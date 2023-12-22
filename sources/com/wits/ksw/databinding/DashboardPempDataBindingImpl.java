package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.p001v4.media.session.PlaybackStateCompat;
import android.support.p004v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public class DashboardPempDataBindingImpl extends DashboardPempDataBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback478;
    private long mDirtyFlags;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final ImageView mboundView4;
    private final TextView mboundView5;
    private final ImageView mboundView6;
    private final ImageView mboundView7;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.tv_dashboard, 8);
        sparseIntArray.put(C0899R.C0901id.divider, 9);
        sparseIntArray.put(C0899R.C0901id.iv_speed, 10);
    }

    public DashboardPempDataBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }

    private DashboardPempDataBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 5, (ImageView) bindings[9], (ImageView) bindings[1], (FrameLayout) bindings[10], (RelativeLayout) bindings[0], (TextView) bindings[8]);
        this.mDirtyFlags = -1L;
        this.ivMask.setTag(null);
        this.llContainer.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[3];
        this.mboundView3 = textView2;
        textView2.setTag(null);
        ImageView imageView = (ImageView) bindings[4];
        this.mboundView4 = imageView;
        imageView.setTag(null);
        TextView textView3 = (TextView) bindings[5];
        this.mboundView5 = textView3;
        textView3.setTag(null);
        ImageView imageView2 = (ImageView) bindings[6];
        this.mboundView6 = imageView2;
        imageView2.setTag(null);
        ImageView imageView3 = (ImageView) bindings[7];
        this.mboundView7 = imageView3;
        imageView3.setTag(null);
        setRootTag(root);
        this.mCallback478 = new OnClickListener(this, 1);
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
        if (6 == variableId) {
            setDashboardViewModel((LauncherViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.DashboardPempDataBinding
    public void setDashboardViewModel(LauncherViewModel DashboardViewModel) {
        this.mDashboardViewModel = DashboardViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(6);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeDashboardViewModelCarInfoOilValue((ObservableField) object, fieldId);
            case 1:
                return onChangeDashboardViewModelCarInfoSpeedUnit((ObservableField) object, fieldId);
            case 2:
                return onChangeDashboardViewModelCarInfoSpeed((ObservableInt) object, fieldId);
            case 3:
                return onChangeDashboardViewModelCarInfoSeatBeltpValue((ObservableField) object, fieldId);
            case 4:
                return onChangeDashboardViewModelCarInfoBrakeValue((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeDashboardViewModelCarInfoOilValue(ObservableField<String> DashboardViewModelCarInfoOilValue, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeDashboardViewModelCarInfoSpeedUnit(ObservableField<String> DashboardViewModelCarInfoSpeedUnit, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeDashboardViewModelCarInfoSpeed(ObservableInt DashboardViewModelCarInfoSpeed, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeDashboardViewModelCarInfoSeatBeltpValue(ObservableField<Boolean> DashboardViewModelCarInfoSeatBeltpValue, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeDashboardViewModelCarInfoBrakeValue(ObservableField<Boolean> DashboardViewModelCarInfoBrakeValue, int fieldId) {
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
        String stringValueOfDashboardViewModelCarInfoSpeed;
        Drawable dashboardViewModelCarInfoSeatBeltpValueMboundView6AndroidDrawablePempId8MainIconDashboardSeatbeltMboundView6AndroidDrawablePempId8MainIconDashboardSeatbeltF;
        ObservableField<String> dashboardViewModelCarInfoOilValue;
        ObservableField<Boolean> dashboardViewModelCarInfoBrakeValue;
        ObservableField<Boolean> dashboardViewModelCarInfoSeatBeltpValue;
        Drawable drawable;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        ObservableField<String> dashboardViewModelCarInfoOilValue2 = null;
        ObservableField<String> dashboardViewModelCarInfoSpeedUnit = null;
        Drawable dashboardViewModelCarInfoBrakeValueMboundView7AndroidDrawablePempId8MainIconDashboardBrakeFMboundView7AndroidDrawablePempId8MainIconDashboardBrake = null;
        Boolean dashboardViewModelCarInfoBrakeValueGet = null;
        String dashboardViewModelCarInfoSpeedUnitGet = null;
        String dashboardViewModelCarInfoOilValueGet = null;
        LauncherViewModel launcherViewModel = this.mDashboardViewModel;
        ObservableInt dashboardViewModelCarInfoSpeed = null;
        int dashboardViewModelCarInfoSpeedGet = 0;
        String stringValueOfDashboardViewModelCarInfoSpeed2 = null;
        Boolean dashboardViewModelCarInfoSeatBeltpValueGet = null;
        Drawable dashboardViewModelCarInfoSeatBeltpValueMboundView6AndroidDrawablePempId8MainIconDashboardSeatbeltMboundView6AndroidDrawablePempId8MainIconDashboardSeatbeltF2 = null;
        if ((dirtyFlags & 95) == 0) {
            stringValueOfDashboardViewModelCarInfoSpeed = null;
            dashboardViewModelCarInfoSeatBeltpValueMboundView6AndroidDrawablePempId8MainIconDashboardSeatbeltMboundView6AndroidDrawablePempId8MainIconDashboardSeatbeltF = null;
        } else {
            com.wits.ksw.launcher.bean.CarInfo dashboardViewModelCarInfo = LauncherViewModel.carInfo;
            if ((dirtyFlags & 65) != 0) {
                if (dashboardViewModelCarInfo != null) {
                    dashboardViewModelCarInfoOilValue2 = dashboardViewModelCarInfo.oilValue;
                }
                updateRegistration(0, dashboardViewModelCarInfoOilValue2);
                if (dashboardViewModelCarInfoOilValue2 != null) {
                    dashboardViewModelCarInfoOilValueGet = dashboardViewModelCarInfoOilValue2.get();
                }
            }
            if ((dirtyFlags & 66) != 0) {
                if (dashboardViewModelCarInfo != null) {
                    dashboardViewModelCarInfoSpeedUnit = dashboardViewModelCarInfo.speedUnit;
                }
                updateRegistration(1, dashboardViewModelCarInfoSpeedUnit);
                if (dashboardViewModelCarInfoSpeedUnit != null) {
                    dashboardViewModelCarInfoSpeedUnitGet = dashboardViewModelCarInfoSpeedUnit.get();
                }
            }
            if ((dirtyFlags & 68) != 0) {
                if (dashboardViewModelCarInfo != null) {
                    dashboardViewModelCarInfoSpeed = dashboardViewModelCarInfo.speed;
                }
                updateRegistration(2, dashboardViewModelCarInfoSpeed);
                if (dashboardViewModelCarInfoSpeed != null) {
                    dashboardViewModelCarInfoSpeedGet = dashboardViewModelCarInfoSpeed.get();
                }
                stringValueOfDashboardViewModelCarInfoSpeed2 = String.valueOf(dashboardViewModelCarInfoSpeedGet);
            }
            if ((dirtyFlags & 72) == 0) {
                dashboardViewModelCarInfoOilValue = dashboardViewModelCarInfoOilValue2;
            } else {
                if (dashboardViewModelCarInfo == null) {
                    dashboardViewModelCarInfoSeatBeltpValue = null;
                } else {
                    dashboardViewModelCarInfoSeatBeltpValue = dashboardViewModelCarInfo.seatBeltpValue;
                }
                updateRegistration(3, dashboardViewModelCarInfoSeatBeltpValue);
                if (dashboardViewModelCarInfoSeatBeltpValue != null) {
                    Boolean dashboardViewModelCarInfoSeatBeltpValueGet2 = dashboardViewModelCarInfoSeatBeltpValue.get();
                    dashboardViewModelCarInfoSeatBeltpValueGet = dashboardViewModelCarInfoSeatBeltpValueGet2;
                }
                boolean androidDatabindingViewDataBindingSafeUnboxDashboardViewModelCarInfoSeatBeltpValueGet = ViewDataBinding.safeUnbox(dashboardViewModelCarInfoSeatBeltpValueGet);
                if ((dirtyFlags & 72) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxDashboardViewModelCarInfoSeatBeltpValueGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    } else {
                        dirtyFlags |= 512;
                    }
                }
                if (androidDatabindingViewDataBindingSafeUnboxDashboardViewModelCarInfoSeatBeltpValueGet) {
                    dashboardViewModelCarInfoOilValue = dashboardViewModelCarInfoOilValue2;
                    drawable = AppCompatResources.getDrawable(this.mboundView6.getContext(), C0899R.C0900drawable.pemp_id8_main_icon_dashboard_seatbelt);
                } else {
                    dashboardViewModelCarInfoOilValue = dashboardViewModelCarInfoOilValue2;
                    drawable = AppCompatResources.getDrawable(this.mboundView6.getContext(), C0899R.C0900drawable.pemp_id8_main_icon_dashboard_seatbelt_f);
                }
                dashboardViewModelCarInfoSeatBeltpValueMboundView6AndroidDrawablePempId8MainIconDashboardSeatbeltMboundView6AndroidDrawablePempId8MainIconDashboardSeatbeltF2 = drawable;
            }
            if ((dirtyFlags & 80) == 0) {
                stringValueOfDashboardViewModelCarInfoSpeed = stringValueOfDashboardViewModelCarInfoSpeed2;
                dashboardViewModelCarInfoSeatBeltpValueMboundView6AndroidDrawablePempId8MainIconDashboardSeatbeltMboundView6AndroidDrawablePempId8MainIconDashboardSeatbeltF = dashboardViewModelCarInfoSeatBeltpValueMboundView6AndroidDrawablePempId8MainIconDashboardSeatbeltMboundView6AndroidDrawablePempId8MainIconDashboardSeatbeltF2;
                dashboardViewModelCarInfoOilValue2 = dashboardViewModelCarInfoOilValue;
            } else {
                if (dashboardViewModelCarInfo == null) {
                    dashboardViewModelCarInfoBrakeValue = null;
                } else {
                    dashboardViewModelCarInfoBrakeValue = dashboardViewModelCarInfo.brakeValue;
                }
                updateRegistration(4, dashboardViewModelCarInfoBrakeValue);
                if (dashboardViewModelCarInfoBrakeValue != null) {
                    Boolean dashboardViewModelCarInfoBrakeValueGet2 = dashboardViewModelCarInfoBrakeValue.get();
                    dashboardViewModelCarInfoBrakeValueGet = dashboardViewModelCarInfoBrakeValueGet2;
                }
                boolean androidDatabindingViewDataBindingSafeUnboxDashboardViewModelCarInfoBrakeValueGet = ViewDataBinding.safeUnbox(dashboardViewModelCarInfoBrakeValueGet);
                if ((dirtyFlags & 80) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxDashboardViewModelCarInfoBrakeValueGet) {
                        dirtyFlags |= 256;
                    } else {
                        dirtyFlags |= 128;
                    }
                }
                dashboardViewModelCarInfoBrakeValueMboundView7AndroidDrawablePempId8MainIconDashboardBrakeFMboundView7AndroidDrawablePempId8MainIconDashboardBrake = AppCompatResources.getDrawable(this.mboundView7.getContext(), androidDatabindingViewDataBindingSafeUnboxDashboardViewModelCarInfoBrakeValueGet ? C0899R.C0900drawable.pemp_id8_main_icon_dashboard_brake_f : C0899R.C0900drawable.pemp_id8_main_icon_dashboard_brake);
                stringValueOfDashboardViewModelCarInfoSpeed = stringValueOfDashboardViewModelCarInfoSpeed2;
                dashboardViewModelCarInfoSeatBeltpValueMboundView6AndroidDrawablePempId8MainIconDashboardSeatbeltMboundView6AndroidDrawablePempId8MainIconDashboardSeatbeltF = dashboardViewModelCarInfoSeatBeltpValueMboundView6AndroidDrawablePempId8MainIconDashboardSeatbeltMboundView6AndroidDrawablePempId8MainIconDashboardSeatbeltF2;
                dashboardViewModelCarInfoOilValue2 = dashboardViewModelCarInfoOilValue;
            }
        }
        if ((dirtyFlags & 64) != 0) {
            this.ivMask.setOnClickListener(this.mCallback478);
        }
        if ((dirtyFlags & 65) != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, dashboardViewModelCarInfoOilValueGet);
        }
        if ((dirtyFlags & 68) != 0) {
            TextViewBindingAdapter.setText(this.mboundView3, stringValueOfDashboardViewModelCarInfoSpeed);
            LauncherViewModel.setPempId8SpeedRotation(this.mboundView4, dashboardViewModelCarInfoSpeedGet);
        }
        if ((dirtyFlags & 66) != 0) {
            TextViewBindingAdapter.setText(this.mboundView5, dashboardViewModelCarInfoSpeedUnitGet);
        }
        if ((dirtyFlags & 72) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.mboundView6, dashboardViewModelCarInfoSeatBeltpValueMboundView6AndroidDrawablePempId8MainIconDashboardSeatbeltMboundView6AndroidDrawablePempId8MainIconDashboardSeatbeltF);
        }
        if ((dirtyFlags & 80) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.mboundView7, dashboardViewModelCarInfoBrakeValueMboundView7AndroidDrawablePempId8MainIconDashboardBrakeFMboundView7AndroidDrawablePempId8MainIconDashboardBrake);
        }
    }

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        LauncherViewModel dashboardViewModel = this.mDashboardViewModel;
        boolean dashboardViewModelJavaLangObjectNull = dashboardViewModel != null;
        if (dashboardViewModelJavaLangObjectNull) {
            dashboardViewModel.openDashboard(callbackArg_0);
        }
    }
}
