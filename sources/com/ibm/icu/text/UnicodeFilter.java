package com.ibm.icu.text;

/* loaded from: classes.dex */
public abstract class UnicodeFilter implements UnicodeMatcher {
    public abstract boolean contains(int i);

    @Override // com.ibm.icu.text.UnicodeMatcher
    public int matches(Replaceable text, int[] offset, int limit, boolean incremental) {
        if (offset[0] < limit) {
            int c = text.char32At(offset[0]);
            if (contains(c)) {
                offset[0] = offset[0] + UTF16.getCharCount(c);
                return 2;
            }
        }
        if (offset[0] <= limit || !contains(text.char32At(offset[0]))) {
            return (incremental && offset[0] == limit) ? 1 : 0;
        }
        offset[0] = offset[0] - 1;
        if (offset[0] >= 0) {
            offset[0] = offset[0] - (UTF16.getCharCount(text.char32At(offset[0])) - 1);
        }
        return 2;
    }

    @Deprecated
    protected UnicodeFilter() {
    }
}
