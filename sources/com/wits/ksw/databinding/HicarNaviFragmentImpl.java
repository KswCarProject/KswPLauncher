package com.wits.ksw.databinding;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public class HicarNaviFragmentImpl extends HicarNaviFragment {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(3);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"id7_sub_navi_view", "id7_sub_hicar_view"}, new int[]{1, 2}, new int[]{C0899R.C0902layout.id7_sub_navi_view, C0899R.C0902layout.id7_sub_hicar_view});
        sViewsWithIds = null;
    }

    public HicarNaviFragmentImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }

    private HicarNaviFragmentImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (Id7SubHicarViewBinding) bindings[2], (NaviSubView) bindings[1]);
        this.mDirtyFlags = -1L;
        setContainedBinding(this.hicarLayout);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        setContainedBinding(this.naviLayout);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8L;
        }
        this.naviLayout.invalidateAll();
        this.hicarLayout.invalidateAll();
        requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.naviLayout.hasPendingBindings() || this.hicarLayout.hasPendingBindings();
        }
    }

    @Override // android.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (14 == variableId) {
            setNaviViewModel((LauncherViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.HicarNaviFragment
    public void setNaviViewModel(LauncherViewModel NaviViewModel) {
        this.mNaviViewModel = NaviViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(14);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.naviLayout.setLifecycleOwner(lifecycleOwner);
        this.hicarLayout.setLifecycleOwner(lifecycleOwner);
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeNaviLayout((NaviSubView) object, fieldId);
            case 1:
                return onChangeHicarLayout((Id7SubHicarViewBinding) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeNaviLayout(NaviSubView NaviLayout, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeHicarLayout(Id7SubHicarViewBinding HicarLayout, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        LauncherViewModel naviViewModel = this.mNaviViewModel;
        if ((12 & dirtyFlags) != 0) {
            this.hicarLayout.setNaviViewModel(naviViewModel);
            this.naviLayout.setNaviViewModel(naviViewModel);
        }
        executeBindingsOn(this.naviLayout);
        executeBindingsOn(this.hicarLayout);
    }
}
