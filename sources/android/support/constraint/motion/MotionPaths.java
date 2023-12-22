package android.support.constraint.motion;

import android.support.constraint.ConstraintAttribute;
import android.support.constraint.ConstraintSet;
import android.support.constraint.motion.utils.Easing;
import android.support.constraint.solver.widgets.analyzer.BasicMeasure;
import android.view.View;
import com.ibm.icu.text.DateFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Set;

/* loaded from: classes.dex */
class MotionPaths implements Comparable<MotionPaths> {
    static final int CARTESIAN = 2;
    public static final boolean DEBUG = false;
    static final int OFF_HEIGHT = 4;
    static final int OFF_PATH_ROTATE = 5;
    static final int OFF_POSITION = 0;
    static final int OFF_WIDTH = 3;
    static final int OFF_X = 1;
    static final int OFF_Y = 2;
    public static final boolean OLD_WAY = false;
    static final int PERPENDICULAR = 1;
    static final int SCREEN = 3;
    public static final String TAG = "MotionPaths";
    static String[] names = {"position", "x", DateFormat.YEAR, "width", "height", "pathRotate"};
    LinkedHashMap<String, ConstraintAttribute> attributes;
    float height;
    int mDrawPath;
    Easing mKeyFrameEasing;
    int mMode;
    int mPathMotionArc;
    float mPathRotate;
    float mProgress;
    double[] mTempDelta;
    double[] mTempValue;
    float position;
    float time;
    float width;

    /* renamed from: x */
    float f11x;

    /* renamed from: y */
    float f12y;

    public MotionPaths() {
        this.mDrawPath = 0;
        this.mPathRotate = Float.NaN;
        this.mProgress = Float.NaN;
        this.mPathMotionArc = Key.UNSET;
        this.attributes = new LinkedHashMap<>();
        this.mMode = 0;
        this.mTempValue = new double[18];
        this.mTempDelta = new double[18];
    }

    void initCartesian(KeyPosition c, MotionPaths startTimePoint, MotionPaths endTimePoint) {
        float dxdx;
        float dydy;
        float position = c.mFramePosition / 100.0f;
        this.time = position;
        this.mDrawPath = c.mDrawPath;
        float scaleWidth = Float.isNaN(c.mPercentWidth) ? position : c.mPercentWidth;
        float scaleHeight = Float.isNaN(c.mPercentHeight) ? position : c.mPercentHeight;
        float f = endTimePoint.width;
        float f2 = startTimePoint.width;
        float scaleX = f - f2;
        float f3 = endTimePoint.height;
        float f4 = startTimePoint.height;
        float scaleY = f3 - f4;
        this.position = this.time;
        float f5 = startTimePoint.f11x;
        float startCenterX = f5 + (f2 / 2.0f);
        float position2 = startTimePoint.f12y;
        float startCenterY = position2 + (f4 / 2.0f);
        float endCenterX = endTimePoint.f11x + (f / 2.0f);
        float endCenterY = endTimePoint.f12y + (f3 / 2.0f);
        float pathVectorX = endCenterX - startCenterX;
        float pathVectorY = endCenterY - startCenterY;
        this.f11x = (int) ((f5 + (pathVectorX * position)) - ((scaleX * scaleWidth) / 2.0f));
        this.f12y = (int) ((position2 + (pathVectorY * position)) - ((scaleY * scaleHeight) / 2.0f));
        this.width = (int) (f2 + (scaleX * scaleWidth));
        this.height = (int) (f4 + (scaleY * scaleHeight));
        if (!Float.isNaN(c.mPercentX)) {
            dxdx = c.mPercentX;
        } else {
            dxdx = position;
        }
        float dydx = Float.isNaN(c.mAltPercentY) ? 0.0f : c.mAltPercentY;
        if (!Float.isNaN(c.mPercentY)) {
            dydy = c.mPercentY;
        } else {
            dydy = position;
        }
        float dxdy = Float.isNaN(c.mAltPercentX) ? 0.0f : c.mAltPercentX;
        this.mMode = 2;
        this.f11x = (int) (((startTimePoint.f11x + (pathVectorX * dxdx)) + (pathVectorY * dxdy)) - ((scaleX * scaleWidth) / 2.0f));
        this.f12y = (int) (((startTimePoint.f12y + (pathVectorX * dydx)) + (pathVectorY * dydy)) - ((scaleY * scaleHeight) / 2.0f));
        this.mKeyFrameEasing = Easing.getInterpolator(c.mTransitionEasing);
        this.mPathMotionArc = c.mPathMotionArc;
    }

