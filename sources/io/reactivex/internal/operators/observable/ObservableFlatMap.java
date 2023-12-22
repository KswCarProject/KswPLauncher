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

/* loaded from: classes.dex */
public final class ObservableFlatMap<T, U> extends AbstractObservableWithUpstream<T, U> {
    final int bufferSize;
    final boolean delayErrors;
    final Function<? super T, ? extends ObservableSource<? extends U>> mapper;
    final int maxConcurrency;

    public ObservableFlatMap(ObservableSource<T> source, Function<? super T, ? extends ObservableSource<? extends U>> mapper, boolean delayErrors, int maxConcurrency, int bufferSize) {
        super(source);
        this.mapper = mapper;
        this.delayErrors = delayErrors;
        this.maxConcurrency = maxConcurrency;
        this.bufferSize = bufferSize;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super U> t) {
        if (ObservableScalarXMap.tryScalarXMapSubscribe(this.source, t, this.mapper)) {
            return;
        }
        this.source.subscribe(new MergeObserver(t, this.mapper, this.delayErrors, this.maxConcurrency, this.bufferSize));
    }

    /* loaded from: classes.dex */
    static final class MergeObserver<T, U> extends AtomicInteger implements Disposable, Observer<T> {
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
        static final InnerObserver<?, ?>[] EMPTY = new InnerObserver[0];
        static final InnerObserver<?, ?>[] CANCELLED = new InnerObserver[0];

