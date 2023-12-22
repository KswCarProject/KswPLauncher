package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.pdf417.PDF417ResultMetadata;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/* loaded from: classes.dex */
final class DecodedBitStreamParser {

    /* renamed from: AL */
    private static final int f109AL = 28;

    /* renamed from: AS */
    private static final int f110AS = 27;
    private static final int BEGIN_MACRO_PDF417_CONTROL_BLOCK = 928;
    private static final int BEGIN_MACRO_PDF417_OPTIONAL_FIELD = 923;
    private static final int BYTE_COMPACTION_MODE_LATCH = 901;
    private static final int BYTE_COMPACTION_MODE_LATCH_6 = 924;
    private static final int ECI_CHARSET = 927;
    private static final int ECI_GENERAL_PURPOSE = 926;
    private static final int ECI_USER_DEFINED = 925;
    private static final BigInteger[] EXP900;

    /* renamed from: LL */
    private static final int f111LL = 27;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_ADDRESSEE = 4;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_CHECKSUM = 6;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_FILE_NAME = 0;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_FILE_SIZE = 5;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_SEGMENT_COUNT = 1;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_SENDER = 3;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_TIME_STAMP = 2;
    private static final int MACRO_PDF417_TERMINATOR = 922;
    private static final int MAX_NUMERIC_CODEWORDS = 15;

    /* renamed from: ML */
    private static final int f112ML = 28;
    private static final int MODE_SHIFT_TO_BYTE_COMPACTION_MODE = 913;
    private static final int NUMBER_OF_SEQUENCE_CODEWORDS = 2;
    private static final int NUMERIC_COMPACTION_MODE_LATCH = 902;
    private static final int PAL = 29;

    /* renamed from: PL */
    private static final int f113PL = 25;

    /* renamed from: PS */
    private static final int f114PS = 29;
    private static final int TEXT_COMPACTION_MODE_LATCH = 900;
    private static final char[] PUNCT_CHARS = ";<>@[\\]_`~!\r\t,:\n-.$/\"|*()?{}'".toCharArray();
    private static final char[] MIXED_CHARS = "0123456789&\r\t,:#-.$/+%*=^".toCharArray();

    /* loaded from: classes.dex */
    private enum Mode {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static {
        BigInteger[] bigIntegerArr = new BigInteger[16];
        EXP900 = bigIntegerArr;
        bigIntegerArr[0] = BigInteger.ONE;
        BigInteger nineHundred = BigInteger.valueOf(900L);
        bigIntegerArr[1] = nineHundred;
        int i = 2;
        while (true) {
            BigInteger[] bigIntegerArr2 = EXP900;
            if (i < bigIntegerArr2.length) {
                bigIntegerArr2[i] = bigIntegerArr2[i - 1].multiply(nineHundred);
                i++;
            } else {
                return;
            }
        }
    }

    private DecodedBitStreamParser() {
    }

