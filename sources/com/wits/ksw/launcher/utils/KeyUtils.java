package com.wits.ksw.launcher.utils;

import android.app.Instrumentation;

/* loaded from: classes11.dex */
public class KeyUtils {
    /* JADX WARN: Type inference failed for: r0v0, types: [com.wits.ksw.launcher.utils.KeyUtils$1] */
    public static void pressKey(final int keycode) {
        new Thread() { // from class: com.wits.ksw.launcher.utils.KeyUtils.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                new Instrumentation().sendKeyDownUpSync(keycode);
            }
        }.start();
    }
}
