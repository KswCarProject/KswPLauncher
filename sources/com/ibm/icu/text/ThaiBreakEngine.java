package com.ibm.icu.text;

import com.ibm.icu.lang.UCharacter;
import java.io.IOException;

class ThaiBreakEngine extends DictionaryBreakEngine {
    private static final byte THAI_LOOKAHEAD = 3;
    private static final char THAI_MAIYAMOK = 'ๆ';
    private static final byte THAI_MIN_WORD = 2;
    private static final byte THAI_MIN_WORD_SPAN = 4;
    private static final char THAI_PAIYANNOI = 'ฯ';
    private static final byte THAI_PREFIX_COMBINE_THRESHOLD = 3;
    private static final byte THAI_ROOT_COMBINE_THRESHOLD = 3;
    private static UnicodeSet fBeginWordSet = new UnicodeSet();
    private static UnicodeSet fEndWordSet;
    private static UnicodeSet fMarkSet = new UnicodeSet();
    private static UnicodeSet fSuffixSet = new UnicodeSet();
    private static UnicodeSet fThaiWordSet = new UnicodeSet();
    private DictionaryMatcher fDictionary = DictionaryData.loadDictionaryFor("Thai");

    static {
        fThaiWordSet.applyPattern("[[:Thai:]&[:LineBreak=SA:]]");
        fThaiWordSet.compact();
        fMarkSet.applyPattern("[[:Thai:]&[:LineBreak=SA:]&[:M:]]");
        fMarkSet.add(32);
        UnicodeSet unicodeSet = new UnicodeSet(fThaiWordSet);
        fEndWordSet = unicodeSet;
        unicodeSet.remove(3633);
        fEndWordSet.remove(3648, 3652);
        fBeginWordSet.add(3585, 3630);
        fBeginWordSet.add(3648, 3652);
        fSuffixSet.add(3631);
        fSuffixSet.add(3654);
        fMarkSet.compact();
        fEndWordSet.compact();
        fBeginWordSet.compact();
        fSuffixSet.compact();
        fThaiWordSet.freeze();
        fMarkSet.freeze();
        fEndWordSet.freeze();
        fBeginWordSet.freeze();
        fSuffixSet.freeze();
    }

    public ThaiBreakEngine() throws IOException {
        setCharacters(fThaiWordSet);
    }

    public boolean equals(Object obj) {
        return obj instanceof ThaiBreakEngine;
    }

    public int hashCode() {
        return getClass().hashCode();
    }

    public boolean handles(int c) {
        return UCharacter.getIntPropertyValue(c, 4106) == 38;
    }

