package com.google.zxing.common;

import com.google.zxing.DecodeHintType;
import java.nio.charset.Charset;
import java.util.Map;

/* loaded from: classes.dex */
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
        if (hints != null && hints.containsKey(DecodeHintType.CHARACTER_SET)) {
            return hints.get(DecodeHintType.CHARACTER_SET).toString();
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
            if (i >= length2) {
                length = length2;
                break;
            } else if (!canBeISO88591 && !canBeShiftJIS && !canBeUTF8) {
                length = length2;
                break;
            } else {
                int length3 = length2;
                int length4 = bArr[i];
                int value = length4 & 255;
                if (canBeUTF8) {
                    if (utf8BytesLeft > 0) {
                        if ((value & 128) != 0) {
                            utf8BytesLeft--;
                        }
                        canBeUTF8 = false;
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
                        canBeUTF8 = false;
                    }
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
                        if (sjisCurKatakanaWordLength <= sjisMaxKatakanaWordLength) {
                            sjisCurDoubleBytesWordLength = 0;
                        } else {
                            sjisMaxKatakanaWordLength = sjisCurKatakanaWordLength;
                            sjisCurDoubleBytesWordLength = 0;
                        }
                    } else if (value > 127) {
                        sjisBytesLeft++;
                        sjisCurDoubleBytesWordLength++;
                        if (sjisCurDoubleBytesWordLength <= sjisMaxDoubleBytesWordLength) {
                            sjisCurKatakanaWordLength = 0;
                        } else {
                            sjisMaxDoubleBytesWordLength = sjisCurDoubleBytesWordLength;
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
            }
        }
        if (canBeUTF8 && utf8BytesLeft > 0) {
            canBeUTF8 = false;
        }
        if (canBeShiftJIS && sjisBytesLeft > 0) {
            canBeShiftJIS = false;
        }
        if (!canBeUTF8 || (!utf8bom && utf2BytesChars + utf3BytesChars + utf4BytesChars <= 0)) {
            if (!canBeShiftJIS || (!ASSUME_SHIFT_JIS && sjisMaxKatakanaWordLength < 3 && sjisMaxDoubleBytesWordLength < 3)) {
                if (canBeISO88591 && canBeShiftJIS) {
                    if ((sjisMaxKatakanaWordLength == 2 && sjisKatakanaChars == 2) || isoHighOther * 10 >= length) {
                        return SHIFT_JIS;
                    }
                    return ISO88591;
                } else if (canBeISO88591) {
                    return ISO88591;
                } else {
                    if (canBeShiftJIS) {
                        return SHIFT_JIS;
                    }
                    if (canBeUTF8) {
                        return UTF8;
                    }
                    return PLATFORM_DEFAULT_ENCODING;
                }
            }
            return SHIFT_JIS;
        }
        return UTF8;
    }
}
