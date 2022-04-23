package com.ibm.icu.text;

import com.ibm.icu.impl.ICUBinary;
import com.ibm.icu.impl.Norm2AllModes;
import com.ibm.icu.text.Normalizer;
import com.ibm.icu.util.ICUUncheckedIOException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public abstract class Normalizer2 {

    public enum Mode {
        COMPOSE,
        DECOMPOSE,
        FCD,
        COMPOSE_CONTIGUOUS
    }

    public abstract StringBuilder append(StringBuilder sb, CharSequence charSequence);

    public abstract String getDecomposition(int i);

    public abstract boolean hasBoundaryAfter(int i);

    public abstract boolean hasBoundaryBefore(int i);

    public abstract boolean isInert(int i);

    public abstract boolean isNormalized(CharSequence charSequence);

    public abstract Appendable normalize(CharSequence charSequence, Appendable appendable);

    public abstract StringBuilder normalize(CharSequence charSequence, StringBuilder sb);

    public abstract StringBuilder normalizeSecondAndAppend(StringBuilder sb, CharSequence charSequence);

    public abstract Normalizer.QuickCheckResult quickCheck(CharSequence charSequence);

    public abstract int spanQuickCheckYes(CharSequence charSequence);

    public static Normalizer2 getNFCInstance() {
        return Norm2AllModes.getNFCInstance().comp;
    }

    public static Normalizer2 getNFDInstance() {
        return Norm2AllModes.getNFCInstance().decomp;
    }

    public static Normalizer2 getNFKCInstance() {
        return Norm2AllModes.getNFKCInstance().comp;
    }

    public static Normalizer2 getNFKDInstance() {
        return Norm2AllModes.getNFKCInstance().decomp;
    }

    public static Normalizer2 getNFKCCasefoldInstance() {
        return Norm2AllModes.getNFKC_CFInstance().comp;
    }

    public static Normalizer2 getInstance(InputStream data, String name, Mode mode) {
        ByteBuffer bytes = null;
        if (data != null) {
            try {
                bytes = ICUBinary.getByteBufferFromInputStreamAndCloseStream(data);
            } catch (IOException e) {
                throw new ICUUncheckedIOException(e);
            }
        }
        IOException e2 = Norm2AllModes.getInstance(bytes, name);
        switch (AnonymousClass1.$SwitchMap$com$ibm$icu$text$Normalizer2$Mode[mode.ordinal()]) {
            case 1:
                return e2.comp;
            case 2:
                return e2.decomp;
            case 3:
                return e2.fcd;
            case 4:
                return e2.fcc;
            default:
                return null;
        }
    }

    /* renamed from: com.ibm.icu.text.Normalizer2$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$ibm$icu$text$Normalizer2$Mode;

        static {
            int[] iArr = new int[Mode.values().length];
            $SwitchMap$com$ibm$icu$text$Normalizer2$Mode = iArr;
            try {
                iArr[Mode.COMPOSE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$Normalizer2$Mode[Mode.DECOMPOSE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$Normalizer2$Mode[Mode.FCD.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$Normalizer2$Mode[Mode.COMPOSE_CONTIGUOUS.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public String normalize(CharSequence src) {
        if (src instanceof String) {
            int spanLength = spanQuickCheckYes(src);
            if (spanLength == src.length()) {
                return (String) src;
            }
            if (spanLength != 0) {
                return normalizeSecondAndAppend(new StringBuilder(src.length()).append(src, 0, spanLength), src.subSequence(spanLength, src.length())).toString();
            }
        }
        return normalize(src, new StringBuilder(src.length())).toString();
    }

    public String getRawDecomposition(int c) {
        return null;
    }

    public int composePair(int a, int b) {
        return -1;
    }

    public int getCombiningClass(int c) {
        return 0;
    }

    @Deprecated
    protected Normalizer2() {
    }
}
