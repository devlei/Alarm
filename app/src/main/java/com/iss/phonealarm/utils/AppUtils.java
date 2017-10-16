package com.iss.phonealarm.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weizhilei on 2017/9/25.
 */
public class AppUtils {

    public static String getMetaData(Context context, String key) {
        if (!TextUtils.isEmpty(key)) {
            ApplicationInfo applicationInfo = getApplicationInfo(context);
            if (null != applicationInfo) {
                return applicationInfo.metaData.get(key).toString();
            }
        }
        return null;
    }

    /**
     * 检测游戏是否已安装
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAppInstalled(Context context, String packageName) {
        if (null == context || TextUtils.isEmpty(packageName)) {
            return false;
        } else {
            try {
                synchronized (context) {
                    ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(packageName, 0);
                    if (null != appInfo) {
                        return true;
                    }
                }
            } catch (Exception e) {
            }
        }
        return false;
    }

    /**
     * app是否有更新
     *
     * @param context
     * @param versionCode
     * @param packageName
     * @return
     */
    public static boolean isAppHasUpdate(Context context, int versionCode, String packageName) {
        if (context != null && !TextUtils.isEmpty(packageName)) {
            if (versionCode > getAppVersionCode(context, packageName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取应用版本号
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        if (null == context) {
            return 0;
        }
        int versionCode = 0;
        try {
            synchronized (context) {
                versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return versionCode;
    }

    /**
     * 获取应用版本名
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        if (null == context) {
            return "";
        }
        String versionName = "";
        try {
            synchronized (context) {
                versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            }
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /***
     * 获取ApplicationInfo
     *
     * @param context
     * @return
     */
    private static ApplicationInfo getApplicationInfo(Context context) {

        ApplicationInfo applicationInfo = null;
        if (null != context) {
            synchronized (context) {
                try {
                    applicationInfo = context.getPackageManager()
                            .getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                } catch (NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        return applicationInfo;
    }

    /**
     * 获取已安装app的包名列表
     *
     * @param context
     * @return
     */
    public static List<String> getInstalledPackageNames(Context context) {
        if (null != context) {
            synchronized (context) {
                PackageManager pm = context.getPackageManager();
                if (null != pm) {
                    List<String> appsReList = new ArrayList<String>();
                    List<ApplicationInfo> appsList = pm.getInstalledApplications(0);
                    if (null != appsList && appsList.size() > 0) {
                        for (ApplicationInfo appInfo : appsList) {
                            if (!appInfo.packageName.contains("com.android")) {
                                appsReList.add(appInfo.packageName);
                                Log.e("getLocalApps", "packName:" + appInfo.packageName);
                            }
                        }
                        return appsReList;
                    }
                }
            }
        }
        return null;
    }

    private static void chmodPathPermision(final String path) {
        try {
            String command = "chmod " + "777" + " " + path;
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 普通安装
     *
     * @param context
     * @param path
     */
    public static void installAPKFile(Context context, String path) {
        chmodPathPermision(path);
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("file://" + path), "application/vnd.android.package-archive");
        context.startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * 普通卸载
     *
     * @param context
     * @param packageName
     */
    public static void uninstallApp(Context context, String packageName) {
        Intent intent = new Intent("android.intent.action.UNINSTALL_PACKAGE");
        intent.setData(Uri.parse("package:" + packageName));
        context.startActivity(intent);
    }


    public static boolean openApp(Context context, String packageName) {
        return openApp(context, packageName, null);
    }

    /**
     * 打开app
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean openApp(Context context, String packageName, Bundle paramsBundle) {
        if (context != null && !TextUtils.isEmpty(packageName)) {
            synchronized (context) {
                Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
                try {
                    if (null != paramsBundle) {
                        intent.putExtras(paramsBundle);
                    }
                    context.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * 是否是系统app
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isSystemApp(Context context, String packageName) {
        boolean isSystemApp = false;
        if (context != null) {
            synchronized (context) {
                PackageManager pm = context.getPackageManager();
                if (pm != null) {
                    List<PackageInfo> packages = pm.getInstalledPackages(0);
                    ApplicationInfo info;
                    for (PackageInfo packageInfo : packages) {
                        info = packageInfo.applicationInfo;
                        if ((info.flags & ApplicationInfo.FLAG_SYSTEM) == 1 && info.packageName.contains(packageName)) {
                            isSystemApp = true;
                            break;
                        }
                    }
                }
            }
        }
        return isSystemApp;
    }

    /**
     * 获取应用的versionCode
     */
    public static long getAppVersionCode(Context context, String packageName) {
        if (null == context) {
            return -1;
        }
        synchronized (context) {
            long versionCode = -1;
            PackageManager pm = context.getPackageManager();
            PackageInfo info = null;
            try {
                info = pm.getPackageInfo(packageName, 0);
            } catch (NameNotFoundException e) {
                e.printStackTrace();
            }
            if (null != info) {
                versionCode = info.versionCode;
            }
            return versionCode;
        }
    }

}
