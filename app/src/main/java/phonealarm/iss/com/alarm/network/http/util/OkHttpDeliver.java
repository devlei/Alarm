package phonealarm.iss.com.alarm.network.http.util;

import java.util.concurrent.Executor;

import android.os.Handler;
import android.support.annotation.NonNull;

import phonealarm.iss.com.alarm.network.callback.CallBack;


/**
 * Created by wp on 2017/9/23.
 *
 * @description
 */

public class OkHttpDeliver {

    Executor executor;

    public OkHttpDeliver(final Handler handler){

        executor = new Executor() {
            @Override
            public void execute(@NonNull Runnable runnable) {
                handler.post(runnable);
            }
        };
    }

    /**
     * 成功回调处理
     * @param callBack
     * @param t
     * @param <T>
     */
    public <T> void deliverSuccess(final CallBack<T> callBack, final T t){

        executor.execute(new Runnable() {
            @Override
            public void run() {
                callBack.onNext(t);
            }
        });
    }

    /**
     * 异常回调处理
     * @param callBack
     * @param e
     */
    public void deliverError(final CallBack callBack, final Throwable e){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                callBack.onError(e);
            }
        });
    }

    /**
     * 完成回调处理
     * @param callBack
     */
    public void deliverComplete(final CallBack callBack){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                callBack.onComplete();
            }
        });
    }
}
