package com.bumptech.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;
import com.bumptech.glide.util.Util;

class AttributeStrategy implements LruPoolStrategy {
    private final GroupedLinkedMap<Key, Bitmap> groupedMap = new GroupedLinkedMap<>();
    private final KeyPool keyPool = new KeyPool();

    AttributeStrategy() {
    }

    public void put(Bitmap bitmap) {
        this.groupedMap.put(this.keyPool.get(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig()), bitmap);
    }

    public Bitmap get(int width, int height, Bitmap.Config config) {
        return this.groupedMap.get(this.keyPool.get(width, height, config));
    }

    public Bitmap removeLast() {
        return this.groupedMap.removeLast();
    }

    public String logBitmap(Bitmap bitmap) {
        return getBitmapString(bitmap);
    }

    public String logBitmap(int width, int height, Bitmap.Config config) {
        return getBitmapString(width, height, config);
    }

    public int getSize(Bitmap bitmap) {
        return Util.getBitmapByteSize(bitmap);
    }

    public String toString() {
        return "AttributeStrategy:\n  " + this.groupedMap;
    }

    private static String getBitmapString(Bitmap bitmap) {
        return getBitmapString(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
    }

    static String getBitmapString(int width, int height, Bitmap.Config config) {
        return "[" + width + "x" + height + "], " + config;
    }

    static class KeyPool extends BaseKeyPool<Key> {
        KeyPool() {
        }

        /* access modifiers changed from: package-private */
        public Key get(int width, int height, Bitmap.Config config) {
            Key result = (Key) get();
            result.init(width, height, config);
            return result;
        }

        /* access modifiers changed from: protected */
        public Key create() {
            return new Key(this);
        }
    }

    static class Key implements Poolable {
        private Bitmap.Config config;
        private int height;
        private final KeyPool pool;
        private int width;

        public Key(KeyPool pool2) {
            this.pool = pool2;
        }

        public void init(int width2, int height2, Bitmap.Config config2) {
            this.width = width2;
            this.height = height2;
            this.config = config2;
        }

        public boolean equals(Object o) {
            if (!(o instanceof Key)) {
                return false;
            }
            Key other = (Key) o;
            if (this.width == other.width && this.height == other.height && this.config == other.config) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int result = ((this.width * 31) + this.height) * 31;
            Bitmap.Config config2 = this.config;
            return result + (config2 != null ? config2.hashCode() : 0);
        }

        public String toString() {
            return AttributeStrategy.getBitmapString(this.width, this.height, this.config);
        }

        public void offer() {
            this.pool.offer(this);
        }
    }
}
