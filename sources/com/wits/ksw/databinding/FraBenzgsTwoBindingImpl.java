package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.CarInfo;
import com.wits.ksw.launcher.view.benzgs.BenzGsViewMoel;

public class FraBenzgsTwoBindingImpl extends FraBenzgsTwoBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final RelativeLayout mboundView1;
    @NonNull
    private final RelativeLayout mboundView2;
    @NonNull
    private final RelativeLayout mboundView3;
    @NonNull
    private final RelativeLayout mboundView4;
    @NonNull
    private final RelativeLayout mboundView6;

    static {
        sViewsWithIds.put(R.id.benz_gs_home_vide_hint, 7);
        sViewsWithIds.put(R.id.benz_gs_home_vide, 8);
        sViewsWithIds.put(R.id.benz_gs_home_filema_hint, 9);
        sViewsWithIds.put(R.id.benz_gs_home_filema, 10);
        sViewsWithIds.put(R.id.benz_gs_home_plink_hint, 11);
        sViewsWithIds.put(R.id.benz_gs_home_plink, 12);
        sViewsWithIds.put(R.id.benz_gs_home_dash, 13);
        sViewsWithIds.put(R.id.benz_gs_home_browser_hint, 14);
        sViewsWithIds.put(R.id.benz_gs_home_browser, 15);
    }

    public FraBenzgsTwoBindingImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private FraBenzgsTwoBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, bindings[15], bindings[14], bindings[13], bindings[5], bindings[10], bindings[9], bindings[12], bindings[11], bindings[8], bindings[7], bindings[0]);
        this.mDirtyFlags = -1;
        this.benzGsHomeDashHint.setTag((Object) null);
        this.benzgsHomeTwo.setTag((Object) null);
        this.mboundView1 = bindings[1];
        this.mboundView1.setTag((Object) null);
        this.mboundView2 = bindings[2];
        this.mboundView2.setTag((Object) null);
        this.mboundView3 = bindings[3];
        this.mboundView3.setTag((Object) null);
        this.mboundView4 = bindings[4];
        this.mboundView4.setTag((Object) null);
        this.mboundView6 = bindings[6];
        this.mboundView6.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8;
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
        if (9 != variableId) {
            return false;
        }
        setVm((BenzGsViewMoel) variable);
        return true;
    }

    public void setVm(@Nullable BenzGsViewMoel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(9);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVmIndex((ObservableInt) object, fieldId);
            case 1:
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

    private boolean onChangeVmCarInfoOilValue(ObservableInt VmCarInfoOilValue, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
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
        ObservableInt vmIndex = null;
        BenzGsViewMoel vm = this.mVm;
        boolean vmIndexInt5 = false;
        boolean vmIndexInt8 = false;
        int vmCarInfoOilValueGet = 0;
        boolean vmIndexInt6 = false;
        int vmIndexGet = 0;
        boolean vmIndexInt9 = false;
        boolean vmIndexInt7 = false;
        ObservableInt vmCarInfoOilValue = null;
        if ((dirtyFlags & 13) != 0) {
            if (vm != null) {
                vmIndex = vm.index;
            }
            updateRegistration(0, (Observable) vmIndex);
            if (vmIndex != null) {
                vmIndexGet = vmIndex.get();
            }
            vmIndexInt5 = vmIndexGet == 5;
            vmIndexInt8 = vmIndexGet == 8;
            vmIndexInt6 = vmIndexGet == 6;
            vmIndexInt9 = vmIndexGet == 9;
            vmIndexInt7 = vmIndexGet == 7;
        }
        if ((dirtyFlags & 10) != 0) {
            CarInfo vmCarInfo = BenzGsViewMoel.carInfo;
            if (vmCarInfo != null) {
                vmCarInfoOilValue = vmCarInfo.oilValue;
            }
            updateRegistration(1, (Observable) vmCarInfoOilValue);
            if (vmCarInfoOilValue != null) {
                vmCarInfoOilValueGet = vmCarInfoOilValue.get();
            }
        }
        if ((dirtyFlags & 10) != 0) {
            BenzGsViewMoel.setOliText(this.benzGsHomeDashHint, vmCarInfoOilValueGet);
        }
        if ((dirtyFlags & 13) != 0) {
            this.mboundView1.setSelected(vmIndexInt5);
            this.mboundView2.setSelected(vmIndexInt6);
            this.mboundView3.setSelected(vmIndexInt7);
            this.mboundView4.setSelected(vmIndexInt8);
            this.mboundView6.setSelected(vmIndexInt9);
        }
    }
}
