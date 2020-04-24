package com.bumptech.glide.request.target;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class DrawableThumbnailImageViewTarget extends ThumbnailImageViewTarget<Drawable> {
    public DrawableThumbnailImageViewTarget(ImageView view) {
        super(view);
    }

    @Deprecated
    public DrawableThumbnailImageViewTarget(ImageView view, boolean waitForLayout) {
        super(view, waitForLayout);
    }

    /* access modifiers changed from: protected */
    public Drawable getDrawable(Drawable resource) {
        return resource;
    }
}
