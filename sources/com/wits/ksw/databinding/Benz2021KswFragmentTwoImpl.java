package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcVieModel;

public class Benz2021KswFragmentTwoImpl extends Benz2021KswFragmentTwo {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.video_rl, 6);
        sparseIntArray.put(R.id.video_tv, 7);
        sparseIntArray.put(R.id.bt_tip, 8);
        sparseIntArray.put(R.id.iv_video1, 9);
        sparseIntArray.put(R.id.space1, 10);
        sparseIntArray.put(R.id.iv_video2, 11);
        sparseIntArray.put(R.id.app_rl, 12);
        sparseIntArray.put(R.id.app_tv, 13);
        sparseIntArray.put(R.id.app_tip, 14);
        sparseIntArray.put(R.id.iv_apps1, 15);
        sparseIntArray.put(R.id.space2, 16);
        sparseIntArray.put(R.id.iv_apps2, 17);
        sparseIntArray.put(R.id.phonelink_rl, 18);
        sparseIntArray.put(R.id.phonelink_tv, 19);
        sparseIntArray.put(R.id.phonelink_tip, 20);
        sparseIntArray.put(R.id.iv_phone1, 21);
        sparseIntArray.put(R.id.space3, 22);
        sparseIntArray.put(R.id.iv_phone2, 23);
        sparseIntArray.put(R.id.dashboard_rl, 24);
        sparseIntArray.put(R.id.dashboard_tv, 25);
        sparseIntArray.put(R.id.dashboard_tip, 26);
        sparseIntArray.put(R.id.iv_dash1, 27);
        sparseIntArray.put(R.id.space, 28);
        sparseIntArray.put(R.id.iv_dash2, 29);
        sparseIntArray.put(R.id.dvr_rl, 30);
        sparseIntArray.put(R.id.dvr_tv, 31);
        sparseIntArray.put(R.id.dvr_tip, 32);
        sparseIntArray.put(R.id.iv_dvr1, 33);
        sparseIntArray.put(R.id.space5, 34);
        sparseIntArray.put(R.id.iv_dvr2, 35);
    }

    public Benz2021KswFragmentTwoImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 36, sIncludes, sViewsWithIds));
    }

    private Benz2021KswFragmentTwoImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, bindings[2], bindings[12], bindings[14], bindings[13], bindings[8], bindings[4], bindings[24], bindings[26], bindings[25], bindings[5], bindings[30], bindings[32], bindings[31], bindings[0], bindings[15], bindings[17], bindings[27], bindings[29], bindings[33], bindings[35], bindings[21], bindings[23], bindings[9], bindings[11], bindings[3], bindings[18], bindings[20], bindings[19], bindings[28], bindings[10], bindings[16], bindings[22], bindings[34], bindings[1], bindings[6], bindings[7]);
        this.mDirtyFlags = -1;
        this.appItemview.setTag((Object) null);
        this.dashboardItemview.setTag((Object) null);
        this.dvrItemview.setTag((Object) null);
        this.fragmentTwoLl.setTag((Object) null);
        this.phonelinkItemview.setTag((Object) null);
        this.videoItemview.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4;
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
        setViewModel((BcVieModel) variable);
        return true;
    }

    public void setViewModel(BcVieModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(25);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelItemIconClassical((ObservableBoolean) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelItemIconClassical(ObservableBoolean ViewModelItemIconClassical, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
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
        Drawable viewModelItemIconClassicalVideoItemviewAndroidDrawableBenzMbux2021KswHomeVideoSelector1VideoItemviewAndroidDrawableBenzMbux2021KswHomeVideoSelector2 = null;
        Drawable viewModelItemIconClassicalDvrItemviewAndroidDrawableBenzMbux2021KswHomeDvrSelector1DvrItemviewAndroidDrawableBenzMbux2021KswHomeDvrSelector2 = null;
        Drawable viewModelItemIconClassicalPhonelinkItemviewAndroidDrawableBenzMbux2021KswHomeEasySelector1PhonelinkItemviewAndroidDrawableBenzMbux2021KswHomeEasySelector2 = null;
        ObservableBoolean viewModelItemIconClassical = null;
        Drawable viewModelItemIconClassicalDashboardItemviewAndroidDrawableBenzMbux2021KswHomeDashboardSelector1DashboardItemviewAndroidDrawableBenzMbux2021KswHomeDashboardSelector2 = null;
        boolean viewModelItemIconClassicalGet = false;
        Drawable viewModelItemIconClassicalAppItemviewAndroidDrawableBenzMbux2021KswHomeAppSelector1AppItemviewAndroidDrawableBenzMbux2021KswHomeAppSelector2 = null;
        BcVieModel viewModel = this.mViewModel;
        if ((dirtyFlags & 7) != 0) {
            if (viewModel != null) {
                viewModelItemIconClassical = viewModel.itemIconClassical;
            }
            updateRegistration(0, (Observable) viewModelItemIconClassical);
            if (viewModelItemIconClassical != null) {
                viewModelItemIconClassicalGet = viewModelItemIconClassical.get();
            }
            if ((dirtyFlags & 7) != 0) {
                if (viewModelItemIconClassicalGet) {
                    dirtyFlags = dirtyFlags | 16 | 64 | 256 | PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID | PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                } else {
                    dirtyFlags = dirtyFlags | 8 | 32 | 128 | 512 | PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                }
            }
            viewModelItemIconClassicalVideoItemviewAndroidDrawableBenzMbux2021KswHomeVideoSelector1VideoItemviewAndroidDrawableBenzMbux2021KswHomeVideoSelector2 = viewModelItemIconClassicalGet ? AppCompatResources.getDrawable(this.videoItemview.getContext(), R.drawable.benz_mbux_2021_ksw_home_video_selector1) : AppCompatResources.getDrawable(this.videoItemview.getContext(), R.drawable.benz_mbux_2021_ksw_home_video_selector2);
            viewModelItemIconClassicalDvrItemviewAndroidDrawableBenzMbux2021KswHomeDvrSelector1DvrItemviewAndroidDrawableBenzMbux2021KswHomeDvrSelector2 = AppCompatResources.getDrawable(this.dvrItemview.getContext(), viewModelItemIconClassicalGet ? R.drawable.benz_mbux_2021_ksw_home_dvr_selector1 : R.drawable.benz_mbux_2021_ksw_home_dvr_selector2);
            viewModelItemIconClassicalPhonelinkItemviewAndroidDrawableBenzMbux2021KswHomeEasySelector1PhonelinkItemviewAndroidDrawableBenzMbux2021KswHomeEasySelector2 = AppCompatResources.getDrawable(this.phonelinkItemview.getContext(), viewModelItemIconClassicalGet ? R.drawable.benz_mbux_2021_ksw_home_easy_selector1 : R.drawable.benz_mbux_2021_ksw_home_easy_selector2);
            viewModelItemIconClassicalDashboardItemviewAndroidDrawableBenzMbux2021KswHomeDashboardSelector1DashboardItemviewAndroidDrawableBenzMbux2021KswHomeDashboardSelector2 = AppCompatResources.getDrawable(this.dashboardItemview.getContext(), viewModelItemIconClassicalGet ? R.drawable.benz_mbux_2021_ksw_home_dashboard_selector1 : R.drawable.benz_mbux_2021_ksw_home_dashboard_selector2);
            viewModelItemIconClassicalAppItemviewAndroidDrawableBenzMbux2021KswHomeAppSelector1AppItemviewAndroidDrawableBenzMbux2021KswHomeAppSelector2 = AppCompatResources.getDrawable(this.appItemview.getContext(), viewModelItemIconClassicalGet ? R.drawable.benz_mbux_2021_ksw_home_app_selector1 : R.drawable.benz_mbux_2021_ksw_home_app_selector2);
        }
        if ((dirtyFlags & 7) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.appItemview, viewModelItemIconClassicalAppItemviewAndroidDrawableBenzMbux2021KswHomeAppSelector1AppItemviewAndroidDrawableBenzMbux2021KswHomeAppSelector2);
            ImageViewBindingAdapter.setImageDrawable(this.dashboardItemview, viewModelItemIconClassicalDashboardItemviewAndroidDrawableBenzMbux2021KswHomeDashboardSelector1DashboardItemviewAndroidDrawableBenzMbux2021KswHomeDashboardSelector2);
            ImageViewBindingAdapter.setImageDrawable(this.dvrItemview, viewModelItemIconClassicalDvrItemviewAndroidDrawableBenzMbux2021KswHomeDvrSelector1DvrItemviewAndroidDrawableBenzMbux2021KswHomeDvrSelector2);
            ImageViewBindingAdapter.setImageDrawable(this.phonelinkItemview, viewModelItemIconClassicalPhonelinkItemviewAndroidDrawableBenzMbux2021KswHomeEasySelector1PhonelinkItemviewAndroidDrawableBenzMbux2021KswHomeEasySelector2);
            ImageViewBindingAdapter.setImageDrawable(this.videoItemview, viewModelItemIconClassicalVideoItemviewAndroidDrawableBenzMbux2021KswHomeVideoSelector1VideoItemviewAndroidDrawableBenzMbux2021KswHomeVideoSelector2);
        }
    }
}
