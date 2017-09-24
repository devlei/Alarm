package phonealarm.iss.com.alarm.network.http.builder;


import phonealarm.iss.com.alarm.network.http.request.GetHttpRequest;
import phonealarm.iss.com.alarm.network.http.request.OkHttpRequest;

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
