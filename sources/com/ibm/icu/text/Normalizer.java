package com.ibm.icu.text;

import com.ibm.icu.impl.Norm2AllModes;
import com.ibm.icu.impl.Normalizer2Impl;
import com.ibm.icu.impl.UCaseProps;
import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.util.ICUCloneNotSupportedException;
import java.nio.CharBuffer;
import java.text.CharacterIterator;

/* loaded from: classes.dex */
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
    public static final Mode FCD;
    public static final int FOLD_CASE_DEFAULT = 0;
    public static final int FOLD_CASE_EXCLUDE_SPECIAL_I = 1;
    @Deprecated
    public static final int IGNORE_HANGUL = 1;
    public static final int INPUT_IS_FCD = 131072;
    public static final QuickCheckResult MAYBE;
    @Deprecated
    public static final Mode NFC;
    @Deprecated
    public static final Mode NFD;
    @Deprecated
    public static final Mode NFKC;
    @Deprecated
    public static final Mode NFKD;

    /* renamed from: NO */
    public static final QuickCheckResult f149NO;
    @Deprecated
    public static final Mode NONE;
    @Deprecated
    public static final Mode NO_OP;
    @Deprecated
    public static final int UNICODE_3_2 = 32;
    public static final QuickCheckResult YES;
    private StringBuilder buffer;
    private int bufferPos;
    private int currentIndex;
    private Mode mode;
    private int nextIndex;
    private Normalizer2 norm2;
    private int options;
    private UCharacterIterator text;

    /* loaded from: classes.dex */
    private static final class ModeImpl {
        private final Normalizer2 normalizer2;

        private ModeImpl(Normalizer2 n2) {
            this.normalizer2 = n2;
        }
    }

    /* loaded from: classes.dex */
    private static final class NFDModeImpl {
        private static final ModeImpl INSTANCE = new ModeImpl(Normalizer2.getNFDInstance());

        private NFDModeImpl() {
        }
    }

    /* loaded from: classes.dex */
    private static final class NFKDModeImpl {
        private static final ModeImpl INSTANCE = new ModeImpl(Normalizer2.getNFKDInstance());

        private NFKDModeImpl() {
        }
    }

    /* loaded from: classes.dex */
    private static final class NFCModeImpl {
        private static final ModeImpl INSTANCE = new ModeImpl(Normalizer2.getNFCInstance());

        private NFCModeImpl() {
        }
    }

    /* loaded from: classes.dex */
    private static final class NFKCModeImpl {
        private static final ModeImpl INSTANCE = new ModeImpl(Normalizer2.getNFKCInstance());

        private NFKCModeImpl() {
        }
    }

    /* loaded from: classes.dex */
    private static final class FCDModeImpl {
        private static final ModeImpl INSTANCE = new ModeImpl(Norm2AllModes.getFCDNormalizer2());

        private FCDModeImpl() {
        }
    }

    /* loaded from: classes.dex */
    private static final class Unicode32 {
        private static final UnicodeSet INSTANCE = new UnicodeSet("[:age=3.2:]").m87freeze();

        private Unicode32() {
        }
    }

    /* loaded from: classes.dex */
    private static final class NFD32ModeImpl {
        private static final ModeImpl INSTANCE = new ModeImpl(new FilteredNormalizer2(Normalizer2.getNFDInstance(), Unicode32.INSTANCE));

        private NFD32ModeImpl() {
        }
    }

    /* loaded from: classes.dex */
    private static final class NFKD32ModeImpl {
        private static final ModeImpl INSTANCE = new ModeImpl(new FilteredNormalizer2(Normalizer2.getNFKDInstance(), Unicode32.INSTANCE));

        private NFKD32ModeImpl() {
        }
    }

    /* loaded from: classes.dex */
    private static final class NFC32ModeImpl {
        private static final ModeImpl INSTANCE = new ModeImpl(new FilteredNormalizer2(Normalizer2.getNFCInstance(), Unicode32.INSTANCE));

        private NFC32ModeImpl() {
        }
    }

    /* loaded from: classes.dex */
    private static final class NFKC32ModeImpl {
        private static final ModeImpl INSTANCE = new ModeImpl(new FilteredNormalizer2(Normalizer2.getNFKCInstance(), Unicode32.INSTANCE));

        private NFKC32ModeImpl() {
        }
    }

    /* loaded from: classes.dex */
    private static final class FCD32ModeImpl {
        private static final ModeImpl INSTANCE = new ModeImpl(new FilteredNormalizer2(Norm2AllModes.getFCDNormalizer2(), Unicode32.INSTANCE));

        private FCD32ModeImpl() {
        }
    }

    @Deprecated
    /* loaded from: classes.dex */
    public static abstract class Mode {
        @Deprecated
        protected abstract Normalizer2 getNormalizer2(int i);

        @Deprecated
        protected Mode() {
        }
    }

    /* loaded from: classes.dex */
    private static final class NONEMode extends Mode {
        private NONEMode() {
        }

        @Override // com.ibm.icu.text.Normalizer.Mode
        protected Normalizer2 getNormalizer2(int options) {
            return Norm2AllModes.NOOP_NORMALIZER2;
        }
    }

    /* loaded from: classes.dex */
    private static final class NFDMode extends Mode {
        private NFDMode() {
        }

        @Override // com.ibm.icu.text.Normalizer.Mode
        protected Normalizer2 getNormalizer2(int options) {
            ModeImpl modeImpl;
            if ((options & 32) == 0) {
                modeImpl = NFDModeImpl.INSTANCE;
            } else {
                modeImpl = NFD32ModeImpl.INSTANCE;
            }
            return modeImpl.normalizer2;
        }
    }

    /* loaded from: classes.dex */
    private static final class NFKDMode extends Mode {
        private NFKDMode() {
        }

        @Override // com.ibm.icu.text.Normalizer.Mode
        protected Normalizer2 getNormalizer2(int options) {
            ModeImpl modeImpl;
            if ((options & 32) == 0) {
                modeImpl = NFKDModeImpl.INSTANCE;
            } else {
                modeImpl = NFKD32ModeImpl.INSTANCE;
            }
            return modeImpl.normalizer2;
        }
    }

    /* loaded from: classes.dex */
    private static final class NFCMode extends Mode {
        private NFCMode() {
        }

        @Override // com.ibm.icu.text.Normalizer.Mode
        protected Normalizer2 getNormalizer2(int options) {
            ModeImpl modeImpl;
            if ((options & 32) == 0) {
                modeImpl = NFCModeImpl.INSTANCE;
            } else {
                modeImpl = NFC32ModeImpl.INSTANCE;
            }
            return modeImpl.normalizer2;
        }
    }

    /* loaded from: classes.dex */
    private static final class NFKCMode extends Mode {
        private NFKCMode() {
        }

        @Override // com.ibm.icu.text.Normalizer.Mode
        protected Normalizer2 getNormalizer2(int options) {
            ModeImpl modeImpl;
            if ((options & 32) == 0) {
                modeImpl = NFKCModeImpl.INSTANCE;
            } else {
                modeImpl = NFKC32ModeImpl.INSTANCE;
            }
            return modeImpl.normalizer2;
        }
    }

    /* loaded from: classes.dex */
    private static final class FCDMode extends Mode {
        private FCDMode() {
        }

        @Override // com.ibm.icu.text.Normalizer.Mode
        protected Normalizer2 getNormalizer2(int options) {
            ModeImpl modeImpl;
            if ((options & 32) == 0) {
                modeImpl = FCDModeImpl.INSTANCE;
            } else {
                modeImpl = FCD32ModeImpl.INSTANCE;
            }
            return modeImpl.normalizer2;
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
        FCD = new FCDMode();
        NO_OP = nONEMode;
        COMPOSE = nFCMode;
        COMPOSE_COMPAT = nFKCMode;
        DECOMP = nFDMode;
        DECOMP_COMPAT = nFKDMode;
        f149NO = new QuickCheckResult(0);
        YES = new QuickCheckResult(1);
        MAYBE = new QuickCheckResult(2);
    }

    /* loaded from: classes.dex */
    public static final class QuickCheckResult {
        private QuickCheckResult(int value) {
        }
    }

    @Deprecated
    public Normalizer(String str, Mode mode, int opt) {
        this.text = UCharacterIterator.getInstance(str);
        this.mode = mode;
        this.options = opt;
        this.norm2 = mode.getNormalizer2(opt);
        this.buffer = new StringBuilder();
    }

    @Deprecated
    public Normalizer(CharacterIterator iter, Mode mode, int opt) {
        this.text = UCharacterIterator.getInstance((CharacterIterator) iter.clone());
        this.mode = mode;
        this.options = opt;
        this.norm2 = mode.getNormalizer2(opt);
        this.buffer = new StringBuilder();
    }

    @Deprecated
    public Normalizer(UCharacterIterator iter, Mode mode, int options) {
        try {
            this.text = (UCharacterIterator) iter.clone();
            this.mode = mode;
            this.options = options;
            this.norm2 = mode.getNormalizer2(options);
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

    private static final Normalizer2 getComposeNormalizer2(boolean compat, int options) {
        return (compat ? NFKC : NFC).getNormalizer2(options);
    }

    private static final Normalizer2 getDecomposeNormalizer2(boolean compat, int options) {
        return (compat ? NFKD : NFD).getNormalizer2(options);
    }

    @Deprecated
    public static String compose(String str, boolean compat) {
        return compose(str, compat, 0);
    }

    @Deprecated
    public static String compose(String str, boolean compat, int options) {
        return getComposeNormalizer2(compat, options).normalize(str);
    }

    @Deprecated
    public static int compose(char[] source, char[] target, boolean compat, int options) {
        return compose(source, 0, source.length, target, 0, target.length, compat, options);
    }

    @Deprecated
    public static int compose(char[] src, int srcStart, int srcLimit, char[] dest, int destStart, int destLimit, boolean compat, int options) {
        CharBuffer srcBuffer = CharBuffer.wrap(src, srcStart, srcLimit - srcStart);
        CharsAppendable app = new CharsAppendable(dest, destStart, destLimit);
        getComposeNormalizer2(compat, options).normalize(srcBuffer, app);
        return app.length();
    }

    @Deprecated
    public static String decompose(String str, boolean compat) {
        return decompose(str, compat, 0);
    }

    @Deprecated
    public static String decompose(String str, boolean compat, int options) {
        return getDecomposeNormalizer2(compat, options).normalize(str);
    }

    @Deprecated
    public static int decompose(char[] source, char[] target, boolean compat, int options) {
        return decompose(source, 0, source.length, target, 0, target.length, compat, options);
    }

    @Deprecated
    public static int decompose(char[] src, int srcStart, int srcLimit, char[] dest, int destStart, int destLimit, boolean compat, int options) {
        CharBuffer srcBuffer = CharBuffer.wrap(src, srcStart, srcLimit - srcStart);
        CharsAppendable app = new CharsAppendable(dest, destStart, destLimit);
        getDecomposeNormalizer2(compat, options).normalize(srcBuffer, app);
        return app.length();
    }

    @Deprecated
    public static String normalize(String str, Mode mode, int options) {
        return mode.getNormalizer2(options).normalize(str);
    }

    @Deprecated
    public static String normalize(String src, Mode mode) {
        return normalize(src, mode, 0);
    }

    @Deprecated
    public static int normalize(char[] source, char[] target, Mode mode, int options) {
        return normalize(source, 0, source.length, target, 0, target.length, mode, options);
    }

    @Deprecated
    public static int normalize(char[] src, int srcStart, int srcLimit, char[] dest, int destStart, int destLimit, Mode mode, int options) {
        CharBuffer srcBuffer = CharBuffer.wrap(src, srcStart, srcLimit - srcStart);
        CharsAppendable app = new CharsAppendable(dest, destStart, destLimit);
        mode.getNormalizer2(options).normalize(srcBuffer, app);
        return app.length();
    }

    @Deprecated
    public static String normalize(int char32, Mode mode, int options) {
        if (mode == NFD && options == 0) {
            String decomposition = Normalizer2.getNFCInstance().getDecomposition(char32);
            if (decomposition == null) {
                return UTF16.valueOf(char32);
            }
            return decomposition;
        }
        return normalize(UTF16.valueOf(char32), mode, options);
    }

    @Deprecated
    public static String normalize(int char32, Mode mode) {
        return normalize(char32, mode, 0);
    }

    @Deprecated
    public static QuickCheckResult quickCheck(String source, Mode mode) {
        return quickCheck(source, mode, 0);
    }

    @Deprecated
    public static QuickCheckResult quickCheck(String source, Mode mode, int options) {
        return mode.getNormalizer2(options).quickCheck(source);
    }

    @Deprecated
    public static QuickCheckResult quickCheck(char[] source, Mode mode, int options) {
        return quickCheck(source, 0, source.length, mode, options);
    }

    @Deprecated
    public static QuickCheckResult quickCheck(char[] source, int start, int limit, Mode mode, int options) {
        CharBuffer srcBuffer = CharBuffer.wrap(source, start, limit - start);
        return mode.getNormalizer2(options).quickCheck(srcBuffer);
    }

    @Deprecated
    public static boolean isNormalized(char[] src, int start, int limit, Mode mode, int options) {
        CharBuffer srcBuffer = CharBuffer.wrap(src, start, limit - start);
        return mode.getNormalizer2(options).isNormalized(srcBuffer);
    }

    @Deprecated
    public static boolean isNormalized(String str, Mode mode, int options) {
        return mode.getNormalizer2(options).isNormalized(str);
    }

    @Deprecated
    public static boolean isNormalized(int char32, Mode mode, int options) {
        return isNormalized(UTF16.valueOf(char32), mode, options);
    }

    public static int compare(char[] s1, int s1Start, int s1Limit, char[] s2, int s2Start, int s2Limit, int options) {
        if (s1 == null || s1Start < 0 || s1Limit < 0 || s2 == null || s2Start < 0 || s2Limit < 0 || s1Limit < s1Start || s2Limit < s2Start) {
            throw new IllegalArgumentException();
        }
        return internalCompare(CharBuffer.wrap(s1, s1Start, s1Limit - s1Start), CharBuffer.wrap(s2, s2Start, s2Limit - s2Start), options);
    }

    public static int compare(String s1, String s2, int options) {
        return internalCompare(s1, s2, options);
    }

    public static int compare(char[] s1, char[] s2, int options) {
        return internalCompare(CharBuffer.wrap(s1), CharBuffer.wrap(s2), options);
    }

    public static int compare(int char32a, int char32b, int options) {
        return internalCompare(UTF16.valueOf(char32a), UTF16.valueOf(char32b), 131072 | options);
    }

    public static int compare(int char32a, String str2, int options) {
        return internalCompare(UTF16.valueOf(char32a), str2, options);
    }

    @Deprecated
    public static int concatenate(char[] left, int leftStart, int leftLimit, char[] right, int rightStart, int rightLimit, char[] dest, int destStart, int destLimit, Mode mode, int options) {
        if (dest == null) {
            throw new IllegalArgumentException();
        }
        if (right == dest && rightStart < destLimit && destStart < rightLimit) {
            throw new IllegalArgumentException("overlapping right and dst ranges");
        }
        StringBuilder destBuilder = new StringBuilder((((leftLimit - leftStart) + rightLimit) - rightStart) + 16);
        destBuilder.append(left, leftStart, leftLimit - leftStart);
        CharBuffer rightBuffer = CharBuffer.wrap(right, rightStart, rightLimit - rightStart);
        mode.getNormalizer2(options).append(destBuilder, rightBuffer);
        int destLength = destBuilder.length();
        if (destLength <= destLimit - destStart) {
            destBuilder.getChars(0, destLength, dest, destStart);
            return destLength;
        }
        throw new IndexOutOfBoundsException(Integer.toString(destLength));
    }

    @Deprecated
    public static String concatenate(char[] left, char[] right, Mode mode, int options) {
        StringBuilder dest = new StringBuilder(left.length + right.length + 16).append(left);
        return mode.getNormalizer2(options).append(dest, CharBuffer.wrap(right)).toString();
    }

    @Deprecated
    public static String concatenate(String left, String right, Mode mode, int options) {
        StringBuilder dest = new StringBuilder(left.length() + right.length() + 16).append(left);
        return mode.getNormalizer2(options).append(dest, right).toString();
    }

    @Deprecated
    public static int getFC_NFKC_Closure(int c, char[] dest) {
        String closure = getFC_NFKC_Closure(c);
        int length = closure.length();
        if (length != 0 && dest != null && length <= dest.length) {
            closure.getChars(0, length, dest, 0);
        }
        return length;
    }

    @Deprecated
    public static String getFC_NFKC_Closure(int c) {
        Norm2AllModes.Normalizer2WithImpl normalizer2WithImpl = NFKCModeImpl.INSTANCE.normalizer2;
        UCaseProps csp = UCaseProps.INSTANCE;
        StringBuilder folded = new StringBuilder();
        int folded1Length = csp.toFullFolding(c, folded, 0);
        if (folded1Length < 0) {
            Normalizer2Impl nfkcImpl = normalizer2WithImpl.impl;
            if (nfkcImpl.getCompQuickCheck(nfkcImpl.getNorm16(c)) != 0) {
                return "";
            }
            folded.appendCodePoint(c);
        } else if (folded1Length > 31) {
            folded.appendCodePoint(folded1Length);
        }
        String kc1 = normalizer2WithImpl.normalize(folded);
        String kc2 = normalizer2WithImpl.normalize(UCharacter.foldCase(kc1, 0));
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
        if (this.bufferPos < this.buffer.length() || nextNormalize()) {
            int c = this.buffer.codePointAt(this.bufferPos);
            this.bufferPos += Character.charCount(c);
            return c;
        }
        return -1;
    }

    @Deprecated
    public int previous() {
        if (this.bufferPos > 0 || previousNormalize()) {
            int c = this.buffer.codePointBefore(this.bufferPos);
            this.bufferPos -= Character.charCount(c);
            return c;
        }
        return -1;
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
        if (newIter == null) {
            throw new IllegalStateException("Could not create a new UCharacterIterator");
        }
        this.text = newIter;
        reset();
    }

    @Deprecated
    public void setText(char[] newText) {
        UCharacterIterator newIter = UCharacterIterator.getInstance(newText);
        if (newIter == null) {
            throw new IllegalStateException("Could not create a new UCharacterIterator");
        }
        this.text = newIter;
        reset();
    }

    @Deprecated
    public void setText(String newText) {
        UCharacterIterator newIter = UCharacterIterator.getInstance(newText);
        if (newIter == null) {
            throw new IllegalStateException("Could not create a new UCharacterIterator");
        }
        this.text = newIter;
        reset();
    }

    @Deprecated
    public void setText(CharacterIterator newText) {
        UCharacterIterator newIter = UCharacterIterator.getInstance(newText);
        if (newIter == null) {
            throw new IllegalStateException("Could not create a new UCharacterIterator");
        }
        this.text = newIter;
        reset();
    }

    @Deprecated
    public void setText(UCharacterIterator newText) {
        try {
            UCharacterIterator newIter = (UCharacterIterator) newText.clone();
            if (newIter == null) {
                throw new IllegalStateException("Could not create a new UCharacterIterator");
            }
            this.text = newIter;
            reset();
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
            int c2 = this.text.nextCodePoint();
            if (c2 < 0) {
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
        return this.buffer.length() != 0;
    }

    private boolean previousNormalize() {
        int c;
        clearBuffer();
        int i = this.currentIndex;
        this.nextIndex = i;
        this.text.setIndex(i);
        StringBuilder segment = new StringBuilder();
        do {
            c = this.text.previousCodePoint();
            if (c < 0) {
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
        return this.buffer.length() != 0;
    }

    private static int internalCompare(CharSequence s1, CharSequence s2, int options) {
        Normalizer2 n2;
        int normOptions = options >>> 20;
        int options2 = options | 524288;
        if ((131072 & options2) == 0 || (options2 & 1) != 0) {
            if ((options2 & 1) != 0) {
                n2 = NFD.getNormalizer2(normOptions);
            } else {
                n2 = FCD.getNormalizer2(normOptions);
            }
            int spanQCYes1 = n2.spanQuickCheckYes(s1);
            int spanQCYes2 = n2.spanQuickCheckYes(s2);
            if (spanQCYes1 < s1.length()) {
                StringBuilder fcd1 = new StringBuilder(s1.length() + 16).append(s1, 0, spanQCYes1);
                s1 = n2.normalizeSecondAndAppend(fcd1, s1.subSequence(spanQCYes1, s1.length()));
            }
            if (spanQCYes2 < s2.length()) {
                StringBuilder fcd2 = new StringBuilder(s2.length() + 16).append(s2, 0, spanQCYes2);
                s2 = n2.normalizeSecondAndAppend(fcd2, s2.subSequence(spanQCYes2, s2.length()));
            }
        }
        return cmpEquivFold(s1, s2, options2);
    }

    /* loaded from: classes.dex */
    private static final class CmpEquivLevel {

        /* renamed from: cs */
        CharSequence f150cs;

        /* renamed from: s */
        int f151s;

        private CmpEquivLevel() {
        }
    }

    private static final CmpEquivLevel[] createCmpEquivLevelStack() {
        return new CmpEquivLevel[]{new CmpEquivLevel(), new CmpEquivLevel()};
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x020b, code lost:
        if (com.ibm.icu.text.UTF16.isSurrogate((char) r4) == false) goto L159;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0211, code lost:
        if (com.ibm.icu.impl.Normalizer2Impl.UTF16Plus.isSurrogateLead(r4) == false) goto L158;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x0213, code lost:
        r15 = r15 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x0216, code lost:
        r12 = r12 - 1;
        r3 = r1.charAt(r12 - 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x021e, code lost:
        if (r25 != null) goto L167;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0220, code lost:
        r5 = createCmpEquivLevelStack();
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x0225, code lost:
        r5 = r25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x0227, code lost:
        r22 = r3;
        r24 = r6;
        r5[0].f150cs = r2;
        r5[0].f151s = r15;
        r3 = r14 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x023a, code lost:
        if (r21 > 31) goto L166;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x023c, code lost:
        r25 = r5;
        r5 = false;
        r9.delete(0, r9.length() - r21);
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x0248, code lost:
        r25 = r5;
        r5 = false;
        r9.setLength(0);
        r9.appendCodePoint(r21);
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x0251, code lost:
        r2 = r9;
        r15 = 0;
        r11 = r25;
        r18 = r3;
        r19 = r7;
        r13 = r20;
        r21 = r22;
        r20 = -1;
        r4 = r16;
        r16 = r9.length();
        r6 = r24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x0272, code lost:
        r24 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x037c, code lost:
        if (java.lang.Character.isLowSurrogate(r1.charAt(r12)) != false) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:184:0x03ac, code lost:
        if (java.lang.Character.isLowSurrogate(r2.charAt(r15)) != false) goto L111;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x01fc, code lost:
        if ((r27 & 65536) == 0) goto L168;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x01fe, code lost:
        r21 = r6.toFullFolding(r13, r9, r27);
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0204, code lost:
        if (r21 < 0) goto L61;
     */
    /* JADX WARN: Removed duplicated region for block: B:118:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x01f6 A[ADDED_TO_REGION, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:211:0x02e7 A[ADDED_TO_REGION, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:215:0x035c A[ADDED_TO_REGION, EDGE_INSN: B:215:0x035c->B:157:0x035c ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01cd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static int cmpEquivFold(CharSequence cs1, CharSequence cs2, int options) {
        Normalizer2Impl nfcImpl;
        UCaseProps csp;
        StringBuilder fold2;
        StringBuilder fold1;
        int level1;
        int c1;
        int level2;
        Normalizer2Impl nfcImpl2;
        int c2;
        int limit1;
        int cp1;
        int limit2;
        int cp2;
        Normalizer2Impl nfcImpl3;
        CmpEquivLevel[] stack2;
        Normalizer2Impl nfcImpl4;
        Normalizer2Impl nfcImpl5;
        int c22;
        int level12;
        int length;
        int level13;
        if ((options & 524288) != 0) {
            nfcImpl = Norm2AllModes.getNFCInstance().impl;
        } else {
            nfcImpl = null;
        }
        if ((options & 65536) != 0) {
            csp = UCaseProps.INSTANCE;
            fold1 = new StringBuilder();
            fold2 = new StringBuilder();
        } else {
            csp = null;
            fold2 = null;
            fold1 = null;
        }
        int limit12 = cs1.length();
        int c23 = -1;
        int c12 = -1;
        int level22 = 0;
        int level14 = 0;
        int s2 = 0;
        int level15 = cs2.length();
        int s1 = 0;
        int limit13 = limit12;
        CmpEquivLevel[] stack1 = null;
        CmpEquivLevel[] stack22 = null;
        CharSequence cs12 = cs1;
        CharSequence cs22 = cs2;
        while (true) {
            if (c12 < 0) {
                while (true) {
                    if (s1 == limit13) {
                        if (level14 == 0) {
                            level1 = level14;
                            c1 = -1;
                            break;
                        }
                        while (true) {
                            level14--;
                            cs12 = stack1[level14].f150cs;
                            if (cs12 != null) {
                                break;
                            }
                        }
                        s1 = stack1[level14].f151s;
                        limit13 = cs12.length();
                    } else {
                        int c13 = cs12.charAt(s1);
                        s1++;
                        level1 = level14;
                        c1 = c13;
                        break;
                    }
                }
            } else {
                level1 = level14;
                c1 = c12;
            }
            int limit22 = level15;
            if (c23 < 0) {
                while (true) {
                    if (s2 == limit22) {
                        if (level22 == 0) {
                            nfcImpl2 = nfcImpl;
                            level2 = level22;
                            c2 = -1;
                            break;
                        }
                        while (true) {
                            level22--;
                            cs22 = stack22[level22].f150cs;
                            if (cs22 != null) {
                                break;
                            }
                        }
                        s2 = stack22[level22].f151s;
                        limit22 = cs22.length();
                    } else {
                        int c24 = cs22.charAt(s2);
                        nfcImpl2 = nfcImpl;
                        s2++;
                        level2 = level22;
                        c2 = c24;
                        break;
                    }
                }
            } else {
                level2 = level22;
                nfcImpl2 = nfcImpl;
                c2 = c23;
            }
            if (c1 == c2) {
                if (c1 < 0) {
                    return 0;
                }
                c23 = -1;
                c12 = -1;
                level14 = level1;
                level22 = level2;
                nfcImpl = nfcImpl2;
                level15 = limit22;
            } else if (c1 < 0) {
                return -1;
            } else {
                if (c2 < 0) {
                    return 1;
                }
                int cp12 = c1;
                CmpEquivLevel[] stack23 = stack22;
                if (!UTF16.isSurrogate((char) c1)) {
                    limit1 = limit13;
                } else if (Normalizer2Impl.UTF16Plus.isSurrogateLead(c1)) {
                    if (s1 != limit13) {
                        char c = cs12.charAt(s1);
                        if (!Character.isLowSurrogate(c)) {
                            limit1 = limit13;
                        } else {
                            limit1 = limit13;
                            int cp13 = Character.toCodePoint((char) c1, c);
                            cp1 = cp13;
                            int cp22 = c2;
                            if (!UTF16.isSurrogate((char) c2)) {
                                limit2 = limit22;
                            } else if (Normalizer2Impl.UTF16Plus.isSurrogateLead(c2)) {
                                if (s2 != limit22) {
                                    char c3 = cs22.charAt(s2);
                                    if (!Character.isLowSurrogate(c3)) {
                                        limit2 = limit22;
                                    } else {
                                        limit2 = limit22;
                                        cp2 = Character.toCodePoint((char) c2, c3);
                                        if (level1 != 0 && (options & 65536) != 0 && (length = csp.toFullFolding(cp1, fold1, options)) >= 0) {
                                            if (UTF16.isSurrogate((char) c1)) {
                                                if (Normalizer2Impl.UTF16Plus.isSurrogateLead(c1)) {
                                                    s1++;
                                                } else {
                                                    s2--;
                                                    c2 = cs22.charAt(s2 - 1);
                                                }
                                            }
                                            if (stack1 == null) {
                                                stack1 = createCmpEquivLevelStack();
                                            }
                                            int c25 = c2;
                                            stack1[0].f150cs = cs12;
                                            stack1[0].f151s = s1;
                                            int level16 = level1 + 1;
                                            if (length > 31) {
                                                level13 = level16;
                                                fold1.delete(0, fold1.length() - length);
                                            } else {
                                                level13 = level16;
                                                fold1.setLength(0);
                                                fold1.appendCodePoint(length);
                                            }
                                            cs12 = fold1;
                                            s1 = 0;
                                            int limit14 = fold1.length();
                                            stack22 = stack23;
                                            level14 = level13;
                                            c12 = -1;
                                            limit13 = limit14;
                                            level22 = level2;
                                            nfcImpl = nfcImpl2;
                                            level15 = limit2;
                                            c23 = c25;
                                        }
                                        UCaseProps csp2 = csp;
                                        if (level1 >= 2 || (options & 524288) == 0) {
                                            nfcImpl3 = nfcImpl2;
                                        } else {
                                            nfcImpl3 = nfcImpl2;
                                            String decomp1 = nfcImpl3.getDecomposition(cp1);
                                            if (decomp1 != null) {
                                                if (UTF16.isSurrogate((char) c1)) {
                                                    if (Normalizer2Impl.UTF16Plus.isSurrogateLead(c1)) {
                                                        s1++;
                                                    } else {
                                                        s2--;
                                                        c2 = cs22.charAt(s2 - 1);
                                                    }
                                                }
                                                if (stack1 == null) {
                                                    stack1 = createCmpEquivLevelStack();
                                                }
                                                stack1[level1].f150cs = cs12;
                                                stack1[level1].f151s = s1;
                                                int level17 = level1 + 1;
                                                if (level17 >= 2) {
                                                    c22 = c2;
                                                    level12 = level17;
                                                } else {
                                                    level12 = level17 + 1;
                                                    c22 = c2;
                                                    stack1[level17].f150cs = null;
                                                }
                                                cs12 = decomp1;
                                                s1 = 0;
                                                int limit15 = decomp1.length();
                                                stack22 = stack23;
                                                c23 = c22;
                                                c12 = -1;
                                                limit13 = limit15;
                                                nfcImpl = nfcImpl3;
                                                level14 = level12;
                                                level22 = level2;
                                                level15 = limit2;
                                                csp = csp2;
                                            }
                                        }
                                        if (level2 >= 2 && (options & 524288) != 0) {
                                            String decomp2 = nfcImpl3.getDecomposition(cp2);
                                            if (decomp2 == null) {
                                                break;
                                            }
                                            if (UTF16.isSurrogate((char) c2)) {
                                                if (Normalizer2Impl.UTF16Plus.isSurrogateLead(c2)) {
                                                    s2++;
                                                } else {
                                                    s1--;
                                                    c1 = cs12.charAt(s1 - 1);
                                                }
                                            }
                                            if (stack23 != null) {
                                                stack2 = stack23;
                                            } else {
                                                stack2 = createCmpEquivLevelStack();
                                            }
                                            int c14 = c1;
                                            stack2[level2].f150cs = cs22;
                                            stack2[level2].f151s = s2;
                                            int level23 = level2 + 1;
                                            if (level23 >= 2) {
                                                nfcImpl4 = nfcImpl3;
                                                nfcImpl5 = null;
                                            } else {
                                                nfcImpl4 = nfcImpl3;
                                                nfcImpl5 = null;
                                                stack2[level23].f150cs = null;
                                                level23++;
                                            }
                                            cs22 = decomp2;
                                            s2 = 0;
                                            stack22 = stack2;
                                            level14 = level1;
                                            level22 = level23;
                                            c12 = c14;
                                            limit13 = limit1;
                                            csp = csp2;
                                            level15 = decomp2.length();
                                            c23 = -1;
                                            nfcImpl = nfcImpl4;
                                        } else {
                                            break;
                                        }
                                    }
                                } else {
                                    limit2 = limit22;
                                }
                            } else {
                                limit2 = limit22;
                                if (s2 - 2 >= 0) {
                                    char c4 = cs22.charAt(s2 - 2);
                                    if (Character.isHighSurrogate(c4)) {
                                        cp2 = Character.toCodePoint(c4, (char) c2);
                                        if (level1 != 0) {
                                            if (UTF16.isSurrogate((char) c1)) {
                                            }
                                            if (stack1 == null) {
                                            }
                                            int c252 = c2;
                                            stack1[0].f150cs = cs12;
                                            stack1[0].f151s = s1;
                                            int level162 = level1 + 1;
                                            if (length > 31) {
                                            }
                                            cs12 = fold1;
                                            s1 = 0;
                                            int limit142 = fold1.length();
                                            stack22 = stack23;
                                            level14 = level13;
                                            c12 = -1;
                                            limit13 = limit142;
                                            level22 = level2;
                                            nfcImpl = nfcImpl2;
                                            level15 = limit2;
                                            c23 = c252;
                                        }
                                        UCaseProps csp22 = csp;
                                        if (level1 >= 2) {
                                        }
                                        nfcImpl3 = nfcImpl2;
                                        if (level2 >= 2) {
                                            break;
                                        }
                                        break;
                                    }
                                }
                            }
                            cp2 = cp22;
                            if (level1 != 0) {
                            }
                            UCaseProps csp222 = csp;
                            if (level1 >= 2) {
                            }
                            nfcImpl3 = nfcImpl2;
                            if (level2 >= 2) {
                            }
                        }
                    } else {
                        limit1 = limit13;
                    }
                } else {
                    limit1 = limit13;
                    if (s1 - 2 >= 0) {
                        char c5 = cs12.charAt(s1 - 2);
                        if (Character.isHighSurrogate(c5)) {
                            int cp14 = Character.toCodePoint(c5, (char) c1);
                            cp1 = cp14;
                            int cp222 = c2;
                            if (!UTF16.isSurrogate((char) c2)) {
                            }
                            cp2 = cp222;
                            if (level1 != 0) {
                            }
                            UCaseProps csp2222 = csp;
                            if (level1 >= 2) {
                            }
                            nfcImpl3 = nfcImpl2;
                            if (level2 >= 2) {
                            }
                        }
                    }
                }
                cp1 = cp12;
                int cp2222 = c2;
                if (!UTF16.isSurrogate((char) c2)) {
                }
                cp2 = cp2222;
                if (level1 != 0) {
                }
                UCaseProps csp22222 = csp;
                if (level1 >= 2) {
                }
                nfcImpl3 = nfcImpl2;
                if (level2 >= 2) {
                }
            }
        }
        if (c1 >= 55296 && c2 >= 55296 && (32768 & options) != 0) {
            if (c1 <= 56319) {
                if (s1 != limit1) {
                }
            }
            if (!Character.isLowSurrogate((char) c1) || s1 - 1 == 0 || !Character.isHighSurrogate(cs12.charAt(s1 - 2))) {
                c1 -= 10240;
            }
            if (c2 <= 56319) {
                if (s2 != limit2) {
                }
            }
            if (!Character.isLowSurrogate((char) c2) || s2 - 1 == 0 || !Character.isHighSurrogate(cs22.charAt(s2 - 2))) {
                c2 -= 10240;
            }
        }
        return c1 - c2;
    }

    /* loaded from: classes.dex */
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

        @Override // java.lang.Appendable
        public Appendable append(char c) {
            int i = this.offset;
            if (i < this.limit) {
                this.chars[i] = c;
            }
            this.offset = i + 1;
            return this;
        }

        @Override // java.lang.Appendable
        public Appendable append(CharSequence s) {
            return append(s, 0, s.length());
        }

        @Override // java.lang.Appendable
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
