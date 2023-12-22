package com.google.zxing;

import com.google.zxing.common.detector.MathUtils;

/* loaded from: classes.dex */
public class ResultPoint {

    /* renamed from: x */
    private final float f94x;

    /* renamed from: y */
    private final float f95y;

    public ResultPoint(float x, float y) {
        this.f94x = x;
        this.f95y = y;
    }

    public final float getX() {
        return this.f94x;
    }

    public final float getY() {
        return this.f95y;
    }

    public final boolean equals(Object other) {
        if (other instanceof ResultPoint) {
            ResultPoint otherPoint = (ResultPoint) other;
            return this.f94x == otherPoint.f94x && this.f95y == otherPoint.f95y;
        }
        return false;
    }

    public final int hashCode() {
        return (Float.floatToIntBits(this.f94x) * 31) + Float.floatToIntBits(this.f95y);
    }

    public final String toString() {
        return "(" + this.f94x + ',' + this.f95y + ')';
    }

    public static void orderBestPatterns(ResultPoint[] patterns) {
        ResultPoint pointB;
        ResultPoint pointA;
        ResultPoint pointC;
        float zeroOneDistance = distance(patterns[0], patterns[1]);
        float oneTwoDistance = distance(patterns[1], patterns[2]);
        float zeroTwoDistance = distance(patterns[0], patterns[2]);
        if (oneTwoDistance >= zeroOneDistance && oneTwoDistance >= zeroTwoDistance) {
            pointB = patterns[0];
            pointA = patterns[1];
            pointC = patterns[2];
        } else if (zeroTwoDistance >= oneTwoDistance && zeroTwoDistance >= zeroOneDistance) {
            pointB = patterns[1];
            pointA = patterns[0];
            pointC = patterns[2];
        } else {
            pointB = patterns[2];
            pointA = patterns[0];
            pointC = patterns[1];
        }
        if (crossProductZ(pointA, pointB, pointC) < 0.0f) {
            ResultPoint temp = pointA;
            pointA = pointC;
            pointC = temp;
        }
        patterns[0] = pointA;
        patterns[1] = pointB;
        patterns[2] = pointC;
    }

    public static float distance(ResultPoint pattern1, ResultPoint pattern2) {
        return MathUtils.distance(pattern1.f94x, pattern1.f95y, pattern2.f94x, pattern2.f95y);
    }

    private static float crossProductZ(ResultPoint pointA, ResultPoint pointB, ResultPoint pointC) {
        float bX = pointB.f94x;
        float bY = pointB.f95y;
        return ((pointC.f94x - bX) * (pointA.f95y - bY)) - ((pointC.f95y - bY) * (pointA.f94x - bX));
    }
}
