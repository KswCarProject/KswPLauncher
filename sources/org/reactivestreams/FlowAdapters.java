package org.reactivestreams;

import java.util.Objects;
import java.util.concurrent.Flow;

/* loaded from: classes.dex */
public final class FlowAdapters {
    private FlowAdapters() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Publisher<T> toPublisher(Flow.Publisher<? extends T> flowPublisher) {
        Objects.requireNonNull(flowPublisher, "flowPublisher");
        if (flowPublisher instanceof FlowPublisherFromReactive) {
            return (Publisher<? extends T>) ((FlowPublisherFromReactive) flowPublisher).reactiveStreams;
        }
        if (flowPublisher instanceof Publisher) {
            Publisher<T> publisher = (Publisher) flowPublisher;
            return publisher;
        }
        Publisher<T> publisher2 = new ReactivePublisherFromFlow<>(flowPublisher);
        return publisher2;
    }

    public static <T> Flow.Publisher<T> toFlowPublisher(Publisher<? extends T> reactiveStreamsPublisher) {
        Objects.requireNonNull(reactiveStreamsPublisher, "reactiveStreamsPublisher");
        if (reactiveStreamsPublisher instanceof ReactivePublisherFromFlow) {
            return (Flow.Publisher<? extends T>) ((ReactivePublisherFromFlow) reactiveStreamsPublisher).flow;
        }
        if (reactiveStreamsPublisher instanceof Flow.Publisher) {
            Flow.Publisher<T> flowPublisher = (Flow.Publisher) reactiveStreamsPublisher;
            return flowPublisher;
        }
        Flow.Publisher<T> flowPublisher2 = new FlowPublisherFromReactive<>(reactiveStreamsPublisher);
        return flowPublisher2;
    }

    public static <T, U> Processor<T, U> toProcessor(Flow.Processor<? super T, ? extends U> flowProcessor) {
        Objects.requireNonNull(flowProcessor, "flowProcessor");
        if (flowProcessor instanceof FlowToReactiveProcessor) {
            return (Processor<? super T, ? extends U>) ((FlowToReactiveProcessor) flowProcessor).reactiveStreams;
        }
        if (flowProcessor instanceof Processor) {
            Processor<T, U> processor = (Processor) flowProcessor;
            return processor;
        }
        Processor<T, U> processor2 = new ReactiveToFlowProcessor<>(flowProcessor);
        return processor2;
    }

    public static <T, U> Flow.Processor<T, U> toFlowProcessor(Processor<? super T, ? extends U> reactiveStreamsProcessor) {
        Objects.requireNonNull(reactiveStreamsProcessor, "reactiveStreamsProcessor");
        if (reactiveStreamsProcessor instanceof ReactiveToFlowProcessor) {
            return (Flow.Processor<? super T, ? extends U>) ((ReactiveToFlowProcessor) reactiveStreamsProcessor).flow;
        }
        if (reactiveStreamsProcessor instanceof Flow.Processor) {
            Flow.Processor<T, U> flowProcessor = (Flow.Processor) reactiveStreamsProcessor;
            return flowProcessor;
        }
        Flow.Processor<T, U> flowProcessor2 = new FlowToReactiveProcessor<>(reactiveStreamsProcessor);
        return flowProcessor2;
    }

    public static <T> Flow.Subscriber<T> toFlowSubscriber(Subscriber<T> reactiveStreamsSubscriber) {
        Objects.requireNonNull(reactiveStreamsSubscriber, "reactiveStreamsSubscriber");
        if (reactiveStreamsSubscriber instanceof ReactiveToFlowSubscriber) {
            return (Flow.Subscriber<? super T>) ((ReactiveToFlowSubscriber) reactiveStreamsSubscriber).flow;
        }
        if (reactiveStreamsSubscriber instanceof Flow.Subscriber) {
            Flow.Subscriber<T> flowSubscriber = (Flow.Subscriber) reactiveStreamsSubscriber;
            return flowSubscriber;
        }
        Flow.Subscriber<T> flowSubscriber2 = new FlowToReactiveSubscriber<>(reactiveStreamsSubscriber);
        return flowSubscriber2;
    }

    public static <T> Subscriber<T> toSubscriber(Flow.Subscriber<T> flowSubscriber) {
        Objects.requireNonNull(flowSubscriber, "flowSubscriber");
        if (flowSubscriber instanceof FlowToReactiveSubscriber) {
            return (Subscriber<? super T>) ((FlowToReactiveSubscriber) flowSubscriber).reactiveStreams;
        }
        if (flowSubscriber instanceof Subscriber) {
            Subscriber<T> subscriber = (Subscriber) flowSubscriber;
            return subscriber;
        }
        Subscriber<T> subscriber2 = new ReactiveToFlowSubscriber<>(flowSubscriber);
        return subscriber2;
    }

    /* loaded from: classes.dex */
    static final class FlowToReactiveSubscription implements Flow.Subscription {
        final Subscription reactiveStreams;

        public FlowToReactiveSubscription(Subscription reactive) {
            this.reactiveStreams = reactive;
        }

        @Override // java.util.concurrent.Flow.Subscription
        public void request(long n) {
            this.reactiveStreams.request(n);
        }

