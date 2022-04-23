package android.support.constraint.motion.utils;

public class LinearCurveFit extends CurveFit {
    private static final String TAG = "LinearCurveFit";
    private double[] mT;
    private double mTotalLength = Double.NaN;
    private double[][] mY;

    public LinearCurveFit(double[] time, double[][] y) {
        double px;
        int dim;
        double[] dArr = time;
        double[][] dArr2 = y;
        int length = dArr.length;
        char c = 0;
        int dim2 = dArr2[0].length;
        this.mT = dArr;
        this.mY = dArr2;
        if (dim2 > 2) {
            double sum = 0.0d;
            double lastx = 0.0d;
            double lasty = 0.0d;
            int i = 0;
            while (i < dArr.length) {
                double px2 = dArr2[i][c];
                double py = dArr2[i][c];
                if (i > 0) {
                    dim = dim2;
                    px = px2;
                    sum += Math.hypot(px2 - lastx, py - lasty);
                } else {
                    dim = dim2;
                    px = px2;
                }
                lastx = px;
                lasty = py;
                i++;
                dim2 = dim;
                c = 0;
            }
            this.mTotalLength = 0.0d;
            return;
        }
    }

    private double getLength2D(double t) {
        double px;
        if (Double.isNaN(this.mTotalLength)) {
            return 0.0d;
        }
        double[] dArr = this.mT;
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
            double[][] dArr2 = this.mY;
            double px2 = dArr2[i][0];
            double py = dArr2[i][1];
            if (i > 0) {
                px = px2;
                sum += Math.hypot(px2 - last_x, py - last_y);
            } else {
                px = px2;
            }
            last_x = px;
            last_y = py;
            double[] dArr3 = this.mT;
            if (t == dArr3[i]) {
                return sum;
            }
            if (t < dArr3[i + 1]) {
                double x = (t - dArr3[i]) / (dArr3[i + 1] - dArr3[i]);
                double[][] dArr4 = this.mY;
                double x1 = dArr4[i][0];
                double x2 = dArr4[i + 1][0];
                int i2 = n;
                double d = x;
                return sum + Math.hypot(py - (((1.0d - x) * dArr4[i][1]) + (dArr4[i + 1][1] * x)), px - (((1.0d - x) * x1) + (x2 * x)));
            }
        }
        return 0.0d;
    }

    public void getPos(double t, double[] v) {
        double[] dArr = this.mT;
        int n = dArr.length;
        int dim = this.mY[0].length;
        if (t <= dArr[0]) {
            for (int j = 0; j < dim; j++) {
                v[j] = this.mY[0][j];
            }
        } else if (t >= dArr[n - 1]) {
            for (int j2 = 0; j2 < dim; j2++) {
                v[j2] = this.mY[n - 1][j2];
            }
        } else {
            for (int i = 0; i < n - 1; i++) {
                if (t == this.mT[i]) {
                    for (int j3 = 0; j3 < dim; j3++) {
                        v[j3] = this.mY[i][j3];
                    }
                }
                double[] dArr2 = this.mT;
                if (t < dArr2[i + 1]) {
                    double x = (t - dArr2[i]) / (dArr2[i + 1] - dArr2[i]);
                    for (int j4 = 0; j4 < dim; j4++) {
                        double[][] dArr3 = this.mY;
                        v[j4] = ((1.0d - x) * dArr3[i][j4]) + (dArr3[i + 1][j4] * x);
                    }
                    return;
                }
            }
        }
    }

    public void getPos(double t, float[] v) {
        double[] dArr = this.mT;
        int n = dArr.length;
        int dim = this.mY[0].length;
        if (t <= dArr[0]) {
            for (int j = 0; j < dim; j++) {
                v[j] = (float) this.mY[0][j];
            }
        } else if (t >= dArr[n - 1]) {
            for (int j2 = 0; j2 < dim; j2++) {
                v[j2] = (float) this.mY[n - 1][j2];
            }
        } else {
            for (int i = 0; i < n - 1; i++) {
                if (t == this.mT[i]) {
                    for (int j3 = 0; j3 < dim; j3++) {
                        v[j3] = (float) this.mY[i][j3];
                    }
                }
                double[] dArr2 = this.mT;
                if (t < dArr2[i + 1]) {
                    double x = (t - dArr2[i]) / (dArr2[i + 1] - dArr2[i]);
                    for (int j4 = 0; j4 < dim; j4++) {
                        double[][] dArr3 = this.mY;
                        v[j4] = (float) (((1.0d - x) * dArr3[i][j4]) + (dArr3[i + 1][j4] * x));
                    }
                    return;
                }
            }
        }
    }

    public double getPos(double t, int j) {
        double[] dArr = this.mT;
        int n = dArr.length;
        if (t <= dArr[0]) {
            return this.mY[0][j];
        }
        if (t >= dArr[n - 1]) {
            return this.mY[n - 1][j];
        }
        for (int i = 0; i < n - 1; i++) {
            double[] dArr2 = this.mT;
            if (t == dArr2[i]) {
                return this.mY[i][j];
            }
            if (t < dArr2[i + 1]) {
                double x = (t - dArr2[i]) / (dArr2[i + 1] - dArr2[i]);
                double[][] dArr3 = this.mY;
                return ((1.0d - x) * dArr3[i][j]) + (dArr3[i + 1][j] * x);
            }
        }
        return 0.0d;
    }

    public void getSlope(double t, double[] v) {
        double t2;
        double[] dArr = this.mT;
        int n = dArr.length;
        int dim = this.mY[0].length;
        if (t <= dArr[0]) {
            t2 = dArr[0];
        } else if (t >= dArr[n - 1]) {
            t2 = dArr[n - 1];
        } else {
            t2 = t;
        }
        for (int i = 0; i < n - 1; i++) {
            double[] dArr2 = this.mT;
            if (t2 <= dArr2[i + 1]) {
                double h = dArr2[i + 1] - dArr2[i];
                double d = (t2 - dArr2[i]) / h;
                for (int j = 0; j < dim; j++) {
                    double[][] dArr3 = this.mY;
                    v[j] = (dArr3[i + 1][j] - dArr3[i][j]) / h;
                }
                return;
            }
        }
    }

    public double getSlope(double t, int j) {
        double t2;
        double[] dArr = this.mT;
        int n = dArr.length;
        if (t < dArr[0]) {
            t2 = dArr[0];
        } else if (t >= dArr[n - 1]) {
            t2 = dArr[n - 1];
        } else {
            t2 = t;
        }
        for (int i = 0; i < n - 1; i++) {
            double[] dArr2 = this.mT;
            if (t2 <= dArr2[i + 1]) {
                double h = dArr2[i + 1] - dArr2[i];
                double d = (t2 - dArr2[i]) / h;
                double[][] dArr3 = this.mY;
                return (dArr3[i + 1][j] - dArr3[i][j]) / h;
            }
        }
        return 0.0d;
    }

    public double[] getTimePoints() {
        return this.mT;
    }
}
