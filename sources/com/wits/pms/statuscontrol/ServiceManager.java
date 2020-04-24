package com.wits.pms.statuscontrol;

import android.os.IBinder;
import android.util.Log;

public class ServiceManager {
    public static final String TAG = ServiceManager.class.getSimpleName();

    public static IBinder getService(String serviceName) {
        try {
            Class<?> serviceManager = Class.forName("android.os.ServiceManager");
            return (IBinder) serviceManager.getMethod("getService", new Class[]{String.class}).invoke(serviceManager, new Object[]{serviceName});
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "error service init - " + serviceName, e);
            return null;
        }
    }

    public static void addService(String serviceName, IBinder binder) {
        try {
            Class<?> serviceManager = Class.forName("android.os.ServiceManager");
            serviceManager.getMethod("addService", new Class[]{String.class, IBinder.class}).invoke(serviceManager, new Object[]{serviceName, binder});
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "error service - " + serviceName, e);
        }
    }
}
