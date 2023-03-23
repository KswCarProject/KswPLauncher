package com.ibm.icu.text;

import com.ibm.icu.text.PluralRules;
import com.ibm.icu.util.Output;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import kotlin.time.DurationKt;

@Deprecated
public class PluralSamples {
    private static final int LIMIT_FRACTION_SAMPLES = 3;
    private static final int[] TENS = {1, 10, 100, 1000, 10000, 100000, DurationKt.NANOS_IN_MILLIS};
    private final Set<PluralRules.FixedDecimal> _fractionSamples;
    private final Map<String, Set<PluralRules.FixedDecimal>> _keyFractionSamplesMap;
    @Deprecated
    public final Map<String, Boolean> _keyLimitedMap;
    private final Map<String, List<Double>> _keySamplesMap;
    private PluralRules pluralRules;

    @Deprecated
    public PluralSamples(PluralRules pluralRules2) {
        PluralRules pluralRules3 = pluralRules2;
        this.pluralRules = pluralRules3;
        Set<String> keywords = pluralRules2.getKeywords();
        Map<String, Boolean> temp = new HashMap<>();
        for (String k : keywords) {
            temp.put(k, pluralRules3.isLimited(k));
        }
        this._keyLimitedMap = temp;
        Map<String, List<Double>> sampleMap = new HashMap<>();
        int keywordsRemaining = keywords.size();
        int i = 0;
        while (keywordsRemaining > 0 && i < 128) {
            keywordsRemaining = addSimpleSamples(pluralRules2, 3, sampleMap, keywordsRemaining, ((double) i) / 2.0d);
            i++;
        }
        int keywordsRemaining2 = addSimpleSamples(pluralRules2, 3, sampleMap, keywordsRemaining, 1000000.0d);
        Map<String, Set<PluralRules.FixedDecimal>> sampleFractionMap = new HashMap<>();
        Set<PluralRules.FixedDecimal> mentioned = new TreeSet<>();
        Map<String, Set<PluralRules.FixedDecimal>> foundKeywords = new HashMap<>();
        for (PluralRules.FixedDecimal s : mentioned) {
            addRelation(foundKeywords, pluralRules3.select((PluralRules.IFixedDecimal) s), s);
        }
        if (foundKeywords.size() != keywords.size()) {
            int i2 = 1;
            while (true) {
                if (i2 >= 1000) {
                    int i3 = 10;
                    while (true) {
                        if (i3 >= 1000) {
                            System.out.println("Failed to find sample for each keyword: " + foundKeywords + "\n\t" + pluralRules3 + "\n\t" + mentioned);
                            break;
                        } else if (addIfNotPresent(((double) i3) / 10.0d, mentioned, foundKeywords)) {
                            break;
                        } else {
                            i3++;
                        }
                    }
                } else if (addIfNotPresent((double) i2, mentioned, foundKeywords)) {
                    break;
                } else {
                    i2++;
                }
            }
        }
        mentioned.add(new PluralRules.FixedDecimal(0));
        mentioned.add(new PluralRules.FixedDecimal(1));
        mentioned.add(new PluralRules.FixedDecimal(2));
        mentioned.add(new PluralRules.FixedDecimal(0.1d, 1));
        mentioned.add(new PluralRules.FixedDecimal(1.99d, 2));
        mentioned.addAll(fractions(mentioned));
        for (PluralRules.FixedDecimal s2 : mentioned) {
            String keyword = pluralRules3.select((PluralRules.IFixedDecimal) s2);
            Set<PluralRules.FixedDecimal> list = sampleFractionMap.get(keyword);
            if (list == null) {
                list = new LinkedHashSet<>();
                sampleFractionMap.put(keyword, list);
            }
            list.add(s2);
        }
        if (keywordsRemaining2 > 0) {
            for (String k2 : keywords) {
                if (!sampleMap.containsKey(k2)) {
                    sampleMap.put(k2, Collections.emptyList());
                }
                if (!sampleFractionMap.containsKey(k2)) {
                    sampleFractionMap.put(k2, Collections.emptySet());
                }
            }
        }
        for (Map.Entry<String, List<Double>> entry : sampleMap.entrySet()) {
            sampleMap.put(entry.getKey(), Collections.unmodifiableList(entry.getValue()));
        }
        for (Map.Entry<String, Set<PluralRules.FixedDecimal>> entry2 : sampleFractionMap.entrySet()) {
            sampleFractionMap.put(entry2.getKey(), Collections.unmodifiableSet(entry2.getValue()));
        }
        this._keySamplesMap = sampleMap;
        this._keyFractionSamplesMap = sampleFractionMap;
        this._fractionSamples = Collections.unmodifiableSet(mentioned);
    }

