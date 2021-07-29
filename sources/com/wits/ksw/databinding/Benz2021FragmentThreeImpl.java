package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcVieModel;

public class Benz2021FragmentThreeImpl extends Benz2021FragmentThree {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.phonelink_rl, 4);
        sparseIntArray.put(R.id.phonelink_tv, 5);
        sparseIntArray.put(R.id.phonelink_tip, 6);
        sparseIntArray.put(R.id.iv_phone1, 7);
        sparseIntArray.put(R.id.space1, 8);
        sparseIntArray.put(R.id.iv_phone2, 9);
        sparseIntArray.put(R.id.app_rl, 10);
        sparseIntArray.put(R.id.app_tv, 11);
        sparseIntArray.put(R.id.app_tip, 12);
        sparseIntArray.put(R.id.iv_apps1, 13);
        sparseIntArray.put(R.id.space2, 14);
        sparseIntArray.put(R.id.iv_apps2, 15);
        sparseIntArray.put(R.id.dashboard_rl, 16);
        sparseIntArray.put(R.id.dashboard_tv, 17);
        sparseIntArray.put(R.id.dashboard_tip, 18);
        sparseIntArray.put(R.id.iv_dash1, 19);
        sparseIntArray.put(R.id.space, 20);
        sparseIntArray.put(R.id.iv_dash2, 21);
    }

    public Benz2021FragmentThreeImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 22, sIncludes, sViewsWithIds));
    }

    private Benz2021FragmentThreeImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, bindings[2], bindings[10], bindings[12], bindings[11], bindings[3], bindings[16], bindings[18], bindings[17], bindings[0], bindings[13], bindings[15], bindings[19], bindings[21], bindings[7], bindings[9], bindings[1], bindings[4], bindings[6], bindings[5], bindings[20], bindings[8], bindings[14]);
        this.mDirtyFlags = -1;
        this.appItemview.setTag((Object) null);
        this.dashboardItemview.setTag((Object) null);
        this.fragmentTwoLl.setTag((Object) null);
        this.phonelinkItemview.setTag((Object) null);
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
        if (16 != variableId) {
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
        notifyPropertyChanged(16);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelItemIconClass((ObservableBoolean) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelItemIconClass(ObservableBoolean ViewModelItemIconClass, int fieldId) {
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
        ObservableBoolean viewModelItemIconClass = null;
        Drawable viewModelItemIconClassAppItemviewAndroidDrawableBenzMbux2021HomeAppSelector1AppItemviewAndroidDrawableBenzMbux2021HomeAppSelector2 = null;
        boolean viewModelItemIconClassGet = false;
        Drawable viewModelItemIconClassDashboardItemviewAndroidDrawableBenzMbux2021HomeDashboardSelector1DashboardItemviewAndroidDrawableBenzMbux2021HomeDashboardSelector2 = null;
        Drawable viewModelItemIconClassPhonelinkItemviewAndroidDrawableBenzMbux2021HomeEasySelector1PhonelinkItemviewAndroidDrawableBenzMbux2021HomeEasySelector2 = null;
        BcVieModel viewModel = this.mViewModel;
        if ((dirtyFlags & 7) != 0) {
            if (viewModel != null) {
                viewModelItemIconClass = viewModel.itemIconClass;
            }
            updateRegistration(0, (Observable) viewModelItemIconClass);
            if (viewModelItemIconClass != null) {
                viewModelItemIconClassGet = viewModelItemIconClass.get();
            }
            if ((dirtyFlags & 7) != 0) {
                if (viewModelItemIconClassGet) {
                    dirtyFlags = dirtyFlags | 16 | 64 | 256;
                } else {
                    dirtyFlags = dirtyFlags | 8 | 32 | 128;
                }
            }
            viewModelItemIconClassAppItemviewAndroidDrawableBenzMbux2021HomeAppSelector1AppItemviewAndroidDrawableBenzMbux2021HomeAppSelector2 = AppCompatResources.getDrawable(this.appItemview.getContext(), viewModelItemIconClassGet ? R.drawable.benz_mbux_2021_home_app_selector1 : R.drawable.benz_mbux_2021_home_app_selector2);
            viewModelItemIconClassDashboardItemviewAndroidDrawableBenzMbux2021HomeDashboardSelector1DashboardItemviewAndroidDrawableBenzMbux2021HomeDashboardSelector2 = AppCompatResources.getDrawable(this.dashboardItemview.getContext(), viewModelItemIconClassGet ? R.drawable.benz_mbux_2021_home_dashboard_selector1 : R.drawable.benz_mbux_2021_home_dashboard_selector2);
            viewModelItemIconClassPhonelinkItemviewAndroidDrawableBenzMbux2021HomeEasySelector1PhonelinkItemviewAndroidDrawableBenzMbux2021HomeEasySelector2 = AppCompatResources.getDrawable(this.phonelinkItemview.getContext(), viewModelItemIconClassGet ? R.drawable.benz_mbux_2021_home_easy_selector1 : R.drawable.benz_mbux_2021_home_easy_selector2);
        }
        if ((7 & dirtyFlags) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.appItemview, viewModelItemIconClassAppItemviewAndroidDrawableBenzMbux2021HomeAppSelector1AppItemviewAndroidDrawableBenzMbux2021HomeAppSelector2);
            ImageViewBindingAdapter.setImageDrawable(this.dashboardItemview, viewModelItemIconClassDashboardItemviewAndroidDrawableBenzMbux2021HomeDashboardSelector1DashboardItemviewAndroidDrawableBenzMbux2021HomeDashboardSelector2);
            ImageViewBindingAdapter.setImageDrawable(this.phonelinkItemview, viewModelItemIconClassPhonelinkItemviewAndroidDrawableBenzMbux2021HomeEasySelector1PhonelinkItemviewAndroidDrawableBenzMbux2021HomeEasySelector2);
        }
    }
}
