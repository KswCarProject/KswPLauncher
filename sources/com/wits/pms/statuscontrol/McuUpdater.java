package com.wits.pms.statuscontrol;

import android.os.IBinder;
import android.os.RemoteException;
import com.wits.pms.ksw.IMcuUpdate;
import com.wits.pms.ksw.OnMcuUpdateProgressListener;

/* loaded from: classes.dex */
public class McuUpdater {
    private static IMcuUpdate getMcuUpdater() {
        IBinder mcu_update = ServiceManager.getService("mcu_update");
        return IMcuUpdate.Stub.asInterface(mcu_update);
    }

    public static void registerMcuUpdateListener(OnMcuUpdateProgressListener listener) throws RemoteException {
        getMcuUpdater().setOnMcuUpdateProgressListener(listener);
    }

    public static void mcuUpdate(String path) throws RemoteException {
        getMcuUpdater().mcuUpdate(path);
    }
}
