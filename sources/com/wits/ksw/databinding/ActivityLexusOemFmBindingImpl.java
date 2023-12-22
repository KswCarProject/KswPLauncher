package com.wits.ksw.databinding;

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
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.view.lexus.LexusOEMFMViewModel;
import com.wits.ksw.launcher.view.lexus.LexusUiParams;
import com.wits.ksw.launcher.view.lexus.VolumeSeekBar;

/* loaded from: classes7.dex */
public class ActivityLexusOemFmBindingImpl extends ActivityLexusOemFmBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback236;
    private long mDirtyFlags;
    private long mDirtyFlags_1;
    private final RelativeLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView18;
    private final TextView mboundView19;
    private final TextView mboundView20;
    private final TextView mboundView21;
    private final TextView mboundView32;
    private final TextView mboundView33;
    private final TextView mboundView34;
    private final TextView mboundView35;
    private final TextView mboundView36;
    private final TextView mboundView43;
    private final TextView mboundView44;
    private final TextView mboundView45;
    private final TextView mboundView46;
    private final TextView mboundView47;
    private final RelativeLayout mboundView53;
    private final TextView mboundView54;
    private final TextView mboundView55;
    private final ImageView mboundView56;
    private final TextView mboundView57;
    private final ImageView mboundView60;
    private final TextView mboundView61;
    private final TextView mboundView8;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.mode_bt, 62);
        sparseIntArray.put(C0899R.C0901id.volume_bar, 63);
    }

    public ActivityLexusOemFmBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 64, sIncludes, sViewsWithIds));
    }

    private ActivityLexusOemFmBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 46, (RelativeLayout) bindings[2], (ImageView) bindings[6], (ImageView) bindings[7], (ImageView) bindings[10], (ImageView) bindings[11], (ImageView) bindings[12], (ImageView) bindings[13], (ImageView) bindings[14], (ImageView) bindings[15], (RelativeLayout) bindings[17], (TextView) bindings[9], (TextView) bindings[22], (TextView) bindings[16], (TextView) bindings[27], (TextView) bindings[28], (TextView) bindings[30], (RelativeLayout) bindings[25], (RelativeLayout) bindings[59], (ImageView) bindings[58], (ImageView) bindings[62], (TextView) bindings[29], (TextView) bindings[52], (RelativeLayout) bindings[42], (TextView) bindings[50], (TextView) bindings[49], (TextView) bindings[48], (TextView) bindings[51], (TextView) bindings[26], (TextView) bindings[24], (TextView) bindings[23], (TextView) bindings[3], (TextView) bindings[4], (TextView) bindings[41], (RelativeLayout) bindings[31], (TextView) bindings[39], (TextView) bindings[38], (TextView) bindings[37], (TextView) bindings[40], (VolumeSeekBar) bindings[63], (ImageView) bindings[5]);
        this.mDirtyFlags = -1L;
        this.mDirtyFlags_1 = -1L;
        this.acLayout.setTag(null);
        this.blowMode.setTag(null);
        this.btSignal.setTag(null);
        this.cd1.setTag(null);
        this.cd2.setTag(null);
        this.cd3.setTag(null);
        this.cd4.setTag(null);
        this.cd5.setTag(null);
        this.cd6.setTag(null);
        this.cdLayout.setTag(null);
        this.discMode.setTag(null);
        this.discNum.setTag(null);
        this.eqMode.setTag(null);
        this.fmBand.setTag(null);
        this.fmCh.setTag(null);
        this.fmFrequency.setTag(null);
        this.fmLayout.setTag(null);
        this.layoutVolume.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
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
        TextView textView6 = (TextView) bindings[32];
        this.mboundView32 = textView6;
        textView6.setTag(null);
        TextView textView7 = (TextView) bindings[33];
        this.mboundView33 = textView7;
        textView7.setTag(null);
        TextView textView8 = (TextView) bindings[34];
        this.mboundView34 = textView8;
        textView8.setTag(null);
        TextView textView9 = (TextView) bindings[35];
        this.mboundView35 = textView9;
        textView9.setTag(null);
        TextView textView10 = (TextView) bindings[36];
        this.mboundView36 = textView10;
        textView10.setTag(null);
        TextView textView11 = (TextView) bindings[43];
        this.mboundView43 = textView11;
        textView11.setTag(null);
        TextView textView12 = (TextView) bindings[44];
        this.mboundView44 = textView12;
        textView12.setTag(null);
        TextView textView13 = (TextView) bindings[45];
        this.mboundView45 = textView13;
        textView13.setTag(null);
        TextView textView14 = (TextView) bindings[46];
        this.mboundView46 = textView14;
        textView14.setTag(null);
        TextView textView15 = (TextView) bindings[47];
        this.mboundView47 = textView15;
        textView15.setTag(null);
        RelativeLayout relativeLayout2 = (RelativeLayout) bindings[53];
        this.mboundView53 = relativeLayout2;
        relativeLayout2.setTag(null);
        TextView textView16 = (TextView) bindings[54];
        this.mboundView54 = textView16;
        textView16.setTag(null);
        TextView textView17 = (TextView) bindings[55];
        this.mboundView55 = textView17;
        textView17.setTag(null);
        ImageView imageView = (ImageView) bindings[56];
        this.mboundView56 = imageView;
        imageView.setTag(null);
        TextView textView18 = (TextView) bindings[57];
        this.mboundView57 = textView18;
        textView18.setTag(null);
        ImageView imageView2 = (ImageView) bindings[60];
        this.mboundView60 = imageView2;
        imageView2.setTag(null);
        TextView textView19 = (TextView) bindings[61];
        this.mboundView61 = textView19;
        textView19.setTag(null);
        TextView textView20 = (TextView) bindings[8];
        this.mboundView8 = textView20;
        textView20.setTag(null);
        this.modeAux.setTag(null);
        this.modeFm.setTag(null);
        this.mp3Index.setTag(null);
        this.mp3Layout.setTag(null);
        this.mp3MusicAlbum.setTag(null);
        this.mp3MusicArtist.setTag(null);
        this.mp3MusicName.setTag(null);
        this.mp3MusicTime.setTag(null);
        this.f177st.setTag(null);
        this.time.setTag(null);
        this.trackNum.setTag(null);
        this.tvLeftTemperature.setTag(null);
        this.tvRightTemperature.setTag(null);
        this.usbIndex.setTag(null);
        this.usbLayout.setTag(null);
        this.usbMusicAlbum.setTag(null);
        this.usbMusicArtist.setTag(null);
        this.usbMusicName.setTag(null);
        this.usbMusicTime.setTag(null);
        this.windSpeedLevel.setTag(null);
        setRootTag(root);
        this.mCallback236 = new OnClickListener(this, 1);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 281474976710656L;
            this.mDirtyFlags_1 = 0L;
        }
        requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags == 0 && this.mDirtyFlags_1 == 0) {
                return false;
            }
            return true;
        }
    }

    @Override // android.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (22 == variableId) {
            setMUiParams((LexusUiParams) variable);
            return true;
        } else if (26 == variableId) {
            setVm((LexusOEMFMViewModel) variable);
            return true;
        } else {
            return false;
        }
    }

    @Override // com.wits.ksw.databinding.ActivityLexusOemFmBinding
    public void setMUiParams(LexusUiParams MUiParams) {
        this.mMUiParams = MUiParams;
        synchronized (this) {
            this.mDirtyFlags |= 70368744177664L;
        }
        notifyPropertyChanged(22);
        super.requestRebind();
    }

    @Override // com.wits.ksw.databinding.ActivityLexusOemFmBinding
    public void setVm(LexusOEMFMViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 140737488355328L;
        }
        notifyPropertyChanged(26);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVmRand((ObservableField) object, fieldId);
            case 1:
                return onChangeVmBtSetUp((ObservableField) object, fieldId);
            case 2:
                return onChangeVmCd3((ObservableField) object, fieldId);
            case 3:
                return onChangeVmEq((ObservableBoolean) object, fieldId);
            case 4:
                return onChangeVmAlbum((ObservableField) object, fieldId);
            case 5:
                return onChangeVmIndex((ObservableField) object, fieldId);
            case 6:
                return onChangeVmBtSignal((ObservableInt) object, fieldId);
            case 7:
                return onChangeVmUsbStatus((ObservableField) object, fieldId);
            case 8:
                return onChangeVmDisc((ObservableField) object, fieldId);
            case 9:
                return onChangeVmTime((ObservableField) object, fieldId);
            case 10:
                return onChangeVmRpt((ObservableField) object, fieldId);
            case 11:
                return onChangeVmCd6((ObservableField) object, fieldId);
            case 12:
                return onChangeVmMode((ObservableField) object, fieldId);
            case 13:
                return onChangeVmCd2((ObservableField) object, fieldId);
            case 14:
                return onChangeVmUsbMusicTime((ObservableField) object, fieldId);
            case 15:
                return onChangeVmMusicName((ObservableField) object, fieldId);
            case 16:
                return onChangeVmEqMode((ObservableField) object, fieldId);
            case 17:
                return onChangeVmMp3((ObservableBoolean) object, fieldId);
            case 18:
                return onChangeVmUsb((ObservableBoolean) object, fieldId);
            case 19:
                return onChangeVmFmFrequency((ObservableField) object, fieldId);
            case 20:
                return onChangeVmBtTime((ObservableField) object, fieldId);
            case 21:
                return onChangeVmCd5((ObservableField) object, fieldId);
            case 22:
                return onChangeVmCd1((ObservableField) object, fieldId);
            case 23:
                return onChangeMUiParamsRightTempStr((ObservableField) object, fieldId);
            case 24:
                return onChangeVmScan((ObservableField) object, fieldId);
            case 25:
                return onChangeVmFmBand((ObservableField) object, fieldId);
            case 26:
                return onChangeVmAsl((ObservableField) object, fieldId);
            case 27:
                return onChangeVmArtist((ObservableField) object, fieldId);
            case 28:
                return onChangeVmBtConnect((ObservableField) object, fieldId);
            case 29:
                return onChangeVmMute((ObservableBoolean) object, fieldId);
            case 30:
                return onChangeMUiParamsBlowingMode((ObservableInt) object, fieldId);
            case 31:
                return onChangeVmAux((ObservableBoolean) object, fieldId);
            case 32:
                return onChangeVmCd((ObservableBoolean) object, fieldId);
            case 33:
                return onChangeVmSt((ObservableField) object, fieldId);
            case 34:
                return onChangeVmCh((ObservableField) object, fieldId);
            case 35:
                return onChangeVmCd4((ObservableField) object, fieldId);
            case 36:
                return onChangeVmShowVolume((ObservableBoolean) object, fieldId);
            case 37:
                return onChangeVmFm((ObservableBoolean) object, fieldId);
            case 38:
                return onChangeVmBtPlay((ObservableField) object, fieldId);
            case 39:
                return onChangeVmBt((ObservableBoolean) object, fieldId);
            case 40:
                return onChangeMUiParamsWindSpeed((ObservableInt) object, fieldId);
            case 41:
                return onChangeVmMediaVolume((ObservableField) object, fieldId);
            case 42:
                return onChangeVmAudioOff((ObservableBoolean) object, fieldId);
            case 43:
                return onChangeVmTrack((ObservableField) object, fieldId);
            case 44:
                return onChangeMUiParamsLeftTempStr((ObservableField) object, fieldId);
            case 45:
                return onChangeVmBtConnectInfo((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmRand(ObservableField<Boolean> VmRand, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmBtSetUp(ObservableField<String> VmBtSetUp, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmCd3(ObservableField<Boolean> VmCd3, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmEq(ObservableBoolean VmEq, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmAlbum(ObservableField<String> VmAlbum, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmIndex(ObservableField<String> VmIndex, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmBtSignal(ObservableInt VmBtSignal, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmUsbStatus(ObservableField<String> VmUsbStatus, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmDisc(ObservableField<String> VmDisc, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmTime(ObservableField<String> VmTime, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmRpt(ObservableField<Boolean> VmRpt, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmCd6(ObservableField<Boolean> VmCd6, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmMode(ObservableField<String> VmMode, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmCd2(ObservableField<Boolean> VmCd2, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmUsbMusicTime(ObservableField<String> VmUsbMusicTime, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmMusicName(ObservableField<String> VmMusicName, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmEqMode(ObservableField<String> VmEqMode, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmMp3(ObservableBoolean VmMp3, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmUsb(ObservableBoolean VmUsb, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmFmFrequency(ObservableField<String> VmFmFrequency, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmBtTime(ObservableField<String> VmBtTime, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmCd5(ObservableField<Boolean> VmCd5, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmCd1(ObservableField<Boolean> VmCd1, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4194304;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeMUiParamsRightTempStr(ObservableField<String> MUiParamsRightTempStr, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8388608;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmScan(ObservableField<Boolean> VmScan, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16777216;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmFmBand(ObservableField<String> VmFmBand, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 33554432;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmAsl(ObservableField<Boolean> VmAsl, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 67108864;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmArtist(ObservableField<String> VmArtist, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 134217728;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmBtConnect(ObservableField<Boolean> VmBtConnect, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 268435456;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmMute(ObservableBoolean VmMute, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 536870912;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeMUiParamsBlowingMode(ObservableInt MUiParamsBlowingMode, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1073741824;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmAux(ObservableBoolean VmAux, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2147483648L;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmCd(ObservableBoolean VmCd, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4294967296L;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmSt(ObservableField<Boolean> VmSt, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8589934592L;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmCh(ObservableField<String> VmCh, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 17179869184L;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmCd4(ObservableField<Boolean> VmCd4, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 34359738368L;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmShowVolume(ObservableBoolean VmShowVolume, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 68719476736L;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmFm(ObservableBoolean VmFm, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 137438953472L;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmBtPlay(ObservableField<Boolean> VmBtPlay, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 274877906944L;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmBt(ObservableBoolean VmBt, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 549755813888L;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeMUiParamsWindSpeed(ObservableInt MUiParamsWindSpeed, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1099511627776L;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmMediaVolume(ObservableField<String> VmMediaVolume, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2199023255552L;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmAudioOff(ObservableBoolean VmAudioOff, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4398046511104L;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmTrack(ObservableField<String> VmTrack, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8796093022208L;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeMUiParamsLeftTempStr(ObservableField<String> MUiParamsLeftTempStr, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 17592186044416L;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmBtConnectInfo(ObservableField<String> VmBtConnectInfo, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 35184372088832L;
            }
            return true;
        }
        return false;
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: SSATransform
        java.lang.IndexOutOfBoundsException: bitIndex < 0: -111
        	at java.base/java.util.BitSet.get(Unknown Source)
        	at jadx.core.dex.visitors.ssa.LiveVarAnalysis.fillBasicBlockInfo(LiveVarAnalysis.java:65)
        	at jadx.core.dex.visitors.ssa.LiveVarAnalysis.runAnalysis(LiveVarAnalysis.java:36)
        	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:55)
        	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:41)
        */
    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 4701
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.databinding.ActivityLexusOemFmBindingImpl.executeBindings():void");
    }

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        LexusOEMFMViewModel vm = this.mVm;
        boolean vmJavaLangObjectNull = vm != null;
        if (vmJavaLangObjectNull) {
            vm.openAir(callbackArg_0);
        }
    }
}
