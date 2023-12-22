package android.support.constraint.solver.widgets;

import android.support.constraint.solver.ArrayRow;
import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;
import android.support.constraint.solver.widgets.ConstraintWidget;
import java.util.ArrayList;

/* loaded from: classes.dex */
class Chain {
    private static final boolean DEBUG = false;

    Chain() {
    }

    static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem system, int orientation) {
        int offset;
        int chainsSize;
        ChainHead[] chainsArray;
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

    /* JADX WARN: Removed duplicated region for block: B:310:0x067a  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x067f  */
    /* JADX WARN: Removed duplicated region for block: B:314:0x0686  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x068b  */
    /* JADX WARN: Removed duplicated region for block: B:317:0x068e  */
    /* JADX WARN: Removed duplicated region for block: B:322:0x06a6  */
    /* JADX WARN: Removed duplicated region for block: B:324:0x06aa  */
    /* JADX WARN: Removed duplicated region for block: B:325:0x06b6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static void applyChainConstraints(ConstraintWidgetContainer container, LinearSystem system, int orientation, int offset, ChainHead chainHead) {
        boolean isChainSpread;
        boolean done;
        boolean isChainPacked;
        ConstraintWidget widget;
        boolean isChainSpreadInside;
        ConstraintWidget widget2;
        ArrayList<ConstraintWidget> listMatchConstraints;
        SolverVariable beginTarget;
        SolverVariable endTarget;
        ConstraintAnchor end;
        int endPointsStrength;
        ConstraintAnchor endTarget2;
        ConstraintAnchor end2;
        ConstraintWidget widget3;
        ConstraintWidget previousVisibleWidget;
        ConstraintWidget next;
        ConstraintWidget next2;
        ConstraintAnchor beginNextAnchor;
        SolverVariable beginNextTarget;
        SolverVariable beginNext;
        int strength;
        ConstraintWidget next3;
        int i;
        SolverVariable beginTarget2;
        ConstraintAnchor beginNextAnchor2;
        SolverVariable beginNext2;
        SolverVariable beginNextTarget2;
        ConstraintWidget next4;
        ConstraintWidget widget4;
        ConstraintWidget previousVisibleWidget2;
        int nextMargin;
        int margin1;
        int margin2;
        int strength2;
        float bias;
        int count;
        ConstraintWidget widget5;
        ArrayList<ConstraintWidget> listMatchConstraints2;
        ConstraintWidget firstMatchConstraintsWidget;
        int margin;
        float totalWeights;
        ConstraintWidget previousMatchConstraintsWidget;
        ConstraintWidget next5;
        int strength3;
        ConstraintWidget first = chainHead.mFirst;
        ConstraintWidget last = chainHead.mLast;
        ConstraintWidget firstVisibleWidget = chainHead.mFirstVisibleWidget;
        ConstraintWidget lastVisibleWidget = chainHead.mLastVisibleWidget;
        ConstraintWidget head = chainHead.mHead;
        float totalWeights2 = chainHead.mTotalWeight;
        ConstraintWidget firstMatchConstraintsWidget2 = chainHead.mFirstMatchConstraintWidget;
        ConstraintWidget previousMatchConstraintsWidget2 = chainHead.mLastMatchConstraintWidget;
        boolean isWrapContent = container.mListDimensionBehaviors[orientation] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (orientation == 0) {
            boolean isChainSpread2 = head.mHorizontalChainStyle == 0;
            isChainSpread = isChainSpread2;
            boolean isChainSpreadInside2 = head.mHorizontalChainStyle == 1;
            done = false;
            isChainPacked = head.mHorizontalChainStyle == 2;
            widget = first;
            isChainSpreadInside = isChainSpreadInside2;
        } else {
            boolean isChainSpread3 = head.mVerticalChainStyle == 0;
            isChainSpread = isChainSpread3;
            boolean isChainSpreadInside3 = head.mVerticalChainStyle == 1;
            done = false;
            isChainPacked = head.mVerticalChainStyle == 2;
            widget = first;
            isChainSpreadInside = isChainSpreadInside3;
        }
        while (!done) {
            ConstraintAnchor begin = widget.mListAnchors[offset];
            int strength4 = 4;
            if (isChainPacked) {
                strength4 = 1;
            }
            int margin3 = begin.getMargin();
            boolean isSpreadOnly = widget.mListDimensionBehaviors[orientation] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widget.mResolvedMatchConstraintDefault[orientation] == 0;
            if (begin.mTarget != null && widget != first) {
                margin = margin3 + begin.mTarget.getMargin();
            } else {
                margin = margin3;
            }
            if (isChainPacked && widget != first && widget != firstVisibleWidget) {
                strength4 = 5;
            }
            if (begin.mTarget == null) {
                totalWeights = totalWeights2;
                previousMatchConstraintsWidget = previousMatchConstraintsWidget2;
            } else {
                if (widget == firstVisibleWidget) {
                    totalWeights = totalWeights2;
                    previousMatchConstraintsWidget = previousMatchConstraintsWidget2;
                    system.addGreaterThan(begin.mSolverVariable, begin.mTarget.mSolverVariable, margin, 6);
                } else {
                    totalWeights = totalWeights2;
                    previousMatchConstraintsWidget = previousMatchConstraintsWidget2;
                    system.addGreaterThan(begin.mSolverVariable, begin.mTarget.mSolverVariable, margin, 8);
                }
                if (isSpreadOnly && !isChainPacked) {
                    strength3 = 5;
                } else {
                    strength3 = strength4;
                }
                system.addEquality(begin.mSolverVariable, begin.mTarget.mSolverVariable, margin, strength3);
            }
            if (isWrapContent) {
                if (widget.getVisibility() != 8 && widget.mListDimensionBehaviors[orientation] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    system.addGreaterThan(widget.mListAnchors[offset + 1].mSolverVariable, widget.mListAnchors[offset].mSolverVariable, 0, 5);
                }
                system.addGreaterThan(widget.mListAnchors[offset].mSolverVariable, container.mListAnchors[offset].mSolverVariable, 0, 8);
            }
            ConstraintAnchor nextAnchor = widget.mListAnchors[offset + 1].mTarget;
            if (nextAnchor != null) {
                ConstraintWidget next6 = nextAnchor.mOwner;
                next5 = (next6.mListAnchors[offset].mTarget == null || next6.mListAnchors[offset].mTarget.mOwner != widget) ? null : next6;
            } else {
                next5 = null;
            }
            if (next5 != null) {
                widget = next5;
            } else {
                done = true;
            }
            totalWeights2 = totalWeights;
            previousMatchConstraintsWidget2 = previousMatchConstraintsWidget;
        }
        float totalWeights3 = totalWeights2;
        if (lastVisibleWidget != null && last.mListAnchors[offset + 1].mTarget != null) {
            ConstraintAnchor end3 = lastVisibleWidget.mListAnchors[offset + 1];
            boolean isSpreadOnly2 = lastVisibleWidget.mListDimensionBehaviors[orientation] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && lastVisibleWidget.mResolvedMatchConstraintDefault[orientation] == 0;
            if (isSpreadOnly2 && !isChainPacked && end3.mTarget.mOwner == container) {
                system.addEquality(end3.mSolverVariable, end3.mTarget.mSolverVariable, -end3.getMargin(), 5);
            } else if (isChainPacked && end3.mTarget.mOwner == container) {
                system.addEquality(end3.mSolverVariable, end3.mTarget.mSolverVariable, -end3.getMargin(), 4);
            }
            system.addLowerThan(end3.mSolverVariable, last.mListAnchors[offset + 1].mTarget.mSolverVariable, -end3.getMargin(), 6);
        }
        if (isWrapContent) {
            system.addGreaterThan(container.mListAnchors[offset + 1].mSolverVariable, last.mListAnchors[offset + 1].mSolverVariable, last.mListAnchors[offset + 1].getMargin(), 8);
        }
        ArrayList<ConstraintWidget> listMatchConstraints3 = chainHead.mWeightedMatchConstraintsWidgets;
        if (listMatchConstraints3 == null) {
            widget2 = widget;
            listMatchConstraints = listMatchConstraints3;
        } else {
            int count2 = listMatchConstraints3.size();
            if (count2 > 1) {
                ConstraintWidget lastMatch = null;
                float lastWeight = 0.0f;
                if (chainHead.mHasUndefinedWeights && !chainHead.mHasComplexMatchWeights) {
                    totalWeights3 = chainHead.mWidgetsMatchCount;
                }
                int i2 = 0;
                while (i2 < count2) {
                    ConstraintWidget match = listMatchConstraints3.get(i2);
                    float currentWeight = match.mWeight[orientation];
                    if (currentWeight >= 0.0f) {
                        count = count2;
                        widget5 = widget;
                        listMatchConstraints2 = listMatchConstraints3;
                    } else if (chainHead.mHasComplexMatchWeights) {
                        count = count2;
                        widget5 = widget;
                        listMatchConstraints2 = listMatchConstraints3;
                        system.addEquality(match.mListAnchors[offset + 1].mSolverVariable, match.mListAnchors[offset].mSolverVariable, 0, 4);
                        firstMatchConstraintsWidget = firstMatchConstraintsWidget2;
                        i2++;
                        firstMatchConstraintsWidget2 = firstMatchConstraintsWidget;
                        listMatchConstraints3 = listMatchConstraints2;
                        count2 = count;
                        widget = widget5;
                    } else {
                        count = count2;
                        widget5 = widget;
                        listMatchConstraints2 = listMatchConstraints3;
                        currentWeight = 1.0f;
                    }
                    if (currentWeight == 0.0f) {
                        firstMatchConstraintsWidget = firstMatchConstraintsWidget2;
                        system.addEquality(match.mListAnchors[offset + 1].mSolverVariable, match.mListAnchors[offset].mSolverVariable, 0, 8);
                    } else {
                        firstMatchConstraintsWidget = firstMatchConstraintsWidget2;
                        if (lastMatch != null) {
                            SolverVariable begin2 = lastMatch.mListAnchors[offset].mSolverVariable;
                            SolverVariable end4 = lastMatch.mListAnchors[offset + 1].mSolverVariable;
                            SolverVariable nextBegin = match.mListAnchors[offset].mSolverVariable;
                            SolverVariable nextEnd = match.mListAnchors[offset + 1].mSolverVariable;
                            ArrayRow row = system.createRow();
                            row.createRowEqualMatchDimensions(lastWeight, totalWeights3, currentWeight, begin2, end4, nextBegin, nextEnd);
                            system.addConstraint(row);
                        }
                        lastWeight = currentWeight;
                        lastMatch = match;
                    }
                    i2++;
                    firstMatchConstraintsWidget2 = firstMatchConstraintsWidget;
                    listMatchConstraints3 = listMatchConstraints2;
                    count2 = count;
                    widget = widget5;
                }
                widget2 = widget;
                listMatchConstraints = listMatchConstraints3;
            } else {
                widget2 = widget;
                listMatchConstraints = listMatchConstraints3;
            }
        }
        if (firstVisibleWidget != null && (firstVisibleWidget == lastVisibleWidget || isChainPacked)) {
            ConstraintAnchor begin3 = first.mListAnchors[offset];
            ConstraintAnchor end5 = last.mListAnchors[offset + 1];
            SolverVariable beginTarget3 = begin3.mTarget != null ? begin3.mTarget.mSolverVariable : null;
            SolverVariable endTarget3 = end5.mTarget != null ? end5.mTarget.mSolverVariable : null;
            ConstraintAnchor begin4 = firstVisibleWidget.mListAnchors[offset];
            ConstraintAnchor end6 = lastVisibleWidget.mListAnchors[offset + 1];
            if (beginTarget3 != null && endTarget3 != null) {
                if (orientation == 0) {
                    float bias2 = head.mHorizontalBiasPercent;
                    bias = bias2;
                } else {
                    float bias3 = head.mVerticalBiasPercent;
                    bias = bias3;
                }
                int beginMargin = begin4.getMargin();
                int endMargin = end6.getMargin();
                system.addCentering(begin4.mSolverVariable, beginTarget3, beginMargin, bias, endTarget3, end6.mSolverVariable, endMargin, 7);
            }
            if ((!isChainSpread || isChainSpreadInside) && firstVisibleWidget != null && firstVisibleWidget != lastVisibleWidget) {
                ConstraintAnchor begin5 = firstVisibleWidget.mListAnchors[offset];
                ConstraintAnchor end7 = lastVisibleWidget.mListAnchors[offset + 1];
                beginTarget = begin5.mTarget == null ? begin5.mTarget.mSolverVariable : null;
                SolverVariable endTarget4 = end7.mTarget == null ? end7.mTarget.mSolverVariable : null;
                if (last != lastVisibleWidget) {
                    endTarget = endTarget4;
                } else {
                    ConstraintAnchor realEnd = last.mListAnchors[offset + 1];
                    SolverVariable endTarget5 = realEnd.mTarget != null ? realEnd.mTarget.mSolverVariable : null;
                    endTarget = endTarget5;
                }
                if (firstVisibleWidget == lastVisibleWidget) {
                    end = end7;
                } else {
                    begin5 = firstVisibleWidget.mListAnchors[offset];
                    end = firstVisibleWidget.mListAnchors[offset + 1];
                }
                if (beginTarget == null && endTarget != null) {
                    int beginMargin2 = begin5.getMargin();
                    if (lastVisibleWidget == null) {
                        lastVisibleWidget = last;
                    }
                    int endMargin2 = lastVisibleWidget.mListAnchors[offset + 1].getMargin();
                    system.addCentering(begin5.mSolverVariable, beginTarget, beginMargin2, 0.5f, endTarget, end.mSolverVariable, endMargin2, 5);
                    return;
                }
            }
            return;
        }
        if (!isChainSpread || firstVisibleWidget == null) {
            int i3 = 8;
            if (isChainSpreadInside && firstVisibleWidget != null) {
                boolean applyFixedEquality = chainHead.mWidgetsMatchCount > 0 && chainHead.mWidgetsCount == chainHead.mWidgetsMatchCount;
                ConstraintWidget widget6 = firstVisibleWidget;
                ConstraintWidget previousVisibleWidget3 = firstVisibleWidget;
                while (widget6 != null) {
                    ConstraintWidget next7 = widget6.mNextChainWidget[orientation];
                    while (next7 != null && next7.getVisibility() == i3) {
                        next7 = next7.mNextChainWidget[orientation];
                    }
                    if (widget6 == firstVisibleWidget || widget6 == lastVisibleWidget || next7 == null) {
                        widget3 = widget6;
                        previousVisibleWidget = previousVisibleWidget3;
                        next = next7;
                    } else {
                        if (next7 != lastVisibleWidget) {
                            next2 = next7;
                        } else {
                            next2 = null;
                        }
                        ConstraintAnchor beginAnchor = widget6.mListAnchors[offset];
                        SolverVariable begin6 = beginAnchor.mSolverVariable;
                        if (beginAnchor.mTarget != null) {
                            SolverVariable solverVariable = beginAnchor.mTarget.mSolverVariable;
                        }
                        SolverVariable beginTarget4 = previousVisibleWidget3.mListAnchors[offset + 1].mSolverVariable;
                        SolverVariable beginNext3 = null;
                        int beginMargin3 = beginAnchor.getMargin();
                        int nextMargin2 = widget6.mListAnchors[offset + 1].getMargin();
                        if (next2 != null) {
                            ConstraintAnchor beginNextAnchor3 = next2.mListAnchors[offset];
                            SolverVariable beginNext4 = beginNextAnchor3.mSolverVariable;
                            beginNextTarget = beginNextAnchor3.mTarget != null ? beginNextAnchor3.mTarget.mSolverVariable : null;
                            beginNext = beginNext4;
                            beginNextAnchor = beginNextAnchor3;
                        } else {
                            ConstraintAnchor beginNextAnchor4 = lastVisibleWidget.mListAnchors[offset];
                            if (beginNextAnchor4 != null) {
                                beginNext3 = beginNextAnchor4.mSolverVariable;
                            }
                            beginNextAnchor = beginNextAnchor4;
                            beginNextTarget = widget6.mListAnchors[offset + 1].mSolverVariable;
                            beginNext = beginNext3;
                        }
                        if (beginNextAnchor != null) {
                            nextMargin2 += beginNextAnchor.getMargin();
                        }
                        if (previousVisibleWidget3 != null) {
                            beginMargin3 += previousVisibleWidget3.mListAnchors[offset + 1].getMargin();
                        }
                        if (!applyFixedEquality) {
                            strength = 4;
                        } else {
                            strength = 8;
                        }
                        if (begin6 == null || beginTarget4 == null || beginNext == null || beginNextTarget == null) {
                            next3 = next2;
                            widget3 = widget6;
                            previousVisibleWidget = previousVisibleWidget3;
                        } else {
                            next3 = next2;
                            widget3 = widget6;
                            previousVisibleWidget = previousVisibleWidget3;
                            system.addCentering(begin6, beginTarget4, beginMargin3, 0.5f, beginNext, beginNextTarget, nextMargin2, strength);
                        }
                        next = next3;
                    }
                    if (widget3.getVisibility() == 8) {
                        previousVisibleWidget3 = previousVisibleWidget;
                    } else {
                        previousVisibleWidget3 = widget3;
                    }
                    widget6 = next;
                    i3 = 8;
                }
                ConstraintAnchor begin7 = firstVisibleWidget.mListAnchors[offset];
                ConstraintAnchor beginTarget5 = first.mListAnchors[offset].mTarget;
                ConstraintAnchor end8 = lastVisibleWidget.mListAnchors[offset + 1];
                ConstraintAnchor endTarget6 = last.mListAnchors[offset + 1].mTarget;
                if (beginTarget5 == null) {
                    endPointsStrength = 5;
                    endTarget2 = endTarget6;
                    end2 = end8;
                } else if (firstVisibleWidget != lastVisibleWidget) {
                    system.addEquality(begin7.mSolverVariable, beginTarget5.mSolverVariable, begin7.getMargin(), 5);
                    endPointsStrength = 5;
                    endTarget2 = endTarget6;
                    end2 = end8;
                } else if (endTarget6 != null) {
                    endPointsStrength = 5;
                    endTarget2 = endTarget6;
                    end2 = end8;
                    system.addCentering(begin7.mSolverVariable, beginTarget5.mSolverVariable, begin7.getMargin(), 0.5f, end8.mSolverVariable, endTarget6.mSolverVariable, end8.getMargin(), 5);
                } else {
                    endPointsStrength = 5;
                    endTarget2 = endTarget6;
                    end2 = end8;
                }
                ConstraintAnchor endTarget7 = endTarget2;
                if (endTarget7 != null && firstVisibleWidget != lastVisibleWidget) {
                    ConstraintAnchor end9 = end2;
                    system.addEquality(end9.mSolverVariable, endTarget7.mSolverVariable, -end9.getMargin(), endPointsStrength);
                }
            }
        } else {
            boolean applyFixedEquality2 = chainHead.mWidgetsMatchCount > 0 && chainHead.mWidgetsCount == chainHead.mWidgetsMatchCount;
            ConstraintWidget previousVisibleWidget4 = firstVisibleWidget;
            for (ConstraintWidget widget7 = firstVisibleWidget; widget7 != null; widget7 = next4) {
                ConstraintWidget next8 = widget7.mNextChainWidget[orientation];
                while (true) {
                    if (next8 == null) {
                        i = 8;
                        break;
                    }
                    i = 8;
                    if (next8.getVisibility() != 8) {
                        break;
                    }
                    next8 = next8.mNextChainWidget[orientation];
                }
                if (next8 != null || widget7 == lastVisibleWidget) {
                    ConstraintAnchor beginAnchor2 = widget7.mListAnchors[offset];
                    SolverVariable begin8 = beginAnchor2.mSolverVariable;
                    SolverVariable beginTarget6 = beginAnchor2.mTarget != null ? beginAnchor2.mTarget.mSolverVariable : null;
                    if (previousVisibleWidget4 != widget7) {
                        beginTarget2 = previousVisibleWidget4.mListAnchors[offset + 1].mSolverVariable;
                    } else if (widget7 == firstVisibleWidget && previousVisibleWidget4 == widget7) {
                        beginTarget2 = first.mListAnchors[offset].mTarget != null ? first.mListAnchors[offset].mTarget.mSolverVariable : null;
                    } else {
                        beginTarget2 = beginTarget6;
                    }
                    SolverVariable beginNext5 = null;
                    int beginMargin4 = beginAnchor2.getMargin();
                    int nextMargin3 = widget7.mListAnchors[offset + 1].getMargin();
                    if (next8 != null) {
                        ConstraintAnchor beginNextAnchor5 = next8.mListAnchors[offset];
                        SolverVariable beginNext6 = beginNextAnchor5.mSolverVariable;
                        SolverVariable beginNextTarget3 = widget7.mListAnchors[offset + 1].mSolverVariable;
                        beginNextAnchor2 = beginNextAnchor5;
                        beginNext2 = beginNext6;
                        beginNextTarget2 = beginNextTarget3;
                    } else {
                        ConstraintAnchor beginNextAnchor6 = last.mListAnchors[offset + 1].mTarget;
                        if (beginNextAnchor6 != null) {
                            beginNext5 = beginNextAnchor6.mSolverVariable;
                        }
                        SolverVariable beginNextTarget4 = widget7.mListAnchors[offset + 1].mSolverVariable;
                        beginNextAnchor2 = beginNextAnchor6;
                        beginNext2 = beginNext5;
                        beginNextTarget2 = beginNextTarget4;
                    }
                    if (beginNextAnchor2 != null) {
                        nextMargin3 += beginNextAnchor2.getMargin();
                    }
                    if (previousVisibleWidget4 != null) {
                        beginMargin4 += previousVisibleWidget4.mListAnchors[offset + 1].getMargin();
                    }
                    if (begin8 == null || beginTarget2 == null || beginNext2 == null || beginNextTarget2 == null) {
                        next4 = next8;
                        widget4 = widget7;
                        previousVisibleWidget2 = previousVisibleWidget4;
                        nextMargin = 8;
                    } else {
                        int margin12 = beginMargin4;
                        if (widget7 != firstVisibleWidget) {
                            margin1 = margin12;
                        } else {
                            int margin13 = firstVisibleWidget.mListAnchors[offset].getMargin();
                            margin1 = margin13;
                        }
                        int margin14 = nextMargin3;
                        if (widget7 != lastVisibleWidget) {
                            margin2 = margin14;
                        } else {
                            int margin22 = lastVisibleWidget.mListAnchors[offset + 1].getMargin();
                            margin2 = margin22;
                        }
                        if (!applyFixedEquality2) {
                            strength2 = 5;
                        } else {
                            strength2 = 8;
                        }
                        nextMargin = 8;
                        next4 = next8;
                        widget4 = widget7;
                        previousVisibleWidget2 = previousVisibleWidget4;
                        system.addCentering(begin8, beginTarget2, margin1, 0.5f, beginNext2, beginNextTarget2, margin2, strength2);
                    }
                } else {
                    nextMargin = i;
                    next4 = next8;
                    widget4 = widget7;
                    previousVisibleWidget2 = previousVisibleWidget4;
                }
                if (widget4.getVisibility() == nextMargin) {
                    previousVisibleWidget4 = previousVisibleWidget2;
                } else {
                    previousVisibleWidget4 = widget4;
                }
            }
        }
        if (!isChainSpread) {
        }
        ConstraintAnchor begin52 = firstVisibleWidget.mListAnchors[offset];
        ConstraintAnchor end72 = lastVisibleWidget.mListAnchors[offset + 1];
        beginTarget = begin52.mTarget == null ? begin52.mTarget.mSolverVariable : null;
        if (end72.mTarget == null) {
        }
        if (last != lastVisibleWidget) {
        }
        if (firstVisibleWidget == lastVisibleWidget) {
        }
        if (beginTarget == null) {
        }
    }
}
