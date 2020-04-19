package com.bumptech.glide.load.engine;

import com.bumptech.glide.GlideContext;
import com.bumptech.glide.Priority;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DecodeJob;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.model.ModelLoader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

final class DecodeHelper<Transcode> {
    private final List<Key> cacheKeys = new ArrayList();
    private DecodeJob.DiskCacheProvider diskCacheProvider;
    private DiskCacheStrategy diskCacheStrategy;
    private GlideContext glideContext;
    private int height;
    private boolean isCacheKeysSet;
    private boolean isLoadDataSet;
    private boolean isScaleOnlyOrNoTransform;
    private boolean isTransformationRequired;
    private final List<ModelLoader.LoadData<?>> loadData = new ArrayList();
    private Object model;
    private Options options;
    private Priority priority;
    private Class<?> resourceClass;
    private Key signature;
    private Class<Transcode> transcodeClass;
    private Map<Class<?>, Transformation<?>> transformations;
    private int width;

    DecodeHelper() {
    }

    /* access modifiers changed from: package-private */
    public <R> void init(GlideContext glideContext2, Object model2, Key signature2, int width2, int height2, DiskCacheStrategy diskCacheStrategy2, Class<?> resourceClass2, Class<R> transcodeClass2, Priority priority2, Options options2, Map<Class<?>, Transformation<?>> transformations2, boolean isTransformationRequired2, boolean isScaleOnlyOrNoTransform2, DecodeJob.DiskCacheProvider diskCacheProvider2) {
        this.glideContext = glideContext2;
        this.model = model2;
        this.signature = signature2;
        this.width = width2;
        this.height = height2;
        this.diskCacheStrategy = diskCacheStrategy2;
        this.resourceClass = resourceClass2;
        this.diskCacheProvider = diskCacheProvider2;
        this.transcodeClass = transcodeClass2;
        this.priority = priority2;
        this.options = options2;
        this.transformations = transformations2;
        this.isTransformationRequired = isTransformationRequired2;
        this.isScaleOnlyOrNoTransform = isScaleOnlyOrNoTransform2;
    }

    /* access modifiers changed from: package-private */
    public void clear() {
        this.glideContext = null;
        this.model = null;
        this.signature = null;
        this.resourceClass = null;
        this.transcodeClass = null;
        this.options = null;
        this.priority = null;
        this.transformations = null;
        this.diskCacheStrategy = null;
        this.loadData.clear();
        this.isLoadDataSet = false;
        this.cacheKeys.clear();
        this.isCacheKeysSet = false;
    }

    /* access modifiers changed from: package-private */
    public DiskCache getDiskCache() {
        return this.diskCacheProvider.getDiskCache();
    }

    /* access modifiers changed from: package-private */
    public DiskCacheStrategy getDiskCacheStrategy() {
        return this.diskCacheStrategy;
    }

    /* access modifiers changed from: package-private */
    public Priority getPriority() {
        return this.priority;
    }

    /* access modifiers changed from: package-private */
    public Options getOptions() {
        return this.options;
    }

    /* access modifiers changed from: package-private */
    public Key getSignature() {
        return this.signature;
    }

    /* access modifiers changed from: package-private */
    public int getWidth() {
        return this.width;
    }

    /* access modifiers changed from: package-private */
    public int getHeight() {
        return this.height;
    }

    /* access modifiers changed from: package-private */
    public ArrayPool getArrayPool() {
        return this.glideContext.getArrayPool();
    }

    /* access modifiers changed from: package-private */
    public Class<?> getTranscodeClass() {
        return this.transcodeClass;
    }

    /* access modifiers changed from: package-private */
    public Class<?> getModelClass() {
        return this.model.getClass();
    }

    /* access modifiers changed from: package-private */
    public List<Class<?>> getRegisteredResourceClasses() {
        return this.glideContext.getRegistry().getRegisteredResourceClasses(this.model.getClass(), this.resourceClass, this.transcodeClass);
    }

    /* access modifiers changed from: package-private */
    public boolean hasLoadPath(Class<?> dataClass) {
        return getLoadPath(dataClass) != null;
    }

    /* access modifiers changed from: package-private */
    public <Data> LoadPath<Data, ?, Transcode> getLoadPath(Class<Data> dataClass) {
        return this.glideContext.getRegistry().getLoadPath(dataClass, this.resourceClass, this.transcodeClass);
    }

