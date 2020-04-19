package com.bumptech.glide.request.target;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;

public abstract class ThumbnailImageViewTarget<T> extends ImageViewTarget<T> {
    /* access modifiers changed from: protected */
    public abstract Drawable getDrawable(T t);

    public ThumbnailImageViewTarget(ImageView view) {
        super(view);
    }

    @Deprecated
    public ThumbnailImageViewTarget(ImageView view, boolean waitForLayout) {
        super(view, waitForLayout);
    }

    /* access modifiers changed from: protected */
    public void setResource(@Nullable T resource) {
        ViewGroup.LayoutParams layoutParams = ((ImageView) this.view).getLayoutParams();
        Drawable result = getDrawable(resource);
        if (layoutParams != null && layoutParams.width > 0 && layoutParams.height > 0) {
            result = new FixedSizeDrawable(result, layoutParams.width, layoutParams.height);
        }
        ((ImageView) this.view).setImageDrawable(result);
    }
}
