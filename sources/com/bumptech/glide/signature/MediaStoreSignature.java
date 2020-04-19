package com.bumptech.glide.signature;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.Key;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

public class MediaStoreSignature implements Key {
    private final long dateModified;
    @NonNull
    private final String mimeType;
    private final int orientation;

    public MediaStoreSignature(@Nullable String mimeType2, long dateModified2, int orientation2) {
        this.mimeType = mimeType2 == null ? "" : mimeType2;
        this.dateModified = dateModified2;
        this.orientation = orientation2;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MediaStoreSignature that = (MediaStoreSignature) o;
        if (this.dateModified == that.dateModified && this.orientation == that.orientation && this.mimeType.equals(that.mimeType)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((this.mimeType.hashCode() * 31) + ((int) (this.dateModified ^ (this.dateModified >>> 32)))) * 31) + this.orientation;
    }

    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(ByteBuffer.allocate(12).putLong(this.dateModified).putInt(this.orientation).array());
        messageDigest.update(this.mimeType.getBytes(CHARSET));
    }
}
