package com.ibm.icu.text;

import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.MotionEventCompat;
import com.ibm.icu.impl.CharacterIteratorWrapper;
import com.ibm.icu.impl.coll.CollationData;
import com.ibm.icu.impl.coll.CollationIterator;
import com.ibm.icu.impl.coll.ContractionsAndExpansions;
import com.ibm.icu.impl.coll.FCDIterCollationIterator;
import com.ibm.icu.impl.coll.FCDUTF16CollationIterator;
import com.ibm.icu.impl.coll.IterCollationIterator;
import com.ibm.icu.impl.coll.UTF16CollationIterator;
import com.ibm.icu.impl.coll.UVector32;
import java.text.CharacterIterator;
import java.util.HashMap;
import java.util.Map;

public final class CollationElementIterator {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int IGNORABLE = 0;
    public static final int NULLORDER = -1;
    private byte dir_;
    private CollationIterator iter_;
    private UVector32 offsets_;
    private int otherHalf_;
    private RuleBasedCollator rbc_;
    private String string_;

    public static final int primaryOrder(int ce) {
        return (ce >>> 16) & 65535;
    }

    public static final int secondaryOrder(int ce) {
        return (ce >>> 8) & 255;
    }

    public static final int tertiaryOrder(int ce) {
        return ce & 255;
    }

