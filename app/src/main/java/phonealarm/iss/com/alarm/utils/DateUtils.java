package phonealarm.iss.com.alarm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by weizhilei on 2017/9/23.
 */
public class DateUtils {

    public static final String Y_M_D_H_M = "yyyy-MM-dd HH:mm";
    public static final String D_M_H_M = "dd/MM HH:mm";

    /**
     * 格式化时间
     *
     * @param pattern
     * @param time
     * @return
     */
    public static String formatDate(String pattern, long time) {
        return new SimpleDateFormat(pattern).format(new Date(time));
    }

}
