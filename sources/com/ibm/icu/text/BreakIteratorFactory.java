package com.ibm.icu.text;

import com.ibm.icu.impl.Assert;
import com.ibm.icu.impl.ICUBinary;
import com.ibm.icu.impl.ICULocaleService;
import com.ibm.icu.impl.ICUResourceBundle;
import com.ibm.icu.impl.ICUService;
import com.ibm.icu.text.BreakIterator;
import com.ibm.icu.util.ULocale;
import java.io.IOException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Locale;
import java.util.MissingResourceException;

final class BreakIteratorFactory extends BreakIterator.BreakIteratorServiceShim {
    private static final String[] KIND_NAMES = {"grapheme", "word", "line", "sentence", "title"};
    static final ICULocaleService service = new BFService();

    BreakIteratorFactory() {
    }

    public Object registerInstance(BreakIterator iter, ULocale locale, int kind) {
        iter.setText((CharacterIterator) new StringCharacterIterator(""));
        return service.registerObject(iter, locale, kind);
    }

    public boolean unregister(Object key) {
        ICULocaleService iCULocaleService = service;
        if (iCULocaleService.isDefault()) {
            return false;
        }
        return iCULocaleService.unregisterFactory((ICUService.Factory) key);
    }

    public Locale[] getAvailableLocales() {
        ICULocaleService iCULocaleService = service;
        if (iCULocaleService == null) {
            return ICUResourceBundle.getAvailableLocales();
        }
        return iCULocaleService.getAvailableLocales();
    }

    public ULocale[] getAvailableULocales() {
        ICULocaleService iCULocaleService = service;
        if (iCULocaleService == null) {
            return ICUResourceBundle.getAvailableULocales();
        }
        return iCULocaleService.getAvailableULocales();
    }

    public BreakIterator createBreakIterator(ULocale locale, int kind) {
        ICULocaleService iCULocaleService = service;
        if (iCULocaleService.isDefault()) {
            return createBreakInstance(locale, kind);
        }
        ULocale[] actualLoc = new ULocale[1];
        BreakIterator iter = (BreakIterator) iCULocaleService.get(locale, kind, actualLoc);
        iter.setLocale(actualLoc[0], actualLoc[0]);
        return iter;
    }

    private static class BFService extends ICULocaleService {
        BFService() {
            super("BreakIterator");
            registerFactory(new ICULocaleService.ICUResourceBundleFactory() {
                /* access modifiers changed from: protected */
                public Object handleCreate(ULocale loc, int kind, ICUService srvc) {
                    return BreakIteratorFactory.createBreakInstance(loc, kind);
                }
            });
            markDefault();
        }

        public String validateFallbackLocale() {
            return "";
        }
    }

    /* access modifiers changed from: private */
    public static BreakIterator createBreakInstance(ULocale locale, int kind) {
        String typeKey;
        String ssKeyword;
        String lbKeyValue;
        RuleBasedBreakIterator iter = null;
        ICUResourceBundle rb = ICUResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudt63b/brkitr", locale, ICUResourceBundle.OpenType.LOCALE_ROOT);
        String typeKeyExt = null;
        if (kind == 2 && (lbKeyValue = locale.getKeywordValue("lb")) != null && (lbKeyValue.equals("strict") || lbKeyValue.equals("normal") || lbKeyValue.equals("loose"))) {
            typeKeyExt = "_" + lbKeyValue;
        }
        if (typeKeyExt == null) {
            try {
                typeKey = KIND_NAMES[kind];
            } catch (Exception e) {
                throw new MissingResourceException(e.toString(), "", "");
            }
        } else {
            typeKey = KIND_NAMES[kind] + typeKeyExt;
        }
        try {
            iter = RuleBasedBreakIterator.getInstanceFromCompiledRules(ICUBinary.getData("brkitr/" + rb.getStringWithFallback("boundaries/" + typeKey)));
        } catch (IOException e2) {
            Assert.fail(e2);
        }
        ULocale uloc = ULocale.forLocale(rb.getLocale());
        iter.setLocale(uloc, uloc);
        if (kind != 3 || (ssKeyword = locale.getKeywordValue("ss")) == null || !ssKeyword.equals("standard")) {
            return iter;
        }
        return FilteredBreakIteratorBuilder.getInstance(new ULocale(locale.getBaseName())).wrapIteratorWithFilter(iter);
    }
}
