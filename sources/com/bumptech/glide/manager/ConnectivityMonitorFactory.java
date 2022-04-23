package com.bumptech.glide.manager;

import android.content.Context;
import com.bumptech.glide.manager.ConnectivityMonitor;

public interface ConnectivityMonitorFactory {
    ConnectivityMonitor build(Context context, ConnectivityMonitor.ConnectivityListener connectivityListener);
}
