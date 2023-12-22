package com.google.zxing.common;

/* loaded from: classes.dex */
public final class PerspectiveTransform {
    private final float a11;
    private final float a12;
    private final float a13;
    private final float a21;
    private final float a22;
    private final float a23;
    private final float a31;
    private final float a32;
    private final float a33;

    private PerspectiveTransform(float a11, float a21, float a31, float a12, float a22, float a32, float a13, float a23, float a33) {
        this.a11 = a11;
        this.a12 = a12;
        this.a13 = a13;
        this.a21 = a21;
        this.a22 = a22;
        this.a23 = a23;
        this.a31 = a31;
        this.a32 = a32;
        this.a33 = a33;
    }

    public static PerspectiveTransform quadrilateralToQuadrilateral(float x0, float y0, float x1, float y1, float x2, float y2, float x3, float y3, float x0p, float y0p, float x1p, float y1p, float x2p, float y2p, float x3p, float y3p) {
        PerspectiveTransform qToS = quadrilateralToSquare(x0, y0, x1, y1, x2, y2, x3, y3);
        return squareToQuadrilateral(x0p, y0p, x1p, y1p, x2p, y2p, x3p, y3p).times(qToS);
    }

    public void transformPoints(float[] points) {
        int max = points.length;
        float a11 = this.a11;
        float a12 = this.a12;
        float a13 = this.a13;
        float a21 = this.a21;
        float a22 = this.a22;
        float a23 = this.a23;
        float a31 = this.a31;
        float a32 = this.a32;
        float a33 = this.a33;
        for (int i = 0; i < max; i += 2) {
            float x = points[i];
            float y = points[i + 1];
            float denominator = (a13 * x) + (a23 * y) + a33;
            points[i] = (((a11 * x) + (a21 * y)) + a31) / denominator;
            points[i + 1] = (((a12 * x) + (a22 * y)) + a32) / denominator;
        }
    }

    public void transformPoints(float[] xValues, float[] yValues) {
        int n = xValues.length;
        for (int i = 0; i < n; i++) {
            float x = xValues[i];
            float y = yValues[i];
            float denominator = (this.a13 * x) + (this.a23 * y) + this.a33;
            xValues[i] = (((this.a11 * x) + (this.a21 * y)) + this.a31) / denominator;
            yValues[i] = (((this.a12 * x) + (this.a22 * y)) + this.a32) / denominator;
        }
    }

    public static PerspectiveTransform squareToQuadrilateral(float x0, float y0, float x1, float y1, float x2, float y2, float x3, float y3) {
        float dx3 = ((x0 - x1) + x2) - x3;
        float dy3 = ((y0 - y1) + y2) - y3;
        if (dx3 == 0.0f && dy3 == 0.0f) {
            return new PerspectiveTransform(x1 - x0, x2 - x1, x0, y1 - y0, y2 - y1, y0, 0.0f, 0.0f, 1.0f);
        }
        float dx1 = x1 - x2;
        float dx2 = x3 - x2;
        float dy1 = y1 - y2;
        float dy2 = y3 - y2;
        float denominator = (dx1 * dy2) - (dx2 * dy1);
        float a13 = ((dx3 * dy2) - (dx2 * dy3)) / denominator;
        float a23 = ((dx1 * dy3) - (dx3 * dy1)) / denominator;
        return new PerspectiveTransform((a13 * x1) + (x1 - x0), (a23 * x3) + (x3 - x0), x0, (y1 - y0) + (a13 * y1), (y3 - y0) + (a23 * y3), y0, a13, a23, 1.0f);
    }

    public static PerspectiveTransform quadrilateralToSquare(float x0, float y0, float x1, float y1, float x2, float y2, float x3, float y3) {
        return squareToQuadrilateral(x0, y0, x1, y1, x2, y2, x3, y3).buildAdjoint();
    }

    PerspectiveTransform buildAdjoint() {
        float f = this.a22;
        float f2 = this.a33;
        float f3 = this.a23;
        float f4 = this.a32;
        float f5 = (f * f2) - (f3 * f4);
        float f6 = this.a31;
        float f7 = this.a21;
        float f8 = (f3 * f6) - (f7 * f2);
        float f9 = (f7 * f4) - (f * f6);
        float f10 = this.a13;
        float f11 = this.a12;
        float f12 = (f10 * f4) - (f11 * f2);
        float f13 = this.a11;
        return new PerspectiveTransform(f5, f8, f9, f12, (f2 * f13) - (f10 * f6), (f6 * f11) - (f4 * f13), (f11 * f3) - (f10 * f), (f10 * f7) - (f3 * f13), (f13 * f) - (f11 * f7));
    }

    PerspectiveTransform times(PerspectiveTransform other) {
        float f = this.a11;
        float f2 = other.a11;
        float f3 = this.a21;
        float f4 = other.a12;
        float f5 = this.a31;
        float f6 = other.a13;
        float f7 = (f * f2) + (f3 * f4) + (f5 * f6);
        float f8 = other.a21;
        float f9 = other.a22;
        float f10 = other.a23;
        float f11 = (f * f8) + (f3 * f9) + (f5 * f10);
        float f12 = other.a31;
        float f13 = other.a32;
        float f14 = (f * f12) + (f3 * f13);
        float f15 = other.a33;
        float f16 = (f5 * f15) + f14;
        float f17 = this.a12;
        float f18 = this.a22;
        float f19 = this.a32;
        float f20 = (f17 * f12) + (f18 * f13) + (f19 * f15);
        float f21 = this.a13;
        float f22 = this.a23;
        float f23 = (f2 * f21) + (f4 * f22);
        float f24 = this.a33;
        return new PerspectiveTransform(f7, f11, f16, (f17 * f2) + (f18 * f4) + (f19 * f6), (f17 * f8) + (f18 * f9) + (f19 * f10), f20, f23 + (f6 * f24), (f8 * f21) + (f9 * f22) + (f10 * f24), (f21 * f12) + (f22 * f13) + (f24 * f15));
    }
}
