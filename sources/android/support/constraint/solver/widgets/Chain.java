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
            if (!constraintWidgetContainer.optimizeFor(4)) {
                applyChainConstraints(constraintWidgetContainer, system, orientation, offset, first);
            } else if (!Optimizer.applyChainOptimized(constraintWidgetContainer, system, orientation, offset, first)) {
                applyChainConstraints(constraintWidgetContainer, system, orientation, offset, first);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:285:0x0616 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:288:0x0621  */
    /* JADX WARNING: Removed duplicated region for block: B:314:0x06a4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void applyChainConstraints(android.support.constraint.solver.widgets.ConstraintWidgetContainer r60, android.support.constraint.solver.LinearSystem r61, int r62, int r63, android.support.constraint.solver.widgets.ChainHead r64) {
        /*
            r0 = r60
            r10 = r61
            r12 = r64
            android.support.constraint.solver.widgets.ConstraintWidget r13 = r12.mFirst
            android.support.constraint.solver.widgets.ConstraintWidget r14 = r12.mLast
            android.support.constraint.solver.widgets.ConstraintWidget r9 = r12.mFirstVisibleWidget
            android.support.constraint.solver.widgets.ConstraintWidget r8 = r12.mLastVisibleWidget
            android.support.constraint.solver.widgets.ConstraintWidget r7 = r12.mHead
            r1 = r13
            r2 = 0
            r3 = 0
            float r4 = r12.mTotalWeight
            android.support.constraint.solver.widgets.ConstraintWidget r6 = r12.mFirstMatchConstraintWidget
            android.support.constraint.solver.widgets.ConstraintWidget r5 = r12.mLastMatchConstraintWidget
            r15 = r1
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r0.mListDimensionBehaviors
            r1 = r1[r62]
            r16 = r2
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r17 = r3
            if (r1 != r2) goto L_0x0028
            r1 = 1
            goto L_0x0029
        L_0x0028:
            r1 = 0
        L_0x0029:
            r19 = r1
            r1 = 0
            r2 = 0
            r20 = 0
            if (r62 != 0) goto L_0x0052
            int r3 = r7.mHorizontalChainStyle
            if (r3 != 0) goto L_0x0037
            r3 = 1
            goto L_0x0038
        L_0x0037:
            r3 = 0
        L_0x0038:
            r1 = r3
            int r3 = r7.mHorizontalChainStyle
            r23 = r1
            r1 = 1
            if (r3 != r1) goto L_0x0042
            r1 = 1
            goto L_0x0043
        L_0x0042:
            r1 = 0
        L_0x0043:
            int r2 = r7.mHorizontalChainStyle
            r3 = 2
            if (r2 != r3) goto L_0x004a
            r2 = 1
            goto L_0x004b
        L_0x004a:
            r2 = 0
        L_0x004b:
            r3 = r15
            r20 = r16
        L_0x004e:
            r16 = r1
            r15 = r2
            goto L_0x0072
        L_0x0052:
            int r3 = r7.mVerticalChainStyle
            if (r3 != 0) goto L_0x0058
            r3 = 1
            goto L_0x0059
        L_0x0058:
            r3 = 0
        L_0x0059:
            r1 = r3
            int r3 = r7.mVerticalChainStyle
            r24 = r1
            r1 = 1
            if (r3 != r1) goto L_0x0063
            r1 = 1
            goto L_0x0064
        L_0x0063:
            r1 = 0
        L_0x0064:
            int r2 = r7.mVerticalChainStyle
            r3 = 2
            if (r2 != r3) goto L_0x006b
            r2 = 1
            goto L_0x006c
        L_0x006b:
            r2 = 0
        L_0x006c:
            r3 = r15
            r20 = r16
            r23 = r24
            goto L_0x004e
        L_0x0072:
            r25 = r5
            if (r17 != 0) goto L_0x0154
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r3.mListAnchors
            r2 = r2[r63]
            r22 = 4
            if (r19 != 0) goto L_0x0080
            if (r15 == 0) goto L_0x0082
        L_0x0080:
            r22 = 1
        L_0x0082:
            int r24 = r2.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r2.mTarget
            if (r1 == 0) goto L_0x0094
            if (r3 == r13) goto L_0x0094
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r2.mTarget
            int r1 = r1.getMargin()
            int r24 = r24 + r1
        L_0x0094:
            r1 = r24
            if (r15 == 0) goto L_0x009f
            if (r3 == r13) goto L_0x009f
            if (r3 == r9) goto L_0x009f
            r22 = 6
            goto L_0x00a5
        L_0x009f:
            if (r23 == 0) goto L_0x00a5
            if (r19 == 0) goto L_0x00a5
            r22 = 4
        L_0x00a5:
            r28 = r22
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r2.mTarget
            if (r5 == 0) goto L_0x00d6
            if (r3 != r9) goto L_0x00bc
            android.support.constraint.solver.SolverVariable r5 = r2.mSolverVariable
            r30 = r4
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r2.mTarget
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            r31 = r6
            r6 = 5
            r10.addGreaterThan(r5, r4, r1, r6)
            goto L_0x00ca
        L_0x00bc:
            r30 = r4
            r31 = r6
            android.support.constraint.solver.SolverVariable r4 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r2.mTarget
            android.support.constraint.solver.SolverVariable r5 = r5.mSolverVariable
            r6 = 6
            r10.addGreaterThan(r4, r5, r1, r6)
        L_0x00ca:
            android.support.constraint.solver.SolverVariable r4 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r2.mTarget
            android.support.constraint.solver.SolverVariable r5 = r5.mSolverVariable
            r6 = r28
            r10.addEquality(r4, r5, r1, r6)
            goto L_0x00dc
        L_0x00d6:
            r30 = r4
            r31 = r6
            r6 = r28
        L_0x00dc:
            if (r19 == 0) goto L_0x011c
            int r4 = r3.getVisibility()
            r5 = 8
            if (r4 == r5) goto L_0x0106
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r4 = r3.mListDimensionBehaviors
            r4 = r4[r62]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r4 != r5) goto L_0x0106
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r3.mListAnchors
            int r5 = r63 + 1
            r4 = r4[r5]
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r5 = r3.mListAnchors
            r5 = r5[r63]
            android.support.constraint.solver.SolverVariable r5 = r5.mSolverVariable
            r32 = r1
            r33 = r2
            r1 = 0
            r2 = 5
            r10.addGreaterThan(r4, r5, r1, r2)
            goto L_0x010b
        L_0x0106:
            r32 = r1
            r33 = r2
            r1 = 0
        L_0x010b:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r3.mListAnchors
            r2 = r2[r63]
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r0.mListAnchors
            r4 = r4[r63]
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            r5 = 6
            r10.addGreaterThan(r2, r4, r1, r5)
            goto L_0x0120
        L_0x011c:
            r32 = r1
            r33 = r2
        L_0x0120:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r3.mListAnchors
            int r2 = r63 + 1
            r1 = r1[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mTarget
            if (r1 == 0) goto L_0x0140
            android.support.constraint.solver.widgets.ConstraintWidget r2 = r1.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r2.mListAnchors
            r4 = r4[r63]
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 == 0) goto L_0x013e
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r2.mListAnchors
            r4 = r4[r63]
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r4.mOwner
            if (r4 == r3) goto L_0x0141
        L_0x013e:
            r2 = 0
            goto L_0x0141
        L_0x0140:
            r2 = 0
        L_0x0141:
            r20 = r2
            if (r20 == 0) goto L_0x0149
            r2 = r20
            r3 = r2
            goto L_0x014c
        L_0x0149:
            r1 = 1
            r17 = r1
        L_0x014c:
            r5 = r25
            r4 = r30
            r6 = r31
            goto L_0x0072
        L_0x0154:
            r30 = r4
            r31 = r6
            if (r8 == 0) goto L_0x0180
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r14.mListAnchors
            int r2 = r63 + 1
            r1 = r1[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mTarget
            if (r1 == 0) goto L_0x0180
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r8.mListAnchors
            int r2 = r63 + 1
            r1 = r1[r2]
            android.support.constraint.solver.SolverVariable r2 = r1.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r14.mListAnchors
            int r5 = r63 + 1
            r4 = r4[r5]
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            int r5 = r1.getMargin()
            int r5 = -r5
            r6 = 5
            r10.addLowerThan(r2, r4, r5, r6)
            goto L_0x0181
        L_0x0180:
            r6 = 5
        L_0x0181:
            if (r19 == 0) goto L_0x01a1
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r0.mListAnchors
            int r2 = r63 + 1
            r1 = r1[r2]
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r14.mListAnchors
            int r4 = r63 + 1
            r2 = r2[r4]
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r14.mListAnchors
            int r5 = r63 + 1
            r4 = r4[r5]
            int r4 = r4.getMargin()
            r5 = 6
            r10.addGreaterThan(r1, r2, r4, r5)
        L_0x01a1:
            java.util.ArrayList<android.support.constraint.solver.widgets.ConstraintWidget> r5 = r12.mWeightedMatchConstraintsWidgets
            if (r5 == 0) goto L_0x0272
            int r1 = r5.size()
            r2 = 1
            if (r1 <= r2) goto L_0x0272
            r4 = 0
            r21 = 0
            boolean r2 = r12.mHasUndefinedWeights
            if (r2 == 0) goto L_0x01bc
            boolean r2 = r12.mHasComplexMatchWeights
            if (r2 != 0) goto L_0x01bc
            int r2 = r12.mWidgetsMatchCount
            float r2 = (float) r2
            r30 = r2
        L_0x01bc:
            r2 = 0
        L_0x01bd:
            if (r2 >= r1) goto L_0x0272
            java.lang.Object r22 = r5.get(r2)
            r6 = r22
            android.support.constraint.solver.widgets.ConstraintWidget r6 = (android.support.constraint.solver.widgets.ConstraintWidget) r6
            float[] r0 = r6.mWeight
            r0 = r0[r62]
            r22 = 0
            int r24 = (r0 > r22 ? 1 : (r0 == r22 ? 0 : -1))
            if (r24 >= 0) goto L_0x0200
            r43 = r0
            boolean r0 = r12.mHasComplexMatchWeights
            if (r0 == 0) goto L_0x01f5
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r6.mListAnchors
            int r22 = r63 + 1
            r0 = r0[r22]
            android.support.constraint.solver.SolverVariable r0 = r0.mSolverVariable
            r44 = r1
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r6.mListAnchors
            r1 = r1[r63]
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            r45 = r3
            r3 = 4
            r46 = r5
            r5 = 0
            r10.addEquality(r0, r1, r5, r3)
            r3 = 0
            r5 = 6
            goto L_0x0265
        L_0x01f5:
            r44 = r1
            r45 = r3
            r46 = r5
            r0 = 1065353216(0x3f800000, float:1.0)
            r43 = r0
            goto L_0x0208
        L_0x0200:
            r43 = r0
            r44 = r1
            r45 = r3
            r46 = r5
        L_0x0208:
            int r0 = (r43 > r22 ? 1 : (r43 == r22 ? 0 : -1))
            if (r0 != 0) goto L_0x0220
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r6.mListAnchors
            int r1 = r63 + 1
            r0 = r0[r1]
            android.support.constraint.solver.SolverVariable r0 = r0.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r6.mListAnchors
            r1 = r1[r63]
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            r3 = 0
            r5 = 6
            r10.addEquality(r0, r1, r3, r5)
            goto L_0x0265
        L_0x0220:
            r3 = 0
            r5 = 6
            if (r4 == 0) goto L_0x025d
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r4.mListAnchors
            r0 = r0[r63]
            android.support.constraint.solver.SolverVariable r0 = r0.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r4.mListAnchors
            int r18 = r63 + 1
            r1 = r1[r18]
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r6.mListAnchors
            r3 = r3[r63]
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r5 = r6.mListAnchors
            int r18 = r63 + 1
            r5 = r5[r18]
            android.support.constraint.solver.SolverVariable r5 = r5.mSolverVariable
            r48 = r4
            android.support.constraint.solver.ArrayRow r4 = r61.createRow()
            r35 = r4
            r36 = r21
            r37 = r30
            r38 = r43
            r39 = r0
            r40 = r1
            r41 = r3
            r42 = r5
            r35.createRowEqualMatchDimensions(r36, r37, r38, r39, r40, r41, r42)
            r10.addConstraint(r4)
            goto L_0x025f
        L_0x025d:
            r48 = r4
        L_0x025f:
            r0 = r6
            r1 = r43
            r4 = r0
            r21 = r1
        L_0x0265:
            int r2 = r2 + 1
            r1 = r44
            r3 = r45
            r5 = r46
            r0 = r60
            r6 = 5
            goto L_0x01bd
        L_0x0272:
            r45 = r3
            r46 = r5
            if (r9 == 0) goto L_0x0318
            if (r9 == r8) goto L_0x0287
            if (r15 == 0) goto L_0x027d
            goto L_0x0287
        L_0x027d:
            r35 = r7
            r0 = r8
            r10 = r9
            r28 = r45
            r32 = r46
            goto L_0x0320
        L_0x0287:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r13.mListAnchors
            r1 = r1[r63]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r14.mListAnchors
            int r3 = r63 + 1
            r2 = r2[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r13.mListAnchors
            r3 = r3[r63]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x02a2
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r13.mListAnchors
            r3 = r3[r63]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x02a3
        L_0x02a2:
            r3 = 0
        L_0x02a3:
            r18 = r3
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r14.mListAnchors
            int r4 = r63 + 1
            r3 = r3[r4]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x02ba
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r14.mListAnchors
            int r4 = r63 + 1
            r3 = r3[r4]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x02bb
        L_0x02ba:
            r3 = 0
        L_0x02bb:
            r21 = r3
            if (r9 != r8) goto L_0x02c9
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r9.mListAnchors
            r1 = r3[r63]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r9.mListAnchors
            int r4 = r63 + 1
            r2 = r3[r4]
        L_0x02c9:
            r6 = r1
            r5 = r2
            if (r18 == 0) goto L_0x030b
            if (r21 == 0) goto L_0x030b
            r1 = 1056964608(0x3f000000, float:0.5)
            if (r62 != 0) goto L_0x02d8
            float r1 = r7.mHorizontalBiasPercent
        L_0x02d5:
            r22 = r1
            goto L_0x02db
        L_0x02d8:
            float r1 = r7.mVerticalBiasPercent
            goto L_0x02d5
        L_0x02db:
            int r24 = r6.getMargin()
            int r26 = r5.getMargin()
            android.support.constraint.solver.SolverVariable r2 = r6.mSolverVariable
            android.support.constraint.solver.SolverVariable r4 = r5.mSolverVariable
            r27 = 5
            r1 = r61
            r28 = r45
            r3 = r18
            r29 = r4
            r4 = r24
            r33 = r5
            r32 = r46
            r5 = r22
            r34 = r6
            r6 = r21
            r35 = r7
            r7 = r29
            r0 = r8
            r8 = r26
            r10 = r9
            r9 = r27
            r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9)
            goto L_0x0313
        L_0x030b:
            r35 = r7
            r0 = r8
            r10 = r9
            r28 = r45
            r32 = r46
        L_0x0313:
            r9 = r10
            r59 = r14
            goto L_0x046c
        L_0x0318:
            r35 = r7
            r0 = r8
            r10 = r9
            r28 = r45
            r32 = r46
        L_0x0320:
            if (r23 == 0) goto L_0x0470
            if (r10 == 0) goto L_0x0470
            r1 = r10
            r2 = r10
            int r3 = r12.mWidgetsMatchCount
            if (r3 <= 0) goto L_0x0333
            int r3 = r12.mWidgetsCount
            int r4 = r12.mWidgetsMatchCount
            if (r3 != r4) goto L_0x0333
            r47 = 1
            goto L_0x0335
        L_0x0333:
            r47 = 0
        L_0x0335:
            r9 = r1
            r8 = r2
        L_0x0337:
            if (r9 == 0) goto L_0x0463
            android.support.constraint.solver.widgets.ConstraintWidget[] r1 = r9.mNextChainWidget
            r1 = r1[r62]
            r7 = r1
        L_0x033e:
            if (r7 == 0) goto L_0x034d
            int r1 = r7.getVisibility()
            r2 = 8
            if (r1 != r2) goto L_0x034f
            android.support.constraint.solver.widgets.ConstraintWidget[] r1 = r7.mNextChainWidget
            r7 = r1[r62]
            goto L_0x033e
        L_0x034d:
            r2 = 8
        L_0x034f:
            if (r7 != 0) goto L_0x035f
            if (r9 != r0) goto L_0x0354
            goto L_0x035f
        L_0x0354:
            r34 = r7
            r36 = r8
            r37 = r9
            r53 = r14
            r14 = r2
            goto L_0x044f
        L_0x035f:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r9.mListAnchors
            r6 = r1[r63]
            android.support.constraint.solver.SolverVariable r5 = r6.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r6.mTarget
            if (r1 == 0) goto L_0x036e
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r6.mTarget
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            goto L_0x036f
        L_0x036e:
            r1 = 0
        L_0x036f:
            if (r8 == r9) goto L_0x037c
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r8.mListAnchors
            int r4 = r63 + 1
            r3 = r3[r4]
            android.support.constraint.solver.SolverVariable r1 = r3.mSolverVariable
        L_0x0379:
            r18 = r1
            goto L_0x0394
        L_0x037c:
            if (r9 != r10) goto L_0x0379
            if (r8 != r9) goto L_0x0379
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r13.mListAnchors
            r3 = r3[r63]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x0391
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r13.mListAnchors
            r3 = r3[r63]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x0392
        L_0x0391:
            r3 = 0
        L_0x0392:
            r1 = r3
            goto L_0x0379
        L_0x0394:
            r1 = 0
            r3 = 0
            r4 = 0
            int r20 = r6.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r9.mListAnchors
            int r21 = r63 + 1
            r2 = r2[r21]
            int r2 = r2.getMargin()
            if (r7 == 0) goto L_0x03be
            r50 = r1
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r7.mListAnchors
            r1 = r1[r63]
            android.support.constraint.solver.SolverVariable r3 = r1.mSolverVariable
            r51 = r1
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r9.mListAnchors
            int r21 = r63 + 1
            r1 = r1[r21]
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            r22 = r1
            r21 = r3
            goto L_0x03dc
        L_0x03be:
            r50 = r1
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r14.mListAnchors
            int r21 = r63 + 1
            r1 = r1[r21]
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mTarget
            if (r1 == 0) goto L_0x03cc
            android.support.constraint.solver.SolverVariable r3 = r1.mSolverVariable
        L_0x03cc:
            r52 = r1
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r9.mListAnchors
            int r21 = r63 + 1
            r1 = r1[r21]
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            r22 = r1
            r21 = r3
            r51 = r52
        L_0x03dc:
            if (r51 == 0) goto L_0x03e3
            int r1 = r51.getMargin()
            int r2 = r2 + r1
        L_0x03e3:
            r24 = r2
            if (r8 == 0) goto L_0x03f3
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r8.mListAnchors
            int r2 = r63 + 1
            r1 = r1[r2]
            int r1 = r1.getMargin()
            int r20 = r20 + r1
        L_0x03f3:
            if (r5 == 0) goto L_0x0445
            if (r18 == 0) goto L_0x0445
            if (r21 == 0) goto L_0x0445
            if (r22 == 0) goto L_0x0445
            r1 = r20
            if (r9 != r10) goto L_0x0407
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r10.mListAnchors
            r2 = r2[r63]
            int r1 = r2.getMargin()
        L_0x0407:
            r26 = r1
            r1 = r24
            if (r9 != r0) goto L_0x0417
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r0.mListAnchors
            int r3 = r63 + 1
            r2 = r2[r3]
            int r1 = r2.getMargin()
        L_0x0417:
            r27 = r1
            r1 = 4
            if (r47 == 0) goto L_0x041d
            r1 = 6
        L_0x041d:
            r28 = r1
            r29 = 1056964608(0x3f000000, float:0.5)
            r1 = r61
            r4 = 8
            r2 = r5
            r3 = r18
            r53 = r14
            r14 = r4
            r4 = r26
            r33 = r5
            r5 = r29
            r29 = r6
            r6 = r21
            r34 = r7
            r7 = r22
            r36 = r8
            r8 = r27
            r37 = r9
            r9 = r28
            r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9)
            goto L_0x044f
        L_0x0445:
            r34 = r7
            r36 = r8
            r37 = r9
            r53 = r14
            r14 = 8
        L_0x044f:
            int r1 = r37.getVisibility()
            if (r1 == r14) goto L_0x0459
            r1 = r37
            r8 = r1
            goto L_0x045b
        L_0x0459:
            r8 = r36
        L_0x045b:
            r9 = r34
            r20 = r34
            r14 = r53
            goto L_0x0337
        L_0x0463:
            r37 = r9
            r53 = r14
            r9 = r10
            r28 = r37
            r59 = r53
        L_0x046c:
            r10 = r61
            goto L_0x0614
        L_0x0470:
            r53 = r14
            r14 = 8
            if (r16 == 0) goto L_0x060f
            if (r10 == 0) goto L_0x060f
            r1 = r10
            r2 = r10
            int r3 = r12.mWidgetsMatchCount
            if (r3 <= 0) goto L_0x0487
            int r3 = r12.mWidgetsCount
            int r4 = r12.mWidgetsMatchCount
            if (r3 != r4) goto L_0x0487
            r47 = 1
            goto L_0x0489
        L_0x0487:
            r47 = 0
        L_0x0489:
            r9 = r1
            r8 = r2
        L_0x048b:
            if (r9 == 0) goto L_0x0571
            android.support.constraint.solver.widgets.ConstraintWidget[] r1 = r9.mNextChainWidget
            r1 = r1[r62]
        L_0x0491:
            if (r1 == 0) goto L_0x049e
            int r2 = r1.getVisibility()
            if (r2 != r14) goto L_0x049e
            android.support.constraint.solver.widgets.ConstraintWidget[] r2 = r1.mNextChainWidget
            r1 = r2[r62]
            goto L_0x0491
        L_0x049e:
            if (r9 == r10) goto L_0x0557
            if (r9 == r0) goto L_0x0557
            if (r1 == 0) goto L_0x0557
            if (r1 != r0) goto L_0x04a7
            r1 = 0
        L_0x04a7:
            r7 = r1
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r9.mListAnchors
            r6 = r1[r63]
            android.support.constraint.solver.SolverVariable r5 = r6.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r6.mTarget
            if (r1 == 0) goto L_0x04b7
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r6.mTarget
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            goto L_0x04b8
        L_0x04b7:
            r1 = 0
        L_0x04b8:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r8.mListAnchors
            int r3 = r63 + 1
            r2 = r2[r3]
            android.support.constraint.solver.SolverVariable r4 = r2.mSolverVariable
            r1 = 0
            r2 = 0
            r3 = 0
            int r18 = r6.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor[] r14 = r9.mListAnchors
            int r20 = r63 + 1
            r14 = r14[r20]
            int r14 = r14.getMargin()
            if (r7 == 0) goto L_0x04ec
            r54 = r1
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r7.mListAnchors
            r1 = r1[r63]
            android.support.constraint.solver.SolverVariable r2 = r1.mSolverVariable
            r55 = r2
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r1.mTarget
            if (r2 == 0) goto L_0x04e6
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r1.mTarget
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            goto L_0x04e7
        L_0x04e6:
            r2 = 0
        L_0x04e7:
            r56 = r1
            r20 = r2
            goto L_0x0508
        L_0x04ec:
            r54 = r1
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r9.mListAnchors
            int r20 = r63 + 1
            r1 = r1[r20]
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mTarget
            if (r1 == 0) goto L_0x04fa
            android.support.constraint.solver.SolverVariable r2 = r1.mSolverVariable
        L_0x04fa:
            r56 = r1
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r9.mListAnchors
            int r20 = r63 + 1
            r1 = r1[r20]
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            r20 = r1
            r55 = r2
        L_0x0508:
            if (r56 == 0) goto L_0x050f
            int r1 = r56.getMargin()
            int r14 = r14 + r1
        L_0x050f:
            if (r8 == 0) goto L_0x051d
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r8.mListAnchors
            int r2 = r63 + 1
            r1 = r1[r2]
            int r1 = r1.getMargin()
            int r18 = r18 + r1
        L_0x051d:
            r1 = 4
            if (r47 == 0) goto L_0x0521
            r1 = 6
        L_0x0521:
            r21 = r1
            if (r5 == 0) goto L_0x054d
            if (r4 == 0) goto L_0x054d
            if (r55 == 0) goto L_0x054d
            if (r20 == 0) goto L_0x054d
            r22 = 1056964608(0x3f000000, float:0.5)
            r1 = r61
            r2 = r5
            r3 = r4
            r24 = r4
            r4 = r18
            r26 = r5
            r11 = 5
            r5 = r22
            r22 = r6
            r6 = r55
            r27 = r7
            r7 = r20
            r28 = r8
            r8 = r14
            r29 = r9
            r9 = r21
            r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9)
            goto L_0x0554
        L_0x054d:
            r27 = r7
            r28 = r8
            r29 = r9
            r11 = 5
        L_0x0554:
            r20 = r27
            goto L_0x055e
        L_0x0557:
            r28 = r8
            r29 = r9
            r11 = 5
            r20 = r1
        L_0x055e:
            int r1 = r29.getVisibility()
            r2 = 8
            if (r1 == r2) goto L_0x056a
            r1 = r29
            r8 = r1
            goto L_0x056c
        L_0x056a:
            r8 = r28
        L_0x056c:
            r9 = r20
            r14 = r2
            goto L_0x048b
        L_0x0571:
            r28 = r8
            r29 = r9
            r11 = 5
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r10.mListAnchors
            r14 = r1[r63]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r13.mListAnchors
            r1 = r1[r63]
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r1.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r0.mListAnchors
            int r2 = r63 + 1
            r8 = r1[r2]
            r7 = r53
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r7.mListAnchors
            int r2 = r63 + 1
            r1 = r1[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r1.mTarget
            if (r9 == 0) goto L_0x05e9
            if (r10 == r0) goto L_0x05ac
            android.support.constraint.solver.SolverVariable r1 = r14.mSolverVariable
            android.support.constraint.solver.SolverVariable r2 = r9.mSolverVariable
            int r3 = r14.getMargin()
            r5 = r10
            r10 = r61
            r10.addEquality(r1, r2, r3, r11)
            r57 = r5
            r58 = r6
            r59 = r7
            r11 = r8
            r18 = r9
            goto L_0x05f4
        L_0x05ac:
            r5 = r10
            r10 = r61
            if (r6 == 0) goto L_0x05df
            android.support.constraint.solver.SolverVariable r2 = r14.mSolverVariable
            android.support.constraint.solver.SolverVariable r3 = r9.mSolverVariable
            int r4 = r14.getMargin()
            r18 = 1056964608(0x3f000000, float:0.5)
            android.support.constraint.solver.SolverVariable r1 = r8.mSolverVariable
            android.support.constraint.solver.SolverVariable r11 = r6.mSolverVariable
            int r21 = r8.getMargin()
            r22 = 5
            r24 = r1
            r1 = r61
            r57 = r5
            r5 = r18
            r58 = r6
            r6 = r24
            r59 = r7
            r7 = r11
            r11 = r8
            r8 = r21
            r18 = r9
            r9 = r22
            r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9)
            goto L_0x05f4
        L_0x05df:
            r57 = r5
            r58 = r6
            r59 = r7
            r11 = r8
            r18 = r9
            goto L_0x05f4
        L_0x05e9:
            r58 = r6
            r59 = r7
            r11 = r8
            r18 = r9
            r57 = r10
            r10 = r61
        L_0x05f4:
            r1 = r58
            if (r1 == 0) goto L_0x060a
            r9 = r57
            if (r9 == r0) goto L_0x060c
            android.support.constraint.solver.SolverVariable r2 = r11.mSolverVariable
            android.support.constraint.solver.SolverVariable r3 = r1.mSolverVariable
            int r4 = r11.getMargin()
            int r4 = -r4
            r5 = 5
            r10.addEquality(r2, r3, r4, r5)
            goto L_0x060c
        L_0x060a:
            r9 = r57
        L_0x060c:
            r28 = r29
            goto L_0x0614
        L_0x060f:
            r9 = r10
            r59 = r53
            r10 = r61
        L_0x0614:
            if (r23 != 0) goto L_0x061f
            if (r16 == 0) goto L_0x0619
            goto L_0x061f
        L_0x0619:
            r33 = r9
            r14 = r59
            goto L_0x06a8
        L_0x061f:
            if (r9 == 0) goto L_0x06a4
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r9.mListAnchors
            r1 = r1[r63]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r0.mListAnchors
            int r3 = r63 + 1
            r2 = r2[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r1.mTarget
            if (r3 == 0) goto L_0x0634
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r1.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x0635
        L_0x0634:
            r3 = 0
        L_0x0635:
            r11 = r3
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r2.mTarget
            if (r3 == 0) goto L_0x063f
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r2.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x0640
        L_0x063f:
            r3 = 0
        L_0x0640:
            r14 = r59
            if (r14 == r0) goto L_0x0659
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r14.mListAnchors
            int r5 = r63 + 1
            r4 = r4[r5]
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r4.mTarget
            if (r5 == 0) goto L_0x0655
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r4.mTarget
            android.support.constraint.solver.SolverVariable r5 = r5.mSolverVariable
            r49 = r5
            goto L_0x0657
        L_0x0655:
            r49 = 0
        L_0x0657:
            r3 = r49
        L_0x0659:
            r18 = r3
            if (r9 != r0) goto L_0x0667
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r9.mListAnchors
            r1 = r3[r63]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r9.mListAnchors
            int r4 = r63 + 1
            r2 = r3[r4]
        L_0x0667:
            r8 = r1
            r7 = r2
            if (r11 == 0) goto L_0x06a1
            if (r18 == 0) goto L_0x06a1
            r21 = 1056964608(0x3f000000, float:0.5)
            int r22 = r8.getMargin()
            if (r0 != 0) goto L_0x0676
            r0 = r14
        L_0x0676:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r0.mListAnchors
            int r2 = r63 + 1
            r1 = r1[r2]
            int r24 = r1.getMargin()
            android.support.constraint.solver.SolverVariable r2 = r8.mSolverVariable
            android.support.constraint.solver.SolverVariable r6 = r7.mSolverVariable
            r26 = 5
            r1 = r61
            r3 = r11
            r4 = r22
            r5 = r21
            r27 = r6
            r6 = r18
            r29 = r7
            r7 = r27
            r27 = r8
            r8 = r24
            r33 = r9
            r9 = r26
            r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9)
            goto L_0x06a8
        L_0x06a1:
            r33 = r9
            goto L_0x06a8
        L_0x06a4:
            r33 = r9
            r14 = r59
        L_0x06a8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.Chain.applyChainConstraints(android.support.constraint.solver.widgets.ConstraintWidgetContainer, android.support.constraint.solver.LinearSystem, int, int, android.support.constraint.solver.widgets.ChainHead):void");
    }
}
