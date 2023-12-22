package com.ibm.icu.text;

import com.ibm.icu.impl.PluralRulesLoader;
import com.ibm.icu.util.Output;
import com.ibm.icu.util.ULocale;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;
import kotlin.time.DurationKt;

/* loaded from: classes.dex */
public class PluralRules implements Serializable {
    static final UnicodeSet ALLOWED_ID = new UnicodeSet("[a-z]").m87freeze();
    static final Pattern AND_SEPARATED;
    static final Pattern AT_SEPARATED;
    @Deprecated
    public static final String CATEGORY_SEPARATOR = ";  ";
    static final Pattern COMMA_SEPARATED;
    public static final PluralRules DEFAULT;
    private static final Rule DEFAULT_RULE;
    static final Pattern DOTDOT_SEPARATED;
    public static final String KEYWORD_FEW = "few";
    public static final String KEYWORD_MANY = "many";
    public static final String KEYWORD_ONE = "one";
    public static final String KEYWORD_OTHER = "other";
    @Deprecated
    public static final String KEYWORD_RULE_SEPARATOR = ": ";
    public static final String KEYWORD_TWO = "two";
    public static final String KEYWORD_ZERO = "zero";
    private static final Constraint NO_CONSTRAINT;
    public static final double NO_UNIQUE_VALUE = -0.00123456777d;
    static final Pattern OR_SEPARATED;
    static final Pattern SEMI_SEPARATED;
    static final Pattern TILDE_SEPARATED;
    private static final long serialVersionUID = 1;
    private final transient Set<String> keywords;
    private final RuleList rules;

    /* loaded from: classes.dex */
    private interface Constraint extends Serializable {
        boolean isFulfilled(IFixedDecimal iFixedDecimal);

        boolean isLimited(SampleType sampleType);
    }

    @Deprecated
    /* loaded from: classes.dex */
    public interface IFixedDecimal {
        @Deprecated
        double getPluralOperand(Operand operand);

        @Deprecated
        boolean isInfinite();

        @Deprecated
        boolean isNaN();
    }

    /* loaded from: classes.dex */
    public enum KeywordStatus {
        INVALID,
        SUPPRESSED,
        UNIQUE,
        BOUNDED,
        UNBOUNDED
    }

    @Deprecated
    /* loaded from: classes.dex */
    public enum Operand {
        n,
        i,
        f,
        t,
        v,
        w,
        j
    }

    /* loaded from: classes.dex */
    public enum PluralType {
        CARDINAL,
        ORDINAL
    }

    @Deprecated
    /* loaded from: classes.dex */
    public enum SampleType {
        INTEGER,
        DECIMAL
    }

    static {
        Constraint constraint = new Constraint() { // from class: com.ibm.icu.text.PluralRules.1
            private static final long serialVersionUID = 9163464945387899416L;

            @Override // com.ibm.icu.text.PluralRules.Constraint
            public boolean isFulfilled(IFixedDecimal n) {
                return true;
            }

            @Override // com.ibm.icu.text.PluralRules.Constraint
            public boolean isLimited(SampleType sampleType) {
                return false;
            }

            public String toString() {
                return "";
            }
        };
        NO_CONSTRAINT = constraint;
        Rule rule = new Rule(KEYWORD_OTHER, constraint, null, null);
        DEFAULT_RULE = rule;
        DEFAULT = new PluralRules(new RuleList().addRule(rule));
        AT_SEPARATED = Pattern.compile("\\s*\\Q\\E@\\s*");
        OR_SEPARATED = Pattern.compile("\\s*or\\s*");
        AND_SEPARATED = Pattern.compile("\\s*and\\s*");
        COMMA_SEPARATED = Pattern.compile("\\s*,\\s*");
        DOTDOT_SEPARATED = Pattern.compile("\\s*\\Q..\\E\\s*");
        TILDE_SEPARATED = Pattern.compile("\\s*~\\s*");
        SEMI_SEPARATED = Pattern.compile("\\s*;\\s*");
    }

    @Deprecated
    /* loaded from: classes.dex */
    public static abstract class Factory {
        @Deprecated
        public abstract PluralRules forLocale(ULocale uLocale, PluralType pluralType);

        @Deprecated
        public abstract ULocale[] getAvailableULocales();

        @Deprecated
        public abstract ULocale getFunctionalEquivalent(ULocale uLocale, boolean[] zArr);

        @Deprecated
        public abstract boolean hasOverride(ULocale uLocale);

        @Deprecated
        protected Factory() {
        }

        @Deprecated
        public final PluralRules forLocale(ULocale locale) {
            return forLocale(locale, PluralType.CARDINAL);
        }

        @Deprecated
        public static PluralRulesLoader getDefaultFactory() {
            return PluralRulesLoader.loader;
        }
    }

    public static PluralRules parseDescription(String description) throws ParseException {
        String description2 = description.trim();
        return description2.length() == 0 ? DEFAULT : new PluralRules(parseRuleChain(description2));
    }

    public static PluralRules createRules(String description) {
        try {
            return parseDescription(description);
        } catch (Exception e) {
            return null;
        }
    }

    @Deprecated
    /* loaded from: classes.dex */
    public static class FixedDecimal extends Number implements Comparable<FixedDecimal>, IFixedDecimal {
        static final long MAX = 1000000000000000000L;
        private static final long MAX_INTEGER_PART = 1000000000;
        private static final long serialVersionUID = -4756200506571685661L;
        private final int baseFactor;
        final long decimalDigits;
        final long decimalDigitsWithoutTrailingZeros;
        final boolean hasIntegerValue;
        final long integerValue;
        final boolean isNegative;
        final double source;
        final int visibleDecimalDigitCount;
        final int visibleDecimalDigitCountWithoutTrailingZeros;

        @Deprecated
        public double getSource() {
            return this.source;
        }

        @Deprecated
        public int getVisibleDecimalDigitCount() {
            return this.visibleDecimalDigitCount;
        }

        @Deprecated
        public int getVisibleDecimalDigitCountWithoutTrailingZeros() {
            return this.visibleDecimalDigitCountWithoutTrailingZeros;
        }

        @Deprecated
        public long getDecimalDigits() {
            return this.decimalDigits;
        }

        @Deprecated
        public long getDecimalDigitsWithoutTrailingZeros() {
            return this.decimalDigitsWithoutTrailingZeros;
        }

