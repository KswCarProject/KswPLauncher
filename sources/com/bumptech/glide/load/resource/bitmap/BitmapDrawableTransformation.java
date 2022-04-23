package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.util.Preconditions;
import java.security.MessageDigest;

@Deprecated
public class BitmapDrawableTransformation implements Transformation<BitmapDrawable> {
    private final Transformation<Drawable> wrapped;

    public BitmapDrawableTransformation(Transformation<Bitmap> wrapped2) {
        this.wrapped = (Transformation) Preconditions.checkNotNull(new DrawableTransformation(wrapped2, false));
    }

    public Resource<BitmapDrawable> transform(Context context, Resource<BitmapDrawable> drawableResourceToTransform, int outWidth, int outHeight) {
        return convertToBitmapDrawableResource(this.wrapped.transform(context, convertToDrawableResource(drawableResourceToTransform), outWidth, outHeight));
    }

    private static Resource<BitmapDrawable> convertToBitmapDrawableResource(Resource<Drawable> resource) {
        if (resource.get() instanceof BitmapDrawable) {
            return resource;
        }
        throw new IllegalArgumentException("Wrapped transformation unexpectedly returned a non BitmapDrawable resource: " + resource.get());
    }

    private static Resource<Drawable> convertToDrawableResource(Resource<BitmapDrawable> toConvert) {
        return toConvert;
    }

    public boolean equals(Object o) {
        if (o instanceof BitmapDrawableTransformation) {
            return this.wrapped.equals(((BitmapDrawableTransformation) o).wrapped);
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
