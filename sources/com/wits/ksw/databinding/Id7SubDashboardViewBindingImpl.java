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
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.bean.CarInfo;
import com.wits.ksw.launcher.model.DashboardViewModel;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class Id7SubDashboardViewBindingImpl extends Id7SubDashboardViewBinding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @Nullable
    private final View.OnClickListener mCallback66;
    private long mDirtyFlags;

    static {
        sViewsWithIds.put(R.id.textView2, 8);
        sViewsWithIds.put(R.id.imageView5, 9);
        sViewsWithIds.put(R.id.rpmTextView, 10);
    }

    public Id7SubDashboardViewBindingImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private Id7SubDashboardViewBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 6, bindings[3], bindings[0], bindings[1], bindings[9], bindings[2], bindings[7], bindings[10], bindings[5], bindings[6], bindings[4], bindings[8]);
        this.mDirtyFlags = -1;
        this.brakeTextView.setTag((Object) null);
        this.dashboardConstraintLayout.setTag((Object) null);
        this.dashboardImageView.setTag((Object) null);
        this.oilTextView.setTag((Object) null);
        this.pointerImageView.setTag((Object) null);
        this.seatBeltTextView.setTag((Object) null);
        this.speedTextView.setTag((Object) null);
        this.tempTextView.setTag((Object) null);
        setRootTag(root);
        this.mCallback66 = new OnClickListener(this, 1);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 128;
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
        if (15 != variableId) {
            return false;
        }
        setCarViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setCarViewModel(@Nullable LauncherViewModel CarViewModel) {
        this.mCarViewModel = CarViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(15);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeCarViewModelCarInfoBrakeValue((ObservableField) object, fieldId);
            case 1:
                return onChangeCarViewModelCarInfoTurnSpeedAnge((ObservableFloat) object, fieldId);
            case 2:
                return onChangeCarViewModelCarInfoOilValue((ObservableInt) object, fieldId);
            case 3:
                return onChangeCarViewModelCarInfoSeatBeltpValue((ObservableField) object, fieldId);
            case 4:
                return onChangeCarViewModelCarInfoTempStr((ObservableField) object, fieldId);
            case 5:
                return onChangeCarViewModelCarInfoTurnSpeed((ObservableInt) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeCarViewModelCarInfoBrakeValue(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeCarViewModelCarInfoTurnSpeedAnge(ObservableFloat CarViewModelCarInfoTurnSpeedAnge, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeCarViewModelCarInfoOilValue(ObservableInt CarViewModelCarInfoOilValue, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeCarViewModelCarInfoSeatBeltpValue(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeCarViewModelCarInfoTempStr(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeCarViewModelCarInfoTurnSpeed(ObservableInt CarViewModelCarInfoTurnSpeed, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        Boolean carViewModelCarInfoBrakeValueGet;
        String carViewModelCarInfoTempStrGet;
        String carViewModelCarInfoSeatBeltpValueSeatBeltTextViewAndroidStringKswId7Seatbelt2SeatBeltTextViewAndroidStringKswId7Seatbelt1;
        String stringValueOfCarViewModelCarInfoOilValueJavaLangStringL;
        String carViewModelCarInfoBrakeValueBrakeTextViewAndroidStringKswId7Brake2BrakeTextViewAndroidStringKswId7Brake1;
        String stringValueOfCarViewModelCarInfoOilValueJavaLangStringL2;
        String stringValueOfCarViewModelCarInfoOilValueJavaLangStringL3;
        ObservableInt carViewModelCarInfoTurnSpeed;
        ObservableField<String> carViewModelCarInfoTempStr;
        long dirtyFlags2;
        String str;
        long dirtyFlags3;
        String str2;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        float carViewModelCarInfoTurnSpeedAngeGet = 0.0f;
        Boolean carViewModelCarInfoBrakeValueGet2 = null;
        ObservableField<Boolean> carViewModelCarInfoBrakeValue = null;
        Boolean carViewModelCarInfoSeatBeltpValueGet = null;
        ObservableFloat carViewModelCarInfoTurnSpeedAnge = null;
        String stringValueOfCarViewModelCarInfoTurnSpeed = null;
        ObservableInt carViewModelCarInfoOilValue = null;
        ObservableField<Boolean> carViewModelCarInfoSeatBeltpValue = null;
        boolean androidDatabindingViewDataBindingSafeUnboxCarViewModelCarInfoSeatBeltpValueGet = false;
        int carViewModelCarInfoTurnSpeedGet = 0;
        LauncherViewModel carViewModel = this.mCarViewModel;
        boolean androidDatabindingViewDataBindingSafeUnboxCarViewModelCarInfoBrakeValueGet = false;
        String carViewModelCarInfoTempStrGet2 = null;
        String carViewModelCarInfoBrakeValueBrakeTextViewAndroidStringKswId7Brake2BrakeTextViewAndroidStringKswId7Brake12 = null;
        int carViewModelCarInfoOilValueGet = 0;
        String carViewModelCarInfoSeatBeltpValueSeatBeltTextViewAndroidStringKswId7Seatbelt2SeatBeltTextViewAndroidStringKswId7Seatbelt12 = null;
        if ((dirtyFlags & 191) != 0) {
            CarInfo carViewModelCarInfo = LauncherViewModel.carInfo;
            if ((dirtyFlags & 129) != 0) {
                if (carViewModelCarInfo != null) {
                    carViewModelCarInfoBrakeValue = carViewModelCarInfo.brakeValue;
                }
                LauncherViewModel launcherViewModel = carViewModel;
                updateRegistration(0, (Observable) carViewModelCarInfoBrakeValue);
                if (carViewModelCarInfoBrakeValue != null) {
                    carViewModelCarInfoBrakeValueGet2 = carViewModelCarInfoBrakeValue.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxCarViewModelCarInfoBrakeValueGet2 = ViewDataBinding.safeUnbox(carViewModelCarInfoBrakeValueGet2);
                if ((dirtyFlags & 129) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxCarViewModelCarInfoBrakeValueGet2) {
                        dirtyFlags |= 512;
                    } else {
                        dirtyFlags |= 256;
                    }
                }
                if (androidDatabindingViewDataBindingSafeUnboxCarViewModelCarInfoBrakeValueGet2) {
                    dirtyFlags3 = dirtyFlags;
                    str2 = this.brakeTextView.getResources().getString(R.string.ksw_id7_brake2);
                } else {
                    dirtyFlags3 = dirtyFlags;
                    str2 = this.brakeTextView.getResources().getString(R.string.ksw_id7_brake1);
                }
                carViewModelCarInfoBrakeValueBrakeTextViewAndroidStringKswId7Brake2BrakeTextViewAndroidStringKswId7Brake12 = str2;
                androidDatabindingViewDataBindingSafeUnboxCarViewModelCarInfoBrakeValueGet = androidDatabindingViewDataBindingSafeUnboxCarViewModelCarInfoBrakeValueGet2;
                dirtyFlags = dirtyFlags3;
            }
            if ((dirtyFlags & 130) != 0) {
                if (carViewModelCarInfo != null) {
                    carViewModelCarInfoTurnSpeedAnge = carViewModelCarInfo.turnSpeedAnge;
                }
                updateRegistration(1, (Observable) carViewModelCarInfoTurnSpeedAnge);
                if (carViewModelCarInfoTurnSpeedAnge != null) {
                    carViewModelCarInfoTurnSpeedAngeGet = carViewModelCarInfoTurnSpeedAnge.get();
                }
            }
            if ((dirtyFlags & 132) != 0) {
                if (carViewModelCarInfo != null) {
                    carViewModelCarInfoOilValue = carViewModelCarInfo.oilValue;
                }
                updateRegistration(2, (Observable) carViewModelCarInfoOilValue);
                if (carViewModelCarInfoOilValue != null) {
                    carViewModelCarInfoOilValueGet = carViewModelCarInfoOilValue.get();
                }
                String stringValueOfCarViewModelCarInfoOilValue = String.valueOf(carViewModelCarInfoOilValueGet);
                boolean z = androidDatabindingViewDataBindingSafeUnboxCarViewModelCarInfoBrakeValueGet;
                StringBuilder sb = new StringBuilder();
                sb.append(stringValueOfCarViewModelCarInfoOilValue);
                String stringValueOfCarViewModelCarInfoOilValue2 = stringValueOfCarViewModelCarInfoOilValue;
                sb.append("L");
                stringValueOfCarViewModelCarInfoOilValueJavaLangStringL2 = sb.toString();
                String str3 = stringValueOfCarViewModelCarInfoOilValue2;
            } else {
                stringValueOfCarViewModelCarInfoOilValueJavaLangStringL2 = null;
            }
            if ((dirtyFlags & 136) != 0) {
                if (carViewModelCarInfo != null) {
                    carViewModelCarInfoSeatBeltpValue = carViewModelCarInfo.seatBeltpValue;
                }
                updateRegistration(3, (Observable) carViewModelCarInfoSeatBeltpValue);
                if (carViewModelCarInfoSeatBeltpValue != null) {
                    carViewModelCarInfoSeatBeltpValueGet = carViewModelCarInfoSeatBeltpValue.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxCarViewModelCarInfoSeatBeltpValueGet2 = ViewDataBinding.safeUnbox(carViewModelCarInfoSeatBeltpValueGet);
                if ((dirtyFlags & 136) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxCarViewModelCarInfoSeatBeltpValueGet2) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    }
                }
                if (androidDatabindingViewDataBindingSafeUnboxCarViewModelCarInfoSeatBeltpValueGet2) {
                    dirtyFlags2 = dirtyFlags;
                    str = this.seatBeltTextView.getResources().getString(R.string.ksw_id7_seatbelt2);
                } else {
                    dirtyFlags2 = dirtyFlags;
                    str = this.seatBeltTextView.getResources().getString(R.string.ksw_id7_seatbelt1);
                }
                carViewModelCarInfoSeatBeltpValueSeatBeltTextViewAndroidStringKswId7Seatbelt2SeatBeltTextViewAndroidStringKswId7Seatbelt12 = str;
                androidDatabindingViewDataBindingSafeUnboxCarViewModelCarInfoSeatBeltpValueGet = androidDatabindingViewDataBindingSafeUnboxCarViewModelCarInfoSeatBeltpValueGet2;
                dirtyFlags = dirtyFlags2;
            }
            if ((dirtyFlags & 144) != 0) {
                if (carViewModelCarInfo != null) {
                    carViewModelCarInfoTempStr = carViewModelCarInfo.tempStr;
                } else {
                    carViewModelCarInfoTempStr = null;
                }
                stringValueOfCarViewModelCarInfoOilValueJavaLangStringL3 = stringValueOfCarViewModelCarInfoOilValueJavaLangStringL2;
                updateRegistration(4, (Observable) carViewModelCarInfoTempStr);
                if (carViewModelCarInfoTempStr != null) {
                    carViewModelCarInfoTempStrGet2 = carViewModelCarInfoTempStr.get();
                }
            } else {
                stringValueOfCarViewModelCarInfoOilValueJavaLangStringL3 = stringValueOfCarViewModelCarInfoOilValueJavaLangStringL2;
            }
            if ((dirtyFlags & 160) != 0) {
                if (carViewModelCarInfo != null) {
                    carViewModelCarInfoTurnSpeed = carViewModelCarInfo.turnSpeed;
                } else {
                    carViewModelCarInfoTurnSpeed = null;
                }
                updateRegistration(5, (Observable) carViewModelCarInfoTurnSpeed);
                if (carViewModelCarInfoTurnSpeed != null) {
                    carViewModelCarInfoTurnSpeedGet = carViewModelCarInfoTurnSpeed.get();
                }
                stringValueOfCarViewModelCarInfoTurnSpeed = String.valueOf(carViewModelCarInfoTurnSpeedGet);
                CarInfo carInfo = carViewModelCarInfo;
                ObservableInt observableInt = carViewModelCarInfoTurnSpeed;
            } else {
                CarInfo carInfo2 = carViewModelCarInfo;
            }
            carViewModelCarInfoBrakeValueBrakeTextViewAndroidStringKswId7Brake2BrakeTextViewAndroidStringKswId7Brake1 = carViewModelCarInfoBrakeValueBrakeTextViewAndroidStringKswId7Brake2BrakeTextViewAndroidStringKswId7Brake12;
            carViewModelCarInfoSeatBeltpValueSeatBeltTextViewAndroidStringKswId7Seatbelt2SeatBeltTextViewAndroidStringKswId7Seatbelt1 = carViewModelCarInfoSeatBeltpValueSeatBeltTextViewAndroidStringKswId7Seatbelt2SeatBeltTextViewAndroidStringKswId7Seatbelt12;
            stringValueOfCarViewModelCarInfoOilValueJavaLangStringL = stringValueOfCarViewModelCarInfoOilValueJavaLangStringL3;
            ObservableField<Boolean> observableField = carViewModelCarInfoBrakeValue;
            carViewModelCarInfoBrakeValueGet = carViewModelCarInfoBrakeValueGet2;
            carViewModelCarInfoTempStrGet = carViewModelCarInfoTempStrGet2;
            ObservableField<Boolean> carViewModelCarInfoBrakeValue2 = observableField;
        } else {
            LauncherViewModel launcherViewModel2 = carViewModel;
            carViewModelCarInfoBrakeValueBrakeTextViewAndroidStringKswId7Brake2BrakeTextViewAndroidStringKswId7Brake1 = null;
            carViewModelCarInfoSeatBeltpValueSeatBeltTextViewAndroidStringKswId7Seatbelt2SeatBeltTextViewAndroidStringKswId7Seatbelt1 = null;
            stringValueOfCarViewModelCarInfoOilValueJavaLangStringL = null;
            carViewModelCarInfoBrakeValueGet = null;
            carViewModelCarInfoTempStrGet = null;
        }
        if ((dirtyFlags & 129) != 0) {
            Boolean bool = carViewModelCarInfoBrakeValueGet;
            TextViewBindingAdapter.setText(this.brakeTextView, carViewModelCarInfoBrakeValueBrakeTextViewAndroidStringKswId7Brake2BrakeTextViewAndroidStringKswId7Brake1);
        }
        if ((dirtyFlags & 128) != 0) {
            String str4 = carViewModelCarInfoBrakeValueBrakeTextViewAndroidStringKswId7Brake2BrakeTextViewAndroidStringKswId7Brake1;
            this.dashboardImageView.setOnClickListener(this.mCallback66);
        }
        if ((dirtyFlags & 132) != 0) {
            TextViewBindingAdapter.setText(this.oilTextView, stringValueOfCarViewModelCarInfoOilValueJavaLangStringL);
        }
        if ((dirtyFlags & 130) != 0) {
            DashboardViewModel.setRotation(this.pointerImageView, carViewModelCarInfoTurnSpeedAngeGet);
        }
        if ((dirtyFlags & 136) != 0) {
            TextViewBindingAdapter.setText(this.seatBeltTextView, carViewModelCarInfoSeatBeltpValueSeatBeltTextViewAndroidStringKswId7Seatbelt2SeatBeltTextViewAndroidStringKswId7Seatbelt1);
        }
        if ((dirtyFlags & 160) != 0) {
            TextViewBindingAdapter.setText(this.speedTextView, stringValueOfCarViewModelCarInfoTurnSpeed);
        }
        if ((dirtyFlags & 144) != 0) {
            TextViewBindingAdapter.setText(this.tempTextView, carViewModelCarInfoTempStrGet);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        LauncherViewModel carViewModel = this.mCarViewModel;
        if (carViewModel != null) {
            carViewModel.openDashboard(callbackArg_0);
        }
    }
}
