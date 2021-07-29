package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.view.lexus.LexusOEMFMViewModel;
import com.wits.ksw.launcher.view.lexus.LexusUiParams;

public class ActivityLexusOemFmBindingImpl extends ActivityLexusOemFmBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback219;
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
        sparseIntArray.put(R.id.mode_bt, 62);
        sparseIntArray.put(R.id.volume_bar, 63);
    }

    public ActivityLexusOemFmBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 64, sIncludes, sViewsWithIds));
    }

    private ActivityLexusOemFmBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 46, bindings[2], bindings[6], bindings[7], bindings[10], bindings[11], bindings[12], bindings[13], bindings[14], bindings[15], bindings[17], bindings[9], bindings[22], bindings[16], bindings[27], bindings[28], bindings[30], bindings[25], bindings[59], bindings[58], bindings[62], bindings[29], bindings[52], bindings[42], bindings[50], bindings[49], bindings[48], bindings[51], bindings[26], bindings[24], bindings[23], bindings[3], bindings[4], bindings[41], bindings[31], bindings[39], bindings[38], bindings[37], bindings[40], bindings[63], bindings[5]);
        this.mDirtyFlags = -1;
        this.mDirtyFlags_1 = -1;
        this.acLayout.setTag((Object) null);
        this.blowMode.setTag((Object) null);
        this.btSignal.setTag((Object) null);
        this.cd1.setTag((Object) null);
        this.cd2.setTag((Object) null);
        this.cd3.setTag((Object) null);
        this.cd4.setTag((Object) null);
        this.cd5.setTag((Object) null);
        this.cd6.setTag((Object) null);
        this.cdLayout.setTag((Object) null);
        this.discMode.setTag((Object) null);
        this.discNum.setTag((Object) null);
        this.eqMode.setTag((Object) null);
        this.fmBand.setTag((Object) null);
        this.fmCh.setTag((Object) null);
        this.fmFrequency.setTag((Object) null);
        this.fmLayout.setTag((Object) null);
        this.layoutVolume.setTag((Object) null);
        RelativeLayout relativeLayout = bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
        TextView textView = bindings[1];
        this.mboundView1 = textView;
        textView.setTag((Object) null);
        TextView textView2 = bindings[18];
        this.mboundView18 = textView2;
        textView2.setTag((Object) null);
        TextView textView3 = bindings[19];
        this.mboundView19 = textView3;
        textView3.setTag((Object) null);
        TextView textView4 = bindings[20];
        this.mboundView20 = textView4;
        textView4.setTag((Object) null);
        TextView textView5 = bindings[21];
        this.mboundView21 = textView5;
        textView5.setTag((Object) null);
        TextView textView6 = bindings[32];
        this.mboundView32 = textView6;
        textView6.setTag((Object) null);
        TextView textView7 = bindings[33];
        this.mboundView33 = textView7;
        textView7.setTag((Object) null);
        TextView textView8 = bindings[34];
        this.mboundView34 = textView8;
        textView8.setTag((Object) null);
        TextView textView9 = bindings[35];
        this.mboundView35 = textView9;
        textView9.setTag((Object) null);
        TextView textView10 = bindings[36];
        this.mboundView36 = textView10;
        textView10.setTag((Object) null);
        TextView textView11 = bindings[43];
        this.mboundView43 = textView11;
        textView11.setTag((Object) null);
        TextView textView12 = bindings[44];
        this.mboundView44 = textView12;
        textView12.setTag((Object) null);
        TextView textView13 = bindings[45];
        this.mboundView45 = textView13;
        textView13.setTag((Object) null);
        TextView textView14 = bindings[46];
        this.mboundView46 = textView14;
        textView14.setTag((Object) null);
        TextView textView15 = bindings[47];
        this.mboundView47 = textView15;
        textView15.setTag((Object) null);
        RelativeLayout relativeLayout2 = bindings[53];
        this.mboundView53 = relativeLayout2;
        relativeLayout2.setTag((Object) null);
        TextView textView16 = bindings[54];
        this.mboundView54 = textView16;
        textView16.setTag((Object) null);
        TextView textView17 = bindings[55];
        this.mboundView55 = textView17;
        textView17.setTag((Object) null);
        ImageView imageView = bindings[56];
        this.mboundView56 = imageView;
        imageView.setTag((Object) null);
        TextView textView18 = bindings[57];
        this.mboundView57 = textView18;
        textView18.setTag((Object) null);
        ImageView imageView2 = bindings[60];
        this.mboundView60 = imageView2;
        imageView2.setTag((Object) null);
        TextView textView19 = bindings[61];
        this.mboundView61 = textView19;
        textView19.setTag((Object) null);
        TextView textView20 = bindings[8];
        this.mboundView8 = textView20;
        textView20.setTag((Object) null);
        this.modeAux.setTag((Object) null);
        this.modeFm.setTag((Object) null);
        this.mp3Index.setTag((Object) null);
        this.mp3Layout.setTag((Object) null);
        this.mp3MusicAlbum.setTag((Object) null);
        this.mp3MusicArtist.setTag((Object) null);
        this.mp3MusicName.setTag((Object) null);
        this.mp3MusicTime.setTag((Object) null);
        this.st.setTag((Object) null);
        this.time.setTag((Object) null);
        this.trackNum.setTag((Object) null);
        this.tvLeftTemperature.setTag((Object) null);
        this.tvRightTemperature.setTag((Object) null);
        this.usbIndex.setTag((Object) null);
        this.usbLayout.setTag((Object) null);
        this.usbMusicAlbum.setTag((Object) null);
        this.usbMusicArtist.setTag((Object) null);
        this.usbMusicName.setTag((Object) null);
        this.usbMusicTime.setTag((Object) null);
        this.windSpeedLevel.setTag((Object) null);
        setRootTag(root);
        this.mCallback219 = new OnClickListener(this, 1);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 281474976710656L;
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

    public boolean setVariable(int variableId, Object variable) {
        if (13 == variableId) {
            setMUiParams((LexusUiParams) variable);
            return true;
        } else if (17 != variableId) {
            return false;
        } else {
            setVm((LexusOEMFMViewModel) variable);
            return true;
        }
    }

    public void setMUiParams(LexusUiParams MUiParams) {
        this.mMUiParams = MUiParams;
        synchronized (this) {
            this.mDirtyFlags |= 70368744177664L;
        }
        notifyPropertyChanged(13);
        super.requestRebind();
    }

    public void setVm(LexusOEMFMViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 140737488355328L;
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

    private boolean onChangeVmRand(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeVmBtSetUp(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeVmCd3(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeVmEq(ObservableBoolean VmEq, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeVmAlbum(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeVmIndex(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeVmBtSignal(ObservableInt VmBtSignal, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeVmUsbStatus(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeVmDisc(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeVmTime(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeVmRpt(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeVmCd6(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeVmMode(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        return true;
    }

    private boolean onChangeVmCd2(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
        }
        return true;
    }

    private boolean onChangeVmUsbMusicTime(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
        }
        return true;
    }

    private boolean onChangeVmMusicName(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeVmEqMode(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeVmMp3(ObservableBoolean VmMp3, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
        }
        return true;
    }

    private boolean onChangeVmUsb(ObservableBoolean VmUsb, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
        }
        return true;
    }

    private boolean onChangeVmFmFrequency(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
        }
        return true;
    }

    private boolean onChangeVmBtTime(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        }
        return true;
    }

    private boolean onChangeVmCd5(ObservableField<Boolean> observableField, int fieldId) {
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

    private boolean onChangeVmFmBand(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 33554432;
        }
        return true;
    }

    private boolean onChangeVmAsl(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 67108864;
        }
        return true;
    }

    private boolean onChangeVmArtist(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 134217728;
        }
        return true;
    }

    private boolean onChangeVmBtConnect(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 268435456;
        }
        return true;
    }

    private boolean onChangeVmMute(ObservableBoolean VmMute, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 536870912;
        }
        return true;
    }

    private boolean onChangeMUiParamsBlowingMode(ObservableInt MUiParamsBlowingMode, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1073741824;
        }
        return true;
    }

    private boolean onChangeVmAux(ObservableBoolean VmAux, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2147483648L;
        }
        return true;
    }

    private boolean onChangeVmCd(ObservableBoolean VmCd, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4294967296L;
        }
        return true;
    }

    private boolean onChangeVmSt(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8589934592L;
        }
        return true;
    }

    private boolean onChangeVmCh(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 17179869184L;
        }
        return true;
    }

    private boolean onChangeVmCd4(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 34359738368L;
        }
        return true;
    }

    private boolean onChangeVmShowVolume(ObservableBoolean VmShowVolume, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 68719476736L;
        }
        return true;
    }

    private boolean onChangeVmFm(ObservableBoolean VmFm, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 137438953472L;
        }
        return true;
    }

    private boolean onChangeVmBtPlay(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 274877906944L;
        }
        return true;
    }

    private boolean onChangeVmBt(ObservableBoolean VmBt, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 549755813888L;
        }
        return true;
    }

    private boolean onChangeMUiParamsWindSpeed(ObservableInt MUiParamsWindSpeed, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1099511627776L;
        }
        return true;
    }

    private boolean onChangeVmMediaVolume(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2199023255552L;
        }
        return true;
    }

    private boolean onChangeVmAudioOff(ObservableBoolean VmAudioOff, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4398046511104L;
        }
        return true;
    }

    private boolean onChangeVmTrack(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8796093022208L;
        }
        return true;
    }

    private boolean onChangeMUiParamsLeftTempStr(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 17592186044416L;
        }
        return true;
    }

    private boolean onChangeVmBtConnectInfo(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 35184372088832L;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        long dirtyFlags_1;
        ObservableField<String> vmBtSetUp;
        String vmChGet;
        ObservableField<Boolean> vmCd3;
        int mUiParamsBlowingModeGet;
        String mUiParamsRightTempStrGet;
        String vmChGet2;
        int mUiParamsWindSpeedGet;
        String vmAlbumGet;
        String vmFmFrequencyGet;
        String vmTimeGet;
        String vmTrackGet;
        String vmBtSetUpGet;
        int vmShowVolumeViewVISIBLEViewGONE;
        int vmRandViewVISIBLEViewGONE;
        String vmIndexGet;
        int vmBtPlayViewVISIBLEViewVISIBLE;
        int vmScanViewVISIBLEViewGONE;
        int vmBtViewVISIBLEViewGONE;
        int vmRptViewVISIBLEViewGONE;
        int vmAslViewVISIBLEViewGONE;
        int vmAuxViewVISIBLEViewGONE;
        int vmStViewVISIBLEViewGONE;
        String vmMusicNameGet;
        String vmModeGet;
        int vmMp3ViewVISIBLEViewGONE;
        String vmBtConnectInfoGet;
        Drawable vmMuteMboundView60AndroidDrawableLexusCdProgressBarMuteMboundView60AndroidDrawableLexusCdProgressBarVol;
        Boolean vmCd6Get;
        int i;
        String vmBtConnectInfoGet2;
        int vmFmViewVISIBLEViewGONE;
        int vmBtSignalGet;
        String vmBtTimeGet;
        String vmBtTimeGet2;
        String vmArtistGet;
        String vmUsbMusicTimeGet;
        String vmArtistGet2;
        String vmDiscGet;
        int vmUsbViewVISIBLEViewGONE;
        int vmFmViewVISIBLEViewGONE2;
        int vmUsbViewVISIBLEViewGONE2;
        int vmCd3ViewVISIBLEViewGONE;
        int vmAudioOffViewGONEViewVISIBLE;
        int vmCd4ViewVISIBLEViewGONE;
        String vmDiscGet2;
        String vmFmBandGet;
        String vmEqModeGet;
        int vmEqViewVISIBLEViewGONE;
        int vmBtSignalGet2;
        int vmBtConnectViewVISIBLEViewGONE;
        int mUiParamsWindSpeedGet2;
        int vmEqViewVISIBLEViewGONE2;
        String vmFmBandGet2;
        int vmAudioOffViewGONEVmCd5ViewVISIBLEViewGONE;
        int vmAudioOffViewGONEVmCd4ViewVISIBLEViewGONE;
        int vmAudioOffViewGONEVmCd6ViewVISIBLEViewGONE;
        int vmAudioOffViewGONEVmCd1ViewVISIBLEViewGONE;
        int vmAudioOffViewGONEVmCd2ViewVISIBLEViewGONE;
        String vmEqModeGet2;
        int vmAudioOffViewGONEVmCd3ViewVISIBLEViewGONE;
        int vmAudioOffViewVISIBLEViewGONE;
        String vmDiscGet3;
        String vmDiscGet4;
        String vmDiscGet5;
        int vmEqViewVISIBLEViewGONE3;
        String vmFmBandGet3;
        String vmFmBandGet4;
        String vmChGet3;
        int vmFmViewVISIBLEViewGONE3;
        int vmShowVolumeViewVISIBLEViewGONE2;
        int vmRandViewVISIBLEViewGONE2;
        int vmRandViewVISIBLEViewGONE3;
        String vmUsbStatusGet;
        int vmScanViewVISIBLEViewGONE2;
        int vmStViewVISIBLEViewGONE2;
        int vmStViewVISIBLEViewGONE3;
        String vmBtConnectInfoGet3;
        String vmBtConnectInfoGet4;
        int vmBtPlayViewVISIBLEViewVISIBLE2;
        String vmBtTimeGet3;
        Drawable vmMuteMboundView60AndroidDrawableLexusCdProgressBarMuteMboundView60AndroidDrawableLexusCdProgressBarVol2;
        String vmMediaVolumeGet;
        int vmAslViewVISIBLEViewGONE2;
        int vmAuxViewVISIBLEViewGONE2;
        String vmModeGet2;
        String vmIndexGet2;
        int vmMp3ViewVISIBLEViewGONE2;
        String vmAlbumGet2;
        String vmAlbumGet3;
        String vmMusicNameGet2;
        String vmUsbMusicTimeGet2;
        String vmTimeGet2;
        String vmTimeGet3;
        String mUiParamsLeftTempStrGet;
        String mUiParamsRightTempStrGet2;
        int vmUsbViewVISIBLEViewGONE3;
        ObservableField<Boolean> vmCd4;
        ObservableField<Boolean> vmCd1;
        ObservableField<Boolean> vmCd5;
        ObservableField<Boolean> vmCd2;
        ObservableField<Boolean> vmCd6;
        ObservableField<Boolean> vmCd32;
        ObservableField<String> vmBtSetUp2;
        ObservableBoolean vmEq;
        ObservableField<String> vmBtConnectInfo;
        ObservableField<String> vmTrack;
        ObservableBoolean vmAudioOff;
        ObservableField<String> vmMediaVolume;
        ObservableBoolean vmBt;
        ObservableField<Boolean> vmBtPlay;
        ObservableBoolean vmFm;
        ObservableBoolean vmShowVolume;
        ObservableField<String> vmCh;
        ObservableField<Boolean> vmSt;
        ObservableBoolean vmCd;
        ObservableBoolean vmAux;
        ObservableBoolean vmMute;
        long dirtyFlags_12;
        Drawable drawable;
        ObservableField<Boolean> vmBtConnect;
        ObservableField<String> vmArtist;
        ObservableField<Boolean> vmAsl;
        ObservableField<String> vmFmBand;
        ObservableField<Boolean> vmScan;
        ObservableField<String> vmBtTime;
        ObservableField<String> vmFmFrequency;
        ObservableBoolean vmUsb;
        ObservableBoolean vmMp3;
        ObservableField<String> vmEqMode;
        ObservableField<String> vmMusicName;
        ObservableField<String> vmUsbMusicTime;
        ObservableField<String> vmMode;
        ObservableField<Boolean> vmRpt;
        ObservableField<String> vmTime;
        ObservableField<String> vmDisc;
        ObservableField<String> vmUsbStatus;
        ObservableInt vmBtSignal;
        ObservableBoolean vmEq2;
        ObservableField<Boolean> vmRand;
        ObservableField<String> mUiParamsLeftTempStr;
        ObservableInt mUiParamsWindSpeed;
        ObservableInt mUiParamsBlowingMode;
        ObservableField<String> mUiParamsRightTempStr;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
            dirtyFlags_1 = this.mDirtyFlags_1;
            this.mDirtyFlags_1 = 0;
        }
        ObservableField<String> vmAlbum = null;
        ObservableField<String> vmIndex = null;
        String vmEqModeGet3 = null;
        LexusUiParams mUiParams = this.mMUiParams;
        String vmMediaVolumeGet2 = null;
        LexusOEMFMViewModel vm = this.mVm;
        int vmUsbViewVISIBLEViewGONE4 = 0;
        int vmAudioOffViewVISIBLEViewGONE2 = 0;
        int vmFmViewVISIBLEViewGONE4 = 0;
        String vmUsbMusicTimeGet3 = null;
        Boolean vmRandGet = null;
        int vmCd5ViewVISIBLEViewGONE = 0;
        String vmDiscGet6 = null;
        boolean vmMuteGet = false;
        boolean vmShowVolumeGet = false;
        String vmArtistGet3 = null;
        int vmBtConnectViewVISIBLEViewGONE2 = 0;
        int vmCdViewVISIBLEViewGONE = 0;
        boolean vmBtGet = false;
        int vmCd1ViewVISIBLEViewGONE = 0;
        Boolean vmBtConnectGet = null;
        String vmBtTimeGet4 = null;
        boolean vmAuxGet = false;
        boolean vmUsbGet = false;
        int vmBtPlayViewVISIBLEViewGONE = 0;
        int vmBtSignalGet3 = 0;
        Boolean vmCd5Get = null;
        String vmUsbStatusGet2 = null;
        Drawable vmMuteMboundView60AndroidDrawableLexusCdProgressBarMuteMboundView60AndroidDrawableLexusCdProgressBarVol3 = null;
        Boolean vmCd2Get = null;
        int vmMp3ViewVISIBLEViewGONE3 = 0;
        boolean vmAudioOffGet = false;
        Boolean vmBtPlayGet = null;
        int vmCd2ViewVISIBLEViewGONE = 0;
        Boolean vmCd4Get = null;
        Boolean vmScanGet = null;
        Boolean vmAslGet = null;
        Boolean vmCd3Get = null;
        Boolean vmStGet = null;
        boolean vmMp3Get = false;
        String vmModeGet3 = null;
        String vmMusicNameGet3 = null;
        int vmStViewVISIBLEViewGONE4 = 0;
        String vmFmBandGet5 = null;
        String mUiParamsRightTempStrGet3 = null;
        int vmAuxViewVISIBLEViewGONE3 = 0;
        int vmAslViewVISIBLEViewGONE3 = 0;
        int vmRptViewVISIBLEViewGONE2 = 0;
        int vmBtViewVISIBLEViewGONE2 = 0;
        int mUiParamsWindSpeedGet3 = 0;
        int vmScanViewVISIBLEViewGONE3 = 0;
        Boolean vmCd1Get = null;
        boolean vmCdGet = false;
        boolean vmFmGet = false;
        int vmBtPlayViewVISIBLEViewVISIBLE3 = 0;
        String vmIndexGet3 = null;
        int vmRandViewVISIBLEViewGONE4 = 0;
        int vmShowVolumeViewVISIBLEViewGONE3 = 0;
        int vmAudioOffViewGONEViewVISIBLE2 = 0;
        String vmBtSetUpGet2 = null;
        Boolean vmRptGet = null;
        String vmTrackGet2 = null;
        String vmTimeGet4 = null;
        String vmFmFrequencyGet2 = null;
        int mUiParamsBlowingModeGet2 = 0;
        String vmAlbumGet4 = null;
        boolean vmEqGet = false;
        int vmCd6ViewVISIBLEViewGONE = 0;
        int vmEqViewVISIBLEViewGONE4 = 0;
        if ((dirtyFlags & 370536500690944L) != 0) {
            if ((dirtyFlags & 351843729276928L) != 0) {
                if (mUiParams != null) {
                    vmChGet = null;
                    mUiParamsRightTempStr = mUiParams.rightTempStr;
                } else {
                    vmChGet = null;
                    mUiParamsRightTempStr = null;
                }
                vmBtSetUp = null;
                updateRegistration(23, (Observable) mUiParamsRightTempStr);
                if (mUiParamsRightTempStr != null) {
                    mUiParamsRightTempStrGet3 = mUiParamsRightTempStr.get();
                    ObservableField<String> observableField = mUiParamsRightTempStr;
                } else {
                    ObservableField<String> observableField2 = mUiParamsRightTempStr;
                }
            } else {
                vmChGet = null;
                vmBtSetUp = null;
            }
            if ((351844794630144L & dirtyFlags) != 0) {
                if (mUiParams != null) {
                    mUiParamsBlowingMode = mUiParams.blowingMode;
                } else {
                    mUiParamsBlowingMode = null;
                }
                updateRegistration(30, (Observable) mUiParamsBlowingMode);
                if (mUiParamsBlowingMode != null) {
                    mUiParamsBlowingModeGet2 = mUiParamsBlowingMode.get();
                    ObservableInt observableInt = mUiParamsBlowingMode;
                } else {
                    ObservableInt observableInt2 = mUiParamsBlowingMode;
                }
            }
            if ((352943232516096L & dirtyFlags) != 0) {
                if (mUiParams != null) {
                    mUiParamsWindSpeed = mUiParams.windSpeed;
                } else {
                    mUiParamsWindSpeed = null;
                }
                updateRegistration(40, (Observable) mUiParamsWindSpeed);
                if (mUiParamsWindSpeed != null) {
                    mUiParamsWindSpeedGet3 = mUiParamsWindSpeed.get();
                    ObservableInt observableInt3 = mUiParamsWindSpeed;
                } else {
                    ObservableInt observableInt4 = mUiParamsWindSpeed;
                }
            }
            if ((369435906932736L & dirtyFlags) != 0) {
                if (mUiParams != null) {
                    mUiParamsLeftTempStr = mUiParams.leftTempStr;
                } else {
                    mUiParamsLeftTempStr = null;
                }
                updateRegistration(44, (Observable) mUiParamsLeftTempStr);
                if (mUiParamsLeftTempStr != null) {
                    ObservableField<String> observableField3 = mUiParamsLeftTempStr;
                    vmChGet2 = mUiParamsLeftTempStr.get();
                    mUiParamsRightTempStrGet = mUiParamsRightTempStrGet3;
                    LexusUiParams lexusUiParams = mUiParams;
                    vmCd3 = null;
                    mUiParamsWindSpeedGet = mUiParamsWindSpeedGet3;
                    mUiParamsBlowingModeGet = mUiParamsBlowingModeGet2;
                } else {
                    ObservableField<String> observableField4 = mUiParamsLeftTempStr;
                    vmChGet2 = null;
                    mUiParamsRightTempStrGet = mUiParamsRightTempStrGet3;
                    LexusUiParams lexusUiParams2 = mUiParams;
                    vmCd3 = null;
                    mUiParamsWindSpeedGet = mUiParamsWindSpeedGet3;
                    mUiParamsBlowingModeGet = mUiParamsBlowingModeGet2;
                }
            } else {
                vmChGet2 = null;
                mUiParamsRightTempStrGet = mUiParamsRightTempStrGet3;
                LexusUiParams lexusUiParams3 = mUiParams;
                vmCd3 = null;
                mUiParamsWindSpeedGet = mUiParamsWindSpeedGet3;
                mUiParamsBlowingModeGet = mUiParamsBlowingModeGet2;
            }
        } else {
            vmChGet = null;
            vmBtSetUp = null;
            vmChGet2 = null;
            mUiParamsRightTempStrGet = null;
            LexusUiParams lexusUiParams4 = mUiParams;
            vmCd3 = null;
            mUiParamsWindSpeedGet = 0;
            mUiParamsBlowingModeGet = 0;
        }
        if ((dirtyFlags & 473888429441023L) != 0) {
            if ((dirtyFlags & 422212465065985L) != 0) {
                if (vm != null) {
                    vmRand = vm.rand;
                } else {
                    vmRand = null;
                }
                vmCd6Get = null;
                updateRegistration(0, (Observable) vmRand);
                if (vmRand != null) {
                    vmRandGet = vmRand.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxVmRandGet = ViewDataBinding.safeUnbox(vmRandGet);
                if ((dirtyFlags & 422212465065985L) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxVmRandGet) {
                        dirtyFlags_1 |= 17179869184L;
                    } else {
                        dirtyFlags_1 |= 8589934592L;
                    }
                }
                ObservableField<Boolean> observableField5 = vmRand;
                vmRandViewVISIBLEViewGONE4 = androidDatabindingViewDataBindingSafeUnboxVmRandGet ? 0 : 8;
            } else {
                vmCd6Get = null;
            }
            if ((422212465065986L & dirtyFlags) != 0) {
                if (vm != null) {
                    vmBtSetUp2 = vm.btSetUp;
                } else {
                    vmBtSetUp2 = vmBtSetUp;
                }
                updateRegistration(1, (Observable) vmBtSetUp2);
                if (vmBtSetUp2 != null) {
                    vmBtSetUpGet2 = vmBtSetUp2.get();
                }
            } else {
                vmBtSetUp2 = vmBtSetUp;
            }
            if ((dirtyFlags & 422212465065992L) != 0) {
                if (vm != null) {
                    vmEq2 = vm.eq;
                } else {
                    vmEq2 = null;
                }
                ObservableField<String> observableField6 = vmBtSetUp2;
                updateRegistration(3, (Observable) vmEq2);
                if (vmEq2 != null) {
                    vmEqGet = vmEq2.get();
                }
                if ((dirtyFlags & 422212465065992L) != 0) {
                    if (vmEqGet) {
                        dirtyFlags_1 |= 70368744177664L;
                    } else {
                        dirtyFlags_1 |= 35184372088832L;
                    }
                }
                vmEqViewVISIBLEViewGONE4 = vmEqGet ? 0 : 8;
                vmEq = vmEq2;
            } else {
                vmEq = null;
            }
            if ((dirtyFlags & 422212465066000L) != 0) {
                if (vm != null) {
                    vmAlbum = vm.album;
                }
                updateRegistration(4, (Observable) vmAlbum);
                if (vmAlbum != null) {
                    vmAlbumGet4 = vmAlbum.get();
                }
            }
            if ((dirtyFlags & 422212465066016L) != 0) {
                if (vm != null) {
                    vmIndex = vm.index;
                }
                updateRegistration(5, (Observable) vmIndex);
                if (vmIndex != null) {
                    vmIndexGet3 = vmIndex.get();
                }
            }
            if ((dirtyFlags & 422212465066048L) != 0) {
                if (vm != null) {
                    vmBtSignal = vm.btSignal;
                } else {
                    vmBtSignal = null;
                }
                ObservableBoolean observableBoolean = vmEq;
                updateRegistration(6, (Observable) vmBtSignal);
                if (vmBtSignal != null) {
                    vmBtSignalGet3 = vmBtSignal.get();
                    ObservableInt observableInt5 = vmBtSignal;
                } else {
                    ObservableInt observableInt6 = vmBtSignal;
                }
            }
            if ((422212465066112L & dirtyFlags) != 0) {
                if (vm != null) {
                    vmUsbStatus = vm.usbStatus;
                } else {
                    vmUsbStatus = null;
                }
                updateRegistration(7, (Observable) vmUsbStatus);
                if (vmUsbStatus != null) {
                    ObservableField<String> observableField7 = vmUsbStatus;
                    vmUsbStatusGet2 = vmUsbStatus.get();
                } else {
                    ObservableField<String> observableField8 = vmUsbStatus;
                }
            }
            if ((422212465066240L & dirtyFlags) != 0) {
                if (vm != null) {
                    vmDisc = vm.disc;
                } else {
                    vmDisc = null;
                }
                updateRegistration(8, (Observable) vmDisc);
                if (vmDisc != null) {
                    vmDiscGet6 = vmDisc.get();
                    ObservableField<String> observableField9 = vmDisc;
                } else {
                    ObservableField<String> observableField10 = vmDisc;
                }
            }
            if ((dirtyFlags & 422212465066496L) != 0) {
                if (vm != null) {
                    vmTime = vm.time;
                } else {
                    vmTime = null;
                }
                updateRegistration(9, (Observable) vmTime);
                if (vmTime != null) {
                    vmTimeGet4 = vmTime.get();
                    ObservableField<String> observableField11 = vmTime;
                } else {
                    ObservableField<String> observableField12 = vmTime;
                }
            }
            if ((dirtyFlags & 422212465067008L) != 0) {
                if (vm != null) {
                    vmRpt = vm.rpt;
                } else {
                    vmRpt = null;
                }
                updateRegistration(10, (Observable) vmRpt);
                if (vmRpt != null) {
                    vmRptGet = vmRpt.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxVmRptGet = ViewDataBinding.safeUnbox(vmRptGet);
                if ((dirtyFlags & 422212465067008L) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxVmRptGet) {
                        dirtyFlags_1 |= 16777216;
                    } else {
                        dirtyFlags_1 |= 8388608;
                    }
                }
                ObservableField<Boolean> observableField13 = vmRpt;
                vmRptViewVISIBLEViewGONE2 = androidDatabindingViewDataBindingSafeUnboxVmRptGet ? 0 : 8;
            }
            if ((422212465070080L & dirtyFlags) != 0) {
                if (vm != null) {
                    vmMode = vm.mode;
                } else {
                    vmMode = null;
                }
                updateRegistration(12, (Observable) vmMode);
                if (vmMode != null) {
                    ObservableField<String> observableField14 = vmMode;
                    vmModeGet3 = vmMode.get();
                } else {
                    ObservableField<String> observableField15 = vmMode;
                }
            }
            if ((422212465082368L & dirtyFlags) != 0) {
                if (vm != null) {
                    vmUsbMusicTime = vm.usbMusicTime;
                } else {
                    vmUsbMusicTime = null;
                }
                updateRegistration(14, (Observable) vmUsbMusicTime);
                if (vmUsbMusicTime != null) {
                    vmUsbMusicTimeGet3 = vmUsbMusicTime.get();
                    ObservableField<String> observableField16 = vmUsbMusicTime;
                } else {
                    ObservableField<String> observableField17 = vmUsbMusicTime;
                }
            }
            if ((422212465098752L & dirtyFlags) != 0) {
                if (vm != null) {
                    vmMusicName = vm.musicName;
                } else {
                    vmMusicName = null;
                }
                updateRegistration(15, (Observable) vmMusicName);
                if (vmMusicName != null) {
                    vmMusicNameGet3 = vmMusicName.get();
                    ObservableField<String> observableField18 = vmMusicName;
                } else {
                    ObservableField<String> observableField19 = vmMusicName;
                }
            }
            if ((422212465131520L & dirtyFlags) != 0) {
                if (vm != null) {
                    vmEqMode = vm.eqMode;
                } else {
                    vmEqMode = null;
                }
                updateRegistration(16, (Observable) vmEqMode);
                if (vmEqMode != null) {
                    ObservableField<String> observableField20 = vmEqMode;
                    vmEqModeGet3 = vmEqMode.get();
                } else {
                    ObservableField<String> observableField21 = vmEqMode;
                }
            }
            if ((dirtyFlags & 422212465197056L) != 0) {
                if (vm != null) {
                    vmMp3 = vm.mp3;
                } else {
                    vmMp3 = null;
                }
                updateRegistration(17, (Observable) vmMp3);
                if (vmMp3 != null) {
                    vmMp3Get = vmMp3.get();
                }
                if ((dirtyFlags & 422212465197056L) != 0) {
                    if (vmMp3Get) {
                        dirtyFlags_1 |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                    } else {
                        dirtyFlags_1 |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                    }
                }
                vmMp3ViewVISIBLEViewGONE3 = vmMp3Get ? 0 : 8;
                ObservableBoolean observableBoolean2 = vmMp3;
            }
            if ((dirtyFlags & 422212465328128L) != 0) {
                if (vm != null) {
                    vmUsb = vm.usb;
                } else {
                    vmUsb = null;
                }
                updateRegistration(18, (Observable) vmUsb);
                if (vmUsb != null) {
                    vmUsbGet = vmUsb.get();
                }
                if ((dirtyFlags & 422212465328128L) != 0) {
                    if (vmUsbGet) {
                        dirtyFlags |= 18014398509481984L;
                    } else {
                        dirtyFlags |= 9007199254740992L;
                    }
                }
                vmUsbViewVISIBLEViewGONE4 = vmUsbGet ? 0 : 8;
                ObservableBoolean observableBoolean3 = vmUsb;
            }
            if ((422212465590272L & dirtyFlags) != 0) {
                if (vm != null) {
                    vmFmFrequency = vm.fmFrequency;
                } else {
                    vmFmFrequency = null;
                }
                updateRegistration(19, (Observable) vmFmFrequency);
                if (vmFmFrequency != null) {
                    ObservableField<String> observableField22 = vmFmFrequency;
                    vmFmFrequencyGet2 = vmFmFrequency.get();
                } else {
                    ObservableField<String> observableField23 = vmFmFrequency;
                }
            }
            if ((422212466114560L & dirtyFlags) != 0) {
                if (vm != null) {
                    vmBtTime = vm.btTime;
                } else {
                    vmBtTime = null;
                }
                updateRegistration(20, (Observable) vmBtTime);
                if (vmBtTime != null) {
                    ObservableField<String> observableField24 = vmBtTime;
                    vmBtTimeGet4 = vmBtTime.get();
                } else {
                    ObservableField<String> observableField25 = vmBtTime;
                }
            }
            if ((dirtyFlags & 422212481843200L) != 0) {
                if (vm != null) {
                    vmScan = vm.scan;
                } else {
                    vmScan = null;
                }
                updateRegistration(24, (Observable) vmScan);
                if (vmScan != null) {
                    vmScanGet = vmScan.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxVmScanGet = ViewDataBinding.safeUnbox(vmScanGet);
                if ((dirtyFlags & 422212481843200L) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxVmScanGet) {
                        dirtyFlags_1 |= 268435456;
                    } else {
                        dirtyFlags_1 |= 134217728;
                    }
                }
                ObservableField<Boolean> observableField26 = vmScan;
                vmScanViewVISIBLEViewGONE3 = androidDatabindingViewDataBindingSafeUnboxVmScanGet ? 0 : 8;
            }
            if ((422212498620416L & dirtyFlags) != 0) {
                if (vm != null) {
                    vmFmBand = vm.fmBand;
                } else {
                    vmFmBand = null;
                }
                updateRegistration(25, (Observable) vmFmBand);
                if (vmFmBand != null) {
                    ObservableField<String> observableField27 = vmFmBand;
                    vmFmBandGet5 = vmFmBand.get();
                } else {
                    ObservableField<String> observableField28 = vmFmBand;
                }
            }
            if ((422212532174848L & dirtyFlags) != 0) {
                if (vm != null) {
                    vmAsl = vm.asl;
                } else {
                    vmAsl = null;
                }
                updateRegistration(26, (Observable) vmAsl);
                if (vmAsl != null) {
                    vmAslGet = vmAsl.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxVmAslGet = ViewDataBinding.safeUnbox(vmAslGet);
                if ((dirtyFlags & 422212532174848L) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxVmAslGet) {
                        dirtyFlags_1 |= 4194304;
                    } else {
                        dirtyFlags_1 |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
                    }
                }
                ObservableField<Boolean> observableField29 = vmAsl;
                vmAslViewVISIBLEViewGONE3 = androidDatabindingViewDataBindingSafeUnboxVmAslGet ? 0 : 8;
            }
            if ((422212599283712L & dirtyFlags) != 0) {
                if (vm != null) {
                    vmArtist = vm.artist;
                } else {
                    vmArtist = null;
                }
                updateRegistration(27, (Observable) vmArtist);
                if (vmArtist != null) {
                    vmArtistGet3 = vmArtist.get();
                    ObservableField<String> observableField30 = vmArtist;
                } else {
                    ObservableField<String> observableField31 = vmArtist;
                }
            }
            if ((dirtyFlags & 422212733501440L) != 0) {
                if (vm != null) {
                    vmBtConnect = vm.btConnect;
                } else {
                    vmBtConnect = null;
                }
                updateRegistration(28, (Observable) vmBtConnect);
                if (vmBtConnect != null) {
                    vmBtConnectGet = vmBtConnect.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxVmBtConnectGet = ViewDataBinding.safeUnbox(vmBtConnectGet);
                if ((dirtyFlags & 422212733501440L) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxVmBtConnectGet) {
                        dirtyFlags_1 |= 1;
                    } else {
                        dirtyFlags |= Long.MIN_VALUE;
                    }
                }
                ObservableField<Boolean> observableField32 = vmBtConnect;
                vmBtConnectViewVISIBLEViewGONE2 = androidDatabindingViewDataBindingSafeUnboxVmBtConnectGet ? 0 : 8;
            }
            if ((422213001936896L & dirtyFlags) != 0) {
                if (vm != null) {
                    vmMute = vm.mute;
                } else {
                    vmMute = null;
                }
                updateRegistration(29, (Observable) vmMute);
                if (vmMute != null) {
                    vmMuteGet = vmMute.get();
                }
                if ((dirtyFlags & 422213001936896L) != 0) {
                    if (vmMuteGet) {
                        dirtyFlags_1 |= 256;
                    } else {
                        dirtyFlags_1 |= 128;
                    }
                }
                if (vmMuteGet) {
                    dirtyFlags_12 = dirtyFlags_1;
                    drawable = AppCompatResources.getDrawable(this.mboundView60.getContext(), R.drawable.lexus_cd_progress_bar_mute);
                } else {
                    dirtyFlags_12 = dirtyFlags_1;
                    drawable = AppCompatResources.getDrawable(this.mboundView60.getContext(), R.drawable.lexus_cd_progress_bar_vol);
                }
                vmMuteMboundView60AndroidDrawableLexusCdProgressBarMuteMboundView60AndroidDrawableLexusCdProgressBarVol3 = drawable;
                ObservableBoolean observableBoolean4 = vmMute;
                dirtyFlags_1 = dirtyFlags_12;
            }
            if ((422214612549632L & dirtyFlags) != 0) {
                if (vm != null) {
                    vmAux = vm.aux;
                } else {
                    vmAux = null;
                }
                updateRegistration(31, (Observable) vmAux);
                if (vmAux != null) {
                    vmAuxGet = vmAux.get();
                }
                if ((dirtyFlags & 422214612549632L) != 0) {
                    if (vmAuxGet) {
                        dirtyFlags_1 |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                    } else {
                        dirtyFlags_1 |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
                    }
                }
                ObservableBoolean observableBoolean5 = vmAux;
                vmAuxViewVISIBLEViewGONE3 = vmAuxGet ? 0 : 8;
            }
            if ((422216760033280L & dirtyFlags) != 0) {
                if (vm != null) {
                    vmCd = vm.cd;
                } else {
                    vmCd = null;
                }
                updateRegistration(32, (Observable) vmCd);
                if (vmCd != null) {
                    vmCdGet = vmCd.get();
                }
                if ((dirtyFlags & 422216760033280L) != 0) {
                    if (vmCdGet) {
                        dirtyFlags_1 |= 4;
                    } else {
                        dirtyFlags_1 |= 2;
                    }
                }
                ObservableBoolean observableBoolean6 = vmCd;
                vmCdViewVISIBLEViewGONE = vmCdGet ? 0 : 8;
            }
            if ((422221055000576L & dirtyFlags) != 0) {
                if (vm != null) {
                    vmSt = vm.st;
                } else {
                    vmSt = null;
                }
                updateRegistration(33, (Observable) vmSt);
                if (vmSt != null) {
                    vmStGet = vmSt.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxVmStGet = ViewDataBinding.safeUnbox(vmStGet);
                if ((dirtyFlags & 422221055000576L) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxVmStGet) {
                        dirtyFlags_1 |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
                    } else {
                        dirtyFlags_1 |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                    }
                }
                ObservableField<Boolean> observableField33 = vmSt;
                vmStViewVISIBLEViewGONE4 = androidDatabindingViewDataBindingSafeUnboxVmStGet ? 0 : 8;
            }
            if ((422229644935168L & dirtyFlags) != 0) {
                if (vm != null) {
                    vmCh = vm.ch;
                } else {
                    vmCh = null;
                }
                updateRegistration(34, (Observable) vmCh);
                if (vmCh != null) {
                    ObservableField<String> observableField34 = vmCh;
                    vmChGet = vmCh.get();
                } else {
                    ObservableField<String> observableField35 = vmCh;
                }
            }
            if ((422281184542720L & dirtyFlags) != 0) {
                if (vm != null) {
                    vmShowVolume = vm.showVolume;
                } else {
                    vmShowVolume = null;
                }
                updateRegistration(36, (Observable) vmShowVolume);
                if (vmShowVolume != null) {
                    vmShowVolumeGet = vmShowVolume.get();
                }
                if ((dirtyFlags & 422281184542720L) != 0) {
                    if (vmShowVolumeGet) {
                        dirtyFlags_1 |= 68719476736L;
                    } else {
                        dirtyFlags_1 |= 34359738368L;
                    }
                }
                ObservableBoolean observableBoolean7 = vmShowVolume;
                vmShowVolumeViewVISIBLEViewGONE3 = vmShowVolumeGet ? 0 : 8;
            }
            if ((422349904019456L & dirtyFlags) != 0) {
                if (vm != null) {
                    vmFm = vm.fm;
                } else {
                    vmFm = null;
                }
                updateRegistration(37, (Observable) vmFm);
                if (vmFm != null) {
                    vmFmGet = vmFm.get();
                }
                if ((dirtyFlags & 422349904019456L) != 0) {
                    if (vmFmGet) {
                        dirtyFlags |= 1152921504606846976L;
                    } else {
                        dirtyFlags |= 576460752303423488L;
                    }
                }
                ObservableBoolean observableBoolean8 = vmFm;
                vmFmViewVISIBLEViewGONE4 = vmFmGet ? 0 : 8;
            }
            if ((422487342972928L & dirtyFlags) != 0) {
                if (vm != null) {
                    vmBtPlay = vm.btPlay;
                } else {
                    vmBtPlay = null;
                }
                updateRegistration(38, (Observable) vmBtPlay);
                if (vmBtPlay != null) {
                    vmBtPlayGet = vmBtPlay.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxVmBtPlayGet = ViewDataBinding.safeUnbox(vmBtPlayGet);
                if ((dirtyFlags & 422487342972928L) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxVmBtPlayGet) {
                        dirtyFlags_1 = dirtyFlags_1 | 64 | 1073741824;
                    } else {
                        dirtyFlags_1 = dirtyFlags_1 | 32 | 536870912;
                    }
                }
                i = 0;
                ObservableField<Boolean> observableField36 = vmBtPlay;
                vmBtPlayViewVISIBLEViewVISIBLE3 = 0;
                vmBtPlayViewVISIBLEViewGONE = androidDatabindingViewDataBindingSafeUnboxVmBtPlayGet ? 0 : 8;
            } else {
                i = 0;
            }
            if ((422762220879872L & dirtyFlags) != 0) {
                if (vm != null) {
                    vmBt = vm.bt;
                } else {
                    vmBt = null;
                }
                updateRegistration(39, (Observable) vmBt);
                if (vmBt != null) {
                    vmBtGet = vmBt.get();
                }
                if ((dirtyFlags & 422762220879872L) != 0) {
                    if (vmBtGet) {
                        dirtyFlags_1 |= 67108864;
                    } else {
                        dirtyFlags_1 |= 33554432;
                    }
                }
                ObservableBoolean observableBoolean9 = vmBt;
                vmBtViewVISIBLEViewGONE2 = vmBtGet ? i : 8;
            }
            if ((424411488321536L & dirtyFlags) != 0) {
                if (vm != null) {
                    vmMediaVolume = vm.mediaVolume;
                } else {
                    vmMediaVolume = null;
                }
                updateRegistration(41, (Observable) vmMediaVolume);
                if (vmMediaVolume != null) {
                    ObservableField<String> observableField37 = vmMediaVolume;
                    vmMediaVolumeGet2 = vmMediaVolume.get();
                } else {
                    ObservableField<String> observableField38 = vmMediaVolume;
                }
            }
            if ((426644877617156L & dirtyFlags) != 0) {
                if (vm != null) {
                    vmAudioOff = vm.audioOff;
                } else {
                    vmAudioOff = null;
                }
                updateRegistration(42, (Observable) vmAudioOff);
                if (vmAudioOff != null) {
                    vmAudioOffGet = vmAudioOff.get();
                }
                if ((dirtyFlags & 426610513674240L) != 0) {
                    if (vmAudioOffGet) {
                        dirtyFlags |= 72057594037927936L;
                    } else {
                        dirtyFlags |= 36028797018963968L;
                    }
                }
                if ((dirtyFlags & 426610511577088L) != 0) {
                    if (vmAudioOffGet) {
                        dirtyFlags |= 288230376151711744L;
                        dirtyFlags_1 |= 274877906944L;
                    } else {
                        dirtyFlags |= 144115188075855872L;
                        dirtyFlags_1 |= 137438953472L;
                    }
                }
                if ((dirtyFlags & 426644871315456L) != 0) {
                    if (vmAudioOffGet) {
                        dirtyFlags_1 |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    } else {
                        dirtyFlags_1 |= 512;
                    }
                }
                if ((dirtyFlags & 426610511579136L) != 0) {
                    if (vmAudioOffGet) {
                        dirtyFlags_1 |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                    } else {
                        dirtyFlags_1 |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                    }
                }
                if ((dirtyFlags & 426610515771392L) != 0) {
                    if (vmAudioOffGet) {
                        dirtyFlags_1 |= 4294967296L;
                    } else {
                        dirtyFlags_1 |= 2147483648L;
                    }
                }
                if ((dirtyFlags & 426610511585280L) != 0) {
                    if (vmAudioOffGet) {
                        dirtyFlags_1 |= 1099511627776L;
                    } else {
                        dirtyFlags_1 |= 549755813888L;
                    }
                }
                if ((dirtyFlags & 426610511577092L) != 0) {
                    if (vmAudioOffGet) {
                        dirtyFlags_1 |= 4398046511104L;
                    } else {
                        dirtyFlags_1 |= 2199023255552L;
                    }
                }
                if ((dirtyFlags & 426610511577088L) != 0) {
                    int vmAudioOffViewVISIBLEViewGONE3 = vmAudioOffGet ? i : 8;
                    ObservableBoolean observableBoolean10 = vmAudioOff;
                    vmAudioOffViewGONEViewVISIBLE2 = vmAudioOffGet ? 8 : i;
                    vmAudioOffViewVISIBLEViewGONE2 = vmAudioOffViewVISIBLEViewGONE3;
                } else {
                    ObservableBoolean observableBoolean11 = vmAudioOff;
                }
            }
            if ((431008558088192L & dirtyFlags) != 0) {
                if (vm != null) {
                    vmTrack = vm.track;
                } else {
                    vmTrack = null;
                }
                updateRegistration(43, (Observable) vmTrack);
                if (vmTrack != null) {
                    vmTrackGet2 = vmTrack.get();
                    ObservableField<String> observableField39 = vmTrack;
                } else {
                    ObservableField<String> observableField40 = vmTrack;
                }
            }
            if ((457396837154816L & dirtyFlags) != 0) {
                if (vm != null) {
                    vmBtConnectInfo = vm.btConnectInfo;
                } else {
                    vmBtConnectInfo = null;
                }
                updateRegistration(45, (Observable) vmBtConnectInfo);
                if (vmBtConnectInfo != null) {
                    ObservableField<String> observableField41 = vmBtConnectInfo;
                    vmEqModeGet = vmEqModeGet3;
                    vmMuteMboundView60AndroidDrawableLexusCdProgressBarMuteMboundView60AndroidDrawableLexusCdProgressBarVol = vmMuteMboundView60AndroidDrawableLexusCdProgressBarMuteMboundView60AndroidDrawableLexusCdProgressBarVol3;
                    vmBtConnectInfoGet = vmBtConnectInfo.get();
                    vmMp3ViewVISIBLEViewGONE = vmMp3ViewVISIBLEViewGONE3;
                    vmModeGet = vmModeGet3;
                    vmMusicNameGet = vmMusicNameGet3;
                    vmStViewVISIBLEViewGONE = vmStViewVISIBLEViewGONE4;
                    vmAuxViewVISIBLEViewGONE = vmAuxViewVISIBLEViewGONE3;
                    vmAslViewVISIBLEViewGONE = vmAslViewVISIBLEViewGONE3;
                    vmRptViewVISIBLEViewGONE = vmRptViewVISIBLEViewGONE2;
                    vmBtViewVISIBLEViewGONE = vmBtViewVISIBLEViewGONE2;
                    vmScanViewVISIBLEViewGONE = vmScanViewVISIBLEViewGONE3;
                    vmBtPlayViewVISIBLEViewVISIBLE = vmBtPlayViewVISIBLEViewVISIBLE3;
                    vmIndexGet = vmIndexGet3;
                    vmRandViewVISIBLEViewGONE = vmRandViewVISIBLEViewGONE4;
                    vmShowVolumeViewVISIBLEViewGONE = vmShowVolumeViewVISIBLEViewGONE3;
                    vmBtSetUpGet = vmBtSetUpGet2;
                    vmTrackGet = vmTrackGet2;
                    vmTimeGet = vmTimeGet4;
                    vmFmFrequencyGet = vmFmFrequencyGet2;
                    vmAlbumGet = vmAlbumGet4;
                    vmEqViewVISIBLEViewGONE = vmEqViewVISIBLEViewGONE4;
                    vmBtConnectInfoGet2 = vmChGet;
                    ObservableField<String> observableField42 = vmAlbum;
                    vmFmViewVISIBLEViewGONE = vmFmViewVISIBLEViewGONE4;
                    vmFmBandGet = vmFmBandGet5;
                    vmFmViewVISIBLEViewGONE2 = mUiParamsWindSpeedGet;
                    mUiParamsWindSpeedGet2 = vmCdViewVISIBLEViewGONE;
                    vmArtistGet = vmArtistGet3;
                    vmArtistGet2 = vmChGet2;
                    vmBtConnectViewVISIBLEViewGONE = vmBtConnectViewVISIBLEViewGONE2;
                    vmUsbMusicTimeGet = vmUsbMusicTimeGet3;
                    vmUsbViewVISIBLEViewGONE = vmUsbViewVISIBLEViewGONE4;
                    vmUsbViewVISIBLEViewGONE2 = 0;
                    vmCd4ViewVISIBLEViewGONE = vmAudioOffViewVISIBLEViewGONE2;
                    ObservableField<String> observableField43 = vmIndex;
                    vmAudioOffViewGONEViewVISIBLE = vmAudioOffViewGONEViewVISIBLE2;
                    String str = vmDiscGet6;
                    vmDiscGet = mUiParamsRightTempStrGet;
                    vmBtSignalGet2 = vmBtSignalGet3;
                    vmBtSignalGet = vmBtPlayViewVISIBLEViewGONE;
                    vmBtTimeGet = vmBtTimeGet4;
                    vmBtTimeGet2 = vmMediaVolumeGet2;
                    vmCd3ViewVISIBLEViewGONE = 0;
                    vmDiscGet2 = str;
                } else {
                    ObservableField<String> observableField44 = vmBtConnectInfo;
                    vmEqModeGet = vmEqModeGet3;
                    vmMuteMboundView60AndroidDrawableLexusCdProgressBarMuteMboundView60AndroidDrawableLexusCdProgressBarVol = vmMuteMboundView60AndroidDrawableLexusCdProgressBarMuteMboundView60AndroidDrawableLexusCdProgressBarVol3;
                    vmBtConnectInfoGet = null;
                    vmMp3ViewVISIBLEViewGONE = vmMp3ViewVISIBLEViewGONE3;
                    vmModeGet = vmModeGet3;
                    vmMusicNameGet = vmMusicNameGet3;
                    vmStViewVISIBLEViewGONE = vmStViewVISIBLEViewGONE4;
                    vmAuxViewVISIBLEViewGONE = vmAuxViewVISIBLEViewGONE3;
                    vmAslViewVISIBLEViewGONE = vmAslViewVISIBLEViewGONE3;
                    vmRptViewVISIBLEViewGONE = vmRptViewVISIBLEViewGONE2;
                    vmBtViewVISIBLEViewGONE = vmBtViewVISIBLEViewGONE2;
                    vmScanViewVISIBLEViewGONE = vmScanViewVISIBLEViewGONE3;
                    vmBtPlayViewVISIBLEViewVISIBLE = vmBtPlayViewVISIBLEViewVISIBLE3;
                    vmIndexGet = vmIndexGet3;
                    vmRandViewVISIBLEViewGONE = vmRandViewVISIBLEViewGONE4;
                    vmShowVolumeViewVISIBLEViewGONE = vmShowVolumeViewVISIBLEViewGONE3;
                    vmBtSetUpGet = vmBtSetUpGet2;
                    vmTrackGet = vmTrackGet2;
                    vmTimeGet = vmTimeGet4;
                    vmFmFrequencyGet = vmFmFrequencyGet2;
                    vmAlbumGet = vmAlbumGet4;
                    vmEqViewVISIBLEViewGONE = vmEqViewVISIBLEViewGONE4;
                    vmBtConnectInfoGet2 = vmChGet;
                    ObservableField<String> observableField45 = vmAlbum;
                    vmFmViewVISIBLEViewGONE = vmFmViewVISIBLEViewGONE4;
                    vmFmBandGet = vmFmBandGet5;
                    vmFmViewVISIBLEViewGONE2 = mUiParamsWindSpeedGet;
                    mUiParamsWindSpeedGet2 = vmCdViewVISIBLEViewGONE;
                    vmArtistGet = vmArtistGet3;
                    vmArtistGet2 = vmChGet2;
                    vmBtConnectViewVISIBLEViewGONE = vmBtConnectViewVISIBLEViewGONE2;
                    vmUsbMusicTimeGet = vmUsbMusicTimeGet3;
                    vmUsbViewVISIBLEViewGONE = vmUsbViewVISIBLEViewGONE4;
                    vmUsbViewVISIBLEViewGONE2 = 0;
                    vmCd4ViewVISIBLEViewGONE = vmAudioOffViewVISIBLEViewGONE2;
                    ObservableField<String> observableField46 = vmIndex;
                    vmAudioOffViewGONEViewVISIBLE = vmAudioOffViewGONEViewVISIBLE2;
                    String str2 = vmDiscGet6;
                    vmDiscGet = mUiParamsRightTempStrGet;
                    vmBtSignalGet2 = vmBtSignalGet3;
                    vmBtSignalGet = vmBtPlayViewVISIBLEViewGONE;
                    vmBtTimeGet = vmBtTimeGet4;
                    vmBtTimeGet2 = vmMediaVolumeGet2;
                    vmCd3ViewVISIBLEViewGONE = 0;
                    vmDiscGet2 = str2;
                }
            } else {
                vmEqModeGet = vmEqModeGet3;
                vmMuteMboundView60AndroidDrawableLexusCdProgressBarMuteMboundView60AndroidDrawableLexusCdProgressBarVol = vmMuteMboundView60AndroidDrawableLexusCdProgressBarMuteMboundView60AndroidDrawableLexusCdProgressBarVol3;
                vmBtConnectInfoGet = null;
                vmMp3ViewVISIBLEViewGONE = vmMp3ViewVISIBLEViewGONE3;
                vmModeGet = vmModeGet3;
                vmMusicNameGet = vmMusicNameGet3;
                vmStViewVISIBLEViewGONE = vmStViewVISIBLEViewGONE4;
                vmAuxViewVISIBLEViewGONE = vmAuxViewVISIBLEViewGONE3;
                vmAslViewVISIBLEViewGONE = vmAslViewVISIBLEViewGONE3;
                vmRptViewVISIBLEViewGONE = vmRptViewVISIBLEViewGONE2;
                vmBtViewVISIBLEViewGONE = vmBtViewVISIBLEViewGONE2;
                vmScanViewVISIBLEViewGONE = vmScanViewVISIBLEViewGONE3;
                vmBtPlayViewVISIBLEViewVISIBLE = vmBtPlayViewVISIBLEViewVISIBLE3;
                vmIndexGet = vmIndexGet3;
                vmRandViewVISIBLEViewGONE = vmRandViewVISIBLEViewGONE4;
                vmShowVolumeViewVISIBLEViewGONE = vmShowVolumeViewVISIBLEViewGONE3;
                vmBtSetUpGet = vmBtSetUpGet2;
                vmTrackGet = vmTrackGet2;
                vmTimeGet = vmTimeGet4;
                vmFmFrequencyGet = vmFmFrequencyGet2;
                vmAlbumGet = vmAlbumGet4;
                vmEqViewVISIBLEViewGONE = vmEqViewVISIBLEViewGONE4;
                vmBtConnectInfoGet2 = vmChGet;
                ObservableField<String> observableField47 = vmAlbum;
                vmFmViewVISIBLEViewGONE = vmFmViewVISIBLEViewGONE4;
                vmFmBandGet = vmFmBandGet5;
                vmFmViewVISIBLEViewGONE2 = mUiParamsWindSpeedGet;
                mUiParamsWindSpeedGet2 = vmCdViewVISIBLEViewGONE;
                vmArtistGet = vmArtistGet3;
                vmArtistGet2 = vmChGet2;
                vmBtConnectViewVISIBLEViewGONE = vmBtConnectViewVISIBLEViewGONE2;
                vmUsbMusicTimeGet = vmUsbMusicTimeGet3;
                vmUsbViewVISIBLEViewGONE = vmUsbViewVISIBLEViewGONE4;
                vmUsbViewVISIBLEViewGONE2 = 0;
                vmCd4ViewVISIBLEViewGONE = vmAudioOffViewVISIBLEViewGONE2;
                ObservableField<String> observableField48 = vmIndex;
                vmAudioOffViewGONEViewVISIBLE = vmAudioOffViewGONEViewVISIBLE2;
                String str3 = vmDiscGet6;
                vmDiscGet = mUiParamsRightTempStrGet;
                vmBtSignalGet2 = vmBtSignalGet3;
                vmBtSignalGet = vmBtPlayViewVISIBLEViewGONE;
                vmBtTimeGet = vmBtTimeGet4;
                vmBtTimeGet2 = vmMediaVolumeGet2;
                vmCd3ViewVISIBLEViewGONE = 0;
                vmDiscGet2 = str3;
            }
        } else {
            vmCd6Get = null;
            i = 0;
            vmEqModeGet = null;
            vmMuteMboundView60AndroidDrawableLexusCdProgressBarMuteMboundView60AndroidDrawableLexusCdProgressBarVol = null;
            vmBtConnectInfoGet = null;
            vmMp3ViewVISIBLEViewGONE = 0;
            vmModeGet = null;
            vmMusicNameGet = null;
            vmStViewVISIBLEViewGONE = 0;
            vmAuxViewVISIBLEViewGONE = 0;
            vmAslViewVISIBLEViewGONE = 0;
            vmRptViewVISIBLEViewGONE = 0;
            vmBtViewVISIBLEViewGONE = 0;
            vmScanViewVISIBLEViewGONE = 0;
            vmBtPlayViewVISIBLEViewVISIBLE = 0;
            vmIndexGet = null;
            vmRandViewVISIBLEViewGONE = 0;
            vmShowVolumeViewVISIBLEViewGONE = 0;
            vmBtSetUpGet = null;
            vmTrackGet = null;
            vmTimeGet = null;
            vmFmFrequencyGet = null;
            vmAlbumGet = null;
            vmEqViewVISIBLEViewGONE = 0;
            vmBtConnectInfoGet2 = vmChGet;
            vmFmViewVISIBLEViewGONE = 0;
            vmFmBandGet = null;
            vmFmViewVISIBLEViewGONE2 = mUiParamsWindSpeedGet;
            mUiParamsWindSpeedGet2 = 0;
            vmArtistGet = null;
            vmArtistGet2 = vmChGet2;
            vmBtConnectViewVISIBLEViewGONE = 0;
            vmUsbMusicTimeGet = null;
            vmUsbViewVISIBLEViewGONE = 0;
            vmUsbViewVISIBLEViewGONE2 = 0;
            vmCd4ViewVISIBLEViewGONE = 0;
            vmAudioOffViewGONEViewVISIBLE = 0;
            vmDiscGet = mUiParamsRightTempStrGet;
            vmBtSignalGet2 = 0;
            vmBtSignalGet = 0;
            vmBtTimeGet = null;
            vmBtTimeGet2 = null;
            vmCd3ViewVISIBLEViewGONE = 0;
            vmDiscGet2 = null;
        }
        if ((dirtyFlags_1 & 2199023255552L) != 0) {
            if (vm != null) {
                vmFmBandGet2 = vmFmBandGet;
                vmCd32 = vm.cd3;
            } else {
                vmFmBandGet2 = vmFmBandGet;
                vmCd32 = vmCd3;
            }
            vmEqViewVISIBLEViewGONE2 = vmEqViewVISIBLEViewGONE;
            updateRegistration(2, (Observable) vmCd32);
            if (vmCd32 != null) {
                vmCd3Get = vmCd32.get();
            }
            boolean androidDatabindingViewDataBindingSafeUnboxVmCd3Get = ViewDataBinding.safeUnbox(vmCd3Get);
            if ((dirtyFlags_1 & 2199023255552L) != 0) {
                if (androidDatabindingViewDataBindingSafeUnboxVmCd3Get) {
                    dirtyFlags |= 1125899906842624L;
                } else {
                    dirtyFlags |= 562949953421312L;
                }
            }
            vmCd3ViewVISIBLEViewGONE = androidDatabindingViewDataBindingSafeUnboxVmCd3Get ? i : 8;
            ObservableField<Boolean> observableField49 = vmCd32;
        } else {
            vmEqViewVISIBLEViewGONE2 = vmEqViewVISIBLEViewGONE;
            vmFmBandGet2 = vmFmBandGet;
        }
        if ((dirtyFlags_1 & PlaybackStateCompat.ACTION_PREPARE_FROM_URI) != 0) {
            if (vm != null) {
                vmCd6 = vm.cd6;
            } else {
                vmCd6 = null;
            }
            updateRegistration(11, (Observable) vmCd6);
            if (vmCd6 != null) {
                vmCd6Get = vmCd6.get();
            }
            boolean androidDatabindingViewDataBindingSafeUnboxVmCd6Get = ViewDataBinding.safeUnbox(vmCd6Get);
            if ((dirtyFlags_1 & PlaybackStateCompat.ACTION_PREPARE_FROM_URI) != 0) {
                if (androidDatabindingViewDataBindingSafeUnboxVmCd6Get) {
                    dirtyFlags_1 |= 17592186044416L;
                } else {
                    dirtyFlags_1 |= 8796093022208L;
                }
            }
            vmCd6ViewVISIBLEViewGONE = androidDatabindingViewDataBindingSafeUnboxVmCd6Get ? i : 8;
            ObservableField<Boolean> observableField50 = vmCd6;
        }
        if ((dirtyFlags_1 & 549755813888L) != 0) {
            if (vm != null) {
                vmCd2 = vm.cd2;
            } else {
                vmCd2 = null;
            }
            updateRegistration(13, (Observable) vmCd2);
            if (vmCd2 != null) {
                vmCd2Get = vmCd2.get();
            }
            boolean androidDatabindingViewDataBindingSafeUnboxVmCd2Get = ViewDataBinding.safeUnbox(vmCd2Get);
            if ((dirtyFlags_1 & 549755813888L) != 0) {
                if (androidDatabindingViewDataBindingSafeUnboxVmCd2Get) {
                    dirtyFlags_1 |= PlaybackStateCompat.ACTION_PREPARE;
                } else {
                    dirtyFlags_1 |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                }
            }
            vmCd2ViewVISIBLEViewGONE = androidDatabindingViewDataBindingSafeUnboxVmCd2Get ? i : 8;
            ObservableField<Boolean> observableField51 = vmCd2;
        }
        if ((dirtyFlags & 36028797018963968L) != 0) {
            if (vm != null) {
                vmCd5 = vm.cd5;
            } else {
                vmCd5 = null;
            }
            updateRegistration(21, (Observable) vmCd5);
            if (vmCd5 != null) {
                vmCd5Get = vmCd5.get();
            }
            boolean androidDatabindingViewDataBindingSafeUnboxVmCd5Get = ViewDataBinding.safeUnbox(vmCd5Get);
            if ((dirtyFlags & 36028797018963968L) != 0) {
                if (androidDatabindingViewDataBindingSafeUnboxVmCd5Get) {
                    dirtyFlags |= 4611686018427387904L;
                } else {
                    dirtyFlags |= 2305843009213693952L;
                }
            }
            vmCd5ViewVISIBLEViewGONE = androidDatabindingViewDataBindingSafeUnboxVmCd5Get ? i : 8;
            ObservableField<Boolean> observableField52 = vmCd5;
        }
        if ((dirtyFlags_1 & 2147483648L) != 0) {
            if (vm != null) {
                vmCd1 = vm.cd1;
            } else {
                vmCd1 = null;
            }
            updateRegistration(22, (Observable) vmCd1);
            if (vmCd1 != null) {
                vmCd1Get = vmCd1.get();
            }
            boolean androidDatabindingViewDataBindingSafeUnboxVmCd1Get = ViewDataBinding.safeUnbox(vmCd1Get);
            if ((dirtyFlags_1 & 2147483648L) != 0) {
                if (androidDatabindingViewDataBindingSafeUnboxVmCd1Get) {
                    dirtyFlags_1 |= 16;
                } else {
                    dirtyFlags_1 |= 8;
                }
            }
            vmCd1ViewVISIBLEViewGONE = androidDatabindingViewDataBindingSafeUnboxVmCd1Get ? i : 8;
            ObservableField<Boolean> observableField53 = vmCd1;
        }
        if ((dirtyFlags_1 & 512) != 0) {
            if (vm != null) {
                vmCd4 = vm.cd4;
            } else {
                vmCd4 = null;
            }
            updateRegistration(35, (Observable) vmCd4);
            if (vmCd4 != null) {
                vmCd4Get = vmCd4.get();
            }
            boolean androidDatabindingViewDataBindingSafeUnboxVmCd4Get = ViewDataBinding.safeUnbox(vmCd4Get);
            if ((dirtyFlags_1 & 512) != 0) {
                if (androidDatabindingViewDataBindingSafeUnboxVmCd4Get) {
                    dirtyFlags |= 4503599627370496L;
                } else {
                    dirtyFlags |= 2251799813685248L;
                }
            }
            if (!androidDatabindingViewDataBindingSafeUnboxVmCd4Get) {
                i = 8;
            }
            ObservableField<Boolean> observableField54 = vmCd4;
            vmUsbViewVISIBLEViewGONE2 = i;
        }
        if ((dirtyFlags & 426610513674240L) != 0) {
            vmAudioOffViewGONEVmCd5ViewVISIBLEViewGONE = vmAudioOffGet ? 8 : vmCd5ViewVISIBLEViewGONE;
        } else {
            vmAudioOffViewGONEVmCd5ViewVISIBLEViewGONE = 0;
        }
        if ((dirtyFlags & 426644871315456L) != 0) {
            vmAudioOffViewGONEVmCd4ViewVISIBLEViewGONE = vmAudioOffGet ? 8 : vmUsbViewVISIBLEViewGONE2;
        } else {
            vmAudioOffViewGONEVmCd4ViewVISIBLEViewGONE = 0;
        }
        if ((dirtyFlags & 426610511579136L) != 0) {
            LexusOEMFMViewModel lexusOEMFMViewModel = vm;
            vmAudioOffViewGONEVmCd6ViewVISIBLEViewGONE = vmAudioOffGet ? 8 : vmCd6ViewVISIBLEViewGONE;
        } else {
            LexusOEMFMViewModel lexusOEMFMViewModel2 = vm;
            vmAudioOffViewGONEVmCd6ViewVISIBLEViewGONE = 0;
        }
        if ((dirtyFlags & 426610515771392L) != 0) {
            long j = dirtyFlags_1;
            vmAudioOffViewGONEVmCd1ViewVISIBLEViewGONE = vmAudioOffGet ? 8 : vmCd1ViewVISIBLEViewGONE;
        } else {
            long j2 = dirtyFlags_1;
            vmAudioOffViewGONEVmCd1ViewVISIBLEViewGONE = 0;
        }
        if ((dirtyFlags & 426610511585280L) != 0) {
            vmAudioOffViewGONEVmCd2ViewVISIBLEViewGONE = vmAudioOffGet ? 8 : vmCd2ViewVISIBLEViewGONE;
        } else {
            vmAudioOffViewGONEVmCd2ViewVISIBLEViewGONE = 0;
        }
        if ((dirtyFlags & 426610511577092L) != 0) {
            vmEqModeGet2 = vmEqModeGet;
            vmAudioOffViewGONEVmCd3ViewVISIBLEViewGONE = vmAudioOffGet ? 8 : vmCd3ViewVISIBLEViewGONE;
        } else {
            vmEqModeGet2 = vmEqModeGet;
            vmAudioOffViewGONEVmCd3ViewVISIBLEViewGONE = 0;
        }
        if ((dirtyFlags & 281474976710656L) != 0) {
            vmDiscGet3 = vmDiscGet2;
            vmAudioOffViewVISIBLEViewGONE = vmCd4ViewVISIBLEViewGONE;
            this.acLayout.setOnClickListener(this.mCallback219);
        } else {
            vmDiscGet3 = vmDiscGet2;
            vmAudioOffViewVISIBLEViewGONE = vmCd4ViewVISIBLEViewGONE;
        }
        if ((351844794630144L & dirtyFlags) != 0) {
            this.blowMode.setImageLevel(mUiParamsBlowingModeGet);
        }
        if ((dirtyFlags & 422212733501440L) != 0) {
            this.btSignal.setVisibility(vmBtConnectViewVISIBLEViewGONE);
        }
        if ((422212465066048L & dirtyFlags) != 0) {
            this.btSignal.setImageLevel(vmBtSignalGet2);
        }
        if ((dirtyFlags & 426610515771392L) != 0) {
            this.cd1.setVisibility(vmAudioOffViewGONEVmCd1ViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 426610511585280L) != 0) {
            this.cd2.setVisibility(vmAudioOffViewGONEVmCd2ViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 426610511577092L) != 0) {
            this.cd3.setVisibility(vmAudioOffViewGONEVmCd3ViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 426644871315456L) != 0) {
            this.cd4.setVisibility(vmAudioOffViewGONEVmCd4ViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 426610513674240L) != 0) {
            this.cd5.setVisibility(vmAudioOffViewGONEVmCd5ViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 426610511579136L) != 0) {
            this.cd6.setVisibility(vmAudioOffViewGONEVmCd6ViewVISIBLEViewGONE);
        }
        if ((422216760033280L & dirtyFlags) != 0) {
            this.cdLayout.setVisibility(mUiParamsWindSpeedGet2);
        }
        if ((426610511577088L & dirtyFlags) != 0) {
            this.discMode.setVisibility(vmAudioOffViewGONEViewVISIBLE);
            this.mboundView1.setVisibility(vmAudioOffViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 422212465066240L) != 0) {
            int i2 = vmAudioOffViewGONEVmCd6ViewVISIBLEViewGONE;
            vmDiscGet4 = vmDiscGet3;
            TextViewBindingAdapter.setText(this.discNum, vmDiscGet4);
        } else {
            vmDiscGet4 = vmDiscGet3;
        }
        if ((dirtyFlags & 422212465131520L) != 0) {
            String str4 = vmDiscGet4;
            vmDiscGet5 = vmEqModeGet2;
            TextViewBindingAdapter.setText(this.eqMode, vmDiscGet5);
        } else {
            vmDiscGet5 = vmEqModeGet2;
        }
        if ((dirtyFlags & 422212465065992L) != 0) {
            String str5 = vmDiscGet5;
            vmEqViewVISIBLEViewGONE3 = vmEqViewVISIBLEViewGONE2;
            this.eqMode.setVisibility(vmEqViewVISIBLEViewGONE3);
        } else {
            String vmEqModeGet4 = vmDiscGet5;
            vmEqViewVISIBLEViewGONE3 = vmEqViewVISIBLEViewGONE2;
        }
        if ((dirtyFlags & 422212498620416L) != 0) {
            int i3 = vmEqViewVISIBLEViewGONE3;
            vmFmBandGet3 = vmFmBandGet2;
            TextViewBindingAdapter.setText(this.fmBand, vmFmBandGet3);
        } else {
            vmFmBandGet3 = vmFmBandGet2;
        }
        if ((dirtyFlags & 422229644935168L) != 0) {
            String str6 = vmFmBandGet3;
            vmFmBandGet4 = vmBtConnectInfoGet2;
            TextViewBindingAdapter.setText(this.fmCh, vmFmBandGet4);
        } else {
            vmFmBandGet4 = vmBtConnectInfoGet2;
        }
        if ((dirtyFlags & 422212465590272L) != 0) {
            String str7 = vmFmBandGet4;
            vmChGet3 = vmFmFrequencyGet;
            TextViewBindingAdapter.setText(this.fmFrequency, vmChGet3);
        } else {
            String vmChGet4 = vmFmBandGet4;
            vmChGet3 = vmFmFrequencyGet;
        }
        if ((dirtyFlags & 422349904019456L) != 0) {
            String str8 = vmChGet3;
            vmFmViewVISIBLEViewGONE3 = vmFmViewVISIBLEViewGONE;
            this.fmLayout.setVisibility(vmFmViewVISIBLEViewGONE3);
        } else {
            String vmFmFrequencyGet3 = vmChGet3;
            vmFmViewVISIBLEViewGONE3 = vmFmViewVISIBLEViewGONE;
        }
        if ((dirtyFlags & 422281184542720L) != 0) {
            int i4 = vmFmViewVISIBLEViewGONE3;
            vmShowVolumeViewVISIBLEViewGONE2 = vmShowVolumeViewVISIBLEViewGONE;
            this.layoutVolume.setVisibility(vmShowVolumeViewVISIBLEViewGONE2);
        } else {
            vmShowVolumeViewVISIBLEViewGONE2 = vmShowVolumeViewVISIBLEViewGONE;
        }
        if ((dirtyFlags & 422212465065985L) != 0) {
            int i5 = vmShowVolumeViewVISIBLEViewGONE2;
            vmRandViewVISIBLEViewGONE2 = vmRandViewVISIBLEViewGONE;
            this.mboundView18.setVisibility(vmRandViewVISIBLEViewGONE2);
            this.mboundView32.setVisibility(vmRandViewVISIBLEViewGONE2);
            this.mboundView43.setVisibility(vmRandViewVISIBLEViewGONE2);
        } else {
            vmRandViewVISIBLEViewGONE2 = vmRandViewVISIBLEViewGONE;
        }
        if ((dirtyFlags & 422212465067008L) != 0) {
            int i6 = vmRandViewVISIBLEViewGONE2;
            vmRandViewVISIBLEViewGONE3 = vmRptViewVISIBLEViewGONE;
            this.mboundView19.setVisibility(vmRandViewVISIBLEViewGONE3);
            this.mboundView33.setVisibility(vmRandViewVISIBLEViewGONE3);
            this.mboundView44.setVisibility(vmRandViewVISIBLEViewGONE3);
        } else {
            vmRandViewVISIBLEViewGONE3 = vmRptViewVISIBLEViewGONE;
        }
        if ((dirtyFlags & 422212465066112L) != 0) {
            int i7 = vmRandViewVISIBLEViewGONE3;
            vmUsbStatusGet = vmUsbStatusGet2;
            TextViewBindingAdapter.setText(this.mboundView20, vmUsbStatusGet);
            TextViewBindingAdapter.setText(this.mboundView34, vmUsbStatusGet);
            TextViewBindingAdapter.setText(this.mboundView45, vmUsbStatusGet);
        } else {
            int vmRptViewVISIBLEViewGONE3 = vmRandViewVISIBLEViewGONE3;
            vmUsbStatusGet = vmUsbStatusGet2;
        }
        if ((dirtyFlags & 422212481843200L) != 0) {
            String str9 = vmUsbStatusGet;
            vmScanViewVISIBLEViewGONE2 = vmScanViewVISIBLEViewGONE;
            this.mboundView21.setVisibility(vmScanViewVISIBLEViewGONE2);
            this.mboundView35.setVisibility(vmScanViewVISIBLEViewGONE2);
            this.mboundView46.setVisibility(vmScanViewVISIBLEViewGONE2);
        } else {
            vmScanViewVISIBLEViewGONE2 = vmScanViewVISIBLEViewGONE;
        }
        if ((dirtyFlags & 422221055000576L) != 0) {
            int i8 = vmScanViewVISIBLEViewGONE2;
            vmStViewVISIBLEViewGONE2 = vmStViewVISIBLEViewGONE;
            this.mboundView36.setVisibility(vmStViewVISIBLEViewGONE2);
            this.mboundView47.setVisibility(vmStViewVISIBLEViewGONE2);
            this.st.setVisibility(vmStViewVISIBLEViewGONE2);
        } else {
            vmStViewVISIBLEViewGONE2 = vmStViewVISIBLEViewGONE;
        }
        if ((dirtyFlags & 422762220879872L) != 0) {
            int i9 = vmStViewVISIBLEViewGONE2;
            vmStViewVISIBLEViewGONE3 = vmBtViewVISIBLEViewGONE;
            this.mboundView53.setVisibility(vmStViewVISIBLEViewGONE3);
        } else {
            vmStViewVISIBLEViewGONE3 = vmBtViewVISIBLEViewGONE;
        }
        if ((dirtyFlags & 457396837154816L) != 0) {
            int i10 = vmStViewVISIBLEViewGONE3;
            vmBtConnectInfoGet3 = vmBtConnectInfoGet;
            TextViewBindingAdapter.setText(this.mboundView54, vmBtConnectInfoGet3);
        } else {
            int vmBtViewVISIBLEViewGONE3 = vmStViewVISIBLEViewGONE3;
            vmBtConnectInfoGet3 = vmBtConnectInfoGet;
        }
        if ((dirtyFlags & 422212465065986L) != 0) {
            String str10 = vmBtConnectInfoGet3;
            vmBtConnectInfoGet4 = vmBtSetUpGet;
            TextViewBindingAdapter.setText(this.mboundView55, vmBtConnectInfoGet4);
        } else {
            vmBtConnectInfoGet4 = vmBtSetUpGet;
        }
        if ((dirtyFlags & 422487342972928L) != 0) {
            String str11 = vmBtConnectInfoGet4;
            this.mboundView56.setVisibility(vmBtSignalGet);
            vmBtPlayViewVISIBLEViewVISIBLE2 = vmBtPlayViewVISIBLEViewVISIBLE;
            this.mboundView57.setVisibility(vmBtPlayViewVISIBLEViewVISIBLE2);
        } else {
            String vmBtSetUpGet3 = vmBtConnectInfoGet4;
            vmBtPlayViewVISIBLEViewVISIBLE2 = vmBtPlayViewVISIBLEViewVISIBLE;
        }
        if ((dirtyFlags & 422212466114560L) != 0) {
            int i11 = vmBtPlayViewVISIBLEViewVISIBLE2;
            vmBtTimeGet3 = vmBtTimeGet;
            TextViewBindingAdapter.setText(this.mboundView57, vmBtTimeGet3);
        } else {
            vmBtTimeGet3 = vmBtTimeGet;
        }
        if ((dirtyFlags & 422213001936896L) != 0) {
            String str12 = vmBtTimeGet3;
            vmMuteMboundView60AndroidDrawableLexusCdProgressBarMuteMboundView60AndroidDrawableLexusCdProgressBarVol2 = vmMuteMboundView60AndroidDrawableLexusCdProgressBarMuteMboundView60AndroidDrawableLexusCdProgressBarVol;
            ImageViewBindingAdapter.setImageDrawable(this.mboundView60, vmMuteMboundView60AndroidDrawableLexusCdProgressBarMuteMboundView60AndroidDrawableLexusCdProgressBarVol2);
        } else {
            vmMuteMboundView60AndroidDrawableLexusCdProgressBarMuteMboundView60AndroidDrawableLexusCdProgressBarVol2 = vmMuteMboundView60AndroidDrawableLexusCdProgressBarMuteMboundView60AndroidDrawableLexusCdProgressBarVol;
        }
        if ((dirtyFlags & 424411488321536L) != 0) {
            Drawable drawable2 = vmMuteMboundView60AndroidDrawableLexusCdProgressBarMuteMboundView60AndroidDrawableLexusCdProgressBarVol2;
            vmMediaVolumeGet = vmBtTimeGet2;
            TextViewBindingAdapter.setText(this.mboundView61, vmMediaVolumeGet);
        } else {
            vmMediaVolumeGet = vmBtTimeGet2;
        }
        if ((dirtyFlags & 422212532174848L) != 0) {
            String str13 = vmMediaVolumeGet;
            vmAslViewVISIBLEViewGONE2 = vmAslViewVISIBLEViewGONE;
            this.mboundView8.setVisibility(vmAslViewVISIBLEViewGONE2);
        } else {
            vmAslViewVISIBLEViewGONE2 = vmAslViewVISIBLEViewGONE;
        }
        if ((dirtyFlags & 422214612549632L) != 0) {
            int i12 = vmAslViewVISIBLEViewGONE2;
            vmAuxViewVISIBLEViewGONE2 = vmAuxViewVISIBLEViewGONE;
            this.modeAux.setVisibility(vmAuxViewVISIBLEViewGONE2);
        } else {
            vmAuxViewVISIBLEViewGONE2 = vmAuxViewVISIBLEViewGONE;
        }
        if ((dirtyFlags & 422212465070080L) != 0) {
            int i13 = vmAuxViewVISIBLEViewGONE2;
            vmModeGet2 = vmModeGet;
            TextViewBindingAdapter.setText(this.modeFm, vmModeGet2);
        } else {
            vmModeGet2 = vmModeGet;
        }
        if ((dirtyFlags & 422212465066016L) != 0) {
            String str14 = vmModeGet2;
            vmIndexGet2 = vmIndexGet;
            TextViewBindingAdapter.setText(this.mp3Index, vmIndexGet2);
            TextViewBindingAdapter.setText(this.usbIndex, vmIndexGet2);
        } else {
            vmIndexGet2 = vmIndexGet;
        }
        if ((dirtyFlags & 422212465197056L) != 0) {
            String str15 = vmIndexGet2;
            vmMp3ViewVISIBLEViewGONE2 = vmMp3ViewVISIBLEViewGONE;
            this.mp3Layout.setVisibility(vmMp3ViewVISIBLEViewGONE2);
        } else {
            vmMp3ViewVISIBLEViewGONE2 = vmMp3ViewVISIBLEViewGONE;
        }
        if ((dirtyFlags & 422212465066000L) != 0) {
            int i14 = vmMp3ViewVISIBLEViewGONE2;
            vmAlbumGet2 = vmAlbumGet;
            TextViewBindingAdapter.setText(this.mp3MusicAlbum, vmAlbumGet2);
            TextViewBindingAdapter.setText(this.usbMusicAlbum, vmAlbumGet2);
        } else {
            vmAlbumGet2 = vmAlbumGet;
        }
        if ((dirtyFlags & 422212599283712L) != 0) {
            String str16 = vmAlbumGet2;
            vmAlbumGet3 = vmArtistGet;
            TextViewBindingAdapter.setText(this.mp3MusicArtist, vmAlbumGet3);
            TextViewBindingAdapter.setText(this.usbMusicArtist, vmAlbumGet3);
        } else {
            vmAlbumGet3 = vmArtistGet;
        }
        if ((dirtyFlags & 422212465098752L) != 0) {
            String str17 = vmAlbumGet3;
            vmMusicNameGet2 = vmMusicNameGet;
            TextViewBindingAdapter.setText(this.mp3MusicName, vmMusicNameGet2);
            TextViewBindingAdapter.setText(this.usbMusicName, vmMusicNameGet2);
        } else {
            String vmArtistGet4 = vmAlbumGet3;
            vmMusicNameGet2 = vmMusicNameGet;
        }
        if ((dirtyFlags & 422212465082368L) != 0) {
            String str18 = vmMusicNameGet2;
            vmUsbMusicTimeGet2 = vmUsbMusicTimeGet;
            TextViewBindingAdapter.setText(this.mp3MusicTime, vmUsbMusicTimeGet2);
            TextViewBindingAdapter.setText(this.usbMusicTime, vmUsbMusicTimeGet2);
        } else {
            vmUsbMusicTimeGet2 = vmUsbMusicTimeGet;
        }
        if ((dirtyFlags & 422212465066496L) != 0) {
            String str19 = vmUsbMusicTimeGet2;
            vmTimeGet2 = vmTimeGet;
            TextViewBindingAdapter.setText(this.time, vmTimeGet2);
        } else {
            vmTimeGet2 = vmTimeGet;
        }
        if ((dirtyFlags & 431008558088192L) != 0) {
            String str20 = vmTimeGet2;
            vmTimeGet3 = vmTrackGet;
            TextViewBindingAdapter.setText(this.trackNum, vmTimeGet3);
        } else {
            vmTimeGet3 = vmTrackGet;
        }
        if ((dirtyFlags & 369435906932736L) != 0) {
            String str21 = vmTimeGet3;
            mUiParamsLeftTempStrGet = vmArtistGet2;
            TextViewBindingAdapter.setText(this.tvLeftTemperature, mUiParamsLeftTempStrGet);
        } else {
            String vmTrackGet3 = vmTimeGet3;
            mUiParamsLeftTempStrGet = vmArtistGet2;
        }
        if ((dirtyFlags & 351843729276928L) != 0) {
            String str22 = mUiParamsLeftTempStrGet;
            mUiParamsRightTempStrGet2 = vmDiscGet;
            TextViewBindingAdapter.setText(this.tvRightTemperature, mUiParamsRightTempStrGet2);
        } else {
            mUiParamsRightTempStrGet2 = vmDiscGet;
        }
        if ((dirtyFlags & 422212465328128L) != 0) {
            String str23 = mUiParamsRightTempStrGet2;
            vmUsbViewVISIBLEViewGONE3 = vmUsbViewVISIBLEViewGONE;
            this.usbLayout.setVisibility(vmUsbViewVISIBLEViewGONE3);
        } else {
            vmUsbViewVISIBLEViewGONE3 = vmUsbViewVISIBLEViewGONE;
        }
        if ((dirtyFlags & 352943232516096L) != 0) {
            int i15 = vmUsbViewVISIBLEViewGONE3;
            this.windSpeedLevel.setImageLevel(vmFmViewVISIBLEViewGONE2);
            return;
        }
        int vmUsbViewVISIBLEViewGONE5 = vmFmViewVISIBLEViewGONE2;
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        LexusOEMFMViewModel vm = this.mVm;
        if (vm != null) {
            vm.openAir(callbackArg_0);
        }
    }
}
