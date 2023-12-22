package com.ibm.icu.text;

import kotlin.text.Typography;

/* compiled from: NFSubstitution.java */
/* loaded from: classes.dex */
class MultiplierSubstitution extends NFSubstitution {
    long divisor;

    MultiplierSubstitution(int pos, NFRule rule, NFRuleSet ruleSet, String description) {
        super(pos, ruleSet, description);
        long divisor = rule.getDivisor();
        this.divisor = divisor;
        if (divisor == 0) {
            throw new IllegalStateException("Substitution with divisor 0 " + description.substring(0, pos) + " | " + description.substring(pos));
        }
    }

    @Override // com.ibm.icu.text.NFSubstitution
    public void setDivisor(int radix, short exponent) {
        long power = NFRule.power(radix, exponent);
        this.divisor = power;
        if (power == 0) {
            throw new IllegalStateException("Substitution with divisor 0");
        }
    }

    @Override // com.ibm.icu.text.NFSubstitution
    public boolean equals(Object that) {
        return super.equals(that) && this.divisor == ((MultiplierSubstitution) that).divisor;
    }

    @Override // com.ibm.icu.text.NFSubstitution
    public long transformNumber(long number) {
        return (long) Math.floor(number / this.divisor);
    }

    @Override // com.ibm.icu.text.NFSubstitution
    public double transformNumber(double number) {
        if (this.ruleSet == null) {
            return number / this.divisor;
        }
        return Math.floor(number / this.divisor);
    }

    @Override // com.ibm.icu.text.NFSubstitution
    public double composeRuleValue(double newRuleValue, double oldRuleValue) {
        return this.divisor * newRuleValue;
    }

    @Override // com.ibm.icu.text.NFSubstitution
    public double calcUpperBound(double oldUpperBound) {
        return this.divisor;
    }

    @Override // com.ibm.icu.text.NFSubstitution
    char tokenChar() {
        return Typography.less;
    }
}
