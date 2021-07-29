package com.google.zxing.pdf417.decoder;

class DetectionResultColumn {
    private static final int MAX_NEARBY_DISTANCE = 5;
    private final BoundingBox boundingBox;
    private final Codeword[] codewords;

    DetectionResultColumn(BoundingBox boundingBox2) {
        this.boundingBox = new BoundingBox(boundingBox2);
        this.codewords = new Codeword[((boundingBox2.getMaxY() - boundingBox2.getMinY()) + 1)];
    }

    /* access modifiers changed from: package-private */
    public final Codeword getCodewordNearby(int imageRow) {
        Codeword codeword = getCodeword(imageRow);
        Codeword codeword2 = codeword;
        if (codeword != null) {
            return codeword2;
        }
        for (int i = 1; i < 5; i++) {
            int imageRowToCodewordIndex = imageRowToCodewordIndex(imageRow) - i;
            int nearImageRow = imageRowToCodewordIndex;
            if (imageRowToCodewordIndex >= 0) {
                Codeword codeword3 = this.codewords[nearImageRow];
                Codeword codeword4 = codeword3;
                if (codeword3 != null) {
                    return codeword4;
                }
            }
            int imageRowToCodewordIndex2 = imageRowToCodewordIndex(imageRow) + i;
            int nearImageRow2 = imageRowToCodewordIndex2;
            Codeword[] codewordArr = this.codewords;
            if (imageRowToCodewordIndex2 < codewordArr.length) {
                Codeword codeword5 = codewordArr[nearImageRow2];
                Codeword codeword6 = codeword5;
                if (codeword5 != null) {
                    return codeword6;
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final int imageRowToCodewordIndex(int imageRow) {
        return imageRow - this.boundingBox.getMinY();
    }

    /* access modifiers changed from: package-private */
    public final void setCodeword(int imageRow, Codeword codeword) {
        this.codewords[imageRowToCodewordIndex(imageRow)] = codeword;
    }

    /* access modifiers changed from: package-private */
    public final Codeword getCodeword(int imageRow) {
        return this.codewords[imageRowToCodewordIndex(imageRow)];
    }

    /* access modifiers changed from: package-private */
    public final BoundingBox getBoundingBox() {
        return this.boundingBox;
    }

    /* access modifiers changed from: package-private */
    public final Codeword[] getCodewords() {
        return this.codewords;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0058, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0059, code lost:
        if (r1 != null) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005f, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0060, code lost:
        r1.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0064, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0067, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String toString() {
        /*
            r11 = this;
            java.util.Formatter r0 = new java.util.Formatter
            r0.<init>()
            r1 = 0
            com.google.zxing.pdf417.decoder.Codeword[] r2 = r11.codewords     // Catch:{ all -> 0x0056 }
            int r3 = r2.length     // Catch:{ all -> 0x0056 }
            r4 = 0
            r5 = 0
            r6 = r4
        L_0x000c:
            if (r6 >= r3) goto L_0x004e
            r7 = r2[r6]     // Catch:{ all -> 0x0056 }
            r5 = r7
            r8 = 1
            if (r7 != 0) goto L_0x0025
            java.lang.String r7 = "%3d:    |   %n"
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ all -> 0x0056 }
            int r9 = r1 + 1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x0056 }
            r8[r4] = r1     // Catch:{ all -> 0x0056 }
            r0.format(r7, r8)     // Catch:{ all -> 0x0056 }
            r1 = r9
            goto L_0x004b
        L_0x0025:
            java.lang.String r7 = "%3d: %3d|%3d%n"
            r9 = 3
            java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch:{ all -> 0x0056 }
            int r10 = r1 + 1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x0056 }
            r9[r4] = r1     // Catch:{ all -> 0x0056 }
            int r1 = r5.getRowNumber()     // Catch:{ all -> 0x0056 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x0056 }
            r9[r8] = r1     // Catch:{ all -> 0x0056 }
            r1 = 2
            int r8 = r5.getValue()     // Catch:{ all -> 0x0056 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x0056 }
            r9[r1] = r8     // Catch:{ all -> 0x0056 }
            r0.format(r7, r9)     // Catch:{ all -> 0x0056 }
            r1 = r10
        L_0x004b:
            int r6 = r6 + 1
            goto L_0x000c
        L_0x004e:
            java.lang.String r2 = r0.toString()     // Catch:{ all -> 0x0056 }
            r0.close()
            return r2
        L_0x0056:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0058 }
        L_0x0058:
            r2 = move-exception
            if (r1 == 0) goto L_0x0064
            r0.close()     // Catch:{ all -> 0x005f }
            goto L_0x0067
        L_0x005f:
            r3 = move-exception
            r1.addSuppressed(r3)
            goto L_0x0067
        L_0x0064:
            r0.close()
        L_0x0067:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DetectionResultColumn.toString():java.lang.String");
    }
}
