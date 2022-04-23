package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;

class Chain {
    private static final boolean DEBUG = false;

    Chain() {
    }

    static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem system, int orientation) {
        ChainHead[] chainsArray;
        int chainsSize;
        int offset;
        if (orientation == 0) {
            offset = 0;
            chainsSize = constraintWidgetContainer.mHorizontalChainsSize;
            chainsArray = constraintWidgetContainer.mHorizontalChainsArray;
        } else {
            offset = 2;
            chainsSize = constraintWidgetContainer.mVerticalChainsSize;
            chainsArray = constraintWidgetContainer.mVerticalChainsArray;
        }
        for (int i = 0; i < chainsSize; i++) {
            ChainHead first = chainsArray[i];
            first.define();
            applyChainConstraints(constraintWidgetContainer, system, orientation, offset, first);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:299:0x0666 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:304:0x067a  */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x067f  */
    /* JADX WARNING: Removed duplicated region for block: B:308:0x0686  */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x068b  */
    /* JADX WARNING: Removed duplicated region for block: B:311:0x068e  */
    /* JADX WARNING: Removed duplicated region for block: B:316:0x06a6  */
    /* JADX WARNING: Removed duplicated region for block: B:318:0x06aa  */
    /* JADX WARNING: Removed duplicated region for block: B:319:0x06b6  */
    /* JADX WARNING: Removed duplicated region for block: B:321:0x06b9 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void applyChainConstraints(android.support.constraint.solver.widgets.ConstraintWidgetContainer r45, android.support.constraint.solver.LinearSystem r46, int r47, int r48, android.support.constraint.solver.widgets.ChainHead r49) {
        /*
            r0 = r45
            r10 = r46
            r11 = r49
            android.support.constraint.solver.widgets.ConstraintWidget r12 = r11.mFirst
            android.support.constraint.solver.widgets.ConstraintWidget r13 = r11.mLast
            android.support.constraint.solver.widgets.ConstraintWidget r14 = r11.mFirstVisibleWidget
            android.support.constraint.solver.widgets.ConstraintWidget r15 = r11.mLastVisibleWidget
            android.support.constraint.solver.widgets.ConstraintWidget r9 = r11.mHead
            r1 = r12
            r2 = 0
            r3 = 0
            float r4 = r11.mTotalWeight
            android.support.constraint.solver.widgets.ConstraintWidget r8 = r11.mFirstMatchConstraintWidget
            android.support.constraint.solver.widgets.ConstraintWidget r7 = r11.mLastMatchConstraintWidget
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r5 = r0.mListDimensionBehaviors
            r5 = r5[r47]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r16 = r1
            if (r5 != r6) goto L_0x0025
            r5 = 1
            goto L_0x0026
        L_0x0025:
            r5 = 0
        L_0x0026:
            r18 = r5
            r5 = 0
            r6 = 0
            r19 = 0
            if (r47 != 0) goto L_0x0052
            int r1 = r9.mHorizontalChainStyle
            if (r1 != 0) goto L_0x0034
            r1 = 1
            goto L_0x0035
        L_0x0034:
            r1 = 0
        L_0x0035:
            int r5 = r9.mHorizontalChainStyle
            r22 = r1
            r1 = 1
            if (r5 != r1) goto L_0x003e
            r1 = 1
            goto L_0x003f
        L_0x003e:
            r1 = 0
        L_0x003f:
            int r5 = r9.mHorizontalChainStyle
            r6 = 2
            if (r5 != r6) goto L_0x0046
            r5 = 1
            goto L_0x0047
        L_0x0046:
            r5 = 0
        L_0x0047:
            r19 = r2
            r21 = r3
            r23 = r5
            r6 = r16
            r16 = r1
            goto L_0x0075
        L_0x0052:
            int r1 = r9.mVerticalChainStyle
            if (r1 != 0) goto L_0x0058
            r1 = 1
            goto L_0x0059
        L_0x0058:
            r1 = 0
        L_0x0059:
            int r5 = r9.mVerticalChainStyle
            r22 = r1
            r1 = 1
            if (r5 != r1) goto L_0x0062
            r1 = 1
            goto L_0x0063
        L_0x0062:
            r1 = 0
        L_0x0063:
            int r5 = r9.mVerticalChainStyle
            r6 = 2
            if (r5 != r6) goto L_0x006a
            r5 = 1
            goto L_0x006b
        L_0x006a:
            r5 = 0
        L_0x006b:
            r19 = r2
            r21 = r3
            r23 = r5
            r6 = r16
            r16 = r1
        L_0x0075:
            if (r21 != 0) goto L_0x0164
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r6.mListAnchors
            r3 = r3[r48]
            r24 = 4
            if (r23 == 0) goto L_0x0081
            r24 = 1
        L_0x0081:
            int r25 = r3.getMargin()
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r6.mListDimensionBehaviors
            r1 = r1[r47]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r1 != r5) goto L_0x0095
            int[] r1 = r6.mResolvedMatchConstraintDefault
            r1 = r1[r47]
            if (r1 != 0) goto L_0x0095
            r1 = 1
            goto L_0x0096
        L_0x0095:
            r1 = 0
        L_0x0096:
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r3.mTarget
            if (r5 == 0) goto L_0x00a7
            if (r6 == r12) goto L_0x00a7
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r3.mTarget
            int r5 = r5.getMargin()
            int r25 = r25 + r5
            r5 = r25
            goto L_0x00a9
        L_0x00a7:
            r5 = r25
        L_0x00a9:
            if (r23 == 0) goto L_0x00b1
            if (r6 == r12) goto L_0x00b1
            if (r6 == r14) goto L_0x00b1
            r24 = 5
        L_0x00b1:
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r3.mTarget
            if (r2 == 0) goto L_0x00e9
            if (r6 != r14) goto L_0x00c6
            android.support.constraint.solver.SolverVariable r2 = r3.mSolverVariable
            r28 = r4
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r3.mTarget
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            r29 = r7
            r7 = 6
            r10.addGreaterThan(r2, r4, r5, r7)
            goto L_0x00d5
        L_0x00c6:
            r28 = r4
            r29 = r7
            android.support.constraint.solver.SolverVariable r2 = r3.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r3.mTarget
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            r7 = 8
            r10.addGreaterThan(r2, r4, r5, r7)
        L_0x00d5:
            if (r1 == 0) goto L_0x00db
            if (r23 != 0) goto L_0x00db
            r2 = 5
            goto L_0x00dd
        L_0x00db:
            r2 = r24
        L_0x00dd:
            android.support.constraint.solver.SolverVariable r4 = r3.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r3.mTarget
            android.support.constraint.solver.SolverVariable r7 = r7.mSolverVariable
            r10.addEquality(r4, r7, r5, r2)
            r24 = r2
            goto L_0x00ed
        L_0x00e9:
            r28 = r4
            r29 = r7
        L_0x00ed:
            if (r18 == 0) goto L_0x012a
            int r2 = r6.getVisibility()
            r4 = 8
            if (r2 == r4) goto L_0x0115
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r2 = r6.mListDimensionBehaviors
            r2 = r2[r47]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r4 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r2 != r4) goto L_0x0115
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r6.mListAnchors
            int r4 = r48 + 1
            r2 = r2[r4]
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r6.mListAnchors
            r4 = r4[r48]
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            r25 = r1
            r1 = 0
            r7 = 5
            r10.addGreaterThan(r2, r4, r1, r7)
            goto L_0x0117
        L_0x0115:
            r25 = r1
        L_0x0117:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r6.mListAnchors
            r1 = r1[r48]
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r0.mListAnchors
            r2 = r2[r48]
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            r4 = 8
            r7 = 0
            r10.addGreaterThan(r1, r2, r7, r4)
            goto L_0x012c
        L_0x012a:
            r25 = r1
        L_0x012c:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r6.mListAnchors
            int r2 = r48 + 1
            r1 = r1[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mTarget
            if (r1 == 0) goto L_0x0152
            android.support.constraint.solver.widgets.ConstraintWidget r2 = r1.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r2.mListAnchors
            r4 = r4[r48]
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 == 0) goto L_0x014e
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r2.mListAnchors
            r4 = r4[r48]
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r4.mOwner
            if (r4 == r6) goto L_0x014b
            goto L_0x014e
        L_0x014b:
            r19 = r2
            goto L_0x0155
        L_0x014e:
            r2 = 0
            r19 = r2
            goto L_0x0155
        L_0x0152:
            r2 = 0
            r19 = r2
        L_0x0155:
            if (r19 == 0) goto L_0x015b
            r2 = r19
            r6 = r2
            goto L_0x015e
        L_0x015b:
            r2 = 1
            r21 = r2
        L_0x015e:
            r4 = r28
            r7 = r29
            goto L_0x0075
        L_0x0164:
            r28 = r4
            r29 = r7
            if (r15 == 0) goto L_0x01d1
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r13.mListAnchors
            int r3 = r48 + 1
            r2 = r2[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x01d1
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r15.mListAnchors
            int r3 = r48 + 1
            r2 = r2[r3]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r3 = r15.mListDimensionBehaviors
            r3 = r3[r47]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r4 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r3 != r4) goto L_0x018a
            int[] r3 = r15.mResolvedMatchConstraintDefault
            r3 = r3[r47]
            if (r3 != 0) goto L_0x018a
            r3 = 1
            goto L_0x018b
        L_0x018a:
            r3 = 0
        L_0x018b:
            if (r3 == 0) goto L_0x01a5
            if (r23 != 0) goto L_0x01a5
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r2.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r4.mOwner
            if (r4 != r0) goto L_0x01a5
            android.support.constraint.solver.SolverVariable r4 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r2.mTarget
            android.support.constraint.solver.SolverVariable r5 = r5.mSolverVariable
            int r7 = r2.getMargin()
            int r7 = -r7
            r1 = 5
            r10.addEquality(r4, r5, r7, r1)
            goto L_0x01bc
        L_0x01a5:
            if (r23 == 0) goto L_0x01bc
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r2.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r1 = r1.mOwner
            if (r1 != r0) goto L_0x01bc
            android.support.constraint.solver.SolverVariable r1 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r2.mTarget
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            int r5 = r2.getMargin()
            int r5 = -r5
            r7 = 4
            r10.addEquality(r1, r4, r5, r7)
        L_0x01bc:
            android.support.constraint.solver.SolverVariable r1 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r13.mListAnchors
            int r5 = r48 + 1
            r4 = r4[r5]
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            int r5 = r2.getMargin()
            int r5 = -r5
            r7 = 6
            r10.addLowerThan(r1, r4, r5, r7)
        L_0x01d1:
            if (r18 == 0) goto L_0x01f2
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r0.mListAnchors
            int r2 = r48 + 1
            r1 = r1[r2]
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r13.mListAnchors
            int r3 = r48 + 1
            r2 = r2[r3]
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r13.mListAnchors
            int r4 = r48 + 1
            r3 = r3[r4]
            int r3 = r3.getMargin()
            r4 = 8
            r10.addGreaterThan(r1, r2, r3, r4)
        L_0x01f2:
            java.util.ArrayList<android.support.constraint.solver.widgets.ConstraintWidget> r7 = r11.mWeightedMatchConstraintsWidgets
            if (r7 == 0) goto L_0x02da
            int r1 = r7.size()
            r2 = 1
            if (r1 <= r2) goto L_0x02d1
            r3 = 0
            r4 = 0
            boolean r5 = r11.mHasUndefinedWeights
            if (r5 == 0) goto L_0x020c
            boolean r5 = r11.mHasComplexMatchWeights
            if (r5 != 0) goto L_0x020c
            int r5 = r11.mWidgetsMatchCount
            float r5 = (float) r5
            r28 = r5
        L_0x020c:
            r5 = 0
        L_0x020d:
            if (r5 >= r1) goto L_0x02c6
            java.lang.Object r20 = r7.get(r5)
            r2 = r20
            android.support.constraint.solver.widgets.ConstraintWidget r2 = (android.support.constraint.solver.widgets.ConstraintWidget) r2
            float[] r0 = r2.mWeight
            r0 = r0[r47]
            r20 = 0
            int r26 = (r0 > r20 ? 1 : (r0 == r20 ? 0 : -1))
            if (r26 >= 0) goto L_0x024f
            r26 = r0
            boolean r0 = r11.mHasComplexMatchWeights
            if (r0 == 0) goto L_0x0245
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r2.mListAnchors
            int r20 = r48 + 1
            r0 = r0[r20]
            android.support.constraint.solver.SolverVariable r0 = r0.mSolverVariable
            r38 = r1
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r2.mListAnchors
            r1 = r1[r48]
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            r39 = r6
            r24 = r7
            r6 = 4
            r7 = 0
            r10.addEquality(r0, r1, r7, r6)
            r17 = r8
            r8 = 0
            goto L_0x02b7
        L_0x0245:
            r38 = r1
            r39 = r6
            r24 = r7
            r6 = 4
            r0 = 1065353216(0x3f800000, float:1.0)
            goto L_0x0258
        L_0x024f:
            r26 = r0
            r38 = r1
            r39 = r6
            r24 = r7
            r6 = 4
        L_0x0258:
            int r1 = (r0 > r20 ? 1 : (r0 == r20 ? 0 : -1))
            if (r1 != 0) goto L_0x0273
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r2.mListAnchors
            int r7 = r48 + 1
            r1 = r1[r7]
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r7 = r2.mListAnchors
            r7 = r7[r48]
            android.support.constraint.solver.SolverVariable r7 = r7.mSolverVariable
            r17 = r8
            r6 = 8
            r8 = 0
            r10.addEquality(r1, r7, r8, r6)
            goto L_0x02b7
        L_0x0273:
            r17 = r8
            r8 = 0
            if (r3 == 0) goto L_0x02b1
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r3.mListAnchors
            r1 = r1[r48]
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r3.mListAnchors
            int r7 = r48 + 1
            r6 = r6[r7]
            android.support.constraint.solver.SolverVariable r6 = r6.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r7 = r2.mListAnchors
            r7 = r7[r48]
            android.support.constraint.solver.SolverVariable r7 = r7.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r8 = r2.mListAnchors
            int r30 = r48 + 1
            r8 = r8[r30]
            android.support.constraint.solver.SolverVariable r8 = r8.mSolverVariable
            r40 = r3
            android.support.constraint.solver.ArrayRow r3 = r46.createRow()
            r30 = r3
            r31 = r4
            r32 = r28
            r33 = r0
            r34 = r1
            r35 = r6
            r36 = r7
            r37 = r8
            r30.createRowEqualMatchDimensions(r31, r32, r33, r34, r35, r36, r37)
            r10.addConstraint(r3)
            goto L_0x02b3
        L_0x02b1:
            r40 = r3
        L_0x02b3:
            r1 = r2
            r3 = r0
            r4 = r3
            r3 = r1
        L_0x02b7:
            int r5 = r5 + 1
            r0 = r45
            r8 = r17
            r7 = r24
            r1 = r38
            r6 = r39
            r2 = 1
            goto L_0x020d
        L_0x02c6:
            r38 = r1
            r40 = r3
            r39 = r6
            r24 = r7
            r17 = r8
            goto L_0x02e0
        L_0x02d1:
            r38 = r1
            r39 = r6
            r24 = r7
            r17 = r8
            goto L_0x02e0
        L_0x02da:
            r39 = r6
            r24 = r7
            r17 = r8
        L_0x02e0:
            if (r14 == 0) goto L_0x0372
            if (r14 == r15) goto L_0x02f3
            if (r23 == 0) goto L_0x02e7
            goto L_0x02f3
        L_0x02e7:
            r35 = r9
            r32 = r39
            r44 = r29
            r29 = r24
            r24 = r44
            goto L_0x037c
        L_0x02f3:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r12.mListAnchors
            r1 = r1[r48]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r13.mListAnchors
            int r3 = r48 + 1
            r2 = r2[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r1.mTarget
            if (r3 == 0) goto L_0x0306
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r1.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x0307
        L_0x0306:
            r3 = 0
        L_0x0307:
            r20 = r3
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r2.mTarget
            if (r3 == 0) goto L_0x0312
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r2.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x0313
        L_0x0312:
            r3 = 0
        L_0x0313:
            r25 = r3
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r14.mListAnchors
            r8 = r3[r48]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r15.mListAnchors
            int r3 = r48 + 1
            r7 = r1[r3]
            if (r20 == 0) goto L_0x0362
            if (r25 == 0) goto L_0x0362
            r1 = 1056964608(0x3f000000, float:0.5)
            if (r47 != 0) goto L_0x032c
            float r1 = r9.mHorizontalBiasPercent
            r26 = r1
            goto L_0x0330
        L_0x032c:
            float r1 = r9.mVerticalBiasPercent
            r26 = r1
        L_0x0330:
            int r27 = r8.getMargin()
            int r30 = r7.getMargin()
            android.support.constraint.solver.SolverVariable r2 = r8.mSolverVariable
            android.support.constraint.solver.SolverVariable r6 = r7.mSolverVariable
            r31 = 7
            r1 = r46
            r3 = r20
            r4 = r27
            r5 = r26
            r33 = r6
            r32 = r39
            r6 = r25
            r34 = r7
            r44 = r29
            r29 = r24
            r24 = r44
            r7 = r33
            r33 = r8
            r8 = r30
            r35 = r9
            r9 = r31
            r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9)
            goto L_0x0370
        L_0x0362:
            r34 = r7
            r33 = r8
            r35 = r9
            r32 = r39
            r44 = r29
            r29 = r24
            r24 = r44
        L_0x0370:
            goto L_0x0664
        L_0x0372:
            r35 = r9
            r32 = r39
            r44 = r29
            r29 = r24
            r24 = r44
        L_0x037c:
            if (r22 == 0) goto L_0x04cb
            if (r14 == 0) goto L_0x04cb
            r1 = r14
            r2 = r14
            int r3 = r11.mWidgetsMatchCount
            if (r3 <= 0) goto L_0x038f
            int r3 = r11.mWidgetsCount
            int r4 = r11.mWidgetsMatchCount
            if (r3 != r4) goto L_0x038f
            r25 = 1
            goto L_0x0391
        L_0x038f:
            r25 = 0
        L_0x0391:
            r20 = r25
            r8 = r1
            r9 = r2
        L_0x0395:
            if (r8 == 0) goto L_0x04c3
            android.support.constraint.solver.widgets.ConstraintWidget[] r1 = r8.mNextChainWidget
            r1 = r1[r47]
            r7 = r1
        L_0x039c:
            if (r7 == 0) goto L_0x03ab
            int r1 = r7.getVisibility()
            r5 = 8
            if (r1 != r5) goto L_0x03ad
            android.support.constraint.solver.widgets.ConstraintWidget[] r1 = r7.mNextChainWidget
            r7 = r1[r47]
            goto L_0x039c
        L_0x03ab:
            r5 = 8
        L_0x03ad:
            if (r7 != 0) goto L_0x03bb
            if (r8 != r15) goto L_0x03b2
            goto L_0x03bb
        L_0x03b2:
            r0 = r5
            r39 = r7
            r40 = r8
            r41 = r9
            goto L_0x04b1
        L_0x03bb:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r8.mListAnchors
            r6 = r1[r48]
            android.support.constraint.solver.SolverVariable r4 = r6.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r6.mTarget
            if (r1 == 0) goto L_0x03ca
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r6.mTarget
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            goto L_0x03cb
        L_0x03ca:
            r1 = 0
        L_0x03cb:
            if (r9 == r8) goto L_0x03d8
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r9.mListAnchors
            int r3 = r48 + 1
            r2 = r2[r3]
            android.support.constraint.solver.SolverVariable r1 = r2.mSolverVariable
            r19 = r1
            goto L_0x03f4
        L_0x03d8:
            if (r8 != r14) goto L_0x03f2
            if (r9 != r8) goto L_0x03f2
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r12.mListAnchors
            r2 = r2[r48]
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x03ed
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r12.mListAnchors
            r2 = r2[r48]
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            goto L_0x03ee
        L_0x03ed:
            r2 = 0
        L_0x03ee:
            r1 = r2
            r19 = r1
            goto L_0x03f4
        L_0x03f2:
            r19 = r1
        L_0x03f4:
            r1 = 0
            r2 = 0
            r3 = 0
            int r25 = r6.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r8.mListAnchors
            int r26 = r48 + 1
            r0 = r0[r26]
            int r0 = r0.getMargin()
            if (r7 == 0) goto L_0x041c
            android.support.constraint.solver.widgets.ConstraintAnchor[] r5 = r7.mListAnchors
            r1 = r5[r48]
            android.support.constraint.solver.SolverVariable r2 = r1.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r5 = r8.mListAnchors
            int r30 = r48 + 1
            r5 = r5[r30]
            android.support.constraint.solver.SolverVariable r3 = r5.mSolverVariable
            r30 = r1
            r31 = r2
            r32 = r3
            goto L_0x0436
        L_0x041c:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r5 = r13.mListAnchors
            int r30 = r48 + 1
            r5 = r5[r30]
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r5.mTarget
            if (r1 == 0) goto L_0x0428
            android.support.constraint.solver.SolverVariable r2 = r1.mSolverVariable
        L_0x0428:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r5 = r8.mListAnchors
            int r30 = r48 + 1
            r5 = r5[r30]
            android.support.constraint.solver.SolverVariable r3 = r5.mSolverVariable
            r30 = r1
            r31 = r2
            r32 = r3
        L_0x0436:
            if (r30 == 0) goto L_0x043d
            int r1 = r30.getMargin()
            int r0 = r0 + r1
        L_0x043d:
            if (r9 == 0) goto L_0x044b
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r9.mListAnchors
            int r2 = r48 + 1
            r1 = r1[r2]
            int r1 = r1.getMargin()
            int r25 = r25 + r1
        L_0x044b:
            if (r4 == 0) goto L_0x04a3
            if (r19 == 0) goto L_0x04a3
            if (r31 == 0) goto L_0x04a3
            if (r32 == 0) goto L_0x04a3
            r1 = r25
            if (r8 != r14) goto L_0x0462
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r14.mListAnchors
            r2 = r2[r48]
            int r1 = r2.getMargin()
            r33 = r1
            goto L_0x0464
        L_0x0462:
            r33 = r1
        L_0x0464:
            r1 = r0
            if (r8 != r15) goto L_0x0474
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r15.mListAnchors
            int r3 = r48 + 1
            r2 = r2[r3]
            int r1 = r2.getMargin()
            r34 = r1
            goto L_0x0476
        L_0x0474:
            r34 = r1
        L_0x0476:
            r1 = 5
            if (r20 == 0) goto L_0x047e
            r1 = 8
            r36 = r1
            goto L_0x0480
        L_0x047e:
            r36 = r1
        L_0x0480:
            r5 = 1056964608(0x3f000000, float:0.5)
            r1 = r46
            r2 = r4
            r3 = r19
            r37 = r4
            r4 = r33
            r38 = r0
            r0 = 8
            r26 = r6
            r6 = r31
            r39 = r7
            r7 = r32
            r40 = r8
            r8 = r34
            r41 = r9
            r9 = r36
            r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9)
            goto L_0x04b1
        L_0x04a3:
            r38 = r0
            r37 = r4
            r26 = r6
            r39 = r7
            r40 = r8
            r41 = r9
            r0 = 8
        L_0x04b1:
            int r1 = r40.getVisibility()
            if (r1 == r0) goto L_0x04bb
            r1 = r40
            r9 = r1
            goto L_0x04bd
        L_0x04bb:
            r9 = r41
        L_0x04bd:
            r8 = r39
            r19 = r39
            goto L_0x0395
        L_0x04c3:
            r40 = r8
            r41 = r9
            r32 = r40
            goto L_0x0664
        L_0x04cb:
            r0 = 8
            if (r16 == 0) goto L_0x0664
            if (r14 == 0) goto L_0x0664
            r1 = r14
            r2 = r14
            int r3 = r11.mWidgetsMatchCount
            if (r3 <= 0) goto L_0x04e0
            int r3 = r11.mWidgetsCount
            int r4 = r11.mWidgetsMatchCount
            if (r3 != r4) goto L_0x04e0
            r25 = 1
            goto L_0x04e2
        L_0x04e0:
            r25 = 0
        L_0x04e2:
            r20 = r25
            r8 = r1
            r9 = r2
        L_0x04e6:
            if (r8 == 0) goto L_0x05d3
            android.support.constraint.solver.widgets.ConstraintWidget[] r1 = r8.mNextChainWidget
            r1 = r1[r47]
        L_0x04ec:
            if (r1 == 0) goto L_0x04f9
            int r2 = r1.getVisibility()
            if (r2 != r0) goto L_0x04f9
            android.support.constraint.solver.widgets.ConstraintWidget[] r2 = r1.mNextChainWidget
            r1 = r2[r47]
            goto L_0x04ec
        L_0x04f9:
            if (r8 == r14) goto L_0x05ba
            if (r8 == r15) goto L_0x05ba
            if (r1 == 0) goto L_0x05ba
            if (r1 != r15) goto L_0x0504
            r1 = 0
            r7 = r1
            goto L_0x0505
        L_0x0504:
            r7 = r1
        L_0x0505:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r8.mListAnchors
            r6 = r1[r48]
            android.support.constraint.solver.SolverVariable r5 = r6.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r6.mTarget
            if (r1 == 0) goto L_0x0514
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r6.mTarget
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            goto L_0x0515
        L_0x0514:
            r1 = 0
        L_0x0515:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r9.mListAnchors
            int r3 = r48 + 1
            r2 = r2[r3]
            android.support.constraint.solver.SolverVariable r4 = r2.mSolverVariable
            r1 = 0
            r2 = 0
            r3 = 0
            int r19 = r6.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r8.mListAnchors
            int r25 = r48 + 1
            r0 = r0[r25]
            int r0 = r0.getMargin()
            if (r7 == 0) goto L_0x054b
            r25 = r1
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r7.mListAnchors
            r1 = r1[r48]
            android.support.constraint.solver.SolverVariable r2 = r1.mSolverVariable
            r25 = r2
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r1.mTarget
            if (r2 == 0) goto L_0x0543
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r1.mTarget
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            goto L_0x0544
        L_0x0543:
            r2 = 0
        L_0x0544:
            r30 = r2
            r31 = r25
            r25 = r1
            goto L_0x0563
        L_0x054b:
            r25 = r1
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r15.mListAnchors
            r1 = r1[r48]
            if (r1 == 0) goto L_0x0555
            android.support.constraint.solver.SolverVariable r2 = r1.mSolverVariable
        L_0x0555:
            r25 = r1
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r8.mListAnchors
            int r30 = r48 + 1
            r1 = r1[r30]
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            r30 = r1
            r31 = r2
        L_0x0563:
            if (r25 == 0) goto L_0x056a
            int r1 = r25.getMargin()
            int r0 = r0 + r1
        L_0x056a:
            if (r9 == 0) goto L_0x0578
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r9.mListAnchors
            int r2 = r48 + 1
            r1 = r1[r2]
            int r1 = r1.getMargin()
            int r19 = r19 + r1
        L_0x0578:
            r1 = 4
            if (r20 == 0) goto L_0x0580
            r1 = 8
            r32 = r1
            goto L_0x0582
        L_0x0580:
            r32 = r1
        L_0x0582:
            if (r5 == 0) goto L_0x05ab
            if (r4 == 0) goto L_0x05ab
            if (r31 == 0) goto L_0x05ab
            if (r30 == 0) goto L_0x05ab
            r33 = 1056964608(0x3f000000, float:0.5)
            r1 = r46
            r2 = r5
            r3 = r4
            r34 = r4
            r4 = r19
            r36 = r5
            r5 = r33
            r33 = r6
            r6 = r31
            r37 = r7
            r7 = r30
            r38 = r8
            r8 = r0
            r39 = r9
            r9 = r32
            r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9)
            goto L_0x05b7
        L_0x05ab:
            r34 = r4
            r36 = r5
            r33 = r6
            r37 = r7
            r38 = r8
            r39 = r9
        L_0x05b7:
            r19 = r37
            goto L_0x05c0
        L_0x05ba:
            r38 = r8
            r39 = r9
            r19 = r1
        L_0x05c0:
            int r0 = r38.getVisibility()
            r1 = 8
            if (r0 == r1) goto L_0x05cc
            r0 = r38
            r9 = r0
            goto L_0x05ce
        L_0x05cc:
            r9 = r39
        L_0x05ce:
            r8 = r19
            r0 = r1
            goto L_0x04e6
        L_0x05d3:
            r38 = r8
            r39 = r9
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r14.mListAnchors
            r0 = r0[r48]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r12.mListAnchors
            r1 = r1[r48]
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r1.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r15.mListAnchors
            int r2 = r48 + 1
            r8 = r1[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r13.mListAnchors
            int r2 = r48 + 1
            r1 = r1[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r1.mTarget
            r6 = 5
            if (r9 == 0) goto L_0x063f
            if (r14 == r15) goto L_0x0608
            android.support.constraint.solver.SolverVariable r1 = r0.mSolverVariable
            android.support.constraint.solver.SolverVariable r2 = r9.mSolverVariable
            int r3 = r0.getMargin()
            r10.addEquality(r1, r2, r3, r6)
            r31 = r6
            r42 = r7
            r43 = r8
            r25 = r9
            goto L_0x0647
        L_0x0608:
            if (r7 == 0) goto L_0x0636
            android.support.constraint.solver.SolverVariable r2 = r0.mSolverVariable
            android.support.constraint.solver.SolverVariable r3 = r9.mSolverVariable
            int r4 = r0.getMargin()
            android.support.constraint.solver.SolverVariable r1 = r8.mSolverVariable
            android.support.constraint.solver.SolverVariable r5 = r7.mSolverVariable
            int r26 = r8.getMargin()
            r30 = r1
            r1 = r46
            r25 = r5
            r5 = 1056964608(0x3f000000, float:0.5)
            r31 = r6
            r6 = r30
            r42 = r7
            r7 = r25
            r43 = r8
            r8 = r26
            r25 = r9
            r9 = r31
            r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9)
            goto L_0x0647
        L_0x0636:
            r31 = r6
            r42 = r7
            r43 = r8
            r25 = r9
            goto L_0x0647
        L_0x063f:
            r31 = r6
            r42 = r7
            r43 = r8
            r25 = r9
        L_0x0647:
            r1 = r42
            if (r1 == 0) goto L_0x065e
            if (r14 == r15) goto L_0x065e
            r2 = r43
            android.support.constraint.solver.SolverVariable r3 = r2.mSolverVariable
            android.support.constraint.solver.SolverVariable r4 = r1.mSolverVariable
            int r5 = r2.getMargin()
            int r5 = -r5
            r6 = r31
            r10.addEquality(r3, r4, r5, r6)
            goto L_0x0662
        L_0x065e:
            r6 = r31
            r2 = r43
        L_0x0662:
            r32 = r38
        L_0x0664:
            if (r22 != 0) goto L_0x0668
            if (r16 == 0) goto L_0x06eb
        L_0x0668:
            if (r14 == 0) goto L_0x06eb
            if (r14 == r15) goto L_0x06eb
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r14.mListAnchors
            r0 = r0[r48]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r15.mListAnchors
            int r2 = r48 + 1
            r1 = r1[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r0.mTarget
            if (r2 == 0) goto L_0x067f
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r0.mTarget
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            goto L_0x0680
        L_0x067f:
            r2 = 0
        L_0x0680:
            r20 = r2
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r1.mTarget
            if (r2 == 0) goto L_0x068b
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r1.mTarget
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            goto L_0x068c
        L_0x068b:
            r2 = 0
        L_0x068c:
            if (r13 == r15) goto L_0x06a6
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r13.mListAnchors
            int r4 = r48 + 1
            r3 = r3[r4]
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r3.mTarget
            if (r4 == 0) goto L_0x069f
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r3.mTarget
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            r27 = r4
            goto L_0x06a1
        L_0x069f:
            r27 = 0
        L_0x06a1:
            r2 = r27
            r25 = r2
            goto L_0x06a8
        L_0x06a6:
            r25 = r2
        L_0x06a8:
            if (r14 != r15) goto L_0x06b6
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r14.mListAnchors
            r0 = r2[r48]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r14.mListAnchors
            int r3 = r48 + 1
            r1 = r2[r3]
            r9 = r1
            goto L_0x06b7
        L_0x06b6:
            r9 = r1
        L_0x06b7:
            if (r20 == 0) goto L_0x06e9
            if (r25 == 0) goto L_0x06e9
            r26 = 1056964608(0x3f000000, float:0.5)
            int r27 = r0.getMargin()
            if (r15 != 0) goto L_0x06c5
            r1 = r13
            r15 = r1
        L_0x06c5:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r15.mListAnchors
            int r2 = r48 + 1
            r1 = r1[r2]
            int r30 = r1.getMargin()
            android.support.constraint.solver.SolverVariable r2 = r0.mSolverVariable
            android.support.constraint.solver.SolverVariable r7 = r9.mSolverVariable
            r31 = 5
            r1 = r46
            r3 = r20
            r4 = r27
            r5 = r26
            r6 = r25
            r8 = r30
            r33 = r9
            r9 = r31
            r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9)
            goto L_0x06eb
        L_0x06e9:
            r33 = r9
        L_0x06eb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.Chain.applyChainConstraints(android.support.constraint.solver.widgets.ConstraintWidgetContainer, android.support.constraint.solver.LinearSystem, int, int, android.support.constraint.solver.widgets.ChainHead):void");
    }
}
