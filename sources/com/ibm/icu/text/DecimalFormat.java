package com.ibm.icu.text;

import com.ibm.icu.impl.number.AffixUtils;
import com.ibm.icu.impl.number.DecimalFormatProperties;
import com.ibm.icu.impl.number.Padder;
import com.ibm.icu.impl.number.PatternStringParser;
import com.ibm.icu.impl.number.PatternStringUtils;
import com.ibm.icu.impl.number.Properties;
import com.ibm.icu.impl.number.parse.NumberParserImpl;
import com.ibm.icu.impl.number.parse.ParsedNumber;
import com.ibm.icu.math.MathContext;
import com.ibm.icu.number.FormattedNumber;
import com.ibm.icu.number.LocalizedNumberFormatter;
import com.ibm.icu.number.NumberFormatter;
import com.ibm.icu.text.PluralRules;
import com.ibm.icu.util.Currency;
import com.ibm.icu.util.CurrencyAmount;
import com.ibm.icu.util.ULocale;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.AttributedCharacterIterator;
import java.text.FieldPosition;
import java.text.ParsePosition;

/* loaded from: classes.dex */
public class DecimalFormat extends NumberFormat {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int PAD_AFTER_PREFIX = 1;
    public static final int PAD_AFTER_SUFFIX = 3;
    public static final int PAD_BEFORE_PREFIX = 0;
    public static final int PAD_BEFORE_SUFFIX = 2;
    private static final long serialVersionUID = 864413376551465018L;
    volatile transient NumberParserImpl currencyParser;
    volatile transient DecimalFormatProperties exportedProperties;
    volatile transient LocalizedNumberFormatter formatter;
    private transient int icuMathContextForm;
    volatile transient NumberParserImpl parser;
    transient DecimalFormatProperties properties;
    private final int serialVersionOnStream;
    volatile transient DecimalFormatSymbols symbols;

    @Deprecated
    /* loaded from: classes.dex */
    public interface PropertySetter {
        @Deprecated
        void set(DecimalFormatProperties decimalFormatProperties);
    }

    public DecimalFormat() {
        this.serialVersionOnStream = 5;
        this.icuMathContextForm = 0;
        ULocale def = ULocale.getDefault(ULocale.Category.FORMAT);
        String pattern = getPattern(def, 0);
        this.symbols = getDefaultSymbols();
        this.properties = new DecimalFormatProperties();
        this.exportedProperties = new DecimalFormatProperties();
        setPropertiesFromPattern(pattern, 1);
        refreshFormatter();
    }

    public DecimalFormat(String pattern) {
        this.serialVersionOnStream = 5;
        this.icuMathContextForm = 0;
        this.symbols = getDefaultSymbols();
        this.properties = new DecimalFormatProperties();
        this.exportedProperties = new DecimalFormatProperties();
        setPropertiesFromPattern(pattern, 1);
        refreshFormatter();
    }

    public DecimalFormat(String pattern, DecimalFormatSymbols symbols) {
        this.serialVersionOnStream = 5;
        this.icuMathContextForm = 0;
        this.symbols = (DecimalFormatSymbols) symbols.clone();
        this.properties = new DecimalFormatProperties();
        this.exportedProperties = new DecimalFormatProperties();
        setPropertiesFromPattern(pattern, 1);
        refreshFormatter();
    }

    public DecimalFormat(String pattern, DecimalFormatSymbols symbols, CurrencyPluralInfo infoInput, int style) {
        this(pattern, symbols, style);
        this.properties.setCurrencyPluralInfo(infoInput);
        refreshFormatter();
    }

    DecimalFormat(String pattern, DecimalFormatSymbols symbols, int choice) {
        this.serialVersionOnStream = 5;
        this.icuMathContextForm = 0;
        this.symbols = (DecimalFormatSymbols) symbols.clone();
        this.properties = new DecimalFormatProperties();
        this.exportedProperties = new DecimalFormatProperties();
        if (choice == 1 || choice == 5 || choice == 7 || choice == 8 || choice == 9 || choice == 6) {
            setPropertiesFromPattern(pattern, 2);
        } else {
            setPropertiesFromPattern(pattern, 1);
        }
        refreshFormatter();
    }

    private static DecimalFormatSymbols getDefaultSymbols() {
        return DecimalFormatSymbols.getInstance();
    }

    public synchronized void applyPattern(String pattern) {
        setPropertiesFromPattern(pattern, 0);
        this.properties.setPositivePrefix((String) null);
        this.properties.setNegativePrefix((String) null);
        this.properties.setPositiveSuffix((String) null);
        this.properties.setNegativeSuffix((String) null);
        this.properties.setCurrencyPluralInfo((CurrencyPluralInfo) null);
        refreshFormatter();
    }

    public synchronized void applyLocalizedPattern(String localizedPattern) {
        String pattern = PatternStringUtils.convertLocalized(localizedPattern, this.symbols, false);
        applyPattern(pattern);
    }

