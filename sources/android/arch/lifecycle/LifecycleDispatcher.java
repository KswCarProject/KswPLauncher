package android.arch.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.os.Bundle;
import android.support.p001v4.app.Fragment;
import android.support.p001v4.app.FragmentActivity;
import android.support.p001v4.app.FragmentManager;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
class LifecycleDispatcher {
    private static final String REPORT_FRAGMENT_TAG = "android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag";
    private static AtomicBoolean sInitialized = new AtomicBoolean(false);

    LifecycleDispatcher() {
    }

    static void init(Context context) {
        if (sInitialized.getAndSet(true)) {
            return;
        }
        ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(new DispatcherActivityCallback());
    }

    /* loaded from: classes.dex */
    static class DispatcherActivityCallback extends EmptyActivityLifecycleCallbacks {
        private final FragmentCallback mFragmentCallback = new FragmentCallback();

        DispatcherActivityCallback() {
        }

        @Override // android.arch.lifecycle.EmptyActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            if (activity instanceof FragmentActivity) {
                ((FragmentActivity) activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(this.mFragmentCallback, true);
            }
            ReportFragment.injectIfNeededIn(activity);
        }

        @Override // android.arch.lifecycle.EmptyActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            if (activity instanceof FragmentActivity) {
                LifecycleDispatcher.markState((FragmentActivity) activity, Lifecycle.State.CREATED);
            }
        }

        @Override // android.arch.lifecycle.EmptyActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            if (activity instanceof FragmentActivity) {
                LifecycleDispatcher.markState((FragmentActivity) activity, Lifecycle.State.CREATED);
            }
        }
    }

    /* loaded from: classes.dex */
    public static class DestructionReportFragment extends Fragment {
        @Override // android.support.p001v4.app.Fragment
        public void onPause() {
            super.onPause();
            dispatch(Lifecycle.Event.ON_PAUSE);
        }

        @Override // android.support.p001v4.app.Fragment
        public void onStop() {
            super.onStop();
            dispatch(Lifecycle.Event.ON_STOP);
        }

        @Override // android.support.p001v4.app.Fragment
        public void onDestroy() {
            super.onDestroy();
            dispatch(Lifecycle.Event.ON_DESTROY);
        }

        protected void dispatch(Lifecycle.Event event) {
            LifecycleDispatcher.dispatchIfLifecycleOwner(getParentFragment(), event);
        }
    }

    private static void markState(FragmentManager manager, Lifecycle.State state) {
        Collection<Fragment> fragments = manager.getFragments();
        if (fragments == null) {
            return;
        }
        for (Fragment fragment : fragments) {
            if (fragment != null) {
                markStateIn(fragment, state);
                if (fragment.isAdded()) {
                    markState(fragment.getChildFragmentManager(), state);
                }
            }
        }
    }

    private static void markStateIn(Object object, Lifecycle.State state) {
        if (object instanceof LifecycleRegistryOwner) {
            LifecycleRegistry registry = ((LifecycleRegistryOwner) object).getLifecycle();
            registry.markState(state);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void markState(FragmentActivity activity, Lifecycle.State state) {
        markStateIn(activity, state);
        markState(activity.getSupportFragmentManager(), state);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dispatchIfLifecycleOwner(Fragment fragment, Lifecycle.Event event) {
        if (fragment instanceof LifecycleRegistryOwner) {
            ((LifecycleRegistryOwner) fragment).getLifecycle().handleLifecycleEvent(event);
        }
    }

    /* loaded from: classes.dex */
    static class FragmentCallback extends FragmentManager.FragmentLifecycleCallbacks {
        FragmentCallback() {
        }

        @Override // android.support.p001v4.app.FragmentManager.FragmentLifecycleCallbacks
        public void onFragmentCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
            LifecycleDispatcher.dispatchIfLifecycleOwner(f, Lifecycle.Event.ON_CREATE);
            if ((f instanceof LifecycleRegistryOwner) && f.getChildFragmentManager().findFragmentByTag(LifecycleDispatcher.REPORT_FRAGMENT_TAG) == null) {
                f.getChildFragmentManager().beginTransaction().add(new DestructionReportFragment(), LifecycleDispatcher.REPORT_FRAGMENT_TAG).commit();
            }
        }

        @Override // android.support.p001v4.app.FragmentManager.FragmentLifecycleCallbacks
        public void onFragmentStarted(FragmentManager fm, Fragment f) {
            LifecycleDispatcher.dispatchIfLifecycleOwner(f, Lifecycle.Event.ON_START);
        }

        @Override // android.support.p001v4.app.FragmentManager.FragmentLifecycleCallbacks
        public void onFragmentResumed(FragmentManager fm, Fragment f) {
            LifecycleDispatcher.dispatchIfLifecycleOwner(f, Lifecycle.Event.ON_RESUME);
        }
    }
}
