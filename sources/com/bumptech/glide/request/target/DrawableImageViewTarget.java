package com.bumptech.glide.request.target;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;

public class DrawableImageViewTarget extends ImageViewTarget<Drawable> {
    public DrawableImageViewTarget(ImageView view) {
        super(view);
    }

    @Deprecated
    public DrawableImageViewTarget(ImageView view, boolean waitForLayout) {
        super(view, waitForLayout);
    }

    /* access modifiers changed from: protected */
    public void setResource(@Nullable Drawable resource) {
        ((ImageView) this.view).setImageDrawable(resource);
    }
}
