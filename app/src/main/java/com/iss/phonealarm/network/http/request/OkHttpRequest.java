package com.iss.phonealarm.network.http.request;

import java.util.HashMap;

import okhttp3.Request;
import android.text.TextUtils;

/**
 * Created by wp on 2017/9/23.
 *
 * @description
 */

public abstract class OkHttpRequest {

    protected String  url;

    protected HashMap<String, String> params;

    protected Request.Builder builder = new Request.Builder();

    public  OkHttpRequest(String url, HashMap<String, String> params){

        if(TextUtils.isEmpty(url)){
            throw new RuntimeException("请求路径不能为空");
        }

        this.url = url;
        this.params = params;

    }

    public  abstract void initBuilder();

    public abstract Request generateRequest();


    public RequestCall buildRequestCall(){
        return new RequestCall(this);
    }
}
