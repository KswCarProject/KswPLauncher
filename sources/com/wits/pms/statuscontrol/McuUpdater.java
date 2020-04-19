package com.wits.pms.statuscontrol;

import android.os.RemoteException;
import com.wits.pms.ksw.IMcuUpdate;
import com.wits.pms.ksw.OnMcuUpdateProgressListener;

public class McuUpdater {
    private static IMcuUpdate getMcuUpdater() {
        return IMcuUpdate.Stub.asInterface(ServiceManager.getService("mcu_update"));
    }

    public static void registerMcuUpdateListener(OnMcuUpdateProgressListener listener) throws RemoteException {
        getMcuUpdater().setOnMcuUpdateProgressListener(listener);
    }

    public static void mcuUpdate(String path) throws RemoteException {
        getMcuUpdater().mcuUpdate(path);
    }
}
