package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.p001v4.media.session.PlaybackStateCompat;
import android.support.p004v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.DashboardViewModel;
import com.wits.ksw.launcher.view.ID7SpeedImageView;

/* loaded from: classes7.dex */
public class ALSDasoardBindHdpi1920x720Impl extends ALSDasoardBind {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ID7SpeedImageView mboundView1;
    private final ImageView mboundView3;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.carImageView, 13);
        sparseIntArray.put(C0899R.C0901id.dorr_left_fl_imageView, 14);
        sparseIntArray.put(C0899R.C0901id.dorr_left_fr_imageView, 15);
        sparseIntArray.put(C0899R.C0901id.dorr_left_rl_imageView, 16);
        sparseIntArray.put(C0899R.C0901id.imageView19, 17);
        sparseIntArray.put(C0899R.C0901id.dorr_back_imageView, 18);
        sparseIntArray.put(C0899R.C0901id.alsCloseView, 19);
        sparseIntArray.put(C0899R.C0901id.alsRadioGroup, 20);
        sparseIntArray.put(C0899R.C0901id.alsRadioButton1, 21);
        sparseIntArray.put(C0899R.C0901id.alsRadioButton2, 22);
        sparseIntArray.put(C0899R.C0901id.alsRadioButton3, 23);
    }

    public ALSDasoardBindHdpi1920x720Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 24, sIncludes, sViewsWithIds));
    }

    private ALSDasoardBindHdpi1920x720Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 11, (ImageView) bindings[19], (ConstraintLayout) bindings[12], (RadioButton) bindings[21], (RadioButton) bindings[22], (RadioButton) bindings[23], (RadioGroup) bindings[20], (ImageView) bindings[10], (ImageView) bindings[13], (ImageView) bindings[18], (ImageView) bindings[14], (ImageView) bindings[15], (ImageView) bindings[16], (ImageView) bindings[17], (ConstraintLayout) bindings[0], (TextView) bindings[8], (ImageView) bindings[11], null, (TextView) bindings[4], (ID7SpeedImageView) bindings[2], (ID7SpeedImageView) bindings[5], (TextView) bindings[9], (ImageView) bindings[6], (TextView) bindings[7]);
        this.mDirtyFlags = -1L;
        this.alsMenu.setTag(null);
        this.brakeTextView.setTag(null);
        this.linearLayout2.setTag(null);
        ID7SpeedImageView iD7SpeedImageView = (ID7SpeedImageView) bindings[1];
        this.mboundView1 = iD7SpeedImageView;
        iD7SpeedImageView.setTag(null);
        ImageView imageView = (ImageView) bindings[3];
        this.mboundView3 = imageView;
        imageView.setTag(null);
        this.oilTextView.setTag(null);
        this.seatBeltTextView.setTag(null);
        this.speedPointerTextView.setTag(null);
        this.speedometerImageView.setTag(null);
        this.tachometerImageView.setTag(null);
        this.tempTextView.setTag(null);
        this.zspeedPointerImageView.setTag(null);
        this.zspeedPointerTextView.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
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
        if (25 == variableId) {
            setViewModel((DashboardViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.ALSDasoardBind
    public void setViewModel(DashboardViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        notifyPropertyChanged(25);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelCarInfoTurnSpeed((ObservableInt) object, fieldId);
            case 1:
                return onChangeViewModelCarInfoAlsmode((ObservableInt) object, fieldId);
            case 2:
                return onChangeViewModelShowAls((ObservableBoolean) object, fieldId);
            case 3:
                return onChangeViewModelHideOil((ObservableBoolean) object, fieldId);
            case 4:
                return onChangeViewModelCarInfoOilValue((ObservableField) object, fieldId);
            case 5:
                return onChangeViewModelCarInfoTempStr((ObservableField) object, fieldId);
            case 6:
                return onChangeViewModelCarInfoUnit((ObservableInt) object, fieldId);
            case 7:
                return onChangeViewModelCarInfoTurnSpeedAnge((ObservableFloat) object, fieldId);
            case 8:
                return onChangeViewModelCarInfoSeatBeltpValue((ObservableField) object, fieldId);
            case 9:
                return onChangeViewModelCarInfoSpeed((ObservableInt) object, fieldId);
            case 10:
                return onChangeViewModelCarInfoBrakeValue((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelCarInfoTurnSpeed(ObservableInt ViewModelCarInfoTurnSpeed, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoAlsmode(ObservableInt ViewModelCarInfoAlsmode, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelShowAls(ObservableBoolean ViewModelShowAls, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelHideOil(ObservableBoolean ViewModelHideOil, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoOilValue(ObservableField<String> ViewModelCarInfoOilValue, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoTempStr(ObservableField<String> ViewModelCarInfoTempStr, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoUnit(ObservableInt ViewModelCarInfoUnit, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoTurnSpeedAnge(ObservableFloat ViewModelCarInfoTurnSpeedAnge, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoSeatBeltpValue(ObservableField<Boolean> ViewModelCarInfoSeatBeltpValue, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoSpeed(ObservableInt ViewModelCarInfoSpeed, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoBrakeValue(ObservableField<Boolean> ViewModelCarInfoBrakeValue, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
            }
            return true;
        }
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        int viewModelCarInfoBrakeValueViewVISIBLEViewGONE;
        long dirtyFlags2;
        String stringValueOfViewModelCarInfoSpeed;
        String viewModelCarInfoTempStrGet;
        float viewModelCarInfoTurnSpeedAngeGet;
        int viewModelHideOilViewGONEViewVISIBLE;
        boolean viewModelHideOilGet;
        Drawable viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableId7AlsMphSpeedWtachLevelSpeedometerImageViewAndroidDrawableId7AlsSpeedWtachLevel;
        int viewModelCarInfoAlsmodeGet;
        String stringValueOfViewModelCarInfoTurnSpeed;
        int viewModelHideOilViewGONEViewVISIBLE2;
        int viewModelShowAlsViewVISIBLEViewGONE;
        ObservableBoolean viewModelHideOil;
        boolean viewModelHideOilGet2;
        ObservableField<Boolean> viewModelCarInfoBrakeValue;
        ObservableField<Boolean> viewModelCarInfoBrakeValue2;
        ObservableInt viewModelCarInfoSpeed;
        ObservableField<Boolean> viewModelCarInfoSeatBeltpValue;
        ObservableFloat viewModelCarInfoTurnSpeedAnge;
        ObservableInt viewModelCarInfoUnit;
        long dirtyFlags3;
        Drawable drawable;
        ObservableField<String> viewModelCarInfoTempStr;
        ObservableField<String> viewModelCarInfoOilValue;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        ObservableInt viewModelCarInfoTurnSpeed = null;
        int viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE = 0;
        int viewModelCarInfoUnitGet = 0;
        int viewModelCarInfoSpeedGet = 0;
        String viewModelCarInfoOilValueGet = null;
        Boolean viewModelCarInfoSeatBeltpValueGet = null;
        ObservableInt viewModelCarInfoAlsmode = null;
        String stringValueOfViewModelCarInfoSpeed2 = null;
        int viewModelShowAlsViewVISIBLEViewGONE2 = 0;
        int viewModelCarInfoTurnSpeedGet = 0;
        boolean viewModelShowAlsGet = false;
        Drawable viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableId7AlsMphSpeedWtachLevelSpeedometerImageViewAndroidDrawableId7AlsSpeedWtachLevel2 = null;
        String viewModelCarInfoTempStrGet2 = null;
        Boolean viewModelCarInfoBrakeValueGet = null;
        String stringValueOfViewModelCarInfoTurnSpeed2 = null;
        DashboardViewModel viewModel = this.mViewModel;
        int viewModelCarInfoAlsmodeGet2 = 0;
        float viewModelCarInfoTurnSpeedAngeGet2 = 0.0f;
        if ((dirtyFlags & 6131) == 0) {
            viewModelCarInfoBrakeValueViewVISIBLEViewGONE = 0;
            dirtyFlags2 = dirtyFlags;
            stringValueOfViewModelCarInfoSpeed = null;
            viewModelCarInfoTempStrGet = null;
            viewModelCarInfoTurnSpeedAngeGet = 0.0f;
            viewModelHideOilViewGONEViewVISIBLE = 0;
            viewModelHideOilGet = false;
            viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableId7AlsMphSpeedWtachLevelSpeedometerImageViewAndroidDrawableId7AlsSpeedWtachLevel = null;
            viewModelCarInfoAlsmodeGet = 0;
            stringValueOfViewModelCarInfoTurnSpeed = null;
        } else {
            com.wits.ksw.launcher.bean.CarInfo viewModelCarInfo = DashboardViewModel.carInfo;
            if ((dirtyFlags & 4097) == 0) {
                viewModelCarInfoBrakeValue = null;
            } else {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoTurnSpeed = viewModelCarInfo.turnSpeed;
                }
                viewModelCarInfoBrakeValue = null;
                updateRegistration(0, viewModelCarInfoTurnSpeed);
                if (viewModelCarInfoTurnSpeed != null) {
                    viewModelCarInfoTurnSpeedGet = viewModelCarInfoTurnSpeed.get();
                }
                stringValueOfViewModelCarInfoTurnSpeed2 = String.valueOf(viewModelCarInfoTurnSpeedGet);
            }
            if ((dirtyFlags & 4098) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoAlsmode = viewModelCarInfo.alsmode;
                }
                updateRegistration(1, viewModelCarInfoAlsmode);
                if (viewModelCarInfoAlsmode != null) {
                    viewModelCarInfoAlsmodeGet2 = viewModelCarInfoAlsmode.get();
                }
            }
            if ((dirtyFlags & 4112) != 0) {
                if (viewModelCarInfo == null) {
                    viewModelCarInfoOilValue = null;
                } else {
                    viewModelCarInfoOilValue = viewModelCarInfo.oilValue;
                }
                updateRegistration(4, viewModelCarInfoOilValue);
                if (viewModelCarInfoOilValue != null) {
                    viewModelCarInfoOilValueGet = viewModelCarInfoOilValue.get();
                }
            }
            if ((dirtyFlags & 4128) != 0) {
                if (viewModelCarInfo == null) {
                    viewModelCarInfoTempStr = null;
                } else {
                    viewModelCarInfoTempStr = viewModelCarInfo.tempStr;
                }
                updateRegistration(5, viewModelCarInfoTempStr);
                if (viewModelCarInfoTempStr != null) {
                    viewModelCarInfoTempStrGet2 = viewModelCarInfoTempStr.get();
                }
            }
            if ((dirtyFlags & 4160) != 0) {
                if (viewModelCarInfo == null) {
                    viewModelCarInfoUnit = null;
                } else {
                    viewModelCarInfoUnit = viewModelCarInfo.unit;
                }
                updateRegistration(6, viewModelCarInfoUnit);
                if (viewModelCarInfoUnit != null) {
                    viewModelCarInfoUnitGet = viewModelCarInfoUnit.get();
                }
                boolean viewModelCarInfoUnitInt1 = viewModelCarInfoUnitGet == 1;
                if ((dirtyFlags & 4160) != 0) {
                    if (viewModelCarInfoUnitInt1) {
                        dirtyFlags |= 4194304;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
                    }
                }
                if (viewModelCarInfoUnitInt1) {
                    dirtyFlags3 = dirtyFlags;
                    drawable = AppCompatResources.getDrawable(this.speedometerImageView.getContext(), C0899R.C0900drawable.id7_als_mph_speed_wtach_level);
                } else {
                    dirtyFlags3 = dirtyFlags;
                    drawable = AppCompatResources.getDrawable(this.speedometerImageView.getContext(), C0899R.C0900drawable.id7_als_speed_wtach_level);
                }
                viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableId7AlsMphSpeedWtachLevelSpeedometerImageViewAndroidDrawableId7AlsSpeedWtachLevel2 = drawable;
                dirtyFlags = dirtyFlags3;
            }
            if ((dirtyFlags & 4224) != 0) {
                if (viewModelCarInfo == null) {
                    viewModelCarInfoTurnSpeedAnge = null;
                } else {
                    viewModelCarInfoTurnSpeedAnge = viewModelCarInfo.turnSpeedAnge;
                }
                updateRegistration(7, viewModelCarInfoTurnSpeedAnge);
                if (viewModelCarInfoTurnSpeedAnge != null) {
                    viewModelCarInfoTurnSpeedAngeGet2 = viewModelCarInfoTurnSpeedAnge.get();
                }
            }
            if ((dirtyFlags & 4352) != 0) {
                if (viewModelCarInfo == null) {
                    viewModelCarInfoSeatBeltpValue = null;
                } else {
                    viewModelCarInfoSeatBeltpValue = viewModelCarInfo.seatBeltpValue;
                }
                updateRegistration(8, viewModelCarInfoSeatBeltpValue);
                if (viewModelCarInfoSeatBeltpValue != null) {
                    viewModelCarInfoSeatBeltpValueGet = viewModelCarInfoSeatBeltpValue.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet = ViewDataBinding.safeUnbox(viewModelCarInfoSeatBeltpValueGet);
                if ((dirtyFlags & 4352) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                    }
                }
                viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet ? 8 : 0;
            }
            if ((dirtyFlags & 4608) != 0) {
                if (viewModelCarInfo == null) {
                    viewModelCarInfoSpeed = null;
                } else {
                    viewModelCarInfoSpeed = viewModelCarInfo.speed;
                }
                updateRegistration(9, viewModelCarInfoSpeed);
                if (viewModelCarInfoSpeed != null) {
                    viewModelCarInfoSpeedGet = viewModelCarInfoSpeed.get();
                }
                stringValueOfViewModelCarInfoSpeed2 = String.valueOf(viewModelCarInfoSpeedGet);
            }
            if ((dirtyFlags & 5120) == 0) {
                dirtyFlags2 = dirtyFlags;
                stringValueOfViewModelCarInfoSpeed = stringValueOfViewModelCarInfoSpeed2;
                viewModelCarInfoBrakeValueViewVISIBLEViewGONE = 0;
                viewModelCarInfoTurnSpeedAngeGet = viewModelCarInfoTurnSpeedAngeGet2;
                viewModelHideOilViewGONEViewVISIBLE = 0;
                viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableId7AlsMphSpeedWtachLevelSpeedometerImageViewAndroidDrawableId7AlsSpeedWtachLevel = viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableId7AlsMphSpeedWtachLevelSpeedometerImageViewAndroidDrawableId7AlsSpeedWtachLevel2;
                viewModelCarInfoTempStrGet = viewModelCarInfoTempStrGet2;
                viewModelHideOilGet = false;
                stringValueOfViewModelCarInfoTurnSpeed = stringValueOfViewModelCarInfoTurnSpeed2;
                viewModelCarInfoAlsmodeGet = viewModelCarInfoAlsmodeGet2;
            } else {
                if (viewModelCarInfo == null) {
                    viewModelCarInfoBrakeValue2 = viewModelCarInfoBrakeValue;
                } else {
                    viewModelCarInfoBrakeValue2 = viewModelCarInfo.brakeValue;
                }
                updateRegistration(10, viewModelCarInfoBrakeValue2);
                if (viewModelCarInfoBrakeValue2 != null) {
                    Boolean viewModelCarInfoBrakeValueGet2 = viewModelCarInfoBrakeValue2.get();
                    viewModelCarInfoBrakeValueGet = viewModelCarInfoBrakeValueGet2;
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet = ViewDataBinding.safeUnbox(viewModelCarInfoBrakeValueGet);
                if ((dirtyFlags & 5120) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
                    }
                }
                int viewModelCarInfoBrakeValueViewVISIBLEViewGONE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet ? 0 : 8;
                viewModelCarInfoBrakeValueViewVISIBLEViewGONE = viewModelCarInfoBrakeValueViewVISIBLEViewGONE2;
                dirtyFlags2 = dirtyFlags;
                stringValueOfViewModelCarInfoSpeed = stringValueOfViewModelCarInfoSpeed2;
                viewModelCarInfoTempStrGet = viewModelCarInfoTempStrGet2;
                viewModelCarInfoTurnSpeedAngeGet = viewModelCarInfoTurnSpeedAngeGet2;
                viewModelHideOilViewGONEViewVISIBLE = 0;
                viewModelHideOilGet = false;
                viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableId7AlsMphSpeedWtachLevelSpeedometerImageViewAndroidDrawableId7AlsSpeedWtachLevel = viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableId7AlsMphSpeedWtachLevelSpeedometerImageViewAndroidDrawableId7AlsSpeedWtachLevel2;
                viewModelCarInfoAlsmodeGet = viewModelCarInfoAlsmodeGet2;
                stringValueOfViewModelCarInfoTurnSpeed = stringValueOfViewModelCarInfoTurnSpeed2;
            }
        }
        if ((dirtyFlags2 & 6156) == 0) {
            viewModelHideOilViewGONEViewVISIBLE2 = viewModelHideOilViewGONEViewVISIBLE;
            viewModelShowAlsViewVISIBLEViewGONE = 0;
        } else {
            if ((dirtyFlags2 & 6148) != 0) {
                ObservableBoolean viewModelShowAls = viewModel != null ? viewModel.showAls : null;
                updateRegistration(2, viewModelShowAls);
                if (viewModelShowAls != null) {
                    viewModelShowAlsGet = viewModelShowAls.get();
                }
                if ((dirtyFlags2 & 6148) != 0) {
                    if (viewModelShowAlsGet) {
                        dirtyFlags2 |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                    } else {
                        dirtyFlags2 |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                    }
                }
                viewModelShowAlsViewVISIBLEViewGONE2 = viewModelShowAlsGet ? 0 : 8;
            }
            if ((dirtyFlags2 & 6152) == 0) {
                viewModelHideOilViewGONEViewVISIBLE2 = viewModelHideOilViewGONEViewVISIBLE;
                viewModelShowAlsViewVISIBLEViewGONE = viewModelShowAlsViewVISIBLEViewGONE2;
            } else {
                if (viewModel == null) {
                    viewModelHideOil = null;
                } else {
                    viewModelHideOil = viewModel.hideOil;
                }
                updateRegistration(3, viewModelHideOil);
                if (viewModelHideOil == null) {
                    viewModelHideOilGet2 = viewModelHideOilGet;
                } else {
                    viewModelHideOilGet2 = viewModelHideOil.get();
                }
                if ((dirtyFlags2 & 6152) != 0) {
                    if (viewModelHideOilGet2) {
                        dirtyFlags2 |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
                    } else {
                        dirtyFlags2 |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                    }
                }
                int i = viewModelHideOilGet2 ? 8 : 0;
                viewModelShowAlsViewVISIBLEViewGONE = viewModelShowAlsViewVISIBLEViewGONE2;
                viewModelHideOilViewGONEViewVISIBLE2 = i;
            }
        }
        if ((dirtyFlags2 & 6148) != 0) {
            this.alsMenu.setVisibility(viewModelShowAlsViewVISIBLEViewGONE);
        }
        if ((dirtyFlags2 & 5120) != 0) {
            this.brakeTextView.setVisibility(viewModelCarInfoBrakeValueViewVISIBLEViewGONE);
        }
        if ((dirtyFlags2 & 4098) != 0) {
            this.mboundView1.setImageLevel(viewModelCarInfoAlsmodeGet);
            this.speedometerImageView.setImageLevel(viewModelCarInfoAlsmodeGet);
            this.tachometerImageView.setImageLevel(viewModelCarInfoAlsmodeGet);
        }
        if ((dirtyFlags2 & 4608) != 0) {
            DashboardViewModel.setALSSpeedRotation(this.mboundView3, viewModelCarInfoSpeedGet);
            TextViewBindingAdapter.setText(this.speedPointerTextView, stringValueOfViewModelCarInfoSpeed);
        }
        if ((dirtyFlags2 & 6152) != 0) {
            this.oilTextView.setVisibility(viewModelHideOilViewGONEViewVISIBLE2);
        }
        if ((dirtyFlags2 & 4112) != 0) {
            TextViewBindingAdapter.setText(this.oilTextView, viewModelCarInfoOilValueGet);
        }
        if ((dirtyFlags2 & 4352) != 0) {
            this.seatBeltTextView.setVisibility(viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE);
        }
        if ((dirtyFlags2 & 4160) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.speedometerImageView, viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableId7AlsMphSpeedWtachLevelSpeedometerImageViewAndroidDrawableId7AlsSpeedWtachLevel);
        }
        if ((dirtyFlags2 & 4128) != 0) {
            TextViewBindingAdapter.setText(this.tempTextView, viewModelCarInfoTempStrGet);
        }
        if ((dirtyFlags2 & 4224) != 0) {
            DashboardViewModel.setALSRotation(this.zspeedPointerImageView, viewModelCarInfoTurnSpeedAngeGet);
        }
        if ((dirtyFlags2 & 4097) != 0) {
            TextViewBindingAdapter.setText(this.zspeedPointerTextView, stringValueOfViewModelCarInfoTurnSpeed);
        }
    }
}
