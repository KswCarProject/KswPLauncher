package com.wits.ksw.databinding;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.DataBindingComponent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.support.p001v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.DashboardViewModel;
import com.wits.ksw.launcher.view.LinearGradientProgress;

/* loaded from: classes7.dex */
public class BmwId8DashboardLayoutBindingImpl extends BmwId8DashboardLayoutBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private long mDirtyFlags_1;
    private OnClickListenerImpl1 mViewModelDashboardMusicLayAndroidViewViewOnClickListener;
    private OnClickListenerImpl mViewModelDashboardWeatherLayAndroidViewViewOnClickListener;
    private final TextView mboundView11;
    private final TextView mboundView12;
    private final TextView mboundView13;
    private final TextView mboundView14;
    private final TextView mboundView15;
    private final TextView mboundView16;
    private final TextView mboundView17;
    private final TextView mboundView18;
    private final TextView mboundView19;
    private final TextView mboundView2;
    private final TextView mboundView20;
    private final TextView mboundView21;
    private final TextView mboundView22;
    private final TextView mboundView23;
    private final TextView mboundView24;
    private final TextView mboundView25;
    private final ImageView mboundView26;
    private final ImageView mboundView27;
    private final RelativeLayout mboundView28;
    private final ImageView mboundView29;
    private final TextView mboundView3;
    private final ImageView mboundView30;
    private final ImageView mboundView31;
    private final ImageView mboundView32;
    private final ImageView mboundView33;
    private final TextView mboundView4;
    private final TextView mboundView5;
    private final TextView mboundView6;
    private final TextView mboundView7;
    private final TextView mboundView8;
    private final TextView mboundView9;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(37);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(34, new String[]{"bmw_id8_dashboard_music_layout", "bmw_id8_dashboard_weather_layout"}, new int[]{35, 36}, new int[]{C0899R.C0902layout.bmw_id8_dashboard_music_layout, C0899R.C0902layout.bmw_id8_dashboard_weather_layout});
        sViewsWithIds = null;
    }

    public BmwId8DashboardLayoutBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 37, sIncludes, sViewsWithIds));
    }

    private BmwId8DashboardLayoutBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 18, (RelativeLayout) bindings[0], (RelativeLayout) bindings[34], (BmwId8DashboardMusicLayoutBinding) bindings[35], (LinearGradientProgress) bindings[10], (LinearGradientProgress) bindings[1], (BmwId8DashboardWeatherLayoutBinding) bindings[36]);
        this.mDirtyFlags = -1L;
        this.mDirtyFlags_1 = -1L;
        this.bmwId8DashboardLay.setTag(null);
        this.bmwId8DashboardMidLay.setTag(null);
        setContainedBinding(this.bmwId8DashboardMusicLay);
        this.bmwId8DashboardRotateProgress.setTag(null);
        this.bmwId8DashboardSpeedProgress.setTag(null);
        setContainedBinding(this.bmwId8DashboardWeatherLay);
        TextView textView = (TextView) bindings[11];
        this.mboundView11 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[12];
        this.mboundView12 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[13];
        this.mboundView13 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[14];
        this.mboundView14 = textView4;
        textView4.setTag(null);
        TextView textView5 = (TextView) bindings[15];
        this.mboundView15 = textView5;
        textView5.setTag(null);
        TextView textView6 = (TextView) bindings[16];
        this.mboundView16 = textView6;
        textView6.setTag(null);
        TextView textView7 = (TextView) bindings[17];
        this.mboundView17 = textView7;
        textView7.setTag(null);
        TextView textView8 = (TextView) bindings[18];
        this.mboundView18 = textView8;
        textView8.setTag(null);
        TextView textView9 = (TextView) bindings[19];
        this.mboundView19 = textView9;
        textView9.setTag(null);
        TextView textView10 = (TextView) bindings[2];
        this.mboundView2 = textView10;
        textView10.setTag(null);
        TextView textView11 = (TextView) bindings[20];
        this.mboundView20 = textView11;
        textView11.setTag(null);
        TextView textView12 = (TextView) bindings[21];
        this.mboundView21 = textView12;
        textView12.setTag(null);
        TextView textView13 = (TextView) bindings[22];
        this.mboundView22 = textView13;
        textView13.setTag(null);
        TextView textView14 = (TextView) bindings[23];
        this.mboundView23 = textView14;
        textView14.setTag(null);
        TextView textView15 = (TextView) bindings[24];
        this.mboundView24 = textView15;
        textView15.setTag(null);
        TextView textView16 = (TextView) bindings[25];
        this.mboundView25 = textView16;
        textView16.setTag(null);
        ImageView imageView = (ImageView) bindings[26];
        this.mboundView26 = imageView;
        imageView.setTag(null);
        ImageView imageView2 = (ImageView) bindings[27];
        this.mboundView27 = imageView2;
        imageView2.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[28];
        this.mboundView28 = relativeLayout;
        relativeLayout.setTag(null);
        ImageView imageView3 = (ImageView) bindings[29];
        this.mboundView29 = imageView3;
        imageView3.setTag(null);
        TextView textView17 = (TextView) bindings[3];
        this.mboundView3 = textView17;
        textView17.setTag(null);
        ImageView imageView4 = (ImageView) bindings[30];
        this.mboundView30 = imageView4;
        imageView4.setTag(null);
        ImageView imageView5 = (ImageView) bindings[31];
        this.mboundView31 = imageView5;
        imageView5.setTag(null);
        ImageView imageView6 = (ImageView) bindings[32];
        this.mboundView32 = imageView6;
        imageView6.setTag(null);
        ImageView imageView7 = (ImageView) bindings[33];
        this.mboundView33 = imageView7;
        imageView7.setTag(null);
        TextView textView18 = (TextView) bindings[4];
        this.mboundView4 = textView18;
        textView18.setTag(null);
        TextView textView19 = (TextView) bindings[5];
        this.mboundView5 = textView19;
        textView19.setTag(null);
        TextView textView20 = (TextView) bindings[6];
        this.mboundView6 = textView20;
        textView20.setTag(null);
        TextView textView21 = (TextView) bindings[7];
        this.mboundView7 = textView21;
        textView21.setTag(null);
        TextView textView22 = (TextView) bindings[8];
        this.mboundView8 = textView22;
        textView22.setTag(null);
        TextView textView23 = (TextView) bindings[9];
        this.mboundView9 = textView23;
        textView23.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
            this.mDirtyFlags_1 = 0L;
        }
        this.bmwId8DashboardMusicLay.invalidateAll();
        this.bmwId8DashboardWeatherLay.invalidateAll();
        requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags == 0 && this.mDirtyFlags_1 == 0) {
                return this.bmwId8DashboardMusicLay.hasPendingBindings() || this.bmwId8DashboardWeatherLay.hasPendingBindings();
            }
            return true;
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

    @Override // com.wits.ksw.databinding.BmwId8DashboardLayoutBinding
    public void setViewModel(DashboardViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
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
                return onChangeBmwId8DashboardMusicLay((BmwId8DashboardMusicLayoutBinding) object, fieldId);
            case 1:
                return onChangeViewModelCarInfoCarImage((ObservableBoolean) object, fieldId);
            case 2:
                return onChangeViewModelCarInfoFrDoorState((ObservableField) object, fieldId);
            case 3:
                return onChangeViewModelCarInfoSpeedLevel((ObservableInt) object, fieldId);
            case 4:
                return onChangeViewModelCarInfoRotateLevel((ObservableInt) object, fieldId);
            case 5:
                return onChangeViewModelCarInfoRrDoorState((ObservableField) object, fieldId);
            case 6:
                return onChangeViewModelCarInfoTempStr((ObservableField) object, fieldId);
            case 7:
                return onChangeViewModelCarInfoSeatBeltpValue((ObservableField) object, fieldId);
            case 8:
                return onChangeViewModelCarInfoSpeedUnit((ObservableField) object, fieldId);
            case 9:
                return onChangeBmwId8DashboardWeatherLay((BmwId8DashboardWeatherLayoutBinding) object, fieldId);
            case 10:
                return onChangeViewModelCarInfoTurnSpeed((ObservableInt) object, fieldId);
            case 11:
                return onChangeViewModelCarInfoOilValue((ObservableField) object, fieldId);
            case 12:
                return onChangeViewModelDashBoardMusicShow((ObservableField) object, fieldId);
            case 13:
                return onChangeViewModelCarInfoRlDoorState((ObservableField) object, fieldId);
            case 14:
                return onChangeViewModelCarInfoFlDoorState((ObservableField) object, fieldId);
            case 15:
                return onChangeViewModelCarInfoSpeed((ObservableInt) object, fieldId);
            case 16:
                return onChangeViewModelCarInfoBDoorState((ObservableField) object, fieldId);
            case 17:
                return onChangeViewModelCarInfoBrakeValue((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeBmwId8DashboardMusicLay(BmwId8DashboardMusicLayoutBinding BmwId8DashboardMusicLay, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoCarImage(ObservableBoolean ViewModelCarInfoCarImage, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoFrDoorState(ObservableField<Boolean> ViewModelCarInfoFrDoorState, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoSpeedLevel(ObservableInt ViewModelCarInfoSpeedLevel, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoRotateLevel(ObservableInt ViewModelCarInfoRotateLevel, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoRrDoorState(ObservableField<Boolean> ViewModelCarInfoRrDoorState, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoTempStr(ObservableField<String> ViewModelCarInfoTempStr, int fieldId) {
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

    private boolean onChangeViewModelCarInfoSpeedUnit(ObservableField<String> ViewModelCarInfoSpeedUnit, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeBmwId8DashboardWeatherLay(BmwId8DashboardWeatherLayoutBinding BmwId8DashboardWeatherLay, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoTurnSpeed(ObservableInt ViewModelCarInfoTurnSpeed, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoOilValue(ObservableField<String> ViewModelCarInfoOilValue, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelDashBoardMusicShow(ObservableField<Boolean> ViewModelDashBoardMusicShow, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoRlDoorState(ObservableField<Boolean> ViewModelCarInfoRlDoorState, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoFlDoorState(ObservableField<Boolean> ViewModelCarInfoFlDoorState, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoSpeed(ObservableInt ViewModelCarInfoSpeed, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoBDoorState(ObservableField<Boolean> ViewModelCarInfoBDoorState, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoBrakeValue(ObservableField<Boolean> ViewModelCarInfoBrakeValue, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
            }
            return true;
        }
        return false;
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: SSATransform
        java.lang.IndexOutOfBoundsException: bitIndex < 0: -124
        	at java.base/java.util.BitSet.get(Unknown Source)
        	at jadx.core.dex.visitors.ssa.LiveVarAnalysis.fillBasicBlockInfo(LiveVarAnalysis.java:65)
        	at jadx.core.dex.visitors.ssa.LiveVarAnalysis.runAnalysis(LiveVarAnalysis.java:36)
        	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:55)
        	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:41)
        */
    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 3533
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.databinding.BmwId8DashboardLayoutBindingImpl.executeBindings():void");
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
