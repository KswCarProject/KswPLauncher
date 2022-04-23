package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.util.LruCache;
import com.bumptech.glide.util.Util;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

final class ResourceCacheKey implements Key {
    private static final LruCache<Class<?>, byte[]> RESOURCE_CLASS_BYTES = new LruCache<>(50);
    private final ArrayPool arrayPool;
    private final Class<?> decodedResourceClass;
    private final int height;
    private final Options options;
    private final Key signature;
    private final Key sourceKey;
    private final Transformation<?> transformation;
    private final int width;

    ResourceCacheKey(ArrayPool arrayPool2, Key sourceKey2, Key signature2, int width2, int height2, Transformation<?> appliedTransformation, Class<?> decodedResourceClass2, Options options2) {
        this.arrayPool = arrayPool2;
        this.sourceKey = sourceKey2;
        this.signature = signature2;
        this.width = width2;
        this.height = height2;
        this.transformation = appliedTransformation;
        this.decodedResourceClass = decodedResourceClass2;
        this.options = options2;
    }

    public boolean equals(Object o) {
        if (!(o instanceof ResourceCacheKey)) {
            return false;
        }
        ResourceCacheKey other = (ResourceCacheKey) o;
        if (this.height != other.height || this.width != other.width || !Util.bothNullOrEqual(this.transformation, other.transformation) || !this.decodedResourceClass.equals(other.decodedResourceClass) || !this.sourceKey.equals(other.sourceKey) || !this.signature.equals(other.signature) || !this.options.equals(other.options)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = (((((this.sourceKey.hashCode() * 31) + this.signature.hashCode()) * 31) + this.width) * 31) + this.height;
        Transformation<?> transformation2 = this.transformation;
        if (transformation2 != null) {
            result = (result * 31) + transformation2.hashCode();
        }
        return (((result * 31) + this.decodedResourceClass.hashCode()) * 31) + this.options.hashCode();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        byte[] dimensions = (byte[]) this.arrayPool.getExact(8, byte[].class);
        ByteBuffer.wrap(dimensions).putInt(this.width).putInt(this.height).array();
        this.signature.updateDiskCacheKey(messageDigest);
        this.sourceKey.updateDiskCacheKey(messageDigest);
        messageDigest.update(dimensions);
        Transformation<?> transformation2 = this.transformation;
        if (transformation2 != null) {
            transformation2.updateDiskCacheKey(messageDigest);
        }
        this.options.updateDiskCacheKey(messageDigest);
        messageDigest.update(getResourceClassBytes());
        this.arrayPool.put(dimensions);
    }

    private byte[] getResourceClassBytes() {
        LruCache<Class<?>, byte[]> lruCache = RESOURCE_CLASS_BYTES;
        byte[] result = lruCache.get(this.decodedResourceClass);
        if (result != null) {
            return result;
        }
        byte[] result2 = this.decodedResourceClass.getName().getBytes(CHARSET);
        lruCache.put(this.decodedResourceClass, result2);
        return result2;
    }

    public String toString() {
        return "ResourceCacheKey{sourceKey=" + this.sourceKey + ", signature=" + this.signature + ", width=" + this.width + ", height=" + this.height + ", decodedResourceClass=" + this.decodedResourceClass + ", transformation='" + this.transformation + '\'' + ", options=" + this.options + '}';
    }
}
