package com.ibm.icu.text;

import com.ibm.icu.impl.ICUCache;
import com.ibm.icu.impl.ICUResourceBundle;
import com.ibm.icu.impl.SimpleCache;
import com.ibm.icu.impl.UResource;
import com.ibm.icu.text.DateIntervalFormat;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.Freezable;
import com.ibm.icu.util.ICUCloneNotSupportedException;
import com.ibm.icu.util.ICUException;
import com.ibm.icu.util.ULocale;
import com.ibm.icu.util.UResourceBundle;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.Set;

public class DateIntervalInfo implements Cloneable, Freezable<DateIntervalInfo>, Serializable {
    static final String[] CALENDAR_FIELD_TO_PATTERN_LETTER = {"G", DateFormat.YEAR, DateFormat.NUM_MONTH, "w", "W", DateFormat.DAY, "D", DateFormat.ABBR_WEEKDAY, "F", "a", "h", DateFormat.HOUR24, DateFormat.MINUTE, DateFormat.SECOND, "S", DateFormat.ABBR_SPECIFIC_TZ, " ", "Y", "e", "u", "g", "A", " ", " "};
    /* access modifiers changed from: private */
    public static String CALENDAR_KEY = "calendar";
    private static final ICUCache<String, DateIntervalInfo> DIICACHE = new SimpleCache();
    private static String EARLIEST_FIRST_PREFIX = "earliestFirst:";
    private static String FALLBACK_STRING = "fallback";
    /* access modifiers changed from: private */
    public static String INTERVAL_FORMATS_KEY = "intervalFormats";
    private static String LATEST_FIRST_PREFIX = "latestFirst:";
    private static final int MINIMUM_SUPPORTED_CALENDAR_FIELD = 13;
    static final int currentSerialVersion = 1;
    private static final long serialVersionUID = 1;
    private String fFallbackIntervalPattern;
    private boolean fFirstDateInPtnIsLaterDate;
    /* access modifiers changed from: private */
    public Map<String, Map<String, PatternInfo>> fIntervalPatterns;
    private transient boolean fIntervalPatternsReadOnly;
    private volatile transient boolean frozen;

    public static final class PatternInfo implements Cloneable, Serializable {
        static final int currentSerialVersion = 1;
        private static final long serialVersionUID = 1;
        private final boolean fFirstDateInPtnIsLaterDate;
        private final String fIntervalPatternFirstPart;
        private final String fIntervalPatternSecondPart;

        public PatternInfo(String firstPart, String secondPart, boolean firstDateInPtnIsLaterDate) {
            this.fIntervalPatternFirstPart = firstPart;
            this.fIntervalPatternSecondPart = secondPart;
            this.fFirstDateInPtnIsLaterDate = firstDateInPtnIsLaterDate;
        }

        public String getFirstPart() {
            return this.fIntervalPatternFirstPart;
        }

        public String getSecondPart() {
            return this.fIntervalPatternSecondPart;
        }

        public boolean firstDateInPtnIsLaterDate() {
            return this.fFirstDateInPtnIsLaterDate;
        }

        public boolean equals(Object a) {
            if (!(a instanceof PatternInfo)) {
                return false;
            }
            PatternInfo patternInfo = (PatternInfo) a;
            if (!Objects.equals(this.fIntervalPatternFirstPart, patternInfo.fIntervalPatternFirstPart) || !Objects.equals(this.fIntervalPatternSecondPart, patternInfo.fIntervalPatternSecondPart) || this.fFirstDateInPtnIsLaterDate != patternInfo.fFirstDateInPtnIsLaterDate) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            String str = this.fIntervalPatternFirstPart;
            int hash = str != null ? str.hashCode() : 0;
            String str2 = this.fIntervalPatternSecondPart;
            if (str2 != null) {
                hash ^= str2.hashCode();
            }
            if (this.fFirstDateInPtnIsLaterDate) {
                return ~hash;
            }
            return hash;
        }

