package com.samay.commonlib.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by shaohua.li on 8/12/16.
 */
public class SharedPreferencesUtils {
    private static final String PROJECTION="";
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(PROJECTION, Context.MODE_PRIVATE);
        return preferences.getBoolean(key, defaultValue);
    }

    public static void saveBoolean(Context context, String key, boolean value) {
        SharedPreferences preferences = context.getSharedPreferences(PROJECTION, Context.MODE_PRIVATE);
        preferences.edit().putBoolean(key, value).commit();
    }

    public static int getInt(Context context, String key, int defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(PROJECTION, Context.MODE_PRIVATE);
        return preferences.getInt(key, defaultValue);
    }

    public static void saveInt(Context context, String key, int value) {
        SharedPreferences preferences = context.getSharedPreferences(PROJECTION, Context.MODE_PRIVATE);
        preferences.edit().putInt(key, value).commit();
    }

    public static String getString(Context context, String key, String defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(PROJECTION, Context.MODE_PRIVATE);
        return preferences.getString(key, defaultValue);
    }

    public static void saveString(Context context, String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences(PROJECTION, Context.MODE_PRIVATE);
        preferences.edit().putString(key, value).commit();
    }

    public static float getFloat(Context context, String key, float defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(PROJECTION, Context.MODE_PRIVATE);
        return preferences.getFloat(key, defaultValue);
    }

    public static void savaeFloat(Context context, String key, float value) {
        SharedPreferences preferences = context.getSharedPreferences(PROJECTION, Context.MODE_PRIVATE);
        preferences.edit().putFloat(key, value).commit();
    }

    public static long getLong(Context context, String key, long defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(PROJECTION, Context.MODE_PRIVATE);
        return preferences.getLong(key, defaultValue);
    }

    public static void savaeLong(Context context, String key, long value) {
        SharedPreferences preferences = context.getSharedPreferences(PROJECTION, Context.MODE_PRIVATE);
        preferences.edit().putLong(key, value).commit();
    }
}
