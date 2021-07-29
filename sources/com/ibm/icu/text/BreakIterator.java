package com.ibm.icu.text;

import com.ibm.icu.impl.CSCharacterIterator;
import com.ibm.icu.impl.CacheValue;
import com.ibm.icu.impl.ICUDebug;
import com.ibm.icu.util.ICUCloneNotSupportedException;
import com.ibm.icu.util.ULocale;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Locale;
import java.util.MissingResourceException;

public abstract class BreakIterator implements Cloneable {
    private static final boolean DEBUG = ICUDebug.enabled("breakiterator");
    public static final int DONE = -1;
    public static final int KIND_CHARACTER = 0;
    private static final int KIND_COUNT = 5;
    public static final int KIND_LINE = 2;
    public static final int KIND_SENTENCE = 3;
    public static final int KIND_TITLE = 4;
    public static final int KIND_WORD = 1;
    public static final int WORD_IDEO = 400;
    public static final int WORD_IDEO_LIMIT = 500;
    public static final int WORD_KANA = 300;
    public static final int WORD_KANA_LIMIT = 400;
    public static final int WORD_LETTER = 200;
    public static final int WORD_LETTER_LIMIT = 300;
    public static final int WORD_NONE = 0;
    public static final int WORD_NONE_LIMIT = 100;
    public static final int WORD_NUMBER = 100;
    public static final int WORD_NUMBER_LIMIT = 200;
    private static final CacheValue<?>[] iterCache = new CacheValue[5];
    private static BreakIteratorServiceShim shim;
    private ULocale actualLocale;
    private ULocale validLocale;

    public abstract int current();

    public abstract int first();

    public abstract int following(int i);

    public abstract CharacterIterator getText();

    public abstract int last();

    public abstract int next();

    public abstract int next(int i);

    public abstract int previous();

    public abstract void setText(CharacterIterator characterIterator);

