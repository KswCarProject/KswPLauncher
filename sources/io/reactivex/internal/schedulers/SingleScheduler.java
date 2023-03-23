package io.reactivex.internal.schedulers;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleScheduler extends Scheduler {
    private static final String KEY_SINGLE_PRIORITY = "rx2.single-priority";
    static final ScheduledExecutorService SHUTDOWN;
    static final RxThreadFactory SINGLE_THREAD_FACTORY = new RxThreadFactory(THREAD_NAME_PREFIX, Math.max(1, Math.min(10, Integer.getInteger(KEY_SINGLE_PRIORITY, 5).intValue())), true);
    private static final String THREAD_NAME_PREFIX = "RxSingleScheduler";
    final AtomicReference<ScheduledExecutorService> executor;
    final ThreadFactory threadFactory;

    static {
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(0);
        SHUTDOWN = newScheduledThreadPool;
        newScheduledThreadPool.shutdown();
    }

    public SingleScheduler() {
        this(SINGLE_THREAD_FACTORY);
    }

    public SingleScheduler(ThreadFactory threadFactory2) {
        AtomicReference<ScheduledExecutorService> atomicReference = new AtomicReference<>();
        this.executor = atomicReference;
        this.threadFactory = threadFactory2;
        atomicReference.lazySet(createExecutor(threadFactory2));
    }

    static ScheduledExecutorService createExecutor(ThreadFactory threadFactory2) {
        return SchedulerPoolFactory.create(threadFactory2);
    }

    public void start() {
        ScheduledExecutorService current;
        ScheduledExecutorService next = null;
        do {
            current = this.executor.get();
            if (current != SHUTDOWN) {
                if (next != null) {
                    next.shutdown();
                    return;
                }
                return;
            } else if (next == null) {
                next = createExecutor(this.threadFactory);
            }
        } while (!this.executor.compareAndSet(current, next));
    }

    public void shutdown() {
        ScheduledExecutorService current;
        ScheduledExecutorService current2 = this.executor.get();
        ScheduledExecutorService scheduledExecutorService = SHUTDOWN;
        if (current2 != scheduledExecutorService && (current = this.executor.getAndSet(scheduledExecutorService)) != scheduledExecutorService) {
            current.shutdownNow();
        }
    }

    public Scheduler.Worker createWorker() {
        return new ScheduledWorker(this.executor.get());
    }

    public Disposable scheduleDirect(Runnable run, long delay, TimeUnit unit) {
        Future<?> f;
        ScheduledDirectTask task = new ScheduledDirectTask(RxJavaPlugins.onSchedule(run));
        if (delay <= 0) {
            try {
                f = this.executor.get().submit(task);
            } catch (RejectedExecutionException ex) {
                RxJavaPlugins.onError(ex);
                return EmptyDisposable.INSTANCE;
            }
        } else {
            f = this.executor.get().schedule(task, delay, unit);
        }
        task.setFuture(f);
        return task;
    }

    public Disposable schedulePeriodicallyDirect(Runnable run, long initialDelay, long period, TimeUnit unit) {
        Future<?> f;
        Runnable decoratedRun = RxJavaPlugins.onSchedule(run);
        if (period <= 0) {
            ScheduledExecutorService exec = this.executor.get();
            InstantPeriodicTask periodicWrapper = new InstantPeriodicTask(decoratedRun, exec);
            if (initialDelay <= 0) {
                try {
                    f = exec.submit(periodicWrapper);
                } catch (RejectedExecutionException ex) {
                    RxJavaPlugins.onError(ex);
                    return EmptyDisposable.INSTANCE;
                }
            } else {
                f = exec.schedule(periodicWrapper, initialDelay, unit);
            }
            periodicWrapper.setFirst(f);
            return periodicWrapper;
        }
        ScheduledDirectPeriodicTask task = new ScheduledDirectPeriodicTask(decoratedRun);
        try {
            task.setFuture(this.executor.get().scheduleAtFixedRate(task, initialDelay, period, unit));
            return task;
        } catch (RejectedExecutionException ex2) {
            RxJavaPlugins.onError(ex2);
            return EmptyDisposable.INSTANCE;
        }
    }

    static final class ScheduledWorker extends Scheduler.Worker {
        volatile boolean disposed;
        final ScheduledExecutorService executor;
        final CompositeDisposable tasks = new CompositeDisposable();

        ScheduledWorker(ScheduledExecutorService executor2) {
            this.executor = executor2;
        }

        public Disposable schedule(Runnable run, long delay, TimeUnit unit) {
            Future<?> f;
            if (this.disposed) {
                return EmptyDisposable.INSTANCE;
            }
            ScheduledRunnable sr = new ScheduledRunnable(RxJavaPlugins.onSchedule(run), this.tasks);
            this.tasks.add(sr);
            if (delay <= 0) {
                try {
                    f = this.executor.submit(sr);
                } catch (RejectedExecutionException ex) {
                    dispose();
                    RxJavaPlugins.onError(ex);
                    return EmptyDisposable.INSTANCE;
                }
            } else {
                f = this.executor.schedule(sr, delay, unit);
            }
            sr.setFuture(f);
            return sr;
        }

        public void dispose() {
            if (!this.disposed) {
                this.disposed = true;
                this.tasks.dispose();
            }
        }

        public boolean isDisposed() {
            return this.disposed;
        }
    }
}
