package com.bumptech.glide.load.resource.transcode;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.bytes.BytesResource;
import java.io.ByteArrayOutputStream;

public class BitmapBytesTranscoder implements ResourceTranscoder<Bitmap, byte[]> {
    private final Bitmap.CompressFormat compressFormat;
    private final int quality;

    public BitmapBytesTranscoder() {
        this(Bitmap.CompressFormat.JPEG, 100);
    }

    public BitmapBytesTranscoder(@NonNull Bitmap.CompressFormat compressFormat2, int quality2) {
        this.compressFormat = compressFormat2;
        this.quality = quality2;
    }

    @Nullable
    public Resource<byte[]> transcode(@NonNull Resource<Bitmap> toTranscode, @NonNull Options options) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        toTranscode.get().compress(this.compressFormat, this.quality, os);
        toTranscode.recycle();
        return new BytesResource(os.toByteArray());
    }
}
