package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.AudiViewModel;
import com.wits.ksw.launcher.model.DashboardViewModel;

/* loaded from: classes7.dex */
public class AudiMib3RightCarinfoBindingImpl extends AudiMib3RightCarinfoBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.imageView5, 5);
        sparseIntArray.put(C0899R.C0901id.textView4, 6);
        sparseIntArray.put(C0899R.C0901id.imageView9, 7);
        sparseIntArray.put(C0899R.C0901id.imageView10, 8);
        sparseIntArray.put(C0899R.C0901id.imageView11, 9);
    }

    public AudiMib3RightCarinfoBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private AudiMib3RightCarinfoBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 5, (ConstraintLayout) bindings[0], (TextView) bindings[3], (TextView) bindings[4], (TextView) bindings[2], (ImageView) bindings[8], (ImageView) bindings[9], (ImageView) bindings[5], (ImageView) bindings[7], (ImageView) bindings[1], (TextView) bindings[6]);
        this.mDirtyFlags = -1L;
        this.KSWA4LRightTrafficInformation.setTag(null);
        this.TvDrivingRange.setTag(null);
        this.TvTemp.setTag(null);
        this.TvZhuanSu.setTag(null);
        this.pointerImageView.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 64L;
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
        if (26 == variableId) {
            setVm((AudiViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.AudiMib3RightCarinfoBinding
    public void setVm(AudiViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(26);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
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

    private boolean onChangeVmCarInfoMileage(ObservableField<String> VmCarInfoMileage, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmCarInfoTurnSpeedAnge(ObservableFloat VmCarInfoTurnSpeedAnge, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmCarInfoView(ObservableInt VmCarInfoView, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmCarInfoTurnSpeed(ObservableInt VmCarInfoTurnSpeed, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmCarInfoTempStr(ObservableField<String> VmCarInfoTempStr, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        float vmCarInfoTurnSpeedAngeGet;
        ObservableField<String> vmCarInfoTempStr;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
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
            updateRegistration(2, vmCarInfoView);
            if (vmCarInfoView != null) {
                vmCarInfoViewGet = vmCarInfoView.get();
            }
        }
        if ((91 & dirtyFlags) == 0) {
            vmCarInfoTurnSpeedAngeGet = 0.0f;
        } else {
            com.wits.ksw.launcher.bean.CarInfo vmCarInfo = AudiViewModel.carInfo;
            if ((dirtyFlags & 65) != 0) {
                if (vmCarInfo != null) {
                    vmCarInfoMileage = vmCarInfo.mileage;
                }
                updateRegistration(0, vmCarInfoMileage);
                if (vmCarInfoMileage != null) {
                    vmCarInfoMileageGet = vmCarInfoMileage.get();
                }
            }
            if ((dirtyFlags & 66) != 0) {
                if (vmCarInfo != null) {
                    vmCarInfoTurnSpeedAnge = vmCarInfo.turnSpeedAnge;
                }
                updateRegistration(1, vmCarInfoTurnSpeedAnge);
                if (vmCarInfoTurnSpeedAnge != null) {
                    vmCarInfoTurnSpeedAngeGet2 = vmCarInfoTurnSpeedAnge.get();
                }
            }
            if ((dirtyFlags & 72) != 0) {
                if (vmCarInfo != null) {
                    vmCarInfoTurnSpeed = vmCarInfo.turnSpeed;
                }
                updateRegistration(3, vmCarInfoTurnSpeed);
                if (vmCarInfoTurnSpeed != null) {
                    vmCarInfoTurnSpeedGet = vmCarInfoTurnSpeed.get();
                }
                stringValueOfVmCarInfoTurnSpeed = String.valueOf(vmCarInfoTurnSpeedGet);
            }
            if ((dirtyFlags & 80) == 0) {
                vmCarInfoTurnSpeedAngeGet = vmCarInfoTurnSpeedAngeGet2;
            } else {
                if (vmCarInfo == null) {
                    vmCarInfoTempStr = null;
                } else {
                    vmCarInfoTempStr = vmCarInfo.tempStr;
                }
                updateRegistration(4, vmCarInfoTempStr);
                if (vmCarInfoTempStr != null) {
                    vmCarInfoTempStrGet = vmCarInfoTempStr.get();
                    vmCarInfoTurnSpeedAngeGet = vmCarInfoTurnSpeedAngeGet2;
                } else {
                    vmCarInfoTurnSpeedAngeGet = vmCarInfoTurnSpeedAngeGet2;
                }
            }
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
