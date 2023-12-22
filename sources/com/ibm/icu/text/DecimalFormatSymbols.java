package com.ibm.icu.text;

import com.ibm.icu.impl.CacheBase;
import com.ibm.icu.impl.CurrencyData;
import com.ibm.icu.impl.ICUResourceBundle;
import com.ibm.icu.impl.SoftCache;
import com.ibm.icu.impl.UResource;
import com.ibm.icu.util.Currency;
import com.ibm.icu.util.ICUCloneNotSupportedException;
import com.ibm.icu.util.ULocale;
import com.ibm.icu.util.UResourceBundle;
import com.wits.ksw.settings.TxzMessage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Locale;
import java.util.MissingResourceException;

/* loaded from: classes.dex */
public class DecimalFormatSymbols implements Cloneable, Serializable {
    public static final int CURRENCY_SPC_CURRENCY_MATCH = 0;
    public static final int CURRENCY_SPC_INSERT = 2;
    public static final int CURRENCY_SPC_SURROUNDING_MATCH = 1;
    private static final String LATIN_NUMBERING_SYSTEM = "latn";
    private static final String NUMBER_ELEMENTS = "NumberElements";
    private static final String SYMBOLS = "symbols";
    private static final int currentSerialVersion = 8;
    private static final long serialVersionUID = 5772796243397350300L;
    private String NaN;
    private ULocale actualLocale;
    private transient int codePointZero;
    private transient Currency currency;
    private String currencyPattern;
    private String[] currencySpcAfterSym;
    private String[] currencySpcBeforeSym;
    private String currencySymbol;
    private char decimalSeparator;
    private String decimalSeparatorString;
    private char digit;
    private String[] digitStrings;
    private char[] digits;
    private String exponentMultiplicationSign;
    private String exponentSeparator;
    private char exponential;
    private char groupingSeparator;
    private String groupingSeparatorString;
    private String infinity;
    private String intlCurrencySymbol;
    private char minusSign;
    private String minusString;
    private char monetaryGroupingSeparator;
    private String monetaryGroupingSeparatorString;
    private char monetarySeparator;
    private String monetarySeparatorString;
    private char padEscape;
    private char patternSeparator;
    private char perMill;
    private String perMillString;
    private char percent;
    private String percentString;
    private char plusSign;
    private String plusString;
    private Locale requestedLocale;
    private int serialVersionOnStream;
    private char sigDigit;
    private ULocale ulocale;
    private ULocale validLocale;
    private char zeroDigit;
    private static final String[] SYMBOL_KEYS = {"decimal", "group", "percentSign", "minusSign", "plusSign", "exponential", "perMille", "infinity", "nan", "currencyDecimal", "currencyGroup", "superscriptingExponent"};
    private static final String[] DEF_DIGIT_STRINGS_ARRAY = {TxzMessage.TXZ_DISMISS, TxzMessage.TXZ_SHOW, "2", "3", "4", "5", "6", "7", "8", "9"};
    private static final char[] DEF_DIGIT_CHARS_ARRAY = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static final char DEF_DECIMAL_SEPARATOR = '.';
    private static final char DEF_GROUPING_SEPARATOR = ',';
    private static final char DEF_PERCENT = '%';
    private static final char DEF_MINUS_SIGN = '-';
    private static final char DEF_PLUS_SIGN = '+';
    private static final char DEF_PERMILL = '\u2030';
    private static final String[] SYMBOL_DEFAULTS = {String.valueOf((char) DEF_DECIMAL_SEPARATOR), String.valueOf((char) DEF_GROUPING_SEPARATOR), String.valueOf((char) DEF_PERCENT), String.valueOf((char) DEF_MINUS_SIGN), String.valueOf((char) DEF_PLUS_SIGN), DateFormat.ABBR_WEEKDAY, String.valueOf((char) DEF_PERMILL), "\u221e", "NaN", null, null, "\u00d7"};
    private static final CacheBase<ULocale, CacheData, Void> cachedLocaleData = new SoftCache<ULocale, CacheData, Void>() { // from class: com.ibm.icu.text.DecimalFormatSymbols.1
        /* JADX INFO: Access modifiers changed from: protected */
        public CacheData createInstance(ULocale locale, Void unused) {
            return DecimalFormatSymbols.loadData(locale);
        }
    };

