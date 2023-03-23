package com.wits.ksw.databinding;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class AlsId7UiCarInfoBindingImpl extends AlsId7UiCarInfoBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final AlsId7UiCarInfoSubViewBinding mboundView0;
    private final ConstraintLayout mboundView01;
    private final AlsId7UiDashBoardSubViewBinding mboundView02;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(3);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"als_id7_ui_sub_car_view", "als_id7_ui_sub_dashboard_view"}, new int[]{1, 2}, new int[]{R.layout.als_id7_ui_sub_car_view, R.layout.als_id7_ui_sub_dashboard_view});
    }

    public AlsId7UiCarInfoBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }

    private AlsId7UiCarInfoBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0);
        this.mDirtyFlags = -1;
        AlsId7UiCarInfoSubViewBinding alsId7UiCarInfoSubViewBinding = bindings[1];
        this.mboundView0 = alsId7UiCarInfoSubViewBinding;
        setContainedBinding(alsId7UiCarInfoSubViewBinding);
        ConstraintLayout constraintLayout = bindings[0];
        this.mboundView01 = constraintLayout;
        constraintLayout.setTag((Object) null);
        AlsId7UiDashBoardSubViewBinding alsId7UiDashBoardSubViewBinding = bindings[2];
        this.mboundView02 = alsId7UiDashBoardSubViewBinding;
        setContainedBinding(alsId7UiDashBoardSubViewBinding);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
        }
        this.mboundView0.invalidateAll();
        this.mboundView02.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r4.mboundView02.hasPendingBindings() == false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r4.mboundView0.hasPendingBindings() == false) goto L_0x0016;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        /*
            r4 = this;
            monitor-enter(r4)
            long r0 = r4.mDirtyFlags     // Catch:{ all -> 0x0021 }
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 1
            if (r0 == 0) goto L_0x000c
            monitor-exit(r4)     // Catch:{ all -> 0x0021 }
            return r1
        L_0x000c:
            monitor-exit(r4)     // Catch:{ all -> 0x0021 }
            com.wits.ksw.databinding.AlsId7UiCarInfoSubViewBinding r0 = r4.mboundView0
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r1
        L_0x0016:
            com.wits.ksw.databinding.AlsId7UiDashBoardSubViewBinding r0 = r4.mboundView02
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x001f
            return r1
        L_0x001f:
            r0 = 0
            return r0
        L_0x0021:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0021 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.databinding.AlsId7UiCarInfoBindingImpl.hasPendingBindings():boolean");
    }

    public boolean setVariable(int variableId, Object variable) {
        if (3 != variableId) {
            return false;
        }
        setCarViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setCarViewModel(LauncherViewModel CarViewModel) {
        this.mCarViewModel = CarViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(3);
        super.requestRebind();
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.mboundView0.setLifecycleOwner(lifecycleOwner);
        this.mboundView02.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        LauncherViewModel carViewModel = this.mCarViewModel;
        if ((3 & dirtyFlags) != 0) {
            this.mboundView0.setCarViewModel(carViewModel);
            this.mboundView02.setCarViewModel(carViewModel);
        }
        executeBindingsOn(this.mboundView0);
        executeBindingsOn(this.mboundView02);
    }
}
