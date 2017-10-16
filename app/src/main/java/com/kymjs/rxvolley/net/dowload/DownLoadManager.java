package com.kymjs.rxvolley.net.dowload;

import android.os.Environment;
import android.text.TextUtils;
import com.iss.phonealarm.utils.CollectionUtils;
import com.iss.phonealarm.utils.LogUtils;
import com.kymjs.rxvolley.net.ApkDownloadCallback;
import com.kymjs.rxvolley.net.ApkDownloadCallback.ObserverCallbackObj;
import com.kymjs.rxvolley.net.FileDownloadCallback;
import com.kymjs.rxvolley.net.ImageDownloadCallback;
import com.kymjs.rxvolley.net.dowload.model.AppDownloadState;
import com.kymjs.rxvolley.net.dowload.model.AppStateType;

import java.util.*;

/**
 * Created by jaunce on 2016/4/25.
 */
public class DownLoadManager {
    private static DownLoadManager mInstance;

    private String mFileRootPath = "/storage/emulated/0";

    private int mMaxLoadTaskCount = 1;

    private DownloadQueueCache mQueueCache;

    private DownLoadManager() {
        mQueueCache = new DefaultQueueCache();
    }

    /**
     * 队列中暂停的任务队列
     */
    private List<String> mPausedTaskList = new ArrayList<>();

    private DownloadQueueListener mDownloadQueueListener;

    public synchronized static DownLoadManager getInstance() {
        if (null == mInstance) {
            mInstance = new DownLoadManager();
        }
        return mInstance;
    }

    public void init() {
        init(Environment.getExternalStorageDirectory().getPath());
    }

    public void init(String fileRootPath) {
        LogUtils.d("rootPah:" + fileRootPath);
        mFileRootPath = fileRootPath;
    }

    public void setQueueCache(DownloadQueueCache downloadQueueCache) {
        mQueueCache = downloadQueueCache;
    }

    public void setMaxLoadTaskCount(int maxTaskCount) {
        mMaxLoadTaskCount = maxTaskCount;
    }

    public String getRootPath() {
        return mFileRootPath;
    }

    public void loadImage(ImageDownloadCallback callback) {
        if (null != callback) {
            callback.loadImage();
        }
    }

    public void startDownloadApk(ApkDownloadCallback callback) {
        if (null != callback) {
            AppStateType curAppStateType = null == callback.getAppState() ? null : callback.getAppState().appState;
            if (AppStateType.downloading == curAppStateType || AppStateType.installing == curAppStateType) {
                return;
            }
            //下载队列是否已满
            boolean isEnough = isHaveEnoughDownloadingTask(callback);
            addToDownLoadCache(callback.getAppState().packName, callback);
            if (isEnough) {
                // 更新下载状态
                callback.updateAppStateType(AppStateType.waiting);
            } else {
                callback.updateAppStateType(AppStateType.start);
                callback.dowloadFile();
                callback.updateAppStateType(AppStateType.downloading);
            }

        }
    }

    public <T> void startDownloadFile(FileDownloadCallback<T> callback) {
        if (null != callback) {
            callback.dowloadFile();
        }
    }

    /**
     * 下载队列
     */
    public void addToDownLoadCache(String key, ApkDownloadCallback call) {
        if (!TextUtils.isEmpty(key) && null != call) {
            if (!mQueueCache.getQueueCache().containsKey(key)) {
                synchronized (mQueueCache.getQueueCache()) {
                    call.getAppDownLoadObservable().addObserver(mDownLoadQueueObserver);
                    mQueueCache.getQueueCache().put(key, call);
                    LogUtils.d("addAppDownLoadCallBack.size:" + mQueueCache.getQueueCache().size());
                }
            }
        }
    }

    public void removeAppDownLoadCallback(String key) {
        synchronized (mQueueCache.getQueueCache()) {
            if (!TextUtils.isEmpty(key) && null != mQueueCache.getQueueCache()) {
                ApkDownloadCallback callback = mQueueCache.getQueueCache().get(key);
                if (null != callback) {
                    mQueueCache.getQueueCache().remove(key);
                }
                LogUtils.d("jaunce--Callbackcache1.size:" + mQueueCache.getQueueCache().size());
            }
        }
    }

    // 更新消息队列
    private Observer mDownLoadQueueObserver = new Observer() {
        @Override
        public void update(Observable observable, Object data) {
            if (null != observable && null != data) {
                if (data instanceof ObserverCallbackObj) {
                    ObserverCallbackObj observerCallbackObj = (ObserverCallbackObj) data;
                    ApkDownloadCallback finishedCallback = observerCallbackObj.curApkDownloadCallback;
                    if (observerCallbackObj.isUpdateAppState) {
                        //response to download queue listenter
                        if (null != mDownloadQueueListener) {
                            mDownloadQueueListener.downloadQueueUpdate(finishedCallback,
                                    getLiveTaskCount(finishedCallback));
                        }
                    } else {
                        //cur app task for download finished
                        observable.deleteObserver(this);
                        removeAppDownLoadCallback(finishedCallback.getAppState().packName);
                        downloadNextAppFromCache();
                    }

                }
            }
        }
    };

