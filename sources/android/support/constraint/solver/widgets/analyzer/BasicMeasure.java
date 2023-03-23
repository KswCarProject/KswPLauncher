package android.support.constraint.solver.widgets.analyzer;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.ConstraintWidgetContainer;
import android.support.constraint.solver.widgets.Guideline;
import android.support.constraint.solver.widgets.Helper;
import android.support.constraint.solver.widgets.Optimizer;
import android.support.constraint.solver.widgets.VirtualLayout;
import java.util.ArrayList;

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
    private Measure mMeasure = new Measure();
    private final ArrayList<ConstraintWidget> mVariableDimensionsWidgets = new ArrayList<>();

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

    public enum MeasureType {
    }

    public interface Measurer {
        void didMeasures();

        void measure(ConstraintWidget constraintWidget, Measure measure);
    }

    public void updateHierarchy(ConstraintWidgetContainer layout) {
        this.mVariableDimensionsWidgets.clear();
        int childCount = layout.mChildren.size();
        for (int i = 0; i < childCount; i++) {
            ConstraintWidget widget = (ConstraintWidget) layout.mChildren.get(i);
            if (widget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || widget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_PARENT || widget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || widget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                this.mVariableDimensionsWidgets.add(widget);
            }
        }
        layout.invalidateGraph();
    }

    public BasicMeasure(ConstraintWidgetContainer constraintWidgetContainer2) {
        this.constraintWidgetContainer = constraintWidgetContainer2;
    }

    private void measureChildren(ConstraintWidgetContainer layout) {
        int childCount = layout.mChildren.size();
        Measurer measurer = layout.getMeasurer();
        for (int i = 0; i < childCount; i++) {
            ConstraintWidget child = (ConstraintWidget) layout.mChildren.get(i);
            if (!(child instanceof Guideline) && (!child.horizontalRun.dimension.resolved || !child.verticalRun.dimension.resolved)) {
                ConstraintWidget.DimensionBehaviour widthBehavior = child.getDimensionBehaviour(0);
                boolean skip = true;
                ConstraintWidget.DimensionBehaviour heightBehavior = child.getDimensionBehaviour(1);
                if (widthBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || child.mMatchConstraintDefaultWidth == 1 || heightBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || child.mMatchConstraintDefaultHeight == 1) {
                    skip = false;
                }
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
        boolean ratio;
        long layoutTime;
        int heightSize2;
        int widthSize2;
        int optimizations;
        int startingHeight;
        int startingWidth;
        boolean containerWrapWidth;
        Measurer measurer;
        int sizeDependentWidgetsCount;
        boolean needSolverPass;
        int startingHeight2;
        int startingWidth2;
        int optimizations2;
        int computations;
        boolean optimize;
        ConstraintWidgetContainer constraintWidgetContainer2 = layout;
        int i = optimizationLevel;
        int i2 = widthMode;
        int i3 = heightMode;
        Measurer measurer2 = layout.getMeasurer();
        int childCount = constraintWidgetContainer2.mChildren.size();
        int startingWidth3 = layout.getWidth();
        int startingHeight3 = layout.getHeight();
        boolean optimizeWrap = Optimizer.enabled(i, 128);
        boolean optimize2 = optimizeWrap || Optimizer.enabled(i, 64);
        if (optimize2) {
            int i4 = 0;
            while (i4 < childCount) {
                ConstraintWidget child = (ConstraintWidget) constraintWidgetContainer2.mChildren.get(i4);
                boolean matchWidth = child.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                boolean optimize3 = optimize2;
                boolean ratio2 = matchWidth && (child.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) && child.getDimensionRatio() > 0.0f;
                if (!child.isInHorizontalChain() || !ratio2) {
                    if (child.isInVerticalChain() && ratio2) {
                        ratio = false;
                        break;
                    }
                    boolean z = matchWidth;
                    if (child instanceof VirtualLayout) {
                        ratio = false;
                        break;
                    } else if (child.isInHorizontalChain() || child.isInVerticalChain()) {
                        ratio = false;
                        break;
                    } else {
                        i4++;
                        int i5 = optimizationLevel;
                        optimize2 = optimize3;
                    }
                } else {
                    ratio = false;
                    break;
                }
            }
            optimize = optimize2;
        } else {
            optimize = optimize2;
        }
        ratio = optimize;
        if (!ratio || LinearSystem.sMetrics == null) {
            layoutTime = 0;
        } else {
            layoutTime = 0;
            LinearSystem.sMetrics.measures++;
        }
        boolean allSolved = false;
        boolean optimize4 = ((i2 == 1073741824 && i3 == 1073741824) || optimizeWrap) & ratio;
        int computations2 = 0;
        if (optimize4) {
            widthSize2 = Math.min(layout.getMaxWidth(), widthSize);
            heightSize2 = Math.min(layout.getMaxHeight(), heightSize);
            if (i2 == 1073741824 && layout.getWidth() != widthSize2) {
                constraintWidgetContainer2.setWidth(widthSize2);
                layout.invalidateGraph();
            }
            if (i3 == 1073741824 && layout.getHeight() != heightSize2) {
                constraintWidgetContainer2.setHeight(heightSize2);
                layout.invalidateGraph();
            }
            if (i2 == 1073741824 && i3 == 1073741824) {
                allSolved = constraintWidgetContainer2.directMeasure(optimizeWrap);
                computations2 = 2;
            } else {
                allSolved = constraintWidgetContainer2.directMeasureSetup(optimizeWrap);
                if (i2 == 1073741824) {
                    allSolved &= constraintWidgetContainer2.directMeasureWithOrientation(optimizeWrap, 0);
                    computations2 = 0 + 1;
                }
                if (i3 == 1073741824) {
                    allSolved &= constraintWidgetContainer2.directMeasureWithOrientation(optimizeWrap, 1);
                    computations2++;
                }
            }
            if (allSolved) {
                constraintWidgetContainer2.updateFromRuns(i2 == 1073741824, i3 == 1073741824);
            }
        } else {
            widthSize2 = widthSize;
            heightSize2 = heightSize;
        }
        if (!allSolved || computations2 != 2) {
            if (childCount > 0) {
                measureChildren(layout);
            }
            int optimizations3 = layout.getOptimizationLevel();
            int sizeDependentWidgetsCount2 = this.mVariableDimensionsWidgets.size();
            if (childCount > 0) {
                boolean z2 = allSolved;
                solveLinearSystem(constraintWidgetContainer2, "First pass", startingWidth3, startingHeight3);
            }
            if (sizeDependentWidgetsCount2 > 0) {
                boolean containerWrapWidth2 = layout.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                boolean containerWrapHeight = layout.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                boolean z3 = optimize4;
                boolean z4 = optimizeWrap;
                int i6 = 0;
                int minWidth = Math.max(layout.getWidth(), this.constraintWidgetContainer.getMinWidth());
                int minWidth2 = childCount;
                int minHeight = Math.max(layout.getHeight(), this.constraintWidgetContainer.getMinHeight());
                boolean needSolverPass2 = false;
                while (i6 < sizeDependentWidgetsCount2) {
                    int widthSize3 = widthSize2;
                    ConstraintWidget widget = this.mVariableDimensionsWidgets.get(i6);
                    int heightSize3 = heightSize2;
                    if ((widget instanceof VirtualLayout) == 0) {
                        startingWidth2 = startingWidth3;
                        startingHeight2 = startingHeight3;
                        computations = computations2;
                        optimizations2 = optimizations3;
                    } else {
                        int preWidth = widget.getWidth();
                        computations = computations2;
                        int computations3 = widget.getHeight();
                        optimizations2 = optimizations3;
                        boolean needSolverPass3 = needSolverPass2 | measure(measurer2, widget, true);
                        if (constraintWidgetContainer2.mMetrics != null) {
                            startingWidth2 = startingWidth3;
                            startingHeight2 = startingHeight3;
                            constraintWidgetContainer2.mMetrics.measuredMatchWidgets++;
                        } else {
                            startingWidth2 = startingWidth3;
                            startingHeight2 = startingHeight3;
                        }
                        int startingWidth4 = widget.getWidth();
                        int measuredHeight = widget.getHeight();
                        if (startingWidth4 != preWidth) {
                            widget.setWidth(startingWidth4);
                            if (!containerWrapWidth2 || widget.getRight() <= minWidth) {
                            } else {
                                boolean z5 = needSolverPass3;
                                minWidth = Math.max(minWidth, widget.getRight() + widget.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin());
                            }
                            needSolverPass3 = true;
                        } else {
                            boolean z6 = needSolverPass3;
                        }
                        if (measuredHeight != computations3) {
                            widget.setHeight(measuredHeight);
                            if (!containerWrapHeight || widget.getBottom() <= minHeight) {
                            } else {
                                boolean z7 = needSolverPass3;
                                minHeight = Math.max(minHeight, widget.getBottom() + widget.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin());
                            }
                            needSolverPass3 = true;
                        } else {
                            boolean z8 = needSolverPass3;
                        }
                        needSolverPass2 = needSolverPass3 | ((VirtualLayout) widget).needSolverPass();
                    }
                    i6++;
                    widthSize2 = widthSize3;
                    heightSize2 = heightSize3;
                    computations2 = computations;
                    optimizations3 = optimizations2;
                    startingWidth3 = startingWidth2;
                    startingHeight3 = startingHeight2;
                }
                int startingWidth5 = startingWidth3;
                int startingHeight4 = startingHeight3;
                int i7 = widthSize2;
                int i8 = heightSize2;
                int i9 = computations2;
                optimizations = optimizations3;
                int j = 0;
                while (j < 2) {
                    int i10 = 0;
                    while (i10 < sizeDependentWidgetsCount2) {
                        ConstraintWidget widget2 = this.mVariableDimensionsWidgets.get(i10);
                        if ((!(widget2 instanceof Helper) || (widget2 instanceof VirtualLayout)) && !(widget2 instanceof Guideline) && widget2.getVisibility() != 8 && ((!widget2.horizontalRun.dimension.resolved || !widget2.verticalRun.dimension.resolved) && !(widget2 instanceof VirtualLayout))) {
                            int preWidth2 = widget2.getWidth();
                            int preHeight = widget2.getHeight();
                            int preBaselineDistance = widget2.getBaselineDistance();
                            sizeDependentWidgetsCount = sizeDependentWidgetsCount2;
                            boolean needSolverPass4 = needSolverPass2 | measure(measurer2, widget2, true);
                            if (constraintWidgetContainer2.mMetrics != null) {
                                needSolverPass = needSolverPass4;
                                measurer = measurer2;
                                constraintWidgetContainer2.mMetrics.measuredMatchWidgets++;
                            } else {
                                needSolverPass = needSolverPass4;
                                measurer = measurer2;
                            }
                            int measuredWidth = widget2.getWidth();
                            int measuredHeight2 = widget2.getHeight();
                            if (measuredWidth != preWidth2) {
                                widget2.setWidth(measuredWidth);
                                if (!containerWrapWidth2 || widget2.getRight() <= minWidth) {
                                    containerWrapWidth = containerWrapWidth2;
                                } else {
                                    containerWrapWidth = containerWrapWidth2;
                                    minWidth = Math.max(minWidth, widget2.getRight() + widget2.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin());
                                }
                                needSolverPass = true;
                            } else {
                                containerWrapWidth = containerWrapWidth2;
                            }
                            if (measuredHeight2 != preHeight) {
                                widget2.setHeight(measuredHeight2);
                                if (containerWrapHeight && widget2.getBottom() > minHeight) {
                                    minHeight = Math.max(minHeight, widget2.getBottom() + widget2.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin());
                                }
                                needSolverPass = true;
                            }
                            if (!widget2.hasBaseline() || preBaselineDistance == widget2.getBaselineDistance()) {
                                needSolverPass2 = needSolverPass;
                            } else {
                                needSolverPass2 = true;
                            }
                        } else {
                            containerWrapWidth = containerWrapWidth2;
                            measurer = measurer2;
                            sizeDependentWidgetsCount = sizeDependentWidgetsCount2;
                        }
                        i10++;
                        sizeDependentWidgetsCount2 = sizeDependentWidgetsCount;
                        measurer2 = measurer;
                        containerWrapWidth2 = containerWrapWidth;
                    }
                    boolean containerWrapWidth3 = containerWrapWidth2;
                    Measurer measurer3 = measurer2;
                    int sizeDependentWidgetsCount3 = sizeDependentWidgetsCount2;
                    if (needSolverPass2) {
                        startingWidth = startingWidth5;
                        startingHeight = startingHeight4;
                        solveLinearSystem(constraintWidgetContainer2, "intermediate pass", startingWidth, startingHeight);
                        needSolverPass2 = false;
                    } else {
                        startingWidth = startingWidth5;
                        startingHeight = startingHeight4;
                    }
                    j++;
                    startingWidth5 = startingWidth;
                    startingHeight4 = startingHeight;
                    sizeDependentWidgetsCount2 = sizeDependentWidgetsCount3;
                    measurer2 = measurer3;
                    containerWrapWidth2 = containerWrapWidth3;
                }
                Measurer measurer4 = measurer2;
                int i11 = sizeDependentWidgetsCount2;
                int startingWidth6 = startingWidth5;
                int sizeDependentWidgetsCount4 = startingHeight4;
                if (needSolverPass2) {
                    solveLinearSystem(constraintWidgetContainer2, "2nd pass", startingWidth6, sizeDependentWidgetsCount4);
                    boolean needSolverPass5 = false;
                    if (layout.getWidth() < minWidth) {
                        constraintWidgetContainer2.setWidth(minWidth);
                        needSolverPass5 = true;
                    }
                    if (layout.getHeight() < minHeight) {
                        constraintWidgetContainer2.setHeight(minHeight);
                        needSolverPass5 = true;
                    }
                    if (needSolverPass5) {
                        solveLinearSystem(constraintWidgetContainer2, "3rd pass", startingWidth6, sizeDependentWidgetsCount4);
                    }
                }
            } else {
                int i12 = sizeDependentWidgetsCount2;
                boolean z9 = optimize4;
                int i13 = childCount;
                int i14 = startingWidth3;
                int sizeDependentWidgetsCount5 = startingHeight3;
                boolean z10 = optimizeWrap;
                int i15 = widthSize2;
                int i16 = heightSize2;
                int i17 = computations2;
                optimizations = optimizations3;
            }
            constraintWidgetContainer2.setOptimizationLevel(optimizations);
        } else {
            boolean z11 = allSolved;
            Measurer measurer5 = measurer2;
            boolean z12 = optimize4;
            int i18 = childCount;
            int i19 = startingWidth3;
            int i20 = startingHeight3;
            boolean z13 = optimizeWrap;
            int i21 = widthSize2;
            int i22 = heightSize2;
            int i23 = computations2;
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
