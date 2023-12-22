package com.ibm.icu.text;

import com.ibm.icu.impl.ClassLoaderUtil;
import com.ibm.icu.impl.Normalizer2Impl;
import com.ibm.icu.impl.coll.BOCSU;
import com.ibm.icu.impl.coll.CollationCompare;
import com.ibm.icu.impl.coll.CollationData;
import com.ibm.icu.impl.coll.CollationFastLatin;
import com.ibm.icu.impl.coll.CollationKeys;
import com.ibm.icu.impl.coll.CollationLoader;
import com.ibm.icu.impl.coll.CollationRoot;
import com.ibm.icu.impl.coll.CollationSettings;
import com.ibm.icu.impl.coll.CollationTailoring;
import com.ibm.icu.impl.coll.ContractionsAndExpansions;
import com.ibm.icu.impl.coll.FCDUTF16CollationIterator;
import com.ibm.icu.impl.coll.SharedObject;
import com.ibm.icu.impl.coll.TailoredSet;
import com.ibm.icu.impl.coll.UTF16CollationIterator;
import com.ibm.icu.util.ULocale;
import com.ibm.icu.util.VersionInfo;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.CharacterIterator;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes.dex */
public final class RuleBasedCollator extends Collator {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private boolean actualLocaleIsSameAsValid;
    private CollationBuffer collationBuffer;
    CollationData data;
    private Lock frozenLock;
    SharedObject.Reference<CollationSettings> settings;
    CollationTailoring tailoring;
    private ULocale validLocale;

    public RuleBasedCollator(String rules) throws Exception {
        if (rules == null) {
            throw new IllegalArgumentException("Collation rules can not be null");
        }
        this.validLocale = ULocale.ROOT;
        internalBuildTailoring(rules);
    }

    private final void internalBuildTailoring(String rules) throws Exception {
        CollationTailoring base = CollationRoot.getRoot();
        ClassLoader classLoader = ClassLoaderUtil.getClassLoader(getClass());
        try {
            Class<?> builderClass = classLoader.loadClass("com.ibm.icu.impl.coll.CollationBuilder");
            Object builder = builderClass.getConstructor(CollationTailoring.class).newInstance(base);
            Method parseAndBuild = builderClass.getMethod("parseAndBuild", String.class);
            CollationTailoring t = (CollationTailoring) parseAndBuild.invoke(builder, rules);
            t.actualLocale = null;
            adoptTailoring(t);
        } catch (InvocationTargetException e) {
            throw ((Exception) e.getTargetException());
        }
    }

    @Override // com.ibm.icu.text.Collator
    public Object clone() throws CloneNotSupportedException {
        if (isFrozen()) {
            return this;
        }
        return mo73cloneAsThawed();
    }

    private final void initMaxExpansions() {
        synchronized (this.tailoring) {
            if (this.tailoring.maxExpansions == null) {
                CollationTailoring collationTailoring = this.tailoring;
                collationTailoring.maxExpansions = CollationElementIterator.computeMaxExpansions(collationTailoring.data);
            }
        }
    }

    public CollationElementIterator getCollationElementIterator(String source) {
        initMaxExpansions();
        return new CollationElementIterator(source, this);
    }

    public CollationElementIterator getCollationElementIterator(CharacterIterator source) {
        initMaxExpansions();
        CharacterIterator newsource = (CharacterIterator) source.clone();
        return new CollationElementIterator(newsource, this);
    }

    public CollationElementIterator getCollationElementIterator(UCharacterIterator source) {
        initMaxExpansions();
        return new CollationElementIterator(source, this);
    }

    @Override // com.ibm.icu.text.Collator
    public boolean isFrozen() {
        return this.frozenLock != null;
    }

    @Override // com.ibm.icu.text.Collator
    /* renamed from: freeze */
    public Collator mo74freeze() {
        if (!isFrozen()) {
            this.frozenLock = new ReentrantLock();
            if (this.collationBuffer == null) {
                this.collationBuffer = new CollationBuffer(this.data);
            }
        }
        return this;
    }

