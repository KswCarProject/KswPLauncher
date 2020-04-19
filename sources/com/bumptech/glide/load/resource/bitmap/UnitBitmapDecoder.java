package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.util.Util;

public final class UnitBitmapDecoder implements ResourceDecoder<Bitmap, Bitmap> {
    public boolean handles(@NonNull Bitmap source, @NonNull Options options) {
        return true;
    }

    public Resource<Bitmap> decode(@NonNull Bitmap source, int width, int height, @NonNull Options options) {
        return new NonOwnedBitmapResource(source);
    }

    private static final class NonOwnedBitmapResource implements Resource<Bitmap> {
        private final Bitmap bitmap;

        NonOwnedBitmapResource(@NonNull Bitmap bitmap2) {
            this.bitmap = bitmap2;
        }

        @NonNull
        public Class<Bitmap> getResourceClass() {
            return Bitmap.class;
        }

        @NonNull
        public Bitmap get() {
            return this.bitmap;
        }

        public int getSize() {
            return Util.getBitmapByteSize(this.bitmap);
        }

        public void recycle() {
        }
    }
}
