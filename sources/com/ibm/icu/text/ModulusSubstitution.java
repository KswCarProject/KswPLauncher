package com.ibm.icu.text;

import java.text.ParsePosition;
import kotlin.text.Typography;

/* compiled from: NFSubstitution.java */
/* loaded from: classes.dex */
class ModulusSubstitution extends NFSubstitution {
    long divisor;
    private final NFRule ruleToUse;

    ModulusSubstitution(int pos, NFRule rule, NFRule rulePredecessor, NFRuleSet ruleSet, String description) {
        super(pos, ruleSet, description);
        long divisor = rule.getDivisor();
        this.divisor = divisor;
        if (divisor == 0) {
            throw new IllegalStateException("Substitution with bad divisor (" + this.divisor + ") " + description.substring(0, pos) + " | " + description.substring(pos));
        }
        if (description.equals(">>>")) {
            this.ruleToUse = rulePredecessor;
        } else {
            this.ruleToUse = null;
        }
    }

    @Override // com.ibm.icu.text.NFSubstitution
    public void setDivisor(int radix, short exponent) {
        long power = NFRule.power(radix, exponent);
        this.divisor = power;
        if (power == 0) {
            throw new IllegalStateException("Substitution with bad divisor");
        }
    }

    @Override // com.ibm.icu.text.NFSubstitution
    public boolean equals(Object that) {
        if (super.equals(that)) {
            ModulusSubstitution that2 = (ModulusSubstitution) that;
            return this.divisor == that2.divisor;
        }
        return false;
    }

    @Override // com.ibm.icu.text.NFSubstitution
    public void doSubstitution(long number, StringBuilder toInsertInto, int position, int recursionCount) {
        if (this.ruleToUse == null) {
            super.doSubstitution(number, toInsertInto, position, recursionCount);
            return;
        }
        long numberToFormat = transformNumber(number);
        this.ruleToUse.doFormat(numberToFormat, toInsertInto, position + this.pos, recursionCount);
    }

    @Override // com.ibm.icu.text.NFSubstitution
    public void doSubstitution(double number, StringBuilder toInsertInto, int position, int recursionCount) {
        if (this.ruleToUse == null) {
            super.doSubstitution(number, toInsertInto, position, recursionCount);
            return;
        }
        double numberToFormat = transformNumber(number);
        this.ruleToUse.doFormat(numberToFormat, toInsertInto, position + this.pos, recursionCount);
    }

    @Override // com.ibm.icu.text.NFSubstitution
    public long transformNumber(long number) {
        return number % this.divisor;
    }

    @Override // com.ibm.icu.text.NFSubstitution
    public double transformNumber(double number) {
        return Math.floor(number % this.divisor);
    }

    @Override // com.ibm.icu.text.NFSubstitution
    public Number doParse(String text, ParsePosition parsePosition, double baseValue, double upperBound, boolean lenientParse, int nonNumericalExecutedRuleMask) {
        NFRule nFRule = this.ruleToUse;
        if (nFRule == null) {
            return super.doParse(text, parsePosition, baseValue, upperBound, lenientParse, nonNumericalExecutedRuleMask);
        }
        Number tempResult = nFRule.doParse(text, parsePosition, false, upperBound, nonNumericalExecutedRuleMask);
        if (parsePosition.getIndex() != 0) {
            double result = composeRuleValue(tempResult.doubleValue(), baseValue);
            if (result == ((long) result)) {
                return Long.valueOf((long) result);
            }
            return new Double(result);
        }
        return tempResult;
    }

    @Override // com.ibm.icu.text.NFSubstitution
    public double composeRuleValue(double newRuleValue, double oldRuleValue) {
        return (oldRuleValue - (oldRuleValue % this.divisor)) + newRuleValue;
    }

    @Override // com.ibm.icu.text.NFSubstitution
    public double calcUpperBound(double oldUpperBound) {
        return this.divisor;
    }

    @Override // com.ibm.icu.text.NFSubstitution
    public boolean isModulusSubstitution() {
        return true;
    }

    @Override // com.ibm.icu.text.NFSubstitution
    char tokenChar() {
        return Typography.greater;
    }
}
