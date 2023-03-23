package com.wits.ksw;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.wits.ksw.launcher.model.McuImpl;
import com.wits.ksw.launcher.model.MediaImpl;
import com.wits.ksw.launcher.utils.ExceptionPrint;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import com.wits.pms.ICmdListener;
import com.wits.pms.IContentObserver;
import com.wits.pms.statuscontrol.McuStatus;
import com.wits.pms.statuscontrol.MusicStatus;
import com.wits.pms.statuscontrol.PowerManagerApp;
import com.wits.pms.statuscontrol.VideoStatus;
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
                Log.e(KswRunService.TAG, "updateStatusInfo: mcuMssage is null");
                return;
            }
            WitsStatus status1 = WitsStatus.getWitsStatusFormJson(mcuMssage);
            if (status1 == null) {
                Log.e(KswRunService.TAG, "updateStatusInfo: WitsStatus is null");
                return;
            }
            Log.i(KswRunService.TAG, "updateStatusInfo: WitsStatus:" + status1.type + " , type : " + status1.jsonArg);
            switch (status1.getType()) {
                case 5:
                    Log.i(KswRunService.TAG, "updateStatusInfo: TYPE_MCU_STATUS");
                    KswRunService.this.handleCarinfo(McuStatus.getStatusFromJson(status1.jsonArg), 0);
                    return;
                case 21:
                    Log.i(KswRunService.TAG, "updateStatusInfo: TYPE_MUSIC_STATUS");
                    MusicStatus musicStatus = MusicStatus.getStatusFromJson(status1.jsonArg);
                    if (musicStatus == null) {
                        Log.e(KswRunService.TAG, "updateStatusInfo: MusicStatus is null");
                        return;
                    }
                    int postion = musicStatus.position;
                    String path = musicStatus.path;
                    MediaImpl.getInstance().handleMediaPlaySeekbarAndCurrentime(postion);
                    MediaImpl.getInstance().handleMediaInfo(path);
                    return;
                case 22:
                    Log.e(KswRunService.TAG, "updateStatusInfo: TYPE_VIDEO_STATUS json : " + status1.jsonArg);
                    VideoStatus videoStatus = VideoStatus.getStatusFromJson(status1.jsonArg);
                    if (TextUtils.isEmpty(videoStatus.getPath())) {
                        Log.i(KswRunService.TAG, "updateStatusInfo: videoStatus getPath == null");
                        return;
                    } else if (UiThemeUtils.isUI_GS_ID8(KswApplication.appContext)) {
                        MediaImpl.getInstance().handleVideoInfoSetPlayStatus(videoStatus, videoStatus.getPath());
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
    };
    private IContentObserver.Stub musicContentObserver = new IContentObserver.Stub() {
        public void onChange() throws RemoteException {
            if (!UiThemeUtils.isBMW_ID8_UI(KswRunService.this) && !UiThemeUtils.isUI_GS_ID8(KswRunService.this)) {
                try {
                    String path = PowerManagerApp.getManager().getStatusString("path");
                    Log.i(KswRunService.TAG, "musicContentObserver onChange: " + path);
                    MediaImpl.getInstance().handleMediaInfo(path);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(KswRunService.TAG, "musicContentObserver: " + e.getMessage());
                }
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

    public IBinder onBind(Intent intent) {
        return new KswMcuBinder();
    }

    public void onCreate() {
        super.onCreate();
        String str = TAG;
        Log.i(str, "onCreate: MCU服务已启动");
        registerMusicPlayCurrentTime();
        Log.i(str, "onCreate: registerMusicPlayCurrentTime");
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
        Log.i(str, "onCreate: registerICmdListener");
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
