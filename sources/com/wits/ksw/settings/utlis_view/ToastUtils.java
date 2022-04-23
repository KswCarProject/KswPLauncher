package com.wits.ksw.settings.utlis_view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class ToastUtils {
    /* access modifiers changed from: private */
    public static Toast mToast;
    private static Handler sMainThreadHandler;

    public static Handler getMainThreadHandler() {
        if (sMainThreadHandler == null) {
            synchronized (ToastUtils.class) {
                if (sMainThreadHandler == null) {
                    sMainThreadHandler = new Handler(Looper.getMainLooper());
                }
            }
        }
        return sMainThreadHandler;
    }

    public static void showToast(final Context context, final String message, final int duration) {
        Toast toast = mToast;
        if (toast != null) {
            toast.cancel();
        }
        getMainThreadHandler().post(new Runnable() {
            public void run() {
                Toast unused = ToastUtils.mToast = Toast.makeText(context.getApplicationContext(), message, duration);
                ToastUtils.mToast.show();
            }
        });
    }

    public static void showToastLong(Context context, String message) {
        showToast(context, message, 1);
    }

    public static void showToastShort(Context context, String message) {
        showToast(context, message, 0);
    }
}
