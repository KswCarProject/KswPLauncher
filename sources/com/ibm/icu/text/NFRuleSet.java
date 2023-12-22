package com.ibm.icu.text;

import com.ibm.icu.impl.PatternProps;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import kotlin.jvm.internal.LongCompanionObject;

/* loaded from: classes.dex */
final class NFRuleSet {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int IMPROPER_FRACTION_RULE_INDEX = 1;
    static final int INFINITY_RULE_INDEX = 4;
    static final int MASTER_RULE_INDEX = 3;
    static final int NAN_RULE_INDEX = 5;
    static final int NEGATIVE_RULE_INDEX = 0;
    static final int PROPER_FRACTION_RULE_INDEX = 2;
    private static final int RECURSION_LIMIT = 64;
    LinkedList<NFRule> fractionRules;
    private final boolean isParseable;
    private final String name;
    final RuleBasedNumberFormat owner;
    private NFRule[] rules;
    final NFRule[] nonNumericalRules = new NFRule[6];
    private boolean isFractionRuleSet = false;

    public NFRuleSet(RuleBasedNumberFormat owner, String[] descriptions, int index) throws IllegalArgumentException {
        this.owner = owner;
        String description = descriptions[index];
        if (description.length() == 0) {
            throw new IllegalArgumentException("Empty rule set description");
        }
        if (description.charAt(0) == '%') {
            int pos = description.indexOf(58);
            if (pos == -1) {
                throw new IllegalArgumentException("Rule set name doesn't end in colon");
            }
            String name = description.substring(0, pos);
            boolean endsWith = true ^ name.endsWith("@noparse");
            this.isParseable = endsWith;
            this.name = endsWith ? name : name.substring(0, name.length() - 8);
            while (pos < description.length()) {
                pos++;
                if (!PatternProps.isWhiteSpace(description.charAt(pos))) {
                    break;
                }
            }
            description = description.substring(pos);
            descriptions[index] = description;
        } else {
            this.name = "%default";
            this.isParseable = true;
        }
        if (description.length() == 0) {
            throw new IllegalArgumentException("Empty rule set description");
        }
    }

    public void parseRules(String description) {
        List<NFRule> tempRules = new ArrayList<>();
        NFRule predecessor = null;
        int oldP = 0;
        int descriptionLen = description.length();
        do {
            int p = description.indexOf(59, oldP);
            if (p < 0) {
                p = descriptionLen;
            }
            NFRule.makeRules(description.substring(oldP, p), this, predecessor, this.owner, tempRules);
            if (!tempRules.isEmpty()) {
                NFRule predecessor2 = tempRules.get(tempRules.size() - 1);
                predecessor = predecessor2;
            }
            oldP = p + 1;
        } while (oldP < descriptionLen);
        long defaultBaseValue = 0;
        for (NFRule rule : tempRules) {
            long baseValue = rule.getBaseValue();
            if (baseValue == 0) {
                rule.setBaseValue(defaultBaseValue);
            } else if (baseValue < defaultBaseValue) {
                throw new IllegalArgumentException("Rules are not in order, base: " + baseValue + " < " + defaultBaseValue);
            } else {
                defaultBaseValue = baseValue;
            }
            if (!this.isFractionRuleSet) {
                defaultBaseValue++;
            }
        }
        NFRule[] nFRuleArr = new NFRule[tempRules.size()];
        this.rules = nFRuleArr;
        tempRules.toArray(nFRuleArr);
    }

    void setNonNumericalRule(NFRule rule) {
        long baseValue = rule.getBaseValue();
        if (baseValue == -1) {
            this.nonNumericalRules[0] = rule;
        } else if (baseValue == -2) {
            setBestFractionRule(1, rule, true);
        } else if (baseValue == -3) {
            setBestFractionRule(2, rule, true);
        } else if (baseValue == -4) {
            setBestFractionRule(3, rule, true);
        } else if (baseValue == -5) {
            this.nonNumericalRules[4] = rule;
        } else if (baseValue == -6) {
            this.nonNumericalRules[5] = rule;
        }
    }

