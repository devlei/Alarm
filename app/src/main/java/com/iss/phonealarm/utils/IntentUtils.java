package com.iss.phonealarm.utils;

import android.app.Activity;
import android.content.Context;
import com.iss.phonealarm.MainActivity;
import com.iss.phonealarm.bean.beLost.BelostInfo;
import com.iss.phonealarm.bean.carinfo.CarInfo;
import com.iss.phonealarm.bean.interactquery.InterQueryInfo;
import com.iss.phonealarm.bean.lost.LostInfo;
import com.iss.phonealarm.bean.searchalarm.AlarmInfoBean;
import com.iss.phonealarm.bean.suspect.SuspectInfo;
import com.iss.phonealarm.hall.*;
import com.iss.phonealarm.login.LoginActivity;
import com.iss.phonealarm.login.RegisterActivity;
import com.iss.phonealarm.personal.*;
import com.iss.phonealarm.uploadalarm.FastAlarmActivity;
import com.iss.phonealarm.webview.WebViewActivity;

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
     * @param activity
     * @param requestCode
     */
    public static void openEmergencyContactAdd(Activity activity, int requestCode) {
        EmergencyContactAddActivity.openForResult(activity, requestCode);
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
     * @param activity
     * @param requestCode
     */
    public static void openPersonalInfo(Activity activity, int requestCode) {
        PersonalInfoActivity.openForResult(activity, requestCode);
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
        HotelCollectActivity.open(context);
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
     * @param activity
     * @param requestCode
     */
    public static void openPoliceInteractAdd(Activity activity, int requestCode) {
        PoliceInteractAddActivity.openForResult(activity, requestCode);
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

    /**
     * 打开关于
     *
     * @param context
     */
    public static void openAbout(Context context) {
        AboutActivity.open(context);
    }

}
