package com.ibm.icu.text;

import com.ibm.icu.impl.ICUCache;
import com.ibm.icu.impl.ICUResourceBundle;
import com.ibm.icu.impl.PatternTokenizer;
import com.ibm.icu.impl.SimpleCache;
import com.ibm.icu.impl.SimpleFormatterImpl;
import com.ibm.icu.impl.UResource;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.Freezable;
import com.ibm.icu.util.ICUCloneNotSupportedException;
import com.ibm.icu.util.ULocale;
import com.ibm.icu.util.UResourceBundle;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class DateTimePatternGenerator implements Freezable<DateTimePatternGenerator>, Cloneable {
    private static final DisplayWidth APPENDITEM_WIDTH;
    private static final int APPENDITEM_WIDTH_INT;
    /* access modifiers changed from: private */
    public static final String[] CANONICAL_ITEMS;
    private static final Set<String> CANONICAL_SET;
    private static final String[] CLDR_FIELD_APPEND = {"Era", "Year", "Quarter", "Month", "Week", "*", "Day-Of-Week", "Day", "*", "*", "*", "Hour", "Minute", "Second", "*", "Timezone"};
    private static final String[] CLDR_FIELD_NAME = {"era", "year", "quarter", "month", "week", "weekOfMonth", "weekday", "day", "dayOfYear", "weekdayOfMonth", "dayperiod", "hour", "minute", "second", "*", "zone"};
    /* access modifiers changed from: private */
    public static final DisplayWidth[] CLDR_FIELD_WIDTH = DisplayWidth.values();
    private static final int DATE_MASK = 1023;
    public static final int DAY = 7;
    public static final int DAYPERIOD = 10;
    public static final int DAY_OF_WEEK_IN_MONTH = 9;
    public static final int DAY_OF_YEAR = 8;
    private static final boolean DEBUG = false;
    private static final int DELTA = 16;
    private static ICUCache<String, DateTimePatternGenerator> DTPNG_CACHE = new SimpleCache();
    public static final int ERA = 0;
    private static final int EXTRA_FIELD = 65536;
    private static final String[] FIELD_NAME = {"Era", "Year", "Quarter", "Month", "Week_in_Year", "Week_in_Month", "Weekday", "Day", "Day_Of_Year", "Day_of_Week_in_Month", "Dayperiod", "Hour", "Minute", "Second", "Fractional_Second", "Zone"};
    private static final int FRACTIONAL_MASK = 16384;
    public static final int FRACTIONAL_SECOND = 14;
    public static final int HOUR = 11;
    private static final String[] LAST_RESORT_ALLOWED_HOUR_FORMAT = {DateFormat.HOUR24};
    static final Map<String, String[]> LOCALE_TO_ALLOWED_HOUR;
    private static final int LONG = -260;
    public static final int MATCH_ALL_FIELDS_LENGTH = 65535;
    public static final int MATCH_HOUR_FIELD_LENGTH = 2048;
    @Deprecated
    public static final int MATCH_MINUTE_FIELD_LENGTH = 4096;
    public static final int MATCH_NO_OPTIONS = 0;
    @Deprecated
    public static final int MATCH_SECOND_FIELD_LENGTH = 8192;
    public static final int MINUTE = 12;
    private static final int MISSING_FIELD = 4096;
    public static final int MONTH = 3;
    private static final int NARROW = -257;
    private static final int NONE = 0;
    private static final int NUMERIC = 256;
    public static final int QUARTER = 2;
    public static final int SECOND = 13;
    private static final int SECOND_AND_FRACTIONAL_MASK = 24576;
    private static final int SHORT = -259;
    private static final int SHORTER = -258;
    private static final int TIME_MASK = 64512;
    @Deprecated
    public static final int TYPE_LIMIT = 16;
    public static final int WEEKDAY = 6;
    public static final int WEEK_OF_MONTH = 5;
    public static final int WEEK_OF_YEAR = 4;
    public static final int YEAR = 1;
    public static final int ZONE = 15;
    /* access modifiers changed from: private */
    public static final int[][] types = {new int[]{71, 0, SHORT, 1, 3}, new int[]{71, 0, LONG, 4}, new int[]{71, 0, NARROW, 5}, new int[]{121, 1, 256, 1, 20}, new int[]{89, 1, 272, 1, 20}, new int[]{117, 1, 288, 1, 20}, new int[]{114, 1, 304, 1, 20}, new int[]{85, 1, SHORT, 1, 3}, new int[]{85, 1, LONG, 4}, new int[]{85, 1, NARROW, 5}, new int[]{81, 2, 256, 1, 2}, new int[]{81, 2, SHORT, 3}, new int[]{81, 2, LONG, 4}, new int[]{81, 2, NARROW, 5}, new int[]{113, 2, 272, 1, 2}, new int[]{113, 2, -275, 3}, new int[]{113, 2, -276, 4}, new int[]{113, 2, -273, 5}, new int[]{77, 3, 256, 1, 2}, new int[]{77, 3, SHORT, 3}, new int[]{77, 3, LONG, 4}, new int[]{77, 3, NARROW, 5}, new int[]{76, 3, 272, 1, 2}, new int[]{76, 3, -275, 3}, new int[]{76, 3, -276, 4}, new int[]{76, 3, -273, 5}, new int[]{108, 3, 272, 1, 1}, new int[]{119, 4, 256, 1, 2}, new int[]{87, 5, 256, 1}, new int[]{69, 6, SHORT, 1, 3}, new int[]{69, 6, LONG, 4}, new int[]{69, 6, NARROW, 5}, new int[]{69, 6, SHORTER, 6}, new int[]{99, 6, 288, 1, 2}, new int[]{99, 6, -291, 3}, new int[]{99, 6, -292, 4}, new int[]{99, 6, -289, 5}, new int[]{99, 6, -290, 6}, new int[]{101, 6, 272, 1, 2}, new int[]{101, 6, -275, 3}, new int[]{101, 6, -276, 4}, new int[]{101, 6, -273, 5}, new int[]{101, 6, -274, 6}, new int[]{100, 7, 256, 1, 2}, new int[]{103, 7, 272, 1, 20}, new int[]{68, 8, 256, 1, 3}, new int[]{70, 9, 256, 1}, new int[]{97, 10, SHORT, 1, 3}, new int[]{97, 10, LONG, 4}, new int[]{97, 10, NARROW, 5}, new int[]{98, 10, -275, 1, 3}, new int[]{98, 10, -276, 4}, new int[]{98, 10, -273, 5}, new int[]{66, 10, -307, 1, 3}, new int[]{66, 10, -308, 4}, new int[]{66, 10, -305, 5}, new int[]{72, 11, 416, 1, 2}, new int[]{107, 11, 432, 1, 2}, new int[]{104, 11, 256, 1, 2}, new int[]{75, 11, 272, 1, 2}, new int[]{109, 12, 256, 1, 2}, new int[]{115, 13, 256, 1, 2}, new int[]{65, 13, 272, 1, 1000}, new int[]{83, 14, 256, 1, 1000}, new int[]{118, 15, -291, 1}, new int[]{118, 15, -292, 4}, new int[]{122, 15, SHORT, 1, 3}, new int[]{122, 15, LONG, 4}, new int[]{90, 15, -273, 1, 3}, new int[]{90, 15, -276, 4}, new int[]{90, 15, -275, 5}, new int[]{79, 15, -275, 1}, new int[]{79, 15, -276, 4}, new int[]{86, 15, -275, 1}, new int[]{86, 15, -276, 2}, new int[]{86, 15, -277, 3}, new int[]{86, 15, -278, 4}, new int[]{88, 15, -273, 1}, new int[]{88, 15, -275, 2}, new int[]{88, 15, -276, 4}, new int[]{120, 15, -273, 1}, new int[]{120, 15, -275, 2}, new int[]{120, 15, -276, 4}};
    private transient DistanceInfo _distanceInfo;
    private String[] allowedHourFormats;
    private String[] appendItemFormats = new String[16];
    private TreeMap<String, PatternWithSkeletonFlag> basePattern_pattern = new TreeMap<>();
    private Set<String> cldrAvailableFormatKeys;
    private transient DateTimeMatcher current;
    private String dateTimeFormat = "{1} {0}";
    private String decimal = "?";
    private char defaultHourFormatChar;
    private String[][] fieldDisplayNames;
    private transient FormatParser fp;
    private volatile boolean frozen;
    private TreeMap<DateTimeMatcher, PatternWithSkeletonFlag> skeleton2pattern = new TreeMap<>();

    private enum DTPGflags {
        FIX_FRACTIONAL_SECONDS,
        SKELETON_USES_CAP_J
    }

    public static final class PatternInfo {
        public static final int BASE_CONFLICT = 1;
        public static final int CONFLICT = 2;
        public static final int OK = 0;
        public String conflictingPattern;
        public int status;
    }

    public static DateTimePatternGenerator getEmptyInstance() {
        DateTimePatternGenerator instance = new DateTimePatternGenerator();
        instance.addCanonicalItems();
        instance.fillInMissing();
        return instance;
    }

    protected DateTimePatternGenerator() {
        int[] iArr = new int[2];
        iArr[1] = DisplayWidth.COUNT;
        iArr[0] = 16;
        this.fieldDisplayNames = (String[][]) Array.newInstance(String.class, iArr);
        this.defaultHourFormatChar = 'H';
        this.frozen = false;
        this.current = new DateTimeMatcher();
        this.fp = new FormatParser();
        this._distanceInfo = new DistanceInfo();
        this.cldrAvailableFormatKeys = new HashSet(20);
    }

    public static DateTimePatternGenerator getInstance() {
        return getInstance(ULocale.getDefault(ULocale.Category.FORMAT));
    }

    public static DateTimePatternGenerator getInstance(ULocale uLocale) {
        return getFrozenInstance(uLocale).cloneAsThawed();
    }

    public static DateTimePatternGenerator getInstance(Locale locale) {
        return getInstance(ULocale.forLocale(locale));
    }

    @Deprecated
    public static DateTimePatternGenerator getFrozenInstance(ULocale uLocale) {
        String localeKey = uLocale.toString();
        DateTimePatternGenerator result = (DateTimePatternGenerator) DTPNG_CACHE.get(localeKey);
        if (result != null) {
            return result;
        }
        DateTimePatternGenerator result2 = new DateTimePatternGenerator();
        result2.initData(uLocale);
        result2.freeze();
        DTPNG_CACHE.put(localeKey, result2);
        return result2;
    }

    private void initData(ULocale uLocale) {
        PatternInfo returnInfo = new PatternInfo();
        addCanonicalItems();
        addICUPatterns(returnInfo, uLocale);
        addCLDRData(returnInfo, uLocale);
        setDateTimeFromCalendar(uLocale);
        setDecimalSymbols(uLocale);
        getAllowedHourFormats(uLocale);
        fillInMissing();
    }

    private void addICUPatterns(PatternInfo returnInfo, ULocale uLocale) {
        for (int i = 0; i <= 3; i++) {
            addPattern(((SimpleDateFormat) DateFormat.getDateInstance(i, uLocale)).toPattern(), false, returnInfo);
            SimpleDateFormat df = (SimpleDateFormat) DateFormat.getTimeInstance(i, uLocale);
            addPattern(df.toPattern(), false, returnInfo);
            if (i == 3) {
                consumeShortTimePattern(df.toPattern(), returnInfo);
            }
        }
    }

    private String getCalendarTypeToUse(ULocale uLocale) {
        String calendarTypeToUse = uLocale.getKeywordValue("calendar");
        if (calendarTypeToUse == null) {
            calendarTypeToUse = Calendar.getKeywordValuesForLocale("calendar", uLocale, true)[0];
        }
        if (calendarTypeToUse == null) {
            return "gregorian";
        }
        return calendarTypeToUse;
    }

    private void consumeShortTimePattern(String shortTimePattern, PatternInfo returnInfo) {
        FormatParser fp2 = new FormatParser();
        fp2.set(shortTimePattern);
        List<Object> items = fp2.getItems();
        int idx = 0;
        while (true) {
            if (idx >= items.size()) {
                break;
            }
            Object item = items.get(idx);
            if (item instanceof VariableField) {
                VariableField fld = (VariableField) item;
                if (fld.getType() == 11) {
                    this.defaultHourFormatChar = fld.toString().charAt(0);
                    break;
                }
            }
            idx++;
        }
        hackTimes(returnInfo, shortTimePattern);
    }

    private class AppendItemFormatsSink extends UResource.Sink {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        static {
            Class<DateTimePatternGenerator> cls = DateTimePatternGenerator.class;
        }

        private AppendItemFormatsSink() {
        }

        public void put(UResource.Key key, UResource.Value value, boolean noFallback) {
            UResource.Table itemsTable = value.getTable();
            int i = 0;
            while (itemsTable.getKeyAndValue(i, key, value)) {
                int field = DateTimePatternGenerator.getAppendFormatNumber(key);
                if (field != -1) {
                    if (DateTimePatternGenerator.this.getAppendItemFormat(field) == null) {
                        DateTimePatternGenerator.this.setAppendItemFormat(field, value.toString());
                    }
                    i++;
                } else {
                    throw new AssertionError();
                }
            }
        }
    }

    private class AppendItemNamesSink extends UResource.Sink {
        private AppendItemNamesSink() {
        }

        public void put(UResource.Key key, UResource.Value value, boolean noFallback) {
            int fieldAndWidth;
            UResource.Table itemsTable = value.getTable();
            for (int i = 0; itemsTable.getKeyAndValue(i, key, value); i++) {
                if (value.getType() == 2 && (fieldAndWidth = DateTimePatternGenerator.getCLDRFieldAndWidthNumber(key)) != -1) {
                    int field = fieldAndWidth / DisplayWidth.COUNT;
                    DisplayWidth width = DateTimePatternGenerator.CLDR_FIELD_WIDTH[fieldAndWidth % DisplayWidth.COUNT];
                    UResource.Table detailsTable = value.getTable();
                    int j = 0;
                    while (true) {
                        if (!detailsTable.getKeyAndValue(j, key, value)) {
                            break;
                        } else if (!key.contentEquals("dn")) {
                            j++;
                        } else if (DateTimePatternGenerator.this.getFieldDisplayName(field, width) == null) {
                            DateTimePatternGenerator.this.setFieldDisplayName(field, width, value.toString());
                        }
                    }
                }
            }
        }
    }

    private void fillInMissing() {
        for (int i = 0; i < 16; i++) {
            if (getAppendItemFormat(i) == null) {
                setAppendItemFormat(i, "{0} ├{2}: {1}┤");
            }
            if (getFieldDisplayName(i, DisplayWidth.WIDE) == null) {
                setFieldDisplayName(i, DisplayWidth.WIDE, "F" + i);
            }
            if (getFieldDisplayName(i, DisplayWidth.ABBREVIATED) == null) {
                setFieldDisplayName(i, DisplayWidth.ABBREVIATED, getFieldDisplayName(i, DisplayWidth.WIDE));
            }
            if (getFieldDisplayName(i, DisplayWidth.NARROW) == null) {
                setFieldDisplayName(i, DisplayWidth.NARROW, getFieldDisplayName(i, DisplayWidth.ABBREVIATED));
            }
        }
    }

    private class AvailableFormatsSink extends UResource.Sink {
        PatternInfo returnInfo;

        public AvailableFormatsSink(PatternInfo returnInfo2) {
            this.returnInfo = returnInfo2;
        }

        public void put(UResource.Key key, UResource.Value value, boolean isRoot) {
            UResource.Table formatsTable = value.getTable();
            for (int i = 0; formatsTable.getKeyAndValue(i, key, value); i++) {
                String formatKey = key.toString();
                if (!DateTimePatternGenerator.this.isAvailableFormatSet(formatKey)) {
                    DateTimePatternGenerator.this.setAvailableFormat(formatKey);
                    DateTimePatternGenerator.this.addPatternWithSkeleton(value.toString(), formatKey, !isRoot, this.returnInfo);
                }
            }
        }
    }

    private void addCLDRData(PatternInfo returnInfo, ULocale uLocale) {
        ICUResourceBundle rb = UResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudt63b", uLocale);
        String calendarTypeToUse = getCalendarTypeToUse(uLocale);
        try {
            rb.getAllItemsWithFallback("calendar/" + calendarTypeToUse + "/appendItems", new AppendItemFormatsSink());
        } catch (MissingResourceException e) {
        }
        try {
            rb.getAllItemsWithFallback("fields", new AppendItemNamesSink());
        } catch (MissingResourceException e2) {
        }
        try {
            rb.getAllItemsWithFallback("calendar/" + calendarTypeToUse + "/availableFormats", new AvailableFormatsSink(returnInfo));
        } catch (MissingResourceException e3) {
        }
    }

    private void setDateTimeFromCalendar(ULocale uLocale) {
        setDateTimeFormat(Calendar.getDateTimePattern(Calendar.getInstance(uLocale), uLocale, 2));
    }

    private void setDecimalSymbols(ULocale uLocale) {
        setDecimal(String.valueOf(new DecimalFormatSymbols(uLocale).getDecimalSeparator()));
    }

    static {
        HashMap<String, String[]> temp = new HashMap<>();
        ICUResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudt63b", "supplementalData", ICUResourceBundle.ICU_DATA_CLASS_LOADER).getAllItemsWithFallback("timeData", new DayPeriodAllowedHoursSink(temp));
        LOCALE_TO_ALLOWED_HOUR = Collections.unmodifiableMap(temp);
        DisplayWidth displayWidth = DisplayWidth.WIDE;
        APPENDITEM_WIDTH = displayWidth;
        APPENDITEM_WIDTH_INT = displayWidth.ordinal();
        String[] strArr = {"G", DateFormat.YEAR, "Q", DateFormat.NUM_MONTH, "w", "W", DateFormat.ABBR_WEEKDAY, DateFormat.DAY, "D", "F", "a", DateFormat.HOUR24, DateFormat.MINUTE, DateFormat.SECOND, "S", DateFormat.ABBR_GENERIC_TZ};
        CANONICAL_ITEMS = strArr;
        CANONICAL_SET = new HashSet(Arrays.asList(strArr));
    }

    private void getAllowedHourFormats(ULocale uLocale) {
        ULocale max = ULocale.addLikelySubtags(uLocale);
        String country = max.getCountry();
        if (country.isEmpty()) {
            country = "001";
        }
        Map<String, String[]> map = LOCALE_TO_ALLOWED_HOUR;
        String[] list = map.get(max.getLanguage() + "_" + country);
        if (list == null && (list = map.get(country)) == null) {
            list = LAST_RESORT_ALLOWED_HOUR_FORMAT;
        }
        this.allowedHourFormats = list;
    }

    private static class DayPeriodAllowedHoursSink extends UResource.Sink {
        HashMap<String, String[]> tempMap;

        private DayPeriodAllowedHoursSink(HashMap<String, String[]> tempMap2) {
            this.tempMap = tempMap2;
        }

        public void put(UResource.Key key, UResource.Value value, boolean noFallback) {
            UResource.Table timeData = value.getTable();
            for (int i = 0; timeData.getKeyAndValue(i, key, value); i++) {
                String regionOrLocale = key.toString();
                UResource.Table formatList = value.getTable();
                for (int j = 0; formatList.getKeyAndValue(j, key, value); j++) {
                    if (key.contentEquals("allowed")) {
                        this.tempMap.put(regionOrLocale, value.getStringArrayOrStringAsArray());
                    }
                }
            }
        }
    }

    @Deprecated
    public char getDefaultHourFormatChar() {
        return this.defaultHourFormatChar;
    }

    @Deprecated
    public void setDefaultHourFormatChar(char defaultHourFormatChar2) {
        this.defaultHourFormatChar = defaultHourFormatChar2;
    }

    private void hackTimes(PatternInfo returnInfo, String shortTimePattern) {
        this.fp.set(shortTimePattern);
        StringBuilder mmss = new StringBuilder();
        boolean gotMm = false;
        int i = 0;
        while (true) {
            if (i >= this.fp.items.size()) {
                break;
            }
            Object item = this.fp.items.get(i);
            if (!(item instanceof String)) {
                char ch = item.toString().charAt(0);
                if (ch != 'm') {
                    if (ch != 's') {
                        if (gotMm || ch == 'z' || ch == 'Z' || ch == 'v' || ch == 'V') {
                            break;
                        }
                    } else if (gotMm) {
                        mmss.append(item);
                        addPattern(mmss.toString(), false, returnInfo);
                    }
                } else {
                    gotMm = true;
                    mmss.append(item);
                }
            } else if (gotMm) {
                mmss.append(this.fp.quoteLiteral(item.toString()));
            }
            i++;
        }
        BitSet variables = new BitSet();
        BitSet nuke = new BitSet();
        for (int i2 = 0; i2 < this.fp.items.size(); i2++) {
            Object item2 = this.fp.items.get(i2);
            if (item2 instanceof VariableField) {
                variables.set(i2);
                char ch2 = item2.toString().charAt(0);
                if (ch2 == 's' || ch2 == 'S') {
                    nuke.set(i2);
                    int j = i2 - 1;
                    while (j >= 0 && !variables.get(j)) {
                        nuke.set(i2);
                        j++;
                    }
                }
            }
        }
        addPattern(getFilteredPattern(this.fp, nuke), false, returnInfo);
    }

    private static String getFilteredPattern(FormatParser fp2, BitSet nuke) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < fp2.items.size(); i++) {
            if (!nuke.get(i)) {
                Object item = fp2.items.get(i);
                if (item instanceof String) {
                    result.append(fp2.quoteLiteral(item.toString()));
                } else {
                    result.append(item.toString());
                }
            }
        }
        return result.toString();
    }

    @Deprecated
    public static int getAppendFormatNumber(UResource.Key key) {
        int i = 0;
        while (true) {
            String[] strArr = CLDR_FIELD_APPEND;
            if (i >= strArr.length) {
                return -1;
            }
            if (key.contentEquals(strArr[i])) {
                return i;
            }
            i++;
        }
    }

    @Deprecated
    public static int getAppendFormatNumber(String string) {
        int i = 0;
        while (true) {
            String[] strArr = CLDR_FIELD_APPEND;
            if (i >= strArr.length) {
                return -1;
            }
            if (strArr[i].equals(string)) {
                return i;
            }
            i++;
        }
    }

    /* access modifiers changed from: private */
    public static int getCLDRFieldAndWidthNumber(UResource.Key key) {
        for (int i = 0; i < CLDR_FIELD_NAME.length; i++) {
            for (int j = 0; j < DisplayWidth.COUNT; j++) {
                if (key.contentEquals(CLDR_FIELD_NAME[i].concat(CLDR_FIELD_WIDTH[j].cldrKey()))) {
                    return (DisplayWidth.COUNT * i) + j;
                }
            }
        }
        return -1;
    }

    public String getBestPattern(String skeleton) {
        return getBestPattern(skeleton, (DateTimeMatcher) null, 0);
    }

    public String getBestPattern(String skeleton, int options) {
        return getBestPattern(skeleton, (DateTimeMatcher) null, options);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006a, code lost:
        if (r16 != null) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006c, code lost:
        if (r0 != null) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x006e, code lost:
        return "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0073, code lost:
        if (r0 != null) goto L_0x0076;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0075, code lost:
        return r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0087, code lost:
        return com.ibm.icu.impl.SimpleFormatterImpl.formatRawPattern(getDateTimeFormat(), 2, 2, new java.lang.CharSequence[]{r0, r16});
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getBestPattern(java.lang.String r18, com.ibm.icu.text.DateTimePatternGenerator.DateTimeMatcher r19, int r20) {
        /*
            r17 = this;
            r8 = r17
            java.lang.Class<com.ibm.icu.text.DateTimePatternGenerator$DTPGflags> r0 = com.ibm.icu.text.DateTimePatternGenerator.DTPGflags.class
            java.util.EnumSet r9 = java.util.EnumSet.noneOf(r0)
            r10 = r18
            java.lang.String r11 = r8.mapSkeletonMetacharacters(r10, r9)
            monitor-enter(r17)
            com.ibm.icu.text.DateTimePatternGenerator$DateTimeMatcher r0 = r8.current     // Catch:{ all -> 0x008a }
            com.ibm.icu.text.DateTimePatternGenerator$FormatParser r1 = r8.fp     // Catch:{ all -> 0x008a }
            r12 = 0
            r0.set(r11, r1, r12)     // Catch:{ all -> 0x008a }
            com.ibm.icu.text.DateTimePatternGenerator$DateTimeMatcher r0 = r8.current     // Catch:{ all -> 0x008a }
            r1 = -1
            com.ibm.icu.text.DateTimePatternGenerator$DistanceInfo r2 = r8._distanceInfo     // Catch:{ all -> 0x008a }
            r13 = r19
            com.ibm.icu.text.DateTimePatternGenerator$PatternWithMatcher r0 = r8.getBestRaw(r0, r1, r2, r13)     // Catch:{ all -> 0x0088 }
            com.ibm.icu.text.DateTimePatternGenerator$DistanceInfo r1 = r8._distanceInfo     // Catch:{ all -> 0x0088 }
            int r1 = r1.missingFieldMask     // Catch:{ all -> 0x0088 }
            if (r1 != 0) goto L_0x0038
            com.ibm.icu.text.DateTimePatternGenerator$DistanceInfo r1 = r8._distanceInfo     // Catch:{ all -> 0x0088 }
            int r1 = r1.extraFieldMask     // Catch:{ all -> 0x0088 }
            if (r1 != 0) goto L_0x0038
            com.ibm.icu.text.DateTimePatternGenerator$DateTimeMatcher r1 = r8.current     // Catch:{ all -> 0x0088 }
            r14 = r20
            java.lang.String r1 = r8.adjustFieldTypes(r0, r1, r9, r14)     // Catch:{ all -> 0x0091 }
            monitor-exit(r17)     // Catch:{ all -> 0x0091 }
            return r1
        L_0x0038:
            r14 = r20
            com.ibm.icu.text.DateTimePatternGenerator$DateTimeMatcher r1 = r8.current     // Catch:{ all -> 0x0091 }
            int r1 = r1.getFieldMask()     // Catch:{ all -> 0x0091 }
            r15 = r1
            com.ibm.icu.text.DateTimePatternGenerator$DateTimeMatcher r2 = r8.current     // Catch:{ all -> 0x0091 }
            r3 = r15 & 1023(0x3ff, float:1.434E-42)
            com.ibm.icu.text.DateTimePatternGenerator$DistanceInfo r4 = r8._distanceInfo     // Catch:{ all -> 0x0091 }
            r1 = r17
            r5 = r19
            r6 = r9
            r7 = r20
            java.lang.String r1 = r1.getBestAppending(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x0091 }
            r16 = r1
            com.ibm.icu.text.DateTimePatternGenerator$DateTimeMatcher r2 = r8.current     // Catch:{ all -> 0x0091 }
            r1 = 64512(0xfc00, float:9.04E-41)
            r3 = r15 & r1
            com.ibm.icu.text.DateTimePatternGenerator$DistanceInfo r4 = r8._distanceInfo     // Catch:{ all -> 0x0091 }
            r1 = r17
            r5 = r19
            r6 = r9
            r7 = r20
            java.lang.String r1 = r1.getBestAppending(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x0091 }
            r0 = r1
            monitor-exit(r17)     // Catch:{ all -> 0x0091 }
            if (r16 != 0) goto L_0x0073
            if (r0 != 0) goto L_0x0071
            java.lang.String r1 = ""
            goto L_0x0072
        L_0x0071:
            r1 = r0
        L_0x0072:
            return r1
        L_0x0073:
            if (r0 != 0) goto L_0x0076
            return r16
        L_0x0076:
            java.lang.String r1 = r17.getDateTimeFormat()
            r2 = 2
            java.lang.CharSequence[] r3 = new java.lang.CharSequence[r2]
            r3[r12] = r0
            r4 = 1
            r3[r4] = r16
            java.lang.String r1 = com.ibm.icu.impl.SimpleFormatterImpl.formatRawPattern(r1, r2, r2, r3)
            return r1
        L_0x0088:
            r0 = move-exception
            goto L_0x008d
        L_0x008a:
            r0 = move-exception
            r13 = r19
        L_0x008d:
            r14 = r20
        L_0x008f:
            monitor-exit(r17)     // Catch:{ all -> 0x0091 }
            throw r0
        L_0x0091:
            r0 = move-exception
            goto L_0x008f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.DateTimePatternGenerator.getBestPattern(java.lang.String, com.ibm.icu.text.DateTimePatternGenerator$DateTimeMatcher, int):java.lang.String");
    }

    private String mapSkeletonMetacharacters(String skeleton, EnumSet<DTPGflags> flags) {
        char hourChar;
        String str = skeleton;
        StringBuilder skeletonCopy = new StringBuilder();
        boolean inQuoted = false;
        int patPos = 0;
        while (patPos < skeleton.length()) {
            char patChr = str.charAt(patPos);
            boolean z = false;
            if (patChr == '\'') {
                if (!inQuoted) {
                    z = true;
                }
                inQuoted = z;
                EnumSet<DTPGflags> enumSet = flags;
            } else if (!inQuoted) {
                if (patChr == 'j') {
                    EnumSet<DTPGflags> enumSet2 = flags;
                } else if (patChr == 'C') {
                    EnumSet<DTPGflags> enumSet3 = flags;
                } else if (patChr == 'J') {
                    skeletonCopy.append('H');
                    flags.add(DTPGflags.SKELETON_USES_CAP_J);
                } else {
                    EnumSet<DTPGflags> enumSet4 = flags;
                    skeletonCopy.append(patChr);
                }
                int extraLen = 0;
                while (patPos + 1 < skeleton.length() && str.charAt(patPos + 1) == patChr) {
                    extraLen++;
                    patPos++;
                }
                int hourLen = (extraLen & 1) + 1;
                int dayPeriodLen = extraLen < 2 ? 1 : (extraLen >> 1) + 3;
                char dayPeriodChar = 'a';
                if (patChr == 'j') {
                    hourChar = this.defaultHourFormatChar;
                } else {
                    String preferred = this.allowedHourFormats[0];
                    hourChar = preferred.charAt(0);
                    char last = preferred.charAt(preferred.length() - 1);
                    if (last == 'b' || last == 'B') {
                        dayPeriodChar = last;
                    }
                }
                if (hourChar == 'H' || hourChar == 'k') {
                    dayPeriodLen = 0;
                }
                while (true) {
                    int dayPeriodLen2 = dayPeriodLen - 1;
                    if (dayPeriodLen <= 0) {
                        break;
                    }
                    skeletonCopy.append(dayPeriodChar);
                    dayPeriodLen = dayPeriodLen2;
                }
                while (true) {
                    int hourLen2 = hourLen - 1;
                    if (hourLen <= 0) {
                        break;
                    }
                    skeletonCopy.append(hourChar);
                    hourLen = hourLen2;
                }
            } else {
                EnumSet<DTPGflags> enumSet5 = flags;
            }
            patPos++;
        }
        EnumSet<DTPGflags> enumSet6 = flags;
        return skeletonCopy.toString();
    }

    public DateTimePatternGenerator addPattern(String pattern, boolean override, PatternInfo returnInfo) {
        return addPatternWithSkeleton(pattern, (String) null, override, returnInfo);
    }

    @Deprecated
    public DateTimePatternGenerator addPatternWithSkeleton(String pattern, String skeletonToUse, boolean override, PatternInfo returnInfo) {
        DateTimeMatcher matcher;
        checkFrozen();
        boolean z = false;
        if (skeletonToUse == null) {
            matcher = new DateTimeMatcher().set(pattern, this.fp, false);
        } else {
            matcher = new DateTimeMatcher().set(skeletonToUse, this.fp, false);
        }
        String basePattern = matcher.getBasePattern();
        PatternWithSkeletonFlag previousPatternWithSameBase = this.basePattern_pattern.get(basePattern);
        if (previousPatternWithSameBase != null && (!previousPatternWithSameBase.skeletonWasSpecified || (skeletonToUse != null && !override))) {
            returnInfo.status = 1;
            returnInfo.conflictingPattern = previousPatternWithSameBase.pattern;
            if (!override) {
                return this;
            }
        }
        PatternWithSkeletonFlag previousValue = this.skeleton2pattern.get(matcher);
        if (previousValue != null) {
            returnInfo.status = 2;
            returnInfo.conflictingPattern = previousValue.pattern;
            if (!override || (skeletonToUse != null && previousValue.skeletonWasSpecified)) {
                return this;
            }
        }
        returnInfo.status = 0;
        returnInfo.conflictingPattern = "";
        if (skeletonToUse != null) {
            z = true;
        }
        PatternWithSkeletonFlag patWithSkelFlag = new PatternWithSkeletonFlag(pattern, z);
        this.skeleton2pattern.put(matcher, patWithSkelFlag);
        this.basePattern_pattern.put(basePattern, patWithSkelFlag);
        return this;
    }

    public String getSkeleton(String pattern) {
        String dateTimeMatcher;
        synchronized (this) {
            this.current.set(pattern, this.fp, false);
            dateTimeMatcher = this.current.toString();
        }
        return dateTimeMatcher;
    }

    @Deprecated
    public String getSkeletonAllowingDuplicates(String pattern) {
        String dateTimeMatcher;
        synchronized (this) {
            this.current.set(pattern, this.fp, true);
            dateTimeMatcher = this.current.toString();
        }
        return dateTimeMatcher;
    }

    @Deprecated
    public String getCanonicalSkeletonAllowingDuplicates(String pattern) {
        String canonicalString;
        synchronized (this) {
            this.current.set(pattern, this.fp, true);
            canonicalString = this.current.toCanonicalString();
        }
        return canonicalString;
    }

    public String getBaseSkeleton(String pattern) {
        String basePattern;
        synchronized (this) {
            this.current.set(pattern, this.fp, false);
            basePattern = this.current.getBasePattern();
        }
        return basePattern;
    }

    public Map<String, String> getSkeletons(Map<String, String> result) {
        if (result == null) {
            result = new LinkedHashMap<>();
        }
        for (DateTimeMatcher item : this.skeleton2pattern.keySet()) {
            String pattern = this.skeleton2pattern.get(item).pattern;
            if (!CANONICAL_SET.contains(pattern)) {
                result.put(item.toString(), pattern);
            }
        }
        return result;
    }

    public Set<String> getBaseSkeletons(Set<String> result) {
        if (result == null) {
            result = new HashSet<>();
        }
        result.addAll(this.basePattern_pattern.keySet());
        return result;
    }

    public String replaceFieldTypes(String pattern, String skeleton) {
        return replaceFieldTypes(pattern, skeleton, 0);
    }

    public String replaceFieldTypes(String pattern, String skeleton, int options) {
        String adjustFieldTypes;
        synchronized (this) {
            adjustFieldTypes = adjustFieldTypes(new PatternWithMatcher(pattern, (DateTimeMatcher) null), this.current.set(skeleton, this.fp, false), EnumSet.noneOf(DTPGflags.class), options);
        }
        return adjustFieldTypes;
    }

    public void setDateTimeFormat(String dateTimeFormat2) {
        checkFrozen();
        this.dateTimeFormat = dateTimeFormat2;
    }

    public String getDateTimeFormat() {
        return this.dateTimeFormat;
    }

    public void setDecimal(String decimal2) {
        checkFrozen();
        this.decimal = decimal2;
    }

    public String getDecimal() {
        return this.decimal;
    }

    @Deprecated
    public Collection<String> getRedundants(Collection<String> output) {
        synchronized (this) {
            if (output == null) {
                output = new LinkedHashSet<>();
            }
            for (DateTimeMatcher cur : this.skeleton2pattern.keySet()) {
                String pattern = this.skeleton2pattern.get(cur).pattern;
                if (!CANONICAL_SET.contains(pattern)) {
                    if (getBestPattern(cur.toString(), cur, 0).equals(pattern)) {
                        output.add(pattern);
                    }
                }
            }
        }
        return output;
    }

    public enum DisplayWidth {
        WIDE(""),
        ABBREVIATED("-short"),
        NARROW("-narrow");
        
        /* access modifiers changed from: private */
        @Deprecated
        public static int COUNT;
        private final String cldrKey;

        static {
            COUNT = values().length;
        }

        private DisplayWidth(String cldrKey2) {
            this.cldrKey = cldrKey2;
        }

        /* access modifiers changed from: private */
        public String cldrKey() {
            return this.cldrKey;
        }
    }

    public void setAppendItemFormat(int field, String value) {
        checkFrozen();
        this.appendItemFormats[field] = value;
    }

    public String getAppendItemFormat(int field) {
        return this.appendItemFormats[field];
    }

    public void setAppendItemName(int field, String value) {
        setFieldDisplayName(field, APPENDITEM_WIDTH, value);
    }

    public String getAppendItemName(int field) {
        return getFieldDisplayName(field, APPENDITEM_WIDTH);
    }

    /* access modifiers changed from: private */
    @Deprecated
    public void setFieldDisplayName(int field, DisplayWidth width, String value) {
        checkFrozen();
        if (field < 16 && field >= 0) {
            this.fieldDisplayNames[field][width.ordinal()] = value;
        }
    }

    public String getFieldDisplayName(int field, DisplayWidth width) {
        if (field >= 16 || field < 0) {
            return "";
        }
        return this.fieldDisplayNames[field][width.ordinal()];
    }

    @Deprecated
    public static boolean isSingleField(String skeleton) {
        char first = skeleton.charAt(0);
        for (int i = 1; i < skeleton.length(); i++) {
            if (skeleton.charAt(i) != first) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void setAvailableFormat(String key) {
        checkFrozen();
        this.cldrAvailableFormatKeys.add(key);
    }

    /* access modifiers changed from: private */
    public boolean isAvailableFormatSet(String key) {
        return this.cldrAvailableFormatKeys.contains(key);
    }

    public boolean isFrozen() {
        return this.frozen;
    }

    public DateTimePatternGenerator freeze() {
        this.frozen = true;
        return this;
    }

    public DateTimePatternGenerator cloneAsThawed() {
        DateTimePatternGenerator result = (DateTimePatternGenerator) clone();
        this.frozen = false;
        return result;
    }

    public Object clone() {
        try {
            DateTimePatternGenerator result = (DateTimePatternGenerator) super.clone();
            result.skeleton2pattern = (TreeMap) this.skeleton2pattern.clone();
            result.basePattern_pattern = (TreeMap) this.basePattern_pattern.clone();
            result.appendItemFormats = (String[]) this.appendItemFormats.clone();
            result.fieldDisplayNames = (String[][]) this.fieldDisplayNames.clone();
            result.current = new DateTimeMatcher();
            result.fp = new FormatParser();
            result._distanceInfo = new DistanceInfo();
            result.frozen = false;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new ICUCloneNotSupportedException("Internal Error", e);
        }
    }

    @Deprecated
    public static class VariableField {
        private final int canonicalIndex;
        private final String string;

        @Deprecated
        public VariableField(String string2) {
            this(string2, false);
        }

        @Deprecated
        public VariableField(String string2, boolean strict) {
            int access$1300 = DateTimePatternGenerator.getCanonicalIndex(string2, strict);
            this.canonicalIndex = access$1300;
            if (access$1300 >= 0) {
                this.string = string2;
                return;
            }
            throw new IllegalArgumentException("Illegal datetime field:\t" + string2);
        }

        @Deprecated
        public int getType() {
            return DateTimePatternGenerator.types[this.canonicalIndex][1];
        }

        @Deprecated
        public static String getCanonicalCode(int type) {
            try {
                return DateTimePatternGenerator.CANONICAL_ITEMS[type];
            } catch (Exception e) {
                return String.valueOf(type);
            }
        }

        @Deprecated
        public boolean isNumeric() {
            return DateTimePatternGenerator.types[this.canonicalIndex][2] > 0;
        }

        /* access modifiers changed from: private */
        public int getCanonicalIndex() {
            return this.canonicalIndex;
        }

        @Deprecated
        public String toString() {
            return this.string;
        }
    }

    @Deprecated
    public static class FormatParser {
        private static final UnicodeSet QUOTING_CHARS = new UnicodeSet("[[[:script=Latn:][:script=Cyrl:]]&[[:L:][:M:]]]").freeze();
        private static final UnicodeSet SYNTAX_CHARS = new UnicodeSet("[a-zA-Z]").freeze();
        /* access modifiers changed from: private */
        public List<Object> items = new ArrayList();
        private transient PatternTokenizer tokenizer = new PatternTokenizer().setSyntaxCharacters(SYNTAX_CHARS).setExtraQuotingCharacters(QUOTING_CHARS).setUsingQuote(true);

        @Deprecated
        public final FormatParser set(String string) {
            return set(string, false);
        }

        @Deprecated
        public FormatParser set(String string, boolean strict) {
            this.items.clear();
            if (string.length() == 0) {
                return this;
            }
            this.tokenizer.setPattern(string);
            StringBuffer buffer = new StringBuffer();
            StringBuffer variable = new StringBuffer();
            while (true) {
                buffer.setLength(0);
                int status = this.tokenizer.next(buffer);
                if (status == 0) {
                    addVariable(variable, false);
                    return this;
                } else if (status == 1) {
                    if (!(variable.length() == 0 || buffer.charAt(0) == variable.charAt(0))) {
                        addVariable(variable, false);
                    }
                    variable.append(buffer);
                } else {
                    addVariable(variable, false);
                    this.items.add(buffer.toString());
                }
            }
        }

        private void addVariable(StringBuffer variable, boolean strict) {
            if (variable.length() != 0) {
                this.items.add(new VariableField(variable.toString(), strict));
                variable.setLength(0);
            }
        }

        @Deprecated
        public List<Object> getItems() {
            return this.items;
        }

        @Deprecated
        public String toString() {
            return toString(0, this.items.size());
        }

        @Deprecated
        public String toString(int start, int limit) {
            StringBuilder result = new StringBuilder();
            for (int i = start; i < limit; i++) {
                Object item = this.items.get(i);
                if (item instanceof String) {
                    result.append(this.tokenizer.quoteLiteral((String) item));
                } else {
                    result.append(this.items.get(i).toString());
                }
            }
            return result.toString();
        }

        @Deprecated
        public boolean hasDateAndTimeFields() {
            int foundMask = 0;
            for (Object item : this.items) {
                if (item instanceof VariableField) {
                    foundMask |= 1 << ((VariableField) item).getType();
                }
            }
            boolean isDate = (foundMask & 1023) != 0;
            boolean isTime = (DateTimePatternGenerator.TIME_MASK & foundMask) != 0;
            if (!isDate || !isTime) {
                return false;
            }
            return true;
        }

        @Deprecated
        public Object quoteLiteral(String string) {
            return this.tokenizer.quoteLiteral(string);
        }
    }

    @Deprecated
    public boolean skeletonsAreSimilar(String id, String skeleton) {
        if (id.equals(skeleton)) {
            return true;
        }
        TreeSet<String> parser1 = getSet(id);
        TreeSet<String> parser2 = getSet(skeleton);
        if (parser1.size() != parser2.size()) {
            return false;
        }
        Iterator<String> it2 = parser2.iterator();
        Iterator<String> it = parser1.iterator();
        while (it.hasNext()) {
            int index1 = getCanonicalIndex(it.next(), false);
            int index2 = getCanonicalIndex(it2.next(), false);
            int[][] iArr = types;
            if (iArr[index1][1] != iArr[index2][1]) {
                return false;
            }
        }
        return true;
    }

    private TreeSet<String> getSet(String id) {
        List<Object> items = this.fp.set(id).getItems();
        TreeSet<String> result = new TreeSet<>();
        for (Object obj : items) {
            String item = obj.toString();
            if (!item.startsWith("G") && !item.startsWith("a")) {
                result.add(item);
            }
        }
        return result;
    }

    private static class PatternWithMatcher {
        public DateTimeMatcher matcherWithSkeleton;
        public String pattern;

        public PatternWithMatcher(String pat, DateTimeMatcher matcher) {
            this.pattern = pat;
            this.matcherWithSkeleton = matcher;
        }
    }

    private static class PatternWithSkeletonFlag {
        public String pattern;
        public boolean skeletonWasSpecified;

        public PatternWithSkeletonFlag(String pat, boolean skelSpecified) {
            this.pattern = pat;
            this.skeletonWasSpecified = skelSpecified;
        }

        public String toString() {
            return this.pattern + "," + this.skeletonWasSpecified;
        }
    }

    private void checkFrozen() {
        if (isFrozen()) {
            throw new UnsupportedOperationException("Attempt to modify frozen object");
        }
    }

    private String getBestAppending(DateTimeMatcher source, int missingFields, DistanceInfo distInfo, DateTimeMatcher skipMatcher, EnumSet<DTPGflags> flags, int options) {
        DateTimePatternGenerator dateTimePatternGenerator = this;
        DateTimeMatcher dateTimeMatcher = source;
        int i = missingFields;
        DistanceInfo distanceInfo = distInfo;
        int i2 = options;
        String resultPattern = null;
        if (i != 0) {
            PatternWithMatcher resultPatternWithMatcher = getBestRaw(source, missingFields, distInfo, skipMatcher);
            EnumSet<DTPGflags> flags2 = flags;
            resultPattern = dateTimePatternGenerator.adjustFieldTypes(resultPatternWithMatcher, dateTimeMatcher, flags2, i2);
            while (distanceInfo.missingFieldMask != 0) {
                if ((distanceInfo.missingFieldMask & SECOND_AND_FRACTIONAL_MASK) == 16384 && (i & SECOND_AND_FRACTIONAL_MASK) == SECOND_AND_FRACTIONAL_MASK) {
                    resultPatternWithMatcher.pattern = resultPattern;
                    flags2 = EnumSet.copyOf(flags2);
                    flags2.add(DTPGflags.FIX_FRACTIONAL_SECONDS);
                    resultPattern = dateTimePatternGenerator.adjustFieldTypes(resultPatternWithMatcher, dateTimeMatcher, flags2, i2);
                    distanceInfo.missingFieldMask &= -16385;
                } else {
                    int startingMask = distanceInfo.missingFieldMask;
                    String temp = dateTimePatternGenerator.adjustFieldTypes(dateTimePatternGenerator.getBestRaw(dateTimeMatcher, distanceInfo.missingFieldMask, distanceInfo, skipMatcher), dateTimeMatcher, flags2, i2);
                    int topField = dateTimePatternGenerator.getTopBitNumber((~distanceInfo.missingFieldMask) & startingMask);
                    resultPattern = SimpleFormatterImpl.formatRawPattern(dateTimePatternGenerator.getAppendFormat(topField), 2, 3, new CharSequence[]{resultPattern, temp, dateTimePatternGenerator.getAppendName(topField)});
                    dateTimePatternGenerator = this;
                    dateTimeMatcher = source;
                }
            }
            DateTimeMatcher dateTimeMatcher2 = skipMatcher;
        } else {
            DateTimeMatcher dateTimeMatcher3 = skipMatcher;
            EnumSet<DTPGflags> enumSet = flags;
        }
        return resultPattern;
    }

    private String getAppendName(int foundMask) {
        return "'" + this.fieldDisplayNames[foundMask][APPENDITEM_WIDTH_INT] + "'";
    }

    private String getAppendFormat(int foundMask) {
        return this.appendItemFormats[foundMask];
    }

    private int getTopBitNumber(int foundMask) {
        int i = 0;
        while (foundMask != 0) {
            foundMask >>>= 1;
            i++;
        }
        return i - 1;
    }

    private void addCanonicalItems() {
        PatternInfo patternInfo = new PatternInfo();
        int i = 0;
        while (true) {
            String[] strArr = CANONICAL_ITEMS;
            if (i < strArr.length) {
                addPattern(String.valueOf(strArr[i]), false, patternInfo);
                i++;
            } else {
                return;
            }
        }
    }

    private PatternWithMatcher getBestRaw(DateTimeMatcher source, int includeMask, DistanceInfo missingFields, DateTimeMatcher skipMatcher) {
        int distance;
        int bestDistance = Integer.MAX_VALUE;
        PatternWithMatcher bestPatternWithMatcher = new PatternWithMatcher("", (DateTimeMatcher) null);
        DistanceInfo tempInfo = new DistanceInfo();
        for (DateTimeMatcher trial : this.skeleton2pattern.keySet()) {
            if (!trial.equals(skipMatcher) && (distance = source.getDistance(trial, includeMask, tempInfo)) < bestDistance) {
                bestDistance = distance;
                PatternWithSkeletonFlag patternWithSkelFlag = this.skeleton2pattern.get(trial);
                bestPatternWithMatcher.pattern = patternWithSkelFlag.pattern;
                if (patternWithSkelFlag.skeletonWasSpecified) {
                    bestPatternWithMatcher.matcherWithSkeleton = trial;
                } else {
                    bestPatternWithMatcher.matcherWithSkeleton = null;
                }
                missingFields.setTo(tempInfo);
                if (distance == 0) {
                    break;
                }
            }
        }
        return bestPatternWithMatcher;
    }

    private String adjustFieldTypes(PatternWithMatcher patternWithMatcher, DateTimeMatcher inputRequest, EnumSet<DTPGflags> flags, int options) {
        char c;
        PatternWithMatcher patternWithMatcher2 = patternWithMatcher;
        EnumSet<DTPGflags> enumSet = flags;
        int i = options;
        this.fp.set(patternWithMatcher2.pattern);
        StringBuilder newPattern = new StringBuilder();
        for (Object item : this.fp.getItems()) {
            if (item instanceof String) {
                newPattern.append(this.fp.quoteLiteral((String) item));
            } else {
                VariableField variableField = (VariableField) item;
                StringBuilder fieldBuilder = new StringBuilder(variableField.toString());
                int type = variableField.getType();
                if (enumSet.contains(DTPGflags.FIX_FRACTIONAL_SECONDS) && type == 13) {
                    fieldBuilder.append(this.decimal);
                    inputRequest.original.appendFieldTo(14, fieldBuilder);
                } else if (inputRequest.type[type] != 0) {
                    char reqFieldChar = inputRequest.original.getFieldChar(type);
                    int reqFieldLen = inputRequest.original.getFieldLength(type);
                    if (reqFieldChar == 'E' && reqFieldLen < 3) {
                        reqFieldLen = 3;
                    }
                    int adjFieldLen = reqFieldLen;
                    DateTimeMatcher matcherWithSkeleton = patternWithMatcher2.matcherWithSkeleton;
                    if ((type == 11 && (i & 2048) == 0) || ((type == 12 && (i & 4096) == 0) || (type == 13 && (i & 8192) == 0))) {
                        adjFieldLen = fieldBuilder.length();
                    } else if (matcherWithSkeleton != null) {
                        int skelFieldLen = matcherWithSkeleton.original.getFieldLength(type);
                        boolean patFieldIsNumeric = variableField.isNumeric();
                        boolean skelFieldIsNumeric = matcherWithSkeleton.fieldIsNumeric(type);
                        if (skelFieldLen == reqFieldLen || ((patFieldIsNumeric && !skelFieldIsNumeric) || (skelFieldIsNumeric && !patFieldIsNumeric))) {
                            adjFieldLen = fieldBuilder.length();
                        }
                    }
                    if (type == 11 || type == 3 || type == 6 || (type == 1 && reqFieldChar != 'Y')) {
                        c = fieldBuilder.charAt(0);
                    } else {
                        c = reqFieldChar;
                    }
                    if (type == 11 && enumSet.contains(DTPGflags.SKELETON_USES_CAP_J)) {
                        c = this.defaultHourFormatChar;
                    }
                    fieldBuilder = new StringBuilder();
                    for (int i2 = adjFieldLen; i2 > 0; i2--) {
                        fieldBuilder.append(c);
                    }
                }
                newPattern.append(fieldBuilder);
            }
        }
        return newPattern.toString();
    }

    @Deprecated
    public String getFields(String pattern) {
        this.fp.set(pattern);
        StringBuilder newPattern = new StringBuilder();
        for (Object item : this.fp.getItems()) {
            if (item instanceof String) {
                newPattern.append(this.fp.quoteLiteral((String) item));
            } else {
                newPattern.append("{" + getName(item.toString()) + "}");
            }
        }
        return newPattern.toString();
    }

    /* access modifiers changed from: private */
    public static String showMask(int mask) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            if (((1 << i) & mask) != 0) {
                if (result.length() != 0) {
                    result.append(" | ");
                }
                result.append(FIELD_NAME[i]);
                result.append(" ");
            }
        }
        return result.toString();
    }

    private static String getName(String s) {
        int i = getCanonicalIndex(s, true);
        String[] strArr = FIELD_NAME;
        int[][] iArr = types;
        String name = strArr[iArr[i][1]];
        if (iArr[i][2] < 0) {
            return name + ":S";
        }
        return name + ":N";
    }

    /* access modifiers changed from: private */
    public static int getCanonicalIndex(String s, boolean strict) {
        int len = s.length();
        if (len == 0) {
            return -1;
        }
        int ch = s.charAt(0);
        for (int i = 1; i < len; i++) {
            if (s.charAt(i) != ch) {
                return -1;
            }
        }
        int bestRow = -1;
        int i2 = 0;
        while (true) {
            int[][] iArr = types;
            if (i2 < iArr.length) {
                int[] row = iArr[i2];
                if (row[0] == ch) {
                    bestRow = i2;
                    if (row[3] <= len && row[row.length - 1] >= len) {
                        return i2;
                    }
                }
                i2++;
            } else if (strict) {
                return -1;
            } else {
                return bestRow;
            }
        }
    }

    /* access modifiers changed from: private */
    public static char getCanonicalChar(int field, char reference) {
        if (reference == 'h' || reference == 'K') {
            return 'h';
        }
        int i = 0;
        while (true) {
            int[][] iArr = types;
            if (i < iArr.length) {
                int[] row = iArr[i];
                if (row[1] == field) {
                    return (char) row[0];
                }
                i++;
            } else {
                throw new IllegalArgumentException("Could not find field " + field);
            }
        }
    }

    private static class SkeletonFields {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final byte DEFAULT_CHAR = 0;
        private static final byte DEFAULT_LENGTH = 0;
        private byte[] chars;
        private byte[] lengths;

        static {
            Class<DateTimePatternGenerator> cls = DateTimePatternGenerator.class;
        }

        private SkeletonFields() {
            this.chars = new byte[16];
            this.lengths = new byte[16];
        }

        public void clear() {
            Arrays.fill(this.chars, (byte) 0);
            Arrays.fill(this.lengths, (byte) 0);
        }

        /* access modifiers changed from: package-private */
        public void copyFieldFrom(SkeletonFields other, int field) {
            this.chars[field] = other.chars[field];
            this.lengths[field] = other.lengths[field];
        }

        /* access modifiers changed from: package-private */
        public void clearField(int field) {
            this.chars[field] = 0;
            this.lengths[field] = 0;
        }

        /* access modifiers changed from: package-private */
        public char getFieldChar(int field) {
            return (char) this.chars[field];
        }

        /* access modifiers changed from: package-private */
        public int getFieldLength(int field) {
            return this.lengths[field];
        }

        /* access modifiers changed from: package-private */
        public void populate(int field, String value) {
            char[] charArray = value.toCharArray();
            int length = charArray.length;
            int i = 0;
            while (i < length) {
                if (charArray[i] == value.charAt(0)) {
                    i++;
                } else {
                    throw new AssertionError();
                }
            }
            populate(field, value.charAt(0), value.length());
        }

        /* access modifiers changed from: package-private */
        public void populate(int field, char ch, int length) {
            if (ch > 127) {
                throw new AssertionError();
            } else if (length <= 127) {
                this.chars[field] = (byte) ch;
                this.lengths[field] = (byte) length;
            } else {
                throw new AssertionError();
            }
        }

        public boolean isFieldEmpty(int field) {
            return this.lengths[field] == 0;
        }

        public String toString() {
            return appendTo(new StringBuilder(), false, false).toString();
        }

        public String toString(boolean skipDayPeriod) {
            return appendTo(new StringBuilder(), false, skipDayPeriod).toString();
        }

        public String toCanonicalString() {
            return appendTo(new StringBuilder(), true, false).toString();
        }

        public String toCanonicalString(boolean skipDayPeriod) {
            return appendTo(new StringBuilder(), true, skipDayPeriod).toString();
        }

        public StringBuilder appendTo(StringBuilder sb) {
            return appendTo(sb, false, false);
        }

        private StringBuilder appendTo(StringBuilder sb, boolean canonical, boolean skipDayPeriod) {
            for (int i = 0; i < 16; i++) {
                if (!skipDayPeriod || i != 10) {
                    appendFieldTo(i, sb, canonical);
                }
            }
            return sb;
        }

        public StringBuilder appendFieldTo(int field, StringBuilder sb) {
            return appendFieldTo(field, sb, false);
        }

        private StringBuilder appendFieldTo(int field, StringBuilder sb, boolean canonical) {
            char ch = (char) this.chars[field];
            byte length = this.lengths[field];
            if (canonical) {
                ch = DateTimePatternGenerator.getCanonicalChar(field, ch);
            }
            for (int i = 0; i < length; i++) {
                sb.append(ch);
            }
            return sb;
        }

        public int compareTo(SkeletonFields other) {
            for (int i = 0; i < 16; i++) {
                int charDiff = this.chars[i] - other.chars[i];
                if (charDiff != 0) {
                    return charDiff;
                }
                int lengthDiff = this.lengths[i] - other.lengths[i];
                if (lengthDiff != 0) {
                    return lengthDiff;
                }
            }
            return 0;
        }

        public boolean equals(Object other) {
            return this == other || (other != null && (other instanceof SkeletonFields) && compareTo((SkeletonFields) other) == 0);
        }

        public int hashCode() {
            return Arrays.hashCode(this.chars) ^ Arrays.hashCode(this.lengths);
        }
    }

    private static class DateTimeMatcher implements Comparable<DateTimeMatcher> {
        private boolean addedDefaultDayPeriod;
        private SkeletonFields baseOriginal;
        /* access modifiers changed from: private */
        public SkeletonFields original;
        /* access modifiers changed from: private */
        public int[] type;

        private DateTimeMatcher() {
            this.type = new int[16];
            this.original = new SkeletonFields();
            this.baseOriginal = new SkeletonFields();
            this.addedDefaultDayPeriod = false;
        }

        public boolean fieldIsNumeric(int field) {
            return this.type[field] > 0;
        }

        public String toString() {
            return this.original.toString(this.addedDefaultDayPeriod);
        }

        public String toCanonicalString() {
            return this.original.toCanonicalString(this.addedDefaultDayPeriod);
        }

        /* access modifiers changed from: package-private */
        public String getBasePattern() {
            return this.baseOriginal.toString(this.addedDefaultDayPeriod);
        }

        /* access modifiers changed from: package-private */
        public DateTimeMatcher set(String pattern, FormatParser fp, boolean allowDuplicateFields) {
            Arrays.fill(this.type, 0);
            this.original.clear();
            this.baseOriginal.clear();
            this.addedDefaultDayPeriod = false;
            fp.set(pattern);
            for (Object obj : fp.getItems()) {
                if (obj instanceof VariableField) {
                    VariableField item = (VariableField) obj;
                    String value = item.toString();
                    int[] row = DateTimePatternGenerator.types[item.getCanonicalIndex()];
                    int field = row[1];
                    if (!this.original.isFieldEmpty(field)) {
                        char ch1 = this.original.getFieldChar(field);
                        char ch2 = value.charAt(0);
                        if (!allowDuplicateFields && !((ch1 == 'r' && ch2 == 'U') || (ch1 == 'U' && ch2 == 'r'))) {
                            throw new IllegalArgumentException("Conflicting fields:\t" + ch1 + ", " + value + "\t in " + pattern);
                        }
                    } else {
                        this.original.populate(field, value);
                        char repeatChar = (char) row[0];
                        int repeatCount = row[3];
                        if ("GEzvQ".indexOf(repeatChar) >= 0) {
                            repeatCount = 1;
                        }
                        this.baseOriginal.populate(field, repeatChar, repeatCount);
                        int subField = row[2];
                        if (subField > 0) {
                            subField += value.length();
                        }
                        this.type[field] = subField;
                    }
                }
            }
            if (!this.original.isFieldEmpty(11)) {
                if (this.original.getFieldChar(11) == 'h' || this.original.getFieldChar(11) == 'K') {
                    if (this.original.isFieldEmpty(10)) {
                        int i = 0;
                        while (true) {
                            if (i >= DateTimePatternGenerator.types.length) {
                                break;
                            }
                            int[] row2 = DateTimePatternGenerator.types[i];
                            if (row2[1] == 10) {
                                this.original.populate(10, (char) row2[0], row2[3]);
                                this.baseOriginal.populate(10, (char) row2[0], row2[3]);
                                this.type[10] = row2[2];
                                this.addedDefaultDayPeriod = true;
                                break;
                            }
                            i++;
                        }
                    }
                } else if (!this.original.isFieldEmpty(10)) {
                    this.original.clearField(10);
                    this.baseOriginal.clearField(10);
                    this.type[10] = 0;
                }
            }
            return this;
        }

        /* access modifiers changed from: package-private */
        public int getFieldMask() {
            int result = 0;
            int i = 0;
            while (true) {
                int[] iArr = this.type;
                if (i >= iArr.length) {
                    return result;
                }
                if (iArr[i] != 0) {
                    result |= 1 << i;
                }
                i++;
            }
        }

        /* access modifiers changed from: package-private */
        public void extractFrom(DateTimeMatcher source, int fieldMask) {
            int i = 0;
            while (true) {
                int[] iArr = this.type;
                if (i < iArr.length) {
                    if (((1 << i) & fieldMask) != 0) {
                        iArr[i] = source.type[i];
                        this.original.copyFieldFrom(source.original, i);
                    } else {
                        iArr[i] = 0;
                        this.original.clearField(i);
                    }
                    i++;
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public int getDistance(DateTimeMatcher other, int includeMask, DistanceInfo distanceInfo) {
            int result = 0;
            distanceInfo.clear();
            for (int i = 0; i < 16; i++) {
                int myType = ((1 << i) & includeMask) == 0 ? 0 : this.type[i];
                int otherType = other.type[i];
                if (myType != otherType) {
                    if (myType == 0) {
                        result += 65536;
                        distanceInfo.addExtra(i);
                    } else if (otherType == 0) {
                        result += 4096;
                        distanceInfo.addMissing(i);
                    } else {
                        result += Math.abs(myType - otherType);
                    }
                }
            }
            return result;
        }

        public int compareTo(DateTimeMatcher that) {
            int result = this.original.compareTo(that.original);
            if (result > 0) {
                return -1;
            }
            return result < 0 ? 1 : 0;
        }

        public boolean equals(Object other) {
            return this == other || (other != null && (other instanceof DateTimeMatcher) && this.original.equals(((DateTimeMatcher) other).original));
        }

        public int hashCode() {
            return this.original.hashCode();
        }
    }

    private static class DistanceInfo {
        int extraFieldMask;
        int missingFieldMask;

        private DistanceInfo() {
        }

        /* access modifiers changed from: package-private */
        public void clear() {
            this.extraFieldMask = 0;
            this.missingFieldMask = 0;
        }

        /* access modifiers changed from: package-private */
        public void setTo(DistanceInfo other) {
            this.missingFieldMask = other.missingFieldMask;
            this.extraFieldMask = other.extraFieldMask;
        }

        /* access modifiers changed from: package-private */
        public void addMissing(int field) {
            this.missingFieldMask |= 1 << field;
        }

        /* access modifiers changed from: package-private */
        public void addExtra(int field) {
            this.extraFieldMask |= 1 << field;
        }

        public String toString() {
            return "missingFieldMask: " + DateTimePatternGenerator.showMask(this.missingFieldMask) + ", extraFieldMask: " + DateTimePatternGenerator.showMask(this.extraFieldMask);
        }
    }
}
