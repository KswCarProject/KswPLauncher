package android.support.constraint.solver.widgets;

import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.analyzer.BasicMeasure;

public class VirtualLayout extends HelperWidget {
    protected BasicMeasure.Measure mMeasure = new BasicMeasure.Measure();
    private int mMeasuredHeight = 0;
    private int mMeasuredWidth = 0;
    BasicMeasure.Measurer mMeasurer = null;
    private boolean mNeedsCallFromSolver = false;
    private int mPaddingBottom = 0;
    private int mPaddingEnd = 0;
    private int mPaddingLeft = 0;
    private int mPaddingRight = 0;
    private int mPaddingStart = 0;
    private int mPaddingTop = 0;
    private int mResolvedPaddingLeft = 0;
    private int mResolvedPaddingRight = 0;

    public void setPadding(int value) {
        this.mPaddingLeft = value;
        this.mPaddingTop = value;
        this.mPaddingRight = value;
        this.mPaddingBottom = value;
        this.mPaddingStart = value;
        this.mPaddingEnd = value;
    }

    public void setPaddingStart(int value) {
        this.mPaddingStart = value;
        this.mResolvedPaddingLeft = value;
        this.mResolvedPaddingRight = value;
    }

    public void setPaddingEnd(int value) {
        this.mPaddingEnd = value;
    }

    public void setPaddingLeft(int value) {
        this.mPaddingLeft = value;
        this.mResolvedPaddingLeft = value;
    }

    public void applyRtl(boolean isRtl) {
        int i = this.mPaddingStart;
        if (i <= 0 && this.mPaddingEnd <= 0) {
            return;
        }
        if (isRtl) {
            this.mResolvedPaddingLeft = this.mPaddingEnd;
            this.mResolvedPaddingRight = i;
            return;
        }
        this.mResolvedPaddingLeft = i;
        this.mResolvedPaddingRight = this.mPaddingEnd;
    }

    public void setPaddingTop(int value) {
        this.mPaddingTop = value;
    }

    public void setPaddingRight(int value) {
        this.mPaddingRight = value;
        this.mResolvedPaddingRight = value;
    }

    public void setPaddingBottom(int value) {
        this.mPaddingBottom = value;
    }

    public int getPaddingTop() {
        return this.mPaddingTop;
    }

    public int getPaddingBottom() {
        return this.mPaddingBottom;
    }

    public int getPaddingLeft() {
        return this.mResolvedPaddingLeft;
    }

    public int getPaddingRight() {
        return this.mResolvedPaddingRight;
    }

    /* access modifiers changed from: protected */
    public void needsCallbackFromSolver(boolean value) {
        this.mNeedsCallFromSolver = value;
    }

    public boolean needSolverPass() {
        return this.mNeedsCallFromSolver;
    }

    public void measure(int widthMode, int widthSize, int heightMode, int heightSize) {
    }

    public void updateConstraints(ConstraintWidgetContainer container) {
        captureWidgets();
    }

    public void captureWidgets() {
        for (int i = 0; i < this.mWidgetsCount; i++) {
            ConstraintWidget widget = this.mWidgets[i];
            if (widget != null) {
                widget.setInVirtualLayout(true);
            }
        }
    }

    public int getMeasuredWidth() {
        return this.mMeasuredWidth;
    }

    public int getMeasuredHeight() {
        return this.mMeasuredHeight;
    }

    public void setMeasure(int width, int height) {
        this.mMeasuredWidth = width;
        this.mMeasuredHeight = height;
    }

    /* access modifiers changed from: protected */
    public boolean measureChildren() {
        BasicMeasure.Measurer measurer = null;
        if (this.mParent != null) {
            measurer = ((ConstraintWidgetContainer) this.mParent).getMeasurer();
        }
        if (measurer == null) {
            return false;
        }
        int i = 0;
        while (true) {
            boolean skip = true;
            if (i >= this.mWidgetsCount) {
                return true;
            }
            ConstraintWidget widget = this.mWidgets[i];
            if (widget != null && !(widget instanceof Guideline)) {
                ConstraintWidget.DimensionBehaviour widthBehavior = widget.getDimensionBehaviour(0);
                ConstraintWidget.DimensionBehaviour heightBehavior = widget.getDimensionBehaviour(1);
                if (widthBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || widget.mMatchConstraintDefaultWidth == 1 || heightBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || widget.mMatchConstraintDefaultHeight == 1) {
                    skip = false;
                }
                if (!skip) {
                    if (widthBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        widthBehavior = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    }
                    if (heightBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        heightBehavior = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    }
                    this.mMeasure.horizontalBehavior = widthBehavior;
                    this.mMeasure.verticalBehavior = heightBehavior;
                    this.mMeasure.horizontalDimension = widget.getWidth();
                    this.mMeasure.verticalDimension = widget.getHeight();
                    measurer.measure(widget, this.mMeasure);
                    widget.setWidth(this.mMeasure.measuredWidth);
                    widget.setHeight(this.mMeasure.measuredHeight);
                    widget.setBaselineDistance(this.mMeasure.measuredBaseline);
                }
            }
            i++;
        }
    }

    /* access modifiers changed from: protected */
    public void measure(ConstraintWidget widget, ConstraintWidget.DimensionBehaviour horizontalBehavior, int horizontalDimension, ConstraintWidget.DimensionBehaviour verticalBehavior, int verticalDimension) {
        while (this.mMeasurer == null && getParent() != null) {
            this.mMeasurer = ((ConstraintWidgetContainer) getParent()).getMeasurer();
        }
        this.mMeasure.horizontalBehavior = horizontalBehavior;
        this.mMeasure.verticalBehavior = verticalBehavior;
        this.mMeasure.horizontalDimension = horizontalDimension;
        this.mMeasure.verticalDimension = verticalDimension;
        this.mMeasurer.measure(widget, this.mMeasure);
        widget.setWidth(this.mMeasure.measuredWidth);
        widget.setHeight(this.mMeasure.measuredHeight);
        widget.setHasBaseline(this.mMeasure.measuredHasBaseline);
        widget.setBaselineDistance(this.mMeasure.measuredBaseline);
    }
}
