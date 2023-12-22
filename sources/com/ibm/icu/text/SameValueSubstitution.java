package com.ibm.icu.text;

/* compiled from: NFSubstitution.java */
/* loaded from: classes.dex */
class SameValueSubstitution extends NFSubstitution {
    SameValueSubstitution(int pos, NFRuleSet ruleSet, String description) {
        super(pos, ruleSet, description);
        if (description.equals("==")) {
            throw new IllegalArgumentException("== is not a legal token");
        }
    }

    @Override // com.ibm.icu.text.NFSubstitution
    public long transformNumber(long number) {
        return number;
    }

    @Override // com.ibm.icu.text.NFSubstitution
    public double transformNumber(double number) {
        return number;
    }

    @Override // com.ibm.icu.text.NFSubstitution
    public double composeRuleValue(double newRuleValue, double oldRuleValue) {
        return newRuleValue;
    }

    @Override // com.ibm.icu.text.NFSubstitution
    public double calcUpperBound(double oldUpperBound) {
        return oldUpperBound;
    }

    @Override // com.ibm.icu.text.NFSubstitution
    char tokenChar() {
        return '=';
    }
}
