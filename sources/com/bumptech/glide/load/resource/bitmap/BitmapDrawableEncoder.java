package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.io.File;

public class BitmapDrawableEncoder implements ResourceEncoder<BitmapDrawable> {
    private final BitmapPool bitmapPool;
    private final ResourceEncoder<Bitmap> encoder;

    public BitmapDrawableEncoder(BitmapPool bitmapPool2, ResourceEncoder<Bitmap> encoder2) {
        this.bitmapPool = bitmapPool2;
        this.encoder = encoder2;
    }

    public boolean encode(@NonNull Resource<BitmapDrawable> data, @NonNull File file, @NonNull Options options) {
        return this.encoder.encode(new BitmapResource(data.get().getBitmap(), this.bitmapPool), file, options);
    }

    @NonNull
    public EncodeStrategy getEncodeStrategy(@NonNull Options options) {
        return this.encoder.getEncodeStrategy(options);
    }
}
