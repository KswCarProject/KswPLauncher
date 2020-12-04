package com.wits.pms.statuscontrol;

import com.google.gson.Gson;
import com.wits.pms.statuscontrol.WitsCommand;
import java.util.ArrayList;
import java.util.List;

public class McuStatus {
    public static final int ANDROID_MODE = 1;
    public static final int CAR_MODE = 2;
    public static final int TYPE_MCU_STATUS = 5;
    public ACData acData = new ACData();
    public BenzData benzData = new BenzData();
    public CarData carData = new CarData();
    public String mcuVerison;
    public int systemMode;

    public static final class MediaType {
        public static final int SRC_ALL_APP = 13;
        public static final int SRC_AUX = 6;
        public static final int SRC_BT = 3;
        public static final int SRC_BT_MUSIC = 4;
        public static final int SRC_CAR = 0;
        public static final int SRC_DTV = 9;
        public static final int SRC_DVD = 8;
        public static final int SRC_DVD_YUV = 12;
        public static final int SRC_DVR = 5;
        public static final int SRC_IPOD = 11;
        public static final int SRC_MUSIC = 1;
        public static final int SRC_PHONELINK = 7;
        public static final int SRC_RADIO = 10;
        public static final int SRC_VIDEO = 2;
    }

    public McuStatus() {
    }

    public McuStatus(int systemMode2, String mcuVerison2) {
        this.systemMode = systemMode2;
        this.mcuVerison = mcuVerison2;
    }

    public static McuStatus getStatusFromJson(String jsonArg) {
        return (McuStatus) new Gson().fromJson(jsonArg, McuStatus.class);
    }

    public List<String> compare(McuStatus mcuStatus) {
        List<String> keys = new ArrayList<>();
        if (this.systemMode != mcuStatus.systemMode) {
            keys.add("systemMode");
        }
        if (this.carData.safetyBelt != mcuStatus.carData.safetyBelt) {
            keys.add("safetyBelt");
        }
        if (this.carData.handbrake != mcuStatus.carData.handbrake) {
            keys.add("handbrake");
        }
        if (this.carData.oilUnitType != mcuStatus.carData.oilUnitType) {
            keys.add("oilUnitType");
        }
        if (this.carData.temperatureUnitType != mcuStatus.carData.temperatureUnitType) {
            keys.add("temperatureUnitType");
        }
        if (this.carData.distanceUnitType != mcuStatus.carData.distanceUnitType) {
            keys.add("distanceUnitType");
        }
        if (this.carData.airTemperature != mcuStatus.carData.airTemperature) {
            keys.add("airTemperature");
        }
        if (this.carData.oilSum != mcuStatus.carData.oilSum) {
            keys.add("oilSum");
        }
        if (this.carData.engineTurnS != mcuStatus.carData.engineTurnS) {
            keys.add("engineTurnS");
        }
        if (this.carData.speed != mcuStatus.carData.speed) {
            keys.add("speed");
        }
        if (this.carData.averSpeed != mcuStatus.carData.averSpeed) {
            keys.add("averSpeed");
        }
        if (this.carData.oilWear != mcuStatus.carData.oilWear) {
            keys.add("oilWear");
        }
        if (this.carData.mileage != mcuStatus.carData.mileage) {
            keys.add("mileage");
        }
        if (this.carData.carDoor != mcuStatus.carData.carDoor) {
            keys.add("carDoor");
        }
        if (!this.acData.getJson().equals(mcuStatus.acData.getJson())) {
            keys.add("acData");
        }
        return keys;
    }

    public static class AirControl {

        public enum AcKeyType {
            dual,
            backMistSwitch,
            frontMistSwitch,
            loop,
            AC_Switch,
            isOpen,
            NULL2,
            NULL1,
            speedAdd,
            speedReduce,
            windDirect,
            leftTmpAdd,
            leftTmpReduce,
            rightTmpAdd,
            rightTmpReduce,
            autoSwitch
        }

