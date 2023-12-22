package com.wits.ksw.databinding;

import android.content.res.Resources;
import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.databinding.adapters.ViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.p001v4.media.session.PlaybackStateCompat;
import android.support.p004v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.CustomBmwImageView;

/* loaded from: classes7.dex */
public class AlsId7SubDashboardViewBindingImpl extends AlsId7SubDashboardViewBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback49;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.title, 7);
    }

    public AlsId7SubDashboardViewBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }

    private AlsId7SubDashboardViewBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
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
        this.mCallback49 = new OnClickListener(this, 1);
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
                return onChangeDashVideoViewModelCarInfoBrakeValue((ObservableField) object, fieldId);
            case 1:
                return onChangeDashVideoViewModelCarInfoOilValue((ObservableField) object, fieldId);
            case 2:
                return onChangeDashVideoViewModelCarInfoSeatBeltpValue((ObservableField) object, fieldId);
            case 3:
                return onChangeDashVideoViewModelCarInfoUnit((ObservableInt) object, fieldId);
            case 4:
                return onChangeDashVideoViewModelCarInfoSpeed((ObservableInt) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeDashVideoViewModelCarInfoBrakeValue(ObservableField<Boolean> DashVideoViewModelCarInfoBrakeValue, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeDashVideoViewModelCarInfoOilValue(ObservableField<String> DashVideoViewModelCarInfoOilValue, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeDashVideoViewModelCarInfoSeatBeltpValue(ObservableField<Boolean> DashVideoViewModelCarInfoSeatBeltpValue, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeDashVideoViewModelCarInfoUnit(ObservableInt DashVideoViewModelCarInfoUnit, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeDashVideoViewModelCarInfoSpeed(ObservableInt DashVideoViewModelCarInfoSpeed, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:120:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01ea  */
    @Override // android.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void executeBindings() {
        long dirtyFlags;
        String dashVideoViewModelCarInfoBrakeValueBrakeTextViewAndroidStringKswId7Brake2BrakeTextViewAndroidStringKswId7Brake1;
        String stringValueOfDashVideoViewModelCarInfoSpeed;
        View.OnFocusChangeListener dashVideoViewModelDashViewFocusChangeListener;
        String dashVideoViewModelCarInfoOilValueGet;
        String dashVideoViewModelCarInfoOilValueGet2;
        ObservableInt dashVideoViewModelCarInfoUnit;
        ObservableInt dashVideoViewModelCarInfoSpeed;
        boolean dashVideoViewModelCarInfoUnitInt1;
        Drawable drawable;
        ObservableField<Boolean> dashVideoViewModelCarInfoSeatBeltpValue;
        boolean androidDatabindingViewDataBindingSafeUnboxDashVideoViewModelCarInfoSeatBeltpValueGet;
        String string;
        long dirtyFlags2;
        Resources resources;
        int i;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String dashVideoViewModelCarInfoOilValueGet3 = null;
        Drawable dashVideoViewModelCarInfoUnitInt1DashboardImageViewAndroidDrawableAlsId7MainDashboardItemSelectorYDashboardImageViewAndroidDrawableAlsId7MainDashboardItemSelectorG = null;
        ObservableField<Boolean> dashVideoViewModelCarInfoBrakeValue = null;
        com.wits.ksw.launcher.bean.CarInfo dashVideoViewModelCarInfo = null;
        int dashVideoViewModelCarInfoSpeedGet = 0;
        String dashVideoViewModelCarInfoSeatBeltpValueSeatBeltTextViewAndroidStringKswId7Seatbelt2SeatBeltTextViewAndroidStringKswId7Seatbelt1 = null;
        int dashVideoViewModelCarInfoUnitGet = 0;
        ObservableField<String> dashVideoViewModelCarInfoOilValue = null;
        String dashVideoViewModelCarInfoBrakeValueBrakeTextViewAndroidStringKswId7Brake2BrakeTextViewAndroidStringKswId7Brake12 = null;
        AlsID7ViewModel dashVideoViewModel = this.mDashVideoViewModel;
        Boolean dashVideoViewModelCarInfoBrakeValueGet = null;
        Boolean dashVideoViewModelCarInfoSeatBeltpValueGet = null;
        if ((dirtyFlags & 95) == 0) {
            dashVideoViewModelCarInfoBrakeValueBrakeTextViewAndroidStringKswId7Brake2BrakeTextViewAndroidStringKswId7Brake1 = null;
            stringValueOfDashVideoViewModelCarInfoSpeed = null;
        } else {
            dashVideoViewModelCarInfo = AlsID7ViewModel.carInfo;
            if ((dirtyFlags & 65) != 0) {
                if (dashVideoViewModelCarInfo != null) {
                    dashVideoViewModelCarInfoBrakeValue = dashVideoViewModelCarInfo.brakeValue;
                }
                updateRegistration(0, dashVideoViewModelCarInfoBrakeValue);
                if (dashVideoViewModelCarInfoBrakeValue != null) {
                    Boolean dashVideoViewModelCarInfoBrakeValueGet2 = dashVideoViewModelCarInfoBrakeValue.get();
                    dashVideoViewModelCarInfoBrakeValueGet = dashVideoViewModelCarInfoBrakeValueGet2;
                }
                boolean androidDatabindingViewDataBindingSafeUnboxDashVideoViewModelCarInfoBrakeValueGet = ViewDataBinding.safeUnbox(dashVideoViewModelCarInfoBrakeValueGet);
                if ((dirtyFlags & 65) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxDashVideoViewModelCarInfoBrakeValueGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                    }
                }
                if (androidDatabindingViewDataBindingSafeUnboxDashVideoViewModelCarInfoBrakeValueGet) {
                    resources = this.brakeTextView.getResources();
                    dirtyFlags2 = dirtyFlags;
                    i = C0899R.string.ksw_id7_brake2;
                } else {
                    dirtyFlags2 = dirtyFlags;
                    resources = this.brakeTextView.getResources();
                    i = C0899R.string.ksw_id7_brake1;
                }
                dashVideoViewModelCarInfoBrakeValueBrakeTextViewAndroidStringKswId7Brake2BrakeTextViewAndroidStringKswId7Brake12 = resources.getString(i);
                dirtyFlags = dirtyFlags2;
            }
            if ((dirtyFlags & 66) == 0) {
                dashVideoViewModelCarInfoOilValueGet = null;
            } else {
                if (dashVideoViewModelCarInfo != null) {
                    dashVideoViewModelCarInfoOilValue = dashVideoViewModelCarInfo.oilValue;
                }
                updateRegistration(1, dashVideoViewModelCarInfoOilValue);
                if (dashVideoViewModelCarInfoOilValue == null) {
                    dashVideoViewModelCarInfoOilValueGet = null;
                } else {
                    dashVideoViewModelCarInfoOilValueGet = dashVideoViewModelCarInfoOilValue.get();
                }
            }
            if ((dirtyFlags & 68) == 0) {
                dashVideoViewModelCarInfoOilValueGet2 = dashVideoViewModelCarInfoOilValueGet;
            } else {
                if (dashVideoViewModelCarInfo == null) {
                    dashVideoViewModelCarInfoSeatBeltpValue = null;
                } else {
                    dashVideoViewModelCarInfoSeatBeltpValue = dashVideoViewModelCarInfo.seatBeltpValue;
                }
                dashVideoViewModelCarInfoOilValueGet2 = dashVideoViewModelCarInfoOilValueGet;
                updateRegistration(2, dashVideoViewModelCarInfoSeatBeltpValue);
                if (dashVideoViewModelCarInfoSeatBeltpValue != null) {
                    Boolean dashVideoViewModelCarInfoSeatBeltpValueGet2 = dashVideoViewModelCarInfoSeatBeltpValue.get();
                    dashVideoViewModelCarInfoSeatBeltpValueGet = dashVideoViewModelCarInfoSeatBeltpValueGet2;
                }
                boolean androidDatabindingViewDataBindingSafeUnboxDashVideoViewModelCarInfoSeatBeltpValueGet2 = ViewDataBinding.safeUnbox(dashVideoViewModelCarInfoSeatBeltpValueGet);
                if ((dirtyFlags & 68) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxDashVideoViewModelCarInfoSeatBeltpValueGet2) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    } else {
                        dirtyFlags |= 512;
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
            if ((dirtyFlags & 72) == 0) {
                dashVideoViewModelCarInfoUnit = null;
            } else {
                if (dashVideoViewModelCarInfo == null) {
                    dashVideoViewModelCarInfoUnit = null;
                } else {
                    dashVideoViewModelCarInfoUnit = dashVideoViewModelCarInfo.unit;
                }
                updateRegistration(3, dashVideoViewModelCarInfoUnit);
                if (dashVideoViewModelCarInfoUnit != null) {
                    dashVideoViewModelCarInfoUnitGet = dashVideoViewModelCarInfoUnit.get();
                }
                boolean dashVideoViewModelCarInfoUnitInt12 = dashVideoViewModelCarInfoUnitGet == 1;
                if ((dirtyFlags & 72) != 0) {
                    if (dashVideoViewModelCarInfoUnitInt12) {
                        dirtyFlags |= 256;
                    } else {
                        dirtyFlags |= 128;
                    }
                }
                if (dashVideoViewModelCarInfoUnitInt12) {
                    dashVideoViewModelCarInfoUnitInt1 = dashVideoViewModelCarInfoUnitInt12;
                    drawable = AppCompatResources.getDrawable(this.dashboardImageView.getContext(), C0899R.C0900drawable.als_id7_main_dashboard_item_selector_y);
                } else {
                    dashVideoViewModelCarInfoUnitInt1 = dashVideoViewModelCarInfoUnitInt12;
                    drawable = AppCompatResources.getDrawable(this.dashboardImageView.getContext(), C0899R.C0900drawable.als_id7_main_dashboard_item_selector_g);
                }
                dashVideoViewModelCarInfoUnitInt1DashboardImageViewAndroidDrawableAlsId7MainDashboardItemSelectorYDashboardImageViewAndroidDrawableAlsId7MainDashboardItemSelectorG = drawable;
            }
            if ((dirtyFlags & 80) == 0) {
                dashVideoViewModelCarInfoOilValueGet3 = dashVideoViewModelCarInfoOilValueGet2;
                dashVideoViewModelCarInfoBrakeValueBrakeTextViewAndroidStringKswId7Brake2BrakeTextViewAndroidStringKswId7Brake1 = dashVideoViewModelCarInfoBrakeValueBrakeTextViewAndroidStringKswId7Brake2BrakeTextViewAndroidStringKswId7Brake12;
                stringValueOfDashVideoViewModelCarInfoSpeed = null;
            } else {
                if (dashVideoViewModelCarInfo == null) {
                    dashVideoViewModelCarInfoSpeed = null;
                } else {
                    dashVideoViewModelCarInfoSpeed = dashVideoViewModelCarInfo.speed;
                }
                long dirtyFlags3 = dirtyFlags;
                updateRegistration(4, dashVideoViewModelCarInfoSpeed);
                if (dashVideoViewModelCarInfoSpeed != null) {
                    dashVideoViewModelCarInfoSpeedGet = dashVideoViewModelCarInfoSpeed.get();
                }
                String stringValueOfDashVideoViewModelCarInfoSpeed2 = String.valueOf(dashVideoViewModelCarInfoSpeedGet);
                dashVideoViewModelCarInfoOilValueGet3 = dashVideoViewModelCarInfoOilValueGet2;
                dirtyFlags = dirtyFlags3;
                dashVideoViewModelCarInfoBrakeValueBrakeTextViewAndroidStringKswId7Brake2BrakeTextViewAndroidStringKswId7Brake1 = dashVideoViewModelCarInfoBrakeValueBrakeTextViewAndroidStringKswId7Brake2BrakeTextViewAndroidStringKswId7Brake12;
                stringValueOfDashVideoViewModelCarInfoSpeed = stringValueOfDashVideoViewModelCarInfoSpeed2;
            }
        }
        if ((dirtyFlags & 96) != 0 && dashVideoViewModel != null) {
            dashVideoViewModelDashViewFocusChangeListener = dashVideoViewModel.dashViewFocusChangeListener;
            if ((dirtyFlags & 65) == 0) {
                TextViewBindingAdapter.setText(this.brakeTextView, dashVideoViewModelCarInfoBrakeValueBrakeTextViewAndroidStringKswId7Brake2BrakeTextViewAndroidStringKswId7Brake1);
            }
            if ((dirtyFlags & 72) != 0) {
                ViewBindingAdapter.setBackground(this.dashboardImageView, dashVideoViewModelCarInfoUnitInt1DashboardImageViewAndroidDrawableAlsId7MainDashboardItemSelectorYDashboardImageViewAndroidDrawableAlsId7MainDashboardItemSelectorG);
            }
            if ((dirtyFlags & 64) == 0) {
                this.dashboardImageView.setOnClickListener(this.mCallback49);
            }
            if ((dirtyFlags & 96) != 0) {
                this.dashboardImageView.setOnFocusChangeListener(dashVideoViewModelDashViewFocusChangeListener);
            }
            if ((dirtyFlags & 66) != 0) {
                TextViewBindingAdapter.setText(this.oilTextView, dashVideoViewModelCarInfoOilValueGet3);
            }
            if ((dirtyFlags & 80) != 0) {
                LauncherViewModel.setRotationBySpeed(this.pointerImageView, dashVideoViewModelCarInfoSpeedGet);
                TextViewBindingAdapter.setText(this.speedTextView, stringValueOfDashVideoViewModelCarInfoSpeed);
            }
            if ((dirtyFlags & 68) == 0) {
                TextViewBindingAdapter.setText(this.seatBeltTextView, dashVideoViewModelCarInfoSeatBeltpValueSeatBeltTextViewAndroidStringKswId7Seatbelt2SeatBeltTextViewAndroidStringKswId7Seatbelt1);
                return;
            }
            return;
        }
        dashVideoViewModelDashViewFocusChangeListener = null;
        if ((dirtyFlags & 65) == 0) {
        }
        if ((dirtyFlags & 72) != 0) {
        }
        if ((dirtyFlags & 64) == 0) {
        }
        if ((dirtyFlags & 96) != 0) {
        }
        if ((dirtyFlags & 66) != 0) {
        }
        if ((dirtyFlags & 80) != 0) {
        }
        if ((dirtyFlags & 68) == 0) {
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
