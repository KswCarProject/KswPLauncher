package org.reactivestreams;

import java.util.Objects;
import java.util.concurrent.Flow;

public final class FlowAdapters {
    private FlowAdapters() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Publisher<T> toPublisher(Flow.Publisher<? extends T> flowPublisher) {
        Objects.requireNonNull(flowPublisher, "flowPublisher");
        if (flowPublisher instanceof FlowPublisherFromReactive) {
            return ((FlowPublisherFromReactive) flowPublisher).reactiveStreams;
        }
        if (flowPublisher instanceof Publisher) {
            return (Publisher) flowPublisher;
        }
        return new ReactivePublisherFromFlow<>(flowPublisher);
    }

    public static <T> Flow.Publisher<T> toFlowPublisher(Publisher<? extends T> reactiveStreamsPublisher) {
        Objects.requireNonNull(reactiveStreamsPublisher, "reactiveStreamsPublisher");
        if (reactiveStreamsPublisher instanceof ReactivePublisherFromFlow) {
            return ((ReactivePublisherFromFlow) reactiveStreamsPublisher).flow;
        }
        if (reactiveStreamsPublisher instanceof Flow.Publisher) {
            return (Flow.Publisher) reactiveStreamsPublisher;
        }
        return new FlowPublisherFromReactive<>(reactiveStreamsPublisher);
    }

    public static <T, U> Processor<T, U> toProcessor(Flow.Processor<? super T, ? extends U> flowProcessor) {
        Objects.requireNonNull(flowProcessor, "flowProcessor");
        if (flowProcessor instanceof FlowToReactiveProcessor) {
            return ((FlowToReactiveProcessor) flowProcessor).reactiveStreams;
        }
        if (flowProcessor instanceof Processor) {
            return (Processor) flowProcessor;
        }
        return new ReactiveToFlowProcessor<>(flowProcessor);
    }

    public static <T, U> Flow.Processor<T, U> toFlowProcessor(Processor<? super T, ? extends U> reactiveStreamsProcessor) {
        Objects.requireNonNull(reactiveStreamsProcessor, "reactiveStreamsProcessor");
        if (reactiveStreamsProcessor instanceof ReactiveToFlowProcessor) {
            return ((ReactiveToFlowProcessor) reactiveStreamsProcessor).flow;
        }
        if (reactiveStreamsProcessor instanceof Flow.Processor) {
            return (Flow.Processor) reactiveStreamsProcessor;
        }
        return new FlowToReactiveProcessor<>(reactiveStreamsProcessor);
    }

    public static <T> Flow.Subscriber<T> toFlowSubscriber(Subscriber<T> reactiveStreamsSubscriber) {
        Objects.requireNonNull(reactiveStreamsSubscriber, "reactiveStreamsSubscriber");
        if (reactiveStreamsSubscriber instanceof ReactiveToFlowSubscriber) {
            return ((ReactiveToFlowSubscriber) reactiveStreamsSubscriber).flow;
        }
        if (reactiveStreamsSubscriber instanceof Flow.Subscriber) {
            return (Flow.Subscriber) reactiveStreamsSubscriber;
        }
        return new FlowToReactiveSubscriber<>(reactiveStreamsSubscriber);
    }

    public static <T> Subscriber<T> toSubscriber(Flow.Subscriber<T> flowSubscriber) {
        Objects.requireNonNull(flowSubscriber, "flowSubscriber");
        if (flowSubscriber instanceof FlowToReactiveSubscriber) {
            return ((FlowToReactiveSubscriber) flowSubscriber).reactiveStreams;
        }
        if (flowSubscriber instanceof Subscriber) {
            return (Subscriber) flowSubscriber;
        }
        return new ReactiveToFlowSubscriber<>(flowSubscriber);
    }

    static final class FlowToReactiveSubscription implements Flow.Subscription {
        final Subscription reactiveStreams;

        public FlowToReactiveSubscription(Subscription reactive) {
            this.reactiveStreams = reactive;
        }

        public void request(long n) {
            this.reactiveStreams.request(n);
        }

        public void cancel() {
            this.reactiveStreams.cancel();
        }
    }

    static final class ReactiveToFlowSubscription implements Subscription {
        final Flow.Subscription flow;

