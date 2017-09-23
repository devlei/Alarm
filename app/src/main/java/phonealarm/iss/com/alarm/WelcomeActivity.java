package phonealarm.iss.com.alarm;

import android.app.Activity;
import android.os.Bundle;
import phonealarm.iss.com.alarm.utils.IntentUtils;

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
                IntentUtils.openMain(WelcomeActivity.this);
                finish();
            }
        }, 1000);
    }

}
