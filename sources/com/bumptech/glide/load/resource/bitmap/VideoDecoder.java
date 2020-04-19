package com.bumptech.glide.load.resource.bitmap;

import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

public class VideoDecoder<T> implements ResourceDecoder<T, Bitmap> {
    private static final MediaMetadataRetrieverFactory DEFAULT_FACTORY = new MediaMetadataRetrieverFactory();
    public static final long DEFAULT_FRAME = -1;
    @VisibleForTesting
    static final int DEFAULT_FRAME_OPTION = 2;
    public static final Option<Integer> FRAME_OPTION = Option.disk("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.FrameOption", 2, new Option.CacheKeyUpdater<Integer>() {
        private final ByteBuffer buffer = ByteBuffer.allocate(4);

        public void update(@NonNull byte[] keyBytes, @NonNull Integer value, @NonNull MessageDigest messageDigest) {
            if (value != null) {
                messageDigest.update(keyBytes);
                synchronized (this.buffer) {
                    this.buffer.position(0);
                    messageDigest.update(this.buffer.putInt(value.intValue()).array());
                }
            }
        }
    });
    private static final String TAG = "VideoDecoder";
    public static final Option<Long> TARGET_FRAME = Option.disk("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.TargetFrame", -1L, new Option.CacheKeyUpdater<Long>() {
        private final ByteBuffer buffer = ByteBuffer.allocate(8);

        public void update(@NonNull byte[] keyBytes, @NonNull Long value, @NonNull MessageDigest messageDigest) {
            messageDigest.update(keyBytes);
            synchronized (this.buffer) {
                this.buffer.position(0);
                messageDigest.update(this.buffer.putLong(value.longValue()).array());
            }
        }
    });
    private final BitmapPool bitmapPool;
    private final MediaMetadataRetrieverFactory factory;
    private final MediaMetadataRetrieverInitializer<T> initializer;

    @VisibleForTesting
    interface MediaMetadataRetrieverInitializer<T> {
        void initialize(MediaMetadataRetriever mediaMetadataRetriever, T t);
    }

    public static ResourceDecoder<AssetFileDescriptor, Bitmap> asset(BitmapPool bitmapPool2) {
        return new VideoDecoder(bitmapPool2, new AssetFileDescriptorInitializer());
    }

    public static ResourceDecoder<ParcelFileDescriptor, Bitmap> parcel(BitmapPool bitmapPool2) {
        return new VideoDecoder(bitmapPool2, new ParcelFileDescriptorInitializer());
    }

    VideoDecoder(BitmapPool bitmapPool2, MediaMetadataRetrieverInitializer<T> initializer2) {
        this(bitmapPool2, initializer2, DEFAULT_FACTORY);
    }

    @VisibleForTesting
    VideoDecoder(BitmapPool bitmapPool2, MediaMetadataRetrieverInitializer<T> initializer2, MediaMetadataRetrieverFactory factory2) {
        this.bitmapPool = bitmapPool2;
        this.initializer = initializer2;
        this.factory = factory2;
    }

    public boolean handles(@NonNull T t, @NonNull Options options) {
        return true;
    }

    public Resource<Bitmap> decode(@NonNull T resource, int outWidth, int outHeight, @NonNull Options options) throws IOException {
        Options options2 = options;
        long frameTimeMicros = ((Long) options2.get(TARGET_FRAME)).longValue();
        if (frameTimeMicros >= 0 || frameTimeMicros == -1) {
            int frameOption = (Integer) options2.get(FRAME_OPTION);
            if (frameOption == null) {
                frameOption = 2;
            }
            Integer frameOption2 = frameOption;
            DownsampleStrategy downsampleStrategy = (DownsampleStrategy) options2.get(DownsampleStrategy.OPTION);
            if (downsampleStrategy == null) {
                downsampleStrategy = DownsampleStrategy.DEFAULT;
            }
            DownsampleStrategy downsampleStrategy2 = downsampleStrategy;
            MediaMetadataRetriever mediaMetadataRetriever = this.factory.build();
            try {
                try {
                    this.initializer.initialize(mediaMetadataRetriever, resource);
                    Bitmap result = decodeFrame(mediaMetadataRetriever, frameTimeMicros, frameOption2.intValue(), outWidth, outHeight, downsampleStrategy2);
                    mediaMetadataRetriever.release();
                    return BitmapResource.obtain(result, this.bitmapPool);
                } catch (RuntimeException e) {
                    e = e;
                    try {
                        throw new IOException(e);
                    } catch (Throwable th) {
                        e = th;
                        mediaMetadataRetriever.release();
                        throw e;
                    }
                }
            } catch (RuntimeException e2) {
                e = e2;
                T t = resource;
                throw new IOException(e);
            } catch (Throwable th2) {
                e = th2;
                T t2 = resource;
                mediaMetadataRetriever.release();
                throw e;
            }
        } else {
            throw new IllegalArgumentException("Requested frame must be non-negative, or DEFAULT_FRAME, given: " + frameTimeMicros);
        }
    }

