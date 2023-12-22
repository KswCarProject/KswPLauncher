package android.support.p001v4.p003os;

import android.os.Build;
import android.os.LocaleList;
import java.util.Locale;

/* renamed from: android.support.v4.os.LocaleListCompat */
/* loaded from: classes.dex */
public final class LocaleListCompat {
    static final LocaleListInterface IMPL;
    private static final LocaleListCompat sEmptyLocaleList = new LocaleListCompat();

    static {
        if (Build.VERSION.SDK_INT >= 24) {
            IMPL = new LocaleListCompatApi24Impl();
        } else {
            IMPL = new LocaleListCompatBaseImpl();
        }
    }

    /* renamed from: android.support.v4.os.LocaleListCompat$LocaleListCompatBaseImpl */
    /* loaded from: classes.dex */
    static class LocaleListCompatBaseImpl implements LocaleListInterface {
        private LocaleListHelper mLocaleList = new LocaleListHelper(new Locale[0]);

        LocaleListCompatBaseImpl() {
        }

        @Override // android.support.p001v4.p003os.LocaleListInterface
        public void setLocaleList(Locale... list) {
            this.mLocaleList = new LocaleListHelper(list);
        }

        @Override // android.support.p001v4.p003os.LocaleListInterface
        public Object getLocaleList() {
            return this.mLocaleList;
        }

        @Override // android.support.p001v4.p003os.LocaleListInterface
        public Locale get(int index) {
            return this.mLocaleList.get(index);
        }

        @Override // android.support.p001v4.p003os.LocaleListInterface
        public boolean isEmpty() {
            return this.mLocaleList.isEmpty();
        }

        @Override // android.support.p001v4.p003os.LocaleListInterface
        public int size() {
            return this.mLocaleList.size();
        }

        @Override // android.support.p001v4.p003os.LocaleListInterface
        public int indexOf(Locale locale) {
            return this.mLocaleList.indexOf(locale);
        }

        @Override // android.support.p001v4.p003os.LocaleListInterface
        public boolean equals(Object other) {
            return this.mLocaleList.equals(((LocaleListCompat) other).unwrap());
        }

        @Override // android.support.p001v4.p003os.LocaleListInterface
        public int hashCode() {
            return this.mLocaleList.hashCode();
        }

        @Override // android.support.p001v4.p003os.LocaleListInterface
        public String toString() {
            return this.mLocaleList.toString();
        }

        @Override // android.support.p001v4.p003os.LocaleListInterface
        public String toLanguageTags() {
            return this.mLocaleList.toLanguageTags();
        }

        @Override // android.support.p001v4.p003os.LocaleListInterface
        public Locale getFirstMatch(String[] supportedLocales) {
            LocaleListHelper localeListHelper = this.mLocaleList;
            if (localeListHelper != null) {
                return localeListHelper.getFirstMatch(supportedLocales);
            }
            return null;
        }
    }

    /* renamed from: android.support.v4.os.LocaleListCompat$LocaleListCompatApi24Impl */
    /* loaded from: classes.dex */
    static class LocaleListCompatApi24Impl implements LocaleListInterface {
        private LocaleList mLocaleList = new LocaleList(new Locale[0]);

        LocaleListCompatApi24Impl() {
        }

        @Override // android.support.p001v4.p003os.LocaleListInterface
        public void setLocaleList(Locale... list) {
            this.mLocaleList = new LocaleList(list);
        }

        @Override // android.support.p001v4.p003os.LocaleListInterface
        public Object getLocaleList() {
            return this.mLocaleList;
        }

        @Override // android.support.p001v4.p003os.LocaleListInterface
        public Locale get(int index) {
            return this.mLocaleList.get(index);
        }

        @Override // android.support.p001v4.p003os.LocaleListInterface
        public boolean isEmpty() {
            return this.mLocaleList.isEmpty();
        }

        @Override // android.support.p001v4.p003os.LocaleListInterface
        public int size() {
            return this.mLocaleList.size();
        }

