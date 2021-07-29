package com.ibm.icu.text;

import com.ibm.icu.impl.CurrencyData;
import com.ibm.icu.util.ULocale;
import java.util.Locale;
import java.util.Map;

public abstract class CurrencyDisplayNames {
    public abstract String getName(String str);

    public abstract String getNarrowSymbol(String str);

    public abstract String getPluralName(String str, String str2);

    public abstract String getSymbol(String str);

    public abstract ULocale getULocale();

    public abstract Map<String, String> nameMap();

    public abstract Map<String, String> symbolMap();

    public static CurrencyDisplayNames getInstance(ULocale locale) {
        return CurrencyData.provider.getInstance(locale, true);
    }

    public static CurrencyDisplayNames getInstance(Locale locale) {
        return getInstance(locale, false);
    }

    public static CurrencyDisplayNames getInstance(ULocale locale, boolean noSubstitute) {
        return CurrencyData.provider.getInstance(locale, !noSubstitute);
    }

    public static CurrencyDisplayNames getInstance(Locale locale, boolean noSubstitute) {
        return getInstance(ULocale.forLocale(locale), noSubstitute);
    }

    @Deprecated
    public static boolean hasData() {
        return CurrencyData.provider.hasData();
    }

    @Deprecated
    protected CurrencyDisplayNames() {
    }
}
