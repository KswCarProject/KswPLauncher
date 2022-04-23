package com.squareup.picasso;

import android.view.ViewTreeObserver;
import android.widget.ImageView;
import java.lang.ref.WeakReference;

class DeferredRequestCreator implements ViewTreeObserver.OnPreDrawListener {
    Callback callback;
    final RequestCreator creator;
    final WeakReference<ImageView> target;

    DeferredRequestCreator(RequestCreator creator2, ImageView target2) {
        this(creator2, target2, (Callback) null);
    }

    DeferredRequestCreator(RequestCreator creator2, ImageView target2, Callback callback2) {
        this.creator = creator2;
        this.target = new WeakReference<>(target2);
        this.callback = callback2;
        target2.getViewTreeObserver().addOnPreDrawListener(this);
    }

    public boolean onPreDraw() {
        ImageView target2 = (ImageView) this.target.get();
        if (target2 == null) {
            return true;
        }
        ViewTreeObserver vto = target2.getViewTreeObserver();
        if (!vto.isAlive()) {
            return true;
        }
        int width = target2.getWidth();
        int height = target2.getHeight();
        if (width <= 0 || height <= 0) {
            return true;
        }
        vto.removeOnPreDrawListener(this);
        this.creator.unfit().resize(width, height).into(target2, this.callback);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void cancel() {
        this.callback = null;
        ImageView target2 = (ImageView) this.target.get();
        if (target2 != null) {
            ViewTreeObserver vto = target2.getViewTreeObserver();
            if (vto.isAlive()) {
                vto.removeOnPreDrawListener(this);
            }
        }
    }
}
