package com.ibm.icu.text;

import com.ibm.icu.impl.Assert;
import com.ibm.icu.util.BytesTrie;
import java.text.CharacterIterator;

class BytesDictionaryMatcher extends DictionaryMatcher {
    private final byte[] characters;
    private final int transform;

    public BytesDictionaryMatcher(byte[] chars, int transform2) {
        this.characters = chars;
        Assert.assrt((2130706432 & transform2) == 16777216);
        this.transform = transform2;
    }

    private int transform(int c) {
        if (c == 8205) {
            return 255;
        }
        if (c == 8204) {
            return SCSU.KATAKANAINDEX;
        }
        int delta = c - (this.transform & DictionaryData.TRANSFORM_OFFSET_MASK);
        if (delta < 0 || 253 < delta) {
            return -1;
        }
        return delta;
    }

    public int matches(CharacterIterator text_, int maxLength, int[] lengths, int[] count_, int limit, int[] values) {
        int c;
        UCharacterIterator text = UCharacterIterator.getInstance(text_);
        BytesTrie bt = new BytesTrie(this.characters, 0);
        int c2 = text.nextCodePoint();
        if (c2 == -1) {
            return 0;
        }
        BytesTrie.Result result = bt.first(transform(c2));
        int numChars = 1;
        int count = 0;
        while (true) {
            if (!result.hasValue()) {
                if (result == BytesTrie.Result.NO_MATCH) {
                    break;
                }
            } else {
                if (count < limit) {
                    if (values != null) {
                        values[count] = bt.getValue();
                    }
                    lengths[count] = numChars;
                    count++;
                }
                if (result == BytesTrie.Result.FINAL_VALUE) {
                    break;
                }
            }
            if (numChars >= maxLength || (c = text.nextCodePoint()) == -1) {
                break;
            }
            numChars++;
            result = bt.next(transform(c));
        }
        count_[0] = count;
        return numChars;
    }

    public int getType() {
        return 0;
    }
}
