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

/* loaded from: classes.dex */
public class DateTimePatternGenerator implements Freezable<DateTimePatternGenerator>, Cloneable {
    private static final DisplayWidth APPENDITEM_WIDTH;
    private static final int APPENDITEM_WIDTH_INT;
    private static final String[] CANONICAL_ITEMS;
    private static final Set<String> CANONICAL_SET;
    private static final String[] CLDR_FIELD_APPEND;
    private static final String[] CLDR_FIELD_NAME;
    private static final DisplayWidth[] CLDR_FIELD_WIDTH;
    private static final int DATE_MASK = 1023;
    public static final int DAY = 7;
    public static final int DAYPERIOD = 10;
    public static final int DAY_OF_WEEK_IN_MONTH = 9;
    public static final int DAY_OF_YEAR = 8;
    private static final boolean DEBUG = false;
    private static final int DELTA = 16;
    private static ICUCache<String, DateTimePatternGenerator> DTPNG_CACHE = null;
    public static final int ERA = 0;
    private static final int EXTRA_FIELD = 65536;
    private static final String[] FIELD_NAME;
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
    private static final int[][] types;
    private String[] allowedHourFormats;
    private TreeMap<DateTimeMatcher, PatternWithSkeletonFlag> skeleton2pattern = new TreeMap<>();
    private TreeMap<String, PatternWithSkeletonFlag> basePattern_pattern = new TreeMap<>();
    private String decimal = "?";
    private String dateTimeFormat = "{1} {0}";
    private String[] appendItemFormats = new String[16];
    private String[][] fieldDisplayNames = (String[][]) Array.newInstance(String.class, 16, DisplayWidth.COUNT);
    private char defaultHourFormatChar = 'H';
    private volatile boolean frozen = false;
    private transient DateTimeMatcher current = new DateTimeMatcher();

    /* renamed from: fp */
    private transient FormatParser f147fp = new FormatParser();
    private transient DistanceInfo _distanceInfo = new DistanceInfo();
    private Set<String> cldrAvailableFormatKeys = new HashSet(20);

    /* loaded from: classes.dex */
    private enum DTPGflags {
        FIX_FRACTIONAL_SECONDS,
        SKELETON_USES_CAP_J
    }

    /* loaded from: classes.dex */
    public static final class PatternInfo {
        public static final int BASE_CONFLICT = 1;
        public static final int CONFLICT = 2;

        /* renamed from: OK */
        public static final int f148OK = 0;
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
    }

    public static DateTimePatternGenerator getInstance() {
        return getInstance(ULocale.getDefault(ULocale.Category.FORMAT));
    }

