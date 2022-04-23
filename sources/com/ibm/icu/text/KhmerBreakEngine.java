package com.ibm.icu.text;

import com.ibm.icu.lang.UCharacter;
import java.io.IOException;

class KhmerBreakEngine extends DictionaryBreakEngine {
    private static final byte KHMER_LOOKAHEAD = 3;
    private static final byte KHMER_MIN_WORD = 2;
    private static final byte KHMER_MIN_WORD_SPAN = 4;
    private static final byte KHMER_PREFIX_COMBINE_THRESHOLD = 3;
    private static final byte KHMER_ROOT_COMBINE_THRESHOLD = 3;
    private static UnicodeSet fBeginWordSet = new UnicodeSet();
    private static UnicodeSet fEndWordSet = new UnicodeSet(fKhmerWordSet);
    private static UnicodeSet fKhmerWordSet = new UnicodeSet();
    private static UnicodeSet fMarkSet = new UnicodeSet();
    private DictionaryMatcher fDictionary = DictionaryData.loadDictionaryFor("Khmr");

    static {
        fKhmerWordSet.applyPattern("[[:Khmer:]&[:LineBreak=SA:]]");
        fKhmerWordSet.compact();
        fMarkSet.applyPattern("[[:Khmer:]&[:LineBreak=SA:]&[:M:]]");
        fMarkSet.add(32);
        fBeginWordSet.add(6016, 6067);
        fEndWordSet.remove(6098);
        fMarkSet.compact();
        fEndWordSet.compact();
        fBeginWordSet.compact();
        fKhmerWordSet.freeze();
        fMarkSet.freeze();
        fEndWordSet.freeze();
        fBeginWordSet.freeze();
    }

    public KhmerBreakEngine() throws IOException {
        setCharacters(fKhmerWordSet);
    }

    public boolean equals(Object obj) {
        return obj instanceof KhmerBreakEngine;
    }

    public int hashCode() {
        return getClass().hashCode();
    }

    public boolean handles(int c) {
        return UCharacter.getIntPropertyValue(c, 4106) == 23;
    }

