package com.wits.ksw.launcher.land_rover.model;

import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.wits.ksw.BuildConfig;
import com.wits.ksw.MainActivity;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.pms.statuscontrol.McuStatus;
import com.wits.pms.statuscontrol.WitsCommand;

public class LandroverViewModel extends LauncherViewModel {
    public View.OnFocusChangeListener btViewFocusChangeListener = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                Log.i(LandroverViewModel.TAG, "onFocusChange: settingsViewFocusChangeListener");
                if (MainActivity.mainActivity != null) {
                    MainActivity.mainActivity.setCurrentItem(1);
                }
            }
        }
    };
    public View.OnFocusChangeListener settingsViewFocusChangeListener = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                Log.i(LandroverViewModel.TAG, "onFocusChange: settingsViewFocusChangeListener");
                if (MainActivity.mainActivity != null) {
                    MainActivity.mainActivity.setCurrentItem(0);
                }
            }
        }
    };

    public void backKeyClick() {
        onSendCommand(1, 115);
    }

    public void homeKeyClick() {
        onSendCommand(1, 114);
    }

    public void setupkeyClick() {
        WitsCommand.sendMcuCommand(new McuStatus.KswMcuMsg(120, 3, 0));
    }

    public void dvrClick() {
        WitsCommand.sendMcuCommand(new McuStatus.KswMcuMsg(120, 4, 0));
    }

    public void gpsClick() {
        WitsCommand.sendMcuCommand(new McuStatus.KswMcuMsg(120, 5, 0));
    }

    public void btClick() {
        WitsCommand.sendMcuCommand(new McuStatus.KswMcuMsg(120, 6, 0));
    }

    public void videoClick() {
        WitsCommand.sendMcuCommand(new McuStatus.KswMcuMsg(120, 7, 0));
    }

    public void airClick() {
        WitsCommand.sendMcuCommand(new McuStatus.KswMcuMsg(120, 8, 0));
    }

    public void radarClick() {
        WitsCommand.sendMcuCommand(new McuStatus.KswMcuMsg(120, 9, 0));
    }

    public void parkClick() {
        WitsCommand.sendMcuCommand(new McuStatus.KswMcuMsg(120, 10, 0));
    }

    public void screenOff() {
        onSendCommand(1, 113);
    }

    public void sendByteCommand(byte data) {
        byte[] bArr = {-14, 0, 120, 2, data, 0};
    }

    public static void sendKeyDownUpSync(final int key) {
        new Handler().post(new Runnable() {
            public void run() {
                new Thread(new Runnable() {
                    public void run() {
                        new Instrumentation().sendKeyDownUpSync(key);
                    }
                }).start();
            }
        });
    }

    public void openSettings() {
        openApp(new ComponentName(BuildConfig.APPLICATION_ID, "com.wits.ksw.settings.SettingsActivity"));
    }

    public void openDvr() {
        if (KswUtils.getDvrType() == 1) {
            onSendCommand(1, WitsCommand.SystemCommand.OPEN_CVBSDVR);
            Log.i(TAG, "openDvr: onSendCommand:609");
        } else if (KswUtils.getDvrType() == 2) {
            String usbPkg = KswUtils.getUsbDvrPkg();
            Log.i(TAG, "openDvr: usbPkg:" + usbPkg);
            if (!TextUtils.isEmpty(usbPkg)) {
                openApp(this.context.getPackageManager().getLaunchIntentForPackage(usbPkg));
            }
        }
    }

    public void openVideo() {
        openApp(new Intent("com.wits.media.VIDEO"));
    }
}
