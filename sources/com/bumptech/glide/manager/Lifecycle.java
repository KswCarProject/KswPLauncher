package com.bumptech.glide.manager;

import android.support.annotation.NonNull;

public interface Lifecycle {
    void addListener(@NonNull LifecycleListener lifecycleListener);

    void removeListener(@NonNull LifecycleListener lifecycleListener);
}
