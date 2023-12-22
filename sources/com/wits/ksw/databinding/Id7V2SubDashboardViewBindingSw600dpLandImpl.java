package com.wits.ksw.databinding;

import android.content.res.Resources;
import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.constraint.ConstraintLayout;
import android.support.p001v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.DashboardViewModel;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.CustomBmwImageView;

/* loaded from: classes7.dex */
public class Id7V2SubDashboardViewBindingSw600dpLandImpl extends Id7V2SubDashboardViewBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback138;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.textView2, 9);
        sparseIntArray.put(C0899R.C0901id.imageView5, 10);
    }

    public Id7V2SubDashboardViewBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }

    private Id7V2SubDashboardViewBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 7, (TextView) bindings[3], (ConstraintLayout) bindings[0], (CustomBmwImageView) bindings[1], (ImageView) bindings[10], (TextView) bindings[2], (ImageView) bindings[8], (TextView) bindings[7], (TextView) bindings[5], (TextView) bindings[6], (TextView) bindings[4], (TextView) bindings[9]);
        this.mDirtyFlags = -1L;
        this.brakeTextView.setTag(null);
        this.dashboardConstraintLayout.setTag(null);
        this.dashboardImageView.setTag(null);
        this.oilTextView.setTag(null);
        this.pointerImageView.setTag(null);
        this.rpmTextView.setTag(null);
        this.seatBeltTextView.setTag(null);
        this.speedTextView.setTag(null);
        this.tempTextView.setTag(null);
        setRootTag(root);
        this.mCallback138 = new OnClickListener(this, 1);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 256L;
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
        if (3 == variableId) {
            setCarViewModel((LauncherViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.Id7V2SubDashboardViewBinding
    public void setCarViewModel(LauncherViewModel CarViewModel) {
        this.mCarViewModel = CarViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        notifyPropertyChanged(3);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeCarViewModelCarInfoTempStr((ObservableField) object, fieldId);
            case 1:
                return onChangeCarViewModelCarInfoTurnSpeed((ObservableInt) object, fieldId);
            case 2:
                return onChangeCarViewModelCarInfoTurnSpeedAnge((ObservableFloat) object, fieldId);
            case 3:
                return onChangeCarViewModelCarInfoUnitStr((ObservableField) object, fieldId);
            case 4:
                return onChangeCarViewModelCarInfoBrakeValue((ObservableField) object, fieldId);
            case 5:
                return onChangeCarViewModelCarInfoOilValue((ObservableField) object, fieldId);
            case 6:
                return onChangeCarViewModelCarInfoSeatBeltpValue((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeCarViewModelCarInfoTempStr(ObservableField<String> CarViewModelCarInfoTempStr, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeCarViewModelCarInfoTurnSpeed(ObservableInt CarViewModelCarInfoTurnSpeed, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeCarViewModelCarInfoTurnSpeedAnge(ObservableFloat CarViewModelCarInfoTurnSpeedAnge, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeCarViewModelCarInfoUnitStr(ObservableField<String> CarViewModelCarInfoUnitStr, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeCarViewModelCarInfoBrakeValue(ObservableField<Boolean> CarViewModelCarInfoBrakeValue, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeCarViewModelCarInfoOilValue(ObservableField<String> CarViewModelCarInfoOilValue, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeCarViewModelCarInfoSeatBeltpValue(ObservableField<Boolean> CarViewModelCarInfoSeatBeltpValue, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        String carViewModelCarInfoOilValueGet;
        String stringValueOfCarViewModelCarInfoTurnSpeed;
        String carViewModelCarInfoTempStrGet;
        String carViewModelCarInfoSeatBeltpValueSeatBeltTextViewAndroidStringKswId7Seatbelt2SeatBeltTextViewAndroidStringKswId7Seatbelt1;
        boolean androidDatabindingViewDataBindingSafeUnboxCarViewModelCarInfoSeatBeltpValueGet;
        ObservableField<Boolean> carViewModelCarInfoSeatBeltpValue;
        ObservableField<Boolean> carViewModelCarInfoSeatBeltpValue2;
        long dirtyFlags2;
        Resources resources;
        int i;
        ObservableField<String> carViewModelCarInfoOilValue;
        ObservableField<Boolean> carViewModelCarInfoBrakeValue;
        ObservableField<Boolean> carViewModelCarInfoBrakeValue2;
        String string;
        ObservableField<String> carViewModelCarInfoUnitStr;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        Boolean carViewModelCarInfoSeatBeltpValueGet = null;
        ObservableField<String> carViewModelCarInfoTempStr = null;
        ObservableInt carViewModelCarInfoTurnSpeed = null;
        ObservableFloat carViewModelCarInfoTurnSpeedAnge = null;
        String carViewModelCarInfoBrakeValueBrakeTextViewAndroidStringKswId7Brake2BrakeTextViewAndroidStringKswId7Brake1 = null;
        int carViewModelCarInfoTurnSpeedGet = 0;
        LauncherViewModel launcherViewModel = this.mCarViewModel;
        String carViewModelCarInfoUnitStrGet = null;
        float carViewModelCarInfoTurnSpeedAngeGet = 0.0f;
        String carViewModelCarInfoOilValueGet2 = null;
        Boolean carViewModelCarInfoBrakeValueGet = null;
        String stringValueOfCarViewModelCarInfoTurnSpeed2 = null;
        String carViewModelCarInfoTempStrGet2 = null;
        if ((dirtyFlags & 383) == 0) {
            carViewModelCarInfoOilValueGet = null;
            stringValueOfCarViewModelCarInfoTurnSpeed = null;
            carViewModelCarInfoTempStrGet = null;
            carViewModelCarInfoSeatBeltpValueSeatBeltTextViewAndroidStringKswId7Seatbelt2SeatBeltTextViewAndroidStringKswId7Seatbelt1 = null;
        } else {
            com.wits.ksw.launcher.bean.CarInfo carViewModelCarInfo = LauncherViewModel.carInfo;
            if ((dirtyFlags & 257) != 0) {
                if (carViewModelCarInfo != null) {
                    carViewModelCarInfoTempStr = carViewModelCarInfo.tempStr;
                }
                updateRegistration(0, carViewModelCarInfoTempStr);
                if (carViewModelCarInfoTempStr != null) {
                    carViewModelCarInfoTempStrGet2 = carViewModelCarInfoTempStr.get();
                }
            }
            if ((dirtyFlags & 258) != 0) {
                if (carViewModelCarInfo != null) {
                    carViewModelCarInfoTurnSpeed = carViewModelCarInfo.turnSpeed;
                }
                updateRegistration(1, carViewModelCarInfoTurnSpeed);
                if (carViewModelCarInfoTurnSpeed != null) {
                    carViewModelCarInfoTurnSpeedGet = carViewModelCarInfoTurnSpeed.get();
                }
                stringValueOfCarViewModelCarInfoTurnSpeed2 = String.valueOf(carViewModelCarInfoTurnSpeedGet);
            }
            if ((dirtyFlags & 260) != 0) {
                if (carViewModelCarInfo != null) {
                    carViewModelCarInfoTurnSpeedAnge = carViewModelCarInfo.turnSpeedAnge;
                }
                updateRegistration(2, carViewModelCarInfoTurnSpeedAnge);
                if (carViewModelCarInfoTurnSpeedAnge != null) {
                    carViewModelCarInfoTurnSpeedAngeGet = carViewModelCarInfoTurnSpeedAnge.get();
                }
            }
            if ((dirtyFlags & 264) == 0) {
                androidDatabindingViewDataBindingSafeUnboxCarViewModelCarInfoSeatBeltpValueGet = false;
            } else {
                if (carViewModelCarInfo == null) {
                    carViewModelCarInfoUnitStr = null;
                } else {
                    carViewModelCarInfoUnitStr = carViewModelCarInfo.unitStr;
                }
                androidDatabindingViewDataBindingSafeUnboxCarViewModelCarInfoSeatBeltpValueGet = false;
                updateRegistration(3, carViewModelCarInfoUnitStr);
                if (carViewModelCarInfoUnitStr != null) {
                    carViewModelCarInfoUnitStrGet = carViewModelCarInfoUnitStr.get();
                }
            }
            if ((dirtyFlags & 272) != 0) {
                if (carViewModelCarInfo == null) {
                    carViewModelCarInfoBrakeValue = null;
                } else {
                    carViewModelCarInfoBrakeValue = carViewModelCarInfo.brakeValue;
                }
                updateRegistration(4, carViewModelCarInfoBrakeValue);
                if (carViewModelCarInfoBrakeValue != null) {
                    Boolean carViewModelCarInfoBrakeValueGet2 = carViewModelCarInfoBrakeValue.get();
                    carViewModelCarInfoBrakeValueGet = carViewModelCarInfoBrakeValueGet2;
                }
                boolean androidDatabindingViewDataBindingSafeUnboxCarViewModelCarInfoBrakeValueGet = ViewDataBinding.safeUnbox(carViewModelCarInfoBrakeValueGet);
                if ((dirtyFlags & 272) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxCarViewModelCarInfoBrakeValueGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    } else {
                        dirtyFlags |= 512;
                    }
                }
                if (androidDatabindingViewDataBindingSafeUnboxCarViewModelCarInfoBrakeValueGet) {
                    carViewModelCarInfoBrakeValue2 = carViewModelCarInfoBrakeValue;
                    string = this.brakeTextView.getResources().getString(C0899R.string.ksw_id7_brake2);
                } else {
                    carViewModelCarInfoBrakeValue2 = carViewModelCarInfoBrakeValue;
                    string = this.brakeTextView.getResources().getString(C0899R.string.ksw_id7_brake1);
                }
                carViewModelCarInfoBrakeValueBrakeTextViewAndroidStringKswId7Brake2BrakeTextViewAndroidStringKswId7Brake1 = string;
            }
            if ((dirtyFlags & 288) != 0) {
                if (carViewModelCarInfo == null) {
                    carViewModelCarInfoOilValue = null;
                } else {
                    carViewModelCarInfoOilValue = carViewModelCarInfo.oilValue;
                }
                updateRegistration(5, carViewModelCarInfoOilValue);
                if (carViewModelCarInfoOilValue != null) {
                    carViewModelCarInfoOilValueGet2 = carViewModelCarInfoOilValue.get();
                }
            }
            if ((dirtyFlags & 320) == 0) {
                carViewModelCarInfoOilValueGet = carViewModelCarInfoOilValueGet2;
                stringValueOfCarViewModelCarInfoTurnSpeed = stringValueOfCarViewModelCarInfoTurnSpeed2;
                carViewModelCarInfoTempStrGet = carViewModelCarInfoTempStrGet2;
                carViewModelCarInfoSeatBeltpValueSeatBeltTextViewAndroidStringKswId7Seatbelt2SeatBeltTextViewAndroidStringKswId7Seatbelt1 = null;
            } else {
                if (carViewModelCarInfo == null) {
                    carViewModelCarInfoSeatBeltpValue = null;
                } else {
                    carViewModelCarInfoSeatBeltpValue = carViewModelCarInfo.seatBeltpValue;
                }
                updateRegistration(6, carViewModelCarInfoSeatBeltpValue);
                if (carViewModelCarInfoSeatBeltpValue != null) {
                    Boolean carViewModelCarInfoSeatBeltpValueGet2 = carViewModelCarInfoSeatBeltpValue.get();
                    carViewModelCarInfoSeatBeltpValueGet = carViewModelCarInfoSeatBeltpValueGet2;
                }
                boolean androidDatabindingViewDataBindingSafeUnboxCarViewModelCarInfoSeatBeltpValueGet2 = ViewDataBinding.safeUnbox(carViewModelCarInfoSeatBeltpValueGet);
                if ((dirtyFlags & 320) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxCarViewModelCarInfoSeatBeltpValueGet2) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                    }
                }
                if (androidDatabindingViewDataBindingSafeUnboxCarViewModelCarInfoSeatBeltpValueGet2) {
                    carViewModelCarInfoSeatBeltpValue2 = carViewModelCarInfoSeatBeltpValue;
                    resources = this.seatBeltTextView.getResources();
                    dirtyFlags2 = dirtyFlags;
                    i = C0899R.string.ksw_id7_seatbelt2;
                } else {
                    carViewModelCarInfoSeatBeltpValue2 = carViewModelCarInfoSeatBeltpValue;
                    dirtyFlags2 = dirtyFlags;
                    resources = this.seatBeltTextView.getResources();
                    i = C0899R.string.ksw_id7_seatbelt1;
                }
                String carViewModelCarInfoSeatBeltpValueSeatBeltTextViewAndroidStringKswId7Seatbelt2SeatBeltTextViewAndroidStringKswId7Seatbelt12 = resources.getString(i);
                carViewModelCarInfoTempStrGet = carViewModelCarInfoTempStrGet2;
                dirtyFlags = dirtyFlags2;
                carViewModelCarInfoOilValueGet = carViewModelCarInfoOilValueGet2;
                stringValueOfCarViewModelCarInfoTurnSpeed = stringValueOfCarViewModelCarInfoTurnSpeed2;
                carViewModelCarInfoSeatBeltpValueSeatBeltTextViewAndroidStringKswId7Seatbelt2SeatBeltTextViewAndroidStringKswId7Seatbelt1 = carViewModelCarInfoSeatBeltpValueSeatBeltTextViewAndroidStringKswId7Seatbelt2SeatBeltTextViewAndroidStringKswId7Seatbelt12;
            }
        }
        if ((dirtyFlags & 272) != 0) {
            TextViewBindingAdapter.setText(this.brakeTextView, carViewModelCarInfoBrakeValueBrakeTextViewAndroidStringKswId7Brake2BrakeTextViewAndroidStringKswId7Brake1);
        }
        if ((dirtyFlags & 256) != 0) {
            this.dashboardImageView.setOnClickListener(this.mCallback138);
        }
        if ((dirtyFlags & 288) != 0) {
            TextViewBindingAdapter.setText(this.oilTextView, carViewModelCarInfoOilValueGet);
        }
        if ((dirtyFlags & 260) != 0) {
            DashboardViewModel.setRotation(this.pointerImageView, carViewModelCarInfoTurnSpeedAngeGet);
        }
        if ((dirtyFlags & 264) != 0) {
            TextViewBindingAdapter.setText(this.rpmTextView, carViewModelCarInfoUnitStrGet);
        }
        if ((dirtyFlags & 320) != 0) {
            TextViewBindingAdapter.setText(this.seatBeltTextView, carViewModelCarInfoSeatBeltpValueSeatBeltTextViewAndroidStringKswId7Seatbelt2SeatBeltTextViewAndroidStringKswId7Seatbelt1);
        }
        if ((dirtyFlags & 258) != 0) {
            TextViewBindingAdapter.setText(this.speedTextView, stringValueOfCarViewModelCarInfoTurnSpeed);
        }
        if ((dirtyFlags & 257) != 0) {
            TextViewBindingAdapter.setText(this.tempTextView, carViewModelCarInfoTempStrGet);
        }
    }

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        LauncherViewModel carViewModel = this.mCarViewModel;
        boolean carViewModelJavaLangObjectNull = carViewModel != null;
        if (carViewModelJavaLangObjectNull) {
            carViewModel.openDashboard(callbackArg_0);
        }
    }
}
