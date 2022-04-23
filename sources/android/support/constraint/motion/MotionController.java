package android.support.constraint.motion;

import android.graphics.RectF;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.motion.KeyCycleOscillator;
import android.support.constraint.motion.SplineSet;
import android.support.constraint.motion.TimeCycleSplineSet;
import android.support.constraint.motion.utils.CurveFit;
import android.support.constraint.motion.utils.Easing;
import android.support.constraint.motion.utils.VelocityMatrix;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.analyzer.BasicMeasure;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class MotionController {
    private static final boolean DEBUG = false;
    public static final int DRAW_PATH_AS_CONFIGURED = 4;
    public static final int DRAW_PATH_BASIC = 1;
    public static final int DRAW_PATH_CARTESIAN = 3;
    public static final int DRAW_PATH_NONE = 0;
    public static final int DRAW_PATH_RECTANGLE = 5;
    public static final int DRAW_PATH_RELATIVE = 2;
    public static final int DRAW_PATH_SCREEN = 6;
    private static final boolean FAVOR_FIXED_SIZE_VIEWS = false;
    public static final int HORIZONTAL_PATH_X = 2;
    public static final int HORIZONTAL_PATH_Y = 3;
    public static final int PATH_PERCENT = 0;
    public static final int PATH_PERPENDICULAR = 1;
    private static final String TAG = "MotionController";
    public static final int VERTICAL_PATH_X = 4;
    public static final int VERTICAL_PATH_Y = 5;
    private int MAX_DIMENSION = 4;
    String[] attributeTable;
    private CurveFit mArcSpline;
    private int[] mAttributeInterpCount;
    private String[] mAttributeNames;
    private HashMap<String, SplineSet> mAttributesMap;
    String mConstraintTag;
    private int mCurveFitType = -1;
    private HashMap<String, KeyCycleOscillator> mCycleMap;
    private MotionPaths mEndMotionPath = new MotionPaths();
    private MotionConstrainedPoint mEndPoint = new MotionConstrainedPoint();
    int mId;
    private double[] mInterpolateData;
    private int[] mInterpolateVariables;
    private double[] mInterpolateVelocity;
    private ArrayList<Key> mKeyList = new ArrayList<>();
    private KeyTrigger[] mKeyTriggers;
    private ArrayList<MotionPaths> mMotionPaths = new ArrayList<>();
    float mMotionStagger = Float.NaN;
    private int mPathMotionArc = Key.UNSET;
    private CurveFit[] mSpline;
    float mStaggerOffset = 0.0f;
    float mStaggerScale = 1.0f;
    private MotionPaths mStartMotionPath = new MotionPaths();
    private MotionConstrainedPoint mStartPoint = new MotionConstrainedPoint();
    private HashMap<String, TimeCycleSplineSet> mTimeCycleAttributesMap;
    private float[] mValuesBuff = new float[4];
    private float[] mVelocity = new float[1];
    View mView;

    /* access modifiers changed from: package-private */
    public MotionPaths getKeyFrame(int i) {
        return this.mMotionPaths.get(i);
    }

    MotionController(View view) {
        setView(view);
    }

    /* access modifiers changed from: package-private */
    public float getStartX() {
        return this.mStartMotionPath.x;
    }

    /* access modifiers changed from: package-private */
    public float getStartY() {
        return this.mStartMotionPath.y;
    }

    /* access modifiers changed from: package-private */
    public float getFinalX() {
        return this.mEndMotionPath.x;
    }

    /* access modifiers changed from: package-private */
    public float getFinalY() {
        return this.mEndMotionPath.y;
    }

    /* access modifiers changed from: package-private */
    public void buildPath(float[] points, int pointCount) {
        MotionController motionController = this;
        float[] fArr = points;
        int i = pointCount;
        float f = 1.0f;
        float mils = 1.0f / ((float) (i - 1));
        HashMap<String, SplineSet> hashMap = motionController.mAttributesMap;
        KeyCycleOscillator osc_y = null;
        SplineSet trans_x = hashMap == null ? null : hashMap.get("translationX");
        HashMap<String, SplineSet> hashMap2 = motionController.mAttributesMap;
        SplineSet trans_y = hashMap2 == null ? null : hashMap2.get("translationY");
        HashMap<String, KeyCycleOscillator> hashMap3 = motionController.mCycleMap;
        KeyCycleOscillator osc_x = hashMap3 == null ? null : hashMap3.get("translationX");
        HashMap<String, KeyCycleOscillator> hashMap4 = motionController.mCycleMap;
        if (hashMap4 != null) {
            osc_y = hashMap4.get("translationY");
        }
        int i2 = 0;
        while (i2 < i) {
            float position = ((float) i2) * mils;
            float f2 = motionController.mStaggerScale;
            if (f2 != f) {
                float f3 = motionController.mStaggerOffset;
                if (position < f3) {
                    position = 0.0f;
                }
                if (position > f3 && ((double) position) < 1.0d) {
                    position = (position - f3) * f2;
                }
            }
            double p = (double) position;
            Easing easing = motionController.mStartMotionPath.mKeyFrameEasing;
            float start = 0.0f;
            float end = Float.NaN;
            Iterator<MotionPaths> it = motionController.mMotionPaths.iterator();
            while (it.hasNext()) {
                MotionPaths frame = it.next();
                float mils2 = mils;
                if (frame.mKeyFrameEasing != null) {
                    if (frame.time < position) {
                        Easing easing2 = frame.mKeyFrameEasing;
                        start = frame.time;
                        easing = easing2;
                    } else if (Float.isNaN(end)) {
                        end = frame.time;
                    }
                }
                int i3 = pointCount;
                mils = mils2;
            }
            float mils3 = mils;
            if (easing != null) {
                if (Float.isNaN(end)) {
                    end = 1.0f;
                }
                p = (double) (((end - start) * ((float) easing.get((double) ((position - start) / (end - start))))) + start);
            }
            motionController.mSpline[0].getPos(p, motionController.mInterpolateData);
            CurveFit curveFit = motionController.mArcSpline;
            if (curveFit != null) {
                double[] dArr = motionController.mInterpolateData;
                if (dArr.length > 0) {
                    curveFit.getPos(p, dArr);
                }
            }
            motionController.mStartMotionPath.getCenter(motionController.mInterpolateVariables, motionController.mInterpolateData, fArr, i2 * 2);
            if (osc_x != null) {
                int i4 = i2 * 2;
                fArr[i4] = fArr[i4] + osc_x.get(position);
            } else if (trans_x != null) {
                int i5 = i2 * 2;
                fArr[i5] = fArr[i5] + trans_x.get(position);
            }
            if (osc_y != null) {
                int i6 = (i2 * 2) + 1;
                fArr[i6] = fArr[i6] + osc_y.get(position);
            } else if (trans_y != null) {
                int i7 = (i2 * 2) + 1;
                fArr[i7] = fArr[i7] + trans_y.get(position);
            }
            i2++;
            motionController = this;
            i = pointCount;
            mils = mils3;
            f = 1.0f;
        }
    }

    /* access modifiers changed from: package-private */
    public void buildBounds(float[] bounds, int pointCount) {
        float mils;
        MotionController motionController = this;
        int i = pointCount;
        float f = 1.0f;
        float mils2 = 1.0f / ((float) (i - 1));
        HashMap<String, SplineSet> hashMap = motionController.mAttributesMap;
        SplineSet trans_x = hashMap == null ? null : hashMap.get("translationX");
        HashMap<String, SplineSet> hashMap2 = motionController.mAttributesMap;
        if (hashMap2 != null) {
            SplineSet splineSet = hashMap2.get("translationY");
        }
        HashMap<String, KeyCycleOscillator> hashMap3 = motionController.mCycleMap;
        if (hashMap3 != null) {
            KeyCycleOscillator keyCycleOscillator = hashMap3.get("translationX");
        }
        HashMap<String, KeyCycleOscillator> hashMap4 = motionController.mCycleMap;
        if (hashMap4 != null) {
            KeyCycleOscillator keyCycleOscillator2 = hashMap4.get("translationY");
        }
        int i2 = 0;
        while (i2 < i) {
            float position = ((float) i2) * mils2;
            float f2 = motionController.mStaggerScale;
            if (f2 != f) {
                float f3 = motionController.mStaggerOffset;
                if (position < f3) {
                    position = 0.0f;
                }
                if (position > f3 && ((double) position) < 1.0d) {
                    position = (position - f3) * f2;
                }
            }
            double p = (double) position;
            Easing easing = motionController.mStartMotionPath.mKeyFrameEasing;
            float start = 0.0f;
            float end = Float.NaN;
            Iterator<MotionPaths> it = motionController.mMotionPaths.iterator();
            while (it.hasNext()) {
                MotionPaths frame = it.next();
                if (frame.mKeyFrameEasing != null) {
                    if (frame.time < position) {
                        Easing easing2 = frame.mKeyFrameEasing;
                        start = frame.time;
                        easing = easing2;
                    } else if (Float.isNaN(end)) {
                        end = frame.time;
                    }
                }
                int i3 = pointCount;
            }
            if (easing != null) {
                if (Float.isNaN(end)) {
                    end = 1.0f;
                }
                mils = mils2;
                p = (double) (((end - start) * ((float) easing.get((double) ((position - start) / (end - start))))) + start);
            } else {
                mils = mils2;
            }
            motionController.mSpline[0].getPos(p, motionController.mInterpolateData);
            CurveFit curveFit = motionController.mArcSpline;
            if (curveFit != null) {
                double[] dArr = motionController.mInterpolateData;
                if (dArr.length > 0) {
                    curveFit.getPos(p, dArr);
                }
            }
            motionController.mStartMotionPath.getBounds(motionController.mInterpolateVariables, motionController.mInterpolateData, bounds, i2 * 2);
            i2++;
            motionController = this;
            i = pointCount;
            mils2 = mils;
            trans_x = trans_x;
            f = 1.0f;
        }
    }

    private float getPreCycleDistance() {
        float position;
        MotionController motionController = this;
        int pointCount = 100;
        float[] points = new float[2];
        float sum = 0.0f;
        float mils = 1.0f / ((float) (100 - 1));
        double x = 0.0d;
        double y = 0.0d;
        int i = 0;
        while (i < pointCount) {
            float position2 = ((float) i) * mils;
            double p = (double) position2;
            Easing easing = motionController.mStartMotionPath.mKeyFrameEasing;
            float start = 0.0f;
            float end = Float.NaN;
            int pointCount2 = pointCount;
            Iterator<MotionPaths> it = motionController.mMotionPaths.iterator();
            while (it.hasNext()) {
                Iterator<MotionPaths> it2 = it;
                MotionPaths frame = it.next();
                float mils2 = mils;
                if (frame.mKeyFrameEasing != null) {
                    if (frame.time < position2) {
                        Easing easing2 = frame.mKeyFrameEasing;
                        start = frame.time;
                        easing = easing2;
                    } else if (Float.isNaN(end)) {
                        end = frame.time;
                    }
                }
                mils = mils2;
                it = it2;
            }
            float mils3 = mils;
            if (easing != null) {
                if (Float.isNaN(end)) {
                    end = 1.0f;
                }
                position = position2;
                double d = p;
                p = (double) (((end - start) * ((float) easing.get((double) ((position2 - start) / (end - start))))) + start);
            } else {
                position = position2;
                double d2 = p;
            }
            motionController.mSpline[0].getPos(p, motionController.mInterpolateData);
            float f = position;
            motionController.mStartMotionPath.getCenter(motionController.mInterpolateVariables, motionController.mInterpolateData, points, 0);
            if (i > 0) {
                double d3 = p;
                Easing easing3 = easing;
                sum = (float) (((double) sum) + Math.hypot(y - ((double) points[1]), x - ((double) points[0])));
            } else {
                Easing easing4 = easing;
            }
            x = (double) points[0];
            y = (double) points[1];
            i++;
            motionController = this;
            pointCount = pointCount2;
            mils = mils3;
        }
        return sum;
    }

    /* access modifiers changed from: package-private */
    public KeyPositionBase getPositionKeyframe(int layoutWidth, int layoutHeight, float x, float y) {
        RectF start = new RectF();
        start.left = this.mStartMotionPath.x;
        start.top = this.mStartMotionPath.y;
        start.right = start.left + this.mStartMotionPath.width;
        start.bottom = start.top + this.mStartMotionPath.height;
        RectF end = new RectF();
        end.left = this.mEndMotionPath.x;
        end.top = this.mEndMotionPath.y;
        end.right = end.left + this.mEndMotionPath.width;
        end.bottom = end.top + this.mEndMotionPath.height;
        Iterator<Key> it = this.mKeyList.iterator();
        while (it.hasNext()) {
            Key key = it.next();
            if ((key instanceof KeyPositionBase) && ((KeyPositionBase) key).intersects(layoutWidth, layoutHeight, start, end, x, y)) {
                return (KeyPositionBase) key;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public int buildKeyFrames(float[] keyFrames, int[] mode) {
        if (keyFrames == null) {
            return 0;
        }
        int count = 0;
        double[] time = this.mSpline[0].getTimePoints();
        if (mode != null) {
            Iterator<MotionPaths> it = this.mMotionPaths.iterator();
            while (it.hasNext()) {
                mode[count] = it.next().mMode;
                count++;
            }
            count = 0;
        }
        for (double pos : time) {
            this.mSpline[0].getPos(pos, this.mInterpolateData);
            this.mStartMotionPath.getCenter(this.mInterpolateVariables, this.mInterpolateData, keyFrames, count);
            count += 2;
        }
        return count / 2;
    }

    /* access modifiers changed from: package-private */
    public int buildKeyBounds(float[] keyBounds, int[] mode) {
        if (keyBounds == null) {
            return 0;
        }
        int count = 0;
        double[] time = this.mSpline[0].getTimePoints();
        if (mode != null) {
            Iterator<MotionPaths> it = this.mMotionPaths.iterator();
            while (it.hasNext()) {
                mode[count] = it.next().mMode;
                count++;
            }
            count = 0;
        }
        for (double pos : time) {
            this.mSpline[0].getPos(pos, this.mInterpolateData);
            this.mStartMotionPath.getBounds(this.mInterpolateVariables, this.mInterpolateData, keyBounds, count);
            count += 2;
        }
        return count / 2;
    }

    /* access modifiers changed from: package-private */
    public int getAttributeValues(String attributeType, float[] points, int pointCount) {
        float f = 1.0f / ((float) (pointCount - 1));
        SplineSet spline = this.mAttributesMap.get(attributeType);
        if (spline == null) {
            return -1;
        }
        for (int j = 0; j < points.length; j++) {
            points[j] = spline.get((float) (j / (points.length - 1)));
        }
        return points.length;
    }

    /* access modifiers changed from: package-private */
    public void buildRect(float p, float[] path, int offset) {
        this.mSpline[0].getPos((double) getAdjustedPosition(p, (float[]) null), this.mInterpolateData);
        this.mStartMotionPath.getRect(this.mInterpolateVariables, this.mInterpolateData, path, offset);
    }

    /* access modifiers changed from: package-private */
    public void buildRectangles(float[] path, int pointCount) {
        float mils = 1.0f / ((float) (pointCount - 1));
        for (int i = 0; i < pointCount; i++) {
            this.mSpline[0].getPos((double) getAdjustedPosition(((float) i) * mils, (float[]) null), this.mInterpolateData);
            this.mStartMotionPath.getRect(this.mInterpolateVariables, this.mInterpolateData, path, i * 8);
        }
    }

    /* access modifiers changed from: package-private */
    public float getKeyFrameParameter(int type, float x, float y) {
        float dx = this.mEndMotionPath.x - this.mStartMotionPath.x;
        float dy = this.mEndMotionPath.y - this.mStartMotionPath.y;
        float startCenterX = this.mStartMotionPath.x + (this.mStartMotionPath.width / 2.0f);
        float startCenterY = this.mStartMotionPath.y + (this.mStartMotionPath.height / 2.0f);
        float hypot = (float) Math.hypot((double) dx, (double) dy);
        if (((double) hypot) < 1.0E-7d) {
            return Float.NaN;
        }
        float vx = x - startCenterX;
        float vy = y - startCenterY;
        if (((float) Math.hypot((double) vx, (double) vy)) == 0.0f) {
            return 0.0f;
        }
        float pathDistance = (vx * dx) + (vy * dy);
        switch (type) {
            case 0:
                return pathDistance / hypot;
            case 1:
                return (float) Math.sqrt((double) ((hypot * hypot) - (pathDistance * pathDistance)));
            case 2:
                return vx / dx;
            case 3:
                return vy / dx;
            case 4:
                return vx / dy;
            case 5:
                return vy / dy;
            default:
                return 0.0f;
        }
    }

    private void insertKey(MotionPaths point) {
        int pos = Collections.binarySearch(this.mMotionPaths, point);
        if (pos == 0) {
            Log.e(TAG, " KeyPath positon \"" + point.position + "\" outside of range");
        }
        this.mMotionPaths.add((-pos) - 1, point);
    }

    /* access modifiers changed from: package-private */
    public void addKeys(ArrayList<Key> list) {
        this.mKeyList.addAll(list);
    }

    /* access modifiers changed from: package-private */
    public void addKey(Key key) {
        this.mKeyList.add(key);
    }

    public void setPathMotionArc(int arc) {
        this.mPathMotionArc = arc;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v31, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v34, resolved type: double[][]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setup(int r30, int r31, float r32, long r33) {
        /*
            r29 = this;
            r0 = r29
            java.lang.Class<double> r1 = double.class
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            java.util.HashSet r4 = new java.util.HashSet
            r4.<init>()
            java.util.HashSet r5 = new java.util.HashSet
            r5.<init>()
            java.util.HashMap r6 = new java.util.HashMap
            r6.<init>()
            r7 = 0
            int r8 = r0.mPathMotionArc
            int r9 = android.support.constraint.motion.Key.UNSET
            if (r8 == r9) goto L_0x002a
            android.support.constraint.motion.MotionPaths r8 = r0.mStartMotionPath
            int r9 = r0.mPathMotionArc
            r8.mPathMotionArc = r9
        L_0x002a:
            android.support.constraint.motion.MotionConstrainedPoint r8 = r0.mStartPoint
            android.support.constraint.motion.MotionConstrainedPoint r9 = r0.mEndPoint
            r8.different(r9, r4)
            java.util.ArrayList<android.support.constraint.motion.Key> r8 = r0.mKeyList
            if (r8 == 0) goto L_0x00a3
            java.util.Iterator r8 = r8.iterator()
        L_0x0039:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x00a0
            java.lang.Object r9 = r8.next()
            android.support.constraint.motion.Key r9 = (android.support.constraint.motion.Key) r9
            boolean r10 = r9 instanceof android.support.constraint.motion.KeyPosition
            if (r10 == 0) goto L_0x0072
            r10 = r9
            android.support.constraint.motion.KeyPosition r10 = (android.support.constraint.motion.KeyPosition) r10
            android.support.constraint.motion.MotionPaths r15 = new android.support.constraint.motion.MotionPaths
            android.support.constraint.motion.MotionPaths r14 = r0.mStartMotionPath
            android.support.constraint.motion.MotionPaths r13 = r0.mEndMotionPath
            r11 = r15
            r12 = r30
            r16 = r13
            r13 = r31
            r17 = r14
            r14 = r10
            r18 = r2
            r2 = r15
            r15 = r17
            r11.<init>(r12, r13, r14, r15, r16)
            r0.insertKey(r2)
            int r2 = r10.mCurveFit
            int r11 = android.support.constraint.motion.Key.UNSET
            if (r2 == r11) goto L_0x0071
            int r2 = r10.mCurveFit
            r0.mCurveFitType = r2
        L_0x0071:
            goto L_0x009d
        L_0x0072:
            r18 = r2
            boolean r2 = r9 instanceof android.support.constraint.motion.KeyCycle
            if (r2 == 0) goto L_0x007c
            r9.getAttributeNames(r5)
            goto L_0x009d
        L_0x007c:
            boolean r2 = r9 instanceof android.support.constraint.motion.KeyTimeCycle
            if (r2 == 0) goto L_0x0084
            r9.getAttributeNames(r3)
            goto L_0x009d
        L_0x0084:
            boolean r2 = r9 instanceof android.support.constraint.motion.KeyTrigger
            if (r2 == 0) goto L_0x0097
            if (r7 != 0) goto L_0x0090
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r7 = r2
        L_0x0090:
            r2 = r9
            android.support.constraint.motion.KeyTrigger r2 = (android.support.constraint.motion.KeyTrigger) r2
            r7.add(r2)
            goto L_0x009d
        L_0x0097:
            r9.setInterpolation(r6)
            r9.getAttributeNames(r4)
        L_0x009d:
            r2 = r18
            goto L_0x0039
        L_0x00a0:
            r18 = r2
            goto L_0x00a5
        L_0x00a3:
            r18 = r2
        L_0x00a5:
            r2 = 0
            if (r7 == 0) goto L_0x00b2
            android.support.constraint.motion.KeyTrigger[] r8 = new android.support.constraint.motion.KeyTrigger[r2]
            java.lang.Object[] r8 = r7.toArray(r8)
            android.support.constraint.motion.KeyTrigger[] r8 = (android.support.constraint.motion.KeyTrigger[]) r8
            r0.mKeyTriggers = r8
        L_0x00b2:
            boolean r8 = r4.isEmpty()
            java.lang.String r9 = ","
            java.lang.String r10 = "CUSTOM,"
            r11 = 1
            if (r8 != 0) goto L_0x019e
            java.util.HashMap r8 = new java.util.HashMap
            r8.<init>()
            r0.mAttributesMap = r8
            java.util.Iterator r8 = r4.iterator()
        L_0x00c8:
            boolean r12 = r8.hasNext()
            if (r12 == 0) goto L_0x013a
            java.lang.Object r12 = r8.next()
            java.lang.String r12 = (java.lang.String) r12
            boolean r13 = r12.startsWith(r10)
            if (r13 == 0) goto L_0x0120
            android.util.SparseArray r13 = new android.util.SparseArray
            r13.<init>()
            java.lang.String[] r14 = r12.split(r9)
            r14 = r14[r11]
            java.util.ArrayList<android.support.constraint.motion.Key> r15 = r0.mKeyList
            java.util.Iterator r15 = r15.iterator()
        L_0x00eb:
            boolean r16 = r15.hasNext()
            if (r16 == 0) goto L_0x0119
            java.lang.Object r16 = r15.next()
            r11 = r16
            android.support.constraint.motion.Key r11 = (android.support.constraint.motion.Key) r11
            java.util.HashMap<java.lang.String, android.support.constraint.ConstraintAttribute> r2 = r11.mCustomConstraints
            if (r2 != 0) goto L_0x0100
            r2 = 0
            r11 = 1
            goto L_0x00eb
        L_0x0100:
            java.util.HashMap<java.lang.String, android.support.constraint.ConstraintAttribute> r2 = r11.mCustomConstraints
            java.lang.Object r2 = r2.get(r14)
            android.support.constraint.ConstraintAttribute r2 = (android.support.constraint.ConstraintAttribute) r2
            if (r2 == 0) goto L_0x0112
            r19 = r7
            int r7 = r11.mFramePosition
            r13.append(r7, r2)
            goto L_0x0114
        L_0x0112:
            r19 = r7
        L_0x0114:
            r7 = r19
            r2 = 0
            r11 = 1
            goto L_0x00eb
        L_0x0119:
            r19 = r7
            android.support.constraint.motion.SplineSet r2 = android.support.constraint.motion.SplineSet.makeCustomSpline(r12, r13)
            goto L_0x0126
        L_0x0120:
            r19 = r7
            android.support.constraint.motion.SplineSet r2 = android.support.constraint.motion.SplineSet.makeSpline(r12)
        L_0x0126:
            if (r2 != 0) goto L_0x012d
            r7 = r19
            r2 = 0
            r11 = 1
            goto L_0x00c8
        L_0x012d:
            r2.setType(r12)
            java.util.HashMap<java.lang.String, android.support.constraint.motion.SplineSet> r7 = r0.mAttributesMap
            r7.put(r12, r2)
            r7 = r19
            r2 = 0
            r11 = 1
            goto L_0x00c8
        L_0x013a:
            r19 = r7
            java.util.ArrayList<android.support.constraint.motion.Key> r2 = r0.mKeyList
            if (r2 == 0) goto L_0x015a
            java.util.Iterator r2 = r2.iterator()
        L_0x0144:
            boolean r7 = r2.hasNext()
            if (r7 == 0) goto L_0x015a
            java.lang.Object r7 = r2.next()
            android.support.constraint.motion.Key r7 = (android.support.constraint.motion.Key) r7
            boolean r8 = r7 instanceof android.support.constraint.motion.KeyAttributes
            if (r8 == 0) goto L_0x0159
            java.util.HashMap<java.lang.String, android.support.constraint.motion.SplineSet> r8 = r0.mAttributesMap
            r7.addValues(r8)
        L_0x0159:
            goto L_0x0144
        L_0x015a:
            android.support.constraint.motion.MotionConstrainedPoint r2 = r0.mStartPoint
            java.util.HashMap<java.lang.String, android.support.constraint.motion.SplineSet> r7 = r0.mAttributesMap
            r8 = 0
            r2.addValues(r7, r8)
            android.support.constraint.motion.MotionConstrainedPoint r2 = r0.mEndPoint
            java.util.HashMap<java.lang.String, android.support.constraint.motion.SplineSet> r7 = r0.mAttributesMap
            r8 = 100
            r2.addValues(r7, r8)
            java.util.HashMap<java.lang.String, android.support.constraint.motion.SplineSet> r2 = r0.mAttributesMap
            java.util.Set r2 = r2.keySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x0175:
            boolean r7 = r2.hasNext()
            if (r7 == 0) goto L_0x01a0
            java.lang.Object r7 = r2.next()
            java.lang.String r7 = (java.lang.String) r7
            r8 = 0
            boolean r11 = r6.containsKey(r7)
            if (r11 == 0) goto L_0x0192
            java.lang.Object r11 = r6.get(r7)
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r8 = r11.intValue()
        L_0x0192:
            java.util.HashMap<java.lang.String, android.support.constraint.motion.SplineSet> r11 = r0.mAttributesMap
            java.lang.Object r11 = r11.get(r7)
            android.support.constraint.motion.SplineSet r11 = (android.support.constraint.motion.SplineSet) r11
            r11.setup(r8)
            goto L_0x0175
        L_0x019e:
            r19 = r7
        L_0x01a0:
            boolean r2 = r3.isEmpty()
            if (r2 != 0) goto L_0x0282
            java.util.HashMap<java.lang.String, android.support.constraint.motion.TimeCycleSplineSet> r2 = r0.mTimeCycleAttributesMap
            if (r2 != 0) goto L_0x01b1
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            r0.mTimeCycleAttributesMap = r2
        L_0x01b1:
            java.util.Iterator r2 = r3.iterator()
        L_0x01b5:
            boolean r7 = r2.hasNext()
            if (r7 == 0) goto L_0x022c
            java.lang.Object r7 = r2.next()
            java.lang.String r7 = (java.lang.String) r7
            java.util.HashMap<java.lang.String, android.support.constraint.motion.TimeCycleSplineSet> r8 = r0.mTimeCycleAttributesMap
            boolean r8 = r8.containsKey(r7)
            if (r8 == 0) goto L_0x01ca
            goto L_0x01b5
        L_0x01ca:
            r8 = 0
            boolean r11 = r7.startsWith(r10)
            if (r11 == 0) goto L_0x0214
            android.util.SparseArray r11 = new android.util.SparseArray
            r11.<init>()
            java.lang.String[] r12 = r7.split(r9)
            r13 = 1
            r12 = r12[r13]
            java.util.ArrayList<android.support.constraint.motion.Key> r13 = r0.mKeyList
            java.util.Iterator r13 = r13.iterator()
        L_0x01e3:
            boolean r14 = r13.hasNext()
            if (r14 == 0) goto L_0x020b
            java.lang.Object r14 = r13.next()
            android.support.constraint.motion.Key r14 = (android.support.constraint.motion.Key) r14
            java.util.HashMap<java.lang.String, android.support.constraint.ConstraintAttribute> r15 = r14.mCustomConstraints
            if (r15 != 0) goto L_0x01f4
            goto L_0x01e3
        L_0x01f4:
            java.util.HashMap<java.lang.String, android.support.constraint.ConstraintAttribute> r15 = r14.mCustomConstraints
            java.lang.Object r15 = r15.get(r12)
            android.support.constraint.ConstraintAttribute r15 = (android.support.constraint.ConstraintAttribute) r15
            if (r15 == 0) goto L_0x0206
            r20 = r2
            int r2 = r14.mFramePosition
            r11.append(r2, r15)
            goto L_0x0208
        L_0x0206:
            r20 = r2
        L_0x0208:
            r2 = r20
            goto L_0x01e3
        L_0x020b:
            r20 = r2
            android.support.constraint.motion.TimeCycleSplineSet r2 = android.support.constraint.motion.TimeCycleSplineSet.makeCustomSpline(r7, r11)
            r11 = r33
            goto L_0x021c
        L_0x0214:
            r20 = r2
            r11 = r33
            android.support.constraint.motion.TimeCycleSplineSet r2 = android.support.constraint.motion.TimeCycleSplineSet.makeSpline(r7, r11)
        L_0x021c:
            if (r2 != 0) goto L_0x0221
            r2 = r20
            goto L_0x01b5
        L_0x0221:
            r2.setType(r7)
            java.util.HashMap<java.lang.String, android.support.constraint.motion.TimeCycleSplineSet> r8 = r0.mTimeCycleAttributesMap
            r8.put(r7, r2)
            r2 = r20
            goto L_0x01b5
        L_0x022c:
            r11 = r33
            java.util.ArrayList<android.support.constraint.motion.Key> r2 = r0.mKeyList
            if (r2 == 0) goto L_0x024f
            java.util.Iterator r2 = r2.iterator()
        L_0x0236:
            boolean r7 = r2.hasNext()
            if (r7 == 0) goto L_0x024f
            java.lang.Object r7 = r2.next()
            android.support.constraint.motion.Key r7 = (android.support.constraint.motion.Key) r7
            boolean r8 = r7 instanceof android.support.constraint.motion.KeyTimeCycle
            if (r8 == 0) goto L_0x024e
            r8 = r7
            android.support.constraint.motion.KeyTimeCycle r8 = (android.support.constraint.motion.KeyTimeCycle) r8
            java.util.HashMap<java.lang.String, android.support.constraint.motion.TimeCycleSplineSet> r9 = r0.mTimeCycleAttributesMap
            r8.addTimeValues(r9)
        L_0x024e:
            goto L_0x0236
        L_0x024f:
            java.util.HashMap<java.lang.String, android.support.constraint.motion.TimeCycleSplineSet> r2 = r0.mTimeCycleAttributesMap
            java.util.Set r2 = r2.keySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x0259:
            boolean r7 = r2.hasNext()
            if (r7 == 0) goto L_0x0284
            java.lang.Object r7 = r2.next()
            java.lang.String r7 = (java.lang.String) r7
            r8 = 0
            boolean r9 = r6.containsKey(r7)
            if (r9 == 0) goto L_0x0276
            java.lang.Object r9 = r6.get(r7)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r8 = r9.intValue()
        L_0x0276:
            java.util.HashMap<java.lang.String, android.support.constraint.motion.TimeCycleSplineSet> r9 = r0.mTimeCycleAttributesMap
            java.lang.Object r9 = r9.get(r7)
            android.support.constraint.motion.TimeCycleSplineSet r9 = (android.support.constraint.motion.TimeCycleSplineSet) r9
            r9.setup(r8)
            goto L_0x0259
        L_0x0282:
            r11 = r33
        L_0x0284:
            java.util.ArrayList<android.support.constraint.motion.MotionPaths> r2 = r0.mMotionPaths
            int r2 = r2.size()
            r7 = 2
            int r2 = r2 + r7
            android.support.constraint.motion.MotionPaths[] r2 = new android.support.constraint.motion.MotionPaths[r2]
            r8 = 1
            android.support.constraint.motion.MotionPaths r9 = r0.mStartMotionPath
            r13 = 0
            r2[r13] = r9
            int r9 = r2.length
            r13 = 1
            int r9 = r9 - r13
            android.support.constraint.motion.MotionPaths r13 = r0.mEndMotionPath
            r2[r9] = r13
            java.util.ArrayList<android.support.constraint.motion.MotionPaths> r9 = r0.mMotionPaths
            int r9 = r9.size()
            if (r9 <= 0) goto L_0x02ab
            int r9 = r0.mCurveFitType
            r13 = -1
            if (r9 != r13) goto L_0x02ab
            r9 = 0
            r0.mCurveFitType = r9
        L_0x02ab:
            java.util.ArrayList<android.support.constraint.motion.MotionPaths> r9 = r0.mMotionPaths
            java.util.Iterator r9 = r9.iterator()
        L_0x02b1:
            boolean r13 = r9.hasNext()
            if (r13 == 0) goto L_0x02c3
            java.lang.Object r13 = r9.next()
            android.support.constraint.motion.MotionPaths r13 = (android.support.constraint.motion.MotionPaths) r13
            int r14 = r8 + 1
            r2[r8] = r13
            r8 = r14
            goto L_0x02b1
        L_0x02c3:
            r9 = 18
            java.util.HashSet r13 = new java.util.HashSet
            r13.<init>()
            android.support.constraint.motion.MotionPaths r14 = r0.mEndMotionPath
            java.util.LinkedHashMap<java.lang.String, android.support.constraint.ConstraintAttribute> r14 = r14.attributes
            java.util.Set r14 = r14.keySet()
            java.util.Iterator r14 = r14.iterator()
        L_0x02d6:
            boolean r15 = r14.hasNext()
            if (r15 == 0) goto L_0x0308
            java.lang.Object r15 = r14.next()
            java.lang.String r15 = (java.lang.String) r15
            android.support.constraint.motion.MotionPaths r7 = r0.mStartMotionPath
            java.util.LinkedHashMap<java.lang.String, android.support.constraint.ConstraintAttribute> r7 = r7.attributes
            boolean r7 = r7.containsKey(r15)
            if (r7 == 0) goto L_0x0306
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.StringBuilder r7 = r7.append(r10)
            java.lang.StringBuilder r7 = r7.append(r15)
            java.lang.String r7 = r7.toString()
            boolean r7 = r4.contains(r7)
            if (r7 != 0) goto L_0x0306
            r13.add(r15)
        L_0x0306:
            r7 = 2
            goto L_0x02d6
        L_0x0308:
            r7 = 0
            java.lang.String[] r10 = new java.lang.String[r7]
            java.lang.Object[] r7 = r13.toArray(r10)
            java.lang.String[] r7 = (java.lang.String[]) r7
            r0.mAttributeNames = r7
            int r7 = r7.length
            int[] r7 = new int[r7]
            r0.mAttributeInterpCount = r7
            r7 = 0
        L_0x0319:
            java.lang.String[] r10 = r0.mAttributeNames
            int r14 = r10.length
            if (r7 >= r14) goto L_0x0358
            r10 = r10[r7]
            int[] r14 = r0.mAttributeInterpCount
            r15 = 0
            r14[r7] = r15
            r14 = 0
        L_0x0326:
            int r15 = r2.length
            if (r14 >= r15) goto L_0x0351
            r15 = r2[r14]
            java.util.LinkedHashMap<java.lang.String, android.support.constraint.ConstraintAttribute> r15 = r15.attributes
            boolean r15 = r15.containsKey(r10)
            if (r15 == 0) goto L_0x034c
            int[] r15 = r0.mAttributeInterpCount
            r21 = r15[r7]
            r22 = r3
            r3 = r2[r14]
            java.util.LinkedHashMap<java.lang.String, android.support.constraint.ConstraintAttribute> r3 = r3.attributes
            java.lang.Object r3 = r3.get(r10)
            android.support.constraint.ConstraintAttribute r3 = (android.support.constraint.ConstraintAttribute) r3
            int r3 = r3.noOfInterpValues()
            int r21 = r21 + r3
            r15[r7] = r21
            goto L_0x0353
        L_0x034c:
            r22 = r3
            int r14 = r14 + 1
            goto L_0x0326
        L_0x0351:
            r22 = r3
        L_0x0353:
            int r7 = r7 + 1
            r3 = r22
            goto L_0x0319
        L_0x0358:
            r22 = r3
            r3 = 0
            r7 = r2[r3]
            int r3 = r7.mPathMotionArc
            int r7 = android.support.constraint.motion.Key.UNSET
            if (r3 == r7) goto L_0x0365
            r3 = 1
            goto L_0x0366
        L_0x0365:
            r3 = 0
        L_0x0366:
            java.lang.String[] r7 = r0.mAttributeNames
            int r7 = r7.length
            int r7 = r7 + r9
            boolean[] r7 = new boolean[r7]
            r10 = 1
        L_0x036d:
            int r14 = r2.length
            if (r10 >= r14) goto L_0x0382
            r14 = r2[r10]
            int r15 = r10 + -1
            r15 = r2[r15]
            r21 = r4
            java.lang.String[] r4 = r0.mAttributeNames
            r14.different(r15, r7, r4, r3)
            int r10 = r10 + 1
            r4 = r21
            goto L_0x036d
        L_0x0382:
            r21 = r4
            r4 = 0
            r8 = 1
        L_0x0386:
            int r10 = r7.length
            if (r8 >= r10) goto L_0x0392
            boolean r10 = r7[r8]
            if (r10 == 0) goto L_0x038f
            int r4 = r4 + 1
        L_0x038f:
            int r8 = r8 + 1
            goto L_0x0386
        L_0x0392:
            int[] r8 = new int[r4]
            r0.mInterpolateVariables = r8
            int r10 = r8.length
            double[] r10 = new double[r10]
            r0.mInterpolateData = r10
            int r8 = r8.length
            double[] r8 = new double[r8]
            r0.mInterpolateVelocity = r8
            r4 = 0
            r8 = 1
        L_0x03a2:
            int r10 = r7.length
            if (r8 >= r10) goto L_0x03b3
            boolean r10 = r7[r8]
            if (r10 == 0) goto L_0x03b0
            int[] r10 = r0.mInterpolateVariables
            int r14 = r4 + 1
            r10[r4] = r8
            r4 = r14
        L_0x03b0:
            int r8 = r8 + 1
            goto L_0x03a2
        L_0x03b3:
            int r8 = r2.length
            int[] r10 = r0.mInterpolateVariables
            int r10 = r10.length
            r14 = 2
            int[] r15 = new int[r14]
            r14 = 1
            r15[r14] = r10
            r10 = 0
            r15[r10] = r8
            java.lang.Object r8 = java.lang.reflect.Array.newInstance(r1, r15)
            double[][] r8 = (double[][]) r8
            int r10 = r2.length
            double[] r10 = new double[r10]
            r14 = 0
        L_0x03ca:
            int r15 = r2.length
            if (r14 >= r15) goto L_0x03e8
            r15 = r2[r14]
            r23 = r3
            r3 = r8[r14]
            r24 = r4
            int[] r4 = r0.mInterpolateVariables
            r15.fillStandard(r3, r4)
            r3 = r2[r14]
            float r3 = r3.time
            double r3 = (double) r3
            r10[r14] = r3
            int r14 = r14 + 1
            r3 = r23
            r4 = r24
            goto L_0x03ca
        L_0x03e8:
            r23 = r3
            r24 = r4
            r3 = 0
        L_0x03ed:
            int[] r4 = r0.mInterpolateVariables
            int r14 = r4.length
            if (r3 >= r14) goto L_0x044c
            r4 = r4[r3]
            java.lang.String[] r14 = android.support.constraint.motion.MotionPaths.names
            int r14 = r14.length
            if (r4 >= r14) goto L_0x043f
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String[] r15 = android.support.constraint.motion.MotionPaths.names
            r25 = r4
            int[] r4 = r0.mInterpolateVariables
            r4 = r4[r3]
            r4 = r15[r4]
            java.lang.StringBuilder r4 = r14.append(r4)
            java.lang.String r14 = " ["
            java.lang.StringBuilder r4 = r4.append(r14)
            java.lang.String r4 = r4.toString()
            r14 = 0
        L_0x0417:
            int r15 = r2.length
            if (r14 >= r15) goto L_0x043a
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            java.lang.StringBuilder r15 = r15.append(r4)
            r26 = r8[r14]
            r27 = r6
            r28 = r7
            r6 = r26[r3]
            java.lang.StringBuilder r6 = r15.append(r6)
            java.lang.String r4 = r6.toString()
            int r14 = r14 + 1
            r6 = r27
            r7 = r28
            goto L_0x0417
        L_0x043a:
            r27 = r6
            r28 = r7
            goto L_0x0445
        L_0x043f:
            r25 = r4
            r27 = r6
            r28 = r7
        L_0x0445:
            int r3 = r3 + 1
            r6 = r27
            r7 = r28
            goto L_0x03ed
        L_0x044c:
            r27 = r6
            r28 = r7
            java.lang.String[] r3 = r0.mAttributeNames
            int r3 = r3.length
            r4 = 1
            int r3 = r3 + r4
            android.support.constraint.motion.utils.CurveFit[] r3 = new android.support.constraint.motion.utils.CurveFit[r3]
            r0.mSpline = r3
            r3 = 0
        L_0x045a:
            java.lang.String[] r4 = r0.mAttributeNames
            int r6 = r4.length
            if (r3 >= r6) goto L_0x04d4
            r6 = 0
            r7 = 0
            double[][] r7 = (double[][]) r7
            r14 = 0
            r4 = r4[r3]
            r15 = 0
        L_0x0467:
            r25 = r9
            int r9 = r2.length
            if (r15 >= r9) goto L_0x04b2
            r9 = r2[r15]
            boolean r9 = r9.hasCustomData(r4)
            if (r9 == 0) goto L_0x04a7
            if (r7 != 0) goto L_0x0493
            int r9 = r2.length
            double[] r14 = new double[r9]
            int r9 = r2.length
            r11 = r2[r15]
            int r11 = r11.getCustomDataCount(r4)
            r26 = r13
            r12 = 2
            int[] r13 = new int[r12]
            r12 = 1
            r13[r12] = r11
            r11 = 0
            r13[r11] = r9
            java.lang.Object r9 = java.lang.reflect.Array.newInstance(r1, r13)
            r7 = r9
            double[][] r7 = (double[][]) r7
            goto L_0x0495
        L_0x0493:
            r26 = r13
        L_0x0495:
            r9 = r2[r15]
            float r9 = r9.time
            double r11 = (double) r9
            r14[r6] = r11
            r9 = r2[r15]
            r11 = r7[r6]
            r12 = 0
            r9.getCustomData(r4, r11, r12)
            int r6 = r6 + 1
            goto L_0x04a9
        L_0x04a7:
            r26 = r13
        L_0x04a9:
            int r15 = r15 + 1
            r11 = r33
            r9 = r25
            r13 = r26
            goto L_0x0467
        L_0x04b2:
            r26 = r13
            double[] r9 = java.util.Arrays.copyOf(r14, r6)
            java.lang.Object[] r11 = java.util.Arrays.copyOf(r7, r6)
            r7 = r11
            double[][] r7 = (double[][]) r7
            android.support.constraint.motion.utils.CurveFit[] r11 = r0.mSpline
            int r12 = r3 + 1
            int r13 = r0.mCurveFitType
            android.support.constraint.motion.utils.CurveFit r13 = android.support.constraint.motion.utils.CurveFit.get(r13, r9, r7)
            r11[r12] = r13
            int r3 = r3 + 1
            r11 = r33
            r9 = r25
            r13 = r26
            goto L_0x045a
        L_0x04d4:
            r25 = r9
            r26 = r13
            android.support.constraint.motion.utils.CurveFit[] r3 = r0.mSpline
            int r4 = r0.mCurveFitType
            android.support.constraint.motion.utils.CurveFit r4 = android.support.constraint.motion.utils.CurveFit.get(r4, r10, r8)
            r6 = 0
            r3[r6] = r4
            r3 = r2[r6]
            int r3 = r3.mPathMotionArc
            int r4 = android.support.constraint.motion.Key.UNSET
            if (r3 == r4) goto L_0x052c
            int r3 = r2.length
            int[] r4 = new int[r3]
            double[] r6 = new double[r3]
            r7 = 2
            int[] r9 = new int[r7]
            r11 = 1
            r9[r11] = r7
            r7 = 0
            r9[r7] = r3
            java.lang.Object r1 = java.lang.reflect.Array.newInstance(r1, r9)
            double[][] r1 = (double[][]) r1
            r7 = 0
        L_0x0500:
            if (r7 >= r3) goto L_0x0526
            r9 = r2[r7]
            int r9 = r9.mPathMotionArc
            r4[r7] = r9
            r9 = r2[r7]
            float r9 = r9.time
            double r11 = (double) r9
            r6[r7] = r11
            r9 = r1[r7]
            r11 = r2[r7]
            float r11 = r11.x
            double r11 = (double) r11
            r13 = 0
            r9[r13] = r11
            r9 = r1[r7]
            r11 = r2[r7]
            float r11 = r11.y
            double r11 = (double) r11
            r14 = 1
            r9[r14] = r11
            int r7 = r7 + 1
            goto L_0x0500
        L_0x0526:
            android.support.constraint.motion.utils.CurveFit r7 = android.support.constraint.motion.utils.CurveFit.getArc(r4, r6, r1)
            r0.mArcSpline = r7
        L_0x052c:
            r1 = 2143289344(0x7fc00000, float:NaN)
            java.util.HashMap r3 = new java.util.HashMap
            r3.<init>()
            r0.mCycleMap = r3
            java.util.ArrayList<android.support.constraint.motion.Key> r3 = r0.mKeyList
            if (r3 == 0) goto L_0x05a2
            java.util.Iterator r3 = r5.iterator()
        L_0x053d:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0569
            java.lang.Object r4 = r3.next()
            java.lang.String r4 = (java.lang.String) r4
            android.support.constraint.motion.KeyCycleOscillator r6 = android.support.constraint.motion.KeyCycleOscillator.makeSpline(r4)
            if (r6 != 0) goto L_0x0550
            goto L_0x053d
        L_0x0550:
            boolean r7 = r6.variesByPath()
            if (r7 == 0) goto L_0x0560
            boolean r7 = java.lang.Float.isNaN(r1)
            if (r7 == 0) goto L_0x0560
            float r1 = r29.getPreCycleDistance()
        L_0x0560:
            r6.setType(r4)
            java.util.HashMap<java.lang.String, android.support.constraint.motion.KeyCycleOscillator> r7 = r0.mCycleMap
            r7.put(r4, r6)
            goto L_0x053d
        L_0x0569:
            java.util.ArrayList<android.support.constraint.motion.Key> r3 = r0.mKeyList
            java.util.Iterator r3 = r3.iterator()
        L_0x056f:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0588
            java.lang.Object r4 = r3.next()
            android.support.constraint.motion.Key r4 = (android.support.constraint.motion.Key) r4
            boolean r6 = r4 instanceof android.support.constraint.motion.KeyCycle
            if (r6 == 0) goto L_0x0587
            r6 = r4
            android.support.constraint.motion.KeyCycle r6 = (android.support.constraint.motion.KeyCycle) r6
            java.util.HashMap<java.lang.String, android.support.constraint.motion.KeyCycleOscillator> r7 = r0.mCycleMap
            r6.addCycleValues(r7)
        L_0x0587:
            goto L_0x056f
        L_0x0588:
            java.util.HashMap<java.lang.String, android.support.constraint.motion.KeyCycleOscillator> r3 = r0.mCycleMap
            java.util.Collection r3 = r3.values()
            java.util.Iterator r3 = r3.iterator()
        L_0x0592:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x05a2
            java.lang.Object r4 = r3.next()
            android.support.constraint.motion.KeyCycleOscillator r4 = (android.support.constraint.motion.KeyCycleOscillator) r4
            r4.setup(r1)
            goto L_0x0592
        L_0x05a2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.motion.MotionController.setup(int, int, float, long):void");
    }

    public String toString() {
        return " start: x: " + this.mStartMotionPath.x + " y: " + this.mStartMotionPath.y + " end: x: " + this.mEndMotionPath.x + " y: " + this.mEndMotionPath.y;
    }

    private void readView(MotionPaths motionPaths) {
        motionPaths.setBounds((float) ((int) this.mView.getX()), (float) ((int) this.mView.getY()), (float) this.mView.getWidth(), (float) this.mView.getHeight());
    }

    public void setView(View view) {
        this.mView = view;
        this.mId = view.getId();
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp instanceof ConstraintLayout.LayoutParams) {
            this.mConstraintTag = ((ConstraintLayout.LayoutParams) lp).getConstraintTag();
        }
    }

    /* access modifiers changed from: package-private */
    public void setStartCurrentState(View v) {
        this.mStartMotionPath.time = 0.0f;
        this.mStartMotionPath.position = 0.0f;
        this.mStartMotionPath.setBounds(v.getX(), v.getY(), (float) v.getWidth(), (float) v.getHeight());
        this.mStartPoint.setState(v);
    }

    /* access modifiers changed from: package-private */
    public void setStartState(ConstraintWidget cw, ConstraintSet constraintSet) {
        this.mStartMotionPath.time = 0.0f;
        this.mStartMotionPath.position = 0.0f;
        readView(this.mStartMotionPath);
        this.mStartMotionPath.setBounds((float) cw.getX(), (float) cw.getY(), (float) cw.getWidth(), (float) cw.getHeight());
        ConstraintSet.Constraint constraint = constraintSet.getParameters(this.mId);
        this.mStartMotionPath.applyParameters(constraint);
        this.mMotionStagger = constraint.motion.mMotionStagger;
        this.mStartPoint.setState(cw, constraintSet, this.mId);
    }

    /* access modifiers changed from: package-private */
    public void setEndState(ConstraintWidget cw, ConstraintSet constraintSet) {
        this.mEndMotionPath.time = 1.0f;
        this.mEndMotionPath.position = 1.0f;
        readView(this.mEndMotionPath);
        this.mEndMotionPath.setBounds((float) cw.getX(), (float) cw.getY(), (float) cw.getWidth(), (float) cw.getHeight());
        this.mEndMotionPath.applyParameters(constraintSet.getParameters(this.mId));
        this.mEndPoint.setState(cw, constraintSet, this.mId);
    }

    private float getAdjustedPosition(float position, float[] velocity) {
        if (velocity != null) {
            velocity[0] = 1.0f;
        } else {
            float f = this.mStaggerScale;
            if (((double) f) != 1.0d) {
                float f2 = this.mStaggerOffset;
                if (position < f2) {
                    position = 0.0f;
                }
                if (position > f2 && ((double) position) < 1.0d) {
                    position = (position - f2) * f;
                }
            }
        }
        float adjusted = position;
        Easing easing = this.mStartMotionPath.mKeyFrameEasing;
        float start = 0.0f;
        float end = Float.NaN;
        Iterator<MotionPaths> it = this.mMotionPaths.iterator();
        while (it.hasNext()) {
            MotionPaths frame = it.next();
            if (frame.mKeyFrameEasing != null) {
                if (frame.time < position) {
                    easing = frame.mKeyFrameEasing;
                    start = frame.time;
                } else if (Float.isNaN(end)) {
                    end = frame.time;
                }
            }
        }
        if (easing != null) {
            if (Float.isNaN(end)) {
                end = 1.0f;
            }
            float offset = (position - start) / (end - start);
            adjusted = ((end - start) * ((float) easing.get((double) offset))) + start;
            if (velocity != null) {
                velocity[0] = (float) easing.getDiff((double) offset);
            }
        }
        return adjusted;
    }

    /* access modifiers changed from: package-private */
    public boolean interpolate(View child, float global_position, long time, KeyCache keyCache) {
        TimeCycleSplineSet.PathRotate timePathRotate;
        boolean timeAnimation;
        int i;
        View view = child;
        float position = getAdjustedPosition(global_position, (float[]) null);
        HashMap<String, SplineSet> hashMap = this.mAttributesMap;
        if (hashMap != null) {
            for (SplineSet aSpline : hashMap.values()) {
                aSpline.setProperty(view, position);
            }
        }
        HashMap<String, TimeCycleSplineSet> hashMap2 = this.mTimeCycleAttributesMap;
        if (hashMap2 != null) {
            boolean timeAnimation2 = false;
            TimeCycleSplineSet.PathRotate timePathRotate2 = null;
            for (TimeCycleSplineSet aSpline2 : hashMap2.values()) {
                if (aSpline2 instanceof TimeCycleSplineSet.PathRotate) {
                    timePathRotate2 = aSpline2;
                } else {
                    timeAnimation2 |= aSpline2.setProperty(child, position, time, keyCache);
                }
            }
            timeAnimation = timeAnimation2;
            timePathRotate = timePathRotate2;
        } else {
            timeAnimation = false;
            timePathRotate = null;
        }
        CurveFit[] curveFitArr = this.mSpline;
        if (curveFitArr != null) {
            curveFitArr[0].getPos((double) position, this.mInterpolateData);
            this.mSpline[0].getSlope((double) position, this.mInterpolateVelocity);
            CurveFit curveFit = this.mArcSpline;
            if (curveFit != null) {
                double[] dArr = this.mInterpolateData;
                if (dArr.length > 0) {
                    curveFit.getPos((double) position, dArr);
                    this.mArcSpline.getSlope((double) position, this.mInterpolateVelocity);
                }
            }
            this.mStartMotionPath.setView(child, this.mInterpolateVariables, this.mInterpolateData, this.mInterpolateVelocity, (double[]) null);
            HashMap<String, SplineSet> hashMap3 = this.mAttributesMap;
            if (hashMap3 != null) {
                for (SplineSet aSpline3 : hashMap3.values()) {
                    if (aSpline3 instanceof SplineSet.PathRotate) {
                        double[] dArr2 = this.mInterpolateVelocity;
                        ((SplineSet.PathRotate) aSpline3).setPathRotate(child, position, dArr2[0], dArr2[1]);
                    }
                }
            }
            if (timePathRotate != null) {
                double[] dArr3 = this.mInterpolateVelocity;
                i = 0;
                timeAnimation = timePathRotate.setPathRotate(child, keyCache, position, time, dArr3[0], dArr3[1]) | timeAnimation;
            } else {
                i = 0;
            }
            int i2 = 1;
            while (true) {
                CurveFit[] curveFitArr2 = this.mSpline;
                if (i2 >= curveFitArr2.length) {
                    break;
                }
                curveFitArr2[i2].getPos((double) position, this.mValuesBuff);
                this.mStartMotionPath.attributes.get(this.mAttributeNames[i2 - 1]).setInterpolatedValue(view, this.mValuesBuff);
                i2++;
            }
            if (this.mStartPoint.mVisibilityMode == 0) {
                if (position <= 0.0f) {
                    view.setVisibility(this.mStartPoint.visibility);
                } else if (position >= 1.0f) {
                    view.setVisibility(this.mEndPoint.visibility);
                } else if (this.mEndPoint.visibility != this.mStartPoint.visibility) {
                    view.setVisibility(i);
                }
            }
            if (this.mKeyTriggers != null) {
                int i3 = 0;
                while (true) {
                    KeyTrigger[] keyTriggerArr = this.mKeyTriggers;
                    if (i3 >= keyTriggerArr.length) {
                        break;
                    }
                    keyTriggerArr[i3].conditionallyFire(position, view);
                    i3++;
                }
            }
        } else {
            float float_l = this.mStartMotionPath.x + ((this.mEndMotionPath.x - this.mStartMotionPath.x) * position);
            float float_t = this.mStartMotionPath.y + ((this.mEndMotionPath.y - this.mStartMotionPath.y) * position);
            int l = (int) (float_l + 0.5f);
            int t = (int) (float_t + 0.5f);
            int r = (int) (float_l + 0.5f + this.mStartMotionPath.width + ((this.mEndMotionPath.width - this.mStartMotionPath.width) * position));
            int b = (int) (0.5f + float_t + this.mStartMotionPath.height + ((this.mEndMotionPath.height - this.mStartMotionPath.height) * position));
            int width = r - l;
            int height = b - t;
            float f = float_l;
            if (!(this.mEndMotionPath.width == this.mStartMotionPath.width && this.mEndMotionPath.height == this.mStartMotionPath.height)) {
                view.measure(View.MeasureSpec.makeMeasureSpec(width, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(height, BasicMeasure.EXACTLY));
            }
            view.layout(l, t, r, b);
        }
        HashMap<String, KeyCycleOscillator> hashMap4 = this.mCycleMap;
        if (hashMap4 != null) {
            for (KeyCycleOscillator osc : hashMap4.values()) {
                if (osc instanceof KeyCycleOscillator.PathRotateSet) {
                    double[] dArr4 = this.mInterpolateVelocity;
                    ((KeyCycleOscillator.PathRotateSet) osc).setPathRotate(child, position, dArr4[0], dArr4[1]);
                } else {
                    osc.setProperty(view, position);
                }
            }
        }
        return timeAnimation;
    }

    /* access modifiers changed from: package-private */
    public void getDpDt(float position, float locationX, float locationY, float[] mAnchorDpDt) {
        double[] dArr;
        float f = position;
        float position2 = getAdjustedPosition(position, this.mVelocity);
        CurveFit[] curveFitArr = this.mSpline;
        if (curveFitArr != null) {
            curveFitArr[0].getSlope((double) position2, this.mInterpolateVelocity);
            this.mSpline[0].getPos((double) position2, this.mInterpolateData);
            float v = this.mVelocity[0];
            int i = 0;
            while (true) {
                dArr = this.mInterpolateVelocity;
                if (i >= dArr.length) {
                    break;
                }
                dArr[i] = dArr[i] * ((double) v);
                i++;
            }
            CurveFit curveFit = this.mArcSpline;
            if (curveFit != null) {
                double[] dArr2 = this.mInterpolateData;
                if (dArr2.length > 0) {
                    curveFit.getPos((double) position2, dArr2);
                    this.mArcSpline.getSlope((double) position2, this.mInterpolateVelocity);
                    this.mStartMotionPath.setDpDt(locationX, locationY, mAnchorDpDt, this.mInterpolateVariables, this.mInterpolateVelocity, this.mInterpolateData);
                    return;
                }
                return;
            }
            this.mStartMotionPath.setDpDt(locationX, locationY, mAnchorDpDt, this.mInterpolateVariables, dArr, this.mInterpolateData);
            return;
        }
        float dleft = this.mEndMotionPath.x - this.mStartMotionPath.x;
        float dTop = this.mEndMotionPath.y - this.mStartMotionPath.y;
        float dWidth = this.mEndMotionPath.width - this.mStartMotionPath.width;
        float dHeight = this.mEndMotionPath.height - this.mStartMotionPath.height;
        mAnchorDpDt[0] = ((1.0f - locationX) * dleft) + ((dleft + dWidth) * locationX);
        mAnchorDpDt[1] = ((1.0f - locationY) * dTop) + ((dTop + dHeight) * locationY);
    }

    /* access modifiers changed from: package-private */
    public void getPostLayoutDvDp(float position, int width, int height, float locationX, float locationY, float[] mAnchorDpDt) {
        VelocityMatrix vmat;
        float position2 = getAdjustedPosition(position, this.mVelocity);
        HashMap<String, SplineSet> hashMap = this.mAttributesMap;
        KeyCycleOscillator keyCycleOscillator = null;
        SplineSet trans_x = hashMap == null ? null : hashMap.get("translationX");
        HashMap<String, SplineSet> hashMap2 = this.mAttributesMap;
        SplineSet trans_y = hashMap2 == null ? null : hashMap2.get("translationY");
        HashMap<String, SplineSet> hashMap3 = this.mAttributesMap;
        SplineSet rotation = hashMap3 == null ? null : hashMap3.get("rotation");
        HashMap<String, SplineSet> hashMap4 = this.mAttributesMap;
        SplineSet scale_x = hashMap4 == null ? null : hashMap4.get("scaleX");
        HashMap<String, SplineSet> hashMap5 = this.mAttributesMap;
        SplineSet scale_y = hashMap5 == null ? null : hashMap5.get("scaleY");
        HashMap<String, KeyCycleOscillator> hashMap6 = this.mCycleMap;
        KeyCycleOscillator osc_x = hashMap6 == null ? null : hashMap6.get("translationX");
        HashMap<String, KeyCycleOscillator> hashMap7 = this.mCycleMap;
        KeyCycleOscillator osc_y = hashMap7 == null ? null : hashMap7.get("translationY");
        HashMap<String, KeyCycleOscillator> hashMap8 = this.mCycleMap;
        KeyCycleOscillator osc_r = hashMap8 == null ? null : hashMap8.get("rotation");
        HashMap<String, KeyCycleOscillator> hashMap9 = this.mCycleMap;
        KeyCycleOscillator osc_sx = hashMap9 == null ? null : hashMap9.get("scaleX");
        HashMap<String, KeyCycleOscillator> hashMap10 = this.mCycleMap;
        if (hashMap10 != null) {
            keyCycleOscillator = hashMap10.get("scaleY");
        }
        KeyCycleOscillator osc_sy = keyCycleOscillator;
        VelocityMatrix vmat2 = new VelocityMatrix();
        vmat2.clear();
        vmat2.setRotationVelocity(rotation, position2);
        vmat2.setTranslationVelocity(trans_x, trans_y, position2);
        vmat2.setScaleVelocity(scale_x, scale_y, position2);
        vmat2.setRotationVelocity(osc_r, position2);
        vmat2.setTranslationVelocity(osc_x, osc_y, position2);
        vmat2.setScaleVelocity(osc_sx, osc_sy, position2);
        CurveFit curveFit = this.mArcSpline;
        if (curveFit != null) {
            double[] dArr = this.mInterpolateData;
            if (dArr.length > 0) {
                curveFit.getPos((double) position2, dArr);
                this.mArcSpline.getSlope((double) position2, this.mInterpolateVelocity);
                vmat = vmat2;
                KeyCycleOscillator keyCycleOscillator2 = osc_x;
                KeyCycleOscillator osc_x2 = osc_r;
                KeyCycleOscillator keyCycleOscillator3 = osc_sx;
                KeyCycleOscillator keyCycleOscillator4 = osc_sy;
                this.mStartMotionPath.setDpDt(locationX, locationY, mAnchorDpDt, this.mInterpolateVariables, this.mInterpolateVelocity, this.mInterpolateData);
            } else {
                vmat = vmat2;
                KeyCycleOscillator keyCycleOscillator5 = osc_sx;
                KeyCycleOscillator keyCycleOscillator6 = osc_sy;
                KeyCycleOscillator keyCycleOscillator7 = osc_x;
                KeyCycleOscillator osc_x3 = osc_r;
            }
            vmat.applyTransform(locationX, locationY, width, height, mAnchorDpDt);
            return;
        }
        VelocityMatrix vmat3 = vmat2;
        KeyCycleOscillator osc_sx2 = osc_sx;
        KeyCycleOscillator osc_sy2 = osc_sy;
        KeyCycleOscillator osc_x4 = osc_x;
        KeyCycleOscillator osc_r2 = osc_r;
        if (this.mSpline != null) {
            float position3 = getAdjustedPosition(position2, this.mVelocity);
            this.mSpline[0].getSlope((double) position3, this.mInterpolateVelocity);
            this.mSpline[0].getPos((double) position3, this.mInterpolateData);
            float v = this.mVelocity[0];
            int i = 0;
            while (true) {
                double[] dArr2 = this.mInterpolateVelocity;
                if (i < dArr2.length) {
                    dArr2[i] = dArr2[i] * ((double) v);
                    i++;
                } else {
                    float f = locationX;
                    float f2 = locationY;
                    float f3 = v;
                    this.mStartMotionPath.setDpDt(f, f2, mAnchorDpDt, this.mInterpolateVariables, dArr2, this.mInterpolateData);
                    vmat3.applyTransform(f, f2, width, height, mAnchorDpDt);
                    return;
                }
            }
        } else {
            float dleft = this.mEndMotionPath.x - this.mStartMotionPath.x;
            float dTop = this.mEndMotionPath.y - this.mStartMotionPath.y;
            mAnchorDpDt[0] = ((1.0f - locationX) * dleft) + ((dleft + (this.mEndMotionPath.width - this.mStartMotionPath.width)) * locationX);
            mAnchorDpDt[1] = ((1.0f - locationY) * dTop) + ((dTop + (this.mEndMotionPath.height - this.mStartMotionPath.height)) * locationY);
            vmat3.clear();
            VelocityMatrix vmat4 = vmat3;
            vmat4.setRotationVelocity(rotation, position2);
            vmat4.setTranslationVelocity(trans_x, trans_y, position2);
            vmat4.setScaleVelocity(scale_x, scale_y, position2);
            vmat4.setRotationVelocity(osc_r2, position2);
            KeyCycleOscillator osc_x5 = osc_x4;
            vmat4.setTranslationVelocity(osc_x5, osc_y, position2);
            KeyCycleOscillator osc_sy3 = osc_sy2;
            vmat4.setScaleVelocity(osc_sx2, osc_sy3, position2);
            KeyCycleOscillator keyCycleOscillator8 = osc_sy3;
            KeyCycleOscillator keyCycleOscillator9 = osc_x5;
            VelocityMatrix velocityMatrix = vmat4;
            vmat4.applyTransform(locationX, locationY, width, height, mAnchorDpDt);
        }
    }

    public int getDrawPath() {
        int mode = this.mStartMotionPath.mDrawPath;
        Iterator<MotionPaths> it = this.mMotionPaths.iterator();
        while (it.hasNext()) {
            mode = Math.max(mode, it.next().mDrawPath);
        }
        return Math.max(mode, this.mEndMotionPath.mDrawPath);
    }

    public void setDrawPath(int debugMode) {
        this.mStartMotionPath.mDrawPath = debugMode;
    }

    /* access modifiers changed from: package-private */
    public String name() {
        return this.mView.getContext().getResources().getResourceEntryName(this.mView.getId());
    }

    /* access modifiers changed from: package-private */
    public void positionKeyframe(View view, KeyPositionBase key, float x, float y, String[] attribute, float[] value) {
        RectF start = new RectF();
        start.left = this.mStartMotionPath.x;
        start.top = this.mStartMotionPath.y;
        start.right = start.left + this.mStartMotionPath.width;
        start.bottom = start.top + this.mStartMotionPath.height;
        RectF end = new RectF();
        end.left = this.mEndMotionPath.x;
        end.top = this.mEndMotionPath.y;
        end.right = end.left + this.mEndMotionPath.width;
        end.bottom = end.top + this.mEndMotionPath.height;
        key.positionAttributes(view, start, end, x, y, attribute, value);
    }

    public int getkeyFramePositions(int[] type, float[] pos) {
        int i = 0;
        int count = 0;
        Iterator<Key> it = this.mKeyList.iterator();
        while (it.hasNext()) {
            Key key = it.next();
            type[i] = key.mFramePosition + (key.mType * 1000);
            this.mSpline[0].getPos((double) (((float) key.mFramePosition) / 100.0f), this.mInterpolateData);
            this.mStartMotionPath.getCenter(this.mInterpolateVariables, this.mInterpolateData, pos, count);
            count += 2;
            i++;
        }
        return i;
    }

    public int getKeyFrameInfo(int type, int[] info) {
        int count = 0;
        int cursor = 0;
        float[] pos = new float[2];
        Iterator<Key> it = this.mKeyList.iterator();
        while (it.hasNext()) {
            Key key = it.next();
            if (key.mType == type || type != -1) {
                int len = cursor;
                info[cursor] = 0;
                int cursor2 = cursor + 1;
                info[cursor2] = key.mType;
                int cursor3 = cursor2 + 1;
                info[cursor3] = key.mFramePosition;
                this.mSpline[0].getPos((double) (((float) key.mFramePosition) / 100.0f), this.mInterpolateData);
                this.mStartMotionPath.getCenter(this.mInterpolateVariables, this.mInterpolateData, pos, 0);
                int cursor4 = cursor3 + 1;
                info[cursor4] = Float.floatToIntBits(pos[0]);
                int cursor5 = cursor4 + 1;
                info[cursor5] = Float.floatToIntBits(pos[1]);
                if (key instanceof KeyPosition) {
                    KeyPosition kp = (KeyPosition) key;
                    int cursor6 = cursor5 + 1;
                    info[cursor6] = kp.mPositionType;
                    int cursor7 = cursor6 + 1;
                    info[cursor7] = Float.floatToIntBits(kp.mPercentX);
                    cursor5 = cursor7 + 1;
                    info[cursor5] = Float.floatToIntBits(kp.mPercentY);
                }
                cursor = cursor5 + 1;
                info[len] = cursor - len;
                count++;
            }
        }
        return count;
    }
}
