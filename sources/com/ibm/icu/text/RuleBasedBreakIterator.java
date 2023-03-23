package com.ibm.icu.text;

import com.ibm.icu.impl.CharacterIteration;
import com.ibm.icu.impl.ICUBinary;
import com.ibm.icu.impl.ICUDebug;
import com.ibm.icu.impl.RBBIDataWrapper;
import com.ibm.icu.text.DictionaryBreakEngine;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;

public class RuleBasedBreakIterator extends BreakIterator {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String RBBI_DEBUG_ARG = "rbbi";
    private static final int RBBI_END = 2;
    private static final int RBBI_RUN = 1;
    private static final int RBBI_START = 0;
    private static final int START_STATE = 1;
    private static final int STOP_STATE = 0;
    private static final boolean TRACE = (ICUDebug.enabled(RBBI_DEBUG_ARG) && ICUDebug.value(RBBI_DEBUG_ARG).indexOf("trace") >= 0);
    @Deprecated
    public static final String fDebugEnv = (ICUDebug.enabled(RBBI_DEBUG_ARG) ? ICUDebug.value(RBBI_DEBUG_ARG) : null);
    private static final List<LanguageBreakEngine> gAllBreakEngines;
    private static final UnhandledBreakEngine gUnhandledBreakEngine;
    private static final int kMaxLookaheads = 8;
    private BreakCache fBreakCache;
    private List<LanguageBreakEngine> fBreakEngines;
    /* access modifiers changed from: private */
    public DictionaryCache fDictionaryCache;
    /* access modifiers changed from: private */
    public int fDictionaryCharCount;
    /* access modifiers changed from: private */
    public boolean fDone;
    private LookAheadResults fLookAheadMatches;
    /* access modifiers changed from: private */
    public int fPosition;
    @Deprecated
    public RBBIDataWrapper fRData;
    /* access modifiers changed from: private */
    public int fRuleStatusIndex;
    /* access modifiers changed from: private */
    public CharacterIterator fText;

    static {
        UnhandledBreakEngine unhandledBreakEngine = new UnhandledBreakEngine();
        gUnhandledBreakEngine = unhandledBreakEngine;
        ArrayList arrayList = new ArrayList();
        gAllBreakEngines = arrayList;
        arrayList.add(unhandledBreakEngine);
    }

    private RuleBasedBreakIterator() {
        this.fText = new StringCharacterIterator("");
        this.fBreakCache = new BreakCache();
        this.fDictionaryCache = new DictionaryCache();
        this.fLookAheadMatches = new LookAheadResults();
        this.fDictionaryCharCount = 0;
        List<LanguageBreakEngine> list = gAllBreakEngines;
        synchronized (list) {
            this.fBreakEngines = new ArrayList(list);
        }
    }

    public static RuleBasedBreakIterator getInstanceFromCompiledRules(InputStream is) throws IOException {
        RuleBasedBreakIterator This = new RuleBasedBreakIterator();
        This.fRData = RBBIDataWrapper.get(ICUBinary.getByteBufferFromInputStreamAndCloseStream(is));
        return This;
    }

    @Deprecated
    public static RuleBasedBreakIterator getInstanceFromCompiledRules(ByteBuffer bytes) throws IOException {
        RuleBasedBreakIterator This = new RuleBasedBreakIterator();
        This.fRData = RBBIDataWrapper.get(bytes);
        return This;
    }

    public RuleBasedBreakIterator(String rules) {
        this();
        try {
            ByteArrayOutputStream ruleOS = new ByteArrayOutputStream();
            compileRules(rules, ruleOS);
            this.fRData = RBBIDataWrapper.get(ByteBuffer.wrap(ruleOS.toByteArray()));
        } catch (IOException e) {
            throw new RuntimeException("RuleBasedBreakIterator rule compilation internal error: " + e.getMessage());
        }
    }

    public Object clone() {
        RuleBasedBreakIterator result = (RuleBasedBreakIterator) super.clone();
        CharacterIterator characterIterator = this.fText;
        if (characterIterator != null) {
            result.fText = (CharacterIterator) characterIterator.clone();
        }
        List<LanguageBreakEngine> list = gAllBreakEngines;
        synchronized (list) {
            result.fBreakEngines = new ArrayList(list);
        }
        result.fLookAheadMatches = new LookAheadResults();
        result.getClass();
        result.fBreakCache = new BreakCache(this.fBreakCache);
        result.getClass();
        result.fDictionaryCache = new DictionaryCache(this.fDictionaryCache);
        return result;
    }

