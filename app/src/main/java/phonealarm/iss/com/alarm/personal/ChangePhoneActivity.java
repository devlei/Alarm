package phonealarm.iss.com.alarm.personal;

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
import phonealarm.iss.com.alarm.personal.observer.UserReceiver;
import phonealarm.iss.com.alarm.utils.ToastUtils;

/**
 * Created by weizhilei on 2017/9/23.
 */
public class ChangePhoneActivity extends Activity implements OnClickListener {

    private EditText mPhoneEt;

    /**
     * open
     *
     * @param context
     */
    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, ChangePhoneActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone);
        init();
    }

    private void init() {
        TextView titleTv = (TextView) findViewById(R.id.title_name);
        titleTv.setText(R.string.change_phone);
        TextView completeTv = (TextView) findViewById(R.id.title_other);
        completeTv.setText(R.string.complete);
        completeTv.setOnClickListener(this);
        mPhoneEt = (EditText) findViewById(R.id.cp_phone);

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
        if (TextUtils.isEmpty(mPhoneEt.getText())) {
            ToastUtils.showToast(this, "手机号不能为空");
            return;
        }
        if (AlarmApplication.mAlarmApplication.isLogin()) {
            UserInfoBean userInfoBean = new UserInfoBean();
            userInfoBean.setUserid(AlarmApplication.mAlarmApplication.getUserId());
            userInfoBean.setTelephone(mPhoneEt.getText().toString());

            XStream xStream = new XStream();
            xStream.autodetectAnnotations(true);
            xStream.registerConverter(new InterQueryAttrConverter());
            String xmlString = xStream.toXML(userInfoBean).replace("__", "_");
            OkHttpUtils.postBuilder()
                    .url(UrlSet.URL_RESET_PHONE)
                    .addParam("userid", AlarmApplication.mAlarmApplication.getUserId())
                    .addParam("value", xmlString)
                    .build()
                    .buildRequestCall()
                    .execute(new CallBack<ResponseMessageBean>() {

                        @Override
                        public void onStart() {}

                        @Override
                        public void onNext(ResponseMessageBean postBean) {
                            if (postBean.getResult() == BaseResponseBean.RESULT_SUCCESS) {
                                ToastUtils.showToast(ChangePhoneActivity.this, "更换成功");
                                loadUserInfo();
                            } else {
                                ToastUtils.showToast(ChangePhoneActivity.this, postBean.getMessage());
                            }
                        }

                        @Override
                        public void onComplete() {}
                    });
        }
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
