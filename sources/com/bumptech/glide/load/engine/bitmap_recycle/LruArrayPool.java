package com.bumptech.glide.load.engine.bitmap_recycle;

import android.util.Log;
import com.bumptech.glide.util.Preconditions;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/* loaded from: classes.dex */
public final class LruArrayPool implements ArrayPool {
    private static final int DEFAULT_SIZE = 4194304;
    static final int MAX_OVER_SIZE_MULTIPLE = 8;
    private static final int SINGLE_ARRAY_MAX_SIZE_DIVISOR = 2;
    private final Map<Class<?>, ArrayAdapterInterface<?>> adapters;
    private int currentSize;
    private final GroupedLinkedMap<Key, Object> groupedMap;
    private final KeyPool keyPool;
    private final int maxSize;
    private final Map<Class<?>, NavigableMap<Integer, Integer>> sortedSizes;

    public LruArrayPool() {
        this.groupedMap = new GroupedLinkedMap<>();
        this.keyPool = new KeyPool();
        this.sortedSizes = new HashMap();
        this.adapters = new HashMap();
        this.maxSize = 4194304;
    }

    public LruArrayPool(int maxSize) {
        this.groupedMap = new GroupedLinkedMap<>();
        this.keyPool = new KeyPool();
        this.sortedSizes = new HashMap();
        this.adapters = new HashMap();
        this.maxSize = maxSize;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool
    @Deprecated
    public <T> void put(T array, Class<T> arrayClass) {
        put(array);
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool
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

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool
    public synchronized <T> T getExact(int size, Class<T> arrayClass) {
        Key key;
        key = this.keyPool.get(size, arrayClass);
        return (T) getForKey(key, arrayClass);
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool
    public synchronized <T> T get(int size, Class<T> arrayClass) {
        Key key;
        Integer possibleSize = getSizesForAdapter(arrayClass).ceilingKey(Integer.valueOf(size));
        if (mayFillRequest(size, possibleSize)) {
            key = this.keyPool.get(possibleSize.intValue(), arrayClass);
        } else {
            key = this.keyPool.get(size, arrayClass);
        }
        return (T) getForKey(key, arrayClass);
    }

    private <T> T getForKey(Key key, Class<T> arrayClass) {
        ArrayAdapterInterface<T> arrayAdapter = getAdapterFromType(arrayClass);
        T result = (T) getArrayForKey(key);
        if (result != null) {
            this.currentSize -= arrayAdapter.getArrayLength(result) * arrayAdapter.getElementSizeInBytes();
            decrementArrayOfSize(arrayAdapter.getArrayLength(result), arrayClass);
        }
        if (result == null) {
            if (Log.isLoggable(arrayAdapter.getTag(), 2)) {
                Log.v(arrayAdapter.getTag(), "Allocated " + key.size + " bytes");
            }
            return arrayAdapter.newArray(key.size);
        }
        return result;
    }

    private <T> T getArrayForKey(Key key) {
        return (T) this.groupedMap.get(key);
    }

    private boolean isSmallEnoughForReuse(int byteSize) {
        return byteSize <= this.maxSize / 2;
    }

    private boolean mayFillRequest(int requestedSize, Integer actualSize) {
        return actualSize != null && (isNoMoreThanHalfFull() || actualSize.intValue() <= requestedSize * 8);
    }

    private boolean isNoMoreThanHalfFull() {
        int i = this.currentSize;
        return i == 0 || this.maxSize / i >= 2;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool
    public synchronized void clearMemory() {
        evictToSize(0);
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool
    public synchronized void trimMemory(int level) {
        try {
            if (level >= 40) {
                clearMemory();
            } else if (level >= 20 || level == 15) {
                evictToSize(this.maxSize / 2);
            }
        } catch (Throwable th) {
            throw th;
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
        }
        if (current.intValue() == 1) {
            sizes.remove(Integer.valueOf(size));
        } else {
            sizes.put(Integer.valueOf(size), Integer.valueOf(current.intValue() - 1));
        }
    }

    private NavigableMap<Integer, Integer> getSizesForAdapter(Class<?> arrayClass) {
        NavigableMap<Integer, Integer> sizes = this.sortedSizes.get(arrayClass);
        if (sizes == null) {
            NavigableMap<Integer, Integer> sizes2 = new TreeMap<>();
            this.sortedSizes.put(arrayClass, sizes2);
            return sizes2;
        }
        return sizes;
    }

    private <T> ArrayAdapterInterface<T> getAdapterFromObject(T object) {
        return getAdapterFromType(object.getClass());
    }

    private <T> ArrayAdapterInterface<T> getAdapterFromType(Class<T> arrayPoolClass) {
        ArrayAdapterInterface<T> arrayAdapterInterface = (ArrayAdapterInterface<T>) this.adapters.get(arrayPoolClass);
        if (arrayAdapterInterface == null) {
            if (arrayPoolClass.equals(int[].class)) {
                ArrayAdapterInterface<?> adapter = new IntegerArrayAdapter();
                arrayAdapterInterface = (ArrayAdapterInterface<T>) adapter;
            } else if (arrayPoolClass.equals(byte[].class)) {
                arrayAdapterInterface = new ByteArrayAdapter();
            } else {
                throw new IllegalArgumentException("No array pool found for: " + arrayPoolClass.getSimpleName());
            }
            this.adapters.put(arrayPoolClass, arrayAdapterInterface);
        }
        return arrayAdapterInterface;
    }

    int getCurrentSize() {
        int currentSize = 0;
        for (Class<?> type : this.sortedSizes.keySet()) {
            for (Integer size : this.sortedSizes.get(type).keySet()) {
                ArrayAdapterInterface<?> adapter = getAdapterFromType(type);
                currentSize += size.intValue() * ((Integer) this.sortedSizes.get(type).get(size)).intValue() * adapter.getElementSizeInBytes();
            }
        }
        return currentSize;
    }

    /* loaded from: classes.dex */
    private static final class KeyPool extends BaseKeyPool<Key> {
        KeyPool() {
        }

        Key get(int size, Class<?> arrayClass) {
            Key result = get();
            result.init(size, arrayClass);
            return result;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.bumptech.glide.load.engine.bitmap_recycle.BaseKeyPool
        public Key create() {
            return new Key(this);
        }
    }

    /* loaded from: classes.dex */
    private static final class Key implements Poolable {
        private Class<?> arrayClass;
        private final KeyPool pool;
        int size;

        Key(KeyPool pool) {
            this.pool = pool;
        }

        void init(int length, Class<?> arrayClass) {
            this.size = length;
            this.arrayClass = arrayClass;
        }

        public boolean equals(Object o) {
            if (o instanceof Key) {
                Key other = (Key) o;
                return this.size == other.size && this.arrayClass == other.arrayClass;
            }
            return false;
        }

        public String toString() {
            return "Key{size=" + this.size + "array=" + this.arrayClass + '}';
        }

        @Override // com.bumptech.glide.load.engine.bitmap_recycle.Poolable
        public void offer() {
            this.pool.offer(this);
        }

        public int hashCode() {
            int result = this.size;
            int i = result * 31;
            Class<?> cls = this.arrayClass;
            int result2 = i + (cls != null ? cls.hashCode() : 0);
            return result2;
        }
    }
}
