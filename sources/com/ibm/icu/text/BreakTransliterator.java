package com.ibm.icu.text;

import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.text.Transliterator;
import com.ibm.icu.util.ICUCloneNotSupportedException;
import com.ibm.icu.util.ULocale;
import java.text.CharacterIterator;

final class BreakTransliterator extends Transliterator {
    static final int LETTER_OR_MARK_MASK = 510;
    private BreakIterator bi;
    private int[] boundaries;
    private int boundaryCount;
    private String insertion;

    public BreakTransliterator(String ID, UnicodeFilter filter, BreakIterator bi2, String insertion2) {
        super(ID, filter);
        this.boundaries = new int[50];
        this.boundaryCount = 0;
        this.bi = bi2;
        this.insertion = insertion2;
    }

    public BreakTransliterator(String ID, UnicodeFilter filter) {
        this(ID, filter, (BreakIterator) null, " ");
    }

    public String getInsertion() {
        return this.insertion;
    }

    public void setInsertion(String insertion2) {
        this.insertion = insertion2;
    }

    public BreakIterator getBreakIterator() {
        if (this.bi == null) {
            this.bi = BreakIterator.getWordInstance(new ULocale("th_TH"));
        }
        return this.bi;
    }

    public void setBreakIterator(BreakIterator bi2) {
        this.bi = bi2;
    }

    /* access modifiers changed from: protected */
    public synchronized void handleTransliterate(Replaceable text, Transliterator.Position pos, boolean incremental) {
        this.boundaryCount = 0;
        getBreakIterator();
        this.bi.setText((CharacterIterator) new ReplaceableCharacterIterator(text, pos.start, pos.limit, pos.start));
        int boundary = this.bi.first();
        while (boundary != -1 && boundary < pos.limit) {
            if (boundary != 0) {
                if (((1 << UCharacter.getType(UTF16.charAt(text, boundary - 1))) & LETTER_OR_MARK_MASK) != 0) {
                    if (((1 << UCharacter.getType(UTF16.charAt(text, boundary))) & LETTER_OR_MARK_MASK) != 0) {
                        int i = this.boundaryCount;
                        int[] iArr = this.boundaries;
                        if (i >= iArr.length) {
                            int[] temp = new int[(iArr.length * 2)];
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
            boundary = this.bi.next();
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
        Transliterator.registerInstance(new BreakTransliterator("Any-BreakInternal", (UnicodeFilter) null), false);
    }

    static final class ReplaceableCharacterIterator implements CharacterIterator {
        private int begin;
        private int end;
        private int pos;
        private Replaceable text;

        public ReplaceableCharacterIterator(Replaceable text2, int begin2, int end2, int pos2) {
            if (text2 != null) {
                this.text = text2;
                if (begin2 < 0 || begin2 > end2 || end2 > text2.length()) {
                    throw new IllegalArgumentException("Invalid substring range");
                } else if (pos2 < begin2 || pos2 > end2) {
                    throw new IllegalArgumentException("Invalid position");
                } else {
                    this.begin = begin2;
                    this.end = end2;
                    this.pos = pos2;
                }
            } else {
                throw new NullPointerException();
            }
        }

        public void setText(Replaceable text2) {
            if (text2 != null) {
                this.text = text2;
                this.begin = 0;
                this.end = text2.length();
                this.pos = 0;
                return;
            }
            throw new NullPointerException();
        }

        public char first() {
            this.pos = this.begin;
            return current();
        }

        public char last() {
            int i = this.end;
            if (i != this.begin) {
                this.pos = i - 1;
            } else {
                this.pos = i;
            }
            return current();
        }

        public char setIndex(int p) {
            if (p < this.begin || p > this.end) {
                throw new IllegalArgumentException("Invalid index");
            }
            this.pos = p;
            return current();
        }

        public char current() {
            int i = this.pos;
            if (i < this.begin || i >= this.end) {
                return 65535;
            }
            return this.text.charAt(i);
        }

        public char next() {
            int i = this.pos;
            int i2 = this.end;
            if (i < i2 - 1) {
                int i3 = i + 1;
                this.pos = i3;
                return this.text.charAt(i3);
            }
            this.pos = i2;
            return 65535;
        }

        public char previous() {
            int i = this.pos;
            if (i <= this.begin) {
                return 65535;
            }
            int i2 = i - 1;
            this.pos = i2;
            return this.text.charAt(i2);
        }

        public int getBeginIndex() {
            return this.begin;
        }

        public int getEndIndex() {
            return this.end;
        }

        public int getIndex() {
            return this.pos;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ReplaceableCharacterIterator)) {
                return false;
            }
            ReplaceableCharacterIterator that = (ReplaceableCharacterIterator) obj;
            if (hashCode() == that.hashCode() && this.text.equals(that.text) && this.pos == that.pos && this.begin == that.begin && this.end == that.end) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return ((this.text.hashCode() ^ this.pos) ^ this.begin) ^ this.end;
        }

        public Object clone() {
            try {
                return (ReplaceableCharacterIterator) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new ICUCloneNotSupportedException();
            }
        }
    }

    public void addSourceTargetSet(UnicodeSet inputFilter, UnicodeSet sourceSet, UnicodeSet targetSet) {
        if (getFilterAsUnicodeSet(inputFilter).size() != 0) {
            targetSet.addAll((CharSequence) this.insertion);
        }
    }
}
