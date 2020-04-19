package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.Metrics;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintWidget;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConstraintWidgetContainer extends WidgetContainer {
    private static final boolean DEBUG = false;
    static final boolean DEBUG_GRAPH = false;
    private static final boolean DEBUG_LAYOUT = false;
    private static final int MAX_ITERATIONS = 8;
    private static final boolean USE_SNAPSHOT = true;
    int mDebugSolverPassCount = 0;
    public boolean mGroupsWrapOptimized = false;
    private boolean mHeightMeasuredTooSmall = false;
    ChainHead[] mHorizontalChainsArray = new ChainHead[4];
    int mHorizontalChainsSize = 0;
    public boolean mHorizontalWrapOptimized = false;
    private boolean mIsRtl = false;
    private int mOptimizationLevel = 7;
    int mPaddingBottom;
    int mPaddingLeft;
    int mPaddingRight;
    int mPaddingTop;
    public boolean mSkipSolver = false;
    private Snapshot mSnapshot;
    protected LinearSystem mSystem = new LinearSystem();
    ChainHead[] mVerticalChainsArray = new ChainHead[4];
    int mVerticalChainsSize = 0;
    public boolean mVerticalWrapOptimized = false;
    public List<ConstraintWidgetGroup> mWidgetGroups = new ArrayList();
    private boolean mWidthMeasuredTooSmall = false;
    public int mWrapFixedHeight = 0;
    public int mWrapFixedWidth = 0;

    public void fillMetrics(Metrics metrics) {
        this.mSystem.fillMetrics(metrics);
    }

    public ConstraintWidgetContainer() {
    }

    public ConstraintWidgetContainer(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public ConstraintWidgetContainer(int width, int height) {
        super(width, height);
    }

    public void setOptimizationLevel(int value) {
        this.mOptimizationLevel = value;
    }

    public int getOptimizationLevel() {
        return this.mOptimizationLevel;
    }

    public boolean optimizeFor(int feature) {
        if ((this.mOptimizationLevel & feature) == feature) {
            return USE_SNAPSHOT;
        }
        return false;
    }

    public String getType() {
        return "ConstraintLayout";
    }

    public void reset() {
        this.mSystem.reset();
        this.mPaddingLeft = 0;
        this.mPaddingRight = 0;
        this.mPaddingTop = 0;
        this.mPaddingBottom = 0;
        this.mWidgetGroups.clear();
        this.mSkipSolver = false;
        super.reset();
    }

    public boolean isWidthMeasuredTooSmall() {
        return this.mWidthMeasuredTooSmall;
    }

    public boolean isHeightMeasuredTooSmall() {
        return this.mHeightMeasuredTooSmall;
    }

    public boolean addChildrenToSolver(LinearSystem system) {
        addToSolver(system);
        int count = this.mChildren.size();
        for (int i = 0; i < count; i++) {
            ConstraintWidget widget = (ConstraintWidget) this.mChildren.get(i);
            if (widget instanceof ConstraintWidgetContainer) {
                ConstraintWidget.DimensionBehaviour horizontalBehaviour = widget.mListDimensionBehaviors[0];
                ConstraintWidget.DimensionBehaviour verticalBehaviour = widget.mListDimensionBehaviors[1];
                if (horizontalBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    widget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                }
                if (verticalBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    widget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                }
                widget.addToSolver(system);
                if (horizontalBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    widget.setHorizontalDimensionBehaviour(horizontalBehaviour);
                }
                if (verticalBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    widget.setVerticalDimensionBehaviour(verticalBehaviour);
                }
            } else {
                Optimizer.checkMatchParent(this, system, widget);
                widget.addToSolver(system);
            }
        }
        if (this.mHorizontalChainsSize > 0) {
            Chain.applyChainConstraints(this, system, 0);
        }
        if (this.mVerticalChainsSize > 0) {
            Chain.applyChainConstraints(this, system, 1);
        }
        return USE_SNAPSHOT;
    }

    public void updateChildrenFromSolver(LinearSystem system, boolean[] flags) {
        flags[2] = false;
        updateFromSolver(system);
        int count = this.mChildren.size();
        for (int i = 0; i < count; i++) {
            ConstraintWidget widget = (ConstraintWidget) this.mChildren.get(i);
            widget.updateFromSolver(system);
            if (widget.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widget.getWidth() < widget.getWrapWidth()) {
                flags[2] = USE_SNAPSHOT;
            }
            if (widget.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widget.getHeight() < widget.getWrapHeight()) {
                flags[2] = USE_SNAPSHOT;
            }
        }
    }

    public void setPadding(int left, int top, int right, int bottom) {
        this.mPaddingLeft = left;
        this.mPaddingTop = top;
        this.mPaddingRight = right;
        this.mPaddingBottom = bottom;
    }

    public void setRtl(boolean isRtl) {
        this.mIsRtl = isRtl;
    }

    public boolean isRtl() {
        return this.mIsRtl;
    }

    public void analyze(int optimizationLevel) {
        super.analyze(optimizationLevel);
        int count = this.mChildren.size();
        for (int i = 0; i < count; i++) {
            ((ConstraintWidget) this.mChildren.get(i)).analyze(optimizationLevel);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:113:0x0282  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x029a  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x02b7  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x02c6  */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x0311  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01a3  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01ad  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x020b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void layout() {
        /*
            r27 = this;
            r1 = r27
            int r2 = r1.mX
            int r3 = r1.mY
            int r0 = r27.getWidth()
            r4 = 0
            int r5 = java.lang.Math.max(r4, r0)
            int r0 = r27.getHeight()
            int r6 = java.lang.Math.max(r4, r0)
            r1.mWidthMeasuredTooSmall = r4
            r1.mHeightMeasuredTooSmall = r4
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r1.mParent
            if (r0 == 0) goto L_0x0046
            android.support.constraint.solver.widgets.Snapshot r0 = r1.mSnapshot
            if (r0 != 0) goto L_0x002a
            android.support.constraint.solver.widgets.Snapshot r0 = new android.support.constraint.solver.widgets.Snapshot
            r0.<init>(r1)
            r1.mSnapshot = r0
        L_0x002a:
            android.support.constraint.solver.widgets.Snapshot r0 = r1.mSnapshot
            r0.updateFrom(r1)
            int r0 = r1.mPaddingLeft
            r1.setX(r0)
            int r0 = r1.mPaddingTop
            r1.setY(r0)
            r27.resetAnchors()
            android.support.constraint.solver.LinearSystem r0 = r1.mSystem
            android.support.constraint.solver.Cache r0 = r0.getCache()
            r1.resetSolverVariables(r0)
            goto L_0x004a
        L_0x0046:
            r1.mX = r4
            r1.mY = r4
        L_0x004a:
            int r0 = r1.mOptimizationLevel
            r7 = 32
            r8 = 8
            r9 = 1
            if (r0 == 0) goto L_0x006a
            boolean r0 = r1.optimizeFor(r8)
            if (r0 != 0) goto L_0x005c
            r27.optimizeReset()
        L_0x005c:
            boolean r0 = r1.optimizeFor(r7)
            if (r0 != 0) goto L_0x0065
            r27.optimize()
        L_0x0065:
            android.support.constraint.solver.LinearSystem r0 = r1.mSystem
            r0.graphOptimizer = r9
            goto L_0x006e
        L_0x006a:
            android.support.constraint.solver.LinearSystem r0 = r1.mSystem
            r0.graphOptimizer = r4
        L_0x006e:
            r0 = 0
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r10 = r1.mListDimensionBehaviors
            r10 = r10[r9]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r11 = r1.mListDimensionBehaviors
            r11 = r11[r4]
            r27.resetChains()
            java.util.List<android.support.constraint.solver.widgets.ConstraintWidgetGroup> r12 = r1.mWidgetGroups
            int r12 = r12.size()
            if (r12 != 0) goto L_0x0093
            java.util.List<android.support.constraint.solver.widgets.ConstraintWidgetGroup> r12 = r1.mWidgetGroups
            r12.clear()
            java.util.List<android.support.constraint.solver.widgets.ConstraintWidgetGroup> r12 = r1.mWidgetGroups
            android.support.constraint.solver.widgets.ConstraintWidgetGroup r13 = new android.support.constraint.solver.widgets.ConstraintWidgetGroup
            java.util.ArrayList r14 = r1.mChildren
            r13.<init>(r14)
            r12.add(r4, r13)
        L_0x0093:
            r12 = 0
            java.util.List<android.support.constraint.solver.widgets.ConstraintWidgetGroup> r13 = r1.mWidgetGroups
            int r13 = r13.size()
            java.util.ArrayList r14 = r1.mChildren
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r15 = r27.getHorizontalDimensionBehaviour()
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r8 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r15 == r8) goto L_0x00af
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r8 = r27.getVerticalDimensionBehaviour()
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r15 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r8 != r15) goto L_0x00ad
            goto L_0x00af
        L_0x00ad:
            r8 = r4
            goto L_0x00b0
        L_0x00af:
            r8 = r9
        L_0x00b0:
            r15 = r12
            r12 = r0
            r0 = r4
        L_0x00b3:
            r17 = r0
            r9 = r17
            if (r9 >= r13) goto L_0x033a
            boolean r0 = r1.mSkipSolver
            if (r0 != 0) goto L_0x033a
            java.util.List<android.support.constraint.solver.widgets.ConstraintWidgetGroup> r0 = r1.mWidgetGroups
            java.lang.Object r0 = r0.get(r9)
            android.support.constraint.solver.widgets.ConstraintWidgetGroup r0 = (android.support.constraint.solver.widgets.ConstraintWidgetGroup) r0
            boolean r0 = r0.mSkipSolver
            if (r0 == 0) goto L_0x00ce
            r22 = r13
            goto L_0x0330
        L_0x00ce:
            boolean r0 = r1.optimizeFor(r7)
            if (r0 == 0) goto L_0x0103
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r0 = r27.getHorizontalDimensionBehaviour()
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r0 != r7) goto L_0x00f5
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r0 = r27.getVerticalDimensionBehaviour()
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r0 != r7) goto L_0x00f5
            java.util.List<android.support.constraint.solver.widgets.ConstraintWidgetGroup> r0 = r1.mWidgetGroups
            java.lang.Object r0 = r0.get(r9)
            android.support.constraint.solver.widgets.ConstraintWidgetGroup r0 = (android.support.constraint.solver.widgets.ConstraintWidgetGroup) r0
            java.util.List r0 = r0.getWidgetsToSolve()
            java.util.ArrayList r0 = (java.util.ArrayList) r0
            r1.mChildren = r0
            goto L_0x0103
        L_0x00f5:
            java.util.List<android.support.constraint.solver.widgets.ConstraintWidgetGroup> r0 = r1.mWidgetGroups
            java.lang.Object r0 = r0.get(r9)
            android.support.constraint.solver.widgets.ConstraintWidgetGroup r0 = (android.support.constraint.solver.widgets.ConstraintWidgetGroup) r0
            java.util.List<android.support.constraint.solver.widgets.ConstraintWidget> r0 = r0.mConstrainedGroup
            java.util.ArrayList r0 = (java.util.ArrayList) r0
            r1.mChildren = r0
        L_0x0103:
            r27.resetChains()
            java.util.ArrayList r0 = r1.mChildren
            int r7 = r0.size()
            r0 = 0
            r15 = r4
        L_0x010e:
            if (r15 >= r7) goto L_0x012a
            java.util.ArrayList r4 = r1.mChildren
            java.lang.Object r4 = r4.get(r15)
            android.support.constraint.solver.widgets.ConstraintWidget r4 = (android.support.constraint.solver.widgets.ConstraintWidget) r4
            r18 = r0
            boolean r0 = r4 instanceof android.support.constraint.solver.widgets.WidgetContainer
            if (r0 == 0) goto L_0x0124
            r0 = r4
            android.support.constraint.solver.widgets.WidgetContainer r0 = (android.support.constraint.solver.widgets.WidgetContainer) r0
            r0.layout()
        L_0x0124:
            int r15 = r15 + 1
            r0 = r18
            r4 = 0
            goto L_0x010e
        L_0x012a:
            r18 = r0
            r0 = 1
        L_0x012d:
            r4 = r0
            if (r4 == 0) goto L_0x031b
            int r15 = r18 + 1
            android.support.constraint.solver.LinearSystem r0 = r1.mSystem     // Catch:{ Exception -> 0x017d }
            r0.reset()     // Catch:{ Exception -> 0x017d }
            r27.resetChains()     // Catch:{ Exception -> 0x017d }
            android.support.constraint.solver.LinearSystem r0 = r1.mSystem     // Catch:{ Exception -> 0x017d }
            r1.createObjectVariables(r0)     // Catch:{ Exception -> 0x017d }
            r0 = 0
        L_0x0140:
            if (r0 >= r7) goto L_0x0160
            r19 = r4
            java.util.ArrayList r4 = r1.mChildren     // Catch:{ Exception -> 0x015a }
            java.lang.Object r4 = r4.get(r0)     // Catch:{ Exception -> 0x015a }
            android.support.constraint.solver.widgets.ConstraintWidget r4 = (android.support.constraint.solver.widgets.ConstraintWidget) r4     // Catch:{ Exception -> 0x015a }
            r20 = r12
            android.support.constraint.solver.LinearSystem r12 = r1.mSystem     // Catch:{ Exception -> 0x0179 }
            r4.createObjectVariables(r12)     // Catch:{ Exception -> 0x0179 }
            int r0 = r0 + 1
            r4 = r19
            r12 = r20
            goto L_0x0140
        L_0x015a:
            r0 = move-exception
            r20 = r12
            r4 = r19
            goto L_0x0182
        L_0x0160:
            r19 = r4
            r20 = r12
            android.support.constraint.solver.LinearSystem r0 = r1.mSystem     // Catch:{ Exception -> 0x0179 }
            boolean r0 = r1.addChildrenToSolver(r0)     // Catch:{ Exception -> 0x0179 }
            r4 = r0
            if (r4 == 0) goto L_0x0175
            android.support.constraint.solver.LinearSystem r0 = r1.mSystem     // Catch:{ Exception -> 0x0173 }
            r0.minimize()     // Catch:{ Exception -> 0x0173 }
            goto L_0x0175
        L_0x0173:
            r0 = move-exception
            goto L_0x0182
        L_0x0175:
            r22 = r13
            goto L_0x01a1
        L_0x0179:
            r0 = move-exception
            r4 = r19
            goto L_0x0182
        L_0x017d:
            r0 = move-exception
            r19 = r4
            r20 = r12
        L_0x0182:
            r0.printStackTrace()
            java.io.PrintStream r12 = java.lang.System.out
            r21 = r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r22 = r13
            java.lang.String r13 = "EXCEPTION : "
            r4.append(r13)
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            r12.println(r4)
            r4 = r21
        L_0x01a1:
            if (r4 == 0) goto L_0x01ad
            android.support.constraint.solver.LinearSystem r12 = r1.mSystem
            boolean[] r13 = android.support.constraint.solver.widgets.Optimizer.flags
            r1.updateChildrenFromSolver(r12, r13)
            r23 = r4
            goto L_0x01fd
        L_0x01ad:
            android.support.constraint.solver.LinearSystem r12 = r1.mSystem
            r1.updateFromSolver(r12)
            r12 = 0
        L_0x01b3:
            if (r12 >= r7) goto L_0x01fb
            java.util.ArrayList r13 = r1.mChildren
            java.lang.Object r13 = r13.get(r12)
            android.support.constraint.solver.widgets.ConstraintWidget r13 = (android.support.constraint.solver.widgets.ConstraintWidget) r13
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r13.mListDimensionBehaviors
            r17 = 0
            r0 = r0[r17]
            r23 = r4
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r4 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r0 != r4) goto L_0x01db
            int r0 = r13.getWidth()
            int r4 = r13.getWrapWidth()
            if (r0 >= r4) goto L_0x01db
            boolean[] r0 = android.support.constraint.solver.widgets.Optimizer.flags
            r4 = 1
            r17 = 2
            r0[r17] = r4
            goto L_0x01fd
        L_0x01db:
            r4 = 1
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r13.mListDimensionBehaviors
            r0 = r0[r4]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r4 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r0 != r4) goto L_0x01f6
            int r0 = r13.getHeight()
            int r4 = r13.getWrapHeight()
            if (r0 >= r4) goto L_0x01f6
            boolean[] r0 = android.support.constraint.solver.widgets.Optimizer.flags
            r4 = 1
            r17 = 2
            r0[r17] = r4
            goto L_0x01fd
        L_0x01f6:
            int r12 = r12 + 1
            r4 = r23
            goto L_0x01b3
        L_0x01fb:
            r23 = r4
        L_0x01fd:
            r0 = 0
            if (r8 == 0) goto L_0x0282
            r4 = 8
            if (r15 >= r4) goto L_0x0282
            boolean[] r12 = android.support.constraint.solver.widgets.Optimizer.flags
            r13 = 2
            boolean r12 = r12[r13]
            if (r12 == 0) goto L_0x0282
            r12 = 0
            r13 = 0
            r4 = r13
            r13 = r12
            r12 = 0
        L_0x0210:
            if (r12 >= r7) goto L_0x023d
            r24 = r0
            java.util.ArrayList r0 = r1.mChildren
            java.lang.Object r0 = r0.get(r12)
            android.support.constraint.solver.widgets.ConstraintWidget r0 = (android.support.constraint.solver.widgets.ConstraintWidget) r0
            r25 = r7
            int r7 = r0.mX
            int r16 = r0.getWidth()
            int r7 = r7 + r16
            int r13 = java.lang.Math.max(r13, r7)
            int r7 = r0.mY
            int r16 = r0.getHeight()
            int r7 = r7 + r16
            int r4 = java.lang.Math.max(r4, r7)
            int r12 = r12 + 1
            r0 = r24
            r7 = r25
            goto L_0x0210
        L_0x023d:
            r24 = r0
            r25 = r7
            int r0 = r1.mMinWidth
            int r0 = java.lang.Math.max(r0, r13)
            int r7 = r1.mMinHeight
            int r4 = java.lang.Math.max(r7, r4)
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r11 != r7) goto L_0x0264
            int r7 = r27.getWidth()
            if (r7 >= r0) goto L_0x0264
            r1.setWidth(r0)
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r7 = r1.mListDimensionBehaviors
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r12 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r13 = 0
            r7[r13] = r12
            r12 = 1
            r7 = 1
            goto L_0x0268
        L_0x0264:
            r12 = r20
            r7 = r24
        L_0x0268:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r13 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r10 != r13) goto L_0x0280
            int r13 = r27.getHeight()
            if (r13 >= r4) goto L_0x0280
            r1.setHeight(r4)
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r13 = r1.mListDimensionBehaviors
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r16 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r17 = 1
            r13[r17] = r16
            r12 = 1
            r0 = 1
            goto L_0x028a
        L_0x0280:
            r0 = r7
            goto L_0x028a
        L_0x0282:
            r24 = r0
            r25 = r7
            r12 = r20
            r0 = r24
        L_0x028a:
            int r4 = r1.mMinWidth
            int r7 = r27.getWidth()
            int r4 = java.lang.Math.max(r4, r7)
            int r7 = r27.getWidth()
            if (r4 <= r7) goto L_0x02a7
            r1.setWidth(r4)
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r7 = r1.mListDimensionBehaviors
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r13 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r16 = 0
            r7[r16] = r13
            r12 = 1
            r0 = 1
        L_0x02a7:
            int r7 = r1.mMinHeight
            int r13 = r27.getHeight()
            int r7 = java.lang.Math.max(r7, r13)
            int r13 = r27.getHeight()
            if (r7 <= r13) goto L_0x02c4
            r1.setHeight(r7)
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r13 = r1.mListDimensionBehaviors
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r16 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r17 = 1
            r13[r17] = r16
            r12 = 1
            r0 = 1
        L_0x02c4:
            if (r12 != 0) goto L_0x0311
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r13 = r1.mListDimensionBehaviors
            r16 = 0
            r13 = r13[r16]
            r26 = r0
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r0 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r13 != r0) goto L_0x02ec
            if (r5 <= 0) goto L_0x02ec
            int r0 = r27.getWidth()
            if (r0 <= r5) goto L_0x02ec
            r13 = 1
            r1.mWidthMeasuredTooSmall = r13
            r12 = 1
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r13 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r16 = 0
            r0[r16] = r13
            r1.setWidth(r5)
            r0 = 1
            r26 = r0
        L_0x02ec:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            r13 = 1
            r0 = r0[r13]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r13 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r0 != r13) goto L_0x030e
            if (r6 <= 0) goto L_0x030e
            int r0 = r27.getHeight()
            if (r0 <= r6) goto L_0x030e
            r13 = 1
            r1.mHeightMeasuredTooSmall = r13
            r0 = 1
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r12 = r1.mListDimensionBehaviors
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r16 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r12[r13] = r16
            r1.setHeight(r6)
            r4 = 1
            r12 = r0
            r0 = r4
            goto L_0x0313
        L_0x030e:
            r0 = r26
            goto L_0x0313
        L_0x0311:
            r26 = r0
        L_0x0313:
            r18 = r15
            r13 = r22
            r7 = r25
            goto L_0x012d
        L_0x031b:
            r19 = r4
            r25 = r7
            r20 = r12
            r22 = r13
            java.util.List<android.support.constraint.solver.widgets.ConstraintWidgetGroup> r0 = r1.mWidgetGroups
            java.lang.Object r0 = r0.get(r9)
            android.support.constraint.solver.widgets.ConstraintWidgetGroup r0 = (android.support.constraint.solver.widgets.ConstraintWidgetGroup) r0
            r0.updateUnresolvedWidgets()
            r15 = r18
        L_0x0330:
            int r0 = r9 + 1
            r13 = r22
            r4 = 0
            r7 = 32
            r9 = 1
            goto L_0x00b3
        L_0x033a:
            r22 = r13
            r0 = r14
            java.util.ArrayList r0 = (java.util.ArrayList) r0
            r1.mChildren = r0
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r1.mParent
            if (r0 == 0) goto L_0x0371
            int r0 = r1.mMinWidth
            int r4 = r27.getWidth()
            int r0 = java.lang.Math.max(r0, r4)
            int r4 = r1.mMinHeight
            int r7 = r27.getHeight()
            int r4 = java.lang.Math.max(r4, r7)
            android.support.constraint.solver.widgets.Snapshot r7 = r1.mSnapshot
            r7.applyTo(r1)
            int r7 = r1.mPaddingLeft
            int r7 = r7 + r0
            int r9 = r1.mPaddingRight
            int r7 = r7 + r9
            r1.setWidth(r7)
            int r7 = r1.mPaddingTop
            int r7 = r7 + r4
            int r9 = r1.mPaddingBottom
            int r7 = r7 + r9
            r1.setHeight(r7)
            goto L_0x0375
        L_0x0371:
            r1.mX = r2
            r1.mY = r3
        L_0x0375:
            if (r12 == 0) goto L_0x0381
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            r4 = 0
            r0[r4] = r11
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            r4 = 1
            r0[r4] = r10
        L_0x0381:
            android.support.constraint.solver.LinearSystem r0 = r1.mSystem
            android.support.constraint.solver.Cache r0 = r0.getCache()
            r1.resetSolverVariables(r0)
            android.support.constraint.solver.widgets.ConstraintWidgetContainer r0 = r27.getRootConstraintContainer()
            if (r1 != r0) goto L_0x0393
            r27.updateDrawPosition()
        L_0x0393:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidgetContainer.layout():void");
    }

    public void preOptimize() {
        optimizeReset();
        analyze(this.mOptimizationLevel);
    }

    public void solveGraph() {
        ResolutionAnchor leftNode = getAnchor(ConstraintAnchor.Type.LEFT).getResolutionNode();
        ResolutionAnchor topNode = getAnchor(ConstraintAnchor.Type.TOP).getResolutionNode();
        leftNode.resolve((ResolutionAnchor) null, 0.0f);
        topNode.resolve((ResolutionAnchor) null, 0.0f);
    }

    public void resetGraph() {
        ResolutionAnchor leftNode = getAnchor(ConstraintAnchor.Type.LEFT).getResolutionNode();
        ResolutionAnchor topNode = getAnchor(ConstraintAnchor.Type.TOP).getResolutionNode();
        leftNode.invalidateAnchors();
        topNode.invalidateAnchors();
        leftNode.resolve((ResolutionAnchor) null, 0.0f);
        topNode.resolve((ResolutionAnchor) null, 0.0f);
    }

    public void optimizeForDimensions(int width, int height) {
        if (!(this.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || this.mResolutionWidth == null)) {
            this.mResolutionWidth.resolve(width);
        }
        if (this.mListDimensionBehaviors[1] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && this.mResolutionHeight != null) {
            this.mResolutionHeight.resolve(height);
        }
    }

    public void optimizeReset() {
        int count = this.mChildren.size();
        resetResolutionNodes();
        for (int i = 0; i < count; i++) {
            ((ConstraintWidget) this.mChildren.get(i)).resetResolutionNodes();
        }
    }

    public void optimize() {
        if (!optimizeFor(8)) {
            analyze(this.mOptimizationLevel);
        }
        solveGraph();
    }

    public boolean handlesInternalConstraints() {
        return false;
    }

    public ArrayList<Guideline> getVerticalGuidelines() {
        ArrayList<Guideline> guidelines = new ArrayList<>();
        int mChildrenSize = this.mChildren.size();
        for (int i = 0; i < mChildrenSize; i++) {
            ConstraintWidget widget = (ConstraintWidget) this.mChildren.get(i);
            if (widget instanceof Guideline) {
                Guideline guideline = (Guideline) widget;
                if (guideline.getOrientation() == 1) {
                    guidelines.add(guideline);
                }
            }
        }
        return guidelines;
    }

    public ArrayList<Guideline> getHorizontalGuidelines() {
        ArrayList<Guideline> guidelines = new ArrayList<>();
        int mChildrenSize = this.mChildren.size();
        for (int i = 0; i < mChildrenSize; i++) {
            ConstraintWidget widget = (ConstraintWidget) this.mChildren.get(i);
            if (widget instanceof Guideline) {
                Guideline guideline = (Guideline) widget;
                if (guideline.getOrientation() == 0) {
                    guidelines.add(guideline);
                }
            }
        }
        return guidelines;
    }

    public LinearSystem getSystem() {
        return this.mSystem;
    }

    private void resetChains() {
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
    }

    /* access modifiers changed from: package-private */
    public void addChain(ConstraintWidget constraintWidget, int type) {
        ConstraintWidget widget = constraintWidget;
        if (type == 0) {
            addHorizontalChain(widget);
        } else if (type == 1) {
            addVerticalChain(widget);
        }
    }

    private void addHorizontalChain(ConstraintWidget widget) {
        if (this.mHorizontalChainsSize + 1 >= this.mHorizontalChainsArray.length) {
            this.mHorizontalChainsArray = (ChainHead[]) Arrays.copyOf(this.mHorizontalChainsArray, this.mHorizontalChainsArray.length * 2);
        }
        this.mHorizontalChainsArray[this.mHorizontalChainsSize] = new ChainHead(widget, 0, isRtl());
        this.mHorizontalChainsSize++;
    }

    private void addVerticalChain(ConstraintWidget widget) {
        if (this.mVerticalChainsSize + 1 >= this.mVerticalChainsArray.length) {
            this.mVerticalChainsArray = (ChainHead[]) Arrays.copyOf(this.mVerticalChainsArray, this.mVerticalChainsArray.length * 2);
        }
        this.mVerticalChainsArray[this.mVerticalChainsSize] = new ChainHead(widget, 1, isRtl());
        this.mVerticalChainsSize++;
    }

    public List<ConstraintWidgetGroup> getWidgetGroups() {
        return this.mWidgetGroups;
    }
}
