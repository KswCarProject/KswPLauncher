package com.wits.ksw.launcher.utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class ScreenUtil {
    private static ScreenUtil instance;

    private ScreenUtil() {
    }

    public static synchronized ScreenUtil getInstance() {
        ScreenUtil screenUtil;
        synchronized (ScreenUtil.class) {
            if (instance == null) {
                instance = new ScreenUtil();
            }
            screenUtil = instance;
        }
        return screenUtil;
    }

    public boolean is1920X720(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels == 1920 && dm.heightPixels == 720;
    }
}