    /* JADX WARNING: Removed duplicated region for block: B:60:0x012a  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x013a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int divideUpDictionaryRange(java.text.CharacterIterator r17, int r18, int r19, com.ibm.icu.text.DictionaryBreakEngine.DequeI r20) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r19
            int r3 = r2 - r18
            r4 = 4
            if (r3 >= r4) goto L_0x000d
            r3 = 0
            return r3
        L_0x000d:
            r3 = 0
            r4 = 3
            com.ibm.icu.text.DictionaryBreakEngine$PossibleWord[] r5 = new com.ibm.icu.text.DictionaryBreakEngine.PossibleWord[r4]
            r6 = 0
        L_0x0012:
            if (r6 >= r4) goto L_0x001e
            com.ibm.icu.text.DictionaryBreakEngine$PossibleWord r7 = new com.ibm.icu.text.DictionaryBreakEngine$PossibleWord
            r7.<init>()
            r5[r6] = r7
            int r6 = r6 + 1
            goto L_0x0012
        L_0x001e:
            r17.setIndex(r18)
        L_0x0021:
            int r6 = r17.getIndex()
            r7 = r6
            if (r6 >= r2) goto L_0x013e
            r6 = 0
            int r8 = r3 % 3
            r8 = r5[r8]
            com.ibm.icu.text.DictionaryMatcher r9 = r0.fDictionary
            int r8 = r8.candidates(r1, r9, r2)
            r9 = 1
            if (r8 != r9) goto L_0x0041
            int r10 = r3 % 3
            r10 = r5[r10]
            int r6 = r10.acceptMarked(r1)
            int r3 = r3 + 1
            goto L_0x00a4
        L_0x0041:
            if (r8 <= r9) goto L_0x00a4
            r10 = 0
            int r11 = r17.getIndex()
            if (r11 >= r2) goto L_0x009a
        L_0x004a:
            r11 = 1
            int r12 = r3 + 1
            int r12 = r12 % r4
            r12 = r5[r12]
            com.ibm.icu.text.DictionaryMatcher r13 = r0.fDictionary
            int r12 = r12.candidates(r1, r13, r2)
            if (r12 <= 0) goto L_0x008e
            r12 = 2
            if (r11 >= r12) goto L_0x0065
            int r12 = r3 % 3
            r12 = r5[r12]
            r12.markCurrent()
            r11 = 2
            r12 = r11
            goto L_0x0066
        L_0x0065:
            r12 = r11
        L_0x0066:
            int r11 = r17.getIndex()
            if (r11 < r2) goto L_0x006d
            goto L_0x009a
        L_0x006d:
            int r11 = r3 + 2
            int r11 = r11 % r4
            r11 = r5[r11]
            com.ibm.icu.text.DictionaryMatcher r13 = r0.fDictionary
            int r11 = r11.candidates(r1, r13, r2)
            if (r11 <= 0) goto L_0x0083
            int r11 = r3 % 3
            r11 = r5[r11]
            r11.markCurrent()
            r10 = 1
            goto L_0x008e
        L_0x0083:
            int r11 = r3 + 1
            int r11 = r11 % r4
            r11 = r5[r11]
            boolean r11 = r11.backUp(r1)
            if (r11 != 0) goto L_0x006d
        L_0x008e:
            int r11 = r3 % 3
            r11 = r5[r11]
            boolean r11 = r11.backUp(r1)
            if (r11 == 0) goto L_0x009a
            if (r10 == 0) goto L_0x004a
        L_0x009a:
            int r11 = r3 % 3
            r11 = r5[r11]
            int r6 = r11.acceptMarked(r1)
            int r3 = r3 + 1
        L_0x00a4:
            int r10 = r17.getIndex()
            if (r10 >= r2) goto L_0x010b
            if (r6 >= r4) goto L_0x010b
            int r10 = r3 % 3
            r10 = r5[r10]
            com.ibm.icu.text.DictionaryMatcher r11 = r0.fDictionary
            int r10 = r10.candidates(r1, r11, r2)
            if (r10 > 0) goto L_0x0106
            if (r6 == 0) goto L_0x00c4
            int r10 = r3 % 3
            r10 = r5[r10]
            int r10 = r10.longestPrefix()
            if (r10 >= r4) goto L_0x0106
        L_0x00c4:
            int r10 = r7 + r6
            int r10 = r2 - r10
            char r11 = r17.current()
            r12 = 0
        L_0x00cd:
            r17.next()
            char r13 = r17.current()
            int r12 = r12 + r9
            int r10 = r10 + -1
            if (r10 > 0) goto L_0x00da
            goto L_0x00fe
        L_0x00da:
            com.ibm.icu.text.UnicodeSet r14 = fEndWordSet
            boolean r14 = r14.contains((int) r11)
            if (r14 == 0) goto L_0x0104
            com.ibm.icu.text.UnicodeSet r14 = fBeginWordSet
            boolean r14 = r14.contains((int) r13)
            if (r14 == 0) goto L_0x0104
            int r14 = r3 + 1
            int r14 = r14 % r4
            r14 = r5[r14]
            com.ibm.icu.text.DictionaryMatcher r15 = r0.fDictionary
            int r14 = r14.candidates(r1, r15, r2)
            int r15 = r7 + r6
            int r15 = r15 + r12
            r1.setIndex(r15)
            if (r14 <= 0) goto L_0x0104
        L_0x00fe:
            if (r6 > 0) goto L_0x0102
            int r3 = r3 + 1
        L_0x0102:
            int r6 = r6 + r12
            goto L_0x010b
        L_0x0104:
            r11 = r13
            goto L_0x00cd
        L_0x0106:
            int r9 = r7 + r6
            r1.setIndex(r9)
        L_0x010b:
            int r9 = r17.getIndex()
            r10 = r9
            if (r9 >= r2) goto L_0x0128
            com.ibm.icu.text.UnicodeSet r9 = fMarkSet
            char r11 = r17.current()
            boolean r9 = r9.contains((int) r11)
            if (r9 == 0) goto L_0x0128
            r17.next()
            int r9 = r17.getIndex()
            int r9 = r9 - r10
            int r6 = r6 + r9
            goto L_0x010b
        L_0x0128:
            if (r6 <= 0) goto L_0x013a
            int r9 = r7 + r6
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            int r9 = r9.intValue()
            r11 = r20
            r11.push(r9)
            goto L_0x013c
        L_0x013a:
            r11 = r20
        L_0x013c:
            goto L_0x0021
        L_0x013e:
            r11 = r20
            int r4 = r20.peek()
            if (r4 < r2) goto L_0x014b
            r20.pop()
            int r3 = r3 + -1
        L_0x014b:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.KhmerBreakEngine.divideUpDictionaryRange(java.text.CharacterIterator, int, int, com.ibm.icu.text.DictionaryBreakEngine$DequeI):int");
    }
}
