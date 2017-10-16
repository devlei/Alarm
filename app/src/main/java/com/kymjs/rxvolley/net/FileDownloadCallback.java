package com.kymjs.rxvolley.net;

import android.util.Log;
import com.kymjs.rxvolley.client.ProgressListener;
import com.kymjs.rxvolley.client.RequestConfig;
import com.kymjs.rxvolley.http.DefaultRetryPolicy;
import com.kymjs.rxvolley.net.dowload.FileInfo;
import com.kymjs.rxvolley.net.dowload.FileInfo.FileType;

import java.io.File;

public abstract class FileDownloadCallback<T> extends RequestHttpCallback<T> implements ProgressListener {
    protected FileInfo mFileInfo;

    protected CustomFileRequest mFileRequest;

    protected boolean mIsDownloadIng;

    public FileDownloadCallback() {
        mFileInfo = FileInfo.getCommonFileInfo();
    }

    public FileDownloadCallback(String url, String fileName, FileType fileType) {
        switch (fileType) {
            case img:
                mFileInfo = FileInfo.getImgFileInfo();
                break;
            case apk:
                mFileInfo = FileInfo.getApkFileInfo();
                break;
            default:
                mFileInfo = FileInfo.getCommonFileInfo();
                break;
        }
        mFileInfo.url = url;
        mFileInfo.setAbsolutePath(fileName);
    }

    public String getFilePath() {
        return mFileInfo.getAbsolutePath();
    }

    public void dowloadFile() {
        if (null != mFileInfo) {
            NetManager<String> netManager = NetManager.getInstance();
            RequestConfig config = new RequestConfig();
            config.mUrl = mFileInfo.url;
            config.mRetryPolicy = new DefaultRetryPolicy(TIME_OUT_TIME, RETRY_NUM, BACKOFF_MULT);
            mFileRequest = new CustomFileRequest(mFileInfo.type, mFileInfo.getAbsolutePath(), config, this);
            if (FileType.img != mFileInfo.type) {
                mFileRequest.setOnProgressListener(this);
            }
            mIsDownloadIng = true;
            netManager.download(mFileRequest);
        }
    }

    public void stopDownload() {
        if (null != mFileRequest) {
            mFileRequest.cancel();
            mIsDownloadIng = false;
        }
    }

    @Override
    public void onSuccess(String jsonStr) {
        super.onSuccess(jsonStr);
        mIsDownloadIng = false;
    }

    public boolean isDownloading() {
        return mIsDownloadIng;
    }

    public boolean isStopDownload() {
        return mFileRequest.isCanceled();
    }

    public boolean deleteCacheFile() {
        String fileCacheStr = getFilePath();
        Log.d("deleteCacheFile", "fileCacheStr:" + fileCacheStr);
        File cacheFile = new File(getFilePath());
        if (cacheFile.exists()) {
            return cacheFile.delete();
        }
        return false;
    }
}
