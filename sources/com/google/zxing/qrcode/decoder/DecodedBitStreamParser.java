package com.google.zxing.qrcode.decoder;

import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.StringUtils;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Map;

final class DecodedBitStreamParser {
    private static final char[] ALPHANUMERIC_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:".toCharArray();
    private static final int GB2312_SUBSET = 1;

    private DecodedBitStreamParser() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00bd, code lost:
        r10 = r2;
        r11 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00c1, code lost:
        if (r6 != com.google.zxing.qrcode.decoder.Mode.TERMINATOR) goto L_0x00e8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00c3, code lost:
        r4 = r7.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00cf, code lost:
        if (r14.isEmpty() == false) goto L_0x00d3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00d1, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00d3, code lost:
        r5 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00d4, code lost:
        if (r18 != null) goto L_0x00d8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00d6, code lost:
        r6 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00d8, code lost:
        r6 = r18.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00dd, code lost:
        r12 = r7;
        r13 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00e7, code lost:
        return new com.google.zxing.common.DecoderResult(r16, r4, r5, r6, r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00f0, code lost:
        r12 = r7;
        r13 = r8;
        r2 = r10;
        r3 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00fb, code lost:
        r0 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0100, code lost:
        throw com.google.zxing.FormatException.getFormatInstance();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.google.zxing.common.DecoderResult decode(byte[] r16, com.google.zxing.qrcode.decoder.Version r17, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel r18, java.util.Map<com.google.zxing.DecodeHintType, ?> r19) throws com.google.zxing.FormatException {
        /*
            r1 = r17
            com.google.zxing.common.BitSource r0 = new com.google.zxing.common.BitSource
            r9 = r16
            r0.<init>(r9)
            r8 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r2 = 50
            r0.<init>(r2)
            r7 = r0
            java.util.ArrayList r14 = new java.util.ArrayList
            r0 = 1
            r14.<init>(r0)
            r2 = -1
            r3 = -1
            r4 = 0
            r5 = 0
        L_0x001c:
            int r6 = r8.available()     // Catch:{ IllegalArgumentException -> 0x00f8 }
            r10 = 4
            if (r6 >= r10) goto L_0x0026
            com.google.zxing.qrcode.decoder.Mode r6 = com.google.zxing.qrcode.decoder.Mode.TERMINATOR     // Catch:{ IllegalArgumentException -> 0x007c }
            goto L_0x002e
        L_0x0026:
            int r6 = r8.readBits(r10)     // Catch:{ IllegalArgumentException -> 0x00f8 }
            com.google.zxing.qrcode.decoder.Mode r6 = com.google.zxing.qrcode.decoder.Mode.forBits(r6)     // Catch:{ IllegalArgumentException -> 0x00f8 }
        L_0x002e:
            int[] r11 = com.google.zxing.qrcode.decoder.DecodedBitStreamParser.AnonymousClass1.$SwitchMap$com$google$zxing$qrcode$decoder$Mode     // Catch:{ IllegalArgumentException -> 0x00f8 }
            int r12 = r6.ordinal()     // Catch:{ IllegalArgumentException -> 0x00f8 }
            r11 = r11[r12]     // Catch:{ IllegalArgumentException -> 0x00f8 }
            switch(r11) {
                case 5: goto L_0x008a;
                case 6: goto L_0x0086;
                case 7: goto L_0x0086;
                case 8: goto L_0x0065;
                case 9: goto L_0x0051;
                case 10: goto L_0x003e;
                default: goto L_0x0039;
            }     // Catch:{ IllegalArgumentException -> 0x00f8 }
        L_0x0039:
            int r10 = r6.getCharacterCountBits(r1)     // Catch:{ IllegalArgumentException -> 0x00f8 }
            goto L_0x008b
        L_0x003e:
            int r10 = r8.readBits(r10)     // Catch:{ IllegalArgumentException -> 0x007c }
            int r11 = r6.getCharacterCountBits(r1)     // Catch:{ IllegalArgumentException -> 0x007c }
            int r11 = r8.readBits(r11)     // Catch:{ IllegalArgumentException -> 0x007c }
            if (r10 != r0) goto L_0x00bd
            decodeHanziSegment(r8, r7, r11)     // Catch:{ IllegalArgumentException -> 0x007c }
            goto L_0x00bd
        L_0x0051:
            int r10 = parseECIValue(r8)     // Catch:{ IllegalArgumentException -> 0x007c }
            com.google.zxing.common.CharacterSetECI r10 = com.google.zxing.common.CharacterSetECI.getCharacterSetECIByValue(r10)     // Catch:{ IllegalArgumentException -> 0x007c }
            r4 = r10
            if (r10 == 0) goto L_0x0060
            r10 = r2
            r11 = r3
            goto L_0x00bf
        L_0x0060:
            com.google.zxing.FormatException r0 = com.google.zxing.FormatException.getFormatInstance()     // Catch:{ IllegalArgumentException -> 0x007c }
            throw r0     // Catch:{ IllegalArgumentException -> 0x007c }
        L_0x0065:
            int r10 = r8.available()     // Catch:{ IllegalArgumentException -> 0x007c }
            r11 = 16
            if (r10 < r11) goto L_0x0081
            r10 = 8
            int r11 = r8.readBits(r10)     // Catch:{ IllegalArgumentException -> 0x007c }
            r2 = r11
            int r10 = r8.readBits(r10)     // Catch:{ IllegalArgumentException -> 0x007c }
            r3 = r10
            r10 = r2
            r11 = r3
            goto L_0x00bf
        L_0x007c:
            r0 = move-exception
            r12 = r7
            r13 = r8
            goto L_0x00fb
        L_0x0081:
            com.google.zxing.FormatException r0 = com.google.zxing.FormatException.getFormatInstance()     // Catch:{ IllegalArgumentException -> 0x007c }
            throw r0     // Catch:{ IllegalArgumentException -> 0x007c }
        L_0x0086:
            r5 = 1
            r10 = r2
            r11 = r3
            goto L_0x00bf
        L_0x008a:
            goto L_0x00bd
        L_0x008b:
            int r10 = r8.readBits(r10)     // Catch:{ IllegalArgumentException -> 0x00f8 }
            r15 = r10
            int[] r10 = com.google.zxing.qrcode.decoder.DecodedBitStreamParser.AnonymousClass1.$SwitchMap$com$google$zxing$qrcode$decoder$Mode     // Catch:{ IllegalArgumentException -> 0x00f8 }
            int r11 = r6.ordinal()     // Catch:{ IllegalArgumentException -> 0x00f8 }
            r10 = r10[r11]     // Catch:{ IllegalArgumentException -> 0x00f8 }
            switch(r10) {
                case 1: goto L_0x00b8;
                case 2: goto L_0x00b3;
                case 3: goto L_0x00a8;
                case 4: goto L_0x00a4;
                default: goto L_0x009b;
            }
        L_0x009b:
            r12 = r7
            r13 = r8
            r0 = r15
            com.google.zxing.FormatException r7 = com.google.zxing.FormatException.getFormatInstance()     // Catch:{ IllegalArgumentException -> 0x00f6 }
            goto L_0x00f5
        L_0x00a4:
            decodeKanjiSegment(r8, r7, r15)     // Catch:{ IllegalArgumentException -> 0x007c }
            goto L_0x00bd
        L_0x00a8:
            r10 = r8
            r11 = r7
            r12 = r15
            r13 = r4
            r0 = r15
            r15 = r19
            decodeByteSegment(r10, r11, r12, r13, r14, r15)     // Catch:{ IllegalArgumentException -> 0x007c }
            goto L_0x00bd
        L_0x00b3:
            r0 = r15
            decodeAlphanumericSegment(r8, r7, r0, r5)     // Catch:{ IllegalArgumentException -> 0x007c }
            goto L_0x00bd
        L_0x00b8:
            r0 = r15
            decodeNumericSegment(r8, r7, r0)     // Catch:{ IllegalArgumentException -> 0x00f8 }
        L_0x00bd:
            r10 = r2
            r11 = r3
        L_0x00bf:
            com.google.zxing.qrcode.decoder.Mode r0 = com.google.zxing.qrcode.decoder.Mode.TERMINATOR     // Catch:{ IllegalArgumentException -> 0x00ef }
            if (r6 != r0) goto L_0x00e8
            com.google.zxing.common.DecoderResult r0 = new com.google.zxing.common.DecoderResult
            java.lang.String r4 = r7.toString()
            boolean r2 = r14.isEmpty()
            r3 = 0
            if (r2 == 0) goto L_0x00d3
            r5 = r3
            goto L_0x00d4
        L_0x00d3:
            r5 = r14
        L_0x00d4:
            if (r18 != 0) goto L_0x00d8
            r6 = r3
            goto L_0x00dd
        L_0x00d8:
            java.lang.String r2 = r18.toString()
            r6 = r2
        L_0x00dd:
            r2 = r0
            r3 = r16
            r12 = r7
            r7 = r10
            r13 = r8
            r8 = r11
            r2.<init>(r3, r4, r5, r6, r7, r8)
            return r0
        L_0x00e8:
            r12 = r7
            r13 = r8
            r2 = r10
            r3 = r11
            r0 = 1
            goto L_0x001c
        L_0x00ef:
            r0 = move-exception
            r12 = r7
            r13 = r8
            r2 = r10
            r3 = r11
            goto L_0x00fb
        L_0x00f5:
            throw r7     // Catch:{ IllegalArgumentException -> 0x00f6 }
        L_0x00f6:
            r0 = move-exception
            goto L_0x00fb
        L_0x00f8:
            r0 = move-exception
            r12 = r7
            r13 = r8
        L_0x00fb:
            r0 = r4
            com.google.zxing.FormatException r4 = com.google.zxing.FormatException.getFormatInstance()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.decoder.DecodedBitStreamParser.decode(byte[], com.google.zxing.qrcode.decoder.Version, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel, java.util.Map):com.google.zxing.common.DecoderResult");
    }

    private static void decodeHanziSegment(BitSource bitSource, StringBuilder sb, int i) throws FormatException {
        int i2;
        if (i * 13 <= bitSource.available()) {
            byte[] bArr = new byte[(i * 2)];
            int i3 = 0;
            while (i > 0) {
                int readBits = bitSource.readBits(13);
                int i4 = (readBits % 96) | ((readBits / 96) << 8);
                if (i4 < 959) {
                    i2 = 41377;
                } else {
                    i2 = 42657;
                }
                int i5 = i4 + i2;
                bArr[i3] = (byte) (i5 >> 8);
                bArr[i3 + 1] = (byte) i5;
                i3 += 2;
                i--;
            }
            try {
                sb.append(new String(bArr, StringUtils.GB2312));
            } catch (UnsupportedEncodingException e) {
                throw FormatException.getFormatInstance();
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private static void decodeKanjiSegment(BitSource bitSource, StringBuilder sb, int i) throws FormatException {
        int i2;
        if (i * 13 <= bitSource.available()) {
            byte[] bArr = new byte[(i * 2)];
            int i3 = 0;
            while (i > 0) {
                int readBits = bitSource.readBits(13);
                int i4 = (readBits % 192) | ((readBits / 192) << 8);
                if (i4 < 7936) {
                    i2 = 33088;
                } else {
                    i2 = 49472;
                }
                int i5 = i4 + i2;
                bArr[i3] = (byte) (i5 >> 8);
                bArr[i3 + 1] = (byte) i5;
                i3 += 2;
                i--;
            }
            try {
                sb.append(new String(bArr, StringUtils.SHIFT_JIS));
            } catch (UnsupportedEncodingException e) {
                throw FormatException.getFormatInstance();
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private static void decodeByteSegment(BitSource bits, StringBuilder result, int count, CharacterSetECI currentCharacterSetECI, Collection<byte[]> byteSegments, Map<DecodeHintType, ?> hints) throws FormatException {
        String encoding;
        if ((count << 3) <= bits.available()) {
            byte[] readBytes = new byte[count];
            for (int i = 0; i < count; i++) {
                readBytes[i] = (byte) bits.readBits(8);
            }
            if (currentCharacterSetECI == null) {
                encoding = StringUtils.guessEncoding(readBytes, hints);
            } else {
                encoding = currentCharacterSetECI.name();
            }
            try {
                result.append(new String(readBytes, encoding));
                byteSegments.add(readBytes);
            } catch (UnsupportedEncodingException e) {
                throw FormatException.getFormatInstance();
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private static char toAlphaNumericChar(int value) throws FormatException {
        char[] cArr = ALPHANUMERIC_CHARS;
        if (value < cArr.length) {
            return cArr[value];
        }
        throw FormatException.getFormatInstance();
    }

    private static void decodeAlphanumericSegment(BitSource bits, StringBuilder result, int count, boolean fc1InEffect) throws FormatException {
        int start = result.length();
        while (count > 1) {
            if (bits.available() >= 11) {
                int nextTwoCharsBits = bits.readBits(11);
                result.append(toAlphaNumericChar(nextTwoCharsBits / 45));
                result.append(toAlphaNumericChar(nextTwoCharsBits % 45));
                count -= 2;
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (count == 1) {
            if (bits.available() >= 6) {
                result.append(toAlphaNumericChar(bits.readBits(6)));
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (fc1InEffect) {
            for (int i = start; i < result.length(); i++) {
                if (result.charAt(i) == '%') {
                    if (i >= result.length() - 1 || result.charAt(i + 1) != '%') {
                        result.setCharAt(i, 29);
                    } else {
                        result.deleteCharAt(i + 1);
                    }
                }
            }
        }
    }

    private static void decodeNumericSegment(BitSource bits, StringBuilder result, int count) throws FormatException {
        while (count >= 3) {
            if (bits.available() >= 10) {
                int readBits = bits.readBits(10);
                int threeDigitsBits = readBits;
                if (readBits < 1000) {
                    result.append(toAlphaNumericChar(threeDigitsBits / 100));
                    result.append(toAlphaNumericChar((threeDigitsBits / 10) % 10));
                    result.append(toAlphaNumericChar(threeDigitsBits % 10));
                    count -= 3;
                } else {
                    throw FormatException.getFormatInstance();
                }
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (count == 2) {
            if (bits.available() >= 7) {
                int readBits2 = bits.readBits(7);
                int twoDigitsBits = readBits2;
                if (readBits2 < 100) {
                    result.append(toAlphaNumericChar(twoDigitsBits / 10));
                    result.append(toAlphaNumericChar(twoDigitsBits % 10));
                    return;
                }
                throw FormatException.getFormatInstance();
            }
            throw FormatException.getFormatInstance();
        } else if (count != 1) {
        } else {
            if (bits.available() >= 4) {
                int readBits3 = bits.readBits(4);
                int digitBits = readBits3;
                if (readBits3 < 10) {
                    result.append(toAlphaNumericChar(digitBits));
                    return;
                }
                throw FormatException.getFormatInstance();
            }
            throw FormatException.getFormatInstance();
        }
    }

    private static int parseECIValue(BitSource bits) throws FormatException {
        int readBits = bits.readBits(8);
        int firstByte = readBits;
        if ((readBits & 128) == 0) {
            return firstByte & 127;
        }
        if ((firstByte & 192) == 128) {
            return ((firstByte & 63) << 8) | bits.readBits(8);
        } else if ((firstByte & 224) == 192) {
            return ((firstByte & 31) << 16) | bits.readBits(16);
        } else {
            throw FormatException.getFormatInstance();
        }
    }
}
