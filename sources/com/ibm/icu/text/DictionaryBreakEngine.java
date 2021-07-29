package com.ibm.icu.text;

import com.ibm.icu.impl.CharacterIteration;
import java.text.CharacterIterator;

abstract class DictionaryBreakEngine implements LanguageBreakEngine {
    UnicodeSet fSet = new UnicodeSet();

    /* access modifiers changed from: package-private */
    public abstract int divideUpDictionaryRange(CharacterIterator characterIterator, int i, int i2, DequeI dequeI);

    static class PossibleWord {
        private static final int POSSIBLE_WORD_LIST_MAX = 20;
        private int[] count = new int[1];
        private int current;
        private int[] lengths = new int[20];
        private int mark;
        private int offset = -1;
        private int prefix;

        public int candidates(CharacterIterator fIter, DictionaryMatcher dict, int rangeEnd) {
            int start = fIter.getIndex();
            if (start != this.offset) {
                this.offset = start;
                int[] iArr = this.lengths;
                this.prefix = dict.matches(fIter, rangeEnd - start, iArr, this.count, iArr.length);
                if (this.count[0] <= 0) {
                    fIter.setIndex(start);
                }
            }
            int[] iArr2 = this.count;
            if (iArr2[0] > 0) {
                fIter.setIndex(this.lengths[iArr2[0] - 1] + start);
            }
            int[] iArr3 = this.count;
            int i = iArr3[0] - 1;
            this.current = i;
            this.mark = i;
            return iArr3[0];
        }

        public int acceptMarked(CharacterIterator fIter) {
            fIter.setIndex(this.offset + this.lengths[this.mark]);
            return this.lengths[this.mark];
        }

        public boolean backUp(CharacterIterator fIter) {
            int i = this.current;
            if (i <= 0) {
                return false;
            }
            int i2 = this.offset;
            int[] iArr = this.lengths;
            int i3 = i - 1;
            this.current = i3;
            fIter.setIndex(i2 + iArr[i3]);
            return true;
        }

        public int longestPrefix() {
            return this.prefix;
        }

        public void markCurrent() {
            this.mark = this.current;
        }
    }

    static class DequeI implements Cloneable {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private int[] data = new int[50];
        private int firstIdx = 4;
        private int lastIdx = 4;

        static {
            Class<DictionaryBreakEngine> cls = DictionaryBreakEngine.class;
        }

        DequeI() {
        }

        public Object clone() throws CloneNotSupportedException {
            DequeI result = (DequeI) super.clone();
            result.data = (int[]) this.data.clone();
            return result;
        }

        /* access modifiers changed from: package-private */
        public int size() {
            return this.firstIdx - this.lastIdx;
        }

        /* access modifiers changed from: package-private */
        public boolean isEmpty() {
            return size() == 0;
        }

        private void grow() {
            int[] iArr = this.data;
            int[] newData = new int[(iArr.length * 2)];
            System.arraycopy(iArr, 0, newData, 0, iArr.length);
            this.data = newData;
        }

        /* access modifiers changed from: package-private */
        public void offer(int v) {
            int i = this.lastIdx;
            if (i > 0) {
                int[] iArr = this.data;
                int i2 = i - 1;
                this.lastIdx = i2;
                iArr[i2] = v;
                return;
            }
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        public void push(int v) {
            if (this.firstIdx >= this.data.length) {
                grow();
            }
            int[] iArr = this.data;
            int i = this.firstIdx;
            this.firstIdx = i + 1;
            iArr[i] = v;
        }

        /* access modifiers changed from: package-private */
        public int pop() {
            if (size() > 0) {
                int[] iArr = this.data;
                int i = this.firstIdx - 1;
                this.firstIdx = i;
                return iArr[i];
            }
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        public int peek() {
            if (size() > 0) {
                return this.data[this.firstIdx - 1];
            }
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        public int peekLast() {
            if (size() > 0) {
                return this.data[this.lastIdx];
            }
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        public int pollLast() {
            if (size() > 0) {
                int[] iArr = this.data;
                int i = this.lastIdx;
                this.lastIdx = i + 1;
                return iArr[i];
            }
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        public boolean contains(int v) {
            for (int i = this.lastIdx; i < this.firstIdx; i++) {
                if (this.data[i] == v) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public int elementAt(int i) {
            if (i < size()) {
                return this.data[this.lastIdx + i];
            }
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        public void removeAllElements() {
            this.firstIdx = 4;
            this.lastIdx = 4;
        }
    }

    public boolean handles(int c) {
        return this.fSet.contains(c);
    }

    public int findBreaks(CharacterIterator text, int startPos, int endPos, DequeI foundBreaks) {
        int current;
        int start = text.getIndex();
        int c = CharacterIteration.current32(text);
        while (true) {
            int index = text.getIndex();
            current = index;
            if (index >= endPos || !this.fSet.contains(c)) {
                int result = divideUpDictionaryRange(text, start, current, foundBreaks);
                text.setIndex(current);
            } else {
                CharacterIteration.next32(text);
                c = CharacterIteration.current32(text);
            }
        }
        int result2 = divideUpDictionaryRange(text, start, current, foundBreaks);
        text.setIndex(current);
        return result2;
    }

    /* access modifiers changed from: package-private */
    public void setCharacters(UnicodeSet set) {
        UnicodeSet unicodeSet = new UnicodeSet(set);
        this.fSet = unicodeSet;
        unicodeSet.compact();
    }
}
