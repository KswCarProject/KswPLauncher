package com.ibm.icu.text;

import com.ibm.icu.impl.CurrencyData;
import com.ibm.icu.text.PluralRules;
import com.ibm.icu.util.ICUCloneNotSupportedException;
import com.ibm.icu.util.ULocale;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public class CurrencyPluralInfo implements Cloneable, Serializable {
    private static final String defaultCurrencyPluralPattern;
    private static final char[] defaultCurrencyPluralPatternChar;
    private static final long serialVersionUID = 1;
    private static final char[] tripleCurrencySign;
    private static final String tripleCurrencyStr;
    private Map<String, String> pluralCountToCurrencyUnitPattern = null;
    private PluralRules pluralRules = null;
    private ULocale ulocale = null;

    public CurrencyPluralInfo() {
        initialize(ULocale.getDefault(ULocale.Category.FORMAT));
    }

    public CurrencyPluralInfo(Locale locale) {
        initialize(ULocale.forLocale(locale));
    }

    public CurrencyPluralInfo(ULocale locale) {
        initialize(locale);
    }

    public static CurrencyPluralInfo getInstance() {
        return new CurrencyPluralInfo();
    }

    public static CurrencyPluralInfo getInstance(Locale locale) {
        return new CurrencyPluralInfo(locale);
    }

    public static CurrencyPluralInfo getInstance(ULocale locale) {
        return new CurrencyPluralInfo(locale);
    }

    public PluralRules getPluralRules() {
        return this.pluralRules;
    }

    public String getCurrencyPluralPattern(String pluralCount) {
        String currencyPluralPattern = this.pluralCountToCurrencyUnitPattern.get(pluralCount);
        if (currencyPluralPattern != null) {
            return currencyPluralPattern;
        }
        if (!pluralCount.equals(PluralRules.KEYWORD_OTHER)) {
            currencyPluralPattern = this.pluralCountToCurrencyUnitPattern.get(PluralRules.KEYWORD_OTHER);
        }
        if (currencyPluralPattern == null) {
            return defaultCurrencyPluralPattern;
        }
        return currencyPluralPattern;
    }

    public ULocale getLocale() {
        return this.ulocale;
    }

    public void setPluralRules(String ruleDescription) {
        this.pluralRules = PluralRules.createRules(ruleDescription);
    }

    public void setCurrencyPluralPattern(String pluralCount, String pattern) {
        this.pluralCountToCurrencyUnitPattern.put(pluralCount, pattern);
    }

    public void setLocale(ULocale loc) {
        this.ulocale = loc;
        initialize(loc);
    }

    public Object clone() {
        try {
            CurrencyPluralInfo other = (CurrencyPluralInfo) super.clone();
            other.ulocale = (ULocale) this.ulocale.clone();
            other.pluralCountToCurrencyUnitPattern = new HashMap();
            for (String pluralCount : this.pluralCountToCurrencyUnitPattern.keySet()) {
                other.pluralCountToCurrencyUnitPattern.put(pluralCount, this.pluralCountToCurrencyUnitPattern.get(pluralCount));
            }
            return other;
        } catch (CloneNotSupportedException e) {
            throw new ICUCloneNotSupportedException(e);
        }
    }

    public boolean equals(Object a) {
        if (!(a instanceof CurrencyPluralInfo)) {
            return false;
        }
        CurrencyPluralInfo other = (CurrencyPluralInfo) a;
        if (!this.pluralRules.equals(other.pluralRules) || !this.pluralCountToCurrencyUnitPattern.equals(other.pluralCountToCurrencyUnitPattern)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.pluralCountToCurrencyUnitPattern.hashCode() ^ this.pluralRules.hashCode()) ^ this.ulocale.hashCode();
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public String select(double number) {
        return this.pluralRules.select(number);
    }

    @Deprecated
    public String select(PluralRules.FixedDecimal numberInfo) {
        return this.pluralRules.select((PluralRules.IFixedDecimal) numberInfo);
    }

    @Deprecated
    public Iterator<String> pluralPatternIterator() {
        return this.pluralCountToCurrencyUnitPattern.keySet().iterator();
    }

    private void initialize(ULocale uloc) {
        this.ulocale = uloc;
        this.pluralRules = PluralRules.forLocale(uloc);
        setupCurrencyPluralPattern(uloc);
    }

    private void setupCurrencyPluralPattern(ULocale uloc) {
        ULocale uLocale = uloc;
        this.pluralCountToCurrencyUnitPattern = new HashMap();
        String numberStylePattern = NumberFormat.getPattern(uLocale, 0);
        int separatorIndex = numberStylePattern.indexOf(";");
        String negNumberPattern = null;
        int i = -1;
        if (separatorIndex != -1) {
            negNumberPattern = numberStylePattern.substring(separatorIndex + 1);
            numberStylePattern = numberStylePattern.substring(0, separatorIndex);
        }
        for (Map.Entry<String, String> e : CurrencyData.provider.getInstance(uLocale, true).getUnitPatterns().entrySet()) {
            String pluralCount = e.getKey();
            String pattern = e.getValue();
            String patternWithNumber = pattern.replace("{0}", numberStylePattern);
            String str = tripleCurrencyStr;
            String patternWithCurrencySign = patternWithNumber.replace("{1}", str);
            if (separatorIndex != i) {
                patternWithCurrencySign = patternWithCurrencySign + ";" + pattern.replace("{0}", negNumberPattern).replace("{1}", str);
            }
            this.pluralCountToCurrencyUnitPattern.put(pluralCount, patternWithCurrencySign);
            ULocale uLocale2 = uloc;
            i = -1;
        }
    }

    static {
        char[] cArr = {164, 164, 164};
        tripleCurrencySign = cArr;
        tripleCurrencyStr = new String(cArr);
        char[] cArr2 = {0, '.', '#', '#', ' ', 164, 164, 164};
        defaultCurrencyPluralPatternChar = cArr2;
        defaultCurrencyPluralPattern = new String(cArr2);
    }
}
