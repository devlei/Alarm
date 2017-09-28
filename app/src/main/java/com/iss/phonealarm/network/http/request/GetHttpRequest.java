package com.iss.phonealarm.network.http.request;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;

/**
 * Created by wp on 2017/9/23.
 *
 * @description
 */

public class GetHttpRequest extends OkHttpRequest {


    public GetHttpRequest(String url, HashMap<String, String> params){
        super(url, params);
    }

    @Override
    public void initBuilder() {
        StringBuffer sb = new StringBuffer(this.url);
        if(this.params != null){
        	sb.append("?");
            for(Map.Entry<String, String> entry : params.entrySet()){
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            sb.deleteCharAt(sb.length()-1);
        }
        System.out.println("===ssss==="+sb.toString());
        this.builder.url(sb.toString());
    }

    @Override
    public Request generateRequest() {
        initBuilder();
        Request request = this.builder.build();
        return request;
    }
}
