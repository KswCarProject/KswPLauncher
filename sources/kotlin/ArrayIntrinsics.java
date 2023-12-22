package kotlin;

import kotlin.jvm.internal.Intrinsics;

@Metadata(m25d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a!\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u000b\b\u0000\u0010\u0002\u0018\u0001\u00a2\u0006\u0002\b\u0003H\u0086\b\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\u0005"}, m24d2 = {"emptyArray", "", "T", "Lkotlin/internal/PureReifiable;", "()[Ljava/lang/Object;", "kotlin-stdlib"}, m23k = 2, m22mv = {1, 6, 0}, m20xi = 48)
/* renamed from: kotlin.ArrayIntrinsicsKt */
/* loaded from: classes.dex */
public final class ArrayIntrinsics {
    public static final /* synthetic */ <T> T[] emptyArray() {
        Intrinsics.reifiedOperationMarker(0, "T?");
        return (T[]) new Object[0];
    }
}
