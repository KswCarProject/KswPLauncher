package com.wits.ksw.launcher.view.lexus;

import android.arch.lifecycle.ViewModel;
import android.content.ComponentName;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;

public class LexusOEMFMViewModel extends ViewModel {
    private OEMFMActivity activity;
    public final ObservableField<String> album = new ObservableField<>();
    public final ObservableField<String> artist = new ObservableField<>();
    public final ObservableField<Boolean> asl = new ObservableField<>();
    public final ObservableBoolean audioOff = new ObservableBoolean();
    public final ObservableBoolean aux = new ObservableBoolean();
    public final ObservableField<String> bal = new ObservableField<>();
    public final ObservableField<String> bas = new ObservableField<>();
    public final ObservableBoolean bt = new ObservableBoolean();
    public final ObservableField<Boolean> btConnect = new ObservableField<>();
    public final ObservableField<String> btConnectInfo = new ObservableField<>();
    public final ObservableField<Boolean> btPlay = new ObservableField<>();
    public final ObservableField<String> btSetUp = new ObservableField<>();
    public final ObservableInt btSignal = new ObservableInt();
    public final ObservableField<String> btTime = new ObservableField<>();
    public final ObservableBoolean cd = new ObservableBoolean();
    public final ObservableField<Boolean> cd1 = new ObservableField<>();
    public final ObservableField<Boolean> cd2 = new ObservableField<>();
    public final ObservableField<Boolean> cd3 = new ObservableField<>();
    public final ObservableField<Boolean> cd4 = new ObservableField<>();
    public final ObservableField<Boolean> cd5 = new ObservableField<>();
    public final ObservableField<Boolean> cd6 = new ObservableField<>();
    public final ObservableField<String> cdMode = new ObservableField<>();
    public final ObservableField<String> ch = new ObservableField<>();
    public final ObservableField<String> disc = new ObservableField<>();
    public final ObservableBoolean eq = new ObservableBoolean();
    public final ObservableField<String> eqMode = new ObservableField<>();
    public final ObservableField<String> fad = new ObservableField<>();
    public final ObservableBoolean fm = new ObservableBoolean();
    public final ObservableField<String> fmBand = new ObservableField<>();
    public final ObservableField<String> fmFrequency = new ObservableField<>();
    public final ObservableField<String> folder = new ObservableField<>();
    public final ObservableField<String> index = new ObservableField<>();
    public final ObservableField<String> mediaVolume = new ObservableField<>();
    public final ObservableField<String> mid = new ObservableField<>();
    public final ObservableField<String> mode = new ObservableField<>();
    public final ObservableBoolean mp3 = new ObservableBoolean();
    public final ObservableField<String> musicName = new ObservableField<>();
    public final ObservableBoolean mute = new ObservableBoolean();
    public final ObservableField<Boolean> pause = new ObservableField<>();
    public final ObservableField<Boolean> rand = new ObservableField<>();
    public final ObservableField<Boolean> rpt = new ObservableField<>();
    public final ObservableField<Boolean> scan = new ObservableField<>();
    public final ObservableBoolean showVolume = new ObservableBoolean();
    public final ObservableField<Boolean> st = new ObservableField<>();
    public final ObservableField<String> time = new ObservableField<>();
    public final ObservableField<String> track = new ObservableField<>();
    public final ObservableField<String> tre = new ObservableField<>();
    public final ObservableBoolean usb = new ObservableBoolean();
    public final ObservableField<Boolean> usbAsl = new ObservableField<>();
    public final ObservableField<String> usbMusicTime = new ObservableField<>();
    public final ObservableField<Boolean> usbRand = new ObservableField<>();
    public final ObservableField<Boolean> usbRpt = new ObservableField<>();
    public final ObservableField<Boolean> usbScan = new ObservableField<>();
    public final ObservableField<Boolean> usbSt = new ObservableField<>();
    public final ObservableField<String> usbStatus = new ObservableField<>();

    public void setActivity(OEMFMActivity activity2) {
        this.activity = activity2;
    }

    public void openAir(View view) {
        ComponentName cn = new ComponentName("com.wits.ksw.airc", "com.wits.ksw.airc.LexusAirControl");
        Intent intent = new Intent();
        intent.setComponent(cn);
        this.activity.overridePendingTransition(0, 0);
        this.activity.startActivity(intent);
    }
}
