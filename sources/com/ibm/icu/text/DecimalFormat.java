package com.ibm.icu.text;

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
    public interface PropertySetter {
        @Deprecated
        void set(DecimalFormatProperties decimalFormatProperties);
    }

    public DecimalFormat() {
        this.serialVersionOnStream = 5;
        this.icuMathContextForm = 0;
        String pattern = getPattern(ULocale.getDefault(ULocale.Category.FORMAT), 0);
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

    public DecimalFormat(String pattern, DecimalFormatSymbols symbols2) {
        this.serialVersionOnStream = 5;
        this.icuMathContextForm = 0;
        this.symbols = (DecimalFormatSymbols) symbols2.clone();
        this.properties = new DecimalFormatProperties();
        this.exportedProperties = new DecimalFormatProperties();
        setPropertiesFromPattern(pattern, 1);
        refreshFormatter();
    }

    public DecimalFormat(String pattern, DecimalFormatSymbols symbols2, CurrencyPluralInfo infoInput, int style) {
        this(pattern, symbols2, style);
        this.properties.setCurrencyPluralInfo(infoInput);
        refreshFormatter();
    }

    DecimalFormat(String pattern, DecimalFormatSymbols symbols2, int choice) {
        this.serialVersionOnStream = 5;
        this.icuMathContextForm = 0;
        this.symbols = (DecimalFormatSymbols) symbols2.clone();
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
        applyPattern(PatternStringUtils.convertLocalized(localizedPattern, this.symbols, false));
    }

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
        } else if (serialVersion != 5) {
            this.properties = new DecimalFormatProperties();
            int length = serializedFields2.length;
            String nsp = null;
            String ns = null;
            String npp = null;
            String np = null;
            String psp = null;
            String ps = null;
            String ppp = null;
            String pp = null;
            int i = 0;
            while (i < length) {
                String name = serializedFields2[i].getName();
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
                        npp = (String) fieldGetter.get("negPrefixPattern", (Object) null);
                    } else if (name.equals("negSuffixPattern")) {
                        nsp = (String) fieldGetter.get("negSuffixPattern", (Object) null);
                    } else if (name.equals("negativePrefix")) {
                        np = (String) fieldGetter.get("negativePrefix", (Object) null);
                    } else if (name.equals("negativeSuffix")) {
                        ns = (String) fieldGetter.get("negativeSuffix", (Object) null);
                    } else if (name.equals("posPrefixPattern")) {
                        ppp = (String) fieldGetter.get("posPrefixPattern", (Object) null);
                    } else if (name.equals("posSuffixPattern")) {
                        psp = (String) fieldGetter.get("posSuffixPattern", (Object) null);
                    } else if (name.equals("positivePrefix")) {
                        pp = (String) fieldGetter.get("positivePrefix", (Object) null);
                    } else if (name.equals("positiveSuffix")) {
                        ps = (String) fieldGetter.get("positiveSuffix", (Object) null);
                    } else if (name.equals("roundingIncrement")) {
                        setRoundingIncrement((BigDecimal) fieldGetter.get("roundingIncrement", (Object) null));
                    } else if (name.equals("symbols")) {
                        setDecimalFormatSymbols((DecimalFormatSymbols) fieldGetter.get("symbols", (Object) null));
                    }
                }
                i++;
                serializedFields2 = serializedFields;
            }
            if (npp == null) {
                this.properties.setNegativePrefix(np);
            } else {
                this.properties.setNegativePrefixPattern(npp);
            }
            if (nsp == null) {
                this.properties.setNegativeSuffix(ns);
            } else {
                this.properties.setNegativeSuffixPattern(nsp);
            }
            if (ppp == null) {
                this.properties.setPositivePrefix(pp);
            } else {
                this.properties.setPositivePrefixPattern(ppp);
            }
            if (psp == null) {
                this.properties.setPositiveSuffix(ps);
            } else {
                this.properties.setPositiveSuffixPattern(psp);
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
            } catch (IllegalArgumentException e) {
                throw new IOException(e);
            } catch (IllegalAccessException e2) {
                throw new IOException(e2);
            } catch (NoSuchFieldException e3) {
                throw new IOException(e3);
            } catch (SecurityException e4) {
                throw new IOException(e4);
            }
        } else if (serializedFields2.length <= 1) {
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
            ObjectStreamField[] objectStreamFieldArr = serializedFields2;
        } else {
            throw new IOException("Too many fields when reading serial version 5");
        }
    }

    public StringBuffer format(double number, StringBuffer result, FieldPosition fieldPosition) {
        FormattedNumber output = this.formatter.format(number);
        fieldPositionHelper(output, fieldPosition, result.length());
        output.appendTo(result);
        return result;
    }

    public StringBuffer format(long number, StringBuffer result, FieldPosition fieldPosition) {
        FormattedNumber output = this.formatter.format(number);
        fieldPositionHelper(output, fieldPosition, result.length());
        output.appendTo(result);
        return result;
    }

    public StringBuffer format(BigInteger number, StringBuffer result, FieldPosition fieldPosition) {
        FormattedNumber output = this.formatter.format(number);
        fieldPositionHelper(output, fieldPosition, result.length());
        output.appendTo(result);
        return result;
    }

    public StringBuffer format(BigDecimal number, StringBuffer result, FieldPosition fieldPosition) {
        FormattedNumber output = this.formatter.format(number);
        fieldPositionHelper(output, fieldPosition, result.length());
        output.appendTo(result);
        return result;
    }

    public StringBuffer format(com.ibm.icu.math.BigDecimal number, StringBuffer result, FieldPosition fieldPosition) {
        FormattedNumber output = this.formatter.format(number);
        fieldPositionHelper(output, fieldPosition, result.length());
        output.appendTo(result);
        return result;
    }

    public AttributedCharacterIterator formatToCharacterIterator(Object obj) {
        if (obj instanceof Number) {
            return this.formatter.format((Number) obj).getFieldIterator();
        }
        throw new IllegalArgumentException();
    }

    public StringBuffer format(CurrencyAmount currAmt, StringBuffer result, FieldPosition fieldPosition) {
        FormattedNumber output = this.formatter.format(currAmt);
        fieldPositionHelper(output, fieldPosition, result.length());
        output.appendTo(result);
        return result;
    }

    public Number parse(String text, ParsePosition parsePosition) {
        if (text != null) {
            if (parsePosition == null) {
                parsePosition = new ParsePosition(0);
            }
            if (parsePosition.getIndex() < 0) {
                throw new IllegalArgumentException("Cannot start parsing at a negative offset");
            } else if (parsePosition.getIndex() >= text.length()) {
                return null;
            } else {
                ParsedNumber result = new ParsedNumber();
                int startIndex = parsePosition.getIndex();
                NumberParserImpl parser2 = getParser();
                parser2.parse(text, startIndex, true, result);
                if (result.success()) {
                    parsePosition.setIndex(result.charEnd);
                    Number number = result.getNumber(parser2.getParseFlags());
                    if (number instanceof BigDecimal) {
                        return safeConvertBigDecimal((BigDecimal) number);
                    }
                    return number;
                }
                parsePosition.setErrorIndex(result.charEnd + startIndex);
                return null;
            }
        } else {
            throw new IllegalArgumentException("Text cannot be null");
        }
    }

    public CurrencyAmount parseCurrency(CharSequence text, ParsePosition parsePosition) {
        if (text != null) {
            if (parsePosition == null) {
                parsePosition = new ParsePosition(0);
            }
            if (parsePosition.getIndex() < 0) {
                throw new IllegalArgumentException("Cannot start parsing at a negative offset");
            } else if (parsePosition.getIndex() >= text.length()) {
                return null;
            } else {
                ParsedNumber result = new ParsedNumber();
                int startIndex = parsePosition.getIndex();
                NumberParserImpl parser2 = getCurrencyParser();
                parser2.parse(text.toString(), startIndex, true, result);
                if (result.success()) {
                    parsePosition.setIndex(result.charEnd);
                    Number number = result.getNumber(parser2.getParseFlags());
                    if (number instanceof BigDecimal) {
                        number = safeConvertBigDecimal((BigDecimal) number);
                    }
                    return new CurrencyAmount(number, Currency.getInstance(result.currencyCode));
                }
                parsePosition.setErrorIndex(result.charEnd + startIndex);
                return null;
            }
        } else {
            throw new IllegalArgumentException("Text cannot be null");
        }
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
        if (prefix != null) {
            this.properties.setPositivePrefix(prefix);
            refreshFormatter();
        } else {
            throw new NullPointerException();
        }
    }

    public synchronized String getNegativePrefix() {
        return this.formatter.getAffixImpl(true, true);
    }

    public synchronized void setNegativePrefix(String prefix) {
        if (prefix != null) {
            this.properties.setNegativePrefix(prefix);
            refreshFormatter();
        } else {
            throw new NullPointerException();
        }
    }

    public synchronized String getPositiveSuffix() {
        return this.formatter.getAffixImpl(false, false);
    }

    public synchronized void setPositiveSuffix(String suffix) {
        if (suffix != null) {
            this.properties.setPositiveSuffix(suffix);
            refreshFormatter();
        } else {
            throw new NullPointerException();
        }
    }

    public synchronized String getNegativeSuffix() {
        return this.formatter.getAffixImpl(false, true);
    }

    public synchronized void setNegativeSuffix(String suffix) {
        if (suffix != null) {
            this.properties.setNegativeSuffix(suffix);
            refreshFormatter();
        } else {
            throw new NullPointerException();
        }
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
        return (int) Math.pow(10.0d, (double) this.properties.getMagnitudeMultiplier());
    }

    public synchronized void setMultiplier(int multiplier) {
        if (multiplier != 0) {
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
                this.properties.setMultiplier(BigDecimal.valueOf((long) multiplier));
            }
            refreshFormatter();
        } else {
            throw new IllegalArgumentException("Multiplier must be nonzero.");
        }
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
        setRoundingIncrement(increment == null ? null : increment.toBigDecimal());
    }

    public synchronized void setRoundingIncrement(double increment) {
        if (increment == 0.0d) {
            setRoundingIncrement((BigDecimal) null);
        } else {
            setRoundingIncrement(BigDecimal.valueOf(increment));
        }
    }

    public synchronized int getRoundingMode() {
        RoundingMode mode;
        mode = this.exportedProperties.getRoundingMode();
        return mode == null ? 0 : mode.ordinal();
    }

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

    public synchronized int getMinimumIntegerDigits() {
        return this.exportedProperties.getMinimumIntegerDigits();
    }

    public synchronized void setMinimumIntegerDigits(int value) {
        int max = this.properties.getMaximumIntegerDigits();
        if (max >= 0 && max < value) {
            this.properties.setMaximumIntegerDigits(value);
        }
        this.properties.setMinimumIntegerDigits(value);
        refreshFormatter();
    }

    public synchronized int getMaximumIntegerDigits() {
        return this.exportedProperties.getMaximumIntegerDigits();
    }

    public synchronized void setMaximumIntegerDigits(int value) {
        int min = this.properties.getMinimumIntegerDigits();
        if (min >= 0 && min > value) {
            this.properties.setMinimumIntegerDigits(value);
        }
        this.properties.setMaximumIntegerDigits(value);
        refreshFormatter();
    }

    public synchronized int getMinimumFractionDigits() {
        return this.exportedProperties.getMinimumFractionDigits();
    }

    public synchronized void setMinimumFractionDigits(int value) {
        int max = this.properties.getMaximumFractionDigits();
        if (max >= 0 && max < value) {
            this.properties.setMaximumFractionDigits(value);
        }
        this.properties.setMinimumFractionDigits(value);
        refreshFormatter();
    }

    public synchronized int getMaximumFractionDigits() {
        return this.exportedProperties.getMaximumFractionDigits();
    }

    public synchronized void setMaximumFractionDigits(int value) {
        int min = this.properties.getMinimumFractionDigits();
        if (min >= 0 && min > value) {
            this.properties.setMinimumFractionDigits(value);
        }
        this.properties.setMaximumFractionDigits(value);
        refreshFormatter();
    }

    public synchronized boolean areSignificantDigitsUsed() {
        return (this.properties.getMinimumSignificantDigits() == -1 && this.properties.getMaximumSignificantDigits() == -1) ? false : true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0015, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setSignificantDigitsUsed(boolean r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            com.ibm.icu.impl.number.DecimalFormatProperties r0 = r5.properties     // Catch:{ all -> 0x0033 }
            int r0 = r0.getMinimumSignificantDigits()     // Catch:{ all -> 0x0033 }
            com.ibm.icu.impl.number.DecimalFormatProperties r1 = r5.properties     // Catch:{ all -> 0x0033 }
            int r1 = r1.getMaximumSignificantDigits()     // Catch:{ all -> 0x0033 }
            r2 = -1
            if (r6 == 0) goto L_0x0016
            if (r0 != r2) goto L_0x0014
            if (r1 == r2) goto L_0x001c
        L_0x0014:
            monitor-exit(r5)
            return
        L_0x0016:
            if (r0 != r2) goto L_0x001c
            if (r1 != r2) goto L_0x001c
            monitor-exit(r5)
            return
        L_0x001c:
            if (r6 == 0) goto L_0x0020
            r3 = 1
            goto L_0x0021
        L_0x0020:
            r3 = r2
        L_0x0021:
            if (r6 == 0) goto L_0x0024
            r2 = 6
        L_0x0024:
            com.ibm.icu.impl.number.DecimalFormatProperties r4 = r5.properties     // Catch:{ all -> 0x0033 }
            r4.setMinimumSignificantDigits(r3)     // Catch:{ all -> 0x0033 }
            com.ibm.icu.impl.number.DecimalFormatProperties r4 = r5.properties     // Catch:{ all -> 0x0033 }
            r4.setMaximumSignificantDigits(r2)     // Catch:{ all -> 0x0033 }
            r5.refreshFormatter()     // Catch:{ all -> 0x0033 }
            monitor-exit(r5)
            return
        L_0x0033:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.DecimalFormat.setSignificantDigitsUsed(boolean):void");
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

    public synchronized boolean isGroupingUsed() {
        return this.properties.getGroupingUsed();
    }

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
        if (this.properties.getMinimumGroupingDigits() <= 0) {
            return 1;
        }
        return this.properties.getMinimumGroupingDigits();
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

    public synchronized Currency getCurrency() {
        return this.exportedProperties.getCurrency();
    }

    public synchronized void setCurrency(Currency currency) {
        this.properties.setCurrency(currency);
        if (currency != null) {
            this.symbols.setCurrency(currency);
            this.symbols.setCurrencySymbol(currency.getName(this.symbols.getULocale(), 0, (boolean[]) null));
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

    public synchronized boolean isParseStrict() {
        return this.properties.getParseMode() == DecimalFormatProperties.ParseMode.STRICT;
    }

    public synchronized void setParseStrict(boolean parseStrict) {
        this.properties.setParseMode(parseStrict ? DecimalFormatProperties.ParseMode.STRICT : DecimalFormatProperties.ParseMode.LENIENT);
        refreshFormatter();
    }

    public synchronized boolean isParseIntegerOnly() {
        return this.properties.getParseIntegerOnly();
    }

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

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x002a, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            r0 = 0
            if (r6 != 0) goto L_0x0006
            monitor-exit(r5)
            return r0
        L_0x0006:
            r1 = 1
            if (r6 != r5) goto L_0x000b
            monitor-exit(r5)
            return r1
        L_0x000b:
            boolean r2 = r6 instanceof com.ibm.icu.text.DecimalFormat     // Catch:{ all -> 0x002b }
            if (r2 != 0) goto L_0x0011
            monitor-exit(r5)
            return r0
        L_0x0011:
            r2 = r6
            com.ibm.icu.text.DecimalFormat r2 = (com.ibm.icu.text.DecimalFormat) r2     // Catch:{ all -> 0x002b }
            com.ibm.icu.impl.number.DecimalFormatProperties r3 = r5.properties     // Catch:{ all -> 0x002b }
            com.ibm.icu.impl.number.DecimalFormatProperties r4 = r2.properties     // Catch:{ all -> 0x002b }
            boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x002b }
            if (r3 == 0) goto L_0x0029
            com.ibm.icu.text.DecimalFormatSymbols r3 = r5.symbols     // Catch:{ all -> 0x002b }
            com.ibm.icu.text.DecimalFormatSymbols r4 = r2.symbols     // Catch:{ all -> 0x002b }
            boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x002b }
            if (r3 == 0) goto L_0x0029
            r0 = r1
        L_0x0029:
            monitor-exit(r5)
            return r0
        L_0x002b:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.DecimalFormat.equals(java.lang.Object):boolean");
    }

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

    /* JADX WARNING: Removed duplicated region for block: B:20:0x004c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.String toPattern() {
        /*
            r3 = this;
            monitor-enter(r3)
            com.ibm.icu.impl.number.DecimalFormatProperties r0 = new com.ibm.icu.impl.number.DecimalFormatProperties     // Catch:{ all -> 0x006d }
            r0.<init>()     // Catch:{ all -> 0x006d }
            com.ibm.icu.impl.number.DecimalFormatProperties r1 = r3.properties     // Catch:{ all -> 0x006d }
            com.ibm.icu.impl.number.DecimalFormatProperties r0 = r0.copyFrom(r1)     // Catch:{ all -> 0x006d }
            com.ibm.icu.util.Currency r1 = r0.getCurrency()     // Catch:{ all -> 0x006d }
            if (r1 != 0) goto L_0x0049
            com.ibm.icu.text.CurrencyPluralInfo r1 = r0.getCurrencyPluralInfo()     // Catch:{ all -> 0x006d }
            if (r1 != 0) goto L_0x0049
            com.ibm.icu.util.Currency$CurrencyUsage r1 = r0.getCurrencyUsage()     // Catch:{ all -> 0x006d }
            if (r1 != 0) goto L_0x0049
            java.lang.String r1 = r0.getPositivePrefixPattern()     // Catch:{ all -> 0x006d }
            boolean r1 = com.ibm.icu.impl.number.AffixUtils.hasCurrencySymbols(r1)     // Catch:{ all -> 0x006d }
            if (r1 != 0) goto L_0x0049
            java.lang.String r1 = r0.getPositiveSuffixPattern()     // Catch:{ all -> 0x006d }
            boolean r1 = com.ibm.icu.impl.number.AffixUtils.hasCurrencySymbols(r1)     // Catch:{ all -> 0x006d }
            if (r1 != 0) goto L_0x0049
            java.lang.String r1 = r0.getNegativePrefixPattern()     // Catch:{ all -> 0x006d }
            boolean r1 = com.ibm.icu.impl.number.AffixUtils.hasCurrencySymbols(r1)     // Catch:{ all -> 0x006d }
            if (r1 != 0) goto L_0x0049
            java.lang.String r1 = r0.getNegativeSuffixPattern()     // Catch:{ all -> 0x006d }
            boolean r1 = com.ibm.icu.impl.number.AffixUtils.hasCurrencySymbols(r1)     // Catch:{ all -> 0x006d }
            if (r1 == 0) goto L_0x0047
            goto L_0x0049
        L_0x0047:
            r1 = 0
            goto L_0x004a
        L_0x0049:
            r1 = 1
        L_0x004a:
            if (r1 == 0) goto L_0x0067
            com.ibm.icu.impl.number.DecimalFormatProperties r2 = r3.exportedProperties     // Catch:{ all -> 0x006d }
            int r2 = r2.getMinimumFractionDigits()     // Catch:{ all -> 0x006d }
            r0.setMinimumFractionDigits(r2)     // Catch:{ all -> 0x006d }
            com.ibm.icu.impl.number.DecimalFormatProperties r2 = r3.exportedProperties     // Catch:{ all -> 0x006d }
            int r2 = r2.getMaximumFractionDigits()     // Catch:{ all -> 0x006d }
            r0.setMaximumFractionDigits(r2)     // Catch:{ all -> 0x006d }
            com.ibm.icu.impl.number.DecimalFormatProperties r2 = r3.exportedProperties     // Catch:{ all -> 0x006d }
            java.math.BigDecimal r2 = r2.getRoundingIncrement()     // Catch:{ all -> 0x006d }
            r0.setRoundingIncrement(r2)     // Catch:{ all -> 0x006d }
        L_0x0067:
            java.lang.String r2 = com.ibm.icu.impl.number.PatternStringUtils.propertiesToPatternString(r0)     // Catch:{ all -> 0x006d }
            monitor-exit(r3)
            return r2
        L_0x006d:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.DecimalFormat.toPattern():java.lang.String");
    }

    public synchronized String toLocalizedPattern() {
        return PatternStringUtils.convertLocalized(toPattern(), this.symbols, true);
    }

    public LocalizedNumberFormatter toNumberFormatter() {
        return this.formatter;
    }

    @Deprecated
    public PluralRules.IFixedDecimal getFixedDecimal(double number) {
        return this.formatter.format(number).getFixedDecimal();
    }

    /* access modifiers changed from: package-private */
    public void refreshFormatter() {
        if (this.exportedProperties != null) {
            ULocale locale = getLocale(ULocale.ACTUAL_LOCALE);
            if (locale == null) {
                locale = this.symbols.getLocale(ULocale.ACTUAL_LOCALE);
            }
            if (locale == null) {
                locale = this.symbols.getULocale();
            }
            if (locale != null) {
                this.formatter = NumberFormatter.fromDecimalFormat(this.properties, this.symbols, this.exportedProperties).locale(locale);
                this.parser = null;
                this.currencyParser = null;
                return;
            }
            throw new AssertionError();
        }
    }

    /* access modifiers changed from: package-private */
    public NumberParserImpl getParser() {
        if (this.parser == null) {
            this.parser = NumberParserImpl.createParserFromProperties(this.properties, this.symbols, false);
        }
        return this.parser;
    }

    /* access modifiers changed from: package-private */
    public NumberParserImpl getCurrencyParser() {
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

    /* access modifiers changed from: package-private */
    public void setPropertiesFromPattern(String pattern, int ignoreRounding) {
        if (pattern != null) {
            PatternStringParser.parseToExistingProperties(pattern, this.properties, ignoreRounding);
            return;
        }
        throw new NullPointerException();
    }

    static void fieldPositionHelper(FormattedNumber formatted, FieldPosition fieldPosition, int offset) {
        fieldPosition.setBeginIndex(0);
        fieldPosition.setEndIndex(0);
        if (formatted.nextFieldPosition(fieldPosition) && offset != 0) {
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
