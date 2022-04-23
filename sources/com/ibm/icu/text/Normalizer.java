package com.ibm.icu.text;

import com.ibm.icu.impl.Norm2AllModes;
import com.ibm.icu.impl.Normalizer2Impl;
import com.ibm.icu.impl.UCaseProps;
import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.util.ICUCloneNotSupportedException;
import java.nio.CharBuffer;
import java.text.CharacterIterator;

public final class Normalizer implements Cloneable {
    public static final int COMPARE_CODE_POINT_ORDER = 32768;
    private static final int COMPARE_EQUIV = 524288;
    public static final int COMPARE_IGNORE_CASE = 65536;
    @Deprecated
    public static final int COMPARE_NORM_OPTIONS_SHIFT = 20;
    @Deprecated
    public static final Mode COMPOSE;
    @Deprecated
    public static final Mode COMPOSE_COMPAT;
    @Deprecated
    public static final Mode DECOMP;
    @Deprecated
    public static final Mode DECOMP_COMPAT;
    @Deprecated
    public static final Mode DEFAULT;
    @Deprecated
    public static final int DONE = -1;
    @Deprecated
    public static final Mode FCD = new FCDMode();
    public static final int FOLD_CASE_DEFAULT = 0;
    public static final int FOLD_CASE_EXCLUDE_SPECIAL_I = 1;
    @Deprecated
    public static final int IGNORE_HANGUL = 1;
    public static final int INPUT_IS_FCD = 131072;
    public static final QuickCheckResult MAYBE = new QuickCheckResult(2);
    @Deprecated
    public static final Mode NFC;
    @Deprecated
    public static final Mode NFD;
    @Deprecated
    public static final Mode NFKC;
    @Deprecated
    public static final Mode NFKD;
    public static final QuickCheckResult NO = new QuickCheckResult(0);
    @Deprecated
    public static final Mode NONE;
    @Deprecated
    public static final Mode NO_OP;
    @Deprecated
    public static final int UNICODE_3_2 = 32;
    public static final QuickCheckResult YES = new QuickCheckResult(1);
    private StringBuilder buffer;
    private int bufferPos;
    private int currentIndex;
    private Mode mode;
    private int nextIndex;
    private Normalizer2 norm2;
    private int options;
    private UCharacterIterator text;

    private static final class ModeImpl {
        /* access modifiers changed from: private */
        public final Normalizer2 normalizer2;

        private ModeImpl(Normalizer2 n2) {
            this.normalizer2 = n2;
        }
    }

    private static final class NFDModeImpl {
        /* access modifiers changed from: private */
        public static final ModeImpl INSTANCE = new ModeImpl(Normalizer2.getNFDInstance());

        private NFDModeImpl() {
        }
    }

    private static final class NFKDModeImpl {
        /* access modifiers changed from: private */
        public static final ModeImpl INSTANCE = new ModeImpl(Normalizer2.getNFKDInstance());

        private NFKDModeImpl() {
        }
    }

    private static final class NFCModeImpl {
        /* access modifiers changed from: private */
        public static final ModeImpl INSTANCE = new ModeImpl(Normalizer2.getNFCInstance());

        private NFCModeImpl() {
        }
    }

    private static final class NFKCModeImpl {
        /* access modifiers changed from: private */
        public static final ModeImpl INSTANCE = new ModeImpl(Normalizer2.getNFKCInstance());

        private NFKCModeImpl() {
        }
    }

    private static final class FCDModeImpl {
        /* access modifiers changed from: private */
        public static final ModeImpl INSTANCE = new ModeImpl(Norm2AllModes.getFCDNormalizer2());

        private FCDModeImpl() {
        }
    }

    private static final class Unicode32 {
        /* access modifiers changed from: private */
        public static final UnicodeSet INSTANCE = new UnicodeSet("[:age=3.2:]").freeze();

        private Unicode32() {
        }
    }

    private static final class NFD32ModeImpl {
        /* access modifiers changed from: private */
        public static final ModeImpl INSTANCE = new ModeImpl(new FilteredNormalizer2(Normalizer2.getNFDInstance(), Unicode32.INSTANCE));

        private NFD32ModeImpl() {
        }
    }

    private static final class NFKD32ModeImpl {
        /* access modifiers changed from: private */
        public static final ModeImpl INSTANCE = new ModeImpl(new FilteredNormalizer2(Normalizer2.getNFKDInstance(), Unicode32.INSTANCE));

        private NFKD32ModeImpl() {
        }
    }

    private static final class NFC32ModeImpl {
        /* access modifiers changed from: private */
        public static final ModeImpl INSTANCE = new ModeImpl(new FilteredNormalizer2(Normalizer2.getNFCInstance(), Unicode32.INSTANCE));

        private NFC32ModeImpl() {
        }
    }

    private static final class NFKC32ModeImpl {
        /* access modifiers changed from: private */
        public static final ModeImpl INSTANCE = new ModeImpl(new FilteredNormalizer2(Normalizer2.getNFKCInstance(), Unicode32.INSTANCE));

        private NFKC32ModeImpl() {
        }
    }

    private static final class FCD32ModeImpl {
        /* access modifiers changed from: private */
        public static final ModeImpl INSTANCE = new ModeImpl(new FilteredNormalizer2(Norm2AllModes.getFCDNormalizer2(), Unicode32.INSTANCE));

        private FCD32ModeImpl() {
        }
    }

    @Deprecated
    public static abstract class Mode {
        /* access modifiers changed from: protected */
        @Deprecated
        public abstract Normalizer2 getNormalizer2(int i);

        @Deprecated
        protected Mode() {
        }
    }

    private static final class NONEMode extends Mode {
        private NONEMode() {
        }

        /* access modifiers changed from: protected */
        public Normalizer2 getNormalizer2(int options) {
            return Norm2AllModes.NOOP_NORMALIZER2;
        }
    }

