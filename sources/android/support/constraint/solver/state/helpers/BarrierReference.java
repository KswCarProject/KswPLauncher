package android.support.constraint.solver.state.helpers;

import android.support.constraint.solver.state.HelperReference;
import android.support.constraint.solver.state.State;
import android.support.constraint.solver.widgets.Barrier;
import android.support.constraint.solver.widgets.HelperWidget;

/* loaded from: classes.dex */
public class BarrierReference extends HelperReference {
    private Barrier mBarrierWidget;
    private State.Direction mDirection;
    private int mMargin;

    public BarrierReference(State state) {
        super(state, State.Helper.BARRIER);
    }

    public void setBarrierDirection(State.Direction barrierDirection) {
        this.mDirection = barrierDirection;
    }

    public void margin(Object value) {
        margin(this.mState.convertDimension(value));
    }

    public void margin(int value) {
        this.mMargin = value;
    }

    @Override // android.support.constraint.solver.state.HelperReference
    public HelperWidget getHelperWidget() {
        if (this.mBarrierWidget == null) {
            this.mBarrierWidget = new Barrier();
        }
        return this.mBarrierWidget;
    }

    @Override // android.support.constraint.solver.state.HelperReference
    public void apply() {
        getHelperWidget();
        int direction = 0;
        switch (C01021.f27x83030585[this.mDirection.ordinal()]) {
            case 1:
            case 2:
                direction = 0;
                break;
            case 3:
            case 4:
                direction = 1;
                break;
            case 5:
                direction = 2;
                break;
            case 6:
                direction = 3;
                break;
        }
        this.mBarrierWidget.setBarrierType(direction);
        this.mBarrierWidget.setMargin(this.mMargin);
    }

    /* renamed from: android.support.constraint.solver.state.helpers.BarrierReference$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C01021 {

        /* renamed from: $SwitchMap$android$support$constraint$solver$state$State$Direction */
        static final /* synthetic */ int[] f27x83030585;

        static {
            int[] iArr = new int[State.Direction.values().length];
            f27x83030585 = iArr;
            try {
                iArr[State.Direction.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f27x83030585[State.Direction.START.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f27x83030585[State.Direction.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f27x83030585[State.Direction.END.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f27x83030585[State.Direction.TOP.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f27x83030585[State.Direction.BOTTOM.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }
}
