package com.ibm.icu.text;

import com.ibm.icu.impl.CacheBase;
import com.ibm.icu.impl.DontCareFieldPosition;
import com.ibm.icu.impl.ICUResourceBundle;
import com.ibm.icu.impl.SimpleFormatterImpl;
import com.ibm.icu.impl.SoftCache;
import com.ibm.icu.impl.StandardPlural;
import com.ibm.icu.impl.UResource;
import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.text.DisplayContext;
import com.ibm.icu.util.ICUException;
import com.ibm.icu.util.ULocale;
import com.ibm.icu.util.UResourceBundle;
import com.wits.ksw.settings.TxzMessage;
import java.lang.reflect.Array;
import java.util.EnumMap;
import java.util.Locale;

/* loaded from: classes.dex */
public final class RelativeDateTimeFormatter {
    private final BreakIterator breakIterator;
    private final DisplayContext capitalizationContext;
    private final String combinedDateAndTime;
    private final DateFormatSymbols dateFormatSymbols;
    private final ULocale locale;
    private final NumberFormat numberFormat;
    private final EnumMap<Style, EnumMap<RelativeUnit, String[][]>> patternMap;
    private final PluralRules pluralRules;
    private final EnumMap<Style, EnumMap<AbsoluteUnit, EnumMap<Direction, String>>> qualitativeUnitMap;
    private final Style style;
    private int[] styleToDateFormatSymbolsWidth = {1, 3, 2};
    private static final Style[] fallbackCache = new Style[3];
    private static final Cache cache = new Cache(null);

    /* loaded from: classes.dex */
    public enum AbsoluteUnit {
        SUNDAY,
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        DAY,
        WEEK,
        MONTH,
        YEAR,
        NOW,
        QUARTER
    }

    /* loaded from: classes.dex */
    public enum Direction {
        LAST_2,
        LAST,
        THIS,
        NEXT,
        NEXT_2,
        PLAIN
    }

    /* loaded from: classes.dex */
    public enum RelativeDateTimeUnit {
        YEAR,
        QUARTER,
        MONTH,
        WEEK,
        DAY,
        HOUR,
        MINUTE,
        SECOND,
        SUNDAY,
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY
    }

    /* loaded from: classes.dex */
    public enum RelativeUnit {
        SECONDS,
        MINUTES,
        HOURS,
        DAYS,
        WEEKS,
        MONTHS,
        YEARS,
        QUARTERS
    }

    /* loaded from: classes.dex */
    public enum Style {
        LONG,
        SHORT,
        NARROW;
        
        private static final int INDEX_COUNT = 3;
    }

    public static RelativeDateTimeFormatter getInstance() {
        return getInstance(ULocale.getDefault(), null, Style.LONG, DisplayContext.CAPITALIZATION_NONE);
    }

    public static RelativeDateTimeFormatter getInstance(ULocale locale) {
        return getInstance(locale, null, Style.LONG, DisplayContext.CAPITALIZATION_NONE);
    }

    public static RelativeDateTimeFormatter getInstance(Locale locale) {
        return getInstance(ULocale.forLocale(locale));
    }

    public static RelativeDateTimeFormatter getInstance(ULocale locale, NumberFormat nf) {
        return getInstance(locale, nf, Style.LONG, DisplayContext.CAPITALIZATION_NONE);
    }

    public static RelativeDateTimeFormatter getInstance(ULocale locale, NumberFormat nf, Style style, DisplayContext capitalizationContext) {
        NumberFormat nf2;
        RelativeDateTimeFormatterData data = cache.get(locale);
        if (nf == null) {
            nf2 = NumberFormat.getInstance(locale);
        } else {
            nf2 = (NumberFormat) nf.clone();
        }
        return new RelativeDateTimeFormatter(data.qualitativeUnitMap, data.relUnitPatternMap, SimpleFormatterImpl.compileToStringMinMaxArguments(data.dateTimePattern, new StringBuilder(), 2, 2), PluralRules.forLocale(locale), nf2, style, capitalizationContext, capitalizationContext == DisplayContext.CAPITALIZATION_FOR_BEGINNING_OF_SENTENCE ? BreakIterator.getSentenceInstance(locale) : null, locale);
    }

