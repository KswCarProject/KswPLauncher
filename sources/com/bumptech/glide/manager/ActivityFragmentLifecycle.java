package com.bumptech.glide.manager;

import com.bumptech.glide.util.Util;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

class ActivityFragmentLifecycle implements Lifecycle {
    private boolean isDestroyed;
    private boolean isStarted;
    private final Set<LifecycleListener> lifecycleListeners = Collections.newSetFromMap(new WeakHashMap());

    ActivityFragmentLifecycle() {
    }

    public void addListener(LifecycleListener listener) {
        this.lifecycleListeners.add(listener);
        if (this.isDestroyed) {
            listener.onDestroy();
        } else if (this.isStarted) {
            listener.onStart();
        } else {
            listener.onStop();
        }
    }

    public void removeListener(LifecycleListener listener) {
        this.lifecycleListeners.remove(listener);
    }

    /* access modifiers changed from: package-private */
    public void onStart() {
        this.isStarted = true;
        for (T lifecycleListener : Util.getSnapshot(this.lifecycleListeners)) {
            lifecycleListener.onStart();
        }
    }

    /* access modifiers changed from: package-private */
    public void onStop() {
        this.isStarted = false;
        for (T lifecycleListener : Util.getSnapshot(this.lifecycleListeners)) {
            lifecycleListener.onStop();
        }
    }

    /* access modifiers changed from: package-private */
    public void onDestroy() {
        this.isDestroyed = true;
        for (T lifecycleListener : Util.getSnapshot(this.lifecycleListeners)) {
            lifecycleListener.onDestroy();
        }
    }
}
