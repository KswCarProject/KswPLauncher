package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.CarInfo;
import com.wits.ksw.launcher.view.bmwevoid6gs.BmwId6gsViewMode;

public class FraBmwEvoId6GsThreeBindingImpl extends FraBmwEvoId6GsThreeBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    private final ConstraintLayout mboundView4;
    @NonNull
    private final ConstraintLayout mboundView5;
    @NonNull
    private final ConstraintLayout mboundView6;

    static {
        sViewsWithIds.put(R.id.bmw_evo_id6_gs_hmoe_plink_hint_textview, 7);
        sViewsWithIds.put(R.id.bmw_evo_id6_gs_hmoe_apps_hint_textview, 8);
        sViewsWithIds.put(R.id.bmw_evo_id6_gs_hmoe_set_hint_textview, 9);
    }

    public FraBmwEvoId6GsThreeBindingImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private FraBmwEvoId6GsThreeBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, bindings[8], bindings[1], bindings[2], bindings[7], bindings[9], bindings[3]);
        this.mDirtyFlags = -1;
        this.bmwEvoId6GsHmoeDashBtn.setTag((Object) null);
        this.bmwEvoId6GsHmoeOliHintTextview.setTag((Object) null);
        this.bmwEvoId6GsHmoeSpeedTextview.setTag((Object) null);
        this.mboundView0 = bindings[0];
        this.mboundView0.setTag((Object) null);
        this.mboundView4 = bindings[4];
        this.mboundView4.setTag((Object) null);
        this.mboundView5 = bindings[5];
        this.mboundView5.setTag((Object) null);
        this.mboundView6 = bindings[6];
        this.mboundView6.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16;
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

    public boolean setVariable(int variableId, @Nullable Object variable) {
        if (10 != variableId) {
            return false;
        }
        setVm((BmwId6gsViewMode) variable);
        return true;
    }

    public void setVm(@Nullable BmwId6gsViewMode Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(10);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVmIndex((ObservableInt) object, fieldId);
            case 1:
                return onChangeVmCarInfoSpeed((ObservableInt) object, fieldId);
            case 2:
                return onChangeVmCarInfoOilValue((ObservableInt) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmIndex(ObservableInt VmIndex, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeVmCarInfoSpeed(ObservableInt VmCarInfoSpeed, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeVmCarInfoOilValue(ObservableInt VmCarInfoOilValue, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        ObservableInt vmCarInfoOilValue;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        ObservableInt vmIndex = null;
        BmwId6gsViewMode vm = this.mVm;
        String vmCarInfoOilValueJavaLangStringL = null;
        boolean vmIndexInt8 = false;
        int vmCarInfoOilValueGet = 0;
        boolean vmIndexInt11 = false;
        String vmCarInfoSpeedJavaLangStringKmH = null;
        ObservableInt vmCarInfoSpeed = null;
        int vmCarInfoSpeedGet = 0;
        int vmIndexGet = 0;
        boolean vmIndexInt9 = false;
        boolean vmIndexInt10 = false;
        if ((dirtyFlags & 25) != 0) {
            if (vm != null) {
                vmIndex = vm.index;
            }
            updateRegistration(0, (Observable) vmIndex);
            if (vmIndex != null) {
                vmIndexGet = vmIndex.get();
            }
            vmIndexInt8 = vmIndexGet == 8;
            vmIndexInt11 = vmIndexGet == 11;
            vmIndexInt9 = vmIndexGet == 9;
            vmIndexInt10 = vmIndexGet == 10;
        }
        ObservableInt vmIndex2 = vmIndex;
        boolean vmIndexInt92 = vmIndexInt9;
        boolean vmIndexInt102 = vmIndexInt10;
        if ((dirtyFlags & 22) != 0) {
            CarInfo vmCarInfo = BmwId6gsViewMode.carInfo;
            if ((dirtyFlags & 18) != 0) {
                if (vmCarInfo != null) {
                    vmCarInfoSpeed = vmCarInfo.speed;
                }
                updateRegistration(1, (Observable) vmCarInfoSpeed);
                if (vmCarInfoSpeed != null) {
                    vmCarInfoSpeedGet = vmCarInfoSpeed.get();
                }
                StringBuilder sb = new StringBuilder();
                sb.append(vmCarInfoSpeedGet);
                ObservableInt observableInt = vmIndex2;
                sb.append("km/h");
                vmCarInfoSpeedJavaLangStringKmH = sb.toString();
            }
            if ((dirtyFlags & 20) != 0) {
                if (vmCarInfo != null) {
                    vmCarInfoOilValue = vmCarInfo.oilValue;
                } else {
                    vmCarInfoOilValue = null;
                }
                updateRegistration(2, (Observable) vmCarInfoOilValue);
                if (vmCarInfoOilValue != null) {
                    vmCarInfoOilValueGet = vmCarInfoOilValue.get();
                }
                vmCarInfoOilValueJavaLangStringL = vmCarInfoOilValueGet + "L";
                ObservableInt observableInt2 = vmCarInfoOilValue;
            }
        }
        if ((dirtyFlags & 25) != 0) {
            this.bmwEvoId6GsHmoeDashBtn.setSelected(vmIndexInt8);
            this.mboundView4.setSelected(vmIndexInt92);
            this.mboundView5.setSelected(vmIndexInt102);
            this.mboundView6.setSelected(vmIndexInt11);
        }
        if ((dirtyFlags & 20) != 0) {
            TextViewBindingAdapter.setText(this.bmwEvoId6GsHmoeOliHintTextview, vmCarInfoOilValueJavaLangStringL);
        }
        if ((dirtyFlags & 18) != 0) {
            TextViewBindingAdapter.setText(this.bmwEvoId6GsHmoeSpeedTextview, vmCarInfoSpeedJavaLangStringKmH);
        }
    }
}
