package com.bumptech.glide.request;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.stream.HttpGlideUrlLoader;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.load.resource.bitmap.Downsampler;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.VideoDecoder;
import com.bumptech.glide.load.resource.gif.GifOptions;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.signature.EmptySignature;
import com.bumptech.glide.util.CachedHashCodeArrayMap;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.util.Map;

public abstract class BaseRequestOptions<T extends BaseRequestOptions<T>> implements Cloneable {
    private static final int DISK_CACHE_STRATEGY = 4;
    private static final int ERROR_ID = 32;
    private static final int ERROR_PLACEHOLDER = 16;
    private static final int FALLBACK = 8192;
    private static final int FALLBACK_ID = 16384;
    private static final int IS_CACHEABLE = 256;
    private static final int ONLY_RETRIEVE_FROM_CACHE = 524288;
    private static final int OVERRIDE = 512;
    private static final int PLACEHOLDER = 64;
    private static final int PLACEHOLDER_ID = 128;
    private static final int PRIORITY = 8;
    private static final int RESOURCE_CLASS = 4096;
    private static final int SIGNATURE = 1024;
    private static final int SIZE_MULTIPLIER = 2;
    private static final int THEME = 32768;
    private static final int TRANSFORMATION = 2048;
    private static final int TRANSFORMATION_ALLOWED = 65536;
    private static final int TRANSFORMATION_REQUIRED = 131072;
    private static final int UNSET = -1;
    private static final int USE_ANIMATION_POOL = 1048576;
    private static final int USE_UNLIMITED_SOURCE_GENERATORS_POOL = 262144;
    private DiskCacheStrategy diskCacheStrategy = DiskCacheStrategy.AUTOMATIC;
    private int errorId;
    private Drawable errorPlaceholder;
    private Drawable fallbackDrawable;
    private int fallbackId;
    private int fields;
    private boolean isAutoCloneEnabled;
    private boolean isCacheable = true;
    private boolean isLocked;
    private boolean isScaleOnlyOrNoTransform = true;
    private boolean isTransformationAllowed = true;
    private boolean isTransformationRequired;
    private boolean onlyRetrieveFromCache;
    private Options options = new Options();
    private int overrideHeight = -1;
    private int overrideWidth = -1;
    private Drawable placeholderDrawable;
    private int placeholderId;
    private Priority priority = Priority.NORMAL;
    private Class<?> resourceClass = Object.class;
    private Key signature = EmptySignature.obtain();
    private float sizeMultiplier = 1.0f;
    private Resources.Theme theme;
    private Map<Class<?>, Transformation<?>> transformations = new CachedHashCodeArrayMap();
    private boolean useAnimationPool;
    private boolean useUnlimitedSourceGeneratorsPool;

    private static boolean isSet(int fields2, int flag) {
        return (fields2 & flag) != 0;
    }

    public T sizeMultiplier(float sizeMultiplier2) {
        if (this.isAutoCloneEnabled) {
            return clone().sizeMultiplier(sizeMultiplier2);
        }
        if (sizeMultiplier2 < 0.0f || sizeMultiplier2 > 1.0f) {
            throw new IllegalArgumentException("sizeMultiplier must be between 0 and 1");
        }
        this.sizeMultiplier = sizeMultiplier2;
        this.fields |= 2;
        return selfOrThrowIfLocked();
    }

    public T useUnlimitedSourceGeneratorsPool(boolean flag) {
        if (this.isAutoCloneEnabled) {
            return clone().useUnlimitedSourceGeneratorsPool(flag);
        }
        this.useUnlimitedSourceGeneratorsPool = flag;
        this.fields |= 262144;
        return selfOrThrowIfLocked();
    }

    public T useAnimationPool(boolean flag) {
        if (this.isAutoCloneEnabled) {
            return clone().useAnimationPool(flag);
        }
        this.useAnimationPool = flag;
        this.fields |= 1048576;
        return selfOrThrowIfLocked();
    }

