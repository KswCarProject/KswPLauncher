package android.support.constraint.solver.widgets;

import android.support.constraint.solver.Cache;
import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.analyzer.ChainRun;
import android.support.constraint.solver.widgets.analyzer.HorizontalWidgetRun;
import android.support.constraint.solver.widgets.analyzer.VerticalWidgetRun;
import android.support.constraint.solver.widgets.analyzer.WidgetRun;
import com.ibm.icu.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/* loaded from: classes.dex */
public class ConstraintWidget {
    public static final int ANCHOR_BASELINE = 4;
    public static final int ANCHOR_BOTTOM = 3;
    public static final int ANCHOR_LEFT = 0;
    public static final int ANCHOR_RIGHT = 1;
    public static final int ANCHOR_TOP = 2;
    private static final boolean AUTOTAG_CENTER = false;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static float DEFAULT_BIAS = 0.5f;
    static final int DIMENSION_HORIZONTAL = 0;
    static final int DIMENSION_VERTICAL = 1;
    protected static final int DIRECT = 2;
    public static final int GONE = 8;
    public static final int HORIZONTAL = 0;
    public static final int INVISIBLE = 4;
    public static final int MATCH_CONSTRAINT_PERCENT = 2;
    public static final int MATCH_CONSTRAINT_RATIO = 3;
    public static final int MATCH_CONSTRAINT_RATIO_RESOLVED = 4;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    protected static final int SOLVER = 1;
    public static final int UNKNOWN = -1;
    private static final boolean USE_WRAP_DIMENSION_FOR_SPREAD = false;
    public static final int VERTICAL = 1;
    public static final int VISIBLE = 0;
    private static final int WRAP = -2;
    private boolean hasBaseline;
    public ChainRun horizontalChainRun;
    public HorizontalWidgetRun horizontalRun;
    private boolean inPlaceholder;
    public boolean[] isTerminalWidget;
    protected ArrayList<ConstraintAnchor> mAnchors;
    ConstraintAnchor mBaseline;
    int mBaselineDistance;
    public ConstraintAnchor mBottom;
    boolean mBottomHasCentered;
    ConstraintAnchor mCenter;
    ConstraintAnchor mCenterX;
    ConstraintAnchor mCenterY;
    private float mCircleConstraintAngle;
    private Object mCompanionWidget;
    private int mContainerItemSkip;
    private String mDebugName;
    public float mDimensionRatio;
    protected int mDimensionRatioSide;
    int mDistToBottom;
    int mDistToLeft;
    int mDistToRight;
    int mDistToTop;
    boolean mGroupsToSolver;
    int mHeight;
    float mHorizontalBiasPercent;
    boolean mHorizontalChainFixedPosition;
    int mHorizontalChainStyle;
    ConstraintWidget mHorizontalNextWidget;
    public int mHorizontalResolution;
    boolean mHorizontalWrapVisited;
    private boolean mInVirtuaLayout;
    public boolean mIsHeightWrapContent;
    private boolean[] mIsInBarrier;
    public boolean mIsWidthWrapContent;
    public ConstraintAnchor mLeft;
    boolean mLeftHasCentered;
    public ConstraintAnchor[] mListAnchors;
    public DimensionBehaviour[] mListDimensionBehaviors;
    protected ConstraintWidget[] mListNextMatchConstraintsWidget;
    public int mMatchConstraintDefaultHeight;
    public int mMatchConstraintDefaultWidth;
    public int mMatchConstraintMaxHeight;
    public int mMatchConstraintMaxWidth;
    public int mMatchConstraintMinHeight;
    public int mMatchConstraintMinWidth;
    public float mMatchConstraintPercentHeight;
    public float mMatchConstraintPercentWidth;
    private int[] mMaxDimension;
    protected int mMinHeight;
    protected int mMinWidth;
    protected ConstraintWidget[] mNextChainWidget;
    protected int mOffsetX;
    protected int mOffsetY;
    boolean mOptimizerMeasurable;
    public ConstraintWidget mParent;
    int mRelX;
    int mRelY;
    float mResolvedDimensionRatio;
    int mResolvedDimensionRatioSide;
    boolean mResolvedHasRatio;
    public int[] mResolvedMatchConstraintDefault;
    public ConstraintAnchor mRight;
    boolean mRightHasCentered;
    public ConstraintAnchor mTop;
    boolean mTopHasCentered;
    private String mType;
    float mVerticalBiasPercent;
    boolean mVerticalChainFixedPosition;
    int mVerticalChainStyle;
    ConstraintWidget mVerticalNextWidget;
    public int mVerticalResolution;
    boolean mVerticalWrapVisited;
    private int mVisibility;
    public float[] mWeight;
    int mWidth;

    /* renamed from: mX */
    protected int f29mX;

    /* renamed from: mY */
    protected int f30mY;
    public boolean measured;
    public WidgetRun[] run;
    public ChainRun verticalChainRun;
    public VerticalWidgetRun verticalRun;
    public int[] wrapMeasure;

    /* loaded from: classes.dex */
    public enum DimensionBehaviour {
        FIXED,
        WRAP_CONTENT,
        MATCH_CONSTRAINT,
        MATCH_PARENT
    }

    public WidgetRun getRun(int orientation) {
        if (orientation == 0) {
            return this.horizontalRun;
        }
        if (orientation == 1) {
            return this.verticalRun;
        }
        return null;
    }

    public boolean isInVirtualLayout() {
        return this.mInVirtuaLayout;
    }

    public void setInVirtualLayout(boolean inVirtualLayout) {
        this.mInVirtuaLayout = inVirtualLayout;
    }

    public int getMaxHeight() {
        return this.mMaxDimension[1];
    }

    public int getMaxWidth() {
        return this.mMaxDimension[0];
    }

    public void setMaxWidth(int maxWidth) {
        this.mMaxDimension[0] = maxWidth;
    }

    public void setMaxHeight(int maxHeight) {
        this.mMaxDimension[1] = maxHeight;
    }

    public boolean isSpreadWidth() {
        return this.mMatchConstraintDefaultWidth == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinWidth == 0 && this.mMatchConstraintMaxWidth == 0 && this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public boolean isSpreadHeight() {
        return this.mMatchConstraintDefaultHeight == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinHeight == 0 && this.mMatchConstraintMaxHeight == 0 && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public void setHasBaseline(boolean hasBaseline) {
        this.hasBaseline = hasBaseline;
    }

    public boolean getHasBaseline() {
        return this.hasBaseline;
    }

    public boolean isInPlaceholder() {
        return this.inPlaceholder;
    }

    public void setInPlaceholder(boolean inPlaceholder) {
        this.inPlaceholder = inPlaceholder;
    }

    protected void setInBarrier(int orientation, boolean value) {
        this.mIsInBarrier[orientation] = value;
    }

    public void reset() {
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mParent = null;
        this.mCircleConstraintAngle = 0.0f;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.f29mX = 0;
        this.f30mY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mListDimensionBehaviors[0] = DimensionBehaviour.FIXED;
        this.mListDimensionBehaviors[1] = DimensionBehaviour.FIXED;
        this.mCompanionWidget = null;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mType = null;
        this.mHorizontalWrapVisited = false;
        this.mVerticalWrapVisited = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mHorizontalChainFixedPosition = false;
        this.mVerticalChainFixedPosition = false;
        float[] fArr = this.mWeight;
        fArr[0] = -1.0f;
        fArr[1] = -1.0f;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        int[] iArr = this.mMaxDimension;
        iArr[0] = Integer.MAX_VALUE;
        iArr[1] = Integer.MAX_VALUE;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mMatchConstraintMaxWidth = Integer.MAX_VALUE;
        this.mMatchConstraintMaxHeight = Integer.MAX_VALUE;
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMinHeight = 0;
        this.mResolvedHasRatio = false;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mOptimizerMeasurable = false;
        this.mGroupsToSolver = false;
        boolean[] zArr = this.isTerminalWidget;
        zArr[0] = true;
        zArr[1] = true;
        this.mInVirtuaLayout = false;
        boolean[] zArr2 = this.mIsInBarrier;
        zArr2[0] = false;
        zArr2[1] = false;
    }

    public ConstraintWidget() {
        this.measured = false;
        this.run = new WidgetRun[2];
        this.horizontalRun = new HorizontalWidgetRun(this);
        this.verticalRun = new VerticalWidgetRun(this);
        this.isTerminalWidget = new boolean[]{true, true};
        this.wrapMeasure = new int[]{0, 0, 0, 0};
        this.mResolvedHasRatio = false;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.mCircleConstraintAngle = 0.0f;
        this.hasBaseline = false;
        this.mInVirtuaLayout = false;
        this.mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        ConstraintAnchor constraintAnchor = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mCenter = constraintAnchor;
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, constraintAnchor};
        this.mAnchors = new ArrayList<>();
        this.mIsInBarrier = new boolean[2];
        this.mListDimensionBehaviors = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.f29mX = 0;
        this.f30mY = 0;
        this.mRelX = 0;
        this.mRelY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mOptimizerMeasurable = false;
        this.mGroupsToSolver = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mNextChainWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        addAnchors();
    }

    public ConstraintWidget(int x, int y, int width, int height) {
        this.measured = false;
        this.run = new WidgetRun[2];
        this.horizontalRun = new HorizontalWidgetRun(this);
        this.verticalRun = new VerticalWidgetRun(this);
        this.isTerminalWidget = new boolean[]{true, true};
        this.wrapMeasure = new int[]{0, 0, 0, 0};
        this.mResolvedHasRatio = false;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.mCircleConstraintAngle = 0.0f;
        this.hasBaseline = false;
        this.mInVirtuaLayout = false;
        this.mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        ConstraintAnchor constraintAnchor = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mCenter = constraintAnchor;
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, constraintAnchor};
        this.mAnchors = new ArrayList<>();
        this.mIsInBarrier = new boolean[2];
        this.mListDimensionBehaviors = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.f29mX = 0;
        this.f30mY = 0;
        this.mRelX = 0;
        this.mRelY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mOptimizerMeasurable = false;
        this.mGroupsToSolver = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mNextChainWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        this.f29mX = x;
        this.f30mY = y;
        this.mWidth = width;
        this.mHeight = height;
        addAnchors();
    }

    public ConstraintWidget(int width, int height) {
        this(0, 0, width, height);
    }

    public void resetSolverVariables(Cache cache) {
        this.mLeft.resetSolverVariable(cache);
        this.mTop.resetSolverVariable(cache);
        this.mRight.resetSolverVariable(cache);
        this.mBottom.resetSolverVariable(cache);
        this.mBaseline.resetSolverVariable(cache);
        this.mCenter.resetSolverVariable(cache);
        this.mCenterX.resetSolverVariable(cache);
        this.mCenterY.resetSolverVariable(cache);
    }

    private void addAnchors() {
        this.mAnchors.add(this.mLeft);
        this.mAnchors.add(this.mTop);
        this.mAnchors.add(this.mRight);
        this.mAnchors.add(this.mBottom);
        this.mAnchors.add(this.mCenterX);
        this.mAnchors.add(this.mCenterY);
        this.mAnchors.add(this.mCenter);
        this.mAnchors.add(this.mBaseline);
    }

    public boolean isRoot() {
        return this.mParent == null;
    }

    public ConstraintWidget getParent() {
        return this.mParent;
    }

    public void setParent(ConstraintWidget widget) {
        this.mParent = widget;
    }

    public void setWidthWrapContent(boolean widthWrapContent) {
        this.mIsWidthWrapContent = widthWrapContent;
    }

    public boolean isWidthWrapContent() {
        return this.mIsWidthWrapContent;
    }

    public void setHeightWrapContent(boolean heightWrapContent) {
        this.mIsHeightWrapContent = heightWrapContent;
    }

    public boolean isHeightWrapContent() {
        return this.mIsHeightWrapContent;
    }

