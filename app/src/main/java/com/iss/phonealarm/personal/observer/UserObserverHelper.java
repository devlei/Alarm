package com.iss.phonealarm.personal.observer;

import android.content.Context;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by weizhilei on 2017/9/27.
 */
public class UserObserverHelper {

    private static volatile UserObserverHelper mUserObserverHelper;

    private UserObservable mUserObservable;

    private UserReceiver mUserReceiver;

    public static UserObserverHelper getInstance() {
        if (mUserObserverHelper == null) {
            synchronized (UserObserverHelper.class) {
                if (mUserObserverHelper == null) {
                    mUserObserverHelper = new UserObserverHelper();
                }
            }
        }
        return mUserObserverHelper;
    }

    public UserObserverHelper() {
        mUserObservable = new UserObservable();
    }

    public UserObservable getUserObservable() {
        return mUserObservable;
    }

    /**
     * 注册广播
     *
     * @param context
     */
    public void registerUserReceiver(Context context) {
        if (context != null) {
            mUserReceiver = new UserReceiver(mUserObservable);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(UserReceiver.ACTION_USER_INFO_CHANGE);
            LocalBroadcastManager.getInstance(context).registerReceiver(mUserReceiver, intentFilter);
        }
    }

    /**
     * 添加监听
     *
     * @param listener
     */
    public void addUserObserver(UserObserver listener) {
        if (listener != null) {
            if (mUserObservable != null) {
                mUserObservable.addObserver(listener);
            }
        }
    }

    /**
     * 移除监听
     *
     * @param listener
     */
    public void removeUserObserver(UserObserver listener) {
        if (listener != null) {
            if (mUserObservable != null) {
                mUserObservable.deleteObserver(listener);
            }
        }
    }

    /**
     * 移除所有监听
     */
    public void clearAllObserver() {
        if (mUserObservable != null) {
            mUserObservable.clearAllObserver();
        }
    }

}
