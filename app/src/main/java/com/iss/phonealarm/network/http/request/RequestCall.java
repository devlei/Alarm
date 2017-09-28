package com.iss.phonealarm.network.http.request;

import com.iss.phonealarm.network.http.util.OkHttpUtils;
import okhttp3.Call;
import okhttp3.Request;
import com.iss.phonealarm.network.callback.CallBack;


/**
 * Created by wp on 2017/9/23.
 * 
 * @description
 */

public class RequestCall {

	private OkHttpRequest okHttpRequest;

	private Request request;

	private Call call;

	public RequestCall(OkHttpRequest okHttpRequest) {
		this.okHttpRequest = okHttpRequest;
	}

	public <T> void execute(CallBack<T> callBack) {

		buildCall();
		if (callBack != null) {
			callBack.onStart();
		}
		OkHttpUtils.getInstance().execute(this, callBack);
	}

	private void buildCall() {
		this.request = this.okHttpRequest.generateRequest();
		this.call = OkHttpUtils.getInstance().getOkHttpClient()
				.newCall(this.request);
	}

	public Call getCall() {
		return this.call;
	}
}