    public static RelativeDateTimeFormatter getInstance(Locale locale, NumberFormat nf) {
        return getInstance(ULocale.forLocale(locale), nf);
    }

    public String format(double quantity, Direction direction, RelativeUnit unit) {
        String result;
        if (direction != Direction.LAST && direction != Direction.NEXT) {
            throw new IllegalArgumentException("direction must be NEXT or LAST");
        }
        int pastFutureIndex = direction == Direction.NEXT ? 1 : 0;
        synchronized (this.numberFormat) {
            StringBuffer formatStr = new StringBuffer();
            DontCareFieldPosition fieldPosition = DontCareFieldPosition.INSTANCE;
            StandardPlural pluralForm = QuantityFormatter.selectPlural(Double.valueOf(quantity), this.numberFormat, this.pluralRules, formatStr, fieldPosition);
            String formatter = getRelativeUnitPluralPattern(this.style, unit, pastFutureIndex, pluralForm);
            result = SimpleFormatterImpl.formatCompiledPattern(formatter, new CharSequence[]{formatStr});
        }
        return adjustForContext(result);
    }

    public String formatNumeric(double offset, RelativeDateTimeUnit unit) {
        RelativeUnit relunit = RelativeUnit.SECONDS;
        switch (C07471.f162x2231a407[unit.ordinal()]) {
            case 1:
                relunit = RelativeUnit.YEARS;
                break;
            case 2:
                relunit = RelativeUnit.QUARTERS;
                break;
            case 3:
                relunit = RelativeUnit.MONTHS;
                break;
            case 4:
                relunit = RelativeUnit.WEEKS;
                break;
            case 5:
                relunit = RelativeUnit.DAYS;
                break;
            case 6:
                relunit = RelativeUnit.HOURS;
                break;
            case 7:
                relunit = RelativeUnit.MINUTES;
                break;
            case 8:
                break;
            default:
                throw new UnsupportedOperationException("formatNumeric does not currently support RelativeUnit.SUNDAY..SATURDAY");
        }
        Direction direction = Direction.NEXT;
        if (Double.compare(offset, 0.0d) < 0) {
            direction = Direction.LAST;
            offset = -offset;
        }
        String result = format(offset, direction, relunit);
        return result != null ? result : "";
    }

    public String format(Direction direction, AbsoluteUnit unit) {
        String result;
        if (unit == AbsoluteUnit.NOW && direction != Direction.PLAIN) {
            throw new IllegalArgumentException("NOW can only accept direction PLAIN.");
        }
        if (direction == Direction.PLAIN && AbsoluteUnit.SUNDAY.ordinal() <= unit.ordinal() && unit.ordinal() <= AbsoluteUnit.SATURDAY.ordinal()) {
            int dateSymbolsDayOrdinal = (unit.ordinal() - AbsoluteUnit.SUNDAY.ordinal()) + 1;
            String[] dayNames = this.dateFormatSymbols.getWeekdays(1, this.styleToDateFormatSymbolsWidth[this.style.ordinal()]);
            result = dayNames[dateSymbolsDayOrdinal];
        } else {
            result = getAbsoluteUnitString(this.style, unit, direction);
        }
        if (result != null) {
            return adjustForContext(result);
        }
        return null;
    }

