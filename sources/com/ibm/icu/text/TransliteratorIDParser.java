package com.ibm.icu.text;

import com.ibm.icu.impl.PatternProps;
import com.ibm.icu.impl.Utility;
import com.ibm.icu.util.CaseInsensitiveString;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
class TransliteratorIDParser {
    private static final String ANY = "Any";
    private static final char CLOSE_REV = ')';
    private static final int FORWARD = 0;
    private static final char ID_DELIM = ';';
    private static final char OPEN_REV = '(';
    private static final int REVERSE = 1;
    private static final Map<CaseInsensitiveString, String> SPECIAL_INVERSES = Collections.synchronizedMap(new HashMap());
    private static final char TARGET_SEP = '-';
    private static final char VARIANT_SEP = '/';

    TransliteratorIDParser() {
    }

    /* loaded from: classes.dex */
    private static class Specs {
        public String filter;
        public boolean sawSource;
        public String source;
        public String target;
        public String variant;

        Specs(String s, String t, String v, boolean sawS, String f) {
            this.source = s;
            this.target = t;
            this.variant = v;
            this.sawSource = sawS;
            this.filter = f;
        }
    }

    /* loaded from: classes.dex */
    static class SingleID {
        public String basicID;
        public String canonID;
        public String filter;

        SingleID(String c, String b, String f) {
            this.canonID = c;
            this.basicID = b;
            this.filter = f;
        }

        SingleID(String c, String b) {
            this(c, b, null);
        }

        Transliterator getInstance() {
            Transliterator t;
            String str = this.basicID;
            if (str == null || str.length() == 0) {
                t = Transliterator.getBasicInstance("Any-Null", this.canonID);
            } else {
                t = Transliterator.getBasicInstance(this.basicID, this.canonID);
            }
            if (t != null && this.filter != null) {
                t.setFilter(new UnicodeSet(this.filter));
            }
            return t;
        }
    }

    public static SingleID parseFilterID(String id, int[] pos) {
        int start = pos[0];
        Specs specs = parseFilterID(id, pos, true);
        if (specs == null) {
            pos[0] = start;
            return null;
        }
        SingleID single = specsToID(specs, 0);
        single.filter = specs.filter;
        return single;
    }

    public static SingleID parseSingleID(String id, int[] pos, int dir) {
        SingleID single;
        int start = pos[0];
        Specs specsA = null;
        Specs specsB = null;
        boolean sawParen = false;
        int pass = 1;
        while (true) {
            if (pass > 2) {
                break;
            } else if (pass == 2 && (specsA = parseFilterID(id, pos, true)) == null) {
                pos[0] = start;
                return null;
            } else if (!Utility.parseChar(id, pos, (char) OPEN_REV)) {
                pass++;
            } else {
                sawParen = true;
                if (!Utility.parseChar(id, pos, (char) CLOSE_REV) && ((specsB = parseFilterID(id, pos, true)) == null || !Utility.parseChar(id, pos, (char) CLOSE_REV))) {
                    pos[0] = start;
                    return null;
                }
            }
        }
        if (sawParen) {
            if (dir == 0) {
                single = specsToID(specsA, 0);
                single.canonID += OPEN_REV + specsToID(specsB, 0).canonID + CLOSE_REV;
                if (specsA != null) {
                    single.filter = specsA.filter;
                }
            } else {
                single = specsToID(specsB, 0);
                single.canonID += OPEN_REV + specsToID(specsA, 0).canonID + CLOSE_REV;
                if (specsB != null) {
                    single.filter = specsB.filter;
                }
            }
        } else {
            if (dir == 0) {
                single = specsToID(specsA, 0);
            } else {
                SingleID single2 = specsToSpecialInverse(specsA);
                if (single2 != null) {
                    single = single2;
                } else {
                    single = specsToID(specsA, 1);
                }
            }
            single.filter = specsA.filter;
        }
        return single;
    }

