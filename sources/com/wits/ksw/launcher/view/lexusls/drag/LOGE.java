package com.wits.ksw.launcher.view.lexusls.drag;

import android.util.Log;

public class LOGE {
    private static final String DEFAULT_TAG = "liuhao";
    private static final int LEVEL = 5;

    public static void V(String msg) {
        Log.v(DEFAULT_TAG, msg);
    }

    public static void D(String msg) {
        Log.d(DEFAULT_TAG, msg);
    }

    public static void I(String msg) {
        Log.i(DEFAULT_TAG, msg);
    }

    public static void W(String msg) {
        Log.w(DEFAULT_TAG, msg);
    }

    public static void E(String msg) {
        Log.e(DEFAULT_TAG, msg);
    }
}
