package com.bumptech.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;

public class BitmapPoolAdapter implements BitmapPool {
    public long getMaxSize() {
        return 0;
    }

    public void setSizeMultiplier(float sizeMultiplier) {
    }

    public void put(Bitmap bitmap) {
        bitmap.recycle();
    }

    public Bitmap get(int width, int height, Bitmap.Config config) {
        return Bitmap.createBitmap(width, height, config);
    }

    public Bitmap getDirty(int width, int height, Bitmap.Config config) {
        return get(width, height, config);
    }

    public void clearMemory() {
    }

    public void trimMemory(int level) {
    }
}
