package com.bumptech.glide.load.engine.prefill;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.bumptech.glide.util.Util;
import java.security.MessageDigest;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

final class BitmapPreFillRunner implements Runnable {
    static final int BACKOFF_RATIO = 4;
    private static final Clock DEFAULT_CLOCK = new Clock();
    static final long INITIAL_BACKOFF_MS = 40;
    static final long MAX_BACKOFF_MS = TimeUnit.SECONDS.toMillis(1);
    static final long MAX_DURATION_MS = 32;
    static final String TAG = "PreFillRunner";
    private final BitmapPool bitmapPool;
    private final Clock clock;
    private long currentDelay;
    private final Handler handler;
    private boolean isCancelled;
    private final MemoryCache memoryCache;
    private final Set<PreFillType> seenTypes;
    private final PreFillQueue toPrefill;

    public BitmapPreFillRunner(BitmapPool bitmapPool2, MemoryCache memoryCache2, PreFillQueue allocationOrder) {
        this(bitmapPool2, memoryCache2, allocationOrder, DEFAULT_CLOCK, new Handler(Looper.getMainLooper()));
    }

    BitmapPreFillRunner(BitmapPool bitmapPool2, MemoryCache memoryCache2, PreFillQueue allocationOrder, Clock clock2, Handler handler2) {
        this.seenTypes = new HashSet();
        this.currentDelay = INITIAL_BACKOFF_MS;
        this.bitmapPool = bitmapPool2;
        this.memoryCache = memoryCache2;
        this.toPrefill = allocationOrder;
        this.clock = clock2;
        this.handler = handler2;
    }

    public void cancel() {
        this.isCancelled = true;
    }

    /* access modifiers changed from: package-private */
    public boolean allocate() {
        Bitmap bitmap;
        long start = this.clock.now();
        while (!this.toPrefill.isEmpty() && !isGcDetected(start)) {
            PreFillType toAllocate = this.toPrefill.remove();
            if (!this.seenTypes.contains(toAllocate)) {
                this.seenTypes.add(toAllocate);
                bitmap = this.bitmapPool.getDirty(toAllocate.getWidth(), toAllocate.getHeight(), toAllocate.getConfig());
            } else {
                bitmap = Bitmap.createBitmap(toAllocate.getWidth(), toAllocate.getHeight(), toAllocate.getConfig());
            }
            int bitmapSize = Util.getBitmapByteSize(bitmap);
            if (getFreeMemoryCacheBytes() >= ((long) bitmapSize)) {
                this.memoryCache.put(new UniqueKey(), BitmapResource.obtain(bitmap, this.bitmapPool));
            } else {
                this.bitmapPool.put(bitmap);
            }
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "allocated [" + toAllocate.getWidth() + "x" + toAllocate.getHeight() + "] " + toAllocate.getConfig() + " size: " + bitmapSize);
            }
        }
        return !this.isCancelled && !this.toPrefill.isEmpty();
    }

    private boolean isGcDetected(long startTimeMs) {
        return this.clock.now() - startTimeMs >= 32;
    }

    private long getFreeMemoryCacheBytes() {
        return this.memoryCache.getMaxSize() - this.memoryCache.getCurrentSize();
    }

    public void run() {
        if (allocate()) {
            this.handler.postDelayed(this, getNextDelay());
        }
    }

    private long getNextDelay() {
        long result = this.currentDelay;
        this.currentDelay = Math.min(this.currentDelay * 4, MAX_BACKOFF_MS);
        return result;
    }

    private static final class UniqueKey implements Key {
        UniqueKey() {
        }

        public void updateDiskCacheKey(MessageDigest messageDigest) {
            throw new UnsupportedOperationException();
        }
    }

    static class Clock {
        Clock() {
        }

        /* access modifiers changed from: package-private */
        public long now() {
            return SystemClock.currentThreadTimeMillis();
        }
    }
}
