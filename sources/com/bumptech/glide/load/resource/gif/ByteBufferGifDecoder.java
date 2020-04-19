package com.bumptech.glide.load.resource.gif;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.bumptech.glide.Glide;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.gifdecoder.GifHeader;
import com.bumptech.glide.gifdecoder.GifHeaderParser;
import com.bumptech.glide.gifdecoder.StandardGifDecoder;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ImageHeaderParserUtils;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
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

    @VisibleForTesting
    ByteBufferGifDecoder(Context context2, List<ImageHeaderParser> parsers2, BitmapPool bitmapPool, ArrayPool arrayPool, GifHeaderParserPool parserPool2, GifDecoderFactory gifDecoderFactory2) {
        this.context = context2.getApplicationContext();
        this.parsers = parsers2;
        this.gifDecoderFactory = gifDecoderFactory2;
        this.provider = new GifBitmapProvider(bitmapPool, arrayPool);
        this.parserPool = parserPool2;
    }

    public boolean handles(@NonNull ByteBuffer source, @NonNull Options options) throws IOException {
        return !((Boolean) options.get(GifOptions.DISABLE_ANIMATION)).booleanValue() && ImageHeaderParserUtils.getType(this.parsers, source) == ImageHeaderParser.ImageType.GIF;
    }

    public GifDrawableResource decode(@NonNull ByteBuffer source, int width, int height, @NonNull Options options) {
        GifHeaderParser parser = this.parserPool.obtain(source);
        try {
            return decode(source, width, height, parser, options);
        } finally {
            this.parserPool.release(parser);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x00e1  */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.bumptech.glide.load.resource.gif.GifDrawableResource decode(java.nio.ByteBuffer r19, int r20, int r21, com.bumptech.glide.gifdecoder.GifHeaderParser r22, com.bumptech.glide.load.Options r23) {
        /*
            r18 = this;
            r1 = r18
            long r2 = com.bumptech.glide.util.LogTime.getLogTime()
            r4 = 2
            com.bumptech.glide.gifdecoder.GifHeader r0 = r22.parseHeader()     // Catch:{ all -> 0x00d6 }
            int r5 = r0.getNumFrames()     // Catch:{ all -> 0x00d6 }
            if (r5 <= 0) goto L_0x00b0
            int r5 = r0.getStatus()     // Catch:{ all -> 0x00d6 }
            if (r5 == 0) goto L_0x0019
            goto L_0x00b0
        L_0x0019:
            com.bumptech.glide.load.Option<com.bumptech.glide.load.DecodeFormat> r5 = com.bumptech.glide.load.resource.gif.GifOptions.DECODE_FORMAT     // Catch:{ all -> 0x00d6 }
            r7 = r23
            java.lang.Object r5 = r7.get(r5)     // Catch:{ all -> 0x00d6 }
            com.bumptech.glide.load.DecodeFormat r8 = com.bumptech.glide.load.DecodeFormat.PREFER_RGB_565     // Catch:{ all -> 0x00d6 }
            if (r5 != r8) goto L_0x0028
            android.graphics.Bitmap$Config r5 = android.graphics.Bitmap.Config.RGB_565     // Catch:{ all -> 0x00d6 }
            goto L_0x002a
        L_0x0028:
            android.graphics.Bitmap$Config r5 = android.graphics.Bitmap.Config.ARGB_8888     // Catch:{ all -> 0x00d6 }
        L_0x002a:
            r15 = r20
            r14 = r21
            int r8 = getSampleSize(r0, r15, r14)     // Catch:{ all -> 0x00ae }
            r13 = r8
            com.bumptech.glide.load.resource.gif.ByteBufferGifDecoder$GifDecoderFactory r8 = r1.gifDecoderFactory     // Catch:{ all -> 0x00ae }
            com.bumptech.glide.load.resource.gif.GifBitmapProvider r9 = r1.provider     // Catch:{ all -> 0x00ae }
            r12 = r19
            com.bumptech.glide.gifdecoder.GifDecoder r8 = r8.build(r9, r0, r12, r13)     // Catch:{ all -> 0x00ae }
            r10 = r8
            r10.setDefaultBitmapConfig(r5)     // Catch:{ all -> 0x00ae }
            r10.advance()     // Catch:{ all -> 0x00ae }
            android.graphics.Bitmap r8 = r10.getNextFrame()     // Catch:{ all -> 0x00ae }
            r16 = r8
            if (r16 != 0) goto L_0x0071
            java.lang.String r8 = "BufferGifDecoder"
            boolean r4 = android.util.Log.isLoggable(r8, r4)
            if (r4 == 0) goto L_0x006f
            java.lang.String r4 = "BufferGifDecoder"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "Decoded GIF from stream in "
            r8.append(r9)
            double r6 = com.bumptech.glide.util.LogTime.getElapsedMillis(r2)
            r8.append(r6)
            java.lang.String r6 = r8.toString()
            android.util.Log.v(r4, r6)
        L_0x006f:
            r4 = 0
            return r4
        L_0x0071:
            com.bumptech.glide.load.resource.UnitTransformation r11 = com.bumptech.glide.load.resource.UnitTransformation.get()     // Catch:{ all -> 0x00ae }
            com.bumptech.glide.load.resource.gif.GifDrawable r6 = new com.bumptech.glide.load.resource.gif.GifDrawable     // Catch:{ all -> 0x00ae }
            android.content.Context r9 = r1.context     // Catch:{ all -> 0x00ae }
            r8 = r6
            r7 = r10
            r12 = r20
            r17 = r13
            r13 = r21
            r14 = r16
            r8.<init>(r9, r10, r11, r12, r13, r14)     // Catch:{ all -> 0x00ae }
            com.bumptech.glide.load.resource.gif.GifDrawableResource r8 = new com.bumptech.glide.load.resource.gif.GifDrawableResource     // Catch:{ all -> 0x00ae }
            r8.<init>(r6)     // Catch:{ all -> 0x00ae }
            java.lang.String r9 = "BufferGifDecoder"
            boolean r4 = android.util.Log.isLoggable(r9, r4)
            if (r4 == 0) goto L_0x00ad
            java.lang.String r4 = "BufferGifDecoder"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "Decoded GIF from stream in "
            r9.append(r10)
            double r12 = com.bumptech.glide.util.LogTime.getElapsedMillis(r2)
            r9.append(r12)
            java.lang.String r9 = r9.toString()
            android.util.Log.v(r4, r9)
        L_0x00ad:
            return r8
        L_0x00ae:
            r0 = move-exception
            goto L_0x00d9
        L_0x00b0:
            r15 = r20
            java.lang.String r5 = "BufferGifDecoder"
            boolean r4 = android.util.Log.isLoggable(r5, r4)
            if (r4 == 0) goto L_0x00d4
            java.lang.String r4 = "BufferGifDecoder"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Decoded GIF from stream in "
            r5.append(r6)
            double r6 = com.bumptech.glide.util.LogTime.getElapsedMillis(r2)
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            android.util.Log.v(r4, r5)
        L_0x00d4:
            r4 = 0
            return r4
        L_0x00d6:
            r0 = move-exception
            r15 = r20
        L_0x00d9:
            java.lang.String r5 = "BufferGifDecoder"
            boolean r4 = android.util.Log.isLoggable(r5, r4)
            if (r4 == 0) goto L_0x00fb
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Decoded GIF from stream in "
            r4.append(r5)
            double r5 = com.bumptech.glide.util.LogTime.getElapsedMillis(r2)
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            java.lang.String r5 = "BufferGifDecoder"
            android.util.Log.v(r5, r4)
        L_0x00fb:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.gif.ByteBufferGifDecoder.decode(java.nio.ByteBuffer, int, int, com.bumptech.glide.gifdecoder.GifHeaderParser, com.bumptech.glide.load.Options):com.bumptech.glide.load.resource.gif.GifDrawableResource");
    }

    private static int getSampleSize(GifHeader gifHeader, int targetWidth, int targetHeight) {
        int exactSampleSize = Math.min(gifHeader.getHeight() / targetHeight, gifHeader.getWidth() / targetWidth);
        int sampleSize = Math.max(1, exactSampleSize == 0 ? 0 : Integer.highestOneBit(exactSampleSize));
        if (Log.isLoggable(TAG, 2) && sampleSize > 1) {
            Log.v(TAG, "Downsampling GIF, sampleSize: " + sampleSize + ", target dimens: [" + targetWidth + "x" + targetHeight + "], actual dimens: [" + gifHeader.getWidth() + "x" + gifHeader.getHeight() + "]");
        }
        return sampleSize;
    }

    @VisibleForTesting
    static class GifDecoderFactory {
        GifDecoderFactory() {
        }

        /* access modifiers changed from: package-private */
        public GifDecoder build(GifDecoder.BitmapProvider provider, GifHeader header, ByteBuffer data, int sampleSize) {
            return new StandardGifDecoder(provider, header, data, sampleSize);
        }
    }

    @VisibleForTesting
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
