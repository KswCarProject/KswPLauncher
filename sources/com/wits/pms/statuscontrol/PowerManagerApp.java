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
import com.wits.pms.IStatusObserver;
import com.wits.pms.IStatusService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PowerManagerApp {
    /* access modifiers changed from: private */
    public static ICmdListener cmdListener;
    private static Context context;
    /* access modifiers changed from: private */
    public static final Object lock = new Object();
    /* access modifiers changed from: private */
    public static final HashMap<String, IContentObserver> maps = new HashMap<>();

    public static void init(Context context2) {
        context = context2.getApplicationContext();
        context2.getContentResolver().registerContentObserver(Settings.System.getUriFor("bootTimes"), true, new ContentObserver(new Handler(context2.getMainLooper())) {
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                if (PowerManagerApp.cmdListener != null) {
                    PowerManagerApp.registerICmdListener(PowerManagerApp.cmdListener);
                }
                synchronized (PowerManagerApp.lock) {
                    for (String key : PowerManagerApp.maps.keySet()) {
                        PowerManagerApp.registerIContentObserver(key, (IContentObserver) PowerManagerApp.maps.get(key));
                    }
                    for (String key2 : V2.maps.keySet()) {
                        V2.registerIContentObserver(key2, (IStatusObserver) V2.maps.get(key2));
                    }
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
        synchronized (lock) {
            try {
                maps.put(key, contentObserver);
                if (getManager() != null) {
                    getManager().registerObserver(key, contentObserver);
                }
            } catch (RemoteException e) {
            }
        }
    }

    public static void unRegisterIContentObserver(IContentObserver contentObserver) {
        synchronized (lock) {
            try {
                for (String key : maps.keySet()) {
                    if (maps.get(key) == contentObserver) {
                        maps.remove(key, contentObserver);
                    }
                }
                if (getManager() != null) {
                    getManager().unregisterObserver(contentObserver);
                }
            } catch (Exception e) {
                maps.clear();
            }
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
        if (getManager() != null) {
            try {
                getManager().sendStatus(new Gson().toJson((Object) witsStatus));
            } catch (RemoteException e) {
            }
        }
    }

    public static List<String> getDataListFromJsonKey(String key) {
        return (List) new Gson().fromJson(Settings.System.getString(context.getContentResolver(), key), new TypeToken<ArrayList<String>>() {
        }.getType());
    }

    public static void setBooleanStatus(String key, boolean value) throws RemoteException {
        try {
            getManager().addBooleanStatus(key, value);
        } catch (Exception e) {
        }
    }

    public static void setStatusString(String key, String value) throws RemoteException {
        try {
            getManager().addStringStatus(key, value);
        } catch (Exception e) {
        }
    }

    public static void setStatusInt(String key, int value) throws RemoteException {
        try {
            getManager().addIntStatus(key, value);
        } catch (Exception e) {
        }
    }

    public static boolean getStatusBoolean(String key) throws RemoteException {
        try {
            return getManager().getStatusBoolean(key);
        } catch (Exception e) {
            return false;
        }
    }

    public static String getStatusString(String key) throws RemoteException {
        try {
            return getManager().getStatusString(key);
        } catch (Exception e) {
            return null;
        }
    }

    public static int getStatusInt(String key) throws RemoteException {
        try {
            return getManager().getStatusInt(key);
        } catch (Exception e) {
            return -1;
        }
    }

    public static int getSettingsInt(String key) throws RemoteException {
        return getManager().getSettingsInt(key);
    }

    public static String getSettingsString(String key) throws RemoteException {
        return getManager().getSettingsString(key);
    }

    public static void setSettingsInt(String key, int value) throws RemoteException {
        try {
            getManager().setSettingsInt(key, value);
        } catch (Exception e) {
        }
    }

    public static void setSettingsString(String key, String value) throws RemoteException {
        try {
            getManager().setSettingsString(key, value);
        } catch (Exception e) {
        }
    }

    public static class V2 {
        /* access modifiers changed from: private */
        public static final HashMap<String, IStatusObserver> maps = new HashMap<>();

        public static IStatusService getManager() {
            return IStatusService.Stub.asInterface(PowerManagerApp.getService("wits_status"));
        }

        public static void setBooleanStatus(String key, boolean value) {
            try {
                getManager().addBooleanStatus(key, value);
            } catch (Exception e) {
            }
        }

        public static void setStatusString(String key, String value) {
            try {
                getManager().addStringStatus(key, value);
            } catch (Exception e) {
            }
        }

        public static void setStatusInt(String key, int value) {
            try {
                getManager().addIntStatus(key, value);
            } catch (Exception e) {
            }
        }

        public static boolean getStatusBoolean(String key) {
            try {
                return getManager().getStatusBoolean(key);
            } catch (Exception e) {
                return false;
            }
        }

        public static String getStatusString(String key) {
            try {
                return getManager().getStatusString(key);
            } catch (Exception e) {
                return null;
            }
        }

        public static int getStatusInt(String key) {
            try {
                return getManager().getStatusInt(key);
            } catch (Exception e) {
                return -1;
            }
        }

        public static void registerIContentObserver(String key, IStatusObserver statusObserver) {
            Log.i("IPowerManagerService", statusObserver.getClass().getName());
            synchronized (PowerManagerApp.lock) {
                try {
                    maps.put(key, statusObserver);
                    if (getManager() != null) {
                        getManager().registerStatusListener(key, statusObserver);
                    }
                } catch (RemoteException e) {
                }
            }
        }

        public static void unRegisterIContentObserver(IStatusObserver statusObserver) {
            synchronized (PowerManagerApp.lock) {
                try {
                    for (String key : maps.keySet()) {
                        if (maps.get(key) == statusObserver) {
                            maps.remove(key, statusObserver);
                        }
                    }
                    if (getManager() != null) {
                        getManager().unregisterStatusListener(statusObserver);
                    }
                } catch (Exception e) {
                    maps.clear();
                }
            }
        }
    }
}
