package com.ibm.icu.text;

public final class UnicodeDecompressor implements SCSU {
    private static final int BUFSIZE = 3;
    private byte[] fBuffer = new byte[3];
    private int fBufferLength = 0;
    private int fCurrentWindow = 0;
    private int fMode = 0;
    private int[] fOffsets = new int[8];

    public UnicodeDecompressor() {
        reset();
    }

    public static String decompress(byte[] buffer) {
        return new String(decompress(buffer, 0, buffer.length));
    }

    public static char[] decompress(byte[] buffer, int start, int limit) {
        UnicodeDecompressor comp = new UnicodeDecompressor();
        int len = Math.max(2, (limit - start) * 2);
        char[] temp = new char[len];
        int charCount = comp.decompress(buffer, start, limit, (int[]) null, temp, 0, len);
        char[] result = new char[charCount];
        System.arraycopy(temp, 0, result, 0, charCount);
        return result;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005f, code lost:
        if (r14 >= r11) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0061, code lost:
        if (r15 >= r13) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0063, code lost:
        r0 = r14 + 1;
        r3 = r9[r14] & 255;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0069, code lost:
        switch(r3) {
            case 224: goto L_0x0116;
            case com.ibm.icu.text.SCSU.UCHANGE1 :int: goto L_0x0116;
            case com.ibm.icu.text.SCSU.UCHANGE2 :int: goto L_0x0116;
            case com.ibm.icu.text.SCSU.UCHANGE3 :int: goto L_0x0116;
            case com.ibm.icu.text.SCSU.UCHANGE4 :int: goto L_0x0116;
            case com.ibm.icu.text.SCSU.UCHANGE5 :int: goto L_0x0116;
            case com.ibm.icu.text.SCSU.UCHANGE6 :int: goto L_0x0116;
            case com.ibm.icu.text.SCSU.UCHANGE7 :int: goto L_0x0116;
            case com.ibm.icu.text.SCSU.UDEFINE0 :int: goto L_0x00e8;
            case com.ibm.icu.text.SCSU.UDEFINE1 :int: goto L_0x00e8;
            case com.ibm.icu.text.SCSU.UDEFINE2 :int: goto L_0x00e8;
            case com.ibm.icu.text.SCSU.UDEFINE3 :int: goto L_0x00e8;
            case com.ibm.icu.text.SCSU.UDEFINE4 :int: goto L_0x00e8;
            case com.ibm.icu.text.SCSU.UDEFINE5 :int: goto L_0x00e8;
            case com.ibm.icu.text.SCSU.UDEFINE6 :int: goto L_0x00e8;
            case com.ibm.icu.text.SCSU.UDEFINE7 :int: goto L_0x00e8;
            case com.ibm.icu.text.SCSU.UQUOTEU :int: goto L_0x00bc;
            case com.ibm.icu.text.SCSU.UDEFINEX :int: goto L_0x0081;
            default: goto L_0x006c;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x006c, code lost:
        if (r0 < r11) goto L_0x0121;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x006e, code lost:
        r0 = r0 - 1;
        java.lang.System.arraycopy(r9, r0, r8.fBuffer, r10, r11 - r0);
        r1 = r11 - r0;
        r8.fBufferLength = r1;
        r14 = r0 + r1;
        r16 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0083, code lost:
        if ((r0 + 1) < r11) goto L_0x0098;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0085, code lost:
        r0 = r0 - 1;
        java.lang.System.arraycopy(r9, r0, r8.fBuffer, r10, r11 - r0);
        r1 = r11 - r0;
        r8.fBufferLength = r1;
        r14 = r0 + r1;
        r16 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0098, code lost:
        r4 = r0 + 1;
        r0 = r9[r0] & 255;
        r3 = (r0 & 224) >> 5;
        r8.fCurrentWindow = r3;
        r8.fOffsets[r3] = (((r9[r4] & 255) | ((r0 & 31) << 8)) * com.ibm.icu.text.Bidi.LEVEL_OVERRIDE) + 65536;
        r8.fMode = r10;
        r16 = r0;
        r14 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00be, code lost:
        if (r0 < (r11 - 1)) goto L_0x00d3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00c0, code lost:
        r0 = r0 - 1;
        java.lang.System.arraycopy(r9, r0, r8.fBuffer, r10, r11 - r0);
        r1 = r11 - r0;
        r8.fBufferLength = r1;
        r14 = r0 + r1;
        r16 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00d3, code lost:
        r4 = r0 + 1;
        r14 = r4 + 1;
        r12[r15] = (char) ((r9[r0] << 8) | (r9[r4] & 255));
        r15 = r15 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00e8, code lost:
        if (r0 < r11) goto L_0x00fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00ea, code lost:
        r0 = r0 - 1;
        java.lang.System.arraycopy(r9, r0, r8.fBuffer, r10, r11 - r0);
        r1 = r11 - r0;
        r8.fBufferLength = r1;
        r14 = r0 + r1;
        r16 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00fd, code lost:
        r1 = r3 - 232;
        r8.fCurrentWindow = r1;
        r8.fOffsets[r1] = sOffsetTable[r9[r0] & 255];
        r8.fMode = r10;
        r16 = r3;
        r14 = r0 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0116, code lost:
        r8.fCurrentWindow = r3 - 224;
        r8.fMode = r10;
        r14 = r0;
        r16 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0121, code lost:
        r14 = r0 + 1;
        r12[r15] = (char) ((r9[r0] & 255) | (r3 << 8));
        r16 = r3;
        r15 = r15 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0134, code lost:
        if (r14 >= r11) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0136, code lost:
        if (r15 >= r13) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0138, code lost:
        r0 = r14 + 1;
        r3 = r9[r14] & 255;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x013e, code lost:
        switch(r3) {
            case 0: goto L_0x026e;
            case 1: goto L_0x0237;
            case 2: goto L_0x0237;
            case 3: goto L_0x0237;
            case 4: goto L_0x0237;
            case 5: goto L_0x0237;
            case 6: goto L_0x0237;
            case 7: goto L_0x0237;
            case 8: goto L_0x0237;
            case 9: goto L_0x026e;
            case 10: goto L_0x026e;
            case 11: goto L_0x01ff;
            case 12: goto L_0x0141;
            case 13: goto L_0x026e;
            case 14: goto L_0x01d0;
            case 15: goto L_0x01c8;
            case 16: goto L_0x01c2;
            case 17: goto L_0x01c2;
            case 18: goto L_0x01c2;
            case 19: goto L_0x01c2;
            case 20: goto L_0x01c2;
            case 21: goto L_0x01c2;
            case 22: goto L_0x01c2;
            case 23: goto L_0x01c2;
            case 24: goto L_0x0196;
            case 25: goto L_0x0196;
            case 26: goto L_0x0196;
            case 27: goto L_0x0196;
            case 28: goto L_0x0196;
            case 29: goto L_0x0196;
            case 30: goto L_0x0196;
            case 31: goto L_0x0196;
            case 32: goto L_0x026e;
            case 33: goto L_0x026e;
            case 34: goto L_0x026e;
            case 35: goto L_0x026e;
            case 36: goto L_0x026e;
            case 37: goto L_0x026e;
            case 38: goto L_0x026e;
            case 39: goto L_0x026e;
            case 40: goto L_0x026e;
            case 41: goto L_0x026e;
            case 42: goto L_0x026e;
            case 43: goto L_0x026e;
            case 44: goto L_0x026e;
            case 45: goto L_0x026e;
            case 46: goto L_0x026e;
            case 47: goto L_0x026e;
            case 48: goto L_0x026e;
            case 49: goto L_0x026e;
            case 50: goto L_0x026e;
            case 51: goto L_0x026e;
            case 52: goto L_0x026e;
            case 53: goto L_0x026e;
            case 54: goto L_0x026e;
            case 55: goto L_0x026e;
            case 56: goto L_0x026e;
            case 57: goto L_0x026e;
            case 58: goto L_0x026e;
            case 59: goto L_0x026e;
            case 60: goto L_0x026e;
            case 61: goto L_0x026e;
            case 62: goto L_0x026e;
            case 63: goto L_0x026e;
            case 64: goto L_0x026e;
            case 65: goto L_0x026e;
            case 66: goto L_0x026e;
            case 67: goto L_0x026e;
            case 68: goto L_0x026e;
            case 69: goto L_0x026e;
            case 70: goto L_0x026e;
            case 71: goto L_0x026e;
            case 72: goto L_0x026e;
            case 73: goto L_0x026e;
            case 74: goto L_0x026e;
            case 75: goto L_0x026e;
            case 76: goto L_0x026e;
            case 77: goto L_0x026e;
            case 78: goto L_0x026e;
            case 79: goto L_0x026e;
            case 80: goto L_0x026e;
            case 81: goto L_0x026e;
            case 82: goto L_0x026e;
            case 83: goto L_0x026e;
            case 84: goto L_0x026e;
            case 85: goto L_0x026e;
            case 86: goto L_0x026e;
            case 87: goto L_0x026e;
            case 88: goto L_0x026e;
            case 89: goto L_0x026e;
            case 90: goto L_0x026e;
            case 91: goto L_0x026e;
            case 92: goto L_0x026e;
            case 93: goto L_0x026e;
            case 94: goto L_0x026e;
            case 95: goto L_0x026e;
            case 96: goto L_0x026e;
            case 97: goto L_0x026e;
            case 98: goto L_0x026e;
            case 99: goto L_0x026e;
            case 100: goto L_0x026e;
            case 101: goto L_0x026e;
            case 102: goto L_0x026e;
            case 103: goto L_0x026e;
            case 104: goto L_0x026e;
            case 105: goto L_0x026e;
            case 106: goto L_0x026e;
            case 107: goto L_0x026e;
            case 108: goto L_0x026e;
            case 109: goto L_0x026e;
            case 110: goto L_0x026e;
            case 111: goto L_0x026e;
            case 112: goto L_0x026e;
            case 113: goto L_0x026e;
            case 114: goto L_0x026e;
            case 115: goto L_0x026e;
            case 116: goto L_0x026e;
            case 117: goto L_0x026e;
            case 118: goto L_0x026e;
            case 119: goto L_0x026e;
            case 120: goto L_0x026e;
            case 121: goto L_0x026e;
            case 122: goto L_0x026e;
            case 123: goto L_0x026e;
            case 124: goto L_0x026e;
            case 125: goto L_0x026e;
            case 126: goto L_0x026e;
            case 127: goto L_0x026e;
            case 128: goto L_0x0143;
            case 129: goto L_0x0143;
            case 130: goto L_0x0143;
            case 131: goto L_0x0143;
            case com.wits.pms.statuscontrol.WitsCommand.SystemCommand.PRE_SEEK_FM :int: goto L_0x0143;
            case com.wits.pms.statuscontrol.WitsCommand.SystemCommand.NEXT_DOT_SEEK_FM :int: goto L_0x0143;
            case com.wits.pms.statuscontrol.WitsCommand.SystemCommand.PRE_DOT_SEEK_FM :int: goto L_0x0143;
            case com.wits.pms.statuscontrol.WitsCommand.SystemCommand.ST_SWITCH :int: goto L_0x0143;
            case com.wits.pms.statuscontrol.WitsCommand.SystemCommand.LOC_SWITCH :int: goto L_0x0143;
            case 137: goto L_0x0143;
            case 138: goto L_0x0143;
            case 139: goto L_0x0143;
            case 140: goto L_0x0143;
            case 141: goto L_0x0143;
            case 142: goto L_0x0143;
            case 143: goto L_0x0143;
            case 144: goto L_0x0143;
            case 145: goto L_0x0143;
            case 146: goto L_0x0143;
            case 147: goto L_0x0143;
            case 148: goto L_0x0143;
            case 149: goto L_0x0143;
            case 150: goto L_0x0143;
            case 151: goto L_0x0143;
            case 152: goto L_0x0143;
            case 153: goto L_0x0143;
            case 154: goto L_0x0143;
            case 155: goto L_0x0143;
            case 156: goto L_0x0143;
            case 157: goto L_0x0143;
            case 158: goto L_0x0143;
            case 159: goto L_0x0143;
            case 160: goto L_0x0143;
            case 161: goto L_0x0143;
            case 162: goto L_0x0143;
            case 163: goto L_0x0143;
            case 164: goto L_0x0143;
            case 165: goto L_0x0143;
            case 166: goto L_0x0143;
            case 167: goto L_0x0143;
            case 168: goto L_0x0143;
            case 169: goto L_0x0143;
            case 170: goto L_0x0143;
            case 171: goto L_0x0143;
            case 172: goto L_0x0143;
            case 173: goto L_0x0143;
            case 174: goto L_0x0143;
            case 175: goto L_0x0143;
            case 176: goto L_0x0143;
            case 177: goto L_0x0143;
            case 178: goto L_0x0143;
            case 179: goto L_0x0143;
            case 180: goto L_0x0143;
            case 181: goto L_0x0143;
            case 182: goto L_0x0143;
            case 183: goto L_0x0143;
            case 184: goto L_0x0143;
            case 185: goto L_0x0143;
            case 186: goto L_0x0143;
            case 187: goto L_0x0143;
            case 188: goto L_0x0143;
            case 189: goto L_0x0143;
            case 190: goto L_0x0143;
            case 191: goto L_0x0143;
            case 192: goto L_0x0143;
            case 193: goto L_0x0143;
            case 194: goto L_0x0143;
            case 195: goto L_0x0143;
            case 196: goto L_0x0143;
            case 197: goto L_0x0143;
            case 198: goto L_0x0143;
            case 199: goto L_0x0143;
            case 200: goto L_0x0143;
            case com.wits.pms.statuscontrol.WitsCommand.SystemCommand.UPDATE_SYSTEM :int: goto L_0x0143;
            case com.wits.pms.statuscontrol.WitsCommand.SystemCommand.UPDATE_MCU :int: goto L_0x0143;
            case com.wits.pms.statuscontrol.WitsCommand.SystemCommand.UPDATE_POWER_VOLTAGE :int: goto L_0x0143;
            case com.wits.pms.statuscontrol.WitsCommand.SystemCommand.LOW_POWER_VOLTAGE_WARNING :int: goto L_0x0143;
            case com.wits.pms.statuscontrol.WitsCommand.SystemCommand.DISMISS_POWER_VOLTAGE_DIALOG :int: goto L_0x0143;
            case com.wits.pms.statuscontrol.WitsCommand.SystemCommand.LOW_OR_HIGH_POWER_VOLTAGE_SHUTDOWN :int: goto L_0x0143;
            case com.wits.pms.statuscontrol.WitsCommand.SystemCommand.REBOOT_MACHINE :int: goto L_0x0143;
            case com.wits.pms.statuscontrol.WitsCommand.SystemCommand.SET_PWM_DUTY_RATIO :int: goto L_0x0143;
            case com.wits.pms.statuscontrol.WitsCommand.SystemCommand.SAVE_TP_ALIGN_DATA :int: goto L_0x0143;
            case com.wits.pms.statuscontrol.WitsCommand.SystemCommand.UPDATE_FK_CONFIG :int: goto L_0x0143;
            case 211: goto L_0x0143;
            case 212: goto L_0x0143;
            case 213: goto L_0x0143;
            case 214: goto L_0x0143;
            case 215: goto L_0x0143;
            case 216: goto L_0x0143;
            case 217: goto L_0x0143;
            case 218: goto L_0x0143;
            case 219: goto L_0x0143;
            case com.wits.pms.statuscontrol.WitsCommand.SystemCommand.IMPORT_CONFIG :int: goto L_0x0143;
            case 221: goto L_0x0143;
            case 222: goto L_0x0143;
            case 223: goto L_0x0143;
            case 224: goto L_0x0143;
            case com.ibm.icu.text.SCSU.UCHANGE1 :int: goto L_0x0143;
            case com.ibm.icu.text.SCSU.UCHANGE2 :int: goto L_0x0143;
            case com.ibm.icu.text.SCSU.UCHANGE3 :int: goto L_0x0143;
            case com.ibm.icu.text.SCSU.UCHANGE4 :int: goto L_0x0143;
            case com.ibm.icu.text.SCSU.UCHANGE5 :int: goto L_0x0143;
            case com.ibm.icu.text.SCSU.UCHANGE6 :int: goto L_0x0143;
            case com.ibm.icu.text.SCSU.UCHANGE7 :int: goto L_0x0143;
            case com.ibm.icu.text.SCSU.UDEFINE0 :int: goto L_0x0143;
            case com.ibm.icu.text.SCSU.UDEFINE1 :int: goto L_0x0143;
            case com.ibm.icu.text.SCSU.UDEFINE2 :int: goto L_0x0143;
            case com.ibm.icu.text.SCSU.UDEFINE3 :int: goto L_0x0143;
            case com.ibm.icu.text.SCSU.UDEFINE4 :int: goto L_0x0143;
            case com.ibm.icu.text.SCSU.UDEFINE5 :int: goto L_0x0143;
            case com.ibm.icu.text.SCSU.UDEFINE6 :int: goto L_0x0143;
            case com.ibm.icu.text.SCSU.UDEFINE7 :int: goto L_0x0143;
            case com.ibm.icu.text.SCSU.UQUOTEU :int: goto L_0x0143;
            case com.ibm.icu.text.SCSU.UDEFINEX :int: goto L_0x0143;
            case com.ibm.icu.text.SCSU.URESERVED :int: goto L_0x0143;
            case 243: goto L_0x0143;
            case 244: goto L_0x0143;
            case 245: goto L_0x0143;
            case 246: goto L_0x0143;
            case 247: goto L_0x0143;
            case 248: goto L_0x0143;
            case com.ibm.icu.text.SCSU.LATININDEX :int: goto L_0x0143;
            case 250: goto L_0x0143;
            case com.ibm.icu.text.SCSU.GREEKINDEX :int: goto L_0x0143;
            case com.ibm.icu.text.SCSU.ARMENIANINDEX :int: goto L_0x0143;
            case com.ibm.icu.text.SCSU.HIRAGANAINDEX :int: goto L_0x0143;
            case com.ibm.icu.text.SCSU.KATAKANAINDEX :int: goto L_0x0143;
            case 255: goto L_0x0143;
            default: goto L_0x0141;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0143, code lost:
        r4 = r8.fOffsets;
        r5 = r8.fCurrentWindow;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x014c, code lost:
        if (r4[r5] > 65535) goto L_0x015d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x014e, code lost:
        r12[r15] = (char) ((r4[r5] + r3) - 128);
        r14 = r0;
        r16 = r3;
        r15 = r15 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x015f, code lost:
        if ((r15 + 1) < r13) goto L_0x0174;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0161, code lost:
        r0 = r0 - 1;
        java.lang.System.arraycopy(r9, r0, r8.fBuffer, r10, r11 - r0);
        r1 = r11 - r0;
        r8.fBufferLength = r1;
        r14 = r0 + r1;
        r16 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0174, code lost:
        r4 = r4[r5] - 65536;
        r5 = r15 + 1;
        r12[r15] = (char) ((r4 >> 10) + 55296);
        r12[r5] = (char) (((r4 & com.wits.ksw.settings.BrightnessUtils.GAMMA_SPACE_MAX) + com.ibm.icu.text.UTF16.TRAIL_SURROGATE_MIN_VALUE) + (r3 & com.ibm.icu.text.Bidi.LEVEL_DEFAULT_RTL));
        r14 = r0;
        r16 = r3;
        r15 = r5 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0196, code lost:
        if (r0 < r11) goto L_0x01ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0198, code lost:
        r0 = r0 - 1;
        java.lang.System.arraycopy(r9, r0, r8.fBuffer, r10, r11 - r0);
        r1 = r11 - r0;
        r8.fBufferLength = r1;
        r14 = r0 + r1;
        r16 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x01ab, code lost:
        r4 = r3 - 24;
        r8.fCurrentWindow = r4;
        r8.fOffsets[r4] = sOffsetTable[r9[r0] & 255];
        r16 = r3;
        r14 = r0 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x01c2, code lost:
        r8.fCurrentWindow = r3 - 16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x01c8, code lost:
        r8.fMode = 1;
        r14 = r0;
        r16 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x01d2, code lost:
        if ((r0 + 1) < r11) goto L_0x01e7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x01d4, code lost:
        r0 = r0 - 1;
        java.lang.System.arraycopy(r9, r0, r8.fBuffer, r10, r11 - r0);
        r1 = r11 - r0;
        r8.fBufferLength = r1;
        r14 = r0 + r1;
        r16 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x01e7, code lost:
        r4 = r0 + 1;
        r0 = r9[r0];
        r12[r15] = (char) ((r9[r4] & 255) | (r0 << 8));
        r16 = r0;
        r15 = r15 + 1;
        r14 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0201, code lost:
        if ((r0 + 1) < r11) goto L_0x0216;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0203, code lost:
        r0 = r0 - 1;
        java.lang.System.arraycopy(r9, r0, r8.fBuffer, r10, r11 - r0);
        r1 = r11 - r0;
        r8.fBufferLength = r1;
        r14 = r0 + r1;
        r16 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0216, code lost:
        r4 = r0 + 1;
        r0 = r9[r0] & 255;
        r3 = (r0 & 224) >> 5;
        r8.fCurrentWindow = r3;
        r8.fOffsets[r3] = (((r9[r4] & 255) | ((r0 & 31) << 8)) * com.ibm.icu.text.Bidi.LEVEL_OVERRIDE) + 65536;
        r16 = r0;
        r14 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0237, code lost:
        if (r0 < r11) goto L_0x024b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0239, code lost:
        r0 = r0 - 1;
        java.lang.System.arraycopy(r9, r0, r8.fBuffer, r10, r11 - r0);
        r1 = r11 - r0;
        r8.fBufferLength = r1;
        r14 = r0 + r1;
        r16 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x024b, code lost:
        r4 = r0 + 1;
        r0 = r9[r0] & 255;
        r5 = r15 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0253, code lost:
        if (r0 < 0) goto L_0x025e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0255, code lost:
        if (r0 >= 128) goto L_0x025e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0257, code lost:
        r6 = sOffsets[r3 - 1];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x025e, code lost:
        r6 = r8.fOffsets[r3 - 1] - 128;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0265, code lost:
        r12[r15] = (char) (r6 + r0);
        r16 = r3;
        r14 = r4;
        r15 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x026e, code lost:
        r12[r15] = (char) r3;
        r14 = r0;
        r16 = r3;
        r15 = r15 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0278, code lost:
        r14 = r0;
        r16 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0050, code lost:
        continue;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0050, code lost:
        continue;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0050, code lost:
        continue;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0050, code lost:
        continue;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int decompress(byte[] r19, int r20, int r21, int[] r22, char[] r23, int r24, int r25) {
        /*
            r18 = this;
            r8 = r18
            r9 = r19
            r10 = r20
            r11 = r21
            r12 = r23
            r13 = r25
            r14 = r20
            r15 = r24
            r16 = 0
            int r0 = r12.length
            r1 = 2
            if (r0 < r1) goto L_0x0289
            int r0 = r13 - r24
            if (r0 < r1) goto L_0x0289
            int r0 = r8.fBufferLength
            r7 = 0
            if (r0 <= 0) goto L_0x004f
            r1 = 0
            r2 = 3
            if (r0 == r2) goto L_0x0035
            byte[] r2 = r8.fBuffer
            int r3 = r2.length
            int r3 = r3 - r0
            int r1 = r11 - r10
            if (r1 >= r3) goto L_0x002e
            int r1 = r11 - r10
            goto L_0x002f
        L_0x002e:
            r1 = r3
        L_0x002f:
            java.lang.System.arraycopy(r9, r10, r2, r0, r1)
            r17 = r1
            goto L_0x0037
        L_0x0035:
            r17 = r1
        L_0x0037:
            r8.fBufferLength = r7
            byte[] r1 = r8.fBuffer
            r2 = 0
            int r3 = r1.length
            r4 = 0
            r0 = r18
            r5 = r23
            r6 = r24
            r10 = r7
            r7 = r25
            int r0 = r0.decompress(r1, r2, r3, r4, r5, r6, r7)
            int r15 = r15 + r0
            int r14 = r14 + r17
            goto L_0x0050
        L_0x004f:
            r10 = r7
        L_0x0050:
            if (r14 >= r11) goto L_0x027f
            if (r15 >= r13) goto L_0x027f
            int r0 = r8.fMode
            r1 = 65536(0x10000, float:9.18355E-41)
            r2 = 128(0x80, float:1.794E-43)
            switch(r0) {
                case 0: goto L_0x0134;
                case 1: goto L_0x005f;
                default: goto L_0x005d;
            }
        L_0x005d:
            goto L_0x027d
        L_0x005f:
            if (r14 >= r11) goto L_0x027d
            if (r15 >= r13) goto L_0x027d
            int r0 = r14 + 1
            byte r3 = r9[r14]
            r3 = r3 & 255(0xff, float:3.57E-43)
            switch(r3) {
                case 224: goto L_0x0116;
                case 225: goto L_0x0116;
                case 226: goto L_0x0116;
                case 227: goto L_0x0116;
                case 228: goto L_0x0116;
                case 229: goto L_0x0116;
                case 230: goto L_0x0116;
                case 231: goto L_0x0116;
                case 232: goto L_0x00e8;
                case 233: goto L_0x00e8;
                case 234: goto L_0x00e8;
                case 235: goto L_0x00e8;
                case 236: goto L_0x00e8;
                case 237: goto L_0x00e8;
                case 238: goto L_0x00e8;
                case 239: goto L_0x00e8;
                case 240: goto L_0x00bc;
                case 241: goto L_0x0081;
                default: goto L_0x006c;
            }
        L_0x006c:
            if (r0 < r11) goto L_0x0121
            int r0 = r0 + -1
            byte[] r1 = r8.fBuffer
            int r2 = r11 - r0
            java.lang.System.arraycopy(r9, r0, r1, r10, r2)
            int r1 = r11 - r0
            r8.fBufferLength = r1
            int r14 = r0 + r1
            r16 = r3
            goto L_0x027f
        L_0x0081:
            int r4 = r0 + 1
            if (r4 < r11) goto L_0x0098
            int r0 = r0 + -1
            byte[] r1 = r8.fBuffer
            int r2 = r11 - r0
            java.lang.System.arraycopy(r9, r0, r1, r10, r2)
            int r1 = r11 - r0
            r8.fBufferLength = r1
            int r14 = r0 + r1
            r16 = r3
            goto L_0x027f
        L_0x0098:
            int r4 = r0 + 1
            byte r0 = r9[r0]
            r0 = r0 & 255(0xff, float:3.57E-43)
            r3 = r0 & 224(0xe0, float:3.14E-43)
            int r3 = r3 >> 5
            r8.fCurrentWindow = r3
            int[] r5 = r8.fOffsets
            r6 = r0 & 31
            int r6 = r6 << 8
            int r7 = r4 + 1
            byte r4 = r9[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            r4 = r4 | r6
            int r4 = r4 * r2
            int r4 = r4 + r1
            r5[r3] = r4
            r8.fMode = r10
            r16 = r0
            r14 = r7
            goto L_0x027d
        L_0x00bc:
            int r4 = r11 + -1
            if (r0 < r4) goto L_0x00d3
            int r0 = r0 + -1
            byte[] r1 = r8.fBuffer
            int r2 = r11 - r0
            java.lang.System.arraycopy(r9, r0, r1, r10, r2)
            int r1 = r11 - r0
            r8.fBufferLength = r1
            int r14 = r0 + r1
            r16 = r3
            goto L_0x027f
        L_0x00d3:
            int r4 = r0 + 1
            byte r16 = r9[r0]
            int r0 = r15 + 1
            int r3 = r16 << 8
            int r14 = r4 + 1
            byte r4 = r9[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            r3 = r3 | r4
            char r3 = (char) r3
            r12[r15] = r3
            r15 = r0
            goto L_0x005f
        L_0x00e8:
            if (r0 < r11) goto L_0x00fd
            int r0 = r0 + -1
            byte[] r1 = r8.fBuffer
            int r2 = r11 - r0
            java.lang.System.arraycopy(r9, r0, r1, r10, r2)
            int r1 = r11 - r0
            r8.fBufferLength = r1
            int r14 = r0 + r1
            r16 = r3
            goto L_0x027f
        L_0x00fd:
            int r1 = r3 + -232
            r8.fCurrentWindow = r1
            int[] r2 = r8.fOffsets
            int[] r4 = sOffsetTable
            int r5 = r0 + 1
            byte r0 = r9[r0]
            r0 = r0 & 255(0xff, float:3.57E-43)
            r0 = r4[r0]
            r2[r1] = r0
            r8.fMode = r10
            r16 = r3
            r14 = r5
            goto L_0x027d
        L_0x0116:
            int r1 = r3 + -224
            r8.fCurrentWindow = r1
            r8.fMode = r10
            r14 = r0
            r16 = r3
            goto L_0x027d
        L_0x0121:
            int r4 = r15 + 1
            int r5 = r3 << 8
            int r14 = r0 + 1
            byte r0 = r9[r0]
            r0 = r0 & 255(0xff, float:3.57E-43)
            r0 = r0 | r5
            char r0 = (char) r0
            r12[r15] = r0
            r16 = r3
            r15 = r4
            goto L_0x005f
        L_0x0134:
            if (r14 >= r11) goto L_0x027d
            if (r15 >= r13) goto L_0x027d
            int r0 = r14 + 1
            byte r3 = r9[r14]
            r3 = r3 & 255(0xff, float:3.57E-43)
            switch(r3) {
                case 0: goto L_0x026e;
                case 1: goto L_0x0237;
                case 2: goto L_0x0237;
                case 3: goto L_0x0237;
                case 4: goto L_0x0237;
                case 5: goto L_0x0237;
                case 6: goto L_0x0237;
                case 7: goto L_0x0237;
                case 8: goto L_0x0237;
                case 9: goto L_0x026e;
                case 10: goto L_0x026e;
                case 11: goto L_0x01ff;
                case 12: goto L_0x0141;
                case 13: goto L_0x026e;
                case 14: goto L_0x01d0;
                case 15: goto L_0x01c8;
                case 16: goto L_0x01c2;
                case 17: goto L_0x01c2;
                case 18: goto L_0x01c2;
                case 19: goto L_0x01c2;
                case 20: goto L_0x01c2;
                case 21: goto L_0x01c2;
                case 22: goto L_0x01c2;
                case 23: goto L_0x01c2;
                case 24: goto L_0x0196;
                case 25: goto L_0x0196;
                case 26: goto L_0x0196;
                case 27: goto L_0x0196;
                case 28: goto L_0x0196;
                case 29: goto L_0x0196;
                case 30: goto L_0x0196;
                case 31: goto L_0x0196;
                case 32: goto L_0x026e;
                case 33: goto L_0x026e;
                case 34: goto L_0x026e;
                case 35: goto L_0x026e;
                case 36: goto L_0x026e;
                case 37: goto L_0x026e;
                case 38: goto L_0x026e;
                case 39: goto L_0x026e;
                case 40: goto L_0x026e;
                case 41: goto L_0x026e;
                case 42: goto L_0x026e;
                case 43: goto L_0x026e;
                case 44: goto L_0x026e;
                case 45: goto L_0x026e;
                case 46: goto L_0x026e;
                case 47: goto L_0x026e;
                case 48: goto L_0x026e;
                case 49: goto L_0x026e;
                case 50: goto L_0x026e;
                case 51: goto L_0x026e;
                case 52: goto L_0x026e;
                case 53: goto L_0x026e;
                case 54: goto L_0x026e;
                case 55: goto L_0x026e;
                case 56: goto L_0x026e;
                case 57: goto L_0x026e;
                case 58: goto L_0x026e;
                case 59: goto L_0x026e;
                case 60: goto L_0x026e;
                case 61: goto L_0x026e;
                case 62: goto L_0x026e;
                case 63: goto L_0x026e;
                case 64: goto L_0x026e;
                case 65: goto L_0x026e;
                case 66: goto L_0x026e;
                case 67: goto L_0x026e;
                case 68: goto L_0x026e;
                case 69: goto L_0x026e;
                case 70: goto L_0x026e;
                case 71: goto L_0x026e;
                case 72: goto L_0x026e;
                case 73: goto L_0x026e;
                case 74: goto L_0x026e;
                case 75: goto L_0x026e;
                case 76: goto L_0x026e;
                case 77: goto L_0x026e;
                case 78: goto L_0x026e;
                case 79: goto L_0x026e;
                case 80: goto L_0x026e;
                case 81: goto L_0x026e;
                case 82: goto L_0x026e;
                case 83: goto L_0x026e;
                case 84: goto L_0x026e;
                case 85: goto L_0x026e;
                case 86: goto L_0x026e;
                case 87: goto L_0x026e;
                case 88: goto L_0x026e;
                case 89: goto L_0x026e;
                case 90: goto L_0x026e;
                case 91: goto L_0x026e;
                case 92: goto L_0x026e;
                case 93: goto L_0x026e;
                case 94: goto L_0x026e;
                case 95: goto L_0x026e;
                case 96: goto L_0x026e;
                case 97: goto L_0x026e;
                case 98: goto L_0x026e;
                case 99: goto L_0x026e;
                case 100: goto L_0x026e;
                case 101: goto L_0x026e;
                case 102: goto L_0x026e;
                case 103: goto L_0x026e;
                case 104: goto L_0x026e;
                case 105: goto L_0x026e;
                case 106: goto L_0x026e;
                case 107: goto L_0x026e;
                case 108: goto L_0x026e;
                case 109: goto L_0x026e;
                case 110: goto L_0x026e;
                case 111: goto L_0x026e;
                case 112: goto L_0x026e;
                case 113: goto L_0x026e;
                case 114: goto L_0x026e;
                case 115: goto L_0x026e;
                case 116: goto L_0x026e;
                case 117: goto L_0x026e;
                case 118: goto L_0x026e;
                case 119: goto L_0x026e;
                case 120: goto L_0x026e;
                case 121: goto L_0x026e;
                case 122: goto L_0x026e;
                case 123: goto L_0x026e;
                case 124: goto L_0x026e;
                case 125: goto L_0x026e;
                case 126: goto L_0x026e;
                case 127: goto L_0x026e;
                case 128: goto L_0x0143;
                case 129: goto L_0x0143;
                case 130: goto L_0x0143;
                case 131: goto L_0x0143;
                case 132: goto L_0x0143;
                case 133: goto L_0x0143;
                case 134: goto L_0x0143;
                case 135: goto L_0x0143;
                case 136: goto L_0x0143;
                case 137: goto L_0x0143;
                case 138: goto L_0x0143;
                case 139: goto L_0x0143;
                case 140: goto L_0x0143;
                case 141: goto L_0x0143;
                case 142: goto L_0x0143;
                case 143: goto L_0x0143;
                case 144: goto L_0x0143;
                case 145: goto L_0x0143;
                case 146: goto L_0x0143;
                case 147: goto L_0x0143;
                case 148: goto L_0x0143;
                case 149: goto L_0x0143;
                case 150: goto L_0x0143;
                case 151: goto L_0x0143;
                case 152: goto L_0x0143;
                case 153: goto L_0x0143;
                case 154: goto L_0x0143;
                case 155: goto L_0x0143;
                case 156: goto L_0x0143;
                case 157: goto L_0x0143;
                case 158: goto L_0x0143;
                case 159: goto L_0x0143;
                case 160: goto L_0x0143;
                case 161: goto L_0x0143;
                case 162: goto L_0x0143;
                case 163: goto L_0x0143;
                case 164: goto L_0x0143;
                case 165: goto L_0x0143;
                case 166: goto L_0x0143;
                case 167: goto L_0x0143;
                case 168: goto L_0x0143;
                case 169: goto L_0x0143;
                case 170: goto L_0x0143;
                case 171: goto L_0x0143;
                case 172: goto L_0x0143;
                case 173: goto L_0x0143;
                case 174: goto L_0x0143;
                case 175: goto L_0x0143;
                case 176: goto L_0x0143;
                case 177: goto L_0x0143;
                case 178: goto L_0x0143;
                case 179: goto L_0x0143;
                case 180: goto L_0x0143;
                case 181: goto L_0x0143;
                case 182: goto L_0x0143;
                case 183: goto L_0x0143;
                case 184: goto L_0x0143;
                case 185: goto L_0x0143;
                case 186: goto L_0x0143;
                case 187: goto L_0x0143;
                case 188: goto L_0x0143;
                case 189: goto L_0x0143;
                case 190: goto L_0x0143;
                case 191: goto L_0x0143;
                case 192: goto L_0x0143;
                case 193: goto L_0x0143;
                case 194: goto L_0x0143;
                case 195: goto L_0x0143;
                case 196: goto L_0x0143;
                case 197: goto L_0x0143;
                case 198: goto L_0x0143;
                case 199: goto L_0x0143;
                case 200: goto L_0x0143;
                case 201: goto L_0x0143;
                case 202: goto L_0x0143;
                case 203: goto L_0x0143;
                case 204: goto L_0x0143;
                case 205: goto L_0x0143;
                case 206: goto L_0x0143;
                case 207: goto L_0x0143;
                case 208: goto L_0x0143;
                case 209: goto L_0x0143;
                case 210: goto L_0x0143;
                case 211: goto L_0x0143;
                case 212: goto L_0x0143;
                case 213: goto L_0x0143;
                case 214: goto L_0x0143;
                case 215: goto L_0x0143;
                case 216: goto L_0x0143;
                case 217: goto L_0x0143;
                case 218: goto L_0x0143;
                case 219: goto L_0x0143;
                case 220: goto L_0x0143;
                case 221: goto L_0x0143;
                case 222: goto L_0x0143;
                case 223: goto L_0x0143;
                case 224: goto L_0x0143;
                case 225: goto L_0x0143;
                case 226: goto L_0x0143;
                case 227: goto L_0x0143;
                case 228: goto L_0x0143;
                case 229: goto L_0x0143;
                case 230: goto L_0x0143;
                case 231: goto L_0x0143;
                case 232: goto L_0x0143;
                case 233: goto L_0x0143;
                case 234: goto L_0x0143;
                case 235: goto L_0x0143;
                case 236: goto L_0x0143;
                case 237: goto L_0x0143;
                case 238: goto L_0x0143;
                case 239: goto L_0x0143;
                case 240: goto L_0x0143;
                case 241: goto L_0x0143;
                case 242: goto L_0x0143;
                case 243: goto L_0x0143;
                case 244: goto L_0x0143;
                case 245: goto L_0x0143;
                case 246: goto L_0x0143;
                case 247: goto L_0x0143;
                case 248: goto L_0x0143;
                case 249: goto L_0x0143;
                case 250: goto L_0x0143;
                case 251: goto L_0x0143;
                case 252: goto L_0x0143;
                case 253: goto L_0x0143;
                case 254: goto L_0x0143;
                case 255: goto L_0x0143;
                default: goto L_0x0141;
            }
        L_0x0141:
            goto L_0x0278
        L_0x0143:
            int[] r4 = r8.fOffsets
            int r5 = r8.fCurrentWindow
            r6 = r4[r5]
            r7 = 65535(0xffff, float:9.1834E-41)
            if (r6 > r7) goto L_0x015d
            int r6 = r15 + 1
            r4 = r4[r5]
            int r4 = r4 + r3
            int r4 = r4 - r2
            char r4 = (char) r4
            r12[r15] = r4
            r14 = r0
            r16 = r3
            r15 = r6
            goto L_0x027b
        L_0x015d:
            int r6 = r15 + 1
            if (r6 < r13) goto L_0x0174
            int r0 = r0 + -1
            byte[] r1 = r8.fBuffer
            int r2 = r11 - r0
            java.lang.System.arraycopy(r9, r0, r1, r10, r2)
            int r1 = r11 - r0
            r8.fBufferLength = r1
            int r14 = r0 + r1
            r16 = r3
            goto L_0x027f
        L_0x0174:
            r4 = r4[r5]
            int r4 = r4 - r1
            int r5 = r15 + 1
            r6 = 55296(0xd800, float:7.7486E-41)
            int r7 = r4 >> 10
            int r7 = r7 + r6
            char r6 = (char) r7
            r12[r15] = r6
            int r6 = r5 + 1
            r7 = 56320(0xdc00, float:7.8921E-41)
            r14 = r4 & 1023(0x3ff, float:1.434E-42)
            int r14 = r14 + r7
            r7 = r3 & 127(0x7f, float:1.78E-43)
            int r14 = r14 + r7
            char r7 = (char) r14
            r12[r5] = r7
            r14 = r0
            r16 = r3
            r15 = r6
            goto L_0x027b
        L_0x0196:
            if (r0 < r11) goto L_0x01ab
            int r0 = r0 + -1
            byte[] r1 = r8.fBuffer
            int r2 = r11 - r0
            java.lang.System.arraycopy(r9, r0, r1, r10, r2)
            int r1 = r11 - r0
            r8.fBufferLength = r1
            int r14 = r0 + r1
            r16 = r3
            goto L_0x027f
        L_0x01ab:
            int r4 = r3 + -24
            r8.fCurrentWindow = r4
            int[] r5 = r8.fOffsets
            int[] r6 = sOffsetTable
            int r7 = r0 + 1
            byte r0 = r9[r0]
            r0 = r0 & 255(0xff, float:3.57E-43)
            r0 = r6[r0]
            r5[r4] = r0
            r16 = r3
            r14 = r7
            goto L_0x027b
        L_0x01c2:
            int r4 = r3 + -16
            r8.fCurrentWindow = r4
            goto L_0x0278
        L_0x01c8:
            r1 = 1
            r8.fMode = r1
            r14 = r0
            r16 = r3
            goto L_0x027d
        L_0x01d0:
            int r4 = r0 + 1
            if (r4 < r11) goto L_0x01e7
            int r0 = r0 + -1
            byte[] r1 = r8.fBuffer
            int r2 = r11 - r0
            java.lang.System.arraycopy(r9, r0, r1, r10, r2)
            int r1 = r11 - r0
            r8.fBufferLength = r1
            int r14 = r0 + r1
            r16 = r3
            goto L_0x027f
        L_0x01e7:
            int r4 = r0 + 1
            byte r0 = r9[r0]
            int r3 = r15 + 1
            int r5 = r0 << 8
            int r6 = r4 + 1
            byte r4 = r9[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            r4 = r4 | r5
            char r4 = (char) r4
            r12[r15] = r4
            r16 = r0
            r15 = r3
            r14 = r6
            goto L_0x027b
        L_0x01ff:
            int r4 = r0 + 1
            if (r4 < r11) goto L_0x0216
            int r0 = r0 + -1
            byte[] r1 = r8.fBuffer
            int r2 = r11 - r0
            java.lang.System.arraycopy(r9, r0, r1, r10, r2)
            int r1 = r11 - r0
            r8.fBufferLength = r1
            int r14 = r0 + r1
            r16 = r3
            goto L_0x027f
        L_0x0216:
            int r4 = r0 + 1
            byte r0 = r9[r0]
            r0 = r0 & 255(0xff, float:3.57E-43)
            r3 = r0 & 224(0xe0, float:3.14E-43)
            int r3 = r3 >> 5
            r8.fCurrentWindow = r3
            int[] r5 = r8.fOffsets
            r6 = r0 & 31
            int r6 = r6 << 8
            int r7 = r4 + 1
            byte r4 = r9[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            r4 = r4 | r6
            int r4 = r4 * r2
            int r4 = r4 + r1
            r5[r3] = r4
            r16 = r0
            r14 = r7
            goto L_0x027b
        L_0x0237:
            if (r0 < r11) goto L_0x024b
            int r0 = r0 + -1
            byte[] r1 = r8.fBuffer
            int r2 = r11 - r0
            java.lang.System.arraycopy(r9, r0, r1, r10, r2)
            int r1 = r11 - r0
            r8.fBufferLength = r1
            int r14 = r0 + r1
            r16 = r3
            goto L_0x027f
        L_0x024b:
            int r4 = r0 + 1
            byte r0 = r9[r0]
            r0 = r0 & 255(0xff, float:3.57E-43)
            int r5 = r15 + 1
            if (r0 < 0) goto L_0x025e
            if (r0 >= r2) goto L_0x025e
            int[] r6 = sOffsets
            int r7 = r3 + -1
            r6 = r6[r7]
            goto L_0x0265
        L_0x025e:
            int[] r6 = r8.fOffsets
            int r7 = r3 + -1
            r6 = r6[r7]
            int r6 = r6 - r2
        L_0x0265:
            int r6 = r6 + r0
            char r6 = (char) r6
            r12[r15] = r6
            r16 = r3
            r14 = r4
            r15 = r5
            goto L_0x027b
        L_0x026e:
            int r4 = r15 + 1
            char r5 = (char) r3
            r12[r15] = r5
            r14 = r0
            r16 = r3
            r15 = r4
            goto L_0x027b
        L_0x0278:
            r14 = r0
            r16 = r3
        L_0x027b:
            goto L_0x0134
        L_0x027d:
            goto L_0x0050
        L_0x027f:
            if (r22 == 0) goto L_0x0286
            r0 = r10
            int r1 = r14 - r20
            r22[r0] = r1
        L_0x0286:
            int r0 = r15 - r24
            return r0
        L_0x0289:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "charBuffer.length < 2"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.UnicodeDecompressor.decompress(byte[], int, int, int[], char[], int, int):int");
    }

    public void reset() {
        int[] iArr = this.fOffsets;
        iArr[0] = 128;
        iArr[1] = 192;
        iArr[2] = 1024;
        iArr[3] = 1536;
        iArr[4] = 2304;
        iArr[5] = 12352;
        iArr[6] = 12448;
        iArr[7] = 65280;
        this.fCurrentWindow = 0;
        this.fMode = 0;
        this.fBufferLength = 0;
    }
}
