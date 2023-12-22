package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.CompoundButtonBindingAdapter;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.wits.ksw.settings.audi.p007vm.AudiSystemViewModel;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;

/* loaded from: classes7.dex */
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
        super(bindingComponent, root, 1, (RadioGroup) bindings[1]);
        this.mDirtyFlags = -1L;
        AudiConstraintLayout audiConstraintLayout = (AudiConstraintLayout) bindings[0];
        this.mboundView0 = audiConstraintLayout;
        audiConstraintLayout.setTag(null);
        RadioButton radioButton = (RadioButton) bindings[2];
        this.mboundView2 = radioButton;
        radioButton.setTag(null);
        RadioButton radioButton2 = (RadioButton) bindings[3];
        this.mboundView3 = radioButton2;
        radioButton2.setTag(null);
        this.timeRadioGroup.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
        }
        requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    @Override // android.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (26 == variableId) {
            setVm((AudiSystemViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.AudiSpeedUnitBinding
    public void setVm(AudiSystemViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(26);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVmSpeedUnit((ObservableInt) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmSpeedUnit(ObservableInt VmSpeedUnit, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
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
        AudiSystemViewModel vm = this.mVm;
        int vmSpeedUnitGet = 0;
        RadioGroup.OnCheckedChangeListener vmOnSpedUnitChangeListener = null;
        boolean vmSpeedUnitInt0 = false;
        boolean vmSpeedUnitInt1 = false;
        ObservableInt vmSpeedUnit = null;
        if ((dirtyFlags & 7) != 0) {
            if ((dirtyFlags & 6) != 0 && vm != null) {
                vmOnSpedUnitChangeListener = vm.onSpedUnitChangeListener;
            }
            if (vm != null) {
                vmSpeedUnit = vm.speedUnit;
            }
            updateRegistration(0, vmSpeedUnit);
            if (vmSpeedUnit != null) {
                vmSpeedUnitGet = vmSpeedUnit.get();
            }
            vmSpeedUnitInt0 = vmSpeedUnitGet == 0;
            vmSpeedUnitInt1 = vmSpeedUnitGet == 1;
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
