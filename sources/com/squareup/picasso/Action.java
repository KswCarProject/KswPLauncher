package com.squareup.picasso;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.squareup.picasso.Picasso;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

abstract class Action<T> {
    boolean cancelled;
    final Drawable errorDrawable;
    final int errorResId;
    final String key;
    final int memoryPolicy;
    final int networkPolicy;
    final boolean noFade;
    final Picasso picasso;
    final Request request;
    final Object tag;
    final WeakReference<T> target;
    boolean willReplay;

    /* access modifiers changed from: package-private */
    public abstract void complete(Bitmap bitmap, Picasso.LoadedFrom loadedFrom);

    /* access modifiers changed from: package-private */
    public abstract void error();

    static class RequestWeakReference<M> extends WeakReference<M> {
        final Action action;

        public RequestWeakReference(Action action2, M referent, ReferenceQueue<? super M> q) {
            super(referent, q);
            this.action = action2;
        }
    }

    Action(Picasso picasso2, T target2, Request request2, int memoryPolicy2, int networkPolicy2, int errorResId2, Drawable errorDrawable2, String key2, Object tag2, boolean noFade2) {
        this.picasso = picasso2;
        this.request = request2;
        this.target = target2 == null ? null : new RequestWeakReference(this, target2, picasso2.referenceQueue);
        this.memoryPolicy = memoryPolicy2;
        this.networkPolicy = networkPolicy2;
        this.noFade = noFade2;
        this.errorResId = errorResId2;
        this.errorDrawable = errorDrawable2;
        this.key = key2;
        this.tag = tag2 != null ? tag2 : this;
    }

    /* access modifiers changed from: package-private */
    public void cancel() {
        this.cancelled = true;
    }

    /* access modifiers changed from: package-private */
    public Request getRequest() {
        return this.request;
    }

    /* access modifiers changed from: package-private */
    public T getTarget() {
        WeakReference<T> weakReference = this.target;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    /* access modifiers changed from: package-private */
    public String getKey() {
        return this.key;
    }

    /* access modifiers changed from: package-private */
    public boolean isCancelled() {
        return this.cancelled;
    }

    /* access modifiers changed from: package-private */
    public boolean willReplay() {
        return this.willReplay;
    }

    /* access modifiers changed from: package-private */
    public int getMemoryPolicy() {
        return this.memoryPolicy;
    }

    /* access modifiers changed from: package-private */
    public int getNetworkPolicy() {
        return this.networkPolicy;
    }

    /* access modifiers changed from: package-private */
    public Picasso getPicasso() {
        return this.picasso;
    }

    /* access modifiers changed from: package-private */
    public Picasso.Priority getPriority() {
        return this.request.priority;
    }

    /* access modifiers changed from: package-private */
    public Object getTag() {
        return this.tag;
    }
}
