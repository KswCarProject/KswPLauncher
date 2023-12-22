package com.ibm.icu.text;

import com.ibm.icu.util.BytesTrie;
import com.ibm.icu.util.CharsTrie;
import java.text.CharacterIterator;

/* loaded from: classes.dex */
class CharsDictionaryMatcher extends DictionaryMatcher {
    private CharSequence characters;

    public CharsDictionaryMatcher(CharSequence chars) {
        this.characters = chars;
    }

    @Override // com.ibm.icu.text.DictionaryMatcher
    public int matches(CharacterIterator text_, int maxLength, int[] lengths, int[] count_, int limit, int[] values) {
        int c;
        UCharacterIterator text = UCharacterIterator.getInstance(text_);
        CharsTrie uct = new CharsTrie(this.characters, 0);
        int c2 = text.nextCodePoint();
        if (c2 == -1) {
            return 0;
        }
        BytesTrie.Result result = uct.firstForCodePoint(c2);
        int numChars = 1;
        int count = 0;
        while (true) {
            if (result.hasValue()) {
                if (count < limit) {
                    if (values != null) {
                        values[count] = uct.getValue();
                    }
                    lengths[count] = numChars;
                    count++;
                }
                if (result == BytesTrie.Result.FINAL_VALUE) {
                    break;
                }
                if (numChars < maxLength || (c = text.nextCodePoint()) == -1) {
                    break;
                    break;
                }
                numChars++;
                result = uct.nextForCodePoint(c);
            } else {
                if (result == BytesTrie.Result.NO_MATCH) {
                    break;
                }
                if (numChars < maxLength) {
                    break;
                }
                numChars++;
                result = uct.nextForCodePoint(c);
            }
        }
        count_[0] = count;
        return numChars;
    }

    @Override // com.ibm.icu.text.DictionaryMatcher
    public int getType() {
        return 1;
    }
}
