package android.support.constraint.solver.state;

import android.support.constraint.solver.state.State;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintWidget;
import java.util.ArrayList;

public class ConstraintReference implements Reference {
    private Object key;
    Object mBaselineToBaseline = null;
    Object mBottomToBottom = null;
    Object mBottomToTop = null;
    private ConstraintWidget mConstraintWidget;
    Object mEndToEnd = null;
    Object mEndToStart = null;
    float mHorizontalBias = 0.5f;
    int mHorizontalChainStyle = 0;
    Dimension mHorizontalDimension = Dimension.Fixed(Dimension.WRAP_DIMENSION);
    State.Constraint mLast = null;
    Object mLeftToLeft = null;
    Object mLeftToRight = null;
    int mMarginBottom = 0;
    int mMarginBottomGone = 0;
    int mMarginEnd = 0;
    int mMarginEndGone = 0;
    int mMarginLeft = 0;
    int mMarginLeftGone = 0;
    int mMarginRight = 0;
    int mMarginRightGone = 0;
    int mMarginStart = 0;
    int mMarginStartGone = 0;
    int mMarginTop = 0;
    int mMarginTopGone = 0;
    Object mRightToLeft = null;
    Object mRightToRight = null;
    Object mStartToEnd = null;
    Object mStartToStart = null;
    final State mState;
    Object mTopToBottom = null;
    Object mTopToTop = null;
    float mVerticalBias = 0.5f;
    int mVerticalChainStyle = 0;
    Dimension mVerticalDimension = Dimension.Fixed(Dimension.WRAP_DIMENSION);
    private Object mView;

    public interface ConstraintReferenceFactory {
        ConstraintReference create(State state);
    }

    public void setKey(Object key2) {
        this.key = key2;
    }

    public Object getKey() {
        return this.key;
    }

    public void setView(Object view) {
        this.mView = view;
        ConstraintWidget constraintWidget = this.mConstraintWidget;
        if (constraintWidget != null) {
            constraintWidget.setCompanionWidget(view);
        }
    }

    public Object getView() {
        return this.mView;
    }

    public void setConstraintWidget(ConstraintWidget widget) {
        if (widget != null) {
            this.mConstraintWidget = widget;
            widget.setCompanionWidget(this.mView);
        }
    }

    public ConstraintWidget getConstraintWidget() {
        if (this.mConstraintWidget == null) {
            ConstraintWidget createConstraintWidget = createConstraintWidget();
            this.mConstraintWidget = createConstraintWidget;
            createConstraintWidget.setCompanionWidget(this.mView);
        }
        return this.mConstraintWidget;
    }

    public ConstraintWidget createConstraintWidget() {
        return new ConstraintWidget(getWidth().getValue(), getHeight().getValue());
    }

    class IncorrectConstraintException extends Exception {
        private final ArrayList<String> mErrors;

        public IncorrectConstraintException(ArrayList<String> errors) {
            this.mErrors = errors;
        }

        public ArrayList<String> getErrors() {
            return this.mErrors;
        }

        public String toString() {
            return "IncorrectConstraintException: " + this.mErrors.toString();
        }
    }

    public void validate() throws IncorrectConstraintException {
        ArrayList<String> errors = new ArrayList<>();
        if (!(this.mLeftToLeft == null || this.mLeftToRight == null)) {
            errors.add("LeftToLeft and LeftToRight both defined");
        }
        if (!(this.mRightToLeft == null || this.mRightToRight == null)) {
            errors.add("RightToLeft and RightToRight both defined");
        }
        if (!(this.mStartToStart == null || this.mStartToEnd == null)) {
            errors.add("StartToStart and StartToEnd both defined");
        }
        if (!(this.mEndToStart == null || this.mEndToEnd == null)) {
            errors.add("EndToStart and EndToEnd both defined");
        }
        if (!((this.mLeftToLeft == null && this.mLeftToRight == null && this.mRightToLeft == null && this.mRightToRight == null) || (this.mStartToStart == null && this.mStartToEnd == null && this.mEndToStart == null && this.mEndToEnd == null))) {
            errors.add("Both left/right and start/end constraints defined");
        }
        if (errors.size() > 0) {
            throw new IncorrectConstraintException(errors);
        }
    }

