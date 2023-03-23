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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableCombineLatest<T, R> extends Observable<R> {
    final int bufferSize;
    final Function<? super Object[], ? extends R> combiner;
    final boolean delayError;
    final ObservableSource<? extends T>[] sources;
    final Iterable<? extends ObservableSource<? extends T>> sourcesIterable;

    public ObservableCombineLatest(ObservableSource<? extends T>[] sources2, Iterable<? extends ObservableSource<? extends T>> sourcesIterable2, Function<? super Object[], ? extends R> combiner2, int bufferSize2, boolean delayError2) {
        this.sources = sources2;
        this.sourcesIterable = sourcesIterable2;
        this.combiner = combiner2;
        this.bufferSize = bufferSize2;
        this.delayError = delayError2;
    }

    public void subscribeActual(Observer<? super R> observer) {
        int count;
        ObservableSource<? extends T>[] sources2 = this.sources;
        int count2 = 0;
        if (sources2 == null) {
            sources2 = new ObservableSource[8];
            for (ObservableSource<? extends T> p : this.sourcesIterable) {
                if (count2 == sources2.length) {
                    ObservableSource<? extends T>[] b = new ObservableSource[((count2 >> 2) + count2)];
                    System.arraycopy(sources2, 0, b, 0, count2);
                    sources2 = b;
                }
                sources2[count2] = p;
                count2++;
            }
            count = count2;
        } else {
            count = sources2.length;
        }
        if (count == 0) {
            EmptyDisposable.complete((Observer<?>) observer);
            return;
        }
        new LatestCoordinator<>(observer, this.combiner, count, this.bufferSize, this.delayError).subscribe(sources2);
    }

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

        LatestCoordinator(Observer<? super R> actual, Function<? super Object[], ? extends R> combiner2, int count, int bufferSize, boolean delayError2) {
            this.downstream = actual;
            this.combiner = combiner2;
            this.delayError = delayError2;
            this.latest = new Object[count];
            CombinerObserver<T, R>[] as = new CombinerObserver[count];
            for (int i = 0; i < count; i++) {
                as[i] = new CombinerObserver<>(this, i);
            }
            this.observers = as;
            this.queue = new SpscLinkedArrayQueue<>(bufferSize);
        }

        public void subscribe(ObservableSource<? extends T>[] sources) {
            Observer<T>[] as = this.observers;
            int len = as.length;
            this.downstream.onSubscribe(this);
            for (int i = 0; i < len && !this.done && !this.cancelled; i++) {
                sources[i].subscribe(as[i]);
            }
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelSources();
                if (getAndIncrement() == 0) {
                    clear(this.queue);
                }
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        /* access modifiers changed from: package-private */
        public void cancelSources() {
            for (CombinerObserver<T, R> observer : this.observers) {
                observer.dispose();
            }
        }

        /* access modifiers changed from: package-private */
        public void clear(SpscLinkedArrayQueue<?> q) {
            synchronized (this) {
                this.latest = null;
            }
            q.clear();
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            if (getAndIncrement() == 0) {
                SpscLinkedArrayQueue<Object[]> q = this.queue;
                Observer<? super R> a = this.downstream;
                boolean delayError2 = this.delayError;
                int missed = 1;
                while (!this.cancelled) {
                    if (delayError2 || this.errors.get() == null) {
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
                        } else if (empty) {
                            missed = addAndGet(-missed);
                            if (missed == 0) {
                                return;
                            }
                        } else {
                            try {
                                a.onNext(ObjectHelper.requireNonNull(this.combiner.apply(s), "The combiner returned a null value"));
                            } catch (Throwable ex2) {
                                Exceptions.throwIfFatal(ex2);
                                this.errors.addThrowable(ex2);
                                cancelSources();
                                clear(q);
                                a.onError(this.errors.terminate());
                                return;
                            }
                        }
                    } else {
                        cancelSources();
                        clear(q);
                        a.onError(this.errors.terminate());
                        return;
                    }
                }
                clear(q);
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0022, code lost:
            if (r0 == false) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0024, code lost:
            drain();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void innerNext(int r7, T r8) {
            /*
                r6 = this;
                r0 = 0
                monitor-enter(r6)
                java.lang.Object[] r1 = r6.latest     // Catch:{ all -> 0x0028 }
                if (r1 != 0) goto L_0x0008
                monitor-exit(r6)     // Catch:{ all -> 0x0028 }
                return
            L_0x0008:
                r2 = r1[r7]     // Catch:{ all -> 0x0028 }
                int r3 = r6.active     // Catch:{ all -> 0x0028 }
                if (r2 != 0) goto L_0x0012
                int r3 = r3 + 1
                r6.active = r3     // Catch:{ all -> 0x0028 }
            L_0x0012:
                r1[r7] = r8     // Catch:{ all -> 0x0028 }
                int r4 = r1.length     // Catch:{ all -> 0x0028 }
                if (r3 != r4) goto L_0x0021
                io.reactivex.internal.queue.SpscLinkedArrayQueue<java.lang.Object[]> r4 = r6.queue     // Catch:{ all -> 0x0028 }
                java.lang.Object r5 = r1.clone()     // Catch:{ all -> 0x0028 }
                r4.offer(r5)     // Catch:{ all -> 0x0028 }
                r0 = 1
            L_0x0021:
                monitor-exit(r6)     // Catch:{ all -> 0x0028 }
                if (r0 == 0) goto L_0x0027
                r6.drain()
            L_0x0027:
                return
            L_0x0028:
                r1 = move-exception
                monitor-exit(r6)     // Catch:{ all -> 0x0028 }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservableCombineLatest.LatestCoordinator.innerNext(int, java.lang.Object):void");
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0025, code lost:
            if (r2 == r1.length) goto L_0x0027;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void innerError(int r6, java.lang.Throwable r7) {
            /*
                r5 = this;
                io.reactivex.internal.util.AtomicThrowable r0 = r5.errors
                boolean r0 = r0.addThrowable(r7)
                if (r0 == 0) goto L_0x0037
                r0 = 1
                boolean r1 = r5.delayError
                if (r1 == 0) goto L_0x002e
                monitor-enter(r5)
                java.lang.Object[] r1 = r5.latest     // Catch:{ all -> 0x002b }
                if (r1 != 0) goto L_0x0014
                monitor-exit(r5)     // Catch:{ all -> 0x002b }
                return
            L_0x0014:
                r2 = r1[r6]     // Catch:{ all -> 0x002b }
                r3 = 1
                if (r2 != 0) goto L_0x001b
                r2 = r3
                goto L_0x001c
            L_0x001b:
                r2 = 0
            L_0x001c:
                r0 = r2
                if (r0 != 0) goto L_0x0027
                int r2 = r5.complete     // Catch:{ all -> 0x002b }
                int r2 = r2 + r3
                r5.complete = r2     // Catch:{ all -> 0x002b }
                int r4 = r1.length     // Catch:{ all -> 0x002b }
                if (r2 != r4) goto L_0x0029
            L_0x0027:
                r5.done = r3     // Catch:{ all -> 0x002b }
            L_0x0029:
                monitor-exit(r5)     // Catch:{ all -> 0x002b }
                goto L_0x002e
            L_0x002b:
                r1 = move-exception
                monitor-exit(r5)     // Catch:{ all -> 0x002b }
                throw r1
            L_0x002e:
                if (r0 == 0) goto L_0x0033
                r5.cancelSources()
            L_0x0033:
                r5.drain()
                goto L_0x003a
            L_0x0037:
                io.reactivex.plugins.RxJavaPlugins.onError(r7)
            L_0x003a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservableCombineLatest.LatestCoordinator.innerError(int, java.lang.Throwable):void");
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0019, code lost:
            if (r2 == r1.length) goto L_0x001b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x001e, code lost:
            if (r0 == false) goto L_0x0023;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0020, code lost:
            cancelSources();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0023, code lost:
            drain();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0026, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void innerComplete(int r6) {
            /*
                r5 = this;
                r0 = 0
                monitor-enter(r5)
                java.lang.Object[] r1 = r5.latest     // Catch:{ all -> 0x0027 }
                if (r1 != 0) goto L_0x0008
                monitor-exit(r5)     // Catch:{ all -> 0x0027 }
                return
            L_0x0008:
                r2 = r1[r6]     // Catch:{ all -> 0x0027 }
                r3 = 1
                if (r2 != 0) goto L_0x000f
                r2 = r3
                goto L_0x0010
            L_0x000f:
                r2 = 0
            L_0x0010:
                r0 = r2
                if (r0 != 0) goto L_0x001b
                int r2 = r5.complete     // Catch:{ all -> 0x0027 }
                int r2 = r2 + r3
                r5.complete = r2     // Catch:{ all -> 0x0027 }
                int r4 = r1.length     // Catch:{ all -> 0x0027 }
                if (r2 != r4) goto L_0x001d
            L_0x001b:
                r5.done = r3     // Catch:{ all -> 0x0027 }
            L_0x001d:
                monitor-exit(r5)     // Catch:{ all -> 0x0027 }
                if (r0 == 0) goto L_0x0023
                r5.cancelSources()
            L_0x0023:
                r5.drain()
                return
            L_0x0027:
                r1 = move-exception
                monitor-exit(r5)     // Catch:{ all -> 0x0027 }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservableCombineLatest.LatestCoordinator.innerComplete(int):void");
        }
    }

    static final class CombinerObserver<T, R> extends AtomicReference<Disposable> implements Observer<T> {
        private static final long serialVersionUID = -4823716997131257941L;
        final int index;
        final LatestCoordinator<T, R> parent;

        CombinerObserver(LatestCoordinator<T, R> parent2, int index2) {
            this.parent = parent2;
            this.index = index2;
        }

        public void onSubscribe(Disposable d) {
            DisposableHelper.setOnce(this, d);
        }

        public void onNext(T t) {
            this.parent.innerNext(this.index, t);
        }

        public void onError(Throwable t) {
            this.parent.innerError(this.index, t);
        }

        public void onComplete() {
            this.parent.innerComplete(this.index);
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }
    }
}
