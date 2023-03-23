package kotlin.collections.unsigned;

import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;

@Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000e*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004J\u001b\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0002ø\u0001\u0000¢\u0006\u0004\b\f\u0010\rJ\u001e\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0006H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014J\b\u0010\u0015\u001a\u00020\nH\u0016J\u001a\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0014R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bø\u0001\u0000\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u0018"}, d2 = {"kotlin/collections/unsigned/UArraysKt___UArraysJvmKt$asList$2", "Lkotlin/collections/AbstractList;", "Lkotlin/ULong;", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "size", "", "getSize", "()I", "contains", "", "element", "contains-VKZWuLQ", "(J)Z", "get", "index", "get-s-VKNKU", "(I)J", "indexOf", "indexOf-VKZWuLQ", "(J)I", "isEmpty", "lastIndexOf", "lastIndexOf-VKZWuLQ", "kotlin-stdlib"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: _UArraysJvm.kt */
public final class UArraysKt___UArraysJvmKt$asList$2 extends AbstractList<ULong> implements RandomAccess {
    final /* synthetic */ long[] $this_asList;

    UArraysKt___UArraysJvmKt$asList$2(long[] $receiver) {
        this.$this_asList = $receiver;
    }

    public final /* bridge */ boolean contains(Object element) {
        if (!(element instanceof ULong)) {
            return false;
        }
        return m488containsVKZWuLQ(((ULong) element).m228unboximpl());
    }

    public /* bridge */ /* synthetic */ Object get(int index) {
        return ULong.m171boximpl(m489getsVKNKU(index));
    }

    public final /* bridge */ int indexOf(Object element) {
        if (!(element instanceof ULong)) {
            return -1;
        }
        return m490indexOfVKZWuLQ(((ULong) element).m228unboximpl());
    }

    public final /* bridge */ int lastIndexOf(Object element) {
        if (!(element instanceof ULong)) {
            return -1;
        }
        return m491lastIndexOfVKZWuLQ(((ULong) element).m228unboximpl());
    }

    public int getSize() {
        return ULongArray.m237getSizeimpl(this.$this_asList);
    }

    public boolean isEmpty() {
        return ULongArray.m239isEmptyimpl(this.$this_asList);
    }

    /* renamed from: contains-VKZWuLQ  reason: not valid java name */
    public boolean m488containsVKZWuLQ(long element) {
        return ULongArray.m232containsVKZWuLQ(this.$this_asList, element);
    }

    /* renamed from: get-s-VKNKU  reason: not valid java name */
    public long m489getsVKNKU(int index) {
        return ULongArray.m236getsVKNKU(this.$this_asList, index);
    }

    /* renamed from: indexOf-VKZWuLQ  reason: not valid java name */
    public int m490indexOfVKZWuLQ(long element) {
        return ArraysKt.indexOf(this.$this_asList, element);
    }

    /* renamed from: lastIndexOf-VKZWuLQ  reason: not valid java name */
    public int m491lastIndexOfVKZWuLQ(long element) {
        return ArraysKt.lastIndexOf(this.$this_asList, element);
    }
}
