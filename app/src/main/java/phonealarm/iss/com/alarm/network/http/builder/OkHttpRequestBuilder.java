package phonealarm.iss.com.alarm.network.http.builder;

import java.util.HashMap;

import phonealarm.iss.com.alarm.network.http.request.OkHttpRequest;


/**
 * Created by wp on 2017/9/23.
 *
 * @description
 */

public abstract class OkHttpRequestBuilder<T extends OkHttpRequestBuilder> implements IAddParam{

    protected String url;
    protected HashMap<String, String> params ;


    public T url(String url){
        this.url = url;
        return (T)this;
    }

    @Override
    public T addParam(String key, String value) {

        if(this.params == null){
            this.params = new HashMap<String, String>();
        }
        this.params.put(key, value);
        return (T)this;
    }

    public abstract OkHttpRequest build();
}
