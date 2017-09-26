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
        if (!AlarmApplication.mAlarmApplication.isLogin() || AlarmApplication.mUserInfo == null) {
            return;
        }
        if (TextUtils.isEmpty(mNickNameEt.getText())) {
            ToastUtils.showToast(this, "昵称不合法");
            return;
        }
        OkHttpUtils.postBuilder()
                .url(UrlSet.getChangeNickNameUrl(AlarmApplication.mUserInfo.getUserid()))
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
