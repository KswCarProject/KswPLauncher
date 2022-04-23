package android.support.v4.os;

import android.os.Build;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;

final class LocaleListHelper {
    private static final Locale EN_LATN = LocaleHelper.forLanguageTag("en-Latn");
    private static final Locale LOCALE_AR_XB = new Locale("ar", "XB");
    private static final Locale LOCALE_EN_XA = new Locale("en", "XA");
    private static final int NUM_PSEUDO_LOCALES = 2;
    private static final String STRING_AR_XB = "ar-XB";
    private static final String STRING_EN_XA = "en-XA";
    private static LocaleListHelper sDefaultAdjustedLocaleList = null;
    private static LocaleListHelper sDefaultLocaleList = null;
    private static final Locale[] sEmptyList = new Locale[0];
    private static final LocaleListHelper sEmptyLocaleList = new LocaleListHelper(new Locale[0]);
    private static Locale sLastDefaultLocale = null;
    private static LocaleListHelper sLastExplicitlySetLocaleList = null;
    private static final Object sLock = new Object();
    private final Locale[] mList;
    private final String mStringRepresentation;

    /* access modifiers changed from: package-private */
    public Locale get(int index) {
        if (index >= 0) {
            Locale[] localeArr = this.mList;
            if (index < localeArr.length) {
                return localeArr[index];
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean isEmpty() {
        return this.mList.length == 0;
    }

    /* access modifiers changed from: package-private */
    public int size() {
        return this.mList.length;
    }

    /* access modifiers changed from: package-private */
    public int indexOf(Locale locale) {
        int i = 0;
        while (true) {
            Locale[] localeArr = this.mList;
            if (i >= localeArr.length) {
                return -1;
            }
            if (localeArr[i].equals(locale)) {
                return i;
            }
            i++;
        }
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof LocaleListHelper)) {
            return false;
        }
        Locale[] otherList = ((LocaleListHelper) other).mList;
        if (this.mList.length != otherList.length) {
            return false;
        }
        int i = 0;
        while (true) {
            Locale[] localeArr = this.mList;
            if (i >= localeArr.length) {
                return true;
            }
            if (!localeArr[i].equals(otherList[i])) {
                return false;
            }
            i++;
        }
    }

    public int hashCode() {
        int result = 1;
        int i = 0;
        while (true) {
            Locale[] localeArr = this.mList;
            if (i >= localeArr.length) {
                return result;
            }
            result = (result * 31) + localeArr[i].hashCode();
            i++;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int i = 0;
        while (true) {
            Locale[] localeArr = this.mList;
            if (i < localeArr.length) {
                sb.append(localeArr[i]);
                if (i < this.mList.length - 1) {
                    sb.append(',');
                }
                i++;
            } else {
                sb.append("]");
                return sb.toString();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public String toLanguageTags() {
        return this.mStringRepresentation;
    }

    LocaleListHelper(Locale... list) {
        if (list.length == 0) {
            this.mList = sEmptyList;
            this.mStringRepresentation = "";
            return;
        }
        Locale[] localeList = new Locale[list.length];
        HashSet<Locale> seenLocales = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < list.length) {
            Locale l = list[i];
            if (l == null) {
                throw new NullPointerException("list[" + i + "] is null");
            } else if (!seenLocales.contains(l)) {
                Locale localeClone = (Locale) l.clone();
                localeList[i] = localeClone;
                sb.append(LocaleHelper.toLanguageTag(localeClone));
                if (i < list.length - 1) {
                    sb.append(',');
                }
                seenLocales.add(localeClone);
                i++;
            } else {
                throw new IllegalArgumentException("list[" + i + "] is a repetition");
            }
        }
        this.mList = localeList;
        this.mStringRepresentation = sb.toString();
    }

    LocaleListHelper(Locale topLocale, LocaleListHelper otherLocales) {
        if (topLocale != null) {
            int inputLength = otherLocales == null ? 0 : otherLocales.mList.length;
            int topLocaleIndex = -1;
            int i = 0;
            while (true) {
                if (i >= inputLength) {
                    break;
                } else if (topLocale.equals(otherLocales.mList[i])) {
                    topLocaleIndex = i;
                    break;
                } else {
                    i++;
                }
            }
            int outputLength = (topLocaleIndex == -1 ? 1 : 0) + inputLength;
            Locale[] localeList = new Locale[outputLength];
            localeList[0] = (Locale) topLocale.clone();
            if (topLocaleIndex == -1) {
                for (int i2 = 0; i2 < inputLength; i2++) {
                    localeList[i2 + 1] = (Locale) otherLocales.mList[i2].clone();
                }
            } else {
                for (int i3 = 0; i3 < topLocaleIndex; i3++) {
                    localeList[i3 + 1] = (Locale) otherLocales.mList[i3].clone();
                }
                for (int i4 = topLocaleIndex + 1; i4 < inputLength; i4++) {
                    localeList[i4] = (Locale) otherLocales.mList[i4].clone();
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int i5 = 0; i5 < outputLength; i5++) {
                sb.append(LocaleHelper.toLanguageTag(localeList[i5]));
                if (i5 < outputLength - 1) {
                    sb.append(',');
                }
            }
            this.mList = localeList;
            this.mStringRepresentation = sb.toString();
            return;
        }
        throw new NullPointerException("topLocale is null");
    }

    static LocaleListHelper getEmptyLocaleList() {
        return sEmptyLocaleList;
    }

    static LocaleListHelper forLanguageTags(String list) {
        if (list == null || list.isEmpty()) {
            return getEmptyLocaleList();
        }
        String[] tags = list.split(",", -1);
        Locale[] localeArray = new Locale[tags.length];
        for (int i = 0; i < localeArray.length; i++) {
            localeArray[i] = LocaleHelper.forLanguageTag(tags[i]);
        }
        return new LocaleListHelper(localeArray);
    }

    private static String getLikelyScript(Locale locale) {
        if (Build.VERSION.SDK_INT < 21) {
            return "";
        }
        String script = locale.getScript();
        if (!script.isEmpty()) {
            return script;
        }
        return "";
    }

    private static boolean isPseudoLocale(String locale) {
        return STRING_EN_XA.equals(locale) || STRING_AR_XB.equals(locale);
    }

    private static boolean isPseudoLocale(Locale locale) {
        return LOCALE_EN_XA.equals(locale) || LOCALE_AR_XB.equals(locale);
    }

    private static int matchScore(Locale supported, Locale desired) {
        if (supported.equals(desired)) {
            return 1;
        }
        if (!supported.getLanguage().equals(desired.getLanguage()) || isPseudoLocale(supported) || isPseudoLocale(desired)) {
            return 0;
        }
        String supportedScr = getLikelyScript(supported);
        if (!supportedScr.isEmpty()) {
            return supportedScr.equals(getLikelyScript(desired)) ? 1 : 0;
        }
        String supportedRegion = supported.getCountry();
        if (supportedRegion.isEmpty() || supportedRegion.equals(desired.getCountry())) {
            return 1;
        }
        return 0;
    }

    private int findFirstMatchIndex(Locale supportedLocale) {
        int idx = 0;
        while (true) {
            Locale[] localeArr = this.mList;
            if (idx >= localeArr.length) {
                return Integer.MAX_VALUE;
            }
            if (matchScore(supportedLocale, localeArr[idx]) > 0) {
                return idx;
            }
            idx++;
        }
    }

    private int computeFirstMatchIndex(Collection<String> supportedLocales, boolean assumeEnglishIsSupported) {
        Locale[] localeArr = this.mList;
        if (localeArr.length == 1) {
            return 0;
        }
        if (localeArr.length == 0) {
            return -1;
        }
        int bestIndex = Integer.MAX_VALUE;
        if (assumeEnglishIsSupported) {
            int idx = findFirstMatchIndex(EN_LATN);
            if (idx == 0) {
                return 0;
            }
            if (idx < Integer.MAX_VALUE) {
                bestIndex = idx;
            }
        }
        for (String languageTag : supportedLocales) {
            int idx2 = findFirstMatchIndex(LocaleHelper.forLanguageTag(languageTag));
            if (idx2 == 0) {
                return 0;
            }
            if (idx2 < bestIndex) {
                bestIndex = idx2;
            }
        }
        if (bestIndex == Integer.MAX_VALUE) {
            return 0;
        }
        return bestIndex;
    }

    private Locale computeFirstMatch(Collection<String> supportedLocales, boolean assumeEnglishIsSupported) {
        int bestIndex = computeFirstMatchIndex(supportedLocales, assumeEnglishIsSupported);
        if (bestIndex == -1) {
            return null;
        }
        return this.mList[bestIndex];
    }

    /* access modifiers changed from: package-private */
    public Locale getFirstMatch(String[] supportedLocales) {
        return computeFirstMatch(Arrays.asList(supportedLocales), false);
    }

    /* access modifiers changed from: package-private */
    public int getFirstMatchIndex(String[] supportedLocales) {
        return computeFirstMatchIndex(Arrays.asList(supportedLocales), false);
    }

    /* access modifiers changed from: package-private */
    public Locale getFirstMatchWithEnglishSupported(String[] supportedLocales) {
        return computeFirstMatch(Arrays.asList(supportedLocales), true);
    }

    /* access modifiers changed from: package-private */
    public int getFirstMatchIndexWithEnglishSupported(Collection<String> supportedLocales) {
        return computeFirstMatchIndex(supportedLocales, true);
    }

    /* access modifiers changed from: package-private */
    public int getFirstMatchIndexWithEnglishSupported(String[] supportedLocales) {
        return getFirstMatchIndexWithEnglishSupported((Collection<String>) Arrays.asList(supportedLocales));
    }

    static boolean isPseudoLocalesOnly(String[] supportedLocales) {
        if (supportedLocales == null) {
            return true;
        }
        if (supportedLocales.length > 3) {
            return false;
        }
        for (String locale : supportedLocales) {
            if (!locale.isEmpty() && !isPseudoLocale(locale)) {
                return false;
            }
        }
        return true;
    }

    static LocaleListHelper getDefault() {
        Locale defaultLocale = Locale.getDefault();
        synchronized (sLock) {
            if (!defaultLocale.equals(sLastDefaultLocale)) {
                sLastDefaultLocale = defaultLocale;
                LocaleListHelper localeListHelper = sDefaultLocaleList;
                if (localeListHelper == null || !defaultLocale.equals(localeListHelper.get(0))) {
                    LocaleListHelper localeListHelper2 = new LocaleListHelper(defaultLocale, sLastExplicitlySetLocaleList);
                    sDefaultLocaleList = localeListHelper2;
                    sDefaultAdjustedLocaleList = localeListHelper2;
                } else {
                    LocaleListHelper localeListHelper3 = sDefaultLocaleList;
                    return localeListHelper3;
                }
            }
            LocaleListHelper localeListHelper4 = sDefaultLocaleList;
            return localeListHelper4;
        }
    }

    static LocaleListHelper getAdjustedDefault() {
        LocaleListHelper localeListHelper;
        getDefault();
        synchronized (sLock) {
            localeListHelper = sDefaultAdjustedLocaleList;
        }
        return localeListHelper;
    }

    static void setDefault(LocaleListHelper locales) {
        setDefault(locales, 0);
    }

    static void setDefault(LocaleListHelper locales, int localeIndex) {
        if (locales == null) {
            throw new NullPointerException("locales is null");
        } else if (!locales.isEmpty()) {
            synchronized (sLock) {
                Locale locale = locales.get(localeIndex);
                sLastDefaultLocale = locale;
                Locale.setDefault(locale);
                sLastExplicitlySetLocaleList = locales;
                sDefaultLocaleList = locales;
                if (localeIndex == 0) {
                    sDefaultAdjustedLocaleList = locales;
                } else {
                    sDefaultAdjustedLocaleList = new LocaleListHelper(sLastDefaultLocale, sDefaultLocaleList);
                }
            }
        } else {
            throw new IllegalArgumentException("locales is empty");
        }
    }
}
