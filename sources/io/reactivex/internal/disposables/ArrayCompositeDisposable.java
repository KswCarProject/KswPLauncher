package io.reactivex.internal.disposables;

import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* loaded from: classes.dex */
public final class ArrayCompositeDisposable extends AtomicReferenceArray<Disposable> implements Disposable {
    private static final long serialVersionUID = 2746389416410565408L;

    public ArrayCompositeDisposable(int capacity) {
        super(capacity);
    }

    public boolean setResource(int index, Disposable resource) {
        Disposable o;
        do {
            o = get(index);
            if (o == DisposableHelper.DISPOSED) {
                resource.dispose();
                return false;
            }
        } while (!compareAndSet(index, o, resource));
        if (o != null) {
            o.dispose();
            return true;
        }
        return true;
    }

    public Disposable replaceResource(int index, Disposable resource) {
        Disposable o;
        do {
            o = get(index);
            if (o == DisposableHelper.DISPOSED) {
                resource.dispose();
                return null;
            }
        } while (!compareAndSet(index, o, resource));
        return o;
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        if (get(0) != DisposableHelper.DISPOSED) {
            int s = length();
            for (int i = 0; i < s; i++) {
                Disposable o = get(i);
                if (o != DisposableHelper.DISPOSED) {
                    Disposable o2 = getAndSet(i, DisposableHelper.DISPOSED);
                    Disposable o3 = o2;
                    if (o3 != DisposableHelper.DISPOSED && o3 != null) {
                        o3.dispose();
                    }
                }
            }
        }
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return get(0) == DisposableHelper.DISPOSED;
    }
}