        @Deprecated
        public long getIntegerValue() {
            return this.integerValue;
        }

        @Deprecated
        public boolean isHasIntegerValue() {
            return this.hasIntegerValue;
        }

        @Deprecated
        public boolean isNegative() {
            return this.isNegative;
        }

        @Deprecated
        public int getBaseFactor() {
            return this.baseFactor;
        }

        @Deprecated
        public FixedDecimal(double n, int v, long f) {
            boolean z = n < 0.0d;
            this.isNegative = z;
            double d = z ? -n : n;
            this.source = d;
            this.visibleDecimalDigitCount = v;
            this.decimalDigits = f;
            long j = n > 1.0E18d ? MAX : (long) n;
            this.integerValue = j;
            this.hasIntegerValue = d == ((double) j);
            if (f == 0) {
                this.decimalDigitsWithoutTrailingZeros = 0L;
                this.visibleDecimalDigitCountWithoutTrailingZeros = 0;
            } else {
                long fdwtz = f;
                int trimmedCount = v;
                while (fdwtz % 10 == 0) {
                    fdwtz /= 10;
                    trimmedCount--;
                }
                this.decimalDigitsWithoutTrailingZeros = fdwtz;
                this.visibleDecimalDigitCountWithoutTrailingZeros = trimmedCount;
            }
            this.baseFactor = (int) Math.pow(10.0d, v);
        }

        @Deprecated
        public FixedDecimal(double n, int v) {
            this(n, v, getFractionalDigits(n, v));
        }

        private static int getFractionalDigits(double n, int v) {
            if (v == 0) {
                return 0;
            }
            if (n < 0.0d) {
                n = -n;
            }
            int baseFactor = (int) Math.pow(10.0d, v);
            long scaled = Math.round(baseFactor * n);
            return (int) (scaled % baseFactor);
        }

        @Deprecated
        public FixedDecimal(double n) {
            this(n, decimals(n));
        }

        @Deprecated
        public FixedDecimal(long n) {
            this(n, 0);
        }

        @Deprecated
        public static int decimals(double n) {
            if (Double.isInfinite(n) || Double.isNaN(n)) {
                return 0;
            }
            if (n < 0.0d) {
                n = -n;
            }
            if (n == Math.floor(n)) {
                return 0;
            }
            if (n < 1.0E9d) {
                long temp = ((long) (1000000.0d * n)) % 1000000;
                int mask = 10;
                for (int digits = 6; digits > 0; digits--) {
                    if (temp % mask == 0) {
                        mask *= 10;
                    } else {
                        return digits;
                    }
                }
                return 0;
            }
            String buf = String.format(Locale.ENGLISH, "%1.15e", Double.valueOf(n));
            int ePos = buf.lastIndexOf(101);
            int expNumPos = ePos + 1;
            if (buf.charAt(expNumPos) == '+') {
                expNumPos++;
            }
            String exponentStr = buf.substring(expNumPos);
            int exponent = Integer.parseInt(exponentStr);
            int numFractionDigits = (ePos - 2) - exponent;
            if (numFractionDigits < 0) {
                return 0;
            }
            for (int i = ePos - 1; numFractionDigits > 0 && buf.charAt(i) == '0'; i--) {
                numFractionDigits--;
            }
            return numFractionDigits;
        }

        @Deprecated
        public FixedDecimal(String n) {
            this(Double.parseDouble(n), getVisibleFractionCount(n));
        }

        private static int getVisibleFractionCount(String value) {
            String value2 = value.trim();
            int decimalPos = value2.indexOf(46) + 1;
            if (decimalPos == 0) {
                return 0;
            }
            return value2.length() - decimalPos;
        }

        @Override // com.ibm.icu.text.PluralRules.IFixedDecimal
        @Deprecated
        public double getPluralOperand(Operand operand) {
            switch (C07462.$SwitchMap$com$ibm$icu$text$PluralRules$Operand[operand.ordinal()]) {
                case 1:
                    return this.source;
                case 2:
                    return this.integerValue;
                case 3:
                    return this.decimalDigits;
                case 4:
                    return this.decimalDigitsWithoutTrailingZeros;
                case 5:
                    return this.visibleDecimalDigitCount;
                case 6:
                    return this.visibleDecimalDigitCountWithoutTrailingZeros;
                default:
                    return this.source;
            }
        }

        @Deprecated
        public static Operand getOperand(String t) {
            return Operand.valueOf(t);
        }

        @Override // java.lang.Comparable
        @Deprecated
        public int compareTo(FixedDecimal other) {
            long j = this.integerValue;
            long j2 = other.integerValue;
            if (j != j2) {
                return j < j2 ? -1 : 1;
            }
            double d = this.source;
            double d2 = other.source;
            if (d != d2) {
                return d < d2 ? -1 : 1;
            }
            int i = this.visibleDecimalDigitCount;
            int i2 = other.visibleDecimalDigitCount;
            if (i != i2) {
                return i < i2 ? -1 : 1;
            }
            long diff = this.decimalDigits - other.decimalDigits;
            if (diff != 0) {
                return diff < 0 ? -1 : 1;
            }
            return 0;
        }

        @Deprecated
        public boolean equals(Object arg0) {
            if (arg0 == null) {
                return false;
            }
            if (arg0 == this) {
                return true;
            }
            if (!(arg0 instanceof FixedDecimal)) {
                return false;
            }
            FixedDecimal other = (FixedDecimal) arg0;
            if (this.source != other.source || this.visibleDecimalDigitCount != other.visibleDecimalDigitCount || this.decimalDigits != other.decimalDigits) {
                return false;
            }
            return true;
        }

        @Deprecated
        public int hashCode() {
            return (int) (this.decimalDigits + ((this.visibleDecimalDigitCount + ((int) (this.source * 37.0d))) * 37));
        }

        @Deprecated
        public String toString() {
            return String.format("%." + this.visibleDecimalDigitCount + "f", Double.valueOf(this.source));
        }

        @Deprecated
        public boolean hasIntegerValue() {
            return this.hasIntegerValue;
        }

        @Override // java.lang.Number
        @Deprecated
        public int intValue() {
            return (int) this.integerValue;
        }

        @Override // java.lang.Number
        @Deprecated
        public long longValue() {
            return this.integerValue;
        }

