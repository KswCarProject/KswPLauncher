package io.reactivex.schedulers;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public final class TestScheduler extends Scheduler {
    long counter;
    final Queue<TimedRunnable> queue = new PriorityBlockingQueue(11);
    volatile long time;

    public TestScheduler() {
    }

    public TestScheduler(long delayTime, TimeUnit unit) {
        this.time = unit.toNanos(delayTime);
    }

    static final class TimedRunnable implements Comparable<TimedRunnable> {
        final long count;
        final Runnable run;
        final TestWorker scheduler;
        final long time;

        TimedRunnable(TestWorker scheduler2, long time2, Runnable run2, long count2) {
            this.time = time2;
            this.run = run2;
            this.scheduler = scheduler2;
            this.count = count2;
        }

        public String toString() {
            return String.format("TimedRunnable(time = %d, run = %s)", new Object[]{Long.valueOf(this.time), this.run.toString()});
        }

        public int compareTo(TimedRunnable o) {
            long j = this.time;
            long j2 = o.time;
            if (j == j2) {
                return ObjectHelper.compare(this.count, o.count);
            }
            return ObjectHelper.compare(j, j2);
        }
    }

    public long now(TimeUnit unit) {
        return unit.convert(this.time, TimeUnit.NANOSECONDS);
    }

    public void advanceTimeBy(long delayTime, TimeUnit unit) {
        advanceTimeTo(this.time + unit.toNanos(delayTime), TimeUnit.NANOSECONDS);
    }

    public void advanceTimeTo(long delayTime, TimeUnit unit) {
        triggerActions(unit.toNanos(delayTime));
    }

    public void triggerActions() {
        triggerActions(this.time);
    }

    private void triggerActions(long targetTimeInNanoseconds) {
        while (true) {
            TimedRunnable current = this.queue.peek();
            if (current == null || current.time > targetTimeInNanoseconds) {
                this.time = targetTimeInNanoseconds;
            } else {
                this.time = current.time == 0 ? this.time : current.time;
                this.queue.remove(current);
                if (!current.scheduler.disposed) {
                    current.run.run();
                }
            }
        }
        this.time = targetTimeInNanoseconds;
    }

    public Scheduler.Worker createWorker() {
        return new TestWorker();
    }

    final class TestWorker extends Scheduler.Worker {
        volatile boolean disposed;

        TestWorker() {
        }

        public void dispose() {
            this.disposed = true;
        }

        public boolean isDisposed() {
            return this.disposed;
        }

        public Disposable schedule(Runnable run, long delayTime, TimeUnit unit) {
            if (this.disposed) {
                return EmptyDisposable.INSTANCE;
            }
            long nanos = unit.toNanos(delayTime) + TestScheduler.this.time;
            TestScheduler testScheduler = TestScheduler.this;
            long j = testScheduler.counter;
            testScheduler.counter = 1 + j;
            TimedRunnable timedRunnable = new TimedRunnable(this, nanos, run, j);
            TestScheduler.this.queue.add(timedRunnable);
            return Disposables.fromRunnable(new QueueRemove(timedRunnable));
        }

        public Disposable schedule(Runnable run) {
            if (this.disposed) {
                return EmptyDisposable.INSTANCE;
            }
            TestScheduler testScheduler = TestScheduler.this;
            long j = testScheduler.counter;
            testScheduler.counter = 1 + j;
            TimedRunnable timedRunnable = new TimedRunnable(this, 0, run, j);
            TestScheduler.this.queue.add(timedRunnable);
            return Disposables.fromRunnable(new QueueRemove(timedRunnable));
        }

        public long now(TimeUnit unit) {
            return TestScheduler.this.now(unit);
        }

        final class QueueRemove implements Runnable {
            final TimedRunnable timedAction;

            QueueRemove(TimedRunnable timedAction2) {
                this.timedAction = timedAction2;
            }

            public void run() {
                TestScheduler.this.queue.remove(this.timedAction);
            }
        }
    }
}
