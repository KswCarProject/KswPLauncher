package com.wits.ksw.settings.utlis_view;

import android.util.Log;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class McuUtil {
    private static final String TAG = ("KSWLauncher." + KswUtils.class.getSimpleName());

    public static String getMcuVersion() {
        String str;
        StringBuilder sb;
        String mcuVerison = "";
        try {
            mcuVerison = PowerManagerApp.getStatusString("mcuVerison");
            str = TAG;
            sb = new StringBuilder();
        } catch (Exception e) {
            e.getStackTrace();
            String str2 = TAG;
            Log.e(str2, "getMcuVersion: Exception " + e.getMessage());
            str = TAG;
            sb = new StringBuilder();
        } catch (Throwable th) {
            String str3 = TAG;
            Log.d(str3, "mcuVersion: " + mcuVerison);
            throw th;
        }
        sb.append("mcuVersion: ");
        sb.append(mcuVerison);
        Log.d(str, sb.toString());
        return mcuVerison;
    }
}
