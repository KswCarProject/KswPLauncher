package android.support.constraint.motion;

import android.os.Build;
import android.support.constraint.ConstraintAttribute;
import android.support.constraint.motion.utils.CurveFit;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;

public abstract class TimeCycleSplineSet {
    private static final int CURVE_OFFSET = 2;
    private static final int CURVE_PERIOD = 1;
    private static final int CURVE_VALUE = 0;
    private static final String TAG = "SplineSet";
    private static float VAL_2PI = 6.2831855f;
    private int count;
    float last_cycle = Float.NaN;
    long last_time;
    private float[] mCache = new float[3];
    protected boolean mContinue = false;
    protected CurveFit mCurveFit;
    protected int[] mTimePoints = new int[10];
    private String mType;
    protected float[][] mValues = ((float[][]) Array.newInstance(float.class, new int[]{10, 3}));
    protected int mWaveShape = 0;

    public abstract boolean setProperty(View view, float f, long j, KeyCache keyCache);

    public String toString() {
        String str = this.mType;
        DecimalFormat df = new DecimalFormat("##.##");
        for (int i = 0; i < this.count; i++) {
            str = str + "[" + this.mTimePoints[i] + " , " + df.format(this.mValues[i]) + "] ";
        }
        return str;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public float get(float pos, long time, View view, KeyCache cache) {
        long j = time;
        View view2 = view;
        KeyCache keyCache = cache;
        this.mCurveFit.getPos((double) pos, this.mCache);
        float[] fArr = this.mCache;
        boolean z = true;
        float period = fArr[1];
        if (period == 0.0f) {
            this.mContinue = false;
            return fArr[2];
        }
        if (Float.isNaN(this.last_cycle)) {
            float floatValue = keyCache.getFloatValue(view2, this.mType, 0);
            this.last_cycle = floatValue;
            if (Float.isNaN(floatValue)) {
                this.last_cycle = 0.0f;
            }
        }
        float f = (float) ((((double) this.last_cycle) + ((((double) (j - this.last_time)) * 1.0E-9d) * ((double) period))) % 1.0d);
        this.last_cycle = f;
        keyCache.setFloatValue(view2, this.mType, 0, f);
        this.last_time = j;
        float v = this.mCache[0];
        float value = (v * calcWave(this.last_cycle)) + this.mCache[2];
        if (v == 0.0f && period == 0.0f) {
            z = false;
        }
        this.mContinue = z;
        return value;
    }

    /* access modifiers changed from: protected */
    public float calcWave(float period) {
        float p = period;
        switch (this.mWaveShape) {
            case 1:
                return Math.signum(VAL_2PI * p);
            case 2:
                return 1.0f - Math.abs(p);
            case 3:
                return (((p * 2.0f) + 1.0f) % 2.0f) - 1.0f;
            case 4:
                return 1.0f - (((p * 2.0f) + 1.0f) % 2.0f);
            case 5:
                return (float) Math.cos((double) (VAL_2PI * p));
            case 6:
                float x = 1.0f - Math.abs(((p * 4.0f) % 4.0f) - 2.0f);
                return 1.0f - (x * x);
            default:
                return (float) Math.sin((double) (VAL_2PI * p));
        }
    }

    public CurveFit getCurveFit() {
        return this.mCurveFit;
    }

    static TimeCycleSplineSet makeCustomSpline(String str, SparseArray<ConstraintAttribute> attrList) {
        return new CustomSet(str, attrList);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static android.support.constraint.motion.TimeCycleSplineSet makeSpline(java.lang.String r1, long r2) {
        /*
            int r0 = r1.hashCode()
            switch(r0) {
                case -1249320806: goto L_0x007d;
                case -1249320805: goto L_0x0073;
                case -1225497657: goto L_0x0068;
                case -1225497656: goto L_0x005d;
                case -1225497655: goto L_0x0052;
                case -1001078227: goto L_0x0047;
                case -908189618: goto L_0x003d;
                case -908189617: goto L_0x0033;
                case -40300674: goto L_0x0029;
                case -4379043: goto L_0x001f;
                case 37232917: goto L_0x0014;
                case 92909918: goto L_0x0009;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x0087
        L_0x0009:
            java.lang.String r0 = "alpha"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 0
            goto L_0x0088
        L_0x0014:
            java.lang.String r0 = "transitionPathRotate"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 5
            goto L_0x0088
        L_0x001f:
            java.lang.String r0 = "elevation"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 1
            goto L_0x0088
        L_0x0029:
            java.lang.String r0 = "rotation"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 2
            goto L_0x0088
        L_0x0033:
            java.lang.String r0 = "scaleY"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 7
            goto L_0x0088
        L_0x003d:
            java.lang.String r0 = "scaleX"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 6
            goto L_0x0088
        L_0x0047:
            java.lang.String r0 = "progress"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 11
            goto L_0x0088
        L_0x0052:
            java.lang.String r0 = "translationZ"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 10
            goto L_0x0088
        L_0x005d:
            java.lang.String r0 = "translationY"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 9
            goto L_0x0088
        L_0x0068:
            java.lang.String r0 = "translationX"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 8
            goto L_0x0088
        L_0x0073:
            java.lang.String r0 = "rotationY"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 4
            goto L_0x0088
        L_0x007d:
            java.lang.String r0 = "rotationX"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 3
            goto L_0x0088
        L_0x0087:
            r0 = -1
        L_0x0088:
            switch(r0) {
                case 0: goto L_0x00cf;
                case 1: goto L_0x00c9;
                case 2: goto L_0x00c3;
                case 3: goto L_0x00bd;
                case 4: goto L_0x00b7;
                case 5: goto L_0x00b1;
                case 6: goto L_0x00ab;
                case 7: goto L_0x00a5;
                case 8: goto L_0x009f;
                case 9: goto L_0x0099;
                case 10: goto L_0x0093;
                case 11: goto L_0x008d;
                default: goto L_0x008b;
            }
        L_0x008b:
            r0 = 0
            return r0
        L_0x008d:
            android.support.constraint.motion.TimeCycleSplineSet$ProgressSet r0 = new android.support.constraint.motion.TimeCycleSplineSet$ProgressSet
            r0.<init>()
            goto L_0x00d5
        L_0x0093:
            android.support.constraint.motion.TimeCycleSplineSet$TranslationZset r0 = new android.support.constraint.motion.TimeCycleSplineSet$TranslationZset
            r0.<init>()
            goto L_0x00d5
        L_0x0099:
            android.support.constraint.motion.TimeCycleSplineSet$TranslationYset r0 = new android.support.constraint.motion.TimeCycleSplineSet$TranslationYset
            r0.<init>()
            goto L_0x00d5
        L_0x009f:
            android.support.constraint.motion.TimeCycleSplineSet$TranslationXset r0 = new android.support.constraint.motion.TimeCycleSplineSet$TranslationXset
            r0.<init>()
            goto L_0x00d5
        L_0x00a5:
            android.support.constraint.motion.TimeCycleSplineSet$ScaleYset r0 = new android.support.constraint.motion.TimeCycleSplineSet$ScaleYset
            r0.<init>()
            goto L_0x00d5
        L_0x00ab:
            android.support.constraint.motion.TimeCycleSplineSet$ScaleXset r0 = new android.support.constraint.motion.TimeCycleSplineSet$ScaleXset
            r0.<init>()
            goto L_0x00d5
        L_0x00b1:
            android.support.constraint.motion.TimeCycleSplineSet$PathRotate r0 = new android.support.constraint.motion.TimeCycleSplineSet$PathRotate
            r0.<init>()
            goto L_0x00d5
        L_0x00b7:
            android.support.constraint.motion.TimeCycleSplineSet$RotationYset r0 = new android.support.constraint.motion.TimeCycleSplineSet$RotationYset
            r0.<init>()
            goto L_0x00d5
        L_0x00bd:
            android.support.constraint.motion.TimeCycleSplineSet$RotationXset r0 = new android.support.constraint.motion.TimeCycleSplineSet$RotationXset
            r0.<init>()
            goto L_0x00d5
        L_0x00c3:
            android.support.constraint.motion.TimeCycleSplineSet$RotationSet r0 = new android.support.constraint.motion.TimeCycleSplineSet$RotationSet
            r0.<init>()
            goto L_0x00d5
        L_0x00c9:
            android.support.constraint.motion.TimeCycleSplineSet$ElevationSet r0 = new android.support.constraint.motion.TimeCycleSplineSet$ElevationSet
            r0.<init>()
            goto L_0x00d5
        L_0x00cf:
            android.support.constraint.motion.TimeCycleSplineSet$AlphaSet r0 = new android.support.constraint.motion.TimeCycleSplineSet$AlphaSet
            r0.<init>()
        L_0x00d5:
            r0.setStartTime(r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.motion.TimeCycleSplineSet.makeSpline(java.lang.String, long):android.support.constraint.motion.TimeCycleSplineSet");
    }

    /* access modifiers changed from: protected */
    public void setStartTime(long currentTime) {
        this.last_time = currentTime;
    }

    public void setPoint(int position, float value, float period, int shape, float offset) {
        int[] iArr = this.mTimePoints;
        int i = this.count;
        iArr[i] = position;
        float[][] fArr = this.mValues;
        fArr[i][0] = value;
        fArr[i][1] = period;
        fArr[i][2] = offset;
        this.mWaveShape = Math.max(this.mWaveShape, shape);
        this.count++;
    }

    public void setup(int curveType) {
        int i = this.count;
        if (i == 0) {
            Log.e(TAG, "Error no points added to " + this.mType);
            return;
        }
        Sort.doubleQuickSort(this.mTimePoints, this.mValues, 0, i - 1);
        int unique = 0;
        int i2 = 1;
        while (true) {
            int[] iArr = this.mTimePoints;
            if (i2 >= iArr.length) {
                break;
            }
            if (iArr[i2] != iArr[i2 - 1]) {
                unique++;
            }
            i2++;
        }
        if (unique == 0) {
            unique = 1;
        }
        double[] time = new double[unique];
        int[] iArr2 = new int[2];
        iArr2[1] = 3;
        iArr2[0] = unique;
        double[][] values = (double[][]) Array.newInstance(double.class, iArr2);
        int k = 0;
        for (int i3 = 0; i3 < this.count; i3++) {
            if (i3 > 0) {
                int[] iArr3 = this.mTimePoints;
                if (iArr3[i3] == iArr3[i3 - 1]) {
                }
            }
            time[k] = ((double) this.mTimePoints[i3]) * 0.01d;
            double[] dArr = values[k];
            float[][] fArr = this.mValues;
            dArr[0] = (double) fArr[i3][0];
            values[k][1] = (double) fArr[i3][1];
            values[k][2] = (double) fArr[i3][2];
            k++;
        }
        this.mCurveFit = CurveFit.get(curveType, time, values);
    }

    static class ElevationSet extends TimeCycleSplineSet {
        ElevationSet() {
        }

        public boolean setProperty(View view, float t, long time, KeyCache cache) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setElevation(get(t, time, view, cache));
            }
            return this.mContinue;
        }
    }

    static class AlphaSet extends TimeCycleSplineSet {
        AlphaSet() {
        }

        public boolean setProperty(View view, float t, long time, KeyCache cache) {
            view.setAlpha(get(t, time, view, cache));
            return this.mContinue;
        }
    }

    static class RotationSet extends TimeCycleSplineSet {
        RotationSet() {
        }

        public boolean setProperty(View view, float t, long time, KeyCache cache) {
            view.setRotation(get(t, time, view, cache));
            return this.mContinue;
        }
    }

    static class RotationXset extends TimeCycleSplineSet {
        RotationXset() {
        }

        public boolean setProperty(View view, float t, long time, KeyCache cache) {
            view.setRotationX(get(t, time, view, cache));
            return this.mContinue;
        }
    }

    static class RotationYset extends TimeCycleSplineSet {
        RotationYset() {
        }

        public boolean setProperty(View view, float t, long time, KeyCache cache) {
            view.setRotationY(get(t, time, view, cache));
            return this.mContinue;
        }
    }

    static class PathRotate extends TimeCycleSplineSet {
        PathRotate() {
        }

        public boolean setProperty(View view, float t, long time, KeyCache cache) {
            return this.mContinue;
        }

        public boolean setPathRotate(View view, KeyCache cache, float t, long time, double dx, double dy) {
            view.setRotation(get(t, time, view, cache) + ((float) Math.toDegrees(Math.atan2(dy, dx))));
            return this.mContinue;
        }
    }

    static class ScaleXset extends TimeCycleSplineSet {
        ScaleXset() {
        }

        public boolean setProperty(View view, float t, long time, KeyCache cache) {
            view.setScaleX(get(t, time, view, cache));
            return this.mContinue;
        }
    }

    static class ScaleYset extends TimeCycleSplineSet {
        ScaleYset() {
        }

        public boolean setProperty(View view, float t, long time, KeyCache cache) {
            view.setScaleY(get(t, time, view, cache));
            return this.mContinue;
        }
    }

    static class TranslationXset extends TimeCycleSplineSet {
        TranslationXset() {
        }

        public boolean setProperty(View view, float t, long time, KeyCache cache) {
            view.setTranslationX(get(t, time, view, cache));
            return this.mContinue;
        }
    }

    static class TranslationYset extends TimeCycleSplineSet {
        TranslationYset() {
        }

        public boolean setProperty(View view, float t, long time, KeyCache cache) {
            view.setTranslationY(get(t, time, view, cache));
            return this.mContinue;
        }
    }

    static class TranslationZset extends TimeCycleSplineSet {
        TranslationZset() {
        }

        public boolean setProperty(View view, float t, long time, KeyCache cache) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setTranslationZ(get(t, time, view, cache));
            }
            return this.mContinue;
        }
    }

    static class CustomSet extends TimeCycleSplineSet {
        String mAttributeName;
        float[] mCache;
        SparseArray<ConstraintAttribute> mConstraintAttributeList;
        float[] mTempValues;
        SparseArray<float[]> mWaveProperties = new SparseArray<>();

        public CustomSet(String attribute, SparseArray<ConstraintAttribute> attrList) {
            this.mAttributeName = attribute.split(",")[1];
            this.mConstraintAttributeList = attrList;
        }

        public void setup(int curveType) {
            int size = this.mConstraintAttributeList.size();
            int dimensionality = this.mConstraintAttributeList.valueAt(0).noOfInterpValues();
            double[] time = new double[size];
            this.mTempValues = new float[(dimensionality + 2)];
            this.mCache = new float[dimensionality];
            int[] iArr = new int[2];
            iArr[1] = dimensionality + 2;
            iArr[0] = size;
            double[][] values = (double[][]) Array.newInstance(double.class, iArr);
            for (int i = 0; i < size; i++) {
                int key = this.mConstraintAttributeList.keyAt(i);
                float[] waveProp = this.mWaveProperties.valueAt(i);
                time[i] = ((double) key) * 0.01d;
                this.mConstraintAttributeList.valueAt(i).getValuesToInterpolate(this.mTempValues);
                int k = 0;
                while (true) {
                    float[] fArr = this.mTempValues;
                    if (k >= fArr.length) {
                        break;
                    }
                    values[i][k] = (double) fArr[k];
                    k++;
                }
                values[i][dimensionality] = (double) waveProp[0];
                values[i][dimensionality + 1] = (double) waveProp[1];
            }
            this.mCurveFit = CurveFit.get(curveType, time, values);
        }

        public void setPoint(int position, float value, float period, int shape, float offset) {
            throw new RuntimeException("don't call for custom attribute call setPoint(pos, ConstraintAttribute,...)");
        }

        public void setPoint(int position, ConstraintAttribute value, float period, int shape, float offset) {
            this.mConstraintAttributeList.append(position, value);
            this.mWaveProperties.append(position, new float[]{period, offset});
            this.mWaveShape = Math.max(this.mWaveShape, shape);
        }

        public boolean setProperty(View view, float t, long time, KeyCache cache) {
            long j = time;
            this.mCurveFit.getPos((double) t, this.mTempValues);
            float[] fArr = this.mTempValues;
            float period = fArr[fArr.length - 2];
            float offset = fArr[fArr.length - 1];
            this.last_cycle = (float) ((((double) this.last_cycle) + ((((double) (j - this.last_time)) * 1.0E-9d) * ((double) period))) % 1.0d);
            this.last_time = j;
            float wave = calcWave(this.last_cycle);
            this.mContinue = false;
            for (int i = 0; i < this.mCache.length; i++) {
                this.mContinue |= ((double) this.mTempValues[i]) != 0.0d;
                this.mCache[i] = (this.mTempValues[i] * wave) + offset;
            }
            this.mConstraintAttributeList.valueAt(0).setInterpolatedValue(view, this.mCache);
            if (period != 0.0f) {
                this.mContinue = true;
            }
            return this.mContinue;
        }
    }

    static class ProgressSet extends TimeCycleSplineSet {
        boolean mNoMethod = false;

        ProgressSet() {
        }

        public boolean setProperty(View view, float t, long time, KeyCache cache) {
            Method method;
            View view2 = view;
            if (view2 instanceof MotionLayout) {
                ((MotionLayout) view2).setProgress(get(t, time, view, cache));
            } else if (this.mNoMethod) {
                return false;
            } else {
                try {
                    method = view.getClass().getMethod("setProgress", new Class[]{Float.TYPE});
                } catch (NoSuchMethodException e) {
                    this.mNoMethod = true;
                    method = null;
                }
                if (method != null) {
                    try {
                        method.invoke(view, new Object[]{Float.valueOf(get(t, time, view, cache))});
                    } catch (IllegalAccessException e2) {
                        Log.e(TimeCycleSplineSet.TAG, "unable to setProgress", e2);
                    } catch (InvocationTargetException e3) {
                        Log.e(TimeCycleSplineSet.TAG, "unable to setProgress", e3);
                    }
                }
            }
            return this.mContinue;
        }
    }

    private static class Sort {
        private Sort() {
        }

        static void doubleQuickSort(int[] key, float[][] value, int low, int hi) {
            int[] stack = new int[(key.length + 10)];
            int count = 0 + 1;
            stack[0] = hi;
            int count2 = count + 1;
            stack[count] = low;
            while (count2 > 0) {
                int count3 = count2 - 1;
                int low2 = stack[count3];
                count2 = count3 - 1;
                int hi2 = stack[count2];
                if (low2 < hi2) {
                    int p = partition(key, value, low2, hi2);
                    int count4 = count2 + 1;
                    stack[count2] = p - 1;
                    int count5 = count4 + 1;
                    stack[count4] = low2;
                    int count6 = count5 + 1;
                    stack[count5] = hi2;
                    count2 = count6 + 1;
                    stack[count6] = p + 1;
                }
            }
        }

        private static int partition(int[] array, float[][] value, int low, int hi) {
            int pivot = array[hi];
            int i = low;
            for (int j = low; j < hi; j++) {
                if (array[j] <= pivot) {
                    swap(array, value, i, j);
                    i++;
                }
            }
            swap(array, value, i, hi);
            return i;
        }

        private static void swap(int[] array, float[][] value, int a, int b) {
            int tmp = array[a];
            array[a] = array[b];
            array[b] = tmp;
            float[] tmpv = value[a];
            value[a] = value[b];
            value[b] = tmpv;
        }
    }
}
