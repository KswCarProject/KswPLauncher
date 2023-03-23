package io.reactivex.plugins;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.flowables.ConnectableFlowable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.schedulers.ComputationScheduler;
import io.reactivex.internal.schedulers.IoScheduler;
import io.reactivex.internal.schedulers.NewThreadScheduler;
import io.reactivex.internal.schedulers.SingleScheduler;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.parallel.ParallelFlowable;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadFactory;
import org.reactivestreams.Subscriber;

public final class RxJavaPlugins {
    static volatile Consumer<? super Throwable> errorHandler;
    static volatile boolean failNonBlockingScheduler;
    static volatile boolean lockdown;
    static volatile BooleanSupplier onBeforeBlocking;
    static volatile Function<? super Completable, ? extends Completable> onCompletableAssembly;
    static volatile BiFunction<? super Completable, ? super CompletableObserver, ? extends CompletableObserver> onCompletableSubscribe;
    static volatile Function<? super Scheduler, ? extends Scheduler> onComputationHandler;
    static volatile Function<? super ConnectableFlowable, ? extends ConnectableFlowable> onConnectableFlowableAssembly;
    static volatile Function<? super ConnectableObservable, ? extends ConnectableObservable> onConnectableObservableAssembly;
    static volatile Function<? super Flowable, ? extends Flowable> onFlowableAssembly;
    static volatile BiFunction<? super Flowable, ? super Subscriber, ? extends Subscriber> onFlowableSubscribe;
    static volatile Function<? super Callable<Scheduler>, ? extends Scheduler> onInitComputationHandler;
    static volatile Function<? super Callable<Scheduler>, ? extends Scheduler> onInitIoHandler;
    static volatile Function<? super Callable<Scheduler>, ? extends Scheduler> onInitNewThreadHandler;
    static volatile Function<? super Callable<Scheduler>, ? extends Scheduler> onInitSingleHandler;
    static volatile Function<? super Scheduler, ? extends Scheduler> onIoHandler;
    static volatile Function<? super Maybe, ? extends Maybe> onMaybeAssembly;
    static volatile BiFunction<? super Maybe, ? super MaybeObserver, ? extends MaybeObserver> onMaybeSubscribe;
    static volatile Function<? super Scheduler, ? extends Scheduler> onNewThreadHandler;
    static volatile Function<? super Observable, ? extends Observable> onObservableAssembly;
    static volatile BiFunction<? super Observable, ? super Observer, ? extends Observer> onObservableSubscribe;
    static volatile Function<? super ParallelFlowable, ? extends ParallelFlowable> onParallelAssembly;
    static volatile Function<? super Runnable, ? extends Runnable> onScheduleHandler;
    static volatile Function<? super Single, ? extends Single> onSingleAssembly;
    static volatile Function<? super Scheduler, ? extends Scheduler> onSingleHandler;
    static volatile BiFunction<? super Single, ? super SingleObserver, ? extends SingleObserver> onSingleSubscribe;

    public static void lockdown() {
        lockdown = true;
    }

    public static boolean isLockdown() {
        return lockdown;
    }