    public void connectCircularConstraint(ConstraintWidget target, float angle, int radius) {
        immediateConnect(ConstraintAnchor.Type.CENTER, target, ConstraintAnchor.Type.CENTER, radius, 0);
        this.mCircleConstraintAngle = angle;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public void setVisibility(int visibility) {
        this.mVisibility = visibility;
    }

    public int getVisibility() {
        return this.mVisibility;
    }

    public String getDebugName() {
        return this.mDebugName;
    }

    public void setDebugName(String name) {
        this.mDebugName = name;
    }

    public void setDebugSolverName(LinearSystem system, String name) {
        this.mDebugName = name;
        SolverVariable left = system.createObjectVariable(this.mLeft);
        SolverVariable top = system.createObjectVariable(this.mTop);
        SolverVariable right = system.createObjectVariable(this.mRight);
        SolverVariable bottom = system.createObjectVariable(this.mBottom);
        left.setName(name + ".left");
        top.setName(name + ".top");
        right.setName(name + ".right");
        bottom.setName(name + ".bottom");
        if (this.mBaselineDistance > 0) {
            SolverVariable baseline = system.createObjectVariable(this.mBaseline);
            baseline.setName(name + ".baseline");
        }
    }

    public void createObjectVariables(LinearSystem system) {
        system.createObjectVariable(this.mLeft);
        system.createObjectVariable(this.mTop);
        system.createObjectVariable(this.mRight);
        system.createObjectVariable(this.mBottom);
        if (this.mBaselineDistance > 0) {
            system.createObjectVariable(this.mBaseline);
        }
    }

    public String toString() {
        return (this.mType != null ? "type: " + this.mType + " " : "") + (this.mDebugName != null ? "id: " + this.mDebugName + " " : "") + "(" + this.f29mX + ", " + this.f30mY + ") - (" + this.mWidth + " x " + this.mHeight + ")";
    }

    public int getX() {
        ConstraintWidget constraintWidget = this.mParent;
        if (constraintWidget != null && (constraintWidget instanceof ConstraintWidgetContainer)) {
            return ((ConstraintWidgetContainer) constraintWidget).mPaddingLeft + this.f29mX;
        }
        return this.f29mX;
    }

    public int getY() {
        ConstraintWidget constraintWidget = this.mParent;
        if (constraintWidget != null && (constraintWidget instanceof ConstraintWidgetContainer)) {
            return ((ConstraintWidgetContainer) constraintWidget).mPaddingTop + this.f30mY;
        }
        return this.f30mY;
    }

    public int getWidth() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mWidth;
    }

    public int getOptimizerWrapWidth() {
        int w;
        int w2 = this.mWidth;
        if (this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT) {
            if (this.mMatchConstraintDefaultWidth == 1) {
                w = Math.max(this.mMatchConstraintMinWidth, w2);
            } else if (this.mMatchConstraintMinWidth > 0) {
                w = this.mMatchConstraintMinWidth;
                this.mWidth = w;
            } else {
                w = 0;
            }
            int i = this.mMatchConstraintMaxWidth;
            if (i > 0 && i < w) {
                return this.mMatchConstraintMaxWidth;
            }
            return w;
        }
        return w2;
    }

    public int getOptimizerWrapHeight() {
        int h;
        int h2 = this.mHeight;
        if (this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT) {
            if (this.mMatchConstraintDefaultHeight == 1) {
                h = Math.max(this.mMatchConstraintMinHeight, h2);
            } else if (this.mMatchConstraintMinHeight > 0) {
                h = this.mMatchConstraintMinHeight;
                this.mHeight = h;
            } else {
                h = 0;
            }
            int i = this.mMatchConstraintMaxHeight;
            if (i > 0 && i < h) {
                return this.mMatchConstraintMaxHeight;
            }
            return h;
        }
        return h2;
    }

