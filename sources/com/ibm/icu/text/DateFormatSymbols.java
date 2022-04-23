package com.ibm.icu.text;

import com.ibm.icu.impl.CacheBase;
import com.ibm.icu.impl.CalendarUtil;
import com.ibm.icu.impl.ICUResourceBundle;
import com.ibm.icu.impl.SoftCache;
import com.ibm.icu.impl.UResource;
import com.ibm.icu.impl.Utility;
import com.ibm.icu.text.TimeZoneNames;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.ICUCloneNotSupportedException;
import com.ibm.icu.util.ICUException;
import com.ibm.icu.util.TimeZone;
import com.ibm.icu.util.ULocale;
import com.ibm.icu.util.UResourceBundle;
import com.ibm.icu.util.UResourceBundleIterator;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;

public class DateFormatSymbols implements Serializable, Cloneable {
    public static final int ABBREVIATED = 0;
    static final String ALTERNATE_TIME_SEPARATOR = ".";
    private static final String[][] CALENDAR_CLASSES = {new String[]{"GregorianCalendar", "gregorian"}, new String[]{"JapaneseCalendar", "japanese"}, new String[]{"BuddhistCalendar", "buddhist"}, new String[]{"TaiwanCalendar", "roc"}, new String[]{"PersianCalendar", "persian"}, new String[]{"IslamicCalendar", "islamic"}, new String[]{"HebrewCalendar", "hebrew"}, new String[]{"ChineseCalendar", "chinese"}, new String[]{"IndianCalendar", "indian"}, new String[]{"CopticCalendar", "coptic"}, new String[]{"EthiopicCalendar", "ethiopic"}};
    private static final String[] DAY_PERIOD_KEYS = {"midnight", "noon", "morning1", "afternoon1", "evening1", "night1", "morning2", "afternoon2", "evening2", "night2"};
    static final String DEFAULT_TIME_SEPARATOR = ":";
    private static CacheBase<String, DateFormatSymbols, ULocale> DFSCACHE = new SoftCache<String, DateFormatSymbols, ULocale>() {
        /* access modifiers changed from: protected */
        public DateFormatSymbols createInstance(String key, ULocale locale) {
            int typeStart = key.indexOf(43) + 1;
            int typeLimit = key.indexOf(43, typeStart);
            if (typeLimit < 0) {
                typeLimit = key.length();
            }
            return new DateFormatSymbols(locale, (ICUResourceBundle) null, key.substring(typeStart, typeLimit));
        }
    };
    @Deprecated
    public static final int DT_CONTEXT_COUNT = 3;
    static final int DT_LEAP_MONTH_PATTERN_FORMAT_ABBREV = 1;
    static final int DT_LEAP_MONTH_PATTERN_FORMAT_NARROW = 2;
    static final int DT_LEAP_MONTH_PATTERN_FORMAT_WIDE = 0;
    static final int DT_LEAP_MONTH_PATTERN_NUMERIC = 6;
    static final int DT_LEAP_MONTH_PATTERN_STANDALONE_ABBREV = 4;
    static final int DT_LEAP_MONTH_PATTERN_STANDALONE_NARROW = 5;
    static final int DT_LEAP_MONTH_PATTERN_STANDALONE_WIDE = 3;
    static final int DT_MONTH_PATTERN_COUNT = 7;
    @Deprecated
    public static final int DT_WIDTH_COUNT = 4;
    public static final int FORMAT = 0;
    private static final String[] LEAP_MONTH_PATTERNS_PATHS;
    public static final int NARROW = 2;
    @Deprecated
    public static final int NUMERIC = 2;
    public static final int SHORT = 3;
    public static final int STANDALONE = 1;
    public static final int WIDE = 1;
    private static final Map<String, CapitalizationContextUsage> contextUsageTypeMap;
    static final int millisPerHour = 3600000;
    static final String patternChars = "GyMdkHmsSEDFwWahKzYeugAZvcLQqVUOXxrbB";
    private static final long serialVersionUID = -5987973545549424702L;
    String[] abbreviatedDayPeriods;
    private ULocale actualLocale;
    String[] ampms;
    String[] ampmsNarrow;
    Map<CapitalizationContextUsage, boolean[]> capitalization;
    String[] eraNames;
    String[] eras;
    String[] leapMonthPatterns;
    String localPatternChars;
    String[] months;
    String[] narrowDayPeriods;
    String[] narrowEras;
    String[] narrowMonths;
    String[] narrowWeekdays;
    String[] quarters;
    private ULocale requestedLocale;
    String[] shortMonths;
    String[] shortQuarters;
    String[] shortWeekdays;
    String[] shortYearNames;
    String[] shortZodiacNames;
    String[] shorterWeekdays;
    String[] standaloneAbbreviatedDayPeriods;
    String[] standaloneMonths;
    String[] standaloneNarrowDayPeriods;
    String[] standaloneNarrowMonths;
    String[] standaloneNarrowWeekdays;
    String[] standaloneQuarters;
    String[] standaloneShortMonths;
    String[] standaloneShortQuarters;
    String[] standaloneShortWeekdays;
    String[] standaloneShorterWeekdays;
    String[] standaloneWeekdays;
    String[] standaloneWideDayPeriods;
    private String timeSeparator;
    private ULocale validLocale;
    String[] weekdays;
    String[] wideDayPeriods;
    private String[][] zoneStrings;

    enum CapitalizationContextUsage {
        OTHER,
        MONTH_FORMAT,
        MONTH_STANDALONE,
        MONTH_NARROW,
        DAY_FORMAT,
        DAY_STANDALONE,
        DAY_NARROW,
        ERA_WIDE,
        ERA_ABBREV,
        ERA_NARROW,
        ZONE_LONG,
        ZONE_SHORT,
        METAZONE_LONG,
        METAZONE_SHORT
    }

    public DateFormatSymbols() {
        this(ULocale.getDefault(ULocale.Category.FORMAT));
    }

    public DateFormatSymbols(Locale locale) {
        this(ULocale.forLocale(locale));
    }

    public DateFormatSymbols(ULocale locale) {
        this.eras = null;
        this.eraNames = null;
        this.narrowEras = null;
        this.months = null;
        this.shortMonths = null;
        this.narrowMonths = null;
        this.standaloneMonths = null;
        this.standaloneShortMonths = null;
        this.standaloneNarrowMonths = null;
        this.weekdays = null;
        this.shortWeekdays = null;
        this.shorterWeekdays = null;
        this.narrowWeekdays = null;
        this.standaloneWeekdays = null;
        this.standaloneShortWeekdays = null;
        this.standaloneShorterWeekdays = null;
        this.standaloneNarrowWeekdays = null;
        this.ampms = null;
        this.ampmsNarrow = null;
        this.timeSeparator = null;
        this.shortQuarters = null;
        this.quarters = null;
        this.standaloneShortQuarters = null;
        this.standaloneQuarters = null;
        this.leapMonthPatterns = null;
        this.shortYearNames = null;
        this.shortZodiacNames = null;
        this.zoneStrings = null;
        this.localPatternChars = null;
        this.abbreviatedDayPeriods = null;
        this.wideDayPeriods = null;
        this.narrowDayPeriods = null;
        this.standaloneAbbreviatedDayPeriods = null;
        this.standaloneWideDayPeriods = null;
        this.standaloneNarrowDayPeriods = null;
        this.capitalization = null;
        initializeData(locale, CalendarUtil.getCalendarType(locale));
    }

