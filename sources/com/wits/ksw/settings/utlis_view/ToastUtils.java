package com.wits.ksw.settings.utlis_view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/* loaded from: classes10.dex */
public class ToastUtils {
    private static Toast mToast;
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
        getMainThreadHandler().post(new Runnable() { // from class: com.wits.ksw.settings.utlis_view.ToastUtils.1
            @Override // java.lang.Runnable
            public void run() {
                Toast unused = ToastUtils.mToast = Toast.makeText(context.getApplicationContext(), message, duration);
                ToastUtils.mToast.show();
            }
        });
    }

    public static void showToastLong(final Context context, final String message) {
        showToast(context, message, 1);
    }

    public static void showToastShort(final Context context, final String message) {
        showToast(context, message, 0);
    }
}
