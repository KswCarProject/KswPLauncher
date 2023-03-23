package kotlin.collections.unsigned;

import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;

@Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\r*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004J\u001b\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0002ø\u0001\u0000¢\u0006\u0004\b\f\u0010\rJ\u001e\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0006H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0011J\b\u0010\u0014\u001a\u00020\nH\u0016J\u001a\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0011R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bø\u0001\u0000\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u0017"}, d2 = {"kotlin/collections/unsigned/UArraysKt___UArraysJvmKt$asList$1", "Lkotlin/collections/AbstractList;", "Lkotlin/UInt;", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "size", "", "getSize", "()I", "contains", "", "element", "contains-WZ4Q5Ns", "(I)Z", "get", "index", "get-pVg5ArA", "(I)I", "indexOf", "indexOf-WZ4Q5Ns", "isEmpty", "lastIndexOf", "lastIndexOf-WZ4Q5Ns", "kotlin-stdlib"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: _UArraysJvm.kt */
public final class UArraysKt___UArraysJvmKt$asList$1 extends AbstractList<UInt> implements RandomAccess {
    final /* synthetic */ int[] $this_asList;

    UArraysKt___UArraysJvmKt$asList$1(int[] $receiver) {
        this.$this_asList = $receiver;
    }

    public final /* bridge */ boolean contains(Object element) {
        if (!(element instanceof UInt)) {
            return false;
        }
        return m484containsWZ4Q5Ns(((UInt) element).m150unboximpl());
    }

    public /* bridge */ /* synthetic */ Object get(int index) {
        return UInt.m93boximpl(m485getpVg5ArA(index));
    }

    public final /* bridge */ int indexOf(Object element) {
        if (!(element instanceof UInt)) {
            return -1;
        }
        return m486indexOfWZ4Q5Ns(((UInt) element).m150unboximpl());
    }

    public final /* bridge */ int lastIndexOf(Object element) {
        if (!(element instanceof UInt)) {
            return -1;
        }
        return m487lastIndexOfWZ4Q5Ns(((UInt) element).m150unboximpl());
    }

    public int getSize() {
        return UIntArray.m159getSizeimpl(this.$this_asList);
    }

    public boolean isEmpty() {
        return UIntArray.m161isEmptyimpl(this.$this_asList);
    }

    /* renamed from: contains-WZ4Q5Ns  reason: not valid java name */
    public boolean m484containsWZ4Q5Ns(int element) {
        return UIntArray.m154containsWZ4Q5Ns(this.$this_asList, element);
    }

    /* renamed from: get-pVg5ArA  reason: not valid java name */
    public int m485getpVg5ArA(int index) {
        return UIntArray.m158getpVg5ArA(this.$this_asList, index);
    }

    /* renamed from: indexOf-WZ4Q5Ns  reason: not valid java name */
    public int m486indexOfWZ4Q5Ns(int element) {
        return ArraysKt.indexOf(this.$this_asList, element);
    }

    /* renamed from: lastIndexOf-WZ4Q5Ns  reason: not valid java name */
    public int m487lastIndexOfWZ4Q5Ns(int element) {
        return ArraysKt.lastIndexOf(this.$this_asList, element);
    }
}
