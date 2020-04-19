package com.bumptech.glide.load.engine.bitmap_recycle;

import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.bumptech.glide.util.Preconditions;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public final class LruArrayPool implements ArrayPool {
    private static final int DEFAULT_SIZE = 4194304;
    @VisibleForTesting
    static final int MAX_OVER_SIZE_MULTIPLE = 8;
    private static final int SINGLE_ARRAY_MAX_SIZE_DIVISOR = 2;
    private final Map<Class<?>, ArrayAdapterInterface<?>> adapters;
    private int currentSize;
    private final GroupedLinkedMap<Key, Object> groupedMap;
    private final KeyPool keyPool;
    private final int maxSize;
    private final Map<Class<?>, NavigableMap<Integer, Integer>> sortedSizes;

    @VisibleForTesting
    public LruArrayPool() {
        this.groupedMap = new GroupedLinkedMap<>();
        this.keyPool = new KeyPool();
        this.sortedSizes = new HashMap();
        this.adapters = new HashMap();
        this.maxSize = 4194304;
    }

    public LruArrayPool(int maxSize2) {
        this.groupedMap = new GroupedLinkedMap<>();
        this.keyPool = new KeyPool();
        this.sortedSizes = new HashMap();
        this.adapters = new HashMap();
        this.maxSize = maxSize2;
    }

    @Deprecated
    public <T> void put(T array, Class<T> cls) {
        put(array);
    }

    public synchronized <T> void put(T array) {
        Class<?> cls = array.getClass();
        ArrayAdapterInterface<T> arrayAdapter = getAdapterFromType(cls);
        int size = arrayAdapter.getArrayLength(array);
        int arrayBytes = arrayAdapter.getElementSizeInBytes() * size;
        if (isSmallEnoughForReuse(arrayBytes)) {
            Key key = this.keyPool.get(size, cls);
            this.groupedMap.put(key, array);
            NavigableMap<Integer, Integer> sizes = getSizesForAdapter(cls);
            Integer current = (Integer) sizes.get(Integer.valueOf(key.size));
            Integer valueOf = Integer.valueOf(key.size);
            int i = 1;
            if (current != null) {
                i = 1 + current.intValue();
            }
            sizes.put(valueOf, Integer.valueOf(i));
            this.currentSize += arrayBytes;
            evict();
        }
    }

    public synchronized <T> T getExact(int size, Class<T> arrayClass) {
        return getForKey(this.keyPool.get(size, arrayClass), arrayClass);
    }

    public synchronized <T> T get(int size, Class<T> arrayClass) {
        Key key;
        Integer possibleSize = getSizesForAdapter(arrayClass).ceilingKey(Integer.valueOf(size));
        if (mayFillRequest(size, possibleSize)) {
            key = this.keyPool.get(possibleSize.intValue(), arrayClass);
        } else {
            key = this.keyPool.get(size, arrayClass);
        }
        return getForKey(key, arrayClass);
    }

    private <T> T getForKey(Key key, Class<T> arrayClass) {
        ArrayAdapterInterface<T> arrayAdapter = getAdapterFromType(arrayClass);
        T result = getArrayForKey(key);
        if (result != null) {
            this.currentSize -= arrayAdapter.getArrayLength(result) * arrayAdapter.getElementSizeInBytes();
            decrementArrayOfSize(arrayAdapter.getArrayLength(result), arrayClass);
        }
        if (result != null) {
            return result;
        }
        if (Log.isLoggable(arrayAdapter.getTag(), 2)) {
            Log.v(arrayAdapter.getTag(), "Allocated " + key.size + " bytes");
        }
        return arrayAdapter.newArray(key.size);
    }

    @Nullable
    private <T> T getArrayForKey(Key key) {
        return this.groupedMap.get(key);
    }

    private boolean isSmallEnoughForReuse(int byteSize) {
        return byteSize <= this.maxSize / 2;
    }

    private boolean mayFillRequest(int requestedSize, Integer actualSize) {
        return actualSize != null && (isNoMoreThanHalfFull() || actualSize.intValue() <= requestedSize * 8);
    }

    private boolean isNoMoreThanHalfFull() {
        return this.currentSize == 0 || this.maxSize / this.currentSize >= 2;
    }

    public synchronized void clearMemory() {
        evictToSize(0);
    }

    public synchronized void trimMemory(int level) {
        if (level >= 40) {
            try {
                clearMemory();
            } catch (Throwable th) {
                throw th;
            }
        } else if (level >= 20 || level == 15) {
            evictToSize(this.maxSize / 2);
        }
    }

    private void evict() {
        evictToSize(this.maxSize);
    }

    private void evictToSize(int size) {
        while (this.currentSize > size) {
            Object evicted = this.groupedMap.removeLast();
            Preconditions.checkNotNull(evicted);
            ArrayAdapterInterface<Object> arrayAdapter = getAdapterFromObject(evicted);
            this.currentSize -= arrayAdapter.getArrayLength(evicted) * arrayAdapter.getElementSizeInBytes();
            decrementArrayOfSize(arrayAdapter.getArrayLength(evicted), evicted.getClass());
            if (Log.isLoggable(arrayAdapter.getTag(), 2)) {
                Log.v(arrayAdapter.getTag(), "evicted: " + arrayAdapter.getArrayLength(evicted));
            }
        }
    }

    private void decrementArrayOfSize(int size, Class<?> arrayClass) {
        NavigableMap<Integer, Integer> sizes = getSizesForAdapter(arrayClass);
        Integer current = (Integer) sizes.get(Integer.valueOf(size));
        if (current == null) {
            throw new NullPointerException("Tried to decrement empty size, size: " + size + ", this: " + this);
        } else if (current.intValue() == 1) {
            sizes.remove(Integer.valueOf(size));
        } else {
            sizes.put(Integer.valueOf(size), Integer.valueOf(current.intValue() - 1));
        }
    }

    private NavigableMap<Integer, Integer> getSizesForAdapter(Class<?> arrayClass) {
        NavigableMap<Integer, Integer> sizes = this.sortedSizes.get(arrayClass);
        if (sizes != null) {
            return sizes;
        }
        NavigableMap<Integer, Integer> sizes2 = new TreeMap<>();
        this.sortedSizes.put(arrayClass, sizes2);
        return sizes2;
    }

    private <T> ArrayAdapterInterface<T> getAdapterFromObject(T object) {
        return getAdapterFromType(object.getClass());
    }

    private <T> ArrayAdapterInterface<T> getAdapterFromType(Class<T> arrayPoolClass) {
        ArrayAdapterInterface<?> adapter = this.adapters.get(arrayPoolClass);
        if (adapter == null) {
            if (arrayPoolClass.equals(int[].class)) {
                adapter = new IntegerArrayAdapter();
            } else if (arrayPoolClass.equals(byte[].class)) {
                adapter = new ByteArrayAdapter();
            } else {
                throw new IllegalArgumentException("No array pool found for: " + arrayPoolClass.getSimpleName());
            }
            this.adapters.put(arrayPoolClass, adapter);
        }
        return adapter;
    }

    /* access modifiers changed from: package-private */
    public int getCurrentSize() {
        int currentSize2 = 0;
        for (Class<?> type : this.sortedSizes.keySet()) {
            for (Integer size : this.sortedSizes.get(type).keySet()) {
                currentSize2 += size.intValue() * ((Integer) this.sortedSizes.get(type).get(size)).intValue() * getAdapterFromType(type).getElementSizeInBytes();
            }
        }
        return currentSize2;
    }

    private static final class KeyPool extends BaseKeyPool<Key> {
        KeyPool() {
        }

        /* access modifiers changed from: package-private */
        public Key get(int size, Class<?> arrayClass) {
            Key result = (Key) get();
            result.init(size, arrayClass);
            return result;
        }

        /* access modifiers changed from: protected */
        public Key create() {
            return new Key(this);
        }
    }

    private static final class Key implements Poolable {
        private Class<?> arrayClass;
        private final KeyPool pool;
        int size;

        Key(KeyPool pool2) {
            this.pool = pool2;
        }

        /* access modifiers changed from: package-private */
        public void init(int length, Class<?> arrayClass2) {
            this.size = length;
            this.arrayClass = arrayClass2;
        }

        public boolean equals(Object o) {
            if (!(o instanceof Key)) {
                return false;
            }
            Key other = (Key) o;
            if (this.size == other.size && this.arrayClass == other.arrayClass) {
                return true;
            }
            return false;
        }

        public String toString() {
            return "Key{size=" + this.size + "array=" + this.arrayClass + '}';
        }

        public void offer() {
            this.pool.offer(this);
        }

        public int hashCode() {
            return (this.size * 31) + (this.arrayClass != null ? this.arrayClass.hashCode() : 0);
        }
    }
}
