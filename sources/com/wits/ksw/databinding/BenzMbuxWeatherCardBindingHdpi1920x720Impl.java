package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.p004v7.content.res.AppCompatResources;
import android.support.p004v7.widget.AppCompatImageView;
import android.support.p004v7.widget.AppCompatTextView;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.C0899R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.BcVieModel;

/* loaded from: classes7.dex */
public class BenzMbuxWeatherCardBindingHdpi1920x720Impl extends BenzMbuxWeatherCardBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback170;
    private final View.OnClickListener mCallback171;
    private final View.OnClickListener mCallback172;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final AppCompatImageView mboundView2;
    private final AppCompatImageView mboundView3;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.benz_mbux_local_card_title, 4);
        sparseIntArray.put(C0899R.C0901id.benz_mbux_local_card_content, 5);
    }

    public BenzMbuxWeatherCardBindingHdpi1920x720Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    private BenzMbuxWeatherCardBindingHdpi1920x720Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (AppCompatImageView) bindings[1], (AppCompatTextView) bindings[5], (AppCompatTextView) bindings[4]);
        this.mDirtyFlags = -1L;
        this.benzMbuxLocalCardBg.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        AppCompatImageView appCompatImageView = (AppCompatImageView) bindings[2];
        this.mboundView2 = appCompatImageView;
        appCompatImageView.setTag(null);
        AppCompatImageView appCompatImageView2 = (AppCompatImageView) bindings[3];
        this.mboundView3 = appCompatImageView2;
        appCompatImageView2.setTag(null);
        setRootTag(root);
        this.mCallback171 = new OnClickListener(this, 2);
        this.mCallback172 = new OnClickListener(this, 3);
        this.mCallback170 = new OnClickListener(this, 1);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
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
        if (16 == variableId) {
            setViewModel((BcVieModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.BenzMbuxWeatherCardBinding
    public void setViewModel(BcVieModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(16);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelItemIconClassical((ObservableBoolean) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelItemIconClassical(ObservableBoolean ViewModelItemIconClassical, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
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
        BcVieModel viewModel = this.mViewModel;
        ObservableBoolean viewModelItemIconClassical = null;
        Drawable viewModelItemIconClassicalBenzMbuxLocalCardBgAndroidDrawableBenzMbux2021KswV2WeatherIcon1SelectorBenzMbuxLocalCardBgAndroidDrawableBenzMbux2021KswV2WeatherIcon2Selector = null;
        boolean viewModelItemIconClassicalGet = false;
        if ((dirtyFlags & 7) != 0) {
            if (viewModel != null) {
                viewModelItemIconClassical = viewModel.itemIconClassical;
            }
            updateRegistration(0, viewModelItemIconClassical);
            if (viewModelItemIconClassical != null) {
                viewModelItemIconClassicalGet = viewModelItemIconClassical.get();
            }
            if ((dirtyFlags & 7) != 0) {
                if (viewModelItemIconClassicalGet) {
                    dirtyFlags |= 16;
                } else {
                    dirtyFlags |= 8;
                }
            }
            viewModelItemIconClassicalBenzMbuxLocalCardBgAndroidDrawableBenzMbux2021KswV2WeatherIcon1SelectorBenzMbuxLocalCardBgAndroidDrawableBenzMbux2021KswV2WeatherIcon2Selector = AppCompatResources.getDrawable(this.benzMbuxLocalCardBg.getContext(), viewModelItemIconClassicalGet ? C0899R.C0900drawable.benz_mbux_2021_ksw_v2_weather_icon1_selector : C0899R.C0900drawable.benz_mbux_2021_ksw_v2_weather_icon2_selector);
        }
        if ((7 & dirtyFlags) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.benzMbuxLocalCardBg, viewModelItemIconClassicalBenzMbuxLocalCardBgAndroidDrawableBenzMbux2021KswV2WeatherIcon1SelectorBenzMbuxLocalCardBgAndroidDrawableBenzMbux2021KswV2WeatherIcon2Selector);
        }
        if ((4 & dirtyFlags) != 0) {
            this.benzMbuxLocalCardBg.setOnClickListener(this.mCallback170);
            this.mboundView2.setOnClickListener(this.mCallback171);
            this.mboundView3.setOnClickListener(this.mCallback172);
        }
    }

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean viewModelJavaLangObjectNull;
        switch (sourceId) {
            case 1:
                BcVieModel viewModel = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel.openWeatherApp(callbackArg_0);
                    return;
                }
                return;
            case 2:
                BcVieModel viewModel2 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel2 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel2.openWeatherApp(callbackArg_0);
                    return;
                }
                return;
            case 3:
                BcVieModel viewModel3 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel3 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel3.openWeatherApp(callbackArg_0);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