        MergeObserver(Observer<? super U> actual, Function<? super T, ? extends ObservableSource<? extends U>> mapper, boolean delayErrors, int maxConcurrency, int bufferSize) {
            this.downstream = actual;
            this.mapper = mapper;
            this.delayErrors = delayErrors;
            this.maxConcurrency = maxConcurrency;
            this.bufferSize = bufferSize;
            if (maxConcurrency != Integer.MAX_VALUE) {
                this.sources = new ArrayDeque(maxConcurrency);
            }
            this.observers = new AtomicReference<>(EMPTY);
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.upstream, d)) {
                this.upstream = d;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            if (this.done) {
                return;
            }
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

        void subscribeInner(ObservableSource<? extends U> p) {
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

        /* JADX WARN: Multi-variable type inference failed */
        boolean addInner(InnerObserver<T, U> inner) {
            InnerObserver<?, ?>[] a;
            InnerObserver[] innerObserverArr;
            do {
                a = this.observers.get();
                if (a == CANCELLED) {
                    inner.dispose();
                    return false;
                }
                int n = a.length;
                innerObserverArr = new InnerObserver[n + 1];
                System.arraycopy(a, 0, innerObserverArr, 0, n);
                innerObserverArr[n] = inner;
            } while (!this.observers.compareAndSet(a, innerObserverArr));
            return true;
        }

        void removeInner(InnerObserver<T, U> inner) {
            InnerObserver<?, ?>[] a;
            InnerObserver<?, ?>[] b;
            do {
                a = this.observers.get();
                int n = a.length;
                if (n == 0) {
                    return;
                }
                int j = -1;
                int i = 0;
                while (true) {
                    if (i >= n) {
                        break;
                    } else if (a[i] != inner) {
                        i++;
                    } else {
                        j = i;
                        break;
                    }
                }
                if (j < 0) {
                    return;
                }
                if (n == 1) {
                    b = EMPTY;
                } else {
                    InnerObserver<?, ?>[] b2 = new InnerObserver[n - 1];
                    System.arraycopy(a, 0, b2, 0, j);
                    System.arraycopy(a, j + 1, b2, j, (n - j) - 1);
                    b = b2;
                }
            } while (!this.observers.compareAndSet(a, b));
        }

        boolean tryEmitScalar(Callable<? extends U> value) {
            try {
                U u = value.call();
                if (u == null) {
                    return true;
                }
                if (get() == 0 && compareAndSet(0, 1)) {
                    this.downstream.onNext(u);
                    if (decrementAndGet() == 0) {
                        return true;
                    }
                } else {
                    SimplePlainQueue<U> q = this.queue;
                    if (q == null) {
                        if (this.maxConcurrency == Integer.MAX_VALUE) {
                            q = new SpscLinkedArrayQueue(this.bufferSize);
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

        void tryEmit(U value, InnerObserver<T, U> inner) {
            if (get() == 0 && compareAndSet(0, 1)) {
                this.downstream.onNext(value);
                if (decrementAndGet() == 0) {
                    return;
                }
            } else {
                SimpleQueue<U> q = inner.queue;
                if (q == null) {
                    q = new SpscLinkedArrayQueue(this.bufferSize);
                    inner.queue = q;
                }
                q.offer(value);
                if (getAndIncrement() != 0) {
                    return;
                }
            }
            drainLoop();
        }

        @Override // io.reactivex.Observer
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

        @Override // io.reactivex.Observer
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            drain();
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            Throwable ex;
            if (!this.cancelled) {
                this.cancelled = true;
                if (disposeAll() && (ex = this.errors.terminate()) != null && ex != ExceptionHelper.TERMINATED) {
                    RxJavaPlugins.onError(ex);
                }
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        void drainLoop() {
            int j;
            Observer<? super U> child;
            int index;
            Observer<? super U> child2;
            Observer<? super U> child3 = this.downstream;
            int missed = 1;
            while (!checkTerminate()) {
                int innerCompleted = 0;
                SimplePlainQueue<U> svq = this.queue;
                if (svq == null) {
                    j = 0;
                } else {
                    while (!checkTerminate()) {
                        Object obj = (U) svq.poll();
                        if (obj == null) {
                            j = innerCompleted;
                        } else {
                            child3.onNext(obj);
                            innerCompleted++;
                        }
                    }
                    return;
                }
                if (j != 0) {
                    if (this.maxConcurrency == Integer.MAX_VALUE) {
                        child = child3;
                    } else {
                        subscribeMore(j);
                        child = child3;
                    }
                } else {
                    boolean d = this.done;
                    SimplePlainQueue<U> svq2 = this.queue;
                    InnerObserver<?, ?>[] inner = this.observers.get();
                    int n = inner.length;
                    int nSources = 0;
                    if (this.maxConcurrency != Integer.MAX_VALUE) {
                        synchronized (this) {
                            nSources = this.sources.size();
                        }
                    }
                    if (d && ((svq2 == null || svq2.isEmpty()) && n == 0 && nSources == 0)) {
                        Throwable ex = this.errors.terminate();
                        if (ex != ExceptionHelper.TERMINATED) {
                            if (ex == null) {
                                child3.onComplete();
                                return;
                            } else {
                                child3.onError(ex);
                                return;
                            }
                        }
                        return;
                    }
                    if (n == 0) {
                        child = child3;
                    } else {
                        long startId = this.lastId;
                        int index2 = this.lastIndex;
                        if (n <= index2 || inner[index2].f318id != startId) {
                            if (n <= index2) {
                                index2 = 0;
                            }
                            int j2 = index2;
                            int i = 0;
                            while (i < n) {
                                boolean d2 = d;
                                if (inner[j2].f318id == startId) {
                                    break;
                                }
                                j2++;
                                if (j2 == n) {
                                    j2 = 0;
                                }
                                i++;
                                d = d2;
                            }
                            this.lastIndex = j2;
                            this.lastId = inner[j2].f318id;
                            index = j2;
                        } else {
                            index = index2;
                        }
                        int i2 = 0;
                        int innerCompleted2 = j;
                        int j3 = index;
                        while (i2 < n) {
                            if (checkTerminate()) {
                                return;
                            }
                            InnerObserver<T, U> is = inner[j3];
                            SimpleQueue<U> q = is.queue;
                            if (q != null) {
                                while (true) {
                                    try {
                                        Object obj2 = (U) q.poll();
                                        if (obj2 == null) {
                                            child2 = child3;
                                            break;
                                        }
                                        child3.onNext(obj2);
                                        if (checkTerminate()) {
                                            return;
                                        }
                                    } catch (Throwable ex2) {
                                        Exceptions.throwIfFatal(ex2);
                                        is.dispose();
                                        child2 = child3;
                                        this.errors.addThrowable(ex2);
                                        if (checkTerminate()) {
                                            return;
                                        }
                                        removeInner(is);
                                        innerCompleted2++;
                                        j3++;
                                        if (j3 == n) {
                                            j3 = 0;
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
                                if (checkTerminate()) {
                                    return;
                                }
                                innerCompleted2++;
                            }
                            j3++;
                            if (j3 == n) {
                                j3 = 0;
                            }
                            i2++;
                            child3 = child2;
                        }
                        child = child3;
                        this.lastIndex = j3;
                        this.lastId = inner[j3].f318id;
                        j = innerCompleted2;
                    }
                    if (j == 0) {
                        missed = addAndGet(-missed);
                        if (missed != 0) {
                            child3 = child;
                        } else {
                            return;
                        }
                    } else if (this.maxConcurrency != Integer.MAX_VALUE) {
                        subscribeMore(j);
                    }
                }
                child3 = child;
            }
        }

        void subscribeMore(int innerCompleted) {
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
        }

        boolean checkTerminate() {
            if (this.cancelled) {
                return true;
            }
            Throwable e = this.errors.get();
            if (!this.delayErrors && e != null) {
                disposeAll();
                Throwable e2 = this.errors.terminate();
                if (e2 != ExceptionHelper.TERMINATED) {
                    this.downstream.onError(e2);
                }
                return true;
            }
            return false;
        }

        boolean disposeAll() {
            this.upstream.dispose();
            InnerObserver<?, ?>[] a = this.observers.get();
            InnerObserver<?, ?>[] innerObserverArr = CANCELLED;
            if (a != innerObserverArr) {
                InnerObserver<?, ?>[] a2 = this.observers.getAndSet(innerObserverArr);
                InnerObserver<?, ?>[] a3 = a2;
                if (a3 != innerObserverArr) {
                    for (InnerObserver<?, ?> inner : a3) {
                        inner.dispose();
                    }
                    return true;
                }
            }
            return false;
        }
    }

    /* loaded from: classes.dex */
    static final class InnerObserver<T, U> extends AtomicReference<Disposable> implements Observer<U> {
        private static final long serialVersionUID = -4606175640614850599L;
        volatile boolean done;
        int fusionMode;

        /* renamed from: id */
        final long f318id;
        final MergeObserver<T, U> parent;
        volatile SimpleQueue<U> queue;

        InnerObserver(MergeObserver<T, U> parent, long id) {
            this.f318id = id;
            this.parent = parent;
        }

        @Override // io.reactivex.Observer
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

        @Override // io.reactivex.Observer
        public void onNext(U t) {
            if (this.fusionMode == 0) {
                this.parent.tryEmit(t, this);
            } else {
                this.parent.drain();
            }
        }

        @Override // io.reactivex.Observer
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

        @Override // io.reactivex.Observer
        public void onComplete() {
            this.done = true;
            this.parent.drain();
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }
    }
}
