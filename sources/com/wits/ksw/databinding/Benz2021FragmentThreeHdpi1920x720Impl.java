package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.p001v4.media.session.PlaybackStateCompat;
import android.support.p004v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;

/* loaded from: classes7.dex */
public class Benz2021FragmentThreeHdpi1920x720Impl extends Benz2021FragmentThree {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.phonelink_rl, 4);
        sparseIntArray.put(C0899R.C0901id.phonelink_tv, 5);
        sparseIntArray.put(C0899R.C0901id.phonelink_tip, 6);
        sparseIntArray.put(C0899R.C0901id.iv_phone1, 7);
        sparseIntArray.put(C0899R.C0901id.space1, 8);
        sparseIntArray.put(C0899R.C0901id.iv_phone2, 9);
        sparseIntArray.put(C0899R.C0901id.app_rl, 10);
        sparseIntArray.put(C0899R.C0901id.app_tv, 11);
        sparseIntArray.put(C0899R.C0901id.app_tip, 12);
        sparseIntArray.put(C0899R.C0901id.iv_apps1, 13);
        sparseIntArray.put(C0899R.C0901id.space2, 14);
        sparseIntArray.put(C0899R.C0901id.iv_apps2, 15);
        sparseIntArray.put(C0899R.C0901id.dashboard_rl, 16);
        sparseIntArray.put(C0899R.C0901id.dashboard_tv, 17);
        sparseIntArray.put(C0899R.C0901id.dashboard_tip, 18);
        sparseIntArray.put(C0899R.C0901id.iv_dash1, 19);
        sparseIntArray.put(C0899R.C0901id.space, 20);
        sparseIntArray.put(C0899R.C0901id.iv_dash2, 21);
    }

    public Benz2021FragmentThreeHdpi1920x720Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 22, sIncludes, sViewsWithIds));
    }

    private Benz2021FragmentThreeHdpi1920x720Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (BenzMbuxItemView) bindings[2], null, (RelativeLayout) bindings[10], (TextView) bindings[12], (TextView) bindings[11], (BenzMbuxItemView) bindings[3], null, (RelativeLayout) bindings[16], (TextView) bindings[18], (TextView) bindings[17], (LinearLayout) bindings[0], (ImageView) bindings[13], (ImageView) bindings[15], (ImageView) bindings[19], (ImageView) bindings[21], (ImageView) bindings[7], (ImageView) bindings[9], (BenzMbuxItemView) bindings[1], null, (RelativeLayout) bindings[4], (TextView) bindings[6], (TextView) bindings[5], (View) bindings[20], (View) bindings[8], (View) bindings[14]);
        this.mDirtyFlags = -1L;
        this.appItemview.setTag(null);
        this.dashboardItemview.setTag(null);
        this.fragmentTwoLl.setTag(null);
        this.phonelinkItemview.setTag(null);
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
            setViewModel((BcVieModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.Benz2021FragmentThree
    public void setViewModel(BcVieModel ViewModel) {
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
                return onChangeViewModelIsYO((ObservableBoolean) object, fieldId);
            case 1:
                return onChangeViewModelItemIconClassical((ObservableBoolean) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelIsYO(ObservableBoolean ViewModelIsYO, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelItemIconClassical(ObservableBoolean ViewModelItemIconClassical, int fieldId) {
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
        ObservableBoolean viewModelIsYO = null;
        Drawable viewModelIsYOPhonelinkItemviewAndroidDrawableBenzMbuxYoHomeEasySelector1PhonelinkItemviewAndroidDrawableBenzMbux2021HomeEasySelector1 = null;
        Drawable viewModelItemIconClassicalViewModelIsYOAppItemviewAndroidDrawableBenzMbuxYoHomeAppSelector1AppItemviewAndroidDrawableBenzMbux2021HomeAppSelector1AppItemviewAndroidDrawableBenzMbux2021HomeAppSelector2 = null;
        boolean viewModelItemIconClassicalGet = false;
        Drawable viewModelIsYOAppItemviewAndroidDrawableBenzMbuxYoHomeAppSelector1AppItemviewAndroidDrawableBenzMbux2021HomeAppSelector1 = null;
        ObservableBoolean viewModelItemIconClassical = null;
        boolean viewModelIsYOGet = false;
        Drawable viewModelIsYODashboardItemviewAndroidDrawableBenzMbuxYoHomeDashboardSelector1DashboardItemviewAndroidDrawableBenzMbux2021HomeDashboardSelector1 = null;
        Drawable viewModelItemIconClassicalViewModelIsYOPhonelinkItemviewAndroidDrawableBenzMbuxYoHomeEasySelector1PhonelinkItemviewAndroidDrawableBenzMbux2021HomeEasySelector1PhonelinkItemviewAndroidDrawableBenzMbux2021HomeEasySelector2 = null;
        Drawable viewModelItemIconClassicalViewModelIsYODashboardItemviewAndroidDrawableBenzMbuxYoHomeDashboardSelector1DashboardItemviewAndroidDrawableBenzMbux2021HomeDashboardSelector1DashboardItemviewAndroidDrawableBenzMbux2021HomeDashboardSelector2 = null;
        BcVieModel viewModel = this.mViewModel;
        if ((dirtyFlags & 15) != 0) {
            if (viewModel != null) {
                viewModelItemIconClassical = viewModel.itemIconClassical;
            }
            updateRegistration(1, viewModelItemIconClassical);
            if (viewModelItemIconClassical != null) {
                viewModelItemIconClassicalGet = viewModelItemIconClassical.get();
            }
            if ((dirtyFlags & 15) != 0) {
                dirtyFlags = viewModelItemIconClassicalGet ? dirtyFlags | 128 | PlaybackStateCompat.ACTION_PLAY_FROM_URI | PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID : dirtyFlags | 64 | PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM | PlaybackStateCompat.ACTION_PREPARE;
            }
        }
        if ((41088 & dirtyFlags) != 0) {
            if (viewModel != null) {
                viewModelIsYO = viewModel.isYO;
            }
            updateRegistration(0, viewModelIsYO);
            if (viewModelIsYO != null) {
                viewModelIsYOGet = viewModelIsYO.get();
            }
            if ((dirtyFlags & PlaybackStateCompat.ACTION_PLAY_FROM_URI) != 0) {
                if (viewModelIsYOGet) {
                    dirtyFlags |= 32;
                } else {
                    dirtyFlags |= 16;
                }
            }
            if ((dirtyFlags & 128) != 0) {
                if (viewModelIsYOGet) {
                    dirtyFlags |= 512;
                } else {
                    dirtyFlags |= 256;
                }
            }
            if ((dirtyFlags & PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID) != 0) {
                if (viewModelIsYOGet) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                }
            }
            if ((dirtyFlags & PlaybackStateCompat.ACTION_PLAY_FROM_URI) != 0) {
                viewModelIsYOPhonelinkItemviewAndroidDrawableBenzMbuxYoHomeEasySelector1PhonelinkItemviewAndroidDrawableBenzMbux2021HomeEasySelector1 = AppCompatResources.getDrawable(this.phonelinkItemview.getContext(), viewModelIsYOGet ? C0899R.C0900drawable.benz_mbux_yo_home_easy_selector1 : C0899R.C0900drawable.benz_mbux_2021_home_easy_selector1);
            }
            if ((dirtyFlags & 128) != 0) {
                viewModelIsYOAppItemviewAndroidDrawableBenzMbuxYoHomeAppSelector1AppItemviewAndroidDrawableBenzMbux2021HomeAppSelector1 = AppCompatResources.getDrawable(this.appItemview.getContext(), viewModelIsYOGet ? C0899R.C0900drawable.benz_mbux_yo_home_app_selector1 : C0899R.C0900drawable.benz_mbux_2021_home_app_selector1);
            }
            if ((dirtyFlags & PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID) != 0) {
                viewModelIsYODashboardItemviewAndroidDrawableBenzMbuxYoHomeDashboardSelector1DashboardItemviewAndroidDrawableBenzMbux2021HomeDashboardSelector1 = AppCompatResources.getDrawable(this.dashboardItemview.getContext(), viewModelIsYOGet ? C0899R.C0900drawable.benz_mbux_yo_home_dashboard_selector1 : C0899R.C0900drawable.benz_mbux_2021_home_dashboard_selector1);
            }
        }
        if ((dirtyFlags & 15) != 0) {
            viewModelItemIconClassicalViewModelIsYOAppItemviewAndroidDrawableBenzMbuxYoHomeAppSelector1AppItemviewAndroidDrawableBenzMbux2021HomeAppSelector1AppItemviewAndroidDrawableBenzMbux2021HomeAppSelector2 = viewModelItemIconClassicalGet ? viewModelIsYOAppItemviewAndroidDrawableBenzMbuxYoHomeAppSelector1AppItemviewAndroidDrawableBenzMbux2021HomeAppSelector1 : AppCompatResources.getDrawable(this.appItemview.getContext(), C0899R.C0900drawable.benz_mbux_2021_home_app_selector2);
            viewModelItemIconClassicalViewModelIsYOPhonelinkItemviewAndroidDrawableBenzMbuxYoHomeEasySelector1PhonelinkItemviewAndroidDrawableBenzMbux2021HomeEasySelector1PhonelinkItemviewAndroidDrawableBenzMbux2021HomeEasySelector2 = viewModelItemIconClassicalGet ? viewModelIsYOPhonelinkItemviewAndroidDrawableBenzMbuxYoHomeEasySelector1PhonelinkItemviewAndroidDrawableBenzMbux2021HomeEasySelector1 : AppCompatResources.getDrawable(this.phonelinkItemview.getContext(), C0899R.C0900drawable.benz_mbux_2021_home_easy_selector2);
            viewModelItemIconClassicalViewModelIsYODashboardItemviewAndroidDrawableBenzMbuxYoHomeDashboardSelector1DashboardItemviewAndroidDrawableBenzMbux2021HomeDashboardSelector1DashboardItemviewAndroidDrawableBenzMbux2021HomeDashboardSelector2 = viewModelItemIconClassicalGet ? viewModelIsYODashboardItemviewAndroidDrawableBenzMbuxYoHomeDashboardSelector1DashboardItemviewAndroidDrawableBenzMbux2021HomeDashboardSelector1 : AppCompatResources.getDrawable(this.dashboardItemview.getContext(), C0899R.C0900drawable.benz_mbux_2021_home_dashboard_selector2);
        }
        if ((dirtyFlags & 15) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.appItemview, viewModelItemIconClassicalViewModelIsYOAppItemviewAndroidDrawableBenzMbuxYoHomeAppSelector1AppItemviewAndroidDrawableBenzMbux2021HomeAppSelector1AppItemviewAndroidDrawableBenzMbux2021HomeAppSelector2);
            ImageViewBindingAdapter.setImageDrawable(this.dashboardItemview, viewModelItemIconClassicalViewModelIsYODashboardItemviewAndroidDrawableBenzMbuxYoHomeDashboardSelector1DashboardItemviewAndroidDrawableBenzMbux2021HomeDashboardSelector1DashboardItemviewAndroidDrawableBenzMbux2021HomeDashboardSelector2);
            ImageViewBindingAdapter.setImageDrawable(this.phonelinkItemview, viewModelItemIconClassicalViewModelIsYOPhonelinkItemviewAndroidDrawableBenzMbuxYoHomeEasySelector1PhonelinkItemviewAndroidDrawableBenzMbux2021HomeEasySelector1PhonelinkItemviewAndroidDrawableBenzMbux2021HomeEasySelector2);
        }
    }
}
