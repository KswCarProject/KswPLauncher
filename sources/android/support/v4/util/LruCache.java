package android.support.v4.util;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class LruCache<K, V> {
    private int createCount;
    private int evictionCount;
    private int hitCount;
    private final LinkedHashMap<K, V> map;
    private int maxSize;
    private int missCount;
    private int putCount;
    private int size;

    public LruCache(int maxSize2) {
        if (maxSize2 > 0) {
            this.maxSize = maxSize2;
            this.map = new LinkedHashMap<>(0, 0.75f, true);
            return;
        }
        throw new IllegalArgumentException("maxSize <= 0");
    }

    public void resize(int maxSize2) {
        if (maxSize2 > 0) {
            synchronized (this) {
                this.maxSize = maxSize2;
            }
            trimToSize(maxSize2);
            return;
        }
        throw new IllegalArgumentException("maxSize <= 0");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001b, code lost:
        r2 = create(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        if (r2 != null) goto L_0x0022;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0021, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0022, code lost:
        monitor-enter(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r5.createCount++;
        r1 = r5.map.put(r6, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0030, code lost:
        if (r1 == null) goto L_0x0038;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0032, code lost:
        r5.map.put(r6, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0038, code lost:
        r5.size += safeSizeOf(r6, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0041, code lost:
        monitor-exit(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0042, code lost:
        if (r1 == null) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0044, code lost:
        entryRemoved(false, r6, r2, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0048, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0049, code lost:
        trimToSize(r5.maxSize);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004e, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final V get(K r6) {
        /*
            r5 = this;
            if (r6 == 0) goto L_0x005a
            monitor-enter(r5)
            r0 = 0
            java.util.LinkedHashMap<K, V> r1 = r5.map     // Catch:{ all -> 0x0052 }
            java.lang.Object r1 = r1.get(r6)     // Catch:{ all -> 0x0052 }
            if (r1 == 0) goto L_0x0014
            int r0 = r5.hitCount     // Catch:{ all -> 0x0058 }
            int r0 = r0 + 1
            r5.hitCount = r0     // Catch:{ all -> 0x0058 }
            monitor-exit(r5)     // Catch:{ all -> 0x0058 }
            return r1
        L_0x0014:
            int r2 = r5.missCount     // Catch:{ all -> 0x0058 }
            int r2 = r2 + 1
            r5.missCount = r2     // Catch:{ all -> 0x0058 }
            monitor-exit(r5)     // Catch:{ all -> 0x0058 }
            java.lang.Object r2 = r5.create(r6)
            if (r2 != 0) goto L_0x0022
            return r0
        L_0x0022:
            monitor-enter(r5)
            int r0 = r5.createCount     // Catch:{ all -> 0x004f }
            int r0 = r0 + 1
            r5.createCount = r0     // Catch:{ all -> 0x004f }
            java.util.LinkedHashMap<K, V> r0 = r5.map     // Catch:{ all -> 0x004f }
            java.lang.Object r0 = r0.put(r6, r2)     // Catch:{ all -> 0x004f }
            r1 = r0
            if (r1 == 0) goto L_0x0038
            java.util.LinkedHashMap<K, V> r0 = r5.map     // Catch:{ all -> 0x004f }
            r0.put(r6, r1)     // Catch:{ all -> 0x004f }
            goto L_0x0041
        L_0x0038:
            int r0 = r5.size     // Catch:{ all -> 0x004f }
            int r3 = r5.safeSizeOf(r6, r2)     // Catch:{ all -> 0x004f }
            int r0 = r0 + r3
            r5.size = r0     // Catch:{ all -> 0x004f }
        L_0x0041:
            monitor-exit(r5)     // Catch:{ all -> 0x004f }
            if (r1 == 0) goto L_0x0049
            r0 = 0
            r5.entryRemoved(r0, r6, r2, r1)
            return r1
        L_0x0049:
            int r0 = r5.maxSize
            r5.trimToSize(r0)
            return r2
        L_0x004f:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x004f }
            throw r0
        L_0x0052:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
        L_0x0056:
            monitor-exit(r5)     // Catch:{ all -> 0x0058 }
            throw r0
        L_0x0058:
            r0 = move-exception
            goto L_0x0056
        L_0x005a:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "key == null"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.util.LruCache.get(java.lang.Object):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0027, code lost:
        if (r0 == null) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0029, code lost:
        entryRemoved(false, r4, r0, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002d, code lost:
        trimToSize(r3.maxSize);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0032, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final V put(K r4, V r5) {
        /*
            r3 = this;
            if (r4 == 0) goto L_0x0038
            if (r5 == 0) goto L_0x0038
            monitor-enter(r3)
            r0 = 0
            int r1 = r3.putCount     // Catch:{ all -> 0x0033 }
            int r1 = r1 + 1
            r3.putCount = r1     // Catch:{ all -> 0x0033 }
            int r1 = r3.size     // Catch:{ all -> 0x0033 }
            int r2 = r3.safeSizeOf(r4, r5)     // Catch:{ all -> 0x0033 }
            int r1 = r1 + r2
            r3.size = r1     // Catch:{ all -> 0x0033 }
            java.util.LinkedHashMap<K, V> r1 = r3.map     // Catch:{ all -> 0x0033 }
            java.lang.Object r0 = r1.put(r4, r5)     // Catch:{ all -> 0x0033 }
            if (r0 == 0) goto L_0x0026
            int r1 = r3.size     // Catch:{ all -> 0x0036 }
            int r2 = r3.safeSizeOf(r4, r0)     // Catch:{ all -> 0x0036 }
            int r1 = r1 - r2
            r3.size = r1     // Catch:{ all -> 0x0036 }
        L_0x0026:
            monitor-exit(r3)     // Catch:{ all -> 0x0036 }
            if (r0 == 0) goto L_0x002d
            r1 = 0
            r3.entryRemoved(r1, r4, r0, r5)
        L_0x002d:
            int r1 = r3.maxSize
            r3.trimToSize(r1)
            return r0
        L_0x0033:
            r1 = move-exception
        L_0x0034:
            monitor-exit(r3)     // Catch:{ all -> 0x0036 }
            throw r1
        L_0x0036:
            r1 = move-exception
            goto L_0x0034
        L_0x0038:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "key == null || value == null"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.util.LruCache.put(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0076, code lost:
        throw new java.lang.IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void trimToSize(int r7) {
        /*
            r6 = this;
            r0 = 0
            r1 = r0
            r2 = r1
        L_0x0003:
            monitor-enter(r6)
            int r3 = r6.size     // Catch:{ all -> 0x0077 }
            if (r3 < 0) goto L_0x0056
            java.util.LinkedHashMap<K, V> r3 = r6.map     // Catch:{ all -> 0x0077 }
            boolean r3 = r3.isEmpty()     // Catch:{ all -> 0x0077 }
            if (r3 == 0) goto L_0x0014
            int r3 = r6.size     // Catch:{ all -> 0x0077 }
            if (r3 != 0) goto L_0x0056
        L_0x0014:
            int r3 = r6.size     // Catch:{ all -> 0x0077 }
            if (r3 <= r7) goto L_0x0054
            java.util.LinkedHashMap<K, V> r3 = r6.map     // Catch:{ all -> 0x0077 }
            boolean r3 = r3.isEmpty()     // Catch:{ all -> 0x0077 }
            if (r3 == 0) goto L_0x0021
            goto L_0x0054
        L_0x0021:
            java.util.LinkedHashMap<K, V> r3 = r6.map     // Catch:{ all -> 0x0077 }
            java.util.Set r3 = r3.entrySet()     // Catch:{ all -> 0x0077 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x0077 }
            java.lang.Object r3 = r3.next()     // Catch:{ all -> 0x0077 }
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3     // Catch:{ all -> 0x0077 }
            java.lang.Object r1 = r3.getKey()     // Catch:{ all -> 0x0077 }
            java.lang.Object r2 = r3.getValue()     // Catch:{ all -> 0x0052 }
            java.util.LinkedHashMap<K, V> r4 = r6.map     // Catch:{ all -> 0x007a }
            r4.remove(r1)     // Catch:{ all -> 0x007a }
            int r4 = r6.size     // Catch:{ all -> 0x007a }
            int r5 = r6.safeSizeOf(r1, r2)     // Catch:{ all -> 0x007a }
            int r4 = r4 - r5
            r6.size = r4     // Catch:{ all -> 0x007a }
            int r4 = r6.evictionCount     // Catch:{ all -> 0x007a }
            r5 = 1
            int r4 = r4 + r5
            r6.evictionCount = r4     // Catch:{ all -> 0x007a }
            monitor-exit(r6)     // Catch:{ all -> 0x007a }
            r6.entryRemoved(r5, r1, r2, r0)
            goto L_0x0003
        L_0x0052:
            r0 = move-exception
            goto L_0x0078
        L_0x0054:
            monitor-exit(r6)     // Catch:{ all -> 0x0077 }
            return
        L_0x0056:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0077 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0077 }
            r3.<init>()     // Catch:{ all -> 0x0077 }
            java.lang.Class r4 = r6.getClass()     // Catch:{ all -> 0x0077 }
            java.lang.String r4 = r4.getName()     // Catch:{ all -> 0x0077 }
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ all -> 0x0077 }
            java.lang.String r4 = ".sizeOf() is reporting inconsistent results!"
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ all -> 0x0077 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0077 }
            r0.<init>(r3)     // Catch:{ all -> 0x0077 }
            throw r0     // Catch:{ all -> 0x0077 }
        L_0x0077:
            r0 = move-exception
        L_0x0078:
            monitor-exit(r6)     // Catch:{ all -> 0x007a }
            throw r0
        L_0x007a:
            r0 = move-exception
            goto L_0x0078
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.util.LruCache.trimToSize(int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0018, code lost:
        entryRemoved(false, r6, r1, (V) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0016, code lost:
        if (r1 == null) goto L_0x001c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final V remove(K r6) {
        /*
            r5 = this;
            if (r6 == 0) goto L_0x0025
            monitor-enter(r5)
            r0 = 0
            java.util.LinkedHashMap<K, V> r1 = r5.map     // Catch:{ all -> 0x001d }
            java.lang.Object r1 = r1.remove(r6)     // Catch:{ all -> 0x001d }
            if (r1 == 0) goto L_0x0015
            int r2 = r5.size     // Catch:{ all -> 0x0023 }
            int r3 = r5.safeSizeOf(r6, r1)     // Catch:{ all -> 0x0023 }
            int r2 = r2 - r3
            r5.size = r2     // Catch:{ all -> 0x0023 }
        L_0x0015:
            monitor-exit(r5)     // Catch:{ all -> 0x0023 }
            if (r1 == 0) goto L_0x001c
            r2 = 0
            r5.entryRemoved(r2, r6, r1, r0)
        L_0x001c:
            return r1
        L_0x001d:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
        L_0x0021:
            monitor-exit(r5)     // Catch:{ all -> 0x0023 }
            throw r0
        L_0x0023:
            r0 = move-exception
            goto L_0x0021
        L_0x0025:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "key == null"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.util.LruCache.remove(java.lang.Object):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public void entryRemoved(boolean evicted, K k, V v, V v2) {
    }

    /* access modifiers changed from: protected */
    public V create(K k) {
        return null;
    }

    private int safeSizeOf(K key, V value) {
        int result = sizeOf(key, value);
        if (result >= 0) {
            return result;
        }
        throw new IllegalStateException("Negative size: " + key + "=" + value);
    }

    /* access modifiers changed from: protected */
    public int sizeOf(K k, V v) {
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
        int i;
        int accesses;
        i = this.hitCount;
        accesses = this.missCount + i;
        return String.format(Locale.US, "LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", new Object[]{Integer.valueOf(this.maxSize), Integer.valueOf(this.hitCount), Integer.valueOf(this.missCount), Integer.valueOf(accesses != 0 ? (i * 100) / accesses : 0)});
    }
}
