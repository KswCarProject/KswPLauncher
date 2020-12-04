package com.wits.ksw.launcher.model;

import android.animation.ObjectAnimator;
import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.os.RemoteException;
import android.util.Log;
import android.widget.ImageView;
import com.wits.ksw.KswApplication;
import com.wits.ksw.launcher.utils.ClientManager;
import com.wits.ksw.launcher.utils.KswUtils;
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
        Log.i(KswApplication.TAG, "isSevenModel:  DashboardSelect=" + DashboardSelect);
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
        Log.i(KswApplication.TAG, "initBmwid7UiView: client=" + ClientManager.getInstance().getClient() + " DashboardSelect=" + DashboardSelect);
        return ClientManager.getInstance().isAls6208Client() && DashboardSelect == 1;
    }

    public boolean isLCModel() {
        int DashboardSelect = ClientManager.getInstance().getDashboardSelect();
        Log.i(KswApplication.TAG, "isLCModel client=" + ClientManager.getInstance().getClient() + " DashboardSelect=" + DashboardSelect);
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

    @BindingAdapter({"setBmwTyTurnSpeedRotation"})
    public static void setBmwTyTurnSpeedRotation(ImageView imageView, int turnSpeed) {
        float angle = new BigDecimal(turnSpeed).divide(new BigDecimal(100)).multiply(new BigDecimal(3.3d)).floatValue();
        Log.i(KswApplication.TAG, "setTurnSpeed:  转速旋转角度" + angle);
        setSpeedRotationBet(imageView, angle);
    }

    @BindingAdapter({"setTurnSpeedRotation"})
    public static void setRotation(ImageView imageView, float rota) {
        setSpeedRotationBet(imageView, rota);
    }

    @BindingAdapter({"setSpeedRotation"})
    public static void setSpeedRotation(ImageView imageView, int rota) {
        setSpeedRotationBet(imageView, (float) rota);
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

    @BindingAdapter({"setSevenSpeedRotation"})
    public static void setSevenSpeedRotation(ImageView imageView, int speed) {
        Log.i(KswApplication.TAG, "setSevenSpeedRotation: rota=" + speed);
        if (KswUtils.ismph()) {
            float angle = new BigDecimal(((double) speed) * 1.2882447665056362d).floatValue();
            Log.i(KswApplication.TAG, "setSevenSpeedRotation: " + angle);
            setSpeedRotationBet(imageView, angle);
            return;
        }
        BigDecimal result = new BigDecimal(((double) speed) * 0.79d);
        float angle2 = result.floatValue();
        Log.i(KswApplication.TAG, "setSevenSpeedRotation: " + result);
        setSpeedRotationBet(imageView, angle2);
    }

    @BindingAdapter({"setALSSpeedRotation"})
    public static void setALSSpeedRotation(ImageView imageView, int rota) {
        Log.i(KswApplication.TAG, "setALSSpeedRotation: ");
        setSpeedRotationBet(imageView, (float) (((double) rota) * 0.79d));
    }

    @BindingAdapter({"setALSTurnSpeedRotation"})
    public static void setALSRotation(ImageView imageView, float rota) {
        setSpeedRotationBet(imageView, -rota);
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        super.onCleared();
        Log.i(KswApplication.TAG, "onCleared: --------DashboardViewModel---------");
    }
}