        @Override // java.util.concurrent.Flow.Subscription
        public void cancel() {
            this.reactiveStreams.cancel();
        }
    }

    /* loaded from: classes.dex */
    static final class ReactiveToFlowSubscription implements Subscription {
        final Flow.Subscription flow;

        public ReactiveToFlowSubscription(Flow.Subscription flow) {
            this.flow = flow;
        }

        @Override // org.reactivestreams.Subscription
        public void request(long n) {
            this.flow.request(n);
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.flow.cancel();
        }
    }

    /* loaded from: classes.dex */
    static final class FlowToReactiveSubscriber<T> implements Flow.Subscriber<T> {
        final Subscriber<? super T> reactiveStreams;

        public FlowToReactiveSubscriber(Subscriber<? super T> reactive) {
            this.reactiveStreams = reactive;
        }

        @Override // java.util.concurrent.Flow.Subscriber
        public void onSubscribe(Flow.Subscription subscription) {
            this.reactiveStreams.onSubscribe(subscription == null ? null : new ReactiveToFlowSubscription(subscription));
        }

        @Override // java.util.concurrent.Flow.Subscriber
        public void onNext(T item) {
            this.reactiveStreams.onNext(item);
        }

        @Override // java.util.concurrent.Flow.Subscriber
        public void onError(Throwable throwable) {
            this.reactiveStreams.onError(throwable);
        }

        @Override // java.util.concurrent.Flow.Subscriber
        public void onComplete() {
            this.reactiveStreams.onComplete();
        }
    }

    /* loaded from: classes.dex */
    static final class ReactiveToFlowSubscriber<T> implements Subscriber<T> {
        final Flow.Subscriber<? super T> flow;

        public ReactiveToFlowSubscriber(Flow.Subscriber<? super T> flow) {
            this.flow = flow;
        }

        @Override // org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            this.flow.onSubscribe(subscription == null ? null : new FlowToReactiveSubscription(subscription));
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T item) {
            this.flow.onNext(item);
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable throwable) {
            this.flow.onError(throwable);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.flow.onComplete();
        }
    }

    /* loaded from: classes.dex */
    static final class ReactiveToFlowProcessor<T, U> implements Processor<T, U> {
        final Flow.Processor<? super T, ? extends U> flow;

        public ReactiveToFlowProcessor(Flow.Processor<? super T, ? extends U> flow) {
            this.flow = flow;
        }

        @Override // org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            this.flow.onSubscribe(subscription == null ? null : new FlowToReactiveSubscription(subscription));
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            this.flow.onNext(t);
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable t) {
            this.flow.onError(t);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.flow.onComplete();
        }

        @Override // org.reactivestreams.Publisher
        public void subscribe(Subscriber<? super U> s) {
            this.flow.subscribe(s == null ? null : new FlowToReactiveSubscriber(s));
        }
    }

    /* loaded from: classes.dex */
    static final class FlowToReactiveProcessor<T, U> implements Flow.Processor<T, U> {
        final Processor<? super T, ? extends U> reactiveStreams;

        public FlowToReactiveProcessor(Processor<? super T, ? extends U> reactive) {
            this.reactiveStreams = reactive;
        }

        @Override // java.util.concurrent.Flow.Subscriber
        public void onSubscribe(Flow.Subscription subscription) {
            this.reactiveStreams.onSubscribe(subscription == null ? null : new ReactiveToFlowSubscription(subscription));
        }

        @Override // java.util.concurrent.Flow.Subscriber
        public void onNext(T t) {
            this.reactiveStreams.onNext(t);
        }

        @Override // java.util.concurrent.Flow.Subscriber
        public void onError(Throwable t) {
            this.reactiveStreams.onError(t);
        }

        @Override // java.util.concurrent.Flow.Subscriber
        public void onComplete() {
            this.reactiveStreams.onComplete();
        }

        @Override // java.util.concurrent.Flow.Publisher
        public void subscribe(Flow.Subscriber<? super U> s) {
            this.reactiveStreams.subscribe(s == null ? null : new ReactiveToFlowSubscriber(s));
        }
    }

    /* loaded from: classes.dex */
    static final class ReactivePublisherFromFlow<T> implements Publisher<T> {
        final Flow.Publisher<? extends T> flow;

        public ReactivePublisherFromFlow(Flow.Publisher<? extends T> flowPublisher) {
            this.flow = flowPublisher;
        }

        @Override // org.reactivestreams.Publisher
        public void subscribe(Subscriber<? super T> reactive) {
            this.flow.subscribe(reactive == null ? null : new FlowToReactiveSubscriber(reactive));
        }
    }

    /* loaded from: classes.dex */
    static final class FlowPublisherFromReactive<T> implements Flow.Publisher<T> {
        final Publisher<? extends T> reactiveStreams;

        public FlowPublisherFromReactive(Publisher<? extends T> reactivePublisher) {
            this.reactiveStreams = reactivePublisher;
        }

        @Override // java.util.concurrent.Flow.Publisher
        public void subscribe(Flow.Subscriber<? super T> flow) {
            this.reactiveStreams.subscribe(flow == null ? null : new ReactiveToFlowSubscriber(flow));
        }
    }
}
