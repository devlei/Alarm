package phonealarm.iss.com.alarm.utils;

import android.content.Context;
import phonealarm.iss.com.alarm.MainActivity;
import phonealarm.iss.com.alarm.personal.ChangePasswordActivity;
import phonealarm.iss.com.alarm.personal.PersonalActivity;

/**
 * Created by weizhilei on 2017/9/22.
 */
public class IntentUtils {

    /**
     * 启动主页
     *
     * @param context
     */
    public static void openMain(Context context) {
        MainActivity.open(context);
    }

    /**
     * 启动个人中心
     *
     * @param context
     */
    public static void openPersonal(Context context) {
        PersonalActivity.open(context);
    }

    /**
     * 启动修改密码
     *
     * @param context
     */
    public static void openChangePassword(Context context) {
        ChangePasswordActivity.open(context);
    }
}
