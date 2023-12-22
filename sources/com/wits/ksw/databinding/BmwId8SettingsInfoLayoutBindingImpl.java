package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.view.MarqueeTextView;
import com.wits.ksw.settings.bmw_id8.p009vm.BmwId8SettingsViewModel;

/* loaded from: classes7.dex */
public class BmwId8SettingsInfoLayoutBindingImpl extends BmwId8SettingsInfoLayoutBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_info_back, 6);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_info_lay, 7);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_info_system_version, 8);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_info_mcu_upgrade, 9);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_info_system_recovery, 10);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_info_system_update, 11);
    }

    public BmwId8SettingsInfoLayoutBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }

    private BmwId8SettingsInfoLayoutBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 5, (MarqueeTextView) bindings[2], (RelativeLayout) bindings[9], (MarqueeTextView) bindings[1], (MarqueeTextView) bindings[5], (MarqueeTextView) bindings[4], (MarqueeTextView) bindings[3], (RelativeLayout) bindings[10], (RelativeLayout) bindings[11], (RelativeLayout) bindings[8], (ImageView) bindings[6], (ScrollView) bindings[7]);
        this.mDirtyFlags = -1L;
        this.bmwId8InfoAppVersionContent.setTag(null);
        this.bmwId8InfoMcuVersionContent.setTag(null);
        this.bmwId8InfoRamContent.setTag(null);
        this.bmwId8InfoStorageContent.setTag(null);
        this.bmwId8InfoSysVersionContent.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
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
        if (25 == variableId) {
            setViewModel((BmwId8SettingsViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.BmwId8SettingsInfoLayoutBinding
    public void setViewModel(BmwId8SettingsViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(25);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
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

    private boolean onChangeViewModelAppVersionStr(ObservableField<String> ViewModelAppVersionStr, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelMcuVersionStr(ObservableField<String> ViewModelMcuVersionStr, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelRamStr(ObservableField<String> ViewModelRamStr, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelStorageStr(ObservableField<String> ViewModelStorageStr, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelSystemVersionStr(ObservableField<String> ViewModelSystemVersionStr, int fieldId) {
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
                updateRegistration(0, viewModelAppVersionStr);
                if (viewModelAppVersionStr != null) {
                    viewModelAppVersionStrGet = viewModelAppVersionStr.get();
                }
            }
            if ((dirtyFlags & 98) != 0) {
                if (viewModel != null) {
                    viewModelMcuVersionStr = viewModel.mcuVersionStr;
                }
                updateRegistration(1, viewModelMcuVersionStr);
                if (viewModelMcuVersionStr != null) {
                    viewModelMcuVersionStrGet = viewModelMcuVersionStr.get();
                }
            }
            if ((dirtyFlags & 100) != 0) {
                if (viewModel != null) {
                    viewModelRamStr = viewModel.ramStr;
                }
                updateRegistration(2, viewModelRamStr);
                if (viewModelRamStr != null) {
                    viewModelRamStrGet = viewModelRamStr.get();
                }
            }
            if ((dirtyFlags & 104) != 0) {
                if (viewModel != null) {
                    viewModelStorageStr = viewModel.storageStr;
                }
                updateRegistration(3, viewModelStorageStr);
                if (viewModelStorageStr != null) {
                    viewModelStorageStrGet = viewModelStorageStr.get();
                }
            }
            if ((dirtyFlags & 112) != 0) {
                if (viewModel != null) {
                    viewModelSystemVersionStr = viewModel.systemVersionStr;
                }
                updateRegistration(4, viewModelSystemVersionStr);
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
