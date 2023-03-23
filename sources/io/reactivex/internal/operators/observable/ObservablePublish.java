package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.fuseable.HasUpstreamObservableSource;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservablePublish<T> extends ConnectableObservable<T> implements HasUpstreamObservableSource<T>, ObservablePublishClassic<T> {
    final AtomicReference<PublishObserver<T>> current;
    final ObservableSource<T> onSubscribe;
    final ObservableSource<T> source;

    public static <T> ConnectableObservable<T> create(ObservableSource<T> source2) {
        AtomicReference<PublishObserver<T>> curr = new AtomicReference<>();
        return RxJavaPlugins.onAssembly(new ObservablePublish(new PublishSource<>(curr), source2, curr));
    }

    private ObservablePublish(ObservableSource<T> onSubscribe2, ObservableSource<T> source2, AtomicReference<PublishObserver<T>> current2) {
        this.onSubscribe = onSubscribe2;
        this.source = source2;
        this.current = current2;
    }

    public ObservableSource<T> source() {
        return this.source;
    }

    public ObservableSource<T> publishSource() {
        return this.source;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Observer<? super T> observer) {
        this.onSubscribe.subscribe(observer);
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void connect(io.reactivex.functions.Consumer<? super io.reactivex.disposables.Disposable> r5) {
        /*
            r4 = this;
        L_0x0000:
            java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.observable.ObservablePublish$PublishObserver<T>> r0 = r4.current
            java.lang.Object r0 = r0.get()
            io.reactivex.internal.operators.observable.ObservablePublish$PublishObserver r0 = (io.reactivex.internal.operators.observable.ObservablePublish.PublishObserver) r0
            if (r0 == 0) goto L_0x0010
            boolean r1 = r0.isDisposed()
            if (r1 == 0) goto L_0x0021
        L_0x0010:
            io.reactivex.internal.operators.observable.ObservablePublish$PublishObserver r1 = new io.reactivex.internal.operators.observable.ObservablePublish$PublishObserver
            java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.observable.ObservablePublish$PublishObserver<T>> r2 = r4.current
            r1.<init>(r2)
            java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.observable.ObservablePublish$PublishObserver<T>> r2 = r4.current
            boolean r2 = r2.compareAndSet(r0, r1)
            if (r2 != 0) goto L_0x0020
            goto L_0x0000
        L_0x0020:
            r0 = r1
        L_0x0021:
            java.util.concurrent.atomic.AtomicBoolean r1 = r0.shouldConnect
            boolean r1 = r1.get()
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L_0x0034
            java.util.concurrent.atomic.AtomicBoolean r1 = r0.shouldConnect
            boolean r1 = r1.compareAndSet(r3, r2)
            if (r1 == 0) goto L_0x0034
            goto L_0x0035
        L_0x0034:
            r2 = r3
        L_0x0035:
            r1 = r2
            r5.accept(r0)     // Catch:{ all -> 0x0043 }
            if (r1 == 0) goto L_0x0042
            io.reactivex.ObservableSource<T> r2 = r4.source
            r2.subscribe(r0)
        L_0x0042:
            return
        L_0x0043:
            r2 = move-exception
            io.reactivex.exceptions.Exceptions.throwIfFatal(r2)
            java.lang.RuntimeException r3 = io.reactivex.internal.util.ExceptionHelper.wrapOrThrow(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservablePublish.connect(io.reactivex.functions.Consumer):void");
    }

    static final class PublishObserver<T> implements Observer<T>, Disposable {
        static final InnerDisposable[] EMPTY = new InnerDisposable[0];
        static final InnerDisposable[] TERMINATED = new InnerDisposable[0];
        final AtomicReference<PublishObserver<T>> current;
        final AtomicReference<InnerDisposable<T>[]> observers = new AtomicReference<>(EMPTY);
        final AtomicBoolean shouldConnect;
        final AtomicReference<Disposable> upstream = new AtomicReference<>();

        PublishObserver(AtomicReference<PublishObserver<T>> current2) {
            this.current = current2;
            this.shouldConnect = new AtomicBoolean();
        }

        public void dispose() {
            AtomicReference<InnerDisposable<T>[]> atomicReference = this.observers;
            InnerDisposable[] innerDisposableArr = TERMINATED;
            if (((InnerDisposable[]) atomicReference.getAndSet(innerDisposableArr)) != innerDisposableArr) {
                this.current.compareAndSet(this, (Object) null);
                DisposableHelper.dispose(this.upstream);
            }
        }

        public boolean isDisposed() {
            return this.observers.get() == TERMINATED;
        }

        public void onSubscribe(Disposable d) {
            DisposableHelper.setOnce(this.upstream, d);
        }

        public void onNext(T t) {
            for (InnerDisposable<T> inner : (InnerDisposable[]) this.observers.get()) {
                inner.child.onNext(t);
            }
        }

        public void onError(Throwable e) {
            this.current.compareAndSet(this, (Object) null);
            InnerDisposable<T>[] a = (InnerDisposable[]) this.observers.getAndSet(TERMINATED);
            if (a.length != 0) {
                for (InnerDisposable<T> inner : a) {
                    inner.child.onError(e);
                }
                return;
            }
            RxJavaPlugins.onError(e);
        }

        public void onComplete() {
            this.current.compareAndSet(this, (Object) null);
            for (InnerDisposable<T> inner : (InnerDisposable[]) this.observers.getAndSet(TERMINATED)) {
                inner.child.onComplete();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean add(InnerDisposable<T> producer) {
            InnerDisposable<T>[] c;
            InnerDisposable<T>[] u;
            do {
                c = (InnerDisposable[]) this.observers.get();
                if (c == TERMINATED) {
                    return false;
                }
                int len = c.length;
                u = new InnerDisposable[(len + 1)];
                System.arraycopy(c, 0, u, 0, len);
                u[len] = producer;
            } while (!this.observers.compareAndSet(c, u));
            return true;
        }

        /* access modifiers changed from: package-private */
        public void remove(InnerDisposable<T> producer) {
            InnerDisposable<T>[] c;
            InnerDisposable<T>[] u;
            do {
                c = (InnerDisposable[]) this.observers.get();
                int len = c.length;
                if (len != 0) {
                    int j = -1;
                    int i = 0;
                    while (true) {
                        if (i >= len) {
                            break;
                        } else if (c[i].equals(producer)) {
                            j = i;
                            break;
                        } else {
                            i++;
                        }
                    }
                    if (j >= 0) {
                        if (len == 1) {
                            u = EMPTY;
                        } else {
                            InnerDisposable<T>[] u2 = new InnerDisposable[(len - 1)];
                            System.arraycopy(c, 0, u2, 0, j);
                            System.arraycopy(c, j + 1, u2, j, (len - j) - 1);
                            u = u2;
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } while (!this.observers.compareAndSet(c, u));
        }
    }

    static final class InnerDisposable<T> extends AtomicReference<Object> implements Disposable {
        private static final long serialVersionUID = -1100270633763673112L;
        final Observer<? super T> child;

        InnerDisposable(Observer<? super T> child2) {
            this.child = child2;
        }

        public boolean isDisposed() {
            return get() == this;
        }

        public void dispose() {
            Object o = getAndSet(this);
            if (o != null && o != this) {
                ((PublishObserver) o).remove(this);
            }
        }

        /* access modifiers changed from: package-private */
        public void setParent(PublishObserver<T> p) {
            if (!compareAndSet((Object) null, p)) {
                p.remove(this);
            }
        }
    }

    static final class PublishSource<T> implements ObservableSource<T> {
        private final AtomicReference<PublishObserver<T>> curr;

        PublishSource(AtomicReference<PublishObserver<T>> curr2) {
            this.curr = curr2;
        }

        public void subscribe(Observer<? super T> child) {
            InnerDisposable<T> inner = new InnerDisposable<>(child);
            child.onSubscribe(inner);
            while (true) {
                PublishObserver<T> r = this.curr.get();
                if (r == null || r.isDisposed()) {
                    PublishObserver<T> u = new PublishObserver<>(this.curr);
                    if (!this.curr.compareAndSet(r, u)) {
                        continue;
                    } else {
                        r = u;
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
