package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import com.wits.pms.statuscontrol.WitsCommand;
import java.util.Arrays;
import java.util.Map;

public final class Code39Reader extends OneDReader {
    static final String ALPHABET_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%";
    static final int ASTERISK_ENCODING = 148;
    static final int[] CHARACTER_ENCODINGS = {52, 289, 97, 352, 49, 304, 112, 37, 292, 100, 265, 73, 328, 25, 280, 88, 13, 268, 76, 28, 259, 67, 322, 19, 274, 82, 7, 262, 70, 22, 385, 193, 448, 145, 400, WitsCommand.SystemCommand.SET_PWM_DUTY_RATIO, WitsCommand.SystemCommand.NEXT_DOT_SEEK_FM, 388, 196, 168, 162, 138, 42};
    private final int[] counters;
    private final StringBuilder decodeRowResult;
    private final boolean extendedMode;
    private final boolean usingCheckDigit;

    public Code39Reader() {
        this(false);
    }

    public Code39Reader(boolean usingCheckDigit2) {
        this(usingCheckDigit2, false);
    }

    public Code39Reader(boolean usingCheckDigit2, boolean extendedMode2) {
        this.usingCheckDigit = usingCheckDigit2;
        this.extendedMode = extendedMode2;
        this.decodeRowResult = new StringBuilder(20);
        this.counters = new int[9];
    }

