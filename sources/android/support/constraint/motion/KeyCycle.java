package android.support.constraint.motion;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.constraint.ConstraintAttribute;
import android.support.constraint.R;
import android.support.v4.app.NotificationCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import java.util.HashMap;
import java.util.HashSet;

public class KeyCycle extends Key {
    public static final int KEY_TYPE = 4;
    static final String NAME = "KeyCycle";
    private static final String TAG = "KeyCycle";
    /* access modifiers changed from: private */
    public float mAlpha = Float.NaN;
    /* access modifiers changed from: private */
    public int mCurveFit = 0;
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
    public String mTransitionEasing = null;
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
    /* access modifiers changed from: private */
    public float mWavePeriod = Float.NaN;
    /* access modifiers changed from: private */
    public int mWaveShape = -1;
    /* access modifiers changed from: private */
    public int mWaveVariesBy = -1;

    public KeyCycle() {
        this.mType = 4;
        this.mCustomConstraints = new HashMap();
    }

    public void load(Context context, AttributeSet attrs) {
        Loader.read(this, context.obtainStyledAttributes(attrs, R.styleable.KeyCycle));
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
        if (!Float.isNaN(this.mScaleX)) {
            attributes.add("scaleX");
        }
        if (!Float.isNaN(this.mScaleY)) {
            attributes.add("scaleY");
        }
        if (!Float.isNaN(this.mTransitionPathRotate)) {
            attributes.add("transitionPathRotate");
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
        if (this.mCustomConstraints.size() > 0) {
            for (String s : this.mCustomConstraints.keySet()) {
                attributes.add("CUSTOM," + s);
            }
        }
    }

    public void addCycleValues(HashMap<String, KeyCycleOscillator> oscSet) {
        HashMap<String, KeyCycleOscillator> hashMap = oscSet;
        for (String key : oscSet.keySet()) {
            if (key.startsWith("CUSTOM")) {
                ConstraintAttribute cvalue = (ConstraintAttribute) this.mCustomConstraints.get(key.substring("CUSTOM".length() + 1));
                if (cvalue != null && cvalue.getType() == ConstraintAttribute.AttributeType.FLOAT_TYPE) {
                    hashMap.get(key).setPoint(this.mFramePosition, this.mWaveShape, this.mWaveVariesBy, this.mWavePeriod, this.mWaveOffset, cvalue.getValueToInterpolate(), cvalue);
                }
            }
            float value = getValue(key);
            if (!Float.isNaN(value)) {
                hashMap.get(key).setPoint(this.mFramePosition, this.mWaveShape, this.mWaveVariesBy, this.mWavePeriod, this.mWaveOffset, value);
            }
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public float getValue(java.lang.String r3) {
        /*
            r2 = this;
            int r0 = r3.hashCode()
            switch(r0) {
                case -1249320806: goto L_0x0089;
                case -1249320805: goto L_0x007f;
                case -1225497657: goto L_0x0074;
                case -1225497656: goto L_0x0069;
                case -1225497655: goto L_0x005e;
                case -1001078227: goto L_0x0053;
                case -908189618: goto L_0x0049;
                case -908189617: goto L_0x003f;
                case -40300674: goto L_0x0035;
                case -4379043: goto L_0x002b;
                case 37232917: goto L_0x0020;
                case 92909918: goto L_0x0015;
                case 156108012: goto L_0x0009;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x0093
        L_0x0009:
            java.lang.String r0 = "waveOffset"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 11
            goto L_0x0094
        L_0x0015:
            java.lang.String r0 = "alpha"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 0
            goto L_0x0094
        L_0x0020:
            java.lang.String r0 = "transitionPathRotate"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 5
            goto L_0x0094
        L_0x002b:
            java.lang.String r0 = "elevation"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 1
            goto L_0x0094
        L_0x0035:
            java.lang.String r0 = "rotation"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 2
            goto L_0x0094
        L_0x003f:
            java.lang.String r0 = "scaleY"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 7
            goto L_0x0094
        L_0x0049:
            java.lang.String r0 = "scaleX"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 6
            goto L_0x0094
        L_0x0053:
            java.lang.String r0 = "progress"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 12
            goto L_0x0094
        L_0x005e:
            java.lang.String r0 = "translationZ"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 10
            goto L_0x0094
        L_0x0069:
            java.lang.String r0 = "translationY"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 9
            goto L_0x0094
        L_0x0074:
            java.lang.String r0 = "translationX"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 8
            goto L_0x0094
        L_0x007f:
            java.lang.String r0 = "rotationY"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 4
            goto L_0x0094
        L_0x0089:
            java.lang.String r0 = "rotationX"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 3
            goto L_0x0094
        L_0x0093:
            r0 = -1
        L_0x0094:
            switch(r0) {
                case 0: goto L_0x00d6;
                case 1: goto L_0x00d3;
                case 2: goto L_0x00d0;
                case 3: goto L_0x00cd;
                case 4: goto L_0x00ca;
                case 5: goto L_0x00c7;
                case 6: goto L_0x00c4;
                case 7: goto L_0x00c1;
                case 8: goto L_0x00be;
                case 9: goto L_0x00bb;
                case 10: goto L_0x00b8;
                case 11: goto L_0x00b5;
                case 12: goto L_0x00b2;
                default: goto L_0x0097;
            }
        L_0x0097:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "  UNKNOWN  "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "WARNING! KeyCycle"
            android.util.Log.v(r1, r0)
            r0 = 2143289344(0x7fc00000, float:NaN)
            return r0
        L_0x00b2:
            float r0 = r2.mProgress
            return r0
        L_0x00b5:
            float r0 = r2.mWaveOffset
            return r0
        L_0x00b8:
            float r0 = r2.mTranslationZ
            return r0
        L_0x00bb:
            float r0 = r2.mTranslationY
            return r0
        L_0x00be:
            float r0 = r2.mTranslationX
            return r0
        L_0x00c1:
            float r0 = r2.mScaleY
            return r0
        L_0x00c4:
            float r0 = r2.mScaleX
            return r0
        L_0x00c7:
            float r0 = r2.mTransitionPathRotate
            return r0
        L_0x00ca:
            float r0 = r2.mRotationY
            return r0
        L_0x00cd:
            float r0 = r2.mRotationX
            return r0
        L_0x00d0:
            float r0 = r2.mRotation
            return r0
        L_0x00d3:
            float r0 = r2.mElevation
            return r0
        L_0x00d6:
            float r0 = r2.mAlpha
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.motion.KeyCycle.getValue(java.lang.String):float");
    }

    public void addValues(HashMap<String, SplineSet> splines) {
        Debug.logStack("KeyCycle", "add " + splines.size() + " values", 2);
        for (String s : splines.keySet()) {
            SplineSet splineSet = splines.get(s);
            char c = 65535;
            switch (s.hashCode()) {
                case -1249320806:
                    if (s.equals("rotationX")) {
                        c = 3;
                        break;
                    }
                    break;
                case -1249320805:
                    if (s.equals("rotationY")) {
                        c = 4;
                        break;
                    }
                    break;
                case -1225497657:
                    if (s.equals("translationX")) {
                        c = 8;
                        break;
                    }
                    break;
                case -1225497656:
                    if (s.equals("translationY")) {
                        c = 9;
                        break;
                    }
                    break;
                case -1225497655:
                    if (s.equals("translationZ")) {
                        c = 10;
                        break;
                    }
                    break;
                case -1001078227:
                    if (s.equals(NotificationCompat.CATEGORY_PROGRESS)) {
                        c = 12;
                        break;
                    }
                    break;
                case -908189618:
                    if (s.equals("scaleX")) {
                        c = 6;
                        break;
                    }
                    break;
                case -908189617:
                    if (s.equals("scaleY")) {
                        c = 7;
                        break;
                    }
                    break;
                case -40300674:
                    if (s.equals("rotation")) {
                        c = 2;
                        break;
                    }
                    break;
                case -4379043:
                    if (s.equals("elevation")) {
                        c = 1;
                        break;
                    }
                    break;
                case 37232917:
                    if (s.equals("transitionPathRotate")) {
                        c = 5;
                        break;
                    }
                    break;
                case 92909918:
                    if (s.equals("alpha")) {
                        c = 0;
                        break;
                    }
                    break;
                case 156108012:
                    if (s.equals("waveOffset")) {
                        c = 11;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    splineSet.setPoint(this.mFramePosition, this.mAlpha);
                    break;
                case 1:
                    splineSet.setPoint(this.mFramePosition, this.mElevation);
                    break;
                case 2:
                    splineSet.setPoint(this.mFramePosition, this.mRotation);
                    break;
                case 3:
                    splineSet.setPoint(this.mFramePosition, this.mRotationX);
                    break;
                case 4:
                    splineSet.setPoint(this.mFramePosition, this.mRotationY);
                    break;
                case 5:
                    splineSet.setPoint(this.mFramePosition, this.mTransitionPathRotate);
                    break;
                case 6:
                    splineSet.setPoint(this.mFramePosition, this.mScaleX);
                    break;
                case 7:
                    splineSet.setPoint(this.mFramePosition, this.mScaleY);
                    break;
                case 8:
                    splineSet.setPoint(this.mFramePosition, this.mTranslationX);
                    break;
                case 9:
                    splineSet.setPoint(this.mFramePosition, this.mTranslationY);
                    break;
                case 10:
                    splineSet.setPoint(this.mFramePosition, this.mTranslationZ);
                    break;
                case 11:
                    splineSet.setPoint(this.mFramePosition, this.mWaveOffset);
                    break;
                case 12:
                    splineSet.setPoint(this.mFramePosition, this.mProgress);
                    break;
                default:
                    Log.v("WARNING KeyCycle", "  UNKNOWN  " + s);
                    break;
            }
        }
    }

    private static class Loader {
        private static final int ANDROID_ALPHA = 9;
        private static final int ANDROID_ELEVATION = 10;
        private static final int ANDROID_ROTATION = 11;
        private static final int ANDROID_ROTATION_X = 12;
        private static final int ANDROID_ROTATION_Y = 13;
        private static final int ANDROID_SCALE_X = 15;
        private static final int ANDROID_SCALE_Y = 16;
        private static final int ANDROID_TRANSLATION_X = 17;
        private static final int ANDROID_TRANSLATION_Y = 18;
        private static final int ANDROID_TRANSLATION_Z = 19;
        private static final int CURVE_FIT = 4;
        private static final int FRAME_POSITION = 2;
        private static final int PROGRESS = 20;
        private static final int TARGET_ID = 1;
        private static final int TRANSITION_EASING = 3;
        private static final int TRANSITION_PATH_ROTATE = 14;
        private static final int WAVE_OFFSET = 7;
        private static final int WAVE_PERIOD = 6;
        private static final int WAVE_SHAPE = 5;
        private static final int WAVE_VARIES_BY = 8;
        private static SparseIntArray mAttrMap;

        private Loader() {
        }

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            mAttrMap = sparseIntArray;
            sparseIntArray.append(R.styleable.KeyCycle_motionTarget, 1);
            mAttrMap.append(R.styleable.KeyCycle_framePosition, 2);
            mAttrMap.append(R.styleable.KeyCycle_transitionEasing, 3);
            mAttrMap.append(R.styleable.KeyCycle_curveFit, 4);
            mAttrMap.append(R.styleable.KeyCycle_waveShape, 5);
            mAttrMap.append(R.styleable.KeyCycle_wavePeriod, 6);
            mAttrMap.append(R.styleable.KeyCycle_waveOffset, 7);
            mAttrMap.append(R.styleable.KeyCycle_waveVariesBy, 8);
            mAttrMap.append(R.styleable.KeyCycle_android_alpha, 9);
            mAttrMap.append(R.styleable.KeyCycle_android_elevation, 10);
            mAttrMap.append(R.styleable.KeyCycle_android_rotation, 11);
            mAttrMap.append(R.styleable.KeyCycle_android_rotationX, 12);
            mAttrMap.append(R.styleable.KeyCycle_android_rotationY, 13);
            mAttrMap.append(R.styleable.KeyCycle_transitionPathRotate, 14);
            mAttrMap.append(R.styleable.KeyCycle_android_scaleX, 15);
            mAttrMap.append(R.styleable.KeyCycle_android_scaleY, 16);
            mAttrMap.append(R.styleable.KeyCycle_android_translationX, 17);
            mAttrMap.append(R.styleable.KeyCycle_android_translationY, 18);
            mAttrMap.append(R.styleable.KeyCycle_android_translationZ, 19);
            mAttrMap.append(R.styleable.KeyCycle_motionProgress, 20);
        }

        /* access modifiers changed from: private */
        public static void read(KeyCycle c, TypedArray a) {
            int N = a.getIndexCount();
            for (int i = 0; i < N; i++) {
                int attr = a.getIndex(i);
                switch (mAttrMap.get(attr)) {
                    case 1:
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
                    case 2:
                        c.mFramePosition = a.getInt(attr, c.mFramePosition);
                        break;
                    case 3:
                        String unused = c.mTransitionEasing = a.getString(attr);
                        break;
                    case 4:
                        int unused2 = c.mCurveFit = a.getInteger(attr, c.mCurveFit);
                        break;
                    case 5:
                        int unused3 = c.mWaveShape = a.getInt(attr, c.mWaveShape);
                        break;
                    case 6:
                        float unused4 = c.mWavePeriod = a.getFloat(attr, c.mWavePeriod);
                        break;
                    case 7:
                        if (a.peekValue(attr).type != 5) {
                            float unused5 = c.mWaveOffset = a.getFloat(attr, c.mWaveOffset);
                            break;
                        } else {
                            float unused6 = c.mWaveOffset = a.getDimension(attr, c.mWaveOffset);
                            break;
                        }
                    case 8:
                        int unused7 = c.mWaveVariesBy = a.getInt(attr, c.mWaveVariesBy);
                        break;
                    case 9:
                        float unused8 = c.mAlpha = a.getFloat(attr, c.mAlpha);
                        break;
                    case 10:
                        float unused9 = c.mElevation = a.getDimension(attr, c.mElevation);
                        break;
                    case 11:
                        float unused10 = c.mRotation = a.getFloat(attr, c.mRotation);
                        break;
                    case 12:
                        float unused11 = c.mRotationX = a.getFloat(attr, c.mRotationX);
                        break;
                    case 13:
                        float unused12 = c.mRotationY = a.getFloat(attr, c.mRotationY);
                        break;
                    case 14:
                        float unused13 = c.mTransitionPathRotate = a.getFloat(attr, c.mTransitionPathRotate);
                        break;
                    case 15:
                        float unused14 = c.mScaleX = a.getFloat(attr, c.mScaleX);
                        break;
                    case 16:
                        float unused15 = c.mScaleY = a.getFloat(attr, c.mScaleY);
                        break;
                    case 17:
                        float unused16 = c.mTranslationX = a.getDimension(attr, c.mTranslationX);
                        break;
                    case 18:
                        float unused17 = c.mTranslationY = a.getDimension(attr, c.mTranslationY);
                        break;
                    case 19:
                        if (Build.VERSION.SDK_INT < 21) {
                            break;
                        } else {
                            float unused18 = c.mTranslationZ = a.getDimension(attr, c.mTranslationZ);
                            break;
                        }
                    case 20:
                        float unused19 = c.mProgress = a.getFloat(attr, c.mProgress);
                        break;
                    default:
                        Log.e("KeyCycle", "unused attribute 0x" + Integer.toHexString(attr) + "   " + mAttrMap.get(attr));
                        break;
                }
            }
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setValue(java.lang.String r2, java.lang.Object r3) {
        /*
            r1 = this;
            int r0 = r2.hashCode()
            switch(r0) {
                case -1812823328: goto L_0x00ac;
                case -1249320806: goto L_0x00a2;
                case -1249320805: goto L_0x0098;
                case -1225497657: goto L_0x008d;
                case -1225497656: goto L_0x0082;
                case -1001078227: goto L_0x0078;
                case -908189618: goto L_0x006e;
                case -908189617: goto L_0x0063;
                case -40300674: goto L_0x0059;
                case -4379043: goto L_0x004f;
                case 37232917: goto L_0x0043;
                case 92909918: goto L_0x0038;
                case 156108012: goto L_0x002c;
                case 184161818: goto L_0x0020;
                case 579057826: goto L_0x0015;
                case 1317633238: goto L_0x0009;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x00b7
        L_0x0009:
            java.lang.String r0 = "mTranslationZ"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 13
            goto L_0x00b8
        L_0x0015:
            java.lang.String r0 = "curveFit"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 1
            goto L_0x00b8
        L_0x0020:
            java.lang.String r0 = "wavePeriod"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 14
            goto L_0x00b8
        L_0x002c:
            java.lang.String r0 = "waveOffset"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 15
            goto L_0x00b8
        L_0x0038:
            java.lang.String r0 = "alpha"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 0
            goto L_0x00b8
        L_0x0043:
            java.lang.String r0 = "transitionPathRotate"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 10
            goto L_0x00b8
        L_0x004f:
            java.lang.String r0 = "elevation"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 2
            goto L_0x00b8
        L_0x0059:
            java.lang.String r0 = "rotation"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 4
            goto L_0x00b8
        L_0x0063:
            java.lang.String r0 = "scaleY"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 8
            goto L_0x00b8
        L_0x006e:
            java.lang.String r0 = "scaleX"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 7
            goto L_0x00b8
        L_0x0078:
            java.lang.String r0 = "progress"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 3
            goto L_0x00b8
        L_0x0082:
            java.lang.String r0 = "translationY"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 12
            goto L_0x00b8
        L_0x008d:
            java.lang.String r0 = "translationX"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 11
            goto L_0x00b8
        L_0x0098:
            java.lang.String r0 = "rotationY"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 6
            goto L_0x00b8
        L_0x00a2:
            java.lang.String r0 = "rotationX"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 5
            goto L_0x00b8
        L_0x00ac:
            java.lang.String r0 = "transitionEasing"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 9
            goto L_0x00b8
        L_0x00b7:
            r0 = -1
        L_0x00b8:
            switch(r0) {
                case 0: goto L_0x0127;
                case 1: goto L_0x0120;
                case 2: goto L_0x0119;
                case 3: goto L_0x0112;
                case 4: goto L_0x010b;
                case 5: goto L_0x0104;
                case 6: goto L_0x00fd;
                case 7: goto L_0x00f6;
                case 8: goto L_0x00ef;
                case 9: goto L_0x00e8;
                case 10: goto L_0x00e1;
                case 11: goto L_0x00da;
                case 12: goto L_0x00d3;
                case 13: goto L_0x00cc;
                case 14: goto L_0x00c5;
                case 15: goto L_0x00bd;
                default: goto L_0x00bb;
            }
        L_0x00bb:
            goto L_0x012e
        L_0x00bd:
            float r0 = r1.toFloat(r3)
            r1.mWaveOffset = r0
            goto L_0x012e
        L_0x00c5:
            float r0 = r1.toFloat(r3)
            r1.mWavePeriod = r0
            goto L_0x012e
        L_0x00cc:
            float r0 = r1.toFloat(r3)
            r1.mTranslationZ = r0
            goto L_0x012e
        L_0x00d3:
            float r0 = r1.toFloat(r3)
            r1.mTranslationY = r0
            goto L_0x012e
        L_0x00da:
            float r0 = r1.toFloat(r3)
            r1.mTranslationX = r0
            goto L_0x012e
        L_0x00e1:
            float r0 = r1.toFloat(r3)
            r1.mTransitionPathRotate = r0
            goto L_0x012e
        L_0x00e8:
            java.lang.String r0 = r3.toString()
            r1.mTransitionEasing = r0
            goto L_0x012e
        L_0x00ef:
            float r0 = r1.toFloat(r3)
            r1.mScaleY = r0
            goto L_0x012e
        L_0x00f6:
            float r0 = r1.toFloat(r3)
            r1.mScaleX = r0
            goto L_0x012e
        L_0x00fd:
            float r0 = r1.toFloat(r3)
            r1.mRotationY = r0
            goto L_0x012e
        L_0x0104:
            float r0 = r1.toFloat(r3)
            r1.mRotationX = r0
            goto L_0x012e
        L_0x010b:
            float r0 = r1.toFloat(r3)
            r1.mRotation = r0
            goto L_0x012e
        L_0x0112:
            float r0 = r1.toFloat(r3)
            r1.mProgress = r0
            goto L_0x012e
        L_0x0119:
            float r0 = r1.toFloat(r3)
            r1.mElevation = r0
            goto L_0x012e
        L_0x0120:
            int r0 = r1.toInt(r3)
            r1.mCurveFit = r0
            goto L_0x012e
        L_0x0127:
            float r0 = r1.toFloat(r3)
            r1.mAlpha = r0
        L_0x012e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.motion.KeyCycle.setValue(java.lang.String, java.lang.Object):void");
    }
}
