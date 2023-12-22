package android.support.constraint.solver.widgets.analyzer;

import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.Guideline;

/* loaded from: classes.dex */
class GuidelineReference extends WidgetRun {
    public GuidelineReference(ConstraintWidget widget) {
        super(widget);
        widget.horizontalRun.clear();
        widget.verticalRun.clear();
        this.orientation = ((Guideline) widget).getOrientation();
    }

    @Override // android.support.constraint.solver.widgets.analyzer.WidgetRun
    void clear() {
        this.start.clear();
    }

    @Override // android.support.constraint.solver.widgets.analyzer.WidgetRun
    void reset() {
        this.start.resolved = false;
        this.end.resolved = false;
    }

    @Override // android.support.constraint.solver.widgets.analyzer.WidgetRun
    boolean supportsWrapComputation() {
        return false;
    }

    private void addDependency(DependencyNode node) {
        this.start.dependencies.add(node);
        node.targets.add(this.start);
    }

    @Override // android.support.constraint.solver.widgets.analyzer.WidgetRun, android.support.constraint.solver.widgets.analyzer.Dependency
    public void update(Dependency dependency) {
        if (!this.start.readyToSolve || this.start.resolved) {
            return;
        }
        DependencyNode startTarget = this.start.targets.get(0);
        Guideline guideline = (Guideline) this.widget;
        int startPos = (int) ((startTarget.value * guideline.getRelativePercent()) + 0.5f);
        this.start.resolve(startPos);
    }

    @Override // android.support.constraint.solver.widgets.analyzer.WidgetRun
    void apply() {
        Guideline guideline = (Guideline) this.widget;
        int relativeBegin = guideline.getRelativeBegin();
        int relativeEnd = guideline.getRelativeEnd();
        guideline.getRelativePercent();
        if (guideline.getOrientation() == 1) {
            if (relativeBegin != -1) {
                this.start.targets.add(this.widget.mParent.horizontalRun.start);
                this.widget.mParent.horizontalRun.start.dependencies.add(this.start);
                this.start.margin = relativeBegin;
            } else if (relativeEnd != -1) {
                this.start.targets.add(this.widget.mParent.horizontalRun.end);
                this.widget.mParent.horizontalRun.end.dependencies.add(this.start);
                this.start.margin = -relativeEnd;
            } else {
                this.start.delegateToWidgetRun = true;
                this.start.targets.add(this.widget.mParent.horizontalRun.end);
                this.widget.mParent.horizontalRun.end.dependencies.add(this.start);
            }
            addDependency(this.widget.horizontalRun.start);
            addDependency(this.widget.horizontalRun.end);
            return;
        }
        if (relativeBegin != -1) {
            this.start.targets.add(this.widget.mParent.verticalRun.start);
            this.widget.mParent.verticalRun.start.dependencies.add(this.start);
            this.start.margin = relativeBegin;
        } else if (relativeEnd != -1) {
            this.start.targets.add(this.widget.mParent.verticalRun.end);
            this.widget.mParent.verticalRun.end.dependencies.add(this.start);
            this.start.margin = -relativeEnd;
        } else {
            this.start.delegateToWidgetRun = true;
            this.start.targets.add(this.widget.mParent.verticalRun.end);
            this.widget.mParent.verticalRun.end.dependencies.add(this.start);
        }
        addDependency(this.widget.verticalRun.start);
        addDependency(this.widget.verticalRun.end);
    }

    @Override // android.support.constraint.solver.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        Guideline guideline = (Guideline) this.widget;
        if (guideline.getOrientation() == 1) {
            this.widget.setX(this.start.value);
        } else {
            this.widget.setY(this.start.value);
        }
    }
}