    public static DateTimePatternGenerator getInstance(ULocale uLocale) {
        return getFrozenInstance(uLocale).m77cloneAsThawed();
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
        result2.m78freeze();
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
            String[] preferredCalendarTypes = Calendar.getKeywordValuesForLocale("calendar", uLocale, true);
            calendarTypeToUse = preferredCalendarTypes[0];
        }
        if (calendarTypeToUse == null) {
            return "gregorian";
        }
        return calendarTypeToUse;
    }

    private void consumeShortTimePattern(String shortTimePattern, PatternInfo returnInfo) {
        FormatParser fp = new FormatParser();
        fp.set(shortTimePattern);
        List<Object> items = fp.getItems();
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

    /* loaded from: classes.dex */
    private class AppendItemFormatsSink extends UResource.Sink {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        private AppendItemFormatsSink() {
        }

        public void put(UResource.Key key, UResource.Value value, boolean noFallback) {
            UResource.Table itemsTable = value.getTable();
            for (int i = 0; itemsTable.getKeyAndValue(i, key, value); i++) {
                int field = DateTimePatternGenerator.getAppendFormatNumber(key);
                if (field == -1) {
                    throw new AssertionError();
                }
                if (DateTimePatternGenerator.this.getAppendItemFormat(field) == null) {
                    DateTimePatternGenerator.this.setAppendItemFormat(field, value.toString());
                }
            }
        }
    }

    /* loaded from: classes.dex */
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
                setAppendItemFormat(i, "{0} \u251c{2}: {1}\u2524");
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

    /* loaded from: classes.dex */
    private class AvailableFormatsSink extends UResource.Sink {
        PatternInfo returnInfo;

        public AvailableFormatsSink(PatternInfo returnInfo) {
            this.returnInfo = returnInfo;
        }

        public void put(UResource.Key key, UResource.Value value, boolean isRoot) {
            UResource.Table formatsTable = value.getTable();
            for (int i = 0; formatsTable.getKeyAndValue(i, key, value); i++) {
                String formatKey = key.toString();
                if (!DateTimePatternGenerator.this.isAvailableFormatSet(formatKey)) {
                    DateTimePatternGenerator.this.setAvailableFormat(formatKey);
                    String formatValue = value.toString();
                    DateTimePatternGenerator.this.addPatternWithSkeleton(formatValue, formatKey, !isRoot, this.returnInfo);
                }
            }
        }
    }

    private void addCLDRData(PatternInfo returnInfo, ULocale uLocale) {
        ICUResourceBundle rb = UResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudt63b", uLocale);
        String calendarTypeToUse = getCalendarTypeToUse(uLocale);
        AppendItemFormatsSink appendItemFormatsSink = new AppendItemFormatsSink();
        try {
            rb.getAllItemsWithFallback("calendar/" + calendarTypeToUse + "/appendItems", appendItemFormatsSink);
        } catch (MissingResourceException e) {
        }
        AppendItemNamesSink appendItemNamesSink = new AppendItemNamesSink();
        try {
            rb.getAllItemsWithFallback("fields", appendItemNamesSink);
        } catch (MissingResourceException e2) {
        }
        AvailableFormatsSink availableFormatsSink = new AvailableFormatsSink(returnInfo);
        try {
            rb.getAllItemsWithFallback("calendar/" + calendarTypeToUse + "/availableFormats", availableFormatsSink);
        } catch (MissingResourceException e3) {
        }
    }

    private void setDateTimeFromCalendar(ULocale uLocale) {
        String dateTimeFormat = Calendar.getDateTimePattern(Calendar.getInstance(uLocale), uLocale, 2);
        setDateTimeFormat(dateTimeFormat);
    }

    private void setDecimalSymbols(ULocale uLocale) {
        DecimalFormatSymbols dfs = new DecimalFormatSymbols(uLocale);
        setDecimal(String.valueOf(dfs.getDecimalSeparator()));
    }

    static {
        HashMap<String, String[]> temp = new HashMap<>();
        ICUResourceBundle suppData = ICUResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudt63b", "supplementalData", ICUResourceBundle.ICU_DATA_CLASS_LOADER);
        DayPeriodAllowedHoursSink allowedHoursSink = new DayPeriodAllowedHoursSink(temp);
        suppData.getAllItemsWithFallback("timeData", allowedHoursSink);
        LOCALE_TO_ALLOWED_HOUR = Collections.unmodifiableMap(temp);
        DisplayWidth displayWidth = DisplayWidth.WIDE;
        APPENDITEM_WIDTH = displayWidth;
        APPENDITEM_WIDTH_INT = displayWidth.ordinal();
        CLDR_FIELD_WIDTH = DisplayWidth.values();
        DTPNG_CACHE = new SimpleCache();
        CLDR_FIELD_APPEND = new String[]{"Era", "Year", "Quarter", "Month", "Week", "*", "Day-Of-Week", "Day", "*", "*", "*", "Hour", "Minute", "Second", "*", "Timezone"};
        CLDR_FIELD_NAME = new String[]{"era", "year", "quarter", "month", "week", "weekOfMonth", "weekday", "day", "dayOfYear", "weekdayOfMonth", "dayperiod", "hour", "minute", "second", "*", "zone"};
        FIELD_NAME = new String[]{"Era", "Year", "Quarter", "Month", "Week_in_Year", "Week_in_Month", "Weekday", "Day", "Day_Of_Year", "Day_of_Week_in_Month", "Dayperiod", "Hour", "Minute", "Second", "Fractional_Second", "Zone"};
        String[] strArr = {"G", DateFormat.YEAR, "Q", DateFormat.NUM_MONTH, "w", "W", DateFormat.ABBR_WEEKDAY, DateFormat.DAY, "D", "F", "a", DateFormat.HOUR24, DateFormat.MINUTE, DateFormat.SECOND, "S", DateFormat.ABBR_GENERIC_TZ};
        CANONICAL_ITEMS = strArr;
        CANONICAL_SET = new HashSet(Arrays.asList(strArr));
        types = new int[][]{new int[]{71, 0, SHORT, 1, 3}, new int[]{71, 0, LONG, 4}, new int[]{71, 0, NARROW, 5}, new int[]{121, 1, 256, 1, 20}, new int[]{89, 1, 272, 1, 20}, new int[]{117, 1, 288, 1, 20}, new int[]{114, 1, 304, 1, 20}, new int[]{85, 1, SHORT, 1, 3}, new int[]{85, 1, LONG, 4}, new int[]{85, 1, NARROW, 5}, new int[]{81, 2, 256, 1, 2}, new int[]{81, 2, SHORT, 3}, new int[]{81, 2, LONG, 4}, new int[]{81, 2, NARROW, 5}, new int[]{113, 2, 272, 1, 2}, new int[]{113, 2, -275, 3}, new int[]{113, 2, -276, 4}, new int[]{113, 2, -273, 5}, new int[]{77, 3, 256, 1, 2}, new int[]{77, 3, SHORT, 3}, new int[]{77, 3, LONG, 4}, new int[]{77, 3, NARROW, 5}, new int[]{76, 3, 272, 1, 2}, new int[]{76, 3, -275, 3}, new int[]{76, 3, -276, 4}, new int[]{76, 3, -273, 5}, new int[]{108, 3, 272, 1, 1}, new int[]{119, 4, 256, 1, 2}, new int[]{87, 5, 256, 1}, new int[]{69, 6, SHORT, 1, 3}, new int[]{69, 6, LONG, 4}, new int[]{69, 6, NARROW, 5}, new int[]{69, 6, SHORTER, 6}, new int[]{99, 6, 288, 1, 2}, new int[]{99, 6, -291, 3}, new int[]{99, 6, -292, 4}, new int[]{99, 6, -289, 5}, new int[]{99, 6, -290, 6}, new int[]{101, 6, 272, 1, 2}, new int[]{101, 6, -275, 3}, new int[]{101, 6, -276, 4}, new int[]{101, 6, -273, 5}, new int[]{101, 6, -274, 6}, new int[]{100, 7, 256, 1, 2}, new int[]{103, 7, 272, 1, 20}, new int[]{68, 8, 256, 1, 3}, new int[]{70, 9, 256, 1}, new int[]{97, 10, SHORT, 1, 3}, new int[]{97, 10, LONG, 4}, new int[]{97, 10, NARROW, 5}, new int[]{98, 10, -275, 1, 3}, new int[]{98, 10, -276, 4}, new int[]{98, 10, -273, 5}, new int[]{66, 10, -307, 1, 3}, new int[]{66, 10, -308, 4}, new int[]{66, 10, -305, 5}, new int[]{72, 11, 416, 1, 2}, new int[]{107, 11, 432, 1, 2}, new int[]{104, 11, 256, 1, 2}, new int[]{75, 11, 272, 1, 2}, new int[]{109, 12, 256, 1, 2}, new int[]{115, 13, 256, 1, 2}, new int[]{65, 13, 272, 1, 1000}, new int[]{83, 14, 256, 1, 1000}, new int[]{118, 15, -291, 1}, new int[]{118, 15, -292, 4}, new int[]{122, 15, SHORT, 1, 3}, new int[]{122, 15, LONG, 4}, new int[]{90, 15, -273, 1, 3}, new int[]{90, 15, -276, 4}, new int[]{90, 15, -275, 5}, new int[]{79, 15, -275, 1}, new int[]{79, 15, -276, 4}, new int[]{86, 15, -275, 1}, new int[]{86, 15, -276, 2}, new int[]{86, 15, -277, 3}, new int[]{86, 15, -278, 4}, new int[]{88, 15, -273, 1}, new int[]{88, 15, -275, 2}, new int[]{88, 15, -276, 4}, new int[]{120, 15, -273, 1}, new int[]{120, 15, -275, 2}, new int[]{120, 15, -276, 4}};
    }

    private void getAllowedHourFormats(ULocale uLocale) {
        ULocale max = ULocale.addLikelySubtags(uLocale);
        String country = max.getCountry();
        if (country.isEmpty()) {
            country = "001";
        }
        String langCountry = max.getLanguage() + "_" + country;
        Map<String, String[]> map = LOCALE_TO_ALLOWED_HOUR;
        String[] list = map.get(langCountry);
        if (list == null && (list = map.get(country)) == null) {
            list = LAST_RESORT_ALLOWED_HOUR_FORMAT;
        }
        this.allowedHourFormats = list;
    }

    /* loaded from: classes.dex */
    private static class DayPeriodAllowedHoursSink extends UResource.Sink {
        HashMap<String, String[]> tempMap;

        private DayPeriodAllowedHoursSink(HashMap<String, String[]> tempMap) {
            this.tempMap = tempMap;
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
    public void setDefaultHourFormatChar(char defaultHourFormatChar) {
        this.defaultHourFormatChar = defaultHourFormatChar;
    }

    private void hackTimes(PatternInfo returnInfo, String shortTimePattern) {
        this.f147fp.set(shortTimePattern);
        StringBuilder mmss = new StringBuilder();
        boolean gotMm = false;
        int i = 0;
        while (true) {
            if (i >= this.f147fp.items.size()) {
                break;
            }
            Object item = this.f147fp.items.get(i);
            if (item instanceof String) {
                if (gotMm) {
                    mmss.append(this.f147fp.quoteLiteral(item.toString()));
                }
            } else {
                char ch = item.toString().charAt(0);
                if (ch == 'm') {
                    gotMm = true;
                    mmss.append(item);
                } else if (ch == 's') {
                    if (gotMm) {
                        mmss.append(item);
                        addPattern(mmss.toString(), false, returnInfo);
                    }
                } else if (gotMm || ch == 'z' || ch == 'Z' || ch == 'v' || ch == 'V') {
                    break;
                }
            }
            i++;
        }
        BitSet variables = new BitSet();
        BitSet nuke = new BitSet();
        for (int i2 = 0; i2 < this.f147fp.items.size(); i2++) {
            Object item2 = this.f147fp.items.get(i2);
            if (item2 instanceof VariableField) {
                variables.set(i2);
                char ch2 = item2.toString().charAt(0);
                if (ch2 == 's' || ch2 == 'S') {
                    nuke.set(i2);
                    for (int j = i2 - 1; j >= 0 && !variables.get(j); j++) {
                        nuke.set(i2);
                    }
                }
            }
        }
        String hhmm = getFilteredPattern(this.f147fp, nuke);
        addPattern(hhmm, false, returnInfo);
    }

    private static String getFilteredPattern(FormatParser fp, BitSet nuke) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < fp.items.size(); i++) {
            if (!nuke.get(i)) {
                Object item = fp.items.get(i);
                if (item instanceof String) {
                    result.append(fp.quoteLiteral(item.toString()));
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
            if (i < strArr.length) {
                if (!key.contentEquals(strArr[i])) {
                    i++;
                } else {
                    return i;
                }
            } else {
                return -1;
            }
        }
    }

    @Deprecated
    public static int getAppendFormatNumber(String string) {
        int i = 0;
        while (true) {
            String[] strArr = CLDR_FIELD_APPEND;
            if (i < strArr.length) {
                if (!strArr[i].equals(string)) {
                    i++;
                } else {
                    return i;
                }
            } else {
                return -1;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getCLDRFieldAndWidthNumber(UResource.Key key) {
        for (int i = 0; i < CLDR_FIELD_NAME.length; i++) {
            for (int j = 0; j < DisplayWidth.COUNT; j++) {
                String fullKey = CLDR_FIELD_NAME[i].concat(CLDR_FIELD_WIDTH[j].cldrKey());
                if (key.contentEquals(fullKey)) {
                    return (DisplayWidth.COUNT * i) + j;
                }
            }
        }
        return -1;
    }

    public String getBestPattern(String skeleton) {
        return getBestPattern(skeleton, null, 0);
    }

    public String getBestPattern(String skeleton, int options) {
        return getBestPattern(skeleton, null, options);
    }

    private String getBestPattern(String skeleton, DateTimeMatcher skipMatcher, int options) {
        EnumSet<DTPGflags> flags = EnumSet.noneOf(DTPGflags.class);
        String skeletonMapped = mapSkeletonMetacharacters(skeleton, flags);
        synchronized (this) {
            try {
                try {
                    this.current.set(skeletonMapped, this.f147fp, false);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    PatternWithMatcher bestWithMatcher = getBestRaw(this.current, -1, this._distanceInfo, skipMatcher);
                    if (this._distanceInfo.missingFieldMask == 0 && this._distanceInfo.extraFieldMask == 0) {
                        return adjustFieldTypes(bestWithMatcher, this.current, flags, options);
                    }
                    int neededFields = this.current.getFieldMask();
                    String datePattern = getBestAppending(this.current, neededFields & 1023, this._distanceInfo, skipMatcher, flags, options);
                    String timePattern = getBestAppending(this.current, neededFields & TIME_MASK, this._distanceInfo, skipMatcher, flags, options);
                    return datePattern == null ? timePattern == null ? "" : timePattern : timePattern == null ? datePattern : SimpleFormatterImpl.formatRawPattern(getDateTimeFormat(), 2, 2, new CharSequence[]{timePattern, datePattern});
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                throw th;
            }
        }
    }

    private String mapSkeletonMetacharacters(String skeleton, EnumSet<DTPGflags> flags) {
        char hourChar;
        StringBuilder skeletonCopy = new StringBuilder();
        boolean inQuoted = false;
        int patPos = 0;
        while (patPos < skeleton.length()) {
            char patChr = skeleton.charAt(patPos);
            if (patChr == '\'') {
                inQuoted = inQuoted ? false : true;
            } else if (!inQuoted) {
                if (patChr != 'j' && patChr != 'C') {
                    if (patChr == 'J') {
                        skeletonCopy.append('H');
                        flags.add(DTPGflags.SKELETON_USES_CAP_J);
                    } else {
                        skeletonCopy.append(patChr);
                    }
                }
                int extraLen = 0;
                while (patPos + 1 < skeleton.length() && skeleton.charAt(patPos + 1) == patChr) {
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
                dayPeriodLen = (hourChar == 'H' || hourChar == 'k') ? 0 : 0;
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
                    if (hourLen > 0) {
                        skeletonCopy.append(hourChar);
                        hourLen = hourLen2;
                    }
                }
            }
            patPos++;
        }
        return skeletonCopy.toString();
    }

    public DateTimePatternGenerator addPattern(String pattern, boolean override, PatternInfo returnInfo) {
        return addPatternWithSkeleton(pattern, null, override, returnInfo);
    }

    @Deprecated
    public DateTimePatternGenerator addPatternWithSkeleton(String pattern, String skeletonToUse, boolean override, PatternInfo returnInfo) {
        DateTimeMatcher matcher;
        checkFrozen();
        if (skeletonToUse == null) {
            matcher = new DateTimeMatcher().set(pattern, this.f147fp, false);
        } else {
            matcher = new DateTimeMatcher().set(skeletonToUse, this.f147fp, false);
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
        PatternWithSkeletonFlag patWithSkelFlag = new PatternWithSkeletonFlag(pattern, skeletonToUse != null);
        this.skeleton2pattern.put(matcher, patWithSkelFlag);
        this.basePattern_pattern.put(basePattern, patWithSkelFlag);
        return this;
    }

    public String getSkeleton(String pattern) {
        String dateTimeMatcher;
        synchronized (this) {
            this.current.set(pattern, this.f147fp, false);
            dateTimeMatcher = this.current.toString();
        }
        return dateTimeMatcher;
    }

    @Deprecated
    public String getSkeletonAllowingDuplicates(String pattern) {
        String dateTimeMatcher;
        synchronized (this) {
            this.current.set(pattern, this.f147fp, true);
            dateTimeMatcher = this.current.toString();
        }
        return dateTimeMatcher;
    }

    @Deprecated
    public String getCanonicalSkeletonAllowingDuplicates(String pattern) {
        String canonicalString;
        synchronized (this) {
            this.current.set(pattern, this.f147fp, true);
            canonicalString = this.current.toCanonicalString();
        }
        return canonicalString;
    }

    public String getBaseSkeleton(String pattern) {
        String basePattern;
        synchronized (this) {
            this.current.set(pattern, this.f147fp, false);
            basePattern = this.current.getBasePattern();
        }
        return basePattern;
    }

    public Map<String, String> getSkeletons(Map<String, String> result) {
        if (result == null) {
            result = new LinkedHashMap();
        }
        for (DateTimeMatcher item : this.skeleton2pattern.keySet()) {
            PatternWithSkeletonFlag patternWithSkelFlag = this.skeleton2pattern.get(item);
            String pattern = patternWithSkelFlag.pattern;
            if (!CANONICAL_SET.contains(pattern)) {
                result.put(item.toString(), pattern);
            }
        }
        return result;
    }

    public Set<String> getBaseSkeletons(Set<String> result) {
        if (result == null) {
            result = new HashSet();
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
            PatternWithMatcher patternNoMatcher = new PatternWithMatcher(pattern, null);
            adjustFieldTypes = adjustFieldTypes(patternNoMatcher, this.current.set(skeleton, this.f147fp, false), EnumSet.noneOf(DTPGflags.class), options);
        }
        return adjustFieldTypes;
    }

    public void setDateTimeFormat(String dateTimeFormat) {
        checkFrozen();
        this.dateTimeFormat = dateTimeFormat;
    }

    public String getDateTimeFormat() {
        return this.dateTimeFormat;
    }

    public void setDecimal(String decimal) {
        checkFrozen();
        this.decimal = decimal;
    }

    public String getDecimal() {
        return this.decimal;
    }

    @Deprecated
    public Collection<String> getRedundants(Collection<String> output) {
        synchronized (this) {
            if (output == null) {
                output = new LinkedHashSet();
            }
            for (DateTimeMatcher cur : this.skeleton2pattern.keySet()) {
                PatternWithSkeletonFlag patternWithSkelFlag = this.skeleton2pattern.get(cur);
                String pattern = patternWithSkelFlag.pattern;
                if (!CANONICAL_SET.contains(pattern)) {
                    String trial = getBestPattern(cur.toString(), cur, 0);
                    if (trial.equals(pattern)) {
                        output.add(pattern);
                    }
                }
            }
        }
        return output;
    }

    /* loaded from: classes.dex */
    public enum DisplayWidth {
        WIDE(""),
        ABBREVIATED("-short"),
        NARROW("-narrow");
        
        @Deprecated
        private static int COUNT = values().length;
        private final String cldrKey;

        DisplayWidth(String cldrKey) {
            this.cldrKey = cldrKey;
        }

        /* JADX INFO: Access modifiers changed from: private */
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

    /* JADX INFO: Access modifiers changed from: private */
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

    /* JADX INFO: Access modifiers changed from: private */
    public void setAvailableFormat(String key) {
        checkFrozen();
        this.cldrAvailableFormatKeys.add(key);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAvailableFormatSet(String key) {
        return this.cldrAvailableFormatKeys.contains(key);
    }

    public boolean isFrozen() {
        return this.frozen;
    }

    /* renamed from: freeze */
    public DateTimePatternGenerator m78freeze() {
        this.frozen = true;
        return this;
    }

    /* renamed from: cloneAsThawed */
    public DateTimePatternGenerator m77cloneAsThawed() {
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
            result.f147fp = new FormatParser();
            result._distanceInfo = new DistanceInfo();
            result.frozen = false;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new ICUCloneNotSupportedException("Internal Error", e);
        }
    }

    @Deprecated
    /* loaded from: classes.dex */
    public static class VariableField {
        private final int canonicalIndex;
        private final String string;

        @Deprecated
        public VariableField(String string) {
            this(string, false);
        }

        @Deprecated
        public VariableField(String string, boolean strict) {
            int canonicalIndex = DateTimePatternGenerator.getCanonicalIndex(string, strict);
            this.canonicalIndex = canonicalIndex;
            if (canonicalIndex < 0) {
                throw new IllegalArgumentException("Illegal datetime field:\t" + string);
            }
            this.string = string;
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

        /* JADX INFO: Access modifiers changed from: private */
        public int getCanonicalIndex() {
            return this.canonicalIndex;
        }

        @Deprecated
        public String toString() {
            return this.string;
        }
    }

    @Deprecated
    /* loaded from: classes.dex */
    public static class FormatParser {
        private static final UnicodeSet SYNTAX_CHARS = new UnicodeSet("[a-zA-Z]").m87freeze();
        private static final UnicodeSet QUOTING_CHARS = new UnicodeSet("[[[:script=Latn:][:script=Cyrl:]]&[[:L:][:M:]]]").m87freeze();
        private transient PatternTokenizer tokenizer = new PatternTokenizer().setSyntaxCharacters(SYNTAX_CHARS).setExtraQuotingCharacters(QUOTING_CHARS).setUsingQuote(true);
        private List<Object> items = new ArrayList();

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
                if (status != 0) {
                    if (status == 1) {
                        if (variable.length() != 0 && buffer.charAt(0) != variable.charAt(0)) {
                            addVariable(variable, false);
                        }
                        variable.append(buffer);
                    } else {
                        addVariable(variable, false);
                        this.items.add(buffer.toString());
                    }
                } else {
                    addVariable(variable, false);
                    return this;
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
                    String itemString = (String) item;
                    result.append(this.tokenizer.quoteLiteral(itemString));
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
                    int type = ((VariableField) item).getType();
                    foundMask |= 1 << type;
                }
            }
            boolean isDate = (foundMask & 1023) != 0;
            boolean isTime = (DateTimePatternGenerator.TIME_MASK & foundMask) != 0;
            return isDate && isTime;
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
            String item = it.next();
            int index1 = getCanonicalIndex(item, false);
            String item2 = it2.next();
            int index2 = getCanonicalIndex(item2, false);
            int[][] iArr = types;
            if (iArr[index1][1] != iArr[index2][1]) {
                return false;
            }
        }
        return true;
    }

    private TreeSet<String> getSet(String id) {
        List<Object> items = this.f147fp.set(id).getItems();
        TreeSet<String> result = new TreeSet<>();
        for (Object obj : items) {
            String item = obj.toString();
            if (!item.startsWith("G") && !item.startsWith("a")) {
                result.add(item);
            }
        }
        return result;
    }

    /* loaded from: classes.dex */
    private static class PatternWithMatcher {
        public DateTimeMatcher matcherWithSkeleton;
        public String pattern;

        public PatternWithMatcher(String pat, DateTimeMatcher matcher) {
            this.pattern = pat;
            this.matcherWithSkeleton = matcher;
        }
    }

    /* loaded from: classes.dex */
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
        String resultPattern = null;
        if (missingFields != 0) {
            PatternWithMatcher resultPatternWithMatcher = getBestRaw(source, missingFields, distInfo, skipMatcher);
            EnumSet<DTPGflags> flags2 = flags;
            resultPattern = dateTimePatternGenerator.adjustFieldTypes(resultPatternWithMatcher, dateTimeMatcher, flags2, options);
            while (distInfo.missingFieldMask != 0) {
                if ((distInfo.missingFieldMask & SECOND_AND_FRACTIONAL_MASK) == 16384 && (missingFields & SECOND_AND_FRACTIONAL_MASK) == SECOND_AND_FRACTIONAL_MASK) {
                    resultPatternWithMatcher.pattern = resultPattern;
                    flags2 = EnumSet.copyOf((EnumSet) flags2);
                    flags2.add(DTPGflags.FIX_FRACTIONAL_SECONDS);
                    resultPattern = dateTimePatternGenerator.adjustFieldTypes(resultPatternWithMatcher, dateTimeMatcher, flags2, options);
                    distInfo.missingFieldMask &= -16385;
                } else {
                    int startingMask = distInfo.missingFieldMask;
                    PatternWithMatcher tempWithMatcher = dateTimePatternGenerator.getBestRaw(dateTimeMatcher, distInfo.missingFieldMask, distInfo, skipMatcher);
                    String temp = dateTimePatternGenerator.adjustFieldTypes(tempWithMatcher, dateTimeMatcher, flags2, options);
                    int foundMask = (~distInfo.missingFieldMask) & startingMask;
                    int topField = dateTimePatternGenerator.getTopBitNumber(foundMask);
                    resultPattern = SimpleFormatterImpl.formatRawPattern(dateTimePatternGenerator.getAppendFormat(topField), 2, 3, new CharSequence[]{resultPattern, temp, dateTimePatternGenerator.getAppendName(topField)});
                    dateTimePatternGenerator = this;
                    dateTimeMatcher = source;
                }
            }
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
        PatternWithMatcher bestPatternWithMatcher = new PatternWithMatcher("", null);
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
        this.f147fp.set(patternWithMatcher.pattern);
        StringBuilder newPattern = new StringBuilder();
        for (Object item : this.f147fp.getItems()) {
            if (item instanceof String) {
                newPattern.append(this.f147fp.quoteLiteral((String) item));
            } else {
                VariableField variableField = (VariableField) item;
                StringBuilder fieldBuilder = new StringBuilder(variableField.toString());
                int type = variableField.getType();
                if (flags.contains(DTPGflags.FIX_FRACTIONAL_SECONDS) && type == 13) {
                    fieldBuilder.append(this.decimal);
                    inputRequest.original.appendFieldTo(14, fieldBuilder);
                } else if (inputRequest.type[type] != 0) {
                    char reqFieldChar = inputRequest.original.getFieldChar(type);
                    int reqFieldLen = inputRequest.original.getFieldLength(type);
                    if (reqFieldChar == 'E' && reqFieldLen < 3) {
                        reqFieldLen = 3;
                    }
                    int adjFieldLen = reqFieldLen;
                    DateTimeMatcher matcherWithSkeleton = patternWithMatcher.matcherWithSkeleton;
                    if ((type == 11 && (options & 2048) == 0) || ((type == 12 && (options & 4096) == 0) || (type == 13 && (options & 8192) == 0))) {
                        adjFieldLen = fieldBuilder.length();
                    } else if (matcherWithSkeleton != null) {
                        int skelFieldLen = matcherWithSkeleton.original.getFieldLength(type);
                        boolean patFieldIsNumeric = variableField.isNumeric();
                        boolean skelFieldIsNumeric = matcherWithSkeleton.fieldIsNumeric(type);
                        if (skelFieldLen == reqFieldLen || ((patFieldIsNumeric && !skelFieldIsNumeric) || (skelFieldIsNumeric && !patFieldIsNumeric))) {
                            adjFieldLen = fieldBuilder.length();
                        }
                    }
                    char c = (type == 11 || type == 3 || type == 6 || (type == 1 && reqFieldChar != 'Y')) ? fieldBuilder.charAt(0) : reqFieldChar;
                    if (type == 11 && flags.contains(DTPGflags.SKELETON_USES_CAP_J)) {
                        c = this.defaultHourFormatChar;
                    }
                    fieldBuilder = new StringBuilder();
                    for (int i = adjFieldLen; i > 0; i--) {
                        fieldBuilder.append(c);
                    }
                }
                newPattern.append((CharSequence) fieldBuilder);
            }
        }
        return newPattern.toString();
    }

    @Deprecated
    public String getFields(String pattern) {
        this.f147fp.set(pattern);
        StringBuilder newPattern = new StringBuilder();
        for (Object item : this.f147fp.getItems()) {
            if (item instanceof String) {
                newPattern.append(this.f147fp.quoteLiteral((String) item));
            } else {
                newPattern.append("{" + getName(item.toString()) + "}");
            }
        }
        return newPattern.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
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

    /* JADX INFO: Access modifiers changed from: private */
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

    /* JADX INFO: Access modifiers changed from: private */
    public static char getCanonicalChar(int field, char reference) {
        if (reference == 'h' || reference == 'K') {
            return 'h';
        }
        int i = 0;
        while (true) {
            int[][] iArr = types;
            if (i < iArr.length) {
                int[] row = iArr[i];
                if (row[1] != field) {
                    i++;
                } else {
                    return (char) row[0];
                }
            } else {
                throw new IllegalArgumentException("Could not find field " + field);
            }
        }
    }

    /* loaded from: classes.dex */
    private static class SkeletonFields {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final byte DEFAULT_CHAR = 0;
        private static final byte DEFAULT_LENGTH = 0;
        private byte[] chars;
        private byte[] lengths;

        private SkeletonFields() {
            this.chars = new byte[16];
            this.lengths = new byte[16];
        }

        public void clear() {
            Arrays.fill(this.chars, (byte) 0);
            Arrays.fill(this.lengths, (byte) 0);
        }

        void copyFieldFrom(SkeletonFields other, int field) {
            this.chars[field] = other.chars[field];
            this.lengths[field] = other.lengths[field];
        }

        void clearField(int field) {
            this.chars[field] = 0;
            this.lengths[field] = 0;
        }

        char getFieldChar(int field) {
            return (char) this.chars[field];
        }

        int getFieldLength(int field) {
            return this.lengths[field];
        }

        void populate(int field, String value) {
            char[] charArray;
            for (char ch : value.toCharArray()) {
                if (ch != value.charAt(0)) {
                    throw new AssertionError();
                }
            }
            populate(field, value.charAt(0), value.length());
        }

        void populate(int field, char ch, int length) {
            if (ch > '\u007f') {
                throw new AssertionError();
            }
            if (length > 127) {
                throw new AssertionError();
            }
            this.chars[field] = (byte) ch;
            this.lengths[field] = (byte) length;
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
            int length = this.lengths[field];
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

    /* loaded from: classes.dex */
    private static class DateTimeMatcher implements Comparable<DateTimeMatcher> {
        private boolean addedDefaultDayPeriod;
        private SkeletonFields baseOriginal;
        private SkeletonFields original;
        private int[] type;

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

        String getBasePattern() {
            return this.baseOriginal.toString(this.addedDefaultDayPeriod);
        }

        DateTimeMatcher set(String pattern, FormatParser fp, boolean allowDuplicateFields) {
            Arrays.fill(this.type, 0);
            this.original.clear();
            this.baseOriginal.clear();
            this.addedDefaultDayPeriod = false;
            fp.set(pattern);
            for (Object obj : fp.getItems()) {
                if (obj instanceof VariableField) {
                    VariableField item = (VariableField) obj;
                    String value = item.toString();
                    int canonicalIndex = item.getCanonicalIndex();
                    int[] row = DateTimePatternGenerator.types[canonicalIndex];
                    int field = row[1];
                    if (!this.original.isFieldEmpty(field)) {
                        char ch1 = this.original.getFieldChar(field);
                        char ch2 = value.charAt(0);
                        if (!allowDuplicateFields && (ch1 != 'r' || ch2 != 'U')) {
                            if (ch1 != 'U' || ch2 != 'r') {
                                throw new IllegalArgumentException("Conflicting fields:\t" + ch1 + ", " + value + "\t in " + pattern);
                            }
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
                            if (row2[1] != 10) {
                                i++;
                            } else {
                                this.original.populate(10, (char) row2[0], row2[3]);
                                this.baseOriginal.populate(10, (char) row2[0], row2[3]);
                                this.type[10] = row2[2];
                                this.addedDefaultDayPeriod = true;
                                break;
                            }
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

        int getFieldMask() {
            int result = 0;
            int i = 0;
            while (true) {
                int[] iArr = this.type;
                if (i < iArr.length) {
                    if (iArr[i] != 0) {
                        result |= 1 << i;
                    }
                    i++;
                } else {
                    return result;
                }
            }
        }

        void extractFrom(DateTimeMatcher source, int fieldMask) {
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

        int getDistance(DateTimeMatcher other, int includeMask, DistanceInfo distanceInfo) {
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

        @Override // java.lang.Comparable
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

    /* loaded from: classes.dex */
    private static class DistanceInfo {
        int extraFieldMask;
        int missingFieldMask;

        private DistanceInfo() {
        }

        void clear() {
            this.extraFieldMask = 0;
            this.missingFieldMask = 0;
        }

        void setTo(DistanceInfo other) {
            this.missingFieldMask = other.missingFieldMask;
            this.extraFieldMask = other.extraFieldMask;
        }

        void addMissing(int field) {
            this.missingFieldMask |= 1 << field;
        }

        void addExtra(int field) {
            this.extraFieldMask |= 1 << field;
        }

        public String toString() {
            return "missingFieldMask: " + DateTimePatternGenerator.showMask(this.missingFieldMask) + ", extraFieldMask: " + DateTimePatternGenerator.showMask(this.extraFieldMask);
        }
    }
}
