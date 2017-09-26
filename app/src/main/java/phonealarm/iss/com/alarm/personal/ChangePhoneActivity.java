package phonealarm.iss.com.alarm.personal;

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
        if (AlarmApplication.mAlarmApplication.isLogin() && AlarmApplication.mUserInfo != null) {
            OkHttpUtils.postBuilder()
                    .url(UrlSet.getChangePhoneUrl(AlarmApplication.mUserInfo.getUserid()))
                    .build()
                    .buildRequestCall()
                    .execute(new CallBack<UserInfoBean>() {

                        @Override
                        public void onStart() {

                        }

                        @Override
                        public void onNext(UserInfoBean getBean) {
                            finish();
                            // TODO: 2017/9/26 weizhilei 通知外面更新
                        }

                        @Override
                        public void onComplete() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            super.onError(e);
                        }
                    });
        }
    }
}
