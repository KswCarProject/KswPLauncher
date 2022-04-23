package com.ibm.icu.text;

import com.ibm.icu.impl.PatternProps;
import com.ibm.icu.text.PluralRules;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Objects;

final class NFRule {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int IMPROPER_FRACTION_RULE = -2;
    static final int INFINITY_RULE = -5;
    static final int MASTER_RULE = -4;
    static final int NAN_RULE = -6;
    static final int NEGATIVE_NUMBER_RULE = -1;
    static final int PROPER_FRACTION_RULE = -3;
    private static final String[] RULE_PREFIXES = {"<<", "<%", "<#", "<0", ">>", ">%", ">#", ">0", "=%", "=#", "=0"};
    static final Long ZERO = 0L;
    private long baseValue;
    private char decimalPoint = 0;
    private short exponent = 0;
    private final RuleBasedNumberFormat formatter;
    private int radix = 10;
    private PluralFormat rulePatternFormat;
    private String ruleText;
    private NFSubstitution sub1;
    private NFSubstitution sub2;

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x006b, code lost:
        if (r8 != -4) goto L_0x00c7;
     */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0110  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0114  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void makeRules(java.lang.String r19, com.ibm.icu.text.NFRuleSet r20, com.ibm.icu.text.NFRule r21, com.ibm.icu.text.RuleBasedNumberFormat r22, java.util.List<com.ibm.icu.text.NFRule> r23) {
        /*
            r0 = r20
            r1 = r21
            r2 = r22
            r3 = r23
            com.ibm.icu.text.NFRule r4 = new com.ibm.icu.text.NFRule
            r5 = r19
            r4.<init>(r2, r5)
            java.lang.String r5 = r4.ruleText
            r6 = 91
            int r6 = r5.indexOf(r6)
            if (r6 >= 0) goto L_0x001b
            r7 = -1
            goto L_0x0021
        L_0x001b:
            r7 = 93
            int r7 = r5.indexOf(r7)
        L_0x0021:
            r8 = 0
            if (r7 < 0) goto L_0x0105
            if (r6 > r7) goto L_0x0105
            long r10 = r4.baseValue
            r12 = -3
            int r14 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r14 == 0) goto L_0x0105
            r14 = -1
            int r14 = (r10 > r14 ? 1 : (r10 == r14 ? 0 : -1))
            if (r14 == 0) goto L_0x0105
            r14 = -5
            int r14 = (r10 > r14 ? 1 : (r10 == r14 ? 0 : -1))
            if (r14 == 0) goto L_0x0105
            r14 = -6
            int r10 = (r10 > r14 ? 1 : (r10 == r14 ? 0 : -1))
            if (r10 != 0) goto L_0x0043
            goto L_0x0105
        L_0x0043:
            r10 = 0
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            long r14 = r4.baseValue
            int r16 = (r14 > r8 ? 1 : (r14 == r8 ? 0 : -1))
            r17 = -4
            if (r16 <= 0) goto L_0x0061
            int r12 = r4.radix
            long r12 = (long) r12
            short r8 = r4.exponent
            long r8 = power(r12, r8)
            long r14 = r14 % r8
            r8 = 0
            int r12 = (r14 > r8 ? 1 : (r14 == r8 ? 0 : -1))
            if (r12 == 0) goto L_0x006d
        L_0x0061:
            long r8 = r4.baseValue
            r12 = -2
            int r14 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r14 == 0) goto L_0x006d
            int r8 = (r8 > r17 ? 1 : (r8 == r17 ? 0 : -1))
            if (r8 != 0) goto L_0x00c7
        L_0x006d:
            com.ibm.icu.text.NFRule r8 = new com.ibm.icu.text.NFRule
            r9 = 0
            r8.<init>(r2, r9)
            r10 = r8
            long r8 = r4.baseValue
            r12 = 0
            int r14 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r14 < 0) goto L_0x008c
            r10.baseValue = r8
            boolean r8 = r20.isFractionSet()
            if (r8 != 0) goto L_0x009f
            long r8 = r4.baseValue
            r12 = 1
            long r8 = r8 + r12
            r4.baseValue = r8
            goto L_0x009f
        L_0x008c:
            r12 = -2
            int r14 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r14 != 0) goto L_0x0097
            r8 = -3
            r10.baseValue = r8
            goto L_0x009f
        L_0x0097:
            int r14 = (r8 > r17 ? 1 : (r8 == r17 ? 0 : -1))
            if (r14 != 0) goto L_0x009f
            r10.baseValue = r8
            r4.baseValue = r12
        L_0x009f:
            int r8 = r4.radix
            r10.radix = r8
            short r8 = r4.exponent
            r10.exponent = r8
            r8 = 0
            java.lang.String r9 = r5.substring(r8, r6)
            r11.append(r9)
            int r8 = r7 + 1
            int r9 = r5.length()
            if (r8 >= r9) goto L_0x00c0
            int r8 = r7 + 1
            java.lang.String r8 = r5.substring(r8)
            r11.append(r8)
        L_0x00c0:
            java.lang.String r8 = r11.toString()
            r10.extractSubstitutions(r0, r8, r1)
        L_0x00c7:
            r8 = 0
            r11.setLength(r8)
            java.lang.String r8 = r5.substring(r8, r6)
            r11.append(r8)
            int r8 = r6 + 1
            java.lang.String r8 = r5.substring(r8, r7)
            r11.append(r8)
            int r8 = r7 + 1
            int r9 = r5.length()
            if (r8 >= r9) goto L_0x00ec
            int r8 = r7 + 1
            java.lang.String r8 = r5.substring(r8)
            r11.append(r8)
        L_0x00ec:
            java.lang.String r8 = r11.toString()
            r4.extractSubstitutions(r0, r8, r1)
            if (r10 == 0) goto L_0x0108
            long r8 = r10.baseValue
            r12 = 0
            int r8 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r8 < 0) goto L_0x0101
            r3.add(r10)
            goto L_0x0108
        L_0x0101:
            r0.setNonNumericalRule(r10)
            goto L_0x0108
        L_0x0105:
            r4.extractSubstitutions(r0, r5, r1)
        L_0x0108:
            long r8 = r4.baseValue
            r10 = 0
            int r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r8 < 0) goto L_0x0114
            r3.add(r4)
            goto L_0x0117
        L_0x0114:
            r0.setNonNumericalRule(r4)
        L_0x0117:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.NFRule.makeRules(java.lang.String, com.ibm.icu.text.NFRuleSet, com.ibm.icu.text.NFRule, com.ibm.icu.text.RuleBasedNumberFormat, java.util.List):void");
    }

    public NFRule(RuleBasedNumberFormat formatter2, String ruleText2) {
        String str = null;
        this.ruleText = null;
        this.rulePatternFormat = null;
        this.sub1 = null;
        this.sub2 = null;
        this.formatter = formatter2;
        this.ruleText = ruleText2 != null ? parseRuleDescriptor(ruleText2) : str;
    }

    private String parseRuleDescriptor(String description) {
        int p;
        short s;
        String description2 = description;
        int p2 = description2.indexOf(":");
        if (p2 != -1) {
            String descriptor = description2.substring(0, p2);
            while (true) {
                p2++;
                if (p2 >= description.length() || !PatternProps.isWhiteSpace(description2.charAt(p2))) {
                    description2 = description2.substring(p2);
                    int descriptorLength = descriptor.length();
                    char firstChar = descriptor.charAt(0);
                    char lastChar = descriptor.charAt(descriptorLength - 1);
                    char c = '0';
                }
            }
            description2 = description2.substring(p2);
            int descriptorLength2 = descriptor.length();
            char firstChar2 = descriptor.charAt(0);
            char lastChar2 = descriptor.charAt(descriptorLength2 - 1);
            char c2 = '0';
            if (firstChar2 >= '0') {
                char c3 = '9';
                if (firstChar2 <= '9' && lastChar2 != 'x') {
                    long tempValue = 0;
                    char c4 = 0;
                    int p3 = 0;
                    while (p < descriptorLength2) {
                        c4 = descriptor.charAt(p);
                        if (c4 < '0' || c4 > '9') {
                            if (c4 == '/' || c4 == '>') {
                                break;
                            } else if (!(PatternProps.isWhiteSpace(c4) || c4 == ',' || c4 == '.')) {
                                throw new IllegalArgumentException("Illegal character " + c4 + " in rule descriptor");
                            }
                        } else {
                            tempValue = (10 * tempValue) + ((long) (c4 - '0'));
                        }
                        p3 = p + 1;
                    }
                    setBaseValue(tempValue);
                    if (c4 == '/') {
                        long tempValue2 = 0;
                        p++;
                        while (p < descriptorLength2) {
                            c4 = descriptor.charAt(p);
                            if (c4 >= c2 && c4 <= c3) {
                                tempValue2 = (tempValue2 * 10) + ((long) (c4 - '0'));
                            } else if (c4 == '>') {
                                break;
                            } else if (!PatternProps.isWhiteSpace(c4)) {
                                if (c4 != ',') {
                                    if (c4 != '.') {
                                        throw new IllegalArgumentException("Illegal character " + c4 + " in rule descriptor");
                                    }
                                }
                            }
                            p++;
                            c2 = '0';
                            c3 = '9';
                        }
                        int i = (int) tempValue2;
                        this.radix = i;
                        if (i != 0) {
                            this.exponent = expectedExponent();
                        } else {
                            throw new IllegalArgumentException("Rule can't have radix of 0");
                        }
                    }
                    if (c4 == '>') {
                        while (p < descriptorLength2) {
                            if (descriptor.charAt(p) != '>' || (s = this.exponent) <= 0) {
                                throw new IllegalArgumentException("Illegal character in rule descriptor");
                            }
                            this.exponent = (short) (s - 1);
                            p++;
                        }
                    }
                }
            }
            if (descriptor.equals("-x")) {
                setBaseValue(-1);
            } else if (descriptorLength2 == 3) {
                if (firstChar2 == '0' && lastChar2 == 'x') {
                    setBaseValue(-3);
                    this.decimalPoint = descriptor.charAt(1);
                } else if (firstChar2 == 'x' && lastChar2 == 'x') {
                    setBaseValue(-2);
                    this.decimalPoint = descriptor.charAt(1);
                } else if (firstChar2 == 'x' && lastChar2 == '0') {
                    setBaseValue(-4);
                    this.decimalPoint = descriptor.charAt(1);
                } else if (descriptor.equals("NaN")) {
                    setBaseValue(-6);
                } else if (descriptor.equals("Inf")) {
                    setBaseValue(-5);
                }
            }
        }
        if (description2.length() <= 0 || description2.charAt(0) != '\'') {
            return description2;
        }
        return description2.substring(1);
    }

    private void extractSubstitutions(NFRuleSet owner, String ruleText2, NFRule predecessor) {
        PluralRules.PluralType pluralType;
        this.ruleText = ruleText2;
        NFSubstitution extractSubstitution = extractSubstitution(owner, predecessor);
        this.sub1 = extractSubstitution;
        if (extractSubstitution == null) {
            this.sub2 = null;
        } else {
            this.sub2 = extractSubstitution(owner, predecessor);
        }
        String ruleText3 = this.ruleText;
        int pluralRuleStart = ruleText3.indexOf("$(");
        int pluralRuleEnd = pluralRuleStart >= 0 ? ruleText3.indexOf(")$", pluralRuleStart) : -1;
        if (pluralRuleEnd >= 0) {
            int endType = ruleText3.indexOf(44, pluralRuleStart);
            if (endType >= 0) {
                String type = this.ruleText.substring(pluralRuleStart + 2, endType);
                if ("cardinal".equals(type)) {
                    pluralType = PluralRules.PluralType.CARDINAL;
                } else if ("ordinal".equals(type)) {
                    pluralType = PluralRules.PluralType.ORDINAL;
                } else {
                    throw new IllegalArgumentException(type + " is an unknown type");
                }
                this.rulePatternFormat = this.formatter.createPluralFormat(pluralType, ruleText3.substring(endType + 1, pluralRuleEnd));
                return;
            }
            throw new IllegalArgumentException("Rule \"" + ruleText3 + "\" does not have a defined type");
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
            if (c != '<' || subEnd2 == -1 || subEnd2 >= this.ruleText.length() - 1 || this.ruleText.charAt(subEnd2 + 1) != c) {
                subEnd = subEnd2;
            } else {
                subEnd = subEnd2 + 1;
            }
        }
        if (subEnd == -1) {
            return null;
        }
        NFSubstitution result = NFSubstitution.makeSubstitution(subStart, this, predecessor, owner, this.formatter, this.ruleText.substring(subStart, subEnd + 1));
        this.ruleText = this.ruleText.substring(0, subStart) + this.ruleText.substring(subEnd + 1);
        return result;
    }

    /* access modifiers changed from: package-private */
    public final void setBaseValue(long newBaseValue) {
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
        this.exponent = 0;
    }

    private short expectedExponent() {
        if (this.radix == 0) {
            return 0;
        }
        long j = this.baseValue;
        if (j < 1) {
            return 0;
        }
        short tempResult = (short) ((int) (Math.log((double) j) / Math.log((double) this.radix)));
        if (power((long) this.radix, (short) (tempResult + 1)) <= this.baseValue) {
            return (short) (tempResult + 1);
        }
        return tempResult;
    }

    private static int indexOfAnyRulePrefix(String ruleText2) {
        int result = -1;
        if (ruleText2.length() > 0) {
            for (String string : RULE_PREFIXES) {
                int pos = ruleText2.indexOf(string);
                if (pos != -1 && (result == -1 || pos < result)) {
                    result = pos;
                }
            }
        }
        return result;
    }

    public boolean equals(Object that) {
        if (!(that instanceof NFRule)) {
            return false;
        }
        NFRule that2 = (NFRule) that;
        if (this.baseValue != that2.baseValue || this.radix != that2.radix || this.exponent != that2.exponent || !this.ruleText.equals(that2.ruleText) || !Objects.equals(this.sub1, that2.sub1) || !Objects.equals(this.sub2, that2.sub2)) {
            return false;
        }
        return true;
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
        } else {
            char c = '.';
            if (j == -2) {
                StringBuilder append = result.append('x');
                char c2 = this.decimalPoint;
                if (c2 != 0) {
                    c = c2;
                }
                append.append(c).append("x: ");
            } else if (j == -3) {
                StringBuilder append2 = result.append('0');
                char c3 = this.decimalPoint;
                if (c3 != 0) {
                    c = c3;
                }
                append2.append(c).append("x: ");
            } else if (j == -4) {
                StringBuilder append3 = result.append('x');
                char c4 = this.decimalPoint;
                if (c4 != 0) {
                    c = c4;
                }
                append3.append(c).append("0: ");
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
                    result.append('>');
                }
                result.append(PluralRules.KEYWORD_RULE_SEPARATOR);
            }
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
        return power((long) this.radix, this.exponent);
    }

