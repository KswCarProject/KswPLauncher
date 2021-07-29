package com.google.zxing.aztec;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import java.util.Map;

public final class AztecReader implements Reader {
    public Result decode(BinaryBitmap image) throws NotFoundException, FormatException {
        return decode(image, (Map<DecodeHintType, ?>) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0063 A[LOOP:0: B:30:0x0061->B:31:0x0063, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0094  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.zxing.Result decode(com.google.zxing.BinaryBitmap r11, java.util.Map<com.google.zxing.DecodeHintType, ?> r12) throws com.google.zxing.NotFoundException, com.google.zxing.FormatException {
        /*
            r10 = this;
            com.google.zxing.aztec.detector.Detector r0 = new com.google.zxing.aztec.detector.Detector
            com.google.zxing.common.BitMatrix r11 = r11.getBlackMatrix()
            r0.<init>(r11)
            r11 = 0
            r1 = 0
            com.google.zxing.aztec.AztecDetectorResult r2 = r0.detect(r11)     // Catch:{ NotFoundException -> 0x002f, FormatException -> 0x0029 }
            com.google.zxing.ResultPoint[] r3 = r2.getPoints()     // Catch:{ NotFoundException -> 0x002f, FormatException -> 0x0029 }
            com.google.zxing.aztec.decoder.Decoder r4 = new com.google.zxing.aztec.decoder.Decoder     // Catch:{ NotFoundException -> 0x0027, FormatException -> 0x0025 }
            r4.<init>()     // Catch:{ NotFoundException -> 0x0027, FormatException -> 0x0025 }
            com.google.zxing.common.DecoderResult r2 = r4.decode(r2)     // Catch:{ NotFoundException -> 0x0027, FormatException -> 0x0025 }
            r4 = r3
            r3 = r1
            r1 = r2
            r2 = r3
            goto L_0x0033
        L_0x0025:
            r2 = move-exception
            goto L_0x002b
        L_0x0027:
            r2 = move-exception
            goto L_0x0031
        L_0x0029:
            r2 = move-exception
            r3 = r1
        L_0x002b:
            r4 = r3
            r3 = r2
            r2 = r1
            goto L_0x0033
        L_0x002f:
            r2 = move-exception
            r3 = r1
        L_0x0031:
            r4 = r3
            r3 = r1
        L_0x0033:
            if (r1 != 0) goto L_0x0053
            r1 = 1
            com.google.zxing.aztec.AztecDetectorResult r0 = r0.detect(r1)     // Catch:{ NotFoundException -> 0x004b, FormatException -> 0x0049 }
            com.google.zxing.ResultPoint[] r4 = r0.getPoints()     // Catch:{ NotFoundException -> 0x004b, FormatException -> 0x0049 }
            com.google.zxing.aztec.decoder.Decoder r1 = new com.google.zxing.aztec.decoder.Decoder     // Catch:{ NotFoundException -> 0x004b, FormatException -> 0x0049 }
            r1.<init>()     // Catch:{ NotFoundException -> 0x004b, FormatException -> 0x0049 }
            com.google.zxing.common.DecoderResult r1 = r1.decode(r0)     // Catch:{ NotFoundException -> 0x004b, FormatException -> 0x0049 }
            r6 = r4
            goto L_0x0054
        L_0x0049:
            r11 = move-exception
            goto L_0x004c
        L_0x004b:
            r11 = move-exception
        L_0x004c:
            if (r2 != 0) goto L_0x0052
            if (r3 == 0) goto L_0x0051
            throw r3
        L_0x0051:
            throw r11
        L_0x0052:
            throw r2
        L_0x0053:
            r6 = r4
        L_0x0054:
            if (r12 == 0) goto L_0x006b
            com.google.zxing.DecodeHintType r0 = com.google.zxing.DecodeHintType.NEED_RESULT_POINT_CALLBACK
            java.lang.Object r12 = r12.get(r0)
            com.google.zxing.ResultPointCallback r12 = (com.google.zxing.ResultPointCallback) r12
            if (r12 == 0) goto L_0x006b
            int r0 = r6.length
        L_0x0061:
            if (r11 >= r0) goto L_0x006b
            r2 = r6[r11]
            r12.foundPossibleResultPoint(r2)
            int r11 = r11 + 1
            goto L_0x0061
        L_0x006b:
            com.google.zxing.Result r11 = new com.google.zxing.Result
            java.lang.String r3 = r1.getText()
            byte[] r4 = r1.getRawBytes()
            int r5 = r1.getNumBits()
            com.google.zxing.BarcodeFormat r7 = com.google.zxing.BarcodeFormat.AZTEC
            long r8 = java.lang.System.currentTimeMillis()
            r2 = r11
            r2.<init>(r3, r4, r5, r6, r7, r8)
            java.util.List r12 = r1.getByteSegments()
            if (r12 == 0) goto L_0x008e
            com.google.zxing.ResultMetadataType r0 = com.google.zxing.ResultMetadataType.BYTE_SEGMENTS
            r11.putMetadata(r0, r12)
        L_0x008e:
            java.lang.String r12 = r1.getECLevel()
            if (r12 == 0) goto L_0x0099
            com.google.zxing.ResultMetadataType r0 = com.google.zxing.ResultMetadataType.ERROR_CORRECTION_LEVEL
            r11.putMetadata(r0, r12)
        L_0x0099:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.aztec.AztecReader.decode(com.google.zxing.BinaryBitmap, java.util.Map):com.google.zxing.Result");
    }

    public void reset() {
    }
}