    public MotionPaths(int parentWidth, int parentHeight, KeyPosition c, MotionPaths startTimePoint, MotionPaths endTimePoint) {
        this.mDrawPath = 0;
        this.mPathRotate = Float.NaN;
        this.mProgress = Float.NaN;
        this.mPathMotionArc = Key.UNSET;
        this.attributes = new LinkedHashMap<>();
        this.mMode = 0;
        this.mTempValue = new double[18];
        this.mTempDelta = new double[18];
        switch (c.mPositionType) {
            case 1:
                initPath(c, startTimePoint, endTimePoint);
                return;
            case 2:
                initScreen(parentWidth, parentHeight, c, startTimePoint, endTimePoint);
                return;
            default:
                initCartesian(c, startTimePoint, endTimePoint);
                return;
        }
    }

    void initScreen(int parentWidth, int parentHeight, KeyPosition c, MotionPaths startTimePoint, MotionPaths endTimePoint) {
        float position = c.mFramePosition / 100.0f;
        this.time = position;
        this.mDrawPath = c.mDrawPath;
        float scaleWidth = Float.isNaN(c.mPercentWidth) ? position : c.mPercentWidth;
        float scaleHeight = Float.isNaN(c.mPercentHeight) ? position : c.mPercentHeight;
        float f = endTimePoint.width;
        float f2 = startTimePoint.width;
        float scaleX = f - f2;
        float f3 = endTimePoint.height;
        float f4 = startTimePoint.height;
        float scaleY = f3 - f4;
        this.position = this.time;
        float f5 = startTimePoint.f11x;
        float startCenterX = f5 + (f2 / 2.0f);
        float position2 = startTimePoint.f12y;
        float startCenterY = position2 + (f4 / 2.0f);
        float endCenterX = endTimePoint.f11x + (f / 2.0f);
        float endCenterY = endTimePoint.f12y + (f3 / 2.0f);
        float pathVectorX = endCenterX - startCenterX;
        float pathVectorY = endCenterY - startCenterY;
        this.f11x = (int) ((f5 + (pathVectorX * position)) - ((scaleX * scaleWidth) / 2.0f));
        this.f12y = (int) ((position2 + (pathVectorY * position)) - ((scaleY * scaleHeight) / 2.0f));
        this.width = (int) (f2 + (scaleX * scaleWidth));
        this.height = (int) (f4 + (scaleY * scaleHeight));
        this.mMode = 3;
        if (!Float.isNaN(c.mPercentX)) {
            this.f11x = (int) (c.mPercentX * ((int) (parentWidth - this.width)));
        }
        if (!Float.isNaN(c.mPercentY)) {
            this.f12y = (int) (c.mPercentY * ((int) (parentHeight - this.height)));
        }
        this.mKeyFrameEasing = Easing.getInterpolator(c.mTransitionEasing);
        this.mPathMotionArc = c.mPathMotionArc;
    }

