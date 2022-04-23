package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.view.bmwevoid6gs.BmwId6gsViewMode;

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
        sparseIntArray.put(R.id.bmw_evo_id6_gs_hmoe_car_hint_textview, 5);
        sparseIntArray.put(R.id.bmw_evo_id6_gs_hmoe_file_hint_textview, 6);
        sparseIntArray.put(R.id.id6_browser_mess_textview, 7);
        sparseIntArray.put(R.id.bmw_evo_id6_gs_hmoe_video_hint_textview, 8);
    }

    public FraBmwEvoId6GsTwoBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    private FraBmwEvoId6GsTwoBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, bindings[1], bindings[5], bindings[6], bindings[8], bindings[7]);
        this.mDirtyFlags = -1;
        this.bmwEvoId6GsHmoeCarBtn.setTag((Object) null);
        LinearLayout linearLayout = bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag((Object) null);
        ConstraintLayout constraintLayout = bindings[2];
        this.mboundView2 = constraintLayout;
        constraintLayout.setTag((Object) null);
        ConstraintLayout constraintLayout2 = bindings[3];
        this.mboundView3 = constraintLayout2;
        constraintLayout2.setTag((Object) null);
        ConstraintLayout constraintLayout3 = bindings[4];
        this.mboundView4 = constraintLayout3;
        constraintLayout3.setTag((Object) null);
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
        if (17 != variableId) {
            return false;
        }
        setVm((BmwId6gsViewMode) variable);
        return true;
    }

    public void setVm(BmwId6gsViewMode Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(17);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVmIndex((ObservableInt) object, fieldId);
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

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
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
            boolean z = false;
            updateRegistration(0, (Observable) vmIndex);
            if (vmIndex != null) {
                vmIndexGet = vmIndex.get();
            }
            vmIndexInt5 = vmIndexGet == 5;
            vmIndexInt4 = vmIndexGet == 4;
            vmIndexInt6 = vmIndexGet == 6;
            if (vmIndexGet == 7) {
                z = true;
            }
            vmIndexInt7 = z;
        }
        if ((7 & dirtyFlags) != 0) {
            this.bmwEvoId6GsHmoeCarBtn.setSelected(vmIndexInt4);
            this.mboundView2.setSelected(vmIndexInt5);
            this.mboundView3.setSelected(vmIndexInt6);
            this.mboundView4.setSelected(vmIndexInt7);
        }
    }
}
