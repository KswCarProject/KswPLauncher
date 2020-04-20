package com.wits.ksw.launcher.view.bmwevoid6gs;

import android.content.Context;
import android.provider.Settings;

public class BmwId6GsConfig {
    public static final String INDEX = "bmwid6_index";

    public static void saveIndex(Context content, int index) {
        Settings.System.putInt(content.getContentResolver(), INDEX, index);
    }

    public static int getIndex(Context content) {
        return Settings.System.getInt(content.getContentResolver(), INDEX, 0);
    }
}
