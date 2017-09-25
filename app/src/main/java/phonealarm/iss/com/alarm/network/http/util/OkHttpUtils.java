package phonealarm.iss.com.alarm.network.http.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.ParameterizedType;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import phonealarm.iss.com.alarm.network.callback.CallBack;
import phonealarm.iss.com.alarm.network.http.builder.FileUploadBuilder;
import phonealarm.iss.com.alarm.network.http.builder.GetBuilder;
import phonealarm.iss.com.alarm.network.http.builder.PostBuilder;
import phonealarm.iss.com.alarm.network.http.exception.PotatoException;
import phonealarm.iss.com.alarm.network.http.request.RequestCall;

import android.os.Handler;
import android.os.Looper;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Created by wp on 2017/9/23.
 *
 * @description
 */

public class OkHttpUtils {
    public static final MediaType MEDIA_TYPE_IMG_PNG = MediaType
            .parse("image/png");
    public static final MediaType MEDIA_TYPE_IMG_JPEG = MediaType
            .parse("image/jpeg");
    public static final MediaType MEDIA_TYPE_VIDEO = MediaType
            .parse("audio/mpeg");
    public static final MediaType MEDIA_TYPE_VOICE = MediaType
            .parse("audio/mpeg");
    public static final MediaType MEDIA_TYPE_XML = MediaType.parse("text/xml");
    public static final MediaType MEDIA_TYPE_HTML = MediaType
            .parse("text/html");
    public static final MediaType MEDIA_TYPE_TXT = MediaType
            .parse("text/plain");

    private static OkHttpUtils sOkHttpUtils;
    private static final long TIME = 10;
    private OkHttpClient mOkHttpClient;
    private OkHttpDeliver mDeliver;

    private OkHttpUtils() {
        mOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("potato"))
                .readTimeout(TIME, TimeUnit.SECONDS)
                .writeTimeout(TIME, TimeUnit.SECONDS)
                .connectTimeout(TIME, TimeUnit.SECONDS).build();
        mDeliver = new OkHttpDeliver(new Handler(Looper.getMainLooper()));
    }

    public static OkHttpUtils getInstance() {
        if (sOkHttpUtils == null) {
            synchronized (OkHttpUtils.class) {
                if (sOkHttpUtils == null) {
                    sOkHttpUtils = new OkHttpUtils();
                }
            }
        }
        return sOkHttpUtils;
    }

    public OkHttpClient getOkHttpClient() {
        return this.mOkHttpClient;
    }

    public <T> void execute(RequestCall requestCall, final CallBack<T> callBack) {

        requestCall.getCall().enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (call.isCanceled()) {
                    if (null != callBack) {
                        mDeliver.deliverError(callBack, new PotatoException("取消了"));
                    }
                    return;
                }
                if (call != null) {
                    callBack.onError(e);
                }
            }

            @Override
            public void onResponse(Call call, Response response)
                    throws IOException {

                try {

                    if (response.code() == 404) {
                        if (callBack != null) {
                            mDeliver.deliverError(callBack,
                                    new PotatoException("服务器资源丢失"));
                        }
                        return;
                    }
                    if (response.code() == 500) {
                        if (callBack != null) {
                            mDeliver.deliverError(callBack,
                                    new PotatoException("服务器异常"));
                        }
                        return;
                    }
                    if (!response.isSuccessful()) {
                        if (callBack != null) {
                            mDeliver.deliverError(callBack,
                                    new PotatoException("请求失败"));
                        }
                        return;
                    }

                    // handle
                    if (callBack != null) {
                        InputStream inStrm = response.body().byteStream();
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(inStrm));
                        String inputLine = null;
                        StringBuffer strResponse = new StringBuffer();
                        while ((inputLine = reader.readLine()) != null) {
                            strResponse.append(inputLine);
                        }
                        reader.close();
                        // T t = parse(response.body().string(), callBack);
                        T t = parse(strResponse.toString(), callBack);
                        mDeliver.deliverSuccess(callBack, t);
                    }

                    if (callBack != null) {
                        mDeliver.deliverComplete(callBack);
                    }
                } catch (Exception e) {
                    if (callBack != null) {
                        mDeliver.deliverError(callBack, e);
                    }
                } finally {
                    if (response.body() != null) {
                        response.body().close();
                    }
                }
            }
        });
    }

    /**
     * 杞寲鏂规硶
     *
     * @param bodyString
     * @param back
     * @param <T>
     * @return
     */
    private <T> T parse(String bodyString, CallBack<T> back) {
        try {
            T t;
            ParameterizedType type = (ParameterizedType) back.getClass()
                    .getGenericSuperclass();
            Class<T> c = (Class<T>) type.getActualTypeArguments()[0];
            XStream xStream = new XStream(new DomDriver());
            xStream.ignoreUnknownElements();
            xStream.processAnnotations(c);
            t = (T) xStream.fromXML(bodyString);
            return t;
        } catch (Exception e) {
            System.out.println("===Exception===>" + e);
            e.printStackTrace();
            return null;
        }
    }

    public static GetBuilder getBuilder() {
        return new GetBuilder();
    }

    public static PostBuilder postBuilder() {
        return new PostBuilder();
    }

    public static FileUploadBuilder fileBuilder() {
        return new FileUploadBuilder();
    }
}
