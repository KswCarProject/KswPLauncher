package com.txznet.weatherquery;

import android.util.Log;
import com.google.gson.Gson;
import java.lang.reflect.Type;

/* loaded from: classes.dex */
public class GsonUtil {
    private static final String TAG = "GsonUtil";

    public static String toJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static <T> T fromJson(byte[] byteJson, Class<T> classOfT) {
        if (byteJson == null) {
            return null;
        }
        try {
            return (T) fromJson(new String(byteJson), (Class<Object>) classOfT);
        } catch (Exception e) {
            Log.e(TAG, "json parse error", e);
            return null;
        }
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        Gson gson = new Gson();
        return (T) gson.fromJson(json, (Class<Object>) classOfT);
    }

    public static <T> T fromJson(byte[] byteJson, Type type) {
        if (byteJson == null) {
            return null;
        }
        return (T) fromJson(new String(byteJson), type);
    }

    public static <T> T fromJson(String json, Type type) {
        Gson gson = new Gson();
        return (T) gson.fromJson(json, type);
    }
}
