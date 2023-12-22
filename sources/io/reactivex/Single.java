package io.reactivex;

import io.reactivex.annotations.BackpressureKind;
import io.reactivex.annotations.BackpressureSupport;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.SchedulerSupport;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.BiPredicate;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Function4;
import io.reactivex.functions.Function5;
import io.reactivex.functions.Function6;
import io.reactivex.functions.Function7;
import io.reactivex.functions.Function8;
import io.reactivex.functions.Function9;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.FuseToFlowable;
import io.reactivex.internal.fuseable.FuseToMaybe;
import io.reactivex.internal.fuseable.FuseToObservable;
import io.reactivex.internal.observers.BiConsumerSingleObserver;
import io.reactivex.internal.observers.BlockingMultiObserver;
import io.reactivex.internal.observers.ConsumerSingleObserver;
import io.reactivex.internal.observers.FutureSingleObserver;
import io.reactivex.internal.operators.completable.CompletableFromSingle;
import io.reactivex.internal.operators.completable.CompletableToFlowable;
import io.reactivex.internal.operators.flowable.FlowableConcatMap;
import io.reactivex.internal.operators.flowable.FlowableConcatMapPublisher;
import io.reactivex.internal.operators.flowable.FlowableFlatMapPublisher;
import io.reactivex.internal.operators.flowable.FlowableSingleSingle;
import io.reactivex.internal.operators.maybe.MaybeFilterSingle;
import io.reactivex.internal.operators.maybe.MaybeFromSingle;
import io.reactivex.internal.operators.mixed.SingleFlatMapObservable;
import io.reactivex.internal.operators.observable.ObservableConcatMap;
import io.reactivex.internal.operators.observable.ObservableSingleSingle;
import io.reactivex.internal.operators.single.SingleAmb;
import io.reactivex.internal.operators.single.SingleCache;
import io.reactivex.internal.operators.single.SingleContains;
import io.reactivex.internal.operators.single.SingleCreate;
import io.reactivex.internal.operators.single.SingleDefer;
import io.reactivex.internal.operators.single.SingleDelay;
import io.reactivex.internal.operators.single.SingleDelayWithCompletable;
import io.reactivex.internal.operators.single.SingleDelayWithObservable;
import io.reactivex.internal.operators.single.SingleDelayWithPublisher;
import io.reactivex.internal.operators.single.SingleDelayWithSingle;
import io.reactivex.internal.operators.single.SingleDematerialize;
import io.reactivex.internal.operators.single.SingleDetach;
import io.reactivex.internal.operators.single.SingleDoAfterSuccess;
import io.reactivex.internal.operators.single.SingleDoAfterTerminate;
import io.reactivex.internal.operators.single.SingleDoFinally;
import io.reactivex.internal.operators.single.SingleDoOnDispose;
import io.reactivex.internal.operators.single.SingleDoOnError;
import io.reactivex.internal.operators.single.SingleDoOnEvent;
import io.reactivex.internal.operators.single.SingleDoOnSubscribe;
import io.reactivex.internal.operators.single.SingleDoOnSuccess;
import io.reactivex.internal.operators.single.SingleDoOnTerminate;
import io.reactivex.internal.operators.single.SingleEquals;
import io.reactivex.internal.operators.single.SingleError;
import io.reactivex.internal.operators.single.SingleFlatMap;
import io.reactivex.internal.operators.single.SingleFlatMapCompletable;
import io.reactivex.internal.operators.single.SingleFlatMapIterableFlowable;
import io.reactivex.internal.operators.single.SingleFlatMapIterableObservable;
import io.reactivex.internal.operators.single.SingleFlatMapMaybe;
import io.reactivex.internal.operators.single.SingleFlatMapPublisher;
import io.reactivex.internal.operators.single.SingleFromCallable;
import io.reactivex.internal.operators.single.SingleFromPublisher;
import io.reactivex.internal.operators.single.SingleFromUnsafeSource;
import io.reactivex.internal.operators.single.SingleHide;
import io.reactivex.internal.operators.single.SingleInternalHelper;
import io.reactivex.internal.operators.single.SingleJust;
import io.reactivex.internal.operators.single.SingleLift;
import io.reactivex.internal.operators.single.SingleMap;
import io.reactivex.internal.operators.single.SingleMaterialize;
import io.reactivex.internal.operators.single.SingleNever;
import io.reactivex.internal.operators.single.SingleObserveOn;
import io.reactivex.internal.operators.single.SingleOnErrorReturn;
import io.reactivex.internal.operators.single.SingleResumeNext;
import io.reactivex.internal.operators.single.SingleSubscribeOn;
import io.reactivex.internal.operators.single.SingleTakeUntil;
import io.reactivex.internal.operators.single.SingleTimeout;
import io.reactivex.internal.operators.single.SingleTimer;
import io.reactivex.internal.operators.single.SingleToFlowable;
import io.reactivex.internal.operators.single.SingleToObservable;
import io.reactivex.internal.operators.single.SingleUnsubscribeOn;
import io.reactivex.internal.operators.single.SingleUsing;
import io.reactivex.internal.operators.single.SingleZipArray;
import io.reactivex.internal.operators.single.SingleZipIterable;
import io.reactivex.internal.util.ErrorMode;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.observers.TestObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import java.util.NoSuchElementException;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Publisher;

