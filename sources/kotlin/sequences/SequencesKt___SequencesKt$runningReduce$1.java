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

/* JADX INFO: Add missing generic type declarations: [S] */
/* compiled from: _Sequences.kt */
@Metadata(m25d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\u0003*\u0002H\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u008a@"}, m24d2 = {"<anonymous>", "", "S", "T", "Lkotlin/sequences/SequenceScope;"}, m23k = 3, m22mv = {1, 6, 0}, m20xi = 48)
@DebugMetadata(m16c = "kotlin.sequences.SequencesKt___SequencesKt$runningReduce$1", m15f = "_Sequences.kt", m14i = {0, 0, 0, 1, 1, 1}, m13l = {2173, 2176}, m12m = "invokeSuspend", m11n = {"$this$sequence", "iterator", "accumulator", "$this$sequence", "iterator", "accumulator"}, m10s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2"})
/* loaded from: classes.dex */
final class SequencesKt___SequencesKt$runningReduce$1<S> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super S>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2<S, T, S> $operation;
    final /* synthetic */ Sequence<T> $this_runningReduce;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    SequencesKt___SequencesKt$runningReduce$1(Sequence<? extends T> sequence, Function2<? super S, ? super T, ? extends S> function2, Continuation<? super SequencesKt___SequencesKt$runningReduce$1> continuation) {
        super(2, continuation);
        this.$this_runningReduce = sequence;
        this.$operation = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SequencesKt___SequencesKt$runningReduce$1 sequencesKt___SequencesKt$runningReduce$1 = new SequencesKt___SequencesKt$runningReduce$1(this.$this_runningReduce, this.$operation, continuation);
        sequencesKt___SequencesKt$runningReduce$1.L$0 = obj;
        return sequencesKt___SequencesKt$runningReduce$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return invoke((SequenceScope) ((SequenceScope) obj), continuation);
    }

    public final Object invoke(SequenceScope<? super S> sequenceScope, Continuation<? super Unit> continuation) {
        return ((SequencesKt___SequencesKt$runningReduce$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0062  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object $result) {
        SequencesKt___SequencesKt$runningReduce$1<S> sequencesKt___SequencesKt$runningReduce$1;
        SequenceScope $this$sequence;
        Iterator iterator;
        Object accumulator;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                sequencesKt___SequencesKt$runningReduce$1 = this;
                $this$sequence = (SequenceScope) sequencesKt___SequencesKt$runningReduce$1.L$0;
                iterator = sequencesKt___SequencesKt$runningReduce$1.$this_runningReduce.iterator();
                if (iterator.hasNext()) {
                    accumulator = iterator.next();
                    sequencesKt___SequencesKt$runningReduce$1.L$0 = $this$sequence;
                    sequencesKt___SequencesKt$runningReduce$1.L$1 = iterator;
                    sequencesKt___SequencesKt$runningReduce$1.L$2 = accumulator;
                    sequencesKt___SequencesKt$runningReduce$1.label = 1;
                    if ($this$sequence.yield(accumulator, sequencesKt___SequencesKt$runningReduce$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    while (iterator.hasNext()) {
                        accumulator = sequencesKt___SequencesKt$runningReduce$1.$operation.invoke(accumulator, iterator.next());
                        sequencesKt___SequencesKt$runningReduce$1.L$0 = $this$sequence;
                        sequencesKt___SequencesKt$runningReduce$1.L$1 = iterator;
                        sequencesKt___SequencesKt$runningReduce$1.L$2 = accumulator;
                        sequencesKt___SequencesKt$runningReduce$1.label = 2;
                        if ($this$sequence.yield(accumulator, sequencesKt___SequencesKt$runningReduce$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                }
                return Unit.INSTANCE;
            case 1:
                sequencesKt___SequencesKt$runningReduce$1 = this;
                accumulator = sequencesKt___SequencesKt$runningReduce$1.L$2;
                iterator = (Iterator) sequencesKt___SequencesKt$runningReduce$1.L$1;
                $this$sequence = (SequenceScope) sequencesKt___SequencesKt$runningReduce$1.L$0;
                ResultKt.throwOnFailure($result);
                while (iterator.hasNext()) {
                }
                return Unit.INSTANCE;
            case 2:
                sequencesKt___SequencesKt$runningReduce$1 = this;
                accumulator = sequencesKt___SequencesKt$runningReduce$1.L$2;
                iterator = (Iterator) sequencesKt___SequencesKt$runningReduce$1.L$1;
                $this$sequence = (SequenceScope) sequencesKt___SequencesKt$runningReduce$1.L$0;
                ResultKt.throwOnFailure($result);
                while (iterator.hasNext()) {
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