    /* access modifiers changed from: private */
    public static final int getFirstHalf(long p, int lower32) {
        return (((int) p) & SupportMenu.CATEGORY_MASK) | ((lower32 >> 16) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | ((lower32 >> 8) & 255);
    }

    /* access modifiers changed from: private */
    public static final int getSecondHalf(long p, int lower32) {
        return (((int) p) << 16) | ((lower32 >> 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (lower32 & 63);
    }

    /* access modifiers changed from: private */
    public static final boolean ceNeedsTwoParts(long ce) {
        return (281470698455103L & ce) != 0;
    }

    private CollationElementIterator(RuleBasedCollator collator) {
        this.iter_ = null;
        this.rbc_ = collator;
        this.otherHalf_ = 0;
        this.dir_ = 0;
        this.offsets_ = null;
    }

    CollationElementIterator(String source, RuleBasedCollator collator) {
        this(collator);
        setText(source);
    }

    CollationElementIterator(CharacterIterator source, RuleBasedCollator collator) {
        this(collator);
        setText(source);
    }

    CollationElementIterator(UCharacterIterator source, RuleBasedCollator collator) {
        this(collator);
        setText(source);
    }

    public int getOffset() {
        UVector32 uVector32;
        if (this.dir_ >= 0 || (uVector32 = this.offsets_) == null || uVector32.isEmpty()) {
            return this.iter_.getOffset();
        }
        int i = this.iter_.getCEsLength();
        if (this.otherHalf_ != 0) {
            i++;
        }
        if (i < this.offsets_.size()) {
            return this.offsets_.elementAti(i);
        }
        throw new AssertionError();
    }

    public int next() {
        byte b = this.dir_;
        if (b > 1) {
            if (this.otherHalf_ != 0) {
                int oh = this.otherHalf_;
                this.otherHalf_ = 0;
                return oh;
            }
        } else if (b == 1) {
            this.dir_ = 2;
        } else if (b == 0) {
            this.dir_ = 2;
        } else {
            throw new IllegalStateException("Illegal change of direction");
        }
        this.iter_.clearCEsIfNoneRemaining();
        long ce = this.iter_.nextCE();
        if (ce == 4311744768L) {
            return -1;
        }
        long p = ce >>> 32;
        int lower32 = (int) ce;
        int firstHalf = getFirstHalf(p, lower32);
        int secondHalf = getSecondHalf(p, lower32);
        if (secondHalf != 0) {
            this.otherHalf_ = secondHalf | 192;
        }
        return firstHalf;
    }

    public int previous() {
        int oh = this.dir_;
        int i = 0;
        if (oh < 0) {
            if (this.otherHalf_ != 0) {
                int oh2 = this.otherHalf_;
                this.otherHalf_ = 0;
                return oh2;
            }
        } else if (oh == 0) {
            this.iter_.resetToOffset(this.string_.length());
            this.dir_ = -1;
        } else if (oh == 1) {
            this.dir_ = -1;
        } else {
            throw new IllegalStateException("Illegal change of direction");
        }
        if (this.offsets_ == null) {
            this.offsets_ = new UVector32();
        }
        if (this.iter_.getCEsLength() == 0) {
            i = this.iter_.getOffset();
        }
        int limitOffset = i;
        long ce = this.iter_.previousCE(this.offsets_);
        if (ce == 4311744768L) {
            return -1;
        }
        long p = ce >>> 32;
        int lower32 = (int) ce;
        int firstHalf = getFirstHalf(p, lower32);
        int secondHalf = getSecondHalf(p, lower32);
        if (secondHalf == 0) {
            return firstHalf;
        }
        if (this.offsets_.isEmpty()) {
            this.offsets_.addElement(this.iter_.getOffset());
            this.offsets_.addElement(limitOffset);
        }
        this.otherHalf_ = firstHalf;
        return secondHalf | 192;
    }

    public void reset() {
        this.iter_.resetToOffset(0);
        this.otherHalf_ = 0;
        this.dir_ = 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0035  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setOffset(int r5) {
        /*
            r4 = this;
            if (r5 <= 0) goto L_0x004f
            java.lang.String r0 = r4.string_
            int r0 = r0.length()
            if (r5 >= r0) goto L_0x004f
            r0 = r5
        L_0x000b:
            java.lang.String r1 = r4.string_
            char r1 = r1.charAt(r0)
            com.ibm.icu.text.RuleBasedCollator r2 = r4.rbc_
            boolean r2 = r2.isUnsafe(r1)
            if (r2 == 0) goto L_0x0033
            boolean r2 = java.lang.Character.isHighSurrogate(r1)
            if (r2 == 0) goto L_0x002e
            com.ibm.icu.text.RuleBasedCollator r2 = r4.rbc_
            java.lang.String r3 = r4.string_
            int r3 = r3.codePointAt(r0)
            boolean r2 = r2.isUnsafe(r3)
            if (r2 != 0) goto L_0x002e
            goto L_0x0033
        L_0x002e:
            int r0 = r0 + -1
            if (r0 > 0) goto L_0x000b
        L_0x0033:
            if (r0 >= r5) goto L_0x004f
            r1 = r0
        L_0x0036:
            com.ibm.icu.impl.coll.CollationIterator r2 = r4.iter_
            r2.resetToOffset(r1)
        L_0x003b:
            com.ibm.icu.impl.coll.CollationIterator r2 = r4.iter_
            r2.nextCE()
            com.ibm.icu.impl.coll.CollationIterator r2 = r4.iter_
            int r2 = r2.getOffset()
            r0 = r2
            if (r2 == r1) goto L_0x003b
            if (r0 > r5) goto L_0x004c
            r1 = r0
        L_0x004c:
            if (r0 < r5) goto L_0x0036
            r5 = r1
        L_0x004f:
            com.ibm.icu.impl.coll.CollationIterator r0 = r4.iter_
            r0.resetToOffset(r5)
            r0 = 0
            r4.otherHalf_ = r0
            r0 = 1
            r4.dir_ = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.CollationElementIterator.setOffset(int):void");
    }

    public void setText(String source) {
        CollationIterator newIter;
        this.string_ = source;
        boolean numeric = this.rbc_.settings.readOnly().isNumeric();
        if (this.rbc_.settings.readOnly().dontCheckFCD()) {
            newIter = new UTF16CollationIterator(this.rbc_.data, numeric, this.string_, 0);
        } else {
            newIter = new FCDUTF16CollationIterator(this.rbc_.data, numeric, this.string_, 0);
        }
        this.iter_ = newIter;
        this.otherHalf_ = 0;
        this.dir_ = 0;
    }

    public void setText(UCharacterIterator source) {
        CollationIterator newIter;
        this.string_ = source.getText();
        try {
            UCharacterIterator src = (UCharacterIterator) source.clone();
            src.setToStart();
            boolean numeric = this.rbc_.settings.readOnly().isNumeric();
            if (this.rbc_.settings.readOnly().dontCheckFCD()) {
                newIter = new IterCollationIterator(this.rbc_.data, numeric, src);
            } else {
                newIter = new FCDIterCollationIterator(this.rbc_.data, numeric, src, 0);
            }
            this.iter_ = newIter;
            this.otherHalf_ = 0;
            this.dir_ = 0;
        } catch (CloneNotSupportedException e) {
            setText(source.getText());
        }
    }

    public void setText(CharacterIterator source) {
        CollationIterator newIter;
        UCharacterIterator src = new CharacterIteratorWrapper(source);
        src.setToStart();
        this.string_ = src.getText();
        boolean numeric = this.rbc_.settings.readOnly().isNumeric();
        if (this.rbc_.settings.readOnly().dontCheckFCD()) {
            newIter = new IterCollationIterator(this.rbc_.data, numeric, src);
        } else {
            newIter = new FCDIterCollationIterator(this.rbc_.data, numeric, src, 0);
        }
        this.iter_ = newIter;
        this.otherHalf_ = 0;
        this.dir_ = 0;
    }

    private static final class MaxExpSink implements ContractionsAndExpansions.CESink {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private Map<Integer, Integer> maxExpansions;

        static {
            Class<CollationElementIterator> cls = CollationElementIterator.class;
        }

        MaxExpSink(Map<Integer, Integer> h) {
            this.maxExpansions = h;
        }

        public void handleCE(long ce) {
        }

        public void handleExpansion(long[] ces, int start, int length) {
            int lastHalf;
            if (length > 1) {
                int count = 0;
                for (int i = 0; i < length; i++) {
                    count += CollationElementIterator.ceNeedsTwoParts(ces[start + i]) ? 2 : 1;
                }
                long ce = ces[(start + length) - 1];
                long p = ce >>> 32;
                int lower32 = (int) ce;
                int lastHalf2 = CollationElementIterator.getSecondHalf(p, lower32);
                if (lastHalf2 == 0) {
                    lastHalf = CollationElementIterator.getFirstHalf(p, lower32);
                    if (lastHalf == 0) {
                        throw new AssertionError();
                    }
                } else {
                    lastHalf = lastHalf2 | 192;
                }
                Integer oldCount = this.maxExpansions.get(Integer.valueOf(lastHalf));
                if (oldCount == null || count > oldCount.intValue()) {
                    this.maxExpansions.put(Integer.valueOf(lastHalf), Integer.valueOf(count));
                }
            }
        }
    }

    static final Map<Integer, Integer> computeMaxExpansions(CollationData data) {
        Map<Integer, Integer> maxExpansions = new HashMap<>();
        new ContractionsAndExpansions((UnicodeSet) null, (UnicodeSet) null, new MaxExpSink(maxExpansions), true).forData(data);
        return maxExpansions;
    }

    public int getMaxExpansion(int ce) {
        return getMaxExpansion(this.rbc_.tailoring.maxExpansions, ce);
    }

    static int getMaxExpansion(Map<Integer, Integer> maxExpansions, int order) {
        if (order == 0) {
            return 1;
        }
        if (maxExpansions != null) {
            Integer num = maxExpansions.get(Integer.valueOf(order));
            Integer max = num;
            if (num != null) {
                return max.intValue();
            }
        }
        if ((order & 192) == 192) {
            return 2;
        }
        return 1;
    }

    private byte normalizeDir() {
        byte b = this.dir_;
        if (b == 1) {
            return 0;
        }
        return b;
    }

    public boolean equals(Object that) {
        if (that == this) {
            return true;
        }
        if (!(that instanceof CollationElementIterator)) {
            return false;
        }
        CollationElementIterator thatceiter = (CollationElementIterator) that;
        if (!this.rbc_.equals(thatceiter.rbc_) || this.otherHalf_ != thatceiter.otherHalf_ || normalizeDir() != thatceiter.normalizeDir() || !this.string_.equals(thatceiter.string_) || !this.iter_.equals(thatceiter.iter_)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        throw new AssertionError("hashCode not designed");
    }

    @Deprecated
    public RuleBasedCollator getRuleBasedCollator() {
        return this.rbc_;
    }
}
