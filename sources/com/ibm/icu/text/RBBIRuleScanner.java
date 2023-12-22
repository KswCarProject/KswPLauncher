package com.ibm.icu.text;

import com.ibm.icu.impl.Assert;
import com.ibm.icu.impl.Utility;
import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.text.RBBIRuleParseTable;
import java.text.ParsePosition;
import java.util.HashMap;

/* loaded from: classes.dex */
class RBBIRuleScanner {
    static final int chLS = 8232;
    static final int chNEL = 133;
    private static final int kStackSize = 100;
    int fCharNum;
    int fLastChar;
    boolean fLookAheadRule;
    int fNextIndex;
    boolean fNoChainInRule;
    int fNodeStackPtr;
    int fOptionStart;
    boolean fQuoteMode;
    RBBIRuleBuilder fRB;
    boolean fReverseRule;
    int fRuleNum;
    UnicodeSet[] fRuleSets;
    int fScanIndex;
    int fStackPtr;
    RBBISymbolTable fSymbolTable;
    private static String gRuleSet_rule_char_pattern = "[^[\\p{Z}\\u0020-\\u007f]-[\\p{L}]-[\\p{N}]]";
    private static String gRuleSet_name_char_pattern = "[_\\p{L}\\p{N}]";
    private static String gRuleSet_digit_char_pattern = "[0-9]";
    private static String gRuleSet_name_start_char_pattern = "[_\\p{L}]";
    private static String gRuleSet_white_space_pattern = "[\\p{Pattern_White_Space}]";
    private static String kAny = "any";

    /* renamed from: fC */
    RBBIRuleChar f161fC = new RBBIRuleChar();
    short[] fStack = new short[100];
    RBBINode[] fNodeStack = new RBBINode[100];
    HashMap<String, RBBISetTableEl> fSetTable = new HashMap<>();
    int fLineNum = 1;

    /* loaded from: classes.dex */
    static class RBBIRuleChar {
        int fChar;
        boolean fEscaped;

        RBBIRuleChar() {
        }
    }

    RBBIRuleScanner(RBBIRuleBuilder rb) {
        UnicodeSet[] unicodeSetArr = new UnicodeSet[10];
        this.fRuleSets = unicodeSetArr;
        this.fRB = rb;
        unicodeSetArr[3] = new UnicodeSet(gRuleSet_rule_char_pattern);
        this.fRuleSets[4] = new UnicodeSet(gRuleSet_white_space_pattern);
        this.fRuleSets[1] = new UnicodeSet(gRuleSet_name_char_pattern);
        this.fRuleSets[2] = new UnicodeSet(gRuleSet_name_start_char_pattern);
        this.fRuleSets[0] = new UnicodeSet(gRuleSet_digit_char_pattern);
        this.fSymbolTable = new RBBISymbolTable(this);
    }

