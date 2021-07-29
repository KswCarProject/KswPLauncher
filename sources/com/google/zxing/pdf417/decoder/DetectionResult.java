package com.google.zxing.pdf417.decoder;

import com.google.zxing.pdf417.PDF417Common;

final class DetectionResult {
    private static final int ADJUST_ROW_NUMBER_SKIP = 2;
    private final int barcodeColumnCount;
    private final BarcodeMetadata barcodeMetadata;
    private BoundingBox boundingBox;
    private final DetectionResultColumn[] detectionResultColumns;

    DetectionResult(BarcodeMetadata barcodeMetadata2, BoundingBox boundingBox2) {
        this.barcodeMetadata = barcodeMetadata2;
        int columnCount = barcodeMetadata2.getColumnCount();
        this.barcodeColumnCount = columnCount;
        this.boundingBox = boundingBox2;
        this.detectionResultColumns = new DetectionResultColumn[(columnCount + 2)];
    }

    /* access modifiers changed from: package-private */
    public DetectionResultColumn[] getDetectionResultColumns() {
        int previousUnadjustedCount;
        adjustIndicatorColumnRowNumbers(this.detectionResultColumns[0]);
        adjustIndicatorColumnRowNumbers(this.detectionResultColumns[this.barcodeColumnCount + 1]);
        int unadjustedCodewordCount = PDF417Common.MAX_CODEWORDS_IN_BARCODE;
        do {
            previousUnadjustedCount = unadjustedCodewordCount;
            int adjustRowNumbers = adjustRowNumbers();
            unadjustedCodewordCount = adjustRowNumbers;
            if (adjustRowNumbers <= 0) {
                break;
            }
        } while (unadjustedCodewordCount < previousUnadjustedCount);
        return this.detectionResultColumns;
    }

    private void adjustIndicatorColumnRowNumbers(DetectionResultColumn detectionResultColumn) {
        if (detectionResultColumn != null) {
            ((DetectionResultRowIndicatorColumn) detectionResultColumn).adjustCompleteIndicatorColumnRowNumbers(this.barcodeMetadata);
        }
    }

    private int adjustRowNumbers() {
        int adjustRowNumbersByRow = adjustRowNumbersByRow();
        int unadjustedCount = adjustRowNumbersByRow;
        if (adjustRowNumbersByRow == 0) {
            return 0;
        }
        for (int barcodeColumn = 1; barcodeColumn < this.barcodeColumnCount + 1; barcodeColumn++) {
            Codeword[] codewords = this.detectionResultColumns[barcodeColumn].getCodewords();
            for (int codewordsRow = 0; codewordsRow < codewords.length; codewordsRow++) {
                if (codewords[codewordsRow] != null && !codewords[codewordsRow].hasValidRowNumber()) {
                    adjustRowNumbers(barcodeColumn, codewordsRow, codewords);
                }
            }
        }
        return unadjustedCount;
    }

    private int adjustRowNumbersByRow() {
        adjustRowNumbersFromBothRI();
        return adjustRowNumbersFromLRI() + adjustRowNumbersFromRRI();
    }

    private void adjustRowNumbersFromBothRI() {
        DetectionResultColumn[] detectionResultColumnArr = this.detectionResultColumns;
        if (detectionResultColumnArr[0] != null && detectionResultColumnArr[this.barcodeColumnCount + 1] != null) {
            Codeword[] LRIcodewords = detectionResultColumnArr[0].getCodewords();
            Codeword[] RRIcodewords = this.detectionResultColumns[this.barcodeColumnCount + 1].getCodewords();
            for (int codewordsRow = 0; codewordsRow < LRIcodewords.length; codewordsRow++) {
                if (!(LRIcodewords[codewordsRow] == null || RRIcodewords[codewordsRow] == null || LRIcodewords[codewordsRow].getRowNumber() != RRIcodewords[codewordsRow].getRowNumber())) {
                    for (int barcodeColumn = 1; barcodeColumn <= this.barcodeColumnCount; barcodeColumn++) {
                        Codeword codeword = this.detectionResultColumns[barcodeColumn].getCodewords()[codewordsRow];
                        Codeword codeword2 = codeword;
                        if (codeword != null) {
                            codeword2.setRowNumber(LRIcodewords[codewordsRow].getRowNumber());
                            if (!codeword2.hasValidRowNumber()) {
                                this.detectionResultColumns[barcodeColumn].getCodewords()[codewordsRow] = null;
                            }
                        }
                    }
                }
            }
        }
    }

