package com.wits.ksw.databinding;

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
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;
import com.wits.ksw.launcher.model.DashboardViewModel;
import com.wits.ksw.launcher.view.CustomBmwImageView;

/* loaded from: classes7.dex */
public class AlsId7SubDashboardViewBindingSw600dpLandImpl extends AlsId7SubDashboardViewBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback440;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.title, 7);
    }

    public AlsId7SubDashboardViewBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }

    private AlsId7SubDashboardViewBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 5, (TextView) bindings[4], (ConstraintLayout) bindings[0], (CustomBmwImageView) bindings[1], (TextView) bindings[2], (ImageView) bindings[3], (TextView) bindings[5], (TextView) bindings[6], (TextView) bindings[7]);
        this.mDirtyFlags = -1L;
        this.brakeTextView.setTag(null);
        this.dashboardConstraintLayout.setTag(null);
        this.dashboardImageView.setTag(null);
        this.oilTextView.setTag(null);
        this.pointerImageView.setTag(null);
        this.seatBeltTextView.setTag(null);
        this.speedTextView.setTag(null);
        setRootTag(root);
        this.mCallback440 = new OnClickListener(this, 1);
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
        if (4 == variableId) {
            setDashVideoViewModel((AlsID7ViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.AlsId7SubDashboardViewBinding
    public void setDashVideoViewModel(AlsID7ViewModel DashVideoViewModel) {
        this.mDashVideoViewModel = DashVideoViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(4);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeDashVideoViewModelCarInfoTurnSpeed((ObservableInt) object, fieldId);
            case 1:
                return onChangeDashVideoViewModelCarInfoBrakeValue((ObservableField) object, fieldId);
            case 2:
                return onChangeDashVideoViewModelCarInfoOilValue((ObservableField) object, fieldId);
            case 3:
                return onChangeDashVideoViewModelCarInfoSeatBeltpValue((ObservableField) object, fieldId);
            case 4:
                return onChangeDashVideoViewModelCarInfoTurnSpeedAnge((ObservableFloat) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeDashVideoViewModelCarInfoTurnSpeed(ObservableInt DashVideoViewModelCarInfoTurnSpeed, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeDashVideoViewModelCarInfoBrakeValue(ObservableField<Boolean> DashVideoViewModelCarInfoBrakeValue, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeDashVideoViewModelCarInfoOilValue(ObservableField<String> DashVideoViewModelCarInfoOilValue, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeDashVideoViewModelCarInfoSeatBeltpValue(ObservableField<Boolean> DashVideoViewModelCarInfoSeatBeltpValue, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeDashVideoViewModelCarInfoTurnSpeedAnge(ObservableFloat DashVideoViewModelCarInfoTurnSpeedAnge, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:106:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x01de  */
    @Override // android.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void executeBindings() {
        long dirtyFlags;
        float dashVideoViewModelCarInfoTurnSpeedAngeGet;
        View.OnFocusChangeListener dashVideoViewModelDashViewFocusChangeListener;
        String dashVideoViewModelCarInfoOilValueGet;
        String dashVideoViewModelCarInfoOilValueGet2;
        String dashVideoViewModelCarInfoOilValueGet3;
        ObservableFloat dashVideoViewModelCarInfoTurnSpeedAnge;
        boolean androidDatabindingViewDataBindingSafeUnboxDashVideoViewModelCarInfoSeatBeltpValueGet;
        String string;
        boolean androidDatabindingViewDataBindingSafeUnboxDashVideoViewModelCarInfoBrakeValueGet;
        String string2;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String dashVideoViewModelCarInfoOilValueGet4 = null;
        ObservableInt dashVideoViewModelCarInfoTurnSpeed = null;
        ObservableField<Boolean> dashVideoViewModelCarInfoBrakeValue = null;
        String dashVideoViewModelCarInfoSeatBeltpValueSeatBeltTextViewAndroidStringKswId7Seatbelt2SeatBeltTextViewAndroidStringKswId7Seatbelt1 = null;
        String stringValueOfDashVideoViewModelCarInfoTurnSpeed = null;
        ObservableField<String> dashVideoViewModelCarInfoOilValue = null;
        String dashVideoViewModelCarInfoBrakeValueBrakeTextViewAndroidStringKswId7Brake2BrakeTextViewAndroidStringKswId7Brake1 = null;
        ObservableField<Boolean> dashVideoViewModelCarInfoSeatBeltpValue = null;
        AlsID7ViewModel dashVideoViewModel = this.mDashVideoViewModel;
        int dashVideoViewModelCarInfoTurnSpeedGet = 0;
        Boolean dashVideoViewModelCarInfoBrakeValueGet = null;
        Boolean dashVideoViewModelCarInfoSeatBeltpValueGet = null;
        if ((dirtyFlags & 95) == 0) {
            dashVideoViewModelCarInfoTurnSpeedAngeGet = 0.0f;
        } else {
            com.wits.ksw.launcher.bean.CarInfo dashVideoViewModelCarInfo = AlsID7ViewModel.carInfo;
            if ((dirtyFlags & 65) == 0) {
                dashVideoViewModelCarInfoOilValueGet = null;
            } else {
                if (dashVideoViewModelCarInfo != null) {
                    dashVideoViewModelCarInfoTurnSpeed = dashVideoViewModelCarInfo.turnSpeed;
                }
                dashVideoViewModelCarInfoOilValueGet = null;
                updateRegistration(0, dashVideoViewModelCarInfoTurnSpeed);
                if (dashVideoViewModelCarInfoTurnSpeed != null) {
                    dashVideoViewModelCarInfoTurnSpeedGet = dashVideoViewModelCarInfoTurnSpeed.get();
                }
                stringValueOfDashVideoViewModelCarInfoTurnSpeed = String.valueOf(dashVideoViewModelCarInfoTurnSpeedGet);
            }
            if ((dirtyFlags & 66) != 0) {
                if (dashVideoViewModelCarInfo != null) {
                    dashVideoViewModelCarInfoBrakeValue = dashVideoViewModelCarInfo.brakeValue;
                }
                updateRegistration(1, dashVideoViewModelCarInfoBrakeValue);
                if (dashVideoViewModelCarInfoBrakeValue != null) {
                    Boolean dashVideoViewModelCarInfoBrakeValueGet2 = dashVideoViewModelCarInfoBrakeValue.get();
                    dashVideoViewModelCarInfoBrakeValueGet = dashVideoViewModelCarInfoBrakeValueGet2;
                }
                boolean androidDatabindingViewDataBindingSafeUnboxDashVideoViewModelCarInfoBrakeValueGet2 = ViewDataBinding.safeUnbox(dashVideoViewModelCarInfoBrakeValueGet);
                if ((dirtyFlags & 66) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxDashVideoViewModelCarInfoBrakeValueGet2) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    } else {
                        dirtyFlags |= 512;
                    }
                }
                if (androidDatabindingViewDataBindingSafeUnboxDashVideoViewModelCarInfoBrakeValueGet2) {
                    androidDatabindingViewDataBindingSafeUnboxDashVideoViewModelCarInfoBrakeValueGet = androidDatabindingViewDataBindingSafeUnboxDashVideoViewModelCarInfoBrakeValueGet2;
                    string2 = this.brakeTextView.getResources().getString(C0899R.string.ksw_id7_brake2);
                } else {
                    androidDatabindingViewDataBindingSafeUnboxDashVideoViewModelCarInfoBrakeValueGet = androidDatabindingViewDataBindingSafeUnboxDashVideoViewModelCarInfoBrakeValueGet2;
                    string2 = this.brakeTextView.getResources().getString(C0899R.string.ksw_id7_brake1);
                }
                dashVideoViewModelCarInfoBrakeValueBrakeTextViewAndroidStringKswId7Brake2BrakeTextViewAndroidStringKswId7Brake1 = string2;
            }
            if ((dirtyFlags & 68) == 0) {
                dashVideoViewModelCarInfoOilValueGet2 = dashVideoViewModelCarInfoOilValueGet;
            } else {
                if (dashVideoViewModelCarInfo != null) {
                    dashVideoViewModelCarInfoOilValue = dashVideoViewModelCarInfo.oilValue;
                }
                updateRegistration(2, dashVideoViewModelCarInfoOilValue);
                if (dashVideoViewModelCarInfoOilValue == null) {
                    dashVideoViewModelCarInfoOilValueGet2 = dashVideoViewModelCarInfoOilValueGet;
                } else {
                    dashVideoViewModelCarInfoOilValueGet2 = dashVideoViewModelCarInfoOilValue.get();
                }
            }
            if ((dirtyFlags & 72) == 0) {
                dashVideoViewModelCarInfoOilValueGet3 = dashVideoViewModelCarInfoOilValueGet2;
            } else {
                if (dashVideoViewModelCarInfo != null) {
                    dashVideoViewModelCarInfoSeatBeltpValue = dashVideoViewModelCarInfo.seatBeltpValue;
                }
                dashVideoViewModelCarInfoOilValueGet3 = dashVideoViewModelCarInfoOilValueGet2;
                updateRegistration(3, dashVideoViewModelCarInfoSeatBeltpValue);
                if (dashVideoViewModelCarInfoSeatBeltpValue != null) {
                    Boolean dashVideoViewModelCarInfoSeatBeltpValueGet2 = dashVideoViewModelCarInfoSeatBeltpValue.get();
                    dashVideoViewModelCarInfoSeatBeltpValueGet = dashVideoViewModelCarInfoSeatBeltpValueGet2;
                }
                boolean androidDatabindingViewDataBindingSafeUnboxDashVideoViewModelCarInfoSeatBeltpValueGet2 = ViewDataBinding.safeUnbox(dashVideoViewModelCarInfoSeatBeltpValueGet);
                if ((dirtyFlags & 72) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxDashVideoViewModelCarInfoSeatBeltpValueGet2) {
                        dirtyFlags |= 256;
                    } else {
                        dirtyFlags |= 128;
                    }
                }
                if (androidDatabindingViewDataBindingSafeUnboxDashVideoViewModelCarInfoSeatBeltpValueGet2) {
                    androidDatabindingViewDataBindingSafeUnboxDashVideoViewModelCarInfoSeatBeltpValueGet = androidDatabindingViewDataBindingSafeUnboxDashVideoViewModelCarInfoSeatBeltpValueGet2;
                    string = this.seatBeltTextView.getResources().getString(C0899R.string.ksw_id7_seatbelt2);
                } else {
                    androidDatabindingViewDataBindingSafeUnboxDashVideoViewModelCarInfoSeatBeltpValueGet = androidDatabindingViewDataBindingSafeUnboxDashVideoViewModelCarInfoSeatBeltpValueGet2;
                    string = this.seatBeltTextView.getResources().getString(C0899R.string.ksw_id7_seatbelt1);
                }
                dashVideoViewModelCarInfoSeatBeltpValueSeatBeltTextViewAndroidStringKswId7Seatbelt2SeatBeltTextViewAndroidStringKswId7Seatbelt1 = string;
            }
            if ((dirtyFlags & 80) == 0) {
                dashVideoViewModelCarInfoOilValueGet4 = dashVideoViewModelCarInfoOilValueGet3;
                dashVideoViewModelCarInfoTurnSpeedAngeGet = 0.0f;
            } else {
                if (dashVideoViewModelCarInfo == null) {
                    dashVideoViewModelCarInfoTurnSpeedAnge = null;
                } else {
                    dashVideoViewModelCarInfoTurnSpeedAnge = dashVideoViewModelCarInfo.turnSpeedAnge;
                }
                long dirtyFlags2 = dirtyFlags;
                updateRegistration(4, dashVideoViewModelCarInfoTurnSpeedAnge);
                if (dashVideoViewModelCarInfoTurnSpeedAnge == null) {
                    dashVideoViewModelCarInfoOilValueGet4 = dashVideoViewModelCarInfoOilValueGet3;
                    dirtyFlags = dirtyFlags2;
                    dashVideoViewModelCarInfoTurnSpeedAngeGet = 0.0f;
                } else {
                    float dashVideoViewModelCarInfoTurnSpeedAngeGet2 = dashVideoViewModelCarInfoTurnSpeedAnge.get();
                    dashVideoViewModelCarInfoOilValueGet4 = dashVideoViewModelCarInfoOilValueGet3;
                    dirtyFlags = dirtyFlags2;
                    dashVideoViewModelCarInfoTurnSpeedAngeGet = dashVideoViewModelCarInfoTurnSpeedAngeGet2;
                }
            }
        }
        if ((dirtyFlags & 96) != 0 && dashVideoViewModel != null) {
            dashVideoViewModelDashViewFocusChangeListener = dashVideoViewModel.dashViewFocusChangeListener;
            if ((dirtyFlags & 66) == 0) {
                TextViewBindingAdapter.setText(this.brakeTextView, dashVideoViewModelCarInfoBrakeValueBrakeTextViewAndroidStringKswId7Brake2BrakeTextViewAndroidStringKswId7Brake1);
            }
            if ((dirtyFlags & 64) == 0) {
                this.dashboardImageView.setOnClickListener(this.mCallback440);
            }
            if ((dirtyFlags & 96) != 0) {
                this.dashboardImageView.setOnFocusChangeListener(dashVideoViewModelDashViewFocusChangeListener);
            }
            if ((dirtyFlags & 68) != 0) {
                TextViewBindingAdapter.setText(this.oilTextView, dashVideoViewModelCarInfoOilValueGet4);
            }
            if ((dirtyFlags & 80) != 0) {
                DashboardViewModel.setRotation(this.pointerImageView, dashVideoViewModelCarInfoTurnSpeedAngeGet);
            }
            if ((dirtyFlags & 72) != 0) {
                TextViewBindingAdapter.setText(this.seatBeltTextView, dashVideoViewModelCarInfoSeatBeltpValueSeatBeltTextViewAndroidStringKswId7Seatbelt2SeatBeltTextViewAndroidStringKswId7Seatbelt1);
            }
            if ((dirtyFlags & 65) == 0) {
                TextViewBindingAdapter.setText(this.speedTextView, stringValueOfDashVideoViewModelCarInfoTurnSpeed);
                return;
            }
            return;
        }
        dashVideoViewModelDashViewFocusChangeListener = null;
        if ((dirtyFlags & 66) == 0) {
        }
        if ((dirtyFlags & 64) == 0) {
        }
        if ((dirtyFlags & 96) != 0) {
        }
        if ((dirtyFlags & 68) != 0) {
        }
        if ((dirtyFlags & 80) != 0) {
        }
        if ((dirtyFlags & 72) != 0) {
        }
        if ((dirtyFlags & 65) == 0) {
        }
    }

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        AlsID7ViewModel dashVideoViewModel = this.mDashVideoViewModel;
        boolean dashVideoViewModelJavaLangObjectNull = dashVideoViewModel != null;
        if (dashVideoViewModelJavaLangObjectNull) {
            dashVideoViewModel.openDashboard(callbackArg_0);
        }
    }
}
