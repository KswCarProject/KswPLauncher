package com.wits.ksw;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.wits.ksw.launcher.model.AppsLoaderTask;
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

/* loaded from: classes17.dex */
public class KswRunService extends Service {
    private static final String TAG = KswRunService.class.getName();
    private IContentObserver.Stub musicContentObserver = new IContentObserver.Stub() { // from class: com.wits.ksw.KswRunService.2
        @Override // com.wits.pms.IContentObserver
        public void onChange() throws RemoteException {
            if (UiThemeUtils.isBMW_ID8_UI(KswRunService.this) || UiThemeUtils.isUI_GS_ID8(KswRunService.this) || UiThemeUtils.isUI_PEMP_ID8(KswRunService.this)) {
                return;
            }
            try {
                String path = PowerManagerApp.getManager().getStatusString("path");
                Log.i(KswRunService.TAG, "musicContentObserver onChange: " + path);
                MediaImpl.getInstance().handleMediaInfo(path);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(KswRunService.TAG, "musicContentObserver: " + e.getMessage());
            }
        }
    };
    private ICmdListener cmdListener = new ICmdListener.Stub() { // from class: com.wits.ksw.KswRunService.3
        @Override // com.wits.pms.ICmdListener
        public boolean handleCommand(String s) throws RemoteException {
            return false;
        }

        @Override // com.wits.pms.ICmdListener
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
                    McuStatus mcuStatus = McuStatus.getStatusFromJson(status1.jsonArg);
                    KswRunService.this.handleCarinfo(mcuStatus, 0);
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
                    } else if (UiThemeUtils.isUI_GS_ID8(KswApplication.appContext) || UiThemeUtils.isUI_PEMP_ID8(KswApplication.appContext)) {
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
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.wits.ksw.KswRunService.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            AppsLoaderTask.getInstance().queryAllApps();
        }
    };

    /* loaded from: classes17.dex */
    class KswMcuBinder extends Binder {
        KswMcuBinder() {
        }

        public KswRunService getService() {
            return KswRunService.this;
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return new KswMcuBinder();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        String str = TAG;
        Log.i(str, "onCreate: MCU\u670d\u52a1\u5df2\u542f\u52a8");
        registerReceiver();
        registerMusicPlayCurrentTime();
        Log.i(str, "onCreate: registerMusicPlayCurrentTime");
        PowerManagerApp.registerICmdListener(this.cmdListener);
        PowerManagerApp.registerIContentObserver("mcuJson", new IContentObserver.Stub() { // from class: com.wits.ksw.KswRunService.1
            long time;

            @Override // com.wits.pms.IContentObserver
            public void onChange() throws RemoteException {
                int delay = 0;
                if (this.time != 0) {
                    delay = (int) (System.currentTimeMillis() - this.time);
                }
                this.time = System.currentTimeMillis();
                McuStatus mcuStatus = McuStatus.getStatusFromJson(PowerManagerApp.getStatusString("mcuJson"));
                KswRunService.this.handleCarinfo(mcuStatus, delay);
            }
        });
        Log.i(str, "onCreate: registerICmdListener");
    }

    private void registerMusicPlayCurrentTime() {
        try {
            PowerManagerApp.registerIContentObserver("path", this.musicContentObserver);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "registerMusicPlayCurrentTime: " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
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

    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme("package");
        KswApplication.appContext.registerReceiver(this.broadcastReceiver, intentFilter);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onCreate: -------------------------------MCU\u670d\u52a1\u5df2\u5173\u95ed");
        KswApplication.appContext.unregisterReceiver(this.broadcastReceiver);
    }
}
