package com.google.zxing.oned;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

public abstract class OneDReader implements Reader {
    public abstract Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException;

    public Result decode(BinaryBitmap image) throws NotFoundException, FormatException {
        return decode(image, (Map<DecodeHintType, ?>) null);
    }

    public Result decode(BinaryBitmap image, Map<DecodeHintType, ?> hints) throws NotFoundException, FormatException {
        try {
            return doDecode(image, hints);
        } catch (NotFoundException nfe) {
            if (!(hints != null && hints.containsKey(DecodeHintType.TRY_HARDER)) || !image.isRotateSupported()) {
                throw nfe;
            }
            BinaryBitmap rotatedImage = image.rotateCounterClockwise();
            Result doDecode = doDecode(rotatedImage, hints);
            Result result = doDecode;
            Map<ResultMetadataType, Object> resultMetadata = doDecode.getResultMetadata();
            int orientation = 270;
            if (resultMetadata != null && resultMetadata.containsKey(ResultMetadataType.ORIENTATION)) {
                orientation = (((Integer) resultMetadata.get(ResultMetadataType.ORIENTATION)).intValue() + 270) % 360;
            }
            result.putMetadata(ResultMetadataType.ORIENTATION, Integer.valueOf(orientation));
            ResultPoint[] resultPoints = result.getResultPoints();
            ResultPoint[] points = resultPoints;
            if (resultPoints != null) {
                int height = rotatedImage.getHeight();
                for (int i = 0; i < points.length; i++) {
                    points[i] = new ResultPoint((((float) height) - points[i].getY()) - 1.0f, points[i].getX());
                }
            }
            return result;
        }
    }

