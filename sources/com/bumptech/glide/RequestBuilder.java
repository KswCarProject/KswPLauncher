package com.bumptech.glide;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.CheckResult;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import android.widget.ImageView;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.ErrorRequestCoordinator;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestCoordinator;
import com.bumptech.glide.request.RequestFutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.SingleRequest;
import com.bumptech.glide.request.target.PreloadTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.signature.ApplicationVersionSignature;
import com.bumptech.glide.util.Executors;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class RequestBuilder<TranscodeType> extends BaseRequestOptions<RequestBuilder<TranscodeType>> implements Cloneable, ModelTypes<RequestBuilder<TranscodeType>> {
    protected static final RequestOptions DOWNLOAD_ONLY_OPTIONS = ((RequestOptions) ((RequestOptions) ((RequestOptions) new RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA)).priority(Priority.LOW)).skipMemoryCache(true));
    private final Context context;
    @Nullable
    private RequestBuilder<TranscodeType> errorBuilder;
    private final Glide glide;
    private final GlideContext glideContext;
    private boolean isDefaultTransitionOptionsSet;
    private boolean isModelSet;
    private boolean isThumbnailBuilt;
    @Nullable
    private Object model;
    @Nullable
    private List<RequestListener<TranscodeType>> requestListeners;
    private final RequestManager requestManager;
    @Nullable
    private Float thumbSizeMultiplier;
    @Nullable
    private RequestBuilder<TranscodeType> thumbnailBuilder;
    private final Class<TranscodeType> transcodeClass;
    @NonNull
    private TransitionOptions<?, ? super TranscodeType> transitionOptions;

    @SuppressLint({"CheckResult"})
    protected RequestBuilder(@NonNull Glide glide2, RequestManager requestManager2, Class<TranscodeType> transcodeClass2, Context context2) {
        this.isDefaultTransitionOptionsSet = true;
        this.glide = glide2;
        this.requestManager = requestManager2;
        this.transcodeClass = transcodeClass2;
        this.context = context2;
        this.transitionOptions = requestManager2.getDefaultTransitionOptions(transcodeClass2);
        this.glideContext = glide2.getGlideContext();
        initRequestListeners(requestManager2.getDefaultRequestListeners());
        apply((BaseRequestOptions<?>) requestManager2.getDefaultRequestOptions());
    }

    @SuppressLint({"CheckResult"})
    protected RequestBuilder(Class<TranscodeType> transcodeClass2, RequestBuilder<?> other) {
        this(other.glide, other.requestManager, transcodeClass2, other.context);
        this.model = other.model;
        this.isModelSet = other.isModelSet;
        apply((BaseRequestOptions<?>) other);
    }

    @SuppressLint({"CheckResult"})
    private void initRequestListeners(List<RequestListener<Object>> requestListeners2) {
        for (RequestListener<Object> listener : requestListeners2) {
            addListener(listener);
        }
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> apply(@NonNull BaseRequestOptions<?> requestOptions) {
        Preconditions.checkNotNull(requestOptions);
        return (RequestBuilder) super.apply(requestOptions);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> transition(@NonNull TransitionOptions<?, ? super TranscodeType> transitionOptions2) {
        this.transitionOptions = (TransitionOptions) Preconditions.checkNotNull(transitionOptions2);
        this.isDefaultTransitionOptionsSet = false;
        return this;
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> listener(@Nullable RequestListener<TranscodeType> requestListener) {
        this.requestListeners = null;
        return addListener(requestListener);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> addListener(@Nullable RequestListener<TranscodeType> requestListener) {
        if (requestListener != null) {
            if (this.requestListeners == null) {
                this.requestListeners = new ArrayList();
            }
            this.requestListeners.add(requestListener);
        }
        return this;
    }

    @NonNull
    public RequestBuilder<TranscodeType> error(@Nullable RequestBuilder<TranscodeType> errorBuilder2) {
        this.errorBuilder = errorBuilder2;
        return this;
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> thumbnail(@Nullable RequestBuilder<TranscodeType> thumbnailRequest) {
        this.thumbnailBuilder = thumbnailRequest;
        return this;
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> thumbnail(@Nullable RequestBuilder<TranscodeType>... thumbnails) {
        if (thumbnails == null || thumbnails.length == 0) {
            return thumbnail((RequestBuilder) null);
        }
        RequestBuilder<TranscodeType> previous = null;
        for (int i = thumbnails.length - 1; i >= 0; i--) {
            RequestBuilder<TranscodeType> current = thumbnails[i];
            if (current != null) {
                if (previous == null) {
                    previous = current;
                } else {
                    previous = current.thumbnail(previous);
                }
            }
        }
        return thumbnail(previous);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> thumbnail(float sizeMultiplier) {
        if (sizeMultiplier < 0.0f || sizeMultiplier > 1.0f) {
            throw new IllegalArgumentException("sizeMultiplier must be between 0 and 1");
        }
        this.thumbSizeMultiplier = Float.valueOf(sizeMultiplier);
        return this;
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> load(@Nullable Object model2) {
        return loadGeneric(model2);
    }

    @NonNull
    private RequestBuilder<TranscodeType> loadGeneric(@Nullable Object model2) {
        this.model = model2;
        this.isModelSet = true;
        return this;
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> load(@Nullable Bitmap bitmap) {
        return loadGeneric(bitmap).apply((BaseRequestOptions<?>) RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE));
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> load(@Nullable Drawable drawable) {
        return loadGeneric(drawable).apply((BaseRequestOptions<?>) RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE));
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> load(@Nullable String string) {
        return loadGeneric(string);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> load(@Nullable Uri uri) {
        return loadGeneric(uri);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> load(@Nullable File file) {
        return loadGeneric(file);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> load(@Nullable @RawRes @DrawableRes Integer resourceId) {
        return loadGeneric(resourceId).apply((BaseRequestOptions<?>) RequestOptions.signatureOf(ApplicationVersionSignature.obtain(this.context)));
    }

    @Deprecated
    @CheckResult
    public RequestBuilder<TranscodeType> load(@Nullable URL url) {
        return loadGeneric(url);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> load(@Nullable byte[] model2) {
        RequestBuilder<TranscodeType> result = loadGeneric(model2);
        if (!result.isDiskCacheStrategySet()) {
            result = result.apply((BaseRequestOptions<?>) RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE));
        }
        if (!result.isSkipMemoryCacheSet()) {
            return result.apply((BaseRequestOptions<?>) RequestOptions.skipMemoryCacheOf(true));
        }
        return result;
    }

    @CheckResult
    public RequestBuilder<TranscodeType> clone() {
        RequestBuilder<TranscodeType> result = (RequestBuilder) super.clone();
        result.transitionOptions = result.transitionOptions.clone();
        return result;
    }

    @NonNull
    public <Y extends Target<TranscodeType>> Y into(@NonNull Y target) {
        return into(target, (RequestListener) null, Executors.mainThreadExecutor());
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public <Y extends Target<TranscodeType>> Y into(@NonNull Y target, @Nullable RequestListener<TranscodeType> targetListener, Executor callbackExecutor) {
        return into(target, targetListener, this, callbackExecutor);
    }

    private <Y extends Target<TranscodeType>> Y into(@NonNull Y target, @Nullable RequestListener<TranscodeType> targetListener, BaseRequestOptions<?> options, Executor callbackExecutor) {
        Preconditions.checkNotNull(target);
        if (this.isModelSet) {
            Request request = buildRequest(target, targetListener, options, callbackExecutor);
            Request previous = target.getRequest();
            if (!request.isEquivalentTo(previous) || isSkipMemoryCacheWithCompletePreviousRequest(options, previous)) {
                this.requestManager.clear((Target<?>) target);
                target.setRequest(request);
                this.requestManager.track(target, request);
                return target;
            }
            request.recycle();
            if (!((Request) Preconditions.checkNotNull(previous)).isRunning()) {
                previous.begin();
            }
            return target;
        }
        throw new IllegalArgumentException("You must call #load() before calling #into()");
    }

    private boolean isSkipMemoryCacheWithCompletePreviousRequest(BaseRequestOptions<?> options, Request previous) {
        return !options.isMemoryCacheable() && previous.isComplete();
    }

    @NonNull
    public ViewTarget<ImageView, TranscodeType> into(@NonNull ImageView view) {
        Util.assertMainThread();
        Preconditions.checkNotNull(view);
        BaseRequestOptions baseRequestOptions = this;
        if (!baseRequestOptions.isTransformationSet() && baseRequestOptions.isTransformationAllowed() && view.getScaleType() != null) {
            switch (AnonymousClass1.$SwitchMap$android$widget$ImageView$ScaleType[view.getScaleType().ordinal()]) {
                case 1:
                    baseRequestOptions = baseRequestOptions.clone().optionalCenterCrop();
                    break;
                case 2:
                    baseRequestOptions = baseRequestOptions.clone().optionalCenterInside();
                    break;
                case 3:
                case 4:
                case 5:
                    baseRequestOptions = baseRequestOptions.clone().optionalFitCenter();
                    break;
                case 6:
                    baseRequestOptions = baseRequestOptions.clone().optionalCenterInside();
                    break;
            }
        }
        return (ViewTarget) into(this.glideContext.buildImageViewTarget(view, this.transcodeClass), (RequestListener) null, baseRequestOptions, Executors.mainThreadExecutor());
    }

    @Deprecated
    public FutureTarget<TranscodeType> into(int width, int height) {
        return submit(width, height);
    }

    @NonNull
    public FutureTarget<TranscodeType> submit() {
        return submit(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    @NonNull
    public FutureTarget<TranscodeType> submit(int width, int height) {
        RequestFutureTarget<TranscodeType> target = new RequestFutureTarget<>(width, height);
        return (FutureTarget) into(target, target, Executors.directExecutor());
    }

    @NonNull
    public Target<TranscodeType> preload(int width, int height) {
        return into(PreloadTarget.obtain(this.requestManager, width, height));
    }

    @NonNull
    public Target<TranscodeType> preload() {
        return preload(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    @Deprecated
    @CheckResult
    public <Y extends Target<File>> Y downloadOnly(@NonNull Y target) {
        return getDownloadOnlyRequest().into(target);
    }

    @Deprecated
    @CheckResult
    public FutureTarget<File> downloadOnly(int width, int height) {
        return getDownloadOnlyRequest().submit(width, height);
    }

    /* access modifiers changed from: protected */
    @CheckResult
    @NonNull
    public RequestBuilder<File> getDownloadOnlyRequest() {
        return new RequestBuilder(File.class, this).apply((BaseRequestOptions<?>) DOWNLOAD_ONLY_OPTIONS);
    }

    /* renamed from: com.bumptech.glide.RequestBuilder$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType = new int[ImageView.ScaleType.values().length];

        static {
            $SwitchMap$com$bumptech$glide$Priority = new int[Priority.values().length];
            try {
                $SwitchMap$com$bumptech$glide$Priority[Priority.LOW.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$bumptech$glide$Priority[Priority.NORMAL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$bumptech$glide$Priority[Priority.HIGH.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$bumptech$glide$Priority[Priority.IMMEDIATE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER_CROP.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_START.ordinal()] = 4;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_END.ordinal()] = 5;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_XY.ordinal()] = 6;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER.ordinal()] = 7;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.MATRIX.ordinal()] = 8;
            } catch (NoSuchFieldError e12) {
            }
        }
    }

    @NonNull
    private Priority getThumbnailPriority(@NonNull Priority current) {
        switch (current) {
            case LOW:
                return Priority.NORMAL;
            case NORMAL:
                return Priority.HIGH;
            case HIGH:
            case IMMEDIATE:
                return Priority.IMMEDIATE;
            default:
                throw new IllegalArgumentException("unknown priority: " + getPriority());
        }
    }

    private Request buildRequest(Target<TranscodeType> target, @Nullable RequestListener<TranscodeType> targetListener, BaseRequestOptions<?> requestOptions, Executor callbackExecutor) {
        return buildRequestRecursive(target, targetListener, (RequestCoordinator) null, this.transitionOptions, requestOptions.getPriority(), requestOptions.getOverrideWidth(), requestOptions.getOverrideHeight(), requestOptions, callbackExecutor);
    }

    private Request buildRequestRecursive(Target<TranscodeType> target, @Nullable RequestListener<TranscodeType> targetListener, @Nullable RequestCoordinator parentCoordinator, TransitionOptions<?, ? super TranscodeType> transitionOptions2, Priority priority, int overrideWidth, int overrideHeight, BaseRequestOptions<?> requestOptions, Executor callbackExecutor) {
        ErrorRequestCoordinator errorRequestCoordinator;
        RequestCoordinator parentCoordinator2;
        if (this.errorBuilder != null) {
            RequestCoordinator errorRequestCoordinator2 = new ErrorRequestCoordinator(parentCoordinator);
            errorRequestCoordinator = errorRequestCoordinator2;
            parentCoordinator2 = errorRequestCoordinator2;
        } else {
            errorRequestCoordinator = null;
            parentCoordinator2 = parentCoordinator;
        }
        Request mainRequest = buildThumbnailRequestRecursive(target, targetListener, parentCoordinator2, transitionOptions2, priority, overrideWidth, overrideHeight, requestOptions, callbackExecutor);
        if (errorRequestCoordinator == null) {
            return mainRequest;
        }
        int errorOverrideWidth = this.errorBuilder.getOverrideWidth();
        int errorOverrideHeight = this.errorBuilder.getOverrideHeight();
        if (Util.isValidDimensions(overrideWidth, overrideHeight) && !this.errorBuilder.isValidOverride()) {
            errorOverrideWidth = requestOptions.getOverrideWidth();
            errorOverrideHeight = requestOptions.getOverrideHeight();
        }
        errorRequestCoordinator.setRequests(mainRequest, this.errorBuilder.buildRequestRecursive(target, targetListener, errorRequestCoordinator, this.errorBuilder.transitionOptions, this.errorBuilder.getPriority(), errorOverrideWidth, errorOverrideHeight, this.errorBuilder, callbackExecutor));
        return errorRequestCoordinator;
    }

    /* JADX WARNING: type inference failed for: r34v0, types: [com.bumptech.glide.request.BaseRequestOptions<?>, com.bumptech.glide.request.BaseRequestOptions] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.bumptech.glide.request.Request buildThumbnailRequestRecursive(com.bumptech.glide.request.target.Target<TranscodeType> r27, com.bumptech.glide.request.RequestListener<TranscodeType> r28, @android.support.annotation.Nullable com.bumptech.glide.request.RequestCoordinator r29, com.bumptech.glide.TransitionOptions<?, ? super TranscodeType> r30, com.bumptech.glide.Priority r31, int r32, int r33, com.bumptech.glide.request.BaseRequestOptions<?> r34, java.util.concurrent.Executor r35) {
        /*
            r26 = this;
            r10 = r26
            r11 = r29
            r12 = r31
            com.bumptech.glide.RequestBuilder<TranscodeType> r0 = r10.thumbnailBuilder
            if (r0 == 0) goto L_0x00a1
            boolean r0 = r10.isThumbnailBuilt
            if (r0 != 0) goto L_0x0099
            com.bumptech.glide.RequestBuilder<TranscodeType> r0 = r10.thumbnailBuilder
            com.bumptech.glide.TransitionOptions<?, ? super TranscodeType> r0 = r0.transitionOptions
            com.bumptech.glide.RequestBuilder<TranscodeType> r1 = r10.thumbnailBuilder
            boolean r1 = r1.isDefaultTransitionOptionsSet
            if (r1 == 0) goto L_0x001a
            r0 = r30
        L_0x001a:
            r23 = r0
            com.bumptech.glide.RequestBuilder<TranscodeType> r0 = r10.thumbnailBuilder
            boolean r0 = r0.isPrioritySet()
            if (r0 == 0) goto L_0x002d
            com.bumptech.glide.RequestBuilder<TranscodeType> r0 = r10.thumbnailBuilder
            com.bumptech.glide.Priority r0 = r0.getPriority()
        L_0x002a:
            r18 = r0
            goto L_0x0032
        L_0x002d:
            com.bumptech.glide.Priority r0 = r10.getThumbnailPriority(r12)
            goto L_0x002a
        L_0x0032:
            com.bumptech.glide.RequestBuilder<TranscodeType> r0 = r10.thumbnailBuilder
            int r0 = r0.getOverrideWidth()
            com.bumptech.glide.RequestBuilder<TranscodeType> r1 = r10.thumbnailBuilder
            int r1 = r1.getOverrideHeight()
            boolean r2 = com.bumptech.glide.util.Util.isValidDimensions(r32, r33)
            if (r2 == 0) goto L_0x0054
            com.bumptech.glide.RequestBuilder<TranscodeType> r2 = r10.thumbnailBuilder
            boolean r2 = r2.isValidOverride()
            if (r2 != 0) goto L_0x0054
            int r0 = r34.getOverrideWidth()
            int r1 = r34.getOverrideHeight()
        L_0x0054:
            r24 = r0
            r25 = r1
            com.bumptech.glide.request.ThumbnailRequestCoordinator r0 = new com.bumptech.glide.request.ThumbnailRequestCoordinator
            r0.<init>(r11)
            r15 = r0
            r0 = r26
            r1 = r27
            r2 = r28
            r3 = r34
            r4 = r15
            r5 = r30
            r6 = r31
            r7 = r32
            r8 = r33
            r9 = r35
            com.bumptech.glide.request.Request r0 = r0.obtainRequest(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            r1 = 1
            r10.isThumbnailBuilt = r1
            com.bumptech.glide.RequestBuilder<TranscodeType> r13 = r10.thumbnailBuilder
            com.bumptech.glide.RequestBuilder<TranscodeType> r1 = r10.thumbnailBuilder
            r14 = r27
            r2 = r15
            r15 = r28
            r16 = r2
            r17 = r23
            r19 = r24
            r20 = r25
            r21 = r1
            r22 = r35
            com.bumptech.glide.request.Request r1 = r13.buildRequestRecursive(r14, r15, r16, r17, r18, r19, r20, r21, r22)
            r3 = 0
            r10.isThumbnailBuilt = r3
            r2.setRequests(r0, r1)
            return r2
        L_0x0099:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "You cannot use a request as both the main request and a thumbnail, consider using clone() on the request(s) passed to thumbnail()"
            r0.<init>(r1)
            throw r0
        L_0x00a1:
            java.lang.Float r0 = r10.thumbSizeMultiplier
            if (r0 == 0) goto L_0x00e4
            com.bumptech.glide.request.ThumbnailRequestCoordinator r0 = new com.bumptech.glide.request.ThumbnailRequestCoordinator
            r0.<init>(r11)
            r13 = r0
            r0 = r26
            r1 = r27
            r2 = r28
            r3 = r34
            r4 = r13
            r5 = r30
            r6 = r31
            r7 = r32
            r8 = r33
            r9 = r35
            com.bumptech.glide.request.Request r14 = r0.obtainRequest(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            com.bumptech.glide.request.BaseRequestOptions r0 = r34.clone()
            java.lang.Float r1 = r10.thumbSizeMultiplier
            float r1 = r1.floatValue()
            com.bumptech.glide.request.BaseRequestOptions r15 = r0.sizeMultiplier(r1)
            com.bumptech.glide.Priority r6 = r10.getThumbnailPriority(r12)
            r0 = r26
            r1 = r27
            r3 = r15
            com.bumptech.glide.request.Request r0 = r0.obtainRequest(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            r13.setRequests(r14, r0)
            return r13
        L_0x00e4:
            r0 = r26
            r1 = r27
            r2 = r28
            r3 = r34
            r4 = r29
            r5 = r30
            r6 = r31
            r7 = r32
            r8 = r33
            r9 = r35
            com.bumptech.glide.request.Request r0 = r0.obtainRequest(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.RequestBuilder.buildThumbnailRequestRecursive(com.bumptech.glide.request.target.Target, com.bumptech.glide.request.RequestListener, com.bumptech.glide.request.RequestCoordinator, com.bumptech.glide.TransitionOptions, com.bumptech.glide.Priority, int, int, com.bumptech.glide.request.BaseRequestOptions, java.util.concurrent.Executor):com.bumptech.glide.request.Request");
    }

    private Request obtainRequest(Target<TranscodeType> target, RequestListener<TranscodeType> targetListener, BaseRequestOptions<?> requestOptions, RequestCoordinator requestCoordinator, TransitionOptions<?, ? super TranscodeType> transitionOptions2, Priority priority, int overrideWidth, int overrideHeight, Executor callbackExecutor) {
        return SingleRequest.obtain(this.context, this.glideContext, this.model, this.transcodeClass, requestOptions, overrideWidth, overrideHeight, priority, target, targetListener, this.requestListeners, requestCoordinator, this.glideContext.getEngine(), transitionOptions2.getTransitionFactory(), callbackExecutor);
    }
}
