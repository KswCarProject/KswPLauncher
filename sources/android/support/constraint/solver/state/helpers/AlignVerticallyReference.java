package android.support.constraint.solver.state.helpers;

import android.support.constraint.solver.state.ConstraintReference;
import android.support.constraint.solver.state.HelperReference;
import android.support.constraint.solver.state.State;
import java.util.Iterator;

public class AlignVerticallyReference extends HelperReference {
    private float mBias = 0.5f;
    private Object mBottomToBottom;
    private Object mBottomToTop;
    private Object mTopToBottom;
    private Object mTopToTop;

    public AlignVerticallyReference(State state) {
        super(state, State.Helper.ALIGN_VERTICALLY);
    }

    public void apply() {
        Iterator it = this.mReferences.iterator();
        while (it.hasNext()) {
            ConstraintReference reference = this.mState.constraints(it.next());
            reference.clearVertical();
            Object obj = this.mTopToTop;
            if (obj != null) {
                reference.topToTop(obj);
            } else {
                Object obj2 = this.mTopToBottom;
                if (obj2 != null) {
                    reference.topToBottom(obj2);
                } else {
                    reference.topToTop(State.PARENT);
                }
            }
            Object obj3 = this.mBottomToTop;
            if (obj3 != null) {
                reference.bottomToTop(obj3);
            } else {
                Object obj4 = this.mBottomToBottom;
                if (obj4 != null) {
                    reference.bottomToBottom(obj4);
                } else {
                    reference.bottomToBottom(State.PARENT);
                }
            }
            float f = this.mBias;
            if (f != 0.5f) {
                reference.verticalBias(f);
            }
        }
    }

    public void topToTop(Object target) {
        this.mTopToTop = target;
    }

    public void topToBottom(Object target) {
        this.mTopToBottom = target;
    }

    public void bottomToTop(Object target) {
        this.mBottomToTop = target;
    }

    public void bottomToBottom(Object target) {
        this.mBottomToBottom = target;
    }

    public void bias(float bias) {
        this.mBias = bias;
    }
}
