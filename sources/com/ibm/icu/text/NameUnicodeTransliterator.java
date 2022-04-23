package com.ibm.icu.text;

import com.ibm.icu.text.Transliterator;

class NameUnicodeTransliterator extends Transliterator {
    static final char CLOSE_DELIM = '}';
    static final char OPEN_DELIM = '\\';
    static final String OPEN_PAT = "\\N~{~";
    static final char SPACE = ' ';
    static final String _ID = "Name-Any";

    static void register() {
        Transliterator.registerFactory(_ID, new Transliterator.Factory() {
            public Transliterator getInstance(String ID) {
                return new NameUnicodeTransliterator((UnicodeFilter) null);
            }
        });
    }

    public NameUnicodeTransliterator(UnicodeFilter filter) {
        super(_ID, filter);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleTransliterate(com.ibm.icu.text.Replaceable r13, com.ibm.icu.text.Transliterator.Position r14, boolean r15) {
        /*
            r12 = this;
            com.ibm.icu.impl.UCharacterName r0 = com.ibm.icu.impl.UCharacterName.INSTANCE
            int r0 = r0.getMaxCharNameLength()
            int r0 = r0 + 1
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>(r0)
            com.ibm.icu.text.UnicodeSet r2 = new com.ibm.icu.text.UnicodeSet
            r2.<init>()
            com.ibm.icu.impl.UCharacterName r3 = com.ibm.icu.impl.UCharacterName.INSTANCE
            r3.getCharNameCharacters(r2)
            int r3 = r14.start
            int r4 = r14.limit
            r5 = 0
            r6 = -1
        L_0x001d:
            if (r3 >= r4) goto L_0x00b7
            int r7 = r13.char32At(r3)
            switch(r5) {
                case 0: goto L_0x0099;
                case 1: goto L_0x0028;
                default: goto L_0x0026;
            }
        L_0x0026:
            goto L_0x00b0
        L_0x0028:
            boolean r8 = com.ibm.icu.impl.PatternProps.isWhiteSpace(r7)
            r9 = 32
            if (r8 == 0) goto L_0x004d
            int r8 = r1.length()
            if (r8 <= 0) goto L_0x00b0
            int r8 = r1.length()
            int r8 = r8 + -1
            char r8 = r1.charAt(r8)
            if (r8 == r9) goto L_0x00b0
            r1.append(r9)
            int r8 = r1.length()
            if (r8 <= r0) goto L_0x00b0
            r5 = 0
            goto L_0x00b0
        L_0x004d:
            r8 = 125(0x7d, float:1.75E-43)
            if (r7 != r8) goto L_0x0084
            int r8 = r1.length()
            if (r8 <= 0) goto L_0x0064
            int r10 = r8 + -1
            char r10 = r1.charAt(r10)
            if (r10 != r9) goto L_0x0064
            int r8 = r8 + -1
            r1.setLength(r8)
        L_0x0064:
            java.lang.String r9 = r1.toString()
            int r7 = com.ibm.icu.lang.UCharacter.getCharFromExtendedName(r9)
            r9 = -1
            if (r7 == r9) goto L_0x0081
            int r3 = r3 + 1
            java.lang.String r9 = com.ibm.icu.text.UTF16.valueOf(r7)
            r13.replace(r6, r3, r9)
            int r10 = r3 - r6
            int r11 = r9.length()
            int r10 = r10 - r11
            int r3 = r3 - r10
            int r4 = r4 - r10
        L_0x0081:
            r5 = 0
            r6 = -1
            goto L_0x001d
        L_0x0084:
            boolean r8 = r2.contains((int) r7)
            if (r8 == 0) goto L_0x0095
            com.ibm.icu.text.UTF16.append(r1, r7)
            int r8 = r1.length()
            if (r8 < r0) goto L_0x00b0
            r5 = 0
            goto L_0x00b0
        L_0x0095:
            int r3 = r3 + -1
            r5 = 0
            goto L_0x00b0
        L_0x0099:
            r8 = 92
            if (r7 != r8) goto L_0x00b0
            r6 = r3
            java.lang.String r8 = "\\N~{~"
            int r8 = com.ibm.icu.impl.Utility.parsePattern(r8, r13, r3, r4)
            if (r8 < 0) goto L_0x00b0
            if (r8 >= r4) goto L_0x00b0
            r5 = 1
            r9 = 0
            r1.setLength(r9)
            r3 = r8
            goto L_0x001d
        L_0x00b0:
            int r8 = com.ibm.icu.text.UTF16.getCharCount(r7)
            int r3 = r3 + r8
            goto L_0x001d
        L_0x00b7:
            int r7 = r14.contextLimit
            int r8 = r14.limit
            int r8 = r4 - r8
            int r7 = r7 + r8
            r14.contextLimit = r7
            r14.limit = r4
            if (r15 == 0) goto L_0x00c8
            if (r6 < 0) goto L_0x00c8
            r7 = r6
            goto L_0x00c9
        L_0x00c8:
            r7 = r3
        L_0x00c9:
            r14.start = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.NameUnicodeTransliterator.handleTransliterate(com.ibm.icu.text.Replaceable, com.ibm.icu.text.Transliterator$Position, boolean):void");
    }

    public void addSourceTargetSet(UnicodeSet inputFilter, UnicodeSet sourceSet, UnicodeSet targetSet) {
        UnicodeSet myFilter = getFilterAsUnicodeSet(inputFilter);
        if (myFilter.containsAll("\\N{") && myFilter.contains(125)) {
            UnicodeSet items = new UnicodeSet().addAll(48, 57).addAll(65, 70).addAll(97, 122).add(60).add(62).add(40).add(41).add(45).add(32).addAll((CharSequence) "\\N{").add(125);
            items.retainAll(myFilter);
            if (items.size() > 0) {
                sourceSet.addAll(items);
                targetSet.addAll(0, 1114111);
            }
        }
    }
}
