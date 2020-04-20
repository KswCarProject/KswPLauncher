package com.wits.ksw.settings.utlis_view;

import android.content.Context;
import android.provider.Settings;
import android.text.format.DateFormat;

public class TimeUtil {
    static final String HOURS_12 = "12";
    static final String HOURS_24 = "24";

    public static void setAudoTime(Context mContext, boolean autoEnabled) {
        Settings.Global.putInt(mContext.getContentResolver(), "auto_time", autoEnabled);
    }

    public static void set24Hour(Context mContext, boolean enabled) {
        Settings.System.putString(mContext.getContentResolver(), "time_12_24", enabled ? HOURS_24 : HOURS_12);
    }

    public static boolean isAutoTime(Context mContext) {
        return Settings.Global.getInt(mContext.getContentResolver(), "auto_time", 0) > 1;
    }

    public static boolean is24HourFormat(Context mContext) {
        return DateFormat.is24HourFormat(mContext);
    }
}
