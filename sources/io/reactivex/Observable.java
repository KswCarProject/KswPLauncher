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
import io.reactivex.internal.fuseable.ScalarCallable;
import io.reactivex.internal.observers.BlockingFirstObserver;
import io.reactivex.internal.observers.BlockingLastObserver;
import io.reactivex.internal.observers.ForEachWhileObserver;
import io.reactivex.internal.observers.FutureObserver;
import io.reactivex.internal.observers.LambdaObserver;
import io.reactivex.internal.operators.flowable.FlowableFromObservable;
import io.reactivex.internal.operators.flowable.FlowableOnBackpressureError;
import io.reactivex.internal.operators.mixed.ObservableConcatMapCompletable;
import io.reactivex.internal.operators.mixed.ObservableConcatMapMaybe;
import io.reactivex.internal.operators.mixed.ObservableConcatMapSingle;
import io.reactivex.internal.operators.mixed.ObservableSwitchMapCompletable;
import io.reactivex.internal.operators.mixed.ObservableSwitchMapMaybe;
import io.reactivex.internal.operators.mixed.ObservableSwitchMapSingle;
import io.reactivex.internal.operators.observable.BlockingObservableIterable;
import io.reactivex.internal.operators.observable.BlockingObservableLatest;
import io.reactivex.internal.operators.observable.BlockingObservableMostRecent;
import io.reactivex.internal.operators.observable.BlockingObservableNext;
import io.reactivex.internal.operators.observable.ObservableAllSingle;
import io.reactivex.internal.operators.observable.ObservableAmb;
import io.reactivex.internal.operators.observable.ObservableAnySingle;
import io.reactivex.internal.operators.observable.ObservableBlockingSubscribe;
import io.reactivex.internal.operators.observable.ObservableBuffer;
import io.reactivex.internal.operators.observable.ObservableBufferBoundary;
import io.reactivex.internal.operators.observable.ObservableBufferBoundarySupplier;
import io.reactivex.internal.operators.observable.ObservableBufferExactBoundary;
import io.reactivex.internal.operators.observable.ObservableBufferTimed;
import io.reactivex.internal.operators.observable.ObservableCache;
import io.reactivex.internal.operators.observable.ObservableCollectSingle;
import io.reactivex.internal.operators.observable.ObservableCombineLatest;
import io.reactivex.internal.operators.observable.ObservableConcatMap;
import io.reactivex.internal.operators.observable.ObservableConcatMapEager;
import io.reactivex.internal.operators.observable.ObservableConcatWithCompletable;
import io.reactivex.internal.operators.observable.ObservableConcatWithMaybe;
import io.reactivex.internal.operators.observable.ObservableConcatWithSingle;
import io.reactivex.internal.operators.observable.ObservableCountSingle;
import io.reactivex.internal.operators.observable.ObservableCreate;
import io.reactivex.internal.operators.observable.ObservableDebounce;
import io.reactivex.internal.operators.observable.ObservableDebounceTimed;
import io.reactivex.internal.operators.observable.ObservableDefer;
import io.reactivex.internal.operators.observable.ObservableDelay;
import io.reactivex.internal.operators.observable.ObservableDelaySubscriptionOther;
import io.reactivex.internal.operators.observable.ObservableDematerialize;
import io.reactivex.internal.operators.observable.ObservableDetach;
import io.reactivex.internal.operators.observable.ObservableDistinct;
import io.reactivex.internal.operators.observable.ObservableDistinctUntilChanged;
import io.reactivex.internal.operators.observable.ObservableDoAfterNext;
import io.reactivex.internal.operators.observable.ObservableDoFinally;
import io.reactivex.internal.operators.observable.ObservableDoOnEach;
import io.reactivex.internal.operators.observable.ObservableDoOnLifecycle;
import io.reactivex.internal.operators.observable.ObservableElementAtMaybe;
import io.reactivex.internal.operators.observable.ObservableElementAtSingle;
import io.reactivex.internal.operators.observable.ObservableEmpty;
import io.reactivex.internal.operators.observable.ObservableError;
import io.reactivex.internal.operators.observable.ObservableFilter;
import io.reactivex.internal.operators.observable.ObservableFlatMap;
import io.reactivex.internal.operators.observable.ObservableFlatMapCompletableCompletable;
import io.reactivex.internal.operators.observable.ObservableFlatMapMaybe;
import io.reactivex.internal.operators.observable.ObservableFlatMapSingle;
import io.reactivex.internal.operators.observable.ObservableFlattenIterable;
import io.reactivex.internal.operators.observable.ObservableFromArray;
import io.reactivex.internal.operators.observable.ObservableFromCallable;
import io.reactivex.internal.operators.observable.ObservableFromFuture;
import io.reactivex.internal.operators.observable.ObservableFromIterable;
import io.reactivex.internal.operators.observable.ObservableFromPublisher;
import io.reactivex.internal.operators.observable.ObservableFromUnsafeSource;
import io.reactivex.internal.operators.observable.ObservableGenerate;
import io.reactivex.internal.operators.observable.ObservableGroupBy;
import io.reactivex.internal.operators.observable.ObservableGroupJoin;
import io.reactivex.internal.operators.observable.ObservableHide;
import io.reactivex.internal.operators.observable.ObservableIgnoreElements;
import io.reactivex.internal.operators.observable.ObservableIgnoreElementsCompletable;
import io.reactivex.internal.operators.observable.ObservableInternalHelper;
import io.reactivex.internal.operators.observable.ObservableInterval;
import io.reactivex.internal.operators.observable.ObservableIntervalRange;
import io.reactivex.internal.operators.observable.ObservableJoin;
import io.reactivex.internal.operators.observable.ObservableJust;
import io.reactivex.internal.operators.observable.ObservableLastMaybe;
import io.reactivex.internal.operators.observable.ObservableLastSingle;
import io.reactivex.internal.operators.observable.ObservableLift;
import io.reactivex.internal.operators.observable.ObservableMap;
import io.reactivex.internal.operators.observable.ObservableMapNotification;
import io.reactivex.internal.operators.observable.ObservableMaterialize;
import io.reactivex.internal.operators.observable.ObservableMergeWithCompletable;
import io.reactivex.internal.operators.observable.ObservableMergeWithMaybe;
import io.reactivex.internal.operators.observable.ObservableMergeWithSingle;
import io.reactivex.internal.operators.observable.ObservableNever;
import io.reactivex.internal.operators.observable.ObservableObserveOn;
import io.reactivex.internal.operators.observable.ObservableOnErrorNext;
import io.reactivex.internal.operators.observable.ObservableOnErrorReturn;
import io.reactivex.internal.operators.observable.ObservablePublish;
import io.reactivex.internal.operators.observable.ObservablePublishSelector;
import io.reactivex.internal.operators.observable.ObservableRange;
import io.reactivex.internal.operators.observable.ObservableRangeLong;
import io.reactivex.internal.operators.observable.ObservableReduceMaybe;
import io.reactivex.internal.operators.observable.ObservableReduceSeedSingle;
import io.reactivex.internal.operators.observable.ObservableReduceWithSingle;
import io.reactivex.internal.operators.observable.ObservableRepeat;
import io.reactivex.internal.operators.observable.ObservableRepeatUntil;
import io.reactivex.internal.operators.observable.ObservableRepeatWhen;
import io.reactivex.internal.operators.observable.ObservableReplay;
import io.reactivex.internal.operators.observable.ObservableRetryBiPredicate;
import io.reactivex.internal.operators.observable.ObservableRetryPredicate;
import io.reactivex.internal.operators.observable.ObservableRetryWhen;
import io.reactivex.internal.operators.observable.ObservableSampleTimed;
import io.reactivex.internal.operators.observable.ObservableSampleWithObservable;
import io.reactivex.internal.operators.observable.ObservableScalarXMap;
import io.reactivex.internal.operators.observable.ObservableScan;
import io.reactivex.internal.operators.observable.ObservableScanSeed;
import io.reactivex.internal.operators.observable.ObservableSequenceEqualSingle;
import io.reactivex.internal.operators.observable.ObservableSerialized;
import io.reactivex.internal.operators.observable.ObservableSingleMaybe;
import io.reactivex.internal.operators.observable.ObservableSingleSingle;
import io.reactivex.internal.operators.observable.ObservableSkip;
import io.reactivex.internal.operators.observable.ObservableSkipLast;
import io.reactivex.internal.operators.observable.ObservableSkipLastTimed;
import io.reactivex.internal.operators.observable.ObservableSkipUntil;
import io.reactivex.internal.operators.observable.ObservableSkipWhile;
import io.reactivex.internal.operators.observable.ObservableSubscribeOn;
import io.reactivex.internal.operators.observable.ObservableSwitchIfEmpty;
import io.reactivex.internal.operators.observable.ObservableSwitchMap;
import io.reactivex.internal.operators.observable.ObservableTake;
import io.reactivex.internal.operators.observable.ObservableTakeLast;
import io.reactivex.internal.operators.observable.ObservableTakeLastOne;
import io.reactivex.internal.operators.observable.ObservableTakeLastTimed;
import io.reactivex.internal.operators.observable.ObservableTakeUntil;
import io.reactivex.internal.operators.observable.ObservableTakeUntilPredicate;
import io.reactivex.internal.operators.observable.ObservableTakeWhile;
import io.reactivex.internal.operators.observable.ObservableThrottleFirstTimed;
import io.reactivex.internal.operators.observable.ObservableThrottleLatest;
import io.reactivex.internal.operators.observable.ObservableTimeInterval;
import io.reactivex.internal.operators.observable.ObservableTimeout;
import io.reactivex.internal.operators.observable.ObservableTimeoutTimed;
import io.reactivex.internal.operators.observable.ObservableTimer;
import io.reactivex.internal.operators.observable.ObservableToList;
import io.reactivex.internal.operators.observable.ObservableToListSingle;
import io.reactivex.internal.operators.observable.ObservableUnsubscribeOn;
import io.reactivex.internal.operators.observable.ObservableUsing;
import io.reactivex.internal.operators.observable.ObservableWindow;
import io.reactivex.internal.operators.observable.ObservableWindowBoundary;
import io.reactivex.internal.operators.observable.ObservableWindowBoundarySelector;
import io.reactivex.internal.operators.observable.ObservableWindowBoundarySupplier;
import io.reactivex.internal.operators.observable.ObservableWindowTimed;
import io.reactivex.internal.operators.observable.ObservableWithLatestFrom;
import io.reactivex.internal.operators.observable.ObservableWithLatestFromMany;
import io.reactivex.internal.operators.observable.ObservableZip;
import io.reactivex.internal.operators.observable.ObservableZipIterable;
import io.reactivex.internal.util.ArrayListSupplier;
import io.reactivex.internal.util.ErrorMode;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.HashMapSupplier;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.observers.SafeObserver;
import io.reactivex.observers.TestObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.Timed;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.LongCompanionObject;
import org.reactivestreams.Publisher;

