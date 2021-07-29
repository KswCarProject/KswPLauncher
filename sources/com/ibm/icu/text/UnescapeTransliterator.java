package com.ibm.icu.text;

import com.ibm.icu.impl.Utility;
import com.ibm.icu.text.Transliterator;

class UnescapeTransliterator extends Transliterator {
    private static final char END = '￿';
    private char[] spec;

    static void register() {
        Transliterator.registerFactory("Hex-Any/Unicode", new Transliterator.Factory() {
            public Transliterator getInstance(String ID) {
                return new UnescapeTransliterator("Hex-Any/Unicode", new char[]{2, 0, 16, 4, 6, 'U', '+', 65535});
            }
        });
        Transliterator.registerFactory("Hex-Any/Java", new Transliterator.Factory() {
            public Transliterator getInstance(String ID) {
                return new UnescapeTransliterator("Hex-Any/Java", new char[]{2, 0, 16, 4, 4, '\\', 'u', 65535});
            }
        });
        Transliterator.registerFactory("Hex-Any/C", new Transliterator.Factory() {
            public Transliterator getInstance(String ID) {
                return new UnescapeTransliterator("Hex-Any/C", new char[]{2, 0, 16, 4, 4, '\\', 'u', 2, 0, 16, 8, 8, '\\', 'U', 65535});
            }
        });
        Transliterator.registerFactory("Hex-Any/XML", new Transliterator.Factory() {
            public Transliterator getInstance(String ID) {
                return new UnescapeTransliterator("Hex-Any/XML", new char[]{3, 1, 16, 1, 6, '&', '#', 'x', ';', 65535});
            }
        });
        Transliterator.registerFactory("Hex-Any/XML10", new Transliterator.Factory() {
            public Transliterator getInstance(String ID) {
                return new UnescapeTransliterator("Hex-Any/XML10", new char[]{2, 1, 10, 1, 7, '&', '#', ';', 65535});
            }
        });
        Transliterator.registerFactory("Hex-Any/Perl", new Transliterator.Factory() {
            public Transliterator getInstance(String ID) {
                return new UnescapeTransliterator("Hex-Any/Perl", new char[]{3, 1, 16, 1, 6, '\\', 'x', '{', '}', 65535});
            }
        });
        Transliterator.registerFactory("Hex-Any", new Transliterator.Factory() {
            public Transliterator getInstance(String ID) {
                return new UnescapeTransliterator("Hex-Any", new char[]{2, 0, 16, 4, 6, 'U', '+', 2, 0, 16, 4, 4, '\\', 'u', 2, 0, 16, 8, 8, '\\', 'U', 3, 1, 16, 1, 6, '&', '#', 'x', ';', 2, 1, 10, 1, 7, '&', '#', ';', 3, 1, 16, 1, 6, '\\', 'x', '{', '}', 65535});
            }
        });
    }

