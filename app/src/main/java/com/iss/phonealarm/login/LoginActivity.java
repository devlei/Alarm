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
import com.iss.phonealarm.constants.Constants;
import com.iss.phonealarm.network.UrlSet;
import com.iss.phonealarm.network.callback.CallBack;
import com.iss.phonealarm.network.http.util.OkHttpUtils;
import com.iss.phonealarm.utils.AppUtils;
import com.iss.phonealarm.utils.IntentUtils;
import com.iss.phonealarm.utils.SharePreferencesUtils;
import com.iss.phonealarm.utils.ToastUtils;
import com.iss.phonealarm.utils.Utils;
import com.thoughtworks.xstream.XStream;

/**
 * Created by weizhilei on 2017/9/24.
 */
public class LoginActivity extends Activity implements OnClickListener {

    private EditText mUserEt;
    private EditText mPasswordEt;

    /**
     * open
     *
     * @param context
     */
    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //init views
        mUserEt = (EditText) findViewById(R.id.login_user);
        mPasswordEt = (EditText) findViewById(R.id.login_password);

        //set listener
        findViewById(R.id.login_confirm).setOnClickListener(this);
        findViewById(R.id.login_register).setOnClickListener(this);
        ((TextView) findViewById(R.id.login_version)).setText("V" + AppUtils.getVersionName(this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_confirm:
                login();
                break;
            case R.id.login_register:
                IntentUtils.openRegister(this);
                break;
        }
    }

    /**
     * login
     */
    private void login() {
        if (TextUtils.isEmpty(mUserEt.getText()) || TextUtils.isEmpty(mPasswordEt.getText())) {
            ToastUtils.showToast(this, "手机号或密码不能为空");
            return;
        }
        if (!Utils.isMobile(mUserEt.getText().toString())) {
            ToastUtils.showToast(this, "请检查输入的手机号是否正确");
            return;
        }
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.setUserid(mUserEt.getText().toString());
        userInfoBean.setPassword(mPasswordEt.getText().toString());
        if (!TextUtils.isEmpty(AlarmApplication.address))
            userInfoBean.setEndadress(AlarmApplication.address);

        XStream xStream = new XStream();
        xStream.autodetectAnnotations(true);
        xStream.registerConverter(new InterQueryAttrConverter());
        String xmlString = xStream.toXML(userInfoBean).replace("__", "_");
        OkHttpUtils.postBuilder()
                .url(UrlSet.URL_LOGIN)
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
                                SharePreferencesUtils.getInstance()
                                        .setString(Constants.KEY_USER, mUserEt.getText().toString());
                                SharePreferencesUtils.getInstance()
                                        .setString(Constants.KEY_PASSWORD, mPasswordEt.getText().toString());
                                // TODO: 2017/9/27 weizhilei 应该存后台返回的userid，目前后台无返回，先用本地userid
                                AlarmApplication.mAlarmApplication.setUserId(mUserEt.getText().toString());
                                AlarmApplication.mAlarmApplication.setLogin(true);
                                AlarmApplication.pwd = mPasswordEt.getText().toString();
                                IntentUtils.openMain(LoginActivity.this);
                                finish();
                            } else {
                                ToastUtils.showToast(LoginActivity.this, postBean.getMessage());
                            }
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

}