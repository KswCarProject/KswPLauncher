package com.ibm.icu.text;

import com.ibm.icu.impl.Norm2AllModes;
import com.ibm.icu.impl.Normalizer2Impl;
import com.ibm.icu.text.Transliterator;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
final class NormalizationTransliterator extends Transliterator {
    static final Map<Normalizer2, SourceTargetUtility> SOURCE_CACHE = new HashMap();
    private final Normalizer2 norm2;

    static void register() {
        Transliterator.registerFactory("Any-NFC", new Transliterator.Factory() { // from class: com.ibm.icu.text.NormalizationTransliterator.1
            @Override // com.ibm.icu.text.Transliterator.Factory
            public Transliterator getInstance(String ID) {
                return new NormalizationTransliterator("NFC", Normalizer2.getNFCInstance());
            }
        });
        Transliterator.registerFactory("Any-NFD", new Transliterator.Factory() { // from class: com.ibm.icu.text.NormalizationTransliterator.2
            @Override // com.ibm.icu.text.Transliterator.Factory
            public Transliterator getInstance(String ID) {
                return new NormalizationTransliterator("NFD", Normalizer2.getNFDInstance());
            }
        });
        Transliterator.registerFactory("Any-NFKC", new Transliterator.Factory() { // from class: com.ibm.icu.text.NormalizationTransliterator.3
            @Override // com.ibm.icu.text.Transliterator.Factory
            public Transliterator getInstance(String ID) {
                return new NormalizationTransliterator("NFKC", Normalizer2.getNFKCInstance());
            }
        });
        Transliterator.registerFactory("Any-NFKD", new Transliterator.Factory() { // from class: com.ibm.icu.text.NormalizationTransliterator.4
            @Override // com.ibm.icu.text.Transliterator.Factory
            public Transliterator getInstance(String ID) {
                return new NormalizationTransliterator("NFKD", Normalizer2.getNFKDInstance());
            }
        });
        Transliterator.registerFactory("Any-FCD", new Transliterator.Factory() { // from class: com.ibm.icu.text.NormalizationTransliterator.5
            @Override // com.ibm.icu.text.Transliterator.Factory
            public Transliterator getInstance(String ID) {
                return new NormalizationTransliterator("FCD", Norm2AllModes.getFCDNormalizer2());
            }
        });
        Transliterator.registerFactory("Any-FCC", new Transliterator.Factory() { // from class: com.ibm.icu.text.NormalizationTransliterator.6
            @Override // com.ibm.icu.text.Transliterator.Factory
            public Transliterator getInstance(String ID) {
                return new NormalizationTransliterator("FCC", Norm2AllModes.getNFCInstance().fcc);
            }
        });
        Transliterator.registerSpecialInverse("NFC", "NFD", true);
        Transliterator.registerSpecialInverse("NFKC", "NFKD", true);
        Transliterator.registerSpecialInverse("FCC", "NFD", false);
        Transliterator.registerSpecialInverse("FCD", "FCD", false);
    }

    private NormalizationTransliterator(String id, Normalizer2 n2) {
        super(id, null);
        this.norm2 = n2;
    }

    @Override // com.ibm.icu.text.Transliterator
    protected void handleTransliterate(Replaceable text, Transliterator.Position offsets, boolean isIncremental) {
        Normalizer2 normalizer2;
        int char32At;
        int start = offsets.start;
        int limit = offsets.limit;
        if (start >= limit) {
            return;
        }
        StringBuilder segment = new StringBuilder();
        StringBuilder normalized = new StringBuilder();
        int c = text.char32At(start);
        while (true) {
            int prev = start;
            segment.setLength(0);
            do {
                segment.appendCodePoint(c);
                start += Character.charCount(c);
                if (start >= limit) {
                    break;
                }
                normalizer2 = this.norm2;
                char32At = text.char32At(start);
                c = char32At;
            } while (!normalizer2.hasBoundaryBefore(char32At));
            if (start == limit && isIncremental && !this.norm2.hasBoundaryAfter(c)) {
                start = prev;
                break;
            }
            this.norm2.normalize((CharSequence) segment, normalized);
            if (!Normalizer2Impl.UTF16Plus.equal(segment, normalized)) {
                text.replace(prev, start, normalized.toString());
                int delta = normalized.length() - (start - prev);
                start += delta;
                limit += delta;
                continue;
            }
            if (start >= limit) {
                break;
            }
        }
        offsets.start = start;
        offsets.contextLimit += limit - offsets.limit;
        offsets.limit = limit;
    }

    /* loaded from: classes.dex */
    static class NormalizingTransform implements Transform<String, String> {
        final Normalizer2 norm2;

        public NormalizingTransform(Normalizer2 norm2) {
            this.norm2 = norm2;
        }

        @Override // com.ibm.icu.text.Transform
        public String transform(String source) {
            return this.norm2.normalize(source);
        }
    }

    @Override // com.ibm.icu.text.Transliterator
    public void addSourceTargetSet(UnicodeSet inputFilter, UnicodeSet sourceSet, UnicodeSet targetSet) {
        SourceTargetUtility cache;
        Map<Normalizer2, SourceTargetUtility> map = SOURCE_CACHE;
        synchronized (map) {
            cache = map.get(this.norm2);
            if (cache == null) {
                cache = new SourceTargetUtility(new NormalizingTransform(this.norm2), this.norm2);
                map.put(this.norm2, cache);
            }
        }
        cache.addSourceTargetSet(this, inputFilter, sourceSet, targetSet);
    }
}
