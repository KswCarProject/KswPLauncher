package android.support.constraint.solver;

/* loaded from: classes.dex */
public class GoalRow extends ArrayRow {
    public GoalRow(Cache cache) {
        super(cache);
    }

    @Override // android.support.constraint.solver.ArrayRow, android.support.constraint.solver.LinearSystem.Row
    public void addError(SolverVariable error) {
        super.addError(error);
        error.usageInRowCount--;
    }
}