    public static DateFormatSymbols getInstance() {
        return new DateFormatSymbols();
    }

    public static DateFormatSymbols getInstance(Locale locale) {
        return new DateFormatSymbols(locale);
    }

    public static DateFormatSymbols getInstance(ULocale locale) {
        return new DateFormatSymbols(locale);
    }

    public static Locale[] getAvailableLocales() {
        return ICUResourceBundle.getAvailableLocales();
    }

    public static ULocale[] getAvailableULocales() {
        return ICUResourceBundle.getAvailableULocales();
    }

    static {
        HashMap hashMap = new HashMap();
        contextUsageTypeMap = hashMap;
        hashMap.put("month-format-except-narrow", CapitalizationContextUsage.MONTH_FORMAT);
        hashMap.put("month-standalone-except-narrow", CapitalizationContextUsage.MONTH_STANDALONE);
        hashMap.put("month-narrow", CapitalizationContextUsage.MONTH_NARROW);
        hashMap.put("day-format-except-narrow", CapitalizationContextUsage.DAY_FORMAT);
        hashMap.put("day-standalone-except-narrow", CapitalizationContextUsage.DAY_STANDALONE);
        hashMap.put("day-narrow", CapitalizationContextUsage.DAY_NARROW);
        hashMap.put("era-name", CapitalizationContextUsage.ERA_WIDE);
        hashMap.put("era-abbr", CapitalizationContextUsage.ERA_ABBREV);
        hashMap.put("era-narrow", CapitalizationContextUsage.ERA_NARROW);
        hashMap.put("zone-long", CapitalizationContextUsage.ZONE_LONG);
        hashMap.put("zone-short", CapitalizationContextUsage.ZONE_SHORT);
        hashMap.put("metazone-long", CapitalizationContextUsage.METAZONE_LONG);
        hashMap.put("metazone-short", CapitalizationContextUsage.METAZONE_SHORT);
        String[] strArr = new String[7];
        LEAP_MONTH_PATTERNS_PATHS = strArr;
        strArr[0] = "monthPatterns/format/wide";
        strArr[1] = "monthPatterns/format/abbreviated";
        strArr[2] = "monthPatterns/format/narrow";
        strArr[3] = "monthPatterns/stand-alone/wide";
        strArr[4] = "monthPatterns/stand-alone/abbreviated";
        strArr[5] = "monthPatterns/stand-alone/narrow";
        strArr[6] = "monthPatterns/numeric/all";
    }

    public String[] getEras() {
        return duplicate(this.eras);
    }

    public void setEras(String[] newEras) {
        this.eras = duplicate(newEras);
    }

    public String[] getEraNames() {
        return duplicate(this.eraNames);
    }

    public void setEraNames(String[] newEraNames) {
        this.eraNames = duplicate(newEraNames);
    }

    public String[] getMonths() {
        return duplicate(this.months);
    }

    public String[] getMonths(int context, int width) {
        String[] returnValue = null;
        switch (context) {
            case 0:
                switch (width) {
                    case 0:
                    case 3:
                        returnValue = this.shortMonths;
                        break;
                    case 1:
                        returnValue = this.months;
                        break;
                    case 2:
                        returnValue = this.narrowMonths;
                        break;
                }
            case 1:
                switch (width) {
                    case 0:
                    case 3:
                        returnValue = this.standaloneShortMonths;
                        break;
                    case 1:
                        returnValue = this.standaloneMonths;
                        break;
                    case 2:
                        returnValue = this.standaloneNarrowMonths;
                        break;
                }
        }
        if (returnValue != null) {
            return duplicate(returnValue);
        }
        throw new IllegalArgumentException("Bad context or width argument");
    }

    public void setMonths(String[] newMonths) {
        this.months = duplicate(newMonths);
    }

    public void setMonths(String[] newMonths, int context, int width) {
        switch (context) {
            case 0:
                switch (width) {
                    case 0:
                        this.shortMonths = duplicate(newMonths);
                        return;
                    case 1:
                        this.months = duplicate(newMonths);
                        return;
                    case 2:
                        this.narrowMonths = duplicate(newMonths);
                        return;
                    default:
                        return;
                }
            case 1:
                switch (width) {
                    case 0:
                        this.standaloneShortMonths = duplicate(newMonths);
                        return;
                    case 1:
                        this.standaloneMonths = duplicate(newMonths);
                        return;
                    case 2:
                        this.standaloneNarrowMonths = duplicate(newMonths);
                        return;
                    default:
                        return;
                }
            default:
                return;
        }
    }

    public String[] getShortMonths() {
        return duplicate(this.shortMonths);
    }

    public void setShortMonths(String[] newShortMonths) {
        this.shortMonths = duplicate(newShortMonths);
    }

    public String[] getWeekdays() {
        return duplicate(this.weekdays);
    }

    public String[] getWeekdays(int context, int width) {
        String[] returnValue = null;
        switch (context) {
            case 0:
                switch (width) {
                    case 0:
                        returnValue = this.shortWeekdays;
                        break;
                    case 1:
                        returnValue = this.weekdays;
                        break;
                    case 2:
                        returnValue = this.narrowWeekdays;
                        break;
                    case 3:
                        String[] strArr = this.shorterWeekdays;
                        if (strArr == null) {
                            strArr = this.shortWeekdays;
                        }
                        returnValue = strArr;
                        break;
                }
            case 1:
                switch (width) {
                    case 0:
                        returnValue = this.standaloneShortWeekdays;
                        break;
                    case 1:
                        returnValue = this.standaloneWeekdays;
                        break;
                    case 2:
                        returnValue = this.standaloneNarrowWeekdays;
                        break;
                    case 3:
                        String[] strArr2 = this.standaloneShorterWeekdays;
                        if (strArr2 == null) {
                            strArr2 = this.standaloneShortWeekdays;
                        }
                        returnValue = strArr2;
                        break;
                }
        }
        if (returnValue != null) {
            return duplicate(returnValue);
        }
        throw new IllegalArgumentException("Bad context or width argument");
    }

    public void setWeekdays(String[] newWeekdays, int context, int width) {
        switch (context) {
            case 0:
                switch (width) {
                    case 0:
                        this.shortWeekdays = duplicate(newWeekdays);
                        return;
                    case 1:
                        this.weekdays = duplicate(newWeekdays);
                        return;
                    case 2:
                        this.narrowWeekdays = duplicate(newWeekdays);
                        return;
                    case 3:
                        this.shorterWeekdays = duplicate(newWeekdays);
                        return;
                    default:
                        return;
                }
            case 1:
                switch (width) {
                    case 0:
                        this.standaloneShortWeekdays = duplicate(newWeekdays);
                        return;
                    case 1:
                        this.standaloneWeekdays = duplicate(newWeekdays);
                        return;
                    case 2:
                        this.standaloneNarrowWeekdays = duplicate(newWeekdays);
                        return;
                    case 3:
                        this.standaloneShorterWeekdays = duplicate(newWeekdays);
                        return;
                    default:
                        return;
                }
            default:
                return;
        }
    }

    public void setWeekdays(String[] newWeekdays) {
        this.weekdays = duplicate(newWeekdays);
    }

    public String[] getShortWeekdays() {
        return duplicate(this.shortWeekdays);
    }

    public void setShortWeekdays(String[] newAbbrevWeekdays) {
        this.shortWeekdays = duplicate(newAbbrevWeekdays);
    }