    private int getLiveTaskCount(ApkDownloadCallback finishedCallback) {
        AppDownloadState appDownloadState = null == finishedCallback ? null : finishedCallback.getAppState();
        if (null != appDownloadState) {
            if (AppStateType.pause == appDownloadState.appState) {
                if (!mPausedTaskList.contains(appDownloadState.packName)) {
                    mPausedTaskList.add(appDownloadState.packName);
                }
            } else {
                if (!CollectionUtils.isEmpty(mPausedTaskList) && mPausedTaskList.contains(appDownloadState.packName)) {
                    mPausedTaskList.remove(appDownloadState.packName);
                }
            }
            int queueNoPauseTaskCount = taskSize() - mPausedTaskList.size();
            LogUtils.d("queueNoPauseTaskCount :" + queueNoPauseTaskCount);
            return queueNoPauseTaskCount;
        }
        return 0;
    }

    public <T extends ApkDownloadCallback> T getAppDownLoadCallBack(String key) {
        if (null != mQueueCache.getQueueCache() && mQueueCache.getQueueCache().size() > 0) {
            return (T) mQueueCache.getQueueCache().get(key);
        }
        return null;
    }

    public List<ApkDownloadCallback> getNextPreparedCallback(ApkDownloadCallback curCallback) {
        return getNextPreparedCallback(curCallback, false);
    }

    public List<ApkDownloadCallback> getNextPreparedCallback(ApkDownloadCallback curCallback,
                                                             boolean isOnlyGetPausedCallback) {
        // 检查队列中是否还有callback,如果有开启最前面一个
        if (!CollectionUtils.isEmpty(mQueueCache.getQueueCache())) {
            // 排查下队列中是否有正在下载task,如果有return null
            Set<Map.Entry<String, ApkDownloadCallback>> downLoadCallback = mQueueCache.getQueueCache().entrySet();
            ApkDownloadCallback tempCallback;
            List<ApkDownloadCallback> preparedCallBackList = new ArrayList<>();
            AppDownloadState appDownloadState;
            AppStateType tempAppStateType;
            for (Map.Entry<String, ApkDownloadCallback> tempEntry : downLoadCallback) {
                tempCallback = tempEntry.getValue();
                appDownloadState = null == tempCallback ? null : tempCallback.getAppState();
                tempAppStateType = null == appDownloadState ? null : appDownloadState.appState;
                if (isOnlyGetPausedCallback) {
                    if (tempCallback != curCallback && (AppStateType.pause == tempAppStateType)) {
                        preparedCallBackList.add(tempCallback);
                        return preparedCallBackList;
                    }
                } else {
                    if (tempCallback != curCallback && (AppStateType.waiting == tempAppStateType)) {
                        preparedCallBackList.add(tempCallback);
                    }
                    if (preparedCallBackList.size() == mMaxLoadTaskCount) {
                        return preparedCallBackList;
                    }
                }
            }

        }
        return null;
    }

    public boolean isHaveEnoughDownloadingTask(ApkDownloadCallback callback) {
        if (mQueueCache.getQueueCache().size() == 0) {
            return false;
        }
        Set<Map.Entry<String, ApkDownloadCallback>> downLoadCallback = mQueueCache.getQueueCache().entrySet();
        ApkDownloadCallback tempCallback;
        int count = 0;
        for (Map.Entry<String, ApkDownloadCallback> tempEntry : downLoadCallback) {
            tempCallback = tempEntry.getValue();
            if (tempCallback != callback && AppStateType.downloading == tempCallback.getAppState().appState ||
                    AppStateType.installing == tempCallback
                    .getAppState().appState) {
                count++;
            }
            if (count == mMaxLoadTaskCount) {
                return true;
            }
        }
        return false;
    }

    public void downloadNextAppFromCache(ApkDownloadCallback curCallBack) {
        final List<ApkDownloadCallback> nextCallbackList = getNextPreparedCallback(curCallBack);
        if (!CollectionUtils.isEmpty(nextCallbackList)) {
            for (ApkDownloadCallback callbackBuffer : nextCallbackList) {
                startDownloadApk(callbackBuffer);
            }
        }
        //是否存在暂停的游戏，若存在回调给全局监听
        else {
            List<ApkDownloadCallback> pauseCallbackList = getNextPreparedCallback(curCallBack, true);
            if (!CollectionUtils.isEmpty(pauseCallbackList)) {
                for (ApkDownloadCallback callbackBuffer : pauseCallbackList) {
                    callbackBuffer.updateAppStateType(AppStateType.pause);
                }
            }
        }
    }

    private void downloadNextAppFromCache() {
        downloadNextAppFromCache(null);
    }

    public int taskSize() {
        if (null != mQueueCache.getQueueCache()) {
            return mQueueCache.getQueueCache().size();
        }
        return 0;
    }

    class DefaultQueueCache implements DownloadQueueCache {
        /**
         * 下载队列
         */
        private Map<String, ApkDownloadCallback> mAppCallbackMap = new LinkedHashMap<>();

        @Override
        public Map<String, ApkDownloadCallback> getQueueCache() {
            return mAppCallbackMap;
        }
    }

    public interface DownloadQueueCache {
        Map<String, ApkDownloadCallback> getQueueCache();
    }

    public static interface DownloadQueueListener {
        void downloadQueueUpdate(ApkDownloadCallback curCallBack, int queueSize);
    }

    public void setDownloadQueueListener(DownloadQueueListener listener) {
        mDownloadQueueListener = listener;
    }
}