    private Object get(Object reference) {
        if (reference == null) {
            return null;
        }
        if (!(reference instanceof ConstraintReference)) {
            return this.mState.reference(reference);
        }
        return reference;
    }

    public ConstraintReference(State state) {
        this.mState = state;
    }

    public void setHorizontalChainStyle(int chainStyle) {
        this.mHorizontalChainStyle = chainStyle;
    }

    public int getHorizontalChainStyle() {
        return this.mHorizontalChainStyle;
    }

    public void setVerticalChainStyle(int chainStyle) {
        this.mVerticalChainStyle = chainStyle;
    }

    public int getVerticalChainStyle(int chainStyle) {
        return this.mVerticalChainStyle;
    }

    public ConstraintReference clearVertical() {
        top().clear();
        baseline().clear();
        bottom().clear();
        return this;
    }

    public ConstraintReference clearHorizontal() {
        start().clear();
        end().clear();
        left().clear();
        right().clear();
        return this;
    }

    public ConstraintReference left() {
        if (this.mLeftToLeft != null) {
            this.mLast = State.Constraint.LEFT_TO_LEFT;
        } else {
            this.mLast = State.Constraint.LEFT_TO_RIGHT;
        }
        return this;
    }

    public ConstraintReference right() {
        if (this.mRightToLeft != null) {
            this.mLast = State.Constraint.RIGHT_TO_LEFT;
        } else {
            this.mLast = State.Constraint.RIGHT_TO_RIGHT;
        }
        return this;
    }

    public ConstraintReference start() {
        if (this.mStartToStart != null) {
            this.mLast = State.Constraint.START_TO_START;
        } else {
            this.mLast = State.Constraint.START_TO_END;
        }
        return this;
    }

    public ConstraintReference end() {
        if (this.mEndToStart != null) {
            this.mLast = State.Constraint.END_TO_START;
        } else {
            this.mLast = State.Constraint.END_TO_END;
        }
        return this;
    }

    public ConstraintReference top() {
        if (this.mTopToTop != null) {
            this.mLast = State.Constraint.TOP_TO_TOP;
        } else {
            this.mLast = State.Constraint.TOP_TO_BOTTOM;
        }
        return this;
    }

    public ConstraintReference bottom() {
        if (this.mBottomToTop != null) {
            this.mLast = State.Constraint.BOTTOM_TO_TOP;
        } else {
            this.mLast = State.Constraint.BOTTOM_TO_BOTTOM;
        }
        return this;
    }

    public ConstraintReference baseline() {
        this.mLast = State.Constraint.BASELINE_TO_BASELINE;
        return this;
    }

    private void dereference() {
        this.mLeftToLeft = get(this.mLeftToLeft);
        this.mLeftToRight = get(this.mLeftToRight);
        this.mRightToLeft = get(this.mRightToLeft);
        this.mRightToRight = get(this.mRightToRight);
        this.mStartToStart = get(this.mStartToStart);
        this.mStartToEnd = get(this.mStartToEnd);
        this.mEndToStart = get(this.mEndToStart);
        this.mEndToEnd = get(this.mEndToEnd);
        this.mTopToTop = get(this.mTopToTop);
        this.mTopToBottom = get(this.mTopToBottom);
        this.mBottomToTop = get(this.mBottomToTop);
        this.mBottomToBottom = get(this.mBottomToBottom);
        this.mBaselineToBaseline = get(this.mBaselineToBaseline);
    }

    public ConstraintReference leftToLeft(Object reference) {
        this.mLast = State.Constraint.LEFT_TO_LEFT;
        this.mLeftToLeft = reference;
        return this;
    }

    public ConstraintReference leftToRight(Object reference) {
        this.mLast = State.Constraint.LEFT_TO_RIGHT;
        this.mLeftToRight = reference;
        return this;
    }

