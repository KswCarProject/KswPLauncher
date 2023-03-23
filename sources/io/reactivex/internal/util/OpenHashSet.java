package io.reactivex.internal.util;

public final class OpenHashSet<T> {
    private static final int INT_PHI = -1640531527;
    T[] keys;
    final float loadFactor;
    int mask;
    int maxSize;
    int size;

    public OpenHashSet() {
        this(16, 0.75f);
    }

    public OpenHashSet(int capacity) {
        this(capacity, 0.75f);
    }

    public OpenHashSet(int capacity, float loadFactor2) {
        this.loadFactor = loadFactor2;
        int c = Pow2.roundToPowerOfTwo(capacity);
        this.mask = c - 1;
        this.maxSize = (int) (((float) c) * loadFactor2);
        this.keys = (Object[]) new Object[c];
    }

    public boolean add(T value) {
        T curr;
        T[] a = this.keys;
        int m = this.mask;
        int pos = mix(value.hashCode()) & m;
        T curr2 = a[pos];
        if (curr2 != null) {
            if (curr2.equals(value)) {
                return false;
            }
            do {
                pos = (pos + 1) & m;
                curr = a[pos];
                if (curr == null) {
                }
            } while (!curr.equals(value));
            return false;
        }
        a[pos] = value;
        int i = this.size + 1;
        this.size = i;
        if (i >= this.maxSize) {
            rehash();
        }
        return true;
    }

    public boolean remove(T value) {
        T curr;
        T[] a = this.keys;
        int m = this.mask;
        int pos = mix(value.hashCode()) & m;
        T curr2 = a[pos];
        if (curr2 == null) {
            return false;
        }
        if (curr2.equals(value)) {
            return removeEntry(pos, a, m);
        }
        do {
            pos = (pos + 1) & m;
            curr = a[pos];
            if (curr == null) {
                return false;
            }
        } while (!curr.equals(value));
        return removeEntry(pos, a, m);
    }

    /* access modifiers changed from: package-private */
    public boolean removeEntry(int pos, T[] a, int m) {
        T curr;
        this.size--;
        while (true) {
            int last = pos;
            pos = (pos + 1) & m;
            while (true) {
                curr = a[pos];
                if (curr == null) {
                    a[last] = null;
                    return true;
                }
                int slot = mix(curr.hashCode()) & m;
                if (last <= pos) {
                    if (last >= slot || slot > pos) {
                        break;
                    }
                    pos = (pos + 1) & m;
                } else {
                    if (last >= slot && slot > pos) {
                        break;
                    }
                    pos = (pos + 1) & m;
                }
            }
            a[last] = curr;
        }
    }

    /* access modifiers changed from: package-private */
    public void rehash() {
        T[] a = this.keys;
        int i = a.length;
        int newCap = i << 1;
        int m = newCap - 1;
        T[] b = (Object[]) new Object[newCap];
        int pos = this.size;
        while (true) {
            int j = pos - 1;
            if (pos != 0) {
                do {
                    i--;
                } while (a[i] == null);
                int pos2 = mix(a[i].hashCode()) & m;
                if (b[pos2] != null) {
                    do {
                        pos2 = (pos2 + 1) & m;
                    } while (b[pos2] != null);
                }
                b[pos2] = a[i];
                pos = j;
            } else {
                this.mask = m;
                this.maxSize = (int) (((float) newCap) * this.loadFactor);
                this.keys = b;
                return;
            }
        }
    }

    static int mix(int x) {
        int h = INT_PHI * x;
        return (h >>> 16) ^ h;
    }

    public Object[] keys() {
        return this.keys;
    }

    public int size() {
        return this.size;
    }
}
