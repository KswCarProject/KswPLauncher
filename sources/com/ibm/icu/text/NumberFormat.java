package com.ibm.icu.text;

import com.ibm.icu.impl.ICUResourceBundle;
import com.ibm.icu.math.BigDecimal;
import com.ibm.icu.text.DisplayContext;
import com.ibm.icu.util.Currency;
import com.ibm.icu.util.CurrencyAmount;
import com.ibm.icu.util.ULocale;
import com.ibm.icu.util.UResourceBundle;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Collections;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Set;
import kotlin.text.Typography;

public abstract class NumberFormat extends UFormat {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int ACCOUNTINGCURRENCYSTYLE = 7;
    public static final int CASHCURRENCYSTYLE = 8;
    public static final int CURRENCYSTYLE = 1;
    public static final int FRACTION_FIELD = 1;
    public static final int INTEGERSTYLE = 4;
    public static final int INTEGER_FIELD = 0;
    public static final int ISOCURRENCYSTYLE = 5;
    public static final int NUMBERSTYLE = 0;
    public static final int PERCENTSTYLE = 2;
    public static final int PLURALCURRENCYSTYLE = 6;
    public static final int SCIENTIFICSTYLE = 3;
    public static final int STANDARDCURRENCYSTYLE = 9;
    static final int currentSerialVersion = 2;
    private static final char[] doubleCurrencySign;
    private static final String doubleCurrencyStr;
    private static final long serialVersionUID = -2308460125733713944L;
    private static NumberFormatShim shim;
    private DisplayContext capitalizationSetting = DisplayContext.CAPITALIZATION_NONE;
    private Currency currency;
    private boolean groupingUsed = true;
    private byte maxFractionDigits = 3;
    private byte maxIntegerDigits = 40;
    private int maximumFractionDigits = 3;
    private int maximumIntegerDigits = 40;
    private byte minFractionDigits = 0;
    private byte minIntegerDigits = 1;
    private int minimumFractionDigits = 0;
    private int minimumIntegerDigits = 1;
    private boolean parseIntegerOnly = false;
    private boolean parseStrict;
    private int serialVersionOnStream = 2;

    public abstract StringBuffer format(double d, StringBuffer stringBuffer, FieldPosition fieldPosition);

    public abstract StringBuffer format(long j, StringBuffer stringBuffer, FieldPosition fieldPosition);

    public abstract StringBuffer format(BigDecimal bigDecimal, StringBuffer stringBuffer, FieldPosition fieldPosition);

    public abstract StringBuffer format(java.math.BigDecimal bigDecimal, StringBuffer stringBuffer, FieldPosition fieldPosition);

    public abstract StringBuffer format(BigInteger bigInteger, StringBuffer stringBuffer, FieldPosition fieldPosition);

    public abstract Number parse(String str, ParsePosition parsePosition);

    static {
        char[] cArr = {164, 164};
        doubleCurrencySign = cArr;
        doubleCurrencyStr = new String(cArr);
    }

    public StringBuffer format(Object number, StringBuffer toAppendTo, FieldPosition pos) {
        if (number instanceof Long) {
            return format(((Long) number).longValue(), toAppendTo, pos);
        }
        if (number instanceof BigInteger) {
            return format((BigInteger) number, toAppendTo, pos);
        }
        if (number instanceof java.math.BigDecimal) {
            return format((java.math.BigDecimal) number, toAppendTo, pos);
        }
        if (number instanceof BigDecimal) {
            return format((BigDecimal) number, toAppendTo, pos);
        }
        if (number instanceof CurrencyAmount) {
            return format((CurrencyAmount) number, toAppendTo, pos);
        }
        if (number instanceof Number) {
            return format(((Number) number).doubleValue(), toAppendTo, pos);
        }
        throw new IllegalArgumentException("Cannot format given Object as a Number");
    }

    public final Object parseObject(String source, ParsePosition parsePosition) {
        return parse(source, parsePosition);
    }

    public final String format(double number) {
        return format(number, new StringBuffer(), new FieldPosition(0)).toString();
    }

    public final String format(long number) {
        StringBuffer buf = new StringBuffer(19);
        format(number, buf, new FieldPosition(0));
        return buf.toString();
    }

    public final String format(BigInteger number) {
        return format(number, new StringBuffer(), new FieldPosition(0)).toString();
    }

    public final String format(java.math.BigDecimal number) {
        return format(number, new StringBuffer(), new FieldPosition(0)).toString();
    }

