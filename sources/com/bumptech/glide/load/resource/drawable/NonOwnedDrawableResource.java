package com.bumptech.glide.load.resource.drawable;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.engine.Resource;

final class NonOwnedDrawableResource extends DrawableResource<Drawable> {
    @Nullable
    static Resource<Drawable> newInstance(@Nullable Drawable drawable) {
        if (drawable != null) {
            return new NonOwnedDrawableResource(drawable);
        }
        return null;
    }

    private NonOwnedDrawableResource(Drawable drawable) {
        super(drawable);
    }

    @NonNull
    public Class<Drawable> getResourceClass() {
        return this.drawable.getClass();
    }

    public int getSize() {
        return Math.max(1, this.drawable.getIntrinsicWidth() * this.drawable.getIntrinsicHeight() * 4);
    }

    public void recycle() {
    }
}
