package com.ibm.icu.text;

/* loaded from: classes.dex */
interface RBNFPostProcessor {
    void init(RuleBasedNumberFormat ruleBasedNumberFormat, String str);

    void process(StringBuilder sb, NFRuleSet nFRuleSet);
}
