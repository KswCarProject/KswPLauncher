package android.arch.lifecycle;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class LifecycleService extends Service implements LifecycleOwner {
    private final ServiceLifecycleDispatcher mDispatcher = new ServiceLifecycleDispatcher(this);

    public void onCreate() {
        this.mDispatcher.onServicePreSuperOnCreate();
        super.onCreate();
    }

    public IBinder onBind(Intent intent) {
        this.mDispatcher.onServicePreSuperOnBind();
        return null;
    }

    public void onStart(Intent intent, int startId) {
        this.mDispatcher.onServicePreSuperOnStart();
        super.onStart(intent, startId);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {
        this.mDispatcher.onServicePreSuperOnDestroy();
        super.onDestroy();
    }

    public Lifecycle getLifecycle() {
        return this.mDispatcher.getLifecycle();
    }
}