    public void doFormat(long number, StringBuilder toInsertInto, int pos, int recursionCount) {
        int lengthOffset;
        int pluralRuleStart;
        StringBuilder sb = toInsertInto;
        int i = pos;
        int pluralRuleStart2 = this.ruleText.length();
        int i2 = 0;
        if (this.rulePatternFormat == null) {
            toInsertInto.insert(i, this.ruleText);
            pluralRuleStart = pluralRuleStart2;
            lengthOffset = 0;
        } else {
            int pluralRuleStart3 = this.ruleText.indexOf("$(");
            int pluralRuleEnd = this.ruleText.indexOf(")$", pluralRuleStart3);
            int initialLength = toInsertInto.length();
            if (pluralRuleEnd < this.ruleText.length() - 1) {
                toInsertInto.insert(i, this.ruleText.substring(pluralRuleEnd + 2));
            }
            toInsertInto.insert(i, this.rulePatternFormat.format((double) (number / power((long) this.radix, this.exponent))));
            if (pluralRuleStart3 > 0) {
                toInsertInto.insert(i, this.ruleText.substring(0, pluralRuleStart3));
            }
            pluralRuleStart = pluralRuleStart3;
            lengthOffset = this.ruleText.length() - (toInsertInto.length() - initialLength);
        }
        NFSubstitution nFSubstitution = this.sub2;
        if (nFSubstitution != null) {
            nFSubstitution.doSubstitution(number, toInsertInto, i - (nFSubstitution.getPos() > pluralRuleStart ? lengthOffset : 0), recursionCount);
        }
        NFSubstitution nFSubstitution2 = this.sub1;
        if (nFSubstitution2 != null) {
            if (nFSubstitution2.getPos() > pluralRuleStart) {
                i2 = lengthOffset;
            }
            nFSubstitution2.doSubstitution(number, toInsertInto, i - i2, recursionCount);
        }
    }

