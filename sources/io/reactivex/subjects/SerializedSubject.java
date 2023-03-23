package io.reactivex.subjects;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.util.AppendOnlyLinkedArrayList;
import io.reactivex.internal.util.NotificationLite;

final class SerializedSubject<T> extends Subject<T> implements AppendOnlyLinkedArrayList.NonThrowingPredicate<Object> {
    final Subject<T> actual;
    volatile boolean done;
    boolean emitting;
    AppendOnlyLinkedArrayList<Object> queue;

    SerializedSubject(Subject<T> actual2) {
        this.actual = actual2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Observer<? super T> observer) {
        this.actual.subscribe(observer);
    }

    public void onSubscribe(Disposable d) {
        boolean cancel;
        if (!this.done) {
            synchronized (this) {
                if (this.done) {
                    cancel = true;
                } else if (this.emitting) {
                    AppendOnlyLinkedArrayList<Object> q = this.queue;
                    if (q == null) {
                        q = new AppendOnlyLinkedArrayList<>(4);
                        this.queue = q;
                    }
                    q.add(NotificationLite.disposable(d));
                    return;
                } else {
                    this.emitting = true;
                    cancel = false;
                }
            }
        } else {
            cancel = true;
        }
        if (cancel) {
            d.dispose();
            return;
        }
        this.actual.onSubscribe(d);
        emitLoop();
    }

    public void onNext(T t) {
        if (!this.done) {
            synchronized (this) {
                if (!this.done) {
                    if (this.emitting) {
                        AppendOnlyLinkedArrayList<Object> q = this.queue;
                        if (q == null) {
                            q = new AppendOnlyLinkedArrayList<>(4);
                            this.queue = q;
                        }
                        q.add(NotificationLite.next(t));
                        return;
                    }
                    this.emitting = true;
                    this.actual.onNext(t);
                    emitLoop();
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0031, code lost:
        if (r0 == false) goto L_0x0037;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0033, code lost:
        io.reactivex.plugins.RxJavaPlugins.onError(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0036, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0037, code lost:
        r3.actual.onError(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003c, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onError(java.lang.Throwable r4) {
        /*
            r3 = this;
            boolean r0 = r3.done
            if (r0 == 0) goto L_0x0008
            io.reactivex.plugins.RxJavaPlugins.onError(r4)
            return
        L_0x0008:
            monitor-enter(r3)
            boolean r0 = r3.done     // Catch:{ all -> 0x003d }
            if (r0 == 0) goto L_0x000f
            r0 = 1
            goto L_0x0030
        L_0x000f:
            r0 = 1
            r3.done = r0     // Catch:{ all -> 0x003d }
            boolean r1 = r3.emitting     // Catch:{ all -> 0x003d }
            if (r1 == 0) goto L_0x002c
            io.reactivex.internal.util.AppendOnlyLinkedArrayList<java.lang.Object> r0 = r3.queue     // Catch:{ all -> 0x003d }
            if (r0 != 0) goto L_0x0023
            io.reactivex.internal.util.AppendOnlyLinkedArrayList r1 = new io.reactivex.internal.util.AppendOnlyLinkedArrayList     // Catch:{ all -> 0x003d }
            r2 = 4
            r1.<init>(r2)     // Catch:{ all -> 0x003d }
            r0 = r1
            r3.queue = r0     // Catch:{ all -> 0x003d }
        L_0x0023:
            java.lang.Object r1 = io.reactivex.internal.util.NotificationLite.error(r4)     // Catch:{ all -> 0x003d }
            r0.setFirst(r1)     // Catch:{ all -> 0x003d }
            monitor-exit(r3)     // Catch:{ all -> 0x003d }
            return
        L_0x002c:
            r1 = 0
            r3.emitting = r0     // Catch:{ all -> 0x003d }
            r0 = r1
        L_0x0030:
            monitor-exit(r3)     // Catch:{ all -> 0x003d }
            if (r0 == 0) goto L_0x0037
            io.reactivex.plugins.RxJavaPlugins.onError(r4)
            return
        L_0x0037:
            io.reactivex.subjects.Subject<T> r1 = r3.actual
            r1.onError(r4)
            return
        L_0x003d:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x003d }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.subjects.SerializedSubject.onError(java.lang.Throwable):void");
    }

    public void onComplete() {
        if (!this.done) {
            synchronized (this) {
                if (!this.done) {
                    this.done = true;
                    if (this.emitting) {
                        AppendOnlyLinkedArrayList<Object> q = this.queue;
                        if (q == null) {
                            q = new AppendOnlyLinkedArrayList<>(4);
                            this.queue = q;
                        }
                        q.add(NotificationLite.complete());
                        return;
                    }
                    this.emitting = true;
                    this.actual.onComplete();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void emitLoop() {
        AppendOnlyLinkedArrayList<Object> q;
        while (true) {
            synchronized (this) {
                q = this.queue;
                if (q == null) {
                    this.emitting = false;
                    return;
                }
                this.queue = null;
            }
            q.forEachWhile(this);
        }
        while (true) {
        }
    }

    public boolean test(Object o) {
        return NotificationLite.acceptFull(o, this.actual);
    }

    public boolean hasObservers() {
        return this.actual.hasObservers();
    }

    public boolean hasThrowable() {
        return this.actual.hasThrowable();
    }

    public Throwable getThrowable() {
        return this.actual.getThrowable();
    }

    public boolean hasComplete() {
        return this.actual.hasComplete();
    }
}
