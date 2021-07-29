package com.ibm.icu.text;

import com.ibm.icu.lang.UCharacter;
import java.io.IOException;

class BurmeseBreakEngine extends DictionaryBreakEngine {
    private static final byte BURMESE_LOOKAHEAD = 3;
    private static final byte BURMESE_MIN_WORD = 2;
    private static final byte BURMESE_PREFIX_COMBINE_THRESHOLD = 3;
    private static final byte BURMESE_ROOT_COMBINE_THRESHOLD = 3;
    private static UnicodeSet fBeginWordSet = new UnicodeSet();
    private static UnicodeSet fBurmeseWordSet = new UnicodeSet();
    private static UnicodeSet fEndWordSet = new UnicodeSet(fBurmeseWordSet);
    private static UnicodeSet fMarkSet = new UnicodeSet();
    private DictionaryMatcher fDictionary = DictionaryData.loadDictionaryFor("Mymr");

    static {
        fBurmeseWordSet.applyPattern("[[:Mymr:]&[:LineBreak=SA:]]");
        fBurmeseWordSet.compact();
        fMarkSet.applyPattern("[[:Mymr:]&[:LineBreak=SA:]&[:M:]]");
        fMarkSet.add(32);
        fBeginWordSet.add(4096, 4138);
        fMarkSet.compact();
        fEndWordSet.compact();
        fBeginWordSet.compact();
        fBurmeseWordSet.freeze();
        fMarkSet.freeze();
        fEndWordSet.freeze();
        fBeginWordSet.freeze();
    }

    public BurmeseBreakEngine() throws IOException {
        setCharacters(fBurmeseWordSet);
    }

    public boolean equals(Object obj) {
        return obj instanceof BurmeseBreakEngine;
    }

    public int hashCode() {
        return getClass().hashCode();
    }

    public boolean handles(int c) {
        return UCharacter.getIntPropertyValue(c, 4106) == 28;
    }

