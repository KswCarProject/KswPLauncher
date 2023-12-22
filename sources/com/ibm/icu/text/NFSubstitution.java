package com.ibm.icu.text;

import java.text.ParsePosition;

/* loaded from: classes.dex */
abstract class NFSubstitution {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long MAX_INT64_IN_DOUBLE = 9007199254740991L;
    final DecimalFormat numberFormat;
    final int pos;
    final NFRuleSet ruleSet;

    public abstract double calcUpperBound(double d);

    public abstract double composeRuleValue(double d, double d2);

    abstract char tokenChar();

    public abstract double transformNumber(double d);

    public abstract long transformNumber(long j);

    public static NFSubstitution makeSubstitution(int pos, NFRule rule, NFRule rulePredecessor, NFRuleSet ruleSet, RuleBasedNumberFormat formatter, String description) {
        if (description.length() == 0) {
            return null;
        }
        switch (description.charAt(0)) {
            case '<':
                if (rule.getBaseValue() != -1) {
                    if (rule.getBaseValue() != -2 && rule.getBaseValue() != -3 && rule.getBaseValue() != -4) {
                        if (ruleSet.isFractionSet()) {
                            return new NumeratorSubstitution(pos, rule.getBaseValue(), formatter.getDefaultRuleSet(), description);
                        }
                        return new MultiplierSubstitution(pos, rule, ruleSet, description);
                    }
                    return new IntegralPartSubstitution(pos, ruleSet, description);
                }
                throw new IllegalArgumentException("<< not allowed in negative-number rule");
            case '=':
                return new SameValueSubstitution(pos, ruleSet, description);
            case '>':
                if (rule.getBaseValue() == -1) {
                    return new AbsoluteValueSubstitution(pos, ruleSet, description);
                }
                if (rule.getBaseValue() == -2 || rule.getBaseValue() == -3 || rule.getBaseValue() == -4) {
                    return new FractionalPartSubstitution(pos, ruleSet, description);
                }
                if (ruleSet.isFractionSet()) {
                    throw new IllegalArgumentException(">> not allowed in fraction rule set");
                }
                return new ModulusSubstitution(pos, rule, rulePredecessor, ruleSet, description);
            default:
                throw new IllegalArgumentException("Illegal substitution character");
        }
    }

    NFSubstitution(int pos, NFRuleSet ruleSet, String description) {
        this.pos = pos;
        int descriptionLen = description.length();
        if (descriptionLen >= 2 && description.charAt(0) == description.charAt(descriptionLen - 1)) {
            description = description.substring(1, descriptionLen - 1);
        } else if (descriptionLen != 0) {
            throw new IllegalArgumentException("Illegal substitution syntax");
        }
        if (description.length() == 0) {
            this.ruleSet = ruleSet;
            this.numberFormat = null;
        } else if (description.charAt(0) == '%') {
            this.ruleSet = ruleSet.owner.findRuleSet(description);
            this.numberFormat = null;
        } else if (description.charAt(0) == '#' || description.charAt(0) == '0') {
            this.ruleSet = null;
            DecimalFormat decimalFormat = (DecimalFormat) ruleSet.owner.getDecimalFormat().clone();
            this.numberFormat = decimalFormat;
            decimalFormat.applyPattern(description);
        } else if (description.charAt(0) == '>') {
            this.ruleSet = ruleSet;
            this.numberFormat = null;
        } else {
            throw new IllegalArgumentException("Illegal substitution syntax");
        }
    }

    public void setDivisor(int radix, short exponent) {
    }

    public boolean equals(Object that) {
        if (that == null) {
            return false;
        }
        if (this == that) {
            return true;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        NFSubstitution that2 = (NFSubstitution) that;
        if (this.pos == that2.pos) {
            if (this.ruleSet == null && that2.ruleSet != null) {
                return false;
            }
            DecimalFormat decimalFormat = this.numberFormat;
            if (decimalFormat == null) {
                if (that2.numberFormat != null) {
                    return false;
                }
            } else if (!decimalFormat.equals(that2.numberFormat)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        throw new AssertionError("hashCode not designed");
    }

    public String toString() {
        if (this.ruleSet != null) {
            return tokenChar() + this.ruleSet.getName() + tokenChar();
        }
        return tokenChar() + this.numberFormat.toPattern() + tokenChar();
    }

    public void doSubstitution(long number, StringBuilder toInsertInto, int position, int recursionCount) {
        if (this.ruleSet != null) {
            long numberToFormat = transformNumber(number);
            this.ruleSet.format(numberToFormat, toInsertInto, position + this.pos, recursionCount);
        } else if (number <= MAX_INT64_IN_DOUBLE) {
            double numberToFormat2 = transformNumber(number);
            if (this.numberFormat.getMaximumFractionDigits() == 0) {
                numberToFormat2 = Math.floor(numberToFormat2);
            }
            toInsertInto.insert(this.pos + position, this.numberFormat.format(numberToFormat2));
        } else {
            toInsertInto.insert(this.pos + position, this.numberFormat.format(transformNumber(number)));
        }
    }

    public void doSubstitution(double number, StringBuilder toInsertInto, int position, int recursionCount) {
        NFRuleSet nFRuleSet;
        double numberToFormat = transformNumber(number);
        if (Double.isInfinite(numberToFormat)) {
            NFRule infiniteRule = this.ruleSet.findRule(Double.POSITIVE_INFINITY);
            infiniteRule.doFormat(numberToFormat, toInsertInto, position + this.pos, recursionCount);
        } else if (numberToFormat == Math.floor(numberToFormat) && (nFRuleSet = this.ruleSet) != null) {
            nFRuleSet.format((long) numberToFormat, toInsertInto, position + this.pos, recursionCount);
        } else {
            NFRuleSet nFRuleSet2 = this.ruleSet;
            if (nFRuleSet2 == null) {
                toInsertInto.insert(position + this.pos, this.numberFormat.format(numberToFormat));
            } else {
                nFRuleSet2.format(numberToFormat, toInsertInto, position + this.pos, recursionCount);
            }
        }
    }

    public Number doParse(String text, ParsePosition parsePosition, double baseValue, double upperBound, boolean lenientParse, int nonNumericalExecutedRuleMask) {
        Number tempResult;
        double upperBound2 = calcUpperBound(upperBound);
        NFRuleSet nFRuleSet = this.ruleSet;
        if (nFRuleSet != null) {
            tempResult = nFRuleSet.parse(text, parsePosition, upperBound2, nonNumericalExecutedRuleMask);
            if (lenientParse && !this.ruleSet.isFractionSet() && parsePosition.getIndex() == 0) {
                tempResult = this.ruleSet.owner.getDecimalFormat().parse(text, parsePosition);
            }
        } else {
            tempResult = this.numberFormat.parse(text, parsePosition);
        }
        if (parsePosition.getIndex() != 0) {
            double result = composeRuleValue(tempResult.doubleValue(), baseValue);
            if (result == ((long) result)) {
                return Long.valueOf((long) result);
            }
            return new Double(result);
        }
        return tempResult;
    }

    public final int getPos() {
        return this.pos;
    }

    public boolean isModulusSubstitution() {
        return false;
    }

    public void setDecimalFormatSymbols(DecimalFormatSymbols newSymbols) {
        DecimalFormat decimalFormat = this.numberFormat;
        if (decimalFormat != null) {
            decimalFormat.setDecimalFormatSymbols(newSymbols);
        }
    }
}
