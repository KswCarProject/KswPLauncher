package com.bumptech.glide.request;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.CheckResult;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
    @NonNull
    private DiskCacheStrategy diskCacheStrategy = DiskCacheStrategy.AUTOMATIC;
    private int errorId;
    @Nullable
    private Drawable errorPlaceholder;
    @Nullable
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
    @NonNull
    private Options options = new Options();
    private int overrideHeight = -1;
    private int overrideWidth = -1;
    @Nullable
    private Drawable placeholderDrawable;
    private int placeholderId;
    @NonNull
    private Priority priority = Priority.NORMAL;
    @NonNull
    private Class<?> resourceClass = Object.class;
    @NonNull
    private Key signature = EmptySignature.obtain();
    private float sizeMultiplier = 1.0f;
    @Nullable
    private Resources.Theme theme;
    @NonNull
    private Map<Class<?>, Transformation<?>> transformations = new CachedHashCodeArrayMap();
    private boolean useAnimationPool;
    private boolean useUnlimitedSourceGeneratorsPool;

    private static boolean isSet(int fields2, int flag) {
        return (fields2 & flag) != 0;
    }

    @CheckResult
    @NonNull
    public T sizeMultiplier(@FloatRange(from = 0.0d, to = 1.0d) float sizeMultiplier2) {
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

    @CheckResult
    @NonNull
    public T useUnlimitedSourceGeneratorsPool(boolean flag) {
        if (this.isAutoCloneEnabled) {
            return clone().useUnlimitedSourceGeneratorsPool(flag);
        }
        this.useUnlimitedSourceGeneratorsPool = flag;
        this.fields |= 262144;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    @NonNull
    public T useAnimationPool(boolean flag) {
        if (this.isAutoCloneEnabled) {
            return clone().useAnimationPool(flag);
        }
        this.useAnimationPool = flag;
        this.fields |= 1048576;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    @NonNull
    public T onlyRetrieveFromCache(boolean flag) {
        if (this.isAutoCloneEnabled) {
            return clone().onlyRetrieveFromCache(flag);
        }
        this.onlyRetrieveFromCache = flag;
        this.fields |= 524288;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    @NonNull
    public T diskCacheStrategy(@NonNull DiskCacheStrategy strategy) {
        if (this.isAutoCloneEnabled) {
            return clone().diskCacheStrategy(strategy);
        }
        this.diskCacheStrategy = (DiskCacheStrategy) Preconditions.checkNotNull(strategy);
        this.fields |= 4;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    @NonNull
    public T priority(@NonNull Priority priority2) {
        if (this.isAutoCloneEnabled) {
            return clone().priority(priority2);
        }
        this.priority = (Priority) Preconditions.checkNotNull(priority2);
        this.fields |= 8;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    @NonNull
    public T placeholder(@Nullable Drawable drawable) {
        if (this.isAutoCloneEnabled) {
            return clone().placeholder(drawable);
        }
        this.placeholderDrawable = drawable;
        this.fields |= 64;
        this.placeholderId = 0;
        this.fields &= -129;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    @NonNull
    public T placeholder(@DrawableRes int resourceId) {
        if (this.isAutoCloneEnabled) {
            return clone().placeholder(resourceId);
        }
        this.placeholderId = resourceId;
        this.fields |= 128;
        this.placeholderDrawable = null;
        this.fields &= -65;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    @NonNull
    public T fallback(@Nullable Drawable drawable) {
        if (this.isAutoCloneEnabled) {
            return clone().fallback(drawable);
        }
        this.fallbackDrawable = drawable;
        this.fields |= 8192;
        this.fallbackId = 0;
        this.fields &= -16385;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    @NonNull
    public T fallback(@DrawableRes int resourceId) {
        if (this.isAutoCloneEnabled) {
            return clone().fallback(resourceId);
        }
        this.fallbackId = resourceId;
        this.fields |= 16384;
        this.fallbackDrawable = null;
        this.fields &= -8193;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    @NonNull
    public T error(@Nullable Drawable drawable) {
        if (this.isAutoCloneEnabled) {
            return clone().error(drawable);
        }
        this.errorPlaceholder = drawable;
        this.fields |= 16;
        this.errorId = 0;
        this.fields &= -33;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    @NonNull
    public T error(@DrawableRes int resourceId) {
        if (this.isAutoCloneEnabled) {
            return clone().error(resourceId);
        }
        this.errorId = resourceId;
        this.fields |= 32;
        this.errorPlaceholder = null;
        this.fields &= -17;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    @NonNull
    public T theme(@Nullable Resources.Theme theme2) {
        if (this.isAutoCloneEnabled) {
            return clone().theme(theme2);
        }
        this.theme = theme2;
        this.fields |= 32768;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    @NonNull
    public T skipMemoryCache(boolean skip) {
        if (this.isAutoCloneEnabled) {
            return clone().skipMemoryCache(true);
        }
        this.isCacheable = !skip;
        this.fields |= 256;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    @NonNull
    public T override(int width, int height) {
        if (this.isAutoCloneEnabled) {
            return clone().override(width, height);
        }
        this.overrideWidth = width;
        this.overrideHeight = height;
        this.fields |= 512;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    @NonNull
    public T override(int size) {
        return override(size, size);
    }

    @CheckResult
    @NonNull
    public T signature(@NonNull Key signature2) {
        if (this.isAutoCloneEnabled) {
            return clone().signature(signature2);
        }
        this.signature = (Key) Preconditions.checkNotNull(signature2);
        this.fields |= 1024;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    public T clone() {
        try {
            BaseRequestOptions<?> result = (BaseRequestOptions) super.clone();
            result.options = new Options();
            result.options.putAll(this.options);
            result.transformations = new CachedHashCodeArrayMap();
            result.transformations.putAll(this.transformations);
            result.isLocked = false;
            result.isAutoCloneEnabled = false;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.bumptech.glide.load.Option, java.lang.Object, com.bumptech.glide.load.Option<Y>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @android.support.annotation.CheckResult
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <Y> T set(@android.support.annotation.NonNull com.bumptech.glide.load.Option<Y> r2, @android.support.annotation.NonNull Y r3) {
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

    @CheckResult
    @NonNull
    public T decode(@NonNull Class<?> resourceClass2) {
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

    @CheckResult
    @NonNull
    public T encodeFormat(@NonNull Bitmap.CompressFormat format) {
        return set(BitmapEncoder.COMPRESSION_FORMAT, Preconditions.checkNotNull(format));
    }

    @CheckResult
    @NonNull
    public T encodeQuality(@IntRange(from = 0, to = 100) int quality) {
        return set(BitmapEncoder.COMPRESSION_QUALITY, Integer.valueOf(quality));
    }

    @CheckResult
    @NonNull
    public T frame(@IntRange(from = 0) long frameTimeMicros) {
        return set(VideoDecoder.TARGET_FRAME, Long.valueOf(frameTimeMicros));
    }

    @CheckResult
    @NonNull
    public T format(@NonNull DecodeFormat format) {
        Preconditions.checkNotNull(format);
        return set(Downsampler.DECODE_FORMAT, format).set(GifOptions.DECODE_FORMAT, format);
    }

    @CheckResult
    @NonNull
    public T disallowHardwareConfig() {
        return set(Downsampler.ALLOW_HARDWARE_CONFIG, false);
    }

    @CheckResult
    @NonNull
    public T downsample(@NonNull DownsampleStrategy strategy) {
        return set(DownsampleStrategy.OPTION, Preconditions.checkNotNull(strategy));
    }

    @CheckResult
    @NonNull
    public T timeout(@IntRange(from = 0) int timeoutMs) {
        return set(HttpGlideUrlLoader.TIMEOUT, Integer.valueOf(timeoutMs));
    }

    @CheckResult
    @NonNull
    public T optionalCenterCrop() {
        return optionalTransform(DownsampleStrategy.CENTER_OUTSIDE, (Transformation<Bitmap>) new CenterCrop());
    }

    @CheckResult
    @NonNull
    public T centerCrop() {
        return transform(DownsampleStrategy.CENTER_OUTSIDE, (Transformation<Bitmap>) new CenterCrop());
    }

    @CheckResult
    @NonNull
    public T optionalFitCenter() {
        return optionalScaleOnlyTransform(DownsampleStrategy.FIT_CENTER, new FitCenter());
    }

    @CheckResult
    @NonNull
    public T fitCenter() {
        return scaleOnlyTransform(DownsampleStrategy.FIT_CENTER, new FitCenter());
    }

    @CheckResult
    @NonNull
    public T optionalCenterInside() {
        return optionalScaleOnlyTransform(DownsampleStrategy.CENTER_INSIDE, new CenterInside());
    }

    @CheckResult
    @NonNull
    public T centerInside() {
        return scaleOnlyTransform(DownsampleStrategy.CENTER_INSIDE, new CenterInside());
    }

    @CheckResult
    @NonNull
    public T optionalCircleCrop() {
        return optionalTransform(DownsampleStrategy.CENTER_OUTSIDE, (Transformation<Bitmap>) new CircleCrop());
    }

    @CheckResult
    @NonNull
    public T circleCrop() {
        return transform(DownsampleStrategy.CENTER_INSIDE, (Transformation<Bitmap>) new CircleCrop());
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.bumptech.glide.load.Transformation, com.bumptech.glide.load.Transformation<android.graphics.Bitmap>] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Unknown variable types count: 1 */
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final T optionalTransform(@android.support.annotation.NonNull com.bumptech.glide.load.resource.bitmap.DownsampleStrategy r2, @android.support.annotation.NonNull com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r3) {
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
    @android.support.annotation.CheckResult
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final T transform(@android.support.annotation.NonNull com.bumptech.glide.load.resource.bitmap.DownsampleStrategy r2, @android.support.annotation.NonNull com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r3) {
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
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private T scaleOnlyTransform(@android.support.annotation.NonNull com.bumptech.glide.load.resource.bitmap.DownsampleStrategy r2, @android.support.annotation.NonNull com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r3) {
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
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private T optionalScaleOnlyTransform(@android.support.annotation.NonNull com.bumptech.glide.load.resource.bitmap.DownsampleStrategy r2, @android.support.annotation.NonNull com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r3) {
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
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private T scaleOnlyTransform(@android.support.annotation.NonNull com.bumptech.glide.load.resource.bitmap.DownsampleStrategy r3, @android.support.annotation.NonNull com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r4, boolean r5) {
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
    @android.support.annotation.CheckResult
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T transform(@android.support.annotation.NonNull com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r2) {
        /*
            r1 = this;
            r0 = 1
            com.bumptech.glide.request.BaseRequestOptions r0 = r1.transform((com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r2, (boolean) r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.transform(com.bumptech.glide.load.Transformation):com.bumptech.glide.request.BaseRequestOptions");
    }

    @CheckResult
    @NonNull
    public T transform(@NonNull Transformation<Bitmap>... transformations2) {
        if (transformations2.length > 1) {
            return transform((Transformation<Bitmap>) new MultiTransformation((Transformation<T>[]) transformations2), true);
        }
        if (transformations2.length == 1) {
            return transform((Transformation<Bitmap>) transformations2[0]);
        }
        return selfOrThrowIfLocked();
    }

    @CheckResult
    @NonNull
    @Deprecated
    public T transforms(@NonNull Transformation<Bitmap>... transformations2) {
        return transform((Transformation<Bitmap>) new MultiTransformation((Transformation<T>[]) transformations2), true);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.bumptech.glide.load.Transformation, com.bumptech.glide.load.Transformation<android.graphics.Bitmap>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @android.support.annotation.CheckResult
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T optionalTransform(@android.support.annotation.NonNull com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r2) {
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
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T transform(@android.support.annotation.NonNull com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r4, boolean r5) {
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
    @android.support.annotation.CheckResult
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <Y> T optionalTransform(@android.support.annotation.NonNull java.lang.Class<Y> r2, @android.support.annotation.NonNull com.bumptech.glide.load.Transformation<Y> r3) {
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
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <Y> T transform(@android.support.annotation.NonNull java.lang.Class<Y> r4, @android.support.annotation.NonNull com.bumptech.glide.load.Transformation<Y> r5, boolean r6) {
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
            r0 = 1
            r3.isTransformationAllowed = r0
            int r1 = r3.fields
            r2 = 65536(0x10000, float:9.18355E-41)
            r1 = r1 | r2
            r3.fields = r1
            r1 = 0
            r3.isScaleOnlyOrNoTransform = r1
            if (r6 == 0) goto L_0x0036
            int r1 = r3.fields
            r2 = 131072(0x20000, float:1.83671E-40)
            r1 = r1 | r2
            r3.fields = r1
            r3.isTransformationRequired = r0
        L_0x0036:
            com.bumptech.glide.request.BaseRequestOptions r0 = r3.selfOrThrowIfLocked()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.transform(java.lang.Class, com.bumptech.glide.load.Transformation, boolean):com.bumptech.glide.request.BaseRequestOptions");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.Class<Y>, java.lang.Class] */
    /* JADX WARNING: type inference failed for: r3v0, types: [com.bumptech.glide.load.Transformation<Y>, com.bumptech.glide.load.Transformation] */
    /* JADX WARNING: Unknown variable types count: 2 */
    @android.support.annotation.CheckResult
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <Y> T transform(@android.support.annotation.NonNull java.lang.Class<Y> r2, @android.support.annotation.NonNull com.bumptech.glide.load.Transformation<Y> r3) {
        /*
            r1 = this;
            r0 = 1
            com.bumptech.glide.request.BaseRequestOptions r0 = r1.transform(r2, r3, r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.transform(java.lang.Class, com.bumptech.glide.load.Transformation):com.bumptech.glide.request.BaseRequestOptions");
    }

    @CheckResult
    @NonNull
    public T dontTransform() {
        if (this.isAutoCloneEnabled) {
            return clone().dontTransform();
        }
        this.transformations.clear();
        this.fields &= -2049;
        this.isTransformationRequired = false;
        this.fields &= -131073;
        this.isTransformationAllowed = false;
        this.fields |= 65536;
        this.isScaleOnlyOrNoTransform = true;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    @NonNull
    public T dontAnimate() {
        return set(GifOptions.DISABLE_ANIMATION, true);
    }

    @CheckResult
    @NonNull
    public T apply(@NonNull BaseRequestOptions<?> o) {
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
            this.fields &= -2049;
            this.isTransformationRequired = false;
            this.fields &= -131073;
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

    @NonNull
    public T lock() {
        this.isLocked = true;
        return self();
    }

    @NonNull
    public T autoClone() {
        if (!this.isLocked || this.isAutoCloneEnabled) {
            this.isAutoCloneEnabled = true;
            return lock();
        }
        throw new IllegalStateException("You cannot auto lock an already locked options object, try clone() first");
    }

    @NonNull
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

    @NonNull
    public final Map<Class<?>, Transformation<?>> getTransformations() {
        return this.transformations;
    }

    public final boolean isTransformationRequired() {
        return this.isTransformationRequired;
    }

    @NonNull
    public final Options getOptions() {
        return this.options;
    }

    @NonNull
    public final Class<?> getResourceClass() {
        return this.resourceClass;
    }

    @NonNull
    public final DiskCacheStrategy getDiskCacheStrategy() {
        return this.diskCacheStrategy;
    }

    @Nullable
    public final Drawable getErrorPlaceholder() {
        return this.errorPlaceholder;
    }

    public final int getErrorId() {
        return this.errorId;
    }

    public final int getPlaceholderId() {
        return this.placeholderId;
    }

    @Nullable
    public final Drawable getPlaceholderDrawable() {
        return this.placeholderDrawable;
    }

    public final int getFallbackId() {
        return this.fallbackId;
    }

    @Nullable
    public final Drawable getFallbackDrawable() {
        return this.fallbackDrawable;
    }

    @Nullable
    public final Resources.Theme getTheme() {
        return this.theme;
    }

    public final boolean isMemoryCacheable() {
        return this.isCacheable;
    }

    @NonNull
    public final Key getSignature() {
        return this.signature;
    }

    public final boolean isPrioritySet() {
        return isSet(8);
    }

    @NonNull
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
