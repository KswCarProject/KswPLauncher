package com.wits.ksw.launcher.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import com.wits.ksw.KswApplication;

/* loaded from: classes11.dex */
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
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        return screenWidth == 1920 && screenHeight == 720;
    }

    public static int dip2px(float dpValue) {
        float scale = KswApplication.appContext.getResources().getDisplayMetrics().density;
        return (int) ((dpValue * scale) + 0.5f);
    }

    public static int px2dip(float pxValue) {
        float scale = KswApplication.appContext.getResources().getDisplayMetrics().density;
        return (int) ((pxValue / scale) + 0.5f);
    }
}