    public DecimalFormatSymbols() {
        this(ULocale.getDefault(ULocale.Category.FORMAT));
    }

    public DecimalFormatSymbols(Locale locale) {
        this(ULocale.forLocale(locale));
    }

    public DecimalFormatSymbols(ULocale locale) {
        this.exponentMultiplicationSign = null;
        this.serialVersionOnStream = 8;
        this.currencyPattern = null;
        initialize(locale, null);
    }

    private DecimalFormatSymbols(Locale locale, NumberingSystem ns) {
        this(ULocale.forLocale(locale), ns);
    }

    private DecimalFormatSymbols(ULocale locale, NumberingSystem ns) {
        this.exponentMultiplicationSign = null;
        this.serialVersionOnStream = 8;
        this.currencyPattern = null;
        initialize(locale, ns);
    }

    public static DecimalFormatSymbols getInstance() {
        return new DecimalFormatSymbols();
    }

    public static DecimalFormatSymbols getInstance(Locale locale) {
        return new DecimalFormatSymbols(locale);
    }

    public static DecimalFormatSymbols getInstance(ULocale locale) {
        return new DecimalFormatSymbols(locale);
    }

    public static DecimalFormatSymbols forNumberingSystem(Locale locale, NumberingSystem ns) {
        return new DecimalFormatSymbols(locale, ns);
    }

    public static DecimalFormatSymbols forNumberingSystem(ULocale locale, NumberingSystem ns) {
        return new DecimalFormatSymbols(locale, ns);
    }

    public static Locale[] getAvailableLocales() {
        return ICUResourceBundle.getAvailableLocales();
    }

    public static ULocale[] getAvailableULocales() {
        return ICUResourceBundle.getAvailableULocales();
    }

    public char getZeroDigit() {
        return this.zeroDigit;
    }

    public char[] getDigits() {
        return (char[]) this.digits.clone();
    }

    public void setZeroDigit(char zeroDigit) {
        this.zeroDigit = zeroDigit;
        this.digitStrings = (String[]) this.digitStrings.clone();
        this.digits = (char[]) this.digits.clone();
        this.digitStrings[0] = String.valueOf(zeroDigit);
        this.digits[0] = zeroDigit;
        for (int i = 1; i < 10; i++) {
            char d = (char) (zeroDigit + i);
            this.digitStrings[i] = String.valueOf(d);
            this.digits[i] = d;
        }
        this.codePointZero = zeroDigit;
    }

    public String[] getDigitStrings() {
        return (String[]) this.digitStrings.clone();
    }

    @Deprecated
    public String[] getDigitStringsLocal() {
        return this.digitStrings;
    }

    @Deprecated
    public int getCodePointZero() {
        return this.codePointZero;
    }

    public void setDigitStrings(String[] digitStrings) {
        int cp;
        int cc;
        if (digitStrings == null) {
            throw new NullPointerException("The input digit string array is null");
        }
        if (digitStrings.length != 10) {
            throw new IllegalArgumentException("Number of digit strings is not 10");
        }
        String[] tmpDigitStrings = new String[10];
        char[] tmpDigits = new char[10];
        int tmpCodePointZero = -1;
        for (int i = 0; i < 10; i++) {
            String digitStr = digitStrings[i];
            if (digitStr == null) {
                throw new IllegalArgumentException("The input digit string array contains a null element");
            }
            tmpDigitStrings[i] = digitStr;
            if (digitStr.length() == 0) {
                cp = -1;
                cc = 0;
            } else {
                cp = Character.codePointAt(digitStrings[i], 0);
                cc = Character.charCount(cp);
            }
            if (cc == digitStr.length()) {
                if (cc == 1 && tmpDigits != null) {
                    tmpDigits[i] = (char) cp;
                } else {
                    tmpDigits = null;
                }
                if (i == 0) {
                    tmpCodePointZero = cp;
                } else if (cp != tmpCodePointZero + i) {
                    tmpCodePointZero = -1;
                }
            } else {
                tmpCodePointZero = -1;
                tmpDigits = null;
            }
        }
        this.digitStrings = tmpDigitStrings;
        this.codePointZero = tmpCodePointZero;
        if (tmpDigits == null) {
            char[] cArr = DEF_DIGIT_CHARS_ARRAY;
            this.zeroDigit = cArr[0];
            this.digits = cArr;
            return;
        }
        this.zeroDigit = tmpDigits[0];
        this.digits = tmpDigits;
    }

