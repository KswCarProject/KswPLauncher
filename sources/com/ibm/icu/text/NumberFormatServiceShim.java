package com.ibm.icu.text;

import com.ibm.icu.impl.ICULocaleService;
import com.ibm.icu.impl.ICUResourceBundle;
import com.ibm.icu.impl.ICUService;
import com.ibm.icu.text.NumberFormat;
import com.ibm.icu.util.Currency;
import com.ibm.icu.util.ULocale;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Set;

/* loaded from: classes.dex */
class NumberFormatServiceShim extends NumberFormat.NumberFormatShim {
    private static ICULocaleService service = new NFService();

    NumberFormatServiceShim() {
    }

    @Override // com.ibm.icu.text.NumberFormat.NumberFormatShim
    Locale[] getAvailableLocales() {
        if (service.isDefault()) {
            return ICUResourceBundle.getAvailableLocales();
        }
        return service.getAvailableLocales();
    }

    @Override // com.ibm.icu.text.NumberFormat.NumberFormatShim
    ULocale[] getAvailableULocales() {
        if (service.isDefault()) {
            return ICUResourceBundle.getAvailableULocales();
        }
        return service.getAvailableULocales();
    }

    /* loaded from: classes.dex */
    private static final class NFFactory extends ICULocaleService.LocaleKeyFactory {
        private NumberFormat.NumberFormatFactory delegate;

        NFFactory(NumberFormat.NumberFormatFactory delegate) {
            super(delegate.visible());
            this.delegate = delegate;
        }

        public Object create(ICUService.Key key, ICUService srvc) {
            if (handlesKey(key) && (key instanceof ICULocaleService.LocaleKey)) {
                ICULocaleService.LocaleKey lkey = (ICULocaleService.LocaleKey) key;
                Object result = this.delegate.createFormat(lkey.canonicalLocale(), lkey.kind());
                if (result == null) {
                    return srvc.getKey(key, (String[]) null, this);
                }
                return result;
            }
            return null;
        }

        protected Set<String> getSupportedIDs() {
            return this.delegate.getSupportedLocaleNames();
        }
    }

    @Override // com.ibm.icu.text.NumberFormat.NumberFormatShim
    Object registerFactory(NumberFormat.NumberFormatFactory factory) {
        return service.registerFactory(new NFFactory(factory));
    }

    @Override // com.ibm.icu.text.NumberFormat.NumberFormatShim
    boolean unregister(Object registryKey) {
        return service.unregisterFactory((ICUService.Factory) registryKey);
    }

    @Override // com.ibm.icu.text.NumberFormat.NumberFormatShim
    NumberFormat createInstance(ULocale desiredLocale, int choice) {
        ULocale[] actualLoc = new ULocale[1];
        NumberFormat fmt = (NumberFormat) service.get(desiredLocale, choice, actualLoc);
        if (fmt == null) {
            throw new MissingResourceException("Unable to construct NumberFormat", "", "");
        }
        NumberFormat fmt2 = (NumberFormat) fmt.clone();
        if (choice == 1 || choice == 5 || choice == 6) {
            fmt2.setCurrency(Currency.getInstance(desiredLocale));
        }
        ULocale uloc = actualLoc[0];
        fmt2.setLocale(uloc, uloc);
        return fmt2;
    }

    /* loaded from: classes.dex */
    private static class NFService extends ICULocaleService {
        NFService() {
            super("NumberFormat");
            registerFactory(new ICULocaleService.ICUResourceBundleFactory() { // from class: com.ibm.icu.text.NumberFormatServiceShim.NFService.1RBNumberFormatFactory
                protected Object handleCreate(ULocale loc, int kind, ICUService srvc) {
                    return NumberFormat.createInstance(loc, kind);
                }
            });
            markDefault();
        }
    }
}
