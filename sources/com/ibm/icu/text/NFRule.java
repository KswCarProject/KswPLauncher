package com.ibm.icu.text;

import com.ibm.icu.impl.PatternProps;
import com.ibm.icu.text.PluralRules;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.List;
import java.util.Objects;
import kotlin.text.Typography;

/* loaded from: classes.dex */
final class NFRule {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int IMPROPER_FRACTION_RULE = -2;
    static final int INFINITY_RULE = -5;
    static final int MASTER_RULE = -4;
    static final int NAN_RULE = -6;
    static final int NEGATIVE_NUMBER_RULE = -1;
    static final int PROPER_FRACTION_RULE = -3;
    private long baseValue;
    private final RuleBasedNumberFormat formatter;
    private String ruleText;
    static final Long ZERO = 0L;
    private static final String[] RULE_PREFIXES = {"<<", "<%", "<#", "<0", ">>", ">%", ">#", ">0", "=%", "=#", "=0"};
    private int radix = 10;
    private short exponent = 0;
    private char decimalPoint = 0;
    private PluralFormat rulePatternFormat = null;
    private NFSubstitution sub1 = null;
    private NFSubstitution sub2 = null;

    /* JADX WARN: Code restructure failed: missing block: B:25:0x006b, code lost:
        if (r8 != (-4)) goto L28;
     */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0114  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void makeRules(String description, NFRuleSet owner, NFRule predecessor, RuleBasedNumberFormat ownersOwner, List<NFRule> returnList) {
        NFRule rule1 = new NFRule(ownersOwner, description);
        String description2 = rule1.ruleText;
        int brack1 = description2.indexOf(91);
        int brack2 = brack1 < 0 ? -1 : description2.indexOf(93);
        if (brack2 >= 0 && brack1 <= brack2) {
            long j = rule1.baseValue;
            if (j != -3 && j != -1 && j != -5 && j != -6) {
                NFRule rule2 = null;
                StringBuilder sbuf = new StringBuilder();
                long j2 = rule1.baseValue;
                if (j2 <= 0 || j2 % power(rule1.radix, rule1.exponent) != 0) {
                    long j3 = rule1.baseValue;
                    if (j3 != -2) {
                    }
                }
                rule2 = new NFRule(ownersOwner, null);
                long j4 = rule1.baseValue;
                if (j4 >= 0) {
                    rule2.baseValue = j4;
                    if (!owner.isFractionSet()) {
                        rule1.baseValue++;
                    }
                } else if (j4 == -2) {
                    rule2.baseValue = -3L;
                } else if (j4 == -4) {
                    rule2.baseValue = j4;
                    rule1.baseValue = -2L;
                }
                rule2.radix = rule1.radix;
                rule2.exponent = rule1.exponent;
                sbuf.append(description2.substring(0, brack1));
                if (brack2 + 1 < description2.length()) {
                    sbuf.append(description2.substring(brack2 + 1));
                }
                rule2.extractSubstitutions(owner, sbuf.toString(), predecessor);
                sbuf.setLength(0);
                sbuf.append(description2.substring(0, brack1));
                sbuf.append(description2.substring(brack1 + 1, brack2));
                if (brack2 + 1 < description2.length()) {
                    sbuf.append(description2.substring(brack2 + 1));
                }
                rule1.extractSubstitutions(owner, sbuf.toString(), predecessor);
                if (rule2 != null) {
                    if (rule2.baseValue >= 0) {
                        returnList.add(rule2);
                    } else {
                        owner.setNonNumericalRule(rule2);
                    }
                }
                if (rule1.baseValue < 0) {
                    returnList.add(rule1);
                    return;
                } else {
                    owner.setNonNumericalRule(rule1);
                    return;
                }
            }
        }
        rule1.extractSubstitutions(owner, description2, predecessor);
        if (rule1.baseValue < 0) {
        }
    }

    public NFRule(RuleBasedNumberFormat formatter, String ruleText) {
        this.ruleText = null;
        this.formatter = formatter;
        this.ruleText = ruleText != null ? parseRuleDescriptor(ruleText) : null;
    }

