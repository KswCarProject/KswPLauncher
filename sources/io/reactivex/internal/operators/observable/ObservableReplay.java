package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.disposables.ResettableConnectable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.HasUpstreamObservableSource;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Timed;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableReplay<T> extends ConnectableObservable<T> implements HasUpstreamObservableSource<T>, ResettableConnectable {
    static final BufferSupplier DEFAULT_UNBOUNDED_FACTORY = new UnBoundedFactory();
    final BufferSupplier<T> bufferFactory;
    final AtomicReference<ReplayObserver<T>> current;
    final ObservableSource<T> onSubscribe;
    final ObservableSource<T> source;

    interface BufferSupplier<T> {
        ReplayBuffer<T> call();
    }

    interface ReplayBuffer<T> {
        void complete();

        void error(Throwable th);

        void next(T t);

        void replay(InnerDisposable<T> innerDisposable);
    }

    public static <U, R> Observable<R> multicastSelector(Callable<? extends ConnectableObservable<U>> connectableFactory, Function<? super Observable<U>, ? extends ObservableSource<R>> selector) {
        return RxJavaPlugins.onAssembly(new MulticastReplay(connectableFactory, selector));
    }

    public static <T> ConnectableObservable<T> observeOn(ConnectableObservable<T> co, Scheduler scheduler) {
        return RxJavaPlugins.onAssembly(new Replay(co, co.observeOn(scheduler)));
    }

    public static <T> ConnectableObservable<T> createFrom(ObservableSource<? extends T> source2) {
        return create(source2, DEFAULT_UNBOUNDED_FACTORY);
    }

    public static <T> ConnectableObservable<T> create(ObservableSource<T> source2, int bufferSize) {
        if (bufferSize == Integer.MAX_VALUE) {
            return createFrom(source2);
        }
        return create(source2, new ReplayBufferSupplier(bufferSize));
    }

    public static <T> ConnectableObservable<T> create(ObservableSource<T> source2, long maxAge, TimeUnit unit, Scheduler scheduler) {
        return create(source2, maxAge, unit, scheduler, Integer.MAX_VALUE);
    }

    public static <T> ConnectableObservable<T> create(ObservableSource<T> source2, long maxAge, TimeUnit unit, Scheduler scheduler, int bufferSize) {
        return create(source2, new ScheduledReplaySupplier(bufferSize, maxAge, unit, scheduler));
    }

    static <T> ConnectableObservable<T> create(ObservableSource<T> source2, BufferSupplier<T> bufferFactory2) {
        AtomicReference<ReplayObserver<T>> curr = new AtomicReference<>();
        return RxJavaPlugins.onAssembly(new ObservableReplay(new ReplaySource<>(curr, bufferFactory2), source2, curr, bufferFactory2));
    }

    private ObservableReplay(ObservableSource<T> onSubscribe2, ObservableSource<T> source2, AtomicReference<ReplayObserver<T>> current2, BufferSupplier<T> bufferFactory2) {
        this.onSubscribe = onSubscribe2;
        this.source = source2;
        this.current = current2;
        this.bufferFactory = bufferFactory2;
    }

    public ObservableSource<T> source() {
        return this.source;
    }

    public void resetIf(Disposable connectionObject) {
        this.current.compareAndSet((ReplayObserver) connectionObject, (Object) null);
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Observer<? super T> observer) {
        this.onSubscribe.subscribe(observer);
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void connect(io.reactivex.functions.Consumer<? super io.reactivex.disposables.Disposable> r7) {
        /*
            r6 = this;
        L_0x0000:
            java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.observable.ObservableReplay$ReplayObserver<T>> r0 = r6.current
            java.lang.Object r0 = r0.get()
            io.reactivex.internal.operators.observable.ObservableReplay$ReplayObserver r0 = (io.reactivex.internal.operators.observable.ObservableReplay.ReplayObserver) r0
            if (r0 == 0) goto L_0x0010
            boolean r1 = r0.isDisposed()
            if (r1 == 0) goto L_0x0025
        L_0x0010:
            io.reactivex.internal.operators.observable.ObservableReplay$BufferSupplier<T> r1 = r6.bufferFactory
            io.reactivex.internal.operators.observable.ObservableReplay$ReplayBuffer r1 = r1.call()
            io.reactivex.internal.operators.observable.ObservableReplay$ReplayObserver r2 = new io.reactivex.internal.operators.observable.ObservableReplay$ReplayObserver
            r2.<init>(r1)
            java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.observable.ObservableReplay$ReplayObserver<T>> r3 = r6.current
            boolean r3 = r3.compareAndSet(r0, r2)
            if (r3 != 0) goto L_0x0024
            goto L_0x0000
        L_0x0024:
            r0 = r2
        L_0x0025:
            java.util.concurrent.atomic.AtomicBoolean r1 = r0.shouldConnect
            boolean r1 = r1.get()
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L_0x0039
            java.util.concurrent.atomic.AtomicBoolean r1 = r0.shouldConnect
            boolean r1 = r1.compareAndSet(r3, r2)
            if (r1 == 0) goto L_0x0039
            r1 = r2
            goto L_0x003a
        L_0x0039:
            r1 = r3
        L_0x003a:
            r7.accept(r0)     // Catch:{ all -> 0x0047 }
            if (r1 == 0) goto L_0x0046
            io.reactivex.ObservableSource<T> r2 = r6.source
            r2.subscribe(r0)
        L_0x0046:
            return
        L_0x0047:
            r4 = move-exception
            if (r1 == 0) goto L_0x004f
            java.util.concurrent.atomic.AtomicBoolean r5 = r0.shouldConnect
            r5.compareAndSet(r2, r3)
        L_0x004f:
            io.reactivex.exceptions.Exceptions.throwIfFatal(r4)
            java.lang.RuntimeException r2 = io.reactivex.internal.util.ExceptionHelper.wrapOrThrow(r4)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservableReplay.connect(io.reactivex.functions.Consumer):void");
    }

    static final class ReplayObserver<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable {
        static final InnerDisposable[] EMPTY = new InnerDisposable[0];
        static final InnerDisposable[] TERMINATED = new InnerDisposable[0];
        private static final long serialVersionUID = -533785617179540163L;
        final ReplayBuffer<T> buffer;
        boolean done;
        final AtomicReference<InnerDisposable[]> observers = new AtomicReference<>(EMPTY);
        final AtomicBoolean shouldConnect = new AtomicBoolean();

        ReplayObserver(ReplayBuffer<T> buffer2) {
            this.buffer = buffer2;
        }

        public boolean isDisposed() {
            return this.observers.get() == TERMINATED;
        }

        public void dispose() {
            this.observers.set(TERMINATED);
            DisposableHelper.dispose(this);
        }

        /* access modifiers changed from: package-private */
        public boolean add(InnerDisposable<T> producer) {
            InnerDisposable[] c;
            InnerDisposable[] u;
            do {
                c = this.observers.get();
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
            InnerDisposable[] c;
            InnerDisposable[] u;
            do {
                c = this.observers.get();
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
                            InnerDisposable[] u2 = new InnerDisposable[(len - 1)];
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

        public void onSubscribe(Disposable p) {
            if (DisposableHelper.setOnce(this, p)) {
                replay();
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                this.buffer.next(t);
                replay();
            }
        }

        public void onError(Throwable e) {
            if (!this.done) {
                this.done = true;
                this.buffer.error(e);
                replayFinal();
                return;
            }
            RxJavaPlugins.onError(e);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.buffer.complete();
                replayFinal();
            }
        }

        /* access modifiers changed from: package-private */
        public void replay() {
            for (InnerDisposable<T> rp : (InnerDisposable[]) this.observers.get()) {
                this.buffer.replay(rp);
            }
        }

        /* access modifiers changed from: package-private */
        public void replayFinal() {
            for (InnerDisposable<T> rp : (InnerDisposable[]) this.observers.getAndSet(TERMINATED)) {
                this.buffer.replay(rp);
            }
        }
    }

    static final class InnerDisposable<T> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = 2728361546769921047L;
        volatile boolean cancelled;
        final Observer<? super T> child;
        Object index;
        final ReplayObserver<T> parent;

        InnerDisposable(ReplayObserver<T> parent2, Observer<? super T> child2) {
            this.parent = parent2;
            this.child = child2;
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.parent.remove(this);
                this.index = null;
            }
        }

        /* access modifiers changed from: package-private */
        public <U> U index() {
            return this.index;
        }
    }

    static final class UnboundedReplayBuffer<T> extends ArrayList<Object> implements ReplayBuffer<T> {
        private static final long serialVersionUID = 7063189396499112664L;
        volatile int size;

        UnboundedReplayBuffer(int capacityHint) {
            super(capacityHint);
        }

        public void next(T value) {
            add(NotificationLite.next(value));
            this.size++;
        }

        public void error(Throwable e) {
            add(NotificationLite.error(e));
            this.size++;
        }

        public void complete() {
            add(NotificationLite.complete());
            this.size++;
        }

        public void replay(InnerDisposable<T> output) {
            if (output.getAndIncrement() == 0) {
                Observer<? super T> child = output.child;
                int missed = 1;
                while (!output.isDisposed()) {
                    int sourceIndex = this.size;
                    Integer destinationIndexObject = (Integer) output.index();
                    int destinationIndex = destinationIndexObject != null ? destinationIndexObject.intValue() : 0;
                    while (destinationIndex < sourceIndex) {
                        if (!NotificationLite.accept(get(destinationIndex), child) && !output.isDisposed()) {
                            destinationIndex++;
                        } else {
                            return;
                        }
                    }
                    output.index = Integer.valueOf(destinationIndex);
                    missed = output.addAndGet(-missed);
                    if (missed == 0) {
                        return;
                    }
                }
            }
        }
    }

    static final class Node extends AtomicReference<Node> {
        private static final long serialVersionUID = 245354315435971818L;
        final Object value;

        Node(Object value2) {
            this.value = value2;
        }
    }

    static abstract class BoundedReplayBuffer<T> extends AtomicReference<Node> implements ReplayBuffer<T> {
        private static final long serialVersionUID = 2346567790059478686L;
        int size;
        Node tail;

        /* access modifiers changed from: package-private */
        public abstract void truncate();

        BoundedReplayBuffer() {
            Node n = new Node((Object) null);
            this.tail = n;
            set(n);
        }

        /* access modifiers changed from: package-private */
        public final void addLast(Node n) {
            this.tail.set(n);
            this.tail = n;
            this.size++;
        }

        /* access modifiers changed from: package-private */
        public final void removeFirst() {
            this.size--;
            setFirst((Node) ((Node) get()).get());
        }

        /* access modifiers changed from: package-private */
        public final void trimHead() {
            Node head = (Node) get();
            if (head.value != null) {
                Node n = new Node((Object) null);
                n.lazySet(head.get());
                set(n);
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: io.reactivex.internal.operators.observable.ObservableReplay$Node} */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void removeSome(int r3) {
            /*
                r2 = this;
                java.lang.Object r0 = r2.get()
                io.reactivex.internal.operators.observable.ObservableReplay$Node r0 = (io.reactivex.internal.operators.observable.ObservableReplay.Node) r0
            L_0x0006:
                if (r3 <= 0) goto L_0x0018
                java.lang.Object r1 = r0.get()
                r0 = r1
                io.reactivex.internal.operators.observable.ObservableReplay$Node r0 = (io.reactivex.internal.operators.observable.ObservableReplay.Node) r0
                int r3 = r3 + -1
                int r1 = r2.size
                int r1 = r1 + -1
                r2.size = r1
                goto L_0x0006
            L_0x0018:
                r2.setFirst(r0)
                java.lang.Object r1 = r2.get()
                r0 = r1
                io.reactivex.internal.operators.observable.ObservableReplay$Node r0 = (io.reactivex.internal.operators.observable.ObservableReplay.Node) r0
                java.lang.Object r1 = r0.get()
                if (r1 != 0) goto L_0x002a
                r2.tail = r0
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservableReplay.BoundedReplayBuffer.removeSome(int):void");
        }

        /* access modifiers changed from: package-private */
        public final void setFirst(Node n) {
            set(n);
        }

        public final void next(T value) {
            addLast(new Node(enterTransform(NotificationLite.next(value))));
            truncate();
        }

        public final void error(Throwable e) {
            addLast(new Node(enterTransform(NotificationLite.error(e))));
            truncateFinal();
        }

        public final void complete() {
            addLast(new Node(enterTransform(NotificationLite.complete())));
            truncateFinal();
        }

        public final void replay(InnerDisposable<T> output) {
            if (output.getAndIncrement() == 0) {
                int missed = 1;
                do {
                    Node node = (Node) output.index();
                    if (node == null) {
                        node = getHead();
                        output.index = node;
                    }
                    while (!output.isDisposed()) {
                        Node v = (Node) node.get();
                        if (v == null) {
                            output.index = node;
                            missed = output.addAndGet(-missed);
                        } else if (NotificationLite.accept(leaveTransform(v.value), output.child)) {
                            output.index = null;
                            return;
                        } else {
                            node = v;
                        }
                    }
                    output.index = null;
                    return;
                } while (missed != 0);
            }
        }

        /* access modifiers changed from: package-private */
        public Object enterTransform(Object value) {
            return value;
        }

        /* access modifiers changed from: package-private */
        public Object leaveTransform(Object value) {
            return value;
        }

        /* access modifiers changed from: package-private */
        public void truncateFinal() {
            trimHead();
        }

        /* access modifiers changed from: package-private */
        public final void collect(Collection<? super T> output) {
            Node n = getHead();
            while (true) {
                Node next = (Node) n.get();
                if (next != null) {
                    Object v = leaveTransform(next.value);
                    if (!NotificationLite.isComplete(v) && !NotificationLite.isError(v)) {
                        output.add(NotificationLite.getValue(v));
                        n = next;
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean hasError() {
            return this.tail.value != null && NotificationLite.isError(leaveTransform(this.tail.value));
        }

        /* access modifiers changed from: package-private */
        public boolean hasCompleted() {
            return this.tail.value != null && NotificationLite.isComplete(leaveTransform(this.tail.value));
        }

        /* access modifiers changed from: package-private */
        public Node getHead() {
            return (Node) get();
        }
    }

    static final class SizeBoundReplayBuffer<T> extends BoundedReplayBuffer<T> {
        private static final long serialVersionUID = -5898283885385201806L;
        final int limit;

        SizeBoundReplayBuffer(int limit2) {
            this.limit = limit2;
        }

        /* access modifiers changed from: package-private */
        public void truncate() {
            if (this.size > this.limit) {
                removeFirst();
            }
        }
    }

    static final class SizeAndTimeBoundReplayBuffer<T> extends BoundedReplayBuffer<T> {
        private static final long serialVersionUID = 3457957419649567404L;
        final int limit;
        final long maxAge;
        final Scheduler scheduler;
        final TimeUnit unit;

        SizeAndTimeBoundReplayBuffer(int limit2, long maxAge2, TimeUnit unit2, Scheduler scheduler2) {
            this.scheduler = scheduler2;
            this.limit = limit2;
            this.maxAge = maxAge2;
            this.unit = unit2;
        }

        /* access modifiers changed from: package-private */
        public Object enterTransform(Object value) {
            return new Timed(value, this.scheduler.now(this.unit), this.unit);
        }

        /* access modifiers changed from: package-private */
        public Object leaveTransform(Object value) {
            return ((Timed) value).value();
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: io.reactivex.internal.operators.observable.ObservableReplay$Node} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: io.reactivex.internal.operators.observable.ObservableReplay$Node} */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void truncate() {
            /*
                r10 = this;
                io.reactivex.Scheduler r0 = r10.scheduler
                java.util.concurrent.TimeUnit r1 = r10.unit
                long r0 = r0.now(r1)
                long r2 = r10.maxAge
                long r0 = r0 - r2
                java.lang.Object r2 = r10.get()
                io.reactivex.internal.operators.observable.ObservableReplay$Node r2 = (io.reactivex.internal.operators.observable.ObservableReplay.Node) r2
                java.lang.Object r3 = r2.get()
                io.reactivex.internal.operators.observable.ObservableReplay$Node r3 = (io.reactivex.internal.operators.observable.ObservableReplay.Node) r3
                r4 = 0
            L_0x0018:
                if (r3 == 0) goto L_0x0051
                int r5 = r10.size
                int r6 = r10.limit
                r7 = 1
                if (r5 <= r6) goto L_0x0035
                int r5 = r10.size
                if (r5 <= r7) goto L_0x0035
                int r4 = r4 + 1
                int r5 = r10.size
                int r5 = r5 - r7
                r10.size = r5
                r2 = r3
                java.lang.Object r5 = r3.get()
                r3 = r5
                io.reactivex.internal.operators.observable.ObservableReplay$Node r3 = (io.reactivex.internal.operators.observable.ObservableReplay.Node) r3
                goto L_0x0018
            L_0x0035:
                java.lang.Object r5 = r3.value
                io.reactivex.schedulers.Timed r5 = (io.reactivex.schedulers.Timed) r5
                long r8 = r5.time()
                int r6 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
                if (r6 > 0) goto L_0x0051
                int r4 = r4 + 1
                int r6 = r10.size
                int r6 = r6 - r7
                r10.size = r6
                r2 = r3
                java.lang.Object r6 = r3.get()
                r3 = r6
                io.reactivex.internal.operators.observable.ObservableReplay$Node r3 = (io.reactivex.internal.operators.observable.ObservableReplay.Node) r3
                goto L_0x0018
            L_0x0051:
                if (r4 == 0) goto L_0x0056
                r10.setFirst(r2)
            L_0x0056:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservableReplay.SizeAndTimeBoundReplayBuffer.truncate():void");
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: io.reactivex.internal.operators.observable.ObservableReplay$Node} */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void truncateFinal() {
            /*
                r9 = this;
                io.reactivex.Scheduler r0 = r9.scheduler
                java.util.concurrent.TimeUnit r1 = r9.unit
                long r0 = r0.now(r1)
                long r2 = r9.maxAge
                long r0 = r0 - r2
                java.lang.Object r2 = r9.get()
                io.reactivex.internal.operators.observable.ObservableReplay$Node r2 = (io.reactivex.internal.operators.observable.ObservableReplay.Node) r2
                java.lang.Object r3 = r2.get()
                io.reactivex.internal.operators.observable.ObservableReplay$Node r3 = (io.reactivex.internal.operators.observable.ObservableReplay.Node) r3
                r4 = 0
            L_0x0018:
                if (r3 == 0) goto L_0x003b
                int r5 = r9.size
                r6 = 1
                if (r5 <= r6) goto L_0x003b
                java.lang.Object r5 = r3.value
                io.reactivex.schedulers.Timed r5 = (io.reactivex.schedulers.Timed) r5
                long r7 = r5.time()
                int r7 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
                if (r7 > 0) goto L_0x003b
                int r4 = r4 + 1
                int r7 = r9.size
                int r7 = r7 - r6
                r9.size = r7
                r2 = r3
                java.lang.Object r6 = r3.get()
                r3 = r6
                io.reactivex.internal.operators.observable.ObservableReplay$Node r3 = (io.reactivex.internal.operators.observable.ObservableReplay.Node) r3
                goto L_0x0018
            L_0x003b:
                if (r4 == 0) goto L_0x0040
                r9.setFirst(r2)
            L_0x0040:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservableReplay.SizeAndTimeBoundReplayBuffer.truncateFinal():void");
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: io.reactivex.internal.operators.observable.ObservableReplay$Node} */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public io.reactivex.internal.operators.observable.ObservableReplay.Node getHead() {
            /*
                r7 = this;
                io.reactivex.Scheduler r0 = r7.scheduler
                java.util.concurrent.TimeUnit r1 = r7.unit
                long r0 = r0.now(r1)
                long r2 = r7.maxAge
                long r0 = r0 - r2
                java.lang.Object r2 = r7.get()
                io.reactivex.internal.operators.observable.ObservableReplay$Node r2 = (io.reactivex.internal.operators.observable.ObservableReplay.Node) r2
                java.lang.Object r3 = r2.get()
                io.reactivex.internal.operators.observable.ObservableReplay$Node r3 = (io.reactivex.internal.operators.observable.ObservableReplay.Node) r3
            L_0x0017:
                if (r3 != 0) goto L_0x001a
                goto L_0x0044
            L_0x001a:
                java.lang.Object r4 = r3.value
                io.reactivex.schedulers.Timed r4 = (io.reactivex.schedulers.Timed) r4
                java.lang.Object r5 = r4.value()
                boolean r5 = io.reactivex.internal.util.NotificationLite.isComplete(r5)
                if (r5 != 0) goto L_0x0044
                java.lang.Object r5 = r4.value()
                boolean r5 = io.reactivex.internal.util.NotificationLite.isError(r5)
                if (r5 == 0) goto L_0x0033
                goto L_0x0044
            L_0x0033:
                long r5 = r4.time()
                int r5 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
                if (r5 > 0) goto L_0x0044
                r2 = r3
                java.lang.Object r5 = r3.get()
                r3 = r5
                io.reactivex.internal.operators.observable.ObservableReplay$Node r3 = (io.reactivex.internal.operators.observable.ObservableReplay.Node) r3
                goto L_0x0017
            L_0x0044:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservableReplay.SizeAndTimeBoundReplayBuffer.getHead():io.reactivex.internal.operators.observable.ObservableReplay$Node");
        }
    }

    static final class UnBoundedFactory implements BufferSupplier<Object> {
        UnBoundedFactory() {
        }

        public ReplayBuffer<Object> call() {
            return new UnboundedReplayBuffer(16);
        }
    }

    static final class DisposeConsumer<R> implements Consumer<Disposable> {
        private final ObserverResourceWrapper<R> srw;

        DisposeConsumer(ObserverResourceWrapper<R> srw2) {
            this.srw = srw2;
        }

        public void accept(Disposable r) {
            this.srw.setResource(r);
        }
    }

    static final class ReplayBufferSupplier<T> implements BufferSupplier<T> {
        private final int bufferSize;

        ReplayBufferSupplier(int bufferSize2) {
            this.bufferSize = bufferSize2;
        }

        public ReplayBuffer<T> call() {
            return new SizeBoundReplayBuffer(this.bufferSize);
        }
    }

    static final class ScheduledReplaySupplier<T> implements BufferSupplier<T> {
        private final int bufferSize;
        private final long maxAge;
        private final Scheduler scheduler;
        private final TimeUnit unit;

        ScheduledReplaySupplier(int bufferSize2, long maxAge2, TimeUnit unit2, Scheduler scheduler2) {
            this.bufferSize = bufferSize2;
            this.maxAge = maxAge2;
            this.unit = unit2;
            this.scheduler = scheduler2;
        }

        public ReplayBuffer<T> call() {
            return new SizeAndTimeBoundReplayBuffer(this.bufferSize, this.maxAge, this.unit, this.scheduler);
        }
    }

    static final class ReplaySource<T> implements ObservableSource<T> {
        private final BufferSupplier<T> bufferFactory;
        private final AtomicReference<ReplayObserver<T>> curr;

        ReplaySource(AtomicReference<ReplayObserver<T>> curr2, BufferSupplier<T> bufferFactory2) {
            this.curr = curr2;
            this.bufferFactory = bufferFactory2;
        }

        /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void subscribe(io.reactivex.Observer<? super T> r6) {
            /*
                r5 = this;
            L_0x0000:
                java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.observable.ObservableReplay$ReplayObserver<T>> r0 = r5.curr
                java.lang.Object r0 = r0.get()
                io.reactivex.internal.operators.observable.ObservableReplay$ReplayObserver r0 = (io.reactivex.internal.operators.observable.ObservableReplay.ReplayObserver) r0
                if (r0 != 0) goto L_0x0020
                io.reactivex.internal.operators.observable.ObservableReplay$BufferSupplier<T> r1 = r5.bufferFactory
                io.reactivex.internal.operators.observable.ObservableReplay$ReplayBuffer r1 = r1.call()
                io.reactivex.internal.operators.observable.ObservableReplay$ReplayObserver r2 = new io.reactivex.internal.operators.observable.ObservableReplay$ReplayObserver
                r2.<init>(r1)
                java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.observable.ObservableReplay$ReplayObserver<T>> r3 = r5.curr
                r4 = 0
                boolean r3 = r3.compareAndSet(r4, r2)
                if (r3 != 0) goto L_0x001f
                goto L_0x0000
            L_0x001f:
                r0 = r2
            L_0x0020:
                io.reactivex.internal.operators.observable.ObservableReplay$InnerDisposable r1 = new io.reactivex.internal.operators.observable.ObservableReplay$InnerDisposable
                r1.<init>(r0, r6)
                r6.onSubscribe(r1)
                r0.add(r1)
                boolean r2 = r1.isDisposed()
                if (r2 == 0) goto L_0x0035
                r0.remove(r1)
                return
            L_0x0035:
                io.reactivex.internal.operators.observable.ObservableReplay$ReplayBuffer<T> r2 = r0.buffer
                r2.replay(r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservableReplay.ReplaySource.subscribe(io.reactivex.Observer):void");
        }
    }

    static final class MulticastReplay<R, U> extends Observable<R> {
        private final Callable<? extends ConnectableObservable<U>> connectableFactory;
        private final Function<? super Observable<U>, ? extends ObservableSource<R>> selector;

        MulticastReplay(Callable<? extends ConnectableObservable<U>> connectableFactory2, Function<? super Observable<U>, ? extends ObservableSource<R>> selector2) {
            this.connectableFactory = connectableFactory2;
            this.selector = selector2;
        }

        /* access modifiers changed from: protected */
        public void subscribeActual(Observer<? super R> child) {
            try {
                ConnectableObservable<U> co = (ConnectableObservable) ObjectHelper.requireNonNull(this.connectableFactory.call(), "The connectableFactory returned a null ConnectableObservable");
                ObservableSource<R> observable = (ObservableSource) ObjectHelper.requireNonNull(this.selector.apply(co), "The selector returned a null ObservableSource");
                ObserverResourceWrapper<R> srw = new ObserverResourceWrapper<>(child);
                observable.subscribe(srw);
                co.connect(new DisposeConsumer(srw));
            } catch (Throwable e) {
                Exceptions.throwIfFatal(e);
                EmptyDisposable.error(e, (Observer<?>) child);
            }
        }
    }

    static final class Replay<T> extends ConnectableObservable<T> {
        private final ConnectableObservable<T> co;
        private final Observable<T> observable;

        Replay(ConnectableObservable<T> co2, Observable<T> observable2) {
            this.co = co2;
            this.observable = observable2;
        }

        public void connect(Consumer<? super Disposable> connection) {
            this.co.connect(connection);
        }

        /* access modifiers changed from: protected */
        public void subscribeActual(Observer<? super T> observer) {
            this.observable.subscribe(observer);
        }
    }
}