    private int adjustRowNumbersFromRRI() {
        DetectionResultColumn[] detectionResultColumnArr = this.detectionResultColumns;
        int i = this.barcodeColumnCount;
        if (detectionResultColumnArr[i + 1] == null) {
            return 0;
        }
        int unadjustedCount = 0;
        Codeword[] codewords = detectionResultColumnArr[i + 1].getCodewords();
        for (int codewordsRow = 0; codewordsRow < codewords.length; codewordsRow++) {
            if (codewords[codewordsRow] != null) {
                int rowIndicatorRowNumber = codewords[codewordsRow].getRowNumber();
                int invalidRowCounts = 0;
                for (int barcodeColumn = this.barcodeColumnCount + 1; barcodeColumn > 0 && invalidRowCounts < 2; barcodeColumn--) {
                    Codeword codeword = this.detectionResultColumns[barcodeColumn].getCodewords()[codewordsRow];
                    Codeword codeword2 = codeword;
                    if (codeword != null) {
                        invalidRowCounts = adjustRowNumberIfValid(rowIndicatorRowNumber, invalidRowCounts, codeword2);
                        if (!codeword2.hasValidRowNumber()) {
                            unadjustedCount++;
                        }
                    }
                }
            }
        }
        return unadjustedCount;
    }

    private int adjustRowNumbersFromLRI() {
        DetectionResultColumn[] detectionResultColumnArr = this.detectionResultColumns;
        if (detectionResultColumnArr[0] == null) {
            return 0;
        }
        int unadjustedCount = 0;
        Codeword[] codewords = detectionResultColumnArr[0].getCodewords();
        for (int codewordsRow = 0; codewordsRow < codewords.length; codewordsRow++) {
            if (codewords[codewordsRow] != null) {
                int rowIndicatorRowNumber = codewords[codewordsRow].getRowNumber();
                int invalidRowCounts = 0;
                for (int barcodeColumn = 1; barcodeColumn < this.barcodeColumnCount + 1 && invalidRowCounts < 2; barcodeColumn++) {
                    Codeword codeword = this.detectionResultColumns[barcodeColumn].getCodewords()[codewordsRow];
                    Codeword codeword2 = codeword;
                    if (codeword != null) {
                        invalidRowCounts = adjustRowNumberIfValid(rowIndicatorRowNumber, invalidRowCounts, codeword2);
                        if (!codeword2.hasValidRowNumber()) {
                            unadjustedCount++;
                        }
                    }
                }
            }
        }
        return unadjustedCount;
    }

    private static int adjustRowNumberIfValid(int rowIndicatorRowNumber, int invalidRowCounts, Codeword codeword) {
        if (codeword == null || codeword.hasValidRowNumber()) {
            return invalidRowCounts;
        }
        if (!codeword.isValidRowNumber(rowIndicatorRowNumber)) {
            return invalidRowCounts + 1;
        }
        codeword.setRowNumber(rowIndicatorRowNumber);
        return 0;
    }

    private void adjustRowNumbers(int barcodeColumn, int codewordsRow, Codeword[] codewords) {
        Codeword codeword = codewords[codewordsRow];
        Codeword[] nextColumnCodewords = this.detectionResultColumns[barcodeColumn - 1].getCodewords();
        Codeword[] previousColumnCodewords = nextColumnCodewords;
        DetectionResultColumn[] detectionResultColumnArr = this.detectionResultColumns;
        if (detectionResultColumnArr[barcodeColumn + 1] != null) {
            nextColumnCodewords = detectionResultColumnArr[barcodeColumn + 1].getCodewords();
        }
        Codeword[] codewordArr = new Codeword[14];
        Codeword[] otherCodewords = codewordArr;
        codewordArr[2] = previousColumnCodewords[codewordsRow];
        otherCodewords[3] = nextColumnCodewords[codewordsRow];
        int i = 0;
        if (codewordsRow > 0) {
            otherCodewords[0] = codewords[codewordsRow - 1];
            otherCodewords[4] = previousColumnCodewords[codewordsRow - 1];
            otherCodewords[5] = nextColumnCodewords[codewordsRow - 1];
        }
        if (codewordsRow > 1) {
            otherCodewords[8] = codewords[codewordsRow - 2];
            otherCodewords[10] = previousColumnCodewords[codewordsRow - 2];
            otherCodewords[11] = nextColumnCodewords[codewordsRow - 2];
        }
        if (codewordsRow < codewords.length - 1) {
            otherCodewords[1] = codewords[codewordsRow + 1];
            otherCodewords[6] = previousColumnCodewords[codewordsRow + 1];
            otherCodewords[7] = nextColumnCodewords[codewordsRow + 1];
        }
        if (codewordsRow < codewords.length - 2) {
            otherCodewords[9] = codewords[codewordsRow + 2];
            otherCodewords[12] = previousColumnCodewords[codewordsRow + 2];
            otherCodewords[13] = nextColumnCodewords[codewordsRow + 2];
        }
        while (i < 14 && !adjustRowNumber(codeword, otherCodewords[i])) {
            i++;
        }
    }

    private static boolean adjustRowNumber(Codeword codeword, Codeword otherCodeword) {
        if (otherCodeword == null || !otherCodeword.hasValidRowNumber() || otherCodeword.getBucket() != codeword.getBucket()) {
            return false;
        }
        codeword.setRowNumber(otherCodeword.getRowNumber());
        return true;
    }

    /* access modifiers changed from: package-private */
    public int getBarcodeColumnCount() {
        return this.barcodeColumnCount;
    }

    /* access modifiers changed from: package-private */
    public int getBarcodeRowCount() {
        return this.barcodeMetadata.getRowCount();
    }