    private void setBestFractionRule(int originalIndex, NFRule newRule, boolean rememberRule) {
        if (rememberRule) {
            if (this.fractionRules == null) {
                this.fractionRules = new LinkedList<>();
            }
            this.fractionRules.add(newRule);
        }
        NFRule[] nFRuleArr = this.nonNumericalRules;
        NFRule bestResult = nFRuleArr[originalIndex];
        if (bestResult == null) {
            nFRuleArr[originalIndex] = newRule;
            return;
        }
        DecimalFormatSymbols decimalFormatSymbols = this.owner.getDecimalFormatSymbols();
        if (decimalFormatSymbols.getDecimalSeparator() == newRule.getDecimalPoint()) {
            this.nonNumericalRules[originalIndex] = newRule;
        }
    }

    public void makeIntoFractionRuleSet() {
        this.isFractionRuleSet = true;
    }

    public boolean equals(Object that) {
        if (!(that instanceof NFRuleSet)) {
            return false;
        }
        NFRuleSet that2 = (NFRuleSet) that;
        if (!this.name.equals(that2.name) || this.rules.length != that2.rules.length || this.isFractionRuleSet != that2.isFractionRuleSet) {
            return false;
        }
        int i = 0;
        while (true) {
            NFRule[] nFRuleArr = this.nonNumericalRules;
            if (i < nFRuleArr.length) {
                if (!Objects.equals(nFRuleArr[i], that2.nonNumericalRules[i])) {
                    return false;
                }
                i++;
            } else {
                int i2 = 0;
                while (true) {
                    NFRule[] nFRuleArr2 = this.rules;
                    if (i2 < nFRuleArr2.length) {
                        if (!nFRuleArr2[i2].equals(that2.rules[i2])) {
                            return false;
                        }
                        i2++;
                    } else {
                        return true;
                    }
                }
            }
        }
    }

    public int hashCode() {
        throw new AssertionError("hashCode not designed");
    }

    public String toString() {
        NFRule[] nFRuleArr;
        StringBuilder result = new StringBuilder();
        result.append(this.name).append(":\n");
        for (NFRule rule : this.rules) {
            result.append(rule.toString()).append("\n");
        }
        for (NFRule rule2 : this.nonNumericalRules) {
            if (rule2 != null) {
                if (rule2.getBaseValue() == -2 || rule2.getBaseValue() == -3 || rule2.getBaseValue() == -4) {
                    Iterator<NFRule> it = this.fractionRules.iterator();
                    while (it.hasNext()) {
                        NFRule fractionRule = it.next();
                        if (fractionRule.getBaseValue() == rule2.getBaseValue()) {
                            result.append(fractionRule.toString()).append("\n");
                        }
                    }
                } else {
                    result.append(rule2.toString()).append("\n");
                }
            }
        }
        return result.toString();
    }

    public boolean isFractionSet() {
        return this.isFractionRuleSet;
    }

    public String getName() {
        return this.name;
    }

    public boolean isPublic() {
        return !this.name.startsWith("%%");
    }

    public boolean isParseable() {
        return this.isParseable;
    }

    public void format(long number, StringBuilder toInsertInto, int pos, int recursionCount) {
        if (recursionCount >= 64) {
            throw new IllegalStateException("Recursion limit exceeded when applying ruleSet " + this.name);
        }
        NFRule applicableRule = findNormalRule(number);
        applicableRule.doFormat(number, toInsertInto, pos, recursionCount + 1);
    }

    public void format(double number, StringBuilder toInsertInto, int pos, int recursionCount) {
        if (recursionCount >= 64) {
            throw new IllegalStateException("Recursion limit exceeded when applying ruleSet " + this.name);
        }
        NFRule applicableRule = findRule(number);
        applicableRule.doFormat(number, toInsertInto, pos, recursionCount + 1);
    }

