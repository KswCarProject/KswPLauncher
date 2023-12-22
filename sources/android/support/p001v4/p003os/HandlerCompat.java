package android.support.p001v4.p003os;

import android.os.Build;
import android.os.Handler;
import android.os.Message;

/* renamed from: android.support.v4.os.HandlerCompat */
/* loaded from: classes.dex */
public final class HandlerCompat {
    public static boolean postDelayed(Handler handler, Runnable r, Object token, long delayMillis) {
        if (Build.VERSION.SDK_INT >= 28) {
            return handler.postDelayed(r, token, delayMillis);
        }
        Message message = Message.obtain(handler, r);
        message.obj = token;
        return handler.sendMessageDelayed(message, delayMillis);
    }

    private HandlerCompat() {
    }
}
