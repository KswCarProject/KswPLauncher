package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class ObservableCombineLatest<T, R> extends Observable<R> {
    final int bufferSize;
    final Function<? super Object[], ? extends R> combiner;
    final boolean delayError;
    final ObservableSource<? extends T>[] sources;
    final Iterable<? extends ObservableSource<? extends T>> sourcesIterable;

    public ObservableCombineLatest(ObservableSource<? extends T>[] sources, Iterable<? extends ObservableSource<? extends T>> sourcesIterable, Function<? super Object[], ? extends R> combiner, int bufferSize, boolean delayError) {
        this.sources = sources;
        this.sourcesIterable = sourcesIterable;
        this.combiner = combiner;
        this.bufferSize = bufferSize;
        this.delayError = delayError;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super R> observer) {
        int count;
        ObservableSource<? extends T>[] sources = this.sources;
        int count2 = 0;
        if (sources == null) {
            sources = new ObservableSource[8];
            for (ObservableSource<? extends T> p : this.sourcesIterable) {
                if (count2 == sources.length) {
                    ObservableSource<? extends T>[] b = new ObservableSource[(count2 >> 2) + count2];
                    System.arraycopy(sources, 0, b, 0, count2);
                    sources = b;
                }
                sources[count2] = p;
                count2++;
            }
            count = count2;
        } else {
            int count3 = sources.length;
            count = count3;
        }
        if (count == 0) {
            EmptyDisposable.complete(observer);
            return;
        }
        LatestCoordinator<T, R> lc = new LatestCoordinator<>(observer, this.combiner, count, this.bufferSize, this.delayError);
        lc.subscribe(sources);
    }

    /* loaded from: classes.dex */
    static final class LatestCoordinator<T, R> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = 8567835998786448817L;
        int active;
        volatile boolean cancelled;
        final Function<? super Object[], ? extends R> combiner;
        int complete;
        final boolean delayError;
        volatile boolean done;
        final Observer<? super R> downstream;
        final AtomicThrowable errors = new AtomicThrowable();
        Object[] latest;
        final CombinerObserver<T, R>[] observers;
        final SpscLinkedArrayQueue<Object[]> queue;

        LatestCoordinator(Observer<? super R> actual, Function<? super Object[], ? extends R> combiner, int count, int bufferSize, boolean delayError) {
            this.downstream = actual;
            this.combiner = combiner;
            this.delayError = delayError;
            this.latest = new Object[count];
            CombinerObserver<T, R>[] as = new CombinerObserver[count];
            for (int i = 0; i < count; i++) {
                as[i] = new CombinerObserver<>(this, i);
            }
            this.observers = as;
            this.queue = new SpscLinkedArrayQueue<>(bufferSize);
        }

        public void subscribe(ObservableSource<? extends T>[] sources) {
            CombinerObserver<T, R>[] combinerObserverArr = this.observers;
            int len = combinerObserverArr.length;
            this.downstream.onSubscribe(this);
            for (int i = 0; i < len && !this.done && !this.cancelled; i++) {
                sources[i].subscribe(combinerObserverArr[i]);
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelSources();
                if (getAndIncrement() == 0) {
                    clear(this.queue);
                }
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        void cancelSources() {
            CombinerObserver<T, R>[] combinerObserverArr;
            for (CombinerObserver<T, R> observer : this.observers) {
                observer.dispose();
            }
        }

        void clear(SpscLinkedArrayQueue<?> q) {
            synchronized (this) {
                this.latest = null;
            }
            q.clear();
        }

        void drain() {
            if (getAndIncrement() != 0) {
                return;
            }
            SpscLinkedArrayQueue<Object[]> q = this.queue;
            Observer<? super R> a = this.downstream;
            boolean delayError = this.delayError;
            int missed = 1;
            while (!this.cancelled) {
                if (!delayError && this.errors.get() != null) {
                    cancelSources();
                    clear(q);
                    a.onError(this.errors.terminate());
                    return;
                }
                boolean d = this.done;
                Object[] s = q.poll();
                boolean empty = s == null;
                if (d && empty) {
                    clear(q);
                    Throwable ex = this.errors.terminate();
                    if (ex == null) {
                        a.onComplete();
                        return;
                    } else {
                        a.onError(ex);
                        return;
                    }
                } else if (!empty) {
                    try {
                        a.onNext((Object) ObjectHelper.requireNonNull(this.combiner.apply(s), "The combiner returned a null value"));
                    } catch (Throwable ex2) {
                        Exceptions.throwIfFatal(ex2);
                        this.errors.addThrowable(ex2);
                        cancelSources();
                        clear(q);
                        a.onError(this.errors.terminate());
                        return;
                    }
                } else {
                    missed = addAndGet(-missed);
                    if (missed == 0) {
                        return;
                    }
                }
            }
            clear(q);
        }

        /* JADX WARN: Multi-variable type inference failed */
        void innerNext(int index, T item) {
            boolean shouldDrain = false;
            synchronized (this) {
                Object[] latest = this.latest;
                if (latest == null) {
                    return;
                }
                Object o = latest[index];
                int a = this.active;
                if (o == null) {
                    a++;
                    this.active = a;
                }
                latest[index] = item;
                if (a == latest.length) {
                    this.queue.offer(latest.clone());
                    shouldDrain = true;
                }
                if (shouldDrain) {
                    drain();
                }
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:18:0x0025, code lost:
            if (r2 == r1.length) goto L21;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        void innerError(int index, Throwable ex) {
            if (this.errors.addThrowable(ex)) {
                boolean cancelOthers = true;
                if (this.delayError) {
                    synchronized (this) {
                        Object[] latest = this.latest;
                        if (latest == null) {
                            return;
                        }
                        cancelOthers = latest[index] == null;
                        if (!cancelOthers) {
                            int i = this.complete + 1;
                            this.complete = i;
                        }
                        this.done = true;
                    }
                }
                if (cancelOthers) {
                    cancelSources();
                }
                drain();
                return;
            }
            RxJavaPlugins.onError(ex);
        }

        /* JADX WARN: Code restructure failed: missing block: B:15:0x0019, code lost:
            if (r2 == r1.length) goto L21;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        void innerComplete(int index) {
            synchronized (this) {
                Object[] latest = this.latest;
                if (latest == null) {
                    return;
                }
                boolean cancelOthers = latest[index] == null;
                if (!cancelOthers) {
                    int i = this.complete + 1;
                    this.complete = i;
                }
                this.done = true;
                if (cancelOthers) {
                    cancelSources();
                }
                drain();
            }
        }
    }

    /* loaded from: classes.dex */
    static final class CombinerObserver<T, R> extends AtomicReference<Disposable> implements Observer<T> {
        private static final long serialVersionUID = -4823716997131257941L;
        final int index;
        final LatestCoordinator<T, R> parent;

        CombinerObserver(LatestCoordinator<T, R> parent, int index) {
            this.parent = parent;
            this.index = index;
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable d) {
            DisposableHelper.setOnce(this, d);
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            this.parent.innerNext(this.index, t);
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable t) {
            this.parent.innerError(this.index, t);
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            this.parent.innerComplete(this.index);
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }
    }
}
