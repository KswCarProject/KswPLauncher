package com.ibm.icu.text;

import com.ibm.icu.impl.Assert;
import com.ibm.icu.impl.Utility;
import com.ibm.icu.lang.UCharacter;
import java.text.ParsePosition;
import java.util.HashMap;

class RBBIRuleScanner {
    static final int chLS = 8232;
    static final int chNEL = 133;
    private static String gRuleSet_digit_char_pattern = "[0-9]";
    private static String gRuleSet_name_char_pattern = "[_\\p{L}\\p{N}]";
    private static String gRuleSet_name_start_char_pattern = "[_\\p{L}]";
    private static String gRuleSet_rule_char_pattern = "[^[\\p{Z}\\u0020-\\u007f]-[\\p{L}]-[\\p{N}]]";
    private static String gRuleSet_white_space_pattern = "[\\p{Pattern_White_Space}]";
    private static String kAny = "any";
    private static final int kStackSize = 100;
    RBBIRuleChar fC = new RBBIRuleChar();
    int fCharNum;
    int fLastChar;
    int fLineNum;
    boolean fLookAheadRule;
    int fNextIndex;
    boolean fNoChainInRule;
    RBBINode[] fNodeStack = new RBBINode[100];
    int fNodeStackPtr;
    int fOptionStart;
    boolean fQuoteMode;
    RBBIRuleBuilder fRB;
    boolean fReverseRule;
    int fRuleNum;
    UnicodeSet[] fRuleSets;
    int fScanIndex;
    HashMap<String, RBBISetTableEl> fSetTable = new HashMap<>();
    short[] fStack = new short[100];
    int fStackPtr;
    RBBISymbolTable fSymbolTable;

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
        this.fLineNum = 1;
        unicodeSetArr[3] = new UnicodeSet(gRuleSet_rule_char_pattern);
        this.fRuleSets[4] = new UnicodeSet(gRuleSet_white_space_pattern);
        this.fRuleSets[1] = new UnicodeSet(gRuleSet_name_char_pattern);
        this.fRuleSets[2] = new UnicodeSet(gRuleSet_name_start_char_pattern);
        this.fRuleSets[0] = new UnicodeSet(gRuleSet_digit_char_pattern);
        this.fSymbolTable = new RBBISymbolTable(this);
    }

    /* access modifiers changed from: package-private */
    public boolean doParseActions(int action) {
        int i = 3;
        switch (action) {
            case 1:
                if (this.fNodeStack[this.fNodeStackPtr].fLeftChild != null) {
                    return true;
                }
                error(66058);
                return false;
            case 2:
                RBBINode n = pushNewNode(0);
                findSetFor(kAny, n, (UnicodeSet) null);
                n.fFirstPos = this.fScanIndex;
                n.fLastPos = this.fNextIndex;
                n.fText = this.fRB.fRules.substring(n.fFirstPos, n.fLastPos);
                return true;
            case 3:
                fixOpStack(1);
                RBBINode[] rBBINodeArr = this.fNodeStack;
                int i2 = this.fNodeStackPtr;
                RBBINode startExprNode = rBBINodeArr[i2 - 2];
                RBBINode varRefNode = rBBINodeArr[i2 - 1];
                RBBINode RHSExprNode = rBBINodeArr[i2];
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
                if (!this.fReverseRule) {
                    i = this.fRB.fDefaultTree;
                }
                int destRules = i;
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
                int i3 = this.fNodeStackPtr;
                this.fNodeStackPtr = i3 - 1;
                RBBINode operandNode = rBBINodeArr2[i3];
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
                int i4 = this.fNodeStackPtr;
                this.fNodeStackPtr = i4 - 1;
                RBBINode operandNode2 = rBBINodeArr3[i4];
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
                findSetFor(String.valueOf((char) this.fC.fChar), n3, (UnicodeSet) null);
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
                n6.fVal = (n6.fVal * 10) + UCharacter.digit((char) this.fC.fChar, 10);
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
                int i5 = this.fNodeStackPtr;
                this.fNodeStackPtr = i5 - 1;
                RBBINode operandNode3 = rBBINodeArr4[i5];
                RBBINode plusNode = pushNewNode(11);
                plusNode.fLeftChild = operandNode3;
                operandNode3.fParent = plusNode;
                return true;
            case 30:
                RBBINode[] rBBINodeArr5 = this.fNodeStack;
                int i6 = this.fNodeStackPtr;
                this.fNodeStackPtr = i6 - 1;
                RBBINode operandNode4 = rBBINodeArr5[i6];
                RBBINode qNode = pushNewNode(12);
                qNode.fLeftChild = operandNode4;
                operandNode4.fParent = qNode;
                return true;
            case 31:
                RBBINode[] rBBINodeArr6 = this.fNodeStack;
                int i7 = this.fNodeStackPtr;
                this.fNodeStackPtr = i7 - 1;
                RBBINode operandNode5 = rBBINodeArr6[i7];
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

    /* access modifiers changed from: package-private */
    public void error(int e) {
        throw new IllegalArgumentException("Error " + e + " at line " + this.fLineNum + " column " + this.fCharNum);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void fixOpStack(int r6) {
        /*
            r5 = this;
        L_0x0000:
            com.ibm.icu.text.RBBINode[] r0 = r5.fNodeStack
            int r1 = r5.fNodeStackPtr
            int r1 = r1 + -1
            r0 = r0[r1]
            int r1 = r0.fPrecedence
            if (r1 != 0) goto L_0x001a
            java.io.PrintStream r1 = java.lang.System.out
            java.lang.String r2 = "RBBIRuleScanner.fixOpStack, bad operator node"
            r1.print(r2)
            r1 = 66049(0x10201, float:9.2554E-41)
            r5.error(r1)
            return
        L_0x001a:
            int r1 = r0.fPrecedence
            r2 = 2
            if (r1 < r6) goto L_0x003b
            int r1 = r0.fPrecedence
            if (r1 > r2) goto L_0x0024
            goto L_0x003b
        L_0x0024:
            com.ibm.icu.text.RBBINode[] r1 = r5.fNodeStack
            int r2 = r5.fNodeStackPtr
            r1 = r1[r2]
            r0.fRightChild = r1
            com.ibm.icu.text.RBBINode[] r1 = r5.fNodeStack
            int r2 = r5.fNodeStackPtr
            r1 = r1[r2]
            r1.fParent = r0
            int r1 = r5.fNodeStackPtr
            int r1 = r1 + -1
            r5.fNodeStackPtr = r1
            goto L_0x0000
        L_0x003b:
            if (r6 > r2) goto L_0x0055
            int r1 = r0.fPrecedence
            if (r1 == r6) goto L_0x0047
            r1 = 66056(0x10208, float:9.2564E-41)
            r5.error(r1)
        L_0x0047:
            com.ibm.icu.text.RBBINode[] r1 = r5.fNodeStack
            int r2 = r5.fNodeStackPtr
            int r3 = r2 + -1
            r4 = r1[r2]
            r1[r3] = r4
            int r2 = r2 + -1
            r5.fNodeStackPtr = r2
        L_0x0055:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.RBBIRuleScanner.fixOpStack(int):void");
    }

    static class RBBISetTableEl {
        String key;
        RBBINode val;

        RBBISetTableEl() {
        }
    }

    /* access modifiers changed from: package-private */
    public void findSetFor(String s, RBBINode node, UnicodeSet setToAdopt) {
        RBBISetTableEl el = this.fSetTable.get(s);
        boolean z = false;
        if (el != null) {
            node.fLeftChild = el.val;
            if (node.fLeftChild.fType == 1) {
                z = true;
            }
            Assert.assrt(z);
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

    /* access modifiers changed from: package-private */
    public int nextCharLL() {
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

    /* access modifiers changed from: package-private */
    public void nextChar(RBBIRuleChar c) {
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
                if (c.fChar == -1 || c.fChar == 13 || c.fChar == 10 || c.fChar == 133 || c.fChar == chLS) {
                }
                c.fChar = nextCharLL();
                break;
            } while (c.fChar == chLS);
            for (int i = commentStart; i < this.fNextIndex - 1; i++) {
                this.fRB.fStrippedRules.setCharAt(i, ' ');
            }
        }
        if (c.fChar != -1 && c.fChar == 92) {
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

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Incorrect type for immutable var: ssa=short, code=int, for r0v3, types: [short] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void parse() {
        /*
            r10 = this;
            r0 = 1
            com.ibm.icu.text.RBBIRuleScanner$RBBIRuleChar r1 = r10.fC
            r10.nextChar(r1)
        L_0x0006:
            r1 = 1
            if (r0 != 0) goto L_0x000b
            goto L_0x011d
        L_0x000b:
            com.ibm.icu.text.RBBIRuleParseTable$RBBIRuleTableElement[] r2 = com.ibm.icu.text.RBBIRuleParseTable.gRuleParseStateTable
            r2 = r2[r0]
            com.ibm.icu.text.RBBIRuleBuilder r3 = r10.fRB
            java.lang.String r3 = r3.fDebugEnv
            java.lang.String r4 = "scan"
            if (r3 == 0) goto L_0x0062
            com.ibm.icu.text.RBBIRuleBuilder r3 = r10.fRB
            java.lang.String r3 = r3.fDebugEnv
            int r3 = r3.indexOf(r4)
            if (r3 < 0) goto L_0x0062
            java.io.PrintStream r3 = java.lang.System.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "char, line, col = ('"
            java.lang.StringBuilder r5 = r5.append(r6)
            com.ibm.icu.text.RBBIRuleScanner$RBBIRuleChar r6 = r10.fC
            int r6 = r6.fChar
            char r6 = (char) r6
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r6 = "', "
            java.lang.StringBuilder r5 = r5.append(r6)
            int r6 = r10.fLineNum
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r6 = ", "
            java.lang.StringBuilder r5 = r5.append(r6)
            int r6 = r10.fCharNum
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r6 = "    state = "
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r6 = r2.fStateName
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            r3.println(r5)
        L_0x0062:
            r3 = r0
        L_0x0063:
            com.ibm.icu.text.RBBIRuleParseTable$RBBIRuleTableElement[] r5 = com.ibm.icu.text.RBBIRuleParseTable.gRuleParseStateTable
            r2 = r5[r3]
            com.ibm.icu.text.RBBIRuleBuilder r5 = r10.fRB
            java.lang.String r5 = r5.fDebugEnv
            if (r5 == 0) goto L_0x007e
            com.ibm.icu.text.RBBIRuleBuilder r5 = r10.fRB
            java.lang.String r5 = r5.fDebugEnv
            int r5 = r5.indexOf(r4)
            if (r5 < 0) goto L_0x007e
            java.io.PrintStream r5 = java.lang.System.out
            java.lang.String r6 = "."
            r5.print(r6)
        L_0x007e:
            short r5 = r2.fCharClass
            r6 = 127(0x7f, float:1.78E-43)
            r7 = 255(0xff, float:3.57E-43)
            if (r5 >= r6) goto L_0x0096
            com.ibm.icu.text.RBBIRuleScanner$RBBIRuleChar r5 = r10.fC
            boolean r5 = r5.fEscaped
            if (r5 != 0) goto L_0x0096
            short r5 = r2.fCharClass
            com.ibm.icu.text.RBBIRuleScanner$RBBIRuleChar r6 = r10.fC
            int r6 = r6.fChar
            if (r5 != r6) goto L_0x0096
            goto L_0x00fd
        L_0x0096:
            short r5 = r2.fCharClass
            if (r5 != r7) goto L_0x009b
            goto L_0x00fd
        L_0x009b:
            short r5 = r2.fCharClass
            r6 = 254(0xfe, float:3.56E-43)
            if (r5 != r6) goto L_0x00a8
            com.ibm.icu.text.RBBIRuleScanner$RBBIRuleChar r5 = r10.fC
            boolean r5 = r5.fEscaped
            if (r5 == 0) goto L_0x00a8
            goto L_0x00fd
        L_0x00a8:
            short r5 = r2.fCharClass
            r6 = 253(0xfd, float:3.55E-43)
            if (r5 != r6) goto L_0x00c5
            com.ibm.icu.text.RBBIRuleScanner$RBBIRuleChar r5 = r10.fC
            boolean r5 = r5.fEscaped
            if (r5 == 0) goto L_0x00c5
            com.ibm.icu.text.RBBIRuleScanner$RBBIRuleChar r5 = r10.fC
            int r5 = r5.fChar
            r6 = 80
            if (r5 == r6) goto L_0x00fd
            com.ibm.icu.text.RBBIRuleScanner$RBBIRuleChar r5 = r10.fC
            int r5 = r5.fChar
            r6 = 112(0x70, float:1.57E-43)
            if (r5 != r6) goto L_0x00c5
            goto L_0x00fd
        L_0x00c5:
            short r5 = r2.fCharClass
            r6 = 252(0xfc, float:3.53E-43)
            r8 = -1
            if (r5 != r6) goto L_0x00d3
            com.ibm.icu.text.RBBIRuleScanner$RBBIRuleChar r5 = r10.fC
            int r5 = r5.fChar
            if (r5 != r8) goto L_0x00d3
            goto L_0x00fd
        L_0x00d3:
            short r5 = r2.fCharClass
            r6 = 128(0x80, float:1.794E-43)
            if (r5 < r6) goto L_0x0201
            short r5 = r2.fCharClass
            r9 = 240(0xf0, float:3.36E-43)
            if (r5 >= r9) goto L_0x0201
            com.ibm.icu.text.RBBIRuleScanner$RBBIRuleChar r5 = r10.fC
            boolean r5 = r5.fEscaped
            if (r5 != 0) goto L_0x0201
            com.ibm.icu.text.RBBIRuleScanner$RBBIRuleChar r5 = r10.fC
            int r5 = r5.fChar
            if (r5 == r8) goto L_0x0201
            com.ibm.icu.text.UnicodeSet[] r5 = r10.fRuleSets
            short r8 = r2.fCharClass
            int r8 = r8 - r6
            r5 = r5[r8]
            com.ibm.icu.text.RBBIRuleScanner$RBBIRuleChar r6 = r10.fC
            int r6 = r6.fChar
            boolean r6 = r5.contains((int) r6)
            if (r6 == 0) goto L_0x0201
        L_0x00fd:
            com.ibm.icu.text.RBBIRuleBuilder r3 = r10.fRB
            java.lang.String r3 = r3.fDebugEnv
            if (r3 == 0) goto L_0x0114
            com.ibm.icu.text.RBBIRuleBuilder r3 = r10.fRB
            java.lang.String r3 = r3.fDebugEnv
            int r3 = r3.indexOf(r4)
            if (r3 < 0) goto L_0x0114
            java.io.PrintStream r3 = java.lang.System.out
            java.lang.String r4 = ""
            r3.println(r4)
        L_0x0114:
            short r3 = r2.fAction
            boolean r3 = r10.doParseActions(r3)
            if (r3 != 0) goto L_0x01b7
        L_0x011d:
            com.ibm.icu.text.RBBIRuleBuilder r2 = r10.fRB
            com.ibm.icu.text.RBBINode[] r2 = r2.fTreeRoots
            r3 = 0
            r2 = r2[r3]
            if (r2 != 0) goto L_0x012c
            r2 = 66052(0x10204, float:9.2559E-41)
            r10.error(r2)
        L_0x012c:
            com.ibm.icu.text.RBBIRuleBuilder r2 = r10.fRB
            java.lang.String r2 = r2.fDebugEnv
            if (r2 == 0) goto L_0x0144
            com.ibm.icu.text.RBBIRuleBuilder r2 = r10.fRB
            java.lang.String r2 = r2.fDebugEnv
            java.lang.String r4 = "symbols"
            int r2 = r2.indexOf(r4)
            if (r2 < 0) goto L_0x0144
            com.ibm.icu.text.RBBISymbolTable r2 = r10.fSymbolTable
            r2.rbbiSymtablePrint()
        L_0x0144:
            com.ibm.icu.text.RBBIRuleBuilder r2 = r10.fRB
            java.lang.String r2 = r2.fDebugEnv
            if (r2 == 0) goto L_0x01b6
            com.ibm.icu.text.RBBIRuleBuilder r2 = r10.fRB
            java.lang.String r2 = r2.fDebugEnv
            java.lang.String r4 = "ptree"
            int r2 = r2.indexOf(r4)
            if (r2 < 0) goto L_0x01b6
            java.io.PrintStream r2 = java.lang.System.out
            java.lang.String r4 = "Completed Forward Rules Parse Tree..."
            r2.println(r4)
            com.ibm.icu.text.RBBIRuleBuilder r2 = r10.fRB
            com.ibm.icu.text.RBBINode[] r2 = r2.fTreeRoots
            r2 = r2[r3]
            r2.printTree(r1)
            java.io.PrintStream r2 = java.lang.System.out
            java.lang.String r3 = "\nCompleted Reverse Rules Parse Tree..."
            r2.println(r3)
            com.ibm.icu.text.RBBIRuleBuilder r2 = r10.fRB
            com.ibm.icu.text.RBBINode[] r2 = r2.fTreeRoots
            r2 = r2[r1]
            r2.printTree(r1)
            java.io.PrintStream r2 = java.lang.System.out
            java.lang.String r3 = "\nCompleted Safe Point Forward Rules Parse Tree..."
            r2.println(r3)
            com.ibm.icu.text.RBBIRuleBuilder r2 = r10.fRB
            com.ibm.icu.text.RBBINode[] r2 = r2.fTreeRoots
            r3 = 2
            r2 = r2[r3]
            java.lang.String r4 = "  -- null -- "
            if (r2 != 0) goto L_0x018e
            java.io.PrintStream r2 = java.lang.System.out
            r2.println(r4)
            goto L_0x0197
        L_0x018e:
            com.ibm.icu.text.RBBIRuleBuilder r2 = r10.fRB
            com.ibm.icu.text.RBBINode[] r2 = r2.fTreeRoots
            r2 = r2[r3]
            r2.printTree(r1)
        L_0x0197:
            java.io.PrintStream r2 = java.lang.System.out
            java.lang.String r3 = "\nCompleted Safe Point Reverse Rules Parse Tree..."
            r2.println(r3)
            com.ibm.icu.text.RBBIRuleBuilder r2 = r10.fRB
            com.ibm.icu.text.RBBINode[] r2 = r2.fTreeRoots
            r3 = 3
            r2 = r2[r3]
            if (r2 != 0) goto L_0x01ad
            java.io.PrintStream r1 = java.lang.System.out
            r1.println(r4)
            goto L_0x01b6
        L_0x01ad:
            com.ibm.icu.text.RBBIRuleBuilder r2 = r10.fRB
            com.ibm.icu.text.RBBINode[] r2 = r2.fTreeRoots
            r2 = r2[r3]
            r2.printTree(r1)
        L_0x01b6:
            return
        L_0x01b7:
            short r3 = r2.fPushState
            r4 = 66049(0x10201, float:9.2554E-41)
            if (r3 == 0) goto L_0x01d9
            int r3 = r10.fStackPtr
            int r3 = r3 + r1
            r10.fStackPtr = r3
            r5 = 100
            if (r3 < r5) goto L_0x01d1
            java.io.PrintStream r3 = java.lang.System.out
            java.lang.String r5 = "RBBIRuleScanner.parse() - state stack overflow."
            r3.println(r5)
            r10.error(r4)
        L_0x01d1:
            short[] r3 = r10.fStack
            int r5 = r10.fStackPtr
            short r6 = r2.fPushState
            r3[r5] = r6
        L_0x01d9:
            boolean r3 = r2.fNextChar
            if (r3 == 0) goto L_0x01e2
            com.ibm.icu.text.RBBIRuleScanner$RBBIRuleChar r3 = r10.fC
            r10.nextChar(r3)
        L_0x01e2:
            short r3 = r2.fNextState
            if (r3 == r7) goto L_0x01ea
            short r0 = r2.fNextState
            goto L_0x0006
        L_0x01ea:
            short[] r3 = r10.fStack
            int r5 = r10.fStackPtr
            short r0 = r3[r5]
            int r5 = r5 - r1
            r10.fStackPtr = r5
            if (r5 >= 0) goto L_0x0006
            java.io.PrintStream r1 = java.lang.System.out
            java.lang.String r3 = "RBBIRuleScanner.parse() - state stack underflow."
            r1.println(r3)
            r10.error(r4)
            goto L_0x0006
        L_0x0201:
            int r3 = r3 + 1
            goto L_0x0063
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.RBBIRuleScanner.parse():void");
    }

    /* access modifiers changed from: package-private */
    public void printNodeStack(String title) {
        System.out.println(title + ".  Dumping node stack...\n");
        for (int i = this.fNodeStackPtr; i > 0; i--) {
            this.fNodeStack[i].printTree(true);
        }
    }

    /* access modifiers changed from: package-private */
    public RBBINode pushNewNode(int nodeType) {
        int i = this.fNodeStackPtr + 1;
        this.fNodeStackPtr = i;
        if (i >= 100) {
            System.out.println("RBBIRuleScanner.pushNewNode - stack overflow.");
            error(66049);
        }
        this.fNodeStack[this.fNodeStackPtr] = new RBBINode(nodeType);
        return this.fNodeStack[this.fNodeStackPtr];
    }

    /* access modifiers changed from: package-private */
    public void scanSet() {
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
