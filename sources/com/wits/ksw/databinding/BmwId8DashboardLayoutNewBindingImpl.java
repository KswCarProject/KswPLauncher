package com.wits.ksw.databinding;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.databinding.DataBindingComponent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.p001v4.media.session.PlaybackStateCompat;
import android.support.p004v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.base.BaseBindingModel;
import com.wits.ksw.launcher.model.DashboardViewModel;
import com.wits.ksw.launcher.view.LinearGradientProgressNew;

/* loaded from: classes7.dex */
public class BmwId8DashboardLayoutNewBindingImpl extends BmwId8DashboardLayoutNewBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private OnClickListenerImpl1 mViewModelDashboardMusicLayAndroidViewViewOnClickListener;
    private OnClickListenerImpl mViewModelDashboardWeatherLayAndroidViewViewOnClickListener;
    private final LinearGradientProgressNew mboundView1;
    private final ImageView mboundView10;
    private final ImageView mboundView11;
    private final ImageView mboundView12;
    private final ImageView mboundView13;
    private final ImageView mboundView14;
    private final LinearGradientProgressNew mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView5;
    private final TextView mboundView6;
    private final ImageView mboundView7;
    private final ImageView mboundView8;
    private final RelativeLayout mboundView9;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(20);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(15, new String[]{"bmw_id8_dashboard_music_layout", "bmw_id8_dashboard_weather_layout"}, new int[]{16, 17}, new int[]{C0899R.C0902layout.bmw_id8_dashboard_music_layout, C0899R.C0902layout.bmw_id8_dashboard_weather_layout});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.bmw_id8_dashboard_left_bg, 18);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_dashboard_right_bg, 19);
    }

    public BmwId8DashboardLayoutNewBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 20, sIncludes, sViewsWithIds));
    }

    private BmwId8DashboardLayoutNewBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 15, (RelativeLayout) bindings[0], (ImageView) bindings[18], (RelativeLayout) bindings[15], (BmwId8DashboardMusicLayoutBinding) bindings[16], (ImageView) bindings[19], (BmwId8DashboardWeatherLayoutBinding) bindings[17]);
        this.mDirtyFlags = -1L;
        this.bmwId8DashboardLay.setTag(null);
        this.bmwId8DashboardMidLay.setTag(null);
        setContainedBinding(this.bmwId8DashboardMusicLay);
        setContainedBinding(this.bmwId8DashboardWeatherLay);
        LinearGradientProgressNew linearGradientProgressNew = (LinearGradientProgressNew) bindings[1];
        this.mboundView1 = linearGradientProgressNew;
        linearGradientProgressNew.setTag(null);
        ImageView imageView = (ImageView) bindings[10];
        this.mboundView10 = imageView;
        imageView.setTag(null);
        ImageView imageView2 = (ImageView) bindings[11];
        this.mboundView11 = imageView2;
        imageView2.setTag(null);
        ImageView imageView3 = (ImageView) bindings[12];
        this.mboundView12 = imageView3;
        imageView3.setTag(null);
        ImageView imageView4 = (ImageView) bindings[13];
        this.mboundView13 = imageView4;
        imageView4.setTag(null);
        ImageView imageView5 = (ImageView) bindings[14];
        this.mboundView14 = imageView5;
        imageView5.setTag(null);
        LinearGradientProgressNew linearGradientProgressNew2 = (LinearGradientProgressNew) bindings[2];
        this.mboundView2 = linearGradientProgressNew2;
        linearGradientProgressNew2.setTag(null);
        TextView textView = (TextView) bindings[3];
        this.mboundView3 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[4];
        this.mboundView4 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[5];
        this.mboundView5 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[6];
        this.mboundView6 = textView4;
        textView4.setTag(null);
        ImageView imageView6 = (ImageView) bindings[7];
        this.mboundView7 = imageView6;
        imageView6.setTag(null);
        ImageView imageView7 = (ImageView) bindings[8];
        this.mboundView8 = imageView7;
        imageView7.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[9];
        this.mboundView9 = relativeLayout;
        relativeLayout.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
        }
        this.bmwId8DashboardMusicLay.invalidateAll();
        this.bmwId8DashboardWeatherLay.invalidateAll();
        requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.bmwId8DashboardMusicLay.hasPendingBindings() || this.bmwId8DashboardWeatherLay.hasPendingBindings();
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

    @Override // com.wits.ksw.databinding.BmwId8DashboardLayoutNewBinding
    public void setViewModel(DashboardViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
        }
        notifyPropertyChanged(25);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.bmwId8DashboardMusicLay.setLifecycleOwner(lifecycleOwner);
        this.bmwId8DashboardWeatherLay.setLifecycleOwner(lifecycleOwner);
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelCarInfoTurnSpeed((ObservableInt) object, fieldId);
            case 1:
                return onChangeBmwId8DashboardMusicLay((BmwId8DashboardMusicLayoutBinding) object, fieldId);
            case 2:
                return onChangeViewModelCarInfoCarImage((ObservableBoolean) object, fieldId);
            case 3:
                return onChangeViewModelCarInfoFrDoorState((ObservableField) object, fieldId);
            case 4:
                return onChangeViewModelCarInfoOilValue((ObservableField) object, fieldId);
            case 5:
                return onChangeViewModelDashBoardMusicShow((ObservableField) object, fieldId);
            case 6:
                return onChangeViewModelCarInfoRlDoorState((ObservableField) object, fieldId);
            case 7:
                return onChangeViewModelCarInfoRrDoorState((ObservableField) object, fieldId);
            case 8:
                return onChangeViewModelCarInfoSeatBeltpValue((ObservableField) object, fieldId);
            case 9:
                return onChangeViewModelCarInfoSpeedUnit((ObservableField) object, fieldId);
            case 10:
                return onChangeViewModelCarInfoFlDoorState((ObservableField) object, fieldId);
            case 11:
                return onChangeBmwId8DashboardWeatherLay((BmwId8DashboardWeatherLayoutBinding) object, fieldId);
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

    private boolean onChangeViewModelCarInfoTurnSpeed(ObservableInt ViewModelCarInfoTurnSpeed, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeBmwId8DashboardMusicLay(BmwId8DashboardMusicLayoutBinding BmwId8DashboardMusicLay, int fieldId) {
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

    private boolean onChangeViewModelCarInfoOilValue(ObservableField<String> ViewModelCarInfoOilValue, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelDashBoardMusicShow(ObservableField<Boolean> ViewModelDashBoardMusicShow, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoRlDoorState(ObservableField<Boolean> ViewModelCarInfoRlDoorState, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoRrDoorState(ObservableField<Boolean> ViewModelCarInfoRrDoorState, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoSeatBeltpValue(ObservableField<Boolean> ViewModelCarInfoSeatBeltpValue, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoSpeedUnit(ObservableField<String> ViewModelCarInfoSpeedUnit, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoFlDoorState(ObservableField<Boolean> ViewModelCarInfoFlDoorState, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeBmwId8DashboardWeatherLay(BmwId8DashboardWeatherLayoutBinding BmwId8DashboardWeatherLay, int fieldId) {
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
        String viewModelCarInfoOilValueGet;
        int viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE;
        String viewModelCarInfoSpeedUnitGet;
        int viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE;
        String stringValueOfViewModelCarInfoSpeed;
        Drawable viewModelCarInfoBrakeValueMboundView8AndroidDrawableId8DashboardIconBrakeFNewMboundView8AndroidDrawableId8DashboardIconBrakeNew;
        View.OnClickListener viewModelDashboardWeatherLayAndroidViewViewOnClickListener;
        int viewModelCarInfoTurnSpeedGet;
        int viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE;
        int viewModelDashBoardMusicShowViewVISIBLEViewGONE;
        int viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE;
        int viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE;
        Drawable viewModelCarInfoSeatBeltpValueMboundView7AndroidDrawableId8DashboardIconSeatbeltNewMboundView7AndroidDrawableId8DashboardIconSeatbeltFNew;
        Drawable viewModelCarInfoBrakeValueMboundView8AndroidDrawableId8DashboardIconBrakeFNewMboundView8AndroidDrawableId8DashboardIconBrakeNew2;
        Drawable viewModelCarInfoSeatBeltpValueMboundView7AndroidDrawableId8DashboardIconSeatbeltNewMboundView7AndroidDrawableId8DashboardIconSeatbeltFNew2;
        View.OnClickListener viewModelDashboardWeatherLayAndroidViewViewOnClickListener2;
        int viewModelDashBoardMusicShowViewVISIBLEViewGONE2;
        String viewModelCarInfoOilValueGet2;
        int viewModelDashBoardMusicShowViewGONEViewVISIBLE;
        String viewModelCarInfoSpeedUnitGet2;
        View.OnClickListener viewModelDashboardMusicLayAndroidViewViewOnClickListener;
        int viewModelCarInfoTurnSpeedGet2;
        int viewModelDashBoardMusicShowViewGONEViewVISIBLE2;
        String viewModelCarInfoSpeedUnitGet3;
        String viewModelCarInfoSpeedUnitGet4;
        Drawable viewModelCarInfoSeatBeltpValueMboundView7AndroidDrawableId8DashboardIconSeatbeltNewMboundView7AndroidDrawableId8DashboardIconSeatbeltFNew3;
        Drawable viewModelCarInfoSeatBeltpValueMboundView7AndroidDrawableId8DashboardIconSeatbeltNewMboundView7AndroidDrawableId8DashboardIconSeatbeltFNew4;
        ObservableField<Boolean> viewModelDashBoardMusicShow;
        ObservableField<Boolean> viewModelCarInfoBDoorState;
        boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet;
        ObservableInt viewModelCarInfoTurnSpeed;
        ObservableInt viewModelCarInfoTurnSpeed2;
        ObservableField<Boolean> viewModelCarInfoBDoorState2;
        boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet2;
        ObservableField<Boolean> viewModelCarInfoBDoorState3;
        ObservableField<Boolean> viewModelCarInfoBDoorState4;
        long dirtyFlags2;
        Context context;
        int i;
        ObservableInt viewModelCarInfoSpeed;
        ObservableField<Boolean> viewModelCarInfoFlDoorState;
        ObservableField<String> viewModelCarInfoSpeedUnit;
        ObservableField<Boolean> viewModelCarInfoSeatBeltpValue;
        long dirtyFlags3;
        Drawable drawable;
        ObservableField<Boolean> viewModelCarInfoRrDoorState;
        ObservableField<Boolean> viewModelCarInfoRlDoorState;
        ObservableField<String> viewModelCarInfoOilValue;
        ObservableField<Boolean> viewModelCarInfoFrDoorState;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        Boolean viewModelCarInfoBDoorStateGet = null;
        int viewModelCarInfoSpeedGet = 0;
        int viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE = 0;
        ObservableBoolean viewModelCarInfoCarImage = null;
        boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet = false;
        boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet = false;
        String viewModelCarInfoOilValueGet3 = null;
        Boolean viewModelCarInfoSeatBeltpValueGet = null;
        Boolean viewModelCarInfoRlDoorStateGet = null;
        int viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE2 = 0;
        String viewModelCarInfoSpeedUnitGet5 = null;
        String stringValueOfViewModelCarInfoSpeed2 = null;
        Boolean viewModelCarInfoRrDoorStateGet = null;
        int viewModelCarInfoTurnSpeedGet3 = 0;
        View.OnClickListener viewModelDashboardMusicLayAndroidViewViewOnClickListener2 = null;
        Boolean viewModelCarInfoFlDoorStateGet = null;
        Boolean viewModelCarInfoBrakeValueGet = null;
        int viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE2 = 0;
        int viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2 = 0;
        int viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2 = 0;
        boolean viewModelCarInfoCarImageGet = false;
        Boolean viewModelCarInfoFrDoorStateGet = null;
        Drawable viewModelCarInfoSeatBeltpValueMboundView7AndroidDrawableId8DashboardIconSeatbeltNewMboundView7AndroidDrawableId8DashboardIconSeatbeltFNew5 = null;
        Boolean viewModelDashBoardMusicShowGet = null;
        int viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2 = 0;
        DashboardViewModel viewModel = this.mViewModel;
        if ((dirtyFlags & 96221) == 0) {
            viewModelCarInfoOilValueGet = null;
            viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE = 0;
            viewModelCarInfoSpeedUnitGet = null;
            viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE = 0;
            stringValueOfViewModelCarInfoSpeed = null;
            viewModelCarInfoBrakeValueMboundView8AndroidDrawableId8DashboardIconBrakeFNewMboundView8AndroidDrawableId8DashboardIconBrakeNew = null;
            viewModelDashboardWeatherLayAndroidViewViewOnClickListener = null;
            viewModelCarInfoTurnSpeedGet = 0;
            viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE = 0;
            viewModelDashBoardMusicShowViewVISIBLEViewGONE = 0;
            viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE = 0;
            viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE = 0;
            viewModelCarInfoSeatBeltpValueMboundView7AndroidDrawableId8DashboardIconSeatbeltNewMboundView7AndroidDrawableId8DashboardIconSeatbeltFNew = null;
        } else {
            com.wits.ksw.launcher.bean.CarInfo viewModelCarInfo = DashboardViewModel.carInfo;
            if ((dirtyFlags & 65537) == 0) {
                viewModelCarInfoBDoorState = null;
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet = false;
                viewModelCarInfoTurnSpeed = null;
            } else {
                if (viewModelCarInfo == null) {
                    viewModelCarInfoBDoorState = null;
                    viewModelCarInfoTurnSpeed = null;
                } else {
                    viewModelCarInfoBDoorState = null;
                    viewModelCarInfoTurnSpeed = viewModelCarInfo.turnSpeed;
                }
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet = false;
                updateRegistration(0, viewModelCarInfoTurnSpeed);
                if (viewModelCarInfoTurnSpeed != null) {
                    viewModelCarInfoTurnSpeedGet3 = viewModelCarInfoTurnSpeed.get();
                }
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
                        dirtyFlags |= 68719476736L;
                    } else {
                        dirtyFlags |= 34359738368L;
                    }
                }
                viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2 = viewModelCarInfoCarImageGet ? 0 : 4;
            }
            if ((dirtyFlags & 65544) == 0) {
                viewModelCarInfoTurnSpeed2 = viewModelCarInfoTurnSpeed;
            } else {
                if (viewModelCarInfo == null) {
                    viewModelCarInfoFrDoorState = null;
                } else {
                    viewModelCarInfoFrDoorState = viewModelCarInfo.frDoorState;
                }
                viewModelCarInfoTurnSpeed2 = viewModelCarInfoTurnSpeed;
                updateRegistration(3, viewModelCarInfoFrDoorState);
                if (viewModelCarInfoFrDoorState != null) {
                    Boolean viewModelCarInfoFrDoorStateGet2 = viewModelCarInfoFrDoorState.get();
                    viewModelCarInfoFrDoorStateGet = viewModelCarInfoFrDoorStateGet2;
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFrDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoFrDoorStateGet);
                if ((dirtyFlags & 65544) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFrDoorStateGet) {
                        dirtyFlags |= 4294967296L;
                    } else {
                        dirtyFlags |= 2147483648L;
                    }
                }
                viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFrDoorStateGet ? 0 : 4;
            }
            if ((dirtyFlags & 65552) != 0) {
                if (viewModelCarInfo == null) {
                    viewModelCarInfoOilValue = null;
                } else {
                    viewModelCarInfoOilValue = viewModelCarInfo.oilValue;
                }
                updateRegistration(4, viewModelCarInfoOilValue);
                if (viewModelCarInfoOilValue != null) {
                    viewModelCarInfoOilValueGet3 = viewModelCarInfoOilValue.get();
                }
            }
            if ((dirtyFlags & 65600) != 0) {
                if (viewModelCarInfo == null) {
                    viewModelCarInfoRlDoorState = null;
                } else {
                    viewModelCarInfoRlDoorState = viewModelCarInfo.rlDoorState;
                }
                updateRegistration(6, viewModelCarInfoRlDoorState);
                if (viewModelCarInfoRlDoorState != null) {
                    Boolean viewModelCarInfoRlDoorStateGet2 = viewModelCarInfoRlDoorState.get();
                    viewModelCarInfoRlDoorStateGet = viewModelCarInfoRlDoorStateGet2;
                }
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoRlDoorStateGet);
                if ((dirtyFlags & 65600) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet) {
                        dirtyFlags |= 268435456;
                    } else {
                        dirtyFlags |= 134217728;
                    }
                }
                viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRlDoorStateGet ? 0 : 4;
            }
            if ((dirtyFlags & 65664) != 0) {
                if (viewModelCarInfo == null) {
                    viewModelCarInfoRrDoorState = null;
                } else {
                    viewModelCarInfoRrDoorState = viewModelCarInfo.rrDoorState;
                }
                updateRegistration(7, viewModelCarInfoRrDoorState);
                if (viewModelCarInfoRrDoorState != null) {
                    Boolean viewModelCarInfoRrDoorStateGet2 = viewModelCarInfoRrDoorState.get();
                    viewModelCarInfoRrDoorStateGet = viewModelCarInfoRrDoorStateGet2;
                }
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoRrDoorStateGet);
                if ((dirtyFlags & 65664) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet) {
                        dirtyFlags |= 4194304;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
                    }
                }
                viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoRrDoorStateGet ? 0 : 4;
            }
            if ((dirtyFlags & 65792) != 0) {
                if (viewModelCarInfo == null) {
                    viewModelCarInfoSeatBeltpValue = null;
                } else {
                    viewModelCarInfoSeatBeltpValue = viewModelCarInfo.seatBeltpValue;
                }
                updateRegistration(8, viewModelCarInfoSeatBeltpValue);
                if (viewModelCarInfoSeatBeltpValue != null) {
                    Boolean viewModelCarInfoSeatBeltpValueGet2 = viewModelCarInfoSeatBeltpValue.get();
                    viewModelCarInfoSeatBeltpValueGet = viewModelCarInfoSeatBeltpValueGet2;
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet = ViewDataBinding.safeUnbox(viewModelCarInfoSeatBeltpValueGet);
                if ((dirtyFlags & 65792) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet) {
                        dirtyFlags |= 17179869184L;
                    } else {
                        dirtyFlags |= 8589934592L;
                    }
                }
                if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet) {
                    dirtyFlags3 = dirtyFlags;
                    drawable = AppCompatResources.getDrawable(this.mboundView7.getContext(), C0899R.C0900drawable.id8_dashboard_icon_seatbelt_new);
                } else {
                    dirtyFlags3 = dirtyFlags;
                    drawable = AppCompatResources.getDrawable(this.mboundView7.getContext(), C0899R.C0900drawable.id8_dashboard_icon_seatbelt_f_new);
                }
                viewModelCarInfoSeatBeltpValueMboundView7AndroidDrawableId8DashboardIconSeatbeltNewMboundView7AndroidDrawableId8DashboardIconSeatbeltFNew5 = drawable;
                dirtyFlags = dirtyFlags3;
            }
            if ((dirtyFlags & 66048) != 0) {
                if (viewModelCarInfo == null) {
                    viewModelCarInfoSpeedUnit = null;
                } else {
                    viewModelCarInfoSpeedUnit = viewModelCarInfo.speedUnit;
                }
                updateRegistration(9, viewModelCarInfoSpeedUnit);
                if (viewModelCarInfoSpeedUnit != null) {
                    viewModelCarInfoSpeedUnitGet5 = viewModelCarInfoSpeedUnit.get();
                }
            }
            if ((dirtyFlags & 66560) != 0) {
                if (viewModelCarInfo == null) {
                    viewModelCarInfoFlDoorState = null;
                } else {
                    viewModelCarInfoFlDoorState = viewModelCarInfo.flDoorState;
                }
                updateRegistration(10, viewModelCarInfoFlDoorState);
                if (viewModelCarInfoFlDoorState != null) {
                    Boolean viewModelCarInfoFlDoorStateGet2 = viewModelCarInfoFlDoorState.get();
                    viewModelCarInfoFlDoorStateGet = viewModelCarInfoFlDoorStateGet2;
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet = ViewDataBinding.safeUnbox(viewModelCarInfoFlDoorStateGet);
                if ((dirtyFlags & 66560) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet) {
                        dirtyFlags |= 1073741824;
                    } else {
                        dirtyFlags |= 536870912;
                    }
                }
                viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoFlDoorStateGet ? 0 : 4;
            }
            if ((dirtyFlags & 69632) != 0) {
                if (viewModelCarInfo == null) {
                    viewModelCarInfoSpeed = null;
                } else {
                    viewModelCarInfoSpeed = viewModelCarInfo.speed;
                }
                updateRegistration(12, viewModelCarInfoSpeed);
                if (viewModelCarInfoSpeed != null) {
                    viewModelCarInfoSpeedGet = viewModelCarInfoSpeed.get();
                }
                stringValueOfViewModelCarInfoSpeed2 = String.valueOf(viewModelCarInfoSpeedGet);
            }
            if ((dirtyFlags & 73728) == 0) {
                viewModelCarInfoBDoorState2 = viewModelCarInfoBDoorState;
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet;
            } else {
                if (viewModelCarInfo == null) {
                    viewModelCarInfoBDoorState2 = viewModelCarInfoBDoorState;
                } else {
                    viewModelCarInfoBDoorState2 = viewModelCarInfo.bDoorState;
                }
                updateRegistration(13, viewModelCarInfoBDoorState2);
                if (viewModelCarInfoBDoorState2 != null) {
                    viewModelCarInfoBDoorStateGet = viewModelCarInfoBDoorState2.get();
                }
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet2 = ViewDataBinding.safeUnbox(viewModelCarInfoBDoorStateGet);
                if ((dirtyFlags & 73728) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet2) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                    }
                }
                viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBDoorStateGet2 ? 0 : 4;
            }
            if ((dirtyFlags & 81920) == 0) {
                viewModelCarInfoOilValueGet = viewModelCarInfoOilValueGet3;
                viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE = viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoSpeedUnitGet = viewModelCarInfoSpeedUnitGet5;
                stringValueOfViewModelCarInfoSpeed = stringValueOfViewModelCarInfoSpeed2;
                viewModelCarInfoBrakeValueMboundView8AndroidDrawableId8DashboardIconBrakeFNewMboundView8AndroidDrawableId8DashboardIconBrakeNew = null;
                int i2 = viewModelCarInfoTurnSpeedGet3;
                viewModelDashboardWeatherLayAndroidViewViewOnClickListener = null;
                viewModelCarInfoTurnSpeedGet = i2;
                int i3 = viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE = 0;
                viewModelDashBoardMusicShowViewVISIBLEViewGONE = i3;
                viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoSeatBeltpValueMboundView7AndroidDrawableId8DashboardIconSeatbeltNewMboundView7AndroidDrawableId8DashboardIconSeatbeltFNew = viewModelCarInfoSeatBeltpValueMboundView7AndroidDrawableId8DashboardIconSeatbeltNewMboundView7AndroidDrawableId8DashboardIconSeatbeltFNew5;
            } else {
                if (viewModelCarInfo == null) {
                    viewModelCarInfoBDoorState3 = viewModelCarInfoBDoorState2;
                    viewModelCarInfoBDoorState4 = null;
                } else {
                    viewModelCarInfoBDoorState3 = viewModelCarInfoBDoorState2;
                    viewModelCarInfoBDoorState4 = viewModelCarInfo.brakeValue;
                }
                updateRegistration(14, viewModelCarInfoBDoorState4);
                if (viewModelCarInfoBDoorState4 != null) {
                    Boolean viewModelCarInfoBrakeValueGet2 = viewModelCarInfoBDoorState4.get();
                    viewModelCarInfoBrakeValueGet = viewModelCarInfoBrakeValueGet2;
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet = ViewDataBinding.safeUnbox(viewModelCarInfoBrakeValueGet);
                if ((dirtyFlags & 81920) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet) {
                        dirtyFlags |= 16777216;
                    } else {
                        dirtyFlags |= 8388608;
                    }
                }
                if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet) {
                    context = this.mboundView8.getContext();
                    dirtyFlags2 = dirtyFlags;
                    i = C0899R.C0900drawable.id8_dashboard_icon_brake_f_new;
                } else {
                    dirtyFlags2 = dirtyFlags;
                    context = this.mboundView8.getContext();
                    i = C0899R.C0900drawable.id8_dashboard_icon_brake_new;
                }
                Drawable viewModelCarInfoBrakeValueMboundView8AndroidDrawableId8DashboardIconBrakeFNewMboundView8AndroidDrawableId8DashboardIconBrakeNew3 = AppCompatResources.getDrawable(context, i);
                viewModelCarInfoOilValueGet = viewModelCarInfoOilValueGet3;
                viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE = viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE2;
                dirtyFlags = dirtyFlags2;
                viewModelCarInfoSpeedUnitGet = viewModelCarInfoSpeedUnitGet5;
                stringValueOfViewModelCarInfoSpeed = stringValueOfViewModelCarInfoSpeed2;
                viewModelCarInfoBrakeValueMboundView8AndroidDrawableId8DashboardIconBrakeFNewMboundView8AndroidDrawableId8DashboardIconBrakeNew = viewModelCarInfoBrakeValueMboundView8AndroidDrawableId8DashboardIconBrakeFNewMboundView8AndroidDrawableId8DashboardIconBrakeNew3;
                int i4 = viewModelCarInfoTurnSpeedGet3;
                viewModelDashboardWeatherLayAndroidViewViewOnClickListener = null;
                viewModelCarInfoTurnSpeedGet = i4;
                int i5 = viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE = 0;
                viewModelDashBoardMusicShowViewVISIBLEViewGONE = i5;
                viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE = viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE2;
                viewModelCarInfoSeatBeltpValueMboundView7AndroidDrawableId8DashboardIconSeatbeltNewMboundView7AndroidDrawableId8DashboardIconSeatbeltFNew = viewModelCarInfoSeatBeltpValueMboundView7AndroidDrawableId8DashboardIconSeatbeltNewMboundView7AndroidDrawableId8DashboardIconSeatbeltFNew5;
            }
        }
        if ((dirtyFlags & 98336) == 0) {
            viewModelCarInfoBrakeValueMboundView8AndroidDrawableId8DashboardIconBrakeFNewMboundView8AndroidDrawableId8DashboardIconBrakeNew2 = viewModelCarInfoBrakeValueMboundView8AndroidDrawableId8DashboardIconBrakeFNewMboundView8AndroidDrawableId8DashboardIconBrakeNew;
            viewModelCarInfoSeatBeltpValueMboundView7AndroidDrawableId8DashboardIconSeatbeltNewMboundView7AndroidDrawableId8DashboardIconSeatbeltFNew2 = viewModelCarInfoSeatBeltpValueMboundView7AndroidDrawableId8DashboardIconSeatbeltNewMboundView7AndroidDrawableId8DashboardIconSeatbeltFNew;
            viewModelDashboardWeatherLayAndroidViewViewOnClickListener2 = viewModelDashboardWeatherLayAndroidViewViewOnClickListener;
            viewModelDashBoardMusicShowViewVISIBLEViewGONE2 = viewModelCarInfoRlDoorStateViewVISIBLEViewINVISIBLE;
            viewModelCarInfoOilValueGet2 = viewModelCarInfoOilValueGet;
            viewModelDashBoardMusicShowViewGONEViewVISIBLE = 0;
            viewModelCarInfoSpeedUnitGet2 = viewModelCarInfoSpeedUnitGet;
            viewModelDashboardMusicLayAndroidViewViewOnClickListener = null;
        } else {
            if ((dirtyFlags & 98304) == 0) {
                viewModelCarInfoBrakeValueMboundView8AndroidDrawableId8DashboardIconBrakeFNewMboundView8AndroidDrawableId8DashboardIconBrakeNew2 = viewModelCarInfoBrakeValueMboundView8AndroidDrawableId8DashboardIconBrakeFNewMboundView8AndroidDrawableId8DashboardIconBrakeNew;
            } else if (viewModel == null) {
                viewModelCarInfoBrakeValueMboundView8AndroidDrawableId8DashboardIconBrakeFNewMboundView8AndroidDrawableId8DashboardIconBrakeNew2 = viewModelCarInfoBrakeValueMboundView8AndroidDrawableId8DashboardIconBrakeFNewMboundView8AndroidDrawableId8DashboardIconBrakeNew;
            } else {
                viewModelCarInfoBrakeValueMboundView8AndroidDrawableId8DashboardIconBrakeFNewMboundView8AndroidDrawableId8DashboardIconBrakeNew2 = viewModelCarInfoBrakeValueMboundView8AndroidDrawableId8DashboardIconBrakeFNewMboundView8AndroidDrawableId8DashboardIconBrakeNew;
                OnClickListenerImpl onClickListenerImpl = this.mViewModelDashboardWeatherLayAndroidViewViewOnClickListener;
                if (onClickListenerImpl == null) {
                    onClickListenerImpl = new OnClickListenerImpl();
                    this.mViewModelDashboardWeatherLayAndroidViewViewOnClickListener = onClickListenerImpl;
                }
                View.OnClickListener viewModelDashboardWeatherLayAndroidViewViewOnClickListener3 = onClickListenerImpl.setValue(viewModel);
                viewModelDashboardWeatherLayAndroidViewViewOnClickListener = viewModelDashboardWeatherLayAndroidViewViewOnClickListener3;
                OnClickListenerImpl1 onClickListenerImpl1 = this.mViewModelDashboardMusicLayAndroidViewViewOnClickListener;
                if (onClickListenerImpl1 == null) {
                    onClickListenerImpl1 = new OnClickListenerImpl1();
                    this.mViewModelDashboardMusicLayAndroidViewViewOnClickListener = onClickListenerImpl1;
                }
                viewModelDashboardMusicLayAndroidViewViewOnClickListener2 = onClickListenerImpl1.setValue(viewModel);
            }
            if (viewModel == null) {
                viewModelDashBoardMusicShow = null;
            } else {
                viewModelDashBoardMusicShow = viewModel.dashBoardMusicShow;
            }
            viewModelCarInfoSeatBeltpValueMboundView7AndroidDrawableId8DashboardIconSeatbeltNewMboundView7AndroidDrawableId8DashboardIconSeatbeltFNew2 = viewModelCarInfoSeatBeltpValueMboundView7AndroidDrawableId8DashboardIconSeatbeltNewMboundView7AndroidDrawableId8DashboardIconSeatbeltFNew;
            updateRegistration(5, viewModelDashBoardMusicShow);
            if (viewModelDashBoardMusicShow != null) {
                Boolean viewModelDashBoardMusicShowGet2 = viewModelDashBoardMusicShow.get();
                viewModelDashBoardMusicShowGet = viewModelDashBoardMusicShowGet2;
            }
            boolean androidDatabindingViewDataBindingSafeUnboxViewModelDashBoardMusicShowGet = ViewDataBinding.safeUnbox(viewModelDashBoardMusicShowGet);
            if ((dirtyFlags & 98336) != 0) {
                if (androidDatabindingViewDataBindingSafeUnboxViewModelDashBoardMusicShowGet) {
                    dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED | 67108864;
                } else {
                    dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED | 33554432;
                }
            }
            viewModelDashBoardMusicShowViewVISIBLEViewGONE2 = androidDatabindingViewDataBindingSafeUnboxViewModelDashBoardMusicShowGet ? 0 : 8;
            int viewModelDashBoardMusicShowViewGONEViewVISIBLE3 = androidDatabindingViewDataBindingSafeUnboxViewModelDashBoardMusicShowGet ? 8 : 0;
            viewModelDashboardWeatherLayAndroidViewViewOnClickListener2 = viewModelDashboardWeatherLayAndroidViewViewOnClickListener;
            viewModelCarInfoOilValueGet2 = viewModelCarInfoOilValueGet;
            viewModelDashBoardMusicShowViewGONEViewVISIBLE = viewModelDashBoardMusicShowViewGONEViewVISIBLE3;
            viewModelCarInfoSpeedUnitGet2 = viewModelCarInfoSpeedUnitGet;
            viewModelDashboardMusicLayAndroidViewViewOnClickListener = viewModelDashboardMusicLayAndroidViewViewOnClickListener2;
        }
        if ((dirtyFlags & 98304) == 0) {
            viewModelCarInfoTurnSpeedGet2 = viewModelCarInfoTurnSpeedGet;
        } else {
            viewModelCarInfoTurnSpeedGet2 = viewModelCarInfoTurnSpeedGet;
            this.bmwId8DashboardMusicLay.getRoot().setOnClickListener(viewModelDashboardMusicLayAndroidViewViewOnClickListener);
            this.bmwId8DashboardMusicLay.setViewModel(viewModel);
            this.bmwId8DashboardWeatherLay.getRoot().setOnClickListener(viewModelDashboardWeatherLayAndroidViewViewOnClickListener2);
            this.bmwId8DashboardWeatherLay.setViewModel(viewModel);
        }
        if ((dirtyFlags & 98336) != 0) {
            this.bmwId8DashboardMusicLay.getRoot().setVisibility(viewModelDashBoardMusicShowViewVISIBLEViewGONE2);
            this.bmwId8DashboardWeatherLay.getRoot().setVisibility(viewModelDashBoardMusicShowViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 69632) != 0) {
            this.mboundView1.setScae(viewModelCarInfoSpeedGet);
            TextViewBindingAdapter.setText(this.mboundView3, stringValueOfViewModelCarInfoSpeed);
        }
        if ((dirtyFlags & 65544) != 0) {
            this.mboundView10.setVisibility(viewModelCarInfoFrDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 65664) != 0) {
            this.mboundView11.setVisibility(viewModelCarInfoRrDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 66560) != 0) {
            this.mboundView12.setVisibility(viewModelCarInfoFlDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 65600) != 0) {
            this.mboundView13.setVisibility(viewModelDashBoardMusicShowViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 73728) != 0) {
            this.mboundView14.setVisibility(viewModelCarInfoBDoorStateViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 65537) != 0) {
            viewModelDashBoardMusicShowViewGONEViewVISIBLE2 = viewModelCarInfoTurnSpeedGet2;
            this.mboundView2.setScae(viewModelDashBoardMusicShowViewGONEViewVISIBLE2);
            BaseBindingModel.setTurnSpeedStr(this.mboundView5, viewModelDashBoardMusicShowViewGONEViewVISIBLE2);
        } else {
            viewModelDashBoardMusicShowViewGONEViewVISIBLE2 = viewModelCarInfoTurnSpeedGet2;
        }
        if ((dirtyFlags & 66048) != 0) {
            viewModelCarInfoSpeedUnitGet3 = viewModelCarInfoSpeedUnitGet2;
            TextViewBindingAdapter.setText(this.mboundView4, viewModelCarInfoSpeedUnitGet3);
        } else {
            viewModelCarInfoSpeedUnitGet3 = viewModelCarInfoSpeedUnitGet2;
        }
        if ((dirtyFlags & 65552) != 0) {
            viewModelCarInfoSpeedUnitGet4 = viewModelCarInfoOilValueGet2;
            TextViewBindingAdapter.setText(this.mboundView6, viewModelCarInfoSpeedUnitGet4);
        } else {
            viewModelCarInfoSpeedUnitGet4 = viewModelCarInfoOilValueGet2;
        }
        if ((dirtyFlags & 65792) != 0) {
            viewModelCarInfoSeatBeltpValueMboundView7AndroidDrawableId8DashboardIconSeatbeltNewMboundView7AndroidDrawableId8DashboardIconSeatbeltFNew3 = viewModelCarInfoSeatBeltpValueMboundView7AndroidDrawableId8DashboardIconSeatbeltNewMboundView7AndroidDrawableId8DashboardIconSeatbeltFNew2;
            ImageViewBindingAdapter.setImageDrawable(this.mboundView7, viewModelCarInfoSeatBeltpValueMboundView7AndroidDrawableId8DashboardIconSeatbeltNewMboundView7AndroidDrawableId8DashboardIconSeatbeltFNew3);
        } else {
            viewModelCarInfoSeatBeltpValueMboundView7AndroidDrawableId8DashboardIconSeatbeltNewMboundView7AndroidDrawableId8DashboardIconSeatbeltFNew3 = viewModelCarInfoSeatBeltpValueMboundView7AndroidDrawableId8DashboardIconSeatbeltNewMboundView7AndroidDrawableId8DashboardIconSeatbeltFNew2;
        }
        if ((dirtyFlags & 81920) != 0) {
            viewModelCarInfoSeatBeltpValueMboundView7AndroidDrawableId8DashboardIconSeatbeltNewMboundView7AndroidDrawableId8DashboardIconSeatbeltFNew4 = viewModelCarInfoBrakeValueMboundView8AndroidDrawableId8DashboardIconBrakeFNewMboundView8AndroidDrawableId8DashboardIconBrakeNew2;
            ImageViewBindingAdapter.setImageDrawable(this.mboundView8, viewModelCarInfoSeatBeltpValueMboundView7AndroidDrawableId8DashboardIconSeatbeltNewMboundView7AndroidDrawableId8DashboardIconSeatbeltFNew4);
        } else {
            viewModelCarInfoSeatBeltpValueMboundView7AndroidDrawableId8DashboardIconSeatbeltNewMboundView7AndroidDrawableId8DashboardIconSeatbeltFNew4 = viewModelCarInfoBrakeValueMboundView8AndroidDrawableId8DashboardIconBrakeFNewMboundView8AndroidDrawableId8DashboardIconBrakeNew2;
        }
        if ((dirtyFlags & 65540) != 0) {
            this.mboundView9.setVisibility(viewModelCarInfoCarImageViewVISIBLEViewINVISIBLE);
        }
        executeBindingsOn(this.bmwId8DashboardMusicLay);
        executeBindingsOn(this.bmwId8DashboardWeatherLay);
    }

    /* loaded from: classes7.dex */
    public static class OnClickListenerImpl implements View.OnClickListener {
        private DashboardViewModel value;

        public OnClickListenerImpl setValue(DashboardViewModel value) {
            this.value = value;
            if (value == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View arg0) {
            this.value.dashboardWeatherLay(arg0);
        }
    }

    /* loaded from: classes7.dex */
    public static class OnClickListenerImpl1 implements View.OnClickListener {
        private DashboardViewModel value;

        public OnClickListenerImpl1 setValue(DashboardViewModel value) {
            this.value = value;
            if (value == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View arg0) {
            this.value.dashboardMusicLay(arg0);
        }
    }
}
