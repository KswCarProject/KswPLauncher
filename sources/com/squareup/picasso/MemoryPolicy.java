package com.squareup.picasso;

/* loaded from: classes.dex */
public enum MemoryPolicy {
    NO_CACHE(1),
    NO_STORE(2);
    
    final int index;

    static boolean shouldReadFromMemoryCache(int memoryPolicy) {
        return (NO_CACHE.index & memoryPolicy) == 0;
    }

    static boolean shouldWriteToMemoryCache(int memoryPolicy) {
        return (NO_STORE.index & memoryPolicy) == 0;
    }

    MemoryPolicy(int index) {
        this.index = index;
    }
}
