package com.ibm.icu.text;

import com.ibm.icu.impl.StaticUnicodeSets;
import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.text.NumberFormat;
import com.ibm.icu.util.ULocale;
import java.text.AttributedCharacterIterator;
import java.util.Map;

/* loaded from: classes.dex */
public final class ScientificNumberFormatter {
    private static final Style SUPER_SCRIPT = new SuperscriptStyle();
    private final DecimalFormat fmt;
    private final String preExponent;
    private final Style style;

    public static ScientificNumberFormatter getSuperscriptInstance(ULocale locale) {
        return getInstanceForLocale(locale, SUPER_SCRIPT);
    }

    public static ScientificNumberFormatter getSuperscriptInstance(DecimalFormat df) {
        return getInstance(df, SUPER_SCRIPT);
    }

    public static ScientificNumberFormatter getMarkupInstance(ULocale locale, String beginMarkup, String endMarkup) {
        return getInstanceForLocale(locale, new MarkupStyle(beginMarkup, endMarkup));
    }

    public static ScientificNumberFormatter getMarkupInstance(DecimalFormat df, String beginMarkup, String endMarkup) {
        return getInstance(df, new MarkupStyle(beginMarkup, endMarkup));
    }

    public String format(Object number) {
        String format;
        synchronized (this.fmt) {
            format = this.style.format(this.fmt.formatToCharacterIterator(number), this.preExponent);
        }
        return format;
    }

    /* loaded from: classes.dex */
    private static abstract class Style {
        abstract String format(AttributedCharacterIterator attributedCharacterIterator, String str);

        private Style() {
        }

        static void append(AttributedCharacterIterator iterator, int start, int limit, StringBuilder result) {
            int oldIndex = iterator.getIndex();
            iterator.setIndex(start);
            for (int i = start; i < limit; i++) {
                result.append(iterator.current());
                iterator.next();
            }
            iterator.setIndex(oldIndex);
        }
    }

    /* loaded from: classes.dex */
    private static class MarkupStyle extends Style {
        private final String beginMarkup;
        private final String endMarkup;

        MarkupStyle(String beginMarkup, String endMarkup) {
            super();
            this.beginMarkup = beginMarkup;
            this.endMarkup = endMarkup;
        }

        @Override // com.ibm.icu.text.ScientificNumberFormatter.Style
        String format(AttributedCharacterIterator iterator, String preExponent) {
            int copyFromOffset = 0;
            StringBuilder result = new StringBuilder();
            iterator.first();
            while (iterator.current() != '\uffff') {
                Map<AttributedCharacterIterator.Attribute, Object> attributeSet = iterator.getAttributes();
                if (attributeSet.containsKey(NumberFormat.Field.EXPONENT_SYMBOL)) {
                    append(iterator, copyFromOffset, iterator.getRunStart(NumberFormat.Field.EXPONENT_SYMBOL), result);
                    copyFromOffset = iterator.getRunLimit(NumberFormat.Field.EXPONENT_SYMBOL);
                    iterator.setIndex(copyFromOffset);
                    result.append(preExponent);
                    result.append(this.beginMarkup);
                } else if (attributeSet.containsKey(NumberFormat.Field.EXPONENT)) {
                    int limit = iterator.getRunLimit(NumberFormat.Field.EXPONENT);
                    append(iterator, copyFromOffset, limit, result);
                    copyFromOffset = limit;
                    iterator.setIndex(copyFromOffset);
                    result.append(this.endMarkup);
                } else {
                    iterator.next();
                }
            }
            append(iterator, copyFromOffset, iterator.getEndIndex(), result);
            return result.toString();
        }
    }

    /* loaded from: classes.dex */
    private static class SuperscriptStyle extends Style {
        private static final char[] SUPERSCRIPT_DIGITS = {'\u2070', '\u00b9', '\u00b2', '\u00b3', '\u2074', '\u2075', '\u2076', '\u2077', '\u2078', '\u2079'};
        private static final char SUPERSCRIPT_MINUS_SIGN = '\u207b';
        private static final char SUPERSCRIPT_PLUS_SIGN = '\u207a';

        private SuperscriptStyle() {
            super();
        }

