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

public final class AlphabeticIndex<V> implements Iterable<Bucket<V>> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String BASE = "﷐";
    private static final char CGJ = '͏';
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
    /* access modifiers changed from: private */
    public final RuleBasedCollator collatorOriginal;
    private final RuleBasedCollator collatorPrimaryOnly;
    private final List<String> firstCharsInScripts;
    private String inflowLabel;
    private final UnicodeSet initialLabels;
    private List<Record<V>> inputList;
    private int maxLabelCount;
    private String overflowLabel;
    private final Comparator<Record<V>> recordComparator;
    private String underflowLabel;

    public static final class ImmutableIndex<V> implements Iterable<Bucket<V>> {
        private final BucketList<V> buckets;
        private final Collator collatorPrimaryOnly;

        private ImmutableIndex(BucketList<V> bucketList, Collator collatorPrimaryOnly2) {
            this.buckets = bucketList;
            this.collatorPrimaryOnly = collatorPrimaryOnly2;
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
            return (Bucket) this.buckets.immutableVisibleList.get(index);
        }

        public Iterator<Bucket<V>> iterator() {
            return this.buckets.iterator();
        }
    }

    public AlphabeticIndex(ULocale locale) {
        this(locale, (RuleBasedCollator) null);
    }

    public AlphabeticIndex(Locale locale) {
        this(ULocale.forLocale(locale), (RuleBasedCollator) null);
    }

    public AlphabeticIndex(RuleBasedCollator collator) {
        this((ULocale) null, collator);
    }

    private AlphabeticIndex(ULocale locale, RuleBasedCollator collator) {
        this.recordComparator = new Comparator<Record<V>>() {
            public int compare(Record<V> o1, Record<V> o2) {
                return AlphabeticIndex.this.collatorOriginal.compare((Object) o1.name, (Object) o2.name);
            }
        };
        this.initialLabels = new UnicodeSet();
        this.overflowLabel = "…";
        this.underflowLabel = "…";
        this.inflowLabel = "…";
        this.maxLabelCount = 99;
        RuleBasedCollator ruleBasedCollator = collator != null ? collator : (RuleBasedCollator) Collator.getInstance(locale);
        this.collatorOriginal = ruleBasedCollator;
        try {
            RuleBasedCollator cloneAsThawed = ruleBasedCollator.cloneAsThawed();
            this.collatorPrimaryOnly = cloneAsThawed;
            cloneAsThawed.setStrength(0);
            cloneAsThawed.freeze();
            List<String> firstCharactersInScripts = getFirstCharactersInScripts();
            this.firstCharsInScripts = firstCharactersInScripts;
            Collections.sort(firstCharactersInScripts, cloneAsThawed);
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

    public AlphabeticIndex<V> setOverflowLabel(String overflowLabel2) {
        this.overflowLabel = overflowLabel2;
        this.buckets = null;
        return this;
    }

    public String getUnderflowLabel() {
        return this.underflowLabel;
    }

    public AlphabeticIndex<V> setUnderflowLabel(String underflowLabel2) {
        this.underflowLabel = underflowLabel2;
        this.buckets = null;
        return this;
    }

    public String getOverflowLabel() {
        return this.overflowLabel;
    }

    public AlphabeticIndex<V> setInflowLabel(String inflowLabel2) {
        this.inflowLabel = inflowLabel2;
        this.buckets = null;
        return this;
    }

    public String getInflowLabel() {
        return this.inflowLabel;
    }

    public int getMaxLabelCount() {
        return this.maxLabelCount;
    }

    public AlphabeticIndex<V> setMaxLabelCount(int maxLabelCount2) {
        this.maxLabelCount = maxLabelCount2;
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
            } else if (item.charAt(item.length() - 1) != '*' || item.charAt(item.length() - 2) == '*') {
                checkDistinct = true;
            } else {
                item = item.substring(0, item.length() - 1);
                checkDistinct = false;
            }
            if (this.collatorPrimaryOnly.compare(item, firstScriptBoundary) >= 0 && this.collatorPrimaryOnly.compare(item, overflowBoundary) < 0) {
                if (!checkDistinct || this.collatorPrimaryOnly.compare(item, separated(item)) != 0) {
                    int insertionPoint = Collections.binarySearch(indexCharacters, item, this.collatorPrimaryOnly);
                    if (insertionPoint < 0) {
                        indexCharacters.add(~insertionPoint, item);
                    } else if (isOneLabelBetterThanOther(nfkdNormalizer, item, indexCharacters.get(insertionPoint))) {
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
        if (10240 >= rest || rest > 10495) {
            return current.substring(BASE.length());
        }
        return (rest - 10240) + "劃";
    }

    private void addIndexExemplars(ULocale locale) {
        UnicodeSet exemplars = LocaleData.getExemplarSet(locale, 0, 2);
        if (exemplars == null || exemplars.isEmpty()) {
            UnicodeSet exemplars2 = LocaleData.getExemplarSet(locale, 0, 0).cloneAsThawed();
            if (exemplars2.containsSome(97, 122) || exemplars2.isEmpty()) {
                exemplars2.addAll(97, 122);
            }
            if (exemplars2.containsSome(44032, 55203)) {
                exemplars2.remove(44032, 55203).add(44032).add(45208).add(45796).add(46972).add(47560).add(48148).add(49324).add(50500).add(51088).add(52264).add(52852).add(53440).add(54028).add(54616);
            }
            if (exemplars2.containsSome(4608, 4991)) {
                UnicodeSet ethiopic = new UnicodeSet("[ሀለሐመሠረሰሸቀቈቐቘበቨተቸኀኈነኘአከኰኸዀወዐዘዠየደዸጀገጐጘጠጨጰጸፀፈፐፘ]");
                ethiopic.retainAll(exemplars2);
                exemplars2.remove(4608, 4991).addAll(ethiopic);
            }
            Iterator<String> it = exemplars2.iterator();
            while (it.hasNext()) {
                this.initialLabels.add((CharSequence) UCharacter.toUpperCase(locale, it.next()));
            }
            return;
        }
        this.initialLabels.addAll(exemplars);
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
                if (s.startsWith(BASE)) {
                    char c = s.charAt(s.length() - 1);
                    if ('A' <= c && c <= 'Z') {
                        this.initialLabels.add(65, 90);
                        break;
                    }
                } else {
                    throw new AssertionError();
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
        if (list == null || list.isEmpty()) {
            if (this.buckets == null) {
                this.buckets = createBucketList();
            }
            immutableBucketList = this.buckets;
        } else {
            immutableBucketList = createBucketList();
        }
        return new ImmutableIndex<>(immutableBucketList, this.collatorPrimaryOnly);
    }

    public List<String> getBucketLabels() {
        initBuckets();
        ArrayList<String> result = new ArrayList<>();
        Iterator<Bucket<V>> it = this.buckets.iterator();
        while (it.hasNext()) {
            result.add(it.next().getLabel());
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
        this.inputList.add(new Record(name, data));
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

    public Iterator<Bucket<V>> iterator() {
        initBuckets();
        return this.buckets.iterator();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: com.ibm.icu.text.AlphabeticIndex$Bucket} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initBuckets() {
        /*
            r8 = this;
            com.ibm.icu.text.AlphabeticIndex$BucketList<V> r0 = r8.buckets
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            com.ibm.icu.text.AlphabeticIndex$BucketList r0 = r8.createBucketList()
            r8.buckets = r0
            java.util.List<com.ibm.icu.text.AlphabeticIndex$Record<V>> r0 = r8.inputList
            if (r0 == 0) goto L_0x0094
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0017
            goto L_0x0094
        L_0x0017:
            java.util.List<com.ibm.icu.text.AlphabeticIndex$Record<V>> r0 = r8.inputList
            java.util.Comparator<com.ibm.icu.text.AlphabeticIndex$Record<V>> r1 = r8.recordComparator
            java.util.Collections.sort(r0, r1)
            com.ibm.icu.text.AlphabeticIndex$BucketList<V> r0 = r8.buckets
            java.util.Iterator r0 = r0.fullIterator()
            java.lang.Object r1 = r0.next()
            com.ibm.icu.text.AlphabeticIndex$Bucket r1 = (com.ibm.icu.text.AlphabeticIndex.Bucket) r1
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x003b
            java.lang.Object r2 = r0.next()
            com.ibm.icu.text.AlphabeticIndex$Bucket r2 = (com.ibm.icu.text.AlphabeticIndex.Bucket) r2
            java.lang.String r3 = r2.lowerBoundary
            goto L_0x003d
        L_0x003b:
            r2 = 0
            r3 = 0
        L_0x003d:
            java.util.List<com.ibm.icu.text.AlphabeticIndex$Record<V>> r4 = r8.inputList
            java.util.Iterator r4 = r4.iterator()
        L_0x0043:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0093
            java.lang.Object r5 = r4.next()
            com.ibm.icu.text.AlphabeticIndex$Record r5 = (com.ibm.icu.text.AlphabeticIndex.Record) r5
        L_0x004f:
            if (r3 == 0) goto L_0x0072
            com.ibm.icu.text.RuleBasedCollator r6 = r8.collatorPrimaryOnly
            java.lang.CharSequence r7 = r5.name
            int r6 = r6.compare((java.lang.Object) r7, (java.lang.Object) r3)
            if (r6 < 0) goto L_0x0072
            r1 = r2
            boolean r6 = r0.hasNext()
            if (r6 == 0) goto L_0x0070
            java.lang.Object r6 = r0.next()
            r2 = r6
            com.ibm.icu.text.AlphabeticIndex$Bucket r2 = (com.ibm.icu.text.AlphabeticIndex.Bucket) r2
            java.lang.String r3 = r2.lowerBoundary
            goto L_0x004f
        L_0x0070:
            r3 = 0
            goto L_0x004f
        L_0x0072:
            r6 = r1
            com.ibm.icu.text.AlphabeticIndex$Bucket r7 = r6.displayBucket
            if (r7 == 0) goto L_0x007d
            com.ibm.icu.text.AlphabeticIndex$Bucket r6 = r6.displayBucket
        L_0x007d:
            java.util.List r7 = r6.records
            if (r7 != 0) goto L_0x008b
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.List unused = r6.records = r7
        L_0x008b:
            java.util.List r7 = r6.records
            r7.add(r5)
            goto L_0x0043
        L_0x0093:
            return
        L_0x0094:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.AlphabeticIndex.initBuckets():void");
    }

    private static boolean isOneLabelBetterThanOther(Normalizer2 nfkdNormalizer, String one, String other) {
        String n1 = nfkdNormalizer.normalize(one);
        String n2 = nfkdNormalizer.normalize(other);
        int result = n1.codePointCount(0, n1.length()) - n2.codePointCount(0, n2.length());
        if (result == 0) {
            Comparator<String> comparator = binaryCmp;
            int result2 = comparator.compare(n1, n2);
            if (result2 != 0) {
                if (result2 < 0) {
                    return true;
                }
                return false;
            } else if (comparator.compare(one, other) < 0) {
                return true;
            } else {
                return false;
            }
        } else if (result < 0) {
            return true;
        } else {
            return false;
        }
    }

    public static class Record<V> {
        private final V data;
        /* access modifiers changed from: private */
        public final CharSequence name;

        private Record(CharSequence name2, V data2) {
            this.name = name2;
            this.data = data2;
        }

        public CharSequence getName() {
            return this.name;
        }

        public V getData() {
            return this.data;
        }

        public String toString() {
            return this.name + "=" + this.data;
        }
    }

    public static class Bucket<V> implements Iterable<Record<V>> {
        /* access modifiers changed from: private */
        public Bucket<V> displayBucket;
        /* access modifiers changed from: private */
        public int displayIndex;
        private final String label;
        /* access modifiers changed from: private */
        public final LabelType labelType;
        /* access modifiers changed from: private */
        public final String lowerBoundary;
        /* access modifiers changed from: private */
        public List<Record<V>> records;

        public enum LabelType {
            NORMAL,
            UNDERFLOW,
            INFLOW,
            OVERFLOW
        }

        private Bucket(String label2, String lowerBoundary2, LabelType labelType2) {
            this.label = label2;
            this.lowerBoundary = lowerBoundary2;
            this.labelType = labelType2;
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

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0100  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x016a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.ibm.icu.text.AlphabeticIndex.BucketList<V> createBucketList() {
        /*
            r21 = this;
            r0 = r21
            java.util.List r1 = r21.initLabels()
            com.ibm.icu.text.RuleBasedCollator r2 = r0.collatorPrimaryOnly
            boolean r2 = r2.isAlternateHandlingShifted()
            if (r2 == 0) goto L_0x001c
            com.ibm.icu.text.RuleBasedCollator r2 = r0.collatorPrimaryOnly
            int r2 = r2.getVariableTop()
            long r2 = (long) r2
            r4 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r2 = r2 & r4
            goto L_0x001e
        L_0x001c:
            r2 = 0
        L_0x001e:
            r4 = 0
            r5 = 26
            com.ibm.icu.text.AlphabeticIndex$Bucket[] r6 = new com.ibm.icu.text.AlphabeticIndex.Bucket[r5]
            com.ibm.icu.text.AlphabeticIndex$Bucket[] r7 = new com.ibm.icu.text.AlphabeticIndex.Bucket[r5]
            r8 = 0
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            com.ibm.icu.text.AlphabeticIndex$Bucket r10 = new com.ibm.icu.text.AlphabeticIndex$Bucket
            java.lang.String r11 = r21.getUnderflowLabel()
            com.ibm.icu.text.AlphabeticIndex$Bucket$LabelType r12 = com.ibm.icu.text.AlphabeticIndex.Bucket.LabelType.UNDERFLOW
            java.lang.String r13 = ""
            r14 = 0
            r10.<init>(r11, r13, r12)
            r9.add(r10)
            r10 = -1
            java.lang.String r11 = ""
            java.util.Iterator r12 = r1.iterator()
        L_0x0043:
            boolean r15 = r12.hasNext()
            r5 = 1
            if (r15 == 0) goto L_0x0179
            java.lang.Object r15 = r12.next()
            java.lang.String r15 = (java.lang.String) r15
            com.ibm.icu.text.RuleBasedCollator r14 = r0.collatorPrimaryOnly
            int r14 = r14.compare(r15, r11)
            if (r14 < 0) goto L_0x009d
            r14 = r11
            r16 = 0
        L_0x005b:
            r17 = r1
            java.util.List<java.lang.String> r1 = r0.firstCharsInScripts
            int r10 = r10 + r5
            java.lang.Object r1 = r1.get(r10)
            r11 = r1
            java.lang.String r11 = (java.lang.String) r11
            com.ibm.icu.text.RuleBasedCollator r1 = r0.collatorPrimaryOnly
            int r1 = r1.compare(r15, r11)
            if (r1 >= 0) goto L_0x0093
            if (r16 == 0) goto L_0x008c
            int r1 = r9.size()
            if (r1 <= r5) goto L_0x008c
            com.ibm.icu.text.AlphabeticIndex$Bucket r1 = new com.ibm.icu.text.AlphabeticIndex$Bucket
            java.lang.String r5 = r21.getInflowLabel()
            r18 = r4
            com.ibm.icu.text.AlphabeticIndex$Bucket$LabelType r4 = com.ibm.icu.text.AlphabeticIndex.Bucket.LabelType.INFLOW
            r19 = r10
            r10 = 0
            r1.<init>(r5, r14, r4)
            r9.add(r1)
            goto L_0x0090
        L_0x008c:
            r18 = r4
            r19 = r10
        L_0x0090:
            r10 = r19
            goto L_0x00a1
        L_0x0093:
            r18 = r4
            r19 = r10
            r16 = 1
            r1 = r17
            r5 = 1
            goto L_0x005b
        L_0x009d:
            r17 = r1
            r18 = r4
        L_0x00a1:
            com.ibm.icu.text.AlphabeticIndex$Bucket r1 = new com.ibm.icu.text.AlphabeticIndex$Bucket
            java.lang.String r4 = fixLabel(r15)
            com.ibm.icu.text.AlphabeticIndex$Bucket$LabelType r5 = com.ibm.icu.text.AlphabeticIndex.Bucket.LabelType.NORMAL
            r14 = 0
            r1.<init>(r4, r15, r5)
            r9.add(r1)
            int r4 = r15.length()
            java.lang.String r5 = "﷐"
            r14 = 1
            if (r4 != r14) goto L_0x00cf
            r4 = 0
            char r4 = r15.charAt(r4)
            r14 = r4
            r20 = r10
            r10 = 65
            if (r10 > r4) goto L_0x00d1
            r4 = 90
            if (r14 > r4) goto L_0x00d1
            int r4 = r14 + -65
            r6[r4] = r1
            goto L_0x00fa
        L_0x00cf:
            r20 = r10
        L_0x00d1:
            int r4 = r15.length()
            int r10 = r5.length()
            r14 = 1
            int r10 = r10 + r14
            if (r4 != r10) goto L_0x00fa
            boolean r4 = r15.startsWith(r5)
            if (r4 == 0) goto L_0x00fa
            int r4 = r5.length()
            char r4 = r15.charAt(r4)
            r10 = r4
            r14 = 65
            if (r14 > r4) goto L_0x00fa
            r4 = 90
            if (r10 > r4) goto L_0x00fa
            int r4 = r10 + -65
            r7[r4] = r1
            r4 = 1
            r8 = r4
        L_0x00fa:
            boolean r4 = r15.startsWith(r5)
            if (r4 != 0) goto L_0x016a
            com.ibm.icu.text.RuleBasedCollator r4 = r0.collatorPrimaryOnly
            boolean r4 = hasMultiplePrimaryWeights(r4, r2, r15)
            if (r4 == 0) goto L_0x0167
            java.lang.String r4 = "￿"
            boolean r5 = r15.endsWith(r4)
            if (r5 != 0) goto L_0x0164
            int r5 = r9.size()
            int r5 = r5 + -2
        L_0x0117:
            java.lang.Object r10 = r9.get(r5)
            com.ibm.icu.text.AlphabeticIndex$Bucket r10 = (com.ibm.icu.text.AlphabeticIndex.Bucket) r10
            com.ibm.icu.text.AlphabeticIndex$Bucket$LabelType r14 = r10.labelType
            r16 = r1
            com.ibm.icu.text.AlphabeticIndex$Bucket$LabelType r1 = com.ibm.icu.text.AlphabeticIndex.Bucket.LabelType.NORMAL
            if (r14 == r1) goto L_0x0128
            goto L_0x016c
        L_0x0128:
            com.ibm.icu.text.AlphabeticIndex$Bucket r1 = r10.displayBucket
            if (r1 != 0) goto L_0x015d
            com.ibm.icu.text.RuleBasedCollator r1 = r0.collatorPrimaryOnly
            java.lang.String r14 = r10.lowerBoundary
            boolean r1 = hasMultiplePrimaryWeights(r1, r2, r14)
            if (r1 != 0) goto L_0x015d
            com.ibm.icu.text.AlphabeticIndex$Bucket r1 = new com.ibm.icu.text.AlphabeticIndex$Bucket
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.StringBuilder r4 = r14.append(r4)
            java.lang.String r4 = r4.toString()
            com.ibm.icu.text.AlphabeticIndex$Bucket$LabelType r14 = com.ibm.icu.text.AlphabeticIndex.Bucket.LabelType.NORMAL
            r0 = 0
            r1.<init>(r13, r4, r14)
            r0 = r1
            com.ibm.icu.text.AlphabeticIndex.Bucket unused = r0.displayBucket = r10
            r9.add(r0)
            r1 = 1
            r4 = r1
            goto L_0x016e
        L_0x015d:
            int r5 = r5 + -1
            r0 = r21
            r1 = r16
            goto L_0x0117
        L_0x0164:
            r16 = r1
            goto L_0x016c
        L_0x0167:
            r16 = r1
            goto L_0x016c
        L_0x016a:
            r16 = r1
        L_0x016c:
            r4 = r18
        L_0x016e:
            r5 = 26
            r14 = 0
            r0 = r21
            r1 = r17
            r10 = r20
            goto L_0x0043
        L_0x0179:
            r17 = r1
            r18 = r4
            int r0 = r9.size()
            r1 = 1
            if (r0 != r1) goto L_0x018b
            com.ibm.icu.text.AlphabeticIndex$BucketList r0 = new com.ibm.icu.text.AlphabeticIndex$BucketList
            r1 = 0
            r0.<init>(r9, r9)
            return r0
        L_0x018b:
            r1 = 0
            com.ibm.icu.text.AlphabeticIndex$Bucket r0 = new com.ibm.icu.text.AlphabeticIndex$Bucket
            java.lang.String r4 = r21.getOverflowLabel()
            com.ibm.icu.text.AlphabeticIndex$Bucket$LabelType r5 = com.ibm.icu.text.AlphabeticIndex.Bucket.LabelType.OVERFLOW
            r0.<init>(r4, r11, r5)
            r9.add(r0)
            if (r8 == 0) goto L_0x01b9
            r0 = 0
            r1 = 0
            r4 = r18
        L_0x01a0:
            r5 = 26
            if (r1 >= r5) goto L_0x01bb
            r12 = r6[r1]
            if (r12 == 0) goto L_0x01aa
            r0 = r6[r1]
        L_0x01aa:
            r12 = r7[r1]
            if (r12 == 0) goto L_0x01b6
            if (r0 == 0) goto L_0x01b6
            r12 = r7[r1]
            com.ibm.icu.text.AlphabeticIndex.Bucket unused = r12.displayBucket = r0
            r4 = 1
        L_0x01b6:
            int r1 = r1 + 1
            goto L_0x01a0
        L_0x01b9:
            r4 = r18
        L_0x01bb:
            if (r4 != 0) goto L_0x01c4
            com.ibm.icu.text.AlphabeticIndex$BucketList r0 = new com.ibm.icu.text.AlphabeticIndex$BucketList
            r1 = 0
            r0.<init>(r9, r9)
            return r0
        L_0x01c4:
            int r0 = r9.size()
            r1 = 1
            int r0 = r0 - r1
            java.lang.Object r1 = r9.get(r0)
            com.ibm.icu.text.AlphabeticIndex$Bucket r1 = (com.ibm.icu.text.AlphabeticIndex.Bucket) r1
        L_0x01d0:
            int r0 = r0 + -1
            if (r0 <= 0) goto L_0x01f7
            java.lang.Object r5 = r9.get(r0)
            com.ibm.icu.text.AlphabeticIndex$Bucket r5 = (com.ibm.icu.text.AlphabeticIndex.Bucket) r5
            com.ibm.icu.text.AlphabeticIndex$Bucket r12 = r5.displayBucket
            if (r12 == 0) goto L_0x01e1
            goto L_0x01d0
        L_0x01e1:
            com.ibm.icu.text.AlphabeticIndex$Bucket$LabelType r12 = r5.labelType
            com.ibm.icu.text.AlphabeticIndex$Bucket$LabelType r13 = com.ibm.icu.text.AlphabeticIndex.Bucket.LabelType.INFLOW
            if (r12 != r13) goto L_0x01f5
            com.ibm.icu.text.AlphabeticIndex$Bucket$LabelType r12 = r1.labelType
            com.ibm.icu.text.AlphabeticIndex$Bucket$LabelType r13 = com.ibm.icu.text.AlphabeticIndex.Bucket.LabelType.NORMAL
            if (r12 == r13) goto L_0x01f5
            com.ibm.icu.text.AlphabeticIndex.Bucket unused = r5.displayBucket = r1
            goto L_0x01d0
        L_0x01f5:
            r1 = r5
            goto L_0x01d0
        L_0x01f7:
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.Iterator r12 = r9.iterator()
        L_0x0200:
            boolean r13 = r12.hasNext()
            if (r13 == 0) goto L_0x0216
            java.lang.Object r13 = r12.next()
            com.ibm.icu.text.AlphabeticIndex$Bucket r13 = (com.ibm.icu.text.AlphabeticIndex.Bucket) r13
            com.ibm.icu.text.AlphabeticIndex$Bucket r14 = r13.displayBucket
            if (r14 != 0) goto L_0x0215
            r5.add(r13)
        L_0x0215:
            goto L_0x0200
        L_0x0216:
            com.ibm.icu.text.AlphabeticIndex$BucketList r12 = new com.ibm.icu.text.AlphabeticIndex$BucketList
            r13 = 0
            r12.<init>(r9, r5)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.AlphabeticIndex.createBucketList():com.ibm.icu.text.AlphabeticIndex$BucketList");
    }

    private static class BucketList<V> implements Iterable<Bucket<V>> {
        private final ArrayList<Bucket<V>> bucketList;
        /* access modifiers changed from: private */
        public final List<Bucket<V>> immutableVisibleList;

        private BucketList(ArrayList<Bucket<V>> bucketList2, ArrayList<Bucket<V>> publicBucketList) {
            this.bucketList = bucketList2;
            int displayIndex = 0;
            Iterator<Bucket<V>> it = publicBucketList.iterator();
            while (it.hasNext()) {
                int unused = it.next().displayIndex = displayIndex;
                displayIndex++;
            }
            this.immutableVisibleList = Collections.unmodifiableList(publicBucketList);
        }

        /* access modifiers changed from: private */
        public int getBucketCount() {
            return this.immutableVisibleList.size();
        }

        /* access modifiers changed from: private */
        public int getBucketIndex(CharSequence name, Collator collatorPrimaryOnly) {
            int start = 0;
            int limit = this.bucketList.size();
            while (start + 1 < limit) {
                int i = (start + limit) / 2;
                if (collatorPrimaryOnly.compare((Object) name, (Object) this.bucketList.get(i).lowerBoundary) < 0) {
                    limit = i;
                } else {
                    start = i;
                }
            }
            Bucket<V> bucket = this.bucketList.get(start);
            if (bucket.displayBucket != null) {
                bucket = bucket.displayBucket;
            }
            return bucket.displayIndex;
        }

        /* access modifiers changed from: private */
        public Iterator<Bucket<V>> fullIterator() {
            return this.bucketList.iterator();
        }

        public Iterator<Bucket<V>> iterator() {
            return this.immutableVisibleList.iterator();
        }
    }

    private static boolean hasMultiplePrimaryWeights(RuleBasedCollator coll, long variableTop, String s) {
        long[] ces = coll.internalGetCEs(s);
        boolean seenPrimary = false;
        for (long ce : ces) {
            if ((ce >>> 32) > variableTop) {
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
        if (!set.isEmpty()) {
            Iterator<String> it = set.iterator();
            while (it.hasNext()) {
                String boundary = it.next();
                if (((1 << UCharacter.getType(boundary.codePointAt(1))) & 63) != 0) {
                    dest.add(boundary);
                }
            }
            return dest;
        }
        throw new UnsupportedOperationException("AlphabeticIndex requires script-first-primary contractions");
    }
}
