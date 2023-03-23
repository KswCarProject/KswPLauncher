package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.CompoundButtonBindingAdapter;
import android.databinding.adapters.SeekBarBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi.vm.EQViewModel;
import com.wits.ksw.settings.audi_mib3.vm.AudiMib3EQViewModel;

public class AudiEqViewBindingSw600dpLandImpl extends AudiEqViewBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RadioButton mboundView10;
    private final RadioButton mboundView11;
    private final RadioButton mboundView12;
    private final TextView mboundView2;
    private final TextView mboundView4;
    private final TextView mboundView6;
    private final RadioButton mboundView8;
    private final RadioButton mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.hzMediaLinearLayout, 13);
        sparseIntArray.put(R.id.linearLayout5, 14);
        sparseIntArray.put(R.id.linearLayout6, 15);
    }

    public AudiEqViewBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private AudiEqViewBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 7, bindings[7], bindings[1], bindings[13], bindings[0], bindings[14], bindings[15], bindings[3], bindings[5]);
        this.mDirtyFlags = -1;
        this.aaa.setTag((Object) null);
        this.bassSeekBar.setTag((Object) null);
        this.linearLayout4.setTag((Object) null);
        RadioButton radioButton = bindings[10];
        this.mboundView10 = radioButton;
        radioButton.setTag((Object) null);
        RadioButton radioButton2 = bindings[11];
        this.mboundView11 = radioButton2;
        radioButton2.setTag((Object) null);
        RadioButton radioButton3 = bindings[12];
        this.mboundView12 = radioButton3;
        radioButton3.setTag((Object) null);
        TextView textView = bindings[2];
        this.mboundView2 = textView;
        textView.setTag((Object) null);
        TextView textView2 = bindings[4];
        this.mboundView4 = textView2;
        textView2.setTag((Object) null);
        TextView textView3 = bindings[6];
        this.mboundView6 = textView3;
        textView3.setTag((Object) null);
        RadioButton radioButton4 = bindings[8];
        this.mboundView8 = radioButton4;
        radioButton4.setTag((Object) null);
        RadioButton radioButton5 = bindings[9];
        this.mboundView9 = radioButton5;
        radioButton5.setTag((Object) null);
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

    public boolean setVariable(int variableId, Object variable) {
        if (26 != variableId) {
            return false;
        }
        setVm((EQViewModel) variable);
        return true;
    }

    public void setVm(EQViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        notifyPropertyChanged(26);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVmMezzoStr((ObservableField) object, fieldId);
            case 1:
                return onChangeVmBassStr((ObservableField) object, fieldId);
            case 2:
                return onChangeVmTrebleStr((ObservableField) object, fieldId);
            case 3:
                return onChangeVmBassProgress((ObservableInt) object, fieldId);
            case 4:
                return onChangeVmMezzoProgress((ObservableInt) object, fieldId);
            case 5:
                return onChangeVmTrebleProgress((ObservableInt) object, fieldId);
            case 6:
                return onChangeVmEqModel((ObservableInt) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmMezzoStr(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeVmBassStr(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeVmTrebleStr(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeVmBassProgress(ObservableInt VmBassProgress, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeVmMezzoProgress(ObservableInt VmMezzoProgress, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeVmTrebleProgress(ObservableInt VmTrebleProgress, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeVmEqModel(ObservableInt VmEqModel, int fieldId) {
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
        boolean vmEqModelInt4;
        boolean vmEqModelInt0;
        String vmTrebleStrGet;
        boolean vmEqModelInt2;
        String vmBassStrGet;
        String vmMezzoStrGet;
        int vmTrebleProgressGet;
        ObservableInt vmEqModel;
        int vmEqModelGet;
        ObservableInt vmTrebleProgress;
        ObservableInt vmMezzoProgress;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        ObservableField<String> vmMezzoStr = null;
        ObservableField<String> vmBassStr = null;
        EQViewModel vm = this.mVm;
        int vmMezzoProgressGet = 0;
        int vmTrebleProgressGet2 = 0;
        boolean vmEqModelInt1 = false;
        boolean vmEqModelInt3 = false;
        ObservableField<String> vmTrebleStr = null;
        boolean vmEqModelInt5 = false;
        int vmBassProgressGet = 0;
        ObservableInt vmBassProgress = null;
        String vmBassStrGet2 = null;
        String vmMezzoStrGet2 = null;
        String vmTrebleStrGet2 = null;
        if ((dirtyFlags & 511) != 0) {
            if ((dirtyFlags & 385) != 0) {
                if (vm != null) {
                    vmMezzoStr = vm.mezzoStr;
                }
                updateRegistration(0, (Observable) vmMezzoStr);
                if (vmMezzoStr != null) {
                    vmMezzoStrGet2 = vmMezzoStr.get();
                }
            }
            if ((dirtyFlags & 386) != 0) {
                if (vm != null) {
                    vmBassStr = vm.bassStr;
                }
                updateRegistration(1, (Observable) vmBassStr);
                if (vmBassStr != null) {
                    vmBassStrGet2 = vmBassStr.get();
                }
            }
            if ((dirtyFlags & 388) != 0) {
                if (vm != null) {
                    vmTrebleStr = vm.trebleStr;
                }
                updateRegistration(2, (Observable) vmTrebleStr);
                if (vmTrebleStr != null) {
                    vmTrebleStrGet2 = vmTrebleStr.get();
                }
            }
            if ((dirtyFlags & 392) != 0) {
                if (vm != null) {
                    vmBassProgress = vm.bassProgress;
                }
                updateRegistration(3, (Observable) vmBassProgress);
                if (vmBassProgress != null) {
                    vmBassProgressGet = vmBassProgress.get();
                }
            }
            if ((dirtyFlags & 400) != 0) {
                if (vm != null) {
                    vmMezzoProgress = vm.mezzoProgress;
                } else {
                    vmMezzoProgress = null;
                }
                updateRegistration(4, (Observable) vmMezzoProgress);
                if (vmMezzoProgress != null) {
                    vmMezzoProgressGet = vmMezzoProgress.get();
                    ObservableInt observableInt = vmMezzoProgress;
                } else {
                    ObservableInt observableInt2 = vmMezzoProgress;
                }
            }
            if ((dirtyFlags & 416) != 0) {
                if (vm != null) {
                    vmTrebleProgress = vm.trebleProgress;
                } else {
                    vmTrebleProgress = null;
                }
                updateRegistration(5, (Observable) vmTrebleProgress);
                if (vmTrebleProgress != null) {
                    vmTrebleProgressGet2 = vmTrebleProgress.get();
                    ObservableInt observableInt3 = vmTrebleProgress;
                } else {
                    ObservableInt observableInt4 = vmTrebleProgress;
                }
            }
            if ((dirtyFlags & 448) != 0) {
                if (vm != null) {
                    vmEqModel = vm.eqModel;
                } else {
                    vmEqModel = null;
                }
                updateRegistration(6, (Observable) vmEqModel);
                if (vmEqModel != null) {
                    vmEqModelGet = vmEqModel.get();
                } else {
                    vmEqModelGet = 0;
                }
                ObservableField<String> vmMezzoStr2 = vmMezzoStr;
                vmEqModelInt1 = vmEqModelGet == 1;
                vmEqModelInt3 = vmEqModelGet == 3;
                vmEqModelInt5 = vmEqModelGet == 5;
                boolean vmEqModelInt22 = vmEqModelGet == 2;
                boolean vmEqModelInt02 = vmEqModelGet == 0;
                boolean vmEqModelInt42 = vmEqModelGet == 4;
                ObservableInt observableInt5 = vmEqModel;
                int i = vmEqModelGet;
                vmBassStrGet = vmBassStrGet2;
                vmEqModelInt2 = vmEqModelInt22;
                vmMezzoStrGet = vmMezzoStrGet2;
                ObservableField<String> observableField = vmMezzoStr2;
                ObservableField<String> observableField2 = vmBassStr;
                ObservableField<String> observableField3 = vmTrebleStr;
                vmTrebleStrGet = vmTrebleStrGet2;
                vmEqModelInt0 = vmEqModelInt02;
                ObservableInt observableInt6 = vmBassProgress;
                vmEqModelInt4 = vmEqModelInt42;
            } else {
                ObservableField<String> vmMezzoStr3 = vmMezzoStr;
                vmBassStrGet = vmBassStrGet2;
                vmEqModelInt2 = false;
                vmMezzoStrGet = vmMezzoStrGet2;
                ObservableField<String> observableField4 = vmMezzoStr3;
                ObservableField<String> observableField5 = vmBassStr;
                ObservableField<String> observableField6 = vmTrebleStr;
                vmTrebleStrGet = vmTrebleStrGet2;
                vmEqModelInt0 = false;
                ObservableInt observableInt7 = vmBassProgress;
                vmEqModelInt4 = false;
            }
        } else {
            vmBassStrGet = null;
            vmEqModelInt2 = false;
            vmMezzoStrGet = null;
            vmTrebleStrGet = null;
            vmEqModelInt0 = false;
            vmEqModelInt4 = false;
        }
        if ((dirtyFlags & 448) != 0) {
            vmTrebleProgressGet = vmTrebleProgressGet2;
            CompoundButtonBindingAdapter.setChecked(this.aaa, vmEqModelInt0);
            this.bassSeekBar.setEnabled(vmEqModelInt0);
            CompoundButtonBindingAdapter.setChecked(this.mboundView10, vmEqModelInt3);
            CompoundButtonBindingAdapter.setChecked(this.mboundView11, vmEqModelInt4);
            CompoundButtonBindingAdapter.setChecked(this.mboundView12, vmEqModelInt5);
            CompoundButtonBindingAdapter.setChecked(this.mboundView8, vmEqModelInt1);
            CompoundButtonBindingAdapter.setChecked(this.mboundView9, vmEqModelInt2);
            this.mezzoSeekBar.setEnabled(vmEqModelInt0);
            this.trebleSeekBar.setEnabled(vmEqModelInt0);
        } else {
            vmTrebleProgressGet = vmTrebleProgressGet2;
        }
        if ((dirtyFlags & 256) != 0) {
            boolean z = vmEqModelInt2;
            AudiMib3EQViewModel.setEQModelChangeListener(this.aaa, 0);
            AudiMib3EQViewModel.setEQModelChangeListener(this.mboundView10, 3);
            AudiMib3EQViewModel.setEQModelChangeListener(this.mboundView11, 4);
            AudiMib3EQViewModel.setEQModelChangeListener(this.mboundView12, 5);
            AudiMib3EQViewModel.setEQModelChangeListener(this.mboundView8, 1);
            AudiMib3EQViewModel.setEQModelChangeListener(this.mboundView9, 2);
        }
        if ((dirtyFlags & 392) != 0) {
            SeekBarBindingAdapter.setProgress(this.bassSeekBar, vmBassProgressGet);
        }
        if ((dirtyFlags & 384) != 0) {
            EQViewModel.setBassSeekBarChangeListener(this.bassSeekBar, vm);
            EQViewModel.setmezzoSeekBarChangeListener(this.mezzoSeekBar, vm);
            EQViewModel.setTrebleSeekBarChangeListener(this.trebleSeekBar, vm);
        }
        if ((dirtyFlags & 386) != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, vmBassStrGet);
        }
        if ((dirtyFlags & 385) != 0) {
            TextViewBindingAdapter.setText(this.mboundView4, vmMezzoStrGet);
        }
        if ((dirtyFlags & 388) != 0) {
            TextViewBindingAdapter.setText(this.mboundView6, vmTrebleStrGet);
        }
        if ((dirtyFlags & 400) != 0) {
            SeekBarBindingAdapter.setProgress(this.mezzoSeekBar, vmMezzoProgressGet);
        }
        if ((dirtyFlags & 416) != 0) {
            SeekBarBindingAdapter.setProgress(this.trebleSeekBar, vmTrebleProgressGet);
        }
    }
}
