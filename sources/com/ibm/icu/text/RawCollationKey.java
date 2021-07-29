package com.ibm.icu.text;

import com.ibm.icu.util.ByteArrayWrapper;

public final class RawCollationKey extends ByteArrayWrapper {
    public RawCollationKey() {
    }

    public RawCollationKey(int capacity) {
        this.bytes = new byte[capacity];
    }

    public RawCollationKey(byte[] bytes) {
        this.bytes = bytes;
    }

    public RawCollationKey(byte[] bytesToAdopt, int size) {
        super(bytesToAdopt, size);
    }

    public int compareTo(RawCollationKey rhs) {
        int result = RawCollationKey.super.compareTo(rhs);
        if (result < 0) {
            return -1;
        }
        return result == 0 ? 0 : 1;
    }
}
