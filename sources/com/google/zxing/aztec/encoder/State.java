package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import java.util.Deque;
import java.util.LinkedList;

/* loaded from: classes.dex */
final class State {
    static final State INITIAL_STATE = new State(Token.EMPTY, 0, 0, 0);
    private final int binaryShiftByteCount;
    private final int bitCount;
    private final int mode;
    private final Token token;

    private State(Token token, int mode, int binaryBytes, int bitCount) {
        this.token = token;
        this.mode = mode;
        this.binaryShiftByteCount = binaryBytes;
        this.bitCount = bitCount;
    }

    int getMode() {
        return this.mode;
    }

    Token getToken() {
        return this.token;
    }

    int getBinaryShiftByteCount() {
        return this.binaryShiftByteCount;
    }

    int getBitCount() {
        return this.bitCount;
    }

    State latchAndAppend(int mode, int value) {
        int bitCount = this.bitCount;
        Token token = this.token;
        if (mode != this.mode) {
            int latch = HighLevelEncoder.LATCH_TABLE[this.mode][mode];
            token = token.add(65535 & latch, latch >> 16);
            bitCount += latch >> 16;
        }
        int latchModeBitCount = mode == 2 ? 4 : 5;
        return new State(token.add(value, latchModeBitCount), mode, 0, bitCount + latchModeBitCount);
    }

    State shiftAndAppend(int mode, int value) {
        Token token = this.token;
        int thisModeBitCount = this.mode == 2 ? 4 : 5;
        return new State(token.add(HighLevelEncoder.SHIFT_TABLE[this.mode][mode], thisModeBitCount).add(value, 5), this.mode, 0, this.bitCount + thisModeBitCount + 5);
    }

    State addBinaryShiftChar(int index) {
        Token token = this.token;
        int mode = this.mode;
        int bitCount = this.bitCount;
        int i = this.mode;
        if (i == 4 || i == 2) {
            int latch = HighLevelEncoder.LATCH_TABLE[mode][0];
            token = token.add(65535 & latch, latch >> 16);
            bitCount += latch >> 16;
            mode = 0;
        }
        int latch2 = this.binaryShiftByteCount;
        int deltaBitCount = (latch2 == 0 || latch2 == 31) ? 18 : latch2 == 62 ? 9 : 8;
        State result = new State(token, mode, this.binaryShiftByteCount + 1, bitCount + deltaBitCount);
        return result.binaryShiftByteCount == 2078 ? result.endBinaryShift(index + 1) : result;
    }

    State endBinaryShift(int index) {
        int i = this.binaryShiftByteCount;
        if (i == 0) {
            return this;
        }
        Token token = this.token.addBinaryShift(index - i, i);
        return new State(token, this.mode, 0, this.bitCount);
    }

    boolean isBetterThanOrEqualTo(State other) {
        int i;
        int mySize = this.bitCount + (HighLevelEncoder.LATCH_TABLE[this.mode][other.mode] >> 16);
        int i2 = other.binaryShiftByteCount;
        if (i2 > 0 && ((i = this.binaryShiftByteCount) == 0 || i > i2)) {
            mySize += 10;
        }
        return mySize <= other.bitCount;
    }

    BitArray toBitArray(byte[] text) {
        Deque<Token> symbols = new LinkedList<>();
        for (Token token = endBinaryShift(text.length).token; token != null; token = token.getPrevious()) {
            symbols.addFirst(token);
        }
        BitArray bitArray = new BitArray();
        for (Token token2 : symbols) {
            token2.appendTo(bitArray, text);
        }
        return bitArray;
    }

    public String toString() {
        return String.format("%s bits=%d bytes=%d", HighLevelEncoder.MODE_NAMES[this.mode], Integer.valueOf(this.bitCount), Integer.valueOf(this.binaryShiftByteCount));
    }
}
