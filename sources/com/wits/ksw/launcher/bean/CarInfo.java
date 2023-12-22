package com.wits.ksw.launcher.bean;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.databinding.ObservableInt;

/* loaded from: classes9.dex */
public class CarInfo {
    public ObservableBoolean carImage = new ObservableBoolean();
    public ObservableField<Boolean> flDoorState = new ObservableField<>();
    public ObservableField<Boolean> frDoorState = new ObservableField<>();
    public ObservableField<Boolean> rlDoorState = new ObservableField<>();
    public ObservableField<Boolean> rrDoorState = new ObservableField<>();
    public ObservableField<Boolean> bDoorState = new ObservableField<>();
    public ObservableField<String> oilValue = new ObservableField<>();
    public ObservableField<Boolean> brakeValue = new ObservableField<>();
    public ObservableField<Float> tempValue = new ObservableField<>();
    public ObservableField<Boolean> seatBeltpValue = new ObservableField<>();
    public ObservableInt turnSpeed = new ObservableInt();
    public ObservableFloat turnSpeedAnge = new ObservableFloat();
    public ObservableInt speed = new ObservableInt();
    public ObservableField<String> speedUnit = new ObservableField<>("km/h");
    public ObservableInt airtemperature = new ObservableInt();
    public ObservableField<String> tempStr = new ObservableField<>();
    public ObservableInt tempUnit = new ObservableInt(0);
    public ObservableField<String> mileage = new ObservableField<>();
    public ObservableField<Integer> delay = new ObservableField<>();
    public ObservableField<Boolean> unitEnImg = new ObservableField<>();
    public ObservableInt speedWatch = new ObservableInt();
    public ObservableInt unit = new ObservableInt();
    public ObservableField<String> unitStr = new ObservableField<>();
    public ObservableInt alsmode = new ObservableInt();
    public ObservableInt sevenmode = new ObservableInt(5);
    public final int[] speedArray = {0, 50, 100, 150, 200, 250, 300, 330};
    public ObservableInt speedLevel = new ObservableInt(0);
    public final int[] rotateArray = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    public ObservableInt rotateLevel = new ObservableInt(0);
}
