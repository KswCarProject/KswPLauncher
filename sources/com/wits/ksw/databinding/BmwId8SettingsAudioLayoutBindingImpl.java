package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.bmw_id8.p009vm.BmwId8SettingsViewModel;

/* loaded from: classes7.dex */
public class BmwId8SettingsAudioLayoutBindingImpl extends BmwId8SettingsAudioLayoutBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final ImageView mboundView2;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_audio_lay, 5);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_audio_android_item, 6);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_audio_oem_item, 7);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_audio_sound_item, 8);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_audio_framelay, 9);
    }

    public BmwId8SettingsAudioLayoutBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private BmwId8SettingsAudioLayoutBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (RelativeLayout) bindings[6], (FrameLayout) bindings[9], (ImageView) bindings[4], (RelativeLayout) bindings[5], (ImageView) bindings[1], (RelativeLayout) bindings[7], (ImageView) bindings[3], (RelativeLayout) bindings[8]);
        this.mDirtyFlags = -1L;
        this.bmwId8SettingsAudioImg.setTag(null);
        this.bmwId8SettingsAudioLeftArrow.setTag(null);
        this.bmwId8SettingsAudioRightArrow.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[2];
        this.mboundView2 = imageView;
        imageView.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8L;
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

    @Override // com.wits.ksw.databinding.BmwId8SettingsAudioLayoutBinding
    public void setViewModel(BmwId8SettingsViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(25);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelAudioIconShow((ObservableBoolean) object, fieldId);
            case 1:
                return onChangeViewModelAudioBgShow((ObservableBoolean) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelAudioIconShow(ObservableBoolean ViewModelAudioIconShow, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelAudioBgShow(ObservableBoolean ViewModelAudioBgShow, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
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
        ObservableBoolean viewModelAudioIconShow = null;
        boolean viewModelAudioBgShowGet = false;
        int viewModelAudioBgShowViewGONEViewVISIBLE = 0;
        BmwId8SettingsViewModel viewModel = this.mViewModel;
        int viewModelAudioBgShowViewVISIBLEViewGONE = 0;
        boolean viewModelAudioIconShowGet = false;
        ObservableBoolean viewModelAudioBgShow = null;
        int viewModelAudioIconShowViewVISIBLEViewGONE = 0;
        if ((15 & dirtyFlags) != 0) {
            if ((dirtyFlags & 13) != 0) {
                if (viewModel != null) {
                    viewModelAudioIconShow = viewModel.audioIconShow;
                }
                updateRegistration(0, viewModelAudioIconShow);
                if (viewModelAudioIconShow != null) {
                    viewModelAudioIconShowGet = viewModelAudioIconShow.get();
                }
                if ((dirtyFlags & 13) != 0) {
                    if (viewModelAudioIconShowGet) {
                        dirtyFlags |= 512;
                    } else {
                        dirtyFlags |= 256;
                    }
                }
                viewModelAudioIconShowViewVISIBLEViewGONE = viewModelAudioIconShowGet ? 0 : 8;
            }
            if ((dirtyFlags & 14) != 0) {
                if (viewModel != null) {
                    viewModelAudioBgShow = viewModel.audioBgShow;
                }
                updateRegistration(1, viewModelAudioBgShow);
                if (viewModelAudioBgShow != null) {
                    viewModelAudioBgShowGet = viewModelAudioBgShow.get();
                }
                if ((dirtyFlags & 14) != 0) {
                    if (viewModelAudioBgShowGet) {
                        dirtyFlags = dirtyFlags | 32 | 128;
                    } else {
                        dirtyFlags = dirtyFlags | 16 | 64;
                    }
                }
                viewModelAudioBgShowViewGONEViewVISIBLE = viewModelAudioBgShowGet ? 8 : 0;
                viewModelAudioBgShowViewVISIBLEViewGONE = viewModelAudioBgShowGet ? 0 : 8;
            }
        }
        if ((dirtyFlags & 13) != 0) {
            this.bmwId8SettingsAudioImg.setVisibility(viewModelAudioIconShowViewVISIBLEViewGONE);
        }
        if ((14 & dirtyFlags) != 0) {
            this.bmwId8SettingsAudioLeftArrow.setVisibility(viewModelAudioBgShowViewVISIBLEViewGONE);
            this.bmwId8SettingsAudioRightArrow.setVisibility(viewModelAudioBgShowViewGONEViewVISIBLE);
            this.mboundView2.setVisibility(viewModelAudioBgShowViewVISIBLEViewGONE);
        }
    }
}