    private static final class NFDMode extends Mode {
        private NFDMode() {
        }

        /* access modifiers changed from: protected */
        public Normalizer2 getNormalizer2(int options) {
            return ((options & 32) != 0 ? NFD32ModeImpl.INSTANCE : NFDModeImpl.INSTANCE).normalizer2;
        }
    }

    private static final class NFKDMode extends Mode {
        private NFKDMode() {
        }

        /* access modifiers changed from: protected */
        public Normalizer2 getNormalizer2(int options) {
            return ((options & 32) != 0 ? NFKD32ModeImpl.INSTANCE : NFKDModeImpl.INSTANCE).normalizer2;
        }
    }

    private static final class NFCMode extends Mode {
        private NFCMode() {
        }

        /* access modifiers changed from: protected */
        public Normalizer2 getNormalizer2(int options) {
            return ((options & 32) != 0 ? NFC32ModeImpl.INSTANCE : NFCModeImpl.INSTANCE).normalizer2;
        }
    }

    private static final class NFKCMode extends Mode {
        private NFKCMode() {
        }

        /* access modifiers changed from: protected */
        public Normalizer2 getNormalizer2(int options) {
            return ((options & 32) != 0 ? NFKC32ModeImpl.INSTANCE : NFKCModeImpl.INSTANCE).normalizer2;
        }
    }

    private static final class FCDMode extends Mode {
        private FCDMode() {
        }

        /* access modifiers changed from: protected */
        public Normalizer2 getNormalizer2(int options) {
            return ((options & 32) != 0 ? FCD32ModeImpl.INSTANCE : FCDModeImpl.INSTANCE).normalizer2;
        }
    }

    static {
        NONEMode nONEMode = new NONEMode();
        NONE = nONEMode;
        NFDMode nFDMode = new NFDMode();
        NFD = nFDMode;
        NFKDMode nFKDMode = new NFKDMode();
        NFKD = nFKDMode;
        NFCMode nFCMode = new NFCMode();
        NFC = nFCMode;
        DEFAULT = nFCMode;
        NFKCMode nFKCMode = new NFKCMode();
        NFKC = nFKCMode;
        NO_OP = nONEMode;
        COMPOSE = nFCMode;
        COMPOSE_COMPAT = nFKCMode;
        DECOMP = nFDMode;
        DECOMP_COMPAT = nFKDMode;
    }

    public static final class QuickCheckResult {
        private QuickCheckResult(int value) {
        }
    }

    @Deprecated
    public Normalizer(String str, Mode mode2, int opt) {
        this.text = UCharacterIterator.getInstance(str);
        this.mode = mode2;
        this.options = opt;
        this.norm2 = mode2.getNormalizer2(opt);
        this.buffer = new StringBuilder();
    }

    @Deprecated
    public Normalizer(CharacterIterator iter, Mode mode2, int opt) {
        this.text = UCharacterIterator.getInstance((CharacterIterator) iter.clone());
        this.mode = mode2;
        this.options = opt;
        this.norm2 = mode2.getNormalizer2(opt);
        this.buffer = new StringBuilder();
    }

    @Deprecated
    public Normalizer(UCharacterIterator iter, Mode mode2, int options2) {
        try {
            this.text = (UCharacterIterator) iter.clone();
            this.mode = mode2;
            this.options = options2;
            this.norm2 = mode2.getNormalizer2(options2);
            this.buffer = new StringBuilder();
        } catch (CloneNotSupportedException e) {
            throw new ICUCloneNotSupportedException(e);
        }
    }

    @Deprecated
    public Object clone() {
        try {
            Normalizer copy = (Normalizer) super.clone();
            copy.text = (UCharacterIterator) this.text.clone();
            copy.mode = this.mode;
            copy.options = this.options;
            copy.norm2 = this.norm2;
            copy.buffer = new StringBuilder(this.buffer);
            copy.bufferPos = this.bufferPos;
            copy.currentIndex = this.currentIndex;
            copy.nextIndex = this.nextIndex;
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new ICUCloneNotSupportedException(e);
        }
    }

    private static final Normalizer2 getComposeNormalizer2(boolean compat, int options2) {
        return (compat ? NFKC : NFC).getNormalizer2(options2);
    }

    private static final Normalizer2 getDecomposeNormalizer2(boolean compat, int options2) {
        return (compat ? NFKD : NFD).getNormalizer2(options2);
    }

    @Deprecated
    public static String compose(String str, boolean compat) {
        return compose(str, compat, 0);
    }

    @Deprecated
    public static String compose(String str, boolean compat, int options2) {
        return getComposeNormalizer2(compat, options2).normalize(str);
    }

    @Deprecated
    public static int compose(char[] source, char[] target, boolean compat, int options2) {
        return compose(source, 0, source.length, target, 0, target.length, compat, options2);
    }

    @Deprecated
    public static int compose(char[] src, int srcStart, int srcLimit, char[] dest, int destStart, int destLimit, boolean compat, int options2) {
        CharBuffer srcBuffer = CharBuffer.wrap(src, srcStart, srcLimit - srcStart);
        CharsAppendable app = new CharsAppendable(dest, destStart, destLimit);
        getComposeNormalizer2(compat, options2).normalize((CharSequence) srcBuffer, (Appendable) app);
        return app.length();
    }

    @Deprecated
    public static String decompose(String str, boolean compat) {
        return decompose(str, compat, 0);
    }

    @Deprecated
    public static String decompose(String str, boolean compat, int options2) {
        return getDecomposeNormalizer2(compat, options2).normalize(str);
    }

    @Deprecated
    public static int decompose(char[] source, char[] target, boolean compat, int options2) {
        return decompose(source, 0, source.length, target, 0, target.length, compat, options2);
    }

