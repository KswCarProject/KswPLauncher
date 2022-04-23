package com.wits.ksw.launcher.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

public class DisplayUtil {
    public static void getScreenRelatedInformation(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager != null) {
            DisplayMetrics outMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(outMetrics);
            int widthPixels = outMetrics.widthPixels;
            int heightPixels = outMetrics.heightPixels;
            int densityDpi = outMetrics.densityDpi;
            float density = outMetrics.density;
            Log.d("display", "widthPixels = " + widthPixels + ",heightPixels = " + heightPixels + "\n,densityDpi = " + densityDpi + "\n,density = " + density + ",scaledDensity = " + outMetrics.scaledDensity);
        }
    }

    public static String getRealScreenRelatedInformation(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager == null) {
            return "";
        }
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getRealMetrics(outMetrics);
        int widthPixels = outMetrics.widthPixels;
        int heightPixels = outMetrics.heightPixels;
        int densityDpi = outMetrics.densityDpi;
        float density = outMetrics.density;
        Log.d("display", "widthPixels = " + widthPixels + ",heightPixels = " + heightPixels + "\n,densityDpi = " + densityDpi + "\n,density = " + density + ",scaledDensity = " + outMetrics.scaledDensity);
        return widthPixels + "," + heightPixels;
    }
}
