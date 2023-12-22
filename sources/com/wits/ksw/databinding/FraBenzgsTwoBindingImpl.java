package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.utils.CustomFontTextView;
import com.wits.ksw.launcher.view.benzgs.BenzGsViewMoel;

/* loaded from: classes7.dex */
public class FraBenzgsTwoBindingImpl extends FraBenzgsTwoBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView1;
    private final RelativeLayout mboundView2;
    private final RelativeLayout mboundView3;
    private final RelativeLayout mboundView4;
    private final RelativeLayout mboundView6;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.benz_gs_home_vide_hint, 7);
        sparseIntArray.put(C0899R.C0901id.benz_gs_home_vide, 8);
        sparseIntArray.put(C0899R.C0901id.benz_gs_home_filema_hint, 9);
        sparseIntArray.put(C0899R.C0901id.benz_gs_home_filema, 10);
        sparseIntArray.put(C0899R.C0901id.benz_gs_home_plink_hint, 11);
        sparseIntArray.put(C0899R.C0901id.benz_gs_home_plink, 12);
        sparseIntArray.put(C0899R.C0901id.benz_gs_home_dash, 13);
        sparseIntArray.put(C0899R.C0901id.benz_gs_home_browser_hint, 14);
        sparseIntArray.put(C0899R.C0901id.benz_gs_home_browser, 15);
    }

    public FraBenzgsTwoBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    private FraBenzgsTwoBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (CustomFontTextView) bindings[15], (CustomFontTextView) bindings[14], (CustomFontTextView) bindings[13], (CustomFontTextView) bindings[5], (CustomFontTextView) bindings[10], (CustomFontTextView) bindings[9], (CustomFontTextView) bindings[12], (CustomFontTextView) bindings[11], (CustomFontTextView) bindings[8], (CustomFontTextView) bindings[7], (LinearLayout) bindings[0]);
        this.mDirtyFlags = -1L;
        this.benzGsHomeDashHint.setTag(null);
        this.benzgsHomeTwo.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[1];
        this.mboundView1 = relativeLayout;
        relativeLayout.setTag(null);
        RelativeLayout relativeLayout2 = (RelativeLayout) bindings[2];
        this.mboundView2 = relativeLayout2;
        relativeLayout2.setTag(null);
        RelativeLayout relativeLayout3 = (RelativeLayout) bindings[3];
        this.mboundView3 = relativeLayout3;
        relativeLayout3.setTag(null);
        RelativeLayout relativeLayout4 = (RelativeLayout) bindings[4];
        this.mboundView4 = relativeLayout4;
        relativeLayout4.setTag(null);
        RelativeLayout relativeLayout5 = (RelativeLayout) bindings[6];
        this.mboundView6 = relativeLayout5;
        relativeLayout5.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8L;
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
            setVm((BenzGsViewMoel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.FraBenzgsTwoBinding
    public void setVm(BenzGsViewMoel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 4;
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

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        boolean vmIndexInt6 = false;
        boolean vmIndexInt8 = false;
        String vmCarInfoOilValueGet = null;
        ObservableInt vmIndex = null;
        BenzGsViewMoel vm = this.mVm;
        boolean vmIndexInt5 = false;
        boolean vmIndexInt7 = false;
        ObservableField<String> vmCarInfoOilValue = null;
        int vmIndexGet = 0;
        boolean vmIndexInt9 = false;
        if ((dirtyFlags & 13) != 0) {
            if (vm != null) {
                vmIndex = vm.index;
            }
            updateRegistration(0, vmIndex);
            if (vmIndex != null) {
                vmIndexGet = vmIndex.get();
            }
            vmIndexInt6 = vmIndexGet == 6;
            vmIndexInt8 = vmIndexGet == 8;
            vmIndexInt5 = vmIndexGet == 5;
            vmIndexInt7 = vmIndexGet == 7;
            vmIndexInt9 = vmIndexGet == 9;
        }
        if ((dirtyFlags & 10) != 0) {
            com.wits.ksw.launcher.bean.CarInfo vmCarInfo = BenzGsViewMoel.carInfo;
            if (vmCarInfo != null) {
                vmCarInfoOilValue = vmCarInfo.oilValue;
            }
            updateRegistration(1, vmCarInfoOilValue);
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