    protected BreakIterator() {
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new ICUCloneNotSupportedException(e);
        }
    }

    public int preceding(int offset) {
        int pos = following(offset);
        while (pos >= offset && pos != -1) {
            pos = previous();
        }
        return pos;
    }

    public boolean isBoundary(int offset) {
        if (offset == 0 || following(offset - 1) == offset) {
            return true;
        }
        return false;
    }

    public int getRuleStatus() {
        return 0;
    }

    public int getRuleStatusVec(int[] fillInArray) {
        if (fillInArray == null || fillInArray.length <= 0) {
            return 1;
        }
        fillInArray[0] = 0;
        return 1;
    }

    public void setText(String newText) {
        setText((CharacterIterator) new StringCharacterIterator(newText));
    }

    public void setText(CharSequence newText) {
        setText((CharacterIterator) new CSCharacterIterator(newText));
    }

    public static BreakIterator getWordInstance() {
        return getWordInstance(ULocale.getDefault());
    }

    public static BreakIterator getWordInstance(Locale where) {
        return getBreakInstance(ULocale.forLocale(where), 1);
    }

    public static BreakIterator getWordInstance(ULocale where) {
        return getBreakInstance(where, 1);
    }

    public static BreakIterator getLineInstance() {
        return getLineInstance(ULocale.getDefault());
    }

    public static BreakIterator getLineInstance(Locale where) {
        return getBreakInstance(ULocale.forLocale(where), 2);
    }

    public static BreakIterator getLineInstance(ULocale where) {
        return getBreakInstance(where, 2);
    }

    public static BreakIterator getCharacterInstance() {
        return getCharacterInstance(ULocale.getDefault());
    }

    public static BreakIterator getCharacterInstance(Locale where) {
        return getBreakInstance(ULocale.forLocale(where), 0);
    }

    public static BreakIterator getCharacterInstance(ULocale where) {
        return getBreakInstance(where, 0);
    }

    public static BreakIterator getSentenceInstance() {
        return getSentenceInstance(ULocale.getDefault());
    }

    public static BreakIterator getSentenceInstance(Locale where) {
        return getBreakInstance(ULocale.forLocale(where), 3);
    }

    public static BreakIterator getSentenceInstance(ULocale where) {
        return getBreakInstance(where, 3);
    }

    public static BreakIterator getTitleInstance() {
        return getTitleInstance(ULocale.getDefault());
    }

    public static BreakIterator getTitleInstance(Locale where) {
        return getBreakInstance(ULocale.forLocale(where), 4);
    }

    public static BreakIterator getTitleInstance(ULocale where) {
        return getBreakInstance(where, 4);
    }

    public static Object registerInstance(BreakIterator iter, Locale locale, int kind) {
        return registerInstance(iter, ULocale.forLocale(locale), kind);
    }

    public static Object registerInstance(BreakIterator iter, ULocale locale, int kind) {
        BreakIteratorCache cache;
        CacheValue<?>[] cacheValueArr = iterCache;
        if (!(cacheValueArr[kind] == null || (cache = (BreakIteratorCache) cacheValueArr[kind].get()) == null || !cache.getLocale().equals(locale))) {
            cacheValueArr[kind] = null;
        }
        return getShim().registerInstance(iter, locale, kind);
    }

    public static boolean unregister(Object key) {
        if (key == null) {
            throw new IllegalArgumentException("registry key must not be null");
        } else if (shim == null) {
            return false;
        } else {
            for (int kind = 0; kind < 5; kind++) {
                iterCache[kind] = null;
            }
            return shim.unregister(key);
        }
    }

    @Deprecated
    public static BreakIterator getBreakInstance(ULocale where, int kind) {
        BreakIteratorCache cache;
        if (where != null) {
            CacheValue<?>[] cacheValueArr = iterCache;
            if (cacheValueArr[kind] != null && (cache = (BreakIteratorCache) cacheValueArr[kind].get()) != null && cache.getLocale().equals(where)) {
                return cache.createBreakInstance();
            }
            BreakIterator result = getShim().createBreakIterator(where, kind);
            cacheValueArr[kind] = CacheValue.getInstance(new BreakIteratorCache(where, result));
            return result;
        }
        throw new NullPointerException("Specified locale is null");
    }

    public static synchronized Locale[] getAvailableLocales() {
        Locale[] availableLocales;
        synchronized (BreakIterator.class) {
            availableLocales = getShim().getAvailableLocales();
        }
        return availableLocales;
    }

    public static synchronized ULocale[] getAvailableULocales() {
        ULocale[] availableULocales;
        synchronized (BreakIterator.class) {
            availableULocales = getShim().getAvailableULocales();
        }
        return availableULocales;
    }

    private static final class BreakIteratorCache {
        private BreakIterator iter;
        private ULocale where;

        BreakIteratorCache(ULocale where2, BreakIterator iter2) {
            this.where = where2;
            this.iter = (BreakIterator) iter2.clone();
        }

        /* access modifiers changed from: package-private */
        public ULocale getLocale() {
            return this.where;
        }

        /* access modifiers changed from: package-private */
        public BreakIterator createBreakInstance() {
            return (BreakIterator) this.iter.clone();
        }
    }

    static abstract class BreakIteratorServiceShim {
        public abstract BreakIterator createBreakIterator(ULocale uLocale, int i);

        public abstract Locale[] getAvailableLocales();

        public abstract ULocale[] getAvailableULocales();

        public abstract Object registerInstance(BreakIterator breakIterator, ULocale uLocale, int i);

        public abstract boolean unregister(Object obj);

        BreakIteratorServiceShim() {
        }
    }

    private static BreakIteratorServiceShim getShim() {
        if (shim == null) {
            try {
                shim = (BreakIteratorServiceShim) Class.forName("com.ibm.icu.text.BreakIteratorFactory").newInstance();
            } catch (MissingResourceException e) {
                throw e;
            } catch (Exception e2) {
                if (DEBUG) {
                    e2.printStackTrace();
                }
                throw new RuntimeException(e2.getMessage());
            }
        }
        return shim;
    }

    public final ULocale getLocale(ULocale.Type type) {
        return type == ULocale.ACTUAL_LOCALE ? this.actualLocale : this.validLocale;
    }

    /* access modifiers changed from: package-private */
    public final void setLocale(ULocale valid, ULocale actual) {
        boolean z = true;
        boolean z2 = valid == null;
        if (actual != null) {
            z = false;
        }
        if (z2 == z) {
            this.validLocale = valid;
            this.actualLocale = actual;
            return;
        }
        throw new IllegalArgumentException();
    }
}
