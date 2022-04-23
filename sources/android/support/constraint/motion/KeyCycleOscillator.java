package android.support.constraint.motion;

import android.os.Build;
import android.support.constraint.ConstraintAttribute;
import android.support.constraint.motion.utils.CurveFit;
import android.support.constraint.motion.utils.Oscillator;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

public abstract class KeyCycleOscillator {
    private static final String TAG = "KeyCycleOscillator";
    private CurveFit mCurveFit;
    protected ConstraintAttribute mCustom;
    private CycleOscillator mCycleOscillator;
    private String mType;
    public int mVariesBy = 0;
    ArrayList<WavePoint> mWavePoints = new ArrayList<>();
    private int mWaveShape = 0;

    public abstract void setProperty(View view, float f);

    public boolean variesByPath() {
        return this.mVariesBy == 1;
    }

    static class WavePoint {
        float mOffset;
        float mPeriod;
        int mPosition;
        float mValue;

        public WavePoint(int position, float period, float offset, float value) {
            this.mPosition = position;
            this.mValue = value;
            this.mOffset = offset;
            this.mPeriod = period;
        }
    }

    public String toString() {
        String str = this.mType;
        DecimalFormat df = new DecimalFormat("##.##");
        Iterator<WavePoint> it = this.mWavePoints.iterator();
        while (it.hasNext()) {
            WavePoint wp = it.next();
            str = str + "[" + wp.mPosition + " , " + df.format((double) wp.mValue) + "] ";
        }
        return str;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public float get(float t) {
        return (float) this.mCycleOscillator.getValues(t);
    }

    public float getSlope(float position) {
        return (float) this.mCycleOscillator.getSlope(position);
    }

    public CurveFit getCurveFit() {
        return this.mCurveFit;
    }

    static KeyCycleOscillator makeSpline(String str) {
        if (str.startsWith("CUSTOM")) {
            return new CustomSet();
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -1249320806:
                if (str.equals("rotationX")) {
                    c = 3;
                    break;
                }
                break;
            case -1249320805:
                if (str.equals("rotationY")) {
                    c = 4;
                    break;
                }
                break;
            case -1225497657:
                if (str.equals("translationX")) {
                    c = 10;
                    break;
                }
                break;
            case -1225497656:
                if (str.equals("translationY")) {
                    c = 11;
                    break;
                }
                break;
            case -1225497655:
                if (str.equals("translationZ")) {
                    c = 12;
                    break;
                }
                break;
            case -1001078227:
                if (str.equals(NotificationCompat.CATEGORY_PROGRESS)) {
                    c = 13;
                    break;
                }
                break;
            case -908189618:
                if (str.equals("scaleX")) {
                    c = 6;
                    break;
                }
                break;
            case -908189617:
                if (str.equals("scaleY")) {
                    c = 7;
                    break;
                }
                break;
            case -797520672:
                if (str.equals("waveVariesBy")) {
                    c = 9;
                    break;
                }
                break;
            case -40300674:
                if (str.equals("rotation")) {
                    c = 2;
                    break;
                }
                break;
            case -4379043:
                if (str.equals("elevation")) {
                    c = 1;
                    break;
                }
                break;
            case 37232917:
                if (str.equals("transitionPathRotate")) {
                    c = 5;
                    break;
                }
                break;
            case 92909918:
                if (str.equals("alpha")) {
                    c = 0;
                    break;
                }
                break;
            case 156108012:
                if (str.equals("waveOffset")) {
                    c = 8;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return new AlphaSet();
            case 1:
                return new ElevationSet();
            case 2:
                return new RotationSet();
            case 3:
                return new RotationXset();
            case 4:
                return new RotationYset();
            case 5:
                return new PathRotateSet();
            case 6:
                return new ScaleXset();
            case 7:
                return new ScaleYset();
            case 8:
                return new AlphaSet();
            case 9:
                return new AlphaSet();
            case 10:
                return new TranslationXset();
            case 11:
                return new TranslationYset();
            case 12:
                return new TranslationZset();
            case 13:
                return new ProgressSet();
            default:
                return null;
        }
    }

    public void setPoint(int framePosition, int shape, int variesBy, float period, float offset, float value, ConstraintAttribute custom) {
        this.mWavePoints.add(new WavePoint(framePosition, period, offset, value));
        if (variesBy != -1) {
            this.mVariesBy = variesBy;
        }
        this.mWaveShape = shape;
        this.mCustom = custom;
    }

    public void setPoint(int framePosition, int shape, int variesBy, float period, float offset, float value) {
        this.mWavePoints.add(new WavePoint(framePosition, period, offset, value));
        if (variesBy != -1) {
            this.mVariesBy = variesBy;
        }
        this.mWaveShape = shape;
    }

    public void setup(float pathLength) {
        int count = this.mWavePoints.size();
        if (count != 0) {
            Collections.sort(this.mWavePoints, new Comparator<WavePoint>() {
                public int compare(WavePoint lhs, WavePoint rhs) {
                    return Integer.compare(lhs.mPosition, rhs.mPosition);
                }
            });
            double[] time = new double[count];
            int[] iArr = new int[2];
            iArr[1] = 2;
            iArr[0] = count;
            double[][] values = (double[][]) Array.newInstance(double.class, iArr);
            this.mCycleOscillator = new CycleOscillator(this.mWaveShape, this.mVariesBy, count);
            int i = 0;
            Iterator<WavePoint> it = this.mWavePoints.iterator();
            while (it.hasNext()) {
                WavePoint wp = it.next();
                time[i] = ((double) wp.mPeriod) * 0.01d;
                values[i][0] = (double) wp.mValue;
                values[i][1] = (double) wp.mOffset;
                this.mCycleOscillator.setPoint(i, wp.mPosition, wp.mPeriod, wp.mOffset, wp.mValue);
                i++;
            }
            this.mCycleOscillator.setup(pathLength);
            this.mCurveFit = CurveFit.get(0, time, values);
        }
    }

    static class ElevationSet extends KeyCycleOscillator {
        ElevationSet() {
        }

        public void setProperty(View view, float t) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setElevation(get(t));
            }
        }
    }