        @Override // com.ibm.icu.text.ScientificNumberFormatter.Style
        String format(AttributedCharacterIterator iterator, String preExponent) {
            int copyFromOffset = 0;
            StringBuilder result = new StringBuilder();
            iterator.first();
            while (iterator.current() != '\uffff') {
                Map<AttributedCharacterIterator.Attribute, Object> attributeSet = iterator.getAttributes();
                if (attributeSet.containsKey(NumberFormat.Field.EXPONENT_SYMBOL)) {
                    append(iterator, copyFromOffset, iterator.getRunStart(NumberFormat.Field.EXPONENT_SYMBOL), result);
                    copyFromOffset = iterator.getRunLimit(NumberFormat.Field.EXPONENT_SYMBOL);
                    iterator.setIndex(copyFromOffset);
                    result.append(preExponent);
                } else if (attributeSet.containsKey(NumberFormat.Field.EXPONENT_SIGN)) {
                    int start = iterator.getRunStart(NumberFormat.Field.EXPONENT_SIGN);
                    int limit = iterator.getRunLimit(NumberFormat.Field.EXPONENT_SIGN);
                    int aChar = char32AtAndAdvance(iterator);
                    if (StaticUnicodeSets.get(StaticUnicodeSets.Key.MINUS_SIGN).contains(aChar)) {
                        append(iterator, copyFromOffset, start, result);
                        result.append(SUPERSCRIPT_MINUS_SIGN);
                    } else if (StaticUnicodeSets.get(StaticUnicodeSets.Key.PLUS_SIGN).contains(aChar)) {
                        append(iterator, copyFromOffset, start, result);
                        result.append(SUPERSCRIPT_PLUS_SIGN);
                    } else {
                        throw new IllegalArgumentException();
                    }
                    copyFromOffset = limit;
                    iterator.setIndex(copyFromOffset);
                } else if (attributeSet.containsKey(NumberFormat.Field.EXPONENT)) {
                    int start2 = iterator.getRunStart(NumberFormat.Field.EXPONENT);
                    int limit2 = iterator.getRunLimit(NumberFormat.Field.EXPONENT);
                    append(iterator, copyFromOffset, start2, result);
                    copyAsSuperscript(iterator, start2, limit2, result);
                    copyFromOffset = limit2;
                    iterator.setIndex(copyFromOffset);
                } else {
                    iterator.next();
                }
            }
            append(iterator, copyFromOffset, iterator.getEndIndex(), result);
            return result.toString();
        }

        private static void copyAsSuperscript(AttributedCharacterIterator iterator, int start, int limit, StringBuilder result) {
            int oldIndex = iterator.getIndex();
            iterator.setIndex(start);
            while (iterator.getIndex() < limit) {
                int aChar = char32AtAndAdvance(iterator);
                int digit = UCharacter.digit(aChar);
                if (digit < 0) {
                    throw new IllegalArgumentException();
                }
                result.append(SUPERSCRIPT_DIGITS[digit]);
            }
            iterator.setIndex(oldIndex);
        }

        private static int char32AtAndAdvance(AttributedCharacterIterator iterator) {
            char c1 = iterator.current();
            char c2 = iterator.next();
            if (UCharacter.isHighSurrogate(c1) && UCharacter.isLowSurrogate(c2)) {
                iterator.next();
                return UCharacter.toCodePoint(c1, c2);
            }
            return c1;
        }
    }

    private static String getPreExponent(DecimalFormatSymbols dfs) {
        StringBuilder preExponent = new StringBuilder();
        preExponent.append(dfs.getExponentMultiplicationSign());
        char[] digits = dfs.getDigits();
        preExponent.append(digits[1]).append(digits[0]);
        return preExponent.toString();
    }

    private static ScientificNumberFormatter getInstance(DecimalFormat decimalFormat, Style style) {
        DecimalFormatSymbols dfs = decimalFormat.getDecimalFormatSymbols();
        return new ScientificNumberFormatter((DecimalFormat) decimalFormat.clone(), getPreExponent(dfs), style);
    }

    private static ScientificNumberFormatter getInstanceForLocale(ULocale locale, Style style) {
        DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getScientificInstance(locale);
        return new ScientificNumberFormatter(decimalFormat, getPreExponent(decimalFormat.getDecimalFormatSymbols()), style);
    }

    private ScientificNumberFormatter(DecimalFormat decimalFormat, String preExponent, Style style) {
        this.fmt = decimalFormat;
        this.preExponent = preExponent;
        this.style = style;
    }
}
