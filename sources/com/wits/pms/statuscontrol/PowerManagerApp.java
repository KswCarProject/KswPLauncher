package com.wits.pms.statuscontrol;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wits.pms.ICmdListener;
import com.wits.pms.IContentObserver;
import com.wits.pms.IPowerManagerAppService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PowerManagerApp {
    /* access modifiers changed from: private */
    public static ICmdListener cmdListener;
    private static Context context;
    /* access modifiers changed from: private */
    public static final HashMap<String, IContentObserver> maps = new HashMap<>();

    public static void init(Context context2) {
        context = context2;
        context2.getContentResolver().registerContentObserver(Settings.System.getUriFor("bootTimes"), true, new ContentObserver(new Handler(context2.getMainLooper())) {
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                if (PowerManagerApp.cmdListener != null) {
                    PowerManagerApp.registerICmdListener(PowerManagerApp.cmdListener);
                }
                for (String key : PowerManagerApp.maps.keySet()) {
                    PowerManagerApp.registerIContentObserver(key, (IContentObserver) PowerManagerApp.maps.get(key));
                }
            }
        });
    }

    public static IPowerManagerAppService getManager() {
        return IPowerManagerAppService.Stub.asInterface(getService("wits_pms"));
    }

    public static void registerICmdListener(ICmdListener listener) {
        try {
            cmdListener = listener;
            if (getManager() != null) {
                getManager().registerCmdListener(listener);
            }
        } catch (RemoteException e) {
        }
    }

    public static void registerIContentObserver(String key, IContentObserver contentObserver) {
        Log.i("IPowerManagerService", contentObserver.getClass().getName());
        try {
            maps.put(key, contentObserver);
            if (getManager() != null) {
                getManager().registerObserver(key, contentObserver);
            }
        } catch (RemoteException e) {
        }
    }

    public static void unRegisterIContentObserver(IContentObserver contentObserver) {
        try {
            for (String key : maps.keySet()) {
                if (maps.get(key) == contentObserver) {
                    maps.remove(key, contentObserver);
                }
            }
            if (getManager() != null) {
                getManager().unregisterObserver(contentObserver);
            }
        } catch (RemoteException e) {
        }
    }

    @SuppressLint({"PrivateApi"})
    public static IBinder getService(String serviceName) {
        try {
            Class<?> serviceManager = Class.forName("android.os.ServiceManager");
            return (IBinder) serviceManager.getMethod("getService", new Class[]{String.class}).invoke(serviceManager, new Object[]{serviceName});
        } catch (Exception e) {
            String name = PowerManagerApp.class.getName();
            Log.e(name, "error service init - " + serviceName, e);
            return null;
        }
    }

    public static boolean sendCommand(String jsonMsg) {
        try {
            return getManager().sendCommand(jsonMsg);
        } catch (RemoteException e) {
            Log.i(getManager().getClass().getName(), "error sendCommand", e);
            return false;
        }
    }

    public static void sendStatus(WitsStatus witsStatus) {
        try {
            getManager().sendStatus(new Gson().toJson((Object) witsStatus));
        } catch (RemoteException e) {
        }
    }

    public static List<String> getDataListFromJsonKey(String key) {
        return (List) new Gson().fromJson(Settings.System.getString(context.getContentResolver(), key), new TypeToken<ArrayList<String>>() {
        }.getType());
    }

    public static boolean getStatusBoolean(String key) throws RemoteException {
        return getManager().getStatusBoolean(key);
    }

    public static String getStatusString(String key) throws RemoteException {
        return getManager().getStatusString(key);
    }

    public static int getStatusInt(String key) throws RemoteException {
        return getManager().getStatusInt(key);
    }

    public static int getSettingsInt(String key) throws RemoteException {
        return getManager().getSettingsInt(key);
    }

    public static String getSettingsString(String key) throws RemoteException {
        return getManager().getSettingsString(key);
    }

    public static void setSettingsInt(String key, int value) throws RemoteException {
        getManager().setSettingsInt(key, value);
    }

    public static void setSettingsString(String key, String value) throws RemoteException {
        getManager().setSettingsString(key, value);
    }
}
