package com.bumptech.glide.load.resource.gif;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import com.bumptech.glide.Glide;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.gifdecoder.GifHeader;
import com.bumptech.glide.gifdecoder.GifHeaderParser;
import com.bumptech.glide.gifdecoder.StandardGifDecoder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ImageHeaderParserUtils;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.UnitTransformation;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Util;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Queue;

public class ByteBufferGifDecoder implements ResourceDecoder<ByteBuffer, GifDrawable> {
    private static final GifDecoderFactory GIF_DECODER_FACTORY = new GifDecoderFactory();
    private static final GifHeaderParserPool PARSER_POOL = new GifHeaderParserPool();
    private static final String TAG = "BufferGifDecoder";
    private final Context context;
    private final GifDecoderFactory gifDecoderFactory;
    private final GifHeaderParserPool parserPool;
    private final List<ImageHeaderParser> parsers;
    private final GifBitmapProvider provider;

    public ByteBufferGifDecoder(Context context2) {
        this(context2, Glide.get(context2).getRegistry().getImageHeaderParsers(), Glide.get(context2).getBitmapPool(), Glide.get(context2).getArrayPool());
    }

    public ByteBufferGifDecoder(Context context2, List<ImageHeaderParser> parsers2, BitmapPool bitmapPool, ArrayPool arrayPool) {
        this(context2, parsers2, bitmapPool, arrayPool, PARSER_POOL, GIF_DECODER_FACTORY);
    }

    ByteBufferGifDecoder(Context context2, List<ImageHeaderParser> parsers2, BitmapPool bitmapPool, ArrayPool arrayPool, GifHeaderParserPool parserPool2, GifDecoderFactory gifDecoderFactory2) {
        this.context = context2.getApplicationContext();
        this.parsers = parsers2;
        this.gifDecoderFactory = gifDecoderFactory2;
        this.provider = new GifBitmapProvider(bitmapPool, arrayPool);
        this.parserPool = parserPool2;
    }

    public boolean handles(ByteBuffer source, Options options) throws IOException {
        return !((Boolean) options.get(GifOptions.DISABLE_ANIMATION)).booleanValue() && ImageHeaderParserUtils.getType(this.parsers, source) == ImageHeaderParser.ImageType.GIF;
    }

    public GifDrawableResource decode(ByteBuffer source, int width, int height, Options options) {
        GifHeaderParser parser = this.parserPool.obtain(source);
        try {
            return decode(source, width, height, parser, options);
        } finally {
            this.parserPool.release(parser);
        }
    }

    private GifDrawableResource decode(ByteBuffer byteBuffer, int width, int height, GifHeaderParser parser, Options options) {
        long startTime = LogTime.getLogTime();
        try {
            GifHeader header = parser.parseHeader();
            if (header.getNumFrames() > 0) {
                if (header.getStatus() == 0) {
                    Bitmap.Config config = options.get(GifOptions.DECODE_FORMAT) == DecodeFormat.PREFER_RGB_565 ? Bitmap.Config.RGB_565 : Bitmap.Config.ARGB_8888;
                    int sampleSize = getSampleSize(header, width, height);
                    GifDecoder gifDecoder = this.gifDecoderFactory.build(this.provider, header, byteBuffer, sampleSize);
                    gifDecoder.setDefaultBitmapConfig(config);
                    gifDecoder.advance();
                    Bitmap firstFrame = gifDecoder.getNextFrame();
                    if (firstFrame != null) {
                        int i = sampleSize;
                        GifDrawableResource gifDrawableResource = new GifDrawableResource(new GifDrawable(this.context, gifDecoder, UnitTransformation.get(), width, height, firstFrame));
                        if (Log.isLoggable(TAG, 2)) {
                            Log.v(TAG, "Decoded GIF from stream in " + LogTime.getElapsedMillis(startTime));
                        }
                        return gifDrawableResource;
                    } else if (!Log.isLoggable(TAG, 2)) {
                        return null;
                    } else {
                        Log.v(TAG, "Decoded GIF from stream in " + LogTime.getElapsedMillis(startTime));
                        return null;
                    }
                }
            }
        } finally {
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "Decoded GIF from stream in " + LogTime.getElapsedMillis(startTime));
            }
        }
    }

    private static int getSampleSize(GifHeader gifHeader, int targetWidth, int targetHeight) {
        int exactSampleSize = Math.min(gifHeader.getHeight() / targetHeight, gifHeader.getWidth() / targetWidth);
        int sampleSize = Math.max(1, exactSampleSize == 0 ? 0 : Integer.highestOneBit(exactSampleSize));
        if (Log.isLoggable(TAG, 2) && sampleSize > 1) {
            Log.v(TAG, "Downsampling GIF, sampleSize: " + sampleSize + ", target dimens: [" + targetWidth + "x" + targetHeight + "], actual dimens: [" + gifHeader.getWidth() + "x" + gifHeader.getHeight() + "]");
        }
        return sampleSize;
    }

    static class GifDecoderFactory {
        GifDecoderFactory() {
        }

        /* access modifiers changed from: package-private */
        public GifDecoder build(GifDecoder.BitmapProvider provider, GifHeader header, ByteBuffer data, int sampleSize) {
            return new StandardGifDecoder(provider, header, data, sampleSize);
        }
    }

    static class GifHeaderParserPool {
        private final Queue<GifHeaderParser> pool = Util.createQueue(0);

        GifHeaderParserPool() {
        }

        /* access modifiers changed from: package-private */
        public synchronized GifHeaderParser obtain(ByteBuffer buffer) {
            GifHeaderParser result;
            result = this.pool.poll();
            if (result == null) {
                result = new GifHeaderParser();
            }
            return result.setData(buffer);
        }

        /* access modifiers changed from: package-private */
        public synchronized void release(GifHeaderParser parser) {
            parser.clear();
            this.pool.offer(parser);
        }
    }
}
