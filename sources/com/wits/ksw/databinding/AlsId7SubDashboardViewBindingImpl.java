package com.wits.ksw.databinding;

import android.content.res.Resources;
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
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;
import com.wits.ksw.launcher.bean.CarInfo;
import com.wits.ksw.launcher.model.DashboardViewModel;

public class AlsId7SubDashboardViewBindingImpl extends AlsId7SubDashboardViewBinding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @Nullable
    private final View.OnClickListener mCallback133;
    private long mDirtyFlags;

    static {
        sViewsWithIds.put(R.id.title, 7);
    }

    public AlsId7SubDashboardViewBindingImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private AlsId7SubDashboardViewBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 5, bindings[4], bindings[0], bindings[1], bindings[2], bindings[3], bindings[5], bindings[6], bindings[7]);
        this.mDirtyFlags = -1;
        this.brakeTextView.setTag((Object) null);
        this.dashboardConstraintLayout.setTag((Object) null);
        this.dashboardImageView.setTag((Object) null);
        this.oilTextView.setTag((Object) null);
        this.pointerImageView.setTag((Object) null);
        this.seatBeltTextView.setTag((Object) null);
        this.speedTextView.setTag((Object) null);
        setRootTag(root);
        this.mCallback133 = new OnClickListener(this, 1);
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
        if (15 != variableId) {
            return false;
        }
        setDashVideoViewModel((AlsID7ViewModel) variable);
        return true;
    }

    public void setDashVideoViewModel(@Nullable AlsID7ViewModel DashVideoViewModel) {
        this.mDashVideoViewModel = DashVideoViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(15);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeDashVideoViewModelCarInfoTurnSpeedAnge((ObservableFloat) object, fieldId);
            case 1:
                return onChangeDashVideoViewModelCarInfoSeatBeltpValue((ObservableField) object, fieldId);
            case 2:
                return onChangeDashVideoViewModelCarInfoOilValue((ObservableInt) object, fieldId);
            case 3:
                return onChangeDashVideoViewModelCarInfoTurnSpeed((ObservableInt) object, fieldId);
            case 4:
                return onChangeDashVideoViewModelCarInfoBrakeValue((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeDashVideoViewModelCarInfoTurnSpeedAnge(ObservableFloat DashVideoViewModelCarInfoTurnSpeedAnge, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeDashVideoViewModelCarInfoSeatBeltpValue(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeDashVideoViewModelCarInfoOilValue(ObservableInt DashVideoViewModelCarInfoOilValue, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeDashVideoViewModelCarInfoTurnSpeed(ObservableInt DashVideoViewModelCarInfoTurnSpeed, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeDashVideoViewModelCarInfoBrakeValue(ObservableField<Boolean> observableField, int fieldId) {
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
        View.OnFocusChangeListener dashVideoViewModelDashViewFocusChangeListener;
        ObservableField<Boolean> dashVideoViewModelCarInfoBrakeValue;
        Resources resources;
        int i;
        ObservableInt dashVideoViewModelCarInfoTurnSpeed;
        ObservableInt dashVideoViewModelCarInfoOilValue;
        Resources resources2;
        int i2;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        ObservableFloat dashVideoViewModelCarInfoTurnSpeedAnge = null;
        String stringValueOfDashVideoViewModelCarInfoOilValue = null;
        CarInfo dashVideoViewModelCarInfo = null;
        ObservableField<Boolean> dashVideoViewModelCarInfoSeatBeltpValue = null;
        AlsID7ViewModel dashVideoViewModel = this.mDashVideoViewModel;
        String stringValueOfDashVideoViewModelCarInfoOilValueJavaLangStringL = null;
        Boolean dashVideoViewModelCarInfoBrakeValueGet = null;
        float dashVideoViewModelCarInfoTurnSpeedAngeGet = 0.0f;
        int dashVideoViewModelCarInfoOilValueGet = 0;
        String dashVideoViewModelCarInfoSeatBeltpValueSeatBeltTextViewAndroidStringKswId7Seatbelt2SeatBeltTextViewAndroidStringKswId7Seatbelt1 = null;
        String stringValueOfDashVideoViewModelCarInfoTurnSpeed = null;
        String dashVideoViewModelCarInfoBrakeValueBrakeTextViewAndroidStringKswId7Brake2BrakeTextViewAndroidStringKswId7Brake1 = null;
        int dashVideoViewModelCarInfoTurnSpeedGet = 0;
        Boolean dashVideoViewModelCarInfoSeatBeltpValueGet = null;
        if ((dirtyFlags & 95) != 0) {
            dashVideoViewModelCarInfo = AlsID7ViewModel.carInfo;
            if ((dirtyFlags & 65) != 0) {
                if (dashVideoViewModelCarInfo != null) {
                    dashVideoViewModelCarInfoTurnSpeedAnge = dashVideoViewModelCarInfo.turnSpeedAnge;
                }
                updateRegistration(0, (Observable) dashVideoViewModelCarInfoTurnSpeedAnge);
                if (dashVideoViewModelCarInfoTurnSpeedAnge != null) {
                    dashVideoViewModelCarInfoTurnSpeedAngeGet = dashVideoViewModelCarInfoTurnSpeedAnge.get();
                }
            }
            if ((dirtyFlags & 66) != 0) {
                if (dashVideoViewModelCarInfo != null) {
                    dashVideoViewModelCarInfoSeatBeltpValue = dashVideoViewModelCarInfo.seatBeltpValue;
                }
                updateRegistration(1, (Observable) dashVideoViewModelCarInfoSeatBeltpValue);
                if (dashVideoViewModelCarInfoSeatBeltpValue != null) {
                    dashVideoViewModelCarInfoSeatBeltpValueGet = dashVideoViewModelCarInfoSeatBeltpValue.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxDashVideoViewModelCarInfoSeatBeltpValueGet = ViewDataBinding.safeUnbox(dashVideoViewModelCarInfoSeatBeltpValueGet);
                if ((dirtyFlags & 66) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxDashVideoViewModelCarInfoSeatBeltpValueGet) {
                        dirtyFlags |= 256;
                    } else {
                        dirtyFlags |= 128;
                    }
                }
                if (androidDatabindingViewDataBindingSafeUnboxDashVideoViewModelCarInfoSeatBeltpValueGet) {
                    resources2 = this.seatBeltTextView.getResources();
                    i2 = R.string.ksw_id7_seatbelt2;
                } else {
                    resources2 = this.seatBeltTextView.getResources();
                    i2 = R.string.ksw_id7_seatbelt1;
                }
                String string = resources2.getString(i2);
                boolean z = androidDatabindingViewDataBindingSafeUnboxDashVideoViewModelCarInfoSeatBeltpValueGet;
                dashVideoViewModelCarInfoSeatBeltpValueSeatBeltTextViewAndroidStringKswId7Seatbelt2SeatBeltTextViewAndroidStringKswId7Seatbelt1 = string;
            }
            if ((dirtyFlags & 68) != 0) {
                if (dashVideoViewModelCarInfo != null) {
                    dashVideoViewModelCarInfoOilValue = dashVideoViewModelCarInfo.oilValue;
                } else {
                    dashVideoViewModelCarInfoOilValue = null;
                }
                updateRegistration(2, (Observable) dashVideoViewModelCarInfoOilValue);
                if (dashVideoViewModelCarInfoOilValue != null) {
                    dashVideoViewModelCarInfoOilValueGet = dashVideoViewModelCarInfoOilValue.get();
                }
                String stringValueOfDashVideoViewModelCarInfoOilValue2 = String.valueOf(dashVideoViewModelCarInfoOilValueGet);
                StringBuilder sb = new StringBuilder();
                sb.append(stringValueOfDashVideoViewModelCarInfoOilValue2);
                ObservableFloat observableFloat = dashVideoViewModelCarInfoTurnSpeedAnge;
                sb.append("L");
                stringValueOfDashVideoViewModelCarInfoOilValueJavaLangStringL = sb.toString();
                ObservableInt observableInt = dashVideoViewModelCarInfoOilValue;
                stringValueOfDashVideoViewModelCarInfoOilValue = stringValueOfDashVideoViewModelCarInfoOilValue2;
            }
            if ((dirtyFlags & 72) != 0) {
                if (dashVideoViewModelCarInfo != null) {
                    dashVideoViewModelCarInfoTurnSpeed = dashVideoViewModelCarInfo.turnSpeed;
                } else {
                    dashVideoViewModelCarInfoTurnSpeed = null;
                }
                updateRegistration(3, (Observable) dashVideoViewModelCarInfoTurnSpeed);
                if (dashVideoViewModelCarInfoTurnSpeed != null) {
                    dashVideoViewModelCarInfoTurnSpeedGet = dashVideoViewModelCarInfoTurnSpeed.get();
                }
                ObservableInt observableInt2 = dashVideoViewModelCarInfoTurnSpeed;
                stringValueOfDashVideoViewModelCarInfoTurnSpeed = String.valueOf(dashVideoViewModelCarInfoTurnSpeedGet);
            }
            if ((dirtyFlags & 80) != 0) {
                if (dashVideoViewModelCarInfo != null) {
                    dashVideoViewModelCarInfoBrakeValue = dashVideoViewModelCarInfo.brakeValue;
                } else {
                    dashVideoViewModelCarInfoBrakeValue = null;
                }
                updateRegistration(4, (Observable) dashVideoViewModelCarInfoBrakeValue);
                if (dashVideoViewModelCarInfoBrakeValue != null) {
                    dashVideoViewModelCarInfoBrakeValueGet = dashVideoViewModelCarInfoBrakeValue.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxDashVideoViewModelCarInfoBrakeValueGet = ViewDataBinding.safeUnbox(dashVideoViewModelCarInfoBrakeValueGet);
                if ((dirtyFlags & 80) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxDashVideoViewModelCarInfoBrakeValueGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    } else {
                        dirtyFlags |= 512;
                    }
                }
                if (androidDatabindingViewDataBindingSafeUnboxDashVideoViewModelCarInfoBrakeValueGet) {
                    resources = this.brakeTextView.getResources();
                    i = R.string.ksw_id7_brake2;
                } else {
                    resources = this.brakeTextView.getResources();
                    i = R.string.ksw_id7_brake1;
                }
                dashVideoViewModelCarInfoBrakeValueBrakeTextViewAndroidStringKswId7Brake2BrakeTextViewAndroidStringKswId7Brake1 = resources.getString(i);
                ObservableField<Boolean> observableField = dashVideoViewModelCarInfoBrakeValue;
            }
        }
        String dashVideoViewModelCarInfoSeatBeltpValueSeatBeltTextViewAndroidStringKswId7Seatbelt2SeatBeltTextViewAndroidStringKswId7Seatbelt12 = dashVideoViewModelCarInfoSeatBeltpValueSeatBeltTextViewAndroidStringKswId7Seatbelt2SeatBeltTextViewAndroidStringKswId7Seatbelt1;
        String stringValueOfDashVideoViewModelCarInfoTurnSpeed2 = stringValueOfDashVideoViewModelCarInfoTurnSpeed;
        String dashVideoViewModelCarInfoBrakeValueBrakeTextViewAndroidStringKswId7Brake2BrakeTextViewAndroidStringKswId7Brake12 = dashVideoViewModelCarInfoBrakeValueBrakeTextViewAndroidStringKswId7Brake2BrakeTextViewAndroidStringKswId7Brake1;
        if ((dirtyFlags & 96) == 0 || dashVideoViewModel == null) {
            dashVideoViewModelDashViewFocusChangeListener = null;
        } else {
            String str = stringValueOfDashVideoViewModelCarInfoOilValue;
            dashVideoViewModelDashViewFocusChangeListener = dashVideoViewModel.dashViewFocusChangeListener;
        }
        if ((dirtyFlags & 80) != 0) {
            CarInfo carInfo = dashVideoViewModelCarInfo;
            TextViewBindingAdapter.setText(this.brakeTextView, dashVideoViewModelCarInfoBrakeValueBrakeTextViewAndroidStringKswId7Brake2BrakeTextViewAndroidStringKswId7Brake12);
        }
        if ((dirtyFlags & 64) != 0) {
            String str2 = dashVideoViewModelCarInfoBrakeValueBrakeTextViewAndroidStringKswId7Brake2BrakeTextViewAndroidStringKswId7Brake12;
            this.dashboardImageView.setOnClickListener(this.mCallback133);
        }
        if ((dirtyFlags & 96) != 0) {
            this.dashboardImageView.setOnFocusChangeListener(dashVideoViewModelDashViewFocusChangeListener);
        }
        if ((dirtyFlags & 68) != 0) {
            TextViewBindingAdapter.setText(this.oilTextView, stringValueOfDashVideoViewModelCarInfoOilValueJavaLangStringL);
        }
        if ((dirtyFlags & 65) != 0) {
            DashboardViewModel.setRotation(this.pointerImageView, dashVideoViewModelCarInfoTurnSpeedAngeGet);
        }
        if ((dirtyFlags & 66) != 0) {
            TextViewBindingAdapter.setText(this.seatBeltTextView, dashVideoViewModelCarInfoSeatBeltpValueSeatBeltTextViewAndroidStringKswId7Seatbelt2SeatBeltTextViewAndroidStringKswId7Seatbelt12);
        }
        if ((dirtyFlags & 72) != 0) {
            TextViewBindingAdapter.setText(this.speedTextView, stringValueOfDashVideoViewModelCarInfoTurnSpeed2);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        AlsID7ViewModel dashVideoViewModel = this.mDashVideoViewModel;
        if (dashVideoViewModel != null) {
            dashVideoViewModel.openDashboard(callbackArg_0);
        }
    }
}
