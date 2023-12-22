package com.wits.ksw.settings;

import com.wits.ksw.MathUtils;

/* loaded from: classes3.dex */
public class BrightnessUtils {

    /* renamed from: A */
    private static final float f209A = 0.17883277f;

    /* renamed from: B */
    private static final float f210B = 0.28466892f;
    public static final int BMW_ID8_GAMMA_SPACE_MAX = 100;

    /* renamed from: C */
    private static final float f211C = 0.5599107f;
    public static final int GAMMA_SPACE_MAX = 1023;

    /* renamed from: R */
    private static final float f212R = 0.5f;

    public static final int convertGammaToLinear(int val, int min, int max) {
        float ret;
        float normalizedVal = MathUtils.norm(0.0f, 1023.0f, val);
        if (normalizedVal <= f212R) {
            ret = MathUtils.m45sq(normalizedVal / f212R);
        } else {
            ret = MathUtils.exp((normalizedVal - f211C) / f209A) + f210B;
        }
        return Math.round(MathUtils.lerp(min, max, ret / 12.0f));
    }

    public static final int convertGammaToLinearBmwId8(int val, int min, int max) {
        float ret;
        float normalizedVal = MathUtils.norm(0.0f, 100.0f, val);
        if (normalizedVal <= f212R) {
            ret = MathUtils.m45sq(normalizedVal / f212R);
        } else {
            ret = MathUtils.exp((normalizedVal - f211C) / f209A) + f210B;
        }
        return Math.round(MathUtils.lerp(min, max, ret / 12.0f));
    }

    public static final int convertLinearToGamma(int val, int min, int max) {
        float ret;
        float normalizedVal = MathUtils.norm(min, max, val) * 12.0f;
        if (normalizedVal <= 1.0f) {
            ret = MathUtils.sqrt(normalizedVal) * f212R;
        } else {
            ret = f211C + (MathUtils.log(normalizedVal - f210B) * f209A);
        }
        return Math.round(MathUtils.lerp(0.0f, 1023.0f, ret));
    }

    public static final int convertLinearToGammaBmwId8(int val, int min, int max) {
        float ret;
        float normalizedVal = MathUtils.norm(min, max, val) * 12.0f;
        if (normalizedVal <= 1.0f) {
            ret = MathUtils.sqrt(normalizedVal) * f212R;
        } else {
            ret = f211C + (MathUtils.log(normalizedVal - f210B) * f209A);
        }
        return Math.round(MathUtils.lerp(0.0f, 100.0f, ret));
    }

    public static double getPercentage(double value, int min, int max) {
        if (value > max) {
            return 1.0d;
        }
        if (value < min) {
            return 0.0d;
        }
        return (value - min) / (max - min);
    }
}
