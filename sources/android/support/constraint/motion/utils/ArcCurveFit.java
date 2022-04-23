package android.support.constraint.motion.utils;

import java.util.Arrays;

class ArcCurveFit extends CurveFit {
    public static final int ARC_START_FLIP = 3;
    public static final int ARC_START_HORIZONTAL = 2;
    public static final int ARC_START_LINEAR = 0;
    public static final int ARC_START_VERTICAL = 1;
    private static final int START_HORIZONTAL = 2;
    private static final int START_LINEAR = 3;
    private static final int START_VERTICAL = 1;
    Arc[] mArcs;
    private final double[] mTime;

    public void getPos(double t, double[] v) {
        if (t < this.mArcs[0].mTime1) {
            t = this.mArcs[0].mTime1;
        }
        Arc[] arcArr = this.mArcs;
        if (t > arcArr[arcArr.length - 1].mTime2) {
            Arc[] arcArr2 = this.mArcs;
            t = arcArr2[arcArr2.length - 1].mTime2;
        }
        int i = 0;
        while (true) {
            Arc[] arcArr3 = this.mArcs;
            if (i >= arcArr3.length) {
                return;
            }
            if (t > arcArr3[i].mTime2) {
                i++;
            } else if (this.mArcs[i].linear) {
                v[0] = this.mArcs[i].getLinearX(t);
                v[1] = this.mArcs[i].getLinearY(t);
                return;
            } else {
                this.mArcs[i].setPoint(t);
                v[0] = this.mArcs[i].getX();
                v[1] = this.mArcs[i].getY();
                return;
            }
        }
    }

    public void getPos(double t, float[] v) {
        if (t < this.mArcs[0].mTime1) {
            t = this.mArcs[0].mTime1;
        } else {
            Arc[] arcArr = this.mArcs;
            if (t > arcArr[arcArr.length - 1].mTime2) {
                Arc[] arcArr2 = this.mArcs;
                t = arcArr2[arcArr2.length - 1].mTime2;
            }
        }
        int i = 0;
        while (true) {
            Arc[] arcArr3 = this.mArcs;
            if (i >= arcArr3.length) {
                return;
            }
            if (t > arcArr3[i].mTime2) {
                i++;
            } else if (this.mArcs[i].linear) {
                v[0] = (float) this.mArcs[i].getLinearX(t);
                v[1] = (float) this.mArcs[i].getLinearY(t);
                return;
            } else {
                this.mArcs[i].setPoint(t);
                v[0] = (float) this.mArcs[i].getX();
                v[1] = (float) this.mArcs[i].getY();
                return;
            }
        }
    }

    public void getSlope(double t, double[] v) {
        if (t < this.mArcs[0].mTime1) {
            t = this.mArcs[0].mTime1;
        } else {
            Arc[] arcArr = this.mArcs;
            if (t > arcArr[arcArr.length - 1].mTime2) {
                Arc[] arcArr2 = this.mArcs;
                t = arcArr2[arcArr2.length - 1].mTime2;
            }
        }
        int i = 0;
        while (true) {
            Arc[] arcArr3 = this.mArcs;
            if (i >= arcArr3.length) {
                return;
            }
            if (t > arcArr3[i].mTime2) {
                i++;
            } else if (this.mArcs[i].linear) {
                v[0] = this.mArcs[i].getLinearDX(t);
                v[1] = this.mArcs[i].getLinearDY(t);
                return;
            } else {
                this.mArcs[i].setPoint(t);
                v[0] = this.mArcs[i].getDX();
                v[1] = this.mArcs[i].getDY();
                return;
            }
        }
    }

    public double getPos(double t, int j) {
        if (t < this.mArcs[0].mTime1) {
            t = this.mArcs[0].mTime1;
        } else {
            Arc[] arcArr = this.mArcs;
            if (t > arcArr[arcArr.length - 1].mTime2) {
                Arc[] arcArr2 = this.mArcs;
                t = arcArr2[arcArr2.length - 1].mTime2;
            }
        }
        int i = 0;
        while (true) {
            Arc[] arcArr3 = this.mArcs;
            if (i >= arcArr3.length) {
                return Double.NaN;
            }
            if (t > arcArr3[i].mTime2) {
                i++;
            } else if (!this.mArcs[i].linear) {
                this.mArcs[i].setPoint(t);
                if (j == 0) {
                    return this.mArcs[i].getX();
                }
                return this.mArcs[i].getY();
            } else if (j == 0) {
                return this.mArcs[i].getLinearX(t);
            } else {
                return this.mArcs[i].getLinearY(t);
            }
        }
    }

