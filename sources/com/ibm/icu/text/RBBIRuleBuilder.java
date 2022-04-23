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
    int fDefaultTree = 0;
    RBBITableBuilder fForwardTable;
    boolean fLBCMNoChain;
    boolean fLookAheadHardBreak;
    List<Integer> fRuleStatusVals;
    String fRules;
    RBBIRuleScanner fScanner;
    RBBISetBuilder fSetBuilder;
    Map<Set<Integer>, Integer> fStatusSets = new HashMap();
    StringBuilder fStrippedRules;
    RBBINode[] fTreeRoots = new RBBINode[4];
    List<RBBINode> fUSetNodes;

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
        return (i + 7) & -8;
    }

    /* access modifiers changed from: package-private */
    public void flattenData(OutputStream os) throws IOException {
        OutputStream outputStream = os;
        DataOutputStream dos = new DataOutputStream(outputStream);
        String strippedRules = RBBIRuleScanner.stripRules(this.fStrippedRules.toString());
        int forwardTableSize = align8(this.fForwardTable.getTableSize());
        int reverseTableSize = align8(this.fForwardTable.getSafeTableSize());
        int trieSize = align8(this.fSetBuilder.getTrieSize());
        int statusTableSize = align8(this.fRuleStatusVals.size() * 4);
        int outputPos = 0;
        ICUBinary.writeHeader(1114794784, 83886080, 0, dos);
        int[] header = new int[20];
        header[0] = 45472;
        boolean z = true;
        header[1] = 83886080;
        header[2] = 80 + forwardTableSize + reverseTableSize + statusTableSize + trieSize + align8(strippedRules.length() * 2);
        header[3] = this.fSetBuilder.getNumCharCategories();
        header[4] = 80;
        header[5] = forwardTableSize;
        header[6] = header[4] + forwardTableSize;
        header[7] = reverseTableSize;
        header[8] = header[6] + header[7];
        header[9] = this.fSetBuilder.getTrieSize();
        header[12] = header[8] + header[9];
        header[13] = statusTableSize;
        header[10] = header[12] + statusTableSize;
        header[11] = strippedRules.length() * 2;
        for (int writeInt : header) {
            dos.writeInt(writeInt);
            outputPos += 4;
        }
        RBBIDataWrapper.RBBIStateTable table = this.fForwardTable.exportTable();
        if (outputPos == header[4]) {
            int outputPos2 = outputPos + table.put(dos);
            RBBIDataWrapper.RBBIStateTable table2 = this.fForwardTable.exportSafeTable();
            Assert.assrt(outputPos2 == header[6]);
            int outputPos3 = outputPos2 + table2.put(dos);
            Assert.assrt(outputPos3 == header[8]);
            this.fSetBuilder.serializeTrie(outputStream);
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
            if (outputPos4 != header[10]) {
                z = false;
            }
            Assert.assrt(z);
            dos.writeChars(strippedRules);
            for (int outputPos5 = outputPos4 + (strippedRules.length() * 2); outputPos5 % 8 != 0; outputPos5++) {
                dos.write(0);
            }
            return;
        }
        throw new AssertionError();
    }

    static void compileRules(String rules, OutputStream os) throws IOException {
        new RBBIRuleBuilder(rules).build(os);
    }

    /* access modifiers changed from: package-private */
    public void build(OutputStream os) throws IOException {
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

    static class IntPair {
        int first = 0;
        int second = 0;

        IntPair() {
        }

        IntPair(int f, int s) {
            this.first = f;
            this.second = s;
        }
    }

    /* access modifiers changed from: package-private */
    public void optimizeTables() {
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
