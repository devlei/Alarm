package com.kymjs.rxvolley.net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import com.iss.phonealarm.utils.FileUtils;
import com.kymjs.rxvolley.net.dowload.FileInfo.FileType;
import com.kymjs.rxvolley.net.dowload.Image;

import java.io.File;
import java.io.FileOutputStream;

public abstract class ImageDownloadCallback extends FileDownloadCallback<Image> {

    private Image mResult;

    private View mDest;

    private boolean mIsSuccess;

    private Exception mException;

    public ImageDownloadCallback(String url) {
        this(null, url);
    }

    public ImageDownloadCallback(View dest, String url) {
        super(url, FileUtils.getImgLocalFileName(url), FileType.img);
        mDest = dest;
    }

    public void loadImage() {
        // 三级缓存机制
        // 1内存中获取图片
        // 2查询本地图片
        // if(ImageUtils.checkCacheFile(mImgUrl)) {
        // mBitmap=ImageUtils.getImageFromCache(mImgUrl);
        // if(null!=mBitmap){
        // if(null!=mImageListener){
        // mImageListener.onSuccess(mDest,mBitmap);
        // return;
        // }
        // }
        // }
        // 3网络下载
        dowloadFile();
    }

    @Override
    public void onSuccessInAsync(byte[] fileData) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(fileData, 0, fileData.length);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(mFileInfo.getAbsolutePath()));
            fos.write(fileData);
            fos.flush();
            mIsSuccess = true;
        } catch (Exception e) {
            mIsSuccess = false;
            mException = e;
        } finally {
            mResult = new Image(bitmap);
            mResult.view = mDest;
            if (null != fos) {
                try {
                    fos.close();
                } catch (Exception e) {
                    mException = e;
                    mIsSuccess = false;
                }
            }
        }

    }

    @Override
    public void onSuccess(String jsonStr) {
    }

    @Override
    public void onFinish() {
        if (mIsSuccess) {
            responseSuccess(mResult);
        } else {
            onFailure(getUnKownStateCode(), mException == null ? "unkown exception" : mException.getMessage());
        }
    }

    @Override
    public void onProgress(long transferredBytes, long totalSize) {

    }
}
