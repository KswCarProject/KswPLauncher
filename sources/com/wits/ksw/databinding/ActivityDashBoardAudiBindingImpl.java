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

public class ActivityDashBoardAudiBindingImpl extends ActivityDashBoardAudiBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.audi_ty_dashboard_speed_highlight_ImageView, 12);
        sparseIntArray.put(R.id.tachometerImageView, 13);
        sparseIntArray.put(R.id.audi_ty_dashboard_tachometer_highlight_imageView, 14);
        sparseIntArray.put(R.id.dorr_back_imageView, 15);
    }

    public ActivityDashBoardAudiBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    private ActivityDashBoardAudiBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 11, bindings[12], bindings[14], bindings[10], bindings[5], bindings[15], bindings[6], bindings[7], bindings[8], bindings[9], bindings[0], bindings[11], bindings[3], bindings[2], bindings[13], bindings[1], bindings[4]);
        this.mDirtyFlags = -1;
        this.brakeTextView.setTag((Object) null);
        this.carImageView.setTag((Object) null);
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
        int viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE;
        int viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE;
        int viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE;
        int viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE;
        String viewModelCarInfoTempStrGet;
        int viewModelCarInfoBrakeValueViewVISIBLEViewGONE;
        int viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE;
        float viewModelCarInfoTurnSpeedAngeGet;
        ObservableField<Boolean> viewModelCarInfoBrakeValue;
        ObservableInt viewModelCarInfoSpeed;
        ObservableField<Boolean> viewModelCarInfoFlDoorState;
        ObservableField<Boolean> viewModelCarInfoSeatBeltpValue;
        ObservableFloat viewModelCarInfoTurnSpeedAnge;
        ObservableField<String> viewModelCarInfoTempStr;
        ObservableField<Boolean> viewModelCarInfoRrDoorState;
        ObservableField<Boolean> viewModelCarInfoRlDoorState;
        ObservableInt viewModelCarInfoSpeedWatch;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        float viewModelCarInfoTurnSpeedAngeGet2 = 0.0f;
        int viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE = 0;
        int viewModelCarInfoSpeedWatchGet = 0;
        int viewModelCarInfoSpeedGet = 0;
        ObservableBoolean viewModelCarInfoCarImage = null;
        boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet = false;
        int androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet = false;
        ObservableField<Boolean> viewModelCarInfoFrDoorState = null;
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
        if ((dirtyFlags & 6143) != 0) {
            CarInfo viewModelCarInfo = DashboardViewModel.carInfo;
            if ((dirtyFlags & 4097) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoCarImage = viewModelCarInfo.carImage;
                }
                updateRegistration(0, (Observable) viewModelCarInfoCarImage);
                if (viewModelCarInfoCarImage != null) {
                    viewModelCarInfoCarImageGet = viewModelCarInfoCarImage.get();
                }
                if ((dirtyFlags & 4097) != 0) {
                    if (viewModelCarInfoCarImageGet) {
                        dirtyFlags |= 67108864;
                    } else {
                        dirtyFlags |= 33554432;
                    }
                }
                viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2 = viewModelCarInfoCarImageGet ? 0 : 4;
            }
            if ((dirtyFlags & 4098) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoFrDoorState = viewModelCarInfo.frDoorState;
                }
                updateRegistration(1, (Observable) viewModelCarInfoFrDoorState);
                if (viewModelCarInfoFrDoorState != null) {
                    viewModelCarInfoFrDoorStateGet = viewModelCarInfoFrDoorState.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFrDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoFrDoorStateGet);
                if ((dirtyFlags & 4098) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFrDoorStateGet) {
                        dirtyFlags |= 16777216;
                    } else {
                        dirtyFlags |= 8388608;
                    }
                }
                viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFrDoorStateGet ? 0 : 4;
            }
            if ((dirtyFlags & 4100) != 0) {
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
            if ((dirtyFlags & 4104) != 0) {
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
                if ((dirtyFlags & 4104) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
                    }
                }
                viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet ? 0 : 4;
                ObservableField<Boolean> observableField = viewModelCarInfoRlDoorState;
            }
            if ((dirtyFlags & 4112) != 0) {
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
                if ((dirtyFlags & 4112) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                    }
                }
                viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet ? 0 : 4;
                ObservableField<Boolean> observableField2 = viewModelCarInfoRrDoorState;
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
            if ((dirtyFlags & 4224) != 0) {
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
                if ((dirtyFlags & 4224) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                    }
                }
                viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet ? 8 : 0;
                ObservableField<Boolean> observableField5 = viewModelCarInfoSeatBeltpValue;
            }
            if ((dirtyFlags & 4352) != 0) {
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
                if ((dirtyFlags & 4352) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet) {
                        dirtyFlags |= 4194304;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
                    }
                }
                viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet ? 0 : 4;
                ObservableField<Boolean> observableField6 = viewModelCarInfoFlDoorState;
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
                    ObservableInt observableInt3 = viewModelCarInfoSpeed;
                } else {
                    ObservableInt observableInt4 = viewModelCarInfoSpeed;
                }
            }
            if ((dirtyFlags & 5120) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoBrakeValue = viewModelCarInfo.brakeValue;
                } else {
                    viewModelCarInfoBrakeValue = null;
                }
                updateRegistration(10, (Observable) viewModelCarInfoBrakeValue);
                if (viewModelCarInfoBrakeValue != null) {
                    viewModelCarInfoBrakeValueGet = viewModelCarInfoBrakeValue.get();
                }
                int androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet = ViewDataBinding.safeUnbox(viewModelCarInfoBrakeValueGet);
                if ((dirtyFlags & 5120) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet == true) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                    }
                }
                ObservableField<Boolean> observableField7 = viewModelCarInfoBrakeValue;
                CarInfo carInfo = viewModelCarInfo;
                viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoBrakeValueViewVISIBLEViewGONE = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet != 0 ? 0 : 8;
                viewModelCarInfoTurnSpeedAngeGet2 = viewModelCarInfoTurnSpeedAngeGet3;
                int viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE3 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet;
                ObservableBoolean observableBoolean = viewModelCarInfoCarImage;
                viewModelCarInfoTempStrGet = viewModelCarInfoTempStrGet2;
                viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE2;
                boolean z = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet;
                int viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE3 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet;
                viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2;
                ObservableField<Boolean> observableField8 = viewModelCarInfoFrDoorState;
                viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE = viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2;
            } else {
                CarInfo carInfo2 = viewModelCarInfo;
                viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoBrakeValueViewVISIBLEViewGONE = 0;
                viewModelCarInfoTurnSpeedAngeGet2 = viewModelCarInfoTurnSpeedAngeGet3;
                ObservableBoolean observableBoolean2 = viewModelCarInfoCarImage;
                viewModelCarInfoTempStrGet = viewModelCarInfoTempStrGet2;
                viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE2;
                boolean z2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet;
                int viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE4 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet;
                viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2;
                ObservableField<Boolean> observableField9 = viewModelCarInfoFrDoorState;
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
        if ((dirtyFlags & 5120) != 0) {
            Boolean bool = viewModelCarInfoSeatBeltpValueGet;
            this.brakeTextView.setVisibility(viewModelCarInfoBrakeValueViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 4097) != 0) {
            this.carImageView.setVisibility(viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 4352) != 0) {
            this.dorrLeftFlImageView.setVisibility(viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 4098) != 0) {
            this.dorrLeftFrImageView.setVisibility(viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 4104) != 0) {
            this.dorrLeftRlImageView.setVisibility(viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 4112) != 0) {
            this.imageView19.setVisibility(viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 4224) != 0) {
            this.seatBeltTextView.setVisibility(viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 4100) != 0) {
            this.speedPointerImageView.setImageLevel(viewModelCarInfoSpeedWatchGet);
            this.speedometerImageView.setImageLevel(viewModelCarInfoSpeedWatchGet);
        }
        if ((dirtyFlags & 4608) != 0) {
            LauncherViewModel.setSpeedRotation(this.speedPointerImageView, viewModelCarInfoSpeedGet);
        }
        if ((dirtyFlags & 4128) != 0) {
            TextViewBindingAdapter.setText(this.temperatureTextView, viewModelCarInfoTempStrGet);
        }
        if ((dirtyFlags & 4160) != 0) {
            DashboardViewModel.setRotation(this.zspeedPointerImageView, viewModelCarInfoTurnSpeedAngeGet2);
        }
    }
}
