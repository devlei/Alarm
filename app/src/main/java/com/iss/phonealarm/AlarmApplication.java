package com.iss.phonealarm;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.iss.phonealarm.bean.BaseResponseBean;
import com.iss.phonealarm.bean.ResponseMessageBean;
import com.iss.phonealarm.bean.interactquery.InterQueryAttrConverter;
import com.iss.phonealarm.bean.login.UserInfoBean;
import com.iss.phonealarm.bean.modifyimg.AllUserInfo;
import com.iss.phonealarm.constants.Constants;
import com.iss.phonealarm.network.UrlSet;
import com.iss.phonealarm.network.callback.CallBack;
import com.iss.phonealarm.network.http.util.OkHttpUtils;
import com.iss.phonealarm.personal.observer.UserObserverHelper;
import com.iss.phonealarm.utils.LogUtils;
import com.iss.phonealarm.utils.SharePreferencesUtils;
import com.kymjs.rxvolley.net.ApkDownloadCallback;
import com.kymjs.rxvolley.net.NetManager;
import com.kymjs.rxvolley.net.dowload.DownLoadManager;
import com.kymjs.rxvolley.net.dowload.DownLoadManager.DownloadQueueListener;
import com.kymjs.rxvolley.net.dowload.model.AppDownloadState;
import com.mob.MobApplication;
import com.thoughtworks.xstream.XStream;

/**
 * Created by weizhilei on 2017/9/25.
 */
public class AlarmApplication extends MobApplication {

    //application
    public static AlarmApplication mAlarmApplication;

    public static MainActivity mainActivity;

    //用户信息
    public static AllUserInfo mUserInfo;

    private String mUserId;

    private boolean mIsLogin;
    public LocationClient mLocationClient = null;
    public static String address = "";
    public static String pwd = "";
    public static double weidu, jingdu;

    private NotificationManager notificationManager;
    private Notification notification;

    @Override
    public void onCreate() {
        super.onCreate();
        NetManager.getInstance().setDataCache(getCacheDir().getPath());
        DownLoadManager.getInstance().init(getFilesDir().getPath());
        setGlobleDownloadListener();
        initNotification();
        SDKInitializer.initialize(getApplicationContext());
        mAlarmApplication = this;
        UserObserverHelper.getInstance().registerUserReceiver(this);
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        location();
        mLocationClient.start();
        autoLogin();
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

    private void location() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系
        int span = 0;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps
        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

        mLocationClient.setLocOption(option);
    }

    public BDLocationListener myListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //获取定位结果
            address = location.getLocationDescribe();
            weidu = location.getLatitude();
            jingdu = location.getLongitude();
            if (location.getLocType() == BDLocation.TypeNetWorkException) {
                Toast.makeText(getApplicationContext(), "定位失败,请检查网络！", Toast.LENGTH_SHORT).show();
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                Toast.makeText(getApplicationContext(), "定位失败", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void autoLogin() {
        final String user = SharePreferencesUtils.getInstance().getString(Constants.KEY_USER, null);
        final String password = SharePreferencesUtils.getInstance().getString(Constants.KEY_PASSWORD, null);
        if (TextUtils.isEmpty(user) || TextUtils.isEmpty(password)) {
            clearUser();
            return;
        }
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.setUserid(user);
        userInfoBean.setPassword(password);
        if (!TextUtils.isEmpty(address)) userInfoBean.setEndadress(address);

        XStream xStream = new XStream();
        xStream.autodetectAnnotations(true);
        xStream.registerConverter(new InterQueryAttrConverter());
        String xmlString = xStream.toXML(userInfoBean).replace("__", "_");
        OkHttpUtils.postBuilder()
                .url(UrlSet.URL_LOGIN)
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
                                setLogin(true);
                                mUserId = user;
                            } else {
                                clearUser();
                            }
                        }
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        clearUser();
                    }
                });
    }

    /**
     * 清除用户信息
     */
    private void clearUser() {
        setLogin(false);
        mUserId = null;
        mUserInfo = null;
    }

    private void initNotification() {
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notification = new Notification();
    }

    /**
     * 通知栏中显示下载进度
     */
    private void showDownLoadNotice() {
        // 下载过程中点击通知栏回到程序
        Intent noticeIntent = new Intent(this, MainActivity.class);
        noticeIntent.setAction(Intent.ACTION_MAIN);
        noticeIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, noticeIntent, 0);

        // 设置通知的显示内容
        notification.icon = R.mipmap.ic_launcher;
        notification.tickerText = getString(R.string.app_name);
        notification.contentView = new RemoteViews(getPackageName(), R.layout.app_updgrade_notice);
        notification.contentIntent = pendingIntent;
        notificationManager.notify(0, notification);
    }

    private void setGlobleDownloadListener() {
        DownLoadManager.getInstance().setDownloadQueueListener(new DownloadQueueListener() {
            @Override
            public void downloadQueueUpdate(ApkDownloadCallback curCallBack, int queueSize) {
                if (curCallBack != null) {
                    AppDownloadState state = curCallBack.getAppState();
                    if (getPackageName().equals(state.packName)) {
                        switch (state.appState) {
                            case start:
                                showDownLoadNotice();
                                break;
                            case waiting:
                                break;
                            case pause:
                                break;
                            case downloading:
                                notification.contentView.setProgressBar(R.id.upgrade_progress, 100, state.getProcess(),
                                        false);
                                notificationManager.notify(0, notification);
                                break;
                            case installing:
                                break;
                            case download_succ:
                                LogUtils.i("『download success』");
                                notificationManager.cancel(0);
                                break;
                            case install_succ:
                                LogUtils.i("『install success』");
                                break;
                            case download_fail:
                                LogUtils.i("『download fail』" + state.mErrorMsg);
                                break;
                            case install_fail:
                                LogUtils.i("『install fail』" + state.mErrorMsg);
                                break;
                        }
                    }
                }
            }
        });
    }
}
