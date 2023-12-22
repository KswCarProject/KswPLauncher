package android.support.constraint.solver.widgets.analyzer;

import android.support.constraint.solver.widgets.analyzer.DependencyNode;

/* loaded from: classes.dex */
class DimensionDependency extends DependencyNode {
    public int wrapValue;

    public DimensionDependency(WidgetRun run) {
        super(run);
        if (run instanceof HorizontalWidgetRun) {
            this.type = DependencyNode.Type.HORIZONTAL_DIMENSION;
        } else {
            this.type = DependencyNode.Type.VERTICAL_DIMENSION;
        }
    }

    @Override // android.support.constraint.solver.widgets.analyzer.DependencyNode
    public void resolve(int value) {
        if (this.resolved) {
            return;
        }
        this.resolved = true;
        this.value = value;
        for (Dependency node : this.dependencies) {
            node.update(node);
        }
    }
}
