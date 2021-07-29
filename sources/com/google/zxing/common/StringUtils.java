package com.google.zxing.common;

import com.google.zxing.DecodeHintType;
import java.nio.charset.Charset;
import java.util.Map;

public final class StringUtils {
    private static final boolean ASSUME_SHIFT_JIS;
    private static final String EUC_JP = "EUC_JP";
    public static final String GB2312 = "GB2312";
    private static final String ISO88591 = "ISO8859_1";
    private static final String PLATFORM_DEFAULT_ENCODING;
    public static final String SHIFT_JIS = "SJIS";
    private static final String UTF8 = "UTF8";

    static {
        String name = Charset.defaultCharset().name();
        PLATFORM_DEFAULT_ENCODING = name;
        ASSUME_SHIFT_JIS = SHIFT_JIS.equalsIgnoreCase(name) || EUC_JP.equalsIgnoreCase(name);
    }

    private StringUtils() {
    }

    public static String guessEncoding(byte[] bytes, Map<DecodeHintType, ?> hints) {
        int length;
        byte[] bArr = bytes;
        Map<DecodeHintType, ?> map = hints;
        if (map != null && map.containsKey(DecodeHintType.CHARACTER_SET)) {
            return map.get(DecodeHintType.CHARACTER_SET).toString();
        }
        int length2 = bArr.length;
        boolean canBeShiftJIS = true;
        boolean canBeUTF8 = true;
        int utf8BytesLeft = 0;
        int utf2BytesChars = 0;
        int utf3BytesChars = 0;
        int utf4BytesChars = 0;
        int sjisBytesLeft = 0;
        int sjisKatakanaChars = 0;
        int sjisCurKatakanaWordLength = 0;
        int sjisCurDoubleBytesWordLength = 0;
        int sjisMaxKatakanaWordLength = 0;
        int sjisMaxDoubleBytesWordLength = 0;
        int isoHighOther = 0;
        boolean z = false;
        boolean canBeISO88591 = true;
        if (bArr.length > 3 && bArr[0] == -17 && bArr[1] == -69 && bArr[2] == -65) {
            z = true;
        }
        boolean utf8bom = z;
        int i = 0;
        while (true) {
            if (i < length2) {
                if (!canBeISO88591 && !canBeShiftJIS && !canBeUTF8) {
                    length = length2;
                    break;
                }
                int length3 = length2;
                int value = bArr[i] & 255;
                if (canBeUTF8) {
                    if (utf8BytesLeft > 0) {
                        if ((value & 128) != 0) {
                            utf8BytesLeft--;
                        }
                    } else if ((value & 128) != 0) {
                        if ((value & 64) != 0) {
                            utf8BytesLeft++;
                            if ((value & 32) == 0) {
                                utf2BytesChars++;
                            } else {
                                utf8BytesLeft++;
                                if ((value & 16) == 0) {
                                    utf3BytesChars++;
                                } else {
                                    utf8BytesLeft++;
                                    if ((value & 8) == 0) {
                                        utf4BytesChars++;
                                    }
                                }
                            }
                        }
                    }
                    canBeUTF8 = false;
                }
                if (canBeISO88591) {
                    if (value > 127 && value < 160) {
                        canBeISO88591 = false;
                    } else if (value > 159 && (value < 192 || value == 215 || value == 247)) {
                        isoHighOther++;
                    }
                }
                if (canBeShiftJIS) {
                    if (sjisBytesLeft > 0) {
                        if (value < 64 || value == 127 || value > 252) {
                            canBeShiftJIS = false;
                        } else {
                            sjisBytesLeft--;
                        }
                    } else if (value == 128 || value == 160 || value > 239) {
                        canBeShiftJIS = false;
                    } else if (value > 160 && value < 224) {
                        sjisKatakanaChars++;
                        sjisCurKatakanaWordLength++;
                        if (sjisCurKatakanaWordLength > sjisMaxKatakanaWordLength) {
                            sjisMaxKatakanaWordLength = sjisCurKatakanaWordLength;
                            sjisCurDoubleBytesWordLength = 0;
                        } else {
                            sjisCurDoubleBytesWordLength = 0;
                        }
                    } else if (value > 127) {
                        sjisBytesLeft++;
                        sjisCurDoubleBytesWordLength++;
                        if (sjisCurDoubleBytesWordLength > sjisMaxDoubleBytesWordLength) {
                            sjisMaxDoubleBytesWordLength = sjisCurDoubleBytesWordLength;
                            sjisCurKatakanaWordLength = 0;
                        } else {
                            sjisCurKatakanaWordLength = 0;
                        }
                    } else {
                        sjisCurDoubleBytesWordLength = 0;
                        sjisCurKatakanaWordLength = 0;
                    }
                }
                i++;
                bArr = bytes;
                length2 = length3;
            } else {
                length = length2;
                break;
            }
        }
        if (canBeUTF8 && utf8BytesLeft > 0) {
            canBeUTF8 = false;
        }
        if (canBeShiftJIS && sjisBytesLeft > 0) {
            canBeShiftJIS = false;
        }
        if (canBeUTF8 && (utf8bom || utf2BytesChars + utf3BytesChars + utf4BytesChars > 0)) {
            return UTF8;
        }
        if (canBeShiftJIS && (ASSUME_SHIFT_JIS || sjisMaxKatakanaWordLength >= 3 || sjisMaxDoubleBytesWordLength >= 3)) {
            return SHIFT_JIS;
        }
        if (!canBeISO88591 || !canBeShiftJIS) {
            int i2 = length;
            if (canBeISO88591) {
                return ISO88591;
            }
            if (canBeShiftJIS) {
                return SHIFT_JIS;
            }
            if (canBeUTF8) {
                return UTF8;
            }
            return PLATFORM_DEFAULT_ENCODING;
        }
        if (sjisMaxKatakanaWordLength == 2 && sjisKatakanaChars == 2) {
            boolean z2 = utf8bom;
            int i3 = length;
        } else {
            boolean z3 = utf8bom;
            if (isoHighOther * 10 >= length) {
                return SHIFT_JIS;
            }
            return ISO88591;
        }
        return SHIFT_JIS;
    }
}
