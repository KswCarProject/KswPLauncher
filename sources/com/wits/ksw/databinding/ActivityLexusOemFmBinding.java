package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.view.lexus.LexusOEMFMViewModel;
import com.wits.ksw.launcher.view.lexus.LexusUiParams;
import com.wits.ksw.launcher.view.lexus.VolumeSeekBar;

public abstract class ActivityLexusOemFmBinding extends ViewDataBinding {
    public final RelativeLayout acLayout;
    public final ImageView blowMode;
    public final ImageView btSignal;
    public final ImageView cd1;
    public final ImageView cd2;
    public final ImageView cd3;
    public final ImageView cd4;
    public final ImageView cd5;
    public final ImageView cd6;
    public final RelativeLayout cdLayout;
    public final TextView discMode;
    public final TextView discNum;
    public final TextView eqMode;
    public final TextView fmBand;
    public final TextView fmCh;
    public final TextView fmFrequency;
    public final RelativeLayout fmLayout;
    public final RelativeLayout layoutVolume;
    @Bindable
    protected LexusUiParams mMUiParams;
    @Bindable
    protected LexusOEMFMViewModel mVm;
    public final ImageView modeAux;
    public final ImageView modeBt;
    public final TextView modeFm;
    public final TextView mp3Index;
    public final RelativeLayout mp3Layout;
    public final TextView mp3MusicAlbum;
    public final TextView mp3MusicArtist;
    public final TextView mp3MusicName;
    public final TextView mp3MusicTime;
    public final TextView st;
    public final TextView time;
    public final TextView trackNum;
    public final TextView tvLeftTemperature;
    public final TextView tvRightTemperature;
    public final TextView usbIndex;
    public final RelativeLayout usbLayout;
    public final TextView usbMusicAlbum;
    public final TextView usbMusicArtist;
    public final TextView usbMusicName;
    public final TextView usbMusicTime;
    public final VolumeSeekBar volumeBar;
    public final ImageView windSpeedLevel;

    public abstract void setMUiParams(LexusUiParams lexusUiParams);

    public abstract void setVm(LexusOEMFMViewModel lexusOEMFMViewModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected ActivityLexusOemFmBinding(Object _bindingComponent, View _root, int _localFieldCount, RelativeLayout acLayout2, ImageView blowMode2, ImageView btSignal2, ImageView cd12, ImageView cd22, ImageView cd32, ImageView cd42, ImageView cd52, ImageView cd62, RelativeLayout cdLayout2, TextView discMode2, TextView discNum2, TextView eqMode2, TextView fmBand2, TextView fmCh2, TextView fmFrequency2, RelativeLayout fmLayout2, RelativeLayout layoutVolume2, ImageView modeAux2, ImageView modeBt2, TextView modeFm2, TextView mp3Index2, RelativeLayout mp3Layout2, TextView mp3MusicAlbum2, TextView mp3MusicArtist2, TextView mp3MusicName2, TextView mp3MusicTime2, TextView st2, TextView time2, TextView trackNum2, TextView tvLeftTemperature2, TextView tvRightTemperature2, TextView usbIndex2, RelativeLayout usbLayout2, TextView usbMusicAlbum2, TextView usbMusicArtist2, TextView usbMusicName2, TextView usbMusicTime2, VolumeSeekBar volumeBar2, ImageView windSpeedLevel2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.acLayout = acLayout2;
        this.blowMode = blowMode2;
        this.btSignal = btSignal2;
        this.cd1 = cd12;
        this.cd2 = cd22;
        this.cd3 = cd32;
        this.cd4 = cd42;
        this.cd5 = cd52;
        this.cd6 = cd62;
        this.cdLayout = cdLayout2;
        this.discMode = discMode2;
        this.discNum = discNum2;
        this.eqMode = eqMode2;
        this.fmBand = fmBand2;
        this.fmCh = fmCh2;
        this.fmFrequency = fmFrequency2;
        this.fmLayout = fmLayout2;
        this.layoutVolume = layoutVolume2;
        this.modeAux = modeAux2;
        this.modeBt = modeBt2;
        this.modeFm = modeFm2;
        this.mp3Index = mp3Index2;
        this.mp3Layout = mp3Layout2;
        this.mp3MusicAlbum = mp3MusicAlbum2;
        this.mp3MusicArtist = mp3MusicArtist2;
        this.mp3MusicName = mp3MusicName2;
        this.mp3MusicTime = mp3MusicTime2;
        this.st = st2;
        this.time = time2;
        this.trackNum = trackNum2;
        this.tvLeftTemperature = tvLeftTemperature2;
        this.tvRightTemperature = tvRightTemperature2;
        this.usbIndex = usbIndex2;
        this.usbLayout = usbLayout2;
        this.usbMusicAlbum = usbMusicAlbum2;
        this.usbMusicArtist = usbMusicArtist2;
        this.usbMusicName = usbMusicName2;
        this.usbMusicTime = usbMusicTime2;
        this.volumeBar = volumeBar2;
        this.windSpeedLevel = windSpeedLevel2;
    }

    public LexusOEMFMViewModel getVm() {
        return this.mVm;
    }

    public LexusUiParams getMUiParams() {
        return this.mMUiParams;
    }

    public static ActivityLexusOemFmBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLexusOemFmBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityLexusOemFmBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_lexus_oem_fm, root, attachToRoot, component);
    }

    public static ActivityLexusOemFmBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLexusOemFmBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityLexusOemFmBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_lexus_oem_fm, (ViewGroup) null, false, component);
    }

    public static ActivityLexusOemFmBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLexusOemFmBinding bind(View view, Object component) {
        return (ActivityLexusOemFmBinding) bind(component, view, R.layout.activity_lexus_oem_fm);
    }
}
