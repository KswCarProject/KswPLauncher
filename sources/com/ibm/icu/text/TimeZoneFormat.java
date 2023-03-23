package com.ibm.icu.text;

import com.ibm.icu.impl.ICUResourceBundle;
import com.ibm.icu.impl.PatternProps;
import com.ibm.icu.impl.SoftCache;
import com.ibm.icu.impl.TZDBTimeZoneNames;
import com.ibm.icu.impl.TextTrieMap;
import com.ibm.icu.impl.TimeZoneGenericNames;
import com.ibm.icu.impl.TimeZoneNamesImpl;
import com.ibm.icu.impl.ZoneMeta;
import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.TimeZoneNames;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.Freezable;
import com.ibm.icu.util.Output;
import com.ibm.icu.util.TimeZone;
import com.ibm.icu.util.ULocale;
import com.wits.ksw.settings.TxzMessage;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;

public class TimeZoneFormat extends UFormat implements Freezable<TimeZoneFormat>, Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final EnumSet<TimeZoneGenericNames.GenericNameType> ALL_GENERIC_NAME_TYPES = EnumSet.of(TimeZoneGenericNames.GenericNameType.LOCATION, TimeZoneGenericNames.GenericNameType.LONG, TimeZoneGenericNames.GenericNameType.SHORT);
    private static final EnumSet<TimeZoneNames.NameType> ALL_SIMPLE_NAME_TYPES = EnumSet.of(TimeZoneNames.NameType.LONG_STANDARD, TimeZoneNames.NameType.LONG_DAYLIGHT, TimeZoneNames.NameType.SHORT_STANDARD, TimeZoneNames.NameType.SHORT_DAYLIGHT, TimeZoneNames.NameType.EXEMPLAR_LOCATION);
    private static final String[] ALT_GMT_STRINGS = {DEFAULT_GMT_ZERO, "UTC", "UT"};
    private static final String ASCII_DIGITS = "0123456789";
    private static final String[] DEFAULT_GMT_DIGITS = {TxzMessage.TXZ_DISMISS, TxzMessage.TXZ_SHOW, "2", "3", "4", "5", "6", "7", "8", "9"};
    private static final char DEFAULT_GMT_OFFSET_SEP = ':';
    private static final String DEFAULT_GMT_PATTERN = "GMT{0}";
    private static final String DEFAULT_GMT_ZERO = "GMT";
    private static final String ISO8601_UTC = "Z";
    private static final int ISO_LOCAL_STYLE_FLAG = 256;
    private static final int ISO_Z_STYLE_FLAG = 128;
    private static final int MAX_OFFSET = 86400000;
    private static final int MAX_OFFSET_HOUR = 23;
    private static final int MAX_OFFSET_MINUTE = 59;
    private static final int MAX_OFFSET_SECOND = 59;
    private static final int MILLIS_PER_HOUR = 3600000;
    private static final int MILLIS_PER_MINUTE = 60000;
    private static final int MILLIS_PER_SECOND = 1000;
    private static final GMTOffsetPatternType[] PARSE_GMT_OFFSET_TYPES = {GMTOffsetPatternType.POSITIVE_HMS, GMTOffsetPatternType.NEGATIVE_HMS, GMTOffsetPatternType.POSITIVE_HM, GMTOffsetPatternType.NEGATIVE_HM, GMTOffsetPatternType.POSITIVE_H, GMTOffsetPatternType.NEGATIVE_H};
    private static volatile TextTrieMap<String> SHORT_ZONE_ID_TRIE = null;
    private static final String TZID_GMT = "Etc/GMT";
    private static final String UNKNOWN_LOCATION = "Unknown";
    private static final int UNKNOWN_OFFSET = Integer.MAX_VALUE;
    private static final String UNKNOWN_SHORT_ZONE_ID = "unk";
    private static final String UNKNOWN_ZONE_ID = "Etc/Unknown";
    private static volatile TextTrieMap<String> ZONE_ID_TRIE = null;
    private static TimeZoneFormatCache _tzfCache = new TimeZoneFormatCache((AnonymousClass1) null);
    private static final ObjectStreamField[] serialPersistentFields = {new ObjectStreamField("_locale", ULocale.class), new ObjectStreamField("_tznames", TimeZoneNames.class), new ObjectStreamField("_gmtPattern", String.class), new ObjectStreamField("_gmtOffsetPatterns", String[].class), new ObjectStreamField("_gmtOffsetDigits", String[].class), new ObjectStreamField("_gmtZeroFormat", String.class), new ObjectStreamField("_parseAllStyles", Boolean.TYPE)};
    private static final long serialVersionUID = 2281246852693575022L;
    private transient boolean _abuttingOffsetHoursAndMinutes;
    private volatile transient boolean _frozen;
    private String[] _gmtOffsetDigits;
    private transient Object[][] _gmtOffsetPatternItems;
    private String[] _gmtOffsetPatterns;
    private String _gmtPattern;
    private transient String _gmtPatternPrefix;
    private transient String _gmtPatternSuffix;
    private String _gmtZeroFormat = DEFAULT_GMT_ZERO;
    private volatile transient TimeZoneGenericNames _gnames;
    private ULocale _locale;
    private boolean _parseAllStyles;
    private boolean _parseTZDBNames;
    private transient String _region;
    private volatile transient TimeZoneNames _tzdbNames;
    private TimeZoneNames _tznames;

    private enum OffsetFields {
        H,
        HM,
        HMS
    }

    public enum ParseOption {
        ALL_STYLES,
        TZ_DATABASE_ABBREVIATIONS
    }

    public enum TimeType {
        UNKNOWN,
        STANDARD,
        DAYLIGHT
    }

    public enum Style {
        GENERIC_LOCATION(1),
        GENERIC_LONG(2),
        GENERIC_SHORT(4),
        SPECIFIC_LONG(8),
        SPECIFIC_SHORT(16),
        LOCALIZED_GMT(32),
        LOCALIZED_GMT_SHORT(64),
        ISO_BASIC_SHORT(128),
        ISO_BASIC_LOCAL_SHORT(256),
        ISO_BASIC_FIXED(128),
        ISO_BASIC_LOCAL_FIXED(256),
        ISO_BASIC_FULL(128),
        ISO_BASIC_LOCAL_FULL(256),
        ISO_EXTENDED_FIXED(128),
        ISO_EXTENDED_LOCAL_FIXED(256),
        ISO_EXTENDED_FULL(128),
        ISO_EXTENDED_LOCAL_FULL(256),
        ZONE_ID(512),
        ZONE_ID_SHORT(1024),
        EXEMPLAR_LOCATION(2048);
        
        final int flag;

        private Style(int flag2) {
            this.flag = flag2;
        }
    }

    public enum GMTOffsetPatternType {
        POSITIVE_HM("+H:mm", DateFormat.HOUR24_MINUTE, true),
        POSITIVE_HMS("+H:mm:ss", DateFormat.HOUR24_MINUTE_SECOND, true),
        NEGATIVE_HM("-H:mm", DateFormat.HOUR24_MINUTE, false),
        NEGATIVE_HMS("-H:mm:ss", DateFormat.HOUR24_MINUTE_SECOND, false),
        POSITIVE_H("+H", DateFormat.HOUR24, true),
        NEGATIVE_H("-H", DateFormat.HOUR24, false);
        
        private String _defaultPattern;
        private boolean _isPositive;
        private String _required;

        private GMTOffsetPatternType(String defaultPattern, String required, boolean isPositive) {
            this._defaultPattern = defaultPattern;
            this._required = required;
            this._isPositive = isPositive;
        }

        /* access modifiers changed from: private */
        public String defaultPattern() {
            return this._defaultPattern;
        }

        /* access modifiers changed from: private */
        public String required() {
            return this._required;
        }

        /* access modifiers changed from: private */
        public boolean isPositive() {
            return this._isPositive;
        }
    }

    protected TimeZoneFormat(ULocale locale) {
        this._locale = locale;
        this._tznames = TimeZoneNames.getInstance(locale);
        String gmtPattern = null;
        String hourFormats = null;
        try {
            ICUResourceBundle bundle = ICUResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudt63b/zone", locale);
            try {
                gmtPattern = bundle.getStringWithFallback("zoneStrings/gmtFormat");
            } catch (MissingResourceException e) {
            }
            try {
                hourFormats = bundle.getStringWithFallback("zoneStrings/hourFormat");
            } catch (MissingResourceException e2) {
            }
            try {
                this._gmtZeroFormat = bundle.getStringWithFallback("zoneStrings/gmtZeroFormat");
            } catch (MissingResourceException e3) {
            }
        } catch (MissingResourceException e4) {
        }
        initGMTPattern(gmtPattern == null ? DEFAULT_GMT_PATTERN : gmtPattern);
        String[] gmtOffsetPatterns = new String[GMTOffsetPatternType.values().length];
        if (hourFormats != null) {
            String[] hourPatterns = hourFormats.split(";", 2);
            gmtOffsetPatterns[GMTOffsetPatternType.POSITIVE_H.ordinal()] = truncateOffsetPattern(hourPatterns[0]);
            gmtOffsetPatterns[GMTOffsetPatternType.POSITIVE_HM.ordinal()] = hourPatterns[0];
            gmtOffsetPatterns[GMTOffsetPatternType.POSITIVE_HMS.ordinal()] = expandOffsetPattern(hourPatterns[0]);
            gmtOffsetPatterns[GMTOffsetPatternType.NEGATIVE_H.ordinal()] = truncateOffsetPattern(hourPatterns[1]);
            gmtOffsetPatterns[GMTOffsetPatternType.NEGATIVE_HM.ordinal()] = hourPatterns[1];
            gmtOffsetPatterns[GMTOffsetPatternType.NEGATIVE_HMS.ordinal()] = expandOffsetPattern(hourPatterns[1]);
        } else {
            for (GMTOffsetPatternType patType : GMTOffsetPatternType.values()) {
                gmtOffsetPatterns[patType.ordinal()] = patType.defaultPattern();
            }
        }
        initGMTOffsetPatterns(gmtOffsetPatterns);
        this._gmtOffsetDigits = DEFAULT_GMT_DIGITS;
        NumberingSystem ns = NumberingSystem.getInstance(locale);
        if (!ns.isAlgorithmic()) {
            this._gmtOffsetDigits = toCodePoints(ns.getDescription());
        }
    }

    public static TimeZoneFormat getInstance(ULocale locale) {
        if (locale != null) {
            return (TimeZoneFormat) _tzfCache.getInstance(locale, locale);
        }
        throw new NullPointerException("locale is null");
    }

    public static TimeZoneFormat getInstance(Locale locale) {
        return getInstance(ULocale.forLocale(locale));
    }

    public TimeZoneNames getTimeZoneNames() {
        return this._tznames;
    }

    private TimeZoneGenericNames getTimeZoneGenericNames() {
        if (this._gnames == null) {
            synchronized (this) {
                if (this._gnames == null) {
                    this._gnames = TimeZoneGenericNames.getInstance(this._locale);
                }
            }
        }
        return this._gnames;
    }

    private TimeZoneNames getTZDBTimeZoneNames() {
        if (this._tzdbNames == null) {
            synchronized (this) {
                if (this._tzdbNames == null) {
                    this._tzdbNames = new TZDBTimeZoneNames(this._locale);
                }
            }
        }
        return this._tzdbNames;
    }

    public TimeZoneFormat setTimeZoneNames(TimeZoneNames tznames) {
        if (!isFrozen()) {
            this._tznames = tznames;
            this._gnames = new TimeZoneGenericNames(this._locale, this._tznames);
            return this;
        }
        throw new UnsupportedOperationException("Attempt to modify frozen object");
    }

    public String getGMTPattern() {
        return this._gmtPattern;
    }

    public TimeZoneFormat setGMTPattern(String pattern) {
        if (!isFrozen()) {
            initGMTPattern(pattern);
            return this;
        }
        throw new UnsupportedOperationException("Attempt to modify frozen object");
    }

    public String getGMTOffsetPattern(GMTOffsetPatternType type) {
        return this._gmtOffsetPatterns[type.ordinal()];
    }

    public TimeZoneFormat setGMTOffsetPattern(GMTOffsetPatternType type, String pattern) {
        if (isFrozen()) {
            throw new UnsupportedOperationException("Attempt to modify frozen object");
        } else if (pattern != null) {
            Object[] parsedItems = parseOffsetPattern(pattern, type.required());
            this._gmtOffsetPatterns[type.ordinal()] = pattern;
            this._gmtOffsetPatternItems[type.ordinal()] = parsedItems;
            checkAbuttingHoursAndMinutes();
            return this;
        } else {
            throw new NullPointerException("Null GMT offset pattern");
        }
    }

    public String getGMTOffsetDigits() {
        StringBuilder buf = new StringBuilder(this._gmtOffsetDigits.length);
        for (String digit : this._gmtOffsetDigits) {
            buf.append(digit);
        }
        return buf.toString();
    }

    public TimeZoneFormat setGMTOffsetDigits(String digits) {
        if (isFrozen()) {
            throw new UnsupportedOperationException("Attempt to modify frozen object");
        } else if (digits != null) {
            String[] digitArray = toCodePoints(digits);
            if (digitArray.length == 10) {
                this._gmtOffsetDigits = digitArray;
                return this;
            }
            throw new IllegalArgumentException("Length of digits must be 10");
        } else {
            throw new NullPointerException("Null GMT offset digits");
        }
    }

    public String getGMTZeroFormat() {
        return this._gmtZeroFormat;
    }

    public TimeZoneFormat setGMTZeroFormat(String gmtZeroFormat) {
        if (isFrozen()) {
            throw new UnsupportedOperationException("Attempt to modify frozen object");
        } else if (gmtZeroFormat == null) {
            throw new NullPointerException("Null GMT zero format");
        } else if (gmtZeroFormat.length() != 0) {
            this._gmtZeroFormat = gmtZeroFormat;
            return this;
        } else {
            throw new IllegalArgumentException("Empty GMT zero format");
        }
    }

    public TimeZoneFormat setDefaultParseOptions(EnumSet<ParseOption> options) {
        this._parseAllStyles = options.contains(ParseOption.ALL_STYLES);
        this._parseTZDBNames = options.contains(ParseOption.TZ_DATABASE_ABBREVIATIONS);
        return this;
    }

    public EnumSet<ParseOption> getDefaultParseOptions() {
        boolean z = this._parseAllStyles;
        if (z && this._parseTZDBNames) {
            return EnumSet.of(ParseOption.ALL_STYLES, ParseOption.TZ_DATABASE_ABBREVIATIONS);
        }
        if (z) {
            return EnumSet.of(ParseOption.ALL_STYLES);
        }
        if (this._parseTZDBNames) {
            return EnumSet.of(ParseOption.TZ_DATABASE_ABBREVIATIONS);
        }
        return EnumSet.noneOf(ParseOption.class);
    }

    public final String formatOffsetISO8601Basic(int offset, boolean useUtcIndicator, boolean isShort, boolean ignoreSeconds) {
        return formatOffsetISO8601(offset, true, useUtcIndicator, isShort, ignoreSeconds);
    }

    public final String formatOffsetISO8601Extended(int offset, boolean useUtcIndicator, boolean isShort, boolean ignoreSeconds) {
        return formatOffsetISO8601(offset, false, useUtcIndicator, isShort, ignoreSeconds);
    }

    public String formatOffsetLocalizedGMT(int offset) {
        return formatOffsetLocalizedGMT(offset, false);
    }

    public String formatOffsetShortLocalizedGMT(int offset) {
        return formatOffsetLocalizedGMT(offset, true);
    }

    public final String format(Style style, TimeZone tz, long date) {
        return format(style, tz, date, (Output<TimeType>) null);
    }

    public String format(Style style, TimeZone tz, long date, Output<TimeType> timeType) {
        String result;
        String result2 = null;
        if (timeType != null) {
            timeType.value = TimeType.UNKNOWN;
        }
        boolean noOffsetFormatFallback = false;
        switch (AnonymousClass1.$SwitchMap$com$ibm$icu$text$TimeZoneFormat$Style[style.ordinal()]) {
            case 1:
                result2 = getTimeZoneGenericNames().getGenericLocationName(ZoneMeta.getCanonicalCLDRID(tz));
                break;
            case 2:
                result2 = getTimeZoneGenericNames().getDisplayName(tz, TimeZoneGenericNames.GenericNameType.LONG, date);
                break;
            case 3:
                result2 = getTimeZoneGenericNames().getDisplayName(tz, TimeZoneGenericNames.GenericNameType.SHORT, date);
                break;
            case 4:
                result2 = formatSpecific(tz, TimeZoneNames.NameType.LONG_STANDARD, TimeZoneNames.NameType.LONG_DAYLIGHT, date, timeType);
                break;
            case 5:
                result2 = formatSpecific(tz, TimeZoneNames.NameType.SHORT_STANDARD, TimeZoneNames.NameType.SHORT_DAYLIGHT, date, timeType);
                break;
            case 6:
                result2 = tz.getID();
                noOffsetFormatFallback = true;
                break;
            case 7:
                result2 = ZoneMeta.getShortID(tz);
                if (result2 == null) {
                    result2 = UNKNOWN_SHORT_ZONE_ID;
                }
                noOffsetFormatFallback = true;
                break;
            case 8:
                result2 = formatExemplarLocation(tz);
                noOffsetFormatFallback = true;
                break;
        }
        if (result2 == null && !noOffsetFormatFallback) {
            int[] offsets = {0, 0};
            tz.getOffset(date, false, offsets);
            int offset = offsets[0] + offsets[1];
            switch (AnonymousClass1.$SwitchMap$com$ibm$icu$text$TimeZoneFormat$Style[style.ordinal()]) {
                case 1:
                case 2:
                case 4:
                case 9:
                    result = formatOffsetLocalizedGMT(offset);
                    break;
                case 3:
                case 5:
                case 10:
                    result = formatOffsetShortLocalizedGMT(offset);
                    break;
                case 11:
                    result = formatOffsetISO8601Basic(offset, true, true, true);
                    break;
                case 12:
                    result = formatOffsetISO8601Basic(offset, false, true, true);
                    break;
                case 13:
                    result = formatOffsetISO8601Basic(offset, true, false, true);
                    break;
                case 14:
                    result = formatOffsetISO8601Basic(offset, false, false, true);
                    break;
                case 15:
                    result = formatOffsetISO8601Basic(offset, true, false, false);
                    break;
                case 16:
                    result = formatOffsetISO8601Basic(offset, false, false, false);
                    break;
                case 17:
                    result = formatOffsetISO8601Extended(offset, true, false, true);
                    break;
                case 18:
                    result = formatOffsetISO8601Extended(offset, false, false, true);
                    break;
                case 19:
                    result = formatOffsetISO8601Extended(offset, true, false, false);
                    break;
                case 20:
                    result = formatOffsetISO8601Extended(offset, false, false, false);
                    break;
                default:
                    throw new AssertionError();
            }
            if (timeType != null) {
                timeType.value = offsets[1] != 0 ? TimeType.DAYLIGHT : TimeType.STANDARD;
            }
        }
        if (result2 != null) {
            return result2;
        }
        throw new AssertionError();
    }

    public final int parseOffsetISO8601(String text, ParsePosition pos) {
        return parseOffsetISO8601(text, pos, false, (Output<Boolean>) null);
    }

    public int parseOffsetLocalizedGMT(String text, ParsePosition pos) {
        return parseOffsetLocalizedGMT(text, pos, false, (Output<Boolean>) null);
    }

    public int parseOffsetShortLocalizedGMT(String text, ParsePosition pos) {
        return parseOffsetLocalizedGMT(text, pos, true, (Output<Boolean>) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:165:0x0382  */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x03cd  */
    /* JADX WARNING: Removed duplicated region for block: B:181:0x03da  */
    /* JADX WARNING: Removed duplicated region for block: B:192:0x0424  */
    /* JADX WARNING: Removed duplicated region for block: B:196:0x042c  */
    /* JADX WARNING: Removed duplicated region for block: B:197:0x0437  */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x0440  */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x058b  */
    /* JADX WARNING: Removed duplicated region for block: B:258:0x0591  */
    /* JADX WARNING: Removed duplicated region for block: B:268:0x05ae  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.ibm.icu.util.TimeZone parse(com.ibm.icu.text.TimeZoneFormat.Style r25, java.lang.String r26, java.text.ParsePosition r27, java.util.EnumSet<com.ibm.icu.text.TimeZoneFormat.ParseOption> r28, com.ibm.icu.util.Output<com.ibm.icu.text.TimeZoneFormat.TimeType> r29) {
        /*
            r24 = this;
            r0 = r24
            r1 = r25
            r2 = r26
            r3 = r27
            r4 = r28
            r5 = r29
            if (r5 != 0) goto L_0x0017
            com.ibm.icu.util.Output r6 = new com.ibm.icu.util.Output
            com.ibm.icu.text.TimeZoneFormat$TimeType r7 = com.ibm.icu.text.TimeZoneFormat.TimeType.UNKNOWN
            r6.<init>(r7)
            r5 = r6
            goto L_0x001b
        L_0x0017:
            com.ibm.icu.text.TimeZoneFormat$TimeType r6 = com.ibm.icu.text.TimeZoneFormat.TimeType.UNKNOWN
            r5.value = r6
        L_0x001b:
            int r6 = r27.getIndex()
            int r7 = r26.length()
            com.ibm.icu.text.TimeZoneFormat$Style r8 = com.ibm.icu.text.TimeZoneFormat.Style.SPECIFIC_LONG
            r10 = 0
            if (r1 == r8) goto L_0x0033
            com.ibm.icu.text.TimeZoneFormat$Style r8 = com.ibm.icu.text.TimeZoneFormat.Style.GENERIC_LONG
            if (r1 == r8) goto L_0x0033
            com.ibm.icu.text.TimeZoneFormat$Style r8 = com.ibm.icu.text.TimeZoneFormat.Style.GENERIC_LOCATION
            if (r1 != r8) goto L_0x0031
            goto L_0x0033
        L_0x0031:
            r8 = r10
            goto L_0x0034
        L_0x0033:
            r8 = 1
        L_0x0034:
            com.ibm.icu.text.TimeZoneFormat$Style r11 = com.ibm.icu.text.TimeZoneFormat.Style.SPECIFIC_SHORT
            if (r1 == r11) goto L_0x003f
            com.ibm.icu.text.TimeZoneFormat$Style r11 = com.ibm.icu.text.TimeZoneFormat.Style.GENERIC_SHORT
            if (r1 != r11) goto L_0x003d
            goto L_0x003f
        L_0x003d:
            r11 = r10
            goto L_0x0040
        L_0x003f:
            r11 = 1
        L_0x0040:
            r12 = 0
            java.text.ParsePosition r13 = new java.text.ParsePosition
            r13.<init>(r6)
            r14 = 2147483647(0x7fffffff, float:NaN)
            r15 = -1
            if (r8 != 0) goto L_0x0054
            if (r11 == 0) goto L_0x004f
            goto L_0x0054
        L_0x004f:
            r17 = r8
            r19 = r11
            goto L_0x009a
        L_0x0054:
            com.ibm.icu.util.Output r9 = new com.ibm.icu.util.Output
            r17 = r8
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r10)
            r9.<init>(r8)
            r8 = r9
            int r9 = r0.parseOffsetLocalizedGMT(r2, r13, r11, r8)
            int r10 = r13.getErrorIndex()
            r19 = r11
            r11 = -1
            if (r10 != r11) goto L_0x0090
            int r10 = r13.getIndex()
            if (r10 == r7) goto L_0x0084
            java.lang.Object r10 = r8.value
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L_0x007e
            goto L_0x0084
        L_0x007e:
            r14 = r9
            int r15 = r13.getIndex()
            goto L_0x0090
        L_0x0084:
            int r10 = r13.getIndex()
            r3.setIndex(r10)
            com.ibm.icu.util.TimeZone r10 = r0.getTimeZoneForOffset(r9)
            return r10
        L_0x0090:
            com.ibm.icu.text.TimeZoneFormat$Style r10 = com.ibm.icu.text.TimeZoneFormat.Style.LOCALIZED_GMT
            int r10 = r10.flag
            com.ibm.icu.text.TimeZoneFormat$Style r11 = com.ibm.icu.text.TimeZoneFormat.Style.LOCALIZED_GMT_SHORT
            int r11 = r11.flag
            r10 = r10 | r11
            r12 = r12 | r10
        L_0x009a:
            if (r4 != 0) goto L_0x00a7
            java.util.EnumSet r8 = r24.getDefaultParseOptions()
            com.ibm.icu.text.TimeZoneFormat$ParseOption r9 = com.ibm.icu.text.TimeZoneFormat.ParseOption.TZ_DATABASE_ABBREVIATIONS
            boolean r8 = r8.contains(r9)
            goto L_0x00ad
        L_0x00a7:
            com.ibm.icu.text.TimeZoneFormat$ParseOption r8 = com.ibm.icu.text.TimeZoneFormat.ParseOption.TZ_DATABASE_ABBREVIATIONS
            boolean r8 = r4.contains(r8)
        L_0x00ad:
            int[] r9 = com.ibm.icu.text.TimeZoneFormat.AnonymousClass1.$SwitchMap$com$ibm$icu$text$TimeZoneFormat$Style
            int r10 = r25.ordinal()
            r9 = r9[r10]
            switch(r9) {
                case 1: goto L_0x029d;
                case 2: goto L_0x029d;
                case 3: goto L_0x029d;
                case 4: goto L_0x01aa;
                case 5: goto L_0x01aa;
                case 6: goto L_0x018d;
                case 7: goto L_0x0170;
                case 8: goto L_0x0153;
                case 9: goto L_0x012f;
                case 10: goto L_0x010b;
                case 11: goto L_0x00ee;
                case 12: goto L_0x00bb;
                case 13: goto L_0x00ee;
                case 14: goto L_0x00bb;
                case 15: goto L_0x00ee;
                case 16: goto L_0x00bb;
                case 17: goto L_0x00ee;
                case 18: goto L_0x00bb;
                case 19: goto L_0x00ee;
                case 20: goto L_0x00bb;
                default: goto L_0x00b9;
            }
        L_0x00b9:
            goto L_0x02f1
        L_0x00bb:
            r13.setIndex(r6)
            r9 = -1
            r13.setErrorIndex(r9)
            com.ibm.icu.util.Output r10 = new com.ibm.icu.util.Output
            r11 = 0
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r11)
            r10.<init>(r9)
            r9 = r10
            int r10 = parseOffsetISO8601(r2, r13, r11, r9)
            int r11 = r13.getErrorIndex()
            r4 = -1
            if (r11 != r4) goto L_0x02f1
            java.lang.Object r4 = r9.value
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r4 = r4.booleanValue()
            if (r4 == 0) goto L_0x02f1
            int r4 = r13.getIndex()
            r3.setIndex(r4)
            com.ibm.icu.util.TimeZone r4 = r0.getTimeZoneForOffset(r10)
            return r4
        L_0x00ee:
            r13.setIndex(r6)
            r4 = -1
            r13.setErrorIndex(r4)
            int r9 = r0.parseOffsetISO8601(r2, r13)
            int r10 = r13.getErrorIndex()
            if (r10 != r4) goto L_0x02f1
            int r4 = r13.getIndex()
            r3.setIndex(r4)
            com.ibm.icu.util.TimeZone r4 = r0.getTimeZoneForOffset(r9)
            return r4
        L_0x010b:
            r13.setIndex(r6)
            r4 = -1
            r13.setErrorIndex(r4)
            int r9 = r0.parseOffsetShortLocalizedGMT(r2, r13)
            int r10 = r13.getErrorIndex()
            if (r10 != r4) goto L_0x0128
            int r4 = r13.getIndex()
            r3.setIndex(r4)
            com.ibm.icu.util.TimeZone r4 = r0.getTimeZoneForOffset(r9)
            return r4
        L_0x0128:
            com.ibm.icu.text.TimeZoneFormat$Style r4 = com.ibm.icu.text.TimeZoneFormat.Style.LOCALIZED_GMT
            int r4 = r4.flag
            r12 = r12 | r4
            goto L_0x02f1
        L_0x012f:
            r13.setIndex(r6)
            r4 = -1
            r13.setErrorIndex(r4)
            int r9 = r0.parseOffsetLocalizedGMT(r2, r13)
            int r10 = r13.getErrorIndex()
            if (r10 != r4) goto L_0x014c
            int r4 = r13.getIndex()
            r3.setIndex(r4)
            com.ibm.icu.util.TimeZone r4 = r0.getTimeZoneForOffset(r9)
            return r4
        L_0x014c:
            com.ibm.icu.text.TimeZoneFormat$Style r4 = com.ibm.icu.text.TimeZoneFormat.Style.LOCALIZED_GMT_SHORT
            int r4 = r4.flag
            r12 = r12 | r4
            goto L_0x02f1
        L_0x0153:
            r13.setIndex(r6)
            r4 = -1
            r13.setErrorIndex(r4)
            java.lang.String r9 = r0.parseExemplarLocation(r2, r13)
            int r10 = r13.getErrorIndex()
            if (r10 != r4) goto L_0x02f1
            int r4 = r13.getIndex()
            r3.setIndex(r4)
            com.ibm.icu.util.TimeZone r4 = com.ibm.icu.util.TimeZone.getTimeZone(r9)
            return r4
        L_0x0170:
            r13.setIndex(r6)
            r4 = -1
            r13.setErrorIndex(r4)
            java.lang.String r9 = parseShortZoneID(r2, r13)
            int r10 = r13.getErrorIndex()
            if (r10 != r4) goto L_0x02f1
            int r4 = r13.getIndex()
            r3.setIndex(r4)
            com.ibm.icu.util.TimeZone r4 = com.ibm.icu.util.TimeZone.getTimeZone(r9)
            return r4
        L_0x018d:
            r13.setIndex(r6)
            r4 = -1
            r13.setErrorIndex(r4)
            java.lang.String r9 = parseZoneID(r2, r13)
            int r10 = r13.getErrorIndex()
            if (r10 != r4) goto L_0x02f1
            int r4 = r13.getIndex()
            r3.setIndex(r4)
            com.ibm.icu.util.TimeZone r4 = com.ibm.icu.util.TimeZone.getTimeZone(r9)
            return r4
        L_0x01aa:
            r4 = 0
            com.ibm.icu.text.TimeZoneFormat$Style r9 = com.ibm.icu.text.TimeZoneFormat.Style.SPECIFIC_LONG
            if (r1 != r9) goto L_0x01b8
            com.ibm.icu.text.TimeZoneNames$NameType r9 = com.ibm.icu.text.TimeZoneNames.NameType.LONG_STANDARD
            com.ibm.icu.text.TimeZoneNames$NameType r10 = com.ibm.icu.text.TimeZoneNames.NameType.LONG_DAYLIGHT
            java.util.EnumSet r4 = java.util.EnumSet.of(r9, r10)
            goto L_0x01c4
        L_0x01b8:
            com.ibm.icu.text.TimeZoneFormat$Style r9 = com.ibm.icu.text.TimeZoneFormat.Style.SPECIFIC_SHORT
            if (r1 != r9) goto L_0x0297
            com.ibm.icu.text.TimeZoneNames$NameType r9 = com.ibm.icu.text.TimeZoneNames.NameType.SHORT_STANDARD
            com.ibm.icu.text.TimeZoneNames$NameType r10 = com.ibm.icu.text.TimeZoneNames.NameType.SHORT_DAYLIGHT
            java.util.EnumSet r4 = java.util.EnumSet.of(r9, r10)
        L_0x01c4:
            com.ibm.icu.text.TimeZoneNames r9 = r0._tznames
            java.util.Collection r9 = r9.find(r2, r6, r4)
            if (r9 == 0) goto L_0x0215
            r10 = 0
            java.util.Iterator r11 = r9.iterator()
        L_0x01d1:
            boolean r20 = r11.hasNext()
            if (r20 == 0) goto L_0x01f3
            java.lang.Object r20 = r11.next()
            com.ibm.icu.text.TimeZoneNames$MatchInfo r20 = (com.ibm.icu.text.TimeZoneNames.MatchInfo) r20
            int r21 = r20.matchLength()
            r22 = r9
            int r9 = r6 + r21
            if (r9 <= r15) goto L_0x01f0
            r9 = r20
            int r10 = r20.matchLength()
            int r10 = r10 + r6
            r15 = r10
            r10 = r9
        L_0x01f0:
            r9 = r22
            goto L_0x01d1
        L_0x01f3:
            r22 = r9
            if (r10 == 0) goto L_0x0217
            com.ibm.icu.text.TimeZoneNames$NameType r9 = r10.nameType()
            com.ibm.icu.text.TimeZoneFormat$TimeType r9 = r0.getTimeType(r9)
            r5.value = r9
            r3.setIndex(r15)
            java.lang.String r9 = r10.tzID()
            java.lang.String r11 = r10.mzID()
            java.lang.String r9 = r0.getTimeZoneID(r9, r11)
            com.ibm.icu.util.TimeZone r9 = com.ibm.icu.util.TimeZone.getTimeZone(r9)
            return r9
        L_0x0215:
            r22 = r9
        L_0x0217:
            if (r8 == 0) goto L_0x0294
            com.ibm.icu.text.TimeZoneFormat$Style r9 = com.ibm.icu.text.TimeZoneFormat.Style.SPECIFIC_SHORT
            if (r1 != r9) goto L_0x0294
            com.ibm.icu.text.TimeZoneNames$NameType r9 = com.ibm.icu.text.TimeZoneNames.NameType.SHORT_STANDARD
            boolean r9 = r4.contains(r9)
            if (r9 == 0) goto L_0x028c
            com.ibm.icu.text.TimeZoneNames$NameType r9 = com.ibm.icu.text.TimeZoneNames.NameType.SHORT_DAYLIGHT
            boolean r9 = r4.contains(r9)
            if (r9 == 0) goto L_0x0284
            com.ibm.icu.text.TimeZoneNames r9 = r24.getTZDBTimeZoneNames()
            java.util.Collection r9 = r9.find(r2, r6, r4)
            if (r9 == 0) goto L_0x0281
            r10 = 0
            java.util.Iterator r11 = r9.iterator()
        L_0x023d:
            boolean r20 = r11.hasNext()
            if (r20 == 0) goto L_0x025f
            java.lang.Object r20 = r11.next()
            com.ibm.icu.text.TimeZoneNames$MatchInfo r20 = (com.ibm.icu.text.TimeZoneNames.MatchInfo) r20
            int r21 = r20.matchLength()
            r23 = r4
            int r4 = r6 + r21
            if (r4 <= r15) goto L_0x025c
            r4 = r20
            int r10 = r20.matchLength()
            int r10 = r10 + r6
            r15 = r10
            r10 = r4
        L_0x025c:
            r4 = r23
            goto L_0x023d
        L_0x025f:
            r23 = r4
            if (r10 == 0) goto L_0x0283
            com.ibm.icu.text.TimeZoneNames$NameType r4 = r10.nameType()
            com.ibm.icu.text.TimeZoneFormat$TimeType r4 = r0.getTimeType(r4)
            r5.value = r4
            r3.setIndex(r15)
            java.lang.String r4 = r10.tzID()
            java.lang.String r11 = r10.mzID()
            java.lang.String r4 = r0.getTimeZoneID(r4, r11)
            com.ibm.icu.util.TimeZone r4 = com.ibm.icu.util.TimeZone.getTimeZone(r4)
            return r4
        L_0x0281:
            r23 = r4
        L_0x0283:
            goto L_0x02f1
        L_0x0284:
            r23 = r4
            java.lang.AssertionError r4 = new java.lang.AssertionError
            r4.<init>()
            throw r4
        L_0x028c:
            r23 = r4
            java.lang.AssertionError r4 = new java.lang.AssertionError
            r4.<init>()
            throw r4
        L_0x0294:
            r23 = r4
            goto L_0x02f1
        L_0x0297:
            java.lang.AssertionError r9 = new java.lang.AssertionError
            r9.<init>()
            throw r9
        L_0x029d:
            r4 = 0
            int[] r9 = com.ibm.icu.text.TimeZoneFormat.AnonymousClass1.$SwitchMap$com$ibm$icu$text$TimeZoneFormat$Style
            int r10 = r25.ordinal()
            r9 = r9[r10]
            switch(r9) {
                case 1: goto L_0x02bc;
                case 2: goto L_0x02b3;
                case 3: goto L_0x02aa;
                default: goto L_0x02a9;
            }
        L_0x02a9:
            goto L_0x02eb
        L_0x02aa:
            com.ibm.icu.impl.TimeZoneGenericNames$GenericNameType r9 = com.ibm.icu.impl.TimeZoneGenericNames.GenericNameType.SHORT
            com.ibm.icu.impl.TimeZoneGenericNames$GenericNameType r10 = com.ibm.icu.impl.TimeZoneGenericNames.GenericNameType.LOCATION
            java.util.EnumSet r4 = java.util.EnumSet.of(r9, r10)
            goto L_0x02c3
        L_0x02b3:
            com.ibm.icu.impl.TimeZoneGenericNames$GenericNameType r9 = com.ibm.icu.impl.TimeZoneGenericNames.GenericNameType.LONG
            com.ibm.icu.impl.TimeZoneGenericNames$GenericNameType r10 = com.ibm.icu.impl.TimeZoneGenericNames.GenericNameType.LOCATION
            java.util.EnumSet r4 = java.util.EnumSet.of(r9, r10)
            goto L_0x02c3
        L_0x02bc:
            com.ibm.icu.impl.TimeZoneGenericNames$GenericNameType r9 = com.ibm.icu.impl.TimeZoneGenericNames.GenericNameType.LOCATION
            java.util.EnumSet r4 = java.util.EnumSet.of(r9)
        L_0x02c3:
            com.ibm.icu.impl.TimeZoneGenericNames r9 = r24.getTimeZoneGenericNames()
            com.ibm.icu.impl.TimeZoneGenericNames$GenericMatchInfo r9 = r9.findBestMatch(r2, r6, r4)
            if (r9 == 0) goto L_0x02f1
            int r10 = r9.matchLength()
            int r10 = r10 + r6
            if (r10 <= r15) goto L_0x02f1
            com.ibm.icu.text.TimeZoneFormat$TimeType r10 = r9.timeType()
            r5.value = r10
            int r10 = r9.matchLength()
            int r10 = r10 + r6
            r3.setIndex(r10)
            java.lang.String r10 = r9.tzID()
            com.ibm.icu.util.TimeZone r10 = com.ibm.icu.util.TimeZone.getTimeZone(r10)
            return r10
        L_0x02eb:
            java.lang.AssertionError r9 = new java.lang.AssertionError
            r9.<init>()
            throw r9
        L_0x02f1:
            int r4 = r1.flag
            r4 = r4 | r12
            r9 = 2147483647(0x7fffffff, float:NaN)
            if (r15 <= r6) goto L_0x0309
            if (r14 == r9) goto L_0x0303
            r3.setIndex(r15)
            com.ibm.icu.util.TimeZone r9 = r0.getTimeZoneForOffset(r14)
            return r9
        L_0x0303:
            java.lang.AssertionError r9 = new java.lang.AssertionError
            r9.<init>()
            throw r9
        L_0x0309:
            r10 = 0
            com.ibm.icu.text.TimeZoneFormat$TimeType r11 = com.ibm.icu.text.TimeZoneFormat.TimeType.UNKNOWN
            if (r15 >= 0) goto L_0x05b9
            if (r14 != r9) goto L_0x05b3
            if (r15 >= r7) goto L_0x0375
            r12 = r4 & 128(0x80, float:1.794E-43)
            if (r12 == 0) goto L_0x031e
            r12 = r4 & 256(0x100, float:3.59E-43)
            if (r12 != 0) goto L_0x031b
            goto L_0x031e
        L_0x031b:
            r22 = r10
            goto L_0x0377
        L_0x031e:
            r13.setIndex(r6)
            r12 = -1
            r13.setErrorIndex(r12)
            com.ibm.icu.util.Output r9 = new com.ibm.icu.util.Output
            r16 = 0
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r16)
            r9.<init>(r12)
            r12 = r16
            int r1 = parseOffsetISO8601(r2, r13, r12, r9)
            int r12 = r13.getErrorIndex()
            r22 = r10
            r10 = -1
            if (r12 != r10) goto L_0x0377
            int r10 = r13.getIndex()
            if (r10 == r7) goto L_0x0369
            java.lang.Object r10 = r9.value
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L_0x0350
            goto L_0x0369
        L_0x0350:
            int r10 = r13.getIndex()
            if (r15 >= r10) goto L_0x0377
            r14 = r1
            r10 = 0
            com.ibm.icu.text.TimeZoneFormat$TimeType r11 = com.ibm.icu.text.TimeZoneFormat.TimeType.UNKNOWN
            int r15 = r13.getIndex()
            int r12 = r6 + 1
            if (r15 != r12) goto L_0x0363
            goto L_0x0379
        L_0x0363:
            java.lang.AssertionError r12 = new java.lang.AssertionError
            r12.<init>()
            throw r12
        L_0x0369:
            int r10 = r13.getIndex()
            r3.setIndex(r10)
            com.ibm.icu.util.TimeZone r10 = r0.getTimeZoneForOffset(r1)
            return r10
        L_0x0375:
            r22 = r10
        L_0x0377:
            r10 = r22
        L_0x0379:
            if (r15 >= r7) goto L_0x03cd
            com.ibm.icu.text.TimeZoneFormat$Style r1 = com.ibm.icu.text.TimeZoneFormat.Style.LOCALIZED_GMT
            int r1 = r1.flag
            r1 = r1 & r4
            if (r1 != 0) goto L_0x03cd
            r13.setIndex(r6)
            r1 = -1
            r13.setErrorIndex(r1)
            com.ibm.icu.util.Output r9 = new com.ibm.icu.util.Output
            r12 = 0
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r12)
            r9.<init>(r1)
            r1 = r9
            int r9 = r0.parseOffsetLocalizedGMT(r2, r13, r12, r1)
            int r12 = r13.getErrorIndex()
            r21 = r10
            r10 = -1
            if (r12 != r10) goto L_0x03cf
            int r10 = r13.getIndex()
            if (r10 == r7) goto L_0x03c1
            java.lang.Object r10 = r1.value
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L_0x03b2
            goto L_0x03c1
        L_0x03b2:
            int r10 = r13.getIndex()
            if (r15 >= r10) goto L_0x03cf
            r14 = r9
            r10 = 0
            com.ibm.icu.text.TimeZoneFormat$TimeType r11 = com.ibm.icu.text.TimeZoneFormat.TimeType.UNKNOWN
            int r15 = r13.getIndex()
            goto L_0x03d1
        L_0x03c1:
            int r10 = r13.getIndex()
            r3.setIndex(r10)
            com.ibm.icu.util.TimeZone r10 = r0.getTimeZoneForOffset(r9)
            return r10
        L_0x03cd:
            r21 = r10
        L_0x03cf:
            r10 = r21
        L_0x03d1:
            if (r15 >= r7) goto L_0x0424
            com.ibm.icu.text.TimeZoneFormat$Style r1 = com.ibm.icu.text.TimeZoneFormat.Style.LOCALIZED_GMT_SHORT
            int r1 = r1.flag
            r1 = r1 & r4
            if (r1 != 0) goto L_0x0424
            r13.setIndex(r6)
            r1 = -1
            r13.setErrorIndex(r1)
            com.ibm.icu.util.Output r9 = new com.ibm.icu.util.Output
            r12 = 0
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r12)
            r9.<init>(r12)
            r12 = 1
            int r12 = r0.parseOffsetLocalizedGMT(r2, r13, r12, r9)
            r29 = r10
            int r10 = r13.getErrorIndex()
            if (r10 != r1) goto L_0x0426
            int r1 = r13.getIndex()
            if (r1 == r7) goto L_0x0418
            java.lang.Object r1 = r9.value
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x0409
            goto L_0x0418
        L_0x0409:
            int r1 = r13.getIndex()
            if (r15 >= r1) goto L_0x0426
            r14 = r12
            r10 = 0
            com.ibm.icu.text.TimeZoneFormat$TimeType r11 = com.ibm.icu.text.TimeZoneFormat.TimeType.UNKNOWN
            int r15 = r13.getIndex()
            goto L_0x0428
        L_0x0418:
            int r1 = r13.getIndex()
            r3.setIndex(r1)
            com.ibm.icu.util.TimeZone r1 = r0.getTimeZoneForOffset(r12)
            return r1
        L_0x0424:
            r29 = r10
        L_0x0426:
            r10 = r29
        L_0x0428:
            r1 = r28
            if (r1 != 0) goto L_0x0437
            java.util.EnumSet r9 = r24.getDefaultParseOptions()
            com.ibm.icu.text.TimeZoneFormat$ParseOption r12 = com.ibm.icu.text.TimeZoneFormat.ParseOption.ALL_STYLES
            boolean r9 = r9.contains(r12)
            goto L_0x043d
        L_0x0437:
            com.ibm.icu.text.TimeZoneFormat$ParseOption r9 = com.ibm.icu.text.TimeZoneFormat.ParseOption.ALL_STYLES
            boolean r9 = r1.contains(r9)
        L_0x043d:
            if (r9 == 0) goto L_0x058b
            if (r15 >= r7) goto L_0x04a1
            com.ibm.icu.text.TimeZoneNames r12 = r0._tznames
            java.util.EnumSet<com.ibm.icu.text.TimeZoneNames$NameType> r1 = ALL_SIMPLE_NAME_TYPES
            java.util.Collection r1 = r12.find(r2, r6, r1)
            r12 = 0
            r18 = -1
            if (r1 == 0) goto L_0x047b
            java.util.Iterator r21 = r1.iterator()
            r29 = r1
            r1 = r18
        L_0x0457:
            boolean r18 = r21.hasNext()
            if (r18 == 0) goto L_0x0478
            java.lang.Object r18 = r21.next()
            com.ibm.icu.text.TimeZoneNames$MatchInfo r18 = (com.ibm.icu.text.TimeZoneNames.MatchInfo) r18
            int r22 = r18.matchLength()
            r23 = r9
            int r9 = r6 + r22
            if (r9 <= r1) goto L_0x0475
            r12 = r18
            int r9 = r18.matchLength()
            int r1 = r6 + r9
        L_0x0475:
            r9 = r23
            goto L_0x0457
        L_0x0478:
            r23 = r9
            goto L_0x0481
        L_0x047b:
            r29 = r1
            r23 = r9
            r1 = r18
        L_0x0481:
            if (r15 >= r1) goto L_0x049e
            r15 = r1
            java.lang.String r9 = r12.tzID()
            r18 = r1
            java.lang.String r1 = r12.mzID()
            java.lang.String r10 = r0.getTimeZoneID(r9, r1)
            com.ibm.icu.text.TimeZoneNames$NameType r1 = r12.nameType()
            com.ibm.icu.text.TimeZoneFormat$TimeType r11 = r0.getTimeType(r1)
            r14 = 2147483647(0x7fffffff, float:NaN)
            goto L_0x04a3
        L_0x049e:
            r18 = r1
            goto L_0x04a3
        L_0x04a1:
            r23 = r9
        L_0x04a3:
            if (r8 == 0) goto L_0x050a
            if (r15 >= r7) goto L_0x050a
            com.ibm.icu.text.TimeZoneFormat$Style r1 = com.ibm.icu.text.TimeZoneFormat.Style.SPECIFIC_SHORT
            int r1 = r1.flag
            r1 = r1 & r4
            if (r1 != 0) goto L_0x050a
            com.ibm.icu.text.TimeZoneNames r1 = r24.getTZDBTimeZoneNames()
            java.util.EnumSet<com.ibm.icu.text.TimeZoneNames$NameType> r9 = ALL_SIMPLE_NAME_TYPES
            java.util.Collection r1 = r1.find(r2, r6, r9)
            r9 = 0
            r12 = -1
            if (r1 == 0) goto L_0x0505
            java.util.Iterator r18 = r1.iterator()
        L_0x04c1:
            boolean r21 = r18.hasNext()
            if (r21 == 0) goto L_0x04e3
            java.lang.Object r21 = r18.next()
            com.ibm.icu.text.TimeZoneNames$MatchInfo r21 = (com.ibm.icu.text.TimeZoneNames.MatchInfo) r21
            int r22 = r21.matchLength()
            r29 = r1
            int r1 = r6 + r22
            if (r1 <= r12) goto L_0x04e0
            r1 = r21
            int r9 = r21.matchLength()
            int r9 = r9 + r6
            r12 = r9
            r9 = r1
        L_0x04e0:
            r1 = r29
            goto L_0x04c1
        L_0x04e3:
            r29 = r1
            if (r15 >= r12) goto L_0x0502
            r15 = r12
            java.lang.String r1 = r9.tzID()
            r18 = r8
            java.lang.String r8 = r9.mzID()
            java.lang.String r10 = r0.getTimeZoneID(r1, r8)
            com.ibm.icu.text.TimeZoneNames$NameType r1 = r9.nameType()
            com.ibm.icu.text.TimeZoneFormat$TimeType r11 = r0.getTimeType(r1)
            r14 = 2147483647(0x7fffffff, float:NaN)
            goto L_0x050c
        L_0x0502:
            r18 = r8
            goto L_0x050c
        L_0x0505:
            r29 = r1
            r18 = r8
            goto L_0x050c
        L_0x050a:
            r18 = r8
        L_0x050c:
            if (r15 >= r7) goto L_0x0532
            com.ibm.icu.impl.TimeZoneGenericNames r1 = r24.getTimeZoneGenericNames()
            java.util.EnumSet<com.ibm.icu.impl.TimeZoneGenericNames$GenericNameType> r8 = ALL_GENERIC_NAME_TYPES
            com.ibm.icu.impl.TimeZoneGenericNames$GenericMatchInfo r1 = r1.findBestMatch(r2, r6, r8)
            if (r1 == 0) goto L_0x0532
            int r8 = r1.matchLength()
            int r8 = r8 + r6
            if (r15 >= r8) goto L_0x0532
            int r8 = r1.matchLength()
            int r15 = r6 + r8
            java.lang.String r10 = r1.tzID()
            com.ibm.icu.text.TimeZoneFormat$TimeType r11 = r1.timeType()
            r14 = 2147483647(0x7fffffff, float:NaN)
        L_0x0532:
            if (r15 >= r7) goto L_0x0560
            com.ibm.icu.text.TimeZoneFormat$Style r1 = com.ibm.icu.text.TimeZoneFormat.Style.ZONE_ID
            int r1 = r1.flag
            r1 = r1 & r4
            if (r1 != 0) goto L_0x0560
            r13.setIndex(r6)
            r1 = -1
            r13.setErrorIndex(r1)
            java.lang.String r8 = parseZoneID(r2, r13)
            int r9 = r13.getErrorIndex()
            if (r9 != r1) goto L_0x0560
            int r1 = r13.getIndex()
            if (r15 >= r1) goto L_0x0560
            int r1 = r13.getIndex()
            r9 = r8
            com.ibm.icu.text.TimeZoneFormat$TimeType r10 = com.ibm.icu.text.TimeZoneFormat.TimeType.UNKNOWN
            r11 = 2147483647(0x7fffffff, float:NaN)
            r15 = r1
            r14 = r11
            r11 = r10
            r10 = r9
        L_0x0560:
            if (r15 >= r7) goto L_0x058f
            com.ibm.icu.text.TimeZoneFormat$Style r1 = com.ibm.icu.text.TimeZoneFormat.Style.ZONE_ID_SHORT
            int r1 = r1.flag
            r1 = r1 & r4
            if (r1 != 0) goto L_0x058f
            r13.setIndex(r6)
            r1 = -1
            r13.setErrorIndex(r1)
            java.lang.String r8 = parseShortZoneID(r2, r13)
            int r9 = r13.getErrorIndex()
            if (r9 != r1) goto L_0x058f
            int r1 = r13.getIndex()
            if (r15 >= r1) goto L_0x058f
            int r15 = r13.getIndex()
            r10 = r8
            com.ibm.icu.text.TimeZoneFormat$TimeType r11 = com.ibm.icu.text.TimeZoneFormat.TimeType.UNKNOWN
            r14 = 2147483647(0x7fffffff, float:NaN)
            goto L_0x058f
        L_0x058b:
            r18 = r8
            r23 = r9
        L_0x058f:
            if (r15 <= r6) goto L_0x05ae
            r1 = 0
            if (r10 == 0) goto L_0x0599
            com.ibm.icu.util.TimeZone r1 = com.ibm.icu.util.TimeZone.getTimeZone(r10)
            goto L_0x05a2
        L_0x0599:
            r8 = 2147483647(0x7fffffff, float:NaN)
            if (r14 == r8) goto L_0x05a8
            com.ibm.icu.util.TimeZone r1 = r0.getTimeZoneForOffset(r14)
        L_0x05a2:
            r5.value = r11
            r3.setIndex(r15)
            return r1
        L_0x05a8:
            java.lang.AssertionError r8 = new java.lang.AssertionError
            r8.<init>()
            throw r8
        L_0x05ae:
            r3.setErrorIndex(r6)
            r1 = 0
            return r1
        L_0x05b3:
            java.lang.AssertionError r1 = new java.lang.AssertionError
            r1.<init>()
            throw r1
        L_0x05b9:
            java.lang.AssertionError r1 = new java.lang.AssertionError
            r1.<init>()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.TimeZoneFormat.parse(com.ibm.icu.text.TimeZoneFormat$Style, java.lang.String, java.text.ParsePosition, java.util.EnumSet, com.ibm.icu.util.Output):com.ibm.icu.util.TimeZone");
    }

    public TimeZone parse(Style style, String text, ParsePosition pos, Output<TimeType> timeType) {
        return parse(style, text, pos, (EnumSet<ParseOption>) null, timeType);
    }

    public final TimeZone parse(String text, ParsePosition pos) {
        return parse(Style.GENERIC_LOCATION, text, pos, EnumSet.of(ParseOption.ALL_STYLES), (Output<TimeType>) null);
    }

    public final TimeZone parse(String text) throws ParseException {
        ParsePosition pos = new ParsePosition(0);
        TimeZone tz = parse(text, pos);
        if (pos.getErrorIndex() >= 0) {
            throw new ParseException("Unparseable time zone: \"" + text + "\"", 0);
        } else if (tz != null) {
            return tz;
        } else {
            throw new AssertionError();
        }
    }

    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
        TimeZone tz;
        long date = System.currentTimeMillis();
        if (obj instanceof TimeZone) {
            tz = (TimeZone) obj;
        } else if (obj instanceof Calendar) {
            tz = ((Calendar) obj).getTimeZone();
            date = ((Calendar) obj).getTimeInMillis();
        } else {
            throw new IllegalArgumentException("Cannot format given Object (" + obj.getClass().getName() + ") as a time zone");
        }
        if (tz != null) {
            String result = formatOffsetLocalizedGMT(tz.getOffset(date));
            toAppendTo.append(result);
            if (pos.getFieldAttribute() == DateFormat.Field.TIME_ZONE || pos.getField() == 17) {
                pos.setBeginIndex(0);
                pos.setEndIndex(result.length());
            }
            return toAppendTo;
        }
        throw new AssertionError();
    }

    public AttributedCharacterIterator formatToCharacterIterator(Object obj) {
        AttributedString as = new AttributedString(format(obj, new StringBuffer(), new FieldPosition(0)).toString());
        as.addAttribute(DateFormat.Field.TIME_ZONE, DateFormat.Field.TIME_ZONE);
        return as.getIterator();
    }

    public Object parseObject(String source, ParsePosition pos) {
        return parse(source, pos);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.lang.String[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v0, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: com.ibm.icu.text.TimeZoneFormat$GMTOffsetField} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v4, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v16, resolved type: java.lang.String[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v17, resolved type: com.ibm.icu.text.TimeZoneFormat$GMTOffsetField[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v18, resolved type: com.ibm.icu.text.TimeZoneFormat$GMTOffsetField[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v19, resolved type: com.ibm.icu.text.TimeZoneFormat$GMTOffsetField[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v20, resolved type: com.ibm.icu.text.TimeZoneFormat$GMTOffsetField[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v21, resolved type: com.ibm.icu.text.TimeZoneFormat$GMTOffsetField[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String formatOffsetLocalizedGMT(int r13, boolean r14) {
        /*
            r12 = this;
            if (r13 != 0) goto L_0x0005
            java.lang.String r0 = r12._gmtZeroFormat
            return r0
        L_0x0005:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 1
            if (r13 >= 0) goto L_0x000f
            int r13 = -r13
            r1 = 0
        L_0x000f:
            r2 = 3600000(0x36ee80, float:5.044674E-39)
            int r3 = r13 / r2
            int r13 = r13 % r2
            r2 = 60000(0xea60, float:8.4078E-41)
            int r4 = r13 / r2
            int r13 = r13 % r2
            int r2 = r13 / 1000
            r5 = 23
            if (r3 > r5) goto L_0x00b9
            r5 = 59
            if (r4 > r5) goto L_0x00b9
            if (r2 > r5) goto L_0x00b9
            if (r1 == 0) goto L_0x0051
            if (r2 == 0) goto L_0x0036
            java.lang.Object[][] r5 = r12._gmtOffsetPatternItems
            com.ibm.icu.text.TimeZoneFormat$GMTOffsetPatternType r6 = com.ibm.icu.text.TimeZoneFormat.GMTOffsetPatternType.POSITIVE_HMS
            int r6 = r6.ordinal()
            r5 = r5[r6]
            goto L_0x0078
        L_0x0036:
            if (r4 != 0) goto L_0x0046
            if (r14 != 0) goto L_0x003b
            goto L_0x0046
        L_0x003b:
            java.lang.Object[][] r5 = r12._gmtOffsetPatternItems
            com.ibm.icu.text.TimeZoneFormat$GMTOffsetPatternType r6 = com.ibm.icu.text.TimeZoneFormat.GMTOffsetPatternType.POSITIVE_H
            int r6 = r6.ordinal()
            r5 = r5[r6]
            goto L_0x0078
        L_0x0046:
            java.lang.Object[][] r5 = r12._gmtOffsetPatternItems
            com.ibm.icu.text.TimeZoneFormat$GMTOffsetPatternType r6 = com.ibm.icu.text.TimeZoneFormat.GMTOffsetPatternType.POSITIVE_HM
            int r6 = r6.ordinal()
            r5 = r5[r6]
            goto L_0x0078
        L_0x0051:
            if (r2 == 0) goto L_0x005e
            java.lang.Object[][] r5 = r12._gmtOffsetPatternItems
            com.ibm.icu.text.TimeZoneFormat$GMTOffsetPatternType r6 = com.ibm.icu.text.TimeZoneFormat.GMTOffsetPatternType.NEGATIVE_HMS
            int r6 = r6.ordinal()
            r5 = r5[r6]
            goto L_0x0078
        L_0x005e:
            if (r4 != 0) goto L_0x006e
            if (r14 != 0) goto L_0x0063
            goto L_0x006e
        L_0x0063:
            java.lang.Object[][] r5 = r12._gmtOffsetPatternItems
            com.ibm.icu.text.TimeZoneFormat$GMTOffsetPatternType r6 = com.ibm.icu.text.TimeZoneFormat.GMTOffsetPatternType.NEGATIVE_H
            int r6 = r6.ordinal()
            r5 = r5[r6]
            goto L_0x0078
        L_0x006e:
            java.lang.Object[][] r5 = r12._gmtOffsetPatternItems
            com.ibm.icu.text.TimeZoneFormat$GMTOffsetPatternType r6 = com.ibm.icu.text.TimeZoneFormat.GMTOffsetPatternType.NEGATIVE_HM
            int r6 = r6.ordinal()
            r5 = r5[r6]
        L_0x0078:
            java.lang.String r6 = r12._gmtPatternPrefix
            r0.append(r6)
            int r6 = r5.length
            r7 = 0
        L_0x007f:
            if (r7 >= r6) goto L_0x00af
            r8 = r5[r7]
            boolean r9 = r8 instanceof java.lang.String
            if (r9 == 0) goto L_0x008e
            r9 = r8
            java.lang.String r9 = (java.lang.String) r9
            r0.append(r9)
            goto L_0x00ac
        L_0x008e:
            boolean r9 = r8 instanceof com.ibm.icu.text.TimeZoneFormat.GMTOffsetField
            if (r9 == 0) goto L_0x00ac
            r9 = r8
            com.ibm.icu.text.TimeZoneFormat$GMTOffsetField r9 = (com.ibm.icu.text.TimeZoneFormat.GMTOffsetField) r9
            char r10 = r9.getType()
            r11 = 2
            switch(r10) {
                case 72: goto L_0x00a6;
                case 109: goto L_0x00a2;
                case 115: goto L_0x009e;
                default: goto L_0x009d;
            }
        L_0x009d:
            goto L_0x00ac
        L_0x009e:
            r12.appendOffsetDigits(r0, r2, r11)
            goto L_0x00ac
        L_0x00a2:
            r12.appendOffsetDigits(r0, r4, r11)
            goto L_0x00ac
        L_0x00a6:
            if (r14 == 0) goto L_0x00a9
            r11 = 1
        L_0x00a9:
            r12.appendOffsetDigits(r0, r3, r11)
        L_0x00ac:
            int r7 = r7 + 1
            goto L_0x007f
        L_0x00af:
            java.lang.String r6 = r12._gmtPatternSuffix
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            return r6
        L_0x00b9:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Offset out of range :"
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.StringBuilder r6 = r6.append(r13)
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.TimeZoneFormat.formatOffsetLocalizedGMT(int, boolean):java.lang.String");
    }

    private String formatOffsetISO8601(int offset, boolean isBasic, boolean useUtcIndicator, boolean isShort, boolean ignoreSeconds) {
        int i = offset;
        int absOffset = i < 0 ? -i : i;
        if (useUtcIndicator) {
            if (absOffset < 1000) {
                return ISO8601_UTC;
            }
            if (ignoreSeconds && absOffset < MILLIS_PER_MINUTE) {
                return ISO8601_UTC;
            }
        }
        OffsetFields minFields = isShort ? OffsetFields.H : OffsetFields.HM;
        OffsetFields maxFields = ignoreSeconds ? OffsetFields.HM : OffsetFields.HMS;
        Character sep = isBasic ? null : Character.valueOf(DEFAULT_GMT_OFFSET_SEP);
        if (absOffset < MAX_OFFSET) {
            int absOffset2 = absOffset % MILLIS_PER_HOUR;
            int[] fields = {absOffset / MILLIS_PER_HOUR, absOffset2 / MILLIS_PER_MINUTE, (absOffset2 % MILLIS_PER_MINUTE) / 1000};
            if (fields[0] < 0 || fields[0] > 23) {
                throw new AssertionError();
            } else if (fields[1] < 0 || fields[1] > 59) {
                throw new AssertionError();
            } else if (fields[2] < 0 || fields[2] > 59) {
                throw new AssertionError();
            } else {
                int lastIdx = maxFields.ordinal();
                while (lastIdx > minFields.ordinal() && fields[lastIdx] == 0) {
                    lastIdx--;
                }
                StringBuilder buf = new StringBuilder();
                char sign = '+';
                if (i < 0) {
                    int idx = 0;
                    while (true) {
                        if (idx > lastIdx) {
                            break;
                        } else if (fields[idx] != 0) {
                            sign = '-';
                            break;
                        } else {
                            idx++;
                        }
                    }
                }
                buf.append(sign);
                for (int idx2 = 0; idx2 <= lastIdx; idx2++) {
                    if (!(sep == null || idx2 == 0)) {
                        buf.append(sep);
                    }
                    if (fields[idx2] < 10) {
                        buf.append('0');
                    }
                    buf.append(fields[idx2]);
                }
                return buf.toString();
            }
        } else {
            throw new IllegalArgumentException("Offset out of range :" + offset);
        }
    }

    private String formatSpecific(TimeZone tz, TimeZoneNames.NameType stdType, TimeZoneNames.NameType dstType, long date, Output<TimeType> timeType) {
        String name;
        if (stdType != TimeZoneNames.NameType.LONG_STANDARD && stdType != TimeZoneNames.NameType.SHORT_STANDARD) {
            throw new AssertionError();
        } else if (dstType == TimeZoneNames.NameType.LONG_DAYLIGHT || dstType == TimeZoneNames.NameType.SHORT_DAYLIGHT) {
            boolean isDaylight = tz.inDaylightTime(new Date(date));
            if (isDaylight) {
                name = getTimeZoneNames().getDisplayName(ZoneMeta.getCanonicalCLDRID(tz), dstType, date);
            } else {
                name = getTimeZoneNames().getDisplayName(ZoneMeta.getCanonicalCLDRID(tz), stdType, date);
            }
            if (!(name == null || timeType == null)) {
                timeType.value = isDaylight ? TimeType.DAYLIGHT : TimeType.STANDARD;
            }
            return name;
        } else {
            throw new AssertionError();
        }
    }

    private String formatExemplarLocation(TimeZone tz) {
        String location = getTimeZoneNames().getExemplarLocationName(ZoneMeta.getCanonicalCLDRID(tz));
        if (location != null) {
            return location;
        }
        String location2 = getTimeZoneNames().getExemplarLocationName(UNKNOWN_ZONE_ID);
        if (location2 == null) {
            return UNKNOWN_LOCATION;
        }
        return location2;
    }

    private String getTimeZoneID(String tzID, String mzID) {
        String id = tzID;
        if (id == null) {
            if (mzID != null) {
                id = this._tznames.getReferenceZoneID(mzID, getTargetRegion());
                if (id == null) {
                    throw new IllegalArgumentException("Invalid mzID: " + mzID);
                }
            } else {
                throw new AssertionError();
            }
        }
        return id;
    }

    private synchronized String getTargetRegion() {
        if (this._region == null) {
            String country = this._locale.getCountry();
            this._region = country;
            if (country.length() == 0) {
                String country2 = ULocale.addLikelySubtags(this._locale).getCountry();
                this._region = country2;
                if (country2.length() == 0) {
                    this._region = "001";
                }
            }
        }
        return this._region;
    }

    /* renamed from: com.ibm.icu.text.TimeZoneFormat$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$ibm$icu$text$TimeZoneFormat$Style;
        static final /* synthetic */ int[] $SwitchMap$com$ibm$icu$text$TimeZoneNames$NameType;

        static {
            int[] iArr = new int[TimeZoneNames.NameType.values().length];
            $SwitchMap$com$ibm$icu$text$TimeZoneNames$NameType = iArr;
            try {
                iArr[TimeZoneNames.NameType.LONG_STANDARD.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$TimeZoneNames$NameType[TimeZoneNames.NameType.SHORT_STANDARD.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$TimeZoneNames$NameType[TimeZoneNames.NameType.LONG_DAYLIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$TimeZoneNames$NameType[TimeZoneNames.NameType.SHORT_DAYLIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            int[] iArr2 = new int[Style.values().length];
            $SwitchMap$com$ibm$icu$text$TimeZoneFormat$Style = iArr2;
            try {
                iArr2[Style.GENERIC_LOCATION.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$TimeZoneFormat$Style[Style.GENERIC_LONG.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$TimeZoneFormat$Style[Style.GENERIC_SHORT.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$TimeZoneFormat$Style[Style.SPECIFIC_LONG.ordinal()] = 4;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$TimeZoneFormat$Style[Style.SPECIFIC_SHORT.ordinal()] = 5;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$TimeZoneFormat$Style[Style.ZONE_ID.ordinal()] = 6;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$TimeZoneFormat$Style[Style.ZONE_ID_SHORT.ordinal()] = 7;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$TimeZoneFormat$Style[Style.EXEMPLAR_LOCATION.ordinal()] = 8;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$TimeZoneFormat$Style[Style.LOCALIZED_GMT.ordinal()] = 9;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$TimeZoneFormat$Style[Style.LOCALIZED_GMT_SHORT.ordinal()] = 10;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$TimeZoneFormat$Style[Style.ISO_BASIC_SHORT.ordinal()] = 11;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$TimeZoneFormat$Style[Style.ISO_BASIC_LOCAL_SHORT.ordinal()] = 12;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$TimeZoneFormat$Style[Style.ISO_BASIC_FIXED.ordinal()] = 13;
            } catch (NoSuchFieldError e17) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$TimeZoneFormat$Style[Style.ISO_BASIC_LOCAL_FIXED.ordinal()] = 14;
            } catch (NoSuchFieldError e18) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$TimeZoneFormat$Style[Style.ISO_BASIC_FULL.ordinal()] = 15;
            } catch (NoSuchFieldError e19) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$TimeZoneFormat$Style[Style.ISO_BASIC_LOCAL_FULL.ordinal()] = 16;
            } catch (NoSuchFieldError e20) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$TimeZoneFormat$Style[Style.ISO_EXTENDED_FIXED.ordinal()] = 17;
            } catch (NoSuchFieldError e21) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$TimeZoneFormat$Style[Style.ISO_EXTENDED_LOCAL_FIXED.ordinal()] = 18;
            } catch (NoSuchFieldError e22) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$TimeZoneFormat$Style[Style.ISO_EXTENDED_FULL.ordinal()] = 19;
            } catch (NoSuchFieldError e23) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$TimeZoneFormat$Style[Style.ISO_EXTENDED_LOCAL_FULL.ordinal()] = 20;
            } catch (NoSuchFieldError e24) {
            }
        }
    }

    private TimeType getTimeType(TimeZoneNames.NameType nameType) {
        switch (AnonymousClass1.$SwitchMap$com$ibm$icu$text$TimeZoneNames$NameType[nameType.ordinal()]) {
            case 1:
            case 2:
                return TimeType.STANDARD;
            case 3:
            case 4:
                return TimeType.DAYLIGHT;
            default:
                return TimeType.UNKNOWN;
        }
    }

    private void initGMTPattern(String gmtPattern) {
        int idx = gmtPattern.indexOf("{0}");
        if (idx >= 0) {
            this._gmtPattern = gmtPattern;
            this._gmtPatternPrefix = unquote(gmtPattern.substring(0, idx));
            this._gmtPatternSuffix = unquote(gmtPattern.substring(idx + 3));
            return;
        }
        throw new IllegalArgumentException("Bad localized GMT pattern: " + gmtPattern);
    }

    private static String unquote(String s) {
        if (s.indexOf(39) < 0) {
            return s;
        }
        boolean isPrevQuote = false;
        boolean inQuote = false;
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '\'') {
                if (isPrevQuote) {
                    buf.append(c);
                    isPrevQuote = false;
                } else {
                    isPrevQuote = true;
                }
                inQuote = !inQuote;
            } else {
                isPrevQuote = false;
                buf.append(c);
            }
        }
        return buf.toString();
    }

    private void initGMTOffsetPatterns(String[] gmtOffsetPatterns) {
        int size = GMTOffsetPatternType.values().length;
        if (gmtOffsetPatterns.length >= size) {
            Object[][] gmtOffsetPatternItems = new Object[size][];
            for (GMTOffsetPatternType t : GMTOffsetPatternType.values()) {
                int idx = t.ordinal();
                gmtOffsetPatternItems[idx] = parseOffsetPattern(gmtOffsetPatterns[idx], t.required());
            }
            String[] strArr = new String[size];
            this._gmtOffsetPatterns = strArr;
            System.arraycopy(gmtOffsetPatterns, 0, strArr, 0, size);
            this._gmtOffsetPatternItems = gmtOffsetPatternItems;
            checkAbuttingHoursAndMinutes();
            return;
        }
        throw new IllegalArgumentException("Insufficient number of elements in gmtOffsetPatterns");
    }

    private void checkAbuttingHoursAndMinutes() {
        this._abuttingOffsetHoursAndMinutes = false;
        for (Object[] items : this._gmtOffsetPatternItems) {
            boolean afterH = false;
            for (Object item : r1[r3]) {
                if (item instanceof GMTOffsetField) {
                    GMTOffsetField fld = (GMTOffsetField) item;
                    if (afterH) {
                        this._abuttingOffsetHoursAndMinutes = true;
                    } else if (fld.getType() == 'H') {
                        afterH = true;
                    }
                } else if (afterH) {
                    break;
                }
            }
        }
    }

    private static class GMTOffsetField {
        final char _type;
        final int _width;

        GMTOffsetField(char type, int width) {
            this._type = type;
            this._width = width;
        }

        /* access modifiers changed from: package-private */
        public char getType() {
            return this._type;
        }

        /* access modifiers changed from: package-private */
        public int getWidth() {
            return this._width;
        }

        static boolean isValid(char type, int width) {
            return width == 1 || width == 2;
        }
    }

    private static Object[] parseOffsetPattern(String pattern, String letters) {
        boolean isPrevQuote = false;
        boolean inQuote = false;
        StringBuilder text = new StringBuilder();
        char itemType = 0;
        int itemLength = 1;
        boolean invalidPattern = false;
        List<Object> items = new ArrayList<>();
        BitSet checkBits = new BitSet(letters.length());
        int i = 0;
        while (true) {
            boolean z = false;
            if (i >= pattern.length()) {
                break;
            }
            char ch = pattern.charAt(i);
            if (ch == '\'') {
                if (!isPrevQuote) {
                    isPrevQuote = true;
                    if (itemType != 0) {
                        if (!GMTOffsetField.isValid(itemType, itemLength)) {
                            invalidPattern = true;
                            break;
                        }
                        items.add(new GMTOffsetField(itemType, itemLength));
                        itemType = 0;
                    }
                } else {
                    text.append('\'');
                    isPrevQuote = false;
                }
                if (!inQuote) {
                    z = true;
                }
                inQuote = z;
            } else {
                isPrevQuote = false;
                if (inQuote) {
                    text.append(ch);
                } else {
                    int patFieldIdx = letters.indexOf(ch);
                    if (patFieldIdx < 0) {
                        if (itemType != 0) {
                            if (!GMTOffsetField.isValid(itemType, itemLength)) {
                                invalidPattern = true;
                                break;
                            }
                            items.add(new GMTOffsetField(itemType, itemLength));
                            itemType = 0;
                        }
                        text.append(ch);
                    } else if (ch == itemType) {
                        itemLength++;
                    } else {
                        if (itemType != 0) {
                            if (!GMTOffsetField.isValid(itemType, itemLength)) {
                                invalidPattern = true;
                                break;
                            }
                            items.add(new GMTOffsetField(itemType, itemLength));
                        } else if (text.length() > 0) {
                            items.add(text.toString());
                            text.setLength(0);
                        }
                        itemType = ch;
                        itemLength = 1;
                        checkBits.set(patFieldIdx);
                    }
                }
            }
            i++;
        }
        if (!invalidPattern) {
            if (itemType == 0) {
                if (text.length() > 0) {
                    items.add(text.toString());
                    text.setLength(0);
                }
            } else if (GMTOffsetField.isValid(itemType, itemLength)) {
                items.add(new GMTOffsetField(itemType, itemLength));
            } else {
                invalidPattern = true;
            }
        }
        if (!invalidPattern && checkBits.cardinality() == letters.length()) {
            return items.toArray(new Object[items.size()]);
        }
        throw new IllegalStateException("Bad localized GMT offset pattern: " + pattern);
    }

    private static String expandOffsetPattern(String offsetHM) {
        int idx_mm = offsetHM.indexOf("mm");
        if (idx_mm >= 0) {
            String sep = ":";
            int idx_H = offsetHM.substring(0, idx_mm).lastIndexOf(DateFormat.HOUR24);
            if (idx_H >= 0) {
                sep = offsetHM.substring(idx_H + 1, idx_mm);
            }
            return offsetHM.substring(0, idx_mm + 2) + sep + "ss" + offsetHM.substring(idx_mm + 2);
        }
        throw new RuntimeException("Bad time zone hour pattern data");
    }

    private static String truncateOffsetPattern(String offsetHM) {
        int idx_mm = offsetHM.indexOf("mm");
        if (idx_mm >= 0) {
            int idx_HH = offsetHM.substring(0, idx_mm).lastIndexOf("HH");
            if (idx_HH >= 0) {
                return offsetHM.substring(0, idx_HH + 2);
            }
            int idx_H = offsetHM.substring(0, idx_mm).lastIndexOf(DateFormat.HOUR24);
            if (idx_H >= 0) {
                return offsetHM.substring(0, idx_H + 1);
            }
            throw new RuntimeException("Bad time zone hour pattern data");
        }
        throw new RuntimeException("Bad time zone hour pattern data");
    }

    private void appendOffsetDigits(StringBuilder buf, int n, int minDigits) {
        if (n < 0 || n >= 60) {
            throw new AssertionError();
        }
        int numDigits = n >= 10 ? 2 : 1;
        for (int i = 0; i < minDigits - numDigits; i++) {
            buf.append(this._gmtOffsetDigits[0]);
        }
        if (numDigits == 2) {
            buf.append(this._gmtOffsetDigits[n / 10]);
        }
        buf.append(this._gmtOffsetDigits[n % 10]);
    }

    private TimeZone getTimeZoneForOffset(int offset) {
        if (offset == 0) {
            return TimeZone.getTimeZone(TZID_GMT);
        }
        return ZoneMeta.getCustomTimeZone(offset);
    }

    private int parseOffsetLocalizedGMT(String text, ParsePosition pos, boolean isShort, Output<Boolean> hasDigitOffset) {
        String str = text;
        ParsePosition parsePosition = pos;
        Output<Boolean> output = hasDigitOffset;
        int start = pos.getIndex();
        int[] parsedLength = {0};
        if (output != null) {
            output.value = false;
        }
        int offset = parseOffsetLocalizedGMTPattern(str, start, isShort, parsedLength);
        if (parsedLength[0] > 0) {
            if (output != null) {
                output.value = true;
            }
            parsePosition.setIndex(parsedLength[0] + start);
            return offset;
        }
        int offset2 = parseOffsetDefaultLocalizedGMT(str, start, parsedLength);
        if (parsedLength[0] > 0) {
            if (output != null) {
                output.value = true;
            }
            parsePosition.setIndex(parsedLength[0] + start);
            return offset2;
        }
        String str2 = this._gmtZeroFormat;
        if (text.regionMatches(true, start, str2, 0, str2.length())) {
            parsePosition.setIndex(this._gmtZeroFormat.length() + start);
            return 0;
        }
        String[] strArr = ALT_GMT_STRINGS;
        int length = strArr.length;
        int i = 0;
        while (i < length) {
            String defGMTZero = strArr[i];
            int i2 = i;
            int i3 = length;
            if (text.regionMatches(true, start, defGMTZero, 0, defGMTZero.length())) {
                parsePosition.setIndex(defGMTZero.length() + start);
                return 0;
            }
            i = i2 + 1;
            length = i3;
        }
        parsePosition.setErrorIndex(start);
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0048, code lost:
        if (r19.regionMatches(true, r7, r0._gmtPatternSuffix, 0, r3) == false) goto L_0x004d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0052  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int parseOffsetLocalizedGMTPattern(java.lang.String r19, int r20, boolean r21, int[] r22) {
        /*
            r18 = this;
            r0 = r18
            r7 = r20
            r8 = 0
            r9 = 0
            java.lang.String r1 = r0._gmtPatternPrefix
            int r10 = r1.length()
            r11 = 0
            if (r10 <= 0) goto L_0x0020
            r2 = 1
            java.lang.String r4 = r0._gmtPatternPrefix
            r5 = 0
            r1 = r19
            r3 = r7
            r6 = r10
            boolean r1 = r1.regionMatches(r2, r3, r4, r5, r6)
            if (r1 != 0) goto L_0x0020
            r2 = r19
            goto L_0x004d
        L_0x0020:
            int r7 = r7 + r10
            r1 = 1
            int[] r1 = new int[r1]
            r2 = r19
            int r8 = r0.parseOffsetFields(r2, r7, r11, r1)
            r3 = r1[r11]
            if (r3 != 0) goto L_0x002f
            goto L_0x004d
        L_0x002f:
            r3 = r1[r11]
            int r7 = r7 + r3
            java.lang.String r3 = r0._gmtPatternSuffix
            int r3 = r3.length()
            if (r3 <= 0) goto L_0x004b
            r13 = 1
            java.lang.String r15 = r0._gmtPatternSuffix
            r16 = 0
            r12 = r19
            r14 = r7
            r17 = r3
            boolean r4 = r12.regionMatches(r13, r14, r15, r16, r17)
            if (r4 != 0) goto L_0x004b
            goto L_0x004d
        L_0x004b:
            int r7 = r7 + r3
            r9 = 1
        L_0x004d:
            if (r9 == 0) goto L_0x0052
            int r1 = r7 - r20
            goto L_0x0053
        L_0x0052:
            r1 = r11
        L_0x0053:
            r22[r11] = r1
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.TimeZoneFormat.parseOffsetLocalizedGMTPattern(java.lang.String, int, boolean, int[]):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:55:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int parseOffsetFields(java.lang.String r27, int r28, boolean r29, int[] r30) {
        /*
            r26 = this;
            r6 = r26
            r7 = r30
            r0 = 0
            r8 = 0
            r9 = 1
            r10 = 1
            r11 = 0
            if (r7 == 0) goto L_0x0010
            int r1 = r7.length
            if (r1 < r10) goto L_0x0010
            r7[r11] = r11
        L_0x0010:
            r12 = r11
            r13 = r11
            r14 = r11
            r1 = 3
            int[] r1 = new int[r1]
            r1 = {0, 0, 0} // fill-array
            r15 = r1
            com.ibm.icu.text.TimeZoneFormat$GMTOffsetPatternType[] r5 = PARSE_GMT_OFFSET_TYPES
            int r4 = r5.length
            r16 = r0
            r3 = r11
        L_0x0020:
            r17 = 2
            r18 = -1
            if (r3 >= r4) goto L_0x006c
            r19 = r5[r3]
            java.lang.Object[][] r0 = r6._gmtOffsetPatternItems
            int r1 = r19.ordinal()
            r20 = r0[r1]
            if (r20 == 0) goto L_0x0066
            r21 = 0
            r0 = r26
            r1 = r27
            r2 = r28
            r22 = r3
            r3 = r20
            r23 = r4
            r4 = r21
            r21 = r5
            r5 = r15
            int r16 = r0.parseOffsetFieldsWithPattern(r1, r2, r3, r4, r5)
            if (r16 <= 0) goto L_0x005f
            boolean r0 = r19.isPositive()
            if (r0 == 0) goto L_0x0053
            r0 = r10
            goto L_0x0055
        L_0x0053:
            r0 = r18
        L_0x0055:
            r9 = r0
            r14 = r15[r11]
            r13 = r15[r10]
            r12 = r15[r17]
            r5 = r16
            goto L_0x006e
        L_0x005f:
            int r3 = r22 + 1
            r5 = r21
            r4 = r23
            goto L_0x0020
        L_0x0066:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>()
            throw r0
        L_0x006c:
            r5 = r16
        L_0x006e:
            if (r5 <= 0) goto L_0x00d1
            boolean r0 = r6._abuttingOffsetHoursAndMinutes
            if (r0 == 0) goto L_0x00d1
            r0 = 0
            r16 = 1
            com.ibm.icu.text.TimeZoneFormat$GMTOffsetPatternType[] r4 = PARSE_GMT_OFFSET_TYPES
            int r3 = r4.length
            r19 = r0
            r2 = r11
        L_0x007d:
            if (r2 >= r3) goto L_0x00c1
            r20 = r4[r2]
            java.lang.Object[][] r0 = r6._gmtOffsetPatternItems
            int r1 = r20.ordinal()
            r21 = r0[r1]
            if (r21 == 0) goto L_0x00bb
            r22 = 1
            r0 = r26
            r1 = r27
            r23 = r2
            r2 = r28
            r24 = r3
            r3 = r21
            r25 = r4
            r4 = r22
            r10 = r5
            r5 = r15
            int r19 = r0.parseOffsetFieldsWithPattern(r1, r2, r3, r4, r5)
            if (r19 <= 0) goto L_0x00b2
            boolean r0 = r20.isPositive()
            if (r0 == 0) goto L_0x00ad
            r18 = 1
        L_0x00ad:
            r16 = r18
            r0 = r19
            goto L_0x00c4
        L_0x00b2:
            int r2 = r23 + 1
            r5 = r10
            r3 = r24
            r4 = r25
            r10 = 1
            goto L_0x007d
        L_0x00bb:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>()
            throw r0
        L_0x00c1:
            r10 = r5
            r0 = r19
        L_0x00c4:
            if (r0 <= r10) goto L_0x00d2
            r5 = r0
            r9 = r16
            r14 = r15[r11]
            r1 = 1
            r13 = r15[r1]
            r12 = r15[r17]
            goto L_0x00d3
        L_0x00d1:
            r10 = r5
        L_0x00d2:
            r5 = r10
        L_0x00d3:
            if (r7 == 0) goto L_0x00db
            int r0 = r7.length
            r1 = 1
            if (r0 < r1) goto L_0x00db
            r7[r11] = r5
        L_0x00db:
            if (r5 <= 0) goto L_0x00e7
            int r0 = r14 * 60
            int r0 = r0 + r13
            int r0 = r0 * 60
            int r0 = r0 + r12
            int r0 = r0 * 1000
            int r8 = r0 * r9
        L_0x00e7:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.TimeZoneFormat.parseOffsetFields(java.lang.String, int, boolean, int[]):int");
    }

    private int parseOffsetFieldsWithPattern(String text, int start, Object[] patternItems, boolean forceSingleHourDigit, int[] fields) {
        int i;
        Object[] objArr = patternItems;
        int[] iArr = fields;
        if (iArr == null || iArr.length < 3) {
            throw new AssertionError();
        }
        int i2 = 2;
        iArr[2] = 0;
        iArr[1] = 0;
        iArr[0] = 0;
        boolean failed = false;
        int offsetS = 0;
        int offsetM = 0;
        int offsetH = 0;
        int idx = start;
        int[] tmpParsedLen = {0};
        int i3 = 0;
        while (true) {
            if (i3 >= objArr.length) {
                break;
            }
            if (objArr[i3] instanceof String) {
                String patStr = (String) objArr[i3];
                int len = patStr.length();
                int patIdx = 0;
                if (i3 != 0) {
                    String str = text;
                } else if (idx >= text.length()) {
                    String str2 = text;
                } else if (!PatternProps.isWhiteSpace(text.codePointAt(idx))) {
                    while (len > 0) {
                        int cp = patStr.codePointAt(patIdx);
                        if (!PatternProps.isWhiteSpace(cp)) {
                            break;
                        }
                        int cpLen = Character.charCount(cp);
                        len -= cpLen;
                        patIdx += cpLen;
                    }
                }
                int len2 = len;
                String str3 = patStr;
                i = i3;
                if (!text.regionMatches(true, idx, patStr, patIdx, len2)) {
                    failed = true;
                    break;
                }
                idx += len2;
            } else {
                i = i3;
                if (objArr[i] instanceof GMTOffsetField) {
                    char fieldType = ((GMTOffsetField) objArr[i]).getType();
                    if (fieldType == 'H') {
                        int maxDigits = forceSingleHourDigit ? 1 : i2;
                        char c = fieldType;
                        offsetH = parseOffsetFieldWithLocalizedDigits(text, idx, 1, maxDigits, 0, 23, tmpParsedLen);
                    } else {
                        char fieldType2 = fieldType;
                        if (fieldType2 == 'm') {
                            offsetM = parseOffsetFieldWithLocalizedDigits(text, idx, 2, 2, 0, 59, tmpParsedLen);
                        } else if (fieldType2 == 's') {
                            offsetS = parseOffsetFieldWithLocalizedDigits(text, idx, 2, 2, 0, 59, tmpParsedLen);
                        }
                    }
                    if (tmpParsedLen[0] == 0) {
                        failed = true;
                        break;
                    }
                    idx += tmpParsedLen[0];
                } else {
                    throw new AssertionError();
                }
            }
            i3 = i + 1;
            i2 = 2;
        }
        if (failed) {
            return 0;
        }
        iArr[0] = offsetH;
        iArr[1] = offsetM;
        iArr[2] = offsetS;
        return idx - start;
    }

    private int parseOffsetDefaultLocalizedGMT(String text, int start, int[] parsedLen) {
        int sign;
        int idx;
        String str = text;
        int idx2 = start;
        int offset = 0;
        int parsed = 0;
        int gmtLen = 0;
        String[] strArr = ALT_GMT_STRINGS;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            String gmt = strArr[i];
            int len = gmt.length();
            if (text.regionMatches(true, idx2, gmt, 0, len)) {
                gmtLen = len;
                break;
            }
            i++;
        }
        if (gmtLen != 0) {
            int idx3 = idx2 + gmtLen;
            if (idx3 + 1 < text.length()) {
                char c = str.charAt(idx3);
                if (c == '+') {
                    sign = 1;
                } else if (c == '-') {
                    sign = -1;
                }
                int idx4 = idx3 + 1;
                int[] lenWithSep = {0};
                int offsetWithSep = parseDefaultOffsetFields(str, idx4, DEFAULT_GMT_OFFSET_SEP, lenWithSep);
                if (lenWithSep[0] == text.length() - idx4) {
                    idx = idx4 + lenWithSep[0];
                    offset = offsetWithSep * sign;
                } else {
                    int[] lenAbut = {0};
                    int offsetAbut = parseAbuttingOffsetFields(str, idx4, lenAbut);
                    if (lenWithSep[0] > lenAbut[0]) {
                        offset = offsetWithSep * sign;
                        idx = idx4 + lenWithSep[0];
                    } else {
                        offset = offsetAbut * sign;
                        idx = idx4 + lenAbut[0];
                    }
                }
                parsed = idx - start;
            }
        }
        parsedLen[0] = parsed;
        return offset;
    }

    private int parseDefaultOffsetFields(String text, int start, char separator, int[] parsedLen) {
        int sec;
        String str = text;
        int i = start;
        char c = separator;
        int max = text.length();
        int idx = start;
        int[] len = {0};
        int min = 0;
        int hour = parseOffsetFieldWithLocalizedDigits(text, idx, 1, 2, 0, 23, len);
        if (len[0] == 0) {
            sec = 0;
        } else {
            idx += len[0];
            if (idx + 1 < max && str.charAt(idx) == c) {
                min = parseOffsetFieldWithLocalizedDigits(text, idx + 1, 2, 2, 0, 59, len);
                if (len[0] == 0) {
                    sec = 0;
                } else {
                    idx += len[0] + 1;
                    if (idx + 1 < max && str.charAt(idx) == c) {
                        int sec2 = parseOffsetFieldWithLocalizedDigits(text, idx + 1, 2, 2, 0, 59, len);
                        if (len[0] == 0) {
                            sec = sec2;
                        } else {
                            idx += len[0] + 1;
                            sec = sec2;
                        }
                    }
                }
            }
            sec = 0;
        }
        if (idx == i) {
            parsedLen[0] = 0;
            return 0;
        }
        parsedLen[0] = idx - i;
        return (MILLIS_PER_HOUR * hour) + (MILLIS_PER_MINUTE * min) + (sec * 1000);
    }

    private int parseAbuttingOffsetFields(String text, int start, int[] parsedLen) {
        int[] digits = new int[6];
        int[] parsed = new int[6];
        int idx = start;
        int[] len = {0};
        int numDigits = 0;
        int i = 0;
        while (true) {
            if (i >= 6) {
                String str = text;
                break;
            }
            digits[i] = parseSingleLocalizedDigit(text, idx, len);
            if (digits[i] < 0) {
                break;
            }
            idx += len[0];
            parsed[i] = idx - start;
            numDigits++;
            i++;
        }
        if (numDigits == 0) {
            parsedLen[0] = 0;
            return 0;
        }
        while (numDigits > 0) {
            int hour = 0;
            int min = 0;
            int sec = 0;
            if (numDigits <= 0 || numDigits > 6) {
                throw new AssertionError();
            }
            switch (numDigits) {
                case 1:
                    hour = digits[0];
                    break;
                case 2:
                    hour = (digits[0] * 10) + digits[1];
                    break;
                case 3:
                    hour = digits[0];
                    min = (digits[1] * 10) + digits[2];
                    break;
                case 4:
                    hour = (digits[0] * 10) + digits[1];
                    min = (digits[2] * 10) + digits[3];
                    break;
                case 5:
                    hour = digits[0];
                    min = (digits[1] * 10) + digits[2];
                    sec = (digits[3] * 10) + digits[4];
                    break;
                case 6:
                    hour = (digits[0] * 10) + digits[1];
                    min = (digits[2] * 10) + digits[3];
                    sec = (digits[4] * 10) + digits[5];
                    break;
            }
            if (hour > 23 || min > 59 || sec > 59) {
                numDigits--;
            } else {
                int offset = (MILLIS_PER_HOUR * hour) + (MILLIS_PER_MINUTE * min) + (sec * 1000);
                parsedLen[0] = parsed[numDigits - 1];
                return offset;
            }
        }
        return 0;
    }

    private int parseOffsetFieldWithLocalizedDigits(String text, int start, int minDigits, int maxDigits, int minVal, int maxVal, int[] parsedLen) {
        int tmpVal;
        parsedLen[0] = 0;
        int decVal = 0;
        int numDigits = 0;
        int idx = start;
        int[] digitLen = {0};
        while (idx < text.length() && numDigits < maxDigits && (digit = parseSingleLocalizedDigit(text, idx, digitLen)) >= 0 && (tmpVal = (decVal * 10) + digit) <= maxVal) {
            decVal = tmpVal;
            numDigits++;
            idx += digitLen[0];
        }
        if (numDigits < minDigits || decVal < minVal) {
            return -1;
        }
        parsedLen[0] = idx - start;
        return decVal;
    }

    private int parseSingleLocalizedDigit(String text, int start, int[] len) {
        int digit = -1;
        len[0] = 0;
        if (start < text.length()) {
            int cp = Character.codePointAt(text, start);
            int i = 0;
            while (true) {
                String[] strArr = this._gmtOffsetDigits;
                if (i >= strArr.length) {
                    break;
                } else if (cp == strArr[i].codePointAt(0)) {
                    digit = i;
                    break;
                } else {
                    i++;
                }
            }
            if (digit < 0) {
                digit = UCharacter.digit(cp);
            }
            if (digit >= 0) {
                len[0] = Character.charCount(cp);
            }
        }
        return digit;
    }

    private static String[] toCodePoints(String str) {
        int len = str.codePointCount(0, str.length());
        String[] codePoints = new String[len];
        int offset = 0;
        for (int i = 0; i < len; i++) {
            int codeLen = Character.charCount(str.codePointAt(offset));
            codePoints[i] = str.substring(offset, offset + codeLen);
            offset += codeLen;
        }
        return codePoints;
    }

    private static int parseOffsetISO8601(String text, ParsePosition pos, boolean extendedOnly, Output<Boolean> hasDigitOffset) {
        int sign;
        if (hasDigitOffset != null) {
            hasDigitOffset.value = false;
        }
        int start = pos.getIndex();
        if (start >= text.length()) {
            pos.setErrorIndex(start);
            return 0;
        }
        char firstChar = text.charAt(start);
        if (Character.toUpperCase(firstChar) == ISO8601_UTC.charAt(0)) {
            pos.setIndex(start + 1);
            return 0;
        }
        if (firstChar == '+') {
            sign = 1;
        } else if (firstChar == '-') {
            sign = -1;
        } else {
            pos.setErrorIndex(start);
            return 0;
        }
        ParsePosition posOffset = new ParsePosition(start + 1);
        int offset = parseAsciiOffsetFields(text, posOffset, DEFAULT_GMT_OFFSET_SEP, OffsetFields.H, OffsetFields.HMS);
        if (posOffset.getErrorIndex() == -1 && !extendedOnly && posOffset.getIndex() - start <= 3) {
            ParsePosition posBasic = new ParsePosition(start + 1);
            int tmpOffset = parseAbuttingAsciiOffsetFields(text, posBasic, OffsetFields.H, OffsetFields.HMS, false);
            if (posBasic.getErrorIndex() == -1 && posBasic.getIndex() > posOffset.getIndex()) {
                offset = tmpOffset;
                posOffset.setIndex(posBasic.getIndex());
            }
        }
        if (posOffset.getErrorIndex() != -1) {
            pos.setErrorIndex(start);
            return 0;
        }
        pos.setIndex(posOffset.getIndex());
        if (hasDigitOffset != null) {
            hasDigitOffset.value = true;
        }
        return sign * offset;
    }

    private static int parseAbuttingAsciiOffsetFields(String text, ParsePosition pos, OffsetFields minFields, OffsetFields maxFields, boolean fixedHourWidth) {
        ParsePosition parsePosition = pos;
        int start = pos.getIndex();
        int minDigits = ((minFields.ordinal() + 1) * 2) - (fixedHourWidth ^ true ? 1 : 0);
        int[] digits = new int[((maxFields.ordinal() + 1) * 2)];
        int numDigits = 0;
        int idx = start;
        while (true) {
            if (numDigits >= digits.length || idx >= text.length()) {
                String str = text;
            } else {
                int digit = ASCII_DIGITS.indexOf(text.charAt(idx));
                if (digit < 0) {
                    break;
                }
                digits[numDigits] = digit;
                numDigits++;
                idx++;
            }
        }
        String str2 = text;
        if (fixedHourWidth && (numDigits & 1) != 0) {
            numDigits--;
        }
        if (numDigits < minDigits) {
            parsePosition.setErrorIndex(start);
            return 0;
        }
        int hour = 0;
        int min = 0;
        int sec = 0;
        boolean bParsed = false;
        while (true) {
            if (numDigits >= minDigits) {
                switch (numDigits) {
                    case 1:
                        hour = digits[0];
                        break;
                    case 2:
                        hour = (digits[0] * 10) + digits[1];
                        break;
                    case 3:
                        hour = digits[0];
                        min = (digits[1] * 10) + digits[2];
                        break;
                    case 4:
                        hour = (digits[0] * 10) + digits[1];
                        min = (digits[2] * 10) + digits[3];
                        break;
                    case 5:
                        hour = digits[0];
                        min = (digits[1] * 10) + digits[2];
                        sec = (digits[3] * 10) + digits[4];
                        break;
                    case 6:
                        hour = (digits[0] * 10) + digits[1];
                        min = (digits[2] * 10) + digits[3];
                        sec = (digits[4] * 10) + digits[5];
                        break;
                }
                if (hour > 23 || min > 59 || sec > 59) {
                    numDigits -= fixedHourWidth ? 2 : 1;
                    sec = 0;
                    min = 0;
                    hour = 0;
                } else {
                    bParsed = true;
                }
            }
        }
        if (!bParsed) {
            parsePosition.setErrorIndex(start);
            return 0;
        }
        parsePosition.setIndex(start + numDigits);
        return ((((hour * 60) + min) * 60) + sec) * 1000;
    }

    private static int parseAsciiOffsetFields(String text, ParsePosition pos, char sep, OffsetFields minFields, OffsetFields maxFields) {
        int digit;
        ParsePosition parsePosition = pos;
        int start = pos.getIndex();
        int[] fieldVal = {0, 0, 0};
        int[] fieldLen = {0, -1, -1};
        int idx = start;
        int fieldIdx = 0;
        while (true) {
            if (idx >= text.length() || fieldIdx > maxFields.ordinal()) {
                String str = text;
                char c = sep;
            } else {
                String str2 = text;
                char c2 = text.charAt(idx);
                if (c2 != sep) {
                    if (fieldLen[fieldIdx] == -1 || (digit = ASCII_DIGITS.indexOf(c2)) < 0) {
                        break;
                    }
                    fieldVal[fieldIdx] = (fieldVal[fieldIdx] * 10) + digit;
                    fieldLen[fieldIdx] = fieldLen[fieldIdx] + 1;
                    if (fieldLen[fieldIdx] >= 2) {
                        fieldIdx++;
                    }
                } else if (fieldIdx == 0) {
                    if (fieldLen[0] == 0) {
                        break;
                    }
                    fieldIdx++;
                } else if (fieldLen[fieldIdx] != -1) {
                    break;
                } else {
                    fieldLen[fieldIdx] = 0;
                }
                idx++;
            }
        }
        int offset = 0;
        int parsedLen = 0;
        OffsetFields parsedFields = null;
        if (fieldLen[0] != 0) {
            if (fieldVal[0] > 23) {
                offset = (fieldVal[0] / 10) * MILLIS_PER_HOUR;
                parsedFields = OffsetFields.H;
                parsedLen = 1;
            } else {
                offset = fieldVal[0] * MILLIS_PER_HOUR;
                parsedLen = fieldLen[0];
                parsedFields = OffsetFields.H;
                if (fieldLen[1] == 2 && fieldVal[1] <= 59) {
                    offset += fieldVal[1] * MILLIS_PER_MINUTE;
                    parsedLen += fieldLen[1] + 1;
                    parsedFields = OffsetFields.HM;
                    if (fieldLen[2] == 2 && fieldVal[2] <= 59) {
                        offset += fieldVal[2] * 1000;
                        parsedLen += fieldLen[2] + 1;
                        parsedFields = OffsetFields.HMS;
                    }
                }
            }
        }
        if (parsedFields == null || parsedFields.ordinal() < minFields.ordinal()) {
            parsePosition.setErrorIndex(start);
            return 0;
        }
        parsePosition.setIndex(start + parsedLen);
        return offset;
    }

    private static String parseZoneID(String text, ParsePosition pos) {
        if (ZONE_ID_TRIE == null) {
            synchronized (TimeZoneFormat.class) {
                if (ZONE_ID_TRIE == null) {
                    TextTrieMap<String> trie = new TextTrieMap<>(true);
                    for (String id : TimeZone.getAvailableIDs()) {
                        trie.put(id, id);
                    }
                    ZONE_ID_TRIE = trie;
                }
            }
        }
        TextTrieMap.Output trieOutput = new TextTrieMap.Output();
        Iterator<String> itr = ZONE_ID_TRIE.get(text, pos.getIndex(), trieOutput);
        if (itr != null) {
            String resolvedID = itr.next();
            pos.setIndex(pos.getIndex() + trieOutput.matchLength);
            return resolvedID;
        }
        pos.setErrorIndex(pos.getIndex());
        return null;
    }

    private static String parseShortZoneID(String text, ParsePosition pos) {
        if (SHORT_ZONE_ID_TRIE == null) {
            synchronized (TimeZoneFormat.class) {
                if (SHORT_ZONE_ID_TRIE == null) {
                    TextTrieMap<String> trie = new TextTrieMap<>(true);
                    for (String id : TimeZone.getAvailableIDs(TimeZone.SystemTimeZoneType.CANONICAL, (String) null, (Integer) null)) {
                        String shortID = ZoneMeta.getShortID(id);
                        if (shortID != null) {
                            trie.put(shortID, id);
                        }
                    }
                    trie.put(UNKNOWN_SHORT_ZONE_ID, UNKNOWN_ZONE_ID);
                    SHORT_ZONE_ID_TRIE = trie;
                }
            }
        }
        TextTrieMap.Output trieOutput = new TextTrieMap.Output();
        Iterator<String> itr = SHORT_ZONE_ID_TRIE.get(text, pos.getIndex(), trieOutput);
        if (itr != null) {
            String resolvedID = itr.next();
            pos.setIndex(pos.getIndex() + trieOutput.matchLength);
            return resolvedID;
        }
        pos.setErrorIndex(pos.getIndex());
        return null;
    }

    private String parseExemplarLocation(String text, ParsePosition pos) {
        int startIdx = pos.getIndex();
        int parsedPos = -1;
        String tzID = null;
        Collection<TimeZoneNames.MatchInfo> exemplarMatches = this._tznames.find(text, startIdx, EnumSet.of(TimeZoneNames.NameType.EXEMPLAR_LOCATION));
        if (exemplarMatches != null) {
            TimeZoneNames.MatchInfo exemplarMatch = null;
            for (TimeZoneNames.MatchInfo match : exemplarMatches) {
                if (match.matchLength() + startIdx > parsedPos) {
                    exemplarMatch = match;
                    parsedPos = startIdx + match.matchLength();
                }
            }
            if (exemplarMatch != null) {
                tzID = getTimeZoneID(exemplarMatch.tzID(), exemplarMatch.mzID());
                pos.setIndex(parsedPos);
            }
        }
        if (tzID == null) {
            pos.setErrorIndex(startIdx);
        }
        return tzID;
    }

    private static class TimeZoneFormatCache extends SoftCache<ULocale, TimeZoneFormat, ULocale> {
        private TimeZoneFormatCache() {
        }

        /* synthetic */ TimeZoneFormatCache(AnonymousClass1 x0) {
            this();
        }

        /* access modifiers changed from: protected */
        public TimeZoneFormat createInstance(ULocale key, ULocale data) {
            TimeZoneFormat fmt = new TimeZoneFormat(data);
            fmt.freeze();
            return fmt;
        }
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        ObjectOutputStream.PutField fields = oos.putFields();
        fields.put("_locale", this._locale);
        fields.put("_tznames", this._tznames);
        fields.put("_gmtPattern", this._gmtPattern);
        fields.put("_gmtOffsetPatterns", this._gmtOffsetPatterns);
        fields.put("_gmtOffsetDigits", this._gmtOffsetDigits);
        fields.put("_gmtZeroFormat", this._gmtZeroFormat);
        fields.put("_parseAllStyles", this._parseAllStyles);
        oos.writeFields();
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ObjectInputStream.GetField fields = ois.readFields();
        ULocale uLocale = (ULocale) fields.get("_locale", (Object) null);
        this._locale = uLocale;
        if (uLocale != null) {
            TimeZoneNames timeZoneNames = (TimeZoneNames) fields.get("_tznames", (Object) null);
            this._tznames = timeZoneNames;
            if (timeZoneNames != null) {
                String str = (String) fields.get("_gmtPattern", (Object) null);
                this._gmtPattern = str;
                if (str != null) {
                    String[] tmpGmtOffsetPatterns = (String[]) fields.get("_gmtOffsetPatterns", (Object) null);
                    if (tmpGmtOffsetPatterns == null) {
                        throw new InvalidObjectException("Missing field: gmtOffsetPatterns");
                    } else if (tmpGmtOffsetPatterns.length >= 4) {
                        this._gmtOffsetPatterns = new String[6];
                        if (tmpGmtOffsetPatterns.length == 4) {
                            for (int i = 0; i < 4; i++) {
                                this._gmtOffsetPatterns[i] = tmpGmtOffsetPatterns[i];
                            }
                            this._gmtOffsetPatterns[GMTOffsetPatternType.POSITIVE_H.ordinal()] = truncateOffsetPattern(this._gmtOffsetPatterns[GMTOffsetPatternType.POSITIVE_HM.ordinal()]);
                            this._gmtOffsetPatterns[GMTOffsetPatternType.NEGATIVE_H.ordinal()] = truncateOffsetPattern(this._gmtOffsetPatterns[GMTOffsetPatternType.NEGATIVE_HM.ordinal()]);
                        } else {
                            this._gmtOffsetPatterns = tmpGmtOffsetPatterns;
                        }
                        String[] strArr = (String[]) fields.get("_gmtOffsetDigits", (Object) null);
                        this._gmtOffsetDigits = strArr;
                        if (strArr == null) {
                            throw new InvalidObjectException("Missing field: gmtOffsetDigits");
                        } else if (strArr.length == 10) {
                            String str2 = (String) fields.get("_gmtZeroFormat", (Object) null);
                            this._gmtZeroFormat = str2;
                            if (str2 != null) {
                                this._parseAllStyles = fields.get("_parseAllStyles", false);
                                if (!fields.defaulted("_parseAllStyles")) {
                                    if (this._tznames instanceof TimeZoneNamesImpl) {
                                        this._tznames = TimeZoneNames.getInstance(this._locale);
                                        this._gnames = null;
                                    } else {
                                        this._gnames = new TimeZoneGenericNames(this._locale, this._tznames);
                                    }
                                    initGMTPattern(this._gmtPattern);
                                    initGMTOffsetPatterns(this._gmtOffsetPatterns);
                                    return;
                                }
                                throw new InvalidObjectException("Missing field: parseAllStyles");
                            }
                            throw new InvalidObjectException("Missing field: gmtZeroFormat");
                        } else {
                            throw new InvalidObjectException("Incompatible field: gmtOffsetDigits");
                        }
                    } else {
                        throw new InvalidObjectException("Incompatible field: gmtOffsetPatterns");
                    }
                } else {
                    throw new InvalidObjectException("Missing field: gmtPattern");
                }
            } else {
                throw new InvalidObjectException("Missing field: tznames");
            }
        } else {
            throw new InvalidObjectException("Missing field: locale");
        }
    }

    public boolean isFrozen() {
        return this._frozen;
    }

    public TimeZoneFormat freeze() {
        this._frozen = true;
        return this;
    }

    public TimeZoneFormat cloneAsThawed() {
        TimeZoneFormat copy = (TimeZoneFormat) super.clone();
        copy._frozen = false;
        return copy;
    }
}
