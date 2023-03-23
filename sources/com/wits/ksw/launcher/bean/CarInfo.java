package com.wits.ksw.launcher.bean;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.databinding.ObservableInt;

public class CarInfo {
    public ObservableInt airtemperature = new ObservableInt();
    public ObservableInt alsmode = new ObservableInt();
    public ObservableField<Boolean> bDoorState = new ObservableField<>();
    public ObservableField<Boolean> brakeValue = new ObservableField<>();
    public ObservableBoolean carImage = new ObservableBoolean();
    public ObservableField<Integer> delay = new ObservableField<>();
    public ObservableField<Boolean> flDoorState = new ObservableField<>();
    public ObservableField<Boolean> frDoorState = new ObservableField<>();
    public ObservableField<String> mileage = new ObservableField<>();
    public ObservableField<String> oilValue = new ObservableField<>();
    public ObservableField<Boolean> rlDoorState = new ObservableField<>();
    public final int[] rotateArray = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    public ObservableInt rotateLevel = new ObservableInt(0);
    public ObservableField<Boolean> rrDoorState = new ObservableField<>();
    public ObservableField<Boolean> seatBeltpValue = new ObservableField<>();
    public ObservableInt sevenmode = new ObservableInt(5);
    public ObservableInt speed = new ObservableInt();
    public final int[] speedArray = {0, 50, 100, 150, 200, 250, 300, 330};
    public ObservableInt speedLevel = new ObservableInt(0);
    public ObservableField<String> speedUnit = new ObservableField<>("km/h");
    public ObservableInt speedWatch = new ObservableInt();
    public ObservableField<String> tempStr = new ObservableField<>();
    public ObservableInt tempUnit = new ObservableInt(0);
    public ObservableField<Float> tempValue = new ObservableField<>();
    public ObservableInt turnSpeed = new ObservableInt();
    public ObservableFloat turnSpeedAnge = new ObservableFloat();
    public ObservableInt unit = new ObservableInt();
    public ObservableField<Boolean> unitEnImg = new ObservableField<>();
    public ObservableField<String> unitStr = new ObservableField<>();
}
