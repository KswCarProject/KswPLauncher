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
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.view.lexus.LexusOEMFMViewModel;
import com.wits.ksw.launcher.view.lexus.LexusUiParams;
import com.wits.ksw.launcher.view.lexus.VolumeSeekBar;

/* loaded from: classes7.dex */
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

    /* renamed from: st */
    public final TextView f177st;
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

    public abstract void setMUiParams(LexusUiParams mUiParams);

    public abstract void setVm(LexusOEMFMViewModel vm);

    protected ActivityLexusOemFmBinding(Object _bindingComponent, View _root, int _localFieldCount, RelativeLayout acLayout, ImageView blowMode, ImageView btSignal, ImageView cd1, ImageView cd2, ImageView cd3, ImageView cd4, ImageView cd5, ImageView cd6, RelativeLayout cdLayout, TextView discMode, TextView discNum, TextView eqMode, TextView fmBand, TextView fmCh, TextView fmFrequency, RelativeLayout fmLayout, RelativeLayout layoutVolume, ImageView modeAux, ImageView modeBt, TextView modeFm, TextView mp3Index, RelativeLayout mp3Layout, TextView mp3MusicAlbum, TextView mp3MusicArtist, TextView mp3MusicName, TextView mp3MusicTime, TextView st, TextView time, TextView trackNum, TextView tvLeftTemperature, TextView tvRightTemperature, TextView usbIndex, RelativeLayout usbLayout, TextView usbMusicAlbum, TextView usbMusicArtist, TextView usbMusicName, TextView usbMusicTime, VolumeSeekBar volumeBar, ImageView windSpeedLevel) {
        super(_bindingComponent, _root, _localFieldCount);
        this.acLayout = acLayout;
        this.blowMode = blowMode;
        this.btSignal = btSignal;
        this.cd1 = cd1;
        this.cd2 = cd2;
        this.cd3 = cd3;
        this.cd4 = cd4;
        this.cd5 = cd5;
        this.cd6 = cd6;
        this.cdLayout = cdLayout;
        this.discMode = discMode;
        this.discNum = discNum;
        this.eqMode = eqMode;
        this.fmBand = fmBand;
        this.fmCh = fmCh;
        this.fmFrequency = fmFrequency;
        this.fmLayout = fmLayout;
        this.layoutVolume = layoutVolume;
        this.modeAux = modeAux;
        this.modeBt = modeBt;
        this.modeFm = modeFm;
        this.mp3Index = mp3Index;
        this.mp3Layout = mp3Layout;
        this.mp3MusicAlbum = mp3MusicAlbum;
        this.mp3MusicArtist = mp3MusicArtist;
        this.mp3MusicName = mp3MusicName;
        this.mp3MusicTime = mp3MusicTime;
        this.f177st = st;
        this.time = time;
        this.trackNum = trackNum;
        this.tvLeftTemperature = tvLeftTemperature;
        this.tvRightTemperature = tvRightTemperature;
        this.usbIndex = usbIndex;
        this.usbLayout = usbLayout;
        this.usbMusicAlbum = usbMusicAlbum;
        this.usbMusicArtist = usbMusicArtist;
        this.usbMusicName = usbMusicName;
        this.usbMusicTime = usbMusicTime;
        this.volumeBar = volumeBar;
        this.windSpeedLevel = windSpeedLevel;
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
        return (ActivityLexusOemFmBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_lexus_oem_fm, root, attachToRoot, component);
    }

    public static ActivityLexusOemFmBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLexusOemFmBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityLexusOemFmBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_lexus_oem_fm, null, false, component);
    }

    public static ActivityLexusOemFmBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLexusOemFmBinding bind(View view, Object component) {
        return (ActivityLexusOemFmBinding) bind(component, view, C0899R.C0902layout.activity_lexus_oem_fm);
    }
}
