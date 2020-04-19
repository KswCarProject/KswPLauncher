package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.CarInfo;
import com.wits.ksw.launcher.model.DashboardViewModel;
import com.wits.ksw.launcher.view.ID7SpeedImageView;

public class ALSDasoardBindImpl extends ALSDasoardBind {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final ID7SpeedImageView mboundView1;
    @NonNull
    private final ImageView mboundView3;

    static {
        sViewsWithIds.put(R.id.carImageView, 13);
        sViewsWithIds.put(R.id.dorr_left_fl_imageView, 14);
        sViewsWithIds.put(R.id.dorr_left_fr_imageView, 15);
        sViewsWithIds.put(R.id.dorr_left_rl_imageView, 16);
        sViewsWithIds.put(R.id.imageView19, 17);
        sViewsWithIds.put(R.id.dorr_back_imageView, 18);
        sViewsWithIds.put(R.id.alsCloseView, 19);
        sViewsWithIds.put(R.id.alsRadioGroup, 20);
        sViewsWithIds.put(R.id.alsRadioButton1, 21);
        sViewsWithIds.put(R.id.alsRadioButton2, 22);
        sViewsWithIds.put(R.id.alsRadioButton3, 23);
    }

    public ALSDasoardBindImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 24, sIncludes, sViewsWithIds));
    }

    private ALSDasoardBindImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 10, bindings[19], bindings[12], bindings[21], bindings[22], bindings[23], bindings[20], bindings[10], bindings[13], bindings[18], bindings[14], bindings[15], bindings[16], bindings[17], bindings[0], bindings[8], bindings[11], (ImageView) null, bindings[4], bindings[2], bindings[5], bindings[9], bindings[6], bindings[7]);
        this.mDirtyFlags = -1;
        this.alsMenu.setTag((Object) null);
        this.brakeTextView.setTag((Object) null);
        this.linearLayout2.setTag((Object) null);
        this.mboundView1 = bindings[1];
        this.mboundView1.setTag((Object) null);
        this.mboundView3 = bindings[3];
        this.mboundView3.setTag((Object) null);
        this.oilTextView.setTag((Object) null);
        this.seatBeltTextView.setTag((Object) null);
        this.speedPointerTextView.setTag((Object) null);
        this.speedometerImageView.setTag((Object) null);
        this.tachometerImageView.setTag((Object) null);
        this.tempTextView.setTag((Object) null);
        this.zspeedPointerImageView.setTag((Object) null);
        this.zspeedPointerTextView.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
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
        if (11 != variableId) {
            return false;
        }
        setViewModel((DashboardViewModel) variable);
        return true;
    }

    public void setViewModel(@Nullable DashboardViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        notifyPropertyChanged(11);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelShowAls((ObservableBoolean) object, fieldId);
            case 1:
                return onChangeViewModelCarInfoTempStr((ObservableField) object, fieldId);
            case 2:
                return onChangeViewModelCarInfoUnit((ObservableInt) object, fieldId);
            case 3:
                return onChangeViewModelCarInfoSeatBeltpValue((ObservableField) object, fieldId);
            case 4:
                return onChangeViewModelCarInfoTurnSpeed((ObservableInt) object, fieldId);
            case 5:
                return onChangeViewModelCarInfoAlsmode((ObservableInt) object, fieldId);
            case 6:
                return onChangeViewModelCarInfoOilValue((ObservableInt) object, fieldId);
            case 7:
                return onChangeViewModelCarInfoTurnSpeedAnge((ObservableFloat) object, fieldId);
            case 8:
                return onChangeViewModelCarInfoSpeed((ObservableInt) object, fieldId);
            case 9:
                return onChangeViewModelCarInfoBrakeValue((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelShowAls(ObservableBoolean ViewModelShowAls, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoTempStr(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoUnit(ObservableInt ViewModelCarInfoUnit, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoSeatBeltpValue(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoTurnSpeed(ObservableInt ViewModelCarInfoTurnSpeed, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoAlsmode(ObservableInt ViewModelCarInfoAlsmode, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoOilValue(ObservableInt ViewModelCarInfoOilValue, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoTurnSpeedAnge(ObservableFloat ViewModelCarInfoTurnSpeedAnge, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoSpeed(ObservableInt ViewModelCarInfoSpeed, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoBrakeValue(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        String stringValueOfViewModelCarInfoTurnSpeed;
        float viewModelCarInfoTurnSpeedAngeGet;
        String viewModelCarInfoTempStrGet;
        Drawable viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableId7AlsMphSpeedWtachLevelSpeedometerImageViewAndroidDrawableId7AlsSpeedWtachLevel;
        int viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE;
        int viewModelCarInfoBrakeValueViewVISIBLEViewGONE;
        int viewModelCarInfoSpeedGet;
        int viewModelCarInfoAlsmodeGet;
        int viewModelShowAlsViewVISIBLEViewGONE;
        long dirtyFlags2;
        ObservableField<Boolean> viewModelCarInfoBrakeValue;
        ObservableInt viewModelCarInfoSpeed;
        ObservableFloat viewModelCarInfoTurnSpeedAnge;
        ObservableInt viewModelCarInfoOilValue;
        ObservableInt viewModelCarInfoAlsmode;
        ObservableInt viewModelCarInfoTurnSpeed;
        ID7SpeedImageView iD7SpeedImageView;
        int i;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        int viewModelCarInfoUnitGet = 0;
        int viewModelCarInfoOilValueGet = 0;
        ObservableBoolean viewModelShowAls = null;
        String stringValueOfViewModelCarInfoSpeed = null;
        boolean viewModelShowAlsGet = false;
        String viewModelCarInfoOilValueJavaLangStringL = null;
        ObservableField<String> viewModelCarInfoTempStr = null;
        ObservableInt viewModelCarInfoUnit = null;
        ObservableField<Boolean> viewModelCarInfoSeatBeltpValue = null;
        boolean viewModelCarInfoUnitInt1 = false;
        int viewModelCarInfoAlsmodeGet2 = 0;
        ObservableInt viewModelCarInfoTurnSpeed2 = null;
        int viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE2 = 0;
        int viewModelCarInfoSpeedGet2 = 0;
        Boolean viewModelCarInfoSeatBeltpValueGet = null;
        boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet = false;
        int viewModelCarInfoTurnSpeedGet = 0;
        int viewModelCarInfoBrakeValueViewVISIBLEViewGONE2 = 0;
        Drawable viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableId7AlsMphSpeedWtachLevelSpeedometerImageViewAndroidDrawableId7AlsSpeedWtachLevel2 = null;
        String viewModelCarInfoTempStrGet2 = null;
        Boolean viewModelCarInfoBrakeValueGet = null;
        String stringValueOfViewModelCarInfoTurnSpeed2 = null;
        DashboardViewModel viewModel = this.mViewModel;
        float viewModelCarInfoTurnSpeedAngeGet2 = 0.0f;
        if ((dirtyFlags & 3070) != 0) {
            CarInfo viewModelCarInfo = DashboardViewModel.carInfo;
            if ((dirtyFlags & 2050) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoTempStr = viewModelCarInfo.tempStr;
                }
                updateRegistration(1, (Observable) viewModelCarInfoTempStr);
                if (viewModelCarInfoTempStr != null) {
                    viewModelCarInfoTempStrGet2 = viewModelCarInfoTempStr.get();
                }
            }
            if ((dirtyFlags & 2052) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoUnit = viewModelCarInfo.unit;
                }
                updateRegistration(2, (Observable) viewModelCarInfoUnit);
                if (viewModelCarInfoUnit != null) {
                    viewModelCarInfoUnitGet = viewModelCarInfoUnit.get();
                }
                boolean viewModelCarInfoUnitInt12 = true;
                if (viewModelCarInfoUnitGet != 1) {
                    viewModelCarInfoUnitInt12 = false;
                }
                if ((dirtyFlags & 2052) != 0) {
                    if (viewModelCarInfoUnitInt12) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                    }
                }
                if (viewModelCarInfoUnitInt12) {
                    dirtyFlags2 = dirtyFlags;
                    iD7SpeedImageView = this.speedometerImageView;
                    i = R.drawable.id7_als_mph_speed_wtach_level;
                } else {
                    dirtyFlags2 = dirtyFlags;
                    iD7SpeedImageView = this.speedometerImageView;
                    i = R.drawable.id7_als_speed_wtach_level;
                }
                viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableId7AlsMphSpeedWtachLevelSpeedometerImageViewAndroidDrawableId7AlsSpeedWtachLevel2 = getDrawableFromResource(iD7SpeedImageView, i);
                viewModelCarInfoUnitInt1 = viewModelCarInfoUnitInt12;
            } else {
                dirtyFlags2 = dirtyFlags;
            }
            if ((dirtyFlags2 & 2056) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoSeatBeltpValue = viewModelCarInfo.seatBeltpValue;
                }
                updateRegistration(3, (Observable) viewModelCarInfoSeatBeltpValue);
                if (viewModelCarInfoSeatBeltpValue != null) {
                    viewModelCarInfoSeatBeltpValueGet = viewModelCarInfoSeatBeltpValue.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet2 = ViewDataBinding.safeUnbox(viewModelCarInfoSeatBeltpValueGet);
                if ((dirtyFlags2 & 2056) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet2) {
                        dirtyFlags2 |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                    } else {
                        dirtyFlags2 |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                    }
                }
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet2;
                viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet2 ? 8 : 0;
            }
            dirtyFlags = dirtyFlags2;
            if ((dirtyFlags & 2064) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoTurnSpeed = viewModelCarInfo.turnSpeed;
                } else {
                    viewModelCarInfoTurnSpeed = null;
                }
                updateRegistration(4, (Observable) viewModelCarInfoTurnSpeed);
                if (viewModelCarInfoTurnSpeed != null) {
                    viewModelCarInfoTurnSpeedGet = viewModelCarInfoTurnSpeed.get();
                }
                viewModelCarInfoTurnSpeed2 = viewModelCarInfoTurnSpeed;
                stringValueOfViewModelCarInfoTurnSpeed2 = String.valueOf(viewModelCarInfoTurnSpeedGet);
            }
            if ((dirtyFlags & 2080) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoAlsmode = viewModelCarInfo.alsmode;
                } else {
                    viewModelCarInfoAlsmode = null;
                }
                updateRegistration(5, (Observable) viewModelCarInfoAlsmode);
                if (viewModelCarInfoAlsmode != null) {
                    ObservableInt observableInt = viewModelCarInfoAlsmode;
                    viewModelCarInfoAlsmodeGet2 = viewModelCarInfoAlsmode.get();
                } else {
                    ObservableInt observableInt2 = viewModelCarInfoAlsmode;
                }
            }
            if ((dirtyFlags & 2112) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoOilValue = viewModelCarInfo.oilValue;
                } else {
                    viewModelCarInfoOilValue = null;
                }
                updateRegistration(6, (Observable) viewModelCarInfoOilValue);
                if (viewModelCarInfoOilValue != null) {
                    viewModelCarInfoOilValueGet = viewModelCarInfoOilValue.get();
                }
                viewModelCarInfoOilValueJavaLangStringL = viewModelCarInfoOilValueGet + "L";
                ObservableInt observableInt3 = viewModelCarInfoOilValue;
            }
            if ((dirtyFlags & 2176) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoTurnSpeedAnge = viewModelCarInfo.turnSpeedAnge;
                } else {
                    viewModelCarInfoTurnSpeedAnge = null;
                }
                updateRegistration(7, (Observable) viewModelCarInfoTurnSpeedAnge);
                if (viewModelCarInfoTurnSpeedAnge != null) {
                    ObservableFloat observableFloat = viewModelCarInfoTurnSpeedAnge;
                    viewModelCarInfoTurnSpeedAngeGet2 = viewModelCarInfoTurnSpeedAnge.get();
                } else {
                    ObservableFloat observableFloat2 = viewModelCarInfoTurnSpeedAnge;
                }
            }
            if ((dirtyFlags & 2304) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoSpeed = viewModelCarInfo.speed;
                } else {
                    viewModelCarInfoSpeed = null;
                }
                updateRegistration(8, (Observable) viewModelCarInfoSpeed);
                if (viewModelCarInfoSpeed != null) {
                    viewModelCarInfoSpeedGet2 = viewModelCarInfoSpeed.get();
                }
                stringValueOfViewModelCarInfoSpeed = String.valueOf(viewModelCarInfoSpeedGet2);
                ObservableInt observableInt4 = viewModelCarInfoSpeed;
            }
            if ((dirtyFlags & 2560) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoBrakeValue = viewModelCarInfo.brakeValue;
                } else {
                    viewModelCarInfoBrakeValue = null;
                }
                updateRegistration(9, (Observable) viewModelCarInfoBrakeValue);
                if (viewModelCarInfoBrakeValue != null) {
                    viewModelCarInfoBrakeValueGet = viewModelCarInfoBrakeValue.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet = ViewDataBinding.safeUnbox(viewModelCarInfoBrakeValueGet);
                if ((dirtyFlags & 2560) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
                    }
                }
                viewModelCarInfoBrakeValueViewVISIBLEViewGONE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet ? 0 : 8;
                ObservableField<Boolean> observableField = viewModelCarInfoBrakeValue;
                boolean z = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet;
            }
            viewModelCarInfoSpeedGet = viewModelCarInfoSpeedGet2;
            viewModelCarInfoBrakeValueViewVISIBLEViewGONE = viewModelCarInfoBrakeValueViewVISIBLEViewGONE2;
            viewModelCarInfoTurnSpeedAngeGet = viewModelCarInfoTurnSpeedAngeGet2;
            viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE = viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE2;
            viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableId7AlsMphSpeedWtachLevelSpeedometerImageViewAndroidDrawableId7AlsSpeedWtachLevel = viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableId7AlsMphSpeedWtachLevelSpeedometerImageViewAndroidDrawableId7AlsSpeedWtachLevel2;
            viewModelCarInfoAlsmodeGet = viewModelCarInfoAlsmodeGet2;
            stringValueOfViewModelCarInfoTurnSpeed = stringValueOfViewModelCarInfoTurnSpeed2;
            viewModelCarInfoTempStrGet = viewModelCarInfoTempStrGet2;
        } else {
            viewModelCarInfoAlsmodeGet = 0;
            viewModelCarInfoSpeedGet = 0;
            viewModelCarInfoBrakeValueViewVISIBLEViewGONE = 0;
            viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE = 0;
            stringValueOfViewModelCarInfoTurnSpeed = null;
            viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableId7AlsMphSpeedWtachLevelSpeedometerImageViewAndroidDrawableId7AlsSpeedWtachLevel = null;
            viewModelCarInfoTurnSpeedAngeGet = 0.0f;
            viewModelCarInfoTempStrGet = null;
        }
        if ((dirtyFlags & 3073) != 0) {
            if (viewModel != null) {
                viewModelShowAls = viewModel.showAls;
            }
            DashboardViewModel dashboardViewModel = viewModel;
            updateRegistration(0, (Observable) viewModelShowAls);
            if (viewModelShowAls != null) {
                viewModelShowAlsGet = viewModelShowAls.get();
            }
            if ((dirtyFlags & 3073) != 0) {
                if (viewModelShowAlsGet) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
                }
            }
            viewModelShowAlsViewVISIBLEViewGONE = viewModelShowAlsGet ? 0 : 8;
        } else {
            viewModelShowAlsViewVISIBLEViewGONE = 0;
        }
        if ((dirtyFlags & 3073) != 0) {
            ObservableBoolean observableBoolean = viewModelShowAls;
            this.alsMenu.setVisibility(viewModelShowAlsViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 2560) != 0) {
            this.brakeTextView.setVisibility(viewModelCarInfoBrakeValueViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 2080) != 0) {
            this.mboundView1.setImageLevel(viewModelCarInfoAlsmodeGet);
            this.speedometerImageView.setImageLevel(viewModelCarInfoAlsmodeGet);
            this.tachometerImageView.setImageLevel(viewModelCarInfoAlsmodeGet);
        }
        if ((dirtyFlags & 2304) != 0) {
            DashboardViewModel.setALSSpeedRotation(this.mboundView3, viewModelCarInfoSpeedGet);
            TextViewBindingAdapter.setText(this.speedPointerTextView, stringValueOfViewModelCarInfoSpeed);
        }
        if ((dirtyFlags & 2112) != 0) {
            TextViewBindingAdapter.setText(this.oilTextView, viewModelCarInfoOilValueJavaLangStringL);
        }
        if ((dirtyFlags & 2056) != 0) {
            this.seatBeltTextView.setVisibility(viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 2052) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.speedometerImageView, viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableId7AlsMphSpeedWtachLevelSpeedometerImageViewAndroidDrawableId7AlsSpeedWtachLevel);
        }
        if ((dirtyFlags & 2050) != 0) {
            TextViewBindingAdapter.setText(this.tempTextView, viewModelCarInfoTempStrGet);
        }
        if ((dirtyFlags & 2176) != 0) {
            DashboardViewModel.setALSRotation(this.zspeedPointerImageView, viewModelCarInfoTurnSpeedAngeGet);
        }
        if ((dirtyFlags & 2064) != 0) {
            TextViewBindingAdapter.setText(this.zspeedPointerTextView, stringValueOfViewModelCarInfoTurnSpeed);
        }
    }
}