    public char getSignificantDigit() {
        return this.sigDigit;
    }

    public void setSignificantDigit(char sigDigit) {
        this.sigDigit = sigDigit;
    }

    public char getGroupingSeparator() {
        return this.groupingSeparator;
    }

    public void setGroupingSeparator(char groupingSeparator) {
        this.groupingSeparator = groupingSeparator;
        this.groupingSeparatorString = String.valueOf(groupingSeparator);
    }

    public String getGroupingSeparatorString() {
        return this.groupingSeparatorString;
    }

    public void setGroupingSeparatorString(String groupingSeparatorString) {
        if (groupingSeparatorString == null) {
            throw new NullPointerException("The input grouping separator is null");
        }
        this.groupingSeparatorString = groupingSeparatorString;
        if (groupingSeparatorString.length() == 1) {
            this.groupingSeparator = groupingSeparatorString.charAt(0);
        } else {
            this.groupingSeparator = DEF_GROUPING_SEPARATOR;
        }
    }

    public char getDecimalSeparator() {
        return this.decimalSeparator;
    }

    public void setDecimalSeparator(char decimalSeparator) {
        this.decimalSeparator = decimalSeparator;
        this.decimalSeparatorString = String.valueOf(decimalSeparator);
    }

    public String getDecimalSeparatorString() {
        return this.decimalSeparatorString;
    }

    public void setDecimalSeparatorString(String decimalSeparatorString) {
        if (decimalSeparatorString == null) {
            throw new NullPointerException("The input decimal separator is null");
        }
        this.decimalSeparatorString = decimalSeparatorString;
        if (decimalSeparatorString.length() == 1) {
            this.decimalSeparator = decimalSeparatorString.charAt(0);
        } else {
            this.decimalSeparator = DEF_DECIMAL_SEPARATOR;
        }
    }

    public char getPerMill() {
        return this.perMill;
    }

    public void setPerMill(char perMill) {
        this.perMill = perMill;
        this.perMillString = String.valueOf(perMill);
    }

    public String getPerMillString() {
        return this.perMillString;
    }

    public void setPerMillString(String perMillString) {
        if (perMillString == null) {
            throw new NullPointerException("The input permille string is null");
        }
        this.perMillString = perMillString;
        if (perMillString.length() == 1) {
            this.perMill = perMillString.charAt(0);
        } else {
            this.perMill = DEF_PERMILL;
        }
    }

    public char getPercent() {
        return this.percent;
    }

    public void setPercent(char percent) {
        this.percent = percent;
        this.percentString = String.valueOf(percent);
    }

    public String getPercentString() {
        return this.percentString;
    }

    public void setPercentString(String percentString) {
        if (percentString == null) {
            throw new NullPointerException("The input percent sign is null");
        }
        this.percentString = percentString;
        if (percentString.length() == 1) {
            this.percent = percentString.charAt(0);
        } else {
            this.percent = DEF_PERCENT;
        }
    }

    public char getDigit() {
        return this.digit;
    }

    public void setDigit(char digit) {
        this.digit = digit;
    }

    public char getPatternSeparator() {
        return this.patternSeparator;
    }

    public void setPatternSeparator(char patternSeparator) {
        this.patternSeparator = patternSeparator;
    }

    public String getInfinity() {
        return this.infinity;
    }

    public void setInfinity(String infinity) {
        this.infinity = infinity;
    }

    public String getNaN() {
        return this.NaN;
    }

    public void setNaN(String NaN) {
        this.NaN = NaN;
    }

