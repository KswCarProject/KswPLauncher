package android.support.constraint.motion;

import android.os.Build;
import android.support.constraint.ConstraintAttribute;
import android.support.constraint.ConstraintSet;
import android.support.constraint.motion.SplineSet;
import android.support.constraint.motion.utils.Easing;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import com.ibm.icu.text.DateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

class MotionConstrainedPoint implements Comparable<MotionConstrainedPoint> {
    static final int CARTESIAN = 2;
    public static final boolean DEBUG = false;
    static final int PERPENDICULAR = 1;
    public static final String TAG = "MotionPaths";
    static String[] names = {"position", "x", DateFormat.YEAR, "width", "height", "pathRotate"};
    private float alpha = 1.0f;
    private boolean applyElevation = false;
    LinkedHashMap<String, ConstraintAttribute> attributes = new LinkedHashMap<>();
    private float elevation = 0.0f;
    private float height;
    private int mDrawPath = 0;
    private Easing mKeyFrameEasing;
    int mMode = 0;
    private float mPathRotate = Float.NaN;
    private float mPivotX = Float.NaN;
    private float mPivotY = Float.NaN;
    private float mProgress = Float.NaN;
    double[] mTempDelta = new double[18];
    double[] mTempValue = new double[18];
    int mVisibilityMode = 0;
    private float position;
    private float rotation = 0.0f;
    private float rotationX = 0.0f;
    public float rotationY = 0.0f;
    private float scaleX = 1.0f;
    private float scaleY = 1.0f;
    private float translationX = 0.0f;
    private float translationY = 0.0f;
    private float translationZ = 0.0f;
    int visibility;
    private float width;
    private float x;
    private float y;

    private boolean diff(float a, float b) {
        if (Float.isNaN(a) || Float.isNaN(b)) {
            if (Float.isNaN(a) != Float.isNaN(b)) {
                return true;
            }
            return false;
        } else if (Math.abs(a - b) > 1.0E-6f) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void different(MotionConstrainedPoint points, HashSet<String> keySet) {
        if (diff(this.alpha, points.alpha)) {
            keySet.add("alpha");
        }
        if (diff(this.elevation, points.elevation)) {
            keySet.add("elevation");
        }
        int i = this.visibility;
        int i2 = points.visibility;
        if (i != i2 && this.mVisibilityMode == 0 && (i == 0 || i2 == 0)) {
            keySet.add("alpha");
        }
        if (diff(this.rotation, points.rotation)) {
            keySet.add("rotation");
        }
        if (!Float.isNaN(this.mPathRotate) || !Float.isNaN(points.mPathRotate)) {
            keySet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.mProgress) || !Float.isNaN(points.mProgress)) {
            keySet.add(NotificationCompat.CATEGORY_PROGRESS);
        }
        if (diff(this.rotationX, points.rotationX)) {
            keySet.add("rotationX");
        }
        if (diff(this.rotationY, points.rotationY)) {
            keySet.add("rotationY");
        }
        if (diff(this.mPivotX, points.mPivotX)) {
            keySet.add("transformPivotX");
        }
        if (diff(this.mPivotY, points.mPivotY)) {
            keySet.add("transformPivotY");
        }
        if (diff(this.scaleX, points.scaleX)) {
            keySet.add("scaleX");
        }
        if (diff(this.scaleY, points.scaleY)) {
            keySet.add("scaleY");
        }
        if (diff(this.translationX, points.translationX)) {
            keySet.add("translationX");
        }
        if (diff(this.translationY, points.translationY)) {
            keySet.add("translationY");
        }
        if (diff(this.translationZ, points.translationZ)) {
            keySet.add("translationZ");
        }
    }

    /* access modifiers changed from: package-private */
    public void different(MotionConstrainedPoint points, boolean[] mask, String[] custom) {
        int c = 0 + 1;
        mask[0] = mask[0] | diff(this.position, points.position);
        int c2 = c + 1;
        mask[c] = mask[c] | diff(this.x, points.x);
        int c3 = c2 + 1;
        mask[c2] = mask[c2] | diff(this.y, points.y);
        int c4 = c3 + 1;
        mask[c3] = mask[c3] | diff(this.width, points.width);
        int i = c4 + 1;
        mask[c4] = mask[c4] | diff(this.height, points.height);
    }

