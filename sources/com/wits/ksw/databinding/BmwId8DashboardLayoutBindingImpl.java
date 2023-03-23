package com.wits.ksw.databinding;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.DataBindingComponent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.DashboardViewModel;

public class BmwId8DashboardLayoutBindingImpl extends BmwId8DashboardLayoutBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds = null;
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
        includedLayouts.setIncludes(34, new String[]{"bmw_id8_dashboard_music_layout", "bmw_id8_dashboard_weather_layout"}, new int[]{35, 36}, new int[]{R.layout.bmw_id8_dashboard_music_layout, R.layout.bmw_id8_dashboard_weather_layout});
    }

    public BmwId8DashboardLayoutBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 37, sIncludes, sViewsWithIds));
    }

    private BmwId8DashboardLayoutBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 18, bindings[0], bindings[34], bindings[35], bindings[10], bindings[1], bindings[36]);
        this.mDirtyFlags = -1;
        this.mDirtyFlags_1 = -1;
        this.bmwId8DashboardLay.setTag((Object) null);
        this.bmwId8DashboardMidLay.setTag((Object) null);
        setContainedBinding(this.bmwId8DashboardMusicLay);
        this.bmwId8DashboardRotateProgress.setTag((Object) null);
        this.bmwId8DashboardSpeedProgress.setTag((Object) null);
        setContainedBinding(this.bmwId8DashboardWeatherLay);
        TextView textView = bindings[11];
        this.mboundView11 = textView;
        textView.setTag((Object) null);
        TextView textView2 = bindings[12];
        this.mboundView12 = textView2;
        textView2.setTag((Object) null);
        TextView textView3 = bindings[13];
        this.mboundView13 = textView3;
        textView3.setTag((Object) null);
        TextView textView4 = bindings[14];
        this.mboundView14 = textView4;
        textView4.setTag((Object) null);
        TextView textView5 = bindings[15];
        this.mboundView15 = textView5;
        textView5.setTag((Object) null);
        TextView textView6 = bindings[16];
        this.mboundView16 = textView6;
        textView6.setTag((Object) null);
        TextView textView7 = bindings[17];
        this.mboundView17 = textView7;
        textView7.setTag((Object) null);
        TextView textView8 = bindings[18];
        this.mboundView18 = textView8;
        textView8.setTag((Object) null);
        TextView textView9 = bindings[19];
        this.mboundView19 = textView9;
        textView9.setTag((Object) null);
        TextView textView10 = bindings[2];
        this.mboundView2 = textView10;
        textView10.setTag((Object) null);
        TextView textView11 = bindings[20];
        this.mboundView20 = textView11;
        textView11.setTag((Object) null);
        TextView textView12 = bindings[21];
        this.mboundView21 = textView12;
        textView12.setTag((Object) null);
        TextView textView13 = bindings[22];
        this.mboundView22 = textView13;
        textView13.setTag((Object) null);
        TextView textView14 = bindings[23];
        this.mboundView23 = textView14;
        textView14.setTag((Object) null);
        TextView textView15 = bindings[24];
        this.mboundView24 = textView15;
        textView15.setTag((Object) null);
        TextView textView16 = bindings[25];
        this.mboundView25 = textView16;
        textView16.setTag((Object) null);
        ImageView imageView = bindings[26];
        this.mboundView26 = imageView;
        imageView.setTag((Object) null);
        ImageView imageView2 = bindings[27];
        this.mboundView27 = imageView2;
        imageView2.setTag((Object) null);
        RelativeLayout relativeLayout = bindings[28];
        this.mboundView28 = relativeLayout;
        relativeLayout.setTag((Object) null);
        ImageView imageView3 = bindings[29];
        this.mboundView29 = imageView3;
        imageView3.setTag((Object) null);
        TextView textView17 = bindings[3];
        this.mboundView3 = textView17;
        textView17.setTag((Object) null);
        ImageView imageView4 = bindings[30];
        this.mboundView30 = imageView4;
        imageView4.setTag((Object) null);
        ImageView imageView5 = bindings[31];
        this.mboundView31 = imageView5;
        imageView5.setTag((Object) null);
        ImageView imageView6 = bindings[32];
        this.mboundView32 = imageView6;
        imageView6.setTag((Object) null);
        ImageView imageView7 = bindings[33];
        this.mboundView33 = imageView7;
        imageView7.setTag((Object) null);
        TextView textView18 = bindings[4];
        this.mboundView4 = textView18;
        textView18.setTag((Object) null);
        TextView textView19 = bindings[5];
        this.mboundView5 = textView19;
        textView19.setTag((Object) null);
        TextView textView20 = bindings[6];
        this.mboundView6 = textView20;
        textView20.setTag((Object) null);
        TextView textView21 = bindings[7];
        this.mboundView7 = textView21;
        textView21.setTag((Object) null);
        TextView textView22 = bindings[8];
        this.mboundView8 = textView22;
        textView22.setTag((Object) null);
        TextView textView23 = bindings[9];
        this.mboundView9 = textView23;
        textView23.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
            this.mDirtyFlags_1 = 0;
        }
        this.bmwId8DashboardMusicLay.invalidateAll();
        this.bmwId8DashboardWeatherLay.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001a, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0021, code lost:
        if (r6.bmwId8DashboardWeatherLay.hasPendingBindings() == false) goto L_0x0024;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0023, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0024, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0018, code lost:
        if (r6.bmwId8DashboardMusicLay.hasPendingBindings() == false) goto L_0x001b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        /*
            r6 = this;
            monitor-enter(r6)
            long r0 = r6.mDirtyFlags     // Catch:{ all -> 0x0028 }
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 1
            if (r0 != 0) goto L_0x0026
            long r4 = r6.mDirtyFlags_1     // Catch:{ all -> 0x0028 }
            int r0 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r0 == 0) goto L_0x0011
            goto L_0x0026
        L_0x0011:
            monitor-exit(r6)     // Catch:{ all -> 0x0028 }
            com.wits.ksw.databinding.BmwId8DashboardMusicLayoutBinding r0 = r6.bmwId8DashboardMusicLay
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x001b
            return r1
        L_0x001b:
            com.wits.ksw.databinding.BmwId8DashboardWeatherLayoutBinding r0 = r6.bmwId8DashboardWeatherLay
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0024
            return r1
        L_0x0024:
            r0 = 0
            return r0
        L_0x0026:
            monitor-exit(r6)     // Catch:{ all -> 0x0028 }
            return r1
        L_0x0028:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0028 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.databinding.BmwId8DashboardLayoutBindingImpl.hasPendingBindings():boolean");
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
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
        }
        notifyPropertyChanged(25);
        super.requestRebind();
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.bmwId8DashboardMusicLay.setLifecycleOwner(lifecycleOwner);
        this.bmwId8DashboardWeatherLay.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
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

    private boolean onChangeViewModelCarInfoSpeedLevel(ObservableInt ViewModelCarInfoSpeedLevel, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoRotateLevel(ObservableInt ViewModelCarInfoRotateLevel, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoRrDoorState(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoTempStr(ObservableField<String> observableField, int fieldId) {
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

    private boolean onChangeViewModelCarInfoSpeedUnit(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeBmwId8DashboardWeatherLay(BmwId8DashboardWeatherLayoutBinding BmwId8DashboardWeatherLay, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoTurnSpeed(ObservableInt ViewModelCarInfoTurnSpeed, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoOilValue(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeViewModelDashBoardMusicShow(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoRlDoorState(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoFlDoorState(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoSpeed(ObservableInt ViewModelCarInfoSpeed, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoBDoorState(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoBrakeValue(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
        }
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r120v2, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v13, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v14, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v15, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v16, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v17, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v20, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v23, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v26, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v29, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v32, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v35, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v36, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v37, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v38, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v39, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v40, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v41, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v94, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v95, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v96, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v97, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v98, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v99, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v145, resolved type: long} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r181 = this;
            r1 = r181
            r2 = 0
            r4 = 0
            monitor-enter(r181)
            long r6 = r1.mDirtyFlags     // Catch:{ all -> 0x0dca }
            r2 = r6
            r6 = 0
            r1.mDirtyFlags = r6     // Catch:{ all -> 0x0dca }
            long r8 = r1.mDirtyFlags_1     // Catch:{ all -> 0x0dca }
            r4 = r8
            r1.mDirtyFlags_1 = r6     // Catch:{ all -> 0x0dca }
            monitor-exit(r181)     // Catch:{ all -> 0x0dca }
            r0 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r26 = 0
            r27 = 0
            r28 = 0
            r29 = 0
            r30 = 0
            r31 = 0
            r32 = 0
            r33 = 0
            r34 = 0
            r35 = 0
            r36 = 0
            r37 = 0
            r38 = 0
            r39 = 0
            r40 = 0
            r41 = 0
            r42 = 0
            r43 = 0
            r44 = 0
            r45 = 0
            r46 = 0
            r47 = 0
            r48 = 0
            r49 = 0
            r50 = 0
            r51 = 0
            r52 = 0
            r53 = 0
            r54 = 0
            r55 = 0
            r56 = 0
            r57 = 0
            r58 = 0
            r59 = 0
            r60 = 0
            r61 = 0
            r62 = 0
            r63 = 0
            r64 = 0
            r65 = 0
            r66 = 0
            r67 = 0
            r68 = 0
            r69 = 0
            r70 = 0
            r71 = 0
            r72 = 0
            r73 = 0
            r74 = 0
            r75 = 0
            r76 = 0
            r77 = 0
            r78 = 0
            r79 = 0
            r80 = 0
            r81 = 0
            r82 = 0
            r83 = 0
            r84 = 0
            r85 = 0
            r86 = 0
            r87 = 0
            r88 = 0
            r89 = 0
            r90 = 0
            r91 = 0
            r92 = 0
            r93 = 0
            r94 = 0
            r95 = 0
            r96 = 0
            r97 = 0
            r98 = 0
            r99 = 0
            r100 = 0
            r101 = 0
            r102 = 0
            r103 = 0
            r104 = 0
            r105 = 0
            r106 = 0
            r107 = 0
            r108 = 0
            r109 = 0
            r110 = 0
            r111 = 0
            r112 = 0
            r113 = 0
            r114 = 0
            r115 = 0
            r116 = 0
            r117 = 0
            r118 = 0
            r119 = 0
            r120 = 0
            r121 = 0
            r122 = 0
            r123 = 0
            r124 = 0
            r125 = 0
            r126 = 0
            com.wits.ksw.launcher.model.DashboardViewModel r6 = r1.mViewModel
            r7 = 0
            r129 = 0
            r130 = 0
            r131 = 0
            r132 = 781822(0xbedfe, double:3.862714E-318)
            long r132 = r2 & r132
            r127 = 0
            int r132 = (r132 > r127 ? 1 : (r132 == r127 ? 0 : -1))
            r133 = 540672(0x84000, double:2.671275E-318)
            r135 = 655360(0xa0000, double:3.23791E-318)
            r137 = 532480(0x82000, double:2.6308E-318)
            r139 = 524416(0x80080, double:2.59096E-318)
            r141 = 524320(0x80020, double:2.590485E-318)
            r143 = 524292(0x80004, double:2.590347E-318)
            r145 = 524290(0x80002, double:2.590337E-318)
            r147 = r0
            r148 = 524296(0x80008, double:2.590366E-318)
            r150 = 524304(0x80010, double:2.590406E-318)
            if (r132 == 0) goto L_0x09fa
            com.wits.ksw.launcher.bean.CarInfo r0 = com.wits.ksw.launcher.model.DashboardViewModel.carInfo
            long r153 = r2 & r145
            r127 = 0
            int r66 = (r153 > r127 ? 1 : (r153 == r127 ? 0 : -1))
            r153 = r7
            if (r66 == 0) goto L_0x0168
            if (r0 == 0) goto L_0x013d
            android.databinding.ObservableBoolean r7 = r0.carImage
            goto L_0x013f
        L_0x013d:
            r7 = r18
        L_0x013f:
            r155 = r8
            r8 = 1
            r1.updateRegistration((int) r8, (android.databinding.Observable) r7)
            if (r7 == 0) goto L_0x014b
            boolean r67 = r7.get()
        L_0x014b:
            long r156 = r2 & r145
            r127 = 0
            int r8 = (r156 > r127 ? 1 : (r156 == r127 ? 0 : -1))
            if (r8 == 0) goto L_0x015e
            if (r67 == 0) goto L_0x015a
            r156 = 562949953421312(0x2000000000000, double:2.781342323134002E-309)
            long r2 = r2 | r156
            goto L_0x015e
        L_0x015a:
            r156 = 281474976710656(0x1000000000000, double:1.390671161567E-309)
            long r2 = r2 | r156
        L_0x015e:
            if (r67 == 0) goto L_0x0162
            r8 = 0
            goto L_0x0163
        L_0x0162:
            r8 = 4
        L_0x0163:
            r18 = r7
            r74 = r8
            goto L_0x016a
        L_0x0168:
            r155 = r8
        L_0x016a:
            long r7 = r2 & r143
            r127 = 0
            int r7 = (r7 > r127 ? 1 : (r7 == r127 ? 0 : -1))
            r8 = 2
            if (r7 == 0) goto L_0x01ab
            if (r0 == 0) goto L_0x0178
            android.databinding.ObservableField<java.lang.Boolean> r7 = r0.frDoorState
            goto L_0x017a
        L_0x0178:
            r7 = r25
        L_0x017a:
            r1.updateRegistration((int) r8, (android.databinding.Observable) r7)
            if (r7 == 0) goto L_0x0187
            java.lang.Object r25 = r7.get()
            r68 = r25
            java.lang.Boolean r68 = (java.lang.Boolean) r68
        L_0x0187:
            boolean r25 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r68)
            long r156 = r2 & r143
            r127 = 0
            int r121 = (r156 > r127 ? 1 : (r156 == r127 ? 0 : -1))
            if (r121 == 0) goto L_0x019e
            if (r25 == 0) goto L_0x019a
            r156 = 2
            long r4 = r4 | r156
            goto L_0x019e
        L_0x019a:
            r156 = 1
            long r4 = r4 | r156
        L_0x019e:
            if (r25 == 0) goto L_0x01a3
            r121 = 0
            goto L_0x01a5
        L_0x01a3:
            r121 = 4
        L_0x01a5:
            r117 = r121
            r121 = r25
            r25 = r7
        L_0x01ab:
            long r156 = r2 & r148
            r127 = 0
            int r7 = (r156 > r127 ? 1 : (r156 == r127 ? 0 : -1))
            if (r7 == 0) goto L_0x0398
            if (r0 == 0) goto L_0x01b8
            android.databinding.ObservableInt r7 = r0.speedLevel
            goto L_0x01ba
        L_0x01b8:
            r7 = r45
        L_0x01ba:
            r8 = 3
            r1.updateRegistration((int) r8, (android.databinding.Observable) r7)
            if (r7 == 0) goto L_0x01c4
            int r12 = r7.get()
        L_0x01c4:
            r8 = 5
            if (r12 != r8) goto L_0x01c9
            r8 = 1
            goto L_0x01ca
        L_0x01c9:
            r8 = 0
        L_0x01ca:
            r45 = r7
            r7 = 2
            if (r12 != r7) goto L_0x01d1
            r7 = 1
            goto L_0x01d2
        L_0x01d1:
            r7 = 0
        L_0x01d2:
            r158 = r9
            r9 = 3
            if (r12 != r9) goto L_0x01d9
            r9 = 1
            goto L_0x01da
        L_0x01d9:
            r9 = 0
        L_0x01da:
            if (r12 != 0) goto L_0x01df
            r21 = 1
            goto L_0x01e1
        L_0x01df:
            r21 = 0
        L_0x01e1:
            r159 = r10
            r10 = 1
            if (r12 != r10) goto L_0x01e8
            r10 = 1
            goto L_0x01e9
        L_0x01e8:
            r10 = 0
        L_0x01e9:
            r160 = r11
            r11 = 6
            if (r12 != r11) goto L_0x01f0
            r11 = 1
            goto L_0x01f1
        L_0x01f0:
            r11 = 0
        L_0x01f1:
            r161 = r13
            r13 = 7
            if (r12 != r13) goto L_0x01f8
            r13 = 1
            goto L_0x01f9
        L_0x01f8:
            r13 = 0
        L_0x01f9:
            r162 = r14
            r14 = 4
            if (r12 != r14) goto L_0x0200
            r14 = 1
            goto L_0x0201
        L_0x0200:
            r14 = 0
        L_0x0201:
            long r163 = r2 & r148
            r127 = 0
            int r37 = (r163 > r127 ? 1 : (r163 == r127 ? 0 : -1))
            if (r37 == 0) goto L_0x0214
            if (r8 == 0) goto L_0x0210
            r163 = 576460752303423488(0x800000000000000, double:3.785766995733679E-270)
            long r2 = r2 | r163
            goto L_0x0214
        L_0x0210:
            r163 = 288230376151711744(0x400000000000000, double:2.0522684006491881E-289)
            long r2 = r2 | r163
        L_0x0214:
            long r163 = r2 & r148
            r127 = 0
            int r37 = (r163 > r127 ? 1 : (r163 == r127 ? 0 : -1))
            if (r37 == 0) goto L_0x0227
            if (r7 == 0) goto L_0x0223
            r163 = 8
            long r4 = r4 | r163
            goto L_0x0227
        L_0x0223:
            r163 = 4
            long r4 = r4 | r163
        L_0x0227:
            long r163 = r2 & r148
            r127 = 0
            int r37 = (r163 > r127 ? 1 : (r163 == r127 ? 0 : -1))
            if (r37 == 0) goto L_0x023a
            if (r9 == 0) goto L_0x0236
            r163 = 144115188075855872(0x200000000000000, double:4.7783097267364807E-299)
            long r2 = r2 | r163
            goto L_0x023a
        L_0x0236:
            r163 = 72057594037927936(0x100000000000000, double:7.2911220195563975E-304)
            long r2 = r2 | r163
        L_0x023a:
            long r163 = r2 & r148
            r127 = 0
            int r37 = (r163 > r127 ? 1 : (r163 == r127 ? 0 : -1))
            if (r37 == 0) goto L_0x024d
            if (r21 == 0) goto L_0x0249
            r163 = 512(0x200, double:2.53E-321)
            long r4 = r4 | r163
            goto L_0x024d
        L_0x0249:
            r163 = 256(0x100, double:1.265E-321)
            long r4 = r4 | r163
        L_0x024d:
            long r163 = r2 & r148
            r127 = 0
            int r37 = (r163 > r127 ? 1 : (r163 == r127 ? 0 : -1))
            if (r37 == 0) goto L_0x0266
            if (r10 == 0) goto L_0x025f
            r163 = 140737488355328(0x800000000000, double:6.953355807835E-310)
            long r2 = r2 | r163
            goto L_0x0266
        L_0x025f:
            r163 = 70368744177664(0x400000000000, double:3.4766779039175E-310)
            long r2 = r2 | r163
        L_0x0266:
            long r163 = r2 & r148
            r127 = 0
            int r37 = (r163 > r127 ? 1 : (r163 == r127 ? 0 : -1))
            if (r37 == 0) goto L_0x027b
            if (r11 == 0) goto L_0x0276
            r163 = 2097152(0x200000, double:1.0361308E-317)
            long r2 = r2 | r163
            goto L_0x027b
        L_0x0276:
            r163 = 1048576(0x100000, double:5.180654E-318)
            long r2 = r2 | r163
        L_0x027b:
            long r163 = r2 & r148
            r127 = 0
            int r37 = (r163 > r127 ? 1 : (r163 == r127 ? 0 : -1))
            if (r37 == 0) goto L_0x028e
            if (r13 == 0) goto L_0x028a
            r163 = 9007199254740992(0x20000000000000, double:4.450147717014403E-308)
            long r2 = r2 | r163
            goto L_0x028e
        L_0x028a:
            r163 = 4503599627370496(0x10000000000000, double:2.2250738585072014E-308)
            long r2 = r2 | r163
        L_0x028e:
            long r163 = r2 & r148
            r127 = 0
            int r37 = (r163 > r127 ? 1 : (r163 == r127 ? 0 : -1))
            if (r37 == 0) goto L_0x02a7
            if (r14 == 0) goto L_0x02a0
            r163 = 8589934592(0x200000000, double:4.243991582E-314)
            long r2 = r2 | r163
            goto L_0x02a7
        L_0x02a0:
            r163 = 4294967296(0x100000000, double:2.121995791E-314)
            long r2 = r2 | r163
        L_0x02a7:
            if (r8 == 0) goto L_0x02b1
            r163 = r2
            android.widget.TextView r2 = r1.mboundView4
            r3 = 2131099707(0x7f06003b, float:1.7811775E38)
            goto L_0x02b8
        L_0x02b1:
            r163 = r2
            android.widget.TextView r2 = r1.mboundView4
            r3 = 2131099694(0x7f06002e, float:1.7811748E38)
        L_0x02b8:
            int r2 = getColorFromResource(r2, r3)
            if (r7 == 0) goto L_0x02ca
            android.widget.TextView r3 = r1.mboundView7
            r37 = r2
            r2 = 2131099707(0x7f06003b, float:1.7811775E38)
            int r3 = getColorFromResource(r3, r2)
            goto L_0x02d6
        L_0x02ca:
            r37 = r2
            android.widget.TextView r2 = r1.mboundView7
            r3 = 2131099694(0x7f06002e, float:1.7811748E38)
            int r2 = getColorFromResource(r2, r3)
            r3 = r2
        L_0x02d6:
            r2 = r3
            if (r9 == 0) goto L_0x02e5
            android.widget.TextView r3 = r1.mboundView6
            r56 = r2
            r2 = 2131099707(0x7f06003b, float:1.7811775E38)
            int r3 = getColorFromResource(r3, r2)
            goto L_0x02f1
        L_0x02e5:
            r56 = r2
            android.widget.TextView r2 = r1.mboundView6
            r3 = 2131099694(0x7f06002e, float:1.7811748E38)
            int r2 = getColorFromResource(r2, r3)
            r3 = r2
        L_0x02f1:
            r2 = r3
            if (r21 == 0) goto L_0x0300
            android.widget.TextView r3 = r1.mboundView9
            r76 = r2
            r2 = 2131099707(0x7f06003b, float:1.7811775E38)
            int r3 = getColorFromResource(r3, r2)
            goto L_0x030c
        L_0x0300:
            r76 = r2
            android.widget.TextView r2 = r1.mboundView9
            r3 = 2131099694(0x7f06002e, float:1.7811748E38)
            int r2 = getColorFromResource(r2, r3)
            r3 = r2
        L_0x030c:
            r2 = r3
            if (r10 == 0) goto L_0x031b
            android.widget.TextView r3 = r1.mboundView8
            r85 = r2
            r2 = 2131099707(0x7f06003b, float:1.7811775E38)
            int r3 = getColorFromResource(r3, r2)
            goto L_0x0327
        L_0x031b:
            r85 = r2
            android.widget.TextView r2 = r1.mboundView8
            r3 = 2131099694(0x7f06002e, float:1.7811748E38)
            int r2 = getColorFromResource(r2, r3)
            r3 = r2
        L_0x0327:
            r2 = r3
            if (r11 == 0) goto L_0x0336
            android.widget.TextView r3 = r1.mboundView3
            r73 = r2
            r2 = 2131099707(0x7f06003b, float:1.7811775E38)
            int r3 = getColorFromResource(r3, r2)
            goto L_0x0342
        L_0x0336:
            r73 = r2
            android.widget.TextView r2 = r1.mboundView3
            r3 = 2131099694(0x7f06002e, float:1.7811748E38)
            int r2 = getColorFromResource(r2, r3)
            r3 = r2
        L_0x0342:
            r2 = r3
            if (r13 == 0) goto L_0x0351
            android.widget.TextView r3 = r1.mboundView2
            r89 = r2
            r2 = 2131099707(0x7f06003b, float:1.7811775E38)
            int r3 = getColorFromResource(r3, r2)
            goto L_0x035d
        L_0x0351:
            r89 = r2
            android.widget.TextView r2 = r1.mboundView2
            r3 = 2131099694(0x7f06002e, float:1.7811748E38)
            int r2 = getColorFromResource(r2, r3)
            r3 = r2
        L_0x035d:
            r2 = r3
            if (r14 == 0) goto L_0x036c
            android.widget.TextView r3 = r1.mboundView5
            r83 = r2
            r2 = 2131099707(0x7f06003b, float:1.7811775E38)
            int r3 = getColorFromResource(r3, r2)
            goto L_0x0378
        L_0x036c:
            r83 = r2
            android.widget.TextView r2 = r1.mboundView5
            r3 = 2131099694(0x7f06002e, float:1.7811748E38)
            int r2 = getColorFromResource(r2, r3)
            r3 = r2
        L_0x0378:
            r2 = r3
            r29 = r2
            r100 = r11
            r114 = r13
            r131 = r14
            r96 = r37
            r118 = r56
            r129 = r85
            r147 = r89
            r2 = r163
            r37 = r7
            r56 = r9
            r85 = r10
            r89 = r76
            r76 = r21
            r21 = r8
            goto L_0x03a2
        L_0x0398:
            r158 = r9
            r159 = r10
            r160 = r11
            r161 = r13
            r162 = r14
        L_0x03a2:
            long r7 = r2 & r150
            r9 = 0
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 == 0) goto L_0x05ca
            if (r0 == 0) goto L_0x03af
            android.databinding.ObservableInt r7 = r0.rotateLevel
            goto L_0x03b1
        L_0x03af:
            r7 = r46
        L_0x03b1:
            r8 = 4
            r1.updateRegistration((int) r8, (android.databinding.Observable) r7)
            if (r7 == 0) goto L_0x03be
            int r40 = r7.get()
            r9 = r40
            goto L_0x03c0
        L_0x03be:
            r9 = r40
        L_0x03c0:
            if (r9 != r8) goto L_0x03c4
            r8 = 1
            goto L_0x03c5
        L_0x03c4:
            r8 = 0
        L_0x03c5:
            r10 = 1
            if (r9 != r10) goto L_0x03ca
            r10 = 1
            goto L_0x03cb
        L_0x03ca:
            r10 = 0
        L_0x03cb:
            r11 = 7
            if (r9 != r11) goto L_0x03d0
            r11 = 1
            goto L_0x03d1
        L_0x03d0:
            r11 = 0
        L_0x03d1:
            r13 = 2
            if (r9 != r13) goto L_0x03d6
            r13 = 1
            goto L_0x03d7
        L_0x03d6:
            r13 = 0
        L_0x03d7:
            r14 = 5
            if (r9 != r14) goto L_0x03dc
            r14 = 1
            goto L_0x03dd
        L_0x03dc:
            r14 = 0
        L_0x03dd:
            r46 = r7
            r7 = 8
            if (r9 != r7) goto L_0x03e5
            r7 = 1
            goto L_0x03e6
        L_0x03e5:
            r7 = 0
        L_0x03e6:
            r163 = r12
            r12 = 3
            if (r9 != r12) goto L_0x03ed
            r12 = 1
            goto L_0x03ee
        L_0x03ed:
            r12 = 0
        L_0x03ee:
            if (r9 != 0) goto L_0x03f3
            r36 = 1
            goto L_0x03f5
        L_0x03f3:
            r36 = 0
        L_0x03f5:
            r164 = r15
            r15 = 6
            if (r9 != r15) goto L_0x03fc
            r15 = 1
            goto L_0x03fd
        L_0x03fc:
            r15 = 0
        L_0x03fd:
            long r39 = r2 & r150
            r127 = 0
            int r39 = (r39 > r127 ? 1 : (r39 == r127 ? 0 : -1))
            if (r39 == 0) goto L_0x0412
            if (r8 == 0) goto L_0x040d
            r39 = 33554432(0x2000000, double:1.6578092E-316)
            long r2 = r2 | r39
            goto L_0x0412
        L_0x040d:
            r39 = 16777216(0x1000000, double:8.289046E-317)
            long r2 = r2 | r39
        L_0x0412:
            long r39 = r2 & r150
            r127 = 0
            int r39 = (r39 > r127 ? 1 : (r39 == r127 ? 0 : -1))
            if (r39 == 0) goto L_0x042b
            if (r10 == 0) goto L_0x0424
            r39 = 549755813888(0x8000000000, double:2.716154612436E-312)
            long r2 = r2 | r39
            goto L_0x042b
        L_0x0424:
            r39 = 274877906944(0x4000000000, double:1.358077306218E-312)
            long r2 = r2 | r39
        L_0x042b:
            long r39 = r2 & r150
            r127 = 0
            int r39 = (r39 > r127 ? 1 : (r39 == r127 ? 0 : -1))
            if (r39 == 0) goto L_0x043e
            if (r11 == 0) goto L_0x043a
            r39 = 2305843009213693952(0x2000000000000000, double:1.4916681462400413E-154)
            long r2 = r2 | r39
            goto L_0x043e
        L_0x043a:
            r39 = 1152921504606846976(0x1000000000000000, double:1.2882297539194267E-231)
            long r2 = r2 | r39
        L_0x043e:
            long r39 = r2 & r150
            r127 = 0
            int r39 = (r39 > r127 ? 1 : (r39 == r127 ? 0 : -1))
            if (r39 == 0) goto L_0x0451
            if (r13 == 0) goto L_0x044d
            r39 = 32
            long r4 = r4 | r39
            goto L_0x0451
        L_0x044d:
            r39 = 16
            long r4 = r4 | r39
        L_0x0451:
            long r39 = r2 & r150
            r127 = 0
            int r39 = (r39 > r127 ? 1 : (r39 == r127 ? 0 : -1))
            if (r39 == 0) goto L_0x0464
            if (r14 == 0) goto L_0x0460
            r39 = 2251799813685248(0x8000000000000, double:1.1125369292536007E-308)
            long r2 = r2 | r39
            goto L_0x0464
        L_0x0460:
            r39 = 1125899906842624(0x4000000000000, double:5.562684646268003E-309)
            long r2 = r2 | r39
        L_0x0464:
            long r39 = r2 & r150
            r127 = 0
            int r39 = (r39 > r127 ? 1 : (r39 == r127 ? 0 : -1))
            if (r39 == 0) goto L_0x0477
            if (r7 == 0) goto L_0x0473
            r39 = 128(0x80, double:6.32E-322)
            long r4 = r4 | r39
            goto L_0x0477
        L_0x0473:
            r39 = 64
            long r4 = r4 | r39
        L_0x0477:
            long r39 = r2 & r150
            r127 = 0
            int r39 = (r39 > r127 ? 1 : (r39 == r127 ? 0 : -1))
            if (r39 == 0) goto L_0x048c
            if (r12 == 0) goto L_0x0487
            r39 = 536870912(0x20000000, double:2.652494739E-315)
            long r2 = r2 | r39
            goto L_0x048c
        L_0x0487:
            r39 = 268435456(0x10000000, double:1.32624737E-315)
            long r2 = r2 | r39
        L_0x048c:
            long r39 = r2 & r150
            r127 = 0
            int r39 = (r39 > r127 ? 1 : (r39 == r127 ? 0 : -1))
            if (r39 == 0) goto L_0x04a5
            if (r36 == 0) goto L_0x049e
            r39 = 2199023255552(0x20000000000, double:1.0864618449742E-311)
            long r2 = r2 | r39
            goto L_0x04a5
        L_0x049e:
            r39 = 1099511627776(0x10000000000, double:5.43230922487E-312)
            long r2 = r2 | r39
        L_0x04a5:
            long r39 = r2 & r150
            r127 = 0
            int r39 = (r39 > r127 ? 1 : (r39 == r127 ? 0 : -1))
            if (r39 == 0) goto L_0x04be
            if (r15 == 0) goto L_0x04b7
            r39 = 137438953472(0x2000000000, double:6.7903865311E-313)
            long r2 = r2 | r39
            goto L_0x04be
        L_0x04b7:
            r39 = 68719476736(0x1000000000, double:3.39519326554E-313)
            long r2 = r2 | r39
        L_0x04be:
            if (r8 == 0) goto L_0x04c8
            r39 = r2
            android.widget.TextView r2 = r1.mboundView15
            r3 = 2131099707(0x7f06003b, float:1.7811775E38)
            goto L_0x04cf
        L_0x04c8:
            r39 = r2
            android.widget.TextView r2 = r1.mboundView15
            r3 = 2131099694(0x7f06002e, float:1.7811748E38)
        L_0x04cf:
            int r2 = getColorFromResource(r2, r3)
            if (r10 == 0) goto L_0x04e1
            android.widget.TextView r3 = r1.mboundView18
            r53 = r2
            r2 = 2131099707(0x7f06003b, float:1.7811775E38)
            int r3 = getColorFromResource(r3, r2)
            goto L_0x04ed
        L_0x04e1:
            r53 = r2
            android.widget.TextView r2 = r1.mboundView18
            r3 = 2131099694(0x7f06002e, float:1.7811748E38)
            int r2 = getColorFromResource(r2, r3)
            r3 = r2
        L_0x04ed:
            r2 = r3
            if (r11 == 0) goto L_0x04fc
            android.widget.TextView r3 = r1.mboundView12
            r47 = r2
            r2 = 2131099707(0x7f06003b, float:1.7811775E38)
            int r3 = getColorFromResource(r3, r2)
            goto L_0x0508
        L_0x04fc:
            r47 = r2
            android.widget.TextView r2 = r1.mboundView12
            r3 = 2131099694(0x7f06002e, float:1.7811748E38)
            int r2 = getColorFromResource(r2, r3)
            r3 = r2
        L_0x0508:
            r2 = r3
            if (r13 == 0) goto L_0x0517
            android.widget.TextView r3 = r1.mboundView17
            r70 = r2
            r2 = 2131099707(0x7f06003b, float:1.7811775E38)
            int r3 = getColorFromResource(r3, r2)
            goto L_0x0523
        L_0x0517:
            r70 = r2
            android.widget.TextView r2 = r1.mboundView17
            r3 = 2131099694(0x7f06002e, float:1.7811748E38)
            int r2 = getColorFromResource(r2, r3)
            r3 = r2
        L_0x0523:
            r2 = r3
            if (r14 == 0) goto L_0x0532
            android.widget.TextView r3 = r1.mboundView14
            r84 = r2
            r2 = 2131099707(0x7f06003b, float:1.7811775E38)
            int r3 = getColorFromResource(r3, r2)
            goto L_0x053e
        L_0x0532:
            r84 = r2
            android.widget.TextView r2 = r1.mboundView14
            r3 = 2131099694(0x7f06002e, float:1.7811748E38)
            int r2 = getColorFromResource(r2, r3)
            r3 = r2
        L_0x053e:
            r2 = r3
            if (r7 == 0) goto L_0x054d
            android.widget.TextView r3 = r1.mboundView11
            r77 = r2
            r2 = 2131099707(0x7f06003b, float:1.7811775E38)
            int r3 = getColorFromResource(r3, r2)
            goto L_0x0559
        L_0x054d:
            r77 = r2
            android.widget.TextView r2 = r1.mboundView11
            r3 = 2131099694(0x7f06002e, float:1.7811748E38)
            int r2 = getColorFromResource(r2, r3)
            r3 = r2
        L_0x0559:
            r2 = r3
            if (r12 == 0) goto L_0x0568
            android.widget.TextView r3 = r1.mboundView16
            r92 = r2
            r2 = 2131099707(0x7f06003b, float:1.7811775E38)
            int r3 = getColorFromResource(r3, r2)
            goto L_0x0574
        L_0x0568:
            r92 = r2
            android.widget.TextView r2 = r1.mboundView16
            r3 = 2131099694(0x7f06002e, float:1.7811748E38)
            int r2 = getColorFromResource(r2, r3)
            r3 = r2
        L_0x0574:
            r2 = r3
            if (r36 == 0) goto L_0x0583
            android.widget.TextView r3 = r1.mboundView19
            r19 = r2
            r2 = 2131099707(0x7f06003b, float:1.7811775E38)
            int r3 = getColorFromResource(r3, r2)
            goto L_0x058f
        L_0x0583:
            r19 = r2
            android.widget.TextView r2 = r1.mboundView19
            r3 = 2131099694(0x7f06002e, float:1.7811748E38)
            int r2 = getColorFromResource(r2, r3)
            r3 = r2
        L_0x058f:
            r2 = r3
            if (r15 == 0) goto L_0x059e
            android.widget.TextView r3 = r1.mboundView13
            r49 = r2
            r2 = 2131099707(0x7f06003b, float:1.7811775E38)
            int r2 = getColorFromResource(r3, r2)
            goto L_0x05a9
        L_0x059e:
            r49 = r2
            android.widget.TextView r2 = r1.mboundView13
            r3 = 2131099694(0x7f06002e, float:1.7811748E38)
            int r2 = getColorFromResource(r2, r3)
        L_0x05a9:
            r38 = r2
            r107 = r12
            r123 = r15
            r109 = r36
            r2 = r39
            r101 = r70
            r119 = r84
            r122 = r92
            r92 = r7
            r36 = r8
            r40 = r9
            r39 = r10
            r70 = r13
            r84 = r14
            r14 = r53
            r53 = r11
            goto L_0x05d0
        L_0x05ca:
            r163 = r12
            r164 = r15
            r14 = r162
        L_0x05d0:
            long r7 = r2 & r141
            r9 = 0
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 == 0) goto L_0x0613
            if (r0 == 0) goto L_0x05dd
            android.databinding.ObservableField<java.lang.Boolean> r7 = r0.rrDoorState
            goto L_0x05df
        L_0x05dd:
            r7 = r54
        L_0x05df:
            r8 = 5
            r1.updateRegistration((int) r8, (android.databinding.Observable) r7)
            if (r7 == 0) goto L_0x05ed
            java.lang.Object r8 = r7.get()
            r98 = r8
            java.lang.Boolean r98 = (java.lang.Boolean) r98
        L_0x05ed:
            boolean r8 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r98)
            long r9 = r2 & r141
            r11 = 0
            int r9 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r9 == 0) goto L_0x0608
            if (r8 == 0) goto L_0x0602
            r9 = 34359738368(0x800000000, double:1.69759663277E-313)
            long r2 = r2 | r9
            goto L_0x0608
        L_0x0602:
            r9 = 17179869184(0x400000000, double:8.4879831639E-314)
            long r2 = r2 | r9
        L_0x0608:
            if (r8 == 0) goto L_0x060c
            r9 = 0
            goto L_0x060d
        L_0x060c:
            r9 = 4
        L_0x060d:
            r54 = r7
            r24 = r8
            r31 = r9
        L_0x0613:
            r7 = 524288(0x80000, double:2.590327E-318)
            long r7 = r7 & r2
            r9 = 0
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 == 0) goto L_0x06e9
            if (r0 == 0) goto L_0x0624
            int[] r7 = r0.rotateArray
            int[] r8 = r0.speedArray
            goto L_0x0628
        L_0x0624:
            r7 = r58
            r8 = r60
        L_0x0628:
            if (r7 == 0) goto L_0x0659
            r9 = 5
            int r13 = getFromArray((int[]) r7, (int) r9)
            r9 = 2
            int r15 = getFromArray((int[]) r7, (int) r9)
            r9 = 8
            int r30 = getFromArray((int[]) r7, (int) r9)
            r9 = 0
            int r52 = getFromArray((int[]) r7, (int) r9)
            r9 = 3
            int r55 = getFromArray((int[]) r7, (int) r9)
            r9 = 6
            int r69 = getFromArray((int[]) r7, (int) r9)
            r9 = 1
            int r93 = getFromArray((int[]) r7, (int) r9)
            r9 = 4
            int r94 = getFromArray((int[]) r7, (int) r9)
            r9 = 7
            int r105 = getFromArray((int[]) r7, (int) r9)
            goto L_0x065d
        L_0x0659:
            r13 = r161
            r15 = r164
        L_0x065d:
            if (r8 == 0) goto L_0x0688
            r9 = 6
            int r20 = getFromArray((int[]) r8, (int) r9)
            r9 = 3
            int r44 = getFromArray((int[]) r8, (int) r9)
            r9 = 0
            int r48 = getFromArray((int[]) r8, (int) r9)
            r10 = 5
            int r75 = getFromArray((int[]) r8, (int) r10)
            r10 = 2
            int r80 = getFromArray((int[]) r8, (int) r10)
            r10 = 7
            int r99 = getFromArray((int[]) r8, (int) r10)
            r10 = 4
            int r112 = getFromArray((int[]) r8, (int) r10)
            r11 = 1
            int r115 = getFromArray((int[]) r8, (int) r11)
            goto L_0x068a
        L_0x0688:
            r9 = 0
            r10 = 4
        L_0x068a:
            java.lang.String r11 = java.lang.String.valueOf(r13)
            java.lang.String r12 = java.lang.String.valueOf(r15)
            java.lang.String r35 = java.lang.String.valueOf(r30)
            java.lang.String r42 = java.lang.String.valueOf(r52)
            java.lang.String r43 = java.lang.String.valueOf(r55)
            java.lang.String r57 = java.lang.String.valueOf(r69)
            java.lang.String r58 = java.lang.String.valueOf(r93)
            java.lang.String r60 = java.lang.String.valueOf(r94)
            java.lang.String r66 = java.lang.String.valueOf(r105)
            java.lang.String r23 = java.lang.String.valueOf(r20)
            java.lang.String r71 = java.lang.String.valueOf(r44)
            java.lang.String r34 = java.lang.String.valueOf(r48)
            java.lang.String r72 = java.lang.String.valueOf(r75)
            java.lang.String r78 = java.lang.String.valueOf(r80)
            java.lang.String r81 = java.lang.String.valueOf(r99)
            java.lang.String r88 = java.lang.String.valueOf(r112)
            java.lang.String r61 = java.lang.String.valueOf(r115)
            r116 = r43
            r158 = r71
            r125 = r72
            r97 = r81
            r91 = r88
            r43 = r12
            r71 = r42
            r88 = r58
            r81 = r60
            r72 = r66
            r58 = r7
            r60 = r8
            r42 = r11
            goto L_0x06ef
        L_0x06e9:
            r9 = 0
            r10 = 4
            r13 = r161
            r15 = r164
        L_0x06ef:
            r7 = 524352(0x80040, double:2.590643E-318)
            long r7 = r7 & r2
            r11 = 0
            int r7 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r7 == 0) goto L_0x0713
            if (r0 == 0) goto L_0x06fe
            android.databinding.ObservableField<java.lang.String> r7 = r0.tempStr
            goto L_0x0700
        L_0x06fe:
            r7 = r59
        L_0x0700:
            r8 = 6
            r1.updateRegistration((int) r8, (android.databinding.Observable) r7)
            if (r7 == 0) goto L_0x0711
            java.lang.Object r8 = r7.get()
            java.lang.String r8 = (java.lang.String) r8
            r59 = r7
            r110 = r8
            goto L_0x0713
        L_0x0711:
            r59 = r7
        L_0x0713:
            long r7 = r2 & r139
            r11 = 0
            int r7 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r7 == 0) goto L_0x0762
            if (r0 == 0) goto L_0x0720
            android.databinding.ObservableField<java.lang.Boolean> r7 = r0.seatBeltpValue
            goto L_0x0722
        L_0x0720:
            r7 = r63
        L_0x0722:
            r8 = 7
            r1.updateRegistration((int) r8, (android.databinding.Observable) r7)
            if (r7 == 0) goto L_0x0730
            java.lang.Object r8 = r7.get()
            r90 = r8
            java.lang.Boolean r90 = (java.lang.Boolean) r90
        L_0x0730:
            boolean r8 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r90)
            long r11 = r2 & r139
            r127 = 0
            int r11 = (r11 > r127 ? 1 : (r11 == r127 ? 0 : -1))
            if (r11 == 0) goto L_0x0749
            if (r8 == 0) goto L_0x0745
            r11 = 2147483648(0x80000000, double:1.0609978955E-314)
            long r2 = r2 | r11
            goto L_0x0749
        L_0x0745:
            r11 = 1073741824(0x40000000, double:5.304989477E-315)
            long r2 = r2 | r11
        L_0x0749:
            android.widget.ImageView r11 = r1.mboundView26
            android.content.Context r11 = r11.getContext()
            if (r8 == 0) goto L_0x0755
            r12 = 2131233064(0x7f080928, float:1.8082255E38)
            goto L_0x0758
        L_0x0755:
            r12 = 2131233065(0x7f080929, float:1.8082257E38)
        L_0x0758:
            android.graphics.drawable.Drawable r11 = android.support.v7.content.res.AppCompatResources.getDrawable(r11, r12)
            r63 = r7
            r95 = r8
            r27 = r11
        L_0x0762:
            r7 = 524544(0x80100, double:2.59159E-318)
            long r7 = r7 & r2
            r11 = 0
            int r7 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r7 == 0) goto L_0x0788
            if (r0 == 0) goto L_0x0771
            android.databinding.ObservableField<java.lang.String> r7 = r0.speedUnit
            goto L_0x0773
        L_0x0771:
            r7 = r65
        L_0x0773:
            r8 = 8
            r1.updateRegistration((int) r8, (android.databinding.Observable) r7)
            if (r7 == 0) goto L_0x0785
            java.lang.Object r11 = r7.get()
            java.lang.String r11 = (java.lang.String) r11
            r65 = r7
            r32 = r11
            goto L_0x078a
        L_0x0785:
            r65 = r7
            goto L_0x078a
        L_0x0788:
            r8 = 8
        L_0x078a:
            r11 = 525312(0x80400, double:2.595386E-318)
            long r11 = r11 & r2
            r127 = 0
            int r7 = (r11 > r127 ? 1 : (r11 == r127 ? 0 : -1))
            if (r7 == 0) goto L_0x07ad
            if (r0 == 0) goto L_0x0799
            android.databinding.ObservableInt r7 = r0.turnSpeed
            goto L_0x079b
        L_0x0799:
            r7 = r79
        L_0x079b:
            r11 = 10
            r1.updateRegistration((int) r11, (android.databinding.Observable) r7)
            if (r7 == 0) goto L_0x07ab
            int r11 = r7.get()
            r79 = r7
            r103 = r11
            goto L_0x07ad
        L_0x07ab:
            r79 = r7
        L_0x07ad:
            r11 = 526336(0x80800, double:2.600445E-318)
            long r11 = r11 & r2
            r127 = 0
            int r7 = (r11 > r127 ? 1 : (r11 == r127 ? 0 : -1))
            if (r7 == 0) goto L_0x07d2
            if (r0 == 0) goto L_0x07bc
            android.databinding.ObservableField<java.lang.String> r7 = r0.oilValue
            goto L_0x07be
        L_0x07bc:
            r7 = r102
        L_0x07be:
            r11 = 11
            r1.updateRegistration((int) r11, (android.databinding.Observable) r7)
            if (r7 == 0) goto L_0x07d0
            java.lang.Object r11 = r7.get()
            java.lang.String r11 = (java.lang.String) r11
            r102 = r7
            r26 = r11
            goto L_0x07d2
        L_0x07d0:
            r102 = r7
        L_0x07d2:
            long r11 = r2 & r137
            r127 = 0
            int r7 = (r11 > r127 ? 1 : (r11 == r127 ? 0 : -1))
            if (r7 == 0) goto L_0x0818
            if (r0 == 0) goto L_0x07df
            android.databinding.ObservableField<java.lang.Boolean> r7 = r0.rlDoorState
            goto L_0x07e1
        L_0x07df:
            r7 = r106
        L_0x07e1:
            r11 = 13
            r1.updateRegistration((int) r11, (android.databinding.Observable) r7)
            if (r7 == 0) goto L_0x07f0
            java.lang.Object r11 = r7.get()
            r28 = r11
            java.lang.Boolean r28 = (java.lang.Boolean) r28
        L_0x07f0:
            boolean r11 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r28)
            long r156 = r2 & r137
            r127 = 0
            int r12 = (r156 > r127 ? 1 : (r156 == r127 ? 0 : -1))
            if (r12 == 0) goto L_0x080d
            if (r11 == 0) goto L_0x0806
            r156 = 35184372088832(0x200000000000, double:1.73833895195875E-310)
            long r2 = r2 | r156
            goto L_0x080d
        L_0x0806:
            r156 = 17592186044416(0x100000000000, double:8.6916947597938E-311)
            long r2 = r2 | r156
        L_0x080d:
            if (r11 == 0) goto L_0x0811
            r12 = r9
            goto L_0x0812
        L_0x0811:
            r12 = r10
        L_0x0812:
            r106 = r7
            r22 = r11
            r62 = r12
        L_0x0818:
            long r11 = r2 & r133
            r127 = 0
            int r7 = (r11 > r127 ? 1 : (r11 == r127 ? 0 : -1))
            if (r7 == 0) goto L_0x0858
            if (r0 == 0) goto L_0x0825
            android.databinding.ObservableField<java.lang.Boolean> r7 = r0.flDoorState
            goto L_0x0827
        L_0x0825:
            r7 = r124
        L_0x0827:
            r11 = 14
            r1.updateRegistration((int) r11, (android.databinding.Observable) r7)
            if (r7 == 0) goto L_0x0836
            java.lang.Object r11 = r7.get()
            r108 = r11
            java.lang.Boolean r108 = (java.lang.Boolean) r108
        L_0x0836:
            boolean r11 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r108)
            long r156 = r2 & r133
            r127 = 0
            int r12 = (r156 > r127 ? 1 : (r156 == r127 ? 0 : -1))
            if (r12 == 0) goto L_0x084d
            if (r11 == 0) goto L_0x0849
            r156 = -9223372036854775808
            long r2 = r2 | r156
            goto L_0x084d
        L_0x0849:
            r156 = 4611686018427387904(0x4000000000000000, double:2.0)
            long r2 = r2 | r156
        L_0x084d:
            if (r11 == 0) goto L_0x0851
            r12 = r9
            goto L_0x0852
        L_0x0851:
            r12 = r10
        L_0x0852:
            r124 = r7
            r33 = r11
            r113 = r12
        L_0x0858:
            r11 = 557056(0x88000, double:2.75222E-318)
            long r11 = r11 & r2
            r127 = 0
            int r7 = (r11 > r127 ? 1 : (r11 == r127 ? 0 : -1))
            if (r7 == 0) goto L_0x087c
            if (r0 == 0) goto L_0x0867
            android.databinding.ObservableInt r7 = r0.speed
            goto L_0x0869
        L_0x0867:
            r7 = r126
        L_0x0869:
            r11 = 15
            r1.updateRegistration((int) r11, (android.databinding.Observable) r7)
            if (r7 == 0) goto L_0x0874
            int r86 = r7.get()
        L_0x0874:
            java.lang.String r11 = java.lang.String.valueOf(r86)
            r126 = r7
            r41 = r11
        L_0x087c:
            r11 = 589824(0x90000, double:2.91412E-318)
            long r11 = r11 & r2
            r127 = 0
            int r7 = (r11 > r127 ? 1 : (r11 == r127 ? 0 : -1))
            if (r7 == 0) goto L_0x08c0
            if (r0 == 0) goto L_0x088b
            android.databinding.ObservableField<java.lang.Boolean> r7 = r0.bDoorState
            goto L_0x088d
        L_0x088b:
            r7 = r153
        L_0x088d:
            r11 = 16
            r1.updateRegistration((int) r11, (android.databinding.Observable) r7)
            if (r7 == 0) goto L_0x089c
            java.lang.Object r11 = r7.get()
            r82 = r11
            java.lang.Boolean r82 = (java.lang.Boolean) r82
        L_0x089c:
            boolean r11 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r82)
            r152 = 589824(0x90000, double:2.91412E-318)
            long r152 = r2 & r152
            r127 = 0
            int r12 = (r152 > r127 ? 1 : (r152 == r127 ? 0 : -1))
            if (r12 == 0) goto L_0x08b8
            if (r11 == 0) goto L_0x08b3
            r152 = 134217728(0x8000000, double:6.63123685E-316)
            long r2 = r2 | r152
            goto L_0x08b8
        L_0x08b3:
            r152 = 67108864(0x4000000, double:3.31561842E-316)
            long r2 = r2 | r152
        L_0x08b8:
            if (r11 == 0) goto L_0x08bb
            r10 = r9
        L_0x08bb:
            r17 = r10
            r155 = r11
            goto L_0x08c2
        L_0x08c0:
            r7 = r153
        L_0x08c2:
            long r10 = r2 & r135
            r127 = 0
            int r10 = (r10 > r127 ? 1 : (r10 == r127 ? 0 : -1))
            if (r10 == 0) goto L_0x098a
            if (r0 == 0) goto L_0x08cf
            android.databinding.ObservableField<java.lang.Boolean> r10 = r0.brakeValue
            goto L_0x08d1
        L_0x08cf:
            r10 = r130
        L_0x08d1:
            r11 = 17
            r1.updateRegistration((int) r11, (android.databinding.Observable) r10)
            if (r10 == 0) goto L_0x08e0
            java.lang.Object r11 = r10.get()
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            r111 = r11
        L_0x08e0:
            boolean r11 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r111)
            long r152 = r2 & r135
            r127 = 0
            int r12 = (r152 > r127 ? 1 : (r152 == r127 ? 0 : -1))
            if (r12 == 0) goto L_0x08f9
            if (r11 == 0) goto L_0x08f4
            r152 = 8388608(0x800000, double:4.144523E-317)
            long r2 = r2 | r152
            goto L_0x08f9
        L_0x08f4:
            r152 = 4194304(0x400000, double:2.0722615E-317)
            long r2 = r2 | r152
        L_0x08f9:
            if (r11 == 0) goto L_0x0909
            android.widget.ImageView r12 = r1.mboundView27
            android.content.Context r12 = r12.getContext()
            r8 = 2131233059(0x7f080923, float:1.8082245E38)
            android.graphics.drawable.Drawable r8 = android.support.v7.content.res.AppCompatResources.getDrawable(r12, r8)
            goto L_0x0916
        L_0x0909:
            android.widget.ImageView r8 = r1.mboundView27
            android.content.Context r8 = r8.getContext()
            r12 = 2131233058(0x7f080922, float:1.8082243E38)
            android.graphics.drawable.Drawable r8 = android.support.v7.content.res.AppCompatResources.getDrawable(r8, r12)
        L_0x0916:
            r66 = r0
            r130 = r10
            r159 = r11
            r0 = r57
            r165 = r62
            r166 = r73
            r167 = r74
            r168 = r77
            r74 = r78
            r9 = r81
            r169 = r83
            r12 = r86
            r11 = r88
            r170 = r89
            r78 = r91
            r171 = r96
            r83 = r97
            r172 = r101
            r173 = r110
            r174 = r113
            r10 = r116
            r175 = r117
            r176 = r118
            r177 = r119
            r178 = r122
            r179 = r125
            r180 = r129
            r77 = r158
            r62 = r14
            r81 = r23
            r57 = r47
            r73 = r61
            r14 = r72
            r47 = r147
            r23 = r7
            r61 = r19
            r72 = r34
            r7 = r42
            r34 = r31
            r42 = r32
            r31 = r15
            r32 = r17
            r17 = r20
            r15 = r43
            r19 = r4
            r43 = r29
            r4 = r71
            r5 = r103
            r29 = r13
            r13 = r35
            r71 = r38
            r35 = r8
            r38 = r27
            r8 = r41
            r27 = r163
            r41 = r26
            r26 = r155
            goto L_0x0a77
        L_0x098a:
            r66 = r0
            r8 = r41
            r0 = r57
            r165 = r62
            r166 = r73
            r167 = r74
            r168 = r77
            r74 = r78
            r9 = r81
            r169 = r83
            r12 = r86
            r11 = r88
            r170 = r89
            r78 = r91
            r171 = r96
            r83 = r97
            r172 = r101
            r173 = r110
            r174 = r113
            r10 = r116
            r175 = r117
            r176 = r118
            r177 = r119
            r178 = r122
            r179 = r125
            r180 = r129
            r77 = r158
            r62 = r14
            r81 = r23
            r41 = r26
            r57 = r47
            r73 = r61
            r14 = r72
            r47 = r147
            r26 = r155
            r23 = r7
            r61 = r19
            r72 = r34
            r7 = r42
            r34 = r31
            r42 = r32
            r31 = r15
            r32 = r17
            r17 = r20
            r15 = r43
            r19 = r4
            r43 = r29
            r4 = r71
            r5 = r103
            r29 = r13
            r13 = r35
            r71 = r38
            r35 = r160
            r38 = r27
            r27 = r163
            goto L_0x0a77
        L_0x09fa:
            r153 = r7
            r155 = r8
            r158 = r9
            r159 = r10
            r160 = r11
            r161 = r13
            r162 = r14
            r164 = r15
            r9 = 0
            r13 = r35
            r8 = r41
            r7 = r42
            r15 = r43
            r0 = r57
            r165 = r62
            r14 = r72
            r166 = r73
            r167 = r74
            r168 = r77
            r74 = r78
            r9 = r81
            r169 = r83
            r11 = r88
            r170 = r89
            r78 = r91
            r171 = r96
            r83 = r97
            r172 = r101
            r173 = r110
            r174 = r113
            r10 = r116
            r175 = r117
            r176 = r118
            r177 = r119
            r178 = r122
            r179 = r125
            r180 = r129
            r77 = r158
            r35 = r160
            r62 = r162
            r81 = r23
            r41 = r26
            r43 = r29
            r42 = r32
            r72 = r34
            r57 = r47
            r73 = r61
            r47 = r147
            r23 = r153
            r26 = r155
            r29 = r161
            r32 = r17
            r61 = r19
            r17 = r20
            r34 = r31
            r31 = r164
            r19 = r4
            r4 = r71
            r5 = r103
            r71 = r38
            r38 = r27
            r27 = r12
            r12 = r86
        L_0x0a77:
            r88 = 790528(0xc1000, double:3.905727E-318)
            long r96 = r2 & r88
            r116 = 0
            int r86 = (r96 > r116 ? 1 : (r96 == r116 ? 0 : -1))
            if (r86 == 0) goto L_0x0b10
            r96 = 786432(0xc0000, double:3.88549E-318)
            long r96 = r2 & r96
            int r86 = (r96 > r116 ? 1 : (r96 == r116 ? 0 : -1))
            if (r86 == 0) goto L_0x0ab1
            if (r6 == 0) goto L_0x0aae
            r86 = r4
            com.wits.ksw.databinding.BmwId8DashboardLayoutBindingImpl$OnClickListenerImpl r4 = r1.mViewModelDashboardWeatherLayAndroidViewViewOnClickListener
            if (r4 != 0) goto L_0x0a9a
            com.wits.ksw.databinding.BmwId8DashboardLayoutBindingImpl$OnClickListenerImpl r4 = new com.wits.ksw.databinding.BmwId8DashboardLayoutBindingImpl$OnClickListenerImpl
            r4.<init>()
            r1.mViewModelDashboardWeatherLayAndroidViewViewOnClickListener = r4
        L_0x0a9a:
            com.wits.ksw.databinding.BmwId8DashboardLayoutBindingImpl$OnClickListenerImpl r16 = r4.setValue(r6)
            com.wits.ksw.databinding.BmwId8DashboardLayoutBindingImpl$OnClickListenerImpl1 r4 = r1.mViewModelDashboardMusicLayAndroidViewViewOnClickListener
            if (r4 != 0) goto L_0x0aa9
            com.wits.ksw.databinding.BmwId8DashboardLayoutBindingImpl$OnClickListenerImpl1 r4 = new com.wits.ksw.databinding.BmwId8DashboardLayoutBindingImpl$OnClickListenerImpl1
            r4.<init>()
            r1.mViewModelDashboardMusicLayAndroidViewViewOnClickListener = r4
        L_0x0aa9:
            com.wits.ksw.databinding.BmwId8DashboardLayoutBindingImpl$OnClickListenerImpl1 r51 = r4.setValue(r6)
            goto L_0x0ab3
        L_0x0aae:
            r86 = r4
            goto L_0x0ab3
        L_0x0ab1:
            r86 = r4
        L_0x0ab3:
            if (r6 == 0) goto L_0x0ab8
            android.databinding.ObservableField r4 = r6.dashBoardMusicShow
            goto L_0x0aba
        L_0x0ab8:
            r4 = r104
        L_0x0aba:
            r91 = r11
            r11 = 12
            r1.updateRegistration((int) r11, (android.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0acb
            java.lang.Object r11 = r4.get()
            r120 = r11
            java.lang.Boolean r120 = (java.lang.Boolean) r120
        L_0x0acb:
            boolean r64 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r120)
            long r96 = r2 & r88
            r103 = 0
            int r11 = (r96 > r103 ? 1 : (r96 == r103 ? 0 : -1))
            if (r11 == 0) goto L_0x0af0
            if (r64 == 0) goto L_0x0ae5
            r96 = 8796093022208(0x80000000000, double:4.345847379897E-311)
            long r2 = r2 | r96
            r96 = 36028797018963968(0x80000000000000, double:2.8480945388892178E-306)
            long r2 = r2 | r96
            goto L_0x0af0
        L_0x0ae5:
            r96 = 4398046511104(0x40000000000, double:2.1729236899484E-311)
            long r2 = r2 | r96
            r96 = 18014398509481984(0x40000000000000, double:1.7800590868057611E-307)
            long r2 = r2 | r96
        L_0x0af0:
            if (r64 == 0) goto L_0x0af5
            r11 = 8
            goto L_0x0af6
        L_0x0af5:
            r11 = 0
        L_0x0af6:
            r50 = r11
            if (r64 == 0) goto L_0x0afd
            r132 = 0
            goto L_0x0aff
        L_0x0afd:
            r132 = 8
        L_0x0aff:
            r87 = r132
            r104 = r4
            r4 = r16
            r11 = r50
            r50 = r10
            r16 = r15
            r15 = r51
            r10 = r87
            goto L_0x0b20
        L_0x0b10:
            r86 = r4
            r91 = r11
            r4 = r16
            r11 = r50
            r50 = r10
            r16 = r15
            r15 = r51
            r10 = r87
        L_0x0b20:
            r96 = 786432(0xc0000, double:3.88549E-318)
            long r96 = r2 & r96
            r116 = 0
            int r51 = (r96 > r116 ? 1 : (r96 == r116 ? 0 : -1))
            if (r51 == 0) goto L_0x0b4a
            r51 = r9
            com.wits.ksw.databinding.BmwId8DashboardMusicLayoutBinding r9 = r1.bmwId8DashboardMusicLay
            android.view.View r9 = r9.getRoot()
            r9.setOnClickListener(r15)
            com.wits.ksw.databinding.BmwId8DashboardMusicLayoutBinding r9 = r1.bmwId8DashboardMusicLay
            r9.setViewModel(r6)
            com.wits.ksw.databinding.BmwId8DashboardWeatherLayoutBinding r9 = r1.bmwId8DashboardWeatherLay
            r9.setViewModel(r6)
            com.wits.ksw.databinding.BmwId8DashboardWeatherLayoutBinding r9 = r1.bmwId8DashboardWeatherLay
            android.view.View r9 = r9.getRoot()
            r9.setOnClickListener(r4)
            goto L_0x0b4c
        L_0x0b4a:
            r51 = r9
        L_0x0b4c:
            long r87 = r2 & r88
            r96 = 0
            int r9 = (r87 > r96 ? 1 : (r87 == r96 ? 0 : -1))
            if (r9 == 0) goto L_0x0b66
            com.wits.ksw.databinding.BmwId8DashboardMusicLayoutBinding r9 = r1.bmwId8DashboardMusicLay
            android.view.View r9 = r9.getRoot()
            r9.setVisibility(r10)
            com.wits.ksw.databinding.BmwId8DashboardWeatherLayoutBinding r9 = r1.bmwId8DashboardWeatherLay
            android.view.View r9 = r9.getRoot()
            r9.setVisibility(r11)
        L_0x0b66:
            r87 = 525312(0x80400, double:2.595386E-318)
            long r87 = r2 & r87
            r96 = 0
            int r9 = (r87 > r96 ? 1 : (r87 == r96 ? 0 : -1))
            if (r9 == 0) goto L_0x0b7b
            com.wits.ksw.launcher.view.LinearGradientProgress r9 = r1.bmwId8DashboardRotateProgress
            r9.setScale(r5)
            android.widget.TextView r9 = r1.mboundView23
            com.wits.ksw.launcher.base.BaseBindingModel.setTurnSpeedStr(r9, r5)
        L_0x0b7b:
            r87 = 557056(0x88000, double:2.75222E-318)
            long r87 = r2 & r87
            r96 = 0
            int r9 = (r87 > r96 ? 1 : (r87 == r96 ? 0 : -1))
            if (r9 == 0) goto L_0x0b90
            com.wits.ksw.launcher.view.LinearGradientProgress r9 = r1.bmwId8DashboardSpeedProgress
            r9.setScale(r12)
            android.widget.TextView r9 = r1.mboundView21
            android.databinding.adapters.TextViewBindingAdapter.setText(r9, r8)
        L_0x0b90:
            r87 = 524288(0x80000, double:2.590327E-318)
            long r87 = r2 & r87
            r96 = 0
            int r9 = (r87 > r96 ? 1 : (r87 == r96 ? 0 : -1))
            if (r9 == 0) goto L_0x0c0f
            android.widget.TextView r9 = r1.mboundView11
            android.databinding.adapters.TextViewBindingAdapter.setText(r9, r13)
            android.widget.TextView r9 = r1.mboundView12
            android.databinding.adapters.TextViewBindingAdapter.setText(r9, r14)
            android.widget.TextView r9 = r1.mboundView13
            android.databinding.adapters.TextViewBindingAdapter.setText(r9, r0)
            android.widget.TextView r9 = r1.mboundView14
            android.databinding.adapters.TextViewBindingAdapter.setText(r9, r7)
            android.widget.TextView r9 = r1.mboundView15
            r87 = r0
            r0 = r51
            android.databinding.adapters.TextViewBindingAdapter.setText(r9, r0)
            android.widget.TextView r9 = r1.mboundView16
            r0 = r50
            android.databinding.adapters.TextViewBindingAdapter.setText(r9, r0)
            android.widget.TextView r9 = r1.mboundView17
            r0 = r16
            android.databinding.adapters.TextViewBindingAdapter.setText(r9, r0)
            android.widget.TextView r9 = r1.mboundView18
            r0 = r91
            android.databinding.adapters.TextViewBindingAdapter.setText(r9, r0)
            android.widget.TextView r9 = r1.mboundView19
            r0 = r86
            android.databinding.adapters.TextViewBindingAdapter.setText(r9, r0)
            android.widget.TextView r9 = r1.mboundView2
            r0 = r83
            android.databinding.adapters.TextViewBindingAdapter.setText(r9, r0)
            android.widget.TextView r9 = r1.mboundView3
            r0 = r81
            android.databinding.adapters.TextViewBindingAdapter.setText(r9, r0)
            android.widget.TextView r9 = r1.mboundView4
            r0 = r179
            android.databinding.adapters.TextViewBindingAdapter.setText(r9, r0)
            android.widget.TextView r9 = r1.mboundView5
            r88 = r0
            r0 = r78
            android.databinding.adapters.TextViewBindingAdapter.setText(r9, r0)
            android.widget.TextView r9 = r1.mboundView6
            r0 = r77
            android.databinding.adapters.TextViewBindingAdapter.setText(r9, r0)
            android.widget.TextView r9 = r1.mboundView7
            r0 = r74
            android.databinding.adapters.TextViewBindingAdapter.setText(r9, r0)
            android.widget.TextView r9 = r1.mboundView8
            r0 = r73
            android.databinding.adapters.TextViewBindingAdapter.setText(r9, r0)
            android.widget.TextView r9 = r1.mboundView9
            r0 = r72
            android.databinding.adapters.TextViewBindingAdapter.setText(r9, r0)
            goto L_0x0c15
        L_0x0c0f:
            r87 = r0
            r0 = r72
            r88 = r179
        L_0x0c15:
            long r96 = r2 & r150
            r116 = 0
            int r9 = (r96 > r116 ? 1 : (r96 == r116 ? 0 : -1))
            if (r9 == 0) goto L_0x0c67
            android.widget.TextView r9 = r1.mboundView11
            r72 = r0
            r0 = r178
            r9.setTextColor(r0)
            android.widget.TextView r9 = r1.mboundView12
            r89 = r0
            r0 = r172
            r9.setTextColor(r0)
            android.widget.TextView r9 = r1.mboundView13
            r96 = r0
            r0 = r71
            r9.setTextColor(r0)
            android.widget.TextView r9 = r1.mboundView14
            r0 = r168
            r9.setTextColor(r0)
            android.widget.TextView r9 = r1.mboundView15
            r97 = r0
            r0 = r62
            r9.setTextColor(r0)
            android.widget.TextView r9 = r1.mboundView16
            r0 = r61
            r9.setTextColor(r0)
            android.widget.TextView r9 = r1.mboundView17
            r0 = r177
            r9.setTextColor(r0)
            android.widget.TextView r9 = r1.mboundView18
            r101 = r0
            r0 = r57
            r9.setTextColor(r0)
            android.widget.TextView r9 = r1.mboundView19
            r0 = r49
            r9.setTextColor(r0)
            goto L_0x0c73
        L_0x0c67:
            r72 = r0
            r0 = r49
            r97 = r168
            r96 = r172
            r101 = r177
            r89 = r178
        L_0x0c73:
            long r116 = r2 & r148
            r118 = 0
            int r9 = (r116 > r118 ? 1 : (r116 == r118 ? 0 : -1))
            if (r9 == 0) goto L_0x0cc0
            android.widget.TextView r9 = r1.mboundView2
            r49 = r0
            r0 = r169
            r9.setTextColor(r0)
            android.widget.TextView r9 = r1.mboundView3
            r103 = r0
            r0 = r47
            r9.setTextColor(r0)
            android.widget.TextView r9 = r1.mboundView4
            r0 = r171
            r9.setTextColor(r0)
            android.widget.TextView r9 = r1.mboundView5
            r110 = r0
            r0 = r43
            r9.setTextColor(r0)
            android.widget.TextView r9 = r1.mboundView6
            r0 = r170
            r9.setTextColor(r0)
            android.widget.TextView r9 = r1.mboundView7
            r113 = r0
            r0 = r176
            r9.setTextColor(r0)
            android.widget.TextView r9 = r1.mboundView8
            r116 = r0
            r0 = r166
            r9.setTextColor(r0)
            android.widget.TextView r9 = r1.mboundView9
            r117 = r0
            r0 = r180
            r9.setTextColor(r0)
            goto L_0x0cce
        L_0x0cc0:
            r49 = r0
            r117 = r166
            r103 = r169
            r113 = r170
            r110 = r171
            r116 = r176
            r0 = r180
        L_0x0cce:
            r118 = 524544(0x80100, double:2.59159E-318)
            long r118 = r2 & r118
            r127 = 0
            int r9 = (r118 > r127 ? 1 : (r118 == r127 ? 0 : -1))
            if (r9 == 0) goto L_0x0ce8
            android.widget.TextView r9 = r1.mboundView20
            r118 = r0
            r0 = r42
            android.databinding.adapters.TextViewBindingAdapter.setText(r9, r0)
            android.widget.TextView r9 = r1.mboundView22
            android.databinding.adapters.TextViewBindingAdapter.setText(r9, r0)
            goto L_0x0cec
        L_0x0ce8:
            r118 = r0
            r0 = r42
        L_0x0cec:
            r147 = 526336(0x80800, double:2.600445E-318)
            long r147 = r2 & r147
            r127 = 0
            int r9 = (r147 > r127 ? 1 : (r147 == r127 ? 0 : -1))
            if (r9 == 0) goto L_0x0d01
            android.widget.TextView r9 = r1.mboundView24
            r42 = r0
            r0 = r41
            android.databinding.adapters.TextViewBindingAdapter.setText(r9, r0)
            goto L_0x0d05
        L_0x0d01:
            r42 = r0
            r0 = r41
        L_0x0d05:
            r147 = 524352(0x80040, double:2.590643E-318)
            long r147 = r2 & r147
            int r9 = (r147 > r127 ? 1 : (r147 == r127 ? 0 : -1))
            if (r9 == 0) goto L_0x0d18
            android.widget.TextView r9 = r1.mboundView25
            r41 = r0
            r0 = r173
            android.databinding.adapters.TextViewBindingAdapter.setText(r9, r0)
            goto L_0x0d1c
        L_0x0d18:
            r41 = r0
            r0 = r173
        L_0x0d1c:
            long r139 = r2 & r139
            int r9 = (r139 > r127 ? 1 : (r139 == r127 ? 0 : -1))
            if (r9 == 0) goto L_0x0d2c
            android.widget.ImageView r9 = r1.mboundView26
            r119 = r0
            r0 = r38
            android.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(r9, r0)
            goto L_0x0d30
        L_0x0d2c:
            r119 = r0
            r0 = r38
        L_0x0d30:
            long r135 = r2 & r135
            int r9 = (r135 > r127 ? 1 : (r135 == r127 ? 0 : -1))
            if (r9 == 0) goto L_0x0d40
            android.widget.ImageView r9 = r1.mboundView27
            r38 = r0
            r0 = r35
            android.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(r9, r0)
            goto L_0x0d44
        L_0x0d40:
            r38 = r0
            r0 = r35
        L_0x0d44:
            long r135 = r2 & r145
            int r9 = (r135 > r127 ? 1 : (r135 == r127 ? 0 : -1))
            if (r9 == 0) goto L_0x0d54
            android.widget.RelativeLayout r9 = r1.mboundView28
            r35 = r0
            r0 = r167
            r9.setVisibility(r0)
            goto L_0x0d58
        L_0x0d54:
            r35 = r0
            r0 = r167
        L_0x0d58:
            long r135 = r2 & r143
            int r9 = (r135 > r127 ? 1 : (r135 == r127 ? 0 : -1))
            if (r9 == 0) goto L_0x0d68
            android.widget.ImageView r9 = r1.mboundView29
            r122 = r0
            r0 = r175
            r9.setVisibility(r0)
            goto L_0x0d6c
        L_0x0d68:
            r122 = r0
            r0 = r175
        L_0x0d6c:
            long r135 = r2 & r141
            int r9 = (r135 > r127 ? 1 : (r135 == r127 ? 0 : -1))
            if (r9 == 0) goto L_0x0d7c
            android.widget.ImageView r9 = r1.mboundView30
            r125 = r0
            r0 = r34
            r9.setVisibility(r0)
            goto L_0x0d80
        L_0x0d7c:
            r125 = r0
            r0 = r34
        L_0x0d80:
            long r132 = r2 & r133
            int r9 = (r132 > r127 ? 1 : (r132 == r127 ? 0 : -1))
            if (r9 == 0) goto L_0x0d90
            android.widget.ImageView r9 = r1.mboundView31
            r34 = r0
            r0 = r174
            r9.setVisibility(r0)
            goto L_0x0d94
        L_0x0d90:
            r34 = r0
            r0 = r174
        L_0x0d94:
            long r132 = r2 & r137
            int r9 = (r132 > r127 ? 1 : (r132 == r127 ? 0 : -1))
            if (r9 == 0) goto L_0x0da4
            android.widget.ImageView r9 = r1.mboundView32
            r129 = r0
            r0 = r165
            r9.setVisibility(r0)
            goto L_0x0da8
        L_0x0da4:
            r129 = r0
            r0 = r165
        L_0x0da8:
            r132 = 589824(0x90000, double:2.91412E-318)
            long r132 = r2 & r132
            int r9 = (r132 > r127 ? 1 : (r132 == r127 ? 0 : -1))
            if (r9 == 0) goto L_0x0dbb
            android.widget.ImageView r9 = r1.mboundView33
            r127 = r0
            r0 = r32
            r9.setVisibility(r0)
            goto L_0x0dbf
        L_0x0dbb:
            r127 = r0
            r0 = r32
        L_0x0dbf:
            com.wits.ksw.databinding.BmwId8DashboardMusicLayoutBinding r9 = r1.bmwId8DashboardMusicLay
            executeBindingsOn(r9)
            com.wits.ksw.databinding.BmwId8DashboardWeatherLayoutBinding r9 = r1.bmwId8DashboardWeatherLay
            executeBindingsOn(r9)
            return
        L_0x0dca:
            r0 = move-exception
            monitor-exit(r181)     // Catch:{ all -> 0x0dca }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.databinding.BmwId8DashboardLayoutBindingImpl.executeBindings():void");
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private DashboardViewModel value;

        public OnClickListenerImpl setValue(DashboardViewModel value2) {
            this.value = value2;
            if (value2 == null) {
                return null;
            }
            return this;
        }

        public void onClick(View arg0) {
            this.value.dashboardWeatherLay(arg0);
        }
    }

    public static class OnClickListenerImpl1 implements View.OnClickListener {
        private DashboardViewModel value;

        public OnClickListenerImpl1 setValue(DashboardViewModel value2) {
            this.value = value2;
            if (value2 == null) {
                return null;
            }
            return this;
        }

        public void onClick(View arg0) {
            this.value.dashboardMusicLay(arg0);
        }
    }
}
