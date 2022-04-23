package android.support.constraint.solver.widgets.analyzer;

import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.ConstraintWidgetContainer;
import java.util.ArrayList;
import java.util.Iterator;

public class ChainRun extends WidgetRun {
    private int chainStyle;
    ArrayList<WidgetRun> widgets = new ArrayList<>();

    public ChainRun(ConstraintWidget widget, int orientation) {
        super(widget);
        this.orientation = orientation;
        build();
    }

    public String toString() {
        String log = "ChainRun " + (this.orientation == 0 ? "horizontal : " : "vertical : ");
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            log = ((log + "<") + it.next()) + "> ";
        }
        return log;
    }

    /* access modifiers changed from: package-private */
    public boolean supportsWrapComputation() {
        int count = this.widgets.size();
        for (int i = 0; i < count; i++) {
            if (!this.widgets.get(i).supportsWrapComputation()) {
                return false;
            }
        }
        return true;
    }

    public long getWrapDimension() {
        int count = this.widgets.size();
        long wrapDimension = 0;
        for (int i = 0; i < count; i++) {
            WidgetRun run = this.widgets.get(i);
            wrapDimension = wrapDimension + ((long) run.start.margin) + run.getWrapDimension() + ((long) run.end.margin);
        }
        return wrapDimension;
    }

    private void build() {
        ConstraintWidget current = this.widget;
        ConstraintWidget previous = current.getPreviousChainMember(this.orientation);
        while (previous != null) {
            current = previous;
            previous = current.getPreviousChainMember(this.orientation);
        }
        this.widget = current;
        this.widgets.add(current.getRun(this.orientation));
        ConstraintWidget next = current.getNextChainMember(this.orientation);
        while (next != null) {
            ConstraintWidget current2 = next;
            this.widgets.add(current2.getRun(this.orientation));
            next = current2.getNextChainMember(this.orientation);
        }
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            WidgetRun run = it.next();
            if (this.orientation == 0) {
                run.widget.horizontalChainRun = this;
            } else if (this.orientation == 1) {
                run.widget.verticalChainRun = this;
            }
        }
        if ((this.orientation == 0 && ((ConstraintWidgetContainer) this.widget.getParent()).isRtl()) && this.widgets.size() > 1) {
            ArrayList<WidgetRun> arrayList = this.widgets;
            this.widget = arrayList.get(arrayList.size() - 1).widget;
        }
        this.chainStyle = this.orientation == 0 ? this.widget.getHorizontalChainStyle() : this.widget.getVerticalChainStyle();
    }

    /* access modifiers changed from: package-private */
    public void clear() {
        this.runGroup = null;
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            it.next().clear();
        }
    }

    /* access modifiers changed from: package-private */
    public void reset() {
        this.start.resolved = false;
        this.end.resolved = false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:58:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00f7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void update(android.support.constraint.solver.widgets.analyzer.Dependency r27) {
        /*
            r26 = this;
            r0 = r26
            android.support.constraint.solver.widgets.analyzer.DependencyNode r1 = r0.start
            boolean r1 = r1.resolved
            if (r1 == 0) goto L_0x04a5
            android.support.constraint.solver.widgets.analyzer.DependencyNode r1 = r0.end
            boolean r1 = r1.resolved
            if (r1 != 0) goto L_0x0010
            goto L_0x04a5
        L_0x0010:
            android.support.constraint.solver.widgets.ConstraintWidget r1 = r0.widget
            android.support.constraint.solver.widgets.ConstraintWidget r1 = r1.getParent()
            r2 = 0
            if (r1 == 0) goto L_0x0024
            boolean r3 = r1 instanceof android.support.constraint.solver.widgets.ConstraintWidgetContainer
            if (r3 == 0) goto L_0x0024
            r3 = r1
            android.support.constraint.solver.widgets.ConstraintWidgetContainer r3 = (android.support.constraint.solver.widgets.ConstraintWidgetContainer) r3
            boolean r2 = r3.isRtl()
        L_0x0024:
            android.support.constraint.solver.widgets.analyzer.DependencyNode r3 = r0.end
            int r3 = r3.value
            android.support.constraint.solver.widgets.analyzer.DependencyNode r4 = r0.start
            int r4 = r4.value
            int r3 = r3 - r4
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            java.util.ArrayList<android.support.constraint.solver.widgets.analyzer.WidgetRun> r8 = r0.widgets
            int r8 = r8.size()
            r9 = -1
            r10 = 0
        L_0x0039:
            r11 = 8
            if (r10 >= r8) goto L_0x0052
            java.util.ArrayList<android.support.constraint.solver.widgets.analyzer.WidgetRun> r12 = r0.widgets
            java.lang.Object r12 = r12.get(r10)
            android.support.constraint.solver.widgets.analyzer.WidgetRun r12 = (android.support.constraint.solver.widgets.analyzer.WidgetRun) r12
            android.support.constraint.solver.widgets.ConstraintWidget r13 = r12.widget
            int r13 = r13.getVisibility()
            if (r13 != r11) goto L_0x0051
            int r10 = r10 + 1
            goto L_0x0039
        L_0x0051:
            r9 = r10
        L_0x0052:
            r10 = -1
            int r12 = r8 + -1
        L_0x0055:
            if (r12 < 0) goto L_0x006c
            java.util.ArrayList<android.support.constraint.solver.widgets.analyzer.WidgetRun> r13 = r0.widgets
            java.lang.Object r13 = r13.get(r12)
            android.support.constraint.solver.widgets.analyzer.WidgetRun r13 = (android.support.constraint.solver.widgets.analyzer.WidgetRun) r13
            android.support.constraint.solver.widgets.ConstraintWidget r14 = r13.widget
            int r14 = r14.getVisibility()
            if (r14 != r11) goto L_0x006b
            int r12 = r12 + -1
            goto L_0x0055
        L_0x006b:
            r10 = r12
        L_0x006c:
            r12 = 0
        L_0x006d:
            r15 = 2
            if (r12 >= r15) goto L_0x0121
            r17 = 0
            r15 = r17
        L_0x0074:
            if (r15 >= r8) goto L_0x010e
            java.util.ArrayList<android.support.constraint.solver.widgets.analyzer.WidgetRun> r13 = r0.widgets
            java.lang.Object r13 = r13.get(r15)
            android.support.constraint.solver.widgets.analyzer.WidgetRun r13 = (android.support.constraint.solver.widgets.analyzer.WidgetRun) r13
            android.support.constraint.solver.widgets.ConstraintWidget r14 = r13.widget
            int r14 = r14.getVisibility()
            if (r14 != r11) goto L_0x008a
            r19 = r1
            goto L_0x0106
        L_0x008a:
            int r7 = r7 + 1
            if (r15 <= 0) goto L_0x0095
            if (r15 < r9) goto L_0x0095
            android.support.constraint.solver.widgets.analyzer.DependencyNode r14 = r13.start
            int r14 = r14.margin
            int r4 = r4 + r14
        L_0x0095:
            android.support.constraint.solver.widgets.analyzer.DimensionDependency r14 = r13.dimension
            int r14 = r14.value
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r11 = r13.dimensionBehavior
            r19 = r1
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r11 == r1) goto L_0x00a3
            r1 = 1
            goto L_0x00a4
        L_0x00a3:
            r1 = 0
        L_0x00a4:
            if (r1 == 0) goto L_0x00c7
            int r11 = r0.orientation
            if (r11 != 0) goto L_0x00b5
            android.support.constraint.solver.widgets.ConstraintWidget r11 = r13.widget
            android.support.constraint.solver.widgets.analyzer.HorizontalWidgetRun r11 = r11.horizontalRun
            android.support.constraint.solver.widgets.analyzer.DimensionDependency r11 = r11.dimension
            boolean r11 = r11.resolved
            if (r11 != 0) goto L_0x00b5
            return
        L_0x00b5:
            int r11 = r0.orientation
            r20 = r1
            r1 = 1
            if (r11 != r1) goto L_0x00e0
            android.support.constraint.solver.widgets.ConstraintWidget r1 = r13.widget
            android.support.constraint.solver.widgets.analyzer.VerticalWidgetRun r1 = r1.verticalRun
            android.support.constraint.solver.widgets.analyzer.DimensionDependency r1 = r1.dimension
            boolean r1 = r1.resolved
            if (r1 != 0) goto L_0x00e0
            return
        L_0x00c7:
            r20 = r1
            int r1 = r13.matchConstraintsType
            r11 = 1
            if (r1 != r11) goto L_0x00d8
            if (r12 != 0) goto L_0x00d8
            r1 = 1
            android.support.constraint.solver.widgets.analyzer.DimensionDependency r11 = r13.dimension
            int r14 = r11.wrapValue
            int r5 = r5 + 1
            goto L_0x00e2
        L_0x00d8:
            android.support.constraint.solver.widgets.analyzer.DimensionDependency r1 = r13.dimension
            boolean r1 = r1.resolved
            if (r1 == 0) goto L_0x00e0
            r1 = 1
            goto L_0x00e2
        L_0x00e0:
            r1 = r20
        L_0x00e2:
            if (r1 != 0) goto L_0x00f7
            int r5 = r5 + 1
            android.support.constraint.solver.widgets.ConstraintWidget r11 = r13.widget
            float[] r11 = r11.mWeight
            r20 = r1
            int r1 = r0.orientation
            r1 = r11[r1]
            r11 = 0
            int r21 = (r1 > r11 ? 1 : (r1 == r11 ? 0 : -1))
            if (r21 < 0) goto L_0x00f6
            float r6 = r6 + r1
        L_0x00f6:
            goto L_0x00fa
        L_0x00f7:
            r20 = r1
            int r4 = r4 + r14
        L_0x00fa:
            int r1 = r8 + -1
            if (r15 >= r1) goto L_0x0106
            if (r15 >= r10) goto L_0x0106
            android.support.constraint.solver.widgets.analyzer.DependencyNode r1 = r13.end
            int r1 = r1.margin
            int r1 = -r1
            int r4 = r4 + r1
        L_0x0106:
            int r15 = r15 + 1
            r1 = r19
            r11 = 8
            goto L_0x0074
        L_0x010e:
            r19 = r1
            if (r4 < r3) goto L_0x0123
            if (r5 != 0) goto L_0x0115
            goto L_0x0123
        L_0x0115:
            r7 = 0
            r5 = 0
            r4 = 0
            r6 = 0
            int r12 = r12 + 1
            r1 = r19
            r11 = 8
            goto L_0x006d
        L_0x0121:
            r19 = r1
        L_0x0123:
            android.support.constraint.solver.widgets.analyzer.DependencyNode r1 = r0.start
            int r1 = r1.value
            if (r2 == 0) goto L_0x012d
            android.support.constraint.solver.widgets.analyzer.DependencyNode r11 = r0.end
            int r1 = r11.value
        L_0x012d:
            r11 = 1056964608(0x3f000000, float:0.5)
            if (r4 <= r3) goto L_0x0144
            r12 = 1073741824(0x40000000, float:2.0)
            if (r2 == 0) goto L_0x013d
            int r13 = r4 - r3
            float r13 = (float) r13
            float r13 = r13 / r12
            float r13 = r13 + r11
            int r12 = (int) r13
            int r1 = r1 + r12
            goto L_0x0144
        L_0x013d:
            int r13 = r4 - r3
            float r13 = (float) r13
            float r13 = r13 / r12
            float r13 = r13 + r11
            int r12 = (int) r13
            int r1 = r1 - r12
        L_0x0144:
            r12 = 0
            if (r5 <= 0) goto L_0x0271
            int r13 = r3 - r4
            float r13 = (float) r13
            float r14 = (float) r5
            float r13 = r13 / r14
            float r13 = r13 + r11
            int r12 = (int) r13
            r13 = 0
            r14 = 0
        L_0x0150:
            if (r14 >= r8) goto L_0x021f
            java.util.ArrayList<android.support.constraint.solver.widgets.analyzer.WidgetRun> r15 = r0.widgets
            java.lang.Object r15 = r15.get(r14)
            android.support.constraint.solver.widgets.analyzer.WidgetRun r15 = (android.support.constraint.solver.widgets.analyzer.WidgetRun) r15
            android.support.constraint.solver.widgets.ConstraintWidget r11 = r15.widget
            int r11 = r11.getVisibility()
            r21 = r1
            r1 = 8
            if (r11 != r1) goto L_0x0170
            r23 = r2
            r22 = r4
            r24 = r6
            r25 = r12
            goto L_0x020f
        L_0x0170:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = r15.dimensionBehavior
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r11 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r1 != r11) goto L_0x0207
            android.support.constraint.solver.widgets.analyzer.DimensionDependency r1 = r15.dimension
            boolean r1 = r1.resolved
            if (r1 != 0) goto L_0x0207
            r1 = r12
            r11 = 0
            int r18 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
            if (r18 <= 0) goto L_0x0198
            android.support.constraint.solver.widgets.ConstraintWidget r11 = r15.widget
            float[] r11 = r11.mWeight
            r22 = r1
            int r1 = r0.orientation
            r1 = r11[r1]
            int r11 = r3 - r4
            float r11 = (float) r11
            float r11 = r11 * r1
            float r11 = r11 / r6
            r20 = 1056964608(0x3f000000, float:0.5)
            float r11 = r11 + r20
            int r11 = (int) r11
            r1 = r11
            goto L_0x019a
        L_0x0198:
            r22 = r1
        L_0x019a:
            int r11 = r0.orientation
            if (r11 != 0) goto L_0x01d4
            android.support.constraint.solver.widgets.ConstraintWidget r11 = r15.widget
            int r11 = r11.mMatchConstraintMaxWidth
            r22 = r4
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r15.widget
            int r4 = r4.mMatchConstraintMinWidth
            r23 = r1
            r24 = r6
            int r6 = r15.matchConstraintsType
            r25 = r12
            r12 = 1
            if (r6 != r12) goto L_0x01c0
            android.support.constraint.solver.widgets.analyzer.DimensionDependency r6 = r15.dimension
            int r6 = r6.wrapValue
            r12 = r23
            int r23 = java.lang.Math.min(r12, r6)
            r12 = r23
            goto L_0x01c2
        L_0x01c0:
            r12 = r23
        L_0x01c2:
            int r6 = java.lang.Math.max(r4, r12)
            if (r11 <= 0) goto L_0x01cc
            int r6 = java.lang.Math.min(r11, r6)
        L_0x01cc:
            if (r6 == r1) goto L_0x01d1
            int r13 = r13 + 1
            r1 = r6
        L_0x01d1:
            r23 = r2
            goto L_0x0201
        L_0x01d4:
            r22 = r4
            r24 = r6
            r25 = r12
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r15.widget
            int r4 = r4.mMatchConstraintMaxHeight
            android.support.constraint.solver.widgets.ConstraintWidget r6 = r15.widget
            int r6 = r6.mMatchConstraintMinHeight
            r11 = r1
            int r12 = r15.matchConstraintsType
            r23 = r2
            r2 = 1
            if (r12 != r2) goto L_0x01f2
            android.support.constraint.solver.widgets.analyzer.DimensionDependency r2 = r15.dimension
            int r2 = r2.wrapValue
            int r11 = java.lang.Math.min(r11, r2)
        L_0x01f2:
            int r2 = java.lang.Math.max(r6, r11)
            if (r4 <= 0) goto L_0x01fc
            int r2 = java.lang.Math.min(r4, r2)
        L_0x01fc:
            if (r2 == r1) goto L_0x0201
            int r13 = r13 + 1
            r1 = r2
        L_0x0201:
            android.support.constraint.solver.widgets.analyzer.DimensionDependency r2 = r15.dimension
            r2.resolve(r1)
            goto L_0x020f
        L_0x0207:
            r23 = r2
            r22 = r4
            r24 = r6
            r25 = r12
        L_0x020f:
            int r14 = r14 + 1
            r1 = r21
            r4 = r22
            r2 = r23
            r6 = r24
            r12 = r25
            r11 = 1056964608(0x3f000000, float:0.5)
            goto L_0x0150
        L_0x021f:
            r21 = r1
            r23 = r2
            r22 = r4
            r24 = r6
            r25 = r12
            if (r13 <= 0) goto L_0x0262
            int r5 = r5 - r13
            r1 = 0
            r2 = 0
        L_0x022e:
            if (r2 >= r8) goto L_0x0260
            java.util.ArrayList<android.support.constraint.solver.widgets.analyzer.WidgetRun> r4 = r0.widgets
            java.lang.Object r4 = r4.get(r2)
            android.support.constraint.solver.widgets.analyzer.WidgetRun r4 = (android.support.constraint.solver.widgets.analyzer.WidgetRun) r4
            android.support.constraint.solver.widgets.ConstraintWidget r6 = r4.widget
            int r6 = r6.getVisibility()
            r11 = 8
            if (r6 != r11) goto L_0x0243
            goto L_0x025d
        L_0x0243:
            if (r2 <= 0) goto L_0x024c
            if (r2 < r9) goto L_0x024c
            android.support.constraint.solver.widgets.analyzer.DependencyNode r6 = r4.start
            int r6 = r6.margin
            int r1 = r1 + r6
        L_0x024c:
            android.support.constraint.solver.widgets.analyzer.DimensionDependency r6 = r4.dimension
            int r6 = r6.value
            int r1 = r1 + r6
            int r6 = r8 + -1
            if (r2 >= r6) goto L_0x025d
            if (r2 >= r10) goto L_0x025d
            android.support.constraint.solver.widgets.analyzer.DependencyNode r6 = r4.end
            int r6 = r6.margin
            int r6 = -r6
            int r1 = r1 + r6
        L_0x025d:
            int r2 = r2 + 1
            goto L_0x022e
        L_0x0260:
            r4 = r1
            goto L_0x0264
        L_0x0262:
            r4 = r22
        L_0x0264:
            int r1 = r0.chainStyle
            r2 = 2
            if (r1 != r2) goto L_0x026e
            if (r13 != 0) goto L_0x026e
            r1 = 0
            r0.chainStyle = r1
        L_0x026e:
            r12 = r25
            goto L_0x0279
        L_0x0271:
            r21 = r1
            r23 = r2
            r22 = r4
            r24 = r6
        L_0x0279:
            if (r4 <= r3) goto L_0x027f
            r1 = 2
            r0.chainStyle = r1
            goto L_0x0280
        L_0x027f:
            r1 = 2
        L_0x0280:
            if (r7 <= 0) goto L_0x0288
            if (r5 != 0) goto L_0x0288
            if (r9 != r10) goto L_0x0288
            r0.chainStyle = r1
        L_0x0288:
            int r1 = r0.chainStyle
            r2 = 1
            if (r1 != r2) goto L_0x033d
            r1 = 0
            if (r7 <= r2) goto L_0x0297
            int r6 = r3 - r4
            int r11 = r7 + -1
            int r1 = r6 / r11
            goto L_0x029e
        L_0x0297:
            if (r7 != r2) goto L_0x029e
            int r2 = r3 - r4
            r6 = 2
            int r1 = r2 / 2
        L_0x029e:
            if (r5 <= 0) goto L_0x02a1
            r1 = 0
        L_0x02a1:
            r2 = 0
            r6 = r2
            r2 = r21
        L_0x02a5:
            if (r6 >= r8) goto L_0x0338
            r11 = r6
            if (r23 == 0) goto L_0x02ae
            int r13 = r6 + 1
            int r11 = r8 - r13
        L_0x02ae:
            java.util.ArrayList<android.support.constraint.solver.widgets.analyzer.WidgetRun> r13 = r0.widgets
            java.lang.Object r13 = r13.get(r11)
            android.support.constraint.solver.widgets.analyzer.WidgetRun r13 = (android.support.constraint.solver.widgets.analyzer.WidgetRun) r13
            android.support.constraint.solver.widgets.ConstraintWidget r14 = r13.widget
            int r14 = r14.getVisibility()
            r15 = 8
            if (r14 != r15) goto L_0x02ce
            android.support.constraint.solver.widgets.analyzer.DependencyNode r14 = r13.start
            r14.resolve(r2)
            android.support.constraint.solver.widgets.analyzer.DependencyNode r14 = r13.end
            r14.resolve(r2)
            r16 = r1
            goto L_0x0332
        L_0x02ce:
            if (r6 <= 0) goto L_0x02d5
            if (r23 == 0) goto L_0x02d4
            int r2 = r2 - r1
            goto L_0x02d5
        L_0x02d4:
            int r2 = r2 + r1
        L_0x02d5:
            if (r6 <= 0) goto L_0x02e6
            if (r6 < r9) goto L_0x02e6
            if (r23 == 0) goto L_0x02e1
            android.support.constraint.solver.widgets.analyzer.DependencyNode r14 = r13.start
            int r14 = r14.margin
            int r2 = r2 - r14
            goto L_0x02e6
        L_0x02e1:
            android.support.constraint.solver.widgets.analyzer.DependencyNode r14 = r13.start
            int r14 = r14.margin
            int r2 = r2 + r14
        L_0x02e6:
            if (r23 == 0) goto L_0x02ee
            android.support.constraint.solver.widgets.analyzer.DependencyNode r14 = r13.end
            r14.resolve(r2)
            goto L_0x02f3
        L_0x02ee:
            android.support.constraint.solver.widgets.analyzer.DependencyNode r14 = r13.start
            r14.resolve(r2)
        L_0x02f3:
            android.support.constraint.solver.widgets.analyzer.DimensionDependency r14 = r13.dimension
            int r14 = r14.value
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r15 = r13.dimensionBehavior
            r16 = r1
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r15 != r1) goto L_0x0308
            int r1 = r13.matchConstraintsType
            r15 = 1
            if (r1 != r15) goto L_0x0308
            android.support.constraint.solver.widgets.analyzer.DimensionDependency r1 = r13.dimension
            int r14 = r1.wrapValue
        L_0x0308:
            if (r23 == 0) goto L_0x030c
            int r2 = r2 - r14
            goto L_0x030d
        L_0x030c:
            int r2 = r2 + r14
        L_0x030d:
            if (r23 == 0) goto L_0x0315
            android.support.constraint.solver.widgets.analyzer.DependencyNode r1 = r13.start
            r1.resolve(r2)
            goto L_0x031a
        L_0x0315:
            android.support.constraint.solver.widgets.analyzer.DependencyNode r1 = r13.end
            r1.resolve(r2)
        L_0x031a:
            r1 = 1
            r13.resolved = r1
            int r1 = r8 + -1
            if (r6 >= r1) goto L_0x0332
            if (r6 >= r10) goto L_0x0332
            if (r23 == 0) goto L_0x032c
            android.support.constraint.solver.widgets.analyzer.DependencyNode r1 = r13.end
            int r1 = r1.margin
            int r1 = -r1
            int r2 = r2 - r1
            goto L_0x0332
        L_0x032c:
            android.support.constraint.solver.widgets.analyzer.DependencyNode r1 = r13.end
            int r1 = r1.margin
            int r1 = -r1
            int r2 = r2 + r1
        L_0x0332:
            int r6 = r6 + 1
            r1 = r16
            goto L_0x02a5
        L_0x0338:
            r16 = r1
            r1 = r2
            goto L_0x04a4
        L_0x033d:
            if (r1 != 0) goto L_0x03e1
            int r1 = r3 - r4
            int r2 = r7 + 1
            int r1 = r1 / r2
            if (r5 <= 0) goto L_0x0347
            r1 = 0
        L_0x0347:
            r2 = 0
            r6 = r2
            r2 = r21
        L_0x034b:
            if (r6 >= r8) goto L_0x03dc
            r11 = r6
            if (r23 == 0) goto L_0x0354
            int r13 = r6 + 1
            int r11 = r8 - r13
        L_0x0354:
            java.util.ArrayList<android.support.constraint.solver.widgets.analyzer.WidgetRun> r13 = r0.widgets
            java.lang.Object r13 = r13.get(r11)
            android.support.constraint.solver.widgets.analyzer.WidgetRun r13 = (android.support.constraint.solver.widgets.analyzer.WidgetRun) r13
            android.support.constraint.solver.widgets.ConstraintWidget r14 = r13.widget
            int r14 = r14.getVisibility()
            r15 = 8
            if (r14 != r15) goto L_0x0373
            android.support.constraint.solver.widgets.analyzer.DependencyNode r14 = r13.start
            r14.resolve(r2)
            android.support.constraint.solver.widgets.analyzer.DependencyNode r14 = r13.end
            r14.resolve(r2)
            r16 = r1
            goto L_0x03d6
        L_0x0373:
            if (r23 == 0) goto L_0x0377
            int r2 = r2 - r1
            goto L_0x0378
        L_0x0377:
            int r2 = r2 + r1
        L_0x0378:
            if (r6 <= 0) goto L_0x0389
            if (r6 < r9) goto L_0x0389
            if (r23 == 0) goto L_0x0384
            android.support.constraint.solver.widgets.analyzer.DependencyNode r14 = r13.start
            int r14 = r14.margin
            int r2 = r2 - r14
            goto L_0x0389
        L_0x0384:
            android.support.constraint.solver.widgets.analyzer.DependencyNode r14 = r13.start
            int r14 = r14.margin
            int r2 = r2 + r14
        L_0x0389:
            if (r23 == 0) goto L_0x0391
            android.support.constraint.solver.widgets.analyzer.DependencyNode r14 = r13.end
            r14.resolve(r2)
            goto L_0x0396
        L_0x0391:
            android.support.constraint.solver.widgets.analyzer.DependencyNode r14 = r13.start
            r14.resolve(r2)
        L_0x0396:
            android.support.constraint.solver.widgets.analyzer.DimensionDependency r14 = r13.dimension
            int r14 = r14.value
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r15 = r13.dimensionBehavior
            r16 = r1
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r15 != r1) goto L_0x03af
            int r1 = r13.matchConstraintsType
            r15 = 1
            if (r1 != r15) goto L_0x03af
            android.support.constraint.solver.widgets.analyzer.DimensionDependency r1 = r13.dimension
            int r1 = r1.wrapValue
            int r14 = java.lang.Math.min(r14, r1)
        L_0x03af:
            if (r23 == 0) goto L_0x03b3
            int r2 = r2 - r14
            goto L_0x03b4
        L_0x03b3:
            int r2 = r2 + r14
        L_0x03b4:
            if (r23 == 0) goto L_0x03bc
            android.support.constraint.solver.widgets.analyzer.DependencyNode r1 = r13.start
            r1.resolve(r2)
            goto L_0x03c1
        L_0x03bc:
            android.support.constraint.solver.widgets.analyzer.DependencyNode r1 = r13.end
            r1.resolve(r2)
        L_0x03c1:
            int r1 = r8 + -1
            if (r6 >= r1) goto L_0x03d6
            if (r6 >= r10) goto L_0x03d6
            if (r23 == 0) goto L_0x03d0
            android.support.constraint.solver.widgets.analyzer.DependencyNode r1 = r13.end
            int r1 = r1.margin
            int r1 = -r1
            int r2 = r2 - r1
            goto L_0x03d6
        L_0x03d0:
            android.support.constraint.solver.widgets.analyzer.DependencyNode r1 = r13.end
            int r1 = r1.margin
            int r1 = -r1
            int r2 = r2 + r1
        L_0x03d6:
            int r6 = r6 + 1
            r1 = r16
            goto L_0x034b
        L_0x03dc:
            r16 = r1
            r1 = r2
            goto L_0x04a4
        L_0x03e1:
            r2 = 2
            if (r1 != r2) goto L_0x04a2
            int r1 = r0.orientation
            if (r1 != 0) goto L_0x03ef
            android.support.constraint.solver.widgets.ConstraintWidget r1 = r0.widget
            float r1 = r1.getHorizontalBiasPercent()
            goto L_0x03f5
        L_0x03ef:
            android.support.constraint.solver.widgets.ConstraintWidget r1 = r0.widget
            float r1 = r1.getVerticalBiasPercent()
        L_0x03f5:
            if (r23 == 0) goto L_0x03fc
            r2 = 1065353216(0x3f800000, float:1.0)
            float r1 = r2 - r1
        L_0x03fc:
            int r2 = r3 - r4
            float r2 = (float) r2
            float r2 = r2 * r1
            r6 = 1056964608(0x3f000000, float:0.5)
            float r2 = r2 + r6
            int r2 = (int) r2
            if (r2 < 0) goto L_0x0408
            if (r5 <= 0) goto L_0x0409
        L_0x0408:
            r2 = 0
        L_0x0409:
            if (r23 == 0) goto L_0x040e
            int r6 = r21 - r2
            goto L_0x0410
        L_0x040e:
            int r6 = r21 + r2
        L_0x0410:
            r11 = 0
        L_0x0411:
            if (r11 >= r8) goto L_0x049e
            r13 = r11
            if (r23 == 0) goto L_0x041a
            int r14 = r11 + 1
            int r13 = r8 - r14
        L_0x041a:
            java.util.ArrayList<android.support.constraint.solver.widgets.analyzer.WidgetRun> r14 = r0.widgets
            java.lang.Object r14 = r14.get(r13)
            android.support.constraint.solver.widgets.analyzer.WidgetRun r14 = (android.support.constraint.solver.widgets.analyzer.WidgetRun) r14
            android.support.constraint.solver.widgets.ConstraintWidget r15 = r14.widget
            int r15 = r15.getVisibility()
            r0 = 8
            if (r15 != r0) goto L_0x043a
            android.support.constraint.solver.widgets.analyzer.DependencyNode r15 = r14.start
            r15.resolve(r6)
            android.support.constraint.solver.widgets.analyzer.DependencyNode r15 = r14.end
            r15.resolve(r6)
            r16 = r1
            r1 = 1
            goto L_0x0496
        L_0x043a:
            if (r11 <= 0) goto L_0x044b
            if (r11 < r9) goto L_0x044b
            if (r23 == 0) goto L_0x0446
            android.support.constraint.solver.widgets.analyzer.DependencyNode r15 = r14.start
            int r15 = r15.margin
            int r6 = r6 - r15
            goto L_0x044b
        L_0x0446:
            android.support.constraint.solver.widgets.analyzer.DependencyNode r15 = r14.start
            int r15 = r15.margin
            int r6 = r6 + r15
        L_0x044b:
            if (r23 == 0) goto L_0x0453
            android.support.constraint.solver.widgets.analyzer.DependencyNode r15 = r14.end
            r15.resolve(r6)
            goto L_0x0458
        L_0x0453:
            android.support.constraint.solver.widgets.analyzer.DependencyNode r15 = r14.start
            r15.resolve(r6)
        L_0x0458:
            android.support.constraint.solver.widgets.analyzer.DimensionDependency r15 = r14.dimension
            int r15 = r15.value
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r0 = r14.dimensionBehavior
            r16 = r1
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r0 != r1) goto L_0x046e
            int r0 = r14.matchConstraintsType
            r1 = 1
            if (r0 != r1) goto L_0x046f
            android.support.constraint.solver.widgets.analyzer.DimensionDependency r0 = r14.dimension
            int r15 = r0.wrapValue
            goto L_0x046f
        L_0x046e:
            r1 = 1
        L_0x046f:
            if (r23 == 0) goto L_0x0473
            int r6 = r6 - r15
            goto L_0x0474
        L_0x0473:
            int r6 = r6 + r15
        L_0x0474:
            if (r23 == 0) goto L_0x047c
            android.support.constraint.solver.widgets.analyzer.DependencyNode r0 = r14.start
            r0.resolve(r6)
            goto L_0x0481
        L_0x047c:
            android.support.constraint.solver.widgets.analyzer.DependencyNode r0 = r14.end
            r0.resolve(r6)
        L_0x0481:
            int r0 = r8 + -1
            if (r11 >= r0) goto L_0x0496
            if (r11 >= r10) goto L_0x0496
            if (r23 == 0) goto L_0x0490
            android.support.constraint.solver.widgets.analyzer.DependencyNode r0 = r14.end
            int r0 = r0.margin
            int r0 = -r0
            int r6 = r6 - r0
            goto L_0x0496
        L_0x0490:
            android.support.constraint.solver.widgets.analyzer.DependencyNode r0 = r14.end
            int r0 = r0.margin
            int r0 = -r0
            int r6 = r6 + r0
        L_0x0496:
            int r11 = r11 + 1
            r0 = r26
            r1 = r16
            goto L_0x0411
        L_0x049e:
            r16 = r1
            r1 = r6
            goto L_0x04a4
        L_0x04a2:
            r1 = r21
        L_0x04a4:
            return
        L_0x04a5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.analyzer.ChainRun.update(android.support.constraint.solver.widgets.analyzer.Dependency):void");
    }

    public void applyToWidget() {
        for (int i = 0; i < this.widgets.size(); i++) {
            this.widgets.get(i).applyToWidget();
        }
    }

    private ConstraintWidget getFirstVisibleWidget() {
        for (int i = 0; i < this.widgets.size(); i++) {
            WidgetRun run = this.widgets.get(i);
            if (run.widget.getVisibility() != 8) {
                return run.widget;
            }
        }
        return null;
    }

    private ConstraintWidget getLastVisibleWidget() {
        for (int i = this.widgets.size() - 1; i >= 0; i--) {
            WidgetRun run = this.widgets.get(i);
            if (run.widget.getVisibility() != 8) {
                return run.widget;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void apply() {
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            it.next().apply();
        }
        int count = this.widgets.size();
        if (count >= 1) {
            ConstraintWidget firstWidget = this.widgets.get(0).widget;
            ConstraintWidget lastWidget = this.widgets.get(count - 1).widget;
            if (this.orientation == 0) {
                ConstraintAnchor startAnchor = firstWidget.mLeft;
                ConstraintAnchor endAnchor = lastWidget.mRight;
                DependencyNode startTarget = getTarget(startAnchor, 0);
                int startMargin = startAnchor.getMargin();
                ConstraintWidget firstVisibleWidget = getFirstVisibleWidget();
                if (firstVisibleWidget != null) {
                    startMargin = firstVisibleWidget.mLeft.getMargin();
                }
                if (startTarget != null) {
                    addTarget(this.start, startTarget, startMargin);
                }
                DependencyNode endTarget = getTarget(endAnchor, 0);
                int endMargin = endAnchor.getMargin();
                ConstraintWidget lastVisibleWidget = getLastVisibleWidget();
                if (lastVisibleWidget != null) {
                    endMargin = lastVisibleWidget.mRight.getMargin();
                }
                if (endTarget != null) {
                    addTarget(this.end, endTarget, -endMargin);
                }
            } else {
                ConstraintAnchor startAnchor2 = firstWidget.mTop;
                ConstraintAnchor endAnchor2 = lastWidget.mBottom;
                DependencyNode startTarget2 = getTarget(startAnchor2, 1);
                int startMargin2 = startAnchor2.getMargin();
                ConstraintWidget firstVisibleWidget2 = getFirstVisibleWidget();
                if (firstVisibleWidget2 != null) {
                    startMargin2 = firstVisibleWidget2.mTop.getMargin();
                }
                if (startTarget2 != null) {
                    addTarget(this.start, startTarget2, startMargin2);
                }
                DependencyNode endTarget2 = getTarget(endAnchor2, 1);
                int endMargin2 = endAnchor2.getMargin();
                ConstraintWidget lastVisibleWidget2 = getLastVisibleWidget();
                if (lastVisibleWidget2 != null) {
                    endMargin2 = lastVisibleWidget2.mBottom.getMargin();
                }
                if (endTarget2 != null) {
                    addTarget(this.end, endTarget2, -endMargin2);
                }
            }
            this.start.updateDelegate = this;
            this.end.updateDelegate = this;
        }
    }
}
