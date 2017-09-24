package phonealarm.iss.com.alarm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import phonealarm.iss.com.alarm.network.http.util.OkHttpUtils;


public class NetTestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void get(View view){

//        OkHttpUtils.getBuilder()
//                .url("")
//                .build()
//                .buildRequestCall()
//                .execute(new CallBack<GetBean>() {
//
//                    @Override
//                    public void onStart() {
//
//                    }
//
//                    @Override
//                    public void onNext(GetBean getBean) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        super.onError(e);
//                    }
//                });
    }

    public void post(View view){
//        OkHttpUtils.postBuilder()
//                .url("")
//                .addParam("", "")
//                .build()
//                .buildRequestCall()
//                .execute(new CallBack<PostBean>() {
//
//                    @Override
//                    public void onStart() {
//
//                    }
//
//                    @Override
//                    public void onNext(PostBean postBean) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        super.onError(e);
//                    }
//                });
    }

    public void file(View view){
//        OkHttpUtils.fileBuilder()
//                .url("")
//                .addParam("", "")
//                .addFile("", null, null)
//                .build()
//                .buildRequestCall()
//                .execute(new CallBack<FileBean>() {
//
//                    @Override
//                    public void onStart() {
//
//                    }
//
//                    @Override
//                    public void onNext(FileBean fileBean) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        super.onError(e);
//                    }
//                });
    }
}
