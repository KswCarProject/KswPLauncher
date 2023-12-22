package com.ibm.icu.text;

import com.ibm.icu.impl.PatternProps;
import com.ibm.icu.impl.UCharacterName;
import com.ibm.icu.impl.Utility;
import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.text.Transliterator;

/* loaded from: classes.dex */
class NameUnicodeTransliterator extends Transliterator {
    static final char CLOSE_DELIM = '}';
    static final char OPEN_DELIM = '\\';
    static final String OPEN_PAT = "\\N~{~";
    static final char SPACE = ' ';
    static final String _ID = "Name-Any";

    static void register() {
        Transliterator.registerFactory(_ID, new Transliterator.Factory() { // from class: com.ibm.icu.text.NameUnicodeTransliterator.1
            @Override // com.ibm.icu.text.Transliterator.Factory
            public Transliterator getInstance(String ID) {
                return new NameUnicodeTransliterator(null);
            }
        });
    }

    public NameUnicodeTransliterator(UnicodeFilter filter) {
        super(_ID, filter);
    }

    @Override // com.ibm.icu.text.Transliterator
    protected void handleTransliterate(Replaceable text, Transliterator.Position offsets, boolean isIncremental) {
        int maxLen = UCharacterName.INSTANCE.getMaxCharNameLength() + 1;
        StringBuffer name = new StringBuffer(maxLen);
        UnicodeSet legal = new UnicodeSet();
        UCharacterName.INSTANCE.getCharNameCharacters(legal);
        int cursor = offsets.start;
        int limit = offsets.limit;
        int mode = 0;
        int openPos = -1;
        while (cursor < limit) {
            int c = text.char32At(cursor);
            switch (mode) {
                case 0:
                    if (c != 92) {
                        break;
                    } else {
                        openPos = cursor;
                        int i = Utility.parsePattern(OPEN_PAT, text, cursor, limit);
                        if (i >= 0 && i < limit) {
                            mode = 1;
                            name.setLength(0);
                            cursor = i;
                            continue;
                        }
                    }
                    break;
                case 1:
                    if (PatternProps.isWhiteSpace(c)) {
                        if (name.length() > 0 && name.charAt(name.length() - 1) != ' ') {
                            name.append(SPACE);
                            if (name.length() > maxLen) {
                                mode = 0;
                                break;
                            }
                        }
                    } else if (c == 125) {
                        int len = name.length();
                        if (len > 0 && name.charAt(len - 1) == ' ') {
                            name.setLength(len - 1);
                        }
                        int c2 = UCharacter.getCharFromExtendedName(name.toString());
                        if (c2 != -1) {
                            int cursor2 = cursor + 1;
                            String str = UTF16.valueOf(c2);
                            text.replace(openPos, cursor2, str);
                            int delta = (cursor2 - openPos) - str.length();
                            cursor = cursor2 - delta;
                            limit -= delta;
                        }
                        mode = 0;
                        openPos = -1;
                        continue;
                    } else if (legal.contains(c)) {
                        UTF16.append(name, c);
                        if (name.length() >= maxLen) {
                            mode = 0;
                            break;
                        }
                    } else {
                        cursor--;
                        mode = 0;
                        break;
                    }
                    break;
            }
            cursor += UTF16.getCharCount(c);
        }
        offsets.contextLimit += limit - offsets.limit;
        offsets.limit = limit;
        offsets.start = (!isIncremental || openPos < 0) ? cursor : openPos;
    }

    @Override // com.ibm.icu.text.Transliterator
    public void addSourceTargetSet(UnicodeSet inputFilter, UnicodeSet sourceSet, UnicodeSet targetSet) {
        UnicodeSet myFilter = getFilterAsUnicodeSet(inputFilter);
        if (!myFilter.containsAll("\\N{") || !myFilter.contains(125)) {
            return;
        }
        UnicodeSet items = new UnicodeSet().addAll(48, 57).addAll(65, 70).addAll(97, 122).add(60).add(62).add(40).add(41).add(45).add(32).addAll("\\N{").add(125);
        items.retainAll(myFilter);
        if (items.size() > 0) {
            sourceSet.addAll(items);
            targetSet.addAll(0, 1114111);
        }
    }
}
