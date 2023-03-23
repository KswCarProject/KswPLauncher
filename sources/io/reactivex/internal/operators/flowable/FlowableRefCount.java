package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.flowables.ConnectableFlowable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.ResettableConnectable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableRefCount<T> extends Flowable<T> {
    RefConnection connection;
    final int n;
    final Scheduler scheduler;
    final ConnectableFlowable<T> source;
    final long timeout;
    final TimeUnit unit;

    public FlowableRefCount(ConnectableFlowable<T> source2) {
        this(source2, 1, 0, TimeUnit.NANOSECONDS, (Scheduler) null);
    }

    public FlowableRefCount(ConnectableFlowable<T> source2, int n2, long timeout2, TimeUnit unit2, Scheduler scheduler2) {
        this.source = source2;
        this.n = n2;
        this.timeout = timeout2;
        this.unit = unit2;
        this.scheduler = scheduler2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super T> s) {
        RefConnection conn;
        boolean connect = false;
        synchronized (this) {
            conn = this.connection;
            if (conn == null) {
                conn = new RefConnection(this);
                this.connection = conn;
            }
            long c = conn.subscriberCount;
            if (c == 0 && conn.timer != null) {
                conn.timer.dispose();
            }
            conn.subscriberCount = c + 1;
            if (!conn.connected && 1 + c == ((long) this.n)) {
                connect = true;
                conn.connected = true;
            }
        }
        this.source.subscribe(new RefCountSubscriber(s, this, conn));
        if (connect) {
            this.source.connect(conn);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x003e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void cancel(io.reactivex.internal.operators.flowable.FlowableRefCount.RefConnection r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            io.reactivex.internal.operators.flowable.FlowableRefCount$RefConnection r0 = r6.connection     // Catch:{ all -> 0x003f }
            if (r0 == 0) goto L_0x003d
            if (r0 == r7) goto L_0x0008
            goto L_0x003d
        L_0x0008:
            long r0 = r7.subscriberCount     // Catch:{ all -> 0x003f }
            r2 = 1
            long r0 = r0 - r2
            r7.subscriberCount = r0     // Catch:{ all -> 0x003f }
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L_0x003b
            boolean r4 = r7.connected     // Catch:{ all -> 0x003f }
            if (r4 != 0) goto L_0x001a
            goto L_0x003b
        L_0x001a:
            long r4 = r6.timeout     // Catch:{ all -> 0x003f }
            int r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r2 != 0) goto L_0x0025
            r6.timeout(r7)     // Catch:{ all -> 0x003f }
            monitor-exit(r6)     // Catch:{ all -> 0x003f }
            return
        L_0x0025:
            io.reactivex.internal.disposables.SequentialDisposable r2 = new io.reactivex.internal.disposables.SequentialDisposable     // Catch:{ all -> 0x003f }
            r2.<init>()     // Catch:{ all -> 0x003f }
            r7.timer = r2     // Catch:{ all -> 0x003f }
            monitor-exit(r6)     // Catch:{ all -> 0x003f }
            io.reactivex.Scheduler r0 = r6.scheduler
            long r3 = r6.timeout
            java.util.concurrent.TimeUnit r1 = r6.unit
            io.reactivex.disposables.Disposable r0 = r0.scheduleDirect(r7, r3, r1)
            r2.replace(r0)
            return
        L_0x003b:
            monitor-exit(r6)     // Catch:{ all -> 0x003f }
            return
        L_0x003d:
            monitor-exit(r6)     // Catch:{ all -> 0x003f }
            return
        L_0x003f:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x003f }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableRefCount.cancel(io.reactivex.internal.operators.flowable.FlowableRefCount$RefConnection):void");
    }

    /* access modifiers changed from: package-private */
    public void terminated(RefConnection rc) {
        synchronized (this) {
            if (this.source instanceof FlowablePublishClassic) {
                RefConnection refConnection = this.connection;
                if (refConnection != null && refConnection == rc) {
                    this.connection = null;
                    clearTimer(rc);
                }
                long j = rc.subscriberCount - 1;
                rc.subscriberCount = j;
                if (j == 0) {
                    reset(rc);
                }
            } else {
                RefConnection refConnection2 = this.connection;
                if (refConnection2 != null && refConnection2 == rc) {
                    clearTimer(rc);
                    long j2 = rc.subscriberCount - 1;
                    rc.subscriberCount = j2;
                    if (j2 == 0) {
                        this.connection = null;
                        reset(rc);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void clearTimer(RefConnection rc) {
        if (rc.timer != null) {
            rc.timer.dispose();
            rc.timer = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void reset(RefConnection rc) {
        ConnectableFlowable<T> connectableFlowable = this.source;
        if (connectableFlowable instanceof Disposable) {
            ((Disposable) connectableFlowable).dispose();
        } else if (connectableFlowable instanceof ResettableConnectable) {
            ((ResettableConnectable) connectableFlowable).resetIf((Disposable) rc.get());
        }
    }

    /* access modifiers changed from: package-private */
    public void timeout(RefConnection rc) {
        synchronized (this) {
            if (rc.subscriberCount == 0 && rc == this.connection) {
                this.connection = null;
                Disposable connectionObject = (Disposable) rc.get();
                DisposableHelper.dispose(rc);
                ConnectableFlowable<T> connectableFlowable = this.source;
                if (connectableFlowable instanceof Disposable) {
                    ((Disposable) connectableFlowable).dispose();
                } else if (connectableFlowable instanceof ResettableConnectable) {
                    if (connectionObject == null) {
                        rc.disconnectedEarly = true;
                    } else {
                        ((ResettableConnectable) connectableFlowable).resetIf(connectionObject);
                    }
                }
            }
        }
    }

    static final class RefConnection extends AtomicReference<Disposable> implements Runnable, Consumer<Disposable> {
        private static final long serialVersionUID = -4552101107598366241L;
        boolean connected;
        boolean disconnectedEarly;
        final FlowableRefCount<?> parent;
        long subscriberCount;
        Disposable timer;

        RefConnection(FlowableRefCount<?> parent2) {
            this.parent = parent2;
        }

        public void run() {
            this.parent.timeout(this);
        }

        public void accept(Disposable t) throws Exception {
            DisposableHelper.replace(this, t);
            synchronized (this.parent) {
                if (this.disconnectedEarly) {
                    ((ResettableConnectable) this.parent.source).resetIf(t);
                }
            }
        }
    }

    static final class RefCountSubscriber<T> extends AtomicBoolean implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = -7419642935409022375L;
        final RefConnection connection;
        final Subscriber<? super T> downstream;
        final FlowableRefCount<T> parent;
        Subscription upstream;

        RefCountSubscriber(Subscriber<? super T> actual, FlowableRefCount<T> parent2, RefConnection connection2) {
            this.downstream = actual;
            this.parent = parent2;
            this.connection = connection2;
        }

        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        public void onError(Throwable t) {
            if (compareAndSet(false, true)) {
                this.parent.terminated(this.connection);
                this.downstream.onError(t);
                return;
            }
            RxJavaPlugins.onError(t);
        }

        public void onComplete() {
            if (compareAndSet(false, true)) {
                this.parent.terminated(this.connection);
                this.downstream.onComplete();
            }
        }

        public void request(long n) {
            this.upstream.request(n);
        }

        public void cancel() {
            this.upstream.cancel();
            if (compareAndSet(false, true)) {
                this.parent.cancel(this.connection);
            }
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.upstream, s)) {
                this.upstream = s;
                this.downstream.onSubscribe(this);
            }
        }
    }
}
