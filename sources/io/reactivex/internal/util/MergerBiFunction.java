package io.reactivex.internal.util;

import io.reactivex.functions.BiFunction;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class MergerBiFunction<T> implements BiFunction<List<T>, List<T>, List<T>> {
    final Comparator<? super T> comparator;

    @Override // io.reactivex.functions.BiFunction
    public /* bridge */ /* synthetic */ Object apply(Object obj, Object obj2) throws Exception {
        return apply((List) ((List) obj), (List) ((List) obj2));
    }

    public MergerBiFunction(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    public List<T> apply(List<T> a, List<T> b) throws Exception {
        int n = a.size() + b.size();
        if (n == 0) {
            return new ArrayList();
        }
        List<T> both = new ArrayList<>(n);
        Iterator<T> at = a.iterator();
        Iterator<T> bt = b.iterator();
        T s1 = at.hasNext() ? at.next() : null;
        T s2 = bt.hasNext() ? bt.next() : null;
        while (s1 != null && s2 != null) {
            if (this.comparator.compare(s1, s2) < 0) {
                both.add(s1);
                s1 = at.hasNext() ? at.next() : null;
            } else {
                both.add(s2);
                s2 = bt.hasNext() ? bt.next() : null;
            }
        }
        if (s1 != null) {
            both.add(s1);
            while (at.hasNext()) {
                both.add(at.next());
            }
        } else {
            both.add(s2);
            while (bt.hasNext()) {
                both.add(bt.next());
            }
        }
        return both;
    }
}
