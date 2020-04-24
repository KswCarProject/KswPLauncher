package android.support.v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ConcurrentModificationException;
import java.util.Map;

public class SimpleArrayMap<K, V> {
    private static final int BASE_SIZE = 4;
    private static final int CACHE_SIZE = 10;
    private static final boolean CONCURRENT_MODIFICATION_EXCEPTIONS = true;
    private static final boolean DEBUG = false;
    private static final String TAG = "ArrayMap";
    @Nullable
    static Object[] mBaseCache;
    static int mBaseCacheSize;
    @Nullable
    static Object[] mTwiceBaseCache;
    static int mTwiceBaseCacheSize;
    Object[] mArray;
    int[] mHashes;
    int mSize;

    private static int binarySearchHashes(int[] hashes, int N, int hash) {
        try {
            return ContainerHelpers.binarySearch(hashes, N, hash);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ConcurrentModificationException();
        }
    }

    /* access modifiers changed from: package-private */
    public int indexOf(Object key, int hash) {
        int N = this.mSize;
        if (N == 0) {
            return -1;
        }
        int index = binarySearchHashes(this.mHashes, N, hash);
        if (index < 0 || key.equals(this.mArray[index << 1])) {
            return index;
        }
        int end = index + 1;
        while (end < N && this.mHashes[end] == hash) {
            if (key.equals(this.mArray[end << 1])) {
                return end;
            }
            end++;
        }
        int i = index - 1;
        while (i >= 0 && this.mHashes[i] == hash) {
            if (key.equals(this.mArray[i << 1])) {
                return i;
            }
            i--;
        }
        return ~end;
    }

    /* access modifiers changed from: package-private */
    public int indexOfNull() {
        int N = this.mSize;
        if (N == 0) {
            return -1;
        }
        int index = binarySearchHashes(this.mHashes, N, 0);
        if (index < 0 || this.mArray[index << 1] == null) {
            return index;
        }
        int end = index + 1;
        while (end < N && this.mHashes[end] == 0) {
            if (this.mArray[end << 1] == null) {
                return end;
            }
            end++;
        }
        int i = index - 1;
        while (i >= 0 && this.mHashes[i] == 0) {
            if (this.mArray[i << 1] == null) {
                return i;
            }
            i--;
        }
        return ~end;
    }

    private void allocArrays(int size) {
        if (size == 8) {
            synchronized (ArrayMap.class) {
                if (mTwiceBaseCache != null) {
                    Object[] array = mTwiceBaseCache;
                    this.mArray = array;
                    mTwiceBaseCache = (Object[]) array[0];
                    this.mHashes = (int[]) array[1];
                    array[1] = null;
                    array[0] = null;
                    mTwiceBaseCacheSize--;
                    return;
                }
            }
        } else if (size == 4) {
            synchronized (ArrayMap.class) {
                if (mBaseCache != null) {
                    Object[] array2 = mBaseCache;
                    this.mArray = array2;
                    mBaseCache = (Object[]) array2[0];
                    this.mHashes = (int[]) array2[1];
                    array2[1] = null;
                    array2[0] = null;
                    mBaseCacheSize--;
                    return;
                }
            }
        }
        this.mHashes = new int[size];
        this.mArray = new Object[(size << 1)];
    }

    private static void freeArrays(int[] hashes, Object[] array, int size) {
        if (hashes.length == 8) {
            synchronized (ArrayMap.class) {
                if (mTwiceBaseCacheSize < 10) {
                    array[0] = mTwiceBaseCache;
                    array[1] = hashes;
                    for (int i = (size << 1) - 1; i >= 2; i--) {
                        array[i] = null;
                    }
                    mTwiceBaseCache = array;
                    mTwiceBaseCacheSize++;
                }
            }
        } else if (hashes.length == 4) {
            synchronized (ArrayMap.class) {
                if (mBaseCacheSize < 10) {
                    array[0] = mBaseCache;
                    array[1] = hashes;
                    for (int i2 = (size << 1) - 1; i2 >= 2; i2--) {
                        array[i2] = null;
                    }
                    mBaseCache = array;
                    mBaseCacheSize++;
                }
            }
        }
    }

    public SimpleArrayMap() {
        this.mHashes = ContainerHelpers.EMPTY_INTS;
        this.mArray = ContainerHelpers.EMPTY_OBJECTS;
        this.mSize = 0;
    }

    public SimpleArrayMap(int capacity) {
        if (capacity == 0) {
            this.mHashes = ContainerHelpers.EMPTY_INTS;
            this.mArray = ContainerHelpers.EMPTY_OBJECTS;
        } else {
            allocArrays(capacity);
        }
        this.mSize = 0;
    }

    public SimpleArrayMap(SimpleArrayMap<K, V> map) {
        this();
        if (map != null) {
            putAll(map);
        }
    }

