package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.Downsampler;
import com.bumptech.glide.util.ExceptionCatchingInputStream;
import com.bumptech.glide.util.MarkEnforcingInputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamBitmapDecoder implements ResourceDecoder<InputStream, Bitmap> {
    private final ArrayPool byteArrayPool;
    private final Downsampler downsampler;

    public StreamBitmapDecoder(Downsampler downsampler2, ArrayPool byteArrayPool2) {
        this.downsampler = downsampler2;
        this.byteArrayPool = byteArrayPool2;
    }

    public boolean handles(InputStream source, Options options) {
        return this.downsampler.handles(source);
    }

    public Resource<Bitmap> decode(InputStream source, int width, int height, Options options) throws IOException {
        boolean ownsBufferedStream;
        RecyclableBufferedInputStream bufferedStream;
        if (source instanceof RecyclableBufferedInputStream) {
            bufferedStream = (RecyclableBufferedInputStream) source;
            ownsBufferedStream = false;
        } else {
            bufferedStream = new RecyclableBufferedInputStream(source, this.byteArrayPool);
            ownsBufferedStream = true;
        }
        ExceptionCatchingInputStream exceptionStream = ExceptionCatchingInputStream.obtain(bufferedStream);
        try {
            return this.downsampler.decode(new MarkEnforcingInputStream(exceptionStream), width, height, options, new UntrustedCallbacks(bufferedStream, exceptionStream));
        } finally {
            exceptionStream.release();
            if (ownsBufferedStream) {
                bufferedStream.release();
            }
        }
    }

    static class UntrustedCallbacks implements Downsampler.DecodeCallbacks {
        private final RecyclableBufferedInputStream bufferedStream;
        private final ExceptionCatchingInputStream exceptionStream;

        UntrustedCallbacks(RecyclableBufferedInputStream bufferedStream2, ExceptionCatchingInputStream exceptionStream2) {
            this.bufferedStream = bufferedStream2;
            this.exceptionStream = exceptionStream2;
        }

        public void onObtainBounds() {
            this.bufferedStream.fixMarkLimit();
        }

        public void onDecodeComplete(BitmapPool bitmapPool, Bitmap downsampled) throws IOException {
            IOException streamException = this.exceptionStream.getException();
            if (streamException != null) {
                if (downsampled != null) {
                    bitmapPool.put(downsampled);
                }
                throw streamException;
            }
        }
    }
}
