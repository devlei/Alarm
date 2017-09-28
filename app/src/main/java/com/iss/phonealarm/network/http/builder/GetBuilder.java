package com.iss.phonealarm.network.http.builder;


import com.iss.phonealarm.network.http.request.GetHttpRequest;
import com.iss.phonealarm.network.http.request.OkHttpRequest;

/**
 * Created by wp on 2017/9/23.
 *
 * @description
 */

public class GetBuilder extends OkHttpRequestBuilder<GetBuilder> {


    @Override
    public OkHttpRequest build() {
        return new GetHttpRequest(this.url, this.params);
    }
}
