package com.kymjs.rxvolley.net;

import android.content.Context;

/**
 * Created by zhangjunchao on 2017/2/18.
 */

public class GcAppDownloadCallback extends ApkDownloadCallback {

    public GcAppDownloadCallback(final String url,
                                 final String gameName,
                                 final String pckName,
                                 final boolean isAutoInstall,
                                 final Context context) {
        super(url, gameName, pckName, isAutoInstall, context);
    }

    public GcAppDownloadCallback(final String url, final String gameName, final String pckName) {
        super(url, gameName, pckName);
    }


    public void downLoadSuccess() {
    }

    public void downLoadFail(final int errorNo, final String strMsg) {
    }

    @Override
    public void installSuccess(String packName) {
    }

    @Override
    public void installFailed(String packName, String exceptionMsg) {
    }
}
