package com.google.zxing.datamatrix.encoder;

import com.wits.pms.statuscontrol.WitsCommand;

public final class ErrorCorrection {
    private static final int[] ALOG = new int[255];
    private static final int[][] FACTORS = {new int[]{SCSU.UCHANGE4, 48, 15, 111, 62}, new int[]{23, 68, 144, WitsCommand.SystemCommand.PRE_DOT_SEEK_FM, SCSU.UQUOTEU, 92, SCSU.KATAKANAINDEX}, new int[]{28, 24, 185, 166, 223, 248, 116, 255, 110, 61}, new int[]{175, 138, WitsCommand.SystemCommand.DISMISS_POWER_VOLTAGE_DIALOG, 12, 194, 168, 39, 245, 60, 97, 120}, new int[]{41, 153, 158, 91, 61, 42, 142, 213, 97, 178, 100, SCSU.URESERVED}, new int[]{156, 97, 192, SCSU.ARMENIANINDEX, 95, 9, 157, 119, 138, 45, 18, 186, 83, 185}, new int[]{83, 195, 100, 39, 188, 75, 66, 61, SCSU.UDEFINEX, 213, 109, 129, 94, SCSU.KATAKANAINDEX, SCSU.UCHANGE1, 48, 90, 188}, new int[]{15, 195, 244, 9, SCSU.UDEFINE1, 71, 168, 2, 188, 160, 153, 145, SCSU.HIRAGANAINDEX, 79, 108, 82, 27, 174, 186, 172}, new int[]{52, 190, 88, WitsCommand.SystemCommand.DISMISS_POWER_VOLTAGE_DIALOG, 109, 39, 176, 21, 155, 197, SCSU.GREEKINDEX, 223, 155, 21, 5, 172, SCSU.KATAKANAINDEX, 124, 12, 181, 184, 96, 50, 193}, new int[]{211, SCSU.UCHANGE7, 43, 97, 71, 96, 103, 174, 37, 151, 170, 53, 75, 34, SCSU.LATININDEX, 121, 17, 138, 110, 213, 141, WitsCommand.SystemCommand.LOC_SWITCH, 120, 151, SCSU.UDEFINE1, 168, 93, 255}, new int[]{245, 127, SCSU.URESERVED, 218, 130, 250, 162, 181, 102, 120, 84, 179, WitsCommand.SystemCommand.IMPORT_CONFIG, SCSU.GREEKINDEX, 80, 182, SCSU.UCHANGE5, 18, 2, 4, 68, 33, 101, 137, 95, 119, 115, 44, 175, 184, 59, 25, SCSU.UCHANGE1, 98, 81, 112}, new int[]{77, 193, 137, 31, 19, 38, 22, 153, 247, 105, 122, 2, 245, WitsCommand.SystemCommand.NEXT_DOT_SEEK_FM, SCSU.URESERVED, 8, 175, 95, 100, 9, 167, 105, 214, 111, 57, 121, 21, 1, SCSU.HIRAGANAINDEX, 57, 54, 101, 248, WitsCommand.SystemCommand.UPDATE_MCU, 69, 50, 150, 177, SCSU.UCHANGE2, 5, 9, 5}, new int[]{245, WitsCommand.SystemCommand.PRE_SEEK_FM, 172, 223, 96, 32, 117, 22, SCSU.UDEFINE6, WitsCommand.SystemCommand.NEXT_DOT_SEEK_FM, SCSU.UDEFINE6, SCSU.UCHANGE7, WitsCommand.SystemCommand.DISMISS_POWER_VOLTAGE_DIALOG, 188, SCSU.UDEFINE5, 87, 191, 106, 16, 147, 118, 23, 37, 90, 170, WitsCommand.SystemCommand.DISMISS_POWER_VOLTAGE_DIALOG, 131, 88, 120, 100, 66, 138, 186, SCSU.UQUOTEU, 82, 44, 176, 87, 187, 147, 160, 175, 69, 213, 92, SCSU.HIRAGANAINDEX, SCSU.UCHANGE1, 19}, new int[]{175, 9, 223, SCSU.UDEFINE6, 12, 17, WitsCommand.SystemCommand.IMPORT_CONFIG, WitsCommand.SystemCommand.SET_PWM_DUTY_RATIO, 100, 29, 175, 170, SCSU.UCHANGE6, 192, 215, SCSU.UDEFINE3, 150, 159, 36, 223, 38, 200, WitsCommand.SystemCommand.PRE_SEEK_FM, 54, SCSU.UCHANGE4, 146, 218, SCSU.UDEFINE2, 117, WitsCommand.SystemCommand.UPDATE_POWER_VOLTAGE, 29, SCSU.UDEFINE0, 144, SCSU.UDEFINE6, 22, 150, WitsCommand.SystemCommand.UPDATE_SYSTEM, 117, 62, WitsCommand.SystemCommand.REBOOT_MACHINE, 164, 13, 137, 245, 127, 67, 247, 28, 155, 43, WitsCommand.SystemCommand.UPDATE_POWER_VOLTAGE, 107, SCSU.UDEFINE1, 53, 143, 46}, new int[]{SCSU.URESERVED, 93, 169, 50, 144, WitsCommand.SystemCommand.UPDATE_FK_CONFIG, 39, 118, WitsCommand.SystemCommand.UPDATE_MCU, 188, WitsCommand.SystemCommand.UPDATE_SYSTEM, 189, 143, 108, 196, 37, 185, 112, WitsCommand.SystemCommand.PRE_DOT_SEEK_FM, SCSU.UCHANGE6, 245, 63, 197, 190, 250, 106, 185, 221, 175, 64, 114, 71, 161, 44, 147, 6, 27, 218, 51, 63, 87, 10, 40, 130, 188, 17, 163, 31, 176, 170, 4, 107, SCSU.UDEFINE0, 7, 94, 166, 224, 124, 86, 47, 11, WitsCommand.SystemCommand.LOW_POWER_VOLTAGE_WARNING}, new int[]{WitsCommand.SystemCommand.IMPORT_CONFIG, SCSU.UCHANGE4, 173, 89, SCSU.GREEKINDEX, 149, 159, 56, 89, 33, 147, 244, 154, 36, 73, 127, 213, WitsCommand.SystemCommand.LOC_SWITCH, 248, 180, SCSU.UDEFINE2, 197, 158, 177, 68, 122, 93, 213, 15, 160, SCSU.UCHANGE3, SCSU.UDEFINE4, 66, 139, 153, 185, WitsCommand.SystemCommand.UPDATE_MCU, 167, 179, 25, WitsCommand.SystemCommand.IMPORT_CONFIG, SCSU.UDEFINE0, 96, WitsCommand.SystemCommand.UPDATE_FK_CONFIG, SCSU.UCHANGE7, WitsCommand.SystemCommand.LOC_SWITCH, 223, SCSU.UDEFINE7, 181, SCSU.UDEFINEX, 59, 52, 172, 25, 49, SCSU.UDEFINE0, 211, 189, 64, 54, 108, 153, WitsCommand.SystemCommand.PRE_SEEK_FM, 63, 96, 103, 82, 186}};
    private static final int[] FACTOR_SETS = {5, 7, 10, 11, 12, 14, 18, 20, 24, 28, 36, 42, 48, 56, 62, 68};
    private static final int[] LOG = new int[256];
    private static final int MODULO_VALUE = 301;

