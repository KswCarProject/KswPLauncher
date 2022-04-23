package android.support.constraint.motion.utils;

import android.util.Log;
import java.util.Arrays;

public class Easing {
    private static final String ACCELERATE = "cubic(0.4, 0.05, 0.8, 0.7)";
    private static final String ACCELERATE_NAME = "accelerate";
    private static final String DECELERATE = "cubic(0.0, 0.0, 0.2, 0.95)";
    private static final String DECELERATE_NAME = "decelerate";
    private static final String LINEAR = "cubic(1, 1, 0, 0)";
    private static final String LINEAR_NAME = "linear";
    public static String[] NAMED_EASING = {STANDARD_NAME, ACCELERATE_NAME, DECELERATE_NAME, LINEAR_NAME};
    private static final String STANDARD = "cubic(0.4, 0.0, 0.2, 1)";
    private static final String STANDARD_NAME = "standard";
    static Easing sDefault = new Easing();
    String str = "identity";

    public static Easing getInterpolator(String configString) {
        if (configString == null) {
            return null;
        }
        if (configString.startsWith("cubic")) {
            return new CubicEasing(configString);
        }
        char c = 65535;
        switch (configString.hashCode()) {
            case -1354466595:
                if (configString.equals(ACCELERATE_NAME)) {
                    c = 1;
                    break;
                }
                break;
            case -1263948740:
                if (configString.equals(DECELERATE_NAME)) {
                    c = 2;
                    break;
                }
                break;
            case -1102672091:
                if (configString.equals(LINEAR_NAME)) {
                    c = 3;
                    break;
                }
                break;
            case 1312628413:
                if (configString.equals(STANDARD_NAME)) {
                    c = 0;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return new CubicEasing(STANDARD);
            case 1:
                return new CubicEasing(ACCELERATE);
            case 2:
                return new CubicEasing(DECELERATE);
            case 3:
                return new CubicEasing(LINEAR);
            default:
                Log.e("ConstraintSet", "transitionEasing syntax error syntax:transitionEasing=\"cubic(1.0,0.5,0.0,0.6)\" or " + Arrays.toString(NAMED_EASING));
                return sDefault;
        }
    }

    public double get(double x) {
        return x;
    }

    public String toString() {
        return this.str;
    }

    public double getDiff(double x) {
        return 1.0d;
    }

    static class CubicEasing extends Easing {
        private static double d_error = 1.0E-4d;
        private static double error = 0.01d;
        double x1;
        double x2;
        double y1;
        double y2;

        CubicEasing(String configString) {
            this.str = configString;
            int start = configString.indexOf(40);
            int off1 = configString.indexOf(44, start);
            this.x1 = Double.parseDouble(configString.substring(start + 1, off1).trim());
            int off2 = configString.indexOf(44, off1 + 1);
            this.y1 = Double.parseDouble(configString.substring(off1 + 1, off2).trim());
            int off3 = configString.indexOf(44, off2 + 1);
            this.x2 = Double.parseDouble(configString.substring(off2 + 1, off3).trim());
            this.y2 = Double.parseDouble(configString.substring(off3 + 1, configString.indexOf(41, off3 + 1)).trim());
        }

        public CubicEasing(double x12, double y12, double x22, double y22) {
            setup(x12, y12, x22, y22);
        }

        /* access modifiers changed from: package-private */
        public void setup(double x12, double y12, double x22, double y22) {
            this.x1 = x12;
            this.y1 = y12;
            this.x2 = x22;
            this.y2 = y22;
        }

        private double getX(double t) {
            double t1 = 1.0d - t;
            return (this.x1 * t1 * 3.0d * t1 * t) + (this.x2 * 3.0d * t1 * t * t) + (t * t * t);
        }

        private double getY(double t) {
            double t1 = 1.0d - t;
            return (this.y1 * t1 * 3.0d * t1 * t) + (this.y2 * 3.0d * t1 * t * t) + (t * t * t);
        }

        private double getDiffX(double t) {
            double t1 = 1.0d - t;
            double d = this.x1;
            double d2 = this.x2;
            return (t1 * 3.0d * t1 * d) + (6.0d * t1 * t * (d2 - d)) + (3.0d * t * t * (1.0d - d2));
        }

        private double getDiffY(double t) {
            double t1 = 1.0d - t;
            double d = this.y1;
            double d2 = this.y2;
            return (t1 * 3.0d * t1 * d) + (6.0d * t1 * t * (d2 - d)) + (3.0d * t * t * (1.0d - d2));
        }

        public double getDiff(double x) {
            double t = 0.5d;
            double range = 0.5d;
            while (range > d_error) {
                range *= 0.5d;
                if (getX(t) < x) {
                    t += range;
                } else {
                    t -= range;
                }
            }
            double x12 = getX(t - range);
            double x22 = getX(t + range);
            return (getY(t + range) - getY(t - range)) / (x22 - x12);
        }

        public double get(double x) {
            if (x <= 0.0d) {
                return 0.0d;
            }
            if (x >= 1.0d) {
                return 1.0d;
            }
            double t = 0.5d;
            double range = 0.5d;
            while (range > error) {
                range *= 0.5d;
                if (getX(t) < x) {
                    t += range;
                } else {
                    t -= range;
                }
            }
            double x12 = getX(t - range);
            double x22 = getX(t + range);
            double y12 = getY(t - range);
            return (((getY(t + range) - y12) * (x - x12)) / (x22 - x12)) + y12;
        }
    }
}
