package android.arch.lifecycle;

import android.support.p001v4.app.Fragment;
import android.support.p001v4.app.FragmentActivity;

/* loaded from: classes.dex */
public class ViewModelStores {
    private ViewModelStores() {
    }

    /* renamed from: of */
    public static ViewModelStore m56of(FragmentActivity activity) {
        if (activity instanceof ViewModelStoreOwner) {
            return activity.getViewModelStore();
        }
        return HolderFragment.holderFragmentFor(activity).getViewModelStore();
    }

    /* renamed from: of */
    public static ViewModelStore m57of(Fragment fragment) {
        if (fragment instanceof ViewModelStoreOwner) {
            return fragment.getViewModelStore();
        }
        return HolderFragment.holderFragmentFor(fragment).getViewModelStore();
    }
}
