package phonealarm.iss.com.alarm.utils;

import android.content.Context;
import phonealarm.iss.com.alarm.MainActivity;
import phonealarm.iss.com.alarm.personal.*;

/**
 * Created by weizhilei on 2017/9/22.
 */
public class IntentUtils {

    /**
     * 打开主页
     *
     * @param context
     */
    public static void openMain(Context context) {
        MainActivity.open(context);
    }

    /**
     * 打开个人中心
     *
     * @param context
     */
    public static void openPersonal(Context context) {
        PersonalActivity.open(context);
    }

    /**
     * 打开修改密码
     *
     * @param context
     */
    public static void openChangePassword(Context context) {
        ChangePasswordActivity.open(context);
    }

    /**
     * 打开附近派出所
     *
     * @param context
     */
    public static void openNearPolice(Context context) {
        NearPoliceActivity.open(context);
    }

    /**
     * 打开紧急联系人
     *
     * @param context
     */
    public static void openEmergencyContact(Context context) {
        EmergencyContactActivity.open(context);
    }

    /**
     * 打开紧急联系人添加
     *
     * @param context
     */
    public static void openEmergencyContactAdd(Context context) {
        EmergencyContactAddActivity.open(context);
    }

    /**
     * 打开绑定号码
     *
     * @param context
     */
    public static void openBindPhone(Context context) {
        BindPhoneActivity.open(context);
    }

    /**
     * 打开更换号码
     *
     * @param context
     */
    public static void openChangePhone(Context context) {
        ChangePhoneActivity.open(context);
    }

    /**
     * 打开报警历史
     *
     * @param context
     */
    public static void openAlarmHistory(Context context) {
        AlarmHistoryActivity.open(context);
    }

}
