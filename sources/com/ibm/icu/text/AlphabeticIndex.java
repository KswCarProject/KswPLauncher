package com.ibm.icu.text;

import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.text.UTF16;
import com.ibm.icu.util.LocaleData;
import com.ibm.icu.util.ULocale;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public final class AlphabeticIndex<V> implements Iterable<Bucket<V>> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String BASE = "\ufdd0";
    private static final char CGJ = '\u034f';
    private static final int GC_CN_MASK = 1;
    private static final int GC_LL_MASK = 4;
    private static final int GC_LM_MASK = 16;
    private static final int GC_LO_MASK = 32;
    private static final int GC_LT_MASK = 8;
    private static final int GC_LU_MASK = 2;
    private static final int GC_L_MASK = 62;
    private static final Comparator<String> binaryCmp = new UTF16.StringComparator(true, false, 0);
    private BucketList<V> buckets;
    private RuleBasedCollator collatorExternal;
    private final RuleBasedCollator collatorOriginal;
    private final RuleBasedCollator collatorPrimaryOnly;
    private final List<String> firstCharsInScripts;
    private String inflowLabel;
    private final UnicodeSet initialLabels;
    private List<Record<V>> inputList;
    private int maxLabelCount;
    private String overflowLabel;
    private final Comparator<Record<V>> recordComparator;
    private String underflowLabel;

    /* loaded from: classes.dex */
    public static final class ImmutableIndex<V> implements Iterable<Bucket<V>> {
        private final BucketList<V> buckets;
        private final Collator collatorPrimaryOnly;

        private ImmutableIndex(BucketList<V> bucketList, Collator collatorPrimaryOnly) {
            this.buckets = bucketList;
            this.collatorPrimaryOnly = collatorPrimaryOnly;
        }

        public int getBucketCount() {
            return this.buckets.getBucketCount();
        }

        public int getBucketIndex(CharSequence name) {
            return this.buckets.getBucketIndex(name, this.collatorPrimaryOnly);
        }

        public Bucket<V> getBucket(int index) {
            if (index < 0 || index >= this.buckets.getBucketCount()) {
                return null;
            }
            return (Bucket) ((BucketList) this.buckets).immutableVisibleList.get(index);
        }

        @Override // java.lang.Iterable
        public Iterator<Bucket<V>> iterator() {
            return this.buckets.iterator();
        }
    }

    public AlphabeticIndex(ULocale locale) {
        this(locale, null);
    }

    public AlphabeticIndex(Locale locale) {
        this(ULocale.forLocale(locale), null);
    }

    public AlphabeticIndex(RuleBasedCollator collator) {
        this(null, collator);
    }

    private AlphabeticIndex(ULocale locale, RuleBasedCollator collator) {
        this.recordComparator = new Comparator<Record<V>>() { // from class: com.ibm.icu.text.AlphabeticIndex.1
            @Override // java.util.Comparator
            public /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
                return compare((Record) ((Record) obj), (Record) ((Record) obj2));
            }

            public int compare(Record<V> o1, Record<V> o2) {
                return AlphabeticIndex.this.collatorOriginal.compare(((Record) o1).name, ((Record) o2).name);
            }
        };
        this.initialLabels = new UnicodeSet();
        this.overflowLabel = "\u2026";
        this.underflowLabel = "\u2026";
        this.inflowLabel = "\u2026";
        this.maxLabelCount = 99;
        RuleBasedCollator ruleBasedCollator = collator != null ? collator : (RuleBasedCollator) Collator.getInstance(locale);
        this.collatorOriginal = ruleBasedCollator;
        try {
            RuleBasedCollator mo73cloneAsThawed = ruleBasedCollator.mo73cloneAsThawed();
            this.collatorPrimaryOnly = mo73cloneAsThawed;
            mo73cloneAsThawed.setStrength(0);
            mo73cloneAsThawed.mo74freeze();
            List<String> firstCharactersInScripts = getFirstCharactersInScripts();
            this.firstCharsInScripts = firstCharactersInScripts;
            Collections.sort(firstCharactersInScripts, mo73cloneAsThawed);
            while (!this.firstCharsInScripts.isEmpty()) {
                if (this.collatorPrimaryOnly.compare(this.firstCharsInScripts.get(0), "") == 0) {
                    this.firstCharsInScripts.remove(0);
                } else if (!addChineseIndexCharacters() && locale != null) {
                    addIndexExemplars(locale);
                    return;
                } else {
                    return;
                }
            }
            throw new IllegalArgumentException("AlphabeticIndex requires some non-ignorable script boundary strings");
        } catch (Exception e) {
            throw new IllegalStateException("Collator cannot be cloned", e);
        }
    }

    public AlphabeticIndex<V> addLabels(UnicodeSet additions) {
        this.initialLabels.addAll(additions);
        this.buckets = null;
        return this;
    }

    public AlphabeticIndex<V> addLabels(ULocale... additions) {
        for (ULocale addition : additions) {
            addIndexExemplars(addition);
        }
        this.buckets = null;
        return this;
    }

    public AlphabeticIndex<V> addLabels(Locale... additions) {
        for (Locale addition : additions) {
            addIndexExemplars(ULocale.forLocale(addition));
        }
        this.buckets = null;
        return this;
    }

    public AlphabeticIndex<V> setOverflowLabel(String overflowLabel) {
        this.overflowLabel = overflowLabel;
        this.buckets = null;
        return this;
    }

    public String getUnderflowLabel() {
        return this.underflowLabel;
    }

    public AlphabeticIndex<V> setUnderflowLabel(String underflowLabel) {
        this.underflowLabel = underflowLabel;
        this.buckets = null;
        return this;
    }

    public String getOverflowLabel() {
        return this.overflowLabel;
    }

    public AlphabeticIndex<V> setInflowLabel(String inflowLabel) {
        this.inflowLabel = inflowLabel;
        this.buckets = null;
        return this;
    }

    public String getInflowLabel() {
        return this.inflowLabel;
    }

    public int getMaxLabelCount() {
        return this.maxLabelCount;
    }

    public AlphabeticIndex<V> setMaxLabelCount(int maxLabelCount) {
        this.maxLabelCount = maxLabelCount;
        this.buckets = null;
        return this;
    }

    private List<String> initLabels() {
        boolean checkDistinct;
        Normalizer2 nfkdNormalizer = Normalizer2.getNFKDInstance();
        List<String> indexCharacters = new ArrayList<>();
        String firstScriptBoundary = this.firstCharsInScripts.get(0);
        List<String> list = this.firstCharsInScripts;
        String overflowBoundary = list.get(list.size() - 1);
        Iterator<String> it = this.initialLabels.iterator();
        while (it.hasNext()) {
            String item = it.next();
            if (!UTF16.hasMoreCodePointsThan(item, 1)) {
                checkDistinct = false;
            } else if (item.charAt(item.length() - 1) == '*' && item.charAt(item.length() - 2) != '*') {
                item = item.substring(0, item.length() - 1);
                checkDistinct = false;
            } else {
                checkDistinct = true;
            }
            if (this.collatorPrimaryOnly.compare(item, firstScriptBoundary) >= 0 && this.collatorPrimaryOnly.compare(item, overflowBoundary) < 0 && (!checkDistinct || this.collatorPrimaryOnly.compare(item, separated(item)) != 0)) {
                int insertionPoint = Collections.binarySearch(indexCharacters, item, this.collatorPrimaryOnly);
                if (insertionPoint < 0) {
                    indexCharacters.add(~insertionPoint, item);
                } else {
                    String itemAlreadyIn = indexCharacters.get(insertionPoint);
                    if (isOneLabelBetterThanOther(nfkdNormalizer, item, itemAlreadyIn)) {
                        indexCharacters.set(insertionPoint, item);
                    }
                }
            }
        }
        int size = indexCharacters.size() - 1;
        if (size > this.maxLabelCount) {
            int count = 0;
            int old = -1;
            Iterator<String> it2 = indexCharacters.iterator();
            while (it2.hasNext()) {
                count++;
                it2.next();
                int bump = (this.maxLabelCount * count) / size;
                if (bump == old) {
                    it2.remove();
                } else {
                    old = bump;
                }
            }
        }
        return indexCharacters;
    }

    private static String fixLabel(String current) {
        if (!current.startsWith(BASE)) {
            return current;
        }
        int rest = current.charAt(BASE.length());
        if (10240 < rest && rest <= 10495) {
            return (rest - 10240) + "\u5283";
        }
        return current.substring(BASE.length());
    }

    private void addIndexExemplars(ULocale locale) {
        UnicodeSet exemplars = LocaleData.getExemplarSet(locale, 0, 2);
        if (exemplars != null && !exemplars.isEmpty()) {
            this.initialLabels.addAll(exemplars);
            return;
        }
        UnicodeSet exemplars2 = LocaleData.getExemplarSet(locale, 0, 0).m86cloneAsThawed();
        if (exemplars2.containsSome(97, 122) || exemplars2.isEmpty()) {
            exemplars2.addAll(97, 122);
        }
        if (exemplars2.containsSome(44032, 55203)) {
            exemplars2.remove(44032, 55203).add(44032).add(45208).add(45796).add(46972).add(47560).add(48148).add(49324).add(50500).add(51088).add(52264).add(52852).add(53440).add(54028).add(54616);
        }
        if (exemplars2.containsSome(4608, 4991)) {
            UnicodeSet ethiopic = new UnicodeSet("[\u1200\u1208\u1210\u1218\u1220\u1228\u1230\u1238\u1240\u1248\u1250\u1258\u1260\u1268\u1270\u1278\u1280\u1288\u1290\u1298\u12a0\u12a8\u12b0\u12b8\u12c0\u12c8\u12d0\u12d8\u12e0\u12e8\u12f0\u12f8\u1300\u1308\u1310\u1318\u1320\u1328\u1330\u1338\u1340\u1348\u1350\u1358]");
            ethiopic.retainAll(exemplars2);
            exemplars2.remove(4608, 4991).addAll(ethiopic);
        }
        Iterator<String> it = exemplars2.iterator();
        while (it.hasNext()) {
            String item = it.next();
            this.initialLabels.add(UCharacter.toUpperCase(locale, item));
        }
    }

    private boolean addChineseIndexCharacters() {
        UnicodeSet contractions = new UnicodeSet();
        try {
            this.collatorPrimaryOnly.internalAddContractions(BASE.charAt(0), contractions);
            if (contractions.isEmpty()) {
                return false;
            }
            this.initialLabels.addAll(contractions);
            Iterator<String> it = contractions.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String s = it.next();
                if (!s.startsWith(BASE)) {
                    throw new AssertionError();
                }
                char c = s.charAt(s.length() - 1);
                if ('A' <= c && c <= 'Z') {
                    this.initialLabels.add(65, 90);
                    break;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String separated(String item) {
        StringBuilder result = new StringBuilder();
        char last = item.charAt(0);
        result.append(last);
        for (int i = 1; i < item.length(); i++) {
            char ch = item.charAt(i);
            if (!UCharacter.isHighSurrogate(last) || !UCharacter.isLowSurrogate(ch)) {
                result.append(CGJ);
            }
            result.append(ch);
            last = ch;
        }
        return result.toString();
    }

    public ImmutableIndex<V> buildImmutableIndex() {
        BucketList<V> immutableBucketList;
        List<Record<V>> list = this.inputList;
        if (list != null && !list.isEmpty()) {
            immutableBucketList = createBucketList();
        } else {
            BucketList<V> immutableBucketList2 = this.buckets;
            if (immutableBucketList2 == null) {
                this.buckets = createBucketList();
            }
            immutableBucketList = this.buckets;
        }
        return new ImmutableIndex<>(immutableBucketList, this.collatorPrimaryOnly);
    }

    public List<String> getBucketLabels() {
        initBuckets();
        ArrayList<String> result = new ArrayList<>();
        Iterator<Bucket<V>> it = this.buckets.iterator();
        while (it.hasNext()) {
            Bucket<V> bucket = it.next();
            result.add(bucket.getLabel());
        }
        return result;
    }

    public RuleBasedCollator getCollator() {
        if (this.collatorExternal == null) {
            try {
                this.collatorExternal = (RuleBasedCollator) this.collatorOriginal.clone();
            } catch (Exception e) {
                throw new IllegalStateException("Collator cannot be cloned", e);
            }
        }
        return this.collatorExternal;
    }

    public AlphabeticIndex<V> addRecord(CharSequence name, V data) {
        this.buckets = null;
        if (this.inputList == null) {
            this.inputList = new ArrayList();
        }
        this.inputList.add(new Record<>(name, data));
        return this;
    }

    public int getBucketIndex(CharSequence name) {
        initBuckets();
        return this.buckets.getBucketIndex(name, this.collatorPrimaryOnly);
    }

    public AlphabeticIndex<V> clearRecords() {
        List<Record<V>> list = this.inputList;
        if (list != null && !list.isEmpty()) {
            this.inputList.clear();
            this.buckets = null;
        }
        return this;
    }

    public int getBucketCount() {
        initBuckets();
        return this.buckets.getBucketCount();
    }

    public int getRecordCount() {
        List<Record<V>> list = this.inputList;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // java.lang.Iterable
    public Iterator<Bucket<V>> iterator() {
        initBuckets();
        return this.buckets.iterator();
    }

    private void initBuckets() {
        Bucket<V> nextBucket;
        String upperBoundary;
        if (this.buckets != null) {
            return;
        }
        this.buckets = createBucketList();
        List<Record<V>> list = this.inputList;
        if (list == null || list.isEmpty()) {
            return;
        }
        Collections.sort(this.inputList, this.recordComparator);
        Iterator<Bucket<V>> bucketIterator = this.buckets.fullIterator();
        Bucket<V> currentBucket = bucketIterator.next();
        if (bucketIterator.hasNext()) {
            nextBucket = bucketIterator.next();
            upperBoundary = ((Bucket) nextBucket).lowerBoundary;
        } else {
            nextBucket = null;
            upperBoundary = null;
        }
        for (Record<V> r : this.inputList) {
            while (upperBoundary != null && this.collatorPrimaryOnly.compare(((Record) r).name, upperBoundary) >= 0) {
                currentBucket = nextBucket;
                if (bucketIterator.hasNext()) {
                    Bucket<V> nextBucket2 = bucketIterator.next();
                    nextBucket = nextBucket2;
                    upperBoundary = ((Bucket) nextBucket).lowerBoundary;
                } else {
                    upperBoundary = null;
                }
            }
            Bucket<V> bucket = currentBucket;
            if (((Bucket) bucket).displayBucket != null) {
                bucket = ((Bucket) bucket).displayBucket;
            }
            if (((Bucket) bucket).records == null) {
                ((Bucket) bucket).records = new ArrayList();
            }
            ((Bucket) bucket).records.add(r);
        }
    }

    private static boolean isOneLabelBetterThanOther(Normalizer2 nfkdNormalizer, String one, String other) {
        String n1 = nfkdNormalizer.normalize(one);
        String n2 = nfkdNormalizer.normalize(other);
        int result = n1.codePointCount(0, n1.length()) - n2.codePointCount(0, n2.length());
        if (result != 0) {
            return result < 0;
        }
        Comparator<String> comparator = binaryCmp;
        int result2 = comparator.compare(n1, n2);
        return result2 != 0 ? result2 < 0 : comparator.compare(one, other) < 0;
    }

    /* loaded from: classes.dex */
    public static class Record<V> {
        private final V data;
        private final CharSequence name;

        private Record(CharSequence name, V data) {
            this.name = name;
            this.data = data;
        }

        public CharSequence getName() {
            return this.name;
        }

        public V getData() {
            return this.data;
        }

        public String toString() {
            return ((Object) this.name) + "=" + this.data;
        }
    }

    /* loaded from: classes.dex */
    public static class Bucket<V> implements Iterable<Record<V>> {
        private Bucket<V> displayBucket;
        private int displayIndex;
        private final String label;
        private final LabelType labelType;
        private final String lowerBoundary;
        private List<Record<V>> records;

        /* loaded from: classes.dex */
        public enum LabelType {
            NORMAL,
            UNDERFLOW,
            INFLOW,
            OVERFLOW
        }

        private Bucket(String label, String lowerBoundary, LabelType labelType) {
            this.label = label;
            this.lowerBoundary = lowerBoundary;
            this.labelType = labelType;
        }

        public String getLabel() {
            return this.label;
        }

        public LabelType getLabelType() {
            return this.labelType;
        }

        public int size() {
            List<Record<V>> list = this.records;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        @Override // java.lang.Iterable
        public Iterator<Record<V>> iterator() {
            List<Record<V>> list = this.records;
            if (list == null) {
                return Collections.emptyList().iterator();
            }
            return list.iterator();
        }

        public String toString() {
            return "{labelType=" + this.labelType + ", lowerBoundary=" + this.lowerBoundary + ", label=" + this.label + "}";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x016c A[EDGE_INSN: B:106:0x016c->B:58:0x016c ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0128  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private BucketList<V> createBucketList() {
        long variableTop;
        boolean hasInvisibleBuckets;
        List<String> indexCharacters;
        boolean hasInvisibleBuckets2;
        int scriptIndex;
        char c;
        int i;
        Bucket<V> singleBucket;
        int scriptIndex2;
        AlphabeticIndex<V> alphabeticIndex = this;
        List<String> indexCharacters2 = initLabels();
        if (alphabeticIndex.collatorPrimaryOnly.isAlternateHandlingShifted()) {
            variableTop = alphabeticIndex.collatorPrimaryOnly.getVariableTop() & 4294967295L;
        } else {
            variableTop = 0;
        }
        boolean hasInvisibleBuckets3 = false;
        Bucket<V>[] asciiBuckets = new Bucket[26];
        Bucket<V>[] pinyinBuckets = new Bucket[26];
        boolean hasPinyin = false;
        ArrayList<Bucket<V>> bucketList = new ArrayList<>();
        bucketList.add(new Bucket<>(getUnderflowLabel(), "", Bucket.LabelType.UNDERFLOW));
        int scriptIndex3 = -1;
        String scriptUpperBoundary = "";
        Iterator<String> it = indexCharacters2.iterator();
        while (true) {
            int i2 = 1;
            if (!it.hasNext()) {
                break;
            }
            String current = it.next();
            if (alphabeticIndex.collatorPrimaryOnly.compare(current, scriptUpperBoundary) < 0) {
                indexCharacters = indexCharacters2;
                hasInvisibleBuckets2 = hasInvisibleBuckets3;
            } else {
                String inflowBoundary = scriptUpperBoundary;
                boolean skippedScript = false;
                while (true) {
                    indexCharacters = indexCharacters2;
                    scriptIndex3 += i2;
                    String scriptUpperBoundary2 = alphabeticIndex.firstCharsInScripts.get(scriptIndex3);
                    scriptUpperBoundary = scriptUpperBoundary2;
                    if (alphabeticIndex.collatorPrimaryOnly.compare(current, scriptUpperBoundary) < 0) {
                        break;
                    }
                    skippedScript = true;
                    indexCharacters2 = indexCharacters;
                    i2 = 1;
                }
                if (!skippedScript || bucketList.size() <= i2) {
                    hasInvisibleBuckets2 = hasInvisibleBuckets3;
                    scriptIndex2 = scriptIndex3;
                } else {
                    hasInvisibleBuckets2 = hasInvisibleBuckets3;
                    scriptIndex2 = scriptIndex3;
                    bucketList.add(new Bucket<>(getInflowLabel(), inflowBoundary, Bucket.LabelType.INFLOW));
                }
                scriptIndex3 = scriptIndex2;
            }
            Bucket<V> bucket = new Bucket<>(fixLabel(current), current, Bucket.LabelType.NORMAL);
            bucketList.add(bucket);
            if (current.length() == 1) {
                char c2 = current.charAt(0);
                scriptIndex = scriptIndex3;
                if ('A' <= c2 && c2 <= 'Z') {
                    asciiBuckets[c2 - 'A'] = bucket;
                    if (!current.startsWith(BASE) && hasMultiplePrimaryWeights(alphabeticIndex.collatorPrimaryOnly, variableTop, current) && !current.endsWith("\uffff")) {
                        i = bucketList.size() - 2;
                        while (true) {
                            singleBucket = bucketList.get(i);
                            Bucket<V> bucket2 = bucket;
                            if (((Bucket) singleBucket).labelType == Bucket.LabelType.NORMAL) {
                                break;
                            } else if (((Bucket) singleBucket).displayBucket == null && !hasMultiplePrimaryWeights(alphabeticIndex.collatorPrimaryOnly, variableTop, ((Bucket) singleBucket).lowerBoundary)) {
                                Bucket<V> bucket3 = new Bucket<>("", current + "\uffff", Bucket.LabelType.NORMAL);
                                ((Bucket) bucket3).displayBucket = singleBucket;
                                bucketList.add(bucket3);
                                hasInvisibleBuckets3 = true;
                                break;
                            } else {
                                i--;
                                alphabeticIndex = this;
                                bucket = bucket2;
                            }
                        }
                        alphabeticIndex = this;
                        indexCharacters2 = indexCharacters;
                        scriptIndex3 = scriptIndex;
                    }
                    hasInvisibleBuckets3 = hasInvisibleBuckets2;
                    alphabeticIndex = this;
                    indexCharacters2 = indexCharacters;
                    scriptIndex3 = scriptIndex;
                }
            } else {
                scriptIndex = scriptIndex3;
            }
            if (current.length() == BASE.length() + 1 && current.startsWith(BASE) && 'A' <= (c = current.charAt(BASE.length())) && c <= 'Z') {
                pinyinBuckets[c - 'A'] = bucket;
                hasPinyin = true;
            }
            if (!current.startsWith(BASE)) {
                i = bucketList.size() - 2;
                while (true) {
                    singleBucket = bucketList.get(i);
                    Bucket<V> bucket22 = bucket;
                    if (((Bucket) singleBucket).labelType == Bucket.LabelType.NORMAL) {
                    }
                    i--;
                    alphabeticIndex = this;
                    bucket = bucket22;
                }
                alphabeticIndex = this;
                indexCharacters2 = indexCharacters;
                scriptIndex3 = scriptIndex;
            }
            hasInvisibleBuckets3 = hasInvisibleBuckets2;
            alphabeticIndex = this;
            indexCharacters2 = indexCharacters;
            scriptIndex3 = scriptIndex;
        }
        boolean hasInvisibleBuckets4 = hasInvisibleBuckets3;
        if (bucketList.size() == 1) {
            return new BucketList<>(bucketList, bucketList);
        }
        bucketList.add(new Bucket<>(getOverflowLabel(), scriptUpperBoundary, Bucket.LabelType.OVERFLOW));
        if (!hasPinyin) {
            hasInvisibleBuckets = hasInvisibleBuckets4;
        } else {
            Bucket<V> asciiBucket = null;
            hasInvisibleBuckets = hasInvisibleBuckets4;
            for (int i3 = 0; i3 < 26; i3++) {
                if (asciiBuckets[i3] != null) {
                    asciiBucket = asciiBuckets[i3];
                }
                if (pinyinBuckets[i3] != null && asciiBucket != null) {
                    ((Bucket) pinyinBuckets[i3]).displayBucket = asciiBucket;
                    hasInvisibleBuckets = true;
                }
            }
        }
        if (!hasInvisibleBuckets) {
            return new BucketList<>(bucketList, bucketList);
        }
        int i4 = bucketList.size() - 1;
        Bucket<V> nextBucket = bucketList.get(i4);
        while (true) {
            i4--;
            if (i4 <= 0) {
                break;
            }
            Bucket<V> bucket4 = bucketList.get(i4);
            if (((Bucket) bucket4).displayBucket == null) {
                if (((Bucket) bucket4).labelType == Bucket.LabelType.INFLOW && ((Bucket) nextBucket).labelType != Bucket.LabelType.NORMAL) {
                    ((Bucket) bucket4).displayBucket = nextBucket;
                } else {
                    nextBucket = bucket4;
                }
            }
        }
        ArrayList<Bucket<V>> publicBucketList = new ArrayList<>();
        Iterator<Bucket<V>> it2 = bucketList.iterator();
        while (it2.hasNext()) {
            Bucket<V> bucket5 = it2.next();
            if (((Bucket) bucket5).displayBucket == null) {
                publicBucketList.add(bucket5);
            }
        }
        return new BucketList<>(bucketList, publicBucketList);
    }

    /* loaded from: classes.dex */
    private static class BucketList<V> implements Iterable<Bucket<V>> {
        private final ArrayList<Bucket<V>> bucketList;
        private final List<Bucket<V>> immutableVisibleList;

        private BucketList(ArrayList<Bucket<V>> bucketList, ArrayList<Bucket<V>> publicBucketList) {
            this.bucketList = bucketList;
            int displayIndex = 0;
            Iterator<Bucket<V>> it = publicBucketList.iterator();
            while (it.hasNext()) {
                Bucket<V> bucket = it.next();
                ((Bucket) bucket).displayIndex = displayIndex;
                displayIndex++;
            }
            this.immutableVisibleList = Collections.unmodifiableList(publicBucketList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getBucketCount() {
            return this.immutableVisibleList.size();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getBucketIndex(CharSequence name, Collator collatorPrimaryOnly) {
            int start = 0;
            int limit = this.bucketList.size();
            while (start + 1 < limit) {
                int i = (start + limit) / 2;
                int nameVsBucket = collatorPrimaryOnly.compare(name, ((Bucket) this.bucketList.get(i)).lowerBoundary);
                if (nameVsBucket < 0) {
                    limit = i;
                } else {
                    start = i;
                }
            }
            Bucket<V> bucket = this.bucketList.get(start);
            if (((Bucket) bucket).displayBucket != null) {
                bucket = ((Bucket) bucket).displayBucket;
            }
            return ((Bucket) bucket).displayIndex;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Iterator<Bucket<V>> fullIterator() {
            return this.bucketList.iterator();
        }

        @Override // java.lang.Iterable
        public Iterator<Bucket<V>> iterator() {
            return this.immutableVisibleList.iterator();
        }
    }

    private static boolean hasMultiplePrimaryWeights(RuleBasedCollator coll, long variableTop, String s) {
        long[] ces = coll.internalGetCEs(s);
        boolean seenPrimary = false;
        for (long ce : ces) {
            long p = ce >>> 32;
            if (p > variableTop) {
                if (seenPrimary) {
                    return true;
                }
                seenPrimary = true;
            }
        }
        return false;
    }

    @Deprecated
    public List<String> getFirstCharactersInScripts() {
        List<String> dest = new ArrayList<>(200);
        UnicodeSet set = new UnicodeSet();
        this.collatorPrimaryOnly.internalAddContractions(64977, set);
        if (set.isEmpty()) {
            throw new UnsupportedOperationException("AlphabeticIndex requires script-first-primary contractions");
        }
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String boundary = it.next();
            int gcMask = 1 << UCharacter.getType(boundary.codePointAt(1));
            if ((gcMask & 63) != 0) {
                dest.add(boundary);
            }
        }
        return dest;
    }
}
