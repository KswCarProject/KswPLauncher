package android.arch.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class HolderFragment extends Fragment implements ViewModelStoreOwner {
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String HOLDER_TAG = "android.arch.lifecycle.state.StateProviderHolderFragment";
    private static final String LOG_TAG = "ViewModelStores";
    private static final HolderFragmentManager sHolderFragmentManager = new HolderFragmentManager();
    private ViewModelStore mViewModelStore = new ViewModelStore();

    public HolderFragment() {
        setRetainInstance(true);
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sHolderFragmentManager.holderFragmentCreated(this);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void onDestroy() {
        super.onDestroy();
        this.mViewModelStore.clear();
    }

    @NonNull
    public ViewModelStore getViewModelStore() {
        return this.mViewModelStore;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static HolderFragment holderFragmentFor(FragmentActivity activity) {
        return sHolderFragmentManager.holderFragmentFor(activity);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static HolderFragment holderFragmentFor(Fragment fragment) {
        return sHolderFragmentManager.holderFragmentFor(fragment);
    }

    static class HolderFragmentManager {
        private Application.ActivityLifecycleCallbacks mActivityCallbacks = new EmptyActivityLifecycleCallbacks() {
            public void onActivityDestroyed(Activity activity) {
                if (((HolderFragment) HolderFragmentManager.this.mNotCommittedActivityHolders.remove(activity)) != null) {
                    Log.e(HolderFragment.LOG_TAG, "Failed to save a ViewModel for " + activity);
                }
            }
        };
        private boolean mActivityCallbacksIsAdded = false;
        /* access modifiers changed from: private */
        public Map<Activity, HolderFragment> mNotCommittedActivityHolders = new HashMap();
        /* access modifiers changed from: private */
        public Map<Fragment, HolderFragment> mNotCommittedFragmentHolders = new HashMap();
        private FragmentManager.FragmentLifecycleCallbacks mParentDestroyedCallback = new FragmentManager.FragmentLifecycleCallbacks() {
            public void onFragmentDestroyed(FragmentManager fm, Fragment parentFragment) {
                super.onFragmentDestroyed(fm, parentFragment);
                if (((HolderFragment) HolderFragmentManager.this.mNotCommittedFragmentHolders.remove(parentFragment)) != null) {
                    Log.e(HolderFragment.LOG_TAG, "Failed to save a ViewModel for " + parentFragment);
                }
            }
        };

        HolderFragmentManager() {
        }

        /* access modifiers changed from: package-private */
        public void holderFragmentCreated(Fragment holderFragment) {
            Fragment parentFragment = holderFragment.getParentFragment();
            if (parentFragment != null) {
                this.mNotCommittedFragmentHolders.remove(parentFragment);
                parentFragment.getFragmentManager().unregisterFragmentLifecycleCallbacks(this.mParentDestroyedCallback);
                return;
            }
            this.mNotCommittedActivityHolders.remove(holderFragment.getActivity());
        }

        private static HolderFragment findHolderFragment(FragmentManager manager) {
            if (!manager.isDestroyed()) {
                Fragment fragmentByTag = manager.findFragmentByTag(HolderFragment.HOLDER_TAG);
                if (fragmentByTag == null || (fragmentByTag instanceof HolderFragment)) {
                    return (HolderFragment) fragmentByTag;
                }
                throw new IllegalStateException("Unexpected fragment instance was returned by HOLDER_TAG");
            }
            throw new IllegalStateException("Can't access ViewModels from onDestroy");
        }

        private static HolderFragment createHolderFragment(FragmentManager fragmentManager) {
            HolderFragment holder = new HolderFragment();
            fragmentManager.beginTransaction().add((Fragment) holder, HolderFragment.HOLDER_TAG).commitAllowingStateLoss();
            return holder;
        }

        /* access modifiers changed from: package-private */
        public HolderFragment holderFragmentFor(FragmentActivity activity) {
            FragmentManager fm = activity.getSupportFragmentManager();
            HolderFragment holder = findHolderFragment(fm);
            if (holder != null) {
                return holder;
            }
            HolderFragment holder2 = this.mNotCommittedActivityHolders.get(activity);
            if (holder2 != null) {
                return holder2;
            }
            if (!this.mActivityCallbacksIsAdded) {
                this.mActivityCallbacksIsAdded = true;
                activity.getApplication().registerActivityLifecycleCallbacks(this.mActivityCallbacks);
            }
            HolderFragment holder3 = createHolderFragment(fm);
            this.mNotCommittedActivityHolders.put(activity, holder3);
            return holder3;
        }

        /* access modifiers changed from: package-private */
        public HolderFragment holderFragmentFor(Fragment parentFragment) {
            FragmentManager fm = parentFragment.getChildFragmentManager();
            HolderFragment holder = findHolderFragment(fm);
            if (holder != null) {
                return holder;
            }
            HolderFragment holder2 = this.mNotCommittedFragmentHolders.get(parentFragment);
            if (holder2 != null) {
                return holder2;
            }
            parentFragment.getFragmentManager().registerFragmentLifecycleCallbacks(this.mParentDestroyedCallback, false);
            HolderFragment holder3 = createHolderFragment(fm);
            this.mNotCommittedFragmentHolders.put(parentFragment, holder3);
            return holder3;
        }
    }
}
