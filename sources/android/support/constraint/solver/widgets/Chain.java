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

    /* JADX WARNING: Removed duplicated region for block: B:286:0x0634 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:290:0x0646  */
    /* JADX WARNING: Removed duplicated region for block: B:291:0x064b  */
    /* JADX WARNING: Removed duplicated region for block: B:294:0x0652  */
    /* JADX WARNING: Removed duplicated region for block: B:295:0x0657  */
    /* JADX WARNING: Removed duplicated region for block: B:297:0x065a  */
    /* JADX WARNING: Removed duplicated region for block: B:302:0x066e  */
    /* JADX WARNING: Removed duplicated region for block: B:304:0x0672  */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x067e  */
    /* JADX WARNING: Removed duplicated region for block: B:307:0x0681 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void applyChainConstraints(android.support.constraint.solver.widgets.ConstraintWidgetContainer r44, android.support.constraint.solver.LinearSystem r45, int r46, int r47, android.support.constraint.solver.widgets.ChainHead r48) {
        /*
            r0 = r44
            r10 = r45
            r11 = r48
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
            r5 = r5[r46]
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
            if (r46 != 0) goto L_0x0052
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
            r3 = 5
            if (r21 != 0) goto L_0x0159
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r6.mListAnchors
            r2 = r2[r47]
            r24 = 4
            if (r18 != 0) goto L_0x0082
            if (r23 == 0) goto L_0x0084
        L_0x0082:
            r24 = 1
        L_0x0084:
            int r25 = r2.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r2.mTarget
            if (r5 == 0) goto L_0x0099
            if (r6 == r12) goto L_0x0099
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r2.mTarget
            int r5 = r5.getMargin()
            int r25 = r25 + r5
            r5 = r25
            goto L_0x009b
        L_0x0099:
            r5 = r25
        L_0x009b:
            if (r23 == 0) goto L_0x00a6
            if (r6 == r12) goto L_0x00a6
            if (r6 == r14) goto L_0x00a6
            r24 = 6
            r27 = r24
            goto L_0x00b1
        L_0x00a6:
            if (r22 == 0) goto L_0x00af
            if (r18 == 0) goto L_0x00af
            r24 = 4
            r27 = r24
            goto L_0x00b1
        L_0x00af:
            r27 = r24
        L_0x00b1:
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r2.mTarget
            if (r1 == 0) goto L_0x00db
            if (r6 != r14) goto L_0x00c3
            android.support.constraint.solver.SolverVariable r1 = r2.mSolverVariable
            r25 = r4
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r2.mTarget
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            r10.addGreaterThan(r1, r4, r5, r3)
            goto L_0x00cf
        L_0x00c3:
            r25 = r4
            android.support.constraint.solver.SolverVariable r1 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r2.mTarget
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            r3 = 6
            r10.addGreaterThan(r1, r4, r5, r3)
        L_0x00cf:
            android.support.constraint.solver.SolverVariable r1 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r2.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            r4 = r27
            r10.addEquality(r1, r3, r5, r4)
            goto L_0x00df
        L_0x00db:
            r25 = r4
            r4 = r27
        L_0x00df:
            if (r18 == 0) goto L_0x011f
            int r1 = r6.getVisibility()
            r3 = 8
            if (r1 == r3) goto L_0x0109
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r6.mListDimensionBehaviors
            r1 = r1[r46]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r1 != r3) goto L_0x0109
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r6.mListAnchors
            int r3 = r47 + 1
            r1 = r1[r3]
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r6.mListAnchors
            r3 = r3[r47]
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            r26 = r2
            r27 = r4
            r2 = 5
            r4 = 0
            r10.addGreaterThan(r1, r3, r4, r2)
            goto L_0x010d
        L_0x0109:
            r26 = r2
            r27 = r4
        L_0x010d:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r6.mListAnchors
            r1 = r1[r47]
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r0.mListAnchors
            r2 = r2[r47]
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            r3 = 6
            r4 = 0
            r10.addGreaterThan(r1, r2, r4, r3)
            goto L_0x0123
        L_0x011f:
            r26 = r2
            r27 = r4
        L_0x0123:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r6.mListAnchors
            int r2 = r47 + 1
            r1 = r1[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mTarget
            if (r1 == 0) goto L_0x0149
            android.support.constraint.solver.widgets.ConstraintWidget r2 = r1.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r2.mListAnchors
            r3 = r3[r47]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x0145
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r2.mListAnchors
            r3 = r3[r47]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r3.mOwner
            if (r3 == r6) goto L_0x0142
            goto L_0x0145
        L_0x0142:
            r19 = r2
            goto L_0x014c
        L_0x0145:
            r2 = 0
            r19 = r2
            goto L_0x014c
        L_0x0149:
            r2 = 0
            r19 = r2
        L_0x014c:
            if (r19 == 0) goto L_0x0152
            r2 = r19
            r6 = r2
            goto L_0x0155
        L_0x0152:
            r2 = 1
            r21 = r2
        L_0x0155:
            r4 = r25
            goto L_0x0075
        L_0x0159:
            r25 = r4
            if (r15 == 0) goto L_0x0183
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r13.mListAnchors
            int r2 = r47 + 1
            r1 = r1[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mTarget
            if (r1 == 0) goto L_0x0183
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r15.mListAnchors
            int r2 = r47 + 1
            r1 = r1[r2]
            android.support.constraint.solver.SolverVariable r2 = r1.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r13.mListAnchors
            int r4 = r47 + 1
            r3 = r3[r4]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            int r4 = r1.getMargin()
            int r4 = -r4
            r5 = 5
            r10.addLowerThan(r2, r3, r4, r5)
            goto L_0x0184
        L_0x0183:
            r5 = 5
        L_0x0184:
            if (r18 == 0) goto L_0x01a4
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r0.mListAnchors
            int r2 = r47 + 1
            r1 = r1[r2]
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r13.mListAnchors
            int r3 = r47 + 1
            r2 = r2[r3]
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r13.mListAnchors
            int r4 = r47 + 1
            r3 = r3[r4]
            int r3 = r3.getMargin()
            r4 = 6
            r10.addGreaterThan(r1, r2, r3, r4)
        L_0x01a4:
            java.util.ArrayList<android.support.constraint.solver.widgets.ConstraintWidget> r4 = r11.mWeightedMatchConstraintsWidgets
            if (r4 == 0) goto L_0x0299
            int r1 = r4.size()
            r2 = 1
            if (r1 <= r2) goto L_0x028e
            r3 = 0
            r20 = 0
            boolean r2 = r11.mHasUndefinedWeights
            if (r2 == 0) goto L_0x01be
            boolean r2 = r11.mHasComplexMatchWeights
            if (r2 != 0) goto L_0x01be
            int r2 = r11.mWidgetsMatchCount
            float r2 = (float) r2
            goto L_0x01c0
        L_0x01be:
            r2 = r25
        L_0x01c0:
            r25 = 0
            r5 = r25
        L_0x01c4:
            if (r5 >= r1) goto L_0x027f
            java.lang.Object r25 = r4.get(r5)
            r0 = r25
            android.support.constraint.solver.widgets.ConstraintWidget r0 = (android.support.constraint.solver.widgets.ConstraintWidget) r0
            r37 = r1
            float[] r1 = r0.mWeight
            r1 = r1[r46]
            r25 = 0
            int r28 = (r1 > r25 ? 1 : (r1 == r25 ? 0 : -1))
            if (r28 >= 0) goto L_0x0208
            r28 = r1
            boolean r1 = r11.mHasComplexMatchWeights
            if (r1 == 0) goto L_0x01ff
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r0.mListAnchors
            int r25 = r47 + 1
            r1 = r1[r25]
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            r38 = r4
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r0.mListAnchors
            r4 = r4[r47]
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            r39 = r6
            r6 = 4
            r40 = r7
            r7 = 0
            r10.addEquality(r1, r4, r7, r6)
            r17 = r8
            r7 = 6
            r8 = 0
            goto L_0x026f
        L_0x01ff:
            r38 = r4
            r39 = r6
            r40 = r7
            r1 = 1065353216(0x3f800000, float:1.0)
            goto L_0x0210
        L_0x0208:
            r28 = r1
            r38 = r4
            r39 = r6
            r40 = r7
        L_0x0210:
            int r4 = (r1 > r25 ? 1 : (r1 == r25 ? 0 : -1))
            if (r4 != 0) goto L_0x022a
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r0.mListAnchors
            int r6 = r47 + 1
            r4 = r4[r6]
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r0.mListAnchors
            r6 = r6[r47]
            android.support.constraint.solver.SolverVariable r6 = r6.mSolverVariable
            r17 = r8
            r7 = 6
            r8 = 0
            r10.addEquality(r4, r6, r8, r7)
            goto L_0x026f
        L_0x022a:
            r17 = r8
            r7 = 6
            r8 = 0
            if (r3 == 0) goto L_0x0269
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r3.mListAnchors
            r4 = r4[r47]
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r3.mListAnchors
            int r24 = r47 + 1
            r6 = r6[r24]
            android.support.constraint.solver.SolverVariable r6 = r6.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r7 = r0.mListAnchors
            r7 = r7[r47]
            android.support.constraint.solver.SolverVariable r7 = r7.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r8 = r0.mListAnchors
            int r25 = r47 + 1
            r8 = r8[r25]
            android.support.constraint.solver.SolverVariable r8 = r8.mSolverVariable
            r25 = r3
            android.support.constraint.solver.ArrayRow r3 = r45.createRow()
            r28 = r3
            r29 = r20
            r30 = r2
            r31 = r1
            r32 = r4
            r33 = r6
            r34 = r7
            r35 = r8
            r28.createRowEqualMatchDimensions(r29, r30, r31, r32, r33, r34, r35)
            r10.addConstraint(r3)
            goto L_0x026b
        L_0x0269:
            r25 = r3
        L_0x026b:
            r3 = r0
            r4 = r1
            r20 = r4
        L_0x026f:
            int r5 = r5 + 1
            r0 = r44
            r8 = r17
            r1 = r37
            r4 = r38
            r6 = r39
            r7 = r40
            goto L_0x01c4
        L_0x027f:
            r37 = r1
            r25 = r3
            r38 = r4
            r39 = r6
            r40 = r7
            r17 = r8
            r25 = r2
            goto L_0x02a1
        L_0x028e:
            r37 = r1
            r38 = r4
            r39 = r6
            r40 = r7
            r17 = r8
            goto L_0x02a1
        L_0x0299:
            r38 = r4
            r39 = r6
            r40 = r7
            r17 = r8
        L_0x02a1:
            if (r14 == 0) goto L_0x0348
            if (r14 == r15) goto L_0x02b2
            if (r23 == 0) goto L_0x02a8
            goto L_0x02b2
        L_0x02a8:
            r35 = r9
            r30 = r38
            r31 = r39
            r33 = r40
            goto L_0x0350
        L_0x02b2:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r12.mListAnchors
            r1 = r1[r47]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r13.mListAnchors
            int r3 = r47 + 1
            r2 = r2[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r12.mListAnchors
            r3 = r3[r47]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x02cd
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r12.mListAnchors
            r3 = r3[r47]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x02ce
        L_0x02cd:
            r3 = 0
        L_0x02ce:
            r20 = r3
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r13.mListAnchors
            int r4 = r47 + 1
            r3 = r3[r4]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x02e5
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r13.mListAnchors
            int r4 = r47 + 1
            r3 = r3[r4]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x02e6
        L_0x02e5:
            r3 = 0
        L_0x02e6:
            r24 = r3
            if (r14 != r15) goto L_0x02f7
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r14.mListAnchors
            r1 = r3[r47]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r14.mListAnchors
            int r4 = r47 + 1
            r2 = r3[r4]
            r8 = r1
            r7 = r2
            goto L_0x02f9
        L_0x02f7:
            r8 = r1
            r7 = r2
        L_0x02f9:
            if (r20 == 0) goto L_0x033a
            if (r24 == 0) goto L_0x033a
            r1 = 1056964608(0x3f000000, float:0.5)
            if (r46 != 0) goto L_0x0306
            float r1 = r9.mHorizontalBiasPercent
            r26 = r1
            goto L_0x030a
        L_0x0306:
            float r1 = r9.mVerticalBiasPercent
            r26 = r1
        L_0x030a:
            int r27 = r8.getMargin()
            int r28 = r7.getMargin()
            android.support.constraint.solver.SolverVariable r2 = r8.mSolverVariable
            android.support.constraint.solver.SolverVariable r6 = r7.mSolverVariable
            r29 = 5
            r1 = r45
            r3 = r20
            r30 = r38
            r4 = r27
            r5 = r26
            r32 = r6
            r31 = r39
            r6 = r24
            r34 = r7
            r33 = r40
            r7 = r32
            r32 = r8
            r8 = r28
            r35 = r9
            r9 = r29
            r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9)
            goto L_0x0346
        L_0x033a:
            r34 = r7
            r32 = r8
            r35 = r9
            r30 = r38
            r31 = r39
            r33 = r40
        L_0x0346:
            goto L_0x0632
        L_0x0348:
            r35 = r9
            r30 = r38
            r31 = r39
            r33 = r40
        L_0x0350:
            if (r22 == 0) goto L_0x049e
            if (r14 == 0) goto L_0x049e
            r1 = r14
            r2 = r14
            int r3 = r11.mWidgetsMatchCount
            if (r3 <= 0) goto L_0x0363
            int r3 = r11.mWidgetsCount
            int r4 = r11.mWidgetsMatchCount
            if (r3 != r4) goto L_0x0363
            r27 = 1
            goto L_0x0365
        L_0x0363:
            r27 = 0
        L_0x0365:
            r20 = r27
            r8 = r1
            r9 = r2
        L_0x0369:
            if (r8 == 0) goto L_0x0496
            android.support.constraint.solver.widgets.ConstraintWidget[] r1 = r8.mNextChainWidget
            r1 = r1[r46]
            r7 = r1
        L_0x0370:
            if (r7 == 0) goto L_0x037f
            int r1 = r7.getVisibility()
            r5 = 8
            if (r1 != r5) goto L_0x0381
            android.support.constraint.solver.widgets.ConstraintWidget[] r1 = r7.mNextChainWidget
            r7 = r1[r46]
            goto L_0x0370
        L_0x037f:
            r5 = 8
        L_0x0381:
            if (r7 != 0) goto L_0x038f
            if (r8 != r15) goto L_0x0386
            goto L_0x038f
        L_0x0386:
            r0 = r5
            r39 = r7
            r40 = r8
            r41 = r9
            goto L_0x0484
        L_0x038f:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r8.mListAnchors
            r6 = r1[r47]
            android.support.constraint.solver.SolverVariable r4 = r6.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r6.mTarget
            if (r1 == 0) goto L_0x039e
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r6.mTarget
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            goto L_0x039f
        L_0x039e:
            r1 = 0
        L_0x039f:
            if (r9 == r8) goto L_0x03ac
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r9.mListAnchors
            int r3 = r47 + 1
            r2 = r2[r3]
            android.support.constraint.solver.SolverVariable r1 = r2.mSolverVariable
            r19 = r1
            goto L_0x03c8
        L_0x03ac:
            if (r8 != r14) goto L_0x03c6
            if (r9 != r8) goto L_0x03c6
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r12.mListAnchors
            r2 = r2[r47]
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x03c1
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r12.mListAnchors
            r2 = r2[r47]
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            goto L_0x03c2
        L_0x03c1:
            r2 = 0
        L_0x03c2:
            r1 = r2
            r19 = r1
            goto L_0x03c8
        L_0x03c6:
            r19 = r1
        L_0x03c8:
            r1 = 0
            r2 = 0
            r3 = 0
            int r24 = r6.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r8.mListAnchors
            int r27 = r47 + 1
            r0 = r0[r27]
            int r0 = r0.getMargin()
            if (r7 == 0) goto L_0x03f0
            android.support.constraint.solver.widgets.ConstraintAnchor[] r5 = r7.mListAnchors
            r1 = r5[r47]
            android.support.constraint.solver.SolverVariable r2 = r1.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r5 = r8.mListAnchors
            int r28 = r47 + 1
            r5 = r5[r28]
            android.support.constraint.solver.SolverVariable r3 = r5.mSolverVariable
            r28 = r1
            r29 = r2
            r31 = r3
            goto L_0x040a
        L_0x03f0:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r5 = r13.mListAnchors
            int r28 = r47 + 1
            r5 = r5[r28]
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r5.mTarget
            if (r1 == 0) goto L_0x03fc
            android.support.constraint.solver.SolverVariable r2 = r1.mSolverVariable
        L_0x03fc:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r5 = r8.mListAnchors
            int r28 = r47 + 1
            r5 = r5[r28]
            android.support.constraint.solver.SolverVariable r3 = r5.mSolverVariable
            r28 = r1
            r29 = r2
            r31 = r3
        L_0x040a:
            if (r28 == 0) goto L_0x0411
            int r1 = r28.getMargin()
            int r0 = r0 + r1
        L_0x0411:
            if (r9 == 0) goto L_0x041f
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r9.mListAnchors
            int r2 = r47 + 1
            r1 = r1[r2]
            int r1 = r1.getMargin()
            int r24 = r24 + r1
        L_0x041f:
            if (r4 == 0) goto L_0x0476
            if (r19 == 0) goto L_0x0476
            if (r29 == 0) goto L_0x0476
            if (r31 == 0) goto L_0x0476
            r1 = r24
            if (r8 != r14) goto L_0x0436
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r14.mListAnchors
            r2 = r2[r47]
            int r1 = r2.getMargin()
            r32 = r1
            goto L_0x0438
        L_0x0436:
            r32 = r1
        L_0x0438:
            r1 = r0
            if (r8 != r15) goto L_0x0448
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r15.mListAnchors
            int r3 = r47 + 1
            r2 = r2[r3]
            int r1 = r2.getMargin()
            r34 = r1
            goto L_0x044a
        L_0x0448:
            r34 = r1
        L_0x044a:
            r1 = 4
            if (r20 == 0) goto L_0x0451
            r1 = 6
            r36 = r1
            goto L_0x0453
        L_0x0451:
            r36 = r1
        L_0x0453:
            r5 = 1056964608(0x3f000000, float:0.5)
            r1 = r45
            r2 = r4
            r3 = r19
            r37 = r4
            r4 = r32
            r38 = r0
            r0 = 8
            r27 = r6
            r6 = r29
            r39 = r7
            r7 = r31
            r40 = r8
            r8 = r34
            r41 = r9
            r9 = r36
            r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9)
            goto L_0x0484
        L_0x0476:
            r38 = r0
            r37 = r4
            r27 = r6
            r39 = r7
            r40 = r8
            r41 = r9
            r0 = 8
        L_0x0484:
            int r1 = r40.getVisibility()
            if (r1 == r0) goto L_0x048e
            r1 = r40
            r9 = r1
            goto L_0x0490
        L_0x048e:
            r9 = r41
        L_0x0490:
            r8 = r39
            r19 = r39
            goto L_0x0369
        L_0x0496:
            r40 = r8
            r41 = r9
            r31 = r40
            goto L_0x0632
        L_0x049e:
            r0 = 8
            if (r16 == 0) goto L_0x0632
            if (r14 == 0) goto L_0x0632
            r1 = r14
            r2 = r14
            int r3 = r11.mWidgetsMatchCount
            if (r3 <= 0) goto L_0x04b3
            int r3 = r11.mWidgetsCount
            int r4 = r11.mWidgetsMatchCount
            if (r3 != r4) goto L_0x04b3
            r27 = 1
            goto L_0x04b5
        L_0x04b3:
            r27 = 0
        L_0x04b5:
            r20 = r27
            r8 = r1
            r9 = r2
        L_0x04b9:
            if (r8 == 0) goto L_0x05a9
            android.support.constraint.solver.widgets.ConstraintWidget[] r1 = r8.mNextChainWidget
            r1 = r1[r46]
        L_0x04bf:
            if (r1 == 0) goto L_0x04cc
            int r2 = r1.getVisibility()
            if (r2 != r0) goto L_0x04cc
            android.support.constraint.solver.widgets.ConstraintWidget[] r2 = r1.mNextChainWidget
            r1 = r2[r46]
            goto L_0x04bf
        L_0x04cc:
            if (r8 == r14) goto L_0x0590
            if (r8 == r15) goto L_0x0590
            if (r1 == 0) goto L_0x0590
            if (r1 != r15) goto L_0x04d7
            r1 = 0
            r7 = r1
            goto L_0x04d8
        L_0x04d7:
            r7 = r1
        L_0x04d8:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r8.mListAnchors
            r6 = r1[r47]
            android.support.constraint.solver.SolverVariable r5 = r6.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r6.mTarget
            if (r1 == 0) goto L_0x04e7
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r6.mTarget
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            goto L_0x04e8
        L_0x04e7:
            r1 = 0
        L_0x04e8:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r9.mListAnchors
            int r3 = r47 + 1
            r2 = r2[r3]
            android.support.constraint.solver.SolverVariable r4 = r2.mSolverVariable
            r1 = 0
            r2 = 0
            r3 = 0
            int r19 = r6.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r8.mListAnchors
            int r24 = r47 + 1
            r0 = r0[r24]
            int r0 = r0.getMargin()
            if (r7 == 0) goto L_0x051e
            r24 = r1
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r7.mListAnchors
            r1 = r1[r47]
            android.support.constraint.solver.SolverVariable r2 = r1.mSolverVariable
            r24 = r2
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r1.mTarget
            if (r2 == 0) goto L_0x0516
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r1.mTarget
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            goto L_0x0517
        L_0x0516:
            r2 = 0
        L_0x0517:
            r28 = r2
            r29 = r24
            r24 = r1
            goto L_0x053a
        L_0x051e:
            r24 = r1
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r8.mListAnchors
            int r28 = r47 + 1
            r1 = r1[r28]
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mTarget
            if (r1 == 0) goto L_0x052c
            android.support.constraint.solver.SolverVariable r2 = r1.mSolverVariable
        L_0x052c:
            r24 = r1
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r8.mListAnchors
            int r28 = r47 + 1
            r1 = r1[r28]
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            r28 = r1
            r29 = r2
        L_0x053a:
            if (r24 == 0) goto L_0x0541
            int r1 = r24.getMargin()
            int r0 = r0 + r1
        L_0x0541:
            if (r9 == 0) goto L_0x054f
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r9.mListAnchors
            int r2 = r47 + 1
            r1 = r1[r2]
            int r1 = r1.getMargin()
            int r19 = r19 + r1
        L_0x054f:
            r1 = 4
            if (r20 == 0) goto L_0x0556
            r1 = 6
            r31 = r1
            goto L_0x0558
        L_0x0556:
            r31 = r1
        L_0x0558:
            if (r5 == 0) goto L_0x0581
            if (r4 == 0) goto L_0x0581
            if (r29 == 0) goto L_0x0581
            if (r28 == 0) goto L_0x0581
            r32 = 1056964608(0x3f000000, float:0.5)
            r1 = r45
            r2 = r5
            r3 = r4
            r34 = r4
            r4 = r19
            r36 = r5
            r5 = r32
            r32 = r6
            r6 = r29
            r37 = r7
            r7 = r28
            r38 = r8
            r8 = r0
            r39 = r9
            r9 = r31
            r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9)
            goto L_0x058d
        L_0x0581:
            r34 = r4
            r36 = r5
            r32 = r6
            r37 = r7
            r38 = r8
            r39 = r9
        L_0x058d:
            r19 = r37
            goto L_0x0596
        L_0x0590:
            r38 = r8
            r39 = r9
            r19 = r1
        L_0x0596:
            int r0 = r38.getVisibility()
            r1 = 8
            if (r0 == r1) goto L_0x05a2
            r0 = r38
            r9 = r0
            goto L_0x05a4
        L_0x05a2:
            r9 = r39
        L_0x05a4:
            r8 = r19
            r0 = r1
            goto L_0x04b9
        L_0x05a9:
            r38 = r8
            r39 = r9
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r14.mListAnchors
            r0 = r0[r47]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r12.mListAnchors
            r1 = r1[r47]
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r1.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r15.mListAnchors
            int r2 = r47 + 1
            r8 = r1[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r13.mListAnchors
            int r2 = r47 + 1
            r1 = r1[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r1.mTarget
            if (r9 == 0) goto L_0x0612
            if (r14 == r15) goto L_0x05dc
            android.support.constraint.solver.SolverVariable r1 = r0.mSolverVariable
            android.support.constraint.solver.SolverVariable r2 = r9.mSolverVariable
            int r3 = r0.getMargin()
            r6 = 5
            r10.addEquality(r1, r2, r3, r6)
            r42 = r7
            r43 = r8
            r24 = r9
            goto L_0x0618
        L_0x05dc:
            r6 = 5
            if (r7 == 0) goto L_0x060b
            android.support.constraint.solver.SolverVariable r2 = r0.mSolverVariable
            android.support.constraint.solver.SolverVariable r3 = r9.mSolverVariable
            int r4 = r0.getMargin()
            android.support.constraint.solver.SolverVariable r1 = r8.mSolverVariable
            android.support.constraint.solver.SolverVariable r5 = r7.mSolverVariable
            int r27 = r8.getMargin()
            r28 = 5
            r29 = r1
            r1 = r45
            r24 = r5
            r5 = 1056964608(0x3f000000, float:0.5)
            r6 = r29
            r42 = r7
            r7 = r24
            r43 = r8
            r8 = r27
            r24 = r9
            r9 = r28
            r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9)
            goto L_0x0618
        L_0x060b:
            r42 = r7
            r43 = r8
            r24 = r9
            goto L_0x0618
        L_0x0612:
            r42 = r7
            r43 = r8
            r24 = r9
        L_0x0618:
            r1 = r42
            if (r1 == 0) goto L_0x062e
            if (r14 == r15) goto L_0x062e
            r2 = r43
            android.support.constraint.solver.SolverVariable r3 = r2.mSolverVariable
            android.support.constraint.solver.SolverVariable r4 = r1.mSolverVariable
            int r5 = r2.getMargin()
            int r5 = -r5
            r6 = 5
            r10.addEquality(r3, r4, r5, r6)
            goto L_0x0630
        L_0x062e:
            r2 = r43
        L_0x0630:
            r31 = r38
        L_0x0632:
            if (r22 != 0) goto L_0x0636
            if (r16 == 0) goto L_0x06b3
        L_0x0636:
            if (r14 == 0) goto L_0x06b3
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r14.mListAnchors
            r0 = r0[r47]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r15.mListAnchors
            int r2 = r47 + 1
            r1 = r1[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r0.mTarget
            if (r2 == 0) goto L_0x064b
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r0.mTarget
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            goto L_0x064c
        L_0x064b:
            r2 = 0
        L_0x064c:
            r20 = r2
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r1.mTarget
            if (r2 == 0) goto L_0x0657
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r1.mTarget
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            goto L_0x0658
        L_0x0657:
            r2 = 0
        L_0x0658:
            if (r13 == r15) goto L_0x066e
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r13.mListAnchors
            int r4 = r47 + 1
            r3 = r3[r4]
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r3.mTarget
            if (r4 == 0) goto L_0x0669
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r3.mTarget
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            goto L_0x066a
        L_0x0669:
            r4 = 0
        L_0x066a:
            r2 = r4
            r24 = r2
            goto L_0x0670
        L_0x066e:
            r24 = r2
        L_0x0670:
            if (r14 != r15) goto L_0x067e
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r14.mListAnchors
            r0 = r2[r47]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r14.mListAnchors
            int r3 = r47 + 1
            r1 = r2[r3]
            r9 = r1
            goto L_0x067f
        L_0x067e:
            r9 = r1
        L_0x067f:
            if (r20 == 0) goto L_0x06b1
            if (r24 == 0) goto L_0x06b1
            r26 = 1056964608(0x3f000000, float:0.5)
            int r27 = r0.getMargin()
            if (r15 != 0) goto L_0x068d
            r1 = r13
            r15 = r1
        L_0x068d:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r15.mListAnchors
            int r2 = r47 + 1
            r1 = r1[r2]
            int r28 = r1.getMargin()
            android.support.constraint.solver.SolverVariable r2 = r0.mSolverVariable
            android.support.constraint.solver.SolverVariable r7 = r9.mSolverVariable
            r29 = 5
            r1 = r45
            r3 = r20
            r4 = r27
            r5 = r26
            r6 = r24
            r8 = r28
            r32 = r9
            r9 = r29
            r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9)
            goto L_0x06b3
        L_0x06b1:
            r32 = r9
        L_0x06b3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.Chain.applyChainConstraints(android.support.constraint.solver.widgets.ConstraintWidgetContainer, android.support.constraint.solver.LinearSystem, int, int, android.support.constraint.solver.widgets.ChainHead):void");
    }
}
