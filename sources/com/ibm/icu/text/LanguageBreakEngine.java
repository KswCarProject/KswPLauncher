package com.ibm.icu.text;

import com.ibm.icu.text.DictionaryBreakEngine;
import java.text.CharacterIterator;

interface LanguageBreakEngine {
    int findBreaks(CharacterIterator characterIterator, int i, int i2, DictionaryBreakEngine.DequeI dequeI);

    boolean handles(int i);
}
