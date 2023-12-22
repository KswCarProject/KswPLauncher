package com.bumptech.glide.load.engine.prefill;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.prefill.PreFillType;
import com.bumptech.glide.util.Util;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class BitmapPreFiller {
    private final BitmapPool bitmapPool;
    private BitmapPreFillRunner current;
    private final DecodeFormat defaultFormat;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final MemoryCache memoryCache;

    public BitmapPreFiller(MemoryCache memoryCache, BitmapPool bitmapPool, DecodeFormat defaultFormat) {
        this.memoryCache = memoryCache;
        this.bitmapPool = bitmapPool;
        this.defaultFormat = defaultFormat;
    }

    public void preFill(PreFillType.Builder... bitmapAttributeBuilders) {
        BitmapPreFillRunner bitmapPreFillRunner = this.current;
        if (bitmapPreFillRunner != null) {
            bitmapPreFillRunner.cancel();
        }
        PreFillType[] bitmapAttributes = new PreFillType[bitmapAttributeBuilders.length];
        for (int i = 0; i < bitmapAttributeBuilders.length; i++) {
            PreFillType.Builder builder = bitmapAttributeBuilders[i];
            if (builder.getConfig() == null) {
                builder.setConfig(this.defaultFormat == DecodeFormat.PREFER_ARGB_8888 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            }
            bitmapAttributes[i] = builder.build();
        }
        PreFillQueue allocationOrder = generateAllocationOrder(bitmapAttributes);
        BitmapPreFillRunner bitmapPreFillRunner2 = new BitmapPreFillRunner(this.bitmapPool, this.memoryCache, allocationOrder);
        this.current = bitmapPreFillRunner2;
        this.handler.post(bitmapPreFillRunner2);
    }

    PreFillQueue generateAllocationOrder(PreFillType... preFillSizes) {
        long maxSize = (this.memoryCache.getMaxSize() - this.memoryCache.getCurrentSize()) + this.bitmapPool.getMaxSize();
        int totalWeight = 0;
        for (PreFillType size : preFillSizes) {
            totalWeight += size.getWeight();
        }
        float bytesPerWeight = ((float) maxSize) / totalWeight;
        Map<PreFillType, Integer> attributeToCount = new HashMap<>();
        for (PreFillType size2 : preFillSizes) {
            int bytesForSize = Math.round(size2.getWeight() * bytesPerWeight);
            int bytesPerBitmap = getSizeInBytes(size2);
            int bitmapsForSize = bytesForSize / bytesPerBitmap;
            attributeToCount.put(size2, Integer.valueOf(bitmapsForSize));
        }
        return new PreFillQueue(attributeToCount);
    }

    private static int getSizeInBytes(PreFillType size) {
        return Util.getBitmapByteSize(size.getWidth(), size.getHeight(), size.getConfig());
    }
}
