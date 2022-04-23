package com.ibm.icu.text;

import com.ibm.icu.number.FormattedNumber;
import com.ibm.icu.number.LocalizedNumberFormatter;
import com.ibm.icu.text.MessagePattern;
import com.ibm.icu.text.PluralRules;
import com.ibm.icu.util.ULocale;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class PluralFormat extends UFormat {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long serialVersionUID = 1;
    private transient MessagePattern msgPattern;
    private NumberFormat numberFormat;
    private transient double offset;
    private Map<String, String> parsedValues;
    private String pattern;
    /* access modifiers changed from: private */
    public PluralRules pluralRules;
    private transient PluralSelectorAdapter pluralRulesWrapper;
    private ULocale ulocale;

    interface PluralSelector {
        String select(Object obj, double d);
    }

    public PluralFormat() {
        this.ulocale = null;
        this.pluralRules = null;
        this.pattern = null;
        this.parsedValues = null;
        this.numberFormat = null;
        this.offset = 0.0d;
        this.pluralRulesWrapper = new PluralSelectorAdapter();
        init((PluralRules) null, PluralRules.PluralType.CARDINAL, ULocale.getDefault(ULocale.Category.FORMAT), (NumberFormat) null);
    }

    public PluralFormat(ULocale ulocale2) {
        this.ulocale = null;
        this.pluralRules = null;
        this.pattern = null;
        this.parsedValues = null;
        this.numberFormat = null;
        this.offset = 0.0d;
        this.pluralRulesWrapper = new PluralSelectorAdapter();
        init((PluralRules) null, PluralRules.PluralType.CARDINAL, ulocale2, (NumberFormat) null);
    }

    public PluralFormat(Locale locale) {
        this(ULocale.forLocale(locale));
    }

    public PluralFormat(PluralRules rules) {
        this.ulocale = null;
        this.pluralRules = null;
        this.pattern = null;
        this.parsedValues = null;
        this.numberFormat = null;
        this.offset = 0.0d;
        this.pluralRulesWrapper = new PluralSelectorAdapter();
        init(rules, PluralRules.PluralType.CARDINAL, ULocale.getDefault(ULocale.Category.FORMAT), (NumberFormat) null);
    }

    public PluralFormat(ULocale ulocale2, PluralRules rules) {
        this.ulocale = null;
        this.pluralRules = null;
        this.pattern = null;
        this.parsedValues = null;
        this.numberFormat = null;
        this.offset = 0.0d;
        this.pluralRulesWrapper = new PluralSelectorAdapter();
        init(rules, PluralRules.PluralType.CARDINAL, ulocale2, (NumberFormat) null);
    }

    public PluralFormat(Locale locale, PluralRules rules) {
        this(ULocale.forLocale(locale), rules);
    }

    public PluralFormat(ULocale ulocale2, PluralRules.PluralType type) {
        this.ulocale = null;
        this.pluralRules = null;
        this.pattern = null;
        this.parsedValues = null;
        this.numberFormat = null;
        this.offset = 0.0d;
        this.pluralRulesWrapper = new PluralSelectorAdapter();
        init((PluralRules) null, type, ulocale2, (NumberFormat) null);
    }

    public PluralFormat(Locale locale, PluralRules.PluralType type) {
        this(ULocale.forLocale(locale), type);
    }

    public PluralFormat(String pattern2) {
        this.ulocale = null;
        this.pluralRules = null;
        this.pattern = null;
        this.parsedValues = null;
        this.numberFormat = null;
        this.offset = 0.0d;
        this.pluralRulesWrapper = new PluralSelectorAdapter();
        init((PluralRules) null, PluralRules.PluralType.CARDINAL, ULocale.getDefault(ULocale.Category.FORMAT), (NumberFormat) null);
        applyPattern(pattern2);
    }

    public PluralFormat(ULocale ulocale2, String pattern2) {
        this.ulocale = null;
        this.pluralRules = null;
        this.pattern = null;
        this.parsedValues = null;
        this.numberFormat = null;
        this.offset = 0.0d;
        this.pluralRulesWrapper = new PluralSelectorAdapter();
        init((PluralRules) null, PluralRules.PluralType.CARDINAL, ulocale2, (NumberFormat) null);
        applyPattern(pattern2);
    }

    public PluralFormat(PluralRules rules, String pattern2) {
        this.ulocale = null;
        this.pluralRules = null;
        this.pattern = null;
        this.parsedValues = null;
        this.numberFormat = null;
        this.offset = 0.0d;
        this.pluralRulesWrapper = new PluralSelectorAdapter();
        init(rules, PluralRules.PluralType.CARDINAL, ULocale.getDefault(ULocale.Category.FORMAT), (NumberFormat) null);
        applyPattern(pattern2);
    }

    public PluralFormat(ULocale ulocale2, PluralRules rules, String pattern2) {
        this.ulocale = null;
        this.pluralRules = null;
        this.pattern = null;
        this.parsedValues = null;
        this.numberFormat = null;
        this.offset = 0.0d;
        this.pluralRulesWrapper = new PluralSelectorAdapter();
        init(rules, PluralRules.PluralType.CARDINAL, ulocale2, (NumberFormat) null);
        applyPattern(pattern2);
    }

    public PluralFormat(ULocale ulocale2, PluralRules.PluralType type, String pattern2) {
        this.ulocale = null;
        this.pluralRules = null;
        this.pattern = null;
        this.parsedValues = null;
        this.numberFormat = null;
        this.offset = 0.0d;
        this.pluralRulesWrapper = new PluralSelectorAdapter();
        init((PluralRules) null, type, ulocale2, (NumberFormat) null);
        applyPattern(pattern2);
    }

    PluralFormat(ULocale ulocale2, PluralRules.PluralType type, String pattern2, NumberFormat numberFormat2) {
        this.ulocale = null;
        this.pluralRules = null;
        this.pattern = null;
        this.parsedValues = null;
        this.numberFormat = null;
        this.offset = 0.0d;
        this.pluralRulesWrapper = new PluralSelectorAdapter();
        init((PluralRules) null, type, ulocale2, numberFormat2);
        applyPattern(pattern2);
    }

    private void init(PluralRules rules, PluralRules.PluralType type, ULocale locale, NumberFormat numberFormat2) {
        this.ulocale = locale;
        this.pluralRules = rules == null ? PluralRules.forLocale(locale, type) : rules;
        resetPattern();
        this.numberFormat = numberFormat2 == null ? NumberFormat.getInstance(this.ulocale) : numberFormat2;
    }

    private void resetPattern() {
        this.pattern = null;
        MessagePattern messagePattern = this.msgPattern;
        if (messagePattern != null) {
            messagePattern.clear();
        }
        this.offset = 0.0d;
    }

    public void applyPattern(String pattern2) {
        this.pattern = pattern2;
        if (this.msgPattern == null) {
            this.msgPattern = new MessagePattern();
        }
        try {
            this.msgPattern.parsePluralStyle(pattern2);
            this.offset = this.msgPattern.getPluralOffset(0);
        } catch (RuntimeException e) {
            resetPattern();
            throw e;
        }
    }

    public String toPattern() {
        return this.pattern;
    }

    static int findSubMessage(MessagePattern pattern2, int partIndex, PluralSelector selector, Object context, double number) {
        int partIndex2;
        double offset2;
        MessagePattern messagePattern = pattern2;
        int count = pattern2.countParts();
        MessagePattern.Part part = pattern2.getPart(partIndex);
        if (part.getType().hasNumericValue()) {
            offset2 = pattern2.getNumericValue(part);
            partIndex2 = partIndex + 1;
        } else {
            offset2 = 0.0d;
            partIndex2 = partIndex;
        }
        String keyword = null;
        boolean haveKeywordMatch = false;
        int msgStart = 0;
        while (true) {
            int partIndex3 = partIndex2 + 1;
            MessagePattern.Part part2 = pattern2.getPart(partIndex2);
            MessagePattern.Part.Type type = part2.getType();
            if (type == MessagePattern.Part.Type.ARG_LIMIT) {
                PluralSelector pluralSelector = selector;
                Object obj = context;
                break;
            } else if (type == MessagePattern.Part.Type.ARG_SELECTOR) {
                if (pattern2.getPartType(partIndex3).hasNumericValue()) {
                    int partIndex4 = partIndex3 + 1;
                    if (number == pattern2.getNumericValue(pattern2.getPart(partIndex3))) {
                        return partIndex4;
                    }
                    PluralSelector pluralSelector2 = selector;
                    Object obj2 = context;
                    partIndex3 = partIndex4;
                } else if (haveKeywordMatch) {
                    PluralSelector pluralSelector3 = selector;
                    Object obj3 = context;
                } else if (!pattern2.partSubstringMatches(part2, PluralRules.KEYWORD_OTHER)) {
                    if (keyword == null) {
                        keyword = selector.select(context, number - offset2);
                        if (msgStart != 0 && keyword.equals(PluralRules.KEYWORD_OTHER)) {
                            haveKeywordMatch = true;
                        }
                    } else {
                        PluralSelector pluralSelector4 = selector;
                        Object obj4 = context;
                    }
                    if (!haveKeywordMatch && pattern2.partSubstringMatches(part2, keyword)) {
                        msgStart = partIndex3;
                        haveKeywordMatch = true;
                    }
                } else if (msgStart == 0) {
                    msgStart = partIndex3;
                    if (keyword == null || !keyword.equals(PluralRules.KEYWORD_OTHER)) {
                        PluralSelector pluralSelector5 = selector;
                        Object obj5 = context;
                    } else {
                        haveKeywordMatch = true;
                        PluralSelector pluralSelector6 = selector;
                        Object obj6 = context;
                    }
                } else {
                    PluralSelector pluralSelector7 = selector;
                    Object obj7 = context;
                }
                partIndex2 = pattern2.getLimitPartIndex(partIndex3) + 1;
                if (partIndex2 >= count) {
                    int i = partIndex2;
                    break;
                }
            } else {
                PluralSelector pluralSelector8 = selector;
                Object obj8 = context;
                throw new AssertionError();
            }
        }
        return msgStart;
    }

    private final class PluralSelectorAdapter implements PluralSelector {
        private PluralSelectorAdapter() {
        }

        public String select(Object context, double number) {
            return PluralFormat.this.pluralRules.select((PluralRules.IFixedDecimal) context);
        }
    }

    public final String format(double number) {
        return format(Double.valueOf(number), number);
    }

    public StringBuffer format(Object number, StringBuffer toAppendTo, FieldPosition pos) {
        if (number instanceof Number) {
            Number numberObject = (Number) number;
            toAppendTo.append(format(numberObject, numberObject.doubleValue()));
            return toAppendTo;
        }
        throw new IllegalArgumentException("'" + number + "' is not a Number");
    }

    private String format(Number numberObject, double number) {
        String numberString;
        PluralRules.IFixedDecimal dec;
        int index;
        FormattedNumber result;
        MessagePattern messagePattern = this.msgPattern;
        if (messagePattern == null || messagePattern.countParts() == 0) {
            return this.numberFormat.format(numberObject);
        }
        double d = this.offset;
        double numberMinusOffset = number - d;
        NumberFormat numberFormat2 = this.numberFormat;
        if (numberFormat2 instanceof DecimalFormat) {
            LocalizedNumberFormatter f = ((DecimalFormat) numberFormat2).toNumberFormatter();
            if (this.offset == 0.0d) {
                result = f.format(numberObject);
            } else {
                result = f.format(numberMinusOffset);
            }
            numberString = result.toString();
            dec = result.getFixedDecimal();
        } else {
            if (d == 0.0d) {
                numberString = numberFormat2.format(numberObject);
            } else {
                numberString = numberFormat2.format(numberMinusOffset);
            }
            dec = new PluralRules.FixedDecimal(numberMinusOffset);
        }
        int partIndex = findSubMessage(this.msgPattern, 0, this.pluralRulesWrapper, dec, number);
        StringBuilder result2 = null;
        int prevIndex = this.msgPattern.getPart(partIndex).getLimit();
        while (true) {
            partIndex++;
            MessagePattern.Part part = this.msgPattern.getPart(partIndex);
            MessagePattern.Part.Type type = part.getType();
            index = part.getIndex();
            if (type == MessagePattern.Part.Type.MSG_LIMIT) {
                break;
            } else if (type == MessagePattern.Part.Type.REPLACE_NUMBER || (type == MessagePattern.Part.Type.SKIP_SYNTAX && this.msgPattern.jdkAposMode())) {
                if (result2 == null) {
                    result2 = new StringBuilder();
                }
                result2.append(this.pattern, prevIndex, index);
                if (type == MessagePattern.Part.Type.REPLACE_NUMBER) {
                    result2.append(numberString);
                }
                prevIndex = part.getLimit();
            } else if (type == MessagePattern.Part.Type.ARG_START) {
                if (result2 == null) {
                    result2 = new StringBuilder();
                }
                result2.append(this.pattern, prevIndex, index);
                int prevIndex2 = index;
                partIndex = this.msgPattern.getLimitPartIndex(partIndex);
                int index2 = this.msgPattern.getPart(partIndex).getLimit();
                MessagePattern.appendReducedApostrophes(this.pattern, prevIndex2, index2, result2);
                prevIndex = index2;
            }
        }
        if (result2 == null) {
            return this.pattern.substring(prevIndex, index);
        }
        return result2.append(this.pattern, prevIndex, index).toString();
    }

    public Number parse(String text, ParsePosition parsePosition) {
        throw new UnsupportedOperationException();
    }

    public Object parseObject(String source, ParsePosition pos) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public String parseType(String source, RbnfLenientScanner scanner, FieldPosition pos) {
        String str;
        int i;
        int currMatchIndex;
        PluralFormat pluralFormat = this;
        String str2 = source;
        RbnfLenientScanner rbnfLenientScanner = scanner;
        FieldPosition fieldPosition = pos;
        MessagePattern messagePattern = pluralFormat.msgPattern;
        if (messagePattern == null) {
            i = -1;
            str = null;
        } else if (messagePattern.countParts() == 0) {
            i = -1;
            str = null;
        } else {
            int partIndex = 0;
            int count = pluralFormat.msgPattern.countParts();
            int startingAt = pos.getBeginIndex();
            if (startingAt < 0) {
                startingAt = 0;
            }
            String keyword = null;
            String matchedWord = null;
            int matchedIndex = -1;
            while (partIndex < count) {
                int partIndex2 = partIndex + 1;
                if (pluralFormat.msgPattern.getPart(partIndex).getType() != MessagePattern.Part.Type.ARG_SELECTOR) {
                    partIndex = partIndex2;
                } else {
                    int partIndex3 = partIndex2 + 1;
                    MessagePattern.Part partStart = pluralFormat.msgPattern.getPart(partIndex2);
                    if (partStart.getType() != MessagePattern.Part.Type.MSG_START) {
                        partIndex = partIndex3;
                    } else {
                        int partIndex4 = partIndex3 + 1;
                        MessagePattern.Part partLimit = pluralFormat.msgPattern.getPart(partIndex3);
                        if (partLimit.getType() != MessagePattern.Part.Type.MSG_LIMIT) {
                            partIndex = partIndex4;
                        } else {
                            String currArg = pluralFormat.pattern.substring(partStart.getLimit(), partLimit.getIndex());
                            if (rbnfLenientScanner != null) {
                                currMatchIndex = rbnfLenientScanner.findText(str2, currArg, startingAt)[0];
                            } else {
                                currMatchIndex = str2.indexOf(currArg, startingAt);
                            }
                            if (currMatchIndex >= 0 && currMatchIndex >= matchedIndex && (matchedWord == null || currArg.length() > matchedWord.length())) {
                                matchedWord = currArg;
                                keyword = pluralFormat.pattern.substring(partStart.getLimit(), partLimit.getIndex());
                                matchedIndex = currMatchIndex;
                            }
                            pluralFormat = this;
                            str2 = source;
                            partIndex = partIndex4;
                        }
                    }
                }
            }
            if (keyword != null) {
                fieldPosition.setBeginIndex(matchedIndex);
                fieldPosition.setEndIndex(matchedWord.length() + matchedIndex);
                return keyword;
            }
            fieldPosition.setBeginIndex(-1);
            fieldPosition.setEndIndex(-1);
            return null;
        }
        fieldPosition.setBeginIndex(i);
        fieldPosition.setEndIndex(i);
        return str;
    }

    @Deprecated
    public void setLocale(ULocale ulocale2) {
        if (ulocale2 == null) {
            ulocale2 = ULocale.getDefault(ULocale.Category.FORMAT);
        }
        init((PluralRules) null, PluralRules.PluralType.CARDINAL, ulocale2, (NumberFormat) null);
    }

    public void setNumberFormat(NumberFormat format) {
        this.numberFormat = format;
    }

    public boolean equals(Object rhs) {
        if (this == rhs) {
            return true;
        }
        if (rhs == null || getClass() != rhs.getClass()) {
            return false;
        }
        PluralFormat pf = (PluralFormat) rhs;
        if (!Objects.equals(this.ulocale, pf.ulocale) || !Objects.equals(this.pluralRules, pf.pluralRules) || !Objects.equals(this.msgPattern, pf.msgPattern) || !Objects.equals(this.numberFormat, pf.numberFormat)) {
            return false;
        }
        return true;
    }

    public boolean equals(PluralFormat rhs) {
        return equals((Object) rhs);
    }

    public int hashCode() {
        return this.pluralRules.hashCode() ^ this.parsedValues.hashCode();
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("locale=" + this.ulocale);
        buf.append(", rules='" + this.pluralRules + "'");
        buf.append(", pattern='" + this.pattern + "'");
        buf.append(", format='" + this.numberFormat + "'");
        return buf.toString();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.pluralRulesWrapper = new PluralSelectorAdapter();
        this.parsedValues = null;
        String str = this.pattern;
        if (str != null) {
            applyPattern(str);
        }
    }
}
