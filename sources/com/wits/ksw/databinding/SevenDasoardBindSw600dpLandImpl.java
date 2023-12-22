package com.wits.ksw.databinding;

import android.content.Context;
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
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.DashboardViewModel;
import com.wits.ksw.launcher.view.ID7SpeedImageView;

/* loaded from: classes7.dex */
public class SevenDasoardBindSw600dpLandImpl extends SevenDasoardBind implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback251;
    private final View.OnClickListener mCallback252;
    private final View.OnClickListener mCallback253;
    private final View.OnClickListener mCallback254;
    private final View.OnClickListener mCallback255;
    private final View.OnClickListener mCallback256;
    private final View.OnClickListener mCallback257;
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
        sparseIntArray.put(C0899R.C0901id.alsRadioGroup, 25);
        sparseIntArray.put(C0899R.C0901id.time, 26);
    }

    public SevenDasoardBindSw600dpLandImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 27, sIncludes, sViewsWithIds));
    }

    private SevenDasoardBindSw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 15, (ConstraintLayout) bindings[16], (LinearLayout) bindings[25], (ID7SpeedImageView) bindings[15], (ImageView) bindings[8], (ImageView) bindings[13], (ImageView) bindings[9], (ImageView) bindings[10], (ImageView) bindings[11], (ImageView) bindings[12], (ConstraintLayout) bindings[0], (ID7SpeedImageView) bindings[14], (TextView) bindings[4], (ID7SpeedImageView) bindings[2], (ID7SpeedImageView) bindings[5], (TextView) bindings[24], (TextClock) bindings[26], (ImageView) bindings[6], (TextView) bindings[7]);
        this.mDirtyFlags = -1L;
        this.alsMenu.setTag(null);
        this.batteryImageView.setTag(null);
        this.carImageView.setTag(null);
        this.dorrBackImageView.setTag(null);
        this.dorrLeftFlImageView.setTag(null);
        this.dorrLeftFrImageView.setTag(null);
        this.dorrLeftRlImageView.setTag(null);
        this.imageView19.setTag(null);
        this.linearLayout2.setTag(null);
        ID7SpeedImageView iD7SpeedImageView = (ID7SpeedImageView) bindings[1];
        this.mboundView1 = iD7SpeedImageView;
        iD7SpeedImageView.setTag(null);
        TextView textView = (TextView) bindings[17];
        this.mboundView17 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[18];
        this.mboundView18 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[19];
        this.mboundView19 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[20];
        this.mboundView20 = textView4;
        textView4.setTag(null);
        TextView textView5 = (TextView) bindings[21];
        this.mboundView21 = textView5;
        textView5.setTag(null);
        TextView textView6 = (TextView) bindings[22];
        this.mboundView22 = textView6;
        textView6.setTag(null);
        TextView textView7 = (TextView) bindings[23];
        this.mboundView23 = textView7;
        textView7.setTag(null);
        ImageView imageView = (ImageView) bindings[3];
        this.mboundView3 = imageView;
        imageView.setTag(null);
        this.oilImageView.setTag(null);
        this.speedPointerTextView.setTag(null);
        this.speedometerImageView.setTag(null);
        this.tachometerImageView.setTag(null);
        this.textView18.setTag(null);
        this.zspeedPointerImageView.setTag(null);
        this.zspeedPointerTextView.setTag(null);
        setRootTag(root);
        this.mCallback257 = new OnClickListener(this, 7);
        this.mCallback255 = new OnClickListener(this, 5);
        this.mCallback256 = new OnClickListener(this, 6);
        this.mCallback253 = new OnClickListener(this, 3);
        this.mCallback254 = new OnClickListener(this, 4);
        this.mCallback251 = new OnClickListener(this, 1);
        this.mCallback252 = new OnClickListener(this, 2);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
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

    @Override // com.wits.ksw.databinding.SevenDasoardBind
    public void setViewModel(DashboardViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
        }
        notifyPropertyChanged(25);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
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
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoSevenmode(ObservableInt ViewModelCarInfoSevenmode, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoCarImage(ObservableBoolean ViewModelCarInfoCarImage, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoFrDoorState(ObservableField<Boolean> ViewModelCarInfoFrDoorState, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoRrDoorState(ObservableField<Boolean> ViewModelCarInfoRrDoorState, int fieldId) {
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

    private boolean onChangeViewModelCarInfoSeatBeltpValue(ObservableField<Boolean> ViewModelCarInfoSeatBeltpValue, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoTurnSpeed(ObservableInt ViewModelCarInfoTurnSpeed, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoRlDoorState(ObservableField<Boolean> ViewModelCarInfoRlDoorState, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoTurnSpeedAnge(ObservableFloat ViewModelCarInfoTurnSpeedAnge, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoFlDoorState(ObservableField<Boolean> ViewModelCarInfoFlDoorState, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoSpeed(ObservableInt ViewModelCarInfoSpeed, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoBDoorState(ObservableField<Boolean> ViewModelCarInfoBDoorState, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoBrakeValue(ObservableField<Boolean> ViewModelCarInfoBrakeValue, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
            }
            return true;
        }
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        ObservableBoolean viewModelShowSevenMenu;
        Drawable viewModelCarInfoSevenmodeInt5MboundView22AndroidDrawableSevenColorCheckboxSelMboundView22AndroidDrawableSevenColorCheckboxN;
        Drawable viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN;
        int viewModelCarInfoSevenmodeGet;
        Drawable viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN;
        int viewModelCarInfoBrakeValueViewVISIBLEViewGONE;
        String viewModelCarInfoTempStrGet;
        Drawable viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel;
        String stringValueOfViewModelCarInfoTurnSpeed;
        int viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE;
        Drawable viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN;
        int viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE;
        int viewModelCarInfoBrakeValueViewVISIBLEViewGONE2;
        int viewModelCarInfoSevenmodeGet2;
        Drawable viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN;
        String stringValueOfViewModelCarInfoSpeed;
        int viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE;
        float viewModelCarInfoTurnSpeedAngeGet;
        Drawable viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN;
        int viewModelCarInfoUnitGet;
        int viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE;
        int viewModelShowSevenMenuViewVISIBLEViewGONE;
        int viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE;
        int viewModelShowSevenMenuViewVISIBLEViewGONE2;
        ObservableBoolean viewModelShowSevenMenu2;
        int viewModelCarInfoSevenmodeGet3;
        int viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2;
        int viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2;
        int viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE;
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
        int viewModelCarInfoSevenmodeGet4;
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
            this.mDirtyFlags = 0L;
        }
        Drawable viewModelCarInfoSevenmodeInt6MboundView23AndroidDrawableSevenColorCheckboxSelMboundView23AndroidDrawableSevenColorCheckboxN2 = null;
        ObservableInt viewModelCarInfoSevenmode = null;
        boolean viewModelCarInfoSevenmodeInt6 = false;
        int viewModelCarInfoUnitGet2 = 0;
        int viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE = 0;
        ObservableBoolean viewModelCarInfoCarImage = null;
        Boolean viewModelCarInfoRlDoorStateGet = null;
        Drawable viewModelCarInfoSevenmodeInt5MboundView22AndroidDrawableSevenColorCheckboxSelMboundView22AndroidDrawableSevenColorCheckboxN2 = null;
        int viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE2 = 0;
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
        int viewModelCarInfoSevenmodeGet5 = 0;
        Boolean viewModelCarInfoRrDoorStateGet = null;
        Drawable viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN2 = null;
        int viewModelCarInfoTurnSpeedGet = 0;
        Boolean viewModelCarInfoFlDoorStateGet = null;
        String viewModelCarInfoTempStrGet3 = null;
        Drawable viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel3 = null;
        Boolean viewModelCarInfoBrakeValueGet = null;
        int viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE3 = 0;
        String stringValueOfViewModelCarInfoTurnSpeed2 = null;
        int viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE3 = 0;
        Drawable viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN4 = null;
        DashboardViewModel viewModel = this.mViewModel;
        Drawable viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN5 = null;
        float viewModelCarInfoTurnSpeedAngeGet2 = 0.0f;
        if ((dirtyFlags & 98302) == 0) {
            viewModelShowSevenMenu = null;
            viewModelCarInfoSevenmodeInt5MboundView22AndroidDrawableSevenColorCheckboxSelMboundView22AndroidDrawableSevenColorCheckboxN = null;
            viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN = null;
            viewModelCarInfoSevenmodeGet = 0;
            viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN = null;
            viewModelCarInfoBrakeValueViewVISIBLEViewGONE = 0;
            viewModelCarInfoTempStrGet = null;
            viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel = null;
            stringValueOfViewModelCarInfoTurnSpeed = null;
            viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE = 0;
            viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN = null;
            viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE = 0;
            viewModelCarInfoBrakeValueViewVISIBLEViewGONE2 = 0;
            viewModelCarInfoSevenmodeGet2 = 0;
            viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN = null;
            stringValueOfViewModelCarInfoSpeed = null;
            viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE = 0;
            viewModelCarInfoTurnSpeedAngeGet = 0.0f;
            viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN = null;
            viewModelCarInfoUnitGet = 0;
            viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE = 0;
            viewModelShowSevenMenuViewVISIBLEViewGONE = 0;
        } else {
            com.wits.ksw.launcher.bean.CarInfo viewModelCarInfo = DashboardViewModel.carInfo;
            if ((dirtyFlags & 65538) == 0) {
                viewModelShowSevenMenu = null;
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet = false;
            } else {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoSevenmode = viewModelCarInfo.sevenmode;
                }
                updateRegistration(1, viewModelCarInfoSevenmode);
                if (viewModelCarInfoSevenmode == null) {
                    viewModelCarInfoSevenmodeGet4 = 0;
                } else {
                    int viewModelCarInfoSevenmodeGet6 = viewModelCarInfoSevenmode.get();
                    viewModelCarInfoSevenmodeGet4 = viewModelCarInfoSevenmodeGet6;
                }
                boolean viewModelCarInfoSevenmodeInt4 = viewModelCarInfoSevenmodeGet4 == 4;
                boolean viewModelCarInfoSevenmodeInt62 = viewModelCarInfoSevenmodeGet4 == 6;
                boolean viewModelCarInfoSevenmodeInt1 = viewModelCarInfoSevenmodeGet4 == 1;
                boolean viewModelCarInfoSevenmodeInt0 = viewModelCarInfoSevenmodeGet4 == 0;
                viewModelShowSevenMenu = null;
                boolean viewModelCarInfoSevenmodeInt3 = viewModelCarInfoSevenmodeGet4 == 3;
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet = false;
                boolean viewModelCarInfoSevenmodeInt2 = viewModelCarInfoSevenmodeGet4 == 2;
                ObservableInt viewModelCarInfoSevenmode2 = viewModelCarInfoSevenmode;
                boolean viewModelCarInfoSevenmodeInt5 = viewModelCarInfoSevenmodeGet4 == 5;
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
                int i2 = C0899R.C0900drawable.seven_color_checkbox_sel;
                Context context = this.mboundView21.getContext();
                if (!viewModelCarInfoSevenmodeInt4) {
                    i2 = C0899R.C0900drawable.seven_color_checkbox_n;
                }
                Drawable viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN3 = AppCompatResources.getDrawable(context, i2);
                if (viewModelCarInfoSevenmodeInt62) {
                    viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN2 = viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN3;
                    drawable = AppCompatResources.getDrawable(this.mboundView23.getContext(), C0899R.C0900drawable.seven_color_checkbox_sel);
                } else {
                    viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN2 = viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN3;
                    drawable = AppCompatResources.getDrawable(this.mboundView23.getContext(), C0899R.C0900drawable.seven_color_checkbox_n);
                }
                Drawable viewModelCarInfoSevenmodeInt6MboundView23AndroidDrawableSevenColorCheckboxSelMboundView23AndroidDrawableSevenColorCheckboxN3 = drawable;
                if (viewModelCarInfoSevenmodeInt1) {
                    viewModelCarInfoSevenmodeInt6MboundView23AndroidDrawableSevenColorCheckboxSelMboundView23AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt6MboundView23AndroidDrawableSevenColorCheckboxSelMboundView23AndroidDrawableSevenColorCheckboxN3;
                    drawable2 = AppCompatResources.getDrawable(this.mboundView18.getContext(), C0899R.C0900drawable.seven_color_checkbox_sel);
                } else {
                    viewModelCarInfoSevenmodeInt6MboundView23AndroidDrawableSevenColorCheckboxSelMboundView23AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt6MboundView23AndroidDrawableSevenColorCheckboxSelMboundView23AndroidDrawableSevenColorCheckboxN3;
                    drawable2 = AppCompatResources.getDrawable(this.mboundView18.getContext(), C0899R.C0900drawable.seven_color_checkbox_n);
                }
                Drawable viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN3 = drawable2;
                if (viewModelCarInfoSevenmodeInt0) {
                    viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN2 = viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN3;
                    drawable3 = AppCompatResources.getDrawable(this.mboundView17.getContext(), C0899R.C0900drawable.seven_color_checkbox_sel);
                } else {
                    viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN2 = viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN3;
                    drawable3 = AppCompatResources.getDrawable(this.mboundView17.getContext(), C0899R.C0900drawable.seven_color_checkbox_n);
                }
                Drawable viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN3 = drawable3;
                if (viewModelCarInfoSevenmodeInt3) {
                    viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN2 = viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN3;
                    drawable4 = AppCompatResources.getDrawable(this.mboundView20.getContext(), C0899R.C0900drawable.seven_color_checkbox_sel);
                } else {
                    viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN2 = viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN3;
                    drawable4 = AppCompatResources.getDrawable(this.mboundView20.getContext(), C0899R.C0900drawable.seven_color_checkbox_n);
                }
                Drawable viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN3 = drawable4;
                if (viewModelCarInfoSevenmodeInt2) {
                    viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN2 = viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN3;
                    drawable5 = AppCompatResources.getDrawable(this.mboundView19.getContext(), C0899R.C0900drawable.seven_color_checkbox_sel);
                } else {
                    viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN2 = viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN3;
                    drawable5 = AppCompatResources.getDrawable(this.mboundView19.getContext(), C0899R.C0900drawable.seven_color_checkbox_n);
                }
                Drawable viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN6 = drawable5;
                if (viewModelCarInfoSevenmodeInt5) {
                    viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN2 = viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN6;
                    viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN3 = AppCompatResources.getDrawable(this.mboundView22.getContext(), C0899R.C0900drawable.seven_color_checkbox_sel);
                } else {
                    viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN2 = viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN6;
                    viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN3 = AppCompatResources.getDrawable(this.mboundView22.getContext(), C0899R.C0900drawable.seven_color_checkbox_n);
                }
                viewModelCarInfoSevenmodeInt5MboundView22AndroidDrawableSevenColorCheckboxSelMboundView22AndroidDrawableSevenColorCheckboxN2 = viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN3;
                dirtyFlags = dirtyFlags2;
                viewModelCarInfoSevenmodeInt6MboundView23AndroidDrawableSevenColorCheckboxSelMboundView23AndroidDrawableSevenColorCheckboxN2 = viewModelCarInfoSevenmodeInt6MboundView23AndroidDrawableSevenColorCheckboxSelMboundView23AndroidDrawableSevenColorCheckboxN;
                viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN5 = viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN2;
                viewModelCarInfoSevenmodeGet5 = viewModelCarInfoSevenmodeGet4;
                viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN4 = viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN2;
                viewModelCarInfoSevenmodeInt6 = viewModelCarInfoSevenmodeInt4;
                viewModelCarInfoSevenmode = viewModelCarInfoSevenmode2;
            }
            if ((dirtyFlags & 65540) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoCarImage = viewModelCarInfo.carImage;
                }
                updateRegistration(2, viewModelCarInfoCarImage);
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
                if (viewModelCarInfo == null) {
                    viewModelCarInfoFrDoorState = null;
                } else {
                    viewModelCarInfoFrDoorState = viewModelCarInfo.frDoorState;
                }
                updateRegistration(3, viewModelCarInfoFrDoorState);
                if (viewModelCarInfoFrDoorState != null) {
                    Boolean viewModelCarInfoFrDoorStateGet2 = viewModelCarInfoFrDoorState.get();
                    viewModelCarInfoFrDoorStateGet = viewModelCarInfoFrDoorStateGet2;
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFrDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoFrDoorStateGet);
                if ((dirtyFlags & 65544) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFrDoorStateGet) {
                        dirtyFlags |= 70368744177664L;
                    } else {
                        dirtyFlags |= 35184372088832L;
                    }
                }
                viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE3 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFrDoorStateGet ? 0 : 4;
            }
            if ((dirtyFlags & 65552) != 0) {
                if (viewModelCarInfo == null) {
                    viewModelCarInfoRrDoorState = null;
                } else {
                    viewModelCarInfoRrDoorState = viewModelCarInfo.rrDoorState;
                }
                updateRegistration(4, viewModelCarInfoRrDoorState);
                if (viewModelCarInfoRrDoorState != null) {
                    Boolean viewModelCarInfoRrDoorStateGet2 = viewModelCarInfoRrDoorState.get();
                    viewModelCarInfoRrDoorStateGet = viewModelCarInfoRrDoorStateGet2;
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoRrDoorStateGet);
                if ((dirtyFlags & 65552) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet) {
                        dirtyFlags |= 67108864;
                    } else {
                        dirtyFlags |= 33554432;
                    }
                }
                viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet ? 0 : 4;
            }
            if ((dirtyFlags & 65568) != 0) {
                if (viewModelCarInfo == null) {
                    viewModelCarInfoTempStr = null;
                } else {
                    viewModelCarInfoTempStr = viewModelCarInfo.tempStr;
                }
                updateRegistration(5, viewModelCarInfoTempStr);
                if (viewModelCarInfoTempStr != null) {
                    viewModelCarInfoTempStrGet3 = viewModelCarInfoTempStr.get();
                }
            }
            if ((dirtyFlags & 65600) != 0) {
                if (viewModelCarInfo == null) {
                    viewModelCarInfoUnit = null;
                } else {
                    viewModelCarInfoUnit = viewModelCarInfo.unit;
                }
                updateRegistration(6, viewModelCarInfoUnit);
                if (viewModelCarInfoUnit != null) {
                    viewModelCarInfoUnitGet2 = viewModelCarInfoUnit.get();
                }
                boolean viewModelCarInfoUnitInt1 = viewModelCarInfoUnitGet2 == 1;
                if ((dirtyFlags & 65600) != 0) {
                    if (viewModelCarInfoUnitInt1) {
                        dirtyFlags |= 4398046511104L;
                    } else {
                        dirtyFlags |= 2199023255552L;
                    }
                }
                viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel3 = viewModelCarInfoUnitInt1 ? AppCompatResources.getDrawable(this.speedometerImageView.getContext(), C0899R.C0900drawable.seven_color_speed_mp_wtach_level) : AppCompatResources.getDrawable(this.speedometerImageView.getContext(), C0899R.C0900drawable.seven_color_speed_km_wtach_level);
            }
            if ((dirtyFlags & 65664) != 0) {
                if (viewModelCarInfo == null) {
                    viewModelCarInfoSeatBeltpValue = null;
                } else {
                    viewModelCarInfoSeatBeltpValue = viewModelCarInfo.seatBeltpValue;
                }
                updateRegistration(7, viewModelCarInfoSeatBeltpValue);
                if (viewModelCarInfoSeatBeltpValue != null) {
                    Boolean viewModelCarInfoSeatBeltpValueGet2 = viewModelCarInfoSeatBeltpValue.get();
                    viewModelCarInfoSeatBeltpValueGet = viewModelCarInfoSeatBeltpValueGet2;
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet = ViewDataBinding.safeUnbox(viewModelCarInfoSeatBeltpValueGet);
                if ((dirtyFlags & 65664) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet) {
                        dirtyFlags |= 68719476736L;
                    } else {
                        dirtyFlags |= 34359738368L;
                    }
                }
                viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet ? 8 : 0;
            }
            if ((65792 & dirtyFlags) == 0) {
                i = 8;
            } else {
                if (viewModelCarInfo == null) {
                    viewModelCarInfoTurnSpeed = null;
                } else {
                    viewModelCarInfoTurnSpeed = viewModelCarInfo.turnSpeed;
                }
                i = 8;
                updateRegistration(8, viewModelCarInfoTurnSpeed);
                if (viewModelCarInfoTurnSpeed != null) {
                    viewModelCarInfoTurnSpeedGet = viewModelCarInfoTurnSpeed.get();
                }
                stringValueOfViewModelCarInfoTurnSpeed2 = String.valueOf(viewModelCarInfoTurnSpeedGet);
            }
            if ((dirtyFlags & 66048) != 0) {
                if (viewModelCarInfo == null) {
                    viewModelCarInfoRlDoorState = null;
                } else {
                    viewModelCarInfoRlDoorState = viewModelCarInfo.rlDoorState;
                }
                updateRegistration(9, viewModelCarInfoRlDoorState);
                if (viewModelCarInfoRlDoorState != null) {
                    Boolean viewModelCarInfoRlDoorStateGet2 = viewModelCarInfoRlDoorState.get();
                    viewModelCarInfoRlDoorStateGet = viewModelCarInfoRlDoorStateGet2;
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoRlDoorStateGet);
                if ((dirtyFlags & 66048) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet) {
                        dirtyFlags |= 4294967296L;
                    } else {
                        dirtyFlags |= 2147483648L;
                    }
                }
                viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet ? 0 : 4;
            }
            if ((66560 & dirtyFlags) != 0) {
                if (viewModelCarInfo == null) {
                    viewModelCarInfoTurnSpeedAnge = null;
                } else {
                    viewModelCarInfoTurnSpeedAnge = viewModelCarInfo.turnSpeedAnge;
                }
                updateRegistration(10, viewModelCarInfoTurnSpeedAnge);
                if (viewModelCarInfoTurnSpeedAnge != null) {
                    viewModelCarInfoTurnSpeedAngeGet2 = viewModelCarInfoTurnSpeedAnge.get();
                }
            }
            if ((dirtyFlags & 67584) != 0) {
                if (viewModelCarInfo == null) {
                    viewModelCarInfoFlDoorState = null;
                } else {
                    viewModelCarInfoFlDoorState = viewModelCarInfo.flDoorState;
                }
                updateRegistration(11, viewModelCarInfoFlDoorState);
                if (viewModelCarInfoFlDoorState != null) {
                    Boolean viewModelCarInfoFlDoorStateGet2 = viewModelCarInfoFlDoorState.get();
                    viewModelCarInfoFlDoorStateGet = viewModelCarInfoFlDoorStateGet2;
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoFlDoorStateGet);
                if ((dirtyFlags & 67584) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet) {
                        dirtyFlags |= 17592186044416L;
                    } else {
                        dirtyFlags |= 8796093022208L;
                    }
                }
                viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE3 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet ? 0 : 4;
            }
            if ((69632 & dirtyFlags) != 0) {
                if (viewModelCarInfo == null) {
                    viewModelCarInfoSpeed = null;
                } else {
                    viewModelCarInfoSpeed = viewModelCarInfo.speed;
                }
                updateRegistration(12, viewModelCarInfoSpeed);
                if (viewModelCarInfoSpeed != null) {
                    viewModelCarInfoSpeedGet = viewModelCarInfoSpeed.get();
                }
                stringValueOfViewModelCarInfoSpeed3 = String.valueOf(viewModelCarInfoSpeedGet);
            }
            if ((dirtyFlags & 73728) == 0) {
                viewModelCarInfoBDoorState = null;
            } else {
                if (viewModelCarInfo == null) {
                    viewModelCarInfoBDoorState = null;
                } else {
                    viewModelCarInfoBDoorState = viewModelCarInfo.bDoorState;
                }
                updateRegistration(13, viewModelCarInfoBDoorState);
                if (viewModelCarInfoBDoorState != null) {
                    Boolean viewModelCarInfoBDoorStateGet2 = viewModelCarInfoBDoorState.get();
                    viewModelCarInfoBDoorStateGet = viewModelCarInfoBDoorStateGet2;
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoBDoorStateGet);
                if ((dirtyFlags & 73728) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
                    }
                }
                viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet ? 0 : 4;
            }
            if ((dirtyFlags & 81920) == 0) {
                viewModelCarInfoSevenmodeInt5MboundView22AndroidDrawableSevenColorCheckboxSelMboundView22AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt5MboundView22AndroidDrawableSevenColorCheckboxSelMboundView22AndroidDrawableSevenColorCheckboxN2;
                viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN2;
                viewModelCarInfoSevenmodeGet = viewModelCarInfoSevenmodeGet5;
                viewModelCarInfoBrakeValueViewVISIBLEViewGONE = 0;
                viewModelCarInfoTempStrGet = viewModelCarInfoTempStrGet3;
                viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel = viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel3;
                stringValueOfViewModelCarInfoTurnSpeed = stringValueOfViewModelCarInfoTurnSpeed2;
                viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE3;
                viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN5;
                viewModelCarInfoBrakeValueViewVISIBLEViewGONE2 = viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoSevenmodeGet2 = viewModelCarInfoSpeedGet;
                viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN2;
                viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN4;
                viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE2;
                stringValueOfViewModelCarInfoSpeed = stringValueOfViewModelCarInfoSpeed3;
                viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE = viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE2;
                viewModelCarInfoTurnSpeedAngeGet = viewModelCarInfoTurnSpeedAngeGet2;
                viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN2;
                viewModelCarInfoUnitGet = viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE = 0;
                viewModelShowSevenMenuViewVISIBLEViewGONE = viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE3;
            } else {
                if (viewModelCarInfo == null) {
                    viewModelCarInfoBrakeValue = null;
                } else {
                    viewModelCarInfoBrakeValue = viewModelCarInfo.brakeValue;
                }
                updateRegistration(14, viewModelCarInfoBrakeValue);
                if (viewModelCarInfoBrakeValue != null) {
                    viewModelCarInfoBrakeValueGet = viewModelCarInfoBrakeValue.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet2 = ViewDataBinding.safeUnbox(viewModelCarInfoBrakeValueGet);
                if ((dirtyFlags & 81920) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet2) {
                        dirtyFlags |= 1099511627776L;
                    } else {
                        dirtyFlags |= 549755813888L;
                    }
                }
                int viewModelCarInfoBrakeValueViewVISIBLEViewGONE4 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet2 ? 0 : i;
                viewModelCarInfoSevenmodeInt5MboundView22AndroidDrawableSevenColorCheckboxSelMboundView22AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt5MboundView22AndroidDrawableSevenColorCheckboxSelMboundView22AndroidDrawableSevenColorCheckboxN2;
                viewModelCarInfoSevenmodeGet = viewModelCarInfoSevenmodeGet5;
                viewModelCarInfoBrakeValueViewVISIBLEViewGONE = viewModelCarInfoBrakeValueViewVISIBLEViewGONE4;
                viewModelCarInfoTempStrGet = viewModelCarInfoTempStrGet3;
                viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel = viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel3;
                stringValueOfViewModelCarInfoTurnSpeed = stringValueOfViewModelCarInfoTurnSpeed2;
                viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE3;
                viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN5;
                viewModelCarInfoBrakeValueViewVISIBLEViewGONE2 = viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoSevenmodeGet2 = viewModelCarInfoSpeedGet;
                viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN2;
                viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE2;
                stringValueOfViewModelCarInfoSpeed = stringValueOfViewModelCarInfoSpeed3;
                viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE = viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE2;
                viewModelCarInfoTurnSpeedAngeGet = viewModelCarInfoTurnSpeedAngeGet2;
                viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN2;
                viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN2;
                viewModelCarInfoUnitGet = viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN = viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN4;
                viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE = 0;
                viewModelShowSevenMenuViewVISIBLEViewGONE = viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE3;
            }
        }
        if ((dirtyFlags & 98305) != 0) {
            if (viewModel == null) {
                viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE = viewModelShowSevenMenuViewVISIBLEViewGONE;
                viewModelShowSevenMenu2 = viewModelShowSevenMenu;
            } else {
                viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE = viewModelShowSevenMenuViewVISIBLEViewGONE;
                viewModelShowSevenMenu2 = viewModel.showSevenMenu;
            }
            updateRegistration(0, viewModelShowSevenMenu2);
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
            this.alsMenu.setVisibility(viewModelShowSevenMenuViewVISIBLEViewGONE2);
        }
        if ((dirtyFlags & 65664) != 0) {
            this.batteryImageView.setVisibility(viewModelCarInfoSeatBeltpValueViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 65538) != 0) {
            this.batteryImageView.setImageLevel(viewModelCarInfoSevenmodeGet);
            this.carImageView.setImageLevel(viewModelCarInfoSevenmodeGet);
            this.mboundView1.setImageLevel(viewModelCarInfoSevenmodeGet);
            TextViewBindingAdapter.setDrawableLeft(this.mboundView17, viewModelCarInfoSevenmodeInt0MboundView17AndroidDrawableSevenColorCheckboxSelMboundView17AndroidDrawableSevenColorCheckboxN);
            TextViewBindingAdapter.setDrawableLeft(this.mboundView18, viewModelCarInfoSevenmodeInt1MboundView18AndroidDrawableSevenColorCheckboxSelMboundView18AndroidDrawableSevenColorCheckboxN);
            TextViewBindingAdapter.setDrawableLeft(this.mboundView19, viewModelCarInfoSevenmodeInt2MboundView19AndroidDrawableSevenColorCheckboxSelMboundView19AndroidDrawableSevenColorCheckboxN);
            TextViewBindingAdapter.setDrawableLeft(this.mboundView20, viewModelCarInfoSevenmodeInt3MboundView20AndroidDrawableSevenColorCheckboxSelMboundView20AndroidDrawableSevenColorCheckboxN);
            TextViewBindingAdapter.setDrawableLeft(this.mboundView21, viewModelCarInfoSevenmodeInt4MboundView21AndroidDrawableSevenColorCheckboxSelMboundView21AndroidDrawableSevenColorCheckboxN);
            TextViewBindingAdapter.setDrawableLeft(this.mboundView22, viewModelCarInfoSevenmodeInt5MboundView22AndroidDrawableSevenColorCheckboxSelMboundView22AndroidDrawableSevenColorCheckboxN);
            TextViewBindingAdapter.setDrawableLeft(this.mboundView23, viewModelCarInfoSevenmodeInt6MboundView23AndroidDrawableSevenColorCheckboxSelMboundView23AndroidDrawableSevenColorCheckboxN2);
            this.oilImageView.setImageLevel(viewModelCarInfoSevenmodeGet);
            this.speedometerImageView.setImageLevel(viewModelCarInfoSevenmodeGet);
            this.tachometerImageView.setImageLevel(viewModelCarInfoSevenmodeGet);
        }
        if ((dirtyFlags & 65540) != 0) {
            this.carImageView.setVisibility(viewModelCarInfoUnitGet);
        }
        if ((dirtyFlags & 73728) != 0) {
            this.dorrBackImageView.setVisibility(viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 67584) != 0) {
            viewModelCarInfoSevenmodeGet3 = viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE;
            this.dorrLeftFlImageView.setVisibility(viewModelCarInfoSevenmodeGet3);
        } else {
            viewModelCarInfoSevenmodeGet3 = viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE;
        }
        if ((dirtyFlags & 65544) != 0) {
            viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2 = viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE;
            this.dorrLeftFrImageView.setVisibility(viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2);
        } else {
            viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2 = viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE;
        }
        if ((dirtyFlags & 66048) != 0) {
            viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2 = viewModelCarInfoBrakeValueViewVISIBLEViewGONE2;
            this.dorrLeftRlImageView.setVisibility(viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2);
        } else {
            viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2 = viewModelCarInfoBrakeValueViewVISIBLEViewGONE2;
        }
        if ((dirtyFlags & 65552) != 0) {
            viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE;
            this.imageView19.setVisibility(viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE);
        } else {
            viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE;
        }
        if ((dirtyFlags & PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) != 0) {
            this.mboundView17.setOnClickListener(this.mCallback251);
            this.mboundView18.setOnClickListener(this.mCallback252);
            this.mboundView19.setOnClickListener(this.mCallback253);
            this.mboundView20.setOnClickListener(this.mCallback254);
            this.mboundView21.setOnClickListener(this.mCallback255);
            this.mboundView22.setOnClickListener(this.mCallback256);
            this.mboundView23.setOnClickListener(this.mCallback257);
        }
        if ((dirtyFlags & 69632) != 0) {
            DashboardViewModel.setSevenSpeedRotation(this.mboundView3, viewModelCarInfoSevenmodeGet2);
            stringValueOfViewModelCarInfoSpeed2 = stringValueOfViewModelCarInfoSpeed;
            TextViewBindingAdapter.setText(this.speedPointerTextView, stringValueOfViewModelCarInfoSpeed2);
        } else {
            stringValueOfViewModelCarInfoSpeed2 = stringValueOfViewModelCarInfoSpeed;
        }
        if ((dirtyFlags & 81920) != 0) {
            viewModelCarInfoBrakeValueViewVISIBLEViewGONE3 = viewModelCarInfoBrakeValueViewVISIBLEViewGONE;
            this.oilImageView.setVisibility(viewModelCarInfoBrakeValueViewVISIBLEViewGONE3);
        } else {
            viewModelCarInfoBrakeValueViewVISIBLEViewGONE3 = viewModelCarInfoBrakeValueViewVISIBLEViewGONE;
        }
        if ((dirtyFlags & 65600) != 0) {
            viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel2 = viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel;
            ImageViewBindingAdapter.setImageDrawable(this.speedometerImageView, viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel2);
        } else {
            viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel2 = viewModelCarInfoUnitInt1SpeedometerImageViewAndroidDrawableSevenColorSpeedMpWtachLevelSpeedometerImageViewAndroidDrawableSevenColorSpeedKmWtachLevel;
        }
        if ((dirtyFlags & 65568) != 0) {
            viewModelCarInfoTempStrGet2 = viewModelCarInfoTempStrGet;
            TextViewBindingAdapter.setText(this.textView18, viewModelCarInfoTempStrGet2);
        } else {
            viewModelCarInfoTempStrGet2 = viewModelCarInfoTempStrGet;
        }
        if ((dirtyFlags & 66560) != 0) {
            DashboardViewModel.setALSRotation(this.zspeedPointerImageView, viewModelCarInfoTurnSpeedAngeGet);
        }
        if ((dirtyFlags & 65792) != 0) {
            TextViewBindingAdapter.setText(this.zspeedPointerTextView, stringValueOfViewModelCarInfoTurnSpeed);
        }
    }

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean viewModelJavaLangObjectNull;
        switch (sourceId) {
            case 1:
                DashboardViewModel viewModel = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel.onSevenModeClick(0);
                    return;
                }
                return;
            case 2:
                DashboardViewModel viewModel2 = this.mViewModel;
                if (viewModel2 != null) {
                    viewModel2.onSevenModeClick(1);
                    return;
                }
                return;
            case 3:
                DashboardViewModel viewModel3 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel3 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel3.onSevenModeClick(2);
                    return;
                }
                return;
            case 4:
                DashboardViewModel viewModel4 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel4 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel4.onSevenModeClick(3);
                    return;
                }
                return;
            case 5:
                DashboardViewModel viewModel5 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel5 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel5.onSevenModeClick(4);
                    return;
                }
                return;
            case 6:
                DashboardViewModel viewModel6 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel6 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel6.onSevenModeClick(5);
                    return;
                }
                return;
            case 7:
                DashboardViewModel viewModel7 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel7 != null;
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