    /* access modifiers changed from: package-private */
    public boolean isScaleOnlyOrNoTransform() {
        return this.isScaleOnlyOrNoTransform;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: com.bumptech.glide.load.Transformation<Z>} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <Z> com.bumptech.glide.load.Transformation<Z> getTransformation(java.lang.Class<Z> r5) {
        /*
            r4 = this;
            java.util.Map<java.lang.Class<?>, com.bumptech.glide.load.Transformation<?>> r0 = r4.transformations
            java.lang.Object r0 = r0.get(r5)
            com.bumptech.glide.load.Transformation r0 = (com.bumptech.glide.load.Transformation) r0
            if (r0 != 0) goto L_0x0035
            java.util.Map<java.lang.Class<?>, com.bumptech.glide.load.Transformation<?>> r1 = r4.transformations
            java.util.Set r1 = r1.entrySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x0014:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0035
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getKey()
            java.lang.Class r3 = (java.lang.Class) r3
            boolean r3 = r3.isAssignableFrom(r5)
            if (r3 == 0) goto L_0x0034
            java.lang.Object r1 = r2.getValue()
            r0 = r1
            com.bumptech.glide.load.Transformation r0 = (com.bumptech.glide.load.Transformation) r0
            goto L_0x0035
        L_0x0034:
            goto L_0x0014
        L_0x0035:
            if (r0 != 0) goto L_0x0065
            java.util.Map<java.lang.Class<?>, com.bumptech.glide.load.Transformation<?>> r1 = r4.transformations
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x0060
            boolean r1 = r4.isTransformationRequired
            if (r1 != 0) goto L_0x0044
            goto L_0x0060
        L_0x0044:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Missing transformation for "
            r2.append(r3)
            r2.append(r5)
            java.lang.String r3 = ". If you wish to ignore unknown resource types, use the optional transformation methods."
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L_0x0060:
            com.bumptech.glide.load.resource.UnitTransformation r1 = com.bumptech.glide.load.resource.UnitTransformation.get()
            return r1
        L_0x0065:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.DecodeHelper.getTransformation(java.lang.Class):com.bumptech.glide.load.Transformation");
    }

    /* access modifiers changed from: package-private */
    public boolean isResourceEncoderAvailable(Resource<?> resource) {
        return this.glideContext.getRegistry().isResourceEncoderAvailable(resource);
    }

    /* access modifiers changed from: package-private */
    public <Z> ResourceEncoder<Z> getResultEncoder(Resource<Z> resource) {
        return this.glideContext.getRegistry().getResultEncoder(resource);
    }

    /* access modifiers changed from: package-private */
    public List<ModelLoader<File, ?>> getModelLoaders(File file) throws Registry.NoModelLoaderAvailableException {
        return this.glideContext.getRegistry().getModelLoaders(file);
    }

    /* access modifiers changed from: package-private */
    public boolean isSourceKey(Key key) {
        List<ModelLoader.LoadData<?>> loadData2 = getLoadData();
        int size = loadData2.size();
        for (int i = 0; i < size; i++) {
            if (loadData2.get(i).sourceKey.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public List<ModelLoader.LoadData<?>> getLoadData() {
        if (!this.isLoadDataSet) {
            this.isLoadDataSet = true;
            this.loadData.clear();
            List<ModelLoader<Object, ?>> modelLoaders = this.glideContext.getRegistry().getModelLoaders(this.model);
            int size = modelLoaders.size();
            for (int i = 0; i < size; i++) {
                ModelLoader.LoadData<?> current = modelLoaders.get(i).buildLoadData(this.model, this.width, this.height, this.options);
                if (current != null) {
                    this.loadData.add(current);
                }
            }
        }
        return this.loadData;
    }

    /* access modifiers changed from: package-private */
    public List<Key> getCacheKeys() {
        if (!this.isCacheKeysSet) {
            this.isCacheKeysSet = true;
            this.cacheKeys.clear();
            List<ModelLoader.LoadData<?>> loadData2 = getLoadData();
            int size = loadData2.size();
            for (int i = 0; i < size; i++) {
                ModelLoader.LoadData<?> data = loadData2.get(i);
                if (!this.cacheKeys.contains(data.sourceKey)) {
                    this.cacheKeys.add(data.sourceKey);
                }
                for (int j = 0; j < data.alternateKeys.size(); j++) {
                    if (!this.cacheKeys.contains(data.alternateKeys.get(j))) {
                        this.cacheKeys.add(data.alternateKeys.get(j));
                    }
                }
            }
        }
        return this.cacheKeys;
    }

    /* access modifiers changed from: package-private */
    public <X> Encoder<X> getSourceEncoder(X data) throws Registry.NoSourceEncoderAvailableException {
        return this.glideContext.getRegistry().getSourceEncoder(data);
    }
}