    public String[] getQuarters(int context, int width) {
        String[] returnValue = null;
        switch (context) {
            case 0:
                switch (width) {
                    case 0:
                    case 3:
                        returnValue = this.shortQuarters;
                        break;
                    case 1:
                        returnValue = this.quarters;
                        break;
                    case 2:
                        returnValue = null;
                        break;
                }
            case 1:
                switch (width) {
                    case 0:
                    case 3:
                        returnValue = this.standaloneShortQuarters;
                        break;
                    case 1:
                        returnValue = this.standaloneQuarters;
                        break;
                    case 2:
                        returnValue = null;
                        break;
                }
        }
        if (returnValue != null) {
            return duplicate(returnValue);
        }
        throw new IllegalArgumentException("Bad context or width argument");
    }

    public void setQuarters(String[] newQuarters, int context, int width) {
        switch (context) {
            case 0:
                switch (width) {
                    case 0:
                        this.shortQuarters = duplicate(newQuarters);
                        return;
                    case 1:
                        this.quarters = duplicate(newQuarters);
                        return;
                    default:
                        return;
                }
            case 1:
                switch (width) {
                    case 0:
                        this.standaloneShortQuarters = duplicate(newQuarters);
                        return;
                    case 1:
                        this.standaloneQuarters = duplicate(newQuarters);
                        return;
                    default:
                        return;
                }
            default:
                return;
        }
    }

    public String[] getYearNames(int context, int width) {
        String[] strArr = this.shortYearNames;
        if (strArr != null) {
            return duplicate(strArr);
        }
        return null;
    }

    public void setYearNames(String[] yearNames, int context, int width) {
        if (context == 0 && width == 0) {
            this.shortYearNames = duplicate(yearNames);
        }
    }

    public String[] getZodiacNames(int context, int width) {
        String[] strArr = this.shortZodiacNames;
        if (strArr != null) {
            return duplicate(strArr);
        }
        return null;
    }

    public void setZodiacNames(String[] zodiacNames, int context, int width) {
        if (context == 0 && width == 0) {
            this.shortZodiacNames = duplicate(zodiacNames);
        }
    }

    @Deprecated
    public String getLeapMonthPattern(int context, int width) {
        String[] strArr = this.leapMonthPatterns;
        if (strArr == null) {
            return null;
        }
        int leapMonthPatternIndex = -1;
        switch (context) {
            case 0:
                switch (width) {
                    case 0:
                    case 3:
                        leapMonthPatternIndex = 1;
                        break;
                    case 1:
                        leapMonthPatternIndex = 0;
                        break;
                    case 2:
                        leapMonthPatternIndex = 2;
                        break;
                }
            case 1:
                switch (width) {
                    case 0:
                    case 3:
                        leapMonthPatternIndex = 1;
                        break;
                    case 1:
                        leapMonthPatternIndex = 3;
                        break;
                    case 2:
                        leapMonthPatternIndex = 5;
                        break;
                }
            case 2:
                leapMonthPatternIndex = 6;
                break;
        }
        if (leapMonthPatternIndex >= 0) {
            return strArr[leapMonthPatternIndex];
        }
        throw new IllegalArgumentException("Bad context or width argument");
    }

    @Deprecated
    public void setLeapMonthPattern(String leapMonthPattern, int context, int width) {
        String[] strArr = this.leapMonthPatterns;
        if (strArr != null) {
            int leapMonthPatternIndex = -1;
            switch (context) {
                case 0:
                    switch (width) {
                        case 0:
                            leapMonthPatternIndex = 1;
                            break;
                        case 1:
                            leapMonthPatternIndex = 0;
                            break;
                        case 2:
                            leapMonthPatternIndex = 2;
                            break;
                    }
                case 1:
                    switch (width) {
                        case 0:
                            leapMonthPatternIndex = 1;
                            break;
                        case 1:
                            leapMonthPatternIndex = 3;
                            break;
                        case 2:
                            leapMonthPatternIndex = 5;
                            break;
                    }
                case 2:
                    leapMonthPatternIndex = 6;
                    break;
            }
            if (leapMonthPatternIndex >= 0) {
                strArr[leapMonthPatternIndex] = leapMonthPattern;
            }
        }
    }

    public String[] getAmPmStrings() {
        return duplicate(this.ampms);
    }

    public void setAmPmStrings(String[] newAmpms) {
        this.ampms = duplicate(newAmpms);
    }

    @Deprecated
    public String getTimeSeparatorString() {
        return this.timeSeparator;
    }

    @Deprecated
    public void setTimeSeparatorString(String newTimeSeparator) {
        this.timeSeparator = newTimeSeparator;
    }

    public String[][] getZoneStrings() {
        String[][] strArr = this.zoneStrings;
        if (strArr != null) {
            return duplicate(strArr);
        }
        String[] tzIDs = TimeZone.getAvailableIDs();
        TimeZoneNames tznames = TimeZoneNames.getInstance(this.validLocale);
        tznames.loadAllDisplayNames();
        TimeZoneNames.NameType[] types = {TimeZoneNames.NameType.LONG_STANDARD, TimeZoneNames.NameType.SHORT_STANDARD, TimeZoneNames.NameType.LONG_DAYLIGHT, TimeZoneNames.NameType.SHORT_DAYLIGHT};
        long now = System.currentTimeMillis();
        int length = tzIDs.length;
        int[] iArr = new int[2];
        iArr[1] = 5;
        iArr[0] = length;
        String[][] array = (String[][]) Array.newInstance(String.class, iArr);
        for (int i = 0; i < tzIDs.length; i++) {
            String canonicalID = TimeZone.getCanonicalID(tzIDs[i]);
            if (canonicalID == null) {
                canonicalID = tzIDs[i];
            }
            array[i][0] = tzIDs[i];
            tznames.getDisplayNames(canonicalID, types, now, array[i], 1);
        }
        this.zoneStrings = array;
        return array;
    }

    public void setZoneStrings(String[][] newZoneStrings) {
        this.zoneStrings = duplicate(newZoneStrings);
    }

    public String getLocalPatternChars() {
        return this.localPatternChars;
    }

    public void setLocalPatternChars(String newLocalPatternChars) {
        this.localPatternChars = newLocalPatternChars;
    }

    public Object clone() {
        try {
            return (DateFormatSymbols) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new ICUCloneNotSupportedException(e);
        }
    }

