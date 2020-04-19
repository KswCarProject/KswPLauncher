package com.wits.ksw.launcher.utils;

import android.text.TextUtils;

public class ExceptionPrint {
    public static void print(String ex) {
        if (!TextUtils.isEmpty(ex)) {
            new NullPointerException(ex).printStackTrace();
        }
    }
}
