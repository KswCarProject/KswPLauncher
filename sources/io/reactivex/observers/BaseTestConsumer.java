package io.reactivex.observers;

import io.reactivex.Notification;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.VolatileSizeArrayList;
import io.reactivex.observers.BaseTestConsumer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public abstract class BaseTestConsumer<T, U extends BaseTestConsumer<T, U>> implements Disposable {
    protected boolean checkSubscriptionOnce;
    protected long completions;
    protected final CountDownLatch done = new CountDownLatch(1);
    protected final List<Throwable> errors = new VolatileSizeArrayList();
    protected int establishedFusionMode;
    protected int initialFusionMode;
    protected Thread lastThread;
    protected CharSequence tag;
    protected boolean timeout;
    protected final List<T> values = new VolatileSizeArrayList();

    public abstract U assertNotSubscribed();

    public abstract U assertSubscribed();

    public final Thread lastThread() {
        return this.lastThread;
    }

    public final List<T> values() {
        return this.values;
    }

    public final List<Throwable> errors() {
        return this.errors;
    }

    public final long completions() {
        return this.completions;
    }

    public final boolean isTerminated() {
        return this.done.getCount() == 0;
    }

    public final int valueCount() {
        return this.values.size();
    }

    public final int errorCount() {
        return this.errors.size();
    }

    /* access modifiers changed from: protected */
    public final AssertionError fail(String message) {
        StringBuilder b = new StringBuilder(message.length() + 64);
        b.append(message);
        b.append(" (").append("latch = ").append(this.done.getCount()).append(", ").append("values = ").append(this.values.size()).append(", ").append("errors = ").append(this.errors.size()).append(", ").append("completions = ").append(this.completions);
        if (this.timeout) {
            b.append(", timeout!");
        }
        if (isDisposed()) {
            b.append(", disposed!");
        }
        CharSequence tag2 = this.tag;
        if (tag2 != null) {
            b.append(", tag = ").append(tag2);
        }
        b.append(')');
        AssertionError ae = new AssertionError(b.toString());
        if (!this.errors.isEmpty()) {
            if (this.errors.size() == 1) {
                ae.initCause(this.errors.get(0));
            } else {
                ae.initCause(new CompositeException((Iterable<? extends Throwable>) this.errors));
            }
        }
        return ae;
    }

    public final U await() throws InterruptedException {
        if (this.done.getCount() == 0) {
            return this;
        }
        this.done.await();
        return this;
    }

    public final boolean await(long time, TimeUnit unit) throws InterruptedException {
        boolean z = false;
        boolean d = this.done.getCount() == 0 || this.done.await(time, unit);
        if (!d) {
            z = true;
        }
        this.timeout = z;
        return d;
    }

    public final U assertComplete() {
        long c = this.completions;
        if (c == 0) {
            throw fail("Not completed");
        } else if (c <= 1) {
            return this;
        } else {
            throw fail("Multiple completions: " + c);
        }
    }

    public final U assertNotComplete() {
        long c = this.completions;
        if (c == 1) {
            throw fail("Completed!");
        } else if (c <= 1) {
            return this;
        } else {
            throw fail("Multiple completions: " + c);
        }
    }

    public final U assertNoErrors() {
        if (this.errors.size() == 0) {
            return this;
        }
        throw fail("Error(s) present: " + this.errors);
    }

    public final U assertError(Throwable error) {
        return assertError((Predicate<Throwable>) Functions.equalsWith(error));
    }

    public final U assertError(Class<? extends Throwable> errorClass) {
        return assertError((Predicate<Throwable>) Functions.isInstanceOf(errorClass));
    }

    public final U assertError(Predicate<Throwable> errorPredicate) {
        int s = this.errors.size();
        if (s != 0) {
            boolean found = false;
            Iterator<Throwable> it = this.errors.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                try {
                    if (errorPredicate.test(it.next())) {
                        found = true;
                        break;
                    }
                } catch (Exception ex) {
                    throw ExceptionHelper.wrapOrThrow(ex);
                }
            }
            if (!found) {
                throw fail("Error not present");
            } else if (s == 1) {
                return this;
            } else {
                throw fail("Error present but other errors as well");
            }
        } else {
            throw fail("No errors");
        }
    }

    public final U assertValue(T value) {
        if (this.values.size() == 1) {
            T v = this.values.get(0);
            if (ObjectHelper.equals(value, v)) {
                return this;
            }
            throw fail("expected: " + valueAndClass(value) + " but was: " + valueAndClass(v));
        }
        throw fail("expected: " + valueAndClass(value) + " but was: " + this.values);
    }

    public final U assertNever(T value) {
        int s = this.values.size();
        int i = 0;
        while (i < s) {
            if (!ObjectHelper.equals(this.values.get(i), value)) {
                i++;
            } else {
                throw fail("Value at position " + i + " is equal to " + valueAndClass(value) + "; Expected them to be different");
            }
        }
        return this;
    }

    public final U assertValue(Predicate<T> valuePredicate) {
        assertValueAt(0, valuePredicate);
        if (this.values.size() <= 1) {
            return this;
        }
        throw fail("Value present but other values as well");
    }

    public final U assertNever(Predicate<? super T> valuePredicate) {
        int s = this.values.size();
        int i = 0;
        while (i < s) {
            try {
                if (!valuePredicate.test(this.values.get(i))) {
                    i++;
                } else {
                    throw fail("Value at position " + i + " matches predicate " + valuePredicate.toString() + ", which was not expected.");
                }
            } catch (Exception ex) {
                throw ExceptionHelper.wrapOrThrow(ex);
            }
        }
        return this;
    }

    public final U assertValueAt(int index, T value) {
        int s = this.values.size();
        if (s == 0) {
            throw fail("No values");
        } else if (index < s) {
            T v = this.values.get(index);
            if (ObjectHelper.equals(value, v)) {
                return this;
            }
            throw fail("expected: " + valueAndClass(value) + " but was: " + valueAndClass(v));
        } else {
            throw fail("Invalid index: " + index);
        }
    }

    public final U assertValueAt(int index, Predicate<T> valuePredicate) {
        if (this.values.size() == 0) {
            throw fail("No values");
        } else if (index < this.values.size()) {
            boolean found = false;
            try {
                if (valuePredicate.test(this.values.get(index))) {
                    found = true;
                }
                if (found) {
                    return this;
                }
                throw fail("Value not present");
            } catch (Exception ex) {
                throw ExceptionHelper.wrapOrThrow(ex);
            }
        } else {
            throw fail("Invalid index: " + index);
        }
    }

    public static String valueAndClass(Object o) {
        if (o != null) {
            return o + " (class: " + o.getClass().getSimpleName() + ")";
        }
        return "null";
    }

    public final U assertValueCount(int count) {
        int s = this.values.size();
        if (s == count) {
            return this;
        }
        throw fail("Value counts differ; expected: " + count + " but was: " + s);
    }

    public final U assertNoValues() {
        return assertValueCount(0);
    }

    public final U assertValues(T... values2) {
        int s = this.values.size();
        if (s == values2.length) {
            int i = 0;
            while (i < s) {
                T v = this.values.get(i);
                T u = values2[i];
                if (ObjectHelper.equals(u, v)) {
                    i++;
                } else {
                    throw fail("Values at position " + i + " differ; expected: " + valueAndClass(u) + " but was: " + valueAndClass(v));
                }
            }
            return this;
        }
        throw fail("Value count differs; expected: " + values2.length + " " + Arrays.toString(values2) + " but was: " + s + " " + this.values);
    }

    public final U assertValuesOnly(T... values2) {
        return assertSubscribed().assertValues(values2).assertNoErrors().assertNotComplete();
    }

    public final U assertValueSet(Collection<? extends T> expected) {
        if (expected.isEmpty()) {
            assertNoValues();
            return this;
        }
        for (T v : this.values) {
            if (!expected.contains(v)) {
                throw fail("Value not in the expected collection: " + valueAndClass(v));
            }
        }
        return this;
    }

    public final U assertValueSetOnly(Collection<? extends T> expected) {
        return assertSubscribed().assertValueSet(expected).assertNoErrors().assertNotComplete();
    }

    public final U assertValueSequence(Iterable<? extends T> sequence) {
        boolean expectedNext;
        boolean actualNext;
        int i = 0;
        Iterator<T> actualIterator = this.values.iterator();
        Iterator<? extends T> expectedIterator = sequence.iterator();
        while (true) {
            expectedNext = expectedIterator.hasNext();
            actualNext = actualIterator.hasNext();
            if (actualNext && expectedNext) {
                T u = expectedIterator.next();
                T v = actualIterator.next();
                if (ObjectHelper.equals(u, v)) {
                    i++;
                } else {
                    throw fail("Values at position " + i + " differ; expected: " + valueAndClass(u) + " but was: " + valueAndClass(v));
                }
            }
        }
        if (actualNext) {
            throw fail("More values received than expected (" + i + ")");
        } else if (!expectedNext) {
            return this;
        } else {
            throw fail("Fewer values received than expected (" + i + ")");
        }
    }

    public final U assertValueSequenceOnly(Iterable<? extends T> sequence) {
        return assertSubscribed().assertValueSequence(sequence).assertNoErrors().assertNotComplete();
    }

    public final U assertTerminated() {
        if (this.done.getCount() == 0) {
            long c = this.completions;
            if (c <= 1) {
                int s = this.errors.size();
                if (s > 1) {
                    throw fail("Terminated with multiple errors: " + s);
                } else if (c == 0 || s == 0) {
                    return this;
                } else {
                    throw fail("Terminated with multiple completions and errors: " + c);
                }
            } else {
                throw fail("Terminated with multiple completions: " + c);
            }
        } else {
            throw fail("Subscriber still running!");
        }
    }

    public final U assertNotTerminated() {
        if (this.done.getCount() != 0) {
            return this;
        }
        throw fail("Subscriber terminated!");
    }

    public final boolean awaitTerminalEvent() {
        try {
            await();
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public final boolean awaitTerminalEvent(long duration, TimeUnit unit) {
        try {
            return await(duration, unit);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public final U assertErrorMessage(String message) {
        int s = this.errors.size();
        if (s == 0) {
            throw fail("No errors");
        } else if (s == 1) {
            String errorMessage = this.errors.get(0).getMessage();
            if (ObjectHelper.equals(message, errorMessage)) {
                return this;
            }
            throw fail("Error message differs; exptected: " + message + " but was: " + errorMessage);
        } else {
            throw fail("Multiple errors");
        }
    }

    public final List<List<Object>> getEvents() {
        List<List<Object>> result = new ArrayList<>();
        result.add(values());
        result.add(errors());
        List<Object> completeList = new ArrayList<>();
        for (long i = 0; i < this.completions; i++) {
            completeList.add(Notification.createOnComplete());
        }
        result.add(completeList);
        return result;
    }

    public final U assertResult(T... values2) {
        return assertSubscribed().assertValues(values2).assertNoErrors().assertComplete();
    }

    public final U assertFailure(Class<? extends Throwable> error, T... values2) {
        return assertSubscribed().assertValues(values2).assertError(error).assertNotComplete();
    }

    public final U assertFailure(Predicate<Throwable> errorPredicate, T... values2) {
        return assertSubscribed().assertValues(values2).assertError(errorPredicate).assertNotComplete();
    }

    public final U assertFailureAndMessage(Class<? extends Throwable> error, String message, T... values2) {
        return assertSubscribed().assertValues(values2).assertError(error).assertErrorMessage(message).assertNotComplete();
    }

    public final U awaitDone(long time, TimeUnit unit) {
        try {
            if (!this.done.await(time, unit)) {
                this.timeout = true;
                dispose();
            }
            return this;
        } catch (InterruptedException ex) {
            dispose();
            throw ExceptionHelper.wrapOrThrow(ex);
        }
    }

    public final U assertEmpty() {
        return assertSubscribed().assertNoValues().assertNoErrors().assertNotComplete();
    }

    public final U withTag(CharSequence tag2) {
        this.tag = tag2;
        return this;
    }

    public enum TestWaitStrategy implements Runnable {
        SPIN {
            public void run() {
            }
        },
        YIELD {
            public void run() {
                Thread.yield();
            }
        },
        SLEEP_1MS {
            public void run() {
                sleep(1);
            }
        },
        SLEEP_10MS {
            public void run() {
                sleep(10);
            }
        },
        SLEEP_100MS {
            public void run() {
                sleep(100);
            }
        },
        SLEEP_1000MS {
            public void run() {
                sleep(1000);
            }
        };

        public abstract void run();

        static void sleep(int millis) {
            try {
                Thread.sleep((long) millis);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public final U awaitCount(int atLeast) {
        return awaitCount(atLeast, TestWaitStrategy.SLEEP_10MS, 5000);
    }

    public final U awaitCount(int atLeast, Runnable waitStrategy) {
        return awaitCount(atLeast, waitStrategy, 5000);
    }

    public final U awaitCount(int atLeast, Runnable waitStrategy, long timeoutMillis) {
        long start = System.currentTimeMillis();
        while (true) {
            if (timeoutMillis <= 0 || System.currentTimeMillis() - start < timeoutMillis) {
                if (this.done.getCount() == 0 || this.values.size() >= atLeast) {
                    break;
                }
                waitStrategy.run();
            } else {
                this.timeout = true;
                break;
            }
        }
        return this;
    }

    public final boolean isTimeout() {
        return this.timeout;
    }

    public final U clearTimeout() {
        this.timeout = false;
        return this;
    }

    public final U assertTimeout() {
        if (this.timeout) {
            return this;
        }
        throw fail("No timeout?!");
    }

    public final U assertNoTimeout() {
        if (!this.timeout) {
            return this;
        }
        throw fail("Timeout?!");
    }
}