    @Deprecated
    public static int decompose(char[] src, int srcStart, int srcLimit, char[] dest, int destStart, int destLimit, boolean compat, int options2) {
        CharBuffer srcBuffer = CharBuffer.wrap(src, srcStart, srcLimit - srcStart);
        CharsAppendable app = new CharsAppendable(dest, destStart, destLimit);
        getDecomposeNormalizer2(compat, options2).normalize((CharSequence) srcBuffer, (Appendable) app);
        return app.length();
    }

    @Deprecated
    public static String normalize(String str, Mode mode2, int options2) {
        return mode2.getNormalizer2(options2).normalize(str);
    }

    @Deprecated
    public static String normalize(String src, Mode mode2) {
        return normalize(src, mode2, 0);
    }

    @Deprecated
    public static int normalize(char[] source, char[] target, Mode mode2, int options2) {
        return normalize(source, 0, source.length, target, 0, target.length, mode2, options2);
    }

    @Deprecated
    public static int normalize(char[] src, int srcStart, int srcLimit, char[] dest, int destStart, int destLimit, Mode mode2, int options2) {
        CharBuffer srcBuffer = CharBuffer.wrap(src, srcStart, srcLimit - srcStart);
        CharsAppendable app = new CharsAppendable(dest, destStart, destLimit);
        mode2.getNormalizer2(options2).normalize((CharSequence) srcBuffer, (Appendable) app);
        return app.length();
    }

    @Deprecated
    public static String normalize(int char32, Mode mode2, int options2) {
        if (mode2 != NFD || options2 != 0) {
            return normalize(UTF16.valueOf(char32), mode2, options2);
        }
        String decomposition = Normalizer2.getNFCInstance().getDecomposition(char32);
        if (decomposition == null) {
            return UTF16.valueOf(char32);
        }
        return decomposition;
    }

    @Deprecated
    public static String normalize(int char32, Mode mode2) {
        return normalize(char32, mode2, 0);
    }

    @Deprecated
    public static QuickCheckResult quickCheck(String source, Mode mode2) {
        return quickCheck(source, mode2, 0);
    }

    @Deprecated
    public static QuickCheckResult quickCheck(String source, Mode mode2, int options2) {
        return mode2.getNormalizer2(options2).quickCheck(source);
    }

    @Deprecated
    public static QuickCheckResult quickCheck(char[] source, Mode mode2, int options2) {
        return quickCheck(source, 0, source.length, mode2, options2);
    }

    @Deprecated
    public static QuickCheckResult quickCheck(char[] source, int start, int limit, Mode mode2, int options2) {
        return mode2.getNormalizer2(options2).quickCheck(CharBuffer.wrap(source, start, limit - start));
    }

    @Deprecated
    public static boolean isNormalized(char[] src, int start, int limit, Mode mode2, int options2) {
        return mode2.getNormalizer2(options2).isNormalized(CharBuffer.wrap(src, start, limit - start));
    }

    @Deprecated
    public static boolean isNormalized(String str, Mode mode2, int options2) {
        return mode2.getNormalizer2(options2).isNormalized(str);
    }

    @Deprecated
    public static boolean isNormalized(int char32, Mode mode2, int options2) {
        return isNormalized(UTF16.valueOf(char32), mode2, options2);
    }

    public static int compare(char[] s1, int s1Start, int s1Limit, char[] s2, int s2Start, int s2Limit, int options2) {
        if (s1 != null && s1Start >= 0 && s1Limit >= 0 && s2 != null && s2Start >= 0 && s2Limit >= 0 && s1Limit >= s1Start && s2Limit >= s2Start) {
            return internalCompare(CharBuffer.wrap(s1, s1Start, s1Limit - s1Start), CharBuffer.wrap(s2, s2Start, s2Limit - s2Start), options2);
        }
        throw new IllegalArgumentException();
    }

    public static int compare(String s1, String s2, int options2) {
        return internalCompare(s1, s2, options2);
    }

    public static int compare(char[] s1, char[] s2, int options2) {
        return internalCompare(CharBuffer.wrap(s1), CharBuffer.wrap(s2), options2);
    }

    public static int compare(int char32a, int char32b, int options2) {
        return internalCompare(UTF16.valueOf(char32a), UTF16.valueOf(char32b), 131072 | options2);
    }

    public static int compare(int char32a, String str2, int options2) {
        return internalCompare(UTF16.valueOf(char32a), str2, options2);
    }

    @Deprecated
    public static int concatenate(char[] left, int leftStart, int leftLimit, char[] right, int rightStart, int rightLimit, char[] dest, int destStart, int destLimit, Mode mode2, int options2) {
        if (dest == null) {
            throw new IllegalArgumentException();
        } else if (right != dest || rightStart >= destLimit || destStart >= rightLimit) {
            StringBuilder destBuilder = new StringBuilder((((leftLimit - leftStart) + rightLimit) - rightStart) + 16);
            destBuilder.append(left, leftStart, leftLimit - leftStart);
            mode2.getNormalizer2(options2).append(destBuilder, CharBuffer.wrap(right, rightStart, rightLimit - rightStart));
            int destLength = destBuilder.length();
            if (destLength <= destLimit - destStart) {
                destBuilder.getChars(0, destLength, dest, destStart);
                return destLength;
            }
            throw new IndexOutOfBoundsException(Integer.toString(destLength));
        } else {
            throw new IllegalArgumentException("overlapping right and dst ranges");
        }
    }

    @Deprecated
    public static String concatenate(char[] left, char[] right, Mode mode2, int options2) {
        return mode2.getNormalizer2(options2).append(new StringBuilder(left.length + right.length + 16).append(left), CharBuffer.wrap(right)).toString();
    }

    @Deprecated
    public static String concatenate(String left, String right, Mode mode2, int options2) {
        return mode2.getNormalizer2(options2).append(new StringBuilder(left.length() + right.length() + 16).append(left), right).toString();
    }

