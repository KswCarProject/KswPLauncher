package com.ibm.icu.text;

import com.ibm.icu.impl.DateNumberFormat;
import com.ibm.icu.impl.DayPeriodRules;
import com.ibm.icu.impl.ICUCache;
import com.ibm.icu.impl.ICUResourceBundle;
import com.ibm.icu.impl.PatternProps;
import com.ibm.icu.impl.SimpleCache;
import com.ibm.icu.impl.SimpleFormatterImpl;
import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.DateFormatSymbols;
import com.ibm.icu.text.DisplayContext;
import com.ibm.icu.text.TimeZoneFormat;
import com.ibm.icu.util.BasicTimeZone;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.HebrewCalendar;
import com.ibm.icu.util.Output;
import com.ibm.icu.util.TimeZone;
import com.ibm.icu.util.TimeZoneTransition;
import com.ibm.icu.util.ULocale;
import com.ibm.icu.util.UResourceBundle;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.UUID;
import kotlin.text.Typography;

/* loaded from: classes.dex */
public class SimpleDateFormat extends DateFormat {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int DECIMAL_BUF_SIZE = 10;
    private static final String FALLBACKPATTERN = "yy/MM/dd HH:mm";
    private static final int HEBREW_CAL_CUR_MILLENIUM_END_YEAR = 6000;
    private static final int HEBREW_CAL_CUR_MILLENIUM_START_YEAR = 5000;
    private static final int ISOSpecialEra = -32000;
    private static final String NUMERIC_FORMAT_CHARS = "ADdFgHhKkmrSsuWwYy";
    private static final String NUMERIC_FORMAT_CHARS2 = "ceLMQq";
    private static final String SUPPRESS_NEGATIVE_PREFIX = "\uab00";
    static final int currentSerialVersion = 2;
    private static final int millisPerHour = 3600000;
    private static final long serialVersionUID = 4774881970558875024L;
    private transient BreakIterator capitalizationBrkIter;
    private transient char[] decDigits;
    private transient char[] decimalBuf;
    private transient long defaultCenturyBase;
    private Date defaultCenturyStart;
    private transient int defaultCenturyStartYear;
    private DateFormatSymbols formatData;
    private transient boolean hasMinute;
    private transient boolean hasSecond;
    private transient ULocale locale;
    private HashMap<String, NumberFormat> numberFormatters;
    private String override;
    private HashMap<Character, String> overrideMap;
    private String pattern;
    private transient Object[] patternItems;
    private int serialVersionOnStream;
    private volatile TimeZoneFormat tzFormat;
    private transient boolean useFastFormat;
    private transient boolean useLocalZeroPaddingNumberFormat;
    static boolean DelayedHebrewMonthCheck = false;
    private static final int[] CALENDAR_FIELD_TO_LEVEL = {0, 10, 20, 20, 30, 30, 20, 30, 30, 40, 50, 50, 60, 70, 80, 0, 0, 10, 30, 10, 0, 40, 0, 0};
    private static final int[] PATTERN_CHAR_TO_LEVEL = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 40, -1, -1, 20, 30, 30, 0, 50, -1, -1, 50, 20, 20, -1, 0, -1, 20, -1, 80, -1, 10, 0, 30, 0, 10, 0, -1, -1, -1, -1, -1, -1, 40, -1, 30, 30, 30, -1, 0, 50, -1, -1, 50, -1, 60, -1, -1, -1, 20, 10, 70, -1, 10, 0, 20, 0, 10, 0, -1, -1, -1, -1, -1};
    private static final boolean[] PATTERN_CHAR_IS_SYNTAX = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, false, false, false, false};
    private static ULocale cachedDefaultLocale = null;
    private static String cachedDefaultPattern = null;
    private static final int[] PATTERN_CHAR_TO_INDEX = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 22, 36, -1, 10, 9, 11, 0, 5, -1, -1, 16, 26, 2, -1, 31, -1, 27, -1, 8, -1, 30, 29, 13, 32, 18, 23, -1, -1, -1, -1, -1, -1, 14, 35, 25, 3, 19, -1, 21, 15, -1, -1, 4, -1, 6, -1, -1, -1, 28, 34, 7, -1, 20, 24, 12, 33, 1, 17, -1, -1, -1, -1, -1};
    private static final int[] PATTERN_INDEX_TO_CALENDAR_FIELD = {0, 1, 2, 5, 11, 11, 12, 13, 14, 7, 6, 8, 3, 4, 9, 10, 10, 15, 17, 18, 19, 20, 21, 15, 15, 18, 2, 2, 2, 15, 1, 15, 15, 15, 19, -1, -2};
    private static final int[] PATTERN_INDEX_TO_DATE_FORMAT_FIELD = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37};
    private static final DateFormat.Field[] PATTERN_INDEX_TO_DATE_FORMAT_ATTRIBUTE = {DateFormat.Field.ERA, DateFormat.Field.YEAR, DateFormat.Field.MONTH, DateFormat.Field.DAY_OF_MONTH, DateFormat.Field.HOUR_OF_DAY1, DateFormat.Field.HOUR_OF_DAY0, DateFormat.Field.MINUTE, DateFormat.Field.SECOND, DateFormat.Field.MILLISECOND, DateFormat.Field.DAY_OF_WEEK, DateFormat.Field.DAY_OF_YEAR, DateFormat.Field.DAY_OF_WEEK_IN_MONTH, DateFormat.Field.WEEK_OF_YEAR, DateFormat.Field.WEEK_OF_MONTH, DateFormat.Field.AM_PM, DateFormat.Field.HOUR1, DateFormat.Field.HOUR0, DateFormat.Field.TIME_ZONE, DateFormat.Field.YEAR_WOY, DateFormat.Field.DOW_LOCAL, DateFormat.Field.EXTENDED_YEAR, DateFormat.Field.JULIAN_DAY, DateFormat.Field.MILLISECONDS_IN_DAY, DateFormat.Field.TIME_ZONE, DateFormat.Field.TIME_ZONE, DateFormat.Field.DAY_OF_WEEK, DateFormat.Field.MONTH, DateFormat.Field.QUARTER, DateFormat.Field.QUARTER, DateFormat.Field.TIME_ZONE, DateFormat.Field.YEAR, DateFormat.Field.TIME_ZONE, DateFormat.Field.TIME_ZONE, DateFormat.Field.TIME_ZONE, DateFormat.Field.RELATED_YEAR, DateFormat.Field.AM_PM_MIDNIGHT_NOON, DateFormat.Field.FLEXIBLE_DAY_PERIOD, DateFormat.Field.TIME_SEPARATOR};
    private static ICUCache<String, Object[]> PARSED_PATTERN_CACHE = new SimpleCache();
    static final UnicodeSet DATE_PATTERN_TYPE = new UnicodeSet("[GyYuUQqMLlwWd]").m87freeze();

    /* loaded from: classes.dex */
    private enum ContextValue {
        UNKNOWN,
        CAPITALIZATION_FOR_MIDDLE_OF_SENTENCE,
        CAPITALIZATION_FOR_BEGINNING_OF_SENTENCE,
        CAPITALIZATION_FOR_UI_LIST_OR_MENU,
        CAPITALIZATION_FOR_STANDALONE
    }

    private static int getLevelFromChar(char ch) {
        int[] iArr = PATTERN_CHAR_TO_LEVEL;
        if (ch < iArr.length) {
            return iArr[ch & '\u00ff'];
        }
        return -1;
    }

    private static boolean isSyntaxChar(char ch) {
        boolean[] zArr = PATTERN_CHAR_IS_SYNTAX;
        if (ch < zArr.length) {
            return zArr[ch & '\u00ff'];
        }
        return false;
    }

    public SimpleDateFormat() {
        this(getDefaultPattern(), null, null, null, null, true, null);
    }

    public SimpleDateFormat(String pattern) {
        this(pattern, null, null, null, null, true, null);
    }

    public SimpleDateFormat(String pattern, Locale loc) {
        this(pattern, null, null, null, ULocale.forLocale(loc), true, null);
    }

    public SimpleDateFormat(String pattern, ULocale loc) {
        this(pattern, null, null, null, loc, true, null);
    }

    public SimpleDateFormat(String pattern, String override, ULocale loc) {
        this(pattern, null, null, null, loc, false, override);
    }

    public SimpleDateFormat(String pattern, DateFormatSymbols formatData) {
        this(pattern, (DateFormatSymbols) formatData.clone(), null, null, null, true, null);
    }

    @Deprecated
    public SimpleDateFormat(String pattern, DateFormatSymbols formatData, ULocale loc) {
        this(pattern, (DateFormatSymbols) formatData.clone(), null, null, loc, true, null);
    }

    SimpleDateFormat(String pattern, DateFormatSymbols formatData, Calendar calendar, ULocale locale, boolean useFastFormat, String override) {
        this(pattern, (DateFormatSymbols) formatData.clone(), (Calendar) calendar.clone(), null, locale, useFastFormat, override);
    }

    private SimpleDateFormat(String pattern, DateFormatSymbols formatData, Calendar calendar, NumberFormat numberFormat, ULocale locale, boolean useFastFormat, String override) {
        this.serialVersionOnStream = 2;
        this.capitalizationBrkIter = null;
        this.pattern = pattern;
        this.formatData = formatData;
        this.calendar = calendar;
        this.numberFormat = numberFormat;
        this.locale = locale;
        this.useFastFormat = useFastFormat;
        this.override = override;
        initialize();
    }

    @Deprecated
    public static SimpleDateFormat getInstance(Calendar.FormatConfiguration formatConfig) {
        String ostr = formatConfig.getOverrideString();
        boolean useFast = ostr != null && ostr.length() > 0;
        return new SimpleDateFormat(formatConfig.getPatternString(), formatConfig.getDateFormatSymbols(), formatConfig.getCalendar(), null, formatConfig.getLocale(), useFast, formatConfig.getOverrideString());
    }

    private void initialize() {
        if (this.locale == null) {
            this.locale = ULocale.getDefault(ULocale.Category.FORMAT);
        }
        if (this.formatData == null) {
            this.formatData = new DateFormatSymbols(this.locale);
        }
        if (this.calendar == null) {
            this.calendar = Calendar.getInstance(this.locale);
        }
        if (this.numberFormat == null) {
            NumberingSystem ns = NumberingSystem.getInstance(this.locale);
            String digitString = ns.getDescription();
            if (ns.isAlgorithmic() || digitString.length() != 10) {
                this.numberFormat = NumberFormat.getInstance(this.locale);
            } else {
                String nsName = ns.getName();
                this.numberFormat = new DateNumberFormat(this.locale, digitString, nsName);
            }
        }
        if (this.numberFormat instanceof DecimalFormat) {
            fixNumberFormatForDates(this.numberFormat);
        }
        this.defaultCenturyBase = System.currentTimeMillis();
        setLocale(this.calendar.getLocale(ULocale.VALID_LOCALE), this.calendar.getLocale(ULocale.ACTUAL_LOCALE));
        initLocalZeroPaddingNumberFormat();
        if (this.override != null) {
            initNumberFormatters(this.locale);
        }
        parsePattern();
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0005, code lost:
        if (r7.tzFormat == null) goto L3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private synchronized void initializeTimeZoneFormat(boolean bForceUpdate) {
        if (!bForceUpdate) {
        }
        this.tzFormat = TimeZoneFormat.getInstance(this.locale);
        String digits = null;
        if (this.numberFormat instanceof DecimalFormat) {
            DecimalFormatSymbols decsym = ((DecimalFormat) this.numberFormat).getDecimalFormatSymbols();
            String[] strDigits = decsym.getDigitStringsLocal();
            StringBuilder digitsBuf = new StringBuilder();
            for (String digit : strDigits) {
                digitsBuf.append(digit);
            }
            digits = digitsBuf.toString();
        } else if (this.numberFormat instanceof DateNumberFormat) {
            digits = new String(this.numberFormat.getDigits());
        }
        if (digits != null && !this.tzFormat.getGMTOffsetDigits().equals(digits)) {
            if (this.tzFormat.isFrozen()) {
                this.tzFormat = this.tzFormat.m84cloneAsThawed();
            }
            this.tzFormat.setGMTOffsetDigits(digits);
        }
    }

    private TimeZoneFormat tzFormat() {
        if (this.tzFormat == null) {
            initializeTimeZoneFormat(false);
        }
        return this.tzFormat;
    }

    private static synchronized String getDefaultPattern() {
        String str;
        synchronized (SimpleDateFormat.class) {
            ULocale defaultLocale = ULocale.getDefault(ULocale.Category.FORMAT);
            if (!defaultLocale.equals(cachedDefaultLocale)) {
                cachedDefaultLocale = defaultLocale;
                Calendar cal = Calendar.getInstance(defaultLocale);
                try {
                    ICUResourceBundle rb = UResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudt63b", cachedDefaultLocale);
                    String resourcePath = "calendar/" + cal.getType() + "/DateTimePatterns";
                    ICUResourceBundle patternsRb = rb.findWithFallback(resourcePath);
                    if (patternsRb == null) {
                        patternsRb = rb.findWithFallback("calendar/gregorian/DateTimePatterns");
                    }
                    if (patternsRb != null && patternsRb.getSize() >= 9) {
                        int defaultIndex = 8;
                        if (patternsRb.getSize() >= 13) {
                            defaultIndex = 8 + 4;
                        }
                        String basePattern = patternsRb.getString(defaultIndex);
                        cachedDefaultPattern = SimpleFormatterImpl.formatRawPattern(basePattern, 2, 2, new CharSequence[]{patternsRb.getString(3), patternsRb.getString(7)});
                    }
                    cachedDefaultPattern = FALLBACKPATTERN;
                } catch (MissingResourceException e) {
                    cachedDefaultPattern = FALLBACKPATTERN;
                }
            }
            str = cachedDefaultPattern;
        }
        return str;
    }

    private void parseAmbiguousDatesAsAfter(Date startDate) {
        this.defaultCenturyStart = startDate;
        this.calendar.setTime(startDate);
        this.defaultCenturyStartYear = this.calendar.get(1);
    }

    private void initializeDefaultCenturyStart(long baseTime) {
        this.defaultCenturyBase = baseTime;
        Calendar tmpCal = (Calendar) this.calendar.clone();
        tmpCal.setTimeInMillis(baseTime);
        tmpCal.add(1, -80);
        this.defaultCenturyStart = tmpCal.getTime();
        this.defaultCenturyStartYear = tmpCal.get(1);
    }

    private Date getDefaultCenturyStart() {
        if (this.defaultCenturyStart == null) {
            initializeDefaultCenturyStart(this.defaultCenturyBase);
        }
        return this.defaultCenturyStart;
    }

    private int getDefaultCenturyStartYear() {
        if (this.defaultCenturyStart == null) {
            initializeDefaultCenturyStart(this.defaultCenturyBase);
        }
        return this.defaultCenturyStartYear;
    }

    public void set2DigitYearStart(Date startDate) {
        parseAmbiguousDatesAsAfter(startDate);
    }

    public Date get2DigitYearStart() {
        return getDefaultCenturyStart();
    }

    @Override // com.ibm.icu.text.DateFormat
    public void setContext(DisplayContext context) {
        super.setContext(context);
        if (this.capitalizationBrkIter == null) {
            if (context == DisplayContext.CAPITALIZATION_FOR_BEGINNING_OF_SENTENCE || context == DisplayContext.CAPITALIZATION_FOR_UI_LIST_OR_MENU || context == DisplayContext.CAPITALIZATION_FOR_STANDALONE) {
                this.capitalizationBrkIter = BreakIterator.getSentenceInstance(this.locale);
            }
        }
    }

    @Override // com.ibm.icu.text.DateFormat
    public StringBuffer format(Calendar cal, StringBuffer toAppendTo, FieldPosition pos) {
        TimeZone backupTZ = null;
        if (cal != this.calendar && !cal.getType().equals(this.calendar.getType())) {
            this.calendar.setTimeInMillis(cal.getTimeInMillis());
            backupTZ = this.calendar.getTimeZone();
            this.calendar.setTimeZone(cal.getTimeZone());
            cal = this.calendar;
        }
        StringBuffer result = format(cal, getContext(DisplayContext.Type.CAPITALIZATION), toAppendTo, pos, null);
        if (backupTZ != null) {
            this.calendar.setTimeZone(backupTZ);
        }
        return result;
    }

    private StringBuffer format(Calendar cal, DisplayContext capitalizationContext, StringBuffer toAppendTo, FieldPosition pos, List<FieldPosition> attributes) {
        int start;
        int start2;
        pos.setBeginIndex(0);
        pos.setEndIndex(0);
        Object[] items = getPatternItems();
        for (int i = 0; i < items.length; i++) {
            if (items[i] instanceof String) {
                toAppendTo.append((String) items[i]);
            } else {
                PatternItem item = (PatternItem) items[i];
                if (attributes == null) {
                    start = 0;
                } else {
                    int start3 = toAppendTo.length();
                    start = start3;
                }
                if (this.useFastFormat) {
                    start2 = start;
                    subFormat(toAppendTo, item.type, item.length, toAppendTo.length(), i, capitalizationContext, pos, cal);
                } else {
                    start2 = start;
                    toAppendTo.append(subFormat(item.type, item.length, toAppendTo.length(), i, capitalizationContext, pos, cal));
                }
                if (attributes != null) {
                    int end = toAppendTo.length();
                    if (end - start2 > 0) {
                        DateFormat.Field attr = patternCharToDateFormatField(item.type);
                        FieldPosition fp = new FieldPosition(attr);
                        fp.setBeginIndex(start2);
                        fp.setEndIndex(end);
                        attributes.add(fp);
                    }
                }
            }
        }
        return toAppendTo;
    }

    private static int getIndexFromChar(char ch) {
        int[] iArr = PATTERN_CHAR_TO_INDEX;
        if (ch < iArr.length) {
            return iArr[ch & '\u00ff'];
        }
        return -1;
    }

    protected DateFormat.Field patternCharToDateFormatField(char ch) {
        int patternCharIndex = getIndexFromChar(ch);
        if (patternCharIndex != -1) {
            return PATTERN_INDEX_TO_DATE_FORMAT_ATTRIBUTE[patternCharIndex];
        }
        return null;
    }

    protected String subFormat(char ch, int count, int beginOffset, FieldPosition pos, DateFormatSymbols fmtData, Calendar cal) throws IllegalArgumentException {
        return subFormat(ch, count, beginOffset, 0, DisplayContext.CAPITALIZATION_NONE, pos, cal);
    }

    @Deprecated
    protected String subFormat(char ch, int count, int beginOffset, int fieldNum, DisplayContext capitalizationContext, FieldPosition pos, Calendar cal) {
        StringBuffer buf = new StringBuffer();
        subFormat(buf, ch, count, beginOffset, fieldNum, capitalizationContext, pos, cal);
        return buf.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:229:0x0653  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0662  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x0751  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x075c  */
    /* JADX WARN: Removed duplicated region for block: B:328:0x088c  */
    /* JADX WARN: Removed duplicated region for block: B:329:0x089b  */
    @Deprecated
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void subFormat(StringBuffer buf, char ch, int count, int beginOffset, int fieldNum, DisplayContext capitalizationContext, FieldPosition pos, Calendar cal) {
        int value;
        int patternCharIndex;
        int bufstart;
        int value2;
        DateFormatSymbols.CapitalizationContextUsage capContextUsageType;
        int value3;
        int value4;
        char c;
        int value5;
        int value6;
        char c2;
        int patternCharIndex2;
        int value7;
        String result;
        DateFormatSymbols.CapitalizationContextUsage capContextUsageType2;
        String result2;
        String result3;
        String result4;
        String str;
        String result5;
        String result6;
        String result7;
        String toAppend;
        DayPeriodRules ruleSet;
        DayPeriodRules.DayPeriod periodType;
        DayPeriodRules.DayPeriod periodType2;
        String toAppend2;
        int bufstart2;
        int bufstart3 = buf.length();
        TimeZone tz = cal.getTimeZone();
        long date = cal.getTimeInMillis();
        int patternCharIndex3 = getIndexFromChar(ch);
        if (patternCharIndex3 == -1) {
            if (ch == 'l') {
                return;
            }
            throw new IllegalArgumentException("Illegal pattern character '" + ch + "' in \"" + this.pattern + Typography.quote);
        }
        int field = PATTERN_INDEX_TO_CALENDAR_FIELD[patternCharIndex3];
        if (field < 0) {
            value = 0;
        } else {
            value = patternCharIndex3 != 34 ? cal.get(field) : cal.getRelatedYear();
        }
        NumberFormat currentNumberFormat = getNumberFormat(ch);
        DateFormatSymbols.CapitalizationContextUsage capContextUsageType3 = DateFormatSymbols.CapitalizationContextUsage.OTHER;
        switch (patternCharIndex3) {
            case 0:
                patternCharIndex = patternCharIndex3;
                bufstart = bufstart3;
                int patternCharIndex4 = value;
                if (cal.getType().equals("chinese") || cal.getType().equals("dangi")) {
                    value2 = patternCharIndex4;
                    zeroPaddingNumber(currentNumberFormat, buf, patternCharIndex4, 1, 9);
                    value = value2;
                    capContextUsageType = capContextUsageType3;
                    break;
                } else if (count == 5) {
                    safeAppend(this.formatData.narrowEras, patternCharIndex4, buf);
                    value = patternCharIndex4;
                    capContextUsageType = DateFormatSymbols.CapitalizationContextUsage.ERA_NARROW;
                    break;
                } else if (count == 4) {
                    safeAppend(this.formatData.eraNames, patternCharIndex4, buf);
                    value = patternCharIndex4;
                    capContextUsageType = DateFormatSymbols.CapitalizationContextUsage.ERA_WIDE;
                    break;
                } else {
                    safeAppend(this.formatData.eras, patternCharIndex4, buf);
                    value = patternCharIndex4;
                    capContextUsageType = DateFormatSymbols.CapitalizationContextUsage.ERA_ABBREV;
                    break;
                }
                break;
            case 1:
            case 18:
                patternCharIndex = patternCharIndex3;
                bufstart = bufstart3;
                value3 = value;
                str = this.override;
                if (str == null && ((str.compareTo("hebr") == 0 || this.override.indexOf("y=hebr") >= 0) && value3 > HEBREW_CAL_CUR_MILLENIUM_START_YEAR && value3 < HEBREW_CAL_CUR_MILLENIUM_END_YEAR)) {
                    value2 = value3 - 5000;
                } else {
                    value2 = value3;
                }
                if (count != 2) {
                    zeroPaddingNumber(currentNumberFormat, buf, value2, 2, 2);
                } else {
                    zeroPaddingNumber(currentNumberFormat, buf, value2, count, Integer.MAX_VALUE);
                }
                value = value2;
                capContextUsageType = capContextUsageType3;
                break;
            case 2:
            case 26:
                int value8 = value;
                bufstart = bufstart3;
                if (!cal.getType().equals("hebrew")) {
                    value4 = value8;
                    c = 6;
                } else {
                    boolean isLeap = HebrewCalendar.isLeapYear(cal.get(1));
                    if (isLeap) {
                        value5 = value8;
                        if (value5 == 6 && count >= 3) {
                            value6 = 13;
                            if (isLeap) {
                                c2 = 6;
                                if (value6 >= 6 && count < 3) {
                                    c = 6;
                                    value4 = value6 - 1;
                                }
                            } else {
                                c2 = 6;
                            }
                            c = c2;
                            value4 = value6;
                        }
                    } else {
                        value5 = value8;
                    }
                    value6 = value5;
                    if (isLeap) {
                    }
                    c = c2;
                    value4 = value6;
                }
                int isLeapMonth = (this.formatData.leapMonthPatterns == null || this.formatData.leapMonthPatterns.length < 7) ? 0 : cal.get(22);
                String str2 = null;
                if (count != 5) {
                    if (count == 4) {
                        if (patternCharIndex3 == 2) {
                            String[] strArr = this.formatData.months;
                            if (isLeapMonth != 0) {
                                str2 = this.formatData.leapMonthPatterns[0];
                            }
                            safeAppendWithMonthPattern(strArr, value4, buf, str2);
                            patternCharIndex = patternCharIndex3;
                            value = value4;
                            capContextUsageType = DateFormatSymbols.CapitalizationContextUsage.MONTH_FORMAT;
                            break;
                        } else {
                            String[] strArr2 = this.formatData.standaloneMonths;
                            if (isLeapMonth != 0) {
                                str2 = this.formatData.leapMonthPatterns[3];
                            }
                            safeAppendWithMonthPattern(strArr2, value4, buf, str2);
                            patternCharIndex = patternCharIndex3;
                            value = value4;
                            capContextUsageType = DateFormatSymbols.CapitalizationContextUsage.MONTH_STANDALONE;
                            break;
                        }
                    } else if (count == 3) {
                        if (patternCharIndex3 == 2) {
                            String[] strArr3 = this.formatData.shortMonths;
                            if (isLeapMonth != 0) {
                                str2 = this.formatData.leapMonthPatterns[1];
                            }
                            safeAppendWithMonthPattern(strArr3, value4, buf, str2);
                            patternCharIndex = patternCharIndex3;
                            value = value4;
                            capContextUsageType = DateFormatSymbols.CapitalizationContextUsage.MONTH_FORMAT;
                            break;
                        } else {
                            String[] strArr4 = this.formatData.standaloneShortMonths;
                            if (isLeapMonth != 0) {
                                str2 = this.formatData.leapMonthPatterns[4];
                            }
                            safeAppendWithMonthPattern(strArr4, value4, buf, str2);
                            patternCharIndex = patternCharIndex3;
                            value = value4;
                            capContextUsageType = DateFormatSymbols.CapitalizationContextUsage.MONTH_STANDALONE;
                            break;
                        }
                    } else {
                        StringBuffer monthNumber = new StringBuffer();
                        patternCharIndex = patternCharIndex3;
                        int value9 = value4;
                        zeroPaddingNumber(currentNumberFormat, monthNumber, value4 + 1, count, Integer.MAX_VALUE);
                        String[] monthNumberStrings = {monthNumber.toString()};
                        if (isLeapMonth != 0) {
                            str2 = this.formatData.leapMonthPatterns[c];
                        }
                        safeAppendWithMonthPattern(monthNumberStrings, 0, buf, str2);
                        capContextUsageType = capContextUsageType3;
                        value = value9;
                        break;
                    }
                } else {
                    if (patternCharIndex3 == 2) {
                        String[] strArr5 = this.formatData.narrowMonths;
                        if (isLeapMonth != 0) {
                            str2 = this.formatData.leapMonthPatterns[2];
                        }
                        safeAppendWithMonthPattern(strArr5, value4, buf, str2);
                    } else {
                        String[] strArr6 = this.formatData.standaloneNarrowMonths;
                        if (isLeapMonth != 0) {
                            str2 = this.formatData.leapMonthPatterns[5];
                        }
                        safeAppendWithMonthPattern(strArr6, value4, buf, str2);
                    }
                    patternCharIndex = patternCharIndex3;
                    value = value4;
                    capContextUsageType = DateFormatSymbols.CapitalizationContextUsage.MONTH_NARROW;
                    break;
                }
                break;
            case 3:
            case 5:
            case 6:
            case 7:
            case 10:
            case 11:
            case 12:
            case 13:
            case 16:
            case 20:
            case 21:
            case 22:
            case 34:
            default:
                value2 = value;
                patternCharIndex = patternCharIndex3;
                bufstart = bufstart3;
                zeroPaddingNumber(currentNumberFormat, buf, value2, count, Integer.MAX_VALUE);
                value = value2;
                capContextUsageType = capContextUsageType3;
                break;
            case 4:
                bufstart = bufstart3;
                int patternCharIndex5 = value;
                if (patternCharIndex5 == 0) {
                    zeroPaddingNumber(currentNumberFormat, buf, cal.getMaximum(11) + 1, count, Integer.MAX_VALUE);
                    patternCharIndex = patternCharIndex3;
                    value2 = patternCharIndex5;
                } else {
                    zeroPaddingNumber(currentNumberFormat, buf, patternCharIndex5, count, Integer.MAX_VALUE);
                    patternCharIndex = patternCharIndex3;
                    value2 = patternCharIndex5;
                }
                value = value2;
                capContextUsageType = capContextUsageType3;
                break;
            case 8:
                bufstart = bufstart3;
                int patternCharIndex6 = value;
                this.numberFormat.setMinimumIntegerDigits(Math.min(3, count));
                this.numberFormat.setMaximumIntegerDigits(Integer.MAX_VALUE);
                if (count == 1) {
                    value = patternCharIndex6 / 100;
                } else if (count != 2) {
                    value = patternCharIndex6;
                } else {
                    value = patternCharIndex6 / 10;
                }
                FieldPosition p = new FieldPosition(-1);
                this.numberFormat.format(value, buf, p);
                if (count > 3) {
                    this.numberFormat.setMinimumIntegerDigits(count - 3);
                    this.numberFormat.format(0L, buf, p);
                }
                capContextUsageType = capContextUsageType3;
                patternCharIndex = patternCharIndex3;
                break;
            case 9:
                patternCharIndex2 = patternCharIndex3;
                bufstart = bufstart3;
                if (count != 5) {
                    safeAppend(this.formatData.narrowWeekdays, value, buf);
                    capContextUsageType = DateFormatSymbols.CapitalizationContextUsage.DAY_NARROW;
                    patternCharIndex = patternCharIndex2;
                    break;
                } else if (count == 4) {
                    safeAppend(this.formatData.weekdays, value, buf);
                    capContextUsageType = DateFormatSymbols.CapitalizationContextUsage.DAY_FORMAT;
                    patternCharIndex = patternCharIndex2;
                    break;
                } else if (count == 6 && this.formatData.shorterWeekdays != null) {
                    safeAppend(this.formatData.shorterWeekdays, value, buf);
                    capContextUsageType = DateFormatSymbols.CapitalizationContextUsage.DAY_FORMAT;
                    patternCharIndex = patternCharIndex2;
                    break;
                } else {
                    safeAppend(this.formatData.shortWeekdays, value, buf);
                    capContextUsageType = DateFormatSymbols.CapitalizationContextUsage.DAY_FORMAT;
                    patternCharIndex = patternCharIndex2;
                    break;
                }
                break;
            case 14:
                int value10 = value;
                bufstart = bufstart3;
                if (count >= 5) {
                    if (this.formatData.ampmsNarrow != null) {
                        safeAppend(this.formatData.ampmsNarrow, value10, buf);
                        value2 = value10;
                        patternCharIndex = patternCharIndex3;
                        value = value2;
                        capContextUsageType = capContextUsageType3;
                        break;
                    } else {
                        value7 = value10;
                    }
                } else {
                    value7 = value10;
                }
                safeAppend(this.formatData.ampms, value7, buf);
                value2 = value7;
                patternCharIndex = patternCharIndex3;
                value = value2;
                capContextUsageType = capContextUsageType3;
            case 15:
                int value11 = value;
                bufstart = bufstart3;
                if (value11 == 0) {
                    zeroPaddingNumber(currentNumberFormat, buf, cal.getLeastMaximum(10) + 1, count, Integer.MAX_VALUE);
                    patternCharIndex = patternCharIndex3;
                    value2 = value11;
                } else {
                    zeroPaddingNumber(currentNumberFormat, buf, value11, count, Integer.MAX_VALUE);
                    patternCharIndex = patternCharIndex3;
                    value2 = value11;
                }
                value = value2;
                capContextUsageType = capContextUsageType3;
                break;
            case 17:
                int value12 = value;
                bufstart = bufstart3;
                if (count < 4) {
                    result = tzFormat().format(TimeZoneFormat.Style.SPECIFIC_SHORT, tz, date);
                    capContextUsageType2 = DateFormatSymbols.CapitalizationContextUsage.METAZONE_SHORT;
                } else {
                    result = tzFormat().format(TimeZoneFormat.Style.SPECIFIC_LONG, tz, date);
                    capContextUsageType2 = DateFormatSymbols.CapitalizationContextUsage.METAZONE_LONG;
                }
                buf.append(result);
                capContextUsageType = capContextUsageType2;
                patternCharIndex = patternCharIndex3;
                value = value12;
                break;
            case 19:
                int value13 = value;
                patternCharIndex2 = patternCharIndex3;
                bufstart = bufstart3;
                if (count < 3) {
                    zeroPaddingNumber(currentNumberFormat, buf, value13, count, Integer.MAX_VALUE);
                    patternCharIndex = patternCharIndex2;
                    value2 = value13;
                    value = value2;
                    capContextUsageType = capContextUsageType3;
                    break;
                } else {
                    value = cal.get(7);
                    if (count != 5) {
                    }
                }
                break;
            case 23:
                int value14 = value;
                bufstart = bufstart3;
                if (count < 4) {
                    result2 = tzFormat().format(TimeZoneFormat.Style.ISO_BASIC_LOCAL_FULL, tz, date);
                } else if (count == 5) {
                    result2 = tzFormat().format(TimeZoneFormat.Style.ISO_EXTENDED_FULL, tz, date);
                } else {
                    result2 = tzFormat().format(TimeZoneFormat.Style.LOCALIZED_GMT, tz, date);
                }
                buf.append(result2);
                capContextUsageType = capContextUsageType3;
                patternCharIndex = patternCharIndex3;
                value = value14;
                break;
            case 24:
                int value15 = value;
                bufstart = bufstart3;
                if (count == 1) {
                    String result8 = tzFormat().format(TimeZoneFormat.Style.GENERIC_SHORT, tz, date);
                    capContextUsageType3 = DateFormatSymbols.CapitalizationContextUsage.METAZONE_SHORT;
                    result3 = result8;
                } else if (count != 4) {
                    result3 = null;
                } else {
                    String result9 = tzFormat().format(TimeZoneFormat.Style.GENERIC_LONG, tz, date);
                    capContextUsageType3 = DateFormatSymbols.CapitalizationContextUsage.METAZONE_LONG;
                    result3 = result9;
                }
                buf.append(result3);
                capContextUsageType = capContextUsageType3;
                patternCharIndex = patternCharIndex3;
                value = value15;
                break;
            case 25:
                int value16 = value;
                bufstart = bufstart3;
                if (count < 3) {
                    zeroPaddingNumber(currentNumberFormat, buf, value16, 1, Integer.MAX_VALUE);
                    patternCharIndex = patternCharIndex3;
                    value2 = value16;
                    value = value2;
                    capContextUsageType = capContextUsageType3;
                    break;
                } else {
                    value = cal.get(7);
                    if (count == 5) {
                        safeAppend(this.formatData.standaloneNarrowWeekdays, value, buf);
                        capContextUsageType = DateFormatSymbols.CapitalizationContextUsage.DAY_NARROW;
                        patternCharIndex = patternCharIndex3;
                        break;
                    } else if (count == 4) {
                        safeAppend(this.formatData.standaloneWeekdays, value, buf);
                        capContextUsageType = DateFormatSymbols.CapitalizationContextUsage.DAY_STANDALONE;
                        patternCharIndex = patternCharIndex3;
                        break;
                    } else if (count == 6 && this.formatData.standaloneShorterWeekdays != null) {
                        safeAppend(this.formatData.standaloneShorterWeekdays, value, buf);
                        capContextUsageType = DateFormatSymbols.CapitalizationContextUsage.DAY_STANDALONE;
                        patternCharIndex = patternCharIndex3;
                        break;
                    } else {
                        safeAppend(this.formatData.standaloneShortWeekdays, value, buf);
                        capContextUsageType = DateFormatSymbols.CapitalizationContextUsage.DAY_STANDALONE;
                        patternCharIndex = patternCharIndex3;
                        break;
                    }
                }
                break;
            case 27:
                int value17 = value;
                bufstart = bufstart3;
                if (count >= 4) {
                    safeAppend(this.formatData.quarters, value17 / 3, buf);
                    value2 = value17;
                    patternCharIndex = patternCharIndex3;
                } else if (count == 3) {
                    safeAppend(this.formatData.shortQuarters, value17 / 3, buf);
                    value2 = value17;
                    patternCharIndex = patternCharIndex3;
                } else {
                    zeroPaddingNumber(currentNumberFormat, buf, (value17 / 3) + 1, count, Integer.MAX_VALUE);
                    patternCharIndex = patternCharIndex3;
                    value2 = value17;
                }
                value = value2;
                capContextUsageType = capContextUsageType3;
                break;
            case 28:
                bufstart = bufstart3;
                int patternCharIndex7 = value;
                if (count >= 4) {
                    safeAppend(this.formatData.standaloneQuarters, patternCharIndex7 / 3, buf);
                    value2 = patternCharIndex7;
                    patternCharIndex = patternCharIndex3;
                } else if (count == 3) {
                    safeAppend(this.formatData.standaloneShortQuarters, patternCharIndex7 / 3, buf);
                    value2 = patternCharIndex7;
                    patternCharIndex = patternCharIndex3;
                } else {
                    zeroPaddingNumber(currentNumberFormat, buf, (patternCharIndex7 / 3) + 1, count, Integer.MAX_VALUE);
                    patternCharIndex = patternCharIndex3;
                    value2 = patternCharIndex7;
                }
                value = value2;
                capContextUsageType = capContextUsageType3;
                break;
            case 29:
                bufstart = bufstart3;
                int patternCharIndex8 = value;
                if (count == 1) {
                    String result10 = tzFormat().format(TimeZoneFormat.Style.ZONE_ID_SHORT, tz, date);
                    result4 = result10;
                } else if (count == 2) {
                    String result11 = tzFormat().format(TimeZoneFormat.Style.ZONE_ID, tz, date);
                    result4 = result11;
                } else if (count == 3) {
                    String result12 = tzFormat().format(TimeZoneFormat.Style.EXEMPLAR_LOCATION, tz, date);
                    result4 = result12;
                } else if (count != 4) {
                    result4 = null;
                } else {
                    String result13 = tzFormat().format(TimeZoneFormat.Style.GENERIC_LOCATION, tz, date);
                    capContextUsageType3 = DateFormatSymbols.CapitalizationContextUsage.ZONE_LONG;
                    result4 = result13;
                }
                buf.append(result4);
                value = patternCharIndex8;
                capContextUsageType = capContextUsageType3;
                patternCharIndex = patternCharIndex3;
                break;
            case 30:
                int value18 = value;
                bufstart = bufstart3;
                if (this.formatData.shortYearNames != null) {
                    value3 = value18;
                    if (value3 <= this.formatData.shortYearNames.length) {
                        safeAppend(this.formatData.shortYearNames, value3 - 1, buf);
                        value2 = value3;
                        patternCharIndex = patternCharIndex3;
                        value = value2;
                        capContextUsageType = capContextUsageType3;
                        break;
                    }
                } else {
                    value3 = value18;
                }
                patternCharIndex = patternCharIndex3;
                str = this.override;
                if (str == null) {
                    break;
                }
                value2 = value3;
                if (count != 2) {
                }
                value = value2;
                capContextUsageType = capContextUsageType3;
                break;
            case 31:
                int value19 = value;
                bufstart = bufstart3;
                if (count == 1) {
                    String result14 = tzFormat().format(TimeZoneFormat.Style.LOCALIZED_GMT_SHORT, tz, date);
                    result5 = result14;
                } else if (count != 4) {
                    result5 = null;
                } else {
                    String result15 = tzFormat().format(TimeZoneFormat.Style.LOCALIZED_GMT, tz, date);
                    result5 = result15;
                }
                buf.append(result5);
                capContextUsageType = capContextUsageType3;
                patternCharIndex = patternCharIndex3;
                value = value19;
                break;
            case 32:
                int value20 = value;
                bufstart = bufstart3;
                if (count == 1) {
                    String result16 = tzFormat().format(TimeZoneFormat.Style.ISO_BASIC_SHORT, tz, date);
                    result6 = result16;
                } else if (count == 2) {
                    String result17 = tzFormat().format(TimeZoneFormat.Style.ISO_BASIC_FIXED, tz, date);
                    result6 = result17;
                } else if (count == 3) {
                    String result18 = tzFormat().format(TimeZoneFormat.Style.ISO_EXTENDED_FIXED, tz, date);
                    result6 = result18;
                } else if (count == 4) {
                    String result19 = tzFormat().format(TimeZoneFormat.Style.ISO_BASIC_FULL, tz, date);
                    result6 = result19;
                } else if (count != 5) {
                    result6 = null;
                } else {
                    String result20 = tzFormat().format(TimeZoneFormat.Style.ISO_EXTENDED_FULL, tz, date);
                    result6 = result20;
                }
                buf.append(result6);
                capContextUsageType = capContextUsageType3;
                patternCharIndex = patternCharIndex3;
                value = value20;
                break;
            case 33:
                int value21 = value;
                bufstart = bufstart3;
                if (count == 1) {
                    String result21 = tzFormat().format(TimeZoneFormat.Style.ISO_BASIC_LOCAL_SHORT, tz, date);
                    result7 = result21;
                } else if (count == 2) {
                    String result22 = tzFormat().format(TimeZoneFormat.Style.ISO_BASIC_LOCAL_FIXED, tz, date);
                    result7 = result22;
                } else if (count == 3) {
                    String result23 = tzFormat().format(TimeZoneFormat.Style.ISO_EXTENDED_LOCAL_FIXED, tz, date);
                    result7 = result23;
                } else if (count == 4) {
                    String result24 = tzFormat().format(TimeZoneFormat.Style.ISO_BASIC_LOCAL_FULL, tz, date);
                    result7 = result24;
                } else if (count == 5) {
                    String result25 = tzFormat().format(TimeZoneFormat.Style.ISO_EXTENDED_LOCAL_FULL, tz, date);
                    result7 = result25;
                } else {
                    result7 = null;
                }
                buf.append(result7);
                capContextUsageType = capContextUsageType3;
                patternCharIndex = patternCharIndex3;
                value = value21;
                break;
            case 35:
                int value22 = value;
                bufstart = bufstart3;
                if (cal.get(11) == 12 && ((!this.hasMinute || cal.get(12) == 0) && (!this.hasSecond || cal.get(13) == 0))) {
                    int value23 = cal.get(9);
                    if (count <= 3) {
                        String toAppend3 = this.formatData.abbreviatedDayPeriods[value23];
                        value22 = value23;
                        toAppend = toAppend3;
                    } else if (count == 4 || count > 5) {
                        String toAppend4 = this.formatData.wideDayPeriods[value23];
                        value22 = value23;
                        toAppend = toAppend4;
                    } else {
                        String toAppend5 = this.formatData.narrowDayPeriods[value23];
                        value22 = value23;
                        toAppend = toAppend5;
                    }
                } else {
                    toAppend = null;
                }
                if (toAppend == null) {
                    subFormat(buf, 'a', count, beginOffset, fieldNum, capitalizationContext, pos, cal);
                } else {
                    buf.append(toAppend);
                }
                capContextUsageType = capContextUsageType3;
                patternCharIndex = patternCharIndex3;
                value = value22;
                break;
            case 36:
                DayPeriodRules ruleSet2 = DayPeriodRules.getInstance(getLocale());
                if (ruleSet2 == null) {
                    bufstart = bufstart3;
                    subFormat(buf, 'a', count, beginOffset, fieldNum, capitalizationContext, pos, cal);
                    patternCharIndex = patternCharIndex3;
                    value2 = value;
                } else {
                    int value24 = value;
                    bufstart = bufstart3;
                    int hour = cal.get(11);
                    int minute = 0;
                    if (this.hasMinute) {
                        minute = cal.get(12);
                    }
                    int minute2 = minute;
                    int second = this.hasSecond ? cal.get(13) : 0;
                    if (hour == 0 && minute2 == 0 && second == 0 && ruleSet2.hasMidnight()) {
                        periodType = DayPeriodRules.DayPeriod.MIDNIGHT;
                        ruleSet = ruleSet2;
                    } else if (hour == 12 && minute2 == 0 && second == 0 && ruleSet2.hasNoon()) {
                        periodType = DayPeriodRules.DayPeriod.NOON;
                        ruleSet = ruleSet2;
                    } else {
                        ruleSet = ruleSet2;
                        periodType = ruleSet.getDayPeriodForHour(hour);
                    }
                    if (periodType == null) {
                        throw new AssertionError();
                    }
                    String toAppend6 = null;
                    if (periodType != DayPeriodRules.DayPeriod.AM && periodType != DayPeriodRules.DayPeriod.PM && periodType != DayPeriodRules.DayPeriod.MIDNIGHT) {
                        int index = periodType.ordinal();
                        toAppend6 = count <= 3 ? this.formatData.abbreviatedDayPeriods[index] : (count == 4 || count > 5) ? this.formatData.wideDayPeriods[index] : this.formatData.narrowDayPeriods[index];
                    }
                    if (toAppend6 == null && (periodType == DayPeriodRules.DayPeriod.MIDNIGHT || periodType == DayPeriodRules.DayPeriod.NOON)) {
                        DayPeriodRules.DayPeriod periodType3 = ruleSet.getDayPeriodForHour(hour);
                        int index2 = periodType3.ordinal();
                        if (count <= 3) {
                            String toAppend7 = this.formatData.abbreviatedDayPeriods[index2];
                            periodType2 = periodType3;
                            toAppend2 = toAppend7;
                        } else if (count == 4 || count > 5) {
                            String toAppend8 = this.formatData.wideDayPeriods[index2];
                            periodType2 = periodType3;
                            toAppend2 = toAppend8;
                        } else {
                            String toAppend9 = this.formatData.narrowDayPeriods[index2];
                            periodType2 = periodType3;
                            toAppend2 = toAppend9;
                        }
                    } else {
                        periodType2 = periodType;
                        toAppend2 = toAppend6;
                    }
                    DayPeriodRules.DayPeriod periodType4 = DayPeriodRules.DayPeriod.AM;
                    if (periodType2 == periodType4 || periodType2 == DayPeriodRules.DayPeriod.PM || toAppend2 == null) {
                        subFormat(buf, 'a', count, beginOffset, fieldNum, capitalizationContext, pos, cal);
                        patternCharIndex = patternCharIndex3;
                        value2 = value24;
                    } else {
                        buf.append(toAppend2);
                        patternCharIndex = patternCharIndex3;
                        value2 = value24;
                    }
                }
                value = value2;
                capContextUsageType = capContextUsageType3;
                break;
            case 37:
                buf.append(this.formatData.getTimeSeparatorString());
                value2 = value;
                patternCharIndex = patternCharIndex3;
                bufstart = bufstart3;
                value = value2;
                capContextUsageType = capContextUsageType3;
                break;
        }
        if (fieldNum != 0 || capitalizationContext == null) {
            bufstart2 = bufstart;
        } else {
            bufstart2 = bufstart;
            if (UCharacter.isLowerCase(buf.codePointAt(bufstart2))) {
                boolean titlecase = false;
                switch (C07521.$SwitchMap$com$ibm$icu$text$DisplayContext[capitalizationContext.ordinal()]) {
                    case 1:
                        titlecase = true;
                        break;
                    case 2:
                    case 3:
                        if (this.formatData.capitalization != null) {
                            boolean[] transforms = this.formatData.capitalization.get(capContextUsageType);
                            titlecase = capitalizationContext == DisplayContext.CAPITALIZATION_FOR_UI_LIST_OR_MENU ? transforms[0] : transforms[1];
                            break;
                        }
                        break;
                }
                if (titlecase) {
                    if (this.capitalizationBrkIter == null) {
                        this.capitalizationBrkIter = BreakIterator.getSentenceInstance(this.locale);
                    }
                    String firstField = buf.substring(bufstart2);
                    String firstFieldTitleCase = UCharacter.toTitleCase(this.locale, firstField, this.capitalizationBrkIter, 768);
                    buf.replace(bufstart2, buf.length(), firstFieldTitleCase);
                }
            }
        }
        if (pos.getBeginIndex() == pos.getEndIndex()) {
            if (pos.getField() == PATTERN_INDEX_TO_DATE_FORMAT_FIELD[patternCharIndex]) {
                pos.setBeginIndex(beginOffset);
                pos.setEndIndex((buf.length() + beginOffset) - bufstart2);
            } else if (pos.getFieldAttribute() == PATTERN_INDEX_TO_DATE_FORMAT_ATTRIBUTE[patternCharIndex]) {
                pos.setBeginIndex(beginOffset);
                pos.setEndIndex((buf.length() + beginOffset) - bufstart2);
            }
        }
    }

    /* renamed from: com.ibm.icu.text.SimpleDateFormat$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C07521 {
        static final /* synthetic */ int[] $SwitchMap$com$ibm$icu$text$DisplayContext;

        static {
            int[] iArr = new int[DisplayContext.values().length];
            $SwitchMap$com$ibm$icu$text$DisplayContext = iArr;
            try {
                iArr[DisplayContext.CAPITALIZATION_FOR_BEGINNING_OF_SENTENCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$DisplayContext[DisplayContext.CAPITALIZATION_FOR_UI_LIST_OR_MENU.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$DisplayContext[DisplayContext.CAPITALIZATION_FOR_STANDALONE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    private static void safeAppend(String[] array, int value, StringBuffer appendTo) {
        if (array != null && value >= 0 && value < array.length) {
            appendTo.append(array[value]);
        }
    }

    private static void safeAppendWithMonthPattern(String[] array, int value, StringBuffer appendTo, String monthPattern) {
        if (array != null && value >= 0 && value < array.length) {
            if (monthPattern == null) {
                appendTo.append(array[value]);
                return;
            }
            String s = SimpleFormatterImpl.formatRawPattern(monthPattern, 1, 1, new CharSequence[]{array[value]});
            appendTo.append(s);
        }
    }

    /* loaded from: classes.dex */
    private static class PatternItem {
        final boolean isNumeric;
        final int length;
        final char type;

        PatternItem(char type, int length) {
            this.type = type;
            this.length = length;
            this.isNumeric = SimpleDateFormat.isNumeric(type, length);
        }
    }

    private Object[] getPatternItems() {
        Object[] objArr = this.patternItems;
        if (objArr != null) {
            return objArr;
        }
        Object[] objArr2 = (Object[]) PARSED_PATTERN_CACHE.get(this.pattern);
        this.patternItems = objArr2;
        if (objArr2 != null) {
            return objArr2;
        }
        boolean isPrevQuote = false;
        boolean inQuote = false;
        StringBuilder text = new StringBuilder();
        char itemType = 0;
        int itemLength = 1;
        List<Object> items = new ArrayList<>();
        int i = 0;
        while (true) {
            if (i >= this.pattern.length()) {
                break;
            }
            char ch = this.pattern.charAt(i);
            if (ch == '\'') {
                if (isPrevQuote) {
                    text.append('\'');
                    isPrevQuote = false;
                } else {
                    isPrevQuote = true;
                    if (itemType != 0) {
                        items.add(new PatternItem(itemType, itemLength));
                        itemType = 0;
                    }
                }
                inQuote = inQuote ? false : true;
            } else {
                isPrevQuote = false;
                if (inQuote) {
                    text.append(ch);
                } else if (isSyntaxChar(ch)) {
                    if (ch == itemType) {
                        itemLength++;
                    } else {
                        if (itemType == 0) {
                            if (text.length() > 0) {
                                items.add(text.toString());
                                text.setLength(0);
                            }
                        } else {
                            items.add(new PatternItem(itemType, itemLength));
                        }
                        itemType = ch;
                        itemLength = 1;
                    }
                } else {
                    if (itemType != 0) {
                        items.add(new PatternItem(itemType, itemLength));
                        itemType = 0;
                    }
                    text.append(ch);
                }
            }
            i++;
        }
        if (itemType == 0) {
            if (text.length() > 0) {
                items.add(text.toString());
                text.setLength(0);
            }
        } else {
            items.add(new PatternItem(itemType, itemLength));
        }
        Object[] array = items.toArray(new Object[items.size()]);
        this.patternItems = array;
        PARSED_PATTERN_CACHE.put(this.pattern, array);
        return this.patternItems;
    }

    @Deprecated
    protected void zeroPaddingNumber(NumberFormat nf, StringBuffer buf, int value, int minDigits, int maxDigits) {
        if (this.useLocalZeroPaddingNumberFormat && value >= 0) {
            fastZeroPaddingNumber(buf, value, minDigits, maxDigits);
            return;
        }
        nf.setMinimumIntegerDigits(minDigits);
        nf.setMaximumIntegerDigits(maxDigits);
        nf.format(value, buf, new FieldPosition(-1));
    }

    @Override // com.ibm.icu.text.DateFormat
    public void setNumberFormat(NumberFormat newNumberFormat) {
        super.setNumberFormat(newNumberFormat);
        initLocalZeroPaddingNumberFormat();
        initializeTimeZoneFormat(true);
        if (this.numberFormatters != null) {
            this.numberFormatters = null;
        }
        if (this.overrideMap != null) {
            this.overrideMap = null;
        }
    }

    private void initLocalZeroPaddingNumberFormat() {
        if (this.numberFormat instanceof DecimalFormat) {
            DecimalFormatSymbols tmpDecfs = ((DecimalFormat) this.numberFormat).getDecimalFormatSymbols();
            String[] tmpDigits = tmpDecfs.getDigitStringsLocal();
            this.useLocalZeroPaddingNumberFormat = true;
            this.decDigits = new char[10];
            int i = 0;
            while (true) {
                if (i >= 10) {
                    break;
                } else if (tmpDigits[i].length() > 1) {
                    this.useLocalZeroPaddingNumberFormat = false;
                    break;
                } else {
                    this.decDigits[i] = tmpDigits[i].charAt(0);
                    i++;
                }
            }
        } else if (this.numberFormat instanceof DateNumberFormat) {
            this.decDigits = this.numberFormat.getDigits();
            this.useLocalZeroPaddingNumberFormat = true;
        } else {
            this.useLocalZeroPaddingNumberFormat = false;
        }
        if (this.useLocalZeroPaddingNumberFormat) {
            this.decimalBuf = new char[10];
        }
    }

    private void fastZeroPaddingNumber(StringBuffer buf, int value, int minDigits, int maxDigits) {
        char[] cArr = this.decimalBuf;
        int limit = cArr.length < maxDigits ? cArr.length : maxDigits;
        int index = limit - 1;
        while (true) {
            this.decimalBuf[index] = this.decDigits[value % 10];
            value /= 10;
            if (index == 0 || value == 0) {
                break;
            }
            index--;
        }
        int padding = minDigits - (limit - index);
        while (padding > 0 && index > 0) {
            index--;
            this.decimalBuf[index] = this.decDigits[0];
            padding--;
        }
        while (padding > 0) {
            buf.append(this.decDigits[0]);
            padding--;
        }
        buf.append(this.decimalBuf, index, limit - index);
    }

    protected String zeroPaddingNumber(long value, int minDigits, int maxDigits) {
        this.numberFormat.setMinimumIntegerDigits(minDigits);
        this.numberFormat.setMaximumIntegerDigits(maxDigits);
        return this.numberFormat.format(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isNumeric(char formatChar, int count) {
        return NUMERIC_FORMAT_CHARS.indexOf(formatChar) >= 0 || (count <= 2 && NUMERIC_FORMAT_CHARS2.indexOf(formatChar) >= 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:186:0x03fa, code lost:
        if (r12[1] == 0) goto L229;
     */
    /* JADX WARN: Incorrect condition in loop: B:20:0x0093 */
    /* JADX WARN: Removed duplicated region for block: B:108:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0325  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0356 A[Catch: IllegalArgumentException -> 0x0343, TRY_ENTER, TryCatch #0 {IllegalArgumentException -> 0x0343, blocks: (B:147:0x0335, B:156:0x0356, B:158:0x036a, B:163:0x0387, B:168:0x03a1, B:170:0x03a5, B:171:0x03bc), top: B:253:0x0335 }] */
    /* JADX WARN: Removed duplicated region for block: B:239:0x04f2  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0500  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0510  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0518  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0531  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0335 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:256:0x0378 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:289:? A[RETURN, SYNTHETIC] */
    @Override // com.ibm.icu.text.DateFormat
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void parse(String text, Calendar cal, ParsePosition parsePos) {
        TimeZone backupTZ;
        Calendar resultCal;
        Calendar cal2;
        MessageFormat numericLeapMonthFormatter;
        int pos;
        Calendar cal3;
        int start;
        SimpleDateFormat simpleDateFormat;
        TimeZoneFormat.TimeType tztype;
        long localMillis;
        boolean z;
        int resolvedSavings;
        int resolvedSavings2;
        TimeZoneTransition beforeTrs;
        long afterT;
        TimeZoneTransition afterTrs;
        int hourOfDay;
        Output<TimeZoneFormat.TimeType> tzTimeType;
        Output<DayPeriodRules.DayPeriod> dayPeriod;
        int start2;
        Object[] items;
        Calendar resultCal2;
        Calendar cal4;
        TimeZone backupTZ2;
        int i;
        int i2;
        int numericFieldStart;
        int numericFieldStart2;
        int len;
        if (cal == this.calendar || cal.getType().equals(this.calendar.getType())) {
            backupTZ = null;
            resultCal = null;
            cal2 = cal;
        } else {
            this.calendar.setTimeInMillis(cal.getTimeInMillis());
            TimeZone backupTZ3 = this.calendar.getTimeZone();
            this.calendar.setTimeZone(cal.getTimeZone());
            backupTZ = backupTZ3;
            resultCal = cal;
            cal2 = this.calendar;
        }
        int pos2 = parsePos.getIndex();
        if (pos2 < 0) {
            parsePos.setErrorIndex(0);
            return;
        }
        int start3 = pos2;
        Output<DayPeriodRules.DayPeriod> dayPeriod2 = new Output<>((Object) null);
        Output<TimeZoneFormat.TimeType> tzTimeType2 = new Output<>(TimeZoneFormat.TimeType.UNKNOWN);
        boolean[] ambiguousYear = {false};
        int numericFieldStart3 = -1;
        if (this.formatData.leapMonthPatterns == null || this.formatData.leapMonthPatterns.length < 7) {
            numericLeapMonthFormatter = null;
        } else {
            MessageFormat numericLeapMonthFormatter2 = new MessageFormat(this.formatData.leapMonthPatterns[6], this.locale);
            numericLeapMonthFormatter = numericLeapMonthFormatter2;
        }
        Object[] items2 = getPatternItems();
        int numericFieldLength = 0;
        int numericStartPos = 0;
        int i3 = 0;
        while (i3 < numericFieldLength) {
            if (items2[i3] instanceof PatternItem) {
                PatternItem field = (PatternItem) items2[i3];
                if (field.isNumeric && numericFieldStart3 == -1 && i3 + 1 < items2.length && (items2[i3 + 1] instanceof PatternItem) && ((PatternItem) items2[i3 + 1]).isNumeric) {
                    int numericFieldStart4 = i3;
                    int numericFieldLength2 = field.length;
                    numericFieldLength = numericFieldLength2;
                    numericStartPos = pos2;
                    numericFieldStart = numericFieldStart4;
                } else {
                    numericFieldStart = numericFieldStart3;
                }
                if (numericFieldStart != -1) {
                    int len2 = field.length;
                    if (numericFieldStart == i3) {
                        int len3 = numericFieldLength;
                        len = len3;
                    } else {
                        len = len2;
                    }
                    int numericFieldStart5 = numericFieldStart;
                    int i4 = i3;
                    tzTimeType = tzTimeType2;
                    dayPeriod = dayPeriod2;
                    int start4 = start3;
                    Object[] items3 = items2;
                    resultCal2 = resultCal;
                    pos2 = subParse(text, pos2, field.type, len, true, false, ambiguousYear, cal2, numericLeapMonthFormatter, tzTimeType);
                    if (pos2 < 0) {
                        numericFieldLength--;
                        if (numericFieldLength == 0) {
                            parsePos.setIndex(start4);
                            parsePos.setErrorIndex(pos2);
                            if (backupTZ != null) {
                                this.calendar.setTimeZone(backupTZ);
                                return;
                            }
                            return;
                        }
                        i3 = numericFieldStart5;
                        pos2 = numericStartPos;
                        tzTimeType2 = tzTimeType;
                        start3 = start4;
                        dayPeriod2 = dayPeriod;
                        numericFieldStart3 = numericFieldStart5;
                        items2 = items3;
                        resultCal = resultCal2;
                    } else {
                        start2 = start4;
                        cal4 = cal2;
                        i2 = i4;
                        numericFieldStart2 = numericFieldStart5;
                        items = items3;
                        backupTZ2 = backupTZ;
                    }
                } else {
                    int numericFieldStart6 = numericFieldStart;
                    int i5 = i3;
                    tzTimeType = tzTimeType2;
                    dayPeriod = dayPeriod2;
                    Object[] items4 = items2;
                    resultCal2 = resultCal;
                    int start5 = start3;
                    if (field.type != 'l') {
                        int s = pos2;
                        cal4 = cal2;
                        backupTZ2 = backupTZ;
                        pos2 = subParse(text, pos2, field.type, field.length, false, true, ambiguousYear, cal2, numericLeapMonthFormatter, tzTimeType, dayPeriod);
                        if (pos2 >= 0) {
                            items = items4;
                            start2 = start5;
                            numericFieldStart2 = -1;
                            i2 = i5;
                        } else if (pos2 != ISOSpecialEra) {
                            parsePos.setIndex(start5);
                            parsePos.setErrorIndex(s);
                            if (backupTZ2 != null) {
                                this.calendar.setTimeZone(backupTZ2);
                                return;
                            }
                            return;
                        } else {
                            items = items4;
                            if (i5 + 1 < items.length) {
                                try {
                                    String patl = (String) items[i5 + 1];
                                    if (patl == null) {
                                        patl = (String) items[i5 + 1];
                                    }
                                    int plen = patl.length();
                                    int idx = 0;
                                    while (idx < plen) {
                                        char pch = patl.charAt(idx);
                                        if (!PatternProps.isWhiteSpace(pch)) {
                                            break;
                                        }
                                        idx++;
                                    }
                                    i2 = idx == plen ? i5 + 1 : i5;
                                    pos2 = s;
                                    numericFieldStart2 = -1;
                                    start2 = start5;
                                } catch (ClassCastException e) {
                                    parsePos.setIndex(start5);
                                    parsePos.setErrorIndex(s);
                                    if (backupTZ2 != null) {
                                        this.calendar.setTimeZone(backupTZ2);
                                        return;
                                    }
                                    return;
                                }
                            } else {
                                start2 = start5;
                                pos2 = s;
                                numericFieldStart2 = -1;
                                i2 = i5;
                            }
                        }
                    } else {
                        start2 = start5;
                        cal4 = cal2;
                        items = items4;
                        backupTZ2 = backupTZ;
                        i2 = i5;
                        numericFieldStart2 = numericFieldStart6;
                    }
                }
                numericFieldStart3 = numericFieldStart2;
                i = 1;
            } else {
                int i6 = i3;
                tzTimeType = tzTimeType2;
                dayPeriod = dayPeriod2;
                start2 = start3;
                items = items2;
                resultCal2 = resultCal;
                cal4 = cal2;
                backupTZ2 = backupTZ;
                i = 1;
                boolean[] complete = new boolean[1];
                pos2 = matchLiteral(text, pos2, items, i6, complete);
                if (!complete[0]) {
                    parsePos.setIndex(start2);
                    parsePos.setErrorIndex(pos2);
                    if (backupTZ2 != null) {
                        this.calendar.setTimeZone(backupTZ2);
                        return;
                    }
                    return;
                }
                numericFieldStart3 = -1;
                i2 = i6;
            }
            i3 = i2 + i;
            items2 = items;
            start3 = start2;
            backupTZ = backupTZ2;
            dayPeriod2 = dayPeriod;
            cal2 = cal4;
            resultCal = resultCal2;
            tzTimeType2 = tzTimeType;
        }
        Output<TimeZoneFormat.TimeType> tzTimeType3 = tzTimeType2;
        Output<DayPeriodRules.DayPeriod> dayPeriod3 = dayPeriod2;
        int start6 = start3;
        Object[] items5 = items2;
        Calendar resultCal3 = resultCal;
        Calendar cal5 = cal2;
        TimeZone backupTZ4 = backupTZ;
        try {
            if (pos2 < text.length()) {
                char extra = text.charAt(pos2);
                if (extra == '.' && getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_ALLOW_WHITESPACE) && items5.length != 0) {
                    Object lastItem = items5[items5.length - 1];
                    if ((lastItem instanceof PatternItem) && !((PatternItem) lastItem).isNumeric) {
                        pos = pos2 + 1;
                        if (dayPeriod3.value == null) {
                            DayPeriodRules ruleSet = DayPeriodRules.getInstance(getLocale());
                            cal3 = cal5;
                            if (cal3.isSet(10) || cal3.isSet(11)) {
                                if (cal3.isSet(11)) {
                                    hourOfDay = cal3.get(11);
                                } else {
                                    hourOfDay = cal3.get(10);
                                    if (hourOfDay == 0) {
                                        hourOfDay = 12;
                                    }
                                }
                                if (hourOfDay < 0 || hourOfDay > 23) {
                                    throw new AssertionError();
                                }
                                if (hourOfDay == 0 || (13 <= hourOfDay && hourOfDay <= 23)) {
                                    cal3.set(11, hourOfDay);
                                } else {
                                    if (hourOfDay == 12) {
                                        hourOfDay = 0;
                                    }
                                    double currentHour = hourOfDay + (cal3.get(12) / 60.0d);
                                    double hoursAheadMidPoint = currentHour - ruleSet.getMidPointForDayPeriod((DayPeriodRules.DayPeriod) dayPeriod3.value);
                                    if (-6.0d > hoursAheadMidPoint || hoursAheadMidPoint >= 6.0d) {
                                        cal3.set(9, 1);
                                    } else {
                                        cal3.set(9, 0);
                                    }
                                }
                            } else {
                                double midPoint = ruleSet.getMidPointForDayPeriod((DayPeriodRules.DayPeriod) dayPeriod3.value);
                                int midPointHour = (int) midPoint;
                                int midPointMinute = midPoint - ((double) midPointHour) > 0.0d ? 30 : 0;
                                cal3.set(11, midPointHour);
                                cal3.set(12, midPointMinute);
                            }
                        } else {
                            cal3 = cal5;
                        }
                        parsePos.setIndex(pos);
                        tztype = (TimeZoneFormat.TimeType) tzTimeType3.value;
                        if (!ambiguousYear[0]) {
                            try {
                                if (tztype == TimeZoneFormat.TimeType.UNKNOWN) {
                                    if (resultCal3 != null) {
                                        resultCal3.setTimeZone(cal3.getTimeZone());
                                        resultCal3.setTimeInMillis(cal3.getTimeInMillis());
                                    }
                                    if (backupTZ4 != null) {
                                        this.calendar.setTimeZone(backupTZ4);
                                        return;
                                    }
                                    return;
                                }
                            } catch (IllegalArgumentException e2) {
                                start = start6;
                                simpleDateFormat = this;
                                parsePos.setErrorIndex(pos);
                                parsePos.setIndex(start);
                                if (backupTZ4 != null) {
                                    simpleDateFormat.calendar.setTimeZone(backupTZ4);
                                    return;
                                }
                                return;
                            }
                        }
                        if (ambiguousYear[0]) {
                            Date parsedDate = ((Calendar) cal3.clone()).getTime();
                            if (parsedDate.before(getDefaultCenturyStart())) {
                                cal3.set(1, getDefaultCenturyStartYear() + 100);
                            }
                        }
                        if (tztype == TimeZoneFormat.TimeType.UNKNOWN) {
                            try {
                                Calendar copy = (Calendar) cal3.clone();
                                BasicTimeZone timeZone = copy.getTimeZone();
                                BasicTimeZone btz = timeZone instanceof BasicTimeZone ? timeZone : null;
                                copy.set(15, 0);
                                copy.set(16, 0);
                                long localMillis2 = copy.getTimeInMillis();
                                int[] offsets = new int[2];
                                if (btz == null) {
                                    localMillis = localMillis2;
                                    try {
                                        timeZone.getOffset(localMillis, true, offsets);
                                        if (tztype == TimeZoneFormat.TimeType.STANDARD) {
                                            try {
                                                if (offsets[1] != 0) {
                                                    z = true;
                                                    start = start6;
                                                    try {
                                                        timeZone.getOffset(localMillis - 86400000, z, offsets);
                                                    } catch (IllegalArgumentException e3) {
                                                        simpleDateFormat = this;
                                                        parsePos.setErrorIndex(pos);
                                                        parsePos.setIndex(start);
                                                        if (backupTZ4 != null) {
                                                        }
                                                    }
                                                }
                                            } catch (IllegalArgumentException e4) {
                                                simpleDateFormat = this;
                                                start = start6;
                                                parsePos.setErrorIndex(pos);
                                                parsePos.setIndex(start);
                                                if (backupTZ4 != null) {
                                                }
                                            }
                                        }
                                        z = tztype == TimeZoneFormat.TimeType.DAYLIGHT ? true : true;
                                        start = start6;
                                    } catch (IllegalArgumentException e5) {
                                        start = start6;
                                        simpleDateFormat = this;
                                    }
                                } else if (tztype == TimeZoneFormat.TimeType.STANDARD) {
                                    btz.getOffsetFromLocal(localMillis2, 1, 1, offsets);
                                    start = start6;
                                    localMillis = localMillis2;
                                } else {
                                    btz.getOffsetFromLocal(localMillis2, 3, 3, offsets);
                                    start = start6;
                                    localMillis = localMillis2;
                                }
                                int resolvedSavings3 = offsets[1];
                                if (tztype == TimeZoneFormat.TimeType.STANDARD) {
                                    if (offsets[1] != 0) {
                                        resolvedSavings2 = 0;
                                        cal3.set(15, offsets[0]);
                                        cal3.set(16, resolvedSavings2);
                                    } else {
                                        resolvedSavings = resolvedSavings3;
                                        resolvedSavings2 = resolvedSavings;
                                        cal3.set(15, offsets[0]);
                                        cal3.set(16, resolvedSavings2);
                                    }
                                } else if (offsets[1] == 0) {
                                    if (btz != null) {
                                        long time = localMillis + offsets[0];
                                        long beforeT = time;
                                        int beforeSav = 0;
                                        int afterSav = 0;
                                        while (true) {
                                            TimeZoneFormat.TimeType tztype2 = tztype;
                                            beforeTrs = btz.getPreviousTransition(beforeT, true);
                                            if (beforeTrs == null) {
                                                afterT = time;
                                                break;
                                            }
                                            beforeT = beforeTrs.getTime() - 1;
                                            beforeSav = beforeTrs.getFrom().getDSTSavings();
                                            if (beforeSav != 0) {
                                                afterT = time;
                                                break;
                                            }
                                            tztype = tztype2;
                                        }
                                        do {
                                            afterTrs = btz.getNextTransition(afterT, false);
                                            if (afterTrs == null) {
                                                break;
                                            }
                                            afterT = afterTrs.getTime();
                                            afterSav = afterTrs.getTo().getDSTSavings();
                                        } while (afterSav == 0);
                                        resolvedSavings2 = (beforeTrs == null || afterTrs == null) ? (beforeTrs == null || beforeSav == 0) ? (afterTrs == null || afterSav == 0) ? btz.getDSTSavings() : afterSav : beforeSav : time - beforeT > afterT - time ? afterSav : beforeSav;
                                    } else {
                                        resolvedSavings2 = timeZone.getDSTSavings();
                                    }
                                    if (resolvedSavings2 == 0) {
                                        resolvedSavings2 = millisPerHour;
                                    }
                                    cal3.set(15, offsets[0]);
                                    cal3.set(16, resolvedSavings2);
                                } else {
                                    resolvedSavings = resolvedSavings3;
                                    resolvedSavings2 = resolvedSavings;
                                    cal3.set(15, offsets[0]);
                                    cal3.set(16, resolvedSavings2);
                                }
                            } catch (IllegalArgumentException e6) {
                                start = start6;
                                simpleDateFormat = this;
                            }
                        }
                        if (resultCal3 != null) {
                        }
                        if (backupTZ4 != null) {
                        }
                    }
                }
            }
            tztype = (TimeZoneFormat.TimeType) tzTimeType3.value;
            if (!ambiguousYear[0]) {
            }
            if (ambiguousYear[0]) {
            }
            if (tztype == TimeZoneFormat.TimeType.UNKNOWN) {
            }
            if (resultCal3 != null) {
            }
            if (backupTZ4 != null) {
            }
        } catch (IllegalArgumentException e7) {
            start = start6;
            simpleDateFormat = this;
        }
        pos = pos2;
        if (dayPeriod3.value == null) {
        }
        parsePos.setIndex(pos);
    }

    private int matchLiteral(String text, int pos, Object[] items, int itemIndex, boolean[] complete) {
        boolean z;
        String patternLiteral = (String) items[itemIndex];
        int plen = patternLiteral.length();
        int tlen = text.length();
        int idx = 0;
        int pos2 = pos;
        while (idx < plen && pos2 < tlen) {
            char pch = patternLiteral.charAt(idx);
            char ich = text.charAt(pos2);
            if (PatternProps.isWhiteSpace(pch) && PatternProps.isWhiteSpace(ich)) {
                while (idx + 1 < plen && PatternProps.isWhiteSpace(patternLiteral.charAt(idx + 1))) {
                    idx++;
                }
                while (pos2 + 1 < tlen && PatternProps.isWhiteSpace(text.charAt(pos2 + 1))) {
                    pos2++;
                }
            } else if (pch != ich) {
                if (ich != '.' || pos2 != pos || itemIndex <= 0 || !getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_ALLOW_WHITESPACE)) {
                    if ((pch == ' ' || pch == '.') && getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_ALLOW_WHITESPACE)) {
                        idx++;
                    } else if (pos2 == pos || !getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_PARTIAL_LITERAL_MATCH)) {
                        break;
                    } else {
                        idx++;
                    }
                } else {
                    Object before = items[itemIndex - 1];
                    if (!(before instanceof PatternItem)) {
                        break;
                    }
                    boolean isNumeric = ((PatternItem) before).isNumeric;
                    if (isNumeric) {
                        break;
                    }
                    pos2++;
                }
            }
            idx++;
            pos2++;
        }
        if (idx == plen) {
            z = true;
        } else {
            z = false;
        }
        complete[0] = z;
        if (!complete[0] && getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_ALLOW_WHITESPACE) && itemIndex > 0 && itemIndex < items.length - 1 && pos < tlen) {
            Object before2 = items[itemIndex - 1];
            Object after = items[itemIndex + 1];
            if ((before2 instanceof PatternItem) && (after instanceof PatternItem)) {
                char beforeType = ((PatternItem) before2).type;
                char afterType = ((PatternItem) after).type;
                UnicodeSet unicodeSet = DATE_PATTERN_TYPE;
                if (unicodeSet.contains(beforeType) != unicodeSet.contains(afterType)) {
                    int newPos = pos;
                    while (newPos < tlen && PatternProps.isWhiteSpace(text.charAt(newPos))) {
                        newPos++;
                    }
                    complete[0] = newPos > pos;
                    return newPos;
                }
                return pos2;
            }
            return pos2;
        }
        return pos2;
    }

    protected int matchString(String text, int start, int field, String[] data, Calendar cal) {
        return matchString(text, start, field, data, null, cal);
    }

    @Deprecated
    private int matchString(String text, int start, int field, String[] data, String monthPattern, Calendar cal) {
        String leapMonthName;
        int length;
        int matchLength;
        int matchLength2;
        int count = data.length;
        int bestMatchLength = 0;
        int bestMatch = -1;
        int isLeapMonth = 0;
        for (int i = field == 7 ? 1 : 0; i < count; i++) {
            int length2 = data[i].length();
            if (length2 > bestMatchLength && (matchLength2 = regionMatchesWithOptionalDot(text, start, data[i], length2)) >= 0) {
                bestMatch = i;
                bestMatchLength = matchLength2;
                isLeapMonth = 0;
            }
            if (monthPattern != null && (length = (leapMonthName = SimpleFormatterImpl.formatRawPattern(monthPattern, 1, 1, new CharSequence[]{data[i]})).length()) > bestMatchLength && (matchLength = regionMatchesWithOptionalDot(text, start, leapMonthName, length)) >= 0) {
                bestMatch = i;
                bestMatchLength = matchLength;
                isLeapMonth = 1;
            }
        }
        if (bestMatch >= 0) {
            if (field >= 0) {
                if (field == 1) {
                    bestMatch++;
                }
                cal.set(field, bestMatch);
                if (monthPattern != null) {
                    cal.set(22, isLeapMonth);
                }
            }
            return start + bestMatchLength;
        }
        return ~start;
    }

    private int regionMatchesWithOptionalDot(String text, int start, String data, int length) {
        boolean matches = text.regionMatches(true, start, data, 0, length);
        if (matches) {
            return length;
        }
        if (data.length() > 0 && data.charAt(data.length() - 1) == '.' && text.regionMatches(true, start, data, 0, length - 1)) {
            return length - 1;
        }
        return -1;
    }

    protected int matchQuarterString(String text, int start, int field, String[] data, Calendar cal) {
        int matchLength;
        int count = data.length;
        int bestMatchLength = 0;
        int bestMatch = -1;
        for (int i = 0; i < count; i++) {
            int length = data[i].length();
            if (length > bestMatchLength && (matchLength = regionMatchesWithOptionalDot(text, start, data[i], length)) >= 0) {
                bestMatch = i;
                bestMatchLength = matchLength;
            }
        }
        if (bestMatch >= 0) {
            cal.set(field, bestMatch * 3);
            return start + bestMatchLength;
        }
        return -start;
    }

    private int matchDayPeriodString(String text, int start, String[] data, int dataLength, Output<DayPeriodRules.DayPeriod> dayPeriod) {
        int length;
        int matchLength;
        int bestMatchLength = 0;
        int bestMatch = -1;
        for (int i = 0; i < dataLength; i++) {
            if (data[i] != null && (length = data[i].length()) > bestMatchLength && (matchLength = regionMatchesWithOptionalDot(text, start, data[i], length)) >= 0) {
                bestMatch = i;
                bestMatchLength = matchLength;
            }
        }
        if (bestMatch >= 0) {
            dayPeriod.value = DayPeriodRules.DayPeriod.VALUES[bestMatch];
            return start + bestMatchLength;
        }
        return -start;
    }

    protected int subParse(String text, int start, char ch, int count, boolean obeyCount, boolean allowNegative, boolean[] ambiguousYear, Calendar cal) {
        return subParse(text, start, ch, count, obeyCount, allowNegative, ambiguousYear, cal, null, null);
    }

    private int subParse(String text, int start, char ch, int count, boolean obeyCount, boolean allowNegative, boolean[] ambiguousYear, Calendar cal, MessageFormat numericLeapMonthFormatter, Output<TimeZoneFormat.TimeType> tzTimeType) {
        return subParse(text, start, ch, count, obeyCount, allowNegative, ambiguousYear, cal, null, null, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:200:0x038b, code lost:
        if (r5 > r33.formatData.shortYearNames.length) goto L173;
     */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x014d  */
    @Deprecated
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int subParse(String text, int start, char ch, int count, boolean obeyCount, boolean allowNegative, boolean[] ambiguousYear, Calendar cal, MessageFormat numericLeapMonthFormatter, Output<TimeZoneFormat.TimeType> tzTimeType, Output<DayPeriodRules.DayPeriod> dayPeriod) {
        boolean parsedNumericLeapMonth;
        int field;
        boolean parsedNumericLeapMonth2;
        int patternCharIndex;
        int field2;
        int i;
        NumberFormat currentNumberFormat;
        Number number;
        int i2;
        int ps;
        int i3;
        int value;
        int patternCharIndex2;
        int matchString;
        int value2;
        int value3;
        int start2;
        int field3;
        int value4;
        int i4;
        int field4;
        int newStart;
        int newStart2;
        int value5;
        int field5;
        int i5;
        int value6;
        int value7;
        TimeZoneFormat.Style style;
        int value8;
        int newStart3;
        TimeZoneFormat.Style style2;
        TimeZoneFormat.Style style3;
        Calendar calendar;
        Number number2;
        int value9 = 0;
        ParsePosition pos = new ParsePosition(0);
        int patternCharIndex3 = getIndexFromChar(ch);
        if (patternCharIndex3 != -1) {
            NumberFormat currentNumberFormat2 = getNumberFormat(ch);
            int field6 = PATTERN_INDEX_TO_CALENDAR_FIELD[patternCharIndex3];
            if (numericLeapMonthFormatter != null) {
                numericLeapMonthFormatter.setFormatByArgumentIndex(0, currentNumberFormat2);
            }
            Number number3 = null;
            boolean isChineseCalendar = cal.getType().equals("chinese") || cal.getType().equals("dangi");
            int start3 = start;
            while (start3 < text.length()) {
                int c = UTF16.charAt(text, start3);
                if (UCharacter.isUWhiteSpace(c) && PatternProps.isWhiteSpace(c)) {
                    start3 += UTF16.getCharCount(c);
                } else {
                    pos.setIndex(start3);
                    if (patternCharIndex3 != 4 && patternCharIndex3 != 15 && ((patternCharIndex3 != 2 || count > 2) && patternCharIndex3 != 26 && patternCharIndex3 != 19 && patternCharIndex3 != 25 && patternCharIndex3 != 1 && patternCharIndex3 != 18 && patternCharIndex3 != 30 && ((patternCharIndex3 != 0 || !isChineseCalendar) && patternCharIndex3 != 27 && patternCharIndex3 != 28 && patternCharIndex3 != 8))) {
                        field2 = field6;
                        i = 2;
                        patternCharIndex = patternCharIndex3;
                        currentNumberFormat = currentNumberFormat2;
                    } else {
                        if (numericLeapMonthFormatter != null) {
                            if (patternCharIndex3 != 2 && patternCharIndex3 != 26) {
                                parsedNumericLeapMonth = false;
                                field = field6;
                            } else {
                                Object[] args = numericLeapMonthFormatter.parse(text, pos);
                                parsedNumericLeapMonth = false;
                                if (args == null || pos.getIndex() <= start3) {
                                    field = field6;
                                    i2 = 22;
                                } else {
                                    field = field6;
                                    if (!(args[0] instanceof Number)) {
                                        i2 = 22;
                                    } else {
                                        parsedNumericLeapMonth2 = true;
                                        cal.set(22, 1);
                                        number3 = (Number) args[0];
                                        if (!parsedNumericLeapMonth2) {
                                            patternCharIndex = patternCharIndex3;
                                            field2 = field;
                                            i = 2;
                                            currentNumberFormat = currentNumberFormat2;
                                            number = number3;
                                        } else {
                                            if (obeyCount) {
                                                if (start3 + count > text.length()) {
                                                    return ~start3;
                                                }
                                                field2 = field;
                                                i = 2;
                                                patternCharIndex = patternCharIndex3;
                                                currentNumberFormat = currentNumberFormat2;
                                                number = parseInt(text, count, pos, allowNegative, currentNumberFormat);
                                            } else {
                                                patternCharIndex = patternCharIndex3;
                                                field2 = field;
                                                i = 2;
                                                currentNumberFormat = currentNumberFormat2;
                                                number = parseInt(text, pos, allowNegative, currentNumberFormat);
                                            }
                                            if (number == null && !allowNumericFallback(patternCharIndex)) {
                                                return ~start3;
                                            }
                                        }
                                        if (number != null) {
                                            number3 = number;
                                        } else {
                                            value9 = number.intValue();
                                            number3 = number;
                                        }
                                    }
                                }
                                pos.setIndex(start3);
                                cal.set(i2, 0);
                            }
                        } else {
                            parsedNumericLeapMonth = false;
                            field = field6;
                        }
                        parsedNumericLeapMonth2 = parsedNumericLeapMonth;
                        if (!parsedNumericLeapMonth2) {
                        }
                        if (number != null) {
                        }
                    }
                    switch (patternCharIndex) {
                        case 0:
                            int start4 = start3;
                            if (isChineseCalendar) {
                                cal.set(0, value9);
                                return pos.getIndex();
                            }
                            if (count == 5) {
                                ps = matchString(text, start4, 0, this.formatData.narrowEras, null, cal);
                            } else if (count == 4) {
                                ps = matchString(text, start4, 0, this.formatData.eraNames, null, cal);
                            } else {
                                ps = matchString(text, start4, 0, this.formatData.eras, null, cal);
                            }
                            if (ps == (~start4)) {
                                return ISOSpecialEra;
                            }
                            return ps;
                        case 1:
                        case 18:
                            int start5 = start3;
                            int i6 = i;
                            int field7 = field2;
                            String str = this.override;
                            if (str != null && ((str.compareTo("hebr") == 0 || this.override.indexOf("y=hebr") >= 0) && value9 < 1000)) {
                                value9 += HEBREW_CAL_CUR_MILLENIUM_START_YEAR;
                            } else if (count == i6 && countDigits(text, start5, pos.getIndex()) == i6 && cal.haveDefaultCentury()) {
                                int ambiguousTwoDigitYear = getDefaultCenturyStartYear() % 100;
                                ambiguousYear[0] = value9 == ambiguousTwoDigitYear;
                                value9 += ((getDefaultCenturyStartYear() / 100) * 100) + (value9 >= ambiguousTwoDigitYear ? 0 : 100);
                            }
                            cal.set(field7, value9);
                            if (DelayedHebrewMonthCheck) {
                                if (!HebrewCalendar.isLeapYear(value9)) {
                                    cal.add(i6, 1);
                                }
                                DelayedHebrewMonthCheck = false;
                            }
                            return pos.getIndex();
                        case 2:
                        case 26:
                            int value10 = value9;
                            int start6 = start3;
                            int i7 = i;
                            int patternCharIndex4 = patternCharIndex;
                            if (count > i7) {
                                if (number3 == null || !getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_ALLOW_NUMERIC)) {
                                    boolean haveMonthPat = this.formatData.leapMonthPatterns != null && this.formatData.leapMonthPatterns.length >= 7;
                                    int newStart4 = 0;
                                    if (getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH) || count == 4) {
                                        if (patternCharIndex4 == i7) {
                                            patternCharIndex2 = patternCharIndex4;
                                            matchString = matchString(text, start6, 2, this.formatData.months, haveMonthPat ? this.formatData.leapMonthPatterns[0] : null, cal);
                                        } else {
                                            patternCharIndex2 = patternCharIndex4;
                                            matchString = matchString(text, start6, 2, this.formatData.standaloneMonths, haveMonthPat ? this.formatData.leapMonthPatterns[3] : null, cal);
                                        }
                                        newStart4 = matchString;
                                        if (newStart4 > 0) {
                                            return newStart4;
                                        }
                                    } else {
                                        patternCharIndex2 = patternCharIndex4;
                                    }
                                    if (getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH) || count == 3) {
                                        if (patternCharIndex2 == 2) {
                                            return matchString(text, start6, 2, this.formatData.shortMonths, haveMonthPat ? this.formatData.leapMonthPatterns[1] : null, cal);
                                        }
                                        return matchString(text, start6, 2, this.formatData.standaloneShortMonths, haveMonthPat ? this.formatData.leapMonthPatterns[4] : null, cal);
                                    }
                                    return newStart4;
                                }
                                i3 = i7;
                                value = value10;
                            } else {
                                i3 = i7;
                                value = value10;
                            }
                            int value11 = value;
                            cal.set(i3, value11 - 1);
                            if (cal.getType().equals("hebrew") && value11 >= 6) {
                                if (cal.isSet(1)) {
                                    if (!HebrewCalendar.isLeapYear(cal.get(1))) {
                                        cal.set(i3, value11);
                                    }
                                } else {
                                    DelayedHebrewMonthCheck = true;
                                }
                            }
                            return pos.getIndex();
                        case 3:
                        case 5:
                        case 6:
                        case 7:
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                        case 16:
                        case 20:
                        case 21:
                        case 22:
                        case 34:
                        default:
                            NumberFormat currentNumberFormat3 = currentNumberFormat;
                            int start7 = start3;
                            int patternCharIndex5 = patternCharIndex;
                            int field8 = field2;
                            if (!obeyCount) {
                                calendar = cal;
                                number2 = parseInt(text, pos, allowNegative, currentNumberFormat3);
                            } else if (start7 + count > text.length()) {
                                return -start7;
                            } else {
                                number2 = parseInt(text, count, pos, allowNegative, currentNumberFormat3);
                                calendar = cal;
                            }
                            if (number2 != null) {
                                if (patternCharIndex5 != 34) {
                                    calendar.set(field8, number2.intValue());
                                } else {
                                    calendar.setRelatedYear(number2.intValue());
                                }
                                return pos.getIndex();
                            }
                            return ~start7;
                        case 4:
                            int value12 = value9;
                            if (value12 != cal.getMaximum(11) + 1) {
                                value2 = value12;
                            } else {
                                value2 = 0;
                            }
                            cal.set(11, value2);
                            return pos.getIndex();
                        case 8:
                            int value13 = value9;
                            int i8 = countDigits(text, start3, pos.getIndex());
                            if (i8 < 3) {
                                value3 = value13;
                                while (i8 < 3) {
                                    value3 *= 10;
                                    i8++;
                                }
                            } else {
                                int a = 1;
                                while (i8 > 3) {
                                    a *= 10;
                                    i8--;
                                }
                                value3 = value13 / a;
                            }
                            cal.set(14, value3);
                            return pos.getIndex();
                        case 9:
                            start2 = start3;
                            field3 = field2;
                            value4 = 4;
                            i4 = 6;
                            field4 = 3;
                            break;
                        case 14:
                            int start8 = start3;
                            if (this.formatData.ampmsNarrow == null || count < 5 || getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH)) {
                                newStart = 5;
                                int newStart5 = matchString(text, start8, 9, this.formatData.ampms, null, cal);
                                if (newStart5 > 0) {
                                    return newStart5;
                                }
                            } else {
                                newStart = 5;
                            }
                            return (this.formatData.ampmsNarrow == null || (count < newStart && !getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH)) || (newStart2 = matchString(text, start8, 9, this.formatData.ampmsNarrow, null, cal)) <= 0) ? ~start8 : newStart2;
                        case 15:
                            int value14 = value9;
                            if (value14 != cal.getLeastMaximum(10) + 1) {
                                value5 = value14;
                            } else {
                                value5 = 0;
                            }
                            cal.set(10, value5);
                            return pos.getIndex();
                        case 17:
                            int start9 = start3;
                            TimeZoneFormat.Style style4 = count < 4 ? TimeZoneFormat.Style.SPECIFIC_SHORT : TimeZoneFormat.Style.SPECIFIC_LONG;
                            TimeZone tz = tzFormat().parse(style4, text, pos, tzTimeType);
                            if (tz != null) {
                                cal.setTimeZone(tz);
                                return pos.getIndex();
                            }
                            return ~start9;
                        case 19:
                            start2 = start3;
                            i4 = 6;
                            int value15 = value9;
                            int field9 = field2;
                            value4 = 4;
                            if (count > i && (number3 == null || !getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_ALLOW_NUMERIC))) {
                                field3 = field9;
                                field4 = 3;
                                break;
                            } else {
                                cal.set(field9, value15);
                                return pos.getIndex();
                            }
                        case 23:
                            int start10 = start3;
                            TimeZoneFormat.Style style5 = count < 4 ? TimeZoneFormat.Style.ISO_BASIC_LOCAL_FULL : count == 5 ? TimeZoneFormat.Style.ISO_EXTENDED_FULL : TimeZoneFormat.Style.LOCALIZED_GMT;
                            TimeZone tz2 = tzFormat().parse(style5, text, pos, tzTimeType);
                            if (tz2 != null) {
                                cal.setTimeZone(tz2);
                                return pos.getIndex();
                            }
                            return ~start10;
                        case 24:
                            int start11 = start3;
                            TimeZoneFormat.Style style6 = count < 4 ? TimeZoneFormat.Style.GENERIC_SHORT : TimeZoneFormat.Style.GENERIC_LONG;
                            TimeZone tz3 = tzFormat().parse(style6, text, pos, tzTimeType);
                            if (tz3 != null) {
                                cal.setTimeZone(tz3);
                                return pos.getIndex();
                            }
                            return ~start11;
                        case 25:
                            int value16 = value9;
                            int start12 = start3;
                            if (count == 1 || (number3 != null && getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_ALLOW_NUMERIC))) {
                                cal.set(field2, value16);
                                return pos.getIndex();
                            }
                            int newStart6 = 0;
                            if (getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH) || count == 4) {
                                int matchString2 = matchString(text, start12, 7, this.formatData.standaloneWeekdays, null, cal);
                                newStart6 = matchString2;
                                if (matchString2 > 0) {
                                    return newStart6;
                                }
                            }
                            if (getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH) || count == 3) {
                                int matchString3 = matchString(text, start12, 7, this.formatData.standaloneShortWeekdays, null, cal);
                                newStart6 = matchString3;
                                if (matchString3 > 0) {
                                    return newStart6;
                                }
                            }
                            if ((getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH) || count == 6) && this.formatData.standaloneShorterWeekdays != null) {
                                return matchString(text, start12, 7, this.formatData.standaloneShorterWeekdays, null, cal);
                            }
                            return newStart6;
                        case 27:
                            int value17 = value9;
                            int start13 = start3;
                            if (count > i) {
                                if (number3 == null || !getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_ALLOW_NUMERIC)) {
                                    int newStart7 = 0;
                                    if (getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH) || count == 4) {
                                        int matchQuarterString = matchQuarterString(text, start13, 2, this.formatData.quarters, cal);
                                        newStart7 = matchQuarterString;
                                        if (matchQuarterString > 0) {
                                            return newStart7;
                                        }
                                    }
                                    if (getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH) || count == 3) {
                                        return matchQuarterString(text, start13, 2, this.formatData.shortQuarters, cal);
                                    }
                                    return newStart7;
                                }
                                value6 = value17;
                            } else {
                                value6 = value17;
                            }
                            cal.set(i, (value6 - 1) * 3);
                            return pos.getIndex();
                        case 28:
                            int value18 = value9;
                            int start14 = start3;
                            if (count > i) {
                                if (number3 == null || !getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_ALLOW_NUMERIC)) {
                                    int newStart8 = 0;
                                    if (getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH) || count == 4) {
                                        int matchQuarterString2 = matchQuarterString(text, start14, 2, this.formatData.standaloneQuarters, cal);
                                        newStart8 = matchQuarterString2;
                                        if (matchQuarterString2 > 0) {
                                            return newStart8;
                                        }
                                    }
                                    if (getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH) || count == 3) {
                                        return matchQuarterString(text, start14, 2, this.formatData.standaloneShortQuarters, cal);
                                    }
                                    return newStart8;
                                }
                                value7 = value18;
                            } else {
                                value7 = value18;
                            }
                            cal.set(i, (value7 - 1) * 3);
                            return pos.getIndex();
                        case 29:
                            int start15 = start3;
                            switch (count) {
                                case 1:
                                    style = TimeZoneFormat.Style.ZONE_ID_SHORT;
                                    break;
                                case 2:
                                    style = TimeZoneFormat.Style.ZONE_ID;
                                    break;
                                case 3:
                                    style = TimeZoneFormat.Style.EXEMPLAR_LOCATION;
                                    break;
                                default:
                                    style = TimeZoneFormat.Style.GENERIC_LOCATION;
                                    break;
                            }
                            TimeZone tz4 = tzFormat().parse(style, text, pos, tzTimeType);
                            if (tz4 != null) {
                                cal.setTimeZone(tz4);
                                return pos.getIndex();
                            }
                            return ~start15;
                        case 30:
                            int value19 = value9;
                            int start16 = start3;
                            if (this.formatData.shortYearNames != null && (newStart3 = matchString(text, start16, 1, this.formatData.shortYearNames, null, cal)) > 0) {
                                return newStart3;
                            }
                            if (number3 != null) {
                                if (!getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_ALLOW_NUMERIC) && this.formatData.shortYearNames != null) {
                                    value8 = value19;
                                    break;
                                } else {
                                    value8 = value19;
                                }
                                cal.set(1, value8);
                                return pos.getIndex();
                            }
                            return ~start16;
                        case 31:
                            int start17 = start3;
                            TimeZoneFormat.Style style7 = count < 4 ? TimeZoneFormat.Style.LOCALIZED_GMT_SHORT : TimeZoneFormat.Style.LOCALIZED_GMT;
                            TimeZone tz5 = tzFormat().parse(style7, text, pos, tzTimeType);
                            if (tz5 != null) {
                                cal.setTimeZone(tz5);
                                return pos.getIndex();
                            }
                            return ~start17;
                        case 32:
                            int start18 = start3;
                            switch (count) {
                                case 1:
                                    style2 = TimeZoneFormat.Style.ISO_BASIC_SHORT;
                                    break;
                                case 2:
                                    style2 = TimeZoneFormat.Style.ISO_BASIC_FIXED;
                                    break;
                                case 3:
                                    style2 = TimeZoneFormat.Style.ISO_EXTENDED_FIXED;
                                    break;
                                case 4:
                                    style2 = TimeZoneFormat.Style.ISO_BASIC_FULL;
                                    break;
                                default:
                                    style2 = TimeZoneFormat.Style.ISO_EXTENDED_FULL;
                                    break;
                            }
                            TimeZone tz6 = tzFormat().parse(style2, text, pos, tzTimeType);
                            if (tz6 != null) {
                                cal.setTimeZone(tz6);
                                return pos.getIndex();
                            }
                            return ~start18;
                        case 33:
                            int start19 = start3;
                            switch (count) {
                                case 1:
                                    style3 = TimeZoneFormat.Style.ISO_BASIC_LOCAL_SHORT;
                                    break;
                                case 2:
                                    style3 = TimeZoneFormat.Style.ISO_BASIC_LOCAL_FIXED;
                                    break;
                                case 3:
                                    style3 = TimeZoneFormat.Style.ISO_EXTENDED_LOCAL_FIXED;
                                    break;
                                case 4:
                                    style3 = TimeZoneFormat.Style.ISO_BASIC_LOCAL_FULL;
                                    break;
                                default:
                                    style3 = TimeZoneFormat.Style.ISO_EXTENDED_LOCAL_FULL;
                                    break;
                            }
                            TimeZone tz7 = tzFormat().parse(style3, text, pos, tzTimeType);
                            if (tz7 != null) {
                                cal.setTimeZone(tz7);
                                return pos.getIndex();
                            }
                            return ~start19;
                        case 35:
                            int start20 = start3;
                            int ampmStart = subParse(text, start3, 'a', count, obeyCount, allowNegative, ambiguousYear, cal, numericLeapMonthFormatter, tzTimeType, dayPeriod);
                            if (ampmStart > 0) {
                                return ampmStart;
                            }
                            int newStart9 = 0;
                            if (getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH) || count == 3) {
                                int matchDayPeriodString = matchDayPeriodString(text, start20, this.formatData.abbreviatedDayPeriods, 2, dayPeriod);
                                newStart9 = matchDayPeriodString;
                                if (matchDayPeriodString > 0) {
                                    return newStart9;
                                }
                            }
                            if (getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH) || count == 4) {
                                int matchDayPeriodString2 = matchDayPeriodString(text, start20, this.formatData.wideDayPeriods, 2, dayPeriod);
                                newStart9 = matchDayPeriodString2;
                                if (matchDayPeriodString2 > 0) {
                                    return newStart9;
                                }
                            }
                            if (getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH) || count == 4) {
                                int matchDayPeriodString3 = matchDayPeriodString(text, start20, this.formatData.narrowDayPeriods, 2, dayPeriod);
                                newStart9 = matchDayPeriodString3;
                                if (matchDayPeriodString3 > 0) {
                                    return newStart9;
                                }
                            }
                            return newStart9;
                        case 36:
                            int newStart10 = 0;
                            if (getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH) || count == 3) {
                                int matchDayPeriodString4 = matchDayPeriodString(text, start3, this.formatData.abbreviatedDayPeriods, this.formatData.abbreviatedDayPeriods.length, dayPeriod);
                                newStart10 = matchDayPeriodString4;
                                if (matchDayPeriodString4 > 0) {
                                    return newStart10;
                                }
                            }
                            if (getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH) || count == 4) {
                                int matchDayPeriodString5 = matchDayPeriodString(text, start3, this.formatData.wideDayPeriods, this.formatData.wideDayPeriods.length, dayPeriod);
                                newStart10 = matchDayPeriodString5;
                                if (matchDayPeriodString5 > 0) {
                                    return newStart10;
                                }
                            }
                            if (getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH) || count == 4) {
                                int matchDayPeriodString6 = matchDayPeriodString(text, start3, this.formatData.narrowDayPeriods, this.formatData.narrowDayPeriods.length, dayPeriod);
                                newStart10 = matchDayPeriodString6;
                                if (matchDayPeriodString6 > 0) {
                                    return newStart10;
                                }
                            }
                            return newStart10;
                        case 37:
                            ArrayList<String> data = new ArrayList<>(3);
                            data.add(this.formatData.getTimeSeparatorString());
                            if (!this.formatData.getTimeSeparatorString().equals(":")) {
                                data.add(":");
                            }
                            if (getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_PARTIAL_LITERAL_MATCH) && !this.formatData.getTimeSeparatorString().equals(".")) {
                                data.add(".");
                            }
                            return matchString(text, start3, -1, (String[]) data.toArray(new String[0]), cal);
                    }
                    int newStart11 = 0;
                    if (getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH) || count == value4) {
                        field5 = field4;
                        i5 = i4;
                        int matchString4 = matchString(text, start2, 7, this.formatData.weekdays, null, cal);
                        newStart11 = matchString4;
                        if (matchString4 > 0) {
                            return newStart11;
                        }
                    } else {
                        i5 = i4;
                        field5 = field4;
                    }
                    if (getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH) || count == field5) {
                        int matchString5 = matchString(text, start2, 7, this.formatData.shortWeekdays, null, cal);
                        newStart11 = matchString5;
                        if (matchString5 > 0) {
                            return newStart11;
                        }
                    }
                    if ((getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH) || count == i5) && this.formatData.shorterWeekdays != null) {
                        int matchString6 = matchString(text, start2, 7, this.formatData.shorterWeekdays, null, cal);
                        newStart11 = matchString6;
                        if (matchString6 > 0) {
                            return newStart11;
                        }
                    }
                    if ((getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH) || count == 5) && this.formatData.narrowWeekdays != null) {
                        int matchString7 = matchString(text, start2, 7, this.formatData.narrowWeekdays, null, cal);
                        newStart11 = matchString7;
                        if (matchString7 > 0) {
                            return newStart11;
                        }
                    }
                    return newStart11;
                }
            }
            return ~start3;
        }
        return ~start;
    }

    private boolean allowNumericFallback(int patternCharIndex) {
        if (patternCharIndex == 26 || patternCharIndex == 19 || patternCharIndex == 25 || patternCharIndex == 30 || patternCharIndex == 27 || patternCharIndex == 28) {
            return true;
        }
        return false;
    }

    private Number parseInt(String text, ParsePosition pos, boolean allowNegative, NumberFormat fmt) {
        return parseInt(text, -1, pos, allowNegative, fmt);
    }

    private Number parseInt(String text, int maxDigits, ParsePosition pos, boolean allowNegative, NumberFormat fmt) {
        Number number;
        int nDigits;
        int oldPos = pos.getIndex();
        if (allowNegative) {
            number = fmt.parse(text, pos);
        } else if (fmt instanceof DecimalFormat) {
            String oldPrefix = ((DecimalFormat) fmt).getNegativePrefix();
            ((DecimalFormat) fmt).setNegativePrefix(SUPPRESS_NEGATIVE_PREFIX);
            Number number2 = fmt.parse(text, pos);
            ((DecimalFormat) fmt).setNegativePrefix(oldPrefix);
            number = number2;
        } else {
            boolean dateNumberFormat = fmt instanceof DateNumberFormat;
            if (dateNumberFormat) {
                ((DateNumberFormat) fmt).setParsePositiveOnly(true);
            }
            Number number3 = fmt.parse(text, pos);
            if (dateNumberFormat) {
                ((DateNumberFormat) fmt).setParsePositiveOnly(false);
            }
            number = number3;
        }
        if (maxDigits > 0 && (nDigits = pos.getIndex() - oldPos) > maxDigits) {
            double val = number.doubleValue();
            for (int nDigits2 = nDigits - maxDigits; nDigits2 > 0; nDigits2--) {
                val /= 10.0d;
            }
            pos.setIndex(oldPos + maxDigits);
            Number number4 = Integer.valueOf((int) val);
            return number4;
        }
        return number;
    }

    private static int countDigits(String text, int start, int end) {
        int numDigits = 0;
        int idx = start;
        while (idx < end) {
            int cp = text.codePointAt(idx);
            if (UCharacter.isDigit(cp)) {
                numDigits++;
            }
            idx += UCharacter.charCount(cp);
        }
        return numDigits;
    }

    private String translatePattern(String pat, String from, String to) {
        int ci;
        StringBuilder result = new StringBuilder();
        boolean inQuote = false;
        for (int i = 0; i < pat.length(); i++) {
            char c = pat.charAt(i);
            if (inQuote) {
                if (c == '\'') {
                    inQuote = false;
                }
            } else if (c == '\'') {
                inQuote = true;
            } else if (isSyntaxChar(c) && (ci = from.indexOf(c)) != -1) {
                c = to.charAt(ci);
            }
            result.append(c);
        }
        if (inQuote) {
            throw new IllegalArgumentException("Unfinished quote in pattern");
        }
        return result.toString();
    }

    public String toPattern() {
        return this.pattern;
    }

    public String toLocalizedPattern() {
        return translatePattern(this.pattern, "GyMdkHmsSEDFwWahKzYeugAZvcLQqVUOXxrbB", this.formatData.localPatternChars);
    }

    public void applyPattern(String pat) {
        this.pattern = pat;
        parsePattern();
        setLocale(null, null);
        this.patternItems = null;
    }

    public void applyLocalizedPattern(String pat) {
        this.pattern = translatePattern(pat, this.formatData.localPatternChars, "GyMdkHmsSEDFwWahKzYeugAZvcLQqVUOXxrbB");
        setLocale(null, null);
    }

    public DateFormatSymbols getDateFormatSymbols() {
        return (DateFormatSymbols) this.formatData.clone();
    }

    public void setDateFormatSymbols(DateFormatSymbols newFormatSymbols) {
        this.formatData = (DateFormatSymbols) newFormatSymbols.clone();
    }

    protected DateFormatSymbols getSymbols() {
        return this.formatData;
    }

    public TimeZoneFormat getTimeZoneFormat() {
        return tzFormat().m85freeze();
    }

    public void setTimeZoneFormat(TimeZoneFormat tzfmt) {
        if (tzfmt.isFrozen()) {
            this.tzFormat = tzfmt;
        } else {
            this.tzFormat = tzfmt.m84cloneAsThawed().m85freeze();
        }
    }

    @Override // com.ibm.icu.text.DateFormat, java.text.Format
    public Object clone() {
        SimpleDateFormat other = (SimpleDateFormat) super.clone();
        other.formatData = (DateFormatSymbols) this.formatData.clone();
        if (this.decimalBuf != null) {
            other.decimalBuf = new char[10];
        }
        return other;
    }

    @Override // com.ibm.icu.text.DateFormat
    public int hashCode() {
        return this.pattern.hashCode();
    }

    @Override // com.ibm.icu.text.DateFormat
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            SimpleDateFormat that = (SimpleDateFormat) obj;
            return this.pattern.equals(that.pattern) && this.formatData.equals(that.formatData);
        }
        return false;
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        if (this.defaultCenturyStart == null) {
            initializeDefaultCenturyStart(this.defaultCenturyBase);
        }
        initializeTimeZoneFormat(false);
        stream.defaultWriteObject();
        stream.writeInt(getContext(DisplayContext.Type.CAPITALIZATION).value());
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        int capitalizationSettingValue = this.serialVersionOnStream > 1 ? stream.readInt() : -1;
        if (this.serialVersionOnStream < 1) {
            this.defaultCenturyBase = System.currentTimeMillis();
        } else {
            parseAmbiguousDatesAsAfter(this.defaultCenturyStart);
        }
        this.serialVersionOnStream = 2;
        ULocale locale = getLocale(ULocale.VALID_LOCALE);
        this.locale = locale;
        if (locale == null) {
            this.locale = ULocale.getDefault(ULocale.Category.FORMAT);
        }
        initLocalZeroPaddingNumberFormat();
        setContext(DisplayContext.CAPITALIZATION_NONE);
        if (capitalizationSettingValue >= 0) {
            DisplayContext[] values = DisplayContext.values();
            int length = values.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                DisplayContext context = values[i];
                if (context.value() != capitalizationSettingValue) {
                    i++;
                } else {
                    setContext(context);
                    break;
                }
            }
        }
        if (!getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_PARTIAL_MATCH)) {
            setBooleanAttribute(DateFormat.BooleanAttribute.PARSE_PARTIAL_LITERAL_MATCH, false);
        }
        parsePattern();
    }

    @Override // java.text.Format
    public AttributedCharacterIterator formatToCharacterIterator(Object obj) {
        Calendar cal = this.calendar;
        if (obj instanceof Calendar) {
            cal = (Calendar) obj;
        } else if (obj instanceof Date) {
            this.calendar.setTime((Date) obj);
        } else if (obj instanceof Number) {
            this.calendar.setTimeInMillis(((Number) obj).longValue());
        } else {
            throw new IllegalArgumentException("Cannot format given Object as a Date");
        }
        StringBuffer toAppendTo = new StringBuffer();
        FieldPosition pos = new FieldPosition(0);
        List<FieldPosition> attributes = new ArrayList<>();
        format(cal, getContext(DisplayContext.Type.CAPITALIZATION), toAppendTo, pos, attributes);
        AttributedString as = new AttributedString(toAppendTo.toString());
        for (int i = 0; i < attributes.size(); i++) {
            FieldPosition fp = attributes.get(i);
            Format.Field attribute = fp.getFieldAttribute();
            as.addAttribute(attribute, attribute, fp.getBeginIndex(), fp.getEndIndex());
        }
        return as.getIterator();
    }

    ULocale getLocale() {
        return this.locale;
    }

    boolean isFieldUnitIgnored(int field) {
        return isFieldUnitIgnored(this.pattern, field);
    }

    static boolean isFieldUnitIgnored(String pattern, int field) {
        int fieldLevel = CALENDAR_FIELD_TO_LEVEL[field];
        boolean inQuote = false;
        char prevCh = 0;
        int count = 0;
        int i = 0;
        while (true) {
            if (i < pattern.length()) {
                char ch = pattern.charAt(i);
                if (ch != prevCh && count > 0) {
                    int level = getLevelFromChar(prevCh);
                    if (fieldLevel <= level) {
                        return false;
                    }
                    count = 0;
                }
                if (ch == '\'') {
                    if (i + 1 < pattern.length() && pattern.charAt(i + 1) == '\'') {
                        i++;
                    } else {
                        inQuote = inQuote ? false : true;
                    }
                } else if (!inQuote && isSyntaxChar(ch)) {
                    prevCh = ch;
                    count++;
                }
                i++;
            } else {
                if (count > 0) {
                    int level2 = getLevelFromChar(prevCh);
                    if (fieldLevel <= level2) {
                        return false;
                    }
                }
                return true;
            }
        }
    }

    @Deprecated
    public final StringBuffer intervalFormatByAlgorithm(Calendar fromCalendar, Calendar toCalendar, StringBuffer appendTo, FieldPosition pos) throws IllegalArgumentException {
        int diffBegin;
        IllegalArgumentException e;
        int diffEnd;
        int i;
        int highestLevel;
        String str;
        if (!fromCalendar.isEquivalentTo(toCalendar)) {
            throw new IllegalArgumentException("can not format on two different calendars");
        }
        Object[] items = getPatternItems();
        int diffBegin2 = -1;
        int diffEnd2 = -1;
        int i2 = 0;
        while (true) {
            try {
                if (i2 < items.length) {
                    if (!diffCalFieldValue(fromCalendar, toCalendar, items, i2)) {
                        i2++;
                    } else {
                        diffBegin2 = i2;
                        break;
                    }
                } else {
                    break;
                }
            } catch (IllegalArgumentException e2) {
                throw new IllegalArgumentException(e2.toString());
            }
        }
        if (diffBegin2 == -1) {
            return format(fromCalendar, appendTo, pos);
        }
        int i3 = items.length - 1;
        while (true) {
            if (i3 >= diffBegin2) {
                if (diffCalFieldValue(fromCalendar, toCalendar, items, i3)) {
                    diffEnd2 = i3;
                    break;
                } else {
                    i3--;
                }
            } else {
                break;
            }
        }
        String str2 = " \u2013 ";
        if (diffBegin2 == 0 && diffEnd2 == items.length - 1) {
            format(fromCalendar, appendTo, pos);
            appendTo.append(" \u2013 ");
            format(toCalendar, appendTo, pos);
            return appendTo;
        }
        int highestLevel2 = 1000;
        for (int i4 = diffBegin2; i4 <= diffEnd2; i4++) {
            if (!(items[i4] instanceof String)) {
                char ch = ((PatternItem) items[i4]).type;
                int patternCharIndex = getIndexFromChar(ch);
                if (patternCharIndex == -1) {
                    throw new IllegalArgumentException("Illegal pattern character '" + ch + "' in \"" + this.pattern + Typography.quote);
                }
                if (patternCharIndex < highestLevel2) {
                    highestLevel2 = patternCharIndex;
                }
            }
        }
        int i5 = 0;
        while (true) {
            if (i5 >= diffBegin2) {
                diffBegin = diffBegin2;
                break;
            }
            try {
                if (!lowerLevel(items, i5, highestLevel2)) {
                    i5++;
                } else {
                    int diffBegin3 = i5;
                    diffBegin = diffBegin3;
                    break;
                }
            } catch (IllegalArgumentException e3) {
                e = e3;
                throw new IllegalArgumentException(e.toString());
            }
        }
        try {
            int i6 = items.length;
            int i7 = i6 - 1;
            while (true) {
                if (i7 <= diffEnd2) {
                    i7 = diffEnd2;
                    break;
                }
                try {
                    if (lowerLevel(items, i7, highestLevel2)) {
                        break;
                    }
                    i7--;
                } catch (IllegalArgumentException e4) {
                    e = e4;
                    throw new IllegalArgumentException(e.toString());
                }
            }
            if (diffBegin == 0 && i7 == items.length - 1) {
                format(fromCalendar, appendTo, pos);
                appendTo.append(" \u2013 ");
                format(toCalendar, appendTo, pos);
                return appendTo;
            }
            pos.setBeginIndex(0);
            pos.setEndIndex(0);
            DisplayContext capSetting = getContext(DisplayContext.Type.CAPITALIZATION);
            int i8 = 0;
            while (i8 <= i7) {
                if (items[i8] instanceof String) {
                    appendTo.append((String) items[i8]);
                    diffEnd = i7;
                    i = i8;
                    highestLevel = highestLevel2;
                    str = str2;
                } else {
                    PatternItem item = (PatternItem) items[i8];
                    if (this.useFastFormat) {
                        diffEnd = i7;
                        i = i8;
                        highestLevel = highestLevel2;
                        str = str2;
                        subFormat(appendTo, item.type, item.length, appendTo.length(), i8, capSetting, pos, fromCalendar);
                    } else {
                        diffEnd = i7;
                        i = i8;
                        highestLevel = highestLevel2;
                        str = str2;
                        appendTo.append(subFormat(item.type, item.length, appendTo.length(), i, capSetting, pos, fromCalendar));
                    }
                }
                i8 = i + 1;
                str2 = str;
                i7 = diffEnd;
                highestLevel2 = highestLevel;
            }
            appendTo.append(str2);
            for (int i9 = diffBegin; i9 < items.length; i9++) {
                if (items[i9] instanceof String) {
                    appendTo.append((String) items[i9]);
                } else {
                    PatternItem item2 = (PatternItem) items[i9];
                    if (this.useFastFormat) {
                        subFormat(appendTo, item2.type, item2.length, appendTo.length(), i9, capSetting, pos, toCalendar);
                    } else {
                        appendTo.append(subFormat(item2.type, item2.length, appendTo.length(), i9, capSetting, pos, toCalendar));
                    }
                }
            }
            return appendTo;
        } catch (IllegalArgumentException e5) {
            e = e5;
        }
    }

    private boolean diffCalFieldValue(Calendar fromCalendar, Calendar toCalendar, Object[] items, int i) throws IllegalArgumentException {
        if (items[i] instanceof String) {
            return false;
        }
        PatternItem item = (PatternItem) items[i];
        char ch = item.type;
        int patternCharIndex = getIndexFromChar(ch);
        if (patternCharIndex == -1) {
            throw new IllegalArgumentException("Illegal pattern character '" + ch + "' in \"" + this.pattern + Typography.quote);
        }
        int field = PATTERN_INDEX_TO_CALENDAR_FIELD[patternCharIndex];
        if (field >= 0) {
            int value = fromCalendar.get(field);
            int value_2 = toCalendar.get(field);
            if (value != value_2) {
                return true;
            }
        }
        return false;
    }

    private boolean lowerLevel(Object[] items, int i, int level) throws IllegalArgumentException {
        if (items[i] instanceof String) {
            return false;
        }
        PatternItem item = (PatternItem) items[i];
        char ch = item.type;
        int patternCharIndex = getLevelFromChar(ch);
        if (patternCharIndex != -1) {
            return patternCharIndex >= level;
        }
        throw new IllegalArgumentException("Illegal pattern character '" + ch + "' in \"" + this.pattern + Typography.quote);
    }

    public void setNumberFormat(String fields, NumberFormat overrideNF) {
        overrideNF.setGroupingUsed(false);
        String nsName = "$" + UUID.randomUUID().toString();
        if (this.numberFormatters == null) {
            this.numberFormatters = new HashMap<>();
        }
        if (this.overrideMap == null) {
            this.overrideMap = new HashMap<>();
        }
        for (int i = 0; i < fields.length(); i++) {
            char field = fields.charAt(i);
            if ("GyMdkHmsSEDFwWahKzYeugAZvcLQqVUOXxrbB".indexOf(field) == -1) {
                throw new IllegalArgumentException("Illegal field character '" + field + "' in setNumberFormat.");
            }
            this.overrideMap.put(Character.valueOf(field), nsName);
            this.numberFormatters.put(nsName, overrideNF);
        }
        this.useLocalZeroPaddingNumberFormat = false;
    }

    public NumberFormat getNumberFormat(char field) {
        Character ovrField = Character.valueOf(field);
        HashMap<Character, String> hashMap = this.overrideMap;
        if (hashMap != null && hashMap.containsKey(ovrField)) {
            String nsName = this.overrideMap.get(ovrField).toString();
            NumberFormat nf = this.numberFormatters.get(nsName);
            return nf;
        }
        return this.numberFormat;
    }

    private void initNumberFormatters(ULocale loc) {
        this.numberFormatters = new HashMap<>();
        this.overrideMap = new HashMap<>();
        processOverrideString(loc, this.override);
    }

    private void processOverrideString(ULocale loc, String str) {
        int end;
        String nsName;
        boolean fullOverride;
        if (str == null || str.length() == 0) {
            return;
        }
        int start = 0;
        boolean moreToProcess = true;
        while (moreToProcess) {
            int delimiterPosition = str.indexOf(";", start);
            if (delimiterPosition == -1) {
                moreToProcess = false;
                end = str.length();
            } else {
                end = delimiterPosition;
            }
            String currentString = str.substring(start, end);
            int equalSignPosition = currentString.indexOf("=");
            if (equalSignPosition == -1) {
                nsName = currentString;
                fullOverride = true;
            } else {
                nsName = currentString.substring(equalSignPosition + 1);
                Character ovrField = Character.valueOf(currentString.charAt(0));
                this.overrideMap.put(ovrField, nsName);
                fullOverride = false;
            }
            ULocale ovrLoc = new ULocale(loc.getBaseName() + "@numbers=" + nsName);
            NumberFormat nf = NumberFormat.createInstance(ovrLoc, 0);
            nf.setGroupingUsed(false);
            if (fullOverride) {
                setNumberFormat(nf);
            } else {
                this.useLocalZeroPaddingNumberFormat = false;
            }
            if (!fullOverride && !this.numberFormatters.containsKey(nsName)) {
                this.numberFormatters.put(nsName, nf);
            }
            start = delimiterPosition + 1;
        }
    }

    private void parsePattern() {
        this.hasMinute = false;
        this.hasSecond = false;
        boolean inQuote = false;
        for (int i = 0; i < this.pattern.length(); i++) {
            char ch = this.pattern.charAt(i);
            if (ch == '\'') {
                inQuote = !inQuote;
            }
            if (!inQuote) {
                if (ch == 'm') {
                    this.hasMinute = true;
                }
                if (ch == 's') {
                    this.hasSecond = true;
                }
            }
        }
    }
}
