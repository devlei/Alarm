package phonealarm.iss.com.alarm;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

import phonealarm.iss.com.alarm.bean.modifyimg.AllUserInfo;
import phonealarm.iss.com.alarm.personal.observer.UserObserverHelper;

/**
 * Created by weizhilei on 2017/9/25.
 */
public class AlarmApplication extends Application {

    //application
    public static AlarmApplication mAlarmApplication;

    //用户信息
    public static AllUserInfo mUserInfo;

    private String mUserId;

    private boolean mIsLogin;

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
        mAlarmApplication = this;
        UserObserverHelper.getInstance().registerUserReceiver(this);
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    public void setLogin(boolean login) {
        mIsLogin = login;
    }

    public boolean isLogin() {
        return mIsLogin;
    }

}
