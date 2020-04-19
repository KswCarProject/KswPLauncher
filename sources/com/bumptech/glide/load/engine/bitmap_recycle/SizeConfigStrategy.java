package com.bumptech.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.VisibleForTesting;
import com.bumptech.glide.util.Util;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

@RequiresApi(19)
public class SizeConfigStrategy implements LruPoolStrategy {
    private static final Bitmap.Config[] ALPHA_8_IN_CONFIGS = {Bitmap.Config.ALPHA_8};
    private static final Bitmap.Config[] ARGB_4444_IN_CONFIGS = {Bitmap.Config.ARGB_4444};
    private static final Bitmap.Config[] ARGB_8888_IN_CONFIGS;
    private static final int MAX_SIZE_MULTIPLE = 8;
    private static final Bitmap.Config[] RGBA_F16_IN_CONFIGS = ARGB_8888_IN_CONFIGS;
    private static final Bitmap.Config[] RGB_565_IN_CONFIGS = {Bitmap.Config.RGB_565};
    private final GroupedLinkedMap<Key, Bitmap> groupedMap = new GroupedLinkedMap<>();
    private final KeyPool keyPool = new KeyPool();
    private final Map<Bitmap.Config, NavigableMap<Integer, Integer>> sortedSizes = new HashMap();

