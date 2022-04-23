package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;
import android.support.constraint.solver.widgets.ConstraintWidget;
import java.util.HashMap;

public class Barrier extends HelperWidget {
    public static final int BOTTOM = 3;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int TOP = 2;
    private boolean mAllowsGoneWidget = true;
    private int mBarrierType = 0;
    private int mMargin = 0;

    public boolean allowedInBarrier() {
        return true;
    }

    public int getBarrierType() {
        return this.mBarrierType;
    }

    public void setBarrierType(int barrierType) {
        this.mBarrierType = barrierType;
    }

    public void setAllowsGoneWidget(boolean allowsGoneWidget) {
        this.mAllowsGoneWidget = allowsGoneWidget;
    }

    public boolean allowsGoneWidget() {
        return this.mAllowsGoneWidget;
    }

    public void copy(ConstraintWidget src, HashMap<ConstraintWidget, ConstraintWidget> map) {
        super.copy(src, map);
        Barrier srcBarrier = (Barrier) src;
        this.mBarrierType = srcBarrier.mBarrierType;
        this.mAllowsGoneWidget = srcBarrier.mAllowsGoneWidget;
        this.mMargin = srcBarrier.mMargin;
    }

    public String toString() {
        String debug = "[Barrier] " + getDebugName() + " {";
        for (int i = 0; i < this.mWidgetsCount; i++) {
            ConstraintWidget widget = this.mWidgets[i];
            if (i > 0) {
                debug = debug + ", ";
            }
            debug = debug + widget.getDebugName();
        }
        return debug + "}";
    }

    /* access modifiers changed from: protected */
    public void markWidgets() {
        for (int i = 0; i < this.mWidgetsCount; i++) {
            ConstraintWidget widget = this.mWidgets[i];
            int i2 = this.mBarrierType;
            if (i2 == 0 || i2 == 1) {
                widget.setInBarrier(0, true);
            } else if (i2 == 2 || i2 == 3) {
                widget.setInBarrier(1, true);
            }
        }
    }

    public void addToSolver(LinearSystem system) {
        int i;
        LinearSystem linearSystem = system;
        this.mListAnchors[0] = this.mLeft;
        this.mListAnchors[2] = this.mTop;
        this.mListAnchors[1] = this.mRight;
        this.mListAnchors[3] = this.mBottom;
        for (int i2 = 0; i2 < this.mListAnchors.length; i2++) {
            this.mListAnchors[i2].mSolverVariable = linearSystem.createObjectVariable(this.mListAnchors[i2]);
        }
        int i3 = this.mBarrierType;
        if (i3 >= 0 && i3 < 4) {
            ConstraintAnchor position = this.mListAnchors[this.mBarrierType];
            boolean hasMatchConstraintWidgets = false;
            int i4 = 0;
            while (true) {
                if (i4 >= this.mWidgetsCount) {
                    break;
                }
                ConstraintWidget widget = this.mWidgets[i4];
                if (this.mAllowsGoneWidget || widget.allowedInBarrier()) {
                    int i5 = this.mBarrierType;
                    if ((i5 != 0 && i5 != 1) || widget.getHorizontalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || widget.mLeft.mTarget == null || widget.mRight.mTarget == null) {
                        int i6 = this.mBarrierType;
                        if ((i6 == 2 || i6 == 3) && widget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widget.mTop.mTarget != null && widget.mBottom.mTarget != null) {
                            hasMatchConstraintWidgets = true;
                            break;
                        }
                    } else {
                        hasMatchConstraintWidgets = true;
                        break;
                    }
                }
                i4++;
            }
            boolean mHasHorizontalCenteredDependents = this.mLeft.hasCenteredDependents() || this.mRight.hasCenteredDependents();
            boolean mHasVerticalCenteredDependents = this.mTop.hasCenteredDependents() || this.mBottom.hasCenteredDependents();
            int equalityOnReferencesStrength = 5;
            if (!(!hasMatchConstraintWidgets && (((i = this.mBarrierType) == 0 && mHasHorizontalCenteredDependents) || ((i == 2 && mHasVerticalCenteredDependents) || ((i == 1 && mHasHorizontalCenteredDependents) || (i == 3 && mHasVerticalCenteredDependents)))))) {
                equalityOnReferencesStrength = 4;
            }
            for (int i7 = 0; i7 < this.mWidgetsCount; i7++) {
                ConstraintWidget widget2 = this.mWidgets[i7];
                if (this.mAllowsGoneWidget || widget2.allowedInBarrier()) {
                    SolverVariable target = linearSystem.createObjectVariable(widget2.mListAnchors[this.mBarrierType]);
                    widget2.mListAnchors[this.mBarrierType].mSolverVariable = target;
                    int margin = 0;
                    if (widget2.mListAnchors[this.mBarrierType].mTarget != null && widget2.mListAnchors[this.mBarrierType].mTarget.mOwner == this) {
                        margin = 0 + widget2.mListAnchors[this.mBarrierType].mMargin;
                    }
                    int i8 = this.mBarrierType;
                    if (i8 == 0 || i8 == 2) {
                        linearSystem.addLowerBarrier(position.mSolverVariable, target, this.mMargin - margin, hasMatchConstraintWidgets);
                    } else {
                        linearSystem.addGreaterBarrier(position.mSolverVariable, target, this.mMargin + margin, hasMatchConstraintWidgets);
                    }
                    linearSystem.addEquality(position.mSolverVariable, target, this.mMargin + margin, equalityOnReferencesStrength);
                }
            }
            int i9 = this.mBarrierType;
            if (i9 == 0) {
                linearSystem.addEquality(this.mRight.mSolverVariable, this.mLeft.mSolverVariable, 0, 8);
                linearSystem.addEquality(this.mLeft.mSolverVariable, this.mParent.mRight.mSolverVariable, 0, 4);
                linearSystem.addEquality(this.mLeft.mSolverVariable, this.mParent.mLeft.mSolverVariable, 0, 0);
            } else if (i9 == 1) {
                linearSystem.addEquality(this.mLeft.mSolverVariable, this.mRight.mSolverVariable, 0, 8);
                linearSystem.addEquality(this.mLeft.mSolverVariable, this.mParent.mLeft.mSolverVariable, 0, 4);
                linearSystem.addEquality(this.mLeft.mSolverVariable, this.mParent.mRight.mSolverVariable, 0, 0);
            } else if (i9 == 2) {
                linearSystem.addEquality(this.mBottom.mSolverVariable, this.mTop.mSolverVariable, 0, 8);
                linearSystem.addEquality(this.mTop.mSolverVariable, this.mParent.mBottom.mSolverVariable, 0, 4);
                linearSystem.addEquality(this.mTop.mSolverVariable, this.mParent.mTop.mSolverVariable, 0, 0);
            } else if (i9 == 3) {
                linearSystem.addEquality(this.mTop.mSolverVariable, this.mBottom.mSolverVariable, 0, 8);
                linearSystem.addEquality(this.mTop.mSolverVariable, this.mParent.mTop.mSolverVariable, 0, 4);
                linearSystem.addEquality(this.mTop.mSolverVariable, this.mParent.mBottom.mSolverVariable, 0, 0);
            }
        }
    }

    public void setMargin(int margin) {
        this.mMargin = margin;
    }

    public int getMargin() {
        return this.mMargin;
    }
}
