package com.ibm.icu.text;

import com.ibm.icu.impl.DontCareFieldPosition;
import com.ibm.icu.impl.ICUResourceBundle;
import com.ibm.icu.impl.SimpleCache;
import com.ibm.icu.impl.SimpleFormatterImpl;
import com.ibm.icu.impl.number.LongNameHandler;
import com.ibm.icu.number.FormattedNumber;
import com.ibm.icu.number.LocalizedNumberFormatter;
import com.ibm.icu.number.NumberFormatter;
import com.ibm.icu.number.Precision;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.ListFormatter;
import com.ibm.icu.util.Currency;
import com.ibm.icu.util.ICUUncheckedIOException;
import com.ibm.icu.util.Measure;
import com.ibm.icu.util.MeasureUnit;
import com.ibm.icu.util.TimeZone;
import com.ibm.icu.util.ULocale;
import com.ibm.icu.util.UResourceBundle;
import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.math.RoundingMode;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.UByte;

public class MeasureFormat extends UFormat {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int CURRENCY_FORMAT = 2;
    private static final int MEASURE_FORMAT = 0;
    static final int NUMBER_FORMATTER_CURRENCY = 2;
    static final int NUMBER_FORMATTER_INTEGER = 3;
    static final int NUMBER_FORMATTER_STANDARD = 1;
    private static final int TIME_UNIT_FORMAT = 1;
    private static final Map<MeasureUnit, Integer> hmsTo012;
    private static final Map<ULocale, String> localeIdToRangeFormat = new ConcurrentHashMap();
    private static final SimpleCache<ULocale, NumericFormatters> localeToNumericDurationFormatters = new SimpleCache<>();
    static final long serialVersionUID = -7182021401701778240L;
    private final transient FormatWidth formatWidth;
    private transient NumberFormatterCacheEntry formatter1;
    private transient NumberFormatterCacheEntry formatter2;
    private transient NumberFormatterCacheEntry formatter3;
    private final transient NumberFormat numberFormat;
    private final transient LocalizedNumberFormatter numberFormatter;
    private final transient NumericFormatters numericFormatters;
    private final transient PluralRules rules;

    static {
        HashMap hashMap = new HashMap();
        hmsTo012 = hashMap;
        hashMap.put(MeasureUnit.HOUR, 0);
        hashMap.put(MeasureUnit.MINUTE, 1);
        hashMap.put(MeasureUnit.SECOND, 2);
    }

    public enum FormatWidth {
        WIDE(ListFormatter.Style.DURATION, NumberFormatter.UnitWidth.FULL_NAME, NumberFormatter.UnitWidth.FULL_NAME),
        SHORT(ListFormatter.Style.DURATION_SHORT, NumberFormatter.UnitWidth.SHORT, NumberFormatter.UnitWidth.ISO_CODE),
        NARROW(ListFormatter.Style.DURATION_NARROW, NumberFormatter.UnitWidth.NARROW, NumberFormatter.UnitWidth.SHORT),
        NUMERIC(ListFormatter.Style.DURATION_NARROW, NumberFormatter.UnitWidth.NARROW, NumberFormatter.UnitWidth.SHORT),
        DEFAULT_CURRENCY(ListFormatter.Style.DURATION, NumberFormatter.UnitWidth.FULL_NAME, NumberFormatter.UnitWidth.SHORT);
        
        final NumberFormatter.UnitWidth currencyWidth;
        private final ListFormatter.Style listFormatterStyle;
        final NumberFormatter.UnitWidth unitWidth;

        private FormatWidth(ListFormatter.Style style, NumberFormatter.UnitWidth unitWidth2, NumberFormatter.UnitWidth currencyWidth2) {
            this.listFormatterStyle = style;
            this.unitWidth = unitWidth2;
            this.currencyWidth = currencyWidth2;
        }

        /* access modifiers changed from: package-private */
        public ListFormatter.Style getListFormatterStyle() {
            return this.listFormatterStyle;
        }
    }

    public static MeasureFormat getInstance(ULocale locale, FormatWidth formatWidth2) {
        return getInstance(locale, formatWidth2, NumberFormat.getInstance(locale));
    }

    public static MeasureFormat getInstance(Locale locale, FormatWidth formatWidth2) {
        return getInstance(ULocale.forLocale(locale), formatWidth2);
    }

