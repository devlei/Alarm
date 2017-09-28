package com.iss.phonealarm;

import android.app.Application;
import android.text.TextUtils;
import android.widget.Toast;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.iss.phonealarm.bean.BaseResponseBean;
import com.iss.phonealarm.bean.modifyimg.AllUserInfo;
import com.iss.phonealarm.network.http.util.OkHttpUtils;
import com.iss.phonealarm.utils.SharePreferencesUtils;
import com.thoughtworks.xstream.XStream;
import com.iss.phonealarm.bean.ResponseMessageBean;
import com.iss.phonealarm.bean.interactquery.InterQueryAttrConverter;
import com.iss.phonealarm.bean.login.UserInfoBean;
import com.iss.phonealarm.constants.Constants;
import com.iss.phonealarm.network.UrlSet;
import com.iss.phonealarm.network.callback.CallBack;
import com.iss.phonealarm.personal.observer.UserObserverHelper;

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
    public LocationClient mLocationClient = null;
    public static String address = "";
    public static double weidu, jingdu;

    @Override
    public void onCreate() {
        super.onCreate();
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
                    public void onStart() {}

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
                    public void onComplete() {}

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

}
