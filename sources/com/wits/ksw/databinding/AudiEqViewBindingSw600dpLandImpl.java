package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.CompoundButtonBindingAdapter;
import android.databinding.adapters.SeekBarBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.audi.p007vm.EQViewModel;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;
import com.wits.ksw.settings.audi.widget.EqSeekBar;
import com.wits.ksw.settings.audi_mib3.p008vm.AudiMib3EQViewModel;

/* loaded from: classes7.dex */
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
        sparseIntArray.put(C0899R.C0901id.hzMediaLinearLayout, 13);
        sparseIntArray.put(C0899R.C0901id.linearLayout5, 14);
        sparseIntArray.put(C0899R.C0901id.linearLayout6, 15);
    }

    public AudiEqViewBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    private AudiEqViewBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 7, (RadioButton) bindings[7], (EqSeekBar) bindings[1], (LinearLayout) bindings[13], (AudiConstraintLayout) bindings[0], (LinearLayout) bindings[14], (LinearLayout) bindings[15], (EqSeekBar) bindings[3], (EqSeekBar) bindings[5]);
        this.mDirtyFlags = -1L;
        this.aaa.setTag(null);
        this.bassSeekBar.setTag(null);
        this.linearLayout4.setTag(null);
        RadioButton radioButton = (RadioButton) bindings[10];
        this.mboundView10 = radioButton;
        radioButton.setTag(null);
        RadioButton radioButton2 = (RadioButton) bindings[11];
        this.mboundView11 = radioButton2;
        radioButton2.setTag(null);
        RadioButton radioButton3 = (RadioButton) bindings[12];
        this.mboundView12 = radioButton3;
        radioButton3.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[4];
        this.mboundView4 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[6];
        this.mboundView6 = textView3;
        textView3.setTag(null);
        RadioButton radioButton4 = (RadioButton) bindings[8];
        this.mboundView8 = radioButton4;
        radioButton4.setTag(null);
        RadioButton radioButton5 = (RadioButton) bindings[9];
        this.mboundView9 = radioButton5;
        radioButton5.setTag(null);
        this.mezzoSeekBar.setTag(null);
        this.trebleSeekBar.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 256L;
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
            setVm((EQViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.AudiEqViewBinding
    public void setVm(EQViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        notifyPropertyChanged(26);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
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

    private boolean onChangeVmMezzoStr(ObservableField<String> VmMezzoStr, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmBassStr(ObservableField<String> VmBassStr, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmTrebleStr(ObservableField<String> VmTrebleStr, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmBassProgress(ObservableInt VmBassProgress, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmMezzoProgress(ObservableInt VmMezzoProgress, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmTrebleProgress(ObservableInt VmTrebleProgress, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmEqModel(ObservableInt VmEqModel, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        String vmBassStrGet;
        boolean vmEqModelInt2;
        String vmMezzoStrGet;
        String vmTrebleStrGet;
        boolean vmEqModelInt0;
        boolean vmEqModelInt4;
        int vmTrebleProgressGet;
        ObservableInt vmEqModel;
        int vmEqModelGet;
        ObservableInt vmTrebleProgress;
        ObservableInt vmMezzoProgress;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
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
        if ((dirtyFlags & 511) == 0) {
            vmBassStrGet = null;
            vmEqModelInt2 = false;
            vmMezzoStrGet = null;
            vmTrebleStrGet = null;
            vmEqModelInt0 = false;
            vmEqModelInt4 = false;
        } else {
            if ((dirtyFlags & 385) != 0) {
                if (vm != null) {
                    vmMezzoStr = vm.mezzoStr;
                }
                updateRegistration(0, vmMezzoStr);
                if (vmMezzoStr != null) {
                    vmMezzoStrGet2 = vmMezzoStr.get();
                }
            }
            if ((dirtyFlags & 386) != 0) {
                if (vm != null) {
                    vmBassStr = vm.bassStr;
                }
                updateRegistration(1, vmBassStr);
                if (vmBassStr != null) {
                    vmBassStrGet2 = vmBassStr.get();
                }
            }
            if ((dirtyFlags & 388) != 0) {
                if (vm != null) {
                    vmTrebleStr = vm.trebleStr;
                }
                updateRegistration(2, vmTrebleStr);
                if (vmTrebleStr != null) {
                    vmTrebleStrGet2 = vmTrebleStr.get();
                }
            }
            if ((dirtyFlags & 392) != 0) {
                if (vm != null) {
                    vmBassProgress = vm.bassProgress;
                }
                updateRegistration(3, vmBassProgress);
                if (vmBassProgress != null) {
                    vmBassProgressGet = vmBassProgress.get();
                }
            }
            if ((dirtyFlags & 400) != 0) {
                if (vm == null) {
                    vmMezzoProgress = null;
                } else {
                    vmMezzoProgress = vm.mezzoProgress;
                }
                updateRegistration(4, vmMezzoProgress);
                if (vmMezzoProgress != null) {
                    vmMezzoProgressGet = vmMezzoProgress.get();
                }
            }
            if ((dirtyFlags & 416) != 0) {
                if (vm == null) {
                    vmTrebleProgress = null;
                } else {
                    vmTrebleProgress = vm.trebleProgress;
                }
                updateRegistration(5, vmTrebleProgress);
                if (vmTrebleProgress != null) {
                    vmTrebleProgressGet2 = vmTrebleProgress.get();
                }
            }
            if ((dirtyFlags & 448) == 0) {
                vmBassStrGet = vmBassStrGet2;
                vmEqModelInt2 = false;
                vmMezzoStrGet = vmMezzoStrGet2;
                vmTrebleStrGet = vmTrebleStrGet2;
                vmEqModelInt0 = false;
                vmEqModelInt4 = false;
            } else {
                if (vm == null) {
                    vmEqModel = null;
                } else {
                    vmEqModel = vm.eqModel;
                }
                updateRegistration(6, vmEqModel);
                if (vmEqModel == null) {
                    vmEqModelGet = 0;
                } else {
                    int vmEqModelGet2 = vmEqModel.get();
                    vmEqModelGet = vmEqModelGet2;
                }
                vmEqModelInt1 = vmEqModelGet == 1;
                vmEqModelInt3 = vmEqModelGet == 3;
                vmEqModelInt5 = vmEqModelGet == 5;
                boolean vmEqModelInt22 = vmEqModelGet == 2;
                boolean vmEqModelInt02 = vmEqModelGet == 0;
                boolean vmEqModelInt42 = vmEqModelGet == 4;
                vmBassStrGet = vmBassStrGet2;
                vmEqModelInt2 = vmEqModelInt22;
                vmMezzoStrGet = vmMezzoStrGet2;
                vmTrebleStrGet = vmTrebleStrGet2;
                vmEqModelInt0 = vmEqModelInt02;
                vmEqModelInt4 = vmEqModelInt42;
            }
        }
        if ((dirtyFlags & 448) == 0) {
            vmTrebleProgressGet = vmTrebleProgressGet2;
        } else {
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
        }
        if ((dirtyFlags & 256) != 0) {
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
