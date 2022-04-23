package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi_mib3.vm.AudiMib3SettingViewModel;

public class AudiMib3SysinfoBindingImpl extends AudiMib3SysinfoBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.title, 6);
        sparseIntArray.put(R.id.title_divider, 7);
        sparseIntArray.put(R.id.sv_sysinfo, 8);
        sparseIntArray.put(R.id.audiSysInfParentPanel, 9);
        sparseIntArray.put(R.id.audioSysInfoMcuUpdata, 10);
        sparseIntArray.put(R.id.audioSysInfoRestoreFactory, 11);
        sparseIntArray.put(R.id.v_divider, 12);
    }

    public AudiMib3SysinfoBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }

    private AudiMib3SysinfoBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 5, bindings[9], bindings[2], bindings[1], bindings[3], bindings[4], bindings[10], bindings[5], bindings[11], bindings[8], bindings[6], bindings[7], bindings[12]);
        this.mDirtyFlags = -1;
        this.audiSysInfoAppVer.setTag((Object) null);
        this.audiSysInfoMcuVer.setTag((Object) null);
        this.audiSysInfoSysVer.setTag((Object) null);
        this.audioSysInfoFlash.setTag((Object) null);
        this.audioSysInfoRam.setTag((Object) null);
        RelativeLayout relativeLayout = bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 64;
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
        setVm((AudiMib3SettingViewModel) variable);
        return true;
    }

    public void setVm(AudiMib3SettingViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(17);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
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

    private boolean onChangeVmAppVer(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeVmNandflash(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeVmMcuVer(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeVmSystemVersion(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeVmRamVer(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
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
        ObservableField<String> vmAppVer = null;
        ObservableField<String> vmNandflash = null;
        String vmSystemVersionGet = null;
        String vmNandflashGet = null;
        ObservableField<String> vmMcuVer = null;
        ObservableField<String> vmSystemVersion = null;
        AudiMib3SettingViewModel vm = this.mVm;
        ObservableField<String> vmRamVer = null;
        String vmMcuVerGet = null;
        String vmRamVerGet = null;
        String vmAppVerGet = null;
        if ((dirtyFlags & 127) != 0) {
            if ((dirtyFlags & 97) != 0) {
                if (vm != null) {
                    vmAppVer = vm.appVer;
                }
                updateRegistration(0, (Observable) vmAppVer);
                if (vmAppVer != null) {
                    vmAppVerGet = vmAppVer.get();
                }
            }
            if ((dirtyFlags & 98) != 0) {
                if (vm != null) {
                    vmNandflash = vm.nandflash;
                }
                updateRegistration(1, (Observable) vmNandflash);
                if (vmNandflash != null) {
                    vmNandflashGet = vmNandflash.get();
                }
            }
            if ((dirtyFlags & 100) != 0) {
                if (vm != null) {
                    vmMcuVer = vm.mcuVer;
                }
                updateRegistration(2, (Observable) vmMcuVer);
                if (vmMcuVer != null) {
                    vmMcuVerGet = vmMcuVer.get();
                }
            }
            if ((dirtyFlags & 104) != 0) {
                if (vm != null) {
                    vmSystemVersion = vm.systemVersion;
                }
                updateRegistration(3, (Observable) vmSystemVersion);
                if (vmSystemVersion != null) {
                    vmSystemVersionGet = vmSystemVersion.get();
                }
            }
            if ((dirtyFlags & 112) != 0) {
                if (vm != null) {
                    vmRamVer = vm.ramVer;
                }
                updateRegistration(4, (Observable) vmRamVer);
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
