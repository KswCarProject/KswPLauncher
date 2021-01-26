package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.DashboardViewModel;

public class ActivityNtg6DashBoardBindingSw600dpLandImpl extends ActivityNtg6DashBoardBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;

    static {
        sViewsWithIds.put(R.id.ntg55_pointer_speed_highlight_ImageView, 13);
        sViewsWithIds.put(R.id.tachometerImageView, 14);
        sViewsWithIds.put(R.id.ntg55_pointer_tachometer_highlight_imageView, 15);
    }

    public ActivityNtg6DashBoardBindingSw600dpLandImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    private ActivityNtg6DashBoardBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 12, bindings[11], bindings[5], bindings[10], bindings[6], bindings[7], bindings[8], bindings[9], bindings[0], bindings[13], bindings[15], bindings[12], bindings[3], bindings[2], bindings[14], bindings[1], bindings[4]);
        this.mDirtyFlags = -1;
        this.brakeTextView.setTag((Object) null);
        this.carImageView.setTag((Object) null);
        this.dorrBackImageView.setTag((Object) null);
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
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PLAY_FROM_URI;
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
                return onChangeViewModelCarInfoSpeedWatch((ObservableInt) object, fieldId);
            case 6:
                return onChangeViewModelCarInfoRlDoorState((ObservableField) object, fieldId);
            case 7:
                return onChangeViewModelCarInfoTurnSpeedAnge((ObservableFloat) object, fieldId);
            case 8:
                return onChangeViewModelCarInfoFlDoorState((ObservableField) object, fieldId);
            case 9:
                return onChangeViewModelCarInfoSpeed((ObservableInt) object, fieldId);
            case 10:
                return onChangeViewModelCarInfoBDoorState((ObservableField) object, fieldId);
            case 11:
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

    private boolean onChangeViewModelCarInfoSpeedWatch(ObservableInt ViewModelCarInfoSpeedWatch, int fieldId) {
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

    private boolean onChangeViewModelCarInfoTurnSpeedAnge(ObservableFloat ViewModelCarInfoTurnSpeedAnge, int fieldId) {
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

    private boolean onChangeViewModelCarInfoBDoorState(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoBrakeValue(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x0212  */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x024f  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x025d  */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x027b  */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x02be  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r80 = this;
            r1 = r80
            r2 = 0
            monitor-enter(r80)
            long r4 = r1.mDirtyFlags     // Catch:{ all -> 0x03fa }
            r2 = r4
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x03fa }
            monitor-exit(r80)     // Catch:{ all -> 0x03fa }
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
            r45 = 12287(0x2fff, double:6.0706E-320)
            long r45 = r2 & r45
            int r45 = (r45 > r4 ? 1 : (r45 == r4 ? 0 : -1))
            r46 = 8320(0x2080, double:4.1106E-320)
            r48 = 8224(0x2020, double:4.063E-320)
            r50 = 8200(0x2008, double:4.0513E-320)
            r52 = 8256(0x2040, double:4.079E-320)
            r54 = 8208(0x2010, double:4.0553E-320)
            r56 = 8448(0x2100, double:4.174E-320)
            r58 = 9216(0x2400, double:4.5533E-320)
            r60 = 8196(0x2004, double:4.0494E-320)
            r62 = 8194(0x2002, double:4.0484E-320)
            r64 = 10240(0x2800, double:5.059E-320)
            r66 = 8193(0x2001, double:4.048E-320)
            if (r45 == 0) goto L_0x032e
            com.wits.ksw.launcher.bean.CarInfo r4 = com.wits.ksw.launcher.model.DashboardViewModel.carInfo
            long r70 = r2 & r66
            r68 = 0
            int r5 = (r70 > r68 ? 1 : (r70 == r68 ? 0 : -1))
            r72 = r0
            r0 = 0
            if (r5 == 0) goto L_0x00a6
            if (r4 == 0) goto L_0x0081
            android.databinding.ObservableBoolean r9 = r4.carImage
        L_0x0081:
            r1.updateRegistration((int) r0, (android.databinding.Observable) r9)
            if (r9 == 0) goto L_0x008a
            boolean r21 = r9.get()
        L_0x008a:
            long r70 = r2 & r66
            r68 = 0
            int r5 = (r70 > r68 ? 1 : (r70 == r68 ? 0 : -1))
            if (r5 == 0) goto L_0x009f
            if (r21 == 0) goto L_0x009a
            r70 = 2097152(0x200000, double:1.0361308E-317)
            long r2 = r2 | r70
            goto L_0x009f
        L_0x009a:
            r70 = 1048576(0x100000, double:5.180654E-318)
            long r2 = r2 | r70
        L_0x009f:
            if (r21 == 0) goto L_0x00a3
            r5 = r0
            goto L_0x00a4
        L_0x00a3:
            r5 = 4
        L_0x00a4:
            r23 = r5
        L_0x00a6:
            long r70 = r2 & r62
            r68 = 0
            int r5 = (r70 > r68 ? 1 : (r70 == r68 ? 0 : -1))
            if (r5 == 0) goto L_0x00e4
            if (r4 == 0) goto L_0x00b2
            android.databinding.ObservableField<java.lang.Boolean> r12 = r4.frDoorState
        L_0x00b2:
            r5 = 1
            r1.updateRegistration((int) r5, (android.databinding.Observable) r12)
            if (r12 == 0) goto L_0x00c0
            java.lang.Object r5 = r12.get()
            r22 = r5
            java.lang.Boolean r22 = (java.lang.Boolean) r22
        L_0x00c0:
            boolean r5 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r22)
            long r70 = r2 & r62
            r68 = 0
            int r20 = (r70 > r68 ? 1 : (r70 == r68 ? 0 : -1))
            if (r20 == 0) goto L_0x00d9
            if (r5 == 0) goto L_0x00d4
            r70 = 536870912(0x20000000, double:2.652494739E-315)
            long r2 = r2 | r70
            goto L_0x00d9
        L_0x00d4:
            r70 = 268435456(0x10000000, double:1.32624737E-315)
            long r2 = r2 | r70
        L_0x00d9:
            if (r5 == 0) goto L_0x00de
            r20 = r0
            goto L_0x00e0
        L_0x00de:
            r20 = 4
        L_0x00e0:
            r40 = r5
            r39 = r20
        L_0x00e4:
            long r70 = r2 & r60
            r68 = 0
            int r5 = (r70 > r68 ? 1 : (r70 == r68 ? 0 : -1))
            if (r5 == 0) goto L_0x0123
            if (r4 == 0) goto L_0x00f1
            android.databinding.ObservableField<java.lang.Boolean> r5 = r4.rrDoorState
            goto L_0x00f3
        L_0x00f1:
            r5 = r16
        L_0x00f3:
            r0 = 2
            r1.updateRegistration((int) r0, (android.databinding.Observable) r5)
            if (r5 == 0) goto L_0x0101
            java.lang.Object r0 = r5.get()
            r30 = r0
            java.lang.Boolean r30 = (java.lang.Boolean) r30
        L_0x0101:
            boolean r0 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r30)
            long r70 = r2 & r60
            r68 = 0
            int r11 = (r70 > r68 ? 1 : (r70 == r68 ? 0 : -1))
            if (r11 == 0) goto L_0x011a
            if (r0 == 0) goto L_0x0115
            r70 = 131072(0x20000, double:6.47582E-319)
            long r2 = r2 | r70
            goto L_0x011a
        L_0x0115:
            r70 = 65536(0x10000, double:3.2379E-319)
            long r2 = r2 | r70
        L_0x011a:
            if (r0 == 0) goto L_0x011e
            r11 = 0
            goto L_0x011f
        L_0x011e:
            r11 = 4
        L_0x011f:
            r16 = r5
            r14 = r11
            r11 = r0
        L_0x0123:
            long r70 = r2 & r50
            r68 = 0
            int r0 = (r70 > r68 ? 1 : (r70 == r68 ? 0 : -1))
            if (r0 == 0) goto L_0x0145
            if (r4 == 0) goto L_0x0130
            android.databinding.ObservableField<java.lang.String> r0 = r4.tempStr
            goto L_0x0132
        L_0x0130:
            r0 = r17
        L_0x0132:
            r5 = 3
            r1.updateRegistration((int) r5, (android.databinding.Observable) r0)
            if (r0 == 0) goto L_0x0143
            java.lang.Object r5 = r0.get()
            java.lang.String r5 = (java.lang.String) r5
            r17 = r0
            r35 = r5
            goto L_0x0145
        L_0x0143:
            r17 = r0
        L_0x0145:
            long r70 = r2 & r54
            r68 = 0
            int r0 = (r70 > r68 ? 1 : (r70 == r68 ? 0 : -1))
            if (r0 == 0) goto L_0x0189
            if (r4 == 0) goto L_0x0152
            android.databinding.ObservableField<java.lang.Boolean> r0 = r4.seatBeltpValue
            goto L_0x0154
        L_0x0152:
            r0 = r19
        L_0x0154:
            r5 = 4
            r1.updateRegistration((int) r5, (android.databinding.Observable) r0)
            if (r0 == 0) goto L_0x0162
            java.lang.Object r19 = r0.get()
            r28 = r19
            java.lang.Boolean r28 = (java.lang.Boolean) r28
        L_0x0162:
            boolean r19 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r28)
            long r70 = r2 & r54
            r68 = 0
            int r20 = (r70 > r68 ? 1 : (r70 == r68 ? 0 : -1))
            if (r20 == 0) goto L_0x017b
            if (r19 == 0) goto L_0x0176
            r70 = 8388608(0x800000, double:4.144523E-317)
            long r2 = r2 | r70
            goto L_0x017b
        L_0x0176:
            r70 = 4194304(0x400000, double:2.0722615E-317)
            long r2 = r2 | r70
        L_0x017b:
            if (r19 == 0) goto L_0x0180
            r20 = 8
            goto L_0x0182
        L_0x0180:
            r20 = 0
        L_0x0182:
            r29 = r19
            r24 = r20
            r19 = r0
            goto L_0x018a
        L_0x0189:
            r5 = 4
        L_0x018a:
            long r70 = r2 & r48
            r68 = 0
            int r0 = (r70 > r68 ? 1 : (r70 == r68 ? 0 : -1))
            if (r0 == 0) goto L_0x01aa
            if (r4 == 0) goto L_0x0197
            android.databinding.ObservableInt r0 = r4.speedWatch
            goto L_0x0199
        L_0x0197:
            r0 = r31
        L_0x0199:
            r5 = 5
            r1.updateRegistration((int) r5, (android.databinding.Observable) r0)
            if (r0 == 0) goto L_0x01a8
            int r5 = r0.get()
            r31 = r0
            r26 = r5
            goto L_0x01aa
        L_0x01a8:
            r31 = r0
        L_0x01aa:
            long r70 = r2 & r52
            r68 = 0
            int r0 = (r70 > r68 ? 1 : (r70 == r68 ? 0 : -1))
            if (r0 == 0) goto L_0x01e9
            if (r4 == 0) goto L_0x01b7
            android.databinding.ObservableField<java.lang.Boolean> r0 = r4.rlDoorState
            goto L_0x01b9
        L_0x01b7:
            r0 = r32
        L_0x01b9:
            r5 = 6
            r1.updateRegistration((int) r5, (android.databinding.Observable) r0)
            if (r0 == 0) goto L_0x01c6
            java.lang.Object r5 = r0.get()
            r13 = r5
            java.lang.Boolean r13 = (java.lang.Boolean) r13
        L_0x01c6:
            boolean r5 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r13)
            long r70 = r2 & r52
            r68 = 0
            int r10 = (r70 > r68 ? 1 : (r70 == r68 ? 0 : -1))
            if (r10 == 0) goto L_0x01df
            if (r5 == 0) goto L_0x01da
            r70 = 524288(0x80000, double:2.590327E-318)
            long r2 = r2 | r70
            goto L_0x01df
        L_0x01da:
            r70 = 262144(0x40000, double:1.295163E-318)
            long r2 = r2 | r70
        L_0x01df:
            if (r5 == 0) goto L_0x01e3
            r10 = 0
            goto L_0x01e4
        L_0x01e3:
            r10 = 4
        L_0x01e4:
            r32 = r0
            r18 = r10
            r10 = r5
        L_0x01e9:
            long r70 = r2 & r46
            r68 = 0
            int r0 = (r70 > r68 ? 1 : (r70 == r68 ? 0 : -1))
            if (r0 == 0) goto L_0x0208
            if (r4 == 0) goto L_0x01f6
            android.databinding.ObservableFloat r0 = r4.turnSpeedAnge
            goto L_0x01f8
        L_0x01f6:
            r0 = r37
        L_0x01f8:
            r5 = 7
            r1.updateRegistration((int) r5, (android.databinding.Observable) r0)
            if (r0 == 0) goto L_0x0206
            float r5 = r0.get()
            r37 = r0
            r0 = r5
            goto L_0x020a
        L_0x0206:
            r37 = r0
        L_0x0208:
            r0 = r72
        L_0x020a:
            long r70 = r2 & r56
            r68 = 0
            int r5 = (r70 > r68 ? 1 : (r70 == r68 ? 0 : -1))
            if (r5 == 0) goto L_0x024f
            if (r4 == 0) goto L_0x0217
            android.databinding.ObservableField<java.lang.Boolean> r5 = r4.flDoorState
            goto L_0x0219
        L_0x0217:
            r5 = r41
        L_0x0219:
            r75 = r0
            r0 = 8
            r1.updateRegistration((int) r0, (android.databinding.Observable) r5)
            if (r5 == 0) goto L_0x022a
            java.lang.Object r20 = r5.get()
            r34 = r20
            java.lang.Boolean r34 = (java.lang.Boolean) r34
        L_0x022a:
            boolean r15 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r34)
            long r70 = r2 & r56
            r68 = 0
            int r20 = (r70 > r68 ? 1 : (r70 == r68 ? 0 : -1))
            if (r20 == 0) goto L_0x0243
            if (r15 == 0) goto L_0x023e
            r70 = 134217728(0x8000000, double:6.63123685E-316)
            long r2 = r2 | r70
            goto L_0x0243
        L_0x023e:
            r70 = 67108864(0x4000000, double:3.31561842E-316)
            long r2 = r2 | r70
        L_0x0243:
            if (r15 == 0) goto L_0x0248
            r20 = 0
            goto L_0x024a
        L_0x0248:
            r20 = 4
        L_0x024a:
            r41 = r5
            r38 = r20
            goto L_0x0253
        L_0x024f:
            r75 = r0
            r0 = 8
        L_0x0253:
            r70 = 8704(0x2200, double:4.3003E-320)
            long r70 = r2 & r70
            r68 = 0
            int r5 = (r70 > r68 ? 1 : (r70 == r68 ? 0 : -1))
            if (r5 == 0) goto L_0x0273
            if (r4 == 0) goto L_0x0262
            android.databinding.ObservableInt r5 = r4.speed
            goto L_0x0264
        L_0x0262:
            r5 = r42
        L_0x0264:
            r0 = 9
            r1.updateRegistration((int) r0, (android.databinding.Observable) r5)
            if (r5 == 0) goto L_0x0271
            int r0 = r5.get()
            r27 = r0
        L_0x0271:
            r42 = r5
        L_0x0273:
            long r70 = r2 & r58
            r68 = 0
            int r0 = (r70 > r68 ? 1 : (r70 == r68 ? 0 : -1))
            if (r0 == 0) goto L_0x02b6
            if (r4 == 0) goto L_0x0280
            android.databinding.ObservableField<java.lang.Boolean> r0 = r4.bDoorState
            goto L_0x0282
        L_0x0280:
            r0 = r43
        L_0x0282:
            r5 = 10
            r1.updateRegistration((int) r5, (android.databinding.Observable) r0)
            if (r0 == 0) goto L_0x0291
            java.lang.Object r5 = r0.get()
            r25 = r5
            java.lang.Boolean r25 = (java.lang.Boolean) r25
        L_0x0291:
            boolean r5 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r25)
            long r70 = r2 & r58
            r68 = 0
            int r6 = (r70 > r68 ? 1 : (r70 == r68 ? 0 : -1))
            if (r6 == 0) goto L_0x02a9
            if (r5 == 0) goto L_0x02a5
            r70 = 32768(0x8000, double:1.61895E-319)
            long r2 = r2 | r70
            goto L_0x02a9
        L_0x02a5:
            r70 = 16384(0x4000, double:8.0948E-320)
            long r2 = r2 | r70
        L_0x02a9:
            if (r5 == 0) goto L_0x02ae
            r74 = 0
            goto L_0x02b0
        L_0x02ae:
            r74 = 4
        L_0x02b0:
            r6 = r74
            r43 = r0
            r8 = r6
            r6 = r5
        L_0x02b6:
            long r70 = r2 & r64
            r68 = 0
            int r0 = (r70 > r68 ? 1 : (r70 == r68 ? 0 : -1))
            if (r0 == 0) goto L_0x02f8
            if (r4 == 0) goto L_0x02c3
            android.databinding.ObservableField<java.lang.Boolean> r0 = r4.brakeValue
            goto L_0x02c5
        L_0x02c3:
            r0 = r44
        L_0x02c5:
            r5 = 11
            r1.updateRegistration((int) r5, (android.databinding.Observable) r0)
            if (r0 == 0) goto L_0x02d4
            java.lang.Object r5 = r0.get()
            r36 = r5
            java.lang.Boolean r36 = (java.lang.Boolean) r36
        L_0x02d4:
            boolean r7 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r36)
            long r44 = r2 & r64
            r68 = 0
            int r5 = (r44 > r68 ? 1 : (r44 == r68 ? 0 : -1))
            if (r5 == 0) goto L_0x02ed
            if (r7 == 0) goto L_0x02e8
            r44 = 33554432(0x2000000, double:1.6578092E-316)
            long r2 = r2 | r44
            goto L_0x02ed
        L_0x02e8:
            r44 = 16777216(0x1000000, double:8.289046E-317)
            long r2 = r2 | r44
        L_0x02ed:
            if (r7 == 0) goto L_0x02f2
            r73 = 0
            goto L_0x02f4
        L_0x02f2:
            r73 = 8
        L_0x02f4:
            r33 = r73
            r44 = r0
        L_0x02f8:
            r20 = r19
            r0 = r33
            r5 = r38
            r19 = r17
            r33 = r21
            r21 = r4
            r17 = r13
            r4 = r23
            r13 = r75
            r23 = r6
            r6 = r39
            r77 = r24
            r24 = r7
            r7 = r18
            r18 = r16
            r16 = r12
            r12 = r35
            r35 = r29
            r29 = r15
            r15 = r9
            r9 = r77
            r78 = r26
            r26 = r10
            r10 = r78
            r79 = r27
            r27 = r11
            r11 = r79
            goto L_0x0365
        L_0x032e:
            r72 = r0
            r4 = r23
            r0 = r33
            r5 = r38
            r23 = r6
            r33 = r21
            r6 = r39
            r21 = r20
            r20 = r19
            r19 = r17
            r17 = r13
            r13 = r72
            r77 = r24
            r24 = r7
            r7 = r18
            r18 = r16
            r16 = r12
            r12 = r35
            r35 = r29
            r29 = r15
            r15 = r9
            r9 = r77
            r78 = r26
            r26 = r10
            r10 = r78
            r79 = r27
            r27 = r11
            r11 = r79
        L_0x0365:
            long r38 = r2 & r64
            r64 = 0
            int r38 = (r38 > r64 ? 1 : (r38 == r64 ? 0 : -1))
            if (r38 == 0) goto L_0x0375
            r76 = r15
            android.widget.ImageView r15 = r1.brakeTextView
            r15.setVisibility(r0)
            goto L_0x0377
        L_0x0375:
            r76 = r15
        L_0x0377:
            long r38 = r2 & r66
            int r15 = (r38 > r64 ? 1 : (r38 == r64 ? 0 : -1))
            if (r15 == 0) goto L_0x0382
            android.widget.ImageView r15 = r1.carImageView
            r15.setVisibility(r4)
        L_0x0382:
            long r38 = r2 & r58
            int r15 = (r38 > r64 ? 1 : (r38 == r64 ? 0 : -1))
            if (r15 == 0) goto L_0x038d
            android.widget.ImageView r15 = r1.dorrBackImageView
            r15.setVisibility(r8)
        L_0x038d:
            long r38 = r2 & r56
            int r15 = (r38 > r64 ? 1 : (r38 == r64 ? 0 : -1))
            if (r15 == 0) goto L_0x0398
            android.widget.ImageView r15 = r1.dorrLeftFlImageView
            r15.setVisibility(r5)
        L_0x0398:
            long r38 = r2 & r62
            int r15 = (r38 > r64 ? 1 : (r38 == r64 ? 0 : -1))
            if (r15 == 0) goto L_0x03a3
            android.widget.ImageView r15 = r1.dorrLeftFrImageView
            r15.setVisibility(r6)
        L_0x03a3:
            long r38 = r2 & r52
            int r15 = (r38 > r64 ? 1 : (r38 == r64 ? 0 : -1))
            if (r15 == 0) goto L_0x03ae
            android.widget.ImageView r15 = r1.dorrLeftRlImageView
            r15.setVisibility(r7)
        L_0x03ae:
            long r38 = r2 & r60
            int r15 = (r38 > r64 ? 1 : (r38 == r64 ? 0 : -1))
            if (r15 == 0) goto L_0x03b9
            android.widget.ImageView r15 = r1.imageView19
            r15.setVisibility(r14)
        L_0x03b9:
            long r38 = r2 & r54
            int r15 = (r38 > r64 ? 1 : (r38 == r64 ? 0 : -1))
            if (r15 == 0) goto L_0x03c4
            android.widget.ImageView r15 = r1.seatBeltTextView
            r15.setVisibility(r9)
        L_0x03c4:
            long r38 = r2 & r48
            int r15 = (r38 > r64 ? 1 : (r38 == r64 ? 0 : -1))
            if (r15 == 0) goto L_0x03d4
            com.wits.ksw.launcher.view.ID7SpeedImageView r15 = r1.speedPointerImageView
            r15.setImageLevel(r10)
            com.wits.ksw.launcher.view.ID7SpeedImageView r15 = r1.speedometerImageView
            r15.setImageLevel(r10)
        L_0x03d4:
            r38 = 8704(0x2200, double:4.3003E-320)
            long r38 = r2 & r38
            r48 = 0
            int r15 = (r38 > r48 ? 1 : (r38 == r48 ? 0 : -1))
            if (r15 == 0) goto L_0x03e3
            com.wits.ksw.launcher.view.ID7SpeedImageView r15 = r1.speedPointerImageView
            com.wits.ksw.launcher.model.LauncherViewModel.setSpeedRotation(r15, r11)
        L_0x03e3:
            long r38 = r2 & r50
            int r15 = (r38 > r48 ? 1 : (r38 == r48 ? 0 : -1))
            if (r15 == 0) goto L_0x03ee
            android.widget.TextView r15 = r1.temperatureTextView
            android.databinding.adapters.TextViewBindingAdapter.setText(r15, r12)
        L_0x03ee:
            long r38 = r2 & r46
            int r15 = (r38 > r48 ? 1 : (r38 == r48 ? 0 : -1))
            if (r15 == 0) goto L_0x03f9
            android.widget.ImageView r15 = r1.zspeedPointerImageView
            com.wits.ksw.launcher.model.DashboardViewModel.setRotation(r15, r13)
        L_0x03f9:
            return
        L_0x03fa:
            r0 = move-exception
            monitor-exit(r80)     // Catch:{ all -> 0x03fa }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.databinding.ActivityNtg6DashBoardBindingSw600dpLandImpl.executeBindings():void");
    }
}
