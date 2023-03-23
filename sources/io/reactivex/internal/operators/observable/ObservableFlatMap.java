package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.QueueDisposable;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableFlatMap<T, U> extends AbstractObservableWithUpstream<T, U> {
    final int bufferSize;
    final boolean delayErrors;
    final Function<? super T, ? extends ObservableSource<? extends U>> mapper;
    final int maxConcurrency;

    public ObservableFlatMap(ObservableSource<T> source, Function<? super T, ? extends ObservableSource<? extends U>> mapper2, boolean delayErrors2, int maxConcurrency2, int bufferSize2) {
        super(source);
        this.mapper = mapper2;
        this.delayErrors = delayErrors2;
        this.maxConcurrency = maxConcurrency2;
        this.bufferSize = bufferSize2;
    }

    public void subscribeActual(Observer<? super U> t) {
        if (!ObservableScalarXMap.tryScalarXMapSubscribe(this.source, t, this.mapper)) {
            this.source.subscribe(new MergeObserver(t, this.mapper, this.delayErrors, this.maxConcurrency, this.bufferSize));
        }
    }

    static final class MergeObserver<T, U> extends AtomicInteger implements Disposable, Observer<T> {
        static final InnerObserver<?, ?>[] CANCELLED = new InnerObserver[0];
        static final InnerObserver<?, ?>[] EMPTY = new InnerObserver[0];
        private static final long serialVersionUID = -2117620485640801370L;
        final int bufferSize;
        volatile boolean cancelled;
        final boolean delayErrors;
        volatile boolean done;
        final Observer<? super U> downstream;
        final AtomicThrowable errors = new AtomicThrowable();
        long lastId;
        int lastIndex;
        final Function<? super T, ? extends ObservableSource<? extends U>> mapper;
        final int maxConcurrency;
        final AtomicReference<InnerObserver<?, ?>[]> observers;
        volatile SimplePlainQueue<U> queue;
        Queue<ObservableSource<? extends U>> sources;
        long uniqueId;
        Disposable upstream;
        int wip;

        MergeObserver(Observer<? super U> actual, Function<? super T, ? extends ObservableSource<? extends U>> mapper2, boolean delayErrors2, int maxConcurrency2, int bufferSize2) {
            this.downstream = actual;
            this.mapper = mapper2;
            this.delayErrors = delayErrors2;
            this.maxConcurrency = maxConcurrency2;
            this.bufferSize = bufferSize2;
            if (maxConcurrency2 != Integer.MAX_VALUE) {
                this.sources = new ArrayDeque(maxConcurrency2);
            }
            this.observers = new AtomicReference<>(EMPTY);
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.upstream, d)) {
                this.upstream = d;
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                try {
                    ObservableSource<? extends U> p = (ObservableSource) ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null ObservableSource");
                    if (this.maxConcurrency != Integer.MAX_VALUE) {
                        synchronized (this) {
                            int i = this.wip;
                            if (i == this.maxConcurrency) {
                                this.sources.offer(p);
                                return;
                            }
                            this.wip = i + 1;
                        }
                    }
                    subscribeInner(p);
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    this.upstream.dispose();
                    onError(e);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void subscribeInner(ObservableSource<? extends U> p) {
            while (p instanceof Callable) {
                if (tryEmitScalar((Callable) p) && this.maxConcurrency != Integer.MAX_VALUE) {
                    boolean empty = false;
                    synchronized (this) {
                        p = this.sources.poll();
                        if (p == null) {
                            this.wip--;
                            empty = true;
                        }
                    }
                    if (empty) {
                        drain();
                        return;
                    }
                } else {
                    return;
                }
            }
            long j = this.uniqueId;
            this.uniqueId = 1 + j;
            InnerObserver<T, U> inner = new InnerObserver<>(this, j);
            if (addInner(inner)) {
                p.subscribe(inner);
            }
        }

        /* access modifiers changed from: package-private */
        public boolean addInner(InnerObserver<T, U> inner) {
            InnerObserver<?, ?>[] a;
            InnerObserver<?, ?>[] b;
            do {
                a = (InnerObserver[]) this.observers.get();
                if (a == CANCELLED) {
                    inner.dispose();
                    return false;
                }
                int n = a.length;
                b = new InnerObserver[(n + 1)];
                System.arraycopy(a, 0, b, 0, n);
                b[n] = inner;
            } while (!this.observers.compareAndSet(a, b));
            return true;
        }

        /* access modifiers changed from: package-private */
        public void removeInner(InnerObserver<T, U> inner) {
            InnerObserver<?, ?>[] a;
            InnerObserver<?, ?>[] b;
            do {
                a = (InnerObserver[]) this.observers.get();
                int n = a.length;
                if (n != 0) {
                    int j = -1;
                    int i = 0;
                    while (true) {
                        if (i >= n) {
                            break;
                        } else if (a[i] == inner) {
                            j = i;
                            break;
                        } else {
                            i++;
                        }
                    }
                    if (j >= 0) {
                        if (n == 1) {
                            b = EMPTY;
                        } else {
                            InnerObserver<?, ?>[] b2 = new InnerObserver[(n - 1)];
                            System.arraycopy(a, 0, b2, 0, j);
                            System.arraycopy(a, j + 1, b2, j, (n - j) - 1);
                            b = b2;
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } while (!this.observers.compareAndSet(a, b));
        }

        /* access modifiers changed from: package-private */
        public boolean tryEmitScalar(Callable<? extends U> value) {
            try {
                U u = value.call();
                if (u == null) {
                    return true;
                }
                if (get() != 0 || !compareAndSet(0, 1)) {
                    SimplePlainQueue<U> q = this.queue;
                    if (q == null) {
                        if (this.maxConcurrency == Integer.MAX_VALUE) {
                            q = new SpscLinkedArrayQueue<>(this.bufferSize);
                        } else {
                            q = new SpscArrayQueue<>(this.maxConcurrency);
                        }
                        this.queue = q;
                    }
                    if (!q.offer(u)) {
                        onError(new IllegalStateException("Scalar queue full?!"));
                        return true;
                    } else if (getAndIncrement() != 0) {
                        return false;
                    }
                } else {
                    this.downstream.onNext(u);
                    if (decrementAndGet() == 0) {
                        return true;
                    }
                }
                drainLoop();
                return true;
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                this.errors.addThrowable(ex);
                drain();
                return true;
            }
        }

        /* access modifiers changed from: package-private */
        public void tryEmit(U value, InnerObserver<T, U> inner) {
            if (get() != 0 || !compareAndSet(0, 1)) {
                SimpleQueue<U> q = inner.queue;
                if (q == null) {
                    q = new SpscLinkedArrayQueue<>(this.bufferSize);
                    inner.queue = q;
                }
                q.offer(value);
                if (getAndIncrement() != 0) {
                    return;
                }
            } else {
                this.downstream.onNext(value);
                if (decrementAndGet() == 0) {
                    return;
                }
            }
            drainLoop();
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
            } else if (this.errors.addThrowable(t)) {
                this.done = true;
                drain();
            } else {
                RxJavaPlugins.onError(t);
            }
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                drain();
            }
        }

        public void dispose() {
            Throwable ex;
            if (!this.cancelled) {
                this.cancelled = true;
                if (disposeAll() && (ex = this.errors.terminate()) != null && ex != ExceptionHelper.TERMINATED) {
                    RxJavaPlugins.onError(ex);
                }
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        /* access modifiers changed from: package-private */
        public void drainLoop() {
            int innerCompleted;
            Observer<? super U> child;
            int index;
            Observer<? super U> child2;
            Observer<? super U> child3 = this.downstream;
            int missed = 1;
            while (!checkTerminate()) {
                int innerCompleted2 = 0;
                SimplePlainQueue<U> svq = this.queue;
                if (svq != null) {
                    while (!checkTerminate()) {
                        U o = svq.poll();
                        if (o == null) {
                            innerCompleted = innerCompleted2;
                        } else {
                            child3.onNext(o);
                            innerCompleted2++;
                        }
                    }
                    return;
                }
                innerCompleted = 0;
                if (innerCompleted == 0) {
                    boolean d = this.done;
                    SimplePlainQueue<U> svq2 = this.queue;
                    InnerObserver<T, U>[] inner = (InnerObserver[]) this.observers.get();
                    int n = inner.length;
                    int nSources = 0;
                    if (this.maxConcurrency != Integer.MAX_VALUE) {
                        synchronized (this) {
                            nSources = this.sources.size();
                        }
                    }
                    if (!d || !((svq2 == null || svq2.isEmpty()) && n == 0 && nSources == 0)) {
                        if (n != 0) {
                            long startId = this.lastId;
                            int index2 = this.lastIndex;
                            if (n <= index2 || inner[index2].id != startId) {
                                if (n <= index2) {
                                    index2 = 0;
                                }
                                int j = index2;
                                int i = 0;
                                while (true) {
                                    if (i >= n) {
                                        break;
                                    }
                                    boolean d2 = d;
                                    if (inner[j].id == startId) {
                                        break;
                                    }
                                    j++;
                                    if (j == n) {
                                        j = 0;
                                    }
                                    i++;
                                    d = d2;
                                }
                                this.lastIndex = j;
                                this.lastId = inner[j].id;
                                index = j;
                            } else {
                                index = index2;
                                boolean z = d;
                            }
                            int i2 = 0;
                            int innerCompleted3 = innerCompleted;
                            int j2 = index;
                            while (i2 < n) {
                                if (!checkTerminate()) {
                                    InnerObserver<T, U> is = inner[j2];
                                    SimpleQueue<U> q = is.queue;
                                    if (q != null) {
                                        while (true) {
                                            try {
                                                U o2 = q.poll();
                                                if (o2 == null) {
                                                    child2 = child3;
                                                    break;
                                                }
                                                child3.onNext(o2);
                                                if (checkTerminate()) {
                                                    return;
                                                }
                                            } catch (Throwable th) {
                                                Throwable ex = th;
                                                Exceptions.throwIfFatal(ex);
                                                is.dispose();
                                                child2 = child3;
                                                this.errors.addThrowable(ex);
                                                if (!checkTerminate()) {
                                                    removeInner(is);
                                                    innerCompleted3++;
                                                    j2++;
                                                    if (j2 == n) {
                                                        j2 = 0;
                                                    }
                                                } else {
                                                    return;
                                                }
                                            }
                                        }
                                    } else {
                                        child2 = child3;
                                    }
                                    boolean innerDone = is.done;
                                    SimpleQueue<U> innerQueue = is.queue;
                                    if (innerDone && (innerQueue == null || innerQueue.isEmpty())) {
                                        removeInner(is);
                                        if (!checkTerminate()) {
                                            innerCompleted3++;
                                        } else {
                                            return;
                                        }
                                    }
                                    j2++;
                                    if (j2 == n) {
                                        j2 = 0;
                                    }
                                    i2++;
                                    child3 = child2;
                                } else {
                                    return;
                                }
                            }
                            child = child3;
                            this.lastIndex = j2;
                            this.lastId = inner[j2].id;
                            innerCompleted = innerCompleted3;
                        } else {
                            child = child3;
                            boolean z2 = d;
                        }
                        if (innerCompleted == 0) {
                            missed = addAndGet(-missed);
                            if (missed != 0) {
                                child3 = child;
                            } else {
                                return;
                            }
                        } else if (this.maxConcurrency != Integer.MAX_VALUE) {
                            subscribeMore(innerCompleted);
                        }
                    } else {
                        Throwable ex2 = this.errors.terminate();
                        if (ex2 == ExceptionHelper.TERMINATED) {
                            return;
                        }
                        if (ex2 == null) {
                            child3.onComplete();
                            return;
                        } else {
                            child3.onError(ex2);
                            return;
                        }
                    }
                } else if (this.maxConcurrency != Integer.MAX_VALUE) {
                    subscribeMore(innerCompleted);
                    child = child3;
                } else {
                    child = child3;
                }
                child3 = child;
            }
        }

        /* access modifiers changed from: package-private */
        public void subscribeMore(int innerCompleted) {
            while (true) {
                int innerCompleted2 = innerCompleted - 1;
                if (innerCompleted != 0) {
                    synchronized (this) {
                        ObservableSource<? extends U> p = this.sources.poll();
                        if (p == null) {
                            this.wip--;
                        } else {
                            subscribeInner(p);
                        }
                    }
                    innerCompleted = innerCompleted2;
                } else {
                    return;
                }
            }
            while (true) {
            }
        }

        /* access modifiers changed from: package-private */
        public boolean checkTerminate() {
            if (this.cancelled) {
                return true;
            }
            Throwable e = (Throwable) this.errors.get();
            if (this.delayErrors || e == null) {
                return false;
            }
            disposeAll();
            Throwable e2 = this.errors.terminate();
            if (e2 != ExceptionHelper.TERMINATED) {
                this.downstream.onError(e2);
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        public boolean disposeAll() {
            InnerObserver<?, ?>[] a;
            this.upstream.dispose();
            InnerObserver<?, ?>[] a2 = (InnerObserver[]) this.observers.get();
            InnerObserver<?, ?>[] innerObserverArr = CANCELLED;
            if (a2 == innerObserverArr || (a = (InnerObserver[]) this.observers.getAndSet(innerObserverArr)) == innerObserverArr) {
                return false;
            }
            for (InnerObserver<?, ?> inner : a) {
                inner.dispose();
            }
            return true;
        }
    }

    static final class InnerObserver<T, U> extends AtomicReference<Disposable> implements Observer<U> {
        private static final long serialVersionUID = -4606175640614850599L;
        volatile boolean done;
        int fusionMode;
        final long id;
        final MergeObserver<T, U> parent;
        volatile SimpleQueue<U> queue;

        InnerObserver(MergeObserver<T, U> parent2, long id2) {
            this.id = id2;
            this.parent = parent2;
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.setOnce(this, d) && (d instanceof QueueDisposable)) {
                QueueDisposable<U> qd = (QueueDisposable) d;
                int m = qd.requestFusion(7);
                if (m == 1) {
                    this.fusionMode = m;
                    this.queue = qd;
                    this.done = true;
                    this.parent.drain();
                } else if (m == 2) {
                    this.fusionMode = m;
                    this.queue = qd;
                }
            }
        }

        public void onNext(U t) {
            if (this.fusionMode == 0) {
                this.parent.tryEmit(t, this);
            } else {
                this.parent.drain();
            }
        }

        public void onError(Throwable t) {
            if (this.parent.errors.addThrowable(t)) {
                if (!this.parent.delayErrors) {
                    this.parent.disposeAll();
                }
                this.done = true;
                this.parent.drain();
                return;
            }
            RxJavaPlugins.onError(t);
        }

        public void onComplete() {
            this.done = true;
            this.parent.drain();
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }
    }
}
