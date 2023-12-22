package com.wits.ksw.launcher.utils;

import android.text.TextUtils;

/* loaded from: classes11.dex */
public class ExceptionPrint {
    public static void print(String ex) {
        if (!TextUtils.isEmpty(ex)) {
            NullPointerException exception = new NullPointerException(ex);
            exception.printStackTrace();
        }
    }
}
