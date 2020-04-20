package com.wits.ksw.launcher.bean;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableFloat;
import androidx.databinding.ObservableInt;

public class CarInfo {
    public ObservableInt airtemperature = new ObservableInt();
    public ObservableInt alsmode = new ObservableInt();
    public ObservableField<Boolean> bDoorState = new ObservableField<>();
    public ObservableField<Boolean> brakeValue = new ObservableField<>();
    public ObservableBoolean carImage = new ObservableBoolean();
    public ObservableField<Boolean> flDoorState = new ObservableField<>();
    public ObservableField<Boolean> frDoorState = new ObservableField<>();
    public ObservableField<String> mileage = new ObservableField<>();
    public ObservableInt oilValue = new ObservableInt();
    public ObservableField<Boolean> rlDoorState = new ObservableField<>();
    public ObservableField<Boolean> rrDoorState = new ObservableField<>();
    public ObservableField<Boolean> seatBeltpValue = new ObservableField<>();
    public ObservableInt sevenmode = new ObservableInt(5);
    public ObservableInt speed = new ObservableInt();
    public ObservableInt speedWatch = new ObservableInt();
    public ObservableField<String> tempStr = new ObservableField<>();
    public ObservableInt tempUnit = new ObservableInt(0);
    public ObservableField<Float> tempValue = new ObservableField<>();
    public ObservableInt turnSpeed = new ObservableInt();
    public ObservableFloat turnSpeedAnge = new ObservableFloat();
    public ObservableInt unit = new ObservableInt();
}
