package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.CompoundButtonBindingAdapter;
import android.databinding.adapters.SeekBarBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi.vm.EQViewModel;

public class AudiEqViewBindingImpl extends AudiEqViewBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final RadioButton mboundView10;
    @NonNull
    private final RadioButton mboundView11;
    @NonNull
    private final RadioButton mboundView12;
    @NonNull
    private final TextView mboundView2;
    @NonNull
    private final TextView mboundView4;
    @NonNull
    private final TextView mboundView6;
    @NonNull
    private final RadioButton mboundView8;
    @NonNull
    private final RadioButton mboundView9;

    static {
        sViewsWithIds.put(R.id.hzMediaLinearLayout, 13);
        sViewsWithIds.put(R.id.linearLayout5, 14);
        sViewsWithIds.put(R.id.linearLayout6, 15);
    }

    public AudiEqViewBindingImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private AudiEqViewBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 7, bindings[7], bindings[1], bindings[13], bindings[0], bindings[14], bindings[15], bindings[3], bindings[5]);
        this.mDirtyFlags = -1;
        this.aaa.setTag((Object) null);
        this.bassSeekBar.setTag((Object) null);
        this.linearLayout4.setTag((Object) null);
        this.mboundView10 = bindings[10];
        this.mboundView10.setTag((Object) null);
        this.mboundView11 = bindings[11];
        this.mboundView11.setTag((Object) null);
        this.mboundView12 = bindings[12];
        this.mboundView12.setTag((Object) null);
        this.mboundView2 = bindings[2];
        this.mboundView2.setTag((Object) null);
        this.mboundView4 = bindings[4];
        this.mboundView4.setTag((Object) null);
        this.mboundView6 = bindings[6];
        this.mboundView6.setTag((Object) null);
        this.mboundView8 = bindings[8];
        this.mboundView8.setTag((Object) null);
        this.mboundView9 = bindings[9];
        this.mboundView9.setTag((Object) null);
        this.mezzoSeekBar.setTag((Object) null);
        this.trebleSeekBar.setTag((Object) null);
        View view = root;
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 256;
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
        setVm((EQViewModel) variable);
        return true;
    }

    public void setVm(@Nullable EQViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        notifyPropertyChanged(10);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVmEqModel((ObservableInt) object, fieldId);
            case 1:
                return onChangeVmMezzoStr((ObservableField) object, fieldId);
            case 2:
                return onChangeVmBassStr((ObservableField) object, fieldId);
            case 3:
                return onChangeVmMezzoProgress((ObservableInt) object, fieldId);
            case 4:
                return onChangeVmTrebleStr((ObservableField) object, fieldId);
            case 5:
                return onChangeVmBassProgress((ObservableInt) object, fieldId);
            case 6:
                return onChangeVmTrebleProgress((ObservableInt) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmEqModel(ObservableInt VmEqModel, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeVmMezzoStr(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeVmBassStr(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeVmMezzoProgress(ObservableInt VmMezzoProgress, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeVmTrebleStr(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeVmBassProgress(ObservableInt VmBassProgress, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeVmTrebleProgress(ObservableInt VmTrebleProgress, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        int vmTrebleProgressGet;
        ObservableInt vmTrebleProgress;
        ObservableInt vmBassProgress;
        ObservableField<String> vmTrebleStr;
        ObservableInt vmMezzoProgress;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        EQViewModel vm = this.mVm;
        boolean vmEqModelInt0 = false;
        int vmEqModelGet = 0;
        String vmMezzoStrGet = null;
        boolean vmEqModelInt2 = false;
        ObservableInt vmEqModel = null;
        int vmTrebleProgressGet2 = 0;
        String vmBassStrGet = null;
        boolean vmEqModelInt1 = false;
        ObservableField<String> vmMezzoStr = null;
        ObservableField<String> vmBassStr = null;
        boolean vmEqModelInt12 = false;
        int vmBassProgressGet = 0;
        ObservableInt vmMezzoProgress2 = null;
        String vmTrebleStrGet = null;
        boolean vmEqModelInt4 = false;
        int vmMezzoProgressGet = 0;
        boolean vmEqModelInt3 = false;
        if ((dirtyFlags & 511) != 0) {
            if ((dirtyFlags & 385) != 0) {
                if (vm != null) {
                    vmEqModel = vm.eqModel;
                }
                updateRegistration(0, (Observable) vmEqModel);
                if (vmEqModel != null) {
                    vmEqModelGet = vmEqModel.get();
                }
                boolean vmEqModelInt5 = vmEqModelGet == 5;
                boolean vmEqModelInt02 = vmEqModelGet == 0;
                boolean vmEqModelInt22 = vmEqModelGet == 2;
                boolean vmEqModelInt13 = vmEqModelGet == 1;
                boolean vmEqModelInt42 = vmEqModelGet == 4;
                boolean vmEqModelInt52 = vmEqModelInt5;
                vmEqModelInt3 = vmEqModelGet == 3;
                vmEqModelInt4 = vmEqModelInt42;
                vmEqModelInt12 = vmEqModelInt13;
                vmEqModelInt1 = vmEqModelInt22;
                vmEqModelInt2 = vmEqModelInt02;
                vmEqModelInt0 = vmEqModelInt52;
            }
            if ((dirtyFlags & 386) != 0) {
                if (vm != null) {
                    vmMezzoStr = vm.mezzoStr;
                }
                updateRegistration(1, (Observable) vmMezzoStr);
                if (vmMezzoStr != null) {
                    vmMezzoStrGet = vmMezzoStr.get();
                }
            }
            if ((dirtyFlags & 388) != 0) {
                if (vm != null) {
                    vmBassStr = vm.bassStr;
                }
                updateRegistration(2, (Observable) vmBassStr);
                if (vmBassStr != null) {
                    vmBassStrGet = vmBassStr.get();
                }
            }
            if ((dirtyFlags & 392) != 0) {
                if (vm != null) {
                    vmMezzoProgress = vm.mezzoProgress;
                } else {
                    vmMezzoProgress = null;
                }
                updateRegistration(3, (Observable) vmMezzoProgress);
                if (vmMezzoProgress != null) {
                    vmMezzoProgress2 = vmMezzoProgress;
                    vmMezzoProgressGet = vmMezzoProgress.get();
                } else {
                    vmMezzoProgress2 = vmMezzoProgress;
                }
            }
            if ((dirtyFlags & 400) != 0) {
                if (vm != null) {
                    vmTrebleStr = vm.trebleStr;
                } else {
                    vmTrebleStr = null;
                }
                updateRegistration(4, (Observable) vmTrebleStr);
                if (vmTrebleStr != null) {
                    ObservableField<String> observableField = vmTrebleStr;
                    vmTrebleStrGet = vmTrebleStr.get();
                } else {
                    ObservableField<String> observableField2 = vmTrebleStr;
                }
            }
            if ((dirtyFlags & 416) != 0) {
                if (vm != null) {
                    vmBassProgress = vm.bassProgress;
                } else {
                    vmBassProgress = null;
                }
                updateRegistration(5, (Observable) vmBassProgress);
                if (vmBassProgress != null) {
                    ObservableInt observableInt = vmBassProgress;
                    vmBassProgressGet = vmBassProgress.get();
                } else {
                    ObservableInt observableInt2 = vmBassProgress;
                }
            }
            if ((dirtyFlags & 448) != 0) {
                if (vm != null) {
                    vmTrebleProgress = vm.trebleProgress;
                } else {
                    vmTrebleProgress = null;
                }
                updateRegistration(6, (Observable) vmTrebleProgress);
                if (vmTrebleProgress != null) {
                    vmTrebleProgressGet2 = vmTrebleProgress.get();
                }
            }
        }
        boolean vmEqModelInt43 = vmEqModelInt4;
        boolean vmEqModelInt32 = vmEqModelInt3;
        boolean vmEqModelInt14 = vmEqModelInt12;
        String vmMezzoStrGet2 = vmMezzoStrGet;
        int vmBassProgressGet2 = vmBassProgressGet;
        int vmMezzoProgressGet2 = vmMezzoProgressGet;
        ObservableField<String> observableField3 = vmBassStr;
        int vmTrebleProgressGet3 = vmTrebleProgressGet2;
        String vmTrebleStrGet2 = vmTrebleStrGet;
        ObservableField<String> vmBassStr2 = observableField3;
        if ((dirtyFlags & 385) != 0) {
            vmTrebleProgressGet = vmTrebleProgressGet3;
            CompoundButtonBindingAdapter.setChecked(this.aaa, vmEqModelInt2);
            this.bassSeekBar.setEnabled(vmEqModelInt2);
            CompoundButtonBindingAdapter.setChecked(this.mboundView10, vmEqModelInt32);
            CompoundButtonBindingAdapter.setChecked(this.mboundView11, vmEqModelInt43);
            CompoundButtonBindingAdapter.setChecked(this.mboundView12, vmEqModelInt0);
            CompoundButtonBindingAdapter.setChecked(this.mboundView8, vmEqModelInt14);
            CompoundButtonBindingAdapter.setChecked(this.mboundView9, vmEqModelInt1);
            this.mezzoSeekBar.setEnabled(vmEqModelInt2);
            this.trebleSeekBar.setEnabled(vmEqModelInt2);
        } else {
            vmTrebleProgressGet = vmTrebleProgressGet3;
        }
        if ((dirtyFlags & 256) != 0) {
            boolean z = vmEqModelInt32;
            EQViewModel.setEQModelChangeListener(this.aaa, 0);
            EQViewModel.setEQModelChangeListener(this.mboundView10, 3);
            EQViewModel.setEQModelChangeListener(this.mboundView11, 4);
            EQViewModel.setEQModelChangeListener(this.mboundView12, 5);
            EQViewModel.setEQModelChangeListener(this.mboundView8, 1);
            EQViewModel.setEQModelChangeListener(this.mboundView9, 2);
        }
        if ((dirtyFlags & 416) != 0) {
            SeekBarBindingAdapter.setProgress(this.bassSeekBar, vmBassProgressGet2);
        }
        if ((dirtyFlags & 384) != 0) {
            EQViewModel.setBassSeekBarChangeListener(this.bassSeekBar, vm);
            EQViewModel.setmezzoSeekBarChangeListener(this.mezzoSeekBar, vm);
            EQViewModel.setTrebleSeekBarChangeListener(this.trebleSeekBar, vm);
        }
        if ((dirtyFlags & 388) != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, vmBassStrGet);
        }
        if ((dirtyFlags & 386) != 0) {
            TextViewBindingAdapter.setText(this.mboundView4, vmMezzoStrGet2);
        }
        if ((dirtyFlags & 400) != 0) {
            TextViewBindingAdapter.setText(this.mboundView6, vmTrebleStrGet2);
        }
        if ((dirtyFlags & 392) != 0) {
            SeekBarBindingAdapter.setProgress(this.mezzoSeekBar, vmMezzoProgressGet2);
        }
        if ((dirtyFlags & 448) != 0) {
            SeekBarBindingAdapter.setProgress(this.trebleSeekBar, vmTrebleProgressGet);
        }
    }
}
