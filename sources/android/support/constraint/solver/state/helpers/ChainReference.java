package android.support.constraint.solver.state.helpers;

import android.support.constraint.solver.state.HelperReference;
import android.support.constraint.solver.state.State;

public class ChainReference extends HelperReference {
    protected float mBias = 0.5f;
    protected State.Chain mStyle = State.Chain.SPREAD;

    public ChainReference(State state, State.Helper type) {
        super(state, type);
    }

    public State.Chain getStyle() {
        return State.Chain.SPREAD;
    }

    public void style(State.Chain style) {
        this.mStyle = style;
    }

    public float getBias() {
        return this.mBias;
    }

    public void bias(float bias) {
        this.mBias = bias;
    }
}
