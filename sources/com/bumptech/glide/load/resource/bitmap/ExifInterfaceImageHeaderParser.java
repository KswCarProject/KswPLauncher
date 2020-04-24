package com.bumptech.glide.load.resource.bitmap;

import android.media.ExifInterface;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.util.ByteBufferUtil;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

@RequiresApi(27)
public final class ExifInterfaceImageHeaderParser implements ImageHeaderParser {
    @NonNull
    public ImageHeaderParser.ImageType getType(@NonNull InputStream is) throws IOException {
        return ImageHeaderParser.ImageType.UNKNOWN;
    }

    @NonNull
    public ImageHeaderParser.ImageType getType(@NonNull ByteBuffer byteBuffer) throws IOException {
        return ImageHeaderParser.ImageType.UNKNOWN;
    }

    public int getOrientation(@NonNull InputStream is, @NonNull ArrayPool byteArrayPool) throws IOException {
        int result = new ExifInterface(is).getAttributeInt("Orientation", 1);
        if (result == 0) {
            return -1;
        }
        return result;
    }

    public int getOrientation(@NonNull ByteBuffer byteBuffer, @NonNull ArrayPool byteArrayPool) throws IOException {
        return getOrientation(ByteBufferUtil.toStream(byteBuffer), byteArrayPool);
    }
}
