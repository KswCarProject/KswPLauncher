package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.CompoundButtonBindingAdapter;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi_mib3.vm.AudiMib3SystemViewModel;

public class AudiMib3ReverCameraBindingImpl extends AudiMib3ReverCameraBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.title, 5);
        sparseIntArray.put(R.id.title_divider, 6);
        sparseIntArray.put(R.id.v_divider, 7);
    }

    public AudiMib3ReverCameraBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }

    private AudiMib3ReverCameraBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, bindings[2], bindings[3], bindings[4], bindings[1], bindings[5], bindings[6], bindings[7]);
        this.mDirtyFlags = -1;
        this.RadioButton1.setTag("jz_camera");
        this.RadioButton2.setTag("car_camera");
        this.RadioButton3.setTag((Object) null);
        RelativeLayout relativeLayout = bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
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

    public boolean setVariable(int variableId, Object variable) {
        if (26 != variableId) {
            return false;
        }
        setVm((AudiMib3SystemViewModel) variable);
        return true;
    }

    public void setVm(AudiMib3SystemViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(26);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVmReverCamera((ObservableInt) object, fieldId);
            default:
                return false;
        }
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
        RadioGroup.OnCheckedChangeListener vmOnReverCameraCheckedChangeListener = null;
        int vmReverCameraGet = 0;
        boolean vmReverCameraInt2 = false;
        boolean vmReverCameraInt0 = false;
        boolean vmReverCameraInt1 = false;
        AudiMib3SystemViewModel vm = this.mVm;
        ObservableInt vmReverCamera = null;
        if ((dirtyFlags & 7) != 0) {
            if (!((dirtyFlags & 6) == 0 || vm == null)) {
                vmOnReverCameraCheckedChangeListener = vm.onReverCameraCheckedChangeListener;
            }
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
        }
        if ((7 & dirtyFlags) != 0) {
            CompoundButtonBindingAdapter.setChecked(this.RadioButton1, vmReverCameraInt0);
            CompoundButtonBindingAdapter.setChecked(this.RadioButton2, vmReverCameraInt1);
            CompoundButtonBindingAdapter.setChecked(this.RadioButton3, vmReverCameraInt2);
        }
        if ((6 & dirtyFlags) != 0) {
            this.timeRadioGroup.setOnCheckedChangeListener(vmOnReverCameraCheckedChangeListener);
        }
    }
}
