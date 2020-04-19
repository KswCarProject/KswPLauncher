package com.bumptech.glide.load.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.Pools;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.util.Preconditions;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MultiModelLoaderFactory {
    private static final Factory DEFAULT_FACTORY = new Factory();
    private static final ModelLoader<Object, Object> EMPTY_MODEL_LOADER = new EmptyModelLoader();
    private final Set<Entry<?, ?>> alreadyUsedEntries;
    private final List<Entry<?, ?>> entries;
    private final Factory factory;
    private final Pools.Pool<List<Throwable>> throwableListPool;

    public MultiModelLoaderFactory(@NonNull Pools.Pool<List<Throwable>> throwableListPool2) {
        this(throwableListPool2, DEFAULT_FACTORY);
    }

    @VisibleForTesting
    MultiModelLoaderFactory(@NonNull Pools.Pool<List<Throwable>> throwableListPool2, @NonNull Factory factory2) {
        this.entries = new ArrayList();
        this.alreadyUsedEntries = new HashSet();
        this.throwableListPool = throwableListPool2;
        this.factory = factory2;
    }

    /* access modifiers changed from: package-private */
    public synchronized <Model, Data> void append(@NonNull Class<Model> modelClass, @NonNull Class<Data> dataClass, @NonNull ModelLoaderFactory<? extends Model, ? extends Data> factory2) {
        add(modelClass, dataClass, factory2, true);
    }

    /* access modifiers changed from: package-private */
    public synchronized <Model, Data> void prepend(@NonNull Class<Model> modelClass, @NonNull Class<Data> dataClass, @NonNull ModelLoaderFactory<? extends Model, ? extends Data> factory2) {
        add(modelClass, dataClass, factory2, false);
    }

    private <Model, Data> void add(@NonNull Class<Model> modelClass, @NonNull Class<Data> dataClass, @NonNull ModelLoaderFactory<? extends Model, ? extends Data> factory2, boolean append) {
        this.entries.add(append ? this.entries.size() : 0, new Entry<>(modelClass, dataClass, factory2));
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public synchronized <Model, Data> List<ModelLoaderFactory<? extends Model, ? extends Data>> replace(@NonNull Class<Model> modelClass, @NonNull Class<Data> dataClass, @NonNull ModelLoaderFactory<? extends Model, ? extends Data> factory2) {
        List<ModelLoaderFactory<? extends Model, ? extends Data>> removed;
        removed = remove(modelClass, dataClass);
        append(modelClass, dataClass, factory2);
        return removed;
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public synchronized <Model, Data> List<ModelLoaderFactory<? extends Model, ? extends Data>> remove(@NonNull Class<Model> modelClass, @NonNull Class<Data> dataClass) {
        List<ModelLoaderFactory<? extends Model, ? extends Data>> factories;
        factories = new ArrayList<>();
        Iterator<Entry<?, ?>> iterator = this.entries.iterator();
        while (iterator.hasNext()) {
            Entry<?, ?> entry = iterator.next();
            if (entry.handles(modelClass, dataClass)) {
                iterator.remove();
                factories.add(getFactory(entry));
            }
        }
        return factories;
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public synchronized <Model> List<ModelLoader<Model, ?>> build(@NonNull Class<Model> modelClass) {
        List<ModelLoader<Model, ?>> loaders;
        try {
            loaders = new ArrayList<>();
            for (Entry<?, ?> entry : this.entries) {
                if (!this.alreadyUsedEntries.contains(entry)) {
                    if (entry.handles(modelClass)) {
                        this.alreadyUsedEntries.add(entry);
                        loaders.add(build(entry));
                        this.alreadyUsedEntries.remove(entry);
                    }
                }
            }
        } catch (Throwable t) {
            this.alreadyUsedEntries.clear();
            throw t;
        }
        return loaders;
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public synchronized List<Class<?>> getDataClasses(@NonNull Class<?> modelClass) {
        List<Class<?>> result;
        result = new ArrayList<>();
        for (Entry<?, ?> entry : this.entries) {
            if (!result.contains(entry.dataClass) && entry.handles(modelClass)) {
                result.add(entry.dataClass);
            }
        }
        return result;
    }

    @NonNull
    public synchronized <Model, Data> ModelLoader<Model, Data> build(@NonNull Class<Model> modelClass, @NonNull Class<Data> dataClass) {
        try {
            List<ModelLoader<Model, Data>> loaders = new ArrayList<>();
            boolean ignoredAnyEntries = false;
            for (Entry<?, ?> entry : this.entries) {
                if (this.alreadyUsedEntries.contains(entry)) {
                    ignoredAnyEntries = true;
                } else if (entry.handles(modelClass, dataClass)) {
                    this.alreadyUsedEntries.add(entry);
                    loaders.add(build(entry));
                    this.alreadyUsedEntries.remove(entry);
                }
            }
            if (loaders.size() > 1) {
                return this.factory.build(loaders, this.throwableListPool);
            } else if (loaders.size() == 1) {
                return loaders.get(0);
            } else if (ignoredAnyEntries) {
                return emptyModelLoader();
            } else {
                throw new Registry.NoModelLoaderAvailableException(modelClass, dataClass);
            }
        } catch (Throwable t) {
            this.alreadyUsedEntries.clear();
            throw t;
        }
    }

    @NonNull
    private <Model, Data> ModelLoaderFactory<Model, Data> getFactory(@NonNull Entry<?, ?> entry) {
        return entry.factory;
    }

    @NonNull
    private <Model, Data> ModelLoader<Model, Data> build(@NonNull Entry<?, ?> entry) {
        return (ModelLoader) Preconditions.checkNotNull(entry.factory.build(this));
    }

    @NonNull
    private static <Model, Data> ModelLoader<Model, Data> emptyModelLoader() {
        return EMPTY_MODEL_LOADER;
    }

    private static class Entry<Model, Data> {
        final Class<Data> dataClass;
        final ModelLoaderFactory<? extends Model, ? extends Data> factory;
        private final Class<Model> modelClass;

        public Entry(@NonNull Class<Model> modelClass2, @NonNull Class<Data> dataClass2, @NonNull ModelLoaderFactory<? extends Model, ? extends Data> factory2) {
            this.modelClass = modelClass2;
            this.dataClass = dataClass2;
            this.factory = factory2;
        }

        public boolean handles(@NonNull Class<?> modelClass2, @NonNull Class<?> dataClass2) {
            return handles(modelClass2) && this.dataClass.isAssignableFrom(dataClass2);
        }

        public boolean handles(@NonNull Class<?> modelClass2) {
            return this.modelClass.isAssignableFrom(modelClass2);
        }
    }

    static class Factory {
        Factory() {
        }

        @NonNull
        public <Model, Data> MultiModelLoader<Model, Data> build(@NonNull List<ModelLoader<Model, Data>> modelLoaders, @NonNull Pools.Pool<List<Throwable>> throwableListPool) {
            return new MultiModelLoader<>(modelLoaders, throwableListPool);
        }
    }

    private static class EmptyModelLoader implements ModelLoader<Object, Object> {
        EmptyModelLoader() {
        }

        @Nullable
        public ModelLoader.LoadData<Object> buildLoadData(@NonNull Object o, int width, int height, @NonNull Options options) {
            return null;
        }

        public boolean handles(@NonNull Object o) {
            return false;
        }
    }
}
