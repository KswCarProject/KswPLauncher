package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.security.MessageDigest;

public class DrawableTransformation implements Transformation<Drawable> {
    private final boolean isRequired;
    private final Transformation<Bitmap> wrapped;

    public DrawableTransformation(Transformation<Bitmap> wrapped2, boolean isRequired2) {
        this.wrapped = wrapped2;
        this.isRequired = isRequired2;
    }

    public Transformation<BitmapDrawable> asBitmapDrawable() {
        return this;
    }

    public Resource<Drawable> transform(Context context, Resource<Drawable> resource, int outWidth, int outHeight) {
        BitmapPool bitmapPool = Glide.get(context).getBitmapPool();
        Drawable drawable = resource.get();
        Resource<Bitmap> bitmapResourceToTransform = DrawableToBitmapConverter.convert(bitmapPool, drawable, outWidth, outHeight);
        if (bitmapResourceToTransform != null) {
            Resource<Bitmap> transformedBitmapResource = this.wrapped.transform(context, bitmapResourceToTransform, outWidth, outHeight);
            if (!transformedBitmapResource.equals(bitmapResourceToTransform)) {
                return newDrawableResource(context, transformedBitmapResource);
            }
            transformedBitmapResource.recycle();
            return resource;
        } else if (!this.isRequired) {
            return resource;
        } else {
            throw new IllegalArgumentException("Unable to convert " + drawable + " to a Bitmap");
        }
    }

    private Resource<Drawable> newDrawableResource(Context context, Resource<Bitmap> transformed) {
        return LazyBitmapDrawableResource.obtain(context.getResources(), transformed);
    }

    public boolean equals(Object o) {
        if (o instanceof DrawableTransformation) {
            return this.wrapped.equals(((DrawableTransformation) o).wrapped);
        }
        return false;
    }

    public int hashCode() {
        return this.wrapped.hashCode();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        this.wrapped.updateDiskCacheKey(messageDigest);
    }
}
