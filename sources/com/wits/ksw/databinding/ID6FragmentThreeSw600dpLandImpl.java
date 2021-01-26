package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.CarInfo;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class ID6FragmentThreeSw600dpLandImpl extends ID6FragmentThree {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final ConstraintLayout mboundView0;

    static {
        sViewsWithIds.put(R.id.id6_file_iamge_view, 3);
        sViewsWithIds.put(R.id.id6_video_mess, 4);
        sViewsWithIds.put(R.id.id6_dvr_image_view, 5);
        sViewsWithIds.put(R.id.id6_dvr_mess, 6);
        sViewsWithIds.put(R.id.id6_dashboard_image_view, 7);
    }

    public ID6FragmentThreeSw600dpLandImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }

    private ID6FragmentThreeSw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, bindings[7], bindings[5], bindings[6], bindings[3], bindings[1], bindings[2], bindings[4]);
        this.mDirtyFlags = -1;
        this.id6OilMess.setTag((Object) null);
        this.id6SpeedMess.setTag((Object) null);
        this.mboundView0 = bindings[0];
        this.mboundView0.setTag((Object) null);
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

    public boolean setVariable(int variableId, @Nullable Object variable) {
        if (19 != variableId) {
            return false;
        }
        setViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setViewModel(@Nullable LauncherViewModel ViewModel) {
        this.mViewModel = ViewModel;
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelCarInfoOilValue((ObservableInt) object, fieldId);
            case 1:
                return onChangeViewModelCarInfoSpeed((ObservableInt) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelCarInfoOilValue(ObservableInt ViewModelCarInfoOilValue, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoSpeed(ObservableInt ViewModelCarInfoSpeed, int fieldId) {
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
        int viewModelCarInfoOilValueGet = 0;
        String viewModelCarInfoOilValueJavaLangStringL = null;
        int viewModelCarInfoSpeedGet = 0;
        ObservableInt viewModelCarInfoOilValue = null;
        ObservableInt viewModelCarInfoSpeed = null;
        String viewModelCarInfoSpeedJavaLangStringKmH = null;
        if ((11 & dirtyFlags) != 0) {
            CarInfo viewModelCarInfo = LauncherViewModel.carInfo;
            if ((dirtyFlags & 9) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoOilValue = viewModelCarInfo.oilValue;
                }
                updateRegistration(0, (Observable) viewModelCarInfoOilValue);
                if (viewModelCarInfoOilValue != null) {
                    viewModelCarInfoOilValueGet = viewModelCarInfoOilValue.get();
                }
                viewModelCarInfoOilValueJavaLangStringL = viewModelCarInfoOilValueGet + "L";
            }
            if ((dirtyFlags & 10) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoSpeed = viewModelCarInfo.speed;
                }
                updateRegistration(1, (Observable) viewModelCarInfoSpeed);
                if (viewModelCarInfoSpeed != null) {
                    viewModelCarInfoSpeedGet = viewModelCarInfoSpeed.get();
                }
                viewModelCarInfoSpeedJavaLangStringKmH = viewModelCarInfoSpeedGet + "km/h";
            }
        }
        if ((9 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.id6OilMess, viewModelCarInfoOilValueJavaLangStringL);
        }
        if ((dirtyFlags & 10) != 0) {
            TextViewBindingAdapter.setText(this.id6SpeedMess, viewModelCarInfoSpeedJavaLangStringKmH);
        }
    }
}
