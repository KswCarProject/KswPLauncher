package com.wits.ksw.databinding;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.settings.bmw_id8.vm.BmwId8SettingsViewModel;

public class BmwId8gsSettingsMainLayoutBindingImpl extends BmwId8gsSettingsMainLayoutBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(3);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"id8_gs_launcher_left_bar"}, new int[]{1}, new int[]{R.layout.id8_gs_launcher_left_bar});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.bmw_id8_settings_main_recycle, 2);
    }

    public BmwId8gsSettingsMainLayoutBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }

    private BmwId8gsSettingsMainLayoutBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, bindings[1], bindings[2]);
        this.mDirtyFlags = -1;
        setContainedBinding(this.bmwId8SettingsMainLeftBar);
        ConstraintLayout constraintLayout = bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8;
        }
        this.bmwId8SettingsMainLeftBar.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r4.bmwId8SettingsMainLeftBar.hasPendingBindings() == false) goto L_0x0016;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        /*
            r4 = this;
            monitor-enter(r4)
            long r0 = r4.mDirtyFlags     // Catch:{ all -> 0x0018 }
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 1
            if (r0 == 0) goto L_0x000c
            monitor-exit(r4)     // Catch:{ all -> 0x0018 }
            return r1
        L_0x000c:
            monitor-exit(r4)     // Catch:{ all -> 0x0018 }
            com.wits.ksw.databinding.Id8GsLauncherLeftBarBinding r0 = r4.bmwId8SettingsMainLeftBar
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r1
        L_0x0016:
            r0 = 0
            return r0
        L_0x0018:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0018 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.databinding.BmwId8gsSettingsMainLayoutBindingImpl.hasPendingBindings():boolean");
    }

    public boolean setVariable(int variableId, Object variable) {
        if (7 == variableId) {
            setLauncherViewModel((LauncherViewModel) variable);
            return true;
        } else if (16 != variableId) {
            return false;
        } else {
            setViewModel((BmwId8SettingsViewModel) variable);
            return true;
        }
    }

    public void setLauncherViewModel(LauncherViewModel LauncherViewModel) {
        this.mLauncherViewModel = LauncherViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(7);
        super.requestRebind();
    }

    public void setViewModel(BmwId8SettingsViewModel ViewModel) {
        this.mViewModel = ViewModel;
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.bmwId8SettingsMainLeftBar.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeBmwId8SettingsMainLeftBar((Id8GsLauncherLeftBarBinding) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeBmwId8SettingsMainLeftBar(Id8GsLauncherLeftBarBinding BmwId8SettingsMainLeftBar, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        LauncherViewModel launcherViewModel = this.mLauncherViewModel;
        if ((10 & dirtyFlags) != 0) {
            this.bmwId8SettingsMainLeftBar.setLeftViewModel(launcherViewModel);
        }
        executeBindingsOn(this.bmwId8SettingsMainLeftBar);
    }
}
