package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.Initializable;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;

public class BitmapResource implements Resource<Bitmap>, Initializable {
    private final Bitmap bitmap;
    private final BitmapPool bitmapPool;

    public static BitmapResource obtain(Bitmap bitmap2, BitmapPool bitmapPool2) {
        if (bitmap2 == null) {
            return null;
        }
        return new BitmapResource(bitmap2, bitmapPool2);
    }

    public BitmapResource(Bitmap bitmap2, BitmapPool bitmapPool2) {
        this.bitmap = (Bitmap) Preconditions.checkNotNull(bitmap2, "Bitmap must not be null");
        this.bitmapPool = (BitmapPool) Preconditions.checkNotNull(bitmapPool2, "BitmapPool must not be null");
    }

    public Class<Bitmap> getResourceClass() {
        return Bitmap.class;
    }

    public Bitmap get() {
        return this.bitmap;
    }

    public int getSize() {
        return Util.getBitmapByteSize(this.bitmap);
    }

    public void recycle() {
        this.bitmapPool.put(this.bitmap);
    }

    public void initialize() {
        this.bitmap.prepareToDraw();
    }
}
