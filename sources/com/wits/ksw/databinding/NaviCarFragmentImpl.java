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
public class NaviCarFragmentImpl extends NaviCarFragment {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(3);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"als_id7_sub_navi_view", "als_id7_sub_car_view"}, new int[]{1, 2}, new int[]{C0899R.C0902layout.als_id7_sub_navi_view, C0899R.C0902layout.als_id7_sub_car_view});
        sViewsWithIds = null;
    }

    public NaviCarFragmentImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }

    private NaviCarFragmentImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (AlsId7SubCarViewBinding) bindings[2], (AlsId7SubNaviViewBinding) bindings[1]);
        this.mDirtyFlags = -1L;
        setContainedBinding(this.carinfoLayout);
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
        this.carinfoLayout.invalidateAll();
        requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.naviLayout.hasPendingBindings() || this.carinfoLayout.hasPendingBindings();
        }
    }

    @Override // android.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (13 == variableId) {
            setNaviCarViewModel((AlsID7ViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.NaviCarFragment
    public void setNaviCarViewModel(AlsID7ViewModel NaviCarViewModel) {
        this.mNaviCarViewModel = NaviCarViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(13);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.naviLayout.setLifecycleOwner(lifecycleOwner);
        this.carinfoLayout.setLifecycleOwner(lifecycleOwner);
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeNaviLayout((AlsId7SubNaviViewBinding) object, fieldId);
            case 1:
                return onChangeCarinfoLayout((AlsId7SubCarViewBinding) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeNaviLayout(AlsId7SubNaviViewBinding NaviLayout, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeCarinfoLayout(AlsId7SubCarViewBinding CarinfoLayout, int fieldId) {
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
        AlsID7ViewModel naviCarViewModel = this.mNaviCarViewModel;
        if ((12 & dirtyFlags) != 0) {
            this.carinfoLayout.setNaviCarViewModel(naviCarViewModel);
            this.naviLayout.setNaviCarViewModel(naviCarViewModel);
        }
        executeBindingsOn(this.naviLayout);
        executeBindingsOn(this.carinfoLayout);
    }
}
