package com.ibm.icu.text;

import android.support.p001v4.view.InputDeviceCompat;
import com.ibm.icu.impl.BMPSet;
import com.ibm.icu.impl.CharacterPropertiesImpl;
import com.ibm.icu.impl.PatternProps;
import com.ibm.icu.impl.RuleCharacterIterator;
import com.ibm.icu.impl.SortedSetRelation;
import com.ibm.icu.impl.StringRange;
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
import kotlin.text.Typography;

/* loaded from: classes.dex */
public class UnicodeSet extends UnicodeFilter implements Iterable<String>, Comparable<UnicodeSet>, Freezable<UnicodeSet> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int ADD_CASE_MAPPINGS = 4;
    private static final String ANY_ID = "ANY";
    private static final String ASCII_ID = "ASCII";
    private static final String ASSIGNED = "Assigned";
    public static final int CASE = 2;
    public static final int CASE_INSENSITIVE = 2;
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
    private static final int SETMODE0_NONE = 0;
    private static final int SETMODE1_UNICODESET = 1;
    private static final int SETMODE2_PROPERTYPAT = 2;
    private static final int SETMODE3_PREPARSED = 3;
    private volatile BMPSet bmpSet;
    private int[] buffer;
    private int len;
    private int[] list;
    private String pat;
    private int[] rangeList;
    private volatile UnicodeSetStringSpan stringSpan;
    SortedSet<String> strings;
    private static final SortedSet<String> EMPTY_STRINGS = Collections.unmodifiableSortedSet(new TreeSet());
    public static final UnicodeSet EMPTY = new UnicodeSet().m87freeze();
    public static final UnicodeSet ALL_CODE_POINTS = new UnicodeSet(0, 1114111).m87freeze();
    private static XSymbolTable XSYMBOL_TABLE = null;
    private static final VersionInfo NO_VERSION = VersionInfo.getInstance(0, 0, 0, 0);

    /* loaded from: classes.dex */
    public enum ComparisonStyle {
        SHORTER_FIRST,
        LEXICOGRAPHIC,
        LONGER_FIRST
    }

    /* loaded from: classes.dex */
    private interface Filter {
        boolean contains(int i);
    }

    /* loaded from: classes.dex */
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
        if ((pairs.length & 1) != 0) {
            throw new IllegalArgumentException("Must have even number of integers");
        }
        int[] iArr = new int[pairs.length + 1];
        this.list = iArr;
        this.len = iArr.length;
        int last = -1;
        int limit = 0;
        while (limit < pairs.length) {
            int start = pairs[limit];
            if (last >= start) {
                throw new IllegalArgumentException("Must be monotonically increasing.");
            }
            int[] iArr2 = this.list;
            int i = limit + 1;
            iArr2[limit] = start;
            int limit2 = pairs[i] + 1;
            if (start >= limit2) {
                throw new IllegalArgumentException("Must be monotonically increasing.");
            }
            last = limit2;
            iArr2[i] = limit2;
            limit = i + 1;
        }
        this.list[limit] = HIGH;
    }

    public UnicodeSet(String pattern) {
        this();
        applyPattern(pattern, null, null, 1);
    }

    public UnicodeSet(String pattern, boolean ignoreWhitespace) {
        this();
        applyPattern(pattern, null, null, ignoreWhitespace ? 1 : 0);
    }

    public UnicodeSet(String pattern, int options) {
        this();
        applyPattern(pattern, null, null, options);
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
            this.strings = new TreeSet((SortedSet) other.strings);
        } else {
            this.strings = EMPTY_STRINGS;
        }
        return this;
    }

    public final UnicodeSet applyPattern(String pattern) {
        checkFrozen();
        return applyPattern(pattern, null, null, 1);
    }

    public UnicodeSet applyPattern(String pattern, boolean ignoreWhitespace) {
        checkFrozen();
        return applyPattern(pattern, null, null, ignoreWhitespace ? 1 : 0);
    }

    public UnicodeSet applyPattern(String pattern, int options) {
        checkFrozen();
        return applyPattern(pattern, null, null, options);
    }

    public static boolean resemblesPattern(String pattern, int pos) {
        return (pos + 1 < pattern.length() && pattern.charAt(pos) == '[') || resemblesPropertyPattern(pattern, pos);
    }

    private static void appendCodePoint(Appendable app, int c) {
        if (c < 0 || c > 1114111) {
            throw new AssertionError();
        }
        try {
            if (c <= 65535) {
                app.append((char) c);
            } else {
                app.append(UTF16.getLeadSurrogate(c)).append(UTF16.getTrailSurrogate(c));
            }
        } catch (IOException e) {
            throw new ICUUncheckedIOException(e);
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

    /* JADX INFO: Access modifiers changed from: private */
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

    @Override // com.ibm.icu.text.UnicodeMatcher
    public String toPattern(boolean escapeUnprintable) {
        String str = this.pat;
        if (str != null && !escapeUnprintable) {
            return str;
        }
        StringBuilder result = new StringBuilder();
        return ((StringBuilder) _toPattern(result, escapeUnprintable)).toString();
    }

    private <T extends Appendable> T _toPattern(T result, boolean escapeUnprintable) {
        String str = this.pat;
        if (str == null) {
            return (T) appendNewPattern(result, escapeUnprintable, true);
        }
        try {
            if (!escapeUnprintable) {
                result.append(str);
                return result;
            }
            boolean oddNumberOfBackslashes = false;
            int i = 0;
            while (i < this.pat.length()) {
                int c = this.pat.codePointAt(i);
                i += Character.charCount(c);
                if (Utility.isUnprintable(c)) {
                    Utility.escapeUnprintable(result, c);
                    oddNumberOfBackslashes = false;
                } else if (!oddNumberOfBackslashes && c == 92) {
                    oddNumberOfBackslashes = true;
                } else {
                    if (oddNumberOfBackslashes) {
                        result.append('\\');
                    }
                    appendCodePoint(result, c);
                    oddNumberOfBackslashes = false;
                }
            }
            if (oddNumberOfBackslashes) {
                result.append('\\');
            }
            return result;
        } catch (IOException e) {
            throw new ICUUncheckedIOException(e);
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

    boolean hasStrings() {
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

    @Override // com.ibm.icu.text.UnicodeMatcher
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
        if (hasStrings()) {
            for (String s : this.strings) {
                int c = UTF16.charAt(s, 0);
                if ((c & 255) == v) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.ibm.icu.text.UnicodeFilter, com.ibm.icu.text.UnicodeMatcher
    public int matches(Replaceable text, int[] offset, int limit, boolean incremental) {
        if (offset[0] == limit) {
            if (contains(65535)) {
                return incremental ? 1 : 2;
            }
            return 0;
        }
        if (hasStrings()) {
            boolean forward = offset[0] < limit;
            char firstChar = text.charAt(offset[0]);
            int highWaterLength = 0;
            for (String trial : this.strings) {
                char c = trial.charAt(forward ? 0 : trial.length() - 1);
                if (forward && c > firstChar) {
                    break;
                } else if (c == firstChar) {
                    int length = matchRest(text, offset[0], limit, trial);
                    if (incremental) {
                        int maxLen = forward ? limit - offset[0] : offset[0] - limit;
                        if (length == maxLen) {
                            return 1;
                        }
                    }
                    int maxLen2 = trial.length();
                    if (length == maxLen2) {
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
            }
            if (highWaterLength != 0) {
                offset[0] = offset[0] + (forward ? highWaterLength : -highWaterLength);
                return 2;
            }
        }
        return super.matches(text, offset, limit, incremental);
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
            maxLen = start - limit;
            if (maxLen > slen) {
                maxLen = slen;
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

    @Deprecated
    public int matchesAt(CharSequence text, int offset) {
        int lastLen = -1;
        if (hasStrings()) {
            char firstChar = text.charAt(offset);
            String trial = null;
            Iterator<String> it = this.strings.iterator();
            while (true) {
                if (it.hasNext()) {
                    String trial2 = it.next();
                    trial = trial2;
                    char firstStringChar = trial.charAt(0);
                    if (firstStringChar >= firstChar && firstStringChar > firstChar) {
                        break;
                    }
                } else {
                    while (true) {
                        int tempLen = matchesAt(text, offset, trial);
                        if (lastLen > tempLen) {
                            break;
                        }
                        lastLen = tempLen;
                        if (!it.hasNext()) {
                            break;
                        }
                        String trial3 = it.next();
                        trial = trial3;
                    }
                }
            }
        }
        if (lastLen < 2) {
            int cp = UTF16.charAt(text, offset);
            if (contains(cp)) {
                lastLen = UTF16.getCharCount(cp);
            }
        }
        return offset + lastLen;
    }

    private static int matchesAt(CharSequence text, int offsetInText, CharSequence substring) {
        int len = substring.length();
        int textLength = text.length();
        if (textLength + offsetInText > len) {
            return -1;
        }
        int i = 0;
        int j = offsetInText;
        while (i < len) {
            char pc = substring.charAt(i);
            char tc = text.charAt(j);
            if (pc != tc) {
                return -1;
            }
            i++;
            j++;
        }
        return i;
    }

    @Override // com.ibm.icu.text.UnicodeMatcher
    public void addMatchSetTo(UnicodeSet toUnionTo) {
        toUnionTo.addAll(this);
    }

    public int indexOf(int c) {
        if (c < 0 || c > 1114111) {
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex(c, 6));
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
        if (index >= 0) {
            int len2 = this.len & (-2);
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
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex(start, 6));
        }
        if (end < 0 || end > 1114111) {
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex(end, 6));
        }
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

    public final UnicodeSet add(int c) {
        checkFrozen();
        return add_unchecked(c);
    }

    private final UnicodeSet add_unchecked(int c) {
        if (c < 0 || c > 1114111) {
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex(c, 6));
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
        } else if (i > 0 && c == iArr[i - 1]) {
            int i3 = i - 1;
            iArr[i3] = iArr[i3] + 1;
        } else {
            int i4 = this.len;
            if (i4 + 2 > iArr.length) {
                int[] temp = new int[nextCapacity(i4 + 2)];
                if (i != 0) {
                    System.arraycopy(this.list, 0, temp, 0, i);
                }
                System.arraycopy(this.list, i, temp, i + 2, this.len - i);
                this.list = temp;
            } else {
                System.arraycopy(iArr, i, iArr, i + 2, i4 - i);
            }
            int[] iArr4 = this.list;
            iArr4[i] = c;
            iArr4[i + 1] = c + 1;
            this.len += 2;
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
        }
        if (s.length() > 2) {
            return -1;
        }
        if (s.length() == 1) {
            return s.charAt(0);
        }
        int cp = UTF16.charAt(s, 0);
        if (cp > 65535) {
            return cp;
        }
        return -1;
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
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex(start, 6));
        }
        if (end < 0 || end > 1114111) {
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex(end, 6));
        }
        if (start <= end) {
            retain(range(start, end), 2, 0);
        } else {
            clear();
        }
        return this;
    }

    public final UnicodeSet retain(int c) {
        return retain(c, c);
    }

    public final UnicodeSet retain(CharSequence cs) {
        int cp = getSingleCP(cs);
        if (cp < 0) {
            checkFrozen();
            String s = cs.toString();
            boolean isIn = this.strings.contains(s);
            if (isIn && size() == 1) {
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
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex(start, 6));
        }
        if (end < 0 || end > 1114111) {
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex(end, 6));
        }
        if (start <= end) {
            retain(range(start, end), 2, 2);
        }
        return this;
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
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex(start, 6));
        }
        if (end < 0 || end > 1114111) {
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex(end, 6));
        }
        if (start <= end) {
            xor(range(start, end), 2, 0);
        }
        this.pat = null;
        return this;
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

    @Override // com.ibm.icu.text.UnicodeFilter
    public boolean contains(int c) {
        if (c < 0 || c > 1114111) {
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex(c, 6));
        }
        if (this.bmpSet != null) {
            return this.bmpSet.contains(c);
        }
        if (this.stringSpan != null) {
            return this.stringSpan.contains(c);
        }
        int i = findCodePoint(c);
        return (i & 1) != 0;
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
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex(start, 6));
        }
        if (end < 0 || end > 1114111) {
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex(end, 6));
        }
        int i = findCodePoint(start);
        return (i & 1) != 0 && end < this.list[i];
    }

    public final boolean contains(CharSequence s) {
        int cp = getSingleCP(s);
        if (cp < 0) {
            return this.strings.contains(s.toString());
        }
        return contains(cp);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x003c, code lost:
        if (r18.strings.containsAll(r19.strings) != false) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x003e, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003f, code lost:
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x005a, code lost:
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean containsAll(UnicodeSet b) {
        int[] listB = b.list;
        boolean needA = true;
        boolean needB = true;
        int startA = 0;
        int startB = 0;
        int aLen = this.len - 1;
        int bLen = b.len - 1;
        int aPtr = 0;
        int bPtr = 0;
        int limitA = 0;
        int limitB = 0;
        while (true) {
            if (needA) {
                if (startA >= aLen) {
                    if (!needB || startB < bLen) {
                        return false;
                    }
                } else {
                    int[] iArr = this.list;
                    int aPtr2 = startA + 1;
                    int startA2 = iArr[startA];
                    int startA3 = aPtr2 + 1;
                    limitA = iArr[aPtr2];
                    aPtr = startA2;
                    startA = startA3;
                }
            }
            if (needB) {
                if (startB >= bLen) {
                    break;
                }
                int bPtr2 = startB + 1;
                int startB2 = listB[startB];
                int startB3 = bPtr2 + 1;
                limitB = listB[bPtr2];
                bPtr = startB2;
                startB = startB3;
            }
            if (bPtr >= limitA) {
                needA = true;
                needB = false;
            } else if (bPtr < aPtr || limitB > limitA) {
                break;
            } else {
                needA = false;
                needB = true;
            }
        }
    }

    public boolean containsAll(String s) {
        int i = 0;
        while (i < s.length()) {
            int cp = UTF16.charAt(s, i);
            if (contains(cp)) {
                i += UTF16.getCharCount(cp);
            } else if (hasStrings()) {
                return containsAll(s, 0);
            } else {
                return false;
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
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex(start, 6));
        }
        if (end < 0 || end > 1114111) {
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex(end, 6));
        }
        int i = -1;
        do {
            iArr = this.list;
            i++;
        } while (start >= iArr[i]);
        return (i & 1) == 0 && end < iArr[i];
    }

    public boolean containsNone(UnicodeSet b) {
        int[] listB = b.list;
        boolean needA = true;
        boolean needB = true;
        int startA = 0;
        int startB = 0;
        int aLen = this.len - 1;
        int bLen = b.len - 1;
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
                int startA2 = iArr[startA];
                int startA3 = aPtr2 + 1;
                limitA = iArr[aPtr2];
                aPtr = startA2;
                startA = startA3;
            }
            if (needB) {
                if (startB >= bLen) {
                    break;
                }
                int bPtr2 = startB + 1;
                int startB2 = listB[startB];
                int startB3 = bPtr2 + 1;
                limitB = listB[bPtr2];
                bPtr = startB2;
                startB = startB3;
            }
            if (bPtr >= limitA) {
                needA = true;
                needB = false;
            } else if (aPtr >= limitB) {
                needA = false;
                needB = true;
            } else {
                return false;
            }
        }
        return SortedSetRelation.hasRelation(this.strings, 5, b.strings);
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
                this.strings = new TreeSet((SortedSet) c.strings);
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
                this.strings = new TreeSet((SortedSet) c.strings);
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

    /* JADX WARN: Code restructure failed: missing block: B:190:0x03d3, code lost:
        r15 = r17;
     */
    /* JADX WARN: Removed duplicated region for block: B:172:0x037d  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0380  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x038c  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x03b9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void applyPattern(RuleCharacterIterator chars, SymbolTable symbols, Appendable rebuiltPat, int options, int depth) {
        int opts;
        boolean usePat;
        int mode;
        Object backup;
        char c;
        UnicodeSet nested;
        UnicodeSet nested2;
        UnicodeSet nested3;
        char op;
        int lastChar;
        UnicodeSet scratch;
        String lastString;
        boolean ok;
        int c2;
        UnicodeSet scratch2;
        String lastString2;
        UnicodeMatcher m;
        SymbolTable symbolTable = symbols;
        int i = depth;
        if (i > 100) {
            syntaxError(chars, "Pattern nested too deeply");
        }
        if ((options & 1) == 0) {
            opts = 3;
        } else {
            int opts2 = 3 | 4;
            opts = opts2;
        }
        StringBuilder patBuf = new StringBuilder();
        UnicodeSet scratch3 = null;
        Object backup2 = null;
        char c3 = 0;
        clear();
        boolean usePat2 = false;
        int mode2 = 0;
        int mode3 = 0;
        char op2 = 0;
        StringBuilder buf = null;
        boolean invert = false;
        String lastString3 = null;
        while (true) {
            String lastString4 = lastString3;
            if (mode2 != 2 && !chars.atEnd()) {
                int c4 = 0;
                boolean literal = false;
                UnicodeSet nested4 = null;
                int setMode = 0;
                if (resemblesPropertyPattern(chars, opts)) {
                    setMode = 2;
                    mode = mode2;
                    backup = backup2;
                } else {
                    Object backup3 = chars.getPos(backup2);
                    int c5 = chars.next(opts);
                    literal = chars.isEscaped();
                    if (c5 == 91 && !literal) {
                        if (mode2 == 1) {
                            chars.setPos(backup3);
                            setMode = 1;
                            mode = mode2;
                            c4 = c5;
                            backup = backup3;
                        } else {
                            mode2 = 1;
                            patBuf.append('[');
                            Object backup4 = chars.getPos(backup3);
                            int c6 = chars.next(opts);
                            boolean literal2 = chars.isEscaped();
                            if (c6 == 94 && !literal2) {
                                invert = true;
                                patBuf.append('^');
                                Object backup5 = chars.getPos(backup4);
                                int c7 = chars.next(opts);
                                chars.isEscaped();
                                backup2 = backup5;
                                c4 = c7;
                            } else {
                                backup2 = backup4;
                                c4 = c6;
                            }
                            if (c4 == 45) {
                                literal = true;
                                mode = 1;
                                backup = backup2;
                            } else {
                                chars.setPos(backup2);
                                lastString3 = lastString4;
                            }
                        }
                    } else {
                        if (symbolTable != null && (m = symbolTable.lookupMatcher(c5)) != null) {
                            try {
                                nested4 = (UnicodeSet) m;
                                setMode = 3;
                                mode = mode2;
                                c4 = c5;
                                backup = backup3;
                            } catch (ClassCastException e) {
                                syntaxError(chars, "Syntax error");
                            }
                        }
                        mode = mode2;
                        c4 = c5;
                        backup = backup3;
                    }
                }
                if (setMode != 0) {
                    if (c3 != 1) {
                        c = c3;
                    } else {
                        if (op2 != 0) {
                            syntaxError(chars, "Char expected after operator");
                        }
                        add_unchecked(mode3, mode3);
                        _appendToPat(patBuf, mode3, false);
                        op2 = 0;
                        c = 0;
                    }
                    if (op2 == '-' || op2 == '&') {
                        patBuf.append(op2);
                    }
                    if (nested4 != null) {
                        nested = nested4;
                        nested2 = scratch3;
                    } else {
                        if (scratch3 == null) {
                            scratch3 = new UnicodeSet();
                        }
                        nested2 = scratch3;
                        nested = nested2;
                    }
                    switch (setMode) {
                        case 1:
                            int i2 = i + 1;
                            nested3 = nested;
                            op = op2;
                            lastChar = mode3;
                            nested.applyPattern(chars, symbols, patBuf, options, i2);
                            break;
                        case 2:
                            chars.skipIgnored(opts);
                            nested.applyPropertyPattern(chars, patBuf, symbolTable);
                            nested3 = nested;
                            op = op2;
                            lastChar = mode3;
                            break;
                        case 3:
                            nested._toPattern(patBuf, false);
                            nested3 = nested;
                            op = op2;
                            lastChar = mode3;
                            break;
                        default:
                            nested3 = nested;
                            op = op2;
                            lastChar = mode3;
                            break;
                    }
                    usePat2 = true;
                    if (mode == 0) {
                        set(nested3);
                        mode2 = 2;
                        usePat = true;
                    } else {
                        switch (op) {
                            case 0:
                                addAll(nested3);
                                break;
                            case '&':
                                retainAll(nested3);
                                break;
                            case '-':
                                removeAll(nested3);
                                break;
                        }
                        op2 = 0;
                        c3 = 2;
                        i = depth;
                        mode3 = lastChar;
                        mode2 = mode;
                        backup2 = backup;
                        lastString3 = lastString4;
                        scratch3 = nested2;
                        symbolTable = symbols;
                    }
                } else {
                    int lastChar2 = mode3;
                    if (mode == 0) {
                        syntaxError(chars, "Missing '['");
                    }
                    if (literal) {
                        scratch = scratch3;
                        lastString = lastString4;
                    } else {
                        switch (c4) {
                            case 36:
                                scratch = scratch3;
                                lastString = lastString4;
                                Object backup6 = chars.getPos(backup);
                                c4 = chars.next(opts);
                                boolean literal3 = chars.isEscaped();
                                boolean anchor = c4 == 93 && !literal3;
                                if (symbols == null && !anchor) {
                                    c4 = 36;
                                    chars.setPos(backup6);
                                    backup2 = backup6;
                                } else if (anchor && op2 == 0) {
                                    if (c3 == 1) {
                                        add_unchecked(lastChar2, lastChar2);
                                        _appendToPat(patBuf, lastChar2, false);
                                    }
                                    add_unchecked(65535);
                                    usePat2 = true;
                                    patBuf.append('$').append(']');
                                    i = depth;
                                    lastString3 = lastString;
                                    mode3 = lastChar2;
                                    scratch3 = scratch;
                                    symbolTable = symbols;
                                    backup2 = backup6;
                                    mode2 = 2;
                                    break;
                                } else {
                                    syntaxError(chars, "Unquoted '$'");
                                    backup2 = backup6;
                                }
                                switch (c3) {
                                    case 0:
                                        if (op2 == '-' && lastString != null) {
                                            syntaxError(chars, "Invalid range");
                                        }
                                        int lastItem = c4;
                                        lastString3 = null;
                                        mode3 = lastItem;
                                        c3 = 1;
                                        break;
                                    case 1:
                                        if (op2 != '-') {
                                            add_unchecked(lastChar2, lastChar2);
                                            _appendToPat(patBuf, lastChar2, false);
                                            int lastChar3 = c4;
                                            mode3 = lastChar3;
                                            lastString3 = lastString;
                                            break;
                                        } else {
                                            if (lastString != null) {
                                                syntaxError(chars, "Invalid range");
                                            }
                                            if (lastChar2 >= c4) {
                                                syntaxError(chars, "Invalid range");
                                            }
                                            add_unchecked(lastChar2, c4);
                                            _appendToPat(patBuf, lastChar2, false);
                                            patBuf.append(op2);
                                            _appendToPat(patBuf, c4, false);
                                            lastString3 = lastString;
                                            op2 = 0;
                                            mode3 = lastChar2;
                                            c3 = 0;
                                            break;
                                        }
                                    case 2:
                                        if (op2 != 0) {
                                            syntaxError(chars, "Set expected after operator");
                                        }
                                        int lastChar4 = c4;
                                        c3 = 1;
                                        mode3 = lastChar4;
                                        lastString3 = lastString;
                                        break;
                                    default:
                                        lastString3 = lastString;
                                        mode3 = lastChar2;
                                        break;
                                }
                                symbolTable = symbols;
                                i = depth;
                                mode2 = mode;
                                scratch3 = scratch;
                                break;
                            case 38:
                                scratch = scratch3;
                                lastString = lastString4;
                                if (c3 == 2 && op2 == 0) {
                                    op2 = (char) c4;
                                    i = depth;
                                    mode3 = lastChar2;
                                    mode2 = mode;
                                    backup2 = backup;
                                    symbolTable = symbols;
                                    lastString3 = lastString;
                                    scratch3 = scratch;
                                    break;
                                } else {
                                    syntaxError(chars, "'&' not after set");
                                    break;
                                }
                                break;
                            case 45:
                                scratch = scratch3;
                                lastString = lastString4;
                                if (op2 == 0) {
                                    if (c3 != 0) {
                                        op2 = (char) c4;
                                        i = depth;
                                        mode3 = lastChar2;
                                        mode2 = mode;
                                        backup2 = backup;
                                        symbolTable = symbols;
                                        lastString3 = lastString;
                                        scratch3 = scratch;
                                        break;
                                    } else if (lastString != null) {
                                        op2 = (char) c4;
                                        i = depth;
                                        mode3 = lastChar2;
                                        mode2 = mode;
                                        backup2 = backup;
                                        symbolTable = symbols;
                                        lastString3 = lastString;
                                        scratch3 = scratch;
                                        break;
                                    } else {
                                        add_unchecked(c4, c4);
                                        c4 = chars.next(opts);
                                        boolean literal4 = chars.isEscaped();
                                        if (c4 == 93 && !literal4) {
                                            patBuf.append("-]");
                                            i = depth;
                                            mode2 = 2;
                                            mode3 = lastChar2;
                                            backup2 = backup;
                                            symbolTable = symbols;
                                            lastString3 = lastString;
                                            scratch3 = scratch;
                                            break;
                                        }
                                    }
                                }
                                syntaxError(chars, "'-' not after char, string, or set");
                                backup2 = backup;
                                switch (c3) {
                                }
                                symbolTable = symbols;
                                i = depth;
                                mode2 = mode;
                                scratch3 = scratch;
                                break;
                            case 93:
                                UnicodeSet scratch4 = scratch3;
                                if (c3 == 1) {
                                    add_unchecked(lastChar2, lastChar2);
                                    _appendToPat(patBuf, lastChar2, false);
                                }
                                if (op2 == '-') {
                                    add_unchecked(op2, op2);
                                    patBuf.append(op2);
                                } else if (op2 == '&') {
                                    syntaxError(chars, "Trailing '&'");
                                }
                                patBuf.append(']');
                                mode2 = 2;
                                i = depth;
                                mode3 = lastChar2;
                                backup2 = backup;
                                symbolTable = symbols;
                                lastString3 = lastString4;
                                scratch3 = scratch4;
                                break;
                            case 94:
                                scratch = scratch3;
                                syntaxError(chars, "'^' not after '['");
                                lastString = lastString4;
                                break;
                            case 123:
                                if (op2 != 0 && op2 != '-') {
                                    syntaxError(chars, "Missing operand after operator");
                                }
                                if (c3 == 1) {
                                    add_unchecked(lastChar2, lastChar2);
                                    _appendToPat(patBuf, lastChar2, false);
                                }
                                c3 = 0;
                                if (buf == null) {
                                    buf = new StringBuilder();
                                } else {
                                    buf.setLength(0);
                                }
                                while (true) {
                                    if (chars.atEnd()) {
                                        ok = false;
                                        c2 = c4;
                                    } else {
                                        c4 = chars.next(opts);
                                        boolean literal5 = chars.isEscaped();
                                        if (c4 == 125 && !literal5) {
                                            ok = true;
                                            c2 = c4;
                                        } else {
                                            appendCodePoint(buf, c4);
                                        }
                                    }
                                }
                                int c8 = buf.length();
                                if (c8 < 1 || !ok) {
                                    syntaxError(chars, "Invalid multicharacter string");
                                }
                                String curString = buf.toString();
                                if (op2 != '-') {
                                    scratch2 = scratch3;
                                    add(curString);
                                    lastString2 = curString;
                                } else {
                                    int lastSingle = CharSequences.getSingleCodePoint(lastString4 == null ? "" : lastString4);
                                    int curSingle = CharSequences.getSingleCodePoint(curString);
                                    if (lastSingle != Integer.MAX_VALUE && curSingle != Integer.MAX_VALUE) {
                                        add(lastSingle, curSingle);
                                        scratch2 = scratch3;
                                    } else {
                                        if (this.strings == EMPTY_STRINGS) {
                                            this.strings = new TreeSet();
                                        }
                                        try {
                                            scratch2 = scratch3;
                                            try {
                                                StringRange.expand(lastString4, curString, true, this.strings);
                                            } catch (Exception e2) {
                                                e = e2;
                                                syntaxError(chars, e.getMessage());
                                                lastString2 = null;
                                                op2 = 0;
                                                patBuf.append('{');
                                                _appendToPat(patBuf, curString, false);
                                                patBuf.append('}');
                                                i = depth;
                                                mode3 = lastChar2;
                                                mode2 = mode;
                                                backup2 = backup;
                                                scratch3 = scratch2;
                                                symbolTable = symbols;
                                                lastString3 = lastString2;
                                            }
                                        } catch (Exception e3) {
                                            e = e3;
                                            scratch2 = scratch3;
                                        }
                                    }
                                    lastString2 = null;
                                    op2 = 0;
                                }
                                patBuf.append('{');
                                _appendToPat(patBuf, curString, false);
                                patBuf.append('}');
                                i = depth;
                                mode3 = lastChar2;
                                mode2 = mode;
                                backup2 = backup;
                                scratch3 = scratch2;
                                symbolTable = symbols;
                                lastString3 = lastString2;
                                break;
                            default:
                                scratch = scratch3;
                                lastString = lastString4;
                                break;
                        }
                    }
                    backup2 = backup;
                    switch (c3) {
                    }
                    symbolTable = symbols;
                    i = depth;
                    mode2 = mode;
                    scratch3 = scratch;
                }
            }
        }
        if (mode2 != 2) {
            syntaxError(chars, "Missing ']'");
        }
        chars.skipIgnored(opts);
        if ((options & 2) != 0) {
            closeOver(2);
        }
        if (invert) {
            complement();
        }
        if (!usePat) {
            appendNewPattern(rebuiltPat, false, true);
        } else {
            append(rebuiltPat, patBuf.toString());
        }
    }

    private static void syntaxError(RuleCharacterIterator chars, String msg) {
        throw new IllegalArgumentException("Error: " + msg + " at \"" + Utility.escape(chars.toString()) + Typography.quote);
    }

    public <T extends Collection<String>> T addAllTo(T target) {
        return (T) addAllTo(this, target);
    }

    public String[] addAllTo(String[] target) {
        return (String[]) addAllTo(this, target);
    }

    public static String[] toArray(UnicodeSet set) {
        return (String[]) addAllTo(set, new String[set.size()]);
    }

    public UnicodeSet add(Iterable<?> source) {
        return addAll(source);
    }

    public UnicodeSet addAll(Iterable<?> source) {
        checkFrozen();
        for (Object o : source) {
            add(o.toString());
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
        if (newLen <= this.list.length) {
            return;
        }
        int newCapacity = nextCapacity(newLen);
        int[] temp = new int[newCapacity];
        System.arraycopy(this.list, 0, temp, 0, this.len);
        this.list = temp;
    }

    private void ensureBufferCapacity(int newLen) {
        if (newLen > MAX_LENGTH) {
            newLen = MAX_LENGTH;
        }
        int[] iArr = this.buffer;
        if (iArr == null || newLen > iArr.length) {
            int newCapacity = nextCapacity(newLen);
            this.buffer = new int[newCapacity];
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
        int j2 = 0;
        int j3 = 0 + 1;
        int a = this.list[0];
        if (polarity == 1 || polarity == 2) {
            if (other[0] != 0) {
                b = 0;
                j = 0;
            } else {
                int j4 = 0 + 1;
                int b2 = other[j4];
                b = j4;
                j = b2;
            }
        } else {
            b = 0 + 1;
            j = other[0];
        }
        while (true) {
            if (a < j) {
                int k = j2 + 1;
                this.buffer[j2] = a;
                int i = j3 + 1;
                a = this.list[j3];
                j3 = i;
                j2 = k;
            } else if (j < a) {
                int k2 = j2 + 1;
                this.buffer[j2] = j;
                int j5 = b + 1;
                j = other[b];
                b = j5;
                j2 = k2;
            } else if (a != HIGH) {
                int i2 = j3 + 1;
                a = this.list[j3];
                int j6 = b + 1;
                j = other[b];
                b = j6;
                j3 = i2;
            } else {
                int[] iArr = this.buffer;
                int k3 = j2 + 1;
                iArr[j2] = HIGH;
                this.len = k3;
                int[] temp = this.list;
                this.list = iArr;
                this.buffer = temp;
                this.pat = null;
                return this;
            }
        }
    }

    private UnicodeSet add(int[] other, int otherLen, int polarity) {
        int k;
        ensureBufferCapacity(this.len + otherLen);
        int j = 0;
        int i = 0 + 1;
        int a = this.list[0];
        int j2 = 0 + 1;
        int b = other[0];
        while (true) {
            switch (polarity) {
                case 0:
                    if (a < b) {
                        if (j > 0) {
                            int[] iArr = this.buffer;
                            if (a <= iArr[j - 1]) {
                                j--;
                                a = max(this.list[i], iArr[j]);
                                i++;
                                polarity ^= 1;
                                break;
                            }
                        }
                        int k2 = j + 1;
                        this.buffer[j] = a;
                        a = this.list[i];
                        j = k2;
                        i++;
                        polarity ^= 1;
                    } else if (b < a) {
                        if (j > 0) {
                            int[] iArr2 = this.buffer;
                            if (b <= iArr2[j - 1]) {
                                j--;
                                b = max(other[j2], iArr2[j]);
                                j2++;
                                polarity ^= 2;
                                break;
                            }
                        }
                        int k3 = j + 1;
                        this.buffer[j] = b;
                        b = other[j2];
                        j = k3;
                        j2++;
                        polarity ^= 2;
                    } else if (a == HIGH) {
                        break;
                    } else {
                        if (j > 0) {
                            int[] iArr3 = this.buffer;
                            if (a <= iArr3[j - 1]) {
                                j--;
                                a = max(this.list[i], iArr3[j]);
                                i++;
                                b = other[j2];
                                polarity = (polarity ^ 1) ^ 2;
                                j2++;
                                break;
                            }
                        }
                        int k4 = j + 1;
                        this.buffer[j] = a;
                        a = this.list[i];
                        j = k4;
                        i++;
                        b = other[j2];
                        polarity = (polarity ^ 1) ^ 2;
                        j2++;
                    }
                case 1:
                    if (a < b) {
                        int k5 = j + 1;
                        this.buffer[j] = a;
                        a = this.list[i];
                        polarity ^= 1;
                        i++;
                        j = k5;
                        break;
                    } else if (b < a) {
                        b = other[j2];
                        polarity ^= 2;
                        j2++;
                        break;
                    } else if (a != HIGH) {
                        a = this.list[i];
                        b = other[j2];
                        polarity = (polarity ^ 1) ^ 2;
                        j2++;
                        i++;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (b < a) {
                        int k6 = j + 1;
                        this.buffer[j] = b;
                        b = other[j2];
                        polarity ^= 2;
                        j2++;
                        j = k6;
                        break;
                    } else if (a < b) {
                        a = this.list[i];
                        polarity ^= 1;
                        i++;
                        break;
                    } else if (a != HIGH) {
                        a = this.list[i];
                        b = other[j2];
                        polarity = (polarity ^ 1) ^ 2;
                        j2++;
                        i++;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (b <= a) {
                        if (a != HIGH) {
                            k = j + 1;
                            this.buffer[j] = a;
                            a = this.list[i];
                            b = other[j2];
                            polarity = (polarity ^ 1) ^ 2;
                            j2++;
                            i++;
                            j = k;
                            break;
                        } else {
                            break;
                        }
                    } else if (b == HIGH) {
                        break;
                    } else {
                        k = j + 1;
                        this.buffer[j] = b;
                        a = this.list[i];
                        b = other[j2];
                        polarity = (polarity ^ 1) ^ 2;
                        j2++;
                        i++;
                        j = k;
                    }
            }
        }
        int[] iArr4 = this.buffer;
        int k7 = j + 1;
        iArr4[j] = HIGH;
        this.len = k7;
        int[] temp = this.list;
        this.list = iArr4;
        this.buffer = temp;
        this.pat = null;
        return this;
    }

    private UnicodeSet retain(int[] other, int otherLen, int polarity) {
        ensureBufferCapacity(this.len + otherLen);
        int j = 0;
        int i = 0 + 1;
        int a = this.list[0];
        int j2 = 0 + 1;
        int b = other[0];
        while (true) {
            switch (polarity) {
                case 0:
                    if (a < b) {
                        a = this.list[i];
                        polarity ^= 1;
                        i++;
                        break;
                    } else if (b < a) {
                        b = other[j2];
                        polarity ^= 2;
                        j2++;
                        break;
                    } else if (a != HIGH) {
                        int k = j + 1;
                        this.buffer[j] = a;
                        a = this.list[i];
                        b = other[j2];
                        polarity = (polarity ^ 1) ^ 2;
                        j2++;
                        i++;
                        j = k;
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (a < b) {
                        a = this.list[i];
                        polarity ^= 1;
                        i++;
                        break;
                    } else if (b < a) {
                        int k2 = j + 1;
                        this.buffer[j] = b;
                        b = other[j2];
                        polarity ^= 2;
                        j2++;
                        j = k2;
                        break;
                    } else if (a != HIGH) {
                        a = this.list[i];
                        b = other[j2];
                        polarity = (polarity ^ 1) ^ 2;
                        j2++;
                        i++;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (b < a) {
                        b = other[j2];
                        polarity ^= 2;
                        j2++;
                        break;
                    } else if (a < b) {
                        int k3 = j + 1;
                        this.buffer[j] = a;
                        a = this.list[i];
                        polarity ^= 1;
                        i++;
                        j = k3;
                        break;
                    } else if (a != HIGH) {
                        a = this.list[i];
                        b = other[j2];
                        polarity = (polarity ^ 1) ^ 2;
                        j2++;
                        i++;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (a < b) {
                        int k4 = j + 1;
                        this.buffer[j] = a;
                        a = this.list[i];
                        polarity ^= 1;
                        i++;
                        j = k4;
                        break;
                    } else if (b < a) {
                        int k5 = j + 1;
                        this.buffer[j] = b;
                        b = other[j2];
                        polarity ^= 2;
                        j2++;
                        j = k5;
                        break;
                    } else if (a != HIGH) {
                        int k6 = j + 1;
                        this.buffer[j] = a;
                        a = this.list[i];
                        b = other[j2];
                        polarity = (polarity ^ 1) ^ 2;
                        j2++;
                        i++;
                        j = k6;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int[] iArr = this.buffer;
        int k7 = j + 1;
        iArr[j] = HIGH;
        this.len = k7;
        int[] temp = this.list;
        this.list = iArr;
        this.buffer = temp;
        this.pat = null;
        return this;
    }

    private static final int max(int a, int b) {
        return a > b ? a : b;
    }

    /* loaded from: classes.dex */
    private static final class NumericValueFilter implements Filter {
        double value;

        NumericValueFilter(double value) {
            this.value = value;
        }

        @Override // com.ibm.icu.text.UnicodeSet.Filter
        public boolean contains(int ch) {
            return UCharacter.getUnicodeNumericValue(ch) == this.value;
        }
    }

    /* loaded from: classes.dex */
    private static final class GeneralCategoryMaskFilter implements Filter {
        int mask;

        GeneralCategoryMaskFilter(int mask) {
            this.mask = mask;
        }

        @Override // com.ibm.icu.text.UnicodeSet.Filter
        public boolean contains(int ch) {
            return ((1 << UCharacter.getType(ch)) & this.mask) != 0;
        }
    }

    /* loaded from: classes.dex */
    private static final class IntPropertyFilter implements Filter {
        int prop;
        int value;

        IntPropertyFilter(int prop, int value) {
            this.prop = prop;
            this.value = value;
        }

        @Override // com.ibm.icu.text.UnicodeSet.Filter
        public boolean contains(int ch) {
            return UCharacter.getIntPropertyValue(ch, this.prop) == this.value;
        }
    }

    /* loaded from: classes.dex */
    private static final class ScriptExtensionsFilter implements Filter {
        int script;

        ScriptExtensionsFilter(int script) {
            this.script = script;
        }

        @Override // com.ibm.icu.text.UnicodeSet.Filter
        public boolean contains(int c) {
            return UScript.hasScript(c, this.script);
        }
    }

    /* loaded from: classes.dex */
    private static final class VersionFilter implements Filter {
        VersionInfo version;

        VersionFilter(VersionInfo version) {
            this.version = version;
        }

        @Override // com.ibm.icu.text.UnicodeSet.Filter
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
        int i;
        String source2 = PatternProps.trimWhiteSpace(source);
        StringBuilder buf = null;
        while (i < source2.length()) {
            char ch = source2.charAt(i);
            if (PatternProps.isWhiteSpace(ch)) {
                if (buf != null) {
                    i = buf.charAt(buf.length() + (-1)) == ' ' ? i + 1 : 0;
                } else {
                    buf = new StringBuilder().append((CharSequence) source2, 0, i);
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
            UnicodeSet inclusions = CharacterPropertiesImpl.getInclusionsForProperty(prop);
            applyFilter(new GeneralCategoryMaskFilter(value), inclusions);
        } else if (prop == 28672) {
            UnicodeSet inclusions2 = CharacterPropertiesImpl.getInclusionsForProperty(prop);
            applyFilter(new ScriptExtensionsFilter(value), inclusions2);
        } else if (prop >= 0 && prop < 65) {
            if (value == 0 || value == 1) {
                set(CharacterProperties.getBinaryPropertySet(prop));
                if (value == 0) {
                    complement();
                }
            } else {
                clear();
            }
        } else if (4096 <= prop && prop < 4121) {
            UnicodeSet inclusions3 = CharacterPropertiesImpl.getInclusionsForProperty(prop);
            applyFilter(new IntPropertyFilter(prop, value), inclusions3);
        } else {
            throw new IllegalArgumentException("unsupported property " + prop);
        }
        return this;
    }

    public UnicodeSet applyPropertyAlias(String propertyAlias, String valueAlias) {
        return applyPropertyAlias(propertyAlias, valueAlias, null);
    }

    public UnicodeSet applyPropertyAlias(String propertyAlias, String valueAlias, SymbolTable symbols) {
        int p;
        int v;
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
            if ((p >= 0 && p < 65) || ((p >= 4096 && p < 4121) || (p >= 8192 && p < 8193))) {
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
            } else {
                switch (p) {
                    case 12288:
                        double value = Double.parseDouble(PatternProps.trimWhiteSpace(valueAlias));
                        applyFilter(new NumericValueFilter(value), CharacterPropertiesImpl.getInclusionsForProperty(p));
                        return this;
                    case 16384:
                        String buf = mungeCharName(valueAlias);
                        VersionInfo version = VersionInfo.getInstance(buf);
                        applyFilter(new VersionFilter(version), CharacterPropertiesImpl.getInclusionsForProperty(p));
                        return this;
                    case 16389:
                        String buf2 = mungeCharName(valueAlias);
                        int ch = UCharacter.getCharFromExtendedName(buf2);
                        if (ch == -1) {
                            throw new IllegalArgumentException("Invalid character name");
                        }
                        clear();
                        add_unchecked(ch);
                        return this;
                    case 16395:
                        throw new IllegalArgumentException("Unicode_1_Name (na1) not supported");
                    case 28672:
                        v = UCharacter.getPropertyValueEnum(4106, valueAlias);
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported property");
                }
            }
        } else {
            UPropertyAliases pnames = UPropertyAliases.INSTANCE;
            int v3 = pnames.getPropertyValueEnum(8192, propertyAlias);
            if (v3 != -1) {
                p = 8192;
                v = v3;
            } else {
                int v4 = pnames.getPropertyValueEnum(4106, propertyAlias);
                if (v4 != -1) {
                    p = 4106;
                    v = v4;
                } else {
                    int p2 = pnames.getPropertyEnum(propertyAlias);
                    if (p2 == -1) {
                        p2 = -1;
                    }
                    if (p2 >= 0 && p2 < 65) {
                        v = 1;
                        p = p2;
                    } else if (p2 == -1) {
                        if (UPropertyAliases.compare(ANY_ID, propertyAlias) == 0) {
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
                        throw new IllegalArgumentException("Missing property value");
                    }
                }
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
        return pattern.regionMatches(pos, "[:", 0, 2) || pattern.regionMatches(true, pos, "\\p", 0, 2) || pattern.regionMatches(pos, "\\N", 0, 2);
    }

    private static boolean resemblesPropertyPattern(RuleCharacterIterator chars, int iterOpts) {
        boolean result = false;
        int iterOpts2 = iterOpts & (-3);
        Object pos = chars.getPos((Object) null);
        int c = chars.next(iterOpts2);
        if (c == 91 || c == 92) {
            int d = chars.next(iterOpts2 & (-5));
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
        String propName;
        String valueName;
        int pos2 = ppos.getIndex();
        if (pos2 + 5 > pattern.length()) {
            return null;
        }
        boolean posix = false;
        boolean isName = false;
        boolean invert = false;
        if (pattern.regionMatches(pos2, "[:", 0, 2)) {
            posix = true;
            pos = PatternProps.skipWhiteSpace(pattern, pos2 + 2);
            if (pos < pattern.length() && pattern.charAt(pos) == '^') {
                pos++;
                invert = true;
            }
        } else if (pattern.regionMatches(true, pos2, "\\p", 0, 2) || pattern.regionMatches(pos2, "\\N", 0, 2)) {
            char c = pattern.charAt(pos2 + 1);
            invert = c == 'P';
            isName = c == 'N';
            int pos3 = PatternProps.skipWhiteSpace(pattern, pos2 + 2);
            if (pos3 != pattern.length()) {
                int pos4 = pos3 + 1;
                if (pattern.charAt(pos3) == 123) {
                    pos = pos4;
                }
            }
            return null;
        } else {
            return null;
        }
        int close = pattern.indexOf(posix ? ":]" : "}", pos);
        if (close < 0) {
            return null;
        }
        int equals = pattern.indexOf(61, pos);
        if (equals >= 0 && equals < close && !isName) {
            propName = pattern.substring(pos, equals);
            valueName = pattern.substring(equals + 1, close);
        } else {
            propName = pattern.substring(pos, close);
            valueName = "";
            if (isName) {
                valueName = propName;
                propName = "na";
            }
        }
        applyPropertyAlias(propName, valueName, symbols);
        if (invert) {
            complement();
        }
        ppos.setIndex((posix ? 2 : 1) + close);
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
        if (result >= 0) {
            if (result > 31) {
                set.add(result);
                return;
            }
            set.add(full.toString());
            full.setLength(0);
        }
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
                        int result = csp.toFullLower(cp2, (UCaseProps.ContextIterator) null, full, 1);
                        addCaseMapping(foldSet, result, full);
                        int result2 = csp.toFullTitle(cp2, (UCaseProps.ContextIterator) null, full, 1);
                        addCaseMapping(foldSet, result2, full);
                        int result3 = csp.toFullUpper(cp2, (UCaseProps.ContextIterator) null, full, 1);
                        addCaseMapping(foldSet, result3, full);
                        int result4 = csp.toFullFolding(cp2, full, 0);
                        addCaseMapping(foldSet, result4, full);
                    }
                }
            }
            if (hasStrings()) {
                if ((attribute & 2) != 0) {
                    for (String s : this.strings) {
                        String str = UCharacter.foldCase(s, 0);
                        if (!csp.addStringCaseClosure(str, foldSet)) {
                            foldSet.add(str);
                        }
                    }
                } else {
                    BreakIterator bi = BreakIterator.getWordInstance(root);
                    for (String str2 : this.strings) {
                        foldSet.add(UCharacter.toLowerCase(root, str2));
                        foldSet.add(UCharacter.toTitleCase(root, str2, bi));
                        foldSet.add(UCharacter.toUpperCase(root, str2));
                        foldSet.add(UCharacter.foldCase(str2, 0));
                    }
                }
            }
            set(foldSet);
        }
        return this;
    }

    /* loaded from: classes.dex */
    public static abstract class XSymbolTable implements SymbolTable {
        @Override // com.ibm.icu.text.SymbolTable
        public UnicodeMatcher lookupMatcher(int i) {
            return null;
        }

        public boolean applyPropertyAlias(String propertyName, String propertyValue, UnicodeSet result) {
            return false;
        }

        @Override // com.ibm.icu.text.SymbolTable
        public char[] lookup(String s) {
            return null;
        }

        @Override // com.ibm.icu.text.SymbolTable
        public String parseReference(String text, ParsePosition pos, int limit) {
            return null;
        }
    }

    public boolean isFrozen() {
        return (this.bmpSet == null && this.stringSpan == null) ? false : true;
    }

    /* renamed from: freeze */
    public UnicodeSet m87freeze() {
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
            int which = spanCondition == SpanCondition.NOT_CONTAINED ? 33 : 34;
            UnicodeSetStringSpan strSpan = new UnicodeSetStringSpan(this, new ArrayList(this.strings), which);
            if (strSpan.needsStringSpanUTF16()) {
                return strSpan.span(s, start, spanCondition);
            }
        }
        int which2 = spanCodePointsAndCount(s, start, spanCondition, null);
        return which2;
    }

    @Deprecated
    public int spanAndCount(CharSequence s, int start, SpanCondition spanCondition, OutputInt outCount) {
        if (outCount == null) {
            throw new IllegalArgumentException("outCount must not be null");
        }
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
        if (hasStrings()) {
            int which = spanCondition == SpanCondition.NOT_CONTAINED ? 33 : 34;
            UnicodeSetStringSpan strSpan = new UnicodeSetStringSpan(this, new ArrayList(this.strings), which | 64);
            return strSpan.spanAndCount(s, start, spanCondition, outCount);
        }
        int which2 = spanCodePointsAndCount(s, start, spanCondition, outCount);
        return which2;
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
            int which = spanCondition == SpanCondition.NOT_CONTAINED ? 17 : 18;
            UnicodeSetStringSpan strSpan = new UnicodeSetStringSpan(this, new ArrayList(this.strings), which);
            if (strSpan.needsStringSpanUTF16()) {
                return strSpan.spanBack(s, fromIndex, spanCondition);
            }
        }
        boolean spanContained = spanCondition != SpanCondition.NOT_CONTAINED;
        int prev = fromIndex;
        do {
            int c = Character.codePointBefore(s, prev);
            if (spanContained != contains(c)) {
                break;
            }
            prev -= Character.charCount(c);
        } while (prev > 0);
        return prev;
    }

    /* renamed from: cloneAsThawed */
    public UnicodeSet m86cloneAsThawed() {
        UnicodeSet result = new UnicodeSet(this);
        if (result.isFrozen()) {
            throw new AssertionError();
        }
        return result;
    }

    private void checkFrozen() {
        if (isFrozen()) {
            throw new UnsupportedOperationException("Attempt to modify frozen object");
        }
    }

    /* loaded from: classes.dex */
    public static class EntryRange {
        public int codepoint;
        public int codepointEnd;

        EntryRange() {
        }

        public String toString() {
            StringBuilder b = new StringBuilder();
            int i = this.codepoint;
            return (i == this.codepointEnd ? (StringBuilder) UnicodeSet._appendToPat(b, i, false) : (StringBuilder) UnicodeSet._appendToPat(((StringBuilder) UnicodeSet._appendToPat(b, i, false)).append('-'), this.codepointEnd, false)).toString();
        }
    }

    public Iterable<EntryRange> ranges() {
        return new EntryRangeIterable();
    }

    /* loaded from: classes.dex */
    private class EntryRangeIterable implements Iterable<EntryRange> {
        private EntryRangeIterable() {
        }

        @Override // java.lang.Iterable
        public Iterator<EntryRange> iterator() {
            return new EntryRangeIterator();
        }
    }

    /* loaded from: classes.dex */
    private class EntryRangeIterator implements Iterator<EntryRange> {
        int pos;
        EntryRange result;

        private EntryRangeIterator() {
            this.result = new EntryRange();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.pos < UnicodeSet.this.len - 1;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public EntryRange next() {
            if (this.pos < UnicodeSet.this.len - 1) {
                EntryRange entryRange = this.result;
                int[] iArr = UnicodeSet.this.list;
                int i = this.pos;
                this.pos = i + 1;
                entryRange.codepoint = iArr[i];
                EntryRange entryRange2 = this.result;
                int[] iArr2 = UnicodeSet.this.list;
                int i2 = this.pos;
                this.pos = i2 + 1;
                entryRange2.codepointEnd = iArr2[i2] - 1;
                return this.result;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.lang.Iterable
    public Iterator<String> iterator() {
        return new UnicodeSetIterator2(this);
    }

    /* loaded from: classes.dex */
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
            int i = source.len - 1;
            this.len = i;
            if (i > 0) {
                this.sourceStrings = source.strings;
                int[] iArr = source.list;
                this.sourceList = iArr;
                int i2 = this.item;
                int i3 = i2 + 1;
                this.item = i3;
                this.current = iArr[i2];
                this.item = i3 + 1;
                this.limit = iArr[i3];
                return;
            }
            this.stringIterator = source.strings.iterator();
            this.sourceList = null;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.sourceList != null || this.stringIterator.hasNext();
        }

        @Override // java.util.Iterator
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

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public <T extends CharSequence> boolean containsAll(Iterable<T> collection) {
        for (T o : collection) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    public <T extends CharSequence> boolean containsNone(Iterable<T> collection) {
        for (T o : collection) {
            if (contains(o)) {
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
            add(str);
        }
        return this;
    }

    public <T extends CharSequence> UnicodeSet removeAll(Iterable<T> collection) {
        checkFrozen();
        for (T o : collection) {
            remove(o);
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

    @Override // java.lang.Comparable
    public int compareTo(UnicodeSet o) {
        return compareTo(o, ComparisonStyle.SHORTER_FIRST);
    }

    public int compareTo(UnicodeSet o, ComparisonStyle style) {
        int diff;
        if (style != ComparisonStyle.LEXICOGRAPHIC && (diff = size() - o.size()) != 0) {
            return (diff < 0) == (style == ComparisonStyle.SHORTER_FIRST) ? -1 : 1;
        }
        int i = 0;
        while (true) {
            int[] iArr = this.list;
            int i2 = iArr[i];
            int[] iArr2 = o.list;
            int result = i2 - iArr2[i];
            if (result != 0) {
                if (iArr[i] == HIGH) {
                    if (hasStrings()) {
                        String item = this.strings.first();
                        return compare(item, o.list[i]);
                    }
                    return 1;
                } else if (iArr2[i] != HIGH) {
                    return (i & 1) == 0 ? result : -result;
                } else if (o.hasStrings()) {
                    String item2 = o.strings.first();
                    int compareResult = compare(item2, this.list[i]);
                    if (compareResult > 0) {
                        return -1;
                    }
                    return compareResult < 0 ? 1 : 0;
                } else {
                    return -1;
                }
            } else if (iArr[i] != HIGH) {
                i++;
            } else {
                return compare(this.strings, o.strings);
            }
        }
    }

    public int compareTo(Iterable<String> other) {
        return compare(this, other);
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
            T item1 = first.next();
            T item2 = other.next();
            int result = item1.compareTo(item2);
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
        return (diff < 0) == (style == ComparisonStyle.SHORTER_FIRST) ? -1 : 1;
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
        UnicodeSet notInInput = new UnicodeSet(this).complement();
        UnicodeSetIterator it = new UnicodeSetIterator(notInInput);
        while (it.nextRange()) {
            if (it.codepoint != 0 && it.codepoint != UnicodeSetIterator.IS_STRING && it.codepointEnd != 1114111 && dontCare.contains(it.codepoint, it.codepointEnd)) {
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
