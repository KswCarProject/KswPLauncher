package com.bumptech.glide.load.engine.cache;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.VisibleForTesting;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import com.bumptech.glide.util.Preconditions;

public final class MemorySizeCalculator {
    @VisibleForTesting
    static final int BYTES_PER_ARGB_8888_PIXEL = 4;
    private static final int LOW_MEMORY_BYTE_ARRAY_POOL_DIVISOR = 2;
    private static final String TAG = "MemorySizeCalculator";
    private final int arrayPoolSize;
    private final int bitmapPoolSize;
    private final Context context;
    private final int memoryCacheSize;

    interface ScreenDimensions {
        int getHeightPixels();

        int getWidthPixels();
    }

    MemorySizeCalculator(Builder builder) {
        int i;
        this.context = builder.context;
        if (isLowMemoryDevice(builder.activityManager)) {
            i = builder.arrayPoolSizeBytes / 2;
        } else {
            i = builder.arrayPoolSizeBytes;
        }
        this.arrayPoolSize = i;
        int maxSize = getMaxSize(builder.activityManager, builder.maxSizeMultiplier, builder.lowMemoryMaxSizeMultiplier);
        int screenSize = builder.screenDimensions.getWidthPixels() * builder.screenDimensions.getHeightPixels() * 4;
        int targetBitmapPoolSize = Math.round(((float) screenSize) * builder.bitmapPoolScreens);
        int targetMemoryCacheSize = Math.round(((float) screenSize) * builder.memoryCacheScreens);
        int availableSize = maxSize - this.arrayPoolSize;
        if (targetMemoryCacheSize + targetBitmapPoolSize <= availableSize) {
            this.memoryCacheSize = targetMemoryCacheSize;
            this.bitmapPoolSize = targetBitmapPoolSize;
        } else {
            float part = ((float) availableSize) / (builder.bitmapPoolScreens + builder.memoryCacheScreens);
            this.memoryCacheSize = Math.round(builder.memoryCacheScreens * part);
            this.bitmapPoolSize = Math.round(builder.bitmapPoolScreens * part);
        }
        if (Log.isLoggable(TAG, 3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Calculation complete, Calculated memory cache size: ");
            sb.append(toMb(this.memoryCacheSize));
            sb.append(", pool size: ");
            sb.append(toMb(this.bitmapPoolSize));
            sb.append(", byte array size: ");
            sb.append(toMb(this.arrayPoolSize));
            sb.append(", memory class limited? ");
            sb.append(targetMemoryCacheSize + targetBitmapPoolSize > maxSize);
            sb.append(", max size: ");
            sb.append(toMb(maxSize));
            sb.append(", memoryClass: ");
            sb.append(builder.activityManager.getMemoryClass());
            sb.append(", isLowMemoryDevice: ");
            sb.append(isLowMemoryDevice(builder.activityManager));
            Log.d(TAG, sb.toString());
        }
    }

    public int getMemoryCacheSize() {
        return this.memoryCacheSize;
    }

    public int getBitmapPoolSize() {
        return this.bitmapPoolSize;
    }

    public int getArrayPoolSizeInBytes() {
        return this.arrayPoolSize;
    }

    private static int getMaxSize(ActivityManager activityManager, float maxSizeMultiplier, float lowMemoryMaxSizeMultiplier) {
        return Math.round(((float) (activityManager.getMemoryClass() * 1024 * 1024)) * (isLowMemoryDevice(activityManager) ? lowMemoryMaxSizeMultiplier : maxSizeMultiplier));
    }

    private String toMb(int bytes) {
        return Formatter.formatFileSize(this.context, (long) bytes);
    }

    @TargetApi(19)
    static boolean isLowMemoryDevice(ActivityManager activityManager) {
        if (Build.VERSION.SDK_INT >= 19) {
            return activityManager.isLowRamDevice();
        }
        return true;
    }

    public static final class Builder {
        static final int ARRAY_POOL_SIZE_BYTES = 4194304;
        static final int BITMAP_POOL_TARGET_SCREENS = (Build.VERSION.SDK_INT < 26 ? 4 : 1);
        static final float LOW_MEMORY_MAX_SIZE_MULTIPLIER = 0.33f;
        static final float MAX_SIZE_MULTIPLIER = 0.4f;
        @VisibleForTesting
        static final int MEMORY_CACHE_TARGET_SCREENS = 2;
        ActivityManager activityManager;
        int arrayPoolSizeBytes = 4194304;
        float bitmapPoolScreens = ((float) BITMAP_POOL_TARGET_SCREENS);
        final Context context;
        float lowMemoryMaxSizeMultiplier = LOW_MEMORY_MAX_SIZE_MULTIPLIER;
        float maxSizeMultiplier = MAX_SIZE_MULTIPLIER;
        float memoryCacheScreens = 2.0f;
        ScreenDimensions screenDimensions;

        public Builder(Context context2) {
            this.context = context2;
            this.activityManager = (ActivityManager) context2.getSystemService("activity");
            this.screenDimensions = new DisplayMetricsScreenDimensions(context2.getResources().getDisplayMetrics());
            if (Build.VERSION.SDK_INT >= 26 && MemorySizeCalculator.isLowMemoryDevice(this.activityManager)) {
                this.bitmapPoolScreens = 0.0f;
            }
        }

        public Builder setMemoryCacheScreens(float memoryCacheScreens2) {
            Preconditions.checkArgument(memoryCacheScreens2 >= 0.0f, "Memory cache screens must be greater than or equal to 0");
            this.memoryCacheScreens = memoryCacheScreens2;
            return this;
        }

        public Builder setBitmapPoolScreens(float bitmapPoolScreens2) {
            Preconditions.checkArgument(bitmapPoolScreens2 >= 0.0f, "Bitmap pool screens must be greater than or equal to 0");
            this.bitmapPoolScreens = bitmapPoolScreens2;
            return this;
        }

        public Builder setMaxSizeMultiplier(float maxSizeMultiplier2) {
            Preconditions.checkArgument(maxSizeMultiplier2 >= 0.0f && maxSizeMultiplier2 <= 1.0f, "Size multiplier must be between 0 and 1");
            this.maxSizeMultiplier = maxSizeMultiplier2;
            return this;
        }

        public Builder setLowMemoryMaxSizeMultiplier(float lowMemoryMaxSizeMultiplier2) {
            Preconditions.checkArgument(lowMemoryMaxSizeMultiplier2 >= 0.0f && lowMemoryMaxSizeMultiplier2 <= 1.0f, "Low memory max size multiplier must be between 0 and 1");
            this.lowMemoryMaxSizeMultiplier = lowMemoryMaxSizeMultiplier2;
            return this;
        }

        public Builder setArrayPoolSize(int arrayPoolSizeBytes2) {
            this.arrayPoolSizeBytes = arrayPoolSizeBytes2;
            return this;
        }

        /* access modifiers changed from: package-private */
        @VisibleForTesting
        public Builder setActivityManager(ActivityManager activityManager2) {
            this.activityManager = activityManager2;
            return this;
        }

        /* access modifiers changed from: package-private */
        @VisibleForTesting
        public Builder setScreenDimensions(ScreenDimensions screenDimensions2) {
            this.screenDimensions = screenDimensions2;
            return this;
        }

        public MemorySizeCalculator build() {
            return new MemorySizeCalculator(this);
        }
    }

    private static final class DisplayMetricsScreenDimensions implements ScreenDimensions {
        private final DisplayMetrics displayMetrics;

        DisplayMetricsScreenDimensions(DisplayMetrics displayMetrics2) {
            this.displayMetrics = displayMetrics2;
        }

        public int getWidthPixels() {
            return this.displayMetrics.widthPixels;
        }

        public int getHeightPixels() {
            return this.displayMetrics.heightPixels;
        }
    }
}
