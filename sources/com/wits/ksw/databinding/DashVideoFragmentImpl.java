package com.wits.ksw.databinding;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;

/* loaded from: classes7.dex */
public class DashVideoFragmentImpl extends DashVideoFragment {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final AlsId7SubDashboardViewBinding mboundView0;
    private final ConstraintLayout mboundView01;
    private final AlsId7SubVideoViewBinding mboundView02;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(3);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"als_id7_sub_dashboard_view", "als_id7_sub_video_view"}, new int[]{1, 2}, new int[]{C0899R.C0902layout.als_id7_sub_dashboard_view, C0899R.C0902layout.als_id7_sub_video_view});
        sViewsWithIds = null;
    }

    public DashVideoFragmentImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }

    private DashVideoFragmentImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0);
        this.mDirtyFlags = -1L;
        AlsId7SubDashboardViewBinding alsId7SubDashboardViewBinding = (AlsId7SubDashboardViewBinding) bindings[1];
        this.mboundView0 = alsId7SubDashboardViewBinding;
        setContainedBinding(alsId7SubDashboardViewBinding);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView01 = constraintLayout;
        constraintLayout.setTag(null);
        AlsId7SubVideoViewBinding alsId7SubVideoViewBinding = (AlsId7SubVideoViewBinding) bindings[2];
        this.mboundView02 = alsId7SubVideoViewBinding;
        setContainedBinding(alsId7SubVideoViewBinding);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2L;
        }
        this.mboundView0.invalidateAll();
        this.mboundView02.invalidateAll();
        requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.mboundView0.hasPendingBindings() || this.mboundView02.hasPendingBindings();
        }
    }

    @Override // android.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (4 == variableId) {
            setDashVideoViewModel((AlsID7ViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.DashVideoFragment
    public void setDashVideoViewModel(AlsID7ViewModel DashVideoViewModel) {
        this.mDashVideoViewModel = DashVideoViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(4);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.mboundView0.setLifecycleOwner(lifecycleOwner);
        this.mboundView02.setLifecycleOwner(lifecycleOwner);
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        AlsID7ViewModel dashVideoViewModel = this.mDashVideoViewModel;
        if ((3 & dirtyFlags) != 0) {
            this.mboundView0.setDashVideoViewModel(dashVideoViewModel);
            this.mboundView02.setDashVideoViewModel(dashVideoViewModel);
        }
        executeBindingsOn(this.mboundView0);
        executeBindingsOn(this.mboundView02);
    }
}
