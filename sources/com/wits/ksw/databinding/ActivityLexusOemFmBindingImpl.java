package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.view.lexus.LexusOEMFMViewModel;
import com.wits.ksw.launcher.view.lexus.LexusUiParams;

public class ActivityLexusOemFmBindingImpl extends ActivityLexusOemFmBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final RelativeLayout mboundView0;
    @NonNull
    private final TextView mboundView6;
    @NonNull
    private final TextView mboundView7;
    @NonNull
    private final TextView mboundView8;
    @NonNull
    private final TextView mboundView9;

    static {
        sViewsWithIds.put(R.id.ac_layout, 20);
        sViewsWithIds.put(R.id.cd_1, 21);
        sViewsWithIds.put(R.id.cd_2, 22);
        sViewsWithIds.put(R.id.cd_3, 23);
        sViewsWithIds.put(R.id.cd_4, 24);
        sViewsWithIds.put(R.id.cd_5, 25);
        sViewsWithIds.put(R.id.cd_6, 26);
    }

    public ActivityLexusOemFmBindingImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 27, sIncludes, sViewsWithIds));
    }

    private ActivityLexusOemFmBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 19, bindings[20], bindings[14], bindings[4], bindings[21], bindings[22], bindings[23], bindings[24], bindings[25], bindings[26], bindings[5], bindings[10], bindings[15], bindings[16], bindings[19], bindings[13], bindings[17], bindings[18], bindings[12], bindings[11], bindings[1], bindings[2], bindings[3]);
        this.mDirtyFlags = -1;
        this.asl.setTag((Object) null);
        this.blowMode.setTag((Object) null);
        this.cdLayout.setTag((Object) null);
        this.discNum.setTag((Object) null);
        this.fmBand.setTag((Object) null);
        this.fmCh.setTag((Object) null);
        this.fmFrequency.setTag((Object) null);
        this.fmLayout.setTag((Object) null);
        this.mboundView0 = bindings[0];
        this.mboundView0.setTag((Object) null);
        this.mboundView6 = bindings[6];
        this.mboundView6.setTag((Object) null);
        this.mboundView7 = bindings[7];
        this.mboundView7.setTag((Object) null);
        this.mboundView8 = bindings[8];
        this.mboundView8.setTag((Object) null);
        this.mboundView9 = bindings[9];
        this.mboundView9.setTag((Object) null);
        this.modeFm.setTag((Object) null);
        this.st.setTag((Object) null);
        this.time.setTag((Object) null);
        this.trackNum.setTag((Object) null);
        this.tvLeftTemperature.setTag((Object) null);
        this.tvRightTemperature.setTag((Object) null);
        this.windSpeedLevel.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
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
        if (3 == variableId) {
            setMUiParams((LexusUiParams) variable);
            return true;
        } else if (11 != variableId) {
            return false;
        } else {
            setVm((LexusOEMFMViewModel) variable);
            return true;
        }
    }

    public void setMUiParams(@Nullable LexusUiParams MUiParams) {
        this.mMUiParams = MUiParams;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
        }
        notifyPropertyChanged(3);
        super.requestRebind();
    }

    public void setVm(@Nullable LexusOEMFMViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        }
        notifyPropertyChanged(11);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVmRand((ObservableField) object, fieldId);
            case 1:
                return onChangeVmPause((ObservableField) object, fieldId);
            case 2:
                return onChangeVmDisc((ObservableField) object, fieldId);
            case 3:
                return onChangeVmTime((ObservableField) object, fieldId);
            case 4:
                return onChangeVmRpt((ObservableField) object, fieldId);
            case 5:
                return onChangeVmMode((ObservableField) object, fieldId);
            case 6:
                return onChangeVmFmFrequency((ObservableField) object, fieldId);
            case 7:
                return onChangeMUiParamsRightTempStr((ObservableField) object, fieldId);
            case 8:
                return onChangeVmScan((ObservableField) object, fieldId);
            case 9:
                return onChangeVmFmBand((ObservableField) object, fieldId);
            case 10:
                return onChangeVmAsl((ObservableField) object, fieldId);
            case 11:
                return onChangeMUiParamsBlowingMode((ObservableInt) object, fieldId);
            case 12:
                return onChangeVmCd((ObservableBoolean) object, fieldId);
            case 13:
                return onChangeVmSt((ObservableField) object, fieldId);
            case 14:
                return onChangeVmCh((ObservableField) object, fieldId);
            case 15:
                return onChangeVmFm((ObservableBoolean) object, fieldId);
            case 16:
                return onChangeMUiParamsWindSpeed((ObservableInt) object, fieldId);
            case 17:
                return onChangeVmTrack((ObservableField) object, fieldId);
            case 18:
                return onChangeMUiParamsLeftTempStr((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmRand(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeVmPause(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeVmDisc(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeVmTime(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeVmRpt(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeVmMode(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeVmFmFrequency(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeMUiParamsRightTempStr(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeVmScan(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeVmFmBand(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeVmAsl(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeMUiParamsBlowingMode(ObservableInt MUiParamsBlowingMode, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeVmCd(ObservableBoolean VmCd, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        return true;
    }

    private boolean onChangeVmSt(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
        }
        return true;
    }

    private boolean onChangeVmCh(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
        }
        return true;
    }

    private boolean onChangeVmFm(ObservableBoolean VmFm, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeMUiParamsWindSpeed(ObservableInt MUiParamsWindSpeed, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeVmTrack(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
        }
        return true;
    }

    private boolean onChangeMUiParamsLeftTempStr(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:222:0x03f9  */
    /* JADX WARNING: Removed duplicated region for block: B:237:0x042f  */
    /* JADX WARNING: Removed duplicated region for block: B:240:0x043b  */
    /* JADX WARNING: Removed duplicated region for block: B:247:0x045a  */
    /* JADX WARNING: Removed duplicated region for block: B:249:0x0494  */
    /* JADX WARNING: Removed duplicated region for block: B:252:0x04dd  */
    /* JADX WARNING: Removed duplicated region for block: B:253:0x04e5  */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x04ed  */
    /* JADX WARNING: Removed duplicated region for block: B:259:0x04f8  */
    /* JADX WARNING: Removed duplicated region for block: B:262:0x0506  */
    /* JADX WARNING: Removed duplicated region for block: B:265:0x0514  */
    /* JADX WARNING: Removed duplicated region for block: B:268:0x0522  */
    /* JADX WARNING: Removed duplicated region for block: B:271:0x0530  */
    /* JADX WARNING: Removed duplicated region for block: B:274:0x053b  */
    /* JADX WARNING: Removed duplicated region for block: B:277:0x0546  */
    /* JADX WARNING: Removed duplicated region for block: B:280:0x0551  */
    /* JADX WARNING: Removed duplicated region for block: B:283:0x055c  */
    /* JADX WARNING: Removed duplicated region for block: B:286:0x0567  */
    /* JADX WARNING: Removed duplicated region for block: B:289:0x0575  */
    /* JADX WARNING: Removed duplicated region for block: B:290:0x057f  */
    /* JADX WARNING: Removed duplicated region for block: B:293:0x0589  */
    /* JADX WARNING: Removed duplicated region for block: B:294:0x0593  */
    /* JADX WARNING: Removed duplicated region for block: B:297:0x05a0  */
    /* JADX WARNING: Removed duplicated region for block: B:298:0x05aa  */
    /* JADX WARNING: Removed duplicated region for block: B:301:0x05b7  */
    /* JADX WARNING: Removed duplicated region for block: B:302:0x05c1  */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x05cb  */
    /* JADX WARNING: Removed duplicated region for block: B:306:0x05d5  */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x05df  */
    /* JADX WARNING: Removed duplicated region for block: B:310:0x05e9  */
    /* JADX WARNING: Removed duplicated region for block: B:313:0x05f3  */
    /* JADX WARNING: Removed duplicated region for block: B:314:0x05fd  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0139  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r110 = this;
            r1 = r110
            r2 = 0
            monitor-enter(r110)
            long r4 = r1.mDirtyFlags     // Catch:{ all -> 0x0602 }
            r2 = r4
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x0602 }
            monitor-exit(r110)     // Catch:{ all -> 0x0602 }
            r0 = 0
            com.wits.ksw.launcher.view.lexus.LexusUiParams r6 = r1.mMUiParams
            r7 = 0
            r8 = 0
            com.wits.ksw.launcher.view.lexus.LexusOEMFMViewModel r9 = r1.mVm
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
            r59 = 2951296(0x2d0880, double:1.458134E-317)
            long r59 = r2 & r59
            int r59 = (r59 > r4 ? 1 : (r59 == r4 ? 0 : -1))
            r60 = 2883584(0x2c0000, double:1.42468E-317)
            r62 = 2686976(0x290000, double:1.3275425E-317)
            r64 = 2623488(0x280800, double:1.2961753E-317)
            r66 = 2621568(0x280080, double:1.2952267E-317)
            if (r59 == 0) goto L_0x010a
            long r68 = r2 & r66
            int r59 = (r68 > r4 ? 1 : (r68 == r4 ? 0 : -1))
            if (r59 == 0) goto L_0x00a5
            if (r6 == 0) goto L_0x0090
            android.databinding.ObservableField<java.lang.String> r4 = r6.rightTempStr
            goto L_0x0092
        L_0x0090:
            r4 = r30
        L_0x0092:
            r5 = 7
            r1.updateRegistration((int) r5, (android.databinding.Observable) r4)
            if (r4 == 0) goto L_0x00a3
            java.lang.Object r5 = r4.get()
            java.lang.String r5 = (java.lang.String) r5
            r30 = r4
            r31 = r5
            goto L_0x00a5
        L_0x00a3:
            r30 = r4
        L_0x00a5:
            long r4 = r2 & r64
            r68 = 0
            int r4 = (r4 > r68 ? 1 : (r4 == r68 ? 0 : -1))
            if (r4 == 0) goto L_0x00c6
            if (r6 == 0) goto L_0x00b2
            android.databinding.ObservableInt r4 = r6.blowingMode
            goto L_0x00b4
        L_0x00b2:
            r4 = r38
        L_0x00b4:
            r5 = 11
            r1.updateRegistration((int) r5, (android.databinding.Observable) r4)
            if (r4 == 0) goto L_0x00c4
            int r5 = r4.get()
            r38 = r4
            r54 = r5
            goto L_0x00c6
        L_0x00c4:
            r38 = r4
        L_0x00c6:
            long r4 = r2 & r62
            r68 = 0
            int r4 = (r4 > r68 ? 1 : (r4 == r68 ? 0 : -1))
            if (r4 == 0) goto L_0x00e7
            if (r6 == 0) goto L_0x00d3
            android.databinding.ObservableInt r4 = r6.windSpeed
            goto L_0x00d5
        L_0x00d3:
            r4 = r56
        L_0x00d5:
            r5 = 16
            r1.updateRegistration((int) r5, (android.databinding.Observable) r4)
            if (r4 == 0) goto L_0x00e5
            int r5 = r4.get()
            r56 = r4
            r39 = r5
            goto L_0x00e7
        L_0x00e5:
            r56 = r4
        L_0x00e7:
            long r4 = r2 & r60
            r68 = 0
            int r4 = (r4 > r68 ? 1 : (r4 == r68 ? 0 : -1))
            if (r4 == 0) goto L_0x010a
            if (r6 == 0) goto L_0x00f4
            android.databinding.ObservableField<java.lang.String> r4 = r6.leftTempStr
            goto L_0x00f6
        L_0x00f4:
            r4 = r58
        L_0x00f6:
            r5 = 18
            r1.updateRegistration((int) r5, (android.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0105
            java.lang.Object r5 = r4.get()
            r27 = r5
            java.lang.String r27 = (java.lang.String) r27
        L_0x0105:
            r72 = r0
            r58 = r4
            goto L_0x010c
        L_0x010a:
            r72 = r0
        L_0x010c:
            r73 = r6
            r5 = r27
            r0 = r31
            r6 = r39
            r4 = r54
            r68 = 3340159(0x32f77f, double:1.650258E-317)
            long r68 = r2 & r68
            r70 = 0
            int r27 = (r68 > r70 ? 1 : (r68 == r70 ? 0 : -1))
            r68 = 3153920(0x302000, double:1.5582435E-317)
            r74 = 3178496(0x308000, double:1.5703857E-317)
            r76 = 3145984(0x300100, double:1.5543226E-317)
            r78 = 3145744(0x300010, double:1.554204E-317)
            r80 = 3149824(0x301000, double:1.55622E-317)
            r82 = 3145730(0x300002, double:1.554197E-317)
            r84 = 3146752(0x300400, double:1.554702E-317)
            r86 = 3145729(0x300001, double:1.5541966E-317)
            if (r27 == 0) goto L_0x0494
            long r88 = r2 & r86
            r70 = 0
            int r27 = (r88 > r70 ? 1 : (r88 == r70 ? 0 : -1))
            r90 = r7
            if (r27 == 0) goto L_0x0181
            if (r9 == 0) goto L_0x0148
            android.databinding.ObservableField<java.lang.Boolean> r7 = r9.rand
            goto L_0x014a
        L_0x0148:
            r7 = r72
        L_0x014a:
            r91 = r8
            r8 = 0
            r1.updateRegistration((int) r8, (android.databinding.Observable) r7)
            if (r7 == 0) goto L_0x015a
            java.lang.Object r27 = r7.get()
            r12 = r27
            java.lang.Boolean r12 = (java.lang.Boolean) r12
        L_0x015a:
            boolean r27 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r12)
            long r88 = r2 & r86
            r70 = 0
            int r31 = (r88 > r70 ? 1 : (r88 == r70 ? 0 : -1))
            if (r31 == 0) goto L_0x0177
            if (r27 == 0) goto L_0x0170
            r88 = 137438953472(0x2000000000, double:6.7903865311E-313)
            long r2 = r2 | r88
            goto L_0x0177
        L_0x0170:
            r88 = 68719476736(0x1000000000, double:3.39519326554E-313)
            long r2 = r2 | r88
        L_0x0177:
            if (r27 == 0) goto L_0x017c
            r31 = r8
            goto L_0x017e
        L_0x017c:
            r31 = 8
        L_0x017e:
            r46 = r31
            goto L_0x0188
        L_0x0181:
            r91 = r8
            r8 = 0
            r7 = r72
            r27 = r91
        L_0x0188:
            long r88 = r2 & r82
            r70 = 0
            int r31 = (r88 > r70 ? 1 : (r88 == r70 ? 0 : -1))
            if (r31 == 0) goto L_0x01c6
            if (r9 == 0) goto L_0x0194
            android.databinding.ObservableField<java.lang.Boolean> r10 = r9.pause
        L_0x0194:
            r8 = 1
            r1.updateRegistration((int) r8, (android.databinding.Observable) r10)
            if (r10 == 0) goto L_0x01a2
            java.lang.Object r8 = r10.get()
            r47 = r8
            java.lang.Boolean r47 = (java.lang.Boolean) r47
        L_0x01a2:
            boolean r8 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r47)
            long r88 = r2 & r82
            r70 = 0
            int r31 = (r88 > r70 ? 1 : (r88 == r70 ? 0 : -1))
            if (r31 == 0) goto L_0x01bb
            if (r8 == 0) goto L_0x01b6
            r88 = 134217728(0x8000000, double:6.63123685E-316)
            long r2 = r2 | r88
            goto L_0x01bb
        L_0x01b6:
            r88 = 67108864(0x4000000, double:3.31561842E-316)
            long r2 = r2 | r88
        L_0x01bb:
            if (r8 == 0) goto L_0x01c0
            r31 = 0
            goto L_0x01c2
        L_0x01c0:
            r31 = 8
        L_0x01c2:
            r18 = r31
            r35 = r8
        L_0x01c6:
            r88 = 3145732(0x300004, double:1.554198E-317)
            long r88 = r2 & r88
            r70 = 0
            int r8 = (r88 > r70 ? 1 : (r88 == r70 ? 0 : -1))
            if (r8 == 0) goto L_0x01e2
            if (r9 == 0) goto L_0x01d5
            android.databinding.ObservableField<java.lang.String> r13 = r9.disc
        L_0x01d5:
            r8 = 2
            r1.updateRegistration((int) r8, (android.databinding.Observable) r13)
            if (r13 == 0) goto L_0x01e2
            java.lang.Object r8 = r13.get()
            java.lang.String r8 = (java.lang.String) r8
            r14 = r8
        L_0x01e2:
            r88 = 3145736(0x300008, double:1.5542E-317)
            long r88 = r2 & r88
            r70 = 0
            int r8 = (r88 > r70 ? 1 : (r88 == r70 ? 0 : -1))
            if (r8 == 0) goto L_0x0207
            if (r9 == 0) goto L_0x01f2
            android.databinding.ObservableField<java.lang.String> r8 = r9.time
            goto L_0x01f4
        L_0x01f2:
            r8 = r16
        L_0x01f4:
            r92 = r7
            r7 = 3
            r1.updateRegistration((int) r7, (android.databinding.Observable) r8)
            if (r8 == 0) goto L_0x0204
            java.lang.Object r7 = r8.get()
            java.lang.String r7 = (java.lang.String) r7
            r52 = r7
        L_0x0204:
            r16 = r8
            goto L_0x0209
        L_0x0207:
            r92 = r7
        L_0x0209:
            long r7 = r2 & r78
            r70 = 0
            int r7 = (r7 > r70 ? 1 : (r7 == r70 ? 0 : -1))
            if (r7 == 0) goto L_0x024e
            if (r9 == 0) goto L_0x0216
            android.databinding.ObservableField<java.lang.Boolean> r7 = r9.rpt
            goto L_0x0218
        L_0x0216:
            r7 = r17
        L_0x0218:
            r8 = 4
            r1.updateRegistration((int) r8, (android.databinding.Observable) r7)
            if (r7 == 0) goto L_0x0226
            java.lang.Object r8 = r7.get()
            r48 = r8
            java.lang.Boolean r48 = (java.lang.Boolean) r48
        L_0x0226:
            boolean r8 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r48)
            long r88 = r2 & r78
            r70 = 0
            int r17 = (r88 > r70 ? 1 : (r88 == r70 ? 0 : -1))
            if (r17 == 0) goto L_0x0241
            if (r8 == 0) goto L_0x023c
            r88 = 2147483648(0x80000000, double:1.0609978955E-314)
            long r2 = r2 | r88
            goto L_0x0241
        L_0x023c:
            r88 = 1073741824(0x40000000, double:5.304989477E-315)
            long r2 = r2 | r88
        L_0x0241:
            if (r8 == 0) goto L_0x0246
            r17 = 0
            goto L_0x0248
        L_0x0246:
            r17 = 8
        L_0x0248:
            r22 = r8
            r36 = r17
            r17 = r7
        L_0x024e:
            r7 = 3145760(0x300020, double:1.554212E-317)
            long r7 = r7 & r2
            r70 = 0
            int r7 = (r7 > r70 ? 1 : (r7 == r70 ? 0 : -1))
            if (r7 == 0) goto L_0x0272
            if (r9 == 0) goto L_0x025d
            android.databinding.ObservableField<java.lang.String> r7 = r9.mode
            goto L_0x025f
        L_0x025d:
            r7 = r19
        L_0x025f:
            r8 = 5
            r1.updateRegistration((int) r8, (android.databinding.Observable) r7)
            if (r7 == 0) goto L_0x0270
            java.lang.Object r8 = r7.get()
            java.lang.String r8 = (java.lang.String) r8
            r19 = r7
            r26 = r8
            goto L_0x0272
        L_0x0270:
            r19 = r7
        L_0x0272:
            r7 = 3145792(0x300040, double:1.554228E-317)
            long r7 = r7 & r2
            r70 = 0
            int r7 = (r7 > r70 ? 1 : (r7 == r70 ? 0 : -1))
            if (r7 == 0) goto L_0x0296
            if (r9 == 0) goto L_0x0281
            android.databinding.ObservableField<java.lang.String> r7 = r9.fmFrequency
            goto L_0x0283
        L_0x0281:
            r7 = r24
        L_0x0283:
            r8 = 6
            r1.updateRegistration((int) r8, (android.databinding.Observable) r7)
            if (r7 == 0) goto L_0x0294
            java.lang.Object r8 = r7.get()
            java.lang.String r8 = (java.lang.String) r8
            r24 = r7
            r53 = r8
            goto L_0x0296
        L_0x0294:
            r24 = r7
        L_0x0296:
            long r7 = r2 & r76
            r70 = 0
            int r7 = (r7 > r70 ? 1 : (r7 == r70 ? 0 : -1))
            if (r7 == 0) goto L_0x02df
            if (r9 == 0) goto L_0x02a3
            android.databinding.ObservableField<java.lang.Boolean> r7 = r9.scan
            goto L_0x02a5
        L_0x02a3:
            r7 = r32
        L_0x02a5:
            r8 = 8
            r1.updateRegistration((int) r8, (android.databinding.Observable) r7)
            if (r7 == 0) goto L_0x02b4
            java.lang.Object r31 = r7.get()
            r20 = r31
            java.lang.Boolean r20 = (java.lang.Boolean) r20
        L_0x02b4:
            boolean r31 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r20)
            long r88 = r2 & r76
            r70 = 0
            int r32 = (r88 > r70 ? 1 : (r88 == r70 ? 0 : -1))
            if (r32 == 0) goto L_0x02d1
            if (r31 == 0) goto L_0x02ca
            r88 = 34359738368(0x800000000, double:1.69759663277E-313)
            long r2 = r2 | r88
            goto L_0x02d1
        L_0x02ca:
            r88 = 17179869184(0x400000000, double:8.4879831639E-314)
            long r2 = r2 | r88
        L_0x02d1:
            if (r31 == 0) goto L_0x02d6
            r32 = 0
            goto L_0x02d8
        L_0x02d6:
            r32 = r8
        L_0x02d8:
            r50 = r31
            r40 = r32
            r32 = r7
            goto L_0x02e1
        L_0x02df:
            r8 = 8
        L_0x02e1:
            r88 = 3146240(0x300200, double:1.554449E-317)
            long r88 = r2 & r88
            r70 = 0
            int r7 = (r88 > r70 ? 1 : (r88 == r70 ? 0 : -1))
            if (r7 == 0) goto L_0x0307
            if (r9 == 0) goto L_0x02f1
            android.databinding.ObservableField<java.lang.String> r7 = r9.fmBand
            goto L_0x02f3
        L_0x02f1:
            r7 = r33
        L_0x02f3:
            r8 = 9
            r1.updateRegistration((int) r8, (android.databinding.Observable) r7)
            if (r7 == 0) goto L_0x0305
            java.lang.Object r8 = r7.get()
            java.lang.String r8 = (java.lang.String) r8
            r33 = r7
            r29 = r8
            goto L_0x0307
        L_0x0305:
            r33 = r7
        L_0x0307:
            long r7 = r2 & r84
            r70 = 0
            int r7 = (r7 > r70 ? 1 : (r7 == r70 ? 0 : -1))
            if (r7 == 0) goto L_0x034f
            if (r9 == 0) goto L_0x0314
            android.databinding.ObservableField<java.lang.Boolean> r7 = r9.asl
            goto L_0x0316
        L_0x0314:
            r7 = r34
        L_0x0316:
            r8 = 10
            r1.updateRegistration((int) r8, (android.databinding.Observable) r7)
            if (r7 == 0) goto L_0x0325
            java.lang.Object r8 = r7.get()
            r21 = r8
            java.lang.Boolean r21 = (java.lang.Boolean) r21
        L_0x0325:
            boolean r8 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r21)
            long r88 = r2 & r84
            r70 = 0
            int r25 = (r88 > r70 ? 1 : (r88 == r70 ? 0 : -1))
            if (r25 == 0) goto L_0x0342
            if (r8 == 0) goto L_0x033b
            r88 = 8589934592(0x200000000, double:4.243991582E-314)
            long r2 = r2 | r88
            goto L_0x0342
        L_0x033b:
            r88 = 4294967296(0x100000000, double:2.121995791E-314)
            long r2 = r2 | r88
        L_0x0342:
            if (r8 == 0) goto L_0x0347
            r25 = 0
            goto L_0x0349
        L_0x0347:
            r25 = 8
        L_0x0349:
            r34 = r7
            r37 = r25
            r25 = r8
        L_0x034f:
            long r7 = r2 & r80
            r70 = 0
            int r7 = (r7 > r70 ? 1 : (r7 == r70 ? 0 : -1))
            if (r7 == 0) goto L_0x0387
            if (r9 == 0) goto L_0x035c
            android.databinding.ObservableBoolean r7 = r9.cd
            goto L_0x035e
        L_0x035c:
            r7 = r43
        L_0x035e:
            r8 = 12
            r1.updateRegistration((int) r8, (android.databinding.Observable) r7)
            if (r7 == 0) goto L_0x0369
            boolean r41 = r7.get()
        L_0x0369:
            long r88 = r2 & r80
            r70 = 0
            int r8 = (r88 > r70 ? 1 : (r88 == r70 ? 0 : -1))
            if (r8 == 0) goto L_0x037e
            if (r41 == 0) goto L_0x0379
            r88 = 33554432(0x2000000, double:1.6578092E-316)
            long r2 = r2 | r88
            goto L_0x037e
        L_0x0379:
            r88 = 16777216(0x1000000, double:8.289046E-317)
            long r2 = r2 | r88
        L_0x037e:
            if (r41 == 0) goto L_0x0382
            r8 = 0
            goto L_0x0384
        L_0x0382:
            r8 = 8
        L_0x0384:
            r43 = r7
            r15 = r8
        L_0x0387:
            long r7 = r2 & r68
            r70 = 0
            int r7 = (r7 > r70 ? 1 : (r7 == r70 ? 0 : -1))
            if (r7 == 0) goto L_0x03cb
            if (r9 == 0) goto L_0x0394
            android.databinding.ObservableField<java.lang.Boolean> r7 = r9.st
            goto L_0x0396
        L_0x0394:
            r7 = r44
        L_0x0396:
            r8 = 13
            r1.updateRegistration((int) r8, (android.databinding.Observable) r7)
            if (r7 == 0) goto L_0x03a5
            java.lang.Object r8 = r7.get()
            r23 = r8
            java.lang.Boolean r23 = (java.lang.Boolean) r23
        L_0x03a5:
            boolean r8 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r23)
            long r54 = r2 & r68
            r70 = 0
            int r31 = (r54 > r70 ? 1 : (r54 == r70 ? 0 : -1))
            if (r31 == 0) goto L_0x03be
            if (r8 == 0) goto L_0x03b9
            r54 = 536870912(0x20000000, double:2.652494739E-315)
            long r2 = r2 | r54
            goto L_0x03be
        L_0x03b9:
            r54 = 268435456(0x10000000, double:1.32624737E-315)
            long r2 = r2 | r54
        L_0x03be:
            if (r8 == 0) goto L_0x03c3
            r31 = 0
            goto L_0x03c5
        L_0x03c3:
            r31 = 8
        L_0x03c5:
            r28 = r31
            r44 = r7
            r55 = r8
        L_0x03cb:
            r7 = 3162112(0x304000, double:1.562291E-317)
            long r7 = r7 & r2
            r70 = 0
            int r7 = (r7 > r70 ? 1 : (r7 == r70 ? 0 : -1))
            if (r7 == 0) goto L_0x03ef
            if (r9 == 0) goto L_0x03da
            android.databinding.ObservableField<java.lang.String> r7 = r9.ch
            goto L_0x03dc
        L_0x03da:
            r7 = r45
        L_0x03dc:
            r8 = 14
            r1.updateRegistration((int) r8, (android.databinding.Observable) r7)
            if (r7 == 0) goto L_0x03ed
            java.lang.Object r8 = r7.get()
            java.lang.String r8 = (java.lang.String) r8
            r45 = r7
            r7 = r8
            goto L_0x03f1
        L_0x03ed:
            r45 = r7
        L_0x03ef:
            r7 = r90
        L_0x03f1:
            long r88 = r2 & r74
            r70 = 0
            int r8 = (r88 > r70 ? 1 : (r88 == r70 ? 0 : -1))
            if (r8 == 0) goto L_0x042f
            if (r9 == 0) goto L_0x03fe
            android.databinding.ObservableBoolean r8 = r9.fm
            goto L_0x0400
        L_0x03fe:
            r8 = r51
        L_0x0400:
            r94 = r7
            r7 = 15
            r1.updateRegistration((int) r7, (android.databinding.Observable) r8)
            if (r8 == 0) goto L_0x040d
            boolean r42 = r8.get()
        L_0x040d:
            long r88 = r2 & r74
            r70 = 0
            int r7 = (r88 > r70 ? 1 : (r88 == r70 ? 0 : -1))
            if (r7 == 0) goto L_0x0422
            if (r42 == 0) goto L_0x041d
            r88 = 8388608(0x800000, double:4.144523E-317)
            long r2 = r2 | r88
            goto L_0x0422
        L_0x041d:
            r88 = 4194304(0x400000, double:2.0722615E-317)
            long r2 = r2 | r88
        L_0x0422:
            if (r42 == 0) goto L_0x0427
            r93 = 0
            goto L_0x0429
        L_0x0427:
            r93 = 8
        L_0x0429:
            r7 = r93
            r11 = r7
            r51 = r8
            goto L_0x0431
        L_0x042f:
            r94 = r7
        L_0x0431:
            r7 = 3276800(0x320000, double:1.6189543E-317)
            long r7 = r7 & r2
            r70 = 0
            int r7 = (r7 > r70 ? 1 : (r7 == r70 ? 0 : -1))
            if (r7 == 0) goto L_0x045a
            if (r9 == 0) goto L_0x0440
            android.databinding.ObservableField<java.lang.String> r7 = r9.track
            goto L_0x0442
        L_0x0440:
            r7 = r57
        L_0x0442:
            r8 = 17
            r1.updateRegistration((int) r8, (android.databinding.Observable) r7)
            if (r7 == 0) goto L_0x0451
            java.lang.Object r8 = r7.get()
            r49 = r8
            java.lang.String r49 = (java.lang.String) r49
        L_0x0451:
            r97 = r0
            r98 = r5
            r96 = r6
            r57 = r7
            goto L_0x0460
        L_0x045a:
            r97 = r0
            r98 = r5
            r96 = r6
        L_0x0460:
            r95 = r9
            r6 = r18
            r5 = r26
            r99 = r28
            r8 = r29
            r9 = r36
            r7 = r37
            r0 = r40
            r101 = r49
            r100 = r52
            r18 = r13
            r26 = r22
            r28 = r25
            r13 = r46
            r22 = r20
            r25 = r24
            r20 = r17
            r24 = r23
            r17 = r12
            r23 = r21
            r12 = r11
            r21 = r19
            r11 = r53
            r19 = r16
            r16 = r10
            r10 = r94
            goto L_0x04d5
        L_0x0494:
            r90 = r7
            r91 = r8
            r97 = r0
            r98 = r5
            r96 = r6
            r95 = r9
            r6 = r18
            r5 = r26
            r99 = r28
            r8 = r29
            r9 = r36
            r7 = r37
            r0 = r40
            r101 = r49
            r100 = r52
            r92 = r72
            r27 = r91
            r18 = r13
            r26 = r22
            r28 = r25
            r13 = r46
            r22 = r20
            r25 = r24
            r20 = r17
            r24 = r23
            r17 = r12
            r23 = r21
            r12 = r11
            r21 = r19
            r11 = r53
            r19 = r16
            r16 = r10
            r10 = r90
        L_0x04d5:
            long r36 = r2 & r84
            r39 = 0
            int r29 = (r36 > r39 ? 1 : (r36 == r39 ? 0 : -1))
            if (r29 == 0) goto L_0x04e5
            r102 = r5
            android.widget.TextView r5 = r1.asl
            r5.setVisibility(r7)
            goto L_0x04e7
        L_0x04e5:
            r102 = r5
        L_0x04e7:
            long r36 = r2 & r64
            int r5 = (r36 > r39 ? 1 : (r36 == r39 ? 0 : -1))
            if (r5 == 0) goto L_0x04f2
            android.widget.ImageView r5 = r1.blowMode
            r5.setImageLevel(r4)
        L_0x04f2:
            long r36 = r2 & r80
            int r5 = (r36 > r39 ? 1 : (r36 == r39 ? 0 : -1))
            if (r5 == 0) goto L_0x04fd
            android.widget.RelativeLayout r5 = r1.cdLayout
            r5.setVisibility(r15)
        L_0x04fd:
            r36 = 3145732(0x300004, double:1.554198E-317)
            long r36 = r2 & r36
            int r5 = (r36 > r39 ? 1 : (r36 == r39 ? 0 : -1))
            if (r5 == 0) goto L_0x050b
            android.widget.TextView r5 = r1.discNum
            android.databinding.adapters.TextViewBindingAdapter.setText(r5, r14)
        L_0x050b:
            r36 = 3146240(0x300200, double:1.554449E-317)
            long r36 = r2 & r36
            int r5 = (r36 > r39 ? 1 : (r36 == r39 ? 0 : -1))
            if (r5 == 0) goto L_0x0519
            android.widget.TextView r5 = r1.fmBand
            android.databinding.adapters.TextViewBindingAdapter.setText(r5, r8)
        L_0x0519:
            r36 = 3162112(0x304000, double:1.562291E-317)
            long r36 = r2 & r36
            int r5 = (r36 > r39 ? 1 : (r36 == r39 ? 0 : -1))
            if (r5 == 0) goto L_0x0527
            android.widget.TextView r5 = r1.fmCh
            android.databinding.adapters.TextViewBindingAdapter.setText(r5, r10)
        L_0x0527:
            r36 = 3145792(0x300040, double:1.554228E-317)
            long r36 = r2 & r36
            int r5 = (r36 > r39 ? 1 : (r36 == r39 ? 0 : -1))
            if (r5 == 0) goto L_0x0535
            android.widget.TextView r5 = r1.fmFrequency
            android.databinding.adapters.TextViewBindingAdapter.setText(r5, r11)
        L_0x0535:
            long r36 = r2 & r74
            int r5 = (r36 > r39 ? 1 : (r36 == r39 ? 0 : -1))
            if (r5 == 0) goto L_0x0540
            android.widget.RelativeLayout r5 = r1.fmLayout
            r5.setVisibility(r12)
        L_0x0540:
            long r36 = r2 & r86
            int r5 = (r36 > r39 ? 1 : (r36 == r39 ? 0 : -1))
            if (r5 == 0) goto L_0x054b
            android.widget.TextView r5 = r1.mboundView6
            r5.setVisibility(r13)
        L_0x054b:
            long r36 = r2 & r78
            int r5 = (r36 > r39 ? 1 : (r36 == r39 ? 0 : -1))
            if (r5 == 0) goto L_0x0556
            android.widget.TextView r5 = r1.mboundView7
            r5.setVisibility(r9)
        L_0x0556:
            long r36 = r2 & r82
            int r5 = (r36 > r39 ? 1 : (r36 == r39 ? 0 : -1))
            if (r5 == 0) goto L_0x0561
            android.widget.TextView r5 = r1.mboundView8
            r5.setVisibility(r6)
        L_0x0561:
            long r36 = r2 & r76
            int r5 = (r36 > r39 ? 1 : (r36 == r39 ? 0 : -1))
            if (r5 == 0) goto L_0x056c
            android.widget.TextView r5 = r1.mboundView9
            r5.setVisibility(r0)
        L_0x056c:
            r36 = 3145760(0x300020, double:1.554212E-317)
            long r36 = r2 & r36
            int r5 = (r36 > r39 ? 1 : (r36 == r39 ? 0 : -1))
            if (r5 == 0) goto L_0x057f
            android.widget.TextView r5 = r1.modeFm
            r103 = r0
            r0 = r102
            android.databinding.adapters.TextViewBindingAdapter.setText(r5, r0)
            goto L_0x0583
        L_0x057f:
            r103 = r0
            r0 = r102
        L_0x0583:
            long r36 = r2 & r68
            int r5 = (r36 > r39 ? 1 : (r36 == r39 ? 0 : -1))
            if (r5 == 0) goto L_0x0593
            android.widget.TextView r5 = r1.st
            r104 = r0
            r0 = r99
            r5.setVisibility(r0)
            goto L_0x0597
        L_0x0593:
            r104 = r0
            r0 = r99
        L_0x0597:
            r36 = 3145736(0x300008, double:1.5542E-317)
            long r36 = r2 & r36
            int r5 = (r36 > r39 ? 1 : (r36 == r39 ? 0 : -1))
            if (r5 == 0) goto L_0x05aa
            android.widget.TextView r5 = r1.time
            r105 = r0
            r0 = r100
            android.databinding.adapters.TextViewBindingAdapter.setText(r5, r0)
            goto L_0x05ae
        L_0x05aa:
            r105 = r0
            r0 = r100
        L_0x05ae:
            r36 = 3276800(0x320000, double:1.6189543E-317)
            long r36 = r2 & r36
            int r5 = (r36 > r39 ? 1 : (r36 == r39 ? 0 : -1))
            if (r5 == 0) goto L_0x05c1
            android.widget.TextView r5 = r1.trackNum
            r106 = r0
            r0 = r101
            android.databinding.adapters.TextViewBindingAdapter.setText(r5, r0)
            goto L_0x05c5
        L_0x05c1:
            r106 = r0
            r0 = r101
        L_0x05c5:
            long r36 = r2 & r60
            int r5 = (r36 > r39 ? 1 : (r36 == r39 ? 0 : -1))
            if (r5 == 0) goto L_0x05d5
            android.widget.TextView r5 = r1.tvLeftTemperature
            r107 = r0
            r0 = r98
            android.databinding.adapters.TextViewBindingAdapter.setText(r5, r0)
            goto L_0x05d9
        L_0x05d5:
            r107 = r0
            r0 = r98
        L_0x05d9:
            long r36 = r2 & r66
            int r5 = (r36 > r39 ? 1 : (r36 == r39 ? 0 : -1))
            if (r5 == 0) goto L_0x05e9
            android.widget.TextView r5 = r1.tvRightTemperature
            r108 = r0
            r0 = r97
            android.databinding.adapters.TextViewBindingAdapter.setText(r5, r0)
            goto L_0x05ed
        L_0x05e9:
            r108 = r0
            r0 = r97
        L_0x05ed:
            long r36 = r2 & r62
            int r5 = (r36 > r39 ? 1 : (r36 == r39 ? 0 : -1))
            if (r5 == 0) goto L_0x05fd
            android.widget.ImageView r5 = r1.windSpeedLevel
            r109 = r0
            r0 = r96
            r5.setImageLevel(r0)
            goto L_0x0601
        L_0x05fd:
            r109 = r0
            r0 = r96
        L_0x0601:
            return
        L_0x0602:
            r0 = move-exception
            monitor-exit(r110)     // Catch:{ all -> 0x0602 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.databinding.ActivityLexusOemFmBindingImpl.executeBindings():void");
    }
}
