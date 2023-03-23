package com.wits.ksw.databinding;

import android.content.Context;
import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.settings.bmw_id8.vm.BmwId8SettingsViewModel;

public class BmwId8SettingsSystemTempLayoutBindingImpl extends BmwId8SettingsSystemTempLayoutBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private OnClickListenerImpl mViewModelOnClickAndroidViewViewOnClickListener;
    private final RelativeLayout mboundView0;
    private final ImageView mboundView1;
    private final ImageView mboundView3;
    private final ImageView mboundView5;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.bmw_id8_settings_system_temp_lay, 6);
    }

    public BmwId8SettingsSystemTempLayoutBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private BmwId8SettingsSystemTempLayoutBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, bindings[2], bindings[4], bindings[6]);
        this.mDirtyFlags = -1;
        this.bmwId8SettingsSystemTempC.setTag((Object) null);
        this.bmwId8SettingsSystemTempF.setTag((Object) null);
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
                return onChangeViewModelTempUnit((ObservableInt) object, fieldId);
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

    private boolean onChangeViewModelTempUnit(ObservableInt ViewModelTempUnit, int fieldId) {
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
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        Drawable viewModelTempUnitInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN = null;
        View.OnClickListener viewModelOnClickAndroidViewViewOnClickListener = null;
        int viewModelTempUnitGet = 0;
        ObservableBoolean viewModelSystemBgShow = null;
        boolean viewModelSystemBgShowGet = false;
        Drawable viewModelTempUnitInt0MboundView3AndroidDrawableId8SettingsSystemSelectSelMboundView3AndroidDrawableId8SettingsSystemSelectN = null;
        ObservableInt viewModelTempUnit = null;
        BmwId8SettingsViewModel viewModel = this.mViewModel;
        int viewModelSystemBgShowViewGONEViewVISIBLE = 0;
        if ((dirtyFlags & 15) != 0) {
            if (!((dirtyFlags & 12) == 0 || viewModel == null)) {
                OnClickListenerImpl onClickListenerImpl = this.mViewModelOnClickAndroidViewViewOnClickListener;
                if (onClickListenerImpl == null) {
                    onClickListenerImpl = new OnClickListenerImpl();
                    this.mViewModelOnClickAndroidViewViewOnClickListener = onClickListenerImpl;
                }
                viewModelOnClickAndroidViewViewOnClickListener = onClickListenerImpl.setValue(viewModel);
            }
            boolean z = false;
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
                    viewModelTempUnit = viewModel.tempUnit;
                }
                updateRegistration(1, (Observable) viewModelTempUnit);
                if (viewModelTempUnit != null) {
                    viewModelTempUnitGet = viewModelTempUnit.get();
                }
                boolean viewModelTempUnitInt0 = viewModelTempUnitGet == 0;
                if (viewModelTempUnitGet == 1) {
                    z = true;
                }
                boolean viewModelTempUnitInt1 = z;
                if ((dirtyFlags & 14) != 0) {
                    if (viewModelTempUnitInt0) {
                        dirtyFlags |= 128;
                    } else {
                        dirtyFlags |= 64;
                    }
                }
                if ((dirtyFlags & 14) != 0) {
                    if (viewModelTempUnitInt1) {
                        dirtyFlags |= 32;
                    } else {
                        dirtyFlags |= 16;
                    }
                }
                int i = R.drawable.id8_settings_system_select_sel;
                Context context = this.mboundView3.getContext();
                if (!viewModelTempUnitInt0) {
                    i = R.drawable.id8_settings_system_select_n;
                }
                viewModelTempUnitInt0MboundView3AndroidDrawableId8SettingsSystemSelectSelMboundView3AndroidDrawableId8SettingsSystemSelectN = AppCompatResources.getDrawable(context, i);
                viewModelTempUnitInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN = AppCompatResources.getDrawable(this.mboundView5.getContext(), viewModelTempUnitInt1 ? R.drawable.id8_settings_system_select_sel : R.drawable.id8_settings_system_select_n);
            }
        }
        if ((dirtyFlags & 12) != 0) {
            this.bmwId8SettingsSystemTempC.setOnClickListener(viewModelOnClickAndroidViewViewOnClickListener);
            this.bmwId8SettingsSystemTempF.setOnClickListener(viewModelOnClickAndroidViewViewOnClickListener);
        }
        if ((dirtyFlags & 13) != 0) {
            this.mboundView1.setVisibility(viewModelSystemBgShowViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 14) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.mboundView3, viewModelTempUnitInt0MboundView3AndroidDrawableId8SettingsSystemSelectSelMboundView3AndroidDrawableId8SettingsSystemSelectN);
            ImageViewBindingAdapter.setImageDrawable(this.mboundView5, viewModelTempUnitInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN);
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