    public String format(double offset, RelativeDateTimeUnit unit) {
        String result;
        boolean useNumeric = true;
        Direction direction = Direction.THIS;
        if (offset > -2.1d && offset < 2.1d) {
            double offsetx100 = 100.0d * offset;
            int intoffsetx100 = offsetx100 < 0.0d ? (int) (offsetx100 - 0.5d) : (int) (0.5d + offsetx100);
            switch (intoffsetx100) {
                case -200:
                    direction = Direction.LAST_2;
                    useNumeric = false;
                    break;
                case -100:
                    direction = Direction.LAST;
                    useNumeric = false;
                    break;
                case 0:
                    useNumeric = false;
                    break;
                case 100:
                    direction = Direction.NEXT;
                    useNumeric = false;
                    break;
                case 200:
                    direction = Direction.NEXT_2;
                    useNumeric = false;
                    break;
            }
        }
        AbsoluteUnit absunit = AbsoluteUnit.NOW;
        switch (C07471.f162x2231a407[unit.ordinal()]) {
            case 1:
                absunit = AbsoluteUnit.YEAR;
                break;
            case 2:
                absunit = AbsoluteUnit.QUARTER;
                break;
            case 3:
                absunit = AbsoluteUnit.MONTH;
                break;
            case 4:
                absunit = AbsoluteUnit.WEEK;
                break;
            case 5:
                absunit = AbsoluteUnit.DAY;
                break;
            case 6:
            case 7:
            default:
                useNumeric = true;
                break;
            case 8:
                if (direction == Direction.THIS) {
                    direction = Direction.PLAIN;
                    break;
                } else {
                    useNumeric = true;
                    break;
                }
            case 9:
                absunit = AbsoluteUnit.SUNDAY;
                break;
            case 10:
                absunit = AbsoluteUnit.MONDAY;
                break;
            case 11:
                absunit = AbsoluteUnit.TUESDAY;
                break;
            case 12:
                absunit = AbsoluteUnit.WEDNESDAY;
                break;
            case 13:
                absunit = AbsoluteUnit.THURSDAY;
                break;
            case 14:
                absunit = AbsoluteUnit.FRIDAY;
                break;
            case 15:
                absunit = AbsoluteUnit.SATURDAY;
                break;
        }
        if (!useNumeric && (result = format(direction, absunit)) != null && result.length() > 0) {
            return result;
        }
        return formatNumeric(offset, unit);
    }

    private String getAbsoluteUnitString(Style style, AbsoluteUnit unit, Direction direction) {
        Style style2;
        EnumMap<Direction, String> dirMap;
        String result;
        do {
            EnumMap<AbsoluteUnit, EnumMap<Direction, String>> unitMap = this.qualitativeUnitMap.get(style);
            if (unitMap != null && (dirMap = unitMap.get(unit)) != null && (result = dirMap.get(direction)) != null) {
                return result;
            }
            style2 = fallbackCache[style.ordinal()];
            style = style2;
        } while (style2 != null);
        return null;
    }

    public String combineDateAndTime(String relativeDateString, String timeString) {
        return SimpleFormatterImpl.formatCompiledPattern(this.combinedDateAndTime, new CharSequence[]{timeString, relativeDateString});
    }

    public NumberFormat getNumberFormat() {
        NumberFormat numberFormat;
        synchronized (this.numberFormat) {
            numberFormat = (NumberFormat) this.numberFormat.clone();
        }
        return numberFormat;
    }

    public DisplayContext getCapitalizationContext() {
        return this.capitalizationContext;
    }

    public Style getFormatStyle() {
        return this.style;
    }

    private String adjustForContext(String originalFormattedString) {
        String titleCase;
        if (this.breakIterator == null || originalFormattedString.length() == 0 || !UCharacter.isLowerCase(UCharacter.codePointAt(originalFormattedString, 0))) {
            return originalFormattedString;
        }
        synchronized (this.breakIterator) {
            titleCase = UCharacter.toTitleCase(this.locale, originalFormattedString, this.breakIterator, 768);
        }
        return titleCase;
    }

    private RelativeDateTimeFormatter(EnumMap<Style, EnumMap<AbsoluteUnit, EnumMap<Direction, String>>> qualitativeUnitMap, EnumMap<Style, EnumMap<RelativeUnit, String[][]>> patternMap, String combinedDateAndTime, PluralRules pluralRules, NumberFormat numberFormat, Style style, DisplayContext capitalizationContext, BreakIterator breakIterator, ULocale locale) {
        this.qualitativeUnitMap = qualitativeUnitMap;
        this.patternMap = patternMap;
        this.combinedDateAndTime = combinedDateAndTime;
        this.pluralRules = pluralRules;
        this.numberFormat = numberFormat;
        this.style = style;
        if (capitalizationContext.type() != DisplayContext.Type.CAPITALIZATION) {
            throw new IllegalArgumentException(capitalizationContext.toString());
        }
        this.capitalizationContext = capitalizationContext;
        this.breakIterator = breakIterator;
        this.locale = locale;
        this.dateFormatSymbols = new DateFormatSymbols(locale);
    }

