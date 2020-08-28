package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.CarInfo;
import com.wits.ksw.launcher.model.AudiViewModel;
import com.wits.ksw.launcher.model.DashboardViewModel;

public class AudiRightCarinfoBindingSw600dpLandImpl extends AudiRightCarinfoBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;

    static {
        sViewsWithIds.put(R.id.imageView5, 5);
        sViewsWithIds.put(R.id.textView4, 6);
        sViewsWithIds.put(R.id.imageView9, 7);
        sViewsWithIds.put(R.id.imageView10, 8);
        sViewsWithIds.put(R.id.imageView11, 9);
    }

    public AudiRightCarinfoBindingSw600dpLandImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private AudiRightCarinfoBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 5, bindings[0], bindings[3], bindings[4], bindings[2], bindings[8], bindings[9], bindings[5], bindings[7], bindings[1], bindings[6]);
        this.mDirtyFlags = -1;
        this.KSWA4LRightTrafficInformation.setTag((Object) null);
        this.TvDrivingRange.setTag((Object) null);
        this.TvTemp.setTag((Object) null);
        this.TvZhuanSu.setTag((Object) null);
        this.pointerImageView.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 64;
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
        if (10 != variableId) {
            return false;
        }
        setVm((AudiViewModel) variable);
        return true;
    }

    public void setVm(@Nullable AudiViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(10);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVmCarInfoTurnSpeedAnge((ObservableFloat) object, fieldId);
            case 1:
                return onChangeVmCarInfoView((ObservableInt) object, fieldId);
            case 2:
                return onChangeVmCarInfoTempStr((ObservableField) object, fieldId);
            case 3:
                return onChangeVmCarInfoMileage((ObservableField) object, fieldId);
            case 4:
                return onChangeVmCarInfoTurnSpeed((ObservableInt) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmCarInfoTurnSpeedAnge(ObservableFloat VmCarInfoTurnSpeedAnge, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeVmCarInfoView(ObservableInt VmCarInfoView, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeVmCarInfoTempStr(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeVmCarInfoMileage(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeVmCarInfoTurnSpeed(ObservableInt VmCarInfoTurnSpeed, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        ObservableInt vmCarInfoTurnSpeed;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        AudiViewModel vm = this.mVm;
        ObservableFloat vmCarInfoTurnSpeedAnge = null;
        int vmCarInfoViewGet = 0;
        ObservableInt vmCarInfoView = null;
        String vmCarInfoMileageGet = null;
        ObservableField<String> vmCarInfoTempStr = null;
        String vmCarInfoTempStrGet = null;
        int vmCarInfoTurnSpeedGet = 0;
        ObservableField<String> vmCarInfoMileage = null;
        String stringValueOfVmCarInfoTurnSpeed = null;
        float vmCarInfoTurnSpeedAngeGet = 0.0f;
        if ((dirtyFlags & 98) != 0) {
            if (vm != null) {
                vmCarInfoView = vm.carInfoView;
            }
            updateRegistration(1, (Observable) vmCarInfoView);
            if (vmCarInfoView != null) {
                vmCarInfoViewGet = vmCarInfoView.get();
            }
        }
        if ((93 & dirtyFlags) != 0) {
            CarInfo vmCarInfo = AudiViewModel.carInfo;
            if ((dirtyFlags & 65) != 0) {
                if (vmCarInfo != null) {
                    vmCarInfoTurnSpeedAnge = vmCarInfo.turnSpeedAnge;
                }
                updateRegistration(0, (Observable) vmCarInfoTurnSpeedAnge);
                if (vmCarInfoTurnSpeedAnge != null) {
                    vmCarInfoTurnSpeedAngeGet = vmCarInfoTurnSpeedAnge.get();
                }
            }
            if ((dirtyFlags & 68) != 0) {
                if (vmCarInfo != null) {
                    vmCarInfoTempStr = vmCarInfo.tempStr;
                }
                updateRegistration(2, (Observable) vmCarInfoTempStr);
                if (vmCarInfoTempStr != null) {
                    vmCarInfoTempStrGet = vmCarInfoTempStr.get();
                }
            }
            if ((dirtyFlags & 72) != 0) {
                if (vmCarInfo != null) {
                    vmCarInfoMileage = vmCarInfo.mileage;
                }
                updateRegistration(3, (Observable) vmCarInfoMileage);
                if (vmCarInfoMileage != null) {
                    vmCarInfoMileageGet = vmCarInfoMileage.get();
                }
            }
            if ((dirtyFlags & 80) != 0) {
                if (vmCarInfo != null) {
                    vmCarInfoTurnSpeed = vmCarInfo.turnSpeed;
                } else {
                    vmCarInfoTurnSpeed = null;
                }
                updateRegistration(4, (Observable) vmCarInfoTurnSpeed);
                if (vmCarInfoTurnSpeed != null) {
                    vmCarInfoTurnSpeedGet = vmCarInfoTurnSpeed.get();
                }
                stringValueOfVmCarInfoTurnSpeed = String.valueOf(vmCarInfoTurnSpeedGet);
                ObservableInt observableInt = vmCarInfoTurnSpeed;
            }
        }
        float vmCarInfoTurnSpeedAngeGet2 = vmCarInfoTurnSpeedAngeGet;
        if ((dirtyFlags & 98) != 0) {
            this.KSWA4LRightTrafficInformation.setVisibility(vmCarInfoViewGet);
        }
        if ((dirtyFlags & 72) != 0) {
            TextViewBindingAdapter.setText(this.TvDrivingRange, vmCarInfoMileageGet);
        }
        if ((dirtyFlags & 68) != 0) {
            TextViewBindingAdapter.setText(this.TvTemp, vmCarInfoTempStrGet);
        }
        if ((dirtyFlags & 80) != 0) {
            TextViewBindingAdapter.setText(this.TvZhuanSu, stringValueOfVmCarInfoTurnSpeed);
        }
        if ((dirtyFlags & 65) != 0) {
            DashboardViewModel.setRotation(this.pointerImageView, vmCarInfoTurnSpeedAngeGet2);
        }
    }
}