    @Override // com.ibm.icu.text.Collator
    /* renamed from: cloneAsThawed */
    public RuleBasedCollator mo73cloneAsThawed() {
        try {
            RuleBasedCollator result = (RuleBasedCollator) super.clone();
            result.settings = this.settings.clone();
            result.collationBuffer = null;
            result.frozenLock = null;
            return result;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    private void checkNotFrozen() {
        if (isFrozen()) {
            throw new UnsupportedOperationException("Attempt to modify frozen RuleBasedCollator");
        }
    }

    private final CollationSettings getOwnedSettings() {
        return this.settings.copyOnWrite();
    }

    private final CollationSettings getDefaultSettings() {
        return this.tailoring.settings.readOnly();
    }

    @Deprecated
    public void setHiraganaQuaternary(boolean flag) {
        checkNotFrozen();
    }

    @Deprecated
    public void setHiraganaQuaternaryDefault() {
        checkNotFrozen();
    }

    public void setUpperCaseFirst(boolean upperfirst) {
        checkNotFrozen();
        if (upperfirst == isUpperCaseFirst()) {
            return;
        }
        CollationSettings ownedSettings = getOwnedSettings();
        ownedSettings.setCaseFirst(upperfirst ? 768 : 0);
        setFastLatinOptions(ownedSettings);
    }

    public void setLowerCaseFirst(boolean lowerfirst) {
        checkNotFrozen();
        if (lowerfirst == isLowerCaseFirst()) {
            return;
        }
        CollationSettings ownedSettings = getOwnedSettings();
        ownedSettings.setCaseFirst(lowerfirst ? 512 : 0);
        setFastLatinOptions(ownedSettings);
    }

    public final void setCaseFirstDefault() {
        checkNotFrozen();
        SharedObject defaultSettings = getDefaultSettings();
        if (this.settings.readOnly() == defaultSettings) {
            return;
        }
        CollationSettings ownedSettings = getOwnedSettings();
        ownedSettings.setCaseFirstDefault(((CollationSettings) defaultSettings).options);
        setFastLatinOptions(ownedSettings);
    }

    public void setAlternateHandlingDefault() {
        checkNotFrozen();
        SharedObject defaultSettings = getDefaultSettings();
        if (this.settings.readOnly() == defaultSettings) {
            return;
        }
        CollationSettings ownedSettings = getOwnedSettings();
        ownedSettings.setAlternateHandlingDefault(((CollationSettings) defaultSettings).options);
        setFastLatinOptions(ownedSettings);
    }

    public void setCaseLevelDefault() {
        checkNotFrozen();
        SharedObject defaultSettings = getDefaultSettings();
        if (this.settings.readOnly() == defaultSettings) {
            return;
        }
        CollationSettings ownedSettings = getOwnedSettings();
        ownedSettings.setFlagDefault(1024, ((CollationSettings) defaultSettings).options);
        setFastLatinOptions(ownedSettings);
    }

    public void setDecompositionDefault() {
        checkNotFrozen();
        SharedObject defaultSettings = getDefaultSettings();
        if (this.settings.readOnly() == defaultSettings) {
            return;
        }
        CollationSettings ownedSettings = getOwnedSettings();
        ownedSettings.setFlagDefault(1, ((CollationSettings) defaultSettings).options);
        setFastLatinOptions(ownedSettings);
    }

    public void setFrenchCollationDefault() {
        checkNotFrozen();
        SharedObject defaultSettings = getDefaultSettings();
        if (this.settings.readOnly() == defaultSettings) {
            return;
        }
        CollationSettings ownedSettings = getOwnedSettings();
        ownedSettings.setFlagDefault(2048, ((CollationSettings) defaultSettings).options);
        setFastLatinOptions(ownedSettings);
    }

    public void setStrengthDefault() {
        checkNotFrozen();
        SharedObject defaultSettings = getDefaultSettings();
        if (this.settings.readOnly() == defaultSettings) {
            return;
        }
        CollationSettings ownedSettings = getOwnedSettings();
        ownedSettings.setStrengthDefault(((CollationSettings) defaultSettings).options);
        setFastLatinOptions(ownedSettings);
    }

    public void setNumericCollationDefault() {
        checkNotFrozen();
        SharedObject defaultSettings = getDefaultSettings();
        if (this.settings.readOnly() == defaultSettings) {
            return;
        }
        CollationSettings ownedSettings = getOwnedSettings();
        ownedSettings.setFlagDefault(2, ((CollationSettings) defaultSettings).options);
        setFastLatinOptions(ownedSettings);
    }

    public void setFrenchCollation(boolean flag) {
        checkNotFrozen();
        if (flag == isFrenchCollation()) {
            return;
        }
        CollationSettings ownedSettings = getOwnedSettings();
        ownedSettings.setFlag(2048, flag);
        setFastLatinOptions(ownedSettings);
    }

    public void setAlternateHandlingShifted(boolean shifted) {
        checkNotFrozen();
        if (shifted == isAlternateHandlingShifted()) {
            return;
        }
        CollationSettings ownedSettings = getOwnedSettings();
        ownedSettings.setAlternateHandlingShifted(shifted);
        setFastLatinOptions(ownedSettings);
    }

    public void setCaseLevel(boolean flag) {
        checkNotFrozen();
        if (flag == isCaseLevel()) {
            return;
        }
        CollationSettings ownedSettings = getOwnedSettings();
        ownedSettings.setFlag(1024, flag);
        setFastLatinOptions(ownedSettings);
    }

    @Override // com.ibm.icu.text.Collator
    public void setDecomposition(int decomposition) {
        boolean flag;
        checkNotFrozen();
        switch (decomposition) {
            case 16:
                flag = false;
                break;
            case 17:
                flag = true;
                break;
            default:
                throw new IllegalArgumentException("Wrong decomposition mode.");
        }
        if (flag == this.settings.readOnly().getFlag(1)) {
            return;
        }
        CollationSettings ownedSettings = getOwnedSettings();
        ownedSettings.setFlag(1, flag);
        setFastLatinOptions(ownedSettings);
    }

    @Override // com.ibm.icu.text.Collator
    public void setStrength(int newStrength) {
        checkNotFrozen();
        if (newStrength == getStrength()) {
            return;
        }
        CollationSettings ownedSettings = getOwnedSettings();
        ownedSettings.setStrength(newStrength);
        setFastLatinOptions(ownedSettings);
    }

    @Override // com.ibm.icu.text.Collator
    public RuleBasedCollator setMaxVariable(int group) {
        int value;
        if (group == -1) {
            value = -1;
        } else if (4096 <= group && group <= 4099) {
            value = group - 4096;
        } else {
            throw new IllegalArgumentException("illegal max variable group " + group);
        }
        int oldValue = this.settings.readOnly().getMaxVariable();
        if (value == oldValue) {
            return this;
        }
        SharedObject defaultSettings = getDefaultSettings();
        if (this.settings.readOnly() == defaultSettings && value < 0) {
            return this;
        }
        CollationSettings ownedSettings = getOwnedSettings();
        if (group == -1) {
            group = defaultSettings.getMaxVariable() + 4096;
        }
        long varTop = this.data.getLastPrimaryForGroup(group);
        if (varTop == 0) {
            throw new AssertionError();
        }
        ownedSettings.setMaxVariable(value, ((CollationSettings) defaultSettings).options);
        ownedSettings.variableTop = varTop;
        setFastLatinOptions(ownedSettings);
        return this;
    }

    @Override // com.ibm.icu.text.Collator
    public int getMaxVariable() {
        return this.settings.readOnly().getMaxVariable() + 4096;
    }

    @Override // com.ibm.icu.text.Collator
    @Deprecated
    public int setVariableTop(String varTop) {
        long ce1;
        long ce2;
        checkNotFrozen();
        if (varTop == null || varTop.length() == 0) {
            throw new IllegalArgumentException("Variable top argument string can not be null or zero in length.");
        }
        boolean numeric = this.settings.readOnly().isNumeric();
        if (this.settings.readOnly().dontCheckFCD()) {
            UTF16CollationIterator ci = new UTF16CollationIterator(this.data, numeric, varTop, 0);
            ce1 = ci.nextCE();
            ce2 = ci.nextCE();
        } else {
            FCDUTF16CollationIterator ci2 = new FCDUTF16CollationIterator(this.data, numeric, varTop, 0);
            ce1 = ci2.nextCE();
            ce2 = ci2.nextCE();
        }
        if (ce1 == 4311744768L || ce2 != 4311744768L) {
            throw new IllegalArgumentException("Variable top argument string must map to exactly one collation element");
        }
        internalSetVariableTop(ce1 >>> 32);
        return (int) this.settings.readOnly().variableTop;
    }

    @Override // com.ibm.icu.text.Collator
    @Deprecated
    public void setVariableTop(int varTop) {
        checkNotFrozen();
        internalSetVariableTop(varTop & 4294967295L);
    }

    private void internalSetVariableTop(long varTop) {
        if (varTop != this.settings.readOnly().variableTop) {
            int group = this.data.getGroupForPrimary(varTop);
            if (group < 4096 || 4099 < group) {
                throw new IllegalArgumentException("The variable top must be a primary weight in the space/punctuation/symbols/currency symbols range");
            }
            long v = this.data.getLastPrimaryForGroup(group);
            if (v == 0 || v < varTop) {
                throw new AssertionError();
            }
            if (v != this.settings.readOnly().variableTop) {
                CollationSettings ownedSettings = getOwnedSettings();
                ownedSettings.setMaxVariable(group - 4096, getDefaultSettings().options);
                ownedSettings.variableTop = v;
                setFastLatinOptions(ownedSettings);
            }
        }
    }

    public void setNumericCollation(boolean flag) {
        checkNotFrozen();
        if (flag == getNumericCollation()) {
            return;
        }
        CollationSettings ownedSettings = getOwnedSettings();
        ownedSettings.setFlag(2, flag);
        setFastLatinOptions(ownedSettings);
    }

    @Override // com.ibm.icu.text.Collator
    public void setReorderCodes(int... order) {
        checkNotFrozen();
        int length = order != null ? order.length : 0;
        if (length == 1 && order[0] == 103) {
            length = 0;
        }
        if (length == 0) {
            if (this.settings.readOnly().reorderCodes.length == 0) {
                return;
            }
        } else if (Arrays.equals(order, this.settings.readOnly().reorderCodes)) {
            return;
        }
        SharedObject defaultSettings = getDefaultSettings();
        if (length == 1 && order[0] == -1) {
            if (this.settings.readOnly() != defaultSettings) {
                CollationSettings ownedSettings = getOwnedSettings();
                ownedSettings.copyReorderingFrom(defaultSettings);
                setFastLatinOptions(ownedSettings);
                return;
            }
            return;
        }
        CollationSettings ownedSettings2 = getOwnedSettings();
        if (length == 0) {
            ownedSettings2.resetReordering();
        } else {
            ownedSettings2.setReordering(this.data, (int[]) order.clone());
        }
        setFastLatinOptions(ownedSettings2);
    }

    private void setFastLatinOptions(CollationSettings ownedSettings) {
        ownedSettings.fastLatinOptions = CollationFastLatin.getOptions(this.data, ownedSettings, ownedSettings.fastLatinPrimaries);
    }

    public String getRules() {
        return this.tailoring.getRules();
    }

    public String getRules(boolean fullrules) {
        if (!fullrules) {
            return this.tailoring.getRules();
        }
        return CollationLoader.getRootRules() + this.tailoring.getRules();
    }

    @Override // com.ibm.icu.text.Collator
    public UnicodeSet getTailoredSet() {
        UnicodeSet tailored = new UnicodeSet();
        if (this.data.base != null) {
            new TailoredSet(tailored).forData(this.data);
        }
        return tailored;
    }

    public void getContractionsAndExpansions(UnicodeSet contractions, UnicodeSet expansions, boolean addPrefixes) throws Exception {
        if (contractions != null) {
            contractions.clear();
        }
        if (expansions != null) {
            expansions.clear();
        }
        new ContractionsAndExpansions(contractions, expansions, (ContractionsAndExpansions.CESink) null, addPrefixes).forData(this.data);
    }

    @Deprecated
    void internalAddContractions(int c, UnicodeSet set) {
        new ContractionsAndExpansions(set, (UnicodeSet) null, (ContractionsAndExpansions.CESink) null, false).forCodePoint(this.data, c);
    }

    @Override // com.ibm.icu.text.Collator
    public CollationKey getCollationKey(String source) {
        if (source == null) {
            return null;
        }
        CollationBuffer buffer = null;
        try {
            buffer = getCollationBuffer();
            return getCollationKey(source, buffer);
        } finally {
            releaseCollationBuffer(buffer);
        }
    }

    private CollationKey getCollationKey(String source, CollationBuffer buffer) {
        buffer.rawCollationKey = getRawCollationKey(source, buffer.rawCollationKey, buffer);
        return new CollationKey(source, buffer.rawCollationKey);
    }

    @Override // com.ibm.icu.text.Collator
    public RawCollationKey getRawCollationKey(String source, RawCollationKey key) {
        if (source == null) {
            return null;
        }
        CollationBuffer buffer = null;
        try {
            buffer = getCollationBuffer();
            return getRawCollationKey(source, key, buffer);
        } finally {
            releaseCollationBuffer(buffer);
        }
    }

    /* loaded from: classes.dex */
    private static final class CollationKeyByteSink extends CollationKeys.SortKeyByteSink {
        private RawCollationKey key_;

        CollationKeyByteSink(RawCollationKey key) {
            super(key.bytes);
            this.key_ = key;
        }

        protected void AppendBeyondCapacity(byte[] bytes, int start, int n, int length) {
            if (Resize(n, length)) {
                System.arraycopy(bytes, start, this.buffer_, length, n);
            }
        }

        protected boolean Resize(int appendCapacity, int length) {
            int newCapacity = this.buffer_.length * 2;
            int altCapacity = (appendCapacity * 2) + length;
            if (newCapacity < altCapacity) {
                newCapacity = altCapacity;
            }
            if (newCapacity < 200) {
                newCapacity = 200;
            }
            byte[] newBytes = new byte[newCapacity];
            System.arraycopy(this.buffer_, 0, newBytes, 0, length);
            this.key_.bytes = newBytes;
            this.buffer_ = newBytes;
            return true;
        }
    }

    private RawCollationKey getRawCollationKey(CharSequence source, RawCollationKey key, CollationBuffer buffer) {
        if (key == null) {
            key = new RawCollationKey(simpleKeyLengthEstimate(source));
        } else if (key.bytes == null) {
            key.bytes = new byte[simpleKeyLengthEstimate(source)];
        }
        CollationKeyByteSink sink = new CollationKeyByteSink(key);
        writeSortKey(source, sink, buffer);
        key.size = sink.NumberOfBytesAppended();
        return key;
    }

    private int simpleKeyLengthEstimate(CharSequence source) {
        return (source.length() * 2) + 10;
    }

    private void writeSortKey(CharSequence s, CollationKeyByteSink sink, CollationBuffer buffer) {
        boolean numeric = this.settings.readOnly().isNumeric();
        if (this.settings.readOnly().dontCheckFCD()) {
            buffer.leftUTF16CollIter.setText(numeric, s, 0);
            CollationKeys.writeSortKeyUpToQuaternary(buffer.leftUTF16CollIter, this.data.compressibleBytes, this.settings.readOnly(), sink, 1, CollationKeys.SIMPLE_LEVEL_FALLBACK, true);
        } else {
            buffer.leftFCDUTF16Iter.setText(numeric, s, 0);
            CollationKeys.writeSortKeyUpToQuaternary(buffer.leftFCDUTF16Iter, this.data.compressibleBytes, this.settings.readOnly(), sink, 1, CollationKeys.SIMPLE_LEVEL_FALLBACK, true);
        }
        if (this.settings.readOnly().getStrength() == 15) {
            writeIdenticalLevel(s, sink);
        }
        sink.Append(0);
    }

    private void writeIdenticalLevel(CharSequence s, CollationKeyByteSink sink) {
        int nfdQCYesLimit = this.data.nfcImpl.decompose(s, 0, s.length(), (Normalizer2Impl.ReorderingBuffer) null);
        sink.Append(1);
        sink.key_.size = sink.NumberOfBytesAppended();
        int prev = nfdQCYesLimit != 0 ? BOCSU.writeIdenticalLevelRun(0, s, 0, nfdQCYesLimit, sink.key_) : 0;
        if (nfdQCYesLimit < s.length()) {
            int destLengthEstimate = s.length() - nfdQCYesLimit;
            StringBuilder nfd = new StringBuilder();
            this.data.nfcImpl.decompose(s, nfdQCYesLimit, s.length(), nfd, destLengthEstimate);
            BOCSU.writeIdenticalLevelRun(prev, nfd, 0, nfd.length(), sink.key_);
        }
        sink.setBufferAndAppended(sink.key_.bytes, sink.key_.size);
    }

    @Deprecated
    public long[] internalGetCEs(CharSequence str) {
        UTF16CollationIterator uTF16CollationIterator;
        CollationBuffer buffer = null;
        try {
            buffer = getCollationBuffer();
            boolean numeric = this.settings.readOnly().isNumeric();
            if (this.settings.readOnly().dontCheckFCD()) {
                buffer.leftUTF16CollIter.setText(numeric, str, 0);
                uTF16CollationIterator = buffer.leftUTF16CollIter;
            } else {
                buffer.leftFCDUTF16Iter.setText(numeric, str, 0);
                uTF16CollationIterator = buffer.leftFCDUTF16Iter;
            }
            int length = uTF16CollationIterator.fetchCEs() - 1;
            if (length < 0 || uTF16CollationIterator.getCE(length) != 4311744768L) {
                throw new AssertionError();
            }
            long[] ces = new long[length];
            System.arraycopy(uTF16CollationIterator.getCEs(), 0, ces, 0, length);
            return ces;
        } finally {
            releaseCollationBuffer(buffer);
        }
    }

    @Override // com.ibm.icu.text.Collator
    public int getStrength() {
        return this.settings.readOnly().getStrength();
    }

    @Override // com.ibm.icu.text.Collator
    public int getDecomposition() {
        return (this.settings.readOnly().options & 1) != 0 ? 17 : 16;
    }

    public boolean isUpperCaseFirst() {
        return this.settings.readOnly().getCaseFirst() == 768;
    }

    public boolean isLowerCaseFirst() {
        return this.settings.readOnly().getCaseFirst() == 512;
    }

    public boolean isAlternateHandlingShifted() {
        return this.settings.readOnly().getAlternateHandling();
    }

    public boolean isCaseLevel() {
        return (this.settings.readOnly().options & 1024) != 0;
    }

    public boolean isFrenchCollation() {
        return (this.settings.readOnly().options & 2048) != 0;
    }

    @Deprecated
    public boolean isHiraganaQuaternary() {
        return false;
    }

    @Override // com.ibm.icu.text.Collator
    public int getVariableTop() {
        return (int) this.settings.readOnly().variableTop;
    }

    public boolean getNumericCollation() {
        return (this.settings.readOnly().options & 2) != 0;
    }

    @Override // com.ibm.icu.text.Collator
    public int[] getReorderCodes() {
        return (int[]) this.settings.readOnly().reorderCodes.clone();
    }

    @Override // com.ibm.icu.text.Collator, java.util.Comparator
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (super.equals(obj)) {
            RuleBasedCollator o = (RuleBasedCollator) obj;
            if (this.settings.readOnly().equals(o.settings.readOnly())) {
                CollationData collationData = this.data;
                if (collationData == o.data) {
                    return true;
                }
                boolean thisIsRoot = collationData.base == null;
                boolean otherIsRoot = o.data.base == null;
                if (thisIsRoot && otherIsRoot) {
                    throw new AssertionError();
                }
                if (thisIsRoot != otherIsRoot) {
                    return false;
                }
                String theseRules = this.tailoring.getRules();
                String otherRules = o.tailoring.getRules();
                if ((thisIsRoot || theseRules.length() != 0) && ((otherIsRoot || otherRules.length() != 0) && theseRules.equals(otherRules))) {
                    return true;
                }
                UnicodeSet thisTailored = getTailoredSet();
                UnicodeSet otherTailored = o.getTailoredSet();
                return thisTailored.equals(otherTailored);
            }
            return false;
        }
        return false;
    }

    @Override // com.ibm.icu.text.Collator
    public int hashCode() {
        int h = this.settings.readOnly().hashCode();
        if (this.data.base == null) {
            return h;
        }
        UnicodeSet set = getTailoredSet();
        UnicodeSetIterator iter = new UnicodeSetIterator(set);
        while (iter.next() && iter.codepoint != UnicodeSetIterator.IS_STRING) {
            h ^= this.data.getCE32(iter.codepoint);
        }
        return h;
    }

    @Override // com.ibm.icu.text.Collator
    public int compare(String source, String target) {
        return doCompare(source, target);
    }

    /* loaded from: classes.dex */
    private static abstract class NFDIterator {
        private String decomp;
        private int index;

        protected abstract int nextRawCodePoint();

        NFDIterator() {
        }

        final void reset() {
            this.index = -1;
        }

        final int nextCodePoint() {
            int i = this.index;
            if (i >= 0) {
                if (i == this.decomp.length()) {
                    this.index = -1;
                } else {
                    int c = Character.codePointAt(this.decomp, this.index);
                    this.index += Character.charCount(c);
                    return c;
                }
            }
            return nextRawCodePoint();
        }

        final int nextDecomposedCodePoint(Normalizer2Impl nfcImpl, int c) {
            if (this.index >= 0) {
                return c;
            }
            String decomposition = nfcImpl.getDecomposition(c);
            this.decomp = decomposition;
            if (decomposition == null) {
                return c;
            }
            int c2 = Character.codePointAt(decomposition, 0);
            this.index = Character.charCount(c2);
            return c2;
        }
    }

    /* loaded from: classes.dex */
    private static class UTF16NFDIterator extends NFDIterator {
        protected int pos;

        /* renamed from: s */
        protected CharSequence f164s;

        UTF16NFDIterator() {
        }

        void setText(CharSequence seq, int start) {
            reset();
            this.f164s = seq;
            this.pos = start;
        }

        @Override // com.ibm.icu.text.RuleBasedCollator.NFDIterator
        protected int nextRawCodePoint() {
            if (this.pos == this.f164s.length()) {
                return -1;
            }
            int c = Character.codePointAt(this.f164s, this.pos);
            this.pos += Character.charCount(c);
            return c;
        }
    }

    /* loaded from: classes.dex */
    private static final class FCDUTF16NFDIterator extends UTF16NFDIterator {
        private StringBuilder str;

        FCDUTF16NFDIterator() {
        }

        void setText(Normalizer2Impl nfcImpl, CharSequence seq, int start) {
            reset();
            int spanLimit = nfcImpl.makeFCD(seq, start, seq.length(), (Normalizer2Impl.ReorderingBuffer) null);
            if (spanLimit == seq.length()) {
                this.f164s = seq;
                this.pos = start;
                return;
            }
            StringBuilder sb = this.str;
            if (sb == null) {
                this.str = new StringBuilder();
            } else {
                sb.setLength(0);
            }
            this.str.append(seq, start, spanLimit);
            Normalizer2Impl.ReorderingBuffer buffer = new Normalizer2Impl.ReorderingBuffer(nfcImpl, this.str, seq.length() - start);
            nfcImpl.makeFCD(seq, spanLimit, seq.length(), buffer);
            this.f164s = this.str;
            this.pos = 0;
        }
    }

    private static final int compareNFDIter(Normalizer2Impl nfcImpl, NFDIterator left, NFDIterator right) {
        int leftCp;
        int rightCp;
        while (true) {
            int leftCp2 = left.nextCodePoint();
            int rightCp2 = right.nextCodePoint();
            if (leftCp2 == rightCp2) {
                if (leftCp2 < 0) {
                    return 0;
                }
            } else {
                if (leftCp2 < 0) {
                    leftCp = -2;
                } else if (leftCp2 == 65534) {
                    leftCp = -1;
                } else {
                    leftCp = left.nextDecomposedCodePoint(nfcImpl, leftCp2);
                }
                if (rightCp2 < 0) {
                    rightCp = -2;
                } else if (rightCp2 == 65534) {
                    rightCp = -1;
                } else {
                    rightCp = right.nextDecomposedCodePoint(nfcImpl, rightCp2);
                }
                if (leftCp < rightCp) {
                    return -1;
                }
                if (leftCp > rightCp) {
                    return 1;
                }
            }
        }
    }

    @Override // com.ibm.icu.text.Collator
    @Deprecated
    protected int doCompare(CharSequence left, CharSequence right) {
        int result;
        CollationBuffer buffer;
        if (left == right) {
            return 0;
        }
        int equalPrefixLength = 0;
        while (true) {
            if (equalPrefixLength == left.length()) {
                if (equalPrefixLength == right.length()) {
                    return 0;
                }
            } else if (equalPrefixLength == right.length() || left.charAt(equalPrefixLength) != right.charAt(equalPrefixLength)) {
                break;
            } else {
                equalPrefixLength++;
            }
        }
        CollationSettings roSettings = this.settings.readOnly();
        boolean numeric = roSettings.isNumeric();
        if (equalPrefixLength > 0 && ((equalPrefixLength != left.length() && this.data.isUnsafeBackward(left.charAt(equalPrefixLength), numeric)) || (equalPrefixLength != right.length() && this.data.isUnsafeBackward(right.charAt(equalPrefixLength), numeric)))) {
            do {
                equalPrefixLength--;
                if (equalPrefixLength <= 0) {
                    break;
                }
            } while (this.data.isUnsafeBackward(left.charAt(equalPrefixLength), numeric));
        }
        int fastLatinOptions = roSettings.fastLatinOptions;
        if (fastLatinOptions >= 0 && ((equalPrefixLength == left.length() || left.charAt(equalPrefixLength) <= '\u017f') && (equalPrefixLength == right.length() || right.charAt(equalPrefixLength) <= '\u017f'))) {
            result = CollationFastLatin.compareUTF16(this.data.fastLatinTable, roSettings.fastLatinPrimaries, fastLatinOptions, left, right, equalPrefixLength);
        } else {
            result = -2;
        }
        if (result == -2) {
            buffer = null;
            try {
                buffer = getCollationBuffer();
                if (roSettings.dontCheckFCD()) {
                    buffer.leftUTF16CollIter.setText(numeric, left, equalPrefixLength);
                    buffer.rightUTF16CollIter.setText(numeric, right, equalPrefixLength);
                    result = CollationCompare.compareUpToQuaternary(buffer.leftUTF16CollIter, buffer.rightUTF16CollIter, roSettings);
                } else {
                    buffer.leftFCDUTF16Iter.setText(numeric, left, equalPrefixLength);
                    buffer.rightFCDUTF16Iter.setText(numeric, right, equalPrefixLength);
                    result = CollationCompare.compareUpToQuaternary(buffer.leftFCDUTF16Iter, buffer.rightFCDUTF16Iter, roSettings);
                }
            } finally {
            }
        }
        if (result != 0 || roSettings.getStrength() < 15) {
            return result;
        }
        buffer = null;
        try {
            buffer = getCollationBuffer();
            Normalizer2Impl nfcImpl = this.data.nfcImpl;
            if (roSettings.dontCheckFCD()) {
                buffer.leftUTF16NFDIter.setText(left, equalPrefixLength);
                buffer.rightUTF16NFDIter.setText(right, equalPrefixLength);
                return compareNFDIter(nfcImpl, buffer.leftUTF16NFDIter, buffer.rightUTF16NFDIter);
            }
            buffer.leftFCDUTF16NFDIter.setText(nfcImpl, left, equalPrefixLength);
            buffer.rightFCDUTF16NFDIter.setText(nfcImpl, right, equalPrefixLength);
            return compareNFDIter(nfcImpl, buffer.leftFCDUTF16NFDIter, buffer.rightFCDUTF16NFDIter);
        } finally {
        }
    }

    RuleBasedCollator(CollationTailoring t, ULocale vl) {
        this.data = t.data;
        this.settings = t.settings.clone();
        this.tailoring = t;
        this.validLocale = vl;
        this.actualLocaleIsSameAsValid = false;
    }

    private void adoptTailoring(CollationTailoring t) {
        if (this.settings != null || this.data != null || this.tailoring != null) {
            throw new AssertionError();
        }
        this.data = t.data;
        this.settings = t.settings.clone();
        this.tailoring = t;
        this.validLocale = t.actualLocale;
        this.actualLocaleIsSameAsValid = false;
    }

    final boolean isUnsafe(int c) {
        return this.data.isUnsafeBackward(c, this.settings.readOnly().isNumeric());
    }

    /* loaded from: classes.dex */
    private static final class CollationBuffer {
        FCDUTF16CollationIterator leftFCDUTF16Iter;
        FCDUTF16NFDIterator leftFCDUTF16NFDIter;
        UTF16CollationIterator leftUTF16CollIter;
        UTF16NFDIterator leftUTF16NFDIter;
        RawCollationKey rawCollationKey;
        FCDUTF16CollationIterator rightFCDUTF16Iter;
        FCDUTF16NFDIterator rightFCDUTF16NFDIter;
        UTF16CollationIterator rightUTF16CollIter;
        UTF16NFDIterator rightUTF16NFDIter;

        private CollationBuffer(CollationData data) {
            this.leftUTF16CollIter = new UTF16CollationIterator(data);
            this.rightUTF16CollIter = new UTF16CollationIterator(data);
            this.leftFCDUTF16Iter = new FCDUTF16CollationIterator(data);
            this.rightFCDUTF16Iter = new FCDUTF16CollationIterator(data);
            this.leftUTF16NFDIter = new UTF16NFDIterator();
            this.rightUTF16NFDIter = new UTF16NFDIterator();
            this.leftFCDUTF16NFDIter = new FCDUTF16NFDIterator();
            this.rightFCDUTF16NFDIter = new FCDUTF16NFDIterator();
        }
    }

    @Override // com.ibm.icu.text.Collator
    public VersionInfo getVersion() {
        int version = this.tailoring.version;
        int rtVersion = VersionInfo.UCOL_RUNTIME_VERSION.getMajor();
        return VersionInfo.getInstance((version >>> 24) + (rtVersion << 4) + (rtVersion >> 4), (version >> 16) & 255, (version >> 8) & 255, version & 255);
    }

    @Override // com.ibm.icu.text.Collator
    public VersionInfo getUCAVersion() {
        VersionInfo v = getVersion();
        return VersionInfo.getInstance(v.getMinor() >> 3, v.getMinor() & 7, v.getMilli() >> 6, 0);
    }

    private final CollationBuffer getCollationBuffer() {
        if (isFrozen()) {
            this.frozenLock.lock();
        } else if (this.collationBuffer == null) {
            this.collationBuffer = new CollationBuffer(this.data);
        }
        return this.collationBuffer;
    }

    private final void releaseCollationBuffer(CollationBuffer buffer) {
        if (isFrozen()) {
            this.frozenLock.unlock();
        }
    }

    @Override // com.ibm.icu.text.Collator
    public ULocale getLocale(ULocale.Type type) {
        if (type == ULocale.ACTUAL_LOCALE) {
            return this.actualLocaleIsSameAsValid ? this.validLocale : this.tailoring.actualLocale;
        } else if (type == ULocale.VALID_LOCALE) {
            return this.validLocale;
        } else {
            throw new IllegalArgumentException("unknown ULocale.Type " + type);
        }
    }

    @Override // com.ibm.icu.text.Collator
    void setLocale(ULocale valid, ULocale actual) {
        if ((valid == null) != (actual == null)) {
            throw new AssertionError();
        }
        if (Objects.equals(actual, this.tailoring.actualLocale)) {
            this.actualLocaleIsSameAsValid = false;
        } else if (!Objects.equals(actual, valid)) {
            throw new AssertionError();
        } else {
            this.actualLocaleIsSameAsValid = true;
        }
        this.validLocale = valid;
    }
}
