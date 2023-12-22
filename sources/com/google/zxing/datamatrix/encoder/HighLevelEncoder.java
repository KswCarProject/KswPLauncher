package com.google.zxing.datamatrix.encoder;

import com.google.zxing.Dimension;
import com.ibm.icu.text.SCSU;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class HighLevelEncoder {
    static final int ASCII_ENCODATION = 0;
    static final int BASE256_ENCODATION = 5;
    static final int C40_ENCODATION = 1;
    static final char C40_UNLATCH = '\u00fe';
    static final int EDIFACT_ENCODATION = 4;
    static final char LATCH_TO_ANSIX12 = '\u00ee';
    static final char LATCH_TO_BASE256 = '\u00e7';
    static final char LATCH_TO_C40 = '\u00e6';
    static final char LATCH_TO_EDIFACT = '\u00f0';
    static final char LATCH_TO_TEXT = '\u00ef';
    private static final char MACRO_05 = '\u00ec';
    private static final String MACRO_05_HEADER = "[)>\u001e05\u001d";
    private static final char MACRO_06 = '\u00ed';
    private static final String MACRO_06_HEADER = "[)>\u001e06\u001d";
    private static final String MACRO_TRAILER = "\u001e\u0004";
    private static final char PAD = '\u0081';
    static final int TEXT_ENCODATION = 2;
    static final char UPPER_SHIFT = '\u00eb';
    static final int X12_ENCODATION = 3;
    static final char X12_UNLATCH = '\u00fe';

    private HighLevelEncoder() {
    }

    private static char randomize253State(char ch, int codewordPosition) {
        int pseudoRandom = ((codewordPosition * 149) % SCSU.HIRAGANAINDEX) + 1;
        int tempVariable = ch + pseudoRandom;
        return (char) (tempVariable <= 254 ? tempVariable : tempVariable - 254);
    }

    public static String encodeHighLevel(String msg) {
        return encodeHighLevel(msg, SymbolShapeHint.FORCE_NONE, null, null);
    }

    public static String encodeHighLevel(String msg, SymbolShapeHint shape, Dimension minSize, Dimension maxSize) {
        Encoder[] encoders = {new ASCIIEncoder(), new C40Encoder(), new TextEncoder(), new X12Encoder(), new EdifactEncoder(), new Base256Encoder()};
        EncoderContext context = new EncoderContext(msg);
        context.setSymbolShape(shape);
        context.setSizeConstraints(minSize, maxSize);
        if (msg.startsWith(MACRO_05_HEADER) && msg.endsWith(MACRO_TRAILER)) {
            context.writeCodeword(MACRO_05);
            context.setSkipAtEnd(2);
            context.pos += 7;
        } else if (msg.startsWith(MACRO_06_HEADER) && msg.endsWith(MACRO_TRAILER)) {
            context.writeCodeword(MACRO_06);
            context.setSkipAtEnd(2);
            context.pos += 7;
        }
        int encodingMode = 0;
        while (context.hasMoreCharacters()) {
            encoders[encodingMode].encode(context);
            if (context.getNewEncoding() >= 0) {
                encodingMode = context.getNewEncoding();
                context.resetEncoderSignal();
            }
        }
        int len = context.getCodewordCount();
        context.updateSymbolInfo();
        int capacity = context.getSymbolInfo().getDataCapacity();
        if (len < capacity && encodingMode != 0 && encodingMode != 5 && encodingMode != 4) {
            context.writeCodeword('\u00fe');
        }
        StringBuilder codewords = context.getCodewords();
        if (codewords.length() < capacity) {
            codewords.append(PAD);
        }
        while (codewords.length() < capacity) {
            codewords.append(randomize253State(PAD, codewords.length() + 1));
        }
        return context.getCodewords().toString();
    }

    static int lookAheadTest(CharSequence msg, int startpos, int currentMode) {
        float[] charCounts;
        if (startpos >= msg.length()) {
            return currentMode;
        }
        int i = 6;
        if (currentMode == 0) {
            charCounts = new float[]{0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.25f};
        } else {
            charCounts = new float[]{1.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.25f};
            charCounts[currentMode] = 0.0f;
        }
        int charsProcessed = 0;
        while (startpos + charsProcessed != msg.length()) {
            int min = startpos + charsProcessed;
            char c = msg.charAt(min);
            charsProcessed++;
            if (isDigit(c)) {
                charCounts[0] = charCounts[0] + 0.5f;
            } else if (isExtendedASCII(c)) {
                charCounts[0] = (float) Math.ceil(charCounts[0]);
                charCounts[0] = charCounts[0] + 2.0f;
            } else {
                charCounts[0] = (float) Math.ceil(charCounts[0]);
                charCounts[0] = charCounts[0] + 1.0f;
            }
            if (isNativeC40(c)) {
                charCounts[1] = charCounts[1] + 0.6666667f;
            } else if (isExtendedASCII(c)) {
                charCounts[1] = charCounts[1] + 2.6666667f;
            } else {
                charCounts[1] = charCounts[1] + 1.3333334f;
            }
            if (isNativeText(c)) {
                charCounts[2] = charCounts[2] + 0.6666667f;
            } else if (isExtendedASCII(c)) {
                charCounts[2] = charCounts[2] + 2.6666667f;
            } else {
                charCounts[2] = charCounts[2] + 1.3333334f;
            }
            if (isNativeX12(c)) {
                charCounts[3] = charCounts[3] + 0.6666667f;
            } else if (isExtendedASCII(c)) {
                charCounts[3] = charCounts[3] + 4.3333335f;
            } else {
                charCounts[3] = charCounts[3] + 3.3333333f;
            }
            if (isNativeEDIFACT(c)) {
                charCounts[4] = charCounts[4] + 0.75f;
            } else if (isExtendedASCII(c)) {
                charCounts[4] = charCounts[4] + 4.25f;
            } else {
                charCounts[4] = charCounts[4] + 3.25f;
            }
            if (isSpecialB256(c)) {
                charCounts[5] = charCounts[5] + 4.0f;
            } else {
                charCounts[5] = charCounts[5] + 1.0f;
            }
            if (charsProcessed >= 4) {
                int[] intCharCounts = new int[i];
                byte[] mins = new byte[i];
                findMinimums(charCounts, intCharCounts, Integer.MAX_VALUE, mins);
                int minCount = getMinimumCount(mins);
                if (intCharCounts[0] < intCharCounts[5] && intCharCounts[0] < intCharCounts[1] && intCharCounts[0] < intCharCounts[2] && intCharCounts[0] < intCharCounts[3] && intCharCounts[0] < intCharCounts[4]) {
                    return 0;
                }
                if (intCharCounts[5] < intCharCounts[0] || mins[1] + mins[2] + mins[3] + mins[4] == 0) {
                    return 5;
                }
                if (minCount == 1 && mins[4] > 0) {
                    return 4;
                }
                if (minCount == 1 && mins[2] > 0) {
                    return 2;
                }
                if (minCount == 1 && mins[3] > 0) {
                    return 3;
                }
                if (intCharCounts[1] + 1 < intCharCounts[0] && intCharCounts[1] + 1 < intCharCounts[5] && intCharCounts[1] + 1 < intCharCounts[4] && intCharCounts[1] + 1 < intCharCounts[2]) {
                    if (intCharCounts[1] < intCharCounts[3]) {
                        return 1;
                    }
                    if (intCharCounts[1] == intCharCounts[3]) {
                        for (int p = startpos + charsProcessed + 1; p < msg.length(); p++) {
                            char tc = msg.charAt(p);
                            if (isX12TermSep(tc)) {
                                return 3;
                            }
                            if (!isNativeX12(tc)) {
                                break;
                            }
                        }
                        return 1;
                    }
                }
            }
            i = 6;
        }
        byte[] mins2 = new byte[i];
        int[] intCharCounts2 = new int[i];
        int min2 = findMinimums(charCounts, intCharCounts2, Integer.MAX_VALUE, mins2);
        int minCount2 = getMinimumCount(mins2);
        if (intCharCounts2[0] == min2) {
            return 0;
        }
        if (minCount2 != 1 || mins2[5] <= 0) {
            if (minCount2 != 1 || mins2[4] <= 0) {
                if (minCount2 != 1 || mins2[2] <= 0) {
                    return (minCount2 != 1 || mins2[3] <= 0) ? 1 : 3;
                }
                return 2;
            }
            return 4;
        }
        return 5;
    }

    private static int findMinimums(float[] charCounts, int[] intCharCounts, int min, byte[] mins) {
        Arrays.fill(mins, (byte) 0);
        for (int i = 0; i < 6; i++) {
            intCharCounts[i] = (int) Math.ceil(charCounts[i]);
            int current = intCharCounts[i];
            if (min > current) {
                min = current;
                Arrays.fill(mins, (byte) 0);
            }
            if (min == current) {
                mins[i] = (byte) (mins[i] + 1);
            }
        }
        return min;
    }

    private static int getMinimumCount(byte[] mins) {
        int minCount = 0;
        for (int i = 0; i < 6; i++) {
            minCount += mins[i];
        }
        return minCount;
    }

    static boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    static boolean isExtendedASCII(char ch) {
        return ch >= '\u0080' && ch <= '\u00ff';
    }

    private static boolean isNativeC40(char ch) {
        if (ch != ' ') {
            if (ch < '0' || ch > '9') {
                return ch >= 'A' && ch <= 'Z';
            }
            return true;
        }
        return true;
    }

    private static boolean isNativeText(char ch) {
        if (ch != ' ') {
            if (ch < '0' || ch > '9') {
                return ch >= 'a' && ch <= 'z';
            }
            return true;
        }
        return true;
    }

    private static boolean isNativeX12(char ch) {
        if (isX12TermSep(ch) || ch == ' ') {
            return true;
        }
        if (ch < '0' || ch > '9') {
            return ch >= 'A' && ch <= 'Z';
        }
        return true;
    }

    private static boolean isX12TermSep(char ch) {
        return ch == '\r' || ch == '*' || ch == '>';
    }

    private static boolean isNativeEDIFACT(char ch) {
        return ch >= ' ' && ch <= '^';
    }

    private static boolean isSpecialB256(char ch) {
        return false;
    }

    public static int determineConsecutiveDigitCount(CharSequence msg, int startpos) {
        int count = 0;
        int len = msg.length();
        int idx = startpos;
        if (startpos < len) {
            char ch = msg.charAt(startpos);
            while (isDigit(ch) && idx < len) {
                count++;
                idx++;
                if (idx < len) {
                    ch = msg.charAt(idx);
                }
            }
        }
        return count;
    }

    static void illegalCharacter(char c) {
        String hex = Integer.toHexString(c);
        throw new IllegalArgumentException("Illegal character: " + c + " (0x" + ("0000".substring(0, 4 - hex.length()) + hex) + ')');
    }
}