    static class AlphaSet extends KeyCycleOscillator {
        AlphaSet() {
        }

        public void setProperty(View view, float t) {
            view.setAlpha(get(t));
        }
    }

    static class RotationSet extends KeyCycleOscillator {
        RotationSet() {
        }

        public void setProperty(View view, float t) {
            view.setRotation(get(t));
        }
    }

    static class RotationXset extends KeyCycleOscillator {
        RotationXset() {
        }

        public void setProperty(View view, float t) {
            view.setRotationX(get(t));
        }
    }

    static class RotationYset extends KeyCycleOscillator {
        RotationYset() {
        }

        public void setProperty(View view, float t) {
            view.setRotationY(get(t));
        }
    }

    static class PathRotateSet extends KeyCycleOscillator {
        PathRotateSet() {
        }

        public void setProperty(View view, float t) {
        }

        public void setPathRotate(View view, float t, double dx, double dy) {
            view.setRotation(get(t) + ((float) Math.toDegrees(Math.atan2(dy, dx))));
        }
    }

    static class ScaleXset extends KeyCycleOscillator {
        ScaleXset() {
        }

        public void setProperty(View view, float t) {
            view.setScaleX(get(t));
        }
    }

    static class ScaleYset extends KeyCycleOscillator {
        ScaleYset() {
        }

        public void setProperty(View view, float t) {
            view.setScaleY(get(t));
        }
    }

    static class TranslationXset extends KeyCycleOscillator {
        TranslationXset() {
        }

        public void setProperty(View view, float t) {
            view.setTranslationX(get(t));
        }
    }

    static class TranslationYset extends KeyCycleOscillator {
        TranslationYset() {
        }

        public void setProperty(View view, float t) {
            view.setTranslationY(get(t));
        }
    }

    static class TranslationZset extends KeyCycleOscillator {
        TranslationZset() {
        }

