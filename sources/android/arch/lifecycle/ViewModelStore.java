package android.arch.lifecycle;

import java.util.HashMap;

/* loaded from: classes.dex */
public class ViewModelStore {
    private final HashMap<String, ViewModel> mMap = new HashMap<>();

    final void put(String key, ViewModel viewModel) {
        ViewModel oldViewModel = this.mMap.put(key, viewModel);
        if (oldViewModel != null) {
            oldViewModel.onCleared();
        }
    }

    final ViewModel get(String key) {
        return this.mMap.get(key);
    }

    public final void clear() {
        for (ViewModel vm : this.mMap.values()) {
            vm.onCleared();
        }
        this.mMap.clear();
    }
}
