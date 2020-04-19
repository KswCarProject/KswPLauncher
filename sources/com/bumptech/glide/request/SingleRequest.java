package com.bumptech.glide.request;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.GuardedBy;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pools;
import android.util.Log;
import com.bumptech.glide.GlideContext;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.drawable.DrawableDecoderCompat;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.TransitionFactory;
import com.bumptech.glide.util.Util;
import com.bumptech.glide.util.pool.FactoryPools;
import com.bumptech.glide.util.pool.StateVerifier;
import java.util.List;
import java.util.concurrent.Executor;

public final class SingleRequest<R> implements Request, SizeReadyCallback, ResourceCallback, FactoryPools.Poolable {
    private static final String GLIDE_TAG = "Glide";
    private static final boolean IS_VERBOSE_LOGGABLE = Log.isLoggable(TAG, 2);
    private static final Pools.Pool<SingleRequest<?>> POOL = FactoryPools.threadSafe(150, new FactoryPools.Factory<SingleRequest<?>>() {
        public SingleRequest<?> create() {
            return new SingleRequest<>();
        }
    });
    private static final String TAG = "Request";
    private TransitionFactory<? super R> animationFactory;
    private Executor callbackExecutor;
    private Context context;
    private Engine engine;
    private Drawable errorDrawable;
    private Drawable fallbackDrawable;
    private GlideContext glideContext;
    private int height;
    private boolean isCallingCallbacks;
    private Engine.LoadStatus loadStatus;
    @Nullable
    private Object model;
    private int overrideHeight;
    private int overrideWidth;
    private Drawable placeholderDrawable;
    private Priority priority;
    private RequestCoordinator requestCoordinator;
    @Nullable
    private List<RequestListener<R>> requestListeners;
    private BaseRequestOptions<?> requestOptions;
    @Nullable
    private RuntimeException requestOrigin;
    private Resource<R> resource;
    private long startTime;
    private final StateVerifier stateVerifier;
    @GuardedBy("this")
    private Status status;
    @Nullable
    private final String tag;
    private Target<R> target;
    @Nullable
    private RequestListener<R> targetListener;
    private Class<R> transcodeClass;
    private int width;

    private enum Status {
        PENDING,
        RUNNING,
        WAITING_FOR_SIZE,
        COMPLETE,
        FAILED,
        CLEARED
    }

    public static <R> SingleRequest<R> obtain(Context context2, GlideContext glideContext2, Object model2, Class<R> transcodeClass2, BaseRequestOptions<?> requestOptions2, int overrideWidth2, int overrideHeight2, Priority priority2, Target<R> target2, RequestListener<R> targetListener2, @Nullable List<RequestListener<R>> requestListeners2, RequestCoordinator requestCoordinator2, Engine engine2, TransitionFactory<? super R> animationFactory2, Executor callbackExecutor2) {
        SingleRequest<R> request = POOL.acquire();
        if (request == null) {
            request = new SingleRequest<>();
        }
        request.init(context2, glideContext2, model2, transcodeClass2, requestOptions2, overrideWidth2, overrideHeight2, priority2, target2, targetListener2, requestListeners2, requestCoordinator2, engine2, animationFactory2, callbackExecutor2);
        return request;
    }

    SingleRequest() {
        this.tag = IS_VERBOSE_LOGGABLE ? String.valueOf(super.hashCode()) : null;
        this.stateVerifier = StateVerifier.newInstance();
    }

    private synchronized void init(Context context2, GlideContext glideContext2, Object model2, Class<R> transcodeClass2, BaseRequestOptions<?> requestOptions2, int overrideWidth2, int overrideHeight2, Priority priority2, Target<R> target2, RequestListener<R> targetListener2, @Nullable List<RequestListener<R>> requestListeners2, RequestCoordinator requestCoordinator2, Engine engine2, TransitionFactory<? super R> animationFactory2, Executor callbackExecutor2) {
        synchronized (this) {
            this.context = context2;
            this.glideContext = glideContext2;
            this.model = model2;
            this.transcodeClass = transcodeClass2;
            this.requestOptions = requestOptions2;
            this.overrideWidth = overrideWidth2;
            this.overrideHeight = overrideHeight2;
            this.priority = priority2;
            this.target = target2;
            this.targetListener = targetListener2;
            this.requestListeners = requestListeners2;
            this.requestCoordinator = requestCoordinator2;
            this.engine = engine2;
            this.animationFactory = animationFactory2;
            this.callbackExecutor = callbackExecutor2;
            this.status = Status.PENDING;
            if (this.requestOrigin == null && glideContext2.isLoggingRequestOriginsEnabled()) {
                this.requestOrigin = new RuntimeException("Glide request origin trace");
            }
        }
    }

