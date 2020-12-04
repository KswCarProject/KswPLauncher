package com.wits.ksw.launcher.view.lexus;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

public class LexusUiParams {
    public final ObservableBoolean acStatus = new ObservableBoolean();
    public final ObservableBoolean autoSwitch = new ObservableBoolean();
    public final ObservableBoolean backMistSwitch = new ObservableBoolean();
    public final ObservableInt blowingMode = new ObservableInt();
    public final ObservableBoolean dual = new ObservableBoolean();
    public final ObservableBoolean frontMistSwitch = new ObservableBoolean();
    public final ObservableBoolean isOpen = new ObservableBoolean();
    public final ObservableField<String> leftTempStr = new ObservableField<>();
    public final ObservableInt loopMode = new ObservableInt();
    public final ObservableField<String> rightTempStr = new ObservableField<>();
    public final ObservableInt windSpeed = new ObservableInt();

    public void setOpen(boolean isOpen2) {
        this.isOpen.set(isOpen2);
    }

    public void setDual(boolean dual2) {
        this.dual.set(dual2);
    }

    public void setLoopMode(int value) {
        if (value > 7) {
            value = 7;
        }
        this.loopMode.set(value);
    }

    public void setFrontMistSwitch(boolean frontMistSwitch2) {
        this.frontMistSwitch.set(frontMistSwitch2);
    }

    public void setBackMistSwitch(boolean backMistSwitch2) {
        this.backMistSwitch.set(backMistSwitch2);
    }

    public void setAutoSwitch(boolean autoSwitch2) {
        this.autoSwitch.set(autoSwitch2);
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
