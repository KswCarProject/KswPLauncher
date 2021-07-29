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

public class PluralRules implements Serializable {
    static final UnicodeSet ALLOWED_ID = new UnicodeSet("[a-z]").freeze();
    static final Pattern AND_SEPARATED = Pattern.compile("\\s*and\\s*");
    static final Pattern AT_SEPARATED = Pattern.compile("\\s*\\Q\\E@\\s*");
    @Deprecated
    public static final String CATEGORY_SEPARATOR = ";  ";
    static final Pattern COMMA_SEPARATED = Pattern.compile("\\s*,\\s*");
    public static final PluralRules DEFAULT;
    private static final Rule DEFAULT_RULE;
    static final Pattern DOTDOT_SEPARATED = Pattern.compile("\\s*\\Q..\\E\\s*");
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
    static final Pattern OR_SEPARATED = Pattern.compile("\\s*or\\s*");
    static final Pattern SEMI_SEPARATED = Pattern.compile("\\s*;\\s*");
    static final Pattern TILDE_SEPARATED = Pattern.compile("\\s*~\\s*");
    private static final long serialVersionUID = 1;
    private final transient Set<String> keywords;
    private final RuleList rules;

    private interface Constraint extends Serializable {
        boolean isFulfilled(IFixedDecimal iFixedDecimal);

        boolean isLimited(SampleType sampleType);
    }

    @Deprecated
    public interface IFixedDecimal {
        @Deprecated
        double getPluralOperand(Operand operand);

        @Deprecated
        boolean isInfinite();

        @Deprecated
        boolean isNaN();
    }

    public enum KeywordStatus {
        INVALID,
        SUPPRESSED,
        UNIQUE,
        BOUNDED,
        UNBOUNDED
    }

    @Deprecated
    public enum Operand {
        n,
        i,
        f,
        t,
        v,
        w,
        j
    }

    public enum PluralType {
        CARDINAL,
        ORDINAL
    }

    @Deprecated
    public enum SampleType {
        INTEGER,
        DECIMAL
    }

    static {
        AnonymousClass1 r0 = new Constraint() {
            private static final long serialVersionUID = 9163464945387899416L;

            public boolean isFulfilled(IFixedDecimal n) {
                return true;
            }

            public boolean isLimited(SampleType sampleType) {
                return false;
            }

            public String toString() {
                return "";
            }
        };
        NO_CONSTRAINT = r0;
        Rule rule = new Rule(KEYWORD_OTHER, r0, (FixedDecimalSamples) null, (FixedDecimalSamples) null);
        DEFAULT_RULE = rule;
        DEFAULT = new PluralRules(new RuleList().addRule(rule));
    }

    @Deprecated
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
    public static class FixedDecimal extends Number implements Comparable<FixedDecimal>, IFixedDecimal {
        static final long MAX = 1000000000000000000L;
        private static final long MAX_INTEGER_PART = 1000000000;
        private static final long serialVersionUID = -4756200506571685661L;
        /* access modifiers changed from: private */
        public final int baseFactor;
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
            boolean z = true;
            boolean z2 = n < 0.0d;
            this.isNegative = z2;
            double d = z2 ? -n : n;
            this.source = d;
            this.visibleDecimalDigitCount = v;
            this.decimalDigits = f;
            long j = n > 1.0E18d ? MAX : (long) n;
            this.integerValue = j;
            this.hasIntegerValue = d != ((double) j) ? false : z;
            if (f == 0) {
                this.decimalDigitsWithoutTrailingZeros = 0;
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
            this.baseFactor = (int) Math.pow(10.0d, (double) v);
        }

        @Deprecated
        public FixedDecimal(double n, int v) {
            this(n, v, (long) getFractionalDigits(n, v));
        }

        private static int getFractionalDigits(double n, int v) {
            if (v == 0) {
                return 0;
            }
            if (n < 0.0d) {
                n = -n;
            }
            int baseFactor2 = (int) Math.pow(10.0d, (double) v);
            return (int) (Math.round(((double) baseFactor2) * n) % ((long) baseFactor2));
        }

        @Deprecated
        public FixedDecimal(double n) {
            this(n, decimals(n));
        }