    /* access modifiers changed from: package-private */
    public void fillStandard(double[] data, int[] toUse) {
        float[] set = {this.position, this.x, this.y, this.width, this.height, this.alpha, this.elevation, this.rotation, this.rotationX, this.rotationY, this.scaleX, this.scaleY, this.mPivotX, this.mPivotY, this.translationX, this.translationY, this.translationZ, this.mPathRotate};
        int c = 0;
        for (int i = 0; i < toUse.length; i++) {
            if (toUse[i] < set.length) {
                data[c] = (double) set[toUse[i]];
                c++;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean hasCustomData(String name) {
        return this.attributes.containsKey(name);
    }

    /* access modifiers changed from: package-private */
    public int getCustomDataCount(String name) {
        return this.attributes.get(name).noOfInterpValues();
    }

    /* access modifiers changed from: package-private */
    public int getCustomData(String name, double[] value, int offset) {
        ConstraintAttribute a = this.attributes.get(name);
        if (a.noOfInterpValues() == 1) {
            value[offset] = (double) a.getValueToInterpolate();
            return 1;
        }
        int N = a.noOfInterpValues();
        float[] f = new float[N];
        a.getValuesToInterpolate(f);
        int i = 0;
        while (i < N) {
            value[offset] = (double) f[i];
            i++;
            offset++;
        }
        return N;
    }

    /* access modifiers changed from: package-private */
    public void setBounds(float x2, float y2, float w, float h) {
        this.x = x2;
        this.y = y2;
        this.width = w;
        this.height = h;
    }

    public int compareTo(MotionConstrainedPoint o) {
        return Float.compare(this.position, o.position);
    }

    public void applyParameters(View view) {
        this.visibility = view.getVisibility();
        this.alpha = view.getVisibility() != 0 ? 0.0f : view.getAlpha();
        this.applyElevation = false;
        if (Build.VERSION.SDK_INT >= 21) {
            this.elevation = view.getElevation();
        }
        this.rotation = view.getRotation();
        this.rotationX = view.getRotationX();
        this.rotationY = view.getRotationY();
        this.scaleX = view.getScaleX();
        this.scaleY = view.getScaleY();
        this.mPivotX = view.getPivotX();
        this.mPivotY = view.getPivotY();
        this.translationX = view.getTranslationX();
        this.translationY = view.getTranslationY();
        if (Build.VERSION.SDK_INT >= 21) {
            this.translationZ = view.getTranslationZ();
        }
    }

    public void applyParameters(ConstraintSet.Constraint c) {
        this.mVisibilityMode = c.propertySet.mVisibilityMode;
        this.visibility = c.propertySet.visibility;
        this.alpha = (c.propertySet.visibility == 0 || this.mVisibilityMode != 0) ? c.propertySet.alpha : 0.0f;
        this.applyElevation = c.transform.applyElevation;
        this.elevation = c.transform.elevation;
        this.rotation = c.transform.rotation;
        this.rotationX = c.transform.rotationX;
        this.rotationY = c.transform.rotationY;
        this.scaleX = c.transform.scaleX;
        this.scaleY = c.transform.scaleY;
        this.mPivotX = c.transform.transformPivotX;
        this.mPivotY = c.transform.transformPivotY;
        this.translationX = c.transform.translationX;
        this.translationY = c.transform.translationY;
        this.translationZ = c.transform.translationZ;
        this.mKeyFrameEasing = Easing.getInterpolator(c.motion.mTransitionEasing);
        this.mPathRotate = c.motion.mPathRotate;
        this.mDrawPath = c.motion.mDrawPath;
        this.mProgress = c.propertySet.mProgress;
        for (String s : c.mCustomConstraints.keySet()) {
            ConstraintAttribute attr = c.mCustomConstraints.get(s);
            if (attr.getType() != ConstraintAttribute.AttributeType.STRING_TYPE) {
                this.attributes.put(s, attr);
            }
        }
    }

    public void addValues(HashMap<String, SplineSet> splines, int mFramePosition) {
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
                        c = 11;
                        break;
                    }
                    break;
                case -1225497656:
                    if (s.equals("translationY")) {
                        c = 12;
                        break;
                    }
                    break;
                case -1225497655:
                    if (s.equals("translationZ")) {
                        c = 13;
                        break;
                    }
                    break;
                case -1001078227:
                    if (s.equals(NotificationCompat.CATEGORY_PROGRESS)) {
                        c = 8;
                        break;
                    }
                    break;
                case -908189618:
                    if (s.equals("scaleX")) {
                        c = 9;
                        break;
                    }
                    break;
                case -908189617:
                    if (s.equals("scaleY")) {
                        c = 10;
                        break;
                    }
                    break;
                case -760884510:
                    if (s.equals("transformPivotX")) {
                        c = 5;
                        break;
                    }
                    break;
                case -760884509:
                    if (s.equals("transformPivotY")) {
                        c = 6;
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
                        c = 7;
                        break;
                    }
                    break;
                case 92909918:
                    if (s.equals("alpha")) {
                        c = 0;
                        break;
                    }
                    break;
            }
            float f = 1.0f;
            float f2 = 0.0f;
            switch (c) {
                case 0:
                    if (!Float.isNaN(this.alpha)) {
                        f = this.alpha;
                    }
                    splineSet.setPoint(mFramePosition, f);
                    break;
                case 1:
                    if (!Float.isNaN(this.elevation)) {
                        f2 = this.elevation;
                    }
                    splineSet.setPoint(mFramePosition, f2);
                    break;
                case 2:
                    if (!Float.isNaN(this.rotation)) {
                        f2 = this.rotation;
                    }
                    splineSet.setPoint(mFramePosition, f2);
                    break;
                case 3:
                    if (!Float.isNaN(this.rotationX)) {
                        f2 = this.rotationX;
                    }
                    splineSet.setPoint(mFramePosition, f2);
                    break;
                case 4:
                    if (!Float.isNaN(this.rotationY)) {
                        f2 = this.rotationY;
                    }
                    splineSet.setPoint(mFramePosition, f2);
                    break;
                case 5:
                    if (!Float.isNaN(this.mPivotX)) {
                        f2 = this.mPivotX;
                    }
                    splineSet.setPoint(mFramePosition, f2);
                    break;
                case 6:
                    if (!Float.isNaN(this.mPivotY)) {
                        f2 = this.mPivotY;
                    }
                    splineSet.setPoint(mFramePosition, f2);
                    break;
                case 7:
                    if (!Float.isNaN(this.mPathRotate)) {
                        f2 = this.mPathRotate;
                    }
                    splineSet.setPoint(mFramePosition, f2);
                    break;
                case 8:
                    if (!Float.isNaN(this.mProgress)) {
                        f2 = this.mProgress;
                    }
                    splineSet.setPoint(mFramePosition, f2);
                    break;
                case 9:
                    if (!Float.isNaN(this.scaleX)) {
                        f = this.scaleX;
                    }
                    splineSet.setPoint(mFramePosition, f);
                    break;
                case 10:
                    if (!Float.isNaN(this.scaleY)) {
                        f = this.scaleY;
                    }
                    splineSet.setPoint(mFramePosition, f);
                    break;
                case 11:
                    if (!Float.isNaN(this.translationX)) {
                        f2 = this.translationX;
                    }
                    splineSet.setPoint(mFramePosition, f2);
                    break;
                case 12:
                    if (!Float.isNaN(this.translationY)) {
                        f2 = this.translationY;
                    }
                    splineSet.setPoint(mFramePosition, f2);
                    break;
                case 13:
                    if (!Float.isNaN(this.translationZ)) {
                        f2 = this.translationZ;
                    }
                    splineSet.setPoint(mFramePosition, f2);
                    break;
                default:
                    if (!s.startsWith("CUSTOM")) {
                        Log.e("MotionPaths", "UNKNOWN spline " + s);
                        break;
                    } else {
                        String customName = s.split(",")[1];
                        if (!this.attributes.containsKey(customName)) {
                            Log.e("MotionPaths", "UNKNOWN customName " + customName);
                            break;
                        } else {
                            ConstraintAttribute custom = this.attributes.get(customName);
                            if (!(splineSet instanceof SplineSet.CustomSet)) {
                                Log.e("MotionPaths", s + " splineSet not a CustomSet frame = " + mFramePosition + ", value" + custom.getValueToInterpolate() + splineSet);
                                break;
                            } else {
                                ((SplineSet.CustomSet) splineSet).setPoint(mFramePosition, custom);
                                break;
                            }
                        }
                    }
            }
        }
    }

    public void setState(View view) {
        setBounds(view.getX(), view.getY(), (float) view.getWidth(), (float) view.getHeight());
        applyParameters(view);
    }

    public void setState(ConstraintWidget cw, ConstraintSet constraintSet, int viewId) {
        setBounds((float) cw.getX(), (float) cw.getY(), (float) cw.getWidth(), (float) cw.getHeight());
        applyParameters(constraintSet.getParameters(viewId));
    }
}
