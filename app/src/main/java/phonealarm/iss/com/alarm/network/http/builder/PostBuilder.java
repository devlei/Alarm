package phonealarm.iss.com.alarm.network.http.builder;


import phonealarm.iss.com.alarm.network.http.request.OkHttpRequest;
import phonealarm.iss.com.alarm.network.http.request.PostHttpRequest;

/**
 * Created by wp on 2017/9/23.
 *
 * @description
 */

public class PostBuilder extends OkHttpRequestBuilder<PostBuilder> {


    @Override
    public OkHttpRequest build() {
        return new PostHttpRequest(this.url, this.params);
    }
}
