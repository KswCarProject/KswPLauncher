package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.CarInfo;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.LinearGradientProgressNew;

public class FragmentDashboardgsGsEditBindingImpl extends FragmentDashboardgsGsEditBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final LinearGradientProgressNew mboundView1;
    private final TextView mboundView2;
    private final ImageView mboundView3;
    private final ImageView mboundView4;
    private final TextView mboundView5;
    private final TextView mboundView6;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.gs_id8_icon_edit_bg, 7);
    }

    public FragmentDashboardgsGsEditBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }

    private FragmentDashboardgsGsEditBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 5, bindings[7]);
        this.mDirtyFlags = -1;
        RelativeLayout relativeLayout = bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
        LinearGradientProgressNew linearGradientProgressNew = bindings[1];
        this.mboundView1 = linearGradientProgressNew;
        linearGradientProgressNew.setTag((Object) null);
        TextView textView = bindings[2];
        this.mboundView2 = textView;
        textView.setTag((Object) null);
        ImageView imageView = bindings[3];
        this.mboundView3 = imageView;
        imageView.setTag((Object) null);
        ImageView imageView2 = bindings[4];
        this.mboundView4 = imageView2;
        imageView2.setTag((Object) null);
        TextView textView2 = bindings[5];
        this.mboundView5 = textView2;
        textView2.setTag((Object) null);
        TextView textView3 = bindings[6];
        this.mboundView6 = textView3;
        textView3.setTag((Object) null);
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
        if (5 != variableId) {
            return false;
        }
        setDashboardGsViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setDashboardGsViewModel(LauncherViewModel DashboardGsViewModel) {
        this.mDashboardGsViewModel = DashboardGsViewModel;
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeDashboardGsViewModelCarInfoBrakeValue((ObservableField) object, fieldId);
            case 1:
                return onChangeDashboardGsViewModelCarInfoSpeedUnit((ObservableField) object, fieldId);
            case 2:
                return onChangeDashboardGsViewModelCarInfoSpeed((ObservableInt) object, fieldId);
            case 3:
                return onChangeDashboardGsViewModelCarInfoOilValue((ObservableField) object, fieldId);
            case 4:
                return onChangeDashboardGsViewModelCarInfoSeatBeltpValue((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeDashboardGsViewModelCarInfoBrakeValue(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeDashboardGsViewModelCarInfoSpeedUnit(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeDashboardGsViewModelCarInfoSpeed(ObservableInt DashboardGsViewModelCarInfoSpeed, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeDashboardGsViewModelCarInfoOilValue(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeDashboardGsViewModelCarInfoSeatBeltpValue(ObservableField<Boolean> observableField, int fieldId) {
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
        ObservableField<Boolean> dashboardGsViewModelCarInfoSeatBeltpValue;
        Boolean dashboardGsViewModelCarInfoBrakeValueGet;
        Drawable drawable;
        ObservableField<String> dashboardGsViewModelCarInfoOilValue;
        ObservableInt dashboardGsViewModelCarInfoSpeed;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        Boolean dashboardGsViewModelCarInfoBrakeValueGet2 = null;
        int dashboardGsViewModelCarInfoSpeedGet = 0;
        Drawable dashboardGsViewModelCarInfoSeatBeltpValueMboundView4AndroidDrawableGsId8MainIconDashboardSeatbeltMboundView4AndroidDrawableGsId8MainIconDashboardSeatbeltF = null;
        Drawable dashboardGsViewModelCarInfoBrakeValueMboundView3AndroidDrawableGsId8MainIconDashboardBrakeFMboundView3AndroidDrawableGsId8MainIconDashboardBrake = null;
        String dashboardGsViewModelCarInfoOilValueGet = null;
        String dashboardGsViewModelCarInfoSpeedUnitGet = null;
        ObservableField<Boolean> dashboardGsViewModelCarInfoBrakeValue = null;
        ObservableField<String> dashboardGsViewModelCarInfoSpeedUnit = null;
        String stringValueOfDashboardGsViewModelCarInfoSpeed = null;
        Boolean dashboardGsViewModelCarInfoSeatBeltpValueGet = null;
        if ((dirtyFlags & 95) != 0) {
            CarInfo dashboardGsViewModelCarInfo = LauncherViewModel.carInfo;
            if ((dirtyFlags & 65) != 0) {
                if (dashboardGsViewModelCarInfo != null) {
                    dashboardGsViewModelCarInfoBrakeValue = dashboardGsViewModelCarInfo.brakeValue;
                }
                updateRegistration(0, (Observable) dashboardGsViewModelCarInfoBrakeValue);
                if (dashboardGsViewModelCarInfoBrakeValue != null) {
                    dashboardGsViewModelCarInfoBrakeValueGet2 = dashboardGsViewModelCarInfoBrakeValue.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxDashboardGsViewModelCarInfoBrakeValueGet = ViewDataBinding.safeUnbox(dashboardGsViewModelCarInfoBrakeValueGet2);
                if ((dirtyFlags & 65) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxDashboardGsViewModelCarInfoBrakeValueGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    } else {
                        dirtyFlags |= 512;
                    }
                }
                dashboardGsViewModelCarInfoBrakeValueMboundView3AndroidDrawableGsId8MainIconDashboardBrakeFMboundView3AndroidDrawableGsId8MainIconDashboardBrake = AppCompatResources.getDrawable(this.mboundView3.getContext(), androidDatabindingViewDataBindingSafeUnboxDashboardGsViewModelCarInfoBrakeValueGet ? R.drawable.gs_id8_main_icon_dashboard_brake_f : R.drawable.gs_id8_main_icon_dashboard_brake);
            }
            if ((dirtyFlags & 66) != 0) {
                if (dashboardGsViewModelCarInfo != null) {
                    dashboardGsViewModelCarInfoSpeedUnit = dashboardGsViewModelCarInfo.speedUnit;
                }
                updateRegistration(1, (Observable) dashboardGsViewModelCarInfoSpeedUnit);
                if (dashboardGsViewModelCarInfoSpeedUnit != null) {
                    dashboardGsViewModelCarInfoSpeedUnitGet = dashboardGsViewModelCarInfoSpeedUnit.get();
                }
            }
            if ((dirtyFlags & 68) != 0) {
                if (dashboardGsViewModelCarInfo != null) {
                    dashboardGsViewModelCarInfoSpeed = dashboardGsViewModelCarInfo.speed;
                } else {
                    dashboardGsViewModelCarInfoSpeed = null;
                }
                updateRegistration(2, (Observable) dashboardGsViewModelCarInfoSpeed);
                if (dashboardGsViewModelCarInfoSpeed != null) {
                    dashboardGsViewModelCarInfoSpeedGet = dashboardGsViewModelCarInfoSpeed.get();
                }
                stringValueOfDashboardGsViewModelCarInfoSpeed = String.valueOf(dashboardGsViewModelCarInfoSpeedGet);
                ObservableInt observableInt = dashboardGsViewModelCarInfoSpeed;
            }
            if ((dirtyFlags & 72) != 0) {
                if (dashboardGsViewModelCarInfo != null) {
                    dashboardGsViewModelCarInfoOilValue = dashboardGsViewModelCarInfo.oilValue;
                } else {
                    dashboardGsViewModelCarInfoOilValue = null;
                }
                updateRegistration(3, (Observable) dashboardGsViewModelCarInfoOilValue);
                if (dashboardGsViewModelCarInfoOilValue != null) {
                    dashboardGsViewModelCarInfoOilValueGet = dashboardGsViewModelCarInfoOilValue.get();
                    ObservableField<String> observableField = dashboardGsViewModelCarInfoOilValue;
                } else {
                    ObservableField<String> observableField2 = dashboardGsViewModelCarInfoOilValue;
                }
            }
            if ((dirtyFlags & 80) != 0) {
                if (dashboardGsViewModelCarInfo != null) {
                    dashboardGsViewModelCarInfoSeatBeltpValue = dashboardGsViewModelCarInfo.seatBeltpValue;
                } else {
                    dashboardGsViewModelCarInfoSeatBeltpValue = null;
                }
                updateRegistration(4, (Observable) dashboardGsViewModelCarInfoSeatBeltpValue);
                if (dashboardGsViewModelCarInfoSeatBeltpValue != null) {
                    dashboardGsViewModelCarInfoSeatBeltpValueGet = dashboardGsViewModelCarInfoSeatBeltpValue.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxDashboardGsViewModelCarInfoSeatBeltpValueGet = ViewDataBinding.safeUnbox(dashboardGsViewModelCarInfoSeatBeltpValueGet);
                if ((dirtyFlags & 80) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxDashboardGsViewModelCarInfoSeatBeltpValueGet) {
                        dirtyFlags |= 256;
                    } else {
                        dirtyFlags |= 128;
                    }
                }
                if (androidDatabindingViewDataBindingSafeUnboxDashboardGsViewModelCarInfoSeatBeltpValueGet) {
                    dashboardGsViewModelCarInfoBrakeValueGet = dashboardGsViewModelCarInfoBrakeValueGet2;
                    drawable = AppCompatResources.getDrawable(this.mboundView4.getContext(), R.drawable.gs_id8_main_icon_dashboard_seatbelt);
                } else {
                    dashboardGsViewModelCarInfoBrakeValueGet = dashboardGsViewModelCarInfoBrakeValueGet2;
                    drawable = AppCompatResources.getDrawable(this.mboundView4.getContext(), R.drawable.gs_id8_main_icon_dashboard_seatbelt_f);
                }
                dashboardGsViewModelCarInfoSeatBeltpValueMboundView4AndroidDrawableGsId8MainIconDashboardSeatbeltMboundView4AndroidDrawableGsId8MainIconDashboardSeatbeltF = drawable;
                ObservableField<Boolean> observableField3 = dashboardGsViewModelCarInfoSeatBeltpValue;
                Boolean bool = dashboardGsViewModelCarInfoBrakeValueGet;
            }
        }
        if ((dirtyFlags & 68) != 0) {
            this.mboundView1.setScae(dashboardGsViewModelCarInfoSpeedGet);
            TextViewBindingAdapter.setText(this.mboundView5, stringValueOfDashboardGsViewModelCarInfoSpeed);
        }
        if ((dirtyFlags & 72) != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, dashboardGsViewModelCarInfoOilValueGet);
        }
        if ((dirtyFlags & 65) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.mboundView3, dashboardGsViewModelCarInfoBrakeValueMboundView3AndroidDrawableGsId8MainIconDashboardBrakeFMboundView3AndroidDrawableGsId8MainIconDashboardBrake);
        }
        if ((dirtyFlags & 80) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.mboundView4, dashboardGsViewModelCarInfoSeatBeltpValueMboundView4AndroidDrawableGsId8MainIconDashboardSeatbeltMboundView4AndroidDrawableGsId8MainIconDashboardSeatbeltF);
        }
        if ((dirtyFlags & 66) != 0) {
            TextViewBindingAdapter.setText(this.mboundView6, dashboardGsViewModelCarInfoSpeedUnitGet);
        }
    }
}
