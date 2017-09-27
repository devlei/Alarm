package phonealarm.iss.com.alarm.login;

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
import com.thoughtworks.xstream.XStream;
import phonealarm.iss.com.alarm.R;
import phonealarm.iss.com.alarm.bean.BaseResponseBean;
import phonealarm.iss.com.alarm.bean.ResponseMessageBean;
import phonealarm.iss.com.alarm.bean.interactquery.InterQueryAttrConverter;
import phonealarm.iss.com.alarm.bean.login.UserInfoBean;
import phonealarm.iss.com.alarm.network.UrlSet;
import phonealarm.iss.com.alarm.network.callback.CallBack;
import phonealarm.iss.com.alarm.network.http.util.OkHttpUtils;
import phonealarm.iss.com.alarm.utils.AppUtils;
import phonealarm.iss.com.alarm.utils.ToastUtils;

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
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.setUserid(mPhoneEt.getText().toString());
        userInfoBean.setUsername(mPhoneEt.getText().toString());
        userInfoBean.setTelephone(mPhoneEt.getText().toString());
        userInfoBean.setStartadress("北京市昌平区回龙观新龙城二期");
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
                    public void onStart() {}

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
                    public void onComplete() {}
                });
    }

}