    public boolean equals(Object that) {
        CharacterIterator characterIterator;
        if (that == null) {
            return false;
        }
        if (this == that) {
            return true;
        }
        try {
            RuleBasedBreakIterator other = (RuleBasedBreakIterator) that;
            RBBIDataWrapper rBBIDataWrapper = this.fRData;
            RBBIDataWrapper rBBIDataWrapper2 = other.fRData;
            if (rBBIDataWrapper != rBBIDataWrapper2 && (rBBIDataWrapper == null || rBBIDataWrapper2 == null)) {
                return false;
            }
            if (rBBIDataWrapper != null && rBBIDataWrapper2 != null && !rBBIDataWrapper.fRuleSource.equals(other.fRData.fRuleSource)) {
                return false;
            }
            CharacterIterator characterIterator2 = this.fText;
            if (characterIterator2 == null && other.fText == null) {
                return true;
            }
            if (!(characterIterator2 == null || (characterIterator = other.fText) == null)) {
                if (characterIterator2.equals(characterIterator)) {
                    if (this.fPosition == other.fPosition) {
                        return true;
                    }
                    return false;
                }
            }
            return false;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public String toString() {
        RBBIDataWrapper rBBIDataWrapper = this.fRData;
        if (rBBIDataWrapper != null) {
            return rBBIDataWrapper.fRuleSource;
        }
        return "";
    }

    public int hashCode() {
        return this.fRData.fRuleSource.hashCode();
    }

    @Deprecated
    public void dump(PrintStream out) {
        if (out == null) {
            out = System.out;
        }
        this.fRData.dump(out);
    }

    public static void compileRules(String rules, OutputStream ruleBinary) throws IOException {
        RBBIRuleBuilder.compileRules(rules, ruleBinary);
    }

    public int first() {
        CharacterIterator characterIterator = this.fText;
        if (characterIterator == null) {
            return -1;
        }
        characterIterator.first();
        int start = this.fText.getIndex();
        if (!this.fBreakCache.seek(start)) {
            this.fBreakCache.populateNear(start);
        }
        this.fBreakCache.current();
        int i = this.fPosition;
        if (i == start) {
            return i;
        }
        throw new AssertionError();
    }

    public int last() {
        CharacterIterator characterIterator = this.fText;
        if (characterIterator == null) {
            return -1;
        }
        int endPos = characterIterator.getEndIndex();
        if (isBoundary(endPos)) {
            int i = this.fPosition;
            if (i == endPos || i == endPos) {
                return endPos;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public int next(int n) {
        int result = 0;
        if (n > 0) {
            while (n > 0 && result != -1) {
                result = next();
                n--;
            }
            return result;
        } else if (n >= 0) {
            return current();
        } else {
            while (n < 0 && result != -1) {
                result = previous();
                n++;
            }
            return result;
        }
    }

    public int next() {
        this.fBreakCache.next();
        if (this.fDone) {
            return -1;
        }
        return this.fPosition;
    }

    public int previous() {
        this.fBreakCache.previous();
        if (this.fDone) {
            return -1;
        }
        return this.fPosition;
    }

    public int following(int startPos) {
        if (startPos < this.fText.getBeginIndex()) {
            return first();
        }
        this.fBreakCache.following(CISetIndex32(this.fText, startPos));
        if (this.fDone) {
            return -1;
        }
        return this.fPosition;
    }

    public int preceding(int offset) {
        CharacterIterator characterIterator = this.fText;
        if (characterIterator == null || offset > characterIterator.getEndIndex()) {
            return last();
        }
        if (offset < this.fText.getBeginIndex()) {
            return first();
        }
        this.fBreakCache.preceding(offset);
        if (this.fDone) {
            return -1;
        }
        return this.fPosition;
    }

    protected static final void checkOffset(int offset, CharacterIterator text) {
        if (offset < text.getBeginIndex() || offset > text.getEndIndex()) {
            throw new IllegalArgumentException("offset out of bounds");
        }
    }

    public boolean isBoundary(int offset) {
        checkOffset(offset, this.fText);
        int adjustedOffset = CISetIndex32(this.fText, offset);
        boolean result = false;
        if (this.fBreakCache.seek(adjustedOffset) || this.fBreakCache.populateNear(adjustedOffset)) {
            result = this.fBreakCache.current() == offset;
        }
        if (!result) {
            next();
        }
        return result;
    }

    public int current() {
        if (this.fText != null) {
            return this.fPosition;
        }
        return -1;
    }

    public int getRuleStatus() {
        return this.fRData.fStatusTable[this.fRuleStatusIndex + this.fRData.fStatusTable[this.fRuleStatusIndex]];
    }

    public int getRuleStatusVec(int[] fillInArray) {
        int numStatusVals = this.fRData.fStatusTable[this.fRuleStatusIndex];
        if (fillInArray != null) {
            int numToCopy = Math.min(numStatusVals, fillInArray.length);
            for (int i = 0; i < numToCopy; i++) {
                fillInArray[i] = this.fRData.fStatusTable[this.fRuleStatusIndex + i + 1];
            }
        }
        return numStatusVals;
    }

    public CharacterIterator getText() {
        return this.fText;
    }

    public void setText(CharacterIterator newText) {
        if (newText != null) {
            this.fBreakCache.reset(newText.getBeginIndex(), 0);
        } else {
            this.fBreakCache.reset();
        }
        this.fDictionaryCache.reset();
        this.fText = newText;
        first();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0091, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.ibm.icu.text.LanguageBreakEngine getLanguageBreakEngine(int r5) {
        /*
            r4 = this;
            java.util.List<com.ibm.icu.text.LanguageBreakEngine> r0 = r4.fBreakEngines
            java.util.Iterator r0 = r0.iterator()
        L_0x0006:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x001a
            java.lang.Object r1 = r0.next()
            com.ibm.icu.text.LanguageBreakEngine r1 = (com.ibm.icu.text.LanguageBreakEngine) r1
            boolean r2 = r1.handles(r5)
            if (r2 == 0) goto L_0x0019
            return r1
        L_0x0019:
            goto L_0x0006
        L_0x001a:
            java.util.List<com.ibm.icu.text.LanguageBreakEngine> r0 = gAllBreakEngines
            monitor-enter(r0)
            java.util.Iterator r1 = r0.iterator()     // Catch:{ all -> 0x0092 }
        L_0x0021:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x0092 }
            if (r2 == 0) goto L_0x003b
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x0092 }
            com.ibm.icu.text.LanguageBreakEngine r2 = (com.ibm.icu.text.LanguageBreakEngine) r2     // Catch:{ all -> 0x0092 }
            boolean r3 = r2.handles(r5)     // Catch:{ all -> 0x0092 }
            if (r3 == 0) goto L_0x003a
            java.util.List<com.ibm.icu.text.LanguageBreakEngine> r1 = r4.fBreakEngines     // Catch:{ all -> 0x0092 }
            r1.add(r2)     // Catch:{ all -> 0x0092 }
            monitor-exit(r0)     // Catch:{ all -> 0x0092 }
            return r2
        L_0x003a:
            goto L_0x0021
        L_0x003b:
            r1 = 4106(0x100a, float:5.754E-42)
            int r1 = com.ibm.icu.lang.UCharacter.getIntPropertyValue(r5, r1)     // Catch:{ all -> 0x0092 }
            r2 = 22
            if (r1 == r2) goto L_0x0049
            r2 = 20
            if (r1 != r2) goto L_0x004b
        L_0x0049:
            r1 = 17
        L_0x004b:
            switch(r1) {
                case 17: goto L_0x0070;
                case 18: goto L_0x0069;
                case 23: goto L_0x0063;
                case 24: goto L_0x005d;
                case 28: goto L_0x0057;
                case 38: goto L_0x0051;
                default: goto L_0x004e;
            }
        L_0x004e:
            com.ibm.icu.text.UnhandledBreakEngine r2 = gUnhandledBreakEngine     // Catch:{ IOException -> 0x0077 }
            goto L_0x0079
        L_0x0051:
            com.ibm.icu.text.ThaiBreakEngine r2 = new com.ibm.icu.text.ThaiBreakEngine     // Catch:{ IOException -> 0x0077 }
            r2.<init>()     // Catch:{ IOException -> 0x0077 }
            goto L_0x007d
        L_0x0057:
            com.ibm.icu.text.BurmeseBreakEngine r2 = new com.ibm.icu.text.BurmeseBreakEngine     // Catch:{ IOException -> 0x0077 }
            r2.<init>()     // Catch:{ IOException -> 0x0077 }
            goto L_0x007d
        L_0x005d:
            com.ibm.icu.text.LaoBreakEngine r2 = new com.ibm.icu.text.LaoBreakEngine     // Catch:{ IOException -> 0x0077 }
            r2.<init>()     // Catch:{ IOException -> 0x0077 }
            goto L_0x007d
        L_0x0063:
            com.ibm.icu.text.KhmerBreakEngine r2 = new com.ibm.icu.text.KhmerBreakEngine     // Catch:{ IOException -> 0x0077 }
            r2.<init>()     // Catch:{ IOException -> 0x0077 }
            goto L_0x007d
        L_0x0069:
            com.ibm.icu.text.CjkBreakEngine r2 = new com.ibm.icu.text.CjkBreakEngine     // Catch:{ IOException -> 0x0077 }
            r3 = 1
            r2.<init>(r3)     // Catch:{ IOException -> 0x0077 }
            goto L_0x007d
        L_0x0070:
            com.ibm.icu.text.CjkBreakEngine r2 = new com.ibm.icu.text.CjkBreakEngine     // Catch:{ IOException -> 0x0077 }
            r3 = 0
            r2.<init>(r3)     // Catch:{ IOException -> 0x0077 }
            goto L_0x007d
        L_0x0077:
            r2 = move-exception
            goto L_0x007e
        L_0x0079:
            r2.handleChar(r5)     // Catch:{ IOException -> 0x0077 }
        L_0x007d:
            goto L_0x0080
        L_0x007e:
            r3 = 0
            r2 = r3
        L_0x0080:
            if (r2 == 0) goto L_0x0090
            com.ibm.icu.text.UnhandledBreakEngine r3 = gUnhandledBreakEngine     // Catch:{ all -> 0x0092 }
            if (r2 == r3) goto L_0x0090
            java.util.List<com.ibm.icu.text.LanguageBreakEngine> r3 = gAllBreakEngines     // Catch:{ all -> 0x0092 }
            r3.add(r2)     // Catch:{ all -> 0x0092 }
            java.util.List<com.ibm.icu.text.LanguageBreakEngine> r3 = r4.fBreakEngines     // Catch:{ all -> 0x0092 }
            r3.add(r2)     // Catch:{ all -> 0x0092 }
        L_0x0090:
            monitor-exit(r0)     // Catch:{ all -> 0x0092 }
            return r2
        L_0x0092:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0092 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.RuleBasedBreakIterator.getLanguageBreakEngine(int):com.ibm.icu.text.LanguageBreakEngine");
    }

    private static class LookAheadResults {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        int[] fKeys = new int[8];
        int[] fPositions = new int[8];
        int fUsedSlotLimit = 0;

        static {
            Class<RuleBasedBreakIterator> cls = RuleBasedBreakIterator.class;
        }

        LookAheadResults() {
        }

        /* access modifiers changed from: package-private */
        public int getPosition(int key) {
            for (int i = 0; i < this.fUsedSlotLimit; i++) {
                if (this.fKeys[i] == key) {
                    return this.fPositions[i];
                }
            }
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        public void setPosition(int key, int position) {
            int i = 0;
            while (true) {
                int i2 = this.fUsedSlotLimit;
                if (i < i2) {
                    if (this.fKeys[i] == key) {
                        this.fPositions[i] = position;
                        return;
                    }
                    i++;
                } else if (i >= 8) {
                    throw new AssertionError();
                } else {
                    this.fKeys[i] = key;
                    this.fPositions[i] = position;
                    if (i2 == i) {
                        this.fUsedSlotLimit = i + 1;
                        return;
                    }
                    throw new AssertionError();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void reset() {
            this.fUsedSlotLimit = 0;
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Incorrect type for immutable var: ssa=short, code=int, for r13v2, types: [short, int] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int handleNext() {
        /*
            r19 = this;
            r0 = r19
            boolean r1 = TRACE
            if (r1 == 0) goto L_0x000d
            java.io.PrintStream r2 = java.lang.System.out
            java.lang.String r3 = "Handle Next   pos      char  state category"
            r2.println(r3)
        L_0x000d:
            r2 = 0
            r0.fRuleStatusIndex = r2
            r0.fDictionaryCharCount = r2
            java.text.CharacterIterator r3 = r0.fText
            com.ibm.icu.impl.RBBIDataWrapper r4 = r0.fRData
            com.ibm.icu.impl.Trie2 r4 = r4.fTrie
            com.ibm.icu.impl.RBBIDataWrapper r5 = r0.fRData
            com.ibm.icu.impl.RBBIDataWrapper$RBBIStateTable r5 = r5.fFTable
            short[] r5 = r5.fTable
            int r6 = r0.fPosition
            r3.setIndex(r6)
            r7 = r6
            char r8 = r3.current()
            r9 = 2147483647(0x7fffffff, float:NaN)
            r10 = 55296(0xd800, float:7.7486E-41)
            r11 = -1
            r12 = 1
            if (r8 < r10) goto L_0x003b
            int r8 = com.ibm.icu.impl.CharacterIteration.nextTrail32(r3, r8)
            if (r8 != r9) goto L_0x003b
            r0.fDone = r12
            return r11
        L_0x003b:
            r13 = 1
            com.ibm.icu.impl.RBBIDataWrapper r14 = r0.fRData
            int r14 = r14.getRowIndex(r13)
            r15 = 3
            com.ibm.icu.impl.RBBIDataWrapper r2 = r0.fRData
            com.ibm.icu.impl.RBBIDataWrapper$RBBIStateTable r2 = r2.fFTable
            int r2 = r2.fFlags
            r16 = 1
            r17 = r2 & 2
            r9 = 5
            java.lang.String r11 = "            "
            if (r17 == 0) goto L_0x00a0
            r15 = 2
            r16 = 0
            if (r1 == 0) goto L_0x00a0
            java.io.PrintStream r1 = java.lang.System.out
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.StringBuilder r10 = r10.append(r11)
            int r12 = r3.getIndex()
            java.lang.String r12 = com.ibm.icu.impl.RBBIDataWrapper.intToString(r12, r9)
            java.lang.StringBuilder r10 = r10.append(r12)
            java.lang.String r10 = r10.toString()
            r1.print(r10)
            java.io.PrintStream r1 = java.lang.System.out
            r10 = 10
            java.lang.String r12 = com.ibm.icu.impl.RBBIDataWrapper.intToHexString(r8, r10)
            r1.print(r12)
            java.io.PrintStream r1 = java.lang.System.out
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r12 = 7
            java.lang.String r9 = com.ibm.icu.impl.RBBIDataWrapper.intToString(r13, r12)
            java.lang.StringBuilder r9 = r10.append(r9)
            r10 = 6
            java.lang.String r12 = com.ibm.icu.impl.RBBIDataWrapper.intToString(r15, r10)
            java.lang.StringBuilder r9 = r9.append(r12)
            java.lang.String r9 = r9.toString()
            r1.println(r9)
        L_0x00a0:
            com.ibm.icu.text.RuleBasedBreakIterator$LookAheadResults r1 = r0.fLookAheadMatches
            r1.reset()
            r1 = r16
        L_0x00a7:
            if (r13 == 0) goto L_0x01a2
            r9 = 2147483647(0x7fffffff, float:NaN)
            if (r8 != r9) goto L_0x00c0
            r10 = 2
            if (r1 != r10) goto L_0x00b5
            r16 = r1
            goto L_0x01a4
        L_0x00b5:
            r1 = 2
            r10 = 1
            r16 = r1
            r15 = r10
            r1 = 6
            r9 = 55296(0xd800, float:7.7486E-41)
            goto L_0x0146
        L_0x00c0:
            r10 = 1
            if (r1 != r10) goto L_0x013d
            int r12 = r4.get(r8)
            short r12 = (short) r12
            r15 = r12 & 16384(0x4000, float:2.2959E-41)
            if (r15 == 0) goto L_0x00d4
            int r15 = r0.fDictionaryCharCount
            int r15 = r15 + r10
            r0.fDictionaryCharCount = r15
            r15 = r12 & -16385(0xffffffffffffbfff, float:NaN)
            short r12 = (short) r15
        L_0x00d4:
            boolean r15 = TRACE
            if (r15 == 0) goto L_0x0125
            java.io.PrintStream r15 = java.lang.System.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.StringBuilder r9 = r9.append(r11)
            int r10 = r3.getIndex()
            r16 = r1
            r1 = 5
            java.lang.String r10 = com.ibm.icu.impl.RBBIDataWrapper.intToString(r10, r1)
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.String r9 = r9.toString()
            r15.print(r9)
            java.io.PrintStream r9 = java.lang.System.out
            r10 = 10
            java.lang.String r15 = com.ibm.icu.impl.RBBIDataWrapper.intToHexString(r8, r10)
            r9.print(r15)
            java.io.PrintStream r9 = java.lang.System.out
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            r1 = 7
            java.lang.String r10 = com.ibm.icu.impl.RBBIDataWrapper.intToString(r13, r1)
            java.lang.StringBuilder r10 = r15.append(r10)
            r15 = 6
            java.lang.String r1 = com.ibm.icu.impl.RBBIDataWrapper.intToString(r12, r15)
            java.lang.StringBuilder r1 = r10.append(r1)
            java.lang.String r1 = r1.toString()
            r9.println(r1)
            goto L_0x0128
        L_0x0125:
            r16 = r1
            r15 = 6
        L_0x0128:
            char r1 = r3.next()
            r9 = 55296(0xd800, float:7.7486E-41)
            if (r1 < r9) goto L_0x0139
            int r1 = com.ibm.icu.impl.CharacterIteration.nextTrail32(r3, r1)
            r8 = r1
            r1 = r15
            r15 = r12
            goto L_0x0146
        L_0x0139:
            r8 = r1
            r1 = r15
            r15 = r12
            goto L_0x0146
        L_0x013d:
            r16 = r1
            r1 = 6
            r9 = 55296(0xd800, float:7.7486E-41)
            r10 = 1
            r16 = r10
        L_0x0146:
            int r10 = r14 + 4
            int r10 = r10 + r15
            short r13 = r5[r10]
            com.ibm.icu.impl.RBBIDataWrapper r10 = r0.fRData
            int r14 = r10.getRowIndex(r13)
            int r10 = r14 + 0
            short r10 = r5[r10]
            r12 = 1114111(0x10ffff, float:1.561202E-39)
            r1 = 65536(0x10000, float:9.18355E-41)
            r9 = -1
            if (r10 != r9) goto L_0x016d
            int r7 = r3.getIndex()
            if (r8 < r1) goto L_0x0167
            if (r8 > r12) goto L_0x0167
            int r7 = r7 + -1
        L_0x0167:
            int r10 = r14 + 2
            short r10 = r5[r10]
            r0.fRuleStatusIndex = r10
        L_0x016d:
            int r10 = r14 + 0
            short r10 = r5[r10]
            if (r10 <= 0) goto L_0x0184
            com.ibm.icu.text.RuleBasedBreakIterator$LookAheadResults r9 = r0.fLookAheadMatches
            int r9 = r9.getPosition(r10)
            if (r9 < 0) goto L_0x0184
            int r1 = r14 + 2
            short r1 = r5[r1]
            r0.fRuleStatusIndex = r1
            r0.fPosition = r9
            return r9
        L_0x0184:
            int r9 = r14 + 1
            short r9 = r5[r9]
            if (r9 == 0) goto L_0x019e
            int r18 = r3.getIndex()
            if (r8 < r1) goto L_0x0197
            if (r8 > r12) goto L_0x0197
            int r18 = r18 + -1
            r1 = r18
            goto L_0x0199
        L_0x0197:
            r1 = r18
        L_0x0199:
            com.ibm.icu.text.RuleBasedBreakIterator$LookAheadResults r12 = r0.fLookAheadMatches
            r12.setPosition(r9, r1)
        L_0x019e:
            r1 = r16
            goto L_0x00a7
        L_0x01a2:
            r16 = r1
        L_0x01a4:
            if (r7 != r6) goto L_0x01be
            boolean r1 = TRACE
            if (r1 == 0) goto L_0x01b1
            java.io.PrintStream r1 = java.lang.System.out
            java.lang.String r9 = "Iterator did not move. Advancing by 1."
            r1.println(r9)
        L_0x01b1:
            r3.setIndex(r6)
            com.ibm.icu.impl.CharacterIteration.next32(r3)
            int r7 = r3.getIndex()
            r1 = 0
            r0.fRuleStatusIndex = r1
        L_0x01be:
            r0.fPosition = r7
            boolean r1 = TRACE
            if (r1 == 0) goto L_0x01dc
            java.io.PrintStream r1 = java.lang.System.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "result = "
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.StringBuilder r9 = r9.append(r7)
            java.lang.String r9 = r9.toString()
            r1.println(r9)
        L_0x01dc:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.RuleBasedBreakIterator.handleNext():int");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Incorrect type for immutable var: ssa=short, code=int, for r6v3, types: [short, int] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int handleSafePrevious(int r13) {
        /*
            r12 = this;
            r0 = 0
            r1 = 0
            java.text.CharacterIterator r2 = r12.fText
            com.ibm.icu.impl.RBBIDataWrapper r3 = r12.fRData
            com.ibm.icu.impl.Trie2 r3 = r3.fTrie
            com.ibm.icu.impl.RBBIDataWrapper r4 = r12.fRData
            com.ibm.icu.impl.RBBIDataWrapper$RBBIStateTable r4 = r4.fRTable
            short[] r4 = r4.fTable
            CISetIndex32(r2, r13)
            boolean r5 = TRACE
            if (r5 == 0) goto L_0x001c
            java.io.PrintStream r5 = java.lang.System.out
            java.lang.String r6 = "Handle Previous   pos   char  state category"
            r5.print(r6)
        L_0x001c:
            int r5 = r2.getIndex()
            int r6 = r2.getBeginIndex()
            if (r5 != r6) goto L_0x0028
            r5 = -1
            return r5
        L_0x0028:
            int r5 = com.ibm.icu.impl.CharacterIteration.previous32(r2)
            r6 = 1
            com.ibm.icu.impl.RBBIDataWrapper r7 = r12.fRData
            int r7 = r7.getRowIndex(r6)
        L_0x0033:
            r8 = 2147483647(0x7fffffff, float:NaN)
            if (r5 == r8) goto L_0x00b1
            int r8 = r3.get(r5)
            short r0 = (short) r8
            r8 = r0 & -16385(0xffffffffffffbfff, float:NaN)
            short r0 = (short) r8
            boolean r8 = TRACE
            if (r8 == 0) goto L_0x0090
            java.io.PrintStream r8 = java.lang.System.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "            "
            java.lang.StringBuilder r9 = r9.append(r10)
            int r10 = r2.getIndex()
            r11 = 5
            java.lang.String r10 = com.ibm.icu.impl.RBBIDataWrapper.intToString(r10, r11)
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.String r9 = r9.toString()
            r8.print(r9)
            java.io.PrintStream r8 = java.lang.System.out
            r9 = 10
            java.lang.String r9 = com.ibm.icu.impl.RBBIDataWrapper.intToHexString(r5, r9)
            r8.print(r9)
            java.io.PrintStream r8 = java.lang.System.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r10 = 7
            java.lang.String r10 = com.ibm.icu.impl.RBBIDataWrapper.intToString(r6, r10)
            java.lang.StringBuilder r9 = r9.append(r10)
            r10 = 6
            java.lang.String r10 = com.ibm.icu.impl.RBBIDataWrapper.intToString(r0, r10)
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.String r9 = r9.toString()
            r8.println(r9)
        L_0x0090:
            com.ibm.icu.impl.RBBIDataWrapper r8 = r12.fRData
            com.ibm.icu.impl.RBBIDataWrapper$RBBIDataHeader r8 = r8.fHeader
            int r8 = r8.fCatCount
            if (r0 >= r8) goto L_0x00ab
            int r8 = r7 + 4
            int r8 = r8 + r0
            short r6 = r4[r8]
            com.ibm.icu.impl.RBBIDataWrapper r8 = r12.fRData
            int r7 = r8.getRowIndex(r6)
            if (r6 != 0) goto L_0x00a6
            goto L_0x00b1
        L_0x00a6:
            int r5 = com.ibm.icu.impl.CharacterIteration.previous32(r2)
            goto L_0x0033
        L_0x00ab:
            java.lang.AssertionError r8 = new java.lang.AssertionError
            r8.<init>()
            throw r8
        L_0x00b1:
            int r1 = r2.getIndex()
            boolean r8 = TRACE
            if (r8 == 0) goto L_0x00d1
            java.io.PrintStream r8 = java.lang.System.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "result = "
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.StringBuilder r9 = r9.append(r1)
            java.lang.String r9 = r9.toString()
            r8.println(r9)
        L_0x00d1:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.RuleBasedBreakIterator.handleSafePrevious(int):int");
    }

    private static int CISetIndex32(CharacterIterator ci, int index) {
        if (index <= ci.getBeginIndex()) {
            ci.first();
        } else if (index >= ci.getEndIndex()) {
            ci.setIndex(ci.getEndIndex());
        } else if (Character.isLowSurrogate(ci.setIndex(index)) && !Character.isHighSurrogate(ci.previous())) {
            ci.next();
        }
        return ci.getIndex();
    }

    class DictionaryCache {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        int fBoundary;
        DictionaryBreakEngine.DequeI fBreaks;
        int fFirstRuleStatusIndex;
        int fLimit;
        int fOtherRuleStatusIndex;
        int fPositionInCache;
        int fStart;
        int fStatusIndex;

        static {
            Class<RuleBasedBreakIterator> cls = RuleBasedBreakIterator.class;
        }

        /* access modifiers changed from: package-private */
        public void reset() {
            this.fPositionInCache = -1;
            this.fStart = 0;
            this.fLimit = 0;
            this.fFirstRuleStatusIndex = 0;
            this.fOtherRuleStatusIndex = 0;
            this.fBreaks.removeAllElements();
        }

        /* access modifiers changed from: package-private */
        public boolean following(int fromPos) {
            if (fromPos >= this.fLimit || fromPos < this.fStart) {
                this.fPositionInCache = -1;
                return false;
            }
            int i = this.fPositionInCache;
            if (i < 0 || i >= this.fBreaks.size() || this.fBreaks.elementAt(this.fPositionInCache) != fromPos) {
                this.fPositionInCache = 0;
                while (this.fPositionInCache < this.fBreaks.size()) {
                    int r = this.fBreaks.elementAt(this.fPositionInCache);
                    if (r > fromPos) {
                        this.fBoundary = r;
                        this.fStatusIndex = this.fOtherRuleStatusIndex;
                        return true;
                    }
                    this.fPositionInCache++;
                }
                throw new AssertionError();
            }
            int i2 = this.fPositionInCache + 1;
            this.fPositionInCache = i2;
            if (i2 >= this.fBreaks.size()) {
                this.fPositionInCache = -1;
                return false;
            }
            int r2 = this.fBreaks.elementAt(this.fPositionInCache);
            if (r2 > fromPos) {
                this.fBoundary = r2;
                this.fStatusIndex = this.fOtherRuleStatusIndex;
                return true;
            }
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        public boolean preceding(int fromPos) {
            int i;
            if (fromPos <= this.fStart || fromPos > (i = this.fLimit)) {
                this.fPositionInCache = -1;
                return false;
            }
            if (fromPos == i) {
                int size = this.fBreaks.size() - 1;
                this.fPositionInCache = size;
                if (size >= 0 && this.fBreaks.elementAt(size) != fromPos) {
                    throw new AssertionError();
                }
            }
            int i2 = this.fPositionInCache;
            if (i2 > 0 && i2 < this.fBreaks.size() && this.fBreaks.elementAt(this.fPositionInCache) == fromPos) {
                int i3 = this.fPositionInCache - 1;
                this.fPositionInCache = i3;
                int r = this.fBreaks.elementAt(i3);
                if (r < fromPos) {
                    this.fBoundary = r;
                    this.fStatusIndex = r == this.fStart ? this.fFirstRuleStatusIndex : this.fOtherRuleStatusIndex;
                    return true;
                }
                throw new AssertionError();
            } else if (this.fPositionInCache == 0) {
                this.fPositionInCache = -1;
                return false;
            } else {
                this.fPositionInCache = this.fBreaks.size() - 1;
                while (true) {
                    int i4 = this.fPositionInCache;
                    if (i4 >= 0) {
                        int r2 = this.fBreaks.elementAt(i4);
                        if (r2 < fromPos) {
                            this.fBoundary = r2;
                            this.fStatusIndex = r2 == this.fStart ? this.fFirstRuleStatusIndex : this.fOtherRuleStatusIndex;
                            return true;
                        }
                        this.fPositionInCache--;
                    } else {
                        throw new AssertionError();
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void populateDictionary(int startPos, int endPos, int firstRuleStatus, int otherRuleStatus) {
            if (endPos - startPos > 1) {
                reset();
                this.fFirstRuleStatusIndex = firstRuleStatus;
                this.fOtherRuleStatusIndex = otherRuleStatus;
                int rangeStart = startPos;
                int rangeEnd = endPos;
                int foundBreakCount = 0;
                RuleBasedBreakIterator.this.fText.setIndex(rangeStart);
                int c = CharacterIteration.current32(RuleBasedBreakIterator.this.fText);
                int category = (short) RuleBasedBreakIterator.this.fRData.fTrie.get(c);
                while (true) {
                    int index = RuleBasedBreakIterator.this.fText.getIndex();
                    int current = index;
                    if (index < rangeEnd && (category & 16384) == 0) {
                        c = CharacterIteration.next32(RuleBasedBreakIterator.this.fText);
                        category = (short) RuleBasedBreakIterator.this.fRData.fTrie.get(c);
                    } else if (current >= rangeEnd) {
                        break;
                    } else {
                        LanguageBreakEngine lbe = RuleBasedBreakIterator.this.getLanguageBreakEngine(c);
                        if (lbe != null) {
                            foundBreakCount += lbe.findBreaks(RuleBasedBreakIterator.this.fText, rangeStart, rangeEnd, this.fBreaks);
                        }
                        c = CharacterIteration.current32(RuleBasedBreakIterator.this.fText);
                        category = (short) RuleBasedBreakIterator.this.fRData.fTrie.get(c);
                    }
                }
                if (foundBreakCount <= 0) {
                    return;
                }
                if (foundBreakCount == this.fBreaks.size()) {
                    if (startPos < this.fBreaks.elementAt(0)) {
                        this.fBreaks.offer(startPos);
                    }
                    if (endPos > this.fBreaks.peek()) {
                        this.fBreaks.push(endPos);
                    }
                    this.fPositionInCache = 0;
                    this.fStart = this.fBreaks.elementAt(0);
                    this.fLimit = this.fBreaks.peek();
                    return;
                }
                throw new AssertionError();
            }
        }

        DictionaryCache() {
            this.fPositionInCache = -1;
            this.fBreaks = new DictionaryBreakEngine.DequeI();
        }

        DictionaryCache(DictionaryCache src) {
            try {
                this.fBreaks = (DictionaryBreakEngine.DequeI) src.fBreaks.clone();
                this.fPositionInCache = src.fPositionInCache;
                this.fStart = src.fStart;
                this.fLimit = src.fLimit;
                this.fFirstRuleStatusIndex = src.fFirstRuleStatusIndex;
                this.fOtherRuleStatusIndex = src.fOtherRuleStatusIndex;
                this.fBoundary = src.fBoundary;
                this.fStatusIndex = src.fStatusIndex;
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    class BreakCache {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        static final int CACHE_SIZE = 128;
        static final boolean RetainCachePosition = false;
        static final boolean UpdateCachePosition = true;
        int[] fBoundaries = new int[128];
        int fBufIdx;
        int fEndBufIdx;
        DictionaryBreakEngine.DequeI fSideBuffer = new DictionaryBreakEngine.DequeI();
        int fStartBufIdx;
        short[] fStatuses = new short[128];
        int fTextIdx;

        static {
            Class<RuleBasedBreakIterator> cls = RuleBasedBreakIterator.class;
        }

        BreakCache() {
            reset();
        }

        /* access modifiers changed from: package-private */
        public void reset(int pos, int ruleStatus) {
            this.fStartBufIdx = 0;
            this.fEndBufIdx = 0;
            this.fTextIdx = pos;
            this.fBufIdx = 0;
            this.fBoundaries[0] = pos;
            this.fStatuses[0] = (short) ruleStatus;
        }

        /* access modifiers changed from: package-private */
        public void reset() {
            reset(0, 0);
        }

        /* access modifiers changed from: package-private */
        public void next() {
            int i = this.fBufIdx;
            if (i == this.fEndBufIdx) {
                boolean unused = RuleBasedBreakIterator.this.fDone = populateFollowing() ^ UpdateCachePosition;
                int unused2 = RuleBasedBreakIterator.this.fPosition = this.fTextIdx;
                int unused3 = RuleBasedBreakIterator.this.fRuleStatusIndex = this.fStatuses[this.fBufIdx];
                return;
            }
            int modChunkSize = modChunkSize(i + 1);
            this.fBufIdx = modChunkSize;
            this.fTextIdx = RuleBasedBreakIterator.this.fPosition = this.fBoundaries[modChunkSize];
            int unused4 = RuleBasedBreakIterator.this.fRuleStatusIndex = this.fStatuses[this.fBufIdx];
        }

        /* access modifiers changed from: package-private */
        public void previous() {
            int initialBufIdx = this.fBufIdx;
            int i = this.fBufIdx;
            int i2 = this.fStartBufIdx;
            boolean z = UpdateCachePosition;
            if (i == i2) {
                populatePreceding();
            } else {
                int modChunkSize = modChunkSize(i - 1);
                this.fBufIdx = modChunkSize;
                this.fTextIdx = this.fBoundaries[modChunkSize];
            }
            RuleBasedBreakIterator ruleBasedBreakIterator = RuleBasedBreakIterator.this;
            if (this.fBufIdx != initialBufIdx) {
                z = false;
            }
            boolean unused = ruleBasedBreakIterator.fDone = z;
            int unused2 = RuleBasedBreakIterator.this.fPosition = this.fTextIdx;
            int unused3 = RuleBasedBreakIterator.this.fRuleStatusIndex = this.fStatuses[this.fBufIdx];
        }

        /* access modifiers changed from: package-private */
        public void following(int startPos) {
            if (startPos == this.fTextIdx || seek(startPos) || populateNear(startPos)) {
                boolean unused = RuleBasedBreakIterator.this.fDone = false;
                next();
            }
        }

        /* access modifiers changed from: package-private */
        public void preceding(int startPos) {
            if (startPos == this.fTextIdx || seek(startPos) || populateNear(startPos)) {
                int i = this.fTextIdx;
                if (startPos == i) {
                    previous();
                } else if (startPos > i) {
                    current();
                } else {
                    throw new AssertionError();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public int current() {
            int unused = RuleBasedBreakIterator.this.fPosition = this.fTextIdx;
            int unused2 = RuleBasedBreakIterator.this.fRuleStatusIndex = this.fStatuses[this.fBufIdx];
            boolean unused3 = RuleBasedBreakIterator.this.fDone = false;
            return this.fTextIdx;
        }

        /* access modifiers changed from: package-private */
        public boolean populateNear(int position) {
            int[] iArr;
            int i;
            int i2;
            int[] iArr2 = this.fBoundaries;
            int i3 = this.fStartBufIdx;
            if (position < iArr2[i3] || position > iArr2[this.fEndBufIdx]) {
                if (position < iArr2[i3] - 15 || position > iArr2[this.fEndBufIdx] + 15) {
                    int aBoundary = RuleBasedBreakIterator.this.fText.getBeginIndex();
                    int ruleStatusIndex = 0;
                    if (position > aBoundary + 20) {
                        int backupPos = RuleBasedBreakIterator.this.handleSafePrevious(position);
                        if (backupPos > aBoundary) {
                            int unused = RuleBasedBreakIterator.this.fPosition = backupPos;
                            aBoundary = RuleBasedBreakIterator.this.handleNext();
                            if (aBoundary == backupPos + 1 || (aBoundary == backupPos + 2 && Character.isHighSurrogate(RuleBasedBreakIterator.this.fText.setIndex(backupPos)) && Character.isLowSurrogate(RuleBasedBreakIterator.this.fText.next()))) {
                                aBoundary = RuleBasedBreakIterator.this.handleNext();
                            }
                        }
                        ruleStatusIndex = RuleBasedBreakIterator.this.fRuleStatusIndex;
                    }
                    reset(aBoundary, ruleStatusIndex);
                }
                int[] iArr3 = this.fBoundaries;
                if (iArr3[this.fEndBufIdx] < position) {
                    do {
                        int[] iArr4 = this.fBoundaries;
                        int i4 = this.fEndBufIdx;
                        if (iArr4[i4] >= position) {
                            this.fBufIdx = i4;
                            this.fTextIdx = iArr4[i4];
                            while (this.fTextIdx > position) {
                                previous();
                            }
                            return UpdateCachePosition;
                        }
                    } while (populateFollowing());
                    throw new AssertionError();
                } else if (iArr3[this.fStartBufIdx] > position) {
                    while (true) {
                        iArr = this.fBoundaries;
                        i = this.fStartBufIdx;
                        if (iArr[i] <= position) {
                            break;
                        }
                        populatePreceding();
                    }
                    this.fBufIdx = i;
                    this.fTextIdx = iArr[i];
                    while (true) {
                        i2 = this.fTextIdx;
                        if (i2 >= position) {
                            break;
                        }
                        next();
                    }
                    if (i2 > position) {
                        previous();
                    }
                    return UpdateCachePosition;
                } else if (this.fTextIdx == position) {
                    return UpdateCachePosition;
                } else {
                    throw new AssertionError();
                }
            } else {
                throw new AssertionError();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean populateFollowing() {
            int pos;
            int[] iArr = this.fBoundaries;
            int i = this.fEndBufIdx;
            int fromPosition = iArr[i];
            short fromRuleStatusIdx = this.fStatuses[i];
            if (RuleBasedBreakIterator.this.fDictionaryCache.following(fromPosition)) {
                addFollowing(RuleBasedBreakIterator.this.fDictionaryCache.fBoundary, RuleBasedBreakIterator.this.fDictionaryCache.fStatusIndex, UpdateCachePosition);
                return UpdateCachePosition;
            }
            int unused = RuleBasedBreakIterator.this.fPosition = fromPosition;
            int pos2 = RuleBasedBreakIterator.this.handleNext();
            if (pos2 == -1) {
                return false;
            }
            int ruleStatusIdx = RuleBasedBreakIterator.this.fRuleStatusIndex;
            if (RuleBasedBreakIterator.this.fDictionaryCharCount > 0) {
                RuleBasedBreakIterator.this.fDictionaryCache.populateDictionary(fromPosition, pos2, fromRuleStatusIdx, ruleStatusIdx);
                if (RuleBasedBreakIterator.this.fDictionaryCache.following(fromPosition)) {
                    addFollowing(RuleBasedBreakIterator.this.fDictionaryCache.fBoundary, RuleBasedBreakIterator.this.fDictionaryCache.fStatusIndex, UpdateCachePosition);
                    return UpdateCachePosition;
                }
            }
            addFollowing(pos2, ruleStatusIdx, UpdateCachePosition);
            for (int count = 0; count < 6 && (pos = RuleBasedBreakIterator.this.handleNext()) != -1 && RuleBasedBreakIterator.this.fDictionaryCharCount <= 0; count++) {
                addFollowing(pos, RuleBasedBreakIterator.this.fRuleStatusIndex, false);
            }
            return UpdateCachePosition;
        }

        /* access modifiers changed from: package-private */
        public boolean populatePreceding() {
            int positionStatusIdx;
            int position;
            int textBegin = RuleBasedBreakIterator.this.fText.getBeginIndex();
            int fromPosition = this.fBoundaries[this.fStartBufIdx];
            if (fromPosition == textBegin) {
                return false;
            }
            int i = textBegin;
            if (RuleBasedBreakIterator.this.fDictionaryCache.preceding(fromPosition)) {
                addPreceding(RuleBasedBreakIterator.this.fDictionaryCache.fBoundary, RuleBasedBreakIterator.this.fDictionaryCache.fStatusIndex, UpdateCachePosition);
                return UpdateCachePosition;
            }
            int backupPosition = fromPosition;
            do {
                int backupPosition2 = backupPosition - 30;
                if (backupPosition2 <= textBegin) {
                    backupPosition = textBegin;
                } else {
                    backupPosition = RuleBasedBreakIterator.this.handleSafePrevious(backupPosition2);
                }
                if (backupPosition == -1 || backupPosition == textBegin) {
                    position = textBegin;
                    positionStatusIdx = 0;
                    continue;
                } else {
                    int unused = RuleBasedBreakIterator.this.fPosition = backupPosition;
                    position = RuleBasedBreakIterator.this.handleNext();
                    if (position == backupPosition + 1 || (position == backupPosition + 2 && Character.isHighSurrogate(RuleBasedBreakIterator.this.fText.setIndex(backupPosition)) && Character.isLowSurrogate(RuleBasedBreakIterator.this.fText.next()))) {
                        position = RuleBasedBreakIterator.this.handleNext();
                    }
                    positionStatusIdx = RuleBasedBreakIterator.this.fRuleStatusIndex;
                    continue;
                }
            } while (position >= fromPosition);
            this.fSideBuffer.removeAllElements();
            this.fSideBuffer.push(position);
            this.fSideBuffer.push(positionStatusIdx);
            do {
                int prevPosition = RuleBasedBreakIterator.this.fPosition = position;
                int prevStatusIdx = positionStatusIdx;
                int position2 = RuleBasedBreakIterator.this.handleNext();
                int positionStatusIdx2 = RuleBasedBreakIterator.this.fRuleStatusIndex;
                if (position2 == -1) {
                    break;
                }
                boolean segmentHandledByDictionary = false;
                if (RuleBasedBreakIterator.this.fDictionaryCharCount != 0) {
                    int dictSegEndPosition = position2;
                    RuleBasedBreakIterator.this.fDictionaryCache.populateDictionary(prevPosition, dictSegEndPosition, prevStatusIdx, positionStatusIdx2);
                    while (RuleBasedBreakIterator.this.fDictionaryCache.following(prevPosition)) {
                        position2 = RuleBasedBreakIterator.this.fDictionaryCache.fBoundary;
                        positionStatusIdx2 = RuleBasedBreakIterator.this.fDictionaryCache.fStatusIndex;
                        segmentHandledByDictionary = UpdateCachePosition;
                        if (position2 <= prevPosition) {
                            throw new AssertionError();
                        } else if (position2 >= fromPosition) {
                            break;
                        } else if (position2 <= dictSegEndPosition) {
                            this.fSideBuffer.push(position2);
                            this.fSideBuffer.push(positionStatusIdx2);
                            prevPosition = position2;
                        } else {
                            throw new AssertionError();
                        }
                    }
                    if (position2 != dictSegEndPosition && position2 < fromPosition) {
                        throw new AssertionError();
                    }
                }
                if (!segmentHandledByDictionary && position < fromPosition) {
                    this.fSideBuffer.push(position);
                    this.fSideBuffer.push(positionStatusIdx);
                    continue;
                }
            } while (position < fromPosition);
            boolean success = false;
            if (!this.fSideBuffer.isEmpty()) {
                addPreceding(this.fSideBuffer.pop(), this.fSideBuffer.pop(), UpdateCachePosition);
                success = UpdateCachePosition;
            }
            while (!this.fSideBuffer.isEmpty()) {
                if (!addPreceding(this.fSideBuffer.pop(), this.fSideBuffer.pop(), false)) {
                    break;
                }
            }
            return success;
        }

        /* access modifiers changed from: package-private */
        public void addFollowing(int position, int ruleStatusIdx, boolean update) {
            int[] iArr = this.fBoundaries;
            int i = this.fEndBufIdx;
            if (position <= iArr[i]) {
                throw new AssertionError();
            } else if (ruleStatusIdx <= 32767) {
                int nextIdx = modChunkSize(i + 1);
                int i2 = this.fStartBufIdx;
                if (nextIdx == i2) {
                    this.fStartBufIdx = modChunkSize(i2 + 6);
                }
                this.fBoundaries[nextIdx] = position;
                this.fStatuses[nextIdx] = (short) ruleStatusIdx;
                this.fEndBufIdx = nextIdx;
                if (update) {
                    this.fBufIdx = nextIdx;
                    this.fTextIdx = position;
                } else if (nextIdx == this.fBufIdx) {
                    throw new AssertionError();
                }
            } else {
                throw new AssertionError();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean addPreceding(int position, int ruleStatusIdx, boolean update) {
            int[] iArr = this.fBoundaries;
            int i = this.fStartBufIdx;
            if (position >= iArr[i]) {
                throw new AssertionError();
            } else if (ruleStatusIdx <= 32767) {
                int nextIdx = modChunkSize(i - 1);
                int i2 = this.fEndBufIdx;
                if (nextIdx == i2) {
                    if (this.fBufIdx == i2 && !update) {
                        return false;
                    }
                    this.fEndBufIdx = modChunkSize(i2 - 1);
                }
                this.fBoundaries[nextIdx] = position;
                this.fStatuses[nextIdx] = (short) ruleStatusIdx;
                this.fStartBufIdx = nextIdx;
                if (update) {
                    this.fBufIdx = nextIdx;
                    this.fTextIdx = position;
                }
                return UpdateCachePosition;
            } else {
                throw new AssertionError();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean seek(int pos) {
            int[] iArr = this.fBoundaries;
            int i = this.fStartBufIdx;
            if (pos >= iArr[i]) {
                int i2 = this.fEndBufIdx;
                if (pos <= iArr[i2]) {
                    if (pos == iArr[i]) {
                        this.fBufIdx = i;
                        this.fTextIdx = iArr[i];
                        return UpdateCachePosition;
                    } else if (pos == iArr[i2]) {
                        this.fBufIdx = i2;
                        this.fTextIdx = iArr[i2];
                        return UpdateCachePosition;
                    } else {
                        int min = this.fStartBufIdx;
                        int max = this.fEndBufIdx;
                        while (min != max) {
                            int probe = modChunkSize(((min + max) + (min > max ? 128 : 0)) / 2);
                            if (this.fBoundaries[probe] > pos) {
                                max = probe;
                            } else {
                                min = modChunkSize(probe + 1);
                            }
                        }
                        if (this.fBoundaries[max] > pos) {
                            int modChunkSize = modChunkSize(max - 1);
                            this.fBufIdx = modChunkSize;
                            int i3 = this.fBoundaries[modChunkSize];
                            this.fTextIdx = i3;
                            if (i3 <= pos) {
                                return UpdateCachePosition;
                            }
                            throw new AssertionError();
                        }
                        throw new AssertionError();
                    }
                }
            }
            return false;
        }

        BreakCache(BreakCache src) {
            this.fStartBufIdx = src.fStartBufIdx;
            this.fEndBufIdx = src.fEndBufIdx;
            this.fTextIdx = src.fTextIdx;
            this.fBufIdx = src.fBufIdx;
            this.fBoundaries = (int[]) src.fBoundaries.clone();
            this.fStatuses = (short[]) src.fStatuses.clone();
            this.fSideBuffer = new DictionaryBreakEngine.DequeI();
        }

        /* access modifiers changed from: package-private */
        public void dumpCache() {
            System.out.printf("fTextIdx:%d   fBufIdx:%d%n", new Object[]{Integer.valueOf(this.fTextIdx), Integer.valueOf(this.fBufIdx)});
            int i = this.fStartBufIdx;
            while (true) {
                System.out.printf("%d  %d%n", new Object[]{Integer.valueOf(i), Integer.valueOf(this.fBoundaries[i])});
                if (i != this.fEndBufIdx) {
                    i = modChunkSize(i + 1);
                } else {
                    return;
                }
            }
        }

        private final int modChunkSize(int index) {
            return index & 127;
        }
    }
}
