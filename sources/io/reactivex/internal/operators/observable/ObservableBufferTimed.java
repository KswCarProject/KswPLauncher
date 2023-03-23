package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.observers.QueueDrainObserver;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.util.QueueDrainHelper;
import io.reactivex.observers.SerializedObserver;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableBufferTimed<T, U extends Collection<? super T>> extends AbstractObservableWithUpstream<T, U> {
    final Callable<U> bufferSupplier;
    final int maxSize;
    final boolean restartTimerOnMaxSize;
    final Scheduler scheduler;
    final long timeskip;
    final long timespan;
    final TimeUnit unit;

    public ObservableBufferTimed(ObservableSource<T> source, long timespan2, long timeskip2, TimeUnit unit2, Scheduler scheduler2, Callable<U> bufferSupplier2, int maxSize2, boolean restartTimerOnMaxSize2) {
        super(source);
        this.timespan = timespan2;
        this.timeskip = timeskip2;
        this.unit = unit2;
        this.scheduler = scheduler2;
        this.bufferSupplier = bufferSupplier2;
        this.maxSize = maxSize2;
        this.restartTimerOnMaxSize = restartTimerOnMaxSize2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Observer<? super U> t) {
        if (this.timespan == this.timeskip && this.maxSize == Integer.MAX_VALUE) {
            this.source.subscribe(new BufferExactUnboundedObserver(new SerializedObserver(t), this.bufferSupplier, this.timespan, this.unit, this.scheduler));
            return;
        }
        Scheduler.Worker w = this.scheduler.createWorker();
        if (this.timespan == this.timeskip) {
            this.source.subscribe(new BufferExactBoundedObserver(new SerializedObserver(t), this.bufferSupplier, this.timespan, this.unit, this.maxSize, this.restartTimerOnMaxSize, w));
        } else {
            this.source.subscribe(new BufferSkipBoundedObserver(new SerializedObserver(t), this.bufferSupplier, this.timespan, this.timeskip, this.unit, w));
        }
    }

    static final class BufferExactUnboundedObserver<T, U extends Collection<? super T>> extends QueueDrainObserver<T, U, U> implements Runnable, Disposable {
        U buffer;
        final Callable<U> bufferSupplier;
        final Scheduler scheduler;
        final AtomicReference<Disposable> timer = new AtomicReference<>();
        final long timespan;
        final TimeUnit unit;
        Disposable upstream;

        BufferExactUnboundedObserver(Observer<? super U> actual, Callable<U> bufferSupplier2, long timespan2, TimeUnit unit2, Scheduler scheduler2) {
            super(actual, new MpscLinkedQueue());
            this.bufferSupplier = bufferSupplier2;
            this.timespan = timespan2;
            this.unit = unit2;
            this.scheduler = scheduler2;
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.upstream, d)) {
                this.upstream = d;
                try {
                    this.buffer = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The buffer supplied is null");
                    this.downstream.onSubscribe(this);
                    if (!this.cancelled) {
                        Scheduler scheduler2 = this.scheduler;
                        long j = this.timespan;
                        Disposable task = scheduler2.schedulePeriodicallyDirect(this, j, j, this.unit);
                        if (!this.timer.compareAndSet((Object) null, task)) {
                            task.dispose();
                        }
                    }
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    dispose();
                    EmptyDisposable.error(e, (Observer<?>) this.downstream);
                }
            }
        }

        public void onNext(T t) {
            synchronized (this) {
                U b = this.buffer;
                if (b != null) {
                    b.add(t);
                }
            }
        }

        public void onError(Throwable t) {
            synchronized (this) {
                this.buffer = null;
            }
            this.downstream.onError(t);
            DisposableHelper.dispose(this.timer);
        }

        public void onComplete() {
            U b;
            synchronized (this) {
                b = this.buffer;
                this.buffer = null;
            }
            if (b != null) {
                this.queue.offer(b);
                this.done = true;
                if (enter()) {
                    QueueDrainHelper.drainLoop(this.queue, this.downstream, false, (Disposable) null, this);
                }
            }
            DisposableHelper.dispose(this.timer);
        }

        public void dispose() {
            DisposableHelper.dispose(this.timer);
            this.upstream.dispose();
        }

        public boolean isDisposed() {
            return this.timer.get() == DisposableHelper.DISPOSED;
        }

        public void run() {
            U current;
            try {
                U next = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The bufferSupplier returned a null buffer");
                synchronized (this) {
                    current = this.buffer;
                    if (current != null) {
                        this.buffer = next;
                    }
                }
                if (current == null) {
                    DisposableHelper.dispose(this.timer);
                } else {
                    fastPathEmit(current, false, this);
                }
            } catch (Throwable e) {
                Exceptions.throwIfFatal(e);
                this.downstream.onError(e);
                dispose();
            }
        }

        public void accept(Observer<? super U> observer, U v) {
            this.downstream.onNext(v);
        }
    }

    static final class BufferSkipBoundedObserver<T, U extends Collection<? super T>> extends QueueDrainObserver<T, U, U> implements Runnable, Disposable {
        final Callable<U> bufferSupplier;
        final List<U> buffers = new LinkedList();
        final long timeskip;
        final long timespan;
        final TimeUnit unit;
        Disposable upstream;
        final Scheduler.Worker w;

        BufferSkipBoundedObserver(Observer<? super U> actual, Callable<U> bufferSupplier2, long timespan2, long timeskip2, TimeUnit unit2, Scheduler.Worker w2) {
            super(actual, new MpscLinkedQueue());
            this.bufferSupplier = bufferSupplier2;
            this.timespan = timespan2;
            this.timeskip = timeskip2;
            this.unit = unit2;
            this.w = w2;
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.upstream, d)) {
                this.upstream = d;
                try {
                    U b = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The buffer supplied is null");
                    this.buffers.add(b);
                    this.downstream.onSubscribe(this);
                    Scheduler.Worker worker = this.w;
                    long j = this.timeskip;
                    worker.schedulePeriodically(this, j, j, this.unit);
                    this.w.schedule(new RemoveFromBufferEmit(b), this.timespan, this.unit);
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    d.dispose();
                    EmptyDisposable.error(e, (Observer<?>) this.downstream);
                    this.w.dispose();
                }
            }
        }

        public void onNext(T t) {
            synchronized (this) {
                for (U b : this.buffers) {
                    b.add(t);
                }
            }
        }

        public void onError(Throwable t) {
            this.done = true;
            clear();
            this.downstream.onError(t);
            this.w.dispose();
        }

        public void onComplete() {
            List<U> bs;
            synchronized (this) {
                bs = new ArrayList<>(this.buffers);
                this.buffers.clear();
            }
            for (U b : bs) {
                this.queue.offer(b);
            }
            this.done = true;
            if (enter()) {
                QueueDrainHelper.drainLoop(this.queue, this.downstream, false, this.w, this);
            }
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                clear();
                this.upstream.dispose();
                this.w.dispose();
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        /* access modifiers changed from: package-private */
        public void clear() {
            synchronized (this) {
                this.buffers.clear();
            }
        }

        public void run() {
            if (!this.cancelled) {
                try {
                    U b = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The bufferSupplier returned a null buffer");
                    synchronized (this) {
                        if (!this.cancelled) {
                            this.buffers.add(b);
                            this.w.schedule(new RemoveFromBuffer(b), this.timespan, this.unit);
                        }
                    }
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    this.downstream.onError(e);
                    dispose();
                }
            }
        }

        public void accept(Observer<? super U> a, U v) {
            a.onNext(v);
        }

        final class RemoveFromBuffer implements Runnable {
            private final U b;

            RemoveFromBuffer(U b2) {
                this.b = b2;
            }

            public void run() {
                synchronized (BufferSkipBoundedObserver.this) {
                    BufferSkipBoundedObserver.this.buffers.remove(this.b);
                }
                BufferSkipBoundedObserver bufferSkipBoundedObserver = BufferSkipBoundedObserver.this;
                bufferSkipBoundedObserver.fastPathOrderedEmit(this.b, false, bufferSkipBoundedObserver.w);
            }
        }

        final class RemoveFromBufferEmit implements Runnable {
            private final U buffer;

            RemoveFromBufferEmit(U buffer2) {
                this.buffer = buffer2;
            }

            public void run() {
                synchronized (BufferSkipBoundedObserver.this) {
                    BufferSkipBoundedObserver.this.buffers.remove(this.buffer);
                }
                BufferSkipBoundedObserver bufferSkipBoundedObserver = BufferSkipBoundedObserver.this;
                bufferSkipBoundedObserver.fastPathOrderedEmit(this.buffer, false, bufferSkipBoundedObserver.w);
            }
        }
    }

    static final class BufferExactBoundedObserver<T, U extends Collection<? super T>> extends QueueDrainObserver<T, U, U> implements Runnable, Disposable {
        U buffer;
        final Callable<U> bufferSupplier;
        long consumerIndex;
        final int maxSize;
        long producerIndex;
        final boolean restartTimerOnMaxSize;
        Disposable timer;
        final long timespan;
        final TimeUnit unit;
        Disposable upstream;
        final Scheduler.Worker w;

        BufferExactBoundedObserver(Observer<? super U> actual, Callable<U> bufferSupplier2, long timespan2, TimeUnit unit2, int maxSize2, boolean restartOnMaxSize, Scheduler.Worker w2) {
            super(actual, new MpscLinkedQueue());
            this.bufferSupplier = bufferSupplier2;
            this.timespan = timespan2;
            this.unit = unit2;
            this.maxSize = maxSize2;
            this.restartTimerOnMaxSize = restartOnMaxSize;
            this.w = w2;
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.upstream, d)) {
                this.upstream = d;
                try {
                    this.buffer = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The buffer supplied is null");
                    this.downstream.onSubscribe(this);
                    Scheduler.Worker worker = this.w;
                    long j = this.timespan;
                    this.timer = worker.schedulePeriodically(this, j, j, this.unit);
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    d.dispose();
                    EmptyDisposable.error(e, (Observer<?>) this.downstream);
                    this.w.dispose();
                }
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0021, code lost:
            if (r9.restartTimerOnMaxSize == false) goto L_0x0028;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0023, code lost:
            r9.timer.dispose();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0028, code lost:
            fastPathOrderedEmit(r0, false, r9);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
            r1 = (java.util.Collection) io.reactivex.internal.functions.ObjectHelper.requireNonNull(r9.bufferSupplier.call(), "The buffer supplied is null");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x003b, code lost:
            monitor-enter(r9);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
            r9.buffer = r1;
            r9.consumerIndex++;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0043, code lost:
            monitor-exit(r9);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0046, code lost:
            if (r9.restartTimerOnMaxSize == false) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0048, code lost:
            r2 = r9.w;
            r6 = r9.timespan;
            r9.timer = r2.schedulePeriodically(r9, r6, r6, r9.unit);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x005a, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x005b, code lost:
            io.reactivex.exceptions.Exceptions.throwIfFatal(r1);
            r9.downstream.onError(r1);
            dispose();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x0066, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onNext(T r10) {
            /*
                r9 = this;
                monitor-enter(r9)
                U r0 = r9.buffer     // Catch:{ all -> 0x0067 }
                if (r0 != 0) goto L_0x0007
                monitor-exit(r9)     // Catch:{ all -> 0x0067 }
                return
            L_0x0007:
                r0.add(r10)     // Catch:{ all -> 0x0067 }
                int r1 = r0.size()     // Catch:{ all -> 0x0067 }
                int r2 = r9.maxSize     // Catch:{ all -> 0x0067 }
                if (r1 >= r2) goto L_0x0014
                monitor-exit(r9)     // Catch:{ all -> 0x0067 }
                return
            L_0x0014:
                r1 = 0
                r9.buffer = r1     // Catch:{ all -> 0x0067 }
                long r1 = r9.producerIndex     // Catch:{ all -> 0x0067 }
                r3 = 1
                long r1 = r1 + r3
                r9.producerIndex = r1     // Catch:{ all -> 0x0067 }
                monitor-exit(r9)     // Catch:{ all -> 0x0067 }
                boolean r1 = r9.restartTimerOnMaxSize
                if (r1 == 0) goto L_0x0028
                io.reactivex.disposables.Disposable r1 = r9.timer
                r1.dispose()
            L_0x0028:
                r1 = 0
                r9.fastPathOrderedEmit(r0, r1, r9)
                java.util.concurrent.Callable<U> r1 = r9.bufferSupplier     // Catch:{ all -> 0x005a }
                java.lang.Object r1 = r1.call()     // Catch:{ all -> 0x005a }
                java.lang.String r2 = "The buffer supplied is null"
                java.lang.Object r1 = io.reactivex.internal.functions.ObjectHelper.requireNonNull(r1, (java.lang.String) r2)     // Catch:{ all -> 0x005a }
                java.util.Collection r1 = (java.util.Collection) r1     // Catch:{ all -> 0x005a }
                monitor-enter(r9)
                r9.buffer = r1     // Catch:{ all -> 0x0057 }
                long r5 = r9.consumerIndex     // Catch:{ all -> 0x0057 }
                long r5 = r5 + r3
                r9.consumerIndex = r5     // Catch:{ all -> 0x0057 }
                monitor-exit(r9)     // Catch:{ all -> 0x0057 }
                boolean r0 = r9.restartTimerOnMaxSize
                if (r0 == 0) goto L_0x0056
                io.reactivex.Scheduler$Worker r2 = r9.w
                long r6 = r9.timespan
                java.util.concurrent.TimeUnit r8 = r9.unit
                r3 = r9
                r4 = r6
                io.reactivex.disposables.Disposable r0 = r2.schedulePeriodically(r3, r4, r6, r8)
                r9.timer = r0
            L_0x0056:
                return
            L_0x0057:
                r0 = move-exception
                monitor-exit(r9)     // Catch:{ all -> 0x0057 }
                throw r0
            L_0x005a:
                r1 = move-exception
                io.reactivex.exceptions.Exceptions.throwIfFatal(r1)
                io.reactivex.Observer r2 = r9.downstream
                r2.onError(r1)
                r9.dispose()
                return
            L_0x0067:
                r0 = move-exception
                monitor-exit(r9)     // Catch:{ all -> 0x0067 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservableBufferTimed.BufferExactBoundedObserver.onNext(java.lang.Object):void");
        }

        public void onError(Throwable t) {
            synchronized (this) {
                this.buffer = null;
            }
            this.downstream.onError(t);
            this.w.dispose();
        }

        public void onComplete() {
            U b;
            this.w.dispose();
            synchronized (this) {
                b = this.buffer;
                this.buffer = null;
            }
            if (b != null) {
                this.queue.offer(b);
                this.done = true;
                if (enter()) {
                    QueueDrainHelper.drainLoop(this.queue, this.downstream, false, this, this);
                }
            }
        }

        public void accept(Observer<? super U> a, U v) {
            a.onNext(v);
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.dispose();
                this.w.dispose();
                synchronized (this) {
                    this.buffer = null;
                }
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        public void run() {
            try {
                U next = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The bufferSupplier returned a null buffer");
                synchronized (this) {
                    U current = this.buffer;
                    if (current != null) {
                        if (this.producerIndex == this.consumerIndex) {
                            this.buffer = next;
                            fastPathOrderedEmit(current, false, this);
                        }
                    }
                }
            } catch (Throwable e) {
                Exceptions.throwIfFatal(e);
                dispose();
                this.downstream.onError(e);
            }
        }
    }
}
