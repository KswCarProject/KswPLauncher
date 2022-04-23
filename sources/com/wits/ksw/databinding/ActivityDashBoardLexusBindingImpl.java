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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.CarInfo;
import com.wits.ksw.launcher.model.DashboardViewModel;

public class ActivityDashBoardLexusBindingImpl extends ActivityDashBoardLexusBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;
    private final ImageView mboundView1;
    private final TextView mboundView10;
    private final TextView mboundView11;
    private final ImageView mboundView2;
    private final ImageView mboundView3;
    private final ImageView mboundView4;
    private final ImageView mboundView5;
    private final ImageView mboundView6;
    private final TextView mboundView7;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.lexus_iv_left, 12);
        sparseIntArray.put(R.id.lexus_iv_right, 13);
    }

    public ActivityDashBoardLexusBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }

    private ActivityDashBoardLexusBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 11, bindings[8], bindings[12], bindings[13], bindings[9]);
        this.mDirtyFlags = -1;
        this.brakeTextView.setTag((Object) null);
        FrameLayout frameLayout = bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag((Object) null);
        ImageView imageView = bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag((Object) null);
        TextView textView = bindings[10];
        this.mboundView10 = textView;
        textView.setTag((Object) null);
        TextView textView2 = bindings[11];
        this.mboundView11 = textView2;
        textView2.setTag((Object) null);
        ImageView imageView2 = bindings[2];
        this.mboundView2 = imageView2;
        imageView2.setTag((Object) null);
        ImageView imageView3 = bindings[3];
        this.mboundView3 = imageView3;
        imageView3.setTag((Object) null);
        ImageView imageView4 = bindings[4];
        this.mboundView4 = imageView4;
        imageView4.setTag((Object) null);
        ImageView imageView5 = bindings[5];
        this.mboundView5 = imageView5;
        imageView5.setTag((Object) null);
        ImageView imageView6 = bindings[6];
        this.mboundView6 = imageView6;
        imageView6.setTag((Object) null);
        TextView textView3 = bindings[7];
        this.mboundView7 = textView3;
        textView3.setTag((Object) null);
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
                return onChangeViewModelCarInfoTurnSpeed((ObservableInt) object, fieldId);
            case 1:
                return onChangeViewModelCarInfoCarImage((ObservableBoolean) object, fieldId);
            case 2:
                return onChangeViewModelCarInfoFrDoorState((ObservableField) object, fieldId);
            case 3:
                return onChangeViewModelCarInfoRlDoorState((ObservableField) object, fieldId);
            case 4:
                return onChangeViewModelCarInfoRrDoorState((ObservableField) object, fieldId);
            case 5:
                return onChangeViewModelCarInfoTempStr((ObservableField) object, fieldId);
            case 6:
                return onChangeViewModelCarInfoSeatBeltpValue((ObservableField) object, fieldId);
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

    private boolean onChangeViewModelCarInfoSeatBeltpValue(ObservableField<Boolean> observableField, int fieldId) {
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
        int viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE;
        int viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE;
        String stringValueOfViewModelCarInfoTurnSpeed;
        int viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE;
        int viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE;
        String viewModelCarInfoTempStrGet;
        String stringValueOfViewModelCarInfoSpeed;
        int viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE;
        int viewModelCarInfoBrakeValueViewVISIBLEViewGONE;
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
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet = false;
        int viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE = 0;
        Boolean viewModelCarInfoBDoorStateGet = null;
        int viewModelCarInfoSpeedGet = 0;
        int viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE = 0;
        ObservableBoolean viewModelCarInfoCarImage = null;
        boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet = false;
        boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet = false;
        ObservableField<Boolean> viewModelCarInfoFrDoorState = null;
        Boolean viewModelCarInfoSeatBeltpValueGet = null;
        Boolean viewModelCarInfoRlDoorStateGet = null;
        int viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE2 = 0;
        String stringValueOfViewModelCarInfoSpeed2 = null;
        Boolean viewModelCarInfoRrDoorStateGet = null;
        int viewModelCarInfoTurnSpeedGet = 0;
        Boolean viewModelCarInfoFlDoorStateGet = null;
        String viewModelCarInfoTempStrGet2 = null;
        Boolean viewModelCarInfoBrakeValueGet = null;
        int viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE2 = 0;
        int viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2 = 0;
        String stringValueOfViewModelCarInfoTurnSpeed2 = null;
        int viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2 = 0;
        boolean viewModelCarInfoCarImageGet = false;
        Boolean viewModelCarInfoFrDoorStateGet = null;
        int viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2 = 0;
        if ((dirtyFlags & 6143) != 0) {
            CarInfo viewModelCarInfo = DashboardViewModel.carInfo;
            if ((dirtyFlags & 4097) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoTurnSpeed = viewModelCarInfo.turnSpeed;
                } else {
                    viewModelCarInfoTurnSpeed = null;
                }
                updateRegistration(0, (Observable) viewModelCarInfoTurnSpeed);
                if (viewModelCarInfoTurnSpeed != null) {
                    viewModelCarInfoTurnSpeedGet = viewModelCarInfoTurnSpeed.get();
                }
                stringValueOfViewModelCarInfoTurnSpeed2 = String.valueOf(viewModelCarInfoTurnSpeedGet);
            } else {
                viewModelCarInfoTurnSpeed = null;
            }
            if ((dirtyFlags & 4098) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoCarImage = viewModelCarInfo.carImage;
                }
                updateRegistration(1, (Observable) viewModelCarInfoCarImage);
                if (viewModelCarInfoCarImage != null) {
                    viewModelCarInfoCarImageGet = viewModelCarInfoCarImage.get();
                }
                if ((dirtyFlags & 4098) != 0) {
                    if (viewModelCarInfoCarImageGet) {
                        dirtyFlags |= 268435456;
                    } else {
                        dirtyFlags |= 134217728;
                    }
                }
                viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2 = viewModelCarInfoCarImageGet ? 0 : 4;
            }
            if ((dirtyFlags & 4100) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoFrDoorState = viewModelCarInfo.frDoorState;
                }
                updateRegistration(2, (Observable) viewModelCarInfoFrDoorState);
                if (viewModelCarInfoFrDoorState != null) {
                    viewModelCarInfoFrDoorStateGet = viewModelCarInfoFrDoorState.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFrDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoFrDoorStateGet);
                if ((dirtyFlags & 4100) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFrDoorStateGet) {
                        dirtyFlags |= 67108864;
                    } else {
                        dirtyFlags |= 33554432;
                    }
                }
                viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFrDoorStateGet ? 0 : 4;
            }
            if ((dirtyFlags & 4104) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoRlDoorState = viewModelCarInfo.rlDoorState;
                } else {
                    viewModelCarInfoRlDoorState = null;
                }
                viewModelCarInfoTurnSpeed2 = viewModelCarInfoTurnSpeed;
                updateRegistration(3, (Observable) viewModelCarInfoRlDoorState);
                if (viewModelCarInfoRlDoorState != null) {
                    viewModelCarInfoRlDoorStateGet = viewModelCarInfoRlDoorState.get();
                }
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoRlDoorStateGet);
                if ((dirtyFlags & 4104) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet) {
                        dirtyFlags |= 4194304;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
                    }
                }
                viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet ? 0 : 4;
                ObservableField<Boolean> observableField = viewModelCarInfoRlDoorState;
            } else {
                viewModelCarInfoTurnSpeed2 = viewModelCarInfoTurnSpeed;
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
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
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
                    viewModelCarInfoSeatBeltpValue = viewModelCarInfo.seatBeltpValue;
                } else {
                    viewModelCarInfoSeatBeltpValue = null;
                }
                updateRegistration(6, (Observable) viewModelCarInfoSeatBeltpValue);
                if (viewModelCarInfoSeatBeltpValue != null) {
                    viewModelCarInfoSeatBeltpValueGet = viewModelCarInfoSeatBeltpValue.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet = ViewDataBinding.safeUnbox(viewModelCarInfoSeatBeltpValueGet);
                if ((dirtyFlags & 4160) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                    }
                }
                viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet ? 8 : 0;
                ObservableField<Boolean> observableField5 = viewModelCarInfoSeatBeltpValue;
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
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoFlDoorStateGet);
                if ((dirtyFlags & 4224) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet) {
                        dirtyFlags |= 16777216;
                    } else {
                        dirtyFlags |= 8388608;
                    }
                }
                viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet ? 0 : 4;
                ObservableField<Boolean> observableField6 = viewModelCarInfoFlDoorState;
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
                stringValueOfViewModelCarInfoSpeed2 = String.valueOf(viewModelCarInfoSpeedGet);
                ObservableInt observableInt = viewModelCarInfoSpeed;
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
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoBDoorStateGet);
                if ((dirtyFlags & 4608) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                    }
                }
                viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet ? 0 : 4;
                ObservableField<Boolean> observableField7 = viewModelCarInfoBDoorState;
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
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
                    }
                }
                ObservableField<Boolean> observableField8 = viewModelCarInfoBrakeValue;
                CarInfo carInfo = viewModelCarInfo;
                viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE2;
                stringValueOfViewModelCarInfoSpeed = stringValueOfViewModelCarInfoSpeed2;
                viewModelCarInfoBrakeValueViewVISIBLEViewGONE = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet != 0 ? 0 : 8;
                ObservableInt observableInt2 = viewModelCarInfoTurnSpeed2;
                boolean z = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet;
                int viewModelCarInfoBrakeValueViewVISIBLEViewGONE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet;
                viewModelCarInfoTempStrGet = viewModelCarInfoTempStrGet2;
                viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE2;
                Boolean bool = viewModelCarInfoBDoorStateGet;
                int viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE3 = viewModelCarInfoSpeedGet;
                viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2;
                stringValueOfViewModelCarInfoTurnSpeed = stringValueOfViewModelCarInfoTurnSpeed2;
                ObservableBoolean observableBoolean = viewModelCarInfoCarImage;
                boolean z2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet;
                viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE = viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2;
            } else {
                CarInfo carInfo2 = viewModelCarInfo;
                viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE2;
                stringValueOfViewModelCarInfoSpeed = stringValueOfViewModelCarInfoSpeed2;
                viewModelCarInfoBrakeValueViewVISIBLEViewGONE = 0;
                ObservableInt observableInt3 = viewModelCarInfoTurnSpeed2;
                boolean z3 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet;
                viewModelCarInfoTempStrGet = viewModelCarInfoTempStrGet2;
                viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE2;
                Boolean bool2 = viewModelCarInfoBDoorStateGet;
                int viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE4 = viewModelCarInfoSpeedGet;
                viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2;
                stringValueOfViewModelCarInfoTurnSpeed = stringValueOfViewModelCarInfoTurnSpeed2;
                ObservableBoolean observableBoolean2 = viewModelCarInfoCarImage;
                boolean z4 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet;
                viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE = viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2;
            }
        } else {
            viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE = 0;
            stringValueOfViewModelCarInfoSpeed = null;
            viewModelCarInfoBrakeValueViewVISIBLEViewGONE = 0;
            viewModelCarInfoTempStrGet = null;
            viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE = 0;
            viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE = 0;
            stringValueOfViewModelCarInfoTurnSpeed = null;
            viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE = 0;
            viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE = 0;
        }
        if ((dirtyFlags & 4160) != 0) {
            boolean z5 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet;
            this.brakeTextView.setVisibility(viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 4098) != 0) {
            this.mboundView1.setVisibility(viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 4352) != 0) {
            TextViewBindingAdapter.setText(this.mboundView10, stringValueOfViewModelCarInfoSpeed);
        }
        if ((dirtyFlags & 4097) != 0) {
            TextViewBindingAdapter.setText(this.mboundView11, stringValueOfViewModelCarInfoTurnSpeed);
        }
        if ((dirtyFlags & 4224) != 0) {
            this.mboundView2.setVisibility(viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 4104) != 0) {
            this.mboundView3.setVisibility(viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 4100) != 0) {
            this.mboundView4.setVisibility(viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 4112) != 0) {
            this.mboundView5.setVisibility(viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 4608) != 0) {
            this.mboundView6.setVisibility(viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 4128) != 0) {
            TextViewBindingAdapter.setText(this.mboundView7, viewModelCarInfoTempStrGet);
        }
        if ((dirtyFlags & 5120) != 0) {
            this.seatBeltTextView.setVisibility(viewModelCarInfoBrakeValueViewVISIBLEViewGONE);
        }
    }
}
