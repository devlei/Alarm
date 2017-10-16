package com.kymjs.rxvolley.net;

import android.text.TextUtils;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.kymjs.rxvolley.client.HttpCallback;

public abstract class RequestHttpCallback<T> extends HttpCallback {
    protected static final int TIME_OUT_TIME = 1000 * 10;

    protected static final int RETRY_NUM = 2;

    protected static final float BACKOFF_MULT = 1f;

    private Type mType;

    private boolean mIsCheckResJsonStr = true;

    public RequestHttpCallback(Type type, boolean isCheckResJsonStr) {
        mType = type;
        mIsCheckResJsonStr = isCheckResJsonStr;
    }

    public RequestHttpCallback(Type type) {
        this(type, true);
    }

    public RequestHttpCallback() {
        mType = new TypeToken<String>() {
        }.getType();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onSuccess(String jsonStr) {
        if (mIsCheckResJsonStr) {
            if (!TextUtils.isEmpty(jsonStr)) {
                try {
                    T response = (T) new Gson().fromJson(jsonStr, mType);
                    if (null != response) {
                        responseSuccess(response);
                        return;
                    }
                }
                catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
            onFailure(-1, "unKnow");
        }
        else {
            responseSuccess(null);
        }
    }

    @Override
    public abstract void onFailure(int errorNo, String strMsg);

    public abstract void responseSuccess(T response);

    /**
     * 根据自身应用成功状态码，重载该方法 默认成功状态码为：1
     * 
     * @return
     */
    public int getSuccessStateCode() {
        return IResponse.STATE_CODE_SUCCESS;
    }

    public int getUnKownStateCode() {
        return IResponse.STATE_CODE_UNKOWN;
    }
}