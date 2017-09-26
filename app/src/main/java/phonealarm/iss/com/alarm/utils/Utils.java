package phonealarm.iss.com.alarm.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by weizhilei on 2017/9/26.
 */
public class Utils {

    /**
     * 调用拨号界面
     *
     * @param phone 电话号码
     */
    public static void call(Context context, String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
