package com.wits.ksw.settings.utlis_view;

import android.util.Log;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class McuUtil {
    private static final String TAG = ("KswApplication." + KswUtils.class.getSimpleName());

    public static String getMcuVersion() {
        try {
            String mcuVerison = PowerManagerApp.getStatusString("mcuVerison");
            Log.d(TAG, "mcuVersion: " + mcuVerison);
            return mcuVerison;
        } catch (Exception e) {
            e.getStackTrace();
            String str = TAG;
            Log.e(str, "getMcuVersion: Exception " + e.getMessage());
            Log.d(str, "mcuVersion: " + "");
            return "";
        } catch (Throwable th) {
            Log.d(TAG, "mcuVersion: " + "");
            throw th;
        }
    }
}