    /* JADX WARNING: Removed duplicated region for block: B:90:0x0096 A[EDGE_INSN: B:90:0x0096->B:30:0x0096 ?: BREAK  , SYNTHETIC] */
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
            if (r6 >= r2) goto L_0x0197
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
            goto L_0x00a0
        L_0x0041:
            if (r8 <= r9) goto L_0x00a0
            int r10 = r17.getIndex()
            if (r10 >= r2) goto L_0x0096
        L_0x0049:
            r10 = 1
            int r11 = r3 + 1
            int r11 = r11 % r4
            r11 = r5[r11]
            com.ibm.icu.text.DictionaryMatcher r12 = r0.fDictionary
            int r11 = r11.candidates(r1, r12, r2)
            if (r11 <= 0) goto L_0x008c
            r11 = 2
            if (r10 >= r11) goto L_0x0064
            int r11 = r3 % 3
            r11 = r5[r11]
            r11.markCurrent()
            r10 = 2
            r11 = r10
            goto L_0x0065
        L_0x0064:
            r11 = r10
        L_0x0065:
            int r10 = r17.getIndex()
            if (r10 < r2) goto L_0x006c
            goto L_0x0096
        L_0x006c:
            int r10 = r3 + 2
            int r10 = r10 % r4
            r10 = r5[r10]
            com.ibm.icu.text.DictionaryMatcher r12 = r0.fDictionary
            int r10 = r10.candidates(r1, r12, r2)
            if (r10 <= 0) goto L_0x0081
            int r10 = r3 % 3
            r10 = r5[r10]
            r10.markCurrent()
            goto L_0x0096
        L_0x0081:
            int r10 = r3 + 1
            int r10 = r10 % r4
            r10 = r5[r10]
            boolean r10 = r10.backUp(r1)
            if (r10 != 0) goto L_0x006c
        L_0x008c:
            int r10 = r3 % 3
            r10 = r5[r10]
            boolean r10 = r10.backUp(r1)
            if (r10 != 0) goto L_0x0049
        L_0x0096:
            int r10 = r3 % 3
            r10 = r5[r10]
            int r6 = r10.acceptMarked(r1)
            int r3 = r3 + 1
        L_0x00a0:
            int r10 = r17.getIndex()
            if (r10 >= r2) goto L_0x0107
            if (r6 >= r4) goto L_0x0107
            int r10 = r3 % 3
            r10 = r5[r10]
            com.ibm.icu.text.DictionaryMatcher r11 = r0.fDictionary
            int r10 = r10.candidates(r1, r11, r2)
            if (r10 > 0) goto L_0x0102
            if (r6 == 0) goto L_0x00c0
            int r10 = r3 % 3
            r10 = r5[r10]
            int r10 = r10.longestPrefix()
            if (r10 >= r4) goto L_0x0102
        L_0x00c0:
            int r10 = r7 + r6
            int r10 = r2 - r10
            char r11 = r17.current()
            r12 = 0
        L_0x00c9:
            r17.next()
            char r13 = r17.current()
            int r12 = r12 + r9
            int r10 = r10 + -1
            if (r10 > 0) goto L_0x00d6
            goto L_0x00fa
        L_0x00d6:
            com.ibm.icu.text.UnicodeSet r14 = fEndWordSet
            boolean r14 = r14.contains((int) r11)
            if (r14 == 0) goto L_0x0100
            com.ibm.icu.text.UnicodeSet r14 = fBeginWordSet
            boolean r14 = r14.contains((int) r13)
            if (r14 == 0) goto L_0x0100
            int r14 = r3 + 1
            int r14 = r14 % r4
            r14 = r5[r14]
            com.ibm.icu.text.DictionaryMatcher r15 = r0.fDictionary
            int r14 = r14.candidates(r1, r15, r2)
            int r15 = r7 + r6
            int r15 = r15 + r12
            r1.setIndex(r15)
            if (r14 <= 0) goto L_0x0100
        L_0x00fa:
            if (r6 > 0) goto L_0x00fe
            int r3 = r3 + 1
        L_0x00fe:
            int r6 = r6 + r12
            goto L_0x0107
        L_0x0100:
            r11 = r13
            goto L_0x00c9
        L_0x0102:
            int r9 = r7 + r6
            r1.setIndex(r9)
        L_0x0107:
            int r9 = r17.getIndex()
            r10 = r9
            if (r9 >= r2) goto L_0x0124
            com.ibm.icu.text.UnicodeSet r9 = fMarkSet
            char r11 = r17.current()
            boolean r9 = r9.contains((int) r11)
            if (r9 == 0) goto L_0x0124
            r17.next()
            int r9 = r17.getIndex()
            int r9 = r9 - r10
            int r6 = r6 + r9
            goto L_0x0107
        L_0x0124:
            int r9 = r17.getIndex()
            if (r9 >= r2) goto L_0x0181
            if (r6 <= 0) goto L_0x0181
            int r9 = r3 % 3
            r9 = r5[r9]
            com.ibm.icu.text.DictionaryMatcher r11 = r0.fDictionary
            int r9 = r9.candidates(r1, r11, r2)
            if (r9 > 0) goto L_0x017c
            com.ibm.icu.text.UnicodeSet r9 = fSuffixSet
            char r11 = r17.current()
            r12 = r11
            boolean r9 = r9.contains((int) r11)
            if (r9 == 0) goto L_0x017c
            r9 = 3631(0xe2f, float:5.088E-42)
            if (r12 != r9) goto L_0x0165
            com.ibm.icu.text.UnicodeSet r9 = fSuffixSet
            char r11 = r17.previous()
            boolean r9 = r9.contains((int) r11)
            if (r9 != 0) goto L_0x0162
            r17.next()
            r17.next()
            int r6 = r6 + 1
            char r12 = r17.current()
            goto L_0x0165
        L_0x0162:
            r17.next()
        L_0x0165:
            r9 = 3654(0xe46, float:5.12E-42)
            if (r12 != r9) goto L_0x0181
            char r11 = r17.previous()
            if (r11 == r9) goto L_0x0178
            r17.next()
            r17.next()
            int r6 = r6 + 1
            goto L_0x0181
        L_0x0178:
            r17.next()
            goto L_0x0181
        L_0x017c:
            int r9 = r7 + r6
            r1.setIndex(r9)
        L_0x0181:
            if (r6 <= 0) goto L_0x0193
            int r9 = r7 + r6
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            int r9 = r9.intValue()
            r11 = r20
            r11.push(r9)
            goto L_0x0195
        L_0x0193:
            r11 = r20
        L_0x0195:
            goto L_0x0021
        L_0x0197:
            r11 = r20
            int r4 = r20.peek()
            if (r4 < r2) goto L_0x01a4
            r20.pop()
            int r3 = r3 + -1
        L_0x01a4:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.ThaiBreakEngine.divideUpDictionaryRange(java.text.CharacterIterator, int, int, com.ibm.icu.text.DictionaryBreakEngine$DequeI):int");
    }
}
