package com.google.zxing.common;

import java.util.Arrays;

/* loaded from: classes.dex */
public final class BitArray implements Cloneable {
    private int[] bits;
    private int size;

    public BitArray() {
        this.size = 0;
        this.bits = new int[1];
    }

    public BitArray(int size) {
        this.size = size;
        this.bits = makeArray(size);
    }

    BitArray(int[] bits, int size) {
        this.bits = bits;
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }

    public int getSizeInBytes() {
        return (this.size + 7) / 8;
    }

    private void ensureCapacity(int size) {
        if (size > (this.bits.length << 5)) {
            int[] newBits = makeArray(size);
            int[] iArr = this.bits;
            System.arraycopy(iArr, 0, newBits, 0, iArr.length);
            this.bits = newBits;
        }
    }

    public boolean get(int i) {
        return (this.bits[i / 32] & (1 << (i & 31))) != 0;
    }

    public void set(int i) {
        int[] iArr = this.bits;
        int i2 = i / 32;
        iArr[i2] = iArr[i2] | (1 << (i & 31));
    }

    public void flip(int i) {
        int[] iArr = this.bits;
        int i2 = i / 32;
        iArr[i2] = iArr[i2] ^ (1 << (i & 31));
    }

    public int getNextSet(int from) {
        int i = this.size;
        if (from >= i) {
            return i;
        }
        int bitsOffset = from / 32;
        int currentBits = this.bits[bitsOffset] & (~((1 << (from & 31)) - 1));
        while (currentBits == 0) {
            bitsOffset++;
            int[] iArr = this.bits;
            if (bitsOffset == iArr.length) {
                return this.size;
            }
            currentBits = iArr[bitsOffset];
        }
        int result = (bitsOffset << 5) + Integer.numberOfTrailingZeros(currentBits);
        int i2 = this.size;
        return result > i2 ? i2 : result;
    }

    public int getNextUnset(int from) {
        int i = this.size;
        if (from >= i) {
            return i;
        }
        int bitsOffset = from / 32;
        int currentBits = (~this.bits[bitsOffset]) & (~((1 << (from & 31)) - 1));
        while (currentBits == 0) {
            bitsOffset++;
            int[] iArr = this.bits;
            if (bitsOffset == iArr.length) {
                return this.size;
            }
            currentBits = ~iArr[bitsOffset];
        }
        int result = (bitsOffset << 5) + Integer.numberOfTrailingZeros(currentBits);
        int i2 = this.size;
        return result > i2 ? i2 : result;
    }

    public void setBulk(int i, int newBits) {
        this.bits[i / 32] = newBits;
    }

    public void setRange(int start, int end) {
        if (end < start || start < 0 || end > this.size) {
            throw new IllegalArgumentException();
        }
        if (end == start) {
            return;
        }
        int end2 = end - 1;
        int firstInt = start / 32;
        int lastInt = end2 / 32;
        int i = firstInt;
        while (i <= lastInt) {
            int firstBit = i > firstInt ? 0 : start & 31;
            int lastBit = i < lastInt ? 31 : end2 & 31;
            int mask = (2 << lastBit) - (1 << firstBit);
            int[] iArr = this.bits;
            iArr[i] = iArr[i] | mask;
            i++;
        }
    }

    public void clear() {
        int max = this.bits.length;
        for (int i = 0; i < max; i++) {
            this.bits[i] = 0;
        }
    }

    public boolean isRange(int start, int end, boolean value) {
        if (end < start || start < 0 || end > this.size) {
            throw new IllegalArgumentException();
        }
        if (end == start) {
            return true;
        }
        int end2 = end - 1;
        int firstInt = start / 32;
        int lastInt = end2 / 32;
        int i = firstInt;
        while (i <= lastInt) {
            int firstBit = i > firstInt ? 0 : start & 31;
            int lastBit = i < lastInt ? 31 : end2 & 31;
            int mask = (2 << lastBit) - (1 << firstBit);
            if ((this.bits[i] & mask) == (value ? mask : 0)) {
                i++;
            } else {
                return false;
            }
        }
        return true;
    }

