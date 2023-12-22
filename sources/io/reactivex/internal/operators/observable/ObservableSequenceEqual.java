package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiPredicate;
import io.reactivex.internal.disposables.ArrayCompositeDisposable;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public final class ObservableSequenceEqual<T> extends Observable<Boolean> {
    final int bufferSize;
    final BiPredicate<? super T, ? super T> comparer;
    final ObservableSource<? extends T> first;
    final ObservableSource<? extends T> second;

    public ObservableSequenceEqual(ObservableSource<? extends T> first, ObservableSource<? extends T> second, BiPredicate<? super T, ? super T> comparer, int bufferSize) {
        this.first = first;
        this.second = second;
        this.comparer = comparer;
        this.bufferSize = bufferSize;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super Boolean> observer) {
        EqualCoordinator<T> ec = new EqualCoordinator<>(observer, this.bufferSize, this.first, this.second, this.comparer);
        observer.onSubscribe(ec);
        ec.subscribe();
    }

    /* loaded from: classes.dex */
    static final class EqualCoordinator<T> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = -6178010334400373240L;
        volatile boolean cancelled;
        final BiPredicate<? super T, ? super T> comparer;
        final Observer<? super Boolean> downstream;
        final ObservableSource<? extends T> first;
        final EqualObserver<T>[] observers;
        final ArrayCompositeDisposable resources;
        final ObservableSource<? extends T> second;

        /* renamed from: v1 */
        T f324v1;

        /* renamed from: v2 */
        T f325v2;

        EqualCoordinator(Observer<? super Boolean> actual, int bufferSize, ObservableSource<? extends T> first, ObservableSource<? extends T> second, BiPredicate<? super T, ? super T> comparer) {
            this.downstream = actual;
            this.first = first;
            this.second = second;
            this.comparer = comparer;
            this.observers = as;
            EqualObserver<T>[] as = {new EqualObserver<>(this, 0, bufferSize), new EqualObserver<>(this, 1, bufferSize)};
            this.resources = new ArrayCompositeDisposable(2);
        }

        boolean setDisposable(Disposable d, int index) {
            return this.resources.setResource(index, d);
        }

        void subscribe() {
            EqualObserver<T>[] as = this.observers;
            this.first.subscribe(as[0]);
            this.second.subscribe(as[1]);
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.resources.dispose();
                if (getAndIncrement() == 0) {
                    EqualObserver<T>[] as = this.observers;
                    as[0].queue.clear();
                    as[1].queue.clear();
                }
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        void cancel(SpscLinkedArrayQueue<T> q1, SpscLinkedArrayQueue<T> q2) {
            this.cancelled = true;
            q1.clear();
            q2.clear();
        }

        void drain() {
            boolean e1;
            boolean e2;
            Throwable e;
            Throwable e3;
            if (getAndIncrement() != 0) {
                return;
            }
            int missed = 1;
            EqualObserver<T>[] as = this.observers;
            EqualObserver<T> observer1 = as[0];
            SpscLinkedArrayQueue<T> q1 = observer1.queue;
            EqualObserver<T> observer2 = as[1];
            SpscLinkedArrayQueue<T> q2 = observer2.queue;
            while (!this.cancelled) {
                boolean d1 = observer1.done;
                if (d1 && (e3 = observer1.error) != null) {
                    cancel(q1, q2);
                    this.downstream.onError(e3);
                    return;
                }
                boolean d2 = observer2.done;
                if (d2 && (e = observer2.error) != null) {
                    cancel(q1, q2);
                    this.downstream.onError(e);
                    return;
                }
                if (this.f324v1 == null) {
                    this.f324v1 = q1.poll();
                }
                if (this.f324v1 != null) {
                    e1 = false;
                } else {
                    e1 = true;
                }
                if (this.f325v2 == null) {
                    this.f325v2 = q2.poll();
                }
                T t = this.f325v2;
                if (t != null) {
                    e2 = false;
                } else {
                    e2 = true;
                }
                if (d1 && d2 && e1 && e2) {
                    this.downstream.onNext(true);
                    this.downstream.onComplete();
                    return;
                } else if (d1 && d2 && e1 != e2) {
                    cancel(q1, q2);
                    this.downstream.onNext(false);
                    this.downstream.onComplete();
                    return;
                } else {
                    if (!e1 && !e2) {
                        try {
                            boolean c = this.comparer.test((T) this.f324v1, t);
                            if (!c) {
                                cancel(q1, q2);
                                this.downstream.onNext(false);
                                this.downstream.onComplete();
                                return;
                            }
                            this.f324v1 = null;
                            this.f325v2 = null;
                        } catch (Throwable ex) {
                            Exceptions.throwIfFatal(ex);
                            cancel(q1, q2);
                            this.downstream.onError(ex);
                            return;
                        }
                    }
                    if (e1 || e2) {
                        missed = addAndGet(-missed);
                        if (missed == 0) {
                            return;
                        }
                    }
                }
            }
            q1.clear();
            q2.clear();
        }
    }

    /* loaded from: classes.dex */
    static final class EqualObserver<T> implements Observer<T> {
        volatile boolean done;
        Throwable error;
        final int index;
        final EqualCoordinator<T> parent;
        final SpscLinkedArrayQueue<T> queue;

        EqualObserver(EqualCoordinator<T> parent, int index, int bufferSize) {
            this.parent = parent;
            this.index = index;
            this.queue = new SpscLinkedArrayQueue<>(bufferSize);
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable d) {
            this.parent.setDisposable(d, this.index);
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            this.queue.offer(t);
            this.parent.drain();
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable t) {
            this.error = t;
            this.done = true;
            this.parent.drain();
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            this.done = true;
            this.parent.drain();
        }
    }
}
