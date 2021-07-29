package com.ibm.icu.text;

import com.ibm.icu.impl.Assert;
import com.ibm.icu.impl.RBBIDataWrapper;
import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.text.RBBIRuleBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

class RBBITableBuilder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private List<RBBIStateDescriptor> fDStates = new ArrayList();
    private RBBIRuleBuilder fRB;
    private int fRootIx;
    private List<short[]> fSafeTable;

    static class RBBIStateDescriptor {
        int fAccepting;
        int[] fDtran;
        int fLookAhead;
        boolean fMarked;
        Set<RBBINode> fPositions = new HashSet();
        SortedSet<Integer> fTagVals = new TreeSet();
        int fTagsIdx;

        RBBIStateDescriptor(int maxInputSymbol) {
            this.fDtran = new int[(maxInputSymbol + 1)];
        }
    }

    RBBITableBuilder(RBBIRuleBuilder rb, int rootNodeIx) {
        this.fRootIx = rootNodeIx;
        this.fRB = rb;
    }

    /* access modifiers changed from: package-private */
    public void buildForwardTable() {
        if (this.fRB.fTreeRoots[this.fRootIx] != null) {
            this.fRB.fTreeRoots[this.fRootIx] = this.fRB.fTreeRoots[this.fRootIx].flattenVariables();
            if (this.fRB.fDebugEnv != null && this.fRB.fDebugEnv.indexOf("ftree") >= 0) {
                System.out.println("Parse tree after flattening variable references.");
                this.fRB.fTreeRoots[this.fRootIx].printTree(true);
            }
            if (this.fRB.fSetBuilder.sawBOF()) {
                RBBINode bofTop = new RBBINode(8);
                RBBINode bofLeaf = new RBBINode(3);
                bofTop.fLeftChild = bofLeaf;
                bofTop.fRightChild = this.fRB.fTreeRoots[this.fRootIx];
                bofLeaf.fParent = bofTop;
                bofLeaf.fVal = 2;
                this.fRB.fTreeRoots[this.fRootIx] = bofTop;
            }
            RBBINode cn = new RBBINode(8);
            cn.fLeftChild = this.fRB.fTreeRoots[this.fRootIx];
            this.fRB.fTreeRoots[this.fRootIx].fParent = cn;
            cn.fRightChild = new RBBINode(6);
            cn.fRightChild.fParent = cn;
            this.fRB.fTreeRoots[this.fRootIx] = cn;
            this.fRB.fTreeRoots[this.fRootIx].flattenSets();
            if (this.fRB.fDebugEnv != null && this.fRB.fDebugEnv.indexOf("stree") >= 0) {
                System.out.println("Parse tree after flattening Unicode Set references.");
                this.fRB.fTreeRoots[this.fRootIx].printTree(true);
            }
            calcNullable(this.fRB.fTreeRoots[this.fRootIx]);
            calcFirstPos(this.fRB.fTreeRoots[this.fRootIx]);
            calcLastPos(this.fRB.fTreeRoots[this.fRootIx]);
            calcFollowPos(this.fRB.fTreeRoots[this.fRootIx]);
            if (this.fRB.fDebugEnv != null && this.fRB.fDebugEnv.indexOf("pos") >= 0) {
                System.out.print("\n");
                printPosSets(this.fRB.fTreeRoots[this.fRootIx]);
            }
            if (this.fRB.fChainRules) {
                calcChainedFollowPos(this.fRB.fTreeRoots[this.fRootIx]);
            }
            if (this.fRB.fSetBuilder.sawBOF()) {
                bofFixup();
            }
            buildStateTable();
            flagAcceptingStates();
            flagLookAheadStates();
            flagTaggedStates();
            mergeRuleStatusVals();
        }
    }

    /* access modifiers changed from: package-private */
    public void calcNullable(RBBINode n) {
        if (n != null) {
            boolean z = false;
            if (n.fType == 0 || n.fType == 6) {
                n.fNullable = false;
            } else if (n.fType == 4 || n.fType == 5) {
                n.fNullable = true;
            } else {
                calcNullable(n.fLeftChild);
                calcNullable(n.fRightChild);
                if (n.fType == 9) {
                    if (n.fLeftChild.fNullable || n.fRightChild.fNullable) {
                        z = true;
                    }
                    n.fNullable = z;
                } else if (n.fType == 8) {
                    if (n.fLeftChild.fNullable && n.fRightChild.fNullable) {
                        z = true;
                    }
                    n.fNullable = z;
                } else if (n.fType == 10 || n.fType == 12) {
                    n.fNullable = true;
                } else {
                    n.fNullable = false;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void calcFirstPos(RBBINode n) {
        if (n != null) {
            if (n.fType == 3 || n.fType == 6 || n.fType == 4 || n.fType == 5) {
                n.fFirstPosSet.add(n);
                return;
            }
            calcFirstPos(n.fLeftChild);
            calcFirstPos(n.fRightChild);
            if (n.fType == 9) {
                n.fFirstPosSet.addAll(n.fLeftChild.fFirstPosSet);
                n.fFirstPosSet.addAll(n.fRightChild.fFirstPosSet);
            } else if (n.fType == 8) {
                n.fFirstPosSet.addAll(n.fLeftChild.fFirstPosSet);
                if (n.fLeftChild.fNullable) {
                    n.fFirstPosSet.addAll(n.fRightChild.fFirstPosSet);
                }
            } else if (n.fType == 10 || n.fType == 12 || n.fType == 11) {
                n.fFirstPosSet.addAll(n.fLeftChild.fFirstPosSet);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void calcLastPos(RBBINode n) {
        if (n != null) {
            if (n.fType == 3 || n.fType == 6 || n.fType == 4 || n.fType == 5) {
                n.fLastPosSet.add(n);
                return;
            }
            calcLastPos(n.fLeftChild);
            calcLastPos(n.fRightChild);
            if (n.fType == 9) {
                n.fLastPosSet.addAll(n.fLeftChild.fLastPosSet);
                n.fLastPosSet.addAll(n.fRightChild.fLastPosSet);
            } else if (n.fType == 8) {
                n.fLastPosSet.addAll(n.fRightChild.fLastPosSet);
                if (n.fRightChild.fNullable) {
                    n.fLastPosSet.addAll(n.fLeftChild.fLastPosSet);
                }
            } else if (n.fType == 10 || n.fType == 12 || n.fType == 11) {
                n.fLastPosSet.addAll(n.fLeftChild.fLastPosSet);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void calcFollowPos(RBBINode n) {
        if (n != null && n.fType != 3 && n.fType != 6) {
            calcFollowPos(n.fLeftChild);
            calcFollowPos(n.fRightChild);
            if (n.fType == 8) {
                for (RBBINode i : n.fLeftChild.fLastPosSet) {
                    i.fFollowPos.addAll(n.fRightChild.fFirstPosSet);
                }
            }
            if (n.fType == 10 || n.fType == 11) {
                for (RBBINode i2 : n.fLastPosSet) {
                    i2.fFollowPos.addAll(n.fFirstPosSet);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void addRuleRootNodes(List<RBBINode> dest, RBBINode node) {
        if (node != null) {
            if (node.fRuleRoot) {
                dest.add(node);
                return;
            }
            addRuleRootNodes(dest, node.fLeftChild);
            addRuleRootNodes(dest, node.fRightChild);
        }
    }

    /* access modifiers changed from: package-private */
    public void calcChainedFollowPos(RBBINode tree) {
        int c;
        List<RBBINode> endMarkerNodes = new ArrayList<>();
        List<RBBINode> leafNodes = new ArrayList<>();
        tree.findNodes(endMarkerNodes, 6);
        tree.findNodes(leafNodes, 3);
        List<RBBINode> ruleRootNodes = new ArrayList<>();
        addRuleRootNodes(ruleRootNodes, tree);
        Set<RBBINode> matchStartNodes = new HashSet<>();
        for (RBBINode node : ruleRootNodes) {
            if (node.fChainIn) {
                matchStartNodes.addAll(node.fFirstPosSet);
            }
        }
        for (RBBINode tNode : leafNodes) {
            RBBINode endNode = null;
            Iterator<RBBINode> it = endMarkerNodes.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (tNode.fFollowPos.contains(it.next())) {
                    endNode = tNode;
                    break;
                }
            }
            if (endNode != null && (!this.fRB.fLBCMNoChain || (c = this.fRB.fSetBuilder.getFirstChar(endNode.fVal)) == -1 || UCharacter.getIntPropertyValue(c, 4104) != 9)) {
                for (RBBINode startNode : matchStartNodes) {
                    if (startNode.fType == 3 && endNode.fVal == startNode.fVal) {
                        endNode.fFollowPos.addAll(startNode.fFollowPos);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void bofFixup() {
        RBBINode bofNode = this.fRB.fTreeRoots[this.fRootIx].fLeftChild.fLeftChild;
        boolean z = true;
        Assert.assrt(bofNode.fType == 3);
        if (bofNode.fVal != 2) {
            z = false;
        }
        Assert.assrt(z);
        for (RBBINode startNode : this.fRB.fTreeRoots[this.fRootIx].fLeftChild.fRightChild.fFirstPosSet) {
            if (startNode.fType == 3 && startNode.fVal == bofNode.fVal) {
                bofNode.fFollowPos.addAll(startNode.fFollowPos);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void buildStateTable() {
        int lastInputSymbol = this.fRB.fSetBuilder.getNumCharCategories() - 1;
        this.fDStates.add(new RBBIStateDescriptor(lastInputSymbol));
        RBBIStateDescriptor initialState = new RBBIStateDescriptor(lastInputSymbol);
        initialState.fPositions.addAll(this.fRB.fTreeRoots[this.fRootIx].fFirstPosSet);
        this.fDStates.add(initialState);
        while (true) {
            RBBIStateDescriptor T = null;
            int tx = 1;
            while (true) {
                if (tx >= this.fDStates.size()) {
                    break;
                }
                RBBIStateDescriptor temp = this.fDStates.get(tx);
                if (!temp.fMarked) {
                    T = temp;
                    break;
                }
                tx++;
            }
            if (T != null) {
                T.fMarked = true;
                for (int a = 1; a <= lastInputSymbol; a++) {
                    Set<RBBINode> U = null;
                    for (RBBINode p : T.fPositions) {
                        if (p.fType == 3 && p.fVal == a) {
                            if (U == null) {
                                U = new HashSet<>();
                            }
                            U.addAll(p.fFollowPos);
                        }
                    }
                    int ux = 0;
                    boolean UinDstates = false;
                    if (U != null) {
                        Assert.assrt(U.size() > 0);
                        int ix = 0;
                        while (true) {
                            if (ix >= this.fDStates.size()) {
                                break;
                            }
                            RBBIStateDescriptor temp2 = this.fDStates.get(ix);
                            if (U.equals(temp2.fPositions)) {
                                U = temp2.fPositions;
                                ux = ix;
                                UinDstates = true;
                                break;
                            }
                            ix++;
                        }
                        if (!UinDstates) {
                            RBBIStateDescriptor newState = new RBBIStateDescriptor(lastInputSymbol);
                            newState.fPositions = U;
                            this.fDStates.add(newState);
                            ux = this.fDStates.size() - 1;
                        }
                        T.fDtran[a] = ux;
                    }
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void flagAcceptingStates() {
        List<RBBINode> endMarkerNodes = new ArrayList<>();
        this.fRB.fTreeRoots[this.fRootIx].findNodes(endMarkerNodes, 6);
        for (int i = 0; i < endMarkerNodes.size(); i++) {
            RBBINode endMarker = endMarkerNodes.get(i);
            for (int n = 0; n < this.fDStates.size(); n++) {
                RBBIStateDescriptor sd = this.fDStates.get(n);
                if (sd.fPositions.contains(endMarker)) {
                    if (sd.fAccepting == 0) {
                        sd.fAccepting = endMarker.fVal;
                        if (sd.fAccepting == 0) {
                            sd.fAccepting = -1;
                        }
                    }
                    if (sd.fAccepting == -1 && endMarker.fVal != 0) {
                        sd.fAccepting = endMarker.fVal;
                    }
                    if (endMarker.fLookAheadEnd) {
                        sd.fLookAhead = sd.fAccepting;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void flagLookAheadStates() {
        List<RBBINode> lookAheadNodes = new ArrayList<>();
        this.fRB.fTreeRoots[this.fRootIx].findNodes(lookAheadNodes, 4);
        for (int i = 0; i < lookAheadNodes.size(); i++) {
            RBBINode lookAheadNode = lookAheadNodes.get(i);
            for (int n = 0; n < this.fDStates.size(); n++) {
                RBBIStateDescriptor sd = this.fDStates.get(n);
                if (sd.fPositions.contains(lookAheadNode)) {
                    sd.fLookAhead = lookAheadNode.fVal;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void flagTaggedStates() {
        List<RBBINode> tagNodes = new ArrayList<>();
        this.fRB.fTreeRoots[this.fRootIx].findNodes(tagNodes, 5);
        for (int i = 0; i < tagNodes.size(); i++) {
            RBBINode tagNode = tagNodes.get(i);
            for (int n = 0; n < this.fDStates.size(); n++) {
                RBBIStateDescriptor sd = this.fDStates.get(n);
                if (sd.fPositions.contains(tagNode)) {
                    sd.fTagVals.add(Integer.valueOf(tagNode.fVal));
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void mergeRuleStatusVals() {
        if (this.fRB.fRuleStatusVals.size() == 0) {
            this.fRB.fRuleStatusVals.add(1);
            this.fRB.fRuleStatusVals.add(0);
            SortedSet<Integer> s0 = new TreeSet<>();
            this.fRB.fStatusSets.put(s0, 0);
            new TreeSet<>().add(0);
            this.fRB.fStatusSets.put(s0, 0);
        }
        for (int n = 0; n < this.fDStates.size(); n++) {
            RBBIStateDescriptor sd = this.fDStates.get(n);
            Set<Integer> statusVals = sd.fTagVals;
            Integer arrayIndexI = this.fRB.fStatusSets.get(statusVals);
            if (arrayIndexI == null) {
                arrayIndexI = Integer.valueOf(this.fRB.fRuleStatusVals.size());
                this.fRB.fStatusSets.put(statusVals, arrayIndexI);
                this.fRB.fRuleStatusVals.add(Integer.valueOf(statusVals.size()));
                this.fRB.fRuleStatusVals.addAll(statusVals);
            }
            sd.fTagsIdx = arrayIndexI.intValue();
        }
    }

    /* access modifiers changed from: package-private */
    public void printPosSets(RBBINode n) {
        if (n != null) {
            RBBINode.printNode(n);
            System.out.print("         Nullable:  " + n.fNullable);
            System.out.print("         firstpos:  ");
            printSet(n.fFirstPosSet);
            System.out.print("         lastpos:   ");
            printSet(n.fLastPosSet);
            System.out.print("         followpos: ");
            printSet(n.fFollowPos);
            printPosSets(n.fLeftChild);
            printPosSets(n.fRightChild);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0043, code lost:
        r10.first++;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean findDuplCharClassFrom(com.ibm.icu.text.RBBIRuleBuilder.IntPair r10) {
        /*
            r9 = this;
            java.util.List<com.ibm.icu.text.RBBITableBuilder$RBBIStateDescriptor> r0 = r9.fDStates
            int r0 = r0.size()
            com.ibm.icu.text.RBBIRuleBuilder r1 = r9.fRB
            com.ibm.icu.text.RBBISetBuilder r1 = r1.fSetBuilder
            int r1 = r1.getNumCharCategories()
            r2 = 0
            r3 = 0
        L_0x0010:
            int r4 = r10.first
            int r5 = r1 + -1
            if (r4 >= r5) goto L_0x0049
            int r4 = r10.first
            r5 = 1
        L_0x0019:
            int r4 = r4 + r5
            r10.second = r4
            int r4 = r10.second
            if (r4 >= r1) goto L_0x0043
            r4 = 0
        L_0x0021:
            if (r4 >= r0) goto L_0x003d
            java.util.List<com.ibm.icu.text.RBBITableBuilder$RBBIStateDescriptor> r6 = r9.fDStates
            java.lang.Object r6 = r6.get(r4)
            com.ibm.icu.text.RBBITableBuilder$RBBIStateDescriptor r6 = (com.ibm.icu.text.RBBITableBuilder.RBBIStateDescriptor) r6
            int[] r7 = r6.fDtran
            int r8 = r10.first
            r2 = r7[r8]
            int[] r7 = r6.fDtran
            int r8 = r10.second
            r3 = r7[r8]
            if (r2 == r3) goto L_0x003a
            goto L_0x003d
        L_0x003a:
            int r4 = r4 + 1
            goto L_0x0021
        L_0x003d:
            if (r2 != r3) goto L_0x0040
            return r5
        L_0x0040:
            int r4 = r10.second
            goto L_0x0019
        L_0x0043:
            int r4 = r10.first
            int r4 = r4 + r5
            r10.first = r4
            goto L_0x0010
        L_0x0049:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.RBBITableBuilder.findDuplCharClassFrom(com.ibm.icu.text.RBBIRuleBuilder$IntPair):boolean");
    }

    /* access modifiers changed from: package-private */
    public void removeColumn(int column) {
        int numStates = this.fDStates.size();
        int state = 0;
        while (state < numStates) {
            RBBIStateDescriptor sd = this.fDStates.get(state);
            if (column < sd.fDtran.length) {
                int[] newArray = Arrays.copyOf(sd.fDtran, sd.fDtran.length - 1);
                System.arraycopy(sd.fDtran, column + 1, newArray, column, newArray.length - column);
                sd.fDtran = newArray;
                state++;
            } else {
                throw new AssertionError();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x006e, code lost:
        r11.first++;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean findDuplicateState(com.ibm.icu.text.RBBIRuleBuilder.IntPair r11) {
        /*
            r10 = this;
            java.util.List<com.ibm.icu.text.RBBITableBuilder$RBBIStateDescriptor> r0 = r10.fDStates
            int r0 = r0.size()
            com.ibm.icu.text.RBBIRuleBuilder r1 = r10.fRB
            com.ibm.icu.text.RBBISetBuilder r1 = r1.fSetBuilder
            int r1 = r1.getNumCharCategories()
        L_0x000e:
            int r2 = r11.first
            int r3 = r0 + -1
            if (r2 >= r3) goto L_0x0074
            java.util.List<com.ibm.icu.text.RBBITableBuilder$RBBIStateDescriptor> r2 = r10.fDStates
            int r3 = r11.first
            java.lang.Object r2 = r2.get(r3)
            com.ibm.icu.text.RBBITableBuilder$RBBIStateDescriptor r2 = (com.ibm.icu.text.RBBITableBuilder.RBBIStateDescriptor) r2
            int r3 = r11.first
            r4 = 1
        L_0x0021:
            int r3 = r3 + r4
            r11.second = r3
            int r3 = r11.second
            if (r3 >= r0) goto L_0x006e
            java.util.List<com.ibm.icu.text.RBBITableBuilder$RBBIStateDescriptor> r3 = r10.fDStates
            int r5 = r11.second
            java.lang.Object r3 = r3.get(r5)
            com.ibm.icu.text.RBBITableBuilder$RBBIStateDescriptor r3 = (com.ibm.icu.text.RBBITableBuilder.RBBIStateDescriptor) r3
            int r5 = r2.fAccepting
            int r6 = r3.fAccepting
            if (r5 != r6) goto L_0x006b
            int r5 = r2.fLookAhead
            int r6 = r3.fLookAhead
            if (r5 != r6) goto L_0x006b
            int r5 = r2.fTagsIdx
            int r6 = r3.fTagsIdx
            if (r5 == r6) goto L_0x0045
            goto L_0x006b
        L_0x0045:
            r5 = 1
            r6 = 0
        L_0x0047:
            if (r6 >= r1) goto L_0x0068
            int[] r7 = r2.fDtran
            r7 = r7[r6]
            int[] r8 = r3.fDtran
            r8 = r8[r6]
            if (r7 == r8) goto L_0x0065
            int r9 = r11.first
            if (r7 == r9) goto L_0x005b
            int r9 = r11.second
            if (r7 != r9) goto L_0x0063
        L_0x005b:
            int r9 = r11.first
            if (r8 == r9) goto L_0x0065
            int r9 = r11.second
            if (r8 == r9) goto L_0x0065
        L_0x0063:
            r5 = 0
            goto L_0x0068
        L_0x0065:
            int r6 = r6 + 1
            goto L_0x0047
        L_0x0068:
            if (r5 == 0) goto L_0x006b
            return r4
        L_0x006b:
            int r3 = r11.second
            goto L_0x0021
        L_0x006e:
            int r2 = r11.first
            int r2 = r2 + r4
            r11.first = r2
            goto L_0x000e
        L_0x0074:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.RBBITableBuilder.findDuplicateState(com.ibm.icu.text.RBBIRuleBuilder$IntPair):boolean");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0050, code lost:
        r11.first++;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean findDuplicateSafeState(com.ibm.icu.text.RBBIRuleBuilder.IntPair r11) {
        /*
            r10 = this;
            java.util.List<short[]> r0 = r10.fSafeTable
            int r0 = r0.size()
        L_0x0006:
            int r1 = r11.first
            int r2 = r0 + -1
            if (r1 >= r2) goto L_0x0056
            java.util.List<short[]> r1 = r10.fSafeTable
            int r2 = r11.first
            java.lang.Object r1 = r1.get(r2)
            short[] r1 = (short[]) r1
            int r2 = r11.first
            r3 = 1
        L_0x0019:
            int r2 = r2 + r3
            r11.second = r2
            int r2 = r11.second
            if (r2 >= r0) goto L_0x0050
            java.util.List<short[]> r2 = r10.fSafeTable
            int r4 = r11.second
            java.lang.Object r2 = r2.get(r4)
            short[] r2 = (short[]) r2
            r4 = 1
            int r5 = r1.length
            r6 = 0
        L_0x002d:
            if (r6 >= r5) goto L_0x004a
            short r7 = r1[r6]
            short r8 = r2[r6]
            if (r7 == r8) goto L_0x0047
            int r9 = r11.first
            if (r7 == r9) goto L_0x003d
            int r9 = r11.second
            if (r7 != r9) goto L_0x0045
        L_0x003d:
            int r9 = r11.first
            if (r8 == r9) goto L_0x0047
            int r9 = r11.second
            if (r8 == r9) goto L_0x0047
        L_0x0045:
            r4 = 0
            goto L_0x004a
        L_0x0047:
            int r6 = r6 + 1
            goto L_0x002d
        L_0x004a:
            if (r4 == 0) goto L_0x004d
            return r3
        L_0x004d:
            int r2 = r11.second
            goto L_0x0019
        L_0x0050:
            int r1 = r11.first
            int r1 = r1 + r3
            r11.first = r1
            goto L_0x0006
        L_0x0056:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.RBBITableBuilder.findDuplicateSafeState(com.ibm.icu.text.RBBIRuleBuilder$IntPair):boolean");
    }

    /* access modifiers changed from: package-private */
    public void removeState(RBBIRuleBuilder.IntPair duplStates) {
        int keepState = duplStates.first;
        int duplState = duplStates.second;
        if (keepState >= duplState) {
            throw new AssertionError();
        } else if (duplState < this.fDStates.size()) {
            this.fDStates.remove(duplState);
            int numStates = this.fDStates.size();
            int numCols = this.fRB.fSetBuilder.getNumCharCategories();
            for (int state = 0; state < numStates; state++) {
                RBBIStateDescriptor sd = this.fDStates.get(state);
                for (int col = 0; col < numCols; col++) {
                    int existingVal = sd.fDtran[col];
                    int newVal = existingVal;
                    if (existingVal == duplState) {
                        newVal = keepState;
                    } else if (existingVal > duplState) {
                        newVal = existingVal - 1;
                    }
                    sd.fDtran[col] = newVal;
                }
                if (sd.fAccepting == duplState) {
                    sd.fAccepting = keepState;
                } else if (sd.fAccepting > duplState) {
                    sd.fAccepting--;
                }
                if (sd.fLookAhead == duplState) {
                    sd.fLookAhead = keepState;
                } else if (sd.fLookAhead > duplState) {
                    sd.fLookAhead--;
                }
            }
        } else {
            throw new AssertionError();
        }
    }

    /* access modifiers changed from: package-private */
    public void removeSafeState(RBBIRuleBuilder.IntPair duplStates) {
        int keepState = duplStates.first;
        int duplState = duplStates.second;
        if (keepState >= duplState) {
            throw new AssertionError();
        } else if (duplState < this.fSafeTable.size()) {
            this.fSafeTable.remove(duplState);
            int numStates = this.fSafeTable.size();
            for (int state = 0; state < numStates; state++) {
                short[] row = this.fSafeTable.get(state);
                for (int col = 0; col < row.length; col++) {
                    short existingVal = row[col];
                    int newVal = existingVal;
                    if (existingVal == duplState) {
                        newVal = keepState;
                    } else if (existingVal > duplState) {
                        newVal = existingVal - 1;
                    }
                    row[col] = (short) newVal;
                }
            }
        } else {
            throw new AssertionError();
        }
    }

    /* access modifiers changed from: package-private */
    public int removeDuplicateStates() {
        RBBIRuleBuilder.IntPair dupls = new RBBIRuleBuilder.IntPair(3, 0);
        int numStatesRemoved = 0;
        while (findDuplicateState(dupls)) {
            removeState(dupls);
            numStatesRemoved++;
        }
        return numStatesRemoved;
    }

    /* access modifiers changed from: package-private */
    public int getTableSize() {
        if (this.fRB.fTreeRoots[this.fRootIx] == null) {
            return 0;
        }
        return (16 + (this.fDStates.size() * ((this.fRB.fSetBuilder.getNumCharCategories() * 2) + 8)) + 7) & -8;
    }

    /* access modifiers changed from: package-private */
    public RBBIDataWrapper.RBBIStateTable exportTable() {
        RBBIDataWrapper.RBBIStateTable table = new RBBIDataWrapper.RBBIStateTable();
        if (this.fRB.fTreeRoots[this.fRootIx] == null) {
            return table;
        }
        Assert.assrt(this.fRB.fSetBuilder.getNumCharCategories() < 32767 && this.fDStates.size() < 32767);
        table.fNumStates = this.fDStates.size();
        int rowLen = this.fRB.fSetBuilder.getNumCharCategories() + 4;
        table.fTable = new short[((getTableSize() - 16) / 2)];
        table.fRowLen = rowLen * 2;
        if (this.fRB.fLookAheadHardBreak) {
            table.fFlags |= 1;
        }
        if (this.fRB.fSetBuilder.sawBOF()) {
            table.fFlags |= 2;
        }
        int numCharCategories = this.fRB.fSetBuilder.getNumCharCategories();
        for (int state = 0; state < table.fNumStates; state++) {
            RBBIStateDescriptor sd = this.fDStates.get(state);
            int row = state * rowLen;
            Assert.assrt(-32768 < sd.fAccepting && sd.fAccepting <= 32767);
            Assert.assrt(-32768 < sd.fLookAhead && sd.fLookAhead <= 32767);
            table.fTable[row + 0] = (short) sd.fAccepting;
            table.fTable[row + 1] = (short) sd.fLookAhead;
            table.fTable[row + 2] = (short) sd.fTagsIdx;
            for (int col = 0; col < numCharCategories; col++) {
                table.fTable[row + 4 + col] = (short) sd.fDtran[col];
            }
        }
        return table;
    }

    /* access modifiers changed from: package-private */
    public void buildSafeReverseTable() {
        StringBuilder safePairs = new StringBuilder();
        int numCharClasses = this.fRB.fSetBuilder.getNumCharCategories();
        int numStates = this.fDStates.size();
        for (int c1 = 0; c1 < numCharClasses; c1++) {
            for (int c2 = 0; c2 < numCharClasses; c2++) {
                int wantedEndState = -1;
                int endState = 0;
                for (int startState = 1; startState < numStates; startState++) {
                    endState = this.fDStates.get(this.fDStates.get(startState).fDtran[c1]).fDtran[c2];
                    if (wantedEndState < 0) {
                        wantedEndState = endState;
                    } else if (wantedEndState != endState) {
                        break;
                    }
                }
                if (wantedEndState == endState) {
                    safePairs.append((char) c1);
                    safePairs.append((char) c2);
                }
            }
        }
        if (this.fSafeTable == null) {
            this.fSafeTable = new ArrayList();
            for (int row = 0; row < numCharClasses + 2; row++) {
                this.fSafeTable.add(new short[numCharClasses]);
            }
            short[] startState2 = this.fSafeTable.get(1);
            for (int charClass = 0; charClass < numCharClasses; charClass++) {
                startState2[charClass] = (short) (charClass + 2);
            }
            for (int row2 = 2; row2 < numCharClasses + 2; row2++) {
                System.arraycopy(startState2, 0, this.fSafeTable.get(row2), 0, startState2.length);
            }
            for (int pairIdx = 0; pairIdx < safePairs.length(); pairIdx += 2) {
                this.fSafeTable.get(safePairs.charAt(pairIdx + 1) + 2)[safePairs.charAt(pairIdx)] = 0;
            }
            RBBIRuleBuilder.IntPair states = new RBBIRuleBuilder.IntPair(1, 0);
            while (findDuplicateSafeState(states)) {
                removeSafeState(states);
            }
            return;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: package-private */
    public int getSafeTableSize() {
        List<short[]> list = this.fSafeTable;
        if (list == null) {
            return 0;
        }
        return (16 + (list.size() * ((this.fSafeTable.get(0).length * 2) + 8)) + 7) & -8;
    }

    /* access modifiers changed from: package-private */
    public RBBIDataWrapper.RBBIStateTable exportSafeTable() {
        RBBIDataWrapper.RBBIStateTable table = new RBBIDataWrapper.RBBIStateTable();
        table.fNumStates = this.fSafeTable.size();
        int numCharCategories = this.fSafeTable.get(0).length;
        int rowLen = numCharCategories + 4;
        table.fTable = new short[((getSafeTableSize() - 16) / 2)];
        table.fRowLen = rowLen * 2;
        for (int state = 0; state < table.fNumStates; state++) {
            short[] rowArray = this.fSafeTable.get(state);
            int row = state * rowLen;
            for (int col = 0; col < numCharCategories; col++) {
                table.fTable[row + 4 + col] = rowArray[col];
            }
        }
        return table;
    }

    /* access modifiers changed from: package-private */
    public void printSet(Collection<RBBINode> s) {
        for (RBBINode n : s) {
            RBBINode.printInt(n.fSerialNum, 8);
        }
        System.out.println();
    }

    /* access modifiers changed from: package-private */
    public void printStates() {
        System.out.print("state |           i n p u t     s y m b o l s \n");
        System.out.print("      | Acc  LA    Tag");
        for (int c = 0; c < this.fRB.fSetBuilder.getNumCharCategories(); c++) {
            RBBINode.printInt(c, 3);
        }
        System.out.print("\n");
        System.out.print("      |---------------");
        for (int c2 = 0; c2 < this.fRB.fSetBuilder.getNumCharCategories(); c2++) {
            System.out.print("---");
        }
        System.out.print("\n");
        for (int n = 0; n < this.fDStates.size(); n++) {
            RBBIStateDescriptor sd = this.fDStates.get(n);
            RBBINode.printInt(n, 5);
            System.out.print(" | ");
            RBBINode.printInt(sd.fAccepting, 3);
            RBBINode.printInt(sd.fLookAhead, 4);
            RBBINode.printInt(sd.fTagsIdx, 6);
            System.out.print(" ");
            for (int c3 = 0; c3 < this.fRB.fSetBuilder.getNumCharCategories(); c3++) {
                RBBINode.printInt(sd.fDtran[c3], 3);
            }
            System.out.print("\n");
        }
        System.out.print("\n\n");
    }

    /* access modifiers changed from: package-private */
    public void printReverseTable() {
        System.out.printf("    Safe Reverse Table \n", new Object[0]);
        List<short[]> list = this.fSafeTable;
        if (list == null) {
            System.out.printf("   --- nullptr ---\n", new Object[0]);
            return;
        }
        int numCharCategories = list.get(0).length;
        System.out.printf("state |           i n p u t     s y m b o l s \n", new Object[0]);
        System.out.printf("      | Acc  LA    Tag", new Object[0]);
        for (int c = 0; c < numCharCategories; c++) {
            System.out.printf(" %2d", new Object[]{Integer.valueOf(c)});
        }
        System.out.printf("\n", new Object[0]);
        System.out.printf("      |---------------", new Object[0]);
        for (int c2 = 0; c2 < numCharCategories; c2++) {
            System.out.printf("---", new Object[0]);
        }
        System.out.printf("\n", new Object[0]);
        for (int n = 0; n < this.fSafeTable.size(); n++) {
            short[] rowArray = this.fSafeTable.get(n);
            System.out.printf("  %3d | ", new Object[]{Integer.valueOf(n)});
            System.out.printf("%3d %3d %5d ", new Object[]{0, 0, 0});
            for (int c3 = 0; c3 < numCharCategories; c3++) {
                System.out.printf(" %2d", new Object[]{Short.valueOf(rowArray[c3])});
            }
            System.out.printf("\n", new Object[0]);
        }
        System.out.printf("\n\n", new Object[0]);
    }

    /* access modifiers changed from: package-private */
    public void printRuleStatusTable() {
        int nextRecord = 0;
        List<Integer> tbl = this.fRB.fRuleStatusVals;
        System.out.print("index |  tags \n");
        System.out.print("-------------------\n");
        while (nextRecord < tbl.size()) {
            int thisRecord = nextRecord;
            nextRecord = tbl.get(thisRecord).intValue() + thisRecord + 1;
            RBBINode.printInt(thisRecord, 7);
            for (int i = thisRecord + 1; i < nextRecord; i++) {
                RBBINode.printInt(tbl.get(i).intValue(), 7);
            }
            System.out.print("\n");
        }
        System.out.print("\n\n");
    }
}
