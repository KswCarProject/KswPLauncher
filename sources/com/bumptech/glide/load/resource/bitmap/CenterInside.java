package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.security.MessageDigest;

public class CenterInside extends BitmapTransformation {
    private static final String ID = "com.bumptech.glide.load.resource.bitmap.CenterInside";
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);

    /* access modifiers changed from: protected */
    public Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return TransformationUtils.centerInside(pool, toTransform, outWidth, outHeight);
    }

    public boolean equals(Object o) {
        return o instanceof CenterInside;
    }

    public int hashCode() {
        return ID.hashCode();
    }

    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }
}
