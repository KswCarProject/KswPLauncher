package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.SeekBarBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi.vm.AudiSystemViewModel;

public class AudiAuxBindingImpl extends AudiAuxBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final SeekBar mboundView3;
    @NonNull
    private final TextView mboundView4;

    static {
        sViewsWithIds.put(R.id.hzMediaLinearLayout, 5);
        sViewsWithIds.put(R.id.audio_seekbar_title, 6);
        sViewsWithIds.put(R.id.hzCallLinearLayout, 7);
    }

    public AudiAuxBindingImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }

    private AudiAuxBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, bindings[1], bindings[2], bindings[6], bindings[7], bindings[5], bindings[0]);
        this.mDirtyFlags = -1;
        this.audioSeekbar.setTag((Object) null);
        this.audioSeekbarRightText.setTag((Object) null);
        this.linearLayout4.setTag((Object) null);
        this.mboundView3 = bindings[3];
        this.mboundView3.setTag((Object) null);
        this.mboundView4 = bindings[4];
        this.mboundView4.setTag((Object) null);
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
        setVm((AudiSystemViewModel) variable);
        return true;
    }

    public void setVm(@Nullable AudiSystemViewModel Vm) {
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
                return onChangeVmAux1Progress((ObservableInt) object, fieldId);
            case 1:
                return onChangeVmAux2Progress((ObservableInt) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmAux1Progress(ObservableInt VmAux1Progress, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeVmAux2Progress(ObservableInt VmAux2Progress, int fieldId) {
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
        AudiSystemViewModel vm = this.mVm;
        String stringValueOfVmAux2Progress = null;
        int vmAux2ProgressGet = 0;
        int vmAux1ProgressGet = 0;
        ObservableInt vmAux1Progress = null;
        SeekBar.OnSeekBarChangeListener vmAux1ChangeListener = null;
        String stringValueOfVmAux1Progress = null;
        SeekBar.OnSeekBarChangeListener vmAux2ChangeListener = null;
        ObservableInt vmAux2Progress = null;
        if ((15 & dirtyFlags) != 0) {
            if ((dirtyFlags & 13) != 0) {
                if (vm != null) {
                    vmAux1Progress = vm.aux1Progress;
                }
                updateRegistration(0, (Observable) vmAux1Progress);
                if (vmAux1Progress != null) {
                    vmAux1ProgressGet = vmAux1Progress.get();
                }
                stringValueOfVmAux1Progress = String.valueOf(vmAux1ProgressGet);
            }
            if (!((dirtyFlags & 12) == 0 || vm == null)) {
                vmAux1ChangeListener = vm.aux1ChangeListener;
                vmAux2ChangeListener = vm.aux2ChangeListener;
            }
            if ((dirtyFlags & 14) != 0) {
                if (vm != null) {
                    vmAux2Progress = vm.aux2Progress;
                }
                updateRegistration(1, (Observable) vmAux2Progress);
                if (vmAux2Progress != null) {
                    vmAux2ProgressGet = vmAux2Progress.get();
                }
                stringValueOfVmAux2Progress = String.valueOf(vmAux2ProgressGet);
            }
        }
        if ((dirtyFlags & 13) != 0) {
            SeekBarBindingAdapter.setProgress(this.audioSeekbar, vmAux1ProgressGet);
            TextViewBindingAdapter.setText(this.audioSeekbarRightText, stringValueOfVmAux1Progress);
        }
        if ((dirtyFlags & 12) != 0) {
            this.audioSeekbar.setOnSeekBarChangeListener(vmAux1ChangeListener);
            this.mboundView3.setOnSeekBarChangeListener(vmAux2ChangeListener);
        }
        if ((dirtyFlags & 14) != 0) {
            SeekBarBindingAdapter.setProgress(this.mboundView3, vmAux2ProgressGet);
            TextViewBindingAdapter.setText(this.mboundView4, stringValueOfVmAux2Progress);
        }
    }
}
