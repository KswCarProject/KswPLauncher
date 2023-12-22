package kotlin.experimental;

import com.ibm.icu.text.PluralRules;
import kotlin.Metadata;

@Metadata(m25d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\n\n\u0002\b\u0004\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\f\u001a\u0015\u0010\u0000\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\f\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0001H\u0087\b\u001a\r\u0010\u0004\u001a\u00020\u0003*\u00020\u0003H\u0087\b\u001a\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\f\u001a\u0015\u0010\u0005\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\f\u001a\u0015\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\f\u001a\u0015\u0010\u0006\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\f\u00a8\u0006\u0007"}, m24d2 = {"and", "", PluralRules.KEYWORD_OTHER, "", "inv", "or", "xor", "kotlin-stdlib"}, m23k = 2, m22mv = {1, 6, 0}, m20xi = 48)
/* renamed from: kotlin.experimental.BitwiseOperationsKt */
/* loaded from: classes.dex */
public final class bitwiseOperations {
    private static final byte and(byte $this$and, byte other) {
        return (byte) ($this$and & other);
    }

    /* renamed from: or */
    private static final byte m8or(byte $this$or, byte other) {
        return (byte) ($this$or | other);
    }

    private static final byte xor(byte $this$xor, byte other) {
        return (byte) ($this$xor ^ other);
    }

    private static final byte inv(byte $this$inv) {
        return (byte) (~$this$inv);
    }

    private static final short and(short $this$and, short other) {
        return (short) ($this$and & other);
    }

    /* renamed from: or */
    private static final short m7or(short $this$or, short other) {
        return (short) ($this$or | other);
    }

    private static final short xor(short $this$xor, short other) {
        return (short) ($this$xor ^ other);
    }

    private static final short inv(short $this$inv) {
        return (short) (~$this$inv);
    }
}
