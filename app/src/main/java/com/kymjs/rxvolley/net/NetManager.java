package com.kymjs.rxvolley.net;

import android.text.TextUtils;
import com.iss.phonealarm.utils.LogUtils;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpParams;
import com.kymjs.rxvolley.http.RequestQueue;

import java.io.File;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by jczhang on 2016/3/13.
 */
public class NetManager<T> {

    @SuppressWarnings("rawtypes")
    private static NetManager mNetManager = new NetManager();

    private final static int TIME_OUT = 10 * 1000;

    private NetManager() {
    }

    public void setDataCache(String cache) {
        RequestQueue requestQueue;
        if (TextUtils.isEmpty(cache)) {
            requestQueue = RxVolley.getRequestQueue();
        } else {
            requestQueue = RequestQueue.newRequestQueue(new File(cache));
        }
        RxVolley.setRequestQueue(requestQueue);
    }

    @SuppressWarnings("unchecked")
    public static <T> NetManager<T> getInstance() {
        return mNetManager;
    }

    public void download(CustomFileRequest request) {
        if (null != request) {
            getDefaultBuilder().setRequest(request).doTask();
        }
    }

    public void doRequest(CustomFileRequest request) {
        new RxVolley.Builder().setRequest(request).doTask();
    }

    public <T> void doRequest(Url url, RequestHttpCallback<T> callBack) {
        if (null != url && null != callBack) {
            NetManager<T> netManager = NetManager.getInstance();
            if (RxVolley.Method.GET == url.getRequestMethod()) {
                String urlString = withUrlSuffix(url.getUrl(), url.getParams());
                netManager.doGet(urlString, url.getRequestHeaders(), callBack, url.isShouldCache());
                LogUtils.d("get url:" + urlString);
            } else {
                if (url.isContentTypeForJson()) {
                    netManager.doJsonPost(url.getUrl(), url.getRequestHeaders(), url.getJsonString(), callBack);
                } else {
                    netManager.doFormPost(url.getUrl(), url.getRequestHeaders(), url.getParams(), callBack);
                }
                LogUtils.d("post url:" + (null == url ? "" : url.getUrl()));
            }
        }
    }

    private static String withUrlSuffix(String url, Map<String, String> params) {
        if (null != params && params.size() > 0) {
            try {
                Set<Map.Entry<String, String>> paramsSet = params.entrySet();
                // GET请求
                StringBuilder stringBuilder = new StringBuilder(url);
                if (null != paramsSet) {
                    stringBuilder.append("?");
                    int i = 0;
                    String key;
                    String value;
                    for (Map.Entry<String, String> entry : paramsSet) {
                        if (i > 0) {
                            stringBuilder.append('&');
                        }
                        i++;
                        key = entry.getKey();
                        value = entry.getValue();
                        LogUtils.d("key：" + key + "---value:" + value);
                        stringBuilder.append(key);
                        stringBuilder.append('=');
                        stringBuilder.append(URLEncoder.encode(value, "UTF-8"));
                    }
                    return stringBuilder.toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return url;
    }

    private void doGet(String url,
                       Map<String, String> headParams,
                       RequestHttpCallback<T> callback,
                       boolean isShouldCache) {
        getDefaultBuilder(isShouldCache).url(url)
                .httpMethod(RxVolley.Method.GET)
                .params(getParams(headParams, true))
                .callback(callback)
                .doTask();
    }

    private void doJsonPost(String url,
                            Map<String, String> headParams,
                            String jsonString,
                            RequestHttpCallback<T> callback) {
        getDefaultBuilder(true).url(url)
                .httpMethod(RxVolley.Method.POST)
                .params(getJsonParams(headParams, jsonString))
                .contentType(RxVolley.ContentType.JSON)
                .callback(callback)
                .doTask();
    }

    private void doFormPost(String url,
                            Map<String, String> headParams,
                            Map<String, String> bodyParams,
                            RequestHttpCallback<T> callback) {
        getDefaultBuilder(true).url(url)
                .httpMethod(RxVolley.Method.POST)
                .params(getFormParams(headParams, bodyParams))
                .contentType(RxVolley.ContentType.FORM)
                .callback(callback)
                .doTask();
    }

    private RxVolley.Builder getDefaultBuilder(boolean isShouldCache) {
        return getDefaultBuilder().shouldCache(isShouldCache);
    }

    private RxVolley.Builder getDefaultBuilder() {
        return new RxVolley.Builder().timeout(TIME_OUT).encoding("utf-8");
    }

    private HttpParams getParams(HttpParams httpParams, Map<String, String> parms, boolean isHead) {
        if (null == httpParams) {
            httpParams = new HttpParams();
        }
        if (null != parms && parms.size() > 0) {
            if (isHead) {
                LogUtils.d("head parms:" + parms.toString());
            }
            Iterator<String> iterator = parms.keySet().iterator();
            String key;
            String value;
            while (iterator.hasNext()) {
                key = iterator.next();
                value = parms.get(key);
                if (isHead) {
                    httpParams.putHeaders(key, value);
                } else {
                    httpParams.put(key, value);
                }
            }
        }
        return httpParams;
    }

    private HttpParams getParams(Map<String, String> parms, boolean isHeadParams) {
        return getParams(null, parms, isHeadParams);
    }

    private HttpParams getFormParams(Map<String, String> headParams, Map<String, String> bodyParams) {
        return getParams(getParams(headParams, true), bodyParams, false);
    }

    private HttpParams getJsonParams(Map<String, String> headParams, String jsonString) {
        HttpParams httpParams = getParams(headParams, true);
        if (!TextUtils.isEmpty(jsonString)) {
            httpParams.putJsonParams(jsonString);
            LogUtils.d("jsonString:" + jsonString);
        }
        return httpParams;
    }

}
