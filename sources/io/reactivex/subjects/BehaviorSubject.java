package io.reactivex.subjects;

import io.reactivex.Observer;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.AppendOnlyLinkedArrayList;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.plugins.RxJavaPlugins;
import java.lang.reflect.Array;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public final class BehaviorSubject<T> extends Subject<T> {
    static final BehaviorDisposable[] EMPTY = new BehaviorDisposable[0];
    private static final Object[] EMPTY_ARRAY = new Object[0];
    static final BehaviorDisposable[] TERMINATED = new BehaviorDisposable[0];
    long index;
    final ReadWriteLock lock;
    final Lock readLock;
    final AtomicReference<BehaviorDisposable<T>[]> subscribers;
    final AtomicReference<Throwable> terminalEvent;
    final AtomicReference<Object> value;
    final Lock writeLock;

    @CheckReturnValue
    public static <T> BehaviorSubject<T> create() {
        return new BehaviorSubject<>();
    }

    @CheckReturnValue
    public static <T> BehaviorSubject<T> createDefault(T defaultValue) {
        return new BehaviorSubject<>(defaultValue);
    }

    BehaviorSubject() {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.lock = reentrantReadWriteLock;
        this.readLock = reentrantReadWriteLock.readLock();
        this.writeLock = reentrantReadWriteLock.writeLock();
        this.subscribers = new AtomicReference<>(EMPTY);
        this.value = new AtomicReference<>();
        this.terminalEvent = new AtomicReference<>();
    }

    BehaviorSubject(T defaultValue) {
        this();
        this.value.lazySet(ObjectHelper.requireNonNull(defaultValue, "defaultValue is null"));
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Observer<? super T> observer) {
        BehaviorDisposable<T> bs = new BehaviorDisposable<>(observer, this);
        observer.onSubscribe(bs);
        if (!add(bs)) {
            Throwable ex = this.terminalEvent.get();
            if (ex == ExceptionHelper.TERMINATED) {
                observer.onComplete();
            } else {
                observer.onError(ex);
            }
        } else if (bs.cancelled) {
            remove(bs);
        } else {
            bs.emitFirst();
        }
    }

    public void onSubscribe(Disposable d) {
        if (this.terminalEvent.get() != null) {
            d.dispose();
        }
    }

    public void onNext(T t) {
        ObjectHelper.requireNonNull(t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.terminalEvent.get() == null) {
            Object o = NotificationLite.next(t);
            setCurrent(o);
            for (BehaviorDisposable<T> bs : (BehaviorDisposable[]) this.subscribers.get()) {
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
        for (BehaviorDisposable<T> bs : terminate(o)) {
            bs.emitNext(o, this.index);
        }
    }

    public void onComplete() {
        if (this.terminalEvent.compareAndSet((Object) null, ExceptionHelper.TERMINATED)) {
            Object o = NotificationLite.complete();
            for (BehaviorDisposable<T> bs : terminate(o)) {
                bs.emitNext(o, this.index);
            }
        }
    }

    public boolean hasObservers() {
        return ((BehaviorDisposable[]) this.subscribers.get()).length != 0;
    }

    /* access modifiers changed from: package-private */
    public int subscriberCount() {
        return ((BehaviorDisposable[]) this.subscribers.get()).length;
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
    public boolean add(BehaviorDisposable<T> rs) {
        BehaviorDisposable<T>[] a;
        BehaviorDisposable<T>[] b;
        do {
            a = (BehaviorDisposable[]) this.subscribers.get();
            if (a == TERMINATED) {
                return false;
            }
            int len = a.length;
            b = new BehaviorDisposable[(len + 1)];
            System.arraycopy(a, 0, b, 0, len);
            b[len] = rs;
        } while (!this.subscribers.compareAndSet(a, b));
        return true;
    }

    /* access modifiers changed from: package-private */
    public void remove(BehaviorDisposable<T> rs) {
        BehaviorDisposable<T>[] a;
        BehaviorDisposable<T>[] b;
        do {
            a = (BehaviorDisposable[]) this.subscribers.get();
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
                        BehaviorDisposable<T>[] b2 = new BehaviorDisposable[(len - 1)];
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
    public BehaviorDisposable<T>[] terminate(Object terminalValue) {
        AtomicReference<BehaviorDisposable<T>[]> atomicReference = this.subscribers;
        BehaviorDisposable<T>[] behaviorDisposableArr = TERMINATED;
        BehaviorDisposable<T>[] a = (BehaviorDisposable[]) atomicReference.getAndSet(behaviorDisposableArr);
        if (a != behaviorDisposableArr) {
            setCurrent(terminalValue);
        }
        return a;
    }

    /* access modifiers changed from: package-private */
    public void setCurrent(Object o) {
        this.writeLock.lock();
        this.index++;
        this.value.lazySet(o);
        this.writeLock.unlock();
    }

    static final class BehaviorDisposable<T> implements Disposable, AppendOnlyLinkedArrayList.NonThrowingPredicate<Object> {
        volatile boolean cancelled;
        final Observer<? super T> downstream;
        boolean emitting;
        boolean fastPath;
        long index;
        boolean next;
        AppendOnlyLinkedArrayList<Object> queue;
        final BehaviorSubject<T> state;

        BehaviorDisposable(Observer<? super T> actual, BehaviorSubject<T> state2) {
            this.downstream = actual;
            this.state = state2;
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.state.remove(this);
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
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
                io.reactivex.subjects.BehaviorSubject<T> r0 = r5.state     // Catch:{ all -> 0x003e }
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
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.subjects.BehaviorSubject.BehaviorDisposable.emitFirst():void");
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
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.subjects.BehaviorSubject.BehaviorDisposable.emitNext(java.lang.Object, long):void");
        }

        public boolean test(Object o) {
            return this.cancelled || NotificationLite.accept(o, this.downstream);
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
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.subjects.BehaviorSubject.BehaviorDisposable.emitLoop():void");
        }
    }
}
