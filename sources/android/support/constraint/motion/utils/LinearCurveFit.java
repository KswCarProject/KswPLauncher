package android.support.constraint.motion.utils;

/* loaded from: classes.dex */
public class LinearCurveFit extends CurveFit {
    private static final String TAG = "LinearCurveFit";

    /* renamed from: mT */
    private double[] f21mT;
    private double mTotalLength;

    /* renamed from: mY */
    private double[][] f22mY;

    public LinearCurveFit(double[] time, double[][] y) {
        int dim;
        double px;
        this.mTotalLength = Double.NaN;
        int length = time.length;
        char c = 0;
        int dim2 = y[0].length;
        this.f21mT = time;
        this.f22mY = y;
        if (dim2 > 2) {
            double sum = 0.0d;
            double lastx = 0.0d;
            double lasty = 0.0d;
            int i = 0;
            while (i < time.length) {
                double px2 = y[i][c];
                double py = y[i][c];
                if (i <= 0) {
                    dim = dim2;
                    px = px2;
                } else {
                    dim = dim2;
                    px = px2;
                    sum += Math.hypot(px2 - lastx, py - lasty);
                }
                lastx = px;
                lasty = py;
                i++;
                dim2 = dim;
                c = 0;
            }
            this.mTotalLength = 0.0d;
        }
    }

    private double getLength2D(double t) {
        double px;
        if (Double.isNaN(this.mTotalLength)) {
            return 0.0d;
        }
        double[] dArr = this.f21mT;
        int n = dArr.length;
        if (t <= dArr[0]) {
            return 0.0d;
        }
        if (t >= dArr[n - 1]) {
            return this.mTotalLength;
        }
        double sum = 0.0d;
        double last_x = 0.0d;
        double last_y = 0.0d;
        for (int i = 0; i < n - 1; i++) {
            double[][] dArr2 = this.f22mY;
            double px2 = dArr2[i][0];
            double py = dArr2[i][1];
            if (i <= 0) {
                px = px2;
            } else {
                px = px2;
                sum += Math.hypot(px2 - last_x, py - last_y);
            }
            last_x = px;
            last_y = py;
            double[] dArr3 = this.f21mT;
            if (t == dArr3[i]) {
                return sum;
            }
            if (t < dArr3[i + 1]) {
                double h = dArr3[i + 1] - dArr3[i];
                double x = (t - dArr3[i]) / h;
                double[][] dArr4 = this.f22mY;
                double x1 = dArr4[i][0];
                double x2 = dArr4[i + 1][0];
                double y1 = dArr4[i][1];
                double y2 = dArr4[i + 1][1];
                return sum + Math.hypot(py - (((1.0d - x) * y1) + (y2 * x)), px - (((1.0d - x) * x1) + (x2 * x)));
            }
        }
        return 0.0d;
    }

    @Override // android.support.constraint.motion.utils.CurveFit
    public void getPos(double t, double[] v) {
        double[] dArr = this.f21mT;
        int n = dArr.length;
        int dim = this.f22mY[0].length;
        if (t <= dArr[0]) {
            for (int j = 0; j < dim; j++) {
                v[j] = this.f22mY[0][j];
            }
        } else if (t >= dArr[n - 1]) {
            for (int j2 = 0; j2 < dim; j2++) {
                v[j2] = this.f22mY[n - 1][j2];
            }
        } else {
            for (int i = 0; i < n - 1; i++) {
                if (t == this.f21mT[i]) {
                    for (int j3 = 0; j3 < dim; j3++) {
                        v[j3] = this.f22mY[i][j3];
                    }
                }
                double[] dArr2 = this.f21mT;
                if (t < dArr2[i + 1]) {
                    double h = dArr2[i + 1] - dArr2[i];
                    double x = (t - dArr2[i]) / h;
                    for (int j4 = 0; j4 < dim; j4++) {
                        double[][] dArr3 = this.f22mY;
                        double y1 = dArr3[i][j4];
                        double y2 = dArr3[i + 1][j4];
                        v[j4] = ((1.0d - x) * y1) + (y2 * x);
                    }
                    return;
                }
            }
        }
    }

