package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.util.BlockingHelper;
import io.reactivex.internal.util.ExceptionHelper;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class BlockingObservableIterable<T> implements Iterable<T> {
    final int bufferSize;
    final ObservableSource<? extends T> source;

    public BlockingObservableIterable(ObservableSource<? extends T> source2, int bufferSize2) {
        this.source = source2;
        this.bufferSize = bufferSize2;
    }

    public Iterator<T> iterator() {
        BlockingObservableIterator<T> it = new BlockingObservableIterator<>(this.bufferSize);
        this.source.subscribe(it);
        return it;
    }

    static final class BlockingObservableIterator<T> extends AtomicReference<Disposable> implements Observer<T>, Iterator<T>, Disposable {
        private static final long serialVersionUID = 6695226475494099826L;
        final Condition condition;
        volatile boolean done;
        volatile Throwable error;
        final Lock lock;
        final SpscLinkedArrayQueue<T> queue;

        BlockingObservableIterator(int batchSize) {
            this.queue = new SpscLinkedArrayQueue<>(batchSize);
            ReentrantLock reentrantLock = new ReentrantLock();
            this.lock = reentrantLock;
            this.condition = reentrantLock.newCondition();
        }

        public boolean hasNext() {
            while (!isDisposed()) {
                boolean d = this.done;
                boolean empty = this.queue.isEmpty();
                if (d) {
                    Throwable e = this.error;
                    if (e != null) {
                        throw ExceptionHelper.wrapOrThrow(e);
                    } else if (empty) {
                        return false;
                    }
                }
                if (!empty) {
                    return true;
                }
                BlockingHelper.verifyNonBlocking();
                this.lock.lock();
                while (!this.done && this.queue.isEmpty() && !isDisposed()) {
                    try {
                        this.condition.await();
                    } catch (InterruptedException ex) {
                        DisposableHelper.dispose(this);
                        signalConsumer();
                        throw ExceptionHelper.wrapOrThrow(ex);
                    } catch (Throwable th) {
                        this.lock.unlock();
                        throw th;
                    }
                }
                this.lock.unlock();
            }
            Throwable e2 = this.error;
            if (e2 == null) {
                return false;
            }
            throw ExceptionHelper.wrapOrThrow(e2);
        }

        public T next() {
            if (hasNext()) {
                return this.queue.poll();
            }
            throw new NoSuchElementException();
        }

        public void onSubscribe(Disposable d) {
            DisposableHelper.setOnce(this, d);
        }

        public void onNext(T t) {
            this.queue.offer(t);
            signalConsumer();
        }

        public void onError(Throwable t) {
            this.error = t;
            this.done = true;
            signalConsumer();
        }

        public void onComplete() {
            this.done = true;
            signalConsumer();
        }

        /* access modifiers changed from: package-private */
        public void signalConsumer() {
            this.lock.lock();
            try {
                this.condition.signalAll();
            } finally {
                this.lock.unlock();
            }
        }

        public void remove() {
            throw new UnsupportedOperationException("remove");
        }

        public void dispose() {
            DisposableHelper.dispose(this);
            signalConsumer();
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }
    }
}
