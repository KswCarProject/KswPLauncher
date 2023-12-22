package android.support.p001v4.app;

import android.app.ActivityManager;
import android.os.Build;

/* renamed from: android.support.v4.app.ActivityManagerCompat */
/* loaded from: classes.dex */
public final class ActivityManagerCompat {
    private ActivityManagerCompat() {
    }

    public static boolean isLowRamDevice(ActivityManager activityManager) {
        if (Build.VERSION.SDK_INT >= 19) {
            return activityManager.isLowRamDevice();
        }
        return false;
    }
}
