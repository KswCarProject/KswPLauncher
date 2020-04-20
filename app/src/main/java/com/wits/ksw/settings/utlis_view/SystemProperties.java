package com.wits.ksw.settings.utlis_view;

public class SystemProperties {
    public static void set(String key, String val) {
        try {
            Class<?> systemProperties = Class.forName("android.os.SystemProperties");
            systemProperties.getMethod("set", new Class[]{String.class, String.class}).invoke(systemProperties, new Object[]{key, val});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        try {
            Class<?> systemProperties = Class.forName("android.os.SystemProperties");
            return (String) systemProperties.getMethod("get", new Class[]{String.class}).invoke(systemProperties, new Object[]{key});
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
