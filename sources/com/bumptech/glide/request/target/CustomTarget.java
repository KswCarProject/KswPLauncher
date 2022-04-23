package com.bumptech.glide.request.target;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.util.Util;

public abstract class CustomTarget<T> implements Target<T> {
    private final int height;
    private Request request;
    private final int width;

    public CustomTarget() {
        this(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public CustomTarget(int width2, int height2) {
        if (Util.isValidDimensions(width2, height2)) {
            this.width = width2;
            this.height = height2;
            return;
        }
        throw new IllegalArgumentException("Width and height must both be > 0 or Target#SIZE_ORIGINAL, but given width: " + width2 + " and height: " + height2);
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public void onDestroy() {
    }

    public void onLoadStarted(Drawable placeholder) {
    }

    public void onLoadFailed(Drawable errorDrawable) {
    }

    public final void getSize(SizeReadyCallback cb) {
        cb.onSizeReady(this.width, this.height);
    }

    public final void removeCallback(SizeReadyCallback cb) {
    }

    public final void setRequest(Request request2) {
        this.request = request2;
    }

    public final Request getRequest() {
        return this.request;
    }
}
