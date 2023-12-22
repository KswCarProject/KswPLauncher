package com.ibm.icu.text;

import com.ibm.icu.impl.ICULocaleService;
import com.ibm.icu.impl.ICUResourceBundle;
import com.ibm.icu.impl.ICUService;
import com.ibm.icu.impl.coll.CollationLoader;
import com.ibm.icu.impl.coll.CollationTailoring;
import com.ibm.icu.text.Collator;
import com.ibm.icu.util.ICUCloneNotSupportedException;
import com.ibm.icu.util.Output;
import com.ibm.icu.util.ULocale;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Set;

/* loaded from: classes.dex */
final class CollatorServiceShim extends Collator.ServiceShim {
    private static ICULocaleService service = new CService();

    CollatorServiceShim() {
    }

    @Override // com.ibm.icu.text.Collator.ServiceShim
    Collator getInstance(ULocale locale) {
        try {
            ULocale[] actualLoc = new ULocale[1];
            Collator coll = (Collator) service.get(locale, actualLoc);
            if (coll == null) {
                throw new MissingResourceException("Could not locate Collator data", "", "");
            }
            return (Collator) coll.clone();
        } catch (CloneNotSupportedException e) {
            throw new ICUCloneNotSupportedException(e);
        }
    }

    @Override // com.ibm.icu.text.Collator.ServiceShim
    Object registerInstance(Collator collator, ULocale locale) {
        collator.setLocale(locale, locale);
        return service.registerObject(collator, locale);
    }

    @Override // com.ibm.icu.text.Collator.ServiceShim
    Object registerFactory(Collator.CollatorFactory f) {
        return service.registerFactory(new ICULocaleService.LocaleKeyFactory(f) { // from class: com.ibm.icu.text.CollatorServiceShim.1CFactory
            Collator.CollatorFactory delegate;

            {
                super(f.visible());
                this.delegate = f;
            }

            public Object handleCreate(ULocale loc, int kind, ICUService srvc) {
                Object coll = this.delegate.createCollator(loc);
                return coll;
            }

            public String getDisplayName(String id, ULocale displayLocale) {
                ULocale objectLocale = new ULocale(id);
                return this.delegate.getDisplayName(objectLocale, displayLocale);
            }

            public Set<String> getSupportedIDs() {
                return this.delegate.getSupportedLocaleIDs();
            }
        });
    }

    @Override // com.ibm.icu.text.Collator.ServiceShim
    boolean unregister(Object registryKey) {
        return service.unregisterFactory((ICUService.Factory) registryKey);
    }

    @Override // com.ibm.icu.text.Collator.ServiceShim
    Locale[] getAvailableLocales() {
        if (service.isDefault()) {
            Locale[] result = ICUResourceBundle.getAvailableLocales("com/ibm/icu/impl/data/icudt63b/coll", ICUResourceBundle.ICU_DATA_CLASS_LOADER);
            return result;
        }
        Locale[] result2 = service.getAvailableLocales();
        return result2;
    }

    @Override // com.ibm.icu.text.Collator.ServiceShim
    ULocale[] getAvailableULocales() {
        if (service.isDefault()) {
            ULocale[] result = ICUResourceBundle.getAvailableULocales("com/ibm/icu/impl/data/icudt63b/coll", ICUResourceBundle.ICU_DATA_CLASS_LOADER);
            return result;
        }
        ULocale[] result2 = service.getAvailableULocales();
        return result2;
    }

    @Override // com.ibm.icu.text.Collator.ServiceShim
    String getDisplayName(ULocale objectLocale, ULocale displayLocale) {
        String id = objectLocale.getName();
        return service.getDisplayName(id, displayLocale);
    }

    /* loaded from: classes.dex */
    private static class CService extends ICULocaleService {
        CService() {
            super("Collator");
            registerFactory(new ICULocaleService.ICUResourceBundleFactory() { // from class: com.ibm.icu.text.CollatorServiceShim.CService.1CollatorFactory
                protected Object handleCreate(ULocale uloc, int kind, ICUService srvc) {
                    return CollatorServiceShim.makeInstance(uloc);
                }
            });
            markDefault();
        }

        public String validateFallbackLocale() {
            return "";
        }

        protected Object handleDefault(ICUService.Key key, String[] actualIDReturn) {
            if (actualIDReturn != null) {
                actualIDReturn[0] = "root";
            }
            try {
                return CollatorServiceShim.makeInstance(ULocale.ROOT);
            } catch (MissingResourceException e) {
                return null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Collator makeInstance(ULocale desiredLocale) {
        Output<ULocale> validLocale = new Output<>(ULocale.ROOT);
        CollationTailoring t = CollationLoader.loadTailoring(desiredLocale, validLocale);
        return new RuleBasedCollator(t, (ULocale) validLocale.value);
    }
}
