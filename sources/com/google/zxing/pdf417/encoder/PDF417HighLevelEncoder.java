package com.google.zxing.pdf417.encoder;

import com.google.zxing.WriterException;
import com.google.zxing.common.CharacterSetECI;
import com.ibm.icu.text.Bidi;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

final class PDF417HighLevelEncoder {
    private static final int BYTE_COMPACTION = 1;
    private static final Charset DEFAULT_ENCODING = StandardCharsets.ISO_8859_1;
    private static final int ECI_CHARSET = 927;
    private static final int ECI_GENERAL_PURPOSE = 926;
    private static final int ECI_USER_DEFINED = 925;
    private static final int LATCH_TO_BYTE = 924;
    private static final int LATCH_TO_BYTE_PADDED = 901;
    private static final int LATCH_TO_NUMERIC = 902;
    private static final int LATCH_TO_TEXT = 900;
    private static final byte[] MIXED;
    private static final int NUMERIC_COMPACTION = 2;
    private static final byte[] PUNCTUATION = new byte[128];
    private static final int SHIFT_TO_BYTE = 913;
    private static final int SUBMODE_ALPHA = 0;
    private static final int SUBMODE_LOWER = 1;
    private static final int SUBMODE_MIXED = 2;
    private static final int SUBMODE_PUNCTUATION = 3;
    private static final int TEXT_COMPACTION = 0;
    private static final byte[] TEXT_MIXED_RAW = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 38, 13, 9, 44, 58, 35, 45, 46, 36, 47, 43, 37, 42, 61, 94, 0, 32, 0, 0, 0};
    private static final byte[] TEXT_PUNCTUATION_RAW = {59, 60, 62, 64, 91, 92, 93, 95, 96, Bidi.LEVEL_DEFAULT_LTR, 33, 13, 9, 44, 58, 10, 45, 46, 36, 47, 34, 124, 42, 40, 41, 63, 123, Bidi.MAX_EXPLICIT_LEVEL, 39, 0};

    static {
        byte[] bArr = new byte[128];
        MIXED = bArr;
        Arrays.fill(bArr, (byte) -1);
        int i = 0;
        while (true) {
            byte[] bArr2 = TEXT_MIXED_RAW;
            if (i >= bArr2.length) {
                break;
            }
            byte b = bArr2[i];
            byte b2 = b;
            if (b > 0) {
                MIXED[b2] = (byte) i;
            }
            i++;
        }
        Arrays.fill(PUNCTUATION, (byte) -1);
        int i2 = 0;
        while (true) {
            byte[] bArr3 = TEXT_PUNCTUATION_RAW;
            if (i2 < bArr3.length) {
                byte b3 = bArr3[i2];
                byte b4 = b3;
                if (b3 > 0) {
                    PUNCTUATION[b4] = (byte) i2;
                }
                i2++;
            } else {
                return;
            }
        }
    }

    private PDF417HighLevelEncoder() {
    }

    static String encodeHighLevel(String msg, Compaction compaction, Charset encoding) throws WriterException {
        String str = msg;
        Charset encoding2 = encoding;
        StringBuilder sb = new StringBuilder(msg.length());
        if (encoding2 == null) {
            encoding2 = DEFAULT_ENCODING;
        } else if (!DEFAULT_ENCODING.equals(encoding2)) {
            CharacterSetECI characterSetECIByName = CharacterSetECI.getCharacterSetECIByName(encoding.name());
            CharacterSetECI eci = characterSetECIByName;
            if (characterSetECIByName != null) {
                encodingECI(eci.getValue(), sb);
            }
        }
        int len = msg.length();
        int p = 0;
        int textSubMode = 0;
        switch (AnonymousClass1.$SwitchMap$com$google$zxing$pdf417$encoder$Compaction[compaction.ordinal()]) {
            case 1:
                encodeText(str, 0, len, sb, 0);
                break;
            case 2:
                byte[] msgBytes = str.getBytes(encoding2);
                encodeBinary(msgBytes, 0, msgBytes.length, 1, sb);
                break;
            case 3:
                sb.append(902);
                encodeNumeric(str, 0, len, sb);
                break;
            default:
                int encodingMode = 0;
                while (p < len) {
                    int determineConsecutiveDigitCount = determineConsecutiveDigitCount(str, p);
                    int n = determineConsecutiveDigitCount;
                    if (determineConsecutiveDigitCount >= 13) {
                        sb.append(902);
                        encodingMode = 2;
                        textSubMode = 0;
                        encodeNumeric(str, p, n, sb);
                        p += n;
                    } else {
                        int determineConsecutiveTextCount = determineConsecutiveTextCount(str, p);
                        int t = determineConsecutiveTextCount;
                        if (determineConsecutiveTextCount >= 5 || n == len) {
                            if (encodingMode != 0) {
                                sb.append(900);
                                encodingMode = 0;
                                textSubMode = 0;
                            }
                            textSubMode = encodeText(str, p, t, sb, textSubMode);
                            p += t;
                        } else {
                            int determineConsecutiveBinaryCount = determineConsecutiveBinaryCount(str, p, encoding2);
                            int b = determineConsecutiveBinaryCount;
                            if (determineConsecutiveBinaryCount == 0) {
                                b = 1;
                            }
                            byte[] bytes = str.substring(p, p + b).getBytes(encoding2);
                            byte[] bytes2 = bytes;
                            if (bytes.length == 1 && encodingMode == 0) {
                                encodeBinary(bytes2, 0, 1, 0, sb);
                            } else {
                                encodeBinary(bytes2, 0, bytes2.length, encodingMode, sb);
                                encodingMode = 1;
                                textSubMode = 0;
                            }
                            p += b;
                        }
                    }
                }
                break;
        }
        return sb.toString();
    }

    /* renamed from: com.google.zxing.pdf417.encoder.PDF417HighLevelEncoder$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$pdf417$encoder$Compaction;

        static {
            int[] iArr = new int[Compaction.values().length];
            $SwitchMap$com$google$zxing$pdf417$encoder$Compaction = iArr;
            try {
                iArr[Compaction.TEXT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$zxing$pdf417$encoder$Compaction[Compaction.BYTE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$zxing$pdf417$encoder$Compaction[Compaction.NUMERIC.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int encodeText(java.lang.CharSequence r16, int r17, int r18, java.lang.StringBuilder r19, int r20) {
        /*
            r0 = r16
            r1 = r18
            r2 = r19
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r1)
            r4 = r20
            r5 = 0
            r6 = 0
            r7 = r6
        L_0x0010:
            int r8 = r17 + r5
            char r8 = r0.charAt(r8)
            r9 = 26
            r10 = 32
            r11 = 28
            r12 = 27
            r13 = 1
            r14 = 29
            switch(r4) {
                case 0: goto L_0x00bb;
                case 1: goto L_0x0080;
                case 2: goto L_0x0034;
                default: goto L_0x0024;
            }
        L_0x0024:
            boolean r9 = isPunctuation(r8)
            if (r9 == 0) goto L_0x0126
            byte[] r9 = PUNCTUATION
            byte r9 = r9[r8]
            char r9 = (char) r9
            r3.append(r9)
            goto L_0x00f2
        L_0x0034:
            boolean r9 = isMixed(r8)
            if (r9 == 0) goto L_0x0044
            byte[] r9 = MIXED
            byte r9 = r9[r8]
            char r9 = (char) r9
            r3.append(r9)
            goto L_0x00f2
        L_0x0044:
            boolean r9 = isAlphaUpper(r8)
            if (r9 == 0) goto L_0x004f
            r4 = 0
            r3.append(r11)
            goto L_0x0010
        L_0x004f:
            boolean r9 = isAlphaLower(r8)
            if (r9 == 0) goto L_0x005a
            r4 = 1
            r3.append(r12)
            goto L_0x0010
        L_0x005a:
            int r9 = r17 + r5
            int r9 = r9 + r13
            if (r9 >= r1) goto L_0x0073
            int r9 = r17 + r5
            int r9 = r9 + r13
            char r9 = r0.charAt(r9)
            boolean r9 = isPunctuation(r9)
            if (r9 == 0) goto L_0x0073
            r4 = 3
            r9 = 25
            r3.append(r9)
            goto L_0x0010
        L_0x0073:
            r3.append(r14)
            byte[] r9 = PUNCTUATION
            byte r9 = r9[r8]
            char r9 = (char) r9
            r3.append(r9)
            goto L_0x00f2
        L_0x0080:
            boolean r15 = isAlphaLower(r8)
            if (r15 == 0) goto L_0x0093
            if (r8 != r10) goto L_0x008c
            r3.append(r9)
            goto L_0x00f2
        L_0x008c:
            int r9 = r8 + -97
            char r9 = (char) r9
            r3.append(r9)
            goto L_0x00f2
        L_0x0093:
            boolean r9 = isAlphaUpper(r8)
            if (r9 == 0) goto L_0x00a3
            r3.append(r12)
            int r9 = r8 + -65
            char r9 = (char) r9
            r3.append(r9)
            goto L_0x00f2
        L_0x00a3:
            boolean r9 = isMixed(r8)
            if (r9 == 0) goto L_0x00af
            r4 = 2
            r3.append(r11)
            goto L_0x0010
        L_0x00af:
            r3.append(r14)
            byte[] r9 = PUNCTUATION
            byte r9 = r9[r8]
            char r9 = (char) r9
            r3.append(r9)
            goto L_0x00f2
        L_0x00bb:
            boolean r15 = isAlphaUpper(r8)
            if (r15 == 0) goto L_0x00ce
            if (r8 != r10) goto L_0x00c7
            r3.append(r9)
            goto L_0x00f2
        L_0x00c7:
            int r9 = r8 + -65
            char r9 = (char) r9
            r3.append(r9)
            goto L_0x00f2
        L_0x00ce:
            boolean r9 = isAlphaLower(r8)
            if (r9 == 0) goto L_0x00da
            r4 = 1
            r3.append(r12)
            goto L_0x0010
        L_0x00da:
            boolean r9 = isMixed(r8)
            if (r9 == 0) goto L_0x00e6
            r4 = 2
            r3.append(r11)
            goto L_0x0010
        L_0x00e6:
            r3.append(r14)
            byte[] r9 = PUNCTUATION
            byte r9 = r9[r8]
            char r9 = (char) r9
            r3.append(r9)
        L_0x00f2:
            int r5 = r5 + 1
            if (r5 < r1) goto L_0x0010
            r7 = 0
            int r8 = r3.length()
            r9 = 0
        L_0x00fc:
            if (r9 >= r8) goto L_0x011a
            int r10 = r9 % 2
            if (r10 == 0) goto L_0x0104
            r10 = r13
            goto L_0x0105
        L_0x0104:
            r10 = r6
        L_0x0105:
            if (r10 == 0) goto L_0x0113
            int r10 = r7 * 30
            char r11 = r3.charAt(r9)
            int r10 = r10 + r11
            char r7 = (char) r10
            r2.append(r7)
            goto L_0x0117
        L_0x0113:
            char r7 = r3.charAt(r9)
        L_0x0117:
            int r9 = r9 + 1
            goto L_0x00fc
        L_0x011a:
            int r6 = r8 % 2
            if (r6 == 0) goto L_0x0125
            int r6 = r7 * 30
            int r6 = r6 + r14
            char r6 = (char) r6
            r2.append(r6)
        L_0x0125:
            return r4
        L_0x0126:
            r4 = 0
            r3.append(r14)
            goto L_0x0010
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.encoder.PDF417HighLevelEncoder.encodeText(java.lang.CharSequence, int, int, java.lang.StringBuilder, int):int");
    }

    private static void encodeBinary(byte[] bytes, int startpos, int count, int startmode, StringBuilder sb) {
        if (count == 1 && startmode == 0) {
            sb.append(913);
        } else if (count % 6 == 0) {
            sb.append(924);
        } else {
            sb.append(901);
        }
        int idx = startpos;
        if (count >= 6) {
            char[] chars = new char[5];
            while ((startpos + count) - idx >= 6) {
                long t = 0;
                for (int i = 0; i < 6; i++) {
                    t = (t << 8) + ((long) (bytes[idx + i] & 255));
                }
                for (int i2 = 0; i2 < 5; i2++) {
                    chars[i2] = (char) ((int) (t % 900));
                    t /= 900;
                }
                for (int i3 = 4; i3 >= 0; i3--) {
                    sb.append(chars[i3]);
                }
                idx += 6;
            }
        }
        for (int i4 = idx; i4 < startpos + count; i4++) {
            sb.append((char) (bytes[i4] & 255));
        }
    }

    private static void encodeNumeric(String msg, int startpos, int count, StringBuilder sb) {
        BigInteger divide;
        int idx = 0;
        StringBuilder tmp = new StringBuilder((count / 3) + 1);
        BigInteger num900 = BigInteger.valueOf(900);
        BigInteger num0 = BigInteger.valueOf(0);
        while (idx < count) {
            tmp.setLength(0);
            int len = Math.min(44, count - idx);
            BigInteger bigint = new BigInteger("1" + msg.substring(startpos + idx, startpos + idx + len));
            do {
                tmp.append((char) bigint.mod(num900).intValue());
                divide = bigint.divide(num900);
                bigint = divide;
            } while (!divide.equals(num0));
            for (int i = tmp.length() - 1; i >= 0; i--) {
                sb.append(tmp.charAt(i));
            }
            idx += len;
        }
    }

    private static boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    private static boolean isAlphaUpper(char ch) {
        if (ch != ' ') {
            return ch >= 'A' && ch <= 'Z';
        }
        return true;
    }

    private static boolean isAlphaLower(char ch) {
        if (ch != ' ') {
            return ch >= 'a' && ch <= 'z';
        }
        return true;
    }

    private static boolean isMixed(char ch) {
        return MIXED[ch] != -1;
    }

    private static boolean isPunctuation(char ch) {
        return PUNCTUATION[ch] != -1;
    }

    private static boolean isText(char ch) {
        if (ch == 9 || ch == 10 || ch == 13) {
            return true;
        }
        return ch >= ' ' && ch <= '~';
    }

    private static int determineConsecutiveDigitCount(CharSequence msg, int startpos) {
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

    private static int determineConsecutiveTextCount(CharSequence msg, int startpos) {
        int len = msg.length();
        int idx = startpos;
        while (idx < len) {
            char ch = msg.charAt(idx);
            int numericCount = 0;
            while (numericCount < 13 && isDigit(ch) && idx < len) {
                numericCount++;
                idx++;
                if (idx < len) {
                    ch = msg.charAt(idx);
                }
            }
            if (numericCount < 13) {
                if (numericCount <= 0) {
                    if (!isText(msg.charAt(idx))) {
                        break;
                    }
                    idx++;
                }
            } else {
                return (idx - startpos) - numericCount;
            }
        }
        return idx - startpos;
    }

    private static int determineConsecutiveBinaryCount(String msg, int startpos, Charset encoding) throws WriterException {
        CharsetEncoder encoder = encoding.newEncoder();
        int len = msg.length();
        int idx = startpos;
        while (idx < len) {
            char ch = msg.charAt(idx);
            int numericCount = 0;
            while (numericCount < 13 && isDigit(ch)) {
                numericCount++;
                int i = idx + numericCount;
                int i2 = i;
                if (i >= len) {
                    break;
                }
                ch = msg.charAt(i2);
            }
            if (numericCount >= 13) {
                return idx - startpos;
            }
            char ch2 = msg.charAt(idx);
            if (encoder.canEncode(ch2)) {
                idx++;
            } else {
                throw new WriterException("Non-encodable character detected: " + ch2 + " (Unicode: " + ch2 + ')');
            }
        }
        return idx - startpos;
    }

    private static void encodingECI(int eci, StringBuilder sb) throws WriterException {
        if (eci >= 0 && eci < 900) {
            sb.append(927);
            sb.append((char) eci);
        } else if (eci < 810900) {
            sb.append(926);
            sb.append((char) ((eci / 900) - 1));
            sb.append((char) (eci % 900));
        } else if (eci < 811800) {
            sb.append(925);
            sb.append((char) (810900 - eci));
        } else {
            throw new WriterException("ECI number not in valid range from 0..811799, but was ".concat(String.valueOf(eci)));
        }
    }
}