    /* access modifiers changed from: package-private */
    public int getBarcodeECLevel() {
        return this.barcodeMetadata.getErrorCorrectionLevel();
    }

    /* access modifiers changed from: package-private */
    public void setBoundingBox(BoundingBox boundingBox2) {
        this.boundingBox = boundingBox2;
    }

    /* access modifiers changed from: package-private */
    public BoundingBox getBoundingBox() {
        return this.boundingBox;
    }

    /* access modifiers changed from: package-private */
    public void setDetectionResultColumn(int barcodeColumn, DetectionResultColumn detectionResultColumn) {
        this.detectionResultColumns[barcodeColumn] = detectionResultColumn;
    }

    /* access modifiers changed from: package-private */
    public DetectionResultColumn getDetectionResultColumn(int barcodeColumn) {
        return this.detectionResultColumns[barcodeColumn];
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0082, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0083, code lost:
        if (r1 != null) goto L_0x0085;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0089, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x008a, code lost:
        r1.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x008e, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0091, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String toString() {
        /*
            r11 = this;
            com.google.zxing.pdf417.decoder.DetectionResultColumn[] r0 = r11.detectionResultColumns
            r1 = 0
            r2 = r0[r1]
            r3 = 0
            r4 = r3
            r4 = r2
            r5 = 1
            if (r2 != 0) goto L_0x0010
            int r2 = r11.barcodeColumnCount
            int r2 = r2 + r5
            r4 = r0[r2]
        L_0x0010:
            java.util.Formatter r0 = new java.util.Formatter
            r0.<init>()
            r2 = 0
        L_0x0016:
            com.google.zxing.pdf417.decoder.Codeword[] r6 = r4.getCodewords()     // Catch:{ all -> 0x0080 }
            int r6 = r6.length     // Catch:{ all -> 0x0080 }
            if (r2 >= r6) goto L_0x0078
            java.lang.String r6 = "CW %3d:"
            java.lang.Object[] r7 = new java.lang.Object[r5]     // Catch:{ all -> 0x0080 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x0080 }
            r7[r1] = r8     // Catch:{ all -> 0x0080 }
            r0.format(r6, r7)     // Catch:{ all -> 0x0080 }
            r6 = 0
        L_0x002b:
            int r7 = r11.barcodeColumnCount     // Catch:{ all -> 0x0080 }
            r8 = 2
            int r7 = r7 + r8
            if (r6 >= r7) goto L_0x006e
            com.google.zxing.pdf417.decoder.DetectionResultColumn[] r7 = r11.detectionResultColumns     // Catch:{ all -> 0x0080 }
            r9 = r7[r6]     // Catch:{ all -> 0x0080 }
            java.lang.String r10 = "    |   "
            if (r9 != 0) goto L_0x003f
            java.lang.Object[] r7 = new java.lang.Object[r1]     // Catch:{ all -> 0x0080 }
            r0.format(r10, r7)     // Catch:{ all -> 0x0080 }
            goto L_0x006b
        L_0x003f:
            r7 = r7[r6]     // Catch:{ all -> 0x0080 }
            com.google.zxing.pdf417.decoder.Codeword[] r7 = r7.getCodewords()     // Catch:{ all -> 0x0080 }
            r7 = r7[r2]     // Catch:{ all -> 0x0080 }
            r3 = r7
            if (r7 != 0) goto L_0x0050
            java.lang.Object[] r7 = new java.lang.Object[r1]     // Catch:{ all -> 0x0080 }
            r0.format(r10, r7)     // Catch:{ all -> 0x0080 }
            goto L_0x006b
        L_0x0050:
            java.lang.String r7 = " %3d|%3d"
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ all -> 0x0080 }
            int r9 = r3.getRowNumber()     // Catch:{ all -> 0x0080 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x0080 }
            r8[r1] = r9     // Catch:{ all -> 0x0080 }
            int r9 = r3.getValue()     // Catch:{ all -> 0x0080 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x0080 }
            r8[r5] = r9     // Catch:{ all -> 0x0080 }
            r0.format(r7, r8)     // Catch:{ all -> 0x0080 }
        L_0x006b:
            int r6 = r6 + 1
            goto L_0x002b
        L_0x006e:
            java.lang.String r6 = "%n"
            java.lang.Object[] r7 = new java.lang.Object[r1]     // Catch:{ all -> 0x0080 }
            r0.format(r6, r7)     // Catch:{ all -> 0x0080 }
            int r2 = r2 + 1
            goto L_0x0016
        L_0x0078:
            java.lang.String r1 = r0.toString()     // Catch:{ all -> 0x0080 }
            r0.close()
            return r1
        L_0x0080:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0082 }
        L_0x0082:
            r2 = move-exception
            if (r1 == 0) goto L_0x008e
            r0.close()     // Catch:{ all -> 0x0089 }
            goto L_0x0091
        L_0x0089:
            r3 = move-exception
            r1.addSuppressed(r3)
            goto L_0x0091
        L_0x008e:
            r0.close()
        L_0x0091:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DetectionResult.toString():java.lang.String");
    }
}