        @Override // android.support.p001v4.p003os.LocaleListInterface
        public int indexOf(Locale locale) {
            return this.mLocaleList.indexOf(locale);
        }

        @Override // android.support.p001v4.p003os.LocaleListInterface
        public boolean equals(Object other) {
            return this.mLocaleList.equals(((LocaleListCompat) other).unwrap());
        }

        @Override // android.support.p001v4.p003os.LocaleListInterface
        public int hashCode() {
            return this.mLocaleList.hashCode();
        }

        @Override // android.support.p001v4.p003os.LocaleListInterface
        public String toString() {
            return this.mLocaleList.toString();
        }

        @Override // android.support.p001v4.p003os.LocaleListInterface
        public String toLanguageTags() {
            return this.mLocaleList.toLanguageTags();
        }

        @Override // android.support.p001v4.p003os.LocaleListInterface
        public Locale getFirstMatch(String[] supportedLocales) {
            LocaleList localeList = this.mLocaleList;
            if (localeList != null) {
                return localeList.getFirstMatch(supportedLocales);
            }
            return null;
        }
    }

    private LocaleListCompat() {
    }

    public static LocaleListCompat wrap(Object object) {
        LocaleListCompat instance = new LocaleListCompat();
        if (object instanceof LocaleList) {
            instance.setLocaleList((LocaleList) object);
        }
        return instance;
    }

    public Object unwrap() {
        return IMPL.getLocaleList();
    }

    public static LocaleListCompat create(Locale... localeList) {
        LocaleListCompat instance = new LocaleListCompat();
        instance.setLocaleListArray(localeList);
        return instance;
    }

    public Locale get(int index) {
        return IMPL.get(index);
    }

    public boolean isEmpty() {
        return IMPL.isEmpty();
    }

    public int size() {
        return IMPL.size();
    }

    public int indexOf(Locale locale) {
        return IMPL.indexOf(locale);
    }

    public String toLanguageTags() {
        return IMPL.toLanguageTags();
    }

    public Locale getFirstMatch(String[] supportedLocales) {
        return IMPL.getFirstMatch(supportedLocales);
    }

    public static LocaleListCompat getEmptyLocaleList() {
        return sEmptyLocaleList;
    }

    public static LocaleListCompat forLanguageTags(String list) {
        Locale forLanguageTag;
        if (list == null || list.isEmpty()) {
            return getEmptyLocaleList();
        }
        String[] tags = list.split(",", -1);
        Locale[] localeArray = new Locale[tags.length];
        for (int i = 0; i < localeArray.length; i++) {
            if (Build.VERSION.SDK_INT >= 21) {
                forLanguageTag = Locale.forLanguageTag(tags[i]);
            } else {
                forLanguageTag = LocaleHelper.forLanguageTag(tags[i]);
            }
            localeArray[i] = forLanguageTag;
        }
        LocaleListCompat instance = new LocaleListCompat();
        instance.setLocaleListArray(localeArray);
        return instance;
    }

    public static LocaleListCompat getAdjustedDefault() {
        return Build.VERSION.SDK_INT >= 24 ? wrap(LocaleList.getAdjustedDefault()) : create(Locale.getDefault());
    }

    public static LocaleListCompat getDefault() {
        return Build.VERSION.SDK_INT >= 24 ? wrap(LocaleList.getDefault()) : create(Locale.getDefault());
    }

    public boolean equals(Object other) {
        return IMPL.equals(other);
    }

    public int hashCode() {
        return IMPL.hashCode();
    }

    public String toString() {
        return IMPL.toString();
    }

    private void setLocaleList(LocaleList localeList) {
        int localeListSize = localeList.size();
        if (localeListSize > 0) {
            Locale[] localeArrayList = new Locale[localeListSize];
            for (int i = 0; i < localeListSize; i++) {
                localeArrayList[i] = localeList.get(i);
            }
            IMPL.setLocaleList(localeArrayList);
        }
    }

    private void setLocaleListArray(Locale... localeArrayList) {
        IMPL.setLocaleList(localeArrayList);
    }
}
