package com.bumptech.glide.request.target;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class BitmapThumbnailImageViewTarget extends ThumbnailImageViewTarget<Bitmap> {
    public BitmapThumbnailImageViewTarget(ImageView view) {
        super(view);
    }

    @Deprecated
    public BitmapThumbnailImageViewTarget(ImageView view, boolean waitForLayout) {
        super(view, waitForLayout);
    }

    /* access modifiers changed from: protected */
    public Drawable getDrawable(Bitmap resource) {
        return new BitmapDrawable(((ImageView) this.view).getResources(), resource);
    }
}
