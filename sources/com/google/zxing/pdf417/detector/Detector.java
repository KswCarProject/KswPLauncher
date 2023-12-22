package com.google.zxing.pdf417.detector;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class Detector {
    private static final int BARCODE_MIN_HEIGHT = 10;
    private static final float MAX_AVG_VARIANCE = 0.42f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.8f;
    private static final int MAX_PATTERN_DRIFT = 5;
    private static final int MAX_PIXEL_DRIFT = 3;
    private static final int ROW_STEP = 5;
    private static final int SKIPPED_ROW_COUNT_MAX = 25;
    private static final int[] INDEXES_START_PATTERN = {0, 4, 1, 5};
    private static final int[] INDEXES_STOP_PATTERN = {6, 2, 7, 3};
    private static final int[] START_PATTERN = {8, 1, 1, 1, 1, 1, 1, 3};
    private static final int[] STOP_PATTERN = {7, 1, 1, 3, 1, 1, 1, 2, 1};

    private Detector() {
    }

    public static PDF417DetectorResult detect(BinaryBitmap image, Map<DecodeHintType, ?> hints, boolean multiple) throws NotFoundException {
        BitMatrix bitMatrix = image.getBlackMatrix();
        List<ResultPoint[]> detect = detect(multiple, bitMatrix);
        List<ResultPoint[]> barcodeCoordinates = detect;
        if (detect.isEmpty()) {
            BitMatrix m72clone = bitMatrix.m72clone();
            bitMatrix = m72clone;
            m72clone.rotate180();
            barcodeCoordinates = detect(multiple, bitMatrix);
        }
        return new PDF417DetectorResult(bitMatrix, barcodeCoordinates);
    }

    private static List<ResultPoint[]> detect(boolean multiple, BitMatrix bitMatrix) {
        List<ResultPoint[]> barcodeCoordinates = new ArrayList<>();
        int row = 0;
        int column = 0;
        boolean foundBarcodeInRow = false;
        while (row < bitMatrix.getHeight()) {
            ResultPoint[] vertices = findVertices(bitMatrix, row, column);
            if (vertices[0] == null && vertices[3] == null) {
                if (!foundBarcodeInRow) {
                    break;
                }
                foundBarcodeInRow = false;
                column = 0;
                for (ResultPoint[] barcodeCoordinate : barcodeCoordinates) {
                    if (barcodeCoordinate[1] != null) {
                        row = (int) Math.max(row, barcodeCoordinate[1].getY());
                    }
                    if (barcodeCoordinate[3] != null) {
                        row = Math.max(row, (int) barcodeCoordinate[3].getY());
                    }
                }
                row += 5;
            } else {
                foundBarcodeInRow = true;
                barcodeCoordinates.add(vertices);
                if (!multiple) {
                    break;
                } else if (vertices[2] == null) {
                    column = (int) vertices[4].getX();
                    row = (int) vertices[4].getY();
                } else {
                    column = (int) vertices[2].getX();
                    row = (int) vertices[2].getY();
                }
            }
        }
        return barcodeCoordinates;
    }

    private static ResultPoint[] findVertices(BitMatrix matrix, int startRow, int startColumn) {
        int height = matrix.getHeight();
        int width = matrix.getWidth();
        ResultPoint[] result = new ResultPoint[8];
        copyToResult(result, findRowsWithPattern(matrix, height, width, startRow, startColumn, START_PATTERN), INDEXES_START_PATTERN);
        if (result[4] != null) {
            startColumn = (int) result[4].getX();
            startRow = (int) result[4].getY();
        }
        copyToResult(result, findRowsWithPattern(matrix, height, width, startRow, startColumn, STOP_PATTERN), INDEXES_STOP_PATTERN);
        return result;
    }

    private static void copyToResult(ResultPoint[] result, ResultPoint[] tmpResult, int[] destinationIndexes) {
        for (int i = 0; i < destinationIndexes.length; i++) {
            result[destinationIndexes[i]] = tmpResult[i];
        }
    }

    private static ResultPoint[] findRowsWithPattern(BitMatrix matrix, int height, int width, int startRow, int startColumn, int[] pattern) {
        int skippedRowCount;
        int stopRow;
        ResultPoint[] result = new ResultPoint[4];
        boolean found = false;
        int[] counters = new int[pattern.length];
        int startRow2 = startRow;
        int[] loc = null;
        while (true) {
            if (startRow2 >= height) {
                break;
            }
            int[] findGuardPattern = findGuardPattern(matrix, startColumn, startRow2, width, false, pattern, counters);
            loc = findGuardPattern;
            if (findGuardPattern != null) {
                int[] previousRowLoc = null;
                while (true) {
                    if (startRow2 <= 0) {
                        break;
                    }
                    startRow2--;
                    int[] findGuardPattern2 = findGuardPattern(matrix, startColumn, startRow2, width, false, pattern, counters);
                    previousRowLoc = findGuardPattern2;
                    if (findGuardPattern2 != null) {
                        loc = previousRowLoc;
                    } else {
                        startRow2++;
                        break;
                    }
                }
                result[0] = new ResultPoint(loc[0], startRow2);
                result[1] = new ResultPoint(loc[1], startRow2);
                found = true;
            } else {
                startRow2 += 5;
            }
        }
        int stopRow2 = startRow2 + 1;
        if (found) {
            int stopRow3 = stopRow2;
            int skippedRowCount2 = 0;
            int[] previousRowLoc2 = {(int) result[0].getX(), (int) result[1].getX()};
            int[] loc2 = null;
            while (true) {
                if (stopRow3 >= height) {
                    skippedRowCount = skippedRowCount2;
                    stopRow = stopRow3;
                    break;
                }
                skippedRowCount = skippedRowCount2;
                stopRow = stopRow3;
                int[] findGuardPattern3 = findGuardPattern(matrix, previousRowLoc2[0], stopRow3, width, false, pattern, counters);
                loc2 = findGuardPattern3;
                if (findGuardPattern3 != null && Math.abs(previousRowLoc2[0] - loc2[0]) < 5 && Math.abs(previousRowLoc2[1] - loc2[1]) < 5) {
                    previousRowLoc2 = loc2;
                    skippedRowCount2 = 0;
                } else if (skippedRowCount > 25) {
                    break;
                } else {
                    skippedRowCount2 = skippedRowCount + 1;
                }
                stopRow3 = stopRow + 1;
            }
            stopRow2 = stopRow - (skippedRowCount + 1);
            result[2] = new ResultPoint(previousRowLoc2[0], stopRow2);
            result[3] = new ResultPoint(previousRowLoc2[1], stopRow2);
        }
        if (stopRow2 - startRow2 < 10) {
            Arrays.fill(result, (Object) null);
        }
        return result;
    }

    private static int[] findGuardPattern(BitMatrix matrix, int column, int row, int width, boolean whiteFirst, int[] pattern, int[] counters) {
        Arrays.fill(counters, 0, counters.length, 0);
        int patternStart = column;
        int pixelDrift = 0;
        while (matrix.get(patternStart, row) && patternStart > 0) {
            int pixelDrift2 = pixelDrift + 1;
            if (pixelDrift >= 3) {
                break;
            }
            patternStart--;
            pixelDrift = pixelDrift2;
        }
        int x = patternStart;
        int counterPosition = 0;
        int patternLength = pattern.length;
        boolean isWhite = whiteFirst;
        while (true) {
            if (x < width) {
                if (matrix.get(x, row) != isWhite) {
                    counters[counterPosition] = counters[counterPosition] + 1;
                } else {
                    if (counterPosition == patternLength - 1) {
                        if (patternMatchVariance(counters, pattern, MAX_INDIVIDUAL_VARIANCE) < MAX_AVG_VARIANCE) {
                            return new int[]{patternStart, x};
                        }
                        patternStart += counters[0] + counters[1];
                        System.arraycopy(counters, 2, counters, 0, counterPosition - 1);
                        counters[counterPosition - 1] = 0;
                        counters[counterPosition] = 0;
                        counterPosition--;
                    } else {
                        counterPosition++;
                    }
                    counters[counterPosition] = 1;
                    isWhite = isWhite ? false : true;
                }
                x++;
            } else if (counterPosition != patternLength - 1 || patternMatchVariance(counters, pattern, MAX_INDIVIDUAL_VARIANCE) >= MAX_AVG_VARIANCE) {
                return null;
            } else {
                return new int[]{patternStart, x - 1};
            }
        }
    }

    private static float patternMatchVariance(int[] counters, int[] pattern, float maxIndividualVariance) {
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
        float unitBarWidth = total / patternLength;
        float maxIndividualVariance2 = maxIndividualVariance * unitBarWidth;
        float totalVariance = 0.0f;
        for (int x = 0; x < numCounters; x++) {
            int counter = counters[x];
            float scaledPattern = pattern[x] * unitBarWidth;
            float f = ((float) counter) > scaledPattern ? counter - scaledPattern : scaledPattern - counter;
            float variance = f;
            if (f > maxIndividualVariance2) {
                return Float.POSITIVE_INFINITY;
            }
            totalVariance += variance;
        }
        return totalVariance / total;
    }
}