    void initPath(KeyPosition c, MotionPaths startTimePoint, MotionPaths endTimePoint) {
        float f;
        float f2;
        float f3;
        float position;
        float position2 = c.mFramePosition / 100.0f;
        this.time = position2;
        this.mDrawPath = c.mDrawPath;
        float scaleWidth = Float.isNaN(c.mPercentWidth) ? position2 : c.mPercentWidth;
        float scaleHeight = Float.isNaN(c.mPercentHeight) ? position2 : c.mPercentHeight;
        float scaleX = endTimePoint.width - startTimePoint.width;
        float scaleY = endTimePoint.height - startTimePoint.height;
        this.position = this.time;
        float path = Float.isNaN(c.mPercentX) ? position2 : c.mPercentX;
        float startCenterX = (startTimePoint.width / 2.0f) + startTimePoint.f11x;
        float startCenterY = startTimePoint.f12y + (startTimePoint.height / 2.0f);
        float endCenterX = endTimePoint.f11x + (endTimePoint.width / 2.0f);
        float endCenterY = endTimePoint.f12y + (endTimePoint.height / 2.0f);
        float pathVectorX = endCenterX - startCenterX;
        float pathVectorY = endCenterY - startCenterY;
        this.f11x = (int) ((f + (pathVectorX * path)) - ((scaleX * scaleWidth) / 2.0f));
        this.f12y = (int) ((f3 + (pathVectorY * path)) - ((scaleY * scaleHeight) / 2.0f));
        this.width = (int) (f2 + (scaleX * scaleWidth));
        this.height = (int) (position + (scaleY * scaleHeight));
        float perpendicular = Float.isNaN(c.mPercentY) ? 0.0f : c.mPercentY;
        float perpendicularX = -pathVectorY;
        float normalX = perpendicularX * perpendicular;
        float normalY = pathVectorX * perpendicular;
        this.mMode = 1;
        float f4 = (int) ((startTimePoint.f11x + (pathVectorX * path)) - ((scaleX * scaleWidth) / 2.0f));
        this.f11x = f4;
        float f5 = (int) ((startTimePoint.f12y + (pathVectorY * path)) - ((scaleY * scaleHeight) / 2.0f));
        this.f12y = f5;
        this.f11x = f4 + normalX;
        this.f12y = f5 + normalY;
        this.mKeyFrameEasing = Easing.getInterpolator(c.mTransitionEasing);
        this.mPathMotionArc = c.mPathMotionArc;
    }

    private static final float xRotate(float sin, float cos, float cx, float cy, float x, float y) {
        return (((x - cx) * cos) - ((y - cy) * sin)) + cx;
    }

    private static final float yRotate(float sin, float cos, float cx, float cy, float x, float y) {
        return ((x - cx) * sin) + ((y - cy) * cos) + cy;
    }

    private boolean diff(float a, float b) {
        return (Float.isNaN(a) || Float.isNaN(b)) ? Float.isNaN(a) != Float.isNaN(b) : Math.abs(a - b) > 1.0E-6f;
    }

    void different(MotionPaths points, boolean[] mask, String[] custom, boolean arcMode) {
        int c = 0 + 1;
        mask[0] = mask[0] | diff(this.position, points.position);
        int c2 = c + 1;
        mask[c] = mask[c] | diff(this.f11x, points.f11x) | arcMode;
        int c3 = c2 + 1;
        mask[c2] = mask[c2] | diff(this.f12y, points.f12y) | arcMode;
        int c4 = c3 + 1;
        mask[c3] = mask[c3] | diff(this.width, points.width);
        int i = c4 + 1;
        mask[c4] = mask[c4] | diff(this.height, points.height);
    }

    void getCenter(int[] toUse, double[] data, float[] point, int offset) {
        float v_x = this.f11x;
        float v_y = this.f12y;
        float v_width = this.width;
        float v_height = this.height;
        for (int i = 0; i < toUse.length; i++) {
            float value = (float) data[i];
            switch (toUse[i]) {
                case 1:
                    v_x = value;
                    break;
                case 2:
                    v_y = value;
                    break;
                case 3:
                    v_width = value;
                    break;
                case 4:
                    v_height = value;
                    break;
            }
        }
        point[offset] = (v_width / 2.0f) + v_x + 0.0f;
        point[offset + 1] = (v_height / 2.0f) + v_y + 0.0f;
    }

    void getBounds(int[] toUse, double[] data, float[] point, int offset) {
        float f = this.f11x;
        float f2 = this.f12y;
        float v_width = this.width;
        float v_height = this.height;
        for (int i = 0; i < toUse.length; i++) {
            float value = (float) data[i];
            switch (toUse[i]) {
                case 3:
                    v_width = value;
                    break;
                case 4:
                    v_height = value;
                    break;
            }
        }
        point[offset] = v_width;
        point[offset + 1] = v_height;
    }