    UnescapeTransliterator(String ID, char[] spec2) {
        super(ID, (UnicodeFilter) null);
        this.spec = spec2;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00d5, code lost:
        if (r3 >= r4) goto L_0x000a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00d7, code lost:
        r3 = r3 + com.ibm.icu.text.UTF16.getCharCount(r1.char32At(r3));
     */
    /* JADX WARNING: Incorrect type for immutable var: ssa=char, code=int, for r6v5, types: [char] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleTransliterate(com.ibm.icu.text.Replaceable r20, com.ibm.icu.text.Transliterator.Position r21, boolean r22) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r2 = r21
            int r3 = r2.start
            int r4 = r2.limit
        L_0x000a:
            if (r3 >= r4) goto L_0x00e2
            r5 = 0
        L_0x000d:
            char[] r6 = r0.spec
            char r7 = r6[r5]
            r8 = 65535(0xffff, float:9.1834E-41)
            if (r7 == r8) goto L_0x00d5
            int r7 = r5 + 1
            char r5 = r6[r5]
            int r8 = r7 + 1
            char r7 = r6[r7]
            int r9 = r8 + 1
            char r8 = r6[r8]
            int r10 = r9 + 1
            char r9 = r6[r9]
            int r11 = r10 + 1
            char r6 = r6[r10]
            r10 = r3
            r12 = 1
            r13 = 0
        L_0x002d:
            if (r13 >= r5) goto L_0x004e
            if (r10 < r4) goto L_0x0039
            if (r13 <= 0) goto L_0x0039
            if (r22 == 0) goto L_0x0037
            goto L_0x00e2
        L_0x0037:
            r12 = 0
            goto L_0x004e
        L_0x0039:
            int r14 = r10 + 1
            char r10 = r1.charAt(r10)
            char[] r15 = r0.spec
            int r16 = r11 + r13
            char r15 = r15[r16]
            if (r10 == r15) goto L_0x004a
            r12 = 0
            r10 = r14
            goto L_0x004e
        L_0x004a:
            int r13 = r13 + 1
            r10 = r14
            goto L_0x002d
        L_0x004e:
            if (r12 == 0) goto L_0x00cb
            r14 = 0
            r15 = 0
        L_0x0052:
            if (r10 < r4) goto L_0x005d
            if (r10 <= r3) goto L_0x005a
            if (r22 == 0) goto L_0x005a
            goto L_0x00e2
        L_0x005a:
            r16 = r12
            goto L_0x0079
        L_0x005d:
            r16 = r12
            int r12 = r1.char32At(r10)
            int r17 = com.ibm.icu.lang.UCharacter.digit(r12, r8)
            if (r17 >= 0) goto L_0x006a
            goto L_0x0079
        L_0x006a:
            int r18 = com.ibm.icu.text.UTF16.getCharCount(r12)
            int r10 = r10 + r18
            int r18 = r14 * r8
            int r14 = r18 + r17
            int r15 = r15 + 1
            if (r15 != r6) goto L_0x00c6
        L_0x0079:
            if (r15 < r9) goto L_0x007d
            r12 = 1
            goto L_0x007e
        L_0x007d:
            r12 = 0
        L_0x007e:
            if (r12 == 0) goto L_0x00c3
            r13 = 0
        L_0x0081:
            if (r13 >= r7) goto L_0x00ac
            if (r10 < r4) goto L_0x008e
            if (r10 <= r3) goto L_0x008a
            if (r22 == 0) goto L_0x008a
            goto L_0x00e2
        L_0x008a:
            r12 = 0
            r18 = r6
            goto L_0x00ae
        L_0x008e:
            int r16 = r10 + 1
            char r10 = r1.charAt(r10)
            r18 = r6
            char[] r6 = r0.spec
            int r17 = r11 + r5
            int r17 = r17 + r13
            char r6 = r6[r17]
            if (r10 == r6) goto L_0x00a5
            r6 = 0
            r12 = r6
            r10 = r16
            goto L_0x00ae
        L_0x00a5:
            int r13 = r13 + 1
            r10 = r16
            r6 = r18
            goto L_0x0081
        L_0x00ac:
            r18 = r6
        L_0x00ae:
            if (r12 == 0) goto L_0x00cf
            java.lang.String r6 = com.ibm.icu.text.UTF16.valueOf(r14)
            r1.replace(r3, r10, r6)
            int r16 = r10 - r3
            int r17 = r6.length()
            int r16 = r16 - r17
            int r4 = r4 - r16
            r5 = r11
            goto L_0x00d5
        L_0x00c3:
            r18 = r6
            goto L_0x00cf
        L_0x00c6:
            r18 = r6
            r12 = r16
            goto L_0x0052
        L_0x00cb:
            r18 = r6
            r16 = r12
        L_0x00cf:
            int r6 = r5 + r7
            int r5 = r11 + r6
            goto L_0x000d
        L_0x00d5:
            if (r3 >= r4) goto L_0x000a
            int r6 = r1.char32At(r3)
            int r6 = com.ibm.icu.text.UTF16.getCharCount(r6)
            int r3 = r3 + r6
            goto L_0x000a
        L_0x00e2:
            int r5 = r2.contextLimit
            int r6 = r2.limit
            int r6 = r4 - r6
            int r5 = r5 + r6
            r2.contextLimit = r5
            r2.limit = r4
            r2.start = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.UnescapeTransliterator.handleTransliterate(com.ibm.icu.text.Replaceable, com.ibm.icu.text.Transliterator$Position, boolean):void");
    }

    public void addSourceTargetSet(UnicodeSet inputFilter, UnicodeSet sourceSet, UnicodeSet targetSet) {
        UnicodeSet myFilter = getFilterAsUnicodeSet(inputFilter);
        UnicodeSet items = new UnicodeSet();
        StringBuilder buffer = new StringBuilder();
        int i = 0;
        while (true) {
            char[] cArr = this.spec;
            if (cArr[i] == 65535) {
                break;
            }
            int end = cArr[i] + i + cArr[i + 1] + 5;
            char radix = cArr[i + 2];
            for (int j = 0; j < radix; j++) {
                Utility.appendNumber(buffer, j, radix, 0);
            }
            for (int j2 = i + 5; j2 < end; j2++) {
                items.add((int) this.spec[j2]);
            }
            i = end;
        }
        items.addAll((CharSequence) buffer.toString());
        items.retainAll(myFilter);
        if (items.size() > 0) {
            sourceSet.addAll(items);
            targetSet.addAll(0, 1114111);
        }
    }
}
