package com.kymjs.rxvolley.net.controller;

import com.google.gson.reflect.TypeToken;
import com.iss.phonealarm.utils.LogUtils;
import com.kymjs.rxvolley.net.NetManager;
import com.kymjs.rxvolley.net.RequestHttpCallback;
import com.kymjs.rxvolley.net.Url;

import java.lang.reflect.Type;

/**
 * Created by zhangjunchao on 2016/7/25.
 */

public class DataGetController<T> {
    private Url mUrl;

    private Type mType;

    private DataResponse<T> mDataResponse;


    /**
     * please use :DataGetController(Url url,  DataResponse<T> response)
     */
    public DataGetController(Url url, Type type, DataResponse<T> response) {
        mUrl = url;
        mType = type == null ? getDefaultType() : type;
        mDataResponse = response;
    }

    public DataGetController(Url url, DataResponse<T> response) {
        mUrl = url;
        mDataResponse = response;
        if (null == mDataResponse) {
            mType = getDefaultType();
        } else {
            mType = new TypeToken<T>() {}.getType();
        }
    }

    private Type getDefaultType() {
        return new TypeToken<String>() {}.getType();
    }

    public void loadData() {
        loadData(null);
    }

    public void loadData(final Object obj) {
        try {
            NetManager.getInstance().doRequest(mUrl, new RequestHttpCallback<T>(mType) {
                @Override
                public void loacalTaskOnSuccess(final String localData) {
                    if (null != mDataResponse) {
                        if (mDataResponse instanceof DataResWithLocalAsyncTask) {
                            ((DataResWithLocalAsyncTask) mDataResponse).localTaskResult(localData);
                        }
                    }
                }

                @Override
                public void onFailure(int errorNo, String strMsg) {
                    if (null != mDataResponse) {
                        // 统一处理
                        mDataResponse.failMsg(strMsg, errorNo);
                    }
                }

                @Override
                public void responseSuccess(T response) {
                    if (null != mDataResponse) {
                        mDataResponse.setData(response, obj);
                    }
                }

                @Override
                public String loadLocalTaskForAsync() {
                    if (null != mDataResponse) {
                        if (mDataResponse instanceof DataResWithLocalAsyncTask) {
                            return ((DataResWithLocalAsyncTask) mDataResponse).loadLocalTaskForAsync();
                        }
                    }
                    return "";
                }

                @Override
                public void onSuccess(final String jsonStr) {
                    LogUtils.d("url:" + mUrl.getUrl() + "----jsonStr:" + jsonStr);
                    super.onSuccess(jsonStr);
                }
            });
        } catch (Exception e) {
            LogUtils.i("Exception--" + e);
            if (null != mDataResponse) {
                mDataResponse.failMsg(e.getMessage(), -1);
            }
        }
    }
}