    private String parseRuleDescriptor(String description) {
        short s;
        String description2 = description;
        int p = description2.indexOf(":");
        if (p != -1) {
            String descriptor = description2.substring(0, p);
            while (true) {
                p++;
                if (p >= description.length() || !PatternProps.isWhiteSpace(description2.charAt(p))) {
                    break;
                }
            }
            description2 = description2.substring(p);
            int descriptorLength = descriptor.length();
            char firstChar = descriptor.charAt(0);
            char lastChar = descriptor.charAt(descriptorLength - 1);
            char c = '0';
            if (firstChar >= '0') {
                char c2 = '9';
                if (firstChar <= '9' && lastChar != 'x') {
                    long tempValue = 0;
                    char c3 = 0;
                    int p2 = 0;
                    while (p2 < descriptorLength) {
                        c3 = descriptor.charAt(p2);
                        if (c3 >= '0' && c3 <= '9') {
                            tempValue = (10 * tempValue) + (c3 - '0');
                        } else if (c3 == '/' || c3 == '>') {
                            break;
                        } else if (!PatternProps.isWhiteSpace(c3) && c3 != ',' && c3 != '.') {
                            throw new IllegalArgumentException("Illegal character " + c3 + " in rule descriptor");
                        }
                        p2++;
                    }
                    setBaseValue(tempValue);
                    if (c3 == '/') {
                        long tempValue2 = 0;
                        p2++;
                        while (p2 < descriptorLength) {
                            c3 = descriptor.charAt(p2);
                            if (c3 >= c && c3 <= c2) {
                                tempValue2 = (tempValue2 * 10) + (c3 - '0');
                            } else if (c3 == '>') {
                                break;
                            } else if (!PatternProps.isWhiteSpace(c3) && c3 != ',') {
                                if (c3 != '.') {
                                    throw new IllegalArgumentException("Illegal character " + c3 + " in rule descriptor");
                                }
                            }
                            p2++;
                            c = '0';
                            c2 = '9';
                        }
                        int i = (int) tempValue2;
                        this.radix = i;
                        if (i != 0) {
                            this.exponent = expectedExponent();
                        } else {
                            throw new IllegalArgumentException("Rule can't have radix of 0");
                        }
                    }
                    if (c3 == '>') {
                        while (p2 < descriptorLength) {
                            char c4 = descriptor.charAt(p2);
                            if (c4 == '>' && (s = this.exponent) > 0) {
                                this.exponent = (short) (s - 1);
                                p2++;
                            } else {
                                throw new IllegalArgumentException("Illegal character in rule descriptor");
                            }
                        }
                    }
                }
            }
            if (descriptor.equals("-x")) {
                setBaseValue(-1L);
            } else if (descriptorLength == 3) {
                if (firstChar == '0' && lastChar == 'x') {
                    setBaseValue(-3L);
                    this.decimalPoint = descriptor.charAt(1);
                } else if (firstChar == 'x' && lastChar == 'x') {
                    setBaseValue(-2L);
                    this.decimalPoint = descriptor.charAt(1);
                } else if (firstChar == 'x' && lastChar == '0') {
                    setBaseValue(-4L);
                    this.decimalPoint = descriptor.charAt(1);
                } else if (descriptor.equals("NaN")) {
                    setBaseValue(-6L);
                } else if (descriptor.equals("Inf")) {
                    setBaseValue(-5L);
                }
            }
        }
        if (description2.length() > 0 && description2.charAt(0) == '\'') {
            return description2.substring(1);
        }
        return description2;
    }

    private void extractSubstitutions(NFRuleSet owner, String ruleText, NFRule predecessor) {
        PluralRules.PluralType pluralType;
        this.ruleText = ruleText;
        NFSubstitution extractSubstitution = extractSubstitution(owner, predecessor);
        this.sub1 = extractSubstitution;
        if (extractSubstitution == null) {
            this.sub2 = null;
        } else {
            this.sub2 = extractSubstitution(owner, predecessor);
        }
        String ruleText2 = this.ruleText;
        int pluralRuleStart = ruleText2.indexOf("$(");
        int pluralRuleEnd = pluralRuleStart >= 0 ? ruleText2.indexOf(")$", pluralRuleStart) : -1;
        if (pluralRuleEnd >= 0) {
            int endType = ruleText2.indexOf(44, pluralRuleStart);
            if (endType < 0) {
                throw new IllegalArgumentException("Rule \"" + ruleText2 + "\" does not have a defined type");
            }
            String type = this.ruleText.substring(pluralRuleStart + 2, endType);
            if ("cardinal".equals(type)) {
                pluralType = PluralRules.PluralType.CARDINAL;
            } else if ("ordinal".equals(type)) {
                pluralType = PluralRules.PluralType.ORDINAL;
            } else {
                throw new IllegalArgumentException(type + " is an unknown type");
            }
            this.rulePatternFormat = this.formatter.createPluralFormat(pluralType, ruleText2.substring(endType + 1, pluralRuleEnd));
        }
    }

