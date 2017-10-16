package com.kymjs.rxvolley.net.controller;

/**
 * Created by zhangjunchao on 2016/7/25.
 */

public interface DataResWithLocalAsyncTask<T> extends DataResponse<T> {
    String loadLocalTaskForAsync();

    void localTaskResult(String result);
}
