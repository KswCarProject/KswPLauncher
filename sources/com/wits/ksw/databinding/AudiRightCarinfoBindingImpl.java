package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.CarInfo;
import com.wits.ksw.launcher.model.AudiViewModel;
import com.wits.ksw.launcher.model.DashboardViewModel;

public class AudiRightCarinfoBindingImpl extends AudiRightCarinfoBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.imageView5, 5);
        sparseIntArray.put(R.id.textView4, 6);
        sparseIntArray.put(R.id.imageView9, 7);
        sparseIntArray.put(R.id.imageView10, 8);
        sparseIntArray.put(R.id.imageView11, 9);
    }

    public AudiRightCarinfoBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private AudiRightCarinfoBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
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

    public boolean setVariable(int variableId, Object variable) {
        if (17 != variableId) {
            return false;
        }
        setVm((AudiViewModel) variable);
        return true;
    }

    public void setVm(AudiViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(17);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVmCarInfoMileage((ObservableField) object, fieldId);
            case 1:
                return onChangeVmCarInfoTurnSpeedAnge((ObservableFloat) object, fieldId);
            case 2:
                return onChangeVmCarInfoView((ObservableInt) object, fieldId);
            case 3:
                return onChangeVmCarInfoTurnSpeed((ObservableInt) object, fieldId);
            case 4:
                return onChangeVmCarInfoTempStr((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmCarInfoMileage(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeVmCarInfoTurnSpeedAnge(ObservableFloat VmCarInfoTurnSpeedAnge, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeVmCarInfoView(ObservableInt VmCarInfoView, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeVmCarInfoTurnSpeed(ObservableInt VmCarInfoTurnSpeed, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeVmCarInfoTempStr(ObservableField<String> observableField, int fieldId) {
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
        float vmCarInfoTurnSpeedAngeGet;
        ObservableField<String> vmCarInfoTempStr;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String vmCarInfoTempStrGet = null;
        ObservableField<String> vmCarInfoMileage = null;
        int vmCarInfoTurnSpeedGet = 0;
        AudiViewModel vm = this.mVm;
        ObservableFloat vmCarInfoTurnSpeedAnge = null;
        int vmCarInfoViewGet = 0;
        ObservableInt vmCarInfoView = null;
        String vmCarInfoMileageGet = null;
        String stringValueOfVmCarInfoTurnSpeed = null;
        ObservableInt vmCarInfoTurnSpeed = null;
        float vmCarInfoTurnSpeedAngeGet2 = 0.0f;
        if ((dirtyFlags & 100) != 0) {
            if (vm != null) {
                vmCarInfoView = vm.carInfoView;
            }
            updateRegistration(2, (Observable) vmCarInfoView);
            if (vmCarInfoView != null) {
                vmCarInfoViewGet = vmCarInfoView.get();
            }
        }
        if ((91 & dirtyFlags) != 0) {
            CarInfo vmCarInfo = AudiViewModel.carInfo;
            if ((dirtyFlags & 65) != 0) {
                if (vmCarInfo != null) {
                    vmCarInfoMileage = vmCarInfo.mileage;
                }
                updateRegistration(0, (Observable) vmCarInfoMileage);
                if (vmCarInfoMileage != null) {
                    vmCarInfoMileageGet = vmCarInfoMileage.get();
                }
            }
            if ((dirtyFlags & 66) != 0) {
                if (vmCarInfo != null) {
                    vmCarInfoTurnSpeedAnge = vmCarInfo.turnSpeedAnge;
                }
                updateRegistration(1, (Observable) vmCarInfoTurnSpeedAnge);
                if (vmCarInfoTurnSpeedAnge != null) {
                    vmCarInfoTurnSpeedAngeGet2 = vmCarInfoTurnSpeedAnge.get();
                }
            }
            if ((dirtyFlags & 72) != 0) {
                if (vmCarInfo != null) {
                    vmCarInfoTurnSpeed = vmCarInfo.turnSpeed;
                }
                updateRegistration(3, (Observable) vmCarInfoTurnSpeed);
                if (vmCarInfoTurnSpeed != null) {
                    vmCarInfoTurnSpeedGet = vmCarInfoTurnSpeed.get();
                }
                stringValueOfVmCarInfoTurnSpeed = String.valueOf(vmCarInfoTurnSpeedGet);
            }
            if ((dirtyFlags & 80) != 0) {
                if (vmCarInfo != null) {
                    vmCarInfoTempStr = vmCarInfo.tempStr;
                } else {
                    vmCarInfoTempStr = null;
                }
                updateRegistration(4, (Observable) vmCarInfoTempStr);
                if (vmCarInfoTempStr != null) {
                    vmCarInfoTempStrGet = vmCarInfoTempStr.get();
                    ObservableField<String> observableField = vmCarInfoTempStr;
                    vmCarInfoTurnSpeedAngeGet = vmCarInfoTurnSpeedAngeGet2;
                } else {
                    ObservableField<String> observableField2 = vmCarInfoTempStr;
                    vmCarInfoTurnSpeedAngeGet = vmCarInfoTurnSpeedAngeGet2;
                }
            } else {
                vmCarInfoTurnSpeedAngeGet = vmCarInfoTurnSpeedAngeGet2;
            }
        } else {
            vmCarInfoTurnSpeedAngeGet = 0.0f;
        }
        if ((dirtyFlags & 100) != 0) {
            this.KSWA4LRightTrafficInformation.setVisibility(vmCarInfoViewGet);
        }
        if ((dirtyFlags & 65) != 0) {
            TextViewBindingAdapter.setText(this.TvDrivingRange, vmCarInfoMileageGet);
        }
        if ((dirtyFlags & 80) != 0) {
            TextViewBindingAdapter.setText(this.TvTemp, vmCarInfoTempStrGet);
        }
        if ((dirtyFlags & 72) != 0) {
            TextViewBindingAdapter.setText(this.TvZhuanSu, stringValueOfVmCarInfoTurnSpeed);
        }
        if ((dirtyFlags & 66) != 0) {
            DashboardViewModel.setRotation(this.pointerImageView, vmCarInfoTurnSpeedAngeGet);
        }
    }
}
