package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.CarInfo;
import com.wits.ksw.launcher.model.DashboardViewModel;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class DasoardBindSw600dpLandImpl extends DasoardBind {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;

    static {
        sViewsWithIds.put(R.id.tachometerImageView, 14);
        sViewsWithIds.put(R.id.constraintLayout, 15);
    }

    public DasoardBindSw600dpLandImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    private DasoardBindSw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 13, bindings[12], bindings[4], bindings[15], bindings[9], bindings[5], bindings[6], bindings[7], bindings[8], bindings[0], bindings[11], bindings[13], bindings[2], bindings[1], bindings[14], bindings[10], bindings[3]);
        this.mDirtyFlags = -1;
        this.brakeTextView.setTag((Object) null);
        this.carImageView.setTag((Object) null);
        this.dorrBackImageView.setTag((Object) null);
        this.dorrLeftFlImageView.setTag((Object) null);
        this.dorrLeftFrImageView.setTag((Object) null);
        this.dorrLeftRlImageView.setTag((Object) null);
        this.imageView19.setTag((Object) null);
        this.linearLayout2.setTag((Object) null);
        this.oilTextView.setTag((Object) null);
        this.seatBeltTextView.setTag((Object) null);
        this.speedPointerImageView.setTag((Object) null);
        this.speedometerImageView.setTag((Object) null);
        this.tempTextView.setTag((Object) null);
        this.zspeedPointerImageView.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PREPARE;
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
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelCarInfoCarImage((ObservableBoolean) object, fieldId);
            case 1:
                return onChangeViewModelCarInfoFrDoorState((ObservableField) object, fieldId);
            case 2:
                return onChangeViewModelCarInfoRrDoorState((ObservableField) object, fieldId);
            case 3:
                return onChangeViewModelCarInfoTempStr((ObservableField) object, fieldId);
            case 4:
                return onChangeViewModelCarInfoSeatBeltpValue((ObservableField) object, fieldId);
            case 5:
                return onChangeViewModelCarInfoTurnSpeed((ObservableInt) object, fieldId);
            case 6:
                return onChangeViewModelCarInfoSpeedWatch((ObservableInt) object, fieldId);
            case 7:
                return onChangeViewModelCarInfoOilValue((ObservableInt) object, fieldId);
            case 8:
                return onChangeViewModelCarInfoRlDoorState((ObservableField) object, fieldId);
            case 9:
                return onChangeViewModelCarInfoFlDoorState((ObservableField) object, fieldId);
            case 10:
                return onChangeViewModelCarInfoSpeed((ObservableInt) object, fieldId);
            case 11:
                return onChangeViewModelCarInfoBDoorState((ObservableField) object, fieldId);
            case 12:
                return onChangeViewModelCarInfoBrakeValue((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelCarInfoCarImage(ObservableBoolean ViewModelCarInfoCarImage, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoFrDoorState(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoRrDoorState(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoTempStr(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoSeatBeltpValue(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoTurnSpeed(ObservableInt ViewModelCarInfoTurnSpeed, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoSpeedWatch(ObservableInt ViewModelCarInfoSpeedWatch, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoOilValue(ObservableInt ViewModelCarInfoOilValue, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoRlDoorState(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoFlDoorState(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoSpeed(ObservableInt ViewModelCarInfoSpeed, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoBDoorState(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoBrakeValue(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        int viewModelCarInfoTurnSpeedGet;
        String viewModelCarInfoTempStrGet;
        int viewModelCarInfoOilValueGet;
        int viewModelCarInfoSpeedWatchGet;
        int viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE;
        String viewModelCarInfoOilValueJavaLangStringL;
        int viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE;
        int viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE;
        int viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE;
        int viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE;
        int viewModelCarInfoBrakeValueViewVISIBLEViewGONE;
        int viewModelCarInfoTurnSpeedGet2;
        boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet;
        ObservableField<Boolean> viewModelCarInfoBrakeValue;
        ObservableField<Boolean> viewModelCarInfoBDoorState;
        ObservableInt viewModelCarInfoSpeed;
        ObservableField<Boolean> viewModelCarInfoFlDoorState;
        ObservableField<Boolean> viewModelCarInfoRlDoorState;
        ObservableInt viewModelCarInfoOilValue;
        ObservableInt viewModelCarInfoSpeedWatch;
        ObservableInt viewModelCarInfoTurnSpeed;
        ObservableField<Boolean> viewModelCarInfoSeatBeltpValue;
        ObservableField<String> viewModelCarInfoTempStr;
        ObservableField<Boolean> viewModelCarInfoRrDoorState;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        int viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE = 0;
        ObservableBoolean viewModelCarInfoCarImage = null;
        int androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet = false;
        int androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet = false;
        ObservableField<Boolean> viewModelCarInfoFrDoorState = null;
        int viewModelCarInfoOilValueGet2 = 0;
        Boolean viewModelCarInfoRlDoorStateGet = null;
        int viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE = 0;
        boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet = false;
        ObservableField<Boolean> viewModelCarInfoRrDoorState2 = null;
        String viewModelCarInfoOilValueJavaLangStringL2 = null;
        ObservableField<String> viewModelCarInfoTempStr2 = null;
        int viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE2 = 0;
        ObservableField<Boolean> viewModelCarInfoSeatBeltpValue2 = null;
        int viewModelCarInfoCarImageGet = false;
        Boolean viewModelCarInfoFrDoorStateGet = null;
        int viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2 = 0;
        int viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE2 = 0;
        Boolean viewModelCarInfoBDoorStateGet = null;
        int viewModelCarInfoSpeedWatchGet2 = 0;
        int viewModelCarInfoSpeedGet = 0;
        Boolean viewModelCarInfoSeatBeltpValueGet = null;
        int androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet = false;
        Boolean viewModelCarInfoRrDoorStateGet = null;
        int viewModelCarInfoTurnSpeedGet3 = 0;
        Boolean viewModelCarInfoFlDoorStateGet = null;
        String viewModelCarInfoTempStrGet2 = null;
        Boolean viewModelCarInfoBrakeValueGet = null;
        int viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2 = 0;
        int viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2 = 0;
        if ((dirtyFlags & 24575) != 0) {
            CarInfo viewModelCarInfo = DashboardViewModel.carInfo;
            if ((dirtyFlags & 16385) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoCarImage = viewModelCarInfo.carImage;
                }
                updateRegistration(0, (Observable) viewModelCarInfoCarImage);
                if (viewModelCarInfoCarImage != null) {
                    viewModelCarInfoCarImageGet = viewModelCarInfoCarImage.get();
                }
                if ((dirtyFlags & 16385) != 0) {
                    if (viewModelCarInfoCarImageGet) {
                        dirtyFlags |= 4194304;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
                    }
                }
                viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2 = viewModelCarInfoCarImageGet ? 0 : 4;
            }
            if ((dirtyFlags & 16386) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoFrDoorState = viewModelCarInfo.frDoorState;
                }
                updateRegistration(1, (Observable) viewModelCarInfoFrDoorState);
                if (viewModelCarInfoFrDoorState != null) {
                    viewModelCarInfoFrDoorStateGet = viewModelCarInfoFrDoorState.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFrDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoFrDoorStateGet);
                if ((dirtyFlags & 16386) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFrDoorStateGet) {
                        dirtyFlags |= 1073741824;
                    } else {
                        dirtyFlags |= 536870912;
                    }
                }
                boolean z = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFrDoorStateGet;
                viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFrDoorStateGet ? 0 : 4;
            }
            if ((dirtyFlags & 16388) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoRrDoorState = viewModelCarInfo.rrDoorState;
                } else {
                    viewModelCarInfoRrDoorState = null;
                }
                updateRegistration(2, (Observable) viewModelCarInfoRrDoorState);
                if (viewModelCarInfoRrDoorState != null) {
                    viewModelCarInfoRrDoorStateGet = viewModelCarInfoRrDoorState.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet2 = ViewDataBinding.safeUnbox(viewModelCarInfoRrDoorStateGet);
                if ((dirtyFlags & 16388) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet2) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                    }
                }
                viewModelCarInfoRrDoorState2 = viewModelCarInfoRrDoorState;
                viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet2 ? 0 : 4;
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet2;
            }
            if ((dirtyFlags & 16392) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoTempStr = viewModelCarInfo.tempStr;
                } else {
                    viewModelCarInfoTempStr = null;
                }
                updateRegistration(3, (Observable) viewModelCarInfoTempStr);
                if (viewModelCarInfoTempStr != null) {
                    viewModelCarInfoTempStr2 = viewModelCarInfoTempStr;
                    viewModelCarInfoTempStrGet2 = viewModelCarInfoTempStr.get();
                } else {
                    viewModelCarInfoTempStr2 = viewModelCarInfoTempStr;
                }
            }
            if ((dirtyFlags & 16400) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoSeatBeltpValue = viewModelCarInfo.seatBeltpValue;
                } else {
                    viewModelCarInfoSeatBeltpValue = null;
                }
                updateRegistration(4, (Observable) viewModelCarInfoSeatBeltpValue);
                if (viewModelCarInfoSeatBeltpValue != null) {
                    viewModelCarInfoSeatBeltpValueGet = viewModelCarInfoSeatBeltpValue.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet2 = ViewDataBinding.safeUnbox(viewModelCarInfoSeatBeltpValueGet);
                if ((dirtyFlags & 16400) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet2) {
                        dirtyFlags |= 16777216;
                    } else {
                        dirtyFlags |= 8388608;
                    }
                }
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet2;
                viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet2 ? 8 : 0;
                viewModelCarInfoSeatBeltpValue2 = viewModelCarInfoSeatBeltpValue;
            }
            if ((dirtyFlags & 16416) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoTurnSpeed = viewModelCarInfo.turnSpeed;
                } else {
                    viewModelCarInfoTurnSpeed = null;
                }
                updateRegistration(5, (Observable) viewModelCarInfoTurnSpeed);
                if (viewModelCarInfoTurnSpeed != null) {
                    ObservableInt observableInt = viewModelCarInfoTurnSpeed;
                    viewModelCarInfoTurnSpeedGet3 = viewModelCarInfoTurnSpeed.get();
                } else {
                    ObservableInt observableInt2 = viewModelCarInfoTurnSpeed;
                }
            }
            if ((dirtyFlags & 16448) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoSpeedWatch = viewModelCarInfo.speedWatch;
                } else {
                    viewModelCarInfoSpeedWatch = null;
                }
                updateRegistration(6, (Observable) viewModelCarInfoSpeedWatch);
                if (viewModelCarInfoSpeedWatch != null) {
                    ObservableInt observableInt3 = viewModelCarInfoSpeedWatch;
                    viewModelCarInfoSpeedWatchGet2 = viewModelCarInfoSpeedWatch.get();
                } else {
                    ObservableInt observableInt4 = viewModelCarInfoSpeedWatch;
                }
            }
            if ((dirtyFlags & 16512) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoOilValue = viewModelCarInfo.oilValue;
                } else {
                    viewModelCarInfoOilValue = null;
                }
                updateRegistration(7, (Observable) viewModelCarInfoOilValue);
                if (viewModelCarInfoOilValue != null) {
                    viewModelCarInfoOilValueGet2 = viewModelCarInfoOilValue.get();
                }
                viewModelCarInfoOilValueJavaLangStringL2 = viewModelCarInfoOilValueGet2 + "L";
                ObservableInt observableInt5 = viewModelCarInfoOilValue;
            }
            if ((dirtyFlags & 16640) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoRlDoorState = viewModelCarInfo.rlDoorState;
                } else {
                    viewModelCarInfoRlDoorState = null;
                }
                updateRegistration(8, (Observable) viewModelCarInfoRlDoorState);
                if (viewModelCarInfoRlDoorState != null) {
                    viewModelCarInfoRlDoorStateGet = viewModelCarInfoRlDoorState.get();
                }
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoRlDoorStateGet);
                if ((dirtyFlags & 16640) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
                    }
                }
                viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet ? 0 : 4;
                ObservableField<Boolean> observableField = viewModelCarInfoRlDoorState;
            }
            if ((dirtyFlags & 16896) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoFlDoorState = viewModelCarInfo.flDoorState;
                } else {
                    viewModelCarInfoFlDoorState = null;
                }
                updateRegistration(9, (Observable) viewModelCarInfoFlDoorState);
                if (viewModelCarInfoFlDoorState != null) {
                    viewModelCarInfoFlDoorStateGet = viewModelCarInfoFlDoorState.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet2 = ViewDataBinding.safeUnbox(viewModelCarInfoFlDoorStateGet);
                if ((dirtyFlags & 16896) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet2) {
                        dirtyFlags |= 268435456;
                    } else {
                        dirtyFlags |= 134217728;
                    }
                }
                ObservableField<Boolean> observableField2 = viewModelCarInfoFlDoorState;
                viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet2 ? 0 : 4;
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet2;
            }
            if ((dirtyFlags & 17408) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoSpeed = viewModelCarInfo.speed;
                } else {
                    viewModelCarInfoSpeed = null;
                }
                updateRegistration(10, (Observable) viewModelCarInfoSpeed);
                if (viewModelCarInfoSpeed != null) {
                    ObservableInt observableInt6 = viewModelCarInfoSpeed;
                    viewModelCarInfoSpeedGet = viewModelCarInfoSpeed.get();
                } else {
                    ObservableInt observableInt7 = viewModelCarInfoSpeed;
                }
            }
            if ((dirtyFlags & 18432) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoBDoorState = viewModelCarInfo.bDoorState;
                } else {
                    viewModelCarInfoBDoorState = null;
                }
                updateRegistration(11, (Observable) viewModelCarInfoBDoorState);
                if (viewModelCarInfoBDoorState != null) {
                    viewModelCarInfoBDoorStateGet = viewModelCarInfoBDoorState.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet2 = ViewDataBinding.safeUnbox(viewModelCarInfoBDoorStateGet);
                if ((dirtyFlags & 18432) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet2) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                    }
                }
                viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet2 ? 0 : 4;
                ObservableField<Boolean> observableField3 = viewModelCarInfoBDoorState;
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet2;
            } else {
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet = false;
            }
            if ((dirtyFlags & 20480) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoBrakeValue = viewModelCarInfo.brakeValue;
                } else {
                    viewModelCarInfoBrakeValue = null;
                }
                boolean z2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet;
                updateRegistration(12, (Observable) viewModelCarInfoBrakeValue);
                if (viewModelCarInfoBrakeValue != null) {
                    viewModelCarInfoBrakeValueGet = viewModelCarInfoBrakeValue.get();
                }
                int androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet = ViewDataBinding.safeUnbox(viewModelCarInfoBrakeValueGet);
                if ((dirtyFlags & 20480) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet == true) {
                        dirtyFlags |= 67108864;
                    } else {
                        dirtyFlags |= 33554432;
                    }
                }
                ObservableField<Boolean> observableField4 = viewModelCarInfoBrakeValue;
                ObservableField<Boolean> observableField5 = viewModelCarInfoSeatBeltpValue2;
                viewModelCarInfoBrakeValueViewVISIBLEViewGONE = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet != 0 ? 0 : 8;
                viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2;
                ObservableField<String> observableField6 = viewModelCarInfoTempStr2;
                int viewModelCarInfoBrakeValueViewVISIBLEViewGONE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet;
                Boolean bool = viewModelCarInfoRlDoorStateGet;
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet3 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet;
                viewModelCarInfoTurnSpeedGet = viewModelCarInfoTurnSpeedGet3;
                viewModelCarInfoTempStrGet = viewModelCarInfoTempStrGet2;
                int viewModelCarInfoTurnSpeedGet4 = viewModelCarInfoCarImageGet;
                CarInfo carInfo = viewModelCarInfo;
                viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE = viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2;
                int viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE3 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet;
                viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2;
                ObservableField<Boolean> observableField7 = viewModelCarInfoRrDoorState2;
                ObservableBoolean observableBoolean = viewModelCarInfoCarImage;
                viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE2;
                ObservableField<Boolean> observableField8 = observableField7;
                int i = viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE2;
                int viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE3 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet;
                viewModelCarInfoOilValueJavaLangStringL = viewModelCarInfoOilValueJavaLangStringL2;
                ObservableField<Boolean> observableField9 = viewModelCarInfoFrDoorState;
                viewModelCarInfoSpeedWatchGet = viewModelCarInfoSpeedWatchGet2;
                int viewModelCarInfoSpeedWatchGet3 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet;
                viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE = i;
                int i2 = viewModelCarInfoSpeedGet;
                int viewModelCarInfoSpeedGet2 = viewModelCarInfoOilValueGet2;
                viewModelCarInfoOilValueGet = i2;
            } else {
                ObservableField<Boolean> observableField10 = viewModelCarInfoSeatBeltpValue2;
                viewModelCarInfoBrakeValueViewVISIBLEViewGONE = 0;
                viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2;
                ObservableField<String> observableField11 = viewModelCarInfoTempStr2;
                int viewModelCarInfoBrakeValueViewVISIBLEViewGONE3 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet;
                Boolean bool2 = viewModelCarInfoRlDoorStateGet;
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet4 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet;
                viewModelCarInfoTurnSpeedGet = viewModelCarInfoTurnSpeedGet3;
                viewModelCarInfoTempStrGet = viewModelCarInfoTempStrGet2;
                int viewModelCarInfoTurnSpeedGet5 = viewModelCarInfoCarImageGet;
                CarInfo carInfo2 = viewModelCarInfo;
                viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE = viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2;
                ObservableField<Boolean> observableField12 = viewModelCarInfoRrDoorState2;
                ObservableBoolean observableBoolean2 = viewModelCarInfoCarImage;
                viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE2;
                ObservableField<Boolean> observableField13 = observableField12;
                int i3 = viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE2;
                int viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE4 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet;
                viewModelCarInfoOilValueJavaLangStringL = viewModelCarInfoOilValueJavaLangStringL2;
                ObservableField<Boolean> observableField14 = viewModelCarInfoFrDoorState;
                viewModelCarInfoSpeedWatchGet = viewModelCarInfoSpeedWatchGet2;
                int viewModelCarInfoSpeedWatchGet4 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet;
                viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE = i3;
                int i4 = viewModelCarInfoSpeedGet;
                int viewModelCarInfoSpeedGet3 = viewModelCarInfoOilValueGet2;
                viewModelCarInfoOilValueGet = i4;
            }
        } else {
            viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE = 0;
            viewModelCarInfoBrakeValueViewVISIBLEViewGONE = 0;
            viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE = 0;
            viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE = 0;
            viewModelCarInfoTurnSpeedGet = 0;
            viewModelCarInfoTempStrGet = null;
            viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE = 0;
            viewModelCarInfoOilValueJavaLangStringL = null;
            viewModelCarInfoSpeedWatchGet = 0;
            viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE = 0;
            viewModelCarInfoOilValueGet = 0;
        }
        if ((dirtyFlags & 20480) != 0) {
            viewModelCarInfoTurnSpeedGet2 = viewModelCarInfoTurnSpeedGet;
            this.brakeTextView.setVisibility(viewModelCarInfoBrakeValueViewVISIBLEViewGONE);
        } else {
            viewModelCarInfoTurnSpeedGet2 = viewModelCarInfoTurnSpeedGet;
        }
        if ((dirtyFlags & 16385) != 0) {
            this.carImageView.setVisibility(viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 18432) != 0) {
            this.dorrBackImageView.setVisibility(viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 16896) != 0) {
            this.dorrLeftFlImageView.setVisibility(viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 16386) != 0) {
            this.dorrLeftFrImageView.setVisibility(viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 16640) != 0) {
            this.dorrLeftRlImageView.setVisibility(viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 16388) != 0) {
            this.imageView19.setVisibility(viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 16512) != 0) {
            TextViewBindingAdapter.setText(this.oilTextView, viewModelCarInfoOilValueJavaLangStringL);
        }
        if ((dirtyFlags & 16400) != 0) {
            this.seatBeltTextView.setVisibility(viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 16448) != 0) {
            this.speedPointerImageView.setImageLevel(viewModelCarInfoSpeedWatchGet);
            this.speedometerImageView.setImageLevel(viewModelCarInfoSpeedWatchGet);
        }
        if ((dirtyFlags & 17408) != 0) {
            LauncherViewModel.setSpeedRotation(this.speedPointerImageView, viewModelCarInfoOilValueGet);
        }
        if ((dirtyFlags & 16392) != 0) {
            TextViewBindingAdapter.setText(this.tempTextView, viewModelCarInfoTempStrGet);
        }
        if ((dirtyFlags & 16416) != 0) {
            int i5 = viewModelCarInfoBrakeValueViewVISIBLEViewGONE;
            DashboardViewModel.setBmwTyTurnSpeedRotation(this.zspeedPointerImageView, viewModelCarInfoTurnSpeedGet2);
            return;
        }
        int viewModelCarInfoBrakeValueViewVISIBLEViewGONE4 = viewModelCarInfoTurnSpeedGet2;
    }
}
