package com.wits.ksw.launcher.utils;

import android.app.Instrumentation;

public class KeyUtils {
    public static void pressKey(final int keycode) {
        new Thread() {
            public void run() {
                new Instrumentation().sendKeyDownUpSync(keycode);
            }
        }.start();
    }
}
