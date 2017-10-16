package com.kymjs.rxvolley.net.controller;

/**
 * Created by zhangjunchao on 2016/7/25.
 */

public interface DataResponse<T> {
     void setData(T t, Object obj);

     void failMsg(String msg, int errorCode);
}
