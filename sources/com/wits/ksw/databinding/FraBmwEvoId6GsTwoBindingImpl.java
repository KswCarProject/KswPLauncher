package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.view.bmwevoid6gs.BmwId6gsViewMode;

public class FraBmwEvoId6GsTwoBindingImpl extends FraBmwEvoId6GsTwoBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    private final ConstraintLayout mboundView2;
    @NonNull
    private final ConstraintLayout mboundView3;
    @NonNull
    private final ConstraintLayout mboundView4;

    static {
        sViewsWithIds.put(R.id.bmw_evo_id6_gs_hmoe_car_hint_textview, 5);
        sViewsWithIds.put(R.id.bmw_evo_id6_gs_hmoe_file_hint_textview, 6);
        sViewsWithIds.put(R.id.id6_browser_mess_textview, 7);
        sViewsWithIds.put(R.id.bmw_evo_id6_gs_hmoe_video_hint_textview, 8);
    }

    public FraBmwEvoId6GsTwoBindingImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    private FraBmwEvoId6GsTwoBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, bindings[1], bindings[5], bindings[6], bindings[8], bindings[7]);
        this.mDirtyFlags = -1;
        this.bmwEvoId6GsHmoeCarBtn.setTag((Object) null);
        this.mboundView0 = bindings[0];
        this.mboundView0.setTag((Object) null);
        this.mboundView2 = bindings[2];
        this.mboundView2.setTag((Object) null);
        this.mboundView3 = bindings[3];
        this.mboundView3.setTag((Object) null);
        this.mboundView4 = bindings[4];
        this.mboundView4.setTag((Object) null);
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

    public boolean setVariable(int variableId, @Nullable Object variable) {
        if (11 != variableId) {
            return false;
        }
        setVm((BmwId6gsViewMode) variable);
        return true;
    }

    public void setVm(@Nullable BmwId6gsViewMode Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(11);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeVmIndex((ObservableInt) object, fieldId);
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
        ObservableInt vmIndex = null;
        BmwId6gsViewMode vm = this.mVm;
        boolean vmIndexInt5 = false;
        boolean vmIndexInt6 = false;
        int vmIndexGet = 0;
        boolean vmIndexInt4 = false;
        boolean vmIndexInt7 = false;
        if ((dirtyFlags & 7) != 0) {
            if (vm != null) {
                vmIndex = vm.index;
            }
            updateRegistration(0, (Observable) vmIndex);
            if (vmIndex != null) {
                vmIndexGet = vmIndex.get();
            }
            boolean z = true;
            vmIndexInt5 = vmIndexGet == 5;
            vmIndexInt6 = vmIndexGet == 6;
            vmIndexInt4 = vmIndexGet == 4;
            if (vmIndexGet != 7) {
                z = false;
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