    private String getRelativeUnitPluralPattern(Style style, RelativeUnit unit, int pastFutureIndex, StandardPlural pluralForm) {
        String formatter;
        if (pluralForm != StandardPlural.OTHER && (formatter = getRelativeUnitPattern(style, unit, pastFutureIndex, pluralForm)) != null) {
            return formatter;
        }
        return getRelativeUnitPattern(style, unit, pastFutureIndex, StandardPlural.OTHER);
    }

    private String getRelativeUnitPattern(Style style, RelativeUnit unit, int pastFutureIndex, StandardPlural pluralForm) {
        Style style2;
        String[][] spfCompiledPatterns;
        int pluralIndex = pluralForm.ordinal();
        do {
            EnumMap<RelativeUnit, String[][]> unitMap = this.patternMap.get(style);
            if (unitMap != null && (spfCompiledPatterns = unitMap.get(unit)) != null && spfCompiledPatterns[pastFutureIndex][pluralIndex] != null) {
                return spfCompiledPatterns[pastFutureIndex][pluralIndex];
            }
            style2 = fallbackCache[style.ordinal()];
            style = style2;
        } while (style2 != null);
        return null;
    }

    /* loaded from: classes.dex */
    private static class RelativeDateTimeFormatterData {
        public final String dateTimePattern;
        public final EnumMap<Style, EnumMap<AbsoluteUnit, EnumMap<Direction, String>>> qualitativeUnitMap;
        EnumMap<Style, EnumMap<RelativeUnit, String[][]>> relUnitPatternMap;

        public RelativeDateTimeFormatterData(EnumMap<Style, EnumMap<AbsoluteUnit, EnumMap<Direction, String>>> qualitativeUnitMap, EnumMap<Style, EnumMap<RelativeUnit, String[][]>> relUnitPatternMap, String dateTimePattern) {
            this.qualitativeUnitMap = qualitativeUnitMap;
            this.relUnitPatternMap = relUnitPatternMap;
            this.dateTimePattern = dateTimePattern;
        }
    }

    /* loaded from: classes.dex */
    private static class Cache {
        private final CacheBase<String, RelativeDateTimeFormatterData, ULocale> cache;

        private Cache() {
            this.cache = new SoftCache<String, RelativeDateTimeFormatterData, ULocale>() { // from class: com.ibm.icu.text.RelativeDateTimeFormatter.Cache.1
                /* JADX INFO: Access modifiers changed from: protected */
                public RelativeDateTimeFormatterData createInstance(String key, ULocale locale) {
                    return new Loader(locale).load();
                }
            };
        }

        /* synthetic */ Cache(C07471 x0) {
            this();
        }