    public T onlyRetrieveFromCache(boolean flag) {
        if (this.isAutoCloneEnabled) {
            return clone().onlyRetrieveFromCache(flag);
        }
        this.onlyRetrieveFromCache = flag;
        this.fields |= 524288;
        return selfOrThrowIfLocked();
    }

    public T diskCacheStrategy(DiskCacheStrategy strategy) {
        if (this.isAutoCloneEnabled) {
            return clone().diskCacheStrategy(strategy);
        }
        this.diskCacheStrategy = (DiskCacheStrategy) Preconditions.checkNotNull(strategy);
        this.fields |= 4;
        return selfOrThrowIfLocked();
    }

    public T priority(Priority priority2) {
        if (this.isAutoCloneEnabled) {
            return clone().priority(priority2);
        }
        this.priority = (Priority) Preconditions.checkNotNull(priority2);
        this.fields |= 8;
        return selfOrThrowIfLocked();
    }

    public T placeholder(Drawable drawable) {
        if (this.isAutoCloneEnabled) {
            return clone().placeholder(drawable);
        }
        this.placeholderDrawable = drawable;
        int i = this.fields | 64;
        this.fields = i;
        this.placeholderId = 0;
        this.fields = i & -129;
        return selfOrThrowIfLocked();
    }

    public T placeholder(int resourceId) {
        if (this.isAutoCloneEnabled) {
            return clone().placeholder(resourceId);
        }
        this.placeholderId = resourceId;
        int i = this.fields | 128;
        this.fields = i;
        this.placeholderDrawable = null;
        this.fields = i & -65;
        return selfOrThrowIfLocked();
    }

    public T fallback(Drawable drawable) {
        if (this.isAutoCloneEnabled) {
            return clone().fallback(drawable);
        }
        this.fallbackDrawable = drawable;
        int i = this.fields | 8192;
        this.fields = i;
        this.fallbackId = 0;
        this.fields = i & -16385;
        return selfOrThrowIfLocked();
    }

    public T fallback(int resourceId) {
        if (this.isAutoCloneEnabled) {
            return clone().fallback(resourceId);
        }
        this.fallbackId = resourceId;
        int i = this.fields | 16384;
        this.fields = i;
        this.fallbackDrawable = null;
        this.fields = i & -8193;
        return selfOrThrowIfLocked();
    }

    public T error(Drawable drawable) {
        if (this.isAutoCloneEnabled) {
            return clone().error(drawable);
        }
        this.errorPlaceholder = drawable;
        int i = this.fields | 16;
        this.fields = i;
        this.errorId = 0;
        this.fields = i & -33;
        return selfOrThrowIfLocked();
    }

    public T error(int resourceId) {
        if (this.isAutoCloneEnabled) {
            return clone().error(resourceId);
        }
        this.errorId = resourceId;
        int i = this.fields | 32;
        this.fields = i;
        this.errorPlaceholder = null;
        this.fields = i & -17;
        return selfOrThrowIfLocked();
    }

    public T theme(Resources.Theme theme2) {
        if (this.isAutoCloneEnabled) {
            return clone().theme(theme2);
        }
        this.theme = theme2;
        this.fields |= 32768;
        return selfOrThrowIfLocked();
    }

    public T skipMemoryCache(boolean skip) {
        if (this.isAutoCloneEnabled) {
            return clone().skipMemoryCache(true);
        }
        this.isCacheable = !skip;
        this.fields |= 256;
        return selfOrThrowIfLocked();
    }

    public T override(int width, int height) {
        if (this.isAutoCloneEnabled) {
            return clone().override(width, height);
        }
        this.overrideWidth = width;
        this.overrideHeight = height;
        this.fields |= 512;
        return selfOrThrowIfLocked();
    }

    public T override(int size) {
        return override(size, size);
    }

    public T signature(Key signature2) {
        if (this.isAutoCloneEnabled) {
            return clone().signature(signature2);
        }
        this.signature = (Key) Preconditions.checkNotNull(signature2);
        this.fields |= 1024;
        return selfOrThrowIfLocked();
    }

