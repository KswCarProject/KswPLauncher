package com.squareup.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LruCache implements Cache {
    private int evictionCount;
    private int hitCount;
    final LinkedHashMap<String, Bitmap> map;
    private final int maxSize;
    private int missCount;
    private int putCount;
    private int size;

    public LruCache(Context context) {
        this(Utils.calculateMemoryCacheSize(context));
    }

    public LruCache(int maxSize2) {
        if (maxSize2 > 0) {
            this.maxSize = maxSize2;
            this.map = new LinkedHashMap<>(0, 0.75f, true);
            return;
        }
        throw new IllegalArgumentException("Max size must be positive.");
    }

    public Bitmap get(String key) {
        if (key != null) {
            synchronized (this) {
                try {
                    Bitmap mapValue = this.map.get(key);
                    if (mapValue != null) {
                        this.hitCount++;
                        return mapValue;
                    }
                    this.missCount++;
                    return null;
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            }
        } else {
            throw new NullPointerException("key == null");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002a, code lost:
        trimToSize(r3.maxSize);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void set(java.lang.String r4, android.graphics.Bitmap r5) {
        /*
            r3 = this;
            if (r4 == 0) goto L_0x0035
            if (r5 == 0) goto L_0x0035
            monitor-enter(r3)
            r0 = 0
            int r1 = r3.putCount     // Catch:{ all -> 0x0030 }
            int r1 = r1 + 1
            r3.putCount = r1     // Catch:{ all -> 0x0030 }
            int r1 = r3.size     // Catch:{ all -> 0x0030 }
            int r2 = com.squareup.picasso.Utils.getBitmapBytes(r5)     // Catch:{ all -> 0x0030 }
            int r1 = r1 + r2
            r3.size = r1     // Catch:{ all -> 0x0030 }
            java.util.LinkedHashMap<java.lang.String, android.graphics.Bitmap> r1 = r3.map     // Catch:{ all -> 0x0030 }
            java.lang.Object r1 = r1.put(r4, r5)     // Catch:{ all -> 0x0030 }
            android.graphics.Bitmap r1 = (android.graphics.Bitmap) r1     // Catch:{ all -> 0x0030 }
            r0 = r1
            if (r0 == 0) goto L_0x0029
            int r1 = r3.size     // Catch:{ all -> 0x0033 }
            int r2 = com.squareup.picasso.Utils.getBitmapBytes(r0)     // Catch:{ all -> 0x0033 }
            int r1 = r1 - r2
            r3.size = r1     // Catch:{ all -> 0x0033 }
        L_0x0029:
            monitor-exit(r3)     // Catch:{ all -> 0x0033 }
            int r1 = r3.maxSize
            r3.trimToSize(r1)
            return
        L_0x0030:
            r1 = move-exception
        L_0x0031:
            monitor-exit(r3)     // Catch:{ all -> 0x0033 }
            throw r1
        L_0x0033:
            r1 = move-exception
            goto L_0x0031
        L_0x0035:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "key == null || bitmap == null"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.picasso.LruCache.set(java.lang.String, android.graphics.Bitmap):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x007a, code lost:
        throw new java.lang.IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void trimToSize(int r6) {
        /*
            r5 = this;
            r0 = 0
            r1 = r0
        L_0x0002:
            monitor-enter(r5)
            int r2 = r5.size     // Catch:{ all -> 0x007b }
            if (r2 < 0) goto L_0x005a
            java.util.LinkedHashMap<java.lang.String, android.graphics.Bitmap> r2 = r5.map     // Catch:{ all -> 0x007b }
            boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x007b }
            if (r2 == 0) goto L_0x0013
            int r2 = r5.size     // Catch:{ all -> 0x007b }
            if (r2 != 0) goto L_0x005a
        L_0x0013:
            int r2 = r5.size     // Catch:{ all -> 0x007b }
            if (r2 <= r6) goto L_0x0058
            java.util.LinkedHashMap<java.lang.String, android.graphics.Bitmap> r2 = r5.map     // Catch:{ all -> 0x007b }
            boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x007b }
            if (r2 == 0) goto L_0x0020
            goto L_0x0058
        L_0x0020:
            java.util.LinkedHashMap<java.lang.String, android.graphics.Bitmap> r2 = r5.map     // Catch:{ all -> 0x007b }
            java.util.Set r2 = r2.entrySet()     // Catch:{ all -> 0x007b }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x007b }
            java.lang.Object r2 = r2.next()     // Catch:{ all -> 0x007b }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ all -> 0x007b }
            java.lang.Object r3 = r2.getKey()     // Catch:{ all -> 0x007b }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x007b }
            r0 = r3
            java.lang.Object r3 = r2.getValue()     // Catch:{ all -> 0x0056 }
            android.graphics.Bitmap r3 = (android.graphics.Bitmap) r3     // Catch:{ all -> 0x0056 }
            r1 = r3
            java.util.LinkedHashMap<java.lang.String, android.graphics.Bitmap> r3 = r5.map     // Catch:{ all -> 0x0054 }
            r3.remove(r0)     // Catch:{ all -> 0x0054 }
            int r3 = r5.size     // Catch:{ all -> 0x0054 }
            int r4 = com.squareup.picasso.Utils.getBitmapBytes(r1)     // Catch:{ all -> 0x0054 }
            int r3 = r3 - r4
            r5.size = r3     // Catch:{ all -> 0x0054 }
            int r3 = r5.evictionCount     // Catch:{ all -> 0x0054 }
            int r3 = r3 + 1
            r5.evictionCount = r3     // Catch:{ all -> 0x0054 }
            monitor-exit(r5)     // Catch:{ all -> 0x0054 }
            goto L_0x0002
        L_0x0054:
            r2 = move-exception
            goto L_0x007c
        L_0x0056:
            r2 = move-exception
            goto L_0x007c
        L_0x0058:
            monitor-exit(r5)     // Catch:{ all -> 0x007b }
            return
        L_0x005a:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException     // Catch:{ all -> 0x007b }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x007b }
            r3.<init>()     // Catch:{ all -> 0x007b }
            java.lang.Class r4 = r5.getClass()     // Catch:{ all -> 0x007b }
            java.lang.String r4 = r4.getName()     // Catch:{ all -> 0x007b }
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ all -> 0x007b }
            java.lang.String r4 = ".sizeOf() is reporting inconsistent results!"
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ all -> 0x007b }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x007b }
            r2.<init>(r3)     // Catch:{ all -> 0x007b }
            throw r2     // Catch:{ all -> 0x007b }
        L_0x007b:
            r2 = move-exception
        L_0x007c:
            monitor-exit(r5)     // Catch:{ all -> 0x007e }
            throw r2
        L_0x007e:
            r2 = move-exception
            goto L_0x007c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.picasso.LruCache.trimToSize(int):void");
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

    public final synchronized void clear() {
        evictAll();
    }

    public final synchronized void clearKeyUri(String uri) {
        boolean sizeChanged = false;
        int uriLength = uri.length();
        Iterator<Map.Entry<String, Bitmap>> i = this.map.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry<String, Bitmap> entry = i.next();
            String key = entry.getKey();
            Bitmap value = entry.getValue();
            int newlineIndex = key.indexOf(10);
            if (newlineIndex == uriLength && key.substring(0, newlineIndex).equals(uri)) {
                i.remove();
                this.size -= Utils.getBitmapBytes(value);
                sizeChanged = true;
            }
        }
        if (sizeChanged) {
            trimToSize(this.maxSize);
        }
    }

    public final synchronized int hitCount() {
        return this.hitCount;
    }

    public final synchronized int missCount() {
        return this.missCount;
    }

    public final synchronized int putCount() {
        return this.putCount;
    }

    public final synchronized int evictionCount() {
        return this.evictionCount;
    }
}
