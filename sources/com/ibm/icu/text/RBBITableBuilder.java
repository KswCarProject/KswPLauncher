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

/* loaded from: classes.dex */
class RBBITableBuilder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private List<RBBIStateDescriptor> fDStates = new ArrayList();
    private RBBIRuleBuilder fRB;
    private int fRootIx;
    private List<short[]> fSafeTable;

    /* loaded from: classes.dex */
    static class RBBIStateDescriptor {
        int fAccepting;
        int[] fDtran;
        int fLookAhead;
        boolean fMarked;
        int fTagsIdx;
        SortedSet<Integer> fTagVals = new TreeSet();
        Set<RBBINode> fPositions = new HashSet();

        RBBIStateDescriptor(int maxInputSymbol) {
            this.fDtran = new int[maxInputSymbol + 1];
        }
    }

    RBBITableBuilder(RBBIRuleBuilder rb, int rootNodeIx) {
        this.fRootIx = rootNodeIx;
        this.fRB = rb;
    }

    void buildForwardTable() {
        if (this.fRB.fTreeRoots[this.fRootIx] == null) {
            return;
        }
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

    void calcNullable(RBBINode n) {
        if (n == null) {
            return;
        }
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

    void calcFirstPos(RBBINode n) {
        if (n == null) {
            return;
        }
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

    void calcLastPos(RBBINode n) {
        if (n == null) {
            return;
        }
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

    void calcFollowPos(RBBINode n) {
        if (n == null || n.fType == 3 || n.fType == 6) {
            return;
        }
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

    void addRuleRootNodes(List<RBBINode> dest, RBBINode node) {
        if (node == null) {
            return;
        }
        if (node.fRuleRoot) {
            dest.add(node);
            return;
        }
        addRuleRootNodes(dest, node.fLeftChild);
        addRuleRootNodes(dest, node.fRightChild);
    }

    void calcChainedFollowPos(RBBINode tree) {
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
                RBBINode endMarkerNode = it.next();
                if (tNode.fFollowPos.contains(endMarkerNode)) {
                    endNode = tNode;
                    break;
                }
            }
            if (endNode != null) {
                if (this.fRB.fLBCMNoChain && (c = this.fRB.fSetBuilder.getFirstChar(endNode.fVal)) != -1) {
                    int cLBProp = UCharacter.getIntPropertyValue(c, 4104);
                    if (cLBProp == 9) {
                    }
                }
                for (RBBINode startNode : matchStartNodes) {
                    if (startNode.fType == 3 && endNode.fVal == startNode.fVal) {
                        endNode.fFollowPos.addAll(startNode.fFollowPos);
                    }
                }
            }
        }
    }

    void bofFixup() {
        RBBINode bofNode = this.fRB.fTreeRoots[this.fRootIx].fLeftChild.fLeftChild;
        Assert.assrt(bofNode.fType == 3);
        Assert.assrt(bofNode.fVal == 2);
        Set<RBBINode> matchStartNodes = this.fRB.fTreeRoots[this.fRootIx].fLeftChild.fRightChild.fFirstPosSet;
        for (RBBINode startNode : matchStartNodes) {
            if (startNode.fType == 3 && startNode.fVal == bofNode.fVal) {
                bofNode.fFollowPos.addAll(startNode.fFollowPos);
            }
        }
    }

    void buildStateTable() {
        int lastInputSymbol = this.fRB.fSetBuilder.getNumCharCategories() - 1;
        RBBIStateDescriptor failState = new RBBIStateDescriptor(lastInputSymbol);
        this.fDStates.add(failState);
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
                if (temp.fMarked) {
                    tx++;
                } else {
                    T = temp;
                    break;
                }
            }
            if (T != null) {
                T.fMarked = true;
                for (int a = 1; a <= lastInputSymbol; a++) {
                    Set<RBBINode> U = null;
                    for (RBBINode p : T.fPositions) {
                        if (p.fType == 3 && p.fVal == a) {
                            if (U == null) {
                                U = new HashSet();
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
                            if (!U.equals(temp2.fPositions)) {
                                ix++;
                            } else {
                                U = temp2.fPositions;
                                ux = ix;
                                UinDstates = true;
                                break;
                            }
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

    void flagAcceptingStates() {
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

    void flagLookAheadStates() {
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

    void flagTaggedStates() {
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

    void mergeRuleStatusVals() {
        if (this.fRB.fRuleStatusVals.size() == 0) {
            this.fRB.fRuleStatusVals.add(1);
            this.fRB.fRuleStatusVals.add(0);
            SortedSet<Integer> s0 = new TreeSet<>();
            this.fRB.fStatusSets.put(s0, 0);
            SortedSet<Integer> s1 = new TreeSet<>();
            s1.add(0);
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

    void printPosSets(RBBINode n) {
        if (n == null) {
            return;
        }
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

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0043, code lost:
        r10.first++;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    boolean findDuplCharClassFrom(RBBIRuleBuilder.IntPair categories) {
        int numStates = this.fDStates.size();
        int numCols = this.fRB.fSetBuilder.getNumCharCategories();
        int table_base = 0;
        int table_dupl = 0;
        while (categories.first < numCols - 1) {
            int i = categories.first;
            while (true) {
                categories.second = i + 1;
                if (categories.second < numCols) {
                    for (int state = 0; state < numStates; state++) {
                        RBBIStateDescriptor sd = this.fDStates.get(state);
                        table_base = sd.fDtran[categories.first];
                        table_dupl = sd.fDtran[categories.second];
                        if (table_base != table_dupl) {
                            break;
                        }
                    }
                    if (table_base == table_dupl) {
                        return true;
                    }
                    i = categories.second;
                }
            }
        }
        return false;
    }

    void removeColumn(int column) {
        int numStates = this.fDStates.size();
        for (int state = 0; state < numStates; state++) {
            RBBIStateDescriptor sd = this.fDStates.get(state);
            if (column >= sd.fDtran.length) {
                throw new AssertionError();
            }
            int[] newArray = Arrays.copyOf(sd.fDtran, sd.fDtran.length - 1);
            System.arraycopy(sd.fDtran, column + 1, newArray, column, newArray.length - column);
            sd.fDtran = newArray;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x006e, code lost:
        r11.first++;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    boolean findDuplicateState(RBBIRuleBuilder.IntPair states) {
        int numStates = this.fDStates.size();
        int numCols = this.fRB.fSetBuilder.getNumCharCategories();
        while (states.first < numStates - 1) {
            RBBIStateDescriptor firstSD = this.fDStates.get(states.first);
            int i = states.first;
            while (true) {
                states.second = i + 1;
                if (states.second < numStates) {
                    RBBIStateDescriptor duplSD = this.fDStates.get(states.second);
                    if (firstSD.fAccepting == duplSD.fAccepting && firstSD.fLookAhead == duplSD.fLookAhead && firstSD.fTagsIdx == duplSD.fTagsIdx) {
                        boolean rowsMatch = true;
                        for (int col = 0; col < numCols; col++) {
                            int firstVal = firstSD.fDtran[col];
                            int duplVal = duplSD.fDtran[col];
                            if (firstVal != duplVal && ((firstVal != states.first && firstVal != states.second) || (duplVal != states.first && duplVal != states.second))) {
                                rowsMatch = false;
                                break;
                            }
                        }
                        if (rowsMatch) {
                            return true;
                        }
                    }
                    i = states.second;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0050, code lost:
        r11.first++;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    boolean findDuplicateSafeState(RBBIRuleBuilder.IntPair states) {
        int numStates = this.fSafeTable.size();
        while (states.first < numStates - 1) {
            short[] firstRow = this.fSafeTable.get(states.first);
            int i = states.first;
            while (true) {
                states.second = i + 1;
                if (states.second < numStates) {
                    short[] duplRow = this.fSafeTable.get(states.second);
                    boolean rowsMatch = true;
                    int numCols = firstRow.length;
                    for (int col = 0; col < numCols; col++) {
                        short s = firstRow[col];
                        short s2 = duplRow[col];
                        if (s != s2 && ((s != states.first && s != states.second) || (s2 != states.first && s2 != states.second))) {
                            rowsMatch = false;
                            break;
                        }
                    }
                    if (rowsMatch) {
                        return true;
                    }
                    i = states.second;
                }
            }
        }
        return false;
    }

    void removeState(RBBIRuleBuilder.IntPair duplStates) {
        int keepState = duplStates.first;
        int duplState = duplStates.second;
        if (keepState >= duplState) {
            throw new AssertionError();
        }
        if (duplState >= this.fDStates.size()) {
            throw new AssertionError();
        }
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
            int col2 = sd.fAccepting;
            if (col2 == duplState) {
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
    }

    void removeSafeState(RBBIRuleBuilder.IntPair duplStates) {
        int keepState = duplStates.first;
        int duplState = duplStates.second;
        if (keepState >= duplState) {
            throw new AssertionError();
        }
        if (duplState >= this.fSafeTable.size()) {
            throw new AssertionError();
        }
        this.fSafeTable.remove(duplState);
        int numStates = this.fSafeTable.size();
        for (int state = 0; state < numStates; state++) {
            short[] row = this.fSafeTable.get(state);
            for (int col = 0; col < row.length; col++) {
                short s = row[col];
                int newVal = s;
                if (s == duplState) {
                    newVal = keepState;
                } else if (s > duplState) {
                    newVal = s - 1;
                }
                row[col] = (short) newVal;
            }
        }
    }

    int removeDuplicateStates() {
        RBBIRuleBuilder.IntPair dupls = new RBBIRuleBuilder.IntPair(3, 0);
        int numStatesRemoved = 0;
        while (findDuplicateState(dupls)) {
            removeState(dupls);
            numStatesRemoved++;
        }
        return numStatesRemoved;
    }

    int getTableSize() {
        if (this.fRB.fTreeRoots[this.fRootIx] == null) {
            return 0;
        }
        int numRows = this.fDStates.size();
        int numCols = this.fRB.fSetBuilder.getNumCharCategories();
        int rowSize = (numCols * 2) + 8;
        int size = 16 + (numRows * rowSize);
        return (size + 7) & (-8);
    }

    RBBIDataWrapper.RBBIStateTable exportTable() {
        RBBIDataWrapper.RBBIStateTable table = new RBBIDataWrapper.RBBIStateTable();
        if (this.fRB.fTreeRoots[this.fRootIx] == null) {
            return table;
        }
        Assert.assrt(this.fRB.fSetBuilder.getNumCharCategories() < 32767 && this.fDStates.size() < 32767);
        table.fNumStates = this.fDStates.size();
        int rowLen = this.fRB.fSetBuilder.getNumCharCategories() + 4;
        int tableSize = (getTableSize() - 16) / 2;
        table.fTable = new short[tableSize];
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

    void buildSafeReverseTable() {
        StringBuilder safePairs = new StringBuilder();
        int numCharClasses = this.fRB.fSetBuilder.getNumCharCategories();
        int numStates = this.fDStates.size();
        for (int c1 = 0; c1 < numCharClasses; c1++) {
            for (int c2 = 0; c2 < numCharClasses; c2++) {
                int wantedEndState = -1;
                int endState = 0;
                for (int startState = 1; startState < numStates; startState++) {
                    RBBIStateDescriptor startStateD = this.fDStates.get(startState);
                    int s2 = startStateD.fDtran[c1];
                    RBBIStateDescriptor s2StateD = this.fDStates.get(s2);
                    endState = s2StateD.fDtran[c2];
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
        if (this.fSafeTable != null) {
            throw new AssertionError();
        }
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
            int c12 = safePairs.charAt(pairIdx);
            int c22 = safePairs.charAt(pairIdx + 1);
            short[] rowState = this.fSafeTable.get(c22 + 2);
            rowState[c12] = 0;
        }
        RBBIRuleBuilder.IntPair states = new RBBIRuleBuilder.IntPair(1, 0);
        while (findDuplicateSafeState(states)) {
            removeSafeState(states);
        }
    }

    int getSafeTableSize() {
        List<short[]> list = this.fSafeTable;
        if (list == null) {
            return 0;
        }
        int numRows = list.size();
        int numCols = this.fSafeTable.get(0).length;
        int rowSize = (numCols * 2) + 8;
        int size = 16 + (numRows * rowSize);
        return (size + 7) & (-8);
    }

    RBBIDataWrapper.RBBIStateTable exportSafeTable() {
        RBBIDataWrapper.RBBIStateTable table = new RBBIDataWrapper.RBBIStateTable();
        table.fNumStates = this.fSafeTable.size();
        int numCharCategories = this.fSafeTable.get(0).length;
        int rowLen = numCharCategories + 4;
        int tableSize = (getSafeTableSize() - 16) / 2;
        table.fTable = new short[tableSize];
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

    void printSet(Collection<RBBINode> s) {
        for (RBBINode n : s) {
            RBBINode.printInt(n.fSerialNum, 8);
        }
        System.out.println();
    }

    void printStates() {
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

    void printReverseTable() {
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
            System.out.printf(" %2d", Integer.valueOf(c));
        }
        System.out.printf("\n", new Object[0]);
        System.out.printf("      |---------------", new Object[0]);
        for (int c2 = 0; c2 < numCharCategories; c2++) {
            System.out.printf("---", new Object[0]);
        }
        System.out.printf("\n", new Object[0]);
        for (int n = 0; n < this.fSafeTable.size(); n++) {
            short[] rowArray = this.fSafeTable.get(n);
            System.out.printf("  %3d | ", Integer.valueOf(n));
            System.out.printf("%3d %3d %5d ", 0, 0, 0);
            for (int c3 = 0; c3 < numCharCategories; c3++) {
                System.out.printf(" %2d", Short.valueOf(rowArray[c3]));
            }
            System.out.printf("\n", new Object[0]);
        }
        System.out.printf("\n\n", new Object[0]);
    }

    void printRuleStatusTable() {
        int nextRecord = 0;
        List<Integer> tbl = this.fRB.fRuleStatusVals;
        System.out.print("index |  tags \n");
        System.out.print("-------------------\n");
        while (nextRecord < tbl.size()) {
            int thisRecord = nextRecord;
            nextRecord = tbl.get(thisRecord).intValue() + thisRecord + 1;
            RBBINode.printInt(thisRecord, 7);
            for (int i = thisRecord + 1; i < nextRecord; i++) {
                int val = tbl.get(i).intValue();
                RBBINode.printInt(val, 7);
            }
            System.out.print("\n");
        }
        System.out.print("\n\n");
    }
}
