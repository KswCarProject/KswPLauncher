package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.audi.p007vm.AudiSettingViewModel;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;

/* loaded from: classes7.dex */
public class AudiSysinfoBindingImpl extends AudiSysinfoBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ScrollView mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.audiSysInfParentPanel, 6);
        sparseIntArray.put(C0899R.C0901id.audioSysInfoMcuUpdata, 7);
        sparseIntArray.put(C0899R.C0901id.audioSysInfoRestoreFactory, 8);
        sparseIntArray.put(C0899R.C0901id.audioSysInfoUpDateFactory, 9);
    }

    public AudiSysinfoBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private AudiSysinfoBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 5, (AudiConstraintLayout) bindings[6], (TextView) bindings[2], (TextView) bindings[1], (TextView) bindings[3], (TextView) bindings[4], (TextView) bindings[7], (TextView) bindings[5], (TextView) bindings[8], (TextView) bindings[9]);
        this.mDirtyFlags = -1L;
        this.audiSysInfoAppVer.setTag(null);
        this.audiSysInfoMcuVer.setTag(null);
        this.audiSysInfoSysVer.setTag(null);
        this.audioSysInfoFlash.setTag(null);
        this.audioSysInfoRam.setTag(null);
        ScrollView scrollView = (ScrollView) bindings[0];
        this.mboundView0 = scrollView;
        scrollView.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 64L;
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
            setVm((AudiSettingViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.AudiSysinfoBinding
    public void setVm(AudiSettingViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(26);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVmAppVer((ObservableField) object, fieldId);
            case 1:
                return onChangeVmNandflash((ObservableField) object, fieldId);
            case 2:
                return onChangeVmMcuVer((ObservableField) object, fieldId);
            case 3:
                return onChangeVmSystemVersion((ObservableField) object, fieldId);
            case 4:
                return onChangeVmRamVer((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmAppVer(ObservableField<String> VmAppVer, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmNandflash(ObservableField<String> VmNandflash, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmMcuVer(ObservableField<String> VmMcuVer, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmSystemVersion(ObservableField<String> VmSystemVersion, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmRamVer(ObservableField<String> VmRamVer, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
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
        ObservableField<String> vmAppVer = null;
        ObservableField<String> vmNandflash = null;
        String vmSystemVersionGet = null;
        String vmNandflashGet = null;
        ObservableField<String> vmMcuVer = null;
        ObservableField<String> vmSystemVersion = null;
        AudiSettingViewModel vm = this.mVm;
        ObservableField<String> vmRamVer = null;
        String vmMcuVerGet = null;
        String vmRamVerGet = null;
        String vmAppVerGet = null;
        if ((dirtyFlags & 127) != 0) {
            if ((dirtyFlags & 97) != 0) {
                if (vm != null) {
                    vmAppVer = vm.appVer;
                }
                updateRegistration(0, vmAppVer);
                if (vmAppVer != null) {
                    vmAppVerGet = vmAppVer.get();
                }
            }
            if ((dirtyFlags & 98) != 0) {
                if (vm != null) {
                    vmNandflash = vm.nandflash;
                }
                updateRegistration(1, vmNandflash);
                if (vmNandflash != null) {
                    vmNandflashGet = vmNandflash.get();
                }
            }
            if ((dirtyFlags & 100) != 0) {
                if (vm != null) {
                    vmMcuVer = vm.mcuVer;
                }
                updateRegistration(2, vmMcuVer);
                if (vmMcuVer != null) {
                    vmMcuVerGet = vmMcuVer.get();
                }
            }
            if ((dirtyFlags & 104) != 0) {
                if (vm != null) {
                    vmSystemVersion = vm.systemVersion;
                }
                updateRegistration(3, vmSystemVersion);
                if (vmSystemVersion != null) {
                    vmSystemVersionGet = vmSystemVersion.get();
                }
            }
            if ((dirtyFlags & 112) != 0) {
                if (vm != null) {
                    vmRamVer = vm.ramVer;
                }
                updateRegistration(4, vmRamVer);
                if (vmRamVer != null) {
                    vmRamVerGet = vmRamVer.get();
                }
            }
        }
        if ((dirtyFlags & 97) != 0) {
            TextViewBindingAdapter.setText(this.audiSysInfoAppVer, vmAppVerGet);
        }
        if ((dirtyFlags & 100) != 0) {
            TextViewBindingAdapter.setText(this.audiSysInfoMcuVer, vmMcuVerGet);
        }
        if ((dirtyFlags & 104) != 0) {
            TextViewBindingAdapter.setText(this.audiSysInfoSysVer, vmSystemVersionGet);
        }
        if ((dirtyFlags & 98) != 0) {
            TextViewBindingAdapter.setText(this.audioSysInfoFlash, vmNandflashGet);
        }
        if ((dirtyFlags & 112) != 0) {
            TextViewBindingAdapter.setText(this.audioSysInfoRam, vmRamVerGet);
        }
    }
}