        public static void pressKey(AcKeyType type) {
            byte data1 = 0;
            byte data2 = 0;
            int index = type.ordinal();
            if (index >= 8) {
                data2 = (byte) ((1 << (index - 8)) + 0);
            } else {
                data1 = (byte) ((1 << index) + 0);
            }
            WitsCommand.sendCommand(1, WitsCommand.SystemCommand.AIRCON_CONTROL, data1 + "," + data2);
        }
    }

    public static class ACData {
        public static final int LEFT_ABOVE = 128;
        public static final int LEFT_AUTO = 16;
        public static final int LEFT_BELOW = 32;
        public static final int LEFT_FRONT = 64;
        public static final int RIGHT_ABOVE = 8;
        public static final int RIGHT_AUTO = 1;
        public static final int RIGHT_BELOW = 2;
        public static final int RIGHT_FRONT = 4;
        public static int i = 0;
        public boolean AC_Switch;
        public boolean autoSwitch;
        public boolean backMistSwitch;
        public boolean eco;
        public boolean frontMistSwitch;
        public boolean isOpen;
        public float leftTmp;
        public int loop;
        public int mode;
        public float rightTmp;
        public float speed;
        public boolean sync;

        public boolean isOpen() {
            return this.isOpen;
        }

        public void setOpen(boolean open) {
            this.isOpen = open;
        }

        public boolean isAC_Switch() {
            return this.AC_Switch;
        }

        public void setAC_Switch(boolean AC_Switch2) {
            this.AC_Switch = AC_Switch2;
        }

        public int getLoop() {
            return this.loop;
        }

        public void setLoop(int loop2) {
            this.loop = loop2;
        }

        public boolean isFrontMistSwitch() {
            return this.frontMistSwitch;
        }

        public void setFrontMistSwitch(boolean frontMistSwitch2) {
            this.frontMistSwitch = frontMistSwitch2;
        }

        public boolean isBackMistSwitch() {
            return this.backMistSwitch;
        }

        public void setBackMistSwitch(boolean backMistSwitch2) {
            this.backMistSwitch = backMistSwitch2;
        }

        public boolean isSync() {
            return this.sync;
        }

        public void setSync(boolean sync2) {
            this.sync = sync2;
        }

        public int getMode() {
            return this.mode;
        }

        public void setMode(int mode2) {
            this.mode = mode2;
        }

        public float getLeftTmp() {
            return this.leftTmp;
        }

        public void setLeftTmp(float leftTmp2) {
            this.leftTmp = leftTmp2;
        }

        public float getRightTmp() {
            return this.rightTmp;
        }

        public void setRightTmp(float rightTmp2) {
            this.rightTmp = rightTmp2;
        }

        public boolean isEco() {
            return this.eco;
        }

        public void setEco(boolean eco2) {
            this.eco = eco2;
        }

        public boolean isAutoSwitch() {
            return this.autoSwitch;
        }

        public void setAutoSwitch(boolean autoSwitch2) {
            this.autoSwitch = autoSwitch2;
        }

        public float getSpeed() {
            return this.speed;
        }

        public void setSpeed(float speed2) {
            this.speed = speed2;
        }

        public boolean isOpen(int type) {
            return (this.mode & type) != 0;
        }

        public static String getModeBinaryString(int mode2) {
            StringBuilder sb = new StringBuilder();
            sb.append("");
            int i2 = 0;
            sb.append((mode2 & 128) != 0 ? 1 : 0);
            String binaryString = sb.toString();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(binaryString);
            sb2.append((mode2 & 64) != 0 ? 1 : 0);
            String binaryString2 = sb2.toString();
            StringBuilder sb3 = new StringBuilder();
            sb3.append(binaryString2);
            sb3.append((mode2 & 32) != 0 ? 1 : 0);
            String binaryString3 = sb3.toString();
            StringBuilder sb4 = new StringBuilder();
            sb4.append(binaryString3);
            sb4.append((mode2 & 16) != 0 ? 1 : 0);
            String binaryString4 = sb4.toString();
            StringBuilder sb5 = new StringBuilder();
            sb5.append(binaryString4);
            sb5.append((mode2 & 8) != 0 ? 1 : 0);
            String binaryString5 = sb5.toString();
            StringBuilder sb6 = new StringBuilder();
            sb6.append(binaryString5);
            sb6.append((mode2 & 4) != 0 ? 1 : 0);
            String binaryString6 = sb6.toString();
            StringBuilder sb7 = new StringBuilder();
            sb7.append(binaryString6);
            sb7.append((mode2 & 2) != 0 ? 1 : 0);
            String binaryString7 = sb7.toString();
            StringBuilder sb8 = new StringBuilder();
            sb8.append(binaryString7);
            if ((mode2 & 1) != 0) {
                i2 = 1;
            }
            sb8.append(i2);
            return sb8.toString();
        }