    boolean doParseActions(int action) {
        switch (action) {
            case 1:
                if (this.fNodeStack[this.fNodeStackPtr].fLeftChild != null) {
                    return true;
                }
                error(66058);
                return false;
            case 2:
                RBBINode n = pushNewNode(0);
                findSetFor(kAny, n, null);
                n.fFirstPos = this.fScanIndex;
                n.fLastPos = this.fNextIndex;
                n.fText = this.fRB.fRules.substring(n.fFirstPos, n.fLastPos);
                return true;
            case 3:
                fixOpStack(1);
                RBBINode[] rBBINodeArr = this.fNodeStack;
                int i = this.fNodeStackPtr;
                RBBINode startExprNode = rBBINodeArr[i - 2];
                RBBINode varRefNode = rBBINodeArr[i - 1];
                RBBINode RHSExprNode = rBBINodeArr[i];
                RHSExprNode.fFirstPos = startExprNode.fFirstPos;
                RHSExprNode.fLastPos = this.fScanIndex;
                RHSExprNode.fText = this.fRB.fRules.substring(RHSExprNode.fFirstPos, RHSExprNode.fLastPos);
                varRefNode.fLeftChild = RHSExprNode;
                RHSExprNode.fParent = varRefNode;
                this.fSymbolTable.addEntry(varRefNode.fText, varRefNode);
                this.fNodeStackPtr -= 3;
                return true;
            case 4:
                fixOpStack(1);
                if (this.fRB.fDebugEnv != null && this.fRB.fDebugEnv.indexOf("rtree") >= 0) {
                    printNodeStack("end of rule");
                }
                Assert.assrt(this.fNodeStackPtr == 1);
                RBBINode thisRule = this.fNodeStack[this.fNodeStackPtr];
                if (this.fLookAheadRule) {
                    RBBINode endNode = pushNewNode(6);
                    RBBINode catNode = pushNewNode(8);
                    this.fNodeStackPtr -= 2;
                    catNode.fLeftChild = thisRule;
                    catNode.fRightChild = endNode;
                    this.fNodeStack[this.fNodeStackPtr] = catNode;
                    endNode.fVal = this.fRuleNum;
                    endNode.fLookAheadEnd = true;
                    thisRule = catNode;
                }
                thisRule.fRuleRoot = true;
                if (this.fRB.fChainRules && !this.fNoChainInRule) {
                    thisRule.fChainIn = true;
                }
                int destRules = this.fReverseRule ? 3 : this.fRB.fDefaultTree;
                if (this.fRB.fTreeRoots[destRules] != null) {
                    RBBINode thisRule2 = this.fNodeStack[this.fNodeStackPtr];
                    RBBINode prevRules = this.fRB.fTreeRoots[destRules];
                    RBBINode orNode = pushNewNode(9);
                    orNode.fLeftChild = prevRules;
                    prevRules.fParent = orNode;
                    orNode.fRightChild = thisRule2;
                    thisRule2.fParent = orNode;
                    this.fRB.fTreeRoots[destRules] = orNode;
                } else {
                    this.fRB.fTreeRoots[destRules] = this.fNodeStack[this.fNodeStackPtr];
                }
                this.fReverseRule = false;
                this.fLookAheadRule = false;
                this.fNoChainInRule = false;
                this.fNodeStackPtr = 0;
                return true;
            case 5:
                RBBINode n2 = this.fNodeStack[this.fNodeStackPtr];
                if (n2 == null || n2.fType != 2) {
                    error(66049);
                    return true;
                }
                n2.fLastPos = this.fScanIndex;
                n2.fText = this.fRB.fRules.substring(n2.fFirstPos + 1, n2.fLastPos);
                n2.fLeftChild = this.fSymbolTable.lookupNode(n2.fText);
                return true;
            case 6:
                return false;
            case 7:
                fixOpStack(4);
                RBBINode[] rBBINodeArr2 = this.fNodeStack;
                int i2 = this.fNodeStackPtr;
                this.fNodeStackPtr = i2 - 1;
                RBBINode operandNode = rBBINodeArr2[i2];
                RBBINode catNode2 = pushNewNode(8);
                catNode2.fLeftChild = operandNode;
                operandNode.fParent = catNode2;
                return true;
            case 8:
            case 13:
                return true;
            case 9:
                fixOpStack(4);
                RBBINode[] rBBINodeArr3 = this.fNodeStack;
                int i3 = this.fNodeStackPtr;
                this.fNodeStackPtr = i3 - 1;
                RBBINode operandNode2 = rBBINodeArr3[i3];
                RBBINode orNode2 = pushNewNode(9);
                orNode2.fLeftChild = operandNode2;
                operandNode2.fParent = orNode2;
                return true;
            case 10:
                fixOpStack(2);
                return true;
            case 11:
                pushNewNode(7);
                this.fRuleNum++;
                return true;
            case 12:
                pushNewNode(15);
                return true;
            case 14:
                this.fNoChainInRule = true;
                return true;
            case 15:
                String opt = this.fRB.fRules.substring(this.fOptionStart, this.fScanIndex);
                if (opt.equals("chain")) {
                    this.fRB.fChainRules = true;
                    return true;
                } else if (opt.equals("LBCMNoChain")) {
                    this.fRB.fLBCMNoChain = true;
                    return true;
                } else if (opt.equals("forward")) {
                    this.fRB.fDefaultTree = 0;
                    return true;
                } else if (opt.equals("reverse")) {
                    this.fRB.fDefaultTree = 1;
                    return true;
                } else if (opt.equals("safe_forward")) {
                    this.fRB.fDefaultTree = 2;
                    return true;
                } else if (opt.equals("safe_reverse")) {
                    this.fRB.fDefaultTree = 3;
                    return true;
                } else if (opt.equals("lookAheadHardBreak")) {
                    this.fRB.fLookAheadHardBreak = true;
                    return true;
                } else if (opt.equals("quoted_literals_only")) {
                    this.fRuleSets[3].clear();
                    return true;
                } else if (opt.equals("unquoted_literals")) {
                    this.fRuleSets[3].applyPattern(gRuleSet_rule_char_pattern);
                    return true;
                } else {
                    error(66061);
                    return true;
                }
            case 16:
                this.fOptionStart = this.fScanIndex;
                return true;
            case 17:
                this.fReverseRule = true;
                return true;
            case 18:
                RBBINode n3 = pushNewNode(0);
                String s = String.valueOf((char) this.f161fC.fChar);
                findSetFor(s, n3, null);
                n3.fFirstPos = this.fScanIndex;
                n3.fLastPos = this.fNextIndex;
                n3.fText = this.fRB.fRules.substring(n3.fFirstPos, n3.fLastPos);
                return true;
            case 19:
                error(66052);
                return false;
            case 20:
                error(66054);
                return false;
            case 21:
                scanSet();
                return true;
            case 22:
                RBBINode n4 = pushNewNode(4);
                n4.fVal = this.fRuleNum;
                n4.fFirstPos = this.fScanIndex;
                n4.fLastPos = this.fNextIndex;
                n4.fText = this.fRB.fRules.substring(n4.fFirstPos, n4.fLastPos);
                this.fLookAheadRule = true;
                return true;
            case 23:
                this.fNodeStack[this.fNodeStackPtr - 1].fFirstPos = this.fNextIndex;
                pushNewNode(7);
                return true;
            case 24:
                RBBINode n5 = pushNewNode(5);
                n5.fVal = 0;
                n5.fFirstPos = this.fScanIndex;
                n5.fLastPos = this.fNextIndex;
                return true;
            case 25:
                pushNewNode(2).fFirstPos = this.fScanIndex;
                return true;
            case 26:
                RBBINode n6 = this.fNodeStack[this.fNodeStackPtr];
                int v = UCharacter.digit((char) this.f161fC.fChar, 10);
                n6.fVal = (n6.fVal * 10) + v;
                return true;
            case 27:
                error(66062);
                return false;
            case 28:
                RBBINode n7 = this.fNodeStack[this.fNodeStackPtr];
                n7.fLastPos = this.fNextIndex;
                n7.fText = this.fRB.fRules.substring(n7.fFirstPos, n7.fLastPos);
                return true;
            case 29:
                RBBINode[] rBBINodeArr4 = this.fNodeStack;
                int i4 = this.fNodeStackPtr;
                this.fNodeStackPtr = i4 - 1;
                RBBINode operandNode3 = rBBINodeArr4[i4];
                RBBINode plusNode = pushNewNode(11);
                plusNode.fLeftChild = operandNode3;
                operandNode3.fParent = plusNode;
                return true;
            case 30:
                RBBINode[] rBBINodeArr5 = this.fNodeStack;
                int i5 = this.fNodeStackPtr;
                this.fNodeStackPtr = i5 - 1;
                RBBINode operandNode4 = rBBINodeArr5[i5];
                RBBINode qNode = pushNewNode(12);
                qNode.fLeftChild = operandNode4;
                operandNode4.fParent = qNode;
                return true;
            case 31:
                RBBINode[] rBBINodeArr6 = this.fNodeStack;
                int i6 = this.fNodeStackPtr;
                this.fNodeStackPtr = i6 - 1;
                RBBINode operandNode5 = rBBINodeArr6[i6];
                RBBINode starNode = pushNewNode(10);
                starNode.fLeftChild = operandNode5;
                operandNode5.fParent = starNode;
                return true;
            case 32:
                error(66052);
                return true;
            default:
                error(66049);
                return false;
        }
    }

