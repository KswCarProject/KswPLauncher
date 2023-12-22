package com.wits.ksw.launcher.model;

import android.arch.lifecycle.MutableLiveData;
import android.database.ContentObserver;
import android.databinding.ObservableField;
import android.os.Handler;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;
import com.google.gson.Gson;
import com.wits.ksw.KswApplication;
import com.wits.ksw.launcher.bean.CarInfo;
import com.wits.ksw.launcher.utils.ExceptionPrint;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.McuStatus;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.math.BigDecimal;

/* loaded from: classes9.dex */
public class McuImpl {
    private static final String TAG = "KswApplication";
    private static volatile McuImpl singleton;
    private McuStatus.CarData carData;
    private int carDataPackHz;
    public CarInfo carInfo = new CarInfo();
    public MutableLiveData<CarInfo> carInfoMutableLiveData = new MutableLiveData<>();
    int fuelUnit;
    int tempUnit;

    private McuImpl() {
        this.tempUnit = 0;
        this.fuelUnit = 0;
        try {
            this.tempUnit = PowerManagerApp.getSettingsInt(KeyConfig.TempUnit);
            this.fuelUnit = PowerManagerApp.getSettingsInt(KeyConfig.FUEL_UNIT);
            Log.d("McuImpl () tempUnit", "tempUnit = " + this.tempUnit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        KswApplication.appContext.getContentResolver().registerContentObserver(Settings.System.getUriFor(KeyConfig.TempUnit), false, new ContentObserver(new Handler()) { // from class: com.wits.ksw.launcher.model.McuImpl.1
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange) {
                Log.d("KswApplication", " tempUnit change()");
                try {
                    McuImpl.this.tempUnit = PowerManagerApp.getSettingsInt(KeyConfig.TempUnit);
                    Log.d("KswApplication", " tempUnit " + McuImpl.this.tempUnit);
                    McuImpl mcuImpl = McuImpl.this;
                    mcuImpl.setTemperature(mcuImpl.carData);
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }
        });
        KswApplication.appContext.getContentResolver().registerContentObserver(Settings.System.getUriFor(KeyConfig.FUEL_UNIT), false, new ContentObserver(new Handler()) { // from class: com.wits.ksw.launcher.model.McuImpl.2
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange) {
                Log.d("KswApplication", " fuelUnit change");
                try {
                    McuImpl.this.fuelUnit = PowerManagerApp.getSettingsInt(KeyConfig.FUEL_UNIT);
                    Log.d("KswApplication", " fuelUnit " + McuImpl.this.fuelUnit);
                    McuImpl mcuImpl = McuImpl.this;
                    mcuImpl.setOilValue(mcuImpl.carData);
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    public static McuImpl getInstance() {
        if (singleton == null) {
            synchronized (McuImpl.class) {
                if (singleton == null) {
                    singleton = new McuImpl();
                }
            }
        }
        return singleton;
    }

    public void init() {
        speedWatch();
        setUint();
        try {
            this.carData = (McuStatus.CarData) new Gson().fromJson(PowerManagerApp.getStatusString("carData"), (Class<Object>) McuStatus.CarData.class);
            int carDoor = PowerManagerApp.getManager().getStatusInt("carDoor");
            Log.i("KswApplication", "onChange: carDoor=" + carDoor);
            McuStatus mcuStatus = new McuStatus();
            mcuStatus.carData = this.carData;
            mcuStatus.carData.carDoor = carDoor;
            setCarData(this.carData, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCarInfo(CarInfo carInfo) {
        this.carInfo = carInfo;
        this.carInfoMutableLiveData.postValue(carInfo);
    }

    public CarInfo getCarInfo() {
        return this.carInfo;
    }

    public void setCarData(McuStatus.CarData carData, int delay) {
        this.carDataPackHz = delay;
        this.carData = carData;
        setMileage(carData);
        setSpeed(carData);
        setTurnSpeed(carData);
        setSafetyBelt(carData);
        setOilValue(carData);
        setBrakeValue(carData);
        setDoor(carData);
        setTemperature(carData);
    }

    public void setMileage(McuStatus.CarData carDatam) {
        if (carDatam == null) {
            ExceptionPrint.print("setMileage: McuStatus.CarData is null");
            return;
        }
        int mileage = carDatam.getMileage();
        if (KswUtils.ismph()) {
            BigDecimal result = new BigDecimal(mileage * 0.621d);
            int reslut = result.intValue();
            this.carInfo.mileage.set(reslut + "mile");
            Log.i("KswApplication", "setMileage \u82f1\u5236\u7eed\u822a\uff1a" + reslut);
            return;
        }
        this.carInfo.mileage.set(mileage + "km");
        Log.i("KswApplication", "setMileage \u516c\u5236\u7eed\u822a\uff1a" + mileage);
    }

    public void setSpeed(McuStatus.CarData carDatam) {
        if (carDatam == null) {
            ExceptionPrint.print("setSpeed: McuStatus.CarData is null");
            return;
        }
        int speed = carDatam.getSpeed();
        Log.i("KswApplication", "onMcuMessageChange \u516c\u5236\u901f\u5ea6\uff1a" + speed);
        if (KswUtils.ismph()) {
            BigDecimal result = new BigDecimal(speed * 0.621d);
            int reslut = result.intValue();
            this.carInfo.speed.set(reslut);
            this.carInfo.unitEnImg.set(true);
            Log.i("KswApplication", "onMcuMessageChange \u82f1\u5236\u901f\u5ea6\uff1a" + reslut);
        } else {
            this.carInfo.speed.set(speed);
            this.carInfo.unitEnImg.set(false);
        }
        this.carInfo.speedLevel.set(getSpeedLevel(this.carInfo.speed.get()));
        this.carInfo.delay.set(Integer.valueOf(this.carDataPackHz));
        this.carInfoMutableLiveData.postValue(this.carInfo);
    }

    private int getSpeedLevel(int speed) {
        int[] array = this.carInfo.speedArray;
        for (int i = array.length - 1; i > 0; i--) {
            if (speed >= array[i]) {
                return i;
            }
        }
        return 0;
    }

    public void setTurnSpeed(McuStatus.CarData carDatam) {
        if (carDatam == null) {
            ExceptionPrint.print("setTurnSpeed: McuStatus.CarData is null");
            return;
        }
        int turnSpeed = carDatam.getEngineTurnS();
        this.carInfo.turnSpeed.set(turnSpeed);
        this.carInfo.rotateLevel.set(getRotateLevel(this.carInfo.turnSpeed.get()));
        Log.i("KswApplication", "setTurnSpeed:  \u8f6c\u901f\uff1a" + turnSpeed);
        BigDecimal result = new BigDecimal(turnSpeed).divide(new BigDecimal(100)).multiply(new BigDecimal(3));
        float angle = result.floatValue();
        Log.i("KswApplication", "setTurnSpeed:  \u8f6c\u901f\u65cb\u8f6c\u89d2\u5ea6" + angle);
        this.carInfo.turnSpeedAnge.set(angle);
        this.carInfoMutableLiveData.postValue(this.carInfo);
    }

    private int getRotateLevel(int speed) {
        int[] array = this.carInfo.rotateArray;
        for (int i = array.length - 1; i > 0; i--) {
            if (speed >= array[i] * 1000) {
                return i;
            }
        }
        return 0;
    }

    public void setUint() {
        int unit = 1;
        try {
            unit = PowerManagerApp.getSettingsInt(KeyConfig.DASHBOARDUNIT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("KswApplication", "setUint: " + unit);
        this.carInfo.unit.set(unit);
        this.carInfo.unitStr.set("rpm");
        this.carInfoMutableLiveData.postValue(this.carInfo);
    }

    public void setTempUnit() {
        try {
            int tunit = PowerManagerApp.getSettingsInt(KeyConfig.TempUnit);
            this.carInfo.tempUnit.set(tunit);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setSafetyBelt(McuStatus.CarData carDatam) {
        if (carDatam == null) {
            ExceptionPrint.print("setSafetyBelt: McuStatus.CarData is null");
            return;
        }
        boolean isSafetyBelt = carDatam.isSafetyBelt();
        this.carInfo.seatBeltpValue.set(Boolean.valueOf(isSafetyBelt));
        Log.i("KswApplication", "setSafetyBelt:  \u5b89\u5168\u5e26:" + isSafetyBelt);
        this.carInfoMutableLiveData.postValue(this.carInfo);
    }

    public void setWaterTemp(McuStatus.CarData carDatam) {
        if (carDatam == null) {
            ExceptionPrint.print("setWaterTemp: McuStatus.CarData is null");
            return;
        }
        float temperature = carDatam.getAirTemperature();
        this.carInfo.tempValue.set(Float.valueOf(temperature));
        Log.i("KswApplication", "setWaterTemp: \u6e29\u5ea6:" + temperature);
        this.carInfoMutableLiveData.postValue(this.carInfo);
    }

    public void setOilValue(McuStatus.CarData carDatam) {
        if (carDatam == null) {
            ExceptionPrint.print("setOilValue: McuStatus.CarData is null");
            return;
        }
        int oilSum = carDatam.getOilSum();
        if (this.fuelUnit == 0) {
            this.carInfo.oilValue.set(oilSum + "L");
        } else {
            this.carInfo.oilValue.set(l2Gal(oilSum, this.fuelUnit));
        }
        Log.i("KswApplication", "setOilValue: \u6cb9\u91cf:" + oilSum);
        this.carInfoMutableLiveData.postValue(this.carInfo);
    }

    private String l2Gal(int l, int type) {
        BigDecimal doubleStr;
        BigDecimal intStr = BigDecimal.valueOf(l);
        if (type == 1) {
            doubleStr = BigDecimal.valueOf(3.78541178d);
        } else {
            doubleStr = BigDecimal.valueOf(4.54609188d);
        }
        BigDecimal bd = intStr.divide(doubleStr, 1, 2);
        return bd.toString() + "gal";
    }

    public void setBrakeValue(McuStatus.CarData carDatam) {
        if (carDatam == null) {
            ExceptionPrint.print("setBrakeValue: McuStatus.CarData is null");
            return;
        }
        boolean isHandbrake = carDatam.isHandbrake();
        this.carInfo.brakeValue.set(Boolean.valueOf(isHandbrake));
        Log.i("KswApplication", "onMcuMessageChange: \u624b\u5239:" + isHandbrake);
        this.carInfoMutableLiveData.postValue(this.carInfo);
    }

    public void setTemperature(McuStatus.CarData carDatam) {
        if (carDatam == null) {
            ExceptionPrint.print("setTemperature: McuStatus.CarData is null");
            return;
        }
        float airTemperature = carDatam.getAirTemperature();
        Log.i("KswApplication", "setTemperature():  temperature=" + airTemperature);
        Log.i("KswApplication", "setTemperature(): tempUnit=" + this.tempUnit);
        if (this.tempUnit == 1) {
            int tempToF = KswUtils.tempToF(airTemperature);
            Log.i("KswApplication", "setTemperature: F " + tempToF);
            this.carInfo.tempStr.set(tempToF + "\u00b0F");
        } else {
            this.carInfo.tempStr.set(airTemperature + "\u2103");
        }
        this.carInfoMutableLiveData.postValue(this.carInfo);
    }

    public void speedWatch() {
        try {
            int Dashboard_MaxSpeed = PowerManagerApp.getSettingsInt(KeyConfig.DASH_MAX_SPEED);
            int unit = PowerManagerApp.getSettingsInt(KeyConfig.DASHBOARDUNIT);
            int carManufacturer = 0;
            try {
                carManufacturer = PowerManagerApp.getSettingsInt(KeyConfig.CarManufacturer);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            if (carManufacturer == 0 || carManufacturer > 3) {
                carManufacturer = UiThemeUtils.getCarManufacturer(KswApplication.appContext);
            }
            Log.d("KswApplication", "initCarManufacturer: " + carManufacturer);
            Log.i("KswApplication", "speedWatch: \u8f66\u901f\u8868\u9009\u9879=" + Dashboard_MaxSpeed + " \u5355\u4f4d\u9009\u9879=" + unit);
            if (Dashboard_MaxSpeed == 1 && unit == 1) {
                this.carInfo.speedWatch.set(0);
                Log.i("KswApplication", "speedWatch: \u8868\u76d8 160mph");
            } else if (Dashboard_MaxSpeed == 3 && unit == 1) {
                this.carInfo.speedWatch.set(1);
                Log.i("KswApplication", "speedWatch: \u8868\u76d8 180mph");
            } else if (Dashboard_MaxSpeed == 1 && unit == 0) {
                this.carInfo.speedWatch.set(2);
                Log.i("KswApplication", "speedWatch: \u8868\u76d8 280km/h");
            } else if (Dashboard_MaxSpeed == 3 && unit == 0) {
                this.carInfo.speedWatch.set(3);
                Log.i("KswApplication", "speedWatch: \u8868\u76d8 260km/h");
            } else if (Dashboard_MaxSpeed == 2 && unit == 0 && carManufacturer == 3) {
                this.carInfo.speedWatch.set(2);
                Log.i("KswApplication", "speedWatch: \u8868\u76d8 300km/h");
            } else {
                this.carInfo.speedWatch.set(1);
                Log.i("KswApplication", "speedWatch: \u7ec4\u5408\u4e0d\u5b58\u5728  \u9ed8\u8ba4\u8868\u76d8 260km/h");
            }
            Log.i("KswApplication", "speedWatch: Dashboard_MaxSpeed=" + Dashboard_MaxSpeed);
        } catch (Exception e2) {
            e2.printStackTrace();
            this.carInfo.speedWatch.set(1);
            Log.i("KswApplication", "speedWatch: Exception \u9ed8\u8ba4\u8868\u76d8 280km/h");
        }
        this.carInfoMutableLiveData.postValue(this.carInfo);
    }

    public void setDoor(McuStatus.CarData carDatam) {
        if (carDatam == null) {
            ExceptionPrint.print("setDoor: McuStatus.CarData is null");
            return;
        }
        boolean LEFT_AHEAD = carDatam.isOpen(16);
        boolean RIGHT_AHEAD = carDatam.isOpen(32);
        boolean LEFT_BACK = carDatam.isOpen(64);
        boolean RIGHT_BACK = carDatam.isOpen(128);
        boolean BACK_COVER = carDatam.isOpen(4);
        int doorCount = 0;
        try {
            doorCount = PowerManagerApp.getSettingsInt(KeyConfig.CAR_DOOR_NUM);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        int jsDoor = 0;
        try {
            jsDoor = PowerManagerApp.getSettingsInt(KeyConfig.CAR_DOOR_SELECT);
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
        boolean z = false;
        if (doorCount == 2) {
            this.carInfo.flDoorState.set(false);
            this.carInfo.frDoorState.set(false);
            this.carInfo.rlDoorState.set(false);
            this.carInfo.rrDoorState.set(false);
            this.carInfo.carImage.set(false);
            this.carInfo.bDoorState.set(false);
        } else if (jsDoor == 1) {
            this.carInfo.flDoorState.set(Boolean.valueOf(RIGHT_AHEAD));
            this.carInfo.frDoorState.set(Boolean.valueOf(LEFT_AHEAD));
            this.carInfo.rlDoorState.set(Boolean.valueOf(RIGHT_BACK && doorCount == 0));
            ObservableField<Boolean> observableField = this.carInfo.rrDoorState;
            if (LEFT_BACK && doorCount == 0) {
                z = true;
            }
            observableField.set(Boolean.valueOf(z));
            this.carInfo.bDoorState.set(Boolean.valueOf(BACK_COVER));
            if (!UiThemeUtils.isBMW_ID8_UI(KswApplication.appContext) && !UiThemeUtils.isUI_GS_ID8(KswApplication.appContext) && !UiThemeUtils.isUI_PEMP_ID8(KswApplication.appContext)) {
                this.carInfo.carImage.set(true);
            } else {
                setBmwId8DoorState();
            }
        } else {
            this.carInfo.flDoorState.set(Boolean.valueOf(LEFT_AHEAD));
            this.carInfo.frDoorState.set(Boolean.valueOf(RIGHT_AHEAD));
            this.carInfo.rlDoorState.set(Boolean.valueOf(LEFT_BACK && doorCount == 0));
            ObservableField<Boolean> observableField2 = this.carInfo.rrDoorState;
            if (RIGHT_BACK && doorCount == 0) {
                z = true;
            }
            observableField2.set(Boolean.valueOf(z));
            this.carInfo.bDoorState.set(Boolean.valueOf(BACK_COVER));
            if (!UiThemeUtils.isBMW_ID8_UI(KswApplication.appContext) && !UiThemeUtils.isUI_GS_ID8(KswApplication.appContext) && !UiThemeUtils.isUI_PEMP_ID8(KswApplication.appContext)) {
                this.carInfo.carImage.set(true);
            } else {
                setBmwId8DoorState();
            }
        }
        this.carInfoMutableLiveData.postValue(this.carInfo);
        Log.i("KswApplication", "setDoor: jsDoor=" + jsDoor + " doorCount=" + doorCount + " : \u9a7e\u9a76\u95e8:" + LEFT_AHEAD + " \u526f\u9a7e\u9a76\u95e8:" + RIGHT_AHEAD + " \u9a7e\u9a76\u5458\u540e\u95e8:" + LEFT_BACK + " \u526f\u9a7e\u9a76\u5458\u540e\u95e8:" + RIGHT_BACK + " \u540e\u5907\u7bb1\u95e8:" + BACK_COVER);
    }

    private void setBmwId8DoorState() {
        if (!this.carInfo.flDoorState.get().booleanValue() && !this.carInfo.frDoorState.get().booleanValue() && !this.carInfo.rlDoorState.get().booleanValue() && !this.carInfo.rrDoorState.get().booleanValue() && !this.carInfo.bDoorState.get().booleanValue()) {
            this.carInfo.carImage.set(false);
        } else {
            this.carInfo.carImage.set(true);
        }
    }
}