    static DecoderResult decode(int[] codewords, String ecLevel) throws FormatException {
        int codeIndex;
        StringBuilder result = new StringBuilder(codewords.length << 1);
        Charset encoding = StandardCharsets.ISO_8859_1;
        int code = codewords[1];
        PDF417ResultMetadata resultMetadata = new PDF417ResultMetadata();
        for (int codeIndex2 = 1 + 1; codeIndex2 < codewords[0]; codeIndex2 = codeIndex + 1) {
            switch (code) {
                case 900:
                    codeIndex = textCompaction(codewords, codeIndex2, result);
                    break;
                case BYTE_COMPACTION_MODE_LATCH /* 901 */:
                case BYTE_COMPACTION_MODE_LATCH_6 /* 924 */:
                    codeIndex = byteCompaction(code, codewords, encoding, codeIndex2, result);
                    break;
                case NUMERIC_COMPACTION_MODE_LATCH /* 902 */:
                    codeIndex = numericCompaction(codewords, codeIndex2, result);
                    break;
                case MODE_SHIFT_TO_BYTE_COMPACTION_MODE /* 913 */:
                    result.append((char) codewords[codeIndex2]);
                    codeIndex = codeIndex2 + 1;
                    break;
                case MACRO_PDF417_TERMINATOR /* 922 */:
                case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /* 923 */:
                    throw FormatException.getFormatInstance();
                case ECI_USER_DEFINED /* 925 */:
                    codeIndex = codeIndex2 + 1;
                    break;
                case ECI_GENERAL_PURPOSE /* 926 */:
                    codeIndex = codeIndex2 + 2;
                    break;
                case ECI_CHARSET /* 927 */:
                    encoding = Charset.forName(CharacterSetECI.getCharacterSetECIByValue(codewords[codeIndex2]).name());
                    codeIndex = codeIndex2 + 1;
                    break;
                case 928:
                    codeIndex = decodeMacroBlock(codewords, codeIndex2, resultMetadata);
                    break;
                default:
                    codeIndex = textCompaction(codewords, codeIndex2 - 1, result);
                    break;
            }
            if (codeIndex < codewords.length) {
                code = codewords[codeIndex];
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (result.length() == 0) {
            throw FormatException.getFormatInstance();
        }
        DecoderResult decoderResult = new DecoderResult(null, result.toString(), null, ecLevel);
        decoderResult.setOther(resultMetadata);
        return decoderResult;
    }

    static int decodeMacroBlock(int[] codewords, int codeIndex, PDF417ResultMetadata resultMetadata) throws FormatException {
        if (codeIndex + 2 > codewords[0]) {
            throw FormatException.getFormatInstance();
        }
        int[] segmentIndexArray = new int[2];
        int i = 0;
        while (i < 2) {
            segmentIndexArray[i] = codewords[codeIndex];
            i++;
            codeIndex++;
        }
        resultMetadata.setSegmentIndex(Integer.parseInt(decodeBase900toBase10(segmentIndexArray, 2)));
        StringBuilder fileId = new StringBuilder();
        int codeIndex2 = textCompaction(codewords, codeIndex, fileId);
        resultMetadata.setFileId(fileId.toString());
        int optionalFieldsStart = -1;
        if (codewords[codeIndex2] == BEGIN_MACRO_PDF417_OPTIONAL_FIELD) {
            optionalFieldsStart = codeIndex2 + 1;
        }
        while (codeIndex2 < codewords[0]) {
            switch (codewords[codeIndex2]) {
                case MACRO_PDF417_TERMINATOR /* 922 */:
                    codeIndex2++;
                    resultMetadata.setLastSegment(true);
                    break;
                case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /* 923 */:
                    int codeIndex3 = codeIndex2 + 1;
                    switch (codewords[codeIndex3]) {
                        case 0:
                            StringBuilder fileName = new StringBuilder();
                            codeIndex2 = textCompaction(codewords, codeIndex3 + 1, fileName);
                            resultMetadata.setFileName(fileName.toString());
                            continue;
                        case 1:
                            StringBuilder segmentCount = new StringBuilder();
                            codeIndex2 = numericCompaction(codewords, codeIndex3 + 1, segmentCount);
                            resultMetadata.setSegmentCount(Integer.parseInt(segmentCount.toString()));
                            continue;
                        case 2:
                            StringBuilder timestamp = new StringBuilder();
                            codeIndex2 = numericCompaction(codewords, codeIndex3 + 1, timestamp);
                            resultMetadata.setTimestamp(Long.parseLong(timestamp.toString()));
                            continue;
                        case 3:
                            StringBuilder sender = new StringBuilder();
                            codeIndex2 = textCompaction(codewords, codeIndex3 + 1, sender);
                            resultMetadata.setSender(sender.toString());
                            continue;
                        case 4:
                            StringBuilder addressee = new StringBuilder();
                            codeIndex2 = textCompaction(codewords, codeIndex3 + 1, addressee);
                            resultMetadata.setAddressee(addressee.toString());
                            continue;
                        case 5:
                            StringBuilder fileSize = new StringBuilder();
                            codeIndex2 = numericCompaction(codewords, codeIndex3 + 1, fileSize);
                            resultMetadata.setFileSize(Long.parseLong(fileSize.toString()));
                            continue;
                        case 6:
                            StringBuilder checksum = new StringBuilder();
                            codeIndex2 = numericCompaction(codewords, codeIndex3 + 1, checksum);
                            resultMetadata.setChecksum(Integer.parseInt(checksum.toString()));
                            continue;
                        default:
                            throw FormatException.getFormatInstance();
                    }
                default:
                    throw FormatException.getFormatInstance();
            }
        }
        if (optionalFieldsStart != -1) {
            int optionalFieldsLength = codeIndex2 - optionalFieldsStart;
            if (resultMetadata.isLastSegment()) {
                optionalFieldsLength--;
            }
            resultMetadata.setOptionalData(Arrays.copyOfRange(codewords, optionalFieldsStart, optionalFieldsStart + optionalFieldsLength));
        }
        return codeIndex2;
    }

    private static int textCompaction(int[] codewords, int codeIndex, StringBuilder result) {
        int[] textCompactionData = new int[(codewords[0] - codeIndex) << 1];
        int[] byteCompactionData = new int[(codewords[0] - codeIndex) << 1];
        int index = 0;
        boolean end = false;
        while (codeIndex < codewords[0] && !end) {
            int codeIndex2 = codeIndex + 1;
            int code = codewords[codeIndex];
            if (code < 900) {
                textCompactionData[index] = code / 30;
                textCompactionData[index + 1] = code % 30;
                index += 2;
                codeIndex = codeIndex2;
            } else {
                switch (code) {
                    case 900:
                        textCompactionData[index] = 900;
                        index++;
                        codeIndex = codeIndex2;
                        continue;
                    case BYTE_COMPACTION_MODE_LATCH /* 901 */:
                    case NUMERIC_COMPACTION_MODE_LATCH /* 902 */:
                    case MACRO_PDF417_TERMINATOR /* 922 */:
                    case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /* 923 */:
                    case BYTE_COMPACTION_MODE_LATCH_6 /* 924 */:
                    case 928:
                        codeIndex = codeIndex2 - 1;
                        end = true;
                        continue;
                    case MODE_SHIFT_TO_BYTE_COMPACTION_MODE /* 913 */:
                        textCompactionData[index] = MODE_SHIFT_TO_BYTE_COMPACTION_MODE;
                        codeIndex = codeIndex2 + 1;
                        byteCompactionData[index] = codewords[codeIndex2];
                        index++;
                        continue;
                    default:
                        codeIndex = codeIndex2;
                        continue;
                }
            }
        }
        decodeTextCompaction(textCompactionData, byteCompactionData, index, result);
        return codeIndex;
    }

    private static void decodeTextCompaction(int[] textCompactionData, int[] byteCompactionData, int length, StringBuilder result) {
        Mode subMode = Mode.ALPHA;
        Mode priorToShiftMode = Mode.ALPHA;
        for (int i = 0; i < length; i++) {
            int subModeCh = textCompactionData[i];
            char ch = 0;
            switch (C06781.f115x45bba1d[subMode.ordinal()]) {
                case 1:
                    if (subModeCh < 26) {
                        ch = (char) (subModeCh + 65);
                        break;
                    } else {
                        switch (subModeCh) {
                            case 26:
                                ch = ' ';
                                break;
                            case 27:
                                subMode = Mode.LOWER;
                                break;
                            case 28:
                                subMode = Mode.MIXED;
                                break;
                            case 29:
                                priorToShiftMode = subMode;
                                subMode = Mode.PUNCT_SHIFT;
                                break;
                            case 900:
                                subMode = Mode.ALPHA;
                                break;
                            case MODE_SHIFT_TO_BYTE_COMPACTION_MODE /* 913 */:
                                result.append((char) byteCompactionData[i]);
                                break;
                        }
                    }
                    break;
                case 2:
                    if (subModeCh < 26) {
                        ch = (char) (subModeCh + 97);
                        break;
                    } else {
                        switch (subModeCh) {
                            case 26:
                                ch = ' ';
                                break;
                            case 27:
                                priorToShiftMode = subMode;
                                subMode = Mode.ALPHA_SHIFT;
                                break;
                            case 28:
                                subMode = Mode.MIXED;
                                break;
                            case 29:
                                priorToShiftMode = subMode;
                                subMode = Mode.PUNCT_SHIFT;
                                break;
                            case 900:
                                subMode = Mode.ALPHA;
                                break;
                            case MODE_SHIFT_TO_BYTE_COMPACTION_MODE /* 913 */:
                                result.append((char) byteCompactionData[i]);
                                break;
                        }
                    }
                case 3:
                    if (subModeCh < 25) {
                        ch = MIXED_CHARS[subModeCh];
                        break;
                    } else {
                        switch (subModeCh) {
                            case 25:
                                subMode = Mode.PUNCT;
                                break;
                            case 26:
                                ch = ' ';
                                break;
                            case 27:
                                subMode = Mode.LOWER;
                                break;
                            case 28:
                                subMode = Mode.ALPHA;
                                break;
                            case 29:
                                priorToShiftMode = subMode;
                                subMode = Mode.PUNCT_SHIFT;
                                break;
                            case 900:
                                subMode = Mode.ALPHA;
                                break;
                            case MODE_SHIFT_TO_BYTE_COMPACTION_MODE /* 913 */:
                                result.append((char) byteCompactionData[i]);
                                break;
                        }
                    }
                case 4:
                    if (subModeCh < 29) {
                        ch = PUNCT_CHARS[subModeCh];
                        break;
                    } else {
                        switch (subModeCh) {
                            case 29:
                                subMode = Mode.ALPHA;
                                break;
                            case 900:
                                subMode = Mode.ALPHA;
                                break;
                            case MODE_SHIFT_TO_BYTE_COMPACTION_MODE /* 913 */:
                                result.append((char) byteCompactionData[i]);
                                break;
                        }
                    }
                case 5:
                    subMode = priorToShiftMode;
                    if (subModeCh < 26) {
                        ch = (char) (subModeCh + 65);
                        break;
                    } else {
                        switch (subModeCh) {
                            case 26:
                                ch = ' ';
                                break;
                            case 900:
                                subMode = Mode.ALPHA;
                                break;
                        }
                    }
                    break;
                case 6:
                    subMode = priorToShiftMode;
                    if (subModeCh < 29) {
                        ch = PUNCT_CHARS[subModeCh];
                        break;
                    } else {
                        switch (subModeCh) {
                            case 29:
                                subMode = Mode.ALPHA;
                                break;
                            case 900:
                                subMode = Mode.ALPHA;
                                break;
                            case MODE_SHIFT_TO_BYTE_COMPACTION_MODE /* 913 */:
                                result.append((char) byteCompactionData[i]);
                                break;
                        }
                    }
                    break;
            }
            if (ch != 0) {
                result.append(ch);
            }
        }
    }

    /* renamed from: com.google.zxing.pdf417.decoder.DecodedBitStreamParser$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C06781 {

        /* renamed from: $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode */
        static final /* synthetic */ int[] f115x45bba1d;

        static {
            int[] iArr = new int[Mode.values().length];
            f115x45bba1d = iArr;
            try {
                iArr[Mode.ALPHA.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f115x45bba1d[Mode.LOWER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f115x45bba1d[Mode.MIXED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f115x45bba1d[Mode.PUNCT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f115x45bba1d[Mode.ALPHA_SHIFT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f115x45bba1d[Mode.PUNCT_SHIFT.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    private static int byteCompaction(int mode, int[] codewords, Charset encoding, int codeIndex, StringBuilder result) {
        int count;
        ByteArrayOutputStream decodedBytes = new ByteArrayOutputStream();
        int count2 = 0;
        long value = 0;
        boolean end = false;
        long j = 900;
        switch (mode) {
            case BYTE_COMPACTION_MODE_LATCH /* 901 */:
                int[] byteCompactedCodewords = new int[6];
                int j2 = codeIndex + 1;
                int nextCode = codewords[codeIndex];
                while (j2 < codewords[0] && !end) {
                    int count3 = count2 + 1;
                    byteCompactedCodewords[count2] = nextCode;
                    value = (value * 900) + nextCode;
                    int codeIndex2 = j2 + 1;
                    int codeIndex3 = codewords[j2];
                    nextCode = codeIndex3;
                    switch (codeIndex3) {
                        case 900:
                        case BYTE_COMPACTION_MODE_LATCH /* 901 */:
                        case NUMERIC_COMPACTION_MODE_LATCH /* 902 */:
                        case MACRO_PDF417_TERMINATOR /* 922 */:
                        case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /* 923 */:
                        case BYTE_COMPACTION_MODE_LATCH_6 /* 924 */:
                        case 928:
                            j2 = codeIndex2 - 1;
                            end = true;
                            count2 = count3;
                            break;
                        default:
                            if (count3 % 5 != 0 || count3 <= 0) {
                                j2 = codeIndex2;
                                count2 = count3;
                                break;
                            } else {
                                for (int codeIndex4 = 0; codeIndex4 < 6; codeIndex4++) {
                                    decodedBytes.write((byte) (value >> ((5 - codeIndex4) * 8)));
                                }
                                value = 0;
                                j2 = codeIndex2;
                                count2 = 0;
                                break;
                            }
                    }
                }
                if (j2 == codewords[0] && nextCode < 900) {
                    byteCompactedCodewords[count2] = nextCode;
                    count2++;
                }
                for (int i = 0; i < count2; i++) {
                    decodedBytes.write((byte) byteCompactedCodewords[i]);
                }
                count = j2;
                break;
            case BYTE_COMPACTION_MODE_LATCH_6 /* 924 */:
                boolean end2 = false;
                long value2 = 0;
                int count4 = 0;
                count = codeIndex;
                while (count < codewords[0] && !end2) {
                    int codeIndex5 = count + 1;
                    int code = codewords[count];
                    if (code < 900) {
                        count4++;
                        value2 = (value2 * j) + code;
                        count = codeIndex5;
                    } else {
                        switch (code) {
                            case 900:
                            case BYTE_COMPACTION_MODE_LATCH /* 901 */:
                            case NUMERIC_COMPACTION_MODE_LATCH /* 902 */:
                            case MACRO_PDF417_TERMINATOR /* 922 */:
                            case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /* 923 */:
                            case BYTE_COMPACTION_MODE_LATCH_6 /* 924 */:
                            case 928:
                                end2 = true;
                                count = codeIndex5 - 1;
                                break;
                            default:
                                count = codeIndex5;
                                break;
                        }
                    }
                    if (count4 % 5 == 0 && count4 > 0) {
                        for (int j3 = 0; j3 < 6; j3++) {
                            decodedBytes.write((byte) (value2 >> ((5 - j3) * 8)));
                        }
                        value2 = 0;
                        count4 = 0;
                    }
                    j = 900;
                }
                break;
            default:
                count = codeIndex;
                break;
        }
        result.append(new String(decodedBytes.toByteArray(), encoding));
        return count;
    }

    private static int numericCompaction(int[] codewords, int code, StringBuilder result) throws FormatException {
        int count = 0;
        boolean end = false;
        int[] numericCodewords = new int[15];
        while (code < codewords[0] && !end) {
            int codeIndex = code + 1;
            int code2 = codewords[code];
            if (codeIndex == codewords[0]) {
                end = true;
            }
            if (code2 < 900) {
                numericCodewords[count] = code2;
                count++;
            } else {
                switch (code2) {
                    case 900:
                    case BYTE_COMPACTION_MODE_LATCH /* 901 */:
                    case MACRO_PDF417_TERMINATOR /* 922 */:
                    case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /* 923 */:
                    case BYTE_COMPACTION_MODE_LATCH_6 /* 924 */:
                    case 928:
                        codeIndex--;
                        end = true;
                        break;
                }
            }
            if ((count % 15 == 0 || code2 == NUMERIC_COMPACTION_MODE_LATCH || end) && count > 0) {
                result.append(decodeBase900toBase10(numericCodewords, count));
                count = 0;
            }
            code = codeIndex;
        }
        return code;
    }

    private static String decodeBase900toBase10(int[] codewords, int count) throws FormatException {
        BigInteger result = BigInteger.ZERO;
        for (int i = 0; i < count; i++) {
            result = result.add(EXP900[(count - i) - 1].multiply(BigInteger.valueOf(codewords[i])));
        }
        String resultString = result.toString();
        if (resultString.charAt(0) != '1') {
            throw FormatException.getFormatInstance();
        }
        return resultString.substring(1);
    }
}
