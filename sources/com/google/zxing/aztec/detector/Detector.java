package com.google.zxing.aztec.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.aztec.AztecDetectorResult;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.common.detector.WhiteRectangleDetector;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;

public final class Detector {
    private static final int[] EXPECTED_CORNER_BITS = {3808, 476, 2107, 1799};
    private boolean compact;
    private final BitMatrix image;
    private int nbCenterLayers;
    private int nbDataBlocks;
    private int nbLayers;
    private int shift;

    public Detector(BitMatrix image2) {
        this.image = image2;
    }

    public AztecDetectorResult detect() throws NotFoundException {
        return detect(false);
    }

    public AztecDetectorResult detect(boolean isMirror) throws NotFoundException {
        ResultPoint[] bullsEyeCorners = getBullsEyeCorners(getMatrixCenter());
        if (isMirror) {
            ResultPoint temp = bullsEyeCorners[0];
            bullsEyeCorners[0] = bullsEyeCorners[2];
            bullsEyeCorners[2] = temp;
        }
        extractParameters(bullsEyeCorners);
        BitMatrix bitMatrix = this.image;
        int i = this.shift;
        BitMatrix bits = sampleGrid(bitMatrix, bullsEyeCorners[i % 4], bullsEyeCorners[(i + 1) % 4], bullsEyeCorners[(i + 2) % 4], bullsEyeCorners[(i + 3) % 4]);
        return new AztecDetectorResult(bits, getMatrixCornerPoints(bullsEyeCorners), this.compact, this.nbDataBlocks, this.nbLayers);
    }