    public double getSlope(double t, int j) {
        if (t < this.mArcs[0].mTime1) {
            t = this.mArcs[0].mTime1;
        }
        Arc[] arcArr = this.mArcs;
        if (t > arcArr[arcArr.length - 1].mTime2) {
            Arc[] arcArr2 = this.mArcs;
            t = arcArr2[arcArr2.length - 1].mTime2;
        }
        int i = 0;
        while (true) {
            Arc[] arcArr3 = this.mArcs;
            if (i >= arcArr3.length) {
                return Double.NaN;
            }
            if (t > arcArr3[i].mTime2) {
                i++;
            } else if (!this.mArcs[i].linear) {
                this.mArcs[i].setPoint(t);
                if (j == 0) {
                    return this.mArcs[i].getDX();
                }
                return this.mArcs[i].getDY();
            } else if (j == 0) {
                return this.mArcs[i].getLinearDX(t);
            } else {
                return this.mArcs[i].getLinearDY(t);
            }
        }
    }

    public double[] getTimePoints() {
        return this.mTime;
    }

    public ArcCurveFit(int[] arcModes, double[] time, double[][] y) {
        double[] dArr = time;
        this.mTime = dArr;
        this.mArcs = new Arc[(dArr.length - 1)];
        int mode = 1;
        int last = 1;
        int i = 0;
        while (true) {
            Arc[] arcArr = this.mArcs;
            if (i < arcArr.length) {
                int i2 = 2;
                switch (arcModes[i]) {
                    case 0:
                        mode = 3;
                        break;
                    case 1:
                        mode = 1;
                        last = 1;
                        break;
                    case 2:
                        mode = 2;
                        last = 2;
                        break;
                    case 3:
                        mode = last != 1 ? 1 : i2;
                        last = mode;
                        break;
                }
                arcArr[i] = new Arc(mode, dArr[i], dArr[i + 1], y[i][0], y[i][1], y[i + 1][0], y[i + 1][1]);
                i++;
            } else {
                return;
            }
        }
    }

    private static class Arc {
        private static final double EPSILON = 0.001d;
        private static final String TAG = "Arc";
        private static double[] ourPercent = new double[91];
        boolean linear = false;
        double mArcDistance;
        double mArcVelocity;
        double mEllipseA;
        double mEllipseB;
        double mEllipseCenterX;
        double mEllipseCenterY;
        double[] mLut;
        double mOneOverDeltaTime;
        double mTime1;
        double mTime2;
        double mTmpCosAngle;
        double mTmpSinAngle;
        boolean mVertical;
        double mX1;
        double mX2;
        double mY1;
        double mY2;

        Arc(int mode, double t1, double t2, double x1, double y1, double x2, double y2) {
            double dx;
            double dy;
            double d;
            double d2;
            double d3;
            int i = mode;
            double d4 = t1;
            double d5 = t2;
            double d6 = x1;
            double d7 = y1;
            double d8 = x2;
            double d9 = y2;
            boolean z = false;
            int i2 = 1;
            this.mVertical = i == 1 ? true : z;
            this.mTime1 = d4;
            this.mTime2 = d5;
            this.mOneOverDeltaTime = 1.0d / (d5 - d4);
            if (3 == i) {
                this.linear = true;
            }
            double dx2 = d8 - d6;
            double dy2 = d9 - d7;
            if (this.linear || Math.abs(dx2) < EPSILON) {
                dx = dx2;
                dy = dy2;
                d2 = d8;
                d = d7;
                d3 = d6;
            } else if (Math.abs(dy2) < EPSILON) {
                dx = dx2;
                dy = dy2;
                d2 = d8;
                d = d7;
                d3 = d6;
            } else {
                this.mLut = new double[101];
                boolean z2 = this.mVertical;
                this.mEllipseA = ((double) (z2 ? -1 : i2)) * dx2;
                this.mEllipseB = ((double) (z2 ? 1 : -1)) * dy2;
                this.mEllipseCenterX = z2 ? d8 : d6;
                this.mEllipseCenterY = z2 ? d7 : y2;
                double d10 = y2;
                double d11 = dy2;
                double d12 = d8;
                double d13 = dx2;
                double d14 = d7;
                double d15 = d6;
                buildTable(x1, y1, x2, y2);
                this.mArcVelocity = this.mArcDistance * this.mOneOverDeltaTime;
                return;
            }
            this.linear = true;
            this.mX1 = d3;
            this.mX2 = d2;
            this.mY1 = d;
            this.mY2 = y2;
            double dy3 = dy;
            double dx3 = dx;
            double hypot = Math.hypot(dy3, dx3);
            this.mArcDistance = hypot;
            this.mArcVelocity = hypot * this.mOneOverDeltaTime;
            double d16 = this.mTime2;
            double d17 = this.mTime1;
            this.mEllipseCenterX = dx3 / (d16 - d17);
            this.mEllipseCenterY = dy3 / (d16 - d17);
        }

