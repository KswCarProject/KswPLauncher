package com.google.zxing.pdf417.encoder;

import com.google.zxing.WriterException;
import com.google.zxing.common.CharacterSetECI;
import com.ibm.icu.text.Bidi;
import com.wits.ksw.settings.TxzMessage;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import kotlin.UByte;

/* loaded from: classes.dex */
final class PDF417HighLevelEncoder {
    private static final int BYTE_COMPACTION = 1;
    private static final int ECI_CHARSET = 927;
    private static final int ECI_GENERAL_PURPOSE = 926;
    private static final int ECI_USER_DEFINED = 925;
    private static final int LATCH_TO_BYTE = 924;
    private static final int LATCH_TO_BYTE_PADDED = 901;
    private static final int LATCH_TO_NUMERIC = 902;
    private static final int LATCH_TO_TEXT = 900;
    private static final byte[] MIXED;
    private static final int NUMERIC_COMPACTION = 2;
    private static final int SHIFT_TO_BYTE = 913;
    private static final int SUBMODE_ALPHA = 0;
    private static final int SUBMODE_LOWER = 1;
    private static final int SUBMODE_MIXED = 2;
    private static final int SUBMODE_PUNCTUATION = 3;
    private static final int TEXT_COMPACTION = 0;
    private static final byte[] TEXT_MIXED_RAW = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 38, 13, 9, 44, 58, 35, 45, 46, 36, 47, 43, 37, 42, 61, 94, 0, 32, 0, 0, 0};
    private static final byte[] TEXT_PUNCTUATION_RAW = {59, 60, 62, 64, 91, 92, 93, 95, 96, Bidi.LEVEL_DEFAULT_LTR, 33, 13, 9, 44, 58, 10, 45, 46, 36, 47, 34, 124, 42, 40, 41, 63, 123, Bidi.MAX_EXPLICIT_LEVEL, 39, 0};
    private static final byte[] PUNCTUATION = new byte[128];
    private static final Charset DEFAULT_ENCODING = StandardCharsets.ISO_8859_1;

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
            if (b > 0) {
                MIXED[b] = (byte) i;
            }
            i++;
        }
        Arrays.fill(PUNCTUATION, (byte) -1);
        int i2 = 0;
        while (true) {
            byte[] bArr3 = TEXT_PUNCTUATION_RAW;
            if (i2 < bArr3.length) {
                byte b2 = bArr3[i2];
                if (b2 > 0) {
                    PUNCTUATION[b2] = (byte) i2;
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
        CharacterSetECI eci;
        Charset encoding2 = encoding;
        StringBuilder sb = new StringBuilder(msg.length());
        if (encoding2 == null) {
            encoding2 = DEFAULT_ENCODING;
        } else if (!DEFAULT_ENCODING.equals(encoding2) && (eci = CharacterSetECI.getCharacterSetECIByName(encoding.name())) != null) {
            encodingECI(eci.getValue(), sb);
        }
        int len = msg.length();
        int p = 0;
        int textSubMode = 0;
        switch (C06791.$SwitchMap$com$google$zxing$pdf417$encoder$Compaction[compaction.ordinal()]) {
            case 1:
                encodeText(msg, 0, len, sb, 0);
                break;
            case 2:
                byte[] msgBytes = msg.getBytes(encoding2);
                encodeBinary(msgBytes, 0, msgBytes.length, 1, sb);
                break;
            case 3:
                sb.append('\u0386');
                encodeNumeric(msg, 0, len, sb);
                break;
            default:
                int encodingMode = 0;
                while (p < len) {
                    int n = determineConsecutiveDigitCount(msg, p);
                    if (n >= 13) {
                        sb.append('\u0386');
                        encodingMode = 2;
                        textSubMode = 0;
                        encodeNumeric(msg, p, n, sb);
                        p += n;
                    } else {
                        int t = determineConsecutiveTextCount(msg, p);
                        if (t >= 5 || n == len) {
                            if (encodingMode != 0) {
                                sb.append('\u0384');
                                encodingMode = 0;
                                textSubMode = 0;
                            }
                            textSubMode = encodeText(msg, p, t, sb, textSubMode);
                            p += t;
                        } else {
                            int determineConsecutiveBinaryCount = determineConsecutiveBinaryCount(msg, p, encoding2);
                            int b = determineConsecutiveBinaryCount;
                            if (determineConsecutiveBinaryCount == 0) {
                                b = 1;
                            }
                            byte[] bytes = msg.substring(p, p + b).getBytes(encoding2);
                            if (bytes.length != 1 || encodingMode != 0) {
                                encodeBinary(bytes, 0, bytes.length, encodingMode, sb);
                                encodingMode = 1;
                                textSubMode = 0;
                            } else {
                                encodeBinary(bytes, 0, 1, 0, sb);
                            }
                            p += b;
                        }
                    }
                }
                break;
        }
        return sb.toString();
    }

    /* renamed from: com.google.zxing.pdf417.encoder.PDF417HighLevelEncoder$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C06791 {
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

    private static int encodeText(CharSequence msg, int startpos, int count, StringBuilder sb, int initialSubmode) {
        StringBuilder tmp = new StringBuilder(count);
        int submode = initialSubmode;
        int idx = 0;
        while (true) {
            char ch = msg.charAt(startpos + idx);
            switch (submode) {
                case 0:
                    if (isAlphaUpper(ch)) {
                        if (ch == ' ') {
                            tmp.append((char) 26);
                            break;
                        } else {
                            tmp.append((char) (ch - 'A'));
                            break;
                        }
                    } else if (isAlphaLower(ch)) {
                        submode = 1;
                        tmp.append((char) 27);
                        continue;
                    } else if (isMixed(ch)) {
                        submode = 2;
                        tmp.append((char) 28);
                    } else {
                        tmp.append((char) 29);
                        tmp.append((char) PUNCTUATION[ch]);
                        break;
                    }
                case 1:
                    if (isAlphaLower(ch)) {
                        if (ch == ' ') {
                            tmp.append((char) 26);
                            break;
                        } else {
                            tmp.append((char) (ch - 'a'));
                            break;
                        }
                    } else if (isAlphaUpper(ch)) {
                        tmp.append((char) 27);
                        tmp.append((char) (ch - 'A'));
                        break;
                    } else if (isMixed(ch)) {
                        submode = 2;
                        tmp.append((char) 28);
                        continue;
                    } else {
                        tmp.append((char) 29);
                        tmp.append((char) PUNCTUATION[ch]);
                        break;
                    }
                case 2:
                    if (isMixed(ch)) {
                        tmp.append((char) MIXED[ch]);
                        break;
                    } else if (isAlphaUpper(ch)) {
                        submode = 0;
                        tmp.append((char) 28);
                        continue;
                    } else if (isAlphaLower(ch)) {
                        submode = 1;
                        tmp.append((char) 27);
                    } else if (startpos + idx + 1 < count && isPunctuation(msg.charAt(startpos + idx + 1))) {
                        submode = 3;
                        tmp.append((char) 25);
                    } else {
                        tmp.append((char) 29);
                        tmp.append((char) PUNCTUATION[ch]);
                        break;
                    }
                    break;
                default:
                    if (isPunctuation(ch)) {
                        tmp.append((char) PUNCTUATION[ch]);
                        break;
                    } else {
                        submode = 0;
                        tmp.append((char) 29);
                        continue;
                    }
            }
            idx++;
            if (idx >= count) {
                char h = 0;
                int len = tmp.length();
                for (int i = 0; i < len; i++) {
                    if (i % 2 != 0) {
                        h = (char) ((h * 30) + tmp.charAt(i));
                        sb.append(h);
                    } else {
                        h = tmp.charAt(i);
                    }
                }
                if (len % 2 != 0) {
                    sb.append((char) ((h * 30) + 29));
                }
                return submode;
            }
        }
    }

    private static void encodeBinary(byte[] bytes, int startpos, int count, int startmode, StringBuilder sb) {
        if (count == 1 && startmode == 0) {
            sb.append('\u0391');
        } else if (count % 6 == 0) {
            sb.append('\u039c');
        } else {
            sb.append('\u0385');
        }
        int idx = startpos;
        if (count >= 6) {
            char[] chars = new char[5];
            while ((startpos + count) - idx >= 6) {
                long t = 0;
                for (int i = 0; i < 6; i++) {
                    t = (t << 8) + (bytes[idx + i] & UByte.MAX_VALUE);
                }
                for (int i2 = 0; i2 < 5; i2++) {
                    chars[i2] = (char) (t % 900);
                    t /= 900;
                }
                for (int i3 = 4; i3 >= 0; i3--) {
                    sb.append(chars[i3]);
                }
                idx += 6;
            }
        }
        for (int i4 = idx; i4 < startpos + count; i4++) {
            int ch = bytes[i4] & UByte.MAX_VALUE;
            sb.append((char) ch);
        }
    }

    private static void encodeNumeric(String msg, int startpos, int count, StringBuilder sb) {
        BigInteger divide;
        int idx = 0;
        StringBuilder tmp = new StringBuilder((count / 3) + 1);
        BigInteger num900 = BigInteger.valueOf(900L);
        BigInteger num0 = BigInteger.valueOf(0L);
        while (idx < count) {
            tmp.setLength(0);
            int len = Math.min(44, count - idx);
            String part = TxzMessage.TXZ_SHOW + msg.substring(startpos + idx, startpos + idx + len);
            BigInteger bigint = new BigInteger(part);
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
        if (ch == '\t' || ch == '\n' || ch == '\r') {
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

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0028, code lost:
        return (r1 - r7) - r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
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
            if (numericCount <= 0) {
                if (!isText(msg.charAt(idx))) {
                    break;
                }
                idx++;
            }
        }
        return idx - startpos;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x002b, code lost:
        return r2 - r9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static int determineConsecutiveBinaryCount(String msg, int startpos, Charset encoding) throws WriterException {
        int i;
        CharsetEncoder encoder = encoding.newEncoder();
        int len = msg.length();
        int idx = startpos;
        while (idx < len) {
            char ch = msg.charAt(idx);
            int numericCount = 0;
            while (numericCount < 13 && isDigit(ch) && (i = idx + (numericCount = numericCount + 1)) < len) {
                ch = msg.charAt(i);
            }
            char ch2 = msg.charAt(idx);
            if (!encoder.canEncode(ch2)) {
                throw new WriterException("Non-encodable character detected: " + ch2 + " (Unicode: " + ((int) ch2) + ')');
            }
            idx++;
        }
        return idx - startpos;
    }

    private static void encodingECI(int eci, StringBuilder sb) throws WriterException {
        if (eci >= 0 && eci < 900) {
            sb.append('\u039f');
            sb.append((char) eci);
        } else if (eci < 810900) {
            sb.append('\u039e');
            sb.append((char) ((eci / 900) - 1));
            sb.append((char) (eci % 900));
        } else if (eci < 811800) {
            sb.append('\u039d');
            sb.append((char) (810900 - eci));
        } else {
            throw new WriterException("ECI number not in valid range from 0..811799, but was ".concat(String.valueOf(eci)));
        }
    }
}
