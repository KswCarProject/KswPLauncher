package com.bumptech.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;
import com.bumptech.glide.util.Util;
import java.util.NavigableMap;

final class SizeStrategy implements LruPoolStrategy {
    private static final int MAX_SIZE_MULTIPLE = 8;
    private final GroupedLinkedMap<Key, Bitmap> groupedMap = new GroupedLinkedMap<>();
    private final KeyPool keyPool = new KeyPool();
    private final NavigableMap<Integer, Integer> sortedSizes = new PrettyPrintTreeMap();

    SizeStrategy() {
    }

    public void put(Bitmap bitmap) {
        Key key = this.keyPool.get(Util.getBitmapByteSize(bitmap));
        this.groupedMap.put(key, bitmap);
        Integer current = (Integer) this.sortedSizes.get(Integer.valueOf(key.size));
        NavigableMap<Integer, Integer> navigableMap = this.sortedSizes;
        Integer valueOf = Integer.valueOf(key.size);
        int i = 1;
        if (current != null) {
            i = 1 + current.intValue();
        }
        navigableMap.put(valueOf, Integer.valueOf(i));
    }

    public Bitmap get(int width, int height, Bitmap.Config config) {
        int size = Util.getBitmapByteSize(width, height, config);
        Key key = this.keyPool.get(size);
        Integer possibleSize = this.sortedSizes.ceilingKey(Integer.valueOf(size));
        if (!(possibleSize == null || possibleSize.intValue() == size || possibleSize.intValue() > size * 8)) {
            this.keyPool.offer(key);
            key = this.keyPool.get(possibleSize.intValue());
        }
        Bitmap result = this.groupedMap.get(key);
        if (result != null) {
            result.reconfigure(width, height, config);
            decrementBitmapOfSize(possibleSize);
        }
        return result;
    }

    public Bitmap removeLast() {
        Bitmap removed = this.groupedMap.removeLast();
        if (removed != null) {
            decrementBitmapOfSize(Integer.valueOf(Util.getBitmapByteSize(removed)));
        }
        return removed;
    }

    private void decrementBitmapOfSize(Integer size) {
        Integer current = (Integer) this.sortedSizes.get(size);
        if (current.intValue() == 1) {
            this.sortedSizes.remove(size);
        } else {
            this.sortedSizes.put(size, Integer.valueOf(current.intValue() - 1));
        }
    }

    public String logBitmap(Bitmap bitmap) {
        return getBitmapString(bitmap);
    }

    public String logBitmap(int width, int height, Bitmap.Config config) {
        return getBitmapString(Util.getBitmapByteSize(width, height, config));
    }

    public int getSize(Bitmap bitmap) {
        return Util.getBitmapByteSize(bitmap);
    }

    public String toString() {
        return "SizeStrategy:\n  " + this.groupedMap + "\n  SortedSizes" + this.sortedSizes;
    }

    private static String getBitmapString(Bitmap bitmap) {
        return getBitmapString(Util.getBitmapByteSize(bitmap));
    }

    static String getBitmapString(int size) {
        return "[" + size + "]";
    }

    static class KeyPool extends BaseKeyPool<Key> {
        KeyPool() {
        }

        public Key get(int size) {
            Key result = (Key) super.get();
            result.init(size);
            return result;
        }

        /* access modifiers changed from: protected */
        public Key create() {
            return new Key(this);
        }
    }

    static final class Key implements Poolable {
        private final KeyPool pool;
        int size;

        Key(KeyPool pool2) {
            this.pool = pool2;
        }

        public void init(int size2) {
            this.size = size2;
        }

        public boolean equals(Object o) {
            if (!(o instanceof Key) || this.size != ((Key) o).size) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.size;
        }

        public String toString() {
            return SizeStrategy.getBitmapString(this.size);
        }

        public void offer() {
            this.pool.offer(this);
        }
    }
}
