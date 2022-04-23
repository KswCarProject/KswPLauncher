package com.google.zxing.pdf417.decoder;

import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.pdf417.PDF417Common;
import java.lang.reflect.Array;

final class PDF417CodewordDecoder {
    private static final float[][] RATIOS_TABLE;

    static {
        int length = PDF417Common.SYMBOL_TABLE.length;
        int[] iArr = new int[2];
        iArr[1] = 8;
        iArr[0] = length;
        RATIOS_TABLE = (float[][]) Array.newInstance(float.class, iArr);
        for (int i = 0; i < PDF417Common.SYMBOL_TABLE.length; i++) {
            int i2 = PDF417Common.SYMBOL_TABLE[i];
            int currentSymbol = i2;
            int currentBit = i2 & 1;
            for (int j = 0; j < 8; j++) {
                float size = 0.0f;
                while ((currentSymbol & 1) == currentBit) {
                    size += 1.0f;
                    currentSymbol >>= 1;
                }
                currentBit = currentSymbol & 1;
                RATIOS_TABLE[i][(8 - j) - 1] = size / 17.0f;
            }
        }
    }

    private PDF417CodewordDecoder() {
    }

    static int getDecodedValue(int[] moduleBitCount) {
        int decodedCodewordValue = getDecodedCodewordValue(sampleBitCounts(moduleBitCount));
        int decodedValue = decodedCodewordValue;
        if (decodedCodewordValue != -1) {
            return decodedValue;
        }
        return getClosestDecodedValue(moduleBitCount);
    }

    private static int[] sampleBitCounts(int[] moduleBitCount) {
        float bitCountSum = (float) MathUtils.sum(moduleBitCount);
        int[] result = new int[8];
        int bitCountIndex = 0;
        int sumPreviousBits = 0;
        for (int i = 0; i < 17; i++) {
            if (((float) (moduleBitCount[bitCountIndex] + sumPreviousBits)) <= (bitCountSum / 34.0f) + ((((float) i) * bitCountSum) / 17.0f)) {
                sumPreviousBits += moduleBitCount[bitCountIndex];
                bitCountIndex++;
            }
            result[bitCountIndex] = result[bitCountIndex] + 1;
        }
        return result;
    }

    private static int getDecodedCodewordValue(int[] moduleBitCount) {
        int bitValue = getBitValue(moduleBitCount);
        int decodedValue = bitValue;
        if (PDF417Common.getCodeword(bitValue) == -1) {
            return -1;
        }
        return decodedValue;
    }

    private static int getBitValue(int[] moduleBitCount) {
        long result = 0;
        for (int i = 0; i < moduleBitCount.length; i++) {
            for (int bit = 0; bit < moduleBitCount[i]; bit++) {
                int i2 = 1;
                long j = result << 1;
                if (i % 2 != 0) {
                    i2 = 0;
                }
                result = j | ((long) i2);
            }
        }
        return (int) result;
    }

    private static int getClosestDecodedValue(int[] moduleBitCount) {
        int bitCountSum = MathUtils.sum(moduleBitCount);
        float[] bitCountRatios = new float[8];
        if (bitCountSum > 1) {
            for (int i = 0; i < 8; i++) {
                bitCountRatios[i] = ((float) moduleBitCount[i]) / ((float) bitCountSum);
            }
        }
        float bestMatchError = Float.MAX_VALUE;
        int bestMatch = -1;
        int j = 0;
        while (true) {
            float[][] fArr = RATIOS_TABLE;
            if (j >= fArr.length) {
                return bestMatch;
            }
            float error = 0.0f;
            float[] ratioTableRow = fArr[j];
            for (int k = 0; k < 8; k++) {
                float diff = ratioTableRow[k] - bitCountRatios[k];
                float f = (diff * diff) + error;
                error = f;
                if (f >= bestMatchError) {
                    break;
                }
            }
            if (error < bestMatchError) {
                bestMatchError = error;
                bestMatch = PDF417Common.SYMBOL_TABLE[j];
            }
            j++;
        }
    }
}
