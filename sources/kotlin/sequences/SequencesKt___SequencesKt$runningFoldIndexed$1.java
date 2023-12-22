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

/* JADX INFO: Add missing generic type declarations: [R] */
/* compiled from: _Sequences.kt */
@Metadata(m25d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H\u008a@"}, m24d2 = {"<anonymous>", "", "T", "R", "Lkotlin/sequences/SequenceScope;"}, m23k = 3, m22mv = {1, 6, 0}, m20xi = 48)
@DebugMetadata(m16c = "kotlin.sequences.SequencesKt___SequencesKt$runningFoldIndexed$1", m15f = "_Sequences.kt", m14i = {0, 1, 1, 1}, m13l = {2143, 2148}, m12m = "invokeSuspend", m11n = {"$this$sequence", "$this$sequence", "accumulator", BenzConfig.INDEX}, m10s = {"L$0", "L$0", "L$1", "I$0"})
/* loaded from: classes.dex */
final class SequencesKt___SequencesKt$runningFoldIndexed$1<R> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ R $initial;
    final /* synthetic */ Function3<Integer, R, T, R> $operation;
    final /* synthetic */ Sequence<T> $this_runningFoldIndexed;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    SequencesKt___SequencesKt$runningFoldIndexed$1(R r, Sequence<? extends T> sequence, Function3<? super Integer, ? super R, ? super T, ? extends R> function3, Continuation<? super SequencesKt___SequencesKt$runningFoldIndexed$1> continuation) {
        super(2, continuation);
        this.$initial = r;
        this.$this_runningFoldIndexed = sequence;
        this.$operation = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SequencesKt___SequencesKt$runningFoldIndexed$1 sequencesKt___SequencesKt$runningFoldIndexed$1 = new SequencesKt___SequencesKt$runningFoldIndexed$1(this.$initial, this.$this_runningFoldIndexed, this.$operation, continuation);
        sequencesKt___SequencesKt$runningFoldIndexed$1.L$0 = obj;
        return sequencesKt___SequencesKt$runningFoldIndexed$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return invoke((SequenceScope) ((SequenceScope) obj), continuation);
    }

    public final Object invoke(SequenceScope<? super R> sequenceScope, Continuation<? super Unit> continuation) {
        return ((SequencesKt___SequencesKt$runningFoldIndexed$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        SequencesKt___SequencesKt$runningFoldIndexed$1<R> sequencesKt___SequencesKt$runningFoldIndexed$1;
        SequenceScope $this$sequence;
        Object accumulator;
        SequenceScope $this$sequence2;
        int index;
        Iterator it;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                sequencesKt___SequencesKt$runningFoldIndexed$1 = this;
                $this$sequence = (SequenceScope) sequencesKt___SequencesKt$runningFoldIndexed$1.L$0;
                sequencesKt___SequencesKt$runningFoldIndexed$1.L$0 = $this$sequence;
                sequencesKt___SequencesKt$runningFoldIndexed$1.label = 1;
                if ($this$sequence.yield(sequencesKt___SequencesKt$runningFoldIndexed$1.$initial, sequencesKt___SequencesKt$runningFoldIndexed$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                accumulator = sequencesKt___SequencesKt$runningFoldIndexed$1.$initial;
                $this$sequence2 = $this$sequence;
                index = 0;
                it = sequencesKt___SequencesKt$runningFoldIndexed$1.$this_runningFoldIndexed.iterator();
                break;
            case 1:
                sequencesKt___SequencesKt$runningFoldIndexed$1 = this;
                $this$sequence = (SequenceScope) sequencesKt___SequencesKt$runningFoldIndexed$1.L$0;
                ResultKt.throwOnFailure($result);
                accumulator = sequencesKt___SequencesKt$runningFoldIndexed$1.$initial;
                $this$sequence2 = $this$sequence;
                index = 0;
                it = sequencesKt___SequencesKt$runningFoldIndexed$1.$this_runningFoldIndexed.iterator();
                break;
            case 2:
                sequencesKt___SequencesKt$runningFoldIndexed$1 = this;
                index = sequencesKt___SequencesKt$runningFoldIndexed$1.I$0;
                it = (Iterator) sequencesKt___SequencesKt$runningFoldIndexed$1.L$2;
                accumulator = sequencesKt___SequencesKt$runningFoldIndexed$1.L$1;
                $this$sequence2 = (SequenceScope) sequencesKt___SequencesKt$runningFoldIndexed$1.L$0;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        while (it.hasNext()) {
            Object element = it.next();
            Function3<Integer, R, T, R> function3 = sequencesKt___SequencesKt$runningFoldIndexed$1.$operation;
            int index2 = index + 1;
            if (index < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            accumulator = function3.invoke(boxing.boxInt(index), accumulator, element);
            sequencesKt___SequencesKt$runningFoldIndexed$1.L$0 = $this$sequence2;
            sequencesKt___SequencesKt$runningFoldIndexed$1.L$1 = accumulator;
            sequencesKt___SequencesKt$runningFoldIndexed$1.L$2 = it;
            sequencesKt___SequencesKt$runningFoldIndexed$1.I$0 = index2;
            sequencesKt___SequencesKt$runningFoldIndexed$1.label = 2;
            if ($this$sequence2.yield(accumulator, sequencesKt___SequencesKt$runningFoldIndexed$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
            index = index2;
        }
        return Unit.INSTANCE;
    }
}
