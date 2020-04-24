package com.wits.ksw.launcher.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import com.wits.ksw.KswApplication;

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

    public static int dip2px(float dpValue) {
        return (int) ((dpValue * KswApplication.appContext.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dip(float pxValue) {
        return (int) ((pxValue / KswApplication.appContext.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
