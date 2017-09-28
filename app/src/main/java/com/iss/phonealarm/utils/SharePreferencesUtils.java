package com.iss.phonealarm.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.iss.phonealarm.AlarmApplication;

import java.lang.reflect.Type;
import java.util.Set;

/**
 * Created by weizhilei on 2017/9/28.
 */
public class SharePreferencesUtils {
    private static final String PREFERENCE_FILE_NAME = "com.iss.phonealarm.preference_file_key";

    private static SharePreferencesUtils sInstance;

    private SharedPreferences mSharedPreferences;

    private SharePreferencesUtils() {
        mSharedPreferences = AlarmApplication.mAlarmApplication.getSharedPreferences(PREFERENCE_FILE_NAME,
                Context.MODE_PRIVATE);
    }

    public static synchronized SharePreferencesUtils getInstance() {
        if (sInstance == null) {
            sInstance = new SharePreferencesUtils();
        }
        return sInstance;
    }

    public void setBoolean(String key, boolean value) {
        mSharedPreferences.edit().putBoolean(key, value).commit();
    }

    public boolean getBoolean(String key, boolean defValue) {
        return mSharedPreferences.getBoolean(key, defValue);
    }

    public void setInt(String key, int value) {
        mSharedPreferences.edit().putInt(key, value).commit();
    }

    public int getInt(String key, int defValue) {
        int result;
        try {
            result = mSharedPreferences.getInt(key, defValue);
        } catch (Exception e) {
            result = defValue;
        }
        return result;
    }

    public void setLong(String key, long value) {
        mSharedPreferences.edit().putLong(key, value).commit();
    }

    public long getLong(String key, long defValue) {
        return mSharedPreferences.getLong(key, defValue);
    }

    public void setFloat(String key, float value) {
        mSharedPreferences.edit().putFloat(key, value).commit();
    }

    public float getFloat(String key, float defValue) {
        return mSharedPreferences.getFloat(key, defValue);
    }

    public void setString(String key, String value) {
        mSharedPreferences.edit().putString(key, value).commit();
    }

    public String getString(String key, String defValue) {
        String result;
        try {
            result = mSharedPreferences.getString(key, defValue);
        } catch (Exception e) {
            result = defValue;
        }
        return result;
    }

    public void setStringSet(String key, Set<String> values) {
        mSharedPreferences.edit().putStringSet(key, values);
    }

    public Set<String> getStringSet(String key, Set<String> defValues) {
        return mSharedPreferences.getStringSet(key, defValues);
    }

    public <T> void putObject(String key, T object) {
        SharedPreferences sp = mSharedPreferences;
        String objectVal = new Gson().toJson(object);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, objectVal);
        editor.commit();
    }

    public <T> T getObject(String key, Class<T> tClass) {
        SharedPreferences sp = mSharedPreferences;
        if (sp.contains(key)) {
            String objectVal = sp.getString(key, null);
            if (!TextUtils.isEmpty(objectVal)) {
                T object = new Gson().fromJson(objectVal, tClass);
                return object;
            }
        }
        return null;
    }

    public <T> T getObject(String key, Type type) {
        SharedPreferences sp = mSharedPreferences;
        if (sp.contains(key)) {
            String objectVal = sp.getString(key, null);
            if (!TextUtils.isEmpty(objectVal)) {
                T object = new Gson().fromJson(objectVal, type);
                return object;
            }
        }
        return null;
    }

}
