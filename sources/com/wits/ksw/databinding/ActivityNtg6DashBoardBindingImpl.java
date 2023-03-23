package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
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

public class ActivityNtg6DashBoardBindingImpl extends ActivityNtg6DashBoardBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.ntg55_pointer_speed_highlight_ImageView, 13);
        sparseIntArray.put(R.id.tachometerImageView, 14);
        sparseIntArray.put(R.id.ntg55_pointer_tachometer_highlight_imageView, 15);
    }

    public ActivityNtg6DashBoardBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    private ActivityNtg6DashBoardBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 12, bindings[11], bindings[5], bindings[10], bindings[6], bindings[7], bindings[8], bindings[9], bindings[0], bindings[13], bindings[15], bindings[12], bindings[3], bindings[2], bindings[14], bindings[1], bindings[4]);
        this.mDirtyFlags = -1;
        this.brakeTextView.setTag((Object) null);
        this.carImageView.setTag((Object) null);
        this.dorrBackImageView.setTag((Object) null);
        this.dorrLeftFlImageView.setTag((Object) null);
        this.dorrLeftFrImageView.setTag((Object) null);
        this.dorrLeftRlImageView.setTag((Object) null);
        this.imageView19.setTag((Object) null);
        this.linearLayout2.setTag((Object) null);
        this.seatBeltTextView.setTag((Object) null);
        this.speedPointerImageView.setTag((Object) null);
        this.speedometerImageView.setTag((Object) null);
        this.temperatureTextView.setTag((Object) null);
        this.zspeedPointerImageView.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PLAY_FROM_URI;
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
                return onChangeViewModelCarInfoCarImage((ObservableBoolean) object, fieldId);
            case 1:
                return onChangeViewModelCarInfoFrDoorState((ObservableField) object, fieldId);
            case 2:
                return onChangeViewModelCarInfoSpeedWatch((ObservableInt) object, fieldId);
            case 3:
                return onChangeViewModelCarInfoRlDoorState((ObservableField) object, fieldId);
            case 4:
                return onChangeViewModelCarInfoRrDoorState((ObservableField) object, fieldId);
            case 5:
                return onChangeViewModelCarInfoTempStr((ObservableField) object, fieldId);
            case 6:
                return onChangeViewModelCarInfoTurnSpeedAnge((ObservableFloat) object, fieldId);
            case 7:
                return onChangeViewModelCarInfoSeatBeltpValue((ObservableField) object, fieldId);
            case 8:
                return onChangeViewModelCarInfoFlDoorState((ObservableField) object, fieldId);
            case 9:
                return onChangeViewModelCarInfoSpeed((ObservableInt) object, fieldId);
            case 10:
                return onChangeViewModelCarInfoBDoorState((ObservableField) object, fieldId);
            case 11:
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

    private boolean onChangeViewModelCarInfoSpeedWatch(ObservableInt ViewModelCarInfoSpeedWatch, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoRlDoorState(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoRrDoorState(ObservableField<Boolean> observableField, int fieldId) {
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

    private boolean onChangeViewModelCarInfoTurnSpeedAnge(ObservableFloat ViewModelCarInfoTurnSpeedAnge, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoSeatBeltpValue(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoFlDoorState(ObservableField<Boolean> observableField, int fieldId) {
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

    private boolean onChangeViewModelCarInfoBDoorState(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoBrakeValue(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
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
        float viewModelCarInfoTurnSpeedAngeGet;
        ObservableField<Boolean> viewModelCarInfoBrakeValue;
        ObservableField<Boolean> viewModelCarInfoBDoorState;
        ObservableInt viewModelCarInfoSpeed;
        ObservableField<Boolean> viewModelCarInfoFlDoorState;
        ObservableField<Boolean> viewModelCarInfoSeatBeltpValue;
        ObservableFloat viewModelCarInfoTurnSpeedAnge;
        ObservableField<String> viewModelCarInfoTempStr;
        ObservableField<Boolean> viewModelCarInfoRrDoorState;
        ObservableField<Boolean> viewModelCarInfoRlDoorState;
        ObservableInt viewModelCarInfoSpeedWatch;
        ObservableField<Boolean> viewModelCarInfoFrDoorState;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        float viewModelCarInfoTurnSpeedAngeGet2 = 0.0f;
        int androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet = false;
        int viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE = 0;
        Boolean viewModelCarInfoBDoorStateGet = null;
        int viewModelCarInfoSpeedWatchGet = 0;
        int viewModelCarInfoSpeedGet = 0;
        int viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE = 0;
        ObservableBoolean viewModelCarInfoCarImage = null;
        int androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet = false;
        boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet = false;
        Boolean viewModelCarInfoSeatBeltpValueGet = null;
        Boolean viewModelCarInfoRlDoorStateGet = null;
        int viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE2 = 0;
        Boolean viewModelCarInfoRrDoorStateGet = null;
        Boolean viewModelCarInfoFlDoorStateGet = null;
        String viewModelCarInfoTempStrGet2 = null;
        Boolean viewModelCarInfoBrakeValueGet = null;
        int viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE2 = 0;
        int viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2 = 0;
        int viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2 = 0;
        boolean viewModelCarInfoCarImageGet = false;
        Boolean viewModelCarInfoFrDoorStateGet = null;
        int viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2 = 0;
        if ((dirtyFlags & 12287) != 0) {
            CarInfo viewModelCarInfo = DashboardViewModel.carInfo;
            if ((dirtyFlags & 8193) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoCarImage = viewModelCarInfo.carImage;
                }
                updateRegistration(0, (Observable) viewModelCarInfoCarImage);
                if (viewModelCarInfoCarImage != null) {
                    viewModelCarInfoCarImageGet = viewModelCarInfoCarImage.get();
                }
                if ((dirtyFlags & 8193) != 0) {
                    if (viewModelCarInfoCarImageGet) {
                        dirtyFlags |= 536870912;
                    } else {
                        dirtyFlags |= 268435456;
                    }
                }
                viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2 = viewModelCarInfoCarImageGet ? 0 : 4;
            }
            if ((dirtyFlags & 8194) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoFrDoorState = viewModelCarInfo.frDoorState;
                } else {
                    viewModelCarInfoFrDoorState = null;
                }
                updateRegistration(1, (Observable) viewModelCarInfoFrDoorState);
                if (viewModelCarInfoFrDoorState != null) {
                    viewModelCarInfoFrDoorStateGet = viewModelCarInfoFrDoorState.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFrDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoFrDoorStateGet);
                if ((dirtyFlags & 8194) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFrDoorStateGet) {
                        dirtyFlags |= 134217728;
                    } else {
                        dirtyFlags |= 67108864;
                    }
                }
                viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFrDoorStateGet ? 0 : 4;
                ObservableField<Boolean> observableField = viewModelCarInfoFrDoorState;
            }
            if ((dirtyFlags & 8196) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoSpeedWatch = viewModelCarInfo.speedWatch;
                } else {
                    viewModelCarInfoSpeedWatch = null;
                }
                updateRegistration(2, (Observable) viewModelCarInfoSpeedWatch);
                if (viewModelCarInfoSpeedWatch != null) {
                    viewModelCarInfoSpeedWatchGet = viewModelCarInfoSpeedWatch.get();
                    ObservableInt observableInt = viewModelCarInfoSpeedWatch;
                } else {
                    ObservableInt observableInt2 = viewModelCarInfoSpeedWatch;
                }
            }
            if ((dirtyFlags & 8200) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoRlDoorState = viewModelCarInfo.rlDoorState;
                } else {
                    viewModelCarInfoRlDoorState = null;
                }
                updateRegistration(3, (Observable) viewModelCarInfoRlDoorState);
                if (viewModelCarInfoRlDoorState != null) {
                    viewModelCarInfoRlDoorStateGet = viewModelCarInfoRlDoorState.get();
                }
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoRlDoorStateGet);
                if ((dirtyFlags & 8200) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet) {
                        dirtyFlags |= 8388608;
                    } else {
                        dirtyFlags |= 4194304;
                    }
                }
                viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet ? 0 : 4;
                ObservableField<Boolean> observableField2 = viewModelCarInfoRlDoorState;
            }
            if ((dirtyFlags & 8208) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoRrDoorState = viewModelCarInfo.rrDoorState;
                } else {
                    viewModelCarInfoRrDoorState = null;
                }
                updateRegistration(4, (Observable) viewModelCarInfoRrDoorState);
                if (viewModelCarInfoRrDoorState != null) {
                    viewModelCarInfoRrDoorStateGet = viewModelCarInfoRrDoorState.get();
                }
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoRrDoorStateGet);
                if ((dirtyFlags & 8208) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                    }
                }
                viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet ? 0 : 4;
                ObservableField<Boolean> observableField3 = viewModelCarInfoRrDoorState;
            }
            if ((dirtyFlags & 8224) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoTempStr = viewModelCarInfo.tempStr;
                } else {
                    viewModelCarInfoTempStr = null;
                }
                updateRegistration(5, (Observable) viewModelCarInfoTempStr);
                if (viewModelCarInfoTempStr != null) {
                    viewModelCarInfoTempStrGet2 = viewModelCarInfoTempStr.get();
                    ObservableField<String> observableField4 = viewModelCarInfoTempStr;
                } else {
                    ObservableField<String> observableField5 = viewModelCarInfoTempStr;
                }
            }
            if ((dirtyFlags & 8256) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoTurnSpeedAnge = viewModelCarInfo.turnSpeedAnge;
                } else {
                    viewModelCarInfoTurnSpeedAnge = null;
                }
                updateRegistration(6, (Observable) viewModelCarInfoTurnSpeedAnge);
                if (viewModelCarInfoTurnSpeedAnge != null) {
                    ObservableFloat observableFloat = viewModelCarInfoTurnSpeedAnge;
                    viewModelCarInfoTurnSpeedAngeGet = viewModelCarInfoTurnSpeedAnge.get();
                } else {
                    ObservableFloat observableFloat2 = viewModelCarInfoTurnSpeedAnge;
                    viewModelCarInfoTurnSpeedAngeGet = 0.0f;
                }
            } else {
                viewModelCarInfoTurnSpeedAngeGet = 0.0f;
            }
            float viewModelCarInfoTurnSpeedAngeGet3 = viewModelCarInfoTurnSpeedAngeGet;
            if ((dirtyFlags & 8320) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoSeatBeltpValue = viewModelCarInfo.seatBeltpValue;
                } else {
                    viewModelCarInfoSeatBeltpValue = null;
                }
                updateRegistration(7, (Observable) viewModelCarInfoSeatBeltpValue);
                if (viewModelCarInfoSeatBeltpValue != null) {
                    viewModelCarInfoSeatBeltpValueGet = viewModelCarInfoSeatBeltpValue.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet = ViewDataBinding.safeUnbox(viewModelCarInfoSeatBeltpValueGet);
                if ((dirtyFlags & 8320) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
                    }
                }
                viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet ? 8 : 0;
                ObservableField<Boolean> observableField6 = viewModelCarInfoSeatBeltpValue;
            }
            if ((dirtyFlags & 8448) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoFlDoorState = viewModelCarInfo.flDoorState;
                } else {
                    viewModelCarInfoFlDoorState = null;
                }
                updateRegistration(8, (Observable) viewModelCarInfoFlDoorState);
                if (viewModelCarInfoFlDoorState != null) {
                    viewModelCarInfoFlDoorStateGet = viewModelCarInfoFlDoorState.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoFlDoorStateGet);
                if ((dirtyFlags & 8448) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet) {
                        dirtyFlags |= 33554432;
                    } else {
                        dirtyFlags |= 16777216;
                    }
                }
                viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet ? 0 : 4;
                ObservableField<Boolean> observableField7 = viewModelCarInfoFlDoorState;
            }
            if ((dirtyFlags & 8704) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoSpeed = viewModelCarInfo.speed;
                } else {
                    viewModelCarInfoSpeed = null;
                }
                updateRegistration(9, (Observable) viewModelCarInfoSpeed);
                if (viewModelCarInfoSpeed != null) {
                    viewModelCarInfoSpeedGet = viewModelCarInfoSpeed.get();
                    ObservableInt observableInt3 = viewModelCarInfoSpeed;
                } else {
                    ObservableInt observableInt4 = viewModelCarInfoSpeed;
                }
            }
            if ((dirtyFlags & 9216) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoBDoorState = viewModelCarInfo.bDoorState;
                } else {
                    viewModelCarInfoBDoorState = null;
                }
                updateRegistration(10, (Observable) viewModelCarInfoBDoorState);
                if (viewModelCarInfoBDoorState != null) {
                    viewModelCarInfoBDoorStateGet = viewModelCarInfoBDoorState.get();
                }
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoBDoorStateGet);
                if ((dirtyFlags & 9216) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
                    }
                }
                viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet ? 0 : 4;
                ObservableField<Boolean> observableField8 = viewModelCarInfoBDoorState;
            }
            if ((dirtyFlags & 10240) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoBrakeValue = viewModelCarInfo.brakeValue;
                } else {
                    viewModelCarInfoBrakeValue = null;
                }
                updateRegistration(11, (Observable) viewModelCarInfoBrakeValue);
                if (viewModelCarInfoBrakeValue != null) {
                    viewModelCarInfoBrakeValueGet = viewModelCarInfoBrakeValue.get();
                }
                int androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet = ViewDataBinding.safeUnbox(viewModelCarInfoBrakeValueGet);
                if ((dirtyFlags & 10240) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet == true) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                    }
                }
                ObservableField<Boolean> observableField9 = viewModelCarInfoBrakeValue;
                CarInfo carInfo = viewModelCarInfo;
                viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoBrakeValueViewVISIBLEViewGONE = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet != 0 ? 0 : 8;
                viewModelCarInfoTurnSpeedAngeGet2 = viewModelCarInfoTurnSpeedAngeGet3;
                int viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE3 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet;
                int viewModelCarInfoBrakeValueViewVISIBLEViewGONE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet;
                viewModelCarInfoTempStrGet = viewModelCarInfoTempStrGet2;
                viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE2;
                Boolean bool = viewModelCarInfoBDoorStateGet;
                ObservableBoolean observableBoolean = viewModelCarInfoCarImage;
                viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2;
                int viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE3 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet;
                viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE = viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2;
            } else {
                CarInfo carInfo2 = viewModelCarInfo;
                viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoBrakeValueViewVISIBLEViewGONE = 0;
                viewModelCarInfoTurnSpeedAngeGet2 = viewModelCarInfoTurnSpeedAngeGet3;
                int viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE4 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet;
                viewModelCarInfoTempStrGet = viewModelCarInfoTempStrGet2;
                viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE2;
                Boolean bool2 = viewModelCarInfoBDoorStateGet;
                ObservableBoolean observableBoolean2 = viewModelCarInfoCarImage;
                viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2;
                int viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE4 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet;
                viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE = viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2;
            }
        } else {
            viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE = 0;
            viewModelCarInfoBrakeValueViewVISIBLEViewGONE = 0;
            viewModelCarInfoTempStrGet = null;
            viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE = 0;
            viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE = 0;
            viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE = 0;
            viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE = 0;
        }
        if ((dirtyFlags & 10240) != 0) {
            boolean z = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet;
            this.brakeTextView.setVisibility(viewModelCarInfoBrakeValueViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 8193) != 0) {
            this.carImageView.setVisibility(viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 9216) != 0) {
            this.dorrBackImageView.setVisibility(viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 8448) != 0) {
            this.dorrLeftFlImageView.setVisibility(viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 8194) != 0) {
            this.dorrLeftFrImageView.setVisibility(viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 8200) != 0) {
            this.dorrLeftRlImageView.setVisibility(viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 8208) != 0) {
            this.imageView19.setVisibility(viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 8320) != 0) {
            this.seatBeltTextView.setVisibility(viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 8196) != 0) {
            this.speedPointerImageView.setImageLevel(viewModelCarInfoSpeedWatchGet);
            this.speedometerImageView.setImageLevel(viewModelCarInfoSpeedWatchGet);
        }
        if ((dirtyFlags & 8704) != 0) {
            LauncherViewModel.setSpeedRotation(this.speedPointerImageView, viewModelCarInfoSpeedGet);
        }
        if ((dirtyFlags & 8224) != 0) {
            TextViewBindingAdapter.setText(this.temperatureTextView, viewModelCarInfoTempStrGet);
        }
        if ((dirtyFlags & 8256) != 0) {
            DashboardViewModel.setRotation(this.zspeedPointerImageView, viewModelCarInfoTurnSpeedAngeGet2);
        }
    }
}
