package io.reactivex.internal.disposables;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Cancellable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class CancellableDisposable extends AtomicReference<Cancellable> implements Disposable {
    private static final long serialVersionUID = 5718521705281392066L;

    public CancellableDisposable(Cancellable cancellable) {
        super(cancellable);
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return get() == null;
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        Cancellable c;
        if (get() != null && (c = getAndSet(null)) != null) {
            try {
                c.cancel();
            } catch (Exception ex) {
                Exceptions.throwIfFatal(ex);
                RxJavaPlugins.onError(ex);
            }
        }
    }
}
