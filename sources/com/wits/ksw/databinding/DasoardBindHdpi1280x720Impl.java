package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.CarInfo;
import com.wits.ksw.launcher.model.DashboardViewModel;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class DasoardBindHdpi1280x720Impl extends DasoardBind {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.tachometerImageView, 14);
        sparseIntArray.put(R.id.constraintLayout, 15);
    }

    public DasoardBindHdpi1280x720Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    private DasoardBindHdpi1280x720Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
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

    public boolean setVariable(int variableId, Object variable) {
        if (25 != variableId) {
            return false;
        }
        setViewModel((DashboardViewModel) variable);
        return true;
    }

    public void setViewModel(DashboardViewModel ViewModel) {
        this.mViewModel = ViewModel;
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelCarInfoTurnSpeed((ObservableInt) object, fieldId);
            case 1:
                return onChangeViewModelCarInfoCarImage((ObservableBoolean) object, fieldId);
            case 2:
                return onChangeViewModelCarInfoFrDoorState((ObservableField) object, fieldId);
            case 3:
                return onChangeViewModelCarInfoSpeedWatch((ObservableInt) object, fieldId);
            case 4:
                return onChangeViewModelCarInfoOilValue((ObservableField) object, fieldId);
            case 5:
                return onChangeViewModelCarInfoRlDoorState((ObservableField) object, fieldId);
            case 6:
                return onChangeViewModelCarInfoRrDoorState((ObservableField) object, fieldId);
            case 7:
                return onChangeViewModelCarInfoTempStr((ObservableField) object, fieldId);
            case 8:
                return onChangeViewModelCarInfoSeatBeltpValue((ObservableField) object, fieldId);
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

    private boolean onChangeViewModelCarInfoTurnSpeed(ObservableInt ViewModelCarInfoTurnSpeed, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoCarImage(ObservableBoolean ViewModelCarInfoCarImage, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoFrDoorState(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoSpeedWatch(ObservableInt ViewModelCarInfoSpeedWatch, int fieldId) {
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

    private boolean onChangeViewModelCarInfoRlDoorState(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoRrDoorState(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoTempStr(ObservableField<String> observableField, int fieldId) {
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
        int viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE;
        int viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE;
        int viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE;
        int viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE;
        String viewModelCarInfoTempStrGet;
        int viewModelCarInfoBrakeValueViewVISIBLEViewGONE;
        int viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE;
        String viewModelCarInfoOilValueGet;
        int viewModelCarInfoTurnSpeedGet;
        int viewModelCarInfoTurnSpeedGet2;
        ObservableInt viewModelCarInfoTurnSpeed;
        ObservableInt viewModelCarInfoTurnSpeed2;
        ObservableField<Boolean> viewModelCarInfoBrakeValue;
        ObservableField<Boolean> viewModelCarInfoBDoorState;
        ObservableInt viewModelCarInfoSpeed;
        ObservableField<Boolean> viewModelCarInfoFlDoorState;
        ObservableField<Boolean> viewModelCarInfoSeatBeltpValue;
        ObservableField<String> viewModelCarInfoTempStr;
        ObservableField<Boolean> viewModelCarInfoRrDoorState;
        ObservableField<Boolean> viewModelCarInfoRlDoorState;
        ObservableField<String> viewModelCarInfoOilValue;
        ObservableInt viewModelCarInfoSpeedWatch;
        ObservableField<Boolean> viewModelCarInfoFrDoorState;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        int androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet = false;
        int viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE = 0;
        Boolean viewModelCarInfoBDoorStateGet = null;
        int viewModelCarInfoSpeedWatchGet = 0;
        int viewModelCarInfoSpeedGet = 0;
        int viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE = 0;
        ObservableBoolean viewModelCarInfoCarImage = null;
        int androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet = false;
        int androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet = false;
        String viewModelCarInfoOilValueGet2 = null;
        Boolean viewModelCarInfoSeatBeltpValueGet = null;
        Boolean viewModelCarInfoRlDoorStateGet = null;
        int viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE2 = 0;
        Boolean viewModelCarInfoRrDoorStateGet = null;
        int viewModelCarInfoTurnSpeedGet3 = 0;
        Boolean viewModelCarInfoFlDoorStateGet = null;
        String viewModelCarInfoTempStrGet2 = null;
        Boolean viewModelCarInfoBrakeValueGet = null;
        int viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE2 = 0;
        int viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2 = 0;
        int viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2 = 0;
        boolean viewModelCarInfoCarImageGet = false;
        Boolean viewModelCarInfoFrDoorStateGet = null;
        int viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2 = 0;
        if ((dirtyFlags & 24575) != 0) {
            CarInfo viewModelCarInfo = DashboardViewModel.carInfo;
            if ((dirtyFlags & 16385) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoTurnSpeed = viewModelCarInfo.turnSpeed;
                } else {
                    viewModelCarInfoTurnSpeed = null;
                }
                updateRegistration(0, (Observable) viewModelCarInfoTurnSpeed);
                if (viewModelCarInfoTurnSpeed != null) {
                    viewModelCarInfoTurnSpeedGet3 = viewModelCarInfoTurnSpeed.get();
                }
            } else {
                viewModelCarInfoTurnSpeed = null;
            }
            if ((dirtyFlags & 16386) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoCarImage = viewModelCarInfo.carImage;
                }
                updateRegistration(1, (Observable) viewModelCarInfoCarImage);
                if (viewModelCarInfoCarImage != null) {
                    viewModelCarInfoCarImageGet = viewModelCarInfoCarImage.get();
                }
                if ((dirtyFlags & 16386) != 0) {
                    if (viewModelCarInfoCarImageGet) {
                        dirtyFlags |= 1073741824;
                    } else {
                        dirtyFlags |= 536870912;
                    }
                }
                viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2 = viewModelCarInfoCarImageGet ? 0 : 4;
            }
            if ((dirtyFlags & 16388) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoFrDoorState = viewModelCarInfo.frDoorState;
                } else {
                    viewModelCarInfoFrDoorState = null;
                }
                viewModelCarInfoTurnSpeed2 = viewModelCarInfoTurnSpeed;
                updateRegistration(2, (Observable) viewModelCarInfoFrDoorState);
                if (viewModelCarInfoFrDoorState != null) {
                    viewModelCarInfoFrDoorStateGet = viewModelCarInfoFrDoorState.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFrDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoFrDoorStateGet);
                if ((dirtyFlags & 16388) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFrDoorStateGet) {
                        dirtyFlags |= 268435456;
                    } else {
                        dirtyFlags |= 134217728;
                    }
                }
                viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFrDoorStateGet ? 0 : 4;
                ObservableField<Boolean> observableField = viewModelCarInfoFrDoorState;
            } else {
                viewModelCarInfoTurnSpeed2 = viewModelCarInfoTurnSpeed;
            }
            if ((dirtyFlags & 16392) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoSpeedWatch = viewModelCarInfo.speedWatch;
                } else {
                    viewModelCarInfoSpeedWatch = null;
                }
                updateRegistration(3, (Observable) viewModelCarInfoSpeedWatch);
                if (viewModelCarInfoSpeedWatch != null) {
                    viewModelCarInfoSpeedWatchGet = viewModelCarInfoSpeedWatch.get();
                    ObservableInt observableInt = viewModelCarInfoSpeedWatch;
                } else {
                    ObservableInt observableInt2 = viewModelCarInfoSpeedWatch;
                }
            }
            if ((dirtyFlags & 16400) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoOilValue = viewModelCarInfo.oilValue;
                } else {
                    viewModelCarInfoOilValue = null;
                }
                updateRegistration(4, (Observable) viewModelCarInfoOilValue);
                if (viewModelCarInfoOilValue != null) {
                    viewModelCarInfoOilValueGet2 = viewModelCarInfoOilValue.get();
                    ObservableField<String> observableField2 = viewModelCarInfoOilValue;
                } else {
                    ObservableField<String> observableField3 = viewModelCarInfoOilValue;
                }
            }
            if ((dirtyFlags & 16416) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoRlDoorState = viewModelCarInfo.rlDoorState;
                } else {
                    viewModelCarInfoRlDoorState = null;
                }
                updateRegistration(5, (Observable) viewModelCarInfoRlDoorState);
                if (viewModelCarInfoRlDoorState != null) {
                    viewModelCarInfoRlDoorStateGet = viewModelCarInfoRlDoorState.get();
                }
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoRlDoorStateGet);
                if ((dirtyFlags & 16416) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet) {
                        dirtyFlags |= 16777216;
                    } else {
                        dirtyFlags |= 8388608;
                    }
                }
                viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet ? 0 : 4;
                ObservableField<Boolean> observableField4 = viewModelCarInfoRlDoorState;
            }
            if ((dirtyFlags & 16448) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoRrDoorState = viewModelCarInfo.rrDoorState;
                } else {
                    viewModelCarInfoRrDoorState = null;
                }
                updateRegistration(6, (Observable) viewModelCarInfoRrDoorState);
                if (viewModelCarInfoRrDoorState != null) {
                    viewModelCarInfoRrDoorStateGet = viewModelCarInfoRrDoorState.get();
                }
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoRrDoorStateGet);
                if ((dirtyFlags & 16448) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
                    }
                }
                viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet ? 0 : 4;
                ObservableField<Boolean> observableField5 = viewModelCarInfoRrDoorState;
            }
            if ((dirtyFlags & 16512) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoTempStr = viewModelCarInfo.tempStr;
                } else {
                    viewModelCarInfoTempStr = null;
                }
                updateRegistration(7, (Observable) viewModelCarInfoTempStr);
                if (viewModelCarInfoTempStr != null) {
                    viewModelCarInfoTempStrGet2 = viewModelCarInfoTempStr.get();
                    ObservableField<String> observableField6 = viewModelCarInfoTempStr;
                } else {
                    ObservableField<String> observableField7 = viewModelCarInfoTempStr;
                }
            }
            if ((dirtyFlags & 16640) != 0) {
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
                if ((dirtyFlags & 16640) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                    }
                }
                viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet ? 8 : 0;
                ObservableField<Boolean> observableField8 = viewModelCarInfoSeatBeltpValue;
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
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoFlDoorStateGet);
                if ((dirtyFlags & 16896) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet) {
                        dirtyFlags |= 67108864;
                    } else {
                        dirtyFlags |= 33554432;
                    }
                }
                viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet ? 0 : 4;
                ObservableField<Boolean> observableField9 = viewModelCarInfoFlDoorState;
            }
            if ((dirtyFlags & 17408) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoSpeed = viewModelCarInfo.speed;
                } else {
                    viewModelCarInfoSpeed = null;
                }
                updateRegistration(10, (Observable) viewModelCarInfoSpeed);
                if (viewModelCarInfoSpeed != null) {
                    viewModelCarInfoSpeedGet = viewModelCarInfoSpeed.get();
                    ObservableInt observableInt3 = viewModelCarInfoSpeed;
                } else {
                    ObservableInt observableInt4 = viewModelCarInfoSpeed;
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
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoBDoorStateGet);
                if ((dirtyFlags & 18432) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                    }
                }
                viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet ? 0 : 4;
                ObservableField<Boolean> observableField10 = viewModelCarInfoBDoorState;
            }
            if ((dirtyFlags & 20480) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoBrakeValue = viewModelCarInfo.brakeValue;
                } else {
                    viewModelCarInfoBrakeValue = null;
                }
                updateRegistration(12, (Observable) viewModelCarInfoBrakeValue);
                if (viewModelCarInfoBrakeValue != null) {
                    viewModelCarInfoBrakeValueGet = viewModelCarInfoBrakeValue.get();
                }
                int androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet = ViewDataBinding.safeUnbox(viewModelCarInfoBrakeValueGet);
                if ((dirtyFlags & 20480) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet == true) {
                        dirtyFlags |= 4194304;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
                    }
                }
                ObservableField<Boolean> observableField11 = viewModelCarInfoBrakeValue;
                CarInfo carInfo = viewModelCarInfo;
                viewModelCarInfoOilValueGet = viewModelCarInfoOilValueGet2;
                viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoTurnSpeedGet = viewModelCarInfoTurnSpeedGet3;
                ObservableInt observableInt5 = viewModelCarInfoTurnSpeed2;
                int viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE3 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet;
                int viewModelCarInfoTurnSpeedGet4 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet;
                viewModelCarInfoBrakeValueViewVISIBLEViewGONE = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet != 0 ? 0 : 8;
                viewModelCarInfoTempStrGet = viewModelCarInfoTempStrGet2;
                Boolean bool = viewModelCarInfoBDoorStateGet;
                ObservableBoolean observableBoolean = viewModelCarInfoCarImage;
                viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2;
                int viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE3 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet;
                int viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE3 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet;
                viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE = viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2;
            } else {
                CarInfo carInfo2 = viewModelCarInfo;
                viewModelCarInfoOilValueGet = viewModelCarInfoOilValueGet2;
                viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoTurnSpeedGet = viewModelCarInfoTurnSpeedGet3;
                ObservableInt observableInt6 = viewModelCarInfoTurnSpeed2;
                int viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE4 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet;
                viewModelCarInfoBrakeValueViewVISIBLEViewGONE = 0;
                viewModelCarInfoTempStrGet = viewModelCarInfoTempStrGet2;
                Boolean bool2 = viewModelCarInfoBDoorStateGet;
                ObservableBoolean observableBoolean2 = viewModelCarInfoCarImage;
                viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2;
                int viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE4 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet;
                int viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE4 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet;
                viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE = viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2;
            }
        } else {
            viewModelCarInfoOilValueGet = null;
            viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE = 0;
            viewModelCarInfoTurnSpeedGet = 0;
            viewModelCarInfoBrakeValueViewVISIBLEViewGONE = 0;
            viewModelCarInfoTempStrGet = null;
            viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE = 0;
            viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE = 0;
            viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE = 0;
            viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE = 0;
        }
        if ((dirtyFlags & 20480) != 0) {
            viewModelCarInfoTurnSpeedGet2 = viewModelCarInfoTurnSpeedGet;
            this.brakeTextView.setVisibility(viewModelCarInfoBrakeValueViewVISIBLEViewGONE);
        } else {
            viewModelCarInfoTurnSpeedGet2 = viewModelCarInfoTurnSpeedGet;
        }
        if ((dirtyFlags & 16386) != 0) {
            this.carImageView.setVisibility(viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 18432) != 0) {
            this.dorrBackImageView.setVisibility(viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 16896) != 0) {
            this.dorrLeftFlImageView.setVisibility(viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 16388) != 0) {
            this.dorrLeftFrImageView.setVisibility(viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 16416) != 0) {
            this.dorrLeftRlImageView.setVisibility(viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 16448) != 0) {
            this.imageView19.setVisibility(viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 16400) != 0) {
            TextViewBindingAdapter.setText(this.oilTextView, viewModelCarInfoOilValueGet);
        }
        if ((dirtyFlags & 16640) != 0) {
            this.seatBeltTextView.setVisibility(viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 16392) != 0) {
            this.speedPointerImageView.setImageLevel(viewModelCarInfoSpeedWatchGet);
            this.speedometerImageView.setImageLevel(viewModelCarInfoSpeedWatchGet);
        }
        if ((dirtyFlags & 17408) != 0) {
            LauncherViewModel.setSpeedRotation(this.speedPointerImageView, viewModelCarInfoSpeedGet);
        }
        if ((dirtyFlags & 16512) != 0) {
            TextViewBindingAdapter.setText(this.tempTextView, viewModelCarInfoTempStrGet);
        }
        if ((dirtyFlags & 16385) != 0) {
            long j = dirtyFlags;
            DashboardViewModel.setBmwTyTurnSpeedRotation(this.zspeedPointerImageView, viewModelCarInfoTurnSpeedGet2);
            return;
        }
        int i = viewModelCarInfoTurnSpeedGet2;
    }
}
