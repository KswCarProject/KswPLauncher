package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.view.bmwevoid6gs.BmwId6gsViewMode;

/* loaded from: classes7.dex */
public class FraBmwEvoId6GsTwoBindingImpl extends FraBmwEvoId6GsTwoBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ConstraintLayout mboundView2;
    private final ConstraintLayout mboundView3;
    private final ConstraintLayout mboundView4;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.bmw_evo_id6_gs_hmoe_car_hint_textview, 5);
        sparseIntArray.put(C0899R.C0901id.bmw_evo_id6_gs_hmoe_file_hint_textview, 6);
        sparseIntArray.put(C0899R.C0901id.id6_browser_mess_textview, 7);
        sparseIntArray.put(C0899R.C0901id.bmw_evo_id6_gs_hmoe_video_hint_textview, 8);
    }

    public FraBmwEvoId6GsTwoBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    private FraBmwEvoId6GsTwoBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (ConstraintLayout) bindings[1], (TextView) bindings[5], (TextView) bindings[6], (TextView) bindings[8], (TextView) bindings[7]);
        this.mDirtyFlags = -1L;
        this.bmwEvoId6GsHmoeCarBtn.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[2];
        this.mboundView2 = constraintLayout;
        constraintLayout.setTag(null);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) bindings[3];
        this.mboundView3 = constraintLayout2;
        constraintLayout2.setTag(null);
        ConstraintLayout constraintLayout3 = (ConstraintLayout) bindings[4];
        this.mboundView4 = constraintLayout3;
        constraintLayout3.setTag(null);
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
            setVm((BmwId6gsViewMode) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.FraBmwEvoId6GsTwoBinding
    public void setVm(BmwId6gsViewMode Vm) {
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
                return onChangeVmIndex((ObservableInt) object, fieldId);
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

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        boolean vmIndexInt5 = false;
        boolean vmIndexInt4 = false;
        boolean vmIndexInt6 = false;
        boolean vmIndexInt7 = false;
        ObservableInt vmIndex = null;
        BmwId6gsViewMode vm = this.mVm;
        int vmIndexGet = 0;
        if ((dirtyFlags & 7) != 0) {
            if (vm != null) {
                vmIndex = vm.index;
            }
            updateRegistration(0, vmIndex);
            if (vmIndex != null) {
                vmIndexGet = vmIndex.get();
            }
            vmIndexInt5 = vmIndexGet == 5;
            vmIndexInt4 = vmIndexGet == 4;
            vmIndexInt6 = vmIndexGet == 6;
            vmIndexInt7 = vmIndexGet == 7;
        }
        if ((7 & dirtyFlags) != 0) {
            this.bmwEvoId6GsHmoeCarBtn.setSelected(vmIndexInt4);
            this.mboundView2.setSelected(vmIndexInt5);
            this.mboundView3.setSelected(vmIndexInt6);
            this.mboundView4.setSelected(vmIndexInt7);
        }
    }
}