    public static UnicodeSet parseGlobalFilter(String id, int[] pos, int dir, int[] withParens, StringBuffer canonID) {
        UnicodeSet filter = null;
        int start = pos[0];
        if (withParens[0] == -1) {
            withParens[0] = Utility.parseChar(id, pos, (char) OPEN_REV) ? 1 : 0;
        } else if (withParens[0] == 1 && !Utility.parseChar(id, pos, (char) OPEN_REV)) {
            pos[0] = start;
            return null;
        }
        pos[0] = PatternProps.skipWhiteSpace(id, pos[0]);
        if (UnicodeSet.resemblesPattern(id, pos[0])) {
            ParsePosition ppos = new ParsePosition(pos[0]);
            try {
                filter = new UnicodeSet(id, ppos, null);
                String pattern = id.substring(pos[0], ppos.getIndex());
                pos[0] = ppos.getIndex();
                if (withParens[0] == 1 && !Utility.parseChar(id, pos, (char) CLOSE_REV)) {
                    pos[0] = start;
                    return null;
                } else if (canonID != null) {
                    if (dir == 0) {
                        if (withParens[0] == 1) {
                            pattern = String.valueOf((char) OPEN_REV) + pattern + CLOSE_REV;
                        }
                        canonID.append(pattern + ID_DELIM);
                    } else {
                        if (withParens[0] == 0) {
                            pattern = String.valueOf((char) OPEN_REV) + pattern + CLOSE_REV;
                        }
                        canonID.insert(0, pattern + ID_DELIM);
                    }
                }
            } catch (IllegalArgumentException e) {
                pos[0] = start;
                return null;
            }
        }
        return filter;
    }