        @Override // java.lang.Number
        @Deprecated
        public float floatValue() {
            return (float) this.source;
        }

        @Override // java.lang.Number
        @Deprecated
        public double doubleValue() {
            return this.isNegative ? -this.source : this.source;
        }

        @Deprecated
        public long getShiftedValue() {
            return (this.integerValue * this.baseFactor) + this.decimalDigits;
        }

        private void writeObject(ObjectOutputStream out) throws IOException {
            throw new NotSerializableException();
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            throw new NotSerializableException();
        }

        @Override // com.ibm.icu.text.PluralRules.IFixedDecimal
        @Deprecated
        public boolean isNaN() {
            return Double.isNaN(this.source);
        }

        @Override // com.ibm.icu.text.PluralRules.IFixedDecimal
        @Deprecated
        public boolean isInfinite() {
            return Double.isInfinite(this.source);
        }
    }

    @Deprecated
    /* loaded from: classes.dex */
    public static class FixedDecimalRange {
        @Deprecated
        public final FixedDecimal end;
        @Deprecated
        public final FixedDecimal start;

        @Deprecated
        public FixedDecimalRange(FixedDecimal start, FixedDecimal end) {
            if (start.visibleDecimalDigitCount != end.visibleDecimalDigitCount) {
                throw new IllegalArgumentException("Ranges must have the same number of visible decimals: " + start + "~" + end);
            }
            this.start = start;
            this.end = end;
        }

        @Deprecated
        public String toString() {
            return this.start + (this.end == this.start ? "" : "~" + this.end);
        }
    }

    @Deprecated
    /* loaded from: classes.dex */
    public static class FixedDecimalSamples {
        @Deprecated
        public final boolean bounded;
        @Deprecated
        public final SampleType sampleType;
        @Deprecated
        public final Set<FixedDecimalRange> samples;

        private FixedDecimalSamples(SampleType sampleType, Set<FixedDecimalRange> samples, boolean bounded) {
            this.sampleType = sampleType;
            this.samples = samples;
            this.bounded = bounded;
        }

        static FixedDecimalSamples parse(String source) {
            SampleType sampleType2;
            String[] split;
            boolean bounded2 = true;
            boolean haveBound = false;
            Set<FixedDecimalRange> samples2 = new LinkedHashSet<>();
            if (source.startsWith("integer")) {
                sampleType2 = SampleType.INTEGER;
            } else if (source.startsWith("decimal")) {
                sampleType2 = SampleType.DECIMAL;
            } else {
                throw new IllegalArgumentException("Samples must start with 'integer' or 'decimal'");
            }
            for (String range : PluralRules.COMMA_SEPARATED.split(source.substring(7).trim())) {
                if (range.equals("\u2026") || range.equals("...")) {
                    bounded2 = false;
                    haveBound = true;
                } else if (haveBound) {
                    throw new IllegalArgumentException("Can only have \u2026 at the end of samples: " + range);
                } else {
                    String[] rangeParts = PluralRules.TILDE_SEPARATED.split(range);
                    switch (rangeParts.length) {
                        case 1:
                            FixedDecimal sample = new FixedDecimal(rangeParts[0]);
                            checkDecimal(sampleType2, sample);
                            samples2.add(new FixedDecimalRange(sample, sample));
                            continue;
                        case 2:
                            FixedDecimal start = new FixedDecimal(rangeParts[0]);
                            FixedDecimal end = new FixedDecimal(rangeParts[1]);
                            checkDecimal(sampleType2, start);
                            checkDecimal(sampleType2, end);
                            samples2.add(new FixedDecimalRange(start, end));
                            continue;
                        default:
                            throw new IllegalArgumentException("Ill-formed number range: " + range);
                    }
                }
            }
            return new FixedDecimalSamples(sampleType2, Collections.unmodifiableSet(samples2), bounded2);
        }

        private static void checkDecimal(SampleType sampleType2, FixedDecimal sample) {
            if ((sampleType2 == SampleType.INTEGER) != (sample.getVisibleDecimalDigitCount() == 0)) {
                throw new IllegalArgumentException("Ill-formed number range: " + sample);
            }
        }

        @Deprecated
        public Set<Double> addSamples(Set<Double> result) {
            for (FixedDecimalRange item : this.samples) {
                long startDouble = item.start.getShiftedValue();
                long endDouble = item.end.getShiftedValue();
                for (long d = startDouble; d <= endDouble; d++) {
                    result.add(Double.valueOf(d / item.start.baseFactor));
                }
            }
            return result;
        }

        @Deprecated
        public String toString() {
            StringBuilder b = new StringBuilder("@").append(this.sampleType.toString().toLowerCase(Locale.ENGLISH));
            boolean first = true;
            for (FixedDecimalRange item : this.samples) {
                if (first) {
                    first = false;
                } else {
                    b.append(",");
                }
                b.append(' ').append(item);
            }
            if (!this.bounded) {
                b.append(", \u2026");
            }
            return b.toString();
        }

        @Deprecated
        public Set<FixedDecimalRange> getSamples() {
            return this.samples;
        }

        @Deprecated
        public void getStartEndSamples(Set<FixedDecimal> target) {
            for (FixedDecimalRange item : this.samples) {
                target.add(item.start);
                target.add(item.end);
            }
        }
    }

    /* loaded from: classes.dex */
    static class SimpleTokenizer {
        static final UnicodeSet BREAK_AND_IGNORE = new UnicodeSet(9, 10, 12, 13, 32, 32).m87freeze();
        static final UnicodeSet BREAK_AND_KEEP = new UnicodeSet(33, 33, 37, 37, 44, 44, 46, 46, 61, 61).m87freeze();

        SimpleTokenizer() {
        }