    private void extractParameters(ResultPoint[] bullsEyeCorners) throws NotFoundException {
        int i;
        long j;
        if (!isValid(bullsEyeCorners[0]) || !isValid(bullsEyeCorners[1]) || !isValid(bullsEyeCorners[2]) || !isValid(bullsEyeCorners[3])) {
            throw NotFoundException.getNotFoundInstance();
        }
        int length = this.nbCenterLayers * 2;
        int[] sides = {sampleLine(bullsEyeCorners[0], bullsEyeCorners[1], length), sampleLine(bullsEyeCorners[1], bullsEyeCorners[2], length), sampleLine(bullsEyeCorners[2], bullsEyeCorners[3], length), sampleLine(bullsEyeCorners[3], bullsEyeCorners[0], length)};
        this.shift = getRotation(sides, length);
        long parameterData = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            int side = sides[(this.shift + i2) % 4];
            if (this.compact) {
                j = parameterData << 7;
                i = (side >> 1) & 127;
            } else {
                j = parameterData << 10;
                i = ((side >> 2) & 992) + ((side >> 1) & 31);
            }
            parameterData = j + ((long) i);
        }
        int correctedData = getCorrectedParameterData(parameterData, this.compact);
        if (this.compact) {
            this.nbLayers = (correctedData >> 6) + 1;
            this.nbDataBlocks = (correctedData & 63) + 1;
            return;
        }
        this.nbLayers = (correctedData >> 11) + 1;
        this.nbDataBlocks = (correctedData & 2047) + 1;
    }

    private static int getRotation(int[] sides, int length) throws NotFoundException {
        int cornerBits = 0;
        for (int side : sides) {
            cornerBits = (cornerBits << 3) + ((side >> (length - 2)) << 1) + (side & 1);
        }
        int cornerBits2 = ((cornerBits & 1) << 11) + (cornerBits >> 1);
        for (int shift2 = 0; shift2 < 4; shift2++) {
            if (Integer.bitCount(EXPECTED_CORNER_BITS[shift2] ^ cornerBits2) <= 2) {
                return shift2;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int getCorrectedParameterData(long j, boolean z) throws NotFoundException {
        int i;
        int i2;
        if (z) {
            i = 7;
            i2 = 2;
        } else {
            i = 10;
            i2 = 4;
        }
        int i3 = i - i2;
        int[] iArr = new int[i];
        for (int i4 = i - 1; i4 >= 0; i4--) {
            iArr[i4] = ((int) j) & 15;
            j >>= 4;
        }
        try {
            new ReedSolomonDecoder(GenericGF.AZTEC_PARAM).decode(iArr, i3);
            int i5 = 0;
            for (int i6 = 0; i6 < i2; i6++) {
                i5 = (i5 << 4) + iArr[i6];
            }
            return i5;
        } catch (ReedSolomonException e) {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    private ResultPoint[] getBullsEyeCorners(Point pCenter) throws NotFoundException {
        Point pina = pCenter;
        Point pinb = pCenter;
        Point pinc = pCenter;
        Point pind = pCenter;
        boolean color = true;
        this.nbCenterLayers = 1;
        while (this.nbCenterLayers < 9) {
            Point pouta = getFirstDifferent(pina, color, 1, -1);
            Point poutb = getFirstDifferent(pinb, color, 1, 1);
            Point poutc = getFirstDifferent(pinc, color, -1, 1);
            Point poutd = getFirstDifferent(pind, color, -1, -1);
            if (this.nbCenterLayers > 2) {
                float distance = (distance(poutd, pouta) * ((float) this.nbCenterLayers)) / (distance(pind, pina) * ((float) (this.nbCenterLayers + 2)));
                float q = distance;
                if (((double) distance) >= 0.75d) {
                    if (((double) q) <= 1.25d) {
                        if (!isWhiteOrBlackRectangle(pouta, poutb, poutc, poutd)) {
                            break;
                        }
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
            pina = pouta;
            pinb = poutb;
            pinc = poutc;
            pind = poutd;
            color = !color;
            this.nbCenterLayers++;
        }
        int i = this.nbCenterLayers;
        if (i == 5 || i == 7) {
            this.compact = i == 5;
            ResultPoint[] resultPointArr = {new ResultPoint(((float) pina.getX()) + 0.5f, ((float) pina.getY()) - 0.5f), new ResultPoint(((float) pinb.getX()) + 0.5f, ((float) pinb.getY()) + 0.5f), new ResultPoint(((float) pinc.getX()) - 0.5f, ((float) pinc.getY()) + 0.5f), new ResultPoint(((float) pind.getX()) - 0.5f, ((float) pind.getY()) - 0.5f)};
            int i2 = this.nbCenterLayers;
            return expandSquare(resultPointArr, (i2 * 2) - 3, i2 * 2);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private Point getMatrixCenter() {
        ResultPoint resultPoint;
        ResultPoint resultPoint2;
        ResultPoint resultPoint3;
        ResultPoint resultPoint4;
        ResultPoint resultPoint5;
        ResultPoint resultPoint6;
        ResultPoint resultPoint7;
        ResultPoint resultPoint8;
        try {
            ResultPoint[] detect = new WhiteRectangleDetector(this.image).detect();
            resultPoint3 = detect[0];
            resultPoint2 = detect[1];
            resultPoint = detect[2];
            resultPoint4 = detect[3];
        } catch (NotFoundException e) {
            int width = this.image.getWidth() / 2;
            int height = this.image.getHeight() / 2;
            int i = width + 7;
            int i2 = height - 7;
            ResultPoint resultPoint9 = getFirstDifferent(new Point(i, i2), false, 1, -1).toResultPoint();
            int i3 = height + 7;
            ResultPoint resultPoint10 = getFirstDifferent(new Point(i, i3), false, 1, 1).toResultPoint();
            int i4 = width - 7;
            ResultPoint resultPoint11 = getFirstDifferent(new Point(i4, i3), false, -1, 1).toResultPoint();
            resultPoint4 = getFirstDifferent(new Point(i4, i2), false, -1, -1).toResultPoint();
            ResultPoint resultPoint12 = resultPoint10;
            resultPoint = resultPoint11;
            resultPoint3 = resultPoint9;
            resultPoint2 = resultPoint12;
        }
        int round = MathUtils.round((((resultPoint3.getX() + resultPoint4.getX()) + resultPoint2.getX()) + resultPoint.getX()) / 4.0f);
        int round2 = MathUtils.round((((resultPoint3.getY() + resultPoint4.getY()) + resultPoint2.getY()) + resultPoint.getY()) / 4.0f);
        try {
            ResultPoint[] detect2 = new WhiteRectangleDetector(this.image, 15, round, round2).detect();
            resultPoint6 = detect2[0];
            resultPoint5 = detect2[1];
            resultPoint7 = detect2[2];
            resultPoint8 = detect2[3];
        } catch (NotFoundException e2) {
            int i5 = round + 7;
            int i6 = round2 - 7;
            resultPoint6 = getFirstDifferent(new Point(i5, i6), false, 1, -1).toResultPoint();
            int i7 = round2 + 7;
            resultPoint5 = getFirstDifferent(new Point(i5, i7), false, 1, 1).toResultPoint();
            int i8 = round - 7;
            resultPoint7 = getFirstDifferent(new Point(i8, i7), false, -1, 1).toResultPoint();
            resultPoint8 = getFirstDifferent(new Point(i8, i6), false, -1, -1).toResultPoint();
        }
        return new Point(MathUtils.round((((resultPoint6.getX() + resultPoint8.getX()) + resultPoint5.getX()) + resultPoint7.getX()) / 4.0f), MathUtils.round((((resultPoint6.getY() + resultPoint8.getY()) + resultPoint5.getY()) + resultPoint7.getY()) / 4.0f));
    }

    private ResultPoint[] getMatrixCornerPoints(ResultPoint[] bullsEyeCorners) {
        return expandSquare(bullsEyeCorners, this.nbCenterLayers * 2, getDimension());
    }

    private BitMatrix sampleGrid(BitMatrix image2, ResultPoint topLeft, ResultPoint topRight, ResultPoint bottomRight, ResultPoint bottomLeft) throws NotFoundException {
        GridSampler instance = GridSampler.getInstance();
        int dimension = getDimension();
        int dimension2 = dimension;
        int i = dimension2;
        int i2 = this.nbCenterLayers;
        float low = (((float) dimension) / 2.0f) - ((float) i2);
        float high = (((float) dimension2) / 2.0f) + ((float) i2);
        int i3 = dimension2;
        return instance.sampleGrid(image2, i, dimension2, low, low, high, low, high, high, low, high, topLeft.getX(), topLeft.getY(), topRight.getX(), topRight.getY(), bottomRight.getX(), bottomRight.getY(), bottomLeft.getX(), bottomLeft.getY());
    }

    private int sampleLine(ResultPoint p1, ResultPoint p2, int size) {
        int result = 0;
        float distance = distance(p1, p2);
        float d = distance;
        float moduleSize = distance / ((float) size);
        float px = p1.getX();
        float py = p1.getY();
        float dx = ((p2.getX() - p1.getX()) * moduleSize) / d;
        float dy = ((p2.getY() - p1.getY()) * moduleSize) / d;
        for (int i = 0; i < size; i++) {
            if (this.image.get(MathUtils.round((((float) i) * dx) + px), MathUtils.round((((float) i) * dy) + py))) {
                result |= 1 << ((size - i) - 1);
            }
        }
        return result;
    }

    private boolean isWhiteOrBlackRectangle(Point p1, Point p2, Point p3, Point p4) {
        Point p12 = new Point(p1.getX() - 3, p1.getY() + 3);
        Point p22 = new Point(p2.getX() - 3, p2.getY() - 3);
        Point p32 = new Point(p3.getX() + 3, p3.getY() - 3);
        Point p42 = new Point(p4.getX() + 3, p4.getY() + 3);
        int color = getColor(p42, p12);
        int cInit = color;
        if (color != 0 && getColor(p12, p22) == cInit && getColor(p22, p32) == cInit && getColor(p32, p42) == cInit) {
            return true;
        }
        return false;
    }

    private int getColor(Point p1, Point p2) {
        float d = distance(p1, p2);
        float dx = ((float) (p2.getX() - p1.getX())) / d;
        float dy = ((float) (p2.getY() - p1.getY())) / d;
        int error = 0;
        float px = (float) p1.getX();
        float py = (float) p1.getY();
        boolean colorModel = this.image.get(p1.getX(), p1.getY());
        int iMax = (int) Math.ceil((double) d);
        for (int i = 0; i < iMax; i++) {
            px += dx;
            py += dy;
            if (this.image.get(MathUtils.round(px), MathUtils.round(py)) != colorModel) {
                error++;
            }
        }
        float f = ((float) error) / d;
        float errRatio = f;
        boolean z = false;
        if (f > 0.1f && errRatio < 0.9f) {
            return 0;
        }
        if (errRatio <= 0.1f) {
            z = true;
        }
        return z == colorModel ? 1 : -1;
    }

    private Point getFirstDifferent(Point init, boolean color, int dx, int dy) {
        int x = init.getX() + dx;
        int y = init.getY();
        while (true) {
            y += dy;
            if (!isValid(x, y) || this.image.get(x, y) != color) {
                int x2 = x - dx;
                int y2 = y - dy;
            } else {
                x += dx;
            }
        }
        int x22 = x - dx;
        int y22 = y - dy;
        while (isValid(x22, y22) && this.image.get(x22, y22) == color) {
            x22 += dx;
        }
        int x3 = x22 - dx;
        while (isValid(x3, y22) && this.image.get(x3, y22) == color) {
            y22 += dy;
        }
        return new Point(x3, y22 - dy);
    }

    private static ResultPoint[] expandSquare(ResultPoint[] cornerPoints, int oldSide, int newSide) {
        float ratio = ((float) newSide) / (((float) oldSide) * 2.0f);
        float dx = cornerPoints[0].getX() - cornerPoints[2].getX();
        float dy = cornerPoints[0].getY() - cornerPoints[2].getY();
        float centerx = (cornerPoints[0].getX() + cornerPoints[2].getX()) / 2.0f;
        float centery = (cornerPoints[0].getY() + cornerPoints[2].getY()) / 2.0f;
        ResultPoint result0 = new ResultPoint((ratio * dx) + centerx, (ratio * dy) + centery);
        ResultPoint result2 = new ResultPoint(centerx - (ratio * dx), centery - (ratio * dy));
        float dx2 = cornerPoints[1].getX() - cornerPoints[3].getX();
        float dy2 = cornerPoints[1].getY() - cornerPoints[3].getY();
        float centerx2 = (cornerPoints[1].getX() + cornerPoints[3].getX()) / 2.0f;
        float centery2 = (cornerPoints[1].getY() + cornerPoints[3].getY()) / 2.0f;
        return new ResultPoint[]{result0, new ResultPoint((ratio * dx2) + centerx2, (ratio * dy2) + centery2), result2, new ResultPoint(centerx2 - (ratio * dx2), centery2 - (ratio * dy2))};
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < this.image.getWidth() && y > 0 && y < this.image.getHeight();
    }

    private boolean isValid(ResultPoint point) {
        return isValid(MathUtils.round(point.getX()), MathUtils.round(point.getY()));
    }

    private static float distance(Point a, Point b) {
        return MathUtils.distance(a.getX(), a.getY(), b.getX(), b.getY());
    }

    private static float distance(ResultPoint a, ResultPoint b) {
        return MathUtils.distance(a.getX(), a.getY(), b.getX(), b.getY());
    }

    private int getDimension() {
        if (this.compact) {
            return (this.nbLayers * 4) + 11;
        }
        int i = this.nbLayers;
        if (i <= 4) {
            return (i * 4) + 15;
        }
        return (i * 4) + ((((i - 4) / 8) + 1) * 2) + 15;
    }

    static final class Point {
        private final int x;
        private final int y;

        /* access modifiers changed from: package-private */
        public ResultPoint toResultPoint() {
            return new ResultPoint((float) getX(), (float) getY());
        }

        Point(int x2, int y2) {
            this.x = x2;
            this.y = y2;
        }

        /* access modifiers changed from: package-private */
        public int getX() {
            return this.x;
        }

        /* access modifiers changed from: package-private */
        public int getY() {
            return this.y;
        }

        public String toString() {
            return "<" + this.x + ' ' + this.y + '>';
        }
    }
}
