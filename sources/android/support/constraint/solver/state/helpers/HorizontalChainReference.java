package android.support.constraint.solver.state.helpers;

import android.support.constraint.solver.state.ConstraintReference;
import android.support.constraint.solver.state.State;
import java.util.Iterator;

public class HorizontalChainReference extends ChainReference {
    private Object mEndToEnd;
    private Object mEndToStart;
    private Object mStartToEnd;
    private Object mStartToStart;

    public HorizontalChainReference(State state) {
        super(state, State.Helper.HORIZONTAL_CHAIN);
    }

    public void apply() {
        ConstraintReference first = null;
        ConstraintReference previous = null;
        Iterator it = this.mReferences.iterator();
        while (it.hasNext()) {
            this.mState.constraints(it.next()).clearHorizontal();
        }
        Iterator it2 = this.mReferences.iterator();
        while (it2.hasNext()) {
            ConstraintReference reference = this.mState.constraints(it2.next());
            if (first == null) {
                first = reference;
                Object obj = this.mStartToStart;
                if (obj != null) {
                    first.startToStart(obj);
                } else {
                    Object obj2 = this.mStartToEnd;
                    if (obj2 != null) {
                        first.startToEnd(obj2);
                    } else {
                        first.startToStart(State.PARENT);
                    }
                }
            }
            if (previous != null) {
                previous.endToStart(reference.getKey());
                reference.startToEnd(previous.getKey());
            }
            previous = reference;
        }
        if (previous != null) {
            Object obj3 = this.mEndToStart;
            if (obj3 != null) {
                previous.endToStart(obj3);
            } else {
                Object obj4 = this.mEndToEnd;
                if (obj4 != null) {
                    previous.endToEnd(obj4);
                } else {
                    previous.endToEnd(State.PARENT);
                }
            }
        }
        if (!(first == null || this.mBias == 0.5f)) {
            first.horizontalBias(this.mBias);
        }
        switch (AnonymousClass1.$SwitchMap$android$support$constraint$solver$state$State$Chain[this.mStyle.ordinal()]) {
            case 1:
                first.setHorizontalChainStyle(0);
                return;
            case 2:
                first.setHorizontalChainStyle(1);
                return;
            case 3:
                first.setHorizontalChainStyle(2);
                return;
            default:
                return;
        }
    }

    /* renamed from: android.support.constraint.solver.state.helpers.HorizontalChainReference$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$support$constraint$solver$state$State$Chain;

        static {
            int[] iArr = new int[State.Chain.values().length];
            $SwitchMap$android$support$constraint$solver$state$State$Chain = iArr;
            try {
                iArr[State.Chain.SPREAD.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$state$State$Chain[State.Chain.SPREAD_INSIDE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$state$State$Chain[State.Chain.PACKED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public void startToStart(Object target) {
        this.mStartToStart = target;
    }

    public void startToEnd(Object target) {
        this.mStartToEnd = target;
    }

    public void endToStart(Object target) {
        this.mEndToStart = target;
    }

    public void endToEnd(Object target) {
        this.mEndToEnd = target;
    }
}