    public Result decodeRow(int rowNumber, BitArray row, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        String resultString;
        Code39Reader code39Reader = this;
        int i = rowNumber;
        BitArray bitArray = row;
        int[] iArr = code39Reader.counters;
        int[] theCounters = iArr;
        Arrays.fill(iArr, 0);
        StringBuilder sb = code39Reader.decodeRowResult;
        StringBuilder result = sb;
        sb.setLength(0);
        int[] start = findAsteriskPattern(bitArray, theCounters);
        int nextStart = bitArray.getNextSet(start[1]);
        int end = row.getSize();
        while (true) {
            recordPattern(bitArray, nextStart, theCounters);
            int narrowWidePattern = toNarrowWidePattern(theCounters);
            int pattern = narrowWidePattern;
            if (narrowWidePattern >= 0) {
                char decodedChar = patternToChar(pattern);
                result.append(decodedChar);
                int lastStart = nextStart;
                for (int counter : theCounters) {
                    nextStart += counter;
                }
                nextStart = bitArray.getNextSet(nextStart);
                if (decodedChar == '*') {
                    result.setLength(result.length() - 1);
                    int lastPatternSize = 0;
                    for (int counter2 : theCounters) {
                        lastPatternSize += counter2;
                    }
                    int whiteSpaceAfterEnd = (nextStart - lastStart) - lastPatternSize;
                    if (nextStart == end || (whiteSpaceAfterEnd << 1) >= lastPatternSize) {
                        if (code39Reader.usingCheckDigit) {
                            int max = result.length() - 1;
                            int total = 0;
                            for (int i2 = 0; i2 < max; i2++) {
                                total += ALPHABET_STRING.indexOf(code39Reader.decodeRowResult.charAt(i2));
                            }
                            if (result.charAt(max) == ALPHABET_STRING.charAt(total % 43)) {
                                result.setLength(max);
                            } else {
                                throw ChecksumException.getChecksumInstance();
                            }
                        }
                        if (result.length() != 0) {
                            if (code39Reader.extendedMode) {
                                resultString = decodeExtended(result);
                            } else {
                                resultString = result.toString();
                            }
                            int[] iArr2 = start;
                            return new Result(resultString, (byte[]) null, new ResultPoint[]{new ResultPoint(((float) (start[1] + start[0])) / 2.0f, (float) i), new ResultPoint(((float) lastStart) + (((float) lastPatternSize) / 2.0f), (float) i)}, BarcodeFormat.CODE_39);
                        }
                        throw NotFoundException.getNotFoundInstance();
                    }
                    throw NotFoundException.getNotFoundInstance();
                }
                bitArray = row;
                start = start;
                code39Reader = this;
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
    }

    private static int[] findAsteriskPattern(BitArray row, int[] counters2) throws NotFoundException {
        int width = row.getSize();
        int rowOffset = row.getNextSet(0);
        int counterPosition = 0;
        int patternStart = rowOffset;
        boolean isWhite = false;
        int patternLength = counters2.length;
        for (int i = rowOffset; i < width; i++) {
            boolean z = true;
            if (row.get(i) != isWhite) {
                counters2[counterPosition] = counters2[counterPosition] + 1;
            } else {
                if (counterPosition != patternLength - 1) {
                    counterPosition++;
                } else if (toNarrowWidePattern(counters2) != ASTERISK_ENCODING || !row.isRange(Math.max(0, patternStart - ((i - patternStart) / 2)), patternStart, false)) {
                    patternStart += counters2[0] + counters2[1];
                    System.arraycopy(counters2, 2, counters2, 0, counterPosition - 1);
                    counters2[counterPosition - 1] = 0;
                    counters2[counterPosition] = 0;
                    counterPosition--;
                } else {
                    return new int[]{patternStart, i};
                }
                counters2[counterPosition] = 1;
                if (isWhite) {
                    z = false;
                }
                isWhite = z;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int toNarrowWidePattern(int[] counters2) {
        int wideCounters;
        int numCounters = counters2.length;
        int maxNarrowCounter = 0;
        do {
            int minCounter = Integer.MAX_VALUE;
            for (int i : counters2) {
                int counter = i;
                if (i < minCounter && counter > maxNarrowCounter) {
                    minCounter = counter;
                }
            }
            maxNarrowCounter = minCounter;
            wideCounters = 0;
            int totalWideCountersWidth = 0;
            int pattern = 0;
            for (int i2 = 0; i2 < numCounters; i2++) {
                int i3 = counters2[i2];
                int counter2 = i3;
                if (i3 > maxNarrowCounter) {
                    pattern |= 1 << ((numCounters - 1) - i2);
                    wideCounters++;
                    totalWideCountersWidth += counter2;
                }
            }
            if (wideCounters == 3) {
                for (int i4 = 0; i4 < numCounters && wideCounters > 0; i4++) {
                    int i5 = counters2[i4];
                    int counter3 = i5;
                    if (i5 > maxNarrowCounter) {
                        wideCounters--;
                        if ((counter3 << 1) >= totalWideCountersWidth) {
                            return -1;
                        }
                    }
                }
                return pattern;
            }
        } while (wideCounters > 3);
        return -1;
    }

    private static char patternToChar(int pattern) throws NotFoundException {
        int i = 0;
        while (true) {
            int[] iArr = CHARACTER_ENCODINGS;
            if (i < iArr.length) {
                if (iArr[i] == pattern) {
                    return ALPHABET_STRING.charAt(i);
                }
                i++;
            } else if (pattern == ASTERISK_ENCODING) {
                return '*';
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
    }

    private static String decodeExtended(CharSequence encoded) throws FormatException {
        int length = encoded.length();
        StringBuilder decoded = new StringBuilder(length);
        int i = 0;
        while (i < length) {
            char charAt = encoded.charAt(i);
            char c = charAt;
            if (charAt == '+' || c == '$' || c == '%' || c == '/') {
                char next = encoded.charAt(i + 1);
                char decodedChar = 0;
                switch (c) {
                    case '$':
                        if (next >= 'A' && next <= 'Z') {
                            decodedChar = (char) (next - '@');
                            break;
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                    case '%':
                        if (next < 'A' || next > 'E') {
                            if (next < 'F' || next > 'J') {
                                if (next < 'K' || next > 'O') {
                                    if (next >= 'P' && next <= 'T') {
                                        decodedChar = (char) (next + '+');
                                        break;
                                    } else if (next == 'U') {
                                        decodedChar = 0;
                                        break;
                                    } else if (next == 'V') {
                                        decodedChar = '@';
                                        break;
                                    } else if (next == 'W') {
                                        decodedChar = '`';
                                        break;
                                    } else if (next == 'X' || next == 'Y' || next == 'Z') {
                                        decodedChar = 127;
                                        break;
                                    } else {
                                        throw FormatException.getFormatInstance();
                                    }
                                } else {
                                    decodedChar = (char) (next + 16);
                                    break;
                                }
                            } else {
                                decodedChar = (char) (next - 11);
                                break;
                            }
                        } else {
                            decodedChar = (char) (next - '&');
                            break;
                        }
                        break;
                    case '+':
                        if (next >= 'A' && next <= 'Z') {
                            decodedChar = (char) (next + ' ');
                            break;
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                    case '/':
                        if (next >= 'A' && next <= 'O') {
                            decodedChar = (char) (next - ' ');
                            break;
                        } else if (next == 'Z') {
                            decodedChar = ':';
                            break;
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                }
                decoded.append(decodedChar);
                i++;
            } else {
                decoded.append(c);
            }
            i++;
        }
        return decoded.toString();
    }
}
