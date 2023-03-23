package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.ResettableConnectable;
import io.reactivex.internal.fuseable.HasUpstreamObservableSource;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.observables.ConnectableObservable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservablePublishAlt<T> extends ConnectableObservable<T> implements HasUpstreamObservableSource<T>, ResettableConnectable {
    final AtomicReference<PublishConnection<T>> current = new AtomicReference<>();
    final ObservableSource<T> source;

    public ObservablePublishAlt(ObservableSource<T> source2) {
        this.source = source2;
    }

    public void connect(Consumer<? super Disposable> connection) {
        PublishConnection<T> conn;
        while (true) {
            conn = this.current.get();
            if (conn != null && !conn.isDisposed()) {
                break;
            }
            PublishConnection<T> fresh = new PublishConnection<>(this.current);
            if (this.current.compareAndSet(conn, fresh)) {
                conn = fresh;
                break;
            }
        }
        boolean z = true;
        if (conn.connect.get() || !conn.connect.compareAndSet(false, true)) {
            z = false;
        }
        boolean doConnect = z;
        try {
            connection.accept(conn);
            if (doConnect) {
                this.source.subscribe(conn);
            }
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            throw ExceptionHelper.wrapOrThrow(ex);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void subscribeActual(io.reactivex.Observer<? super T> r4) {
        /*
            r3 = this;
        L_0x0000:
            java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.observable.ObservablePublishAlt$PublishConnection<T>> r0 = r3.current
            java.lang.Object r0 = r0.get()
            io.reactivex.internal.operators.observable.ObservablePublishAlt$PublishConnection r0 = (io.reactivex.internal.operators.observable.ObservablePublishAlt.PublishConnection) r0
            if (r0 != 0) goto L_0x001b
            io.reactivex.internal.operators.observable.ObservablePublishAlt$PublishConnection r1 = new io.reactivex.internal.operators.observable.ObservablePublishAlt$PublishConnection
            java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.observable.ObservablePublishAlt$PublishConnection<T>> r2 = r3.current
            r1.<init>(r2)
            java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.observable.ObservablePublishAlt$PublishConnection<T>> r2 = r3.current
            boolean r2 = r2.compareAndSet(r0, r1)
            if (r2 != 0) goto L_0x001a
            goto L_0x0000
        L_0x001a:
            r0 = r1
        L_0x001b:
            io.reactivex.internal.operators.observable.ObservablePublishAlt$InnerDisposable r1 = new io.reactivex.internal.operators.observable.ObservablePublishAlt$InnerDisposable
            r1.<init>(r4, r0)
            r4.onSubscribe(r1)
            boolean r2 = r0.add(r1)
            if (r2 == 0) goto L_0x0033
            boolean r2 = r1.isDisposed()
            if (r2 == 0) goto L_0x0032
            r0.remove(r1)
        L_0x0032:
            return
        L_0x0033:
            java.lang.Throwable r2 = r0.error
            if (r2 == 0) goto L_0x003b
            r4.onError(r2)
            goto L_0x003e
        L_0x003b:
            r4.onComplete()
        L_0x003e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservablePublishAlt.subscribeActual(io.reactivex.Observer):void");
    }

    public void resetIf(Disposable connection) {
        this.current.compareAndSet((PublishConnection) connection, (Object) null);
    }

    public ObservableSource<T> source() {
        return this.source;
    }

    static final class PublishConnection<T> extends AtomicReference<InnerDisposable<T>[]> implements Observer<T>, Disposable {
        static final InnerDisposable[] EMPTY = new InnerDisposable[0];
        static final InnerDisposable[] TERMINATED = new InnerDisposable[0];
        private static final long serialVersionUID = -3251430252873581268L;
        final AtomicBoolean connect = new AtomicBoolean();
        final AtomicReference<PublishConnection<T>> current;
        Throwable error;
        final AtomicReference<Disposable> upstream;

        PublishConnection(AtomicReference<PublishConnection<T>> current2) {
            this.current = current2;
            this.upstream = new AtomicReference<>();
            lazySet(EMPTY);
        }

        public void dispose() {
            getAndSet(TERMINATED);
            this.current.compareAndSet(this, (Object) null);
            DisposableHelper.dispose(this.upstream);
        }

        public boolean isDisposed() {
            return get() == TERMINATED;
        }

        public void onSubscribe(Disposable d) {
            DisposableHelper.setOnce(this.upstream, d);
        }

        public void onNext(T t) {
            for (InnerDisposable<T> inner : (InnerDisposable[]) get()) {
                inner.downstream.onNext(t);
            }
        }

        public void onError(Throwable e) {
            this.error = e;
            this.upstream.lazySet(DisposableHelper.DISPOSED);
            for (InnerDisposable<T> inner : (InnerDisposable[]) getAndSet(TERMINATED)) {
                inner.downstream.onError(e);
            }
        }

        public void onComplete() {
            this.upstream.lazySet(DisposableHelper.DISPOSED);
            for (InnerDisposable<T> inner : (InnerDisposable[]) getAndSet(TERMINATED)) {
                inner.downstream.onComplete();
            }
        }

        public boolean add(InnerDisposable<T> inner) {
            InnerDisposable<T>[] a;
            InnerDisposable<T>[] b;
            do {
                a = (InnerDisposable[]) get();
                if (a == TERMINATED) {
                    return false;
                }
                int n = a.length;
                b = new InnerDisposable[(n + 1)];
                System.arraycopy(a, 0, b, 0, n);
                b[n] = inner;
            } while (!compareAndSet(a, b));
            return true;
        }

        public void remove(InnerDisposable<T> inner) {
            InnerDisposable<T>[] a;
            InnerDisposable<T>[] b;
            do {
                a = (InnerDisposable[]) get();
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
                        b = EMPTY;
                        if (n != 1) {
                            b = new InnerDisposable[(n - 1)];
                            System.arraycopy(a, 0, b, 0, j);
                            System.arraycopy(a, j + 1, b, j, (n - j) - 1);
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } while (!compareAndSet(a, b));
        }
    }

    static final class InnerDisposable<T> extends AtomicReference<PublishConnection<T>> implements Disposable {
        private static final long serialVersionUID = 7463222674719692880L;
        final Observer<? super T> downstream;

        InnerDisposable(Observer<? super T> downstream2, PublishConnection<T> parent) {
            this.downstream = downstream2;
            lazySet(parent);
        }

        public void dispose() {
            PublishConnection<T> p = (PublishConnection) getAndSet((Object) null);
            if (p != null) {
                p.remove(this);
            }
        }

        public boolean isDisposed() {
            return get() == null;
        }
    }
}