    void setView(View view, int[] toUse, double[] data, double[] slope, double[] cycle) {
        float v_x;
        float v_width;
        float v_height;
        float v_x2 = this.f11x;
        float v_y = this.f12y;
        float v_width2 = this.width;
        float v_height2 = this.height;
        float dv_x = 0.0f;
        float dv_y = 0.0f;
        float dv_width = 0.0f;
        float dv_height = 0.0f;
        float delta_path = 0.0f;
        float path_rotate = Float.NaN;
        if (toUse.length != 0) {
            v_x = v_x2;
            if (this.mTempValue.length <= toUse[toUse.length - 1]) {
                int scratch_data_length = toUse[toUse.length - 1] + 1;
                this.mTempValue = new double[scratch_data_length];
                this.mTempDelta = new double[scratch_data_length];
            }
        } else {
            v_x = v_x2;
        }
        Arrays.fill(this.mTempValue, Double.NaN);
        for (int i = 0; i < toUse.length; i++) {
            this.mTempValue[toUse[i]] = data[i];
            this.mTempDelta[toUse[i]] = slope[i];
        }
        int i2 = 0;
        float v_y2 = v_y;
        float v_width3 = v_width2;
        while (true) {
            double[] dArr = this.mTempValue;
            if (i2 < dArr.length) {
                if (Double.isNaN(dArr[i2])) {
                    if (cycle == null) {
                        v_width = v_width3;
                        v_height = v_height2;
                    } else if (cycle[i2] == 0.0d) {
                        v_width = v_width3;
                        v_height = v_height2;
                    }
                    v_width3 = v_width;
                    v_height2 = v_height;
                    i2++;
                }
                double deltaCycle = cycle != null ? cycle[i2] : 0.0d;
                if (!Double.isNaN(this.mTempValue[i2])) {
                    deltaCycle = this.mTempValue[i2] + deltaCycle;
                }
                float value = (float) deltaCycle;
                v_width = v_width3;
                v_height = v_height2;
                float dvalue = (float) this.mTempDelta[i2];
                switch (i2) {
                    case 0:
                        delta_path = value;
                        v_width3 = v_width;
                        v_height2 = v_height;
                        continue;
                        i2++;
                    case 1:
                        dv_x = dvalue;
                        v_x = value;
                        v_width3 = v_width;
                        v_height2 = v_height;
                        continue;
                        i2++;
                    case 2:
                        v_y2 = value;
                        dv_y = dvalue;
                        v_width3 = v_width;
                        v_height2 = v_height;
                        continue;
                        i2++;
                    case 3:
                        dv_width = dvalue;
                        v_width3 = value;
                        v_height2 = v_height;
                        continue;
                        i2++;
                    case 4:
                        v_height2 = value;
                        dv_height = dvalue;
                        v_width3 = v_width;
                        continue;
                        i2++;
                    case 5:
                        path_rotate = value;
                        v_width3 = v_width;
                        v_height2 = v_height;
                        continue;
                        i2++;
                }
                v_width3 = v_width;
                v_height2 = v_height;
                i2++;
            } else {
                float v_width4 = v_width3;
                float v_height3 = v_height2;
                if (Float.isNaN(path_rotate)) {
                    if (!Float.isNaN(Float.NaN)) {
                        view.setRotation(Float.NaN);
                    }
                } else {
                    float rot = Float.isNaN(Float.NaN) ? 0.0f : Float.NaN;
                    float dx = (dv_width / 2.0f) + dv_x;
                    float dy = (dv_height / 2.0f) + dv_y;
                    view.setRotation((float) (rot + path_rotate + Math.toDegrees(Math.atan2(dy, dx))));
                }
                int l = (int) (v_x + 0.5f);
                int t = (int) (v_y2 + 0.5f);
                int r = (int) (v_x + 0.5f + v_width4);
                int b = (int) (0.5f + v_y2 + v_height3);
                int i_width = r - l;
                int i_height = b - t;
                boolean remeasure = (i_width == view.getMeasuredWidth() && i_height == view.getMeasuredHeight()) ? false : true;
                if (remeasure) {
                    int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(i_width, BasicMeasure.EXACTLY);
                    int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(i_height, BasicMeasure.EXACTLY);
                    view.measure(widthMeasureSpec, heightMeasureSpec);
                }
                view.layout(l, t, r, b);
                return;
            }
        }
    }

