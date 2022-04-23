package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.CompoundButtonBindingAdapter;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi_mib3.vm.AudiMib3EQViewModel;

public class AudiMib3EqViewBindingHdpi1920x720Impl extends AudiMib3EqViewBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final RadioButton mboundView2;
    private final RadioButton mboundView3;
    private final RadioButton mboundView4;
    private final RadioButton mboundView5;
    private final RadioButton mboundView6;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.title, 7);
        sparseIntArray.put(R.id.title_divider, 8);
        sparseIntArray.put(R.id.linearLayout4, 9);
        sparseIntArray.put(R.id.rg_list, 10);
        sparseIntArray.put(R.id.v_divider, 11);
    }

    public AudiMib3EqViewBindingHdpi1920x720Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }

    private AudiMib3EqViewBindingHdpi1920x720Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, bindings[1], bindings[9], bindings[10], bindings[7], bindings[8], bindings[11]);
        this.mDirtyFlags = -1;
        this.aaa.setTag((Object) null);
        RelativeLayout relativeLayout = bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
        RadioButton radioButton = bindings[2];
        this.mboundView2 = radioButton;
        radioButton.setTag((Object) null);
        RadioButton radioButton2 = bindings[3];
        this.mboundView3 = radioButton2;
        radioButton2.setTag((Object) null);
        RadioButton radioButton3 = bindings[4];
        this.mboundView4 = radioButton3;
        radioButton3.setTag((Object) null);
        RadioButton radioButton4 = bindings[5];
        this.mboundView5 = radioButton4;
        radioButton4.setTag((Object) null);
        RadioButton radioButton5 = bindings[6];
        this.mboundView6 = radioButton5;
        radioButton5.setTag((Object) null);
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
        setVm((AudiMib3EQViewModel) variable);
        return true;
    }

    public void setVm(AudiMib3EQViewModel Vm) {
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
                return onChangeVmEqModel((ObservableInt) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmEqModel(ObservableInt VmEqModel, int fieldId) {
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
        int vmEqModelGet = 0;
        AudiMib3EQViewModel vm = this.mVm;
        boolean vmEqModelInt1 = false;
        boolean vmEqModelInt2 = false;
        boolean vmEqModelInt0 = false;
        boolean vmEqModelInt4 = false;
        boolean vmEqModelInt3 = false;
        boolean vmEqModelInt5 = false;
        ObservableInt vmEqModel = null;
        if ((dirtyFlags & 7) != 0) {
            if (vm != null) {
                vmEqModel = vm.eqModel;
            }
            updateRegistration(0, (Observable) vmEqModel);
            if (vmEqModel != null) {
                vmEqModelGet = vmEqModel.get();
            }
            vmEqModelInt1 = vmEqModelGet == 1;
            vmEqModelInt2 = vmEqModelGet == 2;
            vmEqModelInt0 = vmEqModelGet == 0;
            vmEqModelInt4 = vmEqModelGet == 4;
            vmEqModelInt3 = vmEqModelGet == 3;
            vmEqModelInt5 = vmEqModelGet == 5;
        }
        if ((dirtyFlags & 7) != 0) {
            CompoundButtonBindingAdapter.setChecked(this.aaa, vmEqModelInt0);
            CompoundButtonBindingAdapter.setChecked(this.mboundView2, vmEqModelInt1);
            CompoundButtonBindingAdapter.setChecked(this.mboundView3, vmEqModelInt2);
            CompoundButtonBindingAdapter.setChecked(this.mboundView4, vmEqModelInt3);
            CompoundButtonBindingAdapter.setChecked(this.mboundView5, vmEqModelInt4);
            CompoundButtonBindingAdapter.setChecked(this.mboundView6, vmEqModelInt5);
        }
        if ((dirtyFlags & 4) != 0) {
            AudiMib3EQViewModel.setEQModelChangeListener(this.aaa, 0);
            AudiMib3EQViewModel.setEQModelChangeListener(this.mboundView2, 1);
            AudiMib3EQViewModel.setEQModelChangeListener(this.mboundView3, 2);
            AudiMib3EQViewModel.setEQModelChangeListener(this.mboundView4, 3);
            AudiMib3EQViewModel.setEQModelChangeListener(this.mboundView5, 4);
            AudiMib3EQViewModel.setEQModelChangeListener(this.mboundView6, 5);
        }
    }
}
