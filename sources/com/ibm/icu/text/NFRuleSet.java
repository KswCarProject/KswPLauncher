package com.ibm.icu.text;

import com.ibm.icu.impl.PatternProps;
import java.text.ParsePosition;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import kotlin.jvm.internal.LongCompanionObject;

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
    private boolean isFractionRuleSet = false;
    private final boolean isParseable;
    private final String name;
    final NFRule[] nonNumericalRules = new NFRule[6];
    final RuleBasedNumberFormat owner;
    private NFRule[] rules;

    public NFRuleSet(RuleBasedNumberFormat owner2, String[] descriptions, int index) throws IllegalArgumentException {
        this.owner = owner2;
        String description = descriptions[index];
        if (description.length() != 0) {
            if (description.charAt(0) == '%') {
                int pos = description.indexOf(58);
                if (pos != -1) {
                    String name2 = description.substring(0, pos);
                    boolean endsWith = true ^ name2.endsWith("@noparse");
                    this.isParseable = endsWith;
                    this.name = !endsWith ? name2.substring(0, name2.length() - 8) : name2;
                    while (pos < description.length()) {
                        pos++;
                        if (!PatternProps.isWhiteSpace(description.charAt(pos))) {
                            break;
                        }
                    }
                    description = description.substring(pos);
                    descriptions[index] = description;
                } else {
                    throw new IllegalArgumentException("Rule set name doesn't end in colon");
                }
            } else {
                this.name = "%default";
                this.isParseable = true;
            }
            if (description.length() == 0) {
                throw new IllegalArgumentException("Empty rule set description");
            }
            return;
        }
        throw new IllegalArgumentException("Empty rule set description");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: com.ibm.icu.text.NFRule} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void parseRules(java.lang.String r14) {
        /*
            r13 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            r2 = 0
            int r3 = r14.length()
        L_0x000b:
            r4 = 59
            int r4 = r14.indexOf(r4, r2)
            if (r4 >= 0) goto L_0x0014
            r4 = r3
        L_0x0014:
            java.lang.String r5 = r14.substring(r2, r4)
            com.ibm.icu.text.RuleBasedNumberFormat r6 = r13.owner
            com.ibm.icu.text.NFRule.makeRules(r5, r13, r1, r6, r0)
            boolean r5 = r0.isEmpty()
            if (r5 != 0) goto L_0x0030
            int r5 = r0.size()
            int r5 = r5 + -1
            java.lang.Object r5 = r0.get(r5)
            r1 = r5
            com.ibm.icu.text.NFRule r1 = (com.ibm.icu.text.NFRule) r1
        L_0x0030:
            int r2 = r4 + 1
            if (r2 < r3) goto L_0x0090
            r5 = 0
            java.util.Iterator r7 = r0.iterator()
        L_0x003a:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x0084
            java.lang.Object r8 = r7.next()
            com.ibm.icu.text.NFRule r8 = (com.ibm.icu.text.NFRule) r8
            long r9 = r8.getBaseValue()
            r11 = 0
            int r11 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r11 != 0) goto L_0x0054
            r8.setBaseValue(r5)
            goto L_0x0059
        L_0x0054:
            int r11 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r11 < 0) goto L_0x0061
            r5 = r9
        L_0x0059:
            boolean r11 = r13.isFractionRuleSet
            if (r11 != 0) goto L_0x0060
            r11 = 1
            long r5 = r5 + r11
        L_0x0060:
            goto L_0x003a
        L_0x0061:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "Rules are not in order, base: "
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.StringBuilder r11 = r11.append(r9)
            java.lang.String r12 = " < "
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.StringBuilder r11 = r11.append(r5)
            java.lang.String r11 = r11.toString()
            r7.<init>(r11)
            throw r7
        L_0x0084:
            int r7 = r0.size()
            com.ibm.icu.text.NFRule[] r7 = new com.ibm.icu.text.NFRule[r7]
            r13.rules = r7
            r0.toArray(r7)
            return
        L_0x0090:
            goto L_0x000b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.NFRuleSet.parseRules(java.lang.String):void");
    }

    /* access modifiers changed from: package-private */
    public void setNonNumericalRule(NFRule rule) {
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
        if (nFRuleArr[originalIndex] == null) {
            nFRuleArr[originalIndex] = newRule;
        } else if (this.owner.getDecimalFormatSymbols().getDecimalSeparator() == newRule.getDecimalPoint()) {
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
            if (i >= nFRuleArr.length) {
                int i2 = 0;
                while (true) {
                    NFRule[] nFRuleArr2 = this.rules;
                    if (i2 >= nFRuleArr2.length) {
                        return true;
                    }
                    if (!nFRuleArr2[i2].equals(that2.rules[i2])) {
                        return false;
                    }
                    i2++;
                }
            } else if (!Objects.equals(nFRuleArr[i], that2.nonNumericalRules[i])) {
                return false;
            } else {
                i++;
            }
        }
    }

    public int hashCode() {
        throw new AssertionError("hashCode not designed");
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.name).append(":\n");
        for (NFRule rule : this.rules) {
            result.append(rule.toString()).append("\n");
        }
        for (NFRule rule2 : this.nonNumericalRules) {
            if (rule2 != null) {
                if (rule2.getBaseValue() == -2 || rule2.getBaseValue() == -3 || rule2.getBaseValue() == -4) {
                    Iterator it = this.fractionRules.iterator();
                    while (it.hasNext()) {
                        NFRule fractionRule = (NFRule) it.next();
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
        if (recursionCount < 64) {
            findNormalRule(number).doFormat(number, toInsertInto, pos, recursionCount + 1);
            return;
        }
        throw new IllegalStateException("Recursion limit exceeded when applying ruleSet " + this.name);
    }

    public void format(double number, StringBuilder toInsertInto, int pos, int recursionCount) {
        if (recursionCount < 64) {
            findRule(number).doFormat(number, toInsertInto, pos, recursionCount + 1);
            return;
        }
        throw new IllegalStateException("Recursion limit exceeded when applying ruleSet " + this.name);
    }

    /* access modifiers changed from: package-private */
    public NFRule findRule(double number) {
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
            return findFractionRuleSetRule((double) number);
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
        if (hi <= 0) {
            return this.nonNumericalRules[3];
        }
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
        if (hi != 0) {
            NFRule result = this.rules[hi - 1];
            if (!result.shouldRollBack(number)) {
                return result;
            }
            if (hi != 1) {
                return this.rules[hi - 2];
            }
            throw new IllegalStateException("The rule set " + this.name + " cannot roll back from the rule '" + result + "'");
        }
        throw new IllegalStateException("The rule set " + this.name + " cannot format the value " + number);
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
        long numerator = Math.round(((double) leastCommonMultiple) * number);
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
        if (i3 < nFRuleArr3.length && nFRuleArr3[winner + 1].getBaseValue() == this.rules[winner].getBaseValue() && (Math.round(((double) this.rules[winner].getBaseValue()) * number) < 1 || Math.round(((double) this.rules[winner].getBaseValue()) * number) >= 2)) {
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
        return (x / (x1 << p2)) * y;
    }

    public Number parse(String text, ParsePosition parsePosition, double upperBound, int nonNumericalExecutedRuleMask) {
        ParsePosition parsePosition2 = parsePosition;
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
                parsePosition2.setIndex(0);
            }
            nonNumericalRuleIdx++;
        }
        Number result3 = result2;
        for (int i = this.rules.length - 1; i >= 0 && highWaterMark.getIndex() < text.length(); i--) {
            if (this.isFractionRuleSet || ((double) this.rules[i].getBaseValue()) < upperBound) {
                Number tempResult2 = this.rules[i].doParse(text, parsePosition, this.isFractionRuleSet, upperBound, nonNumericalExecutedRuleMask2);
                if (parsePosition.getIndex() > highWaterMark.getIndex()) {
                    result3 = tempResult2;
                    highWaterMark.setIndex(parsePosition.getIndex());
                }
                parsePosition2.setIndex(0);
            }
        }
        parsePosition2.setIndex(highWaterMark.getIndex());
        return result3;
    }

    public void setDecimalFormatSymbols(DecimalFormatSymbols newSymbols) {
        for (NFRule rule : this.rules) {
            rule.setDecimalFormatSymbols(newSymbols);
        }
        if (this.fractionRules != null) {
            for (int nonNumericalIdx = 1; nonNumericalIdx <= 3; nonNumericalIdx++) {
                if (this.nonNumericalRules[nonNumericalIdx] != null) {
                    Iterator it = this.fractionRules.iterator();
                    while (it.hasNext()) {
                        NFRule rule2 = (NFRule) it.next();
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
