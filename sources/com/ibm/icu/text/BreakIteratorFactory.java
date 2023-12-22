package com.ibm.icu.text;

import com.ibm.icu.impl.Assert;
import com.ibm.icu.impl.ICUBinary;
import com.ibm.icu.impl.ICULocaleService;
import com.ibm.icu.impl.ICUResourceBundle;
import com.ibm.icu.impl.ICUService;
import com.ibm.icu.text.BreakIterator;
import com.ibm.icu.util.ULocale;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.MissingResourceException;

/* loaded from: classes.dex */
final class BreakIteratorFactory extends BreakIterator.BreakIteratorServiceShim {
    static final ICULocaleService service = new BFService();
    private static final String[] KIND_NAMES = {"grapheme", "word", "line", "sentence", "title"};

    BreakIteratorFactory() {
    }

    @Override // com.ibm.icu.text.BreakIterator.BreakIteratorServiceShim
    public Object registerInstance(BreakIterator iter, ULocale locale, int kind) {
        iter.setText(new java.text.StringCharacterIterator(""));
        return service.registerObject(iter, locale, kind);
    }

    @Override // com.ibm.icu.text.BreakIterator.BreakIteratorServiceShim
    public boolean unregister(Object key) {
        ICULocaleService iCULocaleService = service;
        if (iCULocaleService.isDefault()) {
            return false;
        }
        return iCULocaleService.unregisterFactory((ICUService.Factory) key);
    }

    @Override // com.ibm.icu.text.BreakIterator.BreakIteratorServiceShim
    public Locale[] getAvailableLocales() {
        ICULocaleService iCULocaleService = service;
        if (iCULocaleService == null) {
            return ICUResourceBundle.getAvailableLocales();
        }
        return iCULocaleService.getAvailableLocales();
    }

    @Override // com.ibm.icu.text.BreakIterator.BreakIteratorServiceShim
    public ULocale[] getAvailableULocales() {
        ICULocaleService iCULocaleService = service;
        if (iCULocaleService == null) {
            return ICUResourceBundle.getAvailableULocales();
        }
        return iCULocaleService.getAvailableULocales();
    }

    @Override // com.ibm.icu.text.BreakIterator.BreakIteratorServiceShim
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

    /* loaded from: classes.dex */
    private static class BFService extends ICULocaleService {
        BFService() {
            super("BreakIterator");
            registerFactory(new ICULocaleService.ICUResourceBundleFactory() { // from class: com.ibm.icu.text.BreakIteratorFactory.BFService.1RBBreakIteratorFactory
                protected Object handleCreate(ULocale loc, int kind, ICUService srvc) {
                    return BreakIteratorFactory.createBreakInstance(loc, kind);
                }
            });
            markDefault();
        }

        public String validateFallbackLocale() {
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static BreakIterator createBreakInstance(ULocale locale, int kind) {
        String ssKeyword;
        String lbKeyValue;
        RuleBasedBreakIterator iter = null;
        ICUResourceBundle rb = ICUResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudt63b/brkitr", locale, ICUResourceBundle.OpenType.LOCALE_ROOT);
        String typeKeyExt = null;
        if (kind == 2 && (lbKeyValue = locale.getKeywordValue("lb")) != null && (lbKeyValue.equals("strict") || lbKeyValue.equals("normal") || lbKeyValue.equals("loose"))) {
            typeKeyExt = "_" + lbKeyValue;
        }
        try {
            String typeKey = typeKeyExt == null ? KIND_NAMES[kind] : KIND_NAMES[kind] + typeKeyExt;
            String brkfname = rb.getStringWithFallback("boundaries/" + typeKey);
            String rulesFileName = "brkitr/" + brkfname;
            ByteBuffer bytes = ICUBinary.getData(rulesFileName);
            try {
                iter = RuleBasedBreakIterator.getInstanceFromCompiledRules(bytes);
            } catch (IOException e) {
                Assert.fail(e);
            }
            ULocale uloc = ULocale.forLocale(rb.getLocale());
            iter.setLocale(uloc, uloc);
            if (kind == 3 && (ssKeyword = locale.getKeywordValue("ss")) != null && ssKeyword.equals("standard")) {
                ULocale base = new ULocale(locale.getBaseName());
                return FilteredBreakIteratorBuilder.getInstance(base).wrapIteratorWithFilter(iter);
            }
            return iter;
        } catch (Exception e2) {
            throw new MissingResourceException(e2.toString(), "", "");
        }
    }
}
