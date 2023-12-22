package com.ibm.icu.text;

import com.ibm.icu.impl.Assert;
import com.ibm.icu.impl.CharacterIteration;
import com.ibm.icu.text.DictionaryBreakEngine;
import com.wits.pms.statuscontrol.WitsCommand;
import java.io.IOException;
import java.text.CharacterIterator;

/* loaded from: classes.dex */
class CjkBreakEngine extends DictionaryBreakEngine {
    private static final UnicodeSet fHanWordSet;
    private static final UnicodeSet fHangulWordSet;
    private static final UnicodeSet fHiraganaWordSet;
    private static final UnicodeSet fKatakanaWordSet;
    private static final int kMaxKatakanaGroupLength = 20;
    private static final int kMaxKatakanaLength = 8;
    private static final int kint32max = Integer.MAX_VALUE;
    private static final int maxSnlp = 255;
    private DictionaryMatcher fDictionary;

    static {
        UnicodeSet unicodeSet = new UnicodeSet();
        fHangulWordSet = unicodeSet;
        UnicodeSet unicodeSet2 = new UnicodeSet();
        fHanWordSet = unicodeSet2;
        UnicodeSet unicodeSet3 = new UnicodeSet();
        fKatakanaWordSet = unicodeSet3;
        UnicodeSet unicodeSet4 = new UnicodeSet();
        fHiraganaWordSet = unicodeSet4;
        unicodeSet.applyPattern("[\\uac00-\\ud7a3]");
        unicodeSet2.applyPattern("[:Han:]");
        unicodeSet3.applyPattern("[[:Katakana:]\\uff9e\\uff9f]");
        unicodeSet4.applyPattern("[:Hiragana:]");
        unicodeSet.m87freeze();
        unicodeSet2.m87freeze();
        unicodeSet3.m87freeze();
        unicodeSet4.m87freeze();
    }

    public CjkBreakEngine(boolean korean) throws IOException {
        this.fDictionary = null;
        this.fDictionary = DictionaryData.loadDictionaryFor("Hira");
        if (korean) {
            setCharacters(fHangulWordSet);
            return;
        }
        UnicodeSet cjSet = new UnicodeSet();
        cjSet.addAll(fHanWordSet);
        cjSet.addAll(fKatakanaWordSet);
        cjSet.addAll(fHiraganaWordSet);
        cjSet.add(65392);
        cjSet.add(12540);
        setCharacters(cjSet);
    }

    public boolean equals(Object obj) {
        if (obj instanceof CjkBreakEngine) {
            CjkBreakEngine other = (CjkBreakEngine) obj;
            return this.fSet.equals(other.fSet);
        }
        return false;
    }

    public int hashCode() {
        return getClass().hashCode();
    }

    private static int getKatakanaCost(int wordlength) {
        int[] katakanaCost = {8192, 984, 408, SCSU.UQUOTEU, WitsCommand.SystemCommand.LOW_POWER_VOLTAGE_WARNING, SCSU.ARMENIANINDEX, 300, 372, 480};
        if (wordlength > 8) {
            return 8192;
        }
        return katakanaCost[wordlength];
    }

    private static boolean isKatakana(int value) {
        return (value >= 12449 && value <= 12542 && value != 12539) || (value >= 65382 && value <= 65439);
    }

