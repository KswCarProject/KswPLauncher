package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Util;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

/* loaded from: classes.dex */
public class Rotate extends BitmapTransformation {

    /* renamed from: ID */
    private static final String f89ID = "com.bumptech.glide.load.resource.bitmap.Rotate";
    private static final byte[] ID_BYTES = f89ID.getBytes(CHARSET);
    private final int degreesToRotate;

    public Rotate(int degreesToRotate) {
        this.degreesToRotate = degreesToRotate;
    }

    @Override // com.bumptech.glide.load.resource.bitmap.BitmapTransformation
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return TransformationUtils.rotateImage(toTransform, this.degreesToRotate);
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object o) {
        if (o instanceof Rotate) {
            Rotate other = (Rotate) o;
            return this.degreesToRotate == other.degreesToRotate;
        }
        return false;
    }

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        return Util.hashCode(f89ID.hashCode(), Util.hashCode(this.degreesToRotate));
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
        byte[] degreesData = ByteBuffer.allocate(4).putInt(this.degreesToRotate).array();
        messageDigest.update(degreesData);
    }
}
