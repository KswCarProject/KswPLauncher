package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.base.BaseBindingModel;
import com.wits.ksw.launcher.bmw_id8_ui.view.ID8ProgressBar;
import com.wits.ksw.settings.bmw_id8.p009vm.BmwId8SettingsViewModel;

/* loaded from: classes7.dex */
public class BmwId8SettingsAudioOemLayoutBindingImpl extends BmwId8SettingsAudioOemLayoutBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private OnClickListenerImpl mViewModelOnClickAndroidViewViewOnClickListener;
    private final RelativeLayout mboundView0;
    private final ImageView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView6;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_audio_oem_lay, 10);
    }

    public BmwId8SettingsAudioOemLayoutBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }

    private BmwId8SettingsAudioOemLayoutBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4, (RelativeLayout) bindings[10], (ImageButton) bindings[9], (ID8ProgressBar) bindings[8], (ImageButton) bindings[7], (ImageButton) bindings[5], (ID8ProgressBar) bindings[4], (ImageButton) bindings[3]);
        this.mDirtyFlags = -1L;
        this.bmwId8SettingsNaviAddBtn.setTag(null);
        this.bmwId8SettingsNaviSeekbar.setTag(null);
        this.bmwId8SettingsNaviSubBtn.setTag(null);
        this.bmwId8SettingsOemCallAddBtn.setTag(null);
        this.bmwId8SettingsOemCallSeekbar.setTag(null);
        this.bmwId8SettingsOemCallSubBtn.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[6];
        this.mboundView6 = textView2;
        textView2.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32L;
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

    @Override // com.wits.ksw.databinding.BmwId8SettingsAudioOemLayoutBinding
    public void setViewModel(BmwId8SettingsViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(25);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelOemCallVolume((ObservableInt) object, fieldId);
            case 1:
                return onChangeViewModelOemNaviVolume((ObservableInt) object, fieldId);
            case 2:
                return onChangeViewModelAndroidOemVolumeMax((ObservableInt) object, fieldId);
            case 3:
                return onChangeViewModelAudioBgShow((ObservableBoolean) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelOemCallVolume(ObservableInt ViewModelOemCallVolume, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelOemNaviVolume(ObservableInt ViewModelOemNaviVolume, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelAndroidOemVolumeMax(ObservableInt ViewModelAndroidOemVolumeMax, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelAudioBgShow(ObservableBoolean ViewModelAudioBgShow, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        ObservableBoolean viewModelAudioBgShow;
        ObservableInt viewModelAndroidOemVolumeMax;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        int viewModelAndroidOemVolumeMaxGet = 0;
        boolean viewModelAudioBgShowGet = false;
        int viewModelAudioBgShowViewGONEViewVISIBLE = 0;
        int viewModelOemNaviVolumeGet = 0;
        ObservableInt viewModelOemCallVolume = null;
        String stringValueOfViewModelOemNaviVolume = null;
        ObservableInt viewModelOemNaviVolume = null;
        String stringValueOfViewModelOemCallVolume = null;
        View.OnClickListener viewModelOnClickAndroidViewViewOnClickListener = null;
        int viewModelOemCallVolumeGet = 0;
        BmwId8SettingsViewModel viewModel = this.mViewModel;
        if ((dirtyFlags & 63) != 0) {
            if ((dirtyFlags & 49) != 0) {
                if (viewModel != null) {
                    viewModelOemCallVolume = viewModel.oemCallVolume;
                }
                updateRegistration(0, viewModelOemCallVolume);
                if (viewModelOemCallVolume != null) {
                    viewModelOemCallVolumeGet = viewModelOemCallVolume.get();
                }
                stringValueOfViewModelOemCallVolume = String.valueOf(viewModelOemCallVolumeGet);
            }
            if ((dirtyFlags & 50) != 0) {
                if (viewModel != null) {
                    viewModelOemNaviVolume = viewModel.oemNaviVolume;
                }
                updateRegistration(1, viewModelOemNaviVolume);
                if (viewModelOemNaviVolume != null) {
                    viewModelOemNaviVolumeGet = viewModelOemNaviVolume.get();
                }
                stringValueOfViewModelOemNaviVolume = String.valueOf(viewModelOemNaviVolumeGet);
            }
            if ((dirtyFlags & 48) != 0 && viewModel != null) {
                OnClickListenerImpl onClickListenerImpl = this.mViewModelOnClickAndroidViewViewOnClickListener;
                if (onClickListenerImpl == null) {
                    onClickListenerImpl = new OnClickListenerImpl();
                    this.mViewModelOnClickAndroidViewViewOnClickListener = onClickListenerImpl;
                }
                viewModelOnClickAndroidViewViewOnClickListener = onClickListenerImpl.setValue(viewModel);
            }
            if ((dirtyFlags & 52) != 0) {
                if (viewModel == null) {
                    viewModelAndroidOemVolumeMax = null;
                } else {
                    viewModelAndroidOemVolumeMax = viewModel.androidOemVolumeMax;
                }
                updateRegistration(2, viewModelAndroidOemVolumeMax);
                if (viewModelAndroidOemVolumeMax != null) {
                    viewModelAndroidOemVolumeMaxGet = viewModelAndroidOemVolumeMax.get();
                }
            }
            if ((dirtyFlags & 56) != 0) {
                if (viewModel == null) {
                    viewModelAudioBgShow = null;
                } else {
                    viewModelAudioBgShow = viewModel.audioBgShow;
                }
                updateRegistration(3, viewModelAudioBgShow);
                if (viewModelAudioBgShow != null) {
                    viewModelAudioBgShowGet = viewModelAudioBgShow.get();
                }
                if ((dirtyFlags & 56) != 0) {
                    if (viewModelAudioBgShowGet) {
                        dirtyFlags |= 128;
                    } else {
                        dirtyFlags |= 64;
                    }
                }
                viewModelAudioBgShowViewGONEViewVISIBLE = viewModelAudioBgShowGet ? 8 : 0;
            }
        }
        if ((dirtyFlags & 48) != 0) {
            this.bmwId8SettingsNaviAddBtn.setOnClickListener(viewModelOnClickAndroidViewViewOnClickListener);
            this.bmwId8SettingsNaviSubBtn.setOnClickListener(viewModelOnClickAndroidViewViewOnClickListener);
            this.bmwId8SettingsOemCallAddBtn.setOnClickListener(viewModelOnClickAndroidViewViewOnClickListener);
            this.bmwId8SettingsOemCallSubBtn.setOnClickListener(viewModelOnClickAndroidViewViewOnClickListener);
        }
        if ((dirtyFlags & 52) != 0) {
            BaseBindingModel.setID8ProgressBarMax(this.bmwId8SettingsNaviSeekbar, viewModelAndroidOemVolumeMaxGet);
            BaseBindingModel.setID8ProgressBarMax(this.bmwId8SettingsOemCallSeekbar, viewModelAndroidOemVolumeMaxGet);
        }
        if ((dirtyFlags & 50) != 0) {
            BaseBindingModel.setID8ProgressBarValue(this.bmwId8SettingsNaviSeekbar, viewModelOemNaviVolumeGet);
            TextViewBindingAdapter.setText(this.mboundView6, stringValueOfViewModelOemNaviVolume);
        }
        if ((dirtyFlags & 49) != 0) {
            BaseBindingModel.setID8ProgressBarValue(this.bmwId8SettingsOemCallSeekbar, viewModelOemCallVolumeGet);
            TextViewBindingAdapter.setText(this.mboundView2, stringValueOfViewModelOemCallVolume);
        }
        if ((dirtyFlags & 56) != 0) {
            this.mboundView1.setVisibility(viewModelAudioBgShowViewGONEViewVISIBLE);
        }
    }

    /* loaded from: classes7.dex */
    public static class OnClickListenerImpl implements View.OnClickListener {
        private BmwId8SettingsViewModel value;

        public OnClickListenerImpl setValue(BmwId8SettingsViewModel value) {
            this.value = value;
            if (value == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View arg0) {
            this.value.onClick(arg0);
        }
    }
}
