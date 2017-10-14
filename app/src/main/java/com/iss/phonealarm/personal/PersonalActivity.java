package com.iss.phonealarm.personal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.iss.phonealarm.AlarmApplication;
import com.iss.phonealarm.R;
import com.iss.phonealarm.bean.BaseResponseBean;
import com.iss.phonealarm.bean.ResponseMessageBean;
import com.iss.phonealarm.bean.login.UserInfoBean;
import com.iss.phonealarm.bean.modifyimg.AllUserInfo;
import com.iss.phonealarm.network.UrlSet;
import com.iss.phonealarm.network.callback.CallBack;
import com.iss.phonealarm.network.http.util.OkHttpUtils;
import com.iss.phonealarm.personal.observer.UserAdapterObserver;
import com.iss.phonealarm.personal.observer.UserObserverHelper;
import com.iss.phonealarm.utils.AppUtils;
import com.iss.phonealarm.utils.GlideUtils;
import com.iss.phonealarm.utils.IntentUtils;
import com.iss.phonealarm.utils.ToastUtils;
import com.thoughtworks.xstream.XStream;

import java.io.File;

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
        findViewById(R.id.logout).setOnClickListener(this);

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
            case R.id.logout:
                logout();
                break;
        }
    }

    /**
     * 安装APK文件
     */
    private void installApk() {
        File apkfile = new File("apkName");
        if (!apkfile.exists()) {
            return;
        }
        // 通过Intent安装APK文件
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
                "application/vnd.android.package-archive");
        startActivity(i);
        android.os.Process.killProcess(android.os.Process.myPid());
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

    private void logout() {
        if (AlarmApplication.mAlarmApplication.isLogin()) {
            UserInfoBean userInfoBean = new UserInfoBean();
            userInfoBean.setUserid(AlarmApplication.mAlarmApplication.getUserId());
            userInfoBean.setEndAddress(AlarmApplication.address);
            XStream xStream = new XStream();
            xStream.autodetectAnnotations(true);
            String xmlString = xStream.toXML(userInfoBean).replace("__", "_");
            OkHttpUtils.postBuilder()
                    .url(UrlSet.URL_LOGOUT)
                    .addParam("userid", AlarmApplication.mAlarmApplication.getUserId())
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
                                    IntentUtils.openLogin(PersonalActivity.this);
                                } else {
                                    ToastUtils.showToast(PersonalActivity.this, postBean.getMessage());
                                }
                            }
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
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
                        public void onStart() {
                        }

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
                        public void onComplete() {
                        }
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
