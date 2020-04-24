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
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.bean.CarInfo;
import com.wits.ksw.launcher.model.DashboardViewModel;
import com.wits.ksw.launcher.view.ID7SpeedImageView;

public class SevenDasoardBindImpl extends SevenDasoardBind implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @Nullable
    private final View.OnClickListener mCallback20;
    @Nullable
    private final View.OnClickListener mCallback21;
    @Nullable
    private final View.OnClickListener mCallback22;
    @Nullable
    private final View.OnClickListener mCallback23;
    @Nullable
    private final View.OnClickListener mCallback24;
    @Nullable
    private final View.OnClickListener mCallback25;
    @Nullable
    private final View.OnClickListener mCallback26;
    private long mDirtyFlags;
    @NonNull
    private final ID7SpeedImageView mboundView1;
    @NonNull
    private final TextView mboundView17;
    @NonNull
    private final TextView mboundView18;
    @NonNull
    private final TextView mboundView19;
    @NonNull
    private final TextView mboundView20;
    @NonNull
    private final TextView mboundView21;
    @NonNull
    private final TextView mboundView22;
    @NonNull
    private final TextView mboundView23;
    @NonNull
    private final ImageView mboundView3;

    static {
        sViewsWithIds.put(R.id.alsRadioGroup, 25);
        sViewsWithIds.put(R.id.time, 26);
    }

    public SevenDasoardBindImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 27, sIncludes, sViewsWithIds));
    }

    private SevenDasoardBindImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 15, bindings[16], bindings[25], bindings[15], bindings[8], bindings[13], bindings[9], bindings[10], bindings[11], bindings[12], bindings[0], bindings[14], bindings[4], bindings[2], bindings[5], bindings[24], bindings[26], bindings[6], bindings[7]);
        this.mDirtyFlags = -1;
        this.alsMenu.setTag((Object) null);
        this.batteryImageView.setTag((Object) null);
        this.carImageView.setTag((Object) null);
        this.dorrBackImageView.setTag((Object) null);
        this.dorrLeftFlImageView.setTag((Object) null);
        this.dorrLeftFrImageView.setTag((Object) null);
        this.dorrLeftRlImageView.setTag((Object) null);
        this.imageView19.setTag((Object) null);
        this.linearLayout2.setTag((Object) null);
        this.mboundView1 = bindings[1];
        this.mboundView1.setTag((Object) null);
        this.mboundView17 = bindings[17];
        this.mboundView17.setTag((Object) null);
        this.mboundView18 = bindings[18];
        this.mboundView18.setTag((Object) null);
        this.mboundView19 = bindings[19];
        this.mboundView19.setTag((Object) null);
        this.mboundView20 = bindings[20];
        this.mboundView20.setTag((Object) null);
        this.mboundView21 = bindings[21];
        this.mboundView21.setTag((Object) null);
        this.mboundView22 = bindings[22];
        this.mboundView22.setTag((Object) null);
        this.mboundView23 = bindings[23];
        this.mboundView23.setTag((Object) null);
        this.mboundView3 = bindings[3];
        this.mboundView3.setTag((Object) null);
        this.oilImageView.setTag((Object) null);
        this.speedPointerTextView.setTag((Object) null);
        this.speedometerImageView.setTag((Object) null);
        this.tachometerImageView.setTag((Object) null);
        this.textView18.setTag((Object) null);
        this.zspeedPointerImageView.setTag((Object) null);
        this.zspeedPointerTextView.setTag((Object) null);
        setRootTag(root);
        this.mCallback25 = new OnClickListener(this, 6);
        this.mCallback26 = new OnClickListener(this, 7);
        this.mCallback23 = new OnClickListener(this, 4);
        this.mCallback24 = new OnClickListener(this, 5);
        this.mCallback21 = new OnClickListener(this, 2);
        this.mCallback20 = new OnClickListener(this, 1);
        this.mCallback22 = new OnClickListener(this, 3);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
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
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
        }
        notifyPropertyChanged(11);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelCarInfoSevenmode((ObservableInt) object, fieldId);
            case 1:
                return onChangeViewModelCarInfoCarImage((ObservableBoolean) object, fieldId);
            case 2:
                return onChangeViewModelCarInfoUnit((ObservableInt) object, fieldId);
            case 3:
                return onChangeViewModelCarInfoTurnSpeed((ObservableInt) object, fieldId);
            case 4:
                return onChangeViewModelCarInfoRlDoorState((ObservableField) object, fieldId);
            case 5:
                return onChangeViewModelCarInfoTurnSpeedAnge((ObservableFloat) object, fieldId);
            case 6:
                return onChangeViewModelCarInfoFlDoorState((ObservableField) object, fieldId);
            case 7:
                return onChangeViewModelCarInfoBrakeValue((ObservableField) object, fieldId);
            case 8:
                return onChangeViewModelShowSevenMenu((ObservableBoolean) object, fieldId);
            case 9:
                return onChangeViewModelCarInfoFrDoorState((ObservableField) object, fieldId);
            case 10:
                return onChangeViewModelCarInfoRrDoorState((ObservableField) object, fieldId);
            case 11:
                return onChangeViewModelCarInfoTempStr((ObservableField) object, fieldId);
            case 12:
                return onChangeViewModelCarInfoSeatBeltpValue((ObservableField) object, fieldId);
            case 13:
                return onChangeViewModelCarInfoSpeed((ObservableInt) object, fieldId);
            case 14:
                return onChangeViewModelCarInfoBDoorState((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelCarInfoSevenmode(ObservableInt ViewModelCarInfoSevenmode, int fieldId) {
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

    private boolean onChangeViewModelCarInfoUnit(ObservableInt ViewModelCarInfoUnit, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoTurnSpeed(ObservableInt ViewModelCarInfoTurnSpeed, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoRlDoorState(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoTurnSpeedAnge(ObservableFloat ViewModelCarInfoTurnSpeedAnge, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoFlDoorState(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoBrakeValue(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeViewModelShowSevenMenu(ObservableBoolean ViewModelShowSevenMenu, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoFrDoorState(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoRrDoorState(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoTempStr(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoSeatBeltpValue(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoSpeed(ObservableInt ViewModelCarInfoSpeed, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoBDoorState(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        ObservableBoolean viewModelShowSevenMenu;
        String stringValueOfViewModelCarInfoTurnSpeed;
        float viewModelCarInfoTurnSpeedAngeGet;
        String viewModelCarInfoTempStrGet;
        Drawable viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel;
        int viewModelCarInfoBrakeValueViewVISIBLEViewGONE;
        String stringValueOfViewModelCarInfoSpeed;
        int viewModelCarInfoSpeedGet;
        int viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE;
        int viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE;
        int viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE;
        int viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE;
        int viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE;
        int viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE;
        Drawable viewModelCarInfoSevenmodeInt6MboundView23AndroidDrawableSevenColorCheckboxSelMboundView23AndroidDrawableSevenColorCheckboxN;
        Drawable viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN;
        Drawable viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN;
        Drawable viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN;
        Drawable viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN;
        Drawable viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN;
        int viewModelCarInfoSevenmodeGet;
        int viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE;
        int viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2;
        int viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE3;
        int viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2;
        int viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE3;
        int viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE2;
        String stringValueOfViewModelCarInfoSpeed2;
        int viewModelCarInfoBrakeValueViewVISIBLEViewGONE2;
        Drawable viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel2;
        String viewModelCarInfoTempStrGet2;
        boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet;
        ObservableBoolean viewModelCarInfoCarImage;
        boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet;
        ObservableField<Boolean> viewModelCarInfoBrakeValue;
        ObservableBoolean viewModelCarInfoCarImage2;
        boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet2;
        int i;
        boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet2;
        ObservableField<Boolean> viewModelCarInfoBrakeValue2;
        ObservableField<Boolean> viewModelCarInfoBDoorState;
        ObservableInt viewModelCarInfoSpeed;
        ObservableField<Boolean> viewModelCarInfoSeatBeltpValue;
        ObservableField<String> viewModelCarInfoTempStr;
        ObservableField<Boolean> viewModelCarInfoRrDoorState;
        ObservableField<Boolean> viewModelCarInfoFrDoorState;
        ObservableField<Boolean> viewModelCarInfoFlDoorState;
        ObservableFloat viewModelCarInfoTurnSpeedAnge;
        ObservableField<Boolean> viewModelCarInfoRlDoorState;
        ObservableInt viewModelCarInfoTurnSpeed;
        ObservableInt viewModelCarInfoUnit;
        ObservableInt viewModelCarInfoUnit2;
        Drawable drawable;
        long dirtyFlags2;
        Drawable viewModelCarInfoSevenmodeInt6MboundView23AndroidDrawableSevenColorCheckboxSelMboundView23AndroidDrawableSevenColorCheckboxN2;
        TextView textView;
        Drawable viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN2;
        Drawable viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN2;
        Drawable viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN3;
        Drawable viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN2;
        Drawable viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN3;
        Drawable viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN2;
        Drawable viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN3;
        Drawable viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN2;
        Drawable viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN3;
        Drawable viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN4;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        Drawable viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN3 = null;
        ObservableInt viewModelCarInfoSevenmode = null;
        int viewModelShowSevenMenuViewVISIBLEViewGONE = 0;
        Boolean viewModelCarInfoRlDoorStateGet = null;
        Drawable viewModelCarInfoSevenmodeInt5MboundView22AndroidDrawableSevenColorCheckboxSelMboundView22AndroidDrawableSevenColorCheckboxN = null;
        int viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE3 = 0;
        boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet = false;
        String stringValueOfViewModelCarInfoSpeed3 = null;
        boolean viewModelCarInfoSevenmodeInt0 = false;
        Drawable viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN4 = null;
        Drawable viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN4 = null;
        int viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE2 = 0;
        Boolean viewModelCarInfoFrDoorStateGet = null;
        Boolean viewModelCarInfoBDoorStateGet = null;
        int viewModelCarInfoSpeedGet2 = 0;
        int viewModelCarInfoSevenmodeGet2 = 0;
        Drawable viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel3 = null;
        Boolean viewModelCarInfoBrakeValueGet = null;
        String stringValueOfViewModelCarInfoTurnSpeed2 = null;
        int viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE4 = 0;
        DashboardViewModel viewModel = this.mViewModel;
        float viewModelCarInfoTurnSpeedAngeGet2 = 0.0f;
        int viewModelCarInfoUnitGet = 0;
        int viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE2 = 0;
        boolean viewModelCarInfoCarImageGet = false;
        int viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2 = 0;
        int viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE2 = 0;
        Boolean viewModelCarInfoSeatBeltpValueGet = null;
        boolean viewModelShowSevenMenuGet = false;
        Boolean viewModelCarInfoRrDoorStateGet = null;
        Drawable viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN5 = null;
        int viewModelCarInfoTurnSpeedGet = 0;
        int viewModelCarInfoBrakeValueViewVISIBLEViewGONE3 = 0;
        Boolean viewModelCarInfoFlDoorStateGet = null;
        String viewModelCarInfoTempStrGet3 = null;
        int viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE4 = 0;
        Drawable viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN4 = null;
        Drawable viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN4 = null;
        if ((dirtyFlags & 98560) != 0) {
            if (viewModel != null) {
                viewModelShowSevenMenu = viewModel.showSevenMenu;
            } else {
                viewModelShowSevenMenu = null;
            }
            DashboardViewModel dashboardViewModel = viewModel;
            updateRegistration(8, (Observable) viewModelShowSevenMenu);
            if (viewModelShowSevenMenu != null) {
                viewModelShowSevenMenuGet = viewModelShowSevenMenu.get();
            }
            if ((dirtyFlags & 98560) != 0) {
                if (viewModelShowSevenMenuGet) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
                }
            }
            viewModelShowSevenMenuViewVISIBLEViewGONE = viewModelShowSevenMenuGet ? 0 : 8;
        } else {
            viewModelShowSevenMenu = null;
        }
        if ((dirtyFlags & 98047) != 0) {
            CarInfo viewModelCarInfo = DashboardViewModel.carInfo;
            ObservableBoolean observableBoolean = viewModelShowSevenMenu;
            if ((dirtyFlags & 65537) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoSevenmode = viewModelCarInfo.sevenmode;
                }
                updateRegistration(0, (Observable) viewModelCarInfoSevenmode);
                if (viewModelCarInfoSevenmode != null) {
                    viewModelCarInfoSevenmodeGet2 = viewModelCarInfoSevenmode.get();
                }
                int viewModelCarInfoSevenmodeGet3 = viewModelCarInfoSevenmodeGet2;
                viewModelCarInfoBrakeValue = null;
                boolean viewModelCarInfoSevenmodeInt6 = viewModelCarInfoSevenmodeGet3 == 6;
                boolean viewModelCarInfoSevenmodeInt1 = viewModelCarInfoSevenmodeGet3 == 1;
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet = false;
                boolean viewModelCarInfoSevenmodeInt3 = viewModelCarInfoSevenmodeGet3 == 3;
                ObservableInt viewModelCarInfoSevenmode2 = viewModelCarInfoSevenmode;
                boolean viewModelCarInfoSevenmodeInt2 = viewModelCarInfoSevenmodeGet3 == 2;
                viewModelCarInfoCarImage = null;
                boolean viewModelCarInfoSevenmodeInt4 = viewModelCarInfoSevenmodeGet3 == 4;
                boolean viewModelCarInfoSevenmodeInt02 = viewModelCarInfoSevenmodeGet3 == 0;
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet = false;
                boolean viewModelCarInfoSevenmodeInt5 = viewModelCarInfoSevenmodeGet3 == 5;
                if ((dirtyFlags & 65537) != 0) {
                    if (viewModelCarInfoSevenmodeInt6) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                    }
                }
                if ((dirtyFlags & 65537) != 0) {
                    if (viewModelCarInfoSevenmodeInt1) {
                        dirtyFlags |= 281474976710656L;
                    } else {
                        dirtyFlags |= 140737488355328L;
                    }
                }
                if ((dirtyFlags & 65537) != 0) {
                    if (viewModelCarInfoSevenmodeInt3) {
                        dirtyFlags |= 67108864;
                    } else {
                        dirtyFlags |= 33554432;
                    }
                }
                if ((dirtyFlags & 65537) != 0) {
                    if (viewModelCarInfoSevenmodeInt2) {
                        dirtyFlags |= 1125899906842624L;
                    } else {
                        dirtyFlags |= 562949953421312L;
                    }
                }
                if ((dirtyFlags & 65537) != 0) {
                    if (viewModelCarInfoSevenmodeInt4) {
                        dirtyFlags |= 268435456;
                    } else {
                        dirtyFlags |= 134217728;
                    }
                }
                if ((dirtyFlags & 65537) != 0) {
                    if (viewModelCarInfoSevenmodeInt02) {
                        dirtyFlags |= 4398046511104L;
                    } else {
                        dirtyFlags |= 2199023255552L;
                    }
                }
                if ((dirtyFlags & 65537) != 0) {
                    if (viewModelCarInfoSevenmodeInt5) {
                        dirtyFlags |= 4194304;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
                    }
                }
                int viewModelCarInfoSevenmodeGet4 = viewModelCarInfoSevenmodeGet3;
                int viewModelCarInfoSevenmodeGet5 = R.drawable.seven_color_checkbox_sel;
                if (viewModelCarInfoSevenmodeInt6) {
                    dirtyFlags2 = dirtyFlags;
                    viewModelCarInfoSevenmodeInt6MboundView23AndroidDrawableSevenColorCheckboxSelMboundView23AndroidDrawableSevenColorCheckboxN2 = getDrawableFromResource(this.mboundView23, R.drawable.seven_color_checkbox_sel);
                } else {
                    dirtyFlags2 = dirtyFlags;
                    viewModelCarInfoSevenmodeInt6MboundView23AndroidDrawableSevenColorCheckboxSelMboundView23AndroidDrawableSevenColorCheckboxN2 = getDrawableFromResource(this.mboundView23, R.drawable.seven_color_checkbox_n);
                }
                if (viewModelCarInfoSevenmodeInt1) {
                    textView = this.mboundView18;
                } else {
                    textView = this.mboundView18;
                    viewModelCarInfoSevenmodeGet5 = R.drawable.seven_color_checkbox_n;
                }
                Drawable viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN5 = getDrawableFromResource(textView, viewModelCarInfoSevenmodeGet5);
                if (viewModelCarInfoSevenmodeInt3) {
                    viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN2 = viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN5;
                    viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN2 = getDrawableFromResource(this.mboundView20, R.drawable.seven_color_checkbox_sel);
                } else {
                    viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN2 = viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN5;
                    viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN2 = getDrawableFromResource(this.mboundView20, R.drawable.seven_color_checkbox_n);
                }
                if (viewModelCarInfoSevenmodeInt2) {
                    viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN3 = viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN2;
                    viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN2 = getDrawableFromResource(this.mboundView19, R.drawable.seven_color_checkbox_sel);
                } else {
                    viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN3 = viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN2;
                    viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN2 = getDrawableFromResource(this.mboundView19, R.drawable.seven_color_checkbox_n);
                }
                if (viewModelCarInfoSevenmodeInt4) {
                    viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN3 = viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN2;
                    viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN2 = getDrawableFromResource(this.mboundView21, R.drawable.seven_color_checkbox_sel);
                } else {
                    viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN3 = viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN2;
                    viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN2 = getDrawableFromResource(this.mboundView21, R.drawable.seven_color_checkbox_n);
                }
                if (viewModelCarInfoSevenmodeInt02) {
                    viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN3 = viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN2;
                    viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN2 = getDrawableFromResource(this.mboundView17, R.drawable.seven_color_checkbox_sel);
                } else {
                    viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN3 = viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN2;
                    viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN2 = getDrawableFromResource(this.mboundView17, R.drawable.seven_color_checkbox_n);
                }
                if (viewModelCarInfoSevenmodeInt5) {
                    viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN3 = viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN2;
                    viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN4 = getDrawableFromResource(this.mboundView22, R.drawable.seven_color_checkbox_sel);
                } else {
                    viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN3 = viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN2;
                    viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN4 = getDrawableFromResource(this.mboundView22, R.drawable.seven_color_checkbox_n);
                }
                viewModelCarInfoSevenmodeInt5MboundView22AndroidDrawableSevenColorCheckboxSelMboundView22AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN4;
                boolean z = viewModelCarInfoSevenmodeInt1;
                boolean z2 = viewModelCarInfoSevenmodeInt3;
                boolean z3 = viewModelCarInfoSevenmodeInt2;
                boolean z4 = viewModelCarInfoSevenmodeInt4;
                boolean z5 = viewModelCarInfoSevenmodeInt5;
                boolean z6 = viewModelCarInfoSevenmodeInt02;
                viewModelCarInfoSevenmode = viewModelCarInfoSevenmode2;
                viewModelCarInfoSevenmodeGet2 = viewModelCarInfoSevenmodeGet4;
                viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN4 = viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN2;
                viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN4 = viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN3;
                viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN4 = viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN3;
                viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN4 = viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN3;
                viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN5 = viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN3;
                viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN3 = viewModelCarInfoSevenmodeInt6MboundView23AndroidDrawableSevenColorCheckboxSelMboundView23AndroidDrawableSevenColorCheckboxN2;
                viewModelCarInfoSevenmodeInt0 = viewModelCarInfoSevenmodeInt6;
                dirtyFlags = dirtyFlags2;
            } else {
                viewModelCarInfoBrakeValue = null;
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet = false;
                viewModelCarInfoCarImage = null;
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet = false;
            }
            if ((dirtyFlags & 65538) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoCarImage2 = viewModelCarInfo.carImage;
                } else {
                    viewModelCarInfoCarImage2 = viewModelCarInfoCarImage;
                }
                updateRegistration(1, (Observable) viewModelCarInfoCarImage2);
                if (viewModelCarInfoCarImage2 != null) {
                    viewModelCarInfoCarImageGet = viewModelCarInfoCarImage2.get();
                }
                if ((dirtyFlags & 65538) != 0) {
                    if (viewModelCarInfoCarImageGet) {
                        dirtyFlags |= 274877906944L;
                    } else {
                        dirtyFlags |= 137438953472L;
                    }
                }
                viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2 = viewModelCarInfoCarImageGet ? 0 : 4;
            } else {
                viewModelCarInfoCarImage2 = viewModelCarInfoCarImage;
            }
            if ((dirtyFlags & 65540) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoUnit = viewModelCarInfo.unit;
                } else {
                    viewModelCarInfoUnit = null;
                }
                updateRegistration(2, (Observable) viewModelCarInfoUnit);
                if (viewModelCarInfoUnit != null) {
                    viewModelCarInfoUnitGet = viewModelCarInfoUnit.get();
                }
                int viewModelCarInfoUnitGet2 = viewModelCarInfoUnitGet;
                boolean viewModelCarInfoUnitInt1 = true;
                if (viewModelCarInfoUnitGet2 != 1) {
                    viewModelCarInfoUnitInt1 = false;
                }
                if ((dirtyFlags & 65540) != 0) {
                    if (viewModelCarInfoUnitInt1) {
                        dirtyFlags |= 4294967296L;
                    } else {
                        dirtyFlags |= 2147483648L;
                    }
                }
                if (viewModelCarInfoUnitInt1) {
                    viewModelCarInfoUnit2 = viewModelCarInfoUnit;
                    drawable = getDrawableFromResource(this.speedometerImageView, R.drawable.seven_color_speed_mp_wtach_level);
                } else {
                    viewModelCarInfoUnit2 = viewModelCarInfoUnit;
                    drawable = getDrawableFromResource(this.speedometerImageView, R.drawable.seven_color_speed_km_wtach_level);
                }
                viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel3 = drawable;
                int i2 = viewModelCarInfoUnitGet2;
                boolean z7 = viewModelCarInfoUnitInt1;
                ObservableInt observableInt = viewModelCarInfoUnit2;
            }
            if ((dirtyFlags & 65544) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoTurnSpeed = viewModelCarInfo.turnSpeed;
                } else {
                    viewModelCarInfoTurnSpeed = null;
                }
                updateRegistration(3, (Observable) viewModelCarInfoTurnSpeed);
                if (viewModelCarInfoTurnSpeed != null) {
                    viewModelCarInfoTurnSpeedGet = viewModelCarInfoTurnSpeed.get();
                }
                ObservableInt observableInt2 = viewModelCarInfoTurnSpeed;
                stringValueOfViewModelCarInfoTurnSpeed2 = String.valueOf(viewModelCarInfoTurnSpeedGet);
            }
            if ((dirtyFlags & 65552) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoRlDoorState = viewModelCarInfo.rlDoorState;
                } else {
                    viewModelCarInfoRlDoorState = null;
                }
                i = 4;
                updateRegistration(4, (Observable) viewModelCarInfoRlDoorState);
                if (viewModelCarInfoRlDoorState != null) {
                    viewModelCarInfoRlDoorStateGet = viewModelCarInfoRlDoorState.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet3 = ViewDataBinding.safeUnbox(viewModelCarInfoRlDoorStateGet);
                if ((dirtyFlags & 65552) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet3) {
                        dirtyFlags |= 1073741824;
                    } else {
                        dirtyFlags |= 536870912;
                    }
                }
                ObservableField<Boolean> observableField = viewModelCarInfoRlDoorState;
                viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet3 ? 0 : 4;
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet3;
            } else {
                i = 4;
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet;
            }
            if ((dirtyFlags & 65568) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoTurnSpeedAnge = viewModelCarInfo.turnSpeedAnge;
                } else {
                    viewModelCarInfoTurnSpeedAnge = null;
                }
                updateRegistration(5, (Observable) viewModelCarInfoTurnSpeedAnge);
                if (viewModelCarInfoTurnSpeedAnge != null) {
                    ObservableFloat observableFloat = viewModelCarInfoTurnSpeedAnge;
                    viewModelCarInfoTurnSpeedAngeGet2 = viewModelCarInfoTurnSpeedAnge.get();
                } else {
                    ObservableFloat observableFloat2 = viewModelCarInfoTurnSpeedAnge;
                }
            }
            if ((dirtyFlags & 65600) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoFlDoorState = viewModelCarInfo.flDoorState;
                } else {
                    viewModelCarInfoFlDoorState = null;
                }
                updateRegistration(6, (Observable) viewModelCarInfoFlDoorState);
                if (viewModelCarInfoFlDoorState != null) {
                    viewModelCarInfoFlDoorStateGet = viewModelCarInfoFlDoorState.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet2 = ViewDataBinding.safeUnbox(viewModelCarInfoFlDoorStateGet);
                if ((dirtyFlags & 65600) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet2) {
                        dirtyFlags |= 70368744177664L;
                    } else {
                        dirtyFlags |= 35184372088832L;
                    }
                }
                ObservableField<Boolean> observableField2 = viewModelCarInfoFlDoorState;
                viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE4 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet2 ? 0 : i;
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet2;
            }
            if ((dirtyFlags & 65664) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoBrakeValue2 = viewModelCarInfo.brakeValue;
                } else {
                    viewModelCarInfoBrakeValue2 = viewModelCarInfoBrakeValue;
                }
                updateRegistration(7, (Observable) viewModelCarInfoBrakeValue2);
                if (viewModelCarInfoBrakeValue2 != null) {
                    viewModelCarInfoBrakeValueGet = viewModelCarInfoBrakeValue2.get();
                }
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet2 = ViewDataBinding.safeUnbox(viewModelCarInfoBrakeValueGet);
                if ((dirtyFlags & 65664) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet2) {
                        dirtyFlags |= 17592186044416L;
                    } else {
                        dirtyFlags |= 8796093022208L;
                    }
                }
                viewModelCarInfoBrakeValueViewVISIBLEViewGONE3 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet2 ? 0 : 8;
            } else {
                viewModelCarInfoBrakeValue2 = viewModelCarInfoBrakeValue;
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet;
            }
            if ((dirtyFlags & 66048) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoFrDoorState = viewModelCarInfo.frDoorState;
                } else {
                    viewModelCarInfoFrDoorState = null;
                }
                ObservableField<Boolean> observableField3 = viewModelCarInfoBrakeValue2;
                updateRegistration(9, (Observable) viewModelCarInfoFrDoorState);
                if (viewModelCarInfoFrDoorState != null) {
                    viewModelCarInfoFrDoorStateGet = viewModelCarInfoFrDoorState.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFrDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoFrDoorStateGet);
                if ((dirtyFlags & 66048) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFrDoorStateGet) {
                        dirtyFlags |= 17179869184L;
                    } else {
                        dirtyFlags |= 8589934592L;
                    }
                }
                viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE4 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFrDoorStateGet ? 0 : 4;
                boolean z8 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFrDoorStateGet;
                ObservableField<Boolean> observableField4 = viewModelCarInfoFrDoorState;
            }
            if ((dirtyFlags & 66560) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoRrDoorState = viewModelCarInfo.rrDoorState;
                } else {
                    viewModelCarInfoRrDoorState = null;
                }
                updateRegistration(10, (Observable) viewModelCarInfoRrDoorState);
                if (viewModelCarInfoRrDoorState != null) {
                    viewModelCarInfoRrDoorStateGet = viewModelCarInfoRrDoorState.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoRrDoorStateGet);
                if ((dirtyFlags & 66560) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet) {
                        dirtyFlags |= 16777216;
                    } else {
                        dirtyFlags |= 8388608;
                    }
                }
                viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE3 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet ? 0 : 4;
                ObservableField<Boolean> observableField5 = viewModelCarInfoRrDoorState;
                boolean z9 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet;
            }
            if ((dirtyFlags & 67584) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoTempStr = viewModelCarInfo.tempStr;
                } else {
                    viewModelCarInfoTempStr = null;
                }
                updateRegistration(11, (Observable) viewModelCarInfoTempStr);
                if (viewModelCarInfoTempStr != null) {
                    ObservableField<String> observableField6 = viewModelCarInfoTempStr;
                    viewModelCarInfoTempStrGet3 = viewModelCarInfoTempStr.get();
                } else {
                    ObservableField<String> observableField7 = viewModelCarInfoTempStr;
                }
            }
            if ((dirtyFlags & 69632) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoSeatBeltpValue = viewModelCarInfo.seatBeltpValue;
                } else {
                    viewModelCarInfoSeatBeltpValue = null;
                }
                updateRegistration(12, (Observable) viewModelCarInfoSeatBeltpValue);
                if (viewModelCarInfoSeatBeltpValue != null) {
                    viewModelCarInfoSeatBeltpValueGet = viewModelCarInfoSeatBeltpValue.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet = ViewDataBinding.safeUnbox(viewModelCarInfoSeatBeltpValueGet);
                if ((dirtyFlags & 69632) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet) {
                        dirtyFlags |= 1099511627776L;
                    } else {
                        dirtyFlags |= 549755813888L;
                    }
                }
                ObservableField<Boolean> observableField8 = viewModelCarInfoSeatBeltpValue;
                boolean z10 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet;
                viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet ? 8 : 0;
            }
            if ((dirtyFlags & 73728) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoSpeed = viewModelCarInfo.speed;
                } else {
                    viewModelCarInfoSpeed = null;
                }
                updateRegistration(13, (Observable) viewModelCarInfoSpeed);
                if (viewModelCarInfoSpeed != null) {
                    viewModelCarInfoSpeedGet2 = viewModelCarInfoSpeed.get();
                }
                ObservableInt observableInt3 = viewModelCarInfoSpeed;
                stringValueOfViewModelCarInfoSpeed3 = String.valueOf(viewModelCarInfoSpeedGet2);
            }
            if ((dirtyFlags & 81920) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoBDoorState = viewModelCarInfo.bDoorState;
                } else {
                    viewModelCarInfoBDoorState = null;
                }
                updateRegistration(14, (Observable) viewModelCarInfoBDoorState);
                if (viewModelCarInfoBDoorState != null) {
                    viewModelCarInfoBDoorStateGet = viewModelCarInfoBDoorState.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoBDoorStateGet);
                if ((dirtyFlags & 81920) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet) {
                        dirtyFlags |= 68719476736L;
                    } else {
                        dirtyFlags |= 34359738368L;
                    }
                }
                viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet ? 0 : 4;
                ObservableField<Boolean> observableField9 = viewModelCarInfoBDoorState;
                CarInfo carInfo = viewModelCarInfo;
                boolean z11 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet;
            } else {
                CarInfo carInfo2 = viewModelCarInfo;
            }
            viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE3;
            stringValueOfViewModelCarInfoSpeed = stringValueOfViewModelCarInfoSpeed3;
            viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN4;
            viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE2;
            viewModelCarInfoSpeedGet = viewModelCarInfoSpeedGet2;
            viewModelCarInfoSevenmodeGet = viewModelCarInfoSevenmodeGet2;
            viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel = viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel3;
            stringValueOfViewModelCarInfoTurnSpeed = stringValueOfViewModelCarInfoTurnSpeed2;
            viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE4;
            viewModelCarInfoTurnSpeedAngeGet = viewModelCarInfoTurnSpeedAngeGet2;
            viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE2;
            viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE = viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE2;
            viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN5;
            viewModelCarInfoBrakeValueViewVISIBLEViewGONE = viewModelCarInfoBrakeValueViewVISIBLEViewGONE3;
            viewModelCarInfoTempStrGet = viewModelCarInfoTempStrGet3;
            viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN4;
            viewModelCarInfoSevenmodeInt6MboundView23AndroidDrawableSevenColorCheckboxSelMboundView23AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN3;
            boolean viewModelCarInfoSevenmodeInt62 = viewModelCarInfoSevenmodeInt0;
            viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN4;
            viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN4;
            viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE = viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2;
            viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE4;
        } else {
            viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE = 0;
            stringValueOfViewModelCarInfoSpeed = null;
            viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN = null;
            viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE = 0;
            viewModelCarInfoSpeedGet = 0;
            viewModelCarInfoSevenmodeGet = 0;
            viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel = null;
            stringValueOfViewModelCarInfoTurnSpeed = null;
            viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE = 0;
            viewModelCarInfoTurnSpeedAngeGet = 0.0f;
            viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE = 0;
            viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE = 0;
            viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN = null;
            viewModelCarInfoBrakeValueViewVISIBLEViewGONE = 0;
            viewModelCarInfoTempStrGet = null;
            viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN = null;
            viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN = null;
            viewModelCarInfoSevenmodeInt6MboundView23AndroidDrawableSevenColorCheckboxSelMboundView23AndroidDrawableSevenColorCheckboxN = null;
            viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN = null;
            viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE = 0;
            viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE = 0;
        }
        if ((dirtyFlags & 98560) != 0) {
            viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2 = viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE;
            this.alsMenu.setVisibility(viewModelShowSevenMenuViewVISIBLEViewGONE);
        } else {
            viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2 = viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE;
        }
        if ((dirtyFlags & 69632) != 0) {
            this.batteryImageView.setVisibility(viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 65537) != 0) {
            this.batteryImageView.setImageLevel(viewModelCarInfoSevenmodeGet);
            this.carImageView.setImageLevel(viewModelCarInfoSevenmodeGet);
            this.mboundView1.setImageLevel(viewModelCarInfoSevenmodeGet);
            TextViewBindingAdapter.setDrawableLeft(this.mboundView17, viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN);
            TextViewBindingAdapter.setDrawableLeft(this.mboundView18, viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN);
            TextViewBindingAdapter.setDrawableLeft(this.mboundView19, viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN);
            TextViewBindingAdapter.setDrawableLeft(this.mboundView20, viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN);
            TextViewBindingAdapter.setDrawableLeft(this.mboundView21, viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN);
            TextViewBindingAdapter.setDrawableLeft(this.mboundView22, viewModelCarInfoSevenmodeInt5MboundView22AndroidDrawableSevenColorCheckboxSelMboundView22AndroidDrawableSevenColorCheckboxN);
            TextViewBindingAdapter.setDrawableLeft(this.mboundView23, viewModelCarInfoSevenmodeInt6MboundView23AndroidDrawableSevenColorCheckboxSelMboundView23AndroidDrawableSevenColorCheckboxN);
            this.oilImageView.setImageLevel(viewModelCarInfoSevenmodeGet);
            this.speedometerImageView.setImageLevel(viewModelCarInfoSevenmodeGet);
            this.tachometerImageView.setImageLevel(viewModelCarInfoSevenmodeGet);
        }
        if ((dirtyFlags & 65538) != 0) {
            this.carImageView.setVisibility(viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 81920) != 0) {
            this.dorrBackImageView.setVisibility(viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 65600) != 0) {
            int i3 = viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE;
            viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE3 = viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2;
            this.dorrLeftFlImageView.setVisibility(viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE3);
        } else {
            viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE3 = viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2;
        }
        if ((dirtyFlags & 66048) != 0) {
            int i4 = viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE3;
            viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2 = viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE;
            this.dorrLeftFrImageView.setVisibility(viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2);
        } else {
            viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2 = viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE;
        }
        if ((dirtyFlags & 65552) != 0) {
            int i5 = viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2;
            viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE3 = viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE;
            this.dorrLeftRlImageView.setVisibility(viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE3);
        } else {
            viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE3 = viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE;
        }
        if ((dirtyFlags & 66560) != 0) {
            int i6 = viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE3;
            viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE2 = viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE;
            this.imageView19.setVisibility(viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE2);
        } else {
            int viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE3 = viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE3;
            viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE2 = viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE;
        }
        if ((dirtyFlags & PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) != 0) {
            int i7 = viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE2;
            this.mboundView17.setOnClickListener(this.mCallback20);
            this.mboundView18.setOnClickListener(this.mCallback21);
            this.mboundView19.setOnClickListener(this.mCallback22);
            this.mboundView20.setOnClickListener(this.mCallback23);
            this.mboundView21.setOnClickListener(this.mCallback24);
            this.mboundView22.setOnClickListener(this.mCallback25);
            this.mboundView23.setOnClickListener(this.mCallback26);
        }
        if ((dirtyFlags & 73728) != 0) {
            DashboardViewModel.setSevenSpeedRotation(this.mboundView3, viewModelCarInfoSpeedGet);
            int i8 = viewModelCarInfoSevenmodeGet;
            stringValueOfViewModelCarInfoSpeed2 = stringValueOfViewModelCarInfoSpeed;
            TextViewBindingAdapter.setText(this.speedPointerTextView, stringValueOfViewModelCarInfoSpeed2);
        } else {
            int i9 = viewModelCarInfoSpeedGet;
            stringValueOfViewModelCarInfoSpeed2 = stringValueOfViewModelCarInfoSpeed;
        }
        if ((dirtyFlags & 65664) != 0) {
            String str = stringValueOfViewModelCarInfoSpeed2;
            viewModelCarInfoBrakeValueViewVISIBLEViewGONE2 = viewModelCarInfoBrakeValueViewVISIBLEViewGONE;
            this.oilImageView.setVisibility(viewModelCarInfoBrakeValueViewVISIBLEViewGONE2);
        } else {
            viewModelCarInfoBrakeValueViewVISIBLEViewGONE2 = viewModelCarInfoBrakeValueViewVISIBLEViewGONE;
        }
        if ((dirtyFlags & 65540) != 0) {
            int i10 = viewModelCarInfoBrakeValueViewVISIBLEViewGONE2;
            viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel2 = viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel;
            ImageViewBindingAdapter.setImageDrawable(this.speedometerImageView, viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel2);
        } else {
            viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel2 = viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel;
        }
        if ((dirtyFlags & 67584) != 0) {
            Drawable drawable2 = viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel2;
            viewModelCarInfoTempStrGet2 = viewModelCarInfoTempStrGet;
            TextViewBindingAdapter.setText(this.textView18, viewModelCarInfoTempStrGet2);
        } else {
            viewModelCarInfoTempStrGet2 = viewModelCarInfoTempStrGet;
        }
        if ((dirtyFlags & 65568) != 0) {
            String str2 = viewModelCarInfoTempStrGet2;
            DashboardViewModel.setALSRotation(this.zspeedPointerImageView, viewModelCarInfoTurnSpeedAngeGet);
        } else {
            float f = viewModelCarInfoTurnSpeedAngeGet;
        }
        if ((dirtyFlags & 65544) != 0) {
            long j = dirtyFlags;
            TextViewBindingAdapter.setText(this.zspeedPointerTextView, stringValueOfViewModelCarInfoTurnSpeed);
            return;
        }
        String str3 = stringValueOfViewModelCarInfoTurnSpeed;
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean viewModelJavaLangObjectNull = false;
        boolean viewModelJavaLangObjectNull2 = true;
        switch (sourceId) {
            case 1:
                DashboardViewModel viewModel = this.mViewModel;
                if (viewModel == null) {
                    viewModelJavaLangObjectNull2 = false;
                }
                if (viewModelJavaLangObjectNull2) {
                    viewModel.onSevenModeClick(0);
                    return;
                }
                return;
            case 2:
                DashboardViewModel viewModel2 = this.mViewModel;
                if (viewModel2 != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel2.onSevenModeClick(1);
                    return;
                }
                return;
            case 3:
                DashboardViewModel viewModel3 = this.mViewModel;
                if (viewModel3 != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel3.onSevenModeClick(2);
                    return;
                }
                return;
            case 4:
                DashboardViewModel viewModel4 = this.mViewModel;
                if (viewModel4 != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel4.onSevenModeClick(3);
                    return;
                }
                return;
            case 5:
                DashboardViewModel viewModel5 = this.mViewModel;
                if (viewModel5 != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel5.onSevenModeClick(4);
                    return;
                }
                return;
            case 6:
                DashboardViewModel viewModel6 = this.mViewModel;
                if (viewModel6 != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel6.onSevenModeClick(5);
                    return;
                }
                return;
            case 7:
                DashboardViewModel viewModel7 = this.mViewModel;
                if (viewModel7 != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel7.onSevenModeClick(6);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
