package android.support.p001v4.util;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/* renamed from: android.support.v4.util.LruCache */
/* loaded from: classes.dex */
public class LruCache<K, V> {
    private int createCount;
    private int evictionCount;
    private int hitCount;
    private final LinkedHashMap<K, V> map;
    private int maxSize;
    private int missCount;
    private int putCount;
    private int size;

    public LruCache(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.maxSize = maxSize;
        this.map = new LinkedHashMap<>(0, 0.75f, true);
    }

    public void resize(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        synchronized (this) {
            this.maxSize = maxSize;
        }
        trimToSize(maxSize);
    }

    public final V get(K key) {
        V mapValue;
        if (key == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            try {
            } catch (Throwable th) {
                th = th;
            }
            try {
                V mapValue2 = this.map.get(key);
                if (mapValue2 != null) {
                    this.hitCount++;
                    return mapValue2;
                }
                this.missCount++;
                V createdValue = create(key);
                if (createdValue == null) {
                    return null;
                }
                synchronized (this) {
                    this.createCount++;
                    mapValue = this.map.put(key, createdValue);
                    if (mapValue != null) {
                        this.map.put(key, mapValue);
                    } else {
                        this.size += safeSizeOf(key, createdValue);
                    }
                }
                if (mapValue != null) {
                    entryRemoved(false, key, createdValue, mapValue);
                    return mapValue;
                }
                trimToSize(this.maxSize);
                return createdValue;
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    public final V put(K key, V value) {
        if (key == null || value == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            try {
            } catch (Throwable th) {
                th = th;
            }
            try {
                this.putCount++;
                this.size += safeSizeOf(key, value);
                V previous = this.map.put(key, value);
                if (previous != null) {
                    this.size -= safeSizeOf(key, previous);
                }
                if (previous != null) {
                    entryRemoved(false, key, previous, value);
                }
                trimToSize(this.maxSize);
                return previous;
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0076, code lost:
        throw new java.lang.IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x007a -> B:27:0x0078). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void trimToSize(int maxSize) {
        K key;
        V value;
        while (true) {
            synchronized (this) {
                try {
                    if (this.size >= 0 && (!this.map.isEmpty() || this.size == 0)) {
                        if (this.size <= maxSize || this.map.isEmpty()) {
                            break;
                        }
                        Map.Entry<K, V> toEvict = this.map.entrySet().iterator().next();
                        key = toEvict.getKey();
                        try {
                            value = toEvict.getValue();
                            try {
                                this.map.remove(key);
                                this.size -= safeSizeOf(key, value);
                                this.evictionCount++;
                            } catch (Throwable th) {
                                th = th;
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            throw th;
                        }
                    } else {
                        break;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }
            entryRemoved(true, key, value, null);
        }
    }

    public final V remove(K key) {
        Throwable th;
        if (key == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            try {
                try {
                    V previous = this.map.remove(key);
                    if (previous != null) {
                        this.size -= safeSizeOf(key, previous);
                    }
                    if (previous != null) {
                        entryRemoved(false, key, previous, null);
                    }
                    return previous;
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                throw th;
            }
        }
    }

    protected void entryRemoved(boolean evicted, K key, V oldValue, V newValue) {
    }

    protected V create(K key) {
        return null;
    }

    private int safeSizeOf(K key, V value) {
        int result = sizeOf(key, value);
        if (result < 0) {
            throw new IllegalStateException("Negative size: " + key + "=" + value);
        }
        return result;
    }

    protected int sizeOf(K key, V value) {
        return 1;
    }

    public final void evictAll() {
        trimToSize(-1);
    }

    public final synchronized int size() {
        return this.size;
    }

    public final synchronized int maxSize() {
        return this.maxSize;
    }

    public final synchronized int hitCount() {
        return this.hitCount;
    }

    public final synchronized int missCount() {
        return this.missCount;
    }

    public final synchronized int createCount() {
        return this.createCount;
    }

    public final synchronized int putCount() {
        return this.putCount;
    }

    public final synchronized int evictionCount() {
        return this.evictionCount;
    }

    public final synchronized Map<K, V> snapshot() {
        return new LinkedHashMap(this.map);
    }

    public final synchronized String toString() {
        int hitPercent;
        int i = this.hitCount;
        int accesses = this.missCount + i;
        hitPercent = accesses != 0 ? (i * 100) / accesses : 0;
        return String.format(Locale.US, "LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", Integer.valueOf(this.maxSize), Integer.valueOf(this.hitCount), Integer.valueOf(this.missCount), Integer.valueOf(hitPercent));
    }
}