/* loaded from: classes.dex */
public abstract class Single<T> implements SingleSource<T> {
    protected abstract void subscribeActual(SingleObserver<? super T> singleObserver);

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static <T> Single<T> amb(Iterable<? extends SingleSource<? extends T>> sources) {
        ObjectHelper.requireNonNull(sources, "sources is null");
        return RxJavaPlugins.onAssembly(new SingleAmb(null, sources));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static <T> Single<T> ambArray(SingleSource<? extends T>... sources) {
        if (sources.length == 0) {
            return error(SingleInternalHelper.emptyThrower());
        }
        if (sources.length == 1) {
            return wrap(sources[0]);
        }
        return RxJavaPlugins.onAssembly(new SingleAmb(sources, null));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static <T> Flowable<T> concat(Iterable<? extends SingleSource<? extends T>> sources) {
        return concat(Flowable.fromIterable(sources));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static <T> Observable<T> concat(ObservableSource<? extends SingleSource<? extends T>> sources) {
        ObjectHelper.requireNonNull(sources, "sources is null");
        return RxJavaPlugins.onAssembly(new ObservableConcatMap(sources, SingleInternalHelper.toObservable(), 2, ErrorMode.IMMEDIATE));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static <T> Flowable<T> concat(Publisher<? extends SingleSource<? extends T>> sources) {
        return concat(sources, 2);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static <T> Flowable<T> concat(Publisher<? extends SingleSource<? extends T>> sources, int prefetch) {
        ObjectHelper.requireNonNull(sources, "sources is null");
        ObjectHelper.verifyPositive(prefetch, "prefetch");
        return RxJavaPlugins.onAssembly(new FlowableConcatMapPublisher(sources, SingleInternalHelper.toFlowable(), prefetch, ErrorMode.IMMEDIATE));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static <T> Flowable<T> concat(SingleSource<? extends T> source1, SingleSource<? extends T> source2) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        return concat(Flowable.fromArray(source1, source2));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static <T> Flowable<T> concat(SingleSource<? extends T> source1, SingleSource<? extends T> source2, SingleSource<? extends T> source3) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(source3, "source3 is null");
        return concat(Flowable.fromArray(source1, source2, source3));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static <T> Flowable<T> concat(SingleSource<? extends T> source1, SingleSource<? extends T> source2, SingleSource<? extends T> source3, SingleSource<? extends T> source4) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(source3, "source3 is null");
        ObjectHelper.requireNonNull(source4, "source4 is null");
        return concat(Flowable.fromArray(source1, source2, source3, source4));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static <T> Flowable<T> concatArray(SingleSource<? extends T>... sources) {
        return RxJavaPlugins.onAssembly(new FlowableConcatMap(Flowable.fromArray(sources), SingleInternalHelper.toFlowable(), 2, ErrorMode.BOUNDARY));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static <T> Flowable<T> concatArrayEager(SingleSource<? extends T>... sources) {
        return Flowable.fromArray(sources).concatMapEager(SingleInternalHelper.toFlowable());
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static <T> Flowable<T> concatEager(Publisher<? extends SingleSource<? extends T>> sources) {
        return Flowable.fromPublisher(sources).concatMapEager(SingleInternalHelper.toFlowable());
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static <T> Flowable<T> concatEager(Iterable<? extends SingleSource<? extends T>> sources) {
        return Flowable.fromIterable(sources).concatMapEager(SingleInternalHelper.toFlowable());
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static <T> Single<T> create(SingleOnSubscribe<T> source) {
        ObjectHelper.requireNonNull(source, "source is null");
        return RxJavaPlugins.onAssembly(new SingleCreate(source));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static <T> Single<T> defer(Callable<? extends SingleSource<? extends T>> singleSupplier) {
        ObjectHelper.requireNonNull(singleSupplier, "singleSupplier is null");
        return RxJavaPlugins.onAssembly(new SingleDefer(singleSupplier));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static <T> Single<T> error(Callable<? extends Throwable> errorSupplier) {
        ObjectHelper.requireNonNull(errorSupplier, "errorSupplier is null");
        return RxJavaPlugins.onAssembly(new SingleError(errorSupplier));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static <T> Single<T> error(Throwable exception) {
        ObjectHelper.requireNonNull(exception, "exception is null");
        return error(Functions.justCallable(exception));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static <T> Single<T> fromCallable(Callable<? extends T> callable) {
        ObjectHelper.requireNonNull(callable, "callable is null");
        return RxJavaPlugins.onAssembly(new SingleFromCallable(callable));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static <T> Single<T> fromFuture(Future<? extends T> future) {
        return toSingle(Flowable.fromFuture(future));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static <T> Single<T> fromFuture(Future<? extends T> future, long timeout, TimeUnit unit) {
        return toSingle(Flowable.fromFuture(future, timeout, unit));
    }

    @SchedulerSupport(SchedulerSupport.CUSTOM)
    @CheckReturnValue
    public static <T> Single<T> fromFuture(Future<? extends T> future, long timeout, TimeUnit unit, Scheduler scheduler) {
        return toSingle(Flowable.fromFuture(future, timeout, unit, scheduler));
    }

    @SchedulerSupport(SchedulerSupport.CUSTOM)
    @CheckReturnValue
    public static <T> Single<T> fromFuture(Future<? extends T> future, Scheduler scheduler) {
        return toSingle(Flowable.fromFuture(future, scheduler));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    public static <T> Single<T> fromPublisher(Publisher<? extends T> publisher) {
        ObjectHelper.requireNonNull(publisher, "publisher is null");
        return RxJavaPlugins.onAssembly(new SingleFromPublisher(publisher));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static <T> Single<T> fromObservable(ObservableSource<? extends T> observableSource) {
        ObjectHelper.requireNonNull(observableSource, "observableSource is null");
        return RxJavaPlugins.onAssembly(new ObservableSingleSingle(observableSource, null));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static <T> Single<T> just(T item) {
        ObjectHelper.requireNonNull(item, "item is null");
        return RxJavaPlugins.onAssembly(new SingleJust(item));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static <T> Flowable<T> merge(Iterable<? extends SingleSource<? extends T>> sources) {
        return merge(Flowable.fromIterable(sources));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static <T> Flowable<T> merge(Publisher<? extends SingleSource<? extends T>> sources) {
        ObjectHelper.requireNonNull(sources, "sources is null");
        return RxJavaPlugins.onAssembly(new FlowableFlatMapPublisher(sources, SingleInternalHelper.toFlowable(), false, Integer.MAX_VALUE, Flowable.bufferSize()));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static <T> Single<T> merge(SingleSource<? extends SingleSource<? extends T>> source) {
        ObjectHelper.requireNonNull(source, "source is null");
        return RxJavaPlugins.onAssembly(new SingleFlatMap(source, Functions.identity()));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static <T> Flowable<T> merge(SingleSource<? extends T> source1, SingleSource<? extends T> source2) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        return merge(Flowable.fromArray(source1, source2));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static <T> Flowable<T> merge(SingleSource<? extends T> source1, SingleSource<? extends T> source2, SingleSource<? extends T> source3) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(source3, "source3 is null");
        return merge(Flowable.fromArray(source1, source2, source3));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static <T> Flowable<T> merge(SingleSource<? extends T> source1, SingleSource<? extends T> source2, SingleSource<? extends T> source3, SingleSource<? extends T> source4) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(source3, "source3 is null");
        ObjectHelper.requireNonNull(source4, "source4 is null");
        return merge(Flowable.fromArray(source1, source2, source3, source4));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static <T> Flowable<T> mergeDelayError(Iterable<? extends SingleSource<? extends T>> sources) {
        return mergeDelayError(Flowable.fromIterable(sources));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static <T> Flowable<T> mergeDelayError(Publisher<? extends SingleSource<? extends T>> sources) {
        ObjectHelper.requireNonNull(sources, "sources is null");
        return RxJavaPlugins.onAssembly(new FlowableFlatMapPublisher(sources, SingleInternalHelper.toFlowable(), true, Integer.MAX_VALUE, Flowable.bufferSize()));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static <T> Flowable<T> mergeDelayError(SingleSource<? extends T> source1, SingleSource<? extends T> source2) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        return mergeDelayError(Flowable.fromArray(source1, source2));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static <T> Flowable<T> mergeDelayError(SingleSource<? extends T> source1, SingleSource<? extends T> source2, SingleSource<? extends T> source3) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(source3, "source3 is null");
        return mergeDelayError(Flowable.fromArray(source1, source2, source3));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public static <T> Flowable<T> mergeDelayError(SingleSource<? extends T> source1, SingleSource<? extends T> source2, SingleSource<? extends T> source3, SingleSource<? extends T> source4) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(source3, "source3 is null");
        ObjectHelper.requireNonNull(source4, "source4 is null");
        return mergeDelayError(Flowable.fromArray(source1, source2, source3, source4));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static <T> Single<T> never() {
        return RxJavaPlugins.onAssembly(SingleNever.INSTANCE);
    }

    @SchedulerSupport(SchedulerSupport.COMPUTATION)
    @CheckReturnValue
    public static Single<Long> timer(long delay, TimeUnit unit) {
        return timer(delay, unit, Schedulers.computation());
    }

    @SchedulerSupport(SchedulerSupport.CUSTOM)
    @CheckReturnValue
    public static Single<Long> timer(long delay, TimeUnit unit, Scheduler scheduler) {
        ObjectHelper.requireNonNull(unit, "unit is null");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new SingleTimer(delay, unit, scheduler));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static <T> Single<Boolean> equals(SingleSource<? extends T> first, SingleSource<? extends T> second) {
        ObjectHelper.requireNonNull(first, "first is null");
        ObjectHelper.requireNonNull(second, "second is null");
        return RxJavaPlugins.onAssembly(new SingleEquals(first, second));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static <T> Single<T> unsafeCreate(SingleSource<T> onSubscribe) {
        ObjectHelper.requireNonNull(onSubscribe, "onSubscribe is null");
        if (onSubscribe instanceof Single) {
            throw new IllegalArgumentException("unsafeCreate(Single) should be upgraded");
        }
        return RxJavaPlugins.onAssembly(new SingleFromUnsafeSource(onSubscribe));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static <T, U> Single<T> using(Callable<U> resourceSupplier, Function<? super U, ? extends SingleSource<? extends T>> singleFunction, Consumer<? super U> disposer) {
        return using(resourceSupplier, singleFunction, disposer, true);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static <T, U> Single<T> using(Callable<U> resourceSupplier, Function<? super U, ? extends SingleSource<? extends T>> singleFunction, Consumer<? super U> disposer, boolean eager) {
        ObjectHelper.requireNonNull(resourceSupplier, "resourceSupplier is null");
        ObjectHelper.requireNonNull(singleFunction, "singleFunction is null");
        ObjectHelper.requireNonNull(disposer, "disposer is null");
        return RxJavaPlugins.onAssembly(new SingleUsing(resourceSupplier, singleFunction, disposer, eager));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static <T> Single<T> wrap(SingleSource<T> source) {
        ObjectHelper.requireNonNull(source, "source is null");
        if (source instanceof Single) {
            return RxJavaPlugins.onAssembly((Single) source);
        }
        return RxJavaPlugins.onAssembly(new SingleFromUnsafeSource(source));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static <T, R> Single<R> zip(Iterable<? extends SingleSource<? extends T>> sources, Function<? super Object[], ? extends R> zipper) {
        ObjectHelper.requireNonNull(zipper, "zipper is null");
        ObjectHelper.requireNonNull(sources, "sources is null");
        return RxJavaPlugins.onAssembly(new SingleZipIterable(sources, zipper));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static <T1, T2, R> Single<R> zip(SingleSource<? extends T1> source1, SingleSource<? extends T2> source2, BiFunction<? super T1, ? super T2, ? extends R> zipper) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        return zipArray(Functions.toFunction(zipper), source1, source2);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static <T1, T2, T3, R> Single<R> zip(SingleSource<? extends T1> source1, SingleSource<? extends T2> source2, SingleSource<? extends T3> source3, Function3<? super T1, ? super T2, ? super T3, ? extends R> zipper) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(source3, "source3 is null");
        return zipArray(Functions.toFunction(zipper), source1, source2, source3);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static <T1, T2, T3, T4, R> Single<R> zip(SingleSource<? extends T1> source1, SingleSource<? extends T2> source2, SingleSource<? extends T3> source3, SingleSource<? extends T4> source4, Function4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> zipper) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(source3, "source3 is null");
        ObjectHelper.requireNonNull(source4, "source4 is null");
        return zipArray(Functions.toFunction(zipper), source1, source2, source3, source4);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static <T1, T2, T3, T4, T5, R> Single<R> zip(SingleSource<? extends T1> source1, SingleSource<? extends T2> source2, SingleSource<? extends T3> source3, SingleSource<? extends T4> source4, SingleSource<? extends T5> source5, Function5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> zipper) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(source3, "source3 is null");
        ObjectHelper.requireNonNull(source4, "source4 is null");
        ObjectHelper.requireNonNull(source5, "source5 is null");
        return zipArray(Functions.toFunction(zipper), source1, source2, source3, source4, source5);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static <T1, T2, T3, T4, T5, T6, R> Single<R> zip(SingleSource<? extends T1> source1, SingleSource<? extends T2> source2, SingleSource<? extends T3> source3, SingleSource<? extends T4> source4, SingleSource<? extends T5> source5, SingleSource<? extends T6> source6, Function6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> zipper) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(source3, "source3 is null");
        ObjectHelper.requireNonNull(source4, "source4 is null");
        ObjectHelper.requireNonNull(source5, "source5 is null");
        ObjectHelper.requireNonNull(source6, "source6 is null");
        return zipArray(Functions.toFunction(zipper), source1, source2, source3, source4, source5, source6);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static <T1, T2, T3, T4, T5, T6, T7, R> Single<R> zip(SingleSource<? extends T1> source1, SingleSource<? extends T2> source2, SingleSource<? extends T3> source3, SingleSource<? extends T4> source4, SingleSource<? extends T5> source5, SingleSource<? extends T6> source6, SingleSource<? extends T7> source7, Function7<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> zipper) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(source3, "source3 is null");
        ObjectHelper.requireNonNull(source4, "source4 is null");
        ObjectHelper.requireNonNull(source5, "source5 is null");
        ObjectHelper.requireNonNull(source6, "source6 is null");
        ObjectHelper.requireNonNull(source7, "source7 is null");
        return zipArray(Functions.toFunction(zipper), source1, source2, source3, source4, source5, source6, source7);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Single<R> zip(SingleSource<? extends T1> source1, SingleSource<? extends T2> source2, SingleSource<? extends T3> source3, SingleSource<? extends T4> source4, SingleSource<? extends T5> source5, SingleSource<? extends T6> source6, SingleSource<? extends T7> source7, SingleSource<? extends T8> source8, Function8<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> zipper) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(source3, "source3 is null");
        ObjectHelper.requireNonNull(source4, "source4 is null");
        ObjectHelper.requireNonNull(source5, "source5 is null");
        ObjectHelper.requireNonNull(source6, "source6 is null");
        ObjectHelper.requireNonNull(source7, "source7 is null");
        ObjectHelper.requireNonNull(source8, "source8 is null");
        return zipArray(Functions.toFunction(zipper), source1, source2, source3, source4, source5, source6, source7, source8);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Single<R> zip(SingleSource<? extends T1> source1, SingleSource<? extends T2> source2, SingleSource<? extends T3> source3, SingleSource<? extends T4> source4, SingleSource<? extends T5> source5, SingleSource<? extends T6> source6, SingleSource<? extends T7> source7, SingleSource<? extends T8> source8, SingleSource<? extends T9> source9, Function9<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? extends R> zipper) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(source3, "source3 is null");
        ObjectHelper.requireNonNull(source4, "source4 is null");
        ObjectHelper.requireNonNull(source5, "source5 is null");
        ObjectHelper.requireNonNull(source6, "source6 is null");
        ObjectHelper.requireNonNull(source7, "source7 is null");
        ObjectHelper.requireNonNull(source8, "source8 is null");
        ObjectHelper.requireNonNull(source9, "source9 is null");
        return zipArray(Functions.toFunction(zipper), source1, source2, source3, source4, source5, source6, source7, source8, source9);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public static <T, R> Single<R> zipArray(Function<? super Object[], ? extends R> zipper, SingleSource<? extends T>... sources) {
        ObjectHelper.requireNonNull(zipper, "zipper is null");
        ObjectHelper.requireNonNull(sources, "sources is null");
        if (sources.length == 0) {
            return error(new NoSuchElementException());
        }
        return RxJavaPlugins.onAssembly(new SingleZipArray(sources, zipper));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Single<T> ambWith(SingleSource<? extends T> other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return ambArray(this, other);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    /* renamed from: as */
    public final <R> R m31as(SingleConverter<T, ? extends R> converter) {
        return (R) ((SingleConverter) ObjectHelper.requireNonNull(converter, "converter is null")).apply(this);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Single<T> hide() {
        return RxJavaPlugins.onAssembly(new SingleHide(this));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <R> Single<R> compose(SingleTransformer<? super T, ? extends R> transformer) {
        return wrap(((SingleTransformer) ObjectHelper.requireNonNull(transformer, "transformer is null")).apply(this));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Single<T> cache() {
        return RxJavaPlugins.onAssembly(new SingleCache(this));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <U> Single<U> cast(Class<? extends U> clazz) {
        ObjectHelper.requireNonNull(clazz, "clazz is null");
        return (Single<U>) map(Functions.castFunction(clazz));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public final Flowable<T> concatWith(SingleSource<? extends T> other) {
        return concat(this, other);
    }

    @SchedulerSupport(SchedulerSupport.COMPUTATION)
    @CheckReturnValue
    public final Single<T> delay(long time, TimeUnit unit) {
        return delay(time, unit, Schedulers.computation(), false);
    }

    @SchedulerSupport(SchedulerSupport.COMPUTATION)
    @CheckReturnValue
    public final Single<T> delay(long time, TimeUnit unit, boolean delayError) {
        return delay(time, unit, Schedulers.computation(), delayError);
    }

    @SchedulerSupport(SchedulerSupport.CUSTOM)
    @CheckReturnValue
    public final Single<T> delay(long time, TimeUnit unit, Scheduler scheduler) {
        return delay(time, unit, scheduler, false);
    }

    @SchedulerSupport(SchedulerSupport.CUSTOM)
    @CheckReturnValue
    public final Single<T> delay(long time, TimeUnit unit, Scheduler scheduler, boolean delayError) {
        ObjectHelper.requireNonNull(unit, "unit is null");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new SingleDelay(this, time, unit, scheduler, delayError));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Single<T> delaySubscription(CompletableSource other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return RxJavaPlugins.onAssembly(new SingleDelayWithCompletable(this, other));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <U> Single<T> delaySubscription(SingleSource<U> other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return RxJavaPlugins.onAssembly(new SingleDelayWithSingle(this, other));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <U> Single<T> delaySubscription(ObservableSource<U> other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return RxJavaPlugins.onAssembly(new SingleDelayWithObservable(this, other));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public final <U> Single<T> delaySubscription(Publisher<U> other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return RxJavaPlugins.onAssembly(new SingleDelayWithPublisher(this, other));
    }

    @SchedulerSupport(SchedulerSupport.COMPUTATION)
    @CheckReturnValue
    public final Single<T> delaySubscription(long time, TimeUnit unit) {
        return delaySubscription(time, unit, Schedulers.computation());
    }

    @SchedulerSupport(SchedulerSupport.CUSTOM)
    @CheckReturnValue
    public final Single<T> delaySubscription(long time, TimeUnit unit, Scheduler scheduler) {
        return delaySubscription(Observable.timer(time, unit, scheduler));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <R> Maybe<R> dematerialize(Function<? super T, Notification<R>> selector) {
        ObjectHelper.requireNonNull(selector, "selector is null");
        return RxJavaPlugins.onAssembly(new SingleDematerialize(this, selector));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Single<T> doAfterSuccess(Consumer<? super T> onAfterSuccess) {
        ObjectHelper.requireNonNull(onAfterSuccess, "onAfterSuccess is null");
        return RxJavaPlugins.onAssembly(new SingleDoAfterSuccess(this, onAfterSuccess));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Single<T> doAfterTerminate(Action onAfterTerminate) {
        ObjectHelper.requireNonNull(onAfterTerminate, "onAfterTerminate is null");
        return RxJavaPlugins.onAssembly(new SingleDoAfterTerminate(this, onAfterTerminate));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Single<T> doFinally(Action onFinally) {
        ObjectHelper.requireNonNull(onFinally, "onFinally is null");
        return RxJavaPlugins.onAssembly(new SingleDoFinally(this, onFinally));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Single<T> doOnSubscribe(Consumer<? super Disposable> onSubscribe) {
        ObjectHelper.requireNonNull(onSubscribe, "onSubscribe is null");
        return RxJavaPlugins.onAssembly(new SingleDoOnSubscribe(this, onSubscribe));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Single<T> doOnTerminate(Action onTerminate) {
        ObjectHelper.requireNonNull(onTerminate, "onTerminate is null");
        return RxJavaPlugins.onAssembly(new SingleDoOnTerminate(this, onTerminate));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Single<T> doOnSuccess(Consumer<? super T> onSuccess) {
        ObjectHelper.requireNonNull(onSuccess, "onSuccess is null");
        return RxJavaPlugins.onAssembly(new SingleDoOnSuccess(this, onSuccess));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Single<T> doOnEvent(BiConsumer<? super T, ? super Throwable> onEvent) {
        ObjectHelper.requireNonNull(onEvent, "onEvent is null");
        return RxJavaPlugins.onAssembly(new SingleDoOnEvent(this, onEvent));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Single<T> doOnError(Consumer<? super Throwable> onError) {
        ObjectHelper.requireNonNull(onError, "onError is null");
        return RxJavaPlugins.onAssembly(new SingleDoOnError(this, onError));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Single<T> doOnDispose(Action onDispose) {
        ObjectHelper.requireNonNull(onDispose, "onDispose is null");
        return RxJavaPlugins.onAssembly(new SingleDoOnDispose(this, onDispose));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Maybe<T> filter(Predicate<? super T> predicate) {
        ObjectHelper.requireNonNull(predicate, "predicate is null");
        return RxJavaPlugins.onAssembly(new MaybeFilterSingle(this, predicate));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <R> Single<R> flatMap(Function<? super T, ? extends SingleSource<? extends R>> mapper) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        return RxJavaPlugins.onAssembly(new SingleFlatMap(this, mapper));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <R> Maybe<R> flatMapMaybe(Function<? super T, ? extends MaybeSource<? extends R>> mapper) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        return RxJavaPlugins.onAssembly(new SingleFlatMapMaybe(this, mapper));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public final <R> Flowable<R> flatMapPublisher(Function<? super T, ? extends Publisher<? extends R>> mapper) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        return RxJavaPlugins.onAssembly(new SingleFlatMapPublisher(this, mapper));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public final <U> Flowable<U> flattenAsFlowable(Function<? super T, ? extends Iterable<? extends U>> mapper) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        return RxJavaPlugins.onAssembly(new SingleFlatMapIterableFlowable(this, mapper));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <U> Observable<U> flattenAsObservable(Function<? super T, ? extends Iterable<? extends U>> mapper) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        return RxJavaPlugins.onAssembly(new SingleFlatMapIterableObservable(this, mapper));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <R> Observable<R> flatMapObservable(Function<? super T, ? extends ObservableSource<? extends R>> mapper) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        return RxJavaPlugins.onAssembly(new SingleFlatMapObservable(this, mapper));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Completable flatMapCompletable(Function<? super T, ? extends CompletableSource> mapper) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        return RxJavaPlugins.onAssembly(new SingleFlatMapCompletable(this, mapper));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final T blockingGet() {
        BlockingMultiObserver<T> observer = new BlockingMultiObserver<>();
        subscribe(observer);
        return observer.blockingGet();
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <R> Single<R> lift(SingleOperator<? extends R, ? super T> lift) {
        ObjectHelper.requireNonNull(lift, "lift is null");
        return RxJavaPlugins.onAssembly(new SingleLift(this, lift));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <R> Single<R> map(Function<? super T, ? extends R> mapper) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        return RxJavaPlugins.onAssembly(new SingleMap(this, mapper));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Single<Notification<T>> materialize() {
        return RxJavaPlugins.onAssembly(new SingleMaterialize(this));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Single<Boolean> contains(Object value) {
        return contains(value, ObjectHelper.equalsPredicate());
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Single<Boolean> contains(Object value, BiPredicate<Object, Object> comparer) {
        ObjectHelper.requireNonNull(value, "value is null");
        ObjectHelper.requireNonNull(comparer, "comparer is null");
        return RxJavaPlugins.onAssembly(new SingleContains(this, value, comparer));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public final Flowable<T> mergeWith(SingleSource<? extends T> other) {
        return merge(this, other);
    }

    @SchedulerSupport(SchedulerSupport.CUSTOM)
    @CheckReturnValue
    public final Single<T> observeOn(Scheduler scheduler) {
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new SingleObserveOn(this, scheduler));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Single<T> onErrorReturn(Function<Throwable, ? extends T> resumeFunction) {
        ObjectHelper.requireNonNull(resumeFunction, "resumeFunction is null");
        return RxJavaPlugins.onAssembly(new SingleOnErrorReturn(this, resumeFunction, null));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Single<T> onErrorReturnItem(T value) {
        ObjectHelper.requireNonNull(value, "value is null");
        return RxJavaPlugins.onAssembly(new SingleOnErrorReturn(this, null, value));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Single<T> onErrorResumeNext(Single<? extends T> resumeSingleInCaseOfError) {
        ObjectHelper.requireNonNull(resumeSingleInCaseOfError, "resumeSingleInCaseOfError is null");
        return onErrorResumeNext(Functions.justFunction(resumeSingleInCaseOfError));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Single<T> onErrorResumeNext(Function<? super Throwable, ? extends SingleSource<? extends T>> resumeFunctionInCaseOfError) {
        ObjectHelper.requireNonNull(resumeFunctionInCaseOfError, "resumeFunctionInCaseOfError is null");
        return RxJavaPlugins.onAssembly(new SingleResumeNext(this, resumeFunctionInCaseOfError));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Single<T> onTerminateDetach() {
        return RxJavaPlugins.onAssembly(new SingleDetach(this));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public final Flowable<T> repeat() {
        return toFlowable().repeat();
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public final Flowable<T> repeat(long times) {
        return toFlowable().repeat(times);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public final Flowable<T> repeatWhen(Function<? super Flowable<Object>, ? extends Publisher<?>> handler) {
        return toFlowable().repeatWhen(handler);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public final Flowable<T> repeatUntil(BooleanSupplier stop) {
        return toFlowable().repeatUntil(stop);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Single<T> retry() {
        return toSingle(toFlowable().retry());
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Single<T> retry(long times) {
        return toSingle(toFlowable().retry(times));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Single<T> retry(BiPredicate<? super Integer, ? super Throwable> predicate) {
        return toSingle(toFlowable().retry(predicate));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Single<T> retry(long times, Predicate<? super Throwable> predicate) {
        return toSingle(toFlowable().retry(times, predicate));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Single<T> retry(Predicate<? super Throwable> predicate) {
        return toSingle(toFlowable().retry(predicate));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Single<T> retryWhen(Function<? super Flowable<Throwable>, ? extends Publisher<?>> handler) {
        return toSingle(toFlowable().retryWhen(handler));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Disposable subscribe() {
        return subscribe(Functions.emptyConsumer(), Functions.ON_ERROR_MISSING);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Disposable subscribe(BiConsumer<? super T, ? super Throwable> onCallback) {
        ObjectHelper.requireNonNull(onCallback, "onCallback is null");
        BiConsumerSingleObserver<T> observer = new BiConsumerSingleObserver<>(onCallback);
        subscribe(observer);
        return observer;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Disposable subscribe(Consumer<? super T> onSuccess) {
        return subscribe(onSuccess, Functions.ON_ERROR_MISSING);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Disposable subscribe(Consumer<? super T> onSuccess, Consumer<? super Throwable> onError) {
        ObjectHelper.requireNonNull(onSuccess, "onSuccess is null");
        ObjectHelper.requireNonNull(onError, "onError is null");
        ConsumerSingleObserver<T> observer = new ConsumerSingleObserver<>(onSuccess, onError);
        subscribe(observer);
        return observer;
    }

    @Override // io.reactivex.SingleSource
    @SchedulerSupport(SchedulerSupport.NONE)
    public final void subscribe(SingleObserver<? super T> observer) {
        ObjectHelper.requireNonNull(observer, "observer is null");
        SingleObserver<? super T> observer2 = RxJavaPlugins.onSubscribe(this, observer);
        ObjectHelper.requireNonNull(observer2, "The RxJavaPlugins.onSubscribe hook returned a null SingleObserver. Please check the handler provided to RxJavaPlugins.setOnSingleSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
        try {
            subscribeActual(observer2);
        } catch (NullPointerException ex) {
            throw ex;
        } catch (Throwable ex2) {
            Exceptions.throwIfFatal(ex2);
            NullPointerException npe = new NullPointerException("subscribeActual failed");
            npe.initCause(ex2);
            throw npe;
        }
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <E extends SingleObserver<? super T>> E subscribeWith(E observer) {
        subscribe(observer);
        return observer;
    }

    @SchedulerSupport(SchedulerSupport.CUSTOM)
    @CheckReturnValue
    public final Single<T> subscribeOn(Scheduler scheduler) {
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new SingleSubscribeOn(this, scheduler));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Single<T> takeUntil(CompletableSource other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return takeUntil(new CompletableToFlowable(other));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public final <E> Single<T> takeUntil(Publisher<E> other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return RxJavaPlugins.onAssembly(new SingleTakeUntil(this, other));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <E> Single<T> takeUntil(SingleSource<? extends E> other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return takeUntil(new SingleToFlowable(other));
    }

    @SchedulerSupport(SchedulerSupport.COMPUTATION)
    @CheckReturnValue
    public final Single<T> timeout(long timeout, TimeUnit unit) {
        return timeout0(timeout, unit, Schedulers.computation(), null);
    }

    @SchedulerSupport(SchedulerSupport.CUSTOM)
    @CheckReturnValue
    public final Single<T> timeout(long timeout, TimeUnit unit, Scheduler scheduler) {
        return timeout0(timeout, unit, scheduler, null);
    }

    @SchedulerSupport(SchedulerSupport.CUSTOM)
    @CheckReturnValue
    public final Single<T> timeout(long timeout, TimeUnit unit, Scheduler scheduler, SingleSource<? extends T> other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return timeout0(timeout, unit, scheduler, other);
    }

    @SchedulerSupport(SchedulerSupport.COMPUTATION)
    @CheckReturnValue
    public final Single<T> timeout(long timeout, TimeUnit unit, SingleSource<? extends T> other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return timeout0(timeout, unit, Schedulers.computation(), other);
    }

    private Single<T> timeout0(long timeout, TimeUnit unit, Scheduler scheduler, SingleSource<? extends T> other) {
        ObjectHelper.requireNonNull(unit, "unit is null");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new SingleTimeout(this, timeout, unit, scheduler, other));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    /* renamed from: to */
    public final <R> R m30to(Function<? super Single<T>, R> convert) {
        try {
            return (R) ((Function) ObjectHelper.requireNonNull(convert, "convert is null")).apply(this);
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            throw ExceptionHelper.wrapOrThrow(ex);
        }
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    @Deprecated
    public final Completable toCompletable() {
        return RxJavaPlugins.onAssembly(new CompletableFromSingle(this));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Completable ignoreElement() {
        return RxJavaPlugins.onAssembly(new CompletableFromSingle(this));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    @CheckReturnValue
    public final Flowable<T> toFlowable() {
        if (this instanceof FuseToFlowable) {
            return ((FuseToFlowable) this).fuseToFlowable();
        }
        return RxJavaPlugins.onAssembly(new SingleToFlowable(this));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Future<T> toFuture() {
        return (Future) subscribeWith(new FutureSingleObserver());
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Maybe<T> toMaybe() {
        if (this instanceof FuseToMaybe) {
            return ((FuseToMaybe) this).fuseToMaybe();
        }
        return RxJavaPlugins.onAssembly(new MaybeFromSingle(this));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final Observable<T> toObservable() {
        if (this instanceof FuseToObservable) {
            return ((FuseToObservable) this).fuseToObservable();
        }
        return RxJavaPlugins.onAssembly(new SingleToObservable(this));
    }

    @SchedulerSupport(SchedulerSupport.CUSTOM)
    @CheckReturnValue
    public final Single<T> unsubscribeOn(Scheduler scheduler) {
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new SingleUnsubscribeOn(this, scheduler));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <U, R> Single<R> zipWith(SingleSource<U> other, BiFunction<? super T, ? super U, ? extends R> zipper) {
        return zip(this, other, zipper);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final TestObserver<T> test() {
        TestObserver<T> to = new TestObserver<>();
        subscribe(to);
        return to;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final TestObserver<T> test(boolean cancelled) {
        TestObserver<T> to = new TestObserver<>();
        if (cancelled) {
            to.cancel();
        }
        subscribe(to);
        return to;
    }

    private static <T> Single<T> toSingle(Flowable<T> source) {
        return RxJavaPlugins.onAssembly(new FlowableSingleSingle(source, null));
    }
}
