package com.ibm.icu.text;

/* loaded from: classes.dex */
public final class UnicodeCompressor implements SCSU {
    private static boolean[] sSingleTagTable = {false, true, true, true, true, true, true, true, true, false, false, true, true, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
    private static boolean[] sUnicodeTagTable = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false};
    private int fCurrentWindow = 0;
    private int[] fOffsets = new int[8];
    private int fMode = 0;
    private int[] fIndexCount = new int[256];
    private int[] fTimeStamps = new int[8];
    private int fTimeStamp = 0;

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
        int byteCount = comp.compress(buffer, start, limit, null, temp, 0, len);
        byte[] result = new byte[byteCount];
        System.arraycopy(temp, 0, result, 0, byteCount);
        return result;
    }

    /* JADX WARN: Code restructure failed: missing block: B:72:0x014a, code lost:
        if ((r4 + 2) < r26) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x014c, code lost:
        r5 = r14 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0150, code lost:
        r5 = getLRDefinedWindow();
        r10 = r4 + 1;
        r24[r4] = (byte) (r5 + com.ibm.icu.text.SCSU.UDEFINE0);
        r4 = r10 + 1;
        r24[r10] = (byte) r7;
        r10 = r4 + 1;
        r24[r4] = (byte) ((r6 - com.ibm.icu.text.UnicodeCompressor.sOffsetTable[r7]) + 128);
        r19.fOffsets[r5] = com.ibm.icu.text.UnicodeCompressor.sOffsetTable[r7];
        r19.fCurrentWindow = r5;
        r4 = r19.fTimeStamps;
        r13 = r19.fTimeStamp + 1;
        r19.fTimeStamp = r13;
        r4[r5] = r13;
        r19.fMode = 0;
        r4 = r10;
        r5 = r14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int compress(char[] charBuffer, int charBufferStart, int charBufferLimit, int[] charsRead, byte[] byteBuffer, int byteBufferStart, int byteBufferLimit) {
        int nextUC;
        int forwardUC;
        char c;
        int nextUC2;
        char c2;
        int bytePos = byteBufferStart;
        int ucPos = charBufferStart;
        if (byteBuffer.length < 4 || byteBufferLimit - byteBufferStart < 4) {
            throw new IllegalArgumentException("byteBuffer.length < 4");
        }
        while (true) {
            if (ucPos < charBufferLimit && bytePos < byteBufferLimit) {
                int i = 1;
                switch (this.fMode) {
                    case 0:
                        while (ucPos < charBufferLimit && bytePos < byteBufferLimit) {
                            int ucPos2 = ucPos + 1;
                            char c3 = charBuffer[ucPos];
                            if (ucPos2 < charBufferLimit) {
                                nextUC = charBuffer[ucPos2];
                            } else {
                                nextUC = -1;
                            }
                            if (c3 < '\u0080') {
                                int loByte = c3 & '\u00ff';
                                if (sSingleTagTable[loByte]) {
                                    if (bytePos + 1 >= byteBufferLimit) {
                                        ucPos = ucPos2 - 1;
                                        break;
                                    } else {
                                        int ucPos3 = bytePos + 1;
                                        byteBuffer[bytePos] = 1;
                                        bytePos = ucPos3;
                                    }
                                }
                                byteBuffer[bytePos] = (byte) loByte;
                                bytePos++;
                                ucPos = ucPos2;
                            } else {
                                int bytePos2 = this.fCurrentWindow;
                                if (inDynamicWindow(c3, bytePos2)) {
                                    byteBuffer[bytePos] = (byte) ((c3 - this.fOffsets[this.fCurrentWindow]) + 128);
                                    bytePos++;
                                    ucPos = ucPos2;
                                } else if (!isCompressible(c3)) {
                                    if (nextUC != -1 && isCompressible(nextUC)) {
                                        if (bytePos + 2 >= byteBufferLimit) {
                                            ucPos = (-1) + ucPos2;
                                            break;
                                        } else {
                                            int ucPos4 = bytePos + 1;
                                            byteBuffer[bytePos] = 14;
                                            int bytePos3 = ucPos4 + 1;
                                            byteBuffer[ucPos4] = (byte) (c3 >>> '\b');
                                            byteBuffer[bytePos3] = (byte) (c3 & '\u00ff');
                                            bytePos = bytePos3 + 1;
                                            ucPos = ucPos2;
                                        }
                                    } else if (bytePos + 3 >= byteBufferLimit) {
                                        ucPos = (-1) + ucPos2;
                                        break;
                                    } else {
                                        int bytePos4 = bytePos + 1;
                                        byteBuffer[bytePos] = 15;
                                        int hiByte = c3 >>> '\b';
                                        int hiByte2 = c3 & '\u00ff';
                                        if (sUnicodeTagTable[hiByte]) {
                                            byteBuffer[bytePos4] = -16;
                                            bytePos4++;
                                        }
                                        int bytePos5 = bytePos4 + 1;
                                        byteBuffer[bytePos4] = (byte) hiByte;
                                        byteBuffer[bytePos5] = (byte) hiByte2;
                                        this.fMode = 1;
                                        bytePos = bytePos5 + 1;
                                        ucPos = ucPos2;
                                        break;
                                    }
                                } else {
                                    int whichWindow = findDynamicWindow(c3);
                                    if (whichWindow != -1) {
                                        if (ucPos2 + 1 < charBufferLimit) {
                                            forwardUC = charBuffer[ucPos2 + 1];
                                        } else {
                                            forwardUC = -1;
                                        }
                                        if (inDynamicWindow(nextUC, whichWindow) && inDynamicWindow(forwardUC, whichWindow)) {
                                            if (bytePos + 1 >= byteBufferLimit) {
                                                ucPos = (-1) + ucPos2;
                                                break;
                                            } else {
                                                int ucPos5 = bytePos + 1;
                                                byteBuffer[bytePos] = (byte) (whichWindow + 16);
                                                bytePos = ucPos5 + 1;
                                                byteBuffer[ucPos5] = (byte) ((c3 - this.fOffsets[whichWindow]) + 128);
                                                int[] iArr = this.fTimeStamps;
                                                int i2 = this.fTimeStamp + 1;
                                                this.fTimeStamp = i2;
                                                iArr[whichWindow] = i2;
                                                this.fCurrentWindow = whichWindow;
                                                ucPos = ucPos2;
                                            }
                                        } else if (bytePos + 1 >= byteBufferLimit) {
                                            ucPos = (-1) + ucPos2;
                                            break;
                                        } else {
                                            int ucPos6 = bytePos + 1;
                                            byteBuffer[bytePos] = (byte) (whichWindow + 1);
                                            bytePos = ucPos6 + 1;
                                            byteBuffer[ucPos6] = (byte) ((c3 - this.fOffsets[whichWindow]) + 128);
                                            ucPos = ucPos2;
                                        }
                                    } else {
                                        int whichWindow2 = findStaticWindow(c3);
                                        if (whichWindow2 != -1 && !inStaticWindow(nextUC, whichWindow2)) {
                                            if (bytePos + 1 >= byteBufferLimit) {
                                                ucPos = ucPos2 - 1;
                                                break;
                                            } else {
                                                int ucPos7 = bytePos + 1;
                                                byteBuffer[bytePos] = (byte) (whichWindow2 + 1);
                                                bytePos = ucPos7 + 1;
                                                byteBuffer[ucPos7] = (byte) (c3 - sOffsets[whichWindow2]);
                                                ucPos = ucPos2;
                                            }
                                        } else {
                                            int curIndex = makeIndex(c3);
                                            int[] iArr2 = this.fIndexCount;
                                            iArr2[curIndex] = iArr2[curIndex] + 1;
                                            if (ucPos2 + 1 < charBufferLimit) {
                                                c = charBuffer[ucPos2 + 1];
                                            } else {
                                                c = '\uffff';
                                            }
                                            if (iArr2[curIndex] <= 1 && (curIndex != makeIndex(nextUC) || curIndex != makeIndex(c))) {
                                                if (bytePos + 3 >= byteBufferLimit) {
                                                    ucPos = (-1) + ucPos2;
                                                    break;
                                                } else {
                                                    int bytePos6 = bytePos + 1;
                                                    byteBuffer[bytePos] = 15;
                                                    int hiByte3 = c3 >>> '\b';
                                                    int hiByte4 = c3 & '\u00ff';
                                                    if (sUnicodeTagTable[hiByte3]) {
                                                        byteBuffer[bytePos6] = -16;
                                                        bytePos6++;
                                                    }
                                                    int bytePos7 = bytePos6 + 1;
                                                    byteBuffer[bytePos6] = (byte) hiByte3;
                                                    byteBuffer[bytePos7] = (byte) hiByte4;
                                                    this.fMode = 1;
                                                    bytePos = bytePos7 + 1;
                                                    ucPos = ucPos2;
                                                    break;
                                                }
                                            }
                                            if (bytePos + 2 >= byteBufferLimit) {
                                                ucPos = ucPos2 - 1;
                                                break;
                                            } else {
                                                int whichWindow3 = getLRDefinedWindow();
                                                int bytePos8 = bytePos + 1;
                                                byteBuffer[bytePos] = (byte) (whichWindow3 + 24);
                                                int bytePos9 = bytePos8 + 1;
                                                byteBuffer[bytePos8] = (byte) curIndex;
                                                int bytePos10 = bytePos9 + 1;
                                                byteBuffer[bytePos9] = (byte) ((c3 - sOffsetTable[curIndex]) + 128);
                                                this.fOffsets[whichWindow3] = sOffsetTable[curIndex];
                                                this.fCurrentWindow = whichWindow3;
                                                int[] iArr3 = this.fTimeStamps;
                                                int i3 = this.fTimeStamp + 1;
                                                this.fTimeStamp = i3;
                                                iArr3[whichWindow3] = i3;
                                                bytePos = bytePos10;
                                                ucPos = ucPos2;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case 1:
                        while (true) {
                            if (ucPos < charBufferLimit && bytePos < byteBufferLimit) {
                                int ucPos8 = ucPos + 1;
                                char c4 = charBuffer[ucPos];
                                if (ucPos8 < charBufferLimit) {
                                    nextUC2 = charBuffer[ucPos8];
                                } else {
                                    nextUC2 = -1;
                                }
                                if (!isCompressible(c4) || (nextUC2 != -1 && !isCompressible(nextUC2))) {
                                    if (bytePos + 2 >= byteBufferLimit) {
                                        ucPos = ucPos8 - 1;
                                        break;
                                    } else {
                                        int hiByte5 = c4 >>> '\b';
                                        int loByte2 = c4 & '\u00ff';
                                        if (sUnicodeTagTable[hiByte5]) {
                                            byteBuffer[bytePos] = -16;
                                            bytePos++;
                                        }
                                        int bytePos11 = bytePos + 1;
                                        byteBuffer[bytePos] = (byte) hiByte5;
                                        bytePos = bytePos11 + 1;
                                        byteBuffer[bytePos11] = (byte) loByte2;
                                        ucPos = ucPos8;
                                        i = 1;
                                    }
                                } else if (c4 < '\u0080') {
                                    int loByte3 = c4 & '\u00ff';
                                    if (nextUC2 != -1 && nextUC2 < 128 && !sSingleTagTable[loByte3]) {
                                        if (bytePos + 1 >= byteBufferLimit) {
                                            ucPos = ucPos8 - 1;
                                            break;
                                        } else {
                                            int ucPos9 = this.fCurrentWindow;
                                            int whichWindow4 = bytePos + 1;
                                            byteBuffer[bytePos] = (byte) (ucPos9 + 224);
                                            bytePos = whichWindow4 + 1;
                                            byteBuffer[whichWindow4] = (byte) loByte3;
                                            int[] iArr4 = this.fTimeStamps;
                                            int i4 = this.fTimeStamp + i;
                                            this.fTimeStamp = i4;
                                            iArr4[ucPos9] = i4;
                                            this.fMode = 0;
                                            ucPos = ucPos8;
                                            break;
                                        }
                                    } else if (bytePos + 1 >= byteBufferLimit) {
                                        ucPos = ucPos8 - 1;
                                        break;
                                    } else {
                                        int ucPos10 = bytePos + 1;
                                        byteBuffer[bytePos] = 0;
                                        bytePos = ucPos10 + 1;
                                        byteBuffer[ucPos10] = (byte) loByte3;
                                        ucPos = ucPos8;
                                        i = 1;
                                    }
                                } else {
                                    int whichWindow5 = findDynamicWindow(c4);
                                    if (whichWindow5 != -1) {
                                        if (inDynamicWindow(nextUC2, whichWindow5)) {
                                            if (bytePos + 1 >= byteBufferLimit) {
                                                ucPos = ucPos8 - 1;
                                                break;
                                            } else {
                                                int ucPos11 = bytePos + 1;
                                                byteBuffer[bytePos] = (byte) (whichWindow5 + 224);
                                                bytePos = ucPos11 + 1;
                                                byteBuffer[ucPos11] = (byte) ((c4 - this.fOffsets[whichWindow5]) + 128);
                                                int[] iArr5 = this.fTimeStamps;
                                                int i5 = this.fTimeStamp + 1;
                                                this.fTimeStamp = i5;
                                                iArr5[whichWindow5] = i5;
                                                this.fCurrentWindow = whichWindow5;
                                                this.fMode = 0;
                                                ucPos = ucPos8;
                                                break;
                                            }
                                        } else if (bytePos + 2 >= byteBufferLimit) {
                                            ucPos = ucPos8 - 1;
                                            break;
                                        } else {
                                            int hiByte6 = c4 >>> '\b';
                                            int loByte4 = c4 & '\u00ff';
                                            if (sUnicodeTagTable[hiByte6]) {
                                                byteBuffer[bytePos] = -16;
                                                bytePos++;
                                            }
                                            int bytePos12 = bytePos + 1;
                                            byteBuffer[bytePos] = (byte) hiByte6;
                                            bytePos = bytePos12 + 1;
                                            byteBuffer[bytePos12] = (byte) loByte4;
                                            ucPos = ucPos8;
                                            i = 1;
                                        }
                                    } else {
                                        int curIndex2 = makeIndex(c4);
                                        int[] iArr6 = this.fIndexCount;
                                        iArr6[curIndex2] = iArr6[curIndex2] + 1;
                                        if (ucPos8 + 1 < charBufferLimit) {
                                            c2 = charBuffer[ucPos8 + 1];
                                        } else {
                                            c2 = '\uffff';
                                        }
                                        if (iArr6[curIndex2] <= 1 && (curIndex2 != makeIndex(nextUC2) || curIndex2 != makeIndex(c2))) {
                                            if (bytePos + 2 >= byteBufferLimit) {
                                                ucPos = ucPos8 - 1;
                                                break;
                                            } else {
                                                int hiByte7 = c4 >>> '\b';
                                                int loByte5 = c4 & '\u00ff';
                                                if (sUnicodeTagTable[hiByte7]) {
                                                    byteBuffer[bytePos] = -16;
                                                    bytePos++;
                                                }
                                                int bytePos13 = bytePos + 1;
                                                byteBuffer[bytePos] = (byte) hiByte7;
                                                bytePos = bytePos13 + 1;
                                                byteBuffer[bytePos13] = (byte) loByte5;
                                                ucPos = ucPos8;
                                                i = 1;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        break;
                }
            }
        }
        if (charsRead != null) {
            charsRead[0] = ucPos - charBufferStart;
        }
        return bytePos - byteBufferStart;
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
        if (c < 65376 || c >= 65439) {
            if (c >= 128 && c < 13312) {
                return (c / 128) & 255;
            }
            if (c >= 57344 && c <= 65535) {
                return ((c - 44032) / 128) & 255;
            }
            return 0;
        }
        return 255;
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
