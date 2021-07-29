package com.google.zxing.common;

import com.google.zxing.Binarizer;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import java.lang.reflect.Array;

public final class HybridBinarizer extends GlobalHistogramBinarizer {
    private static final int BLOCK_SIZE = 8;
    private static final int BLOCK_SIZE_MASK = 7;
    private static final int BLOCK_SIZE_POWER = 3;
    private static final int MINIMUM_DIMENSION = 40;
    private static final int MIN_DYNAMIC_RANGE = 24;
    private BitMatrix matrix;

    public HybridBinarizer(LuminanceSource source) {
        super(source);
    }

    public BitMatrix getBlackMatrix() throws NotFoundException {
        int subWidth;
        int subHeight;
        BitMatrix bitMatrix = this.matrix;
        if (bitMatrix != null) {
            return bitMatrix;
        }
        LuminanceSource luminanceSource = getLuminanceSource();
        LuminanceSource source = luminanceSource;
        int width = luminanceSource.getWidth();
        int height = source.getHeight();
        if (width < 40 || height < 40) {
            this.matrix = super.getBlackMatrix();
        } else {
            byte[] luminances = source.getMatrix();
            int subWidth2 = width >> 3;
            if ((width & 7) != 0) {
                subWidth = subWidth2 + 1;
            } else {
                subWidth = subWidth2;
            }
            int subWidth3 = height >> 3;
            if ((height & 7) != 0) {
                subHeight = subWidth3 + 1;
            } else {
                subHeight = subWidth3;
            }
            int[][] blackPoints = calculateBlackPoints(luminances, subWidth, subHeight, width, height);
            BitMatrix newMatrix = new BitMatrix(width, height);
            calculateThresholdForBlock(luminances, subWidth, subHeight, width, height, blackPoints, newMatrix);
            this.matrix = newMatrix;
        }
        return this.matrix;
    }

    public Binarizer createBinarizer(LuminanceSource source) {
        return new HybridBinarizer(source);
    }

    private static void calculateThresholdForBlock(byte[] luminances, int subWidth, int subHeight, int width, int height, int[][] blackPoints, BitMatrix matrix2) {
        int yoffset;
        int xoffset;
        int i = subWidth;
        int i2 = subHeight;
        int maxYOffset = height - 8;
        int maxXOffset = width - 8;
        int y = 0;
        while (y < i2) {
            int i3 = y << 3;
            int yoffset2 = i3;
            if (i3 > maxYOffset) {
                yoffset = maxYOffset;
            } else {
                yoffset = yoffset2;
            }
            int top = cap(y, 2, i2 - 3);
            int x = 0;
            while (x < i) {
                int i4 = x << 3;
                int xoffset2 = i4;
                if (i4 > maxXOffset) {
                    xoffset = maxXOffset;
                } else {
                    xoffset = xoffset2;
                }
                int left = cap(x, 2, i - 3);
                int sum = 0;
                for (int z = -2; z <= 2; z++) {
                    int[] blackRow = blackPoints[top + z];
                    sum += blackRow[left - 2] + blackRow[left - 1] + blackRow[left] + blackRow[left + 1] + blackRow[left + 2];
                }
                thresholdBlock(luminances, xoffset, yoffset, sum / 25, width, matrix2);
                x++;
                int i5 = xoffset;
            }
            y++;
            int i6 = yoffset;
        }
    }

    private static int cap(int value, int min, int max) {
        if (value < min) {
            return min;
        }
        return value > max ? max : value;
    }

    private static void thresholdBlock(byte[] luminances, int xoffset, int yoffset, int threshold, int stride, BitMatrix matrix2) {
        int y = 0;
        int offset = (yoffset * stride) + xoffset;
        while (y < 8) {
            for (int x = 0; x < 8; x++) {
                if ((luminances[offset + x] & 255) <= threshold) {
                    matrix2.set(xoffset + x, yoffset + y);
                }
            }
            y++;
            offset += stride;
        }
    }

    private static int[][] calculateBlackPoints(byte[] luminances, int subWidth, int subHeight, int width, int height) {
        char c;
        int i = subWidth;
        int i2 = subHeight;
        int maxYOffset = height - 8;
        int maxXOffset = width - 8;
        char c2 = 2;
        int[] iArr = new int[2];
        boolean z = true;
        iArr[1] = i;
        iArr[0] = i2;
        int[][] blackPoints = (int[][]) Array.newInstance(int.class, iArr);
        int y = 0;
        while (y < i2) {
            int i3 = y << 3;
            int yoffset = i3;
            if (i3 > maxYOffset) {
                yoffset = maxYOffset;
            }
            int x = 0;
            while (x < i) {
                int i4 = x << 3;
                int xoffset = i4;
                if (i4 > maxXOffset) {
                    xoffset = maxXOffset;
                }
                int sum = 0;
                int min = 255;
                int max = 0;
                int yy = 0;
                int offset = (yoffset * width) + xoffset;
                while (true) {
                    if (yy >= 8) {
                        break;
                    }
                    int xx = 0;
                    for (int i5 = 8; xx < i5; i5 = 8) {
                        int pixel = luminances[offset + xx] & 255;
                        sum += pixel;
                        if (pixel < min) {
                            min = pixel;
                        }
                        if (pixel > max) {
                            max = pixel;
                        }
                        xx++;
                    }
                    if (max - min <= 24) {
                        yy++;
                        offset += width;
                    }
                    while (true) {
                        yy++;
                        offset += width;
                        if (yy >= 8) {
                            break;
                        }
                        int xx2 = 0;
                        for (int i6 = 8; xx2 < i6; i6 = 8) {
                            sum += luminances[offset + xx2] & 255;
                            xx2++;
                        }
                    }
                    yy++;
                    offset += width;
                }
                int average = sum >> 6;
                if (max - min <= 24) {
                    average = min / 2;
                    if (y <= 0 || x <= 0) {
                        c = 2;
                    } else {
                        c = 2;
                        int averageNeighborBlackPoint = ((blackPoints[y - 1][x] + (blackPoints[y][x - 1] * 2)) + blackPoints[y - 1][x - 1]) / 4;
                        if (min < averageNeighborBlackPoint) {
                            average = averageNeighborBlackPoint;
                        }
                    }
                } else {
                    c = 2;
                }
                blackPoints[y][x] = average;
                x++;
                z = true;
                c2 = c;
            }
            char c3 = c2;
            boolean z2 = z;
            y++;
            c2 = c3;
        }
        return blackPoints;
    }
}
