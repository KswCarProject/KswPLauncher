package com.bumptech.glide.load.engine;

import android.os.Build;
import android.support.v4.util.Pools;
import android.util.Log;
import com.bumptech.glide.GlideContext;
import com.bumptech.glide.Priority;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.data.DataRewinder;
import com.bumptech.glide.load.engine.DataFetcherGenerator;
import com.bumptech.glide.load.engine.DecodePath;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.resource.bitmap.Downsampler;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.pool.FactoryPools;
import com.bumptech.glide.util.pool.GlideTrace;
import com.bumptech.glide.util.pool.StateVerifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class DecodeJob<R> implements DataFetcherGenerator.FetcherReadyCallback, Runnable, Comparable<DecodeJob<?>>, FactoryPools.Poolable {
    private static final String TAG = "DecodeJob";
    private Callback<R> callback;
    private Key currentAttemptingKey;
    private Object currentData;
    private DataSource currentDataSource;
    private DataFetcher<?> currentFetcher;
    private volatile DataFetcherGenerator currentGenerator;
    private Key currentSourceKey;
    private Thread currentThread;
    private final DecodeHelper<R> decodeHelper = new DecodeHelper<>();
    private final DeferredEncodeManager<?> deferredEncodeManager = new DeferredEncodeManager<>();
    private final DiskCacheProvider diskCacheProvider;
    private DiskCacheStrategy diskCacheStrategy;
    private GlideContext glideContext;
    private int height;
    private volatile boolean isCallbackNotified;
    private volatile boolean isCancelled;
    private EngineKey loadKey;
    private Object model;
    private boolean onlyRetrieveFromCache;
    private Options options;
    private int order;
    private final Pools.Pool<DecodeJob<?>> pool;
    private Priority priority;
    private final ReleaseManager releaseManager = new ReleaseManager();
    private RunReason runReason;
    private Key signature;
    private Stage stage;
    private long startFetchTime;
    private final StateVerifier stateVerifier = StateVerifier.newInstance();
    private final List<Throwable> throwables = new ArrayList();
    private int width;

    interface Callback<R> {
        void onLoadFailed(GlideException glideException);

        void onResourceReady(Resource<R> resource, DataSource dataSource);

        void reschedule(DecodeJob<?> decodeJob);
    }

    interface DiskCacheProvider {
        DiskCache getDiskCache();
    }

    private enum RunReason {
        INITIALIZE,
        SWITCH_TO_SOURCE_SERVICE,
        DECODE_DATA
    }

    private enum Stage {
        INITIALIZE,
        RESOURCE_CACHE,
        DATA_CACHE,
        SOURCE,
        ENCODE,
        FINISHED
    }

    DecodeJob(DiskCacheProvider diskCacheProvider2, Pools.Pool<DecodeJob<?>> pool2) {
        this.diskCacheProvider = diskCacheProvider2;
        this.pool = pool2;
    }

    /* access modifiers changed from: package-private */
    public DecodeJob<R> init(GlideContext glideContext2, Object model2, EngineKey loadKey2, Key signature2, int width2, int height2, Class<?> resourceClass, Class<R> transcodeClass, Priority priority2, DiskCacheStrategy diskCacheStrategy2, Map<Class<?>, Transformation<?>> transformations, boolean isTransformationRequired, boolean isScaleOnlyOrNoTransform, boolean onlyRetrieveFromCache2, Options options2, Callback<R> callback2, int order2) {
        int i = width2;
        int i2 = height2;
        DiskCacheStrategy diskCacheStrategy3 = diskCacheStrategy2;
        this.decodeHelper.init(glideContext2, model2, signature2, i, i2, diskCacheStrategy3, resourceClass, transcodeClass, priority2, options2, transformations, isTransformationRequired, isScaleOnlyOrNoTransform, this.diskCacheProvider);
        this.glideContext = glideContext2;
        this.signature = signature2;
        this.priority = priority2;
        this.loadKey = loadKey2;
        this.width = i;
        this.height = i2;
        this.diskCacheStrategy = diskCacheStrategy3;
        this.onlyRetrieveFromCache = onlyRetrieveFromCache2;
        this.options = options2;
        this.callback = callback2;
        this.order = order2;
        this.runReason = RunReason.INITIALIZE;
        this.model = model2;
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean willDecodeFromCache() {
        Stage firstStage = getNextStage(Stage.INITIALIZE);
        return firstStage == Stage.RESOURCE_CACHE || firstStage == Stage.DATA_CACHE;
    }

    /* access modifiers changed from: package-private */
    public void release(boolean isRemovedFromQueue) {
        if (this.releaseManager.release(isRemovedFromQueue)) {
            releaseInternal();
        }
    }

    private void onEncodeComplete() {
        if (this.releaseManager.onEncodeComplete()) {
            releaseInternal();
        }
    }

    private void onLoadFailed() {
        if (this.releaseManager.onFailed()) {
            releaseInternal();
        }
    }

    private void releaseInternal() {
        this.releaseManager.reset();
        this.deferredEncodeManager.clear();
        this.decodeHelper.clear();
        this.isCallbackNotified = false;
        this.glideContext = null;
        this.signature = null;
        this.options = null;
        this.priority = null;
        this.loadKey = null;
        this.callback = null;
        this.stage = null;
        this.currentGenerator = null;
        this.currentThread = null;
        this.currentSourceKey = null;
        this.currentData = null;
        this.currentDataSource = null;
        this.currentFetcher = null;
        this.startFetchTime = 0;
        this.isCancelled = false;
        this.model = null;
        this.throwables.clear();
        this.pool.release(this);
    }

    public int compareTo(DecodeJob<?> other) {
        int result = getPriority() - other.getPriority();
        if (result == 0) {
            return this.order - other.order;
        }
        return result;
    }

    private int getPriority() {
        return this.priority.ordinal();
    }

    public void cancel() {
        this.isCancelled = true;
        DataFetcherGenerator local = this.currentGenerator;
        if (local != null) {
            local.cancel();
        }
    }

    public void run() {
        GlideTrace.beginSectionFormat("DecodeJob#run(model=%s)", this.model);
        DataFetcher<?> localFetcher = this.currentFetcher;
        try {
            if (this.isCancelled) {
                notifyFailed();
                if (localFetcher != null) {
                    localFetcher.cleanup();
                }
                GlideTrace.endSection();
                return;
            }
            runWrapped();
            if (localFetcher != null) {
                localFetcher.cleanup();
            }
            GlideTrace.endSection();
        } catch (CallbackException e) {
            throw e;
        } catch (Throwable e2) {
            if (localFetcher != null) {
                localFetcher.cleanup();
            }
            GlideTrace.endSection();
            throw e2;
        }
    }

    private void runWrapped() {
        switch (AnonymousClass1.$SwitchMap$com$bumptech$glide$load$engine$DecodeJob$RunReason[this.runReason.ordinal()]) {
            case 1:
                this.stage = getNextStage(Stage.INITIALIZE);
                this.currentGenerator = getNextGenerator();
                runGenerators();
                return;
            case 2:
                runGenerators();
                return;
            case 3:
                decodeFromRetrievedData();
                return;
            default:
                throw new IllegalStateException("Unrecognized run reason: " + this.runReason);
        }
    }

    private DataFetcherGenerator getNextGenerator() {
        switch (AnonymousClass1.$SwitchMap$com$bumptech$glide$load$engine$DecodeJob$Stage[this.stage.ordinal()]) {
            case 1:
                return new ResourceCacheGenerator(this.decodeHelper, this);
            case 2:
                return new DataCacheGenerator(this.decodeHelper, this);
            case 3:
                return new SourceGenerator(this.decodeHelper, this);
            case 4:
                return null;
            default:
                throw new IllegalStateException("Unrecognized stage: " + this.stage);
        }
    }

    private void runGenerators() {
        this.currentThread = Thread.currentThread();
        this.startFetchTime = LogTime.getLogTime();
        boolean isStarted = false;
        while (!this.isCancelled && this.currentGenerator != null) {
            boolean startNext = this.currentGenerator.startNext();
            isStarted = startNext;
            if (startNext) {
                break;
            }
            this.stage = getNextStage(this.stage);
            this.currentGenerator = getNextGenerator();
            if (this.stage == Stage.SOURCE) {
                reschedule();
                return;
            }
        }
        if ((this.stage == Stage.FINISHED || this.isCancelled) && !isStarted) {
            notifyFailed();
        }
    }

    private void notifyFailed() {
        setNotifiedOrThrow();
        this.callback.onLoadFailed(new GlideException("Failed to load resource", (List<Throwable>) new ArrayList(this.throwables)));
        onLoadFailed();
    }

    private void notifyComplete(Resource<R> resource, DataSource dataSource) {
        setNotifiedOrThrow();
        this.callback.onResourceReady(resource, dataSource);
    }

    private void setNotifiedOrThrow() {
        Throwable lastThrown;
        this.stateVerifier.throwIfRecycled();
        if (this.isCallbackNotified) {
            if (this.throwables.isEmpty()) {
                lastThrown = null;
            } else {
                List<Throwable> list = this.throwables;
                lastThrown = list.get(list.size() - 1);
            }
            throw new IllegalStateException("Already notified", lastThrown);
        }
        this.isCallbackNotified = true;
    }

    private Stage getNextStage(Stage current) {
        switch (AnonymousClass1.$SwitchMap$com$bumptech$glide$load$engine$DecodeJob$Stage[current.ordinal()]) {
            case 1:
                return this.diskCacheStrategy.decodeCachedData() ? Stage.DATA_CACHE : getNextStage(Stage.DATA_CACHE);
            case 2:
                return this.onlyRetrieveFromCache ? Stage.FINISHED : Stage.SOURCE;
            case 3:
            case 4:
                return Stage.FINISHED;
            case 5:
                return this.diskCacheStrategy.decodeCachedResource() ? Stage.RESOURCE_CACHE : getNextStage(Stage.RESOURCE_CACHE);
            default:
                throw new IllegalArgumentException("Unrecognized stage: " + current);
        }
    }

    public void reschedule() {
        this.runReason = RunReason.SWITCH_TO_SOURCE_SERVICE;
        this.callback.reschedule(this);
    }

    public void onDataFetcherReady(Key sourceKey, Object data, DataFetcher<?> fetcher, DataSource dataSource, Key attemptedKey) {
        this.currentSourceKey = sourceKey;
        this.currentData = data;
        this.currentFetcher = fetcher;
        this.currentDataSource = dataSource;
        this.currentAttemptingKey = attemptedKey;
        if (Thread.currentThread() != this.currentThread) {
            this.runReason = RunReason.DECODE_DATA;
            this.callback.reschedule(this);
            return;
        }
        GlideTrace.beginSection("DecodeJob.decodeFromRetrievedData");
        try {
            decodeFromRetrievedData();
        } finally {
            GlideTrace.endSection();
        }
    }

    public void onDataFetcherFailed(Key attemptedKey, Exception e, DataFetcher<?> fetcher, DataSource dataSource) {
        fetcher.cleanup();
        GlideException exception = new GlideException("Fetching data failed", (Throwable) e);
        exception.setLoggingDetails(attemptedKey, dataSource, fetcher.getDataClass());
        this.throwables.add(exception);
        if (Thread.currentThread() != this.currentThread) {
            this.runReason = RunReason.SWITCH_TO_SOURCE_SERVICE;
            this.callback.reschedule(this);
            return;
        }
        runGenerators();
    }

    private void decodeFromRetrievedData() {
        if (Log.isLoggable(TAG, 2)) {
            logWithTimeAndKey("Retrieved data", this.startFetchTime, "data: " + this.currentData + ", cache key: " + this.currentSourceKey + ", fetcher: " + this.currentFetcher);
        }
        Resource<R> resource = null;
        try {
            resource = decodeFromData(this.currentFetcher, this.currentData, this.currentDataSource);
        } catch (GlideException e) {
            e.setLoggingDetails(this.currentAttemptingKey, this.currentDataSource);
            this.throwables.add(e);
        }
        if (resource != null) {
            notifyEncodeAndRelease(resource, this.currentDataSource);
        } else {
            runGenerators();
        }
    }

    private void notifyEncodeAndRelease(Resource<R> resource, DataSource dataSource) {
        if (resource instanceof Initializable) {
            ((Initializable) resource).initialize();
        }
        Resource<R> result = resource;
        LockedResource<R> lockedResource = null;
        if (this.deferredEncodeManager.hasResourceToEncode()) {
            lockedResource = LockedResource.obtain(resource);
            result = lockedResource;
        }
        notifyComplete(result, dataSource);
        this.stage = Stage.ENCODE;
        try {
            if (this.deferredEncodeManager.hasResourceToEncode()) {
                this.deferredEncodeManager.encode(this.diskCacheProvider, this.options);
            }
            onEncodeComplete();
        } finally {
            if (lockedResource != null) {
                lockedResource.unlock();
            }
        }
    }

    private <Data> Resource<R> decodeFromData(DataFetcher<?> fetcher, Data data, DataSource dataSource) throws GlideException {
        if (data == null) {
            fetcher.cleanup();
            return null;
        }
        try {
            long startTime = LogTime.getLogTime();
            Resource<R> result = decodeFromFetcher(data, dataSource);
            if (Log.isLoggable(TAG, 2)) {
                logWithTimeAndKey("Decoded result " + result, startTime);
            }
            return result;
        } finally {
            fetcher.cleanup();
        }
    }

    private <Data> Resource<R> decodeFromFetcher(Data data, DataSource dataSource) throws GlideException {
        return runLoadPath(data, dataSource, this.decodeHelper.getLoadPath(data.getClass()));
    }

    private Options getOptionsWithHardwareConfig(DataSource dataSource) {
        Options options2 = this.options;
        if (Build.VERSION.SDK_INT < 26) {
            return options2;
        }
        boolean isHardwareConfigSafe = dataSource == DataSource.RESOURCE_DISK_CACHE || this.decodeHelper.isScaleOnlyOrNoTransform();
        Boolean isHardwareConfigAllowed = (Boolean) options2.get(Downsampler.ALLOW_HARDWARE_CONFIG);
        if (isHardwareConfigAllowed != null && (!isHardwareConfigAllowed.booleanValue() || isHardwareConfigSafe)) {
            return options2;
        }
        Options options3 = new Options();
        options3.putAll(this.options);
        options3.set(Downsampler.ALLOW_HARDWARE_CONFIG, Boolean.valueOf(isHardwareConfigSafe));
        return options3;
    }

    private <Data, ResourceType> Resource<R> runLoadPath(Data data, DataSource dataSource, LoadPath<Data, ResourceType, R> path) throws GlideException {
        Options options2 = getOptionsWithHardwareConfig(dataSource);
        DataRewinder<Data> rewinder = this.glideContext.getRegistry().getRewinder(data);
        try {
            return path.load(rewinder, options2, this.width, this.height, new DecodeCallback(dataSource));
        } finally {
            rewinder.cleanup();
        }
    }

    private void logWithTimeAndKey(String message, long startTime) {
        logWithTimeAndKey(message, startTime, (String) null);
    }

    private void logWithTimeAndKey(String message, long startTime, String extraArgs) {
        Log.v(TAG, message + " in " + LogTime.getElapsedMillis(startTime) + ", load key: " + this.loadKey + (extraArgs != null ? ", " + extraArgs : "") + ", thread: " + Thread.currentThread().getName());
    }

    public StateVerifier getVerifier() {
        return this.stateVerifier;
    }

    /* access modifiers changed from: package-private */
    public <Z> Resource<Z> onResourceDecoded(DataSource dataSource, Resource<Z> decoded) {
        Resource<Z> transformed;
        Transformation<Z> appliedTransformation;
        ResourceEncoder<Z> encoder;
        EncodeStrategy encodeStrategy;
        Key key;
        DataSource dataSource2 = dataSource;
        Resource<Z> resource = decoded;
        Class<?> cls = decoded.get().getClass();
        Resource<Z> transformed2 = decoded;
        if (dataSource2 != DataSource.RESOURCE_DISK_CACHE) {
            Transformation<Z> appliedTransformation2 = this.decodeHelper.getTransformation(cls);
            appliedTransformation = appliedTransformation2;
            transformed = appliedTransformation2.transform(this.glideContext, resource, this.width, this.height);
        } else {
            appliedTransformation = null;
            transformed = transformed2;
        }
        if (!resource.equals(transformed)) {
            decoded.recycle();
        }
        if (this.decodeHelper.isResourceEncoderAvailable(transformed)) {
            ResourceEncoder<Z> encoder2 = this.decodeHelper.getResultEncoder(transformed);
            encoder = encoder2;
            encodeStrategy = encoder2.getEncodeStrategy(this.options);
        } else {
            encoder = null;
            encodeStrategy = EncodeStrategy.NONE;
        }
        Resource<Z> result = transformed;
        boolean isFromAlternateCacheKey = !this.decodeHelper.isSourceKey(this.currentSourceKey);
        if (!this.diskCacheStrategy.isResourceCacheable(isFromAlternateCacheKey, dataSource2, encodeStrategy)) {
            boolean z = isFromAlternateCacheKey;
            EncodeStrategy encodeStrategy2 = encodeStrategy;
            return result;
        } else if (encoder != null) {
            switch (AnonymousClass1.$SwitchMap$com$bumptech$glide$load$EncodeStrategy[encodeStrategy.ordinal()]) {
                case 1:
                    EncodeStrategy encodeStrategy3 = encodeStrategy;
                    key = new DataCacheKey(this.currentSourceKey, this.signature);
                    break;
                case 2:
                    boolean z2 = isFromAlternateCacheKey;
                    EncodeStrategy encodeStrategy4 = encodeStrategy;
                    key = new ResourceCacheKey(this.decodeHelper.getArrayPool(), this.currentSourceKey, this.signature, this.width, this.height, appliedTransformation, cls, this.options);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown strategy: " + encodeStrategy);
            }
            LockedResource<Z> lockedResult = LockedResource.obtain(transformed);
            this.deferredEncodeManager.init(key, encoder, lockedResult);
            return lockedResult;
        } else {
            throw new Registry.NoResultEncoderAvailableException(transformed.get().getClass());
        }
    }

    /* renamed from: com.bumptech.glide.load.engine.DecodeJob$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$bumptech$glide$load$EncodeStrategy;
        static final /* synthetic */ int[] $SwitchMap$com$bumptech$glide$load$engine$DecodeJob$RunReason;
        static final /* synthetic */ int[] $SwitchMap$com$bumptech$glide$load$engine$DecodeJob$Stage;

        static {
            int[] iArr = new int[EncodeStrategy.values().length];
            $SwitchMap$com$bumptech$glide$load$EncodeStrategy = iArr;
            try {
                iArr[EncodeStrategy.SOURCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$bumptech$glide$load$EncodeStrategy[EncodeStrategy.TRANSFORMED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            int[] iArr2 = new int[Stage.values().length];
            $SwitchMap$com$bumptech$glide$load$engine$DecodeJob$Stage = iArr2;
            try {
                iArr2[Stage.RESOURCE_CACHE.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$bumptech$glide$load$engine$DecodeJob$Stage[Stage.DATA_CACHE.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$bumptech$glide$load$engine$DecodeJob$Stage[Stage.SOURCE.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$bumptech$glide$load$engine$DecodeJob$Stage[Stage.FINISHED.ordinal()] = 4;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$bumptech$glide$load$engine$DecodeJob$Stage[Stage.INITIALIZE.ordinal()] = 5;
            } catch (NoSuchFieldError e7) {
            }
            int[] iArr3 = new int[RunReason.values().length];
            $SwitchMap$com$bumptech$glide$load$engine$DecodeJob$RunReason = iArr3;
            try {
                iArr3[RunReason.INITIALIZE.ordinal()] = 1;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$bumptech$glide$load$engine$DecodeJob$RunReason[RunReason.SWITCH_TO_SOURCE_SERVICE.ordinal()] = 2;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$bumptech$glide$load$engine$DecodeJob$RunReason[RunReason.DECODE_DATA.ordinal()] = 3;
            } catch (NoSuchFieldError e10) {
            }
        }
    }

    private final class DecodeCallback<Z> implements DecodePath.DecodeCallback<Z> {
        private final DataSource dataSource;

        DecodeCallback(DataSource dataSource2) {
            this.dataSource = dataSource2;
        }

        public Resource<Z> onResourceDecoded(Resource<Z> decoded) {
            return DecodeJob.this.onResourceDecoded(this.dataSource, decoded);
        }
    }

    private static class ReleaseManager {
        private boolean isEncodeComplete;
        private boolean isFailed;
        private boolean isReleased;

        ReleaseManager() {
        }

        /* access modifiers changed from: package-private */
        public synchronized boolean release(boolean isRemovedFromQueue) {
            this.isReleased = true;
            return isComplete(isRemovedFromQueue);
        }

        /* access modifiers changed from: package-private */
        public synchronized boolean onEncodeComplete() {
            this.isEncodeComplete = true;
            return isComplete(false);
        }

        /* access modifiers changed from: package-private */
        public synchronized boolean onFailed() {
            this.isFailed = true;
            return isComplete(false);
        }

        /* access modifiers changed from: package-private */
        public synchronized void reset() {
            this.isEncodeComplete = false;
            this.isReleased = false;
            this.isFailed = false;
        }

        private boolean isComplete(boolean isRemovedFromQueue) {
            return (this.isFailed || isRemovedFromQueue || this.isEncodeComplete) && this.isReleased;
        }
    }

    private static class DeferredEncodeManager<Z> {
        private ResourceEncoder<Z> encoder;
        private Key key;
        private LockedResource<Z> toEncode;

        DeferredEncodeManager() {
        }

        /* access modifiers changed from: package-private */
        public <X> void init(Key key2, ResourceEncoder<X> encoder2, LockedResource<X> toEncode2) {
            this.key = key2;
            this.encoder = encoder2;
            this.toEncode = toEncode2;
        }

        /* access modifiers changed from: package-private */
        public void encode(DiskCacheProvider diskCacheProvider, Options options) {
            GlideTrace.beginSection("DecodeJob.encode");
            try {
                diskCacheProvider.getDiskCache().put(this.key, new DataCacheWriter(this.encoder, this.toEncode, options));
            } finally {
                this.toEncode.unlock();
                GlideTrace.endSection();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean hasResourceToEncode() {
            return this.toEncode != null;
        }

        /* access modifiers changed from: package-private */
        public void clear() {
            this.key = null;
            this.encoder = null;
            this.toEncode = null;
        }
    }
}