        @Deprecated
        public FixedDecimal(long n) {
            this((double) n, 0);
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
                    if (temp % ((long) mask) != 0) {
                        return digits;
                    }
                    mask *= 10;
                }
                return 0;
            }
            String buf = String.format(Locale.ENGLISH, "%1.15e", new Object[]{Double.valueOf(n)});
            int ePos = buf.lastIndexOf(101);
            int expNumPos = ePos + 1;
            if (buf.charAt(expNumPos) == '+') {
                expNumPos++;
            }
            int numFractionDigits = (ePos - 2) - Integer.parseInt(buf.substring(expNumPos));
            if (numFractionDigits < 0) {
                return 0;
            }
            int i = ePos - 1;
            while (numFractionDigits > 0 && buf.charAt(i) == '0') {
                numFractionDigits--;
                i--;
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

        @Deprecated
        public double getPluralOperand(Operand operand) {
            switch (AnonymousClass2.$SwitchMap$com$ibm$icu$text$PluralRules$Operand[operand.ordinal()]) {
                case 1:
                    return this.source;
                case 2:
                    return (double) this.integerValue;
                case 3:
                    return (double) this.decimalDigits;
                case 4:
                    return (double) this.decimalDigitsWithoutTrailingZeros;
                case 5:
                    return (double) this.visibleDecimalDigitCount;
                case 6:
                    return (double) this.visibleDecimalDigitCountWithoutTrailingZeros;
                default:
                    return this.source;
            }
        }

        @Deprecated
        public static Operand getOperand(String t) {
            return Operand.valueOf(t);
        }

        @Deprecated
        public int compareTo(FixedDecimal other) {
            long j = this.integerValue;
            long j2 = other.integerValue;
            if (j == j2) {
                double d = this.source;
                double d2 = other.source;
                if (d == d2) {
                    int i = this.visibleDecimalDigitCount;
                    int i2 = other.visibleDecimalDigitCount;
                    if (i == i2) {
                        long diff = this.decimalDigits - other.decimalDigits;
                        if (diff == 0) {
                            return 0;
                        }
                        if (diff < 0) {
                            return -1;
                        }
                        return 1;
                    } else if (i < i2) {
                        return -1;
                    } else {
                        return 1;
                    }
                } else if (d < d2) {
                    return -1;
                } else {
                    return 1;
                }
            } else if (j < j2) {
                return -1;
            } else {
                return 1;
            }
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
            if (this.source == other.source && this.visibleDecimalDigitCount == other.visibleDecimalDigitCount && this.decimalDigits == other.decimalDigits) {
                return true;
            }
            return false;
        }

        @Deprecated
        public int hashCode() {
            return (int) (this.decimalDigits + ((long) ((this.visibleDecimalDigitCount + ((int) (this.source * 37.0d))) * 37)));
        }

        @Deprecated
        public String toString() {
            return String.format("%." + this.visibleDecimalDigitCount + "f", new Object[]{Double.valueOf(this.source)});
        }

        @Deprecated
        public boolean hasIntegerValue() {
            return this.hasIntegerValue;
        }

        @Deprecated
        public int intValue() {
            return (int) this.integerValue;
        }

        @Deprecated
        public long longValue() {
            return this.integerValue;
        }

        @Deprecated
        public float floatValue() {
            return (float) this.source;
        }

        @Deprecated
        public double doubleValue() {
            return this.isNegative ? -this.source : this.source;
        }

        @Deprecated
        public long getShiftedValue() {
            return (this.integerValue * ((long) this.baseFactor)) + this.decimalDigits;
        }

        private void writeObject(ObjectOutputStream out) throws IOException {
            throw new NotSerializableException();
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            throw new NotSerializableException();
        }

        @Deprecated
        public boolean isNaN() {
            return Double.isNaN(this.source);
        }

        @Deprecated
        public boolean isInfinite() {
            return Double.isInfinite(this.source);
        }
    }

    @Deprecated
    public static class FixedDecimalRange {
        @Deprecated
        public final FixedDecimal end;
        @Deprecated
        public final FixedDecimal start;

        @Deprecated
        public FixedDecimalRange(FixedDecimal start2, FixedDecimal end2) {
            if (start2.visibleDecimalDigitCount == end2.visibleDecimalDigitCount) {
                this.start = start2;
                this.end = end2;
                return;
            }
            throw new IllegalArgumentException("Ranges must have the same number of visible decimals: " + start2 + "~" + end2);
        }

        @Deprecated
        public String toString() {
            return this.start + (this.end == this.start ? "" : "~" + this.end);
        }
    }

    @Deprecated
    public static class FixedDecimalSamples {
        @Deprecated
        public final boolean bounded;
        @Deprecated
        public final SampleType sampleType;
        @Deprecated
        public final Set<FixedDecimalRange> samples;

        private FixedDecimalSamples(SampleType sampleType2, Set<FixedDecimalRange> samples2, boolean bounded2) {
            this.sampleType = sampleType2;
            this.samples = samples2;
            this.bounded = bounded2;
        }

        static FixedDecimalSamples parse(String source) {
            SampleType sampleType2;
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
                if (range.equals("…") || range.equals("...")) {
                    bounded2 = false;
                    haveBound = true;
                } else if (!haveBound) {
                    String[] rangeParts = PluralRules.TILDE_SEPARATED.split(range);
                    switch (rangeParts.length) {
                        case 1:
                            FixedDecimal sample = new FixedDecimal(rangeParts[0]);
                            checkDecimal(sampleType2, sample);
                            samples2.add(new FixedDecimalRange(sample, sample));
                            break;
                        case 2:
                            FixedDecimal start = new FixedDecimal(rangeParts[0]);
                            FixedDecimal end = new FixedDecimal(rangeParts[1]);
                            checkDecimal(sampleType2, start);
                            checkDecimal(sampleType2, end);
                            samples2.add(new FixedDecimalRange(start, end));
                            break;
                        default:
                            throw new IllegalArgumentException("Ill-formed number range: " + range);
                    }
                } else {
                    throw new IllegalArgumentException("Can only have … at the end of samples: " + range);
                }
            }
            return new FixedDecimalSamples(sampleType2, Collections.unmodifiableSet(samples2), bounded2);
        }

