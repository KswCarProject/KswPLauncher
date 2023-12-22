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
public class AlsId7UiNaviBindingImpl extends AlsId7UiNaviBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(3);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"als_id7_ui_sub_navi_view", "als_id7_ui_sub_phone_view"}, new int[]{1, 2}, new int[]{C0899R.C0902layout.als_id7_ui_sub_navi_view, C0899R.C0902layout.als_id7_ui_sub_phone_view});
        sViewsWithIds = null;
    }

    public AlsId7UiNaviBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }

    private AlsId7UiNaviBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (AlsId7UiSubNaviViewBinding) bindings[1], (AlsId7UiSubPhoneViewBinding) bindings[2]);
        this.mDirtyFlags = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        setContainedBinding(this.naviLayout);
        setContainedBinding(this.phoneLayout);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8L;
        }
        this.naviLayout.invalidateAll();
        this.phoneLayout.invalidateAll();
        requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.naviLayout.hasPendingBindings() || this.phoneLayout.hasPendingBindings();
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

    @Override // com.wits.ksw.databinding.AlsId7UiNaviBinding
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
        this.phoneLayout.setLifecycleOwner(lifecycleOwner);
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeNaviLayout((AlsId7UiSubNaviViewBinding) object, fieldId);
            case 1:
                return onChangePhoneLayout((AlsId7UiSubPhoneViewBinding) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeNaviLayout(AlsId7UiSubNaviViewBinding NaviLayout, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangePhoneLayout(AlsId7UiSubPhoneViewBinding PhoneLayout, int fieldId) {
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
            this.naviLayout.setNaviViewModel(naviViewModel);
            this.phoneLayout.setNaviViewModel(naviViewModel);
        }
        executeBindingsOn(this.naviLayout);
        executeBindingsOn(this.phoneLayout);
    }
}
