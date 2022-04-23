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
import java.util.Arrays;

public abstract class SplineSet {
    private static final String TAG = "SplineSet";
    private int count;
    protected CurveFit mCurveFit;
    protected int[] mTimePoints = new int[10];
    private String mType;
    protected float[] mValues = new float[10];

    public abstract void setProperty(View view, float f);

    public String toString() {
        String str = this.mType;
        DecimalFormat df = new DecimalFormat("##.##");
        for (int i = 0; i < this.count; i++) {
            str = str + "[" + this.mTimePoints[i] + " , " + df.format((double) this.mValues[i]) + "] ";
        }
        return str;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public float get(float t) {
        return (float) this.mCurveFit.getPos((double) t, 0);
    }

    public float getSlope(float t) {
        return (float) this.mCurveFit.getSlope((double) t, 0);
    }

    public CurveFit getCurveFit() {
        return this.mCurveFit;
    }

    static SplineSet makeCustomSpline(String str, SparseArray<ConstraintAttribute> attrList) {
        return new CustomSet(str, attrList);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static android.support.constraint.motion.SplineSet makeSpline(java.lang.String r1) {
        /*
            int r0 = r1.hashCode()
            switch(r0) {
                case -1249320806: goto L_0x00ae;
                case -1249320805: goto L_0x00a4;
                case -1225497657: goto L_0x0099;
                case -1225497656: goto L_0x008e;
                case -1225497655: goto L_0x0083;
                case -1001078227: goto L_0x0078;
                case -908189618: goto L_0x006d;
                case -908189617: goto L_0x0062;
                case -797520672: goto L_0x0057;
                case -760884510: goto L_0x004c;
                case -760884509: goto L_0x0041;
                case -40300674: goto L_0x0036;
                case -4379043: goto L_0x002b;
                case 37232917: goto L_0x0020;
                case 92909918: goto L_0x0015;
                case 156108012: goto L_0x0009;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x00b8
        L_0x0009:
            java.lang.String r0 = "waveOffset"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 10
            goto L_0x00b9
        L_0x0015:
            java.lang.String r0 = "alpha"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 0
            goto L_0x00b9
        L_0x0020:
            java.lang.String r0 = "transitionPathRotate"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 7
            goto L_0x00b9
        L_0x002b:
            java.lang.String r0 = "elevation"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 1
            goto L_0x00b9
        L_0x0036:
            java.lang.String r0 = "rotation"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 2
            goto L_0x00b9
        L_0x0041:
            java.lang.String r0 = "transformPivotY"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 6
            goto L_0x00b9
        L_0x004c:
            java.lang.String r0 = "transformPivotX"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 5
            goto L_0x00b9
        L_0x0057:
            java.lang.String r0 = "waveVariesBy"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 11
            goto L_0x00b9
        L_0x0062:
            java.lang.String r0 = "scaleY"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 9
            goto L_0x00b9
        L_0x006d:
            java.lang.String r0 = "scaleX"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 8
            goto L_0x00b9
        L_0x0078:
            java.lang.String r0 = "progress"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 15
            goto L_0x00b9
        L_0x0083:
            java.lang.String r0 = "translationZ"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 14
            goto L_0x00b9
        L_0x008e:
            java.lang.String r0 = "translationY"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 13
            goto L_0x00b9
        L_0x0099:
            java.lang.String r0 = "translationX"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 12
            goto L_0x00b9
        L_0x00a4:
            java.lang.String r0 = "rotationY"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 4
            goto L_0x00b9
        L_0x00ae:
            java.lang.String r0 = "rotationX"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 3
            goto L_0x00b9
        L_0x00b8:
            r0 = -1
        L_0x00b9:
            switch(r0) {
                case 0: goto L_0x0118;
                case 1: goto L_0x0112;
                case 2: goto L_0x010c;
                case 3: goto L_0x0106;
                case 4: goto L_0x0100;
                case 5: goto L_0x00fa;
                case 6: goto L_0x00f4;
                case 7: goto L_0x00ee;
                case 8: goto L_0x00e8;
                case 9: goto L_0x00e2;
                case 10: goto L_0x00dc;
                case 11: goto L_0x00d6;
                case 12: goto L_0x00d0;
                case 13: goto L_0x00ca;
                case 14: goto L_0x00c4;
                case 15: goto L_0x00be;
                default: goto L_0x00bc;
            }
        L_0x00bc:
            r0 = 0
            return r0
        L_0x00be:
            android.support.constraint.motion.SplineSet$ProgressSet r0 = new android.support.constraint.motion.SplineSet$ProgressSet
            r0.<init>()
            return r0
        L_0x00c4:
            android.support.constraint.motion.SplineSet$TranslationZset r0 = new android.support.constraint.motion.SplineSet$TranslationZset
            r0.<init>()
            return r0
        L_0x00ca:
            android.support.constraint.motion.SplineSet$TranslationYset r0 = new android.support.constraint.motion.SplineSet$TranslationYset
            r0.<init>()
            return r0
        L_0x00d0:
            android.support.constraint.motion.SplineSet$TranslationXset r0 = new android.support.constraint.motion.SplineSet$TranslationXset
            r0.<init>()
            return r0
        L_0x00d6:
            android.support.constraint.motion.SplineSet$AlphaSet r0 = new android.support.constraint.motion.SplineSet$AlphaSet
            r0.<init>()
            return r0
        L_0x00dc:
            android.support.constraint.motion.SplineSet$AlphaSet r0 = new android.support.constraint.motion.SplineSet$AlphaSet
            r0.<init>()
            return r0
        L_0x00e2:
            android.support.constraint.motion.SplineSet$ScaleYset r0 = new android.support.constraint.motion.SplineSet$ScaleYset
            r0.<init>()
            return r0
        L_0x00e8:
            android.support.constraint.motion.SplineSet$ScaleXset r0 = new android.support.constraint.motion.SplineSet$ScaleXset
            r0.<init>()
            return r0
        L_0x00ee:
            android.support.constraint.motion.SplineSet$PathRotate r0 = new android.support.constraint.motion.SplineSet$PathRotate
            r0.<init>()
            return r0
        L_0x00f4:
            android.support.constraint.motion.SplineSet$PivotYset r0 = new android.support.constraint.motion.SplineSet$PivotYset
            r0.<init>()
            return r0
        L_0x00fa:
            android.support.constraint.motion.SplineSet$PivotXset r0 = new android.support.constraint.motion.SplineSet$PivotXset
            r0.<init>()
            return r0
        L_0x0100:
            android.support.constraint.motion.SplineSet$RotationYset r0 = new android.support.constraint.motion.SplineSet$RotationYset
            r0.<init>()
            return r0
        L_0x0106:
            android.support.constraint.motion.SplineSet$RotationXset r0 = new android.support.constraint.motion.SplineSet$RotationXset
            r0.<init>()
            return r0
        L_0x010c:
            android.support.constraint.motion.SplineSet$RotationSet r0 = new android.support.constraint.motion.SplineSet$RotationSet
            r0.<init>()
            return r0
        L_0x0112:
            android.support.constraint.motion.SplineSet$ElevationSet r0 = new android.support.constraint.motion.SplineSet$ElevationSet
            r0.<init>()
            return r0
        L_0x0118:
            android.support.constraint.motion.SplineSet$AlphaSet r0 = new android.support.constraint.motion.SplineSet$AlphaSet
            r0.<init>()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.motion.SplineSet.makeSpline(java.lang.String):android.support.constraint.motion.SplineSet");
    }

    public void setPoint(int position, float value) {
        int[] iArr = this.mTimePoints;
        if (iArr.length < this.count + 1) {
            this.mTimePoints = Arrays.copyOf(iArr, iArr.length * 2);
            float[] fArr = this.mValues;
            this.mValues = Arrays.copyOf(fArr, fArr.length * 2);
        }
        int[] iArr2 = this.mTimePoints;
        int i = this.count;
        iArr2[i] = position;
        this.mValues[i] = value;
        this.count = i + 1;
    }

    public void setup(int curveType) {
        int i = this.count;
        if (i != 0) {
            Sort.doubleQuickSort(this.mTimePoints, this.mValues, 0, i - 1);
            int unique = 1;
            for (int i2 = 1; i2 < this.count; i2++) {
                int[] iArr = this.mTimePoints;
                if (iArr[i2 - 1] != iArr[i2]) {
                    unique++;
                }
            }
            double[] time = new double[unique];
            int[] iArr2 = new int[2];
            iArr2[1] = 1;
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
                values[k][0] = (double) this.mValues[i3];
                k++;
            }
            this.mCurveFit = CurveFit.get(curveType, time, values);
        }
    }

    static class ElevationSet extends SplineSet {
        ElevationSet() {
        }

        public void setProperty(View view, float t) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setElevation(get(t));
            }
        }
    }

    static class AlphaSet extends SplineSet {
        AlphaSet() {
        }

        public void setProperty(View view, float t) {
            view.setAlpha(get(t));
        }
    }

    static class RotationSet extends SplineSet {
        RotationSet() {
        }

        public void setProperty(View view, float t) {
            view.setRotation(get(t));
        }
    }

    static class RotationXset extends SplineSet {
        RotationXset() {
        }

        public void setProperty(View view, float t) {
            view.setRotationX(get(t));
        }
    }

    static class RotationYset extends SplineSet {
        RotationYset() {
        }

        public void setProperty(View view, float t) {
            view.setRotationY(get(t));
        }
    }

    static class PivotXset extends SplineSet {
        PivotXset() {
        }

        public void setProperty(View view, float t) {
            view.setPivotX(get(t));
        }
    }

    static class PivotYset extends SplineSet {
        PivotYset() {
        }

        public void setProperty(View view, float t) {
            view.setPivotY(get(t));
        }
    }

    static class PathRotate extends SplineSet {
        PathRotate() {
        }

        public void setProperty(View view, float t) {
        }

        public void setPathRotate(View view, float t, double dx, double dy) {
            view.setRotation(get(t) + ((float) Math.toDegrees(Math.atan2(dy, dx))));
        }
    }

    static class ScaleXset extends SplineSet {
        ScaleXset() {
        }

        public void setProperty(View view, float t) {
            view.setScaleX(get(t));
        }
    }

    static class ScaleYset extends SplineSet {
        ScaleYset() {
        }

        public void setProperty(View view, float t) {
            view.setScaleY(get(t));
        }
    }

    static class TranslationXset extends SplineSet {
        TranslationXset() {
        }

        public void setProperty(View view, float t) {
            view.setTranslationX(get(t));
        }
    }

    static class TranslationYset extends SplineSet {
        TranslationYset() {
        }

        public void setProperty(View view, float t) {
            view.setTranslationY(get(t));
        }
    }

    static class TranslationZset extends SplineSet {
        TranslationZset() {
        }

        public void setProperty(View view, float t) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setTranslationZ(get(t));
            }
        }
    }

    static class CustomSet extends SplineSet {
        String mAttributeName;
        SparseArray<ConstraintAttribute> mConstraintAttributeList;
        float[] mTempValues;

        public CustomSet(String attribute, SparseArray<ConstraintAttribute> attrList) {
            this.mAttributeName = attribute.split(",")[1];
            this.mConstraintAttributeList = attrList;
        }

        public void setup(int curveType) {
            int size = this.mConstraintAttributeList.size();
            int dimensionality = this.mConstraintAttributeList.valueAt(0).noOfInterpValues();
            double[] time = new double[size];
            this.mTempValues = new float[dimensionality];
            int[] iArr = new int[2];
            iArr[1] = dimensionality;
            iArr[0] = size;
            double[][] values = (double[][]) Array.newInstance(double.class, iArr);
            for (int i = 0; i < size; i++) {
                time[i] = ((double) this.mConstraintAttributeList.keyAt(i)) * 0.01d;
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
            }
            this.mCurveFit = CurveFit.get(curveType, time, values);
        }

        public void setPoint(int position, float value) {
            throw new RuntimeException("don't call for custom attribute call setPoint(pos, ConstraintAttribute)");
        }

        public void setPoint(int position, ConstraintAttribute value) {
            this.mConstraintAttributeList.append(position, value);
        }

        public void setProperty(View view, float t) {
            this.mCurveFit.getPos((double) t, this.mTempValues);
            this.mConstraintAttributeList.valueAt(0).setInterpolatedValue(view, this.mTempValues);
        }
    }

    static class ProgressSet extends SplineSet {
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
                        Log.e(SplineSet.TAG, "unable to setProgress", e2);
                    } catch (InvocationTargetException e3) {
                        Log.e(SplineSet.TAG, "unable to setProgress", e3);
                    }
                }
            }
        }
    }

    private static class Sort {
        private Sort() {
        }

        static void doubleQuickSort(int[] key, float[] value, int low, int hi) {
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
}
