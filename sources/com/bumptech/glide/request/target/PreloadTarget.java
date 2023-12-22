package com.bumptech.glide.request.target;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.transition.Transition;

/* loaded from: classes.dex */
public final class PreloadTarget<Z> extends SimpleTarget<Z> {
    private static final Handler HANDLER = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.bumptech.glide.request.target.PreloadTarget.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                ((PreloadTarget) message.obj).clear();
                return true;
            }
            return false;
        }
    });
    private static final int MESSAGE_CLEAR = 1;
    private final RequestManager requestManager;

    public static <Z> PreloadTarget<Z> obtain(RequestManager requestManager, int width, int height) {
        return new PreloadTarget<>(requestManager, width, height);
    }

    private PreloadTarget(RequestManager requestManager, int width, int height) {
        super(width, height);
        this.requestManager = requestManager;
    }

    @Override // com.bumptech.glide.request.target.Target
    public void onResourceReady(Z resource, Transition<? super Z> transition) {
        HANDLER.obtainMessage(1, this).sendToTarget();
    }

    void clear() {
        this.requestManager.clear(this);
    }
}
