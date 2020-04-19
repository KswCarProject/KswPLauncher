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
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.McuStatus;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.math.BigDecimal;

public class McuImpl {
    private static final String TAG = "KSWLauncher";
    private static volatile McuImpl singleton;
    /* access modifiers changed from: private */
    public McuStatus.CarData carData;
    public CarInfo carInfo = new CarInfo();
    public MutableLiveData<CarInfo> carInfoMutableLiveData = new MutableLiveData<>();

    private McuImpl() {
        KswApplication.appContext.getContentResolver().registerContentObserver(Settings.System.getUriFor(KeyConfig.TempUnit), false, new ContentObserver(new Handler()) {
            public void onChange(boolean selfChange) {
                McuImpl.this.setTemperature(McuImpl.this.carData);
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
            this.carData = (McuStatus.CarData) new Gson().fromJson(PowerManagerApp.getStatusString("carData"), McuStatus.CarData.class);
            int carDoor = PowerManagerApp.getManager().getStatusInt("carDoor");
            Log.i("KSWLauncher", "onChange: carDoor=" + carDoor);
            McuStatus mcuStatus = new McuStatus();
            mcuStatus.carData = this.carData;
            mcuStatus.carData.carDoor = carDoor;
            setCarData(this.carData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCarInfo(CarInfo carInfo2) {
        this.carInfo = carInfo2;
        this.carInfoMutableLiveData.postValue(carInfo2);
    }

    public CarInfo getCarInfo() {
        return this.carInfo;
    }

    public void setCarData(McuStatus.CarData carData2) {
        this.carData = carData2;
        setMileage(carData2);
        setSpeed(carData2);
        setTurnSpeed(carData2);
        setSafetyBelt(carData2);
        setOilValue(carData2);
        setBrakeValue(carData2);
        setDoor(carData2);
        setTemperature(carData2);
    }

    public void setMileage(McuStatus.CarData carDatam) {
        if (carDatam == null) {
            ExceptionPrint.print("setMileage: McuStatus.CarData is null");
            return;
        }
        int mileage = carDatam.getMileage();
        if (KswUtils.ismph()) {
            int reslut = new BigDecimal(((double) mileage) * 0.621d).intValue();
            ObservableField<String> observableField = this.carInfo.mileage;
            observableField.set(reslut + "mile");
            Log.i("KSWLauncher", "setMileage 英制续航：" + reslut);
            return;
        }
        ObservableField<String> observableField2 = this.carInfo.mileage;
        observableField2.set(mileage + "km");
        Log.i("KSWLauncher", "setMileage 公制续航：" + mileage);
    }

    public void setSpeed(McuStatus.CarData carDatam) {
        if (carDatam == null) {
            ExceptionPrint.print("setSpeed: McuStatus.CarData is null");
            return;
        }
        int speed = carDatam.getSpeed();
        Log.i("KSWLauncher", "onMcuMessageChange 公制速度：" + speed);
        if (KswUtils.ismph()) {
            int reslut = new BigDecimal(((double) speed) * 0.621d).intValue();
            this.carInfo.speed.set(reslut);
            Log.i("KSWLauncher", "onMcuMessageChange 英制速度：" + reslut);
        } else {
            this.carInfo.speed.set(speed);
        }
        this.carInfoMutableLiveData.postValue(this.carInfo);
    }

    public void setTurnSpeed(McuStatus.CarData carDatam) {
        if (carDatam == null) {
            ExceptionPrint.print("setTurnSpeed: McuStatus.CarData is null");
            return;
        }
        int turnSpeed = carDatam.getEngineTurnS();
        this.carInfo.turnSpeed.set(turnSpeed);
        Log.i("KSWLauncher", "setTurnSpeed:  转速：" + turnSpeed);
        float angle = new BigDecimal(turnSpeed).divide(new BigDecimal(100)).multiply(new BigDecimal(3)).floatValue();
        Log.i("KSWLauncher", "setTurnSpeed:  转速旋转角度" + angle);
        this.carInfo.turnSpeedAnge.set(angle);
        this.carInfoMutableLiveData.postValue(this.carInfo);
    }

    public void setUint() {
        int unit = 1;
        try {
            unit = PowerManagerApp.getSettingsInt(KeyConfig.DASHBOARDUNIT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("KSWLauncher", "setUint: " + unit);
        this.carInfo.unit.set(unit);
        this.carInfoMutableLiveData.postValue(this.carInfo);
    }

    public void setTempUnit() {
        try {
            this.carInfo.tempUnit.set(PowerManagerApp.getSettingsInt(KeyConfig.TempUnit));
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
        Log.i("KSWLauncher", "setSafetyBelt:  安全带:" + isSafetyBelt);
        this.carInfoMutableLiveData.postValue(this.carInfo);
    }

    public void setWaterTemp(McuStatus.CarData carDatam) {
        if (carDatam == null) {
            ExceptionPrint.print("setWaterTemp: McuStatus.CarData is null");
            return;
        }
        float temperature = carDatam.getAirTemperature();
        this.carInfo.tempValue.set(Float.valueOf(temperature));
        Log.i("KSWLauncher", "setWaterTemp: 温度:" + temperature);
        this.carInfoMutableLiveData.postValue(this.carInfo);
    }

    public void setOilValue(McuStatus.CarData carDatam) {
        if (carDatam == null) {
            ExceptionPrint.print("setOilValue: McuStatus.CarData is null");
            return;
        }
        int oilSum = carDatam.getOilSum();
        this.carInfo.oilValue.set(oilSum);
        Log.i("KSWLauncher", "setOilValue: 油量:" + oilSum);
        this.carInfoMutableLiveData.postValue(this.carInfo);
    }

    public void setBrakeValue(McuStatus.CarData carDatam) {
        if (carDatam == null) {
            ExceptionPrint.print("setBrakeValue: McuStatus.CarData is null");
            return;
        }
        boolean isHandbrake = carDatam.isHandbrake();
        this.carInfo.brakeValue.set(Boolean.valueOf(isHandbrake));
        Log.i("KSWLauncher", "onMcuMessageChange: 手刹:" + isHandbrake);
        this.carInfoMutableLiveData.postValue(this.carInfo);
    }

    public void setTemperature(McuStatus.CarData carDatam) {
        if (carDatam == null) {
            ExceptionPrint.print("setTemperature: McuStatus.CarData is null");
            return;
        }
        float airTemperature = carDatam.getAirTemperature();
        Log.i("KSWLauncher", "setTemperature:  temperature=" + airTemperature);
        int tempUnit = 0;
        try {
            tempUnit = PowerManagerApp.getSettingsInt(KeyConfig.TempUnit);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Log.i("KSWLauncher", "setTemperature: tempUnit=" + tempUnit);
        if (tempUnit == 1) {
            int tempToF = KswUtils.tempToF(airTemperature);
            Log.i("KSWLauncher", "setTemperature: F " + tempToF);
            ObservableField<String> observableField = this.carInfo.tempStr;
            observableField.set(tempToF + "°F");
        } else {
            ObservableField<String> observableField2 = this.carInfo.tempStr;
            observableField2.set(airTemperature + "℃");
        }
        this.carInfoMutableLiveData.postValue(this.carInfo);
    }

    public void speedWatch() {
        try {
            int Dashboard_MaxSpeed = PowerManagerApp.getSettingsInt(KeyConfig.DASH_MAX_SPEED);
            int unit = PowerManagerApp.getSettingsInt(KeyConfig.DASHBOARDUNIT);
            Log.i("KSWLauncher", "speedWatch: 车速表选项=" + Dashboard_MaxSpeed + " 单位选项=" + unit);
            if (Dashboard_MaxSpeed == 1 && unit == 1) {
                this.carInfo.speedWatch.set(0);
                Log.i("KSWLauncher", "speedWatch: 表盘 160mph");
            } else if (Dashboard_MaxSpeed == 3 && unit == 1) {
                this.carInfo.speedWatch.set(1);
                Log.i("KSWLauncher", "speedWatch: 表盘 180mph");
            } else if (Dashboard_MaxSpeed == 1 && unit == 0) {
                this.carInfo.speedWatch.set(2);
                Log.i("KSWLauncher", "speedWatch: 表盘 280km/h");
            } else if (Dashboard_MaxSpeed == 3 && unit == 0) {
                this.carInfo.speedWatch.set(3);
                Log.i("KSWLauncher", "speedWatch: 表盘 260km/h");
            } else {
                this.carInfo.speedWatch.set(1);
                Log.i("KSWLauncher", "speedWatch: 组合不存在  默认表盘 260km/h");
            }
            Log.i("KSWLauncher", "speedWatch: Dashboard_MaxSpeed=" + Dashboard_MaxSpeed);
        } catch (Exception e) {
            e.printStackTrace();
            this.carInfo.speedWatch.set(1);
            Log.i("KSWLauncher", "speedWatch: Exception 默认表盘 280km/h");
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
        boolean z = false;
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
        if (jsDoor == 1) {
            this.carInfo.flDoorState.set(Boolean.valueOf(RIGHT_AHEAD));
            this.carInfo.frDoorState.set(Boolean.valueOf(LEFT_AHEAD));
            this.carInfo.rlDoorState.set(Boolean.valueOf(RIGHT_BACK && doorCount == 0));
            ObservableField<Boolean> observableField = this.carInfo.rrDoorState;
            if (LEFT_BACK && doorCount == 0) {
                z = true;
            }
            observableField.set(Boolean.valueOf(z));
            this.carInfo.bDoorState.set(Boolean.valueOf(BACK_COVER));
            this.carInfo.carImage.set(true);
        } else if (jsDoor == 2) {
            this.carInfo.flDoorState.set(false);
            this.carInfo.frDoorState.set(false);
            this.carInfo.rlDoorState.set(false);
            this.carInfo.rrDoorState.set(false);
            this.carInfo.carImage.set(false);
            this.carInfo.bDoorState.set(false);
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
            this.carInfo.carImage.set(true);
        }
        this.carInfoMutableLiveData.postValue(this.carInfo);
        Log.i("KSWLauncher", "setDoor: jsDoor=" + jsDoor + " doorCount=" + doorCount + " : 驾驶门:" + LEFT_AHEAD + " 副驾驶门:" + RIGHT_AHEAD + " 驾驶员后门:" + LEFT_BACK + " 副驾驶员后门:" + RIGHT_BACK + " 后备箱门:" + BACK_COVER);
    }
}
