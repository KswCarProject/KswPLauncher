package com.wits.ksw.launcher.view.lexus;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

public class LexusOEMFMViewModel extends ViewModel {
    public final ObservableField<Boolean> asl = new ObservableField<>();
    public final ObservableBoolean cd = new ObservableBoolean();
    public final ObservableField<String> ch = new ObservableField<>();
    public final ObservableField<String> disc = new ObservableField<>();
    public final ObservableBoolean fm = new ObservableBoolean();
    public final ObservableField<String> fmBand = new ObservableField<>();
    public final ObservableField<String> fmFrequency = new ObservableField<>();
    public final ObservableField<String> mode = new ObservableField<>();
    public final ObservableField<Boolean> pause = new ObservableField<>();
    public final ObservableField<Boolean> rand = new ObservableField<>();
    public final ObservableField<Boolean> rpt = new ObservableField<>();
    public final ObservableField<Boolean> scan = new ObservableField<>();
    public final ObservableField<Boolean> st = new ObservableField<>();
    public final ObservableField<String> time = new ObservableField<>();
    public final ObservableField<String> track = new ObservableField<>();
}
