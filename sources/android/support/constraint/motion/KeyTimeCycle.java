package android.support.constraint.motion;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.constraint.R;
import android.support.constraint.motion.utils.CurveFit;
import android.support.v4.app.NotificationCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import java.util.HashMap;
import java.util.HashSet;

public class KeyTimeCycle extends Key {
    public static final int KEY_TYPE = 3;
    static final String NAME = "KeyTimeCycle";
    private static final String TAG = "KeyTimeCycle";
    /* access modifiers changed from: private */
    public float mAlpha = Float.NaN;
    /* access modifiers changed from: private */
    public int mCurveFit = -1;
    /* access modifiers changed from: private */
    public float mElevation = Float.NaN;
    /* access modifiers changed from: private */
    public float mProgress = Float.NaN;
    /* access modifiers changed from: private */
    public float mRotation = Float.NaN;
    /* access modifiers changed from: private */
    public float mRotationX = Float.NaN;
    /* access modifiers changed from: private */
    public float mRotationY = Float.NaN;
    /* access modifiers changed from: private */
    public float mScaleX = Float.NaN;
    /* access modifiers changed from: private */
    public float mScaleY = Float.NaN;
    /* access modifiers changed from: private */
    public String mTransitionEasing;
    /* access modifiers changed from: private */
    public float mTransitionPathRotate = Float.NaN;
    /* access modifiers changed from: private */
    public float mTranslationX = Float.NaN;
    /* access modifiers changed from: private */
    public float mTranslationY = Float.NaN;
    /* access modifiers changed from: private */
    public float mTranslationZ = Float.NaN;
    /* access modifiers changed from: private */
    public float mWaveOffset = 0.0f;
    private CurveFit mWaveOffsetSpline;
    /* access modifiers changed from: private */
    public float mWavePeriod = Float.NaN;
    private CurveFit mWavePeriodSpline;
    /* access modifiers changed from: private */
    public int mWaveShape = 0;

    public KeyTimeCycle() {
        this.mType = 3;
        this.mCustomConstraints = new HashMap();
    }

    public void load(Context context, AttributeSet attrs) {
        Loader.read(this, context.obtainStyledAttributes(attrs, R.styleable.KeyTimeCycle));
    }

    /* access modifiers changed from: package-private */
    public int getCurveFit() {
        return this.mCurveFit;
    }