    public char getMinusSign() {
        return this.minusSign;
    }

    public void setMinusSign(char minusSign) {
        this.minusSign = minusSign;
        this.minusString = String.valueOf(minusSign);
    }

    public String getMinusSignString() {
        return this.minusString;
    }

    public void setMinusSignString(String minusSignString) {
        if (minusSignString == null) {
            throw new NullPointerException("The input minus sign is null");
        }
        this.minusString = minusSignString;
        if (minusSignString.length() == 1) {
            this.minusSign = minusSignString.charAt(0);
        } else {
            this.minusSign = DEF_MINUS_SIGN;
        }
    }

    public char getPlusSign() {
        return this.plusSign;
    }

    public void setPlusSign(char plus) {
        this.plusSign = plus;
        this.plusString = String.valueOf(plus);
    }

    public String getPlusSignString() {
        return this.plusString;
    }

    public void setPlusSignString(String plusSignString) {
        if (plusSignString == null) {
            throw new NullPointerException("The input plus sign is null");
        }
        this.plusString = plusSignString;
        if (plusSignString.length() == 1) {
            this.plusSign = plusSignString.charAt(0);
        } else {
            this.plusSign = DEF_PLUS_SIGN;
        }
    }

    public String getCurrencySymbol() {
        return this.currencySymbol;
    }

    public void setCurrencySymbol(String currency) {
        this.currencySymbol = currency;
    }

    public String getInternationalCurrencySymbol() {
        return this.intlCurrencySymbol;
    }

