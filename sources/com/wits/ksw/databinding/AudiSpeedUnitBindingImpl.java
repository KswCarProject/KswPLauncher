package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.CompoundButtonBindingAdapter;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.wits.ksw.settings.audi.vm.AudiSystemViewModel;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;

public class AudiSpeedUnitBindingImpl extends AudiSpeedUnitBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final AudiConstraintLayout mboundView0;
    private final RadioButton mboundView2;
    private final RadioButton mboundView3;

    public AudiSpeedUnitBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private AudiSpeedUnitBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, bindings[1]);
        this.mDirtyFlags = -1;
        AudiConstraintLayout audiConstraintLayout = bindings[0];
        this.mboundView0 = audiConstraintLayout;
        audiConstraintLayout.setTag((Object) null);
        RadioButton radioButton = bindings[2];
        this.mboundView2 = radioButton;
        radioButton.setTag((Object) null);
        RadioButton radioButton2 = bindings[3];
        this.mboundView3 = radioButton2;
        radioButton2.setTag((Object) null);
        this.timeRadioGroup.setTag((Object) null);
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
        if (26 != variableId) {
            return false;
        }
        setVm((AudiSystemViewModel) variable);
        return true;
    }

    public void setVm(AudiSystemViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(26);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVmSpeedUnit((ObservableInt) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmSpeedUnit(ObservableInt VmSpeedUnit, int fieldId) {
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
        AudiSystemViewModel vm = this.mVm;
        int vmSpeedUnitGet = 0;
        RadioGroup.OnCheckedChangeListener vmOnSpedUnitChangeListener = null;
        boolean vmSpeedUnitInt0 = false;
        boolean vmSpeedUnitInt1 = false;
        ObservableInt vmSpeedUnit = null;
        if ((dirtyFlags & 7) != 0) {
            if (!((dirtyFlags & 6) == 0 || vm == null)) {
                vmOnSpedUnitChangeListener = vm.onSpedUnitChangeListener;
            }
            if (vm != null) {
                vmSpeedUnit = vm.speedUnit;
            }
            updateRegistration(0, (Observable) vmSpeedUnit);
            if (vmSpeedUnit != null) {
                vmSpeedUnitGet = vmSpeedUnit.get();
            }
            boolean z = true;
            vmSpeedUnitInt0 = vmSpeedUnitGet == 0;
            if (vmSpeedUnitGet != 1) {
                z = false;
            }
            vmSpeedUnitInt1 = z;
        }
        if ((7 & dirtyFlags) != 0) {
            CompoundButtonBindingAdapter.setChecked(this.mboundView2, vmSpeedUnitInt0);
            CompoundButtonBindingAdapter.setChecked(this.mboundView3, vmSpeedUnitInt1);
        }
        if ((dirtyFlags & 6) != 0) {
            this.timeRadioGroup.setOnCheckedChangeListener(vmOnSpedUnitChangeListener);
        }
    }
}
