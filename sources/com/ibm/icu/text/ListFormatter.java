package com.ibm.icu.text;

import com.ibm.icu.impl.ICUCache;
import com.ibm.icu.impl.ICUResourceBundle;
import com.ibm.icu.impl.SimpleCache;
import com.ibm.icu.impl.SimpleFormatterImpl;
import com.ibm.icu.util.ICUUncheckedIOException;
import com.ibm.icu.util.ULocale;
import com.ibm.icu.util.UResourceBundle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;

public final class ListFormatter {
    static Cache cache = new Cache();
    private final String end;
    private final ULocale locale;
    private final String middle;
    private final String start;
    private final String two;

    @Deprecated
    public enum Style {
        STANDARD("standard"),
        DURATION("unit"),
        DURATION_SHORT("unit-short"),
        DURATION_NARROW("unit-narrow");
        
        private final String name;

        private Style(String name2) {
            this.name = name2;
        }

        @Deprecated
        public String getName() {
            return this.name;
        }
    }

    @Deprecated
    public ListFormatter(String two2, String start2, String middle2, String end2) {
        this(compilePattern(two2, new StringBuilder()), compilePattern(start2, new StringBuilder()), compilePattern(middle2, new StringBuilder()), compilePattern(end2, new StringBuilder()), (ULocale) null);
    }

    private ListFormatter(String two2, String start2, String middle2, String end2, ULocale locale2) {
        this.two = two2;
        this.start = start2;
        this.middle = middle2;
        this.end = end2;
        this.locale = locale2;
    }

    /* access modifiers changed from: private */
    public static String compilePattern(String pattern, StringBuilder sb) {
        return SimpleFormatterImpl.compileToStringMinMaxArguments(pattern, sb, 2, 2);
    }

    public static ListFormatter getInstance(ULocale locale2) {
        return getInstance(locale2, Style.STANDARD);
    }

    public static ListFormatter getInstance(Locale locale2) {
        return getInstance(ULocale.forLocale(locale2), Style.STANDARD);
    }

    @Deprecated
    public static ListFormatter getInstance(ULocale locale2, Style style) {
        return cache.get(locale2, style.getName());
    }

    public static ListFormatter getInstance() {
        return getInstance(ULocale.getDefault(ULocale.Category.FORMAT));
    }

    public String format(Object... items) {
        return format((Collection<?>) Arrays.asList(items));
    }

    public String format(Collection<?> items) {
        return format(items, -1).toString();
    }

    /* access modifiers changed from: package-private */
    public FormattedListBuilder format(Collection<?> items, int index) {
        Iterator<?> it = items.iterator();
        int count = items.size();
        boolean z = false;
        switch (count) {
            case 0:
                return new FormattedListBuilder("", false);
            case 1:
                Object next = it.next();
                if (index == 0) {
                    z = true;
                }
                return new FormattedListBuilder(next, z);
            case 2:
                FormattedListBuilder formattedListBuilder = new FormattedListBuilder(it.next(), index == 0);
                String str = this.two;
                Object next2 = it.next();
                if (index == 1) {
                    z = true;
                }
                return formattedListBuilder.append(str, next2, z);
            default:
                FormattedListBuilder builder = new FormattedListBuilder(it.next(), index == 0);
                builder.append(this.start, it.next(), index == 1);
                int idx = 2;
                while (idx < count - 1) {
                    builder.append(this.middle, it.next(), index == idx);
                    idx++;
                }
                String str2 = this.end;
                Object next3 = it.next();
                if (index == count - 1) {
                    z = true;
                }
                return builder.append(str2, next3, z);
        }
    }

    public String getPatternForNumItems(int count) {
        if (count > 0) {
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                list.add(String.format("{%d}", new Object[]{Integer.valueOf(i)}));
            }
            return format((Collection<?>) list);
        }
        throw new IllegalArgumentException("count must be > 0");
    }

    @Deprecated
    public ULocale getLocale() {
        return this.locale;
    }

    static class FormattedListBuilder {
        private StringBuilder current;
        private int offset;

        public FormattedListBuilder(Object start, boolean recordOffset) {
            this.current = new StringBuilder(start.toString());
            this.offset = recordOffset ? 0 : -1;
        }

        public FormattedListBuilder append(String pattern, Object next, boolean recordOffset) {
            int[] offsets = (recordOffset || offsetRecorded()) ? new int[2] : null;
            StringBuilder sb = this.current;
            SimpleFormatterImpl.formatAndReplace(pattern, sb, offsets, new CharSequence[]{sb, next.toString()});
            if (offsets != null) {
                if (offsets[0] == -1 || offsets[1] == -1) {
                    throw new IllegalArgumentException("{0} or {1} missing from pattern " + pattern);
                } else if (recordOffset) {
                    this.offset = offsets[1];
                } else {
                    this.offset += offsets[0];
                }
            }
            return this;
        }

        public void appendTo(Appendable appendable) {
            try {
                appendable.append(this.current);
            } catch (IOException e) {
                throw new ICUUncheckedIOException(e);
            }
        }

        public String toString() {
            return this.current.toString();
        }

        public int getOffset() {
            return this.offset;
        }

        private boolean offsetRecorded() {
            return this.offset >= 0;
        }
    }

    private static class Cache {
        private final ICUCache<String, ListFormatter> cache;

        private Cache() {
            this.cache = new SimpleCache();
        }

        public ListFormatter get(ULocale locale, String style) {
            String key = String.format("%s:%s", new Object[]{locale.toString(), style});
            ListFormatter result = (ListFormatter) this.cache.get(key);
            if (result != null) {
                return result;
            }
            ListFormatter result2 = load(locale, style);
            this.cache.put(key, result2);
            return result2;
        }

        private static ListFormatter load(ULocale ulocale, String style) {
            ICUResourceBundle r = UResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudt63b", ulocale);
            StringBuilder sb = new StringBuilder();
            return new ListFormatter(ListFormatter.compilePattern(r.getWithFallback("listPattern/" + style + "/2").getString(), sb), ListFormatter.compilePattern(r.getWithFallback("listPattern/" + style + "/start").getString(), sb), ListFormatter.compilePattern(r.getWithFallback("listPattern/" + style + "/middle").getString(), sb), ListFormatter.compilePattern(r.getWithFallback("listPattern/" + style + "/end").getString(), sb), ulocale);
        }
    }
}
