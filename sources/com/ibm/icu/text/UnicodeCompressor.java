package com.ibm.icu.text;

public final class UnicodeCompressor implements SCSU {
    private static boolean[] sSingleTagTable = {false, true, true, true, true, true, true, true, true, false, false, true, true, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
    private static boolean[] sUnicodeTagTable = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false};
    private int fCurrentWindow = 0;
    private int[] fIndexCount = new int[256];
    private int fMode = 0;
    private int[] fOffsets = new int[8];
    private int fTimeStamp = 0;
    private int[] fTimeStamps = new int[8];

    public UnicodeCompressor() {
        reset();
    }

    public static byte[] compress(String buffer) {
        return compress(buffer.toCharArray(), 0, buffer.length());
    }

    public static byte[] compress(char[] buffer, int start, int limit) {
        UnicodeCompressor comp = new UnicodeCompressor();
        int len = Math.max(4, ((limit - start) * 3) + 1);
        byte[] temp = new byte[len];
        int byteCount = comp.compress(buffer, start, limit, (int[]) null, temp, 0, len);
        byte[] result = new byte[byteCount];
        System.arraycopy(temp, 0, result, 0, byteCount);
        return result;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x020e, code lost:
        if (isCompressible(r8) == false) goto L_0x022f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0212, code lost:
        if ((r4 + 2) < r3) goto L_0x0217;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0214, code lost:
        r5 = -1 + r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0217, code lost:
        r5 = r4 + 1;
        r2[r4] = 14;
        r4 = r5 + 1;
        r2[r5] = (byte) (r6 >>> 8);
        r2[r4] = (byte) (r6 & 255);
        r4 = r4 + 1;
        r5 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x0231, code lost:
        if ((r4 + 3) < r3) goto L_0x0237;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x0233, code lost:
        r5 = -1 + r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0237, code lost:
        r5 = r4 + 1;
        r2[r4] = 15;
        r4 = r6 >>> 8;
        r11 = r6 & 255;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0243, code lost:
        if (sUnicodeTagTable[r4] == false) goto L_0x024c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002c, code lost:
        r14 = r5 + 1;
        r6 = r20[r5];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0245, code lost:
        r2[r5] = -16;
        r5 = r5 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x024c, code lost:
        r12 = r5 + 1;
        r2[r5] = (byte) r4;
        r5 = r12 + 1;
        r2[r12] = (byte) r11;
        r0.fMode = 1;
        r12 = r11;
        r11 = r4;
        r4 = r5;
        r5 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x025f, code lost:
        r5 = findDynamicWindow(r6);
        r10 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x0265, code lost:
        if (r5 == -1) goto L_0x02cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x0269, code lost:
        if ((r14 + 1) >= r1) goto L_0x0271;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x026b, code lost:
        r9 = r20[r14 + 1];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x0271, code lost:
        r9 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x0277, code lost:
        if (inDynamicWindow(r8, r10) == false) goto L_0x02ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0030, code lost:
        if (r14 >= r1) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x027d, code lost:
        if (inDynamicWindow(r9, r10) == false) goto L_0x02ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x0281, code lost:
        if ((r4 + 1) < r3) goto L_0x0287;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x0283, code lost:
        r5 = -1 + r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x0287, code lost:
        r5 = r4 + 1;
        r2[r4] = (byte) (r10 + 16);
        r4 = r5 + 1;
        r2[r5] = (byte) ((r6 - r0.fOffsets[r10]) + 128);
        r5 = r0.fTimeStamps;
        r13 = r0.fTimeStamp + 1;
        r0.fTimeStamp = r13;
        r5[r10] = r13;
        r0.fCurrentWindow = r10;
        r5 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x02ae, code lost:
        if ((r4 + 1) < r3) goto L_0x02b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x02b0, code lost:
        r5 = -1 + r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x02b4, code lost:
        r5 = r4 + 1;
        r2[r4] = (byte) (r10 + 1);
        r4 = r5 + 1;
        r2[r5] = (byte) ((r6 - r0.fOffsets[r10]) + 128);
        r5 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0032, code lost:
        r8 = r20[r14];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x02cd, code lost:
        r5 = findStaticWindow(r6);
        r10 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x02d3, code lost:
        if (r5 == -1) goto L_0x02f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x02d9, code lost:
        if (inStaticWindow(r8, r10) != false) goto L_0x02f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x02dd, code lost:
        if ((r4 + 1) < r3) goto L_0x02e3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x02df, code lost:
        r5 = r14 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x02e3, code lost:
        r5 = r4 + 1;
        r2[r4] = (byte) (r10 + 1);
        r4 = r5 + 1;
        r2[r5] = (byte) (r6 - sOffsets[r10]);
        r5 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x02f9, code lost:
        r7 = makeIndex(r6);
        r5 = r0.fIndexCount;
        r5[r7] = r5[r7] + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0309, code lost:
        if ((r14 + 1) >= r1) goto L_0x0310;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0036, code lost:
        r8 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x030b, code lost:
        r9 = r20[r14 + 1];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x0310, code lost:
        r9 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x0314, code lost:
        if (r5[r7] > 1) goto L_0x0354;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x031a, code lost:
        if (r7 != makeIndex(r8)) goto L_0x0325;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x0320, code lost:
        if (r7 != makeIndex(r9)) goto L_0x0325;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x0327, code lost:
        if ((r4 + 3) < r3) goto L_0x032d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x0329, code lost:
        r5 = -1 + r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x032d, code lost:
        r5 = r4 + 1;
        r2[r4] = 15;
        r4 = r6 >>> 8;
        r11 = r6 & 255;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x0339, code lost:
        if (sUnicodeTagTable[r4] == false) goto L_0x0342;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x033b, code lost:
        r2[r5] = -16;
        r5 = r5 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x0342, code lost:
        r12 = r5 + 1;
        r2[r5] = (byte) r4;
        r5 = r12 + 1;
        r2[r12] = (byte) r11;
        r0.fMode = 1;
        r12 = r11;
        r11 = r4;
        r4 = r5;
        r5 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0358, code lost:
        if ((r4 + 2) < r3) goto L_0x035e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x035a, code lost:
        r5 = r14 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003c, code lost:
        if (isCompressible(r6) == false) goto L_0x018a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x035e, code lost:
        r10 = getLRDefinedWindow();
        r5 = r4 + 1;
        r2[r4] = (byte) (r10 + 24);
        r4 = r5 + 1;
        r2[r5] = (byte) r7;
        r5 = r4 + 1;
        r2[r4] = (byte) ((r6 - sOffsetTable[r7]) + 128);
        r0.fOffsets[r10] = sOffsetTable[r7];
        r0.fCurrentWindow = r10;
        r4 = r0.fTimeStamps;
        r13 = r0.fTimeStamp + 1;
        r0.fTimeStamp = r13;
        r4[r10] = r13;
        r4 = r5;
        r5 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003e, code lost:
        if (r8 == -1) goto L_0x0048;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x001b, code lost:
        continue;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x001b, code lost:
        continue;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0044, code lost:
        if (isCompressible(r8) != false) goto L_0x0048;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x001b, code lost:
        continue;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x001b, code lost:
        continue;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004a, code lost:
        if (r6 >= 128) goto L_0x0094;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004c, code lost:
        r12 = r6 & 255;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004e, code lost:
        if (r8 == -1) goto L_0x007e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0050, code lost:
        if (r8 >= 128) goto L_0x007e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0056, code lost:
        if (sSingleTagTable[r12] != false) goto L_0x007e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005a, code lost:
        if ((r4 + 1) < r3) goto L_0x0060;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005c, code lost:
        r5 = r14 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0060, code lost:
        r5 = r0.fCurrentWindow;
        r10 = r4 + 1;
        r2[r4] = (byte) (r5 + 224);
        r4 = r10 + 1;
        r2[r10] = (byte) r12;
        r10 = r0.fTimeStamps;
        r13 = r0.fTimeStamp + r15;
        r0.fTimeStamp = r13;
        r10[r5] = r13;
        r0.fMode = 0;
        r10 = r5;
        r5 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0080, code lost:
        if ((r4 + 1) < r3) goto L_0x0086;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0082, code lost:
        r5 = r14 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0086, code lost:
        r5 = r4 + 1;
        r2[r4] = 0;
        r4 = r5 + 1;
        r2[r5] = (byte) r12;
        r5 = r14;
        r15 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0094, code lost:
        r5 = findDynamicWindow(r6);
        r10 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0099, code lost:
        if (r5 == -1) goto L_0x00f7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x009f, code lost:
        if (inDynamicWindow(r8, r10) == false) goto L_0x00d0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00a3, code lost:
        if ((r4 + 1) < r3) goto L_0x00a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00a5, code lost:
        r5 = r14 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a9, code lost:
        r5 = r4 + 1;
        r2[r4] = (byte) (r10 + 224);
        r4 = r5 + 1;
        r2[r5] = (byte) ((r6 - r0.fOffsets[r10]) + 128);
        r5 = r0.fTimeStamps;
        r13 = r0.fTimeStamp + 1;
        r0.fTimeStamp = r13;
        r5[r10] = r13;
        r0.fCurrentWindow = r10;
        r0.fMode = 0;
        r5 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00d2, code lost:
        if ((r4 + 2) < r3) goto L_0x00d8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00d4, code lost:
        r5 = r14 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00d8, code lost:
        r11 = r6 >>> 8;
        r12 = r6 & 255;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00e0, code lost:
        if (sUnicodeTagTable[r11] == false) goto L_0x00e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00e2, code lost:
        r2[r4] = -16;
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00e9, code lost:
        r5 = r4 + 1;
        r2[r4] = (byte) r11;
        r4 = r5 + 1;
        r2[r5] = (byte) r12;
        r5 = r14;
        r15 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00f7, code lost:
        r7 = makeIndex(r6);
        r5 = r0.fIndexCount;
        r5[r7] = r5[r7] + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0107, code lost:
        if ((r14 + 1) >= r1) goto L_0x010e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0109, code lost:
        r9 = r20[r14 + 1];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x010e, code lost:
        r9 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0112, code lost:
        if (r5[r7] > 1) goto L_0x0148;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0118, code lost:
        if (r7 != makeIndex(r8)) goto L_0x0121;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x011e, code lost:
        if (r7 != makeIndex(r9)) goto L_0x0121;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0123, code lost:
        if ((r4 + 2) < r3) goto L_0x0129;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0125, code lost:
        r5 = r14 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0129, code lost:
        r11 = r6 >>> 8;
        r12 = r6 & 255;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0131, code lost:
        if (sUnicodeTagTable[r11] == false) goto L_0x013a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0133, code lost:
        r2[r4] = -16;
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x013a, code lost:
        r5 = r4 + 1;
        r2[r4] = (byte) r11;
        r4 = r5 + 1;
        r2[r5] = (byte) r12;
        r5 = r14;
        r15 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x014a, code lost:
        if ((r4 + 2) < r3) goto L_0x0150;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x014c, code lost:
        r5 = r14 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0150, code lost:
        r5 = getLRDefinedWindow();
        r10 = r4 + 1;
        r2[r4] = (byte) (r5 + com.ibm.icu.text.SCSU.UDEFINE0);
        r4 = r10 + 1;
        r2[r10] = (byte) r7;
        r10 = r4 + 1;
        r2[r4] = (byte) ((r6 - sOffsetTable[r7]) + 128);
        r0.fOffsets[r5] = sOffsetTable[r7];
        r0.fCurrentWindow = r5;
        r4 = r0.fTimeStamps;
        r13 = r0.fTimeStamp + 1;
        r0.fTimeStamp = r13;
        r4[r5] = r13;
        r0.fMode = 0;
        r4 = r10;
        r10 = r5;
        r5 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x018c, code lost:
        if ((r4 + 2) < r3) goto L_0x0192;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x018e, code lost:
        r5 = r14 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0192, code lost:
        r11 = r6 >>> 8;
        r12 = r6 & 255;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x019a, code lost:
        if (sUnicodeTagTable[r11] == false) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x019c, code lost:
        r2[r4] = -16;
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01a3, code lost:
        r5 = r4 + 1;
        r2[r4] = (byte) r11;
        r4 = r5 + 1;
        r2[r5] = (byte) r12;
        r5 = r14;
        r15 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01b1, code lost:
        if (r5 >= r1) goto L_0x001b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01b3, code lost:
        if (r4 >= r3) goto L_0x001b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01b5, code lost:
        r14 = r5 + 1;
        r6 = r20[r5];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01b9, code lost:
        if (r14 >= r1) goto L_0x01bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01bb, code lost:
        r8 = r20[r14];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01bf, code lost:
        r8 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x01c3, code lost:
        if (r6 >= 128) goto L_0x01e3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x01c5, code lost:
        r12 = r6 & 255;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01cb, code lost:
        if (sSingleTagTable[r12] == false) goto L_0x01db;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x01cf, code lost:
        if ((r4 + 1) < r3) goto L_0x01d5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x01d1, code lost:
        r5 = r14 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0028, code lost:
        if (r5 >= r1) goto L_0x001b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x01d5, code lost:
        r2[r4] = 1;
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01db, code lost:
        r2[r4] = (byte) r12;
        r4 = r4 + 1;
        r5 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x01e9, code lost:
        if (inDynamicWindow(r6, r0.fCurrentWindow) == false) goto L_0x01ff;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01eb, code lost:
        r2[r4] = (byte) ((r6 - r0.fOffsets[r0.fCurrentWindow]) + 128);
        r4 = r4 + 1;
        r5 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0205, code lost:
        if (isCompressible(r6) != 0) goto L_0x025f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0208, code lost:
        if (r8 == -1) goto L_0x022f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002a, code lost:
        if (r4 >= r3) goto L_0x001b;
     */
    /* JADX WARNING: Incorrect type for immutable var: ssa=char, code=int, for r9v3, types: [char] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=char, code=int, for r9v9, types: [char] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int compress(char[] r20, int r21, int r22, int[] r23, byte[] r24, int r25, int r26) {
        /*
            r19 = this;
            r0 = r19
            r1 = r22
            r2 = r24
            r3 = r26
            r4 = r25
            r5 = r21
            r6 = -1
            r7 = -1
            r8 = -1
            r9 = -1
            r10 = 0
            r11 = 0
            r12 = 0
            int r13 = r2.length
            r14 = 4
            if (r13 < r14) goto L_0x03a4
            int r13 = r3 - r25
            if (r13 < r14) goto L_0x03a4
        L_0x001b:
            if (r5 >= r1) goto L_0x039a
            if (r4 >= r3) goto L_0x039a
            int r14 = r0.fMode
            r13 = -1
            r15 = 1
            switch(r14) {
                case 0: goto L_0x01b1;
                case 1: goto L_0x0028;
                default: goto L_0x0026;
            }
        L_0x0026:
            goto L_0x0398
        L_0x0028:
            if (r5 >= r1) goto L_0x0398
            if (r4 >= r3) goto L_0x0398
            int r14 = r5 + 1
            char r6 = r20[r5]
            if (r14 >= r1) goto L_0x0036
            char r5 = r20[r14]
            r8 = r5
            goto L_0x0038
        L_0x0036:
            r5 = -1
            r8 = r5
        L_0x0038:
            boolean r5 = isCompressible(r6)
            if (r5 == 0) goto L_0x018a
            if (r8 == r13) goto L_0x0048
            boolean r5 = isCompressible(r8)
            if (r5 != 0) goto L_0x0048
            goto L_0x018a
        L_0x0048:
            r5 = 128(0x80, float:1.794E-43)
            if (r6 >= r5) goto L_0x0094
            r12 = r6 & 255(0xff, float:3.57E-43)
            if (r8 == r13) goto L_0x007e
            if (r8 >= r5) goto L_0x007e
            boolean[] r5 = sSingleTagTable
            boolean r5 = r5[r12]
            if (r5 != 0) goto L_0x007e
            int r5 = r4 + 1
            if (r5 < r3) goto L_0x0060
            int r5 = r14 + -1
            goto L_0x039a
        L_0x0060:
            int r5 = r0.fCurrentWindow
            int r10 = r4 + 1
            int r13 = r5 + 224
            byte r13 = (byte) r13
            r2[r4] = r13
            int r4 = r10 + 1
            byte r13 = (byte) r12
            r2[r10] = r13
            int[] r10 = r0.fTimeStamps
            int r13 = r0.fTimeStamp
            int r13 = r13 + r15
            r0.fTimeStamp = r13
            r10[r5] = r13
            r10 = 0
            r0.fMode = r10
            r10 = r5
            r5 = r14
            goto L_0x0398
        L_0x007e:
            int r5 = r4 + 1
            if (r5 < r3) goto L_0x0086
            int r5 = r14 + -1
            goto L_0x039a
        L_0x0086:
            int r5 = r4 + 1
            r16 = 0
            r2[r4] = r16
            int r4 = r5 + 1
            byte r15 = (byte) r12
            r2[r5] = r15
            r5 = r14
            r15 = 1
            goto L_0x0028
        L_0x0094:
            int r5 = r0.findDynamicWindow(r6)
            r10 = r5
            if (r5 == r13) goto L_0x00f7
            boolean r5 = r0.inDynamicWindow(r8, r10)
            if (r5 == 0) goto L_0x00d0
            int r5 = r4 + 1
            if (r5 < r3) goto L_0x00a9
            int r5 = r14 + -1
            goto L_0x039a
        L_0x00a9:
            int r5 = r4 + 1
            int r13 = r10 + 224
            byte r13 = (byte) r13
            r2[r4] = r13
            int r4 = r5 + 1
            int[] r13 = r0.fOffsets
            r13 = r13[r10]
            int r13 = r6 - r13
            r15 = 128(0x80, float:1.794E-43)
            int r13 = r13 + r15
            byte r13 = (byte) r13
            r2[r5] = r13
            int[] r5 = r0.fTimeStamps
            int r13 = r0.fTimeStamp
            r15 = 1
            int r13 = r13 + r15
            r0.fTimeStamp = r13
            r5[r10] = r13
            r0.fCurrentWindow = r10
            r5 = 0
            r0.fMode = r5
            r5 = r14
            goto L_0x0398
        L_0x00d0:
            int r5 = r4 + 2
            if (r5 < r3) goto L_0x00d8
            int r5 = r14 + -1
            goto L_0x039a
        L_0x00d8:
            int r11 = r6 >>> 8
            r12 = r6 & 255(0xff, float:3.57E-43)
            boolean[] r5 = sUnicodeTagTable
            boolean r5 = r5[r11]
            if (r5 == 0) goto L_0x00e9
            int r5 = r4 + 1
            r15 = -16
            r2[r4] = r15
            r4 = r5
        L_0x00e9:
            int r5 = r4 + 1
            byte r15 = (byte) r11
            r2[r4] = r15
            int r4 = r5 + 1
            byte r15 = (byte) r12
            r2[r5] = r15
            r5 = r14
            r15 = 1
            goto L_0x0028
        L_0x00f7:
            int r7 = makeIndex(r6)
            int[] r5 = r0.fIndexCount
            r15 = r5[r7]
            r18 = 1
            int r15 = r15 + 1
            r5[r7] = r15
            int r15 = r14 + 1
            if (r15 >= r1) goto L_0x010e
            int r15 = r14 + 1
            char r9 = r20[r15]
            goto L_0x010f
        L_0x010e:
            r9 = -1
        L_0x010f:
            r5 = r5[r7]
            r15 = 1
            if (r5 > r15) goto L_0x0148
            int r5 = makeIndex(r8)
            if (r7 != r5) goto L_0x0121
            int r5 = makeIndex(r9)
            if (r7 != r5) goto L_0x0121
            goto L_0x0148
        L_0x0121:
            int r5 = r4 + 2
            if (r5 < r3) goto L_0x0129
            int r5 = r14 + -1
            goto L_0x039a
        L_0x0129:
            int r11 = r6 >>> 8
            r12 = r6 & 255(0xff, float:3.57E-43)
            boolean[] r5 = sUnicodeTagTable
            boolean r5 = r5[r11]
            if (r5 == 0) goto L_0x013a
            int r5 = r4 + 1
            r15 = -16
            r2[r4] = r15
            r4 = r5
        L_0x013a:
            int r5 = r4 + 1
            byte r15 = (byte) r11
            r2[r4] = r15
            int r4 = r5 + 1
            byte r15 = (byte) r12
            r2[r5] = r15
            r5 = r14
            r15 = 1
            goto L_0x0028
        L_0x0148:
            int r5 = r4 + 2
            if (r5 < r3) goto L_0x0150
            int r5 = r14 + -1
            goto L_0x039a
        L_0x0150:
            int r5 = r19.getLRDefinedWindow()
            int r10 = r4 + 1
            int r13 = r5 + 232
            byte r13 = (byte) r13
            r2[r4] = r13
            int r4 = r10 + 1
            byte r13 = (byte) r7
            r2[r10] = r13
            int r10 = r4 + 1
            int[] r13 = sOffsetTable
            r13 = r13[r7]
            int r13 = r6 - r13
            r15 = 128(0x80, float:1.794E-43)
            int r13 = r13 + r15
            byte r13 = (byte) r13
            r2[r4] = r13
            int[] r4 = r0.fOffsets
            int[] r13 = sOffsetTable
            r13 = r13[r7]
            r4[r5] = r13
            r0.fCurrentWindow = r5
            int[] r4 = r0.fTimeStamps
            int r13 = r0.fTimeStamp
            r15 = 1
            int r13 = r13 + r15
            r0.fTimeStamp = r13
            r4[r5] = r13
            r4 = 0
            r0.fMode = r4
            r4 = r10
            r10 = r5
            r5 = r14
            goto L_0x0398
        L_0x018a:
            int r5 = r4 + 2
            if (r5 < r3) goto L_0x0192
            int r5 = r14 + -1
            goto L_0x039a
        L_0x0192:
            int r11 = r6 >>> 8
            r12 = r6 & 255(0xff, float:3.57E-43)
            boolean[] r5 = sUnicodeTagTable
            boolean r5 = r5[r11]
            if (r5 == 0) goto L_0x01a3
            int r5 = r4 + 1
            r15 = -16
            r2[r4] = r15
            r4 = r5
        L_0x01a3:
            int r5 = r4 + 1
            byte r15 = (byte) r11
            r2[r4] = r15
            int r4 = r5 + 1
            byte r15 = (byte) r12
            r2[r5] = r15
            r5 = r14
            r15 = 1
            goto L_0x0028
        L_0x01b1:
            if (r5 >= r1) goto L_0x0398
            if (r4 >= r3) goto L_0x0398
            int r14 = r5 + 1
            char r6 = r20[r5]
            if (r14 >= r1) goto L_0x01bf
            char r5 = r20[r14]
            r8 = r5
            goto L_0x01c1
        L_0x01bf:
            r5 = -1
            r8 = r5
        L_0x01c1:
            r5 = 128(0x80, float:1.794E-43)
            if (r6 >= r5) goto L_0x01e3
            r12 = r6 & 255(0xff, float:3.57E-43)
            boolean[] r5 = sSingleTagTable
            boolean r5 = r5[r12]
            if (r5 == 0) goto L_0x01db
            int r5 = r4 + 1
            if (r5 < r3) goto L_0x01d5
            int r5 = r14 + -1
            goto L_0x039a
        L_0x01d5:
            int r5 = r4 + 1
            r15 = 1
            r2[r4] = r15
            r4 = r5
        L_0x01db:
            int r5 = r4 + 1
            byte r15 = (byte) r12
            r2[r4] = r15
            r4 = r5
            r5 = r14
            goto L_0x01b1
        L_0x01e3:
            int r5 = r0.fCurrentWindow
            boolean r5 = r0.inDynamicWindow(r6, r5)
            if (r5 == 0) goto L_0x01ff
            int r5 = r4 + 1
            int[] r15 = r0.fOffsets
            int r13 = r0.fCurrentWindow
            r13 = r15[r13]
            int r13 = r6 - r13
            r15 = 128(0x80, float:1.794E-43)
            int r13 = r13 + r15
            byte r13 = (byte) r13
            r2[r4] = r13
            r4 = r5
            r5 = r14
            r13 = -1
            goto L_0x01b1
        L_0x01ff:
            boolean r5 = isCompressible(r6)
            r13 = 15
            if (r5 != 0) goto L_0x025f
            r5 = -1
            if (r8 == r5) goto L_0x022f
            boolean r15 = isCompressible(r8)
            if (r15 == 0) goto L_0x022f
            int r13 = r4 + 2
            if (r13 < r3) goto L_0x0217
            int r5 = r5 + r14
            goto L_0x039a
        L_0x0217:
            int r5 = r4 + 1
            r13 = 14
            r2[r4] = r13
            int r4 = r5 + 1
            int r13 = r6 >>> 8
            byte r13 = (byte) r13
            r2[r5] = r13
            int r5 = r4 + 1
            r13 = r6 & 255(0xff, float:3.57E-43)
            byte r13 = (byte) r13
            r2[r4] = r13
            r4 = r5
            r5 = r14
            r13 = -1
            goto L_0x01b1
        L_0x022f:
            int r5 = r4 + 3
            if (r5 < r3) goto L_0x0237
            r5 = -1
            int r5 = r5 + r14
            goto L_0x039a
        L_0x0237:
            int r5 = r4 + 1
            r2[r4] = r13
            int r4 = r6 >>> 8
            r11 = r6 & 255(0xff, float:3.57E-43)
            boolean[] r12 = sUnicodeTagTable
            boolean r12 = r12[r4]
            if (r12 == 0) goto L_0x024c
            int r12 = r5 + 1
            r13 = -16
            r2[r5] = r13
            r5 = r12
        L_0x024c:
            int r12 = r5 + 1
            byte r13 = (byte) r4
            r2[r5] = r13
            int r5 = r12 + 1
            byte r13 = (byte) r11
            r2[r12] = r13
            r12 = 1
            r0.fMode = r12
            r12 = r11
            r11 = r4
            r4 = r5
            r5 = r14
            goto L_0x0398
        L_0x025f:
            int r5 = r0.findDynamicWindow(r6)
            r10 = r5
            r15 = -1
            if (r5 == r15) goto L_0x02cd
            int r5 = r14 + 1
            if (r5 >= r1) goto L_0x0271
            int r5 = r14 + 1
            char r5 = r20[r5]
            r9 = r5
            goto L_0x0273
        L_0x0271:
            r5 = -1
            r9 = r5
        L_0x0273:
            boolean r5 = r0.inDynamicWindow(r8, r10)
            if (r5 == 0) goto L_0x02ac
            boolean r5 = r0.inDynamicWindow(r9, r10)
            if (r5 == 0) goto L_0x02ac
            int r5 = r4 + 1
            if (r5 < r3) goto L_0x0287
            r5 = -1
            int r5 = r5 + r14
            goto L_0x039a
        L_0x0287:
            int r5 = r4 + 1
            int r13 = r10 + 16
            byte r13 = (byte) r13
            r2[r4] = r13
            int r4 = r5 + 1
            int[] r13 = r0.fOffsets
            r13 = r13[r10]
            int r13 = r6 - r13
            r15 = 128(0x80, float:1.794E-43)
            int r13 = r13 + r15
            byte r13 = (byte) r13
            r2[r5] = r13
            int[] r5 = r0.fTimeStamps
            int r13 = r0.fTimeStamp
            r15 = 1
            int r13 = r13 + r15
            r0.fTimeStamp = r13
            r5[r10] = r13
            r0.fCurrentWindow = r10
            r5 = r14
            r13 = -1
            goto L_0x01b1
        L_0x02ac:
            int r5 = r4 + 1
            if (r5 < r3) goto L_0x02b4
            r5 = -1
            int r5 = r5 + r14
            goto L_0x039a
        L_0x02b4:
            int r5 = r4 + 1
            int r13 = r10 + 1
            byte r13 = (byte) r13
            r2[r4] = r13
            int r4 = r5 + 1
            int[] r13 = r0.fOffsets
            r13 = r13[r10]
            int r13 = r6 - r13
            r15 = 128(0x80, float:1.794E-43)
            int r13 = r13 + r15
            byte r13 = (byte) r13
            r2[r5] = r13
            r5 = r14
            r13 = -1
            goto L_0x01b1
        L_0x02cd:
            int r5 = findStaticWindow(r6)
            r10 = r5
            r15 = -1
            if (r5 == r15) goto L_0x02f9
            boolean r5 = inStaticWindow(r8, r10)
            if (r5 != 0) goto L_0x02f9
            int r5 = r4 + 1
            if (r5 < r3) goto L_0x02e3
            int r5 = r14 + -1
            goto L_0x039a
        L_0x02e3:
            int r5 = r4 + 1
            int r13 = r10 + 1
            byte r13 = (byte) r13
            r2[r4] = r13
            int r4 = r5 + 1
            int[] r13 = sOffsets
            r13 = r13[r10]
            int r13 = r6 - r13
            byte r13 = (byte) r13
            r2[r5] = r13
            r5 = r14
            r13 = -1
            goto L_0x01b1
        L_0x02f9:
            int r7 = makeIndex(r6)
            int[] r5 = r0.fIndexCount
            r15 = r5[r7]
            r18 = 1
            int r15 = r15 + 1
            r5[r7] = r15
            int r15 = r14 + 1
            if (r15 >= r1) goto L_0x0310
            int r15 = r14 + 1
            char r9 = r20[r15]
            goto L_0x0311
        L_0x0310:
            r9 = -1
        L_0x0311:
            r5 = r5[r7]
            r15 = 1
            if (r5 > r15) goto L_0x0354
            int r5 = makeIndex(r8)
            if (r7 != r5) goto L_0x0325
            int r5 = makeIndex(r9)
            if (r7 != r5) goto L_0x0325
            r13 = -16
            goto L_0x0356
        L_0x0325:
            int r5 = r4 + 3
            if (r5 < r3) goto L_0x032d
            r5 = -1
            int r5 = r5 + r14
            goto L_0x039a
        L_0x032d:
            int r5 = r4 + 1
            r2[r4] = r13
            int r4 = r6 >>> 8
            r11 = r6 & 255(0xff, float:3.57E-43)
            boolean[] r12 = sUnicodeTagTable
            boolean r12 = r12[r4]
            if (r12 == 0) goto L_0x0342
            int r12 = r5 + 1
            r13 = -16
            r2[r5] = r13
            r5 = r12
        L_0x0342:
            int r12 = r5 + 1
            byte r13 = (byte) r4
            r2[r5] = r13
            int r5 = r12 + 1
            byte r13 = (byte) r11
            r2[r12] = r13
            r12 = 1
            r0.fMode = r12
            r12 = r11
            r11 = r4
            r4 = r5
            r5 = r14
            goto L_0x0398
        L_0x0354:
            r13 = -16
        L_0x0356:
            int r5 = r4 + 2
            if (r5 < r3) goto L_0x035e
            r15 = -1
            int r5 = r14 + -1
            goto L_0x039a
        L_0x035e:
            r15 = -1
            int r10 = r19.getLRDefinedWindow()
            int r5 = r4 + 1
            int r13 = r10 + 24
            byte r13 = (byte) r13
            r2[r4] = r13
            int r4 = r5 + 1
            byte r13 = (byte) r7
            r2[r5] = r13
            int r5 = r4 + 1
            int[] r13 = sOffsetTable
            r13 = r13[r7]
            int r13 = r6 - r13
            r15 = 128(0x80, float:1.794E-43)
            int r13 = r13 + r15
            byte r13 = (byte) r13
            r2[r4] = r13
            int[] r4 = r0.fOffsets
            int[] r13 = sOffsetTable
            r13 = r13[r7]
            r4[r10] = r13
            r0.fCurrentWindow = r10
            int[] r4 = r0.fTimeStamps
            int r13 = r0.fTimeStamp
            r17 = 1
            int r13 = r13 + 1
            r0.fTimeStamp = r13
            r4[r10] = r13
            r4 = r5
            r5 = r14
            r13 = -1
            goto L_0x01b1
        L_0x0398:
            goto L_0x001b
        L_0x039a:
            if (r23 == 0) goto L_0x03a1
            int r13 = r5 - r21
            r14 = 0
            r23[r14] = r13
        L_0x03a1:
            int r13 = r4 - r25
            return r13
        L_0x03a4:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "byteBuffer.length < 4"
            r13.<init>(r14)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.UnicodeCompressor.compress(char[], int, int, int[], byte[], int, int):int");
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
        for (int i = 0; i < 8; i++) {
            this.fTimeStamps[i] = 0;
        }
        for (int i2 = 0; i2 <= 255; i2++) {
            this.fIndexCount[i2] = 0;
        }
        this.fTimeStamp = 0;
        this.fCurrentWindow = 0;
        this.fMode = 0;
    }

    private static int makeIndex(int c) {
        if (c >= 192 && c < 320) {
            return SCSU.LATININDEX;
        }
        if (c >= 592 && c < 720) {
            return 250;
        }
        if (c >= 880 && c < 1008) {
            return SCSU.GREEKINDEX;
        }
        if (c >= 1328 && c < 1424) {
            return SCSU.ARMENIANINDEX;
        }
        if (c >= 12352 && c < 12448) {
            return SCSU.HIRAGANAINDEX;
        }
        if (c >= 12448 && c < 12576) {
            return SCSU.KATAKANAINDEX;
        }
        if (c >= 65376 && c < 65439) {
            return 255;
        }
        if (c >= 128 && c < 13312) {
            return (c / 128) & 255;
        }
        if (c < 57344 || c > 65535) {
            return 0;
        }
        return ((c - 44032) / 128) & 255;
    }

    private boolean inDynamicWindow(int c, int whichWindow) {
        int[] iArr = this.fOffsets;
        return c >= iArr[whichWindow] && c < iArr[whichWindow] + 128;
    }

    private static boolean inStaticWindow(int c, int whichWindow) {
        return c >= sOffsets[whichWindow] && c < sOffsets[whichWindow] + 128;
    }

    private static boolean isCompressible(int c) {
        return c < 13312 || c >= 57344;
    }

    private int findDynamicWindow(int c) {
        for (int i = 7; i >= 0; i--) {
            if (inDynamicWindow(c, i)) {
                int[] iArr = this.fTimeStamps;
                iArr[i] = iArr[i] + 1;
                return i;
            }
        }
        return -1;
    }

    private static int findStaticWindow(int c) {
        for (int i = 7; i >= 0; i--) {
            if (inStaticWindow(c, i)) {
                return i;
            }
        }
        return -1;
    }

    private int getLRDefinedWindow() {
        int leastRU = Integer.MAX_VALUE;
        int whichWindow = -1;
        for (int i = 7; i >= 0; i--) {
            int[] iArr = this.fTimeStamps;
            if (iArr[i] < leastRU) {
                leastRU = iArr[i];
                whichWindow = i;
            }
        }
        return whichWindow;
    }
}
