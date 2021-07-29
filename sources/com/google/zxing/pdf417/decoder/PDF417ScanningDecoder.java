package com.google.zxing.pdf417.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.pdf417.PDF417Common;
import com.google.zxing.pdf417.decoder.ec.ErrorCorrection;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class PDF417ScanningDecoder {
    private static final int CODEWORD_SKEW_SIZE = 2;
    private static final int MAX_EC_CODEWORDS = 512;
    private static final int MAX_ERRORS = 3;
    private static final ErrorCorrection errorCorrection = new ErrorCorrection();

    private PDF417ScanningDecoder() {
    }

    public static DecoderResult decode(BitMatrix image, ResultPoint imageTopLeft, ResultPoint imageBottomLeft, ResultPoint imageTopRight, ResultPoint imageBottomRight, int minCodewordWidth, int maxCodewordWidth) throws NotFoundException, FormatException, ChecksumException {
        int barcodeColumnCount;
        DetectionResultColumn detectionResultColumn;
        int barcodeColumnCount2;
        int barcodeColumn;
        int previousStartColumn;
        int previousStartColumn2;
        DetectionResultColumn detectionResultColumn2;
        int startColumn;
        BoundingBox boundingBox = new BoundingBox(image, imageTopLeft, imageBottomLeft, imageTopRight, imageBottomRight);
        DetectionResultRowIndicatorColumn leftRowIndicatorColumn = null;
        DetectionResultRowIndicatorColumn rightRowIndicatorColumn = null;
        DetectionResult detectionResult = null;
        int i = 0;
        while (true) {
            if (i >= 2) {
                break;
            }
            if (imageTopLeft != null) {
                leftRowIndicatorColumn = getRowIndicatorColumn(image, boundingBox, imageTopLeft, true, minCodewordWidth, maxCodewordWidth);
            }
            if (imageTopRight != null) {
                rightRowIndicatorColumn = getRowIndicatorColumn(image, boundingBox, imageTopRight, false, minCodewordWidth, maxCodewordWidth);
            }
            DetectionResult merge = merge(leftRowIndicatorColumn, rightRowIndicatorColumn);
            detectionResult = merge;
            if (merge == null) {
                throw NotFoundException.getNotFoundInstance();
            } else if (i != 0 || detectionResult.getBoundingBox() == null || (detectionResult.getBoundingBox().getMinY() >= boundingBox.getMinY() && detectionResult.getBoundingBox().getMaxY() <= boundingBox.getMaxY())) {
                detectionResult.setBoundingBox(boundingBox);
            } else {
                boundingBox = detectionResult.getBoundingBox();
                i++;
            }
        }
        detectionResult.setBoundingBox(boundingBox);
        boolean z = true;
        int maxBarcodeColumn = detectionResult.getBarcodeColumnCount() + 1;
        boolean z2 = false;
        detectionResult.setDetectionResultColumn(0, leftRowIndicatorColumn);
        detectionResult.setDetectionResultColumn(maxBarcodeColumn, rightRowIndicatorColumn);
        boolean leftToRight = leftRowIndicatorColumn != null;
        int maxCodewordWidth2 = 0;
        int barcodeColumnCount3 = 1;
        Codeword codeword = null;
        int startColumn2 = minCodewordWidth;
        int maxCodewordWidth3 = maxCodewordWidth;
        while (barcodeColumnCount3 <= maxBarcodeColumn) {
            int barcodeColumn2 = leftToRight ? barcodeColumnCount3 : maxBarcodeColumn - barcodeColumnCount3;
            if (detectionResult.getDetectionResultColumn(barcodeColumn2) == null) {
                if (barcodeColumn2 == 0 || barcodeColumn2 == maxBarcodeColumn) {
                    detectionResultColumn = new DetectionResultRowIndicatorColumn(boundingBox, barcodeColumn2 == 0 ? z : z2);
                } else {
                    detectionResultColumn = new DetectionResultColumn(boundingBox);
                }
                detectionResult.setDetectionResultColumn(barcodeColumn2, detectionResultColumn);
                int minCodewordWidth2 = startColumn2;
                int previousStartColumn3 = maxCodewordWidth2;
                int minCodewordWidth3 = maxCodewordWidth3;
                int imageRow = boundingBox.getMinY();
                Codeword codeword2 = codeword;
                int imageRow2 = -1;
                while (imageRow <= boundingBox.getMaxY()) {
                    int startColumn3 = getStartColumn(detectionResult, barcodeColumn2, imageRow, leftToRight);
                    previousStartColumn3 = startColumn3;
                    if (startColumn3 >= 0 && previousStartColumn3 <= boundingBox.getMaxX()) {
                        startColumn = previousStartColumn3;
                    } else if (imageRow2 != -1) {
                        startColumn = imageRow2;
                    } else {
                        previousStartColumn = imageRow2;
                        detectionResultColumn2 = detectionResultColumn;
                        barcodeColumn = barcodeColumn2;
                        barcodeColumnCount2 = barcodeColumnCount3;
                        previousStartColumn2 = imageRow;
                        int i2 = minCodewordWidth3;
                        imageRow = previousStartColumn2 + 1;
                        detectionResultColumn = detectionResultColumn2;
                        imageRow2 = previousStartColumn;
                        barcodeColumn2 = barcodeColumn;
                        barcodeColumnCount3 = barcodeColumnCount2;
                    }
                    int imageRow3 = imageRow;
                    previousStartColumn = imageRow2;
                    int maxCodewordWidth4 = minCodewordWidth3;
                    int minCodewordWidth4 = minCodewordWidth2;
                    detectionResultColumn2 = detectionResultColumn;
                    barcodeColumn = barcodeColumn2;
                    barcodeColumnCount2 = barcodeColumnCount3;
                    Codeword detectCodeword = detectCodeword(image, boundingBox.getMinX(), boundingBox.getMaxX(), leftToRight, startColumn, imageRow3, minCodewordWidth4, maxCodewordWidth4);
                    Codeword codeword3 = codeword2;
                    Codeword codeword4 = detectCodeword;
                    if (detectCodeword != null) {
                        previousStartColumn2 = imageRow3;
                        detectionResultColumn2.setCodeword(previousStartColumn2, codeword4);
                        previousStartColumn3 = startColumn;
                        previousStartColumn = previousStartColumn3;
                        codeword2 = codeword4;
                        minCodewordWidth2 = Math.min(minCodewordWidth4, codeword4.getWidth());
                        minCodewordWidth3 = Math.max(maxCodewordWidth4, codeword4.getWidth());
                    } else {
                        previousStartColumn2 = imageRow3;
                        minCodewordWidth2 = minCodewordWidth4;
                        previousStartColumn3 = startColumn;
                        codeword2 = codeword4;
                        minCodewordWidth3 = maxCodewordWidth4;
                    }
                    imageRow = previousStartColumn2 + 1;
                    detectionResultColumn = detectionResultColumn2;
                    imageRow2 = previousStartColumn;
                    barcodeColumn2 = barcodeColumn;
                    barcodeColumnCount3 = barcodeColumnCount2;
                }
                int previousStartColumn4 = imageRow2;
                DetectionResultColumn detectionResultColumn3 = detectionResultColumn;
                int i3 = barcodeColumn2;
                barcodeColumnCount = barcodeColumnCount3;
                int previousStartColumn5 = imageRow;
                int maxCodewordWidth5 = minCodewordWidth3;
                maxCodewordWidth2 = previousStartColumn3;
                startColumn2 = minCodewordWidth2;
                maxCodewordWidth3 = maxCodewordWidth5;
                codeword = codeword2;
            } else {
                barcodeColumnCount = barcodeColumnCount3;
            }
            barcodeColumnCount3 = barcodeColumnCount + 1;
            z = true;
            z2 = false;
        }
        return createDecoderResult(detectionResult);
    }

    private static DetectionResult merge(DetectionResultRowIndicatorColumn leftRowIndicatorColumn, DetectionResultRowIndicatorColumn rightRowIndicatorColumn) throws NotFoundException {
        if (leftRowIndicatorColumn == null && rightRowIndicatorColumn == null) {
            return null;
        }
        BarcodeMetadata barcodeMetadata = getBarcodeMetadata(leftRowIndicatorColumn, rightRowIndicatorColumn);
        BarcodeMetadata barcodeMetadata2 = barcodeMetadata;
        if (barcodeMetadata == null) {
            return null;
        }
        return new DetectionResult(barcodeMetadata2, BoundingBox.merge(adjustBoundingBox(leftRowIndicatorColumn), adjustBoundingBox(rightRowIndicatorColumn)));
    }

    private static BoundingBox adjustBoundingBox(DetectionResultRowIndicatorColumn rowIndicatorColumn) throws NotFoundException {
        if (rowIndicatorColumn == null) {
            return null;
        }
        int[] rowHeights = rowIndicatorColumn.getRowHeights();
        int[] rowHeights2 = rowHeights;
        if (rowHeights == null) {
            return null;
        }
        int maxRowHeight = getMax(rowHeights2);
        int missingStartRows = 0;
        for (int rowHeight : rowHeights2) {
            missingStartRows += maxRowHeight - rowHeight;
            if (rowHeight > 0) {
                break;
            }
        }
        Codeword[] codewords = rowIndicatorColumn.getCodewords();
        int row = 0;
        while (missingStartRows > 0 && codewords[row] == null) {
            missingStartRows--;
            row++;
        }
        int missingEndRows = 0;
        for (int row2 = rowHeights2.length - 1; row2 >= 0; row2--) {
            missingEndRows += maxRowHeight - rowHeights2[row2];
            if (rowHeights2[row2] > 0) {
                break;
            }
        }
        int row3 = codewords.length - 1;
        while (missingEndRows > 0 && codewords[row3] == null) {
            missingEndRows--;
            row3--;
        }
        return rowIndicatorColumn.getBoundingBox().addMissingRows(missingStartRows, missingEndRows, rowIndicatorColumn.isLeft());
    }

    private static int getMax(int[] values) {
        int maxValue = -1;
        for (int value : values) {
            maxValue = Math.max(maxValue, value);
        }
        return maxValue;
    }

    private static BarcodeMetadata getBarcodeMetadata(DetectionResultRowIndicatorColumn leftRowIndicatorColumn, DetectionResultRowIndicatorColumn rightRowIndicatorColumn) {
        if (leftRowIndicatorColumn != null) {
            BarcodeMetadata barcodeMetadata = leftRowIndicatorColumn.getBarcodeMetadata();
            BarcodeMetadata leftBarcodeMetadata = barcodeMetadata;
            if (barcodeMetadata != null) {
                if (rightRowIndicatorColumn != null) {
                    BarcodeMetadata barcodeMetadata2 = rightRowIndicatorColumn.getBarcodeMetadata();
                    BarcodeMetadata rightBarcodeMetadata = barcodeMetadata2;
                    if (barcodeMetadata2 == null || leftBarcodeMetadata.getColumnCount() == rightBarcodeMetadata.getColumnCount() || leftBarcodeMetadata.getErrorCorrectionLevel() == rightBarcodeMetadata.getErrorCorrectionLevel() || leftBarcodeMetadata.getRowCount() == rightBarcodeMetadata.getRowCount()) {
                        return leftBarcodeMetadata;
                    }
                    return null;
                }
                return leftBarcodeMetadata;
            }
        }
        if (rightRowIndicatorColumn == null) {
            return null;
        }
        return rightRowIndicatorColumn.getBarcodeMetadata();
    }

    private static DetectionResultRowIndicatorColumn getRowIndicatorColumn(BitMatrix image, BoundingBox boundingBox, ResultPoint startPoint, boolean leftToRight, int minCodewordWidth, int maxCodewordWidth) {
        boolean z = leftToRight;
        DetectionResultRowIndicatorColumn rowIndicatorColumn = new DetectionResultRowIndicatorColumn(boundingBox, z);
        Codeword codeword = null;
        int i = 0;
        while (i < 2) {
            int increment = i == 0 ? 1 : -1;
            int startColumn = (int) startPoint.getX();
            Codeword codeword2 = codeword;
            int imageRow = (int) startPoint.getY();
            while (imageRow <= boundingBox.getMaxY() && imageRow >= boundingBox.getMinY()) {
                Codeword detectCodeword = detectCodeword(image, 0, image.getWidth(), leftToRight, startColumn, imageRow, minCodewordWidth, maxCodewordWidth);
                Codeword codeword3 = codeword2;
                codeword2 = detectCodeword;
                if (detectCodeword != null) {
                    rowIndicatorColumn.setCodeword(imageRow, codeword2);
                    if (z) {
                        startColumn = codeword2.getStartX();
                    } else {
                        startColumn = codeword2.getEndX();
                    }
                }
                imageRow += increment;
            }
            i++;
            codeword = codeword2;
        }
        return rowIndicatorColumn;
    }

    private static void adjustCodewordCount(DetectionResult detectionResult, BarcodeValue[][] barcodeMatrix) throws NotFoundException {
        BarcodeValue barcodeValue = barcodeMatrix[0][1];
        BarcodeValue barcodeMatrix01 = barcodeValue;
        int[] numberOfCodewords = barcodeValue.getValue();
        int calculatedNumberOfCodewords = (detectionResult.getBarcodeColumnCount() * detectionResult.getBarcodeRowCount()) - getNumberOfECCodeWords(detectionResult.getBarcodeECLevel());
        if (numberOfCodewords.length == 0) {
            if (calculatedNumberOfCodewords <= 0 || calculatedNumberOfCodewords > 928) {
                throw NotFoundException.getNotFoundInstance();
            }
            barcodeMatrix01.setValue(calculatedNumberOfCodewords);
        } else if (numberOfCodewords[0] != calculatedNumberOfCodewords) {
            barcodeMatrix01.setValue(calculatedNumberOfCodewords);
        }
    }

    private static DecoderResult createDecoderResult(DetectionResult detectionResult) throws FormatException, ChecksumException, NotFoundException {
        BarcodeValue[][] barcodeMatrix = createBarcodeMatrix(detectionResult);
        adjustCodewordCount(detectionResult, barcodeMatrix);
        Collection<Integer> erasures = new ArrayList<>();
        int[] codewords = new int[(detectionResult.getBarcodeRowCount() * detectionResult.getBarcodeColumnCount())];
        List<int[]> ambiguousIndexValuesList = new ArrayList<>();
        List<Integer> ambiguousIndexesList = new ArrayList<>();
        for (int row = 0; row < detectionResult.getBarcodeRowCount(); row++) {
            for (int column = 0; column < detectionResult.getBarcodeColumnCount(); column++) {
                int[] values = barcodeMatrix[row][column + 1].getValue();
                int codewordIndex = (detectionResult.getBarcodeColumnCount() * row) + column;
                if (values.length == 0) {
                    erasures.add(Integer.valueOf(codewordIndex));
                } else if (values.length == 1) {
                    codewords[codewordIndex] = values[0];
                } else {
                    ambiguousIndexesList.add(Integer.valueOf(codewordIndex));
                    ambiguousIndexValuesList.add(values);
                }
            }
        }
        int[][] ambiguousIndexValues = new int[ambiguousIndexValuesList.size()][];
        for (int i = 0; i < ambiguousIndexValues.length; i++) {
            ambiguousIndexValues[i] = ambiguousIndexValuesList.get(i);
        }
        return createDecoderResultFromAmbiguousValues(detectionResult.getBarcodeECLevel(), codewords, PDF417Common.toIntArray(erasures), PDF417Common.toIntArray(ambiguousIndexesList), ambiguousIndexValues);
    }

    private static DecoderResult createDecoderResultFromAmbiguousValues(int ecLevel, int[] codewords, int[] erasureArray, int[] ambiguousIndexes, int[][] ambiguousIndexValues) throws FormatException, ChecksumException {
        int[] ambiguousIndexCount = new int[ambiguousIndexes.length];
        int i = 100;
        while (true) {
            int tries = i - 1;
            if (i > 0) {
                for (int i2 = 0; i2 < ambiguousIndexCount.length; i2++) {
                    codewords[ambiguousIndexes[i2]] = ambiguousIndexValues[i2][ambiguousIndexCount[i2]];
                }
                try {
                    return decodeCodewords(codewords, ecLevel, erasureArray);
                } catch (ChecksumException e) {
                    if (ambiguousIndexCount.length != 0) {
                        int i3 = 0;
                        while (true) {
                            if (i3 >= ambiguousIndexCount.length) {
                                break;
                            } else if (ambiguousIndexCount[i3] < ambiguousIndexValues[i3].length - 1) {
                                ambiguousIndexCount[i3] = ambiguousIndexCount[i3] + 1;
                                break;
                            } else {
                                ambiguousIndexCount[i3] = 0;
                                if (i3 != ambiguousIndexCount.length - 1) {
                                    i3++;
                                } else {
                                    throw ChecksumException.getChecksumInstance();
                                }
                            }
                        }
                        i = tries;
                    } else {
                        throw ChecksumException.getChecksumInstance();
                    }
                }
            } else {
                throw ChecksumException.getChecksumInstance();
            }
        }
    }

    private static BarcodeValue[][] createBarcodeMatrix(DetectionResult detectionResult) {
        int barcodeRowCount = detectionResult.getBarcodeRowCount();
        int[] iArr = new int[2];
        iArr[1] = detectionResult.getBarcodeColumnCount() + 2;
        iArr[0] = barcodeRowCount;
        BarcodeValue[][] barcodeMatrix = (BarcodeValue[][]) Array.newInstance(BarcodeValue.class, iArr);
        for (int row = 0; row < barcodeMatrix.length; row++) {
            for (int column = 0; column < barcodeMatrix[row].length; column++) {
                barcodeMatrix[row][column] = new BarcodeValue();
            }
        }
        int column2 = 0;
        for (DetectionResultColumn detectionResultColumn : detectionResult.getDetectionResultColumns()) {
            DetectionResultColumn detectionResultColumn2 = detectionResultColumn;
            if (detectionResultColumn != null) {
                for (Codeword codeword : detectionResultColumn2.getCodewords()) {
                    Codeword codeword2 = codeword;
                    if (codeword != null) {
                        int rowNumber = codeword2.getRowNumber();
                        int rowNumber2 = rowNumber;
                        if (rowNumber >= 0 && rowNumber2 < barcodeMatrix.length) {
                            barcodeMatrix[rowNumber2][column2].setValue(codeword2.getValue());
                        }
                    }
                }
            }
            column2++;
        }
        return barcodeMatrix;
    }

    private static boolean isValidBarcodeColumn(DetectionResult detectionResult, int barcodeColumn) {
        return barcodeColumn >= 0 && barcodeColumn <= detectionResult.getBarcodeColumnCount() + 1;
    }

    private static int getStartColumn(DetectionResult detectionResult, int barcodeColumn, int imageRow, boolean leftToRight) {
        int offset = leftToRight ? 1 : -1;
        Codeword codeword = null;
        if (isValidBarcodeColumn(detectionResult, barcodeColumn - offset)) {
            codeword = detectionResult.getDetectionResultColumn(barcodeColumn - offset).getCodeword(imageRow);
        }
        if (codeword != null) {
            return leftToRight ? codeword.getEndX() : codeword.getStartX();
        }
        Codeword codewordNearby = detectionResult.getDetectionResultColumn(barcodeColumn).getCodewordNearby(imageRow);
        Codeword codeword2 = codewordNearby;
        if (codewordNearby != null) {
            return leftToRight ? codeword2.getStartX() : codeword2.getEndX();
        }
        if (isValidBarcodeColumn(detectionResult, barcodeColumn - offset)) {
            codeword2 = detectionResult.getDetectionResultColumn(barcodeColumn - offset).getCodewordNearby(imageRow);
        }
        if (codeword2 != null) {
            return leftToRight ? codeword2.getEndX() : codeword2.getStartX();
        }
        int skippedColumns = 0;
        while (isValidBarcodeColumn(detectionResult, barcodeColumn - offset)) {
            barcodeColumn -= offset;
            for (Codeword codeword3 : detectionResult.getDetectionResultColumn(barcodeColumn).getCodewords()) {
                Codeword previousRowCodeword = codeword3;
                if (codeword3 != null) {
                    return (leftToRight ? previousRowCodeword.getEndX() : previousRowCodeword.getStartX()) + (offset * skippedColumns * (previousRowCodeword.getEndX() - previousRowCodeword.getStartX()));
                }
            }
            skippedColumns++;
        }
        BoundingBox boundingBox = detectionResult.getBoundingBox();
        return leftToRight ? boundingBox.getMinX() : boundingBox.getMaxX();
    }

    private static Codeword detectCodeword(BitMatrix image, int minColumn, int maxColumn, boolean leftToRight, int startColumn, int imageRow, int minCodewordWidth, int maxCodewordWidth) {
        int endColumn;
        int startColumn2 = adjustCodewordStartColumn(image, minColumn, maxColumn, leftToRight, startColumn, imageRow);
        int[] moduleBitCount = getModuleBitCount(image, minColumn, maxColumn, leftToRight, startColumn2, imageRow);
        int[] moduleBitCount2 = moduleBitCount;
        if (moduleBitCount == null) {
            return null;
        }
        int codewordBitCount = MathUtils.sum(moduleBitCount2);
        if (leftToRight) {
            endColumn = startColumn2 + codewordBitCount;
        } else {
            for (int i = 0; i < moduleBitCount2.length / 2; i++) {
                int tmpCount = moduleBitCount2[i];
                moduleBitCount2[i] = moduleBitCount2[(moduleBitCount2.length - 1) - i];
                moduleBitCount2[(moduleBitCount2.length - 1) - i] = tmpCount;
            }
            endColumn = startColumn2;
            startColumn2 -= codewordBitCount;
        }
        if (!checkCodewordSkew(codewordBitCount, minCodewordWidth, maxCodewordWidth)) {
            return null;
        }
        int decodedValue = PDF417CodewordDecoder.getDecodedValue(moduleBitCount2);
        int decodedValue2 = decodedValue;
        int codeword = PDF417Common.getCodeword(decodedValue);
        int codeword2 = codeword;
        if (codeword == -1) {
            return null;
        }
        return new Codeword(startColumn2, endColumn, getCodewordBucketNumber(decodedValue2), codeword2);
    }

    private static int[] getModuleBitCount(BitMatrix image, int minColumn, int maxColumn, boolean leftToRight, int startColumn, int imageRow) {
        int imageColumn = startColumn;
        int[] moduleBitCount = new int[8];
        int moduleNumber = 0;
        int increment = leftToRight ? 1 : -1;
        boolean previousPixelValue = leftToRight;
        while (true) {
            if (!leftToRight) {
                if (imageColumn < minColumn) {
                    break;
                }
            } else if (imageColumn >= maxColumn) {
                break;
            }
            if (moduleNumber >= 8) {
                break;
            } else if (image.get(imageColumn, imageRow) == previousPixelValue) {
                moduleBitCount[moduleNumber] = moduleBitCount[moduleNumber] + 1;
                imageColumn += increment;
            } else {
                moduleNumber++;
                previousPixelValue = !previousPixelValue;
            }
        }
        if (moduleNumber != 8) {
            if (!(imageColumn == (leftToRight ? maxColumn : minColumn) && moduleNumber == 7)) {
                return null;
            }
        }
        return moduleBitCount;
    }

    private static int getNumberOfECCodeWords(int barcodeECLevel) {
        return 2 << barcodeECLevel;
    }

    private static int adjustCodewordStartColumn(BitMatrix image, int minColumn, int maxColumn, boolean leftToRight, int codewordStartColumn, int imageRow) {
        int correctedStartColumn = codewordStartColumn;
        int increment = leftToRight ? -1 : 1;
        for (int i = 0; i < 2; i++) {
            while (true) {
                if (!leftToRight) {
                    if (correctedStartColumn >= maxColumn) {
                        break;
                    }
                } else if (correctedStartColumn < minColumn) {
                    break;
                }
                if (leftToRight != image.get(correctedStartColumn, imageRow)) {
                    break;
                } else if (Math.abs(codewordStartColumn - correctedStartColumn) > 2) {
                    return codewordStartColumn;
                } else {
                    correctedStartColumn += increment;
                }
            }
            increment = -increment;
            leftToRight = !leftToRight;
        }
        return correctedStartColumn;
    }

    private static boolean checkCodewordSkew(int codewordSize, int minCodewordWidth, int maxCodewordWidth) {
        return minCodewordWidth + -2 <= codewordSize && codewordSize <= maxCodewordWidth + 2;
    }

    private static DecoderResult decodeCodewords(int[] codewords, int ecLevel, int[] erasures) throws FormatException, ChecksumException {
        if (codewords.length != 0) {
            int numECCodewords = 1 << (ecLevel + 1);
            int correctedErrorsCount = correctErrors(codewords, erasures, numECCodewords);
            verifyCodewordCount(codewords, numECCodewords);
            DecoderResult decode = DecodedBitStreamParser.decode(codewords, String.valueOf(ecLevel));
            DecoderResult decoderResult = decode;
            decode.setErrorsCorrected(Integer.valueOf(correctedErrorsCount));
            decoderResult.setErasures(Integer.valueOf(erasures.length));
            return decoderResult;
        }
        throw FormatException.getFormatInstance();
    }

    private static int correctErrors(int[] codewords, int[] erasures, int numECCodewords) throws ChecksumException {
        if ((erasures == null || erasures.length <= (numECCodewords / 2) + 3) && numECCodewords >= 0 && numECCodewords <= 512) {
            return errorCorrection.decode(codewords, numECCodewords, erasures);
        }
        throw ChecksumException.getChecksumInstance();
    }

    private static void verifyCodewordCount(int[] codewords, int numECCodewords) throws FormatException {
        if (codewords.length >= 4) {
            int i = codewords[0];
            int numberOfCodewords = i;
            if (i > codewords.length) {
                throw FormatException.getFormatInstance();
            } else if (numberOfCodewords != 0) {
            } else {
                if (numECCodewords < codewords.length) {
                    codewords[0] = codewords.length - numECCodewords;
                    return;
                }
                throw FormatException.getFormatInstance();
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private static int[] getBitCountForCodeword(int codeword) {
        int[] result = new int[8];
        int previousValue = 0;
        int i = 7;
        while (true) {
            if ((codeword & 1) != previousValue) {
                previousValue = codeword & 1;
                i--;
                if (i < 0) {
                    return result;
                }
            }
            result[i] = result[i] + 1;
            codeword >>= 1;
        }
    }

    private static int getCodewordBucketNumber(int codeword) {
        return getCodewordBucketNumber(getBitCountForCodeword(codeword));
    }

    private static int getCodewordBucketNumber(int[] moduleBitCount) {
        return ((((moduleBitCount[0] - moduleBitCount[2]) + moduleBitCount[4]) - moduleBitCount[6]) + 9) % 9;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0069, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x006a, code lost:
        if (r1 != null) goto L_0x006c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0070, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0071, code lost:
        r1.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0075, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0078, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String toString(com.google.zxing.pdf417.decoder.BarcodeValue[][] r10) {
        /*
            java.util.Formatter r0 = new java.util.Formatter
            r0.<init>()
            r1 = 0
            r2 = 0
            r3 = r2
        L_0x0008:
            int r4 = r10.length     // Catch:{ all -> 0x0067 }
            if (r1 >= r4) goto L_0x005f
            java.lang.String r4 = "Row %2d: "
            r5 = 1
            java.lang.Object[] r6 = new java.lang.Object[r5]     // Catch:{ all -> 0x0067 }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x0067 }
            r8 = 0
            r6[r8] = r7     // Catch:{ all -> 0x0067 }
            r0.format(r4, r6)     // Catch:{ all -> 0x0067 }
            r4 = 0
        L_0x001b:
            r6 = r10[r1]     // Catch:{ all -> 0x0067 }
            int r6 = r6.length     // Catch:{ all -> 0x0067 }
            if (r4 >= r6) goto L_0x0055
            r6 = r10[r1]     // Catch:{ all -> 0x0067 }
            r6 = r6[r4]     // Catch:{ all -> 0x0067 }
            r3 = r6
            int[] r6 = r6.getValue()     // Catch:{ all -> 0x0067 }
            int r6 = r6.length     // Catch:{ all -> 0x0067 }
            if (r6 != 0) goto L_0x0032
            java.lang.String r6 = "        "
            r0.format(r6, r2)     // Catch:{ all -> 0x0067 }
            goto L_0x0052
        L_0x0032:
            java.lang.String r6 = "%4d(%2d)"
            r7 = 2
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ all -> 0x0067 }
            int[] r9 = r3.getValue()     // Catch:{ all -> 0x0067 }
            r9 = r9[r8]     // Catch:{ all -> 0x0067 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x0067 }
            r7[r8] = r9     // Catch:{ all -> 0x0067 }
            int[] r9 = r3.getValue()     // Catch:{ all -> 0x0067 }
            r9 = r9[r8]     // Catch:{ all -> 0x0067 }
            java.lang.Integer r9 = r3.getConfidence(r9)     // Catch:{ all -> 0x0067 }
            r7[r5] = r9     // Catch:{ all -> 0x0067 }
            r0.format(r6, r7)     // Catch:{ all -> 0x0067 }
        L_0x0052:
            int r4 = r4 + 1
            goto L_0x001b
        L_0x0055:
            java.lang.String r4 = "%n"
            java.lang.Object[] r5 = new java.lang.Object[r8]     // Catch:{ all -> 0x0067 }
            r0.format(r4, r5)     // Catch:{ all -> 0x0067 }
            int r1 = r1 + 1
            goto L_0x0008
        L_0x005f:
            java.lang.String r1 = r0.toString()     // Catch:{ all -> 0x0067 }
            r0.close()
            return r1
        L_0x0067:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0069 }
        L_0x0069:
            r2 = move-exception
            if (r1 == 0) goto L_0x0075
            r0.close()     // Catch:{ all -> 0x0070 }
            goto L_0x0078
        L_0x0070:
            r3 = move-exception
            r1.addSuppressed(r3)
            goto L_0x0078
        L_0x0075:
            r0.close()
        L_0x0078:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.PDF417ScanningDecoder.toString(com.google.zxing.pdf417.decoder.BarcodeValue[][]):java.lang.String");
    }
}
