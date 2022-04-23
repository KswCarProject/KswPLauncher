package android.arch.lifecycle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class ViewModelStores {
    private ViewModelStores() {
    }

    public static ViewModelStore of(FragmentActivity activity) {
        if (activity instanceof ViewModelStoreOwner) {
            return activity.getViewModelStore();
        }
        return HolderFragment.holderFragmentFor(activity).getViewModelStore();
    }

    public static ViewModelStore of(Fragment fragment) {
        if (fragment instanceof ViewModelStoreOwner) {
            return fragment.getViewModelStore();
        }
        return HolderFragment.holderFragmentFor(fragment).getViewModelStore();
    }
}