    public final String format(BigDecimal number) {
        return format(number, new StringBuffer(), new FieldPosition(0)).toString();
    }

    public final String format(CurrencyAmount currAmt) {
        return format(currAmt, new StringBuffer(), new FieldPosition(0)).toString();
    }

    public StringBuffer format(CurrencyAmount currAmt, StringBuffer toAppendTo, FieldPosition pos) {
        synchronized (this) {
            Currency save = getCurrency();
            Currency curr = currAmt.getCurrency();
            boolean same = curr.equals(save);
            if (!same) {
                setCurrency(curr);
            }
            format((Object) currAmt.getNumber(), toAppendTo, pos);
            if (!same) {
                setCurrency(save);
            }
        }
        return toAppendTo;
    }

    public Number parse(String text) throws ParseException {
        ParsePosition parsePosition = new ParsePosition(0);
        Number result = parse(text, parsePosition);
        if (parsePosition.getIndex() != 0) {
            return result;
        }
        throw new ParseException("Unparseable number: \"" + text + Typography.quote, parsePosition.getErrorIndex());
    }

    public CurrencyAmount parseCurrency(CharSequence text, ParsePosition pos) {
        Number n = parse(text.toString(), pos);
        if (n == null) {
            return null;
        }
        return new CurrencyAmount(n, getEffectiveCurrency());
    }

    public boolean isParseIntegerOnly() {
        return this.parseIntegerOnly;
    }

    public void setParseIntegerOnly(boolean value) {
        this.parseIntegerOnly = value;
    }

    public void setParseStrict(boolean value) {
        this.parseStrict = value;
    }

    public boolean isParseStrict() {
        return this.parseStrict;
    }

