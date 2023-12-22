package com.wits.ksw.databinding;

import android.content.Context;
import android.databinding.DataBindingComponent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.p001v4.media.session.PlaybackStateCompat;
import android.support.p004v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.bmw_id8.p009vm.BmwId8SettingsViewModel;

/* loaded from: classes7.dex */
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
    private final ImageView mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_system_camera_lay, 10);
    }

    public BmwId8SettingsSystemCameraLayoutBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }

    private BmwId8SettingsSystemCameraLayoutBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (RelativeLayout) bindings[6], (RelativeLayout) bindings[8], (RelativeLayout) bindings[2], (RelativeLayout) bindings[10], (RelativeLayout) bindings[4]);
        this.mDirtyFlags = -1L;
        this.bmwId8SettingsSystemCamera360.setTag(null);
        this.bmwId8SettingsSystemCamera360Built.setTag(null);
        this.bmwId8SettingsSystemCameraAfter.setTag(null);
        this.bmwId8SettingsSystemCameraOem.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        ImageView imageView2 = (ImageView) bindings[3];
        this.mboundView3 = imageView2;
        imageView2.setTag(null);
        ImageView imageView3 = (ImageView) bindings[5];
        this.mboundView5 = imageView3;
        imageView3.setTag(null);
        ImageView imageView4 = (ImageView) bindings[7];
        this.mboundView7 = imageView4;
        imageView4.setTag(null);
        ImageView imageView5 = (ImageView) bindings[9];
        this.mboundView9 = imageView5;
        imageView5.setTag(null);
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

    @Override // com.wits.ksw.databinding.BmwId8SettingsSystemCameraLayoutBinding
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
                return onChangeViewModelSystemBgShow((ObservableBoolean) object, fieldId);
            case 1:
                return onChangeViewModelRearCameraType((ObservableInt) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelSystemBgShow(ObservableBoolean ViewModelSystemBgShow, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelRearCameraType(ObservableInt ViewModelRearCameraType, int fieldId) {
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
        Drawable viewModelRearCameraTypeInt2MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN;
        ObservableInt viewModelRearCameraType;
        ObservableInt viewModelRearCameraType2;
        long dirtyFlags2;
        Context context;
        int i;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        Drawable viewModelRearCameraTypeInt2MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN2 = null;
        boolean viewModelSystemBgShowGet = false;
        Drawable viewModelRearCameraTypeInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN = null;
        int viewModelSystemBgShowViewGONEViewVISIBLE = 0;
        int viewModelRearCameraTypeGet = 0;
        View.OnClickListener viewModelOnClickAndroidViewViewOnClickListener = null;
        ObservableBoolean viewModelSystemBgShow = null;
        Drawable viewModelRearCameraTypeInt0MboundView3AndroidDrawableId8SettingsSystemSelectSelMboundView3AndroidDrawableId8SettingsSystemSelectN = null;
        BmwId8SettingsViewModel viewModel = this.mViewModel;
        Drawable viewModelRearCameraTypeInt3MboundView9AndroidDrawableId8SettingsSystemSelectSelMboundView9AndroidDrawableId8SettingsSystemSelectN = null;
        if ((dirtyFlags & 15) != 0) {
            if ((dirtyFlags & 12) == 0) {
                viewModelRearCameraTypeInt2MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN = null;
            } else if (viewModel == null) {
                viewModelRearCameraTypeInt2MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN = null;
            } else {
                viewModelRearCameraTypeInt2MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN = null;
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
                updateRegistration(0, viewModelSystemBgShow);
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
            if ((dirtyFlags & 14) == 0) {
                viewModelRearCameraTypeInt2MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN2 = viewModelRearCameraTypeInt2MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN;
                viewModelRearCameraTypeInt3MboundView9AndroidDrawableId8SettingsSystemSelectSelMboundView9AndroidDrawableId8SettingsSystemSelectN = null;
            } else {
                if (viewModel == null) {
                    viewModelRearCameraType = null;
                } else {
                    viewModelRearCameraType = viewModel.rearCameraType;
                }
                updateRegistration(1, viewModelRearCameraType);
                if (viewModelRearCameraType != null) {
                    viewModelRearCameraTypeGet = viewModelRearCameraType.get();
                }
                boolean viewModelRearCameraTypeInt3 = viewModelRearCameraTypeGet == 3;
                boolean viewModelRearCameraTypeInt1 = viewModelRearCameraTypeGet == 1;
                boolean viewModelRearCameraTypeInt2 = viewModelRearCameraTypeGet == 2;
                boolean viewModelRearCameraTypeInt0 = viewModelRearCameraTypeGet == 0;
                if ((dirtyFlags & 14) != 0) {
                    if (viewModelRearCameraTypeInt3) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                    }
                }
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
                if (viewModelRearCameraTypeInt3) {
                    viewModelRearCameraType2 = viewModelRearCameraType;
                    viewModelRearCameraTypeInt3MboundView9AndroidDrawableId8SettingsSystemSelectSelMboundView9AndroidDrawableId8SettingsSystemSelectN = AppCompatResources.getDrawable(this.mboundView9.getContext(), C0899R.C0900drawable.id8_settings_system_select_sel);
                } else {
                    viewModelRearCameraType2 = viewModelRearCameraType;
                    viewModelRearCameraTypeInt3MboundView9AndroidDrawableId8SettingsSystemSelectSelMboundView9AndroidDrawableId8SettingsSystemSelectN = AppCompatResources.getDrawable(this.mboundView9.getContext(), C0899R.C0900drawable.id8_settings_system_select_n);
                }
                if (viewModelRearCameraTypeInt1) {
                    context = this.mboundView5.getContext();
                    dirtyFlags2 = dirtyFlags;
                    i = C0899R.C0900drawable.id8_settings_system_select_sel;
                } else {
                    dirtyFlags2 = dirtyFlags;
                    context = this.mboundView5.getContext();
                    i = C0899R.C0900drawable.id8_settings_system_select_n;
                }
                viewModelRearCameraTypeInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN = AppCompatResources.getDrawable(context, i);
                viewModelRearCameraTypeInt2MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN2 = AppCompatResources.getDrawable(this.mboundView7.getContext(), viewModelRearCameraTypeInt2 ? C0899R.C0900drawable.id8_settings_system_select_sel : C0899R.C0900drawable.id8_settings_system_select_n);
                viewModelRearCameraTypeInt0MboundView3AndroidDrawableId8SettingsSystemSelectSelMboundView3AndroidDrawableId8SettingsSystemSelectN = AppCompatResources.getDrawable(this.mboundView3.getContext(), viewModelRearCameraTypeInt0 ? C0899R.C0900drawable.id8_settings_system_select_sel : C0899R.C0900drawable.id8_settings_system_select_n);
                dirtyFlags = dirtyFlags2;
            }
        }
        if ((dirtyFlags & 12) != 0) {
            this.bmwId8SettingsSystemCamera360.setOnClickListener(viewModelOnClickAndroidViewViewOnClickListener);
            this.bmwId8SettingsSystemCamera360Built.setOnClickListener(viewModelOnClickAndroidViewViewOnClickListener);
            this.bmwId8SettingsSystemCameraAfter.setOnClickListener(viewModelOnClickAndroidViewViewOnClickListener);
            this.bmwId8SettingsSystemCameraOem.setOnClickListener(viewModelOnClickAndroidViewViewOnClickListener);
        }
        if ((dirtyFlags & 13) != 0) {
            this.mboundView1.setVisibility(viewModelSystemBgShowViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 14) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.mboundView3, viewModelRearCameraTypeInt0MboundView3AndroidDrawableId8SettingsSystemSelectSelMboundView3AndroidDrawableId8SettingsSystemSelectN);
            ImageViewBindingAdapter.setImageDrawable(this.mboundView5, viewModelRearCameraTypeInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN);
            ImageViewBindingAdapter.setImageDrawable(this.mboundView7, viewModelRearCameraTypeInt2MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN2);
            ImageViewBindingAdapter.setImageDrawable(this.mboundView9, viewModelRearCameraTypeInt3MboundView9AndroidDrawableId8SettingsSystemSelectSelMboundView9AndroidDrawableId8SettingsSystemSelectN);
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