    @Nullable
    private static Bitmap decodeFrame(MediaMetadataRetriever mediaMetadataRetriever, long frameTimeMicros, int frameOption, int outWidth, int outHeight, DownsampleStrategy strategy) {
        Bitmap result = null;
        if (!(Build.VERSION.SDK_INT < 27 || outWidth == Integer.MIN_VALUE || outHeight == Integer.MIN_VALUE || strategy == DownsampleStrategy.NONE)) {
            result = decodeScaledFrame(mediaMetadataRetriever, frameTimeMicros, frameOption, outWidth, outHeight, strategy);
        }
        if (result == null) {
            return decodeOriginalFrame(mediaMetadataRetriever, frameTimeMicros, frameOption);
        }
        return result;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    @android.annotation.TargetApi(27)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.graphics.Bitmap decodeScaledFrame(android.media.MediaMetadataRetriever r14, long r15, int r17, int r18, int r19, com.bumptech.glide.load.resource.bitmap.DownsampleStrategy r20) {
        /*
            r7 = r14
            r0 = 18
            java.lang.String r0 = r14.extractMetadata(r0)     // Catch:{ Throwable -> 0x0052 }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ Throwable -> 0x0052 }
            r1 = 19
            java.lang.String r1 = r14.extractMetadata(r1)     // Catch:{ Throwable -> 0x0052 }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ Throwable -> 0x0052 }
            r2 = 24
            java.lang.String r2 = r14.extractMetadata(r2)     // Catch:{ Throwable -> 0x0052 }
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ Throwable -> 0x0052 }
            r8 = r2
            r2 = 90
            if (r8 == r2) goto L_0x002b
            r2 = 270(0x10e, float:3.78E-43)
            if (r8 != r2) goto L_0x0029
            goto L_0x002b
        L_0x0029:
            r9 = r1
            goto L_0x002f
        L_0x002b:
            r2 = r0
            r0 = r1
            r1 = r2
            goto L_0x0029
        L_0x002f:
            r10 = r18
            r11 = r19
            r12 = r20
            float r1 = r12.getScaleFactor(r0, r9, r10, r11)     // Catch:{ Throwable -> 0x0050 }
            r13 = r1
            float r1 = (float) r0     // Catch:{ Throwable -> 0x0050 }
            float r1 = r1 * r13
            int r5 = java.lang.Math.round(r1)     // Catch:{ Throwable -> 0x0050 }
            float r1 = (float) r9     // Catch:{ Throwable -> 0x0050 }
            float r1 = r1 * r13
            int r6 = java.lang.Math.round(r1)     // Catch:{ Throwable -> 0x0050 }
            r1 = r14
            r2 = r15
            r4 = r17
            android.graphics.Bitmap r1 = r1.getScaledFrameAtTime(r2, r4, r5, r6)     // Catch:{ Throwable -> 0x0050 }
            return r1
        L_0x0050:
            r0 = move-exception
            goto L_0x0059
        L_0x0052:
            r0 = move-exception
            r10 = r18
            r11 = r19
            r12 = r20
        L_0x0059:
            java.lang.String r1 = "VideoDecoder"
            r2 = 3
            boolean r1 = android.util.Log.isLoggable(r1, r2)
            if (r1 == 0) goto L_0x0069
            java.lang.String r1 = "VideoDecoder"
            java.lang.String r2 = "Exception trying to decode frame on oreo+"
            android.util.Log.d(r1, r2, r0)
        L_0x0069:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.VideoDecoder.decodeScaledFrame(android.media.MediaMetadataRetriever, long, int, int, int, com.bumptech.glide.load.resource.bitmap.DownsampleStrategy):android.graphics.Bitmap");
    }

    private static Bitmap decodeOriginalFrame(MediaMetadataRetriever mediaMetadataRetriever, long frameTimeMicros, int frameOption) {
        return mediaMetadataRetriever.getFrameAtTime(frameTimeMicros, frameOption);
    }

    @VisibleForTesting
    static class MediaMetadataRetrieverFactory {
        MediaMetadataRetrieverFactory() {
        }

        public MediaMetadataRetriever build() {
            return new MediaMetadataRetriever();
        }
    }

    private static final class AssetFileDescriptorInitializer implements MediaMetadataRetrieverInitializer<AssetFileDescriptor> {
        private AssetFileDescriptorInitializer() {
        }

        public void initialize(MediaMetadataRetriever retriever, AssetFileDescriptor data) {
            retriever.setDataSource(data.getFileDescriptor(), data.getStartOffset(), data.getLength());
        }
    }

    static final class ParcelFileDescriptorInitializer implements MediaMetadataRetrieverInitializer<ParcelFileDescriptor> {
        ParcelFileDescriptorInitializer() {
        }

        public void initialize(MediaMetadataRetriever retriever, ParcelFileDescriptor data) {
            retriever.setDataSource(data.getFileDescriptor());
        }
    }
}