    public static MeasureFormat getInstance(ULocale locale, FormatWidth formatWidth2, NumberFormat format) {
        return new MeasureFormat(locale, formatWidth2, format, (PluralRules) null, (NumericFormatters) null);
    }

    public static MeasureFormat getInstance(Locale locale, FormatWidth formatWidth2, NumberFormat format) {
        return getInstance(ULocale.forLocale(locale), formatWidth2, format);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v0, resolved type: com.ibm.icu.util.Measure} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.StringBuffer format(java.lang.Object r9, java.lang.StringBuffer r10, java.text.FieldPosition r11) {
        /*
            r8 = this;
            int r0 = r10.length()
            r1 = 0
            r11.setBeginIndex(r1)
            r11.setEndIndex(r1)
            boolean r1 = r9 instanceof java.util.Collection
            if (r1 == 0) goto L_0x0042
            r1 = r9
            java.util.Collection r1 = (java.util.Collection) r1
            int r2 = r1.size()
            com.ibm.icu.util.Measure[] r2 = new com.ibm.icu.util.Measure[r2]
            r3 = 0
            java.util.Iterator r4 = r1.iterator()
        L_0x001d:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x003e
            java.lang.Object r5 = r4.next()
            boolean r6 = r5 instanceof com.ibm.icu.util.Measure
            if (r6 == 0) goto L_0x0034
            int r6 = r3 + 1
            r7 = r5
            com.ibm.icu.util.Measure r7 = (com.ibm.icu.util.Measure) r7
            r2[r3] = r7
            r3 = r6
            goto L_0x001d
        L_0x0034:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r6 = r9.toString()
            r4.<init>(r6)
            throw r4
        L_0x003e:
            r8.formatMeasuresInternal(r10, r11, r2)
            goto L_0x0061
        L_0x0042:
            boolean r1 = r9 instanceof com.ibm.icu.util.Measure[]
            if (r1 == 0) goto L_0x004f
            r1 = r9
            com.ibm.icu.util.Measure[] r1 = (com.ibm.icu.util.Measure[]) r1
            com.ibm.icu.util.Measure[] r1 = (com.ibm.icu.util.Measure[]) r1
            r8.formatMeasuresInternal(r10, r11, r1)
            goto L_0x0061
        L_0x004f:
            boolean r1 = r9 instanceof com.ibm.icu.util.Measure
            if (r1 == 0) goto L_0x007a
            r1 = r9
            com.ibm.icu.util.Measure r1 = (com.ibm.icu.util.Measure) r1
            com.ibm.icu.number.FormattedNumber r1 = r8.formatMeasure(r1)
            r1.populateFieldPosition(r11)
            r1.appendTo(r10)
        L_0x0061:
            if (r0 <= 0) goto L_0x0079
            int r1 = r11.getEndIndex()
            if (r1 == 0) goto L_0x0079
            int r1 = r11.getBeginIndex()
            int r1 = r1 + r0
            r11.setBeginIndex(r1)
            int r1 = r11.getEndIndex()
            int r1 = r1 + r0
            r11.setEndIndex(r1)
        L_0x0079:
            return r10
        L_0x007a:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r2 = r9.toString()
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.MeasureFormat.format(java.lang.Object, java.lang.StringBuffer, java.text.FieldPosition):java.lang.StringBuffer");
    }

    public Measure parseObject(String source, ParsePosition pos) {
        throw new UnsupportedOperationException();
    }

    public final String formatMeasures(Measure... measures) {
        return formatMeasures(new StringBuilder(), DontCareFieldPosition.INSTANCE, measures).toString();
    }

    public StringBuilder formatMeasurePerUnit(Measure measure, MeasureUnit perUnit, StringBuilder appendTo, FieldPosition pos) {
        FormattedNumber result = getUnitFormatterFromCache(1, measure.getUnit(), perUnit).format(measure.getNumber());
        DecimalFormat.fieldPositionHelper(result, pos, appendTo.length());
        result.appendTo(appendTo);
        return appendTo;
    }

    public StringBuilder formatMeasures(StringBuilder appendTo, FieldPosition fpos, Measure... measures) {
        int prevLength = appendTo.length();
        formatMeasuresInternal(appendTo, fpos, measures);
        if (prevLength > 0 && fpos.getEndIndex() > 0) {
            fpos.setBeginIndex(fpos.getBeginIndex() + prevLength);
            fpos.setEndIndex(fpos.getEndIndex() + prevLength);
        }
        return appendTo;
    }

    private void formatMeasuresInternal(Appendable appendTo, FieldPosition fieldPosition, Measure... measures) {
        Number[] hms;
        if (measures.length != 0) {
            if (measures.length == 1) {
                FormattedNumber result = formatMeasure(measures[0]);
                result.populateFieldPosition(fieldPosition);
                result.appendTo(appendTo);
            } else if (this.formatWidth != FormatWidth.NUMERIC || (hms = toHMS(measures)) == null) {
                ListFormatter listFormatter = ListFormatter.getInstance(getLocale(), this.formatWidth.getListFormatterStyle());
                if (fieldPosition != DontCareFieldPosition.INSTANCE) {
                    formatMeasuresSlowTrack(listFormatter, appendTo, fieldPosition, measures);
                    return;
                }
                String[] results = new String[measures.length];
                for (int i = 0; i < measures.length; i++) {
                    if (i == measures.length - 1) {
                        results[i] = formatMeasure(measures[i]).toString();
                    } else {
                        results[i] = formatMeasureInteger(measures[i]).toString();
                    }
                }
                listFormatter.format(Arrays.asList(results), -1).appendTo(appendTo);
            } else {
                formatNumeric(hms, appendTo);
            }
        }
    }

    public String getUnitDisplayName(MeasureUnit unit) {
        return LongNameHandler.getUnitDisplayName(getLocale(), unit, this.formatWidth.unitWidth);
    }

    public final boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MeasureFormat)) {
            return false;
        }
        MeasureFormat rhs = (MeasureFormat) other;
        if (getWidth() != rhs.getWidth() || !getLocale().equals(rhs.getLocale()) || !getNumberFormatInternal().equals(rhs.getNumberFormatInternal())) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return (((getLocale().hashCode() * 31) + getNumberFormatInternal().hashCode()) * 31) + getWidth().hashCode();
    }

    public FormatWidth getWidth() {
        if (this.formatWidth == FormatWidth.DEFAULT_CURRENCY) {
            return FormatWidth.WIDE;
        }
        return this.formatWidth;
    }

    public final ULocale getLocale() {
        return getLocale(ULocale.VALID_LOCALE);
    }

    public NumberFormat getNumberFormat() {
        return (NumberFormat) this.numberFormat.clone();
    }

    /* access modifiers changed from: package-private */
    public NumberFormat getNumberFormatInternal() {
        return this.numberFormat;
    }

    public static MeasureFormat getCurrencyFormat(ULocale locale) {
        return new CurrencyFormat(locale);
    }

    public static MeasureFormat getCurrencyFormat(Locale locale) {
        return getCurrencyFormat(ULocale.forLocale(locale));
    }

    public static MeasureFormat getCurrencyFormat() {
        return getCurrencyFormat(ULocale.getDefault(ULocale.Category.FORMAT));
    }

    /* access modifiers changed from: package-private */
    public MeasureFormat withLocale(ULocale locale) {
        return getInstance(locale, getWidth());
    }

    /* access modifiers changed from: package-private */
    public MeasureFormat withNumberFormat(NumberFormat format) {
        return new MeasureFormat(getLocale(), this.formatWidth, format, this.rules, this.numericFormatters);
    }

    MeasureFormat(ULocale locale, FormatWidth formatWidth2) {
        this(locale, formatWidth2, (NumberFormat) null, (PluralRules) null, (NumericFormatters) null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: com.ibm.icu.text.MeasureFormat$NumericFormatters} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: com.ibm.icu.text.NumberFormat} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private MeasureFormat(com.ibm.icu.util.ULocale r3, com.ibm.icu.text.MeasureFormat.FormatWidth r4, com.ibm.icu.text.NumberFormat r5, com.ibm.icu.text.PluralRules r6, com.ibm.icu.text.MeasureFormat.NumericFormatters r7) {
        /*
            r2 = this;
            r2.<init>()
            r0 = 0
            r2.formatter1 = r0
            r2.formatter2 = r0
            r2.formatter3 = r0
            r2.setLocale(r3, r3)
            r2.formatWidth = r4
            if (r6 != 0) goto L_0x0015
            com.ibm.icu.text.PluralRules r6 = com.ibm.icu.text.PluralRules.forLocale((com.ibm.icu.util.ULocale) r3)
        L_0x0015:
            r2.rules = r6
            if (r5 != 0) goto L_0x001e
            com.ibm.icu.text.NumberFormat r5 = com.ibm.icu.text.NumberFormat.getInstance((com.ibm.icu.util.ULocale) r3)
            goto L_0x0025
        L_0x001e:
            java.lang.Object r0 = r5.clone()
            r5 = r0
            com.ibm.icu.text.NumberFormat r5 = (com.ibm.icu.text.NumberFormat) r5
        L_0x0025:
            r2.numberFormat = r5
            if (r7 != 0) goto L_0x003f
            com.ibm.icu.text.MeasureFormat$FormatWidth r0 = com.ibm.icu.text.MeasureFormat.FormatWidth.NUMERIC
            if (r4 != r0) goto L_0x003f
            com.ibm.icu.impl.SimpleCache<com.ibm.icu.util.ULocale, com.ibm.icu.text.MeasureFormat$NumericFormatters> r0 = localeToNumericDurationFormatters
            java.lang.Object r1 = r0.get(r3)
            r7 = r1
            com.ibm.icu.text.MeasureFormat$NumericFormatters r7 = (com.ibm.icu.text.MeasureFormat.NumericFormatters) r7
            if (r7 != 0) goto L_0x003f
            com.ibm.icu.text.MeasureFormat$NumericFormatters r7 = loadNumericFormatters(r3)
            r0.put(r3, r7)
        L_0x003f:
            r2.numericFormatters = r7
            boolean r0 = r5 instanceof com.ibm.icu.text.DecimalFormat
            if (r0 == 0) goto L_0x0057
            r0 = r5
            com.ibm.icu.text.DecimalFormat r0 = (com.ibm.icu.text.DecimalFormat) r0
            com.ibm.icu.number.LocalizedNumberFormatter r0 = r0.toNumberFormatter()
            com.ibm.icu.number.NumberFormatter$UnitWidth r1 = r4.unitWidth
            com.ibm.icu.number.NumberFormatterSettings r0 = r0.unitWidth(r1)
            com.ibm.icu.number.LocalizedNumberFormatter r0 = (com.ibm.icu.number.LocalizedNumberFormatter) r0
            r2.numberFormatter = r0
            return
        L_0x0057:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.MeasureFormat.<init>(com.ibm.icu.util.ULocale, com.ibm.icu.text.MeasureFormat$FormatWidth, com.ibm.icu.text.NumberFormat, com.ibm.icu.text.PluralRules, com.ibm.icu.text.MeasureFormat$NumericFormatters):void");
    }

    MeasureFormat(ULocale locale, FormatWidth formatWidth2, NumberFormat numberFormat2, PluralRules rules2) {
        this(locale, formatWidth2, numberFormat2, rules2, (NumericFormatters) null);
        if (formatWidth2 == FormatWidth.NUMERIC) {
            throw new IllegalArgumentException("The format width 'numeric' is not allowed by this constructor");
        }
    }

    static class NumericFormatters {
        private DateFormat hourMinute;
        private DateFormat hourMinuteSecond;
        private DateFormat minuteSecond;

        public NumericFormatters(DateFormat hourMinute2, DateFormat minuteSecond2, DateFormat hourMinuteSecond2) {
            this.hourMinute = hourMinute2;
            this.minuteSecond = minuteSecond2;
            this.hourMinuteSecond = hourMinuteSecond2;
        }

        public DateFormat getHourMinute() {
            return this.hourMinute;
        }

        public DateFormat getMinuteSecond() {
            return this.minuteSecond;
        }

        public DateFormat getHourMinuteSecond() {
            return this.hourMinuteSecond;
        }
    }

    private static NumericFormatters loadNumericFormatters(ULocale locale) {
        ICUResourceBundle r = UResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudt63b/unit", locale);
        return new NumericFormatters(loadNumericDurationFormat(r, "hm"), loadNumericDurationFormat(r, DateFormat.MINUTE_SECOND), loadNumericDurationFormat(r, "hms"));
    }

    static class NumberFormatterCacheEntry {
        LocalizedNumberFormatter formatter;
        MeasureUnit perUnit;
        int type;
        MeasureUnit unit;

        NumberFormatterCacheEntry() {
        }
    }

    private synchronized LocalizedNumberFormatter getUnitFormatterFromCache(int type, MeasureUnit unit, MeasureUnit perUnit) {
        LocalizedNumberFormatter formatter;
        NumberFormatterCacheEntry numberFormatterCacheEntry = this.formatter1;
        if (numberFormatterCacheEntry != null) {
            if (numberFormatterCacheEntry.type == type && this.formatter1.unit == unit && this.formatter1.perUnit == perUnit) {
                return this.formatter1.formatter;
            }
            NumberFormatterCacheEntry numberFormatterCacheEntry2 = this.formatter2;
            if (numberFormatterCacheEntry2 != null) {
                if (numberFormatterCacheEntry2.type == type && this.formatter2.unit == unit && this.formatter2.perUnit == perUnit) {
                    return this.formatter2.formatter;
                }
                NumberFormatterCacheEntry numberFormatterCacheEntry3 = this.formatter3;
                if (numberFormatterCacheEntry3 != null && numberFormatterCacheEntry3.type == type && this.formatter3.unit == unit && this.formatter3.perUnit == perUnit) {
                    return this.formatter3.formatter;
                }
            }
        }
        if (type == 1) {
            formatter = (LocalizedNumberFormatter) getNumberFormatter().unit(unit).perUnit(perUnit).unitWidth(this.formatWidth.unitWidth);
        } else if (type == 2) {
            formatter = (LocalizedNumberFormatter) NumberFormatter.withLocale(getLocale()).unit(unit).perUnit(perUnit).unitWidth(this.formatWidth.currencyWidth);
        } else if (type == 3) {
            formatter = (LocalizedNumberFormatter) getNumberFormatter().unit(unit).perUnit(perUnit).unitWidth(this.formatWidth.unitWidth).rounding(Precision.integer().withMode(RoundingMode.DOWN));
        } else {
            throw new AssertionError();
        }
        this.formatter3 = this.formatter2;
        this.formatter2 = this.formatter1;
        NumberFormatterCacheEntry numberFormatterCacheEntry4 = new NumberFormatterCacheEntry();
        this.formatter1 = numberFormatterCacheEntry4;
        numberFormatterCacheEntry4.type = type;
        this.formatter1.unit = unit;
        this.formatter1.perUnit = perUnit;
        this.formatter1.formatter = formatter;
        return formatter;
    }

    /* access modifiers changed from: package-private */
    public synchronized void clearCache() {
        this.formatter1 = null;
        this.formatter2 = null;
        this.formatter3 = null;
    }

    /* access modifiers changed from: package-private */
    public LocalizedNumberFormatter getNumberFormatter() {
        return this.numberFormatter;
    }

    private FormattedNumber formatMeasure(Measure measure) {
        MeasureUnit unit = measure.getUnit();
        if (unit instanceof Currency) {
            return getUnitFormatterFromCache(2, unit, (MeasureUnit) null).format(measure.getNumber());
        }
        return getUnitFormatterFromCache(1, unit, (MeasureUnit) null).format(measure.getNumber());
    }

    private FormattedNumber formatMeasureInteger(Measure measure) {
        return getUnitFormatterFromCache(3, measure.getUnit(), (MeasureUnit) null).format(measure.getNumber());
    }

    private void formatMeasuresSlowTrack(ListFormatter listFormatter, Appendable appendTo, FieldPosition fieldPosition, Measure... measures) {
        FormattedNumber result;
        String[] results = new String[measures.length];
        FieldPosition fpos = new FieldPosition(fieldPosition.getFieldAttribute(), fieldPosition.getField());
        int fieldPositionFoundIndex = -1;
        for (int i = 0; i < measures.length; i++) {
            if (i == measures.length - 1) {
                result = formatMeasure(measures[i]);
            } else {
                result = formatMeasureInteger(measures[i]);
            }
            if (fieldPositionFoundIndex == -1) {
                result.populateFieldPosition(fpos);
                if (fpos.getEndIndex() != 0) {
                    fieldPositionFoundIndex = i;
                }
            }
            results[i] = result.toString();
        }
        ListFormatter.FormattedListBuilder builder = listFormatter.format(Arrays.asList(results), fieldPositionFoundIndex);
        if (builder.getOffset() != -1) {
            fieldPosition.setBeginIndex(fpos.getBeginIndex() + builder.getOffset());
            fieldPosition.setEndIndex(fpos.getEndIndex() + builder.getOffset());
        }
        builder.appendTo(appendTo);
    }

    private static DateFormat loadNumericDurationFormat(ICUResourceBundle r, String type) {
        DateFormat result = new SimpleDateFormat(r.getWithFallback(String.format("durationUnits/%s", new Object[]{type})).getString().replace("h", DateFormat.HOUR24));
        result.setTimeZone(TimeZone.GMT_ZONE);
        return result;
    }

    private static Number[] toHMS(Measure[] measures) {
        Integer idxObj;
        int idx;
        Number[] result = new Number[3];
        int lastIdx = -1;
        for (Measure m : measures) {
            if (m.getNumber().doubleValue() < 0.0d || (idxObj = hmsTo012.get(m.getUnit())) == null || (idx = idxObj.intValue()) <= lastIdx) {
                return null;
            }
            lastIdx = idx;
            result[idx] = m.getNumber();
        }
        return result;
    }

    private void formatNumeric(Number[] hms, Appendable appendable) {
        Number[] numberArr = hms;
        int startIndex = -1;
        int endIndex = -1;
        for (int i = 0; i < numberArr.length; i++) {
            if (numberArr[i] != null) {
                int endIndex2 = i;
                if (startIndex == -1) {
                    endIndex = endIndex2;
                    startIndex = endIndex2;
                } else {
                    endIndex = endIndex2;
                }
            } else {
                numberArr[i] = 0;
            }
        }
        Date d = new Date((long) (((((Math.floor(numberArr[0].doubleValue()) * 60.0d) + Math.floor(numberArr[1].doubleValue())) * 60.0d) + Math.floor(numberArr[2].doubleValue())) * 1000.0d));
        if (startIndex == 0 && endIndex == 2) {
            formatNumeric(d, this.numericFormatters.getHourMinuteSecond(), DateFormat.Field.SECOND, numberArr[endIndex], appendable);
        } else if (startIndex == 1 && endIndex == 2) {
            formatNumeric(d, this.numericFormatters.getMinuteSecond(), DateFormat.Field.SECOND, numberArr[endIndex], appendable);
        } else if (startIndex == 0 && endIndex == 1) {
            formatNumeric(d, this.numericFormatters.getHourMinute(), DateFormat.Field.MINUTE, numberArr[endIndex], appendable);
        } else {
            throw new IllegalStateException();
        }
    }

    private void formatNumeric(Date duration, DateFormat formatter, DateFormat.Field smallestField, Number smallestAmount, Appendable appendTo) {
        String draft;
        FieldPosition intFieldPosition = new FieldPosition(0);
        FormattedNumber result = getNumberFormatter().format(smallestAmount);
        result.populateFieldPosition(intFieldPosition);
        String smallestAmountFormatted = result.toString();
        if (intFieldPosition.getBeginIndex() == 0 && intFieldPosition.getEndIndex() == 0) {
            throw new IllegalStateException();
        }
        FieldPosition smallestFieldPosition = new FieldPosition(smallestField);
        synchronized (formatter) {
            draft = formatter.format(duration, new StringBuffer(), smallestFieldPosition).toString();
        }
        try {
            if (smallestFieldPosition.getBeginIndex() == 0) {
                if (smallestFieldPosition.getEndIndex() == 0) {
                    appendTo.append(draft);
                    return;
                }
            }
            appendTo.append(draft, 0, smallestFieldPosition.getBeginIndex());
            appendTo.append(smallestAmountFormatted, 0, intFieldPosition.getBeginIndex());
            appendTo.append(draft, smallestFieldPosition.getBeginIndex(), smallestFieldPosition.getEndIndex());
            appendTo.append(smallestAmountFormatted, intFieldPosition.getEndIndex(), smallestAmountFormatted.length());
            appendTo.append(draft, smallestFieldPosition.getEndIndex(), draft.length());
        } catch (IOException e) {
            throw new ICUUncheckedIOException(e);
        }
    }

    /* access modifiers changed from: package-private */
    public Object toTimeUnitProxy() {
        return new MeasureProxy(getLocale(), this.formatWidth, getNumberFormatInternal(), 1);
    }

    /* access modifiers changed from: package-private */
    public Object toCurrencyProxy() {
        return new MeasureProxy(getLocale(), this.formatWidth, getNumberFormatInternal(), 2);
    }

    private Object writeReplace() throws ObjectStreamException {
        return new MeasureProxy(getLocale(), this.formatWidth, getNumberFormatInternal(), 0);
    }

    static class MeasureProxy implements Externalizable {
        private static final long serialVersionUID = -6033308329886716770L;
        private FormatWidth formatWidth;
        private HashMap<Object, Object> keyValues;
        private ULocale locale;
        private NumberFormat numberFormat;
        private int subClass;

        public MeasureProxy(ULocale locale2, FormatWidth width, NumberFormat numberFormat2, int subClass2) {
            this.locale = locale2;
            this.formatWidth = width;
            this.numberFormat = numberFormat2;
            this.subClass = subClass2;
            this.keyValues = new HashMap<>();
        }

        public MeasureProxy() {
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeByte(0);
            out.writeUTF(this.locale.toLanguageTag());
            out.writeByte(this.formatWidth.ordinal());
            out.writeObject(this.numberFormat);
            out.writeByte(this.subClass);
            out.writeObject(this.keyValues);
        }

        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            in.readByte();
            this.locale = ULocale.forLanguageTag(in.readUTF());
            this.formatWidth = MeasureFormat.fromFormatWidthOrdinal(in.readByte() & UByte.MAX_VALUE);
            NumberFormat numberFormat2 = (NumberFormat) in.readObject();
            this.numberFormat = numberFormat2;
            if (numberFormat2 != null) {
                this.subClass = in.readByte() & UByte.MAX_VALUE;
                HashMap<Object, Object> hashMap = (HashMap) in.readObject();
                this.keyValues = hashMap;
                if (hashMap == null) {
                    throw new InvalidObjectException("Missing optional values map.");
                }
                return;
            }
            throw new InvalidObjectException("Missing number format.");
        }

        private TimeUnitFormat createTimeUnitFormat() throws InvalidObjectException {
            int style;
            if (this.formatWidth == FormatWidth.WIDE) {
                style = 0;
            } else if (this.formatWidth == FormatWidth.SHORT) {
                style = 1;
            } else {
                throw new InvalidObjectException("Bad width: " + this.formatWidth);
            }
            TimeUnitFormat result = new TimeUnitFormat(this.locale, style);
            result.setNumberFormat(this.numberFormat);
            return result;
        }

        private Object readResolve() throws ObjectStreamException {
            switch (this.subClass) {
                case 0:
                    return MeasureFormat.getInstance(this.locale, this.formatWidth, this.numberFormat);
                case 1:
                    return createTimeUnitFormat();
                case 2:
                    return MeasureFormat.getCurrencyFormat(this.locale);
                default:
                    throw new InvalidObjectException("Unknown subclass: " + this.subClass);
            }
        }
    }

    /* access modifiers changed from: private */
    public static FormatWidth fromFormatWidthOrdinal(int ordinal) {
        FormatWidth[] values = FormatWidth.values();
        if (ordinal < 0 || ordinal >= values.length) {
            return FormatWidth.SHORT;
        }
        return values[ordinal];
    }

    @Deprecated
    public static String getRangeFormat(ULocale forLocale, FormatWidth width) {
        String resultString;
        String result;
        if (forLocale.getLanguage().equals("fr")) {
            return getRangeFormat(ULocale.ROOT, width);
        }
        Map<ULocale, String> map = localeIdToRangeFormat;
        String result2 = map.get(forLocale);
        if (result2 == null) {
            ICUResourceBundle rb = UResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudt63b", forLocale);
            ULocale realLocale = rb.getULocale();
            if (forLocale.equals(realLocale) || (result = map.get(forLocale)) == null) {
                try {
                    resultString = rb.getStringWithFallback("NumberElements/" + NumberingSystem.getInstance(forLocale).getName() + "/miscPatterns/range");
                } catch (MissingResourceException e) {
                    resultString = rb.getStringWithFallback("NumberElements/latn/patterns/range");
                }
                result2 = SimpleFormatterImpl.compileToStringMinMaxArguments(resultString, new StringBuilder(), 2, 2);
                Map<ULocale, String> map2 = localeIdToRangeFormat;
                map2.put(forLocale, result2);
                if (!forLocale.equals(realLocale)) {
                    map2.put(realLocale, result2);
                }
            } else {
                map.put(forLocale, result);
                return result;
            }
        }
        return result2;
    }
}
