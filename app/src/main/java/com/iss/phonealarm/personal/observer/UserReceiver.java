package com.iss.phonealarm.personal.observer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by weizhilei on 2017/9/27.
 */
public class UserReceiver extends BroadcastReceiver {

    /**
     * 用户信息变化广播
     */
    public static final String ACTION_USER_INFO_CHANGE = "com.alarm.user.info.change";

    private UserObservable mObservable;

    public UserReceiver(UserObservable observable) {
        mObservable = observable;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ACTION_USER_INFO_CHANGE.equalsIgnoreCase(intent.getAction())) {
            mObservable.setChanged();
            mObservable.notifyObservers();
        }
    }

}