    public void reset() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:71:0x010a, code lost:
        throw com.google.zxing.NotFoundException.getNotFoundInstance();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.zxing.Result doDecode(com.google.zxing.BinaryBitmap r22, java.util.Map<com.google.zxing.DecodeHintType, ?> r23) throws com.google.zxing.NotFoundException {
        /*
            r21 = this;
            r0 = r23
            int r1 = r22.getWidth()
            int r2 = r22.getHeight()
            com.google.zxing.common.BitArray r3 = new com.google.zxing.common.BitArray
            r3.<init>(r1)
            r4 = 0
            r5 = 1
            if (r0 == 0) goto L_0x001d
            com.google.zxing.DecodeHintType r6 = com.google.zxing.DecodeHintType.TRY_HARDER
            boolean r6 = r0.containsKey(r6)
            if (r6 == 0) goto L_0x001d
            r6 = r5
            goto L_0x001e
        L_0x001d:
            r6 = r4
        L_0x001e:
            if (r6 == 0) goto L_0x0023
            r7 = 8
            goto L_0x0024
        L_0x0023:
            r7 = 5
        L_0x0024:
            int r7 = r2 >> r7
            int r7 = java.lang.Math.max(r5, r7)
            if (r6 == 0) goto L_0x002e
            r6 = r2
            goto L_0x0030
        L_0x002e:
            r6 = 15
        L_0x0030:
            int r8 = r2 / 2
            r9 = r3
            r3 = r0
            r0 = r4
        L_0x0035:
            if (r0 >= r6) goto L_0x0104
            int r10 = r0 + 1
            int r11 = r10 / 2
            r0 = r0 & 1
            if (r0 != 0) goto L_0x0041
            r0 = r5
            goto L_0x0042
        L_0x0041:
            r0 = r4
        L_0x0042:
            if (r0 == 0) goto L_0x0045
            goto L_0x0046
        L_0x0045:
            int r11 = -r11
        L_0x0046:
            int r11 = r11 * r7
            int r11 = r11 + r8
            if (r11 < 0) goto L_0x0101
            if (r11 >= r2) goto L_0x0101
            r12 = r22
            com.google.zxing.common.BitArray r9 = r12.getBlackRow(r11, r9)     // Catch:{ NotFoundException -> 0x00ef }
            r13 = r4
        L_0x0054:
            r0 = 2
            if (r13 >= r0) goto L_0x00e7
            if (r13 != r5) goto L_0x0076
            r9.reverse()
            if (r3 == 0) goto L_0x0076
            com.google.zxing.DecodeHintType r0 = com.google.zxing.DecodeHintType.NEED_RESULT_POINT_CALLBACK
            boolean r0 = r3.containsKey(r0)
            if (r0 == 0) goto L_0x0076
            java.util.EnumMap r0 = new java.util.EnumMap
            java.lang.Class<com.google.zxing.DecodeHintType> r14 = com.google.zxing.DecodeHintType.class
            r0.<init>(r14)
            r0.putAll(r3)
            com.google.zxing.DecodeHintType r3 = com.google.zxing.DecodeHintType.NEED_RESULT_POINT_CALLBACK
            r0.remove(r3)
            r3 = r0
        L_0x0076:
            r14 = r21
            com.google.zxing.Result r0 = r14.decodeRow(r11, r9, r3)     // Catch:{ ReaderException -> 0x00d7 }
            if (r13 != r5) goto L_0x00d6
            com.google.zxing.ResultMetadataType r15 = com.google.zxing.ResultMetadataType.ORIENTATION     // Catch:{ ReaderException -> 0x00d7 }
            r16 = 180(0xb4, float:2.52E-43)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r16)     // Catch:{ ReaderException -> 0x00cf }
            r0.putMetadata(r15, r5)     // Catch:{ ReaderException -> 0x00cf }
            com.google.zxing.ResultPoint[] r5 = r0.getResultPoints()     // Catch:{ ReaderException -> 0x00cf }
            if (r5 == 0) goto L_0x00d6
            com.google.zxing.ResultPoint r15 = new com.google.zxing.ResultPoint     // Catch:{ ReaderException -> 0x00cf }
            r16 = r2
            float r2 = (float) r1
            r18 = r5[r4]     // Catch:{ ReaderException -> 0x00cb }
            float r18 = r18.getX()     // Catch:{ ReaderException -> 0x00cb }
            float r18 = r2 - r18
            r19 = 1065353216(0x3f800000, float:1.0)
            r20 = r1
            float r1 = r18 - r19
            r18 = r5[r4]     // Catch:{ ReaderException -> 0x00c9 }
            float r4 = r18.getY()     // Catch:{ ReaderException -> 0x00c9 }
            r15.<init>(r1, r4)     // Catch:{ ReaderException -> 0x00c9 }
            r1 = 0
            r5[r1] = r15     // Catch:{ ReaderException -> 0x00c9 }
            com.google.zxing.ResultPoint r4 = new com.google.zxing.ResultPoint     // Catch:{ ReaderException -> 0x00c9 }
            r15 = 1
            r17 = r5[r15]     // Catch:{ ReaderException -> 0x00c7 }
            float r17 = r17.getX()     // Catch:{ ReaderException -> 0x00c7 }
            float r2 = r2 - r17
            float r2 = r2 - r19
            r17 = r5[r15]     // Catch:{ ReaderException -> 0x00c7 }
            float r1 = r17.getY()     // Catch:{ ReaderException -> 0x00c7 }
            r4.<init>(r2, r1)     // Catch:{ ReaderException -> 0x00c7 }
            r5[r15] = r4     // Catch:{ ReaderException -> 0x00c7 }
            goto L_0x00d6
        L_0x00c7:
            r0 = move-exception
            goto L_0x00dd
        L_0x00c9:
            r0 = move-exception
            goto L_0x00d4
        L_0x00cb:
            r0 = move-exception
            r20 = r1
            goto L_0x00d4
        L_0x00cf:
            r0 = move-exception
            r20 = r1
            r16 = r2
        L_0x00d4:
            r15 = 1
            goto L_0x00dd
        L_0x00d6:
            return r0
        L_0x00d7:
            r0 = move-exception
            r20 = r1
            r16 = r2
            r15 = r5
        L_0x00dd:
            int r13 = r13 + 1
            r5 = r15
            r2 = r16
            r1 = r20
            r4 = 0
            goto L_0x0054
        L_0x00e7:
            r14 = r21
            r20 = r1
            r16 = r2
            r15 = r5
            goto L_0x00f8
        L_0x00ef:
            r0 = move-exception
            r14 = r21
            r20 = r1
            r16 = r2
            r15 = r5
        L_0x00f8:
            r0 = r10
            r5 = r15
            r2 = r16
            r1 = r20
            r4 = 0
            goto L_0x0035
        L_0x0101:
            r14 = r21
            goto L_0x0106
        L_0x0104:
            r14 = r21
        L_0x0106:
            com.google.zxing.NotFoundException r0 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.OneDReader.doDecode(com.google.zxing.BinaryBitmap, java.util.Map):com.google.zxing.Result");
    }

    protected static void recordPattern(BitArray row, int start, int[] counters) throws NotFoundException {
        int numCounters = counters.length;
        Arrays.fill(counters, 0, numCounters, 0);
        int end = row.getSize();
        if (start < end) {
            boolean isWhite = !row.get(start);
            int counterPosition = 0;
            int i = start;
            while (i < end) {
                if (row.get(i) == isWhite) {
                    counterPosition++;
                    if (counterPosition == numCounters) {
                        break;
                    }
                    counters[counterPosition] = 1;
                    isWhite = !isWhite;
                } else {
                    counters[counterPosition] = counters[counterPosition] + 1;
                }
                i++;
            }
            if (counterPosition == numCounters) {
                return;
            }
            if (counterPosition != numCounters - 1 || i != end) {
                throw NotFoundException.getNotFoundInstance();
            }
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    protected static void recordPatternInReverse(BitArray row, int start, int[] counters) throws NotFoundException {
        int numTransitionsLeft = counters.length;
        boolean last = row.get(start);
        while (start > 0 && numTransitionsLeft >= 0) {
            start--;
            if (row.get(start) != last) {
                numTransitionsLeft--;
                last = !last;
            }
        }
        if (numTransitionsLeft < 0) {
            recordPattern(row, start + 1, counters);
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    protected static float patternMatchVariance(int[] counters, int[] pattern, float maxIndividualVariance) {
        int numCounters = counters.length;
        int total = 0;
        int patternLength = 0;
        for (int i = 0; i < numCounters; i++) {
            total += counters[i];
            patternLength += pattern[i];
        }
        if (total < patternLength) {
            return Float.POSITIVE_INFINITY;
        }
        float unitBarWidth = ((float) total) / ((float) patternLength);
        float maxIndividualVariance2 = maxIndividualVariance * unitBarWidth;
        float totalVariance = 0.0f;
        for (int x = 0; x < numCounters; x++) {
            int counter = counters[x];
            float scaledPattern = ((float) pattern[x]) * unitBarWidth;
            float f = ((float) counter) > scaledPattern ? ((float) counter) - scaledPattern : scaledPattern - ((float) counter);
            float variance = f;
            if (f > maxIndividualVariance2) {
                return Float.POSITIVE_INFINITY;
            }
            totalVariance += variance;
        }
        return totalVariance / ((float) total);
    }
}
