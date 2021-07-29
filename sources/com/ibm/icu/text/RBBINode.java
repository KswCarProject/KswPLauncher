package com.ibm.icu.text;

import com.ibm.icu.impl.Assert;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class RBBINode {
    static final int endMark = 6;
    static int gLastSerial = 0;
    static final int leafChar = 3;
    static final int lookAhead = 4;
    static final int nodeTypeLimit = 16;
    static final String[] nodeTypeNames = {"setRef", "uset", "varRef", "leafChar", "lookAhead", "tag", "endMark", "opStart", "opCat", "opOr", "opStar", "opPlus", "opQuestion", "opBreak", "opReverse", "opLParen"};
    static final int opBreak = 13;
    static final int opCat = 8;
    static final int opLParen = 15;
    static final int opOr = 9;
    static final int opPlus = 11;
    static final int opQuestion = 12;
    static final int opReverse = 14;
    static final int opStar = 10;
    static final int opStart = 7;
    static final int precLParen = 2;
    static final int precOpCat = 4;
    static final int precOpOr = 3;
    static final int precStart = 1;
    static final int precZero = 0;
    static final int setRef = 0;
    static final int tag = 5;
    static final int uset = 1;
    static final int varRef = 2;
    boolean fChainIn;
    int fFirstPos;
    Set<RBBINode> fFirstPosSet;
    Set<RBBINode> fFollowPos;
    UnicodeSet fInputSet;
    int fLastPos;
    Set<RBBINode> fLastPosSet;
    RBBINode fLeftChild;
    boolean fLookAheadEnd;
    boolean fNullable;
    RBBINode fParent;
    int fPrecedence = 0;
    RBBINode fRightChild;
    boolean fRuleRoot;
    int fSerialNum;
    String fText;
    int fType;
    int fVal;

    RBBINode(int t) {
        Assert.assrt(t < 16);
        int i = gLastSerial + 1;
        gLastSerial = i;
        this.fSerialNum = i;
        this.fType = t;
        this.fFirstPosSet = new HashSet();
        this.fLastPosSet = new HashSet();
        this.fFollowPos = new HashSet();
        if (t == 8) {
            this.fPrecedence = 4;
        } else if (t == 9) {
            this.fPrecedence = 3;
        } else if (t == 7) {
            this.fPrecedence = 1;
        } else if (t == 15) {
            this.fPrecedence = 2;
        } else {
            this.fPrecedence = 0;
        }
    }

    RBBINode(RBBINode other) {
        int i = gLastSerial + 1;
        gLastSerial = i;
        this.fSerialNum = i;
        this.fType = other.fType;
        this.fInputSet = other.fInputSet;
        this.fPrecedence = other.fPrecedence;
        this.fText = other.fText;
        this.fFirstPos = other.fFirstPos;
        this.fLastPos = other.fLastPos;
        this.fNullable = other.fNullable;
        this.fVal = other.fVal;
        this.fRuleRoot = false;
        this.fChainIn = other.fChainIn;
        this.fFirstPosSet = new HashSet(other.fFirstPosSet);
        this.fLastPosSet = new HashSet(other.fLastPosSet);
        this.fFollowPos = new HashSet(other.fFollowPos);
    }

    /* access modifiers changed from: package-private */
    public RBBINode cloneTree() {
        int i = this.fType;
        if (i == 2) {
            return this.fLeftChild.cloneTree();
        }
        if (i == 1) {
            return this;
        }
        RBBINode n = new RBBINode(this);
        RBBINode rBBINode = this.fLeftChild;
        if (rBBINode != null) {
            RBBINode cloneTree = rBBINode.cloneTree();
            n.fLeftChild = cloneTree;
            cloneTree.fParent = n;
        }
        RBBINode rBBINode2 = this.fRightChild;
        if (rBBINode2 == null) {
            return n;
        }
        RBBINode cloneTree2 = rBBINode2.cloneTree();
        n.fRightChild = cloneTree2;
        cloneTree2.fParent = n;
        return n;
    }

    /* access modifiers changed from: package-private */
    public RBBINode flattenVariables() {
        if (this.fType == 2) {
            RBBINode retNode = this.fLeftChild.cloneTree();
            retNode.fRuleRoot = this.fRuleRoot;
            retNode.fChainIn = this.fChainIn;
            return retNode;
        }
        RBBINode retNode2 = this.fLeftChild;
        if (retNode2 != null) {
            RBBINode flattenVariables = retNode2.flattenVariables();
            this.fLeftChild = flattenVariables;
            flattenVariables.fParent = this;
        }
        RBBINode rBBINode = this.fRightChild;
        if (rBBINode != null) {
            RBBINode flattenVariables2 = rBBINode.flattenVariables();
            this.fRightChild = flattenVariables2;
            flattenVariables2.fParent = this;
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public void flattenSets() {
        Assert.assrt(this.fType != 0);
        RBBINode rBBINode = this.fLeftChild;
        if (rBBINode != null) {
            if (rBBINode.fType == 0) {
                RBBINode cloneTree = this.fLeftChild.fLeftChild.fLeftChild.cloneTree();
                this.fLeftChild = cloneTree;
                cloneTree.fParent = this;
            } else {
                rBBINode.flattenSets();
            }
        }
        RBBINode rBBINode2 = this.fRightChild;
        if (rBBINode2 == null) {
            return;
        }
        if (rBBINode2.fType == 0) {
            RBBINode cloneTree2 = this.fRightChild.fLeftChild.fLeftChild.cloneTree();
            this.fRightChild = cloneTree2;
            cloneTree2.fParent = this;
            return;
        }
        rBBINode2.flattenSets();
    }

    /* access modifiers changed from: package-private */
    public void findNodes(List<RBBINode> dest, int kind) {
        if (this.fType == kind) {
            dest.add(this);
        }
        RBBINode rBBINode = this.fLeftChild;
        if (rBBINode != null) {
            rBBINode.findNodes(dest, kind);
        }
        RBBINode rBBINode2 = this.fRightChild;
        if (rBBINode2 != null) {
            rBBINode2.findNodes(dest, kind);
        }
    }

    static void printNode(RBBINode n) {
        if (n == null) {
            System.out.print(" -- null --\n");
        } else {
            printInt(n.fSerialNum, 10);
            printString(nodeTypeNames[n.fType], 11);
            RBBINode rBBINode = n.fParent;
            int i = 0;
            printInt(rBBINode == null ? 0 : rBBINode.fSerialNum, 11);
            RBBINode rBBINode2 = n.fLeftChild;
            printInt(rBBINode2 == null ? 0 : rBBINode2.fSerialNum, 11);
            RBBINode rBBINode3 = n.fRightChild;
            if (rBBINode3 != null) {
                i = rBBINode3.fSerialNum;
            }
            printInt(i, 12);
            printInt(n.fFirstPos, 12);
            printInt(n.fVal, 7);
            if (n.fType == 2) {
                System.out.print(" " + n.fText);
            }
        }
        System.out.println("");
    }

    static void printString(String s, int minWidth) {
        for (int i = minWidth; i < 0; i++) {
            System.out.print(' ');
        }
        for (int i2 = s.length(); i2 < minWidth; i2++) {
            System.out.print(' ');
        }
        System.out.print(s);
    }

    static void printInt(int i, int minWidth) {
        String s = Integer.toString(i);
        printString(s, Math.max(minWidth, s.length() + 1));
    }

    static void printHex(int i, int minWidth) {
        String s = Integer.toString(i, 16);
        printString("00000".substring(0, Math.max(0, 5 - s.length())) + s, minWidth);
    }

    /* access modifiers changed from: package-private */
    public void printTree(boolean printHeading) {
        if (printHeading) {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("    Serial       type     Parent  LeftChild  RightChild    position  value");
        }
        printNode(this);
        if (this.fType != 2) {
            RBBINode rBBINode = this.fLeftChild;
            if (rBBINode != null) {
                rBBINode.printTree(false);
            }
            RBBINode rBBINode2 = this.fRightChild;
            if (rBBINode2 != null) {
                rBBINode2.printTree(false);
            }
        }
    }
}
