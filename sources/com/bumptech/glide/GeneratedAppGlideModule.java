package com.bumptech.glide;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.manager.RequestManagerRetriever;
import com.bumptech.glide.module.AppGlideModule;
import java.util.Set;

abstract class GeneratedAppGlideModule extends AppGlideModule {
    /* access modifiers changed from: package-private */
    @NonNull
    public abstract Set<Class<?>> getExcludedModuleClasses();

    GeneratedAppGlideModule() {
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public RequestManagerRetriever.RequestManagerFactory getRequestManagerFactory() {
        return null;
    }
}
