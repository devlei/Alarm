package phonealarm.iss.com.alarm.personal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import phonealarm.iss.com.alarm.AlarmApplication;
import phonealarm.iss.com.alarm.R;
import phonealarm.iss.com.alarm.bean.BaseResponseBean;
import phonealarm.iss.com.alarm.bean.ResponseMessageBean;
import phonealarm.iss.com.alarm.bean.modifyimg.AllUserInfo;
import phonealarm.iss.com.alarm.network.UrlSet;
import phonealarm.iss.com.alarm.network.callback.CallBack;
import phonealarm.iss.com.alarm.network.http.util.OkHttpUtils;
import phonealarm.iss.com.alarm.personal.observer.UserAdapterObserver;
import phonealarm.iss.com.alarm.personal.observer.UserObserverHelper;
import phonealarm.iss.com.alarm.utils.AppUtils;
import phonealarm.iss.com.alarm.utils.GlideUtils;
import phonealarm.iss.com.alarm.utils.IntentUtils;
import phonealarm.iss.com.alarm.utils.ToastUtils;

/**
 * Created by weizhilei on 2017/9/23.
 */
public class PersonalActivity extends Activity implements OnClickListener {
    public static final int REQUEST_CODE = 1;

    private ImageView mHeaderIv;
    private TextView mNickNameTv;
    private TextView mPhoneTv;

    /**
     * open
     *
     * @param context
     */
    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, PersonalActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        init();
        loadUserInfo();
        UserObserverHelper.getInstance().addUserObserver(mUserAdapterObserver);
    }

    /**
     * 初始化
     */
    private void init() {
        TextView titleTv = (TextView) findViewById(R.id.title_name);
        titleTv.setText(R.string.personal_center);
        mHeaderIv = (ImageView) findViewById(R.id.personal_header);
        mNickNameTv = (TextView) findViewById(R.id.personal_nickname);
        mPhoneTv = (TextView) findViewById(R.id.personal_phone);

        findViewById(R.id.title_other).setVisibility(View.GONE);
        findViewById(R.id.title_back).setOnClickListener(this);
        findViewById(R.id.personal_info).setOnClickListener(this);
        findViewById(R.id.personal_alarm_history).setOnClickListener(this);
        findViewById(R.id.personal_emergency_contact).setOnClickListener(this);
        findViewById(R.id.personal_near_police).setOnClickListener(this);
        findViewById(R.id.personal_bind_phone).setOnClickListener(this);
        findViewById(R.id.personal_change_password).setOnClickListener(this);
        findViewById(R.id.personal_check_update).setOnClickListener(this);
        findViewById(R.id.personal_about).setOnClickListener(this);

    }

    private void setData(AllUserInfo userInfo) {
        if (userInfo != null) {
            GlideUtils.loadImage(this, userInfo.getUser_picture(), R.drawable.icon_header_default, mHeaderIv);
            mNickNameTv.setText(userInfo.getUser_username());
            mPhoneTv.setText(userInfo.getUser_userid());
        } else {
            mNickNameTv.setText("");
            mPhoneTv.setText("");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.personal_info:
                IntentUtils.openPersonalInfo(this, REQUEST_CODE);
                break;
            case R.id.personal_alarm_history:
                IntentUtils.openCommonSearch(this, R.integer.type_alarm_history);
                break;
            case R.id.personal_emergency_contact:
                IntentUtils.openEmergencyContact(this);
                break;
            case R.id.personal_near_police:
                IntentUtils.openNearPolice(this);
                break;
            case R.id.personal_bind_phone:
                IntentUtils.openBindPhone(this);
                break;
            case R.id.personal_change_password:
                IntentUtils.openChangePassword(this);
                break;
            case R.id.personal_check_update:
                ToastUtils.showToast(this, "已是最新版本：" + AppUtils.getVersionName(this));
                break;
            case R.id.personal_about:
                IntentUtils.openAbout(this);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                loadUserInfo();
                break;
        }
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
                                    setData(postBean.getUserInfo());
                                } else {
                                    ToastUtils.showToast(PersonalActivity.this, postBean.getMessage());
                                    setData(null);
                                }
                            }
                        }

                        @Override
                        public void onComplete() {}
                    });
        }
    }

    private UserAdapterObserver mUserAdapterObserver = new UserAdapterObserver() {
        @Override
        public void onUserInfoChange() {
            setData(AlarmApplication.mUserInfo);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UserObserverHelper.getInstance().removeUserObserver(mUserAdapterObserver);
    }

}