    private NFSubstitution extractSubstitution(NFRuleSet owner, NFRule predecessor) {
        int subEnd;
        int subStart = indexOfAnyRulePrefix(this.ruleText);
        if (subStart == -1) {
            return null;
        }
        if (this.ruleText.startsWith(">>>", subStart)) {
            subEnd = subStart + 2;
        } else {
            char c = this.ruleText.charAt(subStart);
            int subEnd2 = this.ruleText.indexOf(c, subStart + 1);
            if (c == '<' && subEnd2 != -1 && subEnd2 < this.ruleText.length() - 1 && this.ruleText.charAt(subEnd2 + 1) == c) {
                subEnd = subEnd2 + 1;
            } else {
                subEnd = subEnd2;
            }
        }
        if (subEnd == -1) {
            return null;
        }
        NFSubstitution result = NFSubstitution.makeSubstitution(subStart, this, predecessor, owner, this.formatter, this.ruleText.substring(subStart, subEnd + 1));
        this.ruleText = this.ruleText.substring(0, subStart) + this.ruleText.substring(subEnd + 1);
        return result;
    }

    final void setBaseValue(long newBaseValue) {
        this.baseValue = newBaseValue;
        this.radix = 10;
        if (newBaseValue >= 1) {
            short expectedExponent = expectedExponent();
            this.exponent = expectedExponent;
            NFSubstitution nFSubstitution = this.sub1;
            if (nFSubstitution != null) {
                nFSubstitution.setDivisor(this.radix, expectedExponent);
            }
            NFSubstitution nFSubstitution2 = this.sub2;
            if (nFSubstitution2 != null) {
                nFSubstitution2.setDivisor(this.radix, this.exponent);
                return;
            }
            return;
        }
        this.exponent = (short) 0;
    }

    private short expectedExponent() {
        if (this.radix != 0) {
            long j = this.baseValue;
            if (j < 1) {
                return (short) 0;
            }
            short tempResult = (short) (Math.log(j) / Math.log(this.radix));
            if (power(this.radix, (short) (tempResult + 1)) <= this.baseValue) {
                return (short) (tempResult + 1);
            }
            return tempResult;
        }
        return (short) 0;
    }

    private static int indexOfAnyRulePrefix(String ruleText) {
        String[] strArr;
        int result = -1;
        if (ruleText.length() > 0) {
            for (String string : RULE_PREFIXES) {
                int pos = ruleText.indexOf(string);
                if (pos != -1 && (result == -1 || pos < result)) {
                    result = pos;
                }
            }
        }
        return result;
    }

    public boolean equals(Object that) {
        if (that instanceof NFRule) {
            NFRule that2 = (NFRule) that;
            return this.baseValue == that2.baseValue && this.radix == that2.radix && this.exponent == that2.exponent && this.ruleText.equals(that2.ruleText) && Objects.equals(this.sub1, that2.sub1) && Objects.equals(this.sub2, that2.sub2);
        }
        return false;
    }

    public int hashCode() {
        throw new AssertionError("hashCode not designed");
    }

    public String toString() {
        NFSubstitution nFSubstitution;
        StringBuilder result = new StringBuilder();
        long j = this.baseValue;
        if (j == -1) {
            result.append("-x: ");
        } else if (j == -2) {
            StringBuilder append = result.append('x');
            char c = this.decimalPoint;
            append.append(c != 0 ? c : '.').append("x: ");
        } else if (j == -3) {
            StringBuilder append2 = result.append('0');
            char c2 = this.decimalPoint;
            append2.append(c2 != 0 ? c2 : '.').append("x: ");
        } else if (j == -4) {
            StringBuilder append3 = result.append('x');
            char c3 = this.decimalPoint;
            append3.append(c3 != 0 ? c3 : '.').append("0: ");
        } else if (j == -5) {
            result.append("Inf: ");
        } else if (j == -6) {
            result.append("NaN: ");
        } else {
            result.append(String.valueOf(j));
            if (this.radix != 10) {
                result.append('/').append(this.radix);
            }
            int numCarets = expectedExponent() - this.exponent;
            for (int i = 0; i < numCarets; i++) {
                result.append(Typography.greater);
            }
            result.append(PluralRules.KEYWORD_RULE_SEPARATOR);
        }
        if (this.ruleText.startsWith(" ") && ((nFSubstitution = this.sub1) == null || nFSubstitution.getPos() != 0)) {
            result.append('\'');
        }
        StringBuilder ruleTextCopy = new StringBuilder(this.ruleText);
        NFSubstitution nFSubstitution2 = this.sub2;
        if (nFSubstitution2 != null) {
            ruleTextCopy.insert(nFSubstitution2.getPos(), this.sub2.toString());
        }
        NFSubstitution nFSubstitution3 = this.sub1;
        if (nFSubstitution3 != null) {
            ruleTextCopy.insert(nFSubstitution3.getPos(), this.sub1.toString());
        }
        result.append(ruleTextCopy.toString());
        result.append(';');
        return result.toString();
    }

