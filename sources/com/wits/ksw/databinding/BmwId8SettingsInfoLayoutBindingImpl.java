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
import com.wits.ksw.settings.bmw_id8.vm.BmwId8SettingsViewModel;

public class BmwId8SettingsInfoLayoutBindingImpl extends BmwId8SettingsInfoLayoutBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.bmw_id8_settings_info_back, 6);
        sparseIntArray.put(R.id.bmw_id8_settings_info_lay, 7);
        sparseIntArray.put(R.id.bmw_id8_info_system_version, 8);
        sparseIntArray.put(R.id.bmw_id8_info_mcu_upgrade, 9);
        sparseIntArray.put(R.id.bmw_id8_info_system_recovery, 10);
        sparseIntArray.put(R.id.bmw_id8_info_system_update, 11);
    }

    public BmwId8SettingsInfoLayoutBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private BmwId8SettingsInfoLayoutBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 5, bindings[2], bindings[9], bindings[1], bindings[5], bindings[4], bindings[3], bindings[10], bindings[11], bindings[8], bindings[6], bindings[7]);
        this.mDirtyFlags = -1;
        this.bmwId8InfoAppVersionContent.setTag((Object) null);
        this.bmwId8InfoMcuVersionContent.setTag((Object) null);
        this.bmwId8InfoRamContent.setTag((Object) null);
        this.bmwId8InfoStorageContent.setTag((Object) null);
        this.bmwId8InfoSysVersionContent.setTag((Object) null);
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
        if (25 != variableId) {
            return false;
        }
        setViewModel((BmwId8SettingsViewModel) variable);
        return true;
    }

    public void setViewModel(BmwId8SettingsViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(25);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelAppVersionStr((ObservableField) object, fieldId);
            case 1:
                return onChangeViewModelMcuVersionStr((ObservableField) object, fieldId);
            case 2:
                return onChangeViewModelRamStr((ObservableField) object, fieldId);
            case 3:
                return onChangeViewModelStorageStr((ObservableField) object, fieldId);
            case 4:
                return onChangeViewModelSystemVersionStr((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelAppVersionStr(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelMcuVersionStr(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModelRamStr(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeViewModelStorageStr(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeViewModelSystemVersionStr(ObservableField<String> observableField, int fieldId) {
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
        String viewModelAppVersionStrGet = null;
        ObservableField<String> viewModelAppVersionStr = null;
        String viewModelSystemVersionStrGet = null;
        ObservableField<String> viewModelMcuVersionStr = null;
        ObservableField<String> viewModelRamStr = null;
        ObservableField<String> viewModelStorageStr = null;
        String viewModelMcuVersionStrGet = null;
        String viewModelStorageStrGet = null;
        ObservableField<String> viewModelSystemVersionStr = null;
        BmwId8SettingsViewModel viewModel = this.mViewModel;
        String viewModelRamStrGet = null;
        if ((dirtyFlags & 127) != 0) {
            if ((dirtyFlags & 97) != 0) {
                if (viewModel != null) {
                    viewModelAppVersionStr = viewModel.appVersionStr;
                }
                updateRegistration(0, (Observable) viewModelAppVersionStr);
                if (viewModelAppVersionStr != null) {
                    viewModelAppVersionStrGet = viewModelAppVersionStr.get();
                }
            }
            if ((dirtyFlags & 98) != 0) {
                if (viewModel != null) {
                    viewModelMcuVersionStr = viewModel.mcuVersionStr;
                }
                updateRegistration(1, (Observable) viewModelMcuVersionStr);
                if (viewModelMcuVersionStr != null) {
                    viewModelMcuVersionStrGet = viewModelMcuVersionStr.get();
                }
            }
            if ((dirtyFlags & 100) != 0) {
                if (viewModel != null) {
                    viewModelRamStr = viewModel.ramStr;
                }
                updateRegistration(2, (Observable) viewModelRamStr);
                if (viewModelRamStr != null) {
                    viewModelRamStrGet = viewModelRamStr.get();
                }
            }
            if ((dirtyFlags & 104) != 0) {
                if (viewModel != null) {
                    viewModelStorageStr = viewModel.storageStr;
                }
                updateRegistration(3, (Observable) viewModelStorageStr);
                if (viewModelStorageStr != null) {
                    viewModelStorageStrGet = viewModelStorageStr.get();
                }
            }
            if ((dirtyFlags & 112) != 0) {
                if (viewModel != null) {
                    viewModelSystemVersionStr = viewModel.systemVersionStr;
                }
                updateRegistration(4, (Observable) viewModelSystemVersionStr);
                if (viewModelSystemVersionStr != null) {
                    viewModelSystemVersionStrGet = viewModelSystemVersionStr.get();
                }
            }
        }
        if ((dirtyFlags & 97) != 0) {
            TextViewBindingAdapter.setText(this.bmwId8InfoAppVersionContent, viewModelAppVersionStrGet);
        }
        if ((dirtyFlags & 98) != 0) {
            TextViewBindingAdapter.setText(this.bmwId8InfoMcuVersionContent, viewModelMcuVersionStrGet);
        }
        if ((dirtyFlags & 100) != 0) {
            TextViewBindingAdapter.setText(this.bmwId8InfoRamContent, viewModelRamStrGet);
        }
        if ((dirtyFlags & 104) != 0) {
            TextViewBindingAdapter.setText(this.bmwId8InfoStorageContent, viewModelStorageStrGet);
        }
        if ((dirtyFlags & 112) != 0) {
            TextViewBindingAdapter.setText(this.bmwId8InfoSysVersionContent, viewModelSystemVersionStrGet);
        }
    }
}
