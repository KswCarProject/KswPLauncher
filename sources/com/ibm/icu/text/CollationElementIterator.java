package com.ibm.icu.text;

import android.support.p001v4.internal.view.SupportMenu;
import android.support.p001v4.view.MotionEventCompat;
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

/* loaded from: classes.dex */
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

    /* JADX INFO: Access modifiers changed from: private */
    public static final int getFirstHalf(long p, int lower32) {
        return (((int) p) & SupportMenu.CATEGORY_MASK) | ((lower32 >> 16) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | ((lower32 >> 8) & 255);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int getSecondHalf(long p, int lower32) {
        return (((int) p) << 16) | ((lower32 >> 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (lower32 & 63);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean ceNeedsTwoParts(long ce) {
        return (281470698455103L & ce) != 0;
    }

    private CollationElementIterator(RuleBasedCollator collator) {
        this.iter_ = null;
        this.rbc_ = collator;
        this.otherHalf_ = 0;
        this.dir_ = (byte) 0;
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
        if (this.dir_ < 0 && (uVector32 = this.offsets_) != null && !uVector32.isEmpty()) {
            int i = this.iter_.getCEsLength();
            if (this.otherHalf_ != 0) {
                i++;
            }
            if (i >= this.offsets_.size()) {
                throw new AssertionError();
            }
            return this.offsets_.elementAti(i);
        }
        return this.iter_.getOffset();
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
            this.dir_ = (byte) 2;
        } else if (b == 0) {
            this.dir_ = (byte) 2;
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
        if (oh < 0) {
            if (this.otherHalf_ != 0) {
                int oh2 = this.otherHalf_;
                this.otherHalf_ = 0;
                return oh2;
            }
        } else if (oh == 0) {
            this.iter_.resetToOffset(this.string_.length());
            this.dir_ = (byte) -1;
        } else if (oh == 1) {
            this.dir_ = (byte) -1;
        } else {
            throw new IllegalStateException("Illegal change of direction");
        }
        if (this.offsets_ == null) {
            this.offsets_ = new UVector32();
        }
        int limitOffset = this.iter_.getCEsLength() == 0 ? this.iter_.getOffset() : 0;
        long ce = this.iter_.previousCE(this.offsets_);
        if (ce == 4311744768L) {
            return -1;
        }
        long p = ce >>> 32;
        int lower32 = (int) ce;
        int firstHalf = getFirstHalf(p, lower32);
        int secondHalf = getSecondHalf(p, lower32);
        if (secondHalf != 0) {
            if (this.offsets_.isEmpty()) {
                this.offsets_.addElement(this.iter_.getOffset());
                this.offsets_.addElement(limitOffset);
            }
            this.otherHalf_ = firstHalf;
            return secondHalf | 192;
        }
        return firstHalf;
    }

    public void reset() {
        this.iter_.resetToOffset(0);
        this.otherHalf_ = 0;
        this.dir_ = (byte) 0;
    }

    public void setOffset(int newOffset) {
        int offset;
        if (newOffset > 0 && newOffset < this.string_.length()) {
            int offset2 = newOffset;
            do {
                char c = this.string_.charAt(offset2);
                if (!this.rbc_.isUnsafe(c) || (Character.isHighSurrogate(c) && !this.rbc_.isUnsafe(this.string_.codePointAt(offset2)))) {
                    break;
                }
                offset2--;
            } while (offset2 > 0);
            if (offset2 < newOffset) {
                int lastSafeOffset = offset2;
                do {
                    this.iter_.resetToOffset(lastSafeOffset);
                    do {
                        this.iter_.nextCE();
                        offset = this.iter_.getOffset();
                    } while (offset == lastSafeOffset);
                    if (offset <= newOffset) {
                        lastSafeOffset = offset;
                        continue;
                    }
                } while (offset < newOffset);
                newOffset = lastSafeOffset;
            }
        }
        this.iter_.resetToOffset(newOffset);
        this.otherHalf_ = 0;
        this.dir_ = (byte) 1;
    }

    public void setText(String source) {
        UTF16CollationIterator fCDUTF16CollationIterator;
        this.string_ = source;
        boolean numeric = this.rbc_.settings.readOnly().isNumeric();
        if (this.rbc_.settings.readOnly().dontCheckFCD()) {
            fCDUTF16CollationIterator = new UTF16CollationIterator(this.rbc_.data, numeric, this.string_, 0);
        } else {
            fCDUTF16CollationIterator = new FCDUTF16CollationIterator(this.rbc_.data, numeric, this.string_, 0);
        }
        this.iter_ = fCDUTF16CollationIterator;
        this.otherHalf_ = 0;
        this.dir_ = (byte) 0;
    }

    public void setText(UCharacterIterator source) {
        IterCollationIterator fCDIterCollationIterator;
        this.string_ = source.getText();
        try {
            UCharacterIterator src = (UCharacterIterator) source.clone();
            src.setToStart();
            boolean numeric = this.rbc_.settings.readOnly().isNumeric();
            if (this.rbc_.settings.readOnly().dontCheckFCD()) {
                fCDIterCollationIterator = new IterCollationIterator(this.rbc_.data, numeric, src);
            } else {
                fCDIterCollationIterator = new FCDIterCollationIterator(this.rbc_.data, numeric, src, 0);
            }
            this.iter_ = fCDIterCollationIterator;
            this.otherHalf_ = 0;
            this.dir_ = (byte) 0;
        } catch (CloneNotSupportedException e) {
            setText(source.getText());
        }
    }

    public void setText(CharacterIterator source) {
        IterCollationIterator fCDIterCollationIterator;
        CharacterIteratorWrapper characterIteratorWrapper = new CharacterIteratorWrapper(source);
        characterIteratorWrapper.setToStart();
        this.string_ = characterIteratorWrapper.getText();
        boolean numeric = this.rbc_.settings.readOnly().isNumeric();
        if (this.rbc_.settings.readOnly().dontCheckFCD()) {
            fCDIterCollationIterator = new IterCollationIterator(this.rbc_.data, numeric, characterIteratorWrapper);
        } else {
            fCDIterCollationIterator = new FCDIterCollationIterator(this.rbc_.data, numeric, characterIteratorWrapper, 0);
        }
        this.iter_ = fCDIterCollationIterator;
        this.otherHalf_ = 0;
        this.dir_ = (byte) 0;
    }

    /* loaded from: classes.dex */
    private static final class MaxExpSink implements ContractionsAndExpansions.CESink {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private Map<Integer, Integer> maxExpansions;

        MaxExpSink(Map<Integer, Integer> h) {
            this.maxExpansions = h;
        }

        public void handleCE(long ce) {
        }

        public void handleExpansion(long[] ces, int start, int length) {
            int lastHalf;
            if (length <= 1) {
                return;
            }
            int count = 0;
            for (int i = 0; i < length; i++) {
                count += CollationElementIterator.ceNeedsTwoParts(ces[start + i]) ? 2 : 1;
            }
            int i2 = start + length;
            long ce = ces[i2 - 1];
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

    static final Map<Integer, Integer> computeMaxExpansions(CollationData data) {
        Map<Integer, Integer> maxExpansions = new HashMap<>();
        MaxExpSink sink = new MaxExpSink(maxExpansions);
        new ContractionsAndExpansions((UnicodeSet) null, (UnicodeSet) null, sink, true).forData(data);
        return maxExpansions;
    }

    public int getMaxExpansion(int ce) {
        return getMaxExpansion(this.rbc_.tailoring.maxExpansions, ce);
    }

    static int getMaxExpansion(Map<Integer, Integer> maxExpansions, int order) {
        Integer max;
        if (order == 0) {
            return 1;
        }
        if (maxExpansions != null && (max = maxExpansions.get(Integer.valueOf(order))) != null) {
            return max.intValue();
        }
        if ((order & 192) != 192) {
            return 1;
        }
        return 2;
    }

    private byte normalizeDir() {
        byte b = this.dir_;
        if (b == 1) {
            return (byte) 0;
        }
        return b;
    }

    public boolean equals(Object that) {
        if (that == this) {
            return true;
        }
        if (that instanceof CollationElementIterator) {
            CollationElementIterator thatceiter = (CollationElementIterator) that;
            return this.rbc_.equals(thatceiter.rbc_) && this.otherHalf_ == thatceiter.otherHalf_ && normalizeDir() == thatceiter.normalizeDir() && this.string_.equals(thatceiter.string_) && this.iter_.equals(thatceiter.iter_);
        }
        return false;
    }

    public int hashCode() {
        throw new AssertionError("hashCode not designed");
    }

    @Deprecated
    public RuleBasedCollator getRuleBasedCollator() {
        return this.rbc_;
    }
}
