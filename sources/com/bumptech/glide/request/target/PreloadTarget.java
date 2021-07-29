package com.bumptech.glide.request.target;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.transition.Transition;

public final class PreloadTarget<Z> extends SimpleTarget<Z> {
    private static final Handler HANDLER = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        public boolean handleMessage(Message message) {
            if (message.what != 1) {
                return false;
            }
            ((PreloadTarget) message.obj).clear();
            return true;
        }
    });
    private static final int MESSAGE_CLEAR = 1;
    private final RequestManager requestManager;

    public static <Z> PreloadTarget<Z> obtain(RequestManager requestManager2, int width, int height) {
        return new PreloadTarget<>(requestManager2, width, height);
    }

    private PreloadTarget(RequestManager requestManager2, int width, int height) {
        super(width, height);
        this.requestManager = requestManager2;
    }

    public void onResourceReady(Z z, Transition<? super Z> transition) {
        HANDLER.obtainMessage(1, this).sendToTarget();
    }

    /* access modifiers changed from: package-private */
    public void clear() {
        this.requestManager.clear((Target<?>) this);
    }
}
