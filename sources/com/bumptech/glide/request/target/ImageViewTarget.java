package com.bumptech.glide.request.target;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.request.transition.Transition;

public abstract class ImageViewTarget<Z> extends ViewTarget<ImageView, Z> implements Transition.ViewAdapter {
    private Animatable animatable;

    /* access modifiers changed from: protected */
    public abstract void setResource(Z z);

    public ImageViewTarget(ImageView view) {
        super(view);
    }

    @Deprecated
    public ImageViewTarget(ImageView view, boolean waitForLayout) {
        super(view, waitForLayout);
    }

    public Drawable getCurrentDrawable() {
        return ((ImageView) this.view).getDrawable();
    }

    public void setDrawable(Drawable drawable) {
        ((ImageView) this.view).setImageDrawable(drawable);
    }

    public void onLoadStarted(Drawable placeholder) {
        super.onLoadStarted(placeholder);
        setResourceInternal((Object) null);
        setDrawable(placeholder);
    }

    public void onLoadFailed(Drawable errorDrawable) {
        super.onLoadFailed(errorDrawable);
        setResourceInternal((Object) null);
        setDrawable(errorDrawable);
    }

    public void onLoadCleared(Drawable placeholder) {
        super.onLoadCleared(placeholder);
        Animatable animatable2 = this.animatable;
        if (animatable2 != null) {
            animatable2.stop();
        }
        setResourceInternal((Object) null);
        setDrawable(placeholder);
    }

    public void onResourceReady(Z resource, Transition<? super Z> transition) {
        if (transition == null || !transition.transition(resource, this)) {
            setResourceInternal(resource);
        } else {
            maybeUpdateAnimatable(resource);
        }
    }

    public void onStart() {
        Animatable animatable2 = this.animatable;
        if (animatable2 != null) {
            animatable2.start();
        }
    }

    public void onStop() {
        Animatable animatable2 = this.animatable;
        if (animatable2 != null) {
            animatable2.stop();
        }
    }

    private void setResourceInternal(Z resource) {
        setResource(resource);
        maybeUpdateAnimatable(resource);
    }

    private void maybeUpdateAnimatable(Z resource) {
        if (resource instanceof Animatable) {
            Animatable animatable2 = (Animatable) resource;
            this.animatable = animatable2;
            animatable2.start();
            return;
        }
        this.animatable = null;
    }
}
