package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.CompoundButtonBindingAdapter;
import android.support.p004v7.widget.AppCompatTextView;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.audi_mib3.p008vm.AudiMib3SystemViewModel;

/* loaded from: classes7.dex */
public class AudiMib3TempBindingHdpi1920x720Impl extends AudiMib3TempBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final RadioButton mboundView2;
    private final RadioButton mboundView3;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.title, 4);
        sparseIntArray.put(C0899R.C0901id.title_divider, 5);
        sparseIntArray.put(C0899R.C0901id.v_divider, 6);
    }

    public AudiMib3TempBindingHdpi1920x720Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private AudiMib3TempBindingHdpi1920x720Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (RadioGroup) bindings[1], (AppCompatTextView) bindings[4], (View) bindings[5], (View) bindings[6]);
        this.mDirtyFlags = -1L;
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
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
            setVm((AudiMib3SystemViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.AudiMib3TempBinding
    public void setVm(AudiMib3SystemViewModel Vm) {
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
                return onChangeVmTempUnit((ObservableInt) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmTempUnit(ObservableInt VmTempUnit, int fieldId) {
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
        RadioGroup.OnCheckedChangeListener vmOnTempUnitChangeListener = null;
        int vmTempUnitGet = 0;
        boolean vmTempUnitInt0 = false;
        boolean vmTempUnitInt1 = false;
        AudiMib3SystemViewModel vm = this.mVm;
        ObservableInt vmTempUnit = null;
        if ((dirtyFlags & 7) != 0) {
            if ((dirtyFlags & 6) != 0 && vm != null) {
                vmOnTempUnitChangeListener = vm.onTempUnitChangeListener;
            }
            if (vm != null) {
                vmTempUnit = vm.tempUnit;
            }
            updateRegistration(0, vmTempUnit);
            if (vmTempUnit != null) {
                vmTempUnitGet = vmTempUnit.get();
            }
            vmTempUnitInt0 = vmTempUnitGet == 0;
            vmTempUnitInt1 = vmTempUnitGet == 1;
        }
        if ((7 & dirtyFlags) != 0) {
            CompoundButtonBindingAdapter.setChecked(this.mboundView2, vmTempUnitInt0);
            CompoundButtonBindingAdapter.setChecked(this.mboundView3, vmTempUnitInt1);
        }
        if ((dirtyFlags & 6) != 0) {
            this.timeRadioGroup.setOnCheckedChangeListener(vmOnTempUnitChangeListener);
        }
    }
}
