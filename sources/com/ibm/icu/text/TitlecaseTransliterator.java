package com.ibm.icu.text;

import com.ibm.icu.impl.UCaseProps;
import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.text.Transliterator;
import com.ibm.icu.util.ULocale;

/* loaded from: classes.dex */
class TitlecaseTransliterator extends Transliterator {
    static final String _ID = "Any-Title";
    private int caseLocale;
    private final UCaseProps csp;
    private ReplaceableContextIterator iter;
    private final ULocale locale;
    private StringBuilder result;
    SourceTargetUtility sourceTargetUtility;

    static void register() {
        Transliterator.registerFactory(_ID, new Transliterator.Factory() { // from class: com.ibm.icu.text.TitlecaseTransliterator.1
            @Override // com.ibm.icu.text.Transliterator.Factory
            public Transliterator getInstance(String ID) {
                return new TitlecaseTransliterator(ULocale.US);
            }
        });
        registerSpecialInverse("Title", "Lower", false);
    }

    public TitlecaseTransliterator(ULocale loc) {
        super(_ID, null);
        this.sourceTargetUtility = null;
        this.locale = loc;
        setMaximumContextLength(2);
        this.csp = UCaseProps.INSTANCE;
        this.iter = new ReplaceableContextIterator();
        this.result = new StringBuilder();
        this.caseLocale = UCaseProps.getCaseLocale(loc);
    }

    @Override // com.ibm.icu.text.Transliterator
    protected synchronized void handleTransliterate(Replaceable text, Transliterator.Position offsets, boolean isIncremental) {
        int c;
        int delta;
        if (offsets.start >= offsets.limit) {
            return;
        }
        boolean doTitle = true;
        int start = offsets.start - 1;
        while (true) {
            if (start < offsets.contextStart) {
                break;
            }
            int c2 = text.char32At(start);
            int type = this.csp.getTypeOrIgnorable(c2);
            if (type > 0) {
                doTitle = false;
                break;
            } else if (type == 0) {
                break;
            } else {
                start -= UTF16.getCharCount(c2);
            }
        }
        this.iter.setText(text);
        this.iter.setIndex(offsets.start);
        this.iter.setLimit(offsets.limit);
        this.iter.setContextLimits(offsets.contextStart, offsets.contextLimit);
        this.result.setLength(0);
        while (true) {
            int c3 = this.iter.nextCaseMapCP();
            if (c3 >= 0) {
                int type2 = this.csp.getTypeOrIgnorable(c3);
                if (type2 >= 0) {
                    if (doTitle) {
                        c = this.csp.toFullTitle(c3, this.iter, this.result, this.caseLocale);
                    } else {
                        c = this.csp.toFullLower(c3, this.iter, this.result, this.caseLocale);
                    }
                    doTitle = type2 == 0;
                    if (this.iter.didReachLimit() && isIncremental) {
                        offsets.start = this.iter.getCaseMapCPStart();
                        return;
                    } else if (c >= 0) {
                        if (c <= 31) {
                            delta = this.iter.replace(this.result.toString());
                            this.result.setLength(0);
                        } else {
                            delta = this.iter.replace(UTF16.valueOf(c));
                        }
                        if (delta != 0) {
                            offsets.limit += delta;
                            offsets.contextLimit += delta;
                        }
                    }
                }
            } else {
                offsets.start = offsets.limit;
                return;
            }
        }
    }

    @Override // com.ibm.icu.text.Transliterator
    public void addSourceTargetSet(UnicodeSet inputFilter, UnicodeSet sourceSet, UnicodeSet targetSet) {
        synchronized (this) {
            if (this.sourceTargetUtility == null) {
                this.sourceTargetUtility = new SourceTargetUtility(new Transform<String, String>() { // from class: com.ibm.icu.text.TitlecaseTransliterator.2
                    @Override // com.ibm.icu.text.Transform
                    public String transform(String source) {
                        return UCharacter.toTitleCase(TitlecaseTransliterator.this.locale, source, (BreakIterator) null);
                    }
                });
            }
        }
        this.sourceTargetUtility.addSourceTargetSet(this, inputFilter, sourceSet, targetSet);
    }
}