    @Deprecated
    public static int getFC_NFKC_Closure(int c, char[] dest) {
        String closure = getFC_NFKC_Closure(c);
        int length = closure.length();
        if (!(length == 0 || dest == null || length > dest.length)) {
            closure.getChars(0, length, dest, 0);
        }
        return length;
    }

    @Deprecated
    public static String getFC_NFKC_Closure(int c) {
        Norm2AllModes.Normalizer2WithImpl access$300 = NFKCModeImpl.INSTANCE.normalizer2;
        UCaseProps csp = UCaseProps.INSTANCE;
        StringBuilder folded = new StringBuilder();
        int folded1Length = csp.toFullFolding(c, folded, 0);
        if (folded1Length < 0) {
            Normalizer2Impl nfkcImpl = access$300.impl;
            if (nfkcImpl.getCompQuickCheck(nfkcImpl.getNorm16(c)) != 0) {
                return "";
            }
            folded.appendCodePoint(c);
        } else if (folded1Length > 31) {
            folded.appendCodePoint(folded1Length);
        }
        String kc1 = access$300.normalize(folded);
        String kc2 = access$300.normalize(UCharacter.foldCase(kc1, 0));
        if (kc1.equals(kc2)) {
            return "";
        }
        return kc2;
    }

    @Deprecated
    public int current() {
        if (this.bufferPos < this.buffer.length() || nextNormalize()) {
            return this.buffer.codePointAt(this.bufferPos);
        }
        return -1;
    }

    @Deprecated
    public int next() {
        if (this.bufferPos >= this.buffer.length() && !nextNormalize()) {
            return -1;
        }
        int c = this.buffer.codePointAt(this.bufferPos);
        this.bufferPos += Character.charCount(c);
        return c;
    }

    @Deprecated
    public int previous() {
        if (this.bufferPos <= 0 && !previousNormalize()) {
            return -1;
        }
        int c = this.buffer.codePointBefore(this.bufferPos);
        this.bufferPos -= Character.charCount(c);
        return c;
    }

    @Deprecated
    public void reset() {
        this.text.setToStart();
        this.nextIndex = 0;
        this.currentIndex = 0;
        clearBuffer();
    }

    @Deprecated
    public void setIndexOnly(int index) {
        this.text.setIndex(index);
        this.nextIndex = index;
        this.currentIndex = index;
        clearBuffer();
    }

    @Deprecated
    public int setIndex(int index) {
        setIndexOnly(index);
        return current();
    }

    @Deprecated
    public int getBeginIndex() {
        return 0;
    }

    @Deprecated
    public int getEndIndex() {
        return endIndex();
    }

    @Deprecated
    public int first() {
        reset();
        return next();
    }

    @Deprecated
    public int last() {
        this.text.setToLimit();
        int index = this.text.getIndex();
        this.nextIndex = index;
        this.currentIndex = index;
        clearBuffer();
        return previous();
    }

    @Deprecated
    public int getIndex() {
        if (this.bufferPos < this.buffer.length()) {
            return this.currentIndex;
        }
        return this.nextIndex;
    }

    @Deprecated
    public int startIndex() {
        return 0;
    }

    @Deprecated
    public int endIndex() {
        return this.text.getLength();
    }

    @Deprecated
    public void setMode(Mode newMode) {
        this.mode = newMode;
        this.norm2 = newMode.getNormalizer2(this.options);
    }

    @Deprecated
    public Mode getMode() {
        return this.mode;
    }

    @Deprecated
    public void setOption(int option, boolean value) {
        if (value) {
            this.options |= option;
        } else {
            this.options &= ~option;
        }
        this.norm2 = this.mode.getNormalizer2(this.options);
    }

    @Deprecated
    public int getOption(int option) {
        if ((this.options & option) != 0) {
            return 1;
        }
        return 0;
    }

    @Deprecated
    public int getText(char[] fillIn) {
        return this.text.getText(fillIn);
    }

    @Deprecated
    public int getLength() {
        return this.text.getLength();
    }

    @Deprecated
    public String getText() {
        return this.text.getText();
    }

    @Deprecated
    public void setText(StringBuffer newText) {
        UCharacterIterator newIter = UCharacterIterator.getInstance(newText);
        if (newIter != null) {
            this.text = newIter;
            reset();
            return;
        }
        throw new IllegalStateException("Could not create a new UCharacterIterator");
    }

    @Deprecated
    public void setText(char[] newText) {
        UCharacterIterator newIter = UCharacterIterator.getInstance(newText);
        if (newIter != null) {
            this.text = newIter;
            reset();
            return;
        }
        throw new IllegalStateException("Could not create a new UCharacterIterator");
    }

    @Deprecated
    public void setText(String newText) {
        UCharacterIterator newIter = UCharacterIterator.getInstance(newText);
        if (newIter != null) {
            this.text = newIter;
            reset();
            return;
        }
        throw new IllegalStateException("Could not create a new UCharacterIterator");
    }

    @Deprecated
    public void setText(CharacterIterator newText) {
        UCharacterIterator newIter = UCharacterIterator.getInstance(newText);
        if (newIter != null) {
            this.text = newIter;
            reset();
            return;
        }
        throw new IllegalStateException("Could not create a new UCharacterIterator");
    }

    @Deprecated
    public void setText(UCharacterIterator newText) {
        try {
            UCharacterIterator newIter = (UCharacterIterator) newText.clone();
            if (newIter != null) {
                this.text = newIter;
                reset();
                return;
            }
            throw new IllegalStateException("Could not create a new UCharacterIterator");
        } catch (CloneNotSupportedException e) {
            throw new ICUCloneNotSupportedException("Could not clone the UCharacterIterator", e);
        }
    }

    private void clearBuffer() {
        this.buffer.setLength(0);
        this.bufferPos = 0;
    }

