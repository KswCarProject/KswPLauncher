package android.support.constraint.solver.state;

import android.support.constraint.solver.widgets.ConstraintWidget;

public interface Reference {
    void apply();

    ConstraintWidget getConstraintWidget();

    Object getKey();

    void setConstraintWidget(ConstraintWidget constraintWidget);

    void setKey(Object obj);
}
