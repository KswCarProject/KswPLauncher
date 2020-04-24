package com.bumptech.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.CheckResult;
import android.support.annotation.DrawableRes;
import android.support.annotation.GuardedBy;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import android.view.View;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.manager.ConnectivityMonitor;
import com.bumptech.glide.manager.ConnectivityMonitorFactory;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.LifecycleListener;
import com.bumptech.glide.manager.RequestManagerTreeNode;
import com.bumptech.glide.manager.RequestTracker;
import com.bumptech.glide.manager.TargetTracker;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Util;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RequestManager implements LifecycleListener, ModelTypes<RequestBuilder<Drawable>> {
    private static final RequestOptions DECODE_TYPE_BITMAP = ((RequestOptions) RequestOptions.decodeTypeOf(Bitmap.class).lock());
    private static final RequestOptions DECODE_TYPE_GIF = ((RequestOptions) RequestOptions.decodeTypeOf(GifDrawable.class).lock());
    private static final RequestOptions DOWNLOAD_ONLY_OPTIONS = ((RequestOptions) ((RequestOptions) RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.DATA).priority(Priority.LOW)).skipMemoryCache(true));
    private final Runnable addSelfToLifecycle;
    private final ConnectivityMonitor connectivityMonitor;
    protected final Context context;
    private final CopyOnWriteArrayList<RequestListener<Object>> defaultRequestListeners;
    protected final Glide glide;
    final Lifecycle lifecycle;
    private final Handler mainHandler;
    @GuardedBy("this")
    private RequestOptions requestOptions;
    @GuardedBy("this")
    private final RequestTracker requestTracker;
    @GuardedBy("this")
    private final TargetTracker targetTracker;
    @GuardedBy("this")
    private final RequestManagerTreeNode treeNode;

    public RequestManager(@NonNull Glide glide2, @NonNull Lifecycle lifecycle2, @NonNull RequestManagerTreeNode treeNode2, @NonNull Context context2) {
        this(glide2, lifecycle2, treeNode2, new RequestTracker(), glide2.getConnectivityMonitorFactory(), context2);
    }

    RequestManager(Glide glide2, Lifecycle lifecycle2, RequestManagerTreeNode treeNode2, RequestTracker requestTracker2, ConnectivityMonitorFactory factory, Context context2) {
        this.targetTracker = new TargetTracker();
        this.addSelfToLifecycle = new Runnable() {
            public void run() {
                RequestManager.this.lifecycle.addListener(RequestManager.this);
            }
        };
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.glide = glide2;
        this.lifecycle = lifecycle2;
        this.treeNode = treeNode2;
        this.requestTracker = requestTracker2;
        this.context = context2;
        this.connectivityMonitor = factory.build(context2.getApplicationContext(), new RequestManagerConnectivityListener(requestTracker2));
        if (Util.isOnBackgroundThread()) {
            this.mainHandler.post(this.addSelfToLifecycle);
        } else {
            lifecycle2.addListener(this);
        }
        lifecycle2.addListener(this.connectivityMonitor);
        this.defaultRequestListeners = new CopyOnWriteArrayList<>(glide2.getGlideContext().getDefaultRequestListeners());
        setRequestOptions(glide2.getGlideContext().getDefaultRequestOptions());
        glide2.registerRequestManager(this);
    }

    /* access modifiers changed from: protected */
    public synchronized void setRequestOptions(@NonNull RequestOptions toSet) {
        this.requestOptions = (RequestOptions) ((RequestOptions) toSet.clone()).autoClone();
    }

    private synchronized void updateRequestOptions(@NonNull RequestOptions toUpdate) {
        this.requestOptions = (RequestOptions) this.requestOptions.apply(toUpdate);
    }

    @NonNull
    public synchronized RequestManager applyDefaultRequestOptions(@NonNull RequestOptions requestOptions2) {
        updateRequestOptions(requestOptions2);
        return this;
    }

    @NonNull
    public synchronized RequestManager setDefaultRequestOptions(@NonNull RequestOptions requestOptions2) {
        setRequestOptions(requestOptions2);
        return this;
    }

    public RequestManager addDefaultRequestListener(RequestListener<Object> requestListener) {
        this.defaultRequestListeners.add(requestListener);
        return this;
    }

    public synchronized boolean isPaused() {
        return this.requestTracker.isPaused();
    }

    public synchronized void pauseRequests() {
        this.requestTracker.pauseRequests();
    }

    public synchronized void pauseAllRequests() {
        this.requestTracker.pauseAllRequests();
    }

    public synchronized void pauseRequestsRecursive() {
        pauseRequests();
        for (RequestManager requestManager : this.treeNode.getDescendants()) {
            requestManager.pauseRequests();
        }
    }

    public synchronized void resumeRequests() {
        this.requestTracker.resumeRequests();
    }

    public synchronized void resumeRequestsRecursive() {
        Util.assertMainThread();
        resumeRequests();
        for (RequestManager requestManager : this.treeNode.getDescendants()) {
            requestManager.resumeRequests();
        }
    }

    public synchronized void onStart() {
        resumeRequests();
        this.targetTracker.onStart();
    }

    public synchronized void onStop() {
        pauseRequests();
        this.targetTracker.onStop();
    }

    public synchronized void onDestroy() {
        this.targetTracker.onDestroy();
        for (Target<?> target : this.targetTracker.getAll()) {
            clear(target);
        }
        this.targetTracker.clear();
        this.requestTracker.clearRequests();
        this.lifecycle.removeListener(this);
        this.lifecycle.removeListener(this.connectivityMonitor);
        this.mainHandler.removeCallbacks(this.addSelfToLifecycle);
        this.glide.unregisterRequestManager(this);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<Bitmap> asBitmap() {
        return as(Bitmap.class).apply((BaseRequestOptions<?>) DECODE_TYPE_BITMAP);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<GifDrawable> asGif() {
        return as(GifDrawable.class).apply((BaseRequestOptions<?>) DECODE_TYPE_GIF);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<Drawable> asDrawable() {
        return as(Drawable.class);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<Drawable> load(@Nullable Bitmap bitmap) {
        return asDrawable().load(bitmap);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<Drawable> load(@Nullable Drawable drawable) {
        return asDrawable().load(drawable);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<Drawable> load(@Nullable String string) {
        return asDrawable().load(string);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<Drawable> load(@Nullable Uri uri) {
        return asDrawable().load(uri);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<Drawable> load(@Nullable File file) {
        return asDrawable().load(file);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<Drawable> load(@Nullable @RawRes @DrawableRes Integer resourceId) {
        return asDrawable().load(resourceId);
    }

    @Deprecated
    @CheckResult
    public RequestBuilder<Drawable> load(@Nullable URL url) {
        return asDrawable().load(url);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<Drawable> load(@Nullable byte[] model) {
        return asDrawable().load(model);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<Drawable> load(@Nullable Object model) {
        return asDrawable().load(model);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<File> downloadOnly() {
        return as(File.class).apply((BaseRequestOptions<?>) DOWNLOAD_ONLY_OPTIONS);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<File> download(@Nullable Object model) {
        return downloadOnly().load(model);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<File> asFile() {
        return as(File.class).apply((BaseRequestOptions<?>) RequestOptions.skipMemoryCacheOf(true));
    }

    @CheckResult
    @NonNull
    public <ResourceType> RequestBuilder<ResourceType> as(@NonNull Class<ResourceType> resourceClass) {
        return new RequestBuilder<>(this.glide, this, resourceClass, this.context);
    }

    public void clear(@NonNull View view) {
        clear((Target<?>) new ClearTarget(view));
    }

    public synchronized void clear(@Nullable Target<?> target) {
        if (target != null) {
            untrackOrDelegate(target);
        }
    }

    private void untrackOrDelegate(@NonNull Target<?> target) {
        if (!untrack(target) && !this.glide.removeFromManagers(target) && target.getRequest() != null) {
            Request request = target.getRequest();
            target.setRequest((Request) null);
            request.clear();
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean untrack(@NonNull Target<?> target) {
        Request request = target.getRequest();
        if (request == null) {
            return true;
        }
        if (!this.requestTracker.clearRemoveAndRecycle(request)) {
            return false;
        }
        this.targetTracker.untrack(target);
        target.setRequest((Request) null);
        return true;
    }

    /* access modifiers changed from: package-private */
    public synchronized void track(@NonNull Target<?> target, @NonNull Request request) {
        this.targetTracker.track(target);
        this.requestTracker.runRequest(request);
    }

    /* access modifiers changed from: package-private */
    public List<RequestListener<Object>> getDefaultRequestListeners() {
        return this.defaultRequestListeners;
    }

    /* access modifiers changed from: package-private */
    public synchronized RequestOptions getDefaultRequestOptions() {
        return this.requestOptions;
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public <T> TransitionOptions<?, T> getDefaultTransitionOptions(Class<T> transcodeClass) {
        return this.glide.getGlideContext().getDefaultTransitionOptions(transcodeClass);
    }

    public synchronized String toString() {
        return super.toString() + "{tracker=" + this.requestTracker + ", treeNode=" + this.treeNode + "}";
    }

    private class RequestManagerConnectivityListener implements ConnectivityMonitor.ConnectivityListener {
        @GuardedBy("RequestManager.this")
        private final RequestTracker requestTracker;

        RequestManagerConnectivityListener(@NonNull RequestTracker requestTracker2) {
            this.requestTracker = requestTracker2;
        }

        public void onConnectivityChanged(boolean isConnected) {
            if (isConnected) {
                synchronized (RequestManager.this) {
                    this.requestTracker.restartRequests();
                }
            }
        }
    }

    private static class ClearTarget extends ViewTarget<View, Object> {
        ClearTarget(@NonNull View view) {
            super(view);
        }

        public void onResourceReady(@NonNull Object resource, @Nullable Transition<? super Object> transition) {
        }
    }
}
