package kotlin.sequences;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.BrittleContainsOptimizationKt;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u000f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0002¨\u0006\u0004"}, d2 = {"kotlin/sequences/SequencesKt___SequencesKt$minus$4", "Lkotlin/sequences/Sequence;", "iterator", "", "kotlin-stdlib"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: _Sequences.kt */
public final class SequencesKt___SequencesKt$minus$4 implements Sequence<T> {
    final /* synthetic */ Sequence<T> $elements;
    final /* synthetic */ Sequence<T> $this_minus;

    SequencesKt___SequencesKt$minus$4(Sequence<? extends T> $elements2, Sequence<? extends T> $receiver) {
        this.$elements = $elements2;
        this.$this_minus = $receiver;
    }

    public Iterator<T> iterator() {
        Collection other = BrittleContainsOptimizationKt.convertToSetForSetOperation(this.$elements);
        if (other.isEmpty()) {
            return this.$this_minus.iterator();
        }
        return SequencesKt.filterNot(this.$this_minus, new SequencesKt___SequencesKt$minus$4$iterator$1(other)).iterator();
    }
}
