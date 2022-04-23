package com.ibm.icu.text;

import com.ibm.icu.lang.UCharacter;
import java.text.ParsePosition;
import java.util.HashMap;

class RBBISymbolTable implements SymbolTable {
    UnicodeSet fCachedSetLookup;
    HashMap<String, RBBISymbolTableEntry> fHashTable = new HashMap<>();
    RBBIRuleScanner fRuleScanner;
    String ffffString = "￿";

    static class RBBISymbolTableEntry {
        String key;
        RBBINode val;

        RBBISymbolTableEntry() {
        }
    }

    RBBISymbolTable(RBBIRuleScanner rs) {
        this.fRuleScanner = rs;
    }

    public char[] lookup(String s) {
        String retString;
        RBBISymbolTableEntry el = this.fHashTable.get(s);
        if (el == null) {
            return null;
        }
        RBBINode varRefNode = el.val;
        while (varRefNode.fLeftChild.fType == 2) {
            varRefNode = varRefNode.fLeftChild;
        }
        RBBINode exprNode = varRefNode.fLeftChild;
        if (exprNode.fType == 0) {
            this.fCachedSetLookup = exprNode.fLeftChild.fInputSet;
            retString = this.ffffString;
        } else {
            this.fRuleScanner.error(66063);
            retString = exprNode.fText;
            this.fCachedSetLookup = null;
        }
        return retString.toCharArray();
    }

    public UnicodeMatcher lookupMatcher(int ch) {
        if (ch != 65535) {
            return null;
        }
        UnicodeSet retVal = this.fCachedSetLookup;
        this.fCachedSetLookup = null;
        return retVal;
    }

    public String parseReference(String text, ParsePosition pos, int limit) {
        int start = pos.getIndex();
        int i = start;
        while (i < limit) {
            int c = UTF16.charAt(text, i);
            if ((i == start && !UCharacter.isUnicodeIdentifierStart(c)) || !UCharacter.isUnicodeIdentifierPart(c)) {
                break;
            }
            i += UTF16.getCharCount(c);
        }
        if (i == start) {
            return "";
        }
        pos.setIndex(i);
        return text.substring(start, i);
    }

    /* access modifiers changed from: package-private */
    public RBBINode lookupNode(String key) {
        RBBISymbolTableEntry el = this.fHashTable.get(key);
        if (el != null) {
            return el.val;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void addEntry(String key, RBBINode val) {
        if (this.fHashTable.get(key) != null) {
            this.fRuleScanner.error(66055);
            return;
        }
        RBBISymbolTableEntry e = new RBBISymbolTableEntry();
        e.key = key;
        e.val = val;
        this.fHashTable.put(e.key, e);
    }

    /* access modifiers changed from: package-private */
    public void rbbiSymtablePrint() {
        System.out.print("Variable Definitions\nName               Node Val     String Val\n----------------------------------------------------------------------\n");
        RBBISymbolTableEntry[] syms = (RBBISymbolTableEntry[]) this.fHashTable.values().toArray(new RBBISymbolTableEntry[0]);
        for (RBBISymbolTableEntry s : syms) {
            System.out.print("  " + s.key + "  ");
            System.out.print("  " + s.val + "  ");
            System.out.print(s.val.fLeftChild.fText);
            System.out.print("\n");
        }
        System.out.println("\nParsed Variable Definitions\n");
        for (RBBISymbolTableEntry s2 : syms) {
            System.out.print(s2.key);
            s2.val.fLeftChild.printTree(true);
            System.out.print("\n");
        }
    }
}
