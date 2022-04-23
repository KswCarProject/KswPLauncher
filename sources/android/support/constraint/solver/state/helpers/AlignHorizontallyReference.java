package android.support.constraint.solver.state.helpers;

import android.support.constraint.solver.state.ConstraintReference;
import android.support.constraint.solver.state.HelperReference;
import android.support.constraint.solver.state.State;
import java.util.Iterator;

public class AlignHorizontallyReference extends HelperReference {
    private float mBias = 0.5f;
    private Object mEndToEnd;
    private Object mEndToStart;
    private Object mStartToEnd;
    private Object mStartToStart;

    public AlignHorizontallyReference(State state) {
        super(state, State.Helper.ALIGN_VERTICALLY);
    }

    public void apply() {
        Iterator it = this.mReferences.iterator();
        while (it.hasNext()) {
            ConstraintReference reference = this.mState.constraints(it.next());
            reference.clearHorizontal();
            Object obj = this.mStartToStart;
            if (obj != null) {
                reference.startToStart(obj);
            } else {
                Object obj2 = this.mStartToEnd;
                if (obj2 != null) {
                    reference.startToEnd(obj2);
                } else {
                    reference.startToStart(State.PARENT);
                }
            }
            Object obj3 = this.mEndToStart;
            if (obj3 != null) {
                reference.endToStart(obj3);
            } else {
                Object obj4 = this.mEndToEnd;
                if (obj4 != null) {
                    reference.endToEnd(obj4);
                } else {
                    reference.endToEnd(State.PARENT);
                }
            }
            float f = this.mBias;
            if (f != 0.5f) {
                reference.horizontalBias(f);
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

    public void bias(float bias) {
        this.mBias = bias;
    }
}