    public int getHeight() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mHeight;
    }

    public int getLength(int orientation) {
        if (orientation == 0) {
            return getWidth();
        }
        if (orientation == 1) {
            return getHeight();
        }
        return 0;
    }

    protected int getRootX() {
        return this.f29mX + this.mOffsetX;
    }

    protected int getRootY() {
        return this.f30mY + this.mOffsetY;
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public int getLeft() {
        return getX();
    }

    public int getTop() {
        return getY();
    }

    public int getRight() {
        return getX() + this.mWidth;
    }

    public int getBottom() {
        return getY() + this.mHeight;
    }

    public int getHorizontalMargin() {
        ConstraintAnchor constraintAnchor = this.mLeft;
        int margin = constraintAnchor != null ? 0 + constraintAnchor.mMargin : 0;
        ConstraintAnchor constraintAnchor2 = this.mRight;
        if (constraintAnchor2 != null) {
            return margin + constraintAnchor2.mMargin;
        }
        return margin;
    }

    public int getVerticalMargin() {
        int margin = this.mLeft != null ? 0 + this.mTop.mMargin : 0;
        if (this.mRight != null) {
            return margin + this.mBottom.mMargin;
        }
        return margin;
    }

    public float getHorizontalBiasPercent() {
        return this.mHorizontalBiasPercent;
    }

    public float getVerticalBiasPercent() {
        return this.mVerticalBiasPercent;
    }

    public float getBiasPercent(int orientation) {
        if (orientation == 0) {
            return this.mHorizontalBiasPercent;
        }
        if (orientation == 1) {
            return this.mVerticalBiasPercent;
        }
        return -1.0f;
    }

    public boolean hasBaseline() {
        return this.hasBaseline;
    }

    public int getBaselineDistance() {
        return this.mBaselineDistance;
    }

    public Object getCompanionWidget() {
        return this.mCompanionWidget;
    }

    public ArrayList<ConstraintAnchor> getAnchors() {
        return this.mAnchors;
    }

    public void setX(int x) {
        this.f29mX = x;
    }

    public void setY(int y) {
        this.f30mY = y;
    }

    public void setOrigin(int x, int y) {
        this.f29mX = x;
        this.f30mY = y;
    }

    public void setOffset(int x, int y) {
        this.mOffsetX = x;
        this.mOffsetY = y;
    }

    public void setGoneMargin(ConstraintAnchor.Type type, int goneMargin) {
        switch (C01061.f31x1d400623[type.ordinal()]) {
            case 1:
                this.mLeft.mGoneMargin = goneMargin;
                return;
            case 2:
                this.mTop.mGoneMargin = goneMargin;
                return;
            case 3:
                this.mRight.mGoneMargin = goneMargin;
                return;
            case 4:
                this.mBottom.mGoneMargin = goneMargin;
                return;
            default:
                return;
        }
    }

    public void setWidth(int w) {
        this.mWidth = w;
        int i = this.mMinWidth;
        if (w < i) {
            this.mWidth = i;
        }
    }

    public void setHeight(int h) {
        this.mHeight = h;
        int i = this.mMinHeight;
        if (h < i) {
            this.mHeight = i;
        }
    }

    public void setLength(int length, int orientation) {
        if (orientation == 0) {
            setWidth(length);
        } else if (orientation == 1) {
            setHeight(length);
        }
    }

    public void setHorizontalMatchStyle(int horizontalMatchStyle, int min, int max, float percent) {
        this.mMatchConstraintDefaultWidth = horizontalMatchStyle;
        this.mMatchConstraintMinWidth = min;
        this.mMatchConstraintMaxWidth = max == Integer.MAX_VALUE ? 0 : max;
        this.mMatchConstraintPercentWidth = percent;
        if (percent > 0.0f && percent < 1.0f && horizontalMatchStyle == 0) {
            this.mMatchConstraintDefaultWidth = 2;
        }
    }

    public void setVerticalMatchStyle(int verticalMatchStyle, int min, int max, float percent) {
        this.mMatchConstraintDefaultHeight = verticalMatchStyle;
        this.mMatchConstraintMinHeight = min;
        this.mMatchConstraintMaxHeight = max == Integer.MAX_VALUE ? 0 : max;
        this.mMatchConstraintPercentHeight = percent;
        if (percent > 0.0f && percent < 1.0f && verticalMatchStyle == 0) {
            this.mMatchConstraintDefaultHeight = 2;
        }
    }

    public void setDimensionRatio(String ratio) {
        int commaIndex;
        if (ratio == null || ratio.length() == 0) {
            this.mDimensionRatio = 0.0f;
            return;
        }
        int dimensionRatioSide = -1;
        float dimensionRatio = 0.0f;
        int len = ratio.length();
        int commaIndex2 = ratio.indexOf(44);
        if (commaIndex2 > 0 && commaIndex2 < len - 1) {
            String dimension = ratio.substring(0, commaIndex2);
            if (dimension.equalsIgnoreCase("W")) {
                dimensionRatioSide = 0;
            } else if (dimension.equalsIgnoreCase(DateFormat.HOUR24)) {
                dimensionRatioSide = 1;
            }
            commaIndex = commaIndex2 + 1;
        } else {
            commaIndex = 0;
        }
        int colonIndex = ratio.indexOf(58);
        if (colonIndex >= 0 && colonIndex < len - 1) {
            String nominator = ratio.substring(commaIndex, colonIndex);
            String denominator = ratio.substring(colonIndex + 1);
            if (nominator.length() > 0 && denominator.length() > 0) {
                try {
                    float nominatorValue = Float.parseFloat(nominator);
                    float denominatorValue = Float.parseFloat(denominator);
                    if (nominatorValue > 0.0f && denominatorValue > 0.0f) {
                        dimensionRatio = dimensionRatioSide == 1 ? Math.abs(denominatorValue / nominatorValue) : Math.abs(nominatorValue / denominatorValue);
                    }
                } catch (NumberFormatException e) {
                }
            }
        } else {
            String r = ratio.substring(commaIndex);
            if (r.length() > 0) {
                try {
                    dimensionRatio = Float.parseFloat(r);
                } catch (NumberFormatException e2) {
                }
            }
        }
        if (dimensionRatio > 0.0f) {
            this.mDimensionRatio = dimensionRatio;
            this.mDimensionRatioSide = dimensionRatioSide;
        }
    }

    public void setDimensionRatio(float ratio, int dimensionRatioSide) {
        this.mDimensionRatio = ratio;
        this.mDimensionRatioSide = dimensionRatioSide;
    }

    public float getDimensionRatio() {
        return this.mDimensionRatio;
    }

    public int getDimensionRatioSide() {
        return this.mDimensionRatioSide;
    }

    public void setHorizontalBiasPercent(float horizontalBiasPercent) {
        this.mHorizontalBiasPercent = horizontalBiasPercent;
    }

    public void setVerticalBiasPercent(float verticalBiasPercent) {
        this.mVerticalBiasPercent = verticalBiasPercent;
    }

    public void setMinWidth(int w) {
        if (w < 0) {
            this.mMinWidth = 0;
        } else {
            this.mMinWidth = w;
        }
    }

    public void setMinHeight(int h) {
        if (h < 0) {
            this.mMinHeight = 0;
        } else {
            this.mMinHeight = h;
        }
    }

    public void setDimension(int w, int h) {
        this.mWidth = w;
        int i = this.mMinWidth;
        if (w < i) {
            this.mWidth = i;
        }
        this.mHeight = h;
        int i2 = this.mMinHeight;
        if (h < i2) {
            this.mHeight = i2;
        }
    }

    public void setFrame(int left, int top, int right, int bottom) {
        int w = right - left;
        int h = bottom - top;
        this.f29mX = left;
        this.f30mY = top;
        if (this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        if (this.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED && w < this.mWidth) {
            w = this.mWidth;
        }
        if (this.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED && h < this.mHeight) {
            h = this.mHeight;
        }
        this.mWidth = w;
        this.mHeight = h;
        int i = this.mMinHeight;
        if (h < i) {
            this.mHeight = i;
        }
        int i2 = this.mMinWidth;
        if (w < i2) {
            this.mWidth = i2;
        }
    }

    public void setFrame(int start, int end, int orientation) {
        if (orientation == 0) {
            setHorizontalDimension(start, end);
        } else if (orientation == 1) {
            setVerticalDimension(start, end);
        }
    }

    public void setHorizontalDimension(int left, int right) {
        this.f29mX = left;
        int i = right - left;
        this.mWidth = i;
        int i2 = this.mMinWidth;
        if (i < i2) {
            this.mWidth = i2;
        }
    }

    public void setVerticalDimension(int top, int bottom) {
        this.f30mY = top;
        int i = bottom - top;
        this.mHeight = i;
        int i2 = this.mMinHeight;
        if (i < i2) {
            this.mHeight = i2;
        }
    }

    int getRelativePositioning(int orientation) {
        if (orientation == 0) {
            return this.mRelX;
        }
        if (orientation == 1) {
            return this.mRelY;
        }
        return 0;
    }

    void setRelativePositioning(int offset, int orientation) {
        if (orientation == 0) {
            this.mRelX = offset;
        } else if (orientation == 1) {
            this.mRelY = offset;
        }
    }

    public void setBaselineDistance(int baseline) {
        this.mBaselineDistance = baseline;
        this.hasBaseline = baseline > 0;
    }

    public void setCompanionWidget(Object companion) {
        this.mCompanionWidget = companion;
    }

    public void setContainerItemSkip(int skip) {
        if (skip >= 0) {
            this.mContainerItemSkip = skip;
        } else {
            this.mContainerItemSkip = 0;
        }
    }

    public int getContainerItemSkip() {
        return this.mContainerItemSkip;
    }

    public void setHorizontalWeight(float horizontalWeight) {
        this.mWeight[0] = horizontalWeight;
    }

    public void setVerticalWeight(float verticalWeight) {
        this.mWeight[1] = verticalWeight;
    }

    public void setHorizontalChainStyle(int horizontalChainStyle) {
        this.mHorizontalChainStyle = horizontalChainStyle;
    }

    public int getHorizontalChainStyle() {
        return this.mHorizontalChainStyle;
    }

    public void setVerticalChainStyle(int verticalChainStyle) {
        this.mVerticalChainStyle = verticalChainStyle;
    }

    public int getVerticalChainStyle() {
        return this.mVerticalChainStyle;
    }

    public boolean allowedInBarrier() {
        return this.mVisibility != 8;
    }

    public void immediateConnect(ConstraintAnchor.Type startType, ConstraintWidget target, ConstraintAnchor.Type endType, int margin, int goneMargin) {
        ConstraintAnchor startAnchor = getAnchor(startType);
        ConstraintAnchor endAnchor = target.getAnchor(endType);
        startAnchor.connect(endAnchor, margin, goneMargin, true);
    }

    public void connect(ConstraintAnchor from, ConstraintAnchor to, int margin) {
        if (from.getOwner() == this) {
            connect(from.getType(), to.getOwner(), to.getType(), margin);
        }
    }

    public void connect(ConstraintAnchor.Type constraintFrom, ConstraintWidget target, ConstraintAnchor.Type constraintTo) {
        connect(constraintFrom, target, constraintTo, 0);
    }

    public void connect(ConstraintAnchor.Type constraintFrom, ConstraintWidget target, ConstraintAnchor.Type constraintTo, int margin) {
        if (constraintFrom == ConstraintAnchor.Type.CENTER) {
            if (constraintTo != ConstraintAnchor.Type.CENTER) {
                if (constraintTo == ConstraintAnchor.Type.LEFT || constraintTo == ConstraintAnchor.Type.RIGHT) {
                    connect(ConstraintAnchor.Type.LEFT, target, constraintTo, 0);
                    connect(ConstraintAnchor.Type.RIGHT, target, constraintTo, 0);
                    getAnchor(ConstraintAnchor.Type.CENTER).connect(target.getAnchor(constraintTo), 0);
                    return;
                } else if (constraintTo == ConstraintAnchor.Type.TOP || constraintTo == ConstraintAnchor.Type.BOTTOM) {
                    connect(ConstraintAnchor.Type.TOP, target, constraintTo, 0);
                    connect(ConstraintAnchor.Type.BOTTOM, target, constraintTo, 0);
                    getAnchor(ConstraintAnchor.Type.CENTER).connect(target.getAnchor(constraintTo), 0);
                    return;
                } else {
                    return;
                }
            }
            ConstraintAnchor left = getAnchor(ConstraintAnchor.Type.LEFT);
            ConstraintAnchor right = getAnchor(ConstraintAnchor.Type.RIGHT);
            ConstraintAnchor top = getAnchor(ConstraintAnchor.Type.TOP);
            ConstraintAnchor bottom = getAnchor(ConstraintAnchor.Type.BOTTOM);
            boolean centerX = false;
            boolean centerY = false;
            if ((left == null || !left.isConnected()) && (right == null || !right.isConnected())) {
                connect(ConstraintAnchor.Type.LEFT, target, ConstraintAnchor.Type.LEFT, 0);
                connect(ConstraintAnchor.Type.RIGHT, target, ConstraintAnchor.Type.RIGHT, 0);
                centerX = true;
            }
            if ((top == null || !top.isConnected()) && (bottom == null || !bottom.isConnected())) {
                connect(ConstraintAnchor.Type.TOP, target, ConstraintAnchor.Type.TOP, 0);
                connect(ConstraintAnchor.Type.BOTTOM, target, ConstraintAnchor.Type.BOTTOM, 0);
                centerY = true;
            }
            if (centerX && centerY) {
                getAnchor(ConstraintAnchor.Type.CENTER).connect(target.getAnchor(ConstraintAnchor.Type.CENTER), 0);
            } else if (centerX) {
                getAnchor(ConstraintAnchor.Type.CENTER_X).connect(target.getAnchor(ConstraintAnchor.Type.CENTER_X), 0);
            } else if (centerY) {
                getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(target.getAnchor(ConstraintAnchor.Type.CENTER_Y), 0);
            }
        } else if (constraintFrom == ConstraintAnchor.Type.CENTER_X && (constraintTo == ConstraintAnchor.Type.LEFT || constraintTo == ConstraintAnchor.Type.RIGHT)) {
            ConstraintAnchor left2 = getAnchor(ConstraintAnchor.Type.LEFT);
            ConstraintAnchor targetAnchor = target.getAnchor(constraintTo);
            ConstraintAnchor right2 = getAnchor(ConstraintAnchor.Type.RIGHT);
            left2.connect(targetAnchor, 0);
            right2.connect(targetAnchor, 0);
            getAnchor(ConstraintAnchor.Type.CENTER_X).connect(targetAnchor, 0);
        } else if (constraintFrom == ConstraintAnchor.Type.CENTER_Y && (constraintTo == ConstraintAnchor.Type.TOP || constraintTo == ConstraintAnchor.Type.BOTTOM)) {
            ConstraintAnchor targetAnchor2 = target.getAnchor(constraintTo);
            getAnchor(ConstraintAnchor.Type.TOP).connect(targetAnchor2, 0);
            getAnchor(ConstraintAnchor.Type.BOTTOM).connect(targetAnchor2, 0);
            getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(targetAnchor2, 0);
        } else if (constraintFrom == ConstraintAnchor.Type.CENTER_X && constraintTo == ConstraintAnchor.Type.CENTER_X) {
            ConstraintAnchor left3 = getAnchor(ConstraintAnchor.Type.LEFT);
            ConstraintAnchor leftTarget = target.getAnchor(ConstraintAnchor.Type.LEFT);
            left3.connect(leftTarget, 0);
            ConstraintAnchor right3 = getAnchor(ConstraintAnchor.Type.RIGHT);
            ConstraintAnchor rightTarget = target.getAnchor(ConstraintAnchor.Type.RIGHT);
            right3.connect(rightTarget, 0);
            getAnchor(ConstraintAnchor.Type.CENTER_X).connect(target.getAnchor(constraintTo), 0);
        } else if (constraintFrom == ConstraintAnchor.Type.CENTER_Y && constraintTo == ConstraintAnchor.Type.CENTER_Y) {
            ConstraintAnchor top2 = getAnchor(ConstraintAnchor.Type.TOP);
            ConstraintAnchor topTarget = target.getAnchor(ConstraintAnchor.Type.TOP);
            top2.connect(topTarget, 0);
            ConstraintAnchor bottom2 = getAnchor(ConstraintAnchor.Type.BOTTOM);
            ConstraintAnchor bottomTarget = target.getAnchor(ConstraintAnchor.Type.BOTTOM);
            bottom2.connect(bottomTarget, 0);
            getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(target.getAnchor(constraintTo), 0);
        } else {
            ConstraintAnchor fromAnchor = getAnchor(constraintFrom);
            ConstraintAnchor toAnchor = target.getAnchor(constraintTo);
            if (fromAnchor.isValidConnection(toAnchor)) {
                if (constraintFrom == ConstraintAnchor.Type.BASELINE) {
                    ConstraintAnchor top3 = getAnchor(ConstraintAnchor.Type.TOP);
                    ConstraintAnchor bottom3 = getAnchor(ConstraintAnchor.Type.BOTTOM);
                    if (top3 != null) {
                        top3.reset();
                    }
                    if (bottom3 != null) {
                        bottom3.reset();
                    }
                    margin = 0;
                } else if (constraintFrom == ConstraintAnchor.Type.TOP || constraintFrom == ConstraintAnchor.Type.BOTTOM) {
                    ConstraintAnchor baseline = getAnchor(ConstraintAnchor.Type.BASELINE);
                    if (baseline != null) {
                        baseline.reset();
                    }
                    ConstraintAnchor center = getAnchor(ConstraintAnchor.Type.CENTER);
                    if (center.getTarget() != toAnchor) {
                        center.reset();
                    }
                    ConstraintAnchor opposite = getAnchor(constraintFrom).getOpposite();
                    ConstraintAnchor centerY2 = getAnchor(ConstraintAnchor.Type.CENTER_Y);
                    if (centerY2.isConnected()) {
                        opposite.reset();
                        centerY2.reset();
                    }
                } else if (constraintFrom == ConstraintAnchor.Type.LEFT || constraintFrom == ConstraintAnchor.Type.RIGHT) {
                    ConstraintAnchor center2 = getAnchor(ConstraintAnchor.Type.CENTER);
                    if (center2.getTarget() != toAnchor) {
                        center2.reset();
                    }
                    ConstraintAnchor opposite2 = getAnchor(constraintFrom).getOpposite();
                    ConstraintAnchor centerX2 = getAnchor(ConstraintAnchor.Type.CENTER_X);
                    if (centerX2.isConnected()) {
                        opposite2.reset();
                        centerX2.reset();
                    }
                }
                fromAnchor.connect(toAnchor, margin);
            }
        }
    }

    public void resetAllConstraints() {
        resetAnchors();
        setVerticalBiasPercent(DEFAULT_BIAS);
        setHorizontalBiasPercent(DEFAULT_BIAS);
    }

    public void resetAnchor(ConstraintAnchor anchor) {
        if (getParent() != null && (getParent() instanceof ConstraintWidgetContainer)) {
            ConstraintWidgetContainer parent = (ConstraintWidgetContainer) getParent();
            if (parent.handlesInternalConstraints()) {
                return;
            }
        }
        ConstraintAnchor left = getAnchor(ConstraintAnchor.Type.LEFT);
        ConstraintAnchor right = getAnchor(ConstraintAnchor.Type.RIGHT);
        ConstraintAnchor top = getAnchor(ConstraintAnchor.Type.TOP);
        ConstraintAnchor bottom = getAnchor(ConstraintAnchor.Type.BOTTOM);
        ConstraintAnchor center = getAnchor(ConstraintAnchor.Type.CENTER);
        ConstraintAnchor centerX = getAnchor(ConstraintAnchor.Type.CENTER_X);
        ConstraintAnchor centerY = getAnchor(ConstraintAnchor.Type.CENTER_Y);
        if (anchor == center) {
            if (left.isConnected() && right.isConnected() && left.getTarget() == right.getTarget()) {
                left.reset();
                right.reset();
            }
            if (top.isConnected() && bottom.isConnected() && top.getTarget() == bottom.getTarget()) {
                top.reset();
                bottom.reset();
            }
            this.mHorizontalBiasPercent = 0.5f;
            this.mVerticalBiasPercent = 0.5f;
        } else if (anchor == centerX) {
            if (left.isConnected() && right.isConnected() && left.getTarget().getOwner() == right.getTarget().getOwner()) {
                left.reset();
                right.reset();
            }
            this.mHorizontalBiasPercent = 0.5f;
        } else if (anchor == centerY) {
            if (top.isConnected() && bottom.isConnected() && top.getTarget().getOwner() == bottom.getTarget().getOwner()) {
                top.reset();
                bottom.reset();
            }
            this.mVerticalBiasPercent = 0.5f;
        } else if (anchor == left || anchor == right) {
            if (left.isConnected() && left.getTarget() == right.getTarget()) {
                center.reset();
            }
        } else if ((anchor == top || anchor == bottom) && top.isConnected() && top.getTarget() == bottom.getTarget()) {
            center.reset();
        }
        anchor.reset();
    }

    public void resetAnchors() {
        ConstraintWidget parent = getParent();
        if (parent != null && (parent instanceof ConstraintWidgetContainer)) {
            ConstraintWidgetContainer parentContainer = (ConstraintWidgetContainer) getParent();
            if (parentContainer.handlesInternalConstraints()) {
                return;
            }
        }
        int mAnchorsSize = this.mAnchors.size();
        for (int i = 0; i < mAnchorsSize; i++) {
            ConstraintAnchor anchor = this.mAnchors.get(i);
            anchor.reset();
        }
    }

    public ConstraintAnchor getAnchor(ConstraintAnchor.Type anchorType) {
        switch (C01061.f31x1d400623[anchorType.ordinal()]) {
            case 1:
                return this.mLeft;
            case 2:
                return this.mTop;
            case 3:
                return this.mRight;
            case 4:
                return this.mBottom;
            case 5:
                return this.mBaseline;
            case 6:
                return this.mCenter;
            case 7:
                return this.mCenterX;
            case 8:
                return this.mCenterY;
            case 9:
                return null;
            default:
                throw new AssertionError(anchorType.name());
        }
    }

    public DimensionBehaviour getHorizontalDimensionBehaviour() {
        return this.mListDimensionBehaviors[0];
    }

    public DimensionBehaviour getVerticalDimensionBehaviour() {
        return this.mListDimensionBehaviors[1];
    }

    public DimensionBehaviour getDimensionBehaviour(int orientation) {
        if (orientation == 0) {
            return getHorizontalDimensionBehaviour();
        }
        if (orientation == 1) {
            return getVerticalDimensionBehaviour();
        }
        return null;
    }

    public void setHorizontalDimensionBehaviour(DimensionBehaviour behaviour) {
        this.mListDimensionBehaviors[0] = behaviour;
    }

    public void setVerticalDimensionBehaviour(DimensionBehaviour behaviour) {
        this.mListDimensionBehaviors[1] = behaviour;
    }

    public boolean isInHorizontalChain() {
        if (this.mLeft.mTarget == null || this.mLeft.mTarget.mTarget != this.mLeft) {
            if (this.mRight.mTarget != null && this.mRight.mTarget.mTarget == this.mRight) {
                return true;
            }
            return false;
        }
        return true;
    }

    public ConstraintWidget getPreviousChainMember(int orientation) {
        if (orientation == 0) {
            if (this.mLeft.mTarget != null) {
                ConstraintAnchor constraintAnchor = this.mLeft.mTarget.mTarget;
                ConstraintAnchor constraintAnchor2 = this.mLeft;
                if (constraintAnchor == constraintAnchor2) {
                    return constraintAnchor2.mTarget.mOwner;
                }
                return null;
            }
            return null;
        } else if (orientation != 1 || this.mTop.mTarget == null) {
            return null;
        } else {
            ConstraintAnchor constraintAnchor3 = this.mTop.mTarget.mTarget;
            ConstraintAnchor constraintAnchor4 = this.mTop;
            if (constraintAnchor3 == constraintAnchor4) {
                return constraintAnchor4.mTarget.mOwner;
            }
            return null;
        }
    }

    public ConstraintWidget getNextChainMember(int orientation) {
        if (orientation == 0) {
            if (this.mRight.mTarget != null) {
                ConstraintAnchor constraintAnchor = this.mRight.mTarget.mTarget;
                ConstraintAnchor constraintAnchor2 = this.mRight;
                if (constraintAnchor == constraintAnchor2) {
                    return constraintAnchor2.mTarget.mOwner;
                }
                return null;
            }
            return null;
        } else if (orientation != 1 || this.mBottom.mTarget == null) {
            return null;
        } else {
            ConstraintAnchor constraintAnchor3 = this.mBottom.mTarget.mTarget;
            ConstraintAnchor constraintAnchor4 = this.mBottom;
            if (constraintAnchor3 == constraintAnchor4) {
                return constraintAnchor4.mTarget.mOwner;
            }
            return null;
        }
    }

    public ConstraintWidget getHorizontalChainControlWidget() {
        ConstraintWidget found = null;
        if (!isInHorizontalChain()) {
            return null;
        }
        ConstraintWidget tmp = this;
        while (found == null && tmp != null) {
            ConstraintAnchor anchor = tmp.getAnchor(ConstraintAnchor.Type.LEFT);
            ConstraintAnchor targetOwner = anchor == null ? null : anchor.getTarget();
            ConstraintWidget target = targetOwner == null ? null : targetOwner.getOwner();
            if (target == getParent()) {
                ConstraintWidget found2 = tmp;
                return found2;
            }
            ConstraintAnchor targetAnchor = target != null ? target.getAnchor(ConstraintAnchor.Type.RIGHT).getTarget() : null;
            if (targetAnchor != null && targetAnchor.getOwner() != tmp) {
                found = tmp;
            } else {
                tmp = target;
            }
        }
        return found;
    }

    public boolean isInVerticalChain() {
        if (this.mTop.mTarget == null || this.mTop.mTarget.mTarget != this.mTop) {
            if (this.mBottom.mTarget != null && this.mBottom.mTarget.mTarget == this.mBottom) {
                return true;
            }
            return false;
        }
        return true;
    }

    public ConstraintWidget getVerticalChainControlWidget() {
        ConstraintWidget found = null;
        if (!isInVerticalChain()) {
            return null;
        }
        ConstraintWidget tmp = this;
        while (found == null && tmp != null) {
            ConstraintAnchor anchor = tmp.getAnchor(ConstraintAnchor.Type.TOP);
            ConstraintAnchor targetOwner = anchor == null ? null : anchor.getTarget();
            ConstraintWidget target = targetOwner == null ? null : targetOwner.getOwner();
            if (target == getParent()) {
                ConstraintWidget found2 = tmp;
                return found2;
            }
            ConstraintAnchor targetAnchor = target != null ? target.getAnchor(ConstraintAnchor.Type.BOTTOM).getTarget() : null;
            if (targetAnchor != null && targetAnchor.getOwner() != tmp) {
                found = tmp;
            } else {
                tmp = target;
            }
        }
        return found;
    }

    private boolean isChainHead(int orientation) {
        int offset = orientation * 2;
        if (this.mListAnchors[offset].mTarget != null) {
            ConstraintAnchor constraintAnchor = this.mListAnchors[offset].mTarget.mTarget;
            ConstraintAnchor[] constraintAnchorArr = this.mListAnchors;
            if (constraintAnchor != constraintAnchorArr[offset] && constraintAnchorArr[offset + 1].mTarget != null && this.mListAnchors[offset + 1].mTarget.mTarget == this.mListAnchors[offset + 1]) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:145:0x028b, code lost:
        if (r0 == (-1)) goto L238;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0286  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0292  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x029e  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x02cd  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x03da  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x03f9  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x045f  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0462  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x0465  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x052f  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x055a  */
    /* JADX WARN: Removed duplicated region for block: B:267:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2, types: [int] */
    /* JADX WARN: Type inference failed for: r4v20 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void addToSolver(LinearSystem system) {
        boolean inHorizontalChain;
        boolean inVerticalChain;
        boolean horizontalParentWrapContent;
        boolean verticalParentWrapContent;
        SolverVariable top;
        int matchConstraintDefaultWidth;
        int matchConstraintDefaultHeight;
        int height;
        int width;
        boolean useRatio;
        char c;
        boolean useHorizontalRatio;
        boolean wrapContent;
        int width2;
        boolean applyPosition;
        boolean useRatio2;
        SolverVariable right;
        SolverVariable left;
        boolean verticalParentWrapContent2;
        boolean horizontalParentWrapContent2;
        SolverVariable baseline;
        SolverVariable bottom;
        SolverVariable top2;
        LinearSystem linearSystem;
        SolverVariable baseline2;
        SolverVariable bottom2;
        SolverVariable top3;
        int i;
        int i2;
        ?? r4;
        boolean applyVerticalConstraints;
        int height2;
        boolean applyPosition2;
        int i3;
        boolean z;
        char c2;
        boolean inHorizontalChain2;
        boolean inVerticalChain2;
        SolverVariable left2 = system.createObjectVariable(this.mLeft);
        SolverVariable right2 = system.createObjectVariable(this.mRight);
        SolverVariable top4 = system.createObjectVariable(this.mTop);
        SolverVariable bottom3 = system.createObjectVariable(this.mBottom);
        SolverVariable baseline3 = system.createObjectVariable(this.mBaseline);
        if (LinearSystem.sMetrics != null) {
            LinearSystem.sMetrics.widgets++;
        }
        if (this.horizontalRun.start.resolved && this.horizontalRun.end.resolved && this.verticalRun.start.resolved && this.verticalRun.end.resolved) {
            if (LinearSystem.sMetrics != null) {
                LinearSystem.sMetrics.graphSolved++;
            }
            system.addEquality(left2, this.horizontalRun.start.value);
            system.addEquality(right2, this.horizontalRun.end.value);
            system.addEquality(top4, this.verticalRun.start.value);
            system.addEquality(bottom3, this.verticalRun.end.value);
            system.addEquality(baseline3, this.verticalRun.baseline.value);
            ConstraintWidget constraintWidget = this.mParent;
            if (constraintWidget != null) {
                boolean horizontalParentWrapContent3 = constraintWidget != null && constraintWidget.mListDimensionBehaviors[0] == DimensionBehaviour.WRAP_CONTENT;
                ConstraintWidget constraintWidget2 = this.mParent;
                boolean verticalParentWrapContent3 = constraintWidget2 != null && constraintWidget2.mListDimensionBehaviors[1] == DimensionBehaviour.WRAP_CONTENT;
                if (horizontalParentWrapContent3 && this.isTerminalWidget[0] && !isInHorizontalChain()) {
                    system.addGreaterThan(system.createObjectVariable(this.mParent.mRight), right2, 0, 8);
                }
                if (verticalParentWrapContent3 && this.isTerminalWidget[1] && !isInVerticalChain()) {
                    system.addGreaterThan(system.createObjectVariable(this.mParent.mBottom), bottom3, 0, 8);
                    return;
                }
                return;
            }
            return;
        }
        if (LinearSystem.sMetrics != null) {
            LinearSystem.sMetrics.linearSolved++;
        }
        ConstraintWidget constraintWidget3 = this.mParent;
        if (constraintWidget3 == null) {
            inHorizontalChain = false;
            inVerticalChain = false;
            horizontalParentWrapContent = false;
            verticalParentWrapContent = false;
        } else {
            boolean horizontalParentWrapContent4 = constraintWidget3 != null && constraintWidget3.mListDimensionBehaviors[0] == DimensionBehaviour.WRAP_CONTENT;
            ConstraintWidget constraintWidget4 = this.mParent;
            boolean verticalParentWrapContent4 = constraintWidget4 != null && constraintWidget4.mListDimensionBehaviors[1] == DimensionBehaviour.WRAP_CONTENT;
            if (isChainHead(0)) {
                ((ConstraintWidgetContainer) this.mParent).addChain(this, 0);
                inHorizontalChain2 = true;
            } else {
                inHorizontalChain2 = isInHorizontalChain();
            }
            if (isChainHead(1)) {
                ((ConstraintWidgetContainer) this.mParent).addChain(this, 1);
                inVerticalChain2 = true;
            } else {
                inVerticalChain2 = isInVerticalChain();
            }
            if (!inHorizontalChain2 && horizontalParentWrapContent4 && this.mVisibility != 8 && this.mLeft.mTarget == null && this.mRight.mTarget == null) {
                SolverVariable parentRight = system.createObjectVariable(this.mParent.mRight);
                system.addGreaterThan(parentRight, right2, 0, 1);
            }
            if (!inVerticalChain2 && verticalParentWrapContent4 && this.mVisibility != 8 && this.mTop.mTarget == null && this.mBottom.mTarget == null && this.mBaseline == null) {
                SolverVariable parentBottom = system.createObjectVariable(this.mParent.mBottom);
                system.addGreaterThan(parentBottom, bottom3, 0, 1);
            }
            inHorizontalChain = inHorizontalChain2;
            inVerticalChain = inVerticalChain2;
            horizontalParentWrapContent = horizontalParentWrapContent4;
            verticalParentWrapContent = verticalParentWrapContent4;
        }
        int width3 = this.mWidth;
        if (width3 < this.mMinWidth) {
            width3 = this.mMinWidth;
        }
        int height3 = this.mHeight;
        if (height3 < this.mMinHeight) {
            height3 = this.mMinHeight;
        }
        boolean horizontalDimensionFixed = this.mListDimensionBehaviors[0] != DimensionBehaviour.MATCH_CONSTRAINT;
        boolean verticalDimensionFixed = this.mListDimensionBehaviors[1] != DimensionBehaviour.MATCH_CONSTRAINT;
        boolean useRatio3 = false;
        this.mResolvedDimensionRatioSide = this.mDimensionRatioSide;
        float f = this.mDimensionRatio;
        this.mResolvedDimensionRatio = f;
        int matchConstraintDefaultWidth2 = this.mMatchConstraintDefaultWidth;
        int matchConstraintDefaultHeight2 = this.mMatchConstraintDefaultHeight;
        int width4 = width3;
        if (f <= 0.0f || this.mVisibility == 8) {
            top = top4;
        } else {
            useRatio3 = true;
            if (this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT && matchConstraintDefaultWidth2 == 0) {
                matchConstraintDefaultWidth2 = 3;
            }
            if (this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT && matchConstraintDefaultHeight2 == 0) {
                matchConstraintDefaultHeight2 = 3;
            }
            top = top4;
            if (this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT && matchConstraintDefaultWidth2 == 3 && matchConstraintDefaultHeight2 == 3) {
                setupDimensionRatio(horizontalParentWrapContent, verticalParentWrapContent, horizontalDimensionFixed, verticalDimensionFixed);
            } else {
                if (this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT && matchConstraintDefaultWidth2 == 3) {
                    this.mResolvedDimensionRatioSide = 0;
                    width = (int) (this.mResolvedDimensionRatio * this.mHeight);
                    if (this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT) {
                        matchConstraintDefaultHeight = matchConstraintDefaultHeight2;
                        height = height3;
                        useRatio = true;
                        matchConstraintDefaultWidth = matchConstraintDefaultWidth2;
                    } else {
                        matchConstraintDefaultHeight = matchConstraintDefaultHeight2;
                        height = height3;
                        useRatio = false;
                        matchConstraintDefaultWidth = 4;
                    }
                } else if (this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT && matchConstraintDefaultHeight2 == 3) {
                    this.mResolvedDimensionRatioSide = 1;
                    if (this.mDimensionRatioSide == -1) {
                        this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                    }
                    int height4 = (int) (this.mResolvedDimensionRatio * this.mWidth);
                    if (this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT) {
                        matchConstraintDefaultHeight = matchConstraintDefaultHeight2;
                        height = height4;
                        useRatio = true;
                        matchConstraintDefaultWidth = matchConstraintDefaultWidth2;
                        width = width4;
                    } else {
                        matchConstraintDefaultHeight = 4;
                        height = height4;
                        useRatio = false;
                        matchConstraintDefaultWidth = matchConstraintDefaultWidth2;
                        width = width4;
                    }
                }
                int[] iArr = this.mResolvedMatchConstraintDefault;
                iArr[0] = matchConstraintDefaultWidth;
                iArr[1] = matchConstraintDefaultHeight;
                this.mResolvedHasRatio = useRatio;
                if (!useRatio) {
                    int i4 = this.mResolvedDimensionRatioSide;
                    if (i4 != 0) {
                        c = '\uffff';
                    } else {
                        c = '\uffff';
                    }
                    useHorizontalRatio = true;
                    wrapContent = this.mListDimensionBehaviors[0] != DimensionBehaviour.WRAP_CONTENT && (this instanceof ConstraintWidgetContainer);
                    if (!wrapContent) {
                        width2 = width;
                    } else {
                        width2 = 0;
                    }
                    if (!this.mCenter.isConnected()) {
                        applyPosition = true;
                    } else {
                        applyPosition = false;
                    }
                    boolean[] zArr = this.mIsInBarrier;
                    boolean isInHorizontalBarrier = zArr[0];
                    boolean isInVerticalBarrier = zArr[1];
                    if (this.mHorizontalResolution == 2) {
                        useRatio2 = useRatio;
                        right = right2;
                        left = left2;
                        verticalParentWrapContent2 = verticalParentWrapContent;
                        horizontalParentWrapContent2 = horizontalParentWrapContent;
                        baseline = baseline3;
                        bottom = bottom3;
                        top2 = top;
                    } else {
                        if (this.horizontalRun.start.resolved) {
                            if (this.horizontalRun.end.resolved) {
                                system.addEquality(left2, this.horizontalRun.start.value);
                                system.addEquality(right2, this.horizontalRun.end.value);
                                if (this.mParent == null) {
                                    useRatio2 = useRatio;
                                    right = right2;
                                    left = left2;
                                    verticalParentWrapContent2 = verticalParentWrapContent;
                                    horizontalParentWrapContent2 = horizontalParentWrapContent;
                                    baseline = baseline3;
                                    bottom = bottom3;
                                    top2 = top;
                                } else if (!horizontalParentWrapContent || !this.isTerminalWidget[0] || isInHorizontalChain()) {
                                    useRatio2 = useRatio;
                                    right = right2;
                                    left = left2;
                                    verticalParentWrapContent2 = verticalParentWrapContent;
                                    horizontalParentWrapContent2 = horizontalParentWrapContent;
                                    baseline = baseline3;
                                    bottom = bottom3;
                                    top2 = top;
                                } else {
                                    system.addGreaterThan(system.createObjectVariable(this.mParent.mRight), right2, 0, 8);
                                    useRatio2 = useRatio;
                                    right = right2;
                                    left = left2;
                                    verticalParentWrapContent2 = verticalParentWrapContent;
                                    horizontalParentWrapContent2 = horizontalParentWrapContent;
                                    baseline = baseline3;
                                    bottom = bottom3;
                                    top2 = top;
                                }
                            } else {
                                c2 = '\b';
                            }
                        } else {
                            c2 = '\b';
                        }
                        ConstraintWidget constraintWidget5 = this.mParent;
                        SolverVariable parentMax = constraintWidget5 != null ? system.createObjectVariable(constraintWidget5.mRight) : null;
                        ConstraintWidget constraintWidget6 = this.mParent;
                        SolverVariable parentMin = constraintWidget6 != null ? system.createObjectVariable(constraintWidget6.mLeft) : null;
                        verticalParentWrapContent2 = verticalParentWrapContent;
                        horizontalParentWrapContent2 = horizontalParentWrapContent;
                        baseline = baseline3;
                        bottom = bottom3;
                        useRatio2 = useRatio;
                        top2 = top;
                        right = right2;
                        left = left2;
                        applyConstraints(system, true, horizontalParentWrapContent2, verticalParentWrapContent2, this.isTerminalWidget[0], parentMin, parentMax, this.mListDimensionBehaviors[0], wrapContent, this.mLeft, this.mRight, this.f29mX, width2, this.mMinWidth, this.mMaxDimension[0], this.mHorizontalBiasPercent, useHorizontalRatio, inHorizontalChain, inVerticalChain, isInHorizontalBarrier, matchConstraintDefaultWidth, matchConstraintDefaultHeight, this.mMatchConstraintMinWidth, this.mMatchConstraintMaxWidth, this.mMatchConstraintPercentWidth, applyPosition);
                    }
                    boolean applyVerticalConstraints2 = true;
                    if (this.verticalRun.start.resolved || !this.verticalRun.end.resolved) {
                        linearSystem = system;
                        baseline2 = baseline;
                        bottom2 = bottom;
                        top3 = top2;
                        i = 8;
                        i2 = 1;
                        r4 = 0;
                    } else {
                        linearSystem = system;
                        top3 = top2;
                        linearSystem.addEquality(top3, this.verticalRun.start.value);
                        bottom2 = bottom;
                        linearSystem.addEquality(bottom2, this.verticalRun.end.value);
                        baseline2 = baseline;
                        linearSystem.addEquality(baseline2, this.verticalRun.baseline.value);
                        ConstraintWidget constraintWidget7 = this.mParent;
                        if (constraintWidget7 == null) {
                            i = 8;
                            i2 = 1;
                            z = false;
                        } else {
                            if (inVerticalChain || !verticalParentWrapContent2) {
                                i = 8;
                                i2 = 1;
                            } else {
                                i2 = 1;
                                if (!this.isTerminalWidget[1]) {
                                    i = 8;
                                } else {
                                    i = 8;
                                    z = false;
                                    linearSystem.addGreaterThan(linearSystem.createObjectVariable(constraintWidget7.mBottom), bottom2, 0, 8);
                                }
                            }
                            z = false;
                        }
                        applyVerticalConstraints2 = false;
                        r4 = z;
                    }
                    if (this.mVerticalResolution != 2) {
                        applyVerticalConstraints = applyVerticalConstraints2;
                    } else {
                        applyVerticalConstraints = false;
                    }
                    if (applyVerticalConstraints) {
                        int i5 = (this.mListDimensionBehaviors[i2] == DimensionBehaviour.WRAP_CONTENT && (this instanceof ConstraintWidgetContainer)) ? i2 : r4;
                        if (i5 == 0) {
                            height2 = height;
                        } else {
                            height2 = 0;
                        }
                        boolean useVerticalRatio = (useRatio2 && ((i3 = this.mResolvedDimensionRatioSide) == i2 || i3 == -1)) ? i2 : r4;
                        ConstraintWidget constraintWidget8 = this.mParent;
                        SolverVariable parentMax2 = constraintWidget8 != null ? linearSystem.createObjectVariable(constraintWidget8.mBottom) : null;
                        ConstraintWidget constraintWidget9 = this.mParent;
                        SolverVariable parentMin2 = constraintWidget9 != null ? linearSystem.createObjectVariable(constraintWidget9.mTop) : null;
                        if (this.mBaselineDistance > 0 || this.mVisibility == i) {
                            linearSystem.addEquality(baseline2, top3, getBaselineDistance(), i);
                            if (this.mBaseline.mTarget == null) {
                                if (this.mVisibility == i) {
                                    linearSystem.addEquality(baseline2, top3, r4, i);
                                }
                            } else {
                                SolverVariable baselineTarget = linearSystem.createObjectVariable(this.mBaseline.mTarget);
                                linearSystem.addEquality(baseline2, baselineTarget, 0, i);
                                applyPosition2 = false;
                                if (verticalParentWrapContent2) {
                                    SolverVariable end = linearSystem.createObjectVariable(this.mBottom);
                                    linearSystem.addGreaterThan(parentMax2, end, r4, 5);
                                }
                                applyConstraints(system, false, verticalParentWrapContent2, horizontalParentWrapContent2, this.isTerminalWidget[i2], parentMin2, parentMax2, this.mListDimensionBehaviors[i2], i5, this.mTop, this.mBottom, this.f30mY, height2, this.mMinHeight, this.mMaxDimension[i2], this.mVerticalBiasPercent, useVerticalRatio, inVerticalChain, inHorizontalChain, isInVerticalBarrier, matchConstraintDefaultHeight, matchConstraintDefaultWidth, this.mMatchConstraintMinHeight, this.mMatchConstraintMaxHeight, this.mMatchConstraintPercentHeight, applyPosition2);
                            }
                        }
                        applyPosition2 = applyPosition;
                        applyConstraints(system, false, verticalParentWrapContent2, horizontalParentWrapContent2, this.isTerminalWidget[i2], parentMin2, parentMax2, this.mListDimensionBehaviors[i2], i5, this.mTop, this.mBottom, this.f30mY, height2, this.mMinHeight, this.mMaxDimension[i2], this.mVerticalBiasPercent, useVerticalRatio, inVerticalChain, inHorizontalChain, isInVerticalBarrier, matchConstraintDefaultHeight, matchConstraintDefaultWidth, this.mMatchConstraintMinHeight, this.mMatchConstraintMaxHeight, this.mMatchConstraintPercentHeight, applyPosition2);
                    }
                    if (useRatio2) {
                        if (this.mResolvedDimensionRatioSide == i2) {
                            system.addRatio(bottom2, top3, right, left, this.mResolvedDimensionRatio, 8);
                        } else {
                            system.addRatio(right, left, bottom2, top3, this.mResolvedDimensionRatio, 8);
                        }
                    }
                    if (this.mCenter.isConnected()) {
                        linearSystem.addCenterPoint(this, this.mCenter.getTarget().getOwner(), (float) Math.toRadians(this.mCircleConstraintAngle + 90.0f), this.mCenter.getMargin());
                        return;
                    }
                    return;
                }
                c = '\uffff';
                useHorizontalRatio = false;
                wrapContent = this.mListDimensionBehaviors[0] != DimensionBehaviour.WRAP_CONTENT && (this instanceof ConstraintWidgetContainer);
                if (!wrapContent) {
                }
                if (!this.mCenter.isConnected()) {
                }
                boolean[] zArr2 = this.mIsInBarrier;
                boolean isInHorizontalBarrier2 = zArr2[0];
                boolean isInVerticalBarrier2 = zArr2[1];
                if (this.mHorizontalResolution == 2) {
                }
                boolean applyVerticalConstraints22 = true;
                if (this.verticalRun.start.resolved) {
                }
                linearSystem = system;
                baseline2 = baseline;
                bottom2 = bottom;
                top3 = top2;
                i = 8;
                i2 = 1;
                r4 = 0;
                if (this.mVerticalResolution != 2) {
                }
                if (applyVerticalConstraints) {
                }
                if (useRatio2) {
                }
                if (this.mCenter.isConnected()) {
                }
            }
        }
        matchConstraintDefaultHeight = matchConstraintDefaultHeight2;
        height = height3;
        useRatio = useRatio3;
        matchConstraintDefaultWidth = matchConstraintDefaultWidth2;
        width = width4;
        int[] iArr2 = this.mResolvedMatchConstraintDefault;
        iArr2[0] = matchConstraintDefaultWidth;
        iArr2[1] = matchConstraintDefaultHeight;
        this.mResolvedHasRatio = useRatio;
        if (!useRatio) {
        }
        useHorizontalRatio = false;
        wrapContent = this.mListDimensionBehaviors[0] != DimensionBehaviour.WRAP_CONTENT && (this instanceof ConstraintWidgetContainer);
        if (!wrapContent) {
        }
        if (!this.mCenter.isConnected()) {
        }
        boolean[] zArr22 = this.mIsInBarrier;
        boolean isInHorizontalBarrier22 = zArr22[0];
        boolean isInVerticalBarrier22 = zArr22[1];
        if (this.mHorizontalResolution == 2) {
        }
        boolean applyVerticalConstraints222 = true;
        if (this.verticalRun.start.resolved) {
        }
        linearSystem = system;
        baseline2 = baseline;
        bottom2 = bottom;
        top3 = top2;
        i = 8;
        i2 = 1;
        r4 = 0;
        if (this.mVerticalResolution != 2) {
        }
        if (applyVerticalConstraints) {
        }
        if (useRatio2) {
        }
        if (this.mCenter.isConnected()) {
        }
    }

    boolean addFirst() {
        return (this instanceof VirtualLayout) || (this instanceof Guideline);
    }

    public void setupDimensionRatio(boolean hparentWrapContent, boolean vparentWrapContent, boolean horizontalDimensionFixed, boolean verticalDimensionFixed) {
        if (this.mResolvedDimensionRatioSide == -1) {
            if (horizontalDimensionFixed && !verticalDimensionFixed) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (!horizontalDimensionFixed && verticalDimensionFixed) {
                this.mResolvedDimensionRatioSide = 1;
                if (this.mDimensionRatioSide == -1) {
                    this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                }
            }
        }
        if (this.mResolvedDimensionRatioSide == 0 && (!this.mTop.isConnected() || !this.mBottom.isConnected())) {
            this.mResolvedDimensionRatioSide = 1;
        } else if (this.mResolvedDimensionRatioSide == 1 && (!this.mLeft.isConnected() || !this.mRight.isConnected())) {
            this.mResolvedDimensionRatioSide = 0;
        }
        if (this.mResolvedDimensionRatioSide == -1 && (!this.mTop.isConnected() || !this.mBottom.isConnected() || !this.mLeft.isConnected() || !this.mRight.isConnected())) {
            if (this.mTop.isConnected() && this.mBottom.isConnected()) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (this.mLeft.isConnected() && this.mRight.isConnected()) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide == -1) {
            int i = this.mMatchConstraintMinWidth;
            if (i > 0 && this.mMatchConstraintMinHeight == 0) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (i == 0 && this.mMatchConstraintMinHeight > 0) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:192:0x03de  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0411  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0423 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0424  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void applyConstraints(LinearSystem system, boolean isHorizontal, boolean parentWrapContent, boolean oppositeParentWrapContent, boolean isTerminal, SolverVariable parentMin, SolverVariable parentMax, DimensionBehaviour dimensionBehaviour, boolean wrapContent, ConstraintAnchor beginAnchor, ConstraintAnchor endAnchor, int beginPosition, int dimension, int minDimension, int maxDimension, float bias, boolean useRatio, boolean inChain, boolean oppositeInChain, boolean inBarrier, int matchConstraintDefault, int oppositeMatchConstraintDefault, int matchMinDimension, int matchMaxDimension, float matchPercentDimension, boolean applyPosition) {
        int dimension2;
        boolean variableSize;
        SolverVariable endTarget;
        SolverVariable beginTarget;
        int numConnections;
        SolverVariable endTarget2;
        SolverVariable end;
        boolean variableSize2;
        int matchMaxDimension2;
        int matchMinDimension2;
        boolean variableSize3;
        SolverVariable percentBegin;
        SolverVariable percentEnd;
        SolverVariable begin;
        int numConnections2;
        int i;
        SolverVariable begin2;
        SolverVariable solverVariable;
        SolverVariable endTarget3;
        int wrapStrength;
        boolean applyStrongChecks;
        int rangeCheckStrength;
        SolverVariable beginTarget2;
        boolean applyBoundsCheck;
        ConstraintWidget endWidget;
        SolverVariable beginTarget3;
        int wrapStrength2;
        SolverVariable begin3;
        SolverVariable endTarget4;
        int matchConstraintDefault2;
        int i2;
        ConstraintWidget beginWidget;
        SolverVariable beginTarget4;
        SolverVariable begin4;
        int rangeCheckStrength2;
        int boundsCheckStrength;
        int i3;
        int rangeCheckStrength3;
        int i4;
        SolverVariable begin5 = system.createObjectVariable(beginAnchor);
        SolverVariable end2 = system.createObjectVariable(endAnchor);
        SolverVariable beginTarget5 = system.createObjectVariable(beginAnchor.getTarget());
        SolverVariable endTarget5 = system.createObjectVariable(endAnchor.getTarget());
        if (LinearSystem.getMetrics() != null) {
            LinearSystem.getMetrics().nonresolvedWidgets++;
        }
        boolean isBeginConnected = beginAnchor.isConnected();
        boolean isEndConnected = endAnchor.isConnected();
        boolean isCenterConnected = this.mCenter.isConnected();
        boolean variableSize4 = false;
        int numConnections3 = isBeginConnected ? 0 + 1 : 0;
        if (isEndConnected) {
            numConnections3++;
        }
        int numConnections4 = isCenterConnected ? numConnections3 + 1 : numConnections3;
        int matchConstraintDefault3 = useRatio ? 3 : matchConstraintDefault;
        switch (C01061.f32x27577131[dimensionBehaviour.ordinal()]) {
            case 1:
                variableSize4 = false;
                break;
            case 2:
                variableSize4 = false;
                break;
            case 3:
                variableSize4 = false;
                break;
            case 4:
                if (matchConstraintDefault3 != 4) {
                    variableSize4 = true;
                    break;
                } else {
                    variableSize4 = false;
                    break;
                }
        }
        boolean variableSize5 = variableSize4;
        if (this.mVisibility == 8) {
            dimension2 = 0;
            variableSize = false;
        } else {
            dimension2 = dimension;
            variableSize = variableSize5;
        }
        if (!applyPosition) {
            endTarget = endTarget5;
        } else if (!isBeginConnected && !isEndConnected && !isCenterConnected) {
            system.addEquality(begin5, beginPosition);
            endTarget = endTarget5;
        } else if (!isBeginConnected || isEndConnected) {
            endTarget = endTarget5;
        } else {
            endTarget = endTarget5;
            system.addEquality(begin5, beginTarget5, beginAnchor.getMargin(), 8);
        }
        if (!variableSize) {
            if (wrapContent) {
                system.addEquality(end2, begin5, 0, 3);
                if (minDimension > 0) {
                    i4 = 8;
                    system.addGreaterThan(end2, begin5, minDimension, 8);
                } else {
                    i4 = 8;
                }
                if (maxDimension < Integer.MAX_VALUE) {
                    system.addLowerThan(end2, begin5, maxDimension, i4);
                }
            } else {
                system.addEquality(end2, begin5, dimension2, 8);
            }
            matchMaxDimension2 = matchMaxDimension;
            beginTarget = beginTarget5;
            end = end2;
            numConnections = numConnections4;
            variableSize2 = variableSize;
            endTarget2 = endTarget;
            variableSize3 = isTerminal;
            matchMinDimension2 = matchMinDimension;
        } else if (numConnections4 == 2 || useRatio || !(matchConstraintDefault3 == 1 || matchConstraintDefault3 == 0)) {
            int matchMinDimension3 = matchMinDimension == -2 ? dimension2 : matchMinDimension;
            int matchMaxDimension3 = matchMaxDimension == -2 ? dimension2 : matchMaxDimension;
            if (dimension2 > 0 && matchConstraintDefault3 != 1) {
                dimension2 = 0;
            }
            if (matchMinDimension3 > 0) {
                system.addGreaterThan(end2, begin5, matchMinDimension3, 8);
                dimension2 = Math.max(dimension2, matchMinDimension3);
            }
            if (matchMaxDimension3 > 0) {
                boolean applyLimit = true;
                if (parentWrapContent && matchConstraintDefault3 == 1) {
                    applyLimit = false;
                }
                if (applyLimit) {
                    system.addLowerThan(end2, begin5, matchMaxDimension3, 8);
                }
                dimension2 = Math.min(dimension2, matchMaxDimension3);
            }
            if (matchConstraintDefault3 == 1) {
                if (parentWrapContent) {
                    system.addEquality(end2, begin5, dimension2, 8);
                } else if (inChain) {
                    system.addEquality(end2, begin5, dimension2, 5);
                    system.addLowerThan(end2, begin5, dimension2, 8);
                } else {
                    system.addEquality(end2, begin5, dimension2, 5);
                    system.addLowerThan(end2, begin5, dimension2, 8);
                }
                matchMaxDimension2 = matchMaxDimension3;
                beginTarget = beginTarget5;
                numConnections = numConnections4;
                variableSize2 = variableSize;
                endTarget2 = endTarget;
                variableSize3 = isTerminal;
                matchMinDimension2 = matchMinDimension3;
                end = end2;
            } else if (matchConstraintDefault3 == 2) {
                if (beginAnchor.getType() == ConstraintAnchor.Type.TOP || beginAnchor.getType() == ConstraintAnchor.Type.BOTTOM) {
                    SolverVariable percentBegin2 = system.createObjectVariable(this.mParent.getAnchor(ConstraintAnchor.Type.TOP));
                    percentBegin = percentBegin2;
                    percentEnd = system.createObjectVariable(this.mParent.getAnchor(ConstraintAnchor.Type.BOTTOM));
                } else {
                    SolverVariable percentBegin3 = system.createObjectVariable(this.mParent.getAnchor(ConstraintAnchor.Type.LEFT));
                    percentBegin = percentBegin3;
                    percentEnd = system.createObjectVariable(this.mParent.getAnchor(ConstraintAnchor.Type.RIGHT));
                }
                numConnections = numConnections4;
                int matchMinDimension4 = matchMinDimension3;
                endTarget2 = endTarget;
                beginTarget = beginTarget5;
                end = end2;
                system.addConstraint(system.createRow().createRowDimensionRatio(end2, begin5, percentEnd, percentBegin, matchPercentDimension));
                variableSize2 = false;
                matchMaxDimension2 = matchMaxDimension3;
                variableSize3 = isTerminal;
                matchMinDimension2 = matchMinDimension4;
            } else {
                beginTarget = beginTarget5;
                numConnections = numConnections4;
                int matchMinDimension5 = matchMinDimension3;
                endTarget2 = endTarget;
                end = end2;
                variableSize2 = variableSize;
                matchMaxDimension2 = matchMaxDimension3;
                matchMinDimension2 = matchMinDimension5;
                variableSize3 = true;
            }
        } else {
            int d = Math.max(matchMinDimension, dimension2);
            if (matchMaxDimension > 0) {
                d = Math.min(matchMaxDimension, d);
            }
            system.addEquality(end2, begin5, d, 8);
            matchMaxDimension2 = matchMaxDimension;
            beginTarget = beginTarget5;
            end = end2;
            numConnections = numConnections4;
            variableSize2 = false;
            endTarget2 = endTarget;
            variableSize3 = isTerminal;
            matchMinDimension2 = matchMinDimension;
        }
        if (!applyPosition) {
            begin = begin5;
            numConnections2 = numConnections;
            i = 8;
            begin2 = parentMin;
            solverVariable = parentMax;
        } else if (!inChain) {
            if (!isBeginConnected && !isEndConnected && !isCenterConnected) {
                endTarget3 = endTarget2;
                wrapStrength = 5;
            } else if (isBeginConnected && !isEndConnected) {
                endTarget3 = endTarget2;
                wrapStrength = 5;
            } else if (!isBeginConnected && isEndConnected) {
                system.addEquality(end, endTarget2, -endAnchor.getMargin(), 8);
                if (parentWrapContent) {
                    system.addGreaterThan(begin5, parentMin, 0, 5);
                    endTarget3 = endTarget2;
                    wrapStrength = 5;
                } else {
                    endTarget3 = endTarget2;
                    wrapStrength = 5;
                }
            } else if (isBeginConnected && isEndConnected) {
                boolean applyStrongChecks2 = false;
                boolean applyRangeCheck = false;
                int boundsCheckStrength2 = 4;
                int centeringStrength = 6;
                int rangeCheckStrength4 = parentWrapContent ? 5 : 5;
                ConstraintWidget beginWidget2 = beginAnchor.mTarget.mOwner;
                int matchConstraintDefault4 = matchConstraintDefault3;
                ConstraintWidget endWidget2 = endAnchor.mTarget.mOwner;
                ConstraintWidget parent = getParent();
                if (!variableSize2) {
                    applyRangeCheck = true;
                    applyStrongChecks = false;
                    rangeCheckStrength = 1;
                } else if (matchConstraintDefault4 == 0) {
                    if (matchMaxDimension2 == 0 && matchMinDimension2 == 0) {
                        applyStrongChecks2 = true;
                        rangeCheckStrength4 = 8;
                        boundsCheckStrength2 = 8;
                        rangeCheckStrength = 0;
                    } else {
                        rangeCheckStrength = 1;
                        boundsCheckStrength2 = 5;
                        rangeCheckStrength4 = 5;
                    }
                    boolean applyStrongChecks3 = applyStrongChecks2;
                    boolean applyStrongChecks4 = beginWidget2 instanceof Barrier;
                    if (applyStrongChecks4 || (endWidget2 instanceof Barrier)) {
                        boundsCheckStrength2 = 4;
                        applyStrongChecks = applyStrongChecks3;
                    } else {
                        applyStrongChecks = applyStrongChecks3;
                    }
                } else if (matchConstraintDefault4 == 1) {
                    applyRangeCheck = true;
                    rangeCheckStrength4 = 8;
                    applyStrongChecks = false;
                    rangeCheckStrength = 1;
                } else if (matchConstraintDefault4 != 3) {
                    rangeCheckStrength = 0;
                    applyStrongChecks = false;
                } else if (this.mResolvedDimensionRatioSide == -1) {
                    applyRangeCheck = true;
                    rangeCheckStrength4 = 8;
                    boundsCheckStrength2 = 5;
                    if (oppositeInChain) {
                        boundsCheckStrength2 = 5;
                        centeringStrength = 4;
                        if (parentWrapContent) {
                            centeringStrength = 5;
                            rangeCheckStrength = 1;
                            applyStrongChecks = true;
                        } else {
                            rangeCheckStrength = 1;
                            applyStrongChecks = true;
                        }
                    } else {
                        centeringStrength = 8;
                        rangeCheckStrength = 1;
                        applyStrongChecks = true;
                    }
                } else {
                    applyRangeCheck = true;
                    if (useRatio) {
                        boolean otherSideInvariable = oppositeMatchConstraintDefault == 2 || oppositeMatchConstraintDefault == 1;
                        if (!otherSideInvariable) {
                            rangeCheckStrength4 = 8;
                            boundsCheckStrength2 = 5;
                        }
                        rangeCheckStrength = 1;
                        applyStrongChecks = true;
                    } else {
                        rangeCheckStrength4 = 5;
                        if (matchMaxDimension2 > 0) {
                            boundsCheckStrength2 = 5;
                            rangeCheckStrength = 1;
                            applyStrongChecks = true;
                        } else if (matchMaxDimension2 != 0 || matchMinDimension2 != 0) {
                            rangeCheckStrength = 1;
                            applyStrongChecks = true;
                        } else if (oppositeInChain) {
                            rangeCheckStrength4 = (beginWidget2 == parent || endWidget2 == parent) ? 5 : 4;
                            boundsCheckStrength2 = 4;
                            rangeCheckStrength = 1;
                            applyStrongChecks = true;
                        } else {
                            boundsCheckStrength2 = 8;
                            rangeCheckStrength = 1;
                            applyStrongChecks = true;
                        }
                    }
                }
                if (applyRangeCheck) {
                    beginTarget2 = beginTarget;
                    if (beginTarget2 == endTarget2 && beginWidget2 != parent) {
                        applyRangeCheck = false;
                        applyBoundsCheck = false;
                        if (rangeCheckStrength == 0) {
                            if (this.mVisibility == 8) {
                                centeringStrength = 4;
                            }
                            endWidget = endWidget2;
                            matchConstraintDefault2 = matchConstraintDefault4;
                            beginWidget = beginWidget2;
                            i2 = 8;
                            beginTarget3 = beginTarget2;
                            wrapStrength2 = 5;
                            begin3 = begin5;
                            endTarget4 = endTarget2;
                            system.addCentering(begin5, beginTarget2, beginAnchor.getMargin(), bias, endTarget2, end, endAnchor.getMargin(), centeringStrength);
                        } else {
                            endWidget = endWidget2;
                            beginTarget3 = beginTarget2;
                            wrapStrength2 = 5;
                            begin3 = begin5;
                            endTarget4 = endTarget2;
                            matchConstraintDefault2 = matchConstraintDefault4;
                            i2 = 8;
                            beginWidget = beginWidget2;
                        }
                        if (this.mVisibility != i2) {
                            return;
                        }
                        if (applyRangeCheck) {
                            if (parentWrapContent) {
                                beginTarget4 = beginTarget3;
                                endTarget3 = endTarget4;
                                if (beginTarget4 != endTarget3 && !variableSize2 && ((beginWidget instanceof Barrier) || (endWidget instanceof Barrier))) {
                                    rangeCheckStrength3 = 6;
                                    begin4 = begin3;
                                    system.addGreaterThan(begin4, beginTarget4, beginAnchor.getMargin(), rangeCheckStrength3);
                                    system.addLowerThan(end, endTarget3, -endAnchor.getMargin(), rangeCheckStrength3);
                                    rangeCheckStrength4 = rangeCheckStrength3;
                                }
                            } else {
                                beginTarget4 = beginTarget3;
                                endTarget3 = endTarget4;
                            }
                            rangeCheckStrength3 = rangeCheckStrength4;
                            begin4 = begin3;
                            system.addGreaterThan(begin4, beginTarget4, beginAnchor.getMargin(), rangeCheckStrength3);
                            system.addLowerThan(end, endTarget3, -endAnchor.getMargin(), rangeCheckStrength3);
                            rangeCheckStrength4 = rangeCheckStrength3;
                        } else {
                            beginTarget4 = beginTarget3;
                            begin4 = begin3;
                            endTarget3 = endTarget4;
                        }
                        if (!parentWrapContent || !inBarrier || (beginWidget instanceof Barrier) || (endWidget instanceof Barrier)) {
                            rangeCheckStrength2 = rangeCheckStrength4;
                            boundsCheckStrength = boundsCheckStrength2;
                        } else {
                            applyBoundsCheck = true;
                            rangeCheckStrength2 = 6;
                            boundsCheckStrength = 6;
                        }
                        if (applyBoundsCheck) {
                            if (applyStrongChecks && (!oppositeInChain || oppositeParentWrapContent)) {
                                int strength = boundsCheckStrength;
                                strength = (beginWidget == parent || endWidget == parent) ? 6 : 6;
                                strength = ((beginWidget instanceof Guideline) || (endWidget instanceof Guideline)) ? 5 : 5;
                                strength = ((beginWidget instanceof Barrier) || (endWidget instanceof Barrier)) ? 5 : 5;
                                if (oppositeInChain) {
                                    strength = 5;
                                }
                                boundsCheckStrength = Math.max(strength, boundsCheckStrength);
                            }
                            if (parentWrapContent) {
                                boundsCheckStrength = Math.min(rangeCheckStrength2, boundsCheckStrength);
                                if (useRatio && !oppositeInChain && (beginWidget == parent || endWidget == parent)) {
                                    boundsCheckStrength = 4;
                                }
                            }
                            system.addEquality(begin4, beginTarget4, beginAnchor.getMargin(), boundsCheckStrength);
                            system.addEquality(end, endTarget3, -endAnchor.getMargin(), boundsCheckStrength);
                        }
                        if (parentWrapContent) {
                            int margin = parentMin == beginTarget4 ? beginAnchor.getMargin() : 0;
                            if (beginTarget4 != parentMin) {
                                wrapStrength = wrapStrength2;
                                system.addGreaterThan(begin4, parentMin, margin, wrapStrength);
                            } else {
                                wrapStrength = wrapStrength2;
                            }
                        } else {
                            wrapStrength = wrapStrength2;
                        }
                        if (parentWrapContent && variableSize2) {
                            int i5 = i2;
                            if (minDimension == 0 && matchMinDimension2 == 0) {
                                if (!variableSize2) {
                                    i3 = 0;
                                } else if (matchConstraintDefault2 == 3) {
                                    system.addGreaterThan(end, begin4, 0, i5);
                                } else {
                                    i3 = 0;
                                }
                                system.addGreaterThan(end, begin4, i3, wrapStrength);
                            }
                        }
                    }
                } else {
                    beginTarget2 = beginTarget;
                }
                applyBoundsCheck = true;
                if (rangeCheckStrength == 0) {
                }
                if (this.mVisibility != i2) {
                }
            } else {
                endTarget3 = endTarget2;
                wrapStrength = 5;
            }
            if (parentWrapContent && variableSize3) {
                int margin2 = endAnchor.mTarget != null ? endAnchor.getMargin() : 0;
                if (endTarget3 != parentMax) {
                    system.addGreaterThan(parentMax, end, margin2, wrapStrength);
                    return;
                }
                return;
            }
            return;
        } else {
            begin = begin5;
            numConnections2 = numConnections;
            i = 8;
            begin2 = parentMin;
            solverVariable = parentMax;
        }
        if (numConnections2 < 2 && parentWrapContent && variableSize3) {
            system.addGreaterThan(begin, begin2, 0, i);
            boolean applyEnd = isHorizontal || this.mBaseline.mTarget == null;
            if (!isHorizontal && this.mBaseline.mTarget != null) {
                ConstraintWidget target = this.mBaseline.mTarget.mOwner;
                applyEnd = target.mDimensionRatio != 0.0f && target.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT && target.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT;
            }
            if (applyEnd) {
                system.addGreaterThan(solverVariable, end, 0, i);
            }
        }
    }

    /* renamed from: android.support.constraint.solver.widgets.ConstraintWidget$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C01061 {

        /* renamed from: $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type */
        static final /* synthetic */ int[] f31x1d400623;

        /* renamed from: $SwitchMap$android$support$constraint$solver$widgets$ConstraintWidget$DimensionBehaviour */
        static final /* synthetic */ int[] f32x27577131;

        static {
            int[] iArr = new int[DimensionBehaviour.values().length];
            f32x27577131 = iArr;
            try {
                iArr[DimensionBehaviour.FIXED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f32x27577131[DimensionBehaviour.WRAP_CONTENT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f32x27577131[DimensionBehaviour.MATCH_PARENT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f32x27577131[DimensionBehaviour.MATCH_CONSTRAINT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            int[] iArr2 = new int[ConstraintAnchor.Type.values().length];
            f31x1d400623 = iArr2;
            try {
                iArr2[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f31x1d400623[ConstraintAnchor.Type.TOP.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f31x1d400623[ConstraintAnchor.Type.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f31x1d400623[ConstraintAnchor.Type.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f31x1d400623[ConstraintAnchor.Type.BASELINE.ordinal()] = 5;
            } catch (NoSuchFieldError e9) {
            }
            try {
                f31x1d400623[ConstraintAnchor.Type.CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError e10) {
            }
            try {
                f31x1d400623[ConstraintAnchor.Type.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError e11) {
            }
            try {
                f31x1d400623[ConstraintAnchor.Type.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError e12) {
            }
            try {
                f31x1d400623[ConstraintAnchor.Type.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError e13) {
            }
        }
    }

    public void updateFromSolver(LinearSystem system) {
        int left = system.getObjectVariableValue(this.mLeft);
        int top = system.getObjectVariableValue(this.mTop);
        int right = system.getObjectVariableValue(this.mRight);
        int bottom = system.getObjectVariableValue(this.mBottom);
        if (this.horizontalRun.start.resolved && this.horizontalRun.end.resolved) {
            left = this.horizontalRun.start.value;
            right = this.horizontalRun.end.value;
        }
        if (this.verticalRun.start.resolved && this.verticalRun.end.resolved) {
            top = this.verticalRun.start.value;
            bottom = this.verticalRun.end.value;
        }
        int w = right - left;
        int h = bottom - top;
        if (w < 0 || h < 0 || left == Integer.MIN_VALUE || left == Integer.MAX_VALUE || top == Integer.MIN_VALUE || top == Integer.MAX_VALUE || right == Integer.MIN_VALUE || right == Integer.MAX_VALUE || bottom == Integer.MIN_VALUE || bottom == Integer.MAX_VALUE) {
            left = 0;
            top = 0;
            right = 0;
            bottom = 0;
        }
        setFrame(left, top, right, bottom);
    }

    public void copy(ConstraintWidget src, HashMap<ConstraintWidget, ConstraintWidget> map) {
        this.mHorizontalResolution = src.mHorizontalResolution;
        this.mVerticalResolution = src.mVerticalResolution;
        this.mMatchConstraintDefaultWidth = src.mMatchConstraintDefaultWidth;
        this.mMatchConstraintDefaultHeight = src.mMatchConstraintDefaultHeight;
        int[] iArr = this.mResolvedMatchConstraintDefault;
        int[] iArr2 = src.mResolvedMatchConstraintDefault;
        iArr[0] = iArr2[0];
        iArr[1] = iArr2[1];
        this.mMatchConstraintMinWidth = src.mMatchConstraintMinWidth;
        this.mMatchConstraintMaxWidth = src.mMatchConstraintMaxWidth;
        this.mMatchConstraintMinHeight = src.mMatchConstraintMinHeight;
        this.mMatchConstraintMaxHeight = src.mMatchConstraintMaxHeight;
        this.mMatchConstraintPercentHeight = src.mMatchConstraintPercentHeight;
        this.mIsWidthWrapContent = src.mIsWidthWrapContent;
        this.mIsHeightWrapContent = src.mIsHeightWrapContent;
        this.mResolvedDimensionRatioSide = src.mResolvedDimensionRatioSide;
        this.mResolvedDimensionRatio = src.mResolvedDimensionRatio;
        int[] iArr3 = src.mMaxDimension;
        this.mMaxDimension = Arrays.copyOf(iArr3, iArr3.length);
        this.mCircleConstraintAngle = src.mCircleConstraintAngle;
        this.hasBaseline = src.hasBaseline;
        this.inPlaceholder = src.inPlaceholder;
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mListDimensionBehaviors = (DimensionBehaviour[]) Arrays.copyOf(this.mListDimensionBehaviors, 2);
        this.mParent = this.mParent == null ? null : map.get(src.mParent);
        this.mWidth = src.mWidth;
        this.mHeight = src.mHeight;
        this.mDimensionRatio = src.mDimensionRatio;
        this.mDimensionRatioSide = src.mDimensionRatioSide;
        this.f29mX = src.f29mX;
        this.f30mY = src.f30mY;
        this.mRelX = src.mRelX;
        this.mRelY = src.mRelY;
        this.mOffsetX = src.mOffsetX;
        this.mOffsetY = src.mOffsetY;
        this.mBaselineDistance = src.mBaselineDistance;
        this.mMinWidth = src.mMinWidth;
        this.mMinHeight = src.mMinHeight;
        this.mHorizontalBiasPercent = src.mHorizontalBiasPercent;
        this.mVerticalBiasPercent = src.mVerticalBiasPercent;
        this.mCompanionWidget = src.mCompanionWidget;
        this.mContainerItemSkip = src.mContainerItemSkip;
        this.mVisibility = src.mVisibility;
        this.mDebugName = src.mDebugName;
        this.mType = src.mType;
        this.mDistToTop = src.mDistToTop;
        this.mDistToLeft = src.mDistToLeft;
        this.mDistToRight = src.mDistToRight;
        this.mDistToBottom = src.mDistToBottom;
        this.mLeftHasCentered = src.mLeftHasCentered;
        this.mRightHasCentered = src.mRightHasCentered;
        this.mTopHasCentered = src.mTopHasCentered;
        this.mBottomHasCentered = src.mBottomHasCentered;
        this.mHorizontalWrapVisited = src.mHorizontalWrapVisited;
        this.mVerticalWrapVisited = src.mVerticalWrapVisited;
        this.mOptimizerMeasurable = src.mOptimizerMeasurable;
        this.mGroupsToSolver = src.mGroupsToSolver;
        this.mHorizontalChainStyle = src.mHorizontalChainStyle;
        this.mVerticalChainStyle = src.mVerticalChainStyle;
        this.mHorizontalChainFixedPosition = src.mHorizontalChainFixedPosition;
        this.mVerticalChainFixedPosition = src.mVerticalChainFixedPosition;
        float[] fArr = this.mWeight;
        float[] fArr2 = src.mWeight;
        fArr[0] = fArr2[0];
        fArr[1] = fArr2[1];
        ConstraintWidget[] constraintWidgetArr = this.mListNextMatchConstraintsWidget;
        ConstraintWidget[] constraintWidgetArr2 = src.mListNextMatchConstraintsWidget;
        constraintWidgetArr[0] = constraintWidgetArr2[0];
        constraintWidgetArr[1] = constraintWidgetArr2[1];
        ConstraintWidget[] constraintWidgetArr3 = this.mNextChainWidget;
        ConstraintWidget[] constraintWidgetArr4 = src.mNextChainWidget;
        constraintWidgetArr3[0] = constraintWidgetArr4[0];
        constraintWidgetArr3[1] = constraintWidgetArr4[1];
        ConstraintWidget constraintWidget = src.mHorizontalNextWidget;
        this.mHorizontalNextWidget = constraintWidget == null ? null : map.get(constraintWidget);
        ConstraintWidget constraintWidget2 = src.mVerticalNextWidget;
        this.mVerticalNextWidget = constraintWidget2 != null ? map.get(constraintWidget2) : null;
    }

    public void updateFromRuns(boolean updateHorizontal, boolean updateVertical) {
        boolean updateHorizontal2 = updateHorizontal & this.horizontalRun.isResolved();
        boolean updateVertical2 = updateVertical & this.verticalRun.isResolved();
        int left = this.horizontalRun.start.value;
        int top = this.verticalRun.start.value;
        int right = this.horizontalRun.end.value;
        int bottom = this.verticalRun.end.value;
        int h = bottom - top;
        if (right - left < 0 || h < 0 || left == Integer.MIN_VALUE || left == Integer.MAX_VALUE || top == Integer.MIN_VALUE || top == Integer.MAX_VALUE || right == Integer.MIN_VALUE || right == Integer.MAX_VALUE || bottom == Integer.MIN_VALUE || bottom == Integer.MAX_VALUE) {
            left = 0;
            top = 0;
            right = 0;
            bottom = 0;
        }
        int w = right - left;
        int h2 = bottom - top;
        if (updateHorizontal2) {
            this.f29mX = left;
        }
        if (updateVertical2) {
            this.f30mY = top;
        }
        if (this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        if (updateHorizontal2) {
            if (this.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED && w < this.mWidth) {
                w = this.mWidth;
            }
            this.mWidth = w;
            int i = this.mMinWidth;
            if (w < i) {
                this.mWidth = i;
            }
        }
        if (updateVertical2) {
            if (this.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED && h2 < this.mHeight) {
                h2 = this.mHeight;
            }
            this.mHeight = h2;
            int i2 = this.mMinHeight;
            if (h2 < i2) {
                this.mHeight = i2;
            }
        }
    }
}
