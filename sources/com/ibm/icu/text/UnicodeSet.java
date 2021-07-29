package com.ibm.icu.text;

import android.support.v4.view.InputDeviceCompat;
import com.ibm.icu.impl.BMPSet;
import com.ibm.icu.impl.CharacterPropertiesImpl;
import com.ibm.icu.impl.PatternProps;
import com.ibm.icu.impl.RuleCharacterIterator;
import com.ibm.icu.impl.SortedSetRelation;
import com.ibm.icu.impl.UCaseProps;
import com.ibm.icu.impl.UPropertyAliases;
import com.ibm.icu.impl.UnicodeSetStringSpan;
import com.ibm.icu.impl.Utility;
import com.ibm.icu.lang.CharSequences;
import com.ibm.icu.lang.CharacterProperties;
import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.lang.UScript;
import com.ibm.icu.util.Freezable;
import com.ibm.icu.util.ICUUncheckedIOException;
import com.ibm.icu.util.OutputInt;
import com.ibm.icu.util.ULocale;
import com.ibm.icu.util.VersionInfo;
import com.wits.ksw.settings.BrightnessUtils;
import java.io.IOException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import java.util.TreeSet;

public class UnicodeSet extends UnicodeFilter implements Iterable<String>, Comparable<UnicodeSet>, Freezable<UnicodeSet> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int ADD_CASE_MAPPINGS = 4;
    public static final UnicodeSet ALL_CODE_POINTS = new UnicodeSet(0, 1114111).freeze();
    private static final String ANY_ID = "ANY";
    private static final String ASCII_ID = "ASCII";
    private static final String ASSIGNED = "Assigned";
    public static final int CASE = 2;
    public static final int CASE_INSENSITIVE = 2;
    public static final UnicodeSet EMPTY = new UnicodeSet().freeze();
    private static final SortedSet<String> EMPTY_STRINGS = Collections.unmodifiableSortedSet(new TreeSet());
    private static final int HIGH = 1114112;
    public static final int IGNORE_SPACE = 1;
    private static final int INITIAL_CAPACITY = 25;
    private static final int LAST0_START = 0;
    private static final int LAST1_RANGE = 1;
    private static final int LAST2_SET = 2;
    private static final int LOW = 0;
    private static final int MAX_DEPTH = 100;
    private static final int MAX_LENGTH = 1114113;
    public static final int MAX_VALUE = 1114111;
    public static final int MIN_VALUE = 0;
    private static final int MODE0_NONE = 0;
    private static final int MODE1_INBRACKET = 1;
    private static final int MODE2_OUTBRACKET = 2;
    /* access modifiers changed from: private */
    public static final VersionInfo NO_VERSION = VersionInfo.getInstance(0, 0, 0, 0);
    private static final int SETMODE0_NONE = 0;
    private static final int SETMODE1_UNICODESET = 1;
    private static final int SETMODE2_PROPERTYPAT = 2;
    private static final int SETMODE3_PREPARSED = 3;
    private static XSymbolTable XSYMBOL_TABLE = null;
    private volatile BMPSet bmpSet;
    private int[] buffer;
    /* access modifiers changed from: private */
    public int len;
    /* access modifiers changed from: private */
    public int[] list;
    private String pat;
    private int[] rangeList;
    private volatile UnicodeSetStringSpan stringSpan;
    SortedSet<String> strings;

    public enum ComparisonStyle {
        SHORTER_FIRST,
        LEXICOGRAPHIC,
        LONGER_FIRST
    }

    private interface Filter {
        boolean contains(int i);
    }

    public enum SpanCondition {
        NOT_CONTAINED,
        CONTAINED,
        SIMPLE,
        CONDITION_COUNT
    }

    public UnicodeSet() {
        this.strings = EMPTY_STRINGS;
        this.pat = null;
        int[] iArr = new int[25];
        this.list = iArr;
        iArr[0] = HIGH;
        this.len = 1;
    }

    public UnicodeSet(UnicodeSet other) {
        this.strings = EMPTY_STRINGS;
        this.pat = null;
        set(other);
    }

    public UnicodeSet(int start, int end) {
        this();
        add(start, end);
    }

    public UnicodeSet(int... pairs) {
        this.strings = EMPTY_STRINGS;
        this.pat = null;
        if ((pairs.length & 1) == 0) {
            int[] iArr = new int[(pairs.length + 1)];
            this.list = iArr;
            this.len = iArr.length;
            int last = -1;
            int limit = 0;
            while (limit < pairs.length) {
                int start = pairs[limit];
                if (last < start) {
                    int[] iArr2 = this.list;
                    int i = limit + 1;
                    iArr2[limit] = start;
                    int limit2 = pairs[i] + 1;
                    if (start < limit2) {
                        last = limit2;
                        iArr2[i] = limit2;
                        limit = i + 1;
                    } else {
                        throw new IllegalArgumentException("Must be monotonically increasing.");
                    }
                } else {
                    throw new IllegalArgumentException("Must be monotonically increasing.");
                }
            }
            this.list[limit] = HIGH;
            return;
        }
        throw new IllegalArgumentException("Must have even number of integers");
    }

    public UnicodeSet(String pattern) {
        this();
        applyPattern(pattern, (ParsePosition) null, (SymbolTable) null, 1);
    }

    public UnicodeSet(String pattern, boolean ignoreWhitespace) {
        this();
        applyPattern(pattern, (ParsePosition) null, (SymbolTable) null, ignoreWhitespace);
    }

    public UnicodeSet(String pattern, int options) {
        this();
        applyPattern(pattern, (ParsePosition) null, (SymbolTable) null, options);
    }

    public UnicodeSet(String pattern, ParsePosition pos, SymbolTable symbols) {
        this();
        applyPattern(pattern, pos, symbols, 1);
    }

    public UnicodeSet(String pattern, ParsePosition pos, SymbolTable symbols, int options) {
        this();
        applyPattern(pattern, pos, symbols, options);
    }

    public Object clone() {
        if (isFrozen()) {
            return this;
        }
        return new UnicodeSet(this);
    }

    public UnicodeSet set(int start, int end) {
        checkFrozen();
        clear();
        complement(start, end);
        return this;
    }

    public UnicodeSet set(UnicodeSet other) {
        checkFrozen();
        this.list = Arrays.copyOf(other.list, other.len);
        this.len = other.len;
        this.pat = other.pat;
        if (other.hasStrings()) {
            this.strings = new TreeSet(other.strings);
        } else {
            this.strings = EMPTY_STRINGS;
        }
        return this;
    }

    public final UnicodeSet applyPattern(String pattern) {
        checkFrozen();
        return applyPattern(pattern, (ParsePosition) null, (SymbolTable) null, 1);
    }

    public UnicodeSet applyPattern(String pattern, boolean ignoreWhitespace) {
        checkFrozen();
        return applyPattern(pattern, (ParsePosition) null, (SymbolTable) null, ignoreWhitespace);
    }

    public UnicodeSet applyPattern(String pattern, int options) {
        checkFrozen();
        return applyPattern(pattern, (ParsePosition) null, (SymbolTable) null, options);
    }

    public static boolean resemblesPattern(String pattern, int pos) {
        return (pos + 1 < pattern.length() && pattern.charAt(pos) == '[') || resemblesPropertyPattern(pattern, pos);
    }

    private static void appendCodePoint(Appendable app, int c) {
        if (c < 0 || c > 1114111) {
            throw new AssertionError();
        } else if (c <= 65535) {
            try {
                app.append((char) c);
            } catch (IOException e) {
                throw new ICUUncheckedIOException(e);
            }
        } else {
            app.append(UTF16.getLeadSurrogate(c)).append(UTF16.getTrailSurrogate(c));
        }
    }

    private static void append(Appendable app, CharSequence s) {
        try {
            app.append(s);
        } catch (IOException e) {
            throw new ICUUncheckedIOException(e);
        }
    }

    private static <T extends Appendable> T _appendToPat(T buf, String s, boolean escapeUnprintable) {
        int i = 0;
        while (i < s.length()) {
            int cp = s.codePointAt(i);
            _appendToPat(buf, cp, escapeUnprintable);
            i += Character.charCount(cp);
        }
        return buf;
    }

    /* access modifiers changed from: private */
    public static <T extends Appendable> T _appendToPat(T buf, int c, boolean escapeUnprintable) {
        if (escapeUnprintable) {
            try {
                if (Utility.isUnprintable(c) && Utility.escapeUnprintable(buf, c)) {
                    return buf;
                }
            } catch (IOException e) {
                throw new ICUUncheckedIOException(e);
            }
        }
        switch (c) {
            case 36:
            case 38:
            case 45:
            case 58:
            case 91:
            case 92:
            case 93:
            case 94:
            case 123:
            case 125:
                buf.append('\\');
                break;
            default:
                if (PatternProps.isWhiteSpace(c)) {
                    buf.append('\\');
                    break;
                }
                break;
        }
        appendCodePoint(buf, c);
        return buf;
    }

    public String toPattern(boolean escapeUnprintable) {
        String str = this.pat;
        if (str == null || escapeUnprintable) {
            return ((StringBuilder) _toPattern(new StringBuilder(), escapeUnprintable)).toString();
        }
        return str;
    }

    private <T extends Appendable> T _toPattern(T result, boolean escapeUnprintable) {
        String str = this.pat;
        if (str == null) {
            return appendNewPattern(result, escapeUnprintable, true);
        }
        if (!escapeUnprintable) {
            try {
                result.append(str);
                return result;
            } catch (IOException e) {
                throw new ICUUncheckedIOException(e);
            }
        } else {
            boolean oddNumberOfBackslashes = false;
            int i = 0;
            while (i < this.pat.length()) {
                int c = this.pat.codePointAt(i);
                i += Character.charCount(c);
                if (Utility.isUnprintable(c)) {
                    Utility.escapeUnprintable(result, c);
                    oddNumberOfBackslashes = false;
                } else if (oddNumberOfBackslashes || c != 92) {
                    if (oddNumberOfBackslashes) {
                        result.append('\\');
                    }
                    appendCodePoint(result, c);
                    oddNumberOfBackslashes = false;
                } else {
                    oddNumberOfBackslashes = true;
                }
            }
            if (oddNumberOfBackslashes) {
                result.append('\\');
            }
            return result;
        }
    }

    public StringBuffer _generatePattern(StringBuffer result, boolean escapeUnprintable) {
        return _generatePattern(result, escapeUnprintable, true);
    }

    public StringBuffer _generatePattern(StringBuffer result, boolean escapeUnprintable, boolean includeStrings) {
        return (StringBuffer) appendNewPattern(result, escapeUnprintable, includeStrings);
    }

    private <T extends Appendable> T appendNewPattern(T result, boolean escapeUnprintable, boolean includeStrings) {
        try {
            result.append('[');
            int count = getRangeCount();
            if (count > 1 && getRangeStart(0) == 0 && getRangeEnd(count - 1) == 1114111) {
                result.append('^');
                for (int i = 1; i < count; i++) {
                    int start = getRangeEnd(i - 1) + 1;
                    int end = getRangeStart(i) - 1;
                    _appendToPat(result, start, escapeUnprintable);
                    if (start != end) {
                        if (start + 1 != end) {
                            result.append('-');
                        }
                        _appendToPat(result, end, escapeUnprintable);
                    }
                }
            } else {
                for (int i2 = 0; i2 < count; i2++) {
                    int start2 = getRangeStart(i2);
                    int end2 = getRangeEnd(i2);
                    _appendToPat(result, start2, escapeUnprintable);
                    if (start2 != end2) {
                        if (start2 + 1 != end2) {
                            result.append('-');
                        }
                        _appendToPat(result, end2, escapeUnprintable);
                    }
                }
            }
            if (includeStrings && hasStrings()) {
                for (String s : this.strings) {
                    result.append('{');
                    _appendToPat(result, s, escapeUnprintable);
                    result.append('}');
                }
            }
            result.append(']');
            return result;
        } catch (IOException e) {
            throw new ICUUncheckedIOException(e);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean hasStrings() {
        return !this.strings.isEmpty();
    }

    public int size() {
        int n = 0;
        int count = getRangeCount();
        for (int i = 0; i < count; i++) {
            n += (getRangeEnd(i) - getRangeStart(i)) + 1;
        }
        return this.strings.size() + n;
    }

    public boolean isEmpty() {
        return this.len == 1 && !hasStrings();
    }

    public boolean matchesIndexValue(int v) {
        for (int i = 0; i < getRangeCount(); i++) {
            int low = getRangeStart(i);
            int high = getRangeEnd(i);
            if ((low & InputDeviceCompat.SOURCE_ANY) == (high & InputDeviceCompat.SOURCE_ANY)) {
                if ((low & 255) <= v && v <= (high & 255)) {
                    return true;
                }
            } else if ((low & 255) <= v || v <= (high & 255)) {
                return true;
            }
        }
        if (hasStrings() != 0) {
            for (String s : this.strings) {
                if ((UTF16.charAt(s, 0) & 255) == v) {
                    return true;
                }
            }
        }
        return false;
    }

    public int matches(Replaceable text, int[] offset, int limit, boolean incremental) {
        if (offset[0] != limit) {
            if (hasStrings()) {
                boolean forward = offset[0] < limit;
                char firstChar = text.charAt(offset[0]);
                int highWaterLength = 0;
                for (String trial : this.strings) {
                    char c = trial.charAt(forward ? 0 : trial.length() - 1);
                    if (!forward || c <= firstChar) {
                        if (c == firstChar) {
                            int length = matchRest(text, offset[0], limit, trial);
                            if (incremental) {
                                if (length == (forward ? limit - offset[0] : offset[0] - limit)) {
                                    return 1;
                                }
                            }
                            if (length == trial.length()) {
                                if (length > highWaterLength) {
                                    highWaterLength = length;
                                }
                                if (forward && length < highWaterLength) {
                                    break;
                                }
                            } else {
                                continue;
                            }
                        }
                    } else {
                        break;
                    }
                }
                if (highWaterLength != 0) {
                    offset[0] = offset[0] + (forward ? highWaterLength : -highWaterLength);
                    return 2;
                }
            }
            return super.matches(text, offset, limit, incremental);
        } else if (!contains(65535)) {
            return 0;
        } else {
            if (incremental) {
                return 1;
            }
            return 2;
        }
    }

    private static int matchRest(Replaceable text, int start, int limit, String s) {
        int maxLen;
        int slen = s.length();
        if (start < limit) {
            maxLen = limit - start;
            if (maxLen > slen) {
                maxLen = slen;
            }
            for (int i = 1; i < maxLen; i++) {
                if (text.charAt(start + i) != s.charAt(i)) {
                    return 0;
                }
            }
        } else {
            int maxLen2 = start - limit;
            if (maxLen2 > slen) {
                maxLen2 = slen;
            }
            int slen2 = slen - 1;
            for (int i2 = 1; i2 < maxLen; i2++) {
                if (text.charAt(start - i2) != s.charAt(slen2 - i2)) {
                    return 0;
                }
            }
        }
        return maxLen;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int matchesAt(java.lang.CharSequence r7, int r8) {
        /*
            r6 = this;
            r0 = -1
            boolean r1 = r6.hasStrings()
            if (r1 == 0) goto L_0x0042
            char r1 = r7.charAt(r8)
            r2 = 0
            java.util.SortedSet<java.lang.String> r3 = r6.strings
            java.util.Iterator r3 = r3.iterator()
        L_0x0012:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x002b
            java.lang.Object r4 = r3.next()
            r2 = r4
            java.lang.String r2 = (java.lang.String) r2
            r4 = 0
            char r4 = r2.charAt(r4)
            if (r4 >= r1) goto L_0x0027
            goto L_0x0012
        L_0x0027:
            if (r4 <= r1) goto L_0x002a
            goto L_0x0042
        L_0x002a:
            goto L_0x0012
        L_0x002b:
            int r4 = matchesAt(r7, r8, r2)
            if (r0 <= r4) goto L_0x0032
            goto L_0x0042
        L_0x0032:
            r0 = r4
            boolean r5 = r3.hasNext()
            if (r5 != 0) goto L_0x003a
            goto L_0x0042
        L_0x003a:
            java.lang.Object r5 = r3.next()
            r2 = r5
            java.lang.String r2 = (java.lang.String) r2
            goto L_0x002b
        L_0x0042:
            r1 = 2
            if (r0 >= r1) goto L_0x0053
            int r1 = com.ibm.icu.text.UTF16.charAt((java.lang.CharSequence) r7, (int) r8)
            boolean r2 = r6.contains((int) r1)
            if (r2 == 0) goto L_0x0053
            int r0 = com.ibm.icu.text.UTF16.getCharCount(r1)
        L_0x0053:
            int r1 = r8 + r0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.UnicodeSet.matchesAt(java.lang.CharSequence, int):int");
    }

    private static int matchesAt(CharSequence text, int offsetInText, CharSequence substring) {
        int len2 = substring.length();
        if (text.length() + offsetInText > len2) {
            return -1;
        }
        int i = 0;
        int j = offsetInText;
        while (i < len2) {
            if (substring.charAt(i) != text.charAt(j)) {
                return -1;
            }
            i++;
            j++;
        }
        return i;
    }

    public void addMatchSetTo(UnicodeSet toUnionTo) {
        toUnionTo.addAll(this);
    }

    public int indexOf(int c) {
        if (c < 0 || c > 1114111) {
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex((long) c, 6));
        }
        int start = 0;
        int n = 0;
        while (true) {
            int[] iArr = this.list;
            int i = start + 1;
            int start2 = iArr[start];
            if (c < start2) {
                return -1;
            }
            int i2 = i + 1;
            int limit = iArr[i];
            if (c < limit) {
                return (n + c) - start2;
            }
            n += limit - start2;
            start = i2;
        }
    }

    public int charAt(int index) {
        if (index < 0) {
            return -1;
        }
        int len2 = this.len & -2;
        int start = 0;
        while (start < len2) {
            int[] iArr = this.list;
            int i = start + 1;
            int start2 = iArr[start];
            int i2 = i + 1;
            int count = iArr[i] - start2;
            if (index < count) {
                return start2 + index;
            }
            index -= count;
            start = i2;
        }
        return -1;
    }

    public UnicodeSet add(int start, int end) {
        checkFrozen();
        return add_unchecked(start, end);
    }

    public UnicodeSet addAll(int start, int end) {
        checkFrozen();
        return add_unchecked(start, end);
    }

    private UnicodeSet add_unchecked(int start, int end) {
        if (start < 0 || start > 1114111) {
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex((long) start, 6));
        } else if (end < 0 || end > 1114111) {
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex((long) end, 6));
        } else {
            if (start < end) {
                int limit = end + 1;
                int i = this.len;
                if ((i & 1) != 0) {
                    int lastLimit = i == 1 ? -2 : this.list[i - 2];
                    if (lastLimit <= start) {
                        checkFrozen();
                        if (lastLimit == start) {
                            int[] iArr = this.list;
                            int i2 = this.len;
                            iArr[i2 - 2] = limit;
                            if (limit == HIGH) {
                                this.len = i2 - 1;
                            }
                        } else {
                            int[] iArr2 = this.list;
                            int i3 = this.len;
                            iArr2[i3 - 1] = start;
                            if (limit < HIGH) {
                                ensureCapacity(i3 + 2);
                                int[] iArr3 = this.list;
                                int i4 = this.len;
                                int i5 = i4 + 1;
                                this.len = i5;
                                iArr3[i4] = limit;
                                this.len = i5 + 1;
                                iArr3[i5] = HIGH;
                            } else {
                                ensureCapacity(i3 + 1);
                                int[] iArr4 = this.list;
                                int i6 = this.len;
                                this.len = i6 + 1;
                                iArr4[i6] = HIGH;
                            }
                        }
                        this.pat = null;
                        return this;
                    }
                }
                add(range(start, end), 2, 0);
            } else if (start == end) {
                add(start);
            }
            return this;
        }
    }

    public final UnicodeSet add(int c) {
        checkFrozen();
        return add_unchecked(c);
    }

    private final UnicodeSet add_unchecked(int c) {
        if (c < 0 || c > 1114111) {
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex((long) c, 6));
        }
        int i = findCodePoint(c);
        if ((i & 1) != 0) {
            return this;
        }
        int[] iArr = this.list;
        if (c == iArr[i] - 1) {
            iArr[i] = c;
            if (c == 1114111) {
                ensureCapacity(this.len + 1);
                int[] iArr2 = this.list;
                int i2 = this.len;
                this.len = i2 + 1;
                iArr2[i2] = HIGH;
            }
            if (i > 0) {
                int[] iArr3 = this.list;
                if (c == iArr3[i - 1]) {
                    System.arraycopy(iArr3, i + 1, iArr3, i - 1, (this.len - i) - 1);
                    this.len -= 2;
                }
            }
        } else if (i <= 0 || c != iArr[i - 1]) {
            int i3 = this.len;
            if (i3 + 2 > iArr.length) {
                int[] temp = new int[nextCapacity(i3 + 2)];
                if (i != 0) {
                    System.arraycopy(this.list, 0, temp, 0, i);
                }
                System.arraycopy(this.list, i, temp, i + 2, this.len - i);
                this.list = temp;
            } else {
                System.arraycopy(iArr, i, iArr, i + 2, i3 - i);
            }
            int[] iArr4 = this.list;
            iArr4[i] = c;
            iArr4[i + 1] = c + 1;
            this.len += 2;
        } else {
            int i4 = i - 1;
            iArr[i4] = iArr[i4] + 1;
        }
        this.pat = null;
        return this;
    }

    public final UnicodeSet add(CharSequence s) {
        checkFrozen();
        int cp = getSingleCP(s);
        if (cp < 0) {
            String str = s.toString();
            if (!this.strings.contains(str)) {
                addString(str);
                this.pat = null;
            }
        } else {
            add_unchecked(cp, cp);
        }
        return this;
    }

    private void addString(CharSequence s) {
        if (this.strings == EMPTY_STRINGS) {
            this.strings = new TreeSet();
        }
        this.strings.add(s.toString());
    }

    private static int getSingleCP(CharSequence s) {
        if (s.length() < 1) {
            throw new IllegalArgumentException("Can't use zero-length strings in UnicodeSet");
        } else if (s.length() > 2) {
            return -1;
        } else {
            if (s.length() == 1) {
                return s.charAt(0);
            }
            int cp = UTF16.charAt(s, 0);
            if (cp > 65535) {
                return cp;
            }
            return -1;
        }
    }

    public final UnicodeSet addAll(CharSequence s) {
        checkFrozen();
        int i = 0;
        while (i < s.length()) {
            int cp = UTF16.charAt(s, i);
            add_unchecked(cp, cp);
            i += UTF16.getCharCount(cp);
        }
        return this;
    }

    public final UnicodeSet retainAll(CharSequence s) {
        return retainAll(fromAll(s));
    }

    public final UnicodeSet complementAll(CharSequence s) {
        return complementAll(fromAll(s));
    }

    public final UnicodeSet removeAll(CharSequence s) {
        return removeAll(fromAll(s));
    }

    public final UnicodeSet removeAllStrings() {
        checkFrozen();
        if (hasStrings()) {
            this.strings.clear();
            this.pat = null;
        }
        return this;
    }

    public static UnicodeSet from(CharSequence s) {
        return new UnicodeSet().add(s);
    }

    public static UnicodeSet fromAll(CharSequence s) {
        return new UnicodeSet().addAll(s);
    }

    public UnicodeSet retain(int start, int end) {
        checkFrozen();
        if (start < 0 || start > 1114111) {
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex((long) start, 6));
        } else if (end < 0 || end > 1114111) {
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex((long) end, 6));
        } else {
            if (start <= end) {
                retain(range(start, end), 2, 0);
            } else {
                clear();
            }
            return this;
        }
    }

    public final UnicodeSet retain(int c) {
        return retain(c, c);
    }

    public final UnicodeSet retain(CharSequence cs) {
        int cp = getSingleCP(cs);
        if (cp < 0) {
            checkFrozen();
            String s = cs.toString();
            if (this.strings.contains(s) && size() == 1) {
                return this;
            }
            clear();
            addString(s);
            this.pat = null;
        } else {
            retain(cp, cp);
        }
        return this;
    }

    public UnicodeSet remove(int start, int end) {
        checkFrozen();
        if (start < 0 || start > 1114111) {
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex((long) start, 6));
        } else if (end < 0 || end > 1114111) {
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex((long) end, 6));
        } else {
            if (start <= end) {
                retain(range(start, end), 2, 2);
            }
            return this;
        }
    }

    public final UnicodeSet remove(int c) {
        return remove(c, c);
    }

    public final UnicodeSet remove(CharSequence s) {
        int cp = getSingleCP(s);
        if (cp < 0) {
            checkFrozen();
            String str = s.toString();
            if (this.strings.contains(str)) {
                this.strings.remove(str);
                this.pat = null;
            }
        } else {
            remove(cp, cp);
        }
        return this;
    }

    public UnicodeSet complement(int start, int end) {
        checkFrozen();
        if (start < 0 || start > 1114111) {
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex((long) start, 6));
        } else if (end < 0 || end > 1114111) {
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex((long) end, 6));
        } else {
            if (start <= end) {
                xor(range(start, end), 2, 0);
            }
            this.pat = null;
            return this;
        }
    }

    public final UnicodeSet complement(int c) {
        return complement(c, c);
    }

    public UnicodeSet complement() {
        checkFrozen();
        int[] iArr = this.list;
        if (iArr[0] == 0) {
            System.arraycopy(iArr, 1, iArr, 0, this.len - 1);
            this.len--;
        } else {
            ensureCapacity(this.len + 1);
            int[] iArr2 = this.list;
            System.arraycopy(iArr2, 0, iArr2, 1, this.len);
            this.list[0] = 0;
            this.len++;
        }
        this.pat = null;
        return this;
    }

    public final UnicodeSet complement(CharSequence s) {
        checkFrozen();
        int cp = getSingleCP(s);
        if (cp < 0) {
            String s2 = s.toString();
            if (this.strings.contains(s2)) {
                this.strings.remove(s2);
            } else {
                addString(s2);
            }
            this.pat = null;
        } else {
            complement(cp, cp);
        }
        return this;
    }

    public boolean contains(int c) {
        if (c < 0 || c > 1114111) {
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex((long) c, 6));
        } else if (this.bmpSet != null) {
            return this.bmpSet.contains(c);
        } else {
            if (this.stringSpan != null) {
                return this.stringSpan.contains(c);
            }
            return (findCodePoint(c) & 1) != 0;
        }
    }

    private final int findCodePoint(int c) {
        int[] iArr = this.list;
        if (c < iArr[0]) {
            return 0;
        }
        int i = this.len;
        if (i >= 2 && c >= iArr[i - 2]) {
            return i - 1;
        }
        int lo = 0;
        int hi = i - 1;
        while (true) {
            int i2 = (lo + hi) >>> 1;
            if (i2 == lo) {
                return hi;
            }
            if (c < this.list[i2]) {
                hi = i2;
            } else {
                lo = i2;
            }
        }
    }

    public boolean contains(int start, int end) {
        if (start < 0 || start > 1114111) {
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex((long) start, 6));
        } else if (end < 0 || end > 1114111) {
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex((long) end, 6));
        } else {
            int i = findCodePoint(start);
            return (i & 1) != 0 && end < this.list[i];
        }
    }

    public final boolean contains(CharSequence s) {
        int cp = getSingleCP(s);
        if (cp < 0) {
            return this.strings.contains(s.toString());
        }
        return contains(cp);
    }

    public boolean containsAll(UnicodeSet b) {
        UnicodeSet unicodeSet = b;
        int[] listB = unicodeSet.list;
        boolean needA = true;
        boolean needB = true;
        int startA = 0;
        int startB = 0;
        int aLen = this.len - 1;
        int bLen = unicodeSet.len - 1;
        int aPtr = 0;
        int bPtr = 0;
        int limitA = 0;
        int limitB = 0;
        while (true) {
            if (needA) {
                if (startA < aLen) {
                    int[] iArr = this.list;
                    int aPtr2 = startA + 1;
                    int aPtr3 = iArr[startA];
                    limitA = iArr[aPtr2];
                    aPtr = aPtr3;
                    startA = aPtr2 + 1;
                } else if (!needB || startB < bLen) {
                    return false;
                }
            }
            if (needB) {
                if (startB >= bLen) {
                    break;
                }
                int bPtr2 = startB + 1;
                int startB2 = listB[startB];
                limitB = listB[bPtr2];
                bPtr = startB2;
                startB = bPtr2 + 1;
            }
            if (bPtr >= limitA) {
                needA = true;
                needB = false;
            } else if (bPtr < aPtr || limitB > limitA) {
                return false;
            } else {
                needA = false;
                needB = true;
            }
        }
        if (!this.strings.containsAll(unicodeSet.strings)) {
            return false;
        }
        return true;
    }

    public boolean containsAll(String s) {
        int i = 0;
        while (i < s.length()) {
            int cp = UTF16.charAt(s, i);
            if (contains(cp)) {
                i += UTF16.getCharCount(cp);
            } else if (!hasStrings()) {
                return false;
            } else {
                return containsAll(s, 0);
            }
        }
        return true;
    }

    private boolean containsAll(String s, int i) {
        if (i >= s.length()) {
            return true;
        }
        int cp = UTF16.charAt(s, i);
        if (contains(cp) && containsAll(s, UTF16.getCharCount(cp) + i)) {
            return true;
        }
        for (String setStr : this.strings) {
            if (s.startsWith(setStr, i) && containsAll(s, setStr.length() + i)) {
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public String getRegexEquivalent() {
        if (!hasStrings()) {
            return toString();
        }
        StringBuilder result = new StringBuilder("(?:");
        appendNewPattern(result, true, false);
        for (String s : this.strings) {
            result.append('|');
            _appendToPat(result, s, true);
        }
        return result.append(")").toString();
    }

    public boolean containsNone(int start, int end) {
        int[] iArr;
        if (start < 0 || start > 1114111) {
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex((long) start, 6));
        } else if (end < 0 || end > 1114111) {
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex((long) end, 6));
        } else {
            int i = -1;
            do {
                iArr = this.list;
                i++;
            } while (start >= iArr[i]);
            if ((i & 1) != 0 || end >= iArr[i]) {
                return false;
            }
            return true;
        }
    }

    public boolean containsNone(UnicodeSet b) {
        UnicodeSet unicodeSet = b;
        int[] listB = unicodeSet.list;
        boolean needA = true;
        boolean needB = true;
        int startA = 0;
        int startB = 0;
        int aLen = this.len - 1;
        int bLen = unicodeSet.len - 1;
        int aPtr = 0;
        int bPtr = 0;
        int limitA = 0;
        int limitB = 0;
        while (true) {
            if (needA) {
                if (startA >= aLen) {
                    break;
                }
                int[] iArr = this.list;
                int aPtr2 = startA + 1;
                int aPtr3 = iArr[startA];
                limitA = iArr[aPtr2];
                aPtr = aPtr3;
                startA = aPtr2 + 1;
            }
            if (needB) {
                if (startB >= bLen) {
                    break;
                }
                int bPtr2 = startB + 1;
                int startB2 = listB[startB];
                limitB = listB[bPtr2];
                bPtr = startB2;
                startB = bPtr2 + 1;
            }
            if (bPtr >= limitA) {
                needA = true;
                needB = false;
            } else if (aPtr < limitB) {
                return false;
            } else {
                needA = false;
                needB = true;
            }
        }
        if (!SortedSetRelation.hasRelation(this.strings, 5, unicodeSet.strings)) {
            return false;
        }
        return true;
    }

    public boolean containsNone(CharSequence s) {
        return span(s, SpanCondition.NOT_CONTAINED) == s.length();
    }

    public final boolean containsSome(int start, int end) {
        return !containsNone(start, end);
    }

    public final boolean containsSome(UnicodeSet s) {
        return !containsNone(s);
    }

    public final boolean containsSome(CharSequence s) {
        return !containsNone(s);
    }

    public UnicodeSet addAll(UnicodeSet c) {
        checkFrozen();
        add(c.list, c.len, 0);
        if (c.hasStrings()) {
            SortedSet<String> sortedSet = this.strings;
            if (sortedSet == EMPTY_STRINGS) {
                this.strings = new TreeSet(c.strings);
            } else {
                sortedSet.addAll(c.strings);
            }
        }
        return this;
    }

    public UnicodeSet retainAll(UnicodeSet c) {
        checkFrozen();
        retain(c.list, c.len, 0);
        if (hasStrings()) {
            if (!c.hasStrings()) {
                this.strings.clear();
            } else {
                this.strings.retainAll(c.strings);
            }
        }
        return this;
    }

    public UnicodeSet removeAll(UnicodeSet c) {
        checkFrozen();
        retain(c.list, c.len, 2);
        if (hasStrings() && c.hasStrings()) {
            this.strings.removeAll(c.strings);
        }
        return this;
    }

    public UnicodeSet complementAll(UnicodeSet c) {
        checkFrozen();
        xor(c.list, c.len, 0);
        if (c.hasStrings()) {
            SortedSet<String> sortedSet = this.strings;
            if (sortedSet == EMPTY_STRINGS) {
                this.strings = new TreeSet(c.strings);
            } else {
                SortedSetRelation.doOperation(sortedSet, 5, c.strings);
            }
        }
        return this;
    }

    public UnicodeSet clear() {
        checkFrozen();
        this.list[0] = HIGH;
        this.len = 1;
        this.pat = null;
        if (hasStrings()) {
            this.strings.clear();
        }
        return this;
    }

    public int getRangeCount() {
        return this.len / 2;
    }

    public int getRangeStart(int index) {
        return this.list[index * 2];
    }

    public int getRangeEnd(int index) {
        return this.list[(index * 2) + 1] - 1;
    }

    public UnicodeSet compact() {
        checkFrozen();
        int i = this.len;
        int i2 = i + 7;
        int[] iArr = this.list;
        if (i2 < iArr.length) {
            this.list = Arrays.copyOf(iArr, i);
        }
        this.rangeList = null;
        this.buffer = null;
        SortedSet<String> sortedSet = this.strings;
        SortedSet<String> sortedSet2 = EMPTY_STRINGS;
        if (sortedSet != sortedSet2 && sortedSet.isEmpty()) {
            this.strings = sortedSet2;
        }
        return this;
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        try {
            UnicodeSet that = (UnicodeSet) o;
            if (this.len != that.len) {
                return false;
            }
            for (int i = 0; i < this.len; i++) {
                if (this.list[i] != that.list[i]) {
                    return false;
                }
            }
            if (!this.strings.equals(that.strings)) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int hashCode() {
        int result = this.len;
        for (int i = 0; i < this.len; i++) {
            result = (result * 1000003) + this.list[i];
        }
        return result;
    }

    public String toString() {
        return toPattern(true);
    }

    @Deprecated
    public UnicodeSet applyPattern(String pattern, ParsePosition pos, SymbolTable symbols, int options) {
        boolean parsePositionWasNull = pos == null;
        if (parsePositionWasNull) {
            pos = new ParsePosition(0);
        }
        StringBuilder rebuiltPat = new StringBuilder();
        RuleCharacterIterator chars = new RuleCharacterIterator(pattern, symbols, pos);
        applyPattern(chars, symbols, rebuiltPat, options, 0);
        if (chars.inVariable()) {
            syntaxError(chars, "Extra chars in variable value");
        }
        this.pat = rebuiltPat.toString();
        if (parsePositionWasNull) {
            int i = pos.getIndex();
            if ((options & 1) != 0) {
                i = PatternProps.skipWhiteSpace(pattern, i);
            }
            if (i != pattern.length()) {
                throw new IllegalArgumentException("Parse of \"" + pattern + "\" failed at " + i);
            }
        }
        return this;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void applyPattern(com.ibm.icu.impl.RuleCharacterIterator r28, com.ibm.icu.text.SymbolTable r29, java.lang.Appendable r30, int r31, int r32) {
        /*
            r27 = this;
            r1 = r27
            r8 = r28
            r9 = r29
            r10 = r30
            r11 = r32
            r0 = 100
            if (r11 <= r0) goto L_0x0013
            java.lang.String r0 = "Pattern nested too deeply"
            syntaxError(r8, r0)
        L_0x0013:
            r0 = 3
            r2 = r31 & 1
            if (r2 == 0) goto L_0x001c
            r0 = r0 | 4
            r12 = r0
            goto L_0x001d
        L_0x001c:
            r12 = r0
        L_0x001d:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r13 = r0
            r0 = 0
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r14 = 0
            r15 = 0
            r27.clear()
            r16 = 0
            r17 = r2
            r2 = r7
            r7 = r6
            r6 = r14
            r14 = r0
            r26 = r16
            r16 = r15
            r15 = r26
        L_0x003d:
            r10 = 2
            r18 = r15
            if (r2 == r10) goto L_0x03d3
            boolean r0 = r28.atEnd()
            if (r0 != 0) goto L_0x03d3
            r0 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            boolean r22 = resemblesPropertyPattern((com.ibm.icu.impl.RuleCharacterIterator) r8, (int) r12)
            if (r22 == 0) goto L_0x005b
            r21 = 2
            r10 = r2
            r15 = r4
            goto L_0x00de
        L_0x005b:
            java.lang.Object r4 = r8.getPos(r4)
            int r15 = r8.next(r12)
            boolean r19 = r28.isEscaped()
            r0 = 91
            if (r15 != r0) goto L_0x00bd
            if (r19 != 0) goto L_0x00bd
            r10 = 1
            if (r2 != r10) goto L_0x007a
            r8.setPos(r4)
            r21 = 1
            r10 = r2
            r0 = r15
            r15 = r4
            goto L_0x00de
        L_0x007a:
            r2 = 1
            r13.append(r0)
            java.lang.Object r0 = r8.getPos(r4)
            int r4 = r8.next(r12)
            boolean r10 = r28.isEscaped()
            r15 = 94
            if (r4 != r15) goto L_0x00a7
            if (r10 != 0) goto L_0x00a7
            r16 = 1
            r13.append(r15)
            java.lang.Object r0 = r8.getPos(r0)
            int r4 = r8.next(r12)
            boolean r10 = r28.isEscaped()
            r26 = r4
            r4 = r0
            r0 = r26
            goto L_0x00ac
        L_0x00a7:
            r26 = r4
            r4 = r0
            r0 = r26
        L_0x00ac:
            r15 = 45
            if (r0 != r15) goto L_0x00b5
            r19 = 1
            r10 = r2
            r15 = r4
            goto L_0x00de
        L_0x00b5:
            r8.setPos(r4)
            r10 = r30
            r15 = r18
            goto L_0x003d
        L_0x00bd:
            if (r9 == 0) goto L_0x00db
            com.ibm.icu.text.UnicodeMatcher r10 = r9.lookupMatcher(r15)
            if (r10 == 0) goto L_0x00db
            r0 = r10
            com.ibm.icu.text.UnicodeSet r0 = (com.ibm.icu.text.UnicodeSet) r0     // Catch:{ ClassCastException -> 0x00d0 }
            r20 = r0
            r21 = 3
            r10 = r2
            r0 = r15
            r15 = r4
            goto L_0x00de
        L_0x00d0:
            r0 = move-exception
            r24 = r0
            r0 = r24
            java.lang.String r0 = "Syntax error"
            syntaxError(r8, r0)
        L_0x00db:
            r10 = r2
            r0 = r15
            r15 = r4
        L_0x00de:
            r2 = 38
            if (r21 == 0) goto L_0x017b
            r4 = 1
            if (r5 != r4) goto L_0x00f8
            if (r6 == 0) goto L_0x00ec
            java.lang.String r4 = "Char expected after operator"
            syntaxError(r8, r4)
        L_0x00ec:
            r1.add_unchecked(r7, r7)
            r4 = 0
            _appendToPat(r13, (int) r7, (boolean) r4)
            r5 = 0
            r6 = 0
            r24 = r5
            goto L_0x00fa
        L_0x00f8:
            r24 = r5
        L_0x00fa:
            r4 = 45
            if (r6 == r4) goto L_0x0100
            if (r6 != r2) goto L_0x0103
        L_0x0100:
            r13.append(r6)
        L_0x0103:
            if (r20 != 0) goto L_0x0112
            if (r3 != 0) goto L_0x010d
            com.ibm.icu.text.UnicodeSet r2 = new com.ibm.icu.text.UnicodeSet
            r2.<init>()
            r3 = r2
        L_0x010d:
            r20 = r3
            r5 = r20
            goto L_0x0116
        L_0x0112:
            r5 = r20
            r20 = r3
        L_0x0116:
            switch(r21) {
                case 1: goto L_0x0132;
                case 2: goto L_0x0127;
                case 3: goto L_0x011e;
                default: goto L_0x0119;
            }
        L_0x0119:
            r11 = r5
            r25 = r6
            r9 = r7
            goto L_0x0146
        L_0x011e:
            r2 = 0
            r5._toPattern(r13, r2)
            r11 = r5
            r25 = r6
            r9 = r7
            goto L_0x0146
        L_0x0127:
            r8.skipIgnored(r12)
            r5.applyPropertyPattern((com.ibm.icu.impl.RuleCharacterIterator) r8, (java.lang.Appendable) r13, (com.ibm.icu.text.SymbolTable) r9)
            r11 = r5
            r25 = r6
            r9 = r7
            goto L_0x0146
        L_0x0132:
            int r23 = r11 + 1
            r2 = r5
            r3 = r28
            r4 = r29
            r11 = r5
            r5 = r13
            r25 = r6
            r6 = r31
            r9 = r7
            r7 = r23
            r2.applyPattern(r3, r4, r5, r6, r7)
        L_0x0146:
            r17 = 1
            if (r10 != 0) goto L_0x015b
            r1.set(r11)
            r2 = 2
            r4 = r15
            r15 = r17
            r3 = r18
            r18 = r20
            r5 = r24
            r6 = r25
            goto L_0x03dc
        L_0x015b:
            switch(r25) {
                case 0: goto L_0x0167;
                case 38: goto L_0x0163;
                case 45: goto L_0x015f;
                default: goto L_0x015e;
            }
        L_0x015e:
            goto L_0x016a
        L_0x015f:
            r1.removeAll((com.ibm.icu.text.UnicodeSet) r11)
            goto L_0x016a
        L_0x0163:
            r1.retainAll((com.ibm.icu.text.UnicodeSet) r11)
            goto L_0x016a
        L_0x0167:
            r1.addAll((com.ibm.icu.text.UnicodeSet) r11)
        L_0x016a:
            r6 = 0
            r5 = 2
            r11 = r32
            r7 = r9
            r2 = r10
            r4 = r15
            r15 = r18
            r3 = r20
            r9 = r29
            r10 = r30
            goto L_0x003d
        L_0x017b:
            r9 = r7
            if (r10 != 0) goto L_0x0183
            java.lang.String r4 = "Missing '['"
            syntaxError(r8, r4)
        L_0x0183:
            if (r19 != 0) goto L_0x0371
            r4 = 93
            switch(r0) {
                case 36: goto L_0x0315;
                case 38: goto L_0x02f4;
                case 45: goto L_0x029d;
                case 93: goto L_0x026a;
                case 94: goto L_0x025e;
                case 123: goto L_0x0192;
                default: goto L_0x018a;
            }
        L_0x018a:
            r26 = r18
            r18 = r3
            r3 = r26
            goto L_0x0377
        L_0x0192:
            if (r6 == 0) goto L_0x019d
            r2 = 45
            if (r6 == r2) goto L_0x019d
            java.lang.String r2 = "Missing operand after operator"
            syntaxError(r8, r2)
        L_0x019d:
            r2 = 1
            if (r5 != r2) goto L_0x01a7
            r1.add_unchecked(r9, r9)
            r2 = 0
            _appendToPat(r13, (int) r9, (boolean) r2)
        L_0x01a7:
            r5 = 0
            if (r14 != 0) goto L_0x01b1
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r14 = r2
            goto L_0x01b5
        L_0x01b1:
            r2 = 0
            r14.setLength(r2)
        L_0x01b5:
            r2 = 0
        L_0x01b6:
            boolean r4 = r28.atEnd()
            r7 = 125(0x7d, float:1.75E-43)
            if (r4 != 0) goto L_0x01d2
            int r0 = r8.next(r12)
            boolean r19 = r28.isEscaped()
            if (r0 != r7) goto L_0x01ce
            if (r19 != 0) goto L_0x01ce
            r2 = 1
            r4 = r2
            r2 = r0
            goto L_0x01d4
        L_0x01ce:
            appendCodePoint(r14, r0)
            goto L_0x01b6
        L_0x01d2:
            r4 = r2
            r2 = r0
        L_0x01d4:
            int r0 = r14.length()
            r11 = 1
            if (r0 < r11) goto L_0x01dd
            if (r4 != 0) goto L_0x01e2
        L_0x01dd:
            java.lang.String r0 = "Invalid multicharacter string"
            syntaxError(r8, r0)
        L_0x01e2:
            java.lang.String r11 = r14.toString()
            r7 = 45
            if (r6 != r7) goto L_0x0238
            if (r18 != 0) goto L_0x01ef
            java.lang.String r0 = ""
            goto L_0x01f1
        L_0x01ef:
            r0 = r18
        L_0x01f1:
            int r7 = com.ibm.icu.lang.CharSequences.getSingleCodePoint(r0)
            r23 = r2
            int r2 = com.ibm.icu.lang.CharSequences.getSingleCodePoint(r11)
            r0 = 2147483647(0x7fffffff, float:NaN)
            if (r7 == r0) goto L_0x020c
            if (r2 == r0) goto L_0x020c
            r1.add(r7, r2)
            r24 = r2
            r2 = r18
            r18 = r3
            goto L_0x0234
        L_0x020c:
            java.util.SortedSet<java.lang.String> r0 = r1.strings
            r24 = r2
            java.util.SortedSet<java.lang.String> r2 = EMPTY_STRINGS
            if (r0 != r2) goto L_0x021b
            java.util.TreeSet r0 = new java.util.TreeSet
            r0.<init>()
            r1.strings = r0
        L_0x021b:
            java.util.SortedSet<java.lang.String> r0 = r1.strings     // Catch:{ Exception -> 0x0228 }
            r2 = r18
            r18 = r3
            r3 = 1
            com.ibm.icu.impl.StringRange.expand(r2, r11, r3, r0)     // Catch:{ Exception -> 0x0226 }
            goto L_0x0234
        L_0x0226:
            r0 = move-exception
            goto L_0x022d
        L_0x0228:
            r0 = move-exception
            r2 = r18
            r18 = r3
        L_0x022d:
            java.lang.String r3 = r0.getMessage()
            syntaxError(r8, r3)
        L_0x0234:
            r0 = 0
            r2 = 0
            r6 = r2
            goto L_0x0242
        L_0x0238:
            r23 = r2
            r2 = r18
            r18 = r3
            r1.add((java.lang.CharSequence) r11)
            r0 = r11
        L_0x0242:
            r2 = 123(0x7b, float:1.72E-43)
            r13.append(r2)
            r2 = 0
            _appendToPat(r13, (java.lang.String) r11, (boolean) r2)
            r2 = 125(0x7d, float:1.75E-43)
            r13.append(r2)
            r11 = r32
            r7 = r9
            r2 = r10
            r4 = r15
            r3 = r18
            r9 = r29
            r10 = r30
            r15 = r0
            goto L_0x003d
        L_0x025e:
            r2 = r18
            r18 = r3
            java.lang.String r3 = "'^' not after '['"
            syntaxError(r8, r3)
            r3 = r2
            goto L_0x0377
        L_0x026a:
            r26 = r18
            r18 = r3
            r3 = r26
            r7 = 1
            if (r5 != r7) goto L_0x027a
            r1.add_unchecked(r9, r9)
            r7 = 0
            _appendToPat(r13, (int) r9, (boolean) r7)
        L_0x027a:
            r7 = 45
            if (r6 != r7) goto L_0x0285
            r1.add_unchecked(r6, r6)
            r13.append(r6)
            goto L_0x028c
        L_0x0285:
            if (r6 != r2) goto L_0x028c
            java.lang.String r2 = "Trailing '&'"
            syntaxError(r8, r2)
        L_0x028c:
            r13.append(r4)
            r2 = 2
            r10 = r30
            r11 = r32
            r7 = r9
            r4 = r15
            r9 = r29
            r15 = r3
            r3 = r18
            goto L_0x003d
        L_0x029d:
            r26 = r18
            r18 = r3
            r3 = r26
            if (r6 != 0) goto L_0x02ec
            if (r5 == 0) goto L_0x02b6
            char r6 = (char) r0
            r11 = r32
            r7 = r9
            r2 = r10
            r4 = r15
            r9 = r29
            r10 = r30
            r15 = r3
            r3 = r18
            goto L_0x003d
        L_0x02b6:
            if (r3 == 0) goto L_0x02c7
            char r6 = (char) r0
            r11 = r32
            r7 = r9
            r2 = r10
            r4 = r15
            r9 = r29
            r10 = r30
            r15 = r3
            r3 = r18
            goto L_0x003d
        L_0x02c7:
            r1.add_unchecked(r0, r0)
            int r0 = r8.next(r12)
            boolean r2 = r28.isEscaped()
            if (r0 != r4) goto L_0x02ea
            if (r2 != 0) goto L_0x02ea
            java.lang.String r4 = "-]"
            r13.append(r4)
            r4 = 2
            r10 = r30
            r11 = r32
            r2 = r4
            r7 = r9
            r4 = r15
            r9 = r29
            r15 = r3
            r3 = r18
            goto L_0x003d
        L_0x02ea:
            r19 = r2
        L_0x02ec:
            java.lang.String r2 = "'-' not after char, string, or set"
            syntaxError(r8, r2)
            r4 = r15
            goto L_0x0378
        L_0x02f4:
            r26 = r18
            r18 = r3
            r3 = r26
            r2 = 2
            if (r5 != r2) goto L_0x030e
            if (r6 != 0) goto L_0x030e
            char r6 = (char) r0
            r11 = r32
            r7 = r9
            r2 = r10
            r4 = r15
            r9 = r29
            r10 = r30
            r15 = r3
            r3 = r18
            goto L_0x003d
        L_0x030e:
            java.lang.String r2 = "'&' not after set"
            syntaxError(r8, r2)
            goto L_0x0377
        L_0x0315:
            r26 = r18
            r18 = r3
            r3 = r26
            java.lang.Object r2 = r8.getPos(r15)
            int r0 = r8.next(r12)
            boolean r19 = r28.isEscaped()
            if (r0 != r4) goto L_0x032d
            if (r19 != 0) goto L_0x032d
            r7 = 1
            goto L_0x032e
        L_0x032d:
            r7 = 0
        L_0x032e:
            if (r29 != 0) goto L_0x0339
            if (r7 != 0) goto L_0x0339
            r0 = 36
            r8.setPos(r2)
            r4 = r2
            goto L_0x0378
        L_0x0339:
            if (r7 == 0) goto L_0x036a
            if (r6 != 0) goto L_0x036a
            r11 = 1
            if (r5 != r11) goto L_0x0347
            r1.add_unchecked(r9, r9)
            r11 = 0
            _appendToPat(r13, (int) r9, (boolean) r11)
        L_0x0347:
            r11 = 65535(0xffff, float:9.1834E-41)
            r1.add_unchecked(r11)
            r17 = 1
            r11 = 36
            java.lang.StringBuilder r11 = r13.append(r11)
            r11.append(r4)
            r4 = 2
            r10 = r30
            r11 = r32
            r15 = r3
            r7 = r9
            r3 = r18
            r9 = r29
            r26 = r4
            r4 = r2
            r2 = r26
            goto L_0x003d
        L_0x036a:
            java.lang.String r4 = "Unquoted '$'"
            syntaxError(r8, r4)
            r4 = r2
            goto L_0x0378
        L_0x0371:
            r26 = r18
            r18 = r3
            r3 = r26
        L_0x0377:
            r4 = r15
        L_0x0378:
            java.lang.String r2 = "Invalid range"
            switch(r5) {
                case 0: goto L_0x03b9;
                case 1: goto L_0x038c;
                case 2: goto L_0x0380;
                default: goto L_0x037d;
            }
        L_0x037d:
            r15 = r3
            r7 = r9
            goto L_0x03c8
        L_0x0380:
            if (r6 == 0) goto L_0x0387
            java.lang.String r2 = "Set expected after operator"
            syntaxError(r8, r2)
        L_0x0387:
            r2 = r0
            r5 = 1
            r7 = r2
            r15 = r3
            goto L_0x03c8
        L_0x038c:
            r7 = 45
            if (r6 != r7) goto L_0x03ae
            if (r3 == 0) goto L_0x0395
            syntaxError(r8, r2)
        L_0x0395:
            if (r9 < r0) goto L_0x039a
            syntaxError(r8, r2)
        L_0x039a:
            r1.add_unchecked(r9, r0)
            r2 = 0
            _appendToPat(r13, (int) r9, (boolean) r2)
            r13.append(r6)
            _appendToPat(r13, (int) r0, (boolean) r2)
            r2 = 0
            r5 = 0
            r15 = r3
            r6 = r5
            r7 = r9
            r5 = r2
            goto L_0x03c8
        L_0x03ae:
            r2 = 0
            r1.add_unchecked(r9, r9)
            _appendToPat(r13, (int) r9, (boolean) r2)
            r2 = r0
            r7 = r2
            r15 = r3
            goto L_0x03c8
        L_0x03b9:
            r7 = 45
            if (r6 != r7) goto L_0x03c2
            if (r3 == 0) goto L_0x03c2
            syntaxError(r8, r2)
        L_0x03c2:
            r2 = 1
            r5 = r0
            r3 = 0
            r15 = r3
            r7 = r5
            r5 = r2
        L_0x03c8:
            r9 = r29
            r11 = r32
            r2 = r10
            r3 = r18
            r10 = r30
            goto L_0x003d
        L_0x03d3:
            r9 = r7
            r26 = r18
            r18 = r3
            r3 = r26
            r15 = r17
        L_0x03dc:
            r7 = 2
            if (r2 == r7) goto L_0x03e4
            java.lang.String r0 = "Missing ']'"
            syntaxError(r8, r0)
        L_0x03e4:
            r8.skipIgnored(r12)
            r0 = r31 & 2
            if (r0 == 0) goto L_0x03ef
            r7 = 2
            r1.closeOver(r7)
        L_0x03ef:
            if (r16 == 0) goto L_0x03f4
            r27.complement()
        L_0x03f4:
            if (r15 == 0) goto L_0x0400
            java.lang.String r0 = r13.toString()
            r7 = r30
            append(r7, r0)
            goto L_0x0407
        L_0x0400:
            r7 = r30
            r10 = 1
            r11 = 0
            r1.appendNewPattern(r7, r11, r10)
        L_0x0407:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.UnicodeSet.applyPattern(com.ibm.icu.impl.RuleCharacterIterator, com.ibm.icu.text.SymbolTable, java.lang.Appendable, int, int):void");
    }

    private static void syntaxError(RuleCharacterIterator chars, String msg) {
        throw new IllegalArgumentException("Error: " + msg + " at \"" + Utility.escape(chars.toString()) + '\"');
    }

    public <T extends Collection<String>> T addAllTo(T target) {
        return addAllTo(this, target);
    }

    public String[] addAllTo(String[] target) {
        return (String[]) addAllTo(this, (T[]) target);
    }

    public static String[] toArray(UnicodeSet set) {
        return (String[]) addAllTo(set, (T[]) new String[set.size()]);
    }

    public UnicodeSet add(Iterable<?> source) {
        return addAll(source);
    }

    public UnicodeSet addAll(Iterable<?> source) {
        checkFrozen();
        for (Object o : source) {
            add((CharSequence) o.toString());
        }
        return this;
    }

    private int nextCapacity(int minCapacity) {
        if (minCapacity < 25) {
            return minCapacity + 25;
        }
        if (minCapacity <= 2500) {
            return minCapacity * 5;
        }
        int newCapacity = minCapacity * 2;
        if (newCapacity > MAX_LENGTH) {
            return MAX_LENGTH;
        }
        return newCapacity;
    }

    private void ensureCapacity(int newLen) {
        if (newLen > MAX_LENGTH) {
            newLen = MAX_LENGTH;
        }
        if (newLen > this.list.length) {
            int[] temp = new int[nextCapacity(newLen)];
            System.arraycopy(this.list, 0, temp, 0, this.len);
            this.list = temp;
        }
    }

    private void ensureBufferCapacity(int newLen) {
        if (newLen > MAX_LENGTH) {
            newLen = MAX_LENGTH;
        }
        int[] iArr = this.buffer;
        if (iArr == null || newLen > iArr.length) {
            this.buffer = new int[nextCapacity(newLen)];
        }
    }

    private int[] range(int start, int end) {
        int[] iArr = this.rangeList;
        if (iArr == null) {
            this.rangeList = new int[]{start, end + 1, HIGH};
        } else {
            iArr[0] = start;
            iArr[1] = end + 1;
        }
        return this.rangeList;
    }

    private UnicodeSet xor(int[] other, int otherLen, int polarity) {
        int b;
        int j;
        ensureBufferCapacity(this.len + otherLen);
        int k = 0;
        int i = 0 + 1;
        int a = this.list[0];
        if (polarity != 1 && polarity != 2) {
            b = 0 + 1;
            j = other[0];
        } else if (other[0] == 0) {
            int j2 = 0 + 1;
            b = j2;
            j = other[j2];
        } else {
            b = 0;
            j = 0;
        }
        while (true) {
            if (a < j) {
                this.buffer[k] = a;
                a = this.list[i];
                i++;
                k++;
            } else if (j < a) {
                this.buffer[k] = j;
                j = other[b];
                b++;
                k++;
            } else if (a != HIGH) {
                a = this.list[i];
                j = other[b];
                b++;
                i++;
            } else {
                int[] iArr = this.buffer;
                iArr[k] = HIGH;
                this.len = k + 1;
                int[] temp = this.list;
                this.list = iArr;
                this.buffer = temp;
                this.pat = null;
                return this;
            }
        }
    }

    private UnicodeSet add(int[] other, int otherLen, int polarity) {
        int a;
        int j;
        int j2;
        int k;
        ensureBufferCapacity(this.len + otherLen);
        int j3 = 0;
        int i = 0 + 1;
        int a2 = this.list[0];
        int j4 = 0 + 1;
        int b = other[0];
        while (true) {
            switch (polarity) {
                case 0:
                    if (a2 < b) {
                        if (j3 > 0) {
                            int[] iArr = this.buffer;
                            if (a2 <= iArr[j3 - 1]) {
                                j3--;
                                a2 = max(this.list[i], iArr[j3]);
                                i++;
                                polarity ^= 1;
                                continue;
                            }
                        }
                        this.buffer[j3] = a2;
                        a2 = this.list[i];
                        j3++;
                        i++;
                        polarity ^= 1;
                        continue;
                    } else if (b < a2) {
                        if (j3 > 0) {
                            int[] iArr2 = this.buffer;
                            if (b <= iArr2[j3 - 1]) {
                                j2 = j3 - 1;
                                b = max(other[j4], iArr2[j2]);
                                j4++;
                                polarity ^= 2;
                                break;
                            }
                        }
                        this.buffer[j3] = b;
                        b = other[j4];
                        j2 = j3 + 1;
                        j4++;
                        polarity ^= 2;
                    } else if (a2 == HIGH) {
                        break;
                    } else {
                        if (j3 > 0) {
                            int[] iArr3 = this.buffer;
                            if (a2 <= iArr3[j3 - 1]) {
                                j = j3 - 1;
                                a = max(this.list[i], iArr3[j]);
                                i++;
                                b = other[j4];
                                polarity = (polarity ^ 1) ^ 2;
                                j4++;
                                break;
                            }
                        }
                        this.buffer[j3] = a2;
                        a = this.list[i];
                        j = j3 + 1;
                        i++;
                        b = other[j4];
                        polarity = (polarity ^ 1) ^ 2;
                        j4++;
                    }
                case 1:
                    if (a2 >= b) {
                        if (b >= a2) {
                            if (a2 != HIGH) {
                                a2 = this.list[i];
                                b = other[j4];
                                polarity = (polarity ^ 1) ^ 2;
                                j4++;
                                i++;
                                break;
                            } else {
                                break;
                            }
                        } else {
                            b = other[j4];
                            polarity ^= 2;
                            j4++;
                            break;
                        }
                    } else {
                        this.buffer[j3] = a2;
                        a2 = this.list[i];
                        polarity ^= 1;
                        i++;
                        j3++;
                        continue;
                    }
                case 2:
                    if (b >= a2) {
                        if (a2 >= b) {
                            if (a2 != HIGH) {
                                a2 = this.list[i];
                                b = other[j4];
                                polarity = (polarity ^ 1) ^ 2;
                                j4++;
                                i++;
                                break;
                            } else {
                                break;
                            }
                        } else {
                            a2 = this.list[i];
                            polarity ^= 1;
                            i++;
                            break;
                        }
                    } else {
                        this.buffer[j3] = b;
                        b = other[j4];
                        polarity ^= 2;
                        j4++;
                        j3++;
                        continue;
                    }
                case 3:
                    if (b <= a2) {
                        if (a2 == HIGH) {
                            break;
                        } else {
                            k = j3 + 1;
                            this.buffer[j3] = a2;
                        }
                    } else if (b == HIGH) {
                        break;
                    } else {
                        k = j3 + 1;
                        this.buffer[j3] = b;
                    }
                    a2 = this.list[i];
                    b = other[j4];
                    polarity = (polarity ^ 1) ^ 2;
                    j4++;
                    i++;
                    j3 = k;
                    continue;
            }
        }
        int[] iArr4 = this.buffer;
        iArr4[j3] = HIGH;
        this.len = j3 + 1;
        int[] temp = this.list;
        this.list = iArr4;
        this.buffer = temp;
        this.pat = null;
        return this;
    }

    private UnicodeSet retain(int[] other, int otherLen, int polarity) {
        ensureBufferCapacity(this.len + otherLen);
        int k = 0;
        int i = 0 + 1;
        int a = this.list[0];
        int j = 0 + 1;
        int b = other[0];
        while (true) {
            switch (polarity) {
                case 0:
                    if (a >= b) {
                        if (b >= a) {
                            if (a != HIGH) {
                                this.buffer[k] = a;
                                a = this.list[i];
                                b = other[j];
                                polarity = (polarity ^ 1) ^ 2;
                                j++;
                                i++;
                                k++;
                                break;
                            } else {
                                break;
                            }
                        } else {
                            b = other[j];
                            polarity ^= 2;
                            j++;
                            break;
                        }
                    } else {
                        a = this.list[i];
                        polarity ^= 1;
                        i++;
                        continue;
                    }
                case 1:
                    if (a >= b) {
                        if (b >= a) {
                            if (a != HIGH) {
                                a = this.list[i];
                                b = other[j];
                                polarity = (polarity ^ 1) ^ 2;
                                j++;
                                i++;
                                break;
                            } else {
                                break;
                            }
                        } else {
                            this.buffer[k] = b;
                            b = other[j];
                            polarity ^= 2;
                            j++;
                            k++;
                            break;
                        }
                    } else {
                        a = this.list[i];
                        polarity ^= 1;
                        i++;
                        continue;
                    }
                case 2:
                    if (b >= a) {
                        if (a >= b) {
                            if (a != HIGH) {
                                a = this.list[i];
                                b = other[j];
                                polarity = (polarity ^ 1) ^ 2;
                                j++;
                                i++;
                                break;
                            } else {
                                break;
                            }
                        } else {
                            this.buffer[k] = a;
                            a = this.list[i];
                            polarity ^= 1;
                            i++;
                            k++;
                            break;
                        }
                    } else {
                        b = other[j];
                        polarity ^= 2;
                        j++;
                        continue;
                    }
                case 3:
                    if (a >= b) {
                        if (b >= a) {
                            if (a != HIGH) {
                                this.buffer[k] = a;
                                a = this.list[i];
                                b = other[j];
                                polarity = (polarity ^ 1) ^ 2;
                                j++;
                                i++;
                                k++;
                                break;
                            } else {
                                break;
                            }
                        } else {
                            this.buffer[k] = b;
                            b = other[j];
                            polarity ^= 2;
                            j++;
                            k++;
                            break;
                        }
                    } else {
                        this.buffer[k] = a;
                        a = this.list[i];
                        polarity ^= 1;
                        i++;
                        k++;
                        continue;
                    }
            }
        }
        int[] iArr = this.buffer;
        iArr[k] = HIGH;
        this.len = k + 1;
        int[] temp = this.list;
        this.list = iArr;
        this.buffer = temp;
        this.pat = null;
        return this;
    }

    private static final int max(int a, int b) {
        return a > b ? a : b;
    }

    private static final class NumericValueFilter implements Filter {
        double value;

        NumericValueFilter(double value2) {
            this.value = value2;
        }

        public boolean contains(int ch) {
            return UCharacter.getUnicodeNumericValue(ch) == this.value;
        }
    }

    private static final class GeneralCategoryMaskFilter implements Filter {
        int mask;

        GeneralCategoryMaskFilter(int mask2) {
            this.mask = mask2;
        }

        public boolean contains(int ch) {
            return ((1 << UCharacter.getType(ch)) & this.mask) != 0;
        }
    }

    private static final class IntPropertyFilter implements Filter {
        int prop;
        int value;

        IntPropertyFilter(int prop2, int value2) {
            this.prop = prop2;
            this.value = value2;
        }

        public boolean contains(int ch) {
            return UCharacter.getIntPropertyValue(ch, this.prop) == this.value;
        }
    }

    private static final class ScriptExtensionsFilter implements Filter {
        int script;

        ScriptExtensionsFilter(int script2) {
            this.script = script2;
        }

        public boolean contains(int c) {
            return UScript.hasScript(c, this.script);
        }
    }

    private static final class VersionFilter implements Filter {
        VersionInfo version;

        VersionFilter(VersionInfo version2) {
            this.version = version2;
        }

        public boolean contains(int ch) {
            VersionInfo v = UCharacter.getAge(ch);
            return !Utility.sameObjects(v, UnicodeSet.NO_VERSION) && v.compareTo(this.version) <= 0;
        }
    }

    private void applyFilter(Filter filter, UnicodeSet inclusions) {
        clear();
        int startHasProperty = -1;
        int limitRange = inclusions.getRangeCount();
        for (int j = 0; j < limitRange; j++) {
            int start = inclusions.getRangeStart(j);
            int end = inclusions.getRangeEnd(j);
            for (int ch = start; ch <= end; ch++) {
                if (filter.contains(ch)) {
                    if (startHasProperty < 0) {
                        startHasProperty = ch;
                    }
                } else if (startHasProperty >= 0) {
                    add_unchecked(startHasProperty, ch - 1);
                    startHasProperty = -1;
                }
            }
        }
        if (startHasProperty >= 0) {
            add_unchecked(startHasProperty, 1114111);
        }
    }

    private static String mungeCharName(String source) {
        String source2 = PatternProps.trimWhiteSpace(source);
        StringBuilder buf = null;
        for (int i = 0; i < source2.length(); i++) {
            char ch = source2.charAt(i);
            if (PatternProps.isWhiteSpace(ch)) {
                if (buf == null) {
                    buf = new StringBuilder().append(source2, 0, i);
                } else if (buf.charAt(buf.length() - 1) == ' ') {
                }
                ch = ' ';
            }
            if (buf != null) {
                buf.append(ch);
            }
        }
        return buf == null ? source2 : buf.toString();
    }

    public UnicodeSet applyIntPropertyValue(int prop, int value) {
        if (prop == 8192) {
            applyFilter(new GeneralCategoryMaskFilter(value), CharacterPropertiesImpl.getInclusionsForProperty(prop));
        } else if (prop == 28672) {
            applyFilter(new ScriptExtensionsFilter(value), CharacterPropertiesImpl.getInclusionsForProperty(prop));
        } else if (prop < 0 || prop >= 65) {
            if (4096 > prop || prop >= 4121) {
                throw new IllegalArgumentException("unsupported property " + prop);
            }
            applyFilter(new IntPropertyFilter(prop, value), CharacterPropertiesImpl.getInclusionsForProperty(prop));
        } else if (value == 0 || value == 1) {
            set(CharacterProperties.getBinaryPropertySet(prop));
            if (value == 0) {
                complement();
            }
        } else {
            clear();
        }
        return this;
    }

    public UnicodeSet applyPropertyAlias(String propertyAlias, String valueAlias) {
        return applyPropertyAlias(propertyAlias, valueAlias, (SymbolTable) null);
    }

    public UnicodeSet applyPropertyAlias(String propertyAlias, String valueAlias, SymbolTable symbols) {
        int v;
        int p;
        checkFrozen();
        boolean invert = false;
        if (symbols != null && (symbols instanceof XSymbolTable) && ((XSymbolTable) symbols).applyPropertyAlias(propertyAlias, valueAlias, this)) {
            return this;
        }
        XSymbolTable xSymbolTable = XSYMBOL_TABLE;
        if (xSymbolTable != null && xSymbolTable.applyPropertyAlias(propertyAlias, valueAlias, this)) {
            return this;
        }
        if (valueAlias.length() > 0) {
            p = UCharacter.getPropertyEnum(propertyAlias);
            if (p == 4101) {
                p = 8192;
            }
            if ((p < 0 || p >= 65) && ((p < 4096 || p >= 4121) && (p < 8192 || p >= 8193))) {
                switch (p) {
                    case 12288:
                        applyFilter(new NumericValueFilter(Double.parseDouble(PatternProps.trimWhiteSpace(valueAlias))), CharacterPropertiesImpl.getInclusionsForProperty(p));
                        return this;
                    case 16384:
                        applyFilter(new VersionFilter(VersionInfo.getInstance(mungeCharName(valueAlias))), CharacterPropertiesImpl.getInclusionsForProperty(p));
                        return this;
                    case 16389:
                        int ch = UCharacter.getCharFromExtendedName(mungeCharName(valueAlias));
                        if (ch != -1) {
                            clear();
                            add_unchecked(ch);
                            return this;
                        }
                        throw new IllegalArgumentException("Invalid character name");
                    case 16395:
                        throw new IllegalArgumentException("Unicode_1_Name (na1) not supported");
                    case 28672:
                        v = UCharacter.getPropertyValueEnum(4106, valueAlias);
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported property");
                }
            } else {
                try {
                    v = UCharacter.getPropertyValueEnum(p, valueAlias);
                } catch (IllegalArgumentException e) {
                    if (p == 4098 || p == 4112 || p == 4113) {
                        int v2 = Integer.parseInt(PatternProps.trimWhiteSpace(valueAlias));
                        if (v2 < 0 || v2 > 255) {
                            throw e;
                        }
                        v = v2;
                    } else {
                        throw e;
                    }
                }
            }
        } else {
            UPropertyAliases pnames = UPropertyAliases.INSTANCE;
            int v3 = pnames.getPropertyValueEnum(8192, propertyAlias);
            if (v3 == -1) {
                int v4 = pnames.getPropertyValueEnum(4106, propertyAlias);
                if (v4 == -1) {
                    int p2 = pnames.getPropertyEnum(propertyAlias);
                    if (p2 == -1) {
                        p2 = -1;
                    }
                    if (p2 >= 0 && p2 < 65) {
                        v = 1;
                        p = p2;
                    } else if (p2 != -1) {
                        throw new IllegalArgumentException("Missing property value");
                    } else if (UPropertyAliases.compare(ANY_ID, propertyAlias) == 0) {
                        set(0, 1114111);
                        return this;
                    } else if (UPropertyAliases.compare(ASCII_ID, propertyAlias) == 0) {
                        set(0, 127);
                        return this;
                    } else if (UPropertyAliases.compare(ASSIGNED, propertyAlias) == 0) {
                        invert = true;
                        p = 8192;
                        v = 1;
                    } else {
                        throw new IllegalArgumentException("Invalid property alias: " + propertyAlias + "=" + valueAlias);
                    }
                } else {
                    p = 4106;
                    v = v4;
                }
            } else {
                p = 8192;
                v = v3;
            }
        }
        applyIntPropertyValue(p, v);
        if (invert) {
            complement();
        }
        return this;
    }

    private static boolean resemblesPropertyPattern(String pattern, int pos) {
        if (pos + 5 > pattern.length()) {
            return false;
        }
        if (pattern.regionMatches(pos, "[:", 0, 2) || pattern.regionMatches(true, pos, "\\p", 0, 2) || pattern.regionMatches(pos, "\\N", 0, 2)) {
            return true;
        }
        return false;
    }

    private static boolean resemblesPropertyPattern(RuleCharacterIterator chars, int iterOpts) {
        boolean result = false;
        int iterOpts2 = iterOpts & -3;
        Object pos = chars.getPos((Object) null);
        int c = chars.next(iterOpts2);
        if (c == 91 || c == 92) {
            int d = chars.next(iterOpts2 & -5);
            boolean z = false;
            if (c != 91 ? d == 78 || d == 112 || d == 80 : d == 58) {
                z = true;
            }
            result = z;
        }
        chars.setPos(pos);
        return result;
    }

    private UnicodeSet applyPropertyPattern(String pattern, ParsePosition ppos, SymbolTable symbols) {
        int pos;
        String valueName;
        String propName;
        String str = pattern;
        int pos2 = ppos.getIndex();
        if (pos2 + 5 > pattern.length()) {
            return null;
        }
        boolean posix = false;
        boolean isName = false;
        boolean invert = false;
        boolean z = false;
        int i = 2;
        if (str.regionMatches(pos2, "[:", 0, 2)) {
            posix = true;
            pos = PatternProps.skipWhiteSpace(str, pos2 + 2);
            if (pos < pattern.length() && str.charAt(pos) == '^') {
                pos++;
                invert = true;
            }
        } else if (!pattern.regionMatches(true, pos2, "\\p", 0, 2) && !str.regionMatches(pos2, "\\N", 0, 2)) {
            return null;
        } else {
            char c = str.charAt(pos2 + 1);
            invert = c == 'P';
            if (c == 'N') {
                z = true;
            }
            isName = z;
            int pos3 = PatternProps.skipWhiteSpace(str, pos2 + 2);
            if (pos3 != pattern.length()) {
                int pos4 = pos3 + 1;
                if (str.charAt(pos3) != 123) {
                    ParsePosition parsePosition = ppos;
                    SymbolTable symbolTable = symbols;
                    int i2 = pos4;
                } else {
                    pos = pos4;
                }
            } else {
                ParsePosition parsePosition2 = ppos;
                SymbolTable symbolTable2 = symbols;
            }
            return null;
        }
        int close = str.indexOf(posix ? ":]" : "}", pos);
        if (close < 0) {
            return null;
        }
        int equals = str.indexOf(61, pos);
        if (equals < 0 || equals >= close || isName) {
            propName = str.substring(pos, close);
            valueName = "";
            if (isName) {
                valueName = propName;
                propName = "na";
            }
        } else {
            propName = str.substring(pos, equals);
            valueName = str.substring(equals + 1, close);
        }
        applyPropertyAlias(propName, valueName, symbols);
        if (invert) {
            complement();
        }
        if (!posix) {
            i = 1;
        }
        ppos.setIndex(i + close);
        return this;
    }

    private void applyPropertyPattern(RuleCharacterIterator chars, Appendable rebuiltPat, SymbolTable symbols) {
        String patStr = chars.lookahead();
        ParsePosition pos = new ParsePosition(0);
        applyPropertyPattern(patStr, pos, symbols);
        if (pos.getIndex() == 0) {
            syntaxError(chars, "Invalid property pattern");
        }
        chars.jumpahead(pos.getIndex());
        append(rebuiltPat, patStr.substring(0, pos.getIndex()));
    }

    private static final void addCaseMapping(UnicodeSet set, int result, StringBuilder full) {
        if (result < 0) {
            return;
        }
        if (result > 31) {
            set.add(result);
            return;
        }
        set.add((CharSequence) full.toString());
        full.setLength(0);
    }

    public UnicodeSet closeOver(int attribute) {
        checkFrozen();
        if ((attribute & 6) != 0) {
            UCaseProps csp = UCaseProps.INSTANCE;
            UnicodeSet foldSet = new UnicodeSet(this);
            ULocale root = ULocale.ROOT;
            if ((attribute & 2) != 0 && foldSet.hasStrings()) {
                foldSet.strings.clear();
            }
            int n = getRangeCount();
            StringBuilder full = new StringBuilder();
            for (int i = 0; i < n; i++) {
                int start = getRangeStart(i);
                int end = getRangeEnd(i);
                if ((attribute & 2) != 0) {
                    for (int cp = start; cp <= end; cp++) {
                        csp.addCaseClosure(cp, foldSet);
                    }
                } else {
                    for (int cp2 = start; cp2 <= end; cp2++) {
                        addCaseMapping(foldSet, csp.toFullLower(cp2, (UCaseProps.ContextIterator) null, full, 1), full);
                        addCaseMapping(foldSet, csp.toFullTitle(cp2, (UCaseProps.ContextIterator) null, full, 1), full);
                        addCaseMapping(foldSet, csp.toFullUpper(cp2, (UCaseProps.ContextIterator) null, full, 1), full);
                        addCaseMapping(foldSet, csp.toFullFolding(cp2, full, 0), full);
                    }
                }
            }
            if (hasStrings() != 0) {
                if ((attribute & 2) != 0) {
                    for (String s : this.strings) {
                        String str = UCharacter.foldCase(s, 0);
                        if (!csp.addStringCaseClosure(str, foldSet)) {
                            foldSet.add((CharSequence) str);
                        }
                    }
                } else {
                    BreakIterator bi = BreakIterator.getWordInstance(root);
                    for (String str2 : this.strings) {
                        foldSet.add((CharSequence) UCharacter.toLowerCase(root, str2));
                        foldSet.add((CharSequence) UCharacter.toTitleCase(root, str2, bi));
                        foldSet.add((CharSequence) UCharacter.toUpperCase(root, str2));
                        foldSet.add((CharSequence) UCharacter.foldCase(str2, 0));
                    }
                }
            }
            set(foldSet);
        }
        return this;
    }

    public static abstract class XSymbolTable implements SymbolTable {
        public UnicodeMatcher lookupMatcher(int i) {
            return null;
        }

        public boolean applyPropertyAlias(String propertyName, String propertyValue, UnicodeSet result) {
            return false;
        }

        public char[] lookup(String s) {
            return null;
        }

        public String parseReference(String text, ParsePosition pos, int limit) {
            return null;
        }
    }

    public boolean isFrozen() {
        return (this.bmpSet == null && this.stringSpan == null) ? false : true;
    }

    public UnicodeSet freeze() {
        if (!isFrozen()) {
            compact();
            if (hasStrings()) {
                this.stringSpan = new UnicodeSetStringSpan(this, new ArrayList(this.strings), 127);
            }
            if (this.stringSpan == null || !this.stringSpan.needsStringSpanUTF16()) {
                this.bmpSet = new BMPSet(this.list, this.len);
            }
        }
        return this;
    }

    public int span(CharSequence s, SpanCondition spanCondition) {
        return span(s, 0, spanCondition);
    }

    public int span(CharSequence s, int start, SpanCondition spanCondition) {
        int end = s.length();
        if (start < 0) {
            start = 0;
        } else if (start >= end) {
            return end;
        }
        if (this.bmpSet != null) {
            return this.bmpSet.span(s, start, spanCondition, (OutputInt) null);
        }
        if (this.stringSpan != null) {
            return this.stringSpan.span(s, start, spanCondition);
        }
        if (hasStrings()) {
            UnicodeSetStringSpan strSpan = new UnicodeSetStringSpan(this, new ArrayList(this.strings), spanCondition == SpanCondition.NOT_CONTAINED ? 33 : 34);
            if (strSpan.needsStringSpanUTF16()) {
                return strSpan.span(s, start, spanCondition);
            }
        }
        return spanCodePointsAndCount(s, start, spanCondition, (OutputInt) null);
    }

    @Deprecated
    public int spanAndCount(CharSequence s, int start, SpanCondition spanCondition, OutputInt outCount) {
        if (outCount != null) {
            int end = s.length();
            if (start < 0) {
                start = 0;
            } else if (start >= end) {
                return end;
            }
            if (this.stringSpan != null) {
                return this.stringSpan.spanAndCount(s, start, spanCondition, outCount);
            }
            if (this.bmpSet != null) {
                return this.bmpSet.span(s, start, spanCondition, outCount);
            }
            if (!hasStrings()) {
                return spanCodePointsAndCount(s, start, spanCondition, outCount);
            }
            return new UnicodeSetStringSpan(this, new ArrayList(this.strings), (spanCondition == SpanCondition.NOT_CONTAINED ? 33 : 34) | 64).spanAndCount(s, start, spanCondition, outCount);
        }
        throw new IllegalArgumentException("outCount must not be null");
    }

    private int spanCodePointsAndCount(CharSequence s, int start, SpanCondition spanCondition, OutputInt outCount) {
        boolean spanContained = spanCondition != SpanCondition.NOT_CONTAINED;
        int next = start;
        int length = s.length();
        int count = 0;
        do {
            int c = Character.codePointAt(s, next);
            if (spanContained != contains(c)) {
                break;
            }
            count++;
            next += Character.charCount(c);
        } while (next < length);
        if (outCount != null) {
            outCount.value = count;
        }
        return next;
    }

    public int spanBack(CharSequence s, SpanCondition spanCondition) {
        return spanBack(s, s.length(), spanCondition);
    }

    public int spanBack(CharSequence s, int fromIndex, SpanCondition spanCondition) {
        int c;
        boolean spanContained = false;
        if (fromIndex <= 0) {
            return 0;
        }
        if (fromIndex > s.length()) {
            fromIndex = s.length();
        }
        if (this.bmpSet != null) {
            return this.bmpSet.spanBack(s, fromIndex, spanCondition);
        }
        if (this.stringSpan != null) {
            return this.stringSpan.spanBack(s, fromIndex, spanCondition);
        }
        if (hasStrings()) {
            UnicodeSetStringSpan strSpan = new UnicodeSetStringSpan(this, new ArrayList(this.strings), spanCondition == SpanCondition.NOT_CONTAINED ? 17 : 18);
            if (strSpan.needsStringSpanUTF16()) {
                return strSpan.spanBack(s, fromIndex, spanCondition);
            }
        }
        if (spanCondition != SpanCondition.NOT_CONTAINED) {
            spanContained = true;
        }
        int prev = fromIndex;
        do {
            c = Character.codePointBefore(s, prev);
            if (spanContained != contains(c) || (prev = prev - Character.charCount(c)) <= 0) {
                return prev;
            }
            c = Character.codePointBefore(s, prev);
            break;
        } while ((prev = prev - Character.charCount(c)) <= 0);
        return prev;
    }

    public UnicodeSet cloneAsThawed() {
        UnicodeSet result = new UnicodeSet(this);
        if (!result.isFrozen()) {
            return result;
        }
        throw new AssertionError();
    }

    private void checkFrozen() {
        if (isFrozen()) {
            throw new UnsupportedOperationException("Attempt to modify frozen object");
        }
    }

    public static class EntryRange {
        public int codepoint;
        public int codepointEnd;

        EntryRange() {
        }

        public String toString() {
            StringBuilder sb;
            StringBuilder b = new StringBuilder();
            int i = this.codepoint;
            if (i == this.codepointEnd) {
                sb = (StringBuilder) UnicodeSet._appendToPat(b, i, false);
            } else {
                sb = (StringBuilder) UnicodeSet._appendToPat(((StringBuilder) UnicodeSet._appendToPat(b, i, false)).append('-'), this.codepointEnd, false);
            }
            return sb.toString();
        }
    }

    public Iterable<EntryRange> ranges() {
        return new EntryRangeIterable();
    }

    private class EntryRangeIterable implements Iterable<EntryRange> {
        private EntryRangeIterable() {
        }

        public Iterator<EntryRange> iterator() {
            return new EntryRangeIterator();
        }
    }

    private class EntryRangeIterator implements Iterator<EntryRange> {
        int pos;
        EntryRange result;

        private EntryRangeIterator() {
            this.result = new EntryRange();
        }

        public boolean hasNext() {
            return this.pos < UnicodeSet.this.len - 1;
        }

        public EntryRange next() {
            if (this.pos < UnicodeSet.this.len - 1) {
                EntryRange entryRange = this.result;
                int[] access$500 = UnicodeSet.this.list;
                int i = this.pos;
                this.pos = i + 1;
                entryRange.codepoint = access$500[i];
                EntryRange entryRange2 = this.result;
                int[] access$5002 = UnicodeSet.this.list;
                int i2 = this.pos;
                this.pos = i2 + 1;
                entryRange2.codepointEnd = access$5002[i2] - 1;
                return this.result;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public Iterator<String> iterator() {
        return new UnicodeSetIterator2(this);
    }

    private static class UnicodeSetIterator2 implements Iterator<String> {
        private char[] buffer;
        private int current;
        private int item;
        private int len;
        private int limit;
        private int[] sourceList;
        private SortedSet<String> sourceStrings;
        private Iterator<String> stringIterator;

        UnicodeSetIterator2(UnicodeSet source) {
            int access$400 = source.len - 1;
            this.len = access$400;
            if (access$400 > 0) {
                this.sourceStrings = source.strings;
                int[] access$500 = source.list;
                this.sourceList = access$500;
                int i = this.item;
                int i2 = i + 1;
                this.item = i2;
                this.current = access$500[i];
                this.item = i2 + 1;
                this.limit = access$500[i2];
                return;
            }
            this.stringIterator = source.strings.iterator();
            this.sourceList = null;
        }

        public boolean hasNext() {
            return this.sourceList != null || this.stringIterator.hasNext();
        }

        public String next() {
            int[] iArr = this.sourceList;
            if (iArr == null) {
                return this.stringIterator.next();
            }
            int codepoint = this.current;
            int i = codepoint + 1;
            this.current = i;
            if (i >= this.limit) {
                int i2 = this.item;
                if (i2 >= this.len) {
                    this.stringIterator = this.sourceStrings.iterator();
                    this.sourceList = null;
                } else {
                    int i3 = i2 + 1;
                    this.item = i3;
                    this.current = iArr[i2];
                    this.item = i3 + 1;
                    this.limit = iArr[i3];
                }
            }
            if (codepoint <= 65535) {
                return String.valueOf((char) codepoint);
            }
            if (this.buffer == null) {
                this.buffer = new char[2];
            }
            int offset = codepoint - 65536;
            char[] cArr = this.buffer;
            cArr[0] = (char) ((offset >>> 10) + 55296);
            cArr[1] = (char) ((offset & BrightnessUtils.GAMMA_SPACE_MAX) + UTF16.TRAIL_SURROGATE_MIN_VALUE);
            return String.valueOf(cArr);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public <T extends CharSequence> boolean containsAll(Iterable<T> collection) {
        for (T o : collection) {
            if (!contains((CharSequence) o)) {
                return false;
            }
        }
        return true;
    }

    public <T extends CharSequence> boolean containsNone(Iterable<T> collection) {
        for (T o : collection) {
            if (contains((CharSequence) o)) {
                return false;
            }
        }
        return true;
    }

    public final <T extends CharSequence> boolean containsSome(Iterable<T> collection) {
        return !containsNone(collection);
    }

    public <T extends CharSequence> UnicodeSet addAll(T... collection) {
        checkFrozen();
        for (T str : collection) {
            add((CharSequence) str);
        }
        return this;
    }

    public <T extends CharSequence> UnicodeSet removeAll(Iterable<T> collection) {
        checkFrozen();
        for (T o : collection) {
            remove((CharSequence) o);
        }
        return this;
    }

    public <T extends CharSequence> UnicodeSet retainAll(Iterable<T> collection) {
        checkFrozen();
        UnicodeSet toRetain = new UnicodeSet();
        toRetain.addAll((Iterable<?>) collection);
        retainAll(toRetain);
        return this;
    }

    public int compareTo(UnicodeSet o) {
        return compareTo(o, ComparisonStyle.SHORTER_FIRST);
    }

    public int compareTo(UnicodeSet o, ComparisonStyle style) {
        int compareResult;
        int diff;
        boolean z = false;
        if (style == ComparisonStyle.LEXICOGRAPHIC || (diff = size() - o.size()) == 0) {
            int i = 0;
            while (true) {
                int[] iArr = this.list;
                int i2 = iArr[i];
                int[] iArr2 = o.list;
                int i3 = i2 - iArr2[i];
                int result = i3;
                if (i3 != 0) {
                    if (iArr[i] == HIGH) {
                        if (!hasStrings()) {
                            return 1;
                        }
                        return compare((CharSequence) this.strings.first(), o.list[i]);
                    } else if (iArr2[i] != HIGH) {
                        return (i & 1) == 0 ? result : -result;
                    } else {
                        if (o.hasStrings() && (compareResult = compare((CharSequence) o.strings.first(), this.list[i])) <= 0) {
                            return compareResult < 0 ? 1 : 0;
                        }
                        return -1;
                    }
                } else if (iArr[i] == HIGH) {
                    return compare(this.strings, o.strings);
                } else {
                    i++;
                }
            }
        } else {
            boolean z2 = diff < 0;
            if (style == ComparisonStyle.SHORTER_FIRST) {
                z = true;
            }
            if (z2 == z) {
                return -1;
            }
            return 1;
        }
    }

    public int compareTo(Iterable<String> other) {
        return compare(this, (UnicodeSet) other);
    }

    public static int compare(CharSequence string, int codePoint) {
        return CharSequences.compare(string, codePoint);
    }

    public static int compare(int codePoint, CharSequence string) {
        return -CharSequences.compare(string, codePoint);
    }

    public static <T extends Comparable<T>> int compare(Iterable<T> collection1, Iterable<T> collection2) {
        return compare(collection1.iterator(), collection2.iterator());
    }

    @Deprecated
    public static <T extends Comparable<T>> int compare(Iterator<T> first, Iterator<T> other) {
        while (first.hasNext()) {
            if (!other.hasNext()) {
                return 1;
            }
            int result = ((Comparable) first.next()).compareTo((Comparable) other.next());
            if (result != 0) {
                return result;
            }
        }
        return other.hasNext() ? -1 : 0;
    }

    public static <T extends Comparable<T>> int compare(Collection<T> collection1, Collection<T> collection2, ComparisonStyle style) {
        int diff;
        if (style == ComparisonStyle.LEXICOGRAPHIC || (diff = collection1.size() - collection2.size()) == 0) {
            return compare(collection1, collection2);
        }
        boolean z = false;
        boolean z2 = diff < 0;
        if (style == ComparisonStyle.SHORTER_FIRST) {
            z = true;
        }
        return z2 == z ? -1 : 1;
    }

    public static <T, U extends Collection<T>> U addAllTo(Iterable<T> source, U target) {
        for (T item : source) {
            target.add(item);
        }
        return target;
    }

    public static <T> T[] addAllTo(Iterable<T> source, T[] target) {
        int i = 0;
        for (T item : source) {
            target[i] = item;
            i++;
        }
        return target;
    }

    public Collection<String> strings() {
        if (hasStrings()) {
            return Collections.unmodifiableSortedSet(this.strings);
        }
        return EMPTY_STRINGS;
    }

    @Deprecated
    public static int getSingleCodePoint(CharSequence s) {
        return CharSequences.getSingleCodePoint(s);
    }

    @Deprecated
    public UnicodeSet addBridges(UnicodeSet dontCare) {
        UnicodeSetIterator it = new UnicodeSetIterator(new UnicodeSet(this).complement());
        while (it.nextRange()) {
            if (!(it.codepoint == 0 || it.codepoint == UnicodeSetIterator.IS_STRING || it.codepointEnd == 1114111 || !dontCare.contains(it.codepoint, it.codepointEnd))) {
                add(it.codepoint, it.codepointEnd);
            }
        }
        return this;
    }

    @Deprecated
    public int findIn(CharSequence value, int fromIndex, boolean findNot) {
        while (fromIndex < value.length()) {
            int cp = UTF16.charAt(value, fromIndex);
            if (contains(cp) != findNot) {
                break;
            }
            fromIndex += UTF16.getCharCount(cp);
        }
        return fromIndex;
    }

    @Deprecated
    public int findLastIn(CharSequence value, int fromIndex, boolean findNot) {
        int fromIndex2 = fromIndex - 1;
        while (fromIndex2 >= 0) {
            int cp = UTF16.charAt(value, fromIndex2);
            if (contains(cp) != findNot) {
                break;
            }
            fromIndex2 -= UTF16.getCharCount(cp);
        }
        if (fromIndex2 < 0) {
            return -1;
        }
        return fromIndex2;
    }

    @Deprecated
    public String stripFrom(CharSequence source, boolean matches) {
        StringBuilder result = new StringBuilder();
        int pos = 0;
        while (pos < source.length()) {
            int inside = findIn(source, pos, !matches);
            result.append(source.subSequence(pos, inside));
            pos = findIn(source, inside, matches);
        }
        return result.toString();
    }

    @Deprecated
    public static XSymbolTable getDefaultXSymbolTable() {
        return XSYMBOL_TABLE;
    }

    @Deprecated
    public static void setDefaultXSymbolTable(XSymbolTable xSymbolTable) {
        CharacterPropertiesImpl.clear();
        XSYMBOL_TABLE = xSymbolTable;
    }
}
