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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.LinearGradientProgressNew;

/* loaded from: classes7.dex */
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
        sparseIntArray.put(C0899R.C0901id.gs_id8_icon_edit_bg, 7);
    }

    public FragmentDashboardgsGsEditBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }

    private FragmentDashboardgsGsEditBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 5, (ImageView) bindings[7]);
        this.mDirtyFlags = -1L;
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        LinearGradientProgressNew linearGradientProgressNew = (LinearGradientProgressNew) bindings[1];
        this.mboundView1 = linearGradientProgressNew;
        linearGradientProgressNew.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        ImageView imageView = (ImageView) bindings[3];
        this.mboundView3 = imageView;
        imageView.setTag(null);
        ImageView imageView2 = (ImageView) bindings[4];
        this.mboundView4 = imageView2;
        imageView2.setTag(null);
        TextView textView2 = (TextView) bindings[5];
        this.mboundView5 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[6];
        this.mboundView6 = textView3;
        textView3.setTag(null);
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
        if (5 == variableId) {
            setDashboardGsViewModel((LauncherViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.FragmentDashboardgsGsEditBinding
    public void setDashboardGsViewModel(LauncherViewModel DashboardGsViewModel) {
        this.mDashboardGsViewModel = DashboardGsViewModel;
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
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

    private boolean onChangeDashboardGsViewModelCarInfoBrakeValue(ObservableField<Boolean> DashboardGsViewModelCarInfoBrakeValue, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeDashboardGsViewModelCarInfoSpeedUnit(ObservableField<String> DashboardGsViewModelCarInfoSpeedUnit, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeDashboardGsViewModelCarInfoSpeed(ObservableInt DashboardGsViewModelCarInfoSpeed, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeDashboardGsViewModelCarInfoOilValue(ObservableField<String> DashboardGsViewModelCarInfoOilValue, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeDashboardGsViewModelCarInfoSeatBeltpValue(ObservableField<Boolean> DashboardGsViewModelCarInfoSeatBeltpValue, int fieldId) {
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
        ObservableField<Boolean> dashboardGsViewModelCarInfoSeatBeltpValue;
        Boolean dashboardGsViewModelCarInfoBrakeValueGet;
        Drawable drawable;
        ObservableField<String> dashboardGsViewModelCarInfoOilValue;
        ObservableInt dashboardGsViewModelCarInfoSpeed;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
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
            com.wits.ksw.launcher.bean.CarInfo dashboardGsViewModelCarInfo = LauncherViewModel.carInfo;
            if ((dirtyFlags & 65) != 0) {
                if (dashboardGsViewModelCarInfo != null) {
                    dashboardGsViewModelCarInfoBrakeValue = dashboardGsViewModelCarInfo.brakeValue;
                }
                updateRegistration(0, dashboardGsViewModelCarInfoBrakeValue);
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
                dashboardGsViewModelCarInfoBrakeValueMboundView3AndroidDrawableGsId8MainIconDashboardBrakeFMboundView3AndroidDrawableGsId8MainIconDashboardBrake = AppCompatResources.getDrawable(this.mboundView3.getContext(), androidDatabindingViewDataBindingSafeUnboxDashboardGsViewModelCarInfoBrakeValueGet ? C0899R.C0900drawable.gs_id8_main_icon_dashboard_brake_f : C0899R.C0900drawable.gs_id8_main_icon_dashboard_brake);
            }
            if ((dirtyFlags & 66) != 0) {
                if (dashboardGsViewModelCarInfo != null) {
                    dashboardGsViewModelCarInfoSpeedUnit = dashboardGsViewModelCarInfo.speedUnit;
                }
                updateRegistration(1, dashboardGsViewModelCarInfoSpeedUnit);
                if (dashboardGsViewModelCarInfoSpeedUnit != null) {
                    dashboardGsViewModelCarInfoSpeedUnitGet = dashboardGsViewModelCarInfoSpeedUnit.get();
                }
            }
            if ((dirtyFlags & 68) != 0) {
                if (dashboardGsViewModelCarInfo == null) {
                    dashboardGsViewModelCarInfoSpeed = null;
                } else {
                    dashboardGsViewModelCarInfoSpeed = dashboardGsViewModelCarInfo.speed;
                }
                updateRegistration(2, dashboardGsViewModelCarInfoSpeed);
                if (dashboardGsViewModelCarInfoSpeed != null) {
                    dashboardGsViewModelCarInfoSpeedGet = dashboardGsViewModelCarInfoSpeed.get();
                }
                stringValueOfDashboardGsViewModelCarInfoSpeed = String.valueOf(dashboardGsViewModelCarInfoSpeedGet);
            }
            if ((dirtyFlags & 72) != 0) {
                if (dashboardGsViewModelCarInfo == null) {
                    dashboardGsViewModelCarInfoOilValue = null;
                } else {
                    dashboardGsViewModelCarInfoOilValue = dashboardGsViewModelCarInfo.oilValue;
                }
                updateRegistration(3, dashboardGsViewModelCarInfoOilValue);
                if (dashboardGsViewModelCarInfoOilValue != null) {
                    dashboardGsViewModelCarInfoOilValueGet = dashboardGsViewModelCarInfoOilValue.get();
                }
            }
            if ((dirtyFlags & 80) != 0) {
                if (dashboardGsViewModelCarInfo == null) {
                    dashboardGsViewModelCarInfoSeatBeltpValue = null;
                } else {
                    dashboardGsViewModelCarInfoSeatBeltpValue = dashboardGsViewModelCarInfo.seatBeltpValue;
                }
                updateRegistration(4, dashboardGsViewModelCarInfoSeatBeltpValue);
                if (dashboardGsViewModelCarInfoSeatBeltpValue != null) {
                    Boolean dashboardGsViewModelCarInfoSeatBeltpValueGet2 = dashboardGsViewModelCarInfoSeatBeltpValue.get();
                    dashboardGsViewModelCarInfoSeatBeltpValueGet = dashboardGsViewModelCarInfoSeatBeltpValueGet2;
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
                    drawable = AppCompatResources.getDrawable(this.mboundView4.getContext(), C0899R.C0900drawable.gs_id8_main_icon_dashboard_seatbelt);
                } else {
                    dashboardGsViewModelCarInfoBrakeValueGet = dashboardGsViewModelCarInfoBrakeValueGet2;
                    drawable = AppCompatResources.getDrawable(this.mboundView4.getContext(), C0899R.C0900drawable.gs_id8_main_icon_dashboard_seatbelt_f);
                }
                dashboardGsViewModelCarInfoSeatBeltpValueMboundView4AndroidDrawableGsId8MainIconDashboardSeatbeltMboundView4AndroidDrawableGsId8MainIconDashboardSeatbeltF = drawable;
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