    void error(int e) {
        String s = "Error " + e + " at line " + this.fLineNum + " column " + this.fCharNum;
        IllegalArgumentException ex = new IllegalArgumentException(s);
        throw ex;
    }

    void fixOpStack(int p) {
        RBBINode n;
        while (true) {
            n = this.fNodeStack[this.fNodeStackPtr - 1];
            if (n.fPrecedence == 0) {
                System.out.print("RBBIRuleScanner.fixOpStack, bad operator node");
                error(66049);
                return;
            } else if (n.fPrecedence < p || n.fPrecedence <= 2) {
                break;
            } else {
                n.fRightChild = this.fNodeStack[this.fNodeStackPtr];
                this.fNodeStack[this.fNodeStackPtr].fParent = n;
                this.fNodeStackPtr--;
            }
        }
        if (p <= 2) {
            if (n.fPrecedence != p) {
                error(66056);
            }
            RBBINode[] rBBINodeArr = this.fNodeStack;
            int i = this.fNodeStackPtr;
            rBBINodeArr[i - 1] = rBBINodeArr[i];
            this.fNodeStackPtr = i - 1;
        }
    }

    /* loaded from: classes.dex */
    static class RBBISetTableEl {
        String key;
        RBBINode val;

        RBBISetTableEl() {
        }
    }