    @Override // com.ibm.icu.text.DictionaryBreakEngine
    public int divideUpDictionaryRange(CharacterIterator inText, int startPos, int endPos, DictionaryBreakEngine.DequeI foundBreaks) {
        CharacterIterator text;
        int[] values;
        String prenormstr;
        int[] prev;
        int i;
        int[] bestSnlp;
        CharacterIterator text2;
        int[] lengths;
        int newSnlp;
        if (startPos >= endPos) {
            return 0;
        }
        inText.setIndex(startPos);
        int inputLength = endPos - startPos;
        int[] charPositions = new int[inputLength + 1];
        StringBuffer s = new StringBuffer("");
        inText.setIndex(startPos);
        while (inText.getIndex() < endPos) {
            s.append(inText.current());
            inText.next();
        }
        String prenormstr2 = s.toString();
        boolean isNormalized = Normalizer.quickCheck(prenormstr2, Normalizer.NFKC) == Normalizer.YES || Normalizer.isNormalized(prenormstr2, Normalizer.NFKC, 0);
        int numCodePts = 0;
        if (isNormalized) {
            CharacterIterator stringCharacterIterator = new java.text.StringCharacterIterator(prenormstr2);
            int index = 0;
            charPositions[0] = 0;
            while (index < prenormstr2.length()) {
                int codepoint = prenormstr2.codePointAt(index);
                index += Character.charCount(codepoint);
                numCodePts++;
                charPositions[numCodePts] = index;
            }
            text = stringCharacterIterator;
        } else {
            String normStr = Normalizer.normalize(prenormstr2, Normalizer.NFKC);
            CharacterIterator stringCharacterIterator2 = new java.text.StringCharacterIterator(normStr);
            charPositions = new int[normStr.length() + 1];
            Normalizer normalizer = new Normalizer(prenormstr2, Normalizer.NFKC, 0);
            int index2 = 0;
            charPositions[0] = 0;
            while (index2 < normalizer.endIndex()) {
                normalizer.next();
                numCodePts++;
                index2 = normalizer.getIndex();
                charPositions[numCodePts] = index2;
            }
            text = stringCharacterIterator2;
        }
        int[] bestSnlp2 = new int[numCodePts + 1];
        bestSnlp2[0] = 0;
        for (int i2 = 1; i2 <= numCodePts; i2++) {
            bestSnlp2[i2] = Integer.MAX_VALUE;
        }
        int i3 = numCodePts + 1;
        int[] prev2 = new int[i3];
        for (int i4 = 0; i4 <= numCodePts; i4++) {
            prev2[i4] = -1;
        }
        int[] values2 = new int[numCodePts];
        int[] lengths2 = new int[numCodePts];
        text.setIndex(0);
        boolean is_prev_katakana = false;
        int count = 0;
        while (count < numCodePts) {
            int ix = text.getIndex();
            int inputLength2 = inputLength;
            StringBuffer s2 = s;
            if (bestSnlp2[count] == Integer.MAX_VALUE) {
                lengths = lengths2;
                prenormstr = prenormstr2;
                prev = prev2;
                i = count;
                values = values2;
                bestSnlp = bestSnlp2;
                text2 = text;
            } else {
                values = values2;
                int maxSearchLength = count + 20 < numCodePts ? 20 : numCodePts - count;
                int[] count_ = new int[1];
                prenormstr = prenormstr2;
                prev = prev2;
                i = count;
                bestSnlp = bestSnlp2;
                text2 = text;
                this.fDictionary.matches(text, maxSearchLength, lengths2, count_, maxSearchLength, values);
                int count2 = count_[0];
                text2.setIndex(ix);
                if ((count2 == 0 || lengths2[0] != 1) && CharacterIteration.current32(text2) != Integer.MAX_VALUE && !fHangulWordSet.contains(CharacterIteration.current32(text2))) {
                    values[count2] = 255;
                    lengths2[count2] = 1;
                    count2++;
                }
                for (int j = 0; j < count2; j++) {
                    int newSnlp2 = bestSnlp[i] + values[j];
                    if (newSnlp2 < bestSnlp[lengths2[j] + i]) {
                        bestSnlp[lengths2[j] + i] = newSnlp2;
                        prev[lengths2[j] + i] = i;
                    }
                }
                int j2 = CharacterIteration.current32(text2);
                boolean is_katakana = isKatakana(j2);
                if (is_prev_katakana || !is_katakana) {
                    lengths = lengths2;
                } else {
                    int j3 = i + 1;
                    CharacterIteration.next32(text2);
                    while (true) {
                        if (j3 >= numCodePts) {
                            lengths = lengths2;
                            break;
                        }
                        lengths = lengths2;
                        if (j3 - i >= 20 || !isKatakana(CharacterIteration.current32(text2))) {
                            break;
                        }
                        CharacterIteration.next32(text2);
                        j3++;
                        lengths2 = lengths;
                    }
                    if (j3 - i < 20 && (newSnlp = bestSnlp[i] + getKatakanaCost(j3 - i)) < bestSnlp[j3]) {
                        bestSnlp[j3] = newSnlp;
                        prev[j3] = i;
                    }
                }
                is_prev_katakana = is_katakana;
            }
            count = i + 1;
            text2.setIndex(ix);
            CharacterIteration.next32(text2);
            text = text2;
            lengths2 = lengths;
            inputLength = inputLength2;
            s = s2;
            values2 = values;
            prenormstr2 = prenormstr;
            prev2 = prev;
            bestSnlp2 = bestSnlp;
        }
        int[] prev3 = prev2;
        int[] t_boundary = new int[numCodePts + 1];
        int numBreaks = 0;
        if (bestSnlp2[numCodePts] == Integer.MAX_VALUE) {
            t_boundary[0] = numCodePts;
            numBreaks = 0 + 1;
        } else {
            for (int i5 = numCodePts; i5 > 0; i5 = prev3[i5]) {
                t_boundary[numBreaks] = i5;
                numBreaks++;
            }
            int i6 = numBreaks - 1;
            Assert.assrt(prev3[t_boundary[i6]] == 0);
        }
        if (foundBreaks.size() == 0 || foundBreaks.peek() < startPos) {
            t_boundary[numBreaks] = 0;
            numBreaks++;
        }
        int correctedNumBreaks = 0;
        for (int i7 = numBreaks - 1; i7 >= 0; i7--) {
            int pos = charPositions[t_boundary[i7]] + startPos;
            if (!foundBreaks.contains(pos) && pos != startPos) {
                foundBreaks.push(charPositions[t_boundary[i7]] + startPos);
                correctedNumBreaks++;
            }
        }
        if (!foundBreaks.isEmpty() && foundBreaks.peek() == endPos) {
            foundBreaks.pop();
            correctedNumBreaks--;
        }
        if (!foundBreaks.isEmpty()) {
            inText.setIndex(foundBreaks.peek());
        }
        return correctedNumBreaks;
    }
}