    public void setContext(DisplayContext context) {
        if (context.type() == DisplayContext.Type.CAPITALIZATION) {
            this.capitalizationSetting = context;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r1.capitalizationSetting;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.ibm.icu.text.DisplayContext getContext(com.ibm.icu.text.DisplayContext.Type r2) {
        /*
            r1 = this;
            com.ibm.icu.text.DisplayContext$Type r0 = com.ibm.icu.text.DisplayContext.Type.CAPITALIZATION
            if (r2 != r0) goto L_0x0009
            com.ibm.icu.text.DisplayContext r0 = r1.capitalizationSetting
            if (r0 == 0) goto L_0x0009
            goto L_0x000b
        L_0x0009:
            com.ibm.icu.text.DisplayContext r0 = com.ibm.icu.text.DisplayContext.CAPITALIZATION_NONE
        L_0x000b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.NumberFormat.getContext(com.ibm.icu.text.DisplayContext$Type):com.ibm.icu.text.DisplayContext");
    }

    public static final NumberFormat getInstance() {
        return getInstance(ULocale.getDefault(ULocale.Category.FORMAT), 0);
    }

    public static NumberFormat getInstance(Locale inLocale) {
        return getInstance(ULocale.forLocale(inLocale), 0);
    }

    public static NumberFormat getInstance(ULocale inLocale) {
        return getInstance(inLocale, 0);
    }

    public static final NumberFormat getInstance(int style) {
        return getInstance(ULocale.getDefault(ULocale.Category.FORMAT), style);
    }

    public static NumberFormat getInstance(Locale inLocale, int style) {
        return getInstance(ULocale.forLocale(inLocale), style);
    }

    public static final NumberFormat getNumberInstance() {
        return getInstance(ULocale.getDefault(ULocale.Category.FORMAT), 0);
    }

    public static NumberFormat getNumberInstance(Locale inLocale) {
        return getInstance(ULocale.forLocale(inLocale), 0);
    }

    public static NumberFormat getNumberInstance(ULocale inLocale) {
        return getInstance(inLocale, 0);
    }

    public static final NumberFormat getIntegerInstance() {
        return getInstance(ULocale.getDefault(ULocale.Category.FORMAT), 4);
    }

    public static NumberFormat getIntegerInstance(Locale inLocale) {
        return getInstance(ULocale.forLocale(inLocale), 4);
    }

    public static NumberFormat getIntegerInstance(ULocale inLocale) {
        return getInstance(inLocale, 4);
    }

    public static final NumberFormat getCurrencyInstance() {
        return getInstance(ULocale.getDefault(ULocale.Category.FORMAT), 1);
    }

    public static NumberFormat getCurrencyInstance(Locale inLocale) {
        return getInstance(ULocale.forLocale(inLocale), 1);
    }

    public static NumberFormat getCurrencyInstance(ULocale inLocale) {
        return getInstance(inLocale, 1);
    }

    public static final NumberFormat getPercentInstance() {
        return getInstance(ULocale.getDefault(ULocale.Category.FORMAT), 2);
    }

    public static NumberFormat getPercentInstance(Locale inLocale) {
        return getInstance(ULocale.forLocale(inLocale), 2);
    }

    public static NumberFormat getPercentInstance(ULocale inLocale) {
        return getInstance(inLocale, 2);
    }

    public static final NumberFormat getScientificInstance() {
        return getInstance(ULocale.getDefault(ULocale.Category.FORMAT), 3);
    }

    public static NumberFormat getScientificInstance(Locale inLocale) {
        return getInstance(ULocale.forLocale(inLocale), 3);
    }

    public static NumberFormat getScientificInstance(ULocale inLocale) {
        return getInstance(inLocale, 3);
    }

    public static abstract class NumberFormatFactory {
        public static final int FORMAT_CURRENCY = 1;
        public static final int FORMAT_INTEGER = 4;
        public static final int FORMAT_NUMBER = 0;
        public static final int FORMAT_PERCENT = 2;
        public static final int FORMAT_SCIENTIFIC = 3;

        public abstract Set<String> getSupportedLocaleNames();

        public boolean visible() {
            return true;
        }

        public NumberFormat createFormat(ULocale loc, int formatType) {
            return createFormat(loc.toLocale(), formatType);
        }

        public NumberFormat createFormat(Locale loc, int formatType) {
            return createFormat(ULocale.forLocale(loc), formatType);
        }

        protected NumberFormatFactory() {
        }
    }

    public static abstract class SimpleNumberFormatFactory extends NumberFormatFactory {
        final Set<String> localeNames;
        final boolean visible;

        public SimpleNumberFormatFactory(Locale locale) {
            this(locale, true);
        }

        public SimpleNumberFormatFactory(Locale locale, boolean visible2) {
            this.localeNames = Collections.singleton(ULocale.forLocale(locale).getBaseName());
            this.visible = visible2;
        }

        public SimpleNumberFormatFactory(ULocale locale) {
            this(locale, true);
        }

        public SimpleNumberFormatFactory(ULocale locale, boolean visible2) {
            this.localeNames = Collections.singleton(locale.getBaseName());
            this.visible = visible2;
        }

        public final boolean visible() {
            return this.visible;
        }

        public final Set<String> getSupportedLocaleNames() {
            return this.localeNames;
        }
    }

    static abstract class NumberFormatShim {
        /* access modifiers changed from: package-private */
        public abstract NumberFormat createInstance(ULocale uLocale, int i);

        /* access modifiers changed from: package-private */
        public abstract Locale[] getAvailableLocales();

        /* access modifiers changed from: package-private */
        public abstract ULocale[] getAvailableULocales();

        /* access modifiers changed from: package-private */
        public abstract Object registerFactory(NumberFormatFactory numberFormatFactory);

        /* access modifiers changed from: package-private */
        public abstract boolean unregister(Object obj);

        NumberFormatShim() {
        }
    }

    private static NumberFormatShim getShim() {
        if (shim == null) {
            try {
                shim = (NumberFormatShim) Class.forName("com.ibm.icu.text.NumberFormatServiceShim").newInstance();
            } catch (MissingResourceException e) {
                throw e;
            } catch (Exception e2) {
                throw new RuntimeException(e2.getMessage());
            }
        }
        return shim;
    }

    public static Locale[] getAvailableLocales() {
        if (shim == null) {
            return ICUResourceBundle.getAvailableLocales();
        }
        return getShim().getAvailableLocales();
    }

    public static ULocale[] getAvailableULocales() {
        if (shim == null) {
            return ICUResourceBundle.getAvailableULocales();
        }
        return getShim().getAvailableULocales();
    }

    public static Object registerFactory(NumberFormatFactory factory) {
        if (factory != null) {
            return getShim().registerFactory(factory);
        }
        throw new IllegalArgumentException("factory must not be null");
    }

    public static boolean unregister(Object registryKey) {
        if (registryKey != null) {
            NumberFormatShim numberFormatShim = shim;
            if (numberFormatShim == null) {
                return false;
            }
            return numberFormatShim.unregister(registryKey);
        }
        throw new IllegalArgumentException("registryKey must not be null");
    }

    public int hashCode() {
        return (this.maximumIntegerDigits * 37) + this.maxFractionDigits;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        NumberFormat other = (NumberFormat) obj;
        if (this.maximumIntegerDigits == other.maximumIntegerDigits && this.minimumIntegerDigits == other.minimumIntegerDigits && this.maximumFractionDigits == other.maximumFractionDigits && this.minimumFractionDigits == other.minimumFractionDigits && this.groupingUsed == other.groupingUsed && this.parseIntegerOnly == other.parseIntegerOnly && this.parseStrict == other.parseStrict && this.capitalizationSetting == other.capitalizationSetting) {
            return true;
        }
        return false;
    }

    public Object clone() {
        return (NumberFormat) super.clone();
    }

    public boolean isGroupingUsed() {
        return this.groupingUsed;
    }

    public void setGroupingUsed(boolean newValue) {
        this.groupingUsed = newValue;
    }

    public int getMaximumIntegerDigits() {
        return this.maximumIntegerDigits;
    }

    public void setMaximumIntegerDigits(int newValue) {
        int max = Math.max(0, newValue);
        this.maximumIntegerDigits = max;
        if (this.minimumIntegerDigits > max) {
            this.minimumIntegerDigits = max;
        }
    }

    public int getMinimumIntegerDigits() {
        return this.minimumIntegerDigits;
    }

    public void setMinimumIntegerDigits(int newValue) {
        int max = Math.max(0, newValue);
        this.minimumIntegerDigits = max;
        if (max > this.maximumIntegerDigits) {
            this.maximumIntegerDigits = max;
        }
    }

    public int getMaximumFractionDigits() {
        return this.maximumFractionDigits;
    }

    public void setMaximumFractionDigits(int newValue) {
        int max = Math.max(0, newValue);
        this.maximumFractionDigits = max;
        if (max < this.minimumFractionDigits) {
            this.minimumFractionDigits = max;
        }
    }

    public int getMinimumFractionDigits() {
        return this.minimumFractionDigits;
    }

    public void setMinimumFractionDigits(int newValue) {
        int max = Math.max(0, newValue);
        this.minimumFractionDigits = max;
        if (this.maximumFractionDigits < max) {
            this.maximumFractionDigits = max;
        }
    }

    public void setCurrency(Currency theCurrency) {
        this.currency = theCurrency;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public Currency getEffectiveCurrency() {
        Currency c = getCurrency();
        if (c != null) {
            return c;
        }
        ULocale uloc = getLocale(ULocale.VALID_LOCALE);
        if (uloc == null) {
            uloc = ULocale.getDefault(ULocale.Category.FORMAT);
        }
        return Currency.getInstance(uloc);
    }

    public int getRoundingMode() {
        throw new UnsupportedOperationException("getRoundingMode must be implemented by the subclass implementation.");
    }

    public void setRoundingMode(int roundingMode) {
        throw new UnsupportedOperationException("setRoundingMode must be implemented by the subclass implementation.");
    }

    public static NumberFormat getInstance(ULocale desiredLocale, int choice) {
        if (choice >= 0 && choice <= 9) {
            return getShim().createInstance(desiredLocale, choice);
        }
        throw new IllegalArgumentException("choice should be from NUMBERSTYLE to STANDARDCURRENCYSTYLE");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: com.ibm.icu.text.DecimalFormat} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: com.ibm.icu.text.DecimalFormat} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: com.ibm.icu.text.RuleBasedNumberFormat} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: com.ibm.icu.text.DecimalFormat} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.ibm.icu.text.NumberFormat createInstance(com.ibm.icu.util.ULocale r12, int r13) {
        /*
            java.lang.String r0 = getPattern((com.ibm.icu.util.ULocale) r12, (int) r13)
            com.ibm.icu.text.DecimalFormatSymbols r1 = new com.ibm.icu.text.DecimalFormatSymbols
            r1.<init>((com.ibm.icu.util.ULocale) r12)
            r2 = 8
            r3 = 5
            r4 = 1
            if (r13 == r4) goto L_0x001a
            if (r13 == r3) goto L_0x001a
            r5 = 7
            if (r13 == r5) goto L_0x001a
            if (r13 == r2) goto L_0x001a
            r5 = 9
            if (r13 != r5) goto L_0x0021
        L_0x001a:
            java.lang.String r5 = r1.getCurrencyPattern()
            if (r5 == 0) goto L_0x0021
            r0 = r5
        L_0x0021:
            if (r13 != r3) goto L_0x002c
            java.lang.String r3 = doubleCurrencyStr
            java.lang.String r5 = "¤"
            java.lang.String r0 = r0.replace(r5, r3)
        L_0x002c:
            com.ibm.icu.text.NumberingSystem r3 = com.ibm.icu.text.NumberingSystem.getInstance((com.ibm.icu.util.ULocale) r12)
            if (r3 != 0) goto L_0x0034
            r2 = 0
            return r2
        L_0x0034:
            r5 = 0
            if (r3 == 0) goto L_0x0079
            boolean r6 = r3.isAlgorithmic()
            if (r6 == 0) goto L_0x0079
            r2 = 4
            java.lang.String r4 = r3.getDescription()
            java.lang.String r6 = "/"
            int r7 = r4.indexOf(r6)
            int r6 = r4.lastIndexOf(r6)
            if (r6 <= r7) goto L_0x006d
            java.lang.String r5 = r4.substring(r5, r7)
            int r8 = r7 + 1
            java.lang.String r8 = r4.substring(r8, r6)
            int r9 = r6 + 1
            java.lang.String r9 = r4.substring(r9)
            com.ibm.icu.util.ULocale r10 = new com.ibm.icu.util.ULocale
            r10.<init>(r5)
            java.lang.String r11 = "SpelloutRules"
            boolean r11 = r8.equals(r11)
            if (r11 == 0) goto L_0x006c
            r2 = 1
        L_0x006c:
            goto L_0x006f
        L_0x006d:
            r10 = r12
            r9 = r4
        L_0x006f:
            com.ibm.icu.text.RuleBasedNumberFormat r5 = new com.ibm.icu.text.RuleBasedNumberFormat
            r5.<init>((com.ibm.icu.util.ULocale) r10, (int) r2)
            r5.setDefaultRuleSet(r9)
            r2 = r5
            goto L_0x009c
        L_0x0079:
            com.ibm.icu.text.DecimalFormat r6 = new com.ibm.icu.text.DecimalFormat
            r6.<init>(r0, r1, r13)
            r7 = 4
            if (r13 != r7) goto L_0x008a
            r6.setMaximumFractionDigits(r5)
            r6.setDecimalSeparatorAlwaysShown(r5)
            r6.setParseIntegerOnly(r4)
        L_0x008a:
            if (r13 != r2) goto L_0x0091
            com.ibm.icu.util.Currency$CurrencyUsage r2 = com.ibm.icu.util.Currency.CurrencyUsage.CASH
            r6.setCurrencyUsage(r2)
        L_0x0091:
            r2 = 6
            if (r13 != r2) goto L_0x009b
            com.ibm.icu.text.CurrencyPluralInfo r2 = com.ibm.icu.text.CurrencyPluralInfo.getInstance((com.ibm.icu.util.ULocale) r12)
            r6.setCurrencyPluralInfo(r2)
        L_0x009b:
            r2 = r6
        L_0x009c:
            com.ibm.icu.util.ULocale$Type r4 = com.ibm.icu.util.ULocale.VALID_LOCALE
            com.ibm.icu.util.ULocale r4 = r1.getLocale(r4)
            com.ibm.icu.util.ULocale$Type r5 = com.ibm.icu.util.ULocale.ACTUAL_LOCALE
            com.ibm.icu.util.ULocale r5 = r1.getLocale(r5)
            r2.setLocale(r4, r5)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.NumberFormat.createInstance(com.ibm.icu.util.ULocale, int):com.ibm.icu.text.NumberFormat");
    }

    @Deprecated
    protected static String getPattern(Locale forLocale, int choice) {
        return getPattern(ULocale.forLocale(forLocale), choice);
    }

    protected static String getPattern(ULocale forLocale, int choice) {
        return getPatternForStyle(forLocale, choice);
    }

    @Deprecated
    public static String getPatternForStyle(ULocale forLocale, int choice) {
        return getPatternForStyleAndNumberingSystem(forLocale, NumberingSystem.getInstance(forLocale).getName(), choice);
    }

    @Deprecated
    public static String getPatternForStyleAndNumberingSystem(ULocale forLocale, String nsName, int choice) {
        String patternKey;
        switch (choice) {
            case 0:
            case 4:
            case 6:
                patternKey = "decimalFormat";
                break;
            case 1:
                String cfKeyValue = forLocale.getKeywordValue("cf");
                patternKey = (cfKeyValue == null || !cfKeyValue.equals("account")) ? "currencyFormat" : "accountingFormat";
                break;
            case 2:
                patternKey = "percentFormat";
                break;
            case 3:
                patternKey = "scientificFormat";
                break;
            case 5:
            case 8:
            case 9:
                patternKey = "currencyFormat";
                break;
            case 7:
                patternKey = "accountingFormat";
                break;
            default:
                throw new AssertionError();
        }
        ICUResourceBundle rb = UResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudt63b", forLocale);
        String result = rb.findStringWithFallback("NumberElements/" + nsName + "/patterns/" + patternKey);
        if (result == null) {
            return rb.getStringWithFallback("NumberElements/latn/patterns/" + patternKey);
        }
        return result;
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        int i;
        stream.defaultReadObject();
        int i2 = this.serialVersionOnStream;
        if (i2 < 1) {
            this.maximumIntegerDigits = this.maxIntegerDigits;
            this.minimumIntegerDigits = this.minIntegerDigits;
            this.maximumFractionDigits = this.maxFractionDigits;
            this.minimumFractionDigits = this.minFractionDigits;
        }
        if (i2 < 2) {
            this.capitalizationSetting = DisplayContext.CAPITALIZATION_NONE;
        }
        int i3 = this.minimumIntegerDigits;
        if (i3 > this.maximumIntegerDigits || (i = this.minimumFractionDigits) > this.maximumFractionDigits || i3 < 0 || i < 0) {
            throw new InvalidObjectException("Digit count range invalid");
        }
        this.serialVersionOnStream = 2;
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        int i = this.maximumIntegerDigits;
        byte b = Byte.MAX_VALUE;
        this.maxIntegerDigits = i > 127 ? Byte.MAX_VALUE : (byte) i;
        int i2 = this.minimumIntegerDigits;
        this.minIntegerDigits = i2 > 127 ? Byte.MAX_VALUE : (byte) i2;
        int i3 = this.maximumFractionDigits;
        this.maxFractionDigits = i3 > 127 ? Byte.MAX_VALUE : (byte) i3;
        int i4 = this.minimumFractionDigits;
        if (i4 <= 127) {
            b = (byte) i4;
        }
        this.minFractionDigits = b;
        stream.defaultWriteObject();
    }

    public static class Field extends Format.Field {
        public static final Field CURRENCY = new Field("currency");
        public static final Field DECIMAL_SEPARATOR = new Field("decimal separator");
        public static final Field EXPONENT = new Field("exponent");
        public static final Field EXPONENT_SIGN = new Field("exponent sign");
        public static final Field EXPONENT_SYMBOL = new Field("exponent symbol");
        public static final Field FRACTION = new Field("fraction");
        public static final Field GROUPING_SEPARATOR = new Field("grouping separator");
        public static final Field INTEGER = new Field("integer");
        public static final Field PERCENT = new Field("percent");
        public static final Field PERMILLE = new Field("per mille");
        public static final Field SIGN = new Field("sign");
        static final long serialVersionUID = -4516273749929385842L;

        protected Field(String fieldName) {
            super(fieldName);
        }

        /* access modifiers changed from: protected */
        public Object readResolve() throws InvalidObjectException {
            String name = getName();
            Field field = INTEGER;
            if (name.equals(field.getName())) {
                return field;
            }
            String name2 = getName();
            Field field2 = FRACTION;
            if (name2.equals(field2.getName())) {
                return field2;
            }
            String name3 = getName();
            Field field3 = EXPONENT;
            if (name3.equals(field3.getName())) {
                return field3;
            }
            String name4 = getName();
            Field field4 = EXPONENT_SIGN;
            if (name4.equals(field4.getName())) {
                return field4;
            }
            String name5 = getName();
            Field field5 = EXPONENT_SYMBOL;
            if (name5.equals(field5.getName())) {
                return field5;
            }
            String name6 = getName();
            Field field6 = CURRENCY;
            if (name6.equals(field6.getName())) {
                return field6;
            }
            String name7 = getName();
            Field field7 = DECIMAL_SEPARATOR;
            if (name7.equals(field7.getName())) {
                return field7;
            }
            String name8 = getName();
            Field field8 = GROUPING_SEPARATOR;
            if (name8.equals(field8.getName())) {
                return field8;
            }
            String name9 = getName();
            Field field9 = PERCENT;
            if (name9.equals(field9.getName())) {
                return field9;
            }
            String name10 = getName();
            Field field10 = PERMILLE;
            if (name10.equals(field10.getName())) {
                return field10;
            }
            String name11 = getName();
            Field field11 = SIGN;
            if (name11.equals(field11.getName())) {
                return field11;
            }
            throw new InvalidObjectException("An invalid object.");
        }
    }
}