        public void setProperty(View view, float t) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setTranslationZ(get(t));
            }
        }
    }

    static class CustomSet extends KeyCycleOscillator {
        float[] value = new float[1];

        CustomSet() {
        }

        public void setProperty(View view, float t) {
            this.value[0] = get(t);
            this.mCustom.setInterpolatedValue(view, this.value);
        }
    }

    static class ProgressSet extends KeyCycleOscillator {
        boolean mNoMethod = false;

        ProgressSet() {
        }

        public void setProperty(View view, float t) {
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(get(t));
            } else if (!this.mNoMethod) {
                Method method = null;
                try {
                    method = view.getClass().getMethod("setProgress", new Class[]{Float.TYPE});
                } catch (NoSuchMethodException e) {
                    this.mNoMethod = true;
                }
                if (method != null) {
                    try {
                        method.invoke(view, new Object[]{Float.valueOf(get(t))});
                    } catch (IllegalAccessException e2) {
                        Log.e(KeyCycleOscillator.TAG, "unable to setProgress", e2);
                    } catch (InvocationTargetException e3) {
                        Log.e(KeyCycleOscillator.TAG, "unable to setProgress", e3);
                    }
                }
            }
        }
    }

    private static class IntDoubleSort {
        private IntDoubleSort() {
        }

        static void sort(int[] key, float[] value, int low, int hi) {
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

        private static int partition(int[] array, float[] value, int low, int hi) {
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

        private static void swap(int[] array, float[] value, int a, int b) {
            int tmp = array[a];
            array[a] = array[b];
            array[b] = tmp;
            float tmpv = value[a];
            value[a] = value[b];
            value[b] = tmpv;
        }
    }

    private static class IntFloatFloatSort {
        private IntFloatFloatSort() {
        }

        static void sort(int[] key, float[] value1, float[] value2, int low, int hi) {
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
                    int p = partition(key, value1, value2, low2, hi2);
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

        private static int partition(int[] array, float[] value1, float[] value2, int low, int hi) {
            int pivot = array[hi];
            int i = low;
            for (int j = low; j < hi; j++) {
                if (array[j] <= pivot) {
                    swap(array, value1, value2, i, j);
                    i++;
                }
            }
            swap(array, value1, value2, i, hi);
            return i;
        }

        private static void swap(int[] array, float[] value1, float[] value2, int a, int b) {
            int tmp = array[a];
            array[a] = array[b];
            array[b] = tmp;
            float tmpFloat = value1[a];
            value1[a] = value1[b];
            value1[b] = tmpFloat;
            float tmpFloat2 = value2[a];
            value2[a] = value2[b];
            value2[b] = tmpFloat2;
        }
    }

    static class CycleOscillator {
        private static final String TAG = "CycleOscillator";
        static final int UNSET = -1;
        CurveFit mCurveFit;
        public HashMap<String, ConstraintAttribute> mCustomConstraints = new HashMap<>();
        float[] mOffset;
        Oscillator mOscillator = new Oscillator();
        float mPathLength;
        float[] mPeriod;
        double[] mPosition;
        float[] mScale;
        double[] mSplineSlopeCache;
        double[] mSplineValueCache;
        float[] mValues;
        private final int mVariesBy;
        int mWaveShape;

        CycleOscillator(int waveShape, int variesBy, int steps) {
            this.mWaveShape = waveShape;
            this.mVariesBy = variesBy;
            this.mOscillator.setType(waveShape);
            this.mValues = new float[steps];
            this.mPosition = new double[steps];
            this.mPeriod = new float[steps];
            this.mOffset = new float[steps];
            this.mScale = new float[steps];
        }

        public double getValues(float time) {
            CurveFit curveFit = this.mCurveFit;
            if (curveFit != null) {
                curveFit.getPos((double) time, this.mSplineValueCache);
            } else {
                double[] dArr = this.mSplineValueCache;
                dArr[0] = (double) this.mOffset[0];
                dArr[1] = (double) this.mValues[0];
            }
            return (this.mSplineValueCache[1] * this.mOscillator.getValue((double) time)) + this.mSplineValueCache[0];
        }

        public double getSlope(float time) {
            CurveFit curveFit = this.mCurveFit;
            if (curveFit != null) {
                curveFit.getSlope((double) time, this.mSplineSlopeCache);
                this.mCurveFit.getPos((double) time, this.mSplineValueCache);
            } else {
                double[] dArr = this.mSplineSlopeCache;
                dArr[0] = 0.0d;
                dArr[1] = 0.0d;
            }
            double waveValue = this.mOscillator.getValue((double) time);
            double waveSlope = this.mOscillator.getSlope((double) time);
            double[] dArr2 = this.mSplineSlopeCache;
            return dArr2[0] + (dArr2[1] * waveValue) + (this.mSplineValueCache[1] * waveSlope);
        }

        private ConstraintAttribute get(String attributeName, ConstraintAttribute.AttributeType attributeType) {
            if (this.mCustomConstraints.containsKey(attributeName)) {
                ConstraintAttribute ret = this.mCustomConstraints.get(attributeName);
                if (ret.getType() == attributeType) {
                    return ret;
                }
                throw new IllegalArgumentException("ConstraintAttribute is already a " + ret.getType().name());
            }
            ConstraintAttribute ret2 = new ConstraintAttribute(attributeName, attributeType);
            this.mCustomConstraints.put(attributeName, ret2);
            return ret2;
        }

        public void setPoint(int index, int framePosition, float wavePeriod, float offset, float values) {
            this.mPosition[index] = ((double) framePosition) / 100.0d;
            this.mPeriod[index] = wavePeriod;
            this.mOffset[index] = offset;
            this.mValues[index] = values;
        }

        public void setup(float pathLength) {
            this.mPathLength = pathLength;
            int length = this.mPosition.length;
            int[] iArr = new int[2];
            iArr[1] = 2;
            iArr[0] = length;
            double[][] splineValues = (double[][]) Array.newInstance(double.class, iArr);
            float[] fArr = this.mValues;
            this.mSplineValueCache = new double[(fArr.length + 1)];
            this.mSplineSlopeCache = new double[(fArr.length + 1)];
            if (this.mPosition[0] > 0.0d) {
                this.mOscillator.addPoint(0.0d, this.mPeriod[0]);
            }
            double[] dArr = this.mPosition;
            int last = dArr.length - 1;
            if (dArr[last] < 1.0d) {
                this.mOscillator.addPoint(1.0d, this.mPeriod[last]);
            }
            for (int i = 0; i < splineValues.length; i++) {
                splineValues[i][0] = (double) this.mOffset[i];
                int j = 0;
                while (true) {
                    float[] fArr2 = this.mValues;
                    if (j >= fArr2.length) {
                        break;
                    }
                    splineValues[j][1] = (double) fArr2[j];
                    j++;
                }
                this.mOscillator.addPoint(this.mPosition[i], this.mPeriod[i]);
            }
            this.mOscillator.normalize();
            double[] dArr2 = this.mPosition;
            if (dArr2.length > 1) {
                this.mCurveFit = CurveFit.get(0, dArr2, splineValues);
            } else {
                this.mCurveFit = null;
            }
        }
    }
}