    private boolean nextNormalize() {
        clearBuffer();
        int i = this.nextIndex;
        this.currentIndex = i;
        this.text.setIndex(i);
        int c = this.text.nextCodePoint();
        if (c < 0) {
            return false;
        }
        StringBuilder segment = new StringBuilder().appendCodePoint(c);
        while (true) {
            int nextCodePoint = this.text.nextCodePoint();
            int c2 = nextCodePoint;
            if (nextCodePoint < 0) {
                break;
            } else if (this.norm2.hasBoundaryBefore(c2)) {
                this.text.moveCodePointIndex(-1);
                break;
            } else {
                segment.appendCodePoint(c2);
            }
        }
        this.nextIndex = this.text.getIndex();
        this.norm2.normalize((CharSequence) segment, this.buffer);
        if (this.buffer.length() != 0) {
            return true;
        }
        return false;
    }

    private boolean previousNormalize() {
        int c;
        clearBuffer();
        int i = this.currentIndex;
        this.nextIndex = i;
        this.text.setIndex(i);
        StringBuilder segment = new StringBuilder();
        do {
            int previousCodePoint = this.text.previousCodePoint();
            c = previousCodePoint;
            if (previousCodePoint < 0) {
                break;
            } else if (c <= 65535) {
                segment.insert(0, (char) c);
            } else {
                segment.insert(0, Character.toChars(c));
            }
        } while (!this.norm2.hasBoundaryBefore(c));
        this.currentIndex = this.text.getIndex();
        this.norm2.normalize((CharSequence) segment, this.buffer);
        this.bufferPos = this.buffer.length();
        if (this.buffer.length() != 0) {
            return true;
        }
        return false;
    }

    private static int internalCompare(CharSequence s1, CharSequence s2, int options2) {
        Normalizer2 n2;
        int normOptions = options2 >>> 20;
        int options3 = options2 | 524288;
        if ((131072 & options3) == 0 || (options3 & 1) != 0) {
            if ((options3 & 1) != 0) {
                n2 = NFD.getNormalizer2(normOptions);
            } else {
                n2 = FCD.getNormalizer2(normOptions);
            }
            int spanQCYes1 = n2.spanQuickCheckYes(s1);
            int spanQCYes2 = n2.spanQuickCheckYes(s2);
            if (spanQCYes1 < s1.length()) {
                s1 = n2.normalizeSecondAndAppend(new StringBuilder(s1.length() + 16).append(s1, 0, spanQCYes1), s1.subSequence(spanQCYes1, s1.length()));
            }
            if (spanQCYes2 < s2.length()) {
                s2 = n2.normalizeSecondAndAppend(new StringBuilder(s2.length() + 16).append(s2, 0, spanQCYes2), s2.subSequence(spanQCYes2, s2.length()));
            }
        }
        return cmpEquivFold(s1, s2, options3);
    }

    private static final class CmpEquivLevel {
        CharSequence cs;
        int s;

        private CmpEquivLevel() {
        }
    }

    private static final CmpEquivLevel[] createCmpEquivLevelStack() {
        return new CmpEquivLevel[]{new CmpEquivLevel(), new CmpEquivLevel()};
    }