    public void setInternationalCurrencySymbol(String currency) {
        this.intlCurrencySymbol = currency;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public void setCurrency(Currency currency) {
        if (currency == null) {
            throw new NullPointerException();
        }
        this.currency = currency;
        this.intlCurrencySymbol = currency.getCurrencyCode();
        this.currencySymbol = currency.getSymbol(this.requestedLocale);
    }

    public char getMonetaryDecimalSeparator() {
        return this.monetarySeparator;
    }

    public void setMonetaryDecimalSeparator(char sep) {
        this.monetarySeparator = sep;
        this.monetarySeparatorString = String.valueOf(sep);
    }

    public String getMonetaryDecimalSeparatorString() {
        return this.monetarySeparatorString;
    }

    public void setMonetaryDecimalSeparatorString(String sep) {
        if (sep == null) {
            throw new NullPointerException("The input monetary decimal separator is null");
        }
        this.monetarySeparatorString = sep;
        if (sep.length() == 1) {
            this.monetarySeparator = sep.charAt(0);
        } else {
            this.monetarySeparator = DEF_DECIMAL_SEPARATOR;
        }
    }

    public char getMonetaryGroupingSeparator() {
        return this.monetaryGroupingSeparator;
    }

    public void setMonetaryGroupingSeparator(char sep) {
        this.monetaryGroupingSeparator = sep;
        this.monetaryGroupingSeparatorString = String.valueOf(sep);
    }

    public String getMonetaryGroupingSeparatorString() {
        return this.monetaryGroupingSeparatorString;
    }

    public void setMonetaryGroupingSeparatorString(String sep) {
        if (sep == null) {
            throw new NullPointerException("The input monetary grouping separator is null");
        }
        this.monetaryGroupingSeparatorString = sep;
        if (sep.length() == 1) {
            this.monetaryGroupingSeparator = sep.charAt(0);
        } else {
            this.monetaryGroupingSeparator = DEF_GROUPING_SEPARATOR;
        }
    }

    String getCurrencyPattern() {
        return this.currencyPattern;
    }

    public String getExponentMultiplicationSign() {
        return this.exponentMultiplicationSign;
    }

    public void setExponentMultiplicationSign(String exponentMultiplicationSign) {
        this.exponentMultiplicationSign = exponentMultiplicationSign;
    }

    public String getExponentSeparator() {
        return this.exponentSeparator;
    }

    public void setExponentSeparator(String exp) {
        this.exponentSeparator = exp;
    }

    public char getPadEscape() {
        return this.padEscape;
    }

    public void setPadEscape(char c) {
        this.padEscape = c;
    }

    public String getPatternForCurrencySpacing(int itemType, boolean beforeCurrency) {
        if (itemType < 0 || itemType > 2) {
            throw new IllegalArgumentException("unknown currency spacing: " + itemType);
        }
        if (beforeCurrency) {
            return this.currencySpcBeforeSym[itemType];
        }
        return this.currencySpcAfterSym[itemType];
    }

    public void setPatternForCurrencySpacing(int itemType, boolean beforeCurrency, String pattern) {
        if (itemType < 0 || itemType > 2) {
            throw new IllegalArgumentException("unknown currency spacing: " + itemType);
        }
        if (beforeCurrency) {
            this.currencySpcBeforeSym[itemType] = pattern;
        } else {
            this.currencySpcAfterSym[itemType] = pattern;
        }
    }

    public Locale getLocale() {
        return this.requestedLocale;
    }

    public ULocale getULocale() {
        return this.ulocale;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new ICUCloneNotSupportedException(e);
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof DecimalFormatSymbols) {
            if (this == obj) {
                return true;
            }
            DecimalFormatSymbols other = (DecimalFormatSymbols) obj;
            for (int i = 0; i <= 2; i++) {
                if (!this.currencySpcBeforeSym[i].equals(other.currencySpcBeforeSym[i]) || !this.currencySpcAfterSym[i].equals(other.currencySpcAfterSym[i])) {
                    return false;
                }
            }
            char[] cArr = other.digits;
            if (cArr == null) {
                for (int i2 = 0; i2 < 10; i2++) {
                    if (this.digits[i2] != other.zeroDigit + i2) {
                        return false;
                    }
                }
            } else if (!Arrays.equals(this.digits, cArr)) {
                return false;
            }
            return this.groupingSeparator == other.groupingSeparator && this.decimalSeparator == other.decimalSeparator && this.percent == other.percent && this.perMill == other.perMill && this.digit == other.digit && this.minusSign == other.minusSign && this.minusString.equals(other.minusString) && this.patternSeparator == other.patternSeparator && this.infinity.equals(other.infinity) && this.NaN.equals(other.NaN) && this.currencySymbol.equals(other.currencySymbol) && this.intlCurrencySymbol.equals(other.intlCurrencySymbol) && this.padEscape == other.padEscape && this.plusSign == other.plusSign && this.plusString.equals(other.plusString) && this.exponentSeparator.equals(other.exponentSeparator) && this.monetarySeparator == other.monetarySeparator && this.monetaryGroupingSeparator == other.monetaryGroupingSeparator && this.exponentMultiplicationSign.equals(other.exponentMultiplicationSign);
        }
        return false;
    }

    public int hashCode() {
        int result = (this.digits[0] * DEF_PERCENT) + this.groupingSeparator;
        return (result * 37) + this.decimalSeparator;
    }

    /* loaded from: classes.dex */
    private static final class DecFmtDataSink extends UResource.Sink {
        private String[] numberElements;

        public DecFmtDataSink(String[] numberElements) {
            this.numberElements = numberElements;
        }

        public void put(UResource.Key key, UResource.Value value, boolean noFallback) {
            UResource.Table symbolsTable = value.getTable();
            for (int j = 0; symbolsTable.getKeyAndValue(j, key, value); j++) {
                int i = 0;
                while (true) {
                    if (i >= DecimalFormatSymbols.SYMBOL_KEYS.length) {
                        break;
                    } else if (!key.contentEquals(DecimalFormatSymbols.SYMBOL_KEYS[i])) {
                        i++;
                    } else {
                        String[] strArr = this.numberElements;
                        if (strArr[i] == null) {
                            strArr[i] = value.toString();
                        }
                    }
                }
            }
        }
    }

