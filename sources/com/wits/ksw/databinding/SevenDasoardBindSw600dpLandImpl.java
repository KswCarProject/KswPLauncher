package com.wits.ksw.databinding;

import android.content.Context;
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
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.bean.CarInfo;
import com.wits.ksw.launcher.model.DashboardViewModel;
import com.wits.ksw.launcher.view.ID7SpeedImageView;

public class SevenDasoardBindSw600dpLandImpl extends SevenDasoardBind implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback231;
    private final View.OnClickListener mCallback232;
    private final View.OnClickListener mCallback233;
    private final View.OnClickListener mCallback234;
    private final View.OnClickListener mCallback235;
    private final View.OnClickListener mCallback236;
    private final View.OnClickListener mCallback237;
    private long mDirtyFlags;
    private final ID7SpeedImageView mboundView1;
    private final TextView mboundView17;
    private final TextView mboundView18;
    private final TextView mboundView19;
    private final TextView mboundView20;
    private final TextView mboundView21;
    private final TextView mboundView22;
    private final TextView mboundView23;
    private final ImageView mboundView3;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.alsRadioGroup, 25);
        sparseIntArray.put(R.id.time, 26);
    }

    public SevenDasoardBindSw600dpLandImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 27, sIncludes, sViewsWithIds));
    }

    private SevenDasoardBindSw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
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
        ID7SpeedImageView iD7SpeedImageView = bindings[1];
        this.mboundView1 = iD7SpeedImageView;
        iD7SpeedImageView.setTag((Object) null);
        TextView textView = bindings[17];
        this.mboundView17 = textView;
        textView.setTag((Object) null);
        TextView textView2 = bindings[18];
        this.mboundView18 = textView2;
        textView2.setTag((Object) null);
        TextView textView3 = bindings[19];
        this.mboundView19 = textView3;
        textView3.setTag((Object) null);
        TextView textView4 = bindings[20];
        this.mboundView20 = textView4;
        textView4.setTag((Object) null);
        TextView textView5 = bindings[21];
        this.mboundView21 = textView5;
        textView5.setTag((Object) null);
        TextView textView6 = bindings[22];
        this.mboundView22 = textView6;
        textView6.setTag((Object) null);
        TextView textView7 = bindings[23];
        this.mboundView23 = textView7;
        textView7.setTag((Object) null);
        ImageView imageView = bindings[3];
        this.mboundView3 = imageView;
        imageView.setTag((Object) null);
        this.oilImageView.setTag((Object) null);
        this.speedPointerTextView.setTag((Object) null);
        this.speedometerImageView.setTag((Object) null);
        this.tachometerImageView.setTag((Object) null);
        this.textView18.setTag((Object) null);
        this.zspeedPointerImageView.setTag((Object) null);
        this.zspeedPointerTextView.setTag((Object) null);
        setRootTag(root);
        this.mCallback233 = new OnClickListener(this, 3);
        this.mCallback234 = new OnClickListener(this, 4);
        this.mCallback231 = new OnClickListener(this, 1);
        this.mCallback232 = new OnClickListener(this, 2);
        this.mCallback237 = new OnClickListener(this, 7);
        this.mCallback235 = new OnClickListener(this, 5);
        this.mCallback236 = new OnClickListener(this, 6);
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
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
        }
        notifyPropertyChanged(16);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelShowSevenMenu((ObservableBoolean) object, fieldId);
            case 1:
                return onChangeViewModelCarInfoSevenmode((ObservableInt) object, fieldId);
            case 2:
                return onChangeViewModelCarInfoCarImage((ObservableBoolean) object, fieldId);
            case 3:
                return onChangeViewModelCarInfoFrDoorState((ObservableField) object, fieldId);
            case 4:
                return onChangeViewModelCarInfoRrDoorState((ObservableField) object, fieldId);
            case 5:
                return onChangeViewModelCarInfoTempStr((ObservableField) object, fieldId);
            case 6:
                return onChangeViewModelCarInfoUnit((ObservableInt) object, fieldId);
            case 7:
                return onChangeViewModelCarInfoSeatBeltpValue((ObservableField) object, fieldId);
            case 8:
                return onChangeViewModelCarInfoTurnSpeed((ObservableInt) object, fieldId);
            case 9:
                return onChangeViewModelCarInfoRlDoorState((ObservableField) object, fieldId);
            case 10:
                return onChangeViewModelCarInfoTurnSpeedAnge((ObservableFloat) object, fieldId);
            case 11:
                return onChangeViewModelCarInfoFlDoorState((ObservableField) object, fieldId);
            case 12:
                return onChangeViewModelCarInfoSpeed((ObservableInt) object, fieldId);
            case 13:
                return onChangeViewModelCarInfoBDoorState((ObservableField) object, fieldId);
            case 14:
                return onChangeViewModelCarInfoBrakeValue((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelShowSevenMenu(ObservableBoolean ViewModelShowSevenMenu, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoSevenmode(ObservableInt ViewModelCarInfoSevenmode, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoCarImage(ObservableBoolean ViewModelCarInfoCarImage, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoFrDoorState(ObservableField<Boolean> observableField, int fieldId) {
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

    private boolean onChangeViewModelCarInfoUnit(ObservableInt ViewModelCarInfoUnit, int fieldId) {
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

    private boolean onChangeViewModelCarInfoTurnSpeed(ObservableInt ViewModelCarInfoTurnSpeed, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoRlDoorState(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoTurnSpeedAnge(ObservableFloat ViewModelCarInfoTurnSpeedAnge, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoFlDoorState(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoSpeed(ObservableInt ViewModelCarInfoSpeed, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoBDoorState(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoBrakeValue(ObservableField<Boolean> observableField, int fieldId) {
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
        String stringValueOfViewModelCarInfoTurnSpeed;
        Drawable viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel;
        String viewModelCarInfoTempStrGet;
        int viewModelCarInfoBrakeValueViewVISIBLEViewGONE;
        ObservableBoolean viewModelShowSevenMenu;
        int viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE;
        int viewModelCarInfoBrakeValueViewVISIBLEViewGONE2;
        int viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE;
        int viewModelCarInfoSevenmodeGet;
        String stringValueOfViewModelCarInfoSpeed;
        float viewModelCarInfoTurnSpeedAngeGet;
        int viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE;
        int viewModelShowSevenMenuViewVISIBLEViewGONE;
        int viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE;
        int viewModelCarInfoUnitGet;
        Drawable viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN;
        Drawable viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN;
        Drawable viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN;
        Drawable viewModelCarInfoSevenmodeInt5MboundView22AndroidDrawableSevenColorCheckboxSelMboundView22AndroidDrawableSevenColorCheckboxN;
        Drawable viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN;
        Drawable viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN;
        int viewModelCarInfoSevenmodeGet2;
        int viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE;
        ObservableBoolean viewModelShowSevenMenu2;
        int viewModelShowSevenMenuViewVISIBLEViewGONE2;
        int viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2;
        int viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE3;
        int viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE;
        int viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE2;
        String stringValueOfViewModelCarInfoSpeed2;
        int viewModelCarInfoBrakeValueViewVISIBLEViewGONE3;
        Drawable viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel2;
        String viewModelCarInfoTempStrGet2;
        boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet;
        int i;
        ObservableField<Boolean> viewModelCarInfoBDoorState;
        ObservableField<Boolean> viewModelCarInfoBrakeValue;
        ObservableInt viewModelCarInfoSpeed;
        ObservableField<Boolean> viewModelCarInfoFlDoorState;
        ObservableFloat viewModelCarInfoTurnSpeedAnge;
        ObservableField<Boolean> viewModelCarInfoRlDoorState;
        ObservableInt viewModelCarInfoTurnSpeed;
        ObservableField<Boolean> viewModelCarInfoSeatBeltpValue;
        ObservableInt viewModelCarInfoUnit;
        ObservableField<String> viewModelCarInfoTempStr;
        ObservableField<Boolean> viewModelCarInfoRrDoorState;
        ObservableField<Boolean> viewModelCarInfoFrDoorState;
        int viewModelCarInfoSevenmodeGet3;
        Drawable drawable;
        Drawable viewModelCarInfoSevenmodeInt6MboundView23AndroidDrawableSevenColorCheckboxSelMboundView23AndroidDrawableSevenColorCheckboxN;
        Drawable drawable2;
        Drawable viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN2;
        Drawable drawable3;
        Drawable drawable4;
        Drawable drawable5;
        Drawable viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN2;
        Drawable viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN3;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        Drawable viewModelCarInfoSevenmodeInt6MboundView23AndroidDrawableSevenColorCheckboxSelMboundView23AndroidDrawableSevenColorCheckboxN2 = null;
        ObservableInt viewModelCarInfoSevenmode = null;
        boolean viewModelCarInfoSevenmodeInt6 = false;
        int viewModelCarInfoUnitGet2 = 0;
        int viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE = 0;
        ObservableBoolean viewModelCarInfoCarImage = null;
        Boolean viewModelCarInfoRlDoorStateGet = null;
        Drawable viewModelCarInfoSevenmodeInt5MboundView22AndroidDrawableSevenColorCheckboxSelMboundView22AndroidDrawableSevenColorCheckboxN2 = null;
        int viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE3 = 0;
        String stringValueOfViewModelCarInfoSpeed3 = null;
        Drawable viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN2 = null;
        Drawable viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN2 = null;
        int viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE2 = 0;
        boolean viewModelCarInfoCarImageGet = false;
        Boolean viewModelCarInfoFrDoorStateGet = null;
        int viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2 = 0;
        int viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE2 = 0;
        Boolean viewModelCarInfoBDoorStateGet = null;
        int viewModelCarInfoSpeedGet = 0;
        Boolean viewModelCarInfoSeatBeltpValueGet = null;
        boolean viewModelShowSevenMenuGet = false;
        int viewModelCarInfoSevenmodeGet4 = 0;
        Boolean viewModelCarInfoRrDoorStateGet = null;
        Drawable viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN2 = null;
        int viewModelCarInfoTurnSpeedGet = 0;
        Boolean viewModelCarInfoFlDoorStateGet = null;
        String viewModelCarInfoTempStrGet3 = null;
        Drawable viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel3 = null;
        Boolean viewModelCarInfoBrakeValueGet = null;
        int viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE4 = 0;
        String stringValueOfViewModelCarInfoTurnSpeed2 = null;
        int viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2 = 0;
        Drawable viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN4 = null;
        DashboardViewModel viewModel = this.mViewModel;
        Drawable viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN5 = null;
        float viewModelCarInfoTurnSpeedAngeGet2 = 0.0f;
        if ((dirtyFlags & 98302) != 0) {
            CarInfo viewModelCarInfo = DashboardViewModel.carInfo;
            if ((dirtyFlags & 65538) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoSevenmode = viewModelCarInfo.sevenmode;
                }
                updateRegistration(1, (Observable) viewModelCarInfoSevenmode);
                if (viewModelCarInfoSevenmode != null) {
                    viewModelCarInfoSevenmodeGet3 = viewModelCarInfoSevenmode.get();
                } else {
                    viewModelCarInfoSevenmodeGet3 = 0;
                }
                boolean viewModelCarInfoSevenmodeInt4 = viewModelCarInfoSevenmodeGet3 == 4;
                boolean viewModelCarInfoSevenmodeInt62 = viewModelCarInfoSevenmodeGet3 == 6;
                boolean viewModelCarInfoSevenmodeInt1 = viewModelCarInfoSevenmodeGet3 == 1;
                boolean viewModelCarInfoSevenmodeInt0 = viewModelCarInfoSevenmodeGet3 == 0;
                viewModelShowSevenMenu = null;
                boolean viewModelCarInfoSevenmodeInt3 = viewModelCarInfoSevenmodeGet3 == 3;
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet = false;
                boolean viewModelCarInfoSevenmodeInt2 = viewModelCarInfoSevenmodeGet3 == 2;
                ObservableInt viewModelCarInfoSevenmode2 = viewModelCarInfoSevenmode;
                boolean viewModelCarInfoSevenmodeInt5 = viewModelCarInfoSevenmodeGet3 == 5;
                if ((dirtyFlags & 65538) != 0) {
                    if (viewModelCarInfoSevenmodeInt4) {
                        dirtyFlags |= 1073741824;
                    } else {
                        dirtyFlags |= 536870912;
                    }
                }
                if ((dirtyFlags & 65538) != 0) {
                    if (viewModelCarInfoSevenmodeInt62) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                    }
                }
                if ((dirtyFlags & 65538) != 0) {
                    if (viewModelCarInfoSevenmodeInt1) {
                        dirtyFlags |= 281474976710656L;
                    } else {
                        dirtyFlags |= 140737488355328L;
                    }
                }
                if ((dirtyFlags & 65538) != 0) {
                    if (viewModelCarInfoSevenmodeInt0) {
                        dirtyFlags |= 274877906944L;
                    } else {
                        dirtyFlags |= 137438953472L;
                    }
                }
                if ((dirtyFlags & 65538) != 0) {
                    if (viewModelCarInfoSevenmodeInt3) {
                        dirtyFlags |= 268435456;
                    } else {
                        dirtyFlags |= 134217728;
                    }
                }
                if ((dirtyFlags & 65538) != 0) {
                    if (viewModelCarInfoSevenmodeInt2) {
                        dirtyFlags |= 1125899906842624L;
                    } else {
                        dirtyFlags |= 562949953421312L;
                    }
                }
                if ((dirtyFlags & 65538) != 0) {
                    if (viewModelCarInfoSevenmodeInt5) {
                        dirtyFlags |= 16777216;
                    } else {
                        dirtyFlags |= 8388608;
                    }
                }
                long dirtyFlags2 = dirtyFlags;
                int i2 = R.drawable.seven_color_checkbox_sel;
                Context context = this.mboundView21.getContext();
                if (!viewModelCarInfoSevenmodeInt4) {
                    i2 = R.drawable.seven_color_checkbox_n;
                }
                Drawable viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN3 = AppCompatResources.getDrawable(context, i2);
                if (viewModelCarInfoSevenmodeInt62) {
                    viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN2 = viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN3;
                    drawable = AppCompatResources.getDrawable(this.mboundView23.getContext(), R.drawable.seven_color_checkbox_sel);
                } else {
                    viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN2 = viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN3;
                    drawable = AppCompatResources.getDrawable(this.mboundView23.getContext(), R.drawable.seven_color_checkbox_n);
                }
                Drawable viewModelCarInfoSevenmodeInt6MboundView23AndroidDrawableSevenColorCheckboxSelMboundView23AndroidDrawableSevenColorCheckboxN3 = drawable;
                if (viewModelCarInfoSevenmodeInt1) {
                    viewModelCarInfoSevenmodeInt6MboundView23AndroidDrawableSevenColorCheckboxSelMboundView23AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt6MboundView23AndroidDrawableSevenColorCheckboxSelMboundView23AndroidDrawableSevenColorCheckboxN3;
                    drawable2 = AppCompatResources.getDrawable(this.mboundView18.getContext(), R.drawable.seven_color_checkbox_sel);
                } else {
                    viewModelCarInfoSevenmodeInt6MboundView23AndroidDrawableSevenColorCheckboxSelMboundView23AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt6MboundView23AndroidDrawableSevenColorCheckboxSelMboundView23AndroidDrawableSevenColorCheckboxN3;
                    drawable2 = AppCompatResources.getDrawable(this.mboundView18.getContext(), R.drawable.seven_color_checkbox_n);
                }
                Drawable viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN3 = drawable2;
                if (viewModelCarInfoSevenmodeInt0) {
                    viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN2 = viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN3;
                    drawable3 = AppCompatResources.getDrawable(this.mboundView17.getContext(), R.drawable.seven_color_checkbox_sel);
                } else {
                    viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN2 = viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN3;
                    drawable3 = AppCompatResources.getDrawable(this.mboundView17.getContext(), R.drawable.seven_color_checkbox_n);
                }
                Drawable viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN3 = drawable3;
                if (viewModelCarInfoSevenmodeInt3) {
                    viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN2 = viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN3;
                    drawable4 = AppCompatResources.getDrawable(this.mboundView20.getContext(), R.drawable.seven_color_checkbox_sel);
                } else {
                    viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN2 = viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN3;
                    drawable4 = AppCompatResources.getDrawable(this.mboundView20.getContext(), R.drawable.seven_color_checkbox_n);
                }
                Drawable viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN3 = drawable4;
                if (viewModelCarInfoSevenmodeInt2) {
                    viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN2 = viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN3;
                    drawable5 = AppCompatResources.getDrawable(this.mboundView19.getContext(), R.drawable.seven_color_checkbox_sel);
                } else {
                    viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN2 = viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN3;
                    drawable5 = AppCompatResources.getDrawable(this.mboundView19.getContext(), R.drawable.seven_color_checkbox_n);
                }
                Drawable viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN6 = drawable5;
                if (viewModelCarInfoSevenmodeInt5) {
                    viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN2 = viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN6;
                    viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN3 = AppCompatResources.getDrawable(this.mboundView22.getContext(), R.drawable.seven_color_checkbox_sel);
                } else {
                    viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN2 = viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN6;
                    viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN3 = AppCompatResources.getDrawable(this.mboundView22.getContext(), R.drawable.seven_color_checkbox_n);
                }
                viewModelCarInfoSevenmodeInt5MboundView22AndroidDrawableSevenColorCheckboxSelMboundView22AndroidDrawableSevenColorCheckboxN2 = viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN3;
                boolean z = viewModelCarInfoSevenmodeInt1;
                boolean z2 = viewModelCarInfoSevenmodeInt0;
                dirtyFlags = dirtyFlags2;
                viewModelCarInfoSevenmodeInt6MboundView23AndroidDrawableSevenColorCheckboxSelMboundView23AndroidDrawableSevenColorCheckboxN2 = viewModelCarInfoSevenmodeInt6MboundView23AndroidDrawableSevenColorCheckboxSelMboundView23AndroidDrawableSevenColorCheckboxN;
                viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN5 = viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN2;
                viewModelCarInfoSevenmodeGet4 = viewModelCarInfoSevenmodeGet3;
                boolean z3 = viewModelCarInfoSevenmodeInt3;
                boolean z4 = viewModelCarInfoSevenmodeInt2;
                boolean viewModelCarInfoSevenmodeInt02 = viewModelCarInfoSevenmodeInt62;
                viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN4 = viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN2;
                viewModelCarInfoSevenmodeInt6 = viewModelCarInfoSevenmodeInt4;
                boolean z5 = viewModelCarInfoSevenmodeInt5;
                viewModelCarInfoSevenmode = viewModelCarInfoSevenmode2;
            } else {
                viewModelShowSevenMenu = null;
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet = false;
            }
            if ((dirtyFlags & 65540) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoCarImage = viewModelCarInfo.carImage;
                }
                updateRegistration(2, (Observable) viewModelCarInfoCarImage);
                if (viewModelCarInfoCarImage != null) {
                    viewModelCarInfoCarImageGet = viewModelCarInfoCarImage.get();
                }
                if ((dirtyFlags & 65540) != 0) {
                    if (viewModelCarInfoCarImageGet) {
                        dirtyFlags |= 17179869184L;
                    } else {
                        dirtyFlags |= 8589934592L;
                    }
                }
                viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2 = viewModelCarInfoCarImageGet ? 0 : 4;
            }
            if ((dirtyFlags & 65544) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoFrDoorState = viewModelCarInfo.frDoorState;
                } else {
                    viewModelCarInfoFrDoorState = null;
                }
                updateRegistration(3, (Observable) viewModelCarInfoFrDoorState);
                if (viewModelCarInfoFrDoorState != null) {
                    viewModelCarInfoFrDoorStateGet = viewModelCarInfoFrDoorState.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFrDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoFrDoorStateGet);
                if ((dirtyFlags & 65544) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFrDoorStateGet) {
                        dirtyFlags |= 70368744177664L;
                    } else {
                        dirtyFlags |= 35184372088832L;
                    }
                }
                ObservableField<Boolean> observableField = viewModelCarInfoFrDoorState;
                boolean z6 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFrDoorStateGet;
                viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFrDoorStateGet ? 0 : 4;
            }
            if ((dirtyFlags & 65552) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoRrDoorState = viewModelCarInfo.rrDoorState;
                } else {
                    viewModelCarInfoRrDoorState = null;
                }
                updateRegistration(4, (Observable) viewModelCarInfoRrDoorState);
                if (viewModelCarInfoRrDoorState != null) {
                    viewModelCarInfoRrDoorStateGet = viewModelCarInfoRrDoorState.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoRrDoorStateGet);
                if ((dirtyFlags & 65552) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet) {
                        dirtyFlags |= 67108864;
                    } else {
                        dirtyFlags |= 33554432;
                    }
                }
                ObservableField<Boolean> observableField2 = viewModelCarInfoRrDoorState;
                boolean z7 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet;
                viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE3 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet ? 0 : 4;
            }
            if ((dirtyFlags & 65568) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoTempStr = viewModelCarInfo.tempStr;
                } else {
                    viewModelCarInfoTempStr = null;
                }
                updateRegistration(5, (Observable) viewModelCarInfoTempStr);
                if (viewModelCarInfoTempStr != null) {
                    ObservableField<String> observableField3 = viewModelCarInfoTempStr;
                    viewModelCarInfoTempStrGet3 = viewModelCarInfoTempStr.get();
                } else {
                    ObservableField<String> observableField4 = viewModelCarInfoTempStr;
                }
            }
            if ((dirtyFlags & 65600) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoUnit = viewModelCarInfo.unit;
                } else {
                    viewModelCarInfoUnit = null;
                }
                updateRegistration(6, (Observable) viewModelCarInfoUnit);
                if (viewModelCarInfoUnit != null) {
                    viewModelCarInfoUnitGet2 = viewModelCarInfoUnit.get();
                }
                boolean viewModelCarInfoUnitInt1 = true;
                if (viewModelCarInfoUnitGet2 != 1) {
                    viewModelCarInfoUnitInt1 = false;
                }
                if ((dirtyFlags & 65600) != 0) {
                    if (viewModelCarInfoUnitInt1) {
                        dirtyFlags |= 4398046511104L;
                    } else {
                        dirtyFlags |= 2199023255552L;
                    }
                }
                ObservableInt observableInt = viewModelCarInfoUnit;
                viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel3 = viewModelCarInfoUnitInt1 ? AppCompatResources.getDrawable(this.speedometerImageView.getContext(), R.drawable.seven_color_speed_mp_wtach_level) : AppCompatResources.getDrawable(this.speedometerImageView.getContext(), R.drawable.seven_color_speed_km_wtach_level);
                boolean z8 = viewModelCarInfoUnitInt1;
            }
            if ((dirtyFlags & 65664) != 0) {
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
                if ((dirtyFlags & 65664) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet) {
                        dirtyFlags |= 68719476736L;
                    } else {
                        dirtyFlags |= 34359738368L;
                    }
                }
                ObservableField<Boolean> observableField5 = viewModelCarInfoSeatBeltpValue;
                boolean z9 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet;
                viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet ? 8 : 0;
            }
            if ((65792 & dirtyFlags) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoTurnSpeed = viewModelCarInfo.turnSpeed;
                } else {
                    viewModelCarInfoTurnSpeed = null;
                }
                i = 8;
                updateRegistration(8, (Observable) viewModelCarInfoTurnSpeed);
                if (viewModelCarInfoTurnSpeed != null) {
                    viewModelCarInfoTurnSpeedGet = viewModelCarInfoTurnSpeed.get();
                }
                ObservableInt observableInt2 = viewModelCarInfoTurnSpeed;
                stringValueOfViewModelCarInfoTurnSpeed2 = String.valueOf(viewModelCarInfoTurnSpeedGet);
            } else {
                i = 8;
            }
            if ((dirtyFlags & 66048) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoRlDoorState = viewModelCarInfo.rlDoorState;
                } else {
                    viewModelCarInfoRlDoorState = null;
                }
                updateRegistration(9, (Observable) viewModelCarInfoRlDoorState);
                if (viewModelCarInfoRlDoorState != null) {
                    viewModelCarInfoRlDoorStateGet = viewModelCarInfoRlDoorState.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoRlDoorStateGet);
                if ((dirtyFlags & 66048) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet) {
                        dirtyFlags |= 4294967296L;
                    } else {
                        dirtyFlags |= 2147483648L;
                    }
                }
                ObservableField<Boolean> observableField6 = viewModelCarInfoRlDoorState;
                boolean z10 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet;
                viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet ? 0 : 4;
            }
            if ((66560 & dirtyFlags) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoTurnSpeedAnge = viewModelCarInfo.turnSpeedAnge;
                } else {
                    viewModelCarInfoTurnSpeedAnge = null;
                }
                updateRegistration(10, (Observable) viewModelCarInfoTurnSpeedAnge);
                if (viewModelCarInfoTurnSpeedAnge != null) {
                    ObservableFloat observableFloat = viewModelCarInfoTurnSpeedAnge;
                    viewModelCarInfoTurnSpeedAngeGet2 = viewModelCarInfoTurnSpeedAnge.get();
                } else {
                    ObservableFloat observableFloat2 = viewModelCarInfoTurnSpeedAnge;
                }
            }
            if ((dirtyFlags & 67584) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoFlDoorState = viewModelCarInfo.flDoorState;
                } else {
                    viewModelCarInfoFlDoorState = null;
                }
                updateRegistration(11, (Observable) viewModelCarInfoFlDoorState);
                if (viewModelCarInfoFlDoorState != null) {
                    viewModelCarInfoFlDoorStateGet = viewModelCarInfoFlDoorState.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoFlDoorStateGet);
                if ((dirtyFlags & 67584) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet) {
                        dirtyFlags |= 17592186044416L;
                    } else {
                        dirtyFlags |= 8796093022208L;
                    }
                }
                ObservableField<Boolean> observableField7 = viewModelCarInfoFlDoorState;
                boolean z11 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet;
                viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE4 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet ? 0 : 4;
            }
            if ((69632 & dirtyFlags) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoSpeed = viewModelCarInfo.speed;
                } else {
                    viewModelCarInfoSpeed = null;
                }
                updateRegistration(12, (Observable) viewModelCarInfoSpeed);
                if (viewModelCarInfoSpeed != null) {
                    viewModelCarInfoSpeedGet = viewModelCarInfoSpeed.get();
                }
                ObservableInt observableInt3 = viewModelCarInfoSpeed;
                stringValueOfViewModelCarInfoSpeed3 = String.valueOf(viewModelCarInfoSpeedGet);
            }
            if ((dirtyFlags & 73728) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoBDoorState = viewModelCarInfo.bDoorState;
                } else {
                    viewModelCarInfoBDoorState = null;
                }
                updateRegistration(13, (Observable) viewModelCarInfoBDoorState);
                if (viewModelCarInfoBDoorState != null) {
                    viewModelCarInfoBDoorStateGet = viewModelCarInfoBDoorState.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoBDoorStateGet);
                if ((dirtyFlags & 73728) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
                    }
                }
                boolean z12 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet;
                viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet ? 0 : 4;
            } else {
                viewModelCarInfoBDoorState = null;
            }
            if ((dirtyFlags & 81920) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoBrakeValue = viewModelCarInfo.brakeValue;
                } else {
                    viewModelCarInfoBrakeValue = null;
                }
                updateRegistration(14, (Observable) viewModelCarInfoBrakeValue);
                if (viewModelCarInfoBrakeValue != null) {
                    viewModelCarInfoBrakeValueGet = viewModelCarInfoBrakeValue.get();
                }
                int androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet2 = ViewDataBinding.safeUnbox(viewModelCarInfoBrakeValueGet);
                if ((dirtyFlags & 81920) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet2 == true) {
                        dirtyFlags |= 1099511627776L;
                    } else {
                        dirtyFlags |= 549755813888L;
                    }
                }
                CarInfo carInfo = viewModelCarInfo;
                ObservableField<Boolean> observableField8 = viewModelCarInfoBrakeValue;
                viewModelCarInfoSevenmodeInt5MboundView22AndroidDrawableSevenColorCheckboxSelMboundView22AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt5MboundView22AndroidDrawableSevenColorCheckboxSelMboundView22AndroidDrawableSevenColorCheckboxN2;
                viewModelCarInfoSevenmodeGet2 = viewModelCarInfoSevenmodeGet4;
                viewModelCarInfoBrakeValueViewVISIBLEViewGONE = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet2 != 0 ? 0 : i;
                viewModelCarInfoTempStrGet = viewModelCarInfoTempStrGet3;
                viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel = viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel3;
                stringValueOfViewModelCarInfoTurnSpeed = stringValueOfViewModelCarInfoTurnSpeed2;
                viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN5;
                ObservableField<Boolean> observableField9 = viewModelCarInfoBDoorState;
                viewModelCarInfoBrakeValueViewVISIBLEViewGONE2 = viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoSevenmodeGet = viewModelCarInfoSpeedGet;
                viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN2;
                ObservableBoolean observableBoolean = viewModelCarInfoCarImage;
                viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE3;
                stringValueOfViewModelCarInfoSpeed = stringValueOfViewModelCarInfoSpeed3;
                viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE = viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE2;
                viewModelCarInfoTurnSpeedAngeGet = viewModelCarInfoTurnSpeedAngeGet2;
                int viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE4 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet2;
                ObservableInt observableInt4 = viewModelCarInfoSevenmode;
                viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN2;
                viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN2;
                boolean z13 = viewModelCarInfoSevenmodeInt6;
                int i3 = viewModelCarInfoUnitGet2;
                viewModelCarInfoUnitGet = viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN4;
                viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE = 0;
                viewModelShowSevenMenuViewVISIBLEViewGONE = viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE4;
            } else {
                CarInfo carInfo2 = viewModelCarInfo;
                viewModelCarInfoSevenmodeInt5MboundView22AndroidDrawableSevenColorCheckboxSelMboundView22AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt5MboundView22AndroidDrawableSevenColorCheckboxSelMboundView22AndroidDrawableSevenColorCheckboxN2;
                viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN2;
                viewModelCarInfoSevenmodeGet2 = viewModelCarInfoSevenmodeGet4;
                viewModelCarInfoBrakeValueViewVISIBLEViewGONE = 0;
                viewModelCarInfoTempStrGet = viewModelCarInfoTempStrGet3;
                viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel = viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel3;
                stringValueOfViewModelCarInfoTurnSpeed = stringValueOfViewModelCarInfoTurnSpeed2;
                viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN5;
                ObservableField<Boolean> observableField10 = viewModelCarInfoBDoorState;
                boolean z14 = viewModelCarInfoSevenmodeInt6;
                viewModelCarInfoBrakeValueViewVISIBLEViewGONE2 = viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoSevenmodeGet = viewModelCarInfoSpeedGet;
                viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN2;
                viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN4;
                ObservableBoolean observableBoolean2 = viewModelCarInfoCarImage;
                viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE3;
                stringValueOfViewModelCarInfoSpeed = stringValueOfViewModelCarInfoSpeed3;
                viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE = viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE2;
                viewModelCarInfoTurnSpeedAngeGet = viewModelCarInfoTurnSpeedAngeGet2;
                int viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE5 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet;
                ObservableInt observableInt5 = viewModelCarInfoSevenmode;
                viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN2;
                int i4 = viewModelCarInfoUnitGet2;
                viewModelCarInfoUnitGet = viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE = 0;
                viewModelShowSevenMenuViewVISIBLEViewGONE = viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE4;
            }
        } else {
            viewModelShowSevenMenu = null;
            viewModelCarInfoSevenmodeInt5MboundView22AndroidDrawableSevenColorCheckboxSelMboundView22AndroidDrawableSevenColorCheckboxN = null;
            viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN = null;
            viewModelCarInfoSevenmodeGet2 = 0;
            viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN = null;
            viewModelCarInfoBrakeValueViewVISIBLEViewGONE = 0;
            viewModelCarInfoTempStrGet = null;
            viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel = null;
            stringValueOfViewModelCarInfoTurnSpeed = null;
            viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE = 0;
            viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN = null;
            viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE = 0;
            viewModelCarInfoBrakeValueViewVISIBLEViewGONE2 = 0;
            viewModelCarInfoSevenmodeGet = 0;
            viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN = null;
            stringValueOfViewModelCarInfoSpeed = null;
            viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE = 0;
            viewModelCarInfoTurnSpeedAngeGet = 0.0f;
            viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN = null;
            viewModelCarInfoUnitGet = 0;
            viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE = 0;
            viewModelShowSevenMenuViewVISIBLEViewGONE = 0;
        }
        if ((dirtyFlags & 98305) != 0) {
            if (viewModel != null) {
                viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE = viewModelShowSevenMenuViewVISIBLEViewGONE;
                viewModelShowSevenMenu2 = viewModel.showSevenMenu;
            } else {
                viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE = viewModelShowSevenMenuViewVISIBLEViewGONE;
                viewModelShowSevenMenu2 = viewModelShowSevenMenu;
            }
            DashboardViewModel dashboardViewModel = viewModel;
            updateRegistration(0, (Observable) viewModelShowSevenMenu2);
            if (viewModelShowSevenMenu2 != null) {
                viewModelShowSevenMenuGet = viewModelShowSevenMenu2.get();
            }
            if ((dirtyFlags & 98305) != 0) {
                if (viewModelShowSevenMenuGet) {
                    dirtyFlags |= 4194304;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
                }
            }
            viewModelShowSevenMenuViewVISIBLEViewGONE2 = viewModelShowSevenMenuGet ? 0 : 8;
        } else {
            viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE = viewModelShowSevenMenuViewVISIBLEViewGONE;
            viewModelShowSevenMenuViewVISIBLEViewGONE2 = viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE;
            viewModelShowSevenMenu2 = viewModelShowSevenMenu;
        }
        if ((dirtyFlags & 98305) != 0) {
            ObservableBoolean observableBoolean3 = viewModelShowSevenMenu2;
            this.alsMenu.setVisibility(viewModelShowSevenMenuViewVISIBLEViewGONE2);
        }
        if ((dirtyFlags & 65664) != 0) {
            this.batteryImageView.setVisibility(viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 65538) != 0) {
            this.batteryImageView.setImageLevel(viewModelCarInfoSevenmodeGet2);
            this.carImageView.setImageLevel(viewModelCarInfoSevenmodeGet2);
            this.mboundView1.setImageLevel(viewModelCarInfoSevenmodeGet2);
            TextViewBindingAdapter.setDrawableLeft(this.mboundView17, viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN);
            TextViewBindingAdapter.setDrawableLeft(this.mboundView18, viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN);
            TextViewBindingAdapter.setDrawableLeft(this.mboundView19, viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN);
            TextViewBindingAdapter.setDrawableLeft(this.mboundView20, viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN);
            TextViewBindingAdapter.setDrawableLeft(this.mboundView21, viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN);
            TextViewBindingAdapter.setDrawableLeft(this.mboundView22, viewModelCarInfoSevenmodeInt5MboundView22AndroidDrawableSevenColorCheckboxSelMboundView22AndroidDrawableSevenColorCheckboxN);
            TextViewBindingAdapter.setDrawableLeft(this.mboundView23, viewModelCarInfoSevenmodeInt6MboundView23AndroidDrawableSevenColorCheckboxSelMboundView23AndroidDrawableSevenColorCheckboxN2);
            this.oilImageView.setImageLevel(viewModelCarInfoSevenmodeGet2);
            this.speedometerImageView.setImageLevel(viewModelCarInfoSevenmodeGet2);
            this.tachometerImageView.setImageLevel(viewModelCarInfoSevenmodeGet2);
        }
        if ((dirtyFlags & 65540) != 0) {
            this.carImageView.setVisibility(viewModelCarInfoUnitGet);
        }
        if ((dirtyFlags & 73728) != 0) {
            this.dorrBackImageView.setVisibility(viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 67584) != 0) {
            int i5 = viewModelCarInfoSevenmodeGet2;
            viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2 = viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE;
            this.dorrLeftFlImageView.setVisibility(viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2);
        } else {
            viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2 = viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE;
        }
        if ((dirtyFlags & 65544) != 0) {
            int i6 = viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2;
            viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE3 = viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE;
            this.dorrLeftFrImageView.setVisibility(viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE3);
        } else {
            viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE3 = viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE;
        }
        if ((dirtyFlags & 66048) != 0) {
            int i7 = viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE3;
            viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoBrakeValueViewVISIBLEViewGONE2;
            this.dorrLeftRlImageView.setVisibility(viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE);
        } else {
            int viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE3 = viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE3;
            viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoBrakeValueViewVISIBLEViewGONE2;
        }
        if ((dirtyFlags & 65552) != 0) {
            int i8 = viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE;
            viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE2 = viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE;
            this.imageView19.setVisibility(viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE2);
        } else {
            viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE2 = viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE;
        }
        if ((dirtyFlags & PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) != 0) {
            int i9 = viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE2;
            this.mboundView17.setOnClickListener(this.mCallback231);
            this.mboundView18.setOnClickListener(this.mCallback232);
            this.mboundView19.setOnClickListener(this.mCallback233);
            this.mboundView20.setOnClickListener(this.mCallback234);
            this.mboundView21.setOnClickListener(this.mCallback235);
            this.mboundView22.setOnClickListener(this.mCallback236);
            this.mboundView23.setOnClickListener(this.mCallback237);
        }
        if ((dirtyFlags & 69632) != 0) {
            DashboardViewModel.setSevenSpeedRotation(this.mboundView3, viewModelCarInfoSevenmodeGet);
            int i10 = viewModelShowSevenMenuViewVISIBLEViewGONE2;
            stringValueOfViewModelCarInfoSpeed2 = stringValueOfViewModelCarInfoSpeed;
            TextViewBindingAdapter.setText(this.speedPointerTextView, stringValueOfViewModelCarInfoSpeed2);
        } else {
            int viewModelCarInfoSpeedGet2 = viewModelCarInfoSevenmodeGet;
            int viewModelCarInfoSpeedGet3 = viewModelShowSevenMenuViewVISIBLEViewGONE2;
            stringValueOfViewModelCarInfoSpeed2 = stringValueOfViewModelCarInfoSpeed;
        }
        if ((dirtyFlags & 81920) != 0) {
            String str = stringValueOfViewModelCarInfoSpeed2;
            viewModelCarInfoBrakeValueViewVISIBLEViewGONE3 = viewModelCarInfoBrakeValueViewVISIBLEViewGONE;
            this.oilImageView.setVisibility(viewModelCarInfoBrakeValueViewVISIBLEViewGONE3);
        } else {
            viewModelCarInfoBrakeValueViewVISIBLEViewGONE3 = viewModelCarInfoBrakeValueViewVISIBLEViewGONE;
        }
        if ((dirtyFlags & 65600) != 0) {
            int i11 = viewModelCarInfoBrakeValueViewVISIBLEViewGONE3;
            viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel2 = viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel;
            ImageViewBindingAdapter.setImageDrawable(this.speedometerImageView, viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel2);
        } else {
            viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel2 = viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel;
        }
        if ((dirtyFlags & 65568) != 0) {
            Drawable drawable6 = viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel2;
            viewModelCarInfoTempStrGet2 = viewModelCarInfoTempStrGet;
            TextViewBindingAdapter.setText(this.textView18, viewModelCarInfoTempStrGet2);
        } else {
            viewModelCarInfoTempStrGet2 = viewModelCarInfoTempStrGet;
        }
        if ((dirtyFlags & 66560) != 0) {
            String str2 = viewModelCarInfoTempStrGet2;
            DashboardViewModel.setALSRotation(this.zspeedPointerImageView, viewModelCarInfoTurnSpeedAngeGet);
        } else {
            float f = viewModelCarInfoTurnSpeedAngeGet;
        }
        if ((dirtyFlags & 65792) != 0) {
            long j = dirtyFlags;
            TextViewBindingAdapter.setText(this.zspeedPointerTextView, stringValueOfViewModelCarInfoTurnSpeed);
            return;
        }
        String str3 = stringValueOfViewModelCarInfoTurnSpeed;
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean viewModelJavaLangObjectNull = true;
        boolean viewModelJavaLangObjectNull2 = false;
        switch (sourceId) {
            case 1:
                DashboardViewModel viewModel = this.mViewModel;
                if (viewModel == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel.onSevenModeClick(0);
                    return;
                }
                return;
            case 2:
                DashboardViewModel viewModel2 = this.mViewModel;
                if (viewModel2 != null) {
                    viewModelJavaLangObjectNull2 = true;
                }
                if (viewModelJavaLangObjectNull2) {
                    viewModel2.onSevenModeClick(1);
                    return;
                }
                return;
            case 3:
                DashboardViewModel viewModel3 = this.mViewModel;
                if (viewModel3 == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel3.onSevenModeClick(2);
                    return;
                }
                return;
            case 4:
                DashboardViewModel viewModel4 = this.mViewModel;
                if (viewModel4 == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel4.onSevenModeClick(3);
                    return;
                }
                return;
            case 5:
                DashboardViewModel viewModel5 = this.mViewModel;
                if (viewModel5 == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel5.onSevenModeClick(4);
                    return;
                }
                return;
            case 6:
                DashboardViewModel viewModel6 = this.mViewModel;
                if (viewModel6 == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel6.onSevenModeClick(5);
                    return;
                }
                return;
            case 7:
                DashboardViewModel viewModel7 = this.mViewModel;
                if (viewModel7 == null) {
                    viewModelJavaLangObjectNull = false;
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
