package com.kymjs.rxvolley.net;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.text.TextUtils;
import com.iss.phonealarm.utils.AppUtils;
import com.iss.phonealarm.utils.LogUtils;
import com.kymjs.rxvolley.net.dowload.DownLoadManager;
import com.kymjs.rxvolley.net.dowload.FileInfo.FileType;
import com.kymjs.rxvolley.net.dowload.InstallStateListener;
import com.kymjs.rxvolley.net.dowload.model.AppDownloadState;
import com.kymjs.rxvolley.net.dowload.model.AppStateType;
import com.kymjs.rxvolley.net.tools.IntentAction;

import java.util.Observable;

public abstract class ApkDownloadCallback extends FileDownloadCallback<String> implements InstallStateListener {
    protected EUIInstallApkReceiver mInstallApkReceiver;

    protected SystemInstallApkReceiver mSystemInstallApkReceiver;

    protected AppDownLoadListener mAppDownLoadListener;

    protected AppDownloadState mAppDownloadState;

    private boolean mIsAutoInstall = false;

    private Context mContext;

    protected ApkDownloadCallback(String url,
                                  String fileName,
                                  AppDownloadState state,
                                  boolean isAutoInstall,
                                  Context context) {
        super(url, fileName, FileType.apk);
        mAppDownloadState = state;
        updateAppStateType(mAppDownloadState.appState);
        mIsAutoInstall = isAutoInstall;
        if (null != context) {
            if (context instanceof Activity) {
                try {
                    throw new Exception("appdownloadcall back context is not input Activity,please Context!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            }
            mContext = context;
            addApkInstallListener();
        }
    }

    protected ApkDownloadCallback(AppDownloadState appDownloadState) {
        this("", "", appDownloadState, false, null);
    }

    public ApkDownloadCallback(String url, String gameName, String pckName) {
        this(url, new StringBuffer(pckName).append(".apk").toString(),
                new AppDownloadState(pckName, AppStateType.prepared, gameName), false, null);
    }

    public ApkDownloadCallback(String url, String gameName, String pckName, boolean isAutoInstall, Context context) {
        this(url, new StringBuffer(pckName).append(".apk").toString(),
                new AppDownloadState(pckName, AppStateType.prepared, gameName), isAutoInstall, context);
    }

    public void addApkInstallListener() {
        addEUIApkInstallListener();
        addSystemApkInstallListener();
    }

    private void addEUIApkInstallListener() {
        mInstallApkReceiver = new EUIInstallApkReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(IntentAction.INSTALL_FAILED);
        if (null != mContext) {
            mContext.registerReceiver(mInstallApkReceiver, intentFilter);
        }
    }

    private void addSystemApkInstallListener() {
        mSystemInstallApkReceiver = new SystemInstallApkReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_REPLACED);
        intentFilter.addDataScheme("package");
        if (null != mContext) {
            mContext.registerReceiver(mSystemInstallApkReceiver, intentFilter);
        }
    }

    private class SystemInstallApkReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(final Context context, final Intent intent) {
            doInstallApk(intent);
        }
    }

    private class EUIInstallApkReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            doInstallApk(intent);
        }
    }

    public void removeApkInstallListener() {
        removeEUIApkInstallListener();
        removeSystemApkInstallListener();
    }

    private void removeEUIApkInstallListener() {
        if (null != mInstallApkReceiver && null != mContext) {
            mContext.unregisterReceiver(mInstallApkReceiver);
        }
        mInstallApkReceiver = null;
    }

    private void removeSystemApkInstallListener() {
        if (null != mSystemInstallApkReceiver && null != mContext) {
            mContext.unregisterReceiver(mSystemInstallApkReceiver);
        }
        mSystemInstallApkReceiver = null;
    }

    private void doInstallApk(final Intent intent) {
        if (null == intent) {
            return;
        }
        String packName;
        String action = intent.getAction();
        if (Intent.ACTION_PACKAGE_ADDED.equals(action) || Intent.ACTION_PACKAGE_REPLACED.equals(action)) {
            Uri dataUri = intent.getData();
            packName = dataUri == null ? "" : dataUri.getSchemeSpecificPart();
        } else {
            packName = intent.getStringExtra("com.android.packageinstaller.action.package");
        }
        LogUtils.i("--doInstallApk---");
        if (!TextUtils.isEmpty(packName) && packName.equals(mAppDownloadState.packName)) {
            if (Intent.ACTION_PACKAGE_ADDED.equals(action) || Intent.ACTION_PACKAGE_REPLACED.equals(action)) {
                installSuccess(packName);
                updateAppStateType(AppStateType.install_succ);
            } else if (IntentAction.INSTALL_FAILED.equals(action)) {
                mAppDownloadState.mErrorMsg = intent.getStringExtra("storage_error_reason");
                installFailed(packName, mAppDownloadState.mErrorMsg);
                updateAppStateType(AppStateType.install_fail);
            }
            customNotifyObservers(false);
            if (null != mContext) {
                removeApkInstallListener();
                deleteCacheFile();
            }
        }
    }

    @Override
    public void installSuccess(String packName) {

    }

    @Override
    public void installFailed(String packName, String exceptionMsg) {

    }

    @Override
    public void onFailure(final int errorNo, final String strMsg) {
        mAppDownloadState.mErrorCode = errorNo;
        mAppDownloadState.mErrorMsg = strMsg;
        downLoadFail(errorNo, strMsg);
        updateAppStateType(AppStateType.download_fail);
        customNotifyObservers(false);
    }

    @Override
    public void responseSuccess(final String response) {
        updateDownloadSuccState();
    }

    public void installApk() {
        AppUtils.installAPKFile(mContext, getFilePath());
    }

    public void updateDownloadSuccState() {
        downLoadSuccess();
        updateAppStateType(AppStateType.download_succ);
        if (mIsAutoInstall) {
            updateAppStateType(AppStateType.installing);
            installApk();
        } else {
            customNotifyObservers(false);
        }
    }

    public void downLoadSuccess() {

    }

    public void downLoadFail(final int errorNo, final String strMsg) {

    }

    @Override
    public void onProgress(long progressSize, long total) {
        int tempProcess = mAppDownloadState.getPercentProcess(progressSize, total);
        if (mAppDownloadState.getProcess() != tempProcess) {
            mAppDownloadState.setProcess(tempProcess);
            responseAppState();
        }
    }

    protected void responseAppState() {
        if (null != mAppDownLoadListener) {
            mAppDownLoadListener.onState(mAppDownloadState, mAppDownLoadListener.getTag());
        }
        //只投递：开始中，下载完成，下载失败，安装中，安装成功，安装失败
        //if (!isSelf(mAppDownloadState) && AppStateType.prepared != mAppDownloadState.appState) {
        customNotifyObservers(true);
        //}
    }

    public void updateAppStateType(AppStateType stateType) {
        mAppDownloadState.appState = stateType;
        responseAppState();
    }

    private boolean isSelf(AppDownloadState appDownloadState) {
        if (null != appDownloadState) {
            return "com.iss.phonealarm".equals(appDownloadState.packName);
        }
        return false;
    }

    public AppDownloadState getAppState() {
        return mAppDownloadState;
    }

    public void setAppDownLoadListener(AppDownLoadListener listener) {
        mAppDownLoadListener = listener;
    }

    public static abstract class AppDownLoadListener {
        protected String mTag;

        public abstract void onState(AppDownloadState state, String tag);

        public void setTag(String tag) {
            mTag = tag;
        }

        public String getTag() {
            return mTag;
        }
    }

    protected Observable mAppDownLoadObservable = new Observable() {
        @Override
        public void notifyObservers() {
            this.setChanged();
            super.notifyObservers();
        }

        @Override
        public void notifyObservers(Object arg0) {
            this.setChanged();
            super.notifyObservers(arg0);
        }
    };

    public void customNotifyObservers(boolean isUpdateAppState) {
        if (isUpdateAppState) {
            mAppDownLoadObservable.notifyObservers(new ObserverCallbackObj(this, true));
        } else {
            mAppDownLoadObservable.notifyObservers(new ObserverCallbackObj(this));
        }
    }

    public Observable getAppDownLoadObservable() {
        return mAppDownLoadObservable;
    }

    @Override
    public void stopDownload() {
        super.stopDownload();
        updateAppStateType(AppStateType.pause);
        DownLoadManager.getInstance().downloadNextAppFromCache(this);
    }

    public static class ObserverCallbackObj {
        public ApkDownloadCallback curApkDownloadCallback;

        public boolean isUpdateAppState;

        public ObserverCallbackObj(ApkDownloadCallback callback) {
            this(callback, false);
        }

        public ObserverCallbackObj(ApkDownloadCallback callback, boolean flag) {
            curApkDownloadCallback = callback;
            isUpdateAppState = flag;
        }
    }
}
