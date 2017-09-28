package com.iss.phonealarm.network.callback;

/**
 * Created by wp on 2017/9/23.
 *
 * @description
 */

public abstract class CallBack<T> {

    public abstract void onStart();

    public abstract void onNext(T t);

    public abstract void onComplete();


    public  void onError(Throwable e){

    }
}
