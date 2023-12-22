package android.support.constraint.solver.widgets.analyzer;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.Metrics;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.ConstraintWidgetContainer;
import android.support.constraint.solver.widgets.Guideline;
import android.support.constraint.solver.widgets.Helper;
import android.support.constraint.solver.widgets.Optimizer;
import android.support.constraint.solver.widgets.VirtualLayout;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class BasicMeasure {
    public static final int AT_MOST = Integer.MIN_VALUE;
    private static final boolean DEBUG = false;
    public static final int EXACTLY = 1073741824;
    public static final int FIXED = -3;
    public static final int MATCH_PARENT = -1;
    private static final int MODE_SHIFT = 30;
    public static final int UNSPECIFIED = 0;
    public static final int WRAP_CONTENT = -2;
    private ConstraintWidgetContainer constraintWidgetContainer;
    private final ArrayList<ConstraintWidget> mVariableDimensionsWidgets = new ArrayList<>();
    private Measure mMeasure = new Measure();

    /* loaded from: classes.dex */
    public static class Measure {
        public ConstraintWidget.DimensionBehaviour horizontalBehavior;
        public int horizontalDimension;
        public int measuredBaseline;
        public boolean measuredHasBaseline;
        public int measuredHeight;
        public boolean measuredNeedsSolverPass;
        public int measuredWidth;
        public boolean useCurrentDimensions;
        public ConstraintWidget.DimensionBehaviour verticalBehavior;
        public int verticalDimension;
    }

    /* loaded from: classes.dex */
    public enum MeasureType {
    }

    /* loaded from: classes.dex */
    public interface Measurer {
        void didMeasures();

        void measure(ConstraintWidget constraintWidget, Measure measure);
    }

    public void updateHierarchy(ConstraintWidgetContainer layout) {
        this.mVariableDimensionsWidgets.clear();
        int childCount = layout.mChildren.size();
        for (int i = 0; i < childCount; i++) {
            ConstraintWidget widget = layout.mChildren.get(i);
            if (widget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || widget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_PARENT || widget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || widget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                this.mVariableDimensionsWidgets.add(widget);
            }
        }
        layout.invalidateGraph();
    }

    public BasicMeasure(ConstraintWidgetContainer constraintWidgetContainer) {
        this.constraintWidgetContainer = constraintWidgetContainer;
    }

    private void measureChildren(ConstraintWidgetContainer layout) {
        int childCount = layout.mChildren.size();
        Measurer measurer = layout.getMeasurer();
        for (int i = 0; i < childCount; i++) {
            ConstraintWidget child = layout.mChildren.get(i);
            if (!(child instanceof Guideline) && (!child.horizontalRun.dimension.resolved || !child.verticalRun.dimension.resolved)) {
                ConstraintWidget.DimensionBehaviour widthBehavior = child.getDimensionBehaviour(0);
                boolean skip = true;
                ConstraintWidget.DimensionBehaviour heightBehavior = child.getDimensionBehaviour(1);
                skip = (widthBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || child.mMatchConstraintDefaultWidth == 1 || heightBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || child.mMatchConstraintDefaultHeight == 1) ? false : false;
                if (!skip) {
                    measure(measurer, child, false);
                    if (layout.mMetrics != null) {
                        layout.mMetrics.measuredWidgets++;
                    }
                }
            }
        }
        measurer.didMeasures();
    }

    private void solveLinearSystem(ConstraintWidgetContainer layout, String reason, int w, int h) {
        int minWidth = layout.getMinWidth();
        int minHeight = layout.getMinHeight();
        layout.setMinWidth(0);
        layout.setMinHeight(0);
        layout.setWidth(w);
        layout.setHeight(h);
        layout.setMinWidth(minWidth);
        layout.setMinHeight(minHeight);
        this.constraintWidgetContainer.layout();
    }

    public long solverMeasure(ConstraintWidgetContainer layout, int optimizationLevel, int paddingX, int paddingY, int widthMode, int widthSize, int heightMode, int heightSize, int lastMeasureWidth, int lastMeasureHeight) {
        boolean optimize;
        boolean ratio;
        long layoutTime;
        int widthSize2;
        int heightSize2;
        int optimizations;
        int startingWidth;
        int startingHeight;
        int sizeDependentWidgetsCount;
        int i;
        Measurer measurer;
        boolean containerWrapWidth;
        int computations;
        int optimizations2;
        int startingWidth2;
        int startingHeight2;
        Measurer measurer2 = layout.getMeasurer();
        int childCount = layout.mChildren.size();
        int startingWidth3 = layout.getWidth();
        int startingHeight3 = layout.getHeight();
        boolean optimizeWrap = Optimizer.enabled(optimizationLevel, 128);
        boolean optimize2 = optimizeWrap || Optimizer.enabled(optimizationLevel, 64);
        if (!optimize2) {
            optimize = optimize2;
        } else {
            int i2 = 0;
            while (i2 < childCount) {
                ConstraintWidget child = layout.mChildren.get(i2);
                boolean matchWidth = child.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                boolean optimize3 = optimize2;
                boolean matchHeight = child.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                boolean ratio2 = matchWidth && matchHeight && child.getDimensionRatio() > 0.0f;
                if (child.isInHorizontalChain() && ratio2) {
                    ratio = false;
                    break;
                } else if (child.isInVerticalChain() && ratio2) {
                    ratio = false;
                    break;
                } else {
                    boolean matchWidth2 = child instanceof VirtualLayout;
                    if (matchWidth2) {
                        ratio = false;
                        break;
                    }
                    if (!child.isInHorizontalChain() && !child.isInVerticalChain()) {
                        i2++;
                        optimize2 = optimize3;
                    } else {
                        ratio = false;
                        break;
                    }
                }
            }
            optimize = optimize2;
        }
        ratio = optimize;
        if (!ratio || LinearSystem.sMetrics == null) {
            layoutTime = 0;
        } else {
            Metrics metrics = LinearSystem.sMetrics;
            layoutTime = 0;
            long layoutTime2 = metrics.measures;
            metrics.measures = layoutTime2 + 1;
        }
        boolean allSolved = false;
        boolean optimize4 = ((widthMode == 1073741824 && heightMode == 1073741824) || optimizeWrap) & ratio;
        int computations2 = 0;
        if (!optimize4) {
            widthSize2 = widthSize;
            heightSize2 = heightSize;
        } else {
            widthSize2 = Math.min(layout.getMaxWidth(), widthSize);
            heightSize2 = Math.min(layout.getMaxHeight(), heightSize);
            if (widthMode == 1073741824 && layout.getWidth() != widthSize2) {
                layout.setWidth(widthSize2);
                layout.invalidateGraph();
            }
            if (heightMode == 1073741824 && layout.getHeight() != heightSize2) {
                layout.setHeight(heightSize2);
                layout.invalidateGraph();
            }
            if (widthMode == 1073741824 && heightMode == 1073741824) {
                allSolved = layout.directMeasure(optimizeWrap);
                computations2 = 2;
            } else {
                allSolved = layout.directMeasureSetup(optimizeWrap);
                if (widthMode == 1073741824) {
                    allSolved &= layout.directMeasureWithOrientation(optimizeWrap, 0);
                    computations2 = 0 + 1;
                }
                if (heightMode == 1073741824) {
                    allSolved &= layout.directMeasureWithOrientation(optimizeWrap, 1);
                    computations2++;
                }
            }
            if (allSolved) {
                layout.updateFromRuns(widthMode == 1073741824, heightMode == 1073741824);
            }
        }
        if (!allSolved || computations2 != 2) {
            if (childCount > 0) {
                measureChildren(layout);
            }
            int optimizations3 = layout.getOptimizationLevel();
            int sizeDependentWidgetsCount2 = this.mVariableDimensionsWidgets.size();
            if (childCount > 0) {
                solveLinearSystem(layout, "First pass", startingWidth3, startingHeight3);
            }
            if (sizeDependentWidgetsCount2 > 0) {
                boolean containerWrapWidth2 = layout.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                boolean containerWrapHeight = layout.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                int minWidth = Math.max(layout.getWidth(), this.constraintWidgetContainer.getMinWidth());
                int minHeight = Math.max(layout.getHeight(), this.constraintWidgetContainer.getMinHeight());
                int i3 = 0;
                int minWidth2 = minWidth;
                int childCount2 = minHeight;
                int minHeight2 = 0;
                while (i3 < sizeDependentWidgetsCount2) {
                    int widthSize3 = widthSize2;
                    ConstraintWidget widget = this.mVariableDimensionsWidgets.get(i3);
                    int heightSize3 = heightSize2;
                    if (!(widget instanceof VirtualLayout)) {
                        startingWidth2 = startingWidth3;
                        startingHeight2 = startingHeight3;
                        computations = computations2;
                        optimizations2 = optimizations3;
                    } else {
                        int preWidth = widget.getWidth();
                        computations = computations2;
                        int preHeight = widget.getHeight();
                        optimizations2 = optimizations3;
                        int needSolverPass = minHeight2 | measure(measurer2, widget, true);
                        if (layout.mMetrics == null) {
                            startingWidth2 = startingWidth3;
                            startingHeight2 = startingHeight3;
                        } else {
                            startingWidth2 = startingWidth3;
                            startingHeight2 = startingHeight3;
                            layout.mMetrics.measuredMatchWidgets++;
                        }
                        int measuredWidth = widget.getWidth();
                        int measuredHeight = widget.getHeight();
                        if (measuredWidth != preWidth) {
                            widget.setWidth(measuredWidth);
                            if (containerWrapWidth2 && widget.getRight() > minWidth2) {
                                int w = widget.getRight() + widget.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin();
                                minWidth2 = Math.max(minWidth2, w);
                            }
                            needSolverPass = true;
                        }
                        if (measuredHeight != preHeight) {
                            widget.setHeight(measuredHeight);
                            if (containerWrapHeight && widget.getBottom() > childCount2) {
                                int h = widget.getBottom() + widget.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin();
                                childCount2 = Math.max(childCount2, h);
                            }
                            needSolverPass = true;
                        }
                        VirtualLayout virtualLayout = (VirtualLayout) widget;
                        minHeight2 = needSolverPass | virtualLayout.needSolverPass();
                    }
                    i3++;
                    widthSize2 = widthSize3;
                    heightSize2 = heightSize3;
                    computations2 = computations;
                    optimizations3 = optimizations2;
                    startingWidth3 = startingWidth2;
                    startingHeight3 = startingHeight2;
                }
                int startingWidth4 = startingWidth3;
                int startingHeight4 = startingHeight3;
                optimizations = optimizations3;
                int j = 0;
                while (j < 2) {
                    int i4 = 0;
                    while (i4 < sizeDependentWidgetsCount2) {
                        ConstraintWidget widget2 = this.mVariableDimensionsWidgets.get(i4);
                        if (((widget2 instanceof Helper) && !(widget2 instanceof VirtualLayout)) || (widget2 instanceof Guideline) || widget2.getVisibility() == 8 || ((widget2.horizontalRun.dimension.resolved && widget2.verticalRun.dimension.resolved) || (widget2 instanceof VirtualLayout))) {
                            containerWrapWidth = containerWrapWidth2;
                            measurer = measurer2;
                            sizeDependentWidgetsCount = sizeDependentWidgetsCount2;
                        } else {
                            int preWidth2 = widget2.getWidth();
                            int preHeight2 = widget2.getHeight();
                            int preBaselineDistance = widget2.getBaselineDistance();
                            sizeDependentWidgetsCount = sizeDependentWidgetsCount2;
                            int measure = minHeight2 | measure(measurer2, widget2, true);
                            if (layout.mMetrics == null) {
                                i = measure;
                                measurer = measurer2;
                            } else {
                                i = measure;
                                measurer = measurer2;
                                layout.mMetrics.measuredMatchWidgets++;
                            }
                            int measuredWidth2 = widget2.getWidth();
                            int measuredHeight2 = widget2.getHeight();
                            if (measuredWidth2 == preWidth2) {
                                containerWrapWidth = containerWrapWidth2;
                            } else {
                                widget2.setWidth(measuredWidth2);
                                if (!containerWrapWidth2 || widget2.getRight() <= minWidth2) {
                                    containerWrapWidth = containerWrapWidth2;
                                } else {
                                    containerWrapWidth = containerWrapWidth2;
                                    int w2 = widget2.getRight() + widget2.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin();
                                    minWidth2 = Math.max(minWidth2, w2);
                                }
                                i = 1;
                            }
                            if (measuredHeight2 != preHeight2) {
                                widget2.setHeight(measuredHeight2);
                                if (containerWrapHeight && widget2.getBottom() > childCount2) {
                                    int h2 = widget2.getBottom() + widget2.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin();
                                    childCount2 = Math.max(childCount2, h2);
                                }
                                i = 1;
                            }
                            if (!widget2.hasBaseline() || preBaselineDistance == widget2.getBaselineDistance()) {
                                minHeight2 = i;
                            } else {
                                minHeight2 = 1;
                            }
                        }
                        i4++;
                        sizeDependentWidgetsCount2 = sizeDependentWidgetsCount;
                        measurer2 = measurer;
                        containerWrapWidth2 = containerWrapWidth;
                    }
                    boolean containerWrapWidth3 = containerWrapWidth2;
                    Measurer measurer3 = measurer2;
                    int sizeDependentWidgetsCount3 = sizeDependentWidgetsCount2;
                    if (minHeight2 != 0) {
                        startingWidth = startingWidth4;
                        startingHeight = startingHeight4;
                        solveLinearSystem(layout, "intermediate pass", startingWidth, startingHeight);
                        minHeight2 = 0;
                    } else {
                        startingWidth = startingWidth4;
                        startingHeight = startingHeight4;
                    }
                    j++;
                    startingWidth4 = startingWidth;
                    startingHeight4 = startingHeight;
                    sizeDependentWidgetsCount2 = sizeDependentWidgetsCount3;
                    measurer2 = measurer3;
                    containerWrapWidth2 = containerWrapWidth3;
                }
                int startingWidth5 = startingWidth4;
                int sizeDependentWidgetsCount4 = startingHeight4;
                if (minHeight2 != 0) {
                    solveLinearSystem(layout, "2nd pass", startingWidth5, sizeDependentWidgetsCount4);
                    boolean needSolverPass2 = false;
                    if (layout.getWidth() < minWidth2) {
                        layout.setWidth(minWidth2);
                        needSolverPass2 = true;
                    }
                    if (layout.getHeight() < childCount2) {
                        layout.setHeight(childCount2);
                        needSolverPass2 = true;
                    }
                    if (needSolverPass2) {
                        solveLinearSystem(layout, "3rd pass", startingWidth5, sizeDependentWidgetsCount4);
                    }
                }
            } else {
                optimizations = optimizations3;
            }
            layout.setOptimizationLevel(optimizations);
        }
        return layoutTime;
    }

    private boolean measure(Measurer measurer, ConstraintWidget widget, boolean useCurrentDimensions) {
        this.mMeasure.horizontalBehavior = widget.getHorizontalDimensionBehaviour();
        this.mMeasure.verticalBehavior = widget.getVerticalDimensionBehaviour();
        this.mMeasure.horizontalDimension = widget.getWidth();
        this.mMeasure.verticalDimension = widget.getHeight();
        this.mMeasure.measuredNeedsSolverPass = false;
        this.mMeasure.useCurrentDimensions = useCurrentDimensions;
        boolean horizontalMatchConstraints = this.mMeasure.horizontalBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean verticalMatchConstraints = this.mMeasure.verticalBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean horizontalUseRatio = horizontalMatchConstraints && widget.mDimensionRatio > 0.0f;
        boolean verticalUseRatio = verticalMatchConstraints && widget.mDimensionRatio > 0.0f;
        if (horizontalUseRatio && widget.mResolvedMatchConstraintDefault[0] == 4) {
            this.mMeasure.horizontalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        if (verticalUseRatio && widget.mResolvedMatchConstraintDefault[1] == 4) {
            this.mMeasure.verticalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        measurer.measure(widget, this.mMeasure);
        widget.setWidth(this.mMeasure.measuredWidth);
        widget.setHeight(this.mMeasure.measuredHeight);
        widget.setHasBaseline(this.mMeasure.measuredHasBaseline);
        widget.setBaselineDistance(this.mMeasure.measuredBaseline);
        this.mMeasure.useCurrentDimensions = false;
        return this.mMeasure.measuredNeedsSolverPass;
    }
}
