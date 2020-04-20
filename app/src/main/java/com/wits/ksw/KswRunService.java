package com.wits.ksw;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.wits.ksw.launcher.model.McuImpl;
import com.wits.ksw.launcher.model.MediaImpl;
import com.wits.ksw.launcher.utils.ExceptionPrint;
import com.wits.pms.ICmdListener;
import com.wits.pms.IContentObserver;
import com.wits.pms.statuscontrol.McuStatus;
import com.wits.pms.statuscontrol.MusicStatus;
import com.wits.pms.statuscontrol.PowerManagerApp;
import com.wits.pms.statuscontrol.WitsStatus;
import java.util.Random;

public class KswRunService extends Service {
    /* access modifiers changed from: private */
    public static final String TAG = KswRunService.class.getName();
    private ICmdListener cmdListener = new ICmdListener.Stub() {
        public boolean handleCommand(String s) throws RemoteException {
            return false;
        }

        public void updateStatusInfo(String mcuMssage) throws RemoteException {
            if (TextUtils.isEmpty(mcuMssage)) {
                String access$000 = KswRunService.TAG;
                Log.e(access$000, "updateStatusInfo: mcuMssage:" + mcuMssage);
                return;
            }
            WitsStatus status1 = WitsStatus.getWitsStatusFormJson(mcuMssage);
            if (status1 == null) {
                String access$0002 = KswRunService.TAG;
                Log.e(access$0002, "updateStatusInfo: WitsStatusFormJson:" + status1);
                return;
            }
            int type = status1.getType();
            if (type == 5) {
                KswRunService.this.handleCarinfo(status1);
            } else if (type == 21) {
                MusicStatus musicStatus = MusicStatus.getMusicStatusFromJson(status1.jsonArg);
                if (musicStatus == null) {
                    String access$0003 = KswRunService.TAG;
                    Log.e(access$0003, "updateStatusInfo: MusicStatusFromJson:" + musicStatus);
                    return;
                }
                MediaImpl.getInstance().handleMediaPlaySeekbarAndCurrentime(musicStatus.position);
            }
        }
    };
    private IContentObserver.Stub musicContentObserver = new IContentObserver.Stub() {
        public void onChange() throws RemoteException {
            try {
                String path = PowerManagerApp.getManager().getStatusString("path");
                String access$000 = KswRunService.TAG;
                Log.i(access$000, "musicContentObserver onChange: " + path);
                MediaImpl.getInstance().handleMediaInfo(path);
            } catch (Exception e) {
                e.printStackTrace();
                String access$0002 = KswRunService.TAG;
                Log.e(access$0002, "musicContentObserver: " + e.getMessage());
            }
        }
    };

    class KswMcuBinder extends Binder {
        KswMcuBinder() {
        }

        public KswRunService getService() {
            return KswRunService.this;
        }
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        return new KswMcuBinder();
    }

    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: MCU服务已启动");
        registerMusicPlayCurrentTime();
        Log.i(TAG, "onCreate: registerMusicPlayCurrentTime");
        PowerManagerApp.registerICmdListener(this.cmdListener);
        Log.i(TAG, "onCreate: registerICmdListener");
    }

    private void registerMusicPlayCurrentTime() {
        PowerManagerApp.registerIContentObserver("path", this.musicContentObserver);
    }

    /* access modifiers changed from: private */
    public void handleCarinfo(WitsStatus status1) {
        Log.i(TAG, "handleCarinfo: ");
        McuStatus mcuStatus = McuStatus.getStatusFromJson(status1.jsonArg);
        if (mcuStatus == null) {
            ExceptionPrint.print("handleCarinfo: McuStatus is null");
            return;
        }
        McuStatus.CarData carDatam = mcuStatus.carData;
        if (carDatam == null) {
            ExceptionPrint.print("handleCarinfo: McuStatus.CarData is null");
            return;
        }
        new Random();
        McuImpl.getInstance().setCarData(carDatam);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onCreate: -------------------------------MCU服务已关闭");
    }
}
