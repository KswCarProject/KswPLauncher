package com.google.zxing.pdf417.decoder;

import com.google.zxing.ResultPoint;

final class DetectionResultRowIndicatorColumn extends DetectionResultColumn {
    private final boolean isLeft;

    DetectionResultRowIndicatorColumn(BoundingBox boundingBox, boolean isLeft2) {
        super(boundingBox);
        this.isLeft = isLeft2;
    }

    private void setRowNumbers() {
        for (Codeword codeword : getCodewords()) {
            Codeword codeword2 = codeword;
            if (codeword != null) {
                codeword2.setRowNumberAsRowIndicatorColumn();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void adjustCompleteIndicatorColumnRowNumbers(BarcodeMetadata barcodeMetadata) {
        int checkedRows;
        Codeword[] codewords = getCodewords();
        setRowNumbers();
        removeIncorrectCodewords(codewords, barcodeMetadata);
        BoundingBox boundingBox = getBoundingBox();
        ResultPoint top = this.isLeft ? boundingBox.getTopLeft() : boundingBox.getTopRight();
        ResultPoint bottom = this.isLeft ? boundingBox.getBottomLeft() : boundingBox.getBottomRight();
        int firstRow = imageRowToCodewordIndex((int) top.getY());
        int lastRow = imageRowToCodewordIndex((int) bottom.getY());
        int barcodeRow = -1;
        int maxRowHeight = 1;
        int currentRowHeight = 0;
        int codewordsRow = firstRow;
        while (codewordsRow < lastRow) {
            if (codewords[codewordsRow] != null) {
                Codeword codeword = codewords[codewordsRow];
                Codeword codeword2 = codeword;
                int rowNumber = codeword.getRowNumber() - barcodeRow;
                int rowDifference = rowNumber;
                if (rowNumber == 0) {
                    currentRowHeight++;
                } else if (rowDifference == 1) {
                    maxRowHeight = Math.max(maxRowHeight, currentRowHeight);
                    currentRowHeight = 1;
                    barcodeRow = codeword2.getRowNumber();
                } else if (rowDifference < 0 || codeword2.getRowNumber() >= barcodeMetadata.getRowCount() || rowDifference > codewordsRow) {
                    codewords[codewordsRow] = null;
                } else {
                    if (maxRowHeight > 2) {
                        checkedRows = (maxRowHeight - 2) * rowDifference;
                    } else {
                        checkedRows = rowDifference;
                    }
                    boolean closePreviousCodewordFound = checkedRows >= codewordsRow;
                    for (int i = 1; i <= checkedRows && !closePreviousCodewordFound; i++) {
                        closePreviousCodewordFound = codewords[codewordsRow - i] != null;
                    }
                    if (closePreviousCodewordFound) {
                        codewords[codewordsRow] = null;
                    } else {
                        currentRowHeight = 1;
                        barcodeRow = codeword2.getRowNumber();
                    }
                }
            }
            codewordsRow++;
        }
    }

    /* access modifiers changed from: package-private */
    public int[] getRowHeights() {
        BarcodeMetadata barcodeMetadata = getBarcodeMetadata();
        BarcodeMetadata barcodeMetadata2 = barcodeMetadata;
        if (barcodeMetadata == null) {
            return null;
        }
        adjustIncompleteIndicatorColumnRowNumbers(barcodeMetadata2);
        int[] result = new int[barcodeMetadata2.getRowCount()];
        for (Codeword codeword : getCodewords()) {
            Codeword codeword2 = codeword;
            if (codeword != null) {
                int rowNumber = codeword2.getRowNumber();
                int rowNumber2 = rowNumber;
                if (rowNumber < result.length) {
                    result[rowNumber2] = result[rowNumber2] + 1;
                }
            }
        }
        return result;
    }

    private void adjustIncompleteIndicatorColumnRowNumbers(BarcodeMetadata barcodeMetadata) {
        BoundingBox boundingBox = getBoundingBox();
        ResultPoint top = this.isLeft ? boundingBox.getTopLeft() : boundingBox.getTopRight();
        ResultPoint bottom = this.isLeft ? boundingBox.getBottomLeft() : boundingBox.getBottomRight();
        int firstRow = imageRowToCodewordIndex((int) top.getY());
        int lastRow = imageRowToCodewordIndex((int) bottom.getY());
        Codeword[] codewords = getCodewords();
        int barcodeRow = -1;
        int maxRowHeight = 1;
        int currentRowHeight = 0;
        for (int codewordsRow = firstRow; codewordsRow < lastRow; codewordsRow++) {
            if (codewords[codewordsRow] != null) {
                Codeword codeword = codewords[codewordsRow];
                Codeword codeword2 = codeword;
                codeword.setRowNumberAsRowIndicatorColumn();
                int rowNumber = codeword2.getRowNumber() - barcodeRow;
                int rowDifference = rowNumber;
                if (rowNumber == 0) {
                    currentRowHeight++;
                } else if (rowDifference == 1) {
                    maxRowHeight = Math.max(maxRowHeight, currentRowHeight);
                    currentRowHeight = 1;
                    barcodeRow = codeword2.getRowNumber();
                } else if (codeword2.getRowNumber() >= barcodeMetadata.getRowCount()) {
                    codewords[codewordsRow] = null;
                } else {
                    barcodeRow = codeword2.getRowNumber();
                    currentRowHeight = 1;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public BarcodeMetadata getBarcodeMetadata() {
        Codeword[] codewords = getCodewords();
        BarcodeValue barcodeColumnCount = new BarcodeValue();
        BarcodeValue barcodeRowCountUpperPart = new BarcodeValue();
        BarcodeValue barcodeRowCountLowerPart = new BarcodeValue();
        BarcodeValue barcodeECLevel = new BarcodeValue();
        for (Codeword codeword : codewords) {
            Codeword codeword2 = codeword;
            if (codeword != null) {
                codeword2.setRowNumberAsRowIndicatorColumn();
                int rowIndicatorValue = codeword2.getValue() % 30;
                int codewordRowNumber = codeword2.getRowNumber();
                if (!this.isLeft) {
                    codewordRowNumber += 2;
                }
                switch (codewordRowNumber % 3) {
                    case 0:
                        barcodeRowCountUpperPart.setValue((rowIndicatorValue * 3) + 1);
                        break;
                    case 1:
                        barcodeECLevel.setValue(rowIndicatorValue / 3);
                        barcodeRowCountLowerPart.setValue(rowIndicatorValue % 3);
                        break;
                    case 2:
                        barcodeColumnCount.setValue(rowIndicatorValue + 1);
                        break;
                }
            }
        }
        if (barcodeColumnCount.getValue().length == 0 || barcodeRowCountUpperPart.getValue().length == 0 || barcodeRowCountLowerPart.getValue().length == 0 || barcodeECLevel.getValue().length == 0 || barcodeColumnCount.getValue()[0] <= 0 || barcodeRowCountUpperPart.getValue()[0] + barcodeRowCountLowerPart.getValue()[0] < 3 || barcodeRowCountUpperPart.getValue()[0] + barcodeRowCountLowerPart.getValue()[0] > 90) {
            return null;
        }
        BarcodeMetadata barcodeMetadata = new BarcodeMetadata(barcodeColumnCount.getValue()[0], barcodeRowCountUpperPart.getValue()[0], barcodeRowCountLowerPart.getValue()[0], barcodeECLevel.getValue()[0]);
        removeIncorrectCodewords(codewords, barcodeMetadata);
        return barcodeMetadata;
    }

    private void removeIncorrectCodewords(Codeword[] codewords, BarcodeMetadata barcodeMetadata) {
        for (int codewordRow = 0; codewordRow < codewords.length; codewordRow++) {
            Codeword codeword = codewords[codewordRow];
            if (codewords[codewordRow] != null) {
                int rowIndicatorValue = codeword.getValue() % 30;
                int rowNumber = codeword.getRowNumber();
                int codewordRowNumber = rowNumber;
                if (rowNumber <= barcodeMetadata.getRowCount()) {
                    if (!this.isLeft) {
                        codewordRowNumber += 2;
                    }
                    switch (codewordRowNumber % 3) {
                        case 0:
                            if ((rowIndicatorValue * 3) + 1 == barcodeMetadata.getRowCountUpperPart()) {
                                break;
                            } else {
                                codewords[codewordRow] = null;
                                break;
                            }
                        case 1:
                            if (rowIndicatorValue / 3 != barcodeMetadata.getErrorCorrectionLevel() || rowIndicatorValue % 3 != barcodeMetadata.getRowCountLowerPart()) {
                                codewords[codewordRow] = null;
                                break;
                            } else {
                                break;
                            }
                        case 2:
                            if (rowIndicatorValue + 1 == barcodeMetadata.getColumnCount()) {
                                break;
                            } else {
                                codewords[codewordRow] = null;
                                break;
                            }
                    }
                } else {
                    codewords[codewordRow] = null;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isLeft() {
        return this.isLeft;
    }

    public String toString() {
        return "IsLeft: " + this.isLeft + 10 + super.toString();
    }
}