        /* access modifiers changed from: package-private */
        public void setPoint(double time) {
            double angle = lookup((this.mVertical ? this.mTime2 - time : time - this.mTime1) * this.mOneOverDeltaTime) * 1.5707963267948966d;
            this.mTmpSinAngle = Math.sin(angle);
            this.mTmpCosAngle = Math.cos(angle);
        }

        /* access modifiers changed from: package-private */
        public double getX() {
            return this.mEllipseCenterX + (this.mEllipseA * this.mTmpSinAngle);
        }

        /* access modifiers changed from: package-private */
        public double getY() {
            return this.mEllipseCenterY + (this.mEllipseB * this.mTmpCosAngle);
        }

        /* access modifiers changed from: package-private */
        public double getDX() {
            double vx = this.mEllipseA * this.mTmpCosAngle;
            double norm = this.mArcVelocity / Math.hypot(vx, (-this.mEllipseB) * this.mTmpSinAngle);
            return this.mVertical ? (-vx) * norm : vx * norm;
        }

        /* access modifiers changed from: package-private */
        public double getDY() {
            double vx = this.mEllipseA * this.mTmpCosAngle;
            double vy = (-this.mEllipseB) * this.mTmpSinAngle;
            double norm = this.mArcVelocity / Math.hypot(vx, vy);
            return this.mVertical ? (-vy) * norm : vy * norm;
        }

        public double getLinearX(double t) {
            double t2 = (t - this.mTime1) * this.mOneOverDeltaTime;
            double t3 = this.mX1;
            return t3 + ((this.mX2 - t3) * t2);
        }

        public double getLinearY(double t) {
            double t2 = (t - this.mTime1) * this.mOneOverDeltaTime;
            double t3 = this.mY1;
            return t3 + ((this.mY2 - t3) * t2);
        }

        public double getLinearDX(double t) {
            return this.mEllipseCenterX;
        }

        public double getLinearDY(double t) {
            return this.mEllipseCenterY;
        }

        /* access modifiers changed from: package-private */
        public double lookup(double v) {
            if (v <= 0.0d) {
                return 0.0d;
            }
            if (v >= 1.0d) {
                return 1.0d;
            }
            double[] dArr = this.mLut;
            double pos = ((double) (dArr.length - 1)) * v;
            int iv = (int) pos;
            return dArr[iv] + ((dArr[iv + 1] - dArr[iv]) * (pos - ((double) ((int) pos))));
        }

        private void buildTable(double x1, double y1, double x2, double y2) {
            double b;
            double a;
            double a2 = x2 - x1;
            double b2 = y1 - y2;
            double lx = 0.0d;
            double ly = 0.0d;
            double dist = 0.0d;
            int i = 0;
            while (true) {
                double[] dArr = ourPercent;
                if (i >= dArr.length) {
                    break;
                }
                double dist2 = dist;
                double angle = Math.toRadians((((double) i) * 90.0d) / ((double) (dArr.length - 1)));
                double px = a2 * Math.sin(angle);
                double py = b2 * Math.cos(angle);
                if (i > 0) {
                    a = a2;
                    b = b2;
                    double dist3 = Math.hypot(px - lx, py - ly) + dist2;
                    ourPercent[i] = dist3;
                    dist2 = dist3;
                } else {
                    a = a2;
                    b = b2;
                }
                lx = px;
                ly = py;
                i++;
                dist = dist2;
                a2 = a;
                b2 = b;
            }
            double d = b2;
            double d2 = dist;
            this.mArcDistance = dist;
            int i2 = 0;
            while (true) {
                double[] dArr2 = ourPercent;
                if (i2 >= dArr2.length) {
                    break;
                }
                dArr2[i2] = dArr2[i2] / dist;
                i2++;
            }
            int i3 = 0;
            while (true) {
                double[] dArr3 = this.mLut;
                if (i3 < dArr3.length) {
                    double pos = ((double) i3) / ((double) (dArr3.length - 1));
                    int index = Arrays.binarySearch(ourPercent, pos);
                    if (index >= 0) {
                        this.mLut[i3] = (double) (index / (ourPercent.length - 1));
                    } else if (index == -1) {
                        this.mLut[i3] = 0.0d;
                    } else {
                        int p1 = (-index) - 2;
                        double[] dArr4 = ourPercent;
                        double d3 = pos;
                        int i4 = index;
                        this.mLut[i3] = (((double) p1) + ((pos - dArr4[p1]) / (dArr4[(-index) - 1] - dArr4[p1]))) / ((double) (dArr4.length - 1));
                    }
                    i3++;
                } else {
                    return;
                }
            }
        }
    }
}
