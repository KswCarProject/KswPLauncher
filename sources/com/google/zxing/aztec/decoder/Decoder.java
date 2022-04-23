package com.google.zxing.aztec.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.aztec.AztecDetectorResult;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.PluralRules;
import java.util.Arrays;
import java.util.List;

public final class Decoder {
    private static final String[] DIGIT_TABLE = {"CTRL_PS", " ", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ",", ".", "CTRL_UL", "CTRL_US"};
    private static final String[] LOWER_TABLE = {"CTRL_PS", " ", "a", "b", "c", DateFormat.DAY, "e", "f", "g", "h", "i", DateFormat.HOUR, "k", "l", DateFormat.MINUTE, "n", "o", "p", "q", "r", DateFormat.SECOND, "t", "u", DateFormat.ABBR_GENERIC_TZ, "w", "x", DateFormat.YEAR, DateFormat.ABBR_SPECIFIC_TZ, "CTRL_US", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private static final String[] MIXED_TABLE = {"CTRL_PS", " ", "\u0001", "\u0002", "\u0003", "\u0004", "\u0005", "\u0006", "\u0007", "\b", "\t", "\n", "\u000b", "\f", "\r", "\u001b", "\u001c", "\u001d", "\u001e", "\u001f", "@", "\\", "^", "_", "`", "|", "~", "", "CTRL_LL", "CTRL_UL", "CTRL_PL", "CTRL_BS"};
    private static final String[] PUNCT_TABLE = {"", "\r", "\r\n", ". ", ", ", PluralRules.KEYWORD_RULE_SEPARATOR, "!", "\"", "#", "$", "%", "&", "'", "(", ")", "*", "+", ",", "-", ".", "/", ":", ";", "<", "=", ">", "?", "[", "]", "{", "}", "CTRL_UL"};
    private static final String[] UPPER_TABLE = {"CTRL_PS", " ", "A", "B", "C", "D", DateFormat.ABBR_WEEKDAY, "F", "G", DateFormat.HOUR24, "I", "J", "K", "L", DateFormat.NUM_MONTH, "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "CTRL_LL", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private AztecDetectorResult ddata;

    private enum Table {
        UPPER,
        LOWER,
        MIXED,
        DIGIT,
        PUNCT,
        BINARY
    }

    public DecoderResult decode(AztecDetectorResult detectorResult) throws FormatException {
        this.ddata = detectorResult;
        boolean[] correctBits = correctBits(extractBits(detectorResult.getBits()));
        boolean[] correctedBits = correctBits;
        DecoderResult decoderResult = new DecoderResult(convertBoolArrayToByteArray(correctBits), getEncodedData(correctedBits), (List<byte[]>) null, (String) null);
        DecoderResult decoderResult2 = decoderResult;
        decoderResult.setNumBits(correctedBits.length);
        return decoderResult2;
    }

    public static String highLevelDecode(boolean[] correctedBits) {
        return getEncodedData(correctedBits);
    }

    private static String getEncodedData(boolean[] zArr) {
        int length = zArr.length;
        Table table = Table.UPPER;
        Table table2 = Table.UPPER;
        StringBuilder sb = new StringBuilder(20);
        int i = 0;
        while (i < length) {
            if (table2 == Table.BINARY) {
                if (length - i < 5) {
                    break;
                }
                int readCode = readCode(zArr, i, 5);
                i += 5;
                if (readCode == 0) {
                    if (length - i < 11) {
                        break;
                    }
                    readCode = readCode(zArr, i, 11) + 31;
                    i += 11;
                }
                int i2 = 0;
                while (true) {
                    if (i2 >= readCode) {
                        break;
                    } else if (length - i < 8) {
                        i = length;
                        break;
                    } else {
                        sb.append((char) readCode(zArr, i, 8));
                        i += 8;
                        i2++;
                    }
                }
                table2 = table;
            } else {
                int i3 = table2 == Table.DIGIT ? 4 : 5;
                if (length - i < i3) {
                    break;
                }
                int readCode2 = readCode(zArr, i, i3);
                i += i3;
                String character = getCharacter(table2, readCode2);
                if (character.startsWith("CTRL_")) {
                    table = getTable(character.charAt(5));
                    if (character.charAt(6) == 'L') {
                        table2 = table;
                    } else {
                        Table table3 = table2;
                        table2 = table;
                        table = table3;
                    }
                } else {
                    sb.append(character);
                    table2 = table;
                }
            }
        }
        return sb.toString();
    }

    private static Table getTable(char t) {
        switch (t) {
            case 'B':
                return Table.BINARY;
            case 'D':
                return Table.DIGIT;
            case 'L':
                return Table.LOWER;
            case 'M':
                return Table.MIXED;
            case 'P':
                return Table.PUNCT;
            default:
                return Table.UPPER;
        }
    }

    /* renamed from: com.google.zxing.aztec.decoder.Decoder$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table;

        static {
            int[] iArr = new int[Table.values().length];
            $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table = iArr;
            try {
                iArr[Table.UPPER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[Table.LOWER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[Table.MIXED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[Table.PUNCT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[Table.DIGIT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    private static String getCharacter(Table table, int code) {
        switch (AnonymousClass1.$SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[table.ordinal()]) {
            case 1:
                return UPPER_TABLE[code];
            case 2:
                return LOWER_TABLE[code];
            case 3:
                return MIXED_TABLE[code];
            case 4:
                return PUNCT_TABLE[code];
            case 5:
                return DIGIT_TABLE[code];
            default:
                throw new IllegalStateException("Bad table");
        }
    }

    private boolean[] correctBits(boolean[] zArr) throws FormatException {
        GenericGF genericGF;
        int i = 8;
        if (this.ddata.getNbLayers() <= 2) {
            i = 6;
            genericGF = GenericGF.AZTEC_DATA_6;
        } else if (this.ddata.getNbLayers() <= 8) {
            genericGF = GenericGF.AZTEC_DATA_8;
        } else if (this.ddata.getNbLayers() <= 22) {
            i = 10;
            genericGF = GenericGF.AZTEC_DATA_10;
        } else {
            i = 12;
            genericGF = GenericGF.AZTEC_DATA_12;
        }
        int nbDatablocks = this.ddata.getNbDatablocks();
        int length = zArr.length / i;
        if (length >= nbDatablocks) {
            int length2 = zArr.length % i;
            int[] iArr = new int[length];
            int i2 = 0;
            while (i2 < length) {
                iArr[i2] = readCode(zArr, length2, i);
                i2++;
                length2 += i;
            }
            try {
                new ReedSolomonDecoder(genericGF).decode(iArr, length - nbDatablocks);
                int i3 = (1 << i) - 1;
                int i4 = 0;
                for (int i5 = 0; i5 < nbDatablocks; i5++) {
                    int i6 = iArr[i5];
                    if (i6 == 0 || i6 == i3) {
                        throw FormatException.getFormatInstance();
                    }
                    if (i6 == 1 || i6 == i3 - 1) {
                        i4++;
                    }
                }
                boolean[] zArr2 = new boolean[((nbDatablocks * i) - i4)];
                int i7 = 0;
                for (int i8 = 0; i8 < nbDatablocks; i8++) {
                    int i9 = iArr[i8];
                    if (i9 == 1 || i9 == i3 - 1) {
                        Arrays.fill(zArr2, i7, (i7 + i) - 1, i9 > 1);
                        i7 += i - 1;
                    } else {
                        int i10 = i - 1;
                        while (i10 >= 0) {
                            int i11 = i7 + 1;
                            zArr2[i7] = ((1 << i10) & i9) != 0;
                            i10--;
                            i7 = i11;
                        }
                    }
                }
                return zArr2;
            } catch (ReedSolomonException e) {
                throw FormatException.getFormatInstance(e);
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private boolean[] extractBits(BitMatrix matrix) {
        BitMatrix bitMatrix = matrix;
        boolean compact = this.ddata.isCompact();
        int layers = this.ddata.getNbLayers();
        int i = (compact ? 11 : 14) + (layers << 2);
        int baseMatrixSize = i;
        int[] alignmentMap = new int[i];
        boolean[] rawbits = new boolean[totalBitsInLayer(layers, compact)];
        int i2 = 2;
        if (compact) {
            for (int i3 = 0; i3 < alignmentMap.length; i3++) {
                alignmentMap[i3] = i3;
            }
        } else {
            int origCenter = baseMatrixSize / 2;
            int center = ((baseMatrixSize + 1) + ((((baseMatrixSize / 2) - 1) / 15) * 2)) / 2;
            for (int i4 = 0; i4 < origCenter; i4++) {
                int newOffset = (i4 / 15) + i4;
                alignmentMap[(origCenter - i4) - 1] = (center - newOffset) - 1;
                alignmentMap[origCenter + i4] = center + newOffset + 1;
            }
        }
        int i5 = 0;
        int rowOffset = 0;
        while (i5 < layers) {
            int rowSize = ((layers - i5) << i2) + (compact ? 9 : 12);
            int low = i5 << 1;
            int high = (baseMatrixSize - 1) - low;
            int j = 0;
            while (j < rowSize) {
                int columnOffset = j << 1;
                int k = 0;
                while (k < i2) {
                    rawbits[rowOffset + columnOffset + k] = bitMatrix.get(alignmentMap[low + k], alignmentMap[low + j]);
                    rawbits[(rowSize * 2) + rowOffset + columnOffset + k] = bitMatrix.get(alignmentMap[low + j], alignmentMap[high - k]);
                    rawbits[(rowSize * 4) + rowOffset + columnOffset + k] = bitMatrix.get(alignmentMap[high - k], alignmentMap[high - j]);
                    rawbits[(rowSize * 6) + rowOffset + columnOffset + k] = bitMatrix.get(alignmentMap[high - j], alignmentMap[low + k]);
                    k++;
                    i2 = 2;
                    compact = compact;
                }
                j++;
                i2 = 2;
            }
            rowOffset += rowSize << 3;
            i5++;
            i2 = 2;
        }
        return rawbits;
    }

    private static int readCode(boolean[] rawbits, int startIndex, int length) {
        int res = 0;
        for (int i = startIndex; i < startIndex + length; i++) {
            res <<= 1;
            if (rawbits[i]) {
                res |= 1;
            }
        }
        return res;
    }

    private static byte readByte(boolean[] rawbits, int startIndex) {
        int length = rawbits.length - startIndex;
        int n = length;
        if (length >= 8) {
            return (byte) readCode(rawbits, startIndex, 8);
        }
        return (byte) (readCode(rawbits, startIndex, n) << (8 - n));
    }

    static byte[] convertBoolArrayToByteArray(boolean[] boolArr) {
        byte[] byteArr = new byte[((boolArr.length + 7) / 8)];
        for (int i = 0; i < byteArr.length; i++) {
            byteArr[i] = readByte(boolArr, i << 3);
        }
        return byteArr;
    }

    private static int totalBitsInLayer(int layers, boolean compact) {
        return ((compact ? 88 : 112) + (layers << 4)) * layers;
    }
}