    @Override // android.support.constraint.motion.utils.CurveFit
    public void getPos(double t, float[] v) {
        double[] dArr = this.f21mT;
        int n = dArr.length;
        int dim = this.f22mY[0].length;
        if (t <= dArr[0]) {
            for (int j = 0; j < dim; j++) {
                v[j] = (float) this.f22mY[0][j];
            }
        } else if (t >= dArr[n - 1]) {
            for (int j2 = 0; j2 < dim; j2++) {
                v[j2] = (float) this.f22mY[n - 1][j2];
            }
        } else {
            for (int i = 0; i < n - 1; i++) {
                if (t == this.f21mT[i]) {
                    for (int j3 = 0; j3 < dim; j3++) {
                        v[j3] = (float) this.f22mY[i][j3];
                    }
                }
                double[] dArr2 = this.f21mT;
                if (t < dArr2[i + 1]) {
                    double h = dArr2[i + 1] - dArr2[i];
                    double x = (t - dArr2[i]) / h;
                    for (int j4 = 0; j4 < dim; j4++) {
                        double[][] dArr3 = this.f22mY;
                        double y1 = dArr3[i][j4];
                        double y2 = dArr3[i + 1][j4];
                        v[j4] = (float) (((1.0d - x) * y1) + (y2 * x));
                    }
                    return;
                }
            }
        }
    }

    @Override // android.support.constraint.motion.utils.CurveFit
    public double getPos(double t, int j) {
        double[] dArr = this.f21mT;
        int n = dArr.length;
        if (t <= dArr[0]) {
            return this.f22mY[0][j];
        }
        if (t >= dArr[n - 1]) {
            return this.f22mY[n - 1][j];
        }
        for (int i = 0; i < n - 1; i++) {
            double[] dArr2 = this.f21mT;
            if (t == dArr2[i]) {
                return this.f22mY[i][j];
            }
            if (t < dArr2[i + 1]) {
                double h = dArr2[i + 1] - dArr2[i];
                double x = (t - dArr2[i]) / h;
                double[][] dArr3 = this.f22mY;
                double y1 = dArr3[i][j];
                double y2 = dArr3[i + 1][j];
                return ((1.0d - x) * y1) + (y2 * x);
            }
        }
        return 0.0d;
    }

    @Override // android.support.constraint.motion.utils.CurveFit
    public void getSlope(double t, double[] v) {
        double t2;
        double[] dArr = this.f21mT;
        int n = dArr.length;
        int dim = this.f22mY[0].length;
        if (t <= dArr[0]) {
            t2 = dArr[0];
        } else if (t < dArr[n - 1]) {
            t2 = t;
        } else {
            t2 = dArr[n - 1];
        }
        for (int i = 0; i < n - 1; i++) {
            double[] dArr2 = this.f21mT;
            if (t2 <= dArr2[i + 1]) {
                double h = dArr2[i + 1] - dArr2[i];
                double d = (t2 - dArr2[i]) / h;
                for (int j = 0; j < dim; j++) {
                    double[][] dArr3 = this.f22mY;
                    double y1 = dArr3[i][j];
                    double y2 = dArr3[i + 1][j];
                    v[j] = (y2 - y1) / h;
                }
                return;
            }
        }
    }

    @Override // android.support.constraint.motion.utils.CurveFit
    public double getSlope(double t, int j) {
        double t2;
        double[] dArr = this.f21mT;
        int n = dArr.length;
        if (t < dArr[0]) {
            t2 = dArr[0];
        } else if (t < dArr[n - 1]) {
            t2 = t;
        } else {
            t2 = dArr[n - 1];
        }
        for (int i = 0; i < n - 1; i++) {
            double[] dArr2 = this.f21mT;
            if (t2 <= dArr2[i + 1]) {
                double h = dArr2[i + 1] - dArr2[i];
                double d = (t2 - dArr2[i]) / h;
                double[][] dArr3 = this.f22mY;
                double y1 = dArr3[i][j];
                double y2 = dArr3[i + 1][j];
                return (y2 - y1) / h;
            }
        }
        return 0.0d;
    }

    @Override // android.support.constraint.motion.utils.CurveFit
    public double[] getTimePoints() {
        return this.f21mT;
    }
}
