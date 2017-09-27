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
import phonealarm.iss.com.alarm.AlarmApplication;
import phonealarm.iss.com.alarm.R;
import phonealarm.iss.com.alarm.bean.BaseResponseBean;
import phonealarm.iss.com.alarm.bean.ResponseMessageBean;
import phonealarm.iss.com.alarm.bean.interactquery.InterQueryAttrConverter;
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
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.setUserid(mUserEt.getText().toString());
        userInfoBean.setPassword(mPasswordEt.getText().toString());
        userInfoBean.setEndadress("");

        XStream xStream = new XStream();
        xStream.autodetectAnnotations(true);
        xStream.registerConverter(new InterQueryAttrConverter());
        String xmlString = xStream.toXML(userInfoBean);
        OkHttpUtils.postBuilder()
                .url(UrlSet.URL_LOGIN)
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
                                // TODO: 2017/9/27 weizhilei 应该存后台返回的userid，目前后台无返回，先用本地userid
                                AlarmApplication.mAlarmApplication.setUserId(mUserEt.getText().toString());
                                AlarmApplication.mAlarmApplication.setLogin(true);
                                IntentUtils.openMain(LoginActivity.this);
                                finish();
                            } else {
                                ToastUtils.showToast(LoginActivity.this, postBean.getMessage());
                            }
                        }
                    }

                    @Override
                    public void onComplete() {}
                });
    }

}