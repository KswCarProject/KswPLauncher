package kotlin.collections.unsigned;

import com.wits.ksw.launcher.view.benzgs.BenzConfig;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;

/* compiled from: _UArraysJvm.kt */
@Metadata(m25d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000e*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004J\u001b\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0096\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\f\u0010\rJ\u001e\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0006H\u0096\u0002\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0013\u0010\u0014J\b\u0010\u0015\u001a\u00020\nH\u0016J\u001a\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0017\u0010\u0014R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\b\u00f8\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00a8\u0006\u0018"}, m24d2 = {"kotlin/collections/unsigned/UArraysKt___UArraysJvmKt$asList$4", "Lkotlin/collections/AbstractList;", "Lkotlin/UShort;", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "size", "", "getSize", "()I", "contains", "", "element", "contains-xj2QHRw", "(S)Z", "get", BenzConfig.INDEX, "get-Mh2AYeg", "(I)S", "indexOf", "indexOf-xj2QHRw", "(S)I", "isEmpty", "lastIndexOf", "lastIndexOf-xj2QHRw", "kotlin-stdlib"}, m23k = 1, m22mv = {1, 6, 0}, m20xi = 48)
/* loaded from: classes.dex */
public final class UArraysKt___UArraysJvmKt$asList$4 extends AbstractList<UShort> implements RandomAccess {
    final /* synthetic */ short[] $this_asList;

    UArraysKt___UArraysJvmKt$asList$4(short[] $receiver) {
        this.$this_asList = $receiver;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object element) {
        if (element instanceof UShort) {
            return m584containsxj2QHRw(((UShort) element).m420unboximpl());
        }
        return false;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ Object get(int index) {
        return UShort.m365boximpl(m585getMh2AYeg(index));
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object element) {
        if (element instanceof UShort) {
            return m586indexOfxj2QHRw(((UShort) element).m420unboximpl());
        }
        return -1;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object element) {
        if (element instanceof UShort) {
            return m587lastIndexOfxj2QHRw(((UShort) element).m420unboximpl());
        }
        return -1;
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return UShortArray.m429getSizeimpl(this.$this_asList);
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return UShortArray.m431isEmptyimpl(this.$this_asList);
    }

    /* renamed from: contains-xj2QHRw  reason: not valid java name */
    public boolean m584containsxj2QHRw(short element) {
        return UShortArray.m424containsxj2QHRw(this.$this_asList, element);
    }

    /* renamed from: get-Mh2AYeg  reason: not valid java name */
    public short m585getMh2AYeg(int index) {
        return UShortArray.m428getMh2AYeg(this.$this_asList, index);
    }

    /* renamed from: indexOf-xj2QHRw  reason: not valid java name */
    public int m586indexOfxj2QHRw(short element) {
        return ArraysKt.indexOf(this.$this_asList, element);
    }

    /* renamed from: lastIndexOf-xj2QHRw  reason: not valid java name */
    public int m587lastIndexOfxj2QHRw(short element) {
        return ArraysKt.lastIndexOf(this.$this_asList, element);
    }
}