    @NonNull
    public StateVerifier getVerifier() {
        return this.stateVerifier;
    }

    public synchronized void recycle() {
        assertNotCallingCallbacks();
        this.context = null;
        this.glideContext = null;
        this.model = null;
        this.transcodeClass = null;
        this.requestOptions = null;
        this.overrideWidth = -1;
        this.overrideHeight = -1;
        this.target = null;
        this.requestListeners = null;
        this.targetListener = null;
        this.requestCoordinator = null;
        this.animationFactory = null;
        this.loadStatus = null;
        this.errorDrawable = null;
        this.placeholderDrawable = null;
        this.fallbackDrawable = null;
        this.width = -1;
        this.height = -1;
        this.requestOrigin = null;
        POOL.release(this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00a4, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void begin() {
        /*
            r3 = this;
            monitor-enter(r3)
            r3.assertNotCallingCallbacks()     // Catch:{ all -> 0x00ad }
            com.bumptech.glide.util.pool.StateVerifier r0 = r3.stateVerifier     // Catch:{ all -> 0x00ad }
            r0.throwIfRecycled()     // Catch:{ all -> 0x00ad }
            long r0 = com.bumptech.glide.util.LogTime.getLogTime()     // Catch:{ all -> 0x00ad }
            r3.startTime = r0     // Catch:{ all -> 0x00ad }
            java.lang.Object r0 = r3.model     // Catch:{ all -> 0x00ad }
            if (r0 != 0) goto L_0x003a
            int r0 = r3.overrideWidth     // Catch:{ all -> 0x00ad }
            int r1 = r3.overrideHeight     // Catch:{ all -> 0x00ad }
            boolean r0 = com.bumptech.glide.util.Util.isValidDimensions(r0, r1)     // Catch:{ all -> 0x00ad }
            if (r0 == 0) goto L_0x0025
            int r0 = r3.overrideWidth     // Catch:{ all -> 0x00ad }
            r3.width = r0     // Catch:{ all -> 0x00ad }
            int r0 = r3.overrideHeight     // Catch:{ all -> 0x00ad }
            r3.height = r0     // Catch:{ all -> 0x00ad }
        L_0x0025:
            android.graphics.drawable.Drawable r0 = r3.getFallbackDrawable()     // Catch:{ all -> 0x00ad }
            if (r0 != 0) goto L_0x002d
            r0 = 5
            goto L_0x002e
        L_0x002d:
            r0 = 3
        L_0x002e:
            com.bumptech.glide.load.engine.GlideException r1 = new com.bumptech.glide.load.engine.GlideException     // Catch:{ all -> 0x00ad }
            java.lang.String r2 = "Received null model"
            r1.<init>(r2)     // Catch:{ all -> 0x00ad }
            r3.onLoadFailed(r1, r0)     // Catch:{ all -> 0x00ad }
            monitor-exit(r3)
            return
        L_0x003a:
            com.bumptech.glide.request.SingleRequest$Status r0 = r3.status     // Catch:{ all -> 0x00ad }
            com.bumptech.glide.request.SingleRequest$Status r1 = com.bumptech.glide.request.SingleRequest.Status.RUNNING     // Catch:{ all -> 0x00ad }
            if (r0 == r1) goto L_0x00a5
            com.bumptech.glide.request.SingleRequest$Status r0 = r3.status     // Catch:{ all -> 0x00ad }
            com.bumptech.glide.request.SingleRequest$Status r1 = com.bumptech.glide.request.SingleRequest.Status.COMPLETE     // Catch:{ all -> 0x00ad }
            if (r0 != r1) goto L_0x004f
            com.bumptech.glide.load.engine.Resource<R> r0 = r3.resource     // Catch:{ all -> 0x00ad }
            com.bumptech.glide.load.DataSource r1 = com.bumptech.glide.load.DataSource.MEMORY_CACHE     // Catch:{ all -> 0x00ad }
            r3.onResourceReady(r0, r1)     // Catch:{ all -> 0x00ad }
            monitor-exit(r3)
            return
        L_0x004f:
            com.bumptech.glide.request.SingleRequest$Status r0 = com.bumptech.glide.request.SingleRequest.Status.WAITING_FOR_SIZE     // Catch:{ all -> 0x00ad }
            r3.status = r0     // Catch:{ all -> 0x00ad }
            int r0 = r3.overrideWidth     // Catch:{ all -> 0x00ad }
            int r1 = r3.overrideHeight     // Catch:{ all -> 0x00ad }
            boolean r0 = com.bumptech.glide.util.Util.isValidDimensions(r0, r1)     // Catch:{ all -> 0x00ad }
            if (r0 == 0) goto L_0x0065
            int r0 = r3.overrideWidth     // Catch:{ all -> 0x00ad }
            int r1 = r3.overrideHeight     // Catch:{ all -> 0x00ad }
            r3.onSizeReady(r0, r1)     // Catch:{ all -> 0x00ad }
            goto L_0x006a
        L_0x0065:
            com.bumptech.glide.request.target.Target<R> r0 = r3.target     // Catch:{ all -> 0x00ad }
            r0.getSize(r3)     // Catch:{ all -> 0x00ad }
        L_0x006a:
            com.bumptech.glide.request.SingleRequest$Status r0 = r3.status     // Catch:{ all -> 0x00ad }
            com.bumptech.glide.request.SingleRequest$Status r1 = com.bumptech.glide.request.SingleRequest.Status.RUNNING     // Catch:{ all -> 0x00ad }
            if (r0 == r1) goto L_0x0076
            com.bumptech.glide.request.SingleRequest$Status r0 = r3.status     // Catch:{ all -> 0x00ad }
            com.bumptech.glide.request.SingleRequest$Status r1 = com.bumptech.glide.request.SingleRequest.Status.WAITING_FOR_SIZE     // Catch:{ all -> 0x00ad }
            if (r0 != r1) goto L_0x0085
        L_0x0076:
            boolean r0 = r3.canNotifyStatusChanged()     // Catch:{ all -> 0x00ad }
            if (r0 == 0) goto L_0x0085
            com.bumptech.glide.request.target.Target<R> r0 = r3.target     // Catch:{ all -> 0x00ad }
            android.graphics.drawable.Drawable r1 = r3.getPlaceholderDrawable()     // Catch:{ all -> 0x00ad }
            r0.onLoadStarted(r1)     // Catch:{ all -> 0x00ad }
        L_0x0085:
            boolean r0 = IS_VERBOSE_LOGGABLE     // Catch:{ all -> 0x00ad }
            if (r0 == 0) goto L_0x00a3
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ad }
            r0.<init>()     // Catch:{ all -> 0x00ad }
            java.lang.String r1 = "finished run method in "
            r0.append(r1)     // Catch:{ all -> 0x00ad }
            long r1 = r3.startTime     // Catch:{ all -> 0x00ad }
            double r1 = com.bumptech.glide.util.LogTime.getElapsedMillis(r1)     // Catch:{ all -> 0x00ad }
            r0.append(r1)     // Catch:{ all -> 0x00ad }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00ad }
            r3.logV(r0)     // Catch:{ all -> 0x00ad }
        L_0x00a3:
            monitor-exit(r3)
            return
        L_0x00a5:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x00ad }
            java.lang.String r1 = "Cannot restart a running request"
            r0.<init>(r1)     // Catch:{ all -> 0x00ad }
            throw r0     // Catch:{ all -> 0x00ad }
        L_0x00ad:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.SingleRequest.begin():void");
    }

    private void cancel() {
        assertNotCallingCallbacks();
        this.stateVerifier.throwIfRecycled();
        this.target.removeCallback(this);
        if (this.loadStatus != null) {
            this.loadStatus.cancel();
            this.loadStatus = null;
        }
    }

    private void assertNotCallingCallbacks() {
        if (this.isCallingCallbacks) {
            throw new IllegalStateException("You can't start or clear loads in RequestListener or Target callbacks. If you're trying to start a fallback request when a load fails, use RequestBuilder#error(RequestBuilder). Otherwise consider posting your into() or clear() calls to the main thread using a Handler instead.");
        }
    }

    public synchronized void clear() {
        assertNotCallingCallbacks();
        this.stateVerifier.throwIfRecycled();
        if (this.status != Status.CLEARED) {
            cancel();
            if (this.resource != null) {
                releaseResource(this.resource);
            }
            if (canNotifyCleared()) {
                this.target.onLoadCleared(getPlaceholderDrawable());
            }
            this.status = Status.CLEARED;
        }
    }

    private void releaseResource(Resource<?> resource2) {
        this.engine.release(resource2);
        this.resource = null;
    }

    public synchronized boolean isRunning() {
        return this.status == Status.RUNNING || this.status == Status.WAITING_FOR_SIZE;
    }

    public synchronized boolean isComplete() {
        return this.status == Status.COMPLETE;
    }

    public synchronized boolean isResourceSet() {
        return isComplete();
    }

    public synchronized boolean isCleared() {
        return this.status == Status.CLEARED;
    }

    public synchronized boolean isFailed() {
        return this.status == Status.FAILED;
    }

    private Drawable getErrorDrawable() {
        if (this.errorDrawable == null) {
            this.errorDrawable = this.requestOptions.getErrorPlaceholder();
            if (this.errorDrawable == null && this.requestOptions.getErrorId() > 0) {
                this.errorDrawable = loadDrawable(this.requestOptions.getErrorId());
            }
        }
        return this.errorDrawable;
    }

    private Drawable getPlaceholderDrawable() {
        if (this.placeholderDrawable == null) {
            this.placeholderDrawable = this.requestOptions.getPlaceholderDrawable();
            if (this.placeholderDrawable == null && this.requestOptions.getPlaceholderId() > 0) {
                this.placeholderDrawable = loadDrawable(this.requestOptions.getPlaceholderId());
            }
        }
        return this.placeholderDrawable;
    }

    private Drawable getFallbackDrawable() {
        if (this.fallbackDrawable == null) {
            this.fallbackDrawable = this.requestOptions.getFallbackDrawable();
            if (this.fallbackDrawable == null && this.requestOptions.getFallbackId() > 0) {
                this.fallbackDrawable = loadDrawable(this.requestOptions.getFallbackId());
            }
        }
        return this.fallbackDrawable;
    }

    private Drawable loadDrawable(@DrawableRes int resourceId) {
        return DrawableDecoderCompat.getDrawable((Context) this.glideContext, resourceId, this.requestOptions.getTheme() != null ? this.requestOptions.getTheme() : this.context.getTheme());
    }

    private synchronized void setErrorPlaceholder() {
        if (canNotifyStatusChanged()) {
            Drawable error = null;
            if (this.model == null) {
                error = getFallbackDrawable();
            }
            if (error == null) {
                error = getErrorDrawable();
            }
            if (error == null) {
                error = getPlaceholderDrawable();
            }
            this.target.onLoadFailed(error);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00fb, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onSizeReady(int r24, int r25) {
        /*
            r23 = this;
            r15 = r23
            monitor-enter(r23)
            com.bumptech.glide.util.pool.StateVerifier r0 = r15.stateVerifier     // Catch:{ all -> 0x0102 }
            r0.throwIfRecycled()     // Catch:{ all -> 0x0102 }
            boolean r0 = IS_VERBOSE_LOGGABLE     // Catch:{ all -> 0x0102 }
            if (r0 == 0) goto L_0x0026
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0102 }
            r0.<init>()     // Catch:{ all -> 0x0102 }
            java.lang.String r1 = "Got onSizeReady in "
            r0.append(r1)     // Catch:{ all -> 0x0102 }
            long r1 = r15.startTime     // Catch:{ all -> 0x0102 }
            double r1 = com.bumptech.glide.util.LogTime.getElapsedMillis(r1)     // Catch:{ all -> 0x0102 }
            r0.append(r1)     // Catch:{ all -> 0x0102 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0102 }
            r15.logV(r0)     // Catch:{ all -> 0x0102 }
        L_0x0026:
            com.bumptech.glide.request.SingleRequest$Status r0 = r15.status     // Catch:{ all -> 0x0102 }
            com.bumptech.glide.request.SingleRequest$Status r1 = com.bumptech.glide.request.SingleRequest.Status.WAITING_FOR_SIZE     // Catch:{ all -> 0x0102 }
            if (r0 == r1) goto L_0x002e
            monitor-exit(r23)
            return
        L_0x002e:
            com.bumptech.glide.request.SingleRequest$Status r0 = com.bumptech.glide.request.SingleRequest.Status.RUNNING     // Catch:{ all -> 0x0102 }
            r15.status = r0     // Catch:{ all -> 0x0102 }
            com.bumptech.glide.request.BaseRequestOptions<?> r0 = r15.requestOptions     // Catch:{ all -> 0x0102 }
            float r0 = r0.getSizeMultiplier()     // Catch:{ all -> 0x0102 }
            r14 = r24
            int r1 = maybeApplySizeMultiplier(r14, r0)     // Catch:{ all -> 0x0102 }
            r15.width = r1     // Catch:{ all -> 0x0102 }
            r13 = r25
            int r1 = maybeApplySizeMultiplier(r13, r0)     // Catch:{ all -> 0x0102 }
            r15.height = r1     // Catch:{ all -> 0x0102 }
            boolean r1 = IS_VERBOSE_LOGGABLE     // Catch:{ all -> 0x0102 }
            if (r1 == 0) goto L_0x0066
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0102 }
            r1.<init>()     // Catch:{ all -> 0x0102 }
            java.lang.String r2 = "finished setup for calling load in "
            r1.append(r2)     // Catch:{ all -> 0x0102 }
            long r2 = r15.startTime     // Catch:{ all -> 0x0102 }
            double r2 = com.bumptech.glide.util.LogTime.getElapsedMillis(r2)     // Catch:{ all -> 0x0102 }
            r1.append(r2)     // Catch:{ all -> 0x0102 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0102 }
            r15.logV(r1)     // Catch:{ all -> 0x0102 }
        L_0x0066:
            com.bumptech.glide.load.engine.Engine r1 = r15.engine     // Catch:{ all -> 0x0102 }
            com.bumptech.glide.GlideContext r2 = r15.glideContext     // Catch:{ all -> 0x0102 }
            java.lang.Object r3 = r15.model     // Catch:{ all -> 0x0102 }
            com.bumptech.glide.request.BaseRequestOptions<?> r4 = r15.requestOptions     // Catch:{ all -> 0x0102 }
            com.bumptech.glide.load.Key r4 = r4.getSignature()     // Catch:{ all -> 0x0102 }
            int r5 = r15.width     // Catch:{ all -> 0x0102 }
            int r6 = r15.height     // Catch:{ all -> 0x0102 }
            com.bumptech.glide.request.BaseRequestOptions<?> r7 = r15.requestOptions     // Catch:{ all -> 0x0102 }
            java.lang.Class r7 = r7.getResourceClass()     // Catch:{ all -> 0x0102 }
            java.lang.Class<R> r8 = r15.transcodeClass     // Catch:{ all -> 0x0102 }
            com.bumptech.glide.Priority r9 = r15.priority     // Catch:{ all -> 0x0102 }
            com.bumptech.glide.request.BaseRequestOptions<?> r10 = r15.requestOptions     // Catch:{ all -> 0x0102 }
            com.bumptech.glide.load.engine.DiskCacheStrategy r10 = r10.getDiskCacheStrategy()     // Catch:{ all -> 0x0102 }
            com.bumptech.glide.request.BaseRequestOptions<?> r11 = r15.requestOptions     // Catch:{ all -> 0x0102 }
            java.util.Map r11 = r11.getTransformations()     // Catch:{ all -> 0x0102 }
            com.bumptech.glide.request.BaseRequestOptions<?> r12 = r15.requestOptions     // Catch:{ all -> 0x0102 }
            boolean r12 = r12.isTransformationRequired()     // Catch:{ all -> 0x0102 }
            r21 = r0
            com.bumptech.glide.request.BaseRequestOptions<?> r0 = r15.requestOptions     // Catch:{ all -> 0x0102 }
            boolean r0 = r0.isScaleOnlyOrNoTransform()     // Catch:{ all -> 0x0102 }
            com.bumptech.glide.request.BaseRequestOptions<?> r13 = r15.requestOptions     // Catch:{ all -> 0x0102 }
            com.bumptech.glide.load.Options r16 = r13.getOptions()     // Catch:{ all -> 0x0102 }
            com.bumptech.glide.request.BaseRequestOptions<?> r13 = r15.requestOptions     // Catch:{ all -> 0x0102 }
            boolean r17 = r13.isMemoryCacheable()     // Catch:{ all -> 0x0102 }
            com.bumptech.glide.request.BaseRequestOptions<?> r13 = r15.requestOptions     // Catch:{ all -> 0x0102 }
            boolean r18 = r13.getUseUnlimitedSourceGeneratorsPool()     // Catch:{ all -> 0x0102 }
            com.bumptech.glide.request.BaseRequestOptions<?> r13 = r15.requestOptions     // Catch:{ all -> 0x0102 }
            boolean r19 = r13.getUseAnimationPool()     // Catch:{ all -> 0x0102 }
            com.bumptech.glide.request.BaseRequestOptions<?> r13 = r15.requestOptions     // Catch:{ all -> 0x0102 }
            boolean r20 = r13.getOnlyRetrieveFromCache()     // Catch:{ all -> 0x0102 }
            java.util.concurrent.Executor r13 = r15.callbackExecutor     // Catch:{ all -> 0x0102 }
            r22 = r13
            r13 = r0
            r14 = r16
            r15 = r17
            r16 = r18
            r17 = r19
            r18 = r20
            r19 = r23
            r20 = r22
            com.bumptech.glide.load.engine.Engine$LoadStatus r0 = r1.load(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)     // Catch:{ all -> 0x00fe }
            r1 = r23
            r1.loadStatus = r0     // Catch:{ all -> 0x00fc }
            com.bumptech.glide.request.SingleRequest$Status r0 = r1.status     // Catch:{ all -> 0x00fc }
            com.bumptech.glide.request.SingleRequest$Status r2 = com.bumptech.glide.request.SingleRequest.Status.RUNNING     // Catch:{ all -> 0x00fc }
            if (r0 == r2) goto L_0x00dc
            r0 = 0
            r1.loadStatus = r0     // Catch:{ all -> 0x00fc }
        L_0x00dc:
            boolean r0 = IS_VERBOSE_LOGGABLE     // Catch:{ all -> 0x00fc }
            if (r0 == 0) goto L_0x00fa
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00fc }
            r0.<init>()     // Catch:{ all -> 0x00fc }
            java.lang.String r2 = "finished onSizeReady in "
            r0.append(r2)     // Catch:{ all -> 0x00fc }
            long r2 = r1.startTime     // Catch:{ all -> 0x00fc }
            double r2 = com.bumptech.glide.util.LogTime.getElapsedMillis(r2)     // Catch:{ all -> 0x00fc }
            r0.append(r2)     // Catch:{ all -> 0x00fc }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00fc }
            r1.logV(r0)     // Catch:{ all -> 0x00fc }
        L_0x00fa:
            monitor-exit(r23)
            return
        L_0x00fc:
            r0 = move-exception
            goto L_0x0104
        L_0x00fe:
            r0 = move-exception
            r1 = r23
            goto L_0x0104
        L_0x0102:
            r0 = move-exception
            r1 = r15
        L_0x0104:
            monitor-exit(r23)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.SingleRequest.onSizeReady(int, int):void");
    }

    private static int maybeApplySizeMultiplier(int size, float sizeMultiplier) {
        return size == Integer.MIN_VALUE ? size : Math.round(((float) size) * sizeMultiplier);
    }

    private boolean canSetResource() {
        return this.requestCoordinator == null || this.requestCoordinator.canSetImage(this);
    }

    private boolean canNotifyCleared() {
        return this.requestCoordinator == null || this.requestCoordinator.canNotifyCleared(this);
    }

    private boolean canNotifyStatusChanged() {
        return this.requestCoordinator == null || this.requestCoordinator.canNotifyStatusChanged(this);
    }

    private boolean isFirstReadyResource() {
        return this.requestCoordinator == null || !this.requestCoordinator.isAnyResourceSet();
    }

    private void notifyLoadSuccess() {
        if (this.requestCoordinator != null) {
            this.requestCoordinator.onRequestSuccess(this);
        }
    }

    private void notifyLoadFailed() {
        if (this.requestCoordinator != null) {
            this.requestCoordinator.onRequestFailed(this);
        }
    }

    public synchronized void onResourceReady(Resource<?> resource2, DataSource dataSource) {
        this.stateVerifier.throwIfRecycled();
        this.loadStatus = null;
        if (resource2 == null) {
            onLoadFailed(new GlideException("Expected to receive a Resource<R> with an object of " + this.transcodeClass + " inside, but instead got null."));
            return;
        }
        Object received = resource2.get();
        if (received != null) {
            if (this.transcodeClass.isAssignableFrom(received.getClass())) {
                if (!canSetResource()) {
                    releaseResource(resource2);
                    this.status = Status.COMPLETE;
                    return;
                }
                onResourceReady(resource2, received, dataSource);
                return;
            }
        }
        releaseResource(resource2);
        StringBuilder sb = new StringBuilder();
        sb.append("Expected to receive an object of ");
        sb.append(this.transcodeClass);
        sb.append(" but instead got ");
        sb.append(received != null ? received.getClass() : "");
        sb.append("{");
        sb.append(received);
        sb.append("} inside Resource{");
        sb.append(resource2);
        sb.append("}.");
        sb.append(received != null ? "" : " To indicate failure return a null Resource object, rather than a Resource object containing null data.");
        onLoadFailed(new GlideException(sb.toString()));
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00ae A[Catch:{ all -> 0x00c1 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void onResourceReady(com.bumptech.glide.load.engine.Resource<R> r12, R r13, com.bumptech.glide.load.DataSource r14) {
        /*
            r11 = this;
            monitor-enter(r11)
            boolean r0 = r11.isFirstReadyResource()     // Catch:{ all -> 0x00c5 }
            com.bumptech.glide.request.SingleRequest$Status r1 = com.bumptech.glide.request.SingleRequest.Status.COMPLETE     // Catch:{ all -> 0x00c5 }
            r11.status = r1     // Catch:{ all -> 0x00c5 }
            r11.resource = r12     // Catch:{ all -> 0x00c5 }
            com.bumptech.glide.GlideContext r1 = r11.glideContext     // Catch:{ all -> 0x00c5 }
            int r1 = r1.getLogLevel()     // Catch:{ all -> 0x00c5 }
            r2 = 3
            if (r1 > r2) goto L_0x006b
            java.lang.String r1 = "Glide"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c5 }
            r2.<init>()     // Catch:{ all -> 0x00c5 }
            java.lang.String r3 = "Finished loading "
            r2.append(r3)     // Catch:{ all -> 0x00c5 }
            java.lang.Class r3 = r13.getClass()     // Catch:{ all -> 0x00c5 }
            java.lang.String r3 = r3.getSimpleName()     // Catch:{ all -> 0x00c5 }
            r2.append(r3)     // Catch:{ all -> 0x00c5 }
            java.lang.String r3 = " from "
            r2.append(r3)     // Catch:{ all -> 0x00c5 }
            r2.append(r14)     // Catch:{ all -> 0x00c5 }
            java.lang.String r3 = " for "
            r2.append(r3)     // Catch:{ all -> 0x00c5 }
            java.lang.Object r3 = r11.model     // Catch:{ all -> 0x00c5 }
            r2.append(r3)     // Catch:{ all -> 0x00c5 }
            java.lang.String r3 = " with size ["
            r2.append(r3)     // Catch:{ all -> 0x00c5 }
            int r3 = r11.width     // Catch:{ all -> 0x00c5 }
            r2.append(r3)     // Catch:{ all -> 0x00c5 }
            java.lang.String r3 = "x"
            r2.append(r3)     // Catch:{ all -> 0x00c5 }
            int r3 = r11.height     // Catch:{ all -> 0x00c5 }
            r2.append(r3)     // Catch:{ all -> 0x00c5 }
            java.lang.String r3 = "] in "
            r2.append(r3)     // Catch:{ all -> 0x00c5 }
            long r3 = r11.startTime     // Catch:{ all -> 0x00c5 }
            double r3 = com.bumptech.glide.util.LogTime.getElapsedMillis(r3)     // Catch:{ all -> 0x00c5 }
            r2.append(r3)     // Catch:{ all -> 0x00c5 }
            java.lang.String r3 = " ms"
            r2.append(r3)     // Catch:{ all -> 0x00c5 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x00c5 }
            android.util.Log.d(r1, r2)     // Catch:{ all -> 0x00c5 }
        L_0x006b:
            r7 = 1
            r11.isCallingCallbacks = r7     // Catch:{ all -> 0x00c5 }
            r1 = 0
            r8 = 0
            java.util.List<com.bumptech.glide.request.RequestListener<R>> r2 = r11.requestListeners     // Catch:{ all -> 0x00c1 }
            if (r2 == 0) goto L_0x0094
            java.util.List<com.bumptech.glide.request.RequestListener<R>> r2 = r11.requestListeners     // Catch:{ all -> 0x00c1 }
            java.util.Iterator r9 = r2.iterator()     // Catch:{ all -> 0x00c1 }
            r10 = r1
        L_0x007b:
            boolean r1 = r9.hasNext()     // Catch:{ all -> 0x00c1 }
            if (r1 == 0) goto L_0x0095
            java.lang.Object r1 = r9.next()     // Catch:{ all -> 0x00c1 }
            com.bumptech.glide.request.RequestListener r1 = (com.bumptech.glide.request.RequestListener) r1     // Catch:{ all -> 0x00c1 }
            java.lang.Object r3 = r11.model     // Catch:{ all -> 0x00c1 }
            com.bumptech.glide.request.target.Target<R> r4 = r11.target     // Catch:{ all -> 0x00c1 }
            r2 = r13
            r5 = r14
            r6 = r0
            boolean r2 = r1.onResourceReady(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x00c1 }
            r10 = r10 | r2
            goto L_0x007b
        L_0x0094:
            r10 = r1
        L_0x0095:
            com.bumptech.glide.request.RequestListener<R> r1 = r11.targetListener     // Catch:{ all -> 0x00c1 }
            if (r1 == 0) goto L_0x00a9
            com.bumptech.glide.request.RequestListener<R> r1 = r11.targetListener     // Catch:{ all -> 0x00c1 }
            java.lang.Object r3 = r11.model     // Catch:{ all -> 0x00c1 }
            com.bumptech.glide.request.target.Target<R> r4 = r11.target     // Catch:{ all -> 0x00c1 }
            r2 = r13
            r5 = r14
            r6 = r0
            boolean r1 = r1.onResourceReady(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x00c1 }
            if (r1 == 0) goto L_0x00a9
            goto L_0x00aa
        L_0x00a9:
            r7 = r8
        L_0x00aa:
            r1 = r10 | r7
            if (r1 != 0) goto L_0x00b9
            com.bumptech.glide.request.transition.TransitionFactory<? super R> r2 = r11.animationFactory     // Catch:{ all -> 0x00c1 }
            com.bumptech.glide.request.transition.Transition r2 = r2.build(r14, r0)     // Catch:{ all -> 0x00c1 }
            com.bumptech.glide.request.target.Target<R> r3 = r11.target     // Catch:{ all -> 0x00c1 }
            r3.onResourceReady(r13, r2)     // Catch:{ all -> 0x00c1 }
        L_0x00b9:
            r11.isCallingCallbacks = r8     // Catch:{ all -> 0x00c5 }
            r11.notifyLoadSuccess()     // Catch:{ all -> 0x00c5 }
            monitor-exit(r11)
            return
        L_0x00c1:
            r1 = move-exception
            r11.isCallingCallbacks = r8     // Catch:{ all -> 0x00c5 }
            throw r1     // Catch:{ all -> 0x00c5 }
        L_0x00c5:
            r12 = move-exception
            monitor-exit(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.SingleRequest.onResourceReady(com.bumptech.glide.load.engine.Resource, java.lang.Object, com.bumptech.glide.load.DataSource):void");
    }

    public synchronized void onLoadFailed(GlideException e) {
        onLoadFailed(e, 5);
    }

    /* JADX INFO: finally extract failed */
    private synchronized void onLoadFailed(GlideException e, int maxLogLevel) {
        this.stateVerifier.throwIfRecycled();
        e.setOrigin(this.requestOrigin);
        int logLevel = this.glideContext.getLogLevel();
        if (logLevel <= maxLogLevel) {
            Log.w(GLIDE_TAG, "Load failed for " + this.model + " with size [" + this.width + "x" + this.height + "]", e);
            if (logLevel <= 4) {
                e.logRootCauses(GLIDE_TAG);
            }
        }
        this.loadStatus = null;
        this.status = Status.FAILED;
        boolean z = true;
        this.isCallingCallbacks = true;
        boolean anyListenerHandledUpdatingTarget = false;
        try {
            if (this.requestListeners != null) {
                for (RequestListener<R> listener : this.requestListeners) {
                    anyListenerHandledUpdatingTarget |= listener.onLoadFailed(e, this.model, this.target, isFirstReadyResource());
                }
            }
            if (this.targetListener == null || !this.targetListener.onLoadFailed(e, this.model, this.target, isFirstReadyResource())) {
                z = false;
            }
            if (!z && !anyListenerHandledUpdatingTarget) {
                setErrorPlaceholder();
            }
            this.isCallingCallbacks = false;
            notifyLoadFailed();
        } catch (Throwable th) {
            this.isCallingCallbacks = false;
            throw th;
        }
    }

    public synchronized boolean isEquivalentTo(Request o) {
        boolean z = false;
        if (!(o instanceof SingleRequest)) {
            return false;
        }
        SingleRequest<?> that = (SingleRequest) o;
        synchronized (that) {
            if (this.overrideWidth == that.overrideWidth && this.overrideHeight == that.overrideHeight && Util.bothModelsNullEquivalentOrEquals(this.model, that.model) && this.transcodeClass.equals(that.transcodeClass) && this.requestOptions.equals(that.requestOptions) && this.priority == that.priority && listenerCountEquals(that)) {
                z = true;
            }
        }
        return z;
    }

    private synchronized boolean listenerCountEquals(SingleRequest<?> other) {
        boolean z;
        synchronized (other) {
            z = false;
            if ((this.requestListeners == null ? 0 : this.requestListeners.size()) == (other.requestListeners == null ? 0 : other.requestListeners.size())) {
                z = true;
            }
        }
        return z;
    }

    private void logV(String message) {
        Log.v(TAG, message + " this: " + this.tag);
    }
}