        public static ACData getStatusFromJson(String jsonArg) {
            return (ACData) new Gson().fromJson(jsonArg, ACData.class);
        }

        public String getJson() {
            return new Gson().toJson((Object) this);
        }
    }

    public static class MediaData {
        public static final int TYPE_AUX = 48;
        public static final int TYPE_DISC = 16;
        public static final int TYPE_DVD = 33;
        public static final int TYPE_FM = 1;
        public static final int TYPE_MODE = 64;
        public static final int TYPE_USB = 17;
        public Disc disc;
        public DVD dvd;
        public Fm fm;
        public MODE mode;
        public int type;
        public Usb usb;

        public static class DVD {
            public int chapterNumber;
            public int min;
            public int sec;
            public int totalChapter;
        }

        public static class Disc {
            public int min;
            public int number;
            public int sec;
            public int track;
        }

        public static class Fm {
            public String freq;
            public String name;
            public int preFreq;
        }

        public static class MODE {
            public boolean ASL;
            public boolean PAUSE;
            public boolean RAND;
            public boolean RPT;
            public boolean SCAN;
            public boolean ST;
        }

        public static class Usb {
            public int fileNumber;
            public int folderNumber;
            public int min;
            public int sec;
        }

        public static MediaData parseDataFromJson(String json) {
            return (MediaData) new Gson().fromJson(json, MediaData.class);
        }
    }

    public static class BenzData {
        public static final int AIR_MATIC_STATUS = 2;
        public static final int AUXILIARY_RADAR = 3;
        public static final int HIGH_CHASSIS_SWITCH = 1;
        public boolean airBagSystem;
        public int airMaticStatus;
        public boolean auxiliaryRadar;
        public boolean highChassisSwitch;
        public int key3;
        public int light1 = 0;
        public int light2 = 0;
        public int pressButton;

        public boolean isHighChassisSwitch() {
            return this.highChassisSwitch;
        }

        public void setHighChassisSwitch(boolean highChassisSwitch2) {
            this.highChassisSwitch = highChassisSwitch2;
        }

        public int getAirMaticStatus() {
            return this.airMaticStatus;
        }

        public void setAirMaticStatus(int airMaticStatus2) {
            this.airMaticStatus = airMaticStatus2;
        }

        public boolean isAuxiliaryRadar() {
            return this.auxiliaryRadar;
        }

        public void setAuxiliaryRadar(boolean auxiliaryRadar2) {
            this.auxiliaryRadar = auxiliaryRadar2;
        }

        public int getLight1() {
            return this.light1;
        }

        public void setLight1(int light12) {
            this.light1 = light12;
        }

        public int getLight2() {
            return this.light2;
        }

        public void setLight2(int light22) {
            this.light2 = light22;
        }

        public int getKey3() {
            return this.key3;
        }

        public void setKey3(int key32) {
            this.key3 = key32;
        }

        public boolean isAirBagSystem() {
            return this.airBagSystem;
        }

        public void setAirBagSystem(boolean airBagSystem2) {
            this.airBagSystem = airBagSystem2;
        }

        public int getPressButton() {
            return this.pressButton;
        }

        public void setPressButton(int pressButton2) {
            this.pressButton = pressButton2;
        }

