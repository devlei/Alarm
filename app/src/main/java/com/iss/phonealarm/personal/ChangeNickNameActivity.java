package com.iss.phonealarm.personal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
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
import com.iss.phonealarm.personal.observer.UserReceiver;
import com.iss.phonealarm.utils.ToastUtils;
import com.thoughtworks.xstream.XStream;

/**
 * Created by weizhilei on 2017/9/25.
 */
public class ChangeNickNameActivity extends Activity implements OnClickListener {

    private EditText mNickNameEt;

    /**
     * open
     *
     * @param context
     */
    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, ChangeNickNameActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_nick_name);

        //init
        mNickNameEt = (EditText) findViewById(R.id.cnn_nick_name);
        findViewById(R.id.title_back).setOnClickListener(this);
        findViewById(R.id.title_other).setOnClickListener(this);
        ((TextView) findViewById(R.id.title_name)).setText(R.string.change_nick_name);
        ((TextView) findViewById(R.id.title_other)).setText(R.string.complete);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_other:
                changeNickName();
                break;
        }
    }

    private void changeNickName() {
        if (TextUtils.isEmpty(mNickNameEt.getText())) {
            ToastUtils.showToast(this, "昵称不合法");
            return;
        }
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.setUserid(AlarmApplication.mAlarmApplication.getUserId());
        userInfoBean.setUsername(mNickNameEt.getText().toString());

        XStream xStream = new XStream();
        xStream.autodetectAnnotations(true);
        xStream.registerConverter(new InterQueryAttrConverter());
        String xmlString = xStream.toXML(userInfoBean).replace("__", "_");
        OkHttpUtils.postBuilder()
                .url(UrlSet.URL_RESET_NICKNAME)
                .addParam("userid", AlarmApplication.mAlarmApplication.getUserId())
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
                                ToastUtils.showToast(ChangeNickNameActivity.this, "修改成功");
                                loadUserInfo();
                            } else {
                                ToastUtils.showToast(ChangeNickNameActivity.this, postBean.getMessage());
                            }
                        }
                        finish();
                    }

                    @Override
                    public void onComplete() {}
                });
    }

    /**
     * 发送账户信息改变的广播
     */
    private void sendUserInfoChangeBroadCast() {
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(UserReceiver.ACTION_USER_INFO_CHANGE));
    }

    private void loadUserInfo() {
        if (AlarmApplication.mAlarmApplication.isLogin()) {
            OkHttpUtils.postBuilder()
                    .url(UrlSet.URL_GET_USER)
                    .addParam("userid", AlarmApplication.mAlarmApplication.getUserId())
                    .build()
                    .buildRequestCall()
                    .execute(new CallBack<ResponseMessageBean>() {

                        @Override
                        public void onStart() {}

                        @Override
                        public void onNext(ResponseMessageBean postBean) {
                            if (postBean != null) {
                                if (postBean.getResult() == BaseResponseBean.RESULT_SUCCESS) {
                                    AlarmApplication.mUserInfo = postBean.getUserInfo();
                                    finish();
                                    sendUserInfoChangeBroadCast();
                                }
                            }
                        }

                        @Override
                        public void onComplete() {}
                    });
        }
    }

}
