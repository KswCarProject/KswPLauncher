package com.ibm.icu.text;

import com.ibm.icu.impl.CacheBase;
import com.ibm.icu.impl.ICUResourceBundle;
import com.ibm.icu.impl.SoftCache;
import com.ibm.icu.util.ULocale;
import com.ibm.icu.util.UResourceBundle;
import com.ibm.icu.util.UResourceBundleIterator;
import java.util.ArrayList;
import java.util.Locale;
import java.util.MissingResourceException;

/* loaded from: classes.dex */
public class NumberingSystem {
    private static final String[] OTHER_NS_KEYWORDS = {"native", "traditional", "finance"};
    public static final NumberingSystem LATIN = lookupInstanceByName("latn");
    private static CacheBase<String, NumberingSystem, LocaleLookupData> cachedLocaleData = new SoftCache<String, NumberingSystem, LocaleLookupData>() { // from class: com.ibm.icu.text.NumberingSystem.1
        /* JADX INFO: Access modifiers changed from: protected */
        public NumberingSystem createInstance(String key, LocaleLookupData localeLookupData) {
            return NumberingSystem.lookupInstanceByLocale(localeLookupData);
        }
    };
    private static CacheBase<String, NumberingSystem, Void> cachedStringData = new SoftCache<String, NumberingSystem, Void>() { // from class: com.ibm.icu.text.NumberingSystem.2
        /* JADX INFO: Access modifiers changed from: protected */
        public NumberingSystem createInstance(String key, Void unused) {
            return NumberingSystem.lookupInstanceByName(key);
        }
    };
    private int radix = 10;
    private boolean algorithmic = false;
    private String desc = "0123456789";
    private String name = "latn";

    public static NumberingSystem getInstance(int radix_in, boolean isAlgorithmic_in, String desc_in) {
        return getInstance(null, radix_in, isAlgorithmic_in, desc_in);
    }

    private static NumberingSystem getInstance(String name_in, int radix_in, boolean isAlgorithmic_in, String desc_in) {
        if (radix_in < 2) {
            throw new IllegalArgumentException("Invalid radix for numbering system");
        }
        if (!isAlgorithmic_in && (desc_in.codePointCount(0, desc_in.length()) != radix_in || !isValidDigitString(desc_in))) {
            throw new IllegalArgumentException("Invalid digit string for numbering system");
        }
        NumberingSystem ns = new NumberingSystem();
        ns.radix = radix_in;
        ns.algorithmic = isAlgorithmic_in;
        ns.desc = desc_in;
        ns.name = name_in;
        return ns;
    }

    public static NumberingSystem getInstance(Locale inLocale) {
        return getInstance(ULocale.forLocale(inLocale));
    }

    public static NumberingSystem getInstance(ULocale locale) {
        boolean nsResolved = true;
        String numbersKeyword = locale.getKeywordValue("numbers");
        if (numbersKeyword != null) {
            String[] strArr = OTHER_NS_KEYWORDS;
            int length = strArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                String keyword = strArr[i];
                if (!numbersKeyword.equals(keyword)) {
                    i++;
                } else {
                    nsResolved = false;
                    break;
                }
            }
        } else {
            numbersKeyword = "default";
            nsResolved = false;
        }
        if (nsResolved) {
            NumberingSystem ns = getInstanceByName(numbersKeyword);
            if (ns != null) {
                return ns;
            }
            numbersKeyword = "default";
        }
        String baseName = locale.getBaseName();
        String key = baseName + "@numbers=" + numbersKeyword;
        LocaleLookupData localeLookupData = new LocaleLookupData(locale, numbersKeyword);
        return (NumberingSystem) cachedLocaleData.getInstance(key, localeLookupData);
    }

    /* loaded from: classes.dex */
    private static class LocaleLookupData {
        public final ULocale locale;
        public final String numbersKeyword;

        LocaleLookupData(ULocale locale, String numbersKeyword) {
            this.locale = locale;
            this.numbersKeyword = numbersKeyword;
        }
    }

    static NumberingSystem lookupInstanceByLocale(LocaleLookupData localeLookupData) {
        ULocale locale = localeLookupData.locale;
        try {
            ICUResourceBundle rb = UResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudt63b", locale);
            ICUResourceBundle rb2 = rb.getWithFallback("NumberElements");
            String numbersKeyword = localeLookupData.numbersKeyword;
            String resolvedNumberingSystem = null;
            while (true) {
                try {
                    resolvedNumberingSystem = rb2.getStringWithFallback(numbersKeyword);
                    break;
                } catch (MissingResourceException e) {
                    if (numbersKeyword.equals("native") || numbersKeyword.equals("finance")) {
                        numbersKeyword = "default";
                    } else if (!numbersKeyword.equals("traditional")) {
                        break;
                    } else {
                        numbersKeyword = "native";
                    }
                }
            }
            NumberingSystem ns = null;
            if (resolvedNumberingSystem != null) {
                ns = getInstanceByName(resolvedNumberingSystem);
            }
            if (ns == null) {
                NumberingSystem ns2 = new NumberingSystem();
                return ns2;
            }
            return ns;
        } catch (MissingResourceException e2) {
            return new NumberingSystem();
        }
    }

    public static NumberingSystem getInstance() {
        return getInstance(ULocale.getDefault(ULocale.Category.FORMAT));
    }

    public static NumberingSystem getInstanceByName(String name) {
        return (NumberingSystem) cachedStringData.getInstance(name, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static NumberingSystem lookupInstanceByName(String name) {
        try {
            UResourceBundle numberingSystemsInfo = UResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudt63b", "numberingSystems");
            UResourceBundle nsCurrent = numberingSystemsInfo.get("numberingSystems");
            UResourceBundle nsTop = nsCurrent.get(name);
            String description = nsTop.getString("desc");
            UResourceBundle nsRadixBundle = nsTop.get("radix");
            UResourceBundle nsAlgBundle = nsTop.get("algorithmic");
            int radix = nsRadixBundle.getInt();
            int algorithmic = nsAlgBundle.getInt();
            boolean isAlgorithmic = algorithmic == 1;
            return getInstance(name, radix, isAlgorithmic, description);
        } catch (MissingResourceException e) {
            return null;
        }
    }

    public static String[] getAvailableNames() {
        UResourceBundle numberingSystemsInfo = UResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudt63b", "numberingSystems");
        UResourceBundle nsCurrent = numberingSystemsInfo.get("numberingSystems");
        ArrayList<String> output = new ArrayList<>();
        UResourceBundleIterator it = nsCurrent.getIterator();
        while (it.hasNext()) {
            UResourceBundle temp = it.next();
            String nsName = temp.getKey();
            output.add(nsName);
        }
        return (String[]) output.toArray(new String[output.size()]);
    }

    public static boolean isValidDigitString(String str) {
        int numCodepoints = str.codePointCount(0, str.length());
        return numCodepoints == 10;
    }

    public int getRadix() {
        return this.radix;
    }

    public String getDescription() {
        return this.desc;
    }

    public String getName() {
        return this.name;
    }

    public boolean isAlgorithmic() {
        return this.algorithmic;
    }
}
