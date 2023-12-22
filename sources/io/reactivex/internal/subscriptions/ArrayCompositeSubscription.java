package io.reactivex.internal.subscriptions;

import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicReferenceArray;
import org.reactivestreams.Subscription;

/* loaded from: classes.dex */
public final class ArrayCompositeSubscription extends AtomicReferenceArray<Subscription> implements Disposable {
    private static final long serialVersionUID = 2746389416410565408L;

    public ArrayCompositeSubscription(int capacity) {
        super(capacity);
    }

    public boolean setResource(int index, Subscription resource) {
        Subscription o;
        do {
            o = get(index);
            if (o == SubscriptionHelper.CANCELLED) {
                if (resource != null) {
                    resource.cancel();
                    return false;
                }
                return false;
            }
        } while (!compareAndSet(index, o, resource));
        if (o != null) {
            o.cancel();
            return true;
        }
        return true;
    }

    public Subscription replaceResource(int index, Subscription resource) {
        Subscription o;
        do {
            o = get(index);
            if (o == SubscriptionHelper.CANCELLED) {
                if (resource != null) {
                    resource.cancel();
                    return null;
                }
                return null;
            }
        } while (!compareAndSet(index, o, resource));
        return o;
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        if (get(0) != SubscriptionHelper.CANCELLED) {
            int s = length();
            for (int i = 0; i < s; i++) {
                Subscription o = get(i);
                if (o != SubscriptionHelper.CANCELLED) {
                    Subscription o2 = getAndSet(i, SubscriptionHelper.CANCELLED);
                    Subscription o3 = o2;
                    if (o3 != SubscriptionHelper.CANCELLED && o3 != null) {
                        o3.cancel();
                    }
                }
            }
        }
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return get(0) == SubscriptionHelper.CANCELLED;
    }
}
