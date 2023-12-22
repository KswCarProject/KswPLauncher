package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes.dex */
public final class FlowableZip<T, R> extends Flowable<R> {
    final int bufferSize;
    final boolean delayError;
    final Publisher<? extends T>[] sources;
    final Iterable<? extends Publisher<? extends T>> sourcesIterable;
    final Function<? super Object[], ? extends R> zipper;

    public FlowableZip(Publisher<? extends T>[] sources, Iterable<? extends Publisher<? extends T>> sourcesIterable, Function<? super Object[], ? extends R> zipper, int bufferSize, boolean delayError) {
        this.sources = sources;
        this.sourcesIterable = sourcesIterable;
        this.zipper = zipper;
        this.bufferSize = bufferSize;
        this.delayError = delayError;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super R> s) {
        int count;
        Publisher<? extends T>[] sources = this.sources;
        int count2 = 0;
        if (sources == null) {
            sources = new Publisher[8];
            for (Publisher<? extends T> p : this.sourcesIterable) {
                if (count2 == sources.length) {
                    Publisher<? extends T>[] b = new Publisher[(count2 >> 2) + count2];
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
            EmptySubscription.complete(s);
            return;
        }
        ZipCoordinator<T, R> coordinator = new ZipCoordinator<>(s, this.zipper, count, this.bufferSize, this.delayError);
        s.onSubscribe(coordinator);
        coordinator.subscribe(sources, count);
    }

    /* loaded from: classes.dex */
    static final class ZipCoordinator<T, R> extends AtomicInteger implements Subscription {
        private static final long serialVersionUID = -2434867452883857743L;
        volatile boolean cancelled;
        final Object[] current;
        final boolean delayErrors;
        final Subscriber<? super R> downstream;
        final AtomicThrowable errors;
        final AtomicLong requested;
        final ZipSubscriber<T, R>[] subscribers;
        final Function<? super Object[], ? extends R> zipper;

        ZipCoordinator(Subscriber<? super R> actual, Function<? super Object[], ? extends R> zipper, int n, int prefetch, boolean delayErrors) {
            this.downstream = actual;
            this.zipper = zipper;
            this.delayErrors = delayErrors;
            ZipSubscriber<T, R>[] a = new ZipSubscriber[n];
            for (int i = 0; i < n; i++) {
                a[i] = new ZipSubscriber<>(this, prefetch);
            }
            this.current = new Object[n];
            this.subscribers = a;
            this.requested = new AtomicLong();
            this.errors = new AtomicThrowable();
        }

        void subscribe(Publisher<? extends T>[] sources, int n) {
            ZipSubscriber<T, R>[] a = this.subscribers;
            for (int i = 0; i < n && !this.cancelled; i++) {
                if (!this.delayErrors && this.errors.get() != null) {
                    return;
                }
                sources[i].subscribe(a[i]);
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this.requested, n);
                drain();
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelAll();
            }
        }

        void error(ZipSubscriber<T, R> inner, Throwable e) {
            if (this.errors.addThrowable(e)) {
                inner.done = true;
                drain();
                return;
            }
            RxJavaPlugins.onError(e);
        }

        void cancelAll() {
            ZipSubscriber<T, R>[] zipSubscriberArr;
            for (ZipSubscriber<T, R> s : this.subscribers) {
                s.cancel();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:100:0x0170, code lost:
            r3[r12].request(r9);
            r12 = r12 + 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:102:0x017f, code lost:
            if (r7 == kotlin.jvm.internal.LongCompanionObject.MAX_VALUE) goto L124;
         */
        /* JADX WARN: Code restructure failed: missing block: B:103:0x0181, code lost:
            r19.requested.addAndGet(-r9);
         */
        /* JADX WARN: Code restructure failed: missing block: B:104:0x0187, code lost:
            r6 = addAndGet(-r6);
         */
        /* JADX WARN: Code restructure failed: missing block: B:135:?, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x00e1, code lost:
            if (r7 != r9) goto L115;
         */
        /* JADX WARN: Code restructure failed: missing block: B:60:0x00e5, code lost:
            if (r19.cancelled == false) goto L70;
         */
        /* JADX WARN: Code restructure failed: missing block: B:61:0x00e7, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:63:0x00ea, code lost:
            if (r19.delayErrors != false) goto L77;
         */
        /* JADX WARN: Code restructure failed: missing block: B:65:0x00f2, code lost:
            if (r19.errors.get() == null) goto L77;
         */
        /* JADX WARN: Code restructure failed: missing block: B:66:0x00f4, code lost:
            cancelAll();
            r2.onError(r19.errors.terminate());
         */
        /* JADX WARN: Code restructure failed: missing block: B:67:0x0100, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:68:0x0101, code lost:
            r12 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:69:0x0103, code lost:
            if (r12 >= r4) goto L112;
         */
        /* JADX WARN: Code restructure failed: missing block: B:70:0x0105, code lost:
            r13 = r3[r12];
         */
        /* JADX WARN: Code restructure failed: missing block: B:71:0x0109, code lost:
            if (r5[r12] != null) goto L111;
         */
        /* JADX WARN: Code restructure failed: missing block: B:72:0x010b, code lost:
            r0 = r13.done;
            r14 = r13.queue;
         */
        /* JADX WARN: Code restructure failed: missing block: B:73:0x010f, code lost:
            if (r14 == null) goto L103;
         */
        /* JADX WARN: Code restructure failed: missing block: B:74:0x0111, code lost:
            r15 = r14.poll();
         */
        /* JADX WARN: Code restructure failed: missing block: B:75:0x0116, code lost:
            r15 = r11;
         */
        /* JADX WARN: Code restructure failed: missing block: B:76:0x0117, code lost:
            if (r15 != null) goto L102;
         */
        /* JADX WARN: Code restructure failed: missing block: B:77:0x0119, code lost:
            r16 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:78:0x011c, code lost:
            r16 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:79:0x011e, code lost:
            if (r0 == false) goto L96;
         */
        /* JADX WARN: Code restructure failed: missing block: B:80:0x0120, code lost:
            if (r16 == false) goto L96;
         */
        /* JADX WARN: Code restructure failed: missing block: B:81:0x0122, code lost:
            cancelAll();
         */
        /* JADX WARN: Code restructure failed: missing block: B:82:0x012d, code lost:
            if (r19.errors.get() == null) goto L94;
         */
        /* JADX WARN: Code restructure failed: missing block: B:83:0x012f, code lost:
            r2.onError(r19.errors.terminate());
         */
        /* JADX WARN: Code restructure failed: missing block: B:84:0x013b, code lost:
            r2.onComplete();
         */
        /* JADX WARN: Code restructure failed: missing block: B:85:0x0140, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:87:0x0143, code lost:
            if (r16 != false) goto L101;
         */
        /* JADX WARN: Code restructure failed: missing block: B:88:0x0145, code lost:
            r5[r12] = r15;
         */
        /* JADX WARN: Code restructure failed: missing block: B:90:0x0148, code lost:
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:91:0x0149, code lost:
            io.reactivex.exceptions.Exceptions.throwIfFatal(r0);
            r19.errors.addThrowable(r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:92:0x0153, code lost:
            if (r19.delayErrors == false) goto L107;
         */
        /* JADX WARN: Code restructure failed: missing block: B:93:0x0155, code lost:
            cancelAll();
            r2.onError(r19.errors.terminate());
         */
        /* JADX WARN: Code restructure failed: missing block: B:94:0x0161, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:95:0x0162, code lost:
            r12 = r12 + 1;
            r11 = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:97:0x016a, code lost:
            if (r9 == 0) goto L124;
         */
        /* JADX WARN: Code restructure failed: missing block: B:98:0x016c, code lost:
            r0 = r3.length;
            r12 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:99:0x016e, code lost:
            if (r12 >= r0) goto L120;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        void drain() {
            T t;
            if (getAndIncrement() != 0) {
                return;
            }
            Subscriber<? super R> a = this.downstream;
            ZipSubscriber<T, R>[] qs = this.subscribers;
            int n = qs.length;
            Object[] values = this.current;
            int missed = 1;
            do {
                long r = this.requested.get();
                long e = 0;
                while (true) {
                    if (r == e) {
                        t = null;
                        break;
                    } else if (this.cancelled) {
                        return;
                    } else {
                        if (!this.delayErrors && this.errors.get() != null) {
                            cancelAll();
                            a.onError(this.errors.terminate());
                            return;
                        }
                        boolean empty = false;
                        for (int j = 0; j < n; j++) {
                            ZipSubscriber<T, R> inner = qs[j];
                            if (values[j] == null) {
                                try {
                                    boolean d = inner.done;
                                    SimpleQueue<T> q = inner.queue;
                                    T v = q != null ? q.poll() : null;
                                    boolean sourceEmpty = v == null;
                                    if (d && sourceEmpty) {
                                        cancelAll();
                                        if (this.errors.get() != null) {
                                            a.onError(this.errors.terminate());
                                            return;
                                        } else {
                                            a.onComplete();
                                            return;
                                        }
                                    } else if (!sourceEmpty) {
                                        values[j] = v;
                                    } else {
                                        empty = true;
                                    }
                                } catch (Throwable ex) {
                                    Exceptions.throwIfFatal(ex);
                                    this.errors.addThrowable(ex);
                                    if (!this.delayErrors) {
                                        cancelAll();
                                        a.onError(this.errors.terminate());
                                        return;
                                    }
                                    empty = true;
                                }
                            }
                        }
                        if (empty) {
                            t = null;
                            break;
                        }
                        try {
                            a.onNext((Object) ObjectHelper.requireNonNull(this.zipper.apply(values.clone()), "The zipper returned a null value"));
                            e++;
                            Arrays.fill(values, (Object) null);
                        } catch (Throwable ex2) {
                            Exceptions.throwIfFatal(ex2);
                            cancelAll();
                            this.errors.addThrowable(ex2);
                            a.onError(this.errors.terminate());
                            return;
                        }
                    }
                }
            } while (missed != 0);
        }
    }

    /* loaded from: classes.dex */
    static final class ZipSubscriber<T, R> extends AtomicReference<Subscription> implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = -4627193790118206028L;
        volatile boolean done;
        final int limit;
        final ZipCoordinator<T, R> parent;
        final int prefetch;
        long produced;
        SimpleQueue<T> queue;
        int sourceMode;

        ZipSubscriber(ZipCoordinator<T, R> parent, int prefetch) {
            this.parent = parent;
            this.prefetch = prefetch;
            this.limit = prefetch - (prefetch >> 2);
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.setOnce(this, s)) {
                if (s instanceof QueueSubscription) {
                    QueueSubscription<T> f = (QueueSubscription) s;
                    int m = f.requestFusion(7);
                    if (m == 1) {
                        this.sourceMode = m;
                        this.queue = f;
                        this.done = true;
                        this.parent.drain();
                        return;
                    } else if (m == 2) {
                        this.sourceMode = m;
                        this.queue = f;
                        s.request(this.prefetch);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.prefetch);
                s.request(this.prefetch);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.sourceMode != 2) {
                this.queue.offer(t);
            }
            this.parent.drain();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable t) {
            this.parent.error(this, t);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.done = true;
            this.parent.drain();
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            SubscriptionHelper.cancel(this);
        }

        @Override // org.reactivestreams.Subscription
        public void request(long n) {
            if (this.sourceMode != 1) {
                long p = this.produced + n;
                if (p >= this.limit) {
                    this.produced = 0L;
                    get().request(p);
                    return;
                }
                this.produced = p;
            }
        }
    }
}
