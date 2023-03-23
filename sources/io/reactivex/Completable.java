package io.reactivex;

import io.reactivex.annotations.BackpressureKind;
import io.reactivex.annotations.BackpressureSupport;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.SchedulerSupport;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiPredicate;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.FuseToFlowable;
import io.reactivex.internal.fuseable.FuseToMaybe;
import io.reactivex.internal.fuseable.FuseToObservable;
import io.reactivex.internal.observers.BlockingMultiObserver;
import io.reactivex.internal.observers.CallbackCompletableObserver;
import io.reactivex.internal.observers.EmptyCompletableObserver;
import io.reactivex.internal.operators.completable.CompletableAmb;
import io.reactivex.internal.operators.completable.CompletableAndThenCompletable;
import io.reactivex.internal.operators.completable.CompletableCache;
import io.reactivex.internal.operators.completable.CompletableConcat;
import io.reactivex.internal.operators.completable.CompletableConcatArray;
import io.reactivex.internal.operators.completable.CompletableConcatIterable;
import io.reactivex.internal.operators.completable.CompletableCreate;
import io.reactivex.internal.operators.completable.CompletableDefer;
import io.reactivex.internal.operators.completable.CompletableDelay;
import io.reactivex.internal.operators.completable.CompletableDetach;
import io.reactivex.internal.operators.completable.CompletableDisposeOn;
import io.reactivex.internal.operators.completable.CompletableDoFinally;
import io.reactivex.internal.operators.completable.CompletableDoOnEvent;
import io.reactivex.internal.operators.completable.CompletableEmpty;
import io.reactivex.internal.operators.completable.CompletableError;
import io.reactivex.internal.operators.completable.CompletableErrorSupplier;
import io.reactivex.internal.operators.completable.CompletableFromAction;
import io.reactivex.internal.operators.completable.CompletableFromCallable;
import io.reactivex.internal.operators.completable.CompletableFromObservable;
import io.reactivex.internal.operators.completable.CompletableFromPublisher;
import io.reactivex.internal.operators.completable.CompletableFromRunnable;
import io.reactivex.internal.operators.completable.CompletableFromSingle;
import io.reactivex.internal.operators.completable.CompletableFromUnsafeSource;
import io.reactivex.internal.operators.completable.CompletableHide;
import io.reactivex.internal.operators.completable.CompletableLift;
import io.reactivex.internal.operators.completable.CompletableMaterialize;
import io.reactivex.internal.operators.completable.CompletableMerge;
import io.reactivex.internal.operators.completable.CompletableMergeArray;
import io.reactivex.internal.operators.completable.CompletableMergeDelayErrorArray;
import io.reactivex.internal.operators.completable.CompletableMergeDelayErrorIterable;
import io.reactivex.internal.operators.completable.CompletableMergeIterable;
import io.reactivex.internal.operators.completable.CompletableNever;
import io.reactivex.internal.operators.completable.CompletableObserveOn;
import io.reactivex.internal.operators.completable.CompletableOnErrorComplete;
import io.reactivex.internal.operators.completable.CompletablePeek;
import io.reactivex.internal.operators.completable.CompletableResumeNext;
import io.reactivex.internal.operators.completable.CompletableSubscribeOn;
import io.reactivex.internal.operators.completable.CompletableTakeUntilCompletable;
import io.reactivex.internal.operators.completable.CompletableTimeout;
import io.reactivex.internal.operators.completable.CompletableTimer;
import io.reactivex.internal.operators.completable.CompletableToFlowable;
import io.reactivex.internal.operators.completable.CompletableToObservable;
import io.reactivex.internal.operators.completable.CompletableToSingle;
import io.reactivex.internal.operators.completable.CompletableUsing;
import io.reactivex.internal.operators.maybe.MaybeDelayWithCompletable;
import io.reactivex.internal.operators.maybe.MaybeFromCompletable;
import io.reactivex.internal.operators.maybe.MaybeIgnoreElementCompletable;
import io.reactivex.internal.operators.mixed.CompletableAndThenObservable;
import io.reactivex.internal.operators.mixed.CompletableAndThenPublisher;
import io.reactivex.internal.operators.single.SingleDelayWithCompletable;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.observers.TestObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Publisher;

