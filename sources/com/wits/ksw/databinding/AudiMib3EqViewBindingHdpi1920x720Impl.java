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
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;
import com.wits.ksw.settings.audi_mib3.p008vm.AudiMib3EQViewModel;

/* loaded from: classes7.dex */
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
        sparseIntArray.put(C0899R.C0901id.title, 7);
        sparseIntArray.put(C0899R.C0901id.title_divider, 8);
        sparseIntArray.put(C0899R.C0901id.linearLayout4, 9);
        sparseIntArray.put(C0899R.C0901id.rg_list, 10);
        sparseIntArray.put(C0899R.C0901id.v_divider, 11);
    }

    public AudiMib3EqViewBindingHdpi1920x720Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }

    private AudiMib3EqViewBindingHdpi1920x720Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (RadioButton) bindings[1], (AudiConstraintLayout) bindings[9], (RadioGroup) bindings[10], (AppCompatTextView) bindings[7], (View) bindings[8], (View) bindings[11]);
        this.mDirtyFlags = -1L;
        this.aaa.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        RadioButton radioButton = (RadioButton) bindings[2];
        this.mboundView2 = radioButton;
        radioButton.setTag(null);
        RadioButton radioButton2 = (RadioButton) bindings[3];
        this.mboundView3 = radioButton2;
        radioButton2.setTag(null);
        RadioButton radioButton3 = (RadioButton) bindings[4];
        this.mboundView4 = radioButton3;
        radioButton3.setTag(null);
        RadioButton radioButton4 = (RadioButton) bindings[5];
        this.mboundView5 = radioButton4;
        radioButton4.setTag(null);
        RadioButton radioButton5 = (RadioButton) bindings[6];
        this.mboundView6 = radioButton5;
        radioButton5.setTag(null);
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
            setVm((AudiMib3EQViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.AudiMib3EqViewBinding
    public void setVm(AudiMib3EQViewModel Vm) {
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
                return onChangeVmEqModel((ObservableInt) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmEqModel(ObservableInt VmEqModel, int fieldId) {
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
            updateRegistration(0, vmEqModel);
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
