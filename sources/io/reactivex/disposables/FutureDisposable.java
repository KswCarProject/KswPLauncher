package io.reactivex.disposables;

import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
final class FutureDisposable extends AtomicReference<Future<?>> implements Disposable {
    private static final long serialVersionUID = 6545242830671168775L;
    private final boolean allowInterrupt;

    FutureDisposable(Future<?> run, boolean allowInterrupt) {
        super(run);
        this.allowInterrupt = allowInterrupt;
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        Future<?> f = get();
        return f == null || f.isDone();
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        Future<?> f = getAndSet(null);
        if (f != null) {
            f.cancel(this.allowInterrupt);
        }
    }
}
