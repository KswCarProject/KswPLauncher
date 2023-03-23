package io.reactivex.processors;

import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AppendOnlyLinkedArrayList;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.plugins.RxJavaPlugins;
import java.lang.reflect.Array;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.jvm.internal.LongCompanionObject;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class BehaviorProcessor<T> extends FlowableProcessor<T> {
    static final BehaviorSubscription[] EMPTY = new BehaviorSubscription[0];
    static final Object[] EMPTY_ARRAY = new Object[0];
    static final BehaviorSubscription[] TERMINATED = new BehaviorSubscription[0];
    long index;
    final ReadWriteLock lock;
    final Lock readLock;
    final AtomicReference<BehaviorSubscription<T>[]> subscribers;
    final AtomicReference<Throwable> terminalEvent;
    final AtomicReference<Object> value;
    final Lock writeLock;

    @CheckReturnValue
    public static <T> BehaviorProcessor<T> create() {
        return new BehaviorProcessor<>();
    }

    @CheckReturnValue
    public static <T> BehaviorProcessor<T> createDefault(T defaultValue) {
        ObjectHelper.requireNonNull(defaultValue, "defaultValue is null");
        return new BehaviorProcessor<>(defaultValue);
    }

    BehaviorProcessor() {
        this.value = new AtomicReference<>();
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.lock = reentrantReadWriteLock;
        this.readLock = reentrantReadWriteLock.readLock();
        this.writeLock = reentrantReadWriteLock.writeLock();
        this.subscribers = new AtomicReference<>(EMPTY);
        this.terminalEvent = new AtomicReference<>();
    }

    BehaviorProcessor(T defaultValue) {
        this();
        this.value.lazySet(ObjectHelper.requireNonNull(defaultValue, "defaultValue is null"));
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super T> s) {
        BehaviorSubscription<T> bs = new BehaviorSubscription<>(s, this);
        s.onSubscribe(bs);
        if (!add(bs)) {
            Throwable ex = this.terminalEvent.get();
            if (ex == ExceptionHelper.TERMINATED) {
                s.onComplete();
            } else {
                s.onError(ex);
            }
        } else if (bs.cancelled) {
            remove(bs);
        } else {
            bs.emitFirst();
        }
    }

    public void onSubscribe(Subscription s) {
        if (this.terminalEvent.get() != null) {
            s.cancel();
        } else {
            s.request(LongCompanionObject.MAX_VALUE);
        }
    }

    public void onNext(T t) {
        ObjectHelper.requireNonNull(t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.terminalEvent.get() == null) {
            Object o = NotificationLite.next(t);
            setCurrent(o);
            for (BehaviorSubscription<T> bs : (BehaviorSubscription[]) this.subscribers.get()) {
                bs.emitNext(o, this.index);
            }
        }
    }

    public void onError(Throwable t) {
        ObjectHelper.requireNonNull(t, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (!this.terminalEvent.compareAndSet((Object) null, t)) {
            RxJavaPlugins.onError(t);
            return;
        }
        Object o = NotificationLite.error(t);
        for (BehaviorSubscription<T> bs : terminate(o)) {
            bs.emitNext(o, this.index);
        }
    }

    public void onComplete() {
        if (this.terminalEvent.compareAndSet((Object) null, ExceptionHelper.TERMINATED)) {
            Object o = NotificationLite.complete();
            for (BehaviorSubscription<T> bs : terminate(o)) {
                bs.emitNext(o, this.index);
            }
        }
    }

    public boolean offer(T t) {
        if (t == null) {
            onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
            return true;
        }
        BehaviorSubscription<T>[] array = (BehaviorSubscription[]) this.subscribers.get();
        for (BehaviorSubscription<T> s : array) {
            if (s.isFull()) {
                return false;
            }
        }
        Object o = NotificationLite.next(t);
        setCurrent(o);
        for (BehaviorSubscription<T> bs : array) {
            bs.emitNext(o, this.index);
        }
        return true;
    }

    public boolean hasSubscribers() {
        return ((BehaviorSubscription[]) this.subscribers.get()).length != 0;
    }

    /* access modifiers changed from: package-private */
    public int subscriberCount() {
        return ((BehaviorSubscription[]) this.subscribers.get()).length;
    }

    public Throwable getThrowable() {
        Object o = this.value.get();
        if (NotificationLite.isError(o)) {
            return NotificationLite.getError(o);
        }
        return null;
    }

    public T getValue() {
        Object o = this.value.get();
        if (NotificationLite.isComplete(o) || NotificationLite.isError(o)) {
            return null;
        }
        return NotificationLite.getValue(o);
    }

    @Deprecated
    public Object[] getValues() {
        T[] tArr = EMPTY_ARRAY;
        T[] b = getValues((Object[]) tArr);
        if (b == tArr) {
            return new Object[0];
        }
        return b;
    }

    @Deprecated
    public T[] getValues(T[] array) {
        Object o = this.value.get();
        if (o == null || NotificationLite.isComplete(o) || NotificationLite.isError(o)) {
            if (array.length != 0) {
                array[0] = null;
            }
            return array;
        }
        T v = NotificationLite.getValue(o);
        if (array.length != 0) {
            array[0] = v;
            if (array.length == 1) {
                return array;
            }
            array[1] = null;
            return array;
        }
        T[] array2 = (Object[]) ((Object[]) Array.newInstance(array.getClass().getComponentType(), 1));
        array2[0] = v;
        return array2;
    }

    public boolean hasComplete() {
        return NotificationLite.isComplete(this.value.get());
    }

    public boolean hasThrowable() {
        return NotificationLite.isError(this.value.get());
    }

    public boolean hasValue() {
        Object o = this.value.get();
        return o != null && !NotificationLite.isComplete(o) && !NotificationLite.isError(o);
    }

    /* access modifiers changed from: package-private */
    public boolean add(BehaviorSubscription<T> rs) {
        BehaviorSubscription<T>[] a;
        BehaviorSubscription<T>[] b;
        do {
            a = (BehaviorSubscription[]) this.subscribers.get();
            if (a == TERMINATED) {
                return false;
            }
            int len = a.length;
            b = new BehaviorSubscription[(len + 1)];
            System.arraycopy(a, 0, b, 0, len);
            b[len] = rs;
        } while (!this.subscribers.compareAndSet(a, b));
        return true;
    }

    /* access modifiers changed from: package-private */
    public void remove(BehaviorSubscription<T> rs) {
        BehaviorSubscription<T>[] a;
        BehaviorSubscription<T>[] b;
        do {
            a = (BehaviorSubscription[]) this.subscribers.get();
            int len = a.length;
            if (len != 0) {
                int j = -1;
                int i = 0;
                while (true) {
                    if (i >= len) {
                        break;
                    } else if (a[i] == rs) {
                        j = i;
                        break;
                    } else {
                        i++;
                    }
                }
                if (j >= 0) {
                    if (len == 1) {
                        b = EMPTY;
                    } else {
                        BehaviorSubscription<T>[] b2 = new BehaviorSubscription[(len - 1)];
                        System.arraycopy(a, 0, b2, 0, j);
                        System.arraycopy(a, j + 1, b2, j, (len - j) - 1);
                        b = b2;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (!this.subscribers.compareAndSet(a, b));
    }

    /* access modifiers changed from: package-private */
    public BehaviorSubscription<T>[] terminate(Object terminalValue) {
        BehaviorSubscription<T>[] a = (BehaviorSubscription[]) this.subscribers.get();
        BehaviorSubscription<T>[] behaviorSubscriptionArr = TERMINATED;
        if (!(a == behaviorSubscriptionArr || (a = (BehaviorSubscription[]) this.subscribers.getAndSet(behaviorSubscriptionArr)) == behaviorSubscriptionArr)) {
            setCurrent(terminalValue);
        }
        return a;
    }

    /* access modifiers changed from: package-private */
    public void setCurrent(Object o) {
        Lock wl = this.writeLock;
        wl.lock();
        this.index++;
        this.value.lazySet(o);
        wl.unlock();
    }

    static final class BehaviorSubscription<T> extends AtomicLong implements Subscription, AppendOnlyLinkedArrayList.NonThrowingPredicate<Object> {
        private static final long serialVersionUID = 3293175281126227086L;
        volatile boolean cancelled;
        final Subscriber<? super T> downstream;
        boolean emitting;
        boolean fastPath;
        long index;
        boolean next;
        AppendOnlyLinkedArrayList<Object> queue;
        final BehaviorProcessor<T> state;

        BehaviorSubscription(Subscriber<? super T> actual, BehaviorProcessor<T> state2) {
            this.downstream = actual;
            this.state = state2;
        }

        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this, n);
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.state.remove(this);
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0031, code lost:
            if (r2 == null) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0037, code lost:
            if (test(r2) == false) goto L_0x003a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0039, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x003a, code lost:
            emitLoop();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void emitFirst() {
            /*
                r5 = this;
                boolean r0 = r5.cancelled
                if (r0 == 0) goto L_0x0005
                return
            L_0x0005:
                monitor-enter(r5)
                boolean r0 = r5.cancelled     // Catch:{ all -> 0x003e }
                if (r0 == 0) goto L_0x000c
                monitor-exit(r5)     // Catch:{ all -> 0x003e }
                return
            L_0x000c:
                boolean r0 = r5.next     // Catch:{ all -> 0x003e }
                if (r0 == 0) goto L_0x0012
                monitor-exit(r5)     // Catch:{ all -> 0x003e }
                return
            L_0x0012:
                io.reactivex.processors.BehaviorProcessor<T> r0 = r5.state     // Catch:{ all -> 0x003e }
                java.util.concurrent.locks.Lock r1 = r0.readLock     // Catch:{ all -> 0x003e }
                r1.lock()     // Catch:{ all -> 0x003e }
                long r2 = r0.index     // Catch:{ all -> 0x003e }
                r5.index = r2     // Catch:{ all -> 0x003e }
                java.util.concurrent.atomic.AtomicReference<java.lang.Object> r2 = r0.value     // Catch:{ all -> 0x003e }
                java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x003e }
                r1.unlock()     // Catch:{ all -> 0x003e }
                r3 = 1
                if (r2 == 0) goto L_0x002b
                r4 = r3
                goto L_0x002c
            L_0x002b:
                r4 = 0
            L_0x002c:
                r5.emitting = r4     // Catch:{ all -> 0x003e }
                r5.next = r3     // Catch:{ all -> 0x003e }
                monitor-exit(r5)     // Catch:{ all -> 0x003e }
                if (r2 == 0) goto L_0x003d
                boolean r0 = r5.test(r2)
                if (r0 == 0) goto L_0x003a
                return
            L_0x003a:
                r5.emitLoop()
            L_0x003d:
                return
            L_0x003e:
                r0 = move-exception
                monitor-exit(r5)     // Catch:{ all -> 0x003e }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.processors.BehaviorProcessor.BehaviorSubscription.emitFirst():void");
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x0032, code lost:
            r3.fastPath = true;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void emitNext(java.lang.Object r4, long r5) {
            /*
                r3 = this;
                boolean r0 = r3.cancelled
                if (r0 == 0) goto L_0x0005
                return
            L_0x0005:
                boolean r0 = r3.fastPath
                if (r0 != 0) goto L_0x0038
                monitor-enter(r3)
                boolean r0 = r3.cancelled     // Catch:{ all -> 0x0035 }
                if (r0 == 0) goto L_0x0010
                monitor-exit(r3)     // Catch:{ all -> 0x0035 }
                return
            L_0x0010:
                long r0 = r3.index     // Catch:{ all -> 0x0035 }
                int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
                if (r0 != 0) goto L_0x0018
                monitor-exit(r3)     // Catch:{ all -> 0x0035 }
                return
            L_0x0018:
                boolean r0 = r3.emitting     // Catch:{ all -> 0x0035 }
                if (r0 == 0) goto L_0x002e
                io.reactivex.internal.util.AppendOnlyLinkedArrayList<java.lang.Object> r0 = r3.queue     // Catch:{ all -> 0x0035 }
                if (r0 != 0) goto L_0x0029
                io.reactivex.internal.util.AppendOnlyLinkedArrayList r1 = new io.reactivex.internal.util.AppendOnlyLinkedArrayList     // Catch:{ all -> 0x0035 }
                r2 = 4
                r1.<init>(r2)     // Catch:{ all -> 0x0035 }
                r0 = r1
                r3.queue = r0     // Catch:{ all -> 0x0035 }
            L_0x0029:
                r0.add(r4)     // Catch:{ all -> 0x0035 }
                monitor-exit(r3)     // Catch:{ all -> 0x0035 }
                return
            L_0x002e:
                r0 = 1
                r3.next = r0     // Catch:{ all -> 0x0035 }
                monitor-exit(r3)     // Catch:{ all -> 0x0035 }
                r3.fastPath = r0
                goto L_0x0038
            L_0x0035:
                r0 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x0035 }
                throw r0
            L_0x0038:
                r3.test(r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.processors.BehaviorProcessor.BehaviorSubscription.emitNext(java.lang.Object, long):void");
        }

        public boolean test(Object o) {
            if (this.cancelled) {
                return true;
            }
            if (NotificationLite.isComplete(o)) {
                this.downstream.onComplete();
                return true;
            } else if (NotificationLite.isError(o)) {
                this.downstream.onError(NotificationLite.getError(o));
                return true;
            } else {
                long r = get();
                if (r != 0) {
                    this.downstream.onNext(NotificationLite.getValue(o));
                    if (r == LongCompanionObject.MAX_VALUE) {
                        return false;
                    }
                    decrementAndGet();
                    return false;
                }
                cancel();
                this.downstream.onError(new MissingBackpressureException("Could not deliver value due to lack of requests"));
                return true;
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0013, code lost:
            r0.forEachWhile(r2);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void emitLoop() {
            /*
                r2 = this;
            L_0x0000:
                boolean r0 = r2.cancelled
                if (r0 == 0) goto L_0x0005
                return
            L_0x0005:
                monitor-enter(r2)
                io.reactivex.internal.util.AppendOnlyLinkedArrayList<java.lang.Object> r0 = r2.queue     // Catch:{ all -> 0x0017 }
                if (r0 != 0) goto L_0x000f
                r1 = 0
                r2.emitting = r1     // Catch:{ all -> 0x0017 }
                monitor-exit(r2)     // Catch:{ all -> 0x0017 }
                return
            L_0x000f:
                r1 = 0
                r2.queue = r1     // Catch:{ all -> 0x0017 }
                monitor-exit(r2)     // Catch:{ all -> 0x0017 }
                r0.forEachWhile(r2)
                goto L_0x0000
            L_0x0017:
                r0 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x0017 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.processors.BehaviorProcessor.BehaviorSubscription.emitLoop():void");
        }

        public boolean isFull() {
            return get() == 0;
        }
    }
}
