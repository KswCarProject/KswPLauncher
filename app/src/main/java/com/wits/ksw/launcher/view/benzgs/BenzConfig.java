package com.wits.ksw.launcher.view.benzgs;

import android.content.Context;
import android.provider.Settings;

public class BenzConfig {
    public static final String INDEX = "index";

    public static void saveIndex(Context content, int index) {
        Settings.System.putInt(content.getContentResolver(), INDEX, index);
    }

    public static int getIndex(Context content) {
        return Settings.System.getInt(content.getContentResolver(), INDEX, -1);
    }
}
