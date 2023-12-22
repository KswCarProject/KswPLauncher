package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.ibm.icu.text.SCSU;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* loaded from: classes.dex */
public final class Code128Writer extends OneDimensionalCodeWriter {
    private static final int CODE_CODE_A = 101;
    private static final int CODE_CODE_B = 100;
    private static final int CODE_CODE_C = 99;
    private static final int CODE_FNC_1 = 102;
    private static final int CODE_FNC_2 = 97;
    private static final int CODE_FNC_3 = 96;
    private static final int CODE_FNC_4_A = 101;
    private static final int CODE_FNC_4_B = 100;
    private static final int CODE_START_A = 103;
    private static final int CODE_START_B = 104;
    private static final int CODE_START_C = 105;
    private static final int CODE_STOP = 106;
    private static final char ESCAPE_FNC_1 = '\u00f1';
    private static final char ESCAPE_FNC_2 = '\u00f2';
    private static final char ESCAPE_FNC_3 = '\u00f3';
    private static final char ESCAPE_FNC_4 = '\u00f4';

    /* loaded from: classes.dex */
    private enum CType {
        UNCODABLE,
        ONE_DIGIT,
        TWO_DIGITS,
        FNC_1
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter, com.google.zxing.Writer
    public BitMatrix encode(String contents, BarcodeFormat format, int width, int height, Map<EncodeHintType, ?> hints) throws WriterException {
        if (format != BarcodeFormat.CODE_128) {
            throw new IllegalArgumentException("Can only encode CODE_128, but got ".concat(String.valueOf(format)));
        }
        return super.encode(contents, format, width, height, hints);
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    public boolean[] encode(String contents) {
        int patternIndex;
        int length = contents.length();
        if (length <= 0 || length > 80) {
            throw new IllegalArgumentException("Contents length should be between 1 and 80 characters, but got ".concat(String.valueOf(length)));
        }
        for (int i = 0; i < length; i++) {
            char c = contents.charAt(i);
            switch (c) {
                case SCSU.UDEFINEX /* 241 */:
                case SCSU.URESERVED /* 242 */:
                case '\u00f3':
                case '\u00f4':
                    break;
                default:
                    if (c <= '\u007f') {
                        break;
                    } else {
                        throw new IllegalArgumentException("Bad character in input: ".concat(String.valueOf(c)));
                    }
            }
        }
        Collection<int[]> patterns = new ArrayList<>();
        int checkSum = 0;
        int checkWeight = 1;
        int codeSet = 0;
        int position = 0;
        while (position < length) {
            int newCodeSet = chooseCode(contents, position, codeSet);
            if (newCodeSet == codeSet) {
                switch (contents.charAt(position)) {
                    case SCSU.UDEFINEX /* 241 */:
                        patternIndex = 102;
                        break;
                    case SCSU.URESERVED /* 242 */:
                        patternIndex = 97;
                        break;
                    case '\u00f3':
                        patternIndex = 96;
                        break;
                    case '\u00f4':
                        if (codeSet == 101) {
                            patternIndex = 101;
                            break;
                        } else {
                            patternIndex = 100;
                            break;
                        }
                    default:
                        switch (codeSet) {
                            case 100:
                                int patternIndex2 = contents.charAt(position);
                                patternIndex = patternIndex2 - 32;
                                break;
                            case 101:
                                int charAt = contents.charAt(position) - ' ';
                                patternIndex = charAt;
                                if (charAt < 0) {
                                    patternIndex += 96;
                                    break;
                                }
                                break;
                            default:
                                patternIndex = Integer.parseInt(contents.substring(position, position + 2));
                                position++;
                                break;
                        }
                }
                position++;
            } else {
                if (codeSet == 0) {
                    switch (newCodeSet) {
                        case 100:
                            patternIndex = 104;
                            break;
                        case 101:
                            patternIndex = 103;
                            break;
                        default:
                            patternIndex = 105;
                            break;
                    }
                } else {
                    patternIndex = newCodeSet;
                }
                codeSet = newCodeSet;
            }
            patterns.add(Code128Reader.CODE_PATTERNS[patternIndex]);
            checkSum += patternIndex * checkWeight;
            if (position != 0) {
                checkWeight++;
            }
        }
        patterns.add(Code128Reader.CODE_PATTERNS[checkSum % 103]);
        patterns.add(Code128Reader.CODE_PATTERNS[106]);
        int codeWidth = 0;
        for (int[] iArr : patterns) {
            for (int width : iArr) {
                codeWidth += width;
            }
        }
        boolean[] result = new boolean[codeWidth];
        int pos = 0;
        for (int[] pattern : patterns) {
            pos += appendPattern(result, pos, pattern, true);
        }
        return result;
    }

    private static CType findCType(CharSequence value, int start) {
        int last = value.length();
        if (start >= last) {
            return CType.UNCODABLE;
        }
        char c = value.charAt(start);
        if (c != '\u00f1') {
            if (c < '0' || c > '9') {
                return CType.UNCODABLE;
            }
            if (start + 1 >= last) {
                return CType.ONE_DIGIT;
            }
            char c2 = value.charAt(start + 1);
            if (c2 < '0' || c2 > '9') {
                return CType.ONE_DIGIT;
            }
            return CType.TWO_DIGITS;
        }
        return CType.FNC_1;
    }

    private static int chooseCode(CharSequence value, int start, int oldCode) {
        CType lookahead;
        CType lookahead2;
        char c;
        CType findCType = findCType(value, start);
        CType lookahead3 = findCType;
        if (findCType == CType.ONE_DIGIT) {
            return 100;
        }
        if (lookahead3 == CType.UNCODABLE) {
            return (start >= value.length() || ((c = value.charAt(start)) >= ' ' && (oldCode != 101 || c >= '`'))) ? 100 : 101;
        } else if (oldCode == 99) {
            return 99;
        } else {
            if (oldCode == 100) {
                if (lookahead3 == CType.FNC_1 || (lookahead = findCType(value, start + 2)) == CType.UNCODABLE || lookahead == CType.ONE_DIGIT) {
                    return 100;
                }
                if (lookahead == CType.FNC_1) {
                    return findCType(value, start + 3) == CType.TWO_DIGITS ? 99 : 100;
                }
                int index = start + 4;
                while (true) {
                    lookahead2 = findCType(value, index);
                    if (lookahead2 != CType.TWO_DIGITS) {
                        break;
                    }
                    index += 2;
                }
                return lookahead2 == CType.ONE_DIGIT ? 100 : 99;
            }
            if (lookahead3 == CType.FNC_1) {
                lookahead3 = findCType(value, start + 1);
            }
            return lookahead3 == CType.TWO_DIGITS ? 99 : 100;
        }
    }
}
