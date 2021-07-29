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

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v18, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v2, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v19, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v20, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v47, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v13, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v14, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v53, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v15, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v54, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v16, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v55, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v18, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r17v3 */
    /* JADX WARNING: type inference failed for: r17v5 */
    /* JADX WARNING: Incorrect type for immutable var: ssa=boolean, code=?, for r17v2, types: [boolean] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x027c  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x0294  */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x02b1  */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x02c0  */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x0317  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x019c  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x01a6  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0206  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void layout() {
        /*
            r24 = this;
            r1 = r24
            int r2 = r1.mX
            int r3 = r1.mY
            int r0 = r24.getWidth()
            r4 = 0
            int r5 = java.lang.Math.max(r4, r0)
            int r0 = r24.getHeight()
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
            r24.resetAnchors()
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
            r24.optimizeReset()
        L_0x005c:
            boolean r0 = r1.optimizeFor(r7)
            if (r0 != 0) goto L_0x0065
            r24.optimize()
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
            r24.resetChains()
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
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r15 = r24.getHorizontalDimensionBehaviour()
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r8 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r15 == r8) goto L_0x00af
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r8 = r24.getVerticalDimensionBehaviour()
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r15 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r8 != r15) goto L_0x00ad
            goto L_0x00af
        L_0x00ad:
            r8 = r4
            goto L_0x00b0
        L_0x00af:
            r8 = r9
        L_0x00b0:
            r15 = 0
        L_0x00b1:
            if (r15 >= r13) goto L_0x0344
            boolean r9 = r1.mSkipSolver
            if (r9 != 0) goto L_0x0344
            java.util.List<android.support.constraint.solver.widgets.ConstraintWidgetGroup> r9 = r1.mWidgetGroups
            java.lang.Object r9 = r9.get(r15)
            android.support.constraint.solver.widgets.ConstraintWidgetGroup r9 = (android.support.constraint.solver.widgets.ConstraintWidgetGroup) r9
            boolean r9 = r9.mSkipSolver
            if (r9 == 0) goto L_0x00c7
            r22 = r13
            goto L_0x033a
        L_0x00c7:
            boolean r9 = r1.optimizeFor(r7)
            if (r9 == 0) goto L_0x00fc
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = r24.getHorizontalDimensionBehaviour()
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r9 != r7) goto L_0x00ee
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = r24.getVerticalDimensionBehaviour()
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r7 != r9) goto L_0x00ee
            java.util.List<android.support.constraint.solver.widgets.ConstraintWidgetGroup> r7 = r1.mWidgetGroups
            java.lang.Object r7 = r7.get(r15)
            android.support.constraint.solver.widgets.ConstraintWidgetGroup r7 = (android.support.constraint.solver.widgets.ConstraintWidgetGroup) r7
            java.util.List r7 = r7.getWidgetsToSolve()
            java.util.ArrayList r7 = (java.util.ArrayList) r7
            r1.mChildren = r7
            goto L_0x00fc
        L_0x00ee:
            java.util.List<android.support.constraint.solver.widgets.ConstraintWidgetGroup> r7 = r1.mWidgetGroups
            java.lang.Object r7 = r7.get(r15)
            android.support.constraint.solver.widgets.ConstraintWidgetGroup r7 = (android.support.constraint.solver.widgets.ConstraintWidgetGroup) r7
            java.util.List<android.support.constraint.solver.widgets.ConstraintWidget> r7 = r7.mConstrainedGroup
            java.util.ArrayList r7 = (java.util.ArrayList) r7
            r1.mChildren = r7
        L_0x00fc:
            r24.resetChains()
            java.util.ArrayList r7 = r1.mChildren
            int r7 = r7.size()
            r9 = 0
            r12 = 0
        L_0x0107:
            if (r12 >= r7) goto L_0x0123
            java.util.ArrayList r4 = r1.mChildren
            java.lang.Object r4 = r4.get(r12)
            android.support.constraint.solver.widgets.ConstraintWidget r4 = (android.support.constraint.solver.widgets.ConstraintWidget) r4
            r19 = r9
            boolean r9 = r4 instanceof android.support.constraint.solver.widgets.WidgetContainer
            if (r9 == 0) goto L_0x011d
            r9 = r4
            android.support.constraint.solver.widgets.WidgetContainer r9 = (android.support.constraint.solver.widgets.WidgetContainer) r9
            r9.layout()
        L_0x011d:
            int r12 = r12 + 1
            r9 = r19
            r4 = 0
            goto L_0x0107
        L_0x0123:
            r19 = r9
            r4 = 1
            r9 = r4
            r4 = r0
        L_0x0128:
            if (r9 == 0) goto L_0x0323
            int r12 = r19 + 1
            android.support.constraint.solver.LinearSystem r0 = r1.mSystem     // Catch:{ Exception -> 0x0174 }
            r0.reset()     // Catch:{ Exception -> 0x0174 }
            r24.resetChains()     // Catch:{ Exception -> 0x0174 }
            android.support.constraint.solver.LinearSystem r0 = r1.mSystem     // Catch:{ Exception -> 0x0174 }
            r1.createObjectVariables(r0)     // Catch:{ Exception -> 0x0174 }
            r0 = 0
        L_0x013a:
            if (r0 >= r7) goto L_0x0158
            r20 = r4
            java.util.ArrayList r4 = r1.mChildren     // Catch:{ Exception -> 0x0154 }
            java.lang.Object r4 = r4.get(r0)     // Catch:{ Exception -> 0x0154 }
            android.support.constraint.solver.widgets.ConstraintWidget r4 = (android.support.constraint.solver.widgets.ConstraintWidget) r4     // Catch:{ Exception -> 0x0154 }
            r21 = r9
            android.support.constraint.solver.LinearSystem r9 = r1.mSystem     // Catch:{ Exception -> 0x0170 }
            r4.createObjectVariables(r9)     // Catch:{ Exception -> 0x0170 }
            int r0 = r0 + 1
            r4 = r20
            r9 = r21
            goto L_0x013a
        L_0x0154:
            r0 = move-exception
            r21 = r9
            goto L_0x0179
        L_0x0158:
            r20 = r4
            r21 = r9
            android.support.constraint.solver.LinearSystem r0 = r1.mSystem     // Catch:{ Exception -> 0x0170 }
            boolean r0 = r1.addChildrenToSolver(r0)     // Catch:{ Exception -> 0x0170 }
            r9 = r0
            if (r9 == 0) goto L_0x016d
            android.support.constraint.solver.LinearSystem r0 = r1.mSystem     // Catch:{ Exception -> 0x016b }
            r0.minimize()     // Catch:{ Exception -> 0x016b }
            goto L_0x016d
        L_0x016b:
            r0 = move-exception
            goto L_0x0179
        L_0x016d:
            r22 = r13
            goto L_0x019a
        L_0x0170:
            r0 = move-exception
            r9 = r21
            goto L_0x0179
        L_0x0174:
            r0 = move-exception
            r20 = r4
            r21 = r9
        L_0x0179:
            r0.printStackTrace()
            java.io.PrintStream r4 = java.lang.System.out
            r19 = r9
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r22 = r13
            java.lang.String r13 = "EXCEPTION : "
            java.lang.StringBuilder r9 = r9.append(r13)
            java.lang.StringBuilder r9 = r9.append(r0)
            java.lang.String r9 = r9.toString()
            r4.println(r9)
            r9 = r19
        L_0x019a:
            if (r9 == 0) goto L_0x01a6
            android.support.constraint.solver.LinearSystem r4 = r1.mSystem
            boolean[] r13 = android.support.constraint.solver.widgets.Optimizer.flags
            r1.updateChildrenFromSolver(r4, r13)
            r21 = r9
            goto L_0x01f8
        L_0x01a6:
            android.support.constraint.solver.LinearSystem r4 = r1.mSystem
            r1.updateFromSolver(r4)
            r4 = 0
        L_0x01ac:
            if (r4 >= r7) goto L_0x01f6
            java.util.ArrayList r13 = r1.mChildren
            java.lang.Object r13 = r13.get(r4)
            android.support.constraint.solver.widgets.ConstraintWidget r13 = (android.support.constraint.solver.widgets.ConstraintWidget) r13
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r13.mListDimensionBehaviors
            r18 = 0
            r0 = r0[r18]
            r21 = r9
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r0 != r9) goto L_0x01d7
            int r0 = r13.getWidth()
            int r9 = r13.getWrapWidth()
            if (r0 >= r9) goto L_0x01d4
            boolean[] r0 = android.support.constraint.solver.widgets.Optimizer.flags
            r9 = 2
            r17 = 1
            r0[r9] = r17
            goto L_0x01f8
        L_0x01d4:
            r17 = 1
            goto L_0x01d9
        L_0x01d7:
            r17 = 1
        L_0x01d9:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r13.mListDimensionBehaviors
            r0 = r0[r17]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r0 != r9) goto L_0x01f1
            int r0 = r13.getHeight()
            int r9 = r13.getWrapHeight()
            if (r0 >= r9) goto L_0x01f1
            boolean[] r0 = android.support.constraint.solver.widgets.Optimizer.flags
            r9 = 2
            r0[r9] = r17
            goto L_0x01f8
        L_0x01f1:
            int r4 = r4 + 1
            r9 = r21
            goto L_0x01ac
        L_0x01f6:
            r21 = r9
        L_0x01f8:
            r0 = 0
            if (r8 == 0) goto L_0x027c
            r4 = 8
            if (r12 >= r4) goto L_0x027c
            boolean[] r9 = android.support.constraint.solver.widgets.Optimizer.flags
            r13 = 2
            boolean r9 = r9[r13]
            if (r9 == 0) goto L_0x027c
            r9 = 0
            r13 = 0
            r16 = 0
            r4 = r16
        L_0x020c:
            if (r4 >= r7) goto L_0x0239
            r19 = r0
            java.util.ArrayList r0 = r1.mChildren
            java.lang.Object r0 = r0.get(r4)
            android.support.constraint.solver.widgets.ConstraintWidget r0 = (android.support.constraint.solver.widgets.ConstraintWidget) r0
            r23 = r7
            int r7 = r0.mX
            int r21 = r0.getWidth()
            int r7 = r7 + r21
            int r9 = java.lang.Math.max(r9, r7)
            int r7 = r0.mY
            int r21 = r0.getHeight()
            int r7 = r7 + r21
            int r13 = java.lang.Math.max(r13, r7)
            int r4 = r4 + 1
            r0 = r19
            r7 = r23
            goto L_0x020c
        L_0x0239:
            r19 = r0
            r23 = r7
            int r0 = r1.mMinWidth
            int r0 = java.lang.Math.max(r0, r9)
            int r4 = r1.mMinHeight
            int r4 = java.lang.Math.max(r4, r13)
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r11 != r7) goto L_0x0263
            int r7 = r24.getWidth()
            if (r7 >= r0) goto L_0x0263
            r1.setWidth(r0)
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r7 = r1.mListDimensionBehaviors
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r13 = 0
            r7[r13] = r9
            r7 = 1
            r9 = 1
            r20 = r7
            r19 = r9
        L_0x0263:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r10 != r7) goto L_0x0280
            int r7 = r24.getHeight()
            if (r7 >= r4) goto L_0x0280
            r1.setHeight(r4)
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r7 = r1.mListDimensionBehaviors
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r13 = 1
            r7[r13] = r9
            r7 = 1
            r9 = 1
            r4 = r7
            r0 = r9
            goto L_0x0284
        L_0x027c:
            r19 = r0
            r23 = r7
        L_0x0280:
            r0 = r19
            r4 = r20
        L_0x0284:
            int r7 = r1.mMinWidth
            int r9 = r24.getWidth()
            int r7 = java.lang.Math.max(r7, r9)
            int r9 = r24.getWidth()
            if (r7 <= r9) goto L_0x02a1
            r1.setWidth(r7)
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r9 = r1.mListDimensionBehaviors
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r13 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r18 = 0
            r9[r18] = r13
            r4 = 1
            r0 = 1
        L_0x02a1:
            int r9 = r1.mMinHeight
            int r13 = r24.getHeight()
            int r9 = java.lang.Math.max(r9, r13)
            int r13 = r24.getHeight()
            if (r9 <= r13) goto L_0x02be
            r1.setHeight(r9)
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r13 = r1.mListDimensionBehaviors
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r19 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r17 = 1
            r13[r17] = r19
            r4 = 1
            r0 = 1
        L_0x02be:
            if (r4 != 0) goto L_0x0317
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r13 = r1.mListDimensionBehaviors
            r18 = 0
            r13 = r13[r18]
            r19 = r0
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r0 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r13 != r0) goto L_0x02e5
            if (r5 <= 0) goto L_0x02e5
            int r0 = r24.getWidth()
            if (r0 <= r5) goto L_0x02e5
            r13 = 1
            r1.mWidthMeasuredTooSmall = r13
            r4 = 1
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r13 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r18 = 0
            r0[r18] = r13
            r1.setWidth(r5)
            r0 = 1
            goto L_0x02e7
        L_0x02e5:
            r0 = r19
        L_0x02e7:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r13 = r1.mListDimensionBehaviors
            r19 = r4
            r4 = 1
            r13 = r13[r4]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r4 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r13 != r4) goto L_0x0310
            if (r6 <= 0) goto L_0x0310
            int r4 = r24.getHeight()
            if (r4 <= r6) goto L_0x030d
            r4 = 1
            r1.mHeightMeasuredTooSmall = r4
            r13 = 1
            r20 = r0
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r17 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r0[r4] = r17
            r1.setHeight(r6)
            r0 = 1
            r9 = r0
            r4 = r13
            goto L_0x031b
        L_0x030d:
            r20 = r0
            goto L_0x0312
        L_0x0310:
            r20 = r0
        L_0x0312:
            r4 = r19
            r9 = r20
            goto L_0x031b
        L_0x0317:
            r19 = r0
            r9 = r19
        L_0x031b:
            r19 = r12
            r13 = r22
            r7 = r23
            goto L_0x0128
        L_0x0323:
            r20 = r4
            r23 = r7
            r21 = r9
            r22 = r13
            java.util.List<android.support.constraint.solver.widgets.ConstraintWidgetGroup> r0 = r1.mWidgetGroups
            java.lang.Object r0 = r0.get(r15)
            android.support.constraint.solver.widgets.ConstraintWidgetGroup r0 = (android.support.constraint.solver.widgets.ConstraintWidgetGroup) r0
            r0.updateUnresolvedWidgets()
            r12 = r19
            r0 = r20
        L_0x033a:
            int r15 = r15 + 1
            r13 = r22
            r4 = 0
            r7 = 32
            r9 = 1
            goto L_0x00b1
        L_0x0344:
            r22 = r13
            r4 = r14
            java.util.ArrayList r4 = (java.util.ArrayList) r4
            r1.mChildren = r4
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r1.mParent
            if (r4 == 0) goto L_0x037b
            int r4 = r1.mMinWidth
            int r7 = r24.getWidth()
            int r4 = java.lang.Math.max(r4, r7)
            int r7 = r1.mMinHeight
            int r9 = r24.getHeight()
            int r7 = java.lang.Math.max(r7, r9)
            android.support.constraint.solver.widgets.Snapshot r9 = r1.mSnapshot
            r9.applyTo(r1)
            int r9 = r1.mPaddingLeft
            int r9 = r9 + r4
            int r13 = r1.mPaddingRight
            int r9 = r9 + r13
            r1.setWidth(r9)
            int r9 = r1.mPaddingTop
            int r9 = r9 + r7
            int r13 = r1.mPaddingBottom
            int r9 = r9 + r13
            r1.setHeight(r9)
            goto L_0x037f
        L_0x037b:
            r1.mX = r2
            r1.mY = r3
        L_0x037f:
            if (r0 == 0) goto L_0x038b
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r4 = r1.mListDimensionBehaviors
            r7 = 0
            r4[r7] = r11
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r4 = r1.mListDimensionBehaviors
            r7 = 1
            r4[r7] = r10
        L_0x038b:
            android.support.constraint.solver.LinearSystem r4 = r1.mSystem
            android.support.constraint.solver.Cache r4 = r4.getCache()
            r1.resetSolverVariables(r4)
            android.support.constraint.solver.widgets.ConstraintWidgetContainer r4 = r24.getRootConstraintContainer()
            if (r1 != r4) goto L_0x039d
            r24.updateDrawPosition()
        L_0x039d:
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
        int i = this.mHorizontalChainsSize + 1;
        ChainHead[] chainHeadArr = this.mHorizontalChainsArray;
        if (i >= chainHeadArr.length) {
            this.mHorizontalChainsArray = (ChainHead[]) Arrays.copyOf(chainHeadArr, chainHeadArr.length * 2);
        }
        this.mHorizontalChainsArray[this.mHorizontalChainsSize] = new ChainHead(widget, 0, isRtl());
        this.mHorizontalChainsSize++;
    }

    private void addVerticalChain(ConstraintWidget widget) {
        int i = this.mVerticalChainsSize + 1;
        ChainHead[] chainHeadArr = this.mVerticalChainsArray;
        if (i >= chainHeadArr.length) {
            this.mVerticalChainsArray = (ChainHead[]) Arrays.copyOf(chainHeadArr, chainHeadArr.length * 2);
        }
        this.mVerticalChainsArray[this.mVerticalChainsSize] = new ChainHead(widget, 1, isRtl());
        this.mVerticalChainsSize++;
    }

    public List<ConstraintWidgetGroup> getWidgetGroups() {
        return this.mWidgetGroups;
    }
}
