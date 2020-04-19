package com.bumptech.glide.request.target;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class BitmapImageViewTarget extends ImageViewTarget<Bitmap> {
    public BitmapImageViewTarget(ImageView view) {
        super(view);
    }

    @Deprecated
    public BitmapImageViewTarget(ImageView view, boolean waitForLayout) {
        super(view, waitForLayout);
    }

    /* access modifiers changed from: protected */
    public void setResource(Bitmap resource) {
        ((ImageView) this.view).setImageBitmap(resource);
    }
}