    void getRect(int[] toUse, double[] data, float[] path, int offset) {
        float v_x = this.f11x;
        float v_y = this.f12y;
        float v_width = this.width;
        float v_height = this.height;
        float alpha = 0.0f;
        boolean z = false;
        boolean z2 = false;
        int i = 0;
        while (true) {
            float delta_path = alpha;
            if (i < toUse.length) {
                boolean z3 = z;
                boolean z4 = z2;
                float value = (float) data[i];
                switch (toUse[i]) {
                    case 0:
                        delta_path = value;
                        break;
                    case 1:
                        v_x = value;
                        break;
                    case 2:
                        v_y = value;
                        break;
                    case 3:
                        v_width = value;
                        break;
                    case 4:
                        v_height = value;
                        break;
                }
                i++;
                z = z3;
                alpha = delta_path;
                z2 = z4;
            } else {
                float x1 = v_x;
                float y1 = v_y;
                float x2 = v_x + v_width;
                float y2 = y1;
                float x3 = x2;
                float y3 = v_y + v_height;
                float x4 = x1;
                float y4 = y3;
                float cx = x1 + (v_width / 2.0f);
                float cy = y1 + (v_height / 2.0f);
                if (!Float.isNaN(Float.NaN)) {
                    cx = x1 + ((x2 - x1) * Float.NaN);
                }
                if (!Float.isNaN(Float.NaN)) {
                    cy = y1 + ((y3 - y1) * Float.NaN);
                }
                if (1.0f != 1.0f) {
                    float midx = (x1 + x2) / 2.0f;
                    x1 = ((x1 - midx) * 1.0f) + midx;
                    x2 = ((x2 - midx) * 1.0f) + midx;
                    x3 = ((x3 - midx) * 1.0f) + midx;
                    x4 = ((x4 - midx) * 1.0f) + midx;
                }
                if (1.0f != 1.0f) {
                    float midy = (y1 + y3) / 2.0f;
                    y1 = ((y1 - midy) * 1.0f) + midy;
                    y2 = ((y2 - midy) * 1.0f) + midy;
                    y3 = ((y3 - midy) * 1.0f) + midy;
                    y4 = ((y4 - midy) * 1.0f) + midy;
                }
                if (0.0f != 0.0f) {
                    float sin = (float) Math.sin(Math.toRadians(0.0f));
                    float cos = (float) Math.cos(Math.toRadians(0.0f));
                    float f = cx;
                    float f2 = cy;
                    float f3 = x1;
                    float f4 = y1;
                    float tx1 = xRotate(sin, cos, f, f2, f3, f4);
                    float ty1 = yRotate(sin, cos, f, f2, f3, f4);
                    float f5 = x2;
                    float f6 = y2;
                    float tx2 = xRotate(sin, cos, f, f2, f5, f6);
                    float ty2 = yRotate(sin, cos, f, f2, f5, f6);
                    float f7 = x3;
                    float f8 = y3;
                    float tx3 = xRotate(sin, cos, f, f2, f7, f8);
                    float ty3 = yRotate(sin, cos, f, f2, f7, f8);
                    float f9 = x4;
                    float f10 = y4;
                    float tx4 = xRotate(sin, cos, f, f2, f9, f10);
                    float ty4 = yRotate(sin, cos, f, f2, f9, f10);
                    x1 = tx1;
                    y1 = ty1;
                    x2 = tx2;
                    y2 = ty2;
                    x3 = tx3;
                    y3 = ty3;
                    x4 = tx4;
                    y4 = ty4;
                }
                int offset2 = offset + 1;
                path[offset] = x1 + 0.0f;
                int offset3 = offset2 + 1;
                path[offset2] = y1 + 0.0f;
                int offset4 = offset3 + 1;
                path[offset3] = x2 + 0.0f;
                int offset5 = offset4 + 1;
                path[offset4] = y2 + 0.0f;
                int offset6 = offset5 + 1;
                path[offset5] = x3 + 0.0f;
                int offset7 = offset6 + 1;
                path[offset6] = y3 + 0.0f;
                int offset8 = offset7 + 1;
                path[offset7] = x4 + 0.0f;
                int i2 = offset8 + 1;
                path[offset8] = y4 + 0.0f;
                return;
            }
        }
    }