    public final char getDecimalPoint() {
        return this.decimalPoint;
    }

    public final long getBaseValue() {
        return this.baseValue;
    }

    public long getDivisor() {
        return power(this.radix, this.exponent);
    }

    public void doFormat(long number, StringBuilder toInsertInto, int pos, int recursionCount) {
        int pluralRuleStart;
        int lengthOffset;
        int pluralRuleStart2 = this.ruleText.length();
        if (this.rulePatternFormat == null) {
            toInsertInto.insert(pos, this.ruleText);
            pluralRuleStart = pluralRuleStart2;
            lengthOffset = 0;
        } else {
            int pluralRuleStart3 = this.ruleText.indexOf("$(");
            int pluralRuleEnd = this.ruleText.indexOf(")$", pluralRuleStart3);
            int initialLength = toInsertInto.length();
            if (pluralRuleEnd < this.ruleText.length() - 1) {
                toInsertInto.insert(pos, this.ruleText.substring(pluralRuleEnd + 2));
            }
            toInsertInto.insert(pos, this.rulePatternFormat.format(number / power(this.radix, this.exponent)));
            if (pluralRuleStart3 > 0) {
                toInsertInto.insert(pos, this.ruleText.substring(0, pluralRuleStart3));
            }
            int lengthOffset2 = this.ruleText.length() - (toInsertInto.length() - initialLength);
            pluralRuleStart = pluralRuleStart3;
            lengthOffset = lengthOffset2;
        }
        NFSubstitution nFSubstitution = this.sub2;
        if (nFSubstitution != null) {
            nFSubstitution.doSubstitution(number, toInsertInto, pos - (nFSubstitution.getPos() > pluralRuleStart ? lengthOffset : 0), recursionCount);
        }
        NFSubstitution nFSubstitution2 = this.sub1;
        if (nFSubstitution2 != null) {
            nFSubstitution2.doSubstitution(number, toInsertInto, pos - (nFSubstitution2.getPos() > pluralRuleStart ? lengthOffset : 0), recursionCount);
        }
    }

    public void doFormat(double number, StringBuilder toInsertInto, int pos, int recursionCount) {
        int pluralRuleStart;
        int lengthOffset;
        int pluralRuleStart2 = this.ruleText.length();
        if (this.rulePatternFormat == null) {
            toInsertInto.insert(pos, this.ruleText);
            pluralRuleStart = pluralRuleStart2;
            lengthOffset = 0;
        } else {
            int pluralRuleStart3 = this.ruleText.indexOf("$(");
            int pluralRuleEnd = this.ruleText.indexOf(")$", pluralRuleStart3);
            int initialLength = toInsertInto.length();
            if (pluralRuleEnd < this.ruleText.length() - 1) {
                toInsertInto.insert(pos, this.ruleText.substring(pluralRuleEnd + 2));
            }
            double pluralVal = (0.0d > number || number >= 1.0d) ? number / power(this.radix, this.exponent) : Math.round(power(this.radix, this.exponent) * number);
            toInsertInto.insert(pos, this.rulePatternFormat.format((long) pluralVal));
            if (pluralRuleStart3 > 0) {
                toInsertInto.insert(pos, this.ruleText.substring(0, pluralRuleStart3));
            }
            int lengthOffset2 = this.ruleText.length() - (toInsertInto.length() - initialLength);
            pluralRuleStart = pluralRuleStart3;
            lengthOffset = lengthOffset2;
        }
        NFSubstitution nFSubstitution = this.sub2;
        if (nFSubstitution != null) {
            nFSubstitution.doSubstitution(number, toInsertInto, pos - (nFSubstitution.getPos() > pluralRuleStart ? lengthOffset : 0), recursionCount);
        }
        NFSubstitution nFSubstitution2 = this.sub1;
        if (nFSubstitution2 != null) {
            nFSubstitution2.doSubstitution(number, toInsertInto, pos - (nFSubstitution2.getPos() > pluralRuleStart ? lengthOffset : 0), recursionCount);
        }
    }

