package com.ibm.icu.text;

import com.ibm.icu.impl.Assert;
import com.ibm.icu.impl.ICUBinary;
import com.ibm.icu.impl.ICUDebug;
import com.ibm.icu.impl.RBBIDataWrapper;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
class RBBIRuleBuilder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int U_BRK_ASSIGN_ERROR = 66054;
    static final int U_BRK_ERROR_LIMIT = 66064;
    static final int U_BRK_ERROR_START = 66048;
    static final int U_BRK_HEX_DIGITS_EXPECTED = 66050;
    static final int U_BRK_INIT_ERROR = 66059;
    static final int U_BRK_INTERNAL_ERROR = 66049;
    static final int U_BRK_MALFORMED_RULE_TAG = 66062;
    static final int U_BRK_MALFORMED_SET = 66063;
    static final int U_BRK_MISMATCHED_PAREN = 66056;
    static final int U_BRK_NEW_LINE_IN_QUOTED_STRING = 66057;
    static final int U_BRK_RULE_EMPTY_SET = 66060;
    static final int U_BRK_RULE_SYNTAX = 66052;
    static final int U_BRK_SEMICOLON_EXPECTED = 66051;
    static final int U_BRK_UNCLOSED_SET = 66053;
    static final int U_BRK_UNDEFINED_VARIABLE = 66058;
    static final int U_BRK_UNRECOGNIZED_OPTION = 66061;
    static final int U_BRK_VARIABLE_REDFINITION = 66055;
    static final int fForwardTree = 0;
    static final int fReverseTree = 1;
    static final int fSafeFwdTree = 2;
    static final int fSafeRevTree = 3;
    boolean fChainRules;
    String fDebugEnv;
    RBBITableBuilder fForwardTable;
    boolean fLBCMNoChain;
    boolean fLookAheadHardBreak;
    List<Integer> fRuleStatusVals;
    String fRules;
    RBBIRuleScanner fScanner;
    RBBISetBuilder fSetBuilder;
    StringBuilder fStrippedRules;
    List<RBBINode> fUSetNodes;
    RBBINode[] fTreeRoots = new RBBINode[4];
    int fDefaultTree = 0;
    Map<Set<Integer>, Integer> fStatusSets = new HashMap();

    RBBIRuleBuilder(String rules) {
        this.fDebugEnv = ICUDebug.enabled("rbbi") ? ICUDebug.value("rbbi") : null;
        this.fRules = rules;
        this.fStrippedRules = new StringBuilder(rules);
        this.fUSetNodes = new ArrayList();
        this.fRuleStatusVals = new ArrayList();
        this.fScanner = new RBBIRuleScanner(this);
        this.fSetBuilder = new RBBISetBuilder(this);
    }

    static final int align8(int i) {
        return (i + 7) & (-8);
    }

    void flattenData(OutputStream os) throws IOException {
        DataOutputStream dos = new DataOutputStream(os);
        String strippedRules = RBBIRuleScanner.stripRules(this.fStrippedRules.toString());
        int forwardTableSize = align8(this.fForwardTable.getTableSize());
        int reverseTableSize = align8(this.fForwardTable.getSafeTableSize());
        int trieSize = align8(this.fSetBuilder.getTrieSize());
        int statusTableSize = align8(this.fRuleStatusVals.size() * 4);
        int rulesSize = align8(strippedRules.length() * 2);
        int totalSize = 80 + forwardTableSize + reverseTableSize + statusTableSize + trieSize + rulesSize;
        int outputPos = 0;
        ICUBinary.writeHeader(1114794784, 83886080, 0, dos);
        int[] header = {45472, 83886080, totalSize, this.fSetBuilder.getNumCharCategories(), 80, forwardTableSize, header[4] + forwardTableSize, reverseTableSize, header[6] + header[7], this.fSetBuilder.getTrieSize(), header[12] + statusTableSize, strippedRules.length() * 2, header[8] + header[9], statusTableSize};
        for (int i : header) {
            dos.writeInt(i);
            outputPos += 4;
        }
        RBBIDataWrapper.RBBIStateTable table = this.fForwardTable.exportTable();
        if (outputPos != header[4]) {
            throw new AssertionError();
        }
        int outputPos2 = outputPos + table.put(dos);
        RBBIDataWrapper.RBBIStateTable table2 = this.fForwardTable.exportSafeTable();
        Assert.assrt(outputPos2 == header[6]);
        int outputPos3 = outputPos2 + table2.put(dos);
        Assert.assrt(outputPos3 == header[8]);
        this.fSetBuilder.serializeTrie(os);
        int outputPos4 = outputPos3 + header[9];
        while (outputPos4 % 8 != 0) {
            dos.write(0);
            outputPos4++;
        }
        Assert.assrt(outputPos4 == header[12]);
        for (Integer val : this.fRuleStatusVals) {
            dos.writeInt(val.intValue());
            outputPos4 += 4;
        }
        while (outputPos4 % 8 != 0) {
            dos.write(0);
            outputPos4++;
        }
        Assert.assrt(outputPos4 == header[10]);
        dos.writeChars(strippedRules);
        for (int outputPos5 = outputPos4 + (strippedRules.length() * 2); outputPos5 % 8 != 0; outputPos5++) {
            dos.write(0);
        }
    }

    static void compileRules(String rules, OutputStream os) throws IOException {
        RBBIRuleBuilder builder = new RBBIRuleBuilder(rules);
        builder.build(os);
    }

    void build(OutputStream os) throws IOException {
        this.fScanner.parse();
        this.fSetBuilder.buildRanges();
        RBBITableBuilder rBBITableBuilder = new RBBITableBuilder(this, 0);
        this.fForwardTable = rBBITableBuilder;
        rBBITableBuilder.buildForwardTable();
        optimizeTables();
        this.fForwardTable.buildSafeReverseTable();
        String str = this.fDebugEnv;
        if (str != null && str.indexOf("states") >= 0) {
            this.fForwardTable.printStates();
            this.fForwardTable.printRuleStatusTable();
            this.fForwardTable.printReverseTable();
        }
        this.fSetBuilder.buildTrie();
        flattenData(os);
    }

    /* loaded from: classes.dex */
    static class IntPair {
        int first;
        int second;

        IntPair() {
            this.first = 0;
            this.second = 0;
        }

        IntPair(int f, int s) {
            this.first = 0;
            this.second = 0;
            this.first = f;
            this.second = s;
        }
    }

    void optimizeTables() {
        boolean didSomething;
        do {
            didSomething = false;
            IntPair duplPair = new IntPair(3, 0);
            while (this.fForwardTable.findDuplCharClassFrom(duplPair)) {
                this.fSetBuilder.mergeCategories(duplPair);
                this.fForwardTable.removeColumn(duplPair.second);
                didSomething = true;
            }
            while (this.fForwardTable.removeDuplicateStates() > 0) {
                didSomething = true;
            }
        } while (didSomething);
    }
}