    public ConstraintReference rightToLeft(Object reference) {
        this.mLast = State.Constraint.RIGHT_TO_LEFT;
        this.mRightToLeft = reference;
        return this;
    }

    public ConstraintReference rightToRight(Object reference) {
        this.mLast = State.Constraint.RIGHT_TO_RIGHT;
        this.mRightToRight = reference;
        return this;
    }

    public ConstraintReference startToStart(Object reference) {
        this.mLast = State.Constraint.START_TO_START;
        this.mStartToStart = reference;
        return this;
    }

    public ConstraintReference startToEnd(Object reference) {
        this.mLast = State.Constraint.START_TO_END;
        this.mStartToEnd = reference;
        return this;
    }

    public ConstraintReference endToStart(Object reference) {
        this.mLast = State.Constraint.END_TO_START;
        this.mEndToStart = reference;
        return this;
    }

    public ConstraintReference endToEnd(Object reference) {
        this.mLast = State.Constraint.END_TO_END;
        this.mEndToEnd = reference;
        return this;
    }

    public ConstraintReference topToTop(Object reference) {
        this.mLast = State.Constraint.TOP_TO_TOP;
        this.mTopToTop = reference;
        return this;
    }

    public ConstraintReference topToBottom(Object reference) {
        this.mLast = State.Constraint.TOP_TO_BOTTOM;
        this.mTopToBottom = reference;
        return this;
    }

    public ConstraintReference bottomToTop(Object reference) {
        this.mLast = State.Constraint.BOTTOM_TO_TOP;
        this.mBottomToTop = reference;
        return this;
    }

    public ConstraintReference bottomToBottom(Object reference) {
        this.mLast = State.Constraint.BOTTOM_TO_BOTTOM;
        this.mBottomToBottom = reference;
        return this;
    }

    public ConstraintReference baselineToBaseline(Object reference) {
        this.mLast = State.Constraint.BASELINE_TO_BASELINE;
        this.mBaselineToBaseline = reference;
        return this;
    }

    public ConstraintReference centerHorizontally(Object reference) {
        Object ref = get(reference);
        this.mStartToStart = ref;
        this.mEndToEnd = ref;
        this.mLast = State.Constraint.CENTER_HORIZONTALLY;
        this.mHorizontalBias = 0.5f;
        return this;
    }

    public ConstraintReference centerVertically(Object reference) {
        Object ref = get(reference);
        this.mTopToTop = ref;
        this.mBottomToBottom = ref;
        this.mLast = State.Constraint.CENTER_VERTICALLY;
        this.mVerticalBias = 0.5f;
        return this;
    }

    public ConstraintReference width(Dimension dimension) {
        return setWidth(dimension);
    }

    public ConstraintReference height(Dimension dimension) {
        return setHeight(dimension);
    }

    public Dimension getWidth() {
        return this.mHorizontalDimension;
    }

    public ConstraintReference setWidth(Dimension dimension) {
        this.mHorizontalDimension = dimension;
        return this;
    }

    public Dimension getHeight() {
        return this.mVerticalDimension;
    }

    public ConstraintReference setHeight(Dimension dimension) {
        this.mVerticalDimension = dimension;
        return this;
    }

    public ConstraintReference margin(Object marginValue) {
        return margin(this.mState.convertDimension(marginValue));
    }

