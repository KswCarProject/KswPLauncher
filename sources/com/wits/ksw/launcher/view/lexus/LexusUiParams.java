package com.wits.ksw.launcher.view.lexus;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

/* loaded from: classes3.dex */
public class LexusUiParams {
    public final ObservableBoolean isOpen = new ObservableBoolean();
    public final ObservableField<String> leftTempStr = new ObservableField<>();
    public final ObservableField<String> rightTempStr = new ObservableField<>();
    public final ObservableInt blowingMode = new ObservableInt();
    public final ObservableInt windSpeed = new ObservableInt();
    public final ObservableBoolean acStatus = new ObservableBoolean();
    public final ObservableInt loopMode = new ObservableInt();
    public final ObservableBoolean backMistSwitch = new ObservableBoolean();
    public final ObservableBoolean frontMistSwitch = new ObservableBoolean();
    public final ObservableBoolean autoSwitch = new ObservableBoolean();
    public final ObservableBoolean dual = new ObservableBoolean();

    public void setOpen(boolean isOpen) {
        this.isOpen.set(isOpen);
    }

    public void setDual(boolean dual) {
        this.dual.set(dual);
    }

    public void setLoopMode(int value) {
        if (value > 7) {
            value = 7;
        }
        this.loopMode.set(value);
    }

    public void setFrontMistSwitch(boolean frontMistSwitch) {
        this.frontMistSwitch.set(frontMistSwitch);
    }

    public void setBackMistSwitch(boolean backMistSwitch) {
        this.backMistSwitch.set(backMistSwitch);
    }

    public void setAutoSwitch(boolean autoSwitch) {
        this.autoSwitch.set(autoSwitch);
    }

    public void setACStatus(boolean value) {
        this.acStatus.set(value);
    }

    public void setWindSpeed(int value) {
        this.windSpeed.set(value);
    }

    public void setBlowingMode(int mode) {
        this.blowingMode.set(mode);
    }

    public void setRightTempStr(String value) {
        this.rightTempStr.set(value);
    }

    public void setLeftTempStr(String value) {
        this.leftTempStr.set(value);
    }
}
