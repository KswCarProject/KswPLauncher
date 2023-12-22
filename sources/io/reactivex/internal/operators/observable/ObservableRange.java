package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.internal.observers.BasicIntQueueDisposable;

/* loaded from: classes.dex */
public final class ObservableRange extends Observable<Integer> {
    private final long end;
    private final int start;

    public ObservableRange(int start, int count) {
        this.start = start;
        this.end = start + count;
    }

    @Override // io.reactivex.Observable
    protected void subscribeActual(Observer<? super Integer> o) {
        RangeDisposable parent = new RangeDisposable(o, this.start, this.end);
        o.onSubscribe(parent);
        parent.run();
    }

    /* loaded from: classes.dex */
    static final class RangeDisposable extends BasicIntQueueDisposable<Integer> {
        private static final long serialVersionUID = 396518478098735504L;
        final Observer<? super Integer> downstream;
        final long end;
        boolean fused;
        long index;

        RangeDisposable(Observer<? super Integer> actual, long start, long end) {
            this.downstream = actual;
            this.index = start;
            this.end = end;
        }

        void run() {
            if (this.fused) {
                return;
            }
            Observer<? super Integer> actual = this.downstream;
            long e = this.end;
            for (long i = this.index; i != e && get() == 0; i++) {
                actual.onNext(Integer.valueOf((int) i));
            }
            if (get() == 0) {
                lazySet(1);
                actual.onComplete();
            }
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public Integer poll() throws Exception {
            long i = this.index;
            if (i != this.end) {
                this.index = 1 + i;
                return Integer.valueOf((int) i);
            }
            lazySet(1);
            return null;
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public boolean isEmpty() {
            return this.index == this.end;
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public void clear() {
            this.index = this.end;
            lazySet(1);
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            set(1);
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return get() != 0;
        }

        @Override // io.reactivex.internal.fuseable.QueueFuseable
        public int requestFusion(int mode) {
            if ((mode & 1) != 0) {
                this.fused = true;
                return 1;
            }
            return 0;
        }
    }
}