    private int addSimpleSamples(PluralRules pluralRules2, int MAX_SAMPLES, Map<String, List<Double>> sampleMap, int keywordsRemaining, double val) {
        String keyword = pluralRules2.select(val);
        boolean keyIsLimited = this._keyLimitedMap.get(keyword).booleanValue();
        List<Double> list = sampleMap.get(keyword);
        if (list == null) {
            list = new ArrayList<>(MAX_SAMPLES);
            sampleMap.put(keyword, list);
        } else if (!keyIsLimited && list.size() == MAX_SAMPLES) {
            return keywordsRemaining;
        }
        list.add(Double.valueOf(val));
        if (keyIsLimited || list.size() != MAX_SAMPLES) {
            return keywordsRemaining;
        }
        return keywordsRemaining - 1;
    }

    private void addRelation(Map<String, Set<PluralRules.FixedDecimal>> foundKeywords, String keyword, PluralRules.FixedDecimal s) {
        Set<PluralRules.FixedDecimal> set = foundKeywords.get(keyword);
        if (set == null) {
            Set<PluralRules.FixedDecimal> hashSet = new HashSet<>();
            set = hashSet;
            foundKeywords.put(keyword, hashSet);
        }
        set.add(s);
    }

    private boolean addIfNotPresent(double d, Set<PluralRules.FixedDecimal> mentioned, Map<String, Set<PluralRules.FixedDecimal>> foundKeywords) {
        PluralRules.FixedDecimal numberInfo = new PluralRules.FixedDecimal(d);
        String keyword = this.pluralRules.select((PluralRules.IFixedDecimal) numberInfo);
        if (foundKeywords.containsKey(keyword) && !keyword.equals(PluralRules.KEYWORD_OTHER)) {
            return false;
        }
        addRelation(foundKeywords, keyword, numberInfo);
        mentioned.add(numberInfo);
        if (!keyword.equals(PluralRules.KEYWORD_OTHER) || foundKeywords.get(PluralRules.KEYWORD_OTHER).size() <= 1) {
            return false;
        }
        return true;
    }

    private Set<PluralRules.FixedDecimal> fractions(Set<PluralRules.FixedDecimal> original) {
        List<Integer> ints;
        Set<Integer> result;
        String keyword;
        List<Integer> ints2;
        Set<Integer> result2;
        Integer base;
        Set<PluralRules.FixedDecimal> toAddTo = new HashSet<>();
        Set<Integer> result3 = new HashSet<>();
        for (PluralRules.FixedDecimal base1 : original) {
            result3.add(Integer.valueOf((int) base1.integerValue));
        }
        List<Integer> ints3 = new ArrayList<>(result3);
        Set<String> keywords = new HashSet<>();
        int j = 0;
        while (j < ints3.size()) {
            Integer base2 = ints3.get(j);
            String keyword2 = this.pluralRules.select((double) base2.intValue());
            if (keywords.contains(keyword2)) {
                result = result3;
                ints = ints3;
            } else {
                keywords.add(keyword2);
                toAddTo.add(new PluralRules.FixedDecimal((double) base2.intValue(), 1));
                toAddTo.add(new PluralRules.FixedDecimal((double) base2.intValue(), 2));
                Integer fract = getDifferentCategory(ints3, keyword2);
                if (fract.intValue() >= TENS[2]) {
                    toAddTo.add(new PluralRules.FixedDecimal(base2 + "." + fract));
                    result = result3;
                    ints = ints3;
                } else {
                    for (int visibleFractions = 1; visibleFractions < 3; visibleFractions++) {
                        int i = 1;
                        while (i <= visibleFractions) {
                            int intValue = fract.intValue();
                            int[] iArr = TENS;
                            if (intValue >= iArr[i]) {
                                result2 = result3;
                                ints2 = ints3;
                                base = base2;
                                keyword = keyword2;
                            } else {
                                result2 = result3;
                                ints2 = ints3;
                                base = base2;
                                keyword = keyword2;
                                toAddTo.add(new PluralRules.FixedDecimal(((double) base2.intValue()) + (((double) fract.intValue()) / ((double) iArr[i])), visibleFractions));
                            }
                            i++;
                            base2 = base;
                            result3 = result2;
                            ints3 = ints2;
                            keyword2 = keyword;
                        }
                        List<Integer> list = ints3;
                        Integer num = base2;
                        String str = keyword2;
                    }
                    result = result3;
                    ints = ints3;
                    Integer num2 = base2;
                    String str2 = keyword2;
                }
            }
            j++;
            result3 = result;
            ints3 = ints;
        }
        return toAddTo;
    }

