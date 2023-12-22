package kotlin.sequences;

import com.wits.ksw.launcher.view.benzgs.BenzConfig;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.boxing;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: Add missing generic type declarations: [S] */
/* compiled from: _Sequences.kt */
@Metadata(m25d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\u0003*\u0002H\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u008a@"}, m24d2 = {"<anonymous>", "", "S", "T", "Lkotlin/sequences/SequenceScope;"}, m23k = 3, m22mv = {1, 6, 0}, m20xi = 48)
@DebugMetadata(m16c = "kotlin.sequences.SequencesKt___SequencesKt$runningReduceIndexed$1", m15f = "_Sequences.kt", m14i = {0, 0, 0, 1, 1, 1, 1}, m13l = {2202, 2206}, m12m = "invokeSuspend", m11n = {"$this$sequence", "iterator", "accumulator", "$this$sequence", "iterator", "accumulator", BenzConfig.INDEX}, m10s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "I$0"})
/* loaded from: classes.dex */
final class SequencesKt___SequencesKt$runningReduceIndexed$1<S> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super S>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function3<Integer, S, T, S> $operation;
    final /* synthetic */ Sequence<T> $this_runningReduceIndexed;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    SequencesKt___SequencesKt$runningReduceIndexed$1(Sequence<? extends T> sequence, Function3<? super Integer, ? super S, ? super T, ? extends S> function3, Continuation<? super SequencesKt___SequencesKt$runningReduceIndexed$1> continuation) {
        super(2, continuation);
        this.$this_runningReduceIndexed = sequence;
        this.$operation = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SequencesKt___SequencesKt$runningReduceIndexed$1 sequencesKt___SequencesKt$runningReduceIndexed$1 = new SequencesKt___SequencesKt$runningReduceIndexed$1(this.$this_runningReduceIndexed, this.$operation, continuation);
        sequencesKt___SequencesKt$runningReduceIndexed$1.L$0 = obj;
        return sequencesKt___SequencesKt$runningReduceIndexed$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return invoke((SequenceScope) ((SequenceScope) obj), continuation);
    }

    public final Object invoke(SequenceScope<? super S> sequenceScope, Continuation<? super Unit> continuation) {
        return ((SequencesKt___SequencesKt$runningReduceIndexed$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:16:0x006c  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object $result) {
        SequencesKt___SequencesKt$runningReduceIndexed$1 accumulator;
        SequenceScope $this$sequence;
        Iterator iterator;
        Object accumulator2;
        Iterator iterator2;
        int index;
        SequenceScope $this$sequence2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                accumulator = this;
                $this$sequence = (SequenceScope) accumulator.L$0;
                iterator = accumulator.$this_runningReduceIndexed.iterator();
                if (iterator.hasNext()) {
                    accumulator2 = iterator.next();
                    accumulator.L$0 = $this$sequence;
                    accumulator.L$1 = iterator;
                    accumulator.L$2 = accumulator2;
                    accumulator.label = 1;
                    if ($this$sequence.yield(accumulator2, accumulator) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    SequenceScope sequenceScope = $this$sequence;
                    iterator2 = iterator;
                    index = 1;
                    $this$sequence2 = sequenceScope;
                    while (iterator2.hasNext()) {
                        Function3<Integer, S, T, S> function3 = accumulator.$operation;
                        int index2 = index + 1;
                        if (index < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        Object accumulator3 = function3.invoke(boxing.boxInt(index), accumulator2, iterator2.next());
                        accumulator.L$0 = $this$sequence2;
                        accumulator.L$1 = iterator2;
                        accumulator.L$2 = accumulator3;
                        accumulator.I$0 = index2;
                        accumulator.label = 2;
                        if ($this$sequence2.yield(accumulator3, accumulator) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        accumulator2 = accumulator3;
                        index = index2;
                    }
                }
                return Unit.INSTANCE;
            case 1:
                accumulator = this;
                accumulator2 = accumulator.L$2;
                iterator = (Iterator) accumulator.L$1;
                $this$sequence = (SequenceScope) accumulator.L$0;
                ResultKt.throwOnFailure($result);
                SequenceScope sequenceScope2 = $this$sequence;
                iterator2 = iterator;
                index = 1;
                $this$sequence2 = sequenceScope2;
                while (iterator2.hasNext()) {
                }
                return Unit.INSTANCE;
            case 2:
                accumulator = this;
                int index3 = accumulator.I$0;
                Object accumulator4 = accumulator.L$2;
                iterator2 = (Iterator) accumulator.L$1;
                $this$sequence2 = (SequenceScope) accumulator.L$0;
                ResultKt.throwOnFailure($result);
                index = index3;
                accumulator2 = accumulator4;
                while (iterator2.hasNext()) {
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
