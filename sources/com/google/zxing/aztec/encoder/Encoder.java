package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;

/* loaded from: classes.dex */
public final class Encoder {
    public static final int DEFAULT_AZTEC_LAYERS = 0;
    public static final int DEFAULT_EC_PERCENT = 33;
    private static final int MAX_NB_BITS = 32;
    private static final int MAX_NB_BITS_COMPACT = 4;
    private static final int[] WORD_SIZE = {4, 6, 6, 8, 8, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};

    private Encoder() {
    }

    public static AztecCode encode(byte[] data) {
        return encode(data, 33, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static AztecCode encode(byte[] bArr, int i, int i2) {
        BitArray bitArray;
        int i3;
        boolean z;
        int i4;
        int i5;
        int i6;
        int i7;
        BitArray encode = new HighLevelEncoder(bArr).encode();
        int size = ((encode.getSize() * i) / 100) + 11;
        int size2 = encode.getSize() + size;
        int i8 = 0;
        int i9 = 1;
        if (i2 != 0) {
            z = i2 < 0;
            i4 = Math.abs(i2);
            if (i4 > (z ? 4 : 32)) {
                throw new IllegalArgumentException(String.format("Illegal value %s for layers", Integer.valueOf(i2)));
            }
            i5 = totalBitsInLayer(i4, z);
            i3 = WORD_SIZE[i4];
            int i10 = i5 - (i5 % i3);
            bitArray = stuffBits(encode, i3);
            if (bitArray.getSize() + size > i10) {
                throw new IllegalArgumentException("Data to large for user specified layer");
            }
            if (z && bitArray.getSize() > (i3 << 6)) {
                throw new IllegalArgumentException("Data to large for user specified layer");
            }
        } else {
            BitArray bitArray2 = null;
            int i11 = 0;
            int i12 = 0;
            while (i11 <= 32) {
                boolean z2 = i11 <= 3 ? i9 : i8;
                int i13 = z2 ? i11 + 1 : i11;
                int i14 = totalBitsInLayer(i13, z2);
                if (size2 <= i14) {
                    if (bitArray2 == null || i12 != WORD_SIZE[i13]) {
                        int i15 = WORD_SIZE[i13];
                        i12 = i15;
                        bitArray2 = stuffBits(encode, i15);
                    }
                    int i16 = i14 - (i14 % i12);
                    if ((!z2 || bitArray2.getSize() <= (i12 << 6)) && bitArray2.getSize() + size <= i16) {
                        bitArray = bitArray2;
                        i3 = i12;
                        z = z2;
                        i4 = i13;
                        i5 = i14;
                        i8 = i8;
                    }
                }
                i11++;
                i9 = i9;
                i8 = 0;
            }
            throw new IllegalArgumentException("Data too large for an Aztec code");
        }
        BitArray generateCheckWords = generateCheckWords(bitArray, i5, i3);
        int size3 = bitArray.getSize() / i3;
        BitArray generateModeMessage = generateModeMessage(z, i4, size3);
        int i17 = (z ? 11 : 14) + (i4 << 2);
        int[] iArr = new int[i17];
        int i18 = 2;
        if (!z) {
            int i19 = i17 / 2;
            i6 = i17 + 1 + (((i19 - 1) / 15) * 2);
            int i20 = i6 / 2;
            for (int i21 = i8; i21 < i19; i21++) {
                iArr[(i19 - i21) - i9] = (i20 - i7) - 1;
                iArr[i19 + i21] = (i21 / 15) + i21 + i20 + i9;
            }
        } else {
            for (int i22 = i8; i22 < i17; i22++) {
                iArr[i22] = i22;
            }
            i6 = i17;
        }
        BitMatrix bitMatrix = new BitMatrix(i6);
        int i23 = i8;
        int i24 = i23;
        while (i23 < i4) {
            int i25 = ((i4 - i23) << i18) + (z ? 9 : 12);
            int i26 = i8;
            while (i26 < i25) {
                int i27 = i26 << 1;
                while (i8 < i18) {
                    if (generateCheckWords.get(i24 + i27 + i8)) {
                        int i28 = i23 << 1;
                        bitMatrix.set(iArr[i28 + i8], iArr[i28 + i26]);
                    }
                    if (generateCheckWords.get((i25 << 1) + i24 + i27 + i8)) {
                        int i29 = i23 << 1;
                        bitMatrix.set(iArr[i29 + i26], iArr[((i17 - 1) - i29) - i8]);
                    }
                    if (generateCheckWords.get((i25 << 2) + i24 + i27 + i8)) {
                        int i30 = (i17 - 1) - (i23 << 1);
                        bitMatrix.set(iArr[i30 - i8], iArr[i30 - i26]);
                    }
                    if (generateCheckWords.get((i25 * 6) + i24 + i27 + i8)) {
                        int i31 = i23 << 1;
                        bitMatrix.set(iArr[((i17 - 1) - i31) - i26], iArr[i31 + i8]);
                    }
                    i8++;
                    i18 = 2;
                }
                i26++;
                i8 = 0;
                i18 = 2;
            }
            i24 += i25 << 3;
            i23++;
            i8 = 0;
            i18 = 2;
        }
        drawModeMessage(bitMatrix, z, i6, generateModeMessage);
        if (z) {
            drawBullsEye(bitMatrix, i6 / 2, 5);
        } else {
            int i32 = i6 / 2;
            drawBullsEye(bitMatrix, i32, 7);
            int i33 = 0;
            int i34 = 0;
            while (i34 < (i17 / 2) - 1) {
                for (int i35 = i32 & 1; i35 < i6; i35 += 2) {
                    int i36 = i32 - i33;
                    bitMatrix.set(i36, i35);
                    int i37 = i32 + i33;
                    bitMatrix.set(i37, i35);
                    bitMatrix.set(i35, i36);
                    bitMatrix.set(i35, i37);
                }
                i34 += 15;
                i33 += 16;
            }
        }
        AztecCode aztecCode = new AztecCode();
        aztecCode.setCompact(z);
        aztecCode.setSize(i6);
        aztecCode.setLayers(i4);
        aztecCode.setCodeWords(size3);
        aztecCode.setMatrix(bitMatrix);
        return aztecCode;
    }

    private static void drawBullsEye(BitMatrix matrix, int center, int size) {
        for (int i = 0; i < size; i += 2) {
            for (int j = center - i; j <= center + i; j++) {
                matrix.set(j, center - i);
                matrix.set(j, center + i);
                matrix.set(center - i, j);
                matrix.set(center + i, j);
            }
        }
        int i2 = center - size;
        matrix.set(i2, center - size);
        matrix.set((center - size) + 1, center - size);
        matrix.set(center - size, (center - size) + 1);
        matrix.set(center + size, center - size);
        matrix.set(center + size, (center - size) + 1);
        matrix.set(center + size, (center + size) - 1);
    }

    static BitArray generateModeMessage(boolean compact, int layers, int messageSizeInWords) {
        BitArray modeMessage = new BitArray();
        if (compact) {
            modeMessage.appendBits(layers - 1, 2);
            modeMessage.appendBits(messageSizeInWords - 1, 6);
            return generateCheckWords(modeMessage, 28, 4);
        }
        modeMessage.appendBits(layers - 1, 5);
        modeMessage.appendBits(messageSizeInWords - 1, 11);
        return generateCheckWords(modeMessage, 40, 4);
    }

    private static void drawModeMessage(BitMatrix matrix, boolean compact, int matrixSize, BitArray modeMessage) {
        int center = matrixSize / 2;
        if (compact) {
            for (int i = 0; i < 7; i++) {
                int offset = (center - 3) + i;
                if (modeMessage.get(i)) {
                    matrix.set(offset, center - 5);
                }
                if (modeMessage.get(i + 7)) {
                    matrix.set(center + 5, offset);
                }
                if (modeMessage.get(20 - i)) {
                    matrix.set(offset, center + 5);
                }
                if (modeMessage.get(27 - i)) {
                    matrix.set(center - 5, offset);
                }
            }
            return;
        }
        for (int i2 = 0; i2 < 10; i2++) {
            int offset2 = (center - 5) + i2 + (i2 / 5);
            if (modeMessage.get(i2)) {
                matrix.set(offset2, center - 7);
            }
            if (modeMessage.get(i2 + 10)) {
                matrix.set(center + 7, offset2);
            }
            if (modeMessage.get(29 - i2)) {
                matrix.set(offset2, center + 7);
            }
            if (modeMessage.get(39 - i2)) {
                matrix.set(center - 7, offset2);
            }
        }
    }

    private static BitArray generateCheckWords(BitArray bitArray, int totalBits, int wordSize) {
        int messageSizeInWords = bitArray.getSize() / wordSize;
        ReedSolomonEncoder rs = new ReedSolomonEncoder(getGF(wordSize));
        int totalWords = totalBits / wordSize;
        int[] messageWords = bitsToWords(bitArray, wordSize, totalWords);
        rs.encode(messageWords, totalWords - messageSizeInWords);
        int startPad = totalBits % wordSize;
        BitArray messageBits = new BitArray();
        messageBits.appendBits(0, startPad);
        for (int messageWord : messageWords) {
            messageBits.appendBits(messageWord, wordSize);
        }
        return messageBits;
    }

    private static int[] bitsToWords(BitArray stuffedBits, int wordSize, int totalWords) {
        int[] message = new int[totalWords];
        int n = stuffedBits.getSize() / wordSize;
        for (int i = 0; i < n; i++) {
            int value = 0;
            for (int j = 0; j < wordSize; j++) {
                value |= stuffedBits.get((i * wordSize) + j) ? 1 << ((wordSize - j) - 1) : 0;
            }
            message[i] = value;
        }
        return message;
    }

    private static GenericGF getGF(int wordSize) {
        switch (wordSize) {
            case 4:
                return GenericGF.AZTEC_PARAM;
            case 5:
            case 7:
            case 9:
            case 11:
            default:
                throw new IllegalArgumentException("Unsupported word size ".concat(String.valueOf(wordSize)));
            case 6:
                return GenericGF.AZTEC_DATA_6;
            case 8:
                return GenericGF.AZTEC_DATA_8;
            case 10:
                return GenericGF.AZTEC_DATA_10;
            case 12:
                return GenericGF.AZTEC_DATA_12;
        }
    }

    static BitArray stuffBits(BitArray bits, int wordSize) {
        BitArray out = new BitArray();
        int n = bits.getSize();
        int mask = (1 << wordSize) - 2;
        int i = 0;
        while (i < n) {
            int word = 0;
            for (int j = 0; j < wordSize; j++) {
                if (i + j >= n || bits.get(i + j)) {
                    word |= 1 << ((wordSize - 1) - j);
                }
            }
            int j2 = word & mask;
            if (j2 == mask) {
                out.appendBits(word & mask, wordSize);
                i--;
            } else if ((word & mask) == 0) {
                out.appendBits(word | 1, wordSize);
                i--;
            } else {
                out.appendBits(word, wordSize);
            }
            i += wordSize;
        }
        return out;
    }

    private static int totalBitsInLayer(int layers, boolean compact) {
        return ((compact ? 88 : 112) + (layers << 4)) * layers;
    }
}
