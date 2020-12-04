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

public class ActivityDashBoardAudiBindingImpl extends ActivityDashBoardAudiBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;

    static {
        sViewsWithIds.put(R.id.audi_ty_dashboard_speed_highlight_ImageView, 12);
        sViewsWithIds.put(R.id.tachometerImageView, 13);
        sViewsWithIds.put(R.id.audi_ty_dashboard_tachometer_highlight_imageView, 14);
        sViewsWithIds.put(R.id.dorr_back_imageView, 15);
    }

    public ActivityDashBoardAudiBindingImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    private ActivityDashBoardAudiBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 11, bindings[12], bindings[14], bindings[10], bindings[5], bindings[15], bindings[6], bindings[7], bindings[8], bindings[9], bindings[0], bindings[11], bindings[3], bindings[2], bindings[13], bindings[1], bindings[4]);
        this.mDirtyFlags = -1;
        this.brakeTextView.setTag((Object) null);
        this.carImageView.setTag((Object) null);
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
        if (13 != variableId) {
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
    /* JADX WARNING: Removed duplicated region for block: B:118:0x01f8  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x0237  */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x0243  */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x0261  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r74 = this;
            r1 = r74
            r2 = 0
            monitor-enter(r74)
            long r4 = r1.mDirtyFlags     // Catch:{ all -> 0x0384 }
            r2 = r4
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x0384 }
            monitor-exit(r74)     // Catch:{ all -> 0x0384 }
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
            r41 = 6143(0x17ff, double:3.035E-320)
            long r41 = r2 & r41
            int r41 = (r41 > r4 ? 1 : (r41 == r4 ? 0 : -1))
            r42 = 4608(0x1200, double:2.2767E-320)
            r44 = 4224(0x1080, double:2.087E-320)
            r46 = 4128(0x1020, double:2.0395E-320)
            r48 = 4104(0x1008, double:2.0276E-320)
            r50 = 4160(0x1040, double:2.0553E-320)
            r52 = 4112(0x1010, double:2.0316E-320)
            r54 = 4352(0x1100, double:2.15E-320)
            r56 = 4100(0x1004, double:2.0257E-320)
            r58 = 4098(0x1002, double:2.0247E-320)
            r60 = 5120(0x1400, double:2.5296E-320)
            r62 = 4097(0x1001, double:2.024E-320)
            if (r41 == 0) goto L_0x02cc
            com.wits.ksw.launcher.bean.CarInfo r4 = com.wits.ksw.launcher.model.DashboardViewModel.carInfo
            long r66 = r2 & r62
            r64 = 0
            int r5 = (r66 > r64 ? 1 : (r66 == r64 ? 0 : -1))
            r68 = r0
            r0 = 0
            if (r5 == 0) goto L_0x009e
            if (r4 == 0) goto L_0x0079
            android.databinding.ObservableBoolean r7 = r4.carImage
        L_0x0079:
            r1.updateRegistration((int) r0, (android.databinding.Observable) r7)
            if (r7 == 0) goto L_0x0082
            boolean r19 = r7.get()
        L_0x0082:
            long r66 = r2 & r62
            r64 = 0
            int r5 = (r66 > r64 ? 1 : (r66 == r64 ? 0 : -1))
            if (r5 == 0) goto L_0x0097
            if (r19 == 0) goto L_0x0092
            r66 = 262144(0x40000, double:1.295163E-318)
            long r2 = r2 | r66
            goto L_0x0097
        L_0x0092:
            r66 = 131072(0x20000, double:6.47582E-319)
            long r2 = r2 | r66
        L_0x0097:
            if (r19 == 0) goto L_0x009b
            r5 = r0
            goto L_0x009c
        L_0x009b:
            r5 = 4
        L_0x009c:
            r21 = r5
        L_0x009e:
            long r66 = r2 & r58
            r64 = 0
            int r5 = (r66 > r64 ? 1 : (r66 == r64 ? 0 : -1))
            if (r5 == 0) goto L_0x00dc
            if (r4 == 0) goto L_0x00aa
            android.databinding.ObservableField<java.lang.Boolean> r10 = r4.frDoorState
        L_0x00aa:
            r5 = 1
            r1.updateRegistration((int) r5, (android.databinding.Observable) r10)
            if (r10 == 0) goto L_0x00b8
            java.lang.Object r5 = r10.get()
            r20 = r5
            java.lang.Boolean r20 = (java.lang.Boolean) r20
        L_0x00b8:
            boolean r5 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r20)
            long r66 = r2 & r58
            r64 = 0
            int r18 = (r66 > r64 ? 1 : (r66 == r64 ? 0 : -1))
            if (r18 == 0) goto L_0x00d1
            if (r5 == 0) goto L_0x00cc
            r66 = 67108864(0x4000000, double:3.31561842E-316)
            long r2 = r2 | r66
            goto L_0x00d1
        L_0x00cc:
            r66 = 33554432(0x2000000, double:1.6578092E-316)
            long r2 = r2 | r66
        L_0x00d1:
            if (r5 == 0) goto L_0x00d6
            r18 = r0
            goto L_0x00d8
        L_0x00d6:
            r18 = 4
        L_0x00d8:
            r37 = r5
            r36 = r18
        L_0x00dc:
            long r66 = r2 & r56
            r64 = 0
            int r5 = (r66 > r64 ? 1 : (r66 == r64 ? 0 : -1))
            if (r5 == 0) goto L_0x0114
            if (r4 == 0) goto L_0x00e8
            android.databinding.ObservableField<java.lang.Boolean> r14 = r4.rrDoorState
        L_0x00e8:
            r5 = 2
            r1.updateRegistration((int) r5, (android.databinding.Observable) r14)
            if (r14 == 0) goto L_0x00f6
            java.lang.Object r5 = r14.get()
            r27 = r5
            java.lang.Boolean r27 = (java.lang.Boolean) r27
        L_0x00f6:
            boolean r5 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r27)
            long r66 = r2 & r56
            r64 = 0
            int r9 = (r66 > r64 ? 1 : (r66 == r64 ? 0 : -1))
            if (r9 == 0) goto L_0x010d
            if (r5 == 0) goto L_0x0109
            r66 = 16384(0x4000, double:8.0948E-320)
            long r2 = r2 | r66
            goto L_0x010d
        L_0x0109:
            r66 = 8192(0x2000, double:4.0474E-320)
            long r2 = r2 | r66
        L_0x010d:
            if (r5 == 0) goto L_0x0111
            r9 = r0
            goto L_0x0112
        L_0x0111:
            r9 = 4
        L_0x0112:
            r12 = r9
            r9 = r5
        L_0x0114:
            long r66 = r2 & r48
            r64 = 0
            int r5 = (r66 > r64 ? 1 : (r66 == r64 ? 0 : -1))
            if (r5 == 0) goto L_0x012e
            if (r4 == 0) goto L_0x0120
            android.databinding.ObservableField<java.lang.String> r15 = r4.tempStr
        L_0x0120:
            r5 = 3
            r1.updateRegistration((int) r5, (android.databinding.Observable) r15)
            if (r15 == 0) goto L_0x012e
            java.lang.Object r5 = r15.get()
            java.lang.String r5 = (java.lang.String) r5
            r32 = r5
        L_0x012e:
            long r66 = r2 & r52
            r64 = 0
            int r5 = (r66 > r64 ? 1 : (r66 == r64 ? 0 : -1))
            if (r5 == 0) goto L_0x0172
            if (r4 == 0) goto L_0x013b
            android.databinding.ObservableField<java.lang.Boolean> r5 = r4.seatBeltpValue
            goto L_0x013d
        L_0x013b:
            r5 = r17
        L_0x013d:
            r0 = 4
            r1.updateRegistration((int) r0, (android.databinding.Observable) r5)
            if (r5 == 0) goto L_0x014b
            java.lang.Object r17 = r5.get()
            r25 = r17
            java.lang.Boolean r25 = (java.lang.Boolean) r25
        L_0x014b:
            boolean r17 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r25)
            long r66 = r2 & r52
            r64 = 0
            int r18 = (r66 > r64 ? 1 : (r66 == r64 ? 0 : -1))
            if (r18 == 0) goto L_0x0164
            if (r17 == 0) goto L_0x015f
            r66 = 1048576(0x100000, double:5.180654E-318)
            long r2 = r2 | r66
            goto L_0x0164
        L_0x015f:
            r66 = 524288(0x80000, double:2.590327E-318)
            long r2 = r2 | r66
        L_0x0164:
            if (r17 == 0) goto L_0x0169
            r18 = 8
            goto L_0x016b
        L_0x0169:
            r18 = 0
        L_0x016b:
            r26 = r17
            r22 = r18
            r17 = r5
            goto L_0x0173
        L_0x0172:
            r0 = 4
        L_0x0173:
            long r66 = r2 & r46
            r64 = 0
            int r5 = (r66 > r64 ? 1 : (r66 == r64 ? 0 : -1))
            if (r5 == 0) goto L_0x0190
            if (r4 == 0) goto L_0x0180
            android.databinding.ObservableInt r5 = r4.speedWatch
            goto L_0x0182
        L_0x0180:
            r5 = r28
        L_0x0182:
            r0 = 5
            r1.updateRegistration((int) r0, (android.databinding.Observable) r5)
            if (r5 == 0) goto L_0x018e
            int r0 = r5.get()
            r23 = r0
        L_0x018e:
            r28 = r5
        L_0x0190:
            long r66 = r2 & r50
            r64 = 0
            int r0 = (r66 > r64 ? 1 : (r66 == r64 ? 0 : -1))
            if (r0 == 0) goto L_0x01cf
            if (r4 == 0) goto L_0x019d
            android.databinding.ObservableField<java.lang.Boolean> r0 = r4.rlDoorState
            goto L_0x019f
        L_0x019d:
            r0 = r29
        L_0x019f:
            r5 = 6
            r1.updateRegistration((int) r5, (android.databinding.Observable) r0)
            if (r0 == 0) goto L_0x01ac
            java.lang.Object r5 = r0.get()
            r11 = r5
            java.lang.Boolean r11 = (java.lang.Boolean) r11
        L_0x01ac:
            boolean r5 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r11)
            long r66 = r2 & r50
            r64 = 0
            int r8 = (r66 > r64 ? 1 : (r66 == r64 ? 0 : -1))
            if (r8 == 0) goto L_0x01c5
            if (r5 == 0) goto L_0x01c0
            r66 = 65536(0x10000, double:3.2379E-319)
            long r2 = r2 | r66
            goto L_0x01c5
        L_0x01c0:
            r66 = 32768(0x8000, double:1.61895E-319)
            long r2 = r2 | r66
        L_0x01c5:
            if (r5 == 0) goto L_0x01c9
            r8 = 0
            goto L_0x01ca
        L_0x01c9:
            r8 = 4
        L_0x01ca:
            r29 = r0
            r16 = r8
            r8 = r5
        L_0x01cf:
            long r66 = r2 & r44
            r64 = 0
            int r0 = (r66 > r64 ? 1 : (r66 == r64 ? 0 : -1))
            if (r0 == 0) goto L_0x01ee
            if (r4 == 0) goto L_0x01dc
            android.databinding.ObservableFloat r0 = r4.turnSpeedAnge
            goto L_0x01de
        L_0x01dc:
            r0 = r34
        L_0x01de:
            r5 = 7
            r1.updateRegistration((int) r5, (android.databinding.Observable) r0)
            if (r0 == 0) goto L_0x01ec
            float r5 = r0.get()
            r34 = r0
            r0 = r5
            goto L_0x01f0
        L_0x01ec:
            r34 = r0
        L_0x01ee:
            r0 = r68
        L_0x01f0:
            long r66 = r2 & r54
            r64 = 0
            int r5 = (r66 > r64 ? 1 : (r66 == r64 ? 0 : -1))
            if (r5 == 0) goto L_0x0237
            if (r4 == 0) goto L_0x01fd
            android.databinding.ObservableField<java.lang.Boolean> r5 = r4.flDoorState
            goto L_0x01ff
        L_0x01fd:
            r5 = r38
        L_0x01ff:
            r71 = r0
            r0 = 8
            r1.updateRegistration((int) r0, (android.databinding.Observable) r5)
            if (r5 == 0) goto L_0x0210
            java.lang.Object r18 = r5.get()
            r31 = r18
            java.lang.Boolean r31 = (java.lang.Boolean) r31
        L_0x0210:
            boolean r13 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r31)
            long r66 = r2 & r54
            r64 = 0
            int r18 = (r66 > r64 ? 1 : (r66 == r64 ? 0 : -1))
            if (r18 == 0) goto L_0x0229
            if (r13 == 0) goto L_0x0224
            r66 = 16777216(0x1000000, double:8.289046E-317)
            long r2 = r2 | r66
            goto L_0x0229
        L_0x0224:
            r66 = 8388608(0x800000, double:4.144523E-317)
            long r2 = r2 | r66
        L_0x0229:
            if (r13 == 0) goto L_0x022e
            r70 = 0
            goto L_0x0230
        L_0x022e:
            r70 = 4
        L_0x0230:
            r18 = r70
            r38 = r5
            r35 = r18
            goto L_0x023b
        L_0x0237:
            r71 = r0
            r0 = 8
        L_0x023b:
            long r66 = r2 & r42
            r64 = 0
            int r5 = (r66 > r64 ? 1 : (r66 == r64 ? 0 : -1))
            if (r5 == 0) goto L_0x0259
            if (r4 == 0) goto L_0x0248
            android.databinding.ObservableInt r5 = r4.speed
            goto L_0x024a
        L_0x0248:
            r5 = r39
        L_0x024a:
            r0 = 9
            r1.updateRegistration((int) r0, (android.databinding.Observable) r5)
            if (r5 == 0) goto L_0x0257
            int r0 = r5.get()
            r24 = r0
        L_0x0257:
            r39 = r5
        L_0x0259:
            long r66 = r2 & r60
            r64 = 0
            int r0 = (r66 > r64 ? 1 : (r66 == r64 ? 0 : -1))
            if (r0 == 0) goto L_0x029b
            if (r4 == 0) goto L_0x0266
            android.databinding.ObservableField<java.lang.Boolean> r0 = r4.brakeValue
            goto L_0x0268
        L_0x0266:
            r0 = r40
        L_0x0268:
            r5 = 10
            r1.updateRegistration((int) r5, (android.databinding.Observable) r0)
            if (r0 == 0) goto L_0x0277
            java.lang.Object r5 = r0.get()
            r33 = r5
            java.lang.Boolean r33 = (java.lang.Boolean) r33
        L_0x0277:
            boolean r6 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r33)
            long r40 = r2 & r60
            r64 = 0
            int r5 = (r40 > r64 ? 1 : (r40 == r64 ? 0 : -1))
            if (r5 == 0) goto L_0x0290
            if (r6 == 0) goto L_0x028b
            r40 = 4194304(0x400000, double:2.0722615E-317)
            long r2 = r2 | r40
            goto L_0x0290
        L_0x028b:
            r40 = 2097152(0x200000, double:1.0361308E-317)
            long r2 = r2 | r40
        L_0x0290:
            if (r6 == 0) goto L_0x0295
            r69 = 0
            goto L_0x0297
        L_0x0295:
            r69 = 8
        L_0x0297:
            r30 = r69
            r40 = r0
        L_0x029b:
            r18 = r15
            r0 = r30
            r5 = r35
            r15 = r10
            r30 = r19
            r10 = r24
            r24 = r9
            r19 = r17
            r9 = r23
            r23 = r8
            r17 = r14
            r8 = r22
            r22 = r6
            r14 = r7
            r7 = r16
            r6 = r36
            r16 = r11
            r11 = r32
            r32 = r26
            r26 = r13
            r13 = r71
            r73 = r20
            r20 = r4
            r4 = r21
            r21 = r73
            goto L_0x02fc
        L_0x02cc:
            r68 = r0
            r4 = r21
            r0 = r30
            r5 = r35
            r30 = r19
            r21 = r20
            r19 = r17
            r20 = r18
            r17 = r14
            r18 = r15
            r14 = r7
            r15 = r10
            r7 = r16
            r10 = r24
            r24 = r9
            r16 = r11
            r9 = r23
            r11 = r32
            r23 = r8
            r8 = r22
            r32 = r26
            r22 = r6
            r26 = r13
            r6 = r36
            r13 = r68
        L_0x02fc:
            long r35 = r2 & r60
            r60 = 0
            int r35 = (r35 > r60 ? 1 : (r35 == r60 ? 0 : -1))
            if (r35 == 0) goto L_0x030c
            r72 = r14
            android.widget.ImageView r14 = r1.brakeTextView
            r14.setVisibility(r0)
            goto L_0x030e
        L_0x030c:
            r72 = r14
        L_0x030e:
            long r35 = r2 & r62
            int r14 = (r35 > r60 ? 1 : (r35 == r60 ? 0 : -1))
            if (r14 == 0) goto L_0x0319
            android.widget.ImageView r14 = r1.carImageView
            r14.setVisibility(r4)
        L_0x0319:
            long r35 = r2 & r54
            int r14 = (r35 > r60 ? 1 : (r35 == r60 ? 0 : -1))
            if (r14 == 0) goto L_0x0324
            android.widget.ImageView r14 = r1.dorrLeftFlImageView
            r14.setVisibility(r5)
        L_0x0324:
            long r35 = r2 & r58
            int r14 = (r35 > r60 ? 1 : (r35 == r60 ? 0 : -1))
            if (r14 == 0) goto L_0x032f
            android.widget.ImageView r14 = r1.dorrLeftFrImageView
            r14.setVisibility(r6)
        L_0x032f:
            long r35 = r2 & r50
            int r14 = (r35 > r60 ? 1 : (r35 == r60 ? 0 : -1))
            if (r14 == 0) goto L_0x033a
            android.widget.ImageView r14 = r1.dorrLeftRlImageView
            r14.setVisibility(r7)
        L_0x033a:
            long r35 = r2 & r56
            int r14 = (r35 > r60 ? 1 : (r35 == r60 ? 0 : -1))
            if (r14 == 0) goto L_0x0345
            android.widget.ImageView r14 = r1.imageView19
            r14.setVisibility(r12)
        L_0x0345:
            long r35 = r2 & r52
            int r14 = (r35 > r60 ? 1 : (r35 == r60 ? 0 : -1))
            if (r14 == 0) goto L_0x0350
            android.widget.ImageView r14 = r1.seatBeltTextView
            r14.setVisibility(r8)
        L_0x0350:
            long r35 = r2 & r46
            int r14 = (r35 > r60 ? 1 : (r35 == r60 ? 0 : -1))
            if (r14 == 0) goto L_0x0360
            com.wits.ksw.launcher.view.ID7SpeedImageView r14 = r1.speedPointerImageView
            r14.setImageLevel(r9)
            com.wits.ksw.launcher.view.ID7SpeedImageView r14 = r1.speedometerImageView
            r14.setImageLevel(r9)
        L_0x0360:
            long r35 = r2 & r42
            r41 = 0
            int r14 = (r35 > r41 ? 1 : (r35 == r41 ? 0 : -1))
            if (r14 == 0) goto L_0x036d
            com.wits.ksw.launcher.view.ID7SpeedImageView r14 = r1.speedPointerImageView
            com.wits.ksw.launcher.model.LauncherViewModel.setSpeedRotation(r14, r10)
        L_0x036d:
            long r35 = r2 & r48
            int r14 = (r35 > r41 ? 1 : (r35 == r41 ? 0 : -1))
            if (r14 == 0) goto L_0x0378
            android.widget.TextView r14 = r1.temperatureTextView
            android.databinding.adapters.TextViewBindingAdapter.setText(r14, r11)
        L_0x0378:
            long r35 = r2 & r44
            int r14 = (r35 > r41 ? 1 : (r35 == r41 ? 0 : -1))
            if (r14 == 0) goto L_0x0383
            android.widget.ImageView r14 = r1.zspeedPointerImageView
            com.wits.ksw.launcher.model.DashboardViewModel.setRotation(r14, r13)
        L_0x0383:
            return
        L_0x0384:
            r0 = move-exception
            monitor-exit(r74)     // Catch:{ all -> 0x0384 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.databinding.ActivityDashBoardAudiBindingImpl.executeBindings():void");
    }
}
