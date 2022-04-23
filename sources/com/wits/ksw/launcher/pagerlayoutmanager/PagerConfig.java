package com.wits.ksw.launcher.pagerlayoutmanager;

import android.util.Log;

public class PagerConfig {
    private static final String TAG = "liuhao PagerConfig";
    private static int sFlingThreshold = 1000;
    private static float sMillisecondsPreInch = 60.0f;
    private static boolean sShowLog = false;

    public static boolean isShowLog() {
        return sShowLog;
    }

    public static void setShowLog(boolean showLog) {
        sShowLog = showLog;
    }

    public static int getFlingThreshold() {
        return sFlingThreshold;
    }

    public static void setFlingThreshold(int flingThreshold) {
        sFlingThreshold = flingThreshold;
    }

    public static float getMillisecondsPreInch() {
        return sMillisecondsPreInch;
    }

    public static void setMillisecondsPreInch(float millisecondsPreInch) {
        sMillisecondsPreInch = millisecondsPreInch;
    }

    public static void Logi(String msg) {
        if (isShowLog()) {
            Log.i(TAG, msg);
        }
    }

    public static void Loge(String msg) {
        if (isShowLog()) {
            Log.e(TAG, msg);
        }
    }
}
