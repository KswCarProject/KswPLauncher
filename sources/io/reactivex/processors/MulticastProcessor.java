package io.reactivex.processors;

import io.reactivex.annotations.BackpressureKind;
import io.reactivex.annotations.BackpressureSupport;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.SchedulerSupport;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.internal.LongCompanionObject;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@BackpressureSupport(BackpressureKind.FULL)
@SchedulerSupport("none")
public final class MulticastProcessor<T> extends FlowableProcessor<T> {
    static final MulticastSubscription[] EMPTY = new MulticastSubscription[0];
    static final MulticastSubscription[] TERMINATED = new MulticastSubscription[0];
    final int bufferSize;
    int consumed;
    volatile boolean done;
    volatile Throwable error;
    int fusionMode;
    final int limit;
    final AtomicBoolean once;
    volatile SimpleQueue<T> queue;
    final boolean refcount;
    final AtomicReference<MulticastSubscription<T>[]> subscribers = new AtomicReference<>(EMPTY);
    final AtomicReference<Subscription> upstream = new AtomicReference<>();
    final AtomicInteger wip = new AtomicInteger();

    @CheckReturnValue
    public static <T> MulticastProcessor<T> create() {
        return new MulticastProcessor<>(bufferSize(), false);
    }

    @CheckReturnValue
    public static <T> MulticastProcessor<T> create(boolean refCount) {
        return new MulticastProcessor<>(bufferSize(), refCount);
    }

    @CheckReturnValue
    public static <T> MulticastProcessor<T> create(int bufferSize2) {
        return new MulticastProcessor<>(bufferSize2, false);
    }

    @CheckReturnValue
    public static <T> MulticastProcessor<T> create(int bufferSize2, boolean refCount) {
        return new MulticastProcessor<>(bufferSize2, refCount);
    }

    MulticastProcessor(int bufferSize2, boolean refCount) {
        ObjectHelper.verifyPositive(bufferSize2, "bufferSize");
        this.bufferSize = bufferSize2;
        this.limit = bufferSize2 - (bufferSize2 >> 2);
        this.refcount = refCount;
        this.once = new AtomicBoolean();
    }

    public void start() {
        if (SubscriptionHelper.setOnce(this.upstream, EmptySubscription.INSTANCE)) {
            this.queue = new SpscArrayQueue(this.bufferSize);
        }
    }

    public void startUnbounded() {
        if (SubscriptionHelper.setOnce(this.upstream, EmptySubscription.INSTANCE)) {
            this.queue = new SpscLinkedArrayQueue(this.bufferSize);
        }
    }

    public void onSubscribe(Subscription s) {
        if (SubscriptionHelper.setOnce(this.upstream, s)) {
            if (s instanceof QueueSubscription) {
                QueueSubscription<T> qs = (QueueSubscription) s;
                int m = qs.requestFusion(3);
                if (m == 1) {
                    this.fusionMode = m;
                    this.queue = qs;
                    this.done = true;
                    drain();
                    return;
                } else if (m == 2) {
                    this.fusionMode = m;
                    this.queue = qs;
                    s.request((long) this.bufferSize);
                    return;
                }
            }
            this.queue = new SpscArrayQueue(this.bufferSize);
            s.request((long) this.bufferSize);
        }
    }

