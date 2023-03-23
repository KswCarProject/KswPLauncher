package com.wits.ksw.databinding;

import android.content.Context;
import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.settings.bmw_id8.vm.BmwId8SettingsViewModel;

public class BmwId8SettingsSystemCameraLayoutBindingImpl extends BmwId8SettingsSystemCameraLayoutBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private OnClickListenerImpl mViewModelOnClickAndroidViewViewOnClickListener;
    private final RelativeLayout mboundView0;
    private final ImageView mboundView1;
    private final ImageView mboundView3;
    private final ImageView mboundView5;
    private final ImageView mboundView7;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.bmw_id8_settings_system_camera_lay, 8);
    }

    public BmwId8SettingsSystemCameraLayoutBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    private BmwId8SettingsSystemCameraLayoutBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, bindings[6], bindings[2], bindings[8], bindings[4]);
        this.mDirtyFlags = -1;
        this.bmwId8SettingsSystemCamera360.setTag((Object) null);
        this.bmwId8SettingsSystemCameraAfter.setTag((Object) null);
        this.bmwId8SettingsSystemCameraOem.setTag((Object) null);
        RelativeLayout relativeLayout = bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
        ImageView imageView = bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag((Object) null);
        ImageView imageView2 = bindings[3];
        this.mboundView3 = imageView2;
        imageView2.setTag((Object) null);
        ImageView imageView3 = bindings[5];
        this.mboundView5 = imageView3;
        imageView3.setTag((Object) null);
        ImageView imageView4 = bindings[7];
        this.mboundView7 = imageView4;
        imageView4.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8;
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
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(25);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelSystemBgShow((ObservableBoolean) object, fieldId);
            case 1:
                return onChangeViewModelRearCameraType((ObservableInt) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelSystemBgShow(ObservableBoolean ViewModelSystemBgShow, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelRearCameraType(ObservableInt ViewModelRearCameraType, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        ObservableInt viewModelRearCameraType;
        long dirtyFlags2;
        int i;
        Context context;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        Drawable viewModelRearCameraTypeInt2MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN = null;
        boolean viewModelSystemBgShowGet = false;
        Drawable viewModelRearCameraTypeInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN = null;
        int viewModelSystemBgShowViewGONEViewVISIBLE = 0;
        int viewModelRearCameraTypeGet = 0;
        View.OnClickListener viewModelOnClickAndroidViewViewOnClickListener = null;
        ObservableBoolean viewModelSystemBgShow = null;
        Drawable viewModelRearCameraTypeInt0MboundView3AndroidDrawableId8SettingsSystemSelectSelMboundView3AndroidDrawableId8SettingsSystemSelectN = null;
        BmwId8SettingsViewModel viewModel = this.mViewModel;
        if ((dirtyFlags & 15) != 0) {
            if (!((dirtyFlags & 12) == 0 || viewModel == null)) {
                OnClickListenerImpl onClickListenerImpl = this.mViewModelOnClickAndroidViewViewOnClickListener;
                if (onClickListenerImpl == null) {
                    onClickListenerImpl = new OnClickListenerImpl();
                    this.mViewModelOnClickAndroidViewViewOnClickListener = onClickListenerImpl;
                }
                viewModelOnClickAndroidViewViewOnClickListener = onClickListenerImpl.setValue(viewModel);
            }
            if ((dirtyFlags & 13) != 0) {
                if (viewModel != null) {
                    viewModelSystemBgShow = viewModel.systemBgShow;
                }
                updateRegistration(0, (Observable) viewModelSystemBgShow);
                if (viewModelSystemBgShow != null) {
                    viewModelSystemBgShowGet = viewModelSystemBgShow.get();
                }
                if ((dirtyFlags & 13) != 0) {
                    if (viewModelSystemBgShowGet) {
                        dirtyFlags |= 512;
                    } else {
                        dirtyFlags |= 256;
                    }
                }
                viewModelSystemBgShowViewGONEViewVISIBLE = viewModelSystemBgShowGet ? 8 : 0;
            }
            if ((dirtyFlags & 14) != 0) {
                if (viewModel != null) {
                    viewModelRearCameraType = viewModel.rearCameraType;
                } else {
                    viewModelRearCameraType = null;
                }
                updateRegistration(1, (Observable) viewModelRearCameraType);
                if (viewModelRearCameraType != null) {
                    viewModelRearCameraTypeGet = viewModelRearCameraType.get();
                }
                boolean viewModelRearCameraTypeInt1 = viewModelRearCameraTypeGet == 1;
                boolean viewModelRearCameraTypeInt2 = viewModelRearCameraTypeGet == 2;
                boolean viewModelRearCameraTypeInt0 = viewModelRearCameraTypeGet == 0;
                if ((dirtyFlags & 14) != 0) {
                    if (viewModelRearCameraTypeInt1) {
                        dirtyFlags |= 128;
                    } else {
                        dirtyFlags |= 64;
                    }
                }
                if ((dirtyFlags & 14) != 0) {
                    if (viewModelRearCameraTypeInt2) {
                        dirtyFlags |= 32;
                    } else {
                        dirtyFlags |= 16;
                    }
                }
                if ((dirtyFlags & 14) != 0) {
                    if (viewModelRearCameraTypeInt0) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    }
                }
                if (viewModelRearCameraTypeInt1) {
                    context = this.mboundView5.getContext();
                    dirtyFlags2 = dirtyFlags;
                    i = R.drawable.id8_settings_system_select_sel;
                } else {
                    dirtyFlags2 = dirtyFlags;
                    context = this.mboundView5.getContext();
                    i = R.drawable.id8_settings_system_select_n;
                }
                viewModelRearCameraTypeInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN = AppCompatResources.getDrawable(context, i);
                viewModelRearCameraTypeInt2MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN = AppCompatResources.getDrawable(this.mboundView7.getContext(), viewModelRearCameraTypeInt2 ? R.drawable.id8_settings_system_select_sel : R.drawable.id8_settings_system_select_n);
                viewModelRearCameraTypeInt0MboundView3AndroidDrawableId8SettingsSystemSelectSelMboundView3AndroidDrawableId8SettingsSystemSelectN = AppCompatResources.getDrawable(this.mboundView3.getContext(), viewModelRearCameraTypeInt0 ? R.drawable.id8_settings_system_select_sel : R.drawable.id8_settings_system_select_n);
                ObservableInt observableInt = viewModelRearCameraType;
                dirtyFlags = dirtyFlags2;
            } else {
                viewModelRearCameraTypeInt2MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN = null;
            }
        }
        if ((dirtyFlags & 12) != 0) {
            this.bmwId8SettingsSystemCamera360.setOnClickListener(viewModelOnClickAndroidViewViewOnClickListener);
            this.bmwId8SettingsSystemCameraAfter.setOnClickListener(viewModelOnClickAndroidViewViewOnClickListener);
            this.bmwId8SettingsSystemCameraOem.setOnClickListener(viewModelOnClickAndroidViewViewOnClickListener);
        }
        if ((dirtyFlags & 13) != 0) {
            this.mboundView1.setVisibility(viewModelSystemBgShowViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 14) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.mboundView3, viewModelRearCameraTypeInt0MboundView3AndroidDrawableId8SettingsSystemSelectSelMboundView3AndroidDrawableId8SettingsSystemSelectN);
            ImageViewBindingAdapter.setImageDrawable(this.mboundView5, viewModelRearCameraTypeInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN);
            ImageViewBindingAdapter.setImageDrawable(this.mboundView7, viewModelRearCameraTypeInt2MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN);
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
