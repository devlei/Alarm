package phonealarm.iss.com.alarm;

import android.app.Application;
import phonealarm.iss.com.alarm.bean.login.UserInfoBean;

/**
 * Created by weizhilei on 2017/9/25.
 */
public class AlarmApplication extends Application {

    //application
    public static AlarmApplication mAlarmApplication;

    //用户信息
    public static UserInfoBean mUserInfo;

    private String mUserId;

    private boolean mIsLogin;

    @Override
    public void onCreate() {
        super.onCreate();
        mAlarmApplication = this;
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
