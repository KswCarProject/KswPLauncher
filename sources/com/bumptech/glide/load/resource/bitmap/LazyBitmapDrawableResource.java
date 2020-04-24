package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Initializable;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Preconditions;

public final class LazyBitmapDrawableResource implements Resource<BitmapDrawable>, Initializable {
    private final Resource<Bitmap> bitmapResource;
    private final Resources resources;

    @Deprecated
    public static LazyBitmapDrawableResource obtain(Context context, Bitmap bitmap) {
        return (LazyBitmapDrawableResource) obtain(context.getResources(), (Resource<Bitmap>) BitmapResource.obtain(bitmap, Glide.get(context).getBitmapPool()));
    }

    @Deprecated
    public static LazyBitmapDrawableResource obtain(Resources resources2, BitmapPool bitmapPool, Bitmap bitmap) {
        return (LazyBitmapDrawableResource) obtain(resources2, (Resource<Bitmap>) BitmapResource.obtain(bitmap, bitmapPool));
    }

    @Nullable
    public static Resource<BitmapDrawable> obtain(@NonNull Resources resources2, @Nullable Resource<Bitmap> bitmapResource2) {
        if (bitmapResource2 == null) {
            return null;
        }
        return new LazyBitmapDrawableResource(resources2, bitmapResource2);
    }

    private LazyBitmapDrawableResource(@NonNull Resources resources2, @NonNull Resource<Bitmap> bitmapResource2) {
        this.resources = (Resources) Preconditions.checkNotNull(resources2);
        this.bitmapResource = (Resource) Preconditions.checkNotNull(bitmapResource2);
    }

    @NonNull
    public Class<BitmapDrawable> getResourceClass() {
        return BitmapDrawable.class;
    }

    @NonNull
    public BitmapDrawable get() {
        return new BitmapDrawable(this.resources, this.bitmapResource.get());
    }

    public int getSize() {
        return this.bitmapResource.getSize();
    }

    public void recycle() {
        this.bitmapResource.recycle();
    }

    public void initialize() {
        if (this.bitmapResource instanceof Initializable) {
            ((Initializable) this.bitmapResource).initialize();
        }
    }
}