    void findSetFor(String s, RBBINode node, UnicodeSet setToAdopt) {
        RBBISetTableEl el = this.fSetTable.get(s);
        if (el != null) {
            node.fLeftChild = el.val;
            Assert.assrt(node.fLeftChild.fType == 1);
            return;
        }
        if (setToAdopt == null) {
            if (s.equals(kAny)) {
                setToAdopt = new UnicodeSet(0, 1114111);
            } else {
                int c = UTF16.charAt(s, 0);
                setToAdopt = new UnicodeSet(c, c);
            }
        }
        RBBINode usetNode = new RBBINode(1);
        usetNode.fInputSet = setToAdopt;
        usetNode.fParent = node;
        node.fLeftChild = usetNode;
        usetNode.fText = s;
        this.fRB.fUSetNodes.add(usetNode);
        RBBISetTableEl el2 = new RBBISetTableEl();
        el2.key = s;
        el2.val = usetNode;
        this.fSetTable.put(el2.key, el2);
    }

    static String stripRules(String rules) {
        StringBuilder strippedRules = new StringBuilder();
        int rulesLength = rules.length();
        boolean skippingSpaces = false;
        int idx = 0;
        while (idx < rulesLength) {
            int cp = rules.codePointAt(idx);
            boolean whiteSpace = UCharacter.hasBinaryProperty(cp, 43);
            if (!skippingSpaces || !whiteSpace) {
                strippedRules.appendCodePoint(cp);
                skippingSpaces = whiteSpace;
            }
            idx = rules.offsetByCodePoints(idx, 1);
        }
        return strippedRules.toString();
    }