    /* JADX WARNING: Code restructure failed: missing block: B:164:0x037c, code lost:
        if (java.lang.Character.isLowSurrogate(r1.charAt(r12)) != false) goto L_0x039b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x03ac, code lost:
        if (java.lang.Character.isLowSurrogate(r2.charAt(r15)) != false) goto L_0x03d0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x013b  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x017b  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x018f  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x01f4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int cmpEquivFold(java.lang.CharSequence r25, java.lang.CharSequence r26, int r27) {
        /*
            r0 = r27
            r1 = 0
            r2 = 0
            r3 = 524288(0x80000, float:7.34684E-40)
            r4 = r0 & r3
            if (r4 == 0) goto L_0x0011
            com.ibm.icu.impl.Norm2AllModes r4 = com.ibm.icu.impl.Norm2AllModes.getNFCInstance()
            com.ibm.icu.impl.Normalizer2Impl r4 = r4.impl
            goto L_0x0012
        L_0x0011:
            r4 = 0
        L_0x0012:
            r5 = 65536(0x10000, float:9.18355E-41)
            r6 = r0 & r5
            r7 = 0
            if (r6 == 0) goto L_0x0026
            com.ibm.icu.impl.UCaseProps r6 = com.ibm.icu.impl.UCaseProps.INSTANCE
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            goto L_0x0029
        L_0x0026:
            r6 = 0
            r9 = r7
            r8 = r7
        L_0x0029:
            r10 = 0
            int r11 = r25.length()
            r12 = 0
            int r13 = r26.length()
            r14 = 0
            r15 = r14
            r16 = r14
            r17 = -1
            r18 = r17
            r19 = r17
            r20 = r18
            r21 = r19
            r18 = r15
            r19 = r16
            r15 = r12
            r16 = r13
            r12 = r10
            r13 = r11
            r10 = r1
            r11 = r2
            r1 = r25
            r2 = r26
        L_0x0050:
            if (r21 >= 0) goto L_0x007d
        L_0x0052:
            if (r12 != r13) goto L_0x0071
            if (r19 != 0) goto L_0x005d
            r21 = -1
            r7 = r19
            r3 = r21
            goto L_0x0081
        L_0x005d:
            int r19 = r19 + -1
            r7 = r10[r19]
            java.lang.CharSequence r1 = r7.cs
            if (r1 == 0) goto L_0x006f
            r7 = r10[r19]
            int r12 = r7.s
            int r13 = r1.length()
            r7 = 0
            goto L_0x0052
        L_0x006f:
            r7 = 0
            goto L_0x005d
        L_0x0071:
            int r7 = r12 + 1
            char r21 = r1.charAt(r12)
            r12 = r7
            r7 = r19
            r3 = r21
            goto L_0x0081
        L_0x007d:
            r7 = r19
            r3 = r21
        L_0x0081:
            r5 = r16
            if (r20 >= 0) goto L_0x00b4
        L_0x0085:
            if (r15 != r5) goto L_0x00a6
            if (r18 != 0) goto L_0x0092
            r20 = -1
            r16 = r4
            r14 = r18
            r4 = r20
            goto L_0x00ba
        L_0x0092:
            int r18 = r18 + -1
            r14 = r11[r18]
            java.lang.CharSequence r2 = r14.cs
            if (r2 == 0) goto L_0x00a4
            r14 = r11[r18]
            int r15 = r14.s
            int r5 = r2.length()
            r14 = 0
            goto L_0x0085
        L_0x00a4:
            r14 = 0
            goto L_0x0092
        L_0x00a6:
            int r14 = r15 + 1
            char r20 = r2.charAt(r15)
            r16 = r4
            r15 = r14
            r14 = r18
            r4 = r20
            goto L_0x00ba
        L_0x00b4:
            r14 = r18
            r16 = r4
            r4 = r20
        L_0x00ba:
            if (r3 != r4) goto L_0x00d7
            if (r3 >= 0) goto L_0x00c1
            r17 = 0
            return r17
        L_0x00c1:
            r20 = r17
            r3 = r17
            r21 = r3
            r19 = r7
            r18 = r14
            r4 = r16
            r3 = 524288(0x80000, float:7.34684E-40)
            r7 = 0
            r14 = 0
            r16 = r5
            r5 = 65536(0x10000, float:9.18355E-41)
            goto L_0x0050
        L_0x00d7:
            if (r3 >= 0) goto L_0x00da
            return r17
        L_0x00da:
            if (r4 >= 0) goto L_0x00df
            r17 = 1
            return r17
        L_0x00df:
            r18 = r3
            r25 = r11
            char r11 = (char) r3
            boolean r11 = com.ibm.icu.text.UTF16.isSurrogate(r11)
            if (r11 == 0) goto L_0x012d
            boolean r11 = com.ibm.icu.impl.Normalizer2Impl.UTF16Plus.isSurrogateLead(r3)
            if (r11 == 0) goto L_0x0112
            if (r12 == r13) goto L_0x010f
            char r11 = r1.charAt(r12)
            r26 = r11
            boolean r11 = java.lang.Character.isLowSurrogate(r11)
            if (r11 == 0) goto L_0x010a
            char r11 = (char) r3
            r20 = r13
            r13 = r26
            int r18 = java.lang.Character.toCodePoint(r11, r13)
            r11 = r18
            goto L_0x0131
        L_0x010a:
            r20 = r13
            r13 = r26
            goto L_0x012f
        L_0x010f:
            r20 = r13
            goto L_0x012f
        L_0x0112:
            r20 = r13
            int r11 = r12 + -2
            if (r11 < 0) goto L_0x012f
            int r11 = r12 + -2
            char r11 = r1.charAt(r11)
            r13 = r11
            boolean r11 = java.lang.Character.isHighSurrogate(r11)
            if (r11 == 0) goto L_0x012f
            char r11 = (char) r3
            int r18 = java.lang.Character.toCodePoint(r13, r11)
            r11 = r18
            goto L_0x0131
        L_0x012d:
            r20 = r13
        L_0x012f:
            r11 = r18
        L_0x0131:
            r13 = r4
            r26 = r13
            char r13 = (char) r4
            boolean r13 = com.ibm.icu.text.UTF16.isSurrogate(r13)
            if (r13 == 0) goto L_0x017b
            boolean r13 = com.ibm.icu.impl.Normalizer2Impl.UTF16Plus.isSurrogateLead(r4)
            if (r13 == 0) goto L_0x0161
            if (r15 == r5) goto L_0x015e
            char r13 = r2.charAt(r15)
            r18 = r13
            boolean r13 = java.lang.Character.isLowSurrogate(r13)
            if (r13 == 0) goto L_0x0159
            char r13 = (char) r4
            r23 = r5
            r5 = r18
            int r13 = java.lang.Character.toCodePoint(r13, r5)
            goto L_0x017f
        L_0x0159:
            r23 = r5
            r5 = r18
            goto L_0x017d
        L_0x015e:
            r23 = r5
            goto L_0x017d
        L_0x0161:
            r23 = r5
            int r5 = r15 + -2
            if (r5 < 0) goto L_0x017d
            int r5 = r15 + -2
            char r5 = r2.charAt(r5)
            r13 = r5
            boolean r5 = java.lang.Character.isHighSurrogate(r5)
            if (r5 == 0) goto L_0x017d
            char r5 = (char) r4
            int r5 = java.lang.Character.toCodePoint(r13, r5)
            r13 = r5
            goto L_0x017f
        L_0x017b:
            r23 = r5
        L_0x017d:
            r13 = r26
        L_0x017f:
            if (r7 != 0) goto L_0x01f6
            r18 = 65536(0x10000, float:9.18355E-41)
            r24 = r0 & r18
            if (r24 == 0) goto L_0x01f6
            int r18 = r6.toFullFolding(r11, r8, r0)
            r26 = r18
            if (r18 < 0) goto L_0x01f4
            char r5 = (char) r3
            boolean r5 = com.ibm.icu.text.UTF16.isSurrogate(r5)
            if (r5 == 0) goto L_0x01a7
            boolean r5 = com.ibm.icu.impl.Normalizer2Impl.UTF16Plus.isSurrogateLead(r3)
            if (r5 == 0) goto L_0x019f
            int r12 = r12 + 1
            goto L_0x01a7
        L_0x019f:
            int r15 = r15 + -1
            int r5 = r15 + -1
            char r4 = r2.charAt(r5)
        L_0x01a7:
            if (r10 != 0) goto L_0x01ae
            com.ibm.icu.text.Normalizer$CmpEquivLevel[] r5 = createCmpEquivLevelStack()
            r10 = r5
        L_0x01ae:
            r24 = r4
            r5 = 0
            r4 = r10[r5]
            r4.cs = r1
            r4 = r10[r5]
            r4.s = r12
            int r4 = r7 + 1
            r7 = r26
            r5 = 31
            if (r7 > r5) goto L_0x01cd
            int r5 = r8.length()
            int r5 = r5 - r7
            r26 = r4
            r4 = 0
            r8.delete(r4, r5)
            goto L_0x01d6
        L_0x01cd:
            r26 = r4
            r4 = 0
            r8.setLength(r4)
            r8.appendCodePoint(r7)
        L_0x01d6:
            r1 = r8
            r12 = 0
            int r4 = r8.length()
            r3 = -1
            r11 = r25
            r19 = r26
            r21 = r3
            r13 = r4
            r18 = r14
            r4 = r16
            r16 = r23
            r20 = r24
            r3 = 524288(0x80000, float:7.34684E-40)
            r5 = 65536(0x10000, float:9.18355E-41)
            r7 = 0
            r14 = 0
            goto L_0x0050
        L_0x01f4:
            r5 = r26
        L_0x01f6:
            if (r14 != 0) goto L_0x0278
            r5 = 65536(0x10000, float:9.18355E-41)
            r21 = r0 & r5
            if (r21 == 0) goto L_0x0278
            int r21 = r6.toFullFolding(r13, r9, r0)
            r26 = r21
            if (r21 < 0) goto L_0x0272
            char r5 = (char) r4
            boolean r5 = com.ibm.icu.text.UTF16.isSurrogate(r5)
            if (r5 == 0) goto L_0x021e
            boolean r5 = com.ibm.icu.impl.Normalizer2Impl.UTF16Plus.isSurrogateLead(r4)
            if (r5 == 0) goto L_0x0216
            int r15 = r15 + 1
            goto L_0x021e
        L_0x0216:
            int r12 = r12 + -1
            int r5 = r12 + -1
            char r3 = r1.charAt(r5)
        L_0x021e:
            if (r25 != 0) goto L_0x0225
            com.ibm.icu.text.Normalizer$CmpEquivLevel[] r5 = createCmpEquivLevelStack()
            goto L_0x0227
        L_0x0225:
            r5 = r25
        L_0x0227:
            r22 = r3
            r24 = r6
            r6 = 0
            r3 = r5[r6]
            r3.cs = r2
            r3 = r5[r6]
            r3.s = r15
            int r3 = r14 + 1
            r14 = r26
            r6 = 31
            if (r14 > r6) goto L_0x0248
            int r6 = r9.length()
            int r6 = r6 - r14
            r25 = r5
            r5 = 0
            r9.delete(r5, r6)
            goto L_0x0251
        L_0x0248:
            r25 = r5
            r5 = 0
            r9.setLength(r5)
            r9.appendCodePoint(r14)
        L_0x0251:
            r2 = r9
            r15 = 0
            int r6 = r9.length()
            r4 = -1
            r11 = r25
            r18 = r3
            r14 = r5
            r19 = r7
            r13 = r20
            r21 = r22
            r3 = 524288(0x80000, float:7.34684E-40)
            r5 = 65536(0x10000, float:9.18355E-41)
            r7 = 0
            r20 = r4
            r4 = r16
            r16 = r6
            r6 = r24
            goto L_0x0050
        L_0x0272:
            r24 = r6
            r5 = 0
            r6 = r26
            goto L_0x027b
        L_0x0278:
            r24 = r6
            r5 = 0
        L_0x027b:
            r6 = 2
            if (r7 >= r6) goto L_0x02e7
            r18 = 524288(0x80000, float:7.34684E-40)
            r22 = r0 & r18
            if (r22 == 0) goto L_0x02e7
            r5 = r16
            java.lang.String r16 = r5.getDecomposition(r11)
            r18 = r16
            if (r16 == 0) goto L_0x02e9
            char r6 = (char) r3
            boolean r6 = com.ibm.icu.text.UTF16.isSurrogate(r6)
            if (r6 == 0) goto L_0x02a6
            boolean r6 = com.ibm.icu.impl.Normalizer2Impl.UTF16Plus.isSurrogateLead(r3)
            if (r6 == 0) goto L_0x029e
            int r12 = r12 + 1
            goto L_0x02a6
        L_0x029e:
            int r15 = r15 + -1
            int r6 = r15 + -1
            char r4 = r2.charAt(r6)
        L_0x02a6:
            if (r10 != 0) goto L_0x02ad
            com.ibm.icu.text.Normalizer$CmpEquivLevel[] r6 = createCmpEquivLevelStack()
            r10 = r6
        L_0x02ad:
            r6 = r10[r7]
            r6.cs = r1
            r6 = r10[r7]
            r6.s = r12
            int r7 = r7 + 1
            r6 = 2
            if (r7 >= r6) goto L_0x02c4
            int r6 = r7 + 1
            r7 = r10[r7]
            r26 = r4
            r4 = 0
            r7.cs = r4
            goto L_0x02c7
        L_0x02c4:
            r26 = r4
            r6 = r7
        L_0x02c7:
            r1 = r18
            r12 = 0
            int r4 = r18.length()
            r3 = -1
            r11 = r25
            r20 = r26
            r21 = r3
            r13 = r4
            r4 = r5
            r19 = r6
            r18 = r14
            r16 = r23
            r6 = r24
            r3 = 524288(0x80000, float:7.34684E-40)
            r5 = 65536(0x10000, float:9.18355E-41)
            r7 = 0
            r14 = 0
            goto L_0x0050
        L_0x02e7:
            r5 = r16
        L_0x02e9:
            r6 = 2
            if (r14 >= r6) goto L_0x035c
            r6 = 524288(0x80000, float:7.34684E-40)
            r16 = r0 & r6
            if (r16 == 0) goto L_0x035c
            java.lang.String r16 = r5.getDecomposition(r13)
            r18 = r16
            if (r16 == 0) goto L_0x0359
            char r6 = (char) r4
            boolean r6 = com.ibm.icu.text.UTF16.isSurrogate(r6)
            if (r6 == 0) goto L_0x0312
            boolean r6 = com.ibm.icu.impl.Normalizer2Impl.UTF16Plus.isSurrogateLead(r4)
            if (r6 == 0) goto L_0x030a
            int r15 = r15 + 1
            goto L_0x0312
        L_0x030a:
            int r12 = r12 + -1
            int r6 = r12 + -1
            char r3 = r1.charAt(r6)
        L_0x0312:
            if (r25 != 0) goto L_0x0319
            com.ibm.icu.text.Normalizer$CmpEquivLevel[] r6 = createCmpEquivLevelStack()
            goto L_0x031b
        L_0x0319:
            r6 = r25
        L_0x031b:
            r16 = r3
            r3 = r6[r14]
            r3.cs = r2
            r3 = r6[r14]
            r3.s = r15
            int r14 = r14 + 1
            r3 = 2
            if (r14 >= r3) goto L_0x0335
            int r3 = r14 + 1
            r14 = r6[r14]
            r26 = r5
            r5 = 0
            r14.cs = r5
            r14 = r3
            goto L_0x0338
        L_0x0335:
            r26 = r5
            r5 = 0
        L_0x0338:
            r2 = r18
            r15 = 0
            int r3 = r18.length()
            r4 = -1
            r11 = r6
            r19 = r7
            r18 = r14
            r21 = r16
            r13 = r20
            r6 = r24
            r14 = 0
            r16 = r3
            r20 = r4
            r7 = r5
            r3 = 524288(0x80000, float:7.34684E-40)
            r5 = 65536(0x10000, float:9.18355E-41)
            r4 = r26
            goto L_0x0050
        L_0x0359:
            r26 = r5
            goto L_0x035e
        L_0x035c:
            r26 = r5
        L_0x035e:
            r5 = 55296(0xd800, float:7.7486E-41)
            if (r3 < r5) goto L_0x03cc
            if (r4 < r5) goto L_0x03cc
            r5 = 32768(0x8000, float:4.5918E-41)
            r5 = r5 & r0
            if (r5 == 0) goto L_0x03cc
            r5 = 56319(0xdbff, float:7.892E-41)
            if (r3 > r5) goto L_0x037f
            r6 = r20
            if (r12 == r6) goto L_0x0381
            char r16 = r1.charAt(r12)
            boolean r16 = java.lang.Character.isLowSurrogate(r16)
            if (r16 != 0) goto L_0x039b
            goto L_0x0381
        L_0x037f:
            r6 = r20
        L_0x0381:
            char r5 = (char) r3
            boolean r5 = java.lang.Character.isLowSurrogate(r5)
            if (r5 == 0) goto L_0x0399
            int r5 = r12 + -1
            if (r5 == 0) goto L_0x0399
            int r5 = r12 + -2
            char r5 = r1.charAt(r5)
            boolean r5 = java.lang.Character.isHighSurrogate(r5)
            if (r5 == 0) goto L_0x0399
            goto L_0x039b
        L_0x0399:
            int r3 = r3 + -10240
        L_0x039b:
            r5 = 56319(0xdbff, float:7.892E-41)
            if (r4 > r5) goto L_0x03af
            r5 = r23
            if (r15 == r5) goto L_0x03b1
            char r16 = r2.charAt(r15)
            boolean r16 = java.lang.Character.isLowSurrogate(r16)
            if (r16 != 0) goto L_0x03d0
            goto L_0x03b1
        L_0x03af:
            r5 = r23
        L_0x03b1:
            char r0 = (char) r4
            boolean r0 = java.lang.Character.isLowSurrogate(r0)
            if (r0 == 0) goto L_0x03c9
            int r0 = r15 + -1
            if (r0 == 0) goto L_0x03c9
            int r0 = r15 + -2
            char r0 = r2.charAt(r0)
            boolean r0 = java.lang.Character.isHighSurrogate(r0)
            if (r0 == 0) goto L_0x03c9
            goto L_0x03d0
        L_0x03c9:
            int r4 = r4 + -10240
            goto L_0x03d0
        L_0x03cc:
            r6 = r20
            r5 = r23
        L_0x03d0:
            int r0 = r3 - r4
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.Normalizer.cmpEquivFold(java.lang.CharSequence, java.lang.CharSequence, int):int");
    }

    private static final class CharsAppendable implements Appendable {
        private final char[] chars;
        private final int limit;
        private int offset;
        private final int start;

        public CharsAppendable(char[] dest, int destStart, int destLimit) {
            this.chars = dest;
            this.offset = destStart;
            this.start = destStart;
            this.limit = destLimit;
        }

        public int length() {
            int i = this.offset;
            int len = i - this.start;
            if (i <= this.limit) {
                return len;
            }
            throw new IndexOutOfBoundsException(Integer.toString(len));
        }

        public Appendable append(char c) {
            int i = this.offset;
            if (i < this.limit) {
                this.chars[i] = c;
            }
            this.offset = i + 1;
            return this;
        }

        public Appendable append(CharSequence s) {
            return append(s, 0, s.length());
        }

        public Appendable append(CharSequence s, int sStart, int sLimit) {
            int len = sLimit - sStart;
            int i = this.limit;
            int i2 = this.offset;
            if (len <= i - i2) {
                while (sStart < sLimit) {
                    char[] cArr = this.chars;
                    int i3 = this.offset;
                    this.offset = i3 + 1;
                    cArr[i3] = s.charAt(sStart);
                    sStart++;
                }
            } else {
                this.offset = i2 + len;
            }
            return this;
        }
    }
}
