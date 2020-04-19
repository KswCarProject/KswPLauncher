package com.bumptech.glide.request;

import android.support.annotation.Nullable;

public final class ErrorRequestCoordinator implements RequestCoordinator, Request {
    private Request error;
    @Nullable
    private final RequestCoordinator parent;
    private Request primary;

    public ErrorRequestCoordinator(@Nullable RequestCoordinator parent2) {
        this.parent = parent2;
    }

    public void setRequests(Request primary2, Request error2) {
        this.primary = primary2;
        this.error = error2;
    }

    public void begin() {
        if (!this.primary.isRunning()) {
            this.primary.begin();
        }
    }

    public void clear() {
        this.primary.clear();
        if (this.error.isRunning()) {
            this.error.clear();
        }
    }

    public boolean isRunning() {
        return (this.primary.isFailed() ? this.error : this.primary).isRunning();
    }

    public boolean isComplete() {
        return (this.primary.isFailed() ? this.error : this.primary).isComplete();
    }

    public boolean isResourceSet() {
        return (this.primary.isFailed() ? this.error : this.primary).isResourceSet();
    }

    public boolean isCleared() {
        return (this.primary.isFailed() ? this.error : this.primary).isCleared();
    }

    public boolean isFailed() {
        return this.primary.isFailed() && this.error.isFailed();
    }

    public void recycle() {
        this.primary.recycle();
        this.error.recycle();
    }

    public boolean isEquivalentTo(Request o) {
        if (!(o instanceof ErrorRequestCoordinator)) {
            return false;
        }
        ErrorRequestCoordinator other = (ErrorRequestCoordinator) o;
        if (!this.primary.isEquivalentTo(other.primary) || !this.error.isEquivalentTo(other.error)) {
            return false;
        }
        return true;
    }

    public boolean canSetImage(Request request) {
        return parentCanSetImage() && isValidRequest(request);
    }

    private boolean parentCanSetImage() {
        return this.parent == null || this.parent.canSetImage(this);
    }

    public boolean canNotifyStatusChanged(Request request) {
        return parentCanNotifyStatusChanged() && isValidRequest(request);
    }

    public boolean canNotifyCleared(Request request) {
        return parentCanNotifyCleared() && isValidRequest(request);
    }

    private boolean parentCanNotifyCleared() {
        return this.parent == null || this.parent.canNotifyCleared(this);
    }

    private boolean parentCanNotifyStatusChanged() {
        return this.parent == null || this.parent.canNotifyStatusChanged(this);
    }

    private boolean isValidRequest(Request request) {
        return request.equals(this.primary) || (this.primary.isFailed() && request.equals(this.error));
    }

    public boolean isAnyResourceSet() {
        return parentIsAnyResourceSet() || isResourceSet();
    }

    private boolean parentIsAnyResourceSet() {
        return this.parent != null && this.parent.isAnyResourceSet();
    }

    public void onRequestSuccess(Request request) {
        if (this.parent != null) {
            this.parent.onRequestSuccess(this);
        }
    }

    public void onRequestFailed(Request request) {
        if (!request.equals(this.error)) {
            if (!this.error.isRunning()) {
                this.error.begin();
            }
        } else if (this.parent != null) {
            this.parent.onRequestFailed(this);
        }
    }
}
