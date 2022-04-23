package com.google.zxing.datamatrix.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.common.detector.WhiteRectangleDetector;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class Detector {
    private final BitMatrix image;
    private final WhiteRectangleDetector rectangleDetector;

    public Detector(BitMatrix image2) throws NotFoundException {
        this.image = image2;
        this.rectangleDetector = new WhiteRectangleDetector(image2);
    }

    public DetectorResult detect() throws NotFoundException {
        ResultPoint topRight;
        char c;
        ResultPoint bottomRight;
        BitMatrix bits;
        ResultPoint correctedTopRight;
        ResultPoint topLeft;
        ResultPoint topRight2;
        ResultPoint pointD;
        int dimensionTop;
        int dimensionRight;
        ResultPoint[] detect = this.rectangleDetector.detect();
        ResultPoint[] cornerPoints = detect;
        ResultPoint pointA = detect[0];
        ResultPoint pointB = cornerPoints[1];
        ResultPoint pointC = cornerPoints[2];
        ResultPoint pointD2 = cornerPoints[3];
        ArrayList arrayList = new ArrayList(4);
        ArrayList arrayList2 = arrayList;
        arrayList.add(transitionsBetween(pointA, pointB));
        arrayList2.add(transitionsBetween(pointA, pointC));
        arrayList2.add(transitionsBetween(pointB, pointD2));
        arrayList2.add(transitionsBetween(pointC, pointD2));
        Collections.sort(arrayList2, new ResultPointsAndTransitionsComparator());
        ResultPointsAndTransitions lSideOne = (ResultPointsAndTransitions) arrayList2.get(0);
        ResultPointsAndTransitions lSideTwo = (ResultPointsAndTransitions) arrayList2.get(1);
        Map<ResultPoint, Integer> hashMap = new HashMap<>();
        Map<ResultPoint, Integer> map = hashMap;
        increment(hashMap, lSideOne.getFrom());
        increment(map, lSideOne.getTo());
        increment(map, lSideTwo.getFrom());
        increment(map, lSideTwo.getTo());
        ResultPoint bottomLeft = null;
        Iterator<Map.Entry<ResultPoint, Integer>> it = map.entrySet().iterator();
        ResultPoint maybeBottomRight = null;
        ResultPoint maybeTopLeft = null;
        while (it.hasNext()) {
            Map.Entry next = it.next();
            Map.Entry entry = next;
            ResultPoint point = (ResultPoint) next.getKey();
            if (((Integer) entry.getValue()).intValue() == 2) {
                bottomLeft = point;
            } else if (maybeTopLeft == null) {
                maybeTopLeft = point;
            } else {
                maybeBottomRight = point;
            }
        }
        if (maybeTopLeft == null || bottomLeft == null || maybeBottomRight == null) {
            Map<ResultPoint, Integer> pointCount = map;
            ArrayList arrayList3 = arrayList2;
            ResultPoint resultPoint = pointD2;
            throw NotFoundException.getNotFoundInstance();
        }
        ResultPoint[] resultPointArr = {maybeTopLeft, bottomLeft, maybeBottomRight};
        Iterator<Map.Entry<ResultPoint, Integer>> it2 = it;
        ResultPoint[] corners = resultPointArr;
        ResultPoint.orderBestPatterns(resultPointArr);
        ResultPoint bottomRight2 = corners[0];
        ResultPoint bottomLeft2 = corners[1];
        ResultPoint bottomLeft3 = corners[2];
        if (!map.containsKey(pointA)) {
            topRight = pointA;
        } else if (!map.containsKey(pointB)) {
            topRight = pointB;
        } else if (!map.containsKey(pointC)) {
            topRight = pointC;
        } else {
            topRight = pointD2;
        }
        int dimensionTop2 = transitionsBetween(bottomLeft3, topRight).getTransitions();
        int dimensionRight2 = transitionsBetween(bottomRight2, topRight).getTransitions();
        if ((dimensionTop2 & 1) == 1) {
            dimensionTop2++;
        }
        int dimensionTop3 = dimensionTop2 + 2;
        if ((dimensionRight2 & 1) == 1) {
            dimensionRight2++;
        }
        int dimensionRight3 = dimensionRight2 + 2;
        if (dimensionTop3 * 4 >= dimensionRight3 * 7) {
            topRight2 = topRight;
            HashMap hashMap2 = map;
            ArrayList arrayList4 = arrayList2;
            ResultPoint resultPoint2 = pointD2;
            pointD = bottomLeft3;
        } else if (dimensionRight3 * 4 >= dimensionTop3 * 7) {
            topRight2 = topRight;
            HashMap hashMap3 = map;
            ArrayList arrayList5 = arrayList2;
            ResultPoint resultPoint3 = pointD2;
            pointD = bottomLeft3;
        } else {
            ResultPoint topRight3 = topRight;
            ResultPoint topLeft2 = bottomLeft3;
            HashMap hashMap4 = map;
            ArrayList arrayList6 = arrayList2;
            ResultPoint bottomRight3 = bottomRight2;
            ResultPoint resultPoint4 = pointD2;
            ResultPoint correctTopRight = correctTopRight(bottomLeft2, bottomRight2, topLeft2, topRight3, Math.min(dimensionRight3, dimensionTop3));
            correctedTopRight = correctTopRight;
            if (correctTopRight == null) {
                correctedTopRight = topRight3;
            }
            ResultPoint topLeft3 = topLeft2;
            ResultPoint bottomRight4 = bottomRight3;
            int dimensionCorrected = Math.max(transitionsBetween(topLeft3, correctedTopRight).getTransitions(), transitionsBetween(bottomRight4, correctedTopRight).getTransitions()) + 1;
            if ((dimensionCorrected & 1) == 1) {
                dimensionCorrected++;
            }
            bits = sampleGrid(this.image, topLeft3, bottomLeft2, bottomRight4, correctedTopRight, dimensionCorrected, dimensionCorrected);
            bottomRight = bottomRight4;
            topLeft = topLeft3;
            c = 3;
            ResultPoint[] resultPointArr2 = new ResultPoint[4];
            resultPointArr2[0] = topLeft;
            resultPointArr2[1] = bottomLeft2;
            resultPointArr2[2] = bottomRight;
            resultPointArr2[c] = correctedTopRight;
            return new DetectorResult(bits, resultPointArr2);
        }
        bottomRight = bottomRight2;
        ResultPoint topLeft4 = pointD;
        int i = dimensionTop3;
        c = 3;
        ResultPoint correctTopRightRectangular = correctTopRightRectangular(bottomLeft2, bottomRight2, pointD, topRight2, dimensionTop3, dimensionRight3);
        ResultPoint correctedTopRight2 = correctTopRightRectangular;
        if (correctTopRightRectangular == null) {
            correctedTopRight2 = topRight2;
        }
        topLeft = topLeft4;
        int dimensionTop4 = transitionsBetween(topLeft, correctedTopRight).getTransitions();
        int dimensionRight4 = transitionsBetween(bottomRight, correctedTopRight).getTransitions();
        if ((dimensionTop4 & 1) == 1) {
            dimensionTop = dimensionTop4 + 1;
        } else {
            dimensionTop = dimensionTop4;
        }
        if ((dimensionRight4 & 1) == 1) {
            dimensionRight = dimensionRight4 + 1;
        } else {
            dimensionRight = dimensionRight4;
        }
        bits = sampleGrid(this.image, topLeft, bottomLeft2, bottomRight, correctedTopRight, dimensionTop, dimensionRight);
        ResultPoint[] resultPointArr22 = new ResultPoint[4];
        resultPointArr22[0] = topLeft;
        resultPointArr22[1] = bottomLeft2;
        resultPointArr22[2] = bottomRight;
        resultPointArr22[c] = correctedTopRight;
        return new DetectorResult(bits, resultPointArr22);
    }

    private ResultPoint correctTopRightRectangular(ResultPoint bottomLeft, ResultPoint bottomRight, ResultPoint topLeft, ResultPoint topRight, int dimensionTop, int dimensionRight) {
        float corr = ((float) distance(bottomLeft, bottomRight)) / ((float) dimensionTop);
        int norm = distance(topLeft, topRight);
        ResultPoint c1 = new ResultPoint(topRight.getX() + (corr * ((topRight.getX() - topLeft.getX()) / ((float) norm))), topRight.getY() + (corr * ((topRight.getY() - topLeft.getY()) / ((float) norm))));
        float corr2 = ((float) distance(bottomLeft, topLeft)) / ((float) dimensionRight);
        int norm2 = distance(bottomRight, topRight);
        ResultPoint c2 = new ResultPoint(topRight.getX() + (corr2 * ((topRight.getX() - bottomRight.getX()) / ((float) norm2))), topRight.getY() + (corr2 * ((topRight.getY() - bottomRight.getY()) / ((float) norm2))));
        if (!isValid(c1)) {
            if (isValid(c2)) {
                return c2;
            }
            return null;
        } else if (isValid(c2) && Math.abs(dimensionTop - transitionsBetween(topLeft, c1).getTransitions()) + Math.abs(dimensionRight - transitionsBetween(bottomRight, c1).getTransitions()) > Math.abs(dimensionTop - transitionsBetween(topLeft, c2).getTransitions()) + Math.abs(dimensionRight - transitionsBetween(bottomRight, c2).getTransitions())) {
            return c2;
        } else {
            return c1;
        }
    }

    private ResultPoint correctTopRight(ResultPoint bottomLeft, ResultPoint bottomRight, ResultPoint topLeft, ResultPoint topRight, int dimension) {
        float corr = ((float) distance(bottomLeft, bottomRight)) / ((float) dimension);
        int norm = distance(topLeft, topRight);
        ResultPoint c1 = new ResultPoint(topRight.getX() + (corr * ((topRight.getX() - topLeft.getX()) / ((float) norm))), topRight.getY() + (corr * ((topRight.getY() - topLeft.getY()) / ((float) norm))));
        float corr2 = ((float) distance(bottomLeft, topLeft)) / ((float) dimension);
        int norm2 = distance(bottomRight, topRight);
        ResultPoint c2 = new ResultPoint(topRight.getX() + (corr2 * ((topRight.getX() - bottomRight.getX()) / ((float) norm2))), topRight.getY() + (corr2 * ((topRight.getY() - bottomRight.getY()) / ((float) norm2))));
        if (isValid(c1)) {
            return (isValid(c2) && Math.abs(transitionsBetween(topLeft, c1).getTransitions() - transitionsBetween(bottomRight, c1).getTransitions()) > Math.abs(transitionsBetween(topLeft, c2).getTransitions() - transitionsBetween(bottomRight, c2).getTransitions())) ? c2 : c1;
        }
        if (isValid(c2)) {
            return c2;
        }
        return null;
    }

    private boolean isValid(ResultPoint p) {
        return p.getX() >= 0.0f && p.getX() < ((float) this.image.getWidth()) && p.getY() > 0.0f && p.getY() < ((float) this.image.getHeight());
    }

    private static int distance(ResultPoint a, ResultPoint b) {
        return MathUtils.round(ResultPoint.distance(a, b));
    }

    private static void increment(Map<ResultPoint, Integer> table, ResultPoint key) {
        Integer value = table.get(key);
        int i = 1;
        if (value != null) {
            i = 1 + value.intValue();
        }
        table.put(key, Integer.valueOf(i));
    }

    private static BitMatrix sampleGrid(BitMatrix image2, ResultPoint topLeft, ResultPoint bottomLeft, ResultPoint bottomRight, ResultPoint topRight, int dimensionX, int dimensionY) throws NotFoundException {
        int i = dimensionX;
        int i2 = dimensionY;
        return GridSampler.getInstance().sampleGrid(image2, dimensionX, dimensionY, 0.5f, 0.5f, ((float) i) - 0.5f, 0.5f, ((float) i) - 0.5f, ((float) i2) - 0.5f, 0.5f, ((float) i2) - 0.5f, topLeft.getX(), topLeft.getY(), topRight.getX(), topRight.getY(), bottomRight.getX(), bottomRight.getY(), bottomLeft.getX(), bottomLeft.getY());
    }

    private ResultPointsAndTransitions transitionsBetween(ResultPoint from, ResultPoint to) {
        Detector detector = this;
        int fromX = (int) from.getX();
        int fromY = (int) from.getY();
        int toX = (int) to.getX();
        int y = (int) to.getY();
        boolean isBlack = false;
        int toY = y;
        int xstep = 1;
        boolean z = Math.abs(y - fromY) > Math.abs(toX - fromX);
        boolean steep = z;
        if (z) {
            int temp = fromX;
            fromX = fromY;
            fromY = temp;
            int temp2 = toX;
            toX = toY;
            toY = temp2;
        }
        int dx = Math.abs(toX - fromX);
        int dy = Math.abs(toY - fromY);
        int error = (-dx) / 2;
        int ystep = fromY < toY ? 1 : -1;
        if (fromX >= toX) {
            xstep = -1;
        }
        int transitions = 0;
        boolean inBlack = detector.image.get(steep ? fromY : fromX, steep ? fromX : fromY);
        int x = fromX;
        int y2 = fromY;
        while (true) {
            if (x == toX) {
                int i = fromY;
                break;
            }
            int fromX2 = fromX;
            int fromY2 = fromY;
            boolean z2 = detector.image.get(steep ? y2 : x, steep ? x : y2);
            boolean z3 = isBlack;
            isBlack = z2;
            if (z2 != inBlack) {
                transitions++;
                inBlack = isBlack;
            }
            int i2 = error + dy;
            error = i2;
            if (i2 > 0) {
                if (y2 == toY) {
                    break;
                }
                y2 += ystep;
                error -= dx;
            }
            x += xstep;
            detector = this;
            fromX = fromX2;
            fromY = fromY2;
        }
        return new ResultPointsAndTransitions(from, to, transitions);
    }

    private static final class ResultPointsAndTransitions {
        private final ResultPoint from;
        private final ResultPoint to;
        private final int transitions;

        private ResultPointsAndTransitions(ResultPoint from2, ResultPoint to2, int transitions2) {
            this.from = from2;
            this.to = to2;
            this.transitions = transitions2;
        }

        /* access modifiers changed from: package-private */
        public ResultPoint getFrom() {
            return this.from;
        }

        /* access modifiers changed from: package-private */
        public ResultPoint getTo() {
            return this.to;
        }

        /* access modifiers changed from: package-private */
        public int getTransitions() {
            return this.transitions;
        }

        public String toString() {
            return this.from + "/" + this.to + '/' + this.transitions;
        }
    }

    private static final class ResultPointsAndTransitionsComparator implements Serializable, Comparator<ResultPointsAndTransitions> {
        private ResultPointsAndTransitionsComparator() {
        }

        public int compare(ResultPointsAndTransitions o1, ResultPointsAndTransitions o2) {
            return o1.getTransitions() - o2.getTransitions();
        }
    }
}
