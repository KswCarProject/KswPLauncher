package com.wits.pms.statuscontrol;

import android.os.IBinder;
import android.util.Log;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class ServiceManager {
    public static final String TAG = ServiceManager.class.getSimpleName();

    public static IBinder getService(String serviceName) {
        try {
            Class<?> serviceManager = Class.forName("android.os.ServiceManager");
            Method getService = serviceManager.getMethod("getService", String.class);
            IBinder binder = (IBinder) getService.invoke(serviceManager, serviceName);
            return binder;
        } catch (Exception var4) {
            Log.e(TAG, "error service init - " + serviceName, var4);
            return null;
        }
    }

    public static void addService(String serviceName, IBinder binder) {
        try {
            Class<?> serviceManager = Class.forName("android.os.ServiceManager");
            Method addService = serviceManager.getMethod("addService", String.class, IBinder.class);
            addService.invoke(serviceManager, serviceName, binder);
        } catch (Exception var4) {
            Log.e(TAG, "error service - " + serviceName, var4);
        }
    }
}