    /* JADX WARNING: Removed duplicated region for block: B:59:0x012a  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x013a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int divideUpDictionaryRange(java.text.CharacterIterator r17, int r18, int r19, com.ibm.icu.text.DictionaryBreakEngine.DequeI r20) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r19
            int r3 = r2 - r18
            r4 = 2
            if (r3 >= r4) goto L_0x000d
            r3 = 0
            return r3
        L_0x000d:
            r3 = 0
            r5 = 3
            com.ibm.icu.text.DictionaryBreakEngine$PossibleWord[] r6 = new com.ibm.icu.text.DictionaryBreakEngine.PossibleWord[r5]
            r7 = 0
        L_0x0012:
            if (r7 >= r5) goto L_0x001e
            com.ibm.icu.text.DictionaryBreakEngine$PossibleWord r8 = new com.ibm.icu.text.DictionaryBreakEngine$PossibleWord
            r8.<init>()
            r6[r7] = r8
            int r7 = r7 + 1
            goto L_0x0012
        L_0x001e:
            r17.setIndex(r18)
        L_0x0021:
            int r7 = r17.getIndex()
            r8 = r7
            if (r7 >= r2) goto L_0x013f
            r7 = 0
            int r9 = r3 % 3
            r9 = r6[r9]
            com.ibm.icu.text.DictionaryMatcher r10 = r0.fDictionary
            int r9 = r9.candidates(r1, r10, r2)
            r10 = 1
            if (r9 != r10) goto L_0x0041
            int r11 = r3 % 3
            r11 = r6[r11]
            int r7 = r11.acceptMarked(r1)
            int r3 = r3 + 1
            goto L_0x00a3
        L_0x0041:
            if (r9 <= r10) goto L_0x00a3
            r11 = 0
            int r12 = r17.getIndex()
            if (r12 >= r2) goto L_0x0099
        L_0x004a:
            r12 = 1
            int r13 = r3 + 1
            int r13 = r13 % r5
            r13 = r6[r13]
            com.ibm.icu.text.DictionaryMatcher r14 = r0.fDictionary
            int r13 = r13.candidates(r1, r14, r2)
            if (r13 <= 0) goto L_0x008d
            if (r12 >= r4) goto L_0x0064
            int r13 = r3 % 3
            r13 = r6[r13]
            r13.markCurrent()
            r12 = 2
            r13 = r12
            goto L_0x0065
        L_0x0064:
            r13 = r12
        L_0x0065:
            int r12 = r17.getIndex()
            if (r12 < r2) goto L_0x006c
            goto L_0x0099
        L_0x006c:
            int r12 = r3 + 2
            int r12 = r12 % r5
            r12 = r6[r12]
            com.ibm.icu.text.DictionaryMatcher r14 = r0.fDictionary
            int r12 = r12.candidates(r1, r14, r2)
            if (r12 <= 0) goto L_0x0082
            int r12 = r3 % 3
            r12 = r6[r12]
            r12.markCurrent()
            r11 = 1
            goto L_0x008d
        L_0x0082:
            int r12 = r3 + 1
            int r12 = r12 % r5
            r12 = r6[r12]
            boolean r12 = r12.backUp(r1)
            if (r12 != 0) goto L_0x006c
        L_0x008d:
            int r12 = r3 % 3
            r12 = r6[r12]
            boolean r12 = r12.backUp(r1)
            if (r12 == 0) goto L_0x0099
            if (r11 == 0) goto L_0x004a
        L_0x0099:
            int r12 = r3 % 3
            r12 = r6[r12]
            int r7 = r12.acceptMarked(r1)
            int r3 = r3 + 1
        L_0x00a3:
            int r11 = r17.getIndex()
            if (r11 >= r2) goto L_0x010b
            if (r7 >= r5) goto L_0x010b
            int r11 = r3 % 3
            r11 = r6[r11]
            com.ibm.icu.text.DictionaryMatcher r12 = r0.fDictionary
            int r11 = r11.candidates(r1, r12, r2)
            if (r11 > 0) goto L_0x0106
            if (r7 == 0) goto L_0x00c3
            int r11 = r3 % 3
            r11 = r6[r11]
            int r11 = r11.longestPrefix()
            if (r11 >= r5) goto L_0x0106
        L_0x00c3:
            int r11 = r8 + r7
            int r11 = r2 - r11
            char r12 = r17.current()
            r13 = 0
        L_0x00cc:
            r17.next()
            char r14 = r17.current()
            int r13 = r13 + r10
            int r11 = r11 + -1
            if (r11 > 0) goto L_0x00d9
            goto L_0x00fd
        L_0x00d9:
            com.ibm.icu.text.UnicodeSet r15 = fEndWordSet
            boolean r15 = r15.contains((int) r12)
            if (r15 == 0) goto L_0x0103
            com.ibm.icu.text.UnicodeSet r15 = fBeginWordSet
            boolean r15 = r15.contains((int) r14)
            if (r15 == 0) goto L_0x0103
            int r15 = r3 + 1
            int r15 = r15 % r5
            r15 = r6[r15]
            com.ibm.icu.text.DictionaryMatcher r4 = r0.fDictionary
            int r4 = r15.candidates(r1, r4, r2)
            int r15 = r8 + r7
            int r15 = r15 + r13
            r1.setIndex(r15)
            if (r4 <= 0) goto L_0x0103
        L_0x00fd:
            if (r7 > 0) goto L_0x0101
            int r3 = r3 + 1
        L_0x0101:
            int r7 = r7 + r13
            goto L_0x010b
        L_0x0103:
            r12 = r14
            r4 = 2
            goto L_0x00cc
        L_0x0106:
            int r4 = r8 + r7
            r1.setIndex(r4)
        L_0x010b:
            int r4 = r17.getIndex()
            r10 = r4
            if (r4 >= r2) goto L_0x0128
            com.ibm.icu.text.UnicodeSet r4 = fMarkSet
            char r11 = r17.current()
            boolean r4 = r4.contains((int) r11)
            if (r4 == 0) goto L_0x0128
            r17.next()
            int r4 = r17.getIndex()
            int r4 = r4 - r10
            int r7 = r7 + r4
            goto L_0x010b
        L_0x0128:
            if (r7 <= 0) goto L_0x013a
            int r4 = r8 + r7
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            int r4 = r4.intValue()
            r11 = r20
            r11.push(r4)
            goto L_0x013c
        L_0x013a:
            r11 = r20
        L_0x013c:
            r4 = 2
            goto L_0x0021
        L_0x013f:
            r11 = r20
            int r4 = r20.peek()
            if (r4 < r2) goto L_0x014c
            r20.pop()
            int r3 = r3 + -1
        L_0x014c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.BurmeseBreakEngine.divideUpDictionaryRange(java.text.CharacterIterator, int, int, com.ibm.icu.text.DictionaryBreakEngine$DequeI):int");
    }
}
