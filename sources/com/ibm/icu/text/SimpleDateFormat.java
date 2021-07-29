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
import com.ibm.icu.text.DisplayContext;
import com.ibm.icu.text.TimeZoneFormat;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.Output;
import com.ibm.icu.util.TimeZone;
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

public class SimpleDateFormat extends DateFormat {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int[] CALENDAR_FIELD_TO_LEVEL = {0, 10, 20, 20, 30, 30, 20, 30, 30, 40, 50, 50, 60, 70, 80, 0, 0, 10, 30, 10, 0, 40, 0, 0};
    static final UnicodeSet DATE_PATTERN_TYPE = new UnicodeSet("[GyYuUQqMLlwWd]").freeze();
    private static final int DECIMAL_BUF_SIZE = 10;
    static boolean DelayedHebrewMonthCheck = false;
    private static final String FALLBACKPATTERN = "yy/MM/dd HH:mm";
    private static final int HEBREW_CAL_CUR_MILLENIUM_END_YEAR = 6000;
    private static final int HEBREW_CAL_CUR_MILLENIUM_START_YEAR = 5000;
    private static final int ISOSpecialEra = -32000;
    private static final String NUMERIC_FORMAT_CHARS = "ADdFgHhKkmrSsuWwYy";
    private static final String NUMERIC_FORMAT_CHARS2 = "ceLMQq";
    private static ICUCache<String, Object[]> PARSED_PATTERN_CACHE = new SimpleCache();
    private static final boolean[] PATTERN_CHAR_IS_SYNTAX = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, false, false, false, false};
    private static final int[] PATTERN_CHAR_TO_INDEX = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 22, 36, -1, 10, 9, 11, 0, 5, -1, -1, 16, 26, 2, -1, 31, -1, 27, -1, 8, -1, 30, 29, 13, 32, 18, 23, -1, -1, -1, -1, -1, -1, 14, 35, 25, 3, 19, -1, 21, 15, -1, -1, 4, -1, 6, -1, -1, -1, 28, 34, 7, -1, 20, 24, 12, 33, 1, 17, -1, -1, -1, -1, -1};
    private static final int[] PATTERN_CHAR_TO_LEVEL = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 40, -1, -1, 20, 30, 30, 0, 50, -1, -1, 50, 20, 20, -1, 0, -1, 20, -1, 80, -1, 10, 0, 30, 0, 10, 0, -1, -1, -1, -1, -1, -1, 40, -1, 30, 30, 30, -1, 0, 50, -1, -1, 50, -1, 60, -1, -1, -1, 20, 10, 70, -1, 10, 0, 20, 0, 10, 0, -1, -1, -1, -1, -1};
    private static final int[] PATTERN_INDEX_TO_CALENDAR_FIELD = {0, 1, 2, 5, 11, 11, 12, 13, 14, 7, 6, 8, 3, 4, 9, 10, 10, 15, 17, 18, 19, 20, 21, 15, 15, 18, 2, 2, 2, 15, 1, 15, 15, 15, 19, -1, -2};
    private static final DateFormat.Field[] PATTERN_INDEX_TO_DATE_FORMAT_ATTRIBUTE = {DateFormat.Field.ERA, DateFormat.Field.YEAR, DateFormat.Field.MONTH, DateFormat.Field.DAY_OF_MONTH, DateFormat.Field.HOUR_OF_DAY1, DateFormat.Field.HOUR_OF_DAY0, DateFormat.Field.MINUTE, DateFormat.Field.SECOND, DateFormat.Field.MILLISECOND, DateFormat.Field.DAY_OF_WEEK, DateFormat.Field.DAY_OF_YEAR, DateFormat.Field.DAY_OF_WEEK_IN_MONTH, DateFormat.Field.WEEK_OF_YEAR, DateFormat.Field.WEEK_OF_MONTH, DateFormat.Field.AM_PM, DateFormat.Field.HOUR1, DateFormat.Field.HOUR0, DateFormat.Field.TIME_ZONE, DateFormat.Field.YEAR_WOY, DateFormat.Field.DOW_LOCAL, DateFormat.Field.EXTENDED_YEAR, DateFormat.Field.JULIAN_DAY, DateFormat.Field.MILLISECONDS_IN_DAY, DateFormat.Field.TIME_ZONE, DateFormat.Field.TIME_ZONE, DateFormat.Field.DAY_OF_WEEK, DateFormat.Field.MONTH, DateFormat.Field.QUARTER, DateFormat.Field.QUARTER, DateFormat.Field.TIME_ZONE, DateFormat.Field.YEAR, DateFormat.Field.TIME_ZONE, DateFormat.Field.TIME_ZONE, DateFormat.Field.TIME_ZONE, DateFormat.Field.RELATED_YEAR, DateFormat.Field.AM_PM_MIDNIGHT_NOON, DateFormat.Field.FLEXIBLE_DAY_PERIOD, DateFormat.Field.TIME_SEPARATOR};
    private static final int[] PATTERN_INDEX_TO_DATE_FORMAT_FIELD = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37};
    private static final String SUPPRESS_NEGATIVE_PREFIX = "꬀";
    private static ULocale cachedDefaultLocale = null;
    private static String cachedDefaultPattern = null;
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
            return iArr[ch & 255];
        }
        return -1;
    }

    private static boolean isSyntaxChar(char ch) {
        boolean[] zArr = PATTERN_CHAR_IS_SYNTAX;
        if (ch < zArr.length) {
            return zArr[ch & 255];
        }
        return false;
    }

    public SimpleDateFormat() {
        this(getDefaultPattern(), (DateFormatSymbols) null, (Calendar) null, (NumberFormat) null, (ULocale) null, true, (String) null);
    }

    public SimpleDateFormat(String pattern2) {
        this(pattern2, (DateFormatSymbols) null, (Calendar) null, (NumberFormat) null, (ULocale) null, true, (String) null);
    }

    public SimpleDateFormat(String pattern2, Locale loc) {
        this(pattern2, (DateFormatSymbols) null, (Calendar) null, (NumberFormat) null, ULocale.forLocale(loc), true, (String) null);
    }

    public SimpleDateFormat(String pattern2, ULocale loc) {
        this(pattern2, (DateFormatSymbols) null, (Calendar) null, (NumberFormat) null, loc, true, (String) null);
    }

    public SimpleDateFormat(String pattern2, String override2, ULocale loc) {
        this(pattern2, (DateFormatSymbols) null, (Calendar) null, (NumberFormat) null, loc, false, override2);
    }

    public SimpleDateFormat(String pattern2, DateFormatSymbols formatData2) {
        this(pattern2, (DateFormatSymbols) formatData2.clone(), (Calendar) null, (NumberFormat) null, (ULocale) null, true, (String) null);
    }

    @Deprecated
    public SimpleDateFormat(String pattern2, DateFormatSymbols formatData2, ULocale loc) {
        this(pattern2, (DateFormatSymbols) formatData2.clone(), (Calendar) null, (NumberFormat) null, loc, true, (String) null);
    }

    SimpleDateFormat(String pattern2, DateFormatSymbols formatData2, Calendar calendar, ULocale locale2, boolean useFastFormat2, String override2) {
        this(pattern2, (DateFormatSymbols) formatData2.clone(), (Calendar) calendar.clone(), (NumberFormat) null, locale2, useFastFormat2, override2);
    }

    private SimpleDateFormat(String pattern2, DateFormatSymbols formatData2, Calendar calendar, NumberFormat numberFormat, ULocale locale2, boolean useFastFormat2, String override2) {
        this.serialVersionOnStream = 2;
        this.capitalizationBrkIter = null;
        this.pattern = pattern2;
        this.formatData = formatData2;
        this.calendar = calendar;
        this.numberFormat = numberFormat;
        this.locale = locale2;
        this.useFastFormat = useFastFormat2;
        this.override = override2;
        initialize();
    }

    @Deprecated
    public static SimpleDateFormat getInstance(Calendar.FormatConfiguration formatConfig) {
        String ostr = formatConfig.getOverrideString();
        return new SimpleDateFormat(formatConfig.getPatternString(), formatConfig.getDateFormatSymbols(), formatConfig.getCalendar(), (NumberFormat) null, formatConfig.getLocale(), ostr != null && ostr.length() > 0, formatConfig.getOverrideString());
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
                this.numberFormat = NumberFormat.getInstance((ULocale) this.locale);
            } else {
                this.numberFormat = new DateNumberFormat(this.locale, digitString, ns.getName());
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

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0005, code lost:
        if (r7.tzFormat == null) goto L_0x0007;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void initializeTimeZoneFormat(boolean r8) {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r8 != 0) goto L_0x0007
            com.ibm.icu.text.TimeZoneFormat r0 = r7.tzFormat     // Catch:{ all -> 0x0075 }
            if (r0 != 0) goto L_0x0073
        L_0x0007:
            com.ibm.icu.util.ULocale r0 = r7.locale     // Catch:{ all -> 0x0075 }
            com.ibm.icu.text.TimeZoneFormat r0 = com.ibm.icu.text.TimeZoneFormat.getInstance((com.ibm.icu.util.ULocale) r0)     // Catch:{ all -> 0x0075 }
            r7.tzFormat = r0     // Catch:{ all -> 0x0075 }
            r0 = 0
            com.ibm.icu.text.NumberFormat r1 = r7.numberFormat     // Catch:{ all -> 0x0075 }
            boolean r1 = r1 instanceof com.ibm.icu.text.DecimalFormat     // Catch:{ all -> 0x0075 }
            if (r1 == 0) goto L_0x003a
            com.ibm.icu.text.NumberFormat r1 = r7.numberFormat     // Catch:{ all -> 0x0075 }
            com.ibm.icu.text.DecimalFormat r1 = (com.ibm.icu.text.DecimalFormat) r1     // Catch:{ all -> 0x0075 }
            com.ibm.icu.text.DecimalFormatSymbols r1 = r1.getDecimalFormatSymbols()     // Catch:{ all -> 0x0075 }
            java.lang.String[] r2 = r1.getDigitStringsLocal()     // Catch:{ all -> 0x0075 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0075 }
            r3.<init>()     // Catch:{ all -> 0x0075 }
            int r4 = r2.length     // Catch:{ all -> 0x0075 }
            r5 = 0
        L_0x0029:
            if (r5 >= r4) goto L_0x0034
            r6 = r2[r5]     // Catch:{ all -> 0x0075 }
            r3.append(r6)     // Catch:{ all -> 0x0075 }
            int r5 = r5 + 1
            goto L_0x0029
        L_0x0034:
            java.lang.String r4 = r3.toString()     // Catch:{ all -> 0x0075 }
            r0 = r4
            goto L_0x004f
        L_0x003a:
            com.ibm.icu.text.NumberFormat r1 = r7.numberFormat     // Catch:{ all -> 0x0075 }
            boolean r1 = r1 instanceof com.ibm.icu.impl.DateNumberFormat     // Catch:{ all -> 0x0075 }
            if (r1 == 0) goto L_0x004f
            java.lang.String r1 = new java.lang.String     // Catch:{ all -> 0x0075 }
            com.ibm.icu.text.NumberFormat r2 = r7.numberFormat     // Catch:{ all -> 0x0075 }
            com.ibm.icu.impl.DateNumberFormat r2 = (com.ibm.icu.impl.DateNumberFormat) r2     // Catch:{ all -> 0x0075 }
            char[] r2 = r2.getDigits()     // Catch:{ all -> 0x0075 }
            r1.<init>(r2)     // Catch:{ all -> 0x0075 }
            r0 = r1
            goto L_0x0050
        L_0x004f:
        L_0x0050:
            if (r0 == 0) goto L_0x0073
            com.ibm.icu.text.TimeZoneFormat r1 = r7.tzFormat     // Catch:{ all -> 0x0075 }
            java.lang.String r1 = r1.getGMTOffsetDigits()     // Catch:{ all -> 0x0075 }
            boolean r1 = r1.equals(r0)     // Catch:{ all -> 0x0075 }
            if (r1 != 0) goto L_0x0073
            com.ibm.icu.text.TimeZoneFormat r1 = r7.tzFormat     // Catch:{ all -> 0x0075 }
            boolean r1 = r1.isFrozen()     // Catch:{ all -> 0x0075 }
            if (r1 == 0) goto L_0x006e
            com.ibm.icu.text.TimeZoneFormat r1 = r7.tzFormat     // Catch:{ all -> 0x0075 }
            com.ibm.icu.text.TimeZoneFormat r1 = r1.cloneAsThawed()     // Catch:{ all -> 0x0075 }
            r7.tzFormat = r1     // Catch:{ all -> 0x0075 }
        L_0x006e:
            com.ibm.icu.text.TimeZoneFormat r1 = r7.tzFormat     // Catch:{ all -> 0x0075 }
            r1.setGMTOffsetDigits(r0)     // Catch:{ all -> 0x0075 }
        L_0x0073:
            monitor-exit(r7)
            return
        L_0x0075:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.SimpleDateFormat.initializeTimeZoneFormat(boolean):void");
    }

    private TimeZoneFormat tzFormat() {
        if (this.tzFormat == null) {
            initializeTimeZoneFormat(false);
        }
        return this.tzFormat;
    }

    private static synchronized String getDefaultPattern() {
        Calendar cal;
        synchronized (SimpleDateFormat.class) {
            ULocale defaultLocale = ULocale.getDefault(ULocale.Category.FORMAT);
            if (!defaultLocale.equals(cachedDefaultLocale)) {
                cachedDefaultLocale = defaultLocale;
                Calendar cal2 = Calendar.getInstance(defaultLocale);
                try {
                    ICUResourceBundle rb = UResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudt63b", cachedDefaultLocale);
                    ICUResourceBundle patternsRb = rb.findWithFallback("calendar/" + cal2.getType() + "/DateTimePatterns");
                    if (patternsRb == null) {
                        patternsRb = rb.findWithFallback("calendar/gregorian/DateTimePatterns");
                    }
                    if (patternsRb != null) {
                        if (patternsRb.getSize() >= 9) {
                            int defaultIndex = 8;
                            if (patternsRb.getSize() >= 13) {
                                defaultIndex = 8 + 4;
                            }
                            cachedDefaultPattern = SimpleFormatterImpl.formatRawPattern(patternsRb.getString(defaultIndex), 2, 2, new CharSequence[]{patternsRb.getString(3), patternsRb.getString(7)});
                        }
                    }
                    cachedDefaultPattern = FALLBACKPATTERN;
                } catch (MissingResourceException e) {
                    cachedDefaultPattern = FALLBACKPATTERN;
                }
            }
            cal = cachedDefaultPattern;
        }
        return cal;
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

    public void setContext(DisplayContext context) {
        super.setContext(context);
        if (this.capitalizationBrkIter != null) {
            return;
        }
        if (context == DisplayContext.CAPITALIZATION_FOR_BEGINNING_OF_SENTENCE || context == DisplayContext.CAPITALIZATION_FOR_UI_LIST_OR_MENU || context == DisplayContext.CAPITALIZATION_FOR_STANDALONE) {
            this.capitalizationBrkIter = BreakIterator.getSentenceInstance(this.locale);
        }
    }

    public StringBuffer format(Calendar cal, StringBuffer toAppendTo, FieldPosition pos) {
        TimeZone backupTZ = null;
        if (cal != this.calendar && !cal.getType().equals(this.calendar.getType())) {
            this.calendar.setTimeInMillis(cal.getTimeInMillis());
            backupTZ = this.calendar.getTimeZone();
            this.calendar.setTimeZone(cal.getTimeZone());
            cal = this.calendar;
        }
        StringBuffer result = format(cal, getContext(DisplayContext.Type.CAPITALIZATION), toAppendTo, pos, (List<FieldPosition>) null);
        if (backupTZ != null) {
            this.calendar.setTimeZone(backupTZ);
        }
        return result;
    }

    private StringBuffer format(Calendar cal, DisplayContext capitalizationContext, StringBuffer toAppendTo, FieldPosition pos, List<FieldPosition> attributes) {
        int start;
        int start2;
        StringBuffer stringBuffer = toAppendTo;
        FieldPosition fieldPosition = pos;
        List<FieldPosition> list = attributes;
        fieldPosition.setBeginIndex(0);
        fieldPosition.setEndIndex(0);
        Object[] items = getPatternItems();
        int i = 0;
        while (i < items.length) {
            if (items[i] instanceof String) {
                stringBuffer.append((String) items[i]);
            } else {
                PatternItem item = (PatternItem) items[i];
                if (list != null) {
                    start = toAppendTo.length();
                } else {
                    start = 0;
                }
                if (this.useFastFormat != 0) {
                    start2 = start;
                    subFormat(toAppendTo, item.type, item.length, toAppendTo.length(), i, capitalizationContext, pos, cal);
                } else {
                    start2 = start;
                    stringBuffer.append(subFormat(item.type, item.length, toAppendTo.length(), i, capitalizationContext, pos, cal));
                }
                if (list != null) {
                    int end = toAppendTo.length();
                    if (end - start2 > 0) {
                        FieldPosition fp = new FieldPosition(patternCharToDateFormatField(item.type));
                        fp.setBeginIndex(start2);
                        fp.setEndIndex(end);
                        list.add(fp);
                    }
                }
            }
            i++;
            FieldPosition fieldPosition2 = pos;
        }
        return stringBuffer;
    }

    private static int getIndexFromChar(char ch) {
        int[] iArr = PATTERN_CHAR_TO_INDEX;
        if (ch < iArr.length) {
            return iArr[ch & 255];
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public DateFormat.Field patternCharToDateFormatField(char ch) {
        int patternCharIndex = getIndexFromChar(ch);
        if (patternCharIndex != -1) {
            return PATTERN_INDEX_TO_DATE_FORMAT_ATTRIBUTE[patternCharIndex];
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public String subFormat(char ch, int count, int beginOffset, FieldPosition pos, DateFormatSymbols fmtData, Calendar cal) throws IllegalArgumentException {
        return subFormat(ch, count, beginOffset, 0, DisplayContext.CAPITALIZATION_NONE, pos, cal);
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public String subFormat(char ch, int count, int beginOffset, int fieldNum, DisplayContext capitalizationContext, FieldPosition pos, Calendar cal) {
        StringBuffer buf = new StringBuffer();
        subFormat(buf, ch, count, beginOffset, fieldNum, capitalizationContext, pos, cal);
        return buf.toString();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:222:0x0651, code lost:
        if (r12 != 5) goto L_0x0662;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x0653, code lost:
        safeAppend(r9.formatData.narrowWeekdays, r1, r10);
        r0 = com.ibm.icu.text.DateFormatSymbols.CapitalizationContextUsage.DAY_NARROW;
        r23 = r27;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:0x0662, code lost:
        if (r12 != 4) goto L_0x0673;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x0664, code lost:
        safeAppend(r9.formatData.weekdays, r1, r10);
        r0 = com.ibm.icu.text.DateFormatSymbols.CapitalizationContextUsage.DAY_FORMAT;
        r23 = r27;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x0674, code lost:
        if (r12 != 6) goto L_0x068b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:229:0x067a, code lost:
        if (r9.formatData.shorterWeekdays == null) goto L_0x068b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x067c, code lost:
        safeAppend(r9.formatData.shorterWeekdays, r1, r10);
        r0 = com.ibm.icu.text.DateFormatSymbols.CapitalizationContextUsage.DAY_FORMAT;
        r23 = r27;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:231:0x068b, code lost:
        safeAppend(r9.formatData.shortWeekdays, r1, r10);
        r0 = com.ibm.icu.text.DateFormatSymbols.CapitalizationContextUsage.DAY_FORMAT;
        r23 = r27;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:309:0x0864, code lost:
        r0 = r9.override;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:310:0x0866, code lost:
        if (r0 == null) goto L_0x0887;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:312:0x086e, code lost:
        if (r0.compareTo("hebr") == 0) goto L_0x087a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:314:0x0878, code lost:
        if (r9.override.indexOf("y=hebr") < 0) goto L_0x0887;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:316:0x087c, code lost:
        if (r3 <= HEBREW_CAL_CUR_MILLENIUM_START_YEAR) goto L_0x0887;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:318:0x0880, code lost:
        if (r3 >= HEBREW_CAL_CUR_MILLENIUM_END_YEAR) goto L_0x0887;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:319:0x0882, code lost:
        r19 = r3 - 5000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:320:0x0887, code lost:
        r19 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:321:0x0889, code lost:
        if (r12 != 2) goto L_0x089a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:322:0x088b, code lost:
        zeroPaddingNumber(r18, r36, r19, 2, 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:323:0x089a, code lost:
        zeroPaddingNumber(r18, r36, r19, r38, Integer.MAX_VALUE);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:336:0x090a, code lost:
        if (r40 != 0) goto L_0x0978;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:337:0x090c, code lost:
        if (r14 == null) goto L_0x0978;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:338:0x090e, code lost:
        r2 = r32;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:339:0x0918, code lost:
        if (com.ibm.icu.lang.UCharacter.isLowerCase(r10.codePointAt(r2)) == false) goto L_0x0973;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:340:0x091a, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:341:0x0923, code lost:
        switch(com.ibm.icu.text.SimpleDateFormat.AnonymousClass1.$SwitchMap$com$ibm$icu$text$DisplayContext[r41.ordinal()]) {
            case 1: goto L_0x0944;
            case 2: goto L_0x0927;
            case 3: goto L_0x0927;
            default: goto L_0x0926;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:343:0x092b, code lost:
        if (r9.formatData.capitalization == null) goto L_0x0946;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:344:0x092d, code lost:
        r4 = r9.formatData.capitalization.get(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:345:0x0939, code lost:
        if (r14 != com.ibm.icu.text.DisplayContext.CAPITALIZATION_FOR_UI_LIST_OR_MENU) goto L_0x093f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:346:0x093b, code lost:
        r5 = r4[0];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:347:0x093f, code lost:
        r5 = r4[1];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:348:0x0942, code lost:
        r3 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:349:0x0944, code lost:
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:350:0x0946, code lost:
        if (r3 == false) goto L_0x096e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:352:0x094a, code lost:
        if (r9.capitalizationBrkIter != null) goto L_0x0954;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:353:0x094c, code lost:
        r9.capitalizationBrkIter = com.ibm.icu.text.BreakIterator.getSentenceInstance(r9.locale);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:354:0x0954, code lost:
        r19 = r0;
        r20 = r1;
        r10.replace(r2, r36.length(), com.ibm.icu.lang.UCharacter.toTitleCase(r9.locale, r10.substring(r2), r9.capitalizationBrkIter, 768));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:355:0x096e, code lost:
        r19 = r0;
        r20 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:356:0x0973, code lost:
        r19 = r0;
        r20 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:357:0x0978, code lost:
        r19 = r0;
        r20 = r1;
        r2 = r32;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:359:0x0986, code lost:
        if (r42.getBeginIndex() != r42.getEndIndex()) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:361:0x0990, code lost:
        if (r42.getField() != PATTERN_INDEX_TO_DATE_FORMAT_FIELD[r23]) goto L_0x099f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:362:0x0992, code lost:
        r15.setBeginIndex(r13);
        r15.setEndIndex((r36.length() + r13) - r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:364:0x09a7, code lost:
        if (r42.getFieldAttribute() != PATTERN_INDEX_TO_DATE_FORMAT_ATTRIBUTE[r23]) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:365:0x09a9, code lost:
        r15.setBeginIndex(r13);
        r15.setEndIndex((r36.length() + r13) - r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:366:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:367:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:368:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:369:?, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:257:0x0751  */
    /* JADX WARNING: Removed duplicated region for block: B:261:0x075c  */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void subFormat(java.lang.StringBuffer r36, char r37, int r38, int r39, int r40, com.ibm.icu.text.DisplayContext r41, java.text.FieldPosition r42, com.ibm.icu.util.Calendar r43) {
        /*
            r35 = this;
            r9 = r35
            r10 = r36
            r11 = r37
            r12 = r38
            r13 = r39
            r14 = r41
            r15 = r42
            r8 = r43
            r16 = 2147483647(0x7fffffff, float:NaN)
            int r7 = r36.length()
            com.ibm.icu.util.TimeZone r6 = r43.getTimeZone()
            long r4 = r43.getTimeInMillis()
            r17 = 0
            int r3 = getIndexFromChar(r37)
            r1 = -1
            if (r3 != r1) goto L_0x0058
            r1 = 108(0x6c, float:1.51E-43)
            if (r11 != r1) goto L_0x002d
            return
        L_0x002d:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r0 = "Illegal pattern character '"
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.StringBuilder r0 = r0.append(r11)
            java.lang.String r2 = "' in \""
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r2 = r9.pattern
            java.lang.StringBuilder r0 = r0.append(r2)
            r2 = 34
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L_0x0058:
            int[] r0 = PATTERN_INDEX_TO_CALENDAR_FIELD
            r2 = r0[r3]
            r0 = 0
            if (r2 < 0) goto L_0x006e
            r1 = 34
            if (r3 == r1) goto L_0x0068
            int r1 = r8.get(r2)
            goto L_0x006c
        L_0x0068:
            int r1 = r43.getRelatedYear()
        L_0x006c:
            r0 = r1
            goto L_0x006f
        L_0x006e:
            r1 = r0
        L_0x006f:
            com.ibm.icu.text.NumberFormat r18 = r9.getNumberFormat(r11)
            com.ibm.icu.text.DateFormatSymbols$CapitalizationContextUsage r20 = com.ibm.icu.text.DateFormatSymbols.CapitalizationContextUsage.OTHER
            r0 = 12
            r24 = r2
            r25 = r4
            r4 = 2
            r2 = 4
            r5 = 3
            switch(r3) {
                case 0: goto L_0x08ab;
                case 1: goto L_0x085c;
                case 2: goto L_0x0722;
                case 3: goto L_0x0081;
                case 4: goto L_0x06e1;
                case 5: goto L_0x0081;
                case 6: goto L_0x0081;
                case 7: goto L_0x0081;
                case 8: goto L_0x069a;
                case 9: goto L_0x0648;
                case 10: goto L_0x0081;
                case 11: goto L_0x0081;
                case 12: goto L_0x0081;
                case 13: goto L_0x0081;
                case 14: goto L_0x0615;
                case 15: goto L_0x05ce;
                case 16: goto L_0x0081;
                case 17: goto L_0x0599;
                case 18: goto L_0x085c;
                case 19: goto L_0x0571;
                case 20: goto L_0x0081;
                case 21: goto L_0x0081;
                case 22: goto L_0x0081;
                case 23: goto L_0x0536;
                case 24: goto L_0x04fb;
                case 25: goto L_0x048c;
                case 26: goto L_0x0722;
                case 27: goto L_0x0442;
                case 28: goto L_0x03fc;
                case 29: goto L_0x03a7;
                case 30: goto L_0x037a;
                case 31: goto L_0x0343;
                case 32: goto L_0x02de;
                case 33: goto L_0x026f;
                case 34: goto L_0x0081;
                case 35: goto L_0x01e6;
                case 36: goto L_0x00b0;
                case 37: goto L_0x009c;
                default: goto L_0x0081;
            }
        L_0x0081:
            r19 = r1
            r23 = r3
            r32 = r7
            r11 = r8
            r7 = r25
            r5 = 2147483647(0x7fffffff, float:NaN)
            r0 = r35
            r1 = r18
            r2 = r36
            r3 = r19
            r4 = r38
            r0.zeroPaddingNumber(r1, r2, r3, r4, r5)
            goto L_0x0906
        L_0x009c:
            com.ibm.icu.text.DateFormatSymbols r0 = r9.formatData
            java.lang.String r0 = r0.getTimeSeparatorString()
            r10.append(r0)
            r19 = r1
            r23 = r3
            r32 = r7
            r11 = r8
            r7 = r25
            goto L_0x0906
        L_0x00b0:
            com.ibm.icu.util.ULocale r4 = r35.getLocale()
            com.ibm.icu.impl.DayPeriodRules r4 = com.ibm.icu.impl.DayPeriodRules.getInstance(r4)
            if (r4 != 0) goto L_0x00e8
            r2 = 97
            r0 = r35
            r5 = r1
            r1 = r36
            r11 = 1
            r27 = r3
            r3 = r38
            r19 = r4
            r28 = r25
            r4 = r39
            r30 = r5
            r5 = r40
            r31 = r6
            r6 = r41
            r32 = r7
            r7 = r42
            r11 = r8
            r8 = r43
            r0.subFormat(r1, r2, r3, r4, r5, r6, r7, r8)
            r23 = r27
            r7 = r28
            r19 = r30
            r6 = r31
            goto L_0x0906
        L_0x00e8:
            r30 = r1
            r27 = r3
            r19 = r4
            r31 = r6
            r32 = r7
            r11 = r8
            r28 = r25
            r1 = 11
            int r8 = r11.get(r1)
            r1 = 0
            r3 = 0
            boolean r4 = r9.hasMinute
            if (r4 == 0) goto L_0x0105
            int r1 = r11.get(r0)
        L_0x0105:
            r22 = r1
            boolean r1 = r9.hasSecond
            if (r1 == 0) goto L_0x0111
            r1 = 13
            int r3 = r11.get(r1)
        L_0x0111:
            r21 = r3
            if (r8 != 0) goto L_0x0124
            if (r22 != 0) goto L_0x0124
            if (r21 != 0) goto L_0x0124
            boolean r1 = r19.hasMidnight()
            if (r1 == 0) goto L_0x0124
            com.ibm.icu.impl.DayPeriodRules$DayPeriod r0 = com.ibm.icu.impl.DayPeriodRules.DayPeriod.MIDNIGHT
            r7 = r19
            goto L_0x013b
        L_0x0124:
            if (r8 != r0) goto L_0x0135
            if (r22 != 0) goto L_0x0135
            if (r21 != 0) goto L_0x0135
            boolean r0 = r19.hasNoon()
            if (r0 == 0) goto L_0x0135
            com.ibm.icu.impl.DayPeriodRules$DayPeriod r0 = com.ibm.icu.impl.DayPeriodRules.DayPeriod.NOON
            r7 = r19
            goto L_0x013b
        L_0x0135:
            r7 = r19
            com.ibm.icu.impl.DayPeriodRules$DayPeriod r0 = r7.getDayPeriodForHour(r8)
        L_0x013b:
            if (r0 == 0) goto L_0x01e0
            r1 = 0
            com.ibm.icu.impl.DayPeriodRules$DayPeriod r3 = com.ibm.icu.impl.DayPeriodRules.DayPeriod.AM
            if (r0 == r3) goto L_0x016a
            com.ibm.icu.impl.DayPeriodRules$DayPeriod r3 = com.ibm.icu.impl.DayPeriodRules.DayPeriod.PM
            if (r0 == r3) goto L_0x016a
            com.ibm.icu.impl.DayPeriodRules$DayPeriod r3 = com.ibm.icu.impl.DayPeriodRules.DayPeriod.MIDNIGHT
            if (r0 == r3) goto L_0x016a
            int r3 = r0.ordinal()
            if (r12 > r5) goto L_0x0157
            com.ibm.icu.text.DateFormatSymbols r4 = r9.formatData
            java.lang.String[] r4 = r4.abbreviatedDayPeriods
            r1 = r4[r3]
            goto L_0x016a
        L_0x0157:
            if (r12 == r2) goto L_0x0164
            r4 = 5
            if (r12 <= r4) goto L_0x015d
            goto L_0x0164
        L_0x015d:
            com.ibm.icu.text.DateFormatSymbols r4 = r9.formatData
            java.lang.String[] r4 = r4.narrowDayPeriods
            r1 = r4[r3]
            goto L_0x016a
        L_0x0164:
            com.ibm.icu.text.DateFormatSymbols r4 = r9.formatData
            java.lang.String[] r4 = r4.wideDayPeriods
            r1 = r4[r3]
        L_0x016a:
            if (r1 != 0) goto L_0x019f
            com.ibm.icu.impl.DayPeriodRules$DayPeriod r3 = com.ibm.icu.impl.DayPeriodRules.DayPeriod.MIDNIGHT
            if (r0 == r3) goto L_0x0174
            com.ibm.icu.impl.DayPeriodRules$DayPeriod r3 = com.ibm.icu.impl.DayPeriodRules.DayPeriod.NOON
            if (r0 != r3) goto L_0x019f
        L_0x0174:
            com.ibm.icu.impl.DayPeriodRules$DayPeriod r0 = r7.getDayPeriodForHour(r8)
            int r3 = r0.ordinal()
            if (r12 > r5) goto L_0x0187
            com.ibm.icu.text.DateFormatSymbols r2 = r9.formatData
            java.lang.String[] r2 = r2.abbreviatedDayPeriods
            r1 = r2[r3]
            r6 = r0
            r5 = r1
            goto L_0x01a1
        L_0x0187:
            if (r12 == r2) goto L_0x0196
            r2 = 5
            if (r12 <= r2) goto L_0x018d
            goto L_0x0196
        L_0x018d:
            com.ibm.icu.text.DateFormatSymbols r2 = r9.formatData
            java.lang.String[] r2 = r2.narrowDayPeriods
            r1 = r2[r3]
            r6 = r0
            r5 = r1
            goto L_0x01a1
        L_0x0196:
            com.ibm.icu.text.DateFormatSymbols r2 = r9.formatData
            java.lang.String[] r2 = r2.wideDayPeriods
            r1 = r2[r3]
            r6 = r0
            r5 = r1
            goto L_0x01a1
        L_0x019f:
            r6 = r0
            r5 = r1
        L_0x01a1:
            com.ibm.icu.impl.DayPeriodRules$DayPeriod r0 = com.ibm.icu.impl.DayPeriodRules.DayPeriod.AM
            if (r6 == r0) goto L_0x01b9
            com.ibm.icu.impl.DayPeriodRules$DayPeriod r0 = com.ibm.icu.impl.DayPeriodRules.DayPeriod.PM
            if (r6 == r0) goto L_0x01b9
            if (r5 != 0) goto L_0x01ac
            goto L_0x01b9
        L_0x01ac:
            r10.append(r5)
            r23 = r27
            r7 = r28
            r19 = r30
            r6 = r31
            goto L_0x0906
        L_0x01b9:
            r2 = 97
            r0 = r35
            r1 = r36
            r3 = r38
            r4 = r39
            r19 = r5
            r5 = r40
            r23 = r6
            r6 = r41
            r25 = r7
            r7 = r42
            r26 = r8
            r8 = r43
            r0.subFormat(r1, r2, r3, r4, r5, r6, r7, r8)
            r23 = r27
            r7 = r28
            r19 = r30
            r6 = r31
            goto L_0x0906
        L_0x01e0:
            java.lang.AssertionError r1 = new java.lang.AssertionError
            r1.<init>()
            throw r1
        L_0x01e6:
            r30 = r1
            r27 = r3
            r31 = r6
            r32 = r7
            r11 = r8
            r28 = r25
            r1 = 11
            int r8 = r11.get(r1)
            r1 = 0
            if (r8 != r0) goto L_0x023c
            boolean r3 = r9.hasMinute
            if (r3 == 0) goto L_0x0204
            int r0 = r11.get(r0)
            if (r0 != 0) goto L_0x023c
        L_0x0204:
            boolean r0 = r9.hasSecond
            if (r0 == 0) goto L_0x0210
            r0 = 13
            int r0 = r11.get(r0)
            if (r0 != 0) goto L_0x023c
        L_0x0210:
            r0 = 9
            int r0 = r11.get(r0)
            if (r12 > r5) goto L_0x0222
            com.ibm.icu.text.DateFormatSymbols r2 = r9.formatData
            java.lang.String[] r2 = r2.abbreviatedDayPeriods
            r1 = r2[r0]
            r30 = r0
            r7 = r1
            goto L_0x023d
        L_0x0222:
            if (r12 == r2) goto L_0x0232
            r2 = 5
            if (r12 <= r2) goto L_0x0228
            goto L_0x0232
        L_0x0228:
            com.ibm.icu.text.DateFormatSymbols r2 = r9.formatData
            java.lang.String[] r2 = r2.narrowDayPeriods
            r1 = r2[r0]
            r30 = r0
            r7 = r1
            goto L_0x023d
        L_0x0232:
            com.ibm.icu.text.DateFormatSymbols r2 = r9.formatData
            java.lang.String[] r2 = r2.wideDayPeriods
            r1 = r2[r0]
            r30 = r0
            r7 = r1
            goto L_0x023d
        L_0x023c:
            r7 = r1
        L_0x023d:
            if (r7 != 0) goto L_0x0259
            r2 = 97
            r0 = r35
            r1 = r36
            r3 = r38
            r4 = r39
            r5 = r40
            r6 = r41
            r33 = r7
            r7 = r42
            r19 = r8
            r8 = r43
            r0.subFormat(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x0263
        L_0x0259:
            r33 = r7
            r19 = r8
            r1 = r33
            r10.append(r1)
        L_0x0263:
            r0 = r20
            r23 = r27
            r7 = r28
            r1 = r30
            r6 = r31
            goto L_0x090a
        L_0x026f:
            r30 = r1
            r27 = r3
            r31 = r6
            r32 = r7
            r11 = r8
            r28 = r25
            r0 = 1
            if (r12 != r0) goto L_0x028e
            com.ibm.icu.text.TimeZoneFormat r0 = r35.tzFormat()
            com.ibm.icu.text.TimeZoneFormat$Style r1 = com.ibm.icu.text.TimeZoneFormat.Style.ISO_BASIC_LOCAL_SHORT
            r7 = r28
            r6 = r31
            java.lang.String r17 = r0.format((com.ibm.icu.text.TimeZoneFormat.Style) r1, (com.ibm.icu.util.TimeZone) r6, (long) r7)
            r0 = r17
            goto L_0x02d1
        L_0x028e:
            r7 = r28
            r6 = r31
            if (r12 != r4) goto L_0x02a1
            com.ibm.icu.text.TimeZoneFormat r0 = r35.tzFormat()
            com.ibm.icu.text.TimeZoneFormat$Style r1 = com.ibm.icu.text.TimeZoneFormat.Style.ISO_BASIC_LOCAL_FIXED
            java.lang.String r17 = r0.format((com.ibm.icu.text.TimeZoneFormat.Style) r1, (com.ibm.icu.util.TimeZone) r6, (long) r7)
            r0 = r17
            goto L_0x02d1
        L_0x02a1:
            if (r12 != r5) goto L_0x02b0
            com.ibm.icu.text.TimeZoneFormat r0 = r35.tzFormat()
            com.ibm.icu.text.TimeZoneFormat$Style r1 = com.ibm.icu.text.TimeZoneFormat.Style.ISO_EXTENDED_LOCAL_FIXED
            java.lang.String r17 = r0.format((com.ibm.icu.text.TimeZoneFormat.Style) r1, (com.ibm.icu.util.TimeZone) r6, (long) r7)
            r0 = r17
            goto L_0x02d1
        L_0x02b0:
            if (r12 != r2) goto L_0x02bf
            com.ibm.icu.text.TimeZoneFormat r0 = r35.tzFormat()
            com.ibm.icu.text.TimeZoneFormat$Style r1 = com.ibm.icu.text.TimeZoneFormat.Style.ISO_BASIC_LOCAL_FULL
            java.lang.String r17 = r0.format((com.ibm.icu.text.TimeZoneFormat.Style) r1, (com.ibm.icu.util.TimeZone) r6, (long) r7)
            r0 = r17
            goto L_0x02d1
        L_0x02bf:
            r0 = 5
            if (r12 != r0) goto L_0x02cf
            com.ibm.icu.text.TimeZoneFormat r0 = r35.tzFormat()
            com.ibm.icu.text.TimeZoneFormat$Style r1 = com.ibm.icu.text.TimeZoneFormat.Style.ISO_EXTENDED_LOCAL_FULL
            java.lang.String r17 = r0.format((com.ibm.icu.text.TimeZoneFormat.Style) r1, (com.ibm.icu.util.TimeZone) r6, (long) r7)
            r0 = r17
            goto L_0x02d1
        L_0x02cf:
            r0 = r17
        L_0x02d1:
            r10.append(r0)
            r17 = r0
            r0 = r20
            r23 = r27
            r1 = r30
            goto L_0x090a
        L_0x02de:
            r30 = r1
            r27 = r3
            r32 = r7
            r11 = r8
            r7 = r25
            r0 = 1
            if (r12 != r0) goto L_0x02f7
            com.ibm.icu.text.TimeZoneFormat r0 = r35.tzFormat()
            com.ibm.icu.text.TimeZoneFormat$Style r1 = com.ibm.icu.text.TimeZoneFormat.Style.ISO_BASIC_SHORT
            java.lang.String r17 = r0.format((com.ibm.icu.text.TimeZoneFormat.Style) r1, (com.ibm.icu.util.TimeZone) r6, (long) r7)
            r0 = r17
            goto L_0x0336
        L_0x02f7:
            if (r12 != r4) goto L_0x0306
            com.ibm.icu.text.TimeZoneFormat r0 = r35.tzFormat()
            com.ibm.icu.text.TimeZoneFormat$Style r1 = com.ibm.icu.text.TimeZoneFormat.Style.ISO_BASIC_FIXED
            java.lang.String r17 = r0.format((com.ibm.icu.text.TimeZoneFormat.Style) r1, (com.ibm.icu.util.TimeZone) r6, (long) r7)
            r0 = r17
            goto L_0x0336
        L_0x0306:
            if (r12 != r5) goto L_0x0315
            com.ibm.icu.text.TimeZoneFormat r0 = r35.tzFormat()
            com.ibm.icu.text.TimeZoneFormat$Style r1 = com.ibm.icu.text.TimeZoneFormat.Style.ISO_EXTENDED_FIXED
            java.lang.String r17 = r0.format((com.ibm.icu.text.TimeZoneFormat.Style) r1, (com.ibm.icu.util.TimeZone) r6, (long) r7)
            r0 = r17
            goto L_0x0336
        L_0x0315:
            if (r12 != r2) goto L_0x0324
            com.ibm.icu.text.TimeZoneFormat r0 = r35.tzFormat()
            com.ibm.icu.text.TimeZoneFormat$Style r1 = com.ibm.icu.text.TimeZoneFormat.Style.ISO_BASIC_FULL
            java.lang.String r17 = r0.format((com.ibm.icu.text.TimeZoneFormat.Style) r1, (com.ibm.icu.util.TimeZone) r6, (long) r7)
            r0 = r17
            goto L_0x0336
        L_0x0324:
            r0 = 5
            if (r12 != r0) goto L_0x0334
            com.ibm.icu.text.TimeZoneFormat r0 = r35.tzFormat()
            com.ibm.icu.text.TimeZoneFormat$Style r1 = com.ibm.icu.text.TimeZoneFormat.Style.ISO_EXTENDED_FULL
            java.lang.String r17 = r0.format((com.ibm.icu.text.TimeZoneFormat.Style) r1, (com.ibm.icu.util.TimeZone) r6, (long) r7)
            r0 = r17
            goto L_0x0336
        L_0x0334:
            r0 = r17
        L_0x0336:
            r10.append(r0)
            r17 = r0
            r0 = r20
            r23 = r27
            r1 = r30
            goto L_0x090a
        L_0x0343:
            r30 = r1
            r27 = r3
            r32 = r7
            r11 = r8
            r7 = r25
            r0 = 1
            if (r12 != r0) goto L_0x035c
            com.ibm.icu.text.TimeZoneFormat r0 = r35.tzFormat()
            com.ibm.icu.text.TimeZoneFormat$Style r1 = com.ibm.icu.text.TimeZoneFormat.Style.LOCALIZED_GMT_SHORT
            java.lang.String r17 = r0.format((com.ibm.icu.text.TimeZoneFormat.Style) r1, (com.ibm.icu.util.TimeZone) r6, (long) r7)
            r0 = r17
            goto L_0x036d
        L_0x035c:
            if (r12 != r2) goto L_0x036b
            com.ibm.icu.text.TimeZoneFormat r0 = r35.tzFormat()
            com.ibm.icu.text.TimeZoneFormat$Style r1 = com.ibm.icu.text.TimeZoneFormat.Style.LOCALIZED_GMT
            java.lang.String r17 = r0.format((com.ibm.icu.text.TimeZoneFormat.Style) r1, (com.ibm.icu.util.TimeZone) r6, (long) r7)
            r0 = r17
            goto L_0x036d
        L_0x036b:
            r0 = r17
        L_0x036d:
            r10.append(r0)
            r17 = r0
            r0 = r20
            r23 = r27
            r1 = r30
            goto L_0x090a
        L_0x037a:
            r30 = r1
            r27 = r3
            r32 = r7
            r11 = r8
            r7 = r25
            com.ibm.icu.text.DateFormatSymbols r0 = r9.formatData
            java.lang.String[] r0 = r0.shortYearNames
            if (r0 == 0) goto L_0x03a1
            com.ibm.icu.text.DateFormatSymbols r0 = r9.formatData
            java.lang.String[] r0 = r0.shortYearNames
            int r0 = r0.length
            r3 = r30
            if (r3 > r0) goto L_0x03a3
            com.ibm.icu.text.DateFormatSymbols r0 = r9.formatData
            java.lang.String[] r0 = r0.shortYearNames
            int r1 = r3 + -1
            safeAppend(r0, r1, r10)
            r19 = r3
            r23 = r27
            goto L_0x0906
        L_0x03a1:
            r3 = r30
        L_0x03a3:
            r23 = r27
            goto L_0x0864
        L_0x03a7:
            r27 = r3
            r32 = r7
            r11 = r8
            r7 = r25
            r3 = r1
            r0 = 1
            if (r12 != r0) goto L_0x03bf
            com.ibm.icu.text.TimeZoneFormat r0 = r35.tzFormat()
            com.ibm.icu.text.TimeZoneFormat$Style r1 = com.ibm.icu.text.TimeZoneFormat.Style.ZONE_ID_SHORT
            java.lang.String r17 = r0.format((com.ibm.icu.text.TimeZoneFormat.Style) r1, (com.ibm.icu.util.TimeZone) r6, (long) r7)
            r0 = r17
            goto L_0x03f0
        L_0x03bf:
            if (r12 != r4) goto L_0x03ce
            com.ibm.icu.text.TimeZoneFormat r0 = r35.tzFormat()
            com.ibm.icu.text.TimeZoneFormat$Style r1 = com.ibm.icu.text.TimeZoneFormat.Style.ZONE_ID
            java.lang.String r17 = r0.format((com.ibm.icu.text.TimeZoneFormat.Style) r1, (com.ibm.icu.util.TimeZone) r6, (long) r7)
            r0 = r17
            goto L_0x03f0
        L_0x03ce:
            if (r12 != r5) goto L_0x03dd
            com.ibm.icu.text.TimeZoneFormat r0 = r35.tzFormat()
            com.ibm.icu.text.TimeZoneFormat$Style r1 = com.ibm.icu.text.TimeZoneFormat.Style.EXEMPLAR_LOCATION
            java.lang.String r17 = r0.format((com.ibm.icu.text.TimeZoneFormat.Style) r1, (com.ibm.icu.util.TimeZone) r6, (long) r7)
            r0 = r17
            goto L_0x03f0
        L_0x03dd:
            if (r12 != r2) goto L_0x03ee
            com.ibm.icu.text.TimeZoneFormat r0 = r35.tzFormat()
            com.ibm.icu.text.TimeZoneFormat$Style r1 = com.ibm.icu.text.TimeZoneFormat.Style.GENERIC_LOCATION
            java.lang.String r17 = r0.format((com.ibm.icu.text.TimeZoneFormat.Style) r1, (com.ibm.icu.util.TimeZone) r6, (long) r7)
            com.ibm.icu.text.DateFormatSymbols$CapitalizationContextUsage r20 = com.ibm.icu.text.DateFormatSymbols.CapitalizationContextUsage.ZONE_LONG
            r0 = r17
            goto L_0x03f0
        L_0x03ee:
            r0 = r17
        L_0x03f0:
            r10.append(r0)
            r17 = r0
            r1 = r3
            r0 = r20
            r23 = r27
            goto L_0x090a
        L_0x03fc:
            r27 = r3
            r32 = r7
            r11 = r8
            r7 = r25
            r3 = r1
            if (r12 < r2) goto L_0x0415
            com.ibm.icu.text.DateFormatSymbols r0 = r9.formatData
            java.lang.String[] r0 = r0.standaloneQuarters
            int r1 = r3 / 3
            safeAppend(r0, r1, r10)
            r19 = r3
            r23 = r27
            goto L_0x0906
        L_0x0415:
            if (r12 != r5) goto L_0x0426
            com.ibm.icu.text.DateFormatSymbols r0 = r9.formatData
            java.lang.String[] r0 = r0.standaloneShortQuarters
            int r1 = r3 / 3
            safeAppend(r0, r1, r10)
            r19 = r3
            r23 = r27
            goto L_0x0906
        L_0x0426:
            int r1 = r3 / 3
            r0 = 1
            int r4 = r1 + 1
            r5 = 2147483647(0x7fffffff, float:NaN)
            r0 = r35
            r1 = r18
            r2 = r36
            r34 = r3
            r3 = r4
            r4 = r38
            r0.zeroPaddingNumber(r1, r2, r3, r4, r5)
            r23 = r27
            r19 = r34
            goto L_0x0906
        L_0x0442:
            r34 = r1
            r27 = r3
            r32 = r7
            r11 = r8
            r7 = r25
            if (r12 < r2) goto L_0x045e
            com.ibm.icu.text.DateFormatSymbols r0 = r9.formatData
            java.lang.String[] r0 = r0.quarters
            r4 = r34
            int r1 = r4 / 3
            safeAppend(r0, r1, r10)
            r19 = r4
            r23 = r27
            goto L_0x0906
        L_0x045e:
            r4 = r34
            if (r12 != r5) goto L_0x0471
            com.ibm.icu.text.DateFormatSymbols r0 = r9.formatData
            java.lang.String[] r0 = r0.shortQuarters
            int r1 = r4 / 3
            safeAppend(r0, r1, r10)
            r19 = r4
            r23 = r27
            goto L_0x0906
        L_0x0471:
            int r1 = r4 / 3
            r0 = 1
            int r3 = r1 + 1
            r5 = 2147483647(0x7fffffff, float:NaN)
            r0 = r35
            r1 = r18
            r2 = r36
            r30 = r4
            r4 = r38
            r0.zeroPaddingNumber(r1, r2, r3, r4, r5)
            r23 = r27
            r19 = r30
            goto L_0x0906
        L_0x048c:
            r30 = r1
            r27 = r3
            r32 = r7
            r11 = r8
            r7 = r25
            if (r12 >= r5) goto L_0x04ac
            r4 = 1
            r5 = 2147483647(0x7fffffff, float:NaN)
            r0 = r35
            r1 = r18
            r2 = r36
            r3 = r30
            r0.zeroPaddingNumber(r1, r2, r3, r4, r5)
            r23 = r27
            r19 = r30
            goto L_0x0906
        L_0x04ac:
            r0 = 7
            int r1 = r11.get(r0)
            r0 = 5
            if (r12 != r0) goto L_0x04c3
            com.ibm.icu.text.DateFormatSymbols r0 = r9.formatData
            java.lang.String[] r0 = r0.standaloneNarrowWeekdays
            safeAppend(r0, r1, r10)
            com.ibm.icu.text.DateFormatSymbols$CapitalizationContextUsage r20 = com.ibm.icu.text.DateFormatSymbols.CapitalizationContextUsage.DAY_NARROW
            r0 = r20
            r23 = r27
            goto L_0x090a
        L_0x04c3:
            if (r12 != r2) goto L_0x04d4
            com.ibm.icu.text.DateFormatSymbols r0 = r9.formatData
            java.lang.String[] r0 = r0.standaloneWeekdays
            safeAppend(r0, r1, r10)
            com.ibm.icu.text.DateFormatSymbols$CapitalizationContextUsage r20 = com.ibm.icu.text.DateFormatSymbols.CapitalizationContextUsage.DAY_STANDALONE
            r0 = r20
            r23 = r27
            goto L_0x090a
        L_0x04d4:
            r0 = 6
            if (r12 != r0) goto L_0x04ec
            com.ibm.icu.text.DateFormatSymbols r0 = r9.formatData
            java.lang.String[] r0 = r0.standaloneShorterWeekdays
            if (r0 == 0) goto L_0x04ec
            com.ibm.icu.text.DateFormatSymbols r0 = r9.formatData
            java.lang.String[] r0 = r0.standaloneShorterWeekdays
            safeAppend(r0, r1, r10)
            com.ibm.icu.text.DateFormatSymbols$CapitalizationContextUsage r20 = com.ibm.icu.text.DateFormatSymbols.CapitalizationContextUsage.DAY_STANDALONE
            r0 = r20
            r23 = r27
            goto L_0x090a
        L_0x04ec:
            com.ibm.icu.text.DateFormatSymbols r0 = r9.formatData
            java.lang.String[] r0 = r0.standaloneShortWeekdays
            safeAppend(r0, r1, r10)
            com.ibm.icu.text.DateFormatSymbols$CapitalizationContextUsage r20 = com.ibm.icu.text.DateFormatSymbols.CapitalizationContextUsage.DAY_STANDALONE
            r0 = r20
            r23 = r27
            goto L_0x090a
        L_0x04fb:
            r30 = r1
            r27 = r3
            r32 = r7
            r11 = r8
            r7 = r25
            r0 = 1
            if (r12 != r0) goto L_0x0516
            com.ibm.icu.text.TimeZoneFormat r0 = r35.tzFormat()
            com.ibm.icu.text.TimeZoneFormat$Style r1 = com.ibm.icu.text.TimeZoneFormat.Style.GENERIC_SHORT
            java.lang.String r17 = r0.format((com.ibm.icu.text.TimeZoneFormat.Style) r1, (com.ibm.icu.util.TimeZone) r6, (long) r7)
            com.ibm.icu.text.DateFormatSymbols$CapitalizationContextUsage r20 = com.ibm.icu.text.DateFormatSymbols.CapitalizationContextUsage.METAZONE_SHORT
            r0 = r17
            goto L_0x0529
        L_0x0516:
            if (r12 != r2) goto L_0x0527
            com.ibm.icu.text.TimeZoneFormat r0 = r35.tzFormat()
            com.ibm.icu.text.TimeZoneFormat$Style r1 = com.ibm.icu.text.TimeZoneFormat.Style.GENERIC_LONG
            java.lang.String r17 = r0.format((com.ibm.icu.text.TimeZoneFormat.Style) r1, (com.ibm.icu.util.TimeZone) r6, (long) r7)
            com.ibm.icu.text.DateFormatSymbols$CapitalizationContextUsage r20 = com.ibm.icu.text.DateFormatSymbols.CapitalizationContextUsage.METAZONE_LONG
            r0 = r17
            goto L_0x0529
        L_0x0527:
            r0 = r17
        L_0x0529:
            r10.append(r0)
            r17 = r0
            r0 = r20
            r23 = r27
            r1 = r30
            goto L_0x090a
        L_0x0536:
            r30 = r1
            r27 = r3
            r32 = r7
            r11 = r8
            r7 = r25
            if (r12 >= r2) goto L_0x054c
            com.ibm.icu.text.TimeZoneFormat r0 = r35.tzFormat()
            com.ibm.icu.text.TimeZoneFormat$Style r1 = com.ibm.icu.text.TimeZoneFormat.Style.ISO_BASIC_LOCAL_FULL
            java.lang.String r0 = r0.format((com.ibm.icu.text.TimeZoneFormat.Style) r1, (com.ibm.icu.util.TimeZone) r6, (long) r7)
            goto L_0x0564
        L_0x054c:
            r0 = 5
            if (r12 != r0) goto L_0x055a
            com.ibm.icu.text.TimeZoneFormat r0 = r35.tzFormat()
            com.ibm.icu.text.TimeZoneFormat$Style r1 = com.ibm.icu.text.TimeZoneFormat.Style.ISO_EXTENDED_FULL
            java.lang.String r0 = r0.format((com.ibm.icu.text.TimeZoneFormat.Style) r1, (com.ibm.icu.util.TimeZone) r6, (long) r7)
            goto L_0x0564
        L_0x055a:
            com.ibm.icu.text.TimeZoneFormat r0 = r35.tzFormat()
            com.ibm.icu.text.TimeZoneFormat$Style r1 = com.ibm.icu.text.TimeZoneFormat.Style.LOCALIZED_GMT
            java.lang.String r0 = r0.format((com.ibm.icu.text.TimeZoneFormat.Style) r1, (com.ibm.icu.util.TimeZone) r6, (long) r7)
        L_0x0564:
            r10.append(r0)
            r17 = r0
            r0 = r20
            r23 = r27
            r1 = r30
            goto L_0x090a
        L_0x0571:
            r30 = r1
            r27 = r3
            r32 = r7
            r11 = r8
            r7 = r25
            if (r12 >= r5) goto L_0x0592
            r5 = 2147483647(0x7fffffff, float:NaN)
            r0 = r35
            r1 = r18
            r2 = r36
            r3 = r30
            r4 = r38
            r0.zeroPaddingNumber(r1, r2, r3, r4, r5)
            r23 = r27
            r19 = r30
            goto L_0x0906
        L_0x0592:
            r0 = 7
            int r1 = r11.get(r0)
            goto L_0x0650
        L_0x0599:
            r30 = r1
            r27 = r3
            r32 = r7
            r11 = r8
            r7 = r25
            if (r12 >= r2) goto L_0x05b3
            com.ibm.icu.text.TimeZoneFormat r0 = r35.tzFormat()
            com.ibm.icu.text.TimeZoneFormat$Style r1 = com.ibm.icu.text.TimeZoneFormat.Style.SPECIFIC_SHORT
            java.lang.String r0 = r0.format((com.ibm.icu.text.TimeZoneFormat.Style) r1, (com.ibm.icu.util.TimeZone) r6, (long) r7)
            com.ibm.icu.text.DateFormatSymbols$CapitalizationContextUsage r1 = com.ibm.icu.text.DateFormatSymbols.CapitalizationContextUsage.METAZONE_SHORT
            r20 = r1
            goto L_0x05c1
        L_0x05b3:
            com.ibm.icu.text.TimeZoneFormat r0 = r35.tzFormat()
            com.ibm.icu.text.TimeZoneFormat$Style r1 = com.ibm.icu.text.TimeZoneFormat.Style.SPECIFIC_LONG
            java.lang.String r0 = r0.format((com.ibm.icu.text.TimeZoneFormat.Style) r1, (com.ibm.icu.util.TimeZone) r6, (long) r7)
            com.ibm.icu.text.DateFormatSymbols$CapitalizationContextUsage r1 = com.ibm.icu.text.DateFormatSymbols.CapitalizationContextUsage.METAZONE_LONG
            r20 = r1
        L_0x05c1:
            r10.append(r0)
            r17 = r0
            r0 = r20
            r23 = r27
            r1 = r30
            goto L_0x090a
        L_0x05ce:
            r30 = r1
            r27 = r3
            r32 = r7
            r11 = r8
            r7 = r25
            r5 = r30
            if (r5 != 0) goto L_0x05fd
            r0 = 10
            int r0 = r11.getLeastMaximum(r0)
            r1 = 1
            int r3 = r0 + 1
            r19 = 2147483647(0x7fffffff, float:NaN)
            r0 = r35
            r1 = r18
            r2 = r36
            r4 = r38
            r30 = r5
            r5 = r19
            r0.zeroPaddingNumber(r1, r2, r3, r4, r5)
            r23 = r27
            r19 = r30
            goto L_0x0906
        L_0x05fd:
            r30 = r5
            r5 = 2147483647(0x7fffffff, float:NaN)
            r0 = r35
            r1 = r18
            r2 = r36
            r3 = r30
            r4 = r38
            r0.zeroPaddingNumber(r1, r2, r3, r4, r5)
            r23 = r27
            r19 = r30
            goto L_0x0906
        L_0x0615:
            r30 = r1
            r27 = r3
            r32 = r7
            r11 = r8
            r7 = r25
            r0 = 5
            if (r12 < r0) goto L_0x0639
            com.ibm.icu.text.DateFormatSymbols r0 = r9.formatData
            java.lang.String[] r0 = r0.ampmsNarrow
            if (r0 != 0) goto L_0x062a
            r3 = r30
            goto L_0x063b
        L_0x062a:
            com.ibm.icu.text.DateFormatSymbols r0 = r9.formatData
            java.lang.String[] r0 = r0.ampmsNarrow
            r3 = r30
            safeAppend(r0, r3, r10)
            r19 = r3
            r23 = r27
            goto L_0x0906
        L_0x0639:
            r3 = r30
        L_0x063b:
            com.ibm.icu.text.DateFormatSymbols r0 = r9.formatData
            java.lang.String[] r0 = r0.ampms
            safeAppend(r0, r3, r10)
            r19 = r3
            r23 = r27
            goto L_0x0906
        L_0x0648:
            r27 = r3
            r32 = r7
            r11 = r8
            r7 = r25
            r3 = r1
        L_0x0650:
            r0 = 5
            if (r12 != r0) goto L_0x0662
            com.ibm.icu.text.DateFormatSymbols r0 = r9.formatData
            java.lang.String[] r0 = r0.narrowWeekdays
            safeAppend(r0, r1, r10)
            com.ibm.icu.text.DateFormatSymbols$CapitalizationContextUsage r20 = com.ibm.icu.text.DateFormatSymbols.CapitalizationContextUsage.DAY_NARROW
            r0 = r20
            r23 = r27
            goto L_0x090a
        L_0x0662:
            if (r12 != r2) goto L_0x0673
            com.ibm.icu.text.DateFormatSymbols r0 = r9.formatData
            java.lang.String[] r0 = r0.weekdays
            safeAppend(r0, r1, r10)
            com.ibm.icu.text.DateFormatSymbols$CapitalizationContextUsage r20 = com.ibm.icu.text.DateFormatSymbols.CapitalizationContextUsage.DAY_FORMAT
            r0 = r20
            r23 = r27
            goto L_0x090a
        L_0x0673:
            r0 = 6
            if (r12 != r0) goto L_0x068b
            com.ibm.icu.text.DateFormatSymbols r0 = r9.formatData
            java.lang.String[] r0 = r0.shorterWeekdays
            if (r0 == 0) goto L_0x068b
            com.ibm.icu.text.DateFormatSymbols r0 = r9.formatData
            java.lang.String[] r0 = r0.shorterWeekdays
            safeAppend(r0, r1, r10)
            com.ibm.icu.text.DateFormatSymbols$CapitalizationContextUsage r20 = com.ibm.icu.text.DateFormatSymbols.CapitalizationContextUsage.DAY_FORMAT
            r0 = r20
            r23 = r27
            goto L_0x090a
        L_0x068b:
            com.ibm.icu.text.DateFormatSymbols r0 = r9.formatData
            java.lang.String[] r0 = r0.shortWeekdays
            safeAppend(r0, r1, r10)
            com.ibm.icu.text.DateFormatSymbols$CapitalizationContextUsage r20 = com.ibm.icu.text.DateFormatSymbols.CapitalizationContextUsage.DAY_FORMAT
            r0 = r20
            r23 = r27
            goto L_0x090a
        L_0x069a:
            r27 = r3
            r32 = r7
            r11 = r8
            r7 = r25
            r3 = r1
            com.ibm.icu.text.NumberFormat r0 = r9.numberFormat
            int r1 = java.lang.Math.min(r5, r12)
            r0.setMinimumIntegerDigits(r1)
            com.ibm.icu.text.NumberFormat r0 = r9.numberFormat
            r1 = 2147483647(0x7fffffff, float:NaN)
            r0.setMaximumIntegerDigits(r1)
            r0 = 1
            if (r12 != r0) goto L_0x06b9
            int r1 = r3 / 100
            goto L_0x06bf
        L_0x06b9:
            if (r12 != r4) goto L_0x06be
            int r1 = r3 / 10
            goto L_0x06bf
        L_0x06be:
            r1 = r3
        L_0x06bf:
            java.text.FieldPosition r0 = new java.text.FieldPosition
            r2 = -1
            r0.<init>(r2)
            com.ibm.icu.text.NumberFormat r2 = r9.numberFormat
            long r3 = (long) r1
            r2.format((long) r3, (java.lang.StringBuffer) r10, (java.text.FieldPosition) r0)
            if (r12 <= r5) goto L_0x06db
            com.ibm.icu.text.NumberFormat r2 = r9.numberFormat
            int r3 = r12 + -3
            r2.setMinimumIntegerDigits(r3)
            com.ibm.icu.text.NumberFormat r2 = r9.numberFormat
            r3 = 0
            r2.format((long) r3, (java.lang.StringBuffer) r10, (java.text.FieldPosition) r0)
        L_0x06db:
            r0 = r20
            r23 = r27
            goto L_0x090a
        L_0x06e1:
            r27 = r3
            r32 = r7
            r11 = r8
            r7 = r25
            r3 = r1
            if (r3 != 0) goto L_0x070c
            r0 = 11
            int r0 = r11.getMaximum(r0)
            r1 = 1
            int r4 = r0 + 1
            r5 = 2147483647(0x7fffffff, float:NaN)
            r0 = r35
            r1 = r18
            r2 = r36
            r30 = r3
            r3 = r4
            r4 = r38
            r0.zeroPaddingNumber(r1, r2, r3, r4, r5)
            r23 = r27
            r19 = r30
            goto L_0x0906
        L_0x070c:
            r30 = r3
            r5 = 2147483647(0x7fffffff, float:NaN)
            r0 = r35
            r1 = r18
            r2 = r36
            r4 = r38
            r0.zeroPaddingNumber(r1, r2, r3, r4, r5)
            r23 = r27
            r19 = r30
            goto L_0x0906
        L_0x0722:
            r30 = r1
            r27 = r3
            r32 = r7
            r11 = r8
            r7 = r25
            java.lang.String r0 = r43.getType()
            java.lang.String r1 = "hebrew"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0761
            r0 = 1
            int r1 = r11.get(r0)
            boolean r0 = com.ibm.icu.util.HebrewCalendar.isLeapYear(r1)
            if (r0 == 0) goto L_0x074c
            r3 = r30
            r1 = 6
            if (r3 != r1) goto L_0x074e
            if (r12 < r5) goto L_0x074e
            r1 = 13
            goto L_0x074f
        L_0x074c:
            r3 = r30
        L_0x074e:
            r1 = r3
        L_0x074f:
            if (r0 != 0) goto L_0x075c
            r3 = 6
            if (r1 < r3) goto L_0x075d
            if (r12 >= r5) goto L_0x075d
            int r1 = r1 + -1
            r19 = r3
            r3 = r1
            goto L_0x0765
        L_0x075c:
            r3 = 6
        L_0x075d:
            r19 = r3
            r3 = r1
            goto L_0x0765
        L_0x0761:
            r3 = r30
            r19 = 6
        L_0x0765:
            com.ibm.icu.text.DateFormatSymbols r0 = r9.formatData
            java.lang.String[] r0 = r0.leapMonthPatterns
            if (r0 == 0) goto L_0x077a
            com.ibm.icu.text.DateFormatSymbols r0 = r9.formatData
            java.lang.String[] r0 = r0.leapMonthPatterns
            int r0 = r0.length
            r1 = 7
            if (r0 < r1) goto L_0x077a
            r0 = 22
            int r0 = r11.get(r0)
            goto L_0x077b
        L_0x077a:
            r0 = 0
        L_0x077b:
            r21 = r0
            r22 = 0
            r0 = 5
            if (r12 != r0) goto L_0x07b3
            r1 = r27
            if (r1 != r4) goto L_0x0798
            com.ibm.icu.text.DateFormatSymbols r0 = r9.formatData
            java.lang.String[] r0 = r0.narrowMonths
            if (r21 == 0) goto L_0x0792
            com.ibm.icu.text.DateFormatSymbols r2 = r9.formatData
            java.lang.String[] r2 = r2.leapMonthPatterns
            r22 = r2[r4]
        L_0x0792:
            r2 = r22
            safeAppendWithMonthPattern(r0, r3, r10, r2)
            goto L_0x07aa
        L_0x0798:
            com.ibm.icu.text.DateFormatSymbols r0 = r9.formatData
            java.lang.String[] r0 = r0.standaloneNarrowMonths
            if (r21 == 0) goto L_0x07a5
            com.ibm.icu.text.DateFormatSymbols r2 = r9.formatData
            java.lang.String[] r2 = r2.leapMonthPatterns
            r4 = 5
            r22 = r2[r4]
        L_0x07a5:
            r2 = r22
            safeAppendWithMonthPattern(r0, r3, r10, r2)
        L_0x07aa:
            com.ibm.icu.text.DateFormatSymbols$CapitalizationContextUsage r20 = com.ibm.icu.text.DateFormatSymbols.CapitalizationContextUsage.MONTH_NARROW
            r23 = r1
            r1 = r3
            r0 = r20
            goto L_0x090a
        L_0x07b3:
            r1 = r27
            if (r12 != r2) goto L_0x07ee
            if (r1 != r4) goto L_0x07d4
            com.ibm.icu.text.DateFormatSymbols r0 = r9.formatData
            java.lang.String[] r0 = r0.months
            if (r21 == 0) goto L_0x07c6
            com.ibm.icu.text.DateFormatSymbols r2 = r9.formatData
            java.lang.String[] r2 = r2.leapMonthPatterns
            r4 = 0
            r22 = r2[r4]
        L_0x07c6:
            r2 = r22
            safeAppendWithMonthPattern(r0, r3, r10, r2)
            com.ibm.icu.text.DateFormatSymbols$CapitalizationContextUsage r20 = com.ibm.icu.text.DateFormatSymbols.CapitalizationContextUsage.MONTH_FORMAT
            r23 = r1
            r1 = r3
            r0 = r20
            goto L_0x090a
        L_0x07d4:
            com.ibm.icu.text.DateFormatSymbols r0 = r9.formatData
            java.lang.String[] r0 = r0.standaloneMonths
            if (r21 == 0) goto L_0x07e0
            com.ibm.icu.text.DateFormatSymbols r2 = r9.formatData
            java.lang.String[] r2 = r2.leapMonthPatterns
            r22 = r2[r5]
        L_0x07e0:
            r2 = r22
            safeAppendWithMonthPattern(r0, r3, r10, r2)
            com.ibm.icu.text.DateFormatSymbols$CapitalizationContextUsage r20 = com.ibm.icu.text.DateFormatSymbols.CapitalizationContextUsage.MONTH_STANDALONE
            r23 = r1
            r1 = r3
            r0 = r20
            goto L_0x090a
        L_0x07ee:
            if (r12 != r5) goto L_0x0827
            if (r1 != r4) goto L_0x080d
            com.ibm.icu.text.DateFormatSymbols r0 = r9.formatData
            java.lang.String[] r0 = r0.shortMonths
            if (r21 == 0) goto L_0x07ff
            com.ibm.icu.text.DateFormatSymbols r2 = r9.formatData
            java.lang.String[] r2 = r2.leapMonthPatterns
            r4 = 1
            r22 = r2[r4]
        L_0x07ff:
            r2 = r22
            safeAppendWithMonthPattern(r0, r3, r10, r2)
            com.ibm.icu.text.DateFormatSymbols$CapitalizationContextUsage r20 = com.ibm.icu.text.DateFormatSymbols.CapitalizationContextUsage.MONTH_FORMAT
            r23 = r1
            r1 = r3
            r0 = r20
            goto L_0x090a
        L_0x080d:
            com.ibm.icu.text.DateFormatSymbols r0 = r9.formatData
            java.lang.String[] r0 = r0.standaloneShortMonths
            if (r21 == 0) goto L_0x0819
            com.ibm.icu.text.DateFormatSymbols r4 = r9.formatData
            java.lang.String[] r4 = r4.leapMonthPatterns
            r22 = r4[r2]
        L_0x0819:
            r2 = r22
            safeAppendWithMonthPattern(r0, r3, r10, r2)
            com.ibm.icu.text.DateFormatSymbols$CapitalizationContextUsage r20 = com.ibm.icu.text.DateFormatSymbols.CapitalizationContextUsage.MONTH_STANDALONE
            r23 = r1
            r1 = r3
            r0 = r20
            goto L_0x090a
        L_0x0827:
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            int r4 = r3 + 1
            r5 = 2147483647(0x7fffffff, float:NaN)
            r0 = r35
            r23 = r1
            r1 = r18
            r25 = r3
            r3 = r4
            r4 = r38
            r0.zeroPaddingNumber(r1, r2, r3, r4, r5)
            r0 = 1
            java.lang.String[] r1 = new java.lang.String[r0]
            java.lang.String r0 = r2.toString()
            r3 = 0
            r1[r3] = r0
            if (r21 == 0) goto L_0x0851
            com.ibm.icu.text.DateFormatSymbols r0 = r9.formatData
            java.lang.String[] r0 = r0.leapMonthPatterns
            r22 = r0[r19]
        L_0x0851:
            r0 = r22
            safeAppendWithMonthPattern(r1, r3, r10, r0)
            r0 = r20
            r1 = r25
            goto L_0x090a
        L_0x085c:
            r23 = r3
            r32 = r7
            r11 = r8
            r7 = r25
            r3 = r1
        L_0x0864:
            java.lang.String r0 = r9.override
            if (r0 == 0) goto L_0x0887
            java.lang.String r1 = "hebr"
            int r0 = r0.compareTo(r1)
            if (r0 == 0) goto L_0x087a
            java.lang.String r0 = r9.override
            java.lang.String r1 = "y=hebr"
            int r0 = r0.indexOf(r1)
            if (r0 < 0) goto L_0x0887
        L_0x087a:
            r0 = 5000(0x1388, float:7.006E-42)
            if (r3 <= r0) goto L_0x0887
            r0 = 6000(0x1770, float:8.408E-42)
            if (r3 >= r0) goto L_0x0887
            int r1 = r3 + -5000
            r19 = r1
            goto L_0x0889
        L_0x0887:
            r19 = r3
        L_0x0889:
            if (r12 != r4) goto L_0x089a
            r4 = 2
            r5 = 2
            r0 = r35
            r1 = r18
            r2 = r36
            r3 = r19
            r0.zeroPaddingNumber(r1, r2, r3, r4, r5)
            goto L_0x0906
        L_0x089a:
            r5 = 2147483647(0x7fffffff, float:NaN)
            r0 = r35
            r1 = r18
            r2 = r36
            r3 = r19
            r4 = r38
            r0.zeroPaddingNumber(r1, r2, r3, r4, r5)
            goto L_0x0906
        L_0x08ab:
            r23 = r3
            r32 = r7
            r11 = r8
            r7 = r25
            r3 = r1
            java.lang.String r0 = r43.getType()
            java.lang.String r1 = "chinese"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x08f8
            java.lang.String r0 = r43.getType()
            java.lang.String r1 = "dangi"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x08cc
            goto L_0x08f8
        L_0x08cc:
            r0 = 5
            if (r12 != r0) goto L_0x08dc
            com.ibm.icu.text.DateFormatSymbols r0 = r9.formatData
            java.lang.String[] r0 = r0.narrowEras
            safeAppend(r0, r3, r10)
            com.ibm.icu.text.DateFormatSymbols$CapitalizationContextUsage r20 = com.ibm.icu.text.DateFormatSymbols.CapitalizationContextUsage.ERA_NARROW
            r1 = r3
            r0 = r20
            goto L_0x090a
        L_0x08dc:
            if (r12 != r2) goto L_0x08eb
            com.ibm.icu.text.DateFormatSymbols r0 = r9.formatData
            java.lang.String[] r0 = r0.eraNames
            safeAppend(r0, r3, r10)
            com.ibm.icu.text.DateFormatSymbols$CapitalizationContextUsage r20 = com.ibm.icu.text.DateFormatSymbols.CapitalizationContextUsage.ERA_WIDE
            r1 = r3
            r0 = r20
            goto L_0x090a
        L_0x08eb:
            com.ibm.icu.text.DateFormatSymbols r0 = r9.formatData
            java.lang.String[] r0 = r0.eras
            safeAppend(r0, r3, r10)
            com.ibm.icu.text.DateFormatSymbols$CapitalizationContextUsage r20 = com.ibm.icu.text.DateFormatSymbols.CapitalizationContextUsage.ERA_ABBREV
            r1 = r3
            r0 = r20
            goto L_0x090a
        L_0x08f8:
            r4 = 1
            r5 = 9
            r0 = r35
            r1 = r18
            r2 = r36
            r19 = r3
            r0.zeroPaddingNumber(r1, r2, r3, r4, r5)
        L_0x0906:
            r1 = r19
            r0 = r20
        L_0x090a:
            if (r40 != 0) goto L_0x0978
            if (r14 == 0) goto L_0x0978
            r2 = r32
            int r3 = r10.codePointAt(r2)
            boolean r3 = com.ibm.icu.lang.UCharacter.isLowerCase(r3)
            if (r3 == 0) goto L_0x0973
            r3 = 0
            int[] r4 = com.ibm.icu.text.SimpleDateFormat.AnonymousClass1.$SwitchMap$com$ibm$icu$text$DisplayContext
            int r5 = r41.ordinal()
            r4 = r4[r5]
            switch(r4) {
                case 1: goto L_0x0944;
                case 2: goto L_0x0927;
                case 3: goto L_0x0927;
                default: goto L_0x0926;
            }
        L_0x0926:
            goto L_0x0946
        L_0x0927:
            com.ibm.icu.text.DateFormatSymbols r4 = r9.formatData
            java.util.Map<com.ibm.icu.text.DateFormatSymbols$CapitalizationContextUsage, boolean[]> r4 = r4.capitalization
            if (r4 == 0) goto L_0x0946
            com.ibm.icu.text.DateFormatSymbols r4 = r9.formatData
            java.util.Map<com.ibm.icu.text.DateFormatSymbols$CapitalizationContextUsage, boolean[]> r4 = r4.capitalization
            java.lang.Object r4 = r4.get(r0)
            boolean[] r4 = (boolean[]) r4
            com.ibm.icu.text.DisplayContext r5 = com.ibm.icu.text.DisplayContext.CAPITALIZATION_FOR_UI_LIST_OR_MENU
            if (r14 != r5) goto L_0x093f
            r5 = 0
            boolean r5 = r4[r5]
            goto L_0x0942
        L_0x093f:
            r5 = 1
            boolean r5 = r4[r5]
        L_0x0942:
            r3 = r5
            goto L_0x0946
        L_0x0944:
            r3 = 1
        L_0x0946:
            if (r3 == 0) goto L_0x096e
            com.ibm.icu.text.BreakIterator r4 = r9.capitalizationBrkIter
            if (r4 != 0) goto L_0x0954
            com.ibm.icu.util.ULocale r4 = r9.locale
            com.ibm.icu.text.BreakIterator r4 = com.ibm.icu.text.BreakIterator.getSentenceInstance((com.ibm.icu.util.ULocale) r4)
            r9.capitalizationBrkIter = r4
        L_0x0954:
            java.lang.String r4 = r10.substring(r2)
            com.ibm.icu.util.ULocale r5 = r9.locale
            r19 = r0
            com.ibm.icu.text.BreakIterator r0 = r9.capitalizationBrkIter
            r20 = r1
            r1 = 768(0x300, float:1.076E-42)
            java.lang.String r0 = com.ibm.icu.lang.UCharacter.toTitleCase(r5, r4, r0, r1)
            int r1 = r36.length()
            r10.replace(r2, r1, r0)
            goto L_0x097e
        L_0x096e:
            r19 = r0
            r20 = r1
            goto L_0x097e
        L_0x0973:
            r19 = r0
            r20 = r1
            goto L_0x097e
        L_0x0978:
            r19 = r0
            r20 = r1
            r2 = r32
        L_0x097e:
            int r0 = r42.getBeginIndex()
            int r1 = r42.getEndIndex()
            if (r0 != r1) goto L_0x09b5
            int r0 = r42.getField()
            int[] r1 = PATTERN_INDEX_TO_DATE_FORMAT_FIELD
            r1 = r1[r23]
            if (r0 != r1) goto L_0x099f
            r15.setBeginIndex(r13)
            int r0 = r36.length()
            int r0 = r0 + r13
            int r0 = r0 - r2
            r15.setEndIndex(r0)
            goto L_0x09b5
        L_0x099f:
            java.text.Format$Field r0 = r42.getFieldAttribute()
            com.ibm.icu.text.DateFormat$Field[] r1 = PATTERN_INDEX_TO_DATE_FORMAT_ATTRIBUTE
            r1 = r1[r23]
            if (r0 != r1) goto L_0x09b5
            r15.setBeginIndex(r13)
            int r0 = r36.length()
            int r0 = r0 + r13
            int r0 = r0 - r2
            r15.setEndIndex(r0)
        L_0x09b5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.SimpleDateFormat.subFormat(java.lang.StringBuffer, char, int, int, int, com.ibm.icu.text.DisplayContext, java.text.FieldPosition, com.ibm.icu.util.Calendar):void");
    }

    /* renamed from: com.ibm.icu.text.SimpleDateFormat$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
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
            appendTo.append(SimpleFormatterImpl.formatRawPattern(monthPattern, 1, 1, new CharSequence[]{array[value]}));
        }
    }

    private static class PatternItem {
        final boolean isNumeric;
        final int length;
        final char type;

        PatternItem(char type2, int length2) {
            this.type = type2;
            this.length = length2;
            this.isNumeric = SimpleDateFormat.isNumeric(type2, length2);
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
            boolean z = false;
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
                if (!inQuote) {
                    z = true;
                }
                inQuote = z;
            } else {
                isPrevQuote = false;
                if (inQuote) {
                    text.append(ch);
                } else if (!isSyntaxChar(ch)) {
                    if (itemType != 0) {
                        items.add(new PatternItem(itemType, itemLength));
                        itemType = 0;
                    }
                    text.append(ch);
                } else if (ch == itemType) {
                    itemLength++;
                } else {
                    if (itemType != 0) {
                        items.add(new PatternItem(itemType, itemLength));
                    } else if (text.length() > 0) {
                        items.add(text.toString());
                        text.setLength(0);
                    }
                    itemType = ch;
                    itemLength = 1;
                }
            }
            i++;
        }
        if (itemType != 0) {
            items.add(new PatternItem(itemType, itemLength));
        } else if (text.length() > 0) {
            items.add(text.toString());
            text.setLength(0);
        }
        Object[] array = items.toArray(new Object[items.size()]);
        this.patternItems = array;
        PARSED_PATTERN_CACHE.put(this.pattern, array);
        return this.patternItems;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public void zeroPaddingNumber(NumberFormat nf, StringBuffer buf, int value, int minDigits, int maxDigits) {
        if (!this.useLocalZeroPaddingNumberFormat || value < 0) {
            nf.setMinimumIntegerDigits(minDigits);
            nf.setMaximumIntegerDigits(maxDigits);
            nf.format((long) value, buf, new FieldPosition(-1));
            return;
        }
        fastZeroPaddingNumber(buf, value, minDigits, maxDigits);
    }

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
            String[] tmpDigits = ((DecimalFormat) this.numberFormat).getDecimalFormatSymbols().getDigitStringsLocal();
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
                int padding = minDigits - (limit - index);
            } else {
                index--;
            }
        }
        int padding2 = minDigits - (limit - index);
        while (padding2 > 0 && index > 0) {
            index--;
            this.decimalBuf[index] = this.decDigits[0];
            padding2--;
        }
        while (padding2 > 0) {
            buf.append(this.decDigits[0]);
            padding2--;
        }
        buf.append(this.decimalBuf, index, limit - index);
    }

    /* access modifiers changed from: protected */
    public String zeroPaddingNumber(long value, int minDigits, int maxDigits) {
        this.numberFormat.setMinimumIntegerDigits(minDigits);
        this.numberFormat.setMaximumIntegerDigits(maxDigits);
        return this.numberFormat.format(value);
    }

    /* access modifiers changed from: private */
    public static final boolean isNumeric(char formatChar, int count) {
        return NUMERIC_FORMAT_CHARS.indexOf(formatChar) >= 0 || (count <= 2 && NUMERIC_FORMAT_CHARS2.indexOf(formatChar) >= 0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:191:0x03fa, code lost:
        if (r12[1] == 0) goto L_0x03fc;
     */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x028e  */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x0325  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x0335 A[SYNTHETIC, Splitter:B:142:0x0335] */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x0356 A[SYNTHETIC, Splitter:B:152:0x0356] */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x0378 A[SYNTHETIC, Splitter:B:159:0x0378] */
    /* JADX WARNING: Removed duplicated region for block: B:244:0x04f2  */
    /* JADX WARNING: Removed duplicated region for block: B:247:0x0500  */
    /* JADX WARNING: Removed duplicated region for block: B:249:0x0510  */
    /* JADX WARNING: Removed duplicated region for block: B:250:0x0518  */
    /* JADX WARNING: Removed duplicated region for block: B:255:0x0531  */
    /* JADX WARNING: Removed duplicated region for block: B:281:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void parse(java.lang.String r39, com.ibm.icu.util.Calendar r40, java.text.ParsePosition r41) {
        /*
            r38 = this;
            r13 = r38
            r14 = r41
            r0 = 0
            r1 = 0
            com.ibm.icu.util.Calendar r2 = r13.calendar
            r3 = r40
            if (r3 == r2) goto L_0x003c
            java.lang.String r2 = r40.getType()
            com.ibm.icu.util.Calendar r4 = r13.calendar
            java.lang.String r4 = r4.getType()
            boolean r2 = r2.equals(r4)
            if (r2 != 0) goto L_0x003c
            com.ibm.icu.util.Calendar r2 = r13.calendar
            long r4 = r40.getTimeInMillis()
            r2.setTimeInMillis(r4)
            com.ibm.icu.util.Calendar r2 = r13.calendar
            com.ibm.icu.util.TimeZone r0 = r2.getTimeZone()
            com.ibm.icu.util.Calendar r2 = r13.calendar
            com.ibm.icu.util.TimeZone r4 = r40.getTimeZone()
            r2.setTimeZone(r4)
            r1 = r40
            com.ibm.icu.util.Calendar r2 = r13.calendar
            r12 = r0
            r11 = r1
            r15 = r2
            goto L_0x003f
        L_0x003c:
            r12 = r0
            r11 = r1
            r15 = r3
        L_0x003f:
            int r0 = r41.getIndex()
            r10 = 0
            if (r0 >= 0) goto L_0x004a
            r14.setErrorIndex(r10)
            return
        L_0x004a:
            r9 = r0
            com.ibm.icu.util.Output r1 = new com.ibm.icu.util.Output
            r2 = 0
            r1.<init>(r2)
            r8 = r1
            com.ibm.icu.util.Output r1 = new com.ibm.icu.util.Output
            com.ibm.icu.text.TimeZoneFormat$TimeType r2 = com.ibm.icu.text.TimeZoneFormat.TimeType.UNKNOWN
            r1.<init>(r2)
            r7 = r1
            r6 = 1
            boolean[] r1 = new boolean[r6]
            r1[r10] = r10
            r16 = r1
            r1 = -1
            r2 = 0
            r3 = 0
            r4 = 0
            com.ibm.icu.text.DateFormatSymbols r5 = r13.formatData
            java.lang.String[] r5 = r5.leapMonthPatterns
            if (r5 == 0) goto L_0x0086
            com.ibm.icu.text.DateFormatSymbols r5 = r13.formatData
            java.lang.String[] r5 = r5.leapMonthPatterns
            int r5 = r5.length
            r6 = 7
            if (r5 < r6) goto L_0x0086
            com.ibm.icu.text.MessageFormat r5 = new com.ibm.icu.text.MessageFormat
            com.ibm.icu.text.DateFormatSymbols r6 = r13.formatData
            java.lang.String[] r6 = r6.leapMonthPatterns
            r17 = 6
            r6 = r6[r17]
            com.ibm.icu.util.ULocale r10 = r13.locale
            r5.<init>((java.lang.String) r6, (com.ibm.icu.util.ULocale) r10)
            r4 = r5
            r18 = r4
            goto L_0x0088
        L_0x0086:
            r18 = r4
        L_0x0088:
            java.lang.Object[] r10 = r38.getPatternItems()
            r4 = 0
            r19 = r2
            r20 = r3
            r6 = r4
        L_0x0092:
            int r2 = r10.length
            if (r6 >= r2) goto L_0x0248
            r2 = r10[r6]
            boolean r2 = r2 instanceof com.ibm.icu.text.SimpleDateFormat.PatternItem
            if (r2 == 0) goto L_0x0206
            r2 = r10[r6]
            r5 = r2
            com.ibm.icu.text.SimpleDateFormat$PatternItem r5 = (com.ibm.icu.text.SimpleDateFormat.PatternItem) r5
            boolean r2 = r5.isNumeric
            r3 = -1
            if (r2 == 0) goto L_0x00c8
            if (r1 != r3) goto L_0x00c8
            int r2 = r6 + 1
            int r4 = r10.length
            if (r2 >= r4) goto L_0x00c8
            int r2 = r6 + 1
            r2 = r10[r2]
            boolean r2 = r2 instanceof com.ibm.icu.text.SimpleDateFormat.PatternItem
            if (r2 == 0) goto L_0x00c8
            int r2 = r6 + 1
            r2 = r10[r2]
            com.ibm.icu.text.SimpleDateFormat$PatternItem r2 = (com.ibm.icu.text.SimpleDateFormat.PatternItem) r2
            boolean r2 = r2.isNumeric
            if (r2 == 0) goto L_0x00c8
            r1 = r6
            int r2 = r5.length
            r4 = r0
            r19 = r2
            r20 = r4
            r4 = r1
            goto L_0x00c9
        L_0x00c8:
            r4 = r1
        L_0x00c9:
            if (r4 == r3) goto L_0x013d
            int r1 = r5.length
            if (r4 != r6) goto L_0x00d4
            r1 = r19
            r21 = r1
            goto L_0x00d6
        L_0x00d4:
            r21 = r1
        L_0x00d6:
            char r3 = r5.type
            r22 = 1
            r23 = 0
            r1 = r38
            r2 = r39
            r24 = r3
            r3 = r0
            r25 = r4
            r4 = r24
            r26 = r5
            r5 = r21
            r24 = r6
            r6 = r22
            r40 = r7
            r7 = r23
            r22 = r8
            r8 = r16
            r28 = r9
            r9 = r15
            r29 = r10
            r10 = r18
            r30 = r11
            r11 = r40
            int r0 = r1.subParse(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            if (r0 >= 0) goto L_0x012f
            int r19 = r19 + -1
            if (r19 != 0) goto L_0x011c
            r11 = r28
            r14.setIndex(r11)
            r14.setErrorIndex(r0)
            if (r12 == 0) goto L_0x011b
            com.ibm.icu.util.Calendar r1 = r13.calendar
            r1.setTimeZone(r12)
        L_0x011b:
            return
        L_0x011c:
            r11 = r28
            r6 = r25
            r0 = r20
            r7 = r40
            r9 = r11
            r8 = r22
            r1 = r25
            r10 = r29
            r11 = r30
            goto L_0x0092
        L_0x012f:
            r11 = r28
            r8 = r11
            r26 = r15
            r6 = r24
            r4 = r25
            r7 = r29
            r15 = r12
            goto L_0x0202
        L_0x013d:
            r25 = r4
            r26 = r5
            r24 = r6
            r40 = r7
            r22 = r8
            r29 = r10
            r30 = r11
            r11 = r9
            r10 = r26
            char r1 = r10.type
            r2 = 108(0x6c, float:1.51E-43)
            if (r1 == r2) goto L_0x01f6
            r17 = -1
            r9 = r0
            char r4 = r10.type
            int r5 = r10.length
            r6 = 0
            r7 = 1
            r1 = r38
            r2 = r39
            r3 = r0
            r8 = r16
            r21 = r9
            r9 = r15
            r23 = r10
            r10 = r18
            r31 = r11
            r11 = r40
            r26 = r15
            r15 = r12
            r12 = r22
            int r0 = r1.subParse(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            if (r0 >= 0) goto L_0x01eb
            r1 = -32000(0xffffffffffff8300, float:NaN)
            if (r0 != r1) goto L_0x01d7
            r1 = r21
            int r6 = r24 + 1
            r7 = r29
            int r0 = r7.length
            if (r6 >= r0) goto L_0x01cd
            r2 = 0
            int r6 = r24 + 1
            r0 = r7[r6]     // Catch:{ ClassCastException -> 0x01ba }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ ClassCastException -> 0x01ba }
            if (r0 != 0) goto L_0x0198
            int r6 = r24 + 1
            r2 = r7[r6]
            r0 = r2
            java.lang.String r0 = (java.lang.String) r0
        L_0x0198:
            int r2 = r0.length()
            r3 = 0
        L_0x019d:
            if (r3 >= r2) goto L_0x01ad
            char r4 = r0.charAt(r3)
            boolean r5 = com.ibm.icu.impl.PatternProps.isWhiteSpace(r4)
            if (r5 == 0) goto L_0x01ad
            int r3 = r3 + 1
            goto L_0x019d
        L_0x01ad:
            if (r3 != r2) goto L_0x01b2
            int r6 = r24 + 1
            goto L_0x01b4
        L_0x01b2:
            r6 = r24
        L_0x01b4:
            r0 = r1
            r4 = r17
            r8 = r31
            goto L_0x0202
        L_0x01ba:
            r0 = move-exception
            r8 = r31
            r14.setIndex(r8)
            r3 = r21
            r14.setErrorIndex(r3)
            if (r15 == 0) goto L_0x01cc
            com.ibm.icu.util.Calendar r4 = r13.calendar
            r4.setTimeZone(r15)
        L_0x01cc:
            return
        L_0x01cd:
            r3 = r21
            r8 = r31
            r0 = r1
            r4 = r17
            r6 = r24
            goto L_0x0202
        L_0x01d7:
            r3 = r21
            r7 = r29
            r8 = r31
            r14.setIndex(r8)
            r14.setErrorIndex(r3)
            if (r15 == 0) goto L_0x01ea
            com.ibm.icu.util.Calendar r1 = r13.calendar
            r1.setTimeZone(r15)
        L_0x01ea:
            return
        L_0x01eb:
            r3 = r21
            r7 = r29
            r8 = r31
            r4 = r17
            r6 = r24
            goto L_0x0202
        L_0x01f6:
            r23 = r10
            r8 = r11
            r26 = r15
            r7 = r29
            r15 = r12
            r6 = r24
            r4 = r25
        L_0x0202:
            r1 = r4
            r2 = 0
            r10 = 1
            goto L_0x023a
        L_0x0206:
            r24 = r6
            r40 = r7
            r22 = r8
            r8 = r9
            r7 = r10
            r30 = r11
            r26 = r15
            r15 = r12
            r9 = -1
            r10 = 1
            boolean[] r11 = new boolean[r10]
            r1 = r38
            r2 = r39
            r3 = r0
            r4 = r7
            r5 = r24
            r6 = r11
            int r0 = r1.matchLiteral(r2, r3, r4, r5, r6)
            r2 = 0
            boolean r1 = r11[r2]
            if (r1 != 0) goto L_0x0237
            r14.setIndex(r8)
            r14.setErrorIndex(r0)
            if (r15 == 0) goto L_0x0236
            com.ibm.icu.util.Calendar r1 = r13.calendar
            r1.setTimeZone(r15)
        L_0x0236:
            return
        L_0x0237:
            r1 = r9
            r6 = r24
        L_0x023a:
            int r6 = r6 + r10
            r10 = r7
            r9 = r8
            r12 = r15
            r8 = r22
            r15 = r26
            r11 = r30
            r7 = r40
            goto L_0x0092
        L_0x0248:
            r24 = r6
            r40 = r7
            r22 = r8
            r8 = r9
            r7 = r10
            r30 = r11
            r26 = r15
            r2 = 0
            r10 = 1
            r15 = r12
            int r3 = r39.length()
            if (r0 >= r3) goto L_0x0285
            r3 = r39
            char r4 = r3.charAt(r0)
            r5 = 46
            if (r4 != r5) goto L_0x0287
            com.ibm.icu.text.DateFormat$BooleanAttribute r5 = com.ibm.icu.text.DateFormat.BooleanAttribute.PARSE_ALLOW_WHITESPACE
            boolean r5 = r13.getBooleanAttribute(r5)
            if (r5 == 0) goto L_0x0287
            int r5 = r7.length
            if (r5 == 0) goto L_0x0287
            int r5 = r7.length
            int r5 = r5 - r10
            r5 = r7[r5]
            boolean r6 = r5 instanceof com.ibm.icu.text.SimpleDateFormat.PatternItem
            if (r6 == 0) goto L_0x0287
            r6 = r5
            com.ibm.icu.text.SimpleDateFormat$PatternItem r6 = (com.ibm.icu.text.SimpleDateFormat.PatternItem) r6
            boolean r6 = r6.isNumeric
            if (r6 != 0) goto L_0x0287
            int r0 = r0 + 1
            r4 = r0
            goto L_0x0288
        L_0x0285:
            r3 = r39
        L_0x0287:
            r4 = r0
        L_0x0288:
            r5 = r22
            java.lang.Object r0 = r5.value
            if (r0 == 0) goto L_0x0325
            com.ibm.icu.util.ULocale r0 = r38.getLocale()
            com.ibm.icu.impl.DayPeriodRules r0 = com.ibm.icu.impl.DayPeriodRules.getInstance(r0)
            r6 = 10
            r9 = r26
            boolean r11 = r9.isSet(r6)
            r10 = 11
            if (r11 != 0) goto L_0x02c7
            boolean r11 = r9.isSet(r10)
            if (r11 != 0) goto L_0x02c7
            java.lang.Object r6 = r5.value
            com.ibm.icu.impl.DayPeriodRules$DayPeriod r6 = (com.ibm.icu.impl.DayPeriodRules.DayPeriod) r6
            double r2 = r0.getMidPointForDayPeriod(r6)
            int r6 = (int) r2
            double r12 = (double) r6
            double r12 = r2 - r12
            r21 = 0
            int r12 = (r12 > r21 ? 1 : (r12 == r21 ? 0 : -1))
            if (r12 <= 0) goto L_0x02bd
            r12 = 30
            goto L_0x02be
        L_0x02bd:
            r12 = 0
        L_0x02be:
            r9.set(r10, r6)
            r10 = 12
            r9.set(r10, r12)
            goto L_0x0327
        L_0x02c7:
            boolean r2 = r9.isSet(r10)
            if (r2 == 0) goto L_0x02d2
            int r2 = r9.get(r10)
            goto L_0x02da
        L_0x02d2:
            int r2 = r9.get(r6)
            if (r2 != 0) goto L_0x02da
            r2 = 12
        L_0x02da:
            if (r2 < 0) goto L_0x031f
            r3 = 23
            if (r2 > r3) goto L_0x031f
            if (r2 == 0) goto L_0x031b
            r6 = 13
            if (r6 > r2) goto L_0x02e9
            if (r2 > r3) goto L_0x02e9
            goto L_0x031b
        L_0x02e9:
            r3 = 12
            if (r2 != r3) goto L_0x02ee
            r2 = 0
        L_0x02ee:
            double r10 = (double) r2
            int r3 = r9.get(r3)
            double r12 = (double) r3
            r21 = 4633641066610819072(0x404e000000000000, double:60.0)
            double r12 = r12 / r21
            double r10 = r10 + r12
            java.lang.Object r3 = r5.value
            com.ibm.icu.impl.DayPeriodRules$DayPeriod r3 = (com.ibm.icu.impl.DayPeriodRules.DayPeriod) r3
            double r12 = r0.getMidPointForDayPeriod(r3)
            double r21 = r10 - r12
            r25 = -4604930618986332160(0xc018000000000000, double:-6.0)
            int r3 = (r25 > r21 ? 1 : (r25 == r21 ? 0 : -1))
            r6 = 9
            if (r3 > 0) goto L_0x0316
            r25 = 4618441417868443648(0x4018000000000000, double:6.0)
            int r3 = (r21 > r25 ? 1 : (r21 == r25 ? 0 : -1))
            if (r3 >= 0) goto L_0x0316
            r3 = 0
            r9.set(r6, r3)
            goto L_0x0327
        L_0x0316:
            r3 = 1
            r9.set(r6, r3)
            goto L_0x0327
        L_0x031b:
            r9.set(r10, r2)
            goto L_0x0327
        L_0x031f:
            java.lang.AssertionError r3 = new java.lang.AssertionError
            r3.<init>()
            throw r3
        L_0x0325:
            r9 = r26
        L_0x0327:
            r14.setIndex(r4)
            r2 = r40
            java.lang.Object r0 = r2.value     // Catch:{ IllegalArgumentException -> 0x051b }
            com.ibm.icu.text.TimeZoneFormat$TimeType r0 = (com.ibm.icu.text.TimeZoneFormat.TimeType) r0     // Catch:{ IllegalArgumentException -> 0x051b }
            r3 = 0
            boolean r6 = r16[r3]     // Catch:{ IllegalArgumentException -> 0x051b }
            if (r6 != 0) goto L_0x0351
            com.ibm.icu.text.TimeZoneFormat$TimeType r3 = com.ibm.icu.text.TimeZoneFormat.TimeType.UNKNOWN     // Catch:{ IllegalArgumentException -> 0x0343 }
            if (r0 == r3) goto L_0x033a
            goto L_0x0351
        L_0x033a:
            r13 = r1
            r17 = r2
            r29 = r7
            r28 = r8
            goto L_0x04fb
        L_0x0343:
            r0 = move-exception
            r13 = r1
            r17 = r2
            r29 = r7
            r28 = r8
            r1 = r30
            r2 = r38
            goto L_0x0527
        L_0x0351:
            r3 = 0
            boolean r6 = r16[r3]     // Catch:{ IllegalArgumentException -> 0x051b }
            if (r6 == 0) goto L_0x0374
            java.lang.Object r3 = r9.clone()     // Catch:{ IllegalArgumentException -> 0x0343 }
            com.ibm.icu.util.Calendar r3 = (com.ibm.icu.util.Calendar) r3     // Catch:{ IllegalArgumentException -> 0x0343 }
            java.util.Date r6 = r3.getTime()     // Catch:{ IllegalArgumentException -> 0x0343 }
            java.util.Date r10 = r38.getDefaultCenturyStart()     // Catch:{ IllegalArgumentException -> 0x0343 }
            boolean r10 = r6.before(r10)     // Catch:{ IllegalArgumentException -> 0x0343 }
            if (r10 == 0) goto L_0x0374
            int r10 = r38.getDefaultCenturyStartYear()     // Catch:{ IllegalArgumentException -> 0x0343 }
            int r10 = r10 + 100
            r11 = 1
            r9.set(r11, r10)     // Catch:{ IllegalArgumentException -> 0x0343 }
        L_0x0374:
            com.ibm.icu.text.TimeZoneFormat$TimeType r3 = com.ibm.icu.text.TimeZoneFormat.TimeType.UNKNOWN     // Catch:{ IllegalArgumentException -> 0x051b }
            if (r0 == r3) goto L_0x04f2
            java.lang.Object r3 = r9.clone()     // Catch:{ IllegalArgumentException -> 0x04e5 }
            com.ibm.icu.util.Calendar r3 = (com.ibm.icu.util.Calendar) r3     // Catch:{ IllegalArgumentException -> 0x04e5 }
            com.ibm.icu.util.TimeZone r6 = r3.getTimeZone()     // Catch:{ IllegalArgumentException -> 0x04e5 }
            r10 = 0
            boolean r11 = r6 instanceof com.ibm.icu.util.BasicTimeZone     // Catch:{ IllegalArgumentException -> 0x04e5 }
            if (r11 == 0) goto L_0x038b
            r11 = r6
            com.ibm.icu.util.BasicTimeZone r11 = (com.ibm.icu.util.BasicTimeZone) r11     // Catch:{ IllegalArgumentException -> 0x0343 }
            r10 = r11
        L_0x038b:
            r11 = 15
            r12 = 0
            r3.set(r11, r12)     // Catch:{ IllegalArgumentException -> 0x04e5 }
            r13 = 16
            r3.set(r13, r12)     // Catch:{ IllegalArgumentException -> 0x04e5 }
            long r21 = r3.getTimeInMillis()     // Catch:{ IllegalArgumentException -> 0x04e5 }
            r25 = r21
            r12 = 2
            int[] r12 = new int[r12]     // Catch:{ IllegalArgumentException -> 0x04e5 }
            if (r10 == 0) goto L_0x03d3
            com.ibm.icu.text.TimeZoneFormat$TimeType r13 = com.ibm.icu.text.TimeZoneFormat.TimeType.STANDARD     // Catch:{ IllegalArgumentException -> 0x0343 }
            if (r0 != r13) goto L_0x03bc
            r34 = 1
            r35 = 1
            r31 = r10
            r32 = r25
            r36 = r12
            r31.getOffsetFromLocal(r32, r34, r35, r36)     // Catch:{ IllegalArgumentException -> 0x0343 }
            r13 = r1
            r17 = r2
            r29 = r7
            r28 = r8
            r1 = r25
            goto L_0x040d
        L_0x03bc:
            r34 = 3
            r35 = 3
            r31 = r10
            r32 = r25
            r36 = r12
            r31.getOffsetFromLocal(r32, r34, r35, r36)     // Catch:{ IllegalArgumentException -> 0x0343 }
            r13 = r1
            r17 = r2
            r29 = r7
            r28 = r8
            r1 = r25
            goto L_0x040d
        L_0x03d3:
            r13 = r1
            r17 = r2
            r1 = r25
            r11 = 1
            r6.getOffset(r1, r11, r12)     // Catch:{ IllegalArgumentException -> 0x04db }
            com.ibm.icu.text.TimeZoneFormat$TimeType r11 = com.ibm.icu.text.TimeZoneFormat.TimeType.STANDARD     // Catch:{ IllegalArgumentException -> 0x04db }
            if (r0 != r11) goto L_0x03f3
            r11 = 1
            r22 = r12[r11]     // Catch:{ IllegalArgumentException -> 0x03e8 }
            if (r22 != 0) goto L_0x03e6
            goto L_0x03f3
        L_0x03e6:
            r11 = 1
            goto L_0x03fc
        L_0x03e8:
            r0 = move-exception
            r2 = r38
            r29 = r7
            r28 = r8
            r1 = r30
            goto L_0x0527
        L_0x03f3:
            com.ibm.icu.text.TimeZoneFormat$TimeType r11 = com.ibm.icu.text.TimeZoneFormat.TimeType.DAYLIGHT     // Catch:{ IllegalArgumentException -> 0x04db }
            if (r0 != r11) goto L_0x0409
            r11 = 1
            r22 = r12[r11]     // Catch:{ IllegalArgumentException -> 0x04db }
            if (r22 != 0) goto L_0x0409
        L_0x03fc:
            r22 = 86400000(0x5265c00, double:4.2687272E-316)
            r29 = r7
            r28 = r8
            long r7 = r1 - r22
            r6.getOffset(r7, r11, r12)     // Catch:{ IllegalArgumentException -> 0x04d5 }
            goto L_0x040d
        L_0x0409:
            r29 = r7
            r28 = r8
        L_0x040d:
            r7 = 1
            r8 = r12[r7]     // Catch:{ IllegalArgumentException -> 0x04d5 }
            com.ibm.icu.text.TimeZoneFormat$TimeType r11 = com.ibm.icu.text.TimeZoneFormat.TimeType.STANDARD     // Catch:{ IllegalArgumentException -> 0x04d5 }
            if (r0 != r11) goto L_0x0427
            r7 = r12[r7]     // Catch:{ IllegalArgumentException -> 0x04d5 }
            if (r7 == 0) goto L_0x041f
            r8 = 0
            r33 = r0
            r34 = r1
            goto L_0x04c7
        L_0x041f:
            r33 = r0
            r34 = r1
            r22 = r8
            goto L_0x04c5
        L_0x0427:
            r7 = 1
            r11 = r12[r7]     // Catch:{ IllegalArgumentException -> 0x04d5 }
            if (r11 != 0) goto L_0x04bf
            if (r10 == 0) goto L_0x04ae
            r7 = 0
            r11 = r12[r7]     // Catch:{ IllegalArgumentException -> 0x04d5 }
            r22 = r8
            long r7 = (long) r11     // Catch:{ IllegalArgumentException -> 0x04d5 }
            long r25 = r1 + r7
            r7 = r25
            r31 = r25
            r11 = 0
            r23 = 0
        L_0x043d:
            r33 = r0
            r0 = 1
            com.ibm.icu.util.TimeZoneTransition r27 = r10.getPreviousTransition(r7, r0)     // Catch:{ IllegalArgumentException -> 0x04d5 }
            if (r27 != 0) goto L_0x044b
            r34 = r1
            r0 = r31
            goto L_0x0463
        L_0x044b:
            long r34 = r27.getTime()     // Catch:{ IllegalArgumentException -> 0x04d5 }
            r36 = 1
            long r7 = r34 - r36
            com.ibm.icu.util.TimeZoneRule r34 = r27.getFrom()     // Catch:{ IllegalArgumentException -> 0x04d5 }
            int r34 = r34.getDSTSavings()     // Catch:{ IllegalArgumentException -> 0x04d5 }
            r11 = r34
            if (r11 == 0) goto L_0x04a9
            r34 = r1
            r0 = r31
        L_0x0463:
            r2 = 0
            com.ibm.icu.util.TimeZoneTransition r31 = r10.getNextTransition(r0, r2)     // Catch:{ IllegalArgumentException -> 0x04d5 }
            r2 = r31
            if (r2 != 0) goto L_0x046d
            goto L_0x0480
        L_0x046d:
            long r31 = r2.getTime()     // Catch:{ IllegalArgumentException -> 0x04d5 }
            r0 = r31
            com.ibm.icu.util.TimeZoneRule r31 = r2.getTo()     // Catch:{ IllegalArgumentException -> 0x04d5 }
            int r31 = r31.getDSTSavings()     // Catch:{ IllegalArgumentException -> 0x04d5 }
            r23 = r31
            if (r23 == 0) goto L_0x0463
        L_0x0480:
            if (r27 == 0) goto L_0x0492
            if (r2 == 0) goto L_0x0492
            long r31 = r25 - r7
            long r36 = r0 - r25
            int r31 = (r31 > r36 ? 1 : (r31 == r36 ? 0 : -1))
            if (r31 <= 0) goto L_0x048f
            r22 = r23
            goto L_0x04a6
        L_0x048f:
            r22 = r11
            goto L_0x04a6
        L_0x0492:
            if (r27 == 0) goto L_0x0499
            if (r11 == 0) goto L_0x0499
            r22 = r11
            goto L_0x04a6
        L_0x0499:
            if (r2 == 0) goto L_0x04a0
            if (r23 == 0) goto L_0x04a0
            r22 = r23
            goto L_0x04a6
        L_0x04a0:
            int r31 = r10.getDSTSavings()     // Catch:{ IllegalArgumentException -> 0x04d5 }
            r22 = r31
        L_0x04a6:
            r8 = r22
            goto L_0x04b9
        L_0x04a9:
            r34 = r1
            r0 = r33
            goto L_0x043d
        L_0x04ae:
            r33 = r0
            r34 = r1
            r22 = r8
            int r0 = r6.getDSTSavings()     // Catch:{ IllegalArgumentException -> 0x04d5 }
            r8 = r0
        L_0x04b9:
            if (r8 != 0) goto L_0x04c7
            r8 = 3600000(0x36ee80, float:5.044674E-39)
            goto L_0x04c7
        L_0x04bf:
            r33 = r0
            r34 = r1
            r22 = r8
        L_0x04c5:
            r8 = r22
        L_0x04c7:
            r0 = 0
            r0 = r12[r0]     // Catch:{ IllegalArgumentException -> 0x04d5 }
            r1 = 15
            r9.set(r1, r0)     // Catch:{ IllegalArgumentException -> 0x04d5 }
            r0 = 16
            r9.set(r0, r8)     // Catch:{ IllegalArgumentException -> 0x04d5 }
            goto L_0x04fb
        L_0x04d5:
            r0 = move-exception
            r2 = r38
            r1 = r30
            goto L_0x0527
        L_0x04db:
            r0 = move-exception
            r29 = r7
            r28 = r8
            r2 = r38
            r1 = r30
            goto L_0x0527
        L_0x04e5:
            r0 = move-exception
            r13 = r1
            r17 = r2
            r29 = r7
            r28 = r8
            r2 = r38
            r1 = r30
            goto L_0x0527
        L_0x04f2:
            r33 = r0
            r13 = r1
            r17 = r2
            r29 = r7
            r28 = r8
        L_0x04fb:
            r1 = r30
            if (r1 == 0) goto L_0x050e
            com.ibm.icu.util.TimeZone r0 = r9.getTimeZone()
            r1.setTimeZone(r0)
            long r2 = r9.getTimeInMillis()
            r1.setTimeInMillis(r2)
        L_0x050e:
            if (r15 == 0) goto L_0x0518
            r2 = r38
            com.ibm.icu.util.Calendar r0 = r2.calendar
            r0.setTimeZone(r15)
            goto L_0x051a
        L_0x0518:
            r2 = r38
        L_0x051a:
            return
        L_0x051b:
            r0 = move-exception
            r13 = r1
            r17 = r2
            r29 = r7
            r28 = r8
            r1 = r30
            r2 = r38
        L_0x0527:
            r14.setErrorIndex(r4)
            r3 = r28
            r14.setIndex(r3)
            if (r15 == 0) goto L_0x0536
            com.ibm.icu.util.Calendar r6 = r2.calendar
            r6.setTimeZone(r15)
        L_0x0536:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.SimpleDateFormat.parse(java.lang.String, com.ibm.icu.util.Calendar, java.text.ParsePosition):void");
    }

    private int matchLiteral(String text, int pos, Object[] items, int itemIndex, boolean[] complete) {
        String str = text;
        Object[] objArr = items;
        int i = itemIndex;
        int originalPos = pos;
        String patternLiteral = (String) objArr[i];
        int plen = patternLiteral.length();
        int tlen = text.length();
        int idx = 0;
        int pos2 = pos;
        while (idx < plen && pos2 < tlen) {
            char pch = patternLiteral.charAt(idx);
            char ich = str.charAt(pos2);
            if (!PatternProps.isWhiteSpace(pch) || !PatternProps.isWhiteSpace(ich)) {
                if (pch != ich) {
                    if (ich != '.' || pos2 != originalPos || i <= 0 || !getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_ALLOW_WHITESPACE)) {
                        if ((pch != ' ' && pch != '.') || !getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_ALLOW_WHITESPACE)) {
                            if (pos2 == originalPos || !getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_PARTIAL_LITERAL_MATCH)) {
                                break;
                            }
                            idx++;
                        } else {
                            idx++;
                        }
                    } else {
                        Object before = objArr[i - 1];
                        if (!(before instanceof PatternItem) || ((PatternItem) before).isNumeric) {
                            break;
                        }
                        pos2++;
                    }
                }
            } else {
                while (idx + 1 < plen && PatternProps.isWhiteSpace(patternLiteral.charAt(idx + 1))) {
                    idx++;
                }
                while (pos2 + 1 < tlen && PatternProps.isWhiteSpace(str.charAt(pos2 + 1))) {
                    pos2++;
                }
            }
            idx++;
            pos2++;
        }
        complete[0] = idx == plen;
        if (complete[0] || !getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_ALLOW_WHITESPACE) || i <= 0 || i >= objArr.length - 1 || originalPos >= tlen) {
            return pos2;
        }
        Object before2 = objArr[i - 1];
        Object after = objArr[i + 1];
        if (!(before2 instanceof PatternItem) || !(after instanceof PatternItem)) {
            return pos2;
        }
        char beforeType = ((PatternItem) before2).type;
        char afterType = ((PatternItem) after).type;
        UnicodeSet unicodeSet = DATE_PATTERN_TYPE;
        if (unicodeSet.contains((int) beforeType) == unicodeSet.contains((int) afterType)) {
            return pos2;
        }
        int newPos = originalPos;
        while (newPos < tlen && PatternProps.isWhiteSpace(str.charAt(newPos))) {
            newPos++;
        }
        complete[0] = newPos > originalPos;
        return newPos;
    }

    /* access modifiers changed from: protected */
    public int matchString(String text, int start, int field, String[] data, Calendar cal) {
        return matchString(text, start, field, data, (String) null, cal);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0031, code lost:
        r13 = com.ibm.icu.impl.SimpleFormatterImpl.formatRawPattern(r5, 1, 1, new java.lang.CharSequence[]{r4[r7]});
     */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int matchString(java.lang.String r19, int r20, int r21, java.lang.String[] r22, java.lang.String r23, com.ibm.icu.util.Calendar r24) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            r3 = r21
            r4 = r22
            r5 = r23
            r6 = r24
            r7 = 0
            int r8 = r4.length
            r9 = 7
            if (r3 != r9) goto L_0x0014
            r7 = 1
        L_0x0014:
            r9 = 0
            r10 = -1
            r11 = 0
            r12 = 0
        L_0x0018:
            r13 = 1
            if (r7 >= r8) goto L_0x0050
            r14 = r4[r7]
            int r14 = r14.length()
            if (r14 <= r9) goto L_0x002f
            r15 = r4[r7]
            int r15 = r0.regionMatchesWithOptionalDot(r1, r2, r15, r14)
            r12 = r15
            if (r15 < 0) goto L_0x002f
            r10 = r7
            r9 = r12
            r11 = 0
        L_0x002f:
            if (r5 == 0) goto L_0x004d
            java.lang.CharSequence[] r15 = new java.lang.CharSequence[r13]
            r16 = 0
            r17 = r4[r7]
            r15[r16] = r17
            java.lang.String r13 = com.ibm.icu.impl.SimpleFormatterImpl.formatRawPattern(r5, r13, r13, r15)
            int r14 = r13.length()
            if (r14 <= r9) goto L_0x004d
            int r15 = r0.regionMatchesWithOptionalDot(r1, r2, r13, r14)
            r12 = r15
            if (r15 < 0) goto L_0x004d
            r10 = r7
            r9 = r12
            r11 = 1
        L_0x004d:
            int r7 = r7 + 1
            goto L_0x0018
        L_0x0050:
            if (r10 < 0) goto L_0x0065
            if (r3 < 0) goto L_0x0062
            if (r3 != r13) goto L_0x0058
            int r10 = r10 + 1
        L_0x0058:
            r6.set(r3, r10)
            if (r5 == 0) goto L_0x0062
            r13 = 22
            r6.set(r13, r11)
        L_0x0062:
            int r13 = r2 + r9
            return r13
        L_0x0065:
            int r13 = ~r2
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.SimpleDateFormat.matchString(java.lang.String, int, int, java.lang.String[], java.lang.String, com.ibm.icu.util.Calendar):int");
    }

    private int regionMatchesWithOptionalDot(String text, int start, String data, int length) {
        if (text.regionMatches(true, start, data, 0, length)) {
            return length;
        }
        if (data.length() <= 0 || data.charAt(data.length() - 1) != '.') {
            return -1;
        }
        if (text.regionMatches(true, start, data, 0, length - 1)) {
            return length - 1;
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public int matchQuarterString(String text, int start, int field, String[] data, Calendar cal) {
        int count = data.length;
        int bestMatchLength = 0;
        int bestMatch = -1;
        for (int i = 0; i < count; i++) {
            int length = data[i].length();
            if (length > bestMatchLength) {
                int regionMatchesWithOptionalDot = regionMatchesWithOptionalDot(text, start, data[i], length);
                int matchLength = regionMatchesWithOptionalDot;
                if (regionMatchesWithOptionalDot >= 0) {
                    bestMatch = i;
                    bestMatchLength = matchLength;
                }
            }
        }
        if (bestMatch < 0) {
            return -start;
        }
        cal.set(field, bestMatch * 3);
        return start + bestMatchLength;
    }

    private int matchDayPeriodString(String text, int start, String[] data, int dataLength, Output<DayPeriodRules.DayPeriod> dayPeriod) {
        int length;
        int bestMatchLength = 0;
        int bestMatch = -1;
        for (int i = 0; i < dataLength; i++) {
            if (data[i] != null && (length = data[i].length()) > bestMatchLength) {
                int regionMatchesWithOptionalDot = regionMatchesWithOptionalDot(text, start, data[i], length);
                int matchLength = regionMatchesWithOptionalDot;
                if (regionMatchesWithOptionalDot >= 0) {
                    bestMatch = i;
                    bestMatchLength = matchLength;
                }
            }
        }
        if (bestMatch < 0) {
            return -start;
        }
        dayPeriod.value = DayPeriodRules.DayPeriod.VALUES[bestMatch];
        return start + bestMatchLength;
    }

    /* access modifiers changed from: protected */
    public int subParse(String text, int start, char ch, int count, boolean obeyCount, boolean allowNegative, boolean[] ambiguousYear, Calendar cal) {
        return subParse(text, start, ch, count, obeyCount, allowNegative, ambiguousYear, cal, (MessageFormat) null, (Output<TimeZoneFormat.TimeType>) null);
    }

    private int subParse(String text, int start, char ch, int count, boolean obeyCount, boolean allowNegative, boolean[] ambiguousYear, Calendar cal, MessageFormat numericLeapMonthFormatter, Output<TimeZoneFormat.TimeType> output) {
        return subParse(text, start, ch, count, obeyCount, allowNegative, ambiguousYear, cal, (MessageFormat) null, (Output<TimeZoneFormat.TimeType>) null, (Output<DayPeriodRules.DayPeriod>) null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v14, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v20, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v22, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v11, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v10, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v25, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v38, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v26, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v39, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v27, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v40, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v28, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v41, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v42, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v30, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v93, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v293, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v43, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v31, resolved type: int} */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x038b, code lost:
        if (r5 > r12.formatData.shortYearNames.length) goto L_0x0390;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0103  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x013d  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0146  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x014d  */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int subParse(java.lang.String r34, int r35, char r36, int r37, boolean r38, boolean r39, boolean[] r40, com.ibm.icu.util.Calendar r41, com.ibm.icu.text.MessageFormat r42, com.ibm.icu.util.Output<com.ibm.icu.text.TimeZoneFormat.TimeType> r43, com.ibm.icu.util.Output<com.ibm.icu.impl.DayPeriodRules.DayPeriod> r44) {
        /*
            r33 = this;
            r12 = r33
            r13 = r34
            r14 = r37
            r15 = r39
            r11 = r41
            r10 = r42
            r9 = r43
            r0 = 0
            r1 = 0
            r6 = 0
            java.text.ParsePosition r2 = new java.text.ParsePosition
            r7 = 0
            r2.<init>(r7)
            r8 = r2
            int r5 = getIndexFromChar(r36)
            r2 = -1
            if (r5 != r2) goto L_0x0023
            r2 = r35
            int r3 = ~r2
            return r3
        L_0x0023:
            r2 = r35
            r4 = r36
            com.ibm.icu.text.NumberFormat r3 = r12.getNumberFormat(r4)
            int[] r1 = PATTERN_INDEX_TO_CALENDAR_FIELD
            r1 = r1[r5]
            if (r10 == 0) goto L_0x0034
            r10.setFormatByArgumentIndex(r7, r3)
        L_0x0034:
            java.lang.String r7 = r41.getType()
            r17 = r0
            java.lang.String r0 = "chinese"
            boolean r0 = r7.equals(r0)
            if (r0 != 0) goto L_0x0051
            java.lang.String r0 = r41.getType()
            java.lang.String r7 = "dangi"
            boolean r0 = r0.equals(r7)
            if (r0 == 0) goto L_0x004f
            goto L_0x0051
        L_0x004f:
            r0 = 0
            goto L_0x0052
        L_0x0051:
            r0 = 1
        L_0x0052:
            r19 = r0
            r7 = r2
        L_0x0055:
            int r0 = r34.length()
            if (r7 < r0) goto L_0x005d
            int r0 = ~r7
            return r0
        L_0x005d:
            int r0 = com.ibm.icu.text.UTF16.charAt((java.lang.String) r13, (int) r7)
            boolean r2 = com.ibm.icu.lang.UCharacter.isUWhiteSpace(r0)
            if (r2 == 0) goto L_0x0074
            boolean r2 = com.ibm.icu.impl.PatternProps.isWhiteSpace(r0)
            if (r2 != 0) goto L_0x006e
            goto L_0x0074
        L_0x006e:
            int r2 = com.ibm.icu.text.UTF16.getCharCount(r0)
            int r7 = r7 + r2
            goto L_0x0055
        L_0x0074:
            r8.setIndex(r7)
            r2 = 2
            r0 = 4
            if (r5 == r0) goto L_0x00b2
            r0 = 15
            if (r5 == r0) goto L_0x00b2
            if (r5 != r2) goto L_0x0083
            if (r14 <= r2) goto L_0x00b2
        L_0x0083:
            r0 = 26
            if (r5 == r0) goto L_0x00b2
            r0 = 19
            if (r5 == r0) goto L_0x00b2
            r0 = 25
            if (r5 == r0) goto L_0x00b2
            r0 = 1
            if (r5 == r0) goto L_0x00b2
            r0 = 18
            if (r5 == r0) goto L_0x00b2
            r0 = 30
            if (r5 == r0) goto L_0x00b2
            if (r5 != 0) goto L_0x009e
            if (r19 != 0) goto L_0x00b2
        L_0x009e:
            r0 = 27
            if (r5 == r0) goto L_0x00b2
            r0 = 28
            if (r5 == r0) goto L_0x00b2
            r0 = 8
            if (r5 != r0) goto L_0x00ab
            goto L_0x00b2
        L_0x00ab:
            r25 = r1
            r9 = r2
            r11 = r5
            r5 = r3
            goto L_0x014f
        L_0x00b2:
            r0 = 0
            if (r10 == 0) goto L_0x00fb
            if (r5 == r2) goto L_0x00c1
            r2 = 26
            if (r5 != r2) goto L_0x00bc
            goto L_0x00c1
        L_0x00bc:
            r35 = r0
            r23 = r1
            goto L_0x00ff
        L_0x00c1:
            java.lang.Object[] r2 = r10.parse(r13, r8)
            r35 = r0
            if (r2 == 0) goto L_0x00ef
            int r0 = r8.getIndex()
            if (r0 <= r7) goto L_0x00ef
            r23 = r1
            r0 = 0
            r1 = r2[r0]
            boolean r1 = r1 instanceof java.lang.Number
            if (r1 == 0) goto L_0x00ec
            r1 = 1
            r24 = r2[r0]
            r0 = r24
            java.lang.Number r0 = (java.lang.Number) r0
            r35 = r0
            r22 = r1
            r0 = 22
            r1 = 1
            r11.set(r0, r1)
            r17 = r35
            goto L_0x0101
        L_0x00ec:
            r0 = 22
            goto L_0x00f3
        L_0x00ef:
            r23 = r1
            r0 = 22
        L_0x00f3:
            r8.setIndex(r7)
            r1 = 0
            r11.set(r0, r1)
            goto L_0x00ff
        L_0x00fb:
            r35 = r0
            r23 = r1
        L_0x00ff:
            r22 = r35
        L_0x0101:
            if (r22 != 0) goto L_0x013d
            if (r38 == 0) goto L_0x0127
            int r0 = r7 + r14
            int r1 = r34.length()
            if (r0 <= r1) goto L_0x010f
            int r0 = ~r7
            return r0
        L_0x010f:
            r2 = 4
            r0 = r33
            r25 = r23
            r1 = r34
            r11 = r2
            r9 = 2
            r2 = r37
            r35 = r3
            r3 = r8
            r4 = r39
            r11 = r5
            r5 = r35
            java.lang.Number r0 = r0.parseInt(r1, r2, r3, r4, r5)
            goto L_0x0133
        L_0x0127:
            r35 = r3
            r11 = r5
            r25 = r23
            r9 = 2
            r5 = r35
            java.lang.Number r0 = r12.parseInt(r13, r8, r15, r5)
        L_0x0133:
            if (r0 != 0) goto L_0x0144
            boolean r1 = r12.allowNumericFallback(r11)
            if (r1 != 0) goto L_0x0144
            int r1 = ~r7
            return r1
        L_0x013d:
            r11 = r5
            r25 = r23
            r9 = 2
            r5 = r3
            r0 = r17
        L_0x0144:
            if (r0 == 0) goto L_0x014d
            int r6 = r0.intValue()
            r17 = r0
            goto L_0x014f
        L_0x014d:
            r17 = r0
        L_0x014f:
            r4 = 6
            r3 = 5
            r2 = 3
            switch(r11) {
                case 0: goto L_0x090e;
                case 1: goto L_0x089d;
                case 2: goto L_0x076f;
                case 3: goto L_0x0155;
                case 4: goto L_0x074f;
                case 5: goto L_0x0155;
                case 6: goto L_0x0155;
                case 7: goto L_0x0155;
                case 8: goto L_0x0719;
                case 9: goto L_0x0668;
                case 10: goto L_0x0155;
                case 11: goto L_0x0155;
                case 12: goto L_0x0155;
                case 13: goto L_0x0155;
                case 14: goto L_0x05f6;
                case 15: goto L_0x05d4;
                case 16: goto L_0x0155;
                case 17: goto L_0x05ab;
                case 18: goto L_0x089d;
                case 19: goto L_0x057f;
                case 20: goto L_0x0155;
                case 21: goto L_0x0155;
                case 22: goto L_0x0155;
                case 23: goto L_0x0551;
                case 24: goto L_0x0528;
                case 25: goto L_0x049a;
                case 26: goto L_0x076f;
                case 27: goto L_0x0433;
                case 28: goto L_0x03cc;
                case 29: goto L_0x039d;
                case 30: goto L_0x034e;
                case 31: goto L_0x0326;
                case 32: goto L_0x02f4;
                case 33: goto L_0x02ba;
                case 34: goto L_0x0155;
                case 35: goto L_0x022c;
                case 36: goto L_0x01c0;
                case 37: goto L_0x016e;
                default: goto L_0x0155;
            }
        L_0x0155:
            r35 = r5
            r18 = r6
            r15 = r7
            r10 = r8
            r9 = r11
            r8 = r25
            r11 = r41
            r7 = r43
            if (r38 == 0) goto L_0x0981
            int r0 = r15 + r14
            int r1 = r34.length()
            if (r0 <= r1) goto L_0x096b
            int r0 = -r15
            return r0
        L_0x016e:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>(r2)
            r9 = r0
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String r0 = r0.getTimeSeparatorString()
            r9.add(r0)
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String r0 = r0.getTimeSeparatorString()
            java.lang.String r1 = ":"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x018e
            r9.add(r1)
        L_0x018e:
            com.ibm.icu.text.DateFormat$BooleanAttribute r0 = com.ibm.icu.text.DateFormat.BooleanAttribute.PARSE_PARTIAL_LITERAL_MATCH
            boolean r0 = r12.getBooleanAttribute(r0)
            if (r0 == 0) goto L_0x01a7
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String r0 = r0.getTimeSeparatorString()
            java.lang.String r1 = "."
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x01a7
            r9.add(r1)
        L_0x01a7:
            r3 = -1
            r0 = 0
            java.lang.String[] r0 = new java.lang.String[r0]
            java.lang.Object[] r0 = r9.toArray(r0)
            r4 = r0
            java.lang.String[] r4 = (java.lang.String[]) r4
            r0 = r33
            r1 = r34
            r2 = r7
            r35 = r5
            r5 = r41
            int r0 = r0.matchString(r1, r2, r3, r4, r5)
            return r0
        L_0x01c0:
            r35 = r5
            r9 = 0
            com.ibm.icu.text.DateFormat$BooleanAttribute r0 = com.ibm.icu.text.DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH
            boolean r0 = r12.getBooleanAttribute(r0)
            if (r0 != 0) goto L_0x01cd
            if (r14 != r2) goto L_0x01e5
        L_0x01cd:
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r3 = r0.abbreviatedDayPeriods
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r0 = r0.abbreviatedDayPeriods
            int r4 = r0.length
            r0 = r33
            r1 = r34
            r2 = r7
            r5 = r44
            int r0 = r0.matchDayPeriodString(r1, r2, r3, r4, r5)
            r9 = r0
            if (r0 <= 0) goto L_0x01e5
            return r9
        L_0x01e5:
            com.ibm.icu.text.DateFormat$BooleanAttribute r0 = com.ibm.icu.text.DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH
            boolean r0 = r12.getBooleanAttribute(r0)
            if (r0 != 0) goto L_0x01f0
            r0 = 4
            if (r14 != r0) goto L_0x0208
        L_0x01f0:
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r3 = r0.wideDayPeriods
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r0 = r0.wideDayPeriods
            int r4 = r0.length
            r0 = r33
            r1 = r34
            r2 = r7
            r5 = r44
            int r0 = r0.matchDayPeriodString(r1, r2, r3, r4, r5)
            r9 = r0
            if (r0 <= 0) goto L_0x0208
            return r9
        L_0x0208:
            com.ibm.icu.text.DateFormat$BooleanAttribute r0 = com.ibm.icu.text.DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH
            boolean r0 = r12.getBooleanAttribute(r0)
            if (r0 != 0) goto L_0x0213
            r5 = 4
            if (r14 != r5) goto L_0x022b
        L_0x0213:
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r3 = r0.narrowDayPeriods
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r0 = r0.narrowDayPeriods
            int r4 = r0.length
            r0 = r33
            r1 = r34
            r2 = r7
            r5 = r44
            int r0 = r0.matchDayPeriodString(r1, r2, r3, r4, r5)
            r9 = r0
            if (r0 <= 0) goto L_0x022b
            return r9
        L_0x022b:
            return r9
        L_0x022c:
            r35 = r5
            r5 = 4
            r3 = 97
            r0 = r33
            r1 = r34
            r9 = r2
            r2 = r7
            r4 = r37
            r16 = r5
            r5 = r38
            r26 = r6
            r6 = r39
            r20 = r7
            r7 = r40
            r27 = r8
            r8 = r41
            r9 = r42
            r10 = r43
            r28 = r11
            r15 = r16
            r11 = r44
            int r6 = r0.subParse(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            if (r6 <= 0) goto L_0x025a
            return r6
        L_0x025a:
            r7 = 0
            com.ibm.icu.text.DateFormat$BooleanAttribute r0 = com.ibm.icu.text.DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH
            boolean r0 = r12.getBooleanAttribute(r0)
            if (r0 != 0) goto L_0x0266
            r8 = 3
            if (r14 != r8) goto L_0x027b
        L_0x0266:
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r3 = r0.abbreviatedDayPeriods
            r4 = 2
            r0 = r33
            r1 = r34
            r2 = r20
            r5 = r44
            int r0 = r0.matchDayPeriodString(r1, r2, r3, r4, r5)
            r7 = r0
            if (r0 <= 0) goto L_0x027b
            return r7
        L_0x027b:
            com.ibm.icu.text.DateFormat$BooleanAttribute r0 = com.ibm.icu.text.DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH
            boolean r0 = r12.getBooleanAttribute(r0)
            if (r0 != 0) goto L_0x0285
            if (r14 != r15) goto L_0x029a
        L_0x0285:
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r3 = r0.wideDayPeriods
            r4 = 2
            r0 = r33
            r1 = r34
            r2 = r20
            r5 = r44
            int r0 = r0.matchDayPeriodString(r1, r2, r3, r4, r5)
            r7 = r0
            if (r0 <= 0) goto L_0x029a
            return r7
        L_0x029a:
            com.ibm.icu.text.DateFormat$BooleanAttribute r0 = com.ibm.icu.text.DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH
            boolean r0 = r12.getBooleanAttribute(r0)
            if (r0 != 0) goto L_0x02a4
            if (r14 != r15) goto L_0x02b9
        L_0x02a4:
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r3 = r0.narrowDayPeriods
            r4 = 2
            r0 = r33
            r1 = r34
            r2 = r20
            r5 = r44
            int r0 = r0.matchDayPeriodString(r1, r2, r3, r4, r5)
            r7 = r0
            if (r0 <= 0) goto L_0x02b9
            return r7
        L_0x02b9:
            return r7
        L_0x02ba:
            r35 = r5
            r26 = r6
            r20 = r7
            r27 = r8
            r28 = r11
            switch(r14) {
                case 1: goto L_0x02d3;
                case 2: goto L_0x02d0;
                case 3: goto L_0x02cd;
                case 4: goto L_0x02ca;
                default: goto L_0x02c7;
            }
        L_0x02c7:
            com.ibm.icu.text.TimeZoneFormat$Style r0 = com.ibm.icu.text.TimeZoneFormat.Style.ISO_EXTENDED_LOCAL_FULL
            goto L_0x02d6
        L_0x02ca:
            com.ibm.icu.text.TimeZoneFormat$Style r0 = com.ibm.icu.text.TimeZoneFormat.Style.ISO_BASIC_LOCAL_FULL
            goto L_0x02d6
        L_0x02cd:
            com.ibm.icu.text.TimeZoneFormat$Style r0 = com.ibm.icu.text.TimeZoneFormat.Style.ISO_EXTENDED_LOCAL_FIXED
            goto L_0x02d6
        L_0x02d0:
            com.ibm.icu.text.TimeZoneFormat$Style r0 = com.ibm.icu.text.TimeZoneFormat.Style.ISO_BASIC_LOCAL_FIXED
            goto L_0x02d6
        L_0x02d3:
            com.ibm.icu.text.TimeZoneFormat$Style r0 = com.ibm.icu.text.TimeZoneFormat.Style.ISO_BASIC_LOCAL_SHORT
        L_0x02d6:
            com.ibm.icu.text.TimeZoneFormat r1 = r33.tzFormat()
            r7 = r43
            r10 = r27
            com.ibm.icu.util.TimeZone r1 = r1.parse(r0, r13, r10, r7)
            if (r1 == 0) goto L_0x02ee
            r11 = r41
            r11.setTimeZone(r1)
            int r2 = r10.getIndex()
            return r2
        L_0x02ee:
            r11 = r41
            r15 = r20
            int r2 = ~r15
            return r2
        L_0x02f4:
            r35 = r5
            r26 = r6
            r15 = r7
            r10 = r8
            r28 = r11
            r11 = r41
            r7 = r43
            switch(r14) {
                case 1: goto L_0x030f;
                case 2: goto L_0x030c;
                case 3: goto L_0x0309;
                case 4: goto L_0x0306;
                default: goto L_0x0303;
            }
        L_0x0303:
            com.ibm.icu.text.TimeZoneFormat$Style r0 = com.ibm.icu.text.TimeZoneFormat.Style.ISO_EXTENDED_FULL
            goto L_0x0312
        L_0x0306:
            com.ibm.icu.text.TimeZoneFormat$Style r0 = com.ibm.icu.text.TimeZoneFormat.Style.ISO_BASIC_FULL
            goto L_0x0312
        L_0x0309:
            com.ibm.icu.text.TimeZoneFormat$Style r0 = com.ibm.icu.text.TimeZoneFormat.Style.ISO_EXTENDED_FIXED
            goto L_0x0312
        L_0x030c:
            com.ibm.icu.text.TimeZoneFormat$Style r0 = com.ibm.icu.text.TimeZoneFormat.Style.ISO_BASIC_FIXED
            goto L_0x0312
        L_0x030f:
            com.ibm.icu.text.TimeZoneFormat$Style r0 = com.ibm.icu.text.TimeZoneFormat.Style.ISO_BASIC_SHORT
        L_0x0312:
            com.ibm.icu.text.TimeZoneFormat r1 = r33.tzFormat()
            com.ibm.icu.util.TimeZone r1 = r1.parse(r0, r13, r10, r7)
            if (r1 == 0) goto L_0x0324
            r11.setTimeZone(r1)
            int r2 = r10.getIndex()
            return r2
        L_0x0324:
            int r2 = ~r15
            return r2
        L_0x0326:
            r35 = r5
            r26 = r6
            r15 = r7
            r10 = r8
            r28 = r11
            r6 = 4
            r11 = r41
            r7 = r43
            if (r14 >= r6) goto L_0x0338
            com.ibm.icu.text.TimeZoneFormat$Style r0 = com.ibm.icu.text.TimeZoneFormat.Style.LOCALIZED_GMT_SHORT
            goto L_0x033a
        L_0x0338:
            com.ibm.icu.text.TimeZoneFormat$Style r0 = com.ibm.icu.text.TimeZoneFormat.Style.LOCALIZED_GMT
        L_0x033a:
            com.ibm.icu.text.TimeZoneFormat r1 = r33.tzFormat()
            com.ibm.icu.util.TimeZone r1 = r1.parse(r0, r13, r10, r7)
            if (r1 == 0) goto L_0x034c
            r11.setTimeZone(r1)
            int r2 = r10.getIndex()
            return r2
        L_0x034c:
            int r2 = ~r15
            return r2
        L_0x034e:
            r35 = r5
            r26 = r6
            r15 = r7
            r10 = r8
            r28 = r11
            r11 = r41
            r7 = r43
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r0 = r0.shortYearNames
            if (r0 == 0) goto L_0x0374
            r3 = 1
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r4 = r0.shortYearNames
            r5 = 0
            r0 = r33
            r1 = r34
            r2 = r15
            r6 = r41
            int r0 = r0.matchString(r1, r2, r3, r4, r5, r6)
            if (r0 <= 0) goto L_0x0374
            return r0
        L_0x0374:
            if (r17 == 0) goto L_0x0399
            com.ibm.icu.text.DateFormat$BooleanAttribute r0 = com.ibm.icu.text.DateFormat.BooleanAttribute.PARSE_ALLOW_NUMERIC
            boolean r0 = r12.getBooleanAttribute(r0)
            if (r0 != 0) goto L_0x038e
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r0 = r0.shortYearNames
            if (r0 == 0) goto L_0x038e
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r0 = r0.shortYearNames
            int r0 = r0.length
            r5 = r26
            if (r5 <= r0) goto L_0x039b
            goto L_0x0390
        L_0x038e:
            r5 = r26
        L_0x0390:
            r0 = 1
            r11.set(r0, r5)
            int r0 = r10.getIndex()
            return r0
        L_0x0399:
            r5 = r26
        L_0x039b:
            int r0 = ~r15
            return r0
        L_0x039d:
            r35 = r5
            r5 = r6
            r15 = r7
            r10 = r8
            r28 = r11
            r11 = r41
            r7 = r43
            r0 = 0
            switch(r14) {
                case 1: goto L_0x03b5;
                case 2: goto L_0x03b2;
                case 3: goto L_0x03af;
                default: goto L_0x03ac;
            }
        L_0x03ac:
            com.ibm.icu.text.TimeZoneFormat$Style r0 = com.ibm.icu.text.TimeZoneFormat.Style.GENERIC_LOCATION
            goto L_0x03b8
        L_0x03af:
            com.ibm.icu.text.TimeZoneFormat$Style r0 = com.ibm.icu.text.TimeZoneFormat.Style.EXEMPLAR_LOCATION
            goto L_0x03b8
        L_0x03b2:
            com.ibm.icu.text.TimeZoneFormat$Style r0 = com.ibm.icu.text.TimeZoneFormat.Style.ZONE_ID
            goto L_0x03b8
        L_0x03b5:
            com.ibm.icu.text.TimeZoneFormat$Style r0 = com.ibm.icu.text.TimeZoneFormat.Style.ZONE_ID_SHORT
        L_0x03b8:
            com.ibm.icu.text.TimeZoneFormat r1 = r33.tzFormat()
            com.ibm.icu.util.TimeZone r1 = r1.parse(r0, r13, r10, r7)
            if (r1 == 0) goto L_0x03ca
            r11.setTimeZone(r1)
            int r2 = r10.getIndex()
            return r2
        L_0x03ca:
            int r2 = ~r15
            return r2
        L_0x03cc:
            r35 = r5
            r5 = r6
            r15 = r7
            r10 = r8
            r28 = r11
            r6 = 4
            r11 = r41
            r7 = r43
            r8 = r2
            if (r14 <= r9) goto L_0x0427
            if (r17 == 0) goto L_0x03e7
            com.ibm.icu.text.DateFormat$BooleanAttribute r0 = com.ibm.icu.text.DateFormat.BooleanAttribute.PARSE_ALLOW_NUMERIC
            boolean r0 = r12.getBooleanAttribute(r0)
            if (r0 == 0) goto L_0x03e7
            r6 = r5
            goto L_0x0428
        L_0x03e7:
            r9 = 0
            com.ibm.icu.text.DateFormat$BooleanAttribute r0 = com.ibm.icu.text.DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH
            boolean r0 = r12.getBooleanAttribute(r0)
            if (r0 != 0) goto L_0x03f5
            if (r14 != r6) goto L_0x03f3
            goto L_0x03f5
        L_0x03f3:
            r6 = r5
            goto L_0x040a
        L_0x03f5:
            r3 = 2
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r4 = r0.standaloneQuarters
            r0 = r33
            r1 = r34
            r2 = r15
            r6 = r5
            r5 = r41
            int r0 = r0.matchQuarterString(r1, r2, r3, r4, r5)
            r9 = r0
            if (r0 <= 0) goto L_0x040a
            return r9
        L_0x040a:
            com.ibm.icu.text.DateFormat$BooleanAttribute r0 = com.ibm.icu.text.DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH
            boolean r0 = r12.getBooleanAttribute(r0)
            if (r0 != 0) goto L_0x0416
            if (r14 != r8) goto L_0x0415
            goto L_0x0416
        L_0x0415:
            return r9
        L_0x0416:
            r3 = 2
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r4 = r0.standaloneShortQuarters
            r0 = r33
            r1 = r34
            r2 = r15
            r5 = r41
            int r0 = r0.matchQuarterString(r1, r2, r3, r4, r5)
            return r0
        L_0x0427:
            r6 = r5
        L_0x0428:
            int r0 = r6 + -1
            int r0 = r0 * r8
            r11.set(r9, r0)
            int r0 = r10.getIndex()
            return r0
        L_0x0433:
            r35 = r5
            r5 = r6
            r15 = r7
            r10 = r8
            r28 = r11
            r6 = 4
            r11 = r41
            r7 = r43
            r8 = r2
            if (r14 <= r9) goto L_0x048e
            if (r17 == 0) goto L_0x044e
            com.ibm.icu.text.DateFormat$BooleanAttribute r0 = com.ibm.icu.text.DateFormat.BooleanAttribute.PARSE_ALLOW_NUMERIC
            boolean r0 = r12.getBooleanAttribute(r0)
            if (r0 == 0) goto L_0x044e
            r6 = r5
            goto L_0x048f
        L_0x044e:
            r9 = 0
            com.ibm.icu.text.DateFormat$BooleanAttribute r0 = com.ibm.icu.text.DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH
            boolean r0 = r12.getBooleanAttribute(r0)
            if (r0 != 0) goto L_0x045c
            if (r14 != r6) goto L_0x045a
            goto L_0x045c
        L_0x045a:
            r6 = r5
            goto L_0x0471
        L_0x045c:
            r3 = 2
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r4 = r0.quarters
            r0 = r33
            r1 = r34
            r2 = r15
            r6 = r5
            r5 = r41
            int r0 = r0.matchQuarterString(r1, r2, r3, r4, r5)
            r9 = r0
            if (r0 <= 0) goto L_0x0471
            return r9
        L_0x0471:
            com.ibm.icu.text.DateFormat$BooleanAttribute r0 = com.ibm.icu.text.DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH
            boolean r0 = r12.getBooleanAttribute(r0)
            if (r0 != 0) goto L_0x047d
            if (r14 != r8) goto L_0x047c
            goto L_0x047d
        L_0x047c:
            return r9
        L_0x047d:
            r3 = 2
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r4 = r0.shortQuarters
            r0 = r33
            r1 = r34
            r2 = r15
            r5 = r41
            int r0 = r0.matchQuarterString(r1, r2, r3, r4, r5)
            return r0
        L_0x048e:
            r6 = r5
        L_0x048f:
            int r0 = r6 + -1
            int r0 = r0 * r8
            r11.set(r9, r0)
            int r0 = r10.getIndex()
            return r0
        L_0x049a:
            r35 = r5
            r9 = r6
            r15 = r7
            r10 = r8
            r28 = r11
            r6 = 4
            r11 = r41
            r7 = r43
            r8 = r2
            r0 = 1
            if (r14 == r0) goto L_0x051e
            if (r17 == 0) goto L_0x04b6
            com.ibm.icu.text.DateFormat$BooleanAttribute r0 = com.ibm.icu.text.DateFormat.BooleanAttribute.PARSE_ALLOW_NUMERIC
            boolean r0 = r12.getBooleanAttribute(r0)
            if (r0 == 0) goto L_0x04b6
            goto L_0x051e
        L_0x04b6:
            r16 = 0
            com.ibm.icu.text.DateFormat$BooleanAttribute r0 = com.ibm.icu.text.DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH
            boolean r0 = r12.getBooleanAttribute(r0)
            if (r0 != 0) goto L_0x04c2
            if (r14 != r6) goto L_0x04da
        L_0x04c2:
            r3 = 7
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r5 = r0.standaloneWeekdays
            r6 = 0
            r0 = r33
            r1 = r34
            r2 = r15
            r4 = r5
            r5 = r6
            r6 = r41
            int r0 = r0.matchString(r1, r2, r3, r4, r5, r6)
            r16 = r0
            if (r0 <= 0) goto L_0x04da
            return r16
        L_0x04da:
            com.ibm.icu.text.DateFormat$BooleanAttribute r0 = com.ibm.icu.text.DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH
            boolean r0 = r12.getBooleanAttribute(r0)
            if (r0 != 0) goto L_0x04e4
            if (r14 != r8) goto L_0x04fa
        L_0x04e4:
            r3 = 7
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r4 = r0.standaloneShortWeekdays
            r5 = 0
            r0 = r33
            r1 = r34
            r2 = r15
            r6 = r41
            int r0 = r0.matchString(r1, r2, r3, r4, r5, r6)
            r16 = r0
            if (r0 <= 0) goto L_0x04fa
            return r16
        L_0x04fa:
            com.ibm.icu.text.DateFormat$BooleanAttribute r0 = com.ibm.icu.text.DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH
            boolean r0 = r12.getBooleanAttribute(r0)
            if (r0 != 0) goto L_0x0505
            r5 = 6
            if (r14 != r5) goto L_0x051d
        L_0x0505:
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r0 = r0.standaloneShorterWeekdays
            if (r0 == 0) goto L_0x051d
            r3 = 7
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r4 = r0.standaloneShorterWeekdays
            r5 = 0
            r0 = r33
            r1 = r34
            r2 = r15
            r6 = r41
            int r0 = r0.matchString(r1, r2, r3, r4, r5, r6)
            return r0
        L_0x051d:
            return r16
        L_0x051e:
            r4 = r25
            r11.set(r4, r9)
            int r0 = r10.getIndex()
            return r0
        L_0x0528:
            r35 = r5
            r9 = r6
            r15 = r7
            r10 = r8
            r28 = r11
            r4 = r25
            r6 = 4
            r11 = r41
            r7 = r43
            if (r14 >= r6) goto L_0x053b
            com.ibm.icu.text.TimeZoneFormat$Style r0 = com.ibm.icu.text.TimeZoneFormat.Style.GENERIC_SHORT
            goto L_0x053d
        L_0x053b:
            com.ibm.icu.text.TimeZoneFormat$Style r0 = com.ibm.icu.text.TimeZoneFormat.Style.GENERIC_LONG
        L_0x053d:
            com.ibm.icu.text.TimeZoneFormat r1 = r33.tzFormat()
            com.ibm.icu.util.TimeZone r1 = r1.parse(r0, r13, r10, r7)
            if (r1 == 0) goto L_0x054f
            r11.setTimeZone(r1)
            int r2 = r10.getIndex()
            return r2
        L_0x054f:
            int r2 = ~r15
            return r2
        L_0x0551:
            r35 = r5
            r9 = r6
            r15 = r7
            r10 = r8
            r28 = r11
            r4 = r25
            r6 = 4
            r11 = r41
            r7 = r43
            if (r14 >= r6) goto L_0x0564
            com.ibm.icu.text.TimeZoneFormat$Style r0 = com.ibm.icu.text.TimeZoneFormat.Style.ISO_BASIC_LOCAL_FULL
            goto L_0x056b
        L_0x0564:
            if (r14 != r3) goto L_0x0569
            com.ibm.icu.text.TimeZoneFormat$Style r0 = com.ibm.icu.text.TimeZoneFormat.Style.ISO_EXTENDED_FULL
            goto L_0x056b
        L_0x0569:
            com.ibm.icu.text.TimeZoneFormat$Style r0 = com.ibm.icu.text.TimeZoneFormat.Style.LOCALIZED_GMT
        L_0x056b:
            com.ibm.icu.text.TimeZoneFormat r1 = r33.tzFormat()
            com.ibm.icu.util.TimeZone r1 = r1.parse(r0, r13, r10, r7)
            if (r1 == 0) goto L_0x057d
            r11.setTimeZone(r1)
            int r2 = r10.getIndex()
            return r2
        L_0x057d:
            int r2 = ~r15
            return r2
        L_0x057f:
            r35 = r5
            r15 = r7
            r10 = r8
            r28 = r11
            r11 = r41
            r7 = r43
            r8 = r2
            r5 = r4
            r2 = r6
            r4 = r25
            r6 = 4
            if (r14 <= r9) goto L_0x05a3
            if (r17 == 0) goto L_0x059c
            com.ibm.icu.text.DateFormat$BooleanAttribute r0 = com.ibm.icu.text.DateFormat.BooleanAttribute.PARSE_ALLOW_NUMERIC
            boolean r0 = r12.getBooleanAttribute(r0)
            if (r0 == 0) goto L_0x059c
            goto L_0x05a3
        L_0x059c:
            r29 = r2
            r9 = r4
            r4 = r8
            r8 = r3
            goto L_0x067a
        L_0x05a3:
            r11.set(r4, r2)
            int r0 = r10.getIndex()
            return r0
        L_0x05ab:
            r35 = r5
            r2 = r6
            r15 = r7
            r10 = r8
            r28 = r11
            r4 = r25
            r6 = 4
            r11 = r41
            r7 = r43
            if (r14 >= r6) goto L_0x05be
            com.ibm.icu.text.TimeZoneFormat$Style r0 = com.ibm.icu.text.TimeZoneFormat.Style.SPECIFIC_SHORT
            goto L_0x05c0
        L_0x05be:
            com.ibm.icu.text.TimeZoneFormat$Style r0 = com.ibm.icu.text.TimeZoneFormat.Style.SPECIFIC_LONG
        L_0x05c0:
            com.ibm.icu.text.TimeZoneFormat r1 = r33.tzFormat()
            com.ibm.icu.util.TimeZone r1 = r1.parse(r0, r13, r10, r7)
            if (r1 == 0) goto L_0x05d2
            r11.setTimeZone(r1)
            int r3 = r10.getIndex()
            return r3
        L_0x05d2:
            int r3 = ~r15
            return r3
        L_0x05d4:
            r35 = r5
            r2 = r6
            r15 = r7
            r10 = r8
            r28 = r11
            r4 = r25
            r11 = r41
            r7 = r43
            r0 = 10
            int r1 = r11.getLeastMaximum(r0)
            r3 = 1
            int r1 = r1 + r3
            if (r2 != r1) goto L_0x05ed
            r6 = 0
            goto L_0x05ee
        L_0x05ed:
            r6 = r2
        L_0x05ee:
            r11.set(r0, r6)
            int r0 = r10.getIndex()
            return r0
        L_0x05f6:
            r35 = r5
            r2 = r6
            r15 = r7
            r10 = r8
            r28 = r11
            r4 = r25
            r11 = r41
            r7 = r43
            r8 = 0
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r0 = r0.ampmsNarrow
            if (r0 == 0) goto L_0x061c
            if (r14 < r3) goto L_0x061c
            com.ibm.icu.text.DateFormat$BooleanAttribute r0 = com.ibm.icu.text.DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH
            boolean r0 = r12.getBooleanAttribute(r0)
            if (r0 == 0) goto L_0x0615
            goto L_0x061c
        L_0x0615:
            r29 = r2
            r9 = r4
            r16 = r8
            r8 = r3
            goto L_0x063f
        L_0x061c:
            r5 = 9
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r6 = r0.ampms
            r9 = 0
            r0 = r33
            r1 = r34
            r29 = r2
            r2 = r15
            r16 = r8
            r8 = r3
            r3 = r5
            r5 = r4
            r4 = r6
            r6 = r5
            r5 = r9
            r9 = r6
            r6 = r41
            int r0 = r0.matchString(r1, r2, r3, r4, r5, r6)
            r1 = r0
            if (r0 <= 0) goto L_0x063d
            return r1
        L_0x063d:
            r16 = r1
        L_0x063f:
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r0 = r0.ampmsNarrow
            if (r0 == 0) goto L_0x0666
            if (r14 >= r8) goto L_0x064f
            com.ibm.icu.text.DateFormat$BooleanAttribute r0 = com.ibm.icu.text.DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH
            boolean r0 = r12.getBooleanAttribute(r0)
            if (r0 == 0) goto L_0x0666
        L_0x064f:
            r3 = 9
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r4 = r0.ampmsNarrow
            r5 = 0
            r0 = r33
            r1 = r34
            r2 = r15
            r6 = r41
            int r0 = r0.matchString(r1, r2, r3, r4, r5, r6)
            r16 = r0
            if (r0 <= 0) goto L_0x0666
            return r16
        L_0x0666:
            int r0 = ~r15
            return r0
        L_0x0668:
            r35 = r5
            r29 = r6
            r15 = r7
            r10 = r8
            r28 = r11
            r9 = r25
            r6 = 4
            r11 = r41
            r7 = r43
            r8 = r3
            r5 = r4
            r4 = r2
        L_0x067a:
            r16 = 0
            com.ibm.icu.text.DateFormat$BooleanAttribute r0 = com.ibm.icu.text.DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH
            boolean r0 = r12.getBooleanAttribute(r0)
            if (r0 != 0) goto L_0x068c
            if (r14 != r6) goto L_0x0687
            goto L_0x068c
        L_0x0687:
            r8 = r5
            r25 = r9
            r9 = r4
            goto L_0x06ab
        L_0x068c:
            r3 = 7
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r6 = r0.weekdays
            r18 = 0
            r0 = r33
            r1 = r34
            r2 = r15
            r25 = r9
            r9 = r4
            r4 = r6
            r6 = r5
            r5 = r18
            r8 = r6
            r6 = r41
            int r0 = r0.matchString(r1, r2, r3, r4, r5, r6)
            r16 = r0
            if (r0 <= 0) goto L_0x06ab
            return r16
        L_0x06ab:
            com.ibm.icu.text.DateFormat$BooleanAttribute r0 = com.ibm.icu.text.DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH
            boolean r0 = r12.getBooleanAttribute(r0)
            if (r0 != 0) goto L_0x06b5
            if (r14 != r9) goto L_0x06cb
        L_0x06b5:
            r3 = 7
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r4 = r0.shortWeekdays
            r5 = 0
            r0 = r33
            r1 = r34
            r2 = r15
            r6 = r41
            int r0 = r0.matchString(r1, r2, r3, r4, r5, r6)
            r16 = r0
            if (r0 <= 0) goto L_0x06cb
            return r16
        L_0x06cb:
            com.ibm.icu.text.DateFormat$BooleanAttribute r0 = com.ibm.icu.text.DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH
            boolean r0 = r12.getBooleanAttribute(r0)
            if (r0 != 0) goto L_0x06d5
            if (r14 != r8) goto L_0x06f1
        L_0x06d5:
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r0 = r0.shorterWeekdays
            if (r0 == 0) goto L_0x06f1
            r3 = 7
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r4 = r0.shorterWeekdays
            r5 = 0
            r0 = r33
            r1 = r34
            r2 = r15
            r6 = r41
            int r0 = r0.matchString(r1, r2, r3, r4, r5, r6)
            r16 = r0
            if (r0 <= 0) goto L_0x06f1
            return r16
        L_0x06f1:
            com.ibm.icu.text.DateFormat$BooleanAttribute r0 = com.ibm.icu.text.DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH
            boolean r0 = r12.getBooleanAttribute(r0)
            if (r0 != 0) goto L_0x06fc
            r0 = 5
            if (r14 != r0) goto L_0x0718
        L_0x06fc:
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r0 = r0.narrowWeekdays
            if (r0 == 0) goto L_0x0718
            r3 = 7
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r4 = r0.narrowWeekdays
            r5 = 0
            r0 = r33
            r1 = r34
            r2 = r15
            r6 = r41
            int r0 = r0.matchString(r1, r2, r3, r4, r5, r6)
            r16 = r0
            if (r0 <= 0) goto L_0x0718
            return r16
        L_0x0718:
            return r16
        L_0x0719:
            r9 = r2
            r35 = r5
            r29 = r6
            r15 = r7
            r10 = r8
            r28 = r11
            r11 = r41
            r7 = r43
            int r0 = r10.getIndex()
            int r0 = countDigits(r13, r15, r0)
            if (r0 >= r9) goto L_0x0739
            r6 = r29
        L_0x0732:
            if (r0 >= r9) goto L_0x0745
            int r6 = r6 * 10
            int r0 = r0 + 1
            goto L_0x0732
        L_0x0739:
            r1 = 1
        L_0x073a:
            if (r0 <= r9) goto L_0x0741
            int r1 = r1 * 10
            int r0 = r0 + -1
            goto L_0x073a
        L_0x0741:
            r5 = r29
            int r6 = r5 / r1
        L_0x0745:
            r1 = 14
            r11.set(r1, r6)
            int r1 = r10.getIndex()
            return r1
        L_0x074f:
            r35 = r5
            r5 = r6
            r15 = r7
            r10 = r8
            r28 = r11
            r11 = r41
            r7 = r43
            r0 = 11
            int r1 = r11.getMaximum(r0)
            r2 = 1
            int r1 = r1 + r2
            if (r5 != r1) goto L_0x0766
            r6 = 0
            goto L_0x0767
        L_0x0766:
            r6 = r5
        L_0x0767:
            r11.set(r0, r6)
            int r0 = r10.getIndex()
            return r0
        L_0x076f:
            r35 = r5
            r5 = r6
            r15 = r7
            r10 = r8
            r3 = r9
            r28 = r11
            r6 = 4
            r11 = r41
            r7 = r43
            r9 = r2
            r8 = r4
            r4 = r25
            if (r14 <= r3) goto L_0x0865
            if (r17 == 0) goto L_0x0795
            com.ibm.icu.text.DateFormat$BooleanAttribute r0 = com.ibm.icu.text.DateFormat.BooleanAttribute.PARSE_ALLOW_NUMERIC
            boolean r0 = r12.getBooleanAttribute(r0)
            if (r0 == 0) goto L_0x0795
            r0 = r3
            r31 = r4
            r32 = r5
            r9 = r28
            goto L_0x086c
        L_0x0795:
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r0 = r0.leapMonthPatterns
            if (r0 == 0) goto L_0x07a5
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r0 = r0.leapMonthPatterns
            int r0 = r0.length
            r1 = 7
            if (r0 < r1) goto L_0x07a5
            r0 = 1
            goto L_0x07a6
        L_0x07a5:
            r0 = 0
        L_0x07a6:
            r8 = r0
            r20 = 0
            com.ibm.icu.text.DateFormat$BooleanAttribute r0 = com.ibm.icu.text.DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH
            boolean r0 = r12.getBooleanAttribute(r0)
            r21 = 0
            if (r0 != 0) goto L_0x07be
            if (r14 != r6) goto L_0x07b6
            goto L_0x07be
        L_0x07b6:
            r31 = r4
            r32 = r5
            r30 = r28
            goto L_0x0818
        L_0x07be:
            r2 = r28
            if (r2 != r3) goto L_0x07f1
            r22 = 2
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r1 = r0.months
            if (r8 == 0) goto L_0x07d5
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r0 = r0.leapMonthPatterns
            r16 = 0
            r0 = r0[r16]
            r16 = r0
            goto L_0x07d7
        L_0x07d5:
            r16 = r21
        L_0x07d7:
            r0 = r33
            r23 = r1
            r1 = r34
            r30 = r2
            r2 = r15
            r3 = r22
            r31 = r4
            r4 = r23
            r32 = r5
            r5 = r16
            r6 = r41
            int r0 = r0.matchString(r1, r2, r3, r4, r5, r6)
            goto L_0x0813
        L_0x07f1:
            r30 = r2
            r31 = r4
            r32 = r5
            r3 = 2
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r4 = r0.standaloneMonths
            if (r8 == 0) goto L_0x0806
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r0 = r0.leapMonthPatterns
            r0 = r0[r9]
            r5 = r0
            goto L_0x0808
        L_0x0806:
            r5 = r21
        L_0x0808:
            r0 = r33
            r1 = r34
            r2 = r15
            r6 = r41
            int r0 = r0.matchString(r1, r2, r3, r4, r5, r6)
        L_0x0813:
            r20 = r0
            if (r20 <= 0) goto L_0x0818
            return r20
        L_0x0818:
            com.ibm.icu.text.DateFormat$BooleanAttribute r0 = com.ibm.icu.text.DateFormat.BooleanAttribute.PARSE_MULTIPLE_PATTERNS_FOR_MATCH
            boolean r0 = r12.getBooleanAttribute(r0)
            if (r0 != 0) goto L_0x0824
            if (r14 != r9) goto L_0x0823
            goto L_0x0824
        L_0x0823:
            return r20
        L_0x0824:
            r9 = r30
            r0 = 2
            if (r9 != r0) goto L_0x0847
            r3 = 2
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r4 = r0.shortMonths
            if (r8 == 0) goto L_0x0839
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r0 = r0.leapMonthPatterns
            r1 = 1
            r0 = r0[r1]
            r5 = r0
            goto L_0x083b
        L_0x0839:
            r5 = r21
        L_0x083b:
            r0 = r33
            r1 = r34
            r2 = r15
            r6 = r41
            int r0 = r0.matchString(r1, r2, r3, r4, r5, r6)
            goto L_0x0864
        L_0x0847:
            r3 = 2
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r4 = r0.standaloneShortMonths
            if (r8 == 0) goto L_0x0857
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r0 = r0.leapMonthPatterns
            r1 = 4
            r0 = r0[r1]
            r5 = r0
            goto L_0x0859
        L_0x0857:
            r5 = r21
        L_0x0859:
            r0 = r33
            r1 = r34
            r2 = r15
            r6 = r41
            int r0 = r0.matchString(r1, r2, r3, r4, r5, r6)
        L_0x0864:
            return r0
        L_0x0865:
            r0 = r3
            r31 = r4
            r32 = r5
            r9 = r28
        L_0x086c:
            r6 = r32
            int r1 = r6 + -1
            r11.set(r0, r1)
            java.lang.String r1 = r41.getType()
            java.lang.String r2 = "hebrew"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0898
            if (r6 < r8) goto L_0x0898
            r1 = 1
            boolean r2 = r11.isSet(r1)
            if (r2 == 0) goto L_0x0896
            int r1 = r11.get(r1)
            boolean r1 = com.ibm.icu.util.HebrewCalendar.isLeapYear(r1)
            if (r1 != 0) goto L_0x0898
            r11.set(r0, r6)
            goto L_0x0898
        L_0x0896:
            DelayedHebrewMonthCheck = r1
        L_0x0898:
            int r0 = r10.getIndex()
            return r0
        L_0x089d:
            r35 = r5
            r15 = r7
            r10 = r8
            r0 = r9
            r9 = r11
            r31 = r25
            r11 = r41
            r7 = r43
            java.lang.String r1 = r12.override
            if (r1 == 0) goto L_0x08c6
            java.lang.String r2 = "hebr"
            int r1 = r1.compareTo(r2)
            if (r1 == 0) goto L_0x08bf
            java.lang.String r1 = r12.override
            java.lang.String r2 = "y=hebr"
            int r1 = r1.indexOf(r2)
            if (r1 < 0) goto L_0x08c6
        L_0x08bf:
            r1 = 1000(0x3e8, float:1.401E-42)
            if (r6 >= r1) goto L_0x08c6
            int r6 = r6 + 5000
            goto L_0x08f3
        L_0x08c6:
            if (r14 != r0) goto L_0x08f3
            int r1 = r10.getIndex()
            int r1 = countDigits(r13, r15, r1)
            if (r1 != r0) goto L_0x08f3
            boolean r1 = r41.haveDefaultCentury()
            if (r1 == 0) goto L_0x08f3
            int r1 = r33.getDefaultCenturyStartYear()
            r2 = 100
            int r1 = r1 % r2
            if (r6 != r1) goto L_0x08e3
            r3 = 1
            goto L_0x08e4
        L_0x08e3:
            r3 = 0
        L_0x08e4:
            r4 = 0
            r40[r4] = r3
            int r3 = r33.getDefaultCenturyStartYear()
            int r3 = r3 / r2
            int r3 = r3 * r2
            if (r6 >= r1) goto L_0x08f0
            goto L_0x08f1
        L_0x08f0:
            r2 = 0
        L_0x08f1:
            int r3 = r3 + r2
            int r6 = r6 + r3
        L_0x08f3:
            r8 = r31
            r11.set(r8, r6)
            boolean r1 = DelayedHebrewMonthCheck
            if (r1 == 0) goto L_0x0909
            boolean r1 = com.ibm.icu.util.HebrewCalendar.isLeapYear(r6)
            if (r1 != 0) goto L_0x0906
            r1 = 1
            r11.add(r0, r1)
        L_0x0906:
            r0 = 0
            DelayedHebrewMonthCheck = r0
        L_0x0909:
            int r0 = r10.getIndex()
            return r0
        L_0x090e:
            r35 = r5
            r15 = r7
            r10 = r8
            r9 = r11
            r8 = r25
            r0 = 0
            r1 = 4
            r11 = r41
            r7 = r43
            if (r19 == 0) goto L_0x0925
            r11.set(r0, r6)
            int r0 = r10.getIndex()
            return r0
        L_0x0925:
            r16 = 0
            r0 = 5
            if (r14 != r0) goto L_0x093e
            r3 = 0
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r4 = r0.narrowEras
            r5 = 0
            r0 = r33
            r1 = r34
            r2 = r15
            r18 = r6
            r6 = r41
            int r0 = r0.matchString(r1, r2, r3, r4, r5, r6)
            goto L_0x0965
        L_0x093e:
            r18 = r6
            if (r14 != r1) goto L_0x0954
            r3 = 0
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r4 = r0.eraNames
            r5 = 0
            r0 = r33
            r1 = r34
            r2 = r15
            r6 = r41
            int r0 = r0.matchString(r1, r2, r3, r4, r5, r6)
            goto L_0x0965
        L_0x0954:
            r3 = 0
            com.ibm.icu.text.DateFormatSymbols r0 = r12.formatData
            java.lang.String[] r4 = r0.eras
            r5 = 0
            r0 = r33
            r1 = r34
            r2 = r15
            r6 = r41
            int r0 = r0.matchString(r1, r2, r3, r4, r5, r6)
        L_0x0965:
            int r1 = ~r15
            if (r0 != r1) goto L_0x096a
            r0 = -32000(0xffffffffffff8300, float:NaN)
        L_0x096a:
            return r0
        L_0x096b:
            r0 = r33
            r1 = r34
            r2 = r37
            r3 = r10
            r4 = r39
            r5 = r35
            java.lang.Number r0 = r0.parseInt(r1, r2, r3, r4, r5)
            r2 = r35
            r3 = r0
            r1 = r11
            r0 = r39
            goto L_0x098a
        L_0x0981:
            r2 = r35
            r0 = r39
            r1 = r11
            java.lang.Number r3 = r12.parseInt(r13, r10, r0, r2)
        L_0x098a:
            if (r3 == 0) goto L_0x09a4
            r4 = 34
            if (r9 == r4) goto L_0x0998
            int r4 = r3.intValue()
            r1.set(r8, r4)
            goto L_0x099f
        L_0x0998:
            int r4 = r3.intValue()
            r1.setRelatedYear(r4)
        L_0x099f:
            int r4 = r10.getIndex()
            return r4
        L_0x09a4:
            int r4 = ~r15
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.SimpleDateFormat.subParse(java.lang.String, int, char, int, boolean, boolean, boolean[], com.ibm.icu.util.Calendar, com.ibm.icu.text.MessageFormat, com.ibm.icu.util.Output, com.ibm.icu.util.Output):int");
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
        if (maxDigits <= 0 || (nDigits = pos.getIndex() - oldPos) <= maxDigits) {
            return number;
        }
        double val = number.doubleValue();
        for (int nDigits2 = nDigits - maxDigits; nDigits2 > 0; nDigits2--) {
            val /= 10.0d;
        }
        pos.setIndex(oldPos + maxDigits);
        return Integer.valueOf((int) val);
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
        if (!inQuote) {
            return result.toString();
        }
        throw new IllegalArgumentException("Unfinished quote in pattern");
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
        setLocale((ULocale) null, (ULocale) null);
        this.patternItems = null;
    }

    public void applyLocalizedPattern(String pat) {
        this.pattern = translatePattern(pat, this.formatData.localPatternChars, "GyMdkHmsSEDFwWahKzYeugAZvcLQqVUOXxrbB");
        setLocale((ULocale) null, (ULocale) null);
    }

    public DateFormatSymbols getDateFormatSymbols() {
        return (DateFormatSymbols) this.formatData.clone();
    }

    public void setDateFormatSymbols(DateFormatSymbols newFormatSymbols) {
        this.formatData = (DateFormatSymbols) newFormatSymbols.clone();
    }

    /* access modifiers changed from: protected */
    public DateFormatSymbols getSymbols() {
        return this.formatData;
    }

    public TimeZoneFormat getTimeZoneFormat() {
        return tzFormat().freeze();
    }

    public void setTimeZoneFormat(TimeZoneFormat tzfmt) {
        if (tzfmt.isFrozen()) {
            this.tzFormat = tzfmt;
        } else {
            this.tzFormat = tzfmt.cloneAsThawed().freeze();
        }
    }

    public Object clone() {
        SimpleDateFormat other = (SimpleDateFormat) super.clone();
        other.formatData = (DateFormatSymbols) this.formatData.clone();
        if (this.decimalBuf != null) {
            other.decimalBuf = new char[10];
        }
        return other;
    }

    public int hashCode() {
        return this.pattern.hashCode();
    }

    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        SimpleDateFormat that = (SimpleDateFormat) obj;
        if (!this.pattern.equals(that.pattern) || !this.formatData.equals(that.formatData)) {
            return false;
        }
        return true;
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
        ULocale locale2 = getLocale(ULocale.VALID_LOCALE);
        this.locale = locale2;
        if (locale2 == null) {
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
                if (context.value() == capitalizationSettingValue) {
                    setContext(context);
                    break;
                }
                i++;
            }
        }
        if (!getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_PARTIAL_MATCH)) {
            setBooleanAttribute(DateFormat.BooleanAttribute.PARSE_PARTIAL_LITERAL_MATCH, false);
        }
        parsePattern();
    }

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
        ArrayList arrayList = new ArrayList();
        format(cal, getContext(DisplayContext.Type.CAPITALIZATION), toAppendTo, pos, arrayList);
        AttributedString as = new AttributedString(toAppendTo.toString());
        for (int i = 0; i < arrayList.size(); i++) {
            FieldPosition fp = (FieldPosition) arrayList.get(i);
            Format.Field attribute = fp.getFieldAttribute();
            as.addAttribute(attribute, attribute, fp.getBeginIndex(), fp.getEndIndex());
        }
        return as.getIterator();
    }

    /* access modifiers changed from: package-private */
    public ULocale getLocale() {
        return this.locale;
    }

    /* access modifiers changed from: package-private */
    public boolean isFieldUnitIgnored(int field) {
        return isFieldUnitIgnored(this.pattern, field);
    }

    static boolean isFieldUnitIgnored(String pattern2, int field) {
        int fieldLevel = CALENDAR_FIELD_TO_LEVEL[field];
        boolean inQuote = false;
        char prevCh = 0;
        int count = 0;
        int i = 0;
        while (true) {
            boolean z = false;
            if (i >= pattern2.length()) {
                return count <= 0 || fieldLevel > getLevelFromChar(prevCh);
            }
            char ch = pattern2.charAt(i);
            if (ch != prevCh && count > 0) {
                if (fieldLevel <= getLevelFromChar(prevCh)) {
                    return false;
                }
                count = 0;
            }
            if (ch == '\'') {
                if (i + 1 >= pattern2.length() || pattern2.charAt(i + 1) != '\'') {
                    if (!inQuote) {
                        z = true;
                    }
                    inQuote = z;
                } else {
                    i++;
                }
            } else if (!inQuote && isSyntaxChar(ch)) {
                prevCh = ch;
                count++;
            }
            i++;
        }
    }

    @Deprecated
    public final StringBuffer intervalFormatByAlgorithm(Calendar fromCalendar, Calendar toCalendar, StringBuffer appendTo, FieldPosition pos) throws IllegalArgumentException {
        int diffBegin;
        int highestLevel;
        int i;
        int diffEnd;
        String str;
        Calendar calendar = fromCalendar;
        Calendar calendar2 = toCalendar;
        StringBuffer stringBuffer = appendTo;
        FieldPosition fieldPosition = pos;
        if (fromCalendar.isEquivalentTo(toCalendar)) {
            Object[] items = getPatternItems();
            int diffBegin2 = -1;
            int diffEnd2 = -1;
            int i2 = 0;
            while (true) {
                try {
                    if (i2 >= items.length) {
                        break;
                    } else if (diffCalFieldValue(calendar, calendar2, items, i2)) {
                        diffBegin2 = i2;
                        break;
                    } else {
                        i2++;
                    }
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(e.toString());
                }
            }
            if (diffBegin2 == -1) {
                return format(calendar, stringBuffer, fieldPosition);
            }
            int i3 = items.length - 1;
            while (true) {
                if (i3 < diffBegin2) {
                    break;
                } else if (diffCalFieldValue(calendar, calendar2, items, i3)) {
                    diffEnd2 = i3;
                    break;
                } else {
                    i3--;
                }
            }
            String str2 = " – ";
            if (diffBegin2 == 0 && diffEnd2 == items.length - 1) {
                format(calendar, stringBuffer, fieldPosition);
                stringBuffer.append(str2);
                format(calendar2, stringBuffer, fieldPosition);
                return stringBuffer;
            }
            int highestLevel2 = 1000;
            for (int i4 = diffBegin2; i4 <= diffEnd2; i4++) {
                if (!(items[i4] instanceof String)) {
                    char ch = ((PatternItem) items[i4]).type;
                    int patternCharIndex = getIndexFromChar(ch);
                    if (patternCharIndex == -1) {
                        throw new IllegalArgumentException("Illegal pattern character '" + ch + "' in \"" + this.pattern + '\"');
                    } else if (patternCharIndex < highestLevel2) {
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
                    if (lowerLevel(items, i5, highestLevel2)) {
                        diffBegin = i5;
                        break;
                    }
                    i5++;
                } catch (IllegalArgumentException e2) {
                    e = e2;
                    int i6 = highestLevel2;
                    throw new IllegalArgumentException(e.toString());
                }
            }
            try {
                int i7 = items.length - 1;
                while (true) {
                    if (i7 <= diffEnd2) {
                        i7 = diffEnd2;
                        break;
                    }
                    try {
                        if (lowerLevel(items, i7, highestLevel2)) {
                            int diffEnd3 = i7;
                            break;
                        }
                        i7--;
                    } catch (IllegalArgumentException e3) {
                        e = e3;
                        int i8 = highestLevel2;
                        int i9 = diffBegin;
                        throw new IllegalArgumentException(e.toString());
                    }
                }
                if (diffBegin == 0 && i7 == items.length - 1) {
                    format(calendar, stringBuffer, fieldPosition);
                    stringBuffer.append(str2);
                    format(calendar2, stringBuffer, fieldPosition);
                    return stringBuffer;
                }
                fieldPosition.setBeginIndex(0);
                fieldPosition.setEndIndex(0);
                DisplayContext capSetting = getContext(DisplayContext.Type.CAPITALIZATION);
                int i10 = 0;
                while (i10 <= i7) {
                    if (items[i10] instanceof String) {
                        stringBuffer.append((String) items[i10]);
                        diffEnd = i7;
                        i = i10;
                        highestLevel = highestLevel2;
                        str = str2;
                    } else {
                        PatternItem item = (PatternItem) items[i10];
                        if (this.useFastFormat) {
                            diffEnd = i7;
                            PatternItem patternItem = item;
                            i = i10;
                            highestLevel = highestLevel2;
                            str = str2;
                            subFormat(appendTo, item.type, item.length, appendTo.length(), i10, capSetting, pos, fromCalendar);
                        } else {
                            diffEnd = i7;
                            PatternItem item2 = item;
                            i = i10;
                            highestLevel = highestLevel2;
                            str = str2;
                            stringBuffer.append(subFormat(item2.type, item2.length, appendTo.length(), i, capSetting, pos, fromCalendar));
                        }
                    }
                    i10 = i + 1;
                    str2 = str;
                    i7 = diffEnd;
                    highestLevel2 = highestLevel;
                    Calendar calendar3 = fromCalendar;
                }
                int diffEnd4 = i7;
                int i11 = i10;
                int i12 = highestLevel2;
                stringBuffer.append(str2);
                for (int i13 = diffBegin; i13 < items.length; i13++) {
                    if (items[i13] instanceof String) {
                        stringBuffer.append((String) items[i13]);
                    } else {
                        PatternItem item3 = (PatternItem) items[i13];
                        if (this.useFastFormat) {
                            subFormat(appendTo, item3.type, item3.length, appendTo.length(), i13, capSetting, pos, toCalendar);
                        } else {
                            stringBuffer.append(subFormat(item3.type, item3.length, appendTo.length(), i13, capSetting, pos, toCalendar));
                        }
                    }
                }
                return stringBuffer;
            } catch (IllegalArgumentException e4) {
                e = e4;
                int i14 = highestLevel2;
                int i15 = diffBegin;
                throw new IllegalArgumentException(e.toString());
            }
        } else {
            throw new IllegalArgumentException("can not format on two different calendars");
        }
    }

    private boolean diffCalFieldValue(Calendar fromCalendar, Calendar toCalendar, Object[] items, int i) throws IllegalArgumentException {
        if (items[i] instanceof String) {
            return false;
        }
        char ch = items[i].type;
        int patternCharIndex = getIndexFromChar(ch);
        if (patternCharIndex != -1) {
            int field = PATTERN_INDEX_TO_CALENDAR_FIELD[patternCharIndex];
            if (field < 0 || fromCalendar.get(field) == toCalendar.get(field)) {
                return false;
            }
            return true;
        }
        throw new IllegalArgumentException("Illegal pattern character '" + ch + "' in \"" + this.pattern + '\"');
    }

    private boolean lowerLevel(Object[] items, int i, int level) throws IllegalArgumentException {
        if (items[i] instanceof String) {
            return false;
        }
        char ch = items[i].type;
        int patternCharIndex = getLevelFromChar(ch);
        if (patternCharIndex == -1) {
            throw new IllegalArgumentException("Illegal pattern character '" + ch + "' in \"" + this.pattern + '\"');
        } else if (patternCharIndex >= level) {
            return true;
        } else {
            return false;
        }
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
        int i = 0;
        while (i < fields.length()) {
            char field = fields.charAt(i);
            if ("GyMdkHmsSEDFwWahKzYeugAZvcLQqVUOXxrbB".indexOf(field) != -1) {
                this.overrideMap.put(Character.valueOf(field), nsName);
                this.numberFormatters.put(nsName, overrideNF);
                i++;
            } else {
                throw new IllegalArgumentException("Illegal field character '" + field + "' in setNumberFormat.");
            }
        }
        this.useLocalZeroPaddingNumberFormat = false;
    }

    public NumberFormat getNumberFormat(char field) {
        Character ovrField = Character.valueOf(field);
        HashMap<Character, String> hashMap = this.overrideMap;
        if (hashMap == null || !hashMap.containsKey(ovrField)) {
            return this.numberFormat;
        }
        return this.numberFormatters.get(this.overrideMap.get(ovrField).toString());
    }

    private void initNumberFormatters(ULocale loc) {
        this.numberFormatters = new HashMap<>();
        this.overrideMap = new HashMap<>();
        processOverrideString(loc, this.override);
    }

    private void processOverrideString(ULocale loc, String str) {
        int end;
        boolean fullOverride;
        String nsName;
        if (str != null && str.length() != 0) {
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
                    this.overrideMap.put(Character.valueOf(currentString.charAt(0)), nsName);
                    fullOverride = false;
                }
                NumberFormat nf = NumberFormat.createInstance(new ULocale(loc.getBaseName() + "@numbers=" + nsName), 0);
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
