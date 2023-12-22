package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: Add missing generic type declarations: [R] */
/* compiled from: _Sequences.kt */
@Metadata(m25d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H\u008a@"}, m24d2 = {"<anonymous>", "", "T", "R", "Lkotlin/sequences/SequenceScope;"}, m23k = 3, m22mv = {1, 6, 0}, m20xi = 48)
@DebugMetadata(m16c = "kotlin.sequences.SequencesKt___SequencesKt$zipWithNext$2", m15f = "_Sequences.kt", m14i = {0, 0, 0}, m13l = {2693}, m12m = "invokeSuspend", m11n = {"$this$result", "iterator", "next"}, m10s = {"L$0", "L$1", "L$2"})
/* loaded from: classes.dex */
final class SequencesKt___SequencesKt$zipWithNext$2<R> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Sequence<T> $this_zipWithNext;
    final /* synthetic */ Function2<T, T, R> $transform;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    SequencesKt___SequencesKt$zipWithNext$2(Sequence<? extends T> sequence, Function2<? super T, ? super T, ? extends R> function2, Continuation<? super SequencesKt___SequencesKt$zipWithNext$2> continuation) {
        super(2, continuation);
        this.$this_zipWithNext = sequence;
        this.$transform = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SequencesKt___SequencesKt$zipWithNext$2 sequencesKt___SequencesKt$zipWithNext$2 = new SequencesKt___SequencesKt$zipWithNext$2(this.$this_zipWithNext, this.$transform, continuation);
        sequencesKt___SequencesKt$zipWithNext$2.L$0 = obj;
        return sequencesKt___SequencesKt$zipWithNext$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return invoke((SequenceScope) ((SequenceScope) obj), continuation);
    }

    public final Object invoke(SequenceScope<? super R> sequenceScope, Continuation<? super Unit> continuation) {
        return ((SequencesKt___SequencesKt$zipWithNext$2) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        SequencesKt___SequencesKt$zipWithNext$2<R> sequencesKt___SequencesKt$zipWithNext$2;
        Iterator iterator;
        SequenceScope $this$result;
        Object current;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                sequencesKt___SequencesKt$zipWithNext$2 = this;
                SequenceScope $this$result2 = (SequenceScope) sequencesKt___SequencesKt$zipWithNext$2.L$0;
                iterator = sequencesKt___SequencesKt$zipWithNext$2.$this_zipWithNext.iterator();
                if (iterator.hasNext()) {
                    $this$result = $this$result2;
                    current = iterator.next();
                    break;
                } else {
                    return Unit.INSTANCE;
                }
            case 1:
                sequencesKt___SequencesKt$zipWithNext$2 = this;
                current = sequencesKt___SequencesKt$zipWithNext$2.L$2;
                iterator = (Iterator) sequencesKt___SequencesKt$zipWithNext$2.L$1;
                $this$result = (SequenceScope) sequencesKt___SequencesKt$zipWithNext$2.L$0;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        while (iterator.hasNext()) {
            Object next = iterator.next();
            sequencesKt___SequencesKt$zipWithNext$2.L$0 = $this$result;
            sequencesKt___SequencesKt$zipWithNext$2.L$1 = iterator;
            sequencesKt___SequencesKt$zipWithNext$2.L$2 = next;
            sequencesKt___SequencesKt$zipWithNext$2.label = 1;
            if ($this$result.yield(sequencesKt___SequencesKt$zipWithNext$2.$transform.invoke(current, next), sequencesKt___SequencesKt$zipWithNext$2) == coroutine_suspended) {
                return coroutine_suspended;
            }
            current = next;
        }
        return Unit.INSTANCE;
    }
}
