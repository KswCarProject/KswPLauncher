package com.bumptech.glide.load.resource.transcode;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.bumptech.glide.load.resource.gif.GifDrawable;

public final class DrawableBytesTranscoder implements ResourceTranscoder<Drawable, byte[]> {
    private final ResourceTranscoder<Bitmap, byte[]> bitmapBytesTranscoder;
    private final BitmapPool bitmapPool;
    private final ResourceTranscoder<GifDrawable, byte[]> gifDrawableBytesTranscoder;

    public DrawableBytesTranscoder(@NonNull BitmapPool bitmapPool2, @NonNull ResourceTranscoder<Bitmap, byte[]> bitmapBytesTranscoder2, @NonNull ResourceTranscoder<GifDrawable, byte[]> gifDrawableBytesTranscoder2) {
        this.bitmapPool = bitmapPool2;
        this.bitmapBytesTranscoder = bitmapBytesTranscoder2;
        this.gifDrawableBytesTranscoder = gifDrawableBytesTranscoder2;
    }

    @Nullable
    public Resource<byte[]> transcode(@NonNull Resource<Drawable> toTranscode, @NonNull Options options) {
        Drawable drawable = toTranscode.get();
        if (drawable instanceof BitmapDrawable) {
            return this.bitmapBytesTranscoder.transcode(BitmapResource.obtain(((BitmapDrawable) drawable).getBitmap(), this.bitmapPool), options);
        }
        if (drawable instanceof GifDrawable) {
            return this.gifDrawableBytesTranscoder.transcode(toGifDrawableResource(toTranscode), options);
        }
        return null;
    }

    @NonNull
    private static Resource<GifDrawable> toGifDrawableResource(@NonNull Resource<Drawable> resource) {
        return resource;
    }
}
