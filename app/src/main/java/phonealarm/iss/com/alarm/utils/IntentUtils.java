package phonealarm.iss.com.alarm.utils;

import android.content.Context;
import android.content.Intent;
import phonealarm.iss.com.alarm.MainActivity;

/**
 * Created by weizhilei on 2017/9/22.
 */
public class IntentUtils {

    /**
     * 启动主页
     *
     * @param context
     */
    public static void openMain(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        }
    }

}
