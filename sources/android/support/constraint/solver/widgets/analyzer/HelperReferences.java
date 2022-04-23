package android.support.constraint.solver.widgets.analyzer;

import android.support.constraint.solver.widgets.Barrier;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.analyzer.DependencyNode;

class HelperReferences extends WidgetRun {
    public HelperReferences(ConstraintWidget widget) {
        super(widget);
    }

    /* access modifiers changed from: package-private */
    public void clear() {
        this.runGroup = null;
        this.start.clear();
    }

    /* access modifiers changed from: package-private */
    public void reset() {
        this.start.resolved = false;
    }

    /* access modifiers changed from: package-private */
    public boolean supportsWrapComputation() {
        return false;
    }

    private void addDependency(DependencyNode node) {
        this.start.dependencies.add(node);
        node.targets.add(this.start);
    }

    /* access modifiers changed from: package-private */
    public void apply() {
        if (this.widget instanceof Barrier) {
            this.start.delegateToWidgetRun = true;
            Barrier barrier = (Barrier) this.widget;
            int type = barrier.getBarrierType();
            boolean allowsGoneWidget = barrier.allowsGoneWidget();
            switch (type) {
                case 0:
                    this.start.type = DependencyNode.Type.LEFT;
                    for (int i = 0; i < barrier.mWidgetsCount; i++) {
                        ConstraintWidget refwidget = barrier.mWidgets[i];
                        if (allowsGoneWidget || refwidget.getVisibility() != 8) {
                            DependencyNode target = refwidget.horizontalRun.start;
                            target.dependencies.add(this.start);
                            this.start.targets.add(target);
                        }
                    }
                    addDependency(this.widget.horizontalRun.start);
                    addDependency(this.widget.horizontalRun.end);
                    return;
                case 1:
                    this.start.type = DependencyNode.Type.RIGHT;
                    for (int i2 = 0; i2 < barrier.mWidgetsCount; i2++) {
                        ConstraintWidget refwidget2 = barrier.mWidgets[i2];
                        if (allowsGoneWidget || refwidget2.getVisibility() != 8) {
                            DependencyNode target2 = refwidget2.horizontalRun.end;
                            target2.dependencies.add(this.start);
                            this.start.targets.add(target2);
                        }
                    }
                    addDependency(this.widget.horizontalRun.start);
                    addDependency(this.widget.horizontalRun.end);
                    return;
                case 2:
                    this.start.type = DependencyNode.Type.TOP;
                    for (int i3 = 0; i3 < barrier.mWidgetsCount; i3++) {
                        ConstraintWidget refwidget3 = barrier.mWidgets[i3];
                        if (allowsGoneWidget || refwidget3.getVisibility() != 8) {
                            DependencyNode target3 = refwidget3.verticalRun.start;
                            target3.dependencies.add(this.start);
                            this.start.targets.add(target3);
                        }
                    }
                    addDependency(this.widget.verticalRun.start);
                    addDependency(this.widget.verticalRun.end);
                    return;
                case 3:
                    this.start.type = DependencyNode.Type.BOTTOM;
                    for (int i4 = 0; i4 < barrier.mWidgetsCount; i4++) {
                        ConstraintWidget refwidget4 = barrier.mWidgets[i4];
                        if (allowsGoneWidget || refwidget4.getVisibility() != 8) {
                            DependencyNode target4 = refwidget4.verticalRun.end;
                            target4.dependencies.add(this.start);
                            this.start.targets.add(target4);
                        }
                    }
                    addDependency(this.widget.verticalRun.start);
                    addDependency(this.widget.verticalRun.end);
                    return;
                default:
                    return;
            }
        }
    }

    public void update(Dependency dependency) {
        Barrier barrier = (Barrier) this.widget;
        int type = barrier.getBarrierType();
        int min = -1;
        int max = 0;
        for (DependencyNode node : this.start.targets) {
            int value = node.value;
            if (min == -1 || value < min) {
                min = value;
            }
            if (max < value) {
                max = value;
            }
        }
        if (type == 0 || type == 2) {
            this.start.resolve(barrier.getMargin() + min);
        } else {
            this.start.resolve(barrier.getMargin() + max);
        }
    }

    public void applyToWidget() {
        if (this.widget instanceof Barrier) {
            int type = ((Barrier) this.widget).getBarrierType();
            if (type == 0 || type == 1) {
                this.widget.setX(this.start.value);
            } else {
                this.widget.setY(this.start.value);
            }
        }
    }
}