    /* renamed from: android.support.constraint.solver.state.ConstraintReference$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$support$constraint$solver$state$State$Constraint;

        static {
            int[] iArr = new int[State.Constraint.values().length];
            $SwitchMap$android$support$constraint$solver$state$State$Constraint = iArr;
            try {
                iArr[State.Constraint.LEFT_TO_LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$state$State$Constraint[State.Constraint.LEFT_TO_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$state$State$Constraint[State.Constraint.RIGHT_TO_LEFT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$state$State$Constraint[State.Constraint.RIGHT_TO_RIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$state$State$Constraint[State.Constraint.START_TO_START.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$state$State$Constraint[State.Constraint.START_TO_END.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$state$State$Constraint[State.Constraint.END_TO_START.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$state$State$Constraint[State.Constraint.END_TO_END.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$state$State$Constraint[State.Constraint.TOP_TO_TOP.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$state$State$Constraint[State.Constraint.TOP_TO_BOTTOM.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$state$State$Constraint[State.Constraint.BOTTOM_TO_TOP.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$state$State$Constraint[State.Constraint.BOTTOM_TO_BOTTOM.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$state$State$Constraint[State.Constraint.BASELINE_TO_BASELINE.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$state$State$Constraint[State.Constraint.CENTER_HORIZONTALLY.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$state$State$Constraint[State.Constraint.CENTER_VERTICALLY.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
        }
    }

    public ConstraintReference margin(int value) {
        if (this.mLast != null) {
            switch (AnonymousClass1.$SwitchMap$android$support$constraint$solver$state$State$Constraint[this.mLast.ordinal()]) {
                case 1:
                case 2:
                    this.mMarginLeft = value;
                    break;
                case 3:
                case 4:
                    this.mMarginRight = value;
                    break;
                case 5:
                case 6:
                    this.mMarginStart = value;
                    break;
                case 7:
                case 8:
                    this.mMarginEnd = value;
                    break;
                case 9:
                case 10:
                    this.mMarginTop = value;
                    break;
                case 11:
                case 12:
                    this.mMarginBottom = value;
                    break;
            }
        } else {
            this.mMarginLeft = value;
            this.mMarginRight = value;
            this.mMarginStart = value;
            this.mMarginEnd = value;
            this.mMarginTop = value;
            this.mMarginBottom = value;
        }
        return this;
    }

    public ConstraintReference marginGone(int value) {
        if (this.mLast != null) {
            switch (AnonymousClass1.$SwitchMap$android$support$constraint$solver$state$State$Constraint[this.mLast.ordinal()]) {
                case 1:
                case 2:
                    this.mMarginLeftGone = value;
                    break;
                case 3:
                case 4:
                    this.mMarginRightGone = value;
                    break;
                case 5:
                case 6:
                    this.mMarginStartGone = value;
                    break;
                case 7:
                case 8:
                    this.mMarginEndGone = value;
                    break;
                case 9:
                case 10:
                    this.mMarginTopGone = value;
                    break;
                case 11:
                case 12:
                    this.mMarginBottomGone = value;
                    break;
            }
        } else {
            this.mMarginLeftGone = value;
            this.mMarginRightGone = value;
            this.mMarginStartGone = value;
            this.mMarginEndGone = value;
            this.mMarginTopGone = value;
            this.mMarginBottomGone = value;
        }
        return this;
    }

    public ConstraintReference horizontalBias(float value) {
        this.mHorizontalBias = value;
        return this;
    }

    public ConstraintReference verticalBias(float value) {
        this.mVerticalBias = value;
        return this;
    }

    public ConstraintReference bias(float value) {
        if (this.mLast == null) {
            return this;
        }
        switch (AnonymousClass1.$SwitchMap$android$support$constraint$solver$state$State$Constraint[this.mLast.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 14:
                this.mHorizontalBias = value;
                break;
            case 9:
            case 10:
            case 11:
            case 12:
            case 15:
                this.mVerticalBias = value;
                break;
        }
        return this;
    }

    public ConstraintReference clear() {
        if (this.mLast != null) {
            switch (AnonymousClass1.$SwitchMap$android$support$constraint$solver$state$State$Constraint[this.mLast.ordinal()]) {
                case 1:
                case 2:
                    this.mLeftToLeft = null;
                    this.mLeftToRight = null;
                    this.mMarginLeft = 0;
                    this.mMarginLeftGone = 0;
                    break;
                case 3:
                case 4:
                    this.mRightToLeft = null;
                    this.mRightToRight = null;
                    this.mMarginRight = 0;
                    this.mMarginRightGone = 0;
                    break;
                case 5:
                case 6:
                    this.mStartToStart = null;
                    this.mStartToEnd = null;
                    this.mMarginStart = 0;
                    this.mMarginStartGone = 0;
                    break;
                case 7:
                case 8:
                    this.mEndToStart = null;
                    this.mEndToEnd = null;
                    this.mMarginEnd = 0;
                    this.mMarginEndGone = 0;
                    break;
                case 9:
                case 10:
                    this.mTopToTop = null;
                    this.mTopToBottom = null;
                    this.mMarginTop = 0;
                    this.mMarginTopGone = 0;
                    break;
                case 11:
                case 12:
                    this.mBottomToTop = null;
                    this.mBottomToBottom = null;
                    this.mMarginBottom = 0;
                    this.mMarginBottomGone = 0;
                    break;
                case 13:
                    this.mBaselineToBaseline = null;
                    break;
            }
        } else {
            this.mLeftToLeft = null;
            this.mLeftToRight = null;
            this.mMarginLeft = 0;
            this.mRightToLeft = null;
            this.mRightToRight = null;
            this.mMarginRight = 0;
            this.mStartToStart = null;
            this.mStartToEnd = null;
            this.mMarginStart = 0;
            this.mEndToStart = null;
            this.mEndToEnd = null;
            this.mMarginEnd = 0;
            this.mTopToTop = null;
            this.mTopToBottom = null;
            this.mMarginTop = 0;
            this.mBottomToTop = null;
            this.mBottomToBottom = null;
            this.mMarginBottom = 0;
            this.mBaselineToBaseline = null;
            this.mHorizontalBias = 0.5f;
            this.mVerticalBias = 0.5f;
            this.mMarginLeftGone = 0;
            this.mMarginRightGone = 0;
            this.mMarginStartGone = 0;
            this.mMarginEndGone = 0;
            this.mMarginTopGone = 0;
            this.mMarginBottomGone = 0;
        }
        return this;
    }

    private ConstraintWidget getTarget(Object target) {
        if (target instanceof Reference) {
            return ((Reference) target).getConstraintWidget();
        }
        return null;
    }

    private void applyConnection(ConstraintWidget widget, Object opaqueTarget, State.Constraint type) {
        ConstraintWidget target = getTarget(opaqueTarget);
        if (target != null) {
            int i = AnonymousClass1.$SwitchMap$android$support$constraint$solver$state$State$Constraint[type.ordinal()];
            switch (AnonymousClass1.$SwitchMap$android$support$constraint$solver$state$State$Constraint[type.ordinal()]) {
                case 1:
                    widget.getAnchor(ConstraintAnchor.Type.LEFT).connect(target.getAnchor(ConstraintAnchor.Type.LEFT), this.mMarginLeft, this.mMarginLeftGone, false);
                    return;
                case 2:
                    widget.getAnchor(ConstraintAnchor.Type.LEFT).connect(target.getAnchor(ConstraintAnchor.Type.RIGHT), this.mMarginLeft, this.mMarginLeftGone, false);
                    return;
                case 3:
                    widget.getAnchor(ConstraintAnchor.Type.RIGHT).connect(target.getAnchor(ConstraintAnchor.Type.LEFT), this.mMarginRight, this.mMarginRightGone, false);
                    return;
                case 4:
                    widget.getAnchor(ConstraintAnchor.Type.RIGHT).connect(target.getAnchor(ConstraintAnchor.Type.RIGHT), this.mMarginRight, this.mMarginRightGone, false);
                    return;
                case 5:
                    widget.getAnchor(ConstraintAnchor.Type.LEFT).connect(target.getAnchor(ConstraintAnchor.Type.LEFT), this.mMarginStart, this.mMarginStartGone, false);
                    return;
                case 6:
                    widget.getAnchor(ConstraintAnchor.Type.LEFT).connect(target.getAnchor(ConstraintAnchor.Type.RIGHT), this.mMarginStart, this.mMarginStartGone, false);
                    return;
                case 7:
                    widget.getAnchor(ConstraintAnchor.Type.RIGHT).connect(target.getAnchor(ConstraintAnchor.Type.LEFT), this.mMarginEnd, this.mMarginEndGone, false);
                    return;
                case 8:
                    widget.getAnchor(ConstraintAnchor.Type.RIGHT).connect(target.getAnchor(ConstraintAnchor.Type.RIGHT), this.mMarginEnd, this.mMarginEndGone, false);
                    return;
                case 9:
                    widget.getAnchor(ConstraintAnchor.Type.TOP).connect(target.getAnchor(ConstraintAnchor.Type.TOP), this.mMarginTop, this.mMarginTopGone, false);
                    return;
                case 10:
                    widget.getAnchor(ConstraintAnchor.Type.TOP).connect(target.getAnchor(ConstraintAnchor.Type.BOTTOM), this.mMarginTop, this.mMarginTopGone, false);
                    return;
                case 11:
                    widget.getAnchor(ConstraintAnchor.Type.BOTTOM).connect(target.getAnchor(ConstraintAnchor.Type.TOP), this.mMarginBottom, this.mMarginBottomGone, false);
                    return;
                case 12:
                    widget.getAnchor(ConstraintAnchor.Type.BOTTOM).connect(target.getAnchor(ConstraintAnchor.Type.BOTTOM), this.mMarginBottom, this.mMarginBottomGone, false);
                    return;
                case 13:
                    widget.immediateConnect(ConstraintAnchor.Type.BASELINE, target, ConstraintAnchor.Type.BASELINE, 0, 0);
                    return;
                default:
                    return;
            }
        }
    }

    public void apply() {
        ConstraintWidget constraintWidget = this.mConstraintWidget;
        if (constraintWidget != null) {
            this.mHorizontalDimension.apply(this.mState, constraintWidget, 0);
            this.mVerticalDimension.apply(this.mState, this.mConstraintWidget, 1);
            dereference();
            applyConnection(this.mConstraintWidget, this.mLeftToLeft, State.Constraint.LEFT_TO_LEFT);
            applyConnection(this.mConstraintWidget, this.mLeftToRight, State.Constraint.LEFT_TO_RIGHT);
            applyConnection(this.mConstraintWidget, this.mRightToLeft, State.Constraint.RIGHT_TO_LEFT);
            applyConnection(this.mConstraintWidget, this.mRightToRight, State.Constraint.RIGHT_TO_RIGHT);
            applyConnection(this.mConstraintWidget, this.mStartToStart, State.Constraint.START_TO_START);
            applyConnection(this.mConstraintWidget, this.mStartToEnd, State.Constraint.START_TO_END);
            applyConnection(this.mConstraintWidget, this.mEndToStart, State.Constraint.END_TO_START);
            applyConnection(this.mConstraintWidget, this.mEndToEnd, State.Constraint.END_TO_END);
            applyConnection(this.mConstraintWidget, this.mTopToTop, State.Constraint.TOP_TO_TOP);
            applyConnection(this.mConstraintWidget, this.mTopToBottom, State.Constraint.TOP_TO_BOTTOM);
            applyConnection(this.mConstraintWidget, this.mBottomToTop, State.Constraint.BOTTOM_TO_TOP);
            applyConnection(this.mConstraintWidget, this.mBottomToBottom, State.Constraint.BOTTOM_TO_BOTTOM);
            applyConnection(this.mConstraintWidget, this.mBaselineToBaseline, State.Constraint.BASELINE_TO_BASELINE);
            int i = this.mHorizontalChainStyle;
            if (i != 0) {
                this.mConstraintWidget.setHorizontalChainStyle(i);
            }
            int i2 = this.mVerticalChainStyle;
            if (i2 != 0) {
                this.mConstraintWidget.setVerticalChainStyle(i2);
            }
            this.mConstraintWidget.setHorizontalBiasPercent(this.mHorizontalBias);
            this.mConstraintWidget.setVerticalBiasPercent(this.mVerticalBias);
        }
    }
}
