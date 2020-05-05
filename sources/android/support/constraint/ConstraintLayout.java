package android.support.constraint;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.constraint.solver.Metrics;
import android.support.constraint.solver.widgets.Analyzer;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.ConstraintWidgetContainer;
import android.support.constraint.solver.widgets.Guideline;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.ActivityChooserView;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.HashMap;

public class ConstraintLayout extends ViewGroup {
    static final boolean ALLOWS_EMBEDDED = false;
    private static final boolean CACHE_MEASURED_DIMENSION = false;
    private static final boolean DEBUG = false;
    public static final int DESIGN_INFO_ID = 0;
    private static final String TAG = "ConstraintLayout";
    private static final boolean USE_CONSTRAINTS_HELPER = true;
    public static final String VERSION = "ConstraintLayout-1.1.3";
    SparseArray<View> mChildrenByIds = new SparseArray<>();
    private ArrayList<ConstraintHelper> mConstraintHelpers = new ArrayList<>(4);
    private ConstraintSet mConstraintSet = null;
    private int mConstraintSetId = -1;
    private HashMap<String, Integer> mDesignIds = new HashMap<>();
    private boolean mDirtyHierarchy = USE_CONSTRAINTS_HELPER;
    private int mLastMeasureHeight = -1;
    int mLastMeasureHeightMode = 0;
    int mLastMeasureHeightSize = -1;
    private int mLastMeasureWidth = -1;
    int mLastMeasureWidthMode = 0;
    int mLastMeasureWidthSize = -1;
    ConstraintWidgetContainer mLayoutWidget = new ConstraintWidgetContainer();
    private int mMaxHeight = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
    private int mMaxWidth = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
    private Metrics mMetrics;
    private int mMinHeight = 0;
    private int mMinWidth = 0;
    private int mOptimizationLevel = 7;
    private final ArrayList<ConstraintWidget> mVariableDimensionsWidgets = new ArrayList<>(100);

    public void setDesignInformation(int type, Object value1, Object value2) {
        if (type == 0 && (value1 instanceof String) && (value2 instanceof Integer)) {
            if (this.mDesignIds == null) {
                this.mDesignIds = new HashMap<>();
            }
            String name = (String) value1;
            int index = name.indexOf("/");
            if (index != -1) {
                name = name.substring(index + 1);
            }
            this.mDesignIds.put(name, Integer.valueOf(((Integer) value2).intValue()));
        }
    }

    public Object getDesignInformation(int type, Object value) {
        if (type != 0 || !(value instanceof String)) {
            return null;
        }
        String name = (String) value;
        if (this.mDesignIds == null || !this.mDesignIds.containsKey(name)) {
            return null;
        }
        return this.mDesignIds.get(name);
    }

    public ConstraintLayout(Context context) {
        super(context);
        init((AttributeSet) null);
    }

    public ConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void setId(int id) {
        this.mChildrenByIds.remove(getId());
        super.setId(id);
        this.mChildrenByIds.put(getId(), this);
    }