        static String[] split(String source) {
            int last = -1;
            List<String> result = new ArrayList<>();
            for (int i = 0; i < source.length(); i++) {
                char ch = source.charAt(i);
                if (BREAK_AND_IGNORE.contains(ch)) {
                    if (last >= 0) {
                        result.add(source.substring(last, i));
                        last = -1;
                    }
                } else if (BREAK_AND_KEEP.contains(ch)) {
                    if (last >= 0) {
                        result.add(source.substring(last, i));
                    }
                    result.add(source.substring(i, i + 1));
                    last = -1;
                } else if (last < 0) {
                    last = i;
                }
            }
            if (last >= 0) {
                result.add(source.substring(last));
            }
            return (String[]) result.toArray(new String[result.size()]);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:142:0x0255 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0198  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static Constraint parseConstraint(String description) throws ParseException {
        Constraint result;
        int i;
        String[] and_together;
        Constraint andConstraint;
        int j;
        Constraint andConstraint2;
        boolean integersOnly;
        int x;
        String t;
        boolean integersOnly2;
        Constraint result2;
        long low;
        boolean integersOnly3;
        boolean inRange;
        long high;
        String t2;
        Object obj;
        int x2;
        int x3;
        long[] vals;
        String[] or_together = OR_SEPARATED.split(description);
        int x4 = 0;
        Constraint result3 = null;
        while (x4 < or_together.length) {
            String[] and_together2 = AND_SEPARATED.split(or_together[x4]);
            int j2 = 0;
            Constraint andConstraint3 = null;
            while (j2 < and_together2.length) {
                Constraint newConstraint = NO_CONSTRAINT;
                String condition = and_together2[j2].trim();
                String[] tokens = SimpleTokenizer.split(condition);
                int mod = 0;
                boolean inRange2 = true;
                double lowBound = 9.223372036854776E18d;
                String[] or_together2 = or_together;
                int x5 = 0 + 1;
                String t3 = tokens[0];
                boolean hackForCompatibility = false;
                try {
                    Operand operand = FixedDecimal.getOperand(t3);
                    if (x5 >= tokens.length) {
                        result = result3;
                        i = x4;
                        and_together = and_together2;
                        andConstraint = andConstraint3;
                        j = j2;
                    } else {
                        int x6 = x5 + 1;
                        String t4 = tokens[x5];
                        if ("mod".equals(t4) || "%".equals(t4)) {
                            int x7 = x6 + 1;
                            mod = Integer.parseInt(tokens[x6]);
                            x6 = x7 + 1;
                            t4 = nextToken(tokens, x7, condition);
                        }
                        and_together = and_together2;
                        if ("not".equals(t4)) {
                            inRange2 = !true;
                            int x8 = x6 + 1;
                            t4 = nextToken(tokens, x6, condition);
                            if ("=".equals(t4)) {
                                throw unexpected(t4, condition);
                            }
                            x6 = x8;
                        } else if ("!".equals(t4)) {
                            inRange2 = !true;
                            int x9 = x6 + 1;
                            t4 = nextToken(tokens, x6, condition);
                            if (!"=".equals(t4)) {
                                throw unexpected(t4, condition);
                            }
                            x6 = x9;
                        }
                        if (!"is".equals(t4)) {
                            integersOnly = true;
                            if (!"in".equals(t4) && !"=".equals(t4)) {
                                if ("within".equals(t4)) {
                                    integersOnly2 = false;
                                    x = x6 + 1;
                                    t = nextToken(tokens, x6, condition);
                                    if ("not".equals(t)) {
                                        if (!hackForCompatibility && !inRange2) {
                                            throw unexpected(t, condition);
                                        }
                                        boolean inRange3 = !inRange2;
                                        t = nextToken(tokens, x, condition);
                                        inRange2 = inRange3;
                                        x++;
                                    }
                                    List<Long> valueList = new ArrayList<>();
                                    result2 = result3;
                                    double highBound = -9.223372036854776E18d;
                                    while (true) {
                                        j = j2;
                                        result = result2;
                                        low = Long.parseLong(t);
                                        String t5 = t;
                                        i = x4;
                                        if (x < tokens.length) {
                                            andConstraint = andConstraint3;
                                            integersOnly3 = integersOnly2;
                                            inRange = inRange2;
                                            high = low;
                                            t2 = t5;
                                        } else {
                                            int x10 = x + 1;
                                            String t6 = nextToken(tokens, x, condition);
                                            andConstraint = andConstraint3;
                                            if (t6.equals(".")) {
                                                integersOnly3 = integersOnly2;
                                                int x11 = x10 + 1;
                                                String t7 = nextToken(tokens, x10, condition);
                                                if (!t7.equals(".")) {
                                                    throw unexpected(t7, condition);
                                                }
                                                x = x11 + 1;
                                                t2 = nextToken(tokens, x11, condition);
                                                long high2 = Long.parseLong(t2);
                                                if (x >= tokens.length) {
                                                    inRange = inRange2;
                                                    high = high2;
                                                } else {
                                                    int x12 = x + 1;
                                                    t2 = nextToken(tokens, x, condition);
                                                    if (!t2.equals(",")) {
                                                        throw unexpected(t2, condition);
                                                    }
                                                    x = x12;
                                                    inRange = inRange2;
                                                    high = high2;
                                                }
                                            } else {
                                                integersOnly3 = integersOnly2;
                                                if (t6.equals(",")) {
                                                    inRange = inRange2;
                                                    high = low;
                                                    x = x10;
                                                    t2 = t6;
                                                } else {
                                                    throw unexpected(t6, condition);
                                                }
                                            }
                                        }
                                        if (low <= high) {
                                            throw unexpected(low + "~" + high, condition);
                                        }
                                        if (mod != 0) {
                                            obj = ",";
                                            x2 = x;
                                            if (high >= mod) {
                                                throw unexpected(high + ">mod=" + mod, condition);
                                            }
                                        } else {
                                            obj = ",";
                                            x2 = x;
                                        }
                                        valueList.add(Long.valueOf(low));
                                        valueList.add(Long.valueOf(high));
                                        lowBound = Math.min(lowBound, low);
                                        highBound = Math.max(highBound, high);
                                        x3 = x2;
                                        if (x3 < tokens.length) {
                                            t = nextToken(tokens, x3, condition);
                                            x = x3 + 1;
                                            inRange2 = inRange;
                                            j2 = j;
                                            result2 = result;
                                            x4 = i;
                                            andConstraint3 = andConstraint;
                                            integersOnly2 = integersOnly3;
                                        } else if (t2.equals(obj)) {
                                            throw unexpected(t2, condition);
                                        } else {
                                            if (valueList.size() == 2) {
                                                vals = null;
                                            } else {
                                                long[] vals2 = new long[valueList.size()];
                                                for (int k = 0; k < vals2.length; k++) {
                                                    vals2[k] = valueList.get(k).longValue();
                                                }
                                                vals = vals2;
                                            }
                                            if (lowBound != highBound && hackForCompatibility && !inRange) {
                                                throw unexpected("is not <range>", condition);
                                            }
                                            newConstraint = new RangeConstraint(mod, inRange, operand, integersOnly3, lowBound, highBound, vals);
                                        }
                                    }
                                } else {
                                    throw unexpected(t4, condition);
                                }
                            }
                        } else {
                            integersOnly = true;
                        }
                        hackForCompatibility = "is".equals(t4);
                        if (hackForCompatibility && !inRange2) {
                            throw unexpected(t4, condition);
                        }
                        x = x6 + 1;
                        t = nextToken(tokens, x6, condition);
                        integersOnly2 = integersOnly;
                        if ("not".equals(t)) {
                        }
                        List<Long> valueList2 = new ArrayList<>();
                        result2 = result3;
                        double highBound2 = -9.223372036854776E18d;
                        while (true) {
                            j = j2;
                            result = result2;
                            low = Long.parseLong(t);
                            String t52 = t;
                            i = x4;
                            if (x < tokens.length) {
                            }
                            if (low <= high) {
                            }
                            t = nextToken(tokens, x3, condition);
                            x = x3 + 1;
                            inRange2 = inRange;
                            j2 = j;
                            result2 = result;
                            x4 = i;
                            andConstraint3 = andConstraint;
                            integersOnly2 = integersOnly3;
                        }
                    }
                    if (andConstraint == null) {
                        andConstraint2 = newConstraint;
                    } else {
                        andConstraint2 = new AndConstraint(andConstraint, newConstraint);
                    }
                    andConstraint3 = andConstraint2;
                    j2 = j + 1;
                    or_together = or_together2;
                    and_together2 = and_together;
                    result3 = result;
                    x4 = i;
                } catch (Exception e) {
                    throw unexpected(t3, condition);
                }
            }
            String[] or_together3 = or_together;
            Constraint result4 = result3;
            int i2 = x4;
            result3 = andConstraint3;
            if (result4 != null) {
                result3 = new OrConstraint(result4, result3);
            }
            x4 = i2 + 1;
            or_together = or_together3;
        }
        return result3;
    }

    private static ParseException unexpected(String token, String context) {
        return new ParseException("unexpected token '" + token + "' in '" + context + "'", -1);
    }

    private static String nextToken(String[] tokens, int x, String context) throws ParseException {
        if (x < tokens.length) {
            return tokens[x];
        }
        throw new ParseException("missing token at end of '" + context + "'", -1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Rule parseRule(String description) throws ParseException {
        Constraint constraint;
        if (description.length() == 0) {
            return DEFAULT_RULE;
        }
        String description2 = description.toLowerCase(Locale.ENGLISH);
        int x = description2.indexOf(58);
        if (x == -1) {
            throw new ParseException("missing ':' in rule description '" + description2 + "'", 0);
        }
        String keyword = description2.substring(0, x).trim();
        if (!isValidKeyword(keyword)) {
            throw new ParseException("keyword '" + keyword + " is not valid", 0);
        }
        String description3 = description2.substring(x + 1).trim();
        String[] constraintOrSamples = AT_SEPARATED.split(description3);
        FixedDecimalSamples integerSamples = null;
        FixedDecimalSamples decimalSamples = null;
        switch (constraintOrSamples.length) {
            case 1:
                break;
            case 2:
                integerSamples = FixedDecimalSamples.parse(constraintOrSamples[1]);
                if (integerSamples.sampleType == SampleType.DECIMAL) {
                    decimalSamples = integerSamples;
                    integerSamples = null;
                    break;
                }
                break;
            case 3:
                integerSamples = FixedDecimalSamples.parse(constraintOrSamples[1]);
                decimalSamples = FixedDecimalSamples.parse(constraintOrSamples[2]);
                if (integerSamples.sampleType != SampleType.INTEGER || decimalSamples.sampleType != SampleType.DECIMAL) {
                    throw new IllegalArgumentException("Must have @integer then @decimal in " + description3);
                }
                break;
            default:
                throw new IllegalArgumentException("Too many samples in " + description3);
        }
        if (0 != 0) {
            throw new IllegalArgumentException("Ill-formed samples\u2014'@' characters.");
        }
        boolean isOther = keyword.equals(KEYWORD_OTHER);
        if (isOther != (constraintOrSamples[0].length() == 0)) {
            throw new IllegalArgumentException("The keyword 'other' must have no constraints, just samples.");
        }
        if (isOther) {
            constraint = NO_CONSTRAINT;
        } else {
            constraint = parseConstraint(constraintOrSamples[0]);
        }
        return new Rule(keyword, constraint, integerSamples, decimalSamples);
    }

    private static RuleList parseRuleChain(String description) throws ParseException {
        RuleList result = new RuleList();
        if (description.endsWith(";")) {
            description = description.substring(0, description.length() - 1);
        }
        String[] rules = SEMI_SEPARATED.split(description);
        for (String str : rules) {
            Rule rule = parseRule(str.trim());
            result.hasExplicitBoundingInfo |= (rule.integerSamples == null && rule.decimalSamples == null) ? false : true;
            result.addRule(rule);
        }
        return result.finish();
    }

    /* loaded from: classes.dex */
    private static class RangeConstraint implements Constraint, Serializable {
        private static final long serialVersionUID = 1;
        private final boolean inRange;
        private final boolean integersOnly;
        private final double lowerBound;
        private final int mod;
        private final Operand operand;
        private final long[] range_list;
        private final double upperBound;

        RangeConstraint(int mod, boolean inRange, Operand operand, boolean integersOnly, double lowBound, double highBound, long[] vals) {
            this.mod = mod;
            this.inRange = inRange;
            this.integersOnly = integersOnly;
            this.lowerBound = lowBound;
            this.upperBound = highBound;
            this.range_list = vals;
            this.operand = operand;
        }

        @Override // com.ibm.icu.text.PluralRules.Constraint
        public boolean isFulfilled(IFixedDecimal number) {
            double n = number.getPluralOperand(this.operand);
            if ((this.integersOnly && n - ((long) n) != 0.0d) || (this.operand == Operand.j && number.getPluralOperand(Operand.v) != 0.0d)) {
                return !this.inRange;
            }
            int i = this.mod;
            if (i != 0) {
                n %= i;
            }
            boolean test = n >= this.lowerBound && n <= this.upperBound;
            if (test && this.range_list != null) {
                test = false;
                int i2 = 0;
                while (!test) {
                    long[] jArr = this.range_list;
                    if (i2 >= jArr.length) {
                        break;
                    }
                    test = n >= ((double) jArr[i2]) && n <= ((double) jArr[i2 + 1]);
                    i2 += 2;
                }
            }
            return this.inRange == test;
        }

        @Override // com.ibm.icu.text.PluralRules.Constraint
        public boolean isLimited(SampleType sampleType) {
            double d = this.lowerBound;
            boolean valueIsZero = d == this.upperBound && d == 0.0d;
            boolean hasDecimals = (this.operand == Operand.v || this.operand == Operand.w || this.operand == Operand.f || this.operand == Operand.t) && this.inRange != valueIsZero;
            switch (C07462.$SwitchMap$com$ibm$icu$text$PluralRules$SampleType[sampleType.ordinal()]) {
                case 1:
                    if (hasDecimals) {
                        return true;
                    }
                    return (this.operand == Operand.n || this.operand == Operand.i || this.operand == Operand.j) && this.mod == 0 && this.inRange;
                case 2:
                    return (!hasDecimals || this.operand == Operand.n || this.operand == Operand.j) && (this.integersOnly || this.lowerBound == this.upperBound) && this.mod == 0 && this.inRange;
                default:
                    return false;
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:12:0x002f, code lost:
            if (r11.inRange != false) goto L12;
         */
        /* JADX WARN: Code restructure failed: missing block: B:14:0x0032, code lost:
            r1 = " != ";
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x003a, code lost:
            if (r11.inRange != false) goto L12;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public String toString() {
            long[] jArr;
            StringBuilder result = new StringBuilder();
            result.append(this.operand);
            if (this.mod != 0) {
                result.append(" % ").append(this.mod);
            }
            boolean isList = this.lowerBound != this.upperBound;
            String str = " = ";
            if (isList) {
                if (!this.integersOnly) {
                    str = this.inRange ? " within " : " not within ";
                }
            }
            result.append(str);
            if (this.range_list == null) {
                PluralRules.addRange(result, this.lowerBound, this.upperBound, false);
            } else {
                int i = 0;
                while (true) {
                    if (i >= this.range_list.length) {
                        break;
                    }
                    PluralRules.addRange(result, jArr[i], jArr[i + 1], i != 0);
                    i += 2;
                }
            }
            return result.toString();
        }
    }

    /* renamed from: com.ibm.icu.text.PluralRules$2 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C07462 {
        static final /* synthetic */ int[] $SwitchMap$com$ibm$icu$text$PluralRules$Operand;
        static final /* synthetic */ int[] $SwitchMap$com$ibm$icu$text$PluralRules$SampleType;

        static {
            int[] iArr = new int[SampleType.values().length];
            $SwitchMap$com$ibm$icu$text$PluralRules$SampleType = iArr;
            try {
                iArr[SampleType.INTEGER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$PluralRules$SampleType[SampleType.DECIMAL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            int[] iArr2 = new int[Operand.values().length];
            $SwitchMap$com$ibm$icu$text$PluralRules$Operand = iArr2;
            try {
                iArr2[Operand.n.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$PluralRules$Operand[Operand.i.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$PluralRules$Operand[Operand.f.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$PluralRules$Operand[Operand.t.ordinal()] = 4;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$PluralRules$Operand[Operand.v.ordinal()] = 5;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$PluralRules$Operand[Operand.w.ordinal()] = 6;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void addRange(StringBuilder result, double lb, double ub, boolean addSeparator) {
        if (addSeparator) {
            result.append(",");
        }
        if (lb == ub) {
            result.append(format(lb));
        } else {
            result.append(format(lb) + ".." + format(ub));
        }
    }

    private static String format(double lb) {
        long lbi = (long) lb;
        return lb == ((double) lbi) ? String.valueOf(lbi) : String.valueOf(lb);
    }

    /* loaded from: classes.dex */
    private static abstract class BinaryConstraint implements Constraint, Serializable {
        private static final long serialVersionUID = 1;

        /* renamed from: a */
        protected final Constraint f152a;

        /* renamed from: b */
        protected final Constraint f153b;

        protected BinaryConstraint(Constraint a, Constraint b) {
            this.f152a = a;
            this.f153b = b;
        }
    }

    /* loaded from: classes.dex */
    private static class AndConstraint extends BinaryConstraint {
        private static final long serialVersionUID = 7766999779862263523L;

        AndConstraint(Constraint a, Constraint b) {
            super(a, b);
        }

        @Override // com.ibm.icu.text.PluralRules.Constraint
        public boolean isFulfilled(IFixedDecimal n) {
            return this.f152a.isFulfilled(n) && this.f153b.isFulfilled(n);
        }

        @Override // com.ibm.icu.text.PluralRules.Constraint
        public boolean isLimited(SampleType sampleType) {
            return this.f152a.isLimited(sampleType) || this.f153b.isLimited(sampleType);
        }

        public String toString() {
            return this.f152a.toString() + " and " + this.f153b.toString();
        }
    }

    /* loaded from: classes.dex */
    private static class OrConstraint extends BinaryConstraint {
        private static final long serialVersionUID = 1405488568664762222L;

        OrConstraint(Constraint a, Constraint b) {
            super(a, b);
        }

        @Override // com.ibm.icu.text.PluralRules.Constraint
        public boolean isFulfilled(IFixedDecimal n) {
            return this.f152a.isFulfilled(n) || this.f153b.isFulfilled(n);
        }

        @Override // com.ibm.icu.text.PluralRules.Constraint
        public boolean isLimited(SampleType sampleType) {
            return this.f152a.isLimited(sampleType) && this.f153b.isLimited(sampleType);
        }

        public String toString() {
            return this.f152a.toString() + " or " + this.f153b.toString();
        }
    }

    /* loaded from: classes.dex */
    private static class Rule implements Serializable {
        private static final long serialVersionUID = 1;
        private final Constraint constraint;
        private final FixedDecimalSamples decimalSamples;
        private final FixedDecimalSamples integerSamples;
        private final String keyword;

        public Rule(String keyword, Constraint constraint, FixedDecimalSamples integerSamples, FixedDecimalSamples decimalSamples) {
            this.keyword = keyword;
            this.constraint = constraint;
            this.integerSamples = integerSamples;
            this.decimalSamples = decimalSamples;
        }

        public Rule and(Constraint c) {
            return new Rule(this.keyword, new AndConstraint(this.constraint, c), this.integerSamples, this.decimalSamples);
        }

        /* renamed from: or */
        public Rule m46or(Constraint c) {
            return new Rule(this.keyword, new OrConstraint(this.constraint, c), this.integerSamples, this.decimalSamples);
        }

        public String getKeyword() {
            return this.keyword;
        }

        public boolean appliesTo(IFixedDecimal n) {
            return this.constraint.isFulfilled(n);
        }

        public boolean isLimited(SampleType sampleType) {
            return this.constraint.isLimited(sampleType);
        }

        public String toString() {
            return this.keyword + PluralRules.KEYWORD_RULE_SEPARATOR + this.constraint.toString() + (this.integerSamples == null ? "" : " " + this.integerSamples.toString()) + (this.decimalSamples != null ? " " + this.decimalSamples.toString() : "");
        }

        public int hashCode() {
            return this.keyword.hashCode() ^ this.constraint.hashCode();
        }

        public String getConstraint() {
            return this.constraint.toString();
        }
    }

    /* loaded from: classes.dex */
    private static class RuleList implements Serializable {
        private static final long serialVersionUID = 1;
        private boolean hasExplicitBoundingInfo;
        private final List<Rule> rules;

        private RuleList() {
            this.hasExplicitBoundingInfo = false;
            this.rules = new ArrayList();
        }

        public RuleList addRule(Rule nextRule) {
            String keyword = nextRule.getKeyword();
            for (Rule rule : this.rules) {
                if (keyword.equals(rule.getKeyword())) {
                    throw new IllegalArgumentException("Duplicate keyword: " + keyword);
                }
            }
            this.rules.add(nextRule);
            return this;
        }

        public RuleList finish() throws ParseException {
            Rule otherRule = null;
            Iterator<Rule> it = this.rules.iterator();
            while (it.hasNext()) {
                Rule rule = it.next();
                if (PluralRules.KEYWORD_OTHER.equals(rule.getKeyword())) {
                    otherRule = rule;
                    it.remove();
                }
            }
            if (otherRule == null) {
                otherRule = PluralRules.parseRule("other:");
            }
            this.rules.add(otherRule);
            return this;
        }

        private Rule selectRule(IFixedDecimal n) {
            for (Rule rule : this.rules) {
                if (rule.appliesTo(n)) {
                    return rule;
                }
            }
            return null;
        }

        public String select(IFixedDecimal n) {
            if (n.isInfinite() || n.isNaN()) {
                return PluralRules.KEYWORD_OTHER;
            }
            Rule r = selectRule(n);
            return r.getKeyword();
        }

        public Set<String> getKeywords() {
            Set<String> result = new LinkedHashSet<>();
            for (Rule rule : this.rules) {
                result.add(rule.getKeyword());
            }
            return result;
        }

        public boolean isLimited(String keyword, SampleType sampleType) {
            if (this.hasExplicitBoundingInfo) {
                FixedDecimalSamples mySamples = getDecimalSamples(keyword, sampleType);
                if (mySamples == null) {
                    return true;
                }
                return mySamples.bounded;
            }
            return computeLimited(keyword, sampleType);
        }

        public boolean computeLimited(String keyword, SampleType sampleType) {
            boolean result = false;
            for (Rule rule : this.rules) {
                if (keyword.equals(rule.getKeyword())) {
                    if (!rule.isLimited(sampleType)) {
                        return false;
                    }
                    result = true;
                }
            }
            return result;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();
            for (Rule rule : this.rules) {
                if (builder.length() != 0) {
                    builder.append(PluralRules.CATEGORY_SEPARATOR);
                }
                builder.append(rule);
            }
            return builder.toString();
        }

        public String getRules(String keyword) {
            for (Rule rule : this.rules) {
                if (rule.getKeyword().equals(keyword)) {
                    return rule.getConstraint();
                }
            }
            return null;
        }

        public boolean select(IFixedDecimal sample, String keyword) {
            for (Rule rule : this.rules) {
                if (rule.getKeyword().equals(keyword) && rule.appliesTo(sample)) {
                    return true;
                }
            }
            return false;
        }

        public FixedDecimalSamples getDecimalSamples(String keyword, SampleType sampleType) {
            for (Rule rule : this.rules) {
                if (rule.getKeyword().equals(keyword)) {
                    return sampleType == SampleType.INTEGER ? rule.integerSamples : rule.decimalSamples;
                }
            }
            return null;
        }
    }

    private boolean addConditional(Set<IFixedDecimal> toAddTo, Set<IFixedDecimal> others, double trial) {
        IFixedDecimal toAdd = new FixedDecimal(trial);
        if (!toAddTo.contains(toAdd) && !others.contains(toAdd)) {
            others.add(toAdd);
            return true;
        }
        return false;
    }

    public static PluralRules forLocale(ULocale locale) {
        return Factory.getDefaultFactory().forLocale(locale, PluralType.CARDINAL);
    }

    public static PluralRules forLocale(Locale locale) {
        return forLocale(ULocale.forLocale(locale));
    }

    public static PluralRules forLocale(ULocale locale, PluralType type) {
        return Factory.getDefaultFactory().forLocale(locale, type);
    }

    public static PluralRules forLocale(Locale locale, PluralType type) {
        return forLocale(ULocale.forLocale(locale), type);
    }

    private static boolean isValidKeyword(String token) {
        return ALLOWED_ID.containsAll(token);
    }

    private PluralRules(RuleList rules) {
        this.rules = rules;
        this.keywords = Collections.unmodifiableSet(rules.getKeywords());
    }

    public int hashCode() {
        return this.rules.hashCode();
    }

    public String select(double number) {
        return this.rules.select(new FixedDecimal(number));
    }

    @Deprecated
    public String select(double number, int countVisibleFractionDigits, long fractionaldigits) {
        return this.rules.select(new FixedDecimal(number, countVisibleFractionDigits, fractionaldigits));
    }

    @Deprecated
    public String select(IFixedDecimal number) {
        return this.rules.select(number);
    }

    @Deprecated
    public boolean matches(FixedDecimal sample, String keyword) {
        return this.rules.select(sample, keyword);
    }

    public Set<String> getKeywords() {
        return this.keywords;
    }

    public double getUniqueKeywordValue(String keyword) {
        Collection<Double> values = getAllKeywordValues(keyword);
        if (values != null && values.size() == 1) {
            return values.iterator().next().doubleValue();
        }
        return -0.00123456777d;
    }

    public Collection<Double> getAllKeywordValues(String keyword) {
        return getAllKeywordValues(keyword, SampleType.INTEGER);
    }

    @Deprecated
    public Collection<Double> getAllKeywordValues(String keyword, SampleType type) {
        Collection<Double> samples;
        if (isLimited(keyword, type) && (samples = getSamples(keyword, type)) != null) {
            return Collections.unmodifiableCollection(samples);
        }
        return null;
    }

    public Collection<Double> getSamples(String keyword) {
        return getSamples(keyword, SampleType.INTEGER);
    }

    @Deprecated
    public Collection<Double> getSamples(String keyword, SampleType sampleType) {
        if (this.keywords.contains(keyword)) {
            Set<Double> result = new TreeSet<>();
            if (this.rules.hasExplicitBoundingInfo) {
                FixedDecimalSamples samples = this.rules.getDecimalSamples(keyword, sampleType);
                return samples == null ? Collections.unmodifiableSet(result) : Collections.unmodifiableSet(samples.addSamples(result));
            }
            int maxCount = isLimited(keyword, sampleType) ? Integer.MAX_VALUE : 20;
            switch (C07462.$SwitchMap$com$ibm$icu$text$PluralRules$SampleType[sampleType.ordinal()]) {
                case 1:
                    for (int i = 0; i < 200 && addSample(keyword, Integer.valueOf(i), maxCount, result); i++) {
                    }
                    addSample(keyword, Integer.valueOf((int) DurationKt.NANOS_IN_MILLIS), maxCount, result);
                    break;
                case 2:
                    for (int i2 = 0; i2 < 2000 && addSample(keyword, new FixedDecimal(i2 / 10.0d, 1), maxCount, result); i2++) {
                    }
                    addSample(keyword, new FixedDecimal(1000000.0d, 1), maxCount, result);
                    break;
            }
            if (result.size() == 0) {
                return null;
            }
            return Collections.unmodifiableSet(result);
        }
        return null;
    }

    @Deprecated
    public boolean addSample(String keyword, Number sample, int maxCount, Set<Double> result) {
        String selectedKeyword = sample instanceof FixedDecimal ? select((FixedDecimal) sample) : select(sample.doubleValue());
        if (selectedKeyword.equals(keyword)) {
            result.add(Double.valueOf(sample.doubleValue()));
            if (maxCount - 1 < 0) {
                return false;
            }
            return true;
        }
        return true;
    }

    @Deprecated
    public FixedDecimalSamples getDecimalSamples(String keyword, SampleType sampleType) {
        return this.rules.getDecimalSamples(keyword, sampleType);
    }

    public static ULocale[] getAvailableULocales() {
        return Factory.getDefaultFactory().getAvailableULocales();
    }

    public static ULocale getFunctionalEquivalent(ULocale locale, boolean[] isAvailable) {
        return Factory.getDefaultFactory().getFunctionalEquivalent(locale, isAvailable);
    }

    public String toString() {
        return this.rules.toString();
    }

    public boolean equals(Object rhs) {
        return (rhs instanceof PluralRules) && equals((PluralRules) rhs);
    }

    public boolean equals(PluralRules rhs) {
        return rhs != null && toString().equals(rhs.toString());
    }

    public KeywordStatus getKeywordStatus(String keyword, int offset, Set<Double> explicits, Output<Double> uniqueValue) {
        return getKeywordStatus(keyword, offset, explicits, uniqueValue, SampleType.INTEGER);
    }

    @Deprecated
    public KeywordStatus getKeywordStatus(String keyword, int offset, Set<Double> explicits, Output<Double> uniqueValue, SampleType sampleType) {
        if (uniqueValue != null) {
            uniqueValue.value = null;
        }
        if (!this.keywords.contains(keyword)) {
            return KeywordStatus.INVALID;
        }
        if (!isLimited(keyword, sampleType)) {
            return KeywordStatus.UNBOUNDED;
        }
        Collection<Double> values = getSamples(keyword, sampleType);
        int originalSize = values.size();
        if (explicits == null) {
            explicits = Collections.emptySet();
        }
        if (originalSize > explicits.size()) {
            if (originalSize == 1) {
                if (uniqueValue != null) {
                    uniqueValue.value = values.iterator().next();
                }
                return KeywordStatus.UNIQUE;
            }
            return KeywordStatus.BOUNDED;
        }
        HashSet<Double> subtractedSet = new HashSet<>(values);
        for (Double explicit : explicits) {
            subtractedSet.remove(Double.valueOf(explicit.doubleValue() - offset));
        }
        if (subtractedSet.size() == 0) {
            return KeywordStatus.SUPPRESSED;
        }
        if (uniqueValue != null && subtractedSet.size() == 1) {
            uniqueValue.value = subtractedSet.iterator().next();
        }
        return originalSize == 1 ? KeywordStatus.UNIQUE : KeywordStatus.BOUNDED;
    }

    @Deprecated
    public String getRules(String keyword) {
        return this.rules.getRules(keyword);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        throw new NotSerializableException();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        throw new NotSerializableException();
    }

    private Object writeReplace() throws ObjectStreamException {
        return new PluralRulesSerialProxy(toString());
    }

    @Deprecated
    public int compareTo(PluralRules other) {
        return toString().compareTo(other.toString());
    }

    @Deprecated
    public Boolean isLimited(String keyword) {
        return Boolean.valueOf(this.rules.isLimited(keyword, SampleType.INTEGER));
    }

    @Deprecated
    public boolean isLimited(String keyword, SampleType sampleType) {
        return this.rules.isLimited(keyword, sampleType);
    }

    @Deprecated
    public boolean computeLimited(String keyword, SampleType sampleType) {
        return this.rules.computeLimited(keyword, sampleType);
    }
}
