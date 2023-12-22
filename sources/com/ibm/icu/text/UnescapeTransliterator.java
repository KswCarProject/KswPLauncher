package com.ibm.icu.text;

import com.ibm.icu.impl.Utility;
import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.text.Transliterator;
import kotlin.text.Typography;

/* loaded from: classes.dex */
class UnescapeTransliterator extends Transliterator {
    private static final char END = '\uffff';
    private char[] spec;

    static void register() {
        Transliterator.registerFactory("Hex-Any/Unicode", new Transliterator.Factory() { // from class: com.ibm.icu.text.UnescapeTransliterator.1
            @Override // com.ibm.icu.text.Transliterator.Factory
            public Transliterator getInstance(String ID) {
                return new UnescapeTransliterator("Hex-Any/Unicode", new char[]{2, 0, 16, 4, 6, 'U', '+', '\uffff'});
            }
        });
        Transliterator.registerFactory("Hex-Any/Java", new Transliterator.Factory() { // from class: com.ibm.icu.text.UnescapeTransliterator.2
            @Override // com.ibm.icu.text.Transliterator.Factory
            public Transliterator getInstance(String ID) {
                return new UnescapeTransliterator("Hex-Any/Java", new char[]{2, 0, 16, 4, 4, '\\', 'u', '\uffff'});
            }
        });
        Transliterator.registerFactory("Hex-Any/C", new Transliterator.Factory() { // from class: com.ibm.icu.text.UnescapeTransliterator.3
            @Override // com.ibm.icu.text.Transliterator.Factory
            public Transliterator getInstance(String ID) {
                return new UnescapeTransliterator("Hex-Any/C", new char[]{2, 0, 16, 4, 4, '\\', 'u', 2, 0, 16, '\b', '\b', '\\', 'U', '\uffff'});
            }
        });
        Transliterator.registerFactory("Hex-Any/XML", new Transliterator.Factory() { // from class: com.ibm.icu.text.UnescapeTransliterator.4
            @Override // com.ibm.icu.text.Transliterator.Factory
            public Transliterator getInstance(String ID) {
                return new UnescapeTransliterator("Hex-Any/XML", new char[]{3, 1, 16, 1, 6, Typography.amp, '#', 'x', ';', '\uffff'});
            }
        });
        Transliterator.registerFactory("Hex-Any/XML10", new Transliterator.Factory() { // from class: com.ibm.icu.text.UnescapeTransliterator.5
            @Override // com.ibm.icu.text.Transliterator.Factory
            public Transliterator getInstance(String ID) {
                return new UnescapeTransliterator("Hex-Any/XML10", new char[]{2, 1, '\n', 1, 7, Typography.amp, '#', ';', '\uffff'});
            }
        });
        Transliterator.registerFactory("Hex-Any/Perl", new Transliterator.Factory() { // from class: com.ibm.icu.text.UnescapeTransliterator.6
            @Override // com.ibm.icu.text.Transliterator.Factory
            public Transliterator getInstance(String ID) {
                return new UnescapeTransliterator("Hex-Any/Perl", new char[]{3, 1, 16, 1, 6, '\\', 'x', '{', '}', '\uffff'});
            }
        });
        Transliterator.registerFactory("Hex-Any", new Transliterator.Factory() { // from class: com.ibm.icu.text.UnescapeTransliterator.7
            @Override // com.ibm.icu.text.Transliterator.Factory
            public Transliterator getInstance(String ID) {
                return new UnescapeTransliterator("Hex-Any", new char[]{2, 0, 16, 4, 6, 'U', '+', 2, 0, 16, 4, 4, '\\', 'u', 2, 0, 16, '\b', '\b', '\\', 'U', 3, 1, 16, 1, 6, Typography.amp, '#', 'x', ';', 2, 1, '\n', 1, 7, Typography.amp, '#', ';', 3, 1, 16, 1, 6, '\\', 'x', '{', '}', '\uffff'});
            }
        });
    }

    UnescapeTransliterator(String ID, char[] spec) {
        super(ID, null);
        this.spec = spec;
    }

