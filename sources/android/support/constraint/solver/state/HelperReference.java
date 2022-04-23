package android.support.constraint.solver.state;

import android.support.constraint.solver.state.State;
import android.support.constraint.solver.widgets.HelperWidget;
import java.util.ArrayList;

public class HelperReference {
    private HelperWidget mHelperWidget;
    protected ArrayList<Object> mReferences = new ArrayList<>();
    protected final State mState;
    final State.Helper mType;

    public HelperReference(State state, State.Helper type) {
        this.mState = state;
        this.mType = type;
    }

    public State.Helper getType() {
        return this.mType;
    }

    public HelperReference add(Object... objects) {
        for (Object object : objects) {
            this.mReferences.add(object);
        }
        return this;
    }

    public void setHelperWidget(HelperWidget helperWidget) {
        this.mHelperWidget = helperWidget;
    }

    public HelperWidget getHelperWidget() {
        return this.mHelperWidget;
    }

    public void apply() {
    }
}
