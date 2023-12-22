package com.ibm.icu.text;

import android.support.p001v4.view.InputDeviceCompat;
import com.ibm.icu.text.Transliterator;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
class TransliterationRuleSet {
    private int[] index;
    private TransliterationRule[] rules;
    private List<TransliterationRule> ruleVector = new ArrayList();
    private int maxContextLength = 0;

    public int getMaximumContextLength() {
        return this.maxContextLength;
    }

    public void addRule(TransliterationRule rule) {
        this.ruleVector.add(rule);
        int len = rule.getAnteContextLength();
        if (len > this.maxContextLength) {
            this.maxContextLength = len;
        }
        this.rules = null;
    }

    public void freeze() {
        int n = this.ruleVector.size();
        this.index = new int[InputDeviceCompat.SOURCE_KEYBOARD];
        List<TransliterationRule> v = new ArrayList<>(n * 2);
        int[] indexValue = new int[n];
        for (int j = 0; j < n; j++) {
            indexValue[j] = this.ruleVector.get(j).getIndexValue();
        }
        for (int x = 0; x < 256; x++) {
            this.index[x] = v.size();
            for (int j2 = 0; j2 < n; j2++) {
                if (indexValue[j2] >= 0) {
                    if (indexValue[j2] == x) {
                        v.add(this.ruleVector.get(j2));
                    }
                } else {
                    TransliterationRule r = this.ruleVector.get(j2);
                    if (r.matchesIndexValue(x)) {
                        v.add(r);
                    }
                }
            }
        }
        this.index[256] = v.size();
        TransliterationRule[] transliterationRuleArr = new TransliterationRule[v.size()];
        this.rules = transliterationRuleArr;
        v.toArray(transliterationRuleArr);
        StringBuilder errors = null;
        for (int x2 = 0; x2 < 256; x2++) {
            for (int j3 = this.index[x2]; j3 < this.index[x2 + 1] - 1; j3++) {
                TransliterationRule r1 = this.rules[j3];
                for (int k = j3 + 1; k < this.index[x2 + 1]; k++) {
                    TransliterationRule r2 = this.rules[k];
                    if (r1.masks(r2)) {
                        if (errors == null) {
                            errors = new StringBuilder();
                        } else {
                            errors.append("\n");
                        }
                        errors.append("Rule " + r1 + " masks " + r2);
                    }
                }
            }
        }
        if (errors != null) {
            throw new IllegalArgumentException(errors.toString());
        }
    }

    public boolean transliterate(Replaceable text, Transliterator.Position pos, boolean incremental) {
        int indexByte = text.char32At(pos.start) & 255;
        for (int i = this.index[indexByte]; i < this.index[indexByte + 1]; i++) {
            int m = this.rules[i].matchAndReplace(text, pos, incremental);
            switch (m) {
                case 1:
                    return false;
                case 2:
                    return true;
                default:
            }
        }
        int i2 = pos.start;
        pos.start = i2 + UTF16.getCharCount(text.char32At(pos.start));
        return true;
    }

    String toRules(boolean escapeUnprintable) {
        int count = this.ruleVector.size();
        StringBuilder ruleSource = new StringBuilder();
        for (int i = 0; i < count; i++) {
            if (i != 0) {
                ruleSource.append('\n');
            }
            TransliterationRule r = this.ruleVector.get(i);
            ruleSource.append(r.toRule(escapeUnprintable));
        }
        return ruleSource.toString();
    }

    void addSourceTargetSet(UnicodeSet filter, UnicodeSet sourceSet, UnicodeSet targetSet) {
        UnicodeSet currentFilter = new UnicodeSet(filter);
        UnicodeSet revisiting = new UnicodeSet();
        int count = this.ruleVector.size();
        for (int i = 0; i < count; i++) {
            TransliterationRule r = this.ruleVector.get(i);
            r.addSourceTargetSet(currentFilter, sourceSet, targetSet, revisiting.clear());
            currentFilter.addAll(revisiting);
        }
    }
}