    public static void setFailOnNonBlockingScheduler(boolean enable) {
        if (!lockdown) {
            failNonBlockingScheduler = enable;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    public static boolean isFailOnNonBlockingScheduler() {
        return failNonBlockingScheduler;
    }

    public static Function<? super Scheduler, ? extends Scheduler> getComputationSchedulerHandler() {
        return onComputationHandler;
    }

    public static Consumer<? super Throwable> getErrorHandler() {
        return errorHandler;
    }

    public static Function<? super Callable<Scheduler>, ? extends Scheduler> getInitComputationSchedulerHandler() {
        return onInitComputationHandler;
    }

    public static Function<? super Callable<Scheduler>, ? extends Scheduler> getInitIoSchedulerHandler() {
        return onInitIoHandler;
    }

    public static Function<? super Callable<Scheduler>, ? extends Scheduler> getInitNewThreadSchedulerHandler() {
        return onInitNewThreadHandler;
    }

    public static Function<? super Callable<Scheduler>, ? extends Scheduler> getInitSingleSchedulerHandler() {
        return onInitSingleHandler;
    }

    public static Function<? super Scheduler, ? extends Scheduler> getIoSchedulerHandler() {
        return onIoHandler;
    }

    public static Function<? super Scheduler, ? extends Scheduler> getNewThreadSchedulerHandler() {
        return onNewThreadHandler;
    }

    public static Function<? super Runnable, ? extends Runnable> getScheduleHandler() {
        return onScheduleHandler;
    }

    public static Function<? super Scheduler, ? extends Scheduler> getSingleSchedulerHandler() {
        return onSingleHandler;
    }

    public static Scheduler initComputationScheduler(Callable<Scheduler> defaultScheduler) {
        ObjectHelper.requireNonNull(defaultScheduler, "Scheduler Callable can't be null");
        Function<? super Callable<Scheduler>, ? extends Scheduler> f = onInitComputationHandler;
        if (f == null) {
            return callRequireNonNull(defaultScheduler);
        }
        return applyRequireNonNull(f, defaultScheduler);
    }

    public static Scheduler initIoScheduler(Callable<Scheduler> defaultScheduler) {
        ObjectHelper.requireNonNull(defaultScheduler, "Scheduler Callable can't be null");
        Function<? super Callable<Scheduler>, ? extends Scheduler> f = onInitIoHandler;
        if (f == null) {
            return callRequireNonNull(defaultScheduler);
        }
        return applyRequireNonNull(f, defaultScheduler);
    }

    public static Scheduler initNewThreadScheduler(Callable<Scheduler> defaultScheduler) {
        ObjectHelper.requireNonNull(defaultScheduler, "Scheduler Callable can't be null");
        Function<? super Callable<Scheduler>, ? extends Scheduler> f = onInitNewThreadHandler;
        if (f == null) {
            return callRequireNonNull(defaultScheduler);
        }
        return applyRequireNonNull(f, defaultScheduler);
    }

    public static Scheduler initSingleScheduler(Callable<Scheduler> defaultScheduler) {
        ObjectHelper.requireNonNull(defaultScheduler, "Scheduler Callable can't be null");
        Function<? super Callable<Scheduler>, ? extends Scheduler> f = onInitSingleHandler;
        if (f == null) {
            return callRequireNonNull(defaultScheduler);
        }
        return applyRequireNonNull(f, defaultScheduler);
    }

    public static Scheduler onComputationScheduler(Scheduler defaultScheduler) {
        Function<? super Scheduler, ? extends Scheduler> f = onComputationHandler;
        if (f == null) {
            return defaultScheduler;
        }
        return (Scheduler) apply(f, defaultScheduler);
    }

    public static void onError(Throwable error) {
        Consumer<? super Throwable> f = errorHandler;
        if (error == null) {
            error = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        } else if (!isBug(error)) {
            error = new UndeliverableException(error);
        }
        if (f != null) {
            try {
                f.accept(error);
                return;
            } catch (Throwable e) {
                e.printStackTrace();
                uncaught(e);
            }
        }
        error.printStackTrace();
        uncaught(error);
    }

    static boolean isBug(Throwable error) {
        if (!(error instanceof OnErrorNotImplementedException) && !(error instanceof MissingBackpressureException) && !(error instanceof IllegalStateException) && !(error instanceof NullPointerException) && !(error instanceof IllegalArgumentException) && !(error instanceof CompositeException)) {
            return false;
        }
        return true;
    }

    static void uncaught(Throwable error) {
        Thread currentThread = Thread.currentThread();
        currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, error);
    }

    public static Scheduler onIoScheduler(Scheduler defaultScheduler) {
        Function<? super Scheduler, ? extends Scheduler> f = onIoHandler;
        if (f == null) {
            return defaultScheduler;
        }
        return (Scheduler) apply(f, defaultScheduler);
    }

    public static Scheduler onNewThreadScheduler(Scheduler defaultScheduler) {
        Function<? super Scheduler, ? extends Scheduler> f = onNewThreadHandler;
        if (f == null) {
            return defaultScheduler;
        }
        return (Scheduler) apply(f, defaultScheduler);
    }

    public static Runnable onSchedule(Runnable run) {
        ObjectHelper.requireNonNull(run, "run is null");
        Function<? super Runnable, ? extends Runnable> f = onScheduleHandler;
        if (f == null) {
            return run;
        }
        return (Runnable) apply(f, run);
    }

    public static Scheduler onSingleScheduler(Scheduler defaultScheduler) {
        Function<? super Scheduler, ? extends Scheduler> f = onSingleHandler;
        if (f == null) {
            return defaultScheduler;
        }
        return (Scheduler) apply(f, defaultScheduler);
    }

    public static void reset() {
        setErrorHandler((Consumer<? super Throwable>) null);
        setScheduleHandler((Function<? super Runnable, ? extends Runnable>) null);
        setComputationSchedulerHandler((Function<? super Scheduler, ? extends Scheduler>) null);
        setInitComputationSchedulerHandler((Function<? super Callable<Scheduler>, ? extends Scheduler>) null);
        setIoSchedulerHandler((Function<? super Scheduler, ? extends Scheduler>) null);
        setInitIoSchedulerHandler((Function<? super Callable<Scheduler>, ? extends Scheduler>) null);
        setSingleSchedulerHandler((Function<? super Scheduler, ? extends Scheduler>) null);
        setInitSingleSchedulerHandler((Function<? super Callable<Scheduler>, ? extends Scheduler>) null);
        setNewThreadSchedulerHandler((Function<? super Scheduler, ? extends Scheduler>) null);
        setInitNewThreadSchedulerHandler((Function<? super Callable<Scheduler>, ? extends Scheduler>) null);
        setOnFlowableAssembly((Function<? super Flowable, ? extends Flowable>) null);
        setOnFlowableSubscribe((BiFunction<? super Flowable, ? super Subscriber, ? extends Subscriber>) null);
        setOnObservableAssembly((Function<? super Observable, ? extends Observable>) null);
        setOnObservableSubscribe((BiFunction<? super Observable, ? super Observer, ? extends Observer>) null);
        setOnSingleAssembly((Function<? super Single, ? extends Single>) null);
        setOnSingleSubscribe((BiFunction<? super Single, ? super SingleObserver, ? extends SingleObserver>) null);
        setOnCompletableAssembly((Function<? super Completable, ? extends Completable>) null);
        setOnCompletableSubscribe((BiFunction<? super Completable, ? super CompletableObserver, ? extends CompletableObserver>) null);
        setOnConnectableFlowableAssembly((Function<? super ConnectableFlowable, ? extends ConnectableFlowable>) null);
        setOnConnectableObservableAssembly((Function<? super ConnectableObservable, ? extends ConnectableObservable>) null);
        setOnMaybeAssembly((Function<? super Maybe, ? extends Maybe>) null);
        setOnMaybeSubscribe((BiFunction<? super Maybe, MaybeObserver, ? extends MaybeObserver>) null);
        setOnParallelAssembly((Function<? super ParallelFlowable, ? extends ParallelFlowable>) null);
        setFailOnNonBlockingScheduler(false);
        setOnBeforeBlocking((BooleanSupplier) null);
    }

    public static void setComputationSchedulerHandler(Function<? super Scheduler, ? extends Scheduler> handler) {
        if (!lockdown) {
            onComputationHandler = handler;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    public static void setErrorHandler(Consumer<? super Throwable> handler) {
        if (!lockdown) {
            errorHandler = handler;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    public static void setInitComputationSchedulerHandler(Function<? super Callable<Scheduler>, ? extends Scheduler> handler) {
        if (!lockdown) {
            onInitComputationHandler = handler;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    public static void setInitIoSchedulerHandler(Function<? super Callable<Scheduler>, ? extends Scheduler> handler) {
        if (!lockdown) {
            onInitIoHandler = handler;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    public static void setInitNewThreadSchedulerHandler(Function<? super Callable<Scheduler>, ? extends Scheduler> handler) {
        if (!lockdown) {
            onInitNewThreadHandler = handler;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    public static void setInitSingleSchedulerHandler(Function<? super Callable<Scheduler>, ? extends Scheduler> handler) {
        if (!lockdown) {
            onInitSingleHandler = handler;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    public static void setIoSchedulerHandler(Function<? super Scheduler, ? extends Scheduler> handler) {
        if (!lockdown) {
            onIoHandler = handler;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    public static void setNewThreadSchedulerHandler(Function<? super Scheduler, ? extends Scheduler> handler) {
        if (!lockdown) {
            onNewThreadHandler = handler;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    public static void setScheduleHandler(Function<? super Runnable, ? extends Runnable> handler) {
        if (!lockdown) {
            onScheduleHandler = handler;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    public static void setSingleSchedulerHandler(Function<? super Scheduler, ? extends Scheduler> handler) {
        if (!lockdown) {
            onSingleHandler = handler;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    static void unlock() {
        lockdown = false;
    }

    public static Function<? super Completable, ? extends Completable> getOnCompletableAssembly() {
        return onCompletableAssembly;
    }

    public static BiFunction<? super Completable, ? super CompletableObserver, ? extends CompletableObserver> getOnCompletableSubscribe() {
        return onCompletableSubscribe;
    }

    public static Function<? super Flowable, ? extends Flowable> getOnFlowableAssembly() {
        return onFlowableAssembly;
    }

    public static Function<? super ConnectableFlowable, ? extends ConnectableFlowable> getOnConnectableFlowableAssembly() {
        return onConnectableFlowableAssembly;
    }

    public static BiFunction<? super Flowable, ? super Subscriber, ? extends Subscriber> getOnFlowableSubscribe() {
        return onFlowableSubscribe;
    }

    public static BiFunction<? super Maybe, ? super MaybeObserver, ? extends MaybeObserver> getOnMaybeSubscribe() {
        return onMaybeSubscribe;
    }

    public static Function<? super Maybe, ? extends Maybe> getOnMaybeAssembly() {
        return onMaybeAssembly;
    }

    public static Function<? super Single, ? extends Single> getOnSingleAssembly() {
        return onSingleAssembly;
    }

    public static BiFunction<? super Single, ? super SingleObserver, ? extends SingleObserver> getOnSingleSubscribe() {
        return onSingleSubscribe;
    }

    public static Function<? super Observable, ? extends Observable> getOnObservableAssembly() {
        return onObservableAssembly;
    }

    public static Function<? super ConnectableObservable, ? extends ConnectableObservable> getOnConnectableObservableAssembly() {
        return onConnectableObservableAssembly;
    }

    public static BiFunction<? super Observable, ? super Observer, ? extends Observer> getOnObservableSubscribe() {
        return onObservableSubscribe;
    }

    public static void setOnCompletableAssembly(Function<? super Completable, ? extends Completable> onCompletableAssembly2) {
        if (!lockdown) {
            onCompletableAssembly = onCompletableAssembly2;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    public static void setOnCompletableSubscribe(BiFunction<? super Completable, ? super CompletableObserver, ? extends CompletableObserver> onCompletableSubscribe2) {
        if (!lockdown) {
            onCompletableSubscribe = onCompletableSubscribe2;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    public static void setOnFlowableAssembly(Function<? super Flowable, ? extends Flowable> onFlowableAssembly2) {
        if (!lockdown) {
            onFlowableAssembly = onFlowableAssembly2;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    public static void setOnMaybeAssembly(Function<? super Maybe, ? extends Maybe> onMaybeAssembly2) {
        if (!lockdown) {
            onMaybeAssembly = onMaybeAssembly2;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    public static void setOnConnectableFlowableAssembly(Function<? super ConnectableFlowable, ? extends ConnectableFlowable> onConnectableFlowableAssembly2) {
        if (!lockdown) {
            onConnectableFlowableAssembly = onConnectableFlowableAssembly2;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    public static void setOnFlowableSubscribe(BiFunction<? super Flowable, ? super Subscriber, ? extends Subscriber> onFlowableSubscribe2) {
        if (!lockdown) {
            onFlowableSubscribe = onFlowableSubscribe2;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    public static void setOnMaybeSubscribe(BiFunction<? super Maybe, MaybeObserver, ? extends MaybeObserver> onMaybeSubscribe2) {
        if (!lockdown) {
            onMaybeSubscribe = onMaybeSubscribe2;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    public static void setOnObservableAssembly(Function<? super Observable, ? extends Observable> onObservableAssembly2) {
        if (!lockdown) {
            onObservableAssembly = onObservableAssembly2;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    public static void setOnConnectableObservableAssembly(Function<? super ConnectableObservable, ? extends ConnectableObservable> onConnectableObservableAssembly2) {
        if (!lockdown) {
            onConnectableObservableAssembly = onConnectableObservableAssembly2;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    public static void setOnObservableSubscribe(BiFunction<? super Observable, ? super Observer, ? extends Observer> onObservableSubscribe2) {
        if (!lockdown) {
            onObservableSubscribe = onObservableSubscribe2;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    public static void setOnSingleAssembly(Function<? super Single, ? extends Single> onSingleAssembly2) {
        if (!lockdown) {
            onSingleAssembly = onSingleAssembly2;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    public static void setOnSingleSubscribe(BiFunction<? super Single, ? super SingleObserver, ? extends SingleObserver> onSingleSubscribe2) {
        if (!lockdown) {
            onSingleSubscribe = onSingleSubscribe2;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    public static <T> Subscriber<? super T> onSubscribe(Flowable<T> source, Subscriber<? super T> subscriber) {
        BiFunction<? super Flowable, ? super Subscriber, ? extends Subscriber> f = onFlowableSubscribe;
        if (f != null) {
            return (Subscriber) apply(f, source, subscriber);
        }
        return subscriber;
    }

    public static <T> Observer<? super T> onSubscribe(Observable<T> source, Observer<? super T> observer) {
        BiFunction<? super Observable, ? super Observer, ? extends Observer> f = onObservableSubscribe;
        if (f != null) {
            return (Observer) apply(f, source, observer);
        }
        return observer;
    }

    public static <T> SingleObserver<? super T> onSubscribe(Single<T> source, SingleObserver<? super T> observer) {
        BiFunction<? super Single, ? super SingleObserver, ? extends SingleObserver> f = onSingleSubscribe;
        if (f != null) {
            return (SingleObserver) apply(f, source, observer);
        }
        return observer;
    }

    public static CompletableObserver onSubscribe(Completable source, CompletableObserver observer) {
        BiFunction<? super Completable, ? super CompletableObserver, ? extends CompletableObserver> f = onCompletableSubscribe;
        if (f != null) {
            return (CompletableObserver) apply(f, source, observer);
        }
        return observer;
    }

    public static <T> MaybeObserver<? super T> onSubscribe(Maybe<T> source, MaybeObserver<? super T> observer) {
        BiFunction<? super Maybe, ? super MaybeObserver, ? extends MaybeObserver> f = onMaybeSubscribe;
        if (f != null) {
            return (MaybeObserver) apply(f, source, observer);
        }
        return observer;
    }

    public static <T> Maybe<T> onAssembly(Maybe<T> source) {
        Function<? super Maybe, ? extends Maybe> f = onMaybeAssembly;
        if (f != null) {
            return (Maybe) apply(f, source);
        }
        return source;
    }

    public static <T> Flowable<T> onAssembly(Flowable<T> source) {
        Function<? super Flowable, ? extends Flowable> f = onFlowableAssembly;
        if (f != null) {
            return (Flowable) apply(f, source);
        }
        return source;
    }

    public static <T> ConnectableFlowable<T> onAssembly(ConnectableFlowable<T> source) {
        Function<? super ConnectableFlowable, ? extends ConnectableFlowable> f = onConnectableFlowableAssembly;
        if (f != null) {
            return (ConnectableFlowable) apply(f, source);
        }
        return source;
    }

    public static <T> Observable<T> onAssembly(Observable<T> source) {
        Function<? super Observable, ? extends Observable> f = onObservableAssembly;
        if (f != null) {
            return (Observable) apply(f, source);
        }
        return source;
    }

    public static <T> ConnectableObservable<T> onAssembly(ConnectableObservable<T> source) {
        Function<? super ConnectableObservable, ? extends ConnectableObservable> f = onConnectableObservableAssembly;
        if (f != null) {
            return (ConnectableObservable) apply(f, source);
        }
        return source;
    }

    public static <T> Single<T> onAssembly(Single<T> source) {
        Function<? super Single, ? extends Single> f = onSingleAssembly;
        if (f != null) {
            return (Single) apply(f, source);
        }
        return source;
    }

    public static Completable onAssembly(Completable source) {
        Function<? super Completable, ? extends Completable> f = onCompletableAssembly;
        if (f != null) {
            return (Completable) apply(f, source);
        }
        return source;
    }

    public static void setOnParallelAssembly(Function<? super ParallelFlowable, ? extends ParallelFlowable> handler) {
        if (!lockdown) {
            onParallelAssembly = handler;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    public static Function<? super ParallelFlowable, ? extends ParallelFlowable> getOnParallelAssembly() {
        return onParallelAssembly;
    }

    public static <T> ParallelFlowable<T> onAssembly(ParallelFlowable<T> source) {
        Function<? super ParallelFlowable, ? extends ParallelFlowable> f = onParallelAssembly;
        if (f != null) {
            return (ParallelFlowable) apply(f, source);
        }
        return source;
    }

    public static boolean onBeforeBlocking() {
        BooleanSupplier f = onBeforeBlocking;
        if (f == null) {
            return false;
        }
        try {
            return f.getAsBoolean();
        } catch (Throwable ex) {
            throw ExceptionHelper.wrapOrThrow(ex);
        }
    }

    public static void setOnBeforeBlocking(BooleanSupplier handler) {
        if (!lockdown) {
            onBeforeBlocking = handler;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    public static BooleanSupplier getOnBeforeBlocking() {
        return onBeforeBlocking;
    }

    public static Scheduler createComputationScheduler(ThreadFactory threadFactory) {
        return new ComputationScheduler((ThreadFactory) ObjectHelper.requireNonNull(threadFactory, "threadFactory is null"));
    }

    public static Scheduler createIoScheduler(ThreadFactory threadFactory) {
        return new IoScheduler((ThreadFactory) ObjectHelper.requireNonNull(threadFactory, "threadFactory is null"));
    }

    public static Scheduler createNewThreadScheduler(ThreadFactory threadFactory) {
        return new NewThreadScheduler((ThreadFactory) ObjectHelper.requireNonNull(threadFactory, "threadFactory is null"));
    }

    public static Scheduler createSingleScheduler(ThreadFactory threadFactory) {
        return new SingleScheduler((ThreadFactory) ObjectHelper.requireNonNull(threadFactory, "threadFactory is null"));
    }

    static <T, R> R apply(Function<T, R> f, T t) {
        try {
            return f.apply(t);
        } catch (Throwable ex) {
            throw ExceptionHelper.wrapOrThrow(ex);
        }
    }

    static <T, U, R> R apply(BiFunction<T, U, R> f, T t, U u) {
        try {
            return f.apply(t, u);
        } catch (Throwable ex) {
            throw ExceptionHelper.wrapOrThrow(ex);
        }
    }

    static Scheduler callRequireNonNull(Callable<Scheduler> s) {
        try {
            return (Scheduler) ObjectHelper.requireNonNull(s.call(), "Scheduler Callable result can't be null");
        } catch (Throwable ex) {
            throw ExceptionHelper.wrapOrThrow(ex);
        }
    }

    static Scheduler applyRequireNonNull(Function<? super Callable<Scheduler>, ? extends Scheduler> f, Callable<Scheduler> s) {
        return (Scheduler) ObjectHelper.requireNonNull(apply(f, s), "Scheduler Callable result can't be null");
    }

    private RxJavaPlugins() {
        throw new IllegalStateException("No instances!");
    }
}
