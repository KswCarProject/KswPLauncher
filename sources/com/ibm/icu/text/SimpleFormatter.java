package com.ibm.icu.text;

import com.ibm.icu.impl.SimpleFormatterImpl;

/* loaded from: classes.dex */
public final class SimpleFormatter {
    private final String compiledPattern;

    private SimpleFormatter(String compiledPattern) {
        this.compiledPattern = compiledPattern;
    }

    public static SimpleFormatter compile(CharSequence pattern) {
        return compileMinMaxArguments(pattern, 0, Integer.MAX_VALUE);
    }

    public static SimpleFormatter compileMinMaxArguments(CharSequence pattern, int min, int max) {
        StringBuilder sb = new StringBuilder();
        String compiledPattern = SimpleFormatterImpl.compileToStringMinMaxArguments(pattern, sb, min, max);
        return new SimpleFormatter(compiledPattern);
    }

    public int getArgumentLimit() {
        return SimpleFormatterImpl.getArgumentLimit(this.compiledPattern);
    }

    public String format(CharSequence... values) {
        return SimpleFormatterImpl.formatCompiledPattern(this.compiledPattern, values);
    }

    public StringBuilder formatAndAppend(StringBuilder appendTo, int[] offsets, CharSequence... values) {
        return SimpleFormatterImpl.formatAndAppend(this.compiledPattern, appendTo, offsets, values);
    }

    public StringBuilder formatAndReplace(StringBuilder result, int[] offsets, CharSequence... values) {
        return SimpleFormatterImpl.formatAndReplace(this.compiledPattern, result, offsets, values);
    }

    public String toString() {
        String[] values = new String[getArgumentLimit()];
        for (int i = 0; i < values.length; i++) {
            values[i] = "{" + i + '}';
        }
        return formatAndAppend(new StringBuilder(), null, values).toString();
    }

    public String getTextWithNoArguments() {
        return SimpleFormatterImpl.getTextWithNoArguments(this.compiledPattern);
    }
}
