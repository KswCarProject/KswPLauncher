package android.arch.lifecycle;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

public class LifecycleService extends Service implements LifecycleOwner {
    private final ServiceLifecycleDispatcher mDispatcher = new ServiceLifecycleDispatcher(this);

    @CallSuper
    public void onCreate() {
        this.mDispatcher.onServicePreSuperOnCreate();
        super.onCreate();
    }

    @Nullable
    @CallSuper
    public IBinder onBind(Intent intent) {
        this.mDispatcher.onServicePreSuperOnBind();
        return null;
    }

    @CallSuper
    public void onStart(Intent intent, int startId) {
        this.mDispatcher.onServicePreSuperOnStart();
        super.onStart(intent, startId);
    }

    @CallSuper
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @CallSuper
    public void onDestroy() {
        this.mDispatcher.onServicePreSuperOnDestroy();
        super.onDestroy();
    }

    public Lifecycle getLifecycle() {
        return this.mDispatcher.getLifecycle();
    }
}
