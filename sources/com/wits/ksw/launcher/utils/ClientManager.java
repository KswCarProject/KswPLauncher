package com.wits.ksw.launcher.utils;

import android.text.TextUtils;
import android.util.Log;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class ClientManager {
    public static final String CLIENT_ALS_6208 = "ALS_6208";
    public static final String CLIENT_yc_2306 = "yc_2306";
    private static final String TAG = ("KSWLauncher." + ClientManager.class.getSimpleName());
    private static ClientManager instance;

    private ClientManager() {
    }

    public static synchronized ClientManager getInstance() {
        ClientManager clientManager;
        synchronized (ClientManager.class) {
            if (instance == null) {
                instance = new ClientManager();
            }
            clientManager = instance;
        }
        return clientManager;
    }

    public String getClient() {
        try {
            return PowerManagerApp.getSettingsString("client");
        } catch (Exception e) {
            e.printStackTrace();
            String str = TAG;
            Log.e(str, "getClient: " + e.getMessage());
            return null;
        }
    }

    public int getDashboardSelect() {
        try {
            return PowerManagerApp.getSettingsInt("Dashboard_Select");
        } catch (Exception e) {
            e.printStackTrace();
            String str = TAG;
            Log.e(str, "getDashboardSelect: " + e.getMessage());
            return 0;
        }
    }

    public boolean isAls6208Client() {
        return TextUtils.equals(getClient(), CLIENT_ALS_6208);
    }

    public boolean isYC2306Client() {
        return TextUtils.equals(getClient(), CLIENT_yc_2306);
    }
}
