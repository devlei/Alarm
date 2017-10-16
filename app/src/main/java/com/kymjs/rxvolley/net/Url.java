package com.kymjs.rxvolley.net;

import android.text.TextUtils;
import com.kymjs.rxvolley.RxVolley.Method;

import java.util.HashMap;
import java.util.Map;

public class Url {

    private String mUrl;

    private Map<String, String> mParams;

    private int mRequstMethod;

    private String mJsonString;

    private boolean isContentTypeForJson = true;

    private static HashMap<String, String> mRequestHeadParamsMap = new HashMap<String, String>();

    private boolean mIsShouldCache = false;

    public static void addRequestHeader(String key, String value) {
        if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
            mRequestHeadParamsMap.put(key, value);
        }
    }

    public static void addRequestHeaders(Map<String, String> headParams) {
        if (null != headParams && headParams.size() > 0) {
            if (null == mRequestHeadParamsMap) {
                mRequestHeadParamsMap = new HashMap<String, String>();
            }
            mRequestHeadParamsMap.putAll(headParams);
        }
    }

    public void setIsShouldCache(boolean flag) {
        mIsShouldCache = flag;
    }

    public boolean isShouldCache() {
        return mIsShouldCache;
    }

    public HashMap<String, String> getRequestHeaders() {
        return mRequestHeadParamsMap;
    }

    public Url(String url) {
        mUrl = url;
        mRequstMethod = Method.GET;
    }

    public String getUrl() {
        return mUrl;
    }

    public Url setUrl(String urlString) {
        mUrl = urlString;
        return this;
    }

    public Map<String, String> getParams() {
        return mParams;
    }

    public Url setParms(Map<String, String> params) {
        mParams = params;
        return this;
    }

    public int getRequestMethod() {
        return mRequstMethod;
    }

    public Url setRequestMethod(int requestMethod) {
        mRequstMethod = requestMethod;
        return this;
    }

    public String getJsonString() {
        return mJsonString;
    }

    public void setJsonString(String jsonString) {
        this.mJsonString = jsonString;
    }

    public boolean isContentTypeForJson() {
        return isContentTypeForJson;
    }

    public void setContentTypeIsForJson(boolean isJsonType) {
        isContentTypeForJson = isJsonType;
    }

}
