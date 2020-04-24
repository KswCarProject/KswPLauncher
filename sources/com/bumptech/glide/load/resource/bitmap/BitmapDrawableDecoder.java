package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Preconditions;
import java.io.IOException;

public class BitmapDrawableDecoder<DataType> implements ResourceDecoder<DataType, BitmapDrawable> {
    private final ResourceDecoder<DataType, Bitmap> decoder;
    private final Resources resources;

    public BitmapDrawableDecoder(Context context, ResourceDecoder<DataType, Bitmap> decoder2) {
        this(context.getResources(), decoder2);
    }

    @Deprecated
    public BitmapDrawableDecoder(Resources resources2, BitmapPool bitmapPool, ResourceDecoder<DataType, Bitmap> decoder2) {
        this(resources2, decoder2);
    }

    public BitmapDrawableDecoder(@NonNull Resources resources2, @NonNull ResourceDecoder<DataType, Bitmap> decoder2) {
        this.resources = (Resources) Preconditions.checkNotNull(resources2);
        this.decoder = (ResourceDecoder) Preconditions.checkNotNull(decoder2);
    }

    public boolean handles(@NonNull DataType source, @NonNull Options options) throws IOException {
        return this.decoder.handles(source, options);
    }

    public Resource<BitmapDrawable> decode(@NonNull DataType source, int width, int height, @NonNull Options options) throws IOException {
        return LazyBitmapDrawableResource.obtain(this.resources, this.decoder.decode(source, width, height, options));
    }
}
