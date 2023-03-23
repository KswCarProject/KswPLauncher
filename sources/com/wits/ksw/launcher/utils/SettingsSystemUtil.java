package com.wits.ksw.launcher.utils;

import android.content.ContentResolver;
import android.provider.Settings;
import com.wits.ksw.KswApplication;
import java.io.IOException;

public class SettingsSystemUtil {
    public static boolean putString(ContentResolver resolver, String name, String value) {
        boolean b = Settings.System.putString(resolver, name, value);
        syncDataCmd();
        if (b) {
            KswApplication.getSkinNameAndRefresh();
        }
        return b;
    }

    private static void syncDataCmd() {
        try {
            Runtime.getRuntime().exec("sync");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
