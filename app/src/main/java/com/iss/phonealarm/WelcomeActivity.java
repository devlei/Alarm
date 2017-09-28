package com.iss.phonealarm;

import android.app.Activity;
import android.os.Bundle;
import com.iss.phonealarm.utils.IntentUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by weizhilei on 2017/9/22.
 */
public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        delayOpenMain();
    }

    /**
     * 延迟启动主页,5s
     */
    private void delayOpenMain() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (AlarmApplication.mAlarmApplication.isLogin()) {
                    IntentUtils.openMain(WelcomeActivity.this);
                } else {
                    IntentUtils.openLogin(WelcomeActivity.this);
                }
                finish();
            }
        }, 3000);
    }

}