    public void doFormat(double number, StringBuilder toInsertInto, int pos, int recursionCount) {
        int lengthOffset;
        int pluralRuleStart;
        double pluralVal;
        StringBuilder sb = toInsertInto;
        int i = pos;
        int pluralRuleStart2 = this.ruleText.length();
        int i2 = 0;
        if (this.rulePatternFormat == null) {
            sb.insert(i, this.ruleText);
            pluralRuleStart = pluralRuleStart2;
            lengthOffset = 0;
        } else {
            int pluralRuleStart3 = this.ruleText.indexOf("$(");
            int pluralRuleEnd = this.ruleText.indexOf(")$", pluralRuleStart3);
            int initialLength = toInsertInto.length();
            if (pluralRuleEnd < this.ruleText.length() - 1) {
                sb.insert(i, this.ruleText.substring(pluralRuleEnd + 2));
            }
            double pluralVal2 = number;
            if (0.0d > pluralVal2 || pluralVal2 >= 1.0d) {
                pluralVal = pluralVal2 / ((double) power((long) this.radix, this.exponent));
            } else {
                pluralVal = (double) Math.round(((double) power((long) this.radix, this.exponent)) * pluralVal2);
            }
            sb.insert(i, this.rulePatternFormat.format((double) ((long) pluralVal)));
            if (pluralRuleStart3 > 0) {
                sb.insert(i, this.ruleText.substring(0, pluralRuleStart3));
            }
            pluralRuleStart = pluralRuleStart3;
            lengthOffset = this.ruleText.length() - (toInsertInto.length() - initialLength);
        }
        NFSubstitution nFSubstitution = this.sub2;
        if (nFSubstitution != null) {
            nFSubstitution.doSubstitution(number, toInsertInto, i - (nFSubstitution.getPos() > pluralRuleStart ? lengthOffset : 0), recursionCount);
        }
        NFSubstitution nFSubstitution2 = this.sub1;
        if (nFSubstitution2 != null) {
            if (nFSubstitution2.getPos() > pluralRuleStart) {
                i2 = lengthOffset;
            }
            nFSubstitution2.doSubstitution(number, toInsertInto, i - i2, recursionCount);
        }
    }

