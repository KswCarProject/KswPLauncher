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
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.CarInfo;
import com.wits.ksw.launcher.model.DashboardViewModel;
import com.wits.ksw.launcher.view.ID7SpeedImageView;

public class ALSDasoardBindImpl extends ALSDasoardBind {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ID7SpeedImageView mboundView1;
    private final ImageView mboundView3;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.carImageView, 13);
        sparseIntArray.put(R.id.dorr_left_fl_imageView, 14);
        sparseIntArray.put(R.id.dorr_left_fr_imageView, 15);
        sparseIntArray.put(R.id.dorr_left_rl_imageView, 16);
        sparseIntArray.put(R.id.imageView19, 17);
        sparseIntArray.put(R.id.dorr_back_imageView, 18);
        sparseIntArray.put(R.id.alsCloseView, 19);
        sparseIntArray.put(R.id.alsRadioGroup, 20);
        sparseIntArray.put(R.id.alsRadioButton1, 21);
        sparseIntArray.put(R.id.alsRadioButton2, 22);
        sparseIntArray.put(R.id.alsRadioButton3, 23);
    }

    public ALSDasoardBindImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 24, sIncludes, sViewsWithIds));
    }

    private ALSDasoardBindImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 11, bindings[19], bindings[12], bindings[21], bindings[22], bindings[23], bindings[20], bindings[10], bindings[13], bindings[18], bindings[14], bindings[15], bindings[16], bindings[17], bindings[0], bindings[8], bindings[11], (ImageView) null, bindings[4], bindings[2], bindings[5], bindings[9], bindings[6], bindings[7]);
        this.mDirtyFlags = -1;
        this.alsMenu.setTag((Object) null);
        this.brakeTextView.setTag((Object) null);
        this.linearLayout2.setTag((Object) null);
        ID7SpeedImageView iD7SpeedImageView = bindings[1];
        this.mboundView1 = iD7SpeedImageView;
        iD7SpeedImageView.setTag((Object) null);
        ImageView imageView = bindings[3];
        this.mboundView3 = imageView;
        imageView.setTag((Object) null);
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
            this.mDirtyFlags = PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
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
        if (16 != variableId) {
            return false;
        }
        setViewModel((DashboardViewModel) variable);
        return true;
    }

    public void setViewModel(DashboardViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        notifyPropertyChanged(16);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
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
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoAlsmode(ObservableInt ViewModelCarInfoAlsmode, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModelShowAls(ObservableBoolean ViewModelShowAls, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeViewModelHideOil(ObservableBoolean ViewModelHideOil, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoOilValue(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoTempStr(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoUnit(ObservableInt ViewModelCarInfoUnit, int fieldId) {
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

    private boolean onChangeViewModelCarInfoSeatBeltpValue(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoSpeed(ObservableInt ViewModelCarInfoSpeed, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoBrakeValue(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        boolean viewModelHideOilGet;
        int viewModelHideOilViewGONEViewVISIBLE;
        int viewModelCarInfoAlsmodeGet;
        String stringValueOfViewModelCarInfoTurnSpeed;
        String viewModelCarInfoTempStrGet;
        Drawable viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableId7AlsMphSpeedWtachLevelSpeedometerImageViewAndroidDrawableId7AlsSpeedWtachLevel;
        long dirtyFlags2;
        String stringValueOfViewModelCarInfoSpeed;
        float viewModelCarInfoTurnSpeedAngeGet;
        int viewModelCarInfoBrakeValueViewVISIBLEViewGONE;
        int viewModelShowAlsViewVISIBLEViewGONE;
        int viewModelHideOilViewGONEViewVISIBLE2;
        ObservableBoolean viewModelHideOil;
        boolean viewModelHideOilGet2;
        ObservableBoolean viewModelShowAls;
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
            this.mDirtyFlags = 0;
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
        if ((dirtyFlags & 6131) != 0) {
            CarInfo viewModelCarInfo = DashboardViewModel.carInfo;
            if ((dirtyFlags & 4097) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoTurnSpeed = viewModelCarInfo.turnSpeed;
                }
                viewModelCarInfoBrakeValue = null;
                updateRegistration(0, (Observable) viewModelCarInfoTurnSpeed);
                if (viewModelCarInfoTurnSpeed != null) {
                    viewModelCarInfoTurnSpeedGet = viewModelCarInfoTurnSpeed.get();
                }
                stringValueOfViewModelCarInfoTurnSpeed2 = String.valueOf(viewModelCarInfoTurnSpeedGet);
            } else {
                viewModelCarInfoBrakeValue = null;
            }
            if ((dirtyFlags & 4098) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoAlsmode = viewModelCarInfo.alsmode;
                }
                updateRegistration(1, (Observable) viewModelCarInfoAlsmode);
                if (viewModelCarInfoAlsmode != null) {
                    viewModelCarInfoAlsmodeGet2 = viewModelCarInfoAlsmode.get();
                }
            }
            if ((dirtyFlags & 4112) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoOilValue = viewModelCarInfo.oilValue;
                } else {
                    viewModelCarInfoOilValue = null;
                }
                ObservableInt observableInt = viewModelCarInfoTurnSpeed;
                updateRegistration(4, (Observable) viewModelCarInfoOilValue);
                if (viewModelCarInfoOilValue != null) {
                    viewModelCarInfoOilValueGet = viewModelCarInfoOilValue.get();
                    ObservableField<String> observableField = viewModelCarInfoOilValue;
                } else {
                    ObservableField<String> observableField2 = viewModelCarInfoOilValue;
                }
            }
            if ((dirtyFlags & 4128) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoTempStr = viewModelCarInfo.tempStr;
                } else {
                    viewModelCarInfoTempStr = null;
                }
                updateRegistration(5, (Observable) viewModelCarInfoTempStr);
                if (viewModelCarInfoTempStr != null) {
                    viewModelCarInfoTempStrGet2 = viewModelCarInfoTempStr.get();
                    ObservableField<String> observableField3 = viewModelCarInfoTempStr;
                } else {
                    ObservableField<String> observableField4 = viewModelCarInfoTempStr;
                }
            }
            if ((dirtyFlags & 4160) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoUnit = viewModelCarInfo.unit;
                } else {
                    viewModelCarInfoUnit = null;
                }
                updateRegistration(6, (Observable) viewModelCarInfoUnit);
                if (viewModelCarInfoUnit != null) {
                    viewModelCarInfoUnitGet = viewModelCarInfoUnit.get();
                }
                boolean z = true;
                if (viewModelCarInfoUnitGet != 1) {
                    z = false;
                }
                boolean viewModelCarInfoUnitInt1 = z;
                if ((dirtyFlags & 4160) != 0) {
                    if (viewModelCarInfoUnitInt1) {
                        dirtyFlags |= 4194304;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
                    }
                }
                if (viewModelCarInfoUnitInt1) {
                    dirtyFlags3 = dirtyFlags;
                    drawable = AppCompatResources.getDrawable(this.speedometerImageView.getContext(), R.drawable.id7_als_mph_speed_wtach_level);
                } else {
                    dirtyFlags3 = dirtyFlags;
                    drawable = AppCompatResources.getDrawable(this.speedometerImageView.getContext(), R.drawable.id7_als_speed_wtach_level);
                }
                viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableId7AlsMphSpeedWtachLevelSpeedometerImageViewAndroidDrawableId7AlsSpeedWtachLevel2 = drawable;
                ObservableInt observableInt2 = viewModelCarInfoUnit;
                dirtyFlags = dirtyFlags3;
            }
            if ((dirtyFlags & 4224) != 0) {
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
            if ((dirtyFlags & 4352) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoSeatBeltpValue = viewModelCarInfo.seatBeltpValue;
                } else {
                    viewModelCarInfoSeatBeltpValue = null;
                }
                updateRegistration(8, (Observable) viewModelCarInfoSeatBeltpValue);
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
                ObservableField<Boolean> observableField5 = viewModelCarInfoSeatBeltpValue;
            }
            if ((dirtyFlags & 4608) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoSpeed = viewModelCarInfo.speed;
                } else {
                    viewModelCarInfoSpeed = null;
                }
                updateRegistration(9, (Observable) viewModelCarInfoSpeed);
                if (viewModelCarInfoSpeed != null) {
                    viewModelCarInfoSpeedGet = viewModelCarInfoSpeed.get();
                }
                stringValueOfViewModelCarInfoSpeed2 = String.valueOf(viewModelCarInfoSpeedGet);
                ObservableInt observableInt3 = viewModelCarInfoSpeed;
            }
            if ((dirtyFlags & 5120) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoBrakeValue2 = viewModelCarInfo.brakeValue;
                } else {
                    viewModelCarInfoBrakeValue2 = viewModelCarInfoBrakeValue;
                }
                updateRegistration(10, (Observable) viewModelCarInfoBrakeValue2);
                if (viewModelCarInfoBrakeValue2 != null) {
                    viewModelCarInfoBrakeValueGet = viewModelCarInfoBrakeValue2.get();
                }
                int androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet = ViewDataBinding.safeUnbox(viewModelCarInfoBrakeValueGet);
                if ((dirtyFlags & 5120) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet == true) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
                    }
                }
                CarInfo carInfo = viewModelCarInfo;
                ObservableField<Boolean> observableField6 = viewModelCarInfoBrakeValue2;
                viewModelCarInfoBrakeValueViewVISIBLEViewGONE = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet != 0 ? 0 : 8;
                dirtyFlags2 = dirtyFlags;
                int viewModelCarInfoBrakeValueViewVISIBLEViewGONE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet;
                stringValueOfViewModelCarInfoSpeed = stringValueOfViewModelCarInfoSpeed2;
                viewModelCarInfoTempStrGet = viewModelCarInfoTempStrGet2;
                viewModelCarInfoTurnSpeedAngeGet = viewModelCarInfoTurnSpeedAngeGet2;
                viewModelHideOilViewGONEViewVISIBLE = 0;
                viewModelHideOilGet = false;
                viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableId7AlsMphSpeedWtachLevelSpeedometerImageViewAndroidDrawableId7AlsSpeedWtachLevel = viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableId7AlsMphSpeedWtachLevelSpeedometerImageViewAndroidDrawableId7AlsSpeedWtachLevel2;
                viewModelCarInfoAlsmodeGet = viewModelCarInfoAlsmodeGet2;
                int i = viewModelCarInfoUnitGet;
                stringValueOfViewModelCarInfoTurnSpeed = stringValueOfViewModelCarInfoTurnSpeed2;
            } else {
                CarInfo carInfo2 = viewModelCarInfo;
                dirtyFlags2 = dirtyFlags;
                stringValueOfViewModelCarInfoSpeed = stringValueOfViewModelCarInfoSpeed2;
                viewModelCarInfoBrakeValueViewVISIBLEViewGONE = 0;
                viewModelCarInfoTurnSpeedAngeGet = viewModelCarInfoTurnSpeedAngeGet2;
                viewModelHideOilViewGONEViewVISIBLE = 0;
                viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableId7AlsMphSpeedWtachLevelSpeedometerImageViewAndroidDrawableId7AlsSpeedWtachLevel = viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableId7AlsMphSpeedWtachLevelSpeedometerImageViewAndroidDrawableId7AlsSpeedWtachLevel2;
                viewModelCarInfoTempStrGet = viewModelCarInfoTempStrGet2;
                int i2 = viewModelCarInfoUnitGet;
                viewModelHideOilGet = false;
                stringValueOfViewModelCarInfoTurnSpeed = stringValueOfViewModelCarInfoTurnSpeed2;
                viewModelCarInfoAlsmodeGet = viewModelCarInfoAlsmodeGet2;
            }
        } else {
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
        }
        if ((dirtyFlags2 & 6156) != 0) {
            if ((dirtyFlags2 & 6148) != 0) {
                if (viewModel != null) {
                    Boolean bool = viewModelCarInfoSeatBeltpValueGet;
                    viewModelShowAls = viewModel.showAls;
                } else {
                    viewModelShowAls = null;
                }
                ObservableInt observableInt4 = viewModelCarInfoAlsmode;
                updateRegistration(2, (Observable) viewModelShowAls);
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
                ObservableBoolean observableBoolean = viewModelShowAls;
            } else {
                ObservableInt observableInt5 = viewModelCarInfoAlsmode;
            }
            if ((dirtyFlags2 & 6152) != 0) {
                if (viewModel != null) {
                    viewModelHideOil = viewModel.hideOil;
                } else {
                    viewModelHideOil = null;
                }
                updateRegistration(3, (Observable) viewModelHideOil);
                if (viewModelHideOil != null) {
                    viewModelHideOilGet2 = viewModelHideOil.get();
                } else {
                    viewModelHideOilGet2 = viewModelHideOilGet;
                }
                if ((dirtyFlags2 & 6152) != 0) {
                    if (viewModelHideOilGet2) {
                        dirtyFlags2 |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
                    } else {
                        dirtyFlags2 |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                    }
                }
                int i3 = viewModelHideOilGet2 ? 8 : 0;
                boolean z2 = viewModelHideOilGet2;
                viewModelShowAlsViewVISIBLEViewGONE = viewModelShowAlsViewVISIBLEViewGONE2;
                ObservableBoolean observableBoolean2 = viewModelHideOil;
                viewModelHideOilViewGONEViewVISIBLE2 = i3;
            } else {
                viewModelHideOilViewGONEViewVISIBLE2 = viewModelHideOilViewGONEViewVISIBLE;
                viewModelShowAlsViewVISIBLEViewGONE = viewModelShowAlsViewVISIBLEViewGONE2;
            }
        } else {
            ObservableInt observableInt6 = viewModelCarInfoAlsmode;
            viewModelHideOilViewGONEViewVISIBLE2 = viewModelHideOilViewGONEViewVISIBLE;
            viewModelShowAlsViewVISIBLEViewGONE = 0;
        }
        if ((dirtyFlags2 & 6148) != 0) {
            DashboardViewModel dashboardViewModel = viewModel;
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
