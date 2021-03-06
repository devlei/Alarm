package com.iss.phonealarm.personal;

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
import com.iss.phonealarm.utils.ToastUtils;
import com.thoughtworks.xstream.XStream;

/**
 * Created by weizhilei on 2017/9/23.
 */
public class ChangePasswordActivity extends Activity implements OnClickListener {

    private EditText mOldPasswordTv;
    private EditText mNewPasswordTv;
    private EditText mConfirmPasswordTv;

    /**
     * open
     *
     * @param context
     */
    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, ChangePasswordActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        init();
    }

    private void init() {
        TextView titleTv = (TextView) findViewById(R.id.title_name);
        titleTv.setText(R.string.change_password);
        TextView confirmTv = (TextView) findViewById(R.id.title_other);
        confirmTv.setText(R.string.confirm);
        confirmTv.setOnClickListener(this);
        mOldPasswordTv = (EditText) findViewById(R.id.password_old);
        mNewPasswordTv = (EditText) findViewById(R.id.password_new);
        mConfirmPasswordTv = (EditText) findViewById(R.id.password_confirm);

        findViewById(R.id.title_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_other:
                resetPhone();
                break;
        }
    }

    private void resetPhone() {
        if (TextUtils.isEmpty(mOldPasswordTv.getText()) || TextUtils.isEmpty(
                mNewPasswordTv.getText()) || TextUtils.isEmpty(mConfirmPasswordTv.getText())) {
            ToastUtils.showToast(this, "密码不能为空");
            return;
        }
        if (!mNewPasswordTv.getText().toString().equals(mConfirmPasswordTv.getText().toString())) {
            ToastUtils.showToast(this, "密码输入不一致");
            return;
        }
        if (AlarmApplication.pwd.equals(mNewPasswordTv.getText().toString())) {
            ToastUtils.showToast(this, "不能与上次密码相同");
            return;
        }
        if (AlarmApplication.mAlarmApplication.isLogin()) {
            UserInfoBean userInfoBean = new UserInfoBean();
            userInfoBean.setUserid(AlarmApplication.mAlarmApplication.getUserId());
            userInfoBean.setPassword(mNewPasswordTv.getText().toString());

            XStream xStream = new XStream();
            xStream.autodetectAnnotations(true);
            xStream.registerConverter(new InterQueryAttrConverter());
            String xmlString = xStream.toXML(userInfoBean).replace("__", "_");

            OkHttpUtils.postBuilder()
                    .url(UrlSet.URL_REST_PASSWORD)
                    .addParam("value", xmlString)
                    .build()
                    .buildRequestCall()
                    .execute(new CallBack<ResponseMessageBean>() {

                        @Override
                        public void onStart() {
                        }

                        @Override
                        public void onNext(ResponseMessageBean postBean) {
                            if (postBean.getResult() == BaseResponseBean.RESULT_SUCCESS) {
                                AlarmApplication.pwd = mNewPasswordTv.getText().toString();
                                ToastUtils.showToast(ChangePasswordActivity.this, "重置密码成功");
                                finish();
                            } else {
                                ToastUtils.showToast(ChangePasswordActivity.this, postBean.getMessage());
                            }
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        }
    }

}