    static long power(long base, short exponent2) {
        if (exponent2 < 0) {
            throw new IllegalArgumentException("Exponent can not be negative");
        } else if (base >= 0) {
            long result = 1;
            while (exponent2 > 0) {
                if ((exponent2 & 1) == 1) {
                    result *= base;
                }
                base *= base;
                exponent2 = (short) (exponent2 >> 1);
            }
            return result;
        } else {
            throw new IllegalArgumentException("Base can not be negative");
        }
    }

    public boolean shouldRollBack(long number) {
        NFSubstitution nFSubstitution;
        NFSubstitution nFSubstitution2 = this.sub1;
        if ((nFSubstitution2 == null || !nFSubstitution2.isModulusSubstitution()) && ((nFSubstitution = this.sub2) == null || !nFSubstitution.isModulusSubstitution())) {
            return false;
        }
        long divisor = power((long) this.radix, this.exponent);
        if (number % divisor != 0 || this.baseValue % divisor == 0) {
            return false;
        }
        return true;
    }

    public Number doParse(String text, ParsePosition parsePosition, boolean isFractionRule, double upperBound, int nonNumericalExecutedRuleMask) {
        int sub2Pos;
        int i;
        int start;
        double result;
        int highWaterMark;
        ParsePosition parsePosition2 = parsePosition;
        int i2 = 0;
        ParsePosition pp = new ParsePosition(0);
        NFSubstitution nFSubstitution = this.sub1;
        int sub1Pos = nFSubstitution != null ? nFSubstitution.getPos() : this.ruleText.length();
        NFSubstitution nFSubstitution2 = this.sub2;
        int sub2Pos2 = nFSubstitution2 != null ? nFSubstitution2.getPos() : this.ruleText.length();
        String workText = stripPrefix(text, this.ruleText.substring(0, sub1Pos), pp);
        int prefixLength = text.length() - workText.length();
        if (pp.getIndex() == 0 && sub1Pos != 0) {
            return ZERO;
        }
        long j = this.baseValue;
        if (j == -5) {
            parsePosition2.setIndex(pp.getIndex());
            return Double.valueOf(Double.POSITIVE_INFINITY);
        } else if (j == -6) {
            parsePosition2.setIndex(pp.getIndex());
            return Double.valueOf(Double.NaN);
        } else {
            double tempBaseValue = (double) Math.max(0, j);
            double result2 = 0.0d;
            int start2 = 0;
            int highWaterMark2 = 0;
            while (true) {
                pp.setIndex(i2);
                int highWaterMark3 = highWaterMark2;
                double tempBaseValue2 = tempBaseValue;
                String workText2 = workText;
                sub2Pos = sub2Pos2;
                int sub1Pos2 = sub1Pos;
                double partialResult = matchToDelimiter(workText, start2, tempBaseValue, this.ruleText.substring(sub1Pos, sub2Pos2), this.rulePatternFormat, pp, this.sub1, upperBound, nonNumericalExecutedRuleMask).doubleValue();
                if (pp.getIndex() != 0 || this.sub1 == null) {
                    int start3 = pp.getIndex();
                    String workText22 = workText2.substring(pp.getIndex());
                    ParsePosition pp2 = new ParsePosition(0);
                    i = 0;
                    double partialResult2 = matchToDelimiter(workText22, 0, partialResult, this.ruleText.substring(sub2Pos), this.rulePatternFormat, pp2, this.sub2, upperBound, nonNumericalExecutedRuleMask).doubleValue();
                    if (pp2.getIndex() != 0 || this.sub2 == null) {
                        highWaterMark = highWaterMark3;
                        if (prefixLength + pp.getIndex() + pp2.getIndex() > highWaterMark) {
                            highWaterMark2 = prefixLength + pp.getIndex() + pp2.getIndex();
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
                int sub1Pos3 = sub1Pos2;
                if (sub1Pos3 == sub2Pos || pp.getIndex() <= 0 || pp.getIndex() >= workText2.length() || pp.getIndex() == start) {
                    int i3 = sub2Pos;
                    parsePosition.setIndex(highWaterMark2);
                } else {
                    String str = text;
                    start2 = start;
                    sub1Pos = sub1Pos3;
                    sub2Pos2 = sub2Pos;
                    workText = workText2;
                    i2 = i;
                    tempBaseValue = tempBaseValue2;
                    ParsePosition parsePosition3 = parsePosition;
                }
            }
            int i32 = sub2Pos;
            parsePosition.setIndex(highWaterMark2);
            if (!isFractionRule || highWaterMark2 <= 0 || this.sub1 != null) {
                result = result2;
            } else {
                result = 1.0d / result2;
            }
            if (result == ((double) ((long) result))) {
                return Long.valueOf((long) result);
            }
            return new Double(result);
        }
    }

    private String stripPrefix(String text, String prefix, ParsePosition pp) {
        int pfl;
        if (prefix.length() == 0 || (pfl = prefixLength(text, prefix)) == 0) {
            return text;
        }
        pp.setIndex(pp.getIndex() + pfl);
        return text.substring(pfl);
    }

    private Number matchToDelimiter(String text, int startPos, double baseVal, String delimiter, PluralFormat pluralFormatDelimiter, ParsePosition pp, NFSubstitution sub, double upperBound, int nonNumericalExecutedRuleMask) {
        String str = text;
        String str2 = delimiter;
        PluralFormat pluralFormat = pluralFormatDelimiter;
        ParsePosition parsePosition = pp;
        if (!allIgnorable(str2)) {
            ParsePosition tempPP = new ParsePosition(0);
            int[] temp = findText(str, str2, pluralFormat, startPos);
            int dPos = temp[0];
            int dLen = temp[1];
            while (dPos >= 0) {
                String subText = str.substring(0, dPos);
                if (subText.length() > 0) {
                    Number tempResult = sub.doParse(subText, tempPP, baseVal, upperBound, this.formatter.lenientParseEnabled(), nonNumericalExecutedRuleMask);
                    if (tempPP.getIndex() == dPos) {
                        parsePosition.setIndex(dPos + dLen);
                        return tempResult;
                    }
                }
                tempPP.setIndex(0);
                int[] temp2 = findText(str, str2, pluralFormat, dPos + dLen);
                dPos = temp2[0];
                dLen = temp2[1];
            }
            parsePosition.setIndex(0);
            return ZERO;
        }
        int i = startPos;
        if (sub == null) {
            return Double.valueOf(baseVal);
        }
        ParsePosition tempPP2 = new ParsePosition(0);
        Number result = ZERO;
        Number tempResult2 = sub.doParse(text, tempPP2, baseVal, upperBound, this.formatter.lenientParseEnabled(), nonNumericalExecutedRuleMask);
        if (tempPP2.getIndex() == 0) {
            return result;
        }
        parsePosition.setIndex(tempPP2.getIndex());
        if (tempResult2 != null) {
            return tempResult2;
        }
        return result;
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
        String str2 = str;
        String str3 = key;
        PluralFormat pluralFormat = pluralFormatKey;
        int i = startingAt;
        RbnfLenientScanner scanner = this.formatter.getLenientScanner();
        if (pluralFormat != null) {
            FieldPosition position = new FieldPosition(0);
            position.setBeginIndex(i);
            pluralFormat.parseType(str2, scanner, position);
            int start = position.getBeginIndex();
            if (start >= 0) {
                int pluralRuleStart = this.ruleText.indexOf("$(");
                int matchLen = position.getEndIndex() - start;
                String prefix = this.ruleText.substring(0, pluralRuleStart);
                String suffix = this.ruleText.substring(this.ruleText.indexOf(")$", pluralRuleStart) + 2);
                if (str2.regionMatches(start - prefix.length(), prefix, 0, prefix.length()) && str2.regionMatches(start + matchLen, suffix, 0, suffix.length())) {
                    return new int[]{start - prefix.length(), prefix.length() + matchLen + suffix.length()};
                }
            }
            return new int[]{-1, 0};
        } else if (scanner != null) {
            return scanner.findText(str2, str3, i);
        } else {
            return new int[]{str2.indexOf(str3, i), key.length()};
        }
    }

    private boolean allIgnorable(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        RbnfLenientScanner scanner = this.formatter.getLenientScanner();
        if (scanner == null || !scanner.allIgnorable(str)) {
            return false;
        }
        return true;
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
