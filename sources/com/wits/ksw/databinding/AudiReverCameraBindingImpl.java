package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.CompoundButtonBindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RadioGroup;
import com.wits.ksw.settings.audi.vm.AudiSystemViewModel;

public class AudiReverCameraBindingImpl extends AudiReverCameraBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;

    public AudiReverCameraBindingImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private AudiReverCameraBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, bindings[1], bindings[2], bindings[3], bindings[0]);
        this.mDirtyFlags = -1;
        this.RadioButton1.setTag("jz_camera");
        this.RadioButton2.setTag("car_camera");
        this.RadioButton3.setTag((Object) null);
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

    public boolean setVariable(int variableId, @Nullable Object variable) {
        if (17 != variableId) {
            return false;
        }
        setVm((AudiSystemViewModel) variable);
        return true;
    }

    public void setVm(@Nullable AudiSystemViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(17);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeVmReverCamera((ObservableInt) object, fieldId);
    }

    private boolean onChangeVmReverCamera(ObservableInt VmReverCamera, int fieldId) {
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
        AudiSystemViewModel vm = this.mVm;
        ObservableInt vmReverCamera = null;
        boolean vmReverCameraInt2 = false;
        boolean vmReverCameraInt0 = false;
        int vmReverCameraGet = 0;
        boolean vmReverCameraInt1 = false;
        RadioGroup.OnCheckedChangeListener vmOnReverCameraCheckedChangeListener = null;
        if ((dirtyFlags & 7) != 0) {
            if (vm != null) {
                vmReverCamera = vm.reverCamera;
            }
            updateRegistration(0, (Observable) vmReverCamera);
            if (vmReverCamera != null) {
                vmReverCameraGet = vmReverCamera.get();
            }
            vmReverCameraInt2 = vmReverCameraGet == 2;
            vmReverCameraInt0 = vmReverCameraGet == 0;
            vmReverCameraInt1 = vmReverCameraGet == 1;
            if (!((dirtyFlags & 6) == 0 || vm == null)) {
                vmOnReverCameraCheckedChangeListener = vm.onReverCameraCheckedChangeListener;
            }
        }
        if ((7 & dirtyFlags) != 0) {
            CompoundButtonBindingAdapter.setChecked(this.RadioButton1, vmReverCameraInt0);
            CompoundButtonBindingAdapter.setChecked(this.RadioButton2, vmReverCameraInt1);
            CompoundButtonBindingAdapter.setChecked(this.RadioButton3, vmReverCameraInt2);
        }
        if ((dirtyFlags & 6) != 0) {
            this.timeRadioGroup.setOnCheckedChangeListener(vmOnReverCameraCheckedChangeListener);
        }
    }
}