        public String toString() {
            return "{first=«" + this.fIntervalPatternFirstPart + "», second=«" + this.fIntervalPatternSecondPart + "», reversed:" + this.fFirstDateInPtnIsLaterDate + "}";
        }
    }

    @Deprecated
    public DateIntervalInfo() {
        this.fFirstDateInPtnIsLaterDate = false;
        this.fIntervalPatterns = null;
        this.frozen = false;
        this.fIntervalPatternsReadOnly = false;
        this.fIntervalPatterns = new HashMap();
        this.fFallbackIntervalPattern = "{0} – {1}";
    }

    public DateIntervalInfo(ULocale locale) {
        this.fFirstDateInPtnIsLaterDate = false;
        this.fIntervalPatterns = null;
        this.frozen = false;
        this.fIntervalPatternsReadOnly = false;
        initializeData(locale);
    }

    public DateIntervalInfo(Locale locale) {
        this(ULocale.forLocale(locale));
    }

    private void initializeData(ULocale locale) {
        String key = locale.toString();
        ICUCache<String, DateIntervalInfo> iCUCache = DIICACHE;
        DateIntervalInfo dii = (DateIntervalInfo) iCUCache.get(key);
        if (dii == null) {
            setup(locale);
            this.fIntervalPatternsReadOnly = true;
            iCUCache.put(key, ((DateIntervalInfo) clone()).freeze());
            return;
        }
        initializeFromReadOnlyPatterns(dii);
    }

    private void initializeFromReadOnlyPatterns(DateIntervalInfo dii) {
        this.fFallbackIntervalPattern = dii.fFallbackIntervalPattern;
        this.fFirstDateInPtnIsLaterDate = dii.fFirstDateInPtnIsLaterDate;
        this.fIntervalPatterns = dii.fIntervalPatterns;
        this.fIntervalPatternsReadOnly = true;
    }

    private static final class DateIntervalSink extends UResource.Sink {
        private static final String ACCEPTED_PATTERN_LETTERS = "yMdahHms";
        private static final String DATE_INTERVAL_PATH_PREFIX = ("/LOCALE/" + DateIntervalInfo.CALENDAR_KEY + "/");
        private static final String DATE_INTERVAL_PATH_SUFFIX = ("/" + DateIntervalInfo.INTERVAL_FORMATS_KEY);
        DateIntervalInfo dateIntervalInfo;
        String nextCalendarType;

        public DateIntervalSink(DateIntervalInfo dateIntervalInfo2) {
            this.dateIntervalInfo = dateIntervalInfo2;
        }

        public void put(UResource.Key key, UResource.Value value, boolean noFallback) {
            UResource.Table dateIntervalData = value.getTable();
            for (int i = 0; dateIntervalData.getKeyAndValue(i, key, value); i++) {
                if (key.contentEquals(DateIntervalInfo.INTERVAL_FORMATS_KEY)) {
                    if (value.getType() == 3) {
                        this.nextCalendarType = getCalendarTypeFromPath(value.getAliasString());
                        return;
                    } else if (value.getType() == 2) {
                        UResource.Table skeletonData = value.getTable();
                        for (int j = 0; skeletonData.getKeyAndValue(j, key, value); j++) {
                            if (value.getType() == 2) {
                                processSkeletonTable(key, value);
                            }
                        }
                        return;
                    }
                }
            }
        }

        public void processSkeletonTable(UResource.Key key, UResource.Value value) {
            CharSequence patternLetter;
            String currentSkeleton = key.toString();
            UResource.Table patternData = value.getTable();
            for (int k = 0; patternData.getKeyAndValue(k, key, value); k++) {
                if (value.getType() == 0 && (patternLetter = validateAndProcessPatternLetter(key)) != null) {
                    setIntervalPatternIfAbsent(currentSkeleton, patternLetter.toString(), value);
                }
            }
        }

        public String getAndResetNextCalendarType() {
            String tmpCalendarType = this.nextCalendarType;
            this.nextCalendarType = null;
            return tmpCalendarType;
        }

