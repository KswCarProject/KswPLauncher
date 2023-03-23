package io.reactivex.internal.schedulers;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.functions.Functions;
import io.reactivex.schedulers.SchedulerRunnableIntrospection;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;

abstract class AbstractDirectTask extends AtomicReference<Future<?>> implements Disposable, SchedulerRunnableIntrospection {
    protected static final FutureTask<Void> DISPOSED = new FutureTask<>(Functions.EMPTY_RUNNABLE, (Object) null);
    protected static final FutureTask<Void> FINISHED = new FutureTask<>(Functions.EMPTY_RUNNABLE, (Object) null);
    private static final long serialVersionUID = 1811839108042568751L;
    protected final Runnable runnable;
    protected Thread runner;

    AbstractDirectTask(Runnable runnable2) {
        this.runnable = runnable2;
    }

    public final void dispose() {
        Future<?> future;
        Future<?> f = (Future) get();
        if (f != FINISHED && f != (future = DISPOSED) && compareAndSet(f, future) && f != null) {
            f.cancel(this.runner != Thread.currentThread());
        }
    }

    public final boolean isDisposed() {
        Future<?> f = (Future) get();
        return f == FINISHED || f == DISPOSED;
    }

    public final void setFuture(Future<?> future) {
        Future<?> f;
        do {
            f = (Future) get();
            if (f != FINISHED) {
                if (f == DISPOSED) {
                    future.cancel(this.runner != Thread.currentThread());
                    return;
                }
            } else {
                return;
            }
        } while (!compareAndSet(f, future));
    }

    public Runnable getWrappedRunnable() {
        return this.runnable;
    }
}