    private void initialize(ULocale locale, NumberingSystem ns) {
        this.requestedLocale = locale.toLocale();
        this.ulocale = locale;
        ULocale keyLocale = ns == null ? locale : locale.setKeywordValue("numbers", ns.getName());
        CacheData data = (CacheData) cachedLocaleData.getInstance(keyLocale, (Object) null);
        setLocale(data.validLocale, data.validLocale);
        setDigitStrings(data.digits);
        String[] numberElements = data.numberElements;
        setDecimalSeparatorString(numberElements[0]);
        setGroupingSeparatorString(numberElements[1]);
        this.patternSeparator = ';';
        setPercentString(numberElements[2]);
        setMinusSignString(numberElements[3]);
        setPlusSignString(numberElements[4]);
        setExponentSeparator(numberElements[5]);
        setPerMillString(numberElements[6]);
        setInfinity(numberElements[7]);
        setNaN(numberElements[8]);
        setMonetaryDecimalSeparatorString(numberElements[9]);
        setMonetaryGroupingSeparatorString(numberElements[10]);
        setExponentMultiplicationSign(numberElements[11]);
        this.digit = '#';
        this.padEscape = '*';
        this.sigDigit = '@';
        CurrencyData.CurrencyDisplayInfo info = CurrencyData.provider.getInstance(locale, true);
        Currency currency = Currency.getInstance(locale);
        this.currency = currency;
        if (currency != null) {
            this.intlCurrencySymbol = currency.getCurrencyCode();
            this.currencySymbol = this.currency.getName(locale, 0, (boolean[]) null);
            CurrencyData.CurrencyFormatInfo fmtInfo = info.getFormatInfo(this.intlCurrencySymbol);
            if (fmtInfo != null) {
                this.currencyPattern = fmtInfo.currencyPattern;
                setMonetaryDecimalSeparatorString(fmtInfo.monetaryDecimalSeparator);
                setMonetaryGroupingSeparatorString(fmtInfo.monetaryGroupingSeparator);
            }
        } else {
            this.intlCurrencySymbol = "XXX";
            this.currencySymbol = "\u00a4";
        }
        initSpacingInfo(info.getSpacingInfo());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static CacheData loadData(ULocale locale) {
        String digitString;
        NumberingSystem ns = NumberingSystem.getInstance(locale);
        String[] digits = new String[10];
        if (ns != null && ns.getRadix() == 10 && !ns.isAlgorithmic() && NumberingSystem.isValidDigitString(ns.getDescription())) {
            String digitString2 = ns.getDescription();
            int offset = 0;
            for (int i = 0; i < 10; i++) {
                int cp = digitString2.codePointAt(offset);
                int nextOffset = Character.charCount(cp) + offset;
                digits[i] = digitString2.substring(offset, nextOffset);
                offset = nextOffset;
            }
            digitString = ns.getName();
        } else {
            digits = DEF_DIGIT_STRINGS_ARRAY;
            digitString = LATIN_NUMBERING_SYSTEM;
        }
        ICUResourceBundle rb = UResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudt63b", locale);
        ULocale validLocale = rb.getULocale();
        String[] numberElements = new String[SYMBOL_KEYS.length];
        DecFmtDataSink sink = new DecFmtDataSink(numberElements);
        try {
            rb.getAllItemsWithFallback("NumberElements/" + digitString + "/" + SYMBOLS, sink);
        } catch (MissingResourceException e) {
        }
        boolean hasNull = false;
        int length = numberElements.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            String entry = numberElements[i2];
            if (entry != null) {
                i2++;
            } else {
                hasNull = true;
                break;
            }
        }
        if (hasNull && !digitString.equals(LATIN_NUMBERING_SYSTEM)) {
            rb.getAllItemsWithFallback("NumberElements/latn/symbols", sink);
        }
        for (int i3 = 0; i3 < SYMBOL_KEYS.length; i3++) {
            if (numberElements[i3] == null) {
                numberElements[i3] = SYMBOL_DEFAULTS[i3];
            }
        }
        if (numberElements[9] == null) {
            numberElements[9] = numberElements[0];
        }
        if (numberElements[10] == null) {
            numberElements[10] = numberElements[1];
        }
        return new CacheData(validLocale, digits, numberElements);
    }

