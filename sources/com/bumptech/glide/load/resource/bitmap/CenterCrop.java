package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.security.MessageDigest;

/* loaded from: classes.dex */
public class CenterCrop extends BitmapTransformation {

    /* renamed from: ID */
    private static final String f84ID = "com.bumptech.glide.load.resource.bitmap.CenterCrop";
    private static final byte[] ID_BYTES = f84ID.getBytes(CHARSET);

    @Override // com.bumptech.glide.load.resource.bitmap.BitmapTransformation
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return TransformationUtils.centerCrop(pool, toTransform, outWidth, outHeight);
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object o) {
        return o instanceof CenterCrop;
    }

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        return f84ID.hashCode();
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }
}