    private Integer getDifferentCategory(List<Integer> ints, String keyword) {
        for (int i = ints.size() - 1; i >= 0; i--) {
            Integer other = ints.get(i);
            if (!this.pluralRules.select((double) other.intValue()).equals(keyword)) {
                return other;
            }
        }
        return 37;
    }

    @Deprecated
    public PluralRules.KeywordStatus getStatus(String keyword, int offset, Set<Double> explicits, Output<Double> uniqueValue) {
        if (uniqueValue != null) {
            uniqueValue.value = null;
        }
        if (!this.pluralRules.getKeywords().contains(keyword)) {
            return PluralRules.KeywordStatus.INVALID;
        }
        Collection<Double> values = this.pluralRules.getAllKeywordValues(keyword);
        if (values == null) {
            return PluralRules.KeywordStatus.UNBOUNDED;
        }
        int originalSize = values.size();
        if (explicits == null) {
            explicits = Collections.emptySet();
        }
        if (originalSize <= explicits.size()) {
            HashSet<Double> subtractedSet = new HashSet<>(values);
            for (Double explicit : explicits) {
                subtractedSet.remove(Double.valueOf(explicit.doubleValue() - ((double) offset)));
            }
            if (subtractedSet.size() == 0) {
                return PluralRules.KeywordStatus.SUPPRESSED;
            }
            if (uniqueValue != null && subtractedSet.size() == 1) {
                uniqueValue.value = subtractedSet.iterator().next();
            }
            return originalSize == 1 ? PluralRules.KeywordStatus.UNIQUE : PluralRules.KeywordStatus.BOUNDED;
        } else if (originalSize != 1) {
            return PluralRules.KeywordStatus.BOUNDED;
        } else {
            if (uniqueValue != null) {
                uniqueValue.value = values.iterator().next();
            }
            return PluralRules.KeywordStatus.UNIQUE;
        }
    }

    /* access modifiers changed from: package-private */
    public Map<String, List<Double>> getKeySamplesMap() {
        return this._keySamplesMap;
    }

    /* access modifiers changed from: package-private */
    public Map<String, Set<PluralRules.FixedDecimal>> getKeyFractionSamplesMap() {
        return this._keyFractionSamplesMap;
    }

    /* access modifiers changed from: package-private */
    public Set<PluralRules.FixedDecimal> getFractionSamples() {
        return this._fractionSamples;
    }

    /* access modifiers changed from: package-private */
    public Collection<Double> getAllKeywordValues(String keyword) {
        if (!this.pluralRules.getKeywords().contains(keyword)) {
            return Collections.emptyList();
        }
        Collection<Double> result = getKeySamplesMap().get(keyword);
        if (result.size() <= 2 || this._keyLimitedMap.get(keyword).booleanValue()) {
            return result;
        }
        return null;
    }
}
