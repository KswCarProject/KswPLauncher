package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.util.Util;

public final class UnitBitmapDecoder implements ResourceDecoder<Bitmap, Bitmap> {
    public boolean handles(Bitmap source, Options options) {
        return true;
    }

    public Resource<Bitmap> decode(Bitmap source, int width, int height, Options options) {
        return new NonOwnedBitmapResource(source);
    }

    private static final class NonOwnedBitmapResource implements Resource<Bitmap> {
        private final Bitmap bitmap;

        NonOwnedBitmapResource(Bitmap bitmap2) {
            this.bitmap = bitmap2;
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
        }
    }
}
