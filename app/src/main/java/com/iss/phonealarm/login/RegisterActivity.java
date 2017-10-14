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

/**
 * Created by weizhilei on 2017/9/24.
 */
public class RegisterActivity extends Activity implements OnClickListener {

    private EditText mPhoneEt;
    private EditText mPasswordEt;
    private EditText mConfirmPasswordEt;

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
