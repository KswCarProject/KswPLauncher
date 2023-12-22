package com.ibm.icu.text;

import kotlin.text.Typography;

/* compiled from: NFSubstitution.java */
/* loaded from: classes.dex */
class IntegralPartSubstitution extends NFSubstitution {
    IntegralPartSubstitution(int pos, NFRuleSet ruleSet, String description) {
        super(pos, ruleSet, description);
    }

    @Override // com.ibm.icu.text.NFSubstitution
    public long transformNumber(long number) {
        return number;
    }

    @Override // com.ibm.icu.text.NFSubstitution
    public double transformNumber(double number) {
        return Math.floor(number);
    }

    @Override // com.ibm.icu.text.NFSubstitution
    public double composeRuleValue(double newRuleValue, double oldRuleValue) {
        return newRuleValue + oldRuleValue;
    }

    @Override // com.ibm.icu.text.NFSubstitution
    public double calcUpperBound(double oldUpperBound) {
        return Double.MAX_VALUE;
    }

    @Override // com.ibm.icu.text.NFSubstitution
    char tokenChar() {
        return Typography.less;
    }
}
