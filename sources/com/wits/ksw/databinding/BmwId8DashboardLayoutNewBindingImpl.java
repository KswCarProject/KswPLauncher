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
import com.wits.ksw.launcher.view.LinearGradientProgressNew;

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
        includedLayouts.setIncludes(15, new String[]{"bmw_id8_dashboard_music_layout", "bmw_id8_dashboard_weather_layout"}, new int[]{16, 17}, new int[]{R.layout.bmw_id8_dashboard_music_layout, R.layout.bmw_id8_dashboard_weather_layout});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.bmw_id8_dashboard_left_bg, 18);
        sparseIntArray.put(R.id.bmw_id8_dashboard_right_bg, 19);
    }

    public BmwId8DashboardLayoutNewBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 20, sIncludes, sViewsWithIds));
    }

    private BmwId8DashboardLayoutNewBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 15, bindings[0], bindings[18], bindings[15], bindings[16], bindings[19], bindings[17]);
        this.mDirtyFlags = -1;
        this.bmwId8DashboardLay.setTag((Object) null);
        this.bmwId8DashboardMidLay.setTag((Object) null);
        setContainedBinding(this.bmwId8DashboardMusicLay);
        setContainedBinding(this.bmwId8DashboardWeatherLay);
        LinearGradientProgressNew linearGradientProgressNew = bindings[1];
        this.mboundView1 = linearGradientProgressNew;
        linearGradientProgressNew.setTag((Object) null);
        ImageView imageView = bindings[10];
        this.mboundView10 = imageView;
        imageView.setTag((Object) null);
        ImageView imageView2 = bindings[11];
        this.mboundView11 = imageView2;
        imageView2.setTag((Object) null);
        ImageView imageView3 = bindings[12];
        this.mboundView12 = imageView3;
        imageView3.setTag((Object) null);
        ImageView imageView4 = bindings[13];
        this.mboundView13 = imageView4;
        imageView4.setTag((Object) null);
        ImageView imageView5 = bindings[14];
        this.mboundView14 = imageView5;
        imageView5.setTag((Object) null);
        LinearGradientProgressNew linearGradientProgressNew2 = bindings[2];
        this.mboundView2 = linearGradientProgressNew2;
        linearGradientProgressNew2.setTag((Object) null);
        TextView textView = bindings[3];
        this.mboundView3 = textView;
        textView.setTag((Object) null);
        TextView textView2 = bindings[4];
        this.mboundView4 = textView2;
        textView2.setTag((Object) null);
        TextView textView3 = bindings[5];
        this.mboundView5 = textView3;
        textView3.setTag((Object) null);
        TextView textView4 = bindings[6];
        this.mboundView6 = textView4;
        textView4.setTag((Object) null);
        ImageView imageView6 = bindings[7];
        this.mboundView7 = imageView6;
        imageView6.setTag((Object) null);
        ImageView imageView7 = bindings[8];
        this.mboundView8 = imageView7;
        imageView7.setTag((Object) null);
        RelativeLayout relativeLayout = bindings[9];
        this.mboundView9 = relativeLayout;
        relativeLayout.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
        }
        this.bmwId8DashboardMusicLay.invalidateAll();
        this.bmwId8DashboardWeatherLay.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r4.bmwId8DashboardWeatherLay.hasPendingBindings() == false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r4.bmwId8DashboardMusicLay.hasPendingBindings() == false) goto L_0x0016;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        /*
            r4 = this;
            monitor-enter(r4)
            long r0 = r4.mDirtyFlags     // Catch:{ all -> 0x0021 }
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 1
            if (r0 == 0) goto L_0x000c
            monitor-exit(r4)     // Catch:{ all -> 0x0021 }
            return r1
        L_0x000c:
            monitor-exit(r4)     // Catch:{ all -> 0x0021 }
            com.wits.ksw.databinding.BmwId8DashboardMusicLayoutBinding r0 = r4.bmwId8DashboardMusicLay
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r1
        L_0x0016:
            com.wits.ksw.databinding.BmwId8DashboardWeatherLayoutBinding r0 = r4.bmwId8DashboardWeatherLay
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x001f
            return r1
        L_0x001f:
            r0 = 0
            return r0
        L_0x0021:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0021 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.databinding.BmwId8DashboardLayoutNewBindingImpl.hasPendingBindings():boolean");
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
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
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
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeBmwId8DashboardMusicLay(BmwId8DashboardMusicLayoutBinding BmwId8DashboardMusicLay, int fieldId) {
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

    private boolean onChangeViewModelCarInfoOilValue(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeViewModelDashBoardMusicShow(ObservableField<Boolean> observableField, int fieldId) {
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

    private boolean onChangeViewModelCarInfoRrDoorState(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoSeatBeltpValue(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoSpeedUnit(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoFlDoorState(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeBmwId8DashboardWeatherLay(BmwId8DashboardWeatherLayoutBinding BmwId8DashboardWeatherLay, int fieldId) {
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

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r46v2, resolved type: java.lang.Boolean} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r96 = this;
            r1 = r96
            r2 = 0
            monitor-enter(r96)
            long r4 = r1.mDirtyFlags     // Catch:{ all -> 0x05dd }
            r2 = r4
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x05dd }
            monitor-exit(r96)     // Catch:{ all -> 0x05dd }
            r0 = 0
            r6 = 0
            r7 = 0
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
            com.wits.ksw.launcher.model.DashboardViewModel r4 = r1.mViewModel
            r5 = 0
            r53 = 0
            r54 = 96221(0x177dd, double:4.75395E-319)
            long r54 = r2 & r54
            r51 = 0
            int r54 = (r54 > r51 ? 1 : (r54 == r51 ? 0 : -1))
            r55 = 66048(0x10200, double:3.2632E-319)
            r57 = 65552(0x10010, double:3.2387E-319)
            r59 = 69632(0x11000, double:3.4403E-319)
            r61 = 65537(0x10001, double:3.23796E-319)
            r63 = 81920(0x14000, double:4.0474E-319)
            r65 = 73728(0x12000, double:3.64265E-319)
            r67 = 65792(0x10100, double:3.25056E-319)
            r69 = r0
            r70 = 66560(0x10400, double:3.2885E-319)
            r72 = 65664(0x10080, double:3.24423E-319)
            r74 = 65600(0x10040, double:3.24107E-319)
            r76 = 65544(0x10008, double:3.2383E-319)
            r78 = 65540(0x10004, double:3.2381E-319)
            if (r54 == 0) goto L_0x03e5
            com.wits.ksw.launcher.bean.CarInfo r0 = com.wits.ksw.launcher.model.DashboardViewModel.carInfo
            long r81 = r2 & r61
            r51 = 0
            int r41 = (r81 > r51 ? 1 : (r81 == r51 ? 0 : -1))
            if (r41 == 0) goto L_0x00b6
            if (r0 == 0) goto L_0x00a5
            r81 = r5
            android.databinding.ObservableInt r5 = r0.turnSpeed
            goto L_0x00a9
        L_0x00a5:
            r81 = r5
            r5 = r69
        L_0x00a9:
            r82 = r6
            r6 = 0
            r1.updateRegistration((int) r6, (android.databinding.Observable) r5)
            if (r5 == 0) goto L_0x00bd
            int r28 = r5.get()
            goto L_0x00bd
        L_0x00b6:
            r81 = r5
            r82 = r6
            r6 = 0
            r5 = r69
        L_0x00bd:
            long r83 = r2 & r78
            r51 = 0
            int r41 = (r83 > r51 ? 1 : (r83 == r51 ? 0 : -1))
            if (r41 == 0) goto L_0x00f3
            if (r0 == 0) goto L_0x00c9
            android.databinding.ObservableBoolean r13 = r0.carImage
        L_0x00c9:
            r6 = 2
            r1.updateRegistration((int) r6, (android.databinding.Observable) r13)
            if (r13 == 0) goto L_0x00d3
            boolean r43 = r13.get()
        L_0x00d3:
            long r83 = r2 & r78
            r51 = 0
            int r6 = (r83 > r51 ? 1 : (r83 == r51 ? 0 : -1))
            if (r6 == 0) goto L_0x00ec
            if (r43 == 0) goto L_0x00e5
            r83 = 68719476736(0x1000000000, double:3.39519326554E-313)
            long r2 = r2 | r83
            goto L_0x00ec
        L_0x00e5:
            r83 = 34359738368(0x800000000, double:1.69759663277E-313)
            long r2 = r2 | r83
        L_0x00ec:
            if (r43 == 0) goto L_0x00f0
            r6 = 0
            goto L_0x00f1
        L_0x00f0:
            r6 = 4
        L_0x00f1:
            r49 = r6
        L_0x00f3:
            long r83 = r2 & r76
            r51 = 0
            int r6 = (r83 > r51 ? 1 : (r83 == r51 ? 0 : -1))
            if (r6 == 0) goto L_0x0139
            if (r0 == 0) goto L_0x0100
            android.databinding.ObservableField<java.lang.Boolean> r6 = r0.frDoorState
            goto L_0x0102
        L_0x0100:
            r6 = r16
        L_0x0102:
            r69 = r5
            r5 = 3
            r1.updateRegistration((int) r5, (android.databinding.Observable) r6)
            if (r6 == 0) goto L_0x0112
            java.lang.Object r5 = r6.get()
            r44 = r5
            java.lang.Boolean r44 = (java.lang.Boolean) r44
        L_0x0112:
            boolean r47 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r44)
            long r83 = r2 & r76
            r51 = 0
            int r5 = (r83 > r51 ? 1 : (r83 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x012f
            if (r47 == 0) goto L_0x0128
            r83 = 4294967296(0x100000000, double:2.121995791E-314)
            long r2 = r2 | r83
            goto L_0x012f
        L_0x0128:
            r83 = 2147483648(0x80000000, double:1.0609978955E-314)
            long r2 = r2 | r83
        L_0x012f:
            if (r47 == 0) goto L_0x0133
            r5 = 0
            goto L_0x0134
        L_0x0133:
            r5 = 4
        L_0x0134:
            r42 = r5
            r16 = r6
            goto L_0x013b
        L_0x0139:
            r69 = r5
        L_0x013b:
            long r5 = r2 & r57
            r51 = 0
            int r5 = (r5 > r51 ? 1 : (r5 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x015e
            if (r0 == 0) goto L_0x0148
            android.databinding.ObservableField<java.lang.String> r5 = r0.oilValue
            goto L_0x014a
        L_0x0148:
            r5 = r27
        L_0x014a:
            r6 = 4
            r1.updateRegistration((int) r6, (android.databinding.Observable) r5)
            if (r5 == 0) goto L_0x015b
            java.lang.Object r27 = r5.get()
            r17 = r27
            java.lang.String r17 = (java.lang.String) r17
            r27 = r5
            goto L_0x015f
        L_0x015b:
            r27 = r5
            goto L_0x015f
        L_0x015e:
            r6 = 4
        L_0x015f:
            long r83 = r2 & r74
            r51 = 0
            int r5 = (r83 > r51 ? 1 : (r83 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x019e
            if (r0 == 0) goto L_0x016c
            android.databinding.ObservableField<java.lang.Boolean> r5 = r0.rlDoorState
            goto L_0x016e
        L_0x016c:
            r5 = r32
        L_0x016e:
            r6 = 6
            r1.updateRegistration((int) r6, (android.databinding.Observable) r5)
            if (r5 == 0) goto L_0x017c
            java.lang.Object r6 = r5.get()
            r19 = r6
            java.lang.Boolean r19 = (java.lang.Boolean) r19
        L_0x017c:
            boolean r14 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r19)
            long r83 = r2 & r74
            r51 = 0
            int r6 = (r83 > r51 ? 1 : (r83 == r51 ? 0 : -1))
            if (r6 == 0) goto L_0x0195
            if (r14 == 0) goto L_0x0190
            r83 = 268435456(0x10000000, double:1.32624737E-315)
            long r2 = r2 | r83
            goto L_0x0195
        L_0x0190:
            r83 = 134217728(0x8000000, double:6.63123685E-316)
            long r2 = r2 | r83
        L_0x0195:
            if (r14 == 0) goto L_0x0199
            r6 = 0
            goto L_0x019a
        L_0x0199:
            r6 = 4
        L_0x019a:
            r36 = r6
            r32 = r5
        L_0x019e:
            long r5 = r2 & r72
            r51 = 0
            int r5 = (r5 > r51 ? 1 : (r5 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x01dd
            if (r0 == 0) goto L_0x01ab
            android.databinding.ObservableField<java.lang.Boolean> r5 = r0.rrDoorState
            goto L_0x01ad
        L_0x01ab:
            r5 = r33
        L_0x01ad:
            r6 = 7
            r1.updateRegistration((int) r6, (android.databinding.Observable) r5)
            if (r5 == 0) goto L_0x01bb
            java.lang.Object r6 = r5.get()
            r26 = r6
            java.lang.Boolean r26 = (java.lang.Boolean) r26
        L_0x01bb:
            boolean r15 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r26)
            long r83 = r2 & r72
            r51 = 0
            int r6 = (r83 > r51 ? 1 : (r83 == r51 ? 0 : -1))
            if (r6 == 0) goto L_0x01d4
            if (r15 == 0) goto L_0x01cf
            r83 = 4194304(0x400000, double:2.0722615E-317)
            long r2 = r2 | r83
            goto L_0x01d4
        L_0x01cf:
            r83 = 2097152(0x200000, double:1.0361308E-317)
            long r2 = r2 | r83
        L_0x01d4:
            if (r15 == 0) goto L_0x01d8
            r6 = 0
            goto L_0x01d9
        L_0x01d8:
            r6 = 4
        L_0x01d9:
            r20 = r6
            r33 = r5
        L_0x01dd:
            long r5 = r2 & r67
            r51 = 0
            int r5 = (r5 > r51 ? 1 : (r5 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x023f
            if (r0 == 0) goto L_0x01ea
            android.databinding.ObservableField<java.lang.Boolean> r5 = r0.seatBeltpValue
            goto L_0x01ec
        L_0x01ea:
            r5 = r37
        L_0x01ec:
            r6 = 8
            r1.updateRegistration((int) r6, (android.databinding.Observable) r5)
            if (r5 == 0) goto L_0x01fb
            java.lang.Object r37 = r5.get()
            r18 = r37
            java.lang.Boolean r18 = (java.lang.Boolean) r18
        L_0x01fb:
            boolean r23 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r18)
            long r83 = r2 & r67
            r51 = 0
            int r37 = (r83 > r51 ? 1 : (r83 == r51 ? 0 : -1))
            if (r37 == 0) goto L_0x0218
            if (r23 == 0) goto L_0x0211
            r83 = 17179869184(0x400000000, double:8.4879831639E-314)
            long r2 = r2 | r83
            goto L_0x0218
        L_0x0211:
            r83 = 8589934592(0x200000000, double:4.243991582E-314)
            long r2 = r2 | r83
        L_0x0218:
            if (r23 == 0) goto L_0x022a
            android.widget.ImageView r6 = r1.mboundView7
            android.content.Context r6 = r6.getContext()
            r83 = r2
            r2 = 2131233067(0x7f08092b, float:1.8082261E38)
            android.graphics.drawable.Drawable r2 = android.support.v7.content.res.AppCompatResources.getDrawable(r6, r2)
            goto L_0x0239
        L_0x022a:
            r83 = r2
            android.widget.ImageView r2 = r1.mboundView7
            android.content.Context r2 = r2.getContext()
            r3 = 2131233066(0x7f08092a, float:1.808226E38)
            android.graphics.drawable.Drawable r2 = android.support.v7.content.res.AppCompatResources.getDrawable(r2, r3)
        L_0x0239:
            r45 = r2
            r37 = r5
            r2 = r83
        L_0x023f:
            long r5 = r2 & r55
            r51 = 0
            int r5 = (r5 > r51 ? 1 : (r5 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x0262
            if (r0 == 0) goto L_0x024c
            android.databinding.ObservableField<java.lang.String> r5 = r0.speedUnit
            goto L_0x024e
        L_0x024c:
            r5 = r40
        L_0x024e:
            r6 = 9
            r1.updateRegistration((int) r6, (android.databinding.Observable) r5)
            if (r5 == 0) goto L_0x0260
            java.lang.Object r6 = r5.get()
            r21 = r6
            java.lang.String r21 = (java.lang.String) r21
            r40 = r5
            goto L_0x0262
        L_0x0260:
            r40 = r5
        L_0x0262:
            long r5 = r2 & r70
            r51 = 0
            int r5 = (r5 > r51 ? 1 : (r5 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x02a2
            if (r0 == 0) goto L_0x026f
            android.databinding.ObservableField<java.lang.Boolean> r5 = r0.flDoorState
            goto L_0x0271
        L_0x026f:
            r5 = r48
        L_0x0271:
            r6 = 10
            r1.updateRegistration((int) r6, (android.databinding.Observable) r5)
            if (r5 == 0) goto L_0x0280
            java.lang.Object r6 = r5.get()
            r34 = r6
            java.lang.Boolean r34 = (java.lang.Boolean) r34
        L_0x0280:
            boolean r22 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r34)
            long r83 = r2 & r70
            r51 = 0
            int r6 = (r83 > r51 ? 1 : (r83 == r51 ? 0 : -1))
            if (r6 == 0) goto L_0x0299
            if (r22 == 0) goto L_0x0294
            r83 = 1073741824(0x40000000, double:5.304989477E-315)
            long r2 = r2 | r83
            goto L_0x0299
        L_0x0294:
            r83 = 536870912(0x20000000, double:2.652494739E-315)
            long r2 = r2 | r83
        L_0x0299:
            if (r22 == 0) goto L_0x029d
            r6 = 0
            goto L_0x029e
        L_0x029d:
            r6 = 4
        L_0x029e:
            r38 = r6
            r48 = r5
        L_0x02a2:
            long r5 = r2 & r59
            r51 = 0
            int r5 = (r5 > r51 ? 1 : (r5 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x02c2
            if (r0 == 0) goto L_0x02af
            android.databinding.ObservableInt r5 = r0.speed
            goto L_0x02b1
        L_0x02af:
            r5 = r50
        L_0x02b1:
            r6 = 12
            r1.updateRegistration((int) r6, (android.databinding.Observable) r5)
            if (r5 == 0) goto L_0x02bc
            int r10 = r5.get()
        L_0x02bc:
            java.lang.String r24 = java.lang.String.valueOf(r10)
            r50 = r5
        L_0x02c2:
            long r5 = r2 & r65
            r51 = 0
            int r5 = (r5 > r51 ? 1 : (r5 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x0302
            if (r0 == 0) goto L_0x02cf
            android.databinding.ObservableField<java.lang.Boolean> r5 = r0.bDoorState
            goto L_0x02d1
        L_0x02cf:
            r5 = r81
        L_0x02d1:
            r6 = 13
            r1.updateRegistration((int) r6, (android.databinding.Observable) r5)
            if (r5 == 0) goto L_0x02df
            java.lang.Object r6 = r5.get()
            r8 = r6
            java.lang.Boolean r8 = (java.lang.Boolean) r8
        L_0x02df:
            boolean r6 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r8)
            long r81 = r2 & r65
            r51 = 0
            int r81 = (r81 > r51 ? 1 : (r81 == r51 ? 0 : -1))
            if (r81 == 0) goto L_0x02f8
            if (r6 == 0) goto L_0x02f3
            r81 = 262144(0x40000, double:1.295163E-318)
            long r2 = r2 | r81
            goto L_0x02f8
        L_0x02f3:
            r81 = 131072(0x20000, double:6.47582E-319)
            long r2 = r2 | r81
        L_0x02f8:
            if (r6 == 0) goto L_0x02fd
            r41 = 0
            goto L_0x02ff
        L_0x02fd:
            r41 = 4
        L_0x02ff:
            r11 = r41
            goto L_0x0306
        L_0x0302:
            r5 = r81
            r6 = r82
        L_0x0306:
            long r81 = r2 & r63
            r51 = 0
            int r41 = (r81 > r51 ? 1 : (r81 == r51 ? 0 : -1))
            if (r41 == 0) goto L_0x03a4
            if (r0 == 0) goto L_0x0315
            r41 = r5
            android.databinding.ObservableField<java.lang.Boolean> r5 = r0.brakeValue
            goto L_0x0319
        L_0x0315:
            r41 = r5
            r5 = r53
        L_0x0319:
            r83 = r0
            r0 = 14
            r1.updateRegistration((int) r0, (android.databinding.Observable) r5)
            if (r5 == 0) goto L_0x032a
            java.lang.Object r0 = r5.get()
            r35 = r0
            java.lang.Boolean r35 = (java.lang.Boolean) r35
        L_0x032a:
            boolean r7 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r35)
            long r81 = r2 & r63
            r51 = 0
            int r0 = (r81 > r51 ? 1 : (r81 == r51 ? 0 : -1))
            if (r0 == 0) goto L_0x0343
            if (r7 == 0) goto L_0x033e
            r81 = 16777216(0x1000000, double:8.289046E-317)
            long r2 = r2 | r81
            goto L_0x0343
        L_0x033e:
            r81 = 8388608(0x800000, double:4.144523E-317)
            long r2 = r2 | r81
        L_0x0343:
            if (r7 == 0) goto L_0x0351
            android.widget.ImageView r0 = r1.mboundView8
            android.content.Context r0 = r0.getContext()
            r81 = r2
            r2 = 2131233060(0x7f080924, float:1.8082247E38)
            goto L_0x035c
        L_0x0351:
            r81 = r2
            android.widget.ImageView r0 = r1.mboundView8
            android.content.Context r0 = r0.getContext()
            r2 = 2131233061(0x7f080925, float:1.8082249E38)
        L_0x035c:
            android.graphics.drawable.Drawable r0 = android.support.v7.content.res.AppCompatResources.getDrawable(r0, r2)
            r25 = r0
            r53 = r5
            r0 = r17
            r5 = r20
            r20 = r41
            r85 = r49
            r17 = r69
            r2 = r81
            r41 = r83
            r88 = r21
            r21 = r6
            r6 = r88
            r89 = r24
            r24 = r7
            r7 = r89
            r90 = r25
            r25 = r8
            r8 = r90
            r91 = r28
            r28 = r9
            r9 = r91
            r92 = r36
            r36 = r12
            r12 = r92
            r93 = r38
            r38 = r13
            r13 = r93
            r94 = r42
            r42 = r14
            r14 = r94
            r95 = r45
            r45 = r15
            r15 = r95
            goto L_0x0421
        L_0x03a4:
            r83 = r0
            r41 = r5
            r0 = r17
            r5 = r20
            r20 = r41
            r85 = r49
            r17 = r69
            r41 = r83
            r88 = r21
            r21 = r6
            r6 = r88
            r89 = r24
            r24 = r7
            r7 = r89
            r90 = r25
            r25 = r8
            r8 = r90
            r91 = r28
            r28 = r9
            r9 = r91
            r92 = r36
            r36 = r12
            r12 = r92
            r93 = r38
            r38 = r13
            r13 = r93
            r94 = r42
            r42 = r14
            r14 = r94
            r95 = r45
            r45 = r15
            r15 = r95
            goto L_0x0421
        L_0x03e5:
            r81 = r5
            r82 = r6
            r0 = r17
            r5 = r20
            r6 = r21
            r85 = r49
            r17 = r69
            r20 = r81
            r21 = r82
            r88 = r24
            r24 = r7
            r7 = r88
            r89 = r25
            r25 = r8
            r8 = r89
            r90 = r28
            r28 = r9
            r9 = r90
            r91 = r36
            r36 = r12
            r12 = r91
            r92 = r38
            r38 = r13
            r13 = r92
            r93 = r42
            r42 = r14
            r14 = r93
            r94 = r45
            r45 = r15
            r15 = r94
        L_0x0421:
            r81 = 98336(0x18020, double:4.85844E-319)
            long r83 = r2 & r81
            r51 = 0
            int r49 = (r83 > r51 ? 1 : (r83 == r51 ? 0 : -1))
            r83 = 98304(0x18000, double:4.85686E-319)
            if (r49 == 0) goto L_0x04b5
            long r86 = r2 & r83
            int r49 = (r86 > r51 ? 1 : (r86 == r51 ? 0 : -1))
            if (r49 == 0) goto L_0x045d
            if (r4 == 0) goto L_0x045a
            r49 = r8
            com.wits.ksw.databinding.BmwId8DashboardLayoutNewBindingImpl$OnClickListenerImpl r8 = r1.mViewModelDashboardWeatherLayAndroidViewViewOnClickListener
            if (r8 != 0) goto L_0x0444
            com.wits.ksw.databinding.BmwId8DashboardLayoutNewBindingImpl$OnClickListenerImpl r8 = new com.wits.ksw.databinding.BmwId8DashboardLayoutNewBindingImpl$OnClickListenerImpl
            r8.<init>()
            r1.mViewModelDashboardWeatherLayAndroidViewViewOnClickListener = r8
        L_0x0444:
            com.wits.ksw.databinding.BmwId8DashboardLayoutNewBindingImpl$OnClickListenerImpl r8 = r8.setValue(r4)
            r28 = r8
            com.wits.ksw.databinding.BmwId8DashboardLayoutNewBindingImpl$OnClickListenerImpl1 r8 = r1.mViewModelDashboardMusicLayAndroidViewViewOnClickListener
            if (r8 != 0) goto L_0x0455
            com.wits.ksw.databinding.BmwId8DashboardLayoutNewBindingImpl$OnClickListenerImpl1 r8 = new com.wits.ksw.databinding.BmwId8DashboardLayoutNewBindingImpl$OnClickListenerImpl1
            r8.<init>()
            r1.mViewModelDashboardMusicLayAndroidViewViewOnClickListener = r8
        L_0x0455:
            com.wits.ksw.databinding.BmwId8DashboardLayoutNewBindingImpl$OnClickListenerImpl1 r31 = r8.setValue(r4)
            goto L_0x045f
        L_0x045a:
            r49 = r8
            goto L_0x045f
        L_0x045d:
            r49 = r8
        L_0x045f:
            if (r4 == 0) goto L_0x0464
            android.databinding.ObservableField r8 = r4.dashBoardMusicShow
            goto L_0x0466
        L_0x0464:
            r8 = r30
        L_0x0466:
            r69 = r15
            r15 = 5
            r1.updateRegistration((int) r15, (android.databinding.Observable) r8)
            if (r8 == 0) goto L_0x0476
            java.lang.Object r15 = r8.get()
            r46 = r15
            java.lang.Boolean r46 = (java.lang.Boolean) r46
        L_0x0476:
            boolean r39 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r46)
            long r86 = r2 & r81
            r51 = 0
            int r15 = (r86 > r51 ? 1 : (r86 == r51 ? 0 : -1))
            if (r15 == 0) goto L_0x0499
            if (r39 == 0) goto L_0x048f
            r86 = 1048576(0x100000, double:5.180654E-318)
            long r2 = r2 | r86
            r86 = 67108864(0x4000000, double:3.31561842E-316)
            long r2 = r2 | r86
            goto L_0x0499
        L_0x048f:
            r86 = 524288(0x80000, double:2.590327E-318)
            long r2 = r2 | r86
            r86 = 33554432(0x2000000, double:1.6578092E-316)
            long r2 = r2 | r86
        L_0x0499:
            if (r39 == 0) goto L_0x049d
            r15 = 0
            goto L_0x049f
        L_0x049d:
            r15 = 8
        L_0x049f:
            if (r39 == 0) goto L_0x04a4
            r80 = 8
            goto L_0x04a6
        L_0x04a4:
            r80 = 0
        L_0x04a6:
            r29 = r80
            r30 = r8
            r8 = r28
            r28 = r0
            r0 = r29
            r29 = r6
            r6 = r31
            goto L_0x04c5
        L_0x04b5:
            r49 = r8
            r69 = r15
            r8 = r28
            r15 = r36
            r28 = r0
            r0 = r29
            r29 = r6
            r6 = r31
        L_0x04c5:
            long r83 = r2 & r83
            r51 = 0
            int r31 = (r83 > r51 ? 1 : (r83 == r51 ? 0 : -1))
            if (r31 == 0) goto L_0x04ec
            r31 = r9
            com.wits.ksw.databinding.BmwId8DashboardMusicLayoutBinding r9 = r1.bmwId8DashboardMusicLay
            android.view.View r9 = r9.getRoot()
            r9.setOnClickListener(r6)
            com.wits.ksw.databinding.BmwId8DashboardMusicLayoutBinding r9 = r1.bmwId8DashboardMusicLay
            r9.setViewModel(r4)
            com.wits.ksw.databinding.BmwId8DashboardWeatherLayoutBinding r9 = r1.bmwId8DashboardWeatherLay
            android.view.View r9 = r9.getRoot()
            r9.setOnClickListener(r8)
            com.wits.ksw.databinding.BmwId8DashboardWeatherLayoutBinding r9 = r1.bmwId8DashboardWeatherLay
            r9.setViewModel(r4)
            goto L_0x04ee
        L_0x04ec:
            r31 = r9
        L_0x04ee:
            long r80 = r2 & r81
            r51 = 0
            int r9 = (r80 > r51 ? 1 : (r80 == r51 ? 0 : -1))
            if (r9 == 0) goto L_0x0508
            com.wits.ksw.databinding.BmwId8DashboardMusicLayoutBinding r9 = r1.bmwId8DashboardMusicLay
            android.view.View r9 = r9.getRoot()
            r9.setVisibility(r15)
            com.wits.ksw.databinding.BmwId8DashboardWeatherLayoutBinding r9 = r1.bmwId8DashboardWeatherLay
            android.view.View r9 = r9.getRoot()
            r9.setVisibility(r0)
        L_0x0508:
            long r59 = r2 & r59
            r51 = 0
            int r9 = (r59 > r51 ? 1 : (r59 == r51 ? 0 : -1))
            if (r9 == 0) goto L_0x051a
            com.wits.ksw.launcher.view.LinearGradientProgressNew r9 = r1.mboundView1
            r9.setScae(r10)
            android.widget.TextView r9 = r1.mboundView3
            android.databinding.adapters.TextViewBindingAdapter.setText(r9, r7)
        L_0x051a:
            long r59 = r2 & r76
            r51 = 0
            int r9 = (r59 > r51 ? 1 : (r59 == r51 ? 0 : -1))
            if (r9 == 0) goto L_0x0527
            android.widget.ImageView r9 = r1.mboundView10
            r9.setVisibility(r14)
        L_0x0527:
            long r59 = r2 & r72
            int r9 = (r59 > r51 ? 1 : (r59 == r51 ? 0 : -1))
            if (r9 == 0) goto L_0x0532
            android.widget.ImageView r9 = r1.mboundView11
            r9.setVisibility(r5)
        L_0x0532:
            long r59 = r2 & r70
            int r9 = (r59 > r51 ? 1 : (r59 == r51 ? 0 : -1))
            if (r9 == 0) goto L_0x053d
            android.widget.ImageView r9 = r1.mboundView12
            r9.setVisibility(r13)
        L_0x053d:
            long r59 = r2 & r74
            int r9 = (r59 > r51 ? 1 : (r59 == r51 ? 0 : -1))
            if (r9 == 0) goto L_0x0548
            android.widget.ImageView r9 = r1.mboundView13
            r9.setVisibility(r12)
        L_0x0548:
            long r59 = r2 & r65
            int r9 = (r59 > r51 ? 1 : (r59 == r51 ? 0 : -1))
            if (r9 == 0) goto L_0x0553
            android.widget.ImageView r9 = r1.mboundView14
            r9.setVisibility(r11)
        L_0x0553:
            long r59 = r2 & r61
            int r9 = (r59 > r51 ? 1 : (r59 == r51 ? 0 : -1))
            if (r9 == 0) goto L_0x0568
            com.wits.ksw.launcher.view.LinearGradientProgressNew r9 = r1.mboundView2
            r36 = r0
            r0 = r31
            r9.setScae(r0)
            android.widget.TextView r9 = r1.mboundView5
            com.wits.ksw.launcher.base.BaseBindingModel.setTurnSpeedStr(r9, r0)
            goto L_0x056c
        L_0x0568:
            r36 = r0
            r0 = r31
        L_0x056c:
            long r54 = r2 & r55
            r51 = 0
            int r9 = (r54 > r51 ? 1 : (r54 == r51 ? 0 : -1))
            if (r9 == 0) goto L_0x057e
            android.widget.TextView r9 = r1.mboundView4
            r31 = r0
            r0 = r29
            android.databinding.adapters.TextViewBindingAdapter.setText(r9, r0)
            goto L_0x0582
        L_0x057e:
            r31 = r0
            r0 = r29
        L_0x0582:
            long r54 = r2 & r57
            int r9 = (r54 > r51 ? 1 : (r54 == r51 ? 0 : -1))
            if (r9 == 0) goto L_0x0592
            android.widget.TextView r9 = r1.mboundView6
            r29 = r0
            r0 = r28
            android.databinding.adapters.TextViewBindingAdapter.setText(r9, r0)
            goto L_0x0596
        L_0x0592:
            r29 = r0
            r0 = r28
        L_0x0596:
            long r54 = r2 & r67
            int r9 = (r54 > r51 ? 1 : (r54 == r51 ? 0 : -1))
            if (r9 == 0) goto L_0x05a6
            android.widget.ImageView r9 = r1.mboundView7
            r28 = r0
            r0 = r69
            android.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(r9, r0)
            goto L_0x05aa
        L_0x05a6:
            r28 = r0
            r0 = r69
        L_0x05aa:
            long r54 = r2 & r63
            int r9 = (r54 > r51 ? 1 : (r54 == r51 ? 0 : -1))
            if (r9 == 0) goto L_0x05ba
            android.widget.ImageView r9 = r1.mboundView8
            r69 = r0
            r0 = r49
            android.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(r9, r0)
            goto L_0x05be
        L_0x05ba:
            r69 = r0
            r0 = r49
        L_0x05be:
            long r54 = r2 & r78
            int r9 = (r54 > r51 ? 1 : (r54 == r51 ? 0 : -1))
            if (r9 == 0) goto L_0x05ce
            android.widget.RelativeLayout r9 = r1.mboundView9
            r49 = r0
            r0 = r85
            r9.setVisibility(r0)
            goto L_0x05d2
        L_0x05ce:
            r49 = r0
            r0 = r85
        L_0x05d2:
            com.wits.ksw.databinding.BmwId8DashboardMusicLayoutBinding r9 = r1.bmwId8DashboardMusicLay
            executeBindingsOn(r9)
            com.wits.ksw.databinding.BmwId8DashboardWeatherLayoutBinding r9 = r1.bmwId8DashboardWeatherLay
            executeBindingsOn(r9)
            return
        L_0x05dd:
            r0 = move-exception
            monitor-exit(r96)     // Catch:{ all -> 0x05dd }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.databinding.BmwId8DashboardLayoutNewBindingImpl.executeBindings():void");
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
