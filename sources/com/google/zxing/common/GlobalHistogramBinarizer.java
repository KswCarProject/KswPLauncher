package com.google.zxing.common;

import com.google.zxing.Binarizer;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import kotlin.UByte;

public class GlobalHistogramBinarizer extends Binarizer {
    private static final byte[] EMPTY = new byte[0];
    private static final int LUMINANCE_BITS = 5;
    private static final int LUMINANCE_BUCKETS = 32;
    private static final int LUMINANCE_SHIFT = 3;
    private final int[] buckets = new int[32];
    private byte[] luminances = EMPTY;

    public GlobalHistogramBinarizer(LuminanceSource source) {
        super(source);
    }

    public BitArray getBlackRow(int y, BitArray row) throws NotFoundException {
        LuminanceSource luminanceSource = getLuminanceSource();
        LuminanceSource source = luminanceSource;
        int width = luminanceSource.getWidth();
        if (row == null || row.getSize() < width) {
            row = new BitArray(width);
        } else {
            row.clear();
        }
        initArrays(width);
        byte[] localLuminances = source.getRow(y, this.luminances);
        int[] localBuckets = this.buckets;
        for (int x = 0; x < width; x++) {
            int i = (localLuminances[x] & UByte.MAX_VALUE) >> 3;
            localBuckets[i] = localBuckets[i] + 1;
        }
        int blackPoint = estimateBlackPoint(localBuckets);
        if (width < 3) {
            for (int x2 = 0; x2 < width; x2++) {
                if ((localLuminances[x2] & UByte.MAX_VALUE) < blackPoint) {
                    row.set(x2);
                }
            }
        } else {
            int left = localLuminances[0] & 255;
            int center = localLuminances[1] & 255;
            for (int x3 = 1; x3 < width - 1; x3++) {
                int right = localLuminances[x3 + 1] & 255;
                if ((((center << 2) - left) - right) / 2 < blackPoint) {
                    row.set(x3);
                }
                left = center;
                center = right;
            }
        }
        return row;
    }

    public BitMatrix getBlackMatrix() throws NotFoundException {
        LuminanceSource luminanceSource = getLuminanceSource();
        LuminanceSource source = luminanceSource;
        int width = luminanceSource.getWidth();
        int height = source.getHeight();
        BitMatrix matrix = new BitMatrix(width, height);
        initArrays(width);
        int[] localBuckets = this.buckets;
        for (int y = 1; y < 5; y++) {
            byte[] localLuminances = source.getRow((height * y) / 5, this.luminances);
            int right = (width << 2) / 5;
            for (int x = width / 5; x < right; x++) {
                int i = (localLuminances[x] & 255) >> 3;
                localBuckets[i] = localBuckets[i] + 1;
            }
        }
        int y2 = estimateBlackPoint(localBuckets);
        byte[] localLuminances2 = source.getMatrix();
        for (int y3 = 0; y3 < height; y3++) {
            int offset = y3 * width;
            for (int x2 = 0; x2 < width; x2++) {
                if ((localLuminances2[offset + x2] & UByte.MAX_VALUE) < y2) {
                    matrix.set(x2, y3);
                }
            }
        }
        return matrix;
    }

    public Binarizer createBinarizer(LuminanceSource source) {
        return new GlobalHistogramBinarizer(source);
    }

    private void initArrays(int luminanceSize) {
        if (this.luminances.length < luminanceSize) {
            this.luminances = new byte[luminanceSize];
        }
        for (int x = 0; x < 32; x++) {
            this.buckets[x] = 0;
        }
    }

    private static int estimateBlackPoint(int[] buckets2) throws NotFoundException {
        int numBuckets = buckets2.length;
        int maxBucketCount = 0;
        int firstPeak = 0;
        int firstPeakSize = 0;
        for (int x = 0; x < numBuckets; x++) {
            if (buckets2[x] > firstPeakSize) {
                firstPeak = x;
                firstPeakSize = buckets2[x];
            }
            if (buckets2[x] > maxBucketCount) {
                maxBucketCount = buckets2[x];
            }
        }
        int secondPeak = 0;
        int secondPeakScore = 0;
        for (int x2 = 0; x2 < numBuckets; x2++) {
            int distanceToBiggest = x2 - firstPeak;
            int i = buckets2[x2] * distanceToBiggest * distanceToBiggest;
            int score = i;
            if (i > secondPeakScore) {
                secondPeak = x2;
                secondPeakScore = score;
            }
        }
        if (firstPeak > secondPeak) {
            int temp = firstPeak;
            firstPeak = secondPeak;
            secondPeak = temp;
        }
        if (secondPeak - firstPeak > numBuckets / 16) {
            int bestValley = secondPeak - 1;
            int bestValleyScore = -1;
            for (int x3 = secondPeak - 1; x3 > firstPeak; x3--) {
                int i2 = x3 - firstPeak;
                int i3 = i2 * i2 * (secondPeak - x3) * (maxBucketCount - buckets2[x3]);
                int score2 = i3;
                if (i3 > bestValleyScore) {
                    bestValley = x3;
                    bestValleyScore = score2;
                }
            }
            return bestValley << 3;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