    public void clear() {
        if (this.mSize > 0) {
            int[] ohashes = this.mHashes;
            Object[] oarray = this.mArray;
            int osize = this.mSize;
            this.mHashes = ContainerHelpers.EMPTY_INTS;
            this.mArray = ContainerHelpers.EMPTY_OBJECTS;
            this.mSize = 0;
            freeArrays(ohashes, oarray, osize);
        }
        if (this.mSize > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public void ensureCapacity(int minimumCapacity) {
        int osize = this.mSize;
        if (this.mHashes.length < minimumCapacity) {
            int[] ohashes = this.mHashes;
            Object[] oarray = this.mArray;
            allocArrays(minimumCapacity);
            if (this.mSize > 0) {
                System.arraycopy(ohashes, 0, this.mHashes, 0, osize);
                System.arraycopy(oarray, 0, this.mArray, 0, osize << 1);
            }
            freeArrays(ohashes, oarray, osize);
        }
        if (this.mSize != osize) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(@Nullable Object key) {
        if (indexOfKey(key) >= 0) {
            return CONCURRENT_MODIFICATION_EXCEPTIONS;
        }
        return false;
    }

    public int indexOfKey(@Nullable Object key) {
        return key == null ? indexOfNull() : indexOf(key, key.hashCode());
    }

    /* access modifiers changed from: package-private */
    public int indexOfValue(Object value) {
        int N = this.mSize * 2;
        Object[] array = this.mArray;
        int i = 1;
        if (value == null) {
            while (i < N) {
                if (array[i] == null) {
                    return i >> 1;
                }
                i += 2;
            }
            return -1;
        }
        while (i < N) {
            if (value.equals(array[i])) {
                return i >> 1;
            }
            i += 2;
        }
        return -1;
    }

    public boolean containsValue(Object value) {
        if (indexOfValue(value) >= 0) {
            return CONCURRENT_MODIFICATION_EXCEPTIONS;
        }
        return false;
    }

    @Nullable
    public V get(Object key) {
        int index = indexOfKey(key);
        if (index >= 0) {
            return this.mArray[(index << 1) + 1];
        }
        return null;
    }

    public K keyAt(int index) {
        return this.mArray[index << 1];
    }

    public V valueAt(int index) {
        return this.mArray[(index << 1) + 1];
    }

    public V setValueAt(int index, V value) {
        int index2 = (index << 1) + 1;
        V old = this.mArray[index2];
        this.mArray[index2] = value;
        return old;
    }

    public boolean isEmpty() {
        if (this.mSize <= 0) {
            return CONCURRENT_MODIFICATION_EXCEPTIONS;
        }
        return false;
    }

    @Nullable
    public V put(K key, V value) {
        int index;
        int hash;
        int osize = this.mSize;
        if (key == null) {
            hash = 0;
            index = indexOfNull();
        } else {
            hash = key.hashCode();
            index = indexOf(key, hash);
        }
        if (index >= 0) {
            int index2 = (index << 1) + 1;
            V old = this.mArray[index2];
            this.mArray[index2] = value;
            return old;
        }
        int index3 = ~index;
        if (osize >= this.mHashes.length) {
            int n = 4;
            if (osize >= 8) {
                n = (osize >> 1) + osize;
            } else if (osize >= 4) {
                n = 8;
            }
            int[] ohashes = this.mHashes;
            Object[] oarray = this.mArray;
            allocArrays(n);
            if (osize == this.mSize) {
                if (this.mHashes.length > 0) {
                    System.arraycopy(ohashes, 0, this.mHashes, 0, ohashes.length);
                    System.arraycopy(oarray, 0, this.mArray, 0, oarray.length);
                }
                freeArrays(ohashes, oarray, osize);
            } else {
                throw new ConcurrentModificationException();
            }
        }
        if (index3 < osize) {
            System.arraycopy(this.mHashes, index3, this.mHashes, index3 + 1, osize - index3);
            System.arraycopy(this.mArray, index3 << 1, this.mArray, (index3 + 1) << 1, (this.mSize - index3) << 1);
        }
        if (osize != this.mSize || index3 >= this.mHashes.length) {
            throw new ConcurrentModificationException();
        }
        this.mHashes[index3] = hash;
        this.mArray[index3 << 1] = key;
        this.mArray[(index3 << 1) + 1] = value;
        this.mSize++;
        return null;
    }

    public void putAll(@NonNull SimpleArrayMap<? extends K, ? extends V> array) {
        int N = array.mSize;
        ensureCapacity(this.mSize + N);
        int i = 0;
        if (this.mSize != 0) {
            while (true) {
                int i2 = i;
                if (i2 < N) {
                    put(array.keyAt(i2), array.valueAt(i2));
                    i = i2 + 1;
                } else {
                    return;
                }
            }
        } else if (N > 0) {
            System.arraycopy(array.mHashes, 0, this.mHashes, 0, N);
            System.arraycopy(array.mArray, 0, this.mArray, 0, N << 1);
            this.mSize = N;
        }
    }

    @Nullable
    public V remove(Object key) {
        int index = indexOfKey(key);
        if (index >= 0) {
            return removeAt(index);
        }
        return null;
    }

    public V removeAt(int index) {
        int nsize;
        Object old = this.mArray[(index << 1) + 1];
        int osize = this.mSize;
        if (osize <= 1) {
            freeArrays(this.mHashes, this.mArray, osize);
            this.mHashes = ContainerHelpers.EMPTY_INTS;
            this.mArray = ContainerHelpers.EMPTY_OBJECTS;
            nsize = 0;
        } else {
            int nsize2 = osize - 1;
            int i = 8;
            if (this.mHashes.length <= 8 || this.mSize >= this.mHashes.length / 3) {
                if (index < nsize2) {
                    System.arraycopy(this.mHashes, index + 1, this.mHashes, index, nsize2 - index);
                    System.arraycopy(this.mArray, (index + 1) << 1, this.mArray, index << 1, (nsize2 - index) << 1);
                }
                this.mArray[nsize2 << 1] = null;
                this.mArray[(nsize2 << 1) + 1] = null;
            } else {
                if (osize > 8) {
                    i = osize + (osize >> 1);
                }
                int n = i;
                int[] ohashes = this.mHashes;
                Object[] oarray = this.mArray;
                allocArrays(n);
                if (osize == this.mSize) {
                    if (index > 0) {
                        System.arraycopy(ohashes, 0, this.mHashes, 0, index);
                        System.arraycopy(oarray, 0, this.mArray, 0, index << 1);
                    }
                    if (index < nsize2) {
                        System.arraycopy(ohashes, index + 1, this.mHashes, index, nsize2 - index);
                        System.arraycopy(oarray, (index + 1) << 1, this.mArray, index << 1, (nsize2 - index) << 1);
                    }
                } else {
                    throw new ConcurrentModificationException();
                }
            }
            nsize = nsize2;
        }
        if (osize == this.mSize) {
            this.mSize = nsize;
            return old;
        }
        throw new ConcurrentModificationException();
    }

    public int size() {
        return this.mSize;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return CONCURRENT_MODIFICATION_EXCEPTIONS;
        }
        if (object instanceof SimpleArrayMap) {
            SimpleArrayMap<?, ?> map = (SimpleArrayMap) object;
            if (size() != map.size()) {
                return false;
            }
            int i = 0;
            while (i < this.mSize) {
                try {
                    K key = keyAt(i);
                    V mine = valueAt(i);
                    Object theirs = map.get(key);
                    if (mine == null) {
                        if (theirs != null || !map.containsKey(key)) {
                            return false;
                        }
                    } else if (!mine.equals(theirs)) {
                        return false;
                    }
                    i++;
                } catch (NullPointerException e) {
                    return false;
                } catch (ClassCastException e2) {
                    return false;
                }
            }
            return CONCURRENT_MODIFICATION_EXCEPTIONS;
        } else if (!(object instanceof Map)) {
            return false;
        } else {
            Map<?, ?> map2 = (Map) object;
            if (size() != map2.size()) {
                return false;
            }
            int i2 = 0;
            while (i2 < this.mSize) {
                try {
                    K key2 = keyAt(i2);
                    V mine2 = valueAt(i2);
                    Object theirs2 = map2.get(key2);
                    if (mine2 == null) {
                        if (theirs2 != null || !map2.containsKey(key2)) {
                            return false;
                        }
                    } else if (!mine2.equals(theirs2)) {
                        return false;
                    }
                    i2++;
                } catch (NullPointerException e3) {
                    return false;
                } catch (ClassCastException e4) {
                    return false;
                }
            }
            return CONCURRENT_MODIFICATION_EXCEPTIONS;
        }
    }

    public int hashCode() {
        int[] hashes = this.mHashes;
        Object[] array = this.mArray;
        int result = 0;
        int i = 0;
        int v = 1;
        int s = this.mSize;
        while (i < s) {
            Object value = array[v];
            result += hashes[i] ^ (value == null ? 0 : value.hashCode());
            i++;
            v += 2;
        }
        return result;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder buffer = new StringBuilder(this.mSize * 28);
        buffer.append('{');
        for (int i = 0; i < this.mSize; i++) {
            if (i > 0) {
                buffer.append(", ");
            }
            Object key = keyAt(i);
            if (key != this) {
                buffer.append(key);
            } else {
                buffer.append("(this Map)");
            }
            buffer.append('=');
            Object value = valueAt(i);
            if (value != this) {
                buffer.append(value);
            } else {
                buffer.append("(this Map)");
            }
        }
        buffer.append('}');
        return buffer.toString();
    }
}
