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
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi_mib3.vm.AudiMib3SystemViewModel;

public class AudiMib3SpeedUnitBindingImpl extends AudiMib3SpeedUnitBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final RadioButton mboundView2;
    private final RadioButton mboundView3;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.title, 4);
        sparseIntArray.put(R.id.title_divider, 5);
        sparseIntArray.put(R.id.cl_unit, 6);
        sparseIntArray.put(R.id.v_divider, 7);
    }

    public AudiMib3SpeedUnitBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }

    private AudiMib3SpeedUnitBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, bindings[6], bindings[1], bindings[4], bindings[5], bindings[7]);
        this.mDirtyFlags = -1;
        RelativeLayout relativeLayout = bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
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
        setVm((AudiMib3SystemViewModel) variable);
        return true;
    }

    public void setVm(AudiMib3SystemViewModel Vm) {
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
        int vmSpeedUnitGet = 0;
        RadioGroup.OnCheckedChangeListener vmOnSpedUnitChangeListener = null;
        ObservableInt vmSpeedUnit = null;
        AudiMib3SystemViewModel vm = this.mVm;
        boolean vmSpeedUnitInt0 = false;
        boolean vmSpeedUnitInt1 = false;
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