    NFRule findRule(double number) {
        if (this.isFractionRuleSet) {
            return findFractionRuleSetRule(number);
        }
        if (Double.isNaN(number)) {
            NFRule rule = this.nonNumericalRules[5];
            if (rule == null) {
                return this.owner.getDefaultNaNRule();
            }
            return rule;
        }
        if (number < 0.0d) {
            NFRule[] nFRuleArr = this.nonNumericalRules;
            if (nFRuleArr[0] != null) {
                return nFRuleArr[0];
            }
            number = -number;
        }
        if (Double.isInfinite(number)) {
            NFRule rule2 = this.nonNumericalRules[4];
            if (rule2 == null) {
                return this.owner.getDefaultInfinityRule();
            }
            return rule2;
        }
        if (number != Math.floor(number)) {
            if (number < 1.0d) {
                NFRule[] nFRuleArr2 = this.nonNumericalRules;
                if (nFRuleArr2[2] != null) {
                    return nFRuleArr2[2];
                }
            }
            NFRule[] nFRuleArr3 = this.nonNumericalRules;
            if (nFRuleArr3[1] != null) {
                return nFRuleArr3[1];
            }
        }
        NFRule[] nFRuleArr4 = this.nonNumericalRules;
        if (nFRuleArr4[3] != null) {
            return nFRuleArr4[3];
        }
        return findNormalRule(Math.round(number));
    }

    private NFRule findNormalRule(long number) {
        if (this.isFractionRuleSet) {
            return findFractionRuleSetRule(number);
        }
        if (number < 0) {
            NFRule[] nFRuleArr = this.nonNumericalRules;
            if (nFRuleArr[0] != null) {
                return nFRuleArr[0];
            }
            number = -number;
        }
        int lo = 0;
        int hi = this.rules.length;
        if (hi > 0) {
            while (lo < hi) {
                int mid = (lo + hi) >>> 1;
                long ruleBaseValue = this.rules[mid].getBaseValue();
                if (ruleBaseValue == number) {
                    return this.rules[mid];
                }
                if (ruleBaseValue > number) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }
            if (hi == 0) {
                throw new IllegalStateException("The rule set " + this.name + " cannot format the value " + number);
            }
            NFRule result = this.rules[hi - 1];
            if (result.shouldRollBack(number)) {
                if (hi == 1) {
                    throw new IllegalStateException("The rule set " + this.name + " cannot roll back from the rule '" + result + "'");
                }
                return this.rules[hi - 2];
            }
            return result;
        }
        return this.nonNumericalRules[3];
    }

    private NFRule findFractionRuleSetRule(double number) {
        long leastCommonMultiple = this.rules[0].getBaseValue();
        int i = 1;
        while (true) {
            NFRule[] nFRuleArr = this.rules;
            if (i >= nFRuleArr.length) {
                break;
            }
            leastCommonMultiple = lcm(leastCommonMultiple, nFRuleArr[i].getBaseValue());
            i++;
        }
        long numerator = Math.round(leastCommonMultiple * number);
        long difference = LongCompanionObject.MAX_VALUE;
        int winner = 0;
        int i2 = 0;
        while (true) {
            NFRule[] nFRuleArr2 = this.rules;
            if (i2 >= nFRuleArr2.length) {
                break;
            }
            long tempDifference = (nFRuleArr2[i2].getBaseValue() * numerator) % leastCommonMultiple;
            if (leastCommonMultiple - tempDifference < tempDifference) {
                tempDifference = leastCommonMultiple - tempDifference;
            }
            if (tempDifference < difference) {
                difference = tempDifference;
                winner = i2;
                if (difference == 0) {
                    break;
                }
            }
            i2++;
        }
        int i3 = winner + 1;
        NFRule[] nFRuleArr3 = this.rules;
        if (i3 < nFRuleArr3.length && nFRuleArr3[winner + 1].getBaseValue() == this.rules[winner].getBaseValue() && (Math.round(this.rules[winner].getBaseValue() * number) < 1 || Math.round(this.rules[winner].getBaseValue() * number) >= 2)) {
            winner++;
        }
        return this.rules[winner];
    }