    private void initSpacingInfo(CurrencyData.CurrencySpacingInfo spcInfo) {
        this.currencySpcBeforeSym = spcInfo.getBeforeSymbols();
        this.currencySpcAfterSym = spcInfo.getAfterSymbols();
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        int i = this.serialVersionOnStream;
        if (i < 1) {
            this.monetarySeparator = this.decimalSeparator;
            this.exponential = 'E';
        }
        if (i < 2) {
            this.padEscape = '*';
            this.plusSign = DEF_PLUS_SIGN;
            this.exponentSeparator = String.valueOf(this.exponential);
        }
        if (this.serialVersionOnStream < 3) {
            this.requestedLocale = Locale.getDefault();
        }
        if (this.serialVersionOnStream < 4) {
            this.ulocale = ULocale.forLocale(this.requestedLocale);
        }
        int i2 = this.serialVersionOnStream;
        if (i2 < 5) {
            this.monetaryGroupingSeparator = this.groupingSeparator;
        }
        if (i2 < 6) {
            if (this.currencySpcBeforeSym == null) {
                this.currencySpcBeforeSym = new String[3];
            }
            if (this.currencySpcAfterSym == null) {
                this.currencySpcAfterSym = new String[3];
            }
            initSpacingInfo(CurrencyData.CurrencySpacingInfo.DEFAULT);
        }
        if (this.serialVersionOnStream < 7) {
            if (this.minusString == null) {
                this.minusString = String.valueOf(this.minusSign);
            }
            if (this.plusString == null) {
                this.plusString = String.valueOf(this.plusSign);
            }
        }
        int i3 = this.serialVersionOnStream;
        if (i3 < 8 && this.exponentMultiplicationSign == null) {
            this.exponentMultiplicationSign = "\u00d7";
        }
        if (i3 < 9) {
            if (this.digitStrings == null) {
                this.digitStrings = new String[10];
                char[] cArr = this.digits;
                if (cArr != null && cArr.length == 10) {
                    this.zeroDigit = cArr[0];
                    for (int i4 = 0; i4 < 10; i4++) {
                        this.digitStrings[i4] = String.valueOf(this.digits[i4]);
                    }
                } else {
                    char digit = this.zeroDigit;
                    if (cArr == null) {
                        this.digits = new char[10];
                    }
                    for (int i5 = 0; i5 < 10; i5++) {
                        this.digits[i5] = digit;
                        this.digitStrings[i5] = String.valueOf(digit);
                        digit = (char) (digit + 1);
                    }
                }
            }
            if (this.decimalSeparatorString == null) {
                this.decimalSeparatorString = String.valueOf(this.decimalSeparator);
            }
            if (this.groupingSeparatorString == null) {
                this.groupingSeparatorString = String.valueOf(this.groupingSeparator);
            }
            if (this.percentString == null) {
                this.percentString = String.valueOf(this.percent);
            }
            if (this.perMillString == null) {
                this.perMillString = String.valueOf(this.perMill);
            }
            if (this.monetarySeparatorString == null) {
                this.monetarySeparatorString = String.valueOf(this.monetarySeparator);
            }
            if (this.monetaryGroupingSeparatorString == null) {
                this.monetaryGroupingSeparatorString = String.valueOf(this.monetaryGroupingSeparator);
            }
        }
        this.serialVersionOnStream = 8;
        this.currency = Currency.getInstance(this.intlCurrencySymbol);
        setDigitStrings(this.digitStrings);
    }

    public final ULocale getLocale(ULocale.Type type) {
        return type == ULocale.ACTUAL_LOCALE ? this.actualLocale : this.validLocale;
    }

    final void setLocale(ULocale valid, ULocale actual) {
        if ((valid == null) != (actual == null)) {
            throw new IllegalArgumentException();
        }
        this.validLocale = valid;
        this.actualLocale = actual;
    }

    /* loaded from: classes.dex */
    private static class CacheData {
        final String[] digits;
        final String[] numberElements;
        final ULocale validLocale;

        public CacheData(ULocale loc, String[] digits, String[] numberElements) {
            this.validLocale = loc;
            this.digits = digits;
            this.numberElements = numberElements;
        }
    }
}
