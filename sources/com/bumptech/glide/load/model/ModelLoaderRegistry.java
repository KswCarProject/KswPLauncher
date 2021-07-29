package com.bumptech.glide.load.model;

import android.support.v4.util.Pools;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelLoaderRegistry {
    private final ModelLoaderCache cache;
    private final MultiModelLoaderFactory multiModelLoaderFactory;

    public ModelLoaderRegistry(Pools.Pool<List<Throwable>> throwableListPool) {
        this(new MultiModelLoaderFactory(throwableListPool));
    }

    private ModelLoaderRegistry(MultiModelLoaderFactory multiModelLoaderFactory2) {
        this.cache = new ModelLoaderCache();
        this.multiModelLoaderFactory = multiModelLoaderFactory2;
    }

    public synchronized <Model, Data> void append(Class<Model> modelClass, Class<Data> dataClass, ModelLoaderFactory<? extends Model, ? extends Data> factory) {
        this.multiModelLoaderFactory.append(modelClass, dataClass, factory);
        this.cache.clear();
    }

    public synchronized <Model, Data> void prepend(Class<Model> modelClass, Class<Data> dataClass, ModelLoaderFactory<? extends Model, ? extends Data> factory) {
        this.multiModelLoaderFactory.prepend(modelClass, dataClass, factory);
        this.cache.clear();
    }

    public synchronized <Model, Data> void remove(Class<Model> modelClass, Class<Data> dataClass) {
        tearDown(this.multiModelLoaderFactory.remove(modelClass, dataClass));
        this.cache.clear();
    }

    public synchronized <Model, Data> void replace(Class<Model> modelClass, Class<Data> dataClass, ModelLoaderFactory<? extends Model, ? extends Data> factory) {
        tearDown(this.multiModelLoaderFactory.replace(modelClass, dataClass, factory));
        this.cache.clear();
    }

    private <Model, Data> void tearDown(List<ModelLoaderFactory<? extends Model, ? extends Data>> factories) {
        for (ModelLoaderFactory<? extends Model, ? extends Data> factory : factories) {
            factory.teardown();
        }
    }

    public <A> List<ModelLoader<A, ?>> getModelLoaders(A model) {
        List<ModelLoader<A, ?>> modelLoaders = getModelLoadersForClass(getClass(model));
        int size = modelLoaders.size();
        boolean isEmpty = true;
        List<ModelLoader<A, ?>> filteredLoaders = Collections.emptyList();
        for (int i = 0; i < size; i++) {
            ModelLoader<A, ?> loader = modelLoaders.get(i);
            if (loader.handles(model)) {
                if (isEmpty) {
                    filteredLoaders = new ArrayList<>(size - i);
                    isEmpty = false;
                }
                filteredLoaders.add(loader);
            }
        }
        return filteredLoaders;
    }

    public synchronized <Model, Data> ModelLoader<Model, Data> build(Class<Model> modelClass, Class<Data> dataClass) {
        return this.multiModelLoaderFactory.build(modelClass, dataClass);
    }

    public synchronized List<Class<?>> getDataClasses(Class<?> modelClass) {
        return this.multiModelLoaderFactory.getDataClasses(modelClass);
    }

    private synchronized <A> List<ModelLoader<A, ?>> getModelLoadersForClass(Class<A> modelClass) {
        List<ModelLoader<A, ?>> loaders;
        loaders = this.cache.get(modelClass);
        if (loaders == null) {
            loaders = Collections.unmodifiableList(this.multiModelLoaderFactory.build(modelClass));
            this.cache.put(modelClass, loaders);
        }
        return loaders;
    }

    private static <A> Class<A> getClass(A model) {
        return model.getClass();
    }

    private static class ModelLoaderCache {
        private final Map<Class<?>, Entry<?>> cachedModelLoaders = new HashMap();

        ModelLoaderCache() {
        }

        public void clear() {
            this.cachedModelLoaders.clear();
        }

        public <Model> void put(Class<Model> modelClass, List<ModelLoader<Model, ?>> loaders) {
            if (this.cachedModelLoaders.put(modelClass, new Entry(loaders)) != null) {
                throw new IllegalStateException("Already cached loaders for model: " + modelClass);
            }
        }

        public <Model> List<ModelLoader<Model, ?>> get(Class<Model> modelClass) {
            Entry<Model> entry = this.cachedModelLoaders.get(modelClass);
            if (entry == null) {
                return null;
            }
            return entry.loaders;
        }

        private static class Entry<Model> {
            final List<ModelLoader<Model, ?>> loaders;

            public Entry(List<ModelLoader<Model, ?>> loaders2) {
                this.loaders = loaders2;
            }
        }
    }
}
