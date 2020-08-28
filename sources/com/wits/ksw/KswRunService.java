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

public class KswRunService extends Service {
    /* access modifiers changed from: private */
    public static final String TAG = KswRunService.class.getName();
    private ICmdListener cmdListener = new ICmdListener.Stub() {
        public boolean handleCommand(String s) throws RemoteException {
            return false;
        }

        public void updateStatusInfo(String mcuMssage) throws RemoteException {
            if (TextUtils.isEmpty(mcuMssage)) {
                String access$100 = KswRunService.TAG;
                Log.e(access$100, "updateStatusInfo: mcuMssage:" + mcuMssage);
                return;
            }
            WitsStatus status1 = WitsStatus.getWitsStatusFormJson(mcuMssage);
            if (status1 == null) {
                String access$1002 = KswRunService.TAG;
                Log.e(access$1002, "updateStatusInfo: WitsStatusFormJson:" + status1);
                return;
            }
            int type = status1.getType();
            if (type == 5) {
                KswRunService.this.handleCarinfo(McuStatus.getStatusFromJson(status1.jsonArg), 0);
            } else if (type == 21) {
                MusicStatus musicStatus = MusicStatus.getStatusFromJson(status1.jsonArg);
                if (musicStatus == null) {
                    String access$1003 = KswRunService.TAG;
                    Log.e(access$1003, "updateStatusInfo: MusicStatusFromJson:" + musicStatus);
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
                String access$100 = KswRunService.TAG;
                Log.i(access$100, "musicContentObserver onChange: " + path);
                MediaImpl.getInstance().handleMediaInfo(path);
            } catch (Exception e) {
                e.printStackTrace();
                String access$1002 = KswRunService.TAG;
                Log.e(access$1002, "musicContentObserver: " + e.getMessage());
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
        PowerManagerApp.registerIContentObserver("mcuJson", new IContentObserver.Stub() {
            long time;

            public void onChange() throws RemoteException {
                int delay = 0;
                if (this.time != 0) {
                    delay = (int) (System.currentTimeMillis() - this.time);
                }
                this.time = System.currentTimeMillis();
                KswRunService.this.handleCarinfo(McuStatus.getStatusFromJson(PowerManagerApp.getStatusString("mcuJson")), delay);
            }
        });
        Log.i(TAG, "onCreate: registerICmdListener");
    }

    private void registerMusicPlayCurrentTime() {
        PowerManagerApp.registerIContentObserver("path", this.musicContentObserver);
    }

    /* access modifiers changed from: private */
    public void handleCarinfo(McuStatus mcuStatus, int delay) {
        Log.i(TAG, "handleCarinfo: ");
        if (mcuStatus == null) {
            ExceptionPrint.print("handleCarinfo: McuStatus is null");
            return;
        }
        McuStatus.CarData carDatam = mcuStatus.carData;
        if (carDatam == null) {
            ExceptionPrint.print("handleCarinfo: McuStatus.CarData is null");
        } else {
            McuImpl.getInstance().setCarData(carDatam, delay);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onCreate: -------------------------------MCU服务已关闭");
    }
}
