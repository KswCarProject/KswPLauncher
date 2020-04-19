package com.bumptech.glide.gifdecoder;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;

public interface GifDecoder {
    public static final int STATUS_FORMAT_ERROR = 1;
    public static final int STATUS_OK = 0;
    public static final int STATUS_OPEN_ERROR = 2;
    public static final int STATUS_PARTIAL_DECODE = 3;
    public static final int TOTAL_ITERATION_COUNT_FOREVER = 0;

    public interface BitmapProvider {
        @NonNull
        Bitmap obtain(int i, int i2, @NonNull Bitmap.Config config);

        @NonNull
        byte[] obtainByteArray(int i);

        @NonNull
        int[] obtainIntArray(int i);

        void release(@NonNull Bitmap bitmap);

        void release(@NonNull byte[] bArr);

        void release(@NonNull int[] iArr);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface GifDecodeStatus {
    }

    void advance();

    void clear();

    int getByteSize();

    int getCurrentFrameIndex();

    @NonNull
    ByteBuffer getData();

    int getDelay(int i);

    int getFrameCount();

    int getHeight();

    @Deprecated
    int getLoopCount();

    int getNetscapeLoopCount();

    int getNextDelay();

    @Nullable
    Bitmap getNextFrame();

    int getStatus();

    int getTotalIterationCount();

    int getWidth();

    int read(@Nullable InputStream inputStream, int i);

    int read(@Nullable byte[] bArr);

    void resetFrameIndex();

    void setData(@NonNull GifHeader gifHeader, @NonNull ByteBuffer byteBuffer);

    void setData(@NonNull GifHeader gifHeader, @NonNull ByteBuffer byteBuffer, int i);

    void setData(@NonNull GifHeader gifHeader, @NonNull byte[] bArr);

    void setDefaultBitmapConfig(@NonNull Bitmap.Config config);
}
