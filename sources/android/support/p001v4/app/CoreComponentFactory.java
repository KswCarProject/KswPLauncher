package android.support.p001v4.app;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.app.Application;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Intent;

/* renamed from: android.support.v4.app.CoreComponentFactory */
/* loaded from: classes.dex */
public class CoreComponentFactory extends AppComponentFactory {
    private static final String TAG = "CoreComponentFactory";

    /* renamed from: android.support.v4.app.CoreComponentFactory$CompatWrapped */
    /* loaded from: classes.dex */
    public interface CompatWrapped {
        Object getWrapper();
    }

    @Override // android.app.AppComponentFactory
    public Activity instantiateActivity(ClassLoader cl, String className, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (Activity) checkCompatWrapper(super.instantiateActivity(cl, className, intent));
    }

    @Override // android.app.AppComponentFactory
    public Application instantiateApplication(ClassLoader cl, String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (Application) checkCompatWrapper(super.instantiateApplication(cl, className));
    }

    @Override // android.app.AppComponentFactory
    public BroadcastReceiver instantiateReceiver(ClassLoader cl, String className, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (BroadcastReceiver) checkCompatWrapper(super.instantiateReceiver(cl, className, intent));
    }

    @Override // android.app.AppComponentFactory
    public ContentProvider instantiateProvider(ClassLoader cl, String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (ContentProvider) checkCompatWrapper(super.instantiateProvider(cl, className));
    }

    @Override // android.app.AppComponentFactory
    public Service instantiateService(ClassLoader cl, String className, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (Service) checkCompatWrapper(super.instantiateService(cl, className, intent));
    }

    static <T> T checkCompatWrapper(T obj) {
        T wrapper;
        if ((obj instanceof CompatWrapped) && (wrapper = (T) ((CompatWrapped) obj).getWrapper()) != null) {
            return wrapper;
        }
        return obj;
    }
}