        private String getCalendarTypeFromPath(String path) {
            String str = DATE_INTERVAL_PATH_PREFIX;
            if (path.startsWith(str)) {
                String str2 = DATE_INTERVAL_PATH_SUFFIX;
                if (path.endsWith(str2)) {
                    return path.substring(str.length(), path.length() - str2.length());
                }
            }
            throw new ICUException("Malformed 'intervalFormat' alias path: " + path);
        }

        private CharSequence validateAndProcessPatternLetter(CharSequence patternLetter) {
            if (patternLetter.length() != 1) {
                return null;
            }
            char letter = patternLetter.charAt(0);
            if (ACCEPTED_PATTERN_LETTERS.indexOf(letter) < 0) {
                return null;
            }
            if (letter == DateIntervalInfo.CALENDAR_FIELD_TO_PATTERN_LETTER[11].charAt(0)) {
                return DateIntervalInfo.CALENDAR_FIELD_TO_PATTERN_LETTER[10];
            }
            return patternLetter;
        }

        private void setIntervalPatternIfAbsent(String currentSkeleton, String lrgDiffCalUnit, UResource.Value intervalPattern) {
            Map<String, PatternInfo> patternsOfOneSkeleton = (Map) this.dateIntervalInfo.fIntervalPatterns.get(currentSkeleton);
            if (patternsOfOneSkeleton == null || !patternsOfOneSkeleton.containsKey(lrgDiffCalUnit)) {
                PatternInfo unused = this.dateIntervalInfo.setIntervalPatternInternally(currentSkeleton, lrgDiffCalUnit, intervalPattern.toString());
            }
        }
    }

    private void setup(ULocale locale) {
        this.fIntervalPatterns = new HashMap(19);
        this.fFallbackIntervalPattern = "{0} – {1}";
        try {
            String calendarTypeToUse = locale.getKeywordValue("calendar");
            if (calendarTypeToUse == null) {
                calendarTypeToUse = Calendar.getKeywordValuesForLocale("calendar", locale, true)[0];
            }
            if (calendarTypeToUse == null) {
                calendarTypeToUse = "gregorian";
            }
            DateIntervalSink sink = new DateIntervalSink(this);
            ICUResourceBundle resource = UResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudt63b", locale);
            setFallbackIntervalPattern(resource.getStringWithFallback(CALENDAR_KEY + "/" + calendarTypeToUse + "/" + INTERVAL_FORMATS_KEY + "/" + FALLBACK_STRING));
            Set<String> loadedCalendarTypes = new HashSet<>();
            while (calendarTypeToUse != null) {
                if (!loadedCalendarTypes.contains(calendarTypeToUse)) {
                    loadedCalendarTypes.add(calendarTypeToUse);
                    resource.getAllItemsWithFallback(CALENDAR_KEY + "/" + calendarTypeToUse, sink);
                    calendarTypeToUse = sink.getAndResetNextCalendarType();
                } else {
                    throw new ICUException("Loop in calendar type fallback: " + calendarTypeToUse);
                }
            }
        } catch (MissingResourceException e) {
        }
    }

