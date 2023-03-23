package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import kotlin.text.Typography;

final class BinaryShiftToken extends Token {
    private final short binaryShiftByteCount;
    private final short binaryShiftStart;

    BinaryShiftToken(Token previous, int binaryShiftStart2, int binaryShiftByteCount2) {
        super(previous);
        this.binaryShiftStart = (short) binaryShiftStart2;
        this.binaryShiftByteCount = (short) binaryShiftByteCount2;
    }

    public void appendTo(BitArray bitArray, byte[] text) {
        int i = 0;
        while (true) {
            short s = this.binaryShiftByteCount;
            if (i < s) {
                if (i == 0 || (i == 31 && s <= 62)) {
                    bitArray.appendBits(31, 5);
                    short s2 = this.binaryShiftByteCount;
                    if (s2 > 62) {
                        bitArray.appendBits(s2 - 31, 16);
                    } else if (i == 0) {
                        bitArray.appendBits(Math.min(s2, 31), 5);
                    } else {
                        bitArray.appendBits(s2 - 31, 5);
                    }
                }
                bitArray.appendBits(text[this.binaryShiftStart + i], 8);
                i++;
            } else {
                return;
            }
        }
    }

    public String toString() {
        return "<" + this.binaryShiftStart + "::" + ((this.binaryShiftStart + this.binaryShiftByteCount) - 1) + Typography.greater;
    }
}
