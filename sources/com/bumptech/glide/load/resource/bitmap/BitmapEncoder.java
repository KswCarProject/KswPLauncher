package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.util.Log;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.data.BufferedOutputStream;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Util;
import com.bumptech.glide.util.pool.GlideTrace;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BitmapEncoder implements ResourceEncoder<Bitmap> {
    public static final Option<Bitmap.CompressFormat> COMPRESSION_FORMAT = Option.memory("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionFormat");
    public static final Option<Integer> COMPRESSION_QUALITY = Option.memory("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionQuality", 90);
    private static final String TAG = "BitmapEncoder";
    private final ArrayPool arrayPool;

    public BitmapEncoder(ArrayPool arrayPool2) {
        this.arrayPool = arrayPool2;
    }

    @Deprecated
    public BitmapEncoder() {
        this.arrayPool = null;
    }

    public boolean encode(Resource<Bitmap> resource, File file, Options options) {
        OutputStream os;
        Bitmap bitmap = resource.get();
        Bitmap.CompressFormat format = getFormat(bitmap, options);
        GlideTrace.beginSectionFormat("encode: [%dx%d] %s", Integer.valueOf(bitmap.getWidth()), Integer.valueOf(bitmap.getHeight()), format);
        try {
            long start = LogTime.getLogTime();
            int quality = ((Integer) options.get(COMPRESSION_QUALITY)).intValue();
            boolean success = false;
            os = null;
            try {
                OutputStream os2 = new FileOutputStream(file);
                if (this.arrayPool != null) {
                    os2 = new BufferedOutputStream(os2, this.arrayPool);
                }
                bitmap.compress(format, quality, os2);
                os2.close();
                success = true;
                if (os2 != null) {
                    try {
                        os2.close();
                    } catch (IOException e) {
                    }
                }
            } catch (IOException e2) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Failed to encode Bitmap", e2);
                }
                if (os != null) {
                    os.close();
                }
            }
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "Compressed with type: " + format + " of size " + Util.getBitmapByteSize(bitmap) + " in " + LogTime.getElapsedMillis(start) + ", options format: " + options.get(COMPRESSION_FORMAT) + ", hasAlpha: " + bitmap.hasAlpha());
            }
            GlideTrace.endSection();
            return success;
        } catch (Throwable th) {
            GlideTrace.endSection();
            throw th;
        }
    }

    private Bitmap.CompressFormat getFormat(Bitmap bitmap, Options options) {
        Bitmap.CompressFormat format = (Bitmap.CompressFormat) options.get(COMPRESSION_FORMAT);
        if (format != null) {
            return format;
        }
        if (bitmap.hasAlpha()) {
            return Bitmap.CompressFormat.PNG;
        }
        return Bitmap.CompressFormat.JPEG;
    }

    public EncodeStrategy getEncodeStrategy(Options options) {
        return EncodeStrategy.TRANSFORMED;
    }
}
