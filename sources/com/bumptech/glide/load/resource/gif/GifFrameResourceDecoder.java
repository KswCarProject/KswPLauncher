package com.bumptech.glide.load.resource.gif;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;

public final class GifFrameResourceDecoder implements ResourceDecoder<GifDecoder, Bitmap> {
    private final BitmapPool bitmapPool;

    public GifFrameResourceDecoder(BitmapPool bitmapPool2) {
        this.bitmapPool = bitmapPool2;
    }

    public boolean handles(@NonNull GifDecoder source, @NonNull Options options) {
        return true;
    }

    public Resource<Bitmap> decode(@NonNull GifDecoder source, int width, int height, @NonNull Options options) {
        return BitmapResource.obtain(source.getNextFrame(), this.bitmapPool);
    }
}
