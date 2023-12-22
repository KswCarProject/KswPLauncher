package android.arch.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.support.p001v4.app.Fragment;
import android.support.p001v4.app.FragmentActivity;

/* loaded from: classes.dex */
public class ViewModelProviders {
    private static Application checkApplication(Activity activity) {
        Application application = activity.getApplication();
        if (application == null) {
            throw new IllegalStateException("Your activity/fragment is not yet attached to Application. You can't request ViewModel before onCreate call.");
        }
        return application;
    }

    private static Activity checkActivity(Fragment fragment) {
        Activity activity = fragment.getActivity();
        if (activity == null) {
            throw new IllegalStateException("Can't create ViewModelProvider for detached fragment");
        }
        return activity;
    }

    /* renamed from: of */
    public static ViewModelProvider m61of(Fragment fragment) {
        return m60of(fragment, (ViewModelProvider.Factory) null);
    }

    /* renamed from: of */
    public static ViewModelProvider m59of(FragmentActivity activity) {
        return m58of(activity, (ViewModelProvider.Factory) null);
    }

    /* renamed from: of */
    public static ViewModelProvider m60of(Fragment fragment, ViewModelProvider.Factory factory) {
        Application application = checkApplication(checkActivity(fragment));
        if (factory == null) {
            factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application);
        }
        return new ViewModelProvider(ViewModelStores.m57of(fragment), factory);
    }

    /* renamed from: of */
    public static ViewModelProvider m58of(FragmentActivity activity, ViewModelProvider.Factory factory) {
        Application application = checkApplication(activity);
        if (factory == null) {
            factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application);
        }
        return new ViewModelProvider(ViewModelStores.m56of(activity), factory);
    }

    @Deprecated
    /* loaded from: classes.dex */
    public static class DefaultFactory extends ViewModelProvider.AndroidViewModelFactory {
        @Deprecated
        public DefaultFactory(Application application) {
            super(application);
        }
    }
}
