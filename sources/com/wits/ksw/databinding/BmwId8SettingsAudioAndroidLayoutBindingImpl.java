package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.base.BaseBindingModel;
import com.wits.ksw.settings.bmw_id8.vm.BmwId8SettingsViewModel;

public class BmwId8SettingsAudioAndroidLayoutBindingImpl extends BmwId8SettingsAudioAndroidLayoutBinding {
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
        sparseIntArray.put(R.id.bmw_id8_settings_audio_android_lay, 10);
    }

    public BmwId8SettingsAudioAndroidLayoutBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }

    private BmwId8SettingsAudioAndroidLayoutBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4, bindings[9], bindings[8], bindings[7], bindings[10], bindings[5], bindings[4], bindings[3]);
        this.mDirtyFlags = -1;
        this.bmwId8SettingsAndroidCallAddBtn.setTag((Object) null);
        this.bmwId8SettingsAndroidCallSeekbar.setTag((Object) null);
        this.bmwId8SettingsAndroidCallSubBtn.setTag((Object) null);
        this.bmwId8SettingsMeidaAddBtn.setTag((Object) null);
        this.bmwId8SettingsMeidaSeekbar.setTag((Object) null);
        this.bmwId8SettingsMeidaSubBtn.setTag((Object) null);
        RelativeLayout relativeLayout = bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
        ImageView imageView = bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag((Object) null);
        TextView textView = bindings[2];
        this.mboundView2 = textView;
        textView.setTag((Object) null);
        TextView textView2 = bindings[6];
        this.mboundView6 = textView2;
        textView2.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32;
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
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(25);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelAndroidCallVolume((ObservableInt) object, fieldId);
            case 1:
                return onChangeViewModelAndroidMediaVolume((ObservableInt) object, fieldId);
            case 2:
                return onChangeViewModelAndroidOemVolumeMax((ObservableInt) object, fieldId);
            case 3:
                return onChangeViewModelAudioBgShow((ObservableBoolean) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelAndroidCallVolume(ObservableInt ViewModelAndroidCallVolume, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelAndroidMediaVolume(ObservableInt ViewModelAndroidMediaVolume, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModelAndroidOemVolumeMax(ObservableInt ViewModelAndroidOemVolumeMax, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeViewModelAudioBgShow(ObservableBoolean ViewModelAudioBgShow, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        ObservableBoolean viewModelAudioBgShow;
        ObservableInt viewModelAndroidOemVolumeMax;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String stringValueOfViewModelAndroidMediaVolume = null;
        int viewModelAndroidOemVolumeMaxGet = 0;
        ObservableInt viewModelAndroidCallVolume = null;
        boolean viewModelAudioBgShowGet = false;
        int viewModelAudioBgShowViewGONEViewVISIBLE = 0;
        String stringValueOfViewModelAndroidCallVolume = null;
        int viewModelAndroidMediaVolumeGet = 0;
        View.OnClickListener viewModelOnClickAndroidViewViewOnClickListener = null;
        int viewModelAndroidCallVolumeGet = 0;
        ObservableInt viewModelAndroidMediaVolume = null;
        BmwId8SettingsViewModel viewModel = this.mViewModel;
        if ((dirtyFlags & 63) != 0) {
            if ((dirtyFlags & 49) != 0) {
                if (viewModel != null) {
                    viewModelAndroidCallVolume = viewModel.androidCallVolume;
                }
                updateRegistration(0, (Observable) viewModelAndroidCallVolume);
                if (viewModelAndroidCallVolume != null) {
                    viewModelAndroidCallVolumeGet = viewModelAndroidCallVolume.get();
                }
                stringValueOfViewModelAndroidCallVolume = String.valueOf(viewModelAndroidCallVolumeGet);
            }
            if (!((dirtyFlags & 48) == 0 || viewModel == null)) {
                OnClickListenerImpl onClickListenerImpl = this.mViewModelOnClickAndroidViewViewOnClickListener;
                if (onClickListenerImpl == null) {
                    onClickListenerImpl = new OnClickListenerImpl();
                    this.mViewModelOnClickAndroidViewViewOnClickListener = onClickListenerImpl;
                }
                viewModelOnClickAndroidViewViewOnClickListener = onClickListenerImpl.setValue(viewModel);
            }
            if ((dirtyFlags & 50) != 0) {
                if (viewModel != null) {
                    viewModelAndroidMediaVolume = viewModel.androidMediaVolume;
                }
                updateRegistration(1, (Observable) viewModelAndroidMediaVolume);
                if (viewModelAndroidMediaVolume != null) {
                    viewModelAndroidMediaVolumeGet = viewModelAndroidMediaVolume.get();
                }
                stringValueOfViewModelAndroidMediaVolume = String.valueOf(viewModelAndroidMediaVolumeGet);
            }
            if ((dirtyFlags & 52) != 0) {
                if (viewModel != null) {
                    viewModelAndroidOemVolumeMax = viewModel.androidOemVolumeMax;
                } else {
                    viewModelAndroidOemVolumeMax = null;
                }
                updateRegistration(2, (Observable) viewModelAndroidOemVolumeMax);
                if (viewModelAndroidOemVolumeMax != null) {
                    viewModelAndroidOemVolumeMaxGet = viewModelAndroidOemVolumeMax.get();
                    ObservableInt observableInt = viewModelAndroidOemVolumeMax;
                } else {
                    ObservableInt observableInt2 = viewModelAndroidOemVolumeMax;
                }
            }
            if ((dirtyFlags & 56) != 0) {
                if (viewModel != null) {
                    viewModelAudioBgShow = viewModel.audioBgShow;
                } else {
                    viewModelAudioBgShow = null;
                }
                updateRegistration(3, (Observable) viewModelAudioBgShow);
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
                ObservableBoolean observableBoolean = viewModelAudioBgShow;
            }
        }
        if ((dirtyFlags & 48) != 0) {
            this.bmwId8SettingsAndroidCallAddBtn.setOnClickListener(viewModelOnClickAndroidViewViewOnClickListener);
            this.bmwId8SettingsAndroidCallSubBtn.setOnClickListener(viewModelOnClickAndroidViewViewOnClickListener);
            this.bmwId8SettingsMeidaAddBtn.setOnClickListener(viewModelOnClickAndroidViewViewOnClickListener);
            this.bmwId8SettingsMeidaSubBtn.setOnClickListener(viewModelOnClickAndroidViewViewOnClickListener);
        }
        if ((dirtyFlags & 52) != 0) {
            BaseBindingModel.setID8ProgressBarMax(this.bmwId8SettingsAndroidCallSeekbar, viewModelAndroidOemVolumeMaxGet);
            BaseBindingModel.setID8ProgressBarMax(this.bmwId8SettingsMeidaSeekbar, viewModelAndroidOemVolumeMaxGet);
        }
        if ((dirtyFlags & 49) != 0) {
            BaseBindingModel.setID8ProgressBarValue(this.bmwId8SettingsAndroidCallSeekbar, viewModelAndroidCallVolumeGet);
            TextViewBindingAdapter.setText(this.mboundView6, stringValueOfViewModelAndroidCallVolume);
        }
        if ((dirtyFlags & 50) != 0) {
            BaseBindingModel.setID8ProgressBarValue(this.bmwId8SettingsMeidaSeekbar, viewModelAndroidMediaVolumeGet);
            TextViewBindingAdapter.setText(this.mboundView2, stringValueOfViewModelAndroidMediaVolume);
        }
        if ((dirtyFlags & 56) != 0) {
            this.mboundView1.setVisibility(viewModelAudioBgShowViewGONEViewVISIBLE);
        }
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private BmwId8SettingsViewModel value;

        public OnClickListenerImpl setValue(BmwId8SettingsViewModel value2) {
            this.value = value2;
            if (value2 == null) {
                return null;
            }
            return this;
        }

        public void onClick(View arg0) {
            this.value.onClick(arg0);
        }
    }
}
