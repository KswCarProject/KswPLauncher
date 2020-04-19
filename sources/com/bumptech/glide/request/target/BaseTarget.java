package com.bumptech.glide.request.target;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import com.bumptech.glide.request.Request;

@Deprecated
public abstract class BaseTarget<Z> implements Target<Z> {
    private Request request;

    public void setRequest(@Nullable Request request2) {
        this.request = request2;
    }

    @Nullable
    public Request getRequest() {
        return this.request;
    }

    public void onLoadCleared(@Nullable Drawable placeholder) {
    }

    public void onLoadStarted(@Nullable Drawable placeholder) {
    }

    public void onLoadFailed(@Nullable Drawable errorDrawable) {
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public void onDestroy() {
    }
}