    public void onNext(T t) {
        if (!this.once.get()) {
            if (this.fusionMode == 0) {
                ObjectHelper.requireNonNull(t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
                if (!this.queue.offer(t)) {
                    SubscriptionHelper.cancel(this.upstream);
                    onError(new MissingBackpressureException());
                    return;
                }
            }
            drain();
        }
    }

    public boolean offer(T t) {
        if (this.once.get()) {
            return false;
        }
        ObjectHelper.requireNonNull(t, "offer called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.fusionMode != 0 || !this.queue.offer(t)) {
            return false;
        }
        drain();
        return true;
    }

    public void onError(Throwable t) {
        ObjectHelper.requireNonNull(t, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.once.compareAndSet(false, true)) {
            this.error = t;
            this.done = true;
            drain();
            return;
        }
        RxJavaPlugins.onError(t);
    }

    public void onComplete() {
        if (this.once.compareAndSet(false, true)) {
            this.done = true;
            drain();
        }
    }

    public boolean hasSubscribers() {
        return ((MulticastSubscription[]) this.subscribers.get()).length != 0;
    }

    public boolean hasThrowable() {
        return this.once.get() && this.error != null;
    }

    public boolean hasComplete() {
        return this.once.get() && this.error == null;
    }

    public Throwable getThrowable() {
        if (this.once.get()) {
            return this.error;
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super T> s) {
        Throwable ex;
        MulticastSubscription<T> ms = new MulticastSubscription<>(s, this);
        s.onSubscribe(ms);
        if (add(ms)) {
            if (ms.get() == Long.MIN_VALUE) {
                remove(ms);
            } else {
                drain();
            }
        } else if ((this.once.get() || !this.refcount) && (ex = this.error) != null) {
            s.onError(ex);
        } else {
            s.onComplete();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean add(MulticastSubscription<T> inner) {
        MulticastSubscription<T>[] a;
        MulticastSubscription<T>[] b;
        do {
            a = (MulticastSubscription[]) this.subscribers.get();
            if (a == TERMINATED) {
                return false;
            }
            int n = a.length;
            b = new MulticastSubscription[(n + 1)];
            System.arraycopy(a, 0, b, 0, n);
            b[n] = inner;
        } while (!this.subscribers.compareAndSet(a, b));
        return true;
    }

    /* access modifiers changed from: package-private */
    public void remove(MulticastSubscription<T> inner) {
        while (true) {
            MulticastSubscription<T>[] a = (MulticastSubscription[]) this.subscribers.get();
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
                    if (n != 1) {
                        MulticastSubscription<T>[] b = new MulticastSubscription[(n - 1)];
                        System.arraycopy(a, 0, b, 0, j);
                        System.arraycopy(a, j + 1, b, j, (n - j) - 1);
                        if (this.subscribers.compareAndSet(a, b)) {
                            return;
                        }
                    } else if (this.refcount) {
                        if (this.subscribers.compareAndSet(a, TERMINATED)) {
                            SubscriptionHelper.cancel(this.upstream);
                            this.once.set(true);
                            return;
                        }
                    } else if (this.subscribers.compareAndSet(a, EMPTY)) {
                        return;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:106:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00fe, code lost:
        if (r10 != 0) goto L_0x0147;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0100, code lost:
        r0 = (io.reactivex.processors.MulticastProcessor.MulticastSubscription[]) r2.get();
        r8 = TERMINATED;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0108, code lost:
        if (r0 != r8) goto L_0x010e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x010a, code lost:
        r6.clear();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x010d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x010e, code lost:
        if (r7 == r0) goto L_0x0114;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0116, code lost:
        if (r1.done == false) goto L_0x0147;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x011c, code lost:
        if (r6.isEmpty() == false) goto L_0x0147;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x011e, code lost:
        r12 = r1.error;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0120, code lost:
        if (r12 == null) goto L_0x0134;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0122, code lost:
        r8 = (io.reactivex.processors.MulticastProcessor.MulticastSubscription[]) r2.getAndSet(r8);
        r13 = r8.length;
        r14 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x012a, code lost:
        if (r14 >= r13) goto L_0x0146;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x012c, code lost:
        r8[r14].onError(r12);
        r14 = r14 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0134, code lost:
        r8 = (io.reactivex.processors.MulticastProcessor.MulticastSubscription[]) r2.getAndSet(r8);
        r13 = r8.length;
        r14 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x013c, code lost:
        if (r14 >= r13) goto L_0x0146;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x013e, code lost:
        r8[r14].onComplete();
        r14 = r14 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0147, code lost:
        r0 = r9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void drain() {
        /*
            r23 = this;
            r1 = r23
            java.util.concurrent.atomic.AtomicInteger r0 = r1.wip
            int r0 = r0.getAndIncrement()
            if (r0 == 0) goto L_0x000b
            return
        L_0x000b:
            r0 = 1
            java.util.concurrent.atomic.AtomicReference<io.reactivex.processors.MulticastProcessor$MulticastSubscription<T>[]> r2 = r1.subscribers
            int r3 = r1.consumed
            int r4 = r1.limit
            int r5 = r1.fusionMode
            r22 = r3
            r3 = r0
            r0 = r22
        L_0x0019:
            io.reactivex.internal.fuseable.SimpleQueue<T> r6 = r1.queue
            if (r6 == 0) goto L_0x014b
            java.lang.Object r7 = r2.get()
            io.reactivex.processors.MulticastProcessor$MulticastSubscription[] r7 = (io.reactivex.processors.MulticastProcessor.MulticastSubscription[]) r7
            int r8 = r7.length
            if (r8 == 0) goto L_0x0149
            r9 = -1
            int r11 = r7.length
            r13 = 0
        L_0x002a:
            r14 = 0
            if (r13 >= r11) goto L_0x004e
            r12 = r7[r13]
            long r17 = r12.get()
            int r14 = (r17 > r14 ? 1 : (r17 == r14 ? 0 : -1))
            if (r14 < 0) goto L_0x004b
            r14 = -1
            int r14 = (r9 > r14 ? 1 : (r9 == r14 ? 0 : -1))
            if (r14 != 0) goto L_0x0043
            long r14 = r12.emitted
            long r9 = r17 - r14
            goto L_0x004b
        L_0x0043:
            long r14 = r12.emitted
            long r14 = r17 - r14
            long r9 = java.lang.Math.min(r9, r14)
        L_0x004b:
            int r13 = r13 + 1
            goto L_0x002a
        L_0x004e:
            r10 = r9
            r9 = r0
        L_0x0050:
            int r0 = (r10 > r14 ? 1 : (r10 == r14 ? 0 : -1))
            if (r0 <= 0) goto L_0x00f8
            java.lang.Object r0 = r2.get()
            r12 = r0
            io.reactivex.processors.MulticastProcessor$MulticastSubscription[] r12 = (io.reactivex.processors.MulticastProcessor.MulticastSubscription[]) r12
            io.reactivex.processors.MulticastProcessor$MulticastSubscription[] r0 = TERMINATED
            if (r12 != r0) goto L_0x0063
            r6.clear()
            return
        L_0x0063:
            if (r7 == r12) goto L_0x0067
            goto L_0x0111
        L_0x0067:
            boolean r13 = r1.done
            r14 = 1
            java.lang.Object r0 = r6.poll()     // Catch:{ all -> 0x006f }
            goto L_0x0081
        L_0x006f:
            r0 = move-exception
            r15 = r0
            r0 = r15
            io.reactivex.exceptions.Exceptions.throwIfFatal(r0)
            java.util.concurrent.atomic.AtomicReference<org.reactivestreams.Subscription> r15 = r1.upstream
            io.reactivex.internal.subscriptions.SubscriptionHelper.cancel(r15)
            r13 = 1
            r15 = 0
            r1.error = r0
            r1.done = r14
            r0 = r15
        L_0x0081:
            if (r0 != 0) goto L_0x0085
            r15 = r14
            goto L_0x0086
        L_0x0085:
            r15 = 0
        L_0x0086:
            if (r13 == 0) goto L_0x00c7
            if (r15 == 0) goto L_0x00c7
            java.lang.Throwable r14 = r1.error
            if (r14 == 0) goto L_0x00ac
            r19 = r8
            io.reactivex.processors.MulticastProcessor$MulticastSubscription[] r8 = TERMINATED
            java.lang.Object r8 = r2.getAndSet(r8)
            io.reactivex.processors.MulticastProcessor$MulticastSubscription[] r8 = (io.reactivex.processors.MulticastProcessor.MulticastSubscription[]) r8
            r20 = r12
            int r12 = r8.length
            r21 = r13
            r13 = 0
        L_0x009e:
            if (r13 >= r12) goto L_0x00c6
            r17 = r12
            r12 = r8[r13]
            r12.onError(r14)
            int r13 = r13 + 1
            r12 = r17
            goto L_0x009e
        L_0x00ac:
            r19 = r8
            r20 = r12
            r21 = r13
            io.reactivex.processors.MulticastProcessor$MulticastSubscription[] r8 = TERMINATED
            java.lang.Object r8 = r2.getAndSet(r8)
            io.reactivex.processors.MulticastProcessor$MulticastSubscription[] r8 = (io.reactivex.processors.MulticastProcessor.MulticastSubscription[]) r8
            int r12 = r8.length
            r13 = 0
        L_0x00bc:
            if (r13 >= r12) goto L_0x00c6
            r16 = r8[r13]
            r16.onComplete()
            int r13 = r13 + 1
            goto L_0x00bc
        L_0x00c6:
            return
        L_0x00c7:
            r19 = r8
            r20 = r12
            r21 = r13
            if (r15 == 0) goto L_0x00d0
            goto L_0x00fa
        L_0x00d0:
            int r8 = r7.length
            r12 = 0
        L_0x00d2:
            if (r12 >= r8) goto L_0x00dc
            r13 = r7[r12]
            r13.onNext(r0)
            int r12 = r12 + 1
            goto L_0x00d2
        L_0x00dc:
            r12 = 1
            long r10 = r10 - r12
            if (r5 == r14) goto L_0x00f2
            int r9 = r9 + 1
            if (r9 != r4) goto L_0x00f2
            r9 = 0
            java.util.concurrent.atomic.AtomicReference<org.reactivestreams.Subscription> r8 = r1.upstream
            java.lang.Object r8 = r8.get()
            org.reactivestreams.Subscription r8 = (org.reactivestreams.Subscription) r8
            long r12 = (long) r4
            r8.request(r12)
        L_0x00f2:
            r8 = r19
            r14 = 0
            goto L_0x0050
        L_0x00f8:
            r19 = r8
        L_0x00fa:
            r12 = 0
            int r0 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r0 != 0) goto L_0x0147
            java.lang.Object r0 = r2.get()
            io.reactivex.processors.MulticastProcessor$MulticastSubscription[] r0 = (io.reactivex.processors.MulticastProcessor.MulticastSubscription[]) r0
            io.reactivex.processors.MulticastProcessor$MulticastSubscription[] r8 = TERMINATED
            if (r0 != r8) goto L_0x010e
            r6.clear()
            return
        L_0x010e:
            if (r7 == r0) goto L_0x0114
        L_0x0111:
            r0 = r9
            goto L_0x0019
        L_0x0114:
            boolean r12 = r1.done
            if (r12 == 0) goto L_0x0147
            boolean r12 = r6.isEmpty()
            if (r12 == 0) goto L_0x0147
            java.lang.Throwable r12 = r1.error
            if (r12 == 0) goto L_0x0134
            java.lang.Object r8 = r2.getAndSet(r8)
            io.reactivex.processors.MulticastProcessor$MulticastSubscription[] r8 = (io.reactivex.processors.MulticastProcessor.MulticastSubscription[]) r8
            int r13 = r8.length
            r14 = 0
        L_0x012a:
            if (r14 >= r13) goto L_0x0146
            r15 = r8[r14]
            r15.onError(r12)
            int r14 = r14 + 1
            goto L_0x012a
        L_0x0134:
            java.lang.Object r8 = r2.getAndSet(r8)
            io.reactivex.processors.MulticastProcessor$MulticastSubscription[] r8 = (io.reactivex.processors.MulticastProcessor.MulticastSubscription[]) r8
            int r13 = r8.length
            r14 = 0
        L_0x013c:
            if (r14 >= r13) goto L_0x0146
            r15 = r8[r14]
            r15.onComplete()
            int r14 = r14 + 1
            goto L_0x013c
        L_0x0146:
            return
        L_0x0147:
            r0 = r9
            goto L_0x014b
        L_0x0149:
            r19 = r8
        L_0x014b:
            r1.consumed = r0
            java.util.concurrent.atomic.AtomicInteger r7 = r1.wip
            int r8 = -r3
            int r3 = r7.addAndGet(r8)
            if (r3 != 0) goto L_0x0158
            return
        L_0x0158:
            goto L_0x0019
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.processors.MulticastProcessor.drain():void");
    }

    static final class MulticastSubscription<T> extends AtomicLong implements Subscription {
        private static final long serialVersionUID = -363282618957264509L;
        final Subscriber<? super T> downstream;
        long emitted;
        final MulticastProcessor<T> parent;

        MulticastSubscription(Subscriber<? super T> actual, MulticastProcessor<T> parent2) {
            this.downstream = actual;
            this.parent = parent2;
        }

        public void request(long n) {
            long r;
            long u;
            if (SubscriptionHelper.validate(n)) {
                do {
                    r = get();
                    if (r != Long.MIN_VALUE && r != LongCompanionObject.MAX_VALUE) {
                        u = r + n;
                        if (u < 0) {
                            u = LongCompanionObject.MAX_VALUE;
                        }
                    } else {
                        return;
                    }
                } while (!compareAndSet(r, u));
                this.parent.drain();
            }
        }

        public void cancel() {
            if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.remove(this);
            }
        }

        /* access modifiers changed from: package-private */
        public void onNext(T t) {
            if (get() != Long.MIN_VALUE) {
                this.emitted++;
                this.downstream.onNext(t);
            }
        }

        /* access modifiers changed from: package-private */
        public void onError(Throwable t) {
            if (get() != Long.MIN_VALUE) {
                this.downstream.onError(t);
            }
        }

        /* access modifiers changed from: package-private */
        public void onComplete() {
            if (get() != Long.MIN_VALUE) {
                this.downstream.onComplete();
            }
        }
    }
}
