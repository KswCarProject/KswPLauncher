package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.AudiViewModel;

public class AudiRightLogoBindingImpl extends AudiRightLogoBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.KSW_you_audi, 1);
    }

    public AudiRightLogoBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 2, sIncludes, sViewsWithIds));
    }

    private AudiRightLogoBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, bindings[0], bindings[1]);
        this.mDirtyFlags = -1;
        this.KSWA4LRightShowLogo.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    public boolean setVariable(int variableId, Object variable) {
        if (17 != variableId) {
            return false;
        }
        setVm((AudiViewModel) variable);
        return true;
    }

    public void setVm(AudiViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(17);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVmLogoView((ObservableInt) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmLogoView(ObservableInt VmLogoView, int fieldId) {
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
        AudiViewModel vm = this.mVm;
        int vmLogoViewGet = 0;
        ObservableInt vmLogoView = null;
        if ((dirtyFlags & 7) != 0) {
            if (vm != null) {
                vmLogoView = vm.logoView;
            }
            updateRegistration(0, (Observable) vmLogoView);
            if (vmLogoView != null) {
                vmLogoViewGet = vmLogoView.get();
            }
        }
        if ((7 & dirtyFlags) != 0) {
            ((RelativeLayout) this.KSWA4LRightShowLogo).setVisibility(vmLogoViewGet);
        }
    }
}