        public void pressButton(int which) {
            this.pressButton = which;
            WitsCommand.sendCommand(1, WitsCommand.SystemCommand.BENZ_CONTROL, getJson());
            this.pressButton = 0;
        }

        public static BenzData getStatusFromJson(String jsonArg) {
            return (BenzData) new Gson().fromJson(jsonArg, BenzData.class);
        }

        public String getJson() {
            return new Gson().toJson((Object) this);
        }
    }

    public class CarData {
        public static final int AHEAD_COVER = 8;
        public static final int BACK_COVER = 4;
        public static final int LEFT_AHEAD = 16;
        public static final int LEFT_BACK = 64;
        public static final int RIGHT_AHEAD = 32;
        public static final int RIGHT_BACK = 128;
        public float airTemperature;
        public float averSpeed;
        public int carDoor;
        public int distanceUnitType;
        public int engineTurnS;
        public boolean handbrake;
        public int mileage;
        public int oilSum;
        public int oilUnitType;
        public float oilWear;
        public boolean safetyBelt;
        public int speed;
        public int temperatureUnitType;

        public CarData() {
        }

        public boolean isOpen(int type) {
            return (this.carDoor & type) != 0;
        }

        public int getCarDoor() {
            return this.carDoor;
        }

        public void setCarDoor(int carDoor2) {
            this.carDoor = carDoor2;
        }

        public boolean isHandbrake() {
            return this.handbrake;
        }

        public void setHandbrake(boolean handbrake2) {
            this.handbrake = handbrake2;
        }

        public boolean isSafetyBelt() {
            return this.safetyBelt;
        }

        public void setSafetyBelt(boolean safetyBelt2) {
            this.safetyBelt = safetyBelt2;
        }

        public int getMileage() {
            return this.mileage;
        }

        public void setMileage(int mileage2) {
            this.mileage = mileage2;
        }

        public float getOilWear() {
            return this.oilWear;
        }

        public void setOilWear(float oilWear2) {
            this.oilWear = oilWear2;
        }

        public int getOilSum() {
            return this.oilSum;
        }

        public void setOilSum(int oilSum2) {
            this.oilSum = oilSum2;
        }

        public float getAverSpeed() {
            return this.averSpeed;
        }

        public void setAverSpeed(float averSpeed2) {
            this.averSpeed = averSpeed2;
        }

        public int getSpeed() {
            return this.speed;
        }

        public void setSpeed(int speed2) {
            this.speed = speed2;
        }

        public int getEngineTurnS() {
            return this.engineTurnS;
        }

        public void setEngineTurnS(int engineTurnS2) {
            this.engineTurnS = engineTurnS2;
        }

        public float getAirTemperature() {
            return this.airTemperature;
        }

        public void setAirTemperature(float airTemperature2) {
            this.airTemperature = airTemperature2;
        }

        public int getDistanceUnitType() {
            return this.distanceUnitType;
        }

        public void setDistanceUnitType(int distanceUnitType2) {
            this.distanceUnitType = distanceUnitType2;
        }

        public int getTemperatureUnitType() {
            return this.temperatureUnitType;
        }

        public void setTemperatureUnitType(int temperatureUnitType2) {
            this.temperatureUnitType = temperatureUnitType2;
        }

        public int getOilUnitType() {
            return this.oilUnitType;
        }

        public void setOilUnitType(int oilUnitType2) {
            this.oilUnitType = oilUnitType2;
        }
    }

    public int getSystemMode() {
        return this.systemMode;
    }

    public void setSystemMode(int systemMode2) {
        this.systemMode = systemMode2;
    }

    public String getMcuVerison() {
        return this.mcuVerison;
    }

    public void setMcuVerison(String mcuVerison2) {
        this.mcuVerison = mcuVerison2;
    }

    public CarData getCarData() {
        return this.carData;
    }

    public void setCarData(CarData carData2) {
        this.carData = carData2;
    }

    public ACData getAcData() {
        return this.acData;
    }

    public void setAcData(ACData acData2) {
        this.acData = acData2;
    }
}
