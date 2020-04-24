package com.bumptech.glide.manager;

import android.support.annotation.NonNull;

class ApplicationLifecycle implements Lifecycle {
    ApplicationLifecycle() {
    }

    public void addListener(@NonNull LifecycleListener listener) {
        listener.onStart();
    }

    public void removeListener(@NonNull LifecycleListener listener) {
    }
}
