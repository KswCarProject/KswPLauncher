package com.ibm.icu.text;

/* compiled from: NFSubstitution */
class MultiplierSubstitution extends NFSubstitution {
    long divisor;

    MultiplierSubstitution(int pos, NFRule rule, NFRuleSet ruleSet, String description) {
        super(pos, ruleSet, description);
        long divisor2 = rule.getDivisor();
        this.divisor = divisor2;
        if (divisor2 == 0) {
            throw new IllegalStateException("Substitution with divisor 0 " + description.substring(0, pos) + " | " + description.substring(pos));
        }
    }

    public void setDivisor(int radix, short exponent) {
        long power = NFRule.power((long) radix, exponent);
        this.divisor = power;
        if (power == 0) {
            throw new IllegalStateException("Substitution with divisor 0");
        }
    }

    public boolean equals(Object that) {
        return super.equals(that) && this.divisor == ((MultiplierSubstitution) that).divisor;
    }

    public long transformNumber(long number) {
        return (long) Math.floor((double) (number / this.divisor));
    }

    public double transformNumber(double number) {
        if (this.ruleSet == null) {
            return number / ((double) this.divisor);
        }
        return Math.floor(number / ((double) this.divisor));
    }

    public double composeRuleValue(double newRuleValue, double oldRuleValue) {
        return ((double) this.divisor) * newRuleValue;
    }

    public double calcUpperBound(double oldUpperBound) {
        return (double) this.divisor;
    }

    /* access modifiers changed from: package-private */
    public char tokenChar() {
        return '<';
    }
}
