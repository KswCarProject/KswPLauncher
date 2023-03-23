package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableBufferBoundary<T, U extends Collection<? super T>, Open, Close> extends AbstractObservableWithUpstream<T, U> {
    final Function<? super Open, ? extends ObservableSource<? extends Close>> bufferClose;
    final ObservableSource<? extends Open> bufferOpen;
    final Callable<U> bufferSupplier;

    public ObservableBufferBoundary(ObservableSource<T> source, ObservableSource<? extends Open> bufferOpen2, Function<? super Open, ? extends ObservableSource<? extends Close>> bufferClose2, Callable<U> bufferSupplier2) {
        super(source);
        this.bufferOpen = bufferOpen2;
        this.bufferClose = bufferClose2;
        this.bufferSupplier = bufferSupplier2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Observer<? super U> t) {
        BufferBoundaryObserver<T, U, Open, Close> parent = new BufferBoundaryObserver<>(t, this.bufferOpen, this.bufferClose, this.bufferSupplier);
        t.onSubscribe(parent);
        this.source.subscribe(parent);
    }

    static final class BufferBoundaryObserver<T, C extends Collection<? super T>, Open, Close> extends AtomicInteger implements Observer<T>, Disposable {
        private static final long serialVersionUID = -8466418554264089604L;
        final Function<? super Open, ? extends ObservableSource<? extends Close>> bufferClose;
        final ObservableSource<? extends Open> bufferOpen;
        final Callable<C> bufferSupplier;
        Map<Long, C> buffers = new LinkedHashMap();
        volatile boolean cancelled;
        volatile boolean done;
        final Observer<? super C> downstream;
        final AtomicThrowable errors = new AtomicThrowable();
        long index;
        final CompositeDisposable observers = new CompositeDisposable();
        final SpscLinkedArrayQueue<C> queue = new SpscLinkedArrayQueue<>(Observable.bufferSize());
        final AtomicReference<Disposable> upstream = new AtomicReference<>();

        BufferBoundaryObserver(Observer<? super C> actual, ObservableSource<? extends Open> bufferOpen2, Function<? super Open, ? extends ObservableSource<? extends Close>> bufferClose2, Callable<C> bufferSupplier2) {
            this.downstream = actual;
            this.bufferSupplier = bufferSupplier2;
            this.bufferOpen = bufferOpen2;
            this.bufferClose = bufferClose2;
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.setOnce(this.upstream, d)) {
                BufferOpenObserver<Open> open = new BufferOpenObserver<>(this);
                this.observers.add(open);
                this.bufferOpen.subscribe(open);
            }
        }

        public void onNext(T t) {
            synchronized (this) {
                Map<Long, C> bufs = this.buffers;
                if (bufs != null) {
                    for (C b : bufs.values()) {
                        b.add(t);
                    }
                }
            }
        }

        public void onError(Throwable t) {
            if (this.errors.addThrowable(t)) {
                this.observers.dispose();
                synchronized (this) {
                    this.buffers = null;
                }
                this.done = true;
                drain();
                return;
            }
            RxJavaPlugins.onError(t);
        }

        public void onComplete() {
            this.observers.dispose();
            synchronized (this) {
                Map<Long, C> bufs = this.buffers;
                if (bufs != null) {
                    for (C b : bufs.values()) {
                        this.queue.offer(b);
                    }
                    this.buffers = null;
                    this.done = true;
                    drain();
                }
            }
        }

        public void dispose() {
            if (DisposableHelper.dispose(this.upstream)) {
                this.cancelled = true;
                this.observers.dispose();
                synchronized (this) {
                    this.buffers = null;
                }
                if (getAndIncrement() != 0) {
                    this.queue.clear();
                }
            }
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.upstream.get());
        }

        /* access modifiers changed from: package-private */
        public void open(Open token) {
            try {
                C buf = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The bufferSupplier returned a null Collection");
                ObservableSource<? extends Close> p = (ObservableSource) ObjectHelper.requireNonNull(this.bufferClose.apply(token), "The bufferClose returned a null ObservableSource");
                long idx = this.index;
                this.index = 1 + idx;
                synchronized (this) {
                    Map<Long, C> bufs = this.buffers;
                    if (bufs != null) {
                        bufs.put(Long.valueOf(idx), buf);
                        BufferCloseObserver<T, C> bc = new BufferCloseObserver<>(this, idx);
                        this.observers.add(bc);
                        p.subscribe(bc);
                    }
                }
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                DisposableHelper.dispose(this.upstream);
                onError(ex);
            }
        }

        /* access modifiers changed from: package-private */
        public void openComplete(BufferOpenObserver<Open> os) {
            this.observers.delete(os);
            if (this.observers.size() == 0) {
                DisposableHelper.dispose(this.upstream);
                this.done = true;
                drain();
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x002b, code lost:
            if (r0 == false) goto L_0x0030;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x002d, code lost:
            r5.done = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0030, code lost:
            drain();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0033, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void close(io.reactivex.internal.operators.observable.ObservableBufferBoundary.BufferCloseObserver<T, C> r6, long r7) {
            /*
                r5 = this;
                io.reactivex.disposables.CompositeDisposable r0 = r5.observers
                r0.delete(r6)
                r0 = 0
                io.reactivex.disposables.CompositeDisposable r1 = r5.observers
                int r1 = r1.size()
                if (r1 != 0) goto L_0x0014
                r0 = 1
                java.util.concurrent.atomic.AtomicReference<io.reactivex.disposables.Disposable> r1 = r5.upstream
                io.reactivex.internal.disposables.DisposableHelper.dispose(r1)
            L_0x0014:
                monitor-enter(r5)
                java.util.Map<java.lang.Long, C> r1 = r5.buffers     // Catch:{ all -> 0x0034 }
                r2 = r1
                if (r2 != 0) goto L_0x001c
                monitor-exit(r5)     // Catch:{ all -> 0x0034 }
                return
            L_0x001c:
                io.reactivex.internal.queue.SpscLinkedArrayQueue<C> r3 = r5.queue     // Catch:{ all -> 0x0034 }
                java.lang.Long r4 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x0034 }
                java.lang.Object r1 = r1.remove(r4)     // Catch:{ all -> 0x0034 }
                r3.offer(r1)     // Catch:{ all -> 0x0034 }
                monitor-exit(r5)     // Catch:{ all -> 0x0034 }
                if (r0 == 0) goto L_0x0030
                r1 = 1
                r5.done = r1
            L_0x0030:
                r5.drain()
                return
            L_0x0034:
                r1 = move-exception
                monitor-exit(r5)     // Catch:{ all -> 0x0034 }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservableBufferBoundary.BufferBoundaryObserver.close(io.reactivex.internal.operators.observable.ObservableBufferBoundary$BufferCloseObserver, long):void");
        }

        /* access modifiers changed from: package-private */
        public void boundaryError(Disposable observer, Throwable ex) {
            DisposableHelper.dispose(this.upstream);
            this.observers.delete(observer);
            onError(ex);
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            if (getAndIncrement() == 0) {
                int missed = 1;
                Observer<? super C> a = this.downstream;
                SpscLinkedArrayQueue<C> q = this.queue;
                while (!this.cancelled) {
                    boolean d = this.done;
                    if (!d || this.errors.get() == null) {
                        C v = (Collection) q.poll();
                        boolean empty = v == null;
                        if (d && empty) {
                            a.onComplete();
                            return;
                        } else if (empty) {
                            missed = addAndGet(-missed);
                            if (missed == 0) {
                                return;
                            }
                        } else {
                            a.onNext(v);
                        }
                    } else {
                        q.clear();
                        a.onError(this.errors.terminate());
                        return;
                    }
                }
                q.clear();
            }
        }

        static final class BufferOpenObserver<Open> extends AtomicReference<Disposable> implements Observer<Open>, Disposable {
            private static final long serialVersionUID = -8498650778633225126L;
            final BufferBoundaryObserver<?, ?, Open, ?> parent;

            BufferOpenObserver(BufferBoundaryObserver<?, ?, Open, ?> parent2) {
                this.parent = parent2;
            }

            public void onSubscribe(Disposable d) {
                DisposableHelper.setOnce(this, d);
            }

            public void onNext(Open t) {
                this.parent.open(t);
            }

            public void onError(Throwable t) {
                lazySet(DisposableHelper.DISPOSED);
                this.parent.boundaryError(this, t);
            }

            public void onComplete() {
                lazySet(DisposableHelper.DISPOSED);
                this.parent.openComplete(this);
            }

            public void dispose() {
                DisposableHelper.dispose(this);
            }

            public boolean isDisposed() {
                return get() == DisposableHelper.DISPOSED;
            }
        }
    }

    static final class BufferCloseObserver<T, C extends Collection<? super T>> extends AtomicReference<Disposable> implements Observer<Object>, Disposable {
        private static final long serialVersionUID = -8498650778633225126L;
        final long index;
        final BufferBoundaryObserver<T, C, ?, ?> parent;

        BufferCloseObserver(BufferBoundaryObserver<T, C, ?, ?> parent2, long index2) {
            this.parent = parent2;
            this.index = index2;
        }

        public void onSubscribe(Disposable d) {
            DisposableHelper.setOnce(this, d);
        }

        public void onNext(Object t) {
            Disposable upstream = (Disposable) get();
            if (upstream != DisposableHelper.DISPOSED) {
                lazySet(DisposableHelper.DISPOSED);
                upstream.dispose();
                this.parent.close(this, this.index);
            }
        }

        public void onError(Throwable t) {
            if (get() != DisposableHelper.DISPOSED) {
                lazySet(DisposableHelper.DISPOSED);
                this.parent.boundaryError(this, t);
                return;
            }
            RxJavaPlugins.onError(t);
        }

        public void onComplete() {
            if (get() != DisposableHelper.DISPOSED) {
                lazySet(DisposableHelper.DISPOSED);
                this.parent.close(this, this.index);
            }
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return get() == DisposableHelper.DISPOSED;
        }
    }
}