        public RelativeDateTimeFormatterData get(ULocale locale) {
            String key = locale.toString();
            return (RelativeDateTimeFormatterData) this.cache.getInstance(key, locale);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Direction keyToDirection(UResource.Key key) {
        if (key.contentEquals("-2")) {
            return Direction.LAST_2;
        }
        if (key.contentEquals("-1")) {
            return Direction.LAST;
        }
        if (key.contentEquals(TxzMessage.TXZ_DISMISS)) {
            return Direction.THIS;
        }
        if (key.contentEquals(TxzMessage.TXZ_SHOW)) {
            return Direction.NEXT;
        }
        if (key.contentEquals("2")) {
            return Direction.NEXT_2;
        }
        return null;
    }

    /* loaded from: classes.dex */
    private static final class RelDateTimeDataSink extends UResource.Sink {
        int pastFutureIndex;
        Style style;
        DateTimeUnit unit;
        EnumMap<Style, EnumMap<AbsoluteUnit, EnumMap<Direction, String>>> qualitativeUnitMap = new EnumMap<>(Style.class);
        EnumMap<Style, EnumMap<RelativeUnit, String[][]>> styleRelUnitPatterns = new EnumMap<>(Style.class);

        /* renamed from: sb */
        StringBuilder f163sb = new StringBuilder();

        /* loaded from: classes.dex */
        private enum DateTimeUnit {
            SECOND(RelativeUnit.SECONDS, null),
            MINUTE(RelativeUnit.MINUTES, null),
            HOUR(RelativeUnit.HOURS, null),
            DAY(RelativeUnit.DAYS, AbsoluteUnit.DAY),
            WEEK(RelativeUnit.WEEKS, AbsoluteUnit.WEEK),
            MONTH(RelativeUnit.MONTHS, AbsoluteUnit.MONTH),
            QUARTER(RelativeUnit.QUARTERS, AbsoluteUnit.QUARTER),
            YEAR(RelativeUnit.YEARS, AbsoluteUnit.YEAR),
            SUNDAY(null, AbsoluteUnit.SUNDAY),
            MONDAY(null, AbsoluteUnit.MONDAY),
            TUESDAY(null, AbsoluteUnit.TUESDAY),
            WEDNESDAY(null, AbsoluteUnit.WEDNESDAY),
            THURSDAY(null, AbsoluteUnit.THURSDAY),
            FRIDAY(null, AbsoluteUnit.FRIDAY),
            SATURDAY(null, AbsoluteUnit.SATURDAY);
            
            AbsoluteUnit absUnit;
            RelativeUnit relUnit;

            DateTimeUnit(RelativeUnit relUnit, AbsoluteUnit absUnit) {
                this.relUnit = relUnit;
                this.absUnit = absUnit;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final DateTimeUnit orNullFromString(CharSequence keyword) {
                switch (keyword.length()) {
                    case 3:
                        if ("day".contentEquals(keyword)) {
                            return DAY;
                        }
                        if ("sun".contentEquals(keyword)) {
                            return SUNDAY;
                        }
                        if ("mon".contentEquals(keyword)) {
                            return MONDAY;
                        }
                        if ("tue".contentEquals(keyword)) {
                            return TUESDAY;
                        }
                        if ("wed".contentEquals(keyword)) {
                            return WEDNESDAY;
                        }
                        if ("thu".contentEquals(keyword)) {
                            return THURSDAY;
                        }
                        if ("fri".contentEquals(keyword)) {
                            return FRIDAY;
                        }
                        if ("sat".contentEquals(keyword)) {
                            return SATURDAY;
                        }
                        return null;
                    case 4:
                        if ("hour".contentEquals(keyword)) {
                            return HOUR;
                        }
                        if ("week".contentEquals(keyword)) {
                            return WEEK;
                        }
                        if ("year".contentEquals(keyword)) {
                            return YEAR;
                        }
                        return null;
                    case 5:
                        if ("month".contentEquals(keyword)) {
                            return MONTH;
                        }
                        return null;
                    case 6:
                        if ("minute".contentEquals(keyword)) {
                            return MINUTE;
                        }
                        if ("second".contentEquals(keyword)) {
                            return SECOND;
                        }
                        return null;
                    case 7:
                        if ("quarter".contentEquals(keyword)) {
                            return QUARTER;
                        }
                        return null;
                    default:
                        return null;
                }
            }
        }

        private Style styleFromKey(UResource.Key key) {
            if (key.endsWith("-short")) {
                return Style.SHORT;
            }
            if (key.endsWith("-narrow")) {
                return Style.NARROW;
            }
            return Style.LONG;
        }

        private Style styleFromAlias(UResource.Value value) {
            String s = value.getAliasString();
            if (s.endsWith("-short")) {
                return Style.SHORT;
            }
            if (s.endsWith("-narrow")) {
                return Style.NARROW;
            }
            return Style.LONG;
        }

        private static int styleSuffixLength(Style style) {
            switch (C07471.$SwitchMap$com$ibm$icu$text$RelativeDateTimeFormatter$Style[style.ordinal()]) {
                case 1:
                    return 6;
                case 2:
                    return 7;
                default:
                    return 0;
            }
        }

        public void consumeTableRelative(UResource.Key key, UResource.Value value) {
            AbsoluteUnit absUnit;
            UResource.Table unitTypesTable = value.getTable();
            for (int i = 0; unitTypesTable.getKeyAndValue(i, key, value); i++) {
                if (value.getType() == 0) {
                    String valueString = value.getString();
                    EnumMap<AbsoluteUnit, EnumMap<Direction, String>> absMap = this.qualitativeUnitMap.get(this.style);
                    if (this.unit.relUnit != RelativeUnit.SECONDS || !key.contentEquals(TxzMessage.TXZ_DISMISS)) {
                        Direction keyDirection = RelativeDateTimeFormatter.keyToDirection(key);
                        if (keyDirection != null && (absUnit = this.unit.absUnit) != null) {
                            if (absMap == null) {
                                absMap = new EnumMap<>(AbsoluteUnit.class);
                                this.qualitativeUnitMap.put((EnumMap<Style, EnumMap<AbsoluteUnit, EnumMap<Direction, String>>>) this.style, (Style) absMap);
                            }
                            EnumMap<Direction, String> dirMap = absMap.get(absUnit);
                            if (dirMap == null) {
                                dirMap = new EnumMap<>(Direction.class);
                                absMap.put((EnumMap<AbsoluteUnit, EnumMap<Direction, String>>) absUnit, (AbsoluteUnit) dirMap);
                            }
                            if (dirMap.get(keyDirection) == null) {
                                dirMap.put((EnumMap<Direction, String>) keyDirection, (Direction) value.getString());
                            }
                        }
                    } else {
                        EnumMap<Direction, String> unitStrings = absMap.get(AbsoluteUnit.NOW);
                        if (unitStrings == null) {
                            unitStrings = new EnumMap<>(Direction.class);
                            absMap.put((EnumMap<AbsoluteUnit, EnumMap<Direction, String>>) AbsoluteUnit.NOW, (AbsoluteUnit) unitStrings);
                        }
                        if (unitStrings.get(Direction.PLAIN) == null) {
                            unitStrings.put((EnumMap<Direction, String>) Direction.PLAIN, (Direction) valueString);
                        }
                    }
                }
            }
        }

        public void consumeTableRelativeTime(UResource.Key key, UResource.Value value) {
            if (this.unit.relUnit == null) {
                return;
            }
            UResource.Table unitTypesTable = value.getTable();
            for (int i = 0; unitTypesTable.getKeyAndValue(i, key, value); i++) {
                if (key.contentEquals("past")) {
                    this.pastFutureIndex = 0;
                } else if (key.contentEquals("future")) {
                    this.pastFutureIndex = 1;
                }
                consumeTimeDetail(key, value);
            }
        }

        public void consumeTimeDetail(UResource.Key key, UResource.Value value) {
            UResource.Table unitTypesTable = value.getTable();
            EnumMap<RelativeUnit, String[][]> unitPatterns = this.styleRelUnitPatterns.get(this.style);
            if (unitPatterns == null) {
                unitPatterns = new EnumMap<>(RelativeUnit.class);
                this.styleRelUnitPatterns.put((EnumMap<Style, EnumMap<RelativeUnit, String[][]>>) this.style, (Style) unitPatterns);
            }
            String[][] patterns = unitPatterns.get(this.unit.relUnit);
            if (patterns == null) {
                patterns = (String[][]) Array.newInstance(String.class, 2, StandardPlural.COUNT);
                unitPatterns.put((EnumMap<RelativeUnit, String[][]>) this.unit.relUnit, (RelativeUnit) patterns);
            }
            for (int i = 0; unitTypesTable.getKeyAndValue(i, key, value); i++) {
                if (value.getType() == 0) {
                    int pluralIndex = StandardPlural.indexFromString(key.toString());
                    int i2 = this.pastFutureIndex;
                    if (patterns[i2][pluralIndex] == null) {
                        patterns[i2][pluralIndex] = SimpleFormatterImpl.compileToStringMinMaxArguments(value.getString(), this.f163sb, 0, 1);
                    }
                }
            }
        }

        private void handlePlainDirection(UResource.Key key, UResource.Value value) {
            AbsoluteUnit absUnit = this.unit.absUnit;
            if (absUnit == null) {
                return;
            }
            EnumMap<AbsoluteUnit, EnumMap<Direction, String>> unitMap = this.qualitativeUnitMap.get(this.style);
            if (unitMap == null) {
                unitMap = new EnumMap<>(AbsoluteUnit.class);
                this.qualitativeUnitMap.put((EnumMap<Style, EnumMap<AbsoluteUnit, EnumMap<Direction, String>>>) this.style, (Style) unitMap);
            }
            EnumMap<Direction, String> dirMap = unitMap.get(absUnit);
            if (dirMap == null) {
                dirMap = new EnumMap<>(Direction.class);
                unitMap.put((EnumMap<AbsoluteUnit, EnumMap<Direction, String>>) absUnit, (AbsoluteUnit) dirMap);
            }
            if (dirMap.get(Direction.PLAIN) == null) {
                dirMap.put((EnumMap<Direction, String>) Direction.PLAIN, (Direction) value.toString());
            }
        }

        public void consumeTimeUnit(UResource.Key key, UResource.Value value) {
            UResource.Table unitTypesTable = value.getTable();
            for (int i = 0; unitTypesTable.getKeyAndValue(i, key, value); i++) {
                if (key.contentEquals("dn") && value.getType() == 0) {
                    handlePlainDirection(key, value);
                }
                if (value.getType() == 2) {
                    if (key.contentEquals("relative")) {
                        consumeTableRelative(key, value);
                    } else if (key.contentEquals("relativeTime")) {
                        consumeTableRelativeTime(key, value);
                    }
                }
            }
        }

        private void handleAlias(UResource.Key key, UResource.Value value, boolean noFallback) {
            Style sourceStyle = styleFromKey(key);
            int limit = key.length() - styleSuffixLength(sourceStyle);
            DateTimeUnit unit = DateTimeUnit.orNullFromString(key.substring(0, limit));
            if (unit != null) {
                Style targetStyle = styleFromAlias(value);
                if (sourceStyle != targetStyle) {
                    if (RelativeDateTimeFormatter.fallbackCache[sourceStyle.ordinal()] == null) {
                        RelativeDateTimeFormatter.fallbackCache[sourceStyle.ordinal()] = targetStyle;
                        return;
                    } else if (RelativeDateTimeFormatter.fallbackCache[sourceStyle.ordinal()] != targetStyle) {
                        throw new ICUException("Inconsistent style fallback for style " + sourceStyle + " to " + targetStyle);
                    } else {
                        return;
                    }
                }
                throw new ICUException("Invalid style fallback from " + sourceStyle + " to itself");
            }
        }

        public void put(UResource.Key key, UResource.Value value, boolean noFallback) {
            if (value.getType() == 3) {
                return;
            }
            UResource.Table table = value.getTable();
            for (int i = 0; table.getKeyAndValue(i, key, value); i++) {
                if (value.getType() == 3) {
                    handleAlias(key, value, noFallback);
                } else {
                    this.style = styleFromKey(key);
                    int limit = key.length() - styleSuffixLength(this.style);
                    DateTimeUnit orNullFromString = DateTimeUnit.orNullFromString(key.substring(0, limit));
                    this.unit = orNullFromString;
                    if (orNullFromString != null) {
                        consumeTimeUnit(key, value);
                    }
                }
            }
        }

        RelDateTimeDataSink() {
        }
    }

    /* renamed from: com.ibm.icu.text.RelativeDateTimeFormatter$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C07471 {

        /* renamed from: $SwitchMap$com$ibm$icu$text$RelativeDateTimeFormatter$RelativeDateTimeUnit */
        static final /* synthetic */ int[] f162x2231a407;
        static final /* synthetic */ int[] $SwitchMap$com$ibm$icu$text$RelativeDateTimeFormatter$Style;

        static {
            int[] iArr = new int[Style.values().length];
            $SwitchMap$com$ibm$icu$text$RelativeDateTimeFormatter$Style = iArr;
            try {
                iArr[Style.SHORT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$RelativeDateTimeFormatter$Style[Style.NARROW.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            int[] iArr2 = new int[RelativeDateTimeUnit.values().length];
            f162x2231a407 = iArr2;
            try {
                iArr2[RelativeDateTimeUnit.YEAR.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f162x2231a407[RelativeDateTimeUnit.QUARTER.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f162x2231a407[RelativeDateTimeUnit.MONTH.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f162x2231a407[RelativeDateTimeUnit.WEEK.ordinal()] = 4;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f162x2231a407[RelativeDateTimeUnit.DAY.ordinal()] = 5;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f162x2231a407[RelativeDateTimeUnit.HOUR.ordinal()] = 6;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f162x2231a407[RelativeDateTimeUnit.MINUTE.ordinal()] = 7;
            } catch (NoSuchFieldError e9) {
            }
            try {
                f162x2231a407[RelativeDateTimeUnit.SECOND.ordinal()] = 8;
            } catch (NoSuchFieldError e10) {
            }
            try {
                f162x2231a407[RelativeDateTimeUnit.SUNDAY.ordinal()] = 9;
            } catch (NoSuchFieldError e11) {
            }
            try {
                f162x2231a407[RelativeDateTimeUnit.MONDAY.ordinal()] = 10;
            } catch (NoSuchFieldError e12) {
            }
            try {
                f162x2231a407[RelativeDateTimeUnit.TUESDAY.ordinal()] = 11;
            } catch (NoSuchFieldError e13) {
            }
            try {
                f162x2231a407[RelativeDateTimeUnit.WEDNESDAY.ordinal()] = 12;
            } catch (NoSuchFieldError e14) {
            }
            try {
                f162x2231a407[RelativeDateTimeUnit.THURSDAY.ordinal()] = 13;
            } catch (NoSuchFieldError e15) {
            }
            try {
                f162x2231a407[RelativeDateTimeUnit.FRIDAY.ordinal()] = 14;
            } catch (NoSuchFieldError e16) {
            }
            try {
                f162x2231a407[RelativeDateTimeUnit.SATURDAY.ordinal()] = 15;
            } catch (NoSuchFieldError e17) {
            }
        }
    }

    /* loaded from: classes.dex */
    private static class Loader {
        private final ULocale ulocale;

        public Loader(ULocale ulocale) {
            this.ulocale = ulocale;
        }

        private String getDateTimePattern(ICUResourceBundle r) {
            String calType = r.getStringWithFallback("calendar/default");
            calType = (calType == null || calType.equals("")) ? "gregorian" : "gregorian";
            String resourcePath = "calendar/" + calType + "/DateTimePatterns";
            ICUResourceBundle patternsRb = r.findWithFallback(resourcePath);
            if (patternsRb == null && calType.equals("gregorian")) {
                patternsRb = r.findWithFallback("calendar/gregorian/DateTimePatterns");
            }
            if (patternsRb == null || patternsRb.getSize() < 9) {
                return "{1} {0}";
            }
            int elementType = patternsRb.get(8).getType();
            if (elementType == 8) {
                return patternsRb.get(8).getString(0);
            }
            return patternsRb.getString(8);
        }

        public RelativeDateTimeFormatterData load() {
            Style[] values;
            Style newStyle2;
            RelDateTimeDataSink sink = new RelDateTimeDataSink();
            ICUResourceBundle r = (ICUResourceBundle) UResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudt63b", this.ulocale);
            r.getAllItemsWithFallback("fields", sink);
            for (Style testStyle : Style.values()) {
                Style newStyle1 = RelativeDateTimeFormatter.fallbackCache[testStyle.ordinal()];
                if (newStyle1 != null && (newStyle2 = RelativeDateTimeFormatter.fallbackCache[newStyle1.ordinal()]) != null && RelativeDateTimeFormatter.fallbackCache[newStyle2.ordinal()] != null) {
                    throw new IllegalStateException("Style fallback too deep");
                }
            }
            return new RelativeDateTimeFormatterData(sink.qualitativeUnitMap, sink.styleRelUnitPatterns, getDateTimePattern(r));
        }
    }
}
