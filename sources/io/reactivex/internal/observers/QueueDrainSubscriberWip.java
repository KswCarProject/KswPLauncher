package io.reactivex.internal.observers;

import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: QueueDrainObserver.java */
/* loaded from: classes.dex */
class QueueDrainSubscriberWip extends QueueDrainSubscriberPad0 {
    final AtomicInteger wip = new AtomicInteger();

    QueueDrainSubscriberWip() {
    }
}
