package com.ibm.icu.text;

import java.nio.BufferOverflowException;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class Edits {
    private static final int LENGTH_IN_1TRAIL = 61;
    private static final int LENGTH_IN_2TRAIL = 62;
    private static final int MAX_SHORT_CHANGE = 28671;
    private static final int MAX_SHORT_CHANGE_NEW_LENGTH = 7;
    private static final int MAX_SHORT_CHANGE_OLD_LENGTH = 6;
    private static final int MAX_UNCHANGED = 4095;
    private static final int MAX_UNCHANGED_LENGTH = 4096;
    private static final int SHORT_CHANGE_NUM_MASK = 511;
    private static final int STACK_CAPACITY = 100;
    private char[] array = new char[100];
    private int delta;
    private int length;
    private int numChanges;

    public void reset() {
        this.numChanges = 0;
        this.delta = 0;
        this.length = 0;
    }

    private void setLastUnit(int last) {
        this.array[this.length - 1] = (char) last;
    }

    private int lastUnit() {
        int i = this.length;
        if (i > 0) {
            return this.array[i - 1];
        }
        return 65535;
    }

    public void addUnchanged(int unchangedLength) {
        if (unchangedLength < 0) {
            throw new IllegalArgumentException("addUnchanged(" + unchangedLength + "): length must not be negative");
        }
        int last = lastUnit();
        if (last < MAX_UNCHANGED) {
            int remaining = 4095 - last;
            if (remaining >= unchangedLength) {
                setLastUnit(last + unchangedLength);
                return;
            } else {
                setLastUnit(MAX_UNCHANGED);
                unchangedLength -= remaining;
            }
        }
        while (unchangedLength >= 4096) {
            append(MAX_UNCHANGED);
            unchangedLength -= 4096;
        }
        if (unchangedLength > 0) {
            append(unchangedLength - 1);
        }
    }

    public void addReplace(int oldLength, int newLength) {
        int head;
        int head2;
        int i;
        int i2;
        if (oldLength < 0 || newLength < 0) {
            throw new IllegalArgumentException("addReplace(" + oldLength + ", " + newLength + "): both lengths must be non-negative");
        }
        if (oldLength == 0 && newLength == 0) {
            return;
        }
        this.numChanges++;
        int newDelta = newLength - oldLength;
        if (newDelta != 0) {
            if ((newDelta > 0 && (i2 = this.delta) >= 0 && newDelta > Integer.MAX_VALUE - i2) || (newDelta < 0 && (i = this.delta) < 0 && newDelta < Integer.MIN_VALUE - i)) {
                throw new IndexOutOfBoundsException();
            }
            this.delta += newDelta;
        }
        if (oldLength > 0 && oldLength <= 6 && newLength <= 7) {
            int u = (oldLength << 12) | (newLength << 9);
            int last = lastUnit();
            if (MAX_UNCHANGED < last && last < MAX_SHORT_CHANGE && (last & (-512)) == u && (last & 511) < 511) {
                setLastUnit(last + 1);
            } else {
                append(u);
            }
        } else if (oldLength < 61 && newLength < 61) {
            int head3 = (oldLength << 6) | 28672;
            append(head3 | newLength);
        } else if (this.array.length - this.length >= 5 || growArray()) {
            int i3 = this.length;
            int limit = i3 + 1;
            if (oldLength < 61) {
                head = (oldLength << 6) | 28672;
            } else if (oldLength > 32767) {
                head = (((oldLength >> 30) + 62) << 6) | 28672;
                char[] cArr = this.array;
                int limit2 = limit + 1;
                cArr[limit] = (char) ((oldLength >> 15) | 32768);
                limit = limit2 + 1;
                cArr[limit2] = (char) (oldLength | 32768);
            } else {
                head = 28672 | 3904;
                this.array[limit] = (char) (oldLength | 32768);
                limit++;
            }
            if (newLength < 61) {
                head2 = head | newLength;
            } else if (newLength <= 32767) {
                this.array[limit] = (char) (newLength | 32768);
                head2 = head | 61;
                limit++;
            } else {
                char[] cArr2 = this.array;
                int limit3 = limit + 1;
                cArr2[limit] = (char) ((newLength >> 15) | 32768);
                limit = limit3 + 1;
                cArr2[limit3] = (char) (newLength | 32768);
                head2 = head | ((newLength >> 30) + 62);
            }
            this.array[i3] = (char) head2;
            this.length = limit;
        }
    }

    private void append(int r) {
        if (this.length < this.array.length || growArray()) {
            char[] cArr = this.array;
            int i = this.length;
            this.length = i + 1;
            cArr[i] = (char) r;
        }
    }

    private boolean growArray() {
        int newCapacity;
        char[] cArr = this.array;
        if (cArr.length == 100) {
            newCapacity = 2000;
        } else {
            int newCapacity2 = cArr.length;
            if (newCapacity2 == Integer.MAX_VALUE) {
                throw new BufferOverflowException();
            }
            if (cArr.length >= 1073741823) {
                newCapacity = Integer.MAX_VALUE;
            } else {
                int newCapacity3 = cArr.length;
                newCapacity = newCapacity3 * 2;
            }
        }
        if (newCapacity - cArr.length < 5) {
            throw new BufferOverflowException();
        }
        this.array = Arrays.copyOf(cArr, newCapacity);
        return true;
    }

    public int lengthDelta() {
        return this.delta;
    }

    public boolean hasChanges() {
        return this.numChanges != 0;
    }

    public int numberOfChanges() {
        return this.numChanges;
    }

    /* loaded from: classes.dex */
    public static final class Iterator {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final char[] array;
        private boolean changed;
        private final boolean coarse;
        private int destIndex;
        private int dir;
        private int index;
        private final int length;
        private int newLength_;
        private int oldLength_;
        private final boolean onlyChanges_;
        private int remaining;
        private int replIndex;
        private int srcIndex;

        private Iterator(char[] a, int len, boolean oc, boolean crs) {
            this.array = a;
            this.length = len;
            this.onlyChanges_ = oc;
            this.coarse = crs;
        }

        private int readLength(int head) {
            if (head < 61) {
                return head;
            }
            if (head < 62) {
                int i = this.index;
                if (i >= this.length) {
                    throw new AssertionError();
                }
                char[] cArr = this.array;
                if (cArr[i] < '\u8000') {
                    throw new AssertionError();
                }
                this.index = i + 1;
                return cArr[i] & '\u7fff';
            }
            int i2 = this.index;
            if (i2 + 2 > this.length) {
                throw new AssertionError();
            }
            char[] cArr2 = this.array;
            if (cArr2[i2] < '\u8000') {
                throw new AssertionError();
            }
            if (cArr2[i2 + 1] < '\u8000') {
                throw new AssertionError();
            }
            int len = ((head & 1) << 30) | ((cArr2[i2] & '\u7fff') << 15) | (cArr2[i2 + 1] & '\u7fff');
            this.index = i2 + 2;
            return len;
        }

        private void updateNextIndexes() {
            this.srcIndex += this.oldLength_;
            if (this.changed) {
                this.replIndex += this.newLength_;
            }
            this.destIndex += this.newLength_;
        }

        private void updatePreviousIndexes() {
            this.srcIndex -= this.oldLength_;
            if (this.changed) {
                this.replIndex -= this.newLength_;
            }
            this.destIndex -= this.newLength_;
        }

        private boolean noNext() {
            this.dir = 0;
            this.changed = false;
            this.newLength_ = 0;
            this.oldLength_ = 0;
            return false;
        }

        public boolean next() {
            return next(this.onlyChanges_);
        }

        /* JADX WARN: Code restructure failed: missing block: B:61:0x00ff, code lost:
            return true;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private boolean next(boolean onlyChanges) {
            char c;
            int i = this.dir;
            if (i > 0) {
                updateNextIndexes();
            } else if (i < 0 && this.remaining > 0) {
                this.index++;
                this.dir = 1;
                return true;
            } else {
                this.dir = 1;
            }
            int i2 = this.remaining;
            if (i2 >= 1) {
                if (i2 > 1) {
                    this.remaining = i2 - 1;
                    return true;
                }
                this.remaining = 0;
            }
            int i3 = this.index;
            if (i3 >= this.length) {
                return noNext();
            }
            char[] cArr = this.array;
            this.index = i3 + 1;
            char c2 = cArr[i3];
            if (c2 <= Edits.MAX_UNCHANGED) {
                this.changed = false;
                this.oldLength_ = c2 + 1;
                while (true) {
                    int i4 = this.index;
                    if (i4 >= this.length) {
                        break;
                    }
                    char c3 = this.array[i4];
                    c2 = c3;
                    if (c3 > Edits.MAX_UNCHANGED) {
                        break;
                    }
                    this.index = i4 + 1;
                    this.oldLength_ += c2 + 1;
                }
                this.newLength_ = this.oldLength_;
                if (!onlyChanges) {
                    return true;
                }
                updateNextIndexes();
                int i5 = this.index;
                if (i5 >= this.length) {
                    return noNext();
                }
                this.index = i5 + 1;
            }
            this.changed = true;
            if (c2 <= Edits.MAX_SHORT_CHANGE) {
                int oldLen = c2 >> '\f';
                int newLen = (c2 >> '\t') & 7;
                int num = (c2 & '\u01ff') + 1;
                if (this.coarse) {
                    this.oldLength_ = num * oldLen;
                    this.newLength_ = num * newLen;
                } else {
                    this.oldLength_ = oldLen;
                    this.newLength_ = newLen;
                    if (num > 1) {
                        this.remaining = num;
                    }
                    return true;
                }
            } else if (c2 > '\u7fff') {
                throw new AssertionError();
            } else {
                this.oldLength_ = readLength((c2 >> 6) & 63);
                this.newLength_ = readLength(c2 & '?');
                if (!this.coarse) {
                    return true;
                }
            }
            while (true) {
                int i6 = this.index;
                if (i6 >= this.length || (c = this.array[i6]) <= Edits.MAX_UNCHANGED) {
                    break;
                }
                this.index = i6 + 1;
                if (c <= Edits.MAX_SHORT_CHANGE) {
                    int num2 = (c & '\u01ff') + 1;
                    this.oldLength_ += (c >> '\f') * num2;
                    this.newLength_ += ((c >> '\t') & 7) * num2;
                } else if (c > '\u7fff') {
                    throw new AssertionError();
                } else {
                    this.oldLength_ += readLength((c >> 6) & 63);
                    this.newLength_ += readLength(c & '?');
                }
            }
        }

        private boolean previous() {
            int headIndex;
            char c;
            char c2;
            char c3;
            int i = this.dir;
            if (i >= 0) {
                if (i > 0) {
                    if (this.remaining > 0) {
                        this.index--;
                        this.dir = -1;
                        return true;
                    }
                    updateNextIndexes();
                }
                this.dir = -1;
            }
            int i2 = this.remaining;
            if (i2 > 0) {
                char c4 = this.array[this.index];
                if (Edits.MAX_UNCHANGED >= c4 || c4 > Edits.MAX_SHORT_CHANGE) {
                    throw new AssertionError();
                }
                if (i2 <= (c4 & '\u01ff')) {
                    this.remaining = i2 + 1;
                    updatePreviousIndexes();
                    return true;
                }
                this.remaining = 0;
            }
            int i3 = this.index;
            if (i3 <= 0) {
                return noNext();
            }
            char[] cArr = this.array;
            int i4 = i3 - 1;
            this.index = i4;
            char c5 = cArr[i4];
            if (c5 <= Edits.MAX_UNCHANGED) {
                this.changed = false;
                this.oldLength_ = c5 + 1;
                while (true) {
                    int i5 = this.index;
                    if (i5 <= 0 || (c3 = this.array[i5 - 1]) > Edits.MAX_UNCHANGED) {
                        break;
                    }
                    this.index = i5 - 1;
                    this.oldLength_ += c3 + 1;
                }
                this.newLength_ = this.oldLength_;
                updatePreviousIndexes();
                return true;
            }
            this.changed = true;
            if (c5 <= Edits.MAX_SHORT_CHANGE) {
                int oldLen = c5 >> '\f';
                int newLen = (c5 >> '\t') & 7;
                int num = (c5 & '\u01ff') + 1;
                if (this.coarse) {
                    this.oldLength_ = num * oldLen;
                    this.newLength_ = num * newLen;
                } else {
                    this.oldLength_ = oldLen;
                    this.newLength_ = newLen;
                    if (num > 1) {
                        this.remaining = 1;
                    }
                    updatePreviousIndexes();
                    return true;
                }
            } else {
                if (c5 <= '\u7fff') {
                    this.oldLength_ = readLength((c5 >> 6) & 63);
                    this.newLength_ = readLength(c5 & '?');
                } else if (i4 > 0) {
                    do {
                        char[] cArr2 = this.array;
                        headIndex = this.index - 1;
                        this.index = headIndex;
                        c = cArr2[headIndex];
                    } while (c > '\u7fff');
                    if (c <= Edits.MAX_SHORT_CHANGE) {
                        throw new AssertionError();
                    }
                    this.index = headIndex + 1;
                    this.oldLength_ = readLength((c >> 6) & 63);
                    this.newLength_ = readLength(c & '?');
                    this.index = headIndex;
                } else {
                    throw new AssertionError();
                }
                if (!this.coarse) {
                    updatePreviousIndexes();
                    return true;
                }
            }
            while (true) {
                int i6 = this.index;
                if (i6 <= 0 || (c2 = this.array[i6 - 1]) <= Edits.MAX_UNCHANGED) {
                    break;
                }
                int headIndex2 = i6 - 1;
                this.index = headIndex2;
                if (c2 <= Edits.MAX_SHORT_CHANGE) {
                    int num2 = (c2 & '\u01ff') + 1;
                    this.oldLength_ += (c2 >> '\f') * num2;
                    this.newLength_ += ((c2 >> '\t') & 7) * num2;
                } else if (c2 <= '\u7fff') {
                    this.index = headIndex2 + 1;
                    this.oldLength_ += readLength((c2 >> 6) & 63);
                    this.newLength_ += readLength(c2 & '?');
                    this.index = headIndex2;
                }
            }
            updatePreviousIndexes();
            return true;
        }

        public boolean findSourceIndex(int i) {
            return findIndex(i, true) == 0;
        }

        public boolean findDestinationIndex(int i) {
            return findIndex(i, false) == 0;
        }

        /* JADX WARN: Incorrect condition in loop: B:13:0x001b */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private int findIndex(int i, boolean findSource) {
            int spanStart;
            int spanLength;
            int spanStart2;
            int spanLength2;
            if (i < 0) {
                return -1;
            }
            if (findSource) {
                spanStart = this.srcIndex;
                spanLength = this.oldLength_;
            } else {
                spanStart = this.destIndex;
                spanLength = this.newLength_;
            }
            if (i < spanStart) {
                if (i >= spanStart / 2) {
                    while (hasPrevious) {
                        int spanStart3 = findSource ? this.srcIndex : this.destIndex;
                        if (i >= spanStart3) {
                            return 0;
                        }
                        int i2 = this.remaining;
                        if (i2 > 0) {
                            int spanLength3 = findSource ? this.oldLength_ : this.newLength_;
                            char c = this.array[this.index];
                            if (Edits.MAX_UNCHANGED < c && c <= Edits.MAX_SHORT_CHANGE) {
                                int num = ((c & '\u01ff') + 1) - i2;
                                int len = num * spanLength3;
                                if (i >= spanStart3 - len) {
                                    int n = (((spanStart3 - i) - 1) / spanLength3) + 1;
                                    this.srcIndex -= this.oldLength_ * n;
                                    int i3 = this.replIndex;
                                    int i4 = this.newLength_;
                                    this.replIndex = i3 - (n * i4);
                                    this.destIndex -= i4 * n;
                                    this.remaining = i2 + n;
                                    return 0;
                                }
                                this.srcIndex -= this.oldLength_ * num;
                                int i5 = this.replIndex;
                                int i6 = this.newLength_;
                                this.replIndex = i5 - (num * i6);
                                this.destIndex -= i6 * num;
                                this.remaining = 0;
                            } else {
                                throw new AssertionError();
                            }
                        }
                    }
                    throw new AssertionError();
                }
                this.dir = 0;
                this.destIndex = 0;
                this.replIndex = 0;
                this.srcIndex = 0;
                this.newLength_ = 0;
                this.oldLength_ = 0;
                this.remaining = 0;
                this.index = 0;
            } else if (i < spanStart + spanLength) {
                return 0;
            }
            while (next(false)) {
                if (findSource) {
                    spanStart2 = this.srcIndex;
                    spanLength2 = this.oldLength_;
                } else {
                    spanStart2 = this.destIndex;
                    spanLength2 = this.newLength_;
                }
                if (i < spanStart2 + spanLength2) {
                    return 0;
                }
                int i7 = this.remaining;
                if (i7 > 1) {
                    int len2 = i7 * spanLength2;
                    if (i < spanStart2 + len2) {
                        int n2 = (i - spanStart2) / spanLength2;
                        this.srcIndex += this.oldLength_ * n2;
                        int i8 = this.replIndex;
                        int i9 = this.newLength_;
                        this.replIndex = i8 + (n2 * i9);
                        this.destIndex += i9 * n2;
                        this.remaining = i7 - n2;
                        return 0;
                    }
                    this.oldLength_ *= i7;
                    this.newLength_ *= i7;
                    this.remaining = 0;
                }
            }
            return 1;
        }

        public int destinationIndexFromSourceIndex(int i) {
            int i2;
            int where = findIndex(i, true);
            if (where < 0) {
                return 0;
            }
            if (where > 0 || i == (i2 = this.srcIndex)) {
                return this.destIndex;
            }
            if (this.changed) {
                return this.destIndex + this.newLength_;
            }
            return this.destIndex + (i - i2);
        }

        public int sourceIndexFromDestinationIndex(int i) {
            int i2;
            int where = findIndex(i, false);
            if (where < 0) {
                return 0;
            }
            if (where > 0 || i == (i2 = this.destIndex)) {
                return this.srcIndex;
            }
            if (this.changed) {
                return this.srcIndex + this.oldLength_;
            }
            return this.srcIndex + (i - i2);
        }

        public boolean hasChange() {
            return this.changed;
        }

        public int oldLength() {
            return this.oldLength_;
        }

        public int newLength() {
            return this.newLength_;
        }

        public int sourceIndex() {
            return this.srcIndex;
        }

        public int replacementIndex() {
            return this.replIndex;
        }

        public int destinationIndex() {
            return this.destIndex;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(super.toString());
            sb.append("{ src[");
            sb.append(this.srcIndex);
            sb.append("..");
            sb.append(this.srcIndex + this.oldLength_);
            if (this.changed) {
                sb.append("] \u21dd dest[");
            } else {
                sb.append("] \u2261 dest[");
            }
            sb.append(this.destIndex);
            sb.append("..");
            sb.append(this.destIndex + this.newLength_);
            if (this.changed) {
                sb.append("], repl[");
                sb.append(this.replIndex);
                sb.append("..");
                sb.append(this.replIndex + this.newLength_);
                sb.append("] }");
            } else {
                sb.append("] (no-change) }");
            }
            return sb.toString();
        }
    }

    public Iterator getCoarseChangesIterator() {
        return new Iterator(this.array, this.length, true, true);
    }

    public Iterator getCoarseIterator() {
        return new Iterator(this.array, this.length, false, true);
    }

    public Iterator getFineChangesIterator() {
        return new Iterator(this.array, this.length, true, false);
    }

    public Iterator getFineIterator() {
        return new Iterator(this.array, this.length, false, false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0065, code lost:
        if (r6 != 0) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0068, code lost:
        if (r8 != 0) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x006a, code lost:
        if (r9 == 0) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x006c, code lost:
        addReplace(r8, r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x006f, code lost:
        return r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0077, code lost:
        throw new java.lang.IllegalArgumentException("The ab output string is shorter than the bc input string.");
     */
    /* JADX WARN: Removed duplicated region for block: B:94:0x00f5 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x00f0 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Edits mergeAndAppend(Edits ab, Edits bc) {
        Iterator abIter = ab.getFineIterator();
        Iterator bcIter = bc.getFineIterator();
        boolean abHasNext = true;
        boolean bcHasNext = true;
        int aLength = 0;
        int ab_bLength = 0;
        int bc_bLength = 0;
        int cLength = 0;
        int pending_aLength = 0;
        int pending_cLength = 0;
        while (true) {
            if (bc_bLength == 0 && bcHasNext) {
                boolean next = bcIter.next();
                bcHasNext = next;
                if (next) {
                    bc_bLength = bcIter.oldLength();
                    cLength = bcIter.newLength();
                    if (bc_bLength == 0) {
                        if (ab_bLength == 0 || !abIter.hasChange()) {
                            addReplace(pending_aLength, pending_cLength + cLength);
                            pending_cLength = 0;
                            pending_aLength = 0;
                        } else {
                            pending_cLength += cLength;
                        }
                    }
                }
            }
            if (ab_bLength == 0) {
                if (!abHasNext) {
                    break;
                }
                boolean next2 = abIter.next();
                abHasNext = next2;
                if (!next2) {
                    break;
                }
                aLength = abIter.oldLength();
                ab_bLength = abIter.newLength();
                if (ab_bLength == 0) {
                    if (bc_bLength == bcIter.oldLength() || !bcIter.hasChange()) {
                        addReplace(pending_aLength + aLength, pending_cLength);
                        pending_cLength = 0;
                        pending_aLength = 0;
                    } else {
                        pending_aLength += aLength;
                    }
                }
            }
            if (bc_bLength == 0) {
                throw new IllegalArgumentException("The bc input string is shorter than the ab output string.");
            }
            if (!abIter.hasChange() && !bcIter.hasChange()) {
                if (pending_aLength != 0 || pending_cLength != 0) {
                    addReplace(pending_aLength, pending_cLength);
                    pending_cLength = 0;
                    pending_aLength = 0;
                }
                int unchangedLength = aLength <= cLength ? aLength : cLength;
                addUnchanged(unchangedLength);
                int i = aLength - unchangedLength;
                aLength = i;
                ab_bLength = i;
                int i2 = cLength - unchangedLength;
                cLength = i2;
                bc_bLength = i2;
            } else if (!abIter.hasChange() && bcIter.hasChange()) {
                if (ab_bLength >= bc_bLength) {
                    addReplace(pending_aLength + bc_bLength, pending_cLength + cLength);
                    pending_cLength = 0;
                    pending_aLength = 0;
                    int i3 = ab_bLength - bc_bLength;
                    ab_bLength = i3;
                    aLength = i3;
                    bc_bLength = 0;
                } else {
                    pending_aLength += aLength;
                    pending_cLength += cLength;
                    if (ab_bLength < bc_bLength) {
                    }
                }
            } else if (abIter.hasChange() && !bcIter.hasChange()) {
                if (ab_bLength <= bc_bLength) {
                    addReplace(pending_aLength + aLength, pending_cLength + ab_bLength);
                    pending_cLength = 0;
                    pending_aLength = 0;
                    int i4 = bc_bLength - ab_bLength;
                    bc_bLength = i4;
                    cLength = i4;
                    ab_bLength = 0;
                } else {
                    pending_aLength += aLength;
                    pending_cLength += cLength;
                    if (ab_bLength < bc_bLength) {
                    }
                }
            } else if (ab_bLength == bc_bLength) {
                addReplace(pending_aLength + aLength, pending_cLength + cLength);
                pending_cLength = 0;
                pending_aLength = 0;
                bc_bLength = 0;
                ab_bLength = 0;
            } else {
                pending_aLength += aLength;
                pending_cLength += cLength;
                if (ab_bLength < bc_bLength) {
                    bc_bLength -= ab_bLength;
                    ab_bLength = 0;
                    cLength = 0;
                } else {
                    ab_bLength -= bc_bLength;
                    bc_bLength = 0;
                    aLength = 0;
                }
            }
        }
    }
}
