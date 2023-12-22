package com.google.zxing.common;

import kotlin.UByte;

/* loaded from: classes.dex */
public final class BitSource {
    private int bitOffset;
    private int byteOffset;
    private final byte[] bytes;

    public BitSource(byte[] bytes) {
        this.bytes = bytes;
    }

    public int getBitOffset() {
        return this.bitOffset;
    }

    public int getByteOffset() {
        return this.byteOffset;
    }

    public int readBits(int numBits) {
        if (numBits <= 0 || numBits > 32 || numBits > available()) {
            throw new IllegalArgumentException(String.valueOf(numBits));
        }
        int result = 0;
        int i = this.bitOffset;
        if (i > 0) {
            int bitsLeft = 8 - i;
            int toRead = numBits < bitsLeft ? numBits : bitsLeft;
            int bitsToNotRead = bitsLeft - toRead;
            int mask = (255 >> (8 - toRead)) << bitsToNotRead;
            byte[] bArr = this.bytes;
            int i2 = this.byteOffset;
            result = (bArr[i2] & mask) >> bitsToNotRead;
            numBits -= toRead;
            int i3 = i + toRead;
            this.bitOffset = i3;
            if (i3 == 8) {
                this.bitOffset = 0;
                this.byteOffset = i2 + 1;
            }
        }
        if (numBits > 0) {
            while (numBits >= 8) {
                byte[] bArr2 = this.bytes;
                int i4 = this.byteOffset;
                result = (result << 8) | (bArr2[i4] & UByte.MAX_VALUE);
                this.byteOffset = i4 + 1;
                numBits -= 8;
            }
            if (numBits > 0) {
                int bitsToNotRead2 = 8 - numBits;
                int mask2 = (255 >> bitsToNotRead2) << bitsToNotRead2;
                int result2 = (result << numBits) | ((this.bytes[this.byteOffset] & mask2) >> bitsToNotRead2);
                this.bitOffset += numBits;
                return result2;
            }
            return result;
        }
        return result;
    }

    public int available() {
        return ((this.bytes.length - this.byteOffset) * 8) - this.bitOffset;
    }
}