    private static int splitPatternInto2Part(String intervalPattern) {
        boolean inQuote = false;
        char prevCh = 0;
        int count = 0;
        int[] patternRepeated = new int[58];
        boolean foundRepetition = false;
        int i = 0;
        while (true) {
            if (i >= intervalPattern.length()) {
                break;
            }
            char ch = intervalPattern.charAt(i);
            if (ch != prevCh && count > 0) {
                if (patternRepeated[prevCh - 'A'] != 0) {
                    foundRepetition = true;
                    break;
                }
                patternRepeated[prevCh - 'A'] = 1;
                count = 0;
            }
            if (ch == '\'') {
                if (i + 1 >= intervalPattern.length() || intervalPattern.charAt(i + 1) != '\'') {
                    inQuote = !inQuote;
                } else {
                    i++;
                }
            } else if (!inQuote && ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))) {
                prevCh = ch;
                count++;
            }
            i++;
        }
        if (count > 0 && !foundRepetition && patternRepeated[prevCh - 'A'] == 0) {
            count = 0;
        }
        return i - count;
    }

    public void setIntervalPattern(String skeleton, int lrgDiffCalUnit, String intervalPattern) {
        if (this.frozen) {
            throw new UnsupportedOperationException("no modification is allowed after DII is frozen");
        } else if (lrgDiffCalUnit <= 13) {
            if (this.fIntervalPatternsReadOnly) {
                this.fIntervalPatterns = cloneIntervalPatterns(this.fIntervalPatterns);
                this.fIntervalPatternsReadOnly = false;
            }
            String[] strArr = CALENDAR_FIELD_TO_PATTERN_LETTER;
            PatternInfo ptnInfo = setIntervalPatternInternally(skeleton, strArr[lrgDiffCalUnit], intervalPattern);
            if (lrgDiffCalUnit == 11) {
                setIntervalPattern(skeleton, strArr[9], ptnInfo);
                setIntervalPattern(skeleton, strArr[10], ptnInfo);
            } else if (lrgDiffCalUnit == 5 || lrgDiffCalUnit == 7) {
                setIntervalPattern(skeleton, strArr[5], ptnInfo);
            }
        } else {
            throw new IllegalArgumentException("calendar field is larger than MINIMUM_SUPPORTED_CALENDAR_FIELD");
        }
    }

    /* access modifiers changed from: private */
    public PatternInfo setIntervalPatternInternally(String skeleton, String lrgDiffCalUnit, String intervalPattern) {
        Map<String, PatternInfo> patternsOfOneSkeleton = this.fIntervalPatterns.get(skeleton);
        boolean emptyHash = false;
        if (patternsOfOneSkeleton == null) {
            patternsOfOneSkeleton = new HashMap<>();
            emptyHash = true;
        }
        boolean order = this.fFirstDateInPtnIsLaterDate;
        if (intervalPattern.startsWith(LATEST_FIRST_PREFIX)) {
            order = true;
            intervalPattern = intervalPattern.substring(LATEST_FIRST_PREFIX.length(), intervalPattern.length());
        } else if (intervalPattern.startsWith(EARLIEST_FIRST_PREFIX)) {
            order = false;
            intervalPattern = intervalPattern.substring(EARLIEST_FIRST_PREFIX.length(), intervalPattern.length());
        }
        PatternInfo itvPtnInfo = genPatternInfo(intervalPattern, order);
        patternsOfOneSkeleton.put(lrgDiffCalUnit, itvPtnInfo);
        if (emptyHash) {
            this.fIntervalPatterns.put(skeleton, patternsOfOneSkeleton);
        }
        return itvPtnInfo;
    }

    private void setIntervalPattern(String skeleton, String lrgDiffCalUnit, PatternInfo ptnInfo) {
        this.fIntervalPatterns.get(skeleton).put(lrgDiffCalUnit, ptnInfo);
    }

    @Deprecated
    public static PatternInfo genPatternInfo(String intervalPattern, boolean laterDateFirst) {
        int splitPoint = splitPatternInto2Part(intervalPattern);
        String firstPart = intervalPattern.substring(0, splitPoint);
        String secondPart = null;
        if (splitPoint < intervalPattern.length()) {
            secondPart = intervalPattern.substring(splitPoint, intervalPattern.length());
        }
        return new PatternInfo(firstPart, secondPart, laterDateFirst);
    }

    public PatternInfo getIntervalPattern(String skeleton, int field) {
        PatternInfo intervalPattern;
        if (field <= 13) {
            Map<String, PatternInfo> patternsOfOneSkeleton = this.fIntervalPatterns.get(skeleton);
            if (patternsOfOneSkeleton == null || (intervalPattern = patternsOfOneSkeleton.get(CALENDAR_FIELD_TO_PATTERN_LETTER[field])) == null) {
                return null;
            }
            return intervalPattern;
        }
        throw new IllegalArgumentException("no support for field less than SECOND");
    }

    public String getFallbackIntervalPattern() {
        return this.fFallbackIntervalPattern;
    }

    public void setFallbackIntervalPattern(String fallbackPattern) {
        if (!this.frozen) {
            int firstPatternIndex = fallbackPattern.indexOf("{0}");
            int secondPatternIndex = fallbackPattern.indexOf("{1}");
            if (firstPatternIndex == -1 || secondPatternIndex == -1) {
                throw new IllegalArgumentException("no pattern {0} or pattern {1} in fallbackPattern");
            }
            if (firstPatternIndex > secondPatternIndex) {
                this.fFirstDateInPtnIsLaterDate = true;
            }
            this.fFallbackIntervalPattern = fallbackPattern;
            return;
        }
        throw new UnsupportedOperationException("no modification is allowed after DII is frozen");
    }

    public boolean getDefaultOrder() {
        return this.fFirstDateInPtnIsLaterDate;
    }

    public Object clone() {
        if (this.frozen) {
            return this;
        }
        return cloneUnfrozenDII();
    }

    private Object cloneUnfrozenDII() {
        try {
            DateIntervalInfo other = (DateIntervalInfo) super.clone();
            other.fFallbackIntervalPattern = this.fFallbackIntervalPattern;
            other.fFirstDateInPtnIsLaterDate = this.fFirstDateInPtnIsLaterDate;
            if (this.fIntervalPatternsReadOnly) {
                other.fIntervalPatterns = this.fIntervalPatterns;
                other.fIntervalPatternsReadOnly = true;
            } else {
                other.fIntervalPatterns = cloneIntervalPatterns(this.fIntervalPatterns);
                other.fIntervalPatternsReadOnly = false;
            }
            other.frozen = false;
            return other;
        } catch (CloneNotSupportedException e) {
            throw new ICUCloneNotSupportedException("clone is not supported", e);
        }
    }

    private static Map<String, Map<String, PatternInfo>> cloneIntervalPatterns(Map<String, Map<String, PatternInfo>> patterns) {
        Map<String, Map<String, PatternInfo>> result = new HashMap<>();
        for (Map.Entry<String, Map<String, PatternInfo>> skeletonEntry : patterns.entrySet()) {
            String skeleton = skeletonEntry.getKey();
            Map<String, PatternInfo> oneSetPtn = new HashMap<>();
            for (Map.Entry<String, PatternInfo> calEntry : skeletonEntry.getValue().entrySet()) {
                oneSetPtn.put(calEntry.getKey(), calEntry.getValue());
            }
            result.put(skeleton, oneSetPtn);
        }
        return result;
    }

    public boolean isFrozen() {
        return this.frozen;
    }

    public DateIntervalInfo freeze() {
        this.fIntervalPatternsReadOnly = true;
        this.frozen = true;
        return this;
    }

    public DateIntervalInfo cloneAsThawed() {
        return (DateIntervalInfo) cloneUnfrozenDII();
    }

    static void parseSkeleton(String skeleton, int[] skeletonFieldWidth) {
        for (int i = 0; i < skeleton.length(); i++) {
            int charAt = skeleton.charAt(i) - 'A';
            skeletonFieldWidth[charAt] = skeletonFieldWidth[charAt] + 1;
        }
    }

    private static boolean stringNumeric(int fieldWidth, int anotherFieldWidth, char patternLetter) {
        if (patternLetter != 'M') {
            return false;
        }
        if (fieldWidth <= 2 && anotherFieldWidth > 2) {
            return true;
        }
        if (fieldWidth <= 2 || anotherFieldWidth > 2) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public DateIntervalFormat.BestMatchInfo getBestSkeleton(String inputSkeleton) {
        String inputSkeleton2;
        int[] skeletonFieldWidth;
        String inputSkeleton3 = inputSkeleton;
        String bestSkeleton = inputSkeleton;
        int[] inputSkeletonFieldWidth = new int[58];
        int[] skeletonFieldWidth2 = new int[58];
        boolean replaceZWithV = false;
        if (inputSkeleton3.indexOf(122) != -1) {
            inputSkeleton3 = inputSkeleton3.replace('z', 'v');
            replaceZWithV = true;
        }
        parseSkeleton(inputSkeleton3, inputSkeletonFieldWidth);
        int bestDistance = Integer.MAX_VALUE;
        int bestFieldDifference = 0;
        Iterator<String> it = this.fIntervalPatterns.keySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                String str = bestSkeleton;
                int[] iArr = skeletonFieldWidth2;
                break;
            }
            String skeleton = it.next();
            for (int i = 0; i < skeletonFieldWidth2.length; i++) {
                skeletonFieldWidth2[i] = 0;
            }
            parseSkeleton(skeleton, skeletonFieldWidth2);
            int distance = 0;
            int fieldDifference = 1;
            int i2 = 0;
            while (true) {
                inputSkeleton2 = inputSkeleton3;
                if (i2 >= inputSkeletonFieldWidth.length) {
                    break;
                }
                int inputFieldWidth = inputSkeletonFieldWidth[i2];
                String bestSkeleton2 = bestSkeleton;
                int fieldWidth = skeletonFieldWidth2[i2];
                if (inputFieldWidth == fieldWidth) {
                    skeletonFieldWidth = skeletonFieldWidth2;
                } else if (inputFieldWidth == 0) {
                    fieldDifference = -1;
                    distance += 4096;
                    skeletonFieldWidth = skeletonFieldWidth2;
                } else if (fieldWidth == 0) {
                    fieldDifference = -1;
                    distance += 4096;
                    skeletonFieldWidth = skeletonFieldWidth2;
                } else {
                    skeletonFieldWidth = skeletonFieldWidth2;
                    if (stringNumeric(inputFieldWidth, fieldWidth, (char) (i2 + 65))) {
                        distance += 256;
                    } else {
                        distance += Math.abs(inputFieldWidth - fieldWidth);
                    }
                }
                i2++;
                inputSkeleton3 = inputSkeleton2;
                bestSkeleton = bestSkeleton2;
                skeletonFieldWidth2 = skeletonFieldWidth;
            }
            String bestSkeleton3 = bestSkeleton;
            int[] skeletonFieldWidth3 = skeletonFieldWidth2;
            if (distance < bestDistance) {
                bestDistance = distance;
                bestFieldDifference = fieldDifference;
                bestSkeleton = skeleton;
            } else {
                bestSkeleton = bestSkeleton3;
            }
            if (distance == 0) {
                bestFieldDifference = 0;
                break;
            }
            inputSkeleton3 = inputSkeleton2;
            skeletonFieldWidth2 = skeletonFieldWidth3;
        }
        if (replaceZWithV && bestFieldDifference != -1) {
            bestFieldDifference = 2;
        }
        return new DateIntervalFormat.BestMatchInfo(bestSkeleton, bestFieldDifference);
    }

    public boolean equals(Object a) {
        if (a instanceof DateIntervalInfo) {
            return this.fIntervalPatterns.equals(((DateIntervalInfo) a).fIntervalPatterns);
        }
        return false;
    }

    public int hashCode() {
        return this.fIntervalPatterns.hashCode();
    }

    @Deprecated
    public Map<String, Set<String>> getPatterns() {
        LinkedHashMap<String, Set<String>> result = new LinkedHashMap<>();
        for (Map.Entry<String, Map<String, PatternInfo>> entry : this.fIntervalPatterns.entrySet()) {
            result.put(entry.getKey(), new LinkedHashSet(entry.getValue().keySet()));
        }
        return result;
    }

    @Deprecated
    public Map<String, Map<String, PatternInfo>> getRawPatterns() {
        LinkedHashMap<String, Map<String, PatternInfo>> result = new LinkedHashMap<>();
        for (Map.Entry<String, Map<String, PatternInfo>> entry : this.fIntervalPatterns.entrySet()) {
            result.put(entry.getKey(), new LinkedHashMap(entry.getValue()));
        }
        return result;
    }
}