    static long power(long base, short exponent) {
        if (exponent < 0) {
            throw new IllegalArgumentException("Exponent can not be negative");
        }
        if (base < 0) {
            throw new IllegalArgumentException("Base can not be negative");
        }
        long result = 1;
        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result *= base;
            }
            base *= base;
            exponent = (short) (exponent >> 1);
        }
        return result;
    }

    public boolean shouldRollBack(long number) {
        NFSubstitution nFSubstitution;
        NFSubstitution nFSubstitution2 = this.sub1;
        if ((nFSubstitution2 == null || !nFSubstitution2.isModulusSubstitution()) && ((nFSubstitution = this.sub2) == null || !nFSubstitution.isModulusSubstitution())) {
            return false;
        }
        long divisor = power(this.radix, this.exponent);
        return number % divisor == 0 && this.baseValue % divisor != 0;
    }

    public Number doParse(String text, ParsePosition parsePosition, boolean isFractionRule, double upperBound, int nonNumericalExecutedRuleMask) {
        int i;
        int highWaterMark;
        int start;
        double result;
        int i2 = 0;
        ParsePosition pp = new ParsePosition(0);
        NFSubstitution nFSubstitution = this.sub1;
        int sub1Pos = nFSubstitution != null ? nFSubstitution.getPos() : this.ruleText.length();
        NFSubstitution nFSubstitution2 = this.sub2;
        int sub2Pos = nFSubstitution2 != null ? nFSubstitution2.getPos() : this.ruleText.length();
        String workText = stripPrefix(text, this.ruleText.substring(0, sub1Pos), pp);
        int prefixLength = text.length() - workText.length();
        if (pp.getIndex() == 0 && sub1Pos != 0) {
            return ZERO;
        }
        long j = this.baseValue;
        if (j == -5) {
            parsePosition.setIndex(pp.getIndex());
            return Double.valueOf(Double.POSITIVE_INFINITY);
        } else if (j == -6) {
            parsePosition.setIndex(pp.getIndex());
            return Double.valueOf(Double.NaN);
        } else {
            double tempBaseValue = Math.max(0L, j);
            double result2 = 0.0d;
            int start2 = 0;
            int highWaterMark2 = 0;
            while (true) {
                pp.setIndex(i2);
                int highWaterMark3 = highWaterMark2;
                double tempBaseValue2 = tempBaseValue;
                String workText2 = workText;
                int sub2Pos2 = sub2Pos;
                int sub1Pos2 = sub1Pos;
                double partialResult = matchToDelimiter(workText, start2, tempBaseValue, this.ruleText.substring(sub1Pos, sub2Pos), this.rulePatternFormat, pp, this.sub1, upperBound, nonNumericalExecutedRuleMask).doubleValue();
                if (pp.getIndex() != 0 || this.sub1 == null) {
                    int start3 = pp.getIndex();
                    String workText22 = workText2.substring(pp.getIndex());
                    ParsePosition pp2 = new ParsePosition(0);
                    i = 0;
                    double partialResult2 = matchToDelimiter(workText22, 0, partialResult, this.ruleText.substring(sub2Pos2), this.rulePatternFormat, pp2, this.sub2, upperBound, nonNumericalExecutedRuleMask).doubleValue();
                    if (pp2.getIndex() != 0 || this.sub2 == null) {
                        highWaterMark = highWaterMark3;
                        if (prefixLength + pp.getIndex() + pp2.getIndex() > highWaterMark) {
                            int highWaterMark4 = prefixLength + pp.getIndex() + pp2.getIndex();
                            highWaterMark2 = highWaterMark4;
                            result2 = partialResult2;
                            start = start3;
                        }
                    } else {
                        highWaterMark = highWaterMark3;
                    }
                    highWaterMark2 = highWaterMark;
                    start = start3;
                } else {
                    start = start2;
                    highWaterMark2 = highWaterMark3;
                    i = 0;
                }
                if (sub1Pos2 == sub2Pos2 || pp.getIndex() <= 0 || pp.getIndex() >= workText2.length() || pp.getIndex() == start) {
                    break;
                }
                start2 = start;
                sub1Pos = sub1Pos2;
                sub2Pos = sub2Pos2;
                workText = workText2;
                i2 = i;
                tempBaseValue = tempBaseValue2;
            }
            parsePosition.setIndex(highWaterMark2);
            if (isFractionRule && highWaterMark2 > 0 && this.sub1 == null) {
                result = 1.0d / result2;
            } else {
                result = result2;
            }
            if (result == ((long) result)) {
                return Long.valueOf((long) result);
            }
            return new Double(result);
        }
    }

    private String stripPrefix(String text, String prefix, ParsePosition pp) {
        if (prefix.length() == 0) {
            return text;
        }
        int pfl = prefixLength(text, prefix);
        if (pfl != 0) {
            pp.setIndex(pp.getIndex() + pfl);
            return text.substring(pfl);
        }
        return text;
    }

    private Number matchToDelimiter(String text, int startPos, double baseVal, String delimiter, PluralFormat pluralFormatDelimiter, ParsePosition pp, NFSubstitution sub, double upperBound, int nonNumericalExecutedRuleMask) {
        if (allIgnorable(delimiter)) {
            if (sub == null) {
                return Double.valueOf(baseVal);
            }
            ParsePosition tempPP = new ParsePosition(0);
            Number result = ZERO;
            Number tempResult = sub.doParse(text, tempPP, baseVal, upperBound, this.formatter.lenientParseEnabled(), nonNumericalExecutedRuleMask);
            if (tempPP.getIndex() != 0) {
                pp.setIndex(tempPP.getIndex());
                if (tempResult != null) {
                    return tempResult;
                }
                return result;
            }
            return result;
        }
        ParsePosition tempPP2 = new ParsePosition(0);
        int[] temp = findText(text, delimiter, pluralFormatDelimiter, startPos);
        int dPos = temp[0];
        int dLen = temp[1];
        while (dPos >= 0) {
            String subText = text.substring(0, dPos);
            if (subText.length() > 0) {
                Number tempResult2 = sub.doParse(subText, tempPP2, baseVal, upperBound, this.formatter.lenientParseEnabled(), nonNumericalExecutedRuleMask);
                if (tempPP2.getIndex() == dPos) {
                    pp.setIndex(dPos + dLen);
                    return tempResult2;
                }
            }
            tempPP2.setIndex(0);
            int[] temp2 = findText(text, delimiter, pluralFormatDelimiter, dPos + dLen);
            dPos = temp2[0];
            dLen = temp2[1];
        }
        pp.setIndex(0);
        return ZERO;
    }

    private int prefixLength(String str, String prefix) {
        if (prefix.length() == 0) {
            return 0;
        }
        RbnfLenientScanner scanner = this.formatter.getLenientScanner();
        if (scanner != null) {
            return scanner.prefixLength(str, prefix);
        }
        if (str.startsWith(prefix)) {
            return prefix.length();
        }
        return 0;
    }

    private int[] findText(String str, String key, PluralFormat pluralFormatKey, int startingAt) {
        RbnfLenientScanner scanner = this.formatter.getLenientScanner();
        if (pluralFormatKey == null) {
            return scanner != null ? scanner.findText(str, key, startingAt) : new int[]{str.indexOf(key, startingAt), key.length()};
        }
        FieldPosition position = new FieldPosition(0);
        position.setBeginIndex(startingAt);
        pluralFormatKey.parseType(str, scanner, position);
        int start = position.getBeginIndex();
        if (start >= 0) {
            int pluralRuleStart = this.ruleText.indexOf("$(");
            int pluralRuleSuffix = this.ruleText.indexOf(")$", pluralRuleStart) + 2;
            int matchLen = position.getEndIndex() - start;
            String prefix = this.ruleText.substring(0, pluralRuleStart);
            String suffix = this.ruleText.substring(pluralRuleSuffix);
            if (str.regionMatches(start - prefix.length(), prefix, 0, prefix.length()) && str.regionMatches(start + matchLen, suffix, 0, suffix.length())) {
                return new int[]{start - prefix.length(), prefix.length() + matchLen + suffix.length()};
            }
        }
        return new int[]{-1, 0};
    }

    private boolean allIgnorable(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        RbnfLenientScanner scanner = this.formatter.getLenientScanner();
        return scanner != null && scanner.allIgnorable(str);
    }

    public void setDecimalFormatSymbols(DecimalFormatSymbols newSymbols) {
        NFSubstitution nFSubstitution = this.sub1;
        if (nFSubstitution != null) {
            nFSubstitution.setDecimalFormatSymbols(newSymbols);
        }
        NFSubstitution nFSubstitution2 = this.sub2;
        if (nFSubstitution2 != null) {
            nFSubstitution2.setDecimalFormatSymbols(newSymbols);
        }
    }
}