    static {
        int p = 1;
        for (int i = 0; i < 255; i++) {
            ALOG[i] = p;
            LOG[p] = i;
            int i2 = p << 1;
            p = i2;
            if (i2 >= 256) {
                p ^= MODULO_VALUE;
            }
        }
    }

    private ErrorCorrection() {
    }

    public static String encodeECC200(String codewords, SymbolInfo symbolInfo) {
        if (codewords.length() == symbolInfo.getDataCapacity()) {
            StringBuilder sb = new StringBuilder(symbolInfo.getDataCapacity() + symbolInfo.getErrorCodewords());
            StringBuilder sb2 = sb;
            sb.append(codewords);
            int interleavedBlockCount = symbolInfo.getInterleavedBlockCount();
            int blockCount = interleavedBlockCount;
            if (interleavedBlockCount == 1) {
                sb2.append(createECCBlock(codewords, symbolInfo.getErrorCodewords()));
            } else {
                sb2.setLength(sb2.capacity());
                int[] dataSizes = new int[blockCount];
                int[] errorSizes = new int[blockCount];
                int[] startPos = new int[blockCount];
                for (int i = 0; i < blockCount; i++) {
                    dataSizes[i] = symbolInfo.getDataLengthForInterleavedBlock(i + 1);
                    errorSizes[i] = symbolInfo.getErrorLengthForInterleavedBlock(i + 1);
                    startPos[i] = 0;
                    if (i > 0) {
                        startPos[i] = startPos[i - 1] + dataSizes[i];
                    }
                }
                for (int block = 0; block < blockCount; block++) {
                    StringBuilder temp = new StringBuilder(dataSizes[block]);
                    for (int d = block; d < symbolInfo.getDataCapacity(); d += blockCount) {
                        temp.append(codewords.charAt(d));
                    }
                    String ecc = createECCBlock(temp.toString(), errorSizes[block]);
                    int pos = 0;
                    int e = block;
                    while (e < errorSizes[block] * blockCount) {
                        sb2.setCharAt(symbolInfo.getDataCapacity() + e, ecc.charAt(pos));
                        e += blockCount;
                        pos++;
                    }
                }
            }
            return sb2.toString();
        }
        throw new IllegalArgumentException("The number of codewords does not match the selected symbol");
    }

