package kotlin.sequences;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u0001H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "T", "index", "", "element", "invoke", "(ILjava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* compiled from: _Sequences.kt */
final class SequencesKt___SequencesKt$onEachIndexed$1 extends Lambda implements Function2<Integer, T, T> {
    final /* synthetic */ Function2<Integer, T, Unit> $action;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SequencesKt___SequencesKt$onEachIndexed$1(Function2<? super Integer, ? super T, Unit> function2) {
        super(2);
        this.$action = function2;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2) {
        return invoke(((Number) p1).intValue(), p2);
    }

    public final T invoke(int index, T element) {
        this.$action.invoke(Integer.valueOf(index), element);
        return element;
    }
}