    public int hashCode() {
        return this.requestedLocale.toString().hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DateFormatSymbols that = (DateFormatSymbols) obj;
        if (!Utility.arrayEquals(this.eras, that.eras) || !Utility.arrayEquals(this.eraNames, that.eraNames) || !Utility.arrayEquals(this.months, that.months) || !Utility.arrayEquals(this.shortMonths, that.shortMonths) || !Utility.arrayEquals(this.narrowMonths, that.narrowMonths) || !Utility.arrayEquals(this.standaloneMonths, that.standaloneMonths) || !Utility.arrayEquals(this.standaloneShortMonths, that.standaloneShortMonths) || !Utility.arrayEquals(this.standaloneNarrowMonths, that.standaloneNarrowMonths) || !Utility.arrayEquals(this.weekdays, that.weekdays) || !Utility.arrayEquals(this.shortWeekdays, that.shortWeekdays) || !Utility.arrayEquals(this.shorterWeekdays, that.shorterWeekdays) || !Utility.arrayEquals(this.narrowWeekdays, that.narrowWeekdays) || !Utility.arrayEquals(this.standaloneWeekdays, that.standaloneWeekdays) || !Utility.arrayEquals(this.standaloneShortWeekdays, that.standaloneShortWeekdays) || !Utility.arrayEquals(this.standaloneShorterWeekdays, that.standaloneShorterWeekdays) || !Utility.arrayEquals(this.standaloneNarrowWeekdays, that.standaloneNarrowWeekdays) || !Utility.arrayEquals(this.ampms, that.ampms) || !Utility.arrayEquals(this.ampmsNarrow, that.ampmsNarrow) || !Utility.arrayEquals(this.abbreviatedDayPeriods, that.abbreviatedDayPeriods) || !Utility.arrayEquals(this.wideDayPeriods, that.wideDayPeriods) || !Utility.arrayEquals(this.narrowDayPeriods, that.narrowDayPeriods) || !Utility.arrayEquals(this.standaloneAbbreviatedDayPeriods, that.standaloneAbbreviatedDayPeriods) || !Utility.arrayEquals(this.standaloneWideDayPeriods, that.standaloneWideDayPeriods) || !Utility.arrayEquals(this.standaloneNarrowDayPeriods, that.standaloneNarrowDayPeriods) || !Utility.arrayEquals(this.timeSeparator, that.timeSeparator) || !arrayOfArrayEquals(this.zoneStrings, that.zoneStrings) || !this.requestedLocale.getDisplayName().equals(that.requestedLocale.getDisplayName()) || !Utility.arrayEquals(this.localPatternChars, that.localPatternChars)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void initializeData(ULocale desiredLocale, String type) {
        String key = desiredLocale.getBaseName() + '+' + type;
        String ns = desiredLocale.getKeywordValue("numbers");
        if (ns != null && ns.length() > 0) {
            key = key + '+' + ns;
        }
        initializeData((DateFormatSymbols) DFSCACHE.getInstance(key, desiredLocale));
    }

    /* access modifiers changed from: package-private */
    public void initializeData(DateFormatSymbols dfs) {
        this.eras = dfs.eras;
        this.eraNames = dfs.eraNames;
        this.narrowEras = dfs.narrowEras;
        this.months = dfs.months;
        this.shortMonths = dfs.shortMonths;
        this.narrowMonths = dfs.narrowMonths;
        this.standaloneMonths = dfs.standaloneMonths;
        this.standaloneShortMonths = dfs.standaloneShortMonths;
        this.standaloneNarrowMonths = dfs.standaloneNarrowMonths;
        this.weekdays = dfs.weekdays;
        this.shortWeekdays = dfs.shortWeekdays;
        this.shorterWeekdays = dfs.shorterWeekdays;
        this.narrowWeekdays = dfs.narrowWeekdays;
        this.standaloneWeekdays = dfs.standaloneWeekdays;
        this.standaloneShortWeekdays = dfs.standaloneShortWeekdays;
        this.standaloneShorterWeekdays = dfs.standaloneShorterWeekdays;
        this.standaloneNarrowWeekdays = dfs.standaloneNarrowWeekdays;
        this.ampms = dfs.ampms;
        this.ampmsNarrow = dfs.ampmsNarrow;
        this.timeSeparator = dfs.timeSeparator;
        this.shortQuarters = dfs.shortQuarters;
        this.quarters = dfs.quarters;
        this.standaloneShortQuarters = dfs.standaloneShortQuarters;
        this.standaloneQuarters = dfs.standaloneQuarters;
        this.leapMonthPatterns = dfs.leapMonthPatterns;
        this.shortYearNames = dfs.shortYearNames;
        this.shortZodiacNames = dfs.shortZodiacNames;
        this.abbreviatedDayPeriods = dfs.abbreviatedDayPeriods;
        this.wideDayPeriods = dfs.wideDayPeriods;
        this.narrowDayPeriods = dfs.narrowDayPeriods;
        this.standaloneAbbreviatedDayPeriods = dfs.standaloneAbbreviatedDayPeriods;
        this.standaloneWideDayPeriods = dfs.standaloneWideDayPeriods;
        this.standaloneNarrowDayPeriods = dfs.standaloneNarrowDayPeriods;
        this.zoneStrings = dfs.zoneStrings;
        this.localPatternChars = dfs.localPatternChars;
        this.capitalization = dfs.capitalization;
        this.actualLocale = dfs.actualLocale;
        this.validLocale = dfs.validLocale;
        this.requestedLocale = dfs.requestedLocale;
    }

    private static final class CalendarDataSink extends UResource.Sink {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final String CALENDAR_ALIAS_PREFIX = "/LOCALE/calendar/";
        List<String> aliasPathPairs = new ArrayList();
        private String aliasRelativePath;
        Map<String, String[]> arrays = new TreeMap();
        String currentCalendarType = null;
        Map<String, Map<String, String>> maps = new TreeMap();
        String nextCalendarType = null;
        private Set<String> resourcesToVisit;

        private enum AliasType {
            SAME_CALENDAR,
            DIFFERENT_CALENDAR,
            GREGORIAN,
            NONE
        }

        static {
            Class<DateFormatSymbols> cls = DateFormatSymbols.class;
        }

        CalendarDataSink() {
        }

        /* access modifiers changed from: package-private */
        public void visitAllResources() {
            this.resourcesToVisit = null;
        }

        /* access modifiers changed from: package-private */
        public void preEnumerate(String calendarType) {
            this.currentCalendarType = calendarType;
            this.nextCalendarType = null;
            this.aliasPathPairs.clear();
        }

        /* JADX WARNING: Removed duplicated region for block: B:55:0x00df  */
        /* JADX WARNING: Removed duplicated region for block: B:68:0x0140  */
        /* JADX WARNING: Removed duplicated region for block: B:90:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void put(com.ibm.icu.impl.UResource.Key r10, com.ibm.icu.impl.UResource.Value r11, boolean r12) {
            /*
                r9 = this;
                java.lang.String r0 = r9.currentCalendarType
                if (r0 == 0) goto L_0x0143
                boolean r0 = r0.isEmpty()
                if (r0 != 0) goto L_0x0143
                r0 = 0
                com.ibm.icu.impl.UResource$Table r1 = r11.getTable()
                r2 = 0
            L_0x0010:
                boolean r3 = r1.getKeyAndValue(r2, r10, r11)
                if (r3 == 0) goto L_0x00d5
                java.lang.String r3 = r10.toString()
                com.ibm.icu.text.DateFormatSymbols$CalendarDataSink$AliasType r4 = r9.processAliasFromValue(r3, r11)
                com.ibm.icu.text.DateFormatSymbols$CalendarDataSink$AliasType r5 = com.ibm.icu.text.DateFormatSymbols.CalendarDataSink.AliasType.GREGORIAN
                if (r4 != r5) goto L_0x0024
                goto L_0x00d1
            L_0x0024:
                com.ibm.icu.text.DateFormatSymbols$CalendarDataSink$AliasType r5 = com.ibm.icu.text.DateFormatSymbols.CalendarDataSink.AliasType.DIFFERENT_CALENDAR
                if (r4 != r5) goto L_0x0037
                if (r0 != 0) goto L_0x0030
                java.util.HashSet r5 = new java.util.HashSet
                r5.<init>()
                r0 = r5
            L_0x0030:
                java.lang.String r5 = r9.aliasRelativePath
                r0.add(r5)
                goto L_0x00d1
            L_0x0037:
                com.ibm.icu.text.DateFormatSymbols$CalendarDataSink$AliasType r5 = com.ibm.icu.text.DateFormatSymbols.CalendarDataSink.AliasType.SAME_CALENDAR
                if (r4 != r5) goto L_0x0059
                java.util.Map<java.lang.String, java.lang.String[]> r5 = r9.arrays
                boolean r5 = r5.containsKey(r3)
                if (r5 != 0) goto L_0x00d1
                java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.String>> r5 = r9.maps
                boolean r5 = r5.containsKey(r3)
                if (r5 != 0) goto L_0x00d1
                java.util.List<java.lang.String> r5 = r9.aliasPathPairs
                java.lang.String r6 = r9.aliasRelativePath
                r5.add(r6)
                java.util.List<java.lang.String> r5 = r9.aliasPathPairs
                r5.add(r3)
                goto L_0x00d1
            L_0x0059:
                java.util.Set<java.lang.String> r5 = r9.resourcesToVisit
                if (r5 == 0) goto L_0x0074
                boolean r5 = r5.isEmpty()
                if (r5 != 0) goto L_0x0074
                java.util.Set<java.lang.String> r5 = r9.resourcesToVisit
                boolean r5 = r5.contains(r3)
                if (r5 != 0) goto L_0x0074
                java.lang.String r5 = "AmPmMarkersAbbr"
                boolean r5 = r3.equals(r5)
                if (r5 != 0) goto L_0x0074
                goto L_0x00d1
            L_0x0074:
                java.lang.String r5 = "AmPmMarkers"
                boolean r5 = r3.startsWith(r5)
                if (r5 == 0) goto L_0x0096
                java.lang.String r5 = "%variant"
                boolean r5 = r3.endsWith(r5)
                if (r5 != 0) goto L_0x00d1
                java.util.Map<java.lang.String, java.lang.String[]> r5 = r9.arrays
                boolean r5 = r5.containsKey(r3)
                if (r5 != 0) goto L_0x00d1
                java.lang.String[] r5 = r11.getStringArray()
                java.util.Map<java.lang.String, java.lang.String[]> r6 = r9.arrays
                r6.put(r3, r5)
                goto L_0x00d1
            L_0x0096:
                java.lang.String r5 = "eras"
                boolean r5 = r3.equals(r5)
                if (r5 != 0) goto L_0x00ce
                java.lang.String r5 = "dayNames"
                boolean r5 = r3.equals(r5)
                if (r5 != 0) goto L_0x00ce
                java.lang.String r5 = "monthNames"
                boolean r5 = r3.equals(r5)
                if (r5 != 0) goto L_0x00ce
                java.lang.String r5 = "quarters"
                boolean r5 = r3.equals(r5)
                if (r5 != 0) goto L_0x00ce
                java.lang.String r5 = "dayPeriod"
                boolean r5 = r3.equals(r5)
                if (r5 != 0) goto L_0x00ce
                java.lang.String r5 = "monthPatterns"
                boolean r5 = r3.equals(r5)
                if (r5 != 0) goto L_0x00ce
                java.lang.String r5 = "cyclicNameSets"
                boolean r5 = r3.equals(r5)
                if (r5 == 0) goto L_0x00d1
            L_0x00ce:
                r9.processResource(r3, r10, r11)
            L_0x00d1:
                int r2 = r2 + 1
                goto L_0x0010
            L_0x00d5:
                r2 = 0
                r3 = 0
            L_0x00d7:
                java.util.List<java.lang.String> r4 = r9.aliasPathPairs
                int r4 = r4.size()
                if (r3 >= r4) goto L_0x0134
                r4 = 0
                java.util.List<java.lang.String> r5 = r9.aliasPathPairs
                java.lang.Object r5 = r5.get(r3)
                java.lang.String r5 = (java.lang.String) r5
                java.util.Map<java.lang.String, java.lang.String[]> r6 = r9.arrays
                boolean r6 = r6.containsKey(r5)
                if (r6 == 0) goto L_0x0105
                java.util.Map<java.lang.String, java.lang.String[]> r6 = r9.arrays
                java.util.List<java.lang.String> r7 = r9.aliasPathPairs
                int r8 = r3 + 1
                java.lang.Object r7 = r7.get(r8)
                java.util.Map<java.lang.String, java.lang.String[]> r8 = r9.arrays
                java.lang.Object r8 = r8.get(r5)
                r6.put(r7, r8)
                r4 = 1
                goto L_0x0121
            L_0x0105:
                java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.String>> r6 = r9.maps
                boolean r6 = r6.containsKey(r5)
                if (r6 == 0) goto L_0x0121
                java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.String>> r6 = r9.maps
                java.util.List<java.lang.String> r7 = r9.aliasPathPairs
                int r8 = r3 + 1
                java.lang.Object r7 = r7.get(r8)
                java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.String>> r8 = r9.maps
                java.lang.Object r8 = r8.get(r5)
                r6.put(r7, r8)
                r4 = 1
            L_0x0121:
                if (r4 == 0) goto L_0x0131
                java.util.List<java.lang.String> r6 = r9.aliasPathPairs
                int r7 = r3 + 1
                r6.remove(r7)
                java.util.List<java.lang.String> r6 = r9.aliasPathPairs
                r6.remove(r3)
                r2 = 1
                goto L_0x0133
            L_0x0131:
                int r3 = r3 + 2
            L_0x0133:
                goto L_0x00d7
            L_0x0134:
                if (r2 == 0) goto L_0x013e
                java.util.List<java.lang.String> r3 = r9.aliasPathPairs
                boolean r3 = r3.isEmpty()
                if (r3 == 0) goto L_0x00d5
            L_0x013e:
                if (r0 == 0) goto L_0x0142
                r9.resourcesToVisit = r0
            L_0x0142:
                return
            L_0x0143:
                java.lang.AssertionError r0 = new java.lang.AssertionError
                r0.<init>()
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.DateFormatSymbols.CalendarDataSink.put(com.ibm.icu.impl.UResource$Key, com.ibm.icu.impl.UResource$Value, boolean):void");
        }

        /* access modifiers changed from: protected */
        public void processResource(String path, UResource.Key key, UResource.Value value) {
            UResource.Table table = value.getTable();
            Map<String, String> stringMap = null;
            for (int i = 0; table.getKeyAndValue(i, key, value); i++) {
                if (!key.endsWith("%variant")) {
                    String keyString = key.toString();
                    if (value.getType() == 0) {
                        if (i == 0) {
                            stringMap = new HashMap<>();
                            this.maps.put(path, stringMap);
                        }
                        if (stringMap != null) {
                            stringMap.put(keyString, value.getString());
                        } else {
                            throw new AssertionError();
                        }
                    } else if (stringMap == null) {
                        String currentPath = path + "/" + keyString;
                        if ((!currentPath.startsWith("cyclicNameSets") || "cyclicNameSets/years/format/abbreviated".startsWith(currentPath) || "cyclicNameSets/zodiacs/format/abbreviated".startsWith(currentPath) || "cyclicNameSets/dayParts/format/abbreviated".startsWith(currentPath)) && !this.arrays.containsKey(currentPath) && !this.maps.containsKey(currentPath)) {
                            AliasType aliasType = processAliasFromValue(currentPath, value);
                            if (aliasType == AliasType.SAME_CALENDAR) {
                                this.aliasPathPairs.add(this.aliasRelativePath);
                                this.aliasPathPairs.add(currentPath);
                            } else if (aliasType != AliasType.NONE) {
                                throw new AssertionError();
                            } else if (value.getType() == 8) {
                                this.arrays.put(currentPath, value.getStringArray());
                            } else if (value.getType() == 2) {
                                processResource(currentPath, key, value);
                            }
                        }
                    } else {
                        throw new AssertionError();
                    }
                }
            }
        }

        private AliasType processAliasFromValue(String currentRelativePath, UResource.Value value) {
            int typeLimit;
            if (value.getType() != 3) {
                return AliasType.NONE;
            }
            String aliasPath = value.getAliasString();
            if (aliasPath.startsWith(CALENDAR_ALIAS_PREFIX) && aliasPath.length() > CALENDAR_ALIAS_PREFIX.length() && (typeLimit = aliasPath.indexOf(47, CALENDAR_ALIAS_PREFIX.length())) > CALENDAR_ALIAS_PREFIX.length()) {
                String aliasCalendarType = aliasPath.substring(CALENDAR_ALIAS_PREFIX.length(), typeLimit);
                this.aliasRelativePath = aliasPath.substring(typeLimit + 1);
                if (this.currentCalendarType.equals(aliasCalendarType) && !currentRelativePath.equals(this.aliasRelativePath)) {
                    return AliasType.SAME_CALENDAR;
                }
                if (!this.currentCalendarType.equals(aliasCalendarType) && currentRelativePath.equals(this.aliasRelativePath)) {
                    if (aliasCalendarType.equals("gregorian")) {
                        return AliasType.GREGORIAN;
                    }
                    String str = this.nextCalendarType;
                    if (str == null || str.equals(aliasCalendarType)) {
                        this.nextCalendarType = aliasCalendarType;
                        return AliasType.DIFFERENT_CALENDAR;
                    }
                }
            }
            throw new ICUException("Malformed 'calendar' alias. Path: " + aliasPath);
        }
    }

    private DateFormatSymbols(ULocale desiredLocale, ICUResourceBundle b, String calendarType) {
        this.eras = null;
        this.eraNames = null;
        this.narrowEras = null;
        this.months = null;
        this.shortMonths = null;
        this.narrowMonths = null;
        this.standaloneMonths = null;
        this.standaloneShortMonths = null;
        this.standaloneNarrowMonths = null;
        this.weekdays = null;
        this.shortWeekdays = null;
        this.shorterWeekdays = null;
        this.narrowWeekdays = null;
        this.standaloneWeekdays = null;
        this.standaloneShortWeekdays = null;
        this.standaloneShorterWeekdays = null;
        this.standaloneNarrowWeekdays = null;
        this.ampms = null;
        this.ampmsNarrow = null;
        this.timeSeparator = null;
        this.shortQuarters = null;
        this.quarters = null;
        this.standaloneShortQuarters = null;
        this.standaloneQuarters = null;
        this.leapMonthPatterns = null;
        this.shortYearNames = null;
        this.shortZodiacNames = null;
        this.zoneStrings = null;
        this.localPatternChars = null;
        this.abbreviatedDayPeriods = null;
        this.wideDayPeriods = null;
        this.narrowDayPeriods = null;
        this.standaloneAbbreviatedDayPeriods = null;
        this.standaloneWideDayPeriods = null;
        this.standaloneNarrowDayPeriods = null;
        this.capitalization = null;
        initializeData(desiredLocale, b, calendarType);
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public void initializeData(ULocale desiredLocale, ICUResourceBundle b, String calendarType) {
        String calendarType2;
        ICUResourceBundle b2;
        UResourceBundle contextTransformsBundle;
        String[] ssWeekdays;
        Map<String, String> monthPatternMap;
        String leapMonthPattern;
        String calendarType3;
        ULocale uLocale = desiredLocale;
        CalendarDataSink calendarSink = new CalendarDataSink();
        if (b == null) {
            b2 = (ICUResourceBundle) UResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudt63b", uLocale);
            calendarType2 = calendarType;
        } else {
            b2 = b;
            calendarType2 = calendarType;
        }
        while (calendarType2 != null) {
            ICUResourceBundle dataForType = b2.findWithFallback("calendar/" + calendarType2);
            if (dataForType != null) {
                calendarSink.preEnumerate(calendarType2);
                dataForType.getAllItemsWithFallback("", calendarSink);
                if (calendarType2.equals("gregorian")) {
                    break;
                }
                calendarType3 = calendarSink.nextCalendarType;
                if (calendarType3 == null) {
                    calendarType3 = "gregorian";
                    calendarSink.visitAllResources();
                }
            } else if (!"gregorian".equals(calendarType2)) {
                calendarType3 = "gregorian";
                calendarSink.visitAllResources();
            } else {
                throw new MissingResourceException("The 'gregorian' calendar type wasn't found for the locale: " + desiredLocale.getBaseName(), getClass().getName(), "gregorian");
            }
        }
        Map<String, String[]> arrays = calendarSink.arrays;
        Map<String, Map<String, String>> maps = calendarSink.maps;
        this.eras = arrays.get("eras/abbreviated");
        this.eraNames = arrays.get("eras/wide");
        this.narrowEras = arrays.get("eras/narrow");
        this.months = arrays.get("monthNames/format/wide");
        this.shortMonths = arrays.get("monthNames/format/abbreviated");
        this.narrowMonths = arrays.get("monthNames/format/narrow");
        this.standaloneMonths = arrays.get("monthNames/stand-alone/wide");
        this.standaloneShortMonths = arrays.get("monthNames/stand-alone/abbreviated");
        this.standaloneNarrowMonths = arrays.get("monthNames/stand-alone/narrow");
        String[] lWeekdays = arrays.get("dayNames/format/wide");
        String[] strArr = new String[8];
        this.weekdays = strArr;
        strArr[0] = "";
        System.arraycopy(lWeekdays, 0, strArr, 1, lWeekdays.length);
        String[] aWeekdays = arrays.get("dayNames/format/abbreviated");
        String[] strArr2 = new String[8];
        this.shortWeekdays = strArr2;
        strArr2[0] = "";
        System.arraycopy(aWeekdays, 0, strArr2, 1, aWeekdays.length);
        String[] sWeekdays = arrays.get("dayNames/format/short");
        String[] strArr3 = new String[8];
        this.shorterWeekdays = strArr3;
        strArr3[0] = "";
        CalendarDataSink calendarDataSink = calendarSink;
        System.arraycopy(sWeekdays, 0, strArr3, 1, sWeekdays.length);
        String[] nWeekdays = arrays.get("dayNames/format/narrow");
        if (nWeekdays == null && (nWeekdays = arrays.get("dayNames/stand-alone/narrow")) == null && (nWeekdays = arrays.get("dayNames/format/abbreviated")) == null) {
            throw new MissingResourceException("Resource not found", getClass().getName(), "dayNames/format/abbreviated");
        }
        String[] strArr4 = new String[8];
        this.narrowWeekdays = strArr4;
        strArr4[0] = "";
        ICUResourceBundle iCUResourceBundle = b2;
        System.arraycopy(nWeekdays, 0, strArr4, 1, nWeekdays.length);
        String[] swWeekdays = arrays.get("dayNames/stand-alone/wide");
        String[] strArr5 = new String[8];
        this.standaloneWeekdays = strArr5;
        strArr5[0] = "";
        String[] strArr6 = nWeekdays;
        System.arraycopy(swWeekdays, 0, strArr5, 1, swWeekdays.length);
        String[] saWeekdays = arrays.get("dayNames/stand-alone/abbreviated");
        String[] strArr7 = new String[8];
        this.standaloneShortWeekdays = strArr7;
        strArr7[0] = "";
        String[] strArr8 = swWeekdays;
        System.arraycopy(saWeekdays, 0, strArr7, 1, saWeekdays.length);
        String[] ssWeekdays2 = arrays.get("dayNames/stand-alone/short");
        String[] strArr9 = new String[8];
        this.standaloneShorterWeekdays = strArr9;
        strArr9[0] = "";
        String[] strArr10 = saWeekdays;
        System.arraycopy(ssWeekdays2, 0, strArr9, 1, ssWeekdays2.length);
        String[] snWeekdays = arrays.get("dayNames/stand-alone/narrow");
        String[] strArr11 = new String[8];
        this.standaloneNarrowWeekdays = strArr11;
        strArr11[0] = "";
        System.arraycopy(snWeekdays, 0, strArr11, 1, snWeekdays.length);
        this.ampms = arrays.get("AmPmMarkers");
        this.ampmsNarrow = arrays.get("AmPmMarkersNarrow");
        this.quarters = arrays.get("quarters/format/wide");
        this.shortQuarters = arrays.get("quarters/format/abbreviated");
        this.standaloneQuarters = arrays.get("quarters/stand-alone/wide");
        this.standaloneShortQuarters = arrays.get("quarters/stand-alone/abbreviated");
        this.abbreviatedDayPeriods = loadDayPeriodStrings(maps.get("dayPeriod/format/abbreviated"));
        this.wideDayPeriods = loadDayPeriodStrings(maps.get("dayPeriod/format/wide"));
        this.narrowDayPeriods = loadDayPeriodStrings(maps.get("dayPeriod/format/narrow"));
        this.standaloneAbbreviatedDayPeriods = loadDayPeriodStrings(maps.get("dayPeriod/stand-alone/abbreviated"));
        this.standaloneWideDayPeriods = loadDayPeriodStrings(maps.get("dayPeriod/stand-alone/wide"));
        this.standaloneNarrowDayPeriods = loadDayPeriodStrings(maps.get("dayPeriod/stand-alone/narrow"));
        for (int i = 0; i < 7; i++) {
            String monthPatternPath = LEAP_MONTH_PATTERNS_PATHS[i];
            if (!(monthPatternPath == null || (monthPatternMap = maps.get(monthPatternPath)) == null || (leapMonthPattern = monthPatternMap.get("leap")) == null)) {
                if (this.leapMonthPatterns == null) {
                    this.leapMonthPatterns = new String[7];
                }
                this.leapMonthPatterns[i] = leapMonthPattern;
            }
        }
        this.shortYearNames = arrays.get("cyclicNameSets/years/format/abbreviated");
        this.shortZodiacNames = arrays.get("cyclicNameSets/zodiacs/format/abbreviated");
        this.requestedLocale = uLocale;
        ICUResourceBundle rb = UResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudt63b", uLocale);
        this.localPatternChars = patternChars;
        ULocale uloc = rb.getULocale();
        setLocale(uloc, uloc);
        this.capitalization = new HashMap();
        boolean[] noTransforms = {false, false};
        CapitalizationContextUsage[] allUsages = CapitalizationContextUsage.values();
        int i2 = 0;
        for (int length = allUsages.length; i2 < length; length = length) {
            this.capitalization.put(allUsages[i2], noTransforms);
            i2++;
            ULocale uLocale2 = desiredLocale;
        }
        try {
            contextTransformsBundle = rb.getWithFallback("contextTransforms");
        } catch (MissingResourceException e) {
            contextTransformsBundle = null;
        }
        if (contextTransformsBundle != null) {
            UResourceBundleIterator ctIterator = contextTransformsBundle.getIterator();
            while (ctIterator.hasNext()) {
                UResourceBundle contextTransformUsage = ctIterator.next();
                UResourceBundleIterator ctIterator2 = ctIterator;
                int[] intVector = contextTransformUsage.getIntVector();
                UResourceBundle contextTransformsBundle2 = contextTransformsBundle;
                String[] snWeekdays2 = snWeekdays;
                if (intVector.length >= 2) {
                    String usageKey = contextTransformUsage.getKey();
                    CapitalizationContextUsage usage = contextUsageTypeMap.get(usageKey);
                    if (usage != null) {
                        String str = usageKey;
                        ssWeekdays = ssWeekdays2;
                        boolean[] transforms = new boolean[2];
                        transforms[0] = intVector[0] != 0;
                        transforms[1] = intVector[1] != 0;
                        this.capitalization.put(usage, transforms);
                    } else {
                        ssWeekdays = ssWeekdays2;
                    }
                } else {
                    ssWeekdays = ssWeekdays2;
                }
                ssWeekdays2 = ssWeekdays;
                ctIterator = ctIterator2;
                contextTransformsBundle = contextTransformsBundle2;
                snWeekdays = snWeekdays2;
            }
            UResourceBundle uResourceBundle = contextTransformsBundle;
            String[] strArr12 = snWeekdays;
            String[] strArr13 = ssWeekdays2;
        } else {
            String[] strArr14 = snWeekdays;
            String[] strArr15 = ssWeekdays2;
        }
        UResourceBundle contextTransformsBundle3 = NumberingSystem.getInstance(desiredLocale);
        try {
            setTimeSeparatorString(rb.getStringWithFallback("NumberElements/" + (contextTransformsBundle3 == null ? "latn" : contextTransformsBundle3.getName()) + "/symbols/timeSeparator"));
        } catch (MissingResourceException e2) {
            setTimeSeparatorString(DEFAULT_TIME_SEPARATOR);
        }
    }

    private static final boolean arrayOfArrayEquals(Object[][] aa1, Object[][] aa2) {
        if (aa1 == aa2) {
            return true;
        }
        if (aa1 == null || aa2 == null || aa1.length != aa2.length) {
            return false;
        }
        boolean equal = true;
        int i = 0;
        while (i < aa1.length && (equal = Utility.arrayEquals(aa1[i], aa2[i]))) {
            i++;
        }
        return equal;
    }

    private String[] loadDayPeriodStrings(Map<String, String> resourceMap) {
        String[] strings = new String[DAY_PERIOD_KEYS.length];
        if (resourceMap != null) {
            int i = 0;
            while (true) {
                String[] strArr = DAY_PERIOD_KEYS;
                if (i >= strArr.length) {
                    break;
                }
                strings[i] = resourceMap.get(strArr[i]);
                i++;
            }
        }
        return strings;
    }

    private final String[] duplicate(String[] srcArray) {
        return (String[]) srcArray.clone();
    }

    private final String[][] duplicate(String[][] srcArray) {
        String[][] aCopy = new String[srcArray.length][];
        for (int i = 0; i < srcArray.length; i++) {
            aCopy[i] = duplicate(srcArray[i]);
        }
        return aCopy;
    }

    public DateFormatSymbols(Calendar cal, Locale locale) {
        this.eras = null;
        this.eraNames = null;
        this.narrowEras = null;
        this.months = null;
        this.shortMonths = null;
        this.narrowMonths = null;
        this.standaloneMonths = null;
        this.standaloneShortMonths = null;
        this.standaloneNarrowMonths = null;
        this.weekdays = null;
        this.shortWeekdays = null;
        this.shorterWeekdays = null;
        this.narrowWeekdays = null;
        this.standaloneWeekdays = null;
        this.standaloneShortWeekdays = null;
        this.standaloneShorterWeekdays = null;
        this.standaloneNarrowWeekdays = null;
        this.ampms = null;
        this.ampmsNarrow = null;
        this.timeSeparator = null;
        this.shortQuarters = null;
        this.quarters = null;
        this.standaloneShortQuarters = null;
        this.standaloneQuarters = null;
        this.leapMonthPatterns = null;
        this.shortYearNames = null;
        this.shortZodiacNames = null;
        this.zoneStrings = null;
        this.localPatternChars = null;
        this.abbreviatedDayPeriods = null;
        this.wideDayPeriods = null;
        this.narrowDayPeriods = null;
        this.standaloneAbbreviatedDayPeriods = null;
        this.standaloneWideDayPeriods = null;
        this.standaloneNarrowDayPeriods = null;
        this.capitalization = null;
        initializeData(ULocale.forLocale(locale), cal.getType());
    }

    public DateFormatSymbols(Calendar cal, ULocale locale) {
        this.eras = null;
        this.eraNames = null;
        this.narrowEras = null;
        this.months = null;
        this.shortMonths = null;
        this.narrowMonths = null;
        this.standaloneMonths = null;
        this.standaloneShortMonths = null;
        this.standaloneNarrowMonths = null;
        this.weekdays = null;
        this.shortWeekdays = null;
        this.shorterWeekdays = null;
        this.narrowWeekdays = null;
        this.standaloneWeekdays = null;
        this.standaloneShortWeekdays = null;
        this.standaloneShorterWeekdays = null;
        this.standaloneNarrowWeekdays = null;
        this.ampms = null;
        this.ampmsNarrow = null;
        this.timeSeparator = null;
        this.shortQuarters = null;
        this.quarters = null;
        this.standaloneShortQuarters = null;
        this.standaloneQuarters = null;
        this.leapMonthPatterns = null;
        this.shortYearNames = null;
        this.shortZodiacNames = null;
        this.zoneStrings = null;
        this.localPatternChars = null;
        this.abbreviatedDayPeriods = null;
        this.wideDayPeriods = null;
        this.narrowDayPeriods = null;
        this.standaloneAbbreviatedDayPeriods = null;
        this.standaloneWideDayPeriods = null;
        this.standaloneNarrowDayPeriods = null;
        this.capitalization = null;
        initializeData(locale, cal.getType());
    }

    public DateFormatSymbols(Class<? extends Calendar> calendarClass, Locale locale) {
        this(calendarClass, ULocale.forLocale(locale));
    }

    public DateFormatSymbols(Class<? extends Calendar> calendarClass, ULocale locale) {
        this.eras = null;
        this.eraNames = null;
        this.narrowEras = null;
        this.months = null;
        this.shortMonths = null;
        this.narrowMonths = null;
        this.standaloneMonths = null;
        this.standaloneShortMonths = null;
        this.standaloneNarrowMonths = null;
        this.weekdays = null;
        this.shortWeekdays = null;
        this.shorterWeekdays = null;
        this.narrowWeekdays = null;
        this.standaloneWeekdays = null;
        this.standaloneShortWeekdays = null;
        this.standaloneShorterWeekdays = null;
        this.standaloneNarrowWeekdays = null;
        this.ampms = null;
        this.ampmsNarrow = null;
        this.timeSeparator = null;
        this.shortQuarters = null;
        this.quarters = null;
        this.standaloneShortQuarters = null;
        this.standaloneQuarters = null;
        this.leapMonthPatterns = null;
        this.shortYearNames = null;
        this.shortZodiacNames = null;
        this.zoneStrings = null;
        this.localPatternChars = null;
        this.abbreviatedDayPeriods = null;
        this.wideDayPeriods = null;
        this.narrowDayPeriods = null;
        this.standaloneAbbreviatedDayPeriods = null;
        this.standaloneWideDayPeriods = null;
        this.standaloneNarrowDayPeriods = null;
        this.capitalization = null;
        String fullName = calendarClass.getName();
        String className = fullName.substring(fullName.lastIndexOf(46) + 1);
        String calType = null;
        String[][] strArr = CALENDAR_CLASSES;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            String[] calClassInfo = strArr[i];
            if (calClassInfo[0].equals(className)) {
                calType = calClassInfo[1];
                break;
            }
            i++;
        }
        initializeData(locale, calType == null ? className.replaceAll("Calendar", "").toLowerCase(Locale.ENGLISH) : calType);
    }

    public DateFormatSymbols(ResourceBundle bundle, Locale locale) {
        this(bundle, ULocale.forLocale(locale));
    }

    public DateFormatSymbols(ResourceBundle bundle, ULocale locale) {
        this.eras = null;
        this.eraNames = null;
        this.narrowEras = null;
        this.months = null;
        this.shortMonths = null;
        this.narrowMonths = null;
        this.standaloneMonths = null;
        this.standaloneShortMonths = null;
        this.standaloneNarrowMonths = null;
        this.weekdays = null;
        this.shortWeekdays = null;
        this.shorterWeekdays = null;
        this.narrowWeekdays = null;
        this.standaloneWeekdays = null;
        this.standaloneShortWeekdays = null;
        this.standaloneShorterWeekdays = null;
        this.standaloneNarrowWeekdays = null;
        this.ampms = null;
        this.ampmsNarrow = null;
        this.timeSeparator = null;
        this.shortQuarters = null;
        this.quarters = null;
        this.standaloneShortQuarters = null;
        this.standaloneQuarters = null;
        this.leapMonthPatterns = null;
        this.shortYearNames = null;
        this.shortZodiacNames = null;
        this.zoneStrings = null;
        this.localPatternChars = null;
        this.abbreviatedDayPeriods = null;
        this.wideDayPeriods = null;
        this.narrowDayPeriods = null;
        this.standaloneAbbreviatedDayPeriods = null;
        this.standaloneWideDayPeriods = null;
        this.standaloneNarrowDayPeriods = null;
        this.capitalization = null;
        initializeData(locale, (ICUResourceBundle) bundle, CalendarUtil.getCalendarType(locale));
    }

    @Deprecated
    public static ResourceBundle getDateFormatBundle(Class<? extends Calendar> cls, Locale locale) throws MissingResourceException {
        return null;
    }

    @Deprecated
    public static ResourceBundle getDateFormatBundle(Class<? extends Calendar> cls, ULocale locale) throws MissingResourceException {
        return null;
    }

    @Deprecated
    public static ResourceBundle getDateFormatBundle(Calendar cal, Locale locale) throws MissingResourceException {
        return null;
    }

    @Deprecated
    public static ResourceBundle getDateFormatBundle(Calendar cal, ULocale locale) throws MissingResourceException {
        return null;
    }

    public final ULocale getLocale(ULocale.Type type) {
        return type == ULocale.ACTUAL_LOCALE ? this.actualLocale : this.validLocale;
    }

    /* access modifiers changed from: package-private */
    public final void setLocale(ULocale valid, ULocale actual) {
        boolean z = true;
        boolean z2 = valid == null;
        if (actual != null) {
            z = false;
        }
        if (z2 == z) {
            this.validLocale = valid;
            this.actualLocale = actual;
            return;
        }
        throw new IllegalArgumentException();
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
    }
}