    void setDpDt(float locationX, float locationY, float[] mAnchorDpDt, int[] toUse, double[] deltaData, double[] data) {
        float d_x = 0.0f;
        float d_y = 0.0f;
        float d_width = 0.0f;
        float d_height = 0.0f;
        for (int i = 0; i < toUse.length; i++) {
            float deltaV = (float) deltaData[i];
            float f = (float) data[i];
            switch (toUse[i]) {
                case 1:
                    d_x = deltaV;
                    break;
                case 2:
                    d_y = deltaV;
                    break;
                case 3:
                    d_width = deltaV;
                    break;
                case 4:
                    d_height = deltaV;
                    break;
            }
        }
        float deltaX = d_x - ((0.0f * d_width) / 2.0f);
        float deltaY = d_y - ((0.0f * d_height) / 2.0f);
        float deltaWidth = (0.0f + 1.0f) * d_width;
        float deltaHeight = (0.0f + 1.0f) * d_height;
        float deltaRight = deltaX + deltaWidth;
        float deltaBottom = deltaY + deltaHeight;
        mAnchorDpDt[0] = ((1.0f - locationX) * deltaX) + (deltaRight * locationX) + 0.0f;
        mAnchorDpDt[1] = ((1.0f - locationY) * deltaY) + (deltaBottom * locationY) + 0.0f;
    }

    void fillStandard(double[] data, int[] toUse) {
        float[] set = {this.position, this.f11x, this.f12y, this.width, this.height, this.mPathRotate};
        int c = 0;
        for (int i = 0; i < toUse.length; i++) {
            if (toUse[i] < set.length) {
                data[c] = set[toUse[i]];
                c++;
            }
        }
    }

    boolean hasCustomData(String name) {
        return this.attributes.containsKey(name);
    }

    int getCustomDataCount(String name) {
        return this.attributes.get(name).noOfInterpValues();
    }

    int getCustomData(String name, double[] value, int offset) {
        ConstraintAttribute a = this.attributes.get(name);
        if (a.noOfInterpValues() == 1) {
            value[offset] = a.getValueToInterpolate();
            return 1;
        }
        int N = a.noOfInterpValues();
        float[] f = new float[N];
        a.getValuesToInterpolate(f);
        int i = 0;
        while (i < N) {
            value[offset] = f[i];
            i++;
            offset++;
        }
        return N;
    }

    void setBounds(float x, float y, float w, float h) {
        this.f11x = x;
        this.f12y = y;
        this.width = w;
        this.height = h;
    }

    @Override // java.lang.Comparable
    public int compareTo(MotionPaths o) {
        return Float.compare(this.position, o.position);
    }

    public void applyParameters(ConstraintSet.Constraint c) {
        this.mKeyFrameEasing = Easing.getInterpolator(c.motion.mTransitionEasing);
        this.mPathMotionArc = c.motion.mPathMotionArc;
        this.mPathRotate = c.motion.mPathRotate;
        this.mDrawPath = c.motion.mDrawPath;
        this.mProgress = c.propertySet.mProgress;
        Set<String> at = c.mCustomConstraints.keySet();
        for (String s : at) {
            ConstraintAttribute attr = c.mCustomConstraints.get(s);
            if (attr.getType() != ConstraintAttribute.AttributeType.STRING_TYPE) {
                this.attributes.put(s, attr);
            }
        }
    }
}