        private static void checkDecimal(SampleType sampleType2, FixedDecimal sample) {
            boolean z = true;
            boolean z2 = sampleType2 == SampleType.INTEGER;
            if (sample.getVisibleDecimalDigitCount() != 0) {
                z = false;
            }
            if (z2 != z) {
                throw new IllegalArgumentException("Ill-formed number range: " + sample);
            }
        }

        @Deprecated
        public Set<Double> addSamples(Set<Double> result) {
            for (FixedDecimalRange item : this.samples) {
                long startDouble = item.start.getShiftedValue();
                long endDouble = item.end.getShiftedValue();
                for (long d = startDouble; d <= endDouble; d++) {
                    result.add(Double.valueOf(((double) d) / ((double) item.start.baseFactor)));
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
                b.append(", …");
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

    static class SimpleTokenizer {
        static final UnicodeSet BREAK_AND_IGNORE = new UnicodeSet(9, 10, 12, 13, 32, 32).freeze();
        static final UnicodeSet BREAK_AND_KEEP = new UnicodeSet(33, 33, 37, 37, 44, 44, 46, 46, 61, 61).freeze();

        SimpleTokenizer() {
        }

        static String[] split(String source) {
            int last = -1;
            List<String> result = new ArrayList<>();
            for (int i = 0; i < source.length(); i++) {
                char ch = source.charAt(i);
                if (BREAK_AND_IGNORE.contains((int) ch)) {
                    if (last >= 0) {
                        result.add(source.substring(last, i));
                        last = -1;
                    }
                } else if (BREAK_AND_KEEP.contains((int) ch)) {
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

    /* JADX WARNING: Removed duplicated region for block: B:134:0x0254 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00f8  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x012a  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x018a  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0197  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.ibm.icu.text.PluralRules.Constraint parseConstraint(java.lang.String r39) throws java.text.ParseException {
        /*
            r0 = 0
            java.util.regex.Pattern r1 = OR_SEPARATED
            r2 = r39
            java.lang.String[] r1 = r1.split(r2)
            r3 = 0
            r4 = r3
            r3 = r0
        L_0x000c:
            int r0 = r1.length
            if (r4 >= r0) goto L_0x02cf
            r0 = 0
            java.util.regex.Pattern r5 = AND_SEPARATED
            r6 = r1[r4]
            java.lang.String[] r5 = r5.split(r6)
            r6 = 0
            r7 = r6
            r6 = r0
        L_0x001b:
            int r0 = r5.length
            if (r7 >= r0) goto L_0x02b0
            com.ibm.icu.text.PluralRules$Constraint r8 = NO_CONSTRAINT
            r0 = r5[r7]
            java.lang.String r9 = r0.trim()
            java.lang.String[] r10 = com.ibm.icu.text.PluralRules.SimpleTokenizer.split(r9)
            r11 = 0
            r12 = 1
            r13 = 1
            r14 = 4890909195324358656(0x43e0000000000000, double:9.223372036854776E18)
            r16 = -4332462841530417152(0xc3e0000000000000, double:-9.223372036854776E18)
            r18 = 0
            r0 = 0
            r19 = r1
            int r1 = r0 + 1
            r2 = r10[r0]
            r20 = 0
            com.ibm.icu.text.PluralRules$Operand r24 = com.ibm.icu.text.PluralRules.FixedDecimal.getOperand(r2)     // Catch:{ Exception -> 0x029b }
            int r0 = r10.length
            if (r1 >= r0) goto L_0x0272
            int r0 = r1 + 1
            r1 = r10[r1]
            java.lang.String r2 = "mod"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L_0x0058
            java.lang.String r2 = "%"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0066
        L_0x0058:
            int r2 = r0 + 1
            r0 = r10[r0]
            int r11 = java.lang.Integer.parseInt(r0)
            int r0 = r2 + 1
            java.lang.String r1 = nextToken(r10, r2, r9)
        L_0x0066:
            java.lang.String r2 = "not"
            boolean r21 = r2.equals(r1)
            r31 = r5
            java.lang.String r5 = "="
            if (r21 == 0) goto L_0x008c
            r21 = r12 ^ 1
            r12 = r21
            int r21 = r0 + 1
            java.lang.String r1 = nextToken(r10, r0, r9)
            boolean r0 = r5.equals(r1)
            if (r0 != 0) goto L_0x0087
            r32 = r8
            r0 = r21
            goto L_0x00ac
        L_0x0087:
            java.text.ParseException r0 = unexpected(r1, r9)
            throw r0
        L_0x008c:
            r32 = r8
            java.lang.String r8 = "!"
            boolean r8 = r8.equals(r1)
            if (r8 == 0) goto L_0x00ac
            r8 = r12 ^ 1
            r12 = r8
            int r8 = r0 + 1
            java.lang.String r1 = nextToken(r10, r0, r9)
            boolean r0 = r5.equals(r1)
            if (r0 == 0) goto L_0x00a7
            r0 = r8
            goto L_0x00ac
        L_0x00a7:
            java.text.ParseException r0 = unexpected(r1, r9)
            throw r0
        L_0x00ac:
            java.lang.String r8 = "is"
            boolean r21 = r8.equals(r1)
            if (r21 != 0) goto L_0x00da
            r21 = r13
            java.lang.String r13 = "in"
            boolean r13 = r13.equals(r1)
            if (r13 != 0) goto L_0x00dc
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L_0x00c5
            goto L_0x00dc
        L_0x00c5:
            java.lang.String r5 = "within"
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L_0x00d5
            r13 = 0
            int r5 = r0 + 1
            java.lang.String r0 = nextToken(r10, r0, r9)
            goto L_0x00f2
        L_0x00d5:
            java.text.ParseException r2 = unexpected(r1, r9)
            throw r2
        L_0x00da:
            r21 = r13
        L_0x00dc:
            boolean r20 = r8.equals(r1)
            if (r20 == 0) goto L_0x00ea
            if (r12 == 0) goto L_0x00e5
            goto L_0x00ea
        L_0x00e5:
            java.text.ParseException r2 = unexpected(r1, r9)
            throw r2
        L_0x00ea:
            int r5 = r0 + 1
            java.lang.String r0 = nextToken(r10, r0, r9)
            r13 = r21
        L_0x00f2:
            boolean r1 = r2.equals(r0)
            if (r1 == 0) goto L_0x010f
            if (r20 != 0) goto L_0x0102
            if (r12 == 0) goto L_0x00fd
            goto L_0x0102
        L_0x00fd:
            java.text.ParseException r1 = unexpected(r0, r9)
            throw r1
        L_0x0102:
            if (r12 != 0) goto L_0x0106
            r1 = 1
            goto L_0x0107
        L_0x0106:
            r1 = 0
        L_0x0107:
            int r2 = r5 + 1
            java.lang.String r0 = nextToken(r10, r5, r9)
            r12 = r1
            r5 = r2
        L_0x010f:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r8 = r3
            r2 = r16
        L_0x0117:
            r33 = r7
            r34 = r8
            long r7 = java.lang.Long.parseLong(r0)
            r16 = r7
            r21 = r0
            int r0 = r10.length
            r35 = r4
            java.lang.String r4 = ","
            if (r5 >= r0) goto L_0x018a
            int r0 = r5 + 1
            java.lang.String r5 = nextToken(r10, r5, r9)
            r36 = r6
            java.lang.String r6 = "."
            boolean r21 = r5.equals(r6)
            if (r21 == 0) goto L_0x0174
            r37 = r13
            int r13 = r0 + 1
            java.lang.String r0 = nextToken(r10, r0, r9)
            boolean r5 = r0.equals(r6)
            if (r5 == 0) goto L_0x016f
            int r5 = r13 + 1
            java.lang.String r0 = nextToken(r10, r13, r9)
            long r16 = java.lang.Long.parseLong(r0)
            int r6 = r10.length
            if (r5 >= r6) goto L_0x016b
            int r6 = r5 + 1
            java.lang.String r0 = nextToken(r10, r5, r9)
            boolean r5 = r0.equals(r4)
            if (r5 == 0) goto L_0x0166
            r5 = r6
            r6 = r12
            r12 = r16
            goto L_0x0193
        L_0x0166:
            java.text.ParseException r4 = unexpected(r0, r9)
            throw r4
        L_0x016b:
            r6 = r12
            r12 = r16
            goto L_0x0193
        L_0x016f:
            java.text.ParseException r4 = unexpected(r0, r9)
            throw r4
        L_0x0174:
            r37 = r13
            boolean r6 = r5.equals(r4)
            if (r6 == 0) goto L_0x0185
            r6 = r12
            r12 = r16
            r38 = r5
            r5 = r0
            r0 = r38
            goto L_0x0193
        L_0x0185:
            java.text.ParseException r4 = unexpected(r5, r9)
            throw r4
        L_0x018a:
            r36 = r6
            r37 = r13
            r6 = r12
            r12 = r16
            r0 = r21
        L_0x0193:
            int r16 = (r7 > r12 ? 1 : (r7 == r12 ? 0 : -1))
            if (r16 > 0) goto L_0x0254
            if (r11 == 0) goto L_0x01bf
            r16 = r4
            r17 = r5
            long r4 = (long) r11
            int r4 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r4 >= 0) goto L_0x01a3
            goto L_0x01c3
        L_0x01a3:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.StringBuilder r4 = r4.append(r12)
            java.lang.String r5 = ">mod="
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r4 = r4.append(r11)
            java.lang.String r4 = r4.toString()
            java.text.ParseException r4 = unexpected(r4, r9)
            throw r4
        L_0x01bf:
            r16 = r4
            r17 = r5
        L_0x01c3:
            java.lang.Long r4 = java.lang.Long.valueOf(r7)
            r1.add(r4)
            java.lang.Long r4 = java.lang.Long.valueOf(r12)
            r1.add(r4)
            double r4 = (double) r7
            double r14 = java.lang.Math.min(r14, r4)
            double r4 = (double) r12
            double r2 = java.lang.Math.max(r2, r4)
            int r4 = r10.length
            r5 = r17
            if (r5 < r4) goto L_0x0240
            r4 = r16
            boolean r4 = r0.equals(r4)
            if (r4 != 0) goto L_0x023b
            int r4 = r1.size()
            r7 = 2
            if (r4 != r7) goto L_0x01f4
            r4 = 0
            r18 = r4
            goto L_0x020f
        L_0x01f4:
            int r4 = r1.size()
            long[] r4 = new long[r4]
            r7 = 0
        L_0x01fb:
            int r8 = r4.length
            if (r7 >= r8) goto L_0x020d
            java.lang.Object r8 = r1.get(r7)
            java.lang.Long r8 = (java.lang.Long) r8
            long r12 = r8.longValue()
            r4[r7] = r12
            int r7 = r7 + 1
            goto L_0x01fb
        L_0x020d:
            r18 = r4
        L_0x020f:
            int r4 = (r14 > r2 ? 1 : (r14 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x021f
            if (r20 == 0) goto L_0x021f
            if (r6 == 0) goto L_0x0218
            goto L_0x021f
        L_0x0218:
            java.lang.String r4 = "is not <range>"
            java.text.ParseException r4 = unexpected(r4, r9)
            throw r4
        L_0x021f:
            com.ibm.icu.text.PluralRules$RangeConstraint r4 = new com.ibm.icu.text.PluralRules$RangeConstraint
            r21 = r4
            r22 = r11
            r23 = r6
            r25 = r37
            r26 = r14
            r28 = r2
            r30 = r18
            r21.<init>(r22, r23, r24, r25, r26, r28, r30)
            r8 = r4
            r16 = r2
            r1 = r5
            r12 = r6
            r13 = r37
            r2 = r0
            goto L_0x0280
        L_0x023b:
            java.text.ParseException r4 = unexpected(r0, r9)
            throw r4
        L_0x0240:
            int r4 = r5 + 1
            java.lang.String r0 = nextToken(r10, r5, r9)
            r5 = r4
            r12 = r6
            r7 = r33
            r8 = r34
            r4 = r35
            r6 = r36
            r13 = r37
            goto L_0x0117
        L_0x0254:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.StringBuilder r4 = r4.append(r7)
            r16 = r0
            java.lang.String r0 = "~"
            java.lang.StringBuilder r0 = r4.append(r0)
            java.lang.StringBuilder r0 = r0.append(r12)
            java.lang.String r0 = r0.toString()
            java.text.ParseException r0 = unexpected(r0, r9)
            throw r0
        L_0x0272:
            r34 = r3
            r35 = r4
            r31 = r5
            r36 = r6
            r33 = r7
            r32 = r8
            r21 = r13
        L_0x0280:
            if (r36 != 0) goto L_0x0285
            r0 = r8
            r6 = r0
            goto L_0x028d
        L_0x0285:
            com.ibm.icu.text.PluralRules$AndConstraint r0 = new com.ibm.icu.text.PluralRules$AndConstraint
            r3 = r36
            r0.<init>(r3, r8)
            r6 = r0
        L_0x028d:
            int r7 = r33 + 1
            r2 = r39
            r1 = r19
            r5 = r31
            r3 = r34
            r4 = r35
            goto L_0x001b
        L_0x029b:
            r0 = move-exception
            r34 = r3
            r35 = r4
            r31 = r5
            r3 = r6
            r33 = r7
            r32 = r8
            r21 = r13
            r4 = r0
            r0 = r4
            java.text.ParseException r4 = unexpected(r2, r9)
            throw r4
        L_0x02b0:
            r19 = r1
            r34 = r3
            r35 = r4
            r31 = r5
            r3 = r6
            r33 = r7
            if (r34 != 0) goto L_0x02bf
            r0 = r3
            goto L_0x02c7
        L_0x02bf:
            com.ibm.icu.text.PluralRules$OrConstraint r0 = new com.ibm.icu.text.PluralRules$OrConstraint
            r1 = r34
            r0.<init>(r1, r3)
            r3 = r0
        L_0x02c7:
            int r4 = r35 + 1
            r2 = r39
            r1 = r19
            goto L_0x000c
        L_0x02cf:
            r19 = r1
            r1 = r3
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.PluralRules.parseConstraint(java.lang.String):com.ibm.icu.text.PluralRules$Constraint");
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

    /* access modifiers changed from: private */
    public static Rule parseRule(String description) throws ParseException {
        Constraint constraint;
        if (description.length() == 0) {
            return DEFAULT_RULE;
        }
        String description2 = description.toLowerCase(Locale.ENGLISH);
        int x = description2.indexOf(58);
        if (x != -1) {
            String keyword = description2.substring(0, x).trim();
            if (isValidKeyword(keyword)) {
                String description3 = description2.substring(x + 1).trim();
                String[] constraintOrSamples = AT_SEPARATED.split(description3);
                FixedDecimalSamples integerSamples = null;
                FixedDecimalSamples decimalSamples = null;
                boolean z = true;
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
                        if (!(integerSamples.sampleType == SampleType.INTEGER && decimalSamples.sampleType == SampleType.DECIMAL)) {
                            throw new IllegalArgumentException("Must have @integer then @decimal in " + description3);
                        }
                    default:
                        throw new IllegalArgumentException("Too many samples in " + description3);
                }
                if (0 == 0) {
                    boolean isOther = keyword.equals(KEYWORD_OTHER);
                    if (constraintOrSamples[0].length() != 0) {
                        z = false;
                    }
                    if (isOther == z) {
                        if (isOther) {
                            constraint = NO_CONSTRAINT;
                        } else {
                            constraint = parseConstraint(constraintOrSamples[0]);
                        }
                        return new Rule(keyword, constraint, integerSamples, decimalSamples);
                    }
                    throw new IllegalArgumentException("The keyword 'other' must have no constraints, just samples.");
                }
                throw new IllegalArgumentException("Ill-formed samples—'@' characters.");
            }
            throw new ParseException("keyword '" + keyword + " is not valid", 0);
        }
        throw new ParseException("missing ':' in rule description '" + description2 + "'", 0);
    }

    private static RuleList parseRuleChain(String description) throws ParseException {
        RuleList result = new RuleList();
        if (description.endsWith(";")) {
            description = description.substring(0, description.length() - 1);
        }
        String[] rules2 = SEMI_SEPARATED.split(description);
        for (String trim : rules2) {
            Rule rule = parseRule(trim.trim());
            boolean unused = result.hasExplicitBoundingInfo = result.hasExplicitBoundingInfo | ((rule.integerSamples == null && rule.decimalSamples == null) ? false : true);
            result.addRule(rule);
        }
        return result.finish();
    }

    private static class RangeConstraint implements Constraint, Serializable {
        private static final long serialVersionUID = 1;
        private final boolean inRange;
        private final boolean integersOnly;
        private final double lowerBound;
        private final int mod;
        private final Operand operand;
        private final long[] range_list;
        private final double upperBound;

        RangeConstraint(int mod2, boolean inRange2, Operand operand2, boolean integersOnly2, double lowBound, double highBound, long[] vals) {
            this.mod = mod2;
            this.inRange = inRange2;
            this.integersOnly = integersOnly2;
            this.lowerBound = lowBound;
            this.upperBound = highBound;
            this.range_list = vals;
            this.operand = operand2;
        }

        public boolean isFulfilled(IFixedDecimal number) {
            double n = number.getPluralOperand(this.operand);
            if ((this.integersOnly && n - ((double) ((long) n)) != 0.0d) || (this.operand == Operand.j && number.getPluralOperand(Operand.v) != 0.0d)) {
                return !this.inRange;
            }
            int i = this.mod;
            if (i != 0) {
                n %= (double) i;
            }
            boolean test = n >= this.lowerBound && n <= this.upperBound;
            if (test && this.range_list != null) {
                boolean test2 = false;
                int i2 = 0;
                while (!test) {
                    long[] jArr = this.range_list;
                    if (i2 >= jArr.length) {
                        break;
                    }
                    test2 = n >= ((double) jArr[i2]) && n <= ((double) jArr[i2 + 1]);
                    i2 += 2;
                }
            }
            if (this.inRange == test) {
                return true;
            }
            return false;
        }

        public boolean isLimited(SampleType sampleType) {
            double d = this.lowerBound;
            boolean hasDecimals = (this.operand == Operand.v || this.operand == Operand.w || this.operand == Operand.f || this.operand == Operand.t) && this.inRange != ((d > this.upperBound ? 1 : (d == this.upperBound ? 0 : -1)) == 0 && (d > 0.0d ? 1 : (d == 0.0d ? 0 : -1)) == 0);
            switch (AnonymousClass2.$SwitchMap$com$ibm$icu$text$PluralRules$SampleType[sampleType.ordinal()]) {
                case 1:
                    if (hasDecimals) {
                        return true;
                    }
                    if ((this.operand == Operand.n || this.operand == Operand.i || this.operand == Operand.j) && this.mod == 0 && this.inRange) {
                        return true;
                    }
                    return false;
                case 2:
                    if ((!hasDecimals || this.operand == Operand.n || this.operand == Operand.j) && ((this.integersOnly || this.lowerBound == this.upperBound) && this.mod == 0 && this.inRange)) {
                        return true;
                    }
                    return false;
                default:
                    return false;
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x002f, code lost:
            if (r11.inRange != false) goto L_0x0046;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x003a, code lost:
            if (r11.inRange != false) goto L_0x0046;
         */
        /* JADX WARNING: Removed duplicated region for block: B:22:0x004d  */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x0069  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.String toString() {
            /*
                r11 = this;
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                com.ibm.icu.text.PluralRules$Operand r1 = r11.operand
                r0.append(r1)
                int r1 = r11.mod
                if (r1 == 0) goto L_0x0019
                java.lang.String r1 = " % "
                java.lang.StringBuilder r1 = r0.append(r1)
                int r2 = r11.mod
                r1.append(r2)
            L_0x0019:
                double r1 = r11.lowerBound
                double r3 = r11.upperBound
                int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
                r7 = 0
                r8 = 1
                if (r1 == 0) goto L_0x0025
                r1 = r8
                goto L_0x0026
            L_0x0025:
                r1 = r7
            L_0x0026:
                r9 = r1
                java.lang.String r1 = " = "
                java.lang.String r2 = " != "
                if (r9 != 0) goto L_0x0034
                boolean r3 = r11.inRange
                if (r3 == 0) goto L_0x0032
            L_0x0031:
                goto L_0x0046
            L_0x0032:
                r1 = r2
                goto L_0x0046
            L_0x0034:
                boolean r3 = r11.integersOnly
                if (r3 == 0) goto L_0x003d
                boolean r3 = r11.inRange
                if (r3 == 0) goto L_0x0032
                goto L_0x0031
            L_0x003d:
                boolean r1 = r11.inRange
                if (r1 == 0) goto L_0x0044
                java.lang.String r1 = " within "
                goto L_0x0046
            L_0x0044:
                java.lang.String r1 = " not within "
            L_0x0046:
                r0.append(r1)
                long[] r1 = r11.range_list
                if (r1 == 0) goto L_0x0069
                r1 = 0
                r10 = r1
            L_0x004f:
                long[] r1 = r11.range_list
                int r2 = r1.length
                if (r10 >= r2) goto L_0x0068
                r2 = r1[r10]
                double r2 = (double) r2
                int r4 = r10 + 1
                r4 = r1[r4]
                double r4 = (double) r4
                if (r10 == 0) goto L_0x0060
                r6 = r8
                goto L_0x0061
            L_0x0060:
                r6 = r7
            L_0x0061:
                r1 = r0
                com.ibm.icu.text.PluralRules.addRange(r1, r2, r4, r6)
                int r10 = r10 + 2
                goto L_0x004f
            L_0x0068:
                goto L_0x0072
            L_0x0069:
                double r2 = r11.lowerBound
                double r4 = r11.upperBound
                r6 = 0
                r1 = r0
                com.ibm.icu.text.PluralRules.addRange(r1, r2, r4, r6)
            L_0x0072:
                java.lang.String r1 = r0.toString()
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.PluralRules.RangeConstraint.toString():java.lang.String");
        }
    }

    /* renamed from: com.ibm.icu.text.PluralRules$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
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

    /* access modifiers changed from: private */
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

    private static abstract class BinaryConstraint implements Constraint, Serializable {
        private static final long serialVersionUID = 1;
        protected final Constraint a;
        protected final Constraint b;

        protected BinaryConstraint(Constraint a2, Constraint b2) {
            this.a = a2;
            this.b = b2;
        }
    }

    private static class AndConstraint extends BinaryConstraint {
        private static final long serialVersionUID = 7766999779862263523L;

        AndConstraint(Constraint a, Constraint b) {
            super(a, b);
        }

        public boolean isFulfilled(IFixedDecimal n) {
            return this.a.isFulfilled(n) && this.b.isFulfilled(n);
        }

        public boolean isLimited(SampleType sampleType) {
            return this.a.isLimited(sampleType) || this.b.isLimited(sampleType);
        }

        public String toString() {
            return this.a.toString() + " and " + this.b.toString();
        }
    }

    private static class OrConstraint extends BinaryConstraint {
        private static final long serialVersionUID = 1405488568664762222L;

        OrConstraint(Constraint a, Constraint b) {
            super(a, b);
        }

        public boolean isFulfilled(IFixedDecimal n) {
            return this.a.isFulfilled(n) || this.b.isFulfilled(n);
        }

        public boolean isLimited(SampleType sampleType) {
            return this.a.isLimited(sampleType) && this.b.isLimited(sampleType);
        }

        public String toString() {
            return this.a.toString() + " or " + this.b.toString();
        }
    }

    private static class Rule implements Serializable {
        private static final long serialVersionUID = 1;
        private final Constraint constraint;
        /* access modifiers changed from: private */
        public final FixedDecimalSamples decimalSamples;
        /* access modifiers changed from: private */
        public final FixedDecimalSamples integerSamples;
        private final String keyword;

        public Rule(String keyword2, Constraint constraint2, FixedDecimalSamples integerSamples2, FixedDecimalSamples decimalSamples2) {
            this.keyword = keyword2;
            this.constraint = constraint2;
            this.integerSamples = integerSamples2;
            this.decimalSamples = decimalSamples2;
        }

        public Rule and(Constraint c) {
            return new Rule(this.keyword, new AndConstraint(this.constraint, c), this.integerSamples, this.decimalSamples);
        }

        public Rule or(Constraint c) {
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
            String str;
            StringBuilder append = new StringBuilder().append(this.keyword).append(PluralRules.KEYWORD_RULE_SEPARATOR).append(this.constraint.toString());
            String str2 = "";
            if (this.integerSamples == null) {
                str = str2;
            } else {
                str = " " + this.integerSamples.toString();
            }
            StringBuilder append2 = append.append(str);
            if (this.decimalSamples != null) {
                str2 = " " + this.decimalSamples.toString();
            }
            return append2.append(str2).toString();
        }

        public int hashCode() {
            return this.keyword.hashCode() ^ this.constraint.hashCode();
        }

        public String getConstraint() {
            return this.constraint.toString();
        }
    }

    private static class RuleList implements Serializable {
        private static final long serialVersionUID = 1;
        /* access modifiers changed from: private */
        public boolean hasExplicitBoundingInfo;
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
            return selectRule(n).getKeyword();
        }

        public Set<String> getKeywords() {
            Set<String> result = new LinkedHashSet<>();
            for (Rule rule : this.rules) {
                result.add(rule.getKeyword());
            }
            return result;
        }

        public boolean isLimited(String keyword, SampleType sampleType) {
            if (!this.hasExplicitBoundingInfo) {
                return computeLimited(keyword, sampleType);
            }
            FixedDecimalSamples mySamples = getDecimalSamples(keyword, sampleType);
            if (mySamples == null) {
                return true;
            }
            return mySamples.bounded;
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
        if (toAddTo.contains(toAdd) || others.contains(toAdd)) {
            return false;
        }
        others.add(toAdd);
        return true;
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

    private PluralRules(RuleList rules2) {
        this.rules = rules2;
        this.keywords = Collections.unmodifiableSet(rules2.getKeywords());
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
        if (values == null || values.size() != 1) {
            return -0.00123456777d;
        }
        return values.iterator().next().doubleValue();
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
        if (!this.keywords.contains(keyword)) {
            return null;
        }
        Set<Double> result = new TreeSet<>();
        if (this.rules.hasExplicitBoundingInfo) {
            FixedDecimalSamples samples = this.rules.getDecimalSamples(keyword, sampleType);
            if (samples == null) {
                return Collections.unmodifiableSet(result);
            }
            return Collections.unmodifiableSet(samples.addSamples(result));
        }
        int maxCount = isLimited(keyword, sampleType) ? Integer.MAX_VALUE : 20;
        switch (AnonymousClass2.$SwitchMap$com$ibm$icu$text$PluralRules$SampleType[sampleType.ordinal()]) {
            case 1:
                int i = 0;
                while (i < 200 && addSample(keyword, Integer.valueOf(i), maxCount, result)) {
                    i++;
                }
                addSample(keyword, 1000000, maxCount, result);
                break;
            case 2:
                int i2 = 0;
                while (i2 < 2000 && addSample(keyword, new FixedDecimal(((double) i2) / 10.0d, 1), maxCount, result)) {
                    i2++;
                }
                addSample(keyword, new FixedDecimal(1000000.0d, 1), maxCount, result);
                break;
        }
        if (result.size() == 0) {
            return null;
        }
        return Collections.unmodifiableSet(result);
    }

    @Deprecated
    public boolean addSample(String keyword, Number sample, int maxCount, Set<Double> result) {
        if (!(sample instanceof FixedDecimal ? select((IFixedDecimal) (FixedDecimal) sample) : select(sample.doubleValue())).equals(keyword)) {
            return true;
        }
        result.add(Double.valueOf(sample.doubleValue()));
        if (maxCount - 1 < 0) {
            return false;
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
        if (originalSize <= explicits.size()) {
            HashSet<Double> subtractedSet = new HashSet<>(values);
            for (Double explicit : explicits) {
                subtractedSet.remove(Double.valueOf(explicit.doubleValue() - ((double) offset)));
            }
            if (subtractedSet.size() == 0) {
                return KeywordStatus.SUPPRESSED;
            }
            if (uniqueValue != null && subtractedSet.size() == 1) {
                uniqueValue.value = subtractedSet.iterator().next();
            }
            return originalSize == 1 ? KeywordStatus.UNIQUE : KeywordStatus.BOUNDED;
        } else if (originalSize != 1) {
            return KeywordStatus.BOUNDED;
        } else {
            if (uniqueValue != null) {
                uniqueValue.value = values.iterator().next();
            }
            return KeywordStatus.UNIQUE;
        }
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
