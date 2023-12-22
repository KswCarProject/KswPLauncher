package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

@Deprecated
/* loaded from: classes.dex */
public final class MonochromeRectangleDetector {
    private static final int MAX_MODULES = 32;
    private final BitMatrix image;

    public MonochromeRectangleDetector(BitMatrix image) {
        this.image = image;
    }

    public ResultPoint[] detect() throws NotFoundException {
        int height = this.image.getHeight();
        int width = this.image.getWidth();
        int halfHeight = height / 2;
        int halfWidth = width / 2;
        int deltaY = Math.max(1, height / 256);
        int deltaX = Math.max(1, width / 256);
        int top = ((int) findCornerFromCenter(halfWidth, 0, 0, width, halfHeight, -deltaY, 0, height, halfWidth / 2).getY()) - 1;
        ResultPoint pointB = findCornerFromCenter(halfWidth, -deltaX, 0, width, halfHeight, 0, top, height, halfHeight / 2);
        int left = ((int) pointB.getX()) - 1;
        ResultPoint pointC = findCornerFromCenter(halfWidth, deltaX, left, width, halfHeight, 0, top, height, halfHeight / 2);
        int right = ((int) pointC.getX()) + 1;
        ResultPoint pointD = findCornerFromCenter(halfWidth, 0, left, right, halfHeight, deltaY, top, height, halfWidth / 2);
        int bottom = ((int) pointD.getY()) + 1;
        ResultPoint pointA = findCornerFromCenter(halfWidth, 0, left, right, halfHeight, -deltaY, top, bottom, halfWidth / 4);
        return new ResultPoint[]{pointA, pointB, pointC, pointD};
    }

    private ResultPoint findCornerFromCenter(int centerX, int deltaX, int left, int right, int centerY, int deltaY, int top, int bottom, int maxWhiteRun) throws NotFoundException {
        int[] range;
        int[] lastRange = null;
        int y = centerY;
        int x = centerX;
        while (y < bottom && y >= top && x < right && x >= left) {
            if (deltaX == 0) {
                range = blackWhiteRange(y, maxWhiteRun, left, right, true);
            } else {
                range = blackWhiteRange(x, maxWhiteRun, top, bottom, false);
            }
            if (range == null) {
                if (lastRange != null) {
                    if (deltaX == 0) {
                        int lastY = y - deltaY;
                        if (lastRange[0] < centerX) {
                            if (lastRange[1] > centerX) {
                                return new ResultPoint(lastRange[deltaY > 0 ? (char) 0 : (char) 1], lastY);
                            }
                            return new ResultPoint(lastRange[0], lastY);
                        }
                        return new ResultPoint(lastRange[1], lastY);
                    }
                    int lastY2 = x - deltaX;
                    if (lastRange[0] < centerY) {
                        if (lastRange[1] > centerY) {
                            return new ResultPoint(lastY2, lastRange[deltaX < 0 ? (char) 0 : (char) 1]);
                        }
                        return new ResultPoint(lastY2, lastRange[0]);
                    }
                    return new ResultPoint(lastY2, lastRange[1]);
                }
                throw NotFoundException.getNotFoundInstance();
            }
            lastRange = range;
            y += deltaY;
            x += deltaX;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0033 A[EDGE_INSN: B:70:0x0033->B:22:0x0033 ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x006a A[EDGE_INSN: B:89:0x006a->B:48:0x006a ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int[] blackWhiteRange(int fixedDimension, int maxWhiteRun, int minDim, int maxDim, boolean horizontal) {
        int start = (minDim + maxDim) / 2;
        while (start >= minDim) {
            BitMatrix bitMatrix = this.image;
            if (!horizontal) {
                if (bitMatrix.get(fixedDimension, start)) {
                    start--;
                } else {
                    int whiteRunStart = start;
                    while (true) {
                        start--;
                        if (start >= minDim) {
                        }
                    }
                    int whiteRunSize = whiteRunStart - start;
                    if (start >= minDim) {
                    }
                    start = whiteRunStart;
                    break;
                }
            } else if (bitMatrix.get(start, fixedDimension)) {
                start--;
            } else {
                int whiteRunStart2 = start;
                while (true) {
                    start--;
                    if (start >= minDim) {
                        break;
                    }
                    BitMatrix bitMatrix2 = this.image;
                    if (horizontal) {
                        if (bitMatrix2.get(start, fixedDimension)) {
                            break;
                        }
                    } else if (bitMatrix2.get(fixedDimension, start)) {
                        break;
                    }
                }
                int whiteRunSize2 = whiteRunStart2 - start;
                if (start >= minDim || whiteRunSize2 > maxWhiteRun) {
                    start = whiteRunStart2;
                    break;
                }
            }
        }
        int start2 = start + 1;
        int end = start;
        while (end < maxDim) {
            BitMatrix bitMatrix3 = this.image;
            if (!horizontal) {
                if (bitMatrix3.get(fixedDimension, end)) {
                    end++;
                } else {
                    int whiteRunStart3 = end;
                    while (true) {
                        end++;
                        if (end < maxDim) {
                        }
                    }
                    int whiteRunSize3 = end - whiteRunStart3;
                    if (end < maxDim) {
                    }
                    end = whiteRunStart3;
                    break;
                }
            } else if (bitMatrix3.get(end, fixedDimension)) {
                end++;
            } else {
                int whiteRunStart32 = end;
                while (true) {
                    end++;
                    if (end < maxDim) {
                        break;
                    }
                    BitMatrix bitMatrix4 = this.image;
                    if (horizontal) {
                        if (bitMatrix4.get(end, fixedDimension)) {
                            break;
                        }
                    } else if (bitMatrix4.get(fixedDimension, end)) {
                        break;
                    }
                }
                int whiteRunSize32 = end - whiteRunStart32;
                if (end < maxDim || whiteRunSize32 > maxWhiteRun) {
                    end = whiteRunStart32;
                    break;
                }
            }
        }
        int end2 = end - 1;
        if (end2 > start2) {
            return new int[]{start2, end2};
        }
        return null;
    }
}
