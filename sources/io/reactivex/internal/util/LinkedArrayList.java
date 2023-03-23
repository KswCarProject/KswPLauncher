package io.reactivex.internal.util;

import java.util.ArrayList;
import java.util.List;

public class LinkedArrayList {
    final int capacityHint;
    Object[] head;
    int indexInTail;
    volatile int size;
    Object[] tail;

    public LinkedArrayList(int capacityHint2) {
        this.capacityHint = capacityHint2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void add(java.lang.Object r5) {
        /*
            r4 = this;
            int r0 = r4.size
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0016
            int r0 = r4.capacityHint
            int r0 = r0 + r2
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r4.head = r0
            r4.tail = r0
            r0[r1] = r5
            r4.indexInTail = r2
            r4.size = r2
            goto L_0x003c
        L_0x0016:
            int r0 = r4.indexInTail
            int r3 = r4.capacityHint
            if (r0 != r3) goto L_0x0030
            int r0 = r3 + 1
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r0[r1] = r5
            java.lang.Object[] r1 = r4.tail
            r1[r3] = r0
            r4.tail = r0
            r4.indexInTail = r2
            int r1 = r4.size
            int r1 = r1 + r2
            r4.size = r1
            goto L_0x003c
        L_0x0030:
            java.lang.Object[] r1 = r4.tail
            r1[r0] = r5
            int r0 = r0 + r2
            r4.indexInTail = r0
            int r0 = r4.size
            int r0 = r0 + r2
            r4.size = r0
        L_0x003c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.util.LinkedArrayList.add(java.lang.Object):void");
    }

    public Object[] head() {
        return this.head;
    }

    public int size() {
        return this.size;
    }

    public String toString() {
        int cap = this.capacityHint;
        int s = this.size;
        List<Object> list = new ArrayList<>(s + 1);
        Object[] h = head();
        int j = 0;
        int k = 0;
        while (j < s) {
            list.add(h[k]);
            j++;
            k++;
            if (k == cap) {
                k = 0;
                h = (Object[]) h[cap];
            }
        }
        return list.toString();
    }
}
