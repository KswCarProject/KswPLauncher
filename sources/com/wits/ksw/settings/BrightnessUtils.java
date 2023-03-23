package com.wits.ksw.settings;

import com.wits.ksw.MathUtils;

public class BrightnessUtils {
    private static final float A = 0.17883277f;
    private static final float B = 0.28466892f;
    public static final int BMW_ID8_GAMMA_SPACE_MAX = 100;
    private static final float C = 0.5599107f;
    public static final int GAMMA_SPACE_MAX = 1023;
    private static final float R = 0.5f;

    public static final int convertGammaToLinear(int val, int min, int max) {
        float ret;
        float normalizedVal = MathUtils.norm(0.0f, 1023.0f, (float) val);
        if (normalizedVal <= R) {
            ret = MathUtils.sq(normalizedVal / R);
        } else {
            ret = MathUtils.exp((normalizedVal - C) / A) + B;
        }
        return Math.round(MathUtils.lerp((float) min, (float) max, ret / 12.0f));
    }

    public static final int convertGammaToLinearBmwId8(int val, int min, int max) {
        float ret;
        float normalizedVal = MathUtils.norm(0.0f, 100.0f, (float) val);
        if (normalizedVal <= R) {
            ret = MathUtils.sq(normalizedVal / R);
        } else {
            ret = MathUtils.exp((normalizedVal - C) / A) + B;
        }
        return Math.round(MathUtils.lerp((float) min, (float) max, ret / 12.0f));
    }

    public static final int convertLinearToGamma(int val, int min, int max) {
        float ret;
        float normalizedVal = MathUtils.norm((float) min, (float) max, (float) val) * 12.0f;
        if (normalizedVal <= 1.0f) {
            ret = MathUtils.sqrt(normalizedVal) * R;
        } else {
            ret = C + (MathUtils.log(normalizedVal - B) * A);
        }
        return Math.round(MathUtils.lerp(0.0f, 1023.0f, ret));
    }

    public static final int convertLinearToGammaBmwId8(int val, int min, int max) {
        float ret;
        float normalizedVal = MathUtils.norm((float) min, (float) max, (float) val) * 12.0f;
        if (normalizedVal <= 1.0f) {
            ret = MathUtils.sqrt(normalizedVal) * R;
        } else {
            ret = C + (MathUtils.log(normalizedVal - B) * A);
        }
        return Math.round(MathUtils.lerp(0.0f, 100.0f, ret));
    }

    public static double getPercentage(double value, int min, int max) {
        if (value > ((double) max)) {
            return 1.0d;
        }
        if (value < ((double) min)) {
            return 0.0d;
        }
        return (value - ((double) min)) / ((double) (max - min));
    }
}
