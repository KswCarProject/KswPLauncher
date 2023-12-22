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
public class Benz2021KswFragmentTwoHdpi1280x720Impl extends Benz2021KswFragmentTwo {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.car_rl, 8);
        sparseIntArray.put(C0899R.C0901id.car_tv, 9);
        sparseIntArray.put(C0899R.C0901id.car_tip, 10);
        sparseIntArray.put(C0899R.C0901id.iv_car1, 11);
        sparseIntArray.put(C0899R.C0901id.iv_car2, 12);
        sparseIntArray.put(C0899R.C0901id.setting_rl, 13);
        sparseIntArray.put(C0899R.C0901id.set_tv, 14);
        sparseIntArray.put(C0899R.C0901id.set_tip, 15);
        sparseIntArray.put(C0899R.C0901id.iv_set1, 16);
        sparseIntArray.put(C0899R.C0901id.iv_set2, 17);
        sparseIntArray.put(C0899R.C0901id.video_rl, 18);
        sparseIntArray.put(C0899R.C0901id.video_tv, 19);
        sparseIntArray.put(C0899R.C0901id.bt_tip, 20);
        sparseIntArray.put(C0899R.C0901id.iv_video1, 21);
        sparseIntArray.put(C0899R.C0901id.iv_video2, 22);
        sparseIntArray.put(C0899R.C0901id.app_rl, 23);
        sparseIntArray.put(C0899R.C0901id.app_tv, 24);
        sparseIntArray.put(C0899R.C0901id.app_tip, 25);
        sparseIntArray.put(C0899R.C0901id.iv_apps1, 26);
        sparseIntArray.put(C0899R.C0901id.space2, 27);
        sparseIntArray.put(C0899R.C0901id.iv_apps2, 28);
        sparseIntArray.put(C0899R.C0901id.phonelink_rl, 29);
        sparseIntArray.put(C0899R.C0901id.phonelink_tv, 30);
        sparseIntArray.put(C0899R.C0901id.phonelink_tip, 31);
        sparseIntArray.put(C0899R.C0901id.iv_phone1, 32);
        sparseIntArray.put(C0899R.C0901id.space3, 33);
        sparseIntArray.put(C0899R.C0901id.iv_phone2, 34);
        sparseIntArray.put(C0899R.C0901id.dashboard_rl, 35);
        sparseIntArray.put(C0899R.C0901id.dashboard_tv, 36);
        sparseIntArray.put(C0899R.C0901id.dashboard_tip, 37);
        sparseIntArray.put(C0899R.C0901id.iv_dash1, 38);
        sparseIntArray.put(C0899R.C0901id.space, 39);
        sparseIntArray.put(C0899R.C0901id.iv_dash2, 40);
        sparseIntArray.put(C0899R.C0901id.dvr_rl, 41);
        sparseIntArray.put(C0899R.C0901id.dvr_tv, 42);
        sparseIntArray.put(C0899R.C0901id.dvr_tip, 43);
        sparseIntArray.put(C0899R.C0901id.iv_dvr1, 44);
        sparseIntArray.put(C0899R.C0901id.space5, 45);
        sparseIntArray.put(C0899R.C0901id.iv_dvr2, 46);
    }

    public Benz2021KswFragmentTwoHdpi1280x720Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 47, sIncludes, sViewsWithIds));
    }

    private Benz2021KswFragmentTwoHdpi1280x720Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (BenzMbuxItemView) bindings[4], (RelativeLayout) bindings[23], (TextView) bindings[25], (TextView) bindings[24], (TextView) bindings[20], (BenzMbuxItemView) bindings[1], (RelativeLayout) bindings[8], (TextView) bindings[10], (TextView) bindings[9], (BenzMbuxItemView) bindings[6], (RelativeLayout) bindings[35], (TextView) bindings[37], (TextView) bindings[36], (BenzMbuxItemView) bindings[7], (RelativeLayout) bindings[41], (TextView) bindings[43], (TextView) bindings[42], (LinearLayout) bindings[0], (ImageView) bindings[26], (ImageView) bindings[28], (ImageView) bindings[11], (ImageView) bindings[12], (ImageView) bindings[38], (ImageView) bindings[40], (ImageView) bindings[44], (ImageView) bindings[46], (ImageView) bindings[32], (ImageView) bindings[34], (ImageView) bindings[16], (ImageView) bindings[17], (ImageView) bindings[21], (ImageView) bindings[22], (BenzMbuxItemView) bindings[5], (RelativeLayout) bindings[29], (TextView) bindings[31], (TextView) bindings[30], (BenzMbuxItemView) bindings[2], (TextView) bindings[15], (TextView) bindings[14], (RelativeLayout) bindings[13], (View) bindings[39], null, (View) bindings[27], (View) bindings[33], (View) bindings[45], null, null, (BenzMbuxItemView) bindings[3], (RelativeLayout) bindings[18], (TextView) bindings[19]);
        this.mDirtyFlags = -1L;
        this.appItemview.setTag(null);
        this.carItemview.setTag(null);
        this.dashboardItemview.setTag(null);
        this.dvrItemview.setTag(null);
        this.fragmentTwoLl.setTag(null);
        this.phonelinkItemview.setTag(null);
        this.setItemview.setTag(null);
        this.videoItemview.setTag(null);
        setRootTag(root);
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
        if (25 == variableId) {
            setViewModel((BcVieModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.Benz2021KswFragmentTwo
    public void setViewModel(BcVieModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(25);
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
        Drawable viewModelItemIconClassicalVideoItemviewAndroidDrawableBenzMbux2021KswHomeVideoSelector1VideoItemviewAndroidDrawableBenzMbux2021KswHomeVideoSelector2 = null;
        Drawable viewModelItemIconClassicalDvrItemviewAndroidDrawableBenzMbux2021KswHomeDvrSelector1DvrItemviewAndroidDrawableBenzMbux2021KswHomeDvrSelector2 = null;
        Drawable viewModelItemIconClassicalPhonelinkItemviewAndroidDrawableBenzMbux2021KswHomeEasySelector1PhonelinkItemviewAndroidDrawableBenzMbux2021KswHomeEasySelector2 = null;
        ObservableBoolean viewModelItemIconClassical = null;
        Drawable viewModelItemIconClassicalDashboardItemviewAndroidDrawableBenzMbux2021KswHomeDashboardSelector1DashboardItemviewAndroidDrawableBenzMbux2021KswHomeDashboardSelector2 = null;
        Drawable viewModelItemIconClassicalCarItemviewAndroidDrawableBenzMbux2021KswHomeCarSelector1CarItemviewAndroidDrawableBenzMbux2021KswHomeCarSelector2 = null;
        Drawable viewModelItemIconClassicalSetItemviewAndroidDrawableBenzMbux2021KswHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021KswHomeSettingSelector2 = null;
        boolean viewModelItemIconClassicalGet = false;
        Drawable viewModelItemIconClassicalAppItemviewAndroidDrawableBenzMbux2021KswHomeAppSelector1AppItemviewAndroidDrawableBenzMbux2021KswHomeAppSelector2 = null;
        BcVieModel viewModel = this.mViewModel;
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
                    dirtyFlags = dirtyFlags | 16 | 64 | 256 | PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID | PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM | PlaybackStateCompat.ACTION_PREPARE | PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
                } else {
                    dirtyFlags = dirtyFlags | 8 | 32 | 128 | 512 | PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH | PlaybackStateCompat.ACTION_PLAY_FROM_URI | PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                }
            }
            viewModelItemIconClassicalVideoItemviewAndroidDrawableBenzMbux2021KswHomeVideoSelector1VideoItemviewAndroidDrawableBenzMbux2021KswHomeVideoSelector2 = AppCompatResources.getDrawable(this.videoItemview.getContext(), viewModelItemIconClassicalGet ? C0899R.C0900drawable.benz_mbux_2021_ksw_home_video_selector1 : C0899R.C0900drawable.benz_mbux_2021_ksw_home_video_selector2);
            viewModelItemIconClassicalDvrItemviewAndroidDrawableBenzMbux2021KswHomeDvrSelector1DvrItemviewAndroidDrawableBenzMbux2021KswHomeDvrSelector2 = AppCompatResources.getDrawable(this.dvrItemview.getContext(), viewModelItemIconClassicalGet ? C0899R.C0900drawable.benz_mbux_2021_ksw_home_dvr_selector1 : C0899R.C0900drawable.benz_mbux_2021_ksw_home_dvr_selector2);
            viewModelItemIconClassicalPhonelinkItemviewAndroidDrawableBenzMbux2021KswHomeEasySelector1PhonelinkItemviewAndroidDrawableBenzMbux2021KswHomeEasySelector2 = AppCompatResources.getDrawable(this.phonelinkItemview.getContext(), viewModelItemIconClassicalGet ? C0899R.C0900drawable.benz_mbux_2021_ksw_home_easy_selector1 : C0899R.C0900drawable.benz_mbux_2021_ksw_home_easy_selector2);
            viewModelItemIconClassicalDashboardItemviewAndroidDrawableBenzMbux2021KswHomeDashboardSelector1DashboardItemviewAndroidDrawableBenzMbux2021KswHomeDashboardSelector2 = AppCompatResources.getDrawable(this.dashboardItemview.getContext(), viewModelItemIconClassicalGet ? C0899R.C0900drawable.benz_mbux_2021_ksw_home_dashboard_selector1 : C0899R.C0900drawable.benz_mbux_2021_ksw_home_dashboard_selector2);
            viewModelItemIconClassicalCarItemviewAndroidDrawableBenzMbux2021KswHomeCarSelector1CarItemviewAndroidDrawableBenzMbux2021KswHomeCarSelector2 = AppCompatResources.getDrawable(this.carItemview.getContext(), viewModelItemIconClassicalGet ? C0899R.C0900drawable.benz_mbux_2021_ksw_home_car_selector1 : C0899R.C0900drawable.benz_mbux_2021_ksw_home_car_selector2);
            viewModelItemIconClassicalSetItemviewAndroidDrawableBenzMbux2021KswHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021KswHomeSettingSelector2 = AppCompatResources.getDrawable(this.setItemview.getContext(), viewModelItemIconClassicalGet ? C0899R.C0900drawable.benz_mbux_2021_ksw_home_setting_selector1 : C0899R.C0900drawable.benz_mbux_2021_ksw_home_setting_selector2);
            viewModelItemIconClassicalAppItemviewAndroidDrawableBenzMbux2021KswHomeAppSelector1AppItemviewAndroidDrawableBenzMbux2021KswHomeAppSelector2 = AppCompatResources.getDrawable(this.appItemview.getContext(), viewModelItemIconClassicalGet ? C0899R.C0900drawable.benz_mbux_2021_ksw_home_app_selector1 : C0899R.C0900drawable.benz_mbux_2021_ksw_home_app_selector2);
        }
        if ((dirtyFlags & 7) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.appItemview, viewModelItemIconClassicalAppItemviewAndroidDrawableBenzMbux2021KswHomeAppSelector1AppItemviewAndroidDrawableBenzMbux2021KswHomeAppSelector2);
            ImageViewBindingAdapter.setImageDrawable(this.carItemview, viewModelItemIconClassicalCarItemviewAndroidDrawableBenzMbux2021KswHomeCarSelector1CarItemviewAndroidDrawableBenzMbux2021KswHomeCarSelector2);
            ImageViewBindingAdapter.setImageDrawable(this.dashboardItemview, viewModelItemIconClassicalDashboardItemviewAndroidDrawableBenzMbux2021KswHomeDashboardSelector1DashboardItemviewAndroidDrawableBenzMbux2021KswHomeDashboardSelector2);
            ImageViewBindingAdapter.setImageDrawable(this.dvrItemview, viewModelItemIconClassicalDvrItemviewAndroidDrawableBenzMbux2021KswHomeDvrSelector1DvrItemviewAndroidDrawableBenzMbux2021KswHomeDvrSelector2);
            ImageViewBindingAdapter.setImageDrawable(this.phonelinkItemview, viewModelItemIconClassicalPhonelinkItemviewAndroidDrawableBenzMbux2021KswHomeEasySelector1PhonelinkItemviewAndroidDrawableBenzMbux2021KswHomeEasySelector2);
            ImageViewBindingAdapter.setImageDrawable(this.setItemview, viewModelItemIconClassicalSetItemviewAndroidDrawableBenzMbux2021KswHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021KswHomeSettingSelector2);
            ImageViewBindingAdapter.setImageDrawable(this.videoItemview, viewModelItemIconClassicalVideoItemviewAndroidDrawableBenzMbux2021KswHomeVideoSelector1VideoItemviewAndroidDrawableBenzMbux2021KswHomeVideoSelector2);
        }
    }
}
