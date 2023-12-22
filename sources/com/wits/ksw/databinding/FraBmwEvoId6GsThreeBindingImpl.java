package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.view.bmwevoid6gs.BmwId6gsViewMode;

/* loaded from: classes7.dex */
public class FraBmwEvoId6GsThreeBindingImpl extends FraBmwEvoId6GsThreeBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ConstraintLayout mboundView4;
    private final ConstraintLayout mboundView5;
    private final ConstraintLayout mboundView6;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.bmw_evo_id6_gs_hmoe_plink_hint_textview, 7);
        sparseIntArray.put(C0899R.C0901id.bmw_evo_id6_gs_hmoe_apps_hint_textview, 8);
        sparseIntArray.put(C0899R.C0901id.bmw_evo_id6_gs_hmoe_set_hint_textview, 9);
    }

    public FraBmwEvoId6GsThreeBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private FraBmwEvoId6GsThreeBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, (TextView) bindings[8], (ConstraintLayout) bindings[1], (TextView) bindings[2], (TextView) bindings[7], (TextView) bindings[9], (TextView) bindings[3]);
        this.mDirtyFlags = -1L;
        this.bmwEvoId6GsHmoeDashBtn.setTag(null);
        this.bmwEvoId6GsHmoeOliHintTextview.setTag(null);
        this.bmwEvoId6GsHmoeSpeedTextview.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[4];
        this.mboundView4 = constraintLayout;
        constraintLayout.setTag(null);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) bindings[5];
        this.mboundView5 = constraintLayout2;
        constraintLayout2.setTag(null);
        ConstraintLayout constraintLayout3 = (ConstraintLayout) bindings[6];
        this.mboundView6 = constraintLayout3;
        constraintLayout3.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16L;
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
            setVm((BmwId6gsViewMode) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.FraBmwEvoId6GsThreeBinding
    public void setVm(BmwId6gsViewMode Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(26);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVmIndex((ObservableInt) object, fieldId);
            case 1:
                return onChangeVmCarInfoOilValue((ObservableField) object, fieldId);
            case 2:
                return onChangeVmCarInfoSpeed((ObservableInt) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmIndex(ObservableInt VmIndex, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmCarInfoOilValue(ObservableField<String> VmCarInfoOilValue, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmCarInfoSpeed(ObservableInt VmCarInfoSpeed, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        boolean vmIndexInt9;
        int vmIndexGet;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        boolean vmIndexInt8 = false;
        String vmCarInfoOilValueGet = null;
        ObservableInt vmIndex = null;
        boolean vmIndexInt11 = false;
        BmwId6gsViewMode vm = this.mVm;
        String vmCarInfoSpeedJavaLangStringKmH = null;
        ObservableField<String> vmCarInfoOilValue = null;
        boolean vmIndexInt10 = false;
        ObservableInt vmCarInfoSpeed = null;
        int vmCarInfoSpeedGet = 0;
        if ((dirtyFlags & 25) == 0) {
            vmIndexInt9 = false;
        } else {
            if (vm != null) {
                vmIndex = vm.index;
            }
            updateRegistration(0, vmIndex);
            if (vmIndex == null) {
                vmIndexGet = 0;
            } else {
                int vmIndexGet2 = vmIndex.get();
                vmIndexGet = vmIndexGet2;
            }
            vmIndexInt8 = vmIndexGet == 8;
            vmIndexInt11 = vmIndexGet == 11;
            vmIndexInt10 = vmIndexGet == 10;
            boolean vmIndexInt92 = vmIndexGet == 9;
            vmIndexInt9 = vmIndexInt92;
        }
        if ((dirtyFlags & 22) != 0) {
            com.wits.ksw.launcher.bean.CarInfo vmCarInfo = BmwId6gsViewMode.carInfo;
            if ((dirtyFlags & 18) != 0) {
                if (vmCarInfo != null) {
                    vmCarInfoOilValue = vmCarInfo.oilValue;
                }
                updateRegistration(1, vmCarInfoOilValue);
                if (vmCarInfoOilValue != null) {
                    vmCarInfoOilValueGet = vmCarInfoOilValue.get();
                }
            }
            if ((dirtyFlags & 20) != 0) {
                if (vmCarInfo != null) {
                    vmCarInfoSpeed = vmCarInfo.speed;
                }
                updateRegistration(2, vmCarInfoSpeed);
                if (vmCarInfoSpeed != null) {
                    vmCarInfoSpeedGet = vmCarInfoSpeed.get();
                }
                vmCarInfoSpeedJavaLangStringKmH = vmCarInfoSpeedGet + "km/h";
                vmCarInfoOilValueGet = vmCarInfoOilValueGet;
            }
        }
        if ((dirtyFlags & 25) != 0) {
            this.bmwEvoId6GsHmoeDashBtn.setSelected(vmIndexInt8);
            this.mboundView4.setSelected(vmIndexInt9);
            this.mboundView5.setSelected(vmIndexInt10);
            this.mboundView6.setSelected(vmIndexInt11);
        }
        if ((dirtyFlags & 18) != 0) {
            TextViewBindingAdapter.setText(this.bmwEvoId6GsHmoeOliHintTextview, vmCarInfoOilValueGet);
        }
        if ((dirtyFlags & 20) != 0) {
            TextViewBindingAdapter.setText(this.bmwEvoId6GsHmoeSpeedTextview, vmCarInfoSpeedJavaLangStringKmH);
        }
    }
}
