package android.support.constraint;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.constraint.ConstraintAttribute;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Constraints;
import android.support.constraint.R;
import android.support.constraint.motion.Debug;
import android.support.constraint.motion.MotionScene;
import android.support.constraint.motion.utils.Easing;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.HelperWidget;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ConstraintSet {
    private static final int ALPHA = 43;
    private static final int ANIMATE_RELATIVE_TO = 64;
    private static final int BARRIER_ALLOWS_GONE_WIDGETS = 75;
    private static final int BARRIER_DIRECTION = 72;
    private static final int BARRIER_MARGIN = 73;
    private static final int BARRIER_TYPE = 1;
    public static final int BASELINE = 5;
    private static final int BASELINE_TO_BASELINE = 1;
    public static final int BOTTOM = 4;
    private static final int BOTTOM_MARGIN = 2;
    private static final int BOTTOM_TO_BOTTOM = 3;
    private static final int BOTTOM_TO_TOP = 4;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    private static final int CHAIN_USE_RTL = 71;
    private static final int CIRCLE = 61;
    private static final int CIRCLE_ANGLE = 63;
    private static final int CIRCLE_RADIUS = 62;
    private static final int CONSTRAINED_HEIGHT = 81;
    private static final int CONSTRAINED_WIDTH = 80;
    private static final int CONSTRAINT_REFERENCED_IDS = 74;
    private static final int CONSTRAINT_TAG = 77;
    private static final boolean DEBUG = false;
    private static final int DIMENSION_RATIO = 5;
    private static final int DRAW_PATH = 66;
    private static final int EDITOR_ABSOLUTE_X = 6;
    private static final int EDITOR_ABSOLUTE_Y = 7;
    private static final int ELEVATION = 44;
    public static final int END = 7;
    private static final int END_MARGIN = 8;
    private static final int END_TO_END = 9;
    private static final int END_TO_START = 10;
    private static final String ERROR_MESSAGE = "XML parser error must be within a Constraint ";
    public static final int GONE = 8;
    private static final int GONE_BOTTOM_MARGIN = 11;
    private static final int GONE_END_MARGIN = 12;
    private static final int GONE_LEFT_MARGIN = 13;
    private static final int GONE_RIGHT_MARGIN = 14;
    private static final int GONE_START_MARGIN = 15;
    private static final int GONE_TOP_MARGIN = 16;
    private static final int GUIDE_BEGIN = 17;
    private static final int GUIDE_END = 18;
    private static final int GUIDE_PERCENT = 19;
    private static final int HEIGHT_DEFAULT = 55;
    private static final int HEIGHT_MAX = 57;
    private static final int HEIGHT_MIN = 59;
    private static final int HEIGHT_PERCENT = 70;
    public static final int HORIZONTAL = 0;
    private static final int HORIZONTAL_BIAS = 20;
    public static final int HORIZONTAL_GUIDELINE = 0;
    private static final int HORIZONTAL_STYLE = 41;
    private static final int HORIZONTAL_WEIGHT = 39;
    public static final int INVISIBLE = 4;
    private static final int LAYOUT_HEIGHT = 21;
    private static final int LAYOUT_VISIBILITY = 22;
    private static final int LAYOUT_WIDTH = 23;
    public static final int LEFT = 1;
    private static final int LEFT_MARGIN = 24;
    private static final int LEFT_TO_LEFT = 25;
    private static final int LEFT_TO_RIGHT = 26;
    public static final int MATCH_CONSTRAINT = 0;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    private static final int MOTION_STAGGER = 79;
    private static final int ORIENTATION = 27;
    public static final int PARENT_ID = 0;
    private static final int PATH_MOTION_ARC = 76;
    private static final int PROGRESS = 68;
    public static final int RIGHT = 2;
    private static final int RIGHT_MARGIN = 28;
    private static final int RIGHT_TO_LEFT = 29;
    private static final int RIGHT_TO_RIGHT = 30;
    private static final int ROTATION = 60;
    private static final int ROTATION_X = 45;
    private static final int ROTATION_Y = 46;
    private static final int SCALE_X = 47;
    private static final int SCALE_Y = 48;
    public static final int START = 6;
    private static final int START_MARGIN = 31;
    private static final int START_TO_END = 32;
    private static final int START_TO_START = 33;
    private static final String TAG = "ConstraintSet";
    public static final int TOP = 3;
    private static final int TOP_MARGIN = 34;
    private static final int TOP_TO_BOTTOM = 35;
    private static final int TOP_TO_TOP = 36;
    private static final int TRANSFORM_PIVOT_X = 49;
    private static final int TRANSFORM_PIVOT_Y = 50;
    private static final int TRANSITION_EASING = 65;
    private static final int TRANSITION_PATH_ROTATE = 67;
    private static final int TRANSLATION_X = 51;
    private static final int TRANSLATION_Y = 52;
    private static final int TRANSLATION_Z = 53;
    public static final int UNSET = -1;
    private static final int UNUSED = 82;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_BIAS = 37;
    public static final int VERTICAL_GUIDELINE = 1;
    private static final int VERTICAL_STYLE = 42;
    private static final int VERTICAL_WEIGHT = 40;
    private static final int VIEW_ID = 38;
    /* access modifiers changed from: private */
    public static final int[] VISIBILITY_FLAGS = {0, 4, 8};
    private static final int VISIBILITY_MODE = 78;
    public static final int VISIBILITY_MODE_IGNORE = 1;
    public static final int VISIBILITY_MODE_NORMAL = 0;
    public static final int VISIBLE = 0;
    private static final int WIDTH_DEFAULT = 54;
    private static final int WIDTH_MAX = 56;
    private static final int WIDTH_MIN = 58;
    private static final int WIDTH_PERCENT = 69;
    public static final int WRAP_CONTENT = -2;
    private static SparseIntArray mapToConstant;
    private HashMap<Integer, Constraint> mConstraints = new HashMap<>();
    private boolean mForceId = true;
    private HashMap<String, ConstraintAttribute> mSavedAttributes = new HashMap<>();
    private boolean mValidate;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        mapToConstant = sparseIntArray;
        sparseIntArray.append(R.styleable.Constraint_layout_constraintLeft_toLeftOf, 25);
        mapToConstant.append(R.styleable.Constraint_layout_constraintLeft_toRightOf, 26);
        mapToConstant.append(R.styleable.Constraint_layout_constraintRight_toLeftOf, 29);
        mapToConstant.append(R.styleable.Constraint_layout_constraintRight_toRightOf, 30);
        mapToConstant.append(R.styleable.Constraint_layout_constraintTop_toTopOf, 36);
        mapToConstant.append(R.styleable.Constraint_layout_constraintTop_toBottomOf, 35);
        mapToConstant.append(R.styleable.Constraint_layout_constraintBottom_toTopOf, 4);
        mapToConstant.append(R.styleable.Constraint_layout_constraintBottom_toBottomOf, 3);
        mapToConstant.append(R.styleable.Constraint_layout_constraintBaseline_toBaselineOf, 1);
        mapToConstant.append(R.styleable.Constraint_layout_editor_absoluteX, 6);
        mapToConstant.append(R.styleable.Constraint_layout_editor_absoluteY, 7);
        mapToConstant.append(R.styleable.Constraint_layout_constraintGuide_begin, 17);
        mapToConstant.append(R.styleable.Constraint_layout_constraintGuide_end, 18);
        mapToConstant.append(R.styleable.Constraint_layout_constraintGuide_percent, 19);
        mapToConstant.append(R.styleable.Constraint_android_orientation, 27);
        mapToConstant.append(R.styleable.Constraint_layout_constraintStart_toEndOf, 32);
        mapToConstant.append(R.styleable.Constraint_layout_constraintStart_toStartOf, 33);
        mapToConstant.append(R.styleable.Constraint_layout_constraintEnd_toStartOf, 10);
        mapToConstant.append(R.styleable.Constraint_layout_constraintEnd_toEndOf, 9);
        mapToConstant.append(R.styleable.Constraint_layout_goneMarginLeft, 13);
        mapToConstant.append(R.styleable.Constraint_layout_goneMarginTop, 16);
        mapToConstant.append(R.styleable.Constraint_layout_goneMarginRight, 14);
        mapToConstant.append(R.styleable.Constraint_layout_goneMarginBottom, 11);
        mapToConstant.append(R.styleable.Constraint_layout_goneMarginStart, 15);
        mapToConstant.append(R.styleable.Constraint_layout_goneMarginEnd, 12);
        mapToConstant.append(R.styleable.Constraint_layout_constraintVertical_weight, 40);
        mapToConstant.append(R.styleable.Constraint_layout_constraintHorizontal_weight, 39);
        mapToConstant.append(R.styleable.Constraint_layout_constraintHorizontal_chainStyle, 41);
        mapToConstant.append(R.styleable.Constraint_layout_constraintVertical_chainStyle, 42);
        mapToConstant.append(R.styleable.Constraint_layout_constraintHorizontal_bias, 20);
        mapToConstant.append(R.styleable.Constraint_layout_constraintVertical_bias, 37);
        mapToConstant.append(R.styleable.Constraint_layout_constraintDimensionRatio, 5);
        mapToConstant.append(R.styleable.Constraint_layout_constraintLeft_creator, 82);
        mapToConstant.append(R.styleable.Constraint_layout_constraintTop_creator, 82);
        mapToConstant.append(R.styleable.Constraint_layout_constraintRight_creator, 82);
        mapToConstant.append(R.styleable.Constraint_layout_constraintBottom_creator, 82);
        mapToConstant.append(R.styleable.Constraint_layout_constraintBaseline_creator, 82);
        mapToConstant.append(R.styleable.Constraint_android_layout_marginLeft, 24);
        mapToConstant.append(R.styleable.Constraint_android_layout_marginRight, 28);
        mapToConstant.append(R.styleable.Constraint_android_layout_marginStart, 31);
        mapToConstant.append(R.styleable.Constraint_android_layout_marginEnd, 8);
        mapToConstant.append(R.styleable.Constraint_android_layout_marginTop, 34);
        mapToConstant.append(R.styleable.Constraint_android_layout_marginBottom, 2);
        mapToConstant.append(R.styleable.Constraint_android_layout_width, 23);
        mapToConstant.append(R.styleable.Constraint_android_layout_height, 21);
        mapToConstant.append(R.styleable.Constraint_android_visibility, 22);
        mapToConstant.append(R.styleable.Constraint_android_alpha, 43);
        mapToConstant.append(R.styleable.Constraint_android_elevation, 44);
        mapToConstant.append(R.styleable.Constraint_android_rotationX, 45);
        mapToConstant.append(R.styleable.Constraint_android_rotationY, 46);
        mapToConstant.append(R.styleable.Constraint_android_rotation, 60);
        mapToConstant.append(R.styleable.Constraint_android_scaleX, 47);
        mapToConstant.append(R.styleable.Constraint_android_scaleY, 48);
        mapToConstant.append(R.styleable.Constraint_android_transformPivotX, 49);
        mapToConstant.append(R.styleable.Constraint_android_transformPivotY, 50);
        mapToConstant.append(R.styleable.Constraint_android_translationX, 51);
        mapToConstant.append(R.styleable.Constraint_android_translationY, 52);
        mapToConstant.append(R.styleable.Constraint_android_translationZ, 53);
        mapToConstant.append(R.styleable.Constraint_layout_constraintWidth_default, 54);
        mapToConstant.append(R.styleable.Constraint_layout_constraintHeight_default, 55);
        mapToConstant.append(R.styleable.Constraint_layout_constraintWidth_max, 56);
        mapToConstant.append(R.styleable.Constraint_layout_constraintHeight_max, 57);
        mapToConstant.append(R.styleable.Constraint_layout_constraintWidth_min, 58);
        mapToConstant.append(R.styleable.Constraint_layout_constraintHeight_min, 59);
        mapToConstant.append(R.styleable.Constraint_layout_constraintCircle, 61);
        mapToConstant.append(R.styleable.Constraint_layout_constraintCircleRadius, 62);
        mapToConstant.append(R.styleable.Constraint_layout_constraintCircleAngle, 63);
        mapToConstant.append(R.styleable.Constraint_animate_relativeTo, 64);
        mapToConstant.append(R.styleable.Constraint_transitionEasing, 65);
        mapToConstant.append(R.styleable.Constraint_drawPath, 66);
        mapToConstant.append(R.styleable.Constraint_transitionPathRotate, 67);
        mapToConstant.append(R.styleable.Constraint_motionStagger, 79);
        mapToConstant.append(R.styleable.Constraint_android_id, 38);
        mapToConstant.append(R.styleable.Constraint_motionProgress, 68);
        mapToConstant.append(R.styleable.Constraint_layout_constraintWidth_percent, 69);
        mapToConstant.append(R.styleable.Constraint_layout_constraintHeight_percent, 70);
        mapToConstant.append(R.styleable.Constraint_chainUseRtl, 71);
        mapToConstant.append(R.styleable.Constraint_barrierDirection, 72);
        mapToConstant.append(R.styleable.Constraint_barrierMargin, 73);
        mapToConstant.append(R.styleable.Constraint_constraint_referenced_ids, 74);
        mapToConstant.append(R.styleable.Constraint_barrierAllowsGoneWidgets, 75);
        mapToConstant.append(R.styleable.Constraint_pathMotionArc, 76);
        mapToConstant.append(R.styleable.Constraint_layout_constraintTag, 77);
        mapToConstant.append(R.styleable.Constraint_visibilityMode, 78);
        mapToConstant.append(R.styleable.Constraint_layout_constrainedWidth, 80);
        mapToConstant.append(R.styleable.Constraint_layout_constrainedHeight, 81);
    }

    public HashMap<String, ConstraintAttribute> getCustomAttributeSet() {
        return this.mSavedAttributes;
    }

    public Constraint getParameters(int mId) {
        return get(mId);
    }

    public void readFallback(ConstraintSet set) {
        for (Integer key : set.mConstraints.keySet()) {
            int id = key.intValue();
            Constraint parent = set.mConstraints.get(key);
            if (!this.mConstraints.containsKey(Integer.valueOf(id))) {
                this.mConstraints.put(Integer.valueOf(id), new Constraint());
            }
            Constraint constraint = this.mConstraints.get(Integer.valueOf(id));
            if (!constraint.layout.mApply) {
                constraint.layout.copyFrom(parent.layout);
            }
            if (!constraint.propertySet.mApply) {
                constraint.propertySet.copyFrom(parent.propertySet);
            }
            if (!constraint.transform.mApply) {
                constraint.transform.copyFrom(parent.transform);
            }
            if (!constraint.motion.mApply) {
                constraint.motion.copyFrom(parent.motion);
            }
            for (String s : parent.mCustomConstraints.keySet()) {
                if (!constraint.mCustomConstraints.containsKey(s)) {
                    constraint.mCustomConstraints.put(s, parent.mCustomConstraints.get(s));
                }
            }
        }
    }

    public void readFallback(ConstraintLayout constraintLayout) {
        int count = constraintLayout.getChildCount();
        int i = 0;
        while (i < count) {
            View view = constraintLayout.getChildAt(i);
            ConstraintLayout.LayoutParams param = (ConstraintLayout.LayoutParams) view.getLayoutParams();
            int id = view.getId();
            if (!this.mForceId || id != -1) {
                if (!this.mConstraints.containsKey(Integer.valueOf(id))) {
                    this.mConstraints.put(Integer.valueOf(id), new Constraint());
                }
                Constraint constraint = this.mConstraints.get(Integer.valueOf(id));
                if (!constraint.layout.mApply) {
                    constraint.fillFrom(id, param);
                    if (view instanceof ConstraintHelper) {
                        constraint.layout.mReferenceIds = ((ConstraintHelper) view).getReferencedIds();
                        if (view instanceof Barrier) {
                            Barrier barrier = (Barrier) view;
                            constraint.layout.mBarrierAllowsGoneWidgets = barrier.allowsGoneWidget();
                            constraint.layout.mBarrierDirection = barrier.getType();
                            constraint.layout.mBarrierMargin = barrier.getMargin();
                        }
                    }
                    constraint.layout.mApply = true;
                }
                if (!constraint.propertySet.mApply) {
                    constraint.propertySet.visibility = view.getVisibility();
                    constraint.propertySet.alpha = view.getAlpha();
                    constraint.propertySet.mApply = true;
                }
                if (Build.VERSION.SDK_INT >= 17 && !constraint.transform.mApply) {
                    constraint.transform.mApply = true;
                    constraint.transform.rotation = view.getRotation();
                    constraint.transform.rotationX = view.getRotationX();
                    constraint.transform.rotationY = view.getRotationY();
                    constraint.transform.scaleX = view.getScaleX();
                    constraint.transform.scaleY = view.getScaleY();
                    float pivotX = view.getPivotX();
                    float pivotY = view.getPivotY();
                    if (!(((double) pivotX) == 0.0d && ((double) pivotY) == 0.0d)) {
                        constraint.transform.transformPivotX = pivotX;
                        constraint.transform.transformPivotY = pivotY;
                    }
                    constraint.transform.translationX = view.getTranslationX();
                    constraint.transform.translationY = view.getTranslationY();
                    if (Build.VERSION.SDK_INT >= 21) {
                        constraint.transform.translationZ = view.getTranslationZ();
                        if (constraint.transform.applyElevation) {
                            constraint.transform.elevation = view.getElevation();
                        }
                    }
                }
                i++;
            } else {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
        }
    }

    public static class Layout {
        private static final int BARRIER_ALLOWS_GONE_WIDGETS = 75;
        private static final int BARRIER_DIRECTION = 72;
        private static final int BARRIER_MARGIN = 73;
        private static final int BASELINE_TO_BASELINE = 1;
        private static final int BOTTOM_MARGIN = 2;
        private static final int BOTTOM_TO_BOTTOM = 3;
        private static final int BOTTOM_TO_TOP = 4;
        private static final int CHAIN_USE_RTL = 71;
        private static final int CIRCLE = 61;
        private static final int CIRCLE_ANGLE = 63;
        private static final int CIRCLE_RADIUS = 62;
        private static final int CONSTRAINT_REFERENCED_IDS = 74;
        private static final int DIMENSION_RATIO = 5;
        private static final int EDITOR_ABSOLUTE_X = 6;
        private static final int EDITOR_ABSOLUTE_Y = 7;
        private static final int END_MARGIN = 8;
        private static final int END_TO_END = 9;
        private static final int END_TO_START = 10;
        private static final int GONE_BOTTOM_MARGIN = 11;
        private static final int GONE_END_MARGIN = 12;
        private static final int GONE_LEFT_MARGIN = 13;
        private static final int GONE_RIGHT_MARGIN = 14;
        private static final int GONE_START_MARGIN = 15;
        private static final int GONE_TOP_MARGIN = 16;
        private static final int GUIDE_BEGIN = 17;
        private static final int GUIDE_END = 18;
        private static final int GUIDE_PERCENT = 19;
        private static final int HEIGHT_PERCENT = 70;
        private static final int HORIZONTAL_BIAS = 20;
        private static final int HORIZONTAL_STYLE = 39;
        private static final int HORIZONTAL_WEIGHT = 37;
        private static final int LAYOUT_HEIGHT = 21;
        private static final int LAYOUT_WIDTH = 22;
        private static final int LEFT_MARGIN = 23;
        private static final int LEFT_TO_LEFT = 24;
        private static final int LEFT_TO_RIGHT = 25;
        private static final int ORIENTATION = 26;
        private static final int RIGHT_MARGIN = 27;
        private static final int RIGHT_TO_LEFT = 28;
        private static final int RIGHT_TO_RIGHT = 29;
        private static final int START_MARGIN = 30;
        private static final int START_TO_END = 31;
        private static final int START_TO_START = 32;
        private static final int TOP_MARGIN = 33;
        private static final int TOP_TO_BOTTOM = 34;
        private static final int TOP_TO_TOP = 35;
        public static final int UNSET = -1;
        private static final int UNUSED = 76;
        private static final int VERTICAL_BIAS = 36;
        private static final int VERTICAL_STYLE = 40;
        private static final int VERTICAL_WEIGHT = 38;
        private static final int WIDTH_PERCENT = 69;
        private static SparseIntArray mapToConstant;
        public int baselineToBaseline = -1;
        public int bottomMargin = -1;
        public int bottomToBottom = -1;
        public int bottomToTop = -1;
        public float circleAngle = 0.0f;
        public int circleConstraint = -1;
        public int circleRadius = 0;
        public boolean constrainedHeight = false;
        public boolean constrainedWidth = false;
        public String dimensionRatio = null;
        public int editorAbsoluteX = -1;
        public int editorAbsoluteY = -1;
        public int endMargin = -1;
        public int endToEnd = -1;
        public int endToStart = -1;
        public int goneBottomMargin = -1;
        public int goneEndMargin = -1;
        public int goneLeftMargin = -1;
        public int goneRightMargin = -1;
        public int goneStartMargin = -1;
        public int goneTopMargin = -1;
        public int guideBegin = -1;
        public int guideEnd = -1;
        public float guidePercent = -1.0f;
        public int heightDefault = 0;
        public int heightMax = -1;
        public int heightMin = -1;
        public float heightPercent = 1.0f;
        public float horizontalBias = 0.5f;
        public int horizontalChainStyle = 0;
        public float horizontalWeight = -1.0f;
        public int leftMargin = -1;
        public int leftToLeft = -1;
        public int leftToRight = -1;
        public boolean mApply = false;
        public boolean mBarrierAllowsGoneWidgets = true;
        public int mBarrierDirection = -1;
        public int mBarrierMargin = 0;
        public String mConstraintTag;
        public int mHeight;
        public int mHelperType = -1;
        public boolean mIsGuideline = false;
        public String mReferenceIdString;
        public int[] mReferenceIds;
        public int mWidth;
        public int orientation = -1;
        public int rightMargin = -1;
        public int rightToLeft = -1;
        public int rightToRight = -1;
        public int startMargin = -1;
        public int startToEnd = -1;
        public int startToStart = -1;
        public int topMargin = -1;
        public int topToBottom = -1;
        public int topToTop = -1;
        public float verticalBias = 0.5f;
        public int verticalChainStyle = 0;
        public float verticalWeight = -1.0f;
        public int widthDefault = 0;
        public int widthMax = -1;
        public int widthMin = -1;
        public float widthPercent = 1.0f;

        public void copyFrom(Layout src) {
            this.mIsGuideline = src.mIsGuideline;
            this.mWidth = src.mWidth;
            this.mApply = src.mApply;
            this.mHeight = src.mHeight;
            this.guideBegin = src.guideBegin;
            this.guideEnd = src.guideEnd;
            this.guidePercent = src.guidePercent;
            this.leftToLeft = src.leftToLeft;
            this.leftToRight = src.leftToRight;
            this.rightToLeft = src.rightToLeft;
            this.rightToRight = src.rightToRight;
            this.topToTop = src.topToTop;
            this.topToBottom = src.topToBottom;
            this.bottomToTop = src.bottomToTop;
            this.bottomToBottom = src.bottomToBottom;
            this.baselineToBaseline = src.baselineToBaseline;
            this.startToEnd = src.startToEnd;
            this.startToStart = src.startToStart;
            this.endToStart = src.endToStart;
            this.endToEnd = src.endToEnd;
            this.horizontalBias = src.horizontalBias;
            this.verticalBias = src.verticalBias;
            this.dimensionRatio = src.dimensionRatio;
            this.circleConstraint = src.circleConstraint;
            this.circleRadius = src.circleRadius;
            this.circleAngle = src.circleAngle;
            this.editorAbsoluteX = src.editorAbsoluteX;
            this.editorAbsoluteY = src.editorAbsoluteY;
            this.orientation = src.orientation;
            this.leftMargin = src.leftMargin;
            this.rightMargin = src.rightMargin;
            this.topMargin = src.topMargin;
            this.bottomMargin = src.bottomMargin;
            this.endMargin = src.endMargin;
            this.startMargin = src.startMargin;
            this.goneLeftMargin = src.goneLeftMargin;
            this.goneTopMargin = src.goneTopMargin;
            this.goneRightMargin = src.goneRightMargin;
            this.goneBottomMargin = src.goneBottomMargin;
            this.goneEndMargin = src.goneEndMargin;
            this.goneStartMargin = src.goneStartMargin;
            this.verticalWeight = src.verticalWeight;
            this.horizontalWeight = src.horizontalWeight;
            this.horizontalChainStyle = src.horizontalChainStyle;
            this.verticalChainStyle = src.verticalChainStyle;
            this.widthDefault = src.widthDefault;
            this.heightDefault = src.heightDefault;
            this.widthMax = src.widthMax;
            this.heightMax = src.heightMax;
            this.widthMin = src.widthMin;
            this.heightMin = src.heightMin;
            this.widthPercent = src.widthPercent;
            this.heightPercent = src.heightPercent;
            this.mBarrierDirection = src.mBarrierDirection;
            this.mBarrierMargin = src.mBarrierMargin;
            this.mHelperType = src.mHelperType;
            this.mConstraintTag = src.mConstraintTag;
            int[] iArr = src.mReferenceIds;
            if (iArr != null) {
                this.mReferenceIds = Arrays.copyOf(iArr, iArr.length);
            } else {
                this.mReferenceIds = null;
            }
            this.mReferenceIdString = src.mReferenceIdString;
            this.constrainedWidth = src.constrainedWidth;
            this.constrainedHeight = src.constrainedHeight;
            this.mBarrierAllowsGoneWidgets = src.mBarrierAllowsGoneWidgets;
        }

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            mapToConstant = sparseIntArray;
            sparseIntArray.append(R.styleable.Layout_layout_constraintLeft_toLeftOf, 24);
            mapToConstant.append(R.styleable.Layout_layout_constraintLeft_toRightOf, 25);
            mapToConstant.append(R.styleable.Layout_layout_constraintRight_toLeftOf, 28);
            mapToConstant.append(R.styleable.Layout_layout_constraintRight_toRightOf, 29);
            mapToConstant.append(R.styleable.Layout_layout_constraintTop_toTopOf, 35);
            mapToConstant.append(R.styleable.Layout_layout_constraintTop_toBottomOf, 34);
            mapToConstant.append(R.styleable.Layout_layout_constraintBottom_toTopOf, 4);
            mapToConstant.append(R.styleable.Layout_layout_constraintBottom_toBottomOf, 3);
            mapToConstant.append(R.styleable.Layout_layout_constraintBaseline_toBaselineOf, 1);
            mapToConstant.append(R.styleable.Layout_layout_editor_absoluteX, 6);
            mapToConstant.append(R.styleable.Layout_layout_editor_absoluteY, 7);
            mapToConstant.append(R.styleable.Layout_layout_constraintGuide_begin, 17);
            mapToConstant.append(R.styleable.Layout_layout_constraintGuide_end, 18);
            mapToConstant.append(R.styleable.Layout_layout_constraintGuide_percent, 19);
            mapToConstant.append(R.styleable.Layout_android_orientation, 26);
            mapToConstant.append(R.styleable.Layout_layout_constraintStart_toEndOf, 31);
            mapToConstant.append(R.styleable.Layout_layout_constraintStart_toStartOf, 32);
            mapToConstant.append(R.styleable.Layout_layout_constraintEnd_toStartOf, 10);
            mapToConstant.append(R.styleable.Layout_layout_constraintEnd_toEndOf, 9);
            mapToConstant.append(R.styleable.Layout_layout_goneMarginLeft, 13);
            mapToConstant.append(R.styleable.Layout_layout_goneMarginTop, 16);
            mapToConstant.append(R.styleable.Layout_layout_goneMarginRight, 14);
            mapToConstant.append(R.styleable.Layout_layout_goneMarginBottom, 11);
            mapToConstant.append(R.styleable.Layout_layout_goneMarginStart, 15);
            mapToConstant.append(R.styleable.Layout_layout_goneMarginEnd, 12);
            mapToConstant.append(R.styleable.Layout_layout_constraintVertical_weight, 38);
            mapToConstant.append(R.styleable.Layout_layout_constraintHorizontal_weight, 37);
            mapToConstant.append(R.styleable.Layout_layout_constraintHorizontal_chainStyle, 39);
            mapToConstant.append(R.styleable.Layout_layout_constraintVertical_chainStyle, 40);
            mapToConstant.append(R.styleable.Layout_layout_constraintHorizontal_bias, 20);
            mapToConstant.append(R.styleable.Layout_layout_constraintVertical_bias, 36);
            mapToConstant.append(R.styleable.Layout_layout_constraintDimensionRatio, 5);
            mapToConstant.append(R.styleable.Layout_layout_constraintLeft_creator, 76);
            mapToConstant.append(R.styleable.Layout_layout_constraintTop_creator, 76);
            mapToConstant.append(R.styleable.Layout_layout_constraintRight_creator, 76);
            mapToConstant.append(R.styleable.Layout_layout_constraintBottom_creator, 76);
            mapToConstant.append(R.styleable.Layout_layout_constraintBaseline_creator, 76);
            mapToConstant.append(R.styleable.Layout_android_layout_marginLeft, 23);
            mapToConstant.append(R.styleable.Layout_android_layout_marginRight, 27);
            mapToConstant.append(R.styleable.Layout_android_layout_marginStart, 30);
            mapToConstant.append(R.styleable.Layout_android_layout_marginEnd, 8);
            mapToConstant.append(R.styleable.Layout_android_layout_marginTop, 33);
            mapToConstant.append(R.styleable.Layout_android_layout_marginBottom, 2);
            mapToConstant.append(R.styleable.Layout_android_layout_width, 22);
            mapToConstant.append(R.styleable.Layout_android_layout_height, 21);
            mapToConstant.append(R.styleable.Layout_layout_constraintCircle, 61);
            mapToConstant.append(R.styleable.Layout_layout_constraintCircleRadius, 62);
            mapToConstant.append(R.styleable.Layout_layout_constraintCircleAngle, 63);
            mapToConstant.append(R.styleable.Layout_layout_constraintWidth_percent, 69);
            mapToConstant.append(R.styleable.Layout_layout_constraintHeight_percent, 70);
            mapToConstant.append(R.styleable.Layout_chainUseRtl, 71);
            mapToConstant.append(R.styleable.Layout_barrierDirection, 72);
            mapToConstant.append(R.styleable.Layout_barrierMargin, 73);
            mapToConstant.append(R.styleable.Layout_constraint_referenced_ids, 74);
            mapToConstant.append(R.styleable.Layout_barrierAllowsGoneWidgets, 75);
        }

        /* access modifiers changed from: package-private */
        public void fillFromAttributeList(Context context, AttributeSet attrs) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Layout);
            this.mApply = true;
            int N = a.getIndexCount();
            for (int i = 0; i < N; i++) {
                int attr = a.getIndex(i);
                switch (mapToConstant.get(attr)) {
                    case 1:
                        this.baselineToBaseline = ConstraintSet.lookupID(a, attr, this.baselineToBaseline);
                        break;
                    case 2:
                        this.bottomMargin = a.getDimensionPixelSize(attr, this.bottomMargin);
                        break;
                    case 3:
                        this.bottomToBottom = ConstraintSet.lookupID(a, attr, this.bottomToBottom);
                        break;
                    case 4:
                        this.bottomToTop = ConstraintSet.lookupID(a, attr, this.bottomToTop);
                        break;
                    case 5:
                        this.dimensionRatio = a.getString(attr);
                        break;
                    case 6:
                        this.editorAbsoluteX = a.getDimensionPixelOffset(attr, this.editorAbsoluteX);
                        break;
                    case 7:
                        this.editorAbsoluteY = a.getDimensionPixelOffset(attr, this.editorAbsoluteY);
                        break;
                    case 8:
                        if (Build.VERSION.SDK_INT < 17) {
                            break;
                        } else {
                            this.endMargin = a.getDimensionPixelSize(attr, this.endMargin);
                            break;
                        }
                    case 9:
                        this.endToEnd = ConstraintSet.lookupID(a, attr, this.endToEnd);
                        break;
                    case 10:
                        this.endToStart = ConstraintSet.lookupID(a, attr, this.endToStart);
                        break;
                    case 11:
                        this.goneBottomMargin = a.getDimensionPixelSize(attr, this.goneBottomMargin);
                        break;
                    case 12:
                        this.goneEndMargin = a.getDimensionPixelSize(attr, this.goneEndMargin);
                        break;
                    case 13:
                        this.goneLeftMargin = a.getDimensionPixelSize(attr, this.goneLeftMargin);
                        break;
                    case 14:
                        this.goneRightMargin = a.getDimensionPixelSize(attr, this.goneRightMargin);
                        break;
                    case 15:
                        this.goneStartMargin = a.getDimensionPixelSize(attr, this.goneStartMargin);
                        break;
                    case 16:
                        this.goneTopMargin = a.getDimensionPixelSize(attr, this.goneTopMargin);
                        break;
                    case 17:
                        this.guideBegin = a.getDimensionPixelOffset(attr, this.guideBegin);
                        break;
                    case 18:
                        this.guideEnd = a.getDimensionPixelOffset(attr, this.guideEnd);
                        break;
                    case 19:
                        this.guidePercent = a.getFloat(attr, this.guidePercent);
                        break;
                    case 20:
                        this.horizontalBias = a.getFloat(attr, this.horizontalBias);
                        break;
                    case 21:
                        this.mHeight = a.getLayoutDimension(attr, this.mHeight);
                        break;
                    case 22:
                        this.mWidth = a.getLayoutDimension(attr, this.mWidth);
                        break;
                    case 23:
                        this.leftMargin = a.getDimensionPixelSize(attr, this.leftMargin);
                        break;
                    case 24:
                        this.leftToLeft = ConstraintSet.lookupID(a, attr, this.leftToLeft);
                        break;
                    case 25:
                        this.leftToRight = ConstraintSet.lookupID(a, attr, this.leftToRight);
                        break;
                    case 26:
                        this.orientation = a.getInt(attr, this.orientation);
                        break;
                    case 27:
                        this.rightMargin = a.getDimensionPixelSize(attr, this.rightMargin);
                        break;
                    case 28:
                        this.rightToLeft = ConstraintSet.lookupID(a, attr, this.rightToLeft);
                        break;
                    case 29:
                        this.rightToRight = ConstraintSet.lookupID(a, attr, this.rightToRight);
                        break;
                    case 30:
                        if (Build.VERSION.SDK_INT < 17) {
                            break;
                        } else {
                            this.startMargin = a.getDimensionPixelSize(attr, this.startMargin);
                            break;
                        }
                    case 31:
                        this.startToEnd = ConstraintSet.lookupID(a, attr, this.startToEnd);
                        break;
                    case 32:
                        this.startToStart = ConstraintSet.lookupID(a, attr, this.startToStart);
                        break;
                    case 33:
                        this.topMargin = a.getDimensionPixelSize(attr, this.topMargin);
                        break;
                    case 34:
                        this.topToBottom = ConstraintSet.lookupID(a, attr, this.topToBottom);
                        break;
                    case 35:
                        this.topToTop = ConstraintSet.lookupID(a, attr, this.topToTop);
                        break;
                    case 36:
                        this.verticalBias = a.getFloat(attr, this.verticalBias);
                        break;
                    case 37:
                        this.horizontalWeight = a.getFloat(attr, this.horizontalWeight);
                        break;
                    case 38:
                        this.verticalWeight = a.getFloat(attr, this.verticalWeight);
                        break;
                    case 39:
                        this.horizontalChainStyle = a.getInt(attr, this.horizontalChainStyle);
                        break;
                    case 40:
                        this.verticalChainStyle = a.getInt(attr, this.verticalChainStyle);
                        break;
                    case 54:
                        this.widthDefault = a.getInt(attr, this.widthDefault);
                        break;
                    case 55:
                        this.heightDefault = a.getInt(attr, this.heightDefault);
                        break;
                    case 56:
                        this.widthMax = a.getDimensionPixelSize(attr, this.widthMax);
                        break;
                    case 57:
                        this.heightMax = a.getDimensionPixelSize(attr, this.heightMax);
                        break;
                    case 58:
                        this.widthMin = a.getDimensionPixelSize(attr, this.widthMin);
                        break;
                    case 59:
                        this.heightMin = a.getDimensionPixelSize(attr, this.heightMin);
                        break;
                    case 61:
                        this.circleConstraint = ConstraintSet.lookupID(a, attr, this.circleConstraint);
                        break;
                    case 62:
                        this.circleRadius = a.getDimensionPixelSize(attr, this.circleRadius);
                        break;
                    case 63:
                        this.circleAngle = a.getFloat(attr, this.circleAngle);
                        break;
                    case 69:
                        this.widthPercent = a.getFloat(attr, 1.0f);
                        break;
                    case 70:
                        this.heightPercent = a.getFloat(attr, 1.0f);
                        break;
                    case 71:
                        Log.e(ConstraintSet.TAG, "CURRENTLY UNSUPPORTED");
                        break;
                    case 72:
                        this.mBarrierDirection = a.getInt(attr, this.mBarrierDirection);
                        break;
                    case 73:
                        this.mBarrierMargin = a.getDimensionPixelSize(attr, this.mBarrierMargin);
                        break;
                    case 74:
                        this.mReferenceIdString = a.getString(attr);
                        break;
                    case 75:
                        this.mBarrierAllowsGoneWidgets = a.getBoolean(attr, this.mBarrierAllowsGoneWidgets);
                        break;
                    case 76:
                        Log.w(ConstraintSet.TAG, "unused attribute 0x" + Integer.toHexString(attr) + "   " + mapToConstant.get(attr));
                        break;
                    case 77:
                        this.mConstraintTag = a.getString(attr);
                        break;
                    case 80:
                        this.constrainedWidth = a.getBoolean(attr, this.constrainedWidth);
                        break;
                    case 81:
                        this.constrainedHeight = a.getBoolean(attr, this.constrainedHeight);
                        break;
                    default:
                        Log.w(ConstraintSet.TAG, "Unknown attribute 0x" + Integer.toHexString(attr) + "   " + mapToConstant.get(attr));
                        break;
                }
            }
            a.recycle();
        }

        public void dump(MotionScene scene, StringBuilder stringBuilder) {
            Field[] fields = getClass().getDeclaredFields();
            stringBuilder.append("\n");
            for (Field field : fields) {
                String name = field.getName();
                if (!Modifier.isStatic(field.getModifiers())) {
                    try {
                        Object value = field.get(this);
                        Class<?> type = field.getType();
                        if (type == Integer.TYPE) {
                            Integer iValue = (Integer) value;
                            if (iValue.intValue() != -1) {
                                String stringid = scene.lookUpConstraintName(iValue.intValue());
                                stringBuilder.append("    ");
                                stringBuilder.append(name);
                                stringBuilder.append(" = \"");
                                stringBuilder.append(stringid == null ? iValue : stringid);
                                stringBuilder.append("\"\n");
                            }
                        } else if (type == Float.TYPE) {
                            Float fValue = (Float) value;
                            if (fValue.floatValue() != -1.0f) {
                                stringBuilder.append("    ");
                                stringBuilder.append(name);
                                stringBuilder.append(" = \"");
                                stringBuilder.append(fValue);
                                stringBuilder.append("\"\n");
                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static class Transform {
        private static final int ELEVATION = 11;
        private static final int ROTATION = 1;
        private static final int ROTATION_X = 2;
        private static final int ROTATION_Y = 3;
        private static final int SCALE_X = 4;
        private static final int SCALE_Y = 5;
        private static final int TRANSFORM_PIVOT_X = 6;
        private static final int TRANSFORM_PIVOT_Y = 7;
        private static final int TRANSLATION_X = 8;
        private static final int TRANSLATION_Y = 9;
        private static final int TRANSLATION_Z = 10;
        private static SparseIntArray mapToConstant;
        public boolean applyElevation = false;
        public float elevation = 0.0f;
        public boolean mApply = false;
        public float rotation = 0.0f;
        public float rotationX = 0.0f;
        public float rotationY = 0.0f;
        public float scaleX = 1.0f;
        public float scaleY = 1.0f;
        public float transformPivotX = Float.NaN;
        public float transformPivotY = Float.NaN;
        public float translationX = 0.0f;
        public float translationY = 0.0f;
        public float translationZ = 0.0f;

        public void copyFrom(Transform src) {
            this.mApply = src.mApply;
            this.rotation = src.rotation;
            this.rotationX = src.rotationX;
            this.rotationY = src.rotationY;
            this.scaleX = src.scaleX;
            this.scaleY = src.scaleY;
            this.transformPivotX = src.transformPivotX;
            this.transformPivotY = src.transformPivotY;
            this.translationX = src.translationX;
            this.translationY = src.translationY;
            this.translationZ = src.translationZ;
            this.applyElevation = src.applyElevation;
            this.elevation = src.elevation;
        }

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            mapToConstant = sparseIntArray;
            sparseIntArray.append(R.styleable.Transform_android_rotation, 1);
            mapToConstant.append(R.styleable.Transform_android_rotationX, 2);
            mapToConstant.append(R.styleable.Transform_android_rotationY, 3);
            mapToConstant.append(R.styleable.Transform_android_scaleX, 4);
            mapToConstant.append(R.styleable.Transform_android_scaleY, 5);
            mapToConstant.append(R.styleable.Transform_android_transformPivotX, 6);
            mapToConstant.append(R.styleable.Transform_android_transformPivotY, 7);
            mapToConstant.append(R.styleable.Transform_android_translationX, 8);
            mapToConstant.append(R.styleable.Transform_android_translationY, 9);
            mapToConstant.append(R.styleable.Transform_android_translationZ, 10);
            mapToConstant.append(R.styleable.Transform_android_elevation, 11);
        }

        /* access modifiers changed from: package-private */
        public void fillFromAttributeList(Context context, AttributeSet attrs) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Transform);
            this.mApply = true;
            int N = a.getIndexCount();
            for (int i = 0; i < N; i++) {
                int attr = a.getIndex(i);
                switch (mapToConstant.get(attr)) {
                    case 1:
                        this.rotation = a.getFloat(attr, this.rotation);
                        break;
                    case 2:
                        this.rotationX = a.getFloat(attr, this.rotationX);
                        break;
                    case 3:
                        this.rotationY = a.getFloat(attr, this.rotationY);
                        break;
                    case 4:
                        this.scaleX = a.getFloat(attr, this.scaleX);
                        break;
                    case 5:
                        this.scaleY = a.getFloat(attr, this.scaleY);
                        break;
                    case 6:
                        this.transformPivotX = a.getDimension(attr, this.transformPivotX);
                        break;
                    case 7:
                        this.transformPivotY = a.getDimension(attr, this.transformPivotY);
                        break;
                    case 8:
                        this.translationX = a.getDimension(attr, this.translationX);
                        break;
                    case 9:
                        this.translationY = a.getDimension(attr, this.translationY);
                        break;
                    case 10:
                        if (Build.VERSION.SDK_INT < 21) {
                            break;
                        } else {
                            this.translationZ = a.getDimension(attr, this.translationZ);
                            break;
                        }
                    case 11:
                        if (Build.VERSION.SDK_INT < 21) {
                            break;
                        } else {
                            this.applyElevation = true;
                            this.elevation = a.getDimension(attr, this.elevation);
                            break;
                        }
                }
            }
            a.recycle();
        }
    }

    public static class PropertySet {
        public float alpha = 1.0f;
        public boolean mApply = false;
        public float mProgress = Float.NaN;
        public int mVisibilityMode = 0;
        public int visibility = 0;

        public void copyFrom(PropertySet src) {
            this.mApply = src.mApply;
            this.visibility = src.visibility;
            this.alpha = src.alpha;
            this.mProgress = src.mProgress;
            this.mVisibilityMode = src.mVisibilityMode;
        }

        /* access modifiers changed from: package-private */
        public void fillFromAttributeList(Context context, AttributeSet attrs) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PropertySet);
            this.mApply = true;
            int N = a.getIndexCount();
            for (int i = 0; i < N; i++) {
                int attr = a.getIndex(i);
                if (attr == R.styleable.PropertySet_android_alpha) {
                    this.alpha = a.getFloat(attr, this.alpha);
                } else if (attr == R.styleable.PropertySet_android_visibility) {
                    this.visibility = a.getInt(attr, this.visibility);
                    this.visibility = ConstraintSet.VISIBILITY_FLAGS[this.visibility];
                } else if (attr == R.styleable.PropertySet_visibilityMode) {
                    this.mVisibilityMode = a.getInt(attr, this.mVisibilityMode);
                } else if (attr == R.styleable.PropertySet_motionProgress) {
                    this.mProgress = a.getFloat(attr, this.mProgress);
                }
            }
            a.recycle();
        }
    }

    public static class Motion {
        private static final int ANIMATE_RELATIVE_TO = 5;
        private static final int MOTION_DRAW_PATH = 4;
        private static final int MOTION_STAGGER = 6;
        private static final int PATH_MOTION_ARC = 2;
        private static final int TRANSITION_EASING = 3;
        private static final int TRANSITION_PATH_ROTATE = 1;
        private static SparseIntArray mapToConstant;
        public int mAnimateRelativeTo = -1;
        public boolean mApply = false;
        public int mDrawPath = 0;
        public float mMotionStagger = Float.NaN;
        public int mPathMotionArc = -1;
        public float mPathRotate = Float.NaN;
        public String mTransitionEasing = null;

        public void copyFrom(Motion src) {
            this.mApply = src.mApply;
            this.mAnimateRelativeTo = src.mAnimateRelativeTo;
            this.mTransitionEasing = src.mTransitionEasing;
            this.mPathMotionArc = src.mPathMotionArc;
            this.mDrawPath = src.mDrawPath;
            this.mPathRotate = src.mPathRotate;
            this.mMotionStagger = src.mMotionStagger;
        }

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            mapToConstant = sparseIntArray;
            sparseIntArray.append(R.styleable.Motion_motionPathRotate, 1);
            mapToConstant.append(R.styleable.Motion_pathMotionArc, 2);
            mapToConstant.append(R.styleable.Motion_transitionEasing, 3);
            mapToConstant.append(R.styleable.Motion_drawPath, 4);
            mapToConstant.append(R.styleable.Motion_animate_relativeTo, 5);
            mapToConstant.append(R.styleable.Motion_motionStagger, 6);
        }

        /* access modifiers changed from: package-private */
        public void fillFromAttributeList(Context context, AttributeSet attrs) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Motion);
            this.mApply = true;
            int N = a.getIndexCount();
            for (int i = 0; i < N; i++) {
                int attr = a.getIndex(i);
                switch (mapToConstant.get(attr)) {
                    case 1:
                        this.mPathRotate = a.getFloat(attr, this.mPathRotate);
                        break;
                    case 2:
                        this.mPathMotionArc = a.getInt(attr, this.mPathMotionArc);
                        break;
                    case 3:
                        if (a.peekValue(attr).type != 3) {
                            this.mTransitionEasing = Easing.NAMED_EASING[a.getInteger(attr, 0)];
                            break;
                        } else {
                            this.mTransitionEasing = a.getString(attr);
                            break;
                        }
                    case 4:
                        this.mDrawPath = a.getInt(attr, 0);
                        break;
                    case 5:
                        this.mAnimateRelativeTo = ConstraintSet.lookupID(a, attr, this.mAnimateRelativeTo);
                        break;
                    case 6:
                        this.mMotionStagger = a.getFloat(attr, this.mMotionStagger);
                        break;
                }
            }
            a.recycle();
        }
    }

    public static class Constraint {
        public final Layout layout = new Layout();
        public HashMap<String, ConstraintAttribute> mCustomConstraints = new HashMap<>();
        int mViewId;
        public final Motion motion = new Motion();
        public final PropertySet propertySet = new PropertySet();
        public final Transform transform = new Transform();

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

        /* access modifiers changed from: private */
        public void setStringValue(String attributeName, String value) {
            get(attributeName, ConstraintAttribute.AttributeType.STRING_TYPE).setStringValue(value);
        }

        /* access modifiers changed from: private */
        public void setFloatValue(String attributeName, float value) {
            get(attributeName, ConstraintAttribute.AttributeType.FLOAT_TYPE).setFloatValue(value);
        }

        /* access modifiers changed from: private */
        public void setIntValue(String attributeName, int value) {
            get(attributeName, ConstraintAttribute.AttributeType.INT_TYPE).setIntValue(value);
        }

        /* access modifiers changed from: private */
        public void setColorValue(String attributeName, int value) {
            get(attributeName, ConstraintAttribute.AttributeType.COLOR_TYPE).setColorValue(value);
        }

        public Constraint clone() {
            Constraint clone = new Constraint();
            clone.layout.copyFrom(this.layout);
            clone.motion.copyFrom(this.motion);
            clone.propertySet.copyFrom(this.propertySet);
            clone.transform.copyFrom(this.transform);
            clone.mViewId = this.mViewId;
            return clone;
        }

        /* access modifiers changed from: private */
        public void fillFromConstraints(ConstraintHelper helper, int viewId, Constraints.LayoutParams param) {
            fillFromConstraints(viewId, param);
            if (helper instanceof Barrier) {
                this.layout.mHelperType = 1;
                Barrier barrier = (Barrier) helper;
                this.layout.mBarrierDirection = barrier.getType();
                this.layout.mReferenceIds = barrier.getReferencedIds();
                this.layout.mBarrierMargin = barrier.getMargin();
            }
        }

        /* access modifiers changed from: private */
        public void fillFromConstraints(int viewId, Constraints.LayoutParams param) {
            fillFrom(viewId, param);
            this.propertySet.alpha = param.alpha;
            this.transform.rotation = param.rotation;
            this.transform.rotationX = param.rotationX;
            this.transform.rotationY = param.rotationY;
            this.transform.scaleX = param.scaleX;
            this.transform.scaleY = param.scaleY;
            this.transform.transformPivotX = param.transformPivotX;
            this.transform.transformPivotY = param.transformPivotY;
            this.transform.translationX = param.translationX;
            this.transform.translationY = param.translationY;
            this.transform.translationZ = param.translationZ;
            this.transform.elevation = param.elevation;
            this.transform.applyElevation = param.applyElevation;
        }

        /* access modifiers changed from: private */
        public void fillFrom(int viewId, ConstraintLayout.LayoutParams param) {
            this.mViewId = viewId;
            this.layout.leftToLeft = param.leftToLeft;
            this.layout.leftToRight = param.leftToRight;
            this.layout.rightToLeft = param.rightToLeft;
            this.layout.rightToRight = param.rightToRight;
            this.layout.topToTop = param.topToTop;
            this.layout.topToBottom = param.topToBottom;
            this.layout.bottomToTop = param.bottomToTop;
            this.layout.bottomToBottom = param.bottomToBottom;
            this.layout.baselineToBaseline = param.baselineToBaseline;
            this.layout.startToEnd = param.startToEnd;
            this.layout.startToStart = param.startToStart;
            this.layout.endToStart = param.endToStart;
            this.layout.endToEnd = param.endToEnd;
            this.layout.horizontalBias = param.horizontalBias;
            this.layout.verticalBias = param.verticalBias;
            this.layout.dimensionRatio = param.dimensionRatio;
            this.layout.circleConstraint = param.circleConstraint;
            this.layout.circleRadius = param.circleRadius;
            this.layout.circleAngle = param.circleAngle;
            this.layout.editorAbsoluteX = param.editorAbsoluteX;
            this.layout.editorAbsoluteY = param.editorAbsoluteY;
            this.layout.orientation = param.orientation;
            this.layout.guidePercent = param.guidePercent;
            this.layout.guideBegin = param.guideBegin;
            this.layout.guideEnd = param.guideEnd;
            this.layout.mWidth = param.width;
            this.layout.mHeight = param.height;
            this.layout.leftMargin = param.leftMargin;
            this.layout.rightMargin = param.rightMargin;
            this.layout.topMargin = param.topMargin;
            this.layout.bottomMargin = param.bottomMargin;
            this.layout.verticalWeight = param.verticalWeight;
            this.layout.horizontalWeight = param.horizontalWeight;
            this.layout.verticalChainStyle = param.verticalChainStyle;
            this.layout.horizontalChainStyle = param.horizontalChainStyle;
            this.layout.constrainedWidth = param.constrainedWidth;
            this.layout.constrainedHeight = param.constrainedHeight;
            this.layout.widthDefault = param.matchConstraintDefaultWidth;
            this.layout.heightDefault = param.matchConstraintDefaultHeight;
            this.layout.widthMax = param.matchConstraintMaxWidth;
            this.layout.heightMax = param.matchConstraintMaxHeight;
            this.layout.widthMin = param.matchConstraintMinWidth;
            this.layout.heightMin = param.matchConstraintMinHeight;
            this.layout.widthPercent = param.matchConstraintPercentWidth;
            this.layout.heightPercent = param.matchConstraintPercentHeight;
            this.layout.mConstraintTag = param.constraintTag;
            this.layout.goneTopMargin = param.goneTopMargin;
            this.layout.goneBottomMargin = param.goneBottomMargin;
            this.layout.goneLeftMargin = param.goneLeftMargin;
            this.layout.goneRightMargin = param.goneRightMargin;
            this.layout.goneStartMargin = param.goneStartMargin;
            this.layout.goneEndMargin = param.goneEndMargin;
            if (Build.VERSION.SDK_INT >= 17) {
                this.layout.endMargin = param.getMarginEnd();
                this.layout.startMargin = param.getMarginStart();
            }
        }

        public void applyTo(ConstraintLayout.LayoutParams param) {
            param.leftToLeft = this.layout.leftToLeft;
            param.leftToRight = this.layout.leftToRight;
            param.rightToLeft = this.layout.rightToLeft;
            param.rightToRight = this.layout.rightToRight;
            param.topToTop = this.layout.topToTop;
            param.topToBottom = this.layout.topToBottom;
            param.bottomToTop = this.layout.bottomToTop;
            param.bottomToBottom = this.layout.bottomToBottom;
            param.baselineToBaseline = this.layout.baselineToBaseline;
            param.startToEnd = this.layout.startToEnd;
            param.startToStart = this.layout.startToStart;
            param.endToStart = this.layout.endToStart;
            param.endToEnd = this.layout.endToEnd;
            param.leftMargin = this.layout.leftMargin;
            param.rightMargin = this.layout.rightMargin;
            param.topMargin = this.layout.topMargin;
            param.bottomMargin = this.layout.bottomMargin;
            param.goneStartMargin = this.layout.goneStartMargin;
            param.goneEndMargin = this.layout.goneEndMargin;
            param.goneTopMargin = this.layout.goneTopMargin;
            param.goneBottomMargin = this.layout.goneBottomMargin;
            param.horizontalBias = this.layout.horizontalBias;
            param.verticalBias = this.layout.verticalBias;
            param.circleConstraint = this.layout.circleConstraint;
            param.circleRadius = this.layout.circleRadius;
            param.circleAngle = this.layout.circleAngle;
            param.dimensionRatio = this.layout.dimensionRatio;
            param.editorAbsoluteX = this.layout.editorAbsoluteX;
            param.editorAbsoluteY = this.layout.editorAbsoluteY;
            param.verticalWeight = this.layout.verticalWeight;
            param.horizontalWeight = this.layout.horizontalWeight;
            param.verticalChainStyle = this.layout.verticalChainStyle;
            param.horizontalChainStyle = this.layout.horizontalChainStyle;
            param.constrainedWidth = this.layout.constrainedWidth;
            param.constrainedHeight = this.layout.constrainedHeight;
            param.matchConstraintDefaultWidth = this.layout.widthDefault;
            param.matchConstraintDefaultHeight = this.layout.heightDefault;
            param.matchConstraintMaxWidth = this.layout.widthMax;
            param.matchConstraintMaxHeight = this.layout.heightMax;
            param.matchConstraintMinWidth = this.layout.widthMin;
            param.matchConstraintMinHeight = this.layout.heightMin;
            param.matchConstraintPercentWidth = this.layout.widthPercent;
            param.matchConstraintPercentHeight = this.layout.heightPercent;
            param.orientation = this.layout.orientation;
            param.guidePercent = this.layout.guidePercent;
            param.guideBegin = this.layout.guideBegin;
            param.guideEnd = this.layout.guideEnd;
            param.width = this.layout.mWidth;
            param.height = this.layout.mHeight;
            if (this.layout.mConstraintTag != null) {
                param.constraintTag = this.layout.mConstraintTag;
            }
            if (Build.VERSION.SDK_INT >= 17) {
                param.setMarginStart(this.layout.startMargin);
                param.setMarginEnd(this.layout.endMargin);
            }
            param.validate();
        }
    }

    public void clone(Context context, int constraintLayoutId) {
        clone((ConstraintLayout) LayoutInflater.from(context).inflate(constraintLayoutId, (ViewGroup) null));
    }

    public void clone(ConstraintSet set) {
        this.mConstraints.clear();
        for (Integer key : set.mConstraints.keySet()) {
            this.mConstraints.put(key, set.mConstraints.get(key).clone());
        }
    }

    public void clone(ConstraintLayout constraintLayout) {
        int count = constraintLayout.getChildCount();
        this.mConstraints.clear();
        int i = 0;
        while (i < count) {
            View view = constraintLayout.getChildAt(i);
            ConstraintLayout.LayoutParams param = (ConstraintLayout.LayoutParams) view.getLayoutParams();
            int id = view.getId();
            if (!this.mForceId || id != -1) {
                if (!this.mConstraints.containsKey(Integer.valueOf(id))) {
                    this.mConstraints.put(Integer.valueOf(id), new Constraint());
                }
                Constraint constraint = this.mConstraints.get(Integer.valueOf(id));
                constraint.mCustomConstraints = ConstraintAttribute.extractAttributes(this.mSavedAttributes, view);
                constraint.fillFrom(id, param);
                constraint.propertySet.visibility = view.getVisibility();
                if (Build.VERSION.SDK_INT >= 17) {
                    constraint.propertySet.alpha = view.getAlpha();
                    constraint.transform.rotation = view.getRotation();
                    constraint.transform.rotationX = view.getRotationX();
                    constraint.transform.rotationY = view.getRotationY();
                    constraint.transform.scaleX = view.getScaleX();
                    constraint.transform.scaleY = view.getScaleY();
                    float pivotX = view.getPivotX();
                    float pivotY = view.getPivotY();
                    if (!(((double) pivotX) == 0.0d && ((double) pivotY) == 0.0d)) {
                        constraint.transform.transformPivotX = pivotX;
                        constraint.transform.transformPivotY = pivotY;
                    }
                    constraint.transform.translationX = view.getTranslationX();
                    constraint.transform.translationY = view.getTranslationY();
                    if (Build.VERSION.SDK_INT >= 21) {
                        constraint.transform.translationZ = view.getTranslationZ();
                        if (constraint.transform.applyElevation) {
                            constraint.transform.elevation = view.getElevation();
                        }
                    }
                }
                if (view instanceof Barrier) {
                    Barrier barrier = (Barrier) view;
                    constraint.layout.mBarrierAllowsGoneWidgets = barrier.allowsGoneWidget();
                    constraint.layout.mReferenceIds = barrier.getReferencedIds();
                    constraint.layout.mBarrierDirection = barrier.getType();
                    constraint.layout.mBarrierMargin = barrier.getMargin();
                }
                i++;
            } else {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
        }
    }

    public void clone(Constraints constraints) {
        int count = constraints.getChildCount();
        this.mConstraints.clear();
        int i = 0;
        while (i < count) {
            View view = constraints.getChildAt(i);
            Constraints.LayoutParams param = (Constraints.LayoutParams) view.getLayoutParams();
            int id = view.getId();
            if (!this.mForceId || id != -1) {
                if (!this.mConstraints.containsKey(Integer.valueOf(id))) {
                    this.mConstraints.put(Integer.valueOf(id), new Constraint());
                }
                Constraint constraint = this.mConstraints.get(Integer.valueOf(id));
                if (view instanceof ConstraintHelper) {
                    constraint.fillFromConstraints((ConstraintHelper) view, id, param);
                }
                constraint.fillFromConstraints(id, param);
                i++;
            } else {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
        }
    }

    public void applyTo(ConstraintLayout constraintLayout) {
        applyToInternal(constraintLayout, true);
        constraintLayout.setConstraintSet((ConstraintSet) null);
        constraintLayout.requestLayout();
    }

    public void applyToWithoutCustom(ConstraintLayout constraintLayout) {
        applyToInternal(constraintLayout, false);
        constraintLayout.setConstraintSet((ConstraintSet) null);
    }

    public void applyCustomAttributes(ConstraintLayout constraintLayout) {
        int count = constraintLayout.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = constraintLayout.getChildAt(i);
            int id = view.getId();
            if (!this.mConstraints.containsKey(Integer.valueOf(id))) {
                Log.v(TAG, "id unknown " + Debug.getName(view));
            } else if (this.mForceId && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            } else if (this.mConstraints.containsKey(Integer.valueOf(id))) {
                ConstraintAttribute.setAttributes(view, this.mConstraints.get(Integer.valueOf(id)).mCustomConstraints);
            }
        }
    }

    public void applyToHelper(ConstraintHelper helper, ConstraintWidget child, ConstraintLayout.LayoutParams layoutParams, SparseArray<ConstraintWidget> mapIdToWidget) {
        int id = helper.getId();
        if (this.mConstraints.containsKey(Integer.valueOf(id))) {
            Constraint constraint = this.mConstraints.get(Integer.valueOf(id));
            if (child instanceof HelperWidget) {
                helper.loadParameters(constraint, (HelperWidget) child, layoutParams, mapIdToWidget);
            }
        }
    }

    public void applyToLayoutParams(int id, ConstraintLayout.LayoutParams layoutParams) {
        if (this.mConstraints.containsKey(Integer.valueOf(id))) {
            this.mConstraints.get(Integer.valueOf(id)).applyTo(layoutParams);
        }
    }

    /* access modifiers changed from: package-private */
    public void applyToInternal(ConstraintLayout constraintLayout, boolean applyPostLayout) {
        int count = constraintLayout.getChildCount();
        HashSet<Integer> used = new HashSet<>(this.mConstraints.keySet());
        for (int i = 0; i < count; i++) {
            View view = constraintLayout.getChildAt(i);
            int id = view.getId();
            if (!this.mConstraints.containsKey(Integer.valueOf(id))) {
                Log.w(TAG, "id unknown " + Debug.getName(view));
            } else if (this.mForceId && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            } else if (id != -1) {
                if (this.mConstraints.containsKey(Integer.valueOf(id))) {
                    used.remove(Integer.valueOf(id));
                    Constraint constraint = this.mConstraints.get(Integer.valueOf(id));
                    if (view instanceof Barrier) {
                        constraint.layout.mHelperType = 1;
                    }
                    if (constraint.layout.mHelperType != -1) {
                        switch (constraint.layout.mHelperType) {
                            case 1:
                                Barrier barrier = (Barrier) view;
                                barrier.setId(id);
                                barrier.setType(constraint.layout.mBarrierDirection);
                                barrier.setMargin(constraint.layout.mBarrierMargin);
                                barrier.setAllowsGoneWidget(constraint.layout.mBarrierAllowsGoneWidgets);
                                if (constraint.layout.mReferenceIds == null) {
                                    if (constraint.layout.mReferenceIdString != null) {
                                        constraint.layout.mReferenceIds = convertReferenceString(barrier, constraint.layout.mReferenceIdString);
                                        barrier.setReferencedIds(constraint.layout.mReferenceIds);
                                        break;
                                    }
                                } else {
                                    barrier.setReferencedIds(constraint.layout.mReferenceIds);
                                    break;
                                }
                                break;
                        }
                    }
                    ConstraintLayout.LayoutParams param = (ConstraintLayout.LayoutParams) view.getLayoutParams();
                    param.validate();
                    constraint.applyTo(param);
                    if (applyPostLayout) {
                        ConstraintAttribute.setAttributes(view, constraint.mCustomConstraints);
                    }
                    view.setLayoutParams(param);
                    if (constraint.propertySet.mVisibilityMode == 0) {
                        view.setVisibility(constraint.propertySet.visibility);
                    }
                    if (Build.VERSION.SDK_INT >= 17) {
                        view.setAlpha(constraint.propertySet.alpha);
                        view.setRotation(constraint.transform.rotation);
                        view.setRotationX(constraint.transform.rotationX);
                        view.setRotationY(constraint.transform.rotationY);
                        view.setScaleX(constraint.transform.scaleX);
                        view.setScaleY(constraint.transform.scaleY);
                        if (!Float.isNaN(constraint.transform.transformPivotX)) {
                            view.setPivotX(constraint.transform.transformPivotX);
                        }
                        if (!Float.isNaN(constraint.transform.transformPivotY)) {
                            view.setPivotY(constraint.transform.transformPivotY);
                        }
                        view.setTranslationX(constraint.transform.translationX);
                        view.setTranslationY(constraint.transform.translationY);
                        if (Build.VERSION.SDK_INT >= 21) {
                            view.setTranslationZ(constraint.transform.translationZ);
                            if (constraint.transform.applyElevation) {
                                view.setElevation(constraint.transform.elevation);
                            }
                        }
                    }
                } else {
                    Log.v(TAG, "WARNING NO CONSTRAINTS for view " + id);
                }
            }
        }
        Iterator<Integer> it = used.iterator();
        while (it.hasNext()) {
            Integer id2 = it.next();
            Constraint constraint2 = this.mConstraints.get(id2);
            if (constraint2.layout.mHelperType != -1) {
                switch (constraint2.layout.mHelperType) {
                    case 1:
                        Barrier barrier2 = new Barrier(constraintLayout.getContext());
                        barrier2.setId(id2.intValue());
                        if (constraint2.layout.mReferenceIds != null) {
                            barrier2.setReferencedIds(constraint2.layout.mReferenceIds);
                        } else if (constraint2.layout.mReferenceIdString != null) {
                            constraint2.layout.mReferenceIds = convertReferenceString(barrier2, constraint2.layout.mReferenceIdString);
                            barrier2.setReferencedIds(constraint2.layout.mReferenceIds);
                        }
                        barrier2.setType(constraint2.layout.mBarrierDirection);
                        barrier2.setMargin(constraint2.layout.mBarrierMargin);
                        ConstraintLayout.LayoutParams param2 = constraintLayout.generateDefaultLayoutParams();
                        barrier2.validateParams();
                        constraint2.applyTo(param2);
                        constraintLayout.addView(barrier2, param2);
                        break;
                }
            }
            if (constraint2.layout.mIsGuideline) {
                Guideline g = new Guideline(constraintLayout.getContext());
                g.setId(id2.intValue());
                ConstraintLayout.LayoutParams param3 = constraintLayout.generateDefaultLayoutParams();
                constraint2.applyTo(param3);
                constraintLayout.addView(g, param3);
            }
        }
    }

    public void center(int centerID, int firstID, int firstSide, int firstMargin, int secondId, int secondSide, int secondMargin, float bias) {
        int i = firstSide;
        float f = bias;
        if (firstMargin < 0) {
            throw new IllegalArgumentException("margin must be > 0");
        } else if (secondMargin < 0) {
            throw new IllegalArgumentException("margin must be > 0");
        } else if (f <= 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("bias must be between 0 and 1 inclusive");
        } else if (i == 1 || i == 2) {
            int i2 = centerID;
            connect(i2, 1, firstID, firstSide, firstMargin);
            connect(i2, 2, secondId, secondSide, secondMargin);
            this.mConstraints.get(Integer.valueOf(centerID)).layout.horizontalBias = f;
        } else if (i == 6 || i == 7) {
            int i3 = centerID;
            connect(i3, 6, firstID, firstSide, firstMargin);
            connect(i3, 7, secondId, secondSide, secondMargin);
            this.mConstraints.get(Integer.valueOf(centerID)).layout.horizontalBias = f;
        } else {
            int i4 = centerID;
            connect(i4, 3, firstID, firstSide, firstMargin);
            connect(i4, 4, secondId, secondSide, secondMargin);
            this.mConstraints.get(Integer.valueOf(centerID)).layout.verticalBias = f;
        }
    }

    public void centerHorizontally(int centerID, int leftId, int leftSide, int leftMargin, int rightId, int rightSide, int rightMargin, float bias) {
        connect(centerID, 1, leftId, leftSide, leftMargin);
        connect(centerID, 2, rightId, rightSide, rightMargin);
        this.mConstraints.get(Integer.valueOf(centerID)).layout.horizontalBias = bias;
    }

    public void centerHorizontallyRtl(int centerID, int startId, int startSide, int startMargin, int endId, int endSide, int endMargin, float bias) {
        connect(centerID, 6, startId, startSide, startMargin);
        connect(centerID, 7, endId, endSide, endMargin);
        this.mConstraints.get(Integer.valueOf(centerID)).layout.horizontalBias = bias;
    }

    public void centerVertically(int centerID, int topId, int topSide, int topMargin, int bottomId, int bottomSide, int bottomMargin, float bias) {
        connect(centerID, 3, topId, topSide, topMargin);
        connect(centerID, 4, bottomId, bottomSide, bottomMargin);
        this.mConstraints.get(Integer.valueOf(centerID)).layout.verticalBias = bias;
    }

    public void createVerticalChain(int topId, int topSide, int bottomId, int bottomSide, int[] chainIds, float[] weights, int style) {
        int[] iArr = chainIds;
        float[] fArr = weights;
        if (iArr.length < 2) {
            int i = style;
            throw new IllegalArgumentException("must have 2 or more widgets in a chain");
        } else if (fArr == null || fArr.length == iArr.length) {
            if (fArr != null) {
                get(iArr[0]).layout.verticalWeight = fArr[0];
            }
            get(iArr[0]).layout.verticalChainStyle = style;
            connect(iArr[0], 3, topId, topSide, 0);
            for (int i2 = 1; i2 < iArr.length; i2++) {
                int i3 = iArr[i2];
                connect(iArr[i2], 3, iArr[i2 - 1], 4, 0);
                connect(iArr[i2 - 1], 4, iArr[i2], 3, 0);
                if (fArr != null) {
                    get(iArr[i2]).layout.verticalWeight = fArr[i2];
                }
            }
            connect(iArr[iArr.length - 1], 4, bottomId, bottomSide, 0);
        } else {
            throw new IllegalArgumentException("must have 2 or more widgets in a chain");
        }
    }

    public void createHorizontalChain(int leftId, int leftSide, int rightId, int rightSide, int[] chainIds, float[] weights, int style) {
        createHorizontalChain(leftId, leftSide, rightId, rightSide, chainIds, weights, style, 1, 2);
    }

    public void createHorizontalChainRtl(int startId, int startSide, int endId, int endSide, int[] chainIds, float[] weights, int style) {
        createHorizontalChain(startId, startSide, endId, endSide, chainIds, weights, style, 6, 7);
    }

    private void createHorizontalChain(int leftId, int leftSide, int rightId, int rightSide, int[] chainIds, float[] weights, int style, int left, int right) {
        int[] iArr = chainIds;
        float[] fArr = weights;
        if (iArr.length < 2) {
            int i = style;
            throw new IllegalArgumentException("must have 2 or more widgets in a chain");
        } else if (fArr == null || fArr.length == iArr.length) {
            if (fArr != null) {
                get(iArr[0]).layout.horizontalWeight = fArr[0];
            }
            get(iArr[0]).layout.horizontalChainStyle = style;
            connect(iArr[0], left, leftId, leftSide, -1);
            for (int i2 = 1; i2 < iArr.length; i2++) {
                int i3 = iArr[i2];
                connect(iArr[i2], left, iArr[i2 - 1], right, -1);
                connect(iArr[i2 - 1], right, iArr[i2], left, -1);
                if (fArr != null) {
                    get(iArr[i2]).layout.horizontalWeight = fArr[i2];
                }
            }
            connect(iArr[iArr.length - 1], right, rightId, rightSide, -1);
        } else {
            throw new IllegalArgumentException("must have 2 or more widgets in a chain");
        }
    }

    public void connect(int startID, int startSide, int endID, int endSide, int margin) {
        if (!this.mConstraints.containsKey(Integer.valueOf(startID))) {
            this.mConstraints.put(Integer.valueOf(startID), new Constraint());
        }
        Constraint constraint = this.mConstraints.get(Integer.valueOf(startID));
        switch (startSide) {
            case 1:
                if (endSide == 1) {
                    constraint.layout.leftToLeft = endID;
                    constraint.layout.leftToRight = -1;
                } else if (endSide == 2) {
                    constraint.layout.leftToRight = endID;
                    constraint.layout.leftToLeft = -1;
                } else {
                    throw new IllegalArgumentException("Left to " + sideToString(endSide) + " undefined");
                }
                constraint.layout.leftMargin = margin;
                return;
            case 2:
                if (endSide == 1) {
                    constraint.layout.rightToLeft = endID;
                    constraint.layout.rightToRight = -1;
                } else if (endSide == 2) {
                    constraint.layout.rightToRight = endID;
                    constraint.layout.rightToLeft = -1;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(endSide) + " undefined");
                }
                constraint.layout.rightMargin = margin;
                return;
            case 3:
                if (endSide == 3) {
                    constraint.layout.topToTop = endID;
                    constraint.layout.topToBottom = -1;
                    constraint.layout.baselineToBaseline = -1;
                } else if (endSide == 4) {
                    constraint.layout.topToBottom = endID;
                    constraint.layout.topToTop = -1;
                    constraint.layout.baselineToBaseline = -1;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(endSide) + " undefined");
                }
                constraint.layout.topMargin = margin;
                return;
            case 4:
                if (endSide == 4) {
                    constraint.layout.bottomToBottom = endID;
                    constraint.layout.bottomToTop = -1;
                    constraint.layout.baselineToBaseline = -1;
                } else if (endSide == 3) {
                    constraint.layout.bottomToTop = endID;
                    constraint.layout.bottomToBottom = -1;
                    constraint.layout.baselineToBaseline = -1;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(endSide) + " undefined");
                }
                constraint.layout.bottomMargin = margin;
                return;
            case 5:
                if (endSide == 5) {
                    constraint.layout.baselineToBaseline = endID;
                    constraint.layout.bottomToBottom = -1;
                    constraint.layout.bottomToTop = -1;
                    constraint.layout.topToTop = -1;
                    constraint.layout.topToBottom = -1;
                    return;
                }
                throw new IllegalArgumentException("right to " + sideToString(endSide) + " undefined");
            case 6:
                if (endSide == 6) {
                    constraint.layout.startToStart = endID;
                    constraint.layout.startToEnd = -1;
                } else if (endSide == 7) {
                    constraint.layout.startToEnd = endID;
                    constraint.layout.startToStart = -1;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(endSide) + " undefined");
                }
                constraint.layout.startMargin = margin;
                return;
            case 7:
                if (endSide == 7) {
                    constraint.layout.endToEnd = endID;
                    constraint.layout.endToStart = -1;
                } else if (endSide == 6) {
                    constraint.layout.endToStart = endID;
                    constraint.layout.endToEnd = -1;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(endSide) + " undefined");
                }
                constraint.layout.endMargin = margin;
                return;
            default:
                throw new IllegalArgumentException(sideToString(startSide) + " to " + sideToString(endSide) + " unknown");
        }
    }

    public void connect(int startID, int startSide, int endID, int endSide) {
        if (!this.mConstraints.containsKey(Integer.valueOf(startID))) {
            this.mConstraints.put(Integer.valueOf(startID), new Constraint());
        }
        Constraint constraint = this.mConstraints.get(Integer.valueOf(startID));
        switch (startSide) {
            case 1:
                if (endSide == 1) {
                    constraint.layout.leftToLeft = endID;
                    constraint.layout.leftToRight = -1;
                    return;
                } else if (endSide == 2) {
                    constraint.layout.leftToRight = endID;
                    constraint.layout.leftToLeft = -1;
                    return;
                } else {
                    throw new IllegalArgumentException("left to " + sideToString(endSide) + " undefined");
                }
            case 2:
                if (endSide == 1) {
                    constraint.layout.rightToLeft = endID;
                    constraint.layout.rightToRight = -1;
                    return;
                } else if (endSide == 2) {
                    constraint.layout.rightToRight = endID;
                    constraint.layout.rightToLeft = -1;
                    return;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(endSide) + " undefined");
                }
            case 3:
                if (endSide == 3) {
                    constraint.layout.topToTop = endID;
                    constraint.layout.topToBottom = -1;
                    constraint.layout.baselineToBaseline = -1;
                    return;
                } else if (endSide == 4) {
                    constraint.layout.topToBottom = endID;
                    constraint.layout.topToTop = -1;
                    constraint.layout.baselineToBaseline = -1;
                    return;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(endSide) + " undefined");
                }
            case 4:
                if (endSide == 4) {
                    constraint.layout.bottomToBottom = endID;
                    constraint.layout.bottomToTop = -1;
                    constraint.layout.baselineToBaseline = -1;
                    return;
                } else if (endSide == 3) {
                    constraint.layout.bottomToTop = endID;
                    constraint.layout.bottomToBottom = -1;
                    constraint.layout.baselineToBaseline = -1;
                    return;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(endSide) + " undefined");
                }
            case 5:
                if (endSide == 5) {
                    constraint.layout.baselineToBaseline = endID;
                    constraint.layout.bottomToBottom = -1;
                    constraint.layout.bottomToTop = -1;
                    constraint.layout.topToTop = -1;
                    constraint.layout.topToBottom = -1;
                    return;
                }
                throw new IllegalArgumentException("right to " + sideToString(endSide) + " undefined");
            case 6:
                if (endSide == 6) {
                    constraint.layout.startToStart = endID;
                    constraint.layout.startToEnd = -1;
                    return;
                } else if (endSide == 7) {
                    constraint.layout.startToEnd = endID;
                    constraint.layout.startToStart = -1;
                    return;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(endSide) + " undefined");
                }
            case 7:
                if (endSide == 7) {
                    constraint.layout.endToEnd = endID;
                    constraint.layout.endToStart = -1;
                    return;
                } else if (endSide == 6) {
                    constraint.layout.endToStart = endID;
                    constraint.layout.endToEnd = -1;
                    return;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(endSide) + " undefined");
                }
            default:
                throw new IllegalArgumentException(sideToString(startSide) + " to " + sideToString(endSide) + " unknown");
        }
    }

    public void centerHorizontally(int viewId, int toView) {
        if (toView == 0) {
            center(viewId, 0, 1, 0, 0, 2, 0, 0.5f);
        } else {
            center(viewId, toView, 2, 0, toView, 1, 0, 0.5f);
        }
    }

    public void centerHorizontallyRtl(int viewId, int toView) {
        if (toView == 0) {
            center(viewId, 0, 6, 0, 0, 7, 0, 0.5f);
        } else {
            center(viewId, toView, 7, 0, toView, 6, 0, 0.5f);
        }
    }

    public void centerVertically(int viewId, int toView) {
        if (toView == 0) {
            center(viewId, 0, 3, 0, 0, 4, 0, 0.5f);
        } else {
            center(viewId, toView, 4, 0, toView, 3, 0, 0.5f);
        }
    }

    public void clear(int viewId) {
        this.mConstraints.remove(Integer.valueOf(viewId));
    }

    public void clear(int viewId, int anchor) {
        if (this.mConstraints.containsKey(Integer.valueOf(viewId))) {
            Constraint constraint = this.mConstraints.get(Integer.valueOf(viewId));
            switch (anchor) {
                case 1:
                    constraint.layout.leftToRight = -1;
                    constraint.layout.leftToLeft = -1;
                    constraint.layout.leftMargin = -1;
                    constraint.layout.goneLeftMargin = -1;
                    return;
                case 2:
                    constraint.layout.rightToRight = -1;
                    constraint.layout.rightToLeft = -1;
                    constraint.layout.rightMargin = -1;
                    constraint.layout.goneRightMargin = -1;
                    return;
                case 3:
                    constraint.layout.topToBottom = -1;
                    constraint.layout.topToTop = -1;
                    constraint.layout.topMargin = -1;
                    constraint.layout.goneTopMargin = -1;
                    return;
                case 4:
                    constraint.layout.bottomToTop = -1;
                    constraint.layout.bottomToBottom = -1;
                    constraint.layout.bottomMargin = -1;
                    constraint.layout.goneBottomMargin = -1;
                    return;
                case 5:
                    constraint.layout.baselineToBaseline = -1;
                    return;
                case 6:
                    constraint.layout.startToEnd = -1;
                    constraint.layout.startToStart = -1;
                    constraint.layout.startMargin = -1;
                    constraint.layout.goneStartMargin = -1;
                    return;
                case 7:
                    constraint.layout.endToStart = -1;
                    constraint.layout.endToEnd = -1;
                    constraint.layout.endMargin = -1;
                    constraint.layout.goneEndMargin = -1;
                    return;
                default:
                    throw new IllegalArgumentException("unknown constraint");
            }
        }
    }

    public void setMargin(int viewId, int anchor, int value) {
        Constraint constraint = get(viewId);
        switch (anchor) {
            case 1:
                constraint.layout.leftMargin = value;
                return;
            case 2:
                constraint.layout.rightMargin = value;
                return;
            case 3:
                constraint.layout.topMargin = value;
                return;
            case 4:
                constraint.layout.bottomMargin = value;
                return;
            case 5:
                throw new IllegalArgumentException("baseline does not support margins");
            case 6:
                constraint.layout.startMargin = value;
                return;
            case 7:
                constraint.layout.endMargin = value;
                return;
            default:
                throw new IllegalArgumentException("unknown constraint");
        }
    }

    public void setGoneMargin(int viewId, int anchor, int value) {
        Constraint constraint = get(viewId);
        switch (anchor) {
            case 1:
                constraint.layout.goneLeftMargin = value;
                return;
            case 2:
                constraint.layout.goneRightMargin = value;
                return;
            case 3:
                constraint.layout.goneTopMargin = value;
                return;
            case 4:
                constraint.layout.goneBottomMargin = value;
                return;
            case 5:
                throw new IllegalArgumentException("baseline does not support margins");
            case 6:
                constraint.layout.goneStartMargin = value;
                return;
            case 7:
                constraint.layout.goneEndMargin = value;
                return;
            default:
                throw new IllegalArgumentException("unknown constraint");
        }
    }

    public void setHorizontalBias(int viewId, float bias) {
        get(viewId).layout.horizontalBias = bias;
    }

    public void setVerticalBias(int viewId, float bias) {
        get(viewId).layout.verticalBias = bias;
    }

    public void setDimensionRatio(int viewId, String ratio) {
        get(viewId).layout.dimensionRatio = ratio;
    }

    public void setVisibility(int viewId, int visibility) {
        get(viewId).propertySet.visibility = visibility;
    }

    public void setVisibilityMode(int viewId, int visibilityMode) {
        get(viewId).propertySet.mVisibilityMode = visibilityMode;
    }

    public int getVisibilityMode(int viewId) {
        return get(viewId).propertySet.mVisibilityMode;
    }

    public int getVisibility(int viewId) {
        return get(viewId).propertySet.visibility;
    }

    public int getHeight(int viewId) {
        return get(viewId).layout.mHeight;
    }

    public int getWidth(int viewId) {
        return get(viewId).layout.mWidth;
    }

    public void setAlpha(int viewId, float alpha) {
        get(viewId).propertySet.alpha = alpha;
    }

    public boolean getApplyElevation(int viewId) {
        return get(viewId).transform.applyElevation;
    }

    public void setApplyElevation(int viewId, boolean apply) {
        if (Build.VERSION.SDK_INT >= 21) {
            get(viewId).transform.applyElevation = apply;
        }
    }

    public void setElevation(int viewId, float elevation) {
        if (Build.VERSION.SDK_INT >= 21) {
            get(viewId).transform.elevation = elevation;
            get(viewId).transform.applyElevation = true;
        }
    }

    public void setRotation(int viewId, float rotation) {
        get(viewId).transform.rotation = rotation;
    }

    public void setRotationX(int viewId, float rotationX) {
        get(viewId).transform.rotationX = rotationX;
    }

    public void setRotationY(int viewId, float rotationY) {
        get(viewId).transform.rotationY = rotationY;
    }

    public void setScaleX(int viewId, float scaleX) {
        get(viewId).transform.scaleX = scaleX;
    }

    public void setScaleY(int viewId, float scaleY) {
        get(viewId).transform.scaleY = scaleY;
    }

    public void setTransformPivotX(int viewId, float transformPivotX) {
        get(viewId).transform.transformPivotX = transformPivotX;
    }

    public void setTransformPivotY(int viewId, float transformPivotY) {
        get(viewId).transform.transformPivotY = transformPivotY;
    }

    public void setTransformPivot(int viewId, float transformPivotX, float transformPivotY) {
        Constraint constraint = get(viewId);
        constraint.transform.transformPivotY = transformPivotY;
        constraint.transform.transformPivotX = transformPivotX;
    }

    public void setTranslationX(int viewId, float translationX) {
        get(viewId).transform.translationX = translationX;
    }

    public void setTranslationY(int viewId, float translationY) {
        get(viewId).transform.translationY = translationY;
    }

    public void setTranslation(int viewId, float translationX, float translationY) {
        Constraint constraint = get(viewId);
        constraint.transform.translationX = translationX;
        constraint.transform.translationY = translationY;
    }

    public void setTranslationZ(int viewId, float translationZ) {
        if (Build.VERSION.SDK_INT >= 21) {
            get(viewId).transform.translationZ = translationZ;
        }
    }

    public void setEditorAbsoluteX(int viewId, int position) {
        get(viewId).layout.editorAbsoluteX = position;
    }

    public void setEditorAbsoluteY(int viewId, int position) {
        get(viewId).layout.editorAbsoluteY = position;
    }

    public void constrainHeight(int viewId, int height) {
        get(viewId).layout.mHeight = height;
    }

    public void constrainWidth(int viewId, int width) {
        get(viewId).layout.mWidth = width;
    }

    public void constrainCircle(int viewId, int id, int radius, float angle) {
        Constraint constraint = get(viewId);
        constraint.layout.circleConstraint = id;
        constraint.layout.circleRadius = radius;
        constraint.layout.circleAngle = angle;
    }

    public void constrainMaxHeight(int viewId, int height) {
        get(viewId).layout.heightMax = height;
    }

    public void constrainMaxWidth(int viewId, int width) {
        get(viewId).layout.widthMax = width;
    }

    public void constrainMinHeight(int viewId, int height) {
        get(viewId).layout.heightMin = height;
    }

    public void constrainMinWidth(int viewId, int width) {
        get(viewId).layout.widthMin = width;
    }

    public void constrainPercentWidth(int viewId, float percent) {
        get(viewId).layout.widthPercent = percent;
    }

    public void constrainPercentHeight(int viewId, float percent) {
        get(viewId).layout.heightPercent = percent;
    }

    public void constrainDefaultHeight(int viewId, int height) {
        get(viewId).layout.heightDefault = height;
    }

    public void constrainedWidth(int viewId, boolean constrained) {
        get(viewId).layout.constrainedWidth = constrained;
    }

    public void constrainedHeight(int viewId, boolean constrained) {
        get(viewId).layout.constrainedHeight = constrained;
    }

    public void constrainDefaultWidth(int viewId, int width) {
        get(viewId).layout.widthDefault = width;
    }

    public void setHorizontalWeight(int viewId, float weight) {
        get(viewId).layout.horizontalWeight = weight;
    }

    public void setVerticalWeight(int viewId, float weight) {
        get(viewId).layout.verticalWeight = weight;
    }

    public void setHorizontalChainStyle(int viewId, int chainStyle) {
        get(viewId).layout.horizontalChainStyle = chainStyle;
    }

    public void setVerticalChainStyle(int viewId, int chainStyle) {
        get(viewId).layout.verticalChainStyle = chainStyle;
    }

    public void addToHorizontalChain(int viewId, int leftId, int rightId) {
        connect(viewId, 1, leftId, leftId == 0 ? 1 : 2, 0);
        connect(viewId, 2, rightId, rightId == 0 ? 2 : 1, 0);
        if (leftId != 0) {
            connect(leftId, 2, viewId, 1, 0);
        }
        if (rightId != 0) {
            connect(rightId, 1, viewId, 2, 0);
        }
    }

    public void addToHorizontalChainRTL(int viewId, int leftId, int rightId) {
        connect(viewId, 6, leftId, leftId == 0 ? 6 : 7, 0);
        connect(viewId, 7, rightId, rightId == 0 ? 7 : 6, 0);
        if (leftId != 0) {
            connect(leftId, 7, viewId, 6, 0);
        }
        if (rightId != 0) {
            connect(rightId, 6, viewId, 7, 0);
        }
    }

    public void addToVerticalChain(int viewId, int topId, int bottomId) {
        connect(viewId, 3, topId, topId == 0 ? 3 : 4, 0);
        connect(viewId, 4, bottomId, bottomId == 0 ? 4 : 3, 0);
        if (topId != 0) {
            connect(topId, 4, viewId, 3, 0);
        }
        if (bottomId != 0) {
            connect(bottomId, 3, viewId, 4, 0);
        }
    }

    public void removeFromVerticalChain(int viewId) {
        if (this.mConstraints.containsKey(Integer.valueOf(viewId))) {
            Constraint constraint = this.mConstraints.get(Integer.valueOf(viewId));
            int topId = constraint.layout.topToBottom;
            int bottomId = constraint.layout.bottomToTop;
            if (!(topId == -1 && bottomId == -1)) {
                if (topId != -1 && bottomId != -1) {
                    connect(topId, 4, bottomId, 3, 0);
                    connect(bottomId, 3, topId, 4, 0);
                } else if (!(topId == -1 && bottomId == -1)) {
                    if (constraint.layout.bottomToBottom != -1) {
                        connect(topId, 4, constraint.layout.bottomToBottom, 4, 0);
                    } else if (constraint.layout.topToTop != -1) {
                        connect(bottomId, 3, constraint.layout.topToTop, 3, 0);
                    }
                }
            }
        }
        clear(viewId, 3);
        clear(viewId, 4);
    }

    public void removeFromHorizontalChain(int viewId) {
        if (this.mConstraints.containsKey(Integer.valueOf(viewId))) {
            Constraint constraint = this.mConstraints.get(Integer.valueOf(viewId));
            int leftId = constraint.layout.leftToRight;
            int rightId = constraint.layout.rightToLeft;
            if (leftId == -1 && rightId == -1) {
                int startId = constraint.layout.startToEnd;
                int endId = constraint.layout.endToStart;
                if (!(startId == -1 && endId == -1)) {
                    if (startId != -1 && endId != -1) {
                        connect(startId, 7, endId, 6, 0);
                        connect(endId, 6, leftId, 7, 0);
                    } else if (!(leftId == -1 && endId == -1)) {
                        if (constraint.layout.rightToRight != -1) {
                            connect(leftId, 7, constraint.layout.rightToRight, 7, 0);
                        } else if (constraint.layout.leftToLeft != -1) {
                            connect(endId, 6, constraint.layout.leftToLeft, 6, 0);
                        }
                    }
                }
                clear(viewId, 6);
                clear(viewId, 7);
                return;
            }
            if (leftId != -1 && rightId != -1) {
                connect(leftId, 2, rightId, 1, 0);
                connect(rightId, 1, leftId, 2, 0);
            } else if (!(leftId == -1 && rightId == -1)) {
                if (constraint.layout.rightToRight != -1) {
                    connect(leftId, 2, constraint.layout.rightToRight, 2, 0);
                } else if (constraint.layout.leftToLeft != -1) {
                    connect(rightId, 1, constraint.layout.leftToLeft, 1, 0);
                }
            }
            clear(viewId, 1);
            clear(viewId, 2);
        }
    }

    public void create(int guidelineID, int orientation) {
        Constraint constraint = get(guidelineID);
        constraint.layout.mIsGuideline = true;
        constraint.layout.orientation = orientation;
    }

    public void createBarrier(int id, int direction, int margin, int... referenced) {
        Constraint constraint = get(id);
        constraint.layout.mHelperType = 1;
        constraint.layout.mBarrierDirection = direction;
        constraint.layout.mBarrierMargin = margin;
        constraint.layout.mIsGuideline = false;
        constraint.layout.mReferenceIds = referenced;
    }

    public void setGuidelineBegin(int guidelineID, int margin) {
        get(guidelineID).layout.guideBegin = margin;
        get(guidelineID).layout.guideEnd = -1;
        get(guidelineID).layout.guidePercent = -1.0f;
    }

    public void setGuidelineEnd(int guidelineID, int margin) {
        get(guidelineID).layout.guideEnd = margin;
        get(guidelineID).layout.guideBegin = -1;
        get(guidelineID).layout.guidePercent = -1.0f;
    }

    public void setGuidelinePercent(int guidelineID, float ratio) {
        get(guidelineID).layout.guidePercent = ratio;
        get(guidelineID).layout.guideEnd = -1;
        get(guidelineID).layout.guideBegin = -1;
    }

    public int[] getReferencedIds(int id) {
        Constraint constraint = get(id);
        if (constraint.layout.mReferenceIds == null) {
            return new int[0];
        }
        return Arrays.copyOf(constraint.layout.mReferenceIds, constraint.layout.mReferenceIds.length);
    }

    public void setReferencedIds(int id, int... referenced) {
        get(id).layout.mReferenceIds = referenced;
    }

    public void setBarrierType(int id, int type) {
        get(id).layout.mHelperType = type;
    }

    public void removeAttribute(String attributeName) {
        this.mSavedAttributes.remove(attributeName);
    }

    public void setIntValue(int viewId, String attributeName, int value) {
        get(viewId).setIntValue(attributeName, value);
    }

    public void setColorValue(int viewId, String attributeName, int value) {
        get(viewId).setColorValue(attributeName, value);
    }

    public void setFloatValue(int viewId, String attributeName, float value) {
        get(viewId).setFloatValue(attributeName, value);
    }

    public void setStringValue(int viewId, String attributeName, String value) {
        get(viewId).setStringValue(attributeName, value);
    }

    private void addAttributes(ConstraintAttribute.AttributeType attributeType, String... attributeName) {
        for (int i = 0; i < attributeName.length; i++) {
            if (this.mSavedAttributes.containsKey(attributeName[i])) {
                ConstraintAttribute constraintAttribute = this.mSavedAttributes.get(attributeName[i]);
                if (constraintAttribute.getType() != attributeType) {
                    throw new IllegalArgumentException("ConstraintAttribute is already a " + constraintAttribute.getType().name());
                }
            } else {
                this.mSavedAttributes.put(attributeName[i], new ConstraintAttribute(attributeName[i], attributeType));
            }
        }
    }

    public void parseIntAttributes(Constraint set, String attributes) {
        String[] sp = attributes.split(",");
        for (int i = 0; i < sp.length; i++) {
            String[] attr = sp[i].split("=");
            if (attr.length != 2) {
                Log.w(TAG, " Unable to parse " + sp[i]);
            } else {
                set.setFloatValue(attr[0], (float) Integer.decode(attr[1]).intValue());
            }
        }
    }

    public void parseColorAttributes(Constraint set, String attributes) {
        String[] sp = attributes.split(",");
        for (int i = 0; i < sp.length; i++) {
            String[] attr = sp[i].split("=");
            if (attr.length != 2) {
                Log.w(TAG, " Unable to parse " + sp[i]);
            } else {
                set.setColorValue(attr[0], Color.parseColor(attr[1]));
            }
        }
    }

    public void parseFloatAttributes(Constraint set, String attributes) {
        String[] sp = attributes.split(",");
        for (int i = 0; i < sp.length; i++) {
            String[] attr = sp[i].split("=");
            if (attr.length != 2) {
                Log.w(TAG, " Unable to parse " + sp[i]);
            } else {
                set.setFloatValue(attr[0], Float.parseFloat(attr[1]));
            }
        }
    }

    public void parseStringAttributes(Constraint set, String attributes) {
        String[] sp = splitString(attributes);
        for (int i = 0; i < sp.length; i++) {
            String[] attr = sp[i].split("=");
            Log.w(TAG, " Unable to parse " + sp[i]);
            set.setStringValue(attr[0], attr[1]);
        }
    }

    private static String[] splitString(String str) {
        char[] chars = str.toCharArray();
        ArrayList<String> list = new ArrayList<>();
        boolean indouble = false;
        int start = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ',' && !indouble) {
                list.add(new String(chars, start, i - start));
                start = i + 1;
            } else if (chars[i] == '\"') {
                indouble = !indouble;
            }
        }
        list.add(new String(chars, start, chars.length - start));
        return (String[]) list.toArray(new String[list.size()]);
    }

    public void addIntAttributes(String... attributeName) {
        addAttributes(ConstraintAttribute.AttributeType.INT_TYPE, attributeName);
    }

    public void addColorAttributes(String... attributeName) {
        addAttributes(ConstraintAttribute.AttributeType.COLOR_TYPE, attributeName);
    }

    public void addFloatAttributes(String... attributeName) {
        addAttributes(ConstraintAttribute.AttributeType.FLOAT_TYPE, attributeName);
    }

    public void addStringAttributes(String... attributeName) {
        addAttributes(ConstraintAttribute.AttributeType.STRING_TYPE, attributeName);
    }

    private Constraint get(int id) {
        if (!this.mConstraints.containsKey(Integer.valueOf(id))) {
            this.mConstraints.put(Integer.valueOf(id), new Constraint());
        }
        return this.mConstraints.get(Integer.valueOf(id));
    }

    private String sideToString(int side) {
        switch (side) {
            case 1:
                return "left";
            case 2:
                return "right";
            case 3:
                return "top";
            case 4:
                return "bottom";
            case 5:
                return "baseline";
            case 6:
                return "start";
            case 7:
                return "end";
            default:
                return "undefined";
        }
    }

    public void load(Context context, int resourceId) {
        XmlPullParser parser = context.getResources().getXml(resourceId);
        try {
            for (int eventType = parser.getEventType(); eventType != 1; eventType = parser.next()) {
                switch (eventType) {
                    case 0:
                        String document = parser.getName();
                        break;
                    case 2:
                        String tagName = parser.getName();
                        Constraint constraint = fillFromAttributeList(context, Xml.asAttributeSet(parser));
                        if (tagName.equalsIgnoreCase("Guideline")) {
                            constraint.layout.mIsGuideline = true;
                        }
                        this.mConstraints.put(Integer.valueOf(constraint.mViewId), constraint);
                        break;
                    case 3:
                        break;
                }
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void load(android.content.Context r8, org.xmlpull.v1.XmlPullParser r9) {
        /*
            r7 = this;
            r0 = 0
            r1 = 0
            int r2 = r9.getEventType()     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
        L_0x0006:
            r3 = 1
            if (r2 == r3) goto L_0x018e
            java.lang.String r4 = "Constraint"
            switch(r2) {
                case 0: goto L_0x0182;
                case 1: goto L_0x000e;
                case 2: goto L_0x0033;
                case 3: goto L_0x0010;
                default: goto L_0x000e;
            }
        L_0x000e:
            goto L_0x0187
        L_0x0010:
            java.lang.String r3 = r9.getName()     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            r0 = r3
            java.lang.String r3 = "ConstraintSet"
            boolean r3 = r3.equals(r0)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            if (r3 == 0) goto L_0x001e
            return
        L_0x001e:
            boolean r3 = r0.equalsIgnoreCase(r4)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            if (r3 == 0) goto L_0x0030
            java.util.HashMap<java.lang.Integer, android.support.constraint.ConstraintSet$Constraint> r3 = r7.mConstraints     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            int r4 = r1.mViewId     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            r3.put(r4, r1)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            r1 = 0
        L_0x0030:
            r0 = 0
            goto L_0x0187
        L_0x0033:
            java.lang.String r5 = r9.getName()     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            r0 = r5
            r5 = -1
            int r6 = r0.hashCode()     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            switch(r6) {
                case -2025855158: goto L_0x0085;
                case -1984451626: goto L_0x007b;
                case -1269513683: goto L_0x0071;
                case -1238332596: goto L_0x0067;
                case -71750448: goto L_0x005d;
                case 1331510167: goto L_0x0053;
                case 1791837707: goto L_0x0049;
                case 1803088381: goto L_0x0041;
                default: goto L_0x0040;
            }     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
        L_0x0040:
            goto L_0x008e
        L_0x0041:
            boolean r4 = r0.equals(r4)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            if (r4 == 0) goto L_0x0040
            r5 = 0
            goto L_0x008e
        L_0x0049:
            java.lang.String r4 = "CustomAttribute"
            boolean r4 = r0.equals(r4)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            if (r4 == 0) goto L_0x0040
            r5 = 7
            goto L_0x008e
        L_0x0053:
            java.lang.String r4 = "Barrier"
            boolean r4 = r0.equals(r4)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            if (r4 == 0) goto L_0x0040
            r5 = 2
            goto L_0x008e
        L_0x005d:
            java.lang.String r4 = "Guideline"
            boolean r4 = r0.equals(r4)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            if (r4 == 0) goto L_0x0040
            r5 = r3
            goto L_0x008e
        L_0x0067:
            java.lang.String r4 = "Transform"
            boolean r4 = r0.equals(r4)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            if (r4 == 0) goto L_0x0040
            r5 = 4
            goto L_0x008e
        L_0x0071:
            java.lang.String r4 = "PropertySet"
            boolean r4 = r0.equals(r4)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            if (r4 == 0) goto L_0x0040
            r5 = 3
            goto L_0x008e
        L_0x007b:
            java.lang.String r4 = "Motion"
            boolean r4 = r0.equals(r4)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            if (r4 == 0) goto L_0x0040
            r5 = 6
            goto L_0x008e
        L_0x0085:
            java.lang.String r4 = "Layout"
            boolean r4 = r0.equals(r4)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            if (r4 == 0) goto L_0x0040
            r5 = 5
        L_0x008e:
            java.lang.String r4 = "XML parser error must be within a Constraint "
            switch(r5) {
                case 0: goto L_0x0177;
                case 1: goto L_0x0165;
                case 2: goto L_0x0157;
                case 3: goto L_0x0130;
                case 4: goto L_0x0109;
                case 5: goto L_0x00e1;
                case 6: goto L_0x00b9;
                case 7: goto L_0x0095;
                default: goto L_0x0093;
            }
        L_0x0093:
            goto L_0x0181
        L_0x0095:
            if (r1 == 0) goto L_0x009e
            java.util.HashMap<java.lang.String, android.support.constraint.ConstraintAttribute> r3 = r1.mCustomConstraints     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            android.support.constraint.ConstraintAttribute.parse(r8, r9, r3)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            goto L_0x0181
        L_0x009e:
            java.lang.RuntimeException r3 = new java.lang.RuntimeException     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            r5.<init>()     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            java.lang.StringBuilder r4 = r5.append(r4)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            int r5 = r9.getLineNumber()     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            java.lang.String r4 = r4.toString()     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            r3.<init>(r4)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            throw r3     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
        L_0x00b9:
            if (r1 == 0) goto L_0x00c6
            android.support.constraint.ConstraintSet$Motion r3 = r1.motion     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            android.util.AttributeSet r4 = android.util.Xml.asAttributeSet(r9)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            r3.fillFromAttributeList(r8, r4)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            goto L_0x0181
        L_0x00c6:
            java.lang.RuntimeException r3 = new java.lang.RuntimeException     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            r5.<init>()     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            java.lang.StringBuilder r4 = r5.append(r4)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            int r5 = r9.getLineNumber()     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            java.lang.String r4 = r4.toString()     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            r3.<init>(r4)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            throw r3     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
        L_0x00e1:
            if (r1 == 0) goto L_0x00ee
            android.support.constraint.ConstraintSet$Layout r3 = r1.layout     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            android.util.AttributeSet r4 = android.util.Xml.asAttributeSet(r9)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            r3.fillFromAttributeList(r8, r4)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            goto L_0x0181
        L_0x00ee:
            java.lang.RuntimeException r3 = new java.lang.RuntimeException     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            r5.<init>()     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            java.lang.StringBuilder r4 = r5.append(r4)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            int r5 = r9.getLineNumber()     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            java.lang.String r4 = r4.toString()     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            r3.<init>(r4)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            throw r3     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
        L_0x0109:
            if (r1 == 0) goto L_0x0115
            android.support.constraint.ConstraintSet$Transform r3 = r1.transform     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            android.util.AttributeSet r4 = android.util.Xml.asAttributeSet(r9)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            r3.fillFromAttributeList(r8, r4)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            goto L_0x0181
        L_0x0115:
            java.lang.RuntimeException r3 = new java.lang.RuntimeException     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            r5.<init>()     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            java.lang.StringBuilder r4 = r5.append(r4)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            int r5 = r9.getLineNumber()     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            java.lang.String r4 = r4.toString()     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            r3.<init>(r4)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            throw r3     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
        L_0x0130:
            if (r1 == 0) goto L_0x013c
            android.support.constraint.ConstraintSet$PropertySet r3 = r1.propertySet     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            android.util.AttributeSet r4 = android.util.Xml.asAttributeSet(r9)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            r3.fillFromAttributeList(r8, r4)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            goto L_0x0181
        L_0x013c:
            java.lang.RuntimeException r3 = new java.lang.RuntimeException     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            r5.<init>()     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            java.lang.StringBuilder r4 = r5.append(r4)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            int r5 = r9.getLineNumber()     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            java.lang.String r4 = r4.toString()     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            r3.<init>(r4)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            throw r3     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
        L_0x0157:
            android.util.AttributeSet r4 = android.util.Xml.asAttributeSet(r9)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            android.support.constraint.ConstraintSet$Constraint r4 = r7.fillFromAttributeList(r8, r4)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            r1 = r4
            android.support.constraint.ConstraintSet$Layout r4 = r1.layout     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            r4.mHelperType = r3     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            goto L_0x0181
        L_0x0165:
            android.util.AttributeSet r4 = android.util.Xml.asAttributeSet(r9)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            android.support.constraint.ConstraintSet$Constraint r4 = r7.fillFromAttributeList(r8, r4)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            r1 = r4
            android.support.constraint.ConstraintSet$Layout r4 = r1.layout     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            r4.mIsGuideline = r3     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            android.support.constraint.ConstraintSet$Layout r4 = r1.layout     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            r4.mApply = r3     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            goto L_0x0181
        L_0x0177:
            android.util.AttributeSet r3 = android.util.Xml.asAttributeSet(r9)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            android.support.constraint.ConstraintSet$Constraint r3 = r7.fillFromAttributeList(r8, r3)     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            r1 = r3
        L_0x0181:
            goto L_0x0187
        L_0x0182:
            java.lang.String r3 = r9.getName()     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
        L_0x0187:
            int r3 = r9.next()     // Catch:{ XmlPullParserException -> 0x0194, IOException -> 0x018f }
            r2 = r3
            goto L_0x0006
        L_0x018e:
            goto L_0x0198
        L_0x018f:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0199
        L_0x0194:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0198:
        L_0x0199:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.ConstraintSet.load(android.content.Context, org.xmlpull.v1.XmlPullParser):void");
    }

    /* access modifiers changed from: private */
    public static int lookupID(TypedArray a, int index, int def) {
        int ret = a.getResourceId(index, def);
        if (ret == -1) {
            return a.getInt(index, -1);
        }
        return ret;
    }

    private Constraint fillFromAttributeList(Context context, AttributeSet attrs) {
        Constraint c = new Constraint();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Constraint);
        populateConstraint(context, c, a);
        a.recycle();
        return c;
    }

    private void populateConstraint(Context ctx, Constraint c, TypedArray a) {
        int N = a.getIndexCount();
        for (int i = 0; i < N; i++) {
            int attr = a.getIndex(i);
            if (!(attr == R.styleable.Constraint_android_id || R.styleable.Constraint_android_layout_marginStart == attr || R.styleable.Constraint_android_layout_marginEnd == attr)) {
                c.motion.mApply = true;
                c.layout.mApply = true;
                c.propertySet.mApply = true;
                c.transform.mApply = true;
            }
            switch (mapToConstant.get(attr)) {
                case 1:
                    c.layout.baselineToBaseline = lookupID(a, attr, c.layout.baselineToBaseline);
                    break;
                case 2:
                    c.layout.bottomMargin = a.getDimensionPixelSize(attr, c.layout.bottomMargin);
                    break;
                case 3:
                    c.layout.bottomToBottom = lookupID(a, attr, c.layout.bottomToBottom);
                    break;
                case 4:
                    c.layout.bottomToTop = lookupID(a, attr, c.layout.bottomToTop);
                    break;
                case 5:
                    c.layout.dimensionRatio = a.getString(attr);
                    break;
                case 6:
                    c.layout.editorAbsoluteX = a.getDimensionPixelOffset(attr, c.layout.editorAbsoluteX);
                    break;
                case 7:
                    c.layout.editorAbsoluteY = a.getDimensionPixelOffset(attr, c.layout.editorAbsoluteY);
                    break;
                case 8:
                    if (Build.VERSION.SDK_INT < 17) {
                        break;
                    } else {
                        c.layout.endMargin = a.getDimensionPixelSize(attr, c.layout.endMargin);
                        break;
                    }
                case 9:
                    c.layout.endToEnd = lookupID(a, attr, c.layout.endToEnd);
                    break;
                case 10:
                    c.layout.endToStart = lookupID(a, attr, c.layout.endToStart);
                    break;
                case 11:
                    c.layout.goneBottomMargin = a.getDimensionPixelSize(attr, c.layout.goneBottomMargin);
                    break;
                case 12:
                    c.layout.goneEndMargin = a.getDimensionPixelSize(attr, c.layout.goneEndMargin);
                    break;
                case 13:
                    c.layout.goneLeftMargin = a.getDimensionPixelSize(attr, c.layout.goneLeftMargin);
                    break;
                case 14:
                    c.layout.goneRightMargin = a.getDimensionPixelSize(attr, c.layout.goneRightMargin);
                    break;
                case 15:
                    c.layout.goneStartMargin = a.getDimensionPixelSize(attr, c.layout.goneStartMargin);
                    break;
                case 16:
                    c.layout.goneTopMargin = a.getDimensionPixelSize(attr, c.layout.goneTopMargin);
                    break;
                case 17:
                    c.layout.guideBegin = a.getDimensionPixelOffset(attr, c.layout.guideBegin);
                    break;
                case 18:
                    c.layout.guideEnd = a.getDimensionPixelOffset(attr, c.layout.guideEnd);
                    break;
                case 19:
                    c.layout.guidePercent = a.getFloat(attr, c.layout.guidePercent);
                    break;
                case 20:
                    c.layout.horizontalBias = a.getFloat(attr, c.layout.horizontalBias);
                    break;
                case 21:
                    c.layout.mHeight = a.getLayoutDimension(attr, c.layout.mHeight);
                    break;
                case 22:
                    c.propertySet.visibility = a.getInt(attr, c.propertySet.visibility);
                    c.propertySet.visibility = VISIBILITY_FLAGS[c.propertySet.visibility];
                    break;
                case 23:
                    c.layout.mWidth = a.getLayoutDimension(attr, c.layout.mWidth);
                    break;
                case 24:
                    c.layout.leftMargin = a.getDimensionPixelSize(attr, c.layout.leftMargin);
                    break;
                case 25:
                    c.layout.leftToLeft = lookupID(a, attr, c.layout.leftToLeft);
                    break;
                case 26:
                    c.layout.leftToRight = lookupID(a, attr, c.layout.leftToRight);
                    break;
                case 27:
                    c.layout.orientation = a.getInt(attr, c.layout.orientation);
                    break;
                case 28:
                    c.layout.rightMargin = a.getDimensionPixelSize(attr, c.layout.rightMargin);
                    break;
                case 29:
                    c.layout.rightToLeft = lookupID(a, attr, c.layout.rightToLeft);
                    break;
                case 30:
                    c.layout.rightToRight = lookupID(a, attr, c.layout.rightToRight);
                    break;
                case 31:
                    if (Build.VERSION.SDK_INT < 17) {
                        break;
                    } else {
                        c.layout.startMargin = a.getDimensionPixelSize(attr, c.layout.startMargin);
                        break;
                    }
                case 32:
                    c.layout.startToEnd = lookupID(a, attr, c.layout.startToEnd);
                    break;
                case 33:
                    c.layout.startToStart = lookupID(a, attr, c.layout.startToStart);
                    break;
                case 34:
                    c.layout.topMargin = a.getDimensionPixelSize(attr, c.layout.topMargin);
                    break;
                case 35:
                    c.layout.topToBottom = lookupID(a, attr, c.layout.topToBottom);
                    break;
                case 36:
                    c.layout.topToTop = lookupID(a, attr, c.layout.topToTop);
                    break;
                case 37:
                    c.layout.verticalBias = a.getFloat(attr, c.layout.verticalBias);
                    break;
                case 38:
                    c.mViewId = a.getResourceId(attr, c.mViewId);
                    break;
                case 39:
                    c.layout.horizontalWeight = a.getFloat(attr, c.layout.horizontalWeight);
                    break;
                case 40:
                    c.layout.verticalWeight = a.getFloat(attr, c.layout.verticalWeight);
                    break;
                case 41:
                    c.layout.horizontalChainStyle = a.getInt(attr, c.layout.horizontalChainStyle);
                    break;
                case 42:
                    c.layout.verticalChainStyle = a.getInt(attr, c.layout.verticalChainStyle);
                    break;
                case 43:
                    c.propertySet.alpha = a.getFloat(attr, c.propertySet.alpha);
                    break;
                case 44:
                    if (Build.VERSION.SDK_INT < 21) {
                        break;
                    } else {
                        c.transform.applyElevation = true;
                        c.transform.elevation = a.getDimension(attr, c.transform.elevation);
                        break;
                    }
                case 45:
                    c.transform.rotationX = a.getFloat(attr, c.transform.rotationX);
                    break;
                case 46:
                    c.transform.rotationY = a.getFloat(attr, c.transform.rotationY);
                    break;
                case 47:
                    c.transform.scaleX = a.getFloat(attr, c.transform.scaleX);
                    break;
                case 48:
                    c.transform.scaleY = a.getFloat(attr, c.transform.scaleY);
                    break;
                case 49:
                    c.transform.transformPivotX = a.getDimension(attr, c.transform.transformPivotX);
                    break;
                case 50:
                    c.transform.transformPivotY = a.getDimension(attr, c.transform.transformPivotY);
                    break;
                case 51:
                    c.transform.translationX = a.getDimension(attr, c.transform.translationX);
                    break;
                case 52:
                    c.transform.translationY = a.getDimension(attr, c.transform.translationY);
                    break;
                case 53:
                    if (Build.VERSION.SDK_INT < 21) {
                        break;
                    } else {
                        c.transform.translationZ = a.getDimension(attr, c.transform.translationZ);
                        break;
                    }
                case 54:
                    c.layout.widthDefault = a.getInt(attr, c.layout.widthDefault);
                    break;
                case 55:
                    c.layout.heightDefault = a.getInt(attr, c.layout.heightDefault);
                    break;
                case 56:
                    c.layout.widthMax = a.getDimensionPixelSize(attr, c.layout.widthMax);
                    break;
                case 57:
                    c.layout.heightMax = a.getDimensionPixelSize(attr, c.layout.heightMax);
                    break;
                case 58:
                    c.layout.widthMin = a.getDimensionPixelSize(attr, c.layout.widthMin);
                    break;
                case 59:
                    c.layout.heightMin = a.getDimensionPixelSize(attr, c.layout.heightMin);
                    break;
                case 60:
                    c.transform.rotation = a.getFloat(attr, c.transform.rotation);
                    break;
                case 61:
                    c.layout.circleConstraint = lookupID(a, attr, c.layout.circleConstraint);
                    break;
                case 62:
                    c.layout.circleRadius = a.getDimensionPixelSize(attr, c.layout.circleRadius);
                    break;
                case 63:
                    c.layout.circleAngle = a.getFloat(attr, c.layout.circleAngle);
                    break;
                case 64:
                    c.motion.mAnimateRelativeTo = lookupID(a, attr, c.motion.mAnimateRelativeTo);
                    break;
                case 65:
                    if (a.peekValue(attr).type != 3) {
                        c.motion.mTransitionEasing = Easing.NAMED_EASING[a.getInteger(attr, 0)];
                        break;
                    } else {
                        c.motion.mTransitionEasing = a.getString(attr);
                        break;
                    }
                case 66:
                    c.motion.mDrawPath = a.getInt(attr, 0);
                    break;
                case 67:
                    c.motion.mPathRotate = a.getFloat(attr, c.motion.mPathRotate);
                    break;
                case 68:
                    c.propertySet.mProgress = a.getFloat(attr, c.propertySet.mProgress);
                    break;
                case 69:
                    c.layout.widthPercent = a.getFloat(attr, 1.0f);
                    break;
                case 70:
                    c.layout.heightPercent = a.getFloat(attr, 1.0f);
                    break;
                case 71:
                    Log.e(TAG, "CURRENTLY UNSUPPORTED");
                    break;
                case 72:
                    c.layout.mBarrierDirection = a.getInt(attr, c.layout.mBarrierDirection);
                    break;
                case 73:
                    c.layout.mBarrierMargin = a.getDimensionPixelSize(attr, c.layout.mBarrierMargin);
                    break;
                case 74:
                    c.layout.mReferenceIdString = a.getString(attr);
                    break;
                case 75:
                    c.layout.mBarrierAllowsGoneWidgets = a.getBoolean(attr, c.layout.mBarrierAllowsGoneWidgets);
                    break;
                case 76:
                    c.motion.mPathMotionArc = a.getInt(attr, c.motion.mPathMotionArc);
                    break;
                case 77:
                    c.layout.mConstraintTag = a.getString(attr);
                    break;
                case 78:
                    c.propertySet.mVisibilityMode = a.getInt(attr, c.propertySet.mVisibilityMode);
                    break;
                case 79:
                    c.motion.mMotionStagger = a.getFloat(attr, c.motion.mMotionStagger);
                    break;
                case 80:
                    c.layout.constrainedWidth = a.getBoolean(attr, c.layout.constrainedWidth);
                    break;
                case 81:
                    c.layout.constrainedHeight = a.getBoolean(attr, c.layout.constrainedHeight);
                    break;
                case 82:
                    Log.w(TAG, "unused attribute 0x" + Integer.toHexString(attr) + "   " + mapToConstant.get(attr));
                    break;
                default:
                    Log.w(TAG, "Unknown attribute 0x" + Integer.toHexString(attr) + "   " + mapToConstant.get(attr));
                    break;
            }
        }
    }

    private int[] convertReferenceString(View view, String referenceIdString) {
        Object value;
        String[] split = referenceIdString.split(",");
        Context context = view.getContext();
        int[] tags = new int[split.length];
        int count = 0;
        int i = 0;
        while (i < split.length) {
            String idString = split[i].trim();
            int tag = 0;
            try {
                tag = R.id.class.getField(idString).getInt((Object) null);
            } catch (Exception e) {
            }
            if (tag == 0) {
                tag = context.getResources().getIdentifier(idString, "id", context.getPackageName());
            }
            if (tag == 0 && view.isInEditMode() && (view.getParent() instanceof ConstraintLayout) && (value = ((ConstraintLayout) view.getParent()).getDesignInformation(0, idString)) != null && (value instanceof Integer)) {
                tag = ((Integer) value).intValue();
            }
            tags[count] = tag;
            i++;
            count++;
        }
        if (count != split.length) {
            return Arrays.copyOf(tags, count);
        }
        return tags;
    }

    public Constraint getConstraint(int id) {
        if (this.mConstraints.containsKey(Integer.valueOf(id))) {
            return this.mConstraints.get(Integer.valueOf(id));
        }
        return null;
    }

    public int[] getKnownIds() {
        Integer[] arr = (Integer[]) this.mConstraints.keySet().toArray(new Integer[0]);
        int[] array = new int[arr.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = arr[i].intValue();
        }
        return array;
    }

    public boolean isForceId() {
        return this.mForceId;
    }

    public void setForceId(boolean forceId) {
        this.mForceId = forceId;
    }

    public void setValidateOnParse(boolean validate) {
        this.mValidate = validate;
    }

    public void dump(MotionScene scene, int... ids) {
        HashSet<Integer> set;
        Set<Integer> keys = this.mConstraints.keySet();
        if (ids.length != 0) {
            set = new HashSet<>();
            for (int id : ids) {
                set.add(Integer.valueOf(id));
            }
        } else {
            set = new HashSet<>(keys);
        }
        System.out.println(set.size() + " constraints");
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer id2 : (Integer[]) set.toArray(new Integer[0])) {
            stringBuilder.append("<Constraint id=");
            stringBuilder.append(id2);
            stringBuilder.append(" \n");
            this.mConstraints.get(id2).layout.dump(scene, stringBuilder);
            stringBuilder.append("/>\n");
        }
        System.out.println(stringBuilder.toString());
    }
}