    public static boolean parseCompoundID(String id, int dir, StringBuffer canonID, List<SingleID> list, UnicodeSet[] globalFilter) {
        int[] pos = {0};
        list.clear();
        globalFilter[0] = null;
        canonID.setLength(0);
        int[] withParens = {0};
        UnicodeSet filter = parseGlobalFilter(id, pos, dir, withParens, canonID);
        if (filter != null) {
            if (!Utility.parseChar(id, pos, (char) ID_DELIM)) {
                canonID.setLength(0);
                pos[0] = 0;
            }
            if (dir == 0) {
                globalFilter[0] = filter;
            }
        }
        boolean sawDelimiter = true;
        while (true) {
            SingleID single = parseSingleID(id, pos, dir);
            if (single == null) {
                break;
            }
            if (dir == 0) {
                list.add(single);
            } else {
                list.add(0, single);
            }
            if (!Utility.parseChar(id, pos, (char) ID_DELIM)) {
                sawDelimiter = false;
                break;
            }
        }
        if (list.size() == 0) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            canonID.append(list.get(i).canonID);
            if (i != list.size() - 1) {
                canonID.append(ID_DELIM);
            }
        }
        if (sawDelimiter) {
            withParens[0] = 1;
            UnicodeSet filter2 = parseGlobalFilter(id, pos, dir, withParens, canonID);
            if (filter2 != null) {
                Utility.parseChar(id, pos, (char) ID_DELIM);
                if (dir == 1) {
                    globalFilter[0] = filter2;
                }
            }
        }
        pos[0] = PatternProps.skipWhiteSpace(id, pos[0]);
        return pos[0] == id.length();
    }

    static List<Transliterator> instantiateList(List<SingleID> ids) {
        List<Transliterator> translits = new ArrayList<>();
        for (SingleID single : ids) {
            if (single.basicID.length() != 0) {
                Transliterator t = single.getInstance();
                if (t == null) {
                    throw new IllegalArgumentException("Illegal ID " + single.canonID);
                }
                translits.add(t);
            }
        }
        if (translits.size() == 0) {
            Transliterator t2 = Transliterator.getBasicInstance("Any-Null", null);
            if (t2 == null) {
                throw new IllegalArgumentException("Internal error; cannot instantiate Any-Null");
            }
            translits.add(t2);
        }
        return translits;
    }

    public static String[] IDtoSTV(String id) {
        String variant;
        String target;
        String source = ANY;
        int sep = id.indexOf(45);
        int var = id.indexOf(47);
        if (var < 0) {
            var = id.length();
        }
        boolean isSourcePresent = false;
        if (sep < 0) {
            target = id.substring(0, var);
            variant = id.substring(var);
        } else if (sep < var) {
            if (sep > 0) {
                source = id.substring(0, sep);
                isSourcePresent = true;
            }
            target = id.substring(sep + 1, var);
            variant = id.substring(var);
        } else {
            if (var > 0) {
                source = id.substring(0, var);
                isSourcePresent = true;
            }
            variant = id.substring(var, sep);
            target = id.substring(sep + 1);
        }
        if (variant.length() > 0) {
            variant = variant.substring(1);
        }
        String[] strArr = new String[4];
        strArr[0] = source;
        strArr[1] = target;
        strArr[2] = variant;
        strArr[3] = isSourcePresent ? "" : null;
        return strArr;
    }

    public static String STVtoID(String source, String target, String variant) {
        StringBuilder id = new StringBuilder(source);
        if (id.length() == 0) {
            id.append(ANY);
        }
        id.append(TARGET_SEP).append(target);
        if (variant != null && variant.length() != 0) {
            id.append(VARIANT_SEP).append(variant);
        }
        return id.toString();
    }

    public static void registerSpecialInverse(String target, String inverseTarget, boolean bidirectional) {
        Map<CaseInsensitiveString, String> map = SPECIAL_INVERSES;
        map.put(new CaseInsensitiveString(target), inverseTarget);
        if (bidirectional && !target.equalsIgnoreCase(inverseTarget)) {
            map.put(new CaseInsensitiveString(inverseTarget), target);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0049, code lost:
        r10 = r17.charAt(r18[0]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0051, code lost:
        if (r10 != '-') goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0053, code lost:
        if (r3 == null) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0057, code lost:
        if (r10 != '/') goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0059, code lost:
        if (r4 != null) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x005b, code lost:
        r6 = r10;
        r18[0] = r18[0] + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static Specs parseFilterID(String id, int[] pos, boolean allowFilter) {
        String spec;
        String first = null;
        String source = null;
        String target = null;
        String variant = null;
        String filter = null;
        char delimiter = 0;
        int specCount = 0;
        int start = pos[0];
        while (true) {
            pos[0] = PatternProps.skipWhiteSpace(id, pos[0]);
            if (pos[0] != id.length()) {
                if (allowFilter && filter == null && UnicodeSet.resemblesPattern(id, pos[0])) {
                    ParsePosition ppos = new ParsePosition(pos[0]);
                    new UnicodeSet(id, ppos, null);
                    filter = id.substring(pos[0], ppos.getIndex());
                    pos[0] = ppos.getIndex();
                } else if ((delimiter != 0 || specCount <= 0) && (spec = Utility.parseUnicodeIdentifier(id, pos)) != null) {
                    switch (delimiter) {
                        case 0:
                            first = spec;
                            break;
                        case '-':
                            target = spec;
                            break;
                        case '/':
                            variant = spec;
                            break;
                    }
                    specCount++;
                    delimiter = 0;
                }
            }
        }
        if (first != null) {
            if (target == null) {
                target = first;
            } else {
                source = first;
            }
        }
        if (source == null && target == null) {
            pos[0] = start;
            return null;
        }
        boolean sawSource = true;
        if (source == null) {
            source = ANY;
            sawSource = false;
        }
        if (target == null) {
            target = ANY;
        }
        return new Specs(source, target, variant, sawSource, filter);
    }

    private static SingleID specsToID(Specs specs, int dir) {
        String canonID = "";
        String basicID = "";
        String basicPrefix = "";
        if (specs != null) {
            StringBuilder buf = new StringBuilder();
            if (dir != 0) {
                buf.append(specs.target).append(TARGET_SEP).append(specs.source);
            } else {
                if (specs.sawSource) {
                    buf.append(specs.source).append(TARGET_SEP);
                } else {
                    basicPrefix = specs.source + TARGET_SEP;
                }
                buf.append(specs.target);
            }
            if (specs.variant != null) {
                buf.append(VARIANT_SEP).append(specs.variant);
            }
            basicID = basicPrefix + buf.toString();
            if (specs.filter != null) {
                buf.insert(0, specs.filter);
            }
            canonID = buf.toString();
        }
        return new SingleID(canonID, basicID);
    }

    private static SingleID specsToSpecialInverse(Specs specs) {
        String inverseTarget;
        if (specs.source.equalsIgnoreCase(ANY) && (inverseTarget = SPECIAL_INVERSES.get(new CaseInsensitiveString(specs.target))) != null) {
            StringBuilder buf = new StringBuilder();
            if (specs.filter != null) {
                buf.append(specs.filter);
            }
            if (specs.sawSource) {
                buf.append(ANY).append(TARGET_SEP);
            }
            buf.append(inverseTarget);
            String basicID = "Any-" + inverseTarget;
            if (specs.variant != null) {
                buf.append(VARIANT_SEP).append(specs.variant);
                basicID = basicID + VARIANT_SEP + specs.variant;
            }
            return new SingleID(buf.toString(), basicID);
        }
        return null;
    }
}
