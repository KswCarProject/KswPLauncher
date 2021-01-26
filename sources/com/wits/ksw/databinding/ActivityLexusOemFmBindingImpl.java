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
    private long mDirtyFlags_1;
    @NonNull
    private final RelativeLayout mboundView0;
    @NonNull
    private final TextView mboundView1;
    @NonNull
    private final TextView mboundView14;
    @NonNull
    private final TextView mboundView15;
    @NonNull
    private final TextView mboundView16;
    @NonNull
    private final TextView mboundView17;
    @NonNull
    private final TextView mboundView30;
    @NonNull
    private final TextView mboundView31;
    @NonNull
    private final TextView mboundView32;
    @NonNull
    private final TextView mboundView33;
    @NonNull
    private final TextView mboundView34;

    static {
        sViewsWithIds.put(R.id.ac_layout, 40);
    }

    public ActivityLexusOemFmBindingImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 41, sIncludes, sViewsWithIds));
    }

    private ActivityLexusOemFmBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 33, bindings[40], bindings[22], bindings[5], bindings[7], bindings[8], bindings[9], bindings[10], bindings[11], bindings[12], bindings[13], bindings[6], bindings[18], bindings[23], bindings[24], bindings[27], bindings[21], bindings[25], bindings[26], bindings[20], bindings[19], bindings[2], bindings[3], bindings[29], bindings[39], bindings[28], bindings[37], bindings[36], bindings[35], bindings[38], bindings[4]);
        this.mDirtyFlags = -1;
        this.mDirtyFlags_1 = -1;
        this.asl.setTag((Object) null);
        this.blowMode.setTag((Object) null);
        this.cd1.setTag((Object) null);
        this.cd2.setTag((Object) null);
        this.cd3.setTag((Object) null);
        this.cd4.setTag((Object) null);
        this.cd5.setTag((Object) null);
        this.cd6.setTag((Object) null);
        this.cdLayout.setTag((Object) null);
        this.discMode.setTag((Object) null);
        this.discNum.setTag((Object) null);
        this.fmBand.setTag((Object) null);
        this.fmCh.setTag((Object) null);
        this.fmFrequency.setTag((Object) null);
        this.fmLayout.setTag((Object) null);
        this.mboundView0 = bindings[0];
        this.mboundView0.setTag((Object) null);
        this.mboundView1 = bindings[1];
        this.mboundView1.setTag((Object) null);
        this.mboundView14 = bindings[14];
        this.mboundView14.setTag((Object) null);
        this.mboundView15 = bindings[15];
        this.mboundView15.setTag((Object) null);
        this.mboundView16 = bindings[16];
        this.mboundView16.setTag((Object) null);
        this.mboundView17 = bindings[17];
        this.mboundView17.setTag((Object) null);
        this.mboundView30 = bindings[30];
        this.mboundView30.setTag((Object) null);
        this.mboundView31 = bindings[31];
        this.mboundView31.setTag((Object) null);
        this.mboundView32 = bindings[32];
        this.mboundView32.setTag((Object) null);
        this.mboundView33 = bindings[33];
        this.mboundView33.setTag((Object) null);
        this.mboundView34 = bindings[34];
        this.mboundView34.setTag((Object) null);
        this.modeFm.setTag((Object) null);
        this.st.setTag((Object) null);
        this.time.setTag((Object) null);
        this.trackNum.setTag((Object) null);
        this.tvLeftTemperature.setTag((Object) null);
        this.tvRightTemperature.setTag((Object) null);
        this.usbAsl.setTag((Object) null);
        this.usbIndex.setTag((Object) null);
        this.usbLayout.setTag((Object) null);
        this.usbMusicAlbum.setTag((Object) null);
        this.usbMusicArtist.setTag((Object) null);
        this.usbMusicName.setTag((Object) null);
        this.usbMusicTime.setTag((Object) null);
        this.windSpeedLevel.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 34359738368L;
            this.mDirtyFlags_1 = 0;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags == 0) {
                if (this.mDirtyFlags_1 == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    public boolean setVariable(int variableId, @Nullable Object variable) {
        if (4 == variableId) {
            setMUiParams((LexusUiParams) variable);
            return true;
        } else if (17 != variableId) {
            return false;
        } else {
            setVm((LexusOEMFMViewModel) variable);
            return true;
        }
    }

    public void setMUiParams(@Nullable LexusUiParams MUiParams) {
        this.mMUiParams = MUiParams;
        synchronized (this) {
            this.mDirtyFlags |= 8589934592L;
        }
        notifyPropertyChanged(4);
        super.requestRebind();
    }

    public void setVm(@Nullable LexusOEMFMViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 17179869184L;
        }
        notifyPropertyChanged(17);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVmRand((ObservableField) object, fieldId);
            case 1:
                return onChangeVmIndex((ObservableField) object, fieldId);
            case 2:
                return onChangeVmCd6((ObservableField) object, fieldId);
            case 3:
                return onChangeVmMode((ObservableField) object, fieldId);
            case 4:
                return onChangeVmMusicName((ObservableField) object, fieldId);
            case 5:
                return onChangeVmFmFrequency((ObservableField) object, fieldId);
            case 6:
                return onChangeVmCd5((ObservableField) object, fieldId);
            case 7:
                return onChangeVmFmBand((ObservableField) object, fieldId);
            case 8:
                return onChangeVmArtist((ObservableField) object, fieldId);
            case 9:
                return onChangeVmCh((ObservableField) object, fieldId);
            case 10:
                return onChangeVmCd4((ObservableField) object, fieldId);
            case 11:
                return onChangeVmCdMode((ObservableField) object, fieldId);
            case 12:
                return onChangeVmFm((ObservableBoolean) object, fieldId);
            case 13:
                return onChangeVmCd3((ObservableField) object, fieldId);
            case 14:
                return onChangeVmAlbum((ObservableField) object, fieldId);
            case 15:
                return onChangeVmUsbStatus((ObservableField) object, fieldId);
            case 16:
                return onChangeVmDisc((ObservableField) object, fieldId);
            case 17:
                return onChangeVmTime((ObservableField) object, fieldId);
            case 18:
                return onChangeVmRpt((ObservableField) object, fieldId);
            case 19:
                return onChangeVmCd2((ObservableField) object, fieldId);
            case 20:
                return onChangeVmUsbMusicTime((ObservableField) object, fieldId);
            case 21:
                return onChangeVmUsb((ObservableBoolean) object, fieldId);
            case 22:
                return onChangeVmCd1((ObservableField) object, fieldId);
            case 23:
                return onChangeMUiParamsRightTempStr((ObservableField) object, fieldId);
            case 24:
                return onChangeVmScan((ObservableField) object, fieldId);
            case 25:
                return onChangeVmAsl((ObservableField) object, fieldId);
            case 26:
                return onChangeMUiParamsBlowingMode((ObservableInt) object, fieldId);
            case 27:
                return onChangeVmCd((ObservableBoolean) object, fieldId);
            case 28:
                return onChangeVmSt((ObservableField) object, fieldId);
            case 29:
                return onChangeMUiParamsWindSpeed((ObservableInt) object, fieldId);
            case 30:
                return onChangeVmAudioOff((ObservableBoolean) object, fieldId);
            case 31:
                return onChangeVmTrack((ObservableField) object, fieldId);
            case 32:
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

    private boolean onChangeVmIndex(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeVmCd6(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeVmMode(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeVmMusicName(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeVmFmFrequency(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeVmCd5(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeVmFmBand(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeVmArtist(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeVmCh(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeVmCd4(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeVmCdMode(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeVmFm(ObservableBoolean VmFm, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        return true;
    }

    private boolean onChangeVmCd3(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
        }
        return true;
    }

    private boolean onChangeVmAlbum(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
        }
        return true;
    }

    private boolean onChangeVmUsbStatus(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeVmDisc(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeVmTime(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
        }
        return true;
    }

    private boolean onChangeVmRpt(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
        }
        return true;
    }

    private boolean onChangeVmCd2(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
        }
        return true;
    }

    private boolean onChangeVmUsbMusicTime(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        }
        return true;
    }

    private boolean onChangeVmUsb(ObservableBoolean VmUsb, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
        }
        return true;
    }

    private boolean onChangeVmCd1(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4194304;
        }
        return true;
    }

    private boolean onChangeMUiParamsRightTempStr(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8388608;
        }
        return true;
    }

    private boolean onChangeVmScan(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16777216;
        }
        return true;
    }

    private boolean onChangeVmAsl(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 33554432;
        }
        return true;
    }

    private boolean onChangeMUiParamsBlowingMode(ObservableInt MUiParamsBlowingMode, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 67108864;
        }
        return true;
    }

    private boolean onChangeVmCd(ObservableBoolean VmCd, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 134217728;
        }
        return true;
    }

    private boolean onChangeVmSt(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 268435456;
        }
        return true;
    }

    private boolean onChangeMUiParamsWindSpeed(ObservableInt MUiParamsWindSpeed, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 536870912;
        }
        return true;
    }

    private boolean onChangeVmAudioOff(ObservableBoolean VmAudioOff, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1073741824;
        }
        return true;
    }

    private boolean onChangeVmTrack(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2147483648L;
        }
        return true;
    }

    private boolean onChangeMUiParamsLeftTempStr(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4294967296L;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:432:0x081f  */
    /* JADX WARNING: Removed duplicated region for block: B:435:0x0877  */
    /* JADX WARNING: Removed duplicated region for block: B:436:0x0884  */
    /* JADX WARNING: Removed duplicated region for block: B:439:0x0893  */
    /* JADX WARNING: Removed duplicated region for block: B:442:0x089e  */
    /* JADX WARNING: Removed duplicated region for block: B:445:0x08a9  */
    /* JADX WARNING: Removed duplicated region for block: B:448:0x08b4  */
    /* JADX WARNING: Removed duplicated region for block: B:451:0x08bf  */
    /* JADX WARNING: Removed duplicated region for block: B:454:0x08ca  */
    /* JADX WARNING: Removed duplicated region for block: B:457:0x08d5  */
    /* JADX WARNING: Removed duplicated region for block: B:460:0x08e0  */
    /* JADX WARNING: Removed duplicated region for block: B:463:0x08f0  */
    /* JADX WARNING: Removed duplicated region for block: B:466:0x0900  */
    /* JADX WARNING: Removed duplicated region for block: B:469:0x0910  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x01c0  */
    /* JADX WARNING: Removed duplicated region for block: B:472:0x0920  */
    /* JADX WARNING: Removed duplicated region for block: B:473:0x092a  */
    /* JADX WARNING: Removed duplicated region for block: B:476:0x0939  */
    /* JADX WARNING: Removed duplicated region for block: B:477:0x0943  */
    /* JADX WARNING: Removed duplicated region for block: B:480:0x094d  */
    /* JADX WARNING: Removed duplicated region for block: B:481:0x0957  */
    /* JADX WARNING: Removed duplicated region for block: B:484:0x0961  */
    /* JADX WARNING: Removed duplicated region for block: B:485:0x096b  */
    /* JADX WARNING: Removed duplicated region for block: B:488:0x0975  */
    /* JADX WARNING: Removed duplicated region for block: B:489:0x0984  */
    /* JADX WARNING: Removed duplicated region for block: B:492:0x0990  */
    /* JADX WARNING: Removed duplicated region for block: B:493:0x099f  */
    /* JADX WARNING: Removed duplicated region for block: B:496:0x09b0  */
    /* JADX WARNING: Removed duplicated region for block: B:497:0x09bf  */
    /* JADX WARNING: Removed duplicated region for block: B:500:0x09d0  */
    /* JADX WARNING: Removed duplicated region for block: B:501:0x09df  */
    /* JADX WARNING: Removed duplicated region for block: B:504:0x09f0  */
    /* JADX WARNING: Removed duplicated region for block: B:505:0x09ff  */
    /* JADX WARNING: Removed duplicated region for block: B:508:0x0a10  */
    /* JADX WARNING: Removed duplicated region for block: B:509:0x0a1a  */
    /* JADX WARNING: Removed duplicated region for block: B:512:0x0a29  */
    /* JADX WARNING: Removed duplicated region for block: B:513:0x0a33  */
    /* JADX WARNING: Removed duplicated region for block: B:516:0x0a42  */
    /* JADX WARNING: Removed duplicated region for block: B:517:0x0a4c  */
    /* JADX WARNING: Removed duplicated region for block: B:520:0x0a5b  */
    /* JADX WARNING: Removed duplicated region for block: B:521:0x0a65  */
    /* JADX WARNING: Removed duplicated region for block: B:524:0x0a74  */
    /* JADX WARNING: Removed duplicated region for block: B:525:0x0a7e  */
    /* JADX WARNING: Removed duplicated region for block: B:528:0x0a8d  */
    /* JADX WARNING: Removed duplicated region for block: B:529:0x0a97  */
    /* JADX WARNING: Removed duplicated region for block: B:532:0x0aa6  */
    /* JADX WARNING: Removed duplicated region for block: B:533:0x0ab0  */
    /* JADX WARNING: Removed duplicated region for block: B:536:0x0abf  */
    /* JADX WARNING: Removed duplicated region for block: B:537:0x0ac9  */
    /* JADX WARNING: Removed duplicated region for block: B:540:0x0ad8  */
    /* JADX WARNING: Removed duplicated region for block: B:541:0x0ae2  */
    /* JADX WARNING: Removed duplicated region for block: B:544:0x0af1  */
    /* JADX WARNING: Removed duplicated region for block: B:545:0x0afb  */
    /* JADX WARNING: Removed duplicated region for block: B:548:0x0b0a  */
    /* JADX WARNING: Removed duplicated region for block: B:549:0x0b14  */
    /* JADX WARNING: Removed duplicated region for block: B:552:0x0b23  */
    /* JADX WARNING: Removed duplicated region for block: B:553:0x0b2d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r180 = this;
            r1 = r180
            r2 = 0
            r4 = 0
            monitor-enter(r180)
            long r6 = r1.mDirtyFlags     // Catch:{ all -> 0x0b32 }
            r2 = r6
            r6 = 0
            r1.mDirtyFlags = r6     // Catch:{ all -> 0x0b32 }
            long r8 = r1.mDirtyFlags_1     // Catch:{ all -> 0x0b32 }
            r4 = r8
            r1.mDirtyFlags_1 = r6     // Catch:{ all -> 0x0b32 }
            monitor-exit(r180)     // Catch:{ all -> 0x0b32 }
            r0 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            com.wits.ksw.launcher.view.lexus.LexusUiParams r12 = r1.mMUiParams
            r13 = 0
            com.wits.ksw.launcher.view.lexus.LexusOEMFMViewModel r14 = r1.mVm
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
            r101 = 47857008640(0xb24800000, double:2.36445038818E-313)
            long r101 = r2 & r101
            int r101 = (r101 > r6 ? 1 : (r101 == r6 ? 0 : -1))
            if (r101 == 0) goto L_0x016b
            r101 = 42958061568(0xa00800000, double:2.12241024327E-313)
            long r101 = r2 & r101
            int r101 = (r101 > r6 ? 1 : (r101 == r6 ? 0 : -1))
            if (r101 == 0) goto L_0x00fa
            if (r12 == 0) goto L_0x00e4
            android.databinding.ObservableField<java.lang.String> r6 = r12.rightTempStr
            goto L_0x00e6
        L_0x00e4:
            r6 = r83
        L_0x00e6:
            r7 = 23
            r1.updateRegistration((int) r7, (android.databinding.Observable) r6)
            if (r6 == 0) goto L_0x00f8
            java.lang.Object r7 = r6.get()
            java.lang.String r7 = (java.lang.String) r7
            r83 = r6
            r31 = r7
            goto L_0x00fa
        L_0x00f8:
            r83 = r6
        L_0x00fa:
            r6 = 43016781824(0xa04000000, double:2.1253114094E-313)
            long r6 = r6 & r2
            r101 = 0
            int r6 = (r6 > r101 ? 1 : (r6 == r101 ? 0 : -1))
            if (r6 == 0) goto L_0x011f
            if (r12 == 0) goto L_0x010b
            android.databinding.ObservableInt r6 = r12.blowingMode
            goto L_0x010d
        L_0x010b:
            r6 = r87
        L_0x010d:
            r7 = 26
            r1.updateRegistration((int) r7, (android.databinding.Observable) r6)
            if (r6 == 0) goto L_0x011d
            int r7 = r6.get()
            r87 = r6
            r49 = r7
            goto L_0x011f
        L_0x011d:
            r87 = r6
        L_0x011f:
            r6 = 43486543872(0xa20000000, double:2.14852073835E-313)
            long r6 = r6 & r2
            r101 = 0
            int r6 = (r6 > r101 ? 1 : (r6 == r101 ? 0 : -1))
            if (r6 == 0) goto L_0x0144
            if (r12 == 0) goto L_0x0130
            android.databinding.ObservableInt r6 = r12.windSpeed
            goto L_0x0132
        L_0x0130:
            r6 = r96
        L_0x0132:
            r7 = 29
            r1.updateRegistration((int) r7, (android.databinding.Observable) r6)
            if (r6 == 0) goto L_0x0142
            int r7 = r6.get()
            r96 = r6
            r37 = r7
            goto L_0x0144
        L_0x0142:
            r96 = r6
        L_0x0144:
            r6 = 47244640256(0xb00000000, double:2.33419537006E-313)
            long r6 = r6 & r2
            r101 = 0
            int r6 = (r6 > r101 ? 1 : (r6 == r101 ? 0 : -1))
            if (r6 == 0) goto L_0x016b
            if (r12 == 0) goto L_0x0155
            android.databinding.ObservableField<java.lang.String> r6 = r12.leftTempStr
            goto L_0x0157
        L_0x0155:
            r6 = r100
        L_0x0157:
            r7 = 32
            r1.updateRegistration((int) r7, (android.databinding.Observable) r6)
            if (r6 == 0) goto L_0x0166
            java.lang.Object r7 = r6.get()
            r82 = r7
            java.lang.String r82 = (java.lang.String) r82
        L_0x0166:
            r105 = r0
            r100 = r6
            goto L_0x016d
        L_0x016b:
            r105 = r0
        L_0x016d:
            r106 = r8
            r0 = r31
            r8 = r37
            r7 = r49
            r6 = r82
            r101 = 55222206463(0xcdb7fffff, double:2.7283395101E-313)
            long r101 = r2 & r101
            r103 = 0
            int r31 = (r101 > r103 ? 1 : (r101 == r103 ? 0 : -1))
            r101 = 51539869696(0xc00040000, double:2.5464079008E-313)
            r107 = 52613349376(0xc40000000, double:2.59944484393E-313)
            r109 = 51539611648(0xc00001000, double:2.54639515153E-313)
            r111 = 51673825280(0xc08000000, double:2.553026186E-313)
            r113 = 51539607616(0xc00000040, double:2.5463949523E-313)
            r115 = 51539608576(0xc00000400, double:2.54639499975E-313)
            r117 = 51539615744(0xc00002000, double:2.5463953539E-313)
            r119 = 51540131840(0xc00080000, double:2.54642085243E-313)
            r121 = 51543801856(0xc00400000, double:2.5466021753E-313)
            r123 = 51539607556(0xc00000004, double:2.54639494936E-313)
            r125 = 51573161984(0xc02000000, double:2.54805275837E-313)
            r127 = 51539607553(0xc00000001, double:2.5463949492E-313)
            if (r31 == 0) goto L_0x081f
            long r129 = r2 & r127
            r103 = 0
            int r31 = (r129 > r103 ? 1 : (r129 == r103 ? 0 : -1))
            r131 = r9
            if (r31 == 0) goto L_0x0204
            if (r14 == 0) goto L_0x01cf
            android.databinding.ObservableField<java.lang.Boolean> r9 = r14.rand
            goto L_0x01d1
        L_0x01cf:
            r9 = r105
        L_0x01d1:
            r132 = r10
            r10 = 0
            r1.updateRegistration((int) r10, (android.databinding.Observable) r9)
            if (r9 == 0) goto L_0x01e1
            java.lang.Object r31 = r9.get()
            r16 = r31
            java.lang.Boolean r16 = (java.lang.Boolean) r16
        L_0x01e1:
            boolean r13 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r16)
            long r129 = r2 & r127
            r103 = 0
            int r31 = (r129 > r103 ? 1 : (r129 == r103 ? 0 : -1))
            if (r31 == 0) goto L_0x01f8
            if (r13 == 0) goto L_0x01f4
            r129 = 2
            long r4 = r4 | r129
            goto L_0x01f8
        L_0x01f4:
            r129 = 1
            long r4 = r4 | r129
        L_0x01f8:
            if (r13 == 0) goto L_0x01fd
            r31 = r10
            goto L_0x01ff
        L_0x01fd:
            r31 = 8
        L_0x01ff:
            r105 = r9
            r93 = r31
            goto L_0x0207
        L_0x0204:
            r132 = r10
            r10 = 0
        L_0x0207:
            r129 = 51539607554(0xc00000002, double:2.54639494926E-313)
            long r129 = r2 & r129
            r103 = 0
            int r9 = (r129 > r103 ? 1 : (r129 == r103 ? 0 : -1))
            if (r9 == 0) goto L_0x0226
            if (r14 == 0) goto L_0x0218
            android.databinding.ObservableField<java.lang.String> r11 = r14.index
        L_0x0218:
            r9 = 1
            r1.updateRegistration((int) r9, (android.databinding.Observable) r11)
            if (r11 == 0) goto L_0x0226
            java.lang.Object r9 = r11.get()
            java.lang.String r9 = (java.lang.String) r9
            r91 = r9
        L_0x0226:
            long r129 = r2 & r123
            r103 = 0
            int r9 = (r129 > r103 ? 1 : (r129 == r103 ? 0 : -1))
            if (r9 == 0) goto L_0x0267
            if (r14 == 0) goto L_0x0233
            android.databinding.ObservableField<java.lang.Boolean> r9 = r14.cd6
            goto L_0x0235
        L_0x0233:
            r9 = r19
        L_0x0235:
            r10 = 2
            r1.updateRegistration((int) r10, (android.databinding.Observable) r9)
            if (r9 == 0) goto L_0x0243
            java.lang.Object r10 = r9.get()
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            r106 = r10
        L_0x0243:
            boolean r10 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r106)
            long r129 = r2 & r123
            r103 = 0
            int r19 = (r129 > r103 ? 1 : (r129 == r103 ? 0 : -1))
            if (r19 == 0) goto L_0x025a
            if (r10 == 0) goto L_0x0256
            r129 = 36028797018963968(0x80000000000000, double:2.8480945388892178E-306)
            long r2 = r2 | r129
            goto L_0x025a
        L_0x0256:
            r129 = 18014398509481984(0x40000000000000, double:1.7800590868057611E-307)
            long r2 = r2 | r129
        L_0x025a:
            if (r10 == 0) goto L_0x025f
            r19 = 0
            goto L_0x0261
        L_0x025f:
            r19 = 8
        L_0x0261:
            r68 = r10
            r52 = r19
            r19 = r9
        L_0x0267:
            r9 = 51539607560(0xc00000008, double:2.54639494955E-313)
            long r9 = r9 & r2
            r103 = 0
            int r9 = (r9 > r103 ? 1 : (r9 == r103 ? 0 : -1))
            if (r9 == 0) goto L_0x028d
            if (r14 == 0) goto L_0x0278
            android.databinding.ObservableField<java.lang.String> r9 = r14.mode
            goto L_0x027a
        L_0x0278:
            r9 = r20
        L_0x027a:
            r10 = 3
            r1.updateRegistration((int) r10, (android.databinding.Observable) r9)
            if (r9 == 0) goto L_0x028b
            java.lang.Object r10 = r9.get()
            java.lang.String r10 = (java.lang.String) r10
            r20 = r9
            r81 = r10
            goto L_0x028d
        L_0x028b:
            r20 = r9
        L_0x028d:
            r9 = 51539607568(0xc00000010, double:2.54639494995E-313)
            long r9 = r9 & r2
            r103 = 0
            int r9 = (r9 > r103 ? 1 : (r9 == r103 ? 0 : -1))
            if (r9 == 0) goto L_0x02b3
            if (r14 == 0) goto L_0x029e
            android.databinding.ObservableField<java.lang.String> r9 = r14.musicName
            goto L_0x02a0
        L_0x029e:
            r9 = r23
        L_0x02a0:
            r10 = 4
            r1.updateRegistration((int) r10, (android.databinding.Observable) r9)
            if (r9 == 0) goto L_0x02b1
            java.lang.Object r10 = r9.get()
            java.lang.String r10 = (java.lang.String) r10
            r23 = r9
            r28 = r10
            goto L_0x02b3
        L_0x02b1:
            r23 = r9
        L_0x02b3:
            r9 = 51539607584(0xc00000020, double:2.54639495074E-313)
            long r9 = r9 & r2
            r103 = 0
            int r9 = (r9 > r103 ? 1 : (r9 == r103 ? 0 : -1))
            if (r9 == 0) goto L_0x02d9
            if (r14 == 0) goto L_0x02c4
            android.databinding.ObservableField<java.lang.String> r9 = r14.fmFrequency
            goto L_0x02c6
        L_0x02c4:
            r9 = r26
        L_0x02c6:
            r10 = 5
            r1.updateRegistration((int) r10, (android.databinding.Observable) r9)
            if (r9 == 0) goto L_0x02d7
            java.lang.Object r10 = r9.get()
            java.lang.String r10 = (java.lang.String) r10
            r26 = r9
            r48 = r10
            goto L_0x02d9
        L_0x02d7:
            r26 = r9
        L_0x02d9:
            long r9 = r2 & r113
            r103 = 0
            int r9 = (r9 > r103 ? 1 : (r9 == r103 ? 0 : -1))
            if (r9 == 0) goto L_0x0320
            if (r14 == 0) goto L_0x02e6
            android.databinding.ObservableField<java.lang.Boolean> r9 = r14.cd5
            goto L_0x02e8
        L_0x02e6:
            r9 = r27
        L_0x02e8:
            r10 = 6
            r1.updateRegistration((int) r10, (android.databinding.Observable) r9)
            if (r9 == 0) goto L_0x02f6
            java.lang.Object r10 = r9.get()
            r21 = r10
            java.lang.Boolean r21 = (java.lang.Boolean) r21
        L_0x02f6:
            boolean r10 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r21)
            long r129 = r2 & r113
            r103 = 0
            int r27 = (r129 > r103 ? 1 : (r129 == r103 ? 0 : -1))
            if (r27 == 0) goto L_0x0313
            if (r10 == 0) goto L_0x030c
            r129 = 8796093022208(0x80000000000, double:4.345847379897E-311)
            long r2 = r2 | r129
            goto L_0x0313
        L_0x030c:
            r129 = 4398046511104(0x40000000000, double:2.1729236899484E-311)
            long r2 = r2 | r129
        L_0x0313:
            if (r10 == 0) goto L_0x0318
            r27 = 0
            goto L_0x031a
        L_0x0318:
            r27 = 8
        L_0x031a:
            r17 = r27
            r27 = r9
            r63 = r10
        L_0x0320:
            r9 = 51539607680(0xc00000080, double:2.5463949555E-313)
            long r9 = r9 & r2
            r103 = 0
            int r9 = (r9 > r103 ? 1 : (r9 == r103 ? 0 : -1))
            if (r9 == 0) goto L_0x0346
            if (r14 == 0) goto L_0x0331
            android.databinding.ObservableField<java.lang.String> r9 = r14.fmBand
            goto L_0x0333
        L_0x0331:
            r9 = r32
        L_0x0333:
            r10 = 7
            r1.updateRegistration((int) r10, (android.databinding.Observable) r9)
            if (r9 == 0) goto L_0x0344
            java.lang.Object r10 = r9.get()
            java.lang.String r10 = (java.lang.String) r10
            r32 = r9
            r30 = r10
            goto L_0x0346
        L_0x0344:
            r32 = r9
        L_0x0346:
            r9 = 51539607808(0xc00000100, double:2.5463949618E-313)
            long r9 = r9 & r2
            r103 = 0
            int r9 = (r9 > r103 ? 1 : (r9 == r103 ? 0 : -1))
            if (r9 == 0) goto L_0x036e
            if (r14 == 0) goto L_0x0357
            android.databinding.ObservableField<java.lang.String> r9 = r14.artist
            goto L_0x0359
        L_0x0357:
            r9 = r36
        L_0x0359:
            r10 = 8
            r1.updateRegistration((int) r10, (android.databinding.Observable) r9)
            if (r9 == 0) goto L_0x036b
            java.lang.Object r31 = r9.get()
            java.lang.String r31 = (java.lang.String) r31
            r36 = r9
            r62 = r31
            goto L_0x0370
        L_0x036b:
            r36 = r9
            goto L_0x0370
        L_0x036e:
            r10 = 8
        L_0x0370:
            r129 = 51539608064(0xc00000200, double:2.54639497445E-313)
            long r129 = r2 & r129
            r103 = 0
            int r9 = (r129 > r103 ? 1 : (r129 == r103 ? 0 : -1))
            if (r9 == 0) goto L_0x0398
            if (r14 == 0) goto L_0x0382
            android.databinding.ObservableField<java.lang.String> r9 = r14.ch
            goto L_0x0384
        L_0x0382:
            r9 = r40
        L_0x0384:
            r10 = 9
            r1.updateRegistration((int) r10, (android.databinding.Observable) r9)
            if (r9 == 0) goto L_0x0396
            java.lang.Object r10 = r9.get()
            java.lang.String r10 = (java.lang.String) r10
            r40 = r9
            r55 = r10
            goto L_0x0398
        L_0x0396:
            r40 = r9
        L_0x0398:
            long r9 = r2 & r115
            r103 = 0
            int r9 = (r9 > r103 ? 1 : (r9 == r103 ? 0 : -1))
            if (r9 == 0) goto L_0x03e1
            if (r14 == 0) goto L_0x03a5
            android.databinding.ObservableField<java.lang.Boolean> r9 = r14.cd4
            goto L_0x03a7
        L_0x03a5:
            r9 = r42
        L_0x03a7:
            r10 = 10
            r1.updateRegistration((int) r10, (android.databinding.Observable) r9)
            if (r9 == 0) goto L_0x03b6
            java.lang.Object r10 = r9.get()
            r73 = r10
            java.lang.Boolean r73 = (java.lang.Boolean) r73
        L_0x03b6:
            boolean r10 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r73)
            long r129 = r2 & r115
            r103 = 0
            int r31 = (r129 > r103 ? 1 : (r129 == r103 ? 0 : -1))
            if (r31 == 0) goto L_0x03d3
            if (r10 == 0) goto L_0x03cc
            r129 = 549755813888(0x8000000000, double:2.716154612436E-312)
            long r2 = r2 | r129
            goto L_0x03d3
        L_0x03cc:
            r129 = 274877906944(0x4000000000, double:1.358077306218E-312)
            long r2 = r2 | r129
        L_0x03d3:
            if (r10 == 0) goto L_0x03d8
            r31 = 0
            goto L_0x03da
        L_0x03d8:
            r31 = 8
        L_0x03da:
            r42 = r9
            r33 = r10
            r10 = r31
            goto L_0x03e3
        L_0x03e1:
            r10 = r132
        L_0x03e3:
            r129 = 51539609600(0xc00000800, double:2.54639505034E-313)
            long r129 = r2 & r129
            r103 = 0
            int r9 = (r129 > r103 ? 1 : (r129 == r103 ? 0 : -1))
            if (r9 == 0) goto L_0x040b
            if (r14 == 0) goto L_0x03f5
            android.databinding.ObservableField<java.lang.String> r9 = r14.cdMode
            goto L_0x03f7
        L_0x03f5:
            r9 = r44
        L_0x03f7:
            r134 = r4
            r4 = 11
            r1.updateRegistration((int) r4, (android.databinding.Observable) r9)
            if (r9 == 0) goto L_0x0408
            java.lang.Object r4 = r9.get()
            java.lang.String r4 = (java.lang.String) r4
            r35 = r4
        L_0x0408:
            r44 = r9
            goto L_0x040d
        L_0x040b:
            r134 = r4
        L_0x040d:
            long r4 = r2 & r109
            r103 = 0
            int r4 = (r4 > r103 ? 1 : (r4 == r103 ? 0 : -1))
            if (r4 == 0) goto L_0x0444
            if (r14 == 0) goto L_0x041a
            android.databinding.ObservableBoolean r4 = r14.fm
            goto L_0x041c
        L_0x041a:
            r4 = r46
        L_0x041c:
            r5 = 12
            r1.updateRegistration((int) r5, (android.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0427
            boolean r39 = r4.get()
        L_0x0427:
            long r129 = r2 & r109
            r103 = 0
            int r5 = (r129 > r103 ? 1 : (r129 == r103 ? 0 : -1))
            if (r5 == 0) goto L_0x043a
            if (r39 == 0) goto L_0x0436
            r129 = 576460752303423488(0x800000000000000, double:3.785766995733679E-270)
            long r2 = r2 | r129
            goto L_0x043a
        L_0x0436:
            r129 = 288230376151711744(0x400000000000000, double:2.0522684006491881E-289)
            long r2 = r2 | r129
        L_0x043a:
            if (r39 == 0) goto L_0x043e
            r5 = 0
            goto L_0x0440
        L_0x043e:
            r5 = 8
        L_0x0440:
            r46 = r4
            r58 = r5
        L_0x0444:
            long r4 = r2 & r117
            r103 = 0
            int r4 = (r4 > r103 ? 1 : (r4 == r103 ? 0 : -1))
            if (r4 == 0) goto L_0x048a
            if (r14 == 0) goto L_0x0451
            android.databinding.ObservableField<java.lang.Boolean> r4 = r14.cd3
            goto L_0x0453
        L_0x0451:
            r4 = r53
        L_0x0453:
            r5 = 13
            r1.updateRegistration((int) r5, (android.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0462
            java.lang.Object r5 = r4.get()
            r76 = r5
            java.lang.Boolean r76 = (java.lang.Boolean) r76
        L_0x0462:
            boolean r5 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r76)
            long r129 = r2 & r117
            r103 = 0
            int r9 = (r129 > r103 ? 1 : (r129 == r103 ? 0 : -1))
            if (r9 == 0) goto L_0x047f
            if (r5 == 0) goto L_0x0478
            r129 = 137438953472(0x2000000000, double:6.7903865311E-313)
            long r2 = r2 | r129
            goto L_0x047f
        L_0x0478:
            r129 = 68719476736(0x1000000000, double:3.39519326554E-313)
            long r2 = r2 | r129
        L_0x047f:
            if (r5 == 0) goto L_0x0483
            r9 = 0
            goto L_0x0485
        L_0x0483:
            r9 = 8
        L_0x0485:
            r53 = r4
            r51 = r5
            goto L_0x048c
        L_0x048a:
            r9 = r131
        L_0x048c:
            r4 = 51539623936(0xc00004000, double:2.54639575864E-313)
            long r4 = r4 & r2
            r103 = 0
            int r4 = (r4 > r103 ? 1 : (r4 == r103 ? 0 : -1))
            if (r4 == 0) goto L_0x04b3
            if (r14 == 0) goto L_0x049d
            android.databinding.ObservableField<java.lang.String> r4 = r14.album
            goto L_0x049f
        L_0x049d:
            r4 = r54
        L_0x049f:
            r5 = 14
            r1.updateRegistration((int) r5, (android.databinding.Observable) r4)
            if (r4 == 0) goto L_0x04b1
            java.lang.Object r5 = r4.get()
            java.lang.String r5 = (java.lang.String) r5
            r54 = r4
            r97 = r5
            goto L_0x04b3
        L_0x04b1:
            r54 = r4
        L_0x04b3:
            r4 = 51539640320(0xc00008000, double:2.5463965681E-313)
            long r4 = r4 & r2
            r103 = 0
            int r4 = (r4 > r103 ? 1 : (r4 == r103 ? 0 : -1))
            if (r4 == 0) goto L_0x04da
            if (r14 == 0) goto L_0x04c4
            android.databinding.ObservableField<java.lang.String> r4 = r14.usbStatus
            goto L_0x04c6
        L_0x04c4:
            r4 = r57
        L_0x04c6:
            r5 = 15
            r1.updateRegistration((int) r5, (android.databinding.Observable) r4)
            if (r4 == 0) goto L_0x04d8
            java.lang.Object r5 = r4.get()
            java.lang.String r5 = (java.lang.String) r5
            r57 = r4
            r70 = r5
            goto L_0x04da
        L_0x04d8:
            r57 = r4
        L_0x04da:
            r4 = 51539673088(0xc00010000, double:2.54639818707E-313)
            long r4 = r4 & r2
            r103 = 0
            int r4 = (r4 > r103 ? 1 : (r4 == r103 ? 0 : -1))
            if (r4 == 0) goto L_0x0501
            if (r14 == 0) goto L_0x04eb
            android.databinding.ObservableField<java.lang.String> r4 = r14.disc
            goto L_0x04ed
        L_0x04eb:
            r4 = r60
        L_0x04ed:
            r5 = 16
            r1.updateRegistration((int) r5, (android.databinding.Observable) r4)
            if (r4 == 0) goto L_0x04ff
            java.lang.Object r5 = r4.get()
            java.lang.String r5 = (java.lang.String) r5
            r60 = r4
            r61 = r5
            goto L_0x0501
        L_0x04ff:
            r60 = r4
        L_0x0501:
            r4 = 51539738624(0xc00020000, double:2.546401425E-313)
            long r4 = r4 & r2
            r103 = 0
            int r4 = (r4 > r103 ? 1 : (r4 == r103 ? 0 : -1))
            if (r4 == 0) goto L_0x0528
            if (r14 == 0) goto L_0x0512
            android.databinding.ObservableField<java.lang.String> r4 = r14.time
            goto L_0x0514
        L_0x0512:
            r4 = r65
        L_0x0514:
            r5 = 17
            r1.updateRegistration((int) r5, (android.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0526
            java.lang.Object r5 = r4.get()
            java.lang.String r5 = (java.lang.String) r5
            r65 = r4
            r47 = r5
            goto L_0x0528
        L_0x0526:
            r65 = r4
        L_0x0528:
            long r4 = r2 & r101
            r103 = 0
            int r4 = (r4 > r103 ? 1 : (r4 == r103 ? 0 : -1))
            if (r4 == 0) goto L_0x056a
            if (r14 == 0) goto L_0x0535
            android.databinding.ObservableField<java.lang.Boolean> r4 = r14.rpt
            goto L_0x0537
        L_0x0535:
            r4 = r66
        L_0x0537:
            r5 = 18
            r1.updateRegistration((int) r5, (android.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0546
            java.lang.Object r5 = r4.get()
            r94 = r5
            java.lang.Boolean r94 = (java.lang.Boolean) r94
        L_0x0546:
            boolean r5 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r94)
            long r129 = r2 & r101
            r103 = 0
            int r25 = (r129 > r103 ? 1 : (r129 == r103 ? 0 : -1))
            if (r25 == 0) goto L_0x055d
            if (r5 == 0) goto L_0x0559
            r129 = 2251799813685248(0x8000000000000, double:1.1125369292536007E-308)
            long r2 = r2 | r129
            goto L_0x055d
        L_0x0559:
            r129 = 1125899906842624(0x4000000000000, double:5.562684646268003E-309)
            long r2 = r2 | r129
        L_0x055d:
            if (r5 == 0) goto L_0x0562
            r25 = 0
            goto L_0x0564
        L_0x0562:
            r25 = 8
        L_0x0564:
            r66 = r4
            r34 = r25
            r25 = r5
        L_0x056a:
            long r4 = r2 & r119
            r103 = 0
            int r4 = (r4 > r103 ? 1 : (r4 == r103 ? 0 : -1))
            if (r4 == 0) goto L_0x05b2
            if (r14 == 0) goto L_0x0577
            android.databinding.ObservableField<java.lang.Boolean> r4 = r14.cd2
            goto L_0x0579
        L_0x0577:
            r4 = r69
        L_0x0579:
            r5 = 19
            r1.updateRegistration((int) r5, (android.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0588
            java.lang.Object r5 = r4.get()
            r72 = r5
            java.lang.Boolean r72 = (java.lang.Boolean) r72
        L_0x0588:
            boolean r5 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r72)
            long r129 = r2 & r119
            r103 = 0
            int r31 = (r129 > r103 ? 1 : (r129 == r103 ? 0 : -1))
            if (r31 == 0) goto L_0x05a5
            if (r5 == 0) goto L_0x059e
            r129 = 140737488355328(0x800000000000, double:6.953355807835E-310)
            long r2 = r2 | r129
            goto L_0x05a5
        L_0x059e:
            r129 = 70368744177664(0x400000000000, double:3.4766779039175E-310)
            long r2 = r2 | r129
        L_0x05a5:
            if (r5 == 0) goto L_0x05aa
            r31 = 0
            goto L_0x05ac
        L_0x05aa:
            r31 = 8
        L_0x05ac:
            r24 = r31
            r69 = r4
            r45 = r5
        L_0x05b2:
            r4 = 51540656128(0xc00100000, double:2.5464467557E-313)
            long r4 = r4 & r2
            r103 = 0
            int r4 = (r4 > r103 ? 1 : (r4 == r103 ? 0 : -1))
            if (r4 == 0) goto L_0x05d9
            if (r14 == 0) goto L_0x05c3
            android.databinding.ObservableField<java.lang.String> r4 = r14.usbMusicTime
            goto L_0x05c5
        L_0x05c3:
            r4 = r71
        L_0x05c5:
            r5 = 20
            r1.updateRegistration((int) r5, (android.databinding.Observable) r4)
            if (r4 == 0) goto L_0x05d7
            java.lang.Object r5 = r4.get()
            java.lang.String r5 = (java.lang.String) r5
            r71 = r4
            r59 = r5
            goto L_0x05d9
        L_0x05d7:
            r71 = r4
        L_0x05d9:
            r4 = 51541704704(0xc00200000, double:2.54649856223E-313)
            long r4 = r4 & r2
            r103 = 0
            int r4 = (r4 > r103 ? 1 : (r4 == r103 ? 0 : -1))
            if (r4 == 0) goto L_0x0619
            if (r14 == 0) goto L_0x05ea
            android.databinding.ObservableBoolean r4 = r14.usb
            goto L_0x05ec
        L_0x05ea:
            r4 = r78
        L_0x05ec:
            r5 = 21
            r1.updateRegistration((int) r5, (android.databinding.Observable) r4)
            if (r4 == 0) goto L_0x05f7
            boolean r67 = r4.get()
        L_0x05f7:
            r129 = 51541704704(0xc00200000, double:2.54649856223E-313)
            long r129 = r2 & r129
            r103 = 0
            int r5 = (r129 > r103 ? 1 : (r129 == r103 ? 0 : -1))
            if (r5 == 0) goto L_0x060f
            if (r67 == 0) goto L_0x060b
            r129 = 144115188075855872(0x200000000000000, double:4.7783097267364807E-299)
            long r2 = r2 | r129
            goto L_0x060f
        L_0x060b:
            r129 = 72057594037927936(0x100000000000000, double:7.2911220195563975E-304)
            long r2 = r2 | r129
        L_0x060f:
            if (r67 == 0) goto L_0x0613
            r5 = 0
            goto L_0x0615
        L_0x0613:
            r5 = 8
        L_0x0615:
            r78 = r4
            r56 = r5
        L_0x0619:
            long r4 = r2 & r121
            r103 = 0
            int r4 = (r4 > r103 ? 1 : (r4 == r103 ? 0 : -1))
            if (r4 == 0) goto L_0x0661
            if (r14 == 0) goto L_0x0626
            android.databinding.ObservableField<java.lang.Boolean> r4 = r14.cd1
            goto L_0x0628
        L_0x0626:
            r4 = r79
        L_0x0628:
            r5 = 22
            r1.updateRegistration((int) r5, (android.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0637
            java.lang.Object r5 = r4.get()
            r88 = r5
            java.lang.Boolean r88 = (java.lang.Boolean) r88
        L_0x0637:
            boolean r5 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r88)
            long r129 = r2 & r121
            r103 = 0
            int r31 = (r129 > r103 ? 1 : (r129 == r103 ? 0 : -1))
            if (r31 == 0) goto L_0x0654
            if (r5 == 0) goto L_0x064d
            r129 = 35184372088832(0x200000000000, double:1.73833895195875E-310)
            long r2 = r2 | r129
            goto L_0x0654
        L_0x064d:
            r129 = 17592186044416(0x100000000000, double:8.6916947597938E-311)
            long r2 = r2 | r129
        L_0x0654:
            if (r5 == 0) goto L_0x0659
            r31 = 0
            goto L_0x065b
        L_0x0659:
            r31 = 8
        L_0x065b:
            r18 = r31
            r79 = r4
            r41 = r5
        L_0x0661:
            r4 = 51556384768(0xc01000000, double:2.54722385376E-313)
            long r4 = r4 & r2
            r103 = 0
            int r4 = (r4 > r103 ? 1 : (r4 == r103 ? 0 : -1))
            if (r4 == 0) goto L_0x06ac
            if (r14 == 0) goto L_0x0672
            android.databinding.ObservableField<java.lang.Boolean> r4 = r14.scan
            goto L_0x0674
        L_0x0672:
            r4 = r84
        L_0x0674:
            r5 = 24
            r1.updateRegistration((int) r5, (android.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0683
            java.lang.Object r5 = r4.get()
            r74 = r5
            java.lang.Boolean r74 = (java.lang.Boolean) r74
        L_0x0683:
            boolean r5 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r74)
            r129 = 51556384768(0xc01000000, double:2.54722385376E-313)
            long r129 = r2 & r129
            r103 = 0
            int r31 = (r129 > r103 ? 1 : (r129 == r103 ? 0 : -1))
            if (r31 == 0) goto L_0x069f
            if (r5 == 0) goto L_0x069b
            r129 = 9007199254740992(0x20000000000000, double:4.450147717014403E-308)
            long r2 = r2 | r129
            goto L_0x069f
        L_0x069b:
            r129 = 4503599627370496(0x10000000000000, double:2.2250738585072014E-308)
            long r2 = r2 | r129
        L_0x069f:
            if (r5 == 0) goto L_0x06a4
            r31 = 0
            goto L_0x06a6
        L_0x06a4:
            r31 = 8
        L_0x06a6:
            r84 = r4
            r43 = r5
            r38 = r31
        L_0x06ac:
            long r4 = r2 & r125
            r103 = 0
            int r4 = (r4 > r103 ? 1 : (r4 == r103 ? 0 : -1))
            if (r4 == 0) goto L_0x06ee
            if (r14 == 0) goto L_0x06b9
            android.databinding.ObservableField<java.lang.Boolean> r4 = r14.asl
            goto L_0x06bb
        L_0x06b9:
            r4 = r85
        L_0x06bb:
            r5 = 25
            r1.updateRegistration((int) r5, (android.databinding.Observable) r4)
            if (r4 == 0) goto L_0x06ca
            java.lang.Object r5 = r4.get()
            r75 = r5
            java.lang.Boolean r75 = (java.lang.Boolean) r75
        L_0x06ca:
            boolean r5 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r75)
            long r129 = r2 & r125
            r103 = 0
            int r31 = (r129 > r103 ? 1 : (r129 == r103 ? 0 : -1))
            if (r31 == 0) goto L_0x06e1
            if (r5 == 0) goto L_0x06dd
            r129 = -9223372036854775808
            long r2 = r2 | r129
            goto L_0x06e1
        L_0x06dd:
            r129 = 4611686018427387904(0x4000000000000000, double:2.0)
            long r2 = r2 | r129
        L_0x06e1:
            if (r5 == 0) goto L_0x06e6
            r31 = 0
            goto L_0x06e8
        L_0x06e6:
            r31 = 8
        L_0x06e8:
            r85 = r4
            r80 = r5
            r86 = r31
        L_0x06ee:
            long r4 = r2 & r111
            r103 = 0
            int r4 = (r4 > r103 ? 1 : (r4 == r103 ? 0 : -1))
            if (r4 == 0) goto L_0x0725
            if (r14 == 0) goto L_0x06fb
            android.databinding.ObservableBoolean r4 = r14.cd
            goto L_0x06fd
        L_0x06fb:
            r4 = r90
        L_0x06fd:
            r5 = 27
            r1.updateRegistration((int) r5, (android.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0708
            boolean r89 = r4.get()
        L_0x0708:
            long r129 = r2 & r111
            r103 = 0
            int r5 = (r129 > r103 ? 1 : (r129 == r103 ? 0 : -1))
            if (r5 == 0) goto L_0x071b
            if (r89 == 0) goto L_0x0717
            r129 = 2305843009213693952(0x2000000000000000, double:1.4916681462400413E-154)
            long r2 = r2 | r129
            goto L_0x071b
        L_0x0717:
            r129 = 1152921504606846976(0x1000000000000000, double:1.2882297539194267E-231)
            long r2 = r2 | r129
        L_0x071b:
            if (r89 == 0) goto L_0x071f
            r5 = 0
            goto L_0x0721
        L_0x071f:
            r5 = 8
        L_0x0721:
            r90 = r4
            r64 = r5
        L_0x0725:
            r4 = 51808043008(0xc10000000, double:2.55965742285E-313)
            long r4 = r4 & r2
            r103 = 0
            int r4 = (r4 > r103 ? 1 : (r4 == r103 ? 0 : -1))
            if (r4 == 0) goto L_0x0770
            if (r14 == 0) goto L_0x0736
            android.databinding.ObservableField<java.lang.Boolean> r4 = r14.st
            goto L_0x0738
        L_0x0736:
            r4 = r92
        L_0x0738:
            r5 = 28
            r1.updateRegistration((int) r5, (android.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0747
            java.lang.Object r5 = r4.get()
            r77 = r5
            java.lang.Boolean r77 = (java.lang.Boolean) r77
        L_0x0747:
            boolean r5 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r77)
            r49 = 51808043008(0xc10000000, double:2.55965742285E-313)
            long r49 = r2 & r49
            r103 = 0
            int r31 = (r49 > r103 ? 1 : (r49 == r103 ? 0 : -1))
            if (r31 == 0) goto L_0x0763
            if (r5 == 0) goto L_0x075f
            r49 = 562949953421312(0x2000000000000, double:2.781342323134002E-309)
            long r2 = r2 | r49
            goto L_0x0763
        L_0x075f:
            r49 = 281474976710656(0x1000000000000, double:1.390671161567E-309)
            long r2 = r2 | r49
        L_0x0763:
            if (r5 == 0) goto L_0x0768
            r31 = 0
            goto L_0x076a
        L_0x0768:
            r31 = 8
        L_0x076a:
            r29 = r31
            r92 = r4
            r50 = r5
        L_0x0770:
            long r4 = r2 & r107
            r103 = 0
            int r4 = (r4 > r103 ? 1 : (r4 == r103 ? 0 : -1))
            if (r4 == 0) goto L_0x07af
            if (r14 == 0) goto L_0x077d
            android.databinding.ObservableBoolean r4 = r14.audioOff
            goto L_0x077f
        L_0x077d:
            r4 = r98
        L_0x077f:
            r5 = 30
            r1.updateRegistration((int) r5, (android.databinding.Observable) r4)
            if (r4 == 0) goto L_0x078a
            boolean r22 = r4.get()
        L_0x078a:
            long r129 = r2 & r107
            r103 = 0
            int r5 = (r129 > r103 ? 1 : (r129 == r103 ? 0 : -1))
            if (r5 == 0) goto L_0x07a3
            if (r22 == 0) goto L_0x079c
            r129 = 2199023255552(0x20000000000, double:1.0864618449742E-311)
            long r2 = r2 | r129
            goto L_0x07a3
        L_0x079c:
            r129 = 1099511627776(0x10000000000, double:5.43230922487E-312)
            long r2 = r2 | r129
        L_0x07a3:
            if (r22 == 0) goto L_0x07a8
            r133 = 0
            goto L_0x07aa
        L_0x07a8:
            r133 = 8
        L_0x07aa:
            r5 = r133
            r98 = r4
            r15 = r5
        L_0x07af:
            r4 = 53687091200(0xc80000000, double:2.6524947387E-313)
            long r4 = r4 & r2
            r103 = 0
            int r4 = (r4 > r103 ? 1 : (r4 == r103 ? 0 : -1))
            if (r4 == 0) goto L_0x07d6
            if (r14 == 0) goto L_0x07c0
            android.databinding.ObservableField<java.lang.String> r4 = r14.track
            goto L_0x07c2
        L_0x07c0:
            r4 = r99
        L_0x07c2:
            r5 = 31
            r1.updateRegistration((int) r5, (android.databinding.Observable) r4)
            if (r4 == 0) goto L_0x07d1
            java.lang.Object r5 = r4.get()
            r95 = r5
            java.lang.String r95 = (java.lang.String) r95
        L_0x07d1:
            r139 = r0
            r99 = r4
            goto L_0x07d8
        L_0x07d6:
            r139 = r0
        L_0x07d8:
            r140 = r6
            r138 = r8
            r136 = r12
            r137 = r14
            r143 = r15
            r5 = r18
            r156 = r28
            r148 = r29
            r0 = r30
            r145 = r34
            r14 = r35
            r147 = r38
            r150 = r47
            r141 = r48
            r15 = r52
            r6 = r55
            r153 = r56
            r142 = r58
            r157 = r59
            r8 = r61
            r155 = r62
            r12 = r64
            r146 = r70
            r149 = r81
            r4 = r86
            r152 = r91
            r144 = r93
            r151 = r95
            r154 = r97
            r18 = r13
            r13 = r17
            r17 = r16
            r16 = r11
            r11 = r10
            r10 = r9
            r9 = r24
            goto L_0x086f
        L_0x081f:
            r131 = r9
            r132 = r10
            r139 = r0
            r134 = r4
            r140 = r6
            r138 = r8
            r136 = r12
            r137 = r14
            r143 = r15
            r5 = r18
            r9 = r24
            r156 = r28
            r148 = r29
            r0 = r30
            r145 = r34
            r14 = r35
            r147 = r38
            r150 = r47
            r141 = r48
            r15 = r52
            r6 = r55
            r153 = r56
            r142 = r58
            r157 = r59
            r8 = r61
            r155 = r62
            r12 = r64
            r146 = r70
            r149 = r81
            r4 = r86
            r152 = r91
            r144 = r93
            r151 = r95
            r154 = r97
            r10 = r131
            r18 = r13
            r13 = r17
            r17 = r16
            r16 = r11
            r11 = r132
        L_0x086f:
            long r28 = r2 & r125
            r30 = 0
            int r24 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r24 == 0) goto L_0x0884
            r158 = r6
            android.widget.TextView r6 = r1.asl
            r6.setVisibility(r4)
            android.widget.TextView r6 = r1.usbAsl
            r6.setVisibility(r4)
            goto L_0x0886
        L_0x0884:
            r158 = r6
        L_0x0886:
            r28 = 43016781824(0xa04000000, double:2.1253114094E-313)
            long r28 = r2 & r28
            r30 = 0
            int r6 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r6 == 0) goto L_0x0898
            android.widget.ImageView r6 = r1.blowMode
            r6.setImageLevel(r7)
        L_0x0898:
            long r28 = r2 & r121
            int r6 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r6 == 0) goto L_0x08a3
            android.widget.ImageView r6 = r1.cd1
            r6.setVisibility(r5)
        L_0x08a3:
            long r28 = r2 & r119
            int r6 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r6 == 0) goto L_0x08ae
            android.widget.ImageView r6 = r1.cd2
            r6.setVisibility(r9)
        L_0x08ae:
            long r28 = r2 & r117
            int r6 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r6 == 0) goto L_0x08b9
            android.widget.ImageView r6 = r1.cd3
            r6.setVisibility(r10)
        L_0x08b9:
            long r28 = r2 & r115
            int r6 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r6 == 0) goto L_0x08c4
            android.widget.ImageView r6 = r1.cd4
            r6.setVisibility(r11)
        L_0x08c4:
            long r28 = r2 & r113
            int r6 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r6 == 0) goto L_0x08cf
            android.widget.ImageView r6 = r1.cd5
            r6.setVisibility(r13)
        L_0x08cf:
            long r28 = r2 & r123
            int r6 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r6 == 0) goto L_0x08da
            android.widget.ImageView r6 = r1.cd6
            r6.setVisibility(r15)
        L_0x08da:
            long r28 = r2 & r111
            int r6 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r6 == 0) goto L_0x08e5
            android.widget.RelativeLayout r6 = r1.cdLayout
            r6.setVisibility(r12)
        L_0x08e5:
            r28 = 51539609600(0xc00000800, double:2.54639505034E-313)
            long r28 = r2 & r28
            int r6 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r6 == 0) goto L_0x08f5
            android.widget.TextView r6 = r1.discMode
            android.databinding.adapters.TextViewBindingAdapter.setText(r6, r14)
        L_0x08f5:
            r28 = 51539673088(0xc00010000, double:2.54639818707E-313)
            long r28 = r2 & r28
            int r6 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r6 == 0) goto L_0x0905
            android.widget.TextView r6 = r1.discNum
            android.databinding.adapters.TextViewBindingAdapter.setText(r6, r8)
        L_0x0905:
            r28 = 51539607680(0xc00000080, double:2.5463949555E-313)
            long r28 = r2 & r28
            int r6 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r6 == 0) goto L_0x0915
            android.widget.TextView r6 = r1.fmBand
            android.databinding.adapters.TextViewBindingAdapter.setText(r6, r0)
        L_0x0915:
            r28 = 51539608064(0xc00000200, double:2.54639497445E-313)
            long r28 = r2 & r28
            int r6 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r6 == 0) goto L_0x092a
            android.widget.TextView r6 = r1.fmCh
            r159 = r0
            r0 = r158
            android.databinding.adapters.TextViewBindingAdapter.setText(r6, r0)
            goto L_0x092e
        L_0x092a:
            r159 = r0
            r0 = r158
        L_0x092e:
            r28 = 51539607584(0xc00000020, double:2.54639495074E-313)
            long r28 = r2 & r28
            int r6 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r6 == 0) goto L_0x0943
            android.widget.TextView r6 = r1.fmFrequency
            r160 = r0
            r0 = r141
            android.databinding.adapters.TextViewBindingAdapter.setText(r6, r0)
            goto L_0x0947
        L_0x0943:
            r160 = r0
            r0 = r141
        L_0x0947:
            long r28 = r2 & r109
            int r6 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r6 == 0) goto L_0x0957
            android.widget.RelativeLayout r6 = r1.fmLayout
            r161 = r0
            r0 = r142
            r6.setVisibility(r0)
            goto L_0x095b
        L_0x0957:
            r161 = r0
            r0 = r142
        L_0x095b:
            long r28 = r2 & r107
            int r6 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r6 == 0) goto L_0x096b
            android.widget.TextView r6 = r1.mboundView1
            r162 = r0
            r0 = r143
            r6.setVisibility(r0)
            goto L_0x096f
        L_0x096b:
            r162 = r0
            r0 = r143
        L_0x096f:
            long r28 = r2 & r127
            int r6 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r6 == 0) goto L_0x0984
            android.widget.TextView r6 = r1.mboundView14
            r163 = r0
            r0 = r144
            r6.setVisibility(r0)
            android.widget.TextView r6 = r1.mboundView30
            r6.setVisibility(r0)
            goto L_0x0988
        L_0x0984:
            r163 = r0
            r0 = r144
        L_0x0988:
            long r28 = r2 & r101
            r30 = 0
            int r6 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r6 == 0) goto L_0x099f
            android.widget.TextView r6 = r1.mboundView15
            r164 = r0
            r0 = r145
            r6.setVisibility(r0)
            android.widget.TextView r6 = r1.mboundView31
            r6.setVisibility(r0)
            goto L_0x09a3
        L_0x099f:
            r164 = r0
            r0 = r145
        L_0x09a3:
            r28 = 51539640320(0xc00008000, double:2.5463965681E-313)
            long r28 = r2 & r28
            r30 = 0
            int r6 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r6 == 0) goto L_0x09bf
            android.widget.TextView r6 = r1.mboundView16
            r165 = r0
            r0 = r146
            android.databinding.adapters.TextViewBindingAdapter.setText(r6, r0)
            android.widget.TextView r6 = r1.mboundView32
            android.databinding.adapters.TextViewBindingAdapter.setText(r6, r0)
            goto L_0x09c3
        L_0x09bf:
            r165 = r0
            r0 = r146
        L_0x09c3:
            r28 = 51556384768(0xc01000000, double:2.54722385376E-313)
            long r28 = r2 & r28
            r30 = 0
            int r6 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r6 == 0) goto L_0x09df
            android.widget.TextView r6 = r1.mboundView17
            r166 = r0
            r0 = r147
            r6.setVisibility(r0)
            android.widget.TextView r6 = r1.mboundView33
            r6.setVisibility(r0)
            goto L_0x09e3
        L_0x09df:
            r166 = r0
            r0 = r147
        L_0x09e3:
            r28 = 51808043008(0xc10000000, double:2.55965742285E-313)
            long r28 = r2 & r28
            r30 = 0
            int r6 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r6 == 0) goto L_0x09ff
            android.widget.TextView r6 = r1.mboundView34
            r167 = r0
            r0 = r148
            r6.setVisibility(r0)
            android.widget.TextView r6 = r1.st
            r6.setVisibility(r0)
            goto L_0x0a03
        L_0x09ff:
            r167 = r0
            r0 = r148
        L_0x0a03:
            r28 = 51539607560(0xc00000008, double:2.54639494955E-313)
            long r28 = r2 & r28
            r30 = 0
            int r6 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r6 == 0) goto L_0x0a1a
            android.widget.TextView r6 = r1.modeFm
            r168 = r0
            r0 = r149
            android.databinding.adapters.TextViewBindingAdapter.setText(r6, r0)
            goto L_0x0a1e
        L_0x0a1a:
            r168 = r0
            r0 = r149
        L_0x0a1e:
            r28 = 51539738624(0xc00020000, double:2.546401425E-313)
            long r28 = r2 & r28
            int r6 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r6 == 0) goto L_0x0a33
            android.widget.TextView r6 = r1.time
            r169 = r0
            r0 = r150
            android.databinding.adapters.TextViewBindingAdapter.setText(r6, r0)
            goto L_0x0a37
        L_0x0a33:
            r169 = r0
            r0 = r150
        L_0x0a37:
            r28 = 53687091200(0xc80000000, double:2.6524947387E-313)
            long r28 = r2 & r28
            int r6 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r6 == 0) goto L_0x0a4c
            android.widget.TextView r6 = r1.trackNum
            r170 = r0
            r0 = r151
            android.databinding.adapters.TextViewBindingAdapter.setText(r6, r0)
            goto L_0x0a50
        L_0x0a4c:
            r170 = r0
            r0 = r151
        L_0x0a50:
            r28 = 47244640256(0xb00000000, double:2.33419537006E-313)
            long r28 = r2 & r28
            int r6 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r6 == 0) goto L_0x0a65
            android.widget.TextView r6 = r1.tvLeftTemperature
            r171 = r0
            r0 = r140
            android.databinding.adapters.TextViewBindingAdapter.setText(r6, r0)
            goto L_0x0a69
        L_0x0a65:
            r171 = r0
            r0 = r140
        L_0x0a69:
            r28 = 42958061568(0xa00800000, double:2.12241024327E-313)
            long r28 = r2 & r28
            int r6 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r6 == 0) goto L_0x0a7e
            android.widget.TextView r6 = r1.tvRightTemperature
            r172 = r0
            r0 = r139
            android.databinding.adapters.TextViewBindingAdapter.setText(r6, r0)
            goto L_0x0a82
        L_0x0a7e:
            r172 = r0
            r0 = r139
        L_0x0a82:
            r28 = 51539607554(0xc00000002, double:2.54639494926E-313)
            long r28 = r2 & r28
            int r6 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r6 == 0) goto L_0x0a97
            android.widget.TextView r6 = r1.usbIndex
            r173 = r0
            r0 = r152
            android.databinding.adapters.TextViewBindingAdapter.setText(r6, r0)
            goto L_0x0a9b
        L_0x0a97:
            r173 = r0
            r0 = r152
        L_0x0a9b:
            r28 = 51541704704(0xc00200000, double:2.54649856223E-313)
            long r28 = r2 & r28
            int r6 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r6 == 0) goto L_0x0ab0
            android.widget.RelativeLayout r6 = r1.usbLayout
            r174 = r0
            r0 = r153
            r6.setVisibility(r0)
            goto L_0x0ab4
        L_0x0ab0:
            r174 = r0
            r0 = r153
        L_0x0ab4:
            r28 = 51539623936(0xc00004000, double:2.54639575864E-313)
            long r28 = r2 & r28
            int r6 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r6 == 0) goto L_0x0ac9
            android.widget.TextView r6 = r1.usbMusicAlbum
            r175 = r0
            r0 = r154
            android.databinding.adapters.TextViewBindingAdapter.setText(r6, r0)
            goto L_0x0acd
        L_0x0ac9:
            r175 = r0
            r0 = r154
        L_0x0acd:
            r28 = 51539607808(0xc00000100, double:2.5463949618E-313)
            long r28 = r2 & r28
            int r6 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r6 == 0) goto L_0x0ae2
            android.widget.TextView r6 = r1.usbMusicArtist
            r176 = r0
            r0 = r155
            android.databinding.adapters.TextViewBindingAdapter.setText(r6, r0)
            goto L_0x0ae6
        L_0x0ae2:
            r176 = r0
            r0 = r155
        L_0x0ae6:
            r28 = 51539607568(0xc00000010, double:2.54639494995E-313)
            long r28 = r2 & r28
            int r6 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r6 == 0) goto L_0x0afb
            android.widget.TextView r6 = r1.usbMusicName
            r177 = r0
            r0 = r156
            android.databinding.adapters.TextViewBindingAdapter.setText(r6, r0)
            goto L_0x0aff
        L_0x0afb:
            r177 = r0
            r0 = r156
        L_0x0aff:
            r28 = 51540656128(0xc00100000, double:2.5464467557E-313)
            long r28 = r2 & r28
            int r6 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r6 == 0) goto L_0x0b14
            android.widget.TextView r6 = r1.usbMusicTime
            r178 = r0
            r0 = r157
            android.databinding.adapters.TextViewBindingAdapter.setText(r6, r0)
            goto L_0x0b18
        L_0x0b14:
            r178 = r0
            r0 = r157
        L_0x0b18:
            r28 = 43486543872(0xa20000000, double:2.14852073835E-313)
            long r28 = r2 & r28
            int r6 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r6 == 0) goto L_0x0b2d
            android.widget.ImageView r6 = r1.windSpeedLevel
            r179 = r0
            r0 = r138
            r6.setImageLevel(r0)
            goto L_0x0b31
        L_0x0b2d:
            r179 = r0
            r0 = r138
        L_0x0b31:
            return
        L_0x0b32:
            r0 = move-exception
            monitor-exit(r180)     // Catch:{ all -> 0x0b32 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.databinding.ActivityLexusOemFmBindingImpl.executeBindings():void");
    }
}