    public T clone() {
        try {
            BaseRequestOptions<?> result = (BaseRequestOptions) super.clone();
            Options options2 = new Options();
            result.options = options2;
            options2.putAll(this.options);
            CachedHashCodeArrayMap cachedHashCodeArrayMap = new CachedHashCodeArrayMap();
            result.transformations = cachedHashCodeArrayMap;
            cachedHashCodeArrayMap.putAll(this.transformations);
            result.isLocked = false;
            result.isAutoCloneEnabled = false;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.bumptech.glide.load.Option, java.lang.Object, com.bumptech.glide.load.Option<Y>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <Y> T set(com.bumptech.glide.load.Option<Y> r2, Y r3) {
        /*
            r1 = this;
            boolean r0 = r1.isAutoCloneEnabled
            if (r0 == 0) goto L_0x000d
            com.bumptech.glide.request.BaseRequestOptions r0 = r1.clone()
            com.bumptech.glide.request.BaseRequestOptions r0 = r0.set(r2, r3)
            return r0
        L_0x000d:
            com.bumptech.glide.util.Preconditions.checkNotNull(r2)
            com.bumptech.glide.util.Preconditions.checkNotNull(r3)
            com.bumptech.glide.load.Options r0 = r1.options
            r0.set(r2, r3)
            com.bumptech.glide.request.BaseRequestOptions r0 = r1.selfOrThrowIfLocked()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.set(com.bumptech.glide.load.Option, java.lang.Object):com.bumptech.glide.request.BaseRequestOptions");
    }

    public T decode(Class<?> resourceClass2) {
        if (this.isAutoCloneEnabled) {
            return clone().decode(resourceClass2);
        }
        this.resourceClass = (Class) Preconditions.checkNotNull(resourceClass2);
        this.fields |= 4096;
        return selfOrThrowIfLocked();
    }

    public final boolean isTransformationAllowed() {
        return this.isTransformationAllowed;
    }

    public final boolean isTransformationSet() {
        return isSet(2048);
    }

    public final boolean isLocked() {
        return this.isLocked;
    }

    public T encodeFormat(Bitmap.CompressFormat format) {
        return set(BitmapEncoder.COMPRESSION_FORMAT, Preconditions.checkNotNull(format));
    }

    public T encodeQuality(int quality) {
        return set(BitmapEncoder.COMPRESSION_QUALITY, Integer.valueOf(quality));
    }

    public T frame(long frameTimeMicros) {
        return set(VideoDecoder.TARGET_FRAME, Long.valueOf(frameTimeMicros));
    }

    public T format(DecodeFormat format) {
        Preconditions.checkNotNull(format);
        return set(Downsampler.DECODE_FORMAT, format).set(GifOptions.DECODE_FORMAT, format);
    }

    public T disallowHardwareConfig() {
        return set(Downsampler.ALLOW_HARDWARE_CONFIG, false);
    }

    public T downsample(DownsampleStrategy strategy) {
        return set(DownsampleStrategy.OPTION, Preconditions.checkNotNull(strategy));
    }

    public T timeout(int timeoutMs) {
        return set(HttpGlideUrlLoader.TIMEOUT, Integer.valueOf(timeoutMs));
    }

    public T optionalCenterCrop() {
        return optionalTransform(DownsampleStrategy.CENTER_OUTSIDE, (Transformation<Bitmap>) new CenterCrop());
    }

    public T centerCrop() {
        return transform(DownsampleStrategy.CENTER_OUTSIDE, (Transformation<Bitmap>) new CenterCrop());
    }

    public T optionalFitCenter() {
        return optionalScaleOnlyTransform(DownsampleStrategy.FIT_CENTER, new FitCenter());
    }

    public T fitCenter() {
        return scaleOnlyTransform(DownsampleStrategy.FIT_CENTER, new FitCenter());
    }

    public T optionalCenterInside() {
        return optionalScaleOnlyTransform(DownsampleStrategy.CENTER_INSIDE, new CenterInside());
    }

    public T centerInside() {
        return scaleOnlyTransform(DownsampleStrategy.CENTER_INSIDE, new CenterInside());
    }

    public T optionalCircleCrop() {
        return optionalTransform(DownsampleStrategy.CENTER_OUTSIDE, (Transformation<Bitmap>) new CircleCrop());
    }

    public T circleCrop() {
        return transform(DownsampleStrategy.CENTER_INSIDE, (Transformation<Bitmap>) new CircleCrop());
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.bumptech.glide.load.Transformation, com.bumptech.glide.load.Transformation<android.graphics.Bitmap>] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final T optionalTransform(com.bumptech.glide.load.resource.bitmap.DownsampleStrategy r2, com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r3) {
        /*
            r1 = this;
            boolean r0 = r1.isAutoCloneEnabled
            if (r0 == 0) goto L_0x000d
            com.bumptech.glide.request.BaseRequestOptions r0 = r1.clone()
            com.bumptech.glide.request.BaseRequestOptions r0 = r0.optionalTransform((com.bumptech.glide.load.resource.bitmap.DownsampleStrategy) r2, (com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r3)
            return r0
        L_0x000d:
            r1.downsample(r2)
            r0 = 0
            com.bumptech.glide.request.BaseRequestOptions r0 = r1.transform((com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r3, (boolean) r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.optionalTransform(com.bumptech.glide.load.resource.bitmap.DownsampleStrategy, com.bumptech.glide.load.Transformation):com.bumptech.glide.request.BaseRequestOptions");
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.bumptech.glide.load.Transformation, com.bumptech.glide.load.Transformation<android.graphics.Bitmap>] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final T transform(com.bumptech.glide.load.resource.bitmap.DownsampleStrategy r2, com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r3) {
        /*
            r1 = this;
            boolean r0 = r1.isAutoCloneEnabled
            if (r0 == 0) goto L_0x000d
            com.bumptech.glide.request.BaseRequestOptions r0 = r1.clone()
            com.bumptech.glide.request.BaseRequestOptions r0 = r0.transform((com.bumptech.glide.load.resource.bitmap.DownsampleStrategy) r2, (com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r3)
            return r0
        L_0x000d:
            r1.downsample(r2)
            com.bumptech.glide.request.BaseRequestOptions r0 = r1.transform((com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.transform(com.bumptech.glide.load.resource.bitmap.DownsampleStrategy, com.bumptech.glide.load.Transformation):com.bumptech.glide.request.BaseRequestOptions");
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.bumptech.glide.load.Transformation, com.bumptech.glide.load.Transformation<android.graphics.Bitmap>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private T scaleOnlyTransform(com.bumptech.glide.load.resource.bitmap.DownsampleStrategy r2, com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r3) {
        /*
            r1 = this;
            r0 = 1
            com.bumptech.glide.request.BaseRequestOptions r0 = r1.scaleOnlyTransform(r2, r3, r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.scaleOnlyTransform(com.bumptech.glide.load.resource.bitmap.DownsampleStrategy, com.bumptech.glide.load.Transformation):com.bumptech.glide.request.BaseRequestOptions");
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.bumptech.glide.load.Transformation, com.bumptech.glide.load.Transformation<android.graphics.Bitmap>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private T optionalScaleOnlyTransform(com.bumptech.glide.load.resource.bitmap.DownsampleStrategy r2, com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r3) {
        /*
            r1 = this;
            r0 = 0
            com.bumptech.glide.request.BaseRequestOptions r0 = r1.scaleOnlyTransform(r2, r3, r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.optionalScaleOnlyTransform(com.bumptech.glide.load.resource.bitmap.DownsampleStrategy, com.bumptech.glide.load.Transformation):com.bumptech.glide.request.BaseRequestOptions");
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [com.bumptech.glide.load.Transformation, com.bumptech.glide.load.Transformation<android.graphics.Bitmap>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private T scaleOnlyTransform(com.bumptech.glide.load.resource.bitmap.DownsampleStrategy r3, com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r4, boolean r5) {
        /*
            r2 = this;
            if (r5 == 0) goto L_0x0007
            com.bumptech.glide.request.BaseRequestOptions r0 = r2.transform((com.bumptech.glide.load.resource.bitmap.DownsampleStrategy) r3, (com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r4)
            goto L_0x000b
        L_0x0007:
            com.bumptech.glide.request.BaseRequestOptions r0 = r2.optionalTransform((com.bumptech.glide.load.resource.bitmap.DownsampleStrategy) r3, (com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r4)
        L_0x000b:
            r1 = 1
            r0.isScaleOnlyOrNoTransform = r1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.scaleOnlyTransform(com.bumptech.glide.load.resource.bitmap.DownsampleStrategy, com.bumptech.glide.load.Transformation, boolean):com.bumptech.glide.request.BaseRequestOptions");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.bumptech.glide.load.Transformation, com.bumptech.glide.load.Transformation<android.graphics.Bitmap>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T transform(com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r2) {
        /*
            r1 = this;
            r0 = 1
            com.bumptech.glide.request.BaseRequestOptions r0 = r1.transform((com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r2, (boolean) r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.transform(com.bumptech.glide.load.Transformation):com.bumptech.glide.request.BaseRequestOptions");
    }

    public T transform(Transformation<Bitmap>... transformations2) {
        if (transformations2.length > 1) {
            return transform((Transformation<Bitmap>) new MultiTransformation((Transformation<T>[]) transformations2), true);
        }
        if (transformations2.length == 1) {
            return transform((Transformation<Bitmap>) transformations2[0]);
        }
        return selfOrThrowIfLocked();
    }

    @Deprecated
    public T transforms(Transformation<Bitmap>... transformations2) {
        return transform((Transformation<Bitmap>) new MultiTransformation((Transformation<T>[]) transformations2), true);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.bumptech.glide.load.Transformation, com.bumptech.glide.load.Transformation<android.graphics.Bitmap>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T optionalTransform(com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r2) {
        /*
            r1 = this;
            r0 = 0
            com.bumptech.glide.request.BaseRequestOptions r0 = r1.transform((com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r2, (boolean) r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.optionalTransform(com.bumptech.glide.load.Transformation):com.bumptech.glide.request.BaseRequestOptions");
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [com.bumptech.glide.load.Transformation, com.bumptech.glide.load.Transformation<android.graphics.Bitmap>] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T transform(com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r4, boolean r5) {
        /*
            r3 = this;
            boolean r0 = r3.isAutoCloneEnabled
            if (r0 == 0) goto L_0x000d
            com.bumptech.glide.request.BaseRequestOptions r0 = r3.clone()
            com.bumptech.glide.request.BaseRequestOptions r0 = r0.transform((com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r4, (boolean) r5)
            return r0
        L_0x000d:
            com.bumptech.glide.load.resource.bitmap.DrawableTransformation r0 = new com.bumptech.glide.load.resource.bitmap.DrawableTransformation
            r0.<init>(r4, r5)
            java.lang.Class<android.graphics.Bitmap> r1 = android.graphics.Bitmap.class
            r3.transform(r1, r4, r5)
            java.lang.Class<android.graphics.drawable.Drawable> r1 = android.graphics.drawable.Drawable.class
            r3.transform(r1, r0, r5)
            java.lang.Class<android.graphics.drawable.BitmapDrawable> r1 = android.graphics.drawable.BitmapDrawable.class
            com.bumptech.glide.load.Transformation r2 = r0.asBitmapDrawable()
            r3.transform(r1, r2, r5)
            java.lang.Class<com.bumptech.glide.load.resource.gif.GifDrawable> r1 = com.bumptech.glide.load.resource.gif.GifDrawable.class
            com.bumptech.glide.load.resource.gif.GifDrawableTransformation r2 = new com.bumptech.glide.load.resource.gif.GifDrawableTransformation
            r2.<init>(r4)
            r3.transform(r1, r2, r5)
            com.bumptech.glide.request.BaseRequestOptions r1 = r3.selfOrThrowIfLocked()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.transform(com.bumptech.glide.load.Transformation, boolean):com.bumptech.glide.request.BaseRequestOptions");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.Class<Y>, java.lang.Class] */
    /* JADX WARNING: type inference failed for: r3v0, types: [com.bumptech.glide.load.Transformation<Y>, com.bumptech.glide.load.Transformation] */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <Y> T optionalTransform(java.lang.Class<Y> r2, com.bumptech.glide.load.Transformation<Y> r3) {
        /*
            r1 = this;
            r0 = 0
            com.bumptech.glide.request.BaseRequestOptions r0 = r1.transform(r2, r3, r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.optionalTransform(java.lang.Class, com.bumptech.glide.load.Transformation):com.bumptech.glide.request.BaseRequestOptions");
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [java.lang.Class<Y>, java.lang.Object, java.lang.Class] */
    /* JADX WARNING: type inference failed for: r5v0, types: [com.bumptech.glide.load.Transformation<Y>, com.bumptech.glide.load.Transformation, java.lang.Object] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <Y> T transform(java.lang.Class<Y> r4, com.bumptech.glide.load.Transformation<Y> r5, boolean r6) {
        /*
            r3 = this;
            boolean r0 = r3.isAutoCloneEnabled
            if (r0 == 0) goto L_0x000d
            com.bumptech.glide.request.BaseRequestOptions r0 = r3.clone()
            com.bumptech.glide.request.BaseRequestOptions r0 = r0.transform(r4, r5, r6)
            return r0
        L_0x000d:
            com.bumptech.glide.util.Preconditions.checkNotNull(r4)
            com.bumptech.glide.util.Preconditions.checkNotNull(r5)
            java.util.Map<java.lang.Class<?>, com.bumptech.glide.load.Transformation<?>> r0 = r3.transformations
            r0.put(r4, r5)
            int r0 = r3.fields
            r0 = r0 | 2048(0x800, float:2.87E-42)
            r3.fields = r0
            r1 = 1
            r3.isTransformationAllowed = r1
            r2 = 65536(0x10000, float:9.18355E-41)
            r0 = r0 | r2
            r3.fields = r0
            r2 = 0
            r3.isScaleOnlyOrNoTransform = r2
            if (r6 == 0) goto L_0x0032
            r2 = 131072(0x20000, float:1.83671E-40)
            r0 = r0 | r2
            r3.fields = r0
            r3.isTransformationRequired = r1
        L_0x0032:
            com.bumptech.glide.request.BaseRequestOptions r0 = r3.selfOrThrowIfLocked()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.transform(java.lang.Class, com.bumptech.glide.load.Transformation, boolean):com.bumptech.glide.request.BaseRequestOptions");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.Class<Y>, java.lang.Class] */
    /* JADX WARNING: type inference failed for: r3v0, types: [com.bumptech.glide.load.Transformation<Y>, com.bumptech.glide.load.Transformation] */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <Y> T transform(java.lang.Class<Y> r2, com.bumptech.glide.load.Transformation<Y> r3) {
        /*
            r1 = this;
            r0 = 1
            com.bumptech.glide.request.BaseRequestOptions r0 = r1.transform(r2, r3, r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.transform(java.lang.Class, com.bumptech.glide.load.Transformation):com.bumptech.glide.request.BaseRequestOptions");
    }

    public T dontTransform() {
        if (this.isAutoCloneEnabled) {
            return clone().dontTransform();
        }
        this.transformations.clear();
        int i = this.fields & -2049;
        this.fields = i;
        this.isTransformationRequired = false;
        int i2 = i & -131073;
        this.fields = i2;
        this.isTransformationAllowed = false;
        this.fields = i2 | 65536;
        this.isScaleOnlyOrNoTransform = true;
        return selfOrThrowIfLocked();
    }

    public T dontAnimate() {
        return set(GifOptions.DISABLE_ANIMATION, true);
    }

    public T apply(BaseRequestOptions<?> o) {
        if (this.isAutoCloneEnabled) {
            return clone().apply(o);
        }
        BaseRequestOptions<?> other = o;
        if (isSet(other.fields, 2)) {
            this.sizeMultiplier = other.sizeMultiplier;
        }
        if (isSet(other.fields, 262144)) {
            this.useUnlimitedSourceGeneratorsPool = other.useUnlimitedSourceGeneratorsPool;
        }
        if (isSet(other.fields, 1048576)) {
            this.useAnimationPool = other.useAnimationPool;
        }
        if (isSet(other.fields, 4)) {
            this.diskCacheStrategy = other.diskCacheStrategy;
        }
        if (isSet(other.fields, 8)) {
            this.priority = other.priority;
        }
        if (isSet(other.fields, 16)) {
            this.errorPlaceholder = other.errorPlaceholder;
            this.errorId = 0;
            this.fields &= -33;
        }
        if (isSet(other.fields, 32)) {
            this.errorId = other.errorId;
            this.errorPlaceholder = null;
            this.fields &= -17;
        }
        if (isSet(other.fields, 64)) {
            this.placeholderDrawable = other.placeholderDrawable;
            this.placeholderId = 0;
            this.fields &= -129;
        }
        if (isSet(other.fields, 128)) {
            this.placeholderId = other.placeholderId;
            this.placeholderDrawable = null;
            this.fields &= -65;
        }
        if (isSet(other.fields, 256)) {
            this.isCacheable = other.isCacheable;
        }
        if (isSet(other.fields, 512)) {
            this.overrideWidth = other.overrideWidth;
            this.overrideHeight = other.overrideHeight;
        }
        if (isSet(other.fields, 1024)) {
            this.signature = other.signature;
        }
        if (isSet(other.fields, 4096)) {
            this.resourceClass = other.resourceClass;
        }
        if (isSet(other.fields, 8192)) {
            this.fallbackDrawable = other.fallbackDrawable;
            this.fallbackId = 0;
            this.fields &= -16385;
        }
        if (isSet(other.fields, 16384)) {
            this.fallbackId = other.fallbackId;
            this.fallbackDrawable = null;
            this.fields &= -8193;
        }
        if (isSet(other.fields, 32768)) {
            this.theme = other.theme;
        }
        if (isSet(other.fields, 65536)) {
            this.isTransformationAllowed = other.isTransformationAllowed;
        }
        if (isSet(other.fields, 131072)) {
            this.isTransformationRequired = other.isTransformationRequired;
        }
        if (isSet(other.fields, 2048)) {
            this.transformations.putAll(other.transformations);
            this.isScaleOnlyOrNoTransform = other.isScaleOnlyOrNoTransform;
        }
        if (isSet(other.fields, 524288)) {
            this.onlyRetrieveFromCache = other.onlyRetrieveFromCache;
        }
        if (!this.isTransformationAllowed) {
            this.transformations.clear();
            int i = this.fields & -2049;
            this.fields = i;
            this.isTransformationRequired = false;
            this.fields = i & -131073;
            this.isScaleOnlyOrNoTransform = true;
        }
        this.fields |= other.fields;
        this.options.putAll(other.options);
        return selfOrThrowIfLocked();
    }

    public boolean equals(Object o) {
        if (!(o instanceof BaseRequestOptions)) {
            return false;
        }
        BaseRequestOptions<?> other = (BaseRequestOptions) o;
        if (Float.compare(other.sizeMultiplier, this.sizeMultiplier) == 0 && this.errorId == other.errorId && Util.bothNullOrEqual(this.errorPlaceholder, other.errorPlaceholder) && this.placeholderId == other.placeholderId && Util.bothNullOrEqual(this.placeholderDrawable, other.placeholderDrawable) && this.fallbackId == other.fallbackId && Util.bothNullOrEqual(this.fallbackDrawable, other.fallbackDrawable) && this.isCacheable == other.isCacheable && this.overrideHeight == other.overrideHeight && this.overrideWidth == other.overrideWidth && this.isTransformationRequired == other.isTransformationRequired && this.isTransformationAllowed == other.isTransformationAllowed && this.useUnlimitedSourceGeneratorsPool == other.useUnlimitedSourceGeneratorsPool && this.onlyRetrieveFromCache == other.onlyRetrieveFromCache && this.diskCacheStrategy.equals(other.diskCacheStrategy) && this.priority == other.priority && this.options.equals(other.options) && this.transformations.equals(other.transformations) && this.resourceClass.equals(other.resourceClass) && Util.bothNullOrEqual(this.signature, other.signature) && Util.bothNullOrEqual(this.theme, other.theme)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Util.hashCode((Object) this.theme, Util.hashCode((Object) this.signature, Util.hashCode((Object) this.resourceClass, Util.hashCode((Object) this.transformations, Util.hashCode((Object) this.options, Util.hashCode((Object) this.priority, Util.hashCode((Object) this.diskCacheStrategy, Util.hashCode(this.onlyRetrieveFromCache, Util.hashCode(this.useUnlimitedSourceGeneratorsPool, Util.hashCode(this.isTransformationAllowed, Util.hashCode(this.isTransformationRequired, Util.hashCode(this.overrideWidth, Util.hashCode(this.overrideHeight, Util.hashCode(this.isCacheable, Util.hashCode((Object) this.fallbackDrawable, Util.hashCode(this.fallbackId, Util.hashCode((Object) this.placeholderDrawable, Util.hashCode(this.placeholderId, Util.hashCode((Object) this.errorPlaceholder, Util.hashCode(this.errorId, Util.hashCode(this.sizeMultiplier)))))))))))))))))))));
    }

    public T lock() {
        this.isLocked = true;
        return self();
    }

    public T autoClone() {
        if (!this.isLocked || this.isAutoCloneEnabled) {
            this.isAutoCloneEnabled = true;
            return lock();
        }
        throw new IllegalStateException("You cannot auto lock an already locked options object, try clone() first");
    }

    private T selfOrThrowIfLocked() {
        if (!this.isLocked) {
            return self();
        }
        throw new IllegalStateException("You cannot modify locked T, consider clone()");
    }

    /* access modifiers changed from: protected */
    public boolean isAutoCloneEnabled() {
        return this.isAutoCloneEnabled;
    }

    public final boolean isDiskCacheStrategySet() {
        return isSet(4);
    }

    public final boolean isSkipMemoryCacheSet() {
        return isSet(256);
    }

    public final Map<Class<?>, Transformation<?>> getTransformations() {
        return this.transformations;
    }

    public final boolean isTransformationRequired() {
        return this.isTransformationRequired;
    }

    public final Options getOptions() {
        return this.options;
    }

    public final Class<?> getResourceClass() {
        return this.resourceClass;
    }

    public final DiskCacheStrategy getDiskCacheStrategy() {
        return this.diskCacheStrategy;
    }

    public final Drawable getErrorPlaceholder() {
        return this.errorPlaceholder;
    }

    public final int getErrorId() {
        return this.errorId;
    }

    public final int getPlaceholderId() {
        return this.placeholderId;
    }

    public final Drawable getPlaceholderDrawable() {
        return this.placeholderDrawable;
    }

    public final int getFallbackId() {
        return this.fallbackId;
    }

    public final Drawable getFallbackDrawable() {
        return this.fallbackDrawable;
    }

    public final Resources.Theme getTheme() {
        return this.theme;
    }

    public final boolean isMemoryCacheable() {
        return this.isCacheable;
    }

    public final Key getSignature() {
        return this.signature;
    }

    public final boolean isPrioritySet() {
        return isSet(8);
    }

    public final Priority getPriority() {
        return this.priority;
    }

    public final int getOverrideWidth() {
        return this.overrideWidth;
    }

    public final boolean isValidOverride() {
        return Util.isValidDimensions(this.overrideWidth, this.overrideHeight);
    }

    public final int getOverrideHeight() {
        return this.overrideHeight;
    }

    public final float getSizeMultiplier() {
        return this.sizeMultiplier;
    }

    /* access modifiers changed from: package-private */
    public boolean isScaleOnlyOrNoTransform() {
        return this.isScaleOnlyOrNoTransform;
    }

    private boolean isSet(int flag) {
        return isSet(this.fields, flag);
    }

    public final boolean getUseUnlimitedSourceGeneratorsPool() {
        return this.useUnlimitedSourceGeneratorsPool;
    }

    public final boolean getUseAnimationPool() {
        return this.useAnimationPool;
    }

    public final boolean getOnlyRetrieveFromCache() {
        return this.onlyRetrieveFromCache;
    }

    private T self() {
        return this;
    }
}
