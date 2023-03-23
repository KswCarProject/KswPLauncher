package com.wits.ksw.launcher.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SpfUtils {
    private static final String FILE_NAME = "philipsLauncherConfig";
    private static SharedPreferences.Editor editor;
    private static SharedPreferences sharedPreferences;

    public static void init(Context context) {
        SharedPreferences sharedPreferences2 = context.getSharedPreferences(FILE_NAME, 0);
        sharedPreferences = sharedPreferences2;
        editor = sharedPreferences2.edit();
    }

    public static void saveData(String key, int value) {
        editor.putInt(key, value).commit();
    }

    public static void saveData(String key, float value) {
        editor.putFloat(key, value).commit();
    }

    public static void saveData(String key, String value) {
        editor.putString(key, value).commit();
    }

    public static void saveData(String key, long value) {
        editor.putLong(key, value).commit();
    }

    public static void saveData(String key, boolean value) {
        editor.putBoolean(key, value).commit();
    }

    public static int getInt(String key, int defValue) {
        return sharedPreferences.getInt(key, defValue);
    }

    public static String getString(String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }

    public static Float getFloat(String key, Float defValue) {
        return Float.valueOf(sharedPreferences.getFloat(key, defValue.floatValue()));
    }

    public static Long getLong(String key, long defValue) {
        return Long.valueOf(sharedPreferences.getLong(key, defValue));
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }
}
