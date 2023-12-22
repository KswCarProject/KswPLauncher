package kotlin;

import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m25d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\u001aP\u0010\u0000\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0001\"\u0004\b\u0000\u0010\u00032\u001e\b\b\u0010\u0005\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0001H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\r\n\u0007\b\u0091\u001e\u0018\u00020\u0001\n\u0002\b\u0019\u00a8\u0006\u0007"}, m24d2 = {"suspend", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "R", "", "block", "(Lkotlin/jvm/functions/Function1;)Lkotlin/jvm/functions/Function1;", "kotlin-stdlib"}, m23k = 2, m22mv = {1, 6, 0}, m20xi = 48)
/* renamed from: kotlin.SuspendKt */
/* loaded from: classes.dex */
public final class Suspend {
    /* JADX WARN: Multi-variable type inference failed */
    private static final <R> Function1<Continuation<? super R>, Object> suspend(Function1<? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        return block;
    }
}