    int nextCharLL() {
        if (this.fNextIndex >= this.fRB.fRules.length()) {
            return -1;
        }
        int ch = UTF16.charAt(this.fRB.fRules, this.fNextIndex);
        this.fNextIndex = UTF16.moveCodePointOffset(this.fRB.fRules, this.fNextIndex, 1);
        if (ch == 13 || ch == 133 || ch == chLS || (ch == 10 && this.fLastChar != 13)) {
            this.fLineNum++;
            this.fCharNum = 0;
            if (this.fQuoteMode) {
                error(66057);
                this.fQuoteMode = false;
            }
        } else if (ch != 10) {
            this.fCharNum++;
        }
        this.fLastChar = ch;
        return ch;
    }

    void nextChar(RBBIRuleChar c) {
        this.fScanIndex = this.fNextIndex;
        c.fChar = nextCharLL();
        c.fEscaped = false;
        if (c.fChar == 39) {
            if (UTF16.charAt(this.fRB.fRules, this.fNextIndex) == 39) {
                c.fChar = nextCharLL();
                c.fEscaped = true;
            } else {
                boolean z = !this.fQuoteMode;
                this.fQuoteMode = z;
                if (z) {
                    c.fChar = 40;
                } else {
                    c.fChar = 41;
                }
                c.fEscaped = false;
                return;
            }
        }
        if (this.fQuoteMode) {
            c.fEscaped = true;
            return;
        }
        if (c.fChar == 35) {
            int commentStart = this.fScanIndex;
            do {
                c.fChar = nextCharLL();
                if (c.fChar == -1 || c.fChar == 13 || c.fChar == 10 || c.fChar == 133) {
                    break;
                }
            } while (c.fChar != chLS);
            for (int i = commentStart; i < this.fNextIndex - 1; i++) {
                this.fRB.fStrippedRules.setCharAt(i, ' ');
            }
        }
        int commentStart2 = c.fChar;
        if (commentStart2 != -1 && c.fChar == 92) {
            c.fEscaped = true;
            int[] unescapeIndex = {this.fNextIndex};
            c.fChar = Utility.unescapeAt(this.fRB.fRules, unescapeIndex);
            if (unescapeIndex[0] == this.fNextIndex) {
                error(66050);
            }
            this.fCharNum += unescapeIndex[0] - this.fNextIndex;
            this.fNextIndex = unescapeIndex[0];
        }
    }