public abstract class Observable<T> implements ObservableSource<T> {
    /* access modifiers changed from: protected */
    public abstract void subscribeActual(Observer<? super T> observer);

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> amb(Iterable<? extends ObservableSource<? extends T>> sources) {
        ObjectHelper.requireNonNull(sources, "sources is null");
        return RxJavaPlugins.onAssembly(new ObservableAmb((ObservableSource<? extends T>[]) null, sources));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> ambArray(ObservableSource<? extends T>... sources) {
        ObjectHelper.requireNonNull(sources, "sources is null");
        int len = sources.length;
        if (len == 0) {
            return empty();
        }
        if (len == 1) {
            return wrap(sources[0]);
        }
        return RxJavaPlugins.onAssembly(new ObservableAmb(sources, (Iterable) null));
    }

    public static int bufferSize() {
        return Flowable.bufferSize();
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T, R> Observable<R> combineLatest(Function<? super Object[], ? extends R> combiner, int bufferSize, ObservableSource<? extends T>... sources) {
        return combineLatest(sources, combiner, bufferSize);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T, R> Observable<R> combineLatest(Iterable<? extends ObservableSource<? extends T>> sources, Function<? super Object[], ? extends R> combiner) {
        return combineLatest(sources, combiner, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T, R> Observable<R> combineLatest(Iterable<? extends ObservableSource<? extends T>> sources, Function<? super Object[], ? extends R> combiner, int bufferSize) {
        ObjectHelper.requireNonNull(sources, "sources is null");
        ObjectHelper.requireNonNull(combiner, "combiner is null");
        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        return RxJavaPlugins.onAssembly(new ObservableCombineLatest((ObservableSource<? extends T>[]) null, sources, combiner, bufferSize << 1, false));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T, R> Observable<R> combineLatest(ObservableSource<? extends T>[] sources, Function<? super Object[], ? extends R> combiner) {
        return combineLatest(sources, combiner, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T, R> Observable<R> combineLatest(ObservableSource<? extends T>[] sources, Function<? super Object[], ? extends R> combiner, int bufferSize) {
        ObjectHelper.requireNonNull(sources, "sources is null");
        if (sources.length == 0) {
            return empty();
        }
        ObjectHelper.requireNonNull(combiner, "combiner is null");
        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        return RxJavaPlugins.onAssembly(new ObservableCombineLatest(sources, (Iterable) null, combiner, bufferSize << 1, false));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T1, T2, R> Observable<R> combineLatest(ObservableSource<? extends T1> source1, ObservableSource<? extends T2> source2, BiFunction<? super T1, ? super T2, ? extends R> combiner) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        return combineLatest(Functions.toFunction(combiner), bufferSize(), (ObservableSource<? extends T>[]) new ObservableSource[]{source1, source2});
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T1, T2, T3, R> Observable<R> combineLatest(ObservableSource<? extends T1> source1, ObservableSource<? extends T2> source2, ObservableSource<? extends T3> source3, Function3<? super T1, ? super T2, ? super T3, ? extends R> combiner) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(source3, "source3 is null");
        return combineLatest(Functions.toFunction(combiner), bufferSize(), (ObservableSource<? extends T>[]) new ObservableSource[]{source1, source2, source3});
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T1, T2, T3, T4, R> Observable<R> combineLatest(ObservableSource<? extends T1> source1, ObservableSource<? extends T2> source2, ObservableSource<? extends T3> source3, ObservableSource<? extends T4> source4, Function4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> combiner) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(source3, "source3 is null");
        ObjectHelper.requireNonNull(source4, "source4 is null");
        return combineLatest(Functions.toFunction(combiner), bufferSize(), (ObservableSource<? extends T>[]) new ObservableSource[]{source1, source2, source3, source4});
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T1, T2, T3, T4, T5, R> Observable<R> combineLatest(ObservableSource<? extends T1> source1, ObservableSource<? extends T2> source2, ObservableSource<? extends T3> source3, ObservableSource<? extends T4> source4, ObservableSource<? extends T5> source5, Function5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> combiner) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(source3, "source3 is null");
        ObjectHelper.requireNonNull(source4, "source4 is null");
        ObjectHelper.requireNonNull(source5, "source5 is null");
        return combineLatest(Functions.toFunction(combiner), bufferSize(), (ObservableSource<? extends T>[]) new ObservableSource[]{source1, source2, source3, source4, source5});
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T1, T2, T3, T4, T5, T6, R> Observable<R> combineLatest(ObservableSource<? extends T1> source1, ObservableSource<? extends T2> source2, ObservableSource<? extends T3> source3, ObservableSource<? extends T4> source4, ObservableSource<? extends T5> source5, ObservableSource<? extends T6> source6, Function6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> combiner) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(source3, "source3 is null");
        ObjectHelper.requireNonNull(source4, "source4 is null");
        ObjectHelper.requireNonNull(source5, "source5 is null");
        ObjectHelper.requireNonNull(source6, "source6 is null");
        return combineLatest(Functions.toFunction(combiner), bufferSize(), (ObservableSource<? extends T>[]) new ObservableSource[]{source1, source2, source3, source4, source5, source6});
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T1, T2, T3, T4, T5, T6, T7, R> Observable<R> combineLatest(ObservableSource<? extends T1> source1, ObservableSource<? extends T2> source2, ObservableSource<? extends T3> source3, ObservableSource<? extends T4> source4, ObservableSource<? extends T5> source5, ObservableSource<? extends T6> source6, ObservableSource<? extends T7> source7, Function7<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> combiner) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(source3, "source3 is null");
        ObjectHelper.requireNonNull(source4, "source4 is null");
        ObjectHelper.requireNonNull(source5, "source5 is null");
        ObjectHelper.requireNonNull(source6, "source6 is null");
        ObjectHelper.requireNonNull(source7, "source7 is null");
        return combineLatest(Functions.toFunction(combiner), bufferSize(), (ObservableSource<? extends T>[]) new ObservableSource[]{source1, source2, source3, source4, source5, source6, source7});
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Observable<R> combineLatest(ObservableSource<? extends T1> source1, ObservableSource<? extends T2> source2, ObservableSource<? extends T3> source3, ObservableSource<? extends T4> source4, ObservableSource<? extends T5> source5, ObservableSource<? extends T6> source6, ObservableSource<? extends T7> source7, ObservableSource<? extends T8> source8, Function8<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> combiner) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(source3, "source3 is null");
        ObjectHelper.requireNonNull(source4, "source4 is null");
        ObjectHelper.requireNonNull(source5, "source5 is null");
        ObjectHelper.requireNonNull(source6, "source6 is null");
        ObjectHelper.requireNonNull(source7, "source7 is null");
        ObjectHelper.requireNonNull(source8, "source8 is null");
        return combineLatest(Functions.toFunction(combiner), bufferSize(), (ObservableSource<? extends T>[]) new ObservableSource[]{source1, source2, source3, source4, source5, source6, source7, source8});
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Observable<R> combineLatest(ObservableSource<? extends T1> source1, ObservableSource<? extends T2> source2, ObservableSource<? extends T3> source3, ObservableSource<? extends T4> source4, ObservableSource<? extends T5> source5, ObservableSource<? extends T6> source6, ObservableSource<? extends T7> source7, ObservableSource<? extends T8> source8, ObservableSource<? extends T9> source9, Function9<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? extends R> combiner) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(source3, "source3 is null");
        ObjectHelper.requireNonNull(source4, "source4 is null");
        ObjectHelper.requireNonNull(source5, "source5 is null");
        ObjectHelper.requireNonNull(source6, "source6 is null");
        ObjectHelper.requireNonNull(source7, "source7 is null");
        ObjectHelper.requireNonNull(source8, "source8 is null");
        ObjectHelper.requireNonNull(source9, "source9 is null");
        return combineLatest(Functions.toFunction(combiner), bufferSize(), (ObservableSource<? extends T>[]) new ObservableSource[]{source1, source2, source3, source4, source5, source6, source7, source8, source9});
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T, R> Observable<R> combineLatestDelayError(ObservableSource<? extends T>[] sources, Function<? super Object[], ? extends R> combiner) {
        return combineLatestDelayError(sources, combiner, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T, R> Observable<R> combineLatestDelayError(Function<? super Object[], ? extends R> combiner, int bufferSize, ObservableSource<? extends T>... sources) {
        return combineLatestDelayError(sources, combiner, bufferSize);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T, R> Observable<R> combineLatestDelayError(ObservableSource<? extends T>[] sources, Function<? super Object[], ? extends R> combiner, int bufferSize) {
        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        ObjectHelper.requireNonNull(combiner, "combiner is null");
        if (sources.length == 0) {
            return empty();
        }
        return RxJavaPlugins.onAssembly(new ObservableCombineLatest(sources, (Iterable) null, combiner, bufferSize << 1, true));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T, R> Observable<R> combineLatestDelayError(Iterable<? extends ObservableSource<? extends T>> sources, Function<? super Object[], ? extends R> combiner) {
        return combineLatestDelayError(sources, combiner, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T, R> Observable<R> combineLatestDelayError(Iterable<? extends ObservableSource<? extends T>> sources, Function<? super Object[], ? extends R> combiner, int bufferSize) {
        ObjectHelper.requireNonNull(sources, "sources is null");
        ObjectHelper.requireNonNull(combiner, "combiner is null");
        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        return RxJavaPlugins.onAssembly(new ObservableCombineLatest((ObservableSource<? extends T>[]) null, sources, combiner, bufferSize << 1, true));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> concat(Iterable<? extends ObservableSource<? extends T>> sources) {
        ObjectHelper.requireNonNull(sources, "sources is null");
        return fromIterable(sources).concatMapDelayError(Functions.identity(), bufferSize(), false);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> concat(ObservableSource<? extends ObservableSource<? extends T>> sources) {
        return concat(sources, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> concat(ObservableSource<? extends ObservableSource<? extends T>> sources, int prefetch) {
        ObjectHelper.requireNonNull(sources, "sources is null");
        ObjectHelper.verifyPositive(prefetch, "prefetch");
        return RxJavaPlugins.onAssembly(new ObservableConcatMap(sources, Functions.identity(), prefetch, ErrorMode.IMMEDIATE));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> concat(ObservableSource<? extends T> source1, ObservableSource<? extends T> source2) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        return concatArray(source1, source2);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> concat(ObservableSource<? extends T> source1, ObservableSource<? extends T> source2, ObservableSource<? extends T> source3) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(source3, "source3 is null");
        return concatArray(source1, source2, source3);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> concat(ObservableSource<? extends T> source1, ObservableSource<? extends T> source2, ObservableSource<? extends T> source3, ObservableSource<? extends T> source4) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(source3, "source3 is null");
        ObjectHelper.requireNonNull(source4, "source4 is null");
        return concatArray(source1, source2, source3, source4);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> concatArray(ObservableSource<? extends T>... sources) {
        if (sources.length == 0) {
            return empty();
        }
        if (sources.length == 1) {
            return wrap(sources[0]);
        }
        return RxJavaPlugins.onAssembly(new ObservableConcatMap(fromArray(sources), Functions.identity(), bufferSize(), ErrorMode.BOUNDARY));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> concatArrayDelayError(ObservableSource<? extends T>... sources) {
        if (sources.length == 0) {
            return empty();
        }
        if (sources.length == 1) {
            return wrap(sources[0]);
        }
        return concatDelayError(fromArray(sources));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> concatArrayEager(ObservableSource<? extends T>... sources) {
        return concatArrayEager(bufferSize(), bufferSize(), sources);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> concatArrayEager(int maxConcurrency, int prefetch, ObservableSource<? extends T>... sources) {
        return fromArray(sources).concatMapEagerDelayError(Functions.identity(), maxConcurrency, prefetch, false);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> concatArrayEagerDelayError(ObservableSource<? extends T>... sources) {
        return concatArrayEagerDelayError(bufferSize(), bufferSize(), sources);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> concatArrayEagerDelayError(int maxConcurrency, int prefetch, ObservableSource<? extends T>... sources) {
        return fromArray(sources).concatMapEagerDelayError(Functions.identity(), maxConcurrency, prefetch, true);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> concatDelayError(Iterable<? extends ObservableSource<? extends T>> sources) {
        ObjectHelper.requireNonNull(sources, "sources is null");
        return concatDelayError(fromIterable(sources));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> concatDelayError(ObservableSource<? extends ObservableSource<? extends T>> sources) {
        return concatDelayError(sources, bufferSize(), true);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> concatDelayError(ObservableSource<? extends ObservableSource<? extends T>> sources, int prefetch, boolean tillTheEnd) {
        ObjectHelper.requireNonNull(sources, "sources is null");
        ObjectHelper.verifyPositive(prefetch, "prefetch is null");
        return RxJavaPlugins.onAssembly(new ObservableConcatMap(sources, Functions.identity(), prefetch, tillTheEnd ? ErrorMode.END : ErrorMode.BOUNDARY));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> concatEager(ObservableSource<? extends ObservableSource<? extends T>> sources) {
        return concatEager(sources, bufferSize(), bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> concatEager(ObservableSource<? extends ObservableSource<? extends T>> sources, int maxConcurrency, int prefetch) {
        return wrap(sources).concatMapEager(Functions.identity(), maxConcurrency, prefetch);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> concatEager(Iterable<? extends ObservableSource<? extends T>> sources) {
        return concatEager(sources, bufferSize(), bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> concatEager(Iterable<? extends ObservableSource<? extends T>> sources, int maxConcurrency, int prefetch) {
        return fromIterable(sources).concatMapEagerDelayError(Functions.identity(), maxConcurrency, prefetch, false);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> create(ObservableOnSubscribe<T> source) {
        ObjectHelper.requireNonNull(source, "source is null");
        return RxJavaPlugins.onAssembly(new ObservableCreate(source));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> defer(Callable<? extends ObservableSource<? extends T>> supplier) {
        ObjectHelper.requireNonNull(supplier, "supplier is null");
        return RxJavaPlugins.onAssembly(new ObservableDefer(supplier));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> empty() {
        return RxJavaPlugins.onAssembly(ObservableEmpty.INSTANCE);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> error(Callable<? extends Throwable> errorSupplier) {
        ObjectHelper.requireNonNull(errorSupplier, "errorSupplier is null");
        return RxJavaPlugins.onAssembly(new ObservableError(errorSupplier));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> error(Throwable exception) {
        ObjectHelper.requireNonNull(exception, "exception is null");
        return error((Callable<? extends Throwable>) Functions.justCallable(exception));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> fromArray(T... items) {
        ObjectHelper.requireNonNull(items, "items is null");
        if (items.length == 0) {
            return empty();
        }
        if (items.length == 1) {
            return just(items[0]);
        }
        return RxJavaPlugins.onAssembly(new ObservableFromArray(items));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> fromCallable(Callable<? extends T> supplier) {
        ObjectHelper.requireNonNull(supplier, "supplier is null");
        return RxJavaPlugins.onAssembly(new ObservableFromCallable(supplier));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> fromFuture(Future<? extends T> future) {
        ObjectHelper.requireNonNull(future, "future is null");
        return RxJavaPlugins.onAssembly(new ObservableFromFuture(future, 0, (TimeUnit) null));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> fromFuture(Future<? extends T> future, long timeout, TimeUnit unit) {
        ObjectHelper.requireNonNull(future, "future is null");
        ObjectHelper.requireNonNull(unit, "unit is null");
        return RxJavaPlugins.onAssembly(new ObservableFromFuture(future, timeout, unit));
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public static <T> Observable<T> fromFuture(Future<? extends T> future, long timeout, TimeUnit unit, Scheduler scheduler) {
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return fromFuture(future, timeout, unit).subscribeOn(scheduler);
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public static <T> Observable<T> fromFuture(Future<? extends T> future, Scheduler scheduler) {
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return fromFuture(future).subscribeOn(scheduler);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> fromIterable(Iterable<? extends T> source) {
        ObjectHelper.requireNonNull(source, "source is null");
        return RxJavaPlugins.onAssembly(new ObservableFromIterable(source));
    }

    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.UNBOUNDED_IN)
    @SchedulerSupport("none")
    public static <T> Observable<T> fromPublisher(Publisher<? extends T> publisher) {
        ObjectHelper.requireNonNull(publisher, "publisher is null");
        return RxJavaPlugins.onAssembly(new ObservableFromPublisher(publisher));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> generate(Consumer<Emitter<T>> generator) {
        ObjectHelper.requireNonNull(generator, "generator is null");
        return generate(Functions.nullSupplier(), ObservableInternalHelper.simpleGenerator(generator), Functions.emptyConsumer());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T, S> Observable<T> generate(Callable<S> initialState, BiConsumer<S, Emitter<T>> generator) {
        ObjectHelper.requireNonNull(generator, "generator is null");
        return generate(initialState, ObservableInternalHelper.simpleBiGenerator(generator), Functions.emptyConsumer());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T, S> Observable<T> generate(Callable<S> initialState, BiConsumer<S, Emitter<T>> generator, Consumer<? super S> disposeState) {
        ObjectHelper.requireNonNull(generator, "generator is null");
        return generate(initialState, ObservableInternalHelper.simpleBiGenerator(generator), disposeState);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T, S> Observable<T> generate(Callable<S> initialState, BiFunction<S, Emitter<T>, S> generator) {
        return generate(initialState, generator, Functions.emptyConsumer());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T, S> Observable<T> generate(Callable<S> initialState, BiFunction<S, Emitter<T>, S> generator, Consumer<? super S> disposeState) {
        ObjectHelper.requireNonNull(initialState, "initialState is null");
        ObjectHelper.requireNonNull(generator, "generator is null");
        ObjectHelper.requireNonNull(disposeState, "disposeState is null");
        return RxJavaPlugins.onAssembly(new ObservableGenerate(initialState, generator, disposeState));
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public static Observable<Long> interval(long initialDelay, long period, TimeUnit unit) {
        return interval(initialDelay, period, unit, Schedulers.computation());
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public static Observable<Long> interval(long initialDelay, long period, TimeUnit unit, Scheduler scheduler) {
        ObjectHelper.requireNonNull(unit, "unit is null");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new ObservableInterval(Math.max(0, initialDelay), Math.max(0, period), unit, scheduler));
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public static Observable<Long> interval(long period, TimeUnit unit) {
        return interval(period, period, unit, Schedulers.computation());
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public static Observable<Long> interval(long period, TimeUnit unit, Scheduler scheduler) {
        return interval(period, period, unit, scheduler);
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public static Observable<Long> intervalRange(long start, long count, long initialDelay, long period, TimeUnit unit) {
        return intervalRange(start, count, initialDelay, period, unit, Schedulers.computation());
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public static Observable<Long> intervalRange(long start, long count, long initialDelay, long period, TimeUnit unit, Scheduler scheduler) {
        long j = count;
        long j2 = initialDelay;
        TimeUnit timeUnit = unit;
        Scheduler scheduler2 = scheduler;
        if (j < 0) {
            throw new IllegalArgumentException("count >= 0 required but it was " + j);
        } else if (j == 0) {
            return empty().delay(j2, timeUnit, scheduler2);
        } else {
            long end = start + (j - 1);
            if (start <= 0 || end >= 0) {
                ObjectHelper.requireNonNull(timeUnit, "unit is null");
                ObjectHelper.requireNonNull(scheduler2, "scheduler is null");
                return RxJavaPlugins.onAssembly(new ObservableIntervalRange(start, end, Math.max(0, j2), Math.max(0, period), unit, scheduler));
            }
            throw new IllegalArgumentException("Overflow! start + count is bigger than Long.MAX_VALUE");
        }
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> just(T item) {
        ObjectHelper.requireNonNull(item, "item is null");
        return RxJavaPlugins.onAssembly(new ObservableJust(item));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> just(T item1, T item2) {
        ObjectHelper.requireNonNull(item1, "item1 is null");
        ObjectHelper.requireNonNull(item2, "item2 is null");
        return fromArray(item1, item2);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> just(T item1, T item2, T item3) {
        ObjectHelper.requireNonNull(item1, "item1 is null");
        ObjectHelper.requireNonNull(item2, "item2 is null");
        ObjectHelper.requireNonNull(item3, "item3 is null");
        return fromArray(item1, item2, item3);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> just(T item1, T item2, T item3, T item4) {
        ObjectHelper.requireNonNull(item1, "item1 is null");
        ObjectHelper.requireNonNull(item2, "item2 is null");
        ObjectHelper.requireNonNull(item3, "item3 is null");
        ObjectHelper.requireNonNull(item4, "item4 is null");
        return fromArray(item1, item2, item3, item4);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> just(T item1, T item2, T item3, T item4, T item5) {
        ObjectHelper.requireNonNull(item1, "item1 is null");
        ObjectHelper.requireNonNull(item2, "item2 is null");
        ObjectHelper.requireNonNull(item3, "item3 is null");
        ObjectHelper.requireNonNull(item4, "item4 is null");
        ObjectHelper.requireNonNull(item5, "item5 is null");
        return fromArray(item1, item2, item3, item4, item5);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> just(T item1, T item2, T item3, T item4, T item5, T item6) {
        ObjectHelper.requireNonNull(item1, "item1 is null");
        ObjectHelper.requireNonNull(item2, "item2 is null");
        ObjectHelper.requireNonNull(item3, "item3 is null");
        ObjectHelper.requireNonNull(item4, "item4 is null");
        ObjectHelper.requireNonNull(item5, "item5 is null");
        ObjectHelper.requireNonNull(item6, "item6 is null");
        return fromArray(item1, item2, item3, item4, item5, item6);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> just(T item1, T item2, T item3, T item4, T item5, T item6, T item7) {
        ObjectHelper.requireNonNull(item1, "item1 is null");
        ObjectHelper.requireNonNull(item2, "item2 is null");
        ObjectHelper.requireNonNull(item3, "item3 is null");
        ObjectHelper.requireNonNull(item4, "item4 is null");
        ObjectHelper.requireNonNull(item5, "item5 is null");
        ObjectHelper.requireNonNull(item6, "item6 is null");
        ObjectHelper.requireNonNull(item7, "item7 is null");
        return fromArray(item1, item2, item3, item4, item5, item6, item7);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> just(T item1, T item2, T item3, T item4, T item5, T item6, T item7, T item8) {
        ObjectHelper.requireNonNull(item1, "item1 is null");
        ObjectHelper.requireNonNull(item2, "item2 is null");
        ObjectHelper.requireNonNull(item3, "item3 is null");
        ObjectHelper.requireNonNull(item4, "item4 is null");
        ObjectHelper.requireNonNull(item5, "item5 is null");
        ObjectHelper.requireNonNull(item6, "item6 is null");
        ObjectHelper.requireNonNull(item7, "item7 is null");
        ObjectHelper.requireNonNull(item8, "item8 is null");
        return fromArray(item1, item2, item3, item4, item5, item6, item7, item8);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> just(T item1, T item2, T item3, T item4, T item5, T item6, T item7, T item8, T item9) {
        ObjectHelper.requireNonNull(item1, "item1 is null");
        ObjectHelper.requireNonNull(item2, "item2 is null");
        ObjectHelper.requireNonNull(item3, "item3 is null");
        ObjectHelper.requireNonNull(item4, "item4 is null");
        ObjectHelper.requireNonNull(item5, "item5 is null");
        ObjectHelper.requireNonNull(item6, "item6 is null");
        ObjectHelper.requireNonNull(item7, "item7 is null");
        ObjectHelper.requireNonNull(item8, "item8 is null");
        ObjectHelper.requireNonNull(item9, "item9 is null");
        return fromArray(item1, item2, item3, item4, item5, item6, item7, item8, item9);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> just(T item1, T item2, T item3, T item4, T item5, T item6, T item7, T item8, T item9, T item10) {
        ObjectHelper.requireNonNull(item1, "item1 is null");
        ObjectHelper.requireNonNull(item2, "item2 is null");
        ObjectHelper.requireNonNull(item3, "item3 is null");
        ObjectHelper.requireNonNull(item4, "item4 is null");
        ObjectHelper.requireNonNull(item5, "item5 is null");
        ObjectHelper.requireNonNull(item6, "item6 is null");
        ObjectHelper.requireNonNull(item7, "item7 is null");
        ObjectHelper.requireNonNull(item8, "item8 is null");
        ObjectHelper.requireNonNull(item9, "item9 is null");
        ObjectHelper.requireNonNull(item10, "item10 is null");
        return fromArray(item1, item2, item3, item4, item5, item6, item7, item8, item9, item10);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> merge(Iterable<? extends ObservableSource<? extends T>> sources, int maxConcurrency, int bufferSize) {
        return fromIterable(sources).flatMap(Functions.identity(), false, maxConcurrency, bufferSize);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> mergeArray(int maxConcurrency, int bufferSize, ObservableSource<? extends T>... sources) {
        return fromArray(sources).flatMap(Functions.identity(), false, maxConcurrency, bufferSize);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> merge(Iterable<? extends ObservableSource<? extends T>> sources) {
        return fromIterable(sources).flatMap(Functions.identity());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> merge(Iterable<? extends ObservableSource<? extends T>> sources, int maxConcurrency) {
        return fromIterable(sources).flatMap(Functions.identity(), maxConcurrency);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> merge(ObservableSource<? extends ObservableSource<? extends T>> sources) {
        ObjectHelper.requireNonNull(sources, "sources is null");
        return RxJavaPlugins.onAssembly(new ObservableFlatMap(sources, Functions.identity(), false, Integer.MAX_VALUE, bufferSize()));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> merge(ObservableSource<? extends ObservableSource<? extends T>> sources, int maxConcurrency) {
        ObjectHelper.requireNonNull(sources, "sources is null");
        ObjectHelper.verifyPositive(maxConcurrency, "maxConcurrency");
        return RxJavaPlugins.onAssembly(new ObservableFlatMap(sources, Functions.identity(), false, maxConcurrency, bufferSize()));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> merge(ObservableSource<? extends T> source1, ObservableSource<? extends T> source2) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        return fromArray(source1, source2).flatMap(Functions.identity(), false, 2);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> merge(ObservableSource<? extends T> source1, ObservableSource<? extends T> source2, ObservableSource<? extends T> source3) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(source3, "source3 is null");
        return fromArray(source1, source2, source3).flatMap(Functions.identity(), false, 3);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> merge(ObservableSource<? extends T> source1, ObservableSource<? extends T> source2, ObservableSource<? extends T> source3, ObservableSource<? extends T> source4) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(source3, "source3 is null");
        ObjectHelper.requireNonNull(source4, "source4 is null");
        return fromArray(source1, source2, source3, source4).flatMap(Functions.identity(), false, 4);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> mergeArray(ObservableSource<? extends T>... sources) {
        return fromArray(sources).flatMap(Functions.identity(), sources.length);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> mergeDelayError(Iterable<? extends ObservableSource<? extends T>> sources) {
        return fromIterable(sources).flatMap(Functions.identity(), true);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> mergeDelayError(Iterable<? extends ObservableSource<? extends T>> sources, int maxConcurrency, int bufferSize) {
        return fromIterable(sources).flatMap(Functions.identity(), true, maxConcurrency, bufferSize);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> mergeArrayDelayError(int maxConcurrency, int bufferSize, ObservableSource<? extends T>... sources) {
        return fromArray(sources).flatMap(Functions.identity(), true, maxConcurrency, bufferSize);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> mergeDelayError(Iterable<? extends ObservableSource<? extends T>> sources, int maxConcurrency) {
        return fromIterable(sources).flatMap(Functions.identity(), true, maxConcurrency);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> mergeDelayError(ObservableSource<? extends ObservableSource<? extends T>> sources) {
        ObjectHelper.requireNonNull(sources, "sources is null");
        return RxJavaPlugins.onAssembly(new ObservableFlatMap(sources, Functions.identity(), true, Integer.MAX_VALUE, bufferSize()));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> mergeDelayError(ObservableSource<? extends ObservableSource<? extends T>> sources, int maxConcurrency) {
        ObjectHelper.requireNonNull(sources, "sources is null");
        ObjectHelper.verifyPositive(maxConcurrency, "maxConcurrency");
        return RxJavaPlugins.onAssembly(new ObservableFlatMap(sources, Functions.identity(), true, maxConcurrency, bufferSize()));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> mergeDelayError(ObservableSource<? extends T> source1, ObservableSource<? extends T> source2) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        return fromArray(source1, source2).flatMap(Functions.identity(), true, 2);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> mergeDelayError(ObservableSource<? extends T> source1, ObservableSource<? extends T> source2, ObservableSource<? extends T> source3) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(source3, "source3 is null");
        return fromArray(source1, source2, source3).flatMap(Functions.identity(), true, 3);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> mergeDelayError(ObservableSource<? extends T> source1, ObservableSource<? extends T> source2, ObservableSource<? extends T> source3, ObservableSource<? extends T> source4) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(source3, "source3 is null");
        ObjectHelper.requireNonNull(source4, "source4 is null");
        return fromArray(source1, source2, source3, source4).flatMap(Functions.identity(), true, 4);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> mergeArrayDelayError(ObservableSource<? extends T>... sources) {
        return fromArray(sources).flatMap(Functions.identity(), true, sources.length);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> never() {
        return RxJavaPlugins.onAssembly(ObservableNever.INSTANCE);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static Observable<Integer> range(int start, int count) {
        if (count < 0) {
            throw new IllegalArgumentException("count >= 0 required but it was " + count);
        } else if (count == 0) {
            return empty();
        } else {
            if (count == 1) {
                return just(Integer.valueOf(start));
            }
            if (((long) start) + ((long) (count - 1)) <= 2147483647L) {
                return RxJavaPlugins.onAssembly(new ObservableRange(start, count));
            }
            throw new IllegalArgumentException("Integer overflow");
        }
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static Observable<Long> rangeLong(long start, long count) {
        if (count < 0) {
            throw new IllegalArgumentException("count >= 0 required but it was " + count);
        } else if (count == 0) {
            return empty();
        } else {
            if (count == 1) {
                return just(Long.valueOf(start));
            }
            long end = (count - 1) + start;
            if (start <= 0 || end >= 0) {
                return RxJavaPlugins.onAssembly(new ObservableRangeLong(start, count));
            }
            throw new IllegalArgumentException("Overflow! start + count is bigger than Long.MAX_VALUE");
        }
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Single<Boolean> sequenceEqual(ObservableSource<? extends T> source1, ObservableSource<? extends T> source2) {
        return sequenceEqual(source1, source2, ObjectHelper.equalsPredicate(), bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Single<Boolean> sequenceEqual(ObservableSource<? extends T> source1, ObservableSource<? extends T> source2, BiPredicate<? super T, ? super T> isEqual) {
        return sequenceEqual(source1, source2, isEqual, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Single<Boolean> sequenceEqual(ObservableSource<? extends T> source1, ObservableSource<? extends T> source2, BiPredicate<? super T, ? super T> isEqual, int bufferSize) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(isEqual, "isEqual is null");
        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        return RxJavaPlugins.onAssembly(new ObservableSequenceEqualSingle(source1, source2, isEqual, bufferSize));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Single<Boolean> sequenceEqual(ObservableSource<? extends T> source1, ObservableSource<? extends T> source2, int bufferSize) {
        return sequenceEqual(source1, source2, ObjectHelper.equalsPredicate(), bufferSize);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> switchOnNext(ObservableSource<? extends ObservableSource<? extends T>> sources, int bufferSize) {
        ObjectHelper.requireNonNull(sources, "sources is null");
        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        return RxJavaPlugins.onAssembly(new ObservableSwitchMap(sources, Functions.identity(), bufferSize, false));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> switchOnNext(ObservableSource<? extends ObservableSource<? extends T>> sources) {
        return switchOnNext(sources, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> switchOnNextDelayError(ObservableSource<? extends ObservableSource<? extends T>> sources) {
        return switchOnNextDelayError(sources, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> switchOnNextDelayError(ObservableSource<? extends ObservableSource<? extends T>> sources, int prefetch) {
        ObjectHelper.requireNonNull(sources, "sources is null");
        ObjectHelper.verifyPositive(prefetch, "prefetch");
        return RxJavaPlugins.onAssembly(new ObservableSwitchMap(sources, Functions.identity(), prefetch, true));
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public static Observable<Long> timer(long delay, TimeUnit unit) {
        return timer(delay, unit, Schedulers.computation());
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public static Observable<Long> timer(long delay, TimeUnit unit, Scheduler scheduler) {
        ObjectHelper.requireNonNull(unit, "unit is null");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new ObservableTimer(Math.max(delay, 0), unit, scheduler));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> unsafeCreate(ObservableSource<T> onSubscribe) {
        ObjectHelper.requireNonNull(onSubscribe, "onSubscribe is null");
        if (!(onSubscribe instanceof Observable)) {
            return RxJavaPlugins.onAssembly(new ObservableFromUnsafeSource(onSubscribe));
        }
        throw new IllegalArgumentException("unsafeCreate(Observable) should be upgraded");
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T, D> Observable<T> using(Callable<? extends D> resourceSupplier, Function<? super D, ? extends ObservableSource<? extends T>> sourceSupplier, Consumer<? super D> disposer) {
        return using(resourceSupplier, sourceSupplier, disposer, true);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T, D> Observable<T> using(Callable<? extends D> resourceSupplier, Function<? super D, ? extends ObservableSource<? extends T>> sourceSupplier, Consumer<? super D> disposer, boolean eager) {
        ObjectHelper.requireNonNull(resourceSupplier, "resourceSupplier is null");
        ObjectHelper.requireNonNull(sourceSupplier, "sourceSupplier is null");
        ObjectHelper.requireNonNull(disposer, "disposer is null");
        return RxJavaPlugins.onAssembly(new ObservableUsing(resourceSupplier, sourceSupplier, disposer, eager));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T> Observable<T> wrap(ObservableSource<T> source) {
        ObjectHelper.requireNonNull(source, "source is null");
        if (source instanceof Observable) {
            return RxJavaPlugins.onAssembly((Observable) source);
        }
        return RxJavaPlugins.onAssembly(new ObservableFromUnsafeSource(source));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T, R> Observable<R> zip(Iterable<? extends ObservableSource<? extends T>> sources, Function<? super Object[], ? extends R> zipper) {
        ObjectHelper.requireNonNull(zipper, "zipper is null");
        ObjectHelper.requireNonNull(sources, "sources is null");
        return RxJavaPlugins.onAssembly(new ObservableZip((ObservableSource<? extends T>[]) null, sources, zipper, bufferSize(), false));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T, R> Observable<R> zip(ObservableSource<? extends ObservableSource<? extends T>> sources, Function<? super Object[], ? extends R> zipper) {
        ObjectHelper.requireNonNull(zipper, "zipper is null");
        ObjectHelper.requireNonNull(sources, "sources is null");
        return RxJavaPlugins.onAssembly(new ObservableToList(sources, 16).flatMap(ObservableInternalHelper.zipIterable(zipper)));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T1, T2, R> Observable<R> zip(ObservableSource<? extends T1> source1, ObservableSource<? extends T2> source2, BiFunction<? super T1, ? super T2, ? extends R> zipper) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        return zipArray(Functions.toFunction(zipper), false, bufferSize(), source1, source2);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T1, T2, R> Observable<R> zip(ObservableSource<? extends T1> source1, ObservableSource<? extends T2> source2, BiFunction<? super T1, ? super T2, ? extends R> zipper, boolean delayError) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        return zipArray(Functions.toFunction(zipper), delayError, bufferSize(), source1, source2);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T1, T2, R> Observable<R> zip(ObservableSource<? extends T1> source1, ObservableSource<? extends T2> source2, BiFunction<? super T1, ? super T2, ? extends R> zipper, boolean delayError, int bufferSize) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        return zipArray(Functions.toFunction(zipper), delayError, bufferSize, source1, source2);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T1, T2, T3, R> Observable<R> zip(ObservableSource<? extends T1> source1, ObservableSource<? extends T2> source2, ObservableSource<? extends T3> source3, Function3<? super T1, ? super T2, ? super T3, ? extends R> zipper) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(source3, "source3 is null");
        return zipArray(Functions.toFunction(zipper), false, bufferSize(), source1, source2, source3);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T1, T2, T3, T4, R> Observable<R> zip(ObservableSource<? extends T1> source1, ObservableSource<? extends T2> source2, ObservableSource<? extends T3> source3, ObservableSource<? extends T4> source4, Function4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> zipper) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(source3, "source3 is null");
        ObjectHelper.requireNonNull(source4, "source4 is null");
        return zipArray(Functions.toFunction(zipper), false, bufferSize(), source1, source2, source3, source4);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T1, T2, T3, T4, T5, R> Observable<R> zip(ObservableSource<? extends T1> source1, ObservableSource<? extends T2> source2, ObservableSource<? extends T3> source3, ObservableSource<? extends T4> source4, ObservableSource<? extends T5> source5, Function5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> zipper) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(source3, "source3 is null");
        ObjectHelper.requireNonNull(source4, "source4 is null");
        ObjectHelper.requireNonNull(source5, "source5 is null");
        return zipArray(Functions.toFunction(zipper), false, bufferSize(), source1, source2, source3, source4, source5);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T1, T2, T3, T4, T5, T6, R> Observable<R> zip(ObservableSource<? extends T1> source1, ObservableSource<? extends T2> source2, ObservableSource<? extends T3> source3, ObservableSource<? extends T4> source4, ObservableSource<? extends T5> source5, ObservableSource<? extends T6> source6, Function6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> zipper) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(source3, "source3 is null");
        ObjectHelper.requireNonNull(source4, "source4 is null");
        ObjectHelper.requireNonNull(source5, "source5 is null");
        ObjectHelper.requireNonNull(source6, "source6 is null");
        return zipArray(Functions.toFunction(zipper), false, bufferSize(), source1, source2, source3, source4, source5, source6);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T1, T2, T3, T4, T5, T6, T7, R> Observable<R> zip(ObservableSource<? extends T1> source1, ObservableSource<? extends T2> source2, ObservableSource<? extends T3> source3, ObservableSource<? extends T4> source4, ObservableSource<? extends T5> source5, ObservableSource<? extends T6> source6, ObservableSource<? extends T7> source7, Function7<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> zipper) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(source3, "source3 is null");
        ObjectHelper.requireNonNull(source4, "source4 is null");
        ObjectHelper.requireNonNull(source5, "source5 is null");
        ObjectHelper.requireNonNull(source6, "source6 is null");
        ObjectHelper.requireNonNull(source7, "source7 is null");
        return zipArray(Functions.toFunction(zipper), false, bufferSize(), source1, source2, source3, source4, source5, source6, source7);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Observable<R> zip(ObservableSource<? extends T1> source1, ObservableSource<? extends T2> source2, ObservableSource<? extends T3> source3, ObservableSource<? extends T4> source4, ObservableSource<? extends T5> source5, ObservableSource<? extends T6> source6, ObservableSource<? extends T7> source7, ObservableSource<? extends T8> source8, Function8<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> zipper) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(source3, "source3 is null");
        ObjectHelper.requireNonNull(source4, "source4 is null");
        ObjectHelper.requireNonNull(source5, "source5 is null");
        ObjectHelper.requireNonNull(source6, "source6 is null");
        ObjectHelper.requireNonNull(source7, "source7 is null");
        ObjectHelper.requireNonNull(source8, "source8 is null");
        return zipArray(Functions.toFunction(zipper), false, bufferSize(), source1, source2, source3, source4, source5, source6, source7, source8);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Observable<R> zip(ObservableSource<? extends T1> source1, ObservableSource<? extends T2> source2, ObservableSource<? extends T3> source3, ObservableSource<? extends T4> source4, ObservableSource<? extends T5> source5, ObservableSource<? extends T6> source6, ObservableSource<? extends T7> source7, ObservableSource<? extends T8> source8, ObservableSource<? extends T9> source9, Function9<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? extends R> zipper) {
        ObjectHelper.requireNonNull(source1, "source1 is null");
        ObjectHelper.requireNonNull(source2, "source2 is null");
        ObjectHelper.requireNonNull(source3, "source3 is null");
        ObjectHelper.requireNonNull(source4, "source4 is null");
        ObjectHelper.requireNonNull(source5, "source5 is null");
        ObjectHelper.requireNonNull(source6, "source6 is null");
        ObjectHelper.requireNonNull(source7, "source7 is null");
        ObjectHelper.requireNonNull(source8, "source8 is null");
        ObjectHelper.requireNonNull(source9, "source9 is null");
        return zipArray(Functions.toFunction(zipper), false, bufferSize(), source1, source2, source3, source4, source5, source6, source7, source8, source9);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T, R> Observable<R> zipArray(Function<? super Object[], ? extends R> zipper, boolean delayError, int bufferSize, ObservableSource<? extends T>... sources) {
        if (sources.length == 0) {
            return empty();
        }
        ObjectHelper.requireNonNull(zipper, "zipper is null");
        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        return RxJavaPlugins.onAssembly(new ObservableZip(sources, (Iterable) null, zipper, bufferSize, delayError));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public static <T, R> Observable<R> zipIterable(Iterable<? extends ObservableSource<? extends T>> sources, Function<? super Object[], ? extends R> zipper, boolean delayError, int bufferSize) {
        ObjectHelper.requireNonNull(zipper, "zipper is null");
        ObjectHelper.requireNonNull(sources, "sources is null");
        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        return RxJavaPlugins.onAssembly(new ObservableZip((ObservableSource<? extends T>[]) null, sources, zipper, bufferSize, delayError));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Single<Boolean> all(Predicate<? super T> predicate) {
        ObjectHelper.requireNonNull(predicate, "predicate is null");
        return RxJavaPlugins.onAssembly(new ObservableAllSingle(this, predicate));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> ambWith(ObservableSource<? extends T> other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return ambArray(this, other);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Single<Boolean> any(Predicate<? super T> predicate) {
        ObjectHelper.requireNonNull(predicate, "predicate is null");
        return RxJavaPlugins.onAssembly(new ObservableAnySingle(this, predicate));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> R as(ObservableConverter<T, ? extends R> converter) {
        return ((ObservableConverter) ObjectHelper.requireNonNull(converter, "converter is null")).apply(this);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final T blockingFirst() {
        BlockingFirstObserver<T> observer = new BlockingFirstObserver<>();
        subscribe(observer);
        T v = observer.blockingGet();
        if (v != null) {
            return v;
        }
        throw new NoSuchElementException();
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final T blockingFirst(T defaultItem) {
        BlockingFirstObserver<T> observer = new BlockingFirstObserver<>();
        subscribe(observer);
        T v = observer.blockingGet();
        return v != null ? v : defaultItem;
    }

    @SchedulerSupport("none")
    public final void blockingForEach(Consumer<? super T> onNext) {
        Iterator<T> it = blockingIterable().iterator();
        while (it.hasNext()) {
            try {
                onNext.accept(it.next());
            } catch (Throwable e) {
                Exceptions.throwIfFatal(e);
                ((Disposable) it).dispose();
                throw ExceptionHelper.wrapOrThrow(e);
            }
        }
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Iterable<T> blockingIterable() {
        return blockingIterable(bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Iterable<T> blockingIterable(int bufferSize) {
        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        return new BlockingObservableIterable(this, bufferSize);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final T blockingLast() {
        BlockingLastObserver<T> observer = new BlockingLastObserver<>();
        subscribe(observer);
        T v = observer.blockingGet();
        if (v != null) {
            return v;
        }
        throw new NoSuchElementException();
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final T blockingLast(T defaultItem) {
        BlockingLastObserver<T> observer = new BlockingLastObserver<>();
        subscribe(observer);
        T v = observer.blockingGet();
        return v != null ? v : defaultItem;
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Iterable<T> blockingLatest() {
        return new BlockingObservableLatest(this);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Iterable<T> blockingMostRecent(T initialValue) {
        return new BlockingObservableMostRecent(this, initialValue);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Iterable<T> blockingNext() {
        return new BlockingObservableNext(this);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final T blockingSingle() {
        T v = singleElement().blockingGet();
        if (v != null) {
            return v;
        }
        throw new NoSuchElementException();
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final T blockingSingle(T defaultItem) {
        return single(defaultItem).blockingGet();
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Future<T> toFuture() {
        return (Future) subscribeWith(new FutureObserver());
    }

    @SchedulerSupport("none")
    public final void blockingSubscribe() {
        ObservableBlockingSubscribe.subscribe(this);
    }

    @SchedulerSupport("none")
    public final void blockingSubscribe(Consumer<? super T> onNext) {
        ObservableBlockingSubscribe.subscribe(this, onNext, Functions.ON_ERROR_MISSING, Functions.EMPTY_ACTION);
    }

    @SchedulerSupport("none")
    public final void blockingSubscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError) {
        ObservableBlockingSubscribe.subscribe(this, onNext, onError, Functions.EMPTY_ACTION);
    }

    @SchedulerSupport("none")
    public final void blockingSubscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError, Action onComplete) {
        ObservableBlockingSubscribe.subscribe(this, onNext, onError, onComplete);
    }

    @SchedulerSupport("none")
    public final void blockingSubscribe(Observer<? super T> observer) {
        ObservableBlockingSubscribe.subscribe(this, observer);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<List<T>> buffer(int count) {
        return buffer(count, count);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<List<T>> buffer(int count, int skip) {
        return buffer(count, skip, ArrayListSupplier.asCallable());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <U extends Collection<? super T>> Observable<U> buffer(int count, int skip, Callable<U> bufferSupplier) {
        ObjectHelper.verifyPositive(count, "count");
        ObjectHelper.verifyPositive(skip, "skip");
        ObjectHelper.requireNonNull(bufferSupplier, "bufferSupplier is null");
        return RxJavaPlugins.onAssembly(new ObservableBuffer(this, count, skip, bufferSupplier));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <U extends Collection<? super T>> Observable<U> buffer(int count, Callable<U> bufferSupplier) {
        return buffer(count, count, bufferSupplier);
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public final Observable<List<T>> buffer(long timespan, long timeskip, TimeUnit unit) {
        return buffer(timespan, timeskip, unit, Schedulers.computation(), ArrayListSupplier.asCallable());
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<List<T>> buffer(long timespan, long timeskip, TimeUnit unit, Scheduler scheduler) {
        return buffer(timespan, timeskip, unit, scheduler, ArrayListSupplier.asCallable());
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final <U extends Collection<? super T>> Observable<U> buffer(long timespan, long timeskip, TimeUnit unit, Scheduler scheduler, Callable<U> bufferSupplier) {
        ObjectHelper.requireNonNull(unit, "unit is null");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        ObjectHelper.requireNonNull(bufferSupplier, "bufferSupplier is null");
        return RxJavaPlugins.onAssembly(new ObservableBufferTimed(this, timespan, timeskip, unit, scheduler, bufferSupplier, Integer.MAX_VALUE, false));
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public final Observable<List<T>> buffer(long timespan, TimeUnit unit) {
        return buffer(timespan, unit, Schedulers.computation(), Integer.MAX_VALUE);
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public final Observable<List<T>> buffer(long timespan, TimeUnit unit, int count) {
        return buffer(timespan, unit, Schedulers.computation(), count);
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<List<T>> buffer(long timespan, TimeUnit unit, Scheduler scheduler, int count) {
        return buffer(timespan, unit, scheduler, count, ArrayListSupplier.asCallable(), false);
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final <U extends Collection<? super T>> Observable<U> buffer(long timespan, TimeUnit unit, Scheduler scheduler, int count, Callable<U> bufferSupplier, boolean restartTimerOnMaxSize) {
        ObjectHelper.requireNonNull(unit, "unit is null");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        ObjectHelper.requireNonNull(bufferSupplier, "bufferSupplier is null");
        ObjectHelper.verifyPositive(count, "count");
        return RxJavaPlugins.onAssembly(new ObservableBufferTimed(this, timespan, timespan, unit, scheduler, bufferSupplier, count, restartTimerOnMaxSize));
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<List<T>> buffer(long timespan, TimeUnit unit, Scheduler scheduler) {
        return buffer(timespan, unit, scheduler, Integer.MAX_VALUE, ArrayListSupplier.asCallable(), false);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <TOpening, TClosing> Observable<List<T>> buffer(ObservableSource<? extends TOpening> openingIndicator, Function<? super TOpening, ? extends ObservableSource<? extends TClosing>> closingIndicator) {
        return buffer(openingIndicator, closingIndicator, ArrayListSupplier.asCallable());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <TOpening, TClosing, U extends Collection<? super T>> Observable<U> buffer(ObservableSource<? extends TOpening> openingIndicator, Function<? super TOpening, ? extends ObservableSource<? extends TClosing>> closingIndicator, Callable<U> bufferSupplier) {
        ObjectHelper.requireNonNull(openingIndicator, "openingIndicator is null");
        ObjectHelper.requireNonNull(closingIndicator, "closingIndicator is null");
        ObjectHelper.requireNonNull(bufferSupplier, "bufferSupplier is null");
        return RxJavaPlugins.onAssembly(new ObservableBufferBoundary(this, openingIndicator, closingIndicator, bufferSupplier));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <B> Observable<List<T>> buffer(ObservableSource<B> boundary) {
        return buffer(boundary, ArrayListSupplier.asCallable());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <B> Observable<List<T>> buffer(ObservableSource<B> boundary, int initialCapacity) {
        ObjectHelper.verifyPositive(initialCapacity, "initialCapacity");
        return buffer(boundary, Functions.createArrayList(initialCapacity));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <B, U extends Collection<? super T>> Observable<U> buffer(ObservableSource<B> boundary, Callable<U> bufferSupplier) {
        ObjectHelper.requireNonNull(boundary, "boundary is null");
        ObjectHelper.requireNonNull(bufferSupplier, "bufferSupplier is null");
        return RxJavaPlugins.onAssembly(new ObservableBufferExactBoundary(this, boundary, bufferSupplier));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <B> Observable<List<T>> buffer(Callable<? extends ObservableSource<B>> boundarySupplier) {
        return buffer(boundarySupplier, ArrayListSupplier.asCallable());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <B, U extends Collection<? super T>> Observable<U> buffer(Callable<? extends ObservableSource<B>> boundarySupplier, Callable<U> bufferSupplier) {
        ObjectHelper.requireNonNull(boundarySupplier, "boundarySupplier is null");
        ObjectHelper.requireNonNull(bufferSupplier, "bufferSupplier is null");
        return RxJavaPlugins.onAssembly(new ObservableBufferBoundarySupplier(this, boundarySupplier, bufferSupplier));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> cache() {
        return cacheWithInitialCapacity(16);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> cacheWithInitialCapacity(int initialCapacity) {
        ObjectHelper.verifyPositive(initialCapacity, "initialCapacity");
        return RxJavaPlugins.onAssembly(new ObservableCache(this, initialCapacity));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <U> Observable<U> cast(Class<U> clazz) {
        ObjectHelper.requireNonNull(clazz, "clazz is null");
        return map(Functions.castFunction(clazz));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <U> Single<U> collect(Callable<? extends U> initialValueSupplier, BiConsumer<? super U, ? super T> collector) {
        ObjectHelper.requireNonNull(initialValueSupplier, "initialValueSupplier is null");
        ObjectHelper.requireNonNull(collector, "collector is null");
        return RxJavaPlugins.onAssembly(new ObservableCollectSingle(this, initialValueSupplier, collector));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <U> Single<U> collectInto(U initialValue, BiConsumer<? super U, ? super T> collector) {
        ObjectHelper.requireNonNull(initialValue, "initialValue is null");
        return collect(Functions.justCallable(initialValue), collector);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> compose(ObservableTransformer<? super T, ? extends R> composer) {
        return wrap(((ObservableTransformer) ObjectHelper.requireNonNull(composer, "composer is null")).apply(this));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> concatMap(Function<? super T, ? extends ObservableSource<? extends R>> mapper) {
        return concatMap(mapper, 2);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> concatMap(Function<? super T, ? extends ObservableSource<? extends R>> mapper, int prefetch) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        ObjectHelper.verifyPositive(prefetch, "prefetch");
        if (!(this instanceof ScalarCallable)) {
            return RxJavaPlugins.onAssembly(new ObservableConcatMap(this, mapper, prefetch, ErrorMode.IMMEDIATE));
        }
        T v = ((ScalarCallable) this).call();
        if (v == null) {
            return empty();
        }
        return ObservableScalarXMap.scalarXMap(v, mapper);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> concatMapDelayError(Function<? super T, ? extends ObservableSource<? extends R>> mapper) {
        return concatMapDelayError(mapper, bufferSize(), true);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> concatMapDelayError(Function<? super T, ? extends ObservableSource<? extends R>> mapper, int prefetch, boolean tillTheEnd) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        ObjectHelper.verifyPositive(prefetch, "prefetch");
        if (this instanceof ScalarCallable) {
            T v = ((ScalarCallable) this).call();
            if (v == null) {
                return empty();
            }
            return ObservableScalarXMap.scalarXMap(v, mapper);
        }
        return RxJavaPlugins.onAssembly(new ObservableConcatMap(this, mapper, prefetch, tillTheEnd ? ErrorMode.END : ErrorMode.BOUNDARY));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> concatMapEager(Function<? super T, ? extends ObservableSource<? extends R>> mapper) {
        return concatMapEager(mapper, Integer.MAX_VALUE, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> concatMapEager(Function<? super T, ? extends ObservableSource<? extends R>> mapper, int maxConcurrency, int prefetch) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        ObjectHelper.verifyPositive(maxConcurrency, "maxConcurrency");
        ObjectHelper.verifyPositive(prefetch, "prefetch");
        return RxJavaPlugins.onAssembly(new ObservableConcatMapEager(this, mapper, ErrorMode.IMMEDIATE, maxConcurrency, prefetch));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> concatMapEagerDelayError(Function<? super T, ? extends ObservableSource<? extends R>> mapper, boolean tillTheEnd) {
        return concatMapEagerDelayError(mapper, Integer.MAX_VALUE, bufferSize(), tillTheEnd);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> concatMapEagerDelayError(Function<? super T, ? extends ObservableSource<? extends R>> mapper, int maxConcurrency, int prefetch, boolean tillTheEnd) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        ObjectHelper.verifyPositive(maxConcurrency, "maxConcurrency");
        ObjectHelper.verifyPositive(prefetch, "prefetch");
        return RxJavaPlugins.onAssembly(new ObservableConcatMapEager(this, mapper, tillTheEnd ? ErrorMode.END : ErrorMode.BOUNDARY, maxConcurrency, prefetch));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable concatMapCompletable(Function<? super T, ? extends CompletableSource> mapper) {
        return concatMapCompletable(mapper, 2);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable concatMapCompletable(Function<? super T, ? extends CompletableSource> mapper, int capacityHint) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        ObjectHelper.verifyPositive(capacityHint, "capacityHint");
        return RxJavaPlugins.onAssembly((Completable) new ObservableConcatMapCompletable(this, mapper, ErrorMode.IMMEDIATE, capacityHint));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable concatMapCompletableDelayError(Function<? super T, ? extends CompletableSource> mapper) {
        return concatMapCompletableDelayError(mapper, true, 2);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable concatMapCompletableDelayError(Function<? super T, ? extends CompletableSource> mapper, boolean tillTheEnd) {
        return concatMapCompletableDelayError(mapper, tillTheEnd, 2);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable concatMapCompletableDelayError(Function<? super T, ? extends CompletableSource> mapper, boolean tillTheEnd, int prefetch) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        ObjectHelper.verifyPositive(prefetch, "prefetch");
        return RxJavaPlugins.onAssembly((Completable) new ObservableConcatMapCompletable(this, mapper, tillTheEnd ? ErrorMode.END : ErrorMode.BOUNDARY, prefetch));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <U> Observable<U> concatMapIterable(Function<? super T, ? extends Iterable<? extends U>> mapper) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        return RxJavaPlugins.onAssembly(new ObservableFlattenIterable(this, mapper));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <U> Observable<U> concatMapIterable(Function<? super T, ? extends Iterable<? extends U>> mapper, int prefetch) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        ObjectHelper.verifyPositive(prefetch, "prefetch");
        return concatMap(ObservableInternalHelper.flatMapIntoIterable(mapper), prefetch);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> concatMapMaybe(Function<? super T, ? extends MaybeSource<? extends R>> mapper) {
        return concatMapMaybe(mapper, 2);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> concatMapMaybe(Function<? super T, ? extends MaybeSource<? extends R>> mapper, int prefetch) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        ObjectHelper.verifyPositive(prefetch, "prefetch");
        return RxJavaPlugins.onAssembly(new ObservableConcatMapMaybe(this, mapper, ErrorMode.IMMEDIATE, prefetch));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> concatMapMaybeDelayError(Function<? super T, ? extends MaybeSource<? extends R>> mapper) {
        return concatMapMaybeDelayError(mapper, true, 2);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> concatMapMaybeDelayError(Function<? super T, ? extends MaybeSource<? extends R>> mapper, boolean tillTheEnd) {
        return concatMapMaybeDelayError(mapper, tillTheEnd, 2);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> concatMapMaybeDelayError(Function<? super T, ? extends MaybeSource<? extends R>> mapper, boolean tillTheEnd, int prefetch) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        ObjectHelper.verifyPositive(prefetch, "prefetch");
        return RxJavaPlugins.onAssembly(new ObservableConcatMapMaybe(this, mapper, tillTheEnd ? ErrorMode.END : ErrorMode.BOUNDARY, prefetch));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> concatMapSingle(Function<? super T, ? extends SingleSource<? extends R>> mapper) {
        return concatMapSingle(mapper, 2);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> concatMapSingle(Function<? super T, ? extends SingleSource<? extends R>> mapper, int prefetch) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        ObjectHelper.verifyPositive(prefetch, "prefetch");
        return RxJavaPlugins.onAssembly(new ObservableConcatMapSingle(this, mapper, ErrorMode.IMMEDIATE, prefetch));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> concatMapSingleDelayError(Function<? super T, ? extends SingleSource<? extends R>> mapper) {
        return concatMapSingleDelayError(mapper, true, 2);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> concatMapSingleDelayError(Function<? super T, ? extends SingleSource<? extends R>> mapper, boolean tillTheEnd) {
        return concatMapSingleDelayError(mapper, tillTheEnd, 2);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> concatMapSingleDelayError(Function<? super T, ? extends SingleSource<? extends R>> mapper, boolean tillTheEnd, int prefetch) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        ObjectHelper.verifyPositive(prefetch, "prefetch");
        return RxJavaPlugins.onAssembly(new ObservableConcatMapSingle(this, mapper, tillTheEnd ? ErrorMode.END : ErrorMode.BOUNDARY, prefetch));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> concatWith(ObservableSource<? extends T> other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return concat(this, (Observable) other);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> concatWith(SingleSource<? extends T> other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return RxJavaPlugins.onAssembly(new ObservableConcatWithSingle(this, other));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> concatWith(MaybeSource<? extends T> other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return RxJavaPlugins.onAssembly(new ObservableConcatWithMaybe(this, other));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> concatWith(CompletableSource other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return RxJavaPlugins.onAssembly(new ObservableConcatWithCompletable(this, other));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Single<Boolean> contains(Object element) {
        ObjectHelper.requireNonNull(element, "element is null");
        return any(Functions.equalsWith(element));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Single<Long> count() {
        return RxJavaPlugins.onAssembly(new ObservableCountSingle(this));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <U> Observable<T> debounce(Function<? super T, ? extends ObservableSource<U>> debounceSelector) {
        ObjectHelper.requireNonNull(debounceSelector, "debounceSelector is null");
        return RxJavaPlugins.onAssembly(new ObservableDebounce(this, debounceSelector));
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public final Observable<T> debounce(long timeout, TimeUnit unit) {
        return debounce(timeout, unit, Schedulers.computation());
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<T> debounce(long timeout, TimeUnit unit, Scheduler scheduler) {
        ObjectHelper.requireNonNull(unit, "unit is null");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new ObservableDebounceTimed(this, timeout, unit, scheduler));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> defaultIfEmpty(T defaultItem) {
        ObjectHelper.requireNonNull(defaultItem, "defaultItem is null");
        return switchIfEmpty(just(defaultItem));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <U> Observable<T> delay(Function<? super T, ? extends ObservableSource<U>> itemDelay) {
        ObjectHelper.requireNonNull(itemDelay, "itemDelay is null");
        return flatMap(ObservableInternalHelper.itemDelay(itemDelay));
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public final Observable<T> delay(long delay, TimeUnit unit) {
        return delay(delay, unit, Schedulers.computation(), false);
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public final Observable<T> delay(long delay, TimeUnit unit, boolean delayError) {
        return delay(delay, unit, Schedulers.computation(), delayError);
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<T> delay(long delay, TimeUnit unit, Scheduler scheduler) {
        return delay(delay, unit, scheduler, false);
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<T> delay(long delay, TimeUnit unit, Scheduler scheduler, boolean delayError) {
        ObjectHelper.requireNonNull(unit, "unit is null");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new ObservableDelay(this, delay, unit, scheduler, delayError));
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [io.reactivex.functions.Function, io.reactivex.functions.Function<? super T, ? extends io.reactivex.ObservableSource<V>>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @io.reactivex.annotations.CheckReturnValue
    @io.reactivex.annotations.SchedulerSupport("none")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <U, V> io.reactivex.Observable<T> delay(io.reactivex.ObservableSource<U> r2, io.reactivex.functions.Function<? super T, ? extends io.reactivex.ObservableSource<V>> r3) {
        /*
            r1 = this;
            io.reactivex.Observable r0 = r1.delaySubscription(r2)
            io.reactivex.Observable r0 = r0.delay(r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.Observable.delay(io.reactivex.ObservableSource, io.reactivex.functions.Function):io.reactivex.Observable");
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <U> Observable<T> delaySubscription(ObservableSource<U> other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return RxJavaPlugins.onAssembly(new ObservableDelaySubscriptionOther(this, other));
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public final Observable<T> delaySubscription(long delay, TimeUnit unit) {
        return delaySubscription(delay, unit, Schedulers.computation());
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<T> delaySubscription(long delay, TimeUnit unit, Scheduler scheduler) {
        return delaySubscription(timer(delay, unit, scheduler));
    }

    @CheckReturnValue
    @Deprecated
    @SchedulerSupport("none")
    public final <T2> Observable<T2> dematerialize() {
        return RxJavaPlugins.onAssembly(new ObservableDematerialize(this, Functions.identity()));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> dematerialize(Function<? super T, Notification<R>> selector) {
        ObjectHelper.requireNonNull(selector, "selector is null");
        return RxJavaPlugins.onAssembly(new ObservableDematerialize(this, selector));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> distinct() {
        return distinct(Functions.identity(), Functions.createHashSet());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <K> Observable<T> distinct(Function<? super T, K> keySelector) {
        return distinct(keySelector, Functions.createHashSet());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <K> Observable<T> distinct(Function<? super T, K> keySelector, Callable<? extends Collection<? super K>> collectionSupplier) {
        ObjectHelper.requireNonNull(keySelector, "keySelector is null");
        ObjectHelper.requireNonNull(collectionSupplier, "collectionSupplier is null");
        return RxJavaPlugins.onAssembly(new ObservableDistinct(this, keySelector, collectionSupplier));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> distinctUntilChanged() {
        return distinctUntilChanged(Functions.identity());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <K> Observable<T> distinctUntilChanged(Function<? super T, K> keySelector) {
        ObjectHelper.requireNonNull(keySelector, "keySelector is null");
        return RxJavaPlugins.onAssembly(new ObservableDistinctUntilChanged(this, keySelector, ObjectHelper.equalsPredicate()));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> distinctUntilChanged(BiPredicate<? super T, ? super T> comparer) {
        ObjectHelper.requireNonNull(comparer, "comparer is null");
        return RxJavaPlugins.onAssembly(new ObservableDistinctUntilChanged(this, Functions.identity(), comparer));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> doAfterNext(Consumer<? super T> onAfterNext) {
        ObjectHelper.requireNonNull(onAfterNext, "onAfterNext is null");
        return RxJavaPlugins.onAssembly(new ObservableDoAfterNext(this, onAfterNext));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> doAfterTerminate(Action onFinally) {
        ObjectHelper.requireNonNull(onFinally, "onFinally is null");
        return doOnEach(Functions.emptyConsumer(), Functions.emptyConsumer(), Functions.EMPTY_ACTION, onFinally);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> doFinally(Action onFinally) {
        ObjectHelper.requireNonNull(onFinally, "onFinally is null");
        return RxJavaPlugins.onAssembly(new ObservableDoFinally(this, onFinally));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> doOnDispose(Action onDispose) {
        return doOnLifecycle(Functions.emptyConsumer(), onDispose);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> doOnComplete(Action onComplete) {
        return doOnEach(Functions.emptyConsumer(), Functions.emptyConsumer(), onComplete, Functions.EMPTY_ACTION);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    private Observable<T> doOnEach(Consumer<? super T> onNext, Consumer<? super Throwable> onError, Action onComplete, Action onAfterTerminate) {
        ObjectHelper.requireNonNull(onNext, "onNext is null");
        ObjectHelper.requireNonNull(onError, "onError is null");
        ObjectHelper.requireNonNull(onComplete, "onComplete is null");
        ObjectHelper.requireNonNull(onAfterTerminate, "onAfterTerminate is null");
        return RxJavaPlugins.onAssembly(new ObservableDoOnEach(this, onNext, onError, onComplete, onAfterTerminate));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> doOnEach(Consumer<? super Notification<T>> onNotification) {
        ObjectHelper.requireNonNull(onNotification, "onNotification is null");
        return doOnEach(Functions.notificationOnNext(onNotification), Functions.notificationOnError(onNotification), Functions.notificationOnComplete(onNotification), Functions.EMPTY_ACTION);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> doOnEach(Observer<? super T> observer) {
        ObjectHelper.requireNonNull(observer, "observer is null");
        return doOnEach(ObservableInternalHelper.observerOnNext(observer), ObservableInternalHelper.observerOnError(observer), ObservableInternalHelper.observerOnComplete(observer), Functions.EMPTY_ACTION);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> doOnError(Consumer<? super Throwable> onError) {
        return doOnEach(Functions.emptyConsumer(), onError, Functions.EMPTY_ACTION, Functions.EMPTY_ACTION);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> doOnLifecycle(Consumer<? super Disposable> onSubscribe, Action onDispose) {
        ObjectHelper.requireNonNull(onSubscribe, "onSubscribe is null");
        ObjectHelper.requireNonNull(onDispose, "onDispose is null");
        return RxJavaPlugins.onAssembly(new ObservableDoOnLifecycle(this, onSubscribe, onDispose));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> doOnNext(Consumer<? super T> onNext) {
        return doOnEach(onNext, Functions.emptyConsumer(), Functions.EMPTY_ACTION, Functions.EMPTY_ACTION);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> doOnSubscribe(Consumer<? super Disposable> onSubscribe) {
        return doOnLifecycle(onSubscribe, Functions.EMPTY_ACTION);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> doOnTerminate(Action onTerminate) {
        ObjectHelper.requireNonNull(onTerminate, "onTerminate is null");
        return doOnEach(Functions.emptyConsumer(), Functions.actionConsumer(onTerminate), onTerminate, Functions.EMPTY_ACTION);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Maybe<T> elementAt(long index) {
        if (index >= 0) {
            return RxJavaPlugins.onAssembly(new ObservableElementAtMaybe(this, index));
        }
        throw new IndexOutOfBoundsException("index >= 0 required but it was " + index);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Single<T> elementAt(long index, T defaultItem) {
        if (index >= 0) {
            ObjectHelper.requireNonNull(defaultItem, "defaultItem is null");
            return RxJavaPlugins.onAssembly(new ObservableElementAtSingle(this, index, defaultItem));
        }
        throw new IndexOutOfBoundsException("index >= 0 required but it was " + index);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Single<T> elementAtOrError(long index) {
        if (index >= 0) {
            return RxJavaPlugins.onAssembly(new ObservableElementAtSingle(this, index, null));
        }
        throw new IndexOutOfBoundsException("index >= 0 required but it was " + index);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> filter(Predicate<? super T> predicate) {
        ObjectHelper.requireNonNull(predicate, "predicate is null");
        return RxJavaPlugins.onAssembly(new ObservableFilter(this, predicate));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Maybe<T> firstElement() {
        return elementAt(0);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Single<T> first(T defaultItem) {
        return elementAt(0, defaultItem);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Single<T> firstOrError() {
        return elementAtOrError(0);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> flatMap(Function<? super T, ? extends ObservableSource<? extends R>> mapper) {
        return flatMap(mapper, false);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> flatMap(Function<? super T, ? extends ObservableSource<? extends R>> mapper, boolean delayErrors) {
        return flatMap(mapper, delayErrors, Integer.MAX_VALUE);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> flatMap(Function<? super T, ? extends ObservableSource<? extends R>> mapper, boolean delayErrors, int maxConcurrency) {
        return flatMap(mapper, delayErrors, maxConcurrency, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> flatMap(Function<? super T, ? extends ObservableSource<? extends R>> mapper, boolean delayErrors, int maxConcurrency, int bufferSize) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        ObjectHelper.verifyPositive(maxConcurrency, "maxConcurrency");
        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        if (!(this instanceof ScalarCallable)) {
            return RxJavaPlugins.onAssembly(new ObservableFlatMap(this, mapper, delayErrors, maxConcurrency, bufferSize));
        }
        T v = ((ScalarCallable) this).call();
        if (v == null) {
            return empty();
        }
        return ObservableScalarXMap.scalarXMap(v, mapper);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> flatMap(Function<? super T, ? extends ObservableSource<? extends R>> onNextMapper, Function<? super Throwable, ? extends ObservableSource<? extends R>> onErrorMapper, Callable<? extends ObservableSource<? extends R>> onCompleteSupplier) {
        ObjectHelper.requireNonNull(onNextMapper, "onNextMapper is null");
        ObjectHelper.requireNonNull(onErrorMapper, "onErrorMapper is null");
        ObjectHelper.requireNonNull(onCompleteSupplier, "onCompleteSupplier is null");
        return merge(new ObservableMapNotification(this, onNextMapper, onErrorMapper, onCompleteSupplier));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> flatMap(Function<? super T, ? extends ObservableSource<? extends R>> onNextMapper, Function<Throwable, ? extends ObservableSource<? extends R>> onErrorMapper, Callable<? extends ObservableSource<? extends R>> onCompleteSupplier, int maxConcurrency) {
        ObjectHelper.requireNonNull(onNextMapper, "onNextMapper is null");
        ObjectHelper.requireNonNull(onErrorMapper, "onErrorMapper is null");
        ObjectHelper.requireNonNull(onCompleteSupplier, "onCompleteSupplier is null");
        return merge(new ObservableMapNotification(this, onNextMapper, onErrorMapper, onCompleteSupplier), maxConcurrency);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> flatMap(Function<? super T, ? extends ObservableSource<? extends R>> mapper, int maxConcurrency) {
        return flatMap(mapper, false, maxConcurrency, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <U, R> Observable<R> flatMap(Function<? super T, ? extends ObservableSource<? extends U>> mapper, BiFunction<? super T, ? super U, ? extends R> resultSelector) {
        return flatMap(mapper, resultSelector, false, bufferSize(), bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <U, R> Observable<R> flatMap(Function<? super T, ? extends ObservableSource<? extends U>> mapper, BiFunction<? super T, ? super U, ? extends R> combiner, boolean delayErrors) {
        return flatMap(mapper, combiner, delayErrors, bufferSize(), bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <U, R> Observable<R> flatMap(Function<? super T, ? extends ObservableSource<? extends U>> mapper, BiFunction<? super T, ? super U, ? extends R> combiner, boolean delayErrors, int maxConcurrency) {
        return flatMap(mapper, combiner, delayErrors, maxConcurrency, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <U, R> Observable<R> flatMap(Function<? super T, ? extends ObservableSource<? extends U>> mapper, BiFunction<? super T, ? super U, ? extends R> combiner, boolean delayErrors, int maxConcurrency, int bufferSize) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        ObjectHelper.requireNonNull(combiner, "combiner is null");
        return flatMap(ObservableInternalHelper.flatMapWithCombiner(mapper, combiner), delayErrors, maxConcurrency, bufferSize);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <U, R> Observable<R> flatMap(Function<? super T, ? extends ObservableSource<? extends U>> mapper, BiFunction<? super T, ? super U, ? extends R> combiner, int maxConcurrency) {
        return flatMap(mapper, combiner, false, maxConcurrency, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable flatMapCompletable(Function<? super T, ? extends CompletableSource> mapper) {
        return flatMapCompletable(mapper, false);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable flatMapCompletable(Function<? super T, ? extends CompletableSource> mapper, boolean delayErrors) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        return RxJavaPlugins.onAssembly((Completable) new ObservableFlatMapCompletableCompletable(this, mapper, delayErrors));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <U> Observable<U> flatMapIterable(Function<? super T, ? extends Iterable<? extends U>> mapper) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        return RxJavaPlugins.onAssembly(new ObservableFlattenIterable(this, mapper));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <U, V> Observable<V> flatMapIterable(Function<? super T, ? extends Iterable<? extends U>> mapper, BiFunction<? super T, ? super U, ? extends V> resultSelector) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        ObjectHelper.requireNonNull(resultSelector, "resultSelector is null");
        return flatMap(ObservableInternalHelper.flatMapIntoIterable(mapper), resultSelector, false, bufferSize(), bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> flatMapMaybe(Function<? super T, ? extends MaybeSource<? extends R>> mapper) {
        return flatMapMaybe(mapper, false);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> flatMapMaybe(Function<? super T, ? extends MaybeSource<? extends R>> mapper, boolean delayErrors) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        return RxJavaPlugins.onAssembly(new ObservableFlatMapMaybe(this, mapper, delayErrors));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> flatMapSingle(Function<? super T, ? extends SingleSource<? extends R>> mapper) {
        return flatMapSingle(mapper, false);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> flatMapSingle(Function<? super T, ? extends SingleSource<? extends R>> mapper, boolean delayErrors) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        return RxJavaPlugins.onAssembly(new ObservableFlatMapSingle(this, mapper, delayErrors));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Disposable forEach(Consumer<? super T> onNext) {
        return subscribe(onNext);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Disposable forEachWhile(Predicate<? super T> onNext) {
        return forEachWhile(onNext, Functions.ON_ERROR_MISSING, Functions.EMPTY_ACTION);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Disposable forEachWhile(Predicate<? super T> onNext, Consumer<? super Throwable> onError) {
        return forEachWhile(onNext, onError, Functions.EMPTY_ACTION);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Disposable forEachWhile(Predicate<? super T> onNext, Consumer<? super Throwable> onError, Action onComplete) {
        ObjectHelper.requireNonNull(onNext, "onNext is null");
        ObjectHelper.requireNonNull(onError, "onError is null");
        ObjectHelper.requireNonNull(onComplete, "onComplete is null");
        ForEachWhileObserver<T> o = new ForEachWhileObserver<>(onNext, onError, onComplete);
        subscribe(o);
        return o;
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <K> Observable<GroupedObservable<K, T>> groupBy(Function<? super T, ? extends K> keySelector) {
        return groupBy(keySelector, Functions.identity(), false, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <K> Observable<GroupedObservable<K, T>> groupBy(Function<? super T, ? extends K> keySelector, boolean delayError) {
        return groupBy(keySelector, Functions.identity(), delayError, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <K, V> Observable<GroupedObservable<K, V>> groupBy(Function<? super T, ? extends K> keySelector, Function<? super T, ? extends V> valueSelector) {
        return groupBy(keySelector, valueSelector, false, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <K, V> Observable<GroupedObservable<K, V>> groupBy(Function<? super T, ? extends K> keySelector, Function<? super T, ? extends V> valueSelector, boolean delayError) {
        return groupBy(keySelector, valueSelector, delayError, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <K, V> Observable<GroupedObservable<K, V>> groupBy(Function<? super T, ? extends K> keySelector, Function<? super T, ? extends V> valueSelector, boolean delayError, int bufferSize) {
        ObjectHelper.requireNonNull(keySelector, "keySelector is null");
        ObjectHelper.requireNonNull(valueSelector, "valueSelector is null");
        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        return RxJavaPlugins.onAssembly(new ObservableGroupBy(this, keySelector, valueSelector, bufferSize, delayError));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <TRight, TLeftEnd, TRightEnd, R> Observable<R> groupJoin(ObservableSource<? extends TRight> other, Function<? super T, ? extends ObservableSource<TLeftEnd>> leftEnd, Function<? super TRight, ? extends ObservableSource<TRightEnd>> rightEnd, BiFunction<? super T, ? super Observable<TRight>, ? extends R> resultSelector) {
        ObjectHelper.requireNonNull(other, "other is null");
        ObjectHelper.requireNonNull(leftEnd, "leftEnd is null");
        ObjectHelper.requireNonNull(rightEnd, "rightEnd is null");
        ObjectHelper.requireNonNull(resultSelector, "resultSelector is null");
        return RxJavaPlugins.onAssembly(new ObservableGroupJoin(this, other, leftEnd, rightEnd, resultSelector));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> hide() {
        return RxJavaPlugins.onAssembly(new ObservableHide(this));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable ignoreElements() {
        return RxJavaPlugins.onAssembly((Completable) new ObservableIgnoreElementsCompletable(this));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Single<Boolean> isEmpty() {
        return all(Functions.alwaysFalse());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <TRight, TLeftEnd, TRightEnd, R> Observable<R> join(ObservableSource<? extends TRight> other, Function<? super T, ? extends ObservableSource<TLeftEnd>> leftEnd, Function<? super TRight, ? extends ObservableSource<TRightEnd>> rightEnd, BiFunction<? super T, ? super TRight, ? extends R> resultSelector) {
        ObjectHelper.requireNonNull(other, "other is null");
        ObjectHelper.requireNonNull(leftEnd, "leftEnd is null");
        ObjectHelper.requireNonNull(rightEnd, "rightEnd is null");
        ObjectHelper.requireNonNull(resultSelector, "resultSelector is null");
        return RxJavaPlugins.onAssembly(new ObservableJoin(this, other, leftEnd, rightEnd, resultSelector));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Maybe<T> lastElement() {
        return RxJavaPlugins.onAssembly(new ObservableLastMaybe(this));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Single<T> last(T defaultItem) {
        ObjectHelper.requireNonNull(defaultItem, "defaultItem is null");
        return RxJavaPlugins.onAssembly(new ObservableLastSingle(this, defaultItem));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Single<T> lastOrError() {
        return RxJavaPlugins.onAssembly(new ObservableLastSingle(this, null));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> lift(ObservableOperator<? extends R, ? super T> lifter) {
        ObjectHelper.requireNonNull(lifter, "lifter is null");
        return RxJavaPlugins.onAssembly(new ObservableLift(this, lifter));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> map(Function<? super T, ? extends R> mapper) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        return RxJavaPlugins.onAssembly(new ObservableMap(this, mapper));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<Notification<T>> materialize() {
        return RxJavaPlugins.onAssembly(new ObservableMaterialize(this));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> mergeWith(ObservableSource<? extends T> other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return merge(this, (Observable) other);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> mergeWith(SingleSource<? extends T> other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return RxJavaPlugins.onAssembly(new ObservableMergeWithSingle(this, other));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> mergeWith(MaybeSource<? extends T> other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return RxJavaPlugins.onAssembly(new ObservableMergeWithMaybe(this, other));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> mergeWith(CompletableSource other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return RxJavaPlugins.onAssembly(new ObservableMergeWithCompletable(this, other));
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<T> observeOn(Scheduler scheduler) {
        return observeOn(scheduler, false, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<T> observeOn(Scheduler scheduler, boolean delayError) {
        return observeOn(scheduler, delayError, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<T> observeOn(Scheduler scheduler, boolean delayError, int bufferSize) {
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        return RxJavaPlugins.onAssembly(new ObservableObserveOn(this, scheduler, delayError, bufferSize));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <U> Observable<U> ofType(Class<U> clazz) {
        ObjectHelper.requireNonNull(clazz, "clazz is null");
        return filter(Functions.isInstanceOf(clazz)).cast(clazz);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> onErrorResumeNext(Function<? super Throwable, ? extends ObservableSource<? extends T>> resumeFunction) {
        ObjectHelper.requireNonNull(resumeFunction, "resumeFunction is null");
        return RxJavaPlugins.onAssembly(new ObservableOnErrorNext(this, resumeFunction, false));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> onErrorResumeNext(ObservableSource<? extends T> next) {
        ObjectHelper.requireNonNull(next, "next is null");
        return onErrorResumeNext(Functions.justFunction(next));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> onErrorReturn(Function<? super Throwable, ? extends T> valueSupplier) {
        ObjectHelper.requireNonNull(valueSupplier, "valueSupplier is null");
        return RxJavaPlugins.onAssembly(new ObservableOnErrorReturn(this, valueSupplier));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> onErrorReturnItem(T item) {
        ObjectHelper.requireNonNull(item, "item is null");
        return onErrorReturn(Functions.justFunction(item));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> onExceptionResumeNext(ObservableSource<? extends T> next) {
        ObjectHelper.requireNonNull(next, "next is null");
        return RxJavaPlugins.onAssembly(new ObservableOnErrorNext(this, Functions.justFunction(next), true));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> onTerminateDetach() {
        return RxJavaPlugins.onAssembly(new ObservableDetach(this));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final ConnectableObservable<T> publish() {
        return ObservablePublish.create(this);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> publish(Function<? super Observable<T>, ? extends ObservableSource<R>> selector) {
        ObjectHelper.requireNonNull(selector, "selector is null");
        return RxJavaPlugins.onAssembly(new ObservablePublishSelector(this, selector));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Maybe<T> reduce(BiFunction<T, T, T> reducer) {
        ObjectHelper.requireNonNull(reducer, "reducer is null");
        return RxJavaPlugins.onAssembly(new ObservableReduceMaybe(this, reducer));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Single<R> reduce(R seed, BiFunction<R, ? super T, R> reducer) {
        ObjectHelper.requireNonNull(seed, "seed is null");
        ObjectHelper.requireNonNull(reducer, "reducer is null");
        return RxJavaPlugins.onAssembly(new ObservableReduceSeedSingle(this, seed, reducer));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Single<R> reduceWith(Callable<R> seedSupplier, BiFunction<R, ? super T, R> reducer) {
        ObjectHelper.requireNonNull(seedSupplier, "seedSupplier is null");
        ObjectHelper.requireNonNull(reducer, "reducer is null");
        return RxJavaPlugins.onAssembly(new ObservableReduceWithSingle(this, seedSupplier, reducer));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> repeat() {
        return repeat(LongCompanionObject.MAX_VALUE);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> repeat(long times) {
        if (times < 0) {
            throw new IllegalArgumentException("times >= 0 required but it was " + times);
        } else if (times == 0) {
            return empty();
        } else {
            return RxJavaPlugins.onAssembly(new ObservableRepeat(this, times));
        }
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> repeatUntil(BooleanSupplier stop) {
        ObjectHelper.requireNonNull(stop, "stop is null");
        return RxJavaPlugins.onAssembly(new ObservableRepeatUntil(this, stop));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> repeatWhen(Function<? super Observable<Object>, ? extends ObservableSource<?>> handler) {
        ObjectHelper.requireNonNull(handler, "handler is null");
        return RxJavaPlugins.onAssembly(new ObservableRepeatWhen(this, handler));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final ConnectableObservable<T> replay() {
        return ObservableReplay.createFrom(this);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> replay(Function<? super Observable<T>, ? extends ObservableSource<R>> selector) {
        ObjectHelper.requireNonNull(selector, "selector is null");
        return ObservableReplay.multicastSelector(ObservableInternalHelper.replayCallable(this), selector);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> replay(Function<? super Observable<T>, ? extends ObservableSource<R>> selector, int bufferSize) {
        ObjectHelper.requireNonNull(selector, "selector is null");
        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        return ObservableReplay.multicastSelector(ObservableInternalHelper.replayCallable(this, bufferSize), selector);
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public final <R> Observable<R> replay(Function<? super Observable<T>, ? extends ObservableSource<R>> selector, int bufferSize, long time, TimeUnit unit) {
        return replay(selector, bufferSize, time, unit, Schedulers.computation());
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final <R> Observable<R> replay(Function<? super Observable<T>, ? extends ObservableSource<R>> selector, int bufferSize, long time, TimeUnit unit, Scheduler scheduler) {
        ObjectHelper.requireNonNull(selector, "selector is null");
        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        ObjectHelper.requireNonNull(unit, "unit is null");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return ObservableReplay.multicastSelector(ObservableInternalHelper.replayCallable(this, bufferSize, time, unit, scheduler), selector);
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final <R> Observable<R> replay(Function<? super Observable<T>, ? extends ObservableSource<R>> selector, int bufferSize, Scheduler scheduler) {
        ObjectHelper.requireNonNull(selector, "selector is null");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        return ObservableReplay.multicastSelector(ObservableInternalHelper.replayCallable(this, bufferSize), ObservableInternalHelper.replayFunction(selector, scheduler));
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public final <R> Observable<R> replay(Function<? super Observable<T>, ? extends ObservableSource<R>> selector, long time, TimeUnit unit) {
        return replay(selector, time, unit, Schedulers.computation());
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final <R> Observable<R> replay(Function<? super Observable<T>, ? extends ObservableSource<R>> selector, long time, TimeUnit unit, Scheduler scheduler) {
        ObjectHelper.requireNonNull(selector, "selector is null");
        ObjectHelper.requireNonNull(unit, "unit is null");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return ObservableReplay.multicastSelector(ObservableInternalHelper.replayCallable(this, time, unit, scheduler), selector);
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final <R> Observable<R> replay(Function<? super Observable<T>, ? extends ObservableSource<R>> selector, Scheduler scheduler) {
        ObjectHelper.requireNonNull(selector, "selector is null");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return ObservableReplay.multicastSelector(ObservableInternalHelper.replayCallable(this), ObservableInternalHelper.replayFunction(selector, scheduler));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final ConnectableObservable<T> replay(int bufferSize) {
        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        return ObservableReplay.create(this, bufferSize);
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public final ConnectableObservable<T> replay(int bufferSize, long time, TimeUnit unit) {
        return replay(bufferSize, time, unit, Schedulers.computation());
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final ConnectableObservable<T> replay(int bufferSize, long time, TimeUnit unit, Scheduler scheduler) {
        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        ObjectHelper.requireNonNull(unit, "unit is null");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return ObservableReplay.create(this, time, unit, scheduler, bufferSize);
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final ConnectableObservable<T> replay(int bufferSize, Scheduler scheduler) {
        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        return ObservableReplay.observeOn(replay(bufferSize), scheduler);
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public final ConnectableObservable<T> replay(long time, TimeUnit unit) {
        return replay(time, unit, Schedulers.computation());
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final ConnectableObservable<T> replay(long time, TimeUnit unit, Scheduler scheduler) {
        ObjectHelper.requireNonNull(unit, "unit is null");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return ObservableReplay.create(this, time, unit, scheduler);
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final ConnectableObservable<T> replay(Scheduler scheduler) {
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return ObservableReplay.observeOn(replay(), scheduler);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> retry() {
        return retry(LongCompanionObject.MAX_VALUE, Functions.alwaysTrue());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> retry(BiPredicate<? super Integer, ? super Throwable> predicate) {
        ObjectHelper.requireNonNull(predicate, "predicate is null");
        return RxJavaPlugins.onAssembly(new ObservableRetryBiPredicate(this, predicate));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> retry(long times) {
        return retry(times, Functions.alwaysTrue());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> retry(long times, Predicate<? super Throwable> predicate) {
        if (times >= 0) {
            ObjectHelper.requireNonNull(predicate, "predicate is null");
            return RxJavaPlugins.onAssembly(new ObservableRetryPredicate(this, times, predicate));
        }
        throw new IllegalArgumentException("times >= 0 required but it was " + times);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> retry(Predicate<? super Throwable> predicate) {
        return retry(LongCompanionObject.MAX_VALUE, predicate);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> retryUntil(BooleanSupplier stop) {
        ObjectHelper.requireNonNull(stop, "stop is null");
        return retry(LongCompanionObject.MAX_VALUE, Functions.predicateReverseFor(stop));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> retryWhen(Function<? super Observable<Throwable>, ? extends ObservableSource<?>> handler) {
        ObjectHelper.requireNonNull(handler, "handler is null");
        return RxJavaPlugins.onAssembly(new ObservableRetryWhen(this, handler));
    }

    @SchedulerSupport("none")
    public final void safeSubscribe(Observer<? super T> observer) {
        ObjectHelper.requireNonNull(observer, "observer is null");
        if (observer instanceof SafeObserver) {
            subscribe(observer);
        } else {
            subscribe(new SafeObserver(observer));
        }
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public final Observable<T> sample(long period, TimeUnit unit) {
        return sample(period, unit, Schedulers.computation());
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public final Observable<T> sample(long period, TimeUnit unit, boolean emitLast) {
        return sample(period, unit, Schedulers.computation(), emitLast);
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<T> sample(long period, TimeUnit unit, Scheduler scheduler) {
        ObjectHelper.requireNonNull(unit, "unit is null");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new ObservableSampleTimed(this, period, unit, scheduler, false));
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<T> sample(long period, TimeUnit unit, Scheduler scheduler, boolean emitLast) {
        ObjectHelper.requireNonNull(unit, "unit is null");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new ObservableSampleTimed(this, period, unit, scheduler, emitLast));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <U> Observable<T> sample(ObservableSource<U> sampler) {
        ObjectHelper.requireNonNull(sampler, "sampler is null");
        return RxJavaPlugins.onAssembly(new ObservableSampleWithObservable(this, sampler, false));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <U> Observable<T> sample(ObservableSource<U> sampler, boolean emitLast) {
        ObjectHelper.requireNonNull(sampler, "sampler is null");
        return RxJavaPlugins.onAssembly(new ObservableSampleWithObservable(this, sampler, emitLast));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> scan(BiFunction<T, T, T> accumulator) {
        ObjectHelper.requireNonNull(accumulator, "accumulator is null");
        return RxJavaPlugins.onAssembly(new ObservableScan(this, accumulator));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> scan(R initialValue, BiFunction<R, ? super T, R> accumulator) {
        ObjectHelper.requireNonNull(initialValue, "initialValue is null");
        return scanWith(Functions.justCallable(initialValue), accumulator);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> scanWith(Callable<R> seedSupplier, BiFunction<R, ? super T, R> accumulator) {
        ObjectHelper.requireNonNull(seedSupplier, "seedSupplier is null");
        ObjectHelper.requireNonNull(accumulator, "accumulator is null");
        return RxJavaPlugins.onAssembly(new ObservableScanSeed(this, seedSupplier, accumulator));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> serialize() {
        return RxJavaPlugins.onAssembly(new ObservableSerialized(this));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> share() {
        return publish().refCount();
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Maybe<T> singleElement() {
        return RxJavaPlugins.onAssembly(new ObservableSingleMaybe(this));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Single<T> single(T defaultItem) {
        ObjectHelper.requireNonNull(defaultItem, "defaultItem is null");
        return RxJavaPlugins.onAssembly(new ObservableSingleSingle(this, defaultItem));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Single<T> singleOrError() {
        return RxJavaPlugins.onAssembly(new ObservableSingleSingle(this, null));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> skip(long count) {
        if (count <= 0) {
            return RxJavaPlugins.onAssembly(this);
        }
        return RxJavaPlugins.onAssembly(new ObservableSkip(this, count));
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public final Observable<T> skip(long time, TimeUnit unit) {
        return skipUntil(timer(time, unit));
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<T> skip(long time, TimeUnit unit, Scheduler scheduler) {
        return skipUntil(timer(time, unit, scheduler));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> skipLast(int count) {
        if (count < 0) {
            throw new IndexOutOfBoundsException("count >= 0 required but it was " + count);
        } else if (count == 0) {
            return RxJavaPlugins.onAssembly(this);
        } else {
            return RxJavaPlugins.onAssembly(new ObservableSkipLast(this, count));
        }
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:trampoline")
    public final Observable<T> skipLast(long time, TimeUnit unit) {
        return skipLast(time, unit, Schedulers.trampoline(), false, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:trampoline")
    public final Observable<T> skipLast(long time, TimeUnit unit, boolean delayError) {
        return skipLast(time, unit, Schedulers.trampoline(), delayError, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<T> skipLast(long time, TimeUnit unit, Scheduler scheduler) {
        return skipLast(time, unit, scheduler, false, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<T> skipLast(long time, TimeUnit unit, Scheduler scheduler, boolean delayError) {
        return skipLast(time, unit, scheduler, delayError, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<T> skipLast(long time, TimeUnit unit, Scheduler scheduler, boolean delayError, int bufferSize) {
        int i = bufferSize;
        ObjectHelper.requireNonNull(unit, "unit is null");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        ObjectHelper.verifyPositive(i, "bufferSize");
        return RxJavaPlugins.onAssembly(new ObservableSkipLastTimed(this, time, unit, scheduler, i << 1, delayError));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <U> Observable<T> skipUntil(ObservableSource<U> other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return RxJavaPlugins.onAssembly(new ObservableSkipUntil(this, other));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> skipWhile(Predicate<? super T> predicate) {
        ObjectHelper.requireNonNull(predicate, "predicate is null");
        return RxJavaPlugins.onAssembly(new ObservableSkipWhile(this, predicate));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> sorted() {
        return toList().toObservable().map(Functions.listSorter(Functions.naturalComparator())).flatMapIterable(Functions.identity());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> sorted(Comparator<? super T> sortFunction) {
        ObjectHelper.requireNonNull(sortFunction, "sortFunction is null");
        return toList().toObservable().map(Functions.listSorter(sortFunction)).flatMapIterable(Functions.identity());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> startWith(Iterable<? extends T> items) {
        return concatArray(fromIterable(items), this);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> startWith(ObservableSource<? extends T> other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return concatArray(other, this);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> startWith(T item) {
        ObjectHelper.requireNonNull(item, "item is null");
        return concatArray(just(item), this);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> startWithArray(T... items) {
        Observable<T> fromArray = fromArray(items);
        if (fromArray == empty()) {
            return RxJavaPlugins.onAssembly(this);
        }
        return concatArray(fromArray, this);
    }

    @SchedulerSupport("none")
    public final Disposable subscribe() {
        return subscribe(Functions.emptyConsumer(), Functions.ON_ERROR_MISSING, Functions.EMPTY_ACTION, Functions.emptyConsumer());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Disposable subscribe(Consumer<? super T> onNext) {
        return subscribe(onNext, Functions.ON_ERROR_MISSING, Functions.EMPTY_ACTION, Functions.emptyConsumer());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Disposable subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError) {
        return subscribe(onNext, onError, Functions.EMPTY_ACTION, Functions.emptyConsumer());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Disposable subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError, Action onComplete) {
        return subscribe(onNext, onError, onComplete, Functions.emptyConsumer());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Disposable subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError, Action onComplete, Consumer<? super Disposable> onSubscribe) {
        ObjectHelper.requireNonNull(onNext, "onNext is null");
        ObjectHelper.requireNonNull(onError, "onError is null");
        ObjectHelper.requireNonNull(onComplete, "onComplete is null");
        ObjectHelper.requireNonNull(onSubscribe, "onSubscribe is null");
        LambdaObserver<T> ls = new LambdaObserver<>(onNext, onError, onComplete, onSubscribe);
        subscribe(ls);
        return ls;
    }

    @SchedulerSupport("none")
    public final void subscribe(Observer<? super T> observer) {
        ObjectHelper.requireNonNull(observer, "observer is null");
        try {
            Observer<? super Object> onSubscribe = RxJavaPlugins.onSubscribe(this, observer);
            ObjectHelper.requireNonNull(onSubscribe, "The RxJavaPlugins.onSubscribe hook returned a null Observer. Please change the handler provided to RxJavaPlugins.setOnObservableSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
            subscribeActual(onSubscribe);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable e2) {
            Exceptions.throwIfFatal(e2);
            RxJavaPlugins.onError(e2);
            NullPointerException npe = new NullPointerException("Actually not, but can't throw other exceptions due to RS");
            npe.initCause(e2);
            throw npe;
        }
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <E extends Observer<? super T>> E subscribeWith(E observer) {
        subscribe(observer);
        return observer;
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<T> subscribeOn(Scheduler scheduler) {
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new ObservableSubscribeOn(this, scheduler));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> switchIfEmpty(ObservableSource<? extends T> other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return RxJavaPlugins.onAssembly(new ObservableSwitchIfEmpty(this, other));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> switchMap(Function<? super T, ? extends ObservableSource<? extends R>> mapper) {
        return switchMap(mapper, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> switchMap(Function<? super T, ? extends ObservableSource<? extends R>> mapper, int bufferSize) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        if (!(this instanceof ScalarCallable)) {
            return RxJavaPlugins.onAssembly(new ObservableSwitchMap(this, mapper, bufferSize, false));
        }
        T v = ((ScalarCallable) this).call();
        if (v == null) {
            return empty();
        }
        return ObservableScalarXMap.scalarXMap(v, mapper);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable switchMapCompletable(Function<? super T, ? extends CompletableSource> mapper) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        return RxJavaPlugins.onAssembly((Completable) new ObservableSwitchMapCompletable(this, mapper, false));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Completable switchMapCompletableDelayError(Function<? super T, ? extends CompletableSource> mapper) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        return RxJavaPlugins.onAssembly((Completable) new ObservableSwitchMapCompletable(this, mapper, true));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> switchMapMaybe(Function<? super T, ? extends MaybeSource<? extends R>> mapper) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        return RxJavaPlugins.onAssembly(new ObservableSwitchMapMaybe(this, mapper, false));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> switchMapMaybeDelayError(Function<? super T, ? extends MaybeSource<? extends R>> mapper) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        return RxJavaPlugins.onAssembly(new ObservableSwitchMapMaybe(this, mapper, true));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> switchMapSingle(Function<? super T, ? extends SingleSource<? extends R>> mapper) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        return RxJavaPlugins.onAssembly(new ObservableSwitchMapSingle(this, mapper, false));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> switchMapSingleDelayError(Function<? super T, ? extends SingleSource<? extends R>> mapper) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        return RxJavaPlugins.onAssembly(new ObservableSwitchMapSingle(this, mapper, true));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> switchMapDelayError(Function<? super T, ? extends ObservableSource<? extends R>> mapper) {
        return switchMapDelayError(mapper, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> switchMapDelayError(Function<? super T, ? extends ObservableSource<? extends R>> mapper, int bufferSize) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        if (!(this instanceof ScalarCallable)) {
            return RxJavaPlugins.onAssembly(new ObservableSwitchMap(this, mapper, bufferSize, true));
        }
        T v = ((ScalarCallable) this).call();
        if (v == null) {
            return empty();
        }
        return ObservableScalarXMap.scalarXMap(v, mapper);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> take(long count) {
        if (count >= 0) {
            return RxJavaPlugins.onAssembly(new ObservableTake(this, count));
        }
        throw new IllegalArgumentException("count >= 0 required but it was " + count);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> take(long time, TimeUnit unit) {
        return takeUntil(timer(time, unit));
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<T> take(long time, TimeUnit unit, Scheduler scheduler) {
        return takeUntil(timer(time, unit, scheduler));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> takeLast(int count) {
        if (count < 0) {
            throw new IndexOutOfBoundsException("count >= 0 required but it was " + count);
        } else if (count == 0) {
            return RxJavaPlugins.onAssembly(new ObservableIgnoreElements(this));
        } else {
            if (count == 1) {
                return RxJavaPlugins.onAssembly(new ObservableTakeLastOne(this));
            }
            return RxJavaPlugins.onAssembly(new ObservableTakeLast(this, count));
        }
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:trampoline")
    public final Observable<T> takeLast(long count, long time, TimeUnit unit) {
        return takeLast(count, time, unit, Schedulers.trampoline(), false, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<T> takeLast(long count, long time, TimeUnit unit, Scheduler scheduler) {
        return takeLast(count, time, unit, scheduler, false, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<T> takeLast(long count, long time, TimeUnit unit, Scheduler scheduler, boolean delayError, int bufferSize) {
        long j = count;
        ObjectHelper.requireNonNull(unit, "unit is null");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        if (j >= 0) {
            return RxJavaPlugins.onAssembly(new ObservableTakeLastTimed(this, count, time, unit, scheduler, bufferSize, delayError));
        }
        throw new IndexOutOfBoundsException("count >= 0 required but it was " + j);
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:trampoline")
    public final Observable<T> takeLast(long time, TimeUnit unit) {
        return takeLast(time, unit, Schedulers.trampoline(), false, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:trampoline")
    public final Observable<T> takeLast(long time, TimeUnit unit, boolean delayError) {
        return takeLast(time, unit, Schedulers.trampoline(), delayError, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<T> takeLast(long time, TimeUnit unit, Scheduler scheduler) {
        return takeLast(time, unit, scheduler, false, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<T> takeLast(long time, TimeUnit unit, Scheduler scheduler, boolean delayError) {
        return takeLast(time, unit, scheduler, delayError, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<T> takeLast(long time, TimeUnit unit, Scheduler scheduler, boolean delayError, int bufferSize) {
        return takeLast(LongCompanionObject.MAX_VALUE, time, unit, scheduler, delayError, bufferSize);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <U> Observable<T> takeUntil(ObservableSource<U> other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return RxJavaPlugins.onAssembly(new ObservableTakeUntil(this, other));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> takeUntil(Predicate<? super T> stopPredicate) {
        ObjectHelper.requireNonNull(stopPredicate, "stopPredicate is null");
        return RxJavaPlugins.onAssembly(new ObservableTakeUntilPredicate(this, stopPredicate));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> takeWhile(Predicate<? super T> predicate) {
        ObjectHelper.requireNonNull(predicate, "predicate is null");
        return RxJavaPlugins.onAssembly(new ObservableTakeWhile(this, predicate));
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public final Observable<T> throttleFirst(long windowDuration, TimeUnit unit) {
        return throttleFirst(windowDuration, unit, Schedulers.computation());
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<T> throttleFirst(long skipDuration, TimeUnit unit, Scheduler scheduler) {
        ObjectHelper.requireNonNull(unit, "unit is null");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new ObservableThrottleFirstTimed(this, skipDuration, unit, scheduler));
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public final Observable<T> throttleLast(long intervalDuration, TimeUnit unit) {
        return sample(intervalDuration, unit);
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<T> throttleLast(long intervalDuration, TimeUnit unit, Scheduler scheduler) {
        return sample(intervalDuration, unit, scheduler);
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public final Observable<T> throttleLatest(long timeout, TimeUnit unit) {
        return throttleLatest(timeout, unit, Schedulers.computation(), false);
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public final Observable<T> throttleLatest(long timeout, TimeUnit unit, boolean emitLast) {
        return throttleLatest(timeout, unit, Schedulers.computation(), emitLast);
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<T> throttleLatest(long timeout, TimeUnit unit, Scheduler scheduler) {
        return throttleLatest(timeout, unit, scheduler, false);
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<T> throttleLatest(long timeout, TimeUnit unit, Scheduler scheduler, boolean emitLast) {
        ObjectHelper.requireNonNull(unit, "unit is null");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new ObservableThrottleLatest(this, timeout, unit, scheduler, emitLast));
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public final Observable<T> throttleWithTimeout(long timeout, TimeUnit unit) {
        return debounce(timeout, unit);
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<T> throttleWithTimeout(long timeout, TimeUnit unit, Scheduler scheduler) {
        return debounce(timeout, unit, scheduler);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<Timed<T>> timeInterval() {
        return timeInterval(TimeUnit.MILLISECONDS, Schedulers.computation());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<Timed<T>> timeInterval(Scheduler scheduler) {
        return timeInterval(TimeUnit.MILLISECONDS, scheduler);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<Timed<T>> timeInterval(TimeUnit unit) {
        return timeInterval(unit, Schedulers.computation());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<Timed<T>> timeInterval(TimeUnit unit, Scheduler scheduler) {
        ObjectHelper.requireNonNull(unit, "unit is null");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new ObservableTimeInterval(this, unit, scheduler));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <V> Observable<T> timeout(Function<? super T, ? extends ObservableSource<V>> itemTimeoutIndicator) {
        return timeout0((ObservableSource) null, itemTimeoutIndicator, (ObservableSource) null);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <V> Observable<T> timeout(Function<? super T, ? extends ObservableSource<V>> itemTimeoutIndicator, ObservableSource<? extends T> other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return timeout0((ObservableSource) null, itemTimeoutIndicator, other);
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public final Observable<T> timeout(long timeout, TimeUnit timeUnit) {
        return timeout0(timeout, timeUnit, (ObservableSource) null, Schedulers.computation());
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public final Observable<T> timeout(long timeout, TimeUnit timeUnit, ObservableSource<? extends T> other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return timeout0(timeout, timeUnit, other, Schedulers.computation());
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<T> timeout(long timeout, TimeUnit timeUnit, Scheduler scheduler, ObservableSource<? extends T> other) {
        ObjectHelper.requireNonNull(other, "other is null");
        return timeout0(timeout, timeUnit, other, scheduler);
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<T> timeout(long timeout, TimeUnit timeUnit, Scheduler scheduler) {
        return timeout0(timeout, timeUnit, (ObservableSource) null, scheduler);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <U, V> Observable<T> timeout(ObservableSource<U> firstTimeoutIndicator, Function<? super T, ? extends ObservableSource<V>> itemTimeoutIndicator) {
        ObjectHelper.requireNonNull(firstTimeoutIndicator, "firstTimeoutIndicator is null");
        return timeout0(firstTimeoutIndicator, itemTimeoutIndicator, (ObservableSource) null);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <U, V> Observable<T> timeout(ObservableSource<U> firstTimeoutIndicator, Function<? super T, ? extends ObservableSource<V>> itemTimeoutIndicator, ObservableSource<? extends T> other) {
        ObjectHelper.requireNonNull(firstTimeoutIndicator, "firstTimeoutIndicator is null");
        ObjectHelper.requireNonNull(other, "other is null");
        return timeout0(firstTimeoutIndicator, itemTimeoutIndicator, other);
    }

    private Observable<T> timeout0(long timeout, TimeUnit timeUnit, ObservableSource<? extends T> other, Scheduler scheduler) {
        ObjectHelper.requireNonNull(timeUnit, "timeUnit is null");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new ObservableTimeoutTimed(this, timeout, timeUnit, scheduler, other));
    }

    private <U, V> Observable<T> timeout0(ObservableSource<U> firstTimeoutIndicator, Function<? super T, ? extends ObservableSource<V>> itemTimeoutIndicator, ObservableSource<? extends T> other) {
        ObjectHelper.requireNonNull(itemTimeoutIndicator, "itemTimeoutIndicator is null");
        return RxJavaPlugins.onAssembly(new ObservableTimeout(this, firstTimeoutIndicator, itemTimeoutIndicator, other));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<Timed<T>> timestamp() {
        return timestamp(TimeUnit.MILLISECONDS, Schedulers.computation());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<Timed<T>> timestamp(Scheduler scheduler) {
        return timestamp(TimeUnit.MILLISECONDS, scheduler);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<Timed<T>> timestamp(TimeUnit unit) {
        return timestamp(unit, Schedulers.computation());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<Timed<T>> timestamp(TimeUnit unit, Scheduler scheduler) {
        ObjectHelper.requireNonNull(unit, "unit is null");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return map(Functions.timestampWith(unit, scheduler));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> R to(Function<? super Observable<T>, R> converter) {
        try {
            return ((Function) ObjectHelper.requireNonNull(converter, "converter is null")).apply(this);
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            throw ExceptionHelper.wrapOrThrow(ex);
        }
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Single<List<T>> toList() {
        return toList(16);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Single<List<T>> toList(int capacityHint) {
        ObjectHelper.verifyPositive(capacityHint, "capacityHint");
        return RxJavaPlugins.onAssembly(new ObservableToListSingle(this, capacityHint));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <U extends Collection<? super T>> Single<U> toList(Callable<U> collectionSupplier) {
        ObjectHelper.requireNonNull(collectionSupplier, "collectionSupplier is null");
        return RxJavaPlugins.onAssembly(new ObservableToListSingle(this, collectionSupplier));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <K> Single<Map<K, T>> toMap(Function<? super T, ? extends K> keySelector) {
        ObjectHelper.requireNonNull(keySelector, "keySelector is null");
        return collect(HashMapSupplier.asCallable(), Functions.toMapKeySelector(keySelector));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <K, V> Single<Map<K, V>> toMap(Function<? super T, ? extends K> keySelector, Function<? super T, ? extends V> valueSelector) {
        ObjectHelper.requireNonNull(keySelector, "keySelector is null");
        ObjectHelper.requireNonNull(valueSelector, "valueSelector is null");
        return collect(HashMapSupplier.asCallable(), Functions.toMapKeyValueSelector(keySelector, valueSelector));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <K, V> Single<Map<K, V>> toMap(Function<? super T, ? extends K> keySelector, Function<? super T, ? extends V> valueSelector, Callable<? extends Map<K, V>> mapSupplier) {
        ObjectHelper.requireNonNull(keySelector, "keySelector is null");
        ObjectHelper.requireNonNull(valueSelector, "valueSelector is null");
        ObjectHelper.requireNonNull(mapSupplier, "mapSupplier is null");
        return collect(mapSupplier, Functions.toMapKeyValueSelector(keySelector, valueSelector));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <K> Single<Map<K, Collection<T>>> toMultimap(Function<? super T, ? extends K> keySelector) {
        return toMultimap(keySelector, Functions.identity(), HashMapSupplier.asCallable(), ArrayListSupplier.asFunction());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <K, V> Single<Map<K, Collection<V>>> toMultimap(Function<? super T, ? extends K> keySelector, Function<? super T, ? extends V> valueSelector) {
        return toMultimap(keySelector, valueSelector, HashMapSupplier.asCallable(), ArrayListSupplier.asFunction());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <K, V> Single<Map<K, Collection<V>>> toMultimap(Function<? super T, ? extends K> keySelector, Function<? super T, ? extends V> valueSelector, Callable<? extends Map<K, Collection<V>>> mapSupplier, Function<? super K, ? extends Collection<? super V>> collectionFactory) {
        ObjectHelper.requireNonNull(keySelector, "keySelector is null");
        ObjectHelper.requireNonNull(valueSelector, "valueSelector is null");
        ObjectHelper.requireNonNull(mapSupplier, "mapSupplier is null");
        ObjectHelper.requireNonNull(collectionFactory, "collectionFactory is null");
        return collect(mapSupplier, Functions.toMultimapKeyValueSelector(keySelector, valueSelector, collectionFactory));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <K, V> Single<Map<K, Collection<V>>> toMultimap(Function<? super T, ? extends K> keySelector, Function<? super T, ? extends V> valueSelector, Callable<Map<K, Collection<V>>> mapSupplier) {
        return toMultimap(keySelector, valueSelector, mapSupplier, ArrayListSupplier.asFunction());
    }

    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.SPECIAL)
    @SchedulerSupport("none")
    public final Flowable<T> toFlowable(BackpressureStrategy strategy) {
        Flowable<T> f = new FlowableFromObservable<>(this);
        switch (AnonymousClass1.$SwitchMap$io$reactivex$BackpressureStrategy[strategy.ordinal()]) {
            case 1:
                return f.onBackpressureDrop();
            case 2:
                return f.onBackpressureLatest();
            case 3:
                return f;
            case 4:
                return RxJavaPlugins.onAssembly(new FlowableOnBackpressureError(f));
            default:
                return f.onBackpressureBuffer();
        }
    }

    /* renamed from: io.reactivex.Observable$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$reactivex$BackpressureStrategy;

        static {
            int[] iArr = new int[BackpressureStrategy.values().length];
            $SwitchMap$io$reactivex$BackpressureStrategy = iArr;
            try {
                iArr[BackpressureStrategy.DROP.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$io$reactivex$BackpressureStrategy[BackpressureStrategy.LATEST.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$io$reactivex$BackpressureStrategy[BackpressureStrategy.MISSING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$io$reactivex$BackpressureStrategy[BackpressureStrategy.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Single<List<T>> toSortedList() {
        return toSortedList(Functions.naturalOrder());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Single<List<T>> toSortedList(Comparator<? super T> comparator) {
        ObjectHelper.requireNonNull(comparator, "comparator is null");
        return toList().map(Functions.listSorter(comparator));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Single<List<T>> toSortedList(Comparator<? super T> comparator, int capacityHint) {
        ObjectHelper.requireNonNull(comparator, "comparator is null");
        return toList(capacityHint).map(Functions.listSorter(comparator));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Single<List<T>> toSortedList(int capacityHint) {
        return toSortedList(Functions.naturalOrder(), capacityHint);
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<T> unsubscribeOn(Scheduler scheduler) {
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new ObservableUnsubscribeOn(this, scheduler));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<Observable<T>> window(long count) {
        return window(count, count, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<Observable<T>> window(long count, long skip) {
        return window(count, skip, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<Observable<T>> window(long count, long skip, int bufferSize) {
        ObjectHelper.verifyPositive(count, "count");
        ObjectHelper.verifyPositive(skip, "skip");
        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        return RxJavaPlugins.onAssembly(new ObservableWindow(this, count, skip, bufferSize));
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public final Observable<Observable<T>> window(long timespan, long timeskip, TimeUnit unit) {
        return window(timespan, timeskip, unit, Schedulers.computation(), bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<Observable<T>> window(long timespan, long timeskip, TimeUnit unit, Scheduler scheduler) {
        return window(timespan, timeskip, unit, scheduler, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<Observable<T>> window(long timespan, long timeskip, TimeUnit unit, Scheduler scheduler, int bufferSize) {
        ObjectHelper.verifyPositive(timespan, "timespan");
        ObjectHelper.verifyPositive(timeskip, "timeskip");
        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        ObjectHelper.requireNonNull(unit, "unit is null");
        return RxJavaPlugins.onAssembly(new ObservableWindowTimed(this, timespan, timeskip, unit, scheduler, LongCompanionObject.MAX_VALUE, bufferSize, false));
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public final Observable<Observable<T>> window(long timespan, TimeUnit unit) {
        return window(timespan, unit, Schedulers.computation(), (long) LongCompanionObject.MAX_VALUE, false);
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public final Observable<Observable<T>> window(long timespan, TimeUnit unit, long count) {
        return window(timespan, unit, Schedulers.computation(), count, false);
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public final Observable<Observable<T>> window(long timespan, TimeUnit unit, long count, boolean restart) {
        return window(timespan, unit, Schedulers.computation(), count, restart);
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<Observable<T>> window(long timespan, TimeUnit unit, Scheduler scheduler) {
        return window(timespan, unit, scheduler, (long) LongCompanionObject.MAX_VALUE, false);
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<Observable<T>> window(long timespan, TimeUnit unit, Scheduler scheduler, long count) {
        return window(timespan, unit, scheduler, count, false);
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<Observable<T>> window(long timespan, TimeUnit unit, Scheduler scheduler, long count, boolean restart) {
        return window(timespan, unit, scheduler, count, restart, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<Observable<T>> window(long timespan, TimeUnit unit, Scheduler scheduler, long count, boolean restart, int bufferSize) {
        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        ObjectHelper.requireNonNull(unit, "unit is null");
        ObjectHelper.verifyPositive(count, "count");
        return RxJavaPlugins.onAssembly(new ObservableWindowTimed(this, timespan, timespan, unit, scheduler, count, bufferSize, restart));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <B> Observable<Observable<T>> window(ObservableSource<B> boundary) {
        return window(boundary, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <B> Observable<Observable<T>> window(ObservableSource<B> boundary, int bufferSize) {
        ObjectHelper.requireNonNull(boundary, "boundary is null");
        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        return RxJavaPlugins.onAssembly(new ObservableWindowBoundary(this, boundary, bufferSize));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <U, V> Observable<Observable<T>> window(ObservableSource<U> openingIndicator, Function<? super U, ? extends ObservableSource<V>> closingIndicator) {
        return window(openingIndicator, closingIndicator, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <U, V> Observable<Observable<T>> window(ObservableSource<U> openingIndicator, Function<? super U, ? extends ObservableSource<V>> closingIndicator, int bufferSize) {
        ObjectHelper.requireNonNull(openingIndicator, "openingIndicator is null");
        ObjectHelper.requireNonNull(closingIndicator, "closingIndicator is null");
        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        return RxJavaPlugins.onAssembly(new ObservableWindowBoundarySelector(this, openingIndicator, closingIndicator, bufferSize));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <B> Observable<Observable<T>> window(Callable<? extends ObservableSource<B>> boundary) {
        return window(boundary, bufferSize());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <B> Observable<Observable<T>> window(Callable<? extends ObservableSource<B>> boundary, int bufferSize) {
        ObjectHelper.requireNonNull(boundary, "boundary is null");
        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        return RxJavaPlugins.onAssembly(new ObservableWindowBoundarySupplier(this, boundary, bufferSize));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <U, R> Observable<R> withLatestFrom(ObservableSource<? extends U> other, BiFunction<? super T, ? super U, ? extends R> combiner) {
        ObjectHelper.requireNonNull(other, "other is null");
        ObjectHelper.requireNonNull(combiner, "combiner is null");
        return RxJavaPlugins.onAssembly(new ObservableWithLatestFrom(this, combiner, other));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <T1, T2, R> Observable<R> withLatestFrom(ObservableSource<T1> o1, ObservableSource<T2> o2, Function3<? super T, ? super T1, ? super T2, R> combiner) {
        ObjectHelper.requireNonNull(o1, "o1 is null");
        ObjectHelper.requireNonNull(o2, "o2 is null");
        ObjectHelper.requireNonNull(combiner, "combiner is null");
        return withLatestFrom((ObservableSource<?>[]) new ObservableSource[]{o1, o2}, Functions.toFunction(combiner));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <T1, T2, T3, R> Observable<R> withLatestFrom(ObservableSource<T1> o1, ObservableSource<T2> o2, ObservableSource<T3> o3, Function4<? super T, ? super T1, ? super T2, ? super T3, R> combiner) {
        ObjectHelper.requireNonNull(o1, "o1 is null");
        ObjectHelper.requireNonNull(o2, "o2 is null");
        ObjectHelper.requireNonNull(o3, "o3 is null");
        ObjectHelper.requireNonNull(combiner, "combiner is null");
        return withLatestFrom((ObservableSource<?>[]) new ObservableSource[]{o1, o2, o3}, Functions.toFunction(combiner));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <T1, T2, T3, T4, R> Observable<R> withLatestFrom(ObservableSource<T1> o1, ObservableSource<T2> o2, ObservableSource<T3> o3, ObservableSource<T4> o4, Function5<? super T, ? super T1, ? super T2, ? super T3, ? super T4, R> combiner) {
        ObjectHelper.requireNonNull(o1, "o1 is null");
        ObjectHelper.requireNonNull(o2, "o2 is null");
        ObjectHelper.requireNonNull(o3, "o3 is null");
        ObjectHelper.requireNonNull(o4, "o4 is null");
        ObjectHelper.requireNonNull(combiner, "combiner is null");
        return withLatestFrom((ObservableSource<?>[]) new ObservableSource[]{o1, o2, o3, o4}, Functions.toFunction(combiner));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> withLatestFrom(ObservableSource<?>[] others, Function<? super Object[], R> combiner) {
        ObjectHelper.requireNonNull(others, "others is null");
        ObjectHelper.requireNonNull(combiner, "combiner is null");
        return RxJavaPlugins.onAssembly(new ObservableWithLatestFromMany(this, others, combiner));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <R> Observable<R> withLatestFrom(Iterable<? extends ObservableSource<?>> others, Function<? super Object[], R> combiner) {
        ObjectHelper.requireNonNull(others, "others is null");
        ObjectHelper.requireNonNull(combiner, "combiner is null");
        return RxJavaPlugins.onAssembly(new ObservableWithLatestFromMany(this, others, combiner));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <U, R> Observable<R> zipWith(Iterable<U> other, BiFunction<? super T, ? super U, ? extends R> zipper) {
        ObjectHelper.requireNonNull(other, "other is null");
        ObjectHelper.requireNonNull(zipper, "zipper is null");
        return RxJavaPlugins.onAssembly(new ObservableZipIterable(this, other, zipper));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <U, R> Observable<R> zipWith(ObservableSource<? extends U> other, BiFunction<? super T, ? super U, ? extends R> zipper) {
        ObjectHelper.requireNonNull(other, "other is null");
        return zip(this, other, zipper);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <U, R> Observable<R> zipWith(ObservableSource<? extends U> other, BiFunction<? super T, ? super U, ? extends R> zipper, boolean delayError) {
        return zip(this, other, zipper, delayError);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final <U, R> Observable<R> zipWith(ObservableSource<? extends U> other, BiFunction<? super T, ? super U, ? extends R> zipper, boolean delayError, int bufferSize) {
        return zip(this, other, zipper, delayError, bufferSize);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final TestObserver<T> test() {
        TestObserver<T> to = new TestObserver<>();
        subscribe(to);
        return to;
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final TestObserver<T> test(boolean dispose) {
        TestObserver<T> to = new TestObserver<>();
        if (dispose) {
            to.dispose();
        }
        subscribe(to);
        return to;
    }
}
