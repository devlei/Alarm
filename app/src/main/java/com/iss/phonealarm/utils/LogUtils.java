package com.iss.phonealarm.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by weizhilei on 2017/9/27.
 */
public class LogUtils {

    private static final String TAG = "Alarm-Log";

    private static boolean isShowLog = true;

    public static void v(String msg) {
        v(TAG, msg);
    }

    public static void v(String customTag, String msg) {
        if (isShowLog) {
            logPrint(LogType.v, customTag, msg);
        }
    }

    public static void d(String msg) {
        d(TAG, msg);
    }

    public static void d(String customTag, String msg) {
        if (isShowLog) {
            logPrint(LogType.d, customTag, msg);
        }
    }

    public static void i(String msg) {
        i(TAG, msg);
    }

    public static void i(String customTag, String msg) {
        logPrint(LogType.i, customTag, msg);
    }

    public static void w(String msg) {
        w(TAG, msg);
    }

    public static void w(String customTag, String msg) {
        if (isShowLog) {
            logPrint(LogType.w, customTag, msg);
        }
    }

    public static void e(String msg) {
        e(TAG, msg);
    }

    public static void e(String customTag, String msg) {
        if (isShowLog) {
            logPrint(LogType.e, customTag, msg);
        }
    }

    public static void printException(Exception e) {
        printException("Exception", e);
    }

    public static void printException(String customTag, Exception e) {
        if (isShowLog && null != e) {
            e.printStackTrace();
            String errorMsg = e.getMessage();
            if (!TextUtils.isEmpty(errorMsg)) {
                logPrint(LogType.w, customTag, errorMsg);
            }
        }
    }

    private static void logPrint(LogType type, String tag, String content) {
        if (!TextUtils.isEmpty(content)) {
            int p = 2000;
            long length = content.length();
            if (length < p || length == p) print(type, tag, content);
            else {
                while (content.length() > p) {
                    String logContent = content.substring(0, p);
                    content = content.replace(logContent, "");
                    print(type, tag, logContent);
                }
                print(type, tag, content);
            }
        }
    }

    private static void print(LogType type, String tag, String context) {
        switch (type) {
            case i:
                Log.i(tag, context);
                break;
            case d:
                Log.d(tag, context);
                break;
            case w:
                Log.w(tag, context);
                break;
            case v:
                Log.v(tag, context);
                break;
            case e:
                Log.e(tag, context);
                break;
        }
    }

    private static enum LogType {
        i, d, w, v, e
    }

}
