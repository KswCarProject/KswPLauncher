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

/* loaded from: classes.dex */
public abstract class BaseTestConsumer<T, U extends BaseTestConsumer<T, U>> implements Disposable {
    protected boolean checkSubscriptionOnce;
    protected long completions;
    protected int establishedFusionMode;
    protected int initialFusionMode;
    protected Thread lastThread;
    protected CharSequence tag;
    protected boolean timeout;
    protected final List<T> values = new VolatileSizeArrayList();
    protected final List<Throwable> errors = new VolatileSizeArrayList();
    protected final CountDownLatch done = new CountDownLatch(1);

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

    protected final AssertionError fail(String message) {
        StringBuilder b = new StringBuilder(message.length() + 64);
        b.append(message);
        b.append(" (").append("latch = ").append(this.done.getCount()).append(", ").append("values = ").append(this.values.size()).append(", ").append("errors = ").append(this.errors.size()).append(", ").append("completions = ").append(this.completions);
        if (this.timeout) {
            b.append(", timeout!");
        }
        if (isDisposed()) {
            b.append(", disposed!");
        }
        CharSequence tag = this.tag;
        if (tag != null) {
            b.append(", tag = ").append(tag);
        }
        b.append(')');
        AssertionError ae = new AssertionError(b.toString());
        if (!this.errors.isEmpty()) {
            if (this.errors.size() == 1) {
                ae.initCause(this.errors.get(0));
            } else {
                CompositeException ce = new CompositeException(this.errors);
                ae.initCause(ce);
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
        boolean d = this.done.getCount() == 0 || this.done.await(time, unit);
        this.timeout = d ? false : true;
        return d;
    }

    public final U assertComplete() {
        long c = this.completions;
        if (c == 0) {
            throw fail("Not completed");
        }
        if (c > 1) {
            throw fail("Multiple completions: " + c);
        }
        return this;
    }

    public final U assertNotComplete() {
        long c = this.completions;
        if (c == 1) {
            throw fail("Completed!");
        }
        if (c > 1) {
            throw fail("Multiple completions: " + c);
        }
        return this;
    }

    public final U assertNoErrors() {
        int s = this.errors.size();
        if (s != 0) {
            throw fail("Error(s) present: " + this.errors);
        }
        return this;
    }

    public final U assertError(Throwable error) {
        return assertError(Functions.equalsWith(error));
    }

    public final U assertError(Class<? extends Throwable> errorClass) {
        return assertError(Functions.isInstanceOf(errorClass));
    }

    public final U assertError(Predicate<Throwable> errorPredicate) {
        int s = this.errors.size();
        if (s == 0) {
            throw fail("No errors");
        }
        boolean found = false;
        Iterator<Throwable> it = this.errors.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Throwable e = it.next();
            try {
                if (errorPredicate.test(e)) {
                    found = true;
                    break;
                }
            } catch (Exception ex) {
                throw ExceptionHelper.wrapOrThrow(ex);
            }
        }
        if (found) {
            if (s != 1) {
                throw fail("Error present but other errors as well");
            }
            return this;
        }
        throw fail("Error not present");
    }

    public final U assertValue(T value) {
        int s = this.values.size();
        if (s != 1) {
            throw fail("expected: " + valueAndClass(value) + " but was: " + this.values);
        }
        T v = this.values.get(0);
        if (!ObjectHelper.equals(value, v)) {
            throw fail("expected: " + valueAndClass(value) + " but was: " + valueAndClass(v));
        }
        return this;
    }

    public final U assertNever(T value) {
        int s = this.values.size();
        for (int i = 0; i < s; i++) {
            T v = this.values.get(i);
            if (ObjectHelper.equals(v, value)) {
                throw fail("Value at position " + i + " is equal to " + valueAndClass(value) + "; Expected them to be different");
            }
        }
        return this;
    }

    public final U assertValue(Predicate<T> valuePredicate) {
        assertValueAt(0, (Predicate) valuePredicate);
        if (this.values.size() > 1) {
            throw fail("Value present but other values as well");
        }
        return this;
    }

    public final U assertNever(Predicate<? super T> valuePredicate) {
        int s = this.values.size();
        for (int i = 0; i < s; i++) {
            try {
                if (valuePredicate.test((T) this.values.get(i))) {
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
        }
        if (index >= s) {
            throw fail("Invalid index: " + index);
        }
        T v = this.values.get(index);
        if (!ObjectHelper.equals(value, v)) {
            throw fail("expected: " + valueAndClass(value) + " but was: " + valueAndClass(v));
        }
        return this;
    }

    public final U assertValueAt(int index, Predicate<T> valuePredicate) {
        int s = this.values.size();
        if (s == 0) {
            throw fail("No values");
        }
        if (index >= this.values.size()) {
            throw fail("Invalid index: " + index);
        }
        boolean found = false;
        try {
            if (valuePredicate.test(this.values.get(index))) {
                found = true;
            }
            if (!found) {
                throw fail("Value not present");
            }
            return this;
        } catch (Exception ex) {
            throw ExceptionHelper.wrapOrThrow(ex);
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
        if (s != count) {
            throw fail("Value counts differ; expected: " + count + " but was: " + s);
        }
        return this;
    }

    public final U assertNoValues() {
        return assertValueCount(0);
    }

    public final U assertValues(T... values) {
        int s = this.values.size();
        if (s != values.length) {
            throw fail("Value count differs; expected: " + values.length + " " + Arrays.toString(values) + " but was: " + s + " " + this.values);
        }
        for (int i = 0; i < s; i++) {
            T v = this.values.get(i);
            T u = values[i];
            if (!ObjectHelper.equals(u, v)) {
                throw fail("Values at position " + i + " differ; expected: " + valueAndClass(u) + " but was: " + valueAndClass(v));
            }
        }
        return this;
    }

    public final U assertValuesOnly(T... values) {
        return (U) assertSubscribed().assertValues(values).assertNoErrors().assertNotComplete();
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
        return (U) assertSubscribed().assertValueSet(expected).assertNoErrors().assertNotComplete();
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
            if (!actualNext || !expectedNext) {
                break;
            }
            T u = expectedIterator.next();
            T v = actualIterator.next();
            if (!ObjectHelper.equals(u, v)) {
                throw fail("Values at position " + i + " differ; expected: " + valueAndClass(u) + " but was: " + valueAndClass(v));
            }
            i++;
        }
        if (actualNext) {
            throw fail("More values received than expected (" + i + ")");
        }
        if (expectedNext) {
            throw fail("Fewer values received than expected (" + i + ")");
        }
        return this;
    }

    public final U assertValueSequenceOnly(Iterable<? extends T> sequence) {
        return (U) assertSubscribed().assertValueSequence(sequence).assertNoErrors().assertNotComplete();
    }

    public final U assertTerminated() {
        if (this.done.getCount() != 0) {
            throw fail("Subscriber still running!");
        }
        long c = this.completions;
        if (c > 1) {
            throw fail("Terminated with multiple completions: " + c);
        }
        int s = this.errors.size();
        if (s > 1) {
            throw fail("Terminated with multiple errors: " + s);
        }
        if (c != 0 && s != 0) {
            throw fail("Terminated with multiple completions and errors: " + c);
        }
        return this;
    }

    public final U assertNotTerminated() {
        if (this.done.getCount() == 0) {
            throw fail("Subscriber terminated!");
        }
        return this;
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
        }
        if (s == 1) {
            Throwable e = this.errors.get(0);
            String errorMessage = e.getMessage();
            if (!ObjectHelper.equals(message, errorMessage)) {
                throw fail("Error message differs; exptected: " + message + " but was: " + errorMessage);
            }
            return this;
        }
        throw fail("Multiple errors");
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

    public final U assertResult(T... values) {
        return (U) assertSubscribed().assertValues(values).assertNoErrors().assertComplete();
    }

    public final U assertFailure(Class<? extends Throwable> error, T... values) {
        return (U) assertSubscribed().assertValues(values).assertError(error).assertNotComplete();
    }

    public final U assertFailure(Predicate<Throwable> errorPredicate, T... values) {
        return (U) assertSubscribed().assertValues(values).assertError(errorPredicate).assertNotComplete();
    }

    public final U assertFailureAndMessage(Class<? extends Throwable> error, String message, T... values) {
        return (U) assertSubscribed().assertValues(values).assertError(error).assertErrorMessage(message).assertNotComplete();
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
        return (U) assertSubscribed().assertNoValues().assertNoErrors().assertNotComplete();
    }

    public final U withTag(CharSequence tag) {
        this.tag = tag;
        return this;
    }

    /* loaded from: classes.dex */
    public enum TestWaitStrategy implements Runnable {
        SPIN { // from class: io.reactivex.observers.BaseTestConsumer.TestWaitStrategy.1
            @Override // io.reactivex.observers.BaseTestConsumer.TestWaitStrategy, java.lang.Runnable
            public void run() {
            }
        },
        YIELD { // from class: io.reactivex.observers.BaseTestConsumer.TestWaitStrategy.2
            @Override // io.reactivex.observers.BaseTestConsumer.TestWaitStrategy, java.lang.Runnable
            public void run() {
                Thread.yield();
            }
        },
        SLEEP_1MS { // from class: io.reactivex.observers.BaseTestConsumer.TestWaitStrategy.3
            @Override // io.reactivex.observers.BaseTestConsumer.TestWaitStrategy, java.lang.Runnable
            public void run() {
                sleep(1);
            }
        },
        SLEEP_10MS { // from class: io.reactivex.observers.BaseTestConsumer.TestWaitStrategy.4
            @Override // io.reactivex.observers.BaseTestConsumer.TestWaitStrategy, java.lang.Runnable
            public void run() {
                sleep(10);
            }
        },
        SLEEP_100MS { // from class: io.reactivex.observers.BaseTestConsumer.TestWaitStrategy.5
            @Override // io.reactivex.observers.BaseTestConsumer.TestWaitStrategy, java.lang.Runnable
            public void run() {
                sleep(100);
            }
        },
        SLEEP_1000MS { // from class: io.reactivex.observers.BaseTestConsumer.TestWaitStrategy.6
            @Override // io.reactivex.observers.BaseTestConsumer.TestWaitStrategy, java.lang.Runnable
            public void run() {
                sleep(1000);
            }
        };

        @Override // java.lang.Runnable
        public abstract void run();

        static void sleep(int millis) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public final U awaitCount(int atLeast) {
        return awaitCount(atLeast, TestWaitStrategy.SLEEP_10MS, 5000L);
    }

    public final U awaitCount(int atLeast, Runnable waitStrategy) {
        return awaitCount(atLeast, waitStrategy, 5000L);
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
        if (!this.timeout) {
            throw fail("No timeout?!");
        }
        return this;
    }

    public final U assertNoTimeout() {
        if (this.timeout) {
            throw fail("Timeout?!");
        }
        return this;
    }
}