        public ReactiveToFlowSubscription(Flow.Subscription flow2) {
            this.flow = flow2;
        }

        public void request(long n) {
            this.flow.request(n);
        }

        public void cancel() {
            this.flow.cancel();
        }
    }

    static final class FlowToReactiveSubscriber<T> implements Flow.Subscriber<T> {
        final Subscriber<? super T> reactiveStreams;

        public FlowToReactiveSubscriber(Subscriber<? super T> reactive) {
            this.reactiveStreams = reactive;
        }

        public void onSubscribe(Flow.Subscription subscription) {
            this.reactiveStreams.onSubscribe(subscription == null ? null : new ReactiveToFlowSubscription(subscription));
        }

        public void onNext(T item) {
            this.reactiveStreams.onNext(item);
        }

        public void onError(Throwable throwable) {
            this.reactiveStreams.onError(throwable);
        }

        public void onComplete() {
            this.reactiveStreams.onComplete();
        }
    }

    static final class ReactiveToFlowSubscriber<T> implements Subscriber<T> {
        final Flow.Subscriber<? super T> flow;

        public ReactiveToFlowSubscriber(Flow.Subscriber<? super T> flow2) {
            this.flow = flow2;
        }

        public void onSubscribe(Subscription subscription) {
            this.flow.onSubscribe(subscription == null ? null : new FlowToReactiveSubscription(subscription));
        }

        public void onNext(T item) {
            this.flow.onNext(item);
        }

        public void onError(Throwable throwable) {
            this.flow.onError(throwable);
        }

        public void onComplete() {
            this.flow.onComplete();
        }
    }

    static final class ReactiveToFlowProcessor<T, U> implements Processor<T, U> {
        final Flow.Processor<? super T, ? extends U> flow;

        public ReactiveToFlowProcessor(Flow.Processor<? super T, ? extends U> flow2) {
            this.flow = flow2;
        }

        public void onSubscribe(Subscription subscription) {
            this.flow.onSubscribe(subscription == null ? null : new FlowToReactiveSubscription(subscription));
        }

        public void onNext(T t) {
            this.flow.onNext(t);
        }

        public void onError(Throwable t) {
            this.flow.onError(t);
        }

        public void onComplete() {
            this.flow.onComplete();
        }

        public void subscribe(Subscriber<? super U> s) {
            this.flow.subscribe(s == null ? null : new FlowToReactiveSubscriber(s));
        }
    }

    static final class FlowToReactiveProcessor<T, U> implements Flow.Processor<T, U> {
        final Processor<? super T, ? extends U> reactiveStreams;

        public FlowToReactiveProcessor(Processor<? super T, ? extends U> reactive) {
            this.reactiveStreams = reactive;
        }

        public void onSubscribe(Flow.Subscription subscription) {
            this.reactiveStreams.onSubscribe(subscription == null ? null : new ReactiveToFlowSubscription(subscription));
        }

        public void onNext(T t) {
            this.reactiveStreams.onNext(t);
        }

        public void onError(Throwable t) {
            this.reactiveStreams.onError(t);
        }

        public void onComplete() {
            this.reactiveStreams.onComplete();
        }

        public void subscribe(Flow.Subscriber<? super U> s) {
            this.reactiveStreams.subscribe(s == null ? null : new ReactiveToFlowSubscriber(s));
        }
    }

    static final class ReactivePublisherFromFlow<T> implements Publisher<T> {
        final Flow.Publisher<? extends T> flow;

        public ReactivePublisherFromFlow(Flow.Publisher<? extends T> flowPublisher) {
            this.flow = flowPublisher;
        }

        public void subscribe(Subscriber<? super T> reactive) {
            this.flow.subscribe(reactive == null ? null : new FlowToReactiveSubscriber(reactive));
        }
    }

    static final class FlowPublisherFromReactive<T> implements Flow.Publisher<T> {
        final Publisher<? extends T> reactiveStreams;

        public FlowPublisherFromReactive(Publisher<? extends T> reactivePublisher) {
            this.reactiveStreams = reactivePublisher;
        }

        public void subscribe(Flow.Subscriber<? super T> flow) {
            this.reactiveStreams.subscribe(flow == null ? null : new ReactiveToFlowSubscriber(flow));
        }
    }
}
