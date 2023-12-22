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
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.UByte;

/* loaded from: classes.dex */
public class MeasureFormat extends UFormat {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int CURRENCY_FORMAT = 2;
    private static final int MEASURE_FORMAT = 0;
    static final int NUMBER_FORMATTER_CURRENCY = 2;
    static final int NUMBER_FORMATTER_INTEGER = 3;
    static final int NUMBER_FORMATTER_STANDARD = 1;
    private static final int TIME_UNIT_FORMAT = 1;
    private static final Map<MeasureUnit, Integer> hmsTo012;
    private static final Map<ULocale, String> localeIdToRangeFormat;
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
        localeIdToRangeFormat = new ConcurrentHashMap();
    }

    /* loaded from: classes.dex */
    public enum FormatWidth {
        WIDE(ListFormatter.Style.DURATION, NumberFormatter.UnitWidth.FULL_NAME, NumberFormatter.UnitWidth.FULL_NAME),
        SHORT(ListFormatter.Style.DURATION_SHORT, NumberFormatter.UnitWidth.SHORT, NumberFormatter.UnitWidth.ISO_CODE),
        NARROW(ListFormatter.Style.DURATION_NARROW, NumberFormatter.UnitWidth.NARROW, NumberFormatter.UnitWidth.SHORT),
        NUMERIC(ListFormatter.Style.DURATION_NARROW, NumberFormatter.UnitWidth.NARROW, NumberFormatter.UnitWidth.SHORT),
        DEFAULT_CURRENCY(ListFormatter.Style.DURATION, NumberFormatter.UnitWidth.FULL_NAME, NumberFormatter.UnitWidth.SHORT);
        
        final NumberFormatter.UnitWidth currencyWidth;
        private final ListFormatter.Style listFormatterStyle;
        final NumberFormatter.UnitWidth unitWidth;

        FormatWidth(ListFormatter.Style style, NumberFormatter.UnitWidth unitWidth, NumberFormatter.UnitWidth currencyWidth) {
            this.listFormatterStyle = style;
            this.unitWidth = unitWidth;
            this.currencyWidth = currencyWidth;
        }

        ListFormatter.Style getListFormatterStyle() {
            return this.listFormatterStyle;
        }
    }

    public static MeasureFormat getInstance(ULocale locale, FormatWidth formatWidth) {
        return getInstance(locale, formatWidth, NumberFormat.getInstance(locale));
    }

    public static MeasureFormat getInstance(Locale locale, FormatWidth formatWidth) {
        return getInstance(ULocale.forLocale(locale), formatWidth);
    }

    public static MeasureFormat getInstance(ULocale locale, FormatWidth formatWidth, NumberFormat format) {
        return new MeasureFormat(locale, formatWidth, format, null, null);
    }

    public static MeasureFormat getInstance(Locale locale, FormatWidth formatWidth, NumberFormat format) {
        return getInstance(ULocale.forLocale(locale), formatWidth, format);
    }

    @Override // java.text.Format
    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition fpos) {
        int prevLength = toAppendTo.length();
        fpos.setBeginIndex(0);
        fpos.setEndIndex(0);
        if (obj instanceof Collection) {
            Collection<?> coll = (Collection) obj;
            Measure[] measures = new Measure[coll.size()];
            int idx = 0;
            for (Object o : coll) {
                if (!(o instanceof Measure)) {
                    throw new IllegalArgumentException(obj.toString());
                }
                measures[idx] = (Measure) o;
                idx++;
            }
            formatMeasuresInternal(toAppendTo, fpos, measures);
        } else if (obj instanceof Measure[]) {
            formatMeasuresInternal(toAppendTo, fpos, (Measure[]) obj);
        } else if (obj instanceof Measure) {
            FormattedNumber result = formatMeasure((Measure) obj);
            result.populateFieldPosition(fpos);
            result.appendTo(toAppendTo);
        } else {
            throw new IllegalArgumentException(obj.toString());
        }
        if (prevLength > 0 && fpos.getEndIndex() != 0) {
            fpos.setBeginIndex(fpos.getBeginIndex() + prevLength);
            fpos.setEndIndex(fpos.getEndIndex() + prevLength);
        }
        return toAppendTo;
    }

    @Override // java.text.Format
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
        if (measures.length == 0) {
            return;
        }
        if (measures.length == 1) {
            FormattedNumber result = formatMeasure(measures[0]);
            result.populateFieldPosition(fieldPosition);
            result.appendTo(appendTo);
        } else if (this.formatWidth == FormatWidth.NUMERIC && (hms = toHMS(measures)) != null) {
            formatNumeric(hms, appendTo);
        } else {
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
            ListFormatter.FormattedListBuilder builder = listFormatter.format(Arrays.asList(results), -1);
            builder.appendTo(appendTo);
        }
    }

    public String getUnitDisplayName(MeasureUnit unit) {
        return LongNameHandler.getUnitDisplayName(getLocale(), unit, this.formatWidth.unitWidth);
    }

    public final boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof MeasureFormat) {
            MeasureFormat rhs = (MeasureFormat) other;
            return getWidth() == rhs.getWidth() && getLocale().equals(rhs.getLocale()) && getNumberFormatInternal().equals(rhs.getNumberFormatInternal());
        }
        return false;
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

    NumberFormat getNumberFormatInternal() {
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

    MeasureFormat withLocale(ULocale locale) {
        return getInstance(locale, getWidth());
    }

    MeasureFormat withNumberFormat(NumberFormat format) {
        return new MeasureFormat(getLocale(), this.formatWidth, format, this.rules, this.numericFormatters);
    }

    MeasureFormat(ULocale locale, FormatWidth formatWidth) {
        this(locale, formatWidth, null, null, null);
    }

    private MeasureFormat(ULocale locale, FormatWidth formatWidth, NumberFormat numberFormat, PluralRules rules, NumericFormatters formatters) {
        NumberFormat numberFormat2;
        this.formatter1 = null;
        this.formatter2 = null;
        this.formatter3 = null;
        setLocale(locale, locale);
        this.formatWidth = formatWidth;
        this.rules = rules == null ? PluralRules.forLocale(locale) : rules;
        if (numberFormat == null) {
            numberFormat2 = NumberFormat.getInstance(locale);
        } else {
            numberFormat2 = (NumberFormat) numberFormat.clone();
        }
        this.numberFormat = numberFormat2;
        if (formatters == null && formatWidth == FormatWidth.NUMERIC) {
            SimpleCache<ULocale, NumericFormatters> simpleCache = localeToNumericDurationFormatters;
            formatters = (NumericFormatters) simpleCache.get(locale);
            if (formatters == null) {
                formatters = loadNumericFormatters(locale);
                simpleCache.put(locale, formatters);
            }
        }
        this.numericFormatters = formatters;
        if (!(numberFormat2 instanceof DecimalFormat)) {
            throw new IllegalArgumentException();
        }
        this.numberFormatter = ((DecimalFormat) numberFormat2).toNumberFormatter().unitWidth(formatWidth.unitWidth);
    }

    MeasureFormat(ULocale locale, FormatWidth formatWidth, NumberFormat numberFormat, PluralRules rules) {
        this(locale, formatWidth, numberFormat, rules, null);
        if (formatWidth == FormatWidth.NUMERIC) {
            throw new IllegalArgumentException("The format width 'numeric' is not allowed by this constructor");
        }
    }

    /* loaded from: classes.dex */
    static class NumericFormatters {
        private DateFormat hourMinute;
        private DateFormat hourMinuteSecond;
        private DateFormat minuteSecond;

        public NumericFormatters(DateFormat hourMinute, DateFormat minuteSecond, DateFormat hourMinuteSecond) {
            this.hourMinute = hourMinute;
            this.minuteSecond = minuteSecond;
            this.hourMinuteSecond = hourMinuteSecond;
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

    /* loaded from: classes.dex */
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
        } else if (type != 3) {
            throw new AssertionError();
        } else {
            formatter = getNumberFormatter().unit(unit).perUnit(perUnit).unitWidth(this.formatWidth.unitWidth).rounding(Precision.integer().withMode(RoundingMode.DOWN));
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

    synchronized void clearCache() {
        this.formatter1 = null;
        this.formatter2 = null;
        this.formatter3 = null;
    }

    LocalizedNumberFormatter getNumberFormatter() {
        return this.numberFormatter;
    }

    private FormattedNumber formatMeasure(Measure measure) {
        MeasureUnit unit = measure.getUnit();
        if (unit instanceof Currency) {
            return getUnitFormatterFromCache(2, unit, null).format(measure.getNumber());
        }
        return getUnitFormatterFromCache(1, unit, null).format(measure.getNumber());
    }

    private FormattedNumber formatMeasureInteger(Measure measure) {
        return getUnitFormatterFromCache(3, measure.getUnit(), null).format(measure.getNumber());
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
        DateFormat result = new SimpleDateFormat(r.getWithFallback(String.format("durationUnits/%s", type)).getString().replace("h", DateFormat.HOUR24));
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

    /* JADX WARN: Incorrect condition in loop: B:4:0x0009 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void formatNumeric(Number[] hms, Appendable appendable) {
        int startIndex = -1;
        int endIndex = -1;
        for (int i = 0; i < startIndex; i++) {
            if (hms[i] != null) {
                int endIndex2 = i;
                if (startIndex != -1) {
                    endIndex = endIndex2;
                } else {
                    endIndex = endIndex2;
                    startIndex = endIndex2;
                }
            } else {
                hms[i] = 0;
            }
        }
        long millis = (long) (((((Math.floor(hms[0].doubleValue()) * 60.0d) + Math.floor(hms[1].doubleValue())) * 60.0d) + Math.floor(hms[2].doubleValue())) * 1000.0d);
        Date d = new Date(millis);
        if (startIndex == 0 && endIndex == 2) {
            formatNumeric(d, this.numericFormatters.getHourMinuteSecond(), DateFormat.Field.SECOND, hms[endIndex], appendable);
        } else if (startIndex == 1 && endIndex == 2) {
            formatNumeric(d, this.numericFormatters.getMinuteSecond(), DateFormat.Field.SECOND, hms[endIndex], appendable);
        } else if (startIndex == 0 && endIndex == 1) {
            formatNumeric(d, this.numericFormatters.getHourMinute(), DateFormat.Field.MINUTE, hms[endIndex], appendable);
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
            if (smallestFieldPosition.getBeginIndex() == 0 && smallestFieldPosition.getEndIndex() == 0) {
                appendTo.append(draft);
                return;
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

    Object toTimeUnitProxy() {
        return new MeasureProxy(getLocale(), this.formatWidth, getNumberFormatInternal(), 1);
    }

    Object toCurrencyProxy() {
        return new MeasureProxy(getLocale(), this.formatWidth, getNumberFormatInternal(), 2);
    }

    private Object writeReplace() throws ObjectStreamException {
        return new MeasureProxy(getLocale(), this.formatWidth, getNumberFormatInternal(), 0);
    }

    /* loaded from: classes.dex */
    static class MeasureProxy implements Externalizable {
        private static final long serialVersionUID = -6033308329886716770L;
        private FormatWidth formatWidth;
        private HashMap<Object, Object> keyValues;
        private ULocale locale;
        private NumberFormat numberFormat;
        private int subClass;

        public MeasureProxy(ULocale locale, FormatWidth width, NumberFormat numberFormat, int subClass) {
            this.locale = locale;
            this.formatWidth = width;
            this.numberFormat = numberFormat;
            this.subClass = subClass;
            this.keyValues = new HashMap<>();
        }

        public MeasureProxy() {
        }

        @Override // java.io.Externalizable
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeByte(0);
            out.writeUTF(this.locale.toLanguageTag());
            out.writeByte(this.formatWidth.ordinal());
            out.writeObject(this.numberFormat);
            out.writeByte(this.subClass);
            out.writeObject(this.keyValues);
        }

        @Override // java.io.Externalizable
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            in.readByte();
            this.locale = ULocale.forLanguageTag(in.readUTF());
            this.formatWidth = MeasureFormat.fromFormatWidthOrdinal(in.readByte() & UByte.MAX_VALUE);
            NumberFormat numberFormat = (NumberFormat) in.readObject();
            this.numberFormat = numberFormat;
            if (numberFormat == null) {
                throw new InvalidObjectException("Missing number format.");
            }
            this.subClass = in.readByte() & UByte.MAX_VALUE;
            HashMap<Object, Object> hashMap = (HashMap) in.readObject();
            this.keyValues = hashMap;
            if (hashMap == null) {
                throw new InvalidObjectException("Missing optional values map.");
            }
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

    /* JADX INFO: Access modifiers changed from: private */
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
            if (!forLocale.equals(realLocale) && (result = map.get(forLocale)) != null) {
                map.put(forLocale, result);
                return result;
            }
            NumberingSystem ns = NumberingSystem.getInstance(forLocale);
            try {
                resultString = rb.getStringWithFallback("NumberElements/" + ns.getName() + "/miscPatterns/range");
            } catch (MissingResourceException e) {
                resultString = rb.getStringWithFallback("NumberElements/latn/patterns/range");
            }
            result2 = SimpleFormatterImpl.compileToStringMinMaxArguments(resultString, new StringBuilder(), 2, 2);
            Map<ULocale, String> map2 = localeIdToRangeFormat;
            map2.put(forLocale, result2);
            if (!forLocale.equals(realLocale)) {
                map2.put(realLocale, result2);
            }
        }
        return result2;
    }
}
