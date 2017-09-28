package com.iss.phonealarm.network.http.request;

import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Request;

/**
 * Created by wp on 2017/9/23.
 *
 * @description
 */

public class PostHttpRequest extends OkHttpRequest{

    public PostHttpRequest(String url, HashMap<String, String> params){
        super(url, params);
    }

    @Override
    public void initBuilder() {
        this.builder.url(this.url);
    }


    @Override
    public Request generateRequest() {
        initBuilder();
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        if(this.params != null){
            for(Map.Entry<String, String> entry : params.entrySet()){
                formBodyBuilder.add(entry.getKey(), entry.getValue());
            }
        }
        FormBody body = formBodyBuilder.build();
        Request request = this.builder.post(body).build();
        return request;
    }
}
