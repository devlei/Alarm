package phonealarm.iss.com.alarm.utils;

import android.content.Context;
import phonealarm.iss.com.alarm.MainActivity;
import phonealarm.iss.com.alarm.bean.beLost.BelostInfo;
import phonealarm.iss.com.alarm.bean.carinfo.CarInfo;
import phonealarm.iss.com.alarm.bean.interactquery.InterQueryInfo;
import phonealarm.iss.com.alarm.bean.lost.LostInfo;
import phonealarm.iss.com.alarm.bean.searchalarm.AlarmInfoBean;
import phonealarm.iss.com.alarm.bean.suspect.SuspectInfo;
import phonealarm.iss.com.alarm.hall.*;
import phonealarm.iss.com.alarm.login.LoginActivity;
import phonealarm.iss.com.alarm.login.RegisterActivity;
import phonealarm.iss.com.alarm.personal.*;
import phonealarm.iss.com.alarm.uploadalarm.FastAlarmActivity;
import phonealarm.iss.com.alarm.webview.WebViewActivity;

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
     * 打开通用搜索展示
     *
     * @param context
     * @param typeResId
     */
    public static void openCommonSearch(Context context, int typeResId) {
        CommonSearchActivity.open(context, typeResId);
    }

    /**
     * 打开快速报警页面
     */
    public static void openFastAlarmActivity(Context context, int typeResId) {
        FastAlarmActivity.open(context, typeResId);
    }

    /**
     * 打开登录
     *
     * @param context
     */
    public static void openLogin(Context context) {
        LoginActivity.open(context);
    }

    /**
     * 打开注册
     *
     * @param context
     */
    public static void openRegister(Context context) {
        RegisterActivity.open(context);
    }

    /**
     * 打开人员走失
     *
     * @param context
     * @param belostInfo
     */
    public static void openPeopleLost(Context context, BelostInfo belostInfo) {
        PeopleLostActivity.open(context, belostInfo);
    }

    /**
     * 打开疑犯追踪
     *
     * @param context
     * @param suspectInfo
     */
    public static void openSuspectTrack(Context context, SuspectInfo suspectInfo) {
        SuspectTrackActivity.open(context, suspectInfo);
    }

    /**
     * 打开网页
     *
     * @param context
     * @param url
     */
    public static void openWebView(Context context, String url) {
        WebViewActivity.open(context, url);
    }

    /**
     * 打开车辆追踪
     *
     * @param context
     * @param carInfo
     */
    public static void openVehicleTrack(Context context, CarInfo carInfo) {
        VehicleTrackActivity.open(context, carInfo);
    }

    /**
     * 打开遗失招领
     *
     * @param context
     * @param lostInfo
     */
    public static void openLostFound(Context context, LostInfo lostInfo) {
        LostFoundActivity.open(context, lostInfo);
    }

    /**
     * 打开个人信息
     *
     * @param context
     */
    public static void openPersonalInfo(Context context) {
        PersonalInfoActivity.open(context);
    }

    /**
     * 打开更改昵称
     *
     * @param context
     */
    public static void openChangeNickName(Context context) {
        ChangeNickNameActivity.open(context);
    }

    /**
     * 打开旅馆采集
     *
     * @param context
     */
    public static void openHotalCollect(Context context) {
        HotalCollectActivity.open(context);
    }

    /**
     * 打开租房采集
     *
     * @param context
     */
    public static void openRentCollect(Context context) {
        RentCollectActivity.open(context);
    }

    /**
     * 打开警民互动详情
     *
     * @param context
     * @param interQueryInfo
     */
    public static void openPoliceInteractDetail(Context context, InterQueryInfo interQueryInfo) {
        PoliceInteractDetailActivity.open(context, interQueryInfo);
    }

    /**
     * 打开警民互动添加
     *
     * @param context
     */
    public static void openPoliceInteractAdd(Context context) {
        PoliceInteractAddActivity.open(context);
    }

    /**
     * 打开报警历史
     *
     * @param context
     * @param alarmInfo
     */
    public static void openAlarmHistory(Context context, AlarmInfoBean alarmInfo) {
        AlarmHistoryActivity.open(context, alarmInfo);
    }

}
