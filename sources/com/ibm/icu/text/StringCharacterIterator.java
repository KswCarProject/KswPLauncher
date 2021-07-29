package com.ibm.icu.text;

import com.ibm.icu.util.ICUCloneNotSupportedException;
import java.text.CharacterIterator;

@Deprecated
public final class StringCharacterIterator implements CharacterIterator {
    private int begin;
    private int end;
    private int pos;
    private String text;

    @Deprecated
    public StringCharacterIterator(String text2) {
        this(text2, 0);
    }

    @Deprecated
    public StringCharacterIterator(String text2, int pos2) {
        this(text2, 0, text2.length(), pos2);
    }

    @Deprecated
    public StringCharacterIterator(String text2, int begin2, int end2, int pos2) {
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

    @Deprecated
    public void setText(String text2) {
        if (text2 != null) {
            this.text = text2;
            this.begin = 0;
            this.end = text2.length();
            this.pos = 0;
            return;
        }
        throw new NullPointerException();
    }

    @Deprecated
    public char first() {
        this.pos = this.begin;
        return current();
    }

    @Deprecated
    public char last() {
        int i = this.end;
        if (i != this.begin) {
            this.pos = i - 1;
        } else {
            this.pos = i;
        }
        return current();
    }

    @Deprecated
    public char setIndex(int p) {
        if (p < this.begin || p > this.end) {
            throw new IllegalArgumentException("Invalid index");
        }
        this.pos = p;
        return current();
    }

    @Deprecated
    public char current() {
        int i = this.pos;
        if (i < this.begin || i >= this.end) {
            return 65535;
        }
        return this.text.charAt(i);
    }

    @Deprecated
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

    @Deprecated
    public char previous() {
        int i = this.pos;
        if (i <= this.begin) {
            return 65535;
        }
        int i2 = i - 1;
        this.pos = i2;
        return this.text.charAt(i2);
    }

    @Deprecated
    public int getBeginIndex() {
        return this.begin;
    }

    @Deprecated
    public int getEndIndex() {
        return this.end;
    }

    @Deprecated
    public int getIndex() {
        return this.pos;
    }

    @Deprecated
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StringCharacterIterator)) {
            return false;
        }
        StringCharacterIterator that = (StringCharacterIterator) obj;
        if (hashCode() == that.hashCode() && this.text.equals(that.text) && this.pos == that.pos && this.begin == that.begin && this.end == that.end) {
            return true;
        }
        return false;
    }

    @Deprecated
    public int hashCode() {
        return ((this.text.hashCode() ^ this.pos) ^ this.begin) ^ this.end;
    }

    @Deprecated
    public Object clone() {
        try {
            return (StringCharacterIterator) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new ICUCloneNotSupportedException(e);
        }
    }
}
