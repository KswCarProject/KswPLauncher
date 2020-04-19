package com.bumptech.glide.load.engine.bitmap_recycle;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LruBitmapPool implements BitmapPool {
    private static final Bitmap.Config DEFAULT_CONFIG = Bitmap.Config.ARGB_8888;
    private static final String TAG = "LruBitmapPool";
    private final Set<Bitmap.Config> allowedConfigs;
    private long currentSize;
    private int evictions;
    private int hits;
    private final long initialMaxSize;
    private long maxSize;
    private int misses;
    private int puts;
    private final LruPoolStrategy strategy;
    private final BitmapTracker tracker;

    private interface BitmapTracker {
        void add(Bitmap bitmap);

        void remove(Bitmap bitmap);
    }

    LruBitmapPool(long maxSize2, LruPoolStrategy strategy2, Set<Bitmap.Config> allowedConfigs2) {
        this.initialMaxSize = maxSize2;
        this.maxSize = maxSize2;
        this.strategy = strategy2;
        this.allowedConfigs = allowedConfigs2;
        this.tracker = new NullBitmapTracker();
    }

    public LruBitmapPool(long maxSize2) {
        this(maxSize2, getDefaultStrategy(), getDefaultAllowedConfigs());
    }

    public LruBitmapPool(long maxSize2, Set<Bitmap.Config> allowedConfigs2) {
        this(maxSize2, getDefaultStrategy(), allowedConfigs2);
    }

    public long getMaxSize() {
        return this.maxSize;
    }

    public synchronized void setSizeMultiplier(float sizeMultiplier) {
        this.maxSize = (long) Math.round(((float) this.initialMaxSize) * sizeMultiplier);
        evict();
    }

    public synchronized void put(Bitmap bitmap) {
        if (bitmap == null) {
            throw new NullPointerException("Bitmap must not be null");
        } else if (!bitmap.isRecycled()) {
            if (bitmap.isMutable() && ((long) this.strategy.getSize(bitmap)) <= this.maxSize) {
                if (this.allowedConfigs.contains(bitmap.getConfig())) {
                    int size = this.strategy.getSize(bitmap);
                    this.strategy.put(bitmap);
                    this.tracker.add(bitmap);
                    this.puts++;
                    this.currentSize += (long) size;
                    if (Log.isLoggable(TAG, 2)) {
                        Log.v(TAG, "Put bitmap in pool=" + this.strategy.logBitmap(bitmap));
                    }
                    dump();
                    evict();
                    return;
                }
            }
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "Reject bitmap from pool, bitmap: " + this.strategy.logBitmap(bitmap) + ", is mutable: " + bitmap.isMutable() + ", is allowed config: " + this.allowedConfigs.contains(bitmap.getConfig()));
            }
            bitmap.recycle();
        } else {
            throw new IllegalStateException("Cannot pool recycled bitmap");
        }
    }

    private void evict() {
        trimToSize(this.maxSize);
    }

    @NonNull
    public Bitmap get(int width, int height, Bitmap.Config config) {
        Bitmap result = getDirtyOrNull(width, height, config);
        if (result == null) {
            return createBitmap(width, height, config);
        }
        result.eraseColor(0);
        return result;
    }

    @NonNull
    public Bitmap getDirty(int width, int height, Bitmap.Config config) {
        Bitmap result = getDirtyOrNull(width, height, config);
        if (result == null) {
            return createBitmap(width, height, config);
        }
        return result;
    }

    @NonNull
    private static Bitmap createBitmap(int width, int height, @Nullable Bitmap.Config config) {
        return Bitmap.createBitmap(width, height, config != null ? config : DEFAULT_CONFIG);
    }

    @TargetApi(26)
    private static void assertNotHardwareConfig(Bitmap.Config config) {
        if (Build.VERSION.SDK_INT >= 26 && config == Bitmap.Config.HARDWARE) {
            throw new IllegalArgumentException("Cannot create a mutable Bitmap with config: " + config + ". Consider setting Downsampler#ALLOW_HARDWARE_CONFIG to false in your RequestOptions and/or in GlideBuilder.setDefaultRequestOptions");
        }
    }

    @Nullable
    private synchronized Bitmap getDirtyOrNull(int width, int height, @Nullable Bitmap.Config config) {
        Bitmap result;
        assertNotHardwareConfig(config);
        result = this.strategy.get(width, height, config != null ? config : DEFAULT_CONFIG);
        if (result == null) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Missing bitmap=" + this.strategy.logBitmap(width, height, config));
            }
            this.misses++;
        } else {
            this.hits++;
            this.currentSize -= (long) this.strategy.getSize(result);
            this.tracker.remove(result);
            normalize(result);
        }
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "Get bitmap=" + this.strategy.logBitmap(width, height, config));
        }
        dump();
        return result;
    }

    private static void normalize(Bitmap bitmap) {
        bitmap.setHasAlpha(true);
        maybeSetPreMultiplied(bitmap);
    }

    @TargetApi(19)
    private static void maybeSetPreMultiplied(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 19) {
            bitmap.setPremultiplied(true);
        }
    }

    public void clearMemory() {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "clearMemory");
        }
        trimToSize(0);
    }

    @SuppressLint({"InlinedApi"})
    public void trimMemory(int level) {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "trimMemory, level=" + level);
        }
        if (level >= 40) {
            clearMemory();
        } else if (level >= 20 || level == 15) {
            trimToSize(getMaxSize() / 2);
        }
    }

    private synchronized void trimToSize(long size) {
        while (this.currentSize > size) {
            Bitmap removed = this.strategy.removeLast();
            if (removed == null) {
                if (Log.isLoggable(TAG, 5)) {
                    Log.w(TAG, "Size mismatch, resetting");
                    dumpUnchecked();
                }
                this.currentSize = 0;
                return;
            }
            this.tracker.remove(removed);
            this.currentSize -= (long) this.strategy.getSize(removed);
            this.evictions++;
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Evicting bitmap=" + this.strategy.logBitmap(removed));
            }
            dump();
            removed.recycle();
        }
    }

    private void dump() {
        if (Log.isLoggable(TAG, 2)) {
            dumpUnchecked();
        }
    }

    private void dumpUnchecked() {
        Log.v(TAG, "Hits=" + this.hits + ", misses=" + this.misses + ", puts=" + this.puts + ", evictions=" + this.evictions + ", currentSize=" + this.currentSize + ", maxSize=" + this.maxSize + "\nStrategy=" + this.strategy);
    }

    private static LruPoolStrategy getDefaultStrategy() {
        if (Build.VERSION.SDK_INT >= 19) {
            return new SizeConfigStrategy();
        }
        return new AttributeStrategy();
    }

    @TargetApi(26)
    private static Set<Bitmap.Config> getDefaultAllowedConfigs() {
        Set<Bitmap.Config> configs = new HashSet<>(Arrays.asList(Bitmap.Config.values()));
        if (Build.VERSION.SDK_INT >= 19) {
            configs.add((Object) null);
        }
        if (Build.VERSION.SDK_INT >= 26) {
            configs.remove(Bitmap.Config.HARDWARE);
        }
        return Collections.unmodifiableSet(configs);
    }

    private static class ThrowingBitmapTracker implements BitmapTracker {
        private final Set<Bitmap> bitmaps = Collections.synchronizedSet(new HashSet());

        private ThrowingBitmapTracker() {
        }

        public void add(Bitmap bitmap) {
            if (!this.bitmaps.contains(bitmap)) {
                this.bitmaps.add(bitmap);
                return;
            }
            throw new IllegalStateException("Can't add already added bitmap: " + bitmap + " [" + bitmap.getWidth() + "x" + bitmap.getHeight() + "]");
        }

        public void remove(Bitmap bitmap) {
            if (this.bitmaps.contains(bitmap)) {
                this.bitmaps.remove(bitmap);
                return;
            }
            throw new IllegalStateException("Cannot remove bitmap not in tracker");
        }
    }

    private static final class NullBitmapTracker implements BitmapTracker {
        NullBitmapTracker() {
        }

        public void add(Bitmap bitmap) {
        }

        public void remove(Bitmap bitmap) {
        }
    }
}