    public void appendBit(boolean bit) {
        ensureCapacity(this.size + 1);
        if (bit) {
            int[] iArr = this.bits;
            int i = this.size;
            int i2 = i / 32;
            iArr[i2] = (1 << (i & 31)) | iArr[i2];
        }
        this.size++;
    }

    public void appendBits(int value, int numBits) {
        if (numBits < 0 || numBits > 32) {
            throw new IllegalArgumentException("Num bits must be between 0 and 32");
        }
        ensureCapacity(this.size + numBits);
        for (int numBitsLeft = numBits; numBitsLeft > 0; numBitsLeft--) {
            boolean z = true;
            if (((value >> (numBitsLeft - 1)) & 1) != 1) {
                z = false;
            }
            appendBit(z);
        }
    }

    public void appendBitArray(BitArray other) {
        int otherSize = other.size;
        ensureCapacity(this.size + otherSize);
        for (int i = 0; i < otherSize; i++) {
            appendBit(other.get(i));
        }
    }

    public void xor(BitArray other) {
        if (this.size != other.size) {
            throw new IllegalArgumentException("Sizes don't match");
        }
        int i = 0;
        while (true) {
            int[] iArr = this.bits;
            if (i < iArr.length) {
                iArr[i] = iArr[i] ^ other.bits[i];
                i++;
            } else {
                return;
            }
        }
    }

    public void toBytes(int bitOffset, byte[] array, int offset, int numBytes) {
        for (int i = 0; i < numBytes; i++) {
            int theByte = 0;
            for (int j = 0; j < 8; j++) {
                if (get(bitOffset)) {
                    theByte |= 1 << (7 - j);
                }
                bitOffset++;
            }
            int j2 = offset + i;
            array[j2] = (byte) theByte;
        }
    }

    public int[] getBitArray() {
        return this.bits;
    }

    public void reverse() {
        int[] newBits = new int[this.bits.length];
        int len = (this.size - 1) / 32;
        int oldBitsLen = len + 1;
        for (int i = 0; i < oldBitsLen; i++) {
            long x = this.bits[i];
            long x2 = ((x >> 1) & 1431655765) | ((1431655765 & x) << 1);
            long x3 = ((x2 >> 2) & 858993459) | ((858993459 & x2) << 2);
            long x4 = ((x3 >> 4) & 252645135) | ((252645135 & x3) << 4);
            long x5 = ((x4 >> 8) & 16711935) | ((16711935 & x4) << 8);
            newBits[len - i] = (int) (((x5 >> 16) & 65535) | ((65535 & x5) << 16));
        }
        int i2 = this.size;
        if (i2 != (oldBitsLen << 5)) {
            int leftOffset = (oldBitsLen << 5) - i2;
            int currentInt = newBits[0] >>> leftOffset;
            for (int i3 = 1; i3 < oldBitsLen; i3++) {
                int nextInt = newBits[i3];
                newBits[i3 - 1] = currentInt | (nextInt << (32 - leftOffset));
                currentInt = nextInt >>> leftOffset;
            }
            int i4 = oldBitsLen - 1;
            newBits[i4] = currentInt;
        }
        this.bits = newBits;
    }

    private static int[] makeArray(int size) {
        return new int[(size + 31) / 32];
    }

    public boolean equals(Object o) {
        if (o instanceof BitArray) {
            BitArray other = (BitArray) o;
            return this.size == other.size && Arrays.equals(this.bits, other.bits);
        }
        return false;
    }

    public int hashCode() {
        return (this.size * 31) + Arrays.hashCode(this.bits);
    }

    public String toString() {
        int i = this.size;
        StringBuilder result = new StringBuilder(i + (i / 8) + 1);
        for (int i2 = 0; i2 < this.size; i2++) {
            if ((i2 & 7) == 0) {
                result.append(' ');
            }
            result.append(get(i2) ? 'X' : '.');
        }
        return result.toString();
    }

    /* renamed from: clone */
    public BitArray m71clone() {
        return new BitArray((int[]) this.bits.clone(), this.size);
    }
}
