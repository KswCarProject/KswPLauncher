package com.bumptech.glide.manager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import com.bumptech.glide.manager.ConnectivityMonitor;

public class DefaultConnectivityMonitorFactory implements ConnectivityMonitorFactory {
    private static final String NETWORK_PERMISSION = "android.permission.ACCESS_NETWORK_STATE";
    private static final String TAG = "ConnectivityMonitor";

    @NonNull
    public ConnectivityMonitor build(@NonNull Context context, @NonNull ConnectivityMonitor.ConnectivityListener listener) {
        String str;
        boolean hasPermission = ContextCompat.checkSelfPermission(context, NETWORK_PERMISSION) == 0;
        if (Log.isLoggable(TAG, 3)) {
            if (hasPermission) {
                str = "ACCESS_NETWORK_STATE permission granted, registering connectivity monitor";
            } else {
                str = "ACCESS_NETWORK_STATE permission missing, cannot register connectivity monitor";
            }
            Log.d(TAG, str);
        }
        return hasPermission ? new DefaultConnectivityMonitor(context, listener) : new NullConnectivityMonitor();
    }
}