public abstract class Completable implements CompletableSource {
    /* access modifiers changed from: protected */
    public abstract void subscribeActual(CompletableObserver completableObserver);

    @CheckReturnValue
    @SchedulerSupport("none")
    public static Completable ambArray(CompletableSource... sources) {
        ObjectHelper.requireNonNull(sources, "sources is null");
        if (sources.length == 0) {
            return complete();
        }
        if (sources.length == 1) {
            return wrap(sources[0]);
        }
        return RxJavaPlugins.onAssembly((Completable) new CompletableAmb(sources, (Iterable<? extends CompletableSource>) null));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static Completable amb(Iterable<? extends CompletableSource> sources) {
        ObjectHelper.requireNonNull(sources, "sources is null");
        return RxJavaPlugins.onAssembly((Completable) new CompletableAmb((CompletableSource[]) null, sources));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static Completable complete() {
        return RxJavaPlugins.onAssembly(CompletableEmpty.INSTANCE);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static Completable concatArray(CompletableSource... sources) {
        ObjectHelper.requireNonNull(sources, "sources is null");
        if (sources.length == 0) {
            return complete();
        }
        if (sources.length == 1) {
            return wrap(sources[0]);
        }
        return RxJavaPlugins.onAssembly((Completable) new CompletableConcatArray(sources));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static Completable concat(Iterable<? extends CompletableSource> sources) {
        ObjectHelper.requireNonNull(sources, "sources is null");
        return RxJavaPlugins.onAssembly((Completable) new CompletableConcatIterable(sources));
    }

    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.FULL)
    @SchedulerSupport("none")
    public static Completable concat(Publisher<? extends CompletableSource> sources) {
        return concat(sources, 2);
    }

    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.FULL)
    @SchedulerSupport("none")
    public static Completable concat(Publisher<? extends CompletableSource> sources, int prefetch) {
        ObjectHelper.requireNonNull(sources, "sources is null");
        ObjectHelper.verifyPositive(prefetch, "prefetch");
        return RxJavaPlugins.onAssembly((Completable) new CompletableConcat(sources, prefetch));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static Completable create(CompletableOnSubscribe source) {
        ObjectHelper.requireNonNull(source, "source is null");
        return RxJavaPlugins.onAssembly((Completable) new CompletableCreate(source));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static Completable unsafeCreate(CompletableSource source) {
        ObjectHelper.requireNonNull(source, "source is null");
        if (!(source instanceof Completable)) {
            return RxJavaPlugins.onAssembly((Completable) new CompletableFromUnsafeSource(source));
        }
        throw new IllegalArgumentException("Use of unsafeCreate(Completable)!");
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static Completable defer(Callable<? extends CompletableSource> completableSupplier) {
        ObjectHelper.requireNonNull(completableSupplier, "completableSupplier");
        return RxJavaPlugins.onAssembly((Completable) new CompletableDefer(completableSupplier));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static Completable error(Callable<? extends Throwable> errorSupplier) {
        ObjectHelper.requireNonNull(errorSupplier, "errorSupplier is null");
        return RxJavaPlugins.onAssembly((Completable) new CompletableErrorSupplier(errorSupplier));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static Completable error(Throwable error) {
        ObjectHelper.requireNonNull(error, "error is null");
        return RxJavaPlugins.onAssembly((Completable) new CompletableError(error));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static Completable fromAction(Action run) {
        ObjectHelper.requireNonNull(run, "run is null");
        return RxJavaPlugins.onAssembly((Completable) new CompletableFromAction(run));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static Completable fromCallable(Callable<?> callable) {
        ObjectHelper.requireNonNull(callable, "callable is null");
        return RxJavaPlugins.onAssembly((Completable) new CompletableFromCallable(callable));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static Completable fromFuture(Future<?> future) {
        ObjectHelper.requireNonNull(future, "future is null");
        return fromAction(Functions.futureAction(future));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Completable fromMaybe(MaybeSource<T> maybe) {
        ObjectHelper.requireNonNull(maybe, "maybe is null");
        return RxJavaPlugins.onAssembly((Completable) new MaybeIgnoreElementCompletable(maybe));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static Completable fromRunnable(Runnable run) {
        ObjectHelper.requireNonNull(run, "run is null");
        return RxJavaPlugins.onAssembly((Completable) new CompletableFromRunnable(run));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Completable fromObservable(ObservableSource<T> observable) {
        ObjectHelper.requireNonNull(observable, "observable is null");
        return RxJavaPlugins.onAssembly((Completable) new CompletableFromObservable(observable));
    }

    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.UNBOUNDED_IN)
    @SchedulerSupport("none")
    public static <T> Completable fromPublisher(Publisher<T> publisher) {
        ObjectHelper.requireNonNull(publisher, "publisher is null");
        return RxJavaPlugins.onAssembly((Completable) new CompletableFromPublisher(publisher));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Completable fromSingle(SingleSource<T> single) {
        ObjectHelper.requireNonNull(single, "single is null");
        return RxJavaPlugins.onAssembly((Completable) new CompletableFromSingle(single));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static Completable mergeArray(CompletableSource... sources) {
        ObjectHelper.requireNonNull(sources, "sources is null");
        if (sources.length == 0) {
            return complete();
        }
        if (sources.length == 1) {
            return wrap(sources[0]);
        }
        return RxJavaPlugins.onAssembly((Completable) new CompletableMergeArray(sources));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static Completable merge(Iterable<? extends CompletableSource> sources) {
        ObjectHelper.requireNonNull(sources, "sources is null");
        return RxJavaPlugins.onAssembly((Completable) new CompletableMergeIterable(sources));
    }

    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.UNBOUNDED_IN)
    @SchedulerSupport("none")
    public static Completable merge(Publisher<? extends CompletableSource> sources) {
        return merge0(sources, Integer.MAX_VALUE, false);
    }

    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.FULL)
    @SchedulerSupport("none")
    public static Completable merge(Publisher<? extends CompletableSource> sources, int maxConcurrency) {
        return merge0(sources, maxConcurrency, false);
    }

    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.FULL)
    @SchedulerSupport("none")
    private static Completable merge0(Publisher<? extends CompletableSource> sources, int maxConcurrency, boolean delayErrors) {
        ObjectHelper.requireNonNull(sources, "sources is null");
        ObjectHelper.verifyPositive(maxConcurrency, "maxConcurrency");
        return RxJavaPlugins.onAssembly((Completable) new CompletableMerge(sources, maxConcurrency, delayErrors));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static Completable mergeArrayDelayError(CompletableSource... sources) {
        ObjectHelper.requireNonNull(sources, "sources is null");
        return RxJavaPlugins.onAssembly((Completable) new CompletableMergeDelayErrorArray(sources));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static Completable mergeDelayError(Iterable<? extends CompletableSource> sources) {
        ObjectHelper.requireNonNull(sources, "sources is null");
        return RxJavaPlugins.onAssembly((Completable) new CompletableMergeDelayErrorIterable(sources));
    }

    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.UNBOUNDED_IN)
    @SchedulerSupport("none")
    public static Completable mergeDelayError(Publisher<? extends CompletableSource> sources) {
        return merge0(sources, Integer.MAX_VALUE, true);
    }

    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.FULL)
    @SchedulerSupport("none")
    public static Completable mergeDelayError(Publisher<? extends CompletableSource> sources, int maxConcurrency) {
        return merge0(sources, maxConcurrency, true);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static Completable never() {
        return RxJavaPlugins.onAssembly(CompletableNever.INSTANCE);
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public static Completable timer(long delay, TimeUnit unit) {
        return timer(delay, unit, Schedulers.computation());
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public static Completable timer(long delay, TimeUnit unit, Scheduler scheduler) {
        ObjectHelper.requireNonNull(unit, "unit is null");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly((Completable) new CompletableTimer(delay, unit, scheduler));
    }

    private static NullPointerException toNpe(Throwable ex) {
        NullPointerException npe = new NullPointerException("Actually not, but can't pass out an exception otherwise...");
        npe.initCause(ex);
        return npe;
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <R> Completable using(Callable<R> resourceSupplier, Function<? super R, ? extends CompletableSource> completableFunction, Consumer<? super R> disposer) {
        return using(resourceSupplier, completableFunction, disposer, true);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <R> Completable using(Callable<R> resourceSupplier, Function<? super R, ? extends CompletableSource> completableFunction, Consumer<? super R> disposer, boolean eager) {
        ObjectHelper.requireNonNull(resourceSupplier, "resourceSupplier is null");
        ObjectHelper.requireNonNull(completableFunction, "completableFunction is null");
        ObjectHelper.requireNonNull(disposer, "disposer is null");
        return RxJavaPlugins.onAssembly((Completable) new CompletableUsing(resourceSupplier, completableFunction, disposer, eager));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static Completable wrap(CompletableSource source) {
        ObjectHelper.requireNonNull(source, "source is null");
        if (source instanceof Completable) {
            return RxJavaPlugins.onAssembly((Completable) source);
        }
        return RxJavaPlugins.onAssembly((Completable) new CompletableFromUnsafeSource(source));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable ambWith(CompletableSource other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return ambArray(this, other);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <T> Observable<T> andThen(ObservableSource<T> next) {
        ObjectHelper.requireNonNull(next, "next is null");
        return RxJavaPlugins.onAssembly(new CompletableAndThenObservable(this, next));
    }

    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.FULL)
    @SchedulerSupport("none")
    public final <T> Flowable<T> andThen(Publisher<T> next) {
        ObjectHelper.requireNonNull(next, "next is null");
        return RxJavaPlugins.onAssembly(new CompletableAndThenPublisher(this, next));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <T> Single<T> andThen(SingleSource<T> next) {
        ObjectHelper.requireNonNull(next, "next is null");
        return RxJavaPlugins.onAssembly(new SingleDelayWithCompletable(next, this));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <T> Maybe<T> andThen(MaybeSource<T> next) {
        ObjectHelper.requireNonNull(next, "next is null");
        return RxJavaPlugins.onAssembly(new MaybeDelayWithCompletable(next, this));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable andThen(CompletableSource next) {
        ObjectHelper.requireNonNull(next, "next is null");
        return RxJavaPlugins.onAssembly((Completable) new CompletableAndThenCompletable(this, next));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> R as(CompletableConverter<? extends R> converter) {
        return ((CompletableConverter) ObjectHelper.requireNonNull(converter, "converter is null")).apply(this);
    }

    @SchedulerSupport("none")
    public final void blockingAwait() {
        BlockingMultiObserver<Void> observer = new BlockingMultiObserver<>();
        subscribe((CompletableObserver) observer);
        observer.blockingGet();
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final boolean blockingAwait(long timeout, TimeUnit unit) {
        ObjectHelper.requireNonNull(unit, "unit is null");
        BlockingMultiObserver<Void> observer = new BlockingMultiObserver<>();
        subscribe((CompletableObserver) observer);
        return observer.blockingAwait(timeout, unit);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Throwable blockingGet() {
        BlockingMultiObserver<Void> observer = new BlockingMultiObserver<>();
        subscribe((CompletableObserver) observer);
        return observer.blockingGetError();
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Throwable blockingGet(long timeout, TimeUnit unit) {
        ObjectHelper.requireNonNull(unit, "unit is null");
        BlockingMultiObserver<Void> observer = new BlockingMultiObserver<>();
        subscribe((CompletableObserver) observer);
        return observer.blockingGetError(timeout, unit);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable cache() {
        return RxJavaPlugins.onAssembly((Completable) new CompletableCache(this));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable compose(CompletableTransformer transformer) {
        return wrap(((CompletableTransformer) ObjectHelper.requireNonNull(transformer, "transformer is null")).apply(this));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable concatWith(CompletableSource other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return RxJavaPlugins.onAssembly((Completable) new CompletableAndThenCompletable(this, other));
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public final Completable delay(long delay, TimeUnit unit) {
        return delay(delay, unit, Schedulers.computation(), false);
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Completable delay(long delay, TimeUnit unit, Scheduler scheduler) {
        return delay(delay, unit, scheduler, false);
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Completable delay(long delay, TimeUnit unit, Scheduler scheduler, boolean delayError) {
        ObjectHelper.requireNonNull(unit, "unit is null");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly((Completable) new CompletableDelay(this, delay, unit, scheduler, delayError));
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public final Completable delaySubscription(long delay, TimeUnit unit) {
        return delaySubscription(delay, unit, Schedulers.computation());
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Completable delaySubscription(long delay, TimeUnit unit, Scheduler scheduler) {
        return timer(delay, unit, scheduler).andThen((CompletableSource) this);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable doOnComplete(Action onComplete) {
        return doOnLifecycle(Functions.emptyConsumer(), Functions.emptyConsumer(), onComplete, Functions.EMPTY_ACTION, Functions.EMPTY_ACTION, Functions.EMPTY_ACTION);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable doOnDispose(Action onDispose) {
        return doOnLifecycle(Functions.emptyConsumer(), Functions.emptyConsumer(), Functions.EMPTY_ACTION, Functions.EMPTY_ACTION, Functions.EMPTY_ACTION, onDispose);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable doOnError(Consumer<? super Throwable> onError) {
        return doOnLifecycle(Functions.emptyConsumer(), onError, Functions.EMPTY_ACTION, Functions.EMPTY_ACTION, Functions.EMPTY_ACTION, Functions.EMPTY_ACTION);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable doOnEvent(Consumer<? super Throwable> onEvent) {
        ObjectHelper.requireNonNull(onEvent, "onEvent is null");
        return RxJavaPlugins.onAssembly((Completable) new CompletableDoOnEvent(this, onEvent));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    private Completable doOnLifecycle(Consumer<? super Disposable> onSubscribe, Consumer<? super Throwable> onError, Action onComplete, Action onTerminate, Action onAfterTerminate, Action onDispose) {
        ObjectHelper.requireNonNull(onSubscribe, "onSubscribe is null");
        ObjectHelper.requireNonNull(onError, "onError is null");
        ObjectHelper.requireNonNull(onComplete, "onComplete is null");
        ObjectHelper.requireNonNull(onTerminate, "onTerminate is null");
        ObjectHelper.requireNonNull(onAfterTerminate, "onAfterTerminate is null");
        ObjectHelper.requireNonNull(onDispose, "onDispose is null");
        return RxJavaPlugins.onAssembly((Completable) new CompletablePeek(this, onSubscribe, onError, onComplete, onTerminate, onAfterTerminate, onDispose));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable doOnSubscribe(Consumer<? super Disposable> onSubscribe) {
        return doOnLifecycle(onSubscribe, Functions.emptyConsumer(), Functions.EMPTY_ACTION, Functions.EMPTY_ACTION, Functions.EMPTY_ACTION, Functions.EMPTY_ACTION);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable doOnTerminate(Action onTerminate) {
        return doOnLifecycle(Functions.emptyConsumer(), Functions.emptyConsumer(), Functions.EMPTY_ACTION, onTerminate, Functions.EMPTY_ACTION, Functions.EMPTY_ACTION);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable doAfterTerminate(Action onAfterTerminate) {
        return doOnLifecycle(Functions.emptyConsumer(), Functions.emptyConsumer(), Functions.EMPTY_ACTION, Functions.EMPTY_ACTION, onAfterTerminate, Functions.EMPTY_ACTION);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable doFinally(Action onFinally) {
        ObjectHelper.requireNonNull(onFinally, "onFinally is null");
        return RxJavaPlugins.onAssembly((Completable) new CompletableDoFinally(this, onFinally));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable lift(CompletableOperator onLift) {
        ObjectHelper.requireNonNull(onLift, "onLift is null");
        return RxJavaPlugins.onAssembly((Completable) new CompletableLift(this, onLift));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <T> Single<Notification<T>> materialize() {
        return RxJavaPlugins.onAssembly(new CompletableMaterialize(this));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable mergeWith(CompletableSource other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return mergeArray(this, other);
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Completable observeOn(Scheduler scheduler) {
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly((Completable) new CompletableObserveOn(this, scheduler));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable onErrorComplete() {
        return onErrorComplete(Functions.alwaysTrue());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable onErrorComplete(Predicate<? super Throwable> predicate) {
        ObjectHelper.requireNonNull(predicate, "predicate is null");
        return RxJavaPlugins.onAssembly((Completable) new CompletableOnErrorComplete(this, predicate));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable onErrorResumeNext(Function<? super Throwable, ? extends CompletableSource> errorMapper) {
        ObjectHelper.requireNonNull(errorMapper, "errorMapper is null");
        return RxJavaPlugins.onAssembly((Completable) new CompletableResumeNext(this, errorMapper));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable onTerminateDetach() {
        return RxJavaPlugins.onAssembly((Completable) new CompletableDetach(this));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable repeat() {
        return fromPublisher(toFlowable().repeat());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable repeat(long times) {
        return fromPublisher(toFlowable().repeat(times));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable repeatUntil(BooleanSupplier stop) {
        return fromPublisher(toFlowable().repeatUntil(stop));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable repeatWhen(Function<? super Flowable<Object>, ? extends Publisher<?>> handler) {
        return fromPublisher(toFlowable().repeatWhen(handler));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable retry() {
        return fromPublisher(toFlowable().retry());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable retry(BiPredicate<? super Integer, ? super Throwable> predicate) {
        return fromPublisher(toFlowable().retry(predicate));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable retry(long times) {
        return fromPublisher(toFlowable().retry(times));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable retry(long times, Predicate<? super Throwable> predicate) {
        return fromPublisher(toFlowable().retry(times, predicate));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable retry(Predicate<? super Throwable> predicate) {
        return fromPublisher(toFlowable().retry(predicate));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable retryWhen(Function<? super Flowable<Throwable>, ? extends Publisher<?>> handler) {
        return fromPublisher(toFlowable().retryWhen(handler));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable startWith(CompletableSource other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return concatArray(other, this);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <T> Observable<T> startWith(Observable<T> other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return other.concatWith((ObservableSource<? extends T>) toObservable());
    }

    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.FULL)
    @SchedulerSupport("none")
    public final <T> Flowable<T> startWith(Publisher<T> other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return toFlowable().startWith(other);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable hide() {
        return RxJavaPlugins.onAssembly((Completable) new CompletableHide(this));
    }

    @SchedulerSupport("none")
    public final Disposable subscribe() {
        EmptyCompletableObserver observer = new EmptyCompletableObserver();
        subscribe((CompletableObserver) observer);
        return observer;
    }

    @SchedulerSupport("none")
    public final void subscribe(CompletableObserver observer) {
        ObjectHelper.requireNonNull(observer, "observer is null");
        try {
            CompletableObserver observer2 = RxJavaPlugins.onSubscribe(this, observer);
            ObjectHelper.requireNonNull(observer2, "The RxJavaPlugins.onSubscribe hook returned a null CompletableObserver. Please check the handler provided to RxJavaPlugins.setOnCompletableSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
            subscribeActual(observer2);
        } catch (NullPointerException ex) {
            throw ex;
        } catch (Throwable ex2) {
            Exceptions.throwIfFatal(ex2);
            RxJavaPlugins.onError(ex2);
            throw toNpe(ex2);
        }
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <E extends CompletableObserver> E subscribeWith(E observer) {
        subscribe((CompletableObserver) observer);
        return observer;
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Disposable subscribe(Action onComplete, Consumer<? super Throwable> onError) {
        ObjectHelper.requireNonNull(onError, "onError is null");
        ObjectHelper.requireNonNull(onComplete, "onComplete is null");
        CallbackCompletableObserver observer = new CallbackCompletableObserver(onError, onComplete);
        subscribe((CompletableObserver) observer);
        return observer;
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Disposable subscribe(Action onComplete) {
        ObjectHelper.requireNonNull(onComplete, "onComplete is null");
        CallbackCompletableObserver observer = new CallbackCompletableObserver(onComplete);
        subscribe((CompletableObserver) observer);
        return observer;
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Completable subscribeOn(Scheduler scheduler) {
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly((Completable) new CompletableSubscribeOn(this, scheduler));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable takeUntil(CompletableSource other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return RxJavaPlugins.onAssembly((Completable) new CompletableTakeUntilCompletable(this, other));
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public final Completable timeout(long timeout, TimeUnit unit) {
        return timeout0(timeout, unit, Schedulers.computation(), (CompletableSource) null);
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public final Completable timeout(long timeout, TimeUnit unit, CompletableSource other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return timeout0(timeout, unit, Schedulers.computation(), other);
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Completable timeout(long timeout, TimeUnit unit, Scheduler scheduler) {
        return timeout0(timeout, unit, scheduler, (CompletableSource) null);
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Completable timeout(long timeout, TimeUnit unit, Scheduler scheduler, CompletableSource other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return timeout0(timeout, unit, scheduler, other);
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    private Completable timeout0(long timeout, TimeUnit unit, Scheduler scheduler, CompletableSource other) {
        ObjectHelper.requireNonNull(unit, "unit is null");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly((Completable) new CompletableTimeout(this, timeout, unit, scheduler, other));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <U> U to(Function<? super Completable, U> converter) {
        try {
            return ((Function) ObjectHelper.requireNonNull(converter, "converter is null")).apply(this);
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            throw ExceptionHelper.wrapOrThrow(ex);
        }
    }

    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.FULL)
    @SchedulerSupport("none")
    public final <T> Flowable<T> toFlowable() {
        if (this instanceof FuseToFlowable) {
            return ((FuseToFlowable) this).fuseToFlowable();
        }
        return RxJavaPlugins.onAssembly(new CompletableToFlowable(this));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <T> Maybe<T> toMaybe() {
        if (this instanceof FuseToMaybe) {
            return ((FuseToMaybe) this).fuseToMaybe();
        }
        return RxJavaPlugins.onAssembly(new MaybeFromCompletable(this));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <T> Observable<T> toObservable() {
        if (this instanceof FuseToObservable) {
            return ((FuseToObservable) this).fuseToObservable();
        }
        return RxJavaPlugins.onAssembly(new CompletableToObservable(this));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <T> Single<T> toSingle(Callable<? extends T> completionValueSupplier) {
        ObjectHelper.requireNonNull(completionValueSupplier, "completionValueSupplier is null");
        return RxJavaPlugins.onAssembly(new CompletableToSingle(this, completionValueSupplier, null));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <T> Single<T> toSingleDefault(T completionValue) {
        ObjectHelper.requireNonNull(completionValue, "completionValue is null");
        return RxJavaPlugins.onAssembly(new CompletableToSingle(this, (Callable) null, completionValue));
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Completable unsubscribeOn(Scheduler scheduler) {
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly((Completable) new CompletableDisposeOn(this, scheduler));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final TestObserver<Void> test() {
        TestObserver<Void> to = new TestObserver<>();
        subscribe((CompletableObserver) to);
        return to;
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final TestObserver<Void> test(boolean cancelled) {
        TestObserver<Void> to = new TestObserver<>();
        if (cancelled) {
            to.cancel();
        }
        subscribe((CompletableObserver) to);
        return to;
    }
}
