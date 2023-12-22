package com.ibm.icu.text;

import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.text.Transliterator;
import com.ibm.icu.util.ICUCloneNotSupportedException;
import com.ibm.icu.util.ULocale;
import java.text.CharacterIterator;

/* loaded from: classes.dex */
final class BreakTransliterator extends Transliterator {
    static final int LETTER_OR_MARK_MASK = 510;

    /* renamed from: bi */
    private BreakIterator f144bi;
    private int[] boundaries;
    private int boundaryCount;
    private String insertion;

    public BreakTransliterator(String ID, UnicodeFilter filter, BreakIterator bi, String insertion) {
        super(ID, filter);
        this.boundaries = new int[50];
        this.boundaryCount = 0;
        this.f144bi = bi;
        this.insertion = insertion;
    }

    public BreakTransliterator(String ID, UnicodeFilter filter) {
        this(ID, filter, null, " ");
    }

    public String getInsertion() {
        return this.insertion;
    }

    public void setInsertion(String insertion) {
        this.insertion = insertion;
    }

    public BreakIterator getBreakIterator() {
        if (this.f144bi == null) {
            this.f144bi = BreakIterator.getWordInstance(new ULocale("th_TH"));
        }
        return this.f144bi;
    }

    public void setBreakIterator(BreakIterator bi) {
        this.f144bi = bi;
    }

    @Override // com.ibm.icu.text.Transliterator
    protected synchronized void handleTransliterate(Replaceable text, Transliterator.Position pos, boolean incremental) {
        this.boundaryCount = 0;
        getBreakIterator();
        this.f144bi.setText(new ReplaceableCharacterIterator(text, pos.start, pos.limit, pos.start));
        int boundary = this.f144bi.first();
        while (boundary != -1 && boundary < pos.limit) {
            if (boundary != 0) {
                int cp = UTF16.charAt(text, boundary - 1);
                int type = UCharacter.getType(cp);
                if (((1 << type) & LETTER_OR_MARK_MASK) != 0) {
                    int cp2 = UTF16.charAt(text, boundary);
                    int type2 = UCharacter.getType(cp2);
                    if (((1 << type2) & LETTER_OR_MARK_MASK) != 0) {
                        int i = this.boundaryCount;
                        int[] iArr = this.boundaries;
                        if (i >= iArr.length) {
                            int[] temp = new int[iArr.length * 2];
                            System.arraycopy(iArr, 0, temp, 0, iArr.length);
                            this.boundaries = temp;
                        }
                        int[] temp2 = this.boundaries;
                        int i2 = this.boundaryCount;
                        this.boundaryCount = i2 + 1;
                        temp2[i2] = boundary;
                    }
                }
            }
            boundary = this.f144bi.next();
        }
        int delta = 0;
        int lastBoundary = 0;
        int i3 = this.boundaryCount;
        if (i3 != 0) {
            delta = i3 * this.insertion.length();
            lastBoundary = this.boundaries[this.boundaryCount - 1];
            while (true) {
                int i4 = this.boundaryCount;
                if (i4 <= 0) {
                    break;
                }
                int[] iArr2 = this.boundaries;
                int i5 = i4 - 1;
                this.boundaryCount = i5;
                int boundary2 = iArr2[i5];
                text.replace(boundary2, boundary2, this.insertion);
            }
        }
        pos.contextLimit += delta;
        pos.limit += delta;
        pos.start = incremental ? lastBoundary + delta : pos.limit;
    }

    static void register() {
        Transliterator trans = new BreakTransliterator("Any-BreakInternal", null);
        Transliterator.registerInstance(trans, false);
    }

    /* loaded from: classes.dex */
    static final class ReplaceableCharacterIterator implements CharacterIterator {
        private int begin;
        private int end;
        private int pos;
        private Replaceable text;

        public ReplaceableCharacterIterator(Replaceable text, int begin, int end, int pos) {
            if (text == null) {
                throw new NullPointerException();
            }
            this.text = text;
            if (begin < 0 || begin > end || end > text.length()) {
                throw new IllegalArgumentException("Invalid substring range");
            }
            if (pos < begin || pos > end) {
                throw new IllegalArgumentException("Invalid position");
            }
            this.begin = begin;
            this.end = end;
            this.pos = pos;
        }

        public void setText(Replaceable text) {
            if (text == null) {
                throw new NullPointerException();
            }
            this.text = text;
            this.begin = 0;
            this.end = text.length();
            this.pos = 0;
        }

        @Override // java.text.CharacterIterator
        public char first() {
            this.pos = this.begin;
            return current();
        }

        @Override // java.text.CharacterIterator
        public char last() {
            int i = this.end;
            if (i != this.begin) {
                this.pos = i - 1;
            } else {
                this.pos = i;
            }
            return current();
        }

        @Override // java.text.CharacterIterator
        public char setIndex(int p) {
            if (p < this.begin || p > this.end) {
                throw new IllegalArgumentException("Invalid index");
            }
            this.pos = p;
            return current();
        }

        @Override // java.text.CharacterIterator
        public char current() {
            int i = this.pos;
            if (i >= this.begin && i < this.end) {
                return this.text.charAt(i);
            }
            return '\uffff';
        }

        @Override // java.text.CharacterIterator
        public char next() {
            int i = this.pos;
            int i2 = this.end;
            if (i < i2 - 1) {
                int i3 = i + 1;
                this.pos = i3;
                return this.text.charAt(i3);
            }
            this.pos = i2;
            return '\uffff';
        }

        @Override // java.text.CharacterIterator
        public char previous() {
            int i = this.pos;
            if (i > this.begin) {
                int i2 = i - 1;
                this.pos = i2;
                return this.text.charAt(i2);
            }
            return '\uffff';
        }

        @Override // java.text.CharacterIterator
        public int getBeginIndex() {
            return this.begin;
        }

        @Override // java.text.CharacterIterator
        public int getEndIndex() {
            return this.end;
        }

        @Override // java.text.CharacterIterator
        public int getIndex() {
            return this.pos;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof ReplaceableCharacterIterator) {
                ReplaceableCharacterIterator that = (ReplaceableCharacterIterator) obj;
                return hashCode() == that.hashCode() && this.text.equals(that.text) && this.pos == that.pos && this.begin == that.begin && this.end == that.end;
            }
            return false;
        }

        public int hashCode() {
            return ((this.text.hashCode() ^ this.pos) ^ this.begin) ^ this.end;
        }

        @Override // java.text.CharacterIterator
        public Object clone() {
            try {
                ReplaceableCharacterIterator other = (ReplaceableCharacterIterator) super.clone();
                return other;
            } catch (CloneNotSupportedException e) {
                throw new ICUCloneNotSupportedException();
            }
        }
    }

    @Override // com.ibm.icu.text.Transliterator
    public void addSourceTargetSet(UnicodeSet inputFilter, UnicodeSet sourceSet, UnicodeSet targetSet) {
        UnicodeSet myFilter = getFilterAsUnicodeSet(inputFilter);
        if (myFilter.size() != 0) {
            targetSet.addAll(this.insertion);
        }
    }
}