    @Override // com.ibm.icu.text.NumberFormat, java.text.Format
    public Object clone() {
        DecimalFormat other = (DecimalFormat) super.clone();
        other.symbols = (DecimalFormatSymbols) this.symbols.clone();
        other.properties = this.properties.clone();
        other.exportedProperties = new DecimalFormatProperties();
        other.refreshFormatter();
        return other;
    }

    private synchronized void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeInt(0);
        oos.writeObject(this.properties);
        oos.writeObject(this.symbols);
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ObjectStreamField[] serializedFields;
        ObjectInputStream.GetField fieldGetter = ois.readFields();
        ObjectStreamField[] serializedFields2 = fieldGetter.getObjectStreamClass().getFields();
        int serialVersion = fieldGetter.get("serialVersionOnStream", -1);
        if (serialVersion > 5) {
            throw new IOException("Cannot deserialize newer com.ibm.icu.text.DecimalFormat (v" + serialVersion + ")");
        }
        if (serialVersion != 5) {
            this.properties = new DecimalFormatProperties();
            int length = serializedFields2.length;
            String nsp = null;
            String nsp2 = null;
            String ns = null;
            String npp = null;
            String np = null;
            String psp = null;
            String ps = null;
            String ppp = null;
            int i = 0;
            while (i < length) {
                ObjectStreamField field = serializedFields2[i];
                String name = field.getName();
                if (name.equals("decimalSeparatorAlwaysShown")) {
                    serializedFields = serializedFields2;
                    setDecimalSeparatorAlwaysShown(fieldGetter.get("decimalSeparatorAlwaysShown", false));
                } else {
                    serializedFields = serializedFields2;
                    if (name.equals("exponentSignAlwaysShown")) {
                        setExponentSignAlwaysShown(fieldGetter.get("exponentSignAlwaysShown", false));
                    } else if (name.equals("formatWidth")) {
                        setFormatWidth(fieldGetter.get("formatWidth", 0));
                    } else if (name.equals("groupingSize")) {
                        setGroupingSize(fieldGetter.get("groupingSize", (byte) 3));
                    } else if (name.equals("groupingSize2")) {
                        setSecondaryGroupingSize(fieldGetter.get("groupingSize2", (byte) 0));
                    } else if (name.equals("maxSignificantDigits")) {
                        setMaximumSignificantDigits(fieldGetter.get("maxSignificantDigits", 6));
                    } else if (name.equals("minExponentDigits")) {
                        setMinimumExponentDigits(fieldGetter.get("minExponentDigits", (byte) 0));
                    } else if (name.equals("minSignificantDigits")) {
                        setMinimumSignificantDigits(fieldGetter.get("minSignificantDigits", 1));
                    } else if (name.equals("multiplier")) {
                        setMultiplier(fieldGetter.get("multiplier", 1));
                    } else if (name.equals("pad")) {
                        setPadCharacter(fieldGetter.get("pad", ' '));
                    } else if (name.equals("padPosition")) {
                        setPadPosition(fieldGetter.get("padPosition", 0));
                    } else if (name.equals("parseBigDecimal")) {
                        setParseBigDecimal(fieldGetter.get("parseBigDecimal", false));
                    } else if (name.equals("parseRequireDecimalPoint")) {
                        setDecimalPatternMatchRequired(fieldGetter.get("parseRequireDecimalPoint", false));
                    } else if (name.equals("roundingMode")) {
                        setRoundingMode(fieldGetter.get("roundingMode", 0));
                    } else if (name.equals("useExponentialNotation")) {
                        setScientificNotation(fieldGetter.get("useExponentialNotation", false));
                    } else if (name.equals("useSignificantDigits")) {
                        setSignificantDigitsUsed(fieldGetter.get("useSignificantDigits", false));
                    } else if (name.equals("currencyPluralInfo")) {
                        setCurrencyPluralInfo((CurrencyPluralInfo) fieldGetter.get("currencyPluralInfo", (Object) null));
                    } else if (name.equals("mathContext")) {
                        setMathContextICU((MathContext) fieldGetter.get("mathContext", (Object) null));
                    } else if (name.equals("negPrefixPattern")) {
                        ns = (String) fieldGetter.get("negPrefixPattern", (Object) null);
                    } else if (name.equals("negSuffixPattern")) {
                        nsp = (String) fieldGetter.get("negSuffixPattern", (Object) null);
                    } else if (name.equals("negativePrefix")) {
                        npp = (String) fieldGetter.get("negativePrefix", (Object) null);
                    } else if (name.equals("negativeSuffix")) {
                        nsp2 = (String) fieldGetter.get("negativeSuffix", (Object) null);
                    } else if (name.equals("posPrefixPattern")) {
                        ps = (String) fieldGetter.get("posPrefixPattern", (Object) null);
                    } else if (name.equals("posSuffixPattern")) {
                        np = (String) fieldGetter.get("posSuffixPattern", (Object) null);
                    } else if (name.equals("positivePrefix")) {
                        ppp = (String) fieldGetter.get("positivePrefix", (Object) null);
                    } else if (name.equals("positiveSuffix")) {
                        psp = (String) fieldGetter.get("positiveSuffix", (Object) null);
                    } else if (name.equals("roundingIncrement")) {
                        setRoundingIncrement((BigDecimal) fieldGetter.get("roundingIncrement", (Object) null));
                    } else if (name.equals("symbols")) {
                        setDecimalFormatSymbols((DecimalFormatSymbols) fieldGetter.get("symbols", (Object) null));
                    }
                }
                i++;
                serializedFields2 = serializedFields;
            }
            if (ns == null) {
                this.properties.setNegativePrefix(npp);
            } else {
                this.properties.setNegativePrefixPattern(ns);
            }
            if (nsp == null) {
                this.properties.setNegativeSuffix(nsp2);
            } else {
                this.properties.setNegativeSuffixPattern(nsp);
            }
            if (ps == null) {
                this.properties.setPositivePrefix(ppp);
            } else {
                this.properties.setPositivePrefixPattern(ps);
            }
            if (np == null) {
                this.properties.setPositiveSuffix(psp);
            } else {
                this.properties.setPositiveSuffixPattern(np);
            }
            try {
                Field getter = NumberFormat.class.getDeclaredField("groupingUsed");
                getter.setAccessible(true);
                setGroupingUsed(((Boolean) getter.get(this)).booleanValue());
                Field getter2 = NumberFormat.class.getDeclaredField("parseIntegerOnly");
                getter2.setAccessible(true);
                setParseIntegerOnly(((Boolean) getter2.get(this)).booleanValue());
                Field getter3 = NumberFormat.class.getDeclaredField("maximumIntegerDigits");
                getter3.setAccessible(true);
                setMaximumIntegerDigits(((Integer) getter3.get(this)).intValue());
                Field getter4 = NumberFormat.class.getDeclaredField("minimumIntegerDigits");
                getter4.setAccessible(true);
                setMinimumIntegerDigits(((Integer) getter4.get(this)).intValue());
                Field getter5 = NumberFormat.class.getDeclaredField("maximumFractionDigits");
                getter5.setAccessible(true);
                setMaximumFractionDigits(((Integer) getter5.get(this)).intValue());
                Field getter6 = NumberFormat.class.getDeclaredField("minimumFractionDigits");
                getter6.setAccessible(true);
                setMinimumFractionDigits(((Integer) getter6.get(this)).intValue());
                Field getter7 = NumberFormat.class.getDeclaredField("currency");
                getter7.setAccessible(true);
                setCurrency((Currency) getter7.get(this));
                Field getter8 = NumberFormat.class.getDeclaredField("parseStrict");
                getter8.setAccessible(true);
                setParseStrict(((Boolean) getter8.get(this)).booleanValue());
                if (this.symbols == null) {
                    this.symbols = getDefaultSymbols();
                }
                this.exportedProperties = new DecimalFormatProperties();
                refreshFormatter();
            } catch (IllegalAccessException e) {
                throw new IOException(e);
            } catch (IllegalArgumentException e2) {
                throw new IOException(e2);
            } catch (NoSuchFieldException e3) {
                throw new IOException(e3);
            } catch (SecurityException e4) {
                throw new IOException(e4);
            }
        } else if (serializedFields2.length > 1) {
            throw new IOException("Too many fields when reading serial version 5");
        } else {
            ois.readInt();
            Object serializedProperties = ois.readObject();
            if (serializedProperties instanceof DecimalFormatProperties) {
                this.properties = (DecimalFormatProperties) serializedProperties;
            } else {
                this.properties = ((Properties) serializedProperties).getInstance();
            }
            this.symbols = (DecimalFormatSymbols) ois.readObject();
            this.exportedProperties = new DecimalFormatProperties();
            refreshFormatter();
        }
    }

    @Override // com.ibm.icu.text.NumberFormat
    public StringBuffer format(double number, StringBuffer result, FieldPosition fieldPosition) {
        FormattedNumber output = this.formatter.format(number);
        fieldPositionHelper(output, fieldPosition, result.length());
        output.appendTo(result);
        return result;
    }

    @Override // com.ibm.icu.text.NumberFormat
    public StringBuffer format(long number, StringBuffer result, FieldPosition fieldPosition) {
        FormattedNumber output = this.formatter.format(number);
        fieldPositionHelper(output, fieldPosition, result.length());
        output.appendTo(result);
        return result;
    }

    @Override // com.ibm.icu.text.NumberFormat
    public StringBuffer format(BigInteger number, StringBuffer result, FieldPosition fieldPosition) {
        FormattedNumber output = this.formatter.format(number);
        fieldPositionHelper(output, fieldPosition, result.length());
        output.appendTo(result);
        return result;
    }

    @Override // com.ibm.icu.text.NumberFormat
    public StringBuffer format(BigDecimal number, StringBuffer result, FieldPosition fieldPosition) {
        FormattedNumber output = this.formatter.format(number);
        fieldPositionHelper(output, fieldPosition, result.length());
        output.appendTo(result);
        return result;
    }

    @Override // com.ibm.icu.text.NumberFormat
    public StringBuffer format(com.ibm.icu.math.BigDecimal number, StringBuffer result, FieldPosition fieldPosition) {
        FormattedNumber output = this.formatter.format(number);
        fieldPositionHelper(output, fieldPosition, result.length());
        output.appendTo(result);
        return result;
    }

    @Override // java.text.Format
    public AttributedCharacterIterator formatToCharacterIterator(Object obj) {
        if (!(obj instanceof Number)) {
            throw new IllegalArgumentException();
        }
        Number number = (Number) obj;
        FormattedNumber output = this.formatter.format(number);
        return output.getFieldIterator();
    }

    @Override // com.ibm.icu.text.NumberFormat
    public StringBuffer format(CurrencyAmount currAmt, StringBuffer result, FieldPosition fieldPosition) {
        FormattedNumber output = this.formatter.format(currAmt);
        fieldPositionHelper(output, fieldPosition, result.length());
        output.appendTo(result);
        return result;
    }

    @Override // com.ibm.icu.text.NumberFormat
    public Number parse(String text, ParsePosition parsePosition) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        if (parsePosition == null) {
            parsePosition = new ParsePosition(0);
        }
        if (parsePosition.getIndex() < 0) {
            throw new IllegalArgumentException("Cannot start parsing at a negative offset");
        }
        if (parsePosition.getIndex() >= text.length()) {
            return null;
        }
        ParsedNumber result = new ParsedNumber();
        int startIndex = parsePosition.getIndex();
        NumberParserImpl parser = getParser();
        parser.parse(text, startIndex, true, result);
        if (result.success()) {
            parsePosition.setIndex(result.charEnd);
            Number number = result.getNumber(parser.getParseFlags());
            if (number instanceof BigDecimal) {
                return safeConvertBigDecimal((BigDecimal) number);
            }
            return number;
        }
        parsePosition.setErrorIndex(result.charEnd + startIndex);
        return null;
    }

    @Override // com.ibm.icu.text.NumberFormat
    public CurrencyAmount parseCurrency(CharSequence text, ParsePosition parsePosition) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        if (parsePosition == null) {
            parsePosition = new ParsePosition(0);
        }
        if (parsePosition.getIndex() < 0) {
            throw new IllegalArgumentException("Cannot start parsing at a negative offset");
        }
        if (parsePosition.getIndex() >= text.length()) {
            return null;
        }
        ParsedNumber result = new ParsedNumber();
        int startIndex = parsePosition.getIndex();
        NumberParserImpl parser = getCurrencyParser();
        parser.parse(text.toString(), startIndex, true, result);
        if (result.success()) {
            parsePosition.setIndex(result.charEnd);
            Number number = result.getNumber(parser.getParseFlags());
            if (number instanceof BigDecimal) {
                number = safeConvertBigDecimal((BigDecimal) number);
            }
            Currency currency = Currency.getInstance(result.currencyCode);
            return new CurrencyAmount(number, currency);
        }
        parsePosition.setErrorIndex(result.charEnd + startIndex);
        return null;
    }

    public synchronized DecimalFormatSymbols getDecimalFormatSymbols() {
        return (DecimalFormatSymbols) this.symbols.clone();
    }

    public synchronized void setDecimalFormatSymbols(DecimalFormatSymbols newSymbols) {
        this.symbols = (DecimalFormatSymbols) newSymbols.clone();
        refreshFormatter();
    }

    public synchronized String getPositivePrefix() {
        return this.formatter.getAffixImpl(true, false);
    }

    public synchronized void setPositivePrefix(String prefix) {
        if (prefix == null) {
            throw new NullPointerException();
        }
        this.properties.setPositivePrefix(prefix);
        refreshFormatter();
    }

    public synchronized String getNegativePrefix() {
        return this.formatter.getAffixImpl(true, true);
    }

    public synchronized void setNegativePrefix(String prefix) {
        if (prefix == null) {
            throw new NullPointerException();
        }
        this.properties.setNegativePrefix(prefix);
        refreshFormatter();
    }

    public synchronized String getPositiveSuffix() {
        return this.formatter.getAffixImpl(false, false);
    }

    public synchronized void setPositiveSuffix(String suffix) {
        if (suffix == null) {
            throw new NullPointerException();
        }
        this.properties.setPositiveSuffix(suffix);
        refreshFormatter();
    }

    public synchronized String getNegativeSuffix() {
        return this.formatter.getAffixImpl(false, true);
    }

    public synchronized void setNegativeSuffix(String suffix) {
        if (suffix == null) {
            throw new NullPointerException();
        }
        this.properties.setNegativeSuffix(suffix);
        refreshFormatter();
    }

    @Deprecated
    public synchronized boolean getSignAlwaysShown() {
        return this.properties.getSignAlwaysShown();
    }

    @Deprecated
    public synchronized void setSignAlwaysShown(boolean value) {
        this.properties.setSignAlwaysShown(value);
        refreshFormatter();
    }

    public synchronized int getMultiplier() {
        if (this.properties.getMultiplier() != null) {
            return this.properties.getMultiplier().intValue();
        }
        return (int) Math.pow(10.0d, this.properties.getMagnitudeMultiplier());
    }

    public synchronized void setMultiplier(int multiplier) {
        if (multiplier == 0) {
            throw new IllegalArgumentException("Multiplier must be nonzero.");
        }
        int delta = 0;
        int value = multiplier;
        while (true) {
            if (value == 1) {
                break;
            }
            delta++;
            int temp = value / 10;
            if (temp * 10 != value) {
                delta = -1;
                break;
            }
            value = temp;
        }
        if (delta != -1) {
            this.properties.setMagnitudeMultiplier(delta);
            this.properties.setMultiplier((BigDecimal) null);
        } else {
            this.properties.setMagnitudeMultiplier(0);
            this.properties.setMultiplier(BigDecimal.valueOf(multiplier));
        }
        refreshFormatter();
    }

    public synchronized BigDecimal getRoundingIncrement() {
        return this.exportedProperties.getRoundingIncrement();
    }

    public synchronized void setRoundingIncrement(BigDecimal increment) {
        if (increment != null) {
            if (increment.compareTo(BigDecimal.ZERO) == 0) {
                this.properties.setMaximumFractionDigits(Integer.MAX_VALUE);
                return;
            }
        }
        this.properties.setRoundingIncrement(increment);
        refreshFormatter();
    }

    public synchronized void setRoundingIncrement(com.ibm.icu.math.BigDecimal increment) {
        BigDecimal javaBigDecimal = increment == null ? null : increment.toBigDecimal();
        setRoundingIncrement(javaBigDecimal);
    }

    public synchronized void setRoundingIncrement(double increment) {
        if (increment == 0.0d) {
            setRoundingIncrement((BigDecimal) null);
        } else {
            BigDecimal javaBigDecimal = BigDecimal.valueOf(increment);
            setRoundingIncrement(javaBigDecimal);
        }
    }

    @Override // com.ibm.icu.text.NumberFormat
    public synchronized int getRoundingMode() {
        RoundingMode mode;
        mode = this.exportedProperties.getRoundingMode();
        return mode == null ? 0 : mode.ordinal();
    }

    @Override // com.ibm.icu.text.NumberFormat
    public synchronized void setRoundingMode(int roundingMode) {
        this.properties.setRoundingMode(RoundingMode.valueOf(roundingMode));
        refreshFormatter();
    }

    public synchronized java.math.MathContext getMathContext() {
        java.math.MathContext mathContext;
        mathContext = this.exportedProperties.getMathContext();
        if (mathContext == null) {
            throw new AssertionError();
        }
        return mathContext;
    }

    public synchronized void setMathContext(java.math.MathContext mathContext) {
        this.properties.setMathContext(mathContext);
        refreshFormatter();
    }

    public synchronized MathContext getMathContextICU() {
        java.math.MathContext mathContext;
        mathContext = getMathContext();
        return new MathContext(mathContext.getPrecision(), this.icuMathContextForm, false, mathContext.getRoundingMode().ordinal());
    }

    public synchronized void setMathContextICU(MathContext mathContextICU) {
        java.math.MathContext mathContext;
        this.icuMathContextForm = mathContextICU.getForm();
        if (mathContextICU.getLostDigits()) {
            mathContext = new java.math.MathContext(mathContextICU.getDigits(), RoundingMode.UNNECESSARY);
        } else {
            mathContext = new java.math.MathContext(mathContextICU.getDigits(), RoundingMode.valueOf(mathContextICU.getRoundingMode()));
        }
        setMathContext(mathContext);
    }

    @Override // com.ibm.icu.text.NumberFormat
    public synchronized int getMinimumIntegerDigits() {
        return this.exportedProperties.getMinimumIntegerDigits();
    }

    @Override // com.ibm.icu.text.NumberFormat
    public synchronized void setMinimumIntegerDigits(int value) {
        int max = this.properties.getMaximumIntegerDigits();
        if (max >= 0 && max < value) {
            this.properties.setMaximumIntegerDigits(value);
        }
        this.properties.setMinimumIntegerDigits(value);
        refreshFormatter();
    }

    @Override // com.ibm.icu.text.NumberFormat
    public synchronized int getMaximumIntegerDigits() {
        return this.exportedProperties.getMaximumIntegerDigits();
    }

    @Override // com.ibm.icu.text.NumberFormat
    public synchronized void setMaximumIntegerDigits(int value) {
        int min = this.properties.getMinimumIntegerDigits();
        if (min >= 0 && min > value) {
            this.properties.setMinimumIntegerDigits(value);
        }
        this.properties.setMaximumIntegerDigits(value);
        refreshFormatter();
    }

    @Override // com.ibm.icu.text.NumberFormat
    public synchronized int getMinimumFractionDigits() {
        return this.exportedProperties.getMinimumFractionDigits();
    }

    @Override // com.ibm.icu.text.NumberFormat
    public synchronized void setMinimumFractionDigits(int value) {
        int max = this.properties.getMaximumFractionDigits();
        if (max >= 0 && max < value) {
            this.properties.setMaximumFractionDigits(value);
        }
        this.properties.setMinimumFractionDigits(value);
        refreshFormatter();
    }

    @Override // com.ibm.icu.text.NumberFormat
    public synchronized int getMaximumFractionDigits() {
        return this.exportedProperties.getMaximumFractionDigits();
    }

    @Override // com.ibm.icu.text.NumberFormat
    public synchronized void setMaximumFractionDigits(int value) {
        int min = this.properties.getMinimumFractionDigits();
        if (min >= 0 && min > value) {
            this.properties.setMinimumFractionDigits(value);
        }
        this.properties.setMaximumFractionDigits(value);
        refreshFormatter();
    }

    public synchronized boolean areSignificantDigitsUsed() {
        boolean z;
        if (this.properties.getMinimumSignificantDigits() == -1) {
            z = this.properties.getMaximumSignificantDigits() != -1;
        }
        return z;
    }

    public synchronized void setSignificantDigitsUsed(boolean useSignificantDigits) {
        int oldMinSig = this.properties.getMinimumSignificantDigits();
        int oldMaxSig = this.properties.getMaximumSignificantDigits();
        if (useSignificantDigits) {
            if (oldMinSig != -1 || oldMaxSig != -1) {
                return;
            }
        } else if (oldMinSig == -1 && oldMaxSig == -1) {
            return;
        }
        int minSig = useSignificantDigits ? 1 : -1;
        int maxSig = useSignificantDigits ? 6 : -1;
        this.properties.setMinimumSignificantDigits(minSig);
        this.properties.setMaximumSignificantDigits(maxSig);
        refreshFormatter();
    }

    public synchronized int getMinimumSignificantDigits() {
        return this.exportedProperties.getMinimumSignificantDigits();
    }

    public synchronized void setMinimumSignificantDigits(int value) {
        int max = this.properties.getMaximumSignificantDigits();
        if (max >= 0 && max < value) {
            this.properties.setMaximumSignificantDigits(value);
        }
        this.properties.setMinimumSignificantDigits(value);
        refreshFormatter();
    }

    public synchronized int getMaximumSignificantDigits() {
        return this.exportedProperties.getMaximumSignificantDigits();
    }

    public synchronized void setMaximumSignificantDigits(int value) {
        int min = this.properties.getMinimumSignificantDigits();
        if (min >= 0 && min > value) {
            this.properties.setMinimumSignificantDigits(value);
        }
        this.properties.setMaximumSignificantDigits(value);
        refreshFormatter();
    }

    public synchronized int getFormatWidth() {
        return this.properties.getFormatWidth();
    }

    public synchronized void setFormatWidth(int width) {
        this.properties.setFormatWidth(width);
        refreshFormatter();
    }

    public synchronized char getPadCharacter() {
        CharSequence paddingString = this.properties.getPadString();
        if (paddingString == null) {
            return " ".charAt(0);
        }
        return paddingString.charAt(0);
    }

    public synchronized void setPadCharacter(char padChar) {
        this.properties.setPadString(Character.toString(padChar));
        refreshFormatter();
    }

    public synchronized int getPadPosition() {
        Padder.PadPosition loc;
        loc = this.properties.getPadPosition();
        return loc == null ? 0 : loc.toOld();
    }

    public synchronized void setPadPosition(int padPos) {
        this.properties.setPadPosition(Padder.PadPosition.fromOld(padPos));
        refreshFormatter();
    }

    public synchronized boolean isScientificNotation() {
        return this.properties.getMinimumExponentDigits() != -1;
    }

    public synchronized void setScientificNotation(boolean useScientific) {
        if (useScientific) {
            this.properties.setMinimumExponentDigits(1);
        } else {
            this.properties.setMinimumExponentDigits(-1);
        }
        refreshFormatter();
    }

    public synchronized byte getMinimumExponentDigits() {
        return (byte) this.properties.getMinimumExponentDigits();
    }

    public synchronized void setMinimumExponentDigits(byte minExpDig) {
        this.properties.setMinimumExponentDigits(minExpDig);
        refreshFormatter();
    }

    public synchronized boolean isExponentSignAlwaysShown() {
        return this.properties.getExponentSignAlwaysShown();
    }

    public synchronized void setExponentSignAlwaysShown(boolean expSignAlways) {
        this.properties.setExponentSignAlwaysShown(expSignAlways);
        refreshFormatter();
    }

    @Override // com.ibm.icu.text.NumberFormat
    public synchronized boolean isGroupingUsed() {
        return this.properties.getGroupingUsed();
    }

    @Override // com.ibm.icu.text.NumberFormat
    public synchronized void setGroupingUsed(boolean enabled) {
        this.properties.setGroupingUsed(enabled);
        refreshFormatter();
    }

    public synchronized int getGroupingSize() {
        if (this.properties.getGroupingSize() < 0) {
            return 0;
        }
        return this.properties.getGroupingSize();
    }

    public synchronized void setGroupingSize(int width) {
        this.properties.setGroupingSize(width);
        refreshFormatter();
    }

    public synchronized int getSecondaryGroupingSize() {
        int grouping2 = this.properties.getSecondaryGroupingSize();
        if (grouping2 < 0) {
            return 0;
        }
        return grouping2;
    }

    public synchronized void setSecondaryGroupingSize(int width) {
        this.properties.setSecondaryGroupingSize(width);
        refreshFormatter();
    }

    @Deprecated
    public synchronized int getMinimumGroupingDigits() {
        if (this.properties.getMinimumGroupingDigits() > 0) {
            return this.properties.getMinimumGroupingDigits();
        }
        return 1;
    }

    @Deprecated
    public synchronized void setMinimumGroupingDigits(int number) {
        this.properties.setMinimumGroupingDigits(number);
        refreshFormatter();
    }

    public synchronized boolean isDecimalSeparatorAlwaysShown() {
        return this.properties.getDecimalSeparatorAlwaysShown();
    }

    public synchronized void setDecimalSeparatorAlwaysShown(boolean value) {
        this.properties.setDecimalSeparatorAlwaysShown(value);
        refreshFormatter();
    }

    @Override // com.ibm.icu.text.NumberFormat
    public synchronized Currency getCurrency() {
        return this.exportedProperties.getCurrency();
    }

    @Override // com.ibm.icu.text.NumberFormat
    public synchronized void setCurrency(Currency currency) {
        this.properties.setCurrency(currency);
        if (currency != null) {
            this.symbols.setCurrency(currency);
            String symbol = currency.getName(this.symbols.getULocale(), 0, (boolean[]) null);
            this.symbols.setCurrencySymbol(symbol);
        }
        refreshFormatter();
    }

    public synchronized Currency.CurrencyUsage getCurrencyUsage() {
        Currency.CurrencyUsage usage;
        usage = this.properties.getCurrencyUsage();
        if (usage == null) {
            usage = Currency.CurrencyUsage.STANDARD;
        }
        return usage;
    }

    public synchronized void setCurrencyUsage(Currency.CurrencyUsage usage) {
        this.properties.setCurrencyUsage(usage);
        refreshFormatter();
    }

    public synchronized CurrencyPluralInfo getCurrencyPluralInfo() {
        return this.properties.getCurrencyPluralInfo();
    }

    public synchronized void setCurrencyPluralInfo(CurrencyPluralInfo newInfo) {
        this.properties.setCurrencyPluralInfo(newInfo);
        refreshFormatter();
    }

    public synchronized boolean isParseBigDecimal() {
        return this.properties.getParseToBigDecimal();
    }

    public synchronized void setParseBigDecimal(boolean value) {
        this.properties.setParseToBigDecimal(value);
        refreshFormatter();
    }

    @Deprecated
    public int getParseMaxDigits() {
        return 1000;
    }

    @Deprecated
    public void setParseMaxDigits(int maxDigits) {
    }

    @Override // com.ibm.icu.text.NumberFormat
    public synchronized boolean isParseStrict() {
        return this.properties.getParseMode() == DecimalFormatProperties.ParseMode.STRICT;
    }

    @Override // com.ibm.icu.text.NumberFormat
    public synchronized void setParseStrict(boolean parseStrict) {
        DecimalFormatProperties.ParseMode mode = parseStrict ? DecimalFormatProperties.ParseMode.STRICT : DecimalFormatProperties.ParseMode.LENIENT;
        this.properties.setParseMode(mode);
        refreshFormatter();
    }

    @Override // com.ibm.icu.text.NumberFormat
    public synchronized boolean isParseIntegerOnly() {
        return this.properties.getParseIntegerOnly();
    }

    @Override // com.ibm.icu.text.NumberFormat
    public synchronized void setParseIntegerOnly(boolean parseIntegerOnly) {
        this.properties.setParseIntegerOnly(parseIntegerOnly);
        refreshFormatter();
    }

    public synchronized boolean isDecimalPatternMatchRequired() {
        return this.properties.getDecimalPatternMatchRequired();
    }

    public synchronized void setDecimalPatternMatchRequired(boolean value) {
        this.properties.setDecimalPatternMatchRequired(value);
        refreshFormatter();
    }

    @Deprecated
    public synchronized boolean getParseNoExponent() {
        return this.properties.getParseNoExponent();
    }

    @Deprecated
    public synchronized void setParseNoExponent(boolean value) {
        this.properties.setParseNoExponent(value);
        refreshFormatter();
    }

    @Deprecated
    public synchronized boolean getParseCaseSensitive() {
        return this.properties.getParseCaseSensitive();
    }

    @Deprecated
    public synchronized void setParseCaseSensitive(boolean value) {
        this.properties.setParseCaseSensitive(value);
        refreshFormatter();
    }

    @Override // com.ibm.icu.text.NumberFormat
    public synchronized boolean equals(Object obj) {
        boolean z = false;
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DecimalFormat)) {
            return false;
        }
        DecimalFormat other = (DecimalFormat) obj;
        if (this.properties.equals(other.properties)) {
            if (this.symbols.equals(other.symbols)) {
                z = true;
            }
        }
        return z;
    }

    @Override // com.ibm.icu.text.NumberFormat
    public synchronized int hashCode() {
        return this.properties.hashCode() ^ this.symbols.hashCode();
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(getClass().getName());
        result.append("@");
        result.append(Integer.toHexString(hashCode()));
        result.append(" { symbols@");
        result.append(Integer.toHexString(this.symbols.hashCode()));
        synchronized (this) {
            this.properties.toStringBare(result);
        }
        result.append(" }");
        return result.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004c A[Catch: all -> 0x006d, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0012, B:7:0x0018, B:9:0x001e, B:11:0x0028, B:13:0x0032, B:15:0x003c, B:21:0x004c, B:22:0x0067), top: B:28:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized String toPattern() {
        DecimalFormatProperties tprops;
        boolean useCurrency;
        tprops = new DecimalFormatProperties().copyFrom(this.properties);
        if (tprops.getCurrency() == null && tprops.getCurrencyPluralInfo() == null && tprops.getCurrencyUsage() == null && !AffixUtils.hasCurrencySymbols(tprops.getPositivePrefixPattern()) && !AffixUtils.hasCurrencySymbols(tprops.getPositiveSuffixPattern()) && !AffixUtils.hasCurrencySymbols(tprops.getNegativePrefixPattern()) && !AffixUtils.hasCurrencySymbols(tprops.getNegativeSuffixPattern())) {
            useCurrency = false;
            if (useCurrency) {
                tprops.setMinimumFractionDigits(this.exportedProperties.getMinimumFractionDigits());
                tprops.setMaximumFractionDigits(this.exportedProperties.getMaximumFractionDigits());
                tprops.setRoundingIncrement(this.exportedProperties.getRoundingIncrement());
            }
        }
        useCurrency = true;
        if (useCurrency) {
        }
        return PatternStringUtils.propertiesToPatternString(tprops);
    }

    public synchronized String toLocalizedPattern() {
        String pattern;
        pattern = toPattern();
        return PatternStringUtils.convertLocalized(pattern, this.symbols, true);
    }

    public LocalizedNumberFormatter toNumberFormatter() {
        return this.formatter;
    }

    @Deprecated
    public PluralRules.IFixedDecimal getFixedDecimal(double number) {
        return this.formatter.format(number).getFixedDecimal();
    }

    void refreshFormatter() {
        if (this.exportedProperties == null) {
            return;
        }
        ULocale locale = getLocale(ULocale.ACTUAL_LOCALE);
        if (locale == null) {
            locale = this.symbols.getLocale(ULocale.ACTUAL_LOCALE);
        }
        if (locale == null) {
            locale = this.symbols.getULocale();
        }
        if (locale == null) {
            throw new AssertionError();
        }
        this.formatter = NumberFormatter.fromDecimalFormat(this.properties, this.symbols, this.exportedProperties).locale(locale);
        this.parser = null;
        this.currencyParser = null;
    }

    NumberParserImpl getParser() {
        if (this.parser == null) {
            this.parser = NumberParserImpl.createParserFromProperties(this.properties, this.symbols, false);
        }
        return this.parser;
    }

    NumberParserImpl getCurrencyParser() {
        if (this.currencyParser == null) {
            this.currencyParser = NumberParserImpl.createParserFromProperties(this.properties, this.symbols, true);
        }
        return this.currencyParser;
    }

    private Number safeConvertBigDecimal(BigDecimal number) {
        try {
            return new com.ibm.icu.math.BigDecimal(number);
        } catch (NumberFormatException e) {
            if (number.signum() > 0 && number.scale() < 0) {
                return Double.valueOf(Double.POSITIVE_INFINITY);
            }
            if (number.scale() < 0) {
                return Double.valueOf(Double.NEGATIVE_INFINITY);
            }
            if (number.signum() < 0) {
                return Double.valueOf(-0.0d);
            }
            return Double.valueOf(0.0d);
        }
    }

    void setPropertiesFromPattern(String pattern, int ignoreRounding) {
        if (pattern == null) {
            throw new NullPointerException();
        }
        PatternStringParser.parseToExistingProperties(pattern, this.properties, ignoreRounding);
    }

    static void fieldPositionHelper(FormattedNumber formatted, FieldPosition fieldPosition, int offset) {
        fieldPosition.setBeginIndex(0);
        fieldPosition.setEndIndex(0);
        boolean found = formatted.nextFieldPosition(fieldPosition);
        if (found && offset != 0) {
            fieldPosition.setBeginIndex(fieldPosition.getBeginIndex() + offset);
            fieldPosition.setEndIndex(fieldPosition.getEndIndex() + offset);
        }
    }

    @Deprecated
    public synchronized void setProperties(PropertySetter func) {
        func.set(this.properties);
        refreshFormatter();
    }
}
