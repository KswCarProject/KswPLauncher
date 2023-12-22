package com.ibm.icu.text;

/* loaded from: classes.dex */
public class ReplaceableString implements Replaceable {
    private StringBuffer buf;

    public ReplaceableString(String str) {
        this.buf = new StringBuffer(str);
    }

    public ReplaceableString(StringBuffer buf) {
        this.buf = buf;
    }

    public ReplaceableString() {
        this.buf = new StringBuffer();
    }

    public String toString() {
        return this.buf.toString();
    }

    public String substring(int start, int limit) {
        return this.buf.substring(start, limit);
    }

    @Override // com.ibm.icu.text.Replaceable
    public int length() {
        return this.buf.length();
    }

    @Override // com.ibm.icu.text.Replaceable
    public char charAt(int offset) {
        return this.buf.charAt(offset);
    }

    @Override // com.ibm.icu.text.Replaceable
    public int char32At(int offset) {
        return UTF16.charAt(this.buf, offset);
    }

    @Override // com.ibm.icu.text.Replaceable
    public void getChars(int srcStart, int srcLimit, char[] dst, int dstStart) {
        if (srcStart != srcLimit) {
            this.buf.getChars(srcStart, srcLimit, dst, dstStart);
        }
    }

    @Override // com.ibm.icu.text.Replaceable
    public void replace(int start, int limit, String text) {
        this.buf.replace(start, limit, text);
    }

    @Override // com.ibm.icu.text.Replaceable
    public void replace(int start, int limit, char[] chars, int charsStart, int charsLen) {
        this.buf.delete(start, limit);
        this.buf.insert(start, chars, charsStart, charsLen);
    }

    @Override // com.ibm.icu.text.Replaceable
    public void copy(int start, int limit, int dest) {
        if (start == limit && start >= 0 && start <= this.buf.length()) {
            return;
        }
        char[] text = new char[limit - start];
        getChars(start, limit, text, 0);
        replace(dest, dest, text, 0, limit - start);
    }

    @Override // com.ibm.icu.text.Replaceable
    public boolean hasMetaData() {
        return false;
    }
}
