package kotlin;

/* compiled from: UInt.kt */
@Metadata(m25d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0003\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u0004H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u0006H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\bH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\t\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\nH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\fH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000e"}, m24d2 = {"toUInt", "Lkotlin/UInt;", "", "(B)I", "", "(D)I", "", "(F)I", "", "(I)I", "", "(J)I", "", "(S)I", "kotlin-stdlib"}, m23k = 2, m22mv = {1, 6, 0}, m20xi = 48)
/* loaded from: classes.dex */
public final class UIntKt {
    private static final int toUInt(byte $this$toUInt) {
        return UInt.m187constructorimpl($this$toUInt);
    }

    private static final int toUInt(short $this$toUInt) {
        return UInt.m187constructorimpl($this$toUInt);
    }

    private static final int toUInt(int $this$toUInt) {
        return UInt.m187constructorimpl($this$toUInt);
    }

    private static final int toUInt(long $this$toUInt) {
        return UInt.m187constructorimpl((int) $this$toUInt);
    }

    private static final int toUInt(float $this$toUInt) {
        return UnsignedUtils.doubleToUInt($this$toUInt);
    }

    private static final int toUInt(double $this$toUInt) {
        return UnsignedUtils.doubleToUInt($this$toUInt);
    }
}