    private void init(AttributeSet attrs) {
        this.mLayoutWidget.setCompanionWidget(this);
        this.mChildrenByIds.put(getId(), this);
        this.mConstraintSet = null;
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ConstraintLayout_Layout);
            int N = a.getIndexCount();
            for (int i = 0; i < N; i++) {
                int attr = a.getIndex(i);
                if (attr == R.styleable.ConstraintLayout_Layout_android_minWidth) {
                    this.mMinWidth = a.getDimensionPixelOffset(attr, this.mMinWidth);
                } else if (attr == R.styleable.ConstraintLayout_Layout_android_minHeight) {
                    this.mMinHeight = a.getDimensionPixelOffset(attr, this.mMinHeight);
                } else if (attr == R.styleable.ConstraintLayout_Layout_android_maxWidth) {
                    this.mMaxWidth = a.getDimensionPixelOffset(attr, this.mMaxWidth);
                } else if (attr == R.styleable.ConstraintLayout_Layout_android_maxHeight) {
                    this.mMaxHeight = a.getDimensionPixelOffset(attr, this.mMaxHeight);
                } else if (attr == R.styleable.ConstraintLayout_Layout_layout_optimizationLevel) {
                    this.mOptimizationLevel = a.getInt(attr, this.mOptimizationLevel);
                } else if (attr == R.styleable.ConstraintLayout_Layout_constraintSet) {
                    int id = a.getResourceId(attr, 0);
                    try {
                        this.mConstraintSet = new ConstraintSet();
                        this.mConstraintSet.load(getContext(), id);
                    } catch (Resources.NotFoundException e) {
                        this.mConstraintSet = null;
                    }
                    this.mConstraintSetId = id;
                }
            }
            a.recycle();
        }
        this.mLayoutWidget.setOptimizationLevel(this.mOptimizationLevel);
    }

    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        if (Build.VERSION.SDK_INT < 14) {
            onViewAdded(child);
        }
    }

    public void removeView(View view) {
        super.removeView(view);
        if (Build.VERSION.SDK_INT < 14) {
            onViewRemoved(view);
        }
    }

    public void onViewAdded(View view) {
        if (Build.VERSION.SDK_INT >= 14) {
            super.onViewAdded(view);
        }
        ConstraintWidget widget = getViewWidget(view);
        if ((view instanceof Guideline) && !(widget instanceof Guideline)) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.widget = new Guideline();
            layoutParams.isGuideline = USE_CONSTRAINTS_HELPER;
            ((Guideline) layoutParams.widget).setOrientation(layoutParams.orientation);
        }
        if (view instanceof ConstraintHelper) {
            ConstraintHelper helper = (ConstraintHelper) view;
            helper.validateParams();
            ((LayoutParams) view.getLayoutParams()).isHelper = USE_CONSTRAINTS_HELPER;
            if (!this.mConstraintHelpers.contains(helper)) {
                this.mConstraintHelpers.add(helper);
            }
        }
        this.mChildrenByIds.put(view.getId(), view);
        this.mDirtyHierarchy = USE_CONSTRAINTS_HELPER;
    }

    public void onViewRemoved(View view) {
        if (Build.VERSION.SDK_INT >= 14) {
            super.onViewRemoved(view);
        }
        this.mChildrenByIds.remove(view.getId());
        ConstraintWidget widget = getViewWidget(view);
        this.mLayoutWidget.remove(widget);
        this.mConstraintHelpers.remove(view);
        this.mVariableDimensionsWidgets.remove(widget);
        this.mDirtyHierarchy = USE_CONSTRAINTS_HELPER;
    }

    public void setMinWidth(int value) {
        if (value != this.mMinWidth) {
            this.mMinWidth = value;
            requestLayout();
        }
    }

    public void setMinHeight(int value) {
        if (value != this.mMinHeight) {
            this.mMinHeight = value;
            requestLayout();
        }
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public void setMaxWidth(int value) {
        if (value != this.mMaxWidth) {
            this.mMaxWidth = value;
            requestLayout();
        }
    }

    public void setMaxHeight(int value) {
        if (value != this.mMaxHeight) {
            this.mMaxHeight = value;
            requestLayout();
        }
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    public int getMaxHeight() {
        return this.mMaxHeight;
    }

    private void updateHierarchy() {
        int count = getChildCount();
        boolean recompute = false;
        int i = 0;
        while (true) {
            if (i >= count) {
                break;
            } else if (getChildAt(i).isLayoutRequested()) {
                recompute = USE_CONSTRAINTS_HELPER;
                break;
            } else {
                i++;
            }
        }
        if (recompute) {
            this.mVariableDimensionsWidgets.clear();
            setChildrenConstraints();
        }
    }

    private void setChildrenConstraints() {
        int helperCount;
        int count;
        int i;
        boolean z;
        int resolveGoneRightMargin;
        int resolvedRightToLeft;
        float resolvedHorizontalBias;
        int i2;
        int resolveGoneLeftMargin;
        int resolvedLeftToRight;
        int resolvedLeftToLeft;
        LayoutParams layoutParams;
        int helperCount2;
        int resolvedRightToRight;
        float resolvedHorizontalBias2;
        ConstraintWidget target;
        ConstraintWidget target2;
        ConstraintWidget target3;
        ConstraintWidget target4;
        int resolvedLeftToLeft2;
        int resolvedLeftToLeft3;
        int resolvedLeftToLeft4;
        boolean isInEditMode = isInEditMode();
        int count2 = getChildCount();
        boolean z2 = false;
        int i3 = -1;
        if (isInEditMode) {
            int i4 = 0;
            while (true) {
                int i5 = i4;
                if (i5 >= count2) {
                    break;
                }
                View view = getChildAt(i5);
                try {
                    String IdAsString = getResources().getResourceName(view.getId());
                    setDesignInformation(0, IdAsString, Integer.valueOf(view.getId()));
                    int slashIndex = IdAsString.indexOf(47);
                    if (slashIndex != -1) {
                        IdAsString = IdAsString.substring(slashIndex + 1);
                    }
                    getTargetWidget(view.getId()).setDebugName(IdAsString);
                } catch (Resources.NotFoundException e) {
                }
                i4 = i5 + 1;
            }
        }
        for (int i6 = 0; i6 < count2; i6++) {
            ConstraintWidget widget = getViewWidget(getChildAt(i6));
            if (widget != null) {
                widget.reset();
            }
        }
        if (this.mConstraintSetId != -1) {
            for (int i7 = 0; i7 < count2; i7++) {
                View child = getChildAt(i7);
                if (child.getId() == this.mConstraintSetId && (child instanceof Constraints)) {
                    this.mConstraintSet = ((Constraints) child).getConstraintSet();
                }
            }
        }
        if (this.mConstraintSet != null) {
            this.mConstraintSet.applyToInternal(this);
        }
        this.mLayoutWidget.removeAllChildren();
        int helperCount3 = this.mConstraintHelpers.size();
        if (helperCount3 > 0) {
            for (int i8 = 0; i8 < helperCount3; i8++) {
                this.mConstraintHelpers.get(i8).updatePreLayout(this);
            }
        }
        for (int i9 = 0; i9 < count2; i9++) {
            View child2 = getChildAt(i9);
            if (child2 instanceof Placeholder) {
                ((Placeholder) child2).updatePreLayout(this);
            }
        }
        int i10 = 0;
        while (true) {
            int i11 = i10;
            if (i11 < count2) {
                View child3 = getChildAt(i11);
                ConstraintWidget widget2 = getViewWidget(child3);
                if (widget2 != null) {
                    LayoutParams layoutParams2 = (LayoutParams) child3.getLayoutParams();
                    layoutParams2.validate();
                    if (layoutParams2.helped) {
                        layoutParams2.helped = z2;
                    } else if (isInEditMode) {
                        try {
                            String IdAsString2 = getResources().getResourceName(child3.getId());
                            setDesignInformation(z2 ? 1 : 0, IdAsString2, Integer.valueOf(child3.getId()));
                            getTargetWidget(child3.getId()).setDebugName(IdAsString2.substring(IdAsString2.indexOf("id/") + 3));
                        } catch (Resources.NotFoundException e2) {
                        }
                    }
                    widget2.setVisibility(child3.getVisibility());
                    if (layoutParams2.isInPlaceholder) {
                        widget2.setVisibility(8);
                    }
                    widget2.setCompanionWidget(child3);
                    this.mLayoutWidget.add(widget2);
                    if (!layoutParams2.verticalDimensionFixed || !layoutParams2.horizontalDimensionFixed) {
                        this.mVariableDimensionsWidgets.add(widget2);
                    }
                    if (layoutParams2.isGuideline) {
                        Guideline guideline = (Guideline) widget2;
                        int resolvedGuideBegin = layoutParams2.resolvedGuideBegin;
                        int resolvedGuideEnd = layoutParams2.resolvedGuideEnd;
                        float resolvedGuidePercent = layoutParams2.resolvedGuidePercent;
                        if (Build.VERSION.SDK_INT < 17) {
                            resolvedGuideBegin = layoutParams2.guideBegin;
                            resolvedGuideEnd = layoutParams2.guideEnd;
                            resolvedGuidePercent = layoutParams2.guidePercent;
                        }
                        if (resolvedGuidePercent != -1.0f) {
                            guideline.setGuidePercent(resolvedGuidePercent);
                        } else if (resolvedGuideBegin != i3) {
                            guideline.setGuideBegin(resolvedGuideBegin);
                        } else if (resolvedGuideEnd != i3) {
                            guideline.setGuideEnd(resolvedGuideEnd);
                        }
                    } else if (!(layoutParams2.leftToLeft == i3 && layoutParams2.leftToRight == i3 && layoutParams2.rightToLeft == i3 && layoutParams2.rightToRight == i3 && layoutParams2.startToStart == i3 && layoutParams2.startToEnd == i3 && layoutParams2.endToStart == i3 && layoutParams2.endToEnd == i3 && layoutParams2.topToTop == i3 && layoutParams2.topToBottom == i3 && layoutParams2.bottomToTop == i3 && layoutParams2.bottomToBottom == i3 && layoutParams2.baselineToBaseline == i3 && layoutParams2.editorAbsoluteX == i3 && layoutParams2.editorAbsoluteY == i3 && layoutParams2.circleConstraint == i3 && layoutParams2.width != i3 && layoutParams2.height != i3)) {
                        int resolvedLeftToLeft5 = layoutParams2.resolvedLeftToLeft;
                        int resolvedLeftToRight2 = layoutParams2.resolvedLeftToRight;
                        int resolvedRightToLeft2 = layoutParams2.resolvedRightToLeft;
                        int resolvedRightToRight2 = layoutParams2.resolvedRightToRight;
                        int resolveGoneLeftMargin2 = layoutParams2.resolveGoneLeftMargin;
                        int resolveGoneRightMargin2 = layoutParams2.resolveGoneRightMargin;
                        float resolvedHorizontalBias3 = layoutParams2.resolvedHorizontalBias;
                        int resolvedLeftToLeft6 = resolvedLeftToLeft5;
                        if (Build.VERSION.SDK_INT < 17) {
                            int resolvedLeftToLeft7 = layoutParams2.leftToLeft;
                            int resolvedLeftToRight3 = layoutParams2.leftToRight;
                            int resolvedRightToLeft3 = layoutParams2.rightToLeft;
                            resolvedRightToRight2 = layoutParams2.rightToRight;
                            int i12 = layoutParams2.goneLeftMargin;
                            int resolvedLeftToRight4 = layoutParams2.goneRightMargin;
                            float resolvedHorizontalBias4 = layoutParams2.horizontalBias;
                            if (resolvedLeftToLeft7 == -1 && resolvedLeftToRight3 == -1) {
                                resolvedLeftToLeft4 = resolvedLeftToLeft7;
                                if (layoutParams2.startToStart != -1) {
                                    resolvedLeftToLeft2 = layoutParams2.startToStart;
                                    if (resolvedRightToLeft3 == -1 || resolvedRightToRight2 != -1) {
                                        resolvedLeftToLeft3 = resolvedLeftToLeft2;
                                    } else {
                                        resolvedLeftToLeft3 = resolvedLeftToLeft2;
                                        if (layoutParams2.endToStart != -1) {
                                            resolvedRightToLeft3 = layoutParams2.endToStart;
                                        } else if (layoutParams2.endToEnd != -1) {
                                            resolvedRightToRight2 = layoutParams2.endToEnd;
                                        }
                                    }
                                    resolveGoneRightMargin = resolvedLeftToRight4;
                                    resolvedLeftToRight = resolvedLeftToRight3;
                                    i2 = -1;
                                    resolvedLeftToLeft = resolvedLeftToLeft3;
                                    resolvedRightToLeft = resolvedRightToLeft3;
                                    resolvedHorizontalBias = resolvedHorizontalBias4;
                                    resolveGoneLeftMargin = i12;
                                } else if (layoutParams2.startToEnd != -1) {
                                    resolvedLeftToRight3 = layoutParams2.startToEnd;
                                }
                            } else {
                                resolvedLeftToLeft4 = resolvedLeftToLeft7;
                            }
                            resolvedLeftToLeft2 = resolvedLeftToLeft4;
                            if (resolvedRightToLeft3 == -1) {
                            }
                            resolvedLeftToLeft3 = resolvedLeftToLeft2;
                            resolveGoneRightMargin = resolvedLeftToRight4;
                            resolvedLeftToRight = resolvedLeftToRight3;
                            i2 = -1;
                            resolvedLeftToLeft = resolvedLeftToLeft3;
                            resolvedRightToLeft = resolvedRightToLeft3;
                            resolvedHorizontalBias = resolvedHorizontalBias4;
                            resolveGoneLeftMargin = i12;
                        } else {
                            i2 = -1;
                            resolvedLeftToLeft = resolvedLeftToLeft6;
                            resolveGoneRightMargin = resolveGoneRightMargin2;
                            resolvedLeftToRight = resolvedLeftToRight2;
                            int i13 = resolvedRightToLeft2;
                            resolvedHorizontalBias = resolvedHorizontalBias3;
                            resolveGoneLeftMargin = resolveGoneLeftMargin2;
                            resolvedRightToLeft = i13;
                        }
                        if (layoutParams2.circleConstraint != i2) {
                            ConstraintWidget target5 = getTargetWidget(layoutParams2.circleConstraint);
                            if (target5 != null) {
                                count = count2;
                                widget2.connectCircularConstraint(target5, layoutParams2.circleAngle, layoutParams2.circleRadius);
                            } else {
                                count = count2;
                            }
                            int i14 = resolvedLeftToLeft;
                            helperCount = helperCount3;
                            View view2 = child3;
                            float f = resolvedHorizontalBias;
                            int i15 = resolvedRightToRight2;
                            int helperCount4 = resolvedRightToLeft;
                            layoutParams = layoutParams2;
                        } else {
                            count = count2;
                            if (resolvedLeftToLeft != -1) {
                                ConstraintWidget target6 = getTargetWidget(resolvedLeftToLeft);
                                if (target6 != null) {
                                    int i16 = resolvedLeftToLeft;
                                    resolvedHorizontalBias2 = resolvedHorizontalBias;
                                    ConstraintWidget constraintWidget = target6;
                                    ConstraintWidget constraintWidget2 = target6;
                                    resolvedRightToRight = resolvedRightToRight2;
                                    helperCount = helperCount3;
                                    helperCount2 = resolvedRightToLeft;
                                    View view3 = child3;
                                    layoutParams = layoutParams2;
                                    widget2.immediateConnect(ConstraintAnchor.Type.LEFT, constraintWidget, ConstraintAnchor.Type.LEFT, layoutParams2.leftMargin, resolveGoneLeftMargin);
                                } else {
                                    helperCount = helperCount3;
                                    View view4 = child3;
                                    resolvedHorizontalBias2 = resolvedHorizontalBias;
                                    resolvedRightToRight = resolvedRightToRight2;
                                    helperCount2 = resolvedRightToLeft;
                                    layoutParams = layoutParams2;
                                }
                            } else {
                                helperCount = helperCount3;
                                View view5 = child3;
                                resolvedHorizontalBias2 = resolvedHorizontalBias;
                                resolvedRightToRight = resolvedRightToRight2;
                                helperCount2 = resolvedRightToLeft;
                                layoutParams = layoutParams2;
                                if (!(resolvedLeftToRight == -1 || (target4 = getTargetWidget(resolvedLeftToRight)) == null)) {
                                    widget2.immediateConnect(ConstraintAnchor.Type.LEFT, target4, ConstraintAnchor.Type.RIGHT, layoutParams.leftMargin, resolveGoneLeftMargin);
                                }
                            }
                            if (helperCount2 != -1) {
                                ConstraintWidget target7 = getTargetWidget(helperCount2);
                                if (target7 != null) {
                                    widget2.immediateConnect(ConstraintAnchor.Type.RIGHT, target7, ConstraintAnchor.Type.LEFT, layoutParams.rightMargin, resolveGoneRightMargin);
                                }
                            } else if (!(resolvedRightToRight == -1 || (target3 = getTargetWidget(resolvedRightToRight)) == null)) {
                                widget2.immediateConnect(ConstraintAnchor.Type.RIGHT, target3, ConstraintAnchor.Type.RIGHT, layoutParams.rightMargin, resolveGoneRightMargin);
                            }
                            if (layoutParams.topToTop != -1) {
                                ConstraintWidget target8 = getTargetWidget(layoutParams.topToTop);
                                if (target8 != null) {
                                    widget2.immediateConnect(ConstraintAnchor.Type.TOP, target8, ConstraintAnchor.Type.TOP, layoutParams.topMargin, layoutParams.goneTopMargin);
                                }
                            } else if (!(layoutParams.topToBottom == -1 || (target2 = getTargetWidget(layoutParams.topToBottom)) == null)) {
                                widget2.immediateConnect(ConstraintAnchor.Type.TOP, target2, ConstraintAnchor.Type.BOTTOM, layoutParams.topMargin, layoutParams.goneTopMargin);
                            }
                            if (layoutParams.bottomToTop != -1) {
                                ConstraintWidget target9 = getTargetWidget(layoutParams.bottomToTop);
                                if (target9 != null) {
                                    widget2.immediateConnect(ConstraintAnchor.Type.BOTTOM, target9, ConstraintAnchor.Type.TOP, layoutParams.bottomMargin, layoutParams.goneBottomMargin);
                                }
                            } else if (!(layoutParams.bottomToBottom == -1 || (target = getTargetWidget(layoutParams.bottomToBottom)) == null)) {
                                widget2.immediateConnect(ConstraintAnchor.Type.BOTTOM, target, ConstraintAnchor.Type.BOTTOM, layoutParams.bottomMargin, layoutParams.goneBottomMargin);
                            }
                            if (layoutParams.baselineToBaseline != -1) {
                                View view6 = this.mChildrenByIds.get(layoutParams.baselineToBaseline);
                                ConstraintWidget target10 = getTargetWidget(layoutParams.baselineToBaseline);
                                if (!(target10 == null || view6 == null || !(view6.getLayoutParams() instanceof LayoutParams))) {
                                    layoutParams.needsBaseline = USE_CONSTRAINTS_HELPER;
                                    ((LayoutParams) view6.getLayoutParams()).needsBaseline = USE_CONSTRAINTS_HELPER;
                                    widget2.getAnchor(ConstraintAnchor.Type.BASELINE).connect(target10.getAnchor(ConstraintAnchor.Type.BASELINE), 0, -1, ConstraintAnchor.Strength.STRONG, 0, USE_CONSTRAINTS_HELPER);
                                    widget2.getAnchor(ConstraintAnchor.Type.TOP).reset();
                                    widget2.getAnchor(ConstraintAnchor.Type.BOTTOM).reset();
                                }
                            }
                            if (resolvedHorizontalBias2 >= 0.0f && resolvedHorizontalBias2 != 0.5f) {
                                widget2.setHorizontalBiasPercent(resolvedHorizontalBias2);
                            }
                            if (layoutParams.verticalBias >= 0.0f && layoutParams.verticalBias != 0.5f) {
                                widget2.setVerticalBiasPercent(layoutParams.verticalBias);
                            }
                        }
                        if (isInEditMode && !(layoutParams.editorAbsoluteX == -1 && layoutParams.editorAbsoluteY == -1)) {
                            widget2.setOrigin(layoutParams.editorAbsoluteX, layoutParams.editorAbsoluteY);
                        }
                        if (layoutParams.horizontalDimensionFixed) {
                            widget2.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                            widget2.setWidth(layoutParams.width);
                        } else if (layoutParams.width == -1) {
                            widget2.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_PARENT);
                            widget2.getAnchor(ConstraintAnchor.Type.LEFT).mMargin = layoutParams.leftMargin;
                            widget2.getAnchor(ConstraintAnchor.Type.RIGHT).mMargin = layoutParams.rightMargin;
                        } else {
                            widget2.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
                            widget2.setWidth(0);
                        }
                        if (!layoutParams.verticalDimensionFixed) {
                            i = -1;
                            if (layoutParams.height == -1) {
                                widget2.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_PARENT);
                                widget2.getAnchor(ConstraintAnchor.Type.TOP).mMargin = layoutParams.topMargin;
                                widget2.getAnchor(ConstraintAnchor.Type.BOTTOM).mMargin = layoutParams.bottomMargin;
                                z = false;
                            } else {
                                widget2.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
                                z = false;
                                widget2.setHeight(0);
                            }
                        } else {
                            z = false;
                            i = -1;
                            widget2.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                            widget2.setHeight(layoutParams.height);
                        }
                        if (layoutParams.dimensionRatio != null) {
                            widget2.setDimensionRatio(layoutParams.dimensionRatio);
                        }
                        widget2.setHorizontalWeight(layoutParams.horizontalWeight);
                        widget2.setVerticalWeight(layoutParams.verticalWeight);
                        widget2.setHorizontalChainStyle(layoutParams.horizontalChainStyle);
                        widget2.setVerticalChainStyle(layoutParams.verticalChainStyle);
                        widget2.setHorizontalMatchStyle(layoutParams.matchConstraintDefaultWidth, layoutParams.matchConstraintMinWidth, layoutParams.matchConstraintMaxWidth, layoutParams.matchConstraintPercentWidth);
                        widget2.setVerticalMatchStyle(layoutParams.matchConstraintDefaultHeight, layoutParams.matchConstraintMinHeight, layoutParams.matchConstraintMaxHeight, layoutParams.matchConstraintPercentHeight);
                        i10 = i11 + 1;
                        z2 = z;
                        i3 = i;
                        count2 = count;
                        helperCount3 = helperCount;
                    }
                }
                count = count2;
                z = z2;
                i = i3;
                helperCount = helperCount3;
                i10 = i11 + 1;
                z2 = z;
                i3 = i;
                count2 = count;
                helperCount3 = helperCount;
            } else {
                int i17 = helperCount3;
                return;
            }
        }
    }

    private final ConstraintWidget getTargetWidget(int id) {
        if (id == 0) {
            return this.mLayoutWidget;
        }
        View view = this.mChildrenByIds.get(id);
        if (view == null && (view = findViewById(id)) != null && view != this && view.getParent() == this) {
            onViewAdded(view);
        }
        if (view == this) {
            return this.mLayoutWidget;
        }
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).widget;
    }

    public final ConstraintWidget getViewWidget(View view) {
        if (view == this) {
            return this.mLayoutWidget;
        }
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).widget;
    }

    private void internalMeasureChildren(int parentWidthSpec, int parentHeightSpec) {
        int baseline;
        int childWidthMeasureSpec;
        int childHeightMeasureSpec;
        ConstraintLayout constraintLayout = this;
        int i = parentWidthSpec;
        int i2 = parentHeightSpec;
        int heightPadding = getPaddingTop() + getPaddingBottom();
        int widthPadding = getPaddingLeft() + getPaddingRight();
        int widgetsCount = getChildCount();
        int i3 = 0;
        while (i3 < widgetsCount) {
            View child = constraintLayout.getChildAt(i3);
            if (child.getVisibility() != 8) {
                LayoutParams params = (LayoutParams) child.getLayoutParams();
                ConstraintWidget widget = params.widget;
                if (!params.isGuideline && !params.isHelper) {
                    widget.setVisibility(child.getVisibility());
                    int width = params.width;
                    int height = params.height;
                    boolean didWrapMeasureWidth = false;
                    boolean didWrapMeasureHeight = false;
                    if (params.horizontalDimensionFixed || params.verticalDimensionFixed || (!params.horizontalDimensionFixed && params.matchConstraintDefaultWidth == 1) || params.width == -1 || (!params.verticalDimensionFixed && (params.matchConstraintDefaultHeight == 1 || params.height == -1))) {
                        if (width == 0) {
                            childWidthMeasureSpec = getChildMeasureSpec(i, widthPadding, -2);
                            didWrapMeasureWidth = USE_CONSTRAINTS_HELPER;
                        } else if (width == -1) {
                            childWidthMeasureSpec = getChildMeasureSpec(i, widthPadding, -1);
                        } else {
                            if (width == -2) {
                                didWrapMeasureWidth = USE_CONSTRAINTS_HELPER;
                            }
                            childWidthMeasureSpec = getChildMeasureSpec(i, widthPadding, width);
                        }
                        int childWidthMeasureSpec2 = childWidthMeasureSpec;
                        if (height == 0) {
                            childHeightMeasureSpec = getChildMeasureSpec(i2, heightPadding, -2);
                            didWrapMeasureHeight = USE_CONSTRAINTS_HELPER;
                        } else if (height == -1) {
                            childHeightMeasureSpec = getChildMeasureSpec(i2, heightPadding, -1);
                        } else {
                            if (height == -2) {
                                didWrapMeasureHeight = USE_CONSTRAINTS_HELPER;
                            }
                            childHeightMeasureSpec = getChildMeasureSpec(i2, heightPadding, height);
                        }
                        child.measure(childWidthMeasureSpec2, childHeightMeasureSpec);
                        if (constraintLayout.mMetrics != null) {
                            constraintLayout.mMetrics.measures++;
                        }
                        widget.setWidthWrapContent(width == -2 ? USE_CONSTRAINTS_HELPER : false);
                        widget.setHeightWrapContent(height == -2 ? USE_CONSTRAINTS_HELPER : false);
                        width = child.getMeasuredWidth();
                        height = child.getMeasuredHeight();
                    }
                    widget.setWidth(width);
                    widget.setHeight(height);
                    if (didWrapMeasureWidth) {
                        widget.setWrapWidth(width);
                    }
                    if (didWrapMeasureHeight) {
                        widget.setWrapHeight(height);
                    }
                    if (params.needsBaseline && (baseline = child.getBaseline()) != -1) {
                        widget.setBaselineDistance(baseline);
                    }
                }
            }
            i3++;
            constraintLayout = this;
            i = parentWidthSpec;
        }
    }

    private void updatePostMeasures() {
        int widgetsCount = getChildCount();
        for (int i = 0; i < widgetsCount; i++) {
            View child = getChildAt(i);
            if (child instanceof Placeholder) {
                ((Placeholder) child).updatePostMeasure(this);
            }
        }
        int helperCount = this.mConstraintHelpers.size();
        if (helperCount > 0) {
            for (int i2 = 0; i2 < helperCount; i2++) {
                this.mConstraintHelpers.get(i2).updatePostMeasure(this);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:110:0x0216  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x0254  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x0275  */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x0284  */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x028d  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x028f  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x0295  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x0297  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x02ab  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x02b0  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x02b5  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x02bd  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x02c6  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x02ce  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x02db  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x02e6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void internalMeasureDimensions(int r31, int r32) {
        /*
            r30 = this;
            r0 = r30
            r1 = r31
            r2 = r32
            int r3 = r30.getPaddingTop()
            int r4 = r30.getPaddingBottom()
            int r3 = r3 + r4
            int r4 = r30.getPaddingLeft()
            int r5 = r30.getPaddingRight()
            int r4 = r4 + r5
            int r5 = r30.getChildCount()
            r7 = 0
        L_0x001d:
            r10 = 8
            r12 = -2
            if (r7 >= r5) goto L_0x00e0
            android.view.View r14 = r0.getChildAt(r7)
            int r15 = r14.getVisibility()
            if (r15 != r10) goto L_0x002e
            goto L_0x00dc
        L_0x002e:
            android.view.ViewGroup$LayoutParams r10 = r14.getLayoutParams()
            android.support.constraint.ConstraintLayout$LayoutParams r10 = (android.support.constraint.ConstraintLayout.LayoutParams) r10
            android.support.constraint.solver.widgets.ConstraintWidget r15 = r10.widget
            boolean r6 = r10.isGuideline
            if (r6 != 0) goto L_0x00dc
            boolean r6 = r10.isHelper
            if (r6 == 0) goto L_0x0040
            goto L_0x00dc
        L_0x0040:
            int r6 = r14.getVisibility()
            r15.setVisibility(r6)
            int r6 = r10.width
            int r13 = r10.height
            if (r6 == 0) goto L_0x00cd
            if (r13 != 0) goto L_0x0051
            goto L_0x00cd
        L_0x0051:
            r16 = 0
            r17 = 0
            if (r6 != r12) goto L_0x0059
            r16 = 1
        L_0x0059:
            int r11 = getChildMeasureSpec(r1, r4, r6)
            if (r13 != r12) goto L_0x0061
            r17 = 1
        L_0x0061:
            int r12 = getChildMeasureSpec(r2, r3, r13)
            r14.measure(r11, r12)
            android.support.constraint.solver.Metrics r8 = r0.mMetrics
            if (r8 == 0) goto L_0x007b
            android.support.constraint.solver.Metrics r8 = r0.mMetrics
            r20 = r11
            r21 = r12
            long r11 = r8.measures
            r18 = 1
            long r11 = r11 + r18
            r8.measures = r11
            goto L_0x007f
        L_0x007b:
            r20 = r11
            r21 = r12
        L_0x007f:
            r8 = -2
            if (r6 != r8) goto L_0x0084
            r9 = 1
            goto L_0x0085
        L_0x0084:
            r9 = 0
        L_0x0085:
            r15.setWidthWrapContent(r9)
            if (r13 != r8) goto L_0x008c
            r8 = 1
            goto L_0x008d
        L_0x008c:
            r8 = 0
        L_0x008d:
            r15.setHeightWrapContent(r8)
            int r6 = r14.getMeasuredWidth()
            int r8 = r14.getMeasuredHeight()
            r15.setWidth(r6)
            r15.setHeight(r8)
            if (r16 == 0) goto L_0x00a3
            r15.setWrapWidth(r6)
        L_0x00a3:
            if (r17 == 0) goto L_0x00a8
            r15.setWrapHeight(r8)
        L_0x00a8:
            boolean r9 = r10.needsBaseline
            if (r9 == 0) goto L_0x00b6
            int r9 = r14.getBaseline()
            r11 = -1
            if (r9 == r11) goto L_0x00b6
            r15.setBaselineDistance(r9)
        L_0x00b6:
            boolean r9 = r10.horizontalDimensionFixed
            if (r9 == 0) goto L_0x00dc
            boolean r9 = r10.verticalDimensionFixed
            if (r9 == 0) goto L_0x00dc
            android.support.constraint.solver.widgets.ResolutionDimension r9 = r15.getResolutionWidth()
            r9.resolve(r6)
            android.support.constraint.solver.widgets.ResolutionDimension r9 = r15.getResolutionHeight()
            r9.resolve(r8)
            goto L_0x00dc
        L_0x00cd:
            android.support.constraint.solver.widgets.ResolutionDimension r8 = r15.getResolutionWidth()
            r8.invalidate()
            android.support.constraint.solver.widgets.ResolutionDimension r8 = r15.getResolutionHeight()
            r8.invalidate()
        L_0x00dc:
            int r7 = r7 + 1
            goto L_0x001d
        L_0x00e0:
            android.support.constraint.solver.widgets.ConstraintWidgetContainer r6 = r0.mLayoutWidget
            r6.solveGraph()
            r6 = 0
        L_0x00e6:
            if (r6 >= r5) goto L_0x0303
            android.view.View r7 = r0.getChildAt(r6)
            int r8 = r7.getVisibility()
            if (r8 != r10) goto L_0x0100
        L_0x00f3:
            r28 = r3
            r29 = r4
            r22 = r5
            r23 = r6
        L_0x00fb:
            r2 = -1
            r18 = 1
            goto L_0x02f3
        L_0x0100:
            android.view.ViewGroup$LayoutParams r8 = r7.getLayoutParams()
            android.support.constraint.ConstraintLayout$LayoutParams r8 = (android.support.constraint.ConstraintLayout.LayoutParams) r8
            android.support.constraint.solver.widgets.ConstraintWidget r9 = r8.widget
            boolean r11 = r8.isGuideline
            if (r11 != 0) goto L_0x02e8
            boolean r11 = r8.isHelper
            if (r11 == 0) goto L_0x0111
            goto L_0x00f3
        L_0x0111:
            int r11 = r7.getVisibility()
            r9.setVisibility(r11)
            int r11 = r8.width
            int r12 = r8.height
            if (r11 == 0) goto L_0x0121
            if (r12 == 0) goto L_0x0121
            goto L_0x00f3
        L_0x0121:
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r13 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.LEFT
            android.support.constraint.solver.widgets.ConstraintAnchor r13 = r9.getAnchor(r13)
            android.support.constraint.solver.widgets.ResolutionAnchor r13 = r13.getResolutionNode()
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r14 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.RIGHT
            android.support.constraint.solver.widgets.ConstraintAnchor r14 = r9.getAnchor(r14)
            android.support.constraint.solver.widgets.ResolutionAnchor r14 = r14.getResolutionNode()
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r15 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.LEFT
            android.support.constraint.solver.widgets.ConstraintAnchor r15 = r9.getAnchor(r15)
            android.support.constraint.solver.widgets.ConstraintAnchor r15 = r15.getTarget()
            if (r15 == 0) goto L_0x014f
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r15 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.RIGHT
            android.support.constraint.solver.widgets.ConstraintAnchor r15 = r9.getAnchor(r15)
            android.support.constraint.solver.widgets.ConstraintAnchor r15 = r15.getTarget()
            if (r15 == 0) goto L_0x014f
            r15 = 1
            goto L_0x0150
        L_0x014f:
            r15 = 0
        L_0x0150:
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r10 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r9.getAnchor(r10)
            android.support.constraint.solver.widgets.ResolutionAnchor r10 = r10.getResolutionNode()
            r22 = r5
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r5 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r9.getAnchor(r5)
            android.support.constraint.solver.widgets.ResolutionAnchor r5 = r5.getResolutionNode()
            r23 = r6
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r6 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r9.getAnchor(r6)
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r6.getTarget()
            if (r6 == 0) goto L_0x0182
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r6 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r9.getAnchor(r6)
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r6.getTarget()
            if (r6 == 0) goto L_0x0182
            r6 = 1
            goto L_0x0183
        L_0x0182:
            r6 = 0
        L_0x0183:
            if (r11 != 0) goto L_0x0192
            if (r12 != 0) goto L_0x0192
            if (r15 == 0) goto L_0x0192
            if (r6 == 0) goto L_0x0192
            r28 = r3
            r29 = r4
            goto L_0x00fb
        L_0x0192:
            r16 = 0
            r17 = 0
            r24 = r8
            android.support.constraint.solver.widgets.ConstraintWidgetContainer r8 = r0.mLayoutWidget
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r8 = r8.getHorizontalDimensionBehaviour()
            r25 = r7
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r8 == r7) goto L_0x01a6
            r7 = 1
            goto L_0x01a7
        L_0x01a6:
            r7 = 0
        L_0x01a7:
            android.support.constraint.solver.widgets.ConstraintWidgetContainer r8 = r0.mLayoutWidget
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r8 = r8.getVerticalDimensionBehaviour()
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r0 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r8 == r0) goto L_0x01b3
            r0 = 1
            goto L_0x01b4
        L_0x01b3:
            r0 = 0
        L_0x01b4:
            if (r7 != 0) goto L_0x01bd
            android.support.constraint.solver.widgets.ResolutionDimension r8 = r9.getResolutionWidth()
            r8.invalidate()
        L_0x01bd:
            if (r0 != 0) goto L_0x01c6
            android.support.constraint.solver.widgets.ResolutionDimension r8 = r9.getResolutionHeight()
            r8.invalidate()
        L_0x01c6:
            if (r11 != 0) goto L_0x01fe
            if (r7 == 0) goto L_0x01f5
            boolean r8 = r9.isSpreadWidth()
            if (r8 == 0) goto L_0x01f5
            if (r15 == 0) goto L_0x01f5
            boolean r8 = r13.isResolved()
            if (r8 == 0) goto L_0x01f5
            boolean r8 = r14.isResolved()
            if (r8 == 0) goto L_0x01f5
            float r8 = r14.getResolvedValue()
            float r20 = r13.getResolvedValue()
            float r8 = r8 - r20
            int r11 = (int) r8
            android.support.constraint.solver.widgets.ResolutionDimension r8 = r9.getResolutionWidth()
            r8.resolve(r11)
            int r8 = getChildMeasureSpec(r1, r4, r11)
            goto L_0x0210
        L_0x01f5:
            r8 = -2
            int r20 = getChildMeasureSpec(r1, r4, r8)
            r16 = 1
            r7 = 0
            goto L_0x0212
        L_0x01fe:
            r8 = -1
            if (r11 != r8) goto L_0x0206
            int r20 = getChildMeasureSpec(r1, r4, r8)
            goto L_0x0212
        L_0x0206:
            r8 = -2
            if (r11 != r8) goto L_0x020c
            r8 = 1
            r16 = r8
        L_0x020c:
            int r8 = getChildMeasureSpec(r1, r4, r11)
        L_0x0210:
            r20 = r8
        L_0x0212:
            r8 = r20
            if (r12 != 0) goto L_0x0254
            if (r0 == 0) goto L_0x0245
            boolean r20 = r9.isSpreadHeight()
            if (r20 == 0) goto L_0x0245
            if (r6 == 0) goto L_0x0245
            boolean r20 = r10.isResolved()
            if (r20 == 0) goto L_0x0245
            boolean r20 = r5.isResolved()
            if (r20 == 0) goto L_0x0245
            float r20 = r5.getResolvedValue()
            float r21 = r10.getResolvedValue()
            r26 = r0
            float r0 = r20 - r21
            int r12 = (int) r0
            android.support.constraint.solver.widgets.ResolutionDimension r0 = r9.getResolutionHeight()
            r0.resolve(r12)
            int r0 = getChildMeasureSpec(r2, r3, r12)
            goto L_0x0268
        L_0x0245:
            r26 = r0
            r0 = -2
            int r20 = getChildMeasureSpec(r2, r3, r0)
            r17 = 1
            r0 = 0
            r26 = r0
        L_0x0251:
            r0 = r20
            goto L_0x0268
        L_0x0254:
            r26 = r0
            r0 = -1
            if (r12 != r0) goto L_0x025e
            int r20 = getChildMeasureSpec(r2, r3, r0)
            goto L_0x0251
        L_0x025e:
            r0 = -2
            if (r12 != r0) goto L_0x0264
            r0 = 1
            r17 = r0
        L_0x0264:
            int r0 = getChildMeasureSpec(r2, r3, r12)
        L_0x0268:
            r1 = r25
            r1.measure(r8, r0)
            r27 = r0
            r0 = r30
            android.support.constraint.solver.Metrics r2 = r0.mMetrics
            if (r2 == 0) goto L_0x0284
            android.support.constraint.solver.Metrics r2 = r0.mMetrics
            r28 = r3
            r29 = r4
            long r3 = r2.measures
            r18 = 1
            long r3 = r3 + r18
            r2.measures = r3
            goto L_0x028a
        L_0x0284:
            r28 = r3
            r29 = r4
            r18 = 1
        L_0x028a:
            r2 = -2
            if (r11 != r2) goto L_0x028f
            r3 = 1
            goto L_0x0290
        L_0x028f:
            r3 = 0
        L_0x0290:
            r9.setWidthWrapContent(r3)
            if (r12 != r2) goto L_0x0297
            r3 = 1
            goto L_0x0298
        L_0x0297:
            r3 = 0
        L_0x0298:
            r9.setHeightWrapContent(r3)
            int r3 = r1.getMeasuredWidth()
            int r4 = r1.getMeasuredHeight()
            r9.setWidth(r3)
            r9.setHeight(r4)
            if (r16 == 0) goto L_0x02ae
            r9.setWrapWidth(r3)
        L_0x02ae:
            if (r17 == 0) goto L_0x02b3
            r9.setWrapHeight(r4)
        L_0x02b3:
            if (r7 == 0) goto L_0x02bd
            android.support.constraint.solver.widgets.ResolutionDimension r11 = r9.getResolutionWidth()
            r11.resolve(r3)
            goto L_0x02c4
        L_0x02bd:
            android.support.constraint.solver.widgets.ResolutionDimension r11 = r9.getResolutionWidth()
            r11.remove()
        L_0x02c4:
            if (r26 == 0) goto L_0x02ce
            android.support.constraint.solver.widgets.ResolutionDimension r11 = r9.getResolutionHeight()
            r11.resolve(r4)
            goto L_0x02d5
        L_0x02ce:
            android.support.constraint.solver.widgets.ResolutionDimension r11 = r9.getResolutionHeight()
            r11.remove()
        L_0x02d5:
            r11 = r24
            boolean r12 = r11.needsBaseline
            if (r12 == 0) goto L_0x02e6
            int r12 = r1.getBaseline()
            r2 = -1
            if (r12 == r2) goto L_0x02f3
            r9.setBaselineDistance(r12)
            goto L_0x02f3
        L_0x02e6:
            r2 = -1
            goto L_0x02f3
        L_0x02e8:
            r28 = r3
            r29 = r4
            r22 = r5
            r23 = r6
            r2 = -1
            r18 = 1
        L_0x02f3:
            int r6 = r23 + 1
            r5 = r22
            r3 = r28
            r4 = r29
            r1 = r31
            r2 = r32
            r10 = 8
            goto L_0x00e6
        L_0x0303:
            r28 = r3
            r29 = r4
            r22 = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.ConstraintLayout.internalMeasureDimensions(int, int):void");
    }

    public void fillMetrics(Metrics metrics) {
        this.mMetrics = metrics;
        this.mLayoutWidget.fillMetrics(metrics);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int REMEASURES_B;
        int childState;
        int startingWidth;
        boolean containerWrapWidth;
        int startingWidth2;
        int i;
        int startingWidth3;
        int startingHeight;
        int widthSpec;
        int heightSpec;
        int baseline;
        int i2 = widthMeasureSpec;
        int i3 = heightMeasureSpec;
        long time = System.currentTimeMillis();
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        this.mLayoutWidget.setX(paddingLeft);
        this.mLayoutWidget.setY(paddingTop);
        this.mLayoutWidget.setMaxWidth(this.mMaxWidth);
        this.mLayoutWidget.setMaxHeight(this.mMaxHeight);
        if (Build.VERSION.SDK_INT >= 17) {
            this.mLayoutWidget.setRtl(getLayoutDirection() == 1);
        }
        setSelfDimensionBehaviour(widthMeasureSpec, heightMeasureSpec);
        int startingWidth4 = this.mLayoutWidget.getWidth();
        int startingHeight2 = this.mLayoutWidget.getHeight();
        boolean runAnalyzer = false;
        if (this.mDirtyHierarchy) {
            this.mDirtyHierarchy = false;
            updateHierarchy();
            runAnalyzer = USE_CONSTRAINTS_HELPER;
        }
        long j = time;
        boolean optimiseDimensions = (this.mOptimizationLevel & 8) == 8 ? USE_CONSTRAINTS_HELPER : false;
        if (optimiseDimensions) {
            this.mLayoutWidget.preOptimize();
            this.mLayoutWidget.optimizeForDimensions(startingWidth4, startingHeight2);
            internalMeasureDimensions(widthMeasureSpec, heightMeasureSpec);
        } else {
            internalMeasureChildren(widthMeasureSpec, heightMeasureSpec);
        }
        updatePostMeasures();
        if (getChildCount() > 0 && runAnalyzer) {
            Analyzer.determineGroups(this.mLayoutWidget);
        }
        if (this.mLayoutWidget.mGroupsWrapOptimized) {
            if (this.mLayoutWidget.mHorizontalWrapOptimized && widthMode == Integer.MIN_VALUE) {
                if (this.mLayoutWidget.mWrapFixedWidth < widthSize) {
                    this.mLayoutWidget.setWidth(this.mLayoutWidget.mWrapFixedWidth);
                }
                this.mLayoutWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
            }
            if (this.mLayoutWidget.mVerticalWrapOptimized && heightMode == Integer.MIN_VALUE) {
                if (this.mLayoutWidget.mWrapFixedHeight < heightSize) {
                    this.mLayoutWidget.setHeight(this.mLayoutWidget.mWrapFixedHeight);
                }
                this.mLayoutWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
            }
        }
        int REMEASURES_A = 0;
        if ((this.mOptimizationLevel & 32) == 32) {
            int width = this.mLayoutWidget.getWidth();
            int height = this.mLayoutWidget.getHeight();
            if (this.mLastMeasureWidth == width || widthMode != 1073741824) {
                REMEASURES_B = 0;
            } else {
                REMEASURES_B = 0;
                Analyzer.setPosition(this.mLayoutWidget.mWidgetGroups, 0, width);
            }
            if (this.mLastMeasureHeight != height && heightMode == 1073741824) {
                Analyzer.setPosition(this.mLayoutWidget.mWidgetGroups, 1, height);
            }
            if (this.mLayoutWidget.mHorizontalWrapOptimized && this.mLayoutWidget.mWrapFixedWidth > widthSize) {
                Analyzer.setPosition(this.mLayoutWidget.mWidgetGroups, 0, widthSize);
            }
            if (this.mLayoutWidget.mVerticalWrapOptimized && this.mLayoutWidget.mWrapFixedHeight > heightSize) {
                Analyzer.setPosition(this.mLayoutWidget.mWidgetGroups, 1, heightSize);
            }
        } else {
            REMEASURES_B = 0;
        }
        if (getChildCount() > 0) {
            solveLinearSystem("First pass");
        }
        int sizeDependentWidgetsCount = this.mVariableDimensionsWidgets.size();
        int heightPadding = getPaddingBottom() + paddingTop;
        int widthPadding = paddingLeft + getPaddingRight();
        if (sizeDependentWidgetsCount > 0) {
            boolean needSolverPass = false;
            int i4 = widthMode;
            boolean containerWrapWidth2 = this.mLayoutWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT ? USE_CONSTRAINTS_HELPER : false;
            int i5 = widthSize;
            boolean containerWrapHeight = this.mLayoutWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT ? USE_CONSTRAINTS_HELPER : false;
            int i6 = heightMode;
            int i7 = heightSize;
            int i8 = paddingLeft;
            childState = 0;
            int minWidth = Math.max(this.mLayoutWidget.getWidth(), this.mMinWidth);
            int minHeight = Math.max(this.mLayoutWidget.getHeight(), this.mMinHeight);
            int i9 = 0;
            while (i9 < sizeDependentWidgetsCount) {
                int paddingTop2 = paddingTop;
                ConstraintWidget widget = this.mVariableDimensionsWidgets.get(i9);
                int sizeDependentWidgetsCount2 = sizeDependentWidgetsCount;
                View child = (View) widget.getCompanionWidget();
                if (child == null) {
                    i = i9;
                    startingWidth3 = startingWidth4;
                    startingHeight = startingHeight2;
                } else {
                    startingHeight = startingHeight2;
                    LayoutParams params = (LayoutParams) child.getLayoutParams();
                    startingWidth3 = startingWidth4;
                    if (params.isHelper != 0) {
                        i = i9;
                    } else if (params.isGuideline) {
                        i = i9;
                    } else {
                        i = i9;
                        if (child.getVisibility() != 8 && (!optimiseDimensions || !widget.getResolutionWidth().isResolved() || !widget.getResolutionHeight().isResolved())) {
                            if (params.width != -2 || !params.horizontalDimensionFixed) {
                                widthSpec = View.MeasureSpec.makeMeasureSpec(widget.getWidth(), 1073741824);
                            } else {
                                widthSpec = getChildMeasureSpec(i2, widthPadding, params.width);
                            }
                            if (params.height != -2 || !params.verticalDimensionFixed) {
                                heightSpec = View.MeasureSpec.makeMeasureSpec(widget.getHeight(), 1073741824);
                            } else {
                                heightSpec = getChildMeasureSpec(i3, heightPadding, params.height);
                            }
                            child.measure(widthSpec, heightSpec);
                            if (this.mMetrics != null) {
                                int i10 = heightSpec;
                                this.mMetrics.additionalMeasures++;
                            }
                            REMEASURES_A++;
                            int measuredWidth = child.getMeasuredWidth();
                            int measuredHeight = child.getMeasuredHeight();
                            if (measuredWidth != widget.getWidth()) {
                                widget.setWidth(measuredWidth);
                                if (optimiseDimensions) {
                                    widget.getResolutionWidth().resolve(measuredWidth);
                                }
                                if (!containerWrapWidth2 || widget.getRight() <= minWidth) {
                                } else {
                                    int i11 = measuredWidth;
                                    minWidth = Math.max(minWidth, widget.getRight() + widget.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin());
                                }
                                needSolverPass = USE_CONSTRAINTS_HELPER;
                            }
                            if (measuredHeight != widget.getHeight()) {
                                widget.setHeight(measuredHeight);
                                if (optimiseDimensions) {
                                    widget.getResolutionHeight().resolve(measuredHeight);
                                }
                                if (containerWrapHeight && widget.getBottom() > minHeight) {
                                    minHeight = Math.max(minHeight, widget.getBottom() + widget.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin());
                                }
                                needSolverPass = USE_CONSTRAINTS_HELPER;
                            }
                            if (!(!params.needsBaseline || (baseline = child.getBaseline()) == -1 || baseline == widget.getBaselineDistance())) {
                                widget.setBaselineDistance(baseline);
                                needSolverPass = USE_CONSTRAINTS_HELPER;
                            }
                            if (Build.VERSION.SDK_INT >= 11) {
                                childState = combineMeasuredStates(childState, child.getMeasuredState());
                            }
                        }
                    }
                }
                i9 = i + 1;
                paddingTop = paddingTop2;
                sizeDependentWidgetsCount = sizeDependentWidgetsCount2;
                startingHeight2 = startingHeight;
                startingWidth4 = startingWidth3;
                i2 = widthMeasureSpec;
                i3 = heightMeasureSpec;
            }
            int sizeDependentWidgetsCount3 = sizeDependentWidgetsCount;
            int i12 = paddingTop;
            int startingWidth5 = startingWidth4;
            int startingHeight3 = startingHeight2;
            if (needSolverPass) {
                startingWidth = startingWidth5;
                this.mLayoutWidget.setWidth(startingWidth);
                this.mLayoutWidget.setHeight(startingHeight3);
                if (optimiseDimensions) {
                    this.mLayoutWidget.solveGraph();
                }
                solveLinearSystem("2nd pass");
                boolean needSolverPass2 = false;
                if (this.mLayoutWidget.getWidth() < minWidth) {
                    this.mLayoutWidget.setWidth(minWidth);
                    needSolverPass2 = USE_CONSTRAINTS_HELPER;
                }
                if (this.mLayoutWidget.getHeight() < minHeight) {
                    this.mLayoutWidget.setHeight(minHeight);
                    needSolverPass2 = USE_CONSTRAINTS_HELPER;
                }
                if (needSolverPass2) {
                    solveLinearSystem("3rd pass");
                }
            } else {
                startingWidth = startingWidth5;
            }
            int i13 = 0;
            while (true) {
                int i14 = i13;
                int sizeDependentWidgetsCount4 = sizeDependentWidgetsCount3;
                if (i14 >= sizeDependentWidgetsCount4) {
                    break;
                }
                ConstraintWidget widget2 = this.mVariableDimensionsWidgets.get(i14);
                View child2 = (View) widget2.getCompanionWidget();
                if (child2 == null) {
                    startingWidth2 = startingWidth;
                } else {
                    startingWidth2 = startingWidth;
                    if (!(child2.getMeasuredWidth() == widget2.getWidth() && child2.getMeasuredHeight() == widget2.getHeight())) {
                        if (widget2.getVisibility() != 8) {
                            int widthSpec2 = View.MeasureSpec.makeMeasureSpec(widget2.getWidth(), 1073741824);
                            containerWrapWidth = containerWrapWidth2;
                            int heightSpec2 = View.MeasureSpec.makeMeasureSpec(widget2.getHeight(), 1073741824);
                            child2.measure(widthSpec2, heightSpec2);
                            if (this.mMetrics != null) {
                                int i15 = widthSpec2;
                                int i16 = heightSpec2;
                                this.mMetrics.additionalMeasures++;
                            } else {
                                int i17 = heightSpec2;
                            }
                            REMEASURES_B++;
                        } else {
                            containerWrapWidth = containerWrapWidth2;
                        }
                        i13 = i14 + 1;
                        sizeDependentWidgetsCount3 = sizeDependentWidgetsCount4;
                        startingWidth = startingWidth2;
                        containerWrapWidth2 = containerWrapWidth;
                    }
                }
                containerWrapWidth = containerWrapWidth2;
                i13 = i14 + 1;
                sizeDependentWidgetsCount3 = sizeDependentWidgetsCount4;
                startingWidth = startingWidth2;
                containerWrapWidth2 = containerWrapWidth;
            }
        } else {
            int i18 = widthSize;
            int i19 = heightMode;
            int i20 = heightSize;
            int i21 = paddingLeft;
            int i22 = paddingTop;
            int i23 = startingWidth4;
            int widthSize2 = sizeDependentWidgetsCount;
            int sizeDependentWidgetsCount5 = startingHeight2;
            childState = 0;
        }
        int androidLayoutWidth = this.mLayoutWidget.getWidth() + widthPadding;
        int androidLayoutHeight = this.mLayoutWidget.getHeight() + heightPadding;
        if (Build.VERSION.SDK_INT >= 11) {
            int resolvedWidthSize = resolveSizeAndState(androidLayoutWidth, widthMeasureSpec, childState);
            int resolvedHeightSize = resolveSizeAndState(androidLayoutHeight, heightMeasureSpec, childState << 16);
            int resolvedWidthSize2 = resolvedWidthSize & ViewCompat.MEASURED_SIZE_MASK;
            int resolvedHeightSize2 = resolvedHeightSize & ViewCompat.MEASURED_SIZE_MASK;
            int resolvedWidthSize3 = Math.min(this.mMaxWidth, resolvedWidthSize2);
            int resolvedHeightSize3 = Math.min(this.mMaxHeight, resolvedHeightSize2);
            if (this.mLayoutWidget.isWidthMeasuredTooSmall()) {
                resolvedWidthSize3 |= 16777216;
            }
            if (this.mLayoutWidget.isHeightMeasuredTooSmall()) {
                resolvedHeightSize3 |= 16777216;
            }
            setMeasuredDimension(resolvedWidthSize3, resolvedHeightSize3);
            this.mLastMeasureWidth = resolvedWidthSize3;
            this.mLastMeasureHeight = resolvedHeightSize3;
            return;
        }
        int i24 = widthMeasureSpec;
        int i25 = heightMeasureSpec;
        setMeasuredDimension(androidLayoutWidth, androidLayoutHeight);
        this.mLastMeasureWidth = androidLayoutWidth;
        this.mLastMeasureHeight = androidLayoutHeight;
    }

    private void setSelfDimensionBehaviour(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
        int heightPadding = getPaddingTop() + getPaddingBottom();
        int widthPadding = getPaddingLeft() + getPaddingRight();
        ConstraintWidget.DimensionBehaviour widthBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
        ConstraintWidget.DimensionBehaviour heightBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
        int desiredWidth = 0;
        int desiredHeight = 0;
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (widthMode == Integer.MIN_VALUE) {
            widthBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            desiredWidth = widthSize;
        } else if (widthMode == 0) {
            widthBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        } else if (widthMode == 1073741824) {
            desiredWidth = Math.min(this.mMaxWidth, widthSize) - widthPadding;
        }
        if (heightMode == Integer.MIN_VALUE) {
            heightBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            desiredHeight = heightSize;
        } else if (heightMode == 0) {
            heightBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        } else if (heightMode == 1073741824) {
            desiredHeight = Math.min(this.mMaxHeight, heightSize) - heightPadding;
        }
        this.mLayoutWidget.setMinWidth(0);
        this.mLayoutWidget.setMinHeight(0);
        this.mLayoutWidget.setHorizontalDimensionBehaviour(widthBehaviour);
        this.mLayoutWidget.setWidth(desiredWidth);
        this.mLayoutWidget.setVerticalDimensionBehaviour(heightBehaviour);
        this.mLayoutWidget.setHeight(desiredHeight);
        this.mLayoutWidget.setMinWidth((this.mMinWidth - getPaddingLeft()) - getPaddingRight());
        this.mLayoutWidget.setMinHeight((this.mMinHeight - getPaddingTop()) - getPaddingBottom());
    }

    /* access modifiers changed from: protected */
    public void solveLinearSystem(String reason) {
        this.mLayoutWidget.layout();
        if (this.mMetrics != null) {
            this.mMetrics.resolutions++;
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        View content;
        int widgetsCount = getChildCount();
        boolean isInEditMode = isInEditMode();
        for (int i = 0; i < widgetsCount; i++) {
            View child = getChildAt(i);
            LayoutParams params = (LayoutParams) child.getLayoutParams();
            ConstraintWidget widget = params.widget;
            if ((child.getVisibility() != 8 || params.isGuideline || params.isHelper || isInEditMode) && !params.isInPlaceholder) {
                int l = widget.getDrawX();
                int t = widget.getDrawY();
                int r = widget.getWidth() + l;
                int b = widget.getHeight() + t;
                child.layout(l, t, r, b);
                if ((child instanceof Placeholder) && (content = ((Placeholder) child).getContent()) != null) {
                    content.setVisibility(0);
                    content.layout(l, t, r, b);
                }
            }
        }
        int helperCount = this.mConstraintHelpers.size();
        if (helperCount > 0) {
            for (int i2 = 0; i2 < helperCount; i2++) {
                this.mConstraintHelpers.get(i2).updatePostLayout(this);
            }
        }
    }

    public void setOptimizationLevel(int level) {
        this.mLayoutWidget.setOptimizationLevel(level);
    }

    public int getOptimizationLevel() {
        return this.mLayoutWidget.getOptimizationLevel();
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    public void setConstraintSet(ConstraintSet set) {
        this.mConstraintSet = set;
    }

    public View getViewById(int id) {
        return this.mChildrenByIds.get(id);
    }

    public void dispatchDraw(Canvas canvas) {
        float ow;
        float ch;
        float cw;
        int count;
        super.dispatchDraw(canvas);
        if (isInEditMode()) {
            int count2 = getChildCount();
            float cw2 = (float) getWidth();
            float ch2 = (float) getHeight();
            float ow2 = 1080.0f;
            char c = 0;
            int i = 0;
            while (i < count2) {
                View child = getChildAt(i);
                if (child.getVisibility() == 8) {
                    count = count2;
                    cw = cw2;
                    ch = ch2;
                    ow = ow2;
                } else {
                    Object tag = child.getTag();
                    if (tag != null && (tag instanceof String)) {
                        String[] split = ((String) tag).split(",");
                        if (split.length == 4) {
                            int x = Integer.parseInt(split[c]);
                            int x2 = (int) ((((float) x) / ow2) * cw2);
                            int y = (int) ((((float) Integer.parseInt(split[1])) / 1920.0f) * ch2);
                            int w = (int) ((((float) Integer.parseInt(split[2])) / ow2) * cw2);
                            int h = (int) ((((float) Integer.parseInt(split[3])) / 1920.0f) * ch2);
                            Paint paint = new Paint();
                            count = count2;
                            paint.setColor(SupportMenu.CATEGORY_MASK);
                            cw = cw2;
                            ch = ch2;
                            ow = ow2;
                            Canvas canvas2 = canvas;
                            Paint paint2 = paint;
                            canvas2.drawLine((float) x2, (float) y, (float) (x2 + w), (float) y, paint2);
                            canvas2.drawLine((float) (x2 + w), (float) y, (float) (x2 + w), (float) (y + h), paint2);
                            canvas2.drawLine((float) (x2 + w), (float) (y + h), (float) x2, (float) (y + h), paint2);
                            canvas2.drawLine((float) x2, (float) (y + h), (float) x2, (float) y, paint2);
                            paint.setColor(-16711936);
                            canvas2.drawLine((float) x2, (float) y, (float) (x2 + w), (float) (y + h), paint2);
                            canvas2.drawLine((float) x2, (float) (y + h), (float) (x2 + w), (float) y, paint2);
                        }
                    }
                    count = count2;
                    cw = cw2;
                    ch = ch2;
                    ow = ow2;
                }
                i++;
                count2 = count;
                cw2 = cw;
                ch2 = ch;
                ow2 = ow;
                c = 0;
            }
        }
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public static final int BASELINE = 5;
        public static final int BOTTOM = 4;
        public static final int CHAIN_PACKED = 2;
        public static final int CHAIN_SPREAD = 0;
        public static final int CHAIN_SPREAD_INSIDE = 1;
        public static final int END = 7;
        public static final int HORIZONTAL = 0;
        public static final int LEFT = 1;
        public static final int MATCH_CONSTRAINT = 0;
        public static final int MATCH_CONSTRAINT_PERCENT = 2;
        public static final int MATCH_CONSTRAINT_SPREAD = 0;
        public static final int MATCH_CONSTRAINT_WRAP = 1;
        public static final int PARENT_ID = 0;
        public static final int RIGHT = 2;
        public static final int START = 6;
        public static final int TOP = 3;
        public static final int UNSET = -1;
        public static final int VERTICAL = 1;
        public int baselineToBaseline;
        public int bottomToBottom;
        public int bottomToTop;
        public float circleAngle;
        public int circleConstraint;
        public int circleRadius;
        public boolean constrainedHeight;
        public boolean constrainedWidth;
        public String dimensionRatio;
        int dimensionRatioSide;
        float dimensionRatioValue;
        public int editorAbsoluteX;
        public int editorAbsoluteY;
        public int endToEnd;
        public int endToStart;
        public int goneBottomMargin;
        public int goneEndMargin;
        public int goneLeftMargin;
        public int goneRightMargin;
        public int goneStartMargin;
        public int goneTopMargin;
        public int guideBegin;
        public int guideEnd;
        public float guidePercent;
        public boolean helped;
        public float horizontalBias;
        public int horizontalChainStyle;
        boolean horizontalDimensionFixed;
        public float horizontalWeight;
        boolean isGuideline;
        boolean isHelper;
        boolean isInPlaceholder;
        public int leftToLeft;
        public int leftToRight;
        public int matchConstraintDefaultHeight;
        public int matchConstraintDefaultWidth;
        public int matchConstraintMaxHeight;
        public int matchConstraintMaxWidth;
        public int matchConstraintMinHeight;
        public int matchConstraintMinWidth;
        public float matchConstraintPercentHeight;
        public float matchConstraintPercentWidth;
        boolean needsBaseline;
        public int orientation;
        int resolveGoneLeftMargin;
        int resolveGoneRightMargin;
        int resolvedGuideBegin;
        int resolvedGuideEnd;
        float resolvedGuidePercent;
        float resolvedHorizontalBias;
        int resolvedLeftToLeft;
        int resolvedLeftToRight;
        int resolvedRightToLeft;
        int resolvedRightToRight;
        public int rightToLeft;
        public int rightToRight;
        public int startToEnd;
        public int startToStart;
        public int topToBottom;
        public int topToTop;
        public float verticalBias;
        public int verticalChainStyle;
        boolean verticalDimensionFixed;
        public float verticalWeight;
        ConstraintWidget widget;

        public void reset() {
            if (this.widget != null) {
                this.widget.reset();
            }
        }

        public LayoutParams(LayoutParams source) {
            super(source);
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.circleConstraint = -1;
            this.circleRadius = 0;
            this.circleAngle = 0.0f;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.goneLeftMargin = -1;
            this.goneTopMargin = -1;
            this.goneRightMargin = -1;
            this.goneBottomMargin = -1;
            this.goneStartMargin = -1;
            this.goneEndMargin = -1;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.dimensionRatioValue = 0.0f;
            this.dimensionRatioSide = 1;
            this.horizontalWeight = -1.0f;
            this.verticalWeight = -1.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.matchConstraintDefaultWidth = 0;
            this.matchConstraintDefaultHeight = 0;
            this.matchConstraintMinWidth = 0;
            this.matchConstraintMinHeight = 0;
            this.matchConstraintMaxWidth = 0;
            this.matchConstraintMaxHeight = 0;
            this.matchConstraintPercentWidth = 1.0f;
            this.matchConstraintPercentHeight = 1.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.horizontalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.verticalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.needsBaseline = false;
            this.isGuideline = false;
            this.isHelper = false;
            this.isInPlaceholder = false;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolveGoneLeftMargin = -1;
            this.resolveGoneRightMargin = -1;
            this.resolvedHorizontalBias = 0.5f;
            this.widget = new ConstraintWidget();
            this.helped = false;
            this.guideBegin = source.guideBegin;
            this.guideEnd = source.guideEnd;
            this.guidePercent = source.guidePercent;
            this.leftToLeft = source.leftToLeft;
            this.leftToRight = source.leftToRight;
            this.rightToLeft = source.rightToLeft;
            this.rightToRight = source.rightToRight;
            this.topToTop = source.topToTop;
            this.topToBottom = source.topToBottom;
            this.bottomToTop = source.bottomToTop;
            this.bottomToBottom = source.bottomToBottom;
            this.baselineToBaseline = source.baselineToBaseline;
            this.circleConstraint = source.circleConstraint;
            this.circleRadius = source.circleRadius;
            this.circleAngle = source.circleAngle;
            this.startToEnd = source.startToEnd;
            this.startToStart = source.startToStart;
            this.endToStart = source.endToStart;
            this.endToEnd = source.endToEnd;
            this.goneLeftMargin = source.goneLeftMargin;
            this.goneTopMargin = source.goneTopMargin;
            this.goneRightMargin = source.goneRightMargin;
            this.goneBottomMargin = source.goneBottomMargin;
            this.goneStartMargin = source.goneStartMargin;
            this.goneEndMargin = source.goneEndMargin;
            this.horizontalBias = source.horizontalBias;
            this.verticalBias = source.verticalBias;
            this.dimensionRatio = source.dimensionRatio;
            this.dimensionRatioValue = source.dimensionRatioValue;
            this.dimensionRatioSide = source.dimensionRatioSide;
            this.horizontalWeight = source.horizontalWeight;
            this.verticalWeight = source.verticalWeight;
            this.horizontalChainStyle = source.horizontalChainStyle;
            this.verticalChainStyle = source.verticalChainStyle;
            this.constrainedWidth = source.constrainedWidth;
            this.constrainedHeight = source.constrainedHeight;
            this.matchConstraintDefaultWidth = source.matchConstraintDefaultWidth;
            this.matchConstraintDefaultHeight = source.matchConstraintDefaultHeight;
            this.matchConstraintMinWidth = source.matchConstraintMinWidth;
            this.matchConstraintMaxWidth = source.matchConstraintMaxWidth;
            this.matchConstraintMinHeight = source.matchConstraintMinHeight;
            this.matchConstraintMaxHeight = source.matchConstraintMaxHeight;
            this.matchConstraintPercentWidth = source.matchConstraintPercentWidth;
            this.matchConstraintPercentHeight = source.matchConstraintPercentHeight;
            this.editorAbsoluteX = source.editorAbsoluteX;
            this.editorAbsoluteY = source.editorAbsoluteY;
            this.orientation = source.orientation;
            this.horizontalDimensionFixed = source.horizontalDimensionFixed;
            this.verticalDimensionFixed = source.verticalDimensionFixed;
            this.needsBaseline = source.needsBaseline;
            this.isGuideline = source.isGuideline;
            this.resolvedLeftToLeft = source.resolvedLeftToLeft;
            this.resolvedLeftToRight = source.resolvedLeftToRight;
            this.resolvedRightToLeft = source.resolvedRightToLeft;
            this.resolvedRightToRight = source.resolvedRightToRight;
            this.resolveGoneLeftMargin = source.resolveGoneLeftMargin;
            this.resolveGoneRightMargin = source.resolveGoneRightMargin;
            this.resolvedHorizontalBias = source.resolvedHorizontalBias;
            this.widget = source.widget;
        }

        private static class Table {
            public static final int ANDROID_ORIENTATION = 1;
            public static final int LAYOUT_CONSTRAINED_HEIGHT = 28;
            public static final int LAYOUT_CONSTRAINED_WIDTH = 27;
            public static final int LAYOUT_CONSTRAINT_BASELINE_CREATOR = 43;
            public static final int LAYOUT_CONSTRAINT_BASELINE_TO_BASELINE_OF = 16;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_CREATOR = 42;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_TO_BOTTOM_OF = 15;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_TO_TOP_OF = 14;
            public static final int LAYOUT_CONSTRAINT_CIRCLE = 2;
            public static final int LAYOUT_CONSTRAINT_CIRCLE_ANGLE = 4;
            public static final int LAYOUT_CONSTRAINT_CIRCLE_RADIUS = 3;
            public static final int LAYOUT_CONSTRAINT_DIMENSION_RATIO = 44;
            public static final int LAYOUT_CONSTRAINT_END_TO_END_OF = 20;
            public static final int LAYOUT_CONSTRAINT_END_TO_START_OF = 19;
            public static final int LAYOUT_CONSTRAINT_GUIDE_BEGIN = 5;
            public static final int LAYOUT_CONSTRAINT_GUIDE_END = 6;
            public static final int LAYOUT_CONSTRAINT_GUIDE_PERCENT = 7;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_DEFAULT = 32;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_MAX = 37;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_MIN = 36;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_PERCENT = 38;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_BIAS = 29;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_CHAINSTYLE = 47;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_WEIGHT = 45;
            public static final int LAYOUT_CONSTRAINT_LEFT_CREATOR = 39;
            public static final int LAYOUT_CONSTRAINT_LEFT_TO_LEFT_OF = 8;
            public static final int LAYOUT_CONSTRAINT_LEFT_TO_RIGHT_OF = 9;
            public static final int LAYOUT_CONSTRAINT_RIGHT_CREATOR = 41;
            public static final int LAYOUT_CONSTRAINT_RIGHT_TO_LEFT_OF = 10;
            public static final int LAYOUT_CONSTRAINT_RIGHT_TO_RIGHT_OF = 11;
            public static final int LAYOUT_CONSTRAINT_START_TO_END_OF = 17;
            public static final int LAYOUT_CONSTRAINT_START_TO_START_OF = 18;
            public static final int LAYOUT_CONSTRAINT_TOP_CREATOR = 40;
            public static final int LAYOUT_CONSTRAINT_TOP_TO_BOTTOM_OF = 13;
            public static final int LAYOUT_CONSTRAINT_TOP_TO_TOP_OF = 12;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_BIAS = 30;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE = 48;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_WEIGHT = 46;
            public static final int LAYOUT_CONSTRAINT_WIDTH_DEFAULT = 31;
            public static final int LAYOUT_CONSTRAINT_WIDTH_MAX = 34;
            public static final int LAYOUT_CONSTRAINT_WIDTH_MIN = 33;
            public static final int LAYOUT_CONSTRAINT_WIDTH_PERCENT = 35;
            public static final int LAYOUT_EDITOR_ABSOLUTEX = 49;
            public static final int LAYOUT_EDITOR_ABSOLUTEY = 50;
            public static final int LAYOUT_GONE_MARGIN_BOTTOM = 24;
            public static final int LAYOUT_GONE_MARGIN_END = 26;
            public static final int LAYOUT_GONE_MARGIN_LEFT = 21;
            public static final int LAYOUT_GONE_MARGIN_RIGHT = 23;
            public static final int LAYOUT_GONE_MARGIN_START = 25;
            public static final int LAYOUT_GONE_MARGIN_TOP = 22;
            public static final int UNUSED = 0;
            public static final SparseIntArray map = new SparseIntArray();

            private Table() {
            }

            static {
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toLeftOf, 8);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toRightOf, 9);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_toLeftOf, 10);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_toRightOf, 11);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_toTopOf, 12);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_toBottomOf, 13);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toTopOf, 14);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toBottomOf, 15);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_toBaselineOf, 16);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircle, 2);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircleRadius, 3);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircleAngle, 4);
                map.append(R.styleable.ConstraintLayout_Layout_layout_editor_absoluteX, 49);
                map.append(R.styleable.ConstraintLayout_Layout_layout_editor_absoluteY, 50);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_begin, 5);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_end, 6);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_percent, 7);
                map.append(R.styleable.ConstraintLayout_Layout_android_orientation, 1);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintStart_toEndOf, 17);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintStart_toStartOf, 18);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toStartOf, 19);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toEndOf, 20);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginLeft, 21);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginTop, 22);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginRight, 23);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginBottom, 24);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginStart, 25);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginEnd, 26);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_bias, 29);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_bias, 30);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintDimensionRatio, 44);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_weight, 45);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_weight, 46);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_chainStyle, 47);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_chainStyle, 48);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constrainedWidth, 27);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constrainedHeight, 28);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_default, 31);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_default, 32);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_min, 33);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_max, 34);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_percent, 35);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_min, 36);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_max, 37);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_percent, 38);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_creator, 39);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_creator, 40);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_creator, 41);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_creator, 42);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_creator, 43);
            }
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* JADX WARNING: Code restructure failed: missing block: B:5:0x00b1, code lost:
            r19 = r3;
            r3 = r2;
            r2 = r19;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public LayoutParams(android.content.Context r21, android.util.AttributeSet r22) {
            /*
                r20 = this;
                r1 = r20
                r20.<init>(r21, r22)
                r2 = -1
                r1.guideBegin = r2
                r1.guideEnd = r2
                r0 = -1082130432(0xffffffffbf800000, float:-1.0)
                r1.guidePercent = r0
                r1.leftToLeft = r2
                r1.leftToRight = r2
                r1.rightToLeft = r2
                r1.rightToRight = r2
                r1.topToTop = r2
                r1.topToBottom = r2
                r1.bottomToTop = r2
                r1.bottomToBottom = r2
                r1.baselineToBaseline = r2
                r1.circleConstraint = r2
                r3 = 0
                r1.circleRadius = r3
                r4 = 0
                r1.circleAngle = r4
                r1.startToEnd = r2
                r1.startToStart = r2
                r1.endToStart = r2
                r1.endToEnd = r2
                r1.goneLeftMargin = r2
                r1.goneTopMargin = r2
                r1.goneRightMargin = r2
                r1.goneBottomMargin = r2
                r1.goneStartMargin = r2
                r1.goneEndMargin = r2
                r5 = 1056964608(0x3f000000, float:0.5)
                r1.horizontalBias = r5
                r1.verticalBias = r5
                r6 = 0
                r1.dimensionRatio = r6
                r1.dimensionRatioValue = r4
                r6 = 1
                r1.dimensionRatioSide = r6
                r1.horizontalWeight = r0
                r1.verticalWeight = r0
                r1.horizontalChainStyle = r3
                r1.verticalChainStyle = r3
                r1.matchConstraintDefaultWidth = r3
                r1.matchConstraintDefaultHeight = r3
                r1.matchConstraintMinWidth = r3
                r1.matchConstraintMinHeight = r3
                r1.matchConstraintMaxWidth = r3
                r1.matchConstraintMaxHeight = r3
                r0 = 1065353216(0x3f800000, float:1.0)
                r1.matchConstraintPercentWidth = r0
                r1.matchConstraintPercentHeight = r0
                r1.editorAbsoluteX = r2
                r1.editorAbsoluteY = r2
                r1.orientation = r2
                r1.constrainedWidth = r3
                r1.constrainedHeight = r3
                r1.horizontalDimensionFixed = r6
                r1.verticalDimensionFixed = r6
                r1.needsBaseline = r3
                r1.isGuideline = r3
                r1.isHelper = r3
                r1.isInPlaceholder = r3
                r1.resolvedLeftToLeft = r2
                r1.resolvedLeftToRight = r2
                r1.resolvedRightToLeft = r2
                r1.resolvedRightToRight = r2
                r1.resolveGoneLeftMargin = r2
                r1.resolveGoneRightMargin = r2
                r1.resolvedHorizontalBias = r5
                android.support.constraint.solver.widgets.ConstraintWidget r0 = new android.support.constraint.solver.widgets.ConstraintWidget
                r0.<init>()
                r1.widget = r0
                r1.helped = r3
                int[] r0 = android.support.constraint.R.styleable.ConstraintLayout_Layout
                r5 = r21
                r7 = r22
                android.content.res.TypedArray r8 = r5.obtainStyledAttributes(r7, r0)
                int r9 = r8.getIndexCount()
                r0 = r3
            L_0x00a0:
                r10 = r0
                if (r10 >= r9) goto L_0x0459
                int r11 = r8.getIndex(r10)
                android.util.SparseIntArray r0 = android.support.constraint.ConstraintLayout.LayoutParams.Table.map
                int r12 = r0.get(r11)
                r13 = -2
                switch(r12) {
                    case 0: goto L_0x044b;
                    case 1: goto L_0x043d;
                    case 2: goto L_0x0428;
                    case 3: goto L_0x041c;
                    case 4: goto L_0x0402;
                    case 5: goto L_0x03f8;
                    case 6: goto L_0x03ee;
                    case 7: goto L_0x03e4;
                    case 8: goto L_0x03cc;
                    case 9: goto L_0x03b3;
                    case 10: goto L_0x039a;
                    case 11: goto L_0x0381;
                    case 12: goto L_0x0368;
                    case 13: goto L_0x034f;
                    case 14: goto L_0x0336;
                    case 15: goto L_0x031d;
                    case 16: goto L_0x0304;
                    case 17: goto L_0x02eb;
                    case 18: goto L_0x02d2;
                    case 19: goto L_0x02b9;
                    case 20: goto L_0x02a3;
                    case 21: goto L_0x0298;
                    case 22: goto L_0x028d;
                    case 23: goto L_0x0282;
                    case 24: goto L_0x0277;
                    case 25: goto L_0x026c;
                    case 26: goto L_0x0261;
                    case 27: goto L_0x0256;
                    case 28: goto L_0x024b;
                    case 29: goto L_0x0240;
                    case 30: goto L_0x0235;
                    case 31: goto L_0x0221;
                    case 32: goto L_0x020c;
                    case 33: goto L_0x01f7;
                    case 34: goto L_0x01e2;
                    case 35: goto L_0x01d5;
                    case 36: goto L_0x01c0;
                    case 37: goto L_0x01aa;
                    case 38: goto L_0x019c;
                    case 39: goto L_0x019a;
                    case 40: goto L_0x0198;
                    case 41: goto L_0x0196;
                    case 42: goto L_0x0194;
                    case 43: goto L_0x00b1;
                    case 44: goto L_0x00ea;
                    case 45: goto L_0x00e1;
                    case 46: goto L_0x00d8;
                    case 47: goto L_0x00d1;
                    case 48: goto L_0x00ca;
                    case 49: goto L_0x00c1;
                    case 50: goto L_0x00b8;
                    default: goto L_0x00b1;
                }
            L_0x00b1:
                r19 = r3
                r3 = r2
                r2 = r19
                goto L_0x0450
            L_0x00b8:
                int r0 = r1.editorAbsoluteY
                int r0 = r8.getDimensionPixelOffset(r11, r0)
                r1.editorAbsoluteY = r0
                goto L_0x00b1
            L_0x00c1:
                int r0 = r1.editorAbsoluteX
                int r0 = r8.getDimensionPixelOffset(r11, r0)
                r1.editorAbsoluteX = r0
                goto L_0x00b1
            L_0x00ca:
                int r0 = r8.getInt(r11, r3)
                r1.verticalChainStyle = r0
                goto L_0x00b1
            L_0x00d1:
                int r0 = r8.getInt(r11, r3)
                r1.horizontalChainStyle = r0
                goto L_0x00b1
            L_0x00d8:
                float r0 = r1.verticalWeight
                float r0 = r8.getFloat(r11, r0)
                r1.verticalWeight = r0
                goto L_0x00b1
            L_0x00e1:
                float r0 = r1.horizontalWeight
                float r0 = r8.getFloat(r11, r0)
                r1.horizontalWeight = r0
                goto L_0x00b1
            L_0x00ea:
                java.lang.String r0 = r8.getString(r11)
                r1.dimensionRatio = r0
                r0 = 2143289344(0x7fc00000, float:NaN)
                r1.dimensionRatioValue = r0
                r1.dimensionRatioSide = r2
                java.lang.String r0 = r1.dimensionRatio
                if (r0 == 0) goto L_0x00b1
                java.lang.String r0 = r1.dimensionRatio
                int r13 = r0.length()
                java.lang.String r0 = r1.dimensionRatio
                r14 = 44
                int r0 = r0.indexOf(r14)
                if (r0 <= 0) goto L_0x012c
                int r14 = r13 + -1
                if (r0 >= r14) goto L_0x012c
                java.lang.String r14 = r1.dimensionRatio
                java.lang.String r14 = r14.substring(r3, r0)
                java.lang.String r15 = "W"
                boolean r15 = r14.equalsIgnoreCase(r15)
                if (r15 == 0) goto L_0x011f
                r1.dimensionRatioSide = r3
                goto L_0x0129
            L_0x011f:
                java.lang.String r15 = "H"
                boolean r15 = r14.equalsIgnoreCase(r15)
                if (r15 == 0) goto L_0x0129
                r1.dimensionRatioSide = r6
            L_0x0129:
                int r0 = r0 + 1
                goto L_0x012d
            L_0x012c:
                r0 = 0
            L_0x012d:
                r14 = r0
                java.lang.String r0 = r1.dimensionRatio
                r15 = 58
                int r15 = r0.indexOf(r15)
                if (r15 < 0) goto L_0x017e
                int r0 = r13 + -1
                if (r15 >= r0) goto L_0x017e
                java.lang.String r0 = r1.dimensionRatio
                java.lang.String r16 = r0.substring(r14, r15)
                java.lang.String r0 = r1.dimensionRatio
                int r2 = r15 + 1
                java.lang.String r2 = r0.substring(r2)
                int r0 = r16.length()
                if (r0 <= 0) goto L_0x017d
                int r0 = r2.length()
                if (r0 <= 0) goto L_0x017d
                float r0 = java.lang.Float.parseFloat(r16)     // Catch:{ NumberFormatException -> 0x017c }
                float r17 = java.lang.Float.parseFloat(r2)     // Catch:{ NumberFormatException -> 0x017c }
                int r18 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
                if (r18 <= 0) goto L_0x017b
                int r18 = (r17 > r4 ? 1 : (r17 == r4 ? 0 : -1))
                if (r18 <= 0) goto L_0x017b
                int r3 = r1.dimensionRatioSide     // Catch:{ NumberFormatException -> 0x017c }
                if (r3 != r6) goto L_0x0173
                float r3 = r17 / r0
                float r3 = java.lang.Math.abs(r3)     // Catch:{ NumberFormatException -> 0x017c }
                r1.dimensionRatioValue = r3     // Catch:{ NumberFormatException -> 0x017c }
                goto L_0x017b
            L_0x0173:
                float r3 = r0 / r17
                float r3 = java.lang.Math.abs(r3)     // Catch:{ NumberFormatException -> 0x017c }
                r1.dimensionRatioValue = r3     // Catch:{ NumberFormatException -> 0x017c }
            L_0x017b:
                goto L_0x017d
            L_0x017c:
                r0 = move-exception
            L_0x017d:
                goto L_0x0192
            L_0x017e:
                java.lang.String r0 = r1.dimensionRatio
                java.lang.String r2 = r0.substring(r14)
                int r0 = r2.length()
                if (r0 <= 0) goto L_0x0192
                float r0 = java.lang.Float.parseFloat(r2)     // Catch:{ NumberFormatException -> 0x0191 }
                r1.dimensionRatioValue = r0     // Catch:{ NumberFormatException -> 0x0191 }
                goto L_0x0192
            L_0x0191:
                r0 = move-exception
            L_0x0192:
                goto L_0x021e
            L_0x0194:
                goto L_0x021e
            L_0x0196:
                goto L_0x021e
            L_0x0198:
                goto L_0x021e
            L_0x019a:
                goto L_0x021e
            L_0x019c:
                float r0 = r1.matchConstraintPercentHeight
                float r0 = r8.getFloat(r11, r0)
                float r0 = java.lang.Math.max(r4, r0)
                r1.matchConstraintPercentHeight = r0
                goto L_0x021e
            L_0x01aa:
                int r0 = r1.matchConstraintMaxHeight     // Catch:{ Exception -> 0x01b4 }
                int r0 = r8.getDimensionPixelSize(r11, r0)     // Catch:{ Exception -> 0x01b4 }
                r1.matchConstraintMaxHeight = r0     // Catch:{ Exception -> 0x01b4 }
                goto L_0x021e
            L_0x01b4:
                r0 = move-exception
                int r2 = r1.matchConstraintMaxHeight
                int r2 = r8.getInt(r11, r2)
                if (r2 != r13) goto L_0x01bf
                r1.matchConstraintMaxHeight = r13
            L_0x01bf:
                goto L_0x021e
            L_0x01c0:
                int r0 = r1.matchConstraintMinHeight     // Catch:{ Exception -> 0x01c9 }
                int r0 = r8.getDimensionPixelSize(r11, r0)     // Catch:{ Exception -> 0x01c9 }
                r1.matchConstraintMinHeight = r0     // Catch:{ Exception -> 0x01c9 }
                goto L_0x021e
            L_0x01c9:
                r0 = move-exception
                int r2 = r1.matchConstraintMinHeight
                int r2 = r8.getInt(r11, r2)
                if (r2 != r13) goto L_0x01d4
                r1.matchConstraintMinHeight = r13
            L_0x01d4:
                goto L_0x021e
            L_0x01d5:
                float r0 = r1.matchConstraintPercentWidth
                float r0 = r8.getFloat(r11, r0)
                float r0 = java.lang.Math.max(r4, r0)
                r1.matchConstraintPercentWidth = r0
                goto L_0x021e
            L_0x01e2:
                int r0 = r1.matchConstraintMaxWidth     // Catch:{ Exception -> 0x01eb }
                int r0 = r8.getDimensionPixelSize(r11, r0)     // Catch:{ Exception -> 0x01eb }
                r1.matchConstraintMaxWidth = r0     // Catch:{ Exception -> 0x01eb }
                goto L_0x021e
            L_0x01eb:
                r0 = move-exception
                int r2 = r1.matchConstraintMaxWidth
                int r2 = r8.getInt(r11, r2)
                if (r2 != r13) goto L_0x01f6
                r1.matchConstraintMaxWidth = r13
            L_0x01f6:
                goto L_0x021e
            L_0x01f7:
                int r0 = r1.matchConstraintMinWidth     // Catch:{ Exception -> 0x0200 }
                int r0 = r8.getDimensionPixelSize(r11, r0)     // Catch:{ Exception -> 0x0200 }
                r1.matchConstraintMinWidth = r0     // Catch:{ Exception -> 0x0200 }
                goto L_0x021e
            L_0x0200:
                r0 = move-exception
                int r2 = r1.matchConstraintMinWidth
                int r2 = r8.getInt(r11, r2)
                if (r2 != r13) goto L_0x020b
                r1.matchConstraintMinWidth = r13
            L_0x020b:
                goto L_0x021e
            L_0x020c:
                r2 = 0
                int r0 = r8.getInt(r11, r2)
                r1.matchConstraintDefaultHeight = r0
                int r0 = r1.matchConstraintDefaultHeight
                if (r0 != r6) goto L_0x021e
                java.lang.String r0 = "ConstraintLayout"
                java.lang.String r2 = "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead."
                android.util.Log.e(r0, r2)
            L_0x021e:
                r2 = 0
                goto L_0x0426
            L_0x0221:
                r2 = 0
                int r0 = r8.getInt(r11, r2)
                r1.matchConstraintDefaultWidth = r0
                int r0 = r1.matchConstraintDefaultWidth
                if (r0 != r6) goto L_0x0426
                java.lang.String r0 = "ConstraintLayout"
                java.lang.String r3 = "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead."
                android.util.Log.e(r0, r3)
                goto L_0x0426
            L_0x0235:
                r2 = r3
                float r0 = r1.verticalBias
                float r0 = r8.getFloat(r11, r0)
                r1.verticalBias = r0
                goto L_0x0426
            L_0x0240:
                r2 = r3
                float r0 = r1.horizontalBias
                float r0 = r8.getFloat(r11, r0)
                r1.horizontalBias = r0
                goto L_0x0426
            L_0x024b:
                r2 = r3
                boolean r0 = r1.constrainedHeight
                boolean r0 = r8.getBoolean(r11, r0)
                r1.constrainedHeight = r0
                goto L_0x0426
            L_0x0256:
                r2 = r3
                boolean r0 = r1.constrainedWidth
                boolean r0 = r8.getBoolean(r11, r0)
                r1.constrainedWidth = r0
                goto L_0x0426
            L_0x0261:
                r2 = r3
                int r0 = r1.goneEndMargin
                int r0 = r8.getDimensionPixelSize(r11, r0)
                r1.goneEndMargin = r0
                goto L_0x0426
            L_0x026c:
                r2 = r3
                int r0 = r1.goneStartMargin
                int r0 = r8.getDimensionPixelSize(r11, r0)
                r1.goneStartMargin = r0
                goto L_0x0426
            L_0x0277:
                r2 = r3
                int r0 = r1.goneBottomMargin
                int r0 = r8.getDimensionPixelSize(r11, r0)
                r1.goneBottomMargin = r0
                goto L_0x0426
            L_0x0282:
                r2 = r3
                int r0 = r1.goneRightMargin
                int r0 = r8.getDimensionPixelSize(r11, r0)
                r1.goneRightMargin = r0
                goto L_0x0426
            L_0x028d:
                r2 = r3
                int r0 = r1.goneTopMargin
                int r0 = r8.getDimensionPixelSize(r11, r0)
                r1.goneTopMargin = r0
                goto L_0x0426
            L_0x0298:
                r2 = r3
                int r0 = r1.goneLeftMargin
                int r0 = r8.getDimensionPixelSize(r11, r0)
                r1.goneLeftMargin = r0
                goto L_0x0426
            L_0x02a3:
                r2 = r3
                int r0 = r1.endToEnd
                int r0 = r8.getResourceId(r11, r0)
                r1.endToEnd = r0
                int r0 = r1.endToEnd
                r3 = -1
                if (r0 != r3) goto L_0x0450
                int r0 = r8.getInt(r11, r3)
                r1.endToEnd = r0
                goto L_0x0450
            L_0x02b9:
                r19 = r3
                r3 = r2
                r2 = r19
                int r0 = r1.endToStart
                int r0 = r8.getResourceId(r11, r0)
                r1.endToStart = r0
                int r0 = r1.endToStart
                if (r0 != r3) goto L_0x0450
                int r0 = r8.getInt(r11, r3)
                r1.endToStart = r0
                goto L_0x0450
            L_0x02d2:
                r19 = r3
                r3 = r2
                r2 = r19
                int r0 = r1.startToStart
                int r0 = r8.getResourceId(r11, r0)
                r1.startToStart = r0
                int r0 = r1.startToStart
                if (r0 != r3) goto L_0x0450
                int r0 = r8.getInt(r11, r3)
                r1.startToStart = r0
                goto L_0x0450
            L_0x02eb:
                r19 = r3
                r3 = r2
                r2 = r19
                int r0 = r1.startToEnd
                int r0 = r8.getResourceId(r11, r0)
                r1.startToEnd = r0
                int r0 = r1.startToEnd
                if (r0 != r3) goto L_0x0450
                int r0 = r8.getInt(r11, r3)
                r1.startToEnd = r0
                goto L_0x0450
            L_0x0304:
                r19 = r3
                r3 = r2
                r2 = r19
                int r0 = r1.baselineToBaseline
                int r0 = r8.getResourceId(r11, r0)
                r1.baselineToBaseline = r0
                int r0 = r1.baselineToBaseline
                if (r0 != r3) goto L_0x0450
                int r0 = r8.getInt(r11, r3)
                r1.baselineToBaseline = r0
                goto L_0x0450
            L_0x031d:
                r19 = r3
                r3 = r2
                r2 = r19
                int r0 = r1.bottomToBottom
                int r0 = r8.getResourceId(r11, r0)
                r1.bottomToBottom = r0
                int r0 = r1.bottomToBottom
                if (r0 != r3) goto L_0x0450
                int r0 = r8.getInt(r11, r3)
                r1.bottomToBottom = r0
                goto L_0x0450
            L_0x0336:
                r19 = r3
                r3 = r2
                r2 = r19
                int r0 = r1.bottomToTop
                int r0 = r8.getResourceId(r11, r0)
                r1.bottomToTop = r0
                int r0 = r1.bottomToTop
                if (r0 != r3) goto L_0x0450
                int r0 = r8.getInt(r11, r3)
                r1.bottomToTop = r0
                goto L_0x0450
            L_0x034f:
                r19 = r3
                r3 = r2
                r2 = r19
                int r0 = r1.topToBottom
                int r0 = r8.getResourceId(r11, r0)
                r1.topToBottom = r0
                int r0 = r1.topToBottom
                if (r0 != r3) goto L_0x0450
                int r0 = r8.getInt(r11, r3)
                r1.topToBottom = r0
                goto L_0x0450
            L_0x0368:
                r19 = r3
                r3 = r2
                r2 = r19
                int r0 = r1.topToTop
                int r0 = r8.getResourceId(r11, r0)
                r1.topToTop = r0
                int r0 = r1.topToTop
                if (r0 != r3) goto L_0x0450
                int r0 = r8.getInt(r11, r3)
                r1.topToTop = r0
                goto L_0x0450
            L_0x0381:
                r19 = r3
                r3 = r2
                r2 = r19
                int r0 = r1.rightToRight
                int r0 = r8.getResourceId(r11, r0)
                r1.rightToRight = r0
                int r0 = r1.rightToRight
                if (r0 != r3) goto L_0x0450
                int r0 = r8.getInt(r11, r3)
                r1.rightToRight = r0
                goto L_0x0450
            L_0x039a:
                r19 = r3
                r3 = r2
                r2 = r19
                int r0 = r1.rightToLeft
                int r0 = r8.getResourceId(r11, r0)
                r1.rightToLeft = r0
                int r0 = r1.rightToLeft
                if (r0 != r3) goto L_0x0450
                int r0 = r8.getInt(r11, r3)
                r1.rightToLeft = r0
                goto L_0x0450
            L_0x03b3:
                r19 = r3
                r3 = r2
                r2 = r19
                int r0 = r1.leftToRight
                int r0 = r8.getResourceId(r11, r0)
                r1.leftToRight = r0
                int r0 = r1.leftToRight
                if (r0 != r3) goto L_0x0450
                int r0 = r8.getInt(r11, r3)
                r1.leftToRight = r0
                goto L_0x0450
            L_0x03cc:
                r19 = r3
                r3 = r2
                r2 = r19
                int r0 = r1.leftToLeft
                int r0 = r8.getResourceId(r11, r0)
                r1.leftToLeft = r0
                int r0 = r1.leftToLeft
                if (r0 != r3) goto L_0x0450
                int r0 = r8.getInt(r11, r3)
                r1.leftToLeft = r0
                goto L_0x0426
            L_0x03e4:
                r2 = r3
                float r0 = r1.guidePercent
                float r0 = r8.getFloat(r11, r0)
                r1.guidePercent = r0
                goto L_0x0426
            L_0x03ee:
                r2 = r3
                int r0 = r1.guideEnd
                int r0 = r8.getDimensionPixelOffset(r11, r0)
                r1.guideEnd = r0
                goto L_0x0426
            L_0x03f8:
                r2 = r3
                int r0 = r1.guideBegin
                int r0 = r8.getDimensionPixelOffset(r11, r0)
                r1.guideBegin = r0
                goto L_0x0426
            L_0x0402:
                r2 = r3
                float r0 = r1.circleAngle
                float r0 = r8.getFloat(r11, r0)
                r3 = 1135869952(0x43b40000, float:360.0)
                float r0 = r0 % r3
                r1.circleAngle = r0
                float r0 = r1.circleAngle
                int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
                if (r0 >= 0) goto L_0x0426
                float r0 = r1.circleAngle
                float r0 = r3 - r0
                float r0 = r0 % r3
                r1.circleAngle = r0
                goto L_0x0426
            L_0x041c:
                r2 = r3
                int r0 = r1.circleRadius
                int r0 = r8.getDimensionPixelSize(r11, r0)
                r1.circleRadius = r0
            L_0x0426:
                r3 = -1
                goto L_0x0450
            L_0x0428:
                r2 = r3
                int r0 = r1.circleConstraint
                int r0 = r8.getResourceId(r11, r0)
                r1.circleConstraint = r0
                int r0 = r1.circleConstraint
                r3 = -1
                if (r0 != r3) goto L_0x0450
                int r0 = r8.getInt(r11, r3)
                r1.circleConstraint = r0
                goto L_0x0450
            L_0x043d:
                r19 = r3
                r3 = r2
                r2 = r19
                int r0 = r1.orientation
                int r0 = r8.getInt(r11, r0)
                r1.orientation = r0
                goto L_0x0450
            L_0x044b:
                r19 = r3
                r3 = r2
                r2 = r19
            L_0x0450:
                int r0 = r10 + 1
                r19 = r3
                r3 = r2
                r2 = r19
                goto L_0x00a0
            L_0x0459:
                r8.recycle()
                r20.validate()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.ConstraintLayout.LayoutParams.<init>(android.content.Context, android.util.AttributeSet):void");
        }

        public void validate() {
            this.isGuideline = false;
            this.horizontalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.verticalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            if (this.width == -2 && this.constrainedWidth) {
                this.horizontalDimensionFixed = false;
                this.matchConstraintDefaultWidth = 1;
            }
            if (this.height == -2 && this.constrainedHeight) {
                this.verticalDimensionFixed = false;
                this.matchConstraintDefaultHeight = 1;
            }
            if (this.width == 0 || this.width == -1) {
                this.horizontalDimensionFixed = false;
                if (this.width == 0 && this.matchConstraintDefaultWidth == 1) {
                    this.width = -2;
                    this.constrainedWidth = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                }
            }
            if (this.height == 0 || this.height == -1) {
                this.verticalDimensionFixed = false;
                if (this.height == 0 && this.matchConstraintDefaultHeight == 1) {
                    this.height = -2;
                    this.constrainedHeight = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                }
            }
            if (this.guidePercent != -1.0f || this.guideBegin != -1 || this.guideEnd != -1) {
                this.isGuideline = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                this.horizontalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                this.verticalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                if (!(this.widget instanceof Guideline)) {
                    this.widget = new Guideline();
                }
                ((Guideline) this.widget).setOrientation(this.orientation);
            }
        }

        public LayoutParams(int width, int height) {
            super(width, height);
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.circleConstraint = -1;
            this.circleRadius = 0;
            this.circleAngle = 0.0f;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.goneLeftMargin = -1;
            this.goneTopMargin = -1;
            this.goneRightMargin = -1;
            this.goneBottomMargin = -1;
            this.goneStartMargin = -1;
            this.goneEndMargin = -1;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.dimensionRatioValue = 0.0f;
            this.dimensionRatioSide = 1;
            this.horizontalWeight = -1.0f;
            this.verticalWeight = -1.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.matchConstraintDefaultWidth = 0;
            this.matchConstraintDefaultHeight = 0;
            this.matchConstraintMinWidth = 0;
            this.matchConstraintMinHeight = 0;
            this.matchConstraintMaxWidth = 0;
            this.matchConstraintMaxHeight = 0;
            this.matchConstraintPercentWidth = 1.0f;
            this.matchConstraintPercentHeight = 1.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.horizontalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.verticalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.needsBaseline = false;
            this.isGuideline = false;
            this.isHelper = false;
            this.isInPlaceholder = false;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolveGoneLeftMargin = -1;
            this.resolveGoneRightMargin = -1;
            this.resolvedHorizontalBias = 0.5f;
            this.widget = new ConstraintWidget();
            this.helped = false;
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.circleConstraint = -1;
            this.circleRadius = 0;
            this.circleAngle = 0.0f;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.goneLeftMargin = -1;
            this.goneTopMargin = -1;
            this.goneRightMargin = -1;
            this.goneBottomMargin = -1;
            this.goneStartMargin = -1;
            this.goneEndMargin = -1;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.dimensionRatioValue = 0.0f;
            this.dimensionRatioSide = 1;
            this.horizontalWeight = -1.0f;
            this.verticalWeight = -1.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.matchConstraintDefaultWidth = 0;
            this.matchConstraintDefaultHeight = 0;
            this.matchConstraintMinWidth = 0;
            this.matchConstraintMinHeight = 0;
            this.matchConstraintMaxWidth = 0;
            this.matchConstraintMaxHeight = 0;
            this.matchConstraintPercentWidth = 1.0f;
            this.matchConstraintPercentHeight = 1.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.horizontalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.verticalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.needsBaseline = false;
            this.isGuideline = false;
            this.isHelper = false;
            this.isInPlaceholder = false;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolveGoneLeftMargin = -1;
            this.resolveGoneRightMargin = -1;
            this.resolvedHorizontalBias = 0.5f;
            this.widget = new ConstraintWidget();
            this.helped = false;
        }

        @TargetApi(17)
        public void resolveLayoutDirection(int layoutDirection) {
            int preLeftMargin = this.leftMargin;
            int preRightMargin = this.rightMargin;
            super.resolveLayoutDirection(layoutDirection);
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolveGoneLeftMargin = -1;
            this.resolveGoneRightMargin = -1;
            this.resolveGoneLeftMargin = this.goneLeftMargin;
            this.resolveGoneRightMargin = this.goneRightMargin;
            this.resolvedHorizontalBias = this.horizontalBias;
            this.resolvedGuideBegin = this.guideBegin;
            this.resolvedGuideEnd = this.guideEnd;
            this.resolvedGuidePercent = this.guidePercent;
            if (1 == getLayoutDirection()) {
                boolean startEndDefined = false;
                if (this.startToEnd != -1) {
                    this.resolvedRightToLeft = this.startToEnd;
                    startEndDefined = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                } else if (this.startToStart != -1) {
                    this.resolvedRightToRight = this.startToStart;
                    startEndDefined = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                }
                if (this.endToStart != -1) {
                    this.resolvedLeftToRight = this.endToStart;
                    startEndDefined = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                }
                if (this.endToEnd != -1) {
                    this.resolvedLeftToLeft = this.endToEnd;
                    startEndDefined = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                }
                if (this.goneStartMargin != -1) {
                    this.resolveGoneRightMargin = this.goneStartMargin;
                }
                if (this.goneEndMargin != -1) {
                    this.resolveGoneLeftMargin = this.goneEndMargin;
                }
                if (startEndDefined) {
                    this.resolvedHorizontalBias = 1.0f - this.horizontalBias;
                }
                if (this.isGuideline && this.orientation == 1) {
                    if (this.guidePercent != -1.0f) {
                        this.resolvedGuidePercent = 1.0f - this.guidePercent;
                        this.resolvedGuideBegin = -1;
                        this.resolvedGuideEnd = -1;
                    } else if (this.guideBegin != -1) {
                        this.resolvedGuideEnd = this.guideBegin;
                        this.resolvedGuideBegin = -1;
                        this.resolvedGuidePercent = -1.0f;
                    } else if (this.guideEnd != -1) {
                        this.resolvedGuideBegin = this.guideEnd;
                        this.resolvedGuideEnd = -1;
                        this.resolvedGuidePercent = -1.0f;
                    }
                }
            } else {
                if (this.startToEnd != -1) {
                    this.resolvedLeftToRight = this.startToEnd;
                }
                if (this.startToStart != -1) {
                    this.resolvedLeftToLeft = this.startToStart;
                }
                if (this.endToStart != -1) {
                    this.resolvedRightToLeft = this.endToStart;
                }
                if (this.endToEnd != -1) {
                    this.resolvedRightToRight = this.endToEnd;
                }
                if (this.goneStartMargin != -1) {
                    this.resolveGoneLeftMargin = this.goneStartMargin;
                }
                if (this.goneEndMargin != -1) {
                    this.resolveGoneRightMargin = this.goneEndMargin;
                }
            }
            if (this.endToStart == -1 && this.endToEnd == -1 && this.startToStart == -1 && this.startToEnd == -1) {
                if (this.rightToLeft != -1) {
                    this.resolvedRightToLeft = this.rightToLeft;
                    if (this.rightMargin <= 0 && preRightMargin > 0) {
                        this.rightMargin = preRightMargin;
                    }
                } else if (this.rightToRight != -1) {
                    this.resolvedRightToRight = this.rightToRight;
                    if (this.rightMargin <= 0 && preRightMargin > 0) {
                        this.rightMargin = preRightMargin;
                    }
                }
                if (this.leftToLeft != -1) {
                    this.resolvedLeftToLeft = this.leftToLeft;
                    if (this.leftMargin <= 0 && preLeftMargin > 0) {
                        this.leftMargin = preLeftMargin;
                    }
                } else if (this.leftToRight != -1) {
                    this.resolvedLeftToRight = this.leftToRight;
                    if (this.leftMargin <= 0 && preLeftMargin > 0) {
                        this.leftMargin = preLeftMargin;
                    }
                }
            }
        }
    }

    public void requestLayout() {
        super.requestLayout();
        this.mDirtyHierarchy = USE_CONSTRAINTS_HELPER;
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }
}
