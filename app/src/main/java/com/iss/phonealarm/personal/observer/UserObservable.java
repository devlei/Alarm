package com.iss.phonealarm.personal.observer;

import java.util.Observable;

/**
 * Created by weizhilei on 2017/9/27.
 */
public class UserObservable extends Observable {

    @Override
    public synchronized void setChanged() {
        super.setChanged();
    }

    public synchronized void clearAllObserver() {
        deleteObservers();
    }

}
