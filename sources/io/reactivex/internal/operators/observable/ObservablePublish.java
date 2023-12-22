package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.fuseable.HasUpstreamObservableSource;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class ObservablePublish<T> extends ConnectableObservable<T> implements HasUpstreamObservableSource<T>, ObservablePublishClassic<T> {
    final AtomicReference<PublishObserver<T>> current;
    final ObservableSource<T> onSubscribe;
    final ObservableSource<T> source;

    public static <T> ConnectableObservable<T> create(ObservableSource<T> source) {
        AtomicReference<PublishObserver<T>> curr = new AtomicReference<>();
        ObservableSource<T> onSubscribe = new PublishSource<>(curr);
        return RxJavaPlugins.onAssembly((ConnectableObservable) new ObservablePublish(onSubscribe, source, curr));
    }

    private ObservablePublish(ObservableSource<T> onSubscribe, ObservableSource<T> source, AtomicReference<PublishObserver<T>> current) {
        this.onSubscribe = onSubscribe;
        this.source = source;
        this.current = current;
    }

    @Override // io.reactivex.internal.fuseable.HasUpstreamObservableSource
    public ObservableSource<T> source() {
        return this.source;
    }

    @Override // io.reactivex.internal.operators.observable.ObservablePublishClassic
    public ObservableSource<T> publishSource() {
        return this.source;
    }

    @Override // io.reactivex.Observable
    protected void subscribeActual(Observer<? super T> observer) {
        this.onSubscribe.subscribe(observer);
    }

    @Override // io.reactivex.observables.ConnectableObservable
    public void connect(Consumer<? super Disposable> connection) {
        PublishObserver<T> ps;
        while (true) {
            ps = this.current.get();
            if (ps != null && !ps.isDisposed()) {
                break;
            }
            PublishObserver<T> u = new PublishObserver<>(this.current);
            if (this.current.compareAndSet(ps, u)) {
                ps = u;
                break;
            }
        }
        boolean z = true;
        if (ps.shouldConnect.get() || !ps.shouldConnect.compareAndSet(false, true)) {
            z = false;
        }
        boolean doConnect = z;
        try {
            connection.accept(ps);
            if (doConnect) {
                this.source.subscribe(ps);
            }
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            throw ExceptionHelper.wrapOrThrow(ex);
        }
    }

    /* loaded from: classes.dex */
    static final class PublishObserver<T> implements Observer<T>, Disposable {
        static final InnerDisposable[] EMPTY = new InnerDisposable[0];
        static final InnerDisposable[] TERMINATED = new InnerDisposable[0];
        final AtomicReference<PublishObserver<T>> current;
        final AtomicReference<Disposable> upstream = new AtomicReference<>();
        final AtomicReference<InnerDisposable<T>[]> observers = new AtomicReference<>(EMPTY);
        final AtomicBoolean shouldConnect = new AtomicBoolean();

        PublishObserver(AtomicReference<PublishObserver<T>> current) {
            this.current = current;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            AtomicReference<InnerDisposable<T>[]> atomicReference = this.observers;
            InnerDisposable[] innerDisposableArr = TERMINATED;
            InnerDisposable[] ps = atomicReference.getAndSet(innerDisposableArr);
            if (ps != innerDisposableArr) {
                this.current.compareAndSet(this, null);
                DisposableHelper.dispose(this.upstream);
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.observers.get() == TERMINATED;
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable d) {
            DisposableHelper.setOnce(this.upstream, d);
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            InnerDisposable<T>[] innerDisposableArr;
            for (InnerDisposable<T> inner : this.observers.get()) {
                inner.child.onNext(t);
            }
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable e) {
            this.current.compareAndSet(this, null);
            InnerDisposable<T>[] a = this.observers.getAndSet(TERMINATED);
            if (a.length != 0) {
                for (InnerDisposable<T> inner : a) {
                    inner.child.onError(e);
                }
                return;
            }
            RxJavaPlugins.onError(e);
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            InnerDisposable<T>[] andSet;
            this.current.compareAndSet(this, null);
            for (InnerDisposable<T> inner : this.observers.getAndSet(TERMINATED)) {
                inner.child.onComplete();
            }
        }

        boolean add(InnerDisposable<T> producer) {
            InnerDisposable<T>[] c;
            InnerDisposable<T>[] u;
            do {
                c = this.observers.get();
                if (c == TERMINATED) {
                    return false;
                }
                int len = c.length;
                u = new InnerDisposable[len + 1];
                System.arraycopy(c, 0, u, 0, len);
                u[len] = producer;
            } while (!this.observers.compareAndSet(c, u));
            return true;
        }

        /* JADX WARN: Multi-variable type inference failed */
        void remove(InnerDisposable<T> producer) {
            InnerDisposable<T>[] c;
            InnerDisposable<T>[] u;
            do {
                c = this.observers.get();
                int len = c.length;
                if (len == 0) {
                    return;
                }
                int j = -1;
                int i = 0;
                while (true) {
                    if (i >= len) {
                        break;
                    } else if (!c[i].equals(producer)) {
                        i++;
                    } else {
                        j = i;
                        break;
                    }
                }
                if (j < 0) {
                    return;
                }
                if (len == 1) {
                    u = EMPTY;
                } else {
                    InnerDisposable<T>[] u2 = new InnerDisposable[len - 1];
                    System.arraycopy(c, 0, u2, 0, j);
                    System.arraycopy(c, j + 1, u2, j, (len - j) - 1);
                    u = u2;
                }
            } while (!this.observers.compareAndSet(c, u));
        }
    }

    /* loaded from: classes.dex */
    static final class InnerDisposable<T> extends AtomicReference<Object> implements Disposable {
        private static final long serialVersionUID = -1100270633763673112L;
        final Observer<? super T> child;

        InnerDisposable(Observer<? super T> child) {
            this.child = child;
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return get() == this;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            Object o = getAndSet(this);
            if (o != null && o != this) {
                ((PublishObserver) o).remove(this);
            }
        }

        void setParent(PublishObserver<T> p) {
            if (!compareAndSet(null, p)) {
                p.remove(this);
            }
        }
    }

    /* loaded from: classes.dex */
    static final class PublishSource<T> implements ObservableSource<T> {
        private final AtomicReference<PublishObserver<T>> curr;

        PublishSource(AtomicReference<PublishObserver<T>> curr) {
            this.curr = curr;
        }

        @Override // io.reactivex.ObservableSource
        public void subscribe(Observer<? super T> child) {
            InnerDisposable<T> inner = new InnerDisposable<>(child);
            child.onSubscribe(inner);
            while (true) {
                PublishObserver<T> r = this.curr.get();
                if (r == null || r.isDisposed()) {
                    PublishObserver<T> u = new PublishObserver<>(this.curr);
                    if (this.curr.compareAndSet(r, u)) {
                        r = u;
                    } else {
                        continue;
                    }
                }
                if (r.add(inner)) {
                    inner.setParent(r);
                    return;
                }
            }
        }
    }
}
