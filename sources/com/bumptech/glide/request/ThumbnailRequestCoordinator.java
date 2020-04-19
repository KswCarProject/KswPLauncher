package com.bumptech.glide.request;

import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

public class ThumbnailRequestCoordinator implements RequestCoordinator, Request {
    private Request full;
    private boolean isRunning;
    @Nullable
    private final RequestCoordinator parent;
    private Request thumb;

    @VisibleForTesting
    ThumbnailRequestCoordinator() {
        this((RequestCoordinator) null);
    }

    public ThumbnailRequestCoordinator(@Nullable RequestCoordinator parent2) {
        this.parent = parent2;
    }

    public void setRequests(Request full2, Request thumb2) {
        this.full = full2;
        this.thumb = thumb2;
    }

    public boolean canSetImage(Request request) {
        return parentCanSetImage() && (request.equals(this.full) || !this.full.isResourceSet());
    }

    private boolean parentCanSetImage() {
        return this.parent == null || this.parent.canSetImage(this);
    }

    public boolean canNotifyStatusChanged(Request request) {
        return parentCanNotifyStatusChanged() && request.equals(this.full) && !isAnyResourceSet();
    }

    public boolean canNotifyCleared(Request request) {
        return parentCanNotifyCleared() && request.equals(this.full);
    }

    private boolean parentCanNotifyCleared() {
        return this.parent == null || this.parent.canNotifyCleared(this);
    }

    private boolean parentCanNotifyStatusChanged() {
        return this.parent == null || this.parent.canNotifyStatusChanged(this);
    }

    public boolean isAnyResourceSet() {
        return parentIsAnyResourceSet() || isResourceSet();
    }

    public void onRequestSuccess(Request request) {
        if (!request.equals(this.thumb)) {
            if (this.parent != null) {
                this.parent.onRequestSuccess(this);
            }
            if (!this.thumb.isComplete()) {
                this.thumb.clear();
            }
        }
    }

    public void onRequestFailed(Request request) {
        if (request.equals(this.full) && this.parent != null) {
            this.parent.onRequestFailed(this);
        }
    }

    private boolean parentIsAnyResourceSet() {
        return this.parent != null && this.parent.isAnyResourceSet();
    }

    public void begin() {
        this.isRunning = true;
        if (!this.full.isComplete() && !this.thumb.isRunning()) {
            this.thumb.begin();
        }
        if (this.isRunning && !this.full.isRunning()) {
            this.full.begin();
        }
    }

    public void clear() {
        this.isRunning = false;
        this.thumb.clear();
        this.full.clear();
    }

    public boolean isRunning() {
        return this.full.isRunning();
    }

    public boolean isComplete() {
        return this.full.isComplete() || this.thumb.isComplete();
    }

    public boolean isResourceSet() {
        return this.full.isResourceSet() || this.thumb.isResourceSet();
    }

    public boolean isCleared() {
        return this.full.isCleared();
    }

    public boolean isFailed() {
        return this.full.isFailed();
    }

    public void recycle() {
        this.full.recycle();
        this.thumb.recycle();
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x002e A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isEquivalentTo(com.bumptech.glide.request.Request r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.bumptech.glide.request.ThumbnailRequestCoordinator
            r1 = 0
            if (r0 == 0) goto L_0x0031
            r0 = r5
            com.bumptech.glide.request.ThumbnailRequestCoordinator r0 = (com.bumptech.glide.request.ThumbnailRequestCoordinator) r0
            com.bumptech.glide.request.Request r2 = r4.full
            if (r2 != 0) goto L_0x0011
            com.bumptech.glide.request.Request r2 = r0.full
            if (r2 != 0) goto L_0x0030
            goto L_0x001b
        L_0x0011:
            com.bumptech.glide.request.Request r2 = r4.full
            com.bumptech.glide.request.Request r3 = r0.full
            boolean r2 = r2.isEquivalentTo(r3)
            if (r2 == 0) goto L_0x0030
        L_0x001b:
            com.bumptech.glide.request.Request r2 = r4.thumb
            if (r2 != 0) goto L_0x0024
            com.bumptech.glide.request.Request r2 = r0.thumb
            if (r2 != 0) goto L_0x0030
            goto L_0x002e
        L_0x0024:
            com.bumptech.glide.request.Request r2 = r4.thumb
            com.bumptech.glide.request.Request r3 = r0.thumb
            boolean r2 = r2.isEquivalentTo(r3)
            if (r2 == 0) goto L_0x0030
        L_0x002e:
            r1 = 1
        L_0x0030:
            return r1
        L_0x0031:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.ThumbnailRequestCoordinator.isEquivalentTo(com.bumptech.glide.request.Request):boolean");
    }
}
