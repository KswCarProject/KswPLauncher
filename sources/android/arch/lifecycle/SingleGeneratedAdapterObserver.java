package android.arch.lifecycle;

import android.arch.lifecycle.Lifecycle;

public class SingleGeneratedAdapterObserver implements GenericLifecycleObserver {
    private final GeneratedAdapter mGeneratedAdapter;

    SingleGeneratedAdapterObserver(GeneratedAdapter generatedAdapter) {
        this.mGeneratedAdapter = generatedAdapter;
    }

    public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
        this.mGeneratedAdapter.callMethods(source, event, false, (MethodCallsLogger) null);
        this.mGeneratedAdapter.callMethods(source, event, true, (MethodCallsLogger) null);
    }
}
