package com.bumptech.glide.request;

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
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;

public class RequestOptions extends BaseRequestOptions<RequestOptions> {
    @Nullable
    private static RequestOptions centerCropOptions;
    @Nullable
    private static RequestOptions centerInsideOptions;
    @Nullable
    private static RequestOptions circleCropOptions;
    @Nullable
    private static RequestOptions fitCenterOptions;
    @Nullable
    private static RequestOptions noAnimationOptions;
    @Nullable
    private static RequestOptions noTransformOptions;
    @Nullable
    private static RequestOptions skipMemoryCacheFalseOptions;
    @Nullable
    private static RequestOptions skipMemoryCacheTrueOptions;

    @CheckResult
    @NonNull
    public static RequestOptions sizeMultiplierOf(@FloatRange(from = 0.0d, to = 1.0d) float sizeMultiplier) {
        return (RequestOptions) new RequestOptions().sizeMultiplier(sizeMultiplier);
    }

    @CheckResult
    @NonNull
    public static RequestOptions diskCacheStrategyOf(@NonNull DiskCacheStrategy diskCacheStrategy) {
        return (RequestOptions) new RequestOptions().diskCacheStrategy(diskCacheStrategy);
    }

    @CheckResult
    @NonNull
    public static RequestOptions priorityOf(@NonNull Priority priority) {
        return (RequestOptions) new RequestOptions().priority(priority);
    }

    @CheckResult
    @NonNull
    public static RequestOptions placeholderOf(@Nullable Drawable placeholder) {
        return (RequestOptions) new RequestOptions().placeholder(placeholder);
    }

    @CheckResult
    @NonNull
    public static RequestOptions placeholderOf(@DrawableRes int placeholderId) {
        return (RequestOptions) new RequestOptions().placeholder(placeholderId);
    }

    @CheckResult
    @NonNull
    public static RequestOptions errorOf(@Nullable Drawable errorDrawable) {
        return (RequestOptions) new RequestOptions().error(errorDrawable);
    }

    @CheckResult
    @NonNull
    public static RequestOptions errorOf(@DrawableRes int errorId) {
        return (RequestOptions) new RequestOptions().error(errorId);
    }

    @CheckResult
    @NonNull
    public static RequestOptions skipMemoryCacheOf(boolean skipMemoryCache) {
        if (skipMemoryCache) {
            if (skipMemoryCacheTrueOptions == null) {
                skipMemoryCacheTrueOptions = (RequestOptions) ((RequestOptions) new RequestOptions().skipMemoryCache(true)).autoClone();
            }
            return skipMemoryCacheTrueOptions;
        }
        if (skipMemoryCacheFalseOptions == null) {
            skipMemoryCacheFalseOptions = (RequestOptions) ((RequestOptions) new RequestOptions().skipMemoryCache(false)).autoClone();
        }
        return skipMemoryCacheFalseOptions;
    }

    @CheckResult
    @NonNull
    public static RequestOptions overrideOf(@IntRange(from = 0) int width, @IntRange(from = 0) int height) {
        return (RequestOptions) new RequestOptions().override(width, height);
    }

    @CheckResult
    @NonNull
    public static RequestOptions overrideOf(@IntRange(from = 0) int size) {
        return overrideOf(size, size);
    }

    @CheckResult
    @NonNull
    public static RequestOptions signatureOf(@NonNull Key signature) {
        return (RequestOptions) new RequestOptions().signature(signature);
    }

    @CheckResult
    @NonNull
    public static RequestOptions fitCenterTransform() {
        if (fitCenterOptions == null) {
            fitCenterOptions = (RequestOptions) ((RequestOptions) new RequestOptions().fitCenter()).autoClone();
        }
        return fitCenterOptions;
    }

    @CheckResult
    @NonNull
    public static RequestOptions centerInsideTransform() {
        if (centerInsideOptions == null) {
            centerInsideOptions = (RequestOptions) ((RequestOptions) new RequestOptions().centerInside()).autoClone();
        }
        return centerInsideOptions;
    }

    @CheckResult
    @NonNull
    public static RequestOptions centerCropTransform() {
        if (centerCropOptions == null) {
            centerCropOptions = (RequestOptions) ((RequestOptions) new RequestOptions().centerCrop()).autoClone();
        }
        return centerCropOptions;
    }

    @CheckResult
    @NonNull
    public static RequestOptions circleCropTransform() {
        if (circleCropOptions == null) {
            circleCropOptions = (RequestOptions) ((RequestOptions) new RequestOptions().circleCrop()).autoClone();
        }
        return circleCropOptions;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.bumptech.glide.load.Transformation, com.bumptech.glide.load.Transformation<android.graphics.Bitmap>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @android.support.annotation.CheckResult
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.bumptech.glide.request.RequestOptions bitmapTransform(@android.support.annotation.NonNull com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r1) {
        /*
            com.bumptech.glide.request.RequestOptions r0 = new com.bumptech.glide.request.RequestOptions
            r0.<init>()
            com.bumptech.glide.request.BaseRequestOptions r0 = r0.transform((com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r1)
            com.bumptech.glide.request.RequestOptions r0 = (com.bumptech.glide.request.RequestOptions) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.RequestOptions.bitmapTransform(com.bumptech.glide.load.Transformation):com.bumptech.glide.request.RequestOptions");
    }

    @CheckResult
    @NonNull
    public static RequestOptions noTransformation() {
        if (noTransformOptions == null) {
            noTransformOptions = (RequestOptions) ((RequestOptions) new RequestOptions().dontTransform()).autoClone();
        }
        return noTransformOptions;
    }

    @CheckResult
    @NonNull
    public static <T> RequestOptions option(@NonNull Option<T> option, @NonNull T value) {
        return (RequestOptions) new RequestOptions().set(option, value);
    }

    @CheckResult
    @NonNull
    public static RequestOptions decodeTypeOf(@NonNull Class<?> resourceClass) {
        return (RequestOptions) new RequestOptions().decode(resourceClass);
    }

    @CheckResult
    @NonNull
    public static RequestOptions formatOf(@NonNull DecodeFormat format) {
        return (RequestOptions) new RequestOptions().format(format);
    }

    @CheckResult
    @NonNull
    public static RequestOptions frameOf(@IntRange(from = 0) long frameTimeMicros) {
        return (RequestOptions) new RequestOptions().frame(frameTimeMicros);
    }

    @CheckResult
    @NonNull
    public static RequestOptions downsampleOf(@NonNull DownsampleStrategy strategy) {
        return (RequestOptions) new RequestOptions().downsample(strategy);
    }

    @CheckResult
    @NonNull
    public static RequestOptions timeoutOf(@IntRange(from = 0) int timeout) {
        return (RequestOptions) new RequestOptions().timeout(timeout);
    }

    @CheckResult
    @NonNull
    public static RequestOptions encodeQualityOf(@IntRange(from = 0, to = 100) int quality) {
        return (RequestOptions) new RequestOptions().encodeQuality(quality);
    }

    @CheckResult
    @NonNull
    public static RequestOptions encodeFormatOf(@NonNull Bitmap.CompressFormat format) {
        return (RequestOptions) new RequestOptions().encodeFormat(format);
    }

    @CheckResult
    @NonNull
    public static RequestOptions noAnimation() {
        if (noAnimationOptions == null) {
            noAnimationOptions = (RequestOptions) ((RequestOptions) new RequestOptions().dontAnimate()).autoClone();
        }
        return noAnimationOptions;
    }
}
