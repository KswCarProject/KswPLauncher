package com.wits.ksw.launcher.model;

import android.animation.ObjectAnimator;
import android.databinding.ObservableBoolean;
import android.os.RemoteException;
import android.util.Log;
import android.widget.ImageView;
import com.wits.ksw.KswApplication;
import com.wits.ksw.launcher.utils.ClientManager;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.math.BigDecimal;
import java.util.HashMap;

public class DashboardViewModel extends LauncherViewModel {
    private static HashMap<Integer, ObjectAnimator> animatorMaps = new HashMap<>();
    private static final int time = 150;
    public ObservableBoolean hideOil = new ObservableBoolean(false);
    public ObservableBoolean showAls = new ObservableBoolean();
    public ObservableBoolean showSevenMenu = new ObservableBoolean(false);

    public void onSevenModeClick(int mode) {
        carInfo.sevenmode.set(mode);
        this.showSevenMenu.set(false);
        try {
            PowerManagerApp.setSettingsInt("SevenModel", mode);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isSevenModel() {
        int DashboardSelect = ClientManager.getInstance().getDashboardSelect();
        Log.i(TAG, "isSevenModel:  DashboardSelect=" + DashboardSelect);
        return DashboardSelect == 2;
    }

    public int getSevenModel() {
        try {
            return PowerManagerApp.getSettingsInt("SevenModel");
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean isAlsModel() {
        int DashboardSelect = ClientManager.getInstance().getDashboardSelect();
        Log.i(TAG, "initBmwid7UiView: client=" + ClientManager.getInstance().getClient() + " DashboardSelect=" + DashboardSelect);
        return ClientManager.getInstance().isAls6208Client() && DashboardSelect == 1;
    }

    public boolean isLCModel() {
        int DashboardSelect = ClientManager.getInstance().getDashboardSelect();
        Log.i(TAG, "isLCModel client=" + ClientManager.getInstance().getClient() + " DashboardSelect=" + DashboardSelect);
        return ClientManager.getInstance().isLC3208Client() && DashboardSelect == 3;
    }

    public int getAlsModel() {
        try {
            return PowerManagerApp.getSettingsInt("AlsModel");
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setAlsModel(int value) {
        carInfo.alsmode.set(value);
        try {
            PowerManagerApp.setSettingsInt("AlsModel", value);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void setBmwTyTurnSpeedRotation(ImageView imageView, int turnSpeed) {
        float angle = new BigDecimal(turnSpeed).divide(new BigDecimal(100)).multiply(new BigDecimal(3.3d)).floatValue();
        Log.i(TAG, "setTurnSpeed:  转速旋转角度" + angle);
        setSpeedRotationBet(imageView, angle);
    }

    public static void setRotation(ImageView imageView, float rota) {
        setSpeedRotationBet(imageView, rota);
    }

    public static void setAudiMib3TurnSpeedRotation(ImageView imageView, int turnSpeed) {
        float angle = new BigDecimal(turnSpeed).divide(new BigDecimal(100)).multiply(new BigDecimal(3.3d)).floatValue();
        Log.i(TAG, "setTurnSpeed:  转速旋转角度" + angle);
        setSpeedRotationBet(imageView, angle);
    }

    public static void setSpeedRotation(ImageView imageView, int rota) {
        if (KswUtils.ismph()) {
            setSpeedRotationBet(imageView, new BigDecimal((((double) rota) / 0.621d) * 0.968d).floatValue());
        } else {
            setSpeedRotationBet(imageView, (float) rota);
        }
    }

    public static void setAudiMib3SeepRotation(ImageView imageView, int rota) {
        float rotaa;
        double multiple;
        Log.d(TAG, "setAudiMib3SeepRotation: rota=" + rota);
        Log.d(TAG, "setAudiMib3SeepRotation: carInfo.speedWatch=" + carInfo.speedWatch.get());
        if (KswUtils.ismph()) {
            if (carInfo.speedWatch.get() == 0) {
                multiple = 1.021d;
            } else {
                multiple = 0.91d;
            }
            float angle = new BigDecimal(((double) rota) * (multiple / 0.621d)).floatValue();
            Log.i(TAG, "setSevenSpeedRotation: " + angle);
            setSpeedRotationBet(imageView, angle);
            return;
        }
        if (carInfo.speedWatch.get() == 3) {
            rotaa = (float) (((double) rota) * 0.94d);
        } else if (carInfo.speed.get() < time) {
            rotaa = (float) (((double) rota) * 0.87d);
        } else {
            rotaa = (float) (((double) rota) * 0.88d);
        }
        setSpeedRotationBet(imageView, rotaa);
    }

    public static void setSpeedRotationBet(ImageView imageView, float rota) {
        int delay = 0;
        try {
            delay = McuImpl.getInstance().carInfo.delay.get().intValue();
        } catch (Exception e) {
        }
        ObjectAnimator objectAnimator = animatorMaps.get(Integer.valueOf(imageView.getId()));
        if (objectAnimator == null) {
            objectAnimator = ObjectAnimator.ofFloat(imageView, "rotation", new float[]{rota});
            animatorMaps.put(Integer.valueOf(imageView.getId()), objectAnimator);
        }
        int duration = time;
        if (delay != 0 && delay <= time) {
            duration = delay;
        }
        Log.i("lkt", "time delay - " + duration);
        imageView.setRotation(rota);
        if (duration <= 55) {
            if (objectAnimator.isStarted()) {
                objectAnimator.cancel();
            }
            imageView.setRotation(rota);
            return;
        }
        objectAnimator.setDuration((long) delay);
        objectAnimator.setFloatValues(new float[]{rota});
        objectAnimator.start();
    }

    public static void setSevenSpeedRotation(ImageView imageView, int speed) {
        Log.i(TAG, "setSevenSpeedRotation: rota=" + speed);
        if (KswUtils.ismph()) {
            float angle = new BigDecimal(((double) speed) * 1.2882447665056362d).floatValue();
            Log.i(TAG, "setSevenSpeedRotation: " + angle);
            setSpeedRotationBet(imageView, angle);
            return;
        }
        BigDecimal result = new BigDecimal(((double) speed) * 0.79d);
        float angle2 = result.floatValue();
        Log.i(TAG, "setSevenSpeedRotation: " + result);
        setSpeedRotationBet(imageView, angle2);
    }

    public static void setALSSpeedRotation(ImageView imageView, int rota) {
        float rotaa;
        Log.i(TAG, "setALSSpeedRotation: ");
        if (carInfo.unit.get() == 1) {
            rotaa = (float) (((double) rota) * 1.335d);
        } else {
            rotaa = (float) (((double) rota) * 0.79d);
        }
        setSpeedRotationBet(imageView, rotaa);
    }

    public static void setALSRotation(ImageView imageView, float rota) {
        double mul;
        Log.d(TAG, "setALSRotation: rota=" + rota);
        if (!UiThemeUtils.isALS_ID7_UI(KswApplication.appContext) || carInfo.turnSpeed.get() > 8000) {
            setSpeedRotationBet(imageView, -rota);
            return;
        }
        if (carInfo.turnSpeed.get() <= 2000) {
            mul = 30.2d;
        } else if (carInfo.turnSpeed.get() > 2000 && carInfo.turnSpeed.get() <= 3000) {
            mul = 31.1d;
        } else if (carInfo.turnSpeed.get() > 3000 && carInfo.turnSpeed.get() < 4000) {
            mul = 30.9d;
        } else if (carInfo.turnSpeed.get() >= 4000 && carInfo.turnSpeed.get() < 5000) {
            mul = 30.8d;
        } else if (carInfo.turnSpeed.get() == 5000) {
            mul = 30.6d;
        } else if (carInfo.turnSpeed.get() > 5000 && carInfo.turnSpeed.get() <= 6000) {
            mul = 30.5d;
        } else if (carInfo.turnSpeed.get() > 6000 && carInfo.turnSpeed.get() < 7000) {
            mul = 30.45d;
        } else if (carInfo.turnSpeed.get() < 7000 || carInfo.turnSpeed.get() >= 8000) {
            mul = 30.35d;
        } else {
            mul = 30.4d;
        }
        float angle = new BigDecimal(carInfo.turnSpeed.get()).divide(new BigDecimal(1000)).multiply(new BigDecimal(mul)).floatValue();
        Log.i(TAG, "setALSRotation:  转速旋转角度为=" + angle);
        setSpeedRotationBet(imageView, -angle);
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        super.onCleared();
        Log.i(TAG, "onCleared: --------DashboardViewModel---------");
    }
}
