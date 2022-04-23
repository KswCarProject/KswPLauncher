package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public final class MultiFormatUPCEANReader extends OneDReader {
    private final UPCEANReader[] readers;

    public MultiFormatUPCEANReader(Map<DecodeHintType, ?> hints) {
        Collection<BarcodeFormat> possibleFormats;
        if (hints == null) {
            possibleFormats = null;
        } else {
            possibleFormats = (Collection) hints.get(DecodeHintType.POSSIBLE_FORMATS);
        }
        Collection<UPCEANReader> readers2 = new ArrayList<>();
        if (possibleFormats != null) {
            if (possibleFormats.contains(BarcodeFormat.EAN_13)) {
                readers2.add(new EAN13Reader());
            } else if (possibleFormats.contains(BarcodeFormat.UPC_A)) {
                readers2.add(new UPCAReader());
            }
            if (possibleFormats.contains(BarcodeFormat.EAN_8)) {
                readers2.add(new EAN8Reader());
            }
            if (possibleFormats.contains(BarcodeFormat.UPC_E)) {
                readers2.add(new UPCEReader());
            }
        }
        if (readers2.isEmpty()) {
            readers2.add(new EAN13Reader());
            readers2.add(new EAN8Reader());
            readers2.add(new UPCEReader());
        }
        this.readers = (UPCEANReader[]) readers2.toArray(new UPCEANReader[readers2.size()]);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0038 A[SYNTHETIC, Splitter:B:14:0x0038] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0044 A[SYNTHETIC, Splitter:B:18:0x0044] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0056 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0080 A[ADDED_TO_REGION, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.zxing.Result decodeRow(int r19, com.google.zxing.common.BitArray r20, java.util.Map<com.google.zxing.DecodeHintType, ?> r21) throws com.google.zxing.NotFoundException {
        /*
            r18 = this;
            r1 = r21
            int[] r2 = com.google.zxing.oned.UPCEANReader.findStartGuardPattern(r20)
            r3 = r18
            com.google.zxing.oned.UPCEANReader[] r4 = r3.readers
            int r5 = r4.length
            r6 = 0
            r8 = r6
            r9 = 0
            r10 = 0
            r11 = 0
        L_0x0010:
            if (r8 >= r5) goto L_0x0090
            r12 = r4[r8]
            r13 = r19
            r14 = r20
            com.google.zxing.Result r0 = r12.decodeRow(r13, r14, r2, r1)     // Catch:{ ReaderException -> 0x0085 }
            r9 = r0
            com.google.zxing.BarcodeFormat r0 = r0.getBarcodeFormat()     // Catch:{ ReaderException -> 0x0085 }
            com.google.zxing.BarcodeFormat r15 = com.google.zxing.BarcodeFormat.EAN_13     // Catch:{ ReaderException -> 0x0085 }
            if (r0 != r15) goto L_0x0033
            java.lang.String r0 = r9.getText()     // Catch:{ ReaderException -> 0x004f }
            char r0 = r0.charAt(r6)     // Catch:{ ReaderException -> 0x004f }
            r15 = 48
            if (r0 != r15) goto L_0x0033
            r0 = 1
            goto L_0x0034
        L_0x0033:
            r0 = r6
        L_0x0034:
            if (r1 != 0) goto L_0x0038
            r15 = 0
            goto L_0x0040
        L_0x0038:
            com.google.zxing.DecodeHintType r15 = com.google.zxing.DecodeHintType.POSSIBLE_FORMATS     // Catch:{ ReaderException -> 0x0085 }
            java.lang.Object r15 = r1.get(r15)     // Catch:{ ReaderException -> 0x0085 }
            java.util.Collection r15 = (java.util.Collection) r15     // Catch:{ ReaderException -> 0x0085 }
        L_0x0040:
            r10 = r15
            if (r15 == 0) goto L_0x0053
            com.google.zxing.BarcodeFormat r15 = com.google.zxing.BarcodeFormat.UPC_A     // Catch:{ ReaderException -> 0x004f }
            boolean r15 = r10.contains(r15)     // Catch:{ ReaderException -> 0x004f }
            if (r15 == 0) goto L_0x004d
            goto L_0x0053
        L_0x004d:
            r15 = r6
            goto L_0x0054
        L_0x004f:
            r0 = move-exception
            r16 = r2
            goto L_0x0088
        L_0x0053:
            r15 = 1
        L_0x0054:
            if (r0 == 0) goto L_0x0080
            if (r15 == 0) goto L_0x0080
            com.google.zxing.Result r6 = new com.google.zxing.Result     // Catch:{ ReaderException -> 0x0085 }
            java.lang.String r7 = r9.getText()     // Catch:{ ReaderException -> 0x0085 }
            r17 = r0
            r0 = 1
            java.lang.String r0 = r7.substring(r0)     // Catch:{ ReaderException -> 0x0085 }
            byte[] r7 = r9.getRawBytes()     // Catch:{ ReaderException -> 0x0085 }
            com.google.zxing.ResultPoint[] r1 = r9.getResultPoints()     // Catch:{ ReaderException -> 0x0085 }
            r16 = r2
            com.google.zxing.BarcodeFormat r2 = com.google.zxing.BarcodeFormat.UPC_A     // Catch:{ ReaderException -> 0x007e }
            r6.<init>(r0, r7, r1, r2)     // Catch:{ ReaderException -> 0x007e }
            r0 = r11
            r11 = r6
            java.util.Map r0 = r9.getResultMetadata()     // Catch:{ ReaderException -> 0x007e }
            r6.putAllMetadata(r0)     // Catch:{ ReaderException -> 0x007e }
            return r11
        L_0x007e:
            r0 = move-exception
            goto L_0x0088
        L_0x0080:
            r17 = r0
            r16 = r2
            return r9
        L_0x0085:
            r0 = move-exception
            r16 = r2
        L_0x0088:
            int r8 = r8 + 1
            r1 = r21
            r2 = r16
            r6 = 0
            goto L_0x0010
        L_0x0090:
            com.google.zxing.NotFoundException r0 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.MultiFormatUPCEANReader.decodeRow(int, com.google.zxing.common.BitArray, java.util.Map):com.google.zxing.Result");
    }

    public void reset() {
        for (UPCEANReader reset : this.readers) {
            reset.reset();
        }
    }
}