    private static long lcm(long x, long y) {
        long t;
        long x1 = x;
        long y1 = y;
        int p2 = 0;
        while ((x1 & 1) == 0 && (y1 & 1) == 0) {
            p2++;
            x1 >>= 1;
            y1 >>= 1;
        }
        if ((x1 & 1) == 1) {
            t = -y1;
        } else {
            t = x1;
        }
        while (t != 0) {
            while ((t & 1) == 0) {
                t >>= 1;
            }
            if (t > 0) {
                x1 = t;
            } else {
                y1 = -t;
            }
            t = x1 - y1;
        }
        long gcd = x1 << p2;
        return (x / gcd) * y;
    }

    public Number parse(String text, ParsePosition parsePosition, double upperBound, int nonNumericalExecutedRuleMask) {
        ParsePosition highWaterMark = new ParsePosition(0);
        Number result = NFRule.ZERO;
        if (text.length() == 0) {
            return result;
        }
        int nonNumericalExecutedRuleMask2 = nonNumericalExecutedRuleMask;
        Number result2 = result;
        int nonNumericalRuleIdx = 0;
        while (true) {
            NFRule[] nFRuleArr = this.nonNumericalRules;
            if (nonNumericalRuleIdx >= nFRuleArr.length) {
                break;
            }
            NFRule nonNumericalRule = nFRuleArr[nonNumericalRuleIdx];
            if (nonNumericalRule != null && ((nonNumericalExecutedRuleMask2 >> nonNumericalRuleIdx) & 1) == 0) {
                nonNumericalExecutedRuleMask2 |= 1 << nonNumericalRuleIdx;
                Number tempResult = nonNumericalRule.doParse(text, parsePosition, false, upperBound, nonNumericalExecutedRuleMask2);
                if (parsePosition.getIndex() > highWaterMark.getIndex()) {
                    result2 = tempResult;
                    highWaterMark.setIndex(parsePosition.getIndex());
                }
                parsePosition.setIndex(0);
            }
            nonNumericalRuleIdx++;
        }
        Number result3 = result2;
        for (int i = this.rules.length - 1; i >= 0 && highWaterMark.getIndex() < text.length(); i--) {
            if (this.isFractionRuleSet || this.rules[i].getBaseValue() < upperBound) {
                Number tempResult2 = this.rules[i].doParse(text, parsePosition, this.isFractionRuleSet, upperBound, nonNumericalExecutedRuleMask2);
                if (parsePosition.getIndex() > highWaterMark.getIndex()) {
                    result3 = tempResult2;
                    highWaterMark.setIndex(parsePosition.getIndex());
                }
                parsePosition.setIndex(0);
            }
        }
        parsePosition.setIndex(highWaterMark.getIndex());
        return result3;
    }

    public void setDecimalFormatSymbols(DecimalFormatSymbols newSymbols) {
        NFRule[] nFRuleArr;
        for (NFRule rule : this.rules) {
            rule.setDecimalFormatSymbols(newSymbols);
        }
        if (this.fractionRules != null) {
            for (int nonNumericalIdx = 1; nonNumericalIdx <= 3; nonNumericalIdx++) {
                if (this.nonNumericalRules[nonNumericalIdx] != null) {
                    Iterator<NFRule> it = this.fractionRules.iterator();
                    while (it.hasNext()) {
                        NFRule rule2 = it.next();
                        if (this.nonNumericalRules[nonNumericalIdx].getBaseValue() == rule2.getBaseValue()) {
                            setBestFractionRule(nonNumericalIdx, rule2, false);
                        }
                    }
                }
            }
        }
        for (NFRule rule3 : this.nonNumericalRules) {
            if (rule3 != null) {
                rule3.setDecimalFormatSymbols(newSymbols);
            }
        }
    }
}
