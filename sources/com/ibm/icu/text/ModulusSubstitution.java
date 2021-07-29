package com.ibm.icu.text;

import java.text.ParsePosition;

/* compiled from: NFSubstitution */
class ModulusSubstitution extends NFSubstitution {
    long divisor;
    private final NFRule ruleToUse;

    ModulusSubstitution(int pos, NFRule rule, NFRule rulePredecessor, NFRuleSet ruleSet, String description) {
        super(pos, ruleSet, description);
        long divisor2 = rule.getDivisor();
        this.divisor = divisor2;
        if (divisor2 == 0) {
            throw new IllegalStateException("Substitution with bad divisor (" + this.divisor + ") " + description.substring(0, pos) + " | " + description.substring(pos));
        } else if (description.equals(">>>")) {
            this.ruleToUse = rulePredecessor;
        } else {
            this.ruleToUse = null;
        }
    }

    public void setDivisor(int radix, short exponent) {
        long power = NFRule.power((long) radix, exponent);
        this.divisor = power;
        if (power == 0) {
            throw new IllegalStateException("Substitution with bad divisor");
        }
    }

    public boolean equals(Object that) {
        if (!super.equals(that) || this.divisor != ((ModulusSubstitution) that).divisor) {
            return false;
        }
        return true;
    }

    public void doSubstitution(long number, StringBuilder toInsertInto, int position, int recursionCount) {
        if (this.ruleToUse == null) {
            super.doSubstitution(number, toInsertInto, position, recursionCount);
            return;
        }
        this.ruleToUse.doFormat(transformNumber(number), toInsertInto, position + this.pos, recursionCount);
    }

    public void doSubstitution(double number, StringBuilder toInsertInto, int position, int recursionCount) {
        if (this.ruleToUse == null) {
            super.doSubstitution(number, toInsertInto, position, recursionCount);
            return;
        }
        this.ruleToUse.doFormat(transformNumber(number), toInsertInto, position + this.pos, recursionCount);
    }

    public long transformNumber(long number) {
        return number % this.divisor;
    }

    public double transformNumber(double number) {
        return Math.floor(number % ((double) this.divisor));
    }

    public Number doParse(String text, ParsePosition parsePosition, double baseValue, double upperBound, boolean lenientParse, int nonNumericalExecutedRuleMask) {
        NFRule nFRule = this.ruleToUse;
        if (nFRule == null) {
            return super.doParse(text, parsePosition, baseValue, upperBound, lenientParse, nonNumericalExecutedRuleMask);
        }
        Number tempResult = nFRule.doParse(text, parsePosition, false, upperBound, nonNumericalExecutedRuleMask);
        if (parsePosition.getIndex() == 0) {
            return tempResult;
        }
        double result = composeRuleValue(tempResult.doubleValue(), baseValue);
        if (result == ((double) ((long) result))) {
            return Long.valueOf((long) result);
        }
        return new Double(result);
    }

    public double composeRuleValue(double newRuleValue, double oldRuleValue) {
        return (oldRuleValue - (oldRuleValue % ((double) this.divisor))) + newRuleValue;
    }

    public double calcUpperBound(double oldUpperBound) {
        return (double) this.divisor;
    }

    public boolean isModulusSubstitution() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public char tokenChar() {
        return '>';
    }
}
