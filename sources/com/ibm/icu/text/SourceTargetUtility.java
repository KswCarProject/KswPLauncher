package com.ibm.icu.text;

import com.ibm.icu.lang.CharSequences;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
class SourceTargetUtility {
    final UnicodeSet sourceCache;
    final Set<String> sourceStrings;
    final Transform<String, String> transform;
    static final UnicodeSet NON_STARTERS = new UnicodeSet("[:^ccc=0:]").m87freeze();
    static Normalizer2 NFC = Normalizer2.getNFCInstance();

    public SourceTargetUtility(Transform<String, String> transform) {
        this(transform, null);
    }

    public SourceTargetUtility(Transform<String, String> transform, Normalizer2 normalizer) {
        String d;
        this.transform = transform;
        if (normalizer != null) {
            this.sourceCache = new UnicodeSet("[:^ccc=0:]");
        } else {
            this.sourceCache = new UnicodeSet();
        }
        this.sourceStrings = new HashSet();
        for (int i = 0; i <= 1114111; i++) {
            String s = transform.transform(UTF16.valueOf(i));
            boolean added = false;
            if (!CharSequences.equals(i, s)) {
                this.sourceCache.add(i);
                added = true;
            }
            if (normalizer != null && (d = NFC.getDecomposition(i)) != null) {
                String s2 = transform.transform(d);
                if (!d.equals(s2)) {
                    this.sourceStrings.add(d);
                }
                if (!added && !normalizer.isInert(i)) {
                    this.sourceCache.add(i);
                }
            }
        }
        this.sourceCache.m87freeze();
    }

    public void addSourceTargetSet(Transliterator transliterator, UnicodeSet inputFilter, UnicodeSet sourceSet, UnicodeSet targetSet) {
        UnicodeSet myFilter = transliterator.getFilterAsUnicodeSet(inputFilter);
        UnicodeSet affectedCharacters = new UnicodeSet(this.sourceCache).retainAll(myFilter);
        sourceSet.addAll(affectedCharacters);
        Iterator<String> it = affectedCharacters.iterator();
        while (it.hasNext()) {
            targetSet.addAll(this.transform.transform(it.next()));
        }
        for (String s : this.sourceStrings) {
            if (myFilter.containsAll(s)) {
                String t = this.transform.transform(s);
                if (!s.equals(t)) {
                    targetSet.addAll(t);
                    sourceSet.addAll(s);
                }
            }
        }
    }
}