    private static String createECCBlock(CharSequence codewords, int numECWords) {
        return createECCBlock(codewords, 0, codewords.length(), numECWords);
    }

    private static String createECCBlock(CharSequence codewords, int start, int len, int numECWords) {
        int table = -1;
        int i = 0;
        while (true) {
            int[] iArr = FACTOR_SETS;
            if (i >= iArr.length) {
                break;
            } else if (iArr[i] == numECWords) {
                table = i;
                break;
            } else {
                i++;
            }
        }
        if (table >= 0) {
            int[] poly = FACTORS[table];
            char[] ecc = new char[numECWords];
            for (int i2 = 0; i2 < numECWords; i2++) {
                ecc[i2] = 0;
            }
            for (int i3 = start; i3 < start + len; i3++) {
                int m = ecc[numECWords - 1] ^ codewords.charAt(i3);
                for (int k = numECWords - 1; k > 0; k--) {
                    if (m == 0 || poly[k] == 0) {
                        ecc[k] = ecc[k - 1];
                    } else {
                        char c = ecc[k - 1];
                        int[] iArr2 = ALOG;
                        int[] iArr3 = LOG;
                        ecc[k] = (char) (c ^ iArr2[(iArr3[m] + iArr3[poly[k]]) % 255]);
                    }
                }
                if (m == 0 || poly[0] == 0) {
                    ecc[0] = 0;
                } else {
                    int[] iArr4 = ALOG;
                    int[] iArr5 = LOG;
                    ecc[0] = (char) iArr4[(iArr5[m] + iArr5[poly[0]]) % 255];
                }
            }
            char[] eccReversed = new char[numECWords];
            for (int i4 = 0; i4 < numECWords; i4++) {
                eccReversed[i4] = ecc[(numECWords - i4) - 1];
            }
            return String.valueOf(eccReversed);
        }
        throw new IllegalArgumentException("Illegal number of error correction codewords specified: ".concat(String.valueOf(numECWords)));
    }
}
