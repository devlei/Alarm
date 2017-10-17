package com.iss.phonealarm.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iss.phonealarm.AlarmApplication;
import com.iss.phonealarm.R;
import com.iss.phonealarm.bean.BaseResponseBean;
import com.iss.phonealarm.bean.ResponseMessageBean;
import com.iss.phonealarm.bean.interactquery.InterQueryAttrConverter;
import com.iss.phonealarm.bean.login.UserInfoBean;
import com.iss.phonealarm.network.UrlSet;
import com.iss.phonealarm.network.callback.CallBack;
import com.iss.phonealarm.network.http.util.OkHttpUtils;
import com.iss.phonealarm.utils.AppUtils;
import com.iss.phonealarm.utils.ToastUtils;
import com.iss.phonealarm.utils.Utils;
import com.thoughtworks.xstream.XStream;

import java.util.HashMap;
import java.util.Random;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

/**
 * Created by weizhilei on 2017/9/24.
 */
public class RegisterActivity extends Activity implements OnClickListener {

    private EditText mPhoneEt;
    private EditText mPasswordEt;
    private EditText mConfirmPasswordEt;
    private EventHandler eventHandler;
    private boolean verification;

    /**
     * open
     *
     * @param context
     */
    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, RegisterActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //init views
        mPhoneEt = (EditText) findViewById(R.id.register_phone);
        mPasswordEt = (EditText) findViewById(R.id.register_password);
        mConfirmPasswordEt = (EditText) findViewById(R.id.register_password_confirm);

        //set listener
        findViewById(R.id.register_back).setOnClickListener(this);
        findViewById(R.id.register).setOnClickListener(this);
        ((TextView) findViewById(R.id.register_version)).setText("V" + AppUtils.getVersionName(this));

        eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (data instanceof Throwable) {
                    Throwable throwable = (Throwable) data;
                    String msg = throwable.getMessage();
                    Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
                } else {
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        // 处理你自己的逻辑
                        verification = true;
                    }
                }
            }
        };
        // 注册监听器
        SMSSDK.registerEventHandler(eventHandler);
    }

    public void verification(View view) {
        RegisterPage registerPage = new RegisterPage();
        registerPage.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                // 解析注册结果
                if (result == SMSSDK.RESULT_COMPLETE) {
                    @SuppressWarnings("unchecked")
                    HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                    String country = (String) phoneMap.get("country");
                    String phone = (String) phoneMap.get("phone");
                    // 提交用户信息
                    registerUser(country, phone);
                }
            }
        });
        registerPage.show(this);
    }

    // 短信注册，随机产生头像
    private static final String[] AVATARS = {
            "http://tupian.qqjay.com/u/2011/0729/e755c434c91fed9f6f73152731788cb3.jpg",
            "http://99touxiang.com/public/upload/nvsheng/125/27-011820_433.jpg",
            "http://img1.touxiang.cn/uploads/allimg/111029/2330264224-36.png",
            "http://img1.2345.com/duoteimg/qqTxImg/2012/04/09/13339485237265.jpg",
            "http://diy.qqjay.com/u/files/2012/0523/f466c38e1c6c99ee2d6cd7746207a97a.jpg",
            "http://img1.touxiang.cn/uploads/20121224/24-054837_708.jpg",
            "http://img1.touxiang.cn/uploads/20121212/12-060125_658.jpg",
            "http://img1.touxiang.cn/uploads/20130608/08-054059_703.jpg",
            "http://diy.qqjay.com/u2/2013/0422/fadc08459b1ef5fc1ea6b5b8d22e44b4.jpg",
            "http://img1.2345.com/duoteimg/qqTxImg/2012/04/09/13339510584349.jpg",
            "http://img1.touxiang.cn/uploads/20130515/15-080722_514.jpg",
            "http://diy.qqjay.com/u2/2013/0401/4355c29b30d295b26da6f242a65bcaad.jpg"
    };

    // 提交用户信息
    private void registerUser(String country, String phone) {
        Random rnd = new Random();
        int id = Math.abs(rnd.nextInt());
        String uid = String.valueOf(id);
        String nickName = "SmsSDK_User_" + uid;
        String avatar = AVATARS[id % 12];
        SMSSDK.submitUserInfo(uid, nickName, avatar, country, phone);
    }

    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_back:
                finish();
                break;
            case R.id.register:
                register();
                break;
        }
    }

    /**
     * register
     */
    private void register() {
        if (!verification) {
            Toast.makeText(this, "请先校验手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(mPhoneEt.getText()) || TextUtils.isEmpty(mPasswordEt.getText()) || TextUtils.isEmpty(
                mConfirmPasswordEt.getText())) {
            ToastUtils.showToast(this, "手机号或密码不能为空");
            return;
        }
        if (!mPasswordEt.getText().toString().equals(mConfirmPasswordEt.getText().toString())) {
            ToastUtils.showToast(this, "密码输入不一致");
            return;
        }
        if (!Utils.isMobile(mPhoneEt.getText().toString())) {
            ToastUtils.showToast(this, "请检查输入的手机号是否正确");
            return;
        }
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.setUserid(mPhoneEt.getText().toString());
        userInfoBean.setUsername(mPhoneEt.getText().toString());
        userInfoBean.setTelephone(mPhoneEt.getText().toString());
        userInfoBean.setStartadress(AlarmApplication.address);
        userInfoBean.setPassword(mPasswordEt.getText().toString());

        XStream xStream = new XStream();
        xStream.autodetectAnnotations(true);
        xStream.registerConverter(new InterQueryAttrConverter());
        String xmlString = xStream.toXML(userInfoBean).replace("__", "_");
        OkHttpUtils.postBuilder()
                .url(UrlSet.URL_REGISTERED)
                .addParam("value", xmlString)
                .build()
                .buildRequestCall()
                .execute(new CallBack<ResponseMessageBean>() {

                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onNext(ResponseMessageBean postBean) {
                        if (postBean != null) {
                            if (postBean.getResult() == BaseResponseBean.RESULT_SUCCESS) {
                                ToastUtils.showToast(RegisterActivity.this, "注册成功");
                                finish();
                            } else {
                                ToastUtils.showToast(RegisterActivity.this, postBean.getMessage());
                            }
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

}