    /* JADX WARNING: type inference failed for: r3v6, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    static {
        /*
            r0 = 2
            android.graphics.Bitmap$Config[] r0 = new android.graphics.Bitmap.Config[r0]
            android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.ARGB_8888
            r2 = 0
            r0[r2] = r1
            r1 = 1
            r3 = 0
            r0[r1] = r3
            int r3 = android.os.Build.VERSION.SDK_INT
            r4 = 26
            if (r3 < r4) goto L_0x0021
            int r3 = r0.length
            int r3 = r3 + r1
            java.lang.Object[] r3 = java.util.Arrays.copyOf(r0, r3)
            r0 = r3
            android.graphics.Bitmap$Config[] r0 = (android.graphics.Bitmap.Config[]) r0
            int r3 = r0.length
            int r3 = r3 - r1
            android.graphics.Bitmap$Config r4 = android.graphics.Bitmap.Config.RGBA_F16
            r0[r3] = r4
        L_0x0021:
            ARGB_8888_IN_CONFIGS = r0
            android.graphics.Bitmap$Config[] r0 = ARGB_8888_IN_CONFIGS
            RGBA_F16_IN_CONFIGS = r0
            android.graphics.Bitmap$Config[] r0 = new android.graphics.Bitmap.Config[r1]
            android.graphics.Bitmap$Config r3 = android.graphics.Bitmap.Config.RGB_565
            r0[r2] = r3
            RGB_565_IN_CONFIGS = r0
            android.graphics.Bitmap$Config[] r0 = new android.graphics.Bitmap.Config[r1]
            android.graphics.Bitmap$Config r3 = android.graphics.Bitmap.Config.ARGB_4444
            r0[r2] = r3
            ARGB_4444_IN_CONFIGS = r0
            android.graphics.Bitmap$Config[] r0 = new android.graphics.Bitmap.Config[r1]
            android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.ALPHA_8
            r0[r2] = r1
            ALPHA_8_IN_CONFIGS = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.bitmap_recycle.SizeConfigStrategy.<clinit>():void");
    }

    public void put(Bitmap bitmap) {
        Key key = this.keyPool.get(Util.getBitmapByteSize(bitmap), bitmap.getConfig());
        this.groupedMap.put(key, bitmap);
        NavigableMap<Integer, Integer> sizes = getSizesForConfig(bitmap.getConfig());
        Integer current = (Integer) sizes.get(Integer.valueOf(key.size));
        Integer valueOf = Integer.valueOf(key.size);
        int i = 1;
        if (current != null) {
            i = 1 + current.intValue();
        }
        sizes.put(valueOf, Integer.valueOf(i));
    }

    @Nullable
    public Bitmap get(int width, int height, Bitmap.Config config) {
        Key bestKey = findBestKey(Util.getBitmapByteSize(width, height, config), config);
        Bitmap result = this.groupedMap.get(bestKey);
        if (result != null) {
            decrementBitmapOfSize(Integer.valueOf(bestKey.size), result);
            result.reconfigure(width, height, config);
        }
        return result;
    }

    private Key findBestKey(int size, Bitmap.Config config) {
        Key result = this.keyPool.get(size, config);
        Bitmap.Config[] inConfigs = getInConfigs(config);
        int length = inConfigs.length;
        int i = 0;
        while (i < length) {
            Bitmap.Config possibleConfig = inConfigs[i];
            Integer possibleSize = getSizesForConfig(possibleConfig).ceilingKey(Integer.valueOf(size));
            if (possibleSize == null || possibleSize.intValue() > size * 8) {
                i++;
            } else {
                if (possibleSize.intValue() == size) {
                    if (possibleConfig == null) {
                        if (config == null) {
                            return result;
                        }
                    } else if (possibleConfig.equals(config)) {
                        return result;
                    }
                }
                this.keyPool.offer(result);
                return this.keyPool.get(possibleSize.intValue(), possibleConfig);
            }
        }
        return result;
    }

    @Nullable
    public Bitmap removeLast() {
        Bitmap removed = this.groupedMap.removeLast();
        if (removed != null) {
            decrementBitmapOfSize(Integer.valueOf(Util.getBitmapByteSize(removed)), removed);
        }
        return removed;
    }

    private void decrementBitmapOfSize(Integer size, Bitmap removed) {
        NavigableMap<Integer, Integer> sizes = getSizesForConfig(removed.getConfig());
        Integer current = (Integer) sizes.get(size);
        if (current == null) {
            throw new NullPointerException("Tried to decrement empty size, size: " + size + ", removed: " + logBitmap(removed) + ", this: " + this);
        } else if (current.intValue() == 1) {
            sizes.remove(size);
        } else {
            sizes.put(size, Integer.valueOf(current.intValue() - 1));
        }
    }

    private NavigableMap<Integer, Integer> getSizesForConfig(Bitmap.Config config) {
        NavigableMap<Integer, Integer> sizes = this.sortedSizes.get(config);
        if (sizes != null) {
            return sizes;
        }
        NavigableMap<Integer, Integer> sizes2 = new TreeMap<>();
        this.sortedSizes.put(config, sizes2);
        return sizes2;
    }

    public String logBitmap(Bitmap bitmap) {
        return getBitmapString(Util.getBitmapByteSize(bitmap), bitmap.getConfig());
    }

    public String logBitmap(int width, int height, Bitmap.Config config) {
        return getBitmapString(Util.getBitmapByteSize(width, height, config), config);
    }

    public int getSize(Bitmap bitmap) {
        return Util.getBitmapByteSize(bitmap);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SizeConfigStrategy{groupedMap=");
        sb.append(this.groupedMap);
        StringBuilder sb2 = sb.append(", sortedSizes=(");
        for (Map.Entry<Bitmap.Config, NavigableMap<Integer, Integer>> entry : this.sortedSizes.entrySet()) {
            sb2.append(entry.getKey());
            sb2.append('[');
            sb2.append(entry.getValue());
            sb2.append("], ");
        }
        if (!this.sortedSizes.isEmpty()) {
            sb2.replace(sb2.length() - 2, sb2.length(), "");
        }
        sb2.append(")}");
        return sb2.toString();
    }

    @VisibleForTesting
    static class KeyPool extends BaseKeyPool<Key> {
        KeyPool() {
        }

        public Key get(int size, Bitmap.Config config) {
            Key result = (Key) get();
            result.init(size, config);
            return result;
        }

        /* access modifiers changed from: protected */
        public Key create() {
            return new Key(this);
        }
    }

    @VisibleForTesting
    static final class Key implements Poolable {
        private Bitmap.Config config;
        private final KeyPool pool;
        int size;

        public Key(KeyPool pool2) {
            this.pool = pool2;
        }

        @VisibleForTesting
        Key(KeyPool pool2, int size2, Bitmap.Config config2) {
            this(pool2);
            init(size2, config2);
        }

        public void init(int size2, Bitmap.Config config2) {
            this.size = size2;
            this.config = config2;
        }

        public void offer() {
            this.pool.offer(this);
        }

        public String toString() {
            return SizeConfigStrategy.getBitmapString(this.size, this.config);
        }

        public boolean equals(Object o) {
            if (!(o instanceof Key)) {
                return false;
            }
            Key other = (Key) o;
            if (this.size != other.size || !Util.bothNullOrEqual(this.config, other.config)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (this.size * 31) + (this.config != null ? this.config.hashCode() : 0);
        }
    }

    static String getBitmapString(int size, Bitmap.Config config) {
        return "[" + size + "](" + config + ")";
    }

    private static Bitmap.Config[] getInConfigs(Bitmap.Config requested) {
        if (Build.VERSION.SDK_INT >= 26 && Bitmap.Config.RGBA_F16.equals(requested)) {
            return RGBA_F16_IN_CONFIGS;
        }
        switch (AnonymousClass1.$SwitchMap$android$graphics$Bitmap$Config[requested.ordinal()]) {
            case 1:
                return ARGB_8888_IN_CONFIGS;
            case 2:
                return RGB_565_IN_CONFIGS;
            case 3:
                return ARGB_4444_IN_CONFIGS;
            case 4:
                return ALPHA_8_IN_CONFIGS;
            default:
                return new Bitmap.Config[]{requested};
        }
    }

    /* renamed from: com.bumptech.glide.load.engine.bitmap_recycle.SizeConfigStrategy$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$graphics$Bitmap$Config = new int[Bitmap.Config.values().length];

        static {
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.ARGB_8888.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.RGB_565.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.ARGB_4444.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.ALPHA_8.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }
}
