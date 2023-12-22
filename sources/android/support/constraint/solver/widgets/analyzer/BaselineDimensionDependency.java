package android.support.constraint.solver.widgets.analyzer;

/* loaded from: classes.dex */
class BaselineDimensionDependency extends DimensionDependency {
    public BaselineDimensionDependency(WidgetRun run) {
        super(run);
    }

    public void update(DependencyNode node) {
        VerticalWidgetRun vrun = (VerticalWidgetRun) this.run;
        vrun.baseline.margin = this.run.widget.getBaselineDistance();
        this.resolved = true;
    }
}
