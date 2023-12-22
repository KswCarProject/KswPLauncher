package com.wits.ksw.settings.utlis_view;

import android.util.Log;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* loaded from: classes10.dex */
public class McuUtil {
    private static final String TAG = "KswApplication." + KswUtils.class.getSimpleName();

    public static String getMcuVersion() {
        String str = "mcuVersion: ";
        String mcuVerison = "";
        try {
            try {
                mcuVerison = PowerManagerApp.getStatusString("mcuVerison");
                str = "mcuVersion: " + mcuVerison;
                Log.d(TAG, str);
                return mcuVerison;
            } catch (Exception e) {
                e.getStackTrace();
                String str2 = TAG;
                Log.e(str2, "getMcuVersion: Exception " + e.getMessage());
                Log.d(str2, "mcuVersion: ");
                return "";
            }
        } catch (Throwable th) {
            Log.d(TAG, str + mcuVerison);
            throw th;
        }
    }
}
