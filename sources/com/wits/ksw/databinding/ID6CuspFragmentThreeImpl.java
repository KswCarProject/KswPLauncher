package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.CarInfo;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class ID6CuspFragmentThreeImpl extends ID6CuspFragmentThree {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.id6_cusp_file_iamge_view, 3);
        sparseIntArray.put(R.id.id6_cusp_video_mess, 4);
        sparseIntArray.put(R.id.id6_cusp_dvr_image_view, 5);
        sparseIntArray.put(R.id.id6_cusp_dvr_mess, 6);
        sparseIntArray.put(R.id.id6_cusp_dashboard_image_view, 7);
    }

    public ID6CuspFragmentThreeImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }

    private ID6CuspFragmentThreeImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, bindings[7], bindings[5], bindings[6], bindings[3], bindings[1], bindings[2], bindings[4]);
        this.mDirtyFlags = -1;
        this.id6CuspOilMess.setTag((Object) null);
        this.id6CuspSpeedMess.setTag((Object) null);
        ConstraintLayout constraintLayout = bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
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
        if (16 != variableId) {
            return false;
        }
        setViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setViewModel(LauncherViewModel ViewModel) {
        this.mViewModel = ViewModel;
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelCarInfoOilValue((ObservableField) object, fieldId);
            case 1:
                return onChangeViewModelCarInfoSpeed((ObservableInt) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelCarInfoOilValue(ObservableField<String> observableField, int fieldId) {
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
        ObservableField<String> viewModelCarInfoOilValue = null;
        int viewModelCarInfoSpeedGet = 0;
        ObservableInt viewModelCarInfoSpeed = null;
        String viewModelCarInfoSpeedJavaLangStringKmH = null;
        String viewModelCarInfoOilValueGet = null;
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
        if ((dirtyFlags & 9) != 0) {
            TextViewBindingAdapter.setText(this.id6CuspOilMess, viewModelCarInfoOilValueGet);
        }
        if ((10 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.id6CuspSpeedMess, viewModelCarInfoSpeedJavaLangStringKmH);
        }
    }
}
