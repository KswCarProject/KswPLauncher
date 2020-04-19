package com.bumptech.glide.load.resource.gif;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import java.security.MessageDigest;

public class GifDrawableTransformation implements Transformation<GifDrawable> {
    private final Transformation<Bitmap> wrapped;

    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.Object, com.bumptech.glide.load.Transformation<android.graphics.Bitmap>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GifDrawableTransformation(com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r2) {
        /*
            r1 = this;
            r1.<init>()
            java.lang.Object r0 = com.bumptech.glide.util.Preconditions.checkNotNull(r2)
            com.bumptech.glide.load.Transformation r0 = (com.bumptech.glide.load.Transformation) r0
            r1.wrapped = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.gif.GifDrawableTransformation.<init>(com.bumptech.glide.load.Transformation):void");
    }

    @NonNull
    public Resource<GifDrawable> transform(@NonNull Context context, @NonNull Resource<GifDrawable> resource, int outWidth, int outHeight) {
        GifDrawable drawable = resource.get();
        BitmapResource bitmapResource = new BitmapResource(drawable.getFirstFrame(), Glide.get(context).getBitmapPool());
        Resource<Bitmap> transformed = this.wrapped.transform(context, bitmapResource, outWidth, outHeight);
        if (!bitmapResource.equals(transformed)) {
            bitmapResource.recycle();
        }
        drawable.setFrameTransformation(this.wrapped, transformed.get());
        return resource;
    }

    public boolean equals(Object o) {
        if (o instanceof GifDrawableTransformation) {
            return this.wrapped.equals(((GifDrawableTransformation) o).wrapped);
        }
        return false;
    }

    public int hashCode() {
        return this.wrapped.hashCode();
    }

    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        this.wrapped.updateDiskCacheKey(messageDigest);
    }
}
