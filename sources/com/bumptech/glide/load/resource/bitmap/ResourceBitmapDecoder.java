package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.drawable.ResourceDrawableDecoder;

public class ResourceBitmapDecoder implements ResourceDecoder<Uri, Bitmap> {
    private final BitmapPool bitmapPool;
    private final ResourceDrawableDecoder drawableDecoder;

    public ResourceBitmapDecoder(ResourceDrawableDecoder drawableDecoder2, BitmapPool bitmapPool2) {
        this.drawableDecoder = drawableDecoder2;
        this.bitmapPool = bitmapPool2;
    }

    public boolean handles(Uri source, Options options) {
        return "android.resource".equals(source.getScheme());
    }

    public Resource<Bitmap> decode(Uri source, int width, int height, Options options) {
        Resource<Drawable> drawableResource = this.drawableDecoder.decode(source, width, height, options);
        if (drawableResource == null) {
            return null;
        }
        return DrawableToBitmapConverter.convert(this.bitmapPool, drawableResource.get(), width, height);
    }
}
