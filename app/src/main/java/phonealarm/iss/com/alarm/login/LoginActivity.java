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
import phonealarm.iss.com.alarm.AlarmApplication;
import phonealarm.iss.com.alarm.R;
import phonealarm.iss.com.alarm.bean.login.UserInfoBean;
import phonealarm.iss.com.alarm.network.UrlSet;
import phonealarm.iss.com.alarm.network.callback.CallBack;
import phonealarm.iss.com.alarm.network.http.util.OkHttpUtils;
import phonealarm.iss.com.alarm.utils.AppUtils;
import phonealarm.iss.com.alarm.utils.IntentUtils;
import phonealarm.iss.com.alarm.utils.ToastUtils;

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
        mPasswordEt = (EditText) findViewById(R.id.login_user);

        //set listener
        findViewById(R.id.login_confirm).setOnClickListener(this);
        findViewById(R.id.login_register).setOnClickListener(this);
        ((TextView) findViewById(R.id.login_version)).setText("V" + AppUtils.getVersionName(this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_confirm:
                IntentUtils.openMain(this);
                //login();
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
        OkHttpUtils.postBuilder()
                .url(UrlSet.URL_LOGIN)
                .addParam("", "")
                .build()
                .buildRequestCall()
                .execute(new CallBack<UserInfoBean>() {

                    @Override
                    public void onStart() {}

                    @Override
                    public void onNext(UserInfoBean postBean) {
                        // TODO: 2017/9/25 weizhilei 登录成功后，记录，下次重新进入，自动登录
                        AlarmApplication.mUserInfo = postBean;
                        AlarmApplication.mAlarmApplication.setLogin(true);
                        IntentUtils.openMain(LoginActivity.this);
                    }

                    @Override
                    public void onComplete() {
                        // TODO: 2017/9/25 weizhilei 添加失败原因
                        ToastUtils.showToast(LoginActivity.this, "登录失败");
                    }
                });
    }

}