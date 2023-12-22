package com.ibm.icu.text;

/* loaded from: classes.dex */
class FunctionReplacer implements UnicodeReplacer {
    private UnicodeReplacer replacer;
    private Transliterator translit;

    public FunctionReplacer(Transliterator theTranslit, UnicodeReplacer theReplacer) {
        this.translit = theTranslit;
        this.replacer = theReplacer;
    }

    @Override // com.ibm.icu.text.UnicodeReplacer
    public int replace(Replaceable text, int start, int limit, int[] cursor) {
        int len = this.replacer.replace(text, start, limit, cursor);
        int limit2 = start + len;
        return this.translit.transliterate(text, start, limit2) - start;
    }

    @Override // com.ibm.icu.text.UnicodeReplacer
    public String toReplacerPattern(boolean escapeUnprintable) {
        return "&" + this.translit.getID() + "( " + this.replacer.toReplacerPattern(escapeUnprintable) + " )";
    }

    @Override // com.ibm.icu.text.UnicodeReplacer
    public void addReplacementSetTo(UnicodeSet toUnionTo) {
        toUnionTo.addAll(this.translit.getTargetSet());
    }
}