    public void getAttributeNames(HashSet<String> attributes) {
        if (!Float.isNaN(this.mAlpha)) {
            attributes.add("alpha");
        }
        if (!Float.isNaN(this.mElevation)) {
            attributes.add("elevation");
        }
        if (!Float.isNaN(this.mRotation)) {
            attributes.add("rotation");
        }
        if (!Float.isNaN(this.mRotationX)) {
            attributes.add("rotationX");
        }
        if (!Float.isNaN(this.mRotationY)) {
            attributes.add("rotationY");
        }
        if (!Float.isNaN(this.mTranslationX)) {
            attributes.add("translationX");
        }
        if (!Float.isNaN(this.mTranslationY)) {
            attributes.add("translationY");
        }
        if (!Float.isNaN(this.mTranslationZ)) {
            attributes.add("translationZ");
        }
        if (!Float.isNaN(this.mTransitionPathRotate)) {
            attributes.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.mScaleX)) {
            attributes.add("scaleX");
        }
        if (!Float.isNaN(this.mScaleY)) {
            attributes.add("scaleY");
        }
        if (!Float.isNaN(this.mProgress)) {
            attributes.add(NotificationCompat.CATEGORY_PROGRESS);
        }
        if (this.mCustomConstraints.size() > 0) {
            for (String s : this.mCustomConstraints.keySet()) {
                attributes.add("CUSTOM," + s);
            }
        }
    }

    public void setInterpolation(HashMap<String, Integer> interpolation) {
        if (this.mCurveFit != -1) {
            if (!Float.isNaN(this.mAlpha)) {
                interpolation.put("alpha", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mElevation)) {
                interpolation.put("elevation", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mRotation)) {
                interpolation.put("rotation", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mRotationX)) {
                interpolation.put("rotationX", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mRotationY)) {
                interpolation.put("rotationY", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mTranslationX)) {
                interpolation.put("translationX", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mTranslationY)) {
                interpolation.put("translationY", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mTranslationZ)) {
                interpolation.put("translationZ", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mTransitionPathRotate)) {
                interpolation.put("transitionPathRotate", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mScaleX)) {
                interpolation.put("scaleX", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mScaleX)) {
                interpolation.put("scaleY", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mProgress)) {
                interpolation.put(NotificationCompat.CATEGORY_PROGRESS, Integer.valueOf(this.mCurveFit));
            }
            if (this.mCustomConstraints.size() > 0) {
                for (String s : this.mCustomConstraints.keySet()) {
                    interpolation.put("CUSTOM," + s, Integer.valueOf(this.mCurveFit));
                }
            }
        }
    }

    public void addValues(HashMap<String, SplineSet> hashMap) {
        throw new IllegalArgumentException(" KeyTimeCycles do not support SplineSet");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x006d, code lost:
        if (r1.equals("elevation") != false) goto L_0x00d2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addTimeValues(java.util.HashMap<java.lang.String, android.support.constraint.motion.TimeCycleSplineSet> r12) {
        /*
            r11 = this;
            java.util.Set r0 = r12.keySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x0008:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0211
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r2 = r12.get(r1)
            android.support.constraint.motion.TimeCycleSplineSet r2 = (android.support.constraint.motion.TimeCycleSplineSet) r2
            java.lang.String r3 = "CUSTOM"
            boolean r4 = r1.startsWith(r3)
            r5 = 1
            if (r4 == 0) goto L_0x0046
            int r3 = r3.length()
            int r3 = r3 + r5
            java.lang.String r3 = r1.substring(r3)
            java.util.HashMap r4 = r11.mCustomConstraints
            java.lang.Object r4 = r4.get(r3)
            android.support.constraint.ConstraintAttribute r4 = (android.support.constraint.ConstraintAttribute) r4
            if (r4 == 0) goto L_0x0008
            r5 = r2
            android.support.constraint.motion.TimeCycleSplineSet$CustomSet r5 = (android.support.constraint.motion.TimeCycleSplineSet.CustomSet) r5
            int r6 = r11.mFramePosition
            float r8 = r11.mWavePeriod
            int r9 = r11.mWaveShape
            float r10 = r11.mWaveOffset
            r7 = r4
            r5.setPoint((int) r6, (android.support.constraint.ConstraintAttribute) r7, (float) r8, (int) r9, (float) r10)
            goto L_0x0008
        L_0x0046:
            r3 = -1
            int r4 = r1.hashCode()
            switch(r4) {
                case -1249320806: goto L_0x00c7;
                case -1249320805: goto L_0x00bd;
                case -1225497657: goto L_0x00b1;
                case -1225497656: goto L_0x00a5;
                case -1225497655: goto L_0x0099;
                case -1001078227: goto L_0x008e;
                case -908189618: goto L_0x0084;
                case -908189617: goto L_0x007a;
                case -40300674: goto L_0x0070;
                case -4379043: goto L_0x0067;
                case 37232917: goto L_0x005b;
                case 92909918: goto L_0x0050;
                default: goto L_0x004e;
            }
        L_0x004e:
            goto L_0x00d1
        L_0x0050:
            java.lang.String r4 = "alpha"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x004e
            r5 = 0
            goto L_0x00d2
        L_0x005b:
            java.lang.String r4 = "transitionPathRotate"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x004e
            r5 = 5
            goto L_0x00d2
        L_0x0067:
            java.lang.String r4 = "elevation"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x004e
            goto L_0x00d2
        L_0x0070:
            java.lang.String r4 = "rotation"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x004e
            r5 = 2
            goto L_0x00d2
        L_0x007a:
            java.lang.String r4 = "scaleY"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x004e
            r5 = 7
            goto L_0x00d2
        L_0x0084:
            java.lang.String r4 = "scaleX"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x004e
            r5 = 6
            goto L_0x00d2
        L_0x008e:
            java.lang.String r4 = "progress"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x004e
            r5 = 11
            goto L_0x00d2
        L_0x0099:
            java.lang.String r4 = "translationZ"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x004e
            r5 = 10
            goto L_0x00d2
        L_0x00a5:
            java.lang.String r4 = "translationY"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x004e
            r5 = 9
            goto L_0x00d2
        L_0x00b1:
            java.lang.String r4 = "translationX"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x004e
            r5 = 8
            goto L_0x00d2
        L_0x00bd:
            java.lang.String r4 = "rotationY"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x004e
            r5 = 4
            goto L_0x00d2
        L_0x00c7:
            java.lang.String r4 = "rotationX"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x004e
            r5 = 3
            goto L_0x00d2
        L_0x00d1:
            r5 = r3
        L_0x00d2:
            switch(r5) {
                case 0: goto L_0x01f9;
                case 1: goto L_0x01e2;
                case 2: goto L_0x01cb;
                case 3: goto L_0x01b4;
                case 4: goto L_0x019d;
                case 5: goto L_0x0185;
                case 6: goto L_0x016d;
                case 7: goto L_0x0155;
                case 8: goto L_0x013d;
                case 9: goto L_0x0125;
                case 10: goto L_0x010d;
                case 11: goto L_0x00f5;
                default: goto L_0x00d5;
            }
        L_0x00d5:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "UNKNOWN addValues \""
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r1)
            java.lang.String r4 = "\""
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "KeyTimeCycles"
            android.util.Log.e(r4, r3)
            goto L_0x020f
        L_0x00f5:
            float r3 = r11.mProgress
            boolean r3 = java.lang.Float.isNaN(r3)
            if (r3 != 0) goto L_0x020f
            int r4 = r11.mFramePosition
            float r5 = r11.mProgress
            float r6 = r11.mWavePeriod
            int r7 = r11.mWaveShape
            float r8 = r11.mWaveOffset
            r3 = r2
            r3.setPoint(r4, r5, r6, r7, r8)
            goto L_0x020f
        L_0x010d:
            float r3 = r11.mTranslationZ
            boolean r3 = java.lang.Float.isNaN(r3)
            if (r3 != 0) goto L_0x020f
            int r4 = r11.mFramePosition
            float r5 = r11.mTranslationZ
            float r6 = r11.mWavePeriod
            int r7 = r11.mWaveShape
            float r8 = r11.mWaveOffset
            r3 = r2
            r3.setPoint(r4, r5, r6, r7, r8)
            goto L_0x020f
        L_0x0125:
            float r3 = r11.mTranslationY
            boolean r3 = java.lang.Float.isNaN(r3)
            if (r3 != 0) goto L_0x020f
            int r4 = r11.mFramePosition
            float r5 = r11.mTranslationY
            float r6 = r11.mWavePeriod
            int r7 = r11.mWaveShape
            float r8 = r11.mWaveOffset
            r3 = r2
            r3.setPoint(r4, r5, r6, r7, r8)
            goto L_0x020f
        L_0x013d:
            float r3 = r11.mTranslationX
            boolean r3 = java.lang.Float.isNaN(r3)
            if (r3 != 0) goto L_0x020f
            int r4 = r11.mFramePosition
            float r5 = r11.mTranslationX
            float r6 = r11.mWavePeriod
            int r7 = r11.mWaveShape
            float r8 = r11.mWaveOffset
            r3 = r2
            r3.setPoint(r4, r5, r6, r7, r8)
            goto L_0x020f
        L_0x0155:
            float r3 = r11.mScaleY
            boolean r3 = java.lang.Float.isNaN(r3)
            if (r3 != 0) goto L_0x020f
            int r4 = r11.mFramePosition
            float r5 = r11.mScaleY
            float r6 = r11.mWavePeriod
            int r7 = r11.mWaveShape
            float r8 = r11.mWaveOffset
            r3 = r2
            r3.setPoint(r4, r5, r6, r7, r8)
            goto L_0x020f
        L_0x016d:
            float r3 = r11.mScaleX
            boolean r3 = java.lang.Float.isNaN(r3)
            if (r3 != 0) goto L_0x020f
            int r4 = r11.mFramePosition
            float r5 = r11.mScaleX
            float r6 = r11.mWavePeriod
            int r7 = r11.mWaveShape
            float r8 = r11.mWaveOffset
            r3 = r2
            r3.setPoint(r4, r5, r6, r7, r8)
            goto L_0x020f
        L_0x0185:
            float r3 = r11.mTransitionPathRotate
            boolean r3 = java.lang.Float.isNaN(r3)
            if (r3 != 0) goto L_0x020f
            int r4 = r11.mFramePosition
            float r5 = r11.mTransitionPathRotate
            float r6 = r11.mWavePeriod
            int r7 = r11.mWaveShape
            float r8 = r11.mWaveOffset
            r3 = r2
            r3.setPoint(r4, r5, r6, r7, r8)
            goto L_0x020f
        L_0x019d:
            float r3 = r11.mRotationY
            boolean r3 = java.lang.Float.isNaN(r3)
            if (r3 != 0) goto L_0x020f
            int r4 = r11.mFramePosition
            float r5 = r11.mRotationY
            float r6 = r11.mWavePeriod
            int r7 = r11.mWaveShape
            float r8 = r11.mWaveOffset
            r3 = r2
            r3.setPoint(r4, r5, r6, r7, r8)
            goto L_0x020f
        L_0x01b4:
            float r3 = r11.mRotationX
            boolean r3 = java.lang.Float.isNaN(r3)
            if (r3 != 0) goto L_0x020f
            int r4 = r11.mFramePosition
            float r5 = r11.mRotationX
            float r6 = r11.mWavePeriod
            int r7 = r11.mWaveShape
            float r8 = r11.mWaveOffset
            r3 = r2
            r3.setPoint(r4, r5, r6, r7, r8)
            goto L_0x020f
        L_0x01cb:
            float r3 = r11.mRotation
            boolean r3 = java.lang.Float.isNaN(r3)
            if (r3 != 0) goto L_0x020f
            int r4 = r11.mFramePosition
            float r5 = r11.mRotation
            float r6 = r11.mWavePeriod
            int r7 = r11.mWaveShape
            float r8 = r11.mWaveOffset
            r3 = r2
            r3.setPoint(r4, r5, r6, r7, r8)
            goto L_0x020f
        L_0x01e2:
            float r3 = r11.mElevation
            boolean r3 = java.lang.Float.isNaN(r3)
            if (r3 != 0) goto L_0x020f
            int r4 = r11.mFramePosition
            float r5 = r11.mElevation
            float r6 = r11.mWavePeriod
            int r7 = r11.mWaveShape
            float r8 = r11.mWaveOffset
            r3 = r2
            r3.setPoint(r4, r5, r6, r7, r8)
            goto L_0x020f
        L_0x01f9:
            float r3 = r11.mAlpha
            boolean r3 = java.lang.Float.isNaN(r3)
            if (r3 != 0) goto L_0x020f
            int r4 = r11.mFramePosition
            float r5 = r11.mAlpha
            float r6 = r11.mWavePeriod
            int r7 = r11.mWaveShape
            float r8 = r11.mWaveOffset
            r3 = r2
            r3.setPoint(r4, r5, r6, r7, r8)
        L_0x020f:
            goto L_0x0008
        L_0x0211:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.motion.KeyTimeCycle.addTimeValues(java.util.HashMap):void");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setValue(java.lang.String r2, java.lang.Object r3) {
        /*
            r1 = this;
            int r0 = r2.hashCode()
            switch(r0) {
                case -1812823328: goto L_0x0097;
                case -1249320806: goto L_0x008d;
                case -1249320805: goto L_0x0083;
                case -1225497657: goto L_0x0077;
                case -1225497656: goto L_0x006b;
                case -1001078227: goto L_0x0061;
                case -908189618: goto L_0x0057;
                case -908189617: goto L_0x004c;
                case -40300674: goto L_0x0042;
                case -4379043: goto L_0x0038;
                case 37232917: goto L_0x002b;
                case 92909918: goto L_0x0020;
                case 579057826: goto L_0x0015;
                case 1317633238: goto L_0x0009;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x00a3
        L_0x0009:
            java.lang.String r0 = "mTranslationZ"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 13
            goto L_0x00a4
        L_0x0015:
            java.lang.String r0 = "curveFit"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 1
            goto L_0x00a4
        L_0x0020:
            java.lang.String r0 = "alpha"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 0
            goto L_0x00a4
        L_0x002b:
            java.lang.String r0 = "transitionPathRotate"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 10
            goto L_0x00a4
        L_0x0038:
            java.lang.String r0 = "elevation"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 2
            goto L_0x00a4
        L_0x0042:
            java.lang.String r0 = "rotation"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 4
            goto L_0x00a4
        L_0x004c:
            java.lang.String r0 = "scaleY"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 8
            goto L_0x00a4
        L_0x0057:
            java.lang.String r0 = "scaleX"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 7
            goto L_0x00a4
        L_0x0061:
            java.lang.String r0 = "progress"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 3
            goto L_0x00a4
        L_0x006b:
            java.lang.String r0 = "translationY"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 12
            goto L_0x00a4
        L_0x0077:
            java.lang.String r0 = "translationX"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 11
            goto L_0x00a4
        L_0x0083:
            java.lang.String r0 = "rotationY"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 6
            goto L_0x00a4
        L_0x008d:
            java.lang.String r0 = "rotationX"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 5
            goto L_0x00a4
        L_0x0097:
            java.lang.String r0 = "transitionEasing"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 9
            goto L_0x00a4
        L_0x00a3:
            r0 = -1
        L_0x00a4:
            switch(r0) {
                case 0: goto L_0x0103;
                case 1: goto L_0x00fc;
                case 2: goto L_0x00f5;
                case 3: goto L_0x00ee;
                case 4: goto L_0x00e7;
                case 5: goto L_0x00e0;
                case 6: goto L_0x00d9;
                case 7: goto L_0x00d2;
                case 8: goto L_0x00cb;
                case 9: goto L_0x00c4;
                case 10: goto L_0x00bd;
                case 11: goto L_0x00b6;
                case 12: goto L_0x00af;
                case 13: goto L_0x00a8;
                default: goto L_0x00a7;
            }
        L_0x00a7:
            goto L_0x010a
        L_0x00a8:
            float r0 = r1.toFloat(r3)
            r1.mTranslationZ = r0
            goto L_0x010a
        L_0x00af:
            float r0 = r1.toFloat(r3)
            r1.mTranslationY = r0
            goto L_0x010a
        L_0x00b6:
            float r0 = r1.toFloat(r3)
            r1.mTranslationX = r0
            goto L_0x010a
        L_0x00bd:
            float r0 = r1.toFloat(r3)
            r1.mTransitionPathRotate = r0
            goto L_0x010a
        L_0x00c4:
            java.lang.String r0 = r3.toString()
            r1.mTransitionEasing = r0
            goto L_0x010a
        L_0x00cb:
            float r0 = r1.toFloat(r3)
            r1.mScaleY = r0
            goto L_0x010a
        L_0x00d2:
            float r0 = r1.toFloat(r3)
            r1.mScaleX = r0
            goto L_0x010a
        L_0x00d9:
            float r0 = r1.toFloat(r3)
            r1.mRotationY = r0
            goto L_0x010a
        L_0x00e0:
            float r0 = r1.toFloat(r3)
            r1.mRotationX = r0
            goto L_0x010a
        L_0x00e7:
            float r0 = r1.toFloat(r3)
            r1.mRotation = r0
            goto L_0x010a
        L_0x00ee:
            float r0 = r1.toFloat(r3)
            r1.mProgress = r0
            goto L_0x010a
        L_0x00f5:
            float r0 = r1.toFloat(r3)
            r1.mElevation = r0
            goto L_0x010a
        L_0x00fc:
            int r0 = r1.toInt(r3)
            r1.mCurveFit = r0
            goto L_0x010a
        L_0x0103:
            float r0 = r1.toFloat(r3)
            r1.mAlpha = r0
        L_0x010a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.motion.KeyTimeCycle.setValue(java.lang.String, java.lang.Object):void");
    }

    private static class Loader {
        private static final int ANDROID_ALPHA = 1;
        private static final int ANDROID_ELEVATION = 2;
        private static final int ANDROID_ROTATION = 4;
        private static final int ANDROID_ROTATION_X = 5;
        private static final int ANDROID_ROTATION_Y = 6;
        private static final int ANDROID_SCALE_X = 7;
        private static final int ANDROID_SCALE_Y = 14;
        private static final int ANDROID_TRANSLATION_X = 15;
        private static final int ANDROID_TRANSLATION_Y = 16;
        private static final int ANDROID_TRANSLATION_Z = 17;
        private static final int CURVE_FIT = 13;
        private static final int FRAME_POSITION = 12;
        private static final int PROGRESS = 18;
        private static final int TARGET_ID = 10;
        private static final int TRANSITION_EASING = 9;
        private static final int TRANSITION_PATH_ROTATE = 8;
        private static final int WAVE_OFFSET = 21;
        private static final int WAVE_PERIOD = 20;
        private static final int WAVE_SHAPE = 19;
        private static SparseIntArray mAttrMap;

        private Loader() {
        }

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            mAttrMap = sparseIntArray;
            sparseIntArray.append(R.styleable.KeyTimeCycle_android_alpha, 1);
            mAttrMap.append(R.styleable.KeyTimeCycle_android_elevation, 2);
            mAttrMap.append(R.styleable.KeyTimeCycle_android_rotation, 4);
            mAttrMap.append(R.styleable.KeyTimeCycle_android_rotationX, 5);
            mAttrMap.append(R.styleable.KeyTimeCycle_android_rotationY, 6);
            mAttrMap.append(R.styleable.KeyTimeCycle_android_scaleX, 7);
            mAttrMap.append(R.styleable.KeyTimeCycle_transitionPathRotate, 8);
            mAttrMap.append(R.styleable.KeyTimeCycle_transitionEasing, 9);
            mAttrMap.append(R.styleable.KeyTimeCycle_motionTarget, 10);
            mAttrMap.append(R.styleable.KeyTimeCycle_framePosition, 12);
            mAttrMap.append(R.styleable.KeyTimeCycle_curveFit, 13);
            mAttrMap.append(R.styleable.KeyTimeCycle_android_scaleY, 14);
            mAttrMap.append(R.styleable.KeyTimeCycle_android_translationX, 15);
            mAttrMap.append(R.styleable.KeyTimeCycle_android_translationY, 16);
            mAttrMap.append(R.styleable.KeyTimeCycle_android_translationZ, 17);
            mAttrMap.append(R.styleable.KeyTimeCycle_motionProgress, 18);
            mAttrMap.append(R.styleable.KeyTimeCycle_wavePeriod, 20);
            mAttrMap.append(R.styleable.KeyTimeCycle_waveOffset, 21);
            mAttrMap.append(R.styleable.KeyTimeCycle_waveShape, 19);
        }

        public static void read(KeyTimeCycle c, TypedArray a) {
            int N = a.getIndexCount();
            for (int i = 0; i < N; i++) {
                int attr = a.getIndex(i);
                switch (mAttrMap.get(attr)) {
                    case 1:
                        float unused = c.mAlpha = a.getFloat(attr, c.mAlpha);
                        break;
                    case 2:
                        float unused2 = c.mElevation = a.getDimension(attr, c.mElevation);
                        break;
                    case 4:
                        float unused3 = c.mRotation = a.getFloat(attr, c.mRotation);
                        break;
                    case 5:
                        float unused4 = c.mRotationX = a.getFloat(attr, c.mRotationX);
                        break;
                    case 6:
                        float unused5 = c.mRotationY = a.getFloat(attr, c.mRotationY);
                        break;
                    case 7:
                        float unused6 = c.mScaleX = a.getFloat(attr, c.mScaleX);
                        break;
                    case 8:
                        float unused7 = c.mTransitionPathRotate = a.getFloat(attr, c.mTransitionPathRotate);
                        break;
                    case 9:
                        String unused8 = c.mTransitionEasing = a.getString(attr);
                        break;
                    case 10:
                        if (!MotionLayout.IS_IN_EDIT_MODE) {
                            if (a.peekValue(attr).type != 3) {
                                c.mTargetId = a.getResourceId(attr, c.mTargetId);
                                break;
                            } else {
                                c.mTargetString = a.getString(attr);
                                break;
                            }
                        } else {
                            c.mTargetId = a.getResourceId(attr, c.mTargetId);
                            if (c.mTargetId != -1) {
                                break;
                            } else {
                                c.mTargetString = a.getString(attr);
                                break;
                            }
                        }
                    case 12:
                        c.mFramePosition = a.getInt(attr, c.mFramePosition);
                        break;
                    case 13:
                        int unused9 = c.mCurveFit = a.getInteger(attr, c.mCurveFit);
                        break;
                    case 14:
                        float unused10 = c.mScaleY = a.getFloat(attr, c.mScaleY);
                        break;
                    case 15:
                        float unused11 = c.mTranslationX = a.getDimension(attr, c.mTranslationX);
                        break;
                    case 16:
                        float unused12 = c.mTranslationY = a.getDimension(attr, c.mTranslationY);
                        break;
                    case 17:
                        if (Build.VERSION.SDK_INT < 21) {
                            break;
                        } else {
                            float unused13 = c.mTranslationZ = a.getDimension(attr, c.mTranslationZ);
                            break;
                        }
                    case 18:
                        float unused14 = c.mProgress = a.getFloat(attr, c.mProgress);
                        break;
                    case 19:
                        int unused15 = c.mWaveShape = a.getInt(attr, c.mWaveShape);
                        break;
                    case 20:
                        float unused16 = c.mWavePeriod = a.getFloat(attr, c.mWavePeriod);
                        break;
                    case 21:
                        if (a.peekValue(attr).type != 5) {
                            float unused17 = c.mWaveOffset = a.getFloat(attr, c.mWaveOffset);
                            break;
                        } else {
                            float unused18 = c.mWaveOffset = a.getDimension(attr, c.mWaveOffset);
                            break;
                        }
                    default:
                        Log.e("KeyTimeCycle", "unused attribute 0x" + Integer.toHexString(attr) + "   " + mAttrMap.get(attr));
                        break;
                }
            }
        }
    }
}