    void parse() {
        RBBIRuleParseTable.RBBIRuleTableElement tableEl;
        short s = 1;
        nextChar(this.f161fC);
        while (s != 0) {
            RBBIRuleParseTable.RBBIRuleTableElement tableEl2 = RBBIRuleParseTable.gRuleParseStateTable[s];
            if (this.fRB.fDebugEnv != null && this.fRB.fDebugEnv.indexOf("scan") >= 0) {
                System.out.println("char, line, col = ('" + ((char) this.f161fC.fChar) + "', " + this.fLineNum + ", " + this.fCharNum + "    state = " + tableEl2.fStateName);
            }
            int tableRow = s;
            while (true) {
                tableEl = RBBIRuleParseTable.gRuleParseStateTable[tableRow];
                if (this.fRB.fDebugEnv != null && this.fRB.fDebugEnv.indexOf("scan") >= 0) {
                    System.out.print(".");
                }
                if ((tableEl.fCharClass < 127 && !this.f161fC.fEscaped && tableEl.fCharClass == this.f161fC.fChar) || tableEl.fCharClass == 255 || ((tableEl.fCharClass == 254 && this.f161fC.fEscaped) || ((tableEl.fCharClass == 253 && this.f161fC.fEscaped && (this.f161fC.fChar == 80 || this.f161fC.fChar == 112)) || (tableEl.fCharClass == 252 && this.f161fC.fChar == -1)))) {
                    break;
                }
                if (tableEl.fCharClass >= 128 && tableEl.fCharClass < 240 && !this.f161fC.fEscaped && this.f161fC.fChar != -1) {
                    UnicodeSet uniset = this.fRuleSets[tableEl.fCharClass - 128];
                    if (uniset.contains(this.f161fC.fChar)) {
                        break;
                    }
                }
                tableRow++;
            }
            if (this.fRB.fDebugEnv != null && this.fRB.fDebugEnv.indexOf("scan") >= 0) {
                System.out.println("");
            }
            if (!doParseActions(tableEl.fAction)) {
                break;
            }
            if (tableEl.fPushState != 0) {
                int i = this.fStackPtr + 1;
                this.fStackPtr = i;
                if (i >= 100) {
                    System.out.println("RBBIRuleScanner.parse() - state stack overflow.");
                    error(66049);
                }
                this.fStack[this.fStackPtr] = tableEl.fPushState;
            }
            if (tableEl.fNextChar) {
                nextChar(this.f161fC);
            }
            if (tableEl.fNextState != 255) {
                s = tableEl.fNextState;
            } else {
                short[] sArr = this.fStack;
                int i2 = this.fStackPtr;
                s = sArr[i2];
                int i3 = i2 - 1;
                this.fStackPtr = i3;
                if (i3 < 0) {
                    System.out.println("RBBIRuleScanner.parse() - state stack underflow.");
                    error(66049);
                }
            }
        }
        if (this.fRB.fTreeRoots[0] == null) {
            error(66052);
        }
        if (this.fRB.fDebugEnv != null && this.fRB.fDebugEnv.indexOf("symbols") >= 0) {
            this.fSymbolTable.rbbiSymtablePrint();
        }
        if (this.fRB.fDebugEnv != null && this.fRB.fDebugEnv.indexOf("ptree") >= 0) {
            System.out.println("Completed Forward Rules Parse Tree...");
            this.fRB.fTreeRoots[0].printTree(true);
            System.out.println("\nCompleted Reverse Rules Parse Tree...");
            this.fRB.fTreeRoots[1].printTree(true);
            System.out.println("\nCompleted Safe Point Forward Rules Parse Tree...");
            if (this.fRB.fTreeRoots[2] != null) {
                this.fRB.fTreeRoots[2].printTree(true);
            } else {
                System.out.println("  -- null -- ");
            }
            System.out.println("\nCompleted Safe Point Reverse Rules Parse Tree...");
            if (this.fRB.fTreeRoots[3] != null) {
                this.fRB.fTreeRoots[3].printTree(true);
            } else {
                System.out.println("  -- null -- ");
            }
        }
    }

    void printNodeStack(String title) {
        System.out.println(title + ".  Dumping node stack...\n");
        for (int i = this.fNodeStackPtr; i > 0; i--) {
            this.fNodeStack[i].printTree(true);
        }
    }

    RBBINode pushNewNode(int nodeType) {
        int i = this.fNodeStackPtr + 1;
        this.fNodeStackPtr = i;
        if (i >= 100) {
            System.out.println("RBBIRuleScanner.pushNewNode - stack overflow.");
            error(66049);
        }
        this.fNodeStack[this.fNodeStackPtr] = new RBBINode(nodeType);
        return this.fNodeStack[this.fNodeStackPtr];
    }

    void scanSet() {
        UnicodeSet uset = null;
        ParsePosition pos = new ParsePosition(this.fScanIndex);
        int startPos = this.fScanIndex;
        try {
            uset = new UnicodeSet(this.fRB.fRules, pos, this.fSymbolTable, 1);
        } catch (Exception e) {
            error(66063);
        }
        if (uset.isEmpty()) {
            error(66060);
        }
        int i = pos.getIndex();
        while (this.fNextIndex < i) {
            nextCharLL();
        }
        RBBINode n = pushNewNode(0);
        n.fFirstPos = startPos;
        n.fLastPos = this.fNextIndex;
        n.fText = this.fRB.fRules.substring(n.fFirstPos, n.fLastPos);
        findSetFor(n.fText, n, uset);
    }
}