    /* JADX WARN: Code restructure failed: missing block: B:52:0x00d5, code lost:
        if (r3 >= r4) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00d7, code lost:
        r3 = r3 + com.ibm.icu.text.UTF16.getCharCount(r20.char32At(r3));
     */
    @Override // com.ibm.icu.text.Transliterator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void handleTransliterate(Replaceable text, Transliterator.Position pos, boolean isIncremental) {
        int start = pos.start;
        int limit = pos.limit;
        loop0: while (start < limit) {
            int prefixLen = 0;
            while (true) {
                char[] cArr = this.spec;
                if (cArr[prefixLen] == '\uffff') {
                    break;
                }
                int ipat = prefixLen + 1;
                char c = cArr[prefixLen];
                int ipat2 = ipat + 1;
                char c2 = cArr[ipat];
                int ipat3 = ipat2 + 1;
                char c3 = cArr[ipat2];
                int ipat4 = ipat3 + 1;
                char c4 = cArr[ipat3];
                int ipat5 = ipat4 + 1;
                char c5 = cArr[ipat4];
                int s = start;
                boolean match = true;
                int i = 0;
                while (true) {
                    if (i >= c) {
                        break;
                    } else if (s >= limit && i > 0) {
                        if (isIncremental) {
                            break loop0;
                        }
                        match = false;
                    } else {
                        int s2 = s + 1;
                        char c6 = text.charAt(s);
                        if (c6 == this.spec[ipat5 + i]) {
                            i++;
                            s = s2;
                        } else {
                            match = false;
                            s = s2;
                            break;
                        }
                    }
                }
                if (match) {
                    int u = 0;
                    int digitCount = 0;
                    while (true) {
                        if (s >= limit) {
                            if (s > start && isIncremental) {
                                break loop0;
                            }
                        } else {
                            boolean match2 = match;
                            int ch = text.char32At(s);
                            int digit = UCharacter.digit(ch, c3);
                            if (digit < 0) {
                                break;
                            }
                            s += UTF16.getCharCount(ch);
                            u = (u * c3) + digit;
                            digitCount++;
                            if (digitCount == c5) {
                                break;
                            }
                            match = match2;
                        }
                    }
                    boolean match3 = digitCount >= c4;
                    if (match3) {
                        int i2 = 0;
                        while (true) {
                            if (i2 >= c2) {
                                break;
                            } else if (s >= limit) {
                                if (s > start && isIncremental) {
                                    break loop0;
                                }
                                match3 = false;
                            } else {
                                int s3 = s + 1;
                                char c7 = text.charAt(s);
                                char c8 = c5;
                                if (c7 == this.spec[ipat5 + c + i2]) {
                                    i2++;
                                    s = s3;
                                    c5 = c8;
                                } else {
                                    match3 = false;
                                    s = s3;
                                    break;
                                }
                            }
                        }
                        if (match3) {
                            String str = UTF16.valueOf(u);
                            text.replace(start, s, str);
                            limit -= (s - start) - str.length();
                            break;
                        }
                    }
                }
                int maxDigits = c + c2;
                prefixLen = ipat5 + maxDigits;
            }
        }
        pos.contextLimit += limit - pos.limit;
        pos.limit = limit;
        pos.start = start;
    }

    @Override // com.ibm.icu.text.Transliterator
    public void addSourceTargetSet(UnicodeSet inputFilter, UnicodeSet sourceSet, UnicodeSet targetSet) {
        UnicodeSet myFilter = getFilterAsUnicodeSet(inputFilter);
        UnicodeSet items = new UnicodeSet();
        StringBuilder buffer = new StringBuilder();
        int i = 0;
        while (true) {
            char[] cArr = this.spec;
            if (cArr[i] == '\uffff') {
                break;
            }
            int end = cArr[i] + i + cArr[i + 1] + 5;
            char c = cArr[i + 2];
            for (int j = 0; j < c; j++) {
                Utility.appendNumber(buffer, j, c, 0);
            }
            for (int j2 = i + 5; j2 < end; j2++) {
                items.add(this.spec[j2]);
            }
            i = end;
        }
        items.addAll(buffer.toString());
        items.retainAll(myFilter);
        if (items.size() > 0) {
            sourceSet.addAll(items);
            targetSet.addAll(0, 1114111);
        }
    }
}
