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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.CarInfo;
import com.wits.ksw.launcher.model.DashboardViewModel;

public class ActivityDashBoardLexusBindingImpl extends ActivityDashBoardLexusBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final FrameLayout mboundView0;
    @NonNull
    private final ImageView mboundView1;
    @NonNull
    private final TextView mboundView10;
    @NonNull
    private final TextView mboundView11;
    @NonNull
    private final ImageView mboundView2;
    @NonNull
    private final ImageView mboundView3;
    @NonNull
    private final ImageView mboundView4;
    @NonNull
    private final ImageView mboundView5;
    @NonNull
    private final ImageView mboundView6;
    @NonNull
    private final TextView mboundView7;

    static {
        sViewsWithIds.put(R.id.lexus_iv_left, 12);
        sViewsWithIds.put(R.id.lexus_iv_right, 13);
    }

    public ActivityDashBoardLexusBindingImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }

    private ActivityDashBoardLexusBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 11, bindings[8], bindings[12], bindings[13], bindings[9]);
        this.mDirtyFlags = -1;
        this.brakeTextView.setTag((Object) null);
        this.mboundView0 = bindings[0];
        this.mboundView0.setTag((Object) null);
        this.mboundView1 = bindings[1];
        this.mboundView1.setTag((Object) null);
        this.mboundView10 = bindings[10];
        this.mboundView10.setTag((Object) null);
        this.mboundView11 = bindings[11];
        this.mboundView11.setTag((Object) null);
        this.mboundView2 = bindings[2];
        this.mboundView2.setTag((Object) null);
        this.mboundView3 = bindings[3];
        this.mboundView3.setTag((Object) null);
        this.mboundView4 = bindings[4];
        this.mboundView4.setTag((Object) null);
        this.mboundView5 = bindings[5];
        this.mboundView5.setTag((Object) null);
        this.mboundView6 = bindings[6];
        this.mboundView6.setTag((Object) null);
        this.mboundView7 = bindings[7];
        this.mboundView7.setTag((Object) null);
        this.seatBeltTextView.setTag((Object) null);
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

    public boolean setVariable(int variableId, @Nullable Object variable) {
        if (19 != variableId) {
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
                return onChangeViewModelCarInfoRlDoorState((ObservableField) object, fieldId);
            case 7:
                return onChangeViewModelCarInfoFlDoorState((ObservableField) object, fieldId);
            case 8:
                return onChangeViewModelCarInfoSpeed((ObservableInt) object, fieldId);
            case 9:
                return onChangeViewModelCarInfoBDoorState((ObservableField) object, fieldId);
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

    private boolean onChangeViewModelCarInfoRlDoorState(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoFlDoorState(ObservableField<Boolean> observableField, int fieldId) {
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

    private boolean onChangeViewModelCarInfoBDoorState(ObservableField<Boolean> observableField, int fieldId) {
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
        ObservableBoolean viewModelCarInfoCarImage;
        int viewModelCarInfoBrakeValueViewVISIBLEViewGONE;
        String viewModelCarInfoTempStrGet;
        int viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE;
        int viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE;
        int viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE2;
        int viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE;
        String stringValueOfViewModelCarInfoTurnSpeed;
        int viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE;
        int viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE;
        boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet;
        ObservableField<Boolean> viewModelCarInfoBrakeValue;
        ObservableField<Boolean> viewModelCarInfoBDoorState;
        ObservableInt viewModelCarInfoSpeed;
        ObservableField<Boolean> viewModelCarInfoFlDoorState;
        ObservableField<Boolean> viewModelCarInfoRlDoorState;
        ObservableInt viewModelCarInfoTurnSpeed;
        ObservableField<Boolean> viewModelCarInfoSeatBeltpValue;
        ObservableField<String> viewModelCarInfoTempStr;
        ObservableField<Boolean> viewModelCarInfoRrDoorState;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        int viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE3 = 0;
        ObservableBoolean viewModelCarInfoCarImage2 = null;
        int androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet = false;
        int androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet = false;
        ObservableField<Boolean> viewModelCarInfoFrDoorState = null;
        Boolean viewModelCarInfoRlDoorStateGet = null;
        int viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE = 0;
        int androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet = false;
        String stringValueOfViewModelCarInfoSpeed = null;
        ObservableField<Boolean> viewModelCarInfoRrDoorState2 = null;
        ObservableField<String> viewModelCarInfoTempStr2 = null;
        int viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE = 0;
        boolean viewModelCarInfoCarImageGet = false;
        Boolean viewModelCarInfoFrDoorStateGet = null;
        int viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2 = 0;
        int viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE2 = 0;
        Boolean viewModelCarInfoBDoorStateGet = null;
        int viewModelCarInfoSpeedGet = 0;
        Boolean viewModelCarInfoSeatBeltpValueGet = null;
        int androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet = false;
        Boolean viewModelCarInfoRrDoorStateGet = null;
        int viewModelCarInfoTurnSpeedGet = 0;
        Boolean viewModelCarInfoFlDoorStateGet = null;
        String viewModelCarInfoTempStrGet2 = null;
        Boolean viewModelCarInfoBrakeValueGet = null;
        int viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2 = 0;
        String stringValueOfViewModelCarInfoTurnSpeed2 = null;
        int viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2 = 0;
        if ((dirtyFlags & 6143) != 0) {
            CarInfo viewModelCarInfo = DashboardViewModel.carInfo;
            if ((dirtyFlags & 4097) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoCarImage2 = viewModelCarInfo.carImage;
                }
                updateRegistration(0, (Observable) viewModelCarInfoCarImage2);
                if (viewModelCarInfoCarImage2 != null) {
                    viewModelCarInfoCarImageGet = viewModelCarInfoCarImage2.get();
                }
                if ((dirtyFlags & 4097) != 0) {
                    if (viewModelCarInfoCarImageGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
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
                        dirtyFlags |= 268435456;
                    } else {
                        dirtyFlags |= 134217728;
                    }
                }
                boolean z = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFrDoorStateGet;
                viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFrDoorStateGet ? 0 : 4;
            }
            if ((dirtyFlags & 4100) != 0) {
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
                if ((dirtyFlags & 4100) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet2) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                    }
                }
                viewModelCarInfoRrDoorState2 = viewModelCarInfoRrDoorState;
                viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet2 ? 0 : 4;
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet2;
            }
            if ((dirtyFlags & 4104) != 0) {
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
            if ((dirtyFlags & 4112) != 0) {
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
                if ((dirtyFlags & 4112) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet2) {
                        dirtyFlags |= 4194304;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
                    }
                }
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet2;
                viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet2 ? 8 : 0;
                ObservableField<Boolean> observableField = viewModelCarInfoSeatBeltpValue;
            }
            if ((dirtyFlags & 4128) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoTurnSpeed = viewModelCarInfo.turnSpeed;
                } else {
                    viewModelCarInfoTurnSpeed = null;
                }
                updateRegistration(5, (Observable) viewModelCarInfoTurnSpeed);
                if (viewModelCarInfoTurnSpeed != null) {
                    viewModelCarInfoTurnSpeedGet = viewModelCarInfoTurnSpeed.get();
                }
                ObservableInt observableInt = viewModelCarInfoTurnSpeed;
                stringValueOfViewModelCarInfoTurnSpeed2 = String.valueOf(viewModelCarInfoTurnSpeedGet);
            }
            if ((dirtyFlags & 4160) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoRlDoorState = viewModelCarInfo.rlDoorState;
                } else {
                    viewModelCarInfoRlDoorState = null;
                }
                updateRegistration(6, (Observable) viewModelCarInfoRlDoorState);
                if (viewModelCarInfoRlDoorState != null) {
                    viewModelCarInfoRlDoorStateGet = viewModelCarInfoRlDoorState.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet2 = ViewDataBinding.safeUnbox(viewModelCarInfoRlDoorStateGet);
                if ((dirtyFlags & 4160) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet2) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                    }
                }
                ObservableField<Boolean> observableField2 = viewModelCarInfoRlDoorState;
                viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet2 ? 0 : 4;
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet2;
            }
            if ((dirtyFlags & 4224) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoFlDoorState = viewModelCarInfo.flDoorState;
                } else {
                    viewModelCarInfoFlDoorState = null;
                }
                updateRegistration(7, (Observable) viewModelCarInfoFlDoorState);
                if (viewModelCarInfoFlDoorState != null) {
                    viewModelCarInfoFlDoorStateGet = viewModelCarInfoFlDoorState.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet2 = ViewDataBinding.safeUnbox(viewModelCarInfoFlDoorStateGet);
                if ((dirtyFlags & 4224) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet2) {
                        dirtyFlags |= 67108864;
                    } else {
                        dirtyFlags |= 33554432;
                    }
                }
                ObservableField<Boolean> observableField3 = viewModelCarInfoFlDoorState;
                viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet2 ? 0 : 4;
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet2;
            }
            if ((dirtyFlags & 4352) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoSpeed = viewModelCarInfo.speed;
                } else {
                    viewModelCarInfoSpeed = null;
                }
                updateRegistration(8, (Observable) viewModelCarInfoSpeed);
                if (viewModelCarInfoSpeed != null) {
                    viewModelCarInfoSpeedGet = viewModelCarInfoSpeed.get();
                }
                stringValueOfViewModelCarInfoSpeed = String.valueOf(viewModelCarInfoSpeedGet);
                ObservableInt observableInt2 = viewModelCarInfoSpeed;
            }
            if ((dirtyFlags & 4608) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoBDoorState = viewModelCarInfo.bDoorState;
                } else {
                    viewModelCarInfoBDoorState = null;
                }
                updateRegistration(9, (Observable) viewModelCarInfoBDoorState);
                if (viewModelCarInfoBDoorState != null) {
                    viewModelCarInfoBDoorStateGet = viewModelCarInfoBDoorState.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet2 = ViewDataBinding.safeUnbox(viewModelCarInfoBDoorStateGet);
                if ((dirtyFlags & 4608) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet2) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                    }
                }
                viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE3 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet2 ? 0 : 4;
                ObservableField<Boolean> observableField4 = viewModelCarInfoBDoorState;
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet2;
            } else {
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet = false;
            }
            if ((dirtyFlags & 5120) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoBrakeValue = viewModelCarInfo.brakeValue;
                } else {
                    viewModelCarInfoBrakeValue = null;
                }
                boolean z2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet;
                updateRegistration(10, (Observable) viewModelCarInfoBrakeValue);
                if (viewModelCarInfoBrakeValue != null) {
                    viewModelCarInfoBrakeValueGet = viewModelCarInfoBrakeValue.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet = ViewDataBinding.safeUnbox(viewModelCarInfoBrakeValueGet);
                if ((dirtyFlags & 5120) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet) {
                        dirtyFlags |= 16777216;
                    } else {
                        dirtyFlags |= 8388608;
                    }
                }
                CarInfo carInfo = viewModelCarInfo;
                ObservableField<Boolean> observableField5 = viewModelCarInfoBrakeValue;
                viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE = viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE = viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE2;
                stringValueOfViewModelCarInfoTurnSpeed = stringValueOfViewModelCarInfoTurnSpeed2;
                int viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE3 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet;
                int viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE3 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet;
                viewModelCarInfoTempStrGet = viewModelCarInfoTempStrGet2;
                viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE3;
                viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE2 = viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE;
                int i = viewModelCarInfoTurnSpeedGet;
                ObservableField<String> observableField6 = viewModelCarInfoTempStr2;
                int viewModelCarInfoTurnSpeedGet2 = viewModelCarInfoSpeedGet;
                int viewModelCarInfoSpeedGet2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet;
                ObservableField<Boolean> observableField7 = viewModelCarInfoRrDoorState2;
                ObservableField<Boolean> observableField8 = viewModelCarInfoFrDoorState;
                Boolean bool = viewModelCarInfoRlDoorStateGet;
                viewModelCarInfoBrakeValueViewVISIBLEViewGONE = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet ? 0 : 8;
                viewModelCarInfoCarImage = viewModelCarInfoCarImage2;
                int viewModelCarInfoBrakeValueViewVISIBLEViewGONE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet;
                viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2;
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet3 = viewModelCarInfoCarImageGet;
                boolean viewModelCarInfoCarImageGet2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet;
                viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2;
            } else {
                CarInfo carInfo2 = viewModelCarInfo;
                viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE = viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE = viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE2;
                stringValueOfViewModelCarInfoTurnSpeed = stringValueOfViewModelCarInfoTurnSpeed2;
                int viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE4 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet;
                int viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE4 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet;
                viewModelCarInfoTempStrGet = viewModelCarInfoTempStrGet2;
                viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE3;
                viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE2 = viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE;
                int i2 = viewModelCarInfoTurnSpeedGet;
                ObservableField<String> observableField9 = viewModelCarInfoTempStr2;
                int viewModelCarInfoTurnSpeedGet3 = viewModelCarInfoSpeedGet;
                int viewModelCarInfoSpeedGet3 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet;
                ObservableField<Boolean> observableField10 = viewModelCarInfoRrDoorState2;
                ObservableField<Boolean> observableField11 = viewModelCarInfoFrDoorState;
                Boolean bool2 = viewModelCarInfoRlDoorStateGet;
                viewModelCarInfoBrakeValueViewVISIBLEViewGONE = 0;
                viewModelCarInfoCarImage = viewModelCarInfoCarImage2;
                int viewModelCarInfoBrakeValueViewVISIBLEViewGONE3 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet;
                viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2;
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet4 = viewModelCarInfoCarImageGet;
                viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2;
            }
        } else {
            viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE = 0;
            viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE = 0;
            stringValueOfViewModelCarInfoTurnSpeed = null;
            viewModelCarInfoTempStrGet = null;
            viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE = 0;
            viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE2 = 0;
            viewModelCarInfoBrakeValueViewVISIBLEViewGONE = 0;
            viewModelCarInfoCarImage = null;
            viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE = 0;
            viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE = 0;
        }
        if ((dirtyFlags & 4112) != 0) {
            ObservableBoolean observableBoolean = viewModelCarInfoCarImage;
            this.brakeTextView.setVisibility(viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 4097) != 0) {
            this.mboundView1.setVisibility(viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 4352) != 0) {
            TextViewBindingAdapter.setText(this.mboundView10, stringValueOfViewModelCarInfoSpeed);
        }
        if ((dirtyFlags & 4128) != 0) {
            TextViewBindingAdapter.setText(this.mboundView11, stringValueOfViewModelCarInfoTurnSpeed);
        }
        if ((dirtyFlags & 4224) != 0) {
            this.mboundView2.setVisibility(viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 4160) != 0) {
            this.mboundView3.setVisibility(viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE2);
        }
        if ((dirtyFlags & 4098) != 0) {
            this.mboundView4.setVisibility(viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 4100) != 0) {
            this.mboundView5.setVisibility(viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 4608) != 0) {
            this.mboundView6.setVisibility(viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 4104) != 0) {
            TextViewBindingAdapter.setText(this.mboundView7, viewModelCarInfoTempStrGet);
        }
        if ((dirtyFlags & 5120) != 0) {
            this.seatBeltTextView.setVisibility(viewModelCarInfoBrakeValueViewVISIBLEViewGONE);
        }
    }
}
