package com.bumptech.glide.load;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

public final class ImageHeaderParserUtils {
    private static final int MARK_POSITION = 5242880;

    private ImageHeaderParserUtils() {
    }

    /* JADX INFO: finally extract failed */
    @NonNull
    public static ImageHeaderParser.ImageType getType(@NonNull List<ImageHeaderParser> parsers, @Nullable InputStream is, @NonNull ArrayPool byteArrayPool) throws IOException {
        if (is == null) {
            return ImageHeaderParser.ImageType.UNKNOWN;
        }
        if (!is.markSupported()) {
            is = new RecyclableBufferedInputStream(is, byteArrayPool);
        }
        is.mark(MARK_POSITION);
        int i = 0;
        int size = parsers.size();
        while (i < size) {
            try {
                ImageHeaderParser.ImageType type = parsers.get(i).getType(is);
                if (type != ImageHeaderParser.ImageType.UNKNOWN) {
                    is.reset();
                    return type;
                }
                is.reset();
                i++;
            } catch (Throwable th) {
                is.reset();
                throw th;
            }
        }
        return ImageHeaderParser.ImageType.UNKNOWN;
    }

    @NonNull
    public static ImageHeaderParser.ImageType getType(@NonNull List<ImageHeaderParser> parsers, @Nullable ByteBuffer buffer) throws IOException {
        if (buffer == null) {
            return ImageHeaderParser.ImageType.UNKNOWN;
        }
        int size = parsers.size();
        for (int i = 0; i < size; i++) {
            ImageHeaderParser.ImageType type = parsers.get(i).getType(buffer);
            if (type != ImageHeaderParser.ImageType.UNKNOWN) {
                return type;
            }
        }
        return ImageHeaderParser.ImageType.UNKNOWN;
    }

    /* JADX INFO: finally extract failed */
    public static int getOrientation(@NonNull List<ImageHeaderParser> parsers, @Nullable InputStream is, @NonNull ArrayPool byteArrayPool) throws IOException {
        if (is == null) {
            return -1;
        }
        if (!is.markSupported()) {
            is = new RecyclableBufferedInputStream(is, byteArrayPool);
        }
        is.mark(MARK_POSITION);
        int i = 0;
        int size = parsers.size();
        while (i < size) {
            try {
                int orientation = parsers.get(i).getOrientation(is, byteArrayPool);
                if (orientation != -1) {
                    is.reset();
                    return orientation;
                }
                is.reset();
                i++;
            } catch (Throwable th) {
                is.reset();
                throw th;
            }
        }
        return -1;
    }
}
